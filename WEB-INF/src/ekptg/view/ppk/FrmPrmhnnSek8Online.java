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
import ekptg.helpers.Paging;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8OnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8OnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakOnlineData;
import ekptg.model.ppk.FrmSenaraiFailInternalData;
import ekptg.model.ppk.FrmSenaraiFailOnlineCarianData;
import ekptg.model.ppk.FrmSenaraiFailOnlineData;
//import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
//import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHOnlineData;
//import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
//import ekptg.model.ppk.FrmSenaraiFailInternalCarianData;
//import ekptg.model.ppk.FrmSenaraiFailInternalData;

public class FrmPrmhnnSek8Online extends VTemplate {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String currentDate = dateFormat.format(date);

	static Logger myLogger = Logger.getLogger(FrmPrmhnnSek8Online.class);

	FrmPrmhnnSek8OnlineData logic = new FrmPrmhnnSek8OnlineData();
	FrmPrmhnnSek8DaftarSek8OnlineData logic_AO = new FrmPrmhnnSek8DaftarSek8OnlineData();
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
	FrmPrmhnnSek8SenaraiHTATHInternalData logic_B = new FrmPrmhnnSek8SenaraiHTATHInternalData();
	FrmPrmhnnSek8SenaraiSemakOnlineData logic_C = new FrmPrmhnnSek8SenaraiSemakOnlineData();
	FrmSenaraiFailOnlineCarianData logic_D = new FrmSenaraiFailOnlineCarianData();
	FrmSenaraiFailOnlineData logic_E = new FrmSenaraiFailOnlineData();
	FrmPrmhnnSek8InternalData logic_internal = new FrmPrmhnnSek8InternalData();
	FrmSenaraiFailInternalData logic_F = new FrmSenaraiFailInternalData();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	private String onlineLebih = "";
	private String idUser = "";

	public Template doTemplate() throws Exception {
		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		String flagForView = getParam("flagForView");

		headerppk_baru_default();
		Vector listSupportingDoc = null;
		this.context.put("skrin_online_17", "");
		this.context.put("skrin_online", "");
		this.context.put("skrin_online_popup", "");
		this.context.put("appear_skrin_info", "");

		/*
		 * Vector count_dunia1 = logic_A.getNofaildunia(2, 1, 8);
		 * this.context.put("count_dunia", count_dunia1);
		 */

		String bolehsimpan = "";
		HttpSession session = this.request.getSession();
		String doPost = (String) session.getAttribute("doPost");
		idUser = String.valueOf(session.getAttribute("_ekptg_user_id"));

		Calendar cal = Calendar.getInstance();
		Calendar currentcal = Calendar.getInstance();
		cal.set(2009, Calendar.SEPTEMBER, 1);
		currentcal.set(currentcal.get(Calendar.YEAR),
				currentcal.get(Calendar.MONTH),
				currentcal.get(Calendar.DAY_OF_MONTH));

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
		String action = getParam("action");
		myLogger.info("action:" + action);
		myLogger.info("submit:" + submit);
		System.out.println("submit:" + submit);
		System.out.println("mode:" + mode);

		// int simpanStatus = 2;
		// int eventStatus = 0;
		// int backstatus = 1;

		int simpanStatus = 0;
		int eventStatus = 0;
		int backstatus = 0;

		String Carix = "";
		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";

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
		Vector listPenghutang = null;
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
		Vector view2 = null;
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
		Vector listdaerah = null;
		Vector sumppkhta = null;
		Vector sumoverallppkhta = null;
		Vector listxxx = null;
		Vector listxxx4 = null;
		Vector listSemakO = null;
		Vector v = null;

		int flagno = 0;
		int idFlag = 0;
		int flag_no = 0;

		readability1 = "";
		this.context.put("CheckWujudLot", "");
		this.context.put("daftar", "");
		this.context.put("nofail", "");
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
		this.context.put("llxx", "");
		this.context.put("listsijilmati", "");

		this.context.put("txtbox", "");
		this.context.put("tarikhresit", "");

		this.context.put("listPemohonOB", "");

		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

		String v_tab = getParam("v_tab");
		// System.out.print("V_TAB" + v_tab + ";");

		this.context.put("val_tab", v_tab);

		if ("tambah".equals(submit)) {
			readability1 = "new";
			simpanStatus = 0;
			eventStatus = 0;
			backstatus = 0;
			this.context.put("SimpanStatus", simpanStatus);
			vm = "app/ppk/frmPrmhnnSek8SenaraiSemak.jsp";
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
			this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah"));
			this.context.put("mode1", readability1);
			flagno = 0;
			idFlag = 1;
			this.context.put("flagno", flagno);
			this.context.put("idFlag", idFlag);
			this.context.put("View", list);

		} else if ("getDaftarStatus".equals(submit)) {
			// System.out.println("ST FAIL ::"
			// + getParam("id_Suburusanstatusfail"));

			String id_sub = getParam("id_Suburusanstatusfail");
			String id_Fail = getParam("id_Fail");
			String id = getParam("idPermohonan");
			if (bolehsimpan.equals("yes")) {
				// :::SUB
				// ID_STATUS : 170
				// ID_SUBURUSAN : 553
				// logic_A.daftarstatus(id, (String)
				// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
				logic_A.kemaskiniSubUrusanStatusFail(session, id,
						(String) session.getAttribute("_ekptg_user_id"), "170",
						"553", id_Fail);
			}

			idFlag = 2;
			flag_no = 2;
			eventStatus = 1;

			this.context.put("flagno", flag_no);
			this.context.put("idFlag", idFlag);
			this.context.put("EventStatus", eventStatus);

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			String idSimatim = getParam("idSimati");

			list2 = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			Hashtable h = (Hashtable) list2.get(0);
			// list2 =
			// logic_A.setData(id,(String)session.getAttribute("_ekptg_user_id"));
			this.context.put("View", list2);

			// String sj="";
			this.context.put("tarikhmohondaftar", h.get("tarikhMohon")
					.toString());
			if (h.get("pmidnegeri").toString() == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,null,"class=\"autoselect\" disabled "));
			} else {
				this.context.put("negeri", h.get("pmidnegeri").toString());
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h
						.get("pmidnegeri").toString()));
				this.context.put("listBandarbyNegeri", s3);
				this.context.put("daerah", "");
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
			}

			if (h.get("idbandar").toString() == "") {

				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,null,"class=\"autoselect\" disabled "));
			} else {
				this.context.put("daerah", h.get("idbandar").toString());

				// this.context.put("daerah","");
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
			}

			logic_A.setGetId(id);
			listIds = logic_A.getIds();
			Hashtable k = (Hashtable) listIds.get(0);
			this.context.put("IdSimati",
					Long.parseLong(k.get("idsimati").toString()));
			this.context.put("IdPemohon",
					Long.parseLong(k.get("idpemohon").toString()));
			this.context.put("IdPermohonan", getParam("idPermohonan"));
			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			this.context.put("duplicate", "");

			this.context.put("nofail", "yes");

			this.context.put("daftar", "yes");

			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPraPrmhnnSek8DaftarSek8.jsp";

		} else if ("papar".equals(submit)) {
			String idPermohonan = getParam("idpermohonan");

			logic_C.setDataSemakan(idPermohonan);
			listSemak = logic_C.getListSemakan();

			logic_C.setDataSemakanO(idPermohonan);
			listSemakO = logic_C.getListSemakanO();

			// System.out.println("LIST SEMAK ONLINE :" + listSemak.size());

			if (listSemak.size() == 0) {

				this.context.put("IdPermohonan", idPermohonan);

				idFlag = Integer.parseInt(getParam("idFlag"));
				idAlert = getParam("idAlert");
				simpanStatus = 0;
				this.context.put("SimpanStatus", simpanStatus);
				eventStatus = 0;
				backstatus = 0;
				this.context.put("EventStatus", eventStatus);
				this.context.put("backStatus", backstatus);

				String NegId = "1";
				String IDPermohonan = "0";
				// this.context.put("IdPermohonan", IDPermohonan);
				this.context.put("NegId", NegId);
				this.context.put("idFlag", idFlag);

				this.context.put("ListSemakan", listSemakO);

				this.context.put("nofail", "");
				this.context.put("daftar", "");

				list2 = logic_A.setData(getParam("idpermohonan"),
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, getParam("idpermohonan"), "Y", "", "T");

				Vector llxx = null;
				logic.setDataPeguamOnline(getParam("idpermohonan"));
				llxx = logic.getDataPeguamOnline();
				this.context.put("llxx", llxx);

				Vector ll_mati = null;
				logic.setDataSijilMati(getParam("idpermohonan"));
				ll_mati = logic.getDataSijilMati();
				this.context.put("listsijilmati", ll_mati);

				// System.out.print("list2:" + list2);

				// System.out.println("cal :" + cal + "currentcal :" +
				// currentcal);
				if (cal.before(currentcal)) {
					this.context.put("lepassatusept", "no");
				} else if (cal.after(currentcal)) {
					this.context.put("lepassatusept", "yes");
				} else {
					this.context.put("lepassatusept", "yes");
				}

			} else {

				readability1 = " ";
				readability2 = "readonly";
				disability1 = "disabled";
				disability2 = "";
				eventStatus = 1;
				String NegId = "1";
				int getnewpemohon = 0;
				this.context.put("GetNewPemohon", getnewpemohon);
				this.context.put("NegId", NegId);
				this.context.put("EventStatus", eventStatus);
				// Long idPermohonan = Long.parseLong(getParam("idpermohonan"));
				this.context.put("IdPermohonan", idPermohonan);
				this.context.put("backStatus", backstatus);

				// listSemak =
				// ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakOnlineData.getListSemakan();

				logic_C.setDataSemakan(idPermohonan);
				listSemak = logic_C.getListSemakan();
				this.context.put("ListSemakan", listSemak);

				// FrmPrmhnnSek8SenaraiSemakOnlineData
				// frmPrmhnnSek8SenaraiSemakData = new
				// FrmPrmhnnSek8SenaraiSemakOnlineData();
				logic_C.setDataSemakanSimati(idPermohonan);

				listSemakSimati = logic_C.getListSemakanSimati();
				this.context.put("ListSemakanSimati", listSemakSimati);
				this.context.put("SimpanStatus", 2);

				list2 = logic_A.setData(getParam("idpermohonan"),
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, getParam("idpermohonan"), "Y", "", "T");

				this.context.put("list2", list2);
				// list2 =
				// logic_A.setData(Integer.parseInt(getParam("idpermohonan")),(String)session.getAttribute("_ekptg_user_id"));

				// System.out.print("list2:" + list2);
				if (list2.size() > 0) {
					Hashtable i = (Hashtable) list2.get(0);
					if (i.get("noFail").toString() != "") {
						this.context.put("nofail", "yes");
					}

					Vector llxx = null;
					logic.setDataPeguamOnline(getParam("idpermohonan"));
					llxx = logic.getDataPeguamOnline();
					this.context.put("llxx", llxx);

					Vector ll_mati = null;
					logic.setDataSijilMati(getParam("idpermohonan"));
					ll_mati = logic.getDataSijilMati();
					this.context.put("listsijilmati", ll_mati);
				}

				this.context.put("daftar", "yes");

			}
			vm = "app/ppk/frmPraPrmhnnSek8SenaraiSemak.jsp";
			String usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("userid", usid);

		} else if ("Kemaskini".equals(submit)) {
			String idPermohonan = getParam("idtemp");
			String negId = getParam("negid");
			this.context.put("NegId", negId);
			// Long idPermohonan = Long.parseLong(tempid);
			String eventstatus = getParam("eventStatus");
			this.context.put("EventStatus", eventstatus);
			simpanStatus = 1;
			this.context.put("SimpanStatus", simpanStatus);
			myLogger.info("idPermohonan = "+idPermohonan);
			this.context.put("IdPermohonan", idPermohonan);

			
			//cari dokumen ic pemohon
			//logic_C.setDataSemakanICPemohon(idPermohonan,idPemohon);
			// listSemak2.clear();
			// FrmPrmhnnSek8SenaraiSemakOnlineData frm = new
			// FrmPrmhnnSek8SenaraiSemakOnlineData();
			logic_C.setDataSemakan(idPermohonan);
			listSemak2 = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak2);

			String usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("userid", usid);

			list2 = logic_A.setData(getParam("idtemp"),
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, getParam("idtemp"), "Y", "", "T");

			if (list2.size() > 0) {
				Hashtable i = (Hashtable) list2.get(0);
				if (i.get("noFail").toString() != "") {
					this.context.put("nofail", "yes");
				}
			}

			// list2 =
			// logic_A.setData(Integer.parseInt(getParam("idpermohonan")),(String)session.getAttribute("_ekptg_user_id"));

			this.context.put("list2", list2);
			// list2 =
			// logic_A.setData(Integer.parseInt(getParam("idpermohonan")),(String)session.getAttribute("_ekptg_user_id"));

			// System.out.print("list2:" + list2);
			this.context.put("daftar", "yes");
			
			Vector ll_mati = null;
			logic.setDataSijilMati(getParam("idpermohonan"));
			ll_mati = logic.getDataSijilMati();
			myLogger.info("ll_mati");
			this.context.put("listsijilmati", ll_mati);

			vm = "app/ppk/frmPraPrmhnnSek8SenaraiSemak.jsp";

		} else if ("Simpan".equals(submit)) {
			String eventstatus = getParam("eventStatus");
			eventStatus = Integer.parseInt(eventstatus);
			if (eventStatus == 0) {
				// long idPermohonan = DB.getNextID("TBLPPKPERMOHONAN_SEQ");
				String idPermohonan = getParam("idtemp");
				String buktimati = getParam("cbsemakradio");
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				// FrmPrmhnnSek8SenaraiSemakOnlineData frm = new
				// FrmPrmhnnSek8SenaraiSemakOnlineData();
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						String txtbox = "";
						String tarikhresit = "";
						if (cbsemaks[i].equals("5")) {
							txtbox = getParam("txtNomborSijil");
						} else if (cbsemaks[i].equals("8")) {
							txtbox = getParam("txtNomborPermit");
						} else if (cbsemaks[i].equals("9")) {
							txtbox = getParam("txtNomborResit");
							tarikhresit = getParam("txdTarikhByrn");
						} else if (cbsemaks[i].equals("23")) {
							txtbox = getParam("txtLainLainTujuan");
						}
						if (bolehsimpan.equals("yes")) {
							logic_C.semakanAdd(cbsemaks[i],
									String.valueOf(idPermohonan),
									String.valueOf(txtbox),
									String.valueOf(tarikhresit), buktimati);
						}
					}
				}
				eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				simpanStatus = 2;
				int getnewpemohon = 1;
				// String buktimati = getParam("cbsemakradio");
				String idNeg = getParam("negid");
				this.context.put("NegId", idNeg);
				this.context.put("GetNewPemohon", getnewpemohon);
				this.context.put("SimpanStatus", simpanStatus);
				logic_C.setDataSemakan(idPermohonan);
				this.context.put("IdPermohonan", idPermohonan);

				listSemak = logic_C.getListSemakan();
				this.context.put("ListSemakan", listSemak);
				list2 = logic_A.setData(getParam("idtemp"),
						(String) session.getAttribute("_ekptg_user_id"));
				this.context.put("list2", list2);
				headerppk_baru(session, getParam("idtemp"), "Y", "", "T");
				if (list2.size() > 0) {
					Hashtable h = (Hashtable) list2.get(0);
					if (h.get("noFail").toString() != "") {
						this.context.put("nofail", "yes");
					}
				}
				this.context.put("daftar", "yes");

				vm = "app/ppk/frmPraPrmhnnSek8SenaraiSemak.jsp";
			} else if (eventStatus == 1) {
				String buktimati = getParam("cbsemakradio");
				String idPermohonan = getParam("idtemp");
				// Long idPermohonan = Long.parseLong(tempid);
				if (bolehsimpan.equals("yes")) {
					delete_semakan(session, idPermohonan);
				}
				String[] cbsemaks = this.request.getParameterValues("cbsemaks");
				// FrmPrmhnnSek8SenaraiSemakOnlineData frm1 = new
				// FrmPrmhnnSek8SenaraiSemakOnlineData();
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						String txtbox = "";
						String tarikhresit = "";
						String set_bukti = "";
						if (cbsemaks[i].equals("5")) {
							set_bukti = "1";
							txtbox = getParam("txtNomborSijil");
							if (bolehsimpan.equals("yes")) {
								logic_C.update_bukti(
										String.valueOf(idPermohonan),
										(String) session
												.getAttribute("_ekptg_user_id"),
										txtbox, set_bukti);
							}
						} else if (cbsemaks[i].equals("6")) {
							// txtbox = getParam("txtNomborPermit");
							set_bukti = "2";
							if (bolehsimpan.equals("yes")) {
								logic_C.update_bukti(
										String.valueOf(idPermohonan),
										(String) session
												.getAttribute("_ekptg_user_id"),
										txtbox, set_bukti);
							}
						} else if (cbsemaks[i].equals("7")) {
							// txtbox = getParam("txtNomborPermit");
							set_bukti = "3";
							if (bolehsimpan.equals("yes")) {
								logic_C.update_bukti(
										String.valueOf(idPermohonan),
										(String) session
												.getAttribute("_ekptg_user_id"),
										txtbox, set_bukti);
							}
						} else if (cbsemaks[i].equals("8")) {
							set_bukti = "4";
							txtbox = getParam("txtNomborPermit");
							if (bolehsimpan.equals("yes")) {
								logic_C.update_bukti(
										String.valueOf(idPermohonan),
										(String) session
												.getAttribute("_ekptg_user_id"),
										txtbox, set_bukti);
							}
						} else if (cbsemaks[i].equals("9")) {
							txtbox = getParam("txtNomborResit");
							tarikhresit = getParam("txdTarikhByrn");
							if (bolehsimpan.equals("yes")) {
								logic_C.semakanUpdateBayar(String
										.valueOf(idPermohonan), txtbox,
										tarikhresit, (String) session
												.getAttribute("_ekptg_user_id"));
							}
						} else if (cbsemaks[i].equals("23")) {
							txtbox = getParam("txtLainLainTujuan");
						}
						if (bolehsimpan.equals("yes")) {
							logic_C.semakanUpdate(cbsemaks[i], String
									.valueOf(idPermohonan), String
									.valueOf(txtbox), String
									.valueOf(tarikhresit), buktimati,
									(String) session
											.getAttribute("_ekptg_user_id"));
						}
					}
				}

				simpanStatus = 2;
				eventStatus = 1;
				int getnewpemohon = 0;
				this.context.put("GetNewPemohon", getnewpemohon);
				this.context.put("EventStatus", eventStatus);
				this.context.put("SimpanStatus", simpanStatus);

				this.context.put("IdPermohonan", idPermohonan);

				// FrmPrmhnnSek8SenaraiSemakOnlineData frm = new
				// FrmPrmhnnSek8SenaraiSemakOnlineData();
				logic_C.setDataSemakan(idPermohonan);

				listSemak = logic_C.getListSemakan();

				this.context.put("ListSemakan", listSemak);
				String idNeg = getParam("negid");
				this.context.put("NegId", idNeg);
				String usid = (String) session.getAttribute("_ekptg_user_id");

				logic_C.getIdNegeriUser(usid);

				// String usid=(String)session.getAttribute("_ekptg_user_id");
				this.context.put("userid", usid);

				list2 = logic_A.setData(getParam("idtemp"),
						(String) session.getAttribute("_ekptg_user_id"));
				this.context.put("list2", list2);
				headerppk_baru(session, getParam("idtemp"), "Y", "", "T");

				if (list2.size() > 0) {
					Hashtable h = (Hashtable) list2.get(0);
					if (h.get("noFail").toString() != "") {
						this.context.put("nofail", "yes");
					}

					this.context.put("daftar", "yes");

				}
				vm = "app/ppk/frmPraPrmhnnSek8SenaraiSemak.jsp";
			}

		} else if ("kembali_daftar_pemohon".equals(submit)) {
			String idPermohonan = getParam("idtemp");
			// Long idPermohonan = Long.parseLong(tempid);
			String eventstatus = "1";
			this.context.put("EventStatus", eventstatus);
			// this.context.put("backStatus", 1);
			simpanStatus = 2;
			this.context.put("SimpanStatus", simpanStatus);
			// FrmPrmhnnSek8SenaraiSemakInternalData
			// frmPrmhnnSek8SenaraiSemakData = new
			// FrmPrmhnnSek8SenaraiSemakInternalData();
			logic_C.setDataSemakan(idPermohonan);
			this.context.put("IdPermohonan", idPermohonan);
			listSemak = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak);
			this.context.put("nofail", "yes");
			this.context.put("daftar", "yes");

			list2 = logic_A.setData(getParam("idtemp"),
					(String) session.getAttribute("_ekptg_user_id"));
			this.context.put("list2", list2);
			headerppk_baru(session, getParam("idtemp"), "Y", "", "T");

			vm = "app/ppk/frmPraPrmhnnSek8SenaraiSemak.jsp";

		} else if ("tab".equals(submit)) {
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String id = getParam("idpermohonan");
			// System.out.println("IDDD :::" + id);
			// int id2 = Integer.parseInt(getParam("idPemohon"));

			// this.context.put("id", id);
			// this.context.put("id2", id2);
			// this.context.put("id1", id1);
			// logic_A.setData(id);
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			headerppk_baru(session, id, "Y", "", "T");
			logic.setDataSimati(id);
			listSimati = logic.getDataSimati();
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

			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
			this.context.put("readmode", disability1);

			this.context.put("show_kemaskini_button", "yes");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");

			vm = "app/ppk/frmPrmhnnSek8Simati.jsp";

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);

		} else if ("Seterusnya".equals(submit)) {
			chkId = logic_AO.getId();
			view1 = logic_AO.getJenisKp();
			this.context.put("listkp", view1);
			view2 = logic_AO.getJenisKp();
			this.context.put("listkp2", view2);
			String id = getParam("idtemp");
			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			list2 = logic_AO.getData();
			logic_AO.setDataFail(id);
			listFail = logic_AO.getDataFail();
			this.context.put("ViewFail", listFail);
			Hashtable x = (Hashtable) listFail.get(0);
			String aa = x.get("no_fail").toString();
			String tempid = getParam("idtemp");
			this.context.put("IdPermohonan", tempid);
			long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
			this.context.put("IdPemohon", idPemohon);
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			this.context.put("View", list2);
			Hashtable h = (Hashtable) list2.get(0);
			this.context.put("IdSimati",
					Long.parseLong(h.get("idSimati").toString()));
			this.context.put("IdPemohon",
					Long.parseLong(h.get("idPemohon").toString()));
			if (aa != "") {
				int evenstatus = 1;
				int idflag = 2;
				int flagNo = 2;
				idAlert = "0";
				this.context.put("idFlag", idflag);
				this.context.put("flagno", flagNo);
				this.context.put("idAlert", idAlert);
				this.context.put("EventStatus", evenstatus);

			} else {
				this.context.put("", "");
				int evenstatus = 0;
				int idflag = 2;
				int flagNo = 1;
				idAlert = "0";
				this.context.put("idFlag", idflag);
				this.context.put("flagno", flagNo);
				this.context.put("idAlert", idAlert);
				this.context.put("EventStatus", evenstatus);

			}

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
			listDaerahByUser = logic_AO.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);
			this.context.put("tarikhmohondaftar", "");
			this.context.put("tarikhmohon", currentDate);
			String idp = tempid;
			logic_C.setDataSemakan(idp);
			listSemak = new Vector();
			listSemak = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak);
			String txtbox = getParam("txtNomborResit1");
			String tarikhresit = getParam("txdTarikhByrn1");
			this.context.put("txtNomborResit1", txtbox);
			this.context.put("txdTarikhByrn1", tarikhresit);
			logic_A.setDataFail(idp);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String idpp = getParam("idPermohonan");

			logic.setDataPemohonOB(idpp);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);
			/*
			logic_A.setSupportingDoc(idpp, "99201");
			listSupportingDoc = logic_A.setSupportingDoc(idpp, "99201");
			this.context.put("ViewSupportingDoc", listSupportingDoc);*/
			
			vm = "app/ppk/frmPraPrmhnnSek8DaftarSek8.jsp";

		} else if ("kembali_simati".equals(submit)) {
			view1 = logic_AO.getJenisKp();
			this.context.put("listkp", view1);
			this.context.put("tarikhmohon", currentDate);
			String id = getParam("idPermohonan");
			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			list2 = logic_AO.getData();

			logic_AO.setGetId(id);
			listIds = logic_AO.getIds();

			int idflag = 2;
			int flagNo = 2;
			eventStatus = 1;
			idAlert = "0";
			this.context.put("idFlag", idflag);
			this.context.put("flagno", flagNo);
			this.context.put("idAlert", idAlert);
			this.context.put("EventStatus", eventStatus);
			String tempid = getParam("idPermohonan");
			this.context.put("IdPermohonan", tempid);

			this.context.put("View", list2);
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			Hashtable h = (Hashtable) list2.get(0);

			/*
			 * if (getsocNegeri == "" || getsocNegeri == "0" ) {
			 * this.context.put("selectNegeri",
			 * HTML.SelectNegeri("socNegeri",null
			 * ,"class=\"disabled mediumselect\" disabled ")); } else {
			 * this.context.put("selectNegeri",
			 * HTML.SelectNegeri("socNegeri",Long
			 * .parseLong(getsocNegeri.toString()),"class=disabled")); }
			 */

			if (h.get("pmidnegeri").toString() == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,null,"class=\"autoselect\" disabled "));
			} else {
				this.context.put("negeri", h.get("pmidnegeri").toString());
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h
						.get("pmidnegeri").toString()));
				this.context.put("listBandarbyNegeri", s3);
				this.context.put("daerah", "");
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
			}

			if (h.get("idbandar").toString() == "") {

				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,null,"class=\"autoselect\" disabled "));
			} else {
				this.context.put("daerah", h.get("idbandar").toString());

				// this.context.put("daerah","");
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
			}
			/*
			 * this.context.put("listkp", view1);
			 * this.context.put("tarikhmohon", currentDate);
			 */
			listDaerahByUser = logic_AO.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");

			listxxx = logic_AO.getData();
			Hashtable kl = (Hashtable) listxxx.get(0);

			logic_AO.setDataFail(id);
			listFail = logic_AO.getDataFail();
			this.context.put("ViewFail", listFail);

			String id_Daerah = kl.get("idDaerah").toString();
			String tarikhMohonm = kl.get("tarikhMohon").toString();

			Hashtable k = (Hashtable) listIds.get(0);
			this.context.put("IdSimati",
					Long.parseLong(k.get("idsimati").toString()));
			this.context.put("IdPemohon",
					Long.parseLong(k.get("idpemohon").toString()));
			this.context.put("IdPermohonan", id);
			this.context.put("tarikhmohondaftar", tarikhMohonm);
			this.context.put("", "");

			// int idp=Integer.parseInt(tempid);
			// FrmPrmhnnSek8SenaraiSemakOnlineData frmPrmhnnSek8SenaraiSemakData
			// = new FrmPrmhnnSek8SenaraiSemakOnlineData();
			logic_C.setDataSemakan(id);
			listSemak = new Vector();
			listSemak = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak);

			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPraPrmhnnSek8DaftarSek8.jsp";

		} else if ("Kemaskini_daftar_pemohon".equals(submit)) {
			view1 = logic_AO.getJenisKp();
			this.context.put("listkp", view1);
			this.context.put("tarikhmohon", currentDate);
			String id = getParam("idPermohonan");
			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			list2 = logic_AO.getData();

			logic_AO.setGetId(id);
			listIds = logic_AO.getIds();

			int evenstatus = 0;
			int idflag = 2;
			int flagNo = 1;
			idAlert = "0";
			this.context.put("idFlag", idflag);
			this.context.put("flagno", flagNo);
			this.context.put("idAlert", idAlert);
			this.context.put("EventStatus", evenstatus);
			String tempid = getParam("idPermohonan");
			this.context.put("IdPermohonan", tempid);

			this.context.put("View", list2);
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			Hashtable h = (Hashtable) list2.get(0);

			if (h.get("pmidnegeri").toString() == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,null,"class=\"autoselect\" disabled "));
			} else {
				this.context.put("negeri", h.get("pmidnegeri").toString());
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h
						.get("pmidnegeri").toString()));
				this.context.put("listBandarbyNegeri", s3);
				this.context.put("daerah", "");
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
			}

			if (h.get("idbandar").toString() == "") {

				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,null,"class=\"autoselect\" disabled "));
			} else {
				this.context.put("daerah", h.get("idbandar").toString());

				// this.context.put("daerah","");
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
			}
			/*
			 * this.context.put("listkp", view1);
			 * this.context.put("tarikhmohon", currentDate);
			 */
			listDaerahByUser = logic_AO.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");

			listxxx = logic_AO.getData();
			Hashtable kl = (Hashtable) listxxx.get(0);

			String id_Daerah = kl.get("idDaerah").toString();
			String tarikhMohonm = kl.get("tarikhMohon").toString();

			Hashtable k = (Hashtable) listIds.get(0);
			this.context.put("IdSimati",
					Long.parseLong(k.get("idsimati").toString()));
			this.context.put("IdPemohon",
					Long.parseLong(k.get("idpemohon").toString()));
			this.context.put("IdPermohonan", id);
			this.context.put("tarikhmohondaftar", tarikhMohonm);
			this.context.put("", "");

			String txtbox = getParam("txtNomborResit1");
			String tarikhresit = getParam("txdTarikhByrn1");
			this.context.put("txtbox", txtbox);
			this.context.put("tarikhresit", tarikhresit);

			logic_AO.setDataFail(id);
			listFail = logic_AO.getDataFail();
			this.context.put("ViewFail", listFail);

			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPraPrmhnnSek8DaftarSek8.jsp";

		} else if ("Simpanx".equals(submit)) {
			System.out.println("****Simpanx***");
			String id = getParam("idPermohonan");
			String idppp = getParam("idPermohonan");
			logic_AO.checkData(id);
			chkId = logic_AO.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());
			String useridx = (String) session.getAttribute("_ekptg_user_id");
			logic_AO.setGetUserId(useridx);
			listUserid = logic_AO.getUserIds();
			Hashtable t = (Hashtable) listUserid.get(0);
			String userIdDaerah = t.get("idDaerah").toString();
			String userIdNeg = t.get("idNegeri").toString();
			String userIdKodDaerah = t.get("kodDaerah").toString();
			String userIdKodNegeri = t.get("kodnegeri").toString();
			String userIdPejabat = t.get("idpejabat").toString();
			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			listxxx = logic_AO.getData();
			Hashtable kl = (Hashtable) listxxx.get(0);
			String id_Daerah = kl.get("idDaerah").toString();

			String no_kpbaru_simati = getParam("txtNoKPBaruSimati1")
					+ getParam("txtNoKPBaruSimati2")
					+ getParam("txtNoKPBaruSimati3");
			String no_kplama_simati = getParam("txtNoKPLamaSimati");
			String sel_jeniskp_simati = getParam("socJenisKPLainSimati");
			String no_kplain_simati = getParam("txtNoKPLainSimati");

			/*if (logic_F.checkKPSimati(idppp, no_kpbaru_simati,
					no_kplama_simati, no_kplain_simati) == true) {
				myLogger.info("checkKPSimati=true");

				
				 * view1 = logic_A.getJenisKp(); this.context.put("listkp",
				 * view1);
				 

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
				k.put("idFail", getParam("id_Fail"));
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
				k.put("bandar", getParam("txtBandar"));
				k.put("idDaerah", getParam("socDaerahinput"));
				k.put("umursimati", getParam("txtUmurSimati"));
				k.put("jantinasimati", getParam("socJantinaSimati"));
				k.put("umurpemohon", getParam("txtUmurPemohon"));
				k.put("jantinapemohon", getParam("socJantinaPemohon"));

				k.put("socSaudaraWaris", getParam("socSaudaraWaris"));

				k.put("taraf_penting", getParam("taraf_penting"));
				k.put("no_tel", getParam("no_tel"));
				k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
				k.put("jenis_pemohon", getParam("jenis_pemohon"));
				k.put("jenis_pej", getParam("jenis_pej"));
				k.put("no_hp", getParam("no_hp"));

				v.addElement(k);
				this.context.put("View", v);

				String IdNeg = getParam("negid");
				this.context.put("NegId", IdNeg);
				String negri = getParam("socNegeri");
				if (getParam("socNegeri") == "") {
					this.context.put("negeri", "");
				} else {
					this.context.put("negeri", getParam("socNegeri"));
					Vector s2 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeri")));
					this.context.put("listBandarbyNegeri", s2);
				}

				if (getParam("socBandar") == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", getParam("socBandar"));
				}

				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				String idm = getParam("idPermohonan");
				logic_A.setGetId(idm);
				listIds = logic_A.getIds();
				Hashtable kx = (Hashtable) listIds.get(0);
				this.context.put("IdSimati",
						Long.parseLong(kx.get("idsimati").toString()));
				this.context.put("IdPemohon",
						Long.parseLong(kx.get("idpemohon").toString()));
				this.context.put("IdPermohonan", getParam("idPermohonan"));

				this.context.put("duplicate", "yes");
*/
			//} else {
				myLogger.info("else");
				bolehsimpan = "yes";
				if (bolehsimpan.equals("yes")) {
					myLogger.info("bolehsimpan = yes");
					updatePermohonan(session, userIdNeg, userIdPejabat,
							userIdKodDaerah, userIdKodNegeri, id_Daerah);
				}
				logic_AO.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				listxxx4 = logic_AO.getData();
				Hashtable k4 = (Hashtable) listxxx4.get(0);
				String tarikhMohonm = k4.get("tarikhMohon").toString();
				view1 = logic_AO.getJenisKp();
				this.context.put("listkp", view1);
				idFlag = 2;
				flag_no = 2;
				eventStatus = 1;
				idAlert = "0";
				this.context.put("flagno", flag_no);
				this.context.put("idFlag", idFlag);
				this.context.put("EventStatus", eventStatus);
				this.context.put("idAlert", idAlert);
				this.context.put("tarikhmohon", getParam("tarikh_daftar"));
				logic_AO.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				list2 = logic_AO.getData();
				this.context.put("View", list2);
				String getsocNegeri = getParam("socNegeri");
				Hashtable h = (Hashtable) list2.get(0);
				if (h.get("pmidnegeri").toString() == "") {
					this.context.put("negeri", "");
					this.context.put("daerah", "");
				} else {
					this.context.put("negeri", h.get("pmidnegeri").toString());
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("pmidnegeri").toString()));
					this.context.put("listBandarbyNegeri", s3);
				}
				if (h.get("idbandar").toString() == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", h.get("idbandar").toString());
				}
				logic_AO.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				logic_AO.setGetId(id);
				listIds = logic_AO.getIds();
				Hashtable m = (Hashtable) listIds.get(0);
				this.context.put("IdSimati",
						Long.parseLong(m.get("idsimati").toString()));
				this.context.put("IdPemohon",
						Long.parseLong(m.get("idpemohon").toString()));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
				listDaerahByUser = logic_AO
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				this.context.put("tarikhmohondaftar", "");
				this.context.put("", "");
				this.context.put("tarikhmohon", tarikhMohonm);

			//}
			String ido = getParam("idPermohonan");
			logic_A.setDataFail(ido);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			logic.setDataPemohonOB(ido);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPraPrmhnnSek8DaftarSek8.jsp";

		} else if ("getBandar".equals(submit)) {
			String IdPermohonan = getParam("idPermohonan");
			String Idppp = getParam("idpermohonan");
			// System.out.println("Idppp Duplicate" + Idppp);
			String idmm = getParam("idPermohonan");
			logic_A.checkData(idmm);
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
			/*
			 * view1 = logic_A.getJenisKp(); this.context.put("listkp", view1);
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
			k.put("idFail", getParam("id_Fail"));
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
			k.put("idDaerah", getParam("socDaerahinput"));
			k.put("umursimati", getParam("txtUmurSimati"));
			k.put("jantinasimati", getParam("socJantinaSimati"));
			k.put("umurpemohon", getParam("txtUmurPemohon"));
			k.put("jantinapemohon", getParam("socJantinaPemohon"));

			k.put("taraf_penting", getParam("taraf_penting"));
			k.put("no_tel", getParam("no_tel"));
			k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
			k.put("jenis_pemohon", getParam("jenis_pemohon"));
			k.put("jenis_pej", getParam("jenis_pej"));
			// h.put("adaob",getParam("adaob"));
			k.put("no_hp", getParam("no_hp"));
			k.put("socSaudaraWaris", getParam("socSaudaraWaris"));

			v.addElement(k);
			this.context.put("View", v);
			String id = tempid;
			// list2 =
			// logic_A.setData(id,(String)session.getAttribute("_ekptg_user_id"));
			// this.context.put("View", list2);
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			// Hashtable h = (Hashtable)list2.get(0);
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
			logic_A.setGetId(id);
			listIds = logic_A.getIds();
			Hashtable kx = (Hashtable) listIds.get(0);
			this.context.put("IdSimati",
					Long.parseLong(kx.get("idsimati").toString()));
			this.context.put("IdPemohon",
					Long.parseLong(kx.get("idpemohon").toString()));
			this.context.put("IdPermohonan", getParam("idPermohonan"));
			this.context.put("duplicate", "");
			String ido = getParam("idPermohonan");
			logic_A.setDataFail(ido);
			headerppk_baru(session, ido, "Y", "", "T");
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			logic.setDataPemohonOB(ido);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPraPrmhnnSek8DaftarSek8.jsp";

		}

		// ketabs
		else if ("seterusnya".equals(submit)) {
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";

			String id = getParam("idPermohonan");
			String id2 = getParam("idPemohon");
			String id1 = getParam("idSimati");

			this.context.put("id", id);
			this.context.put("id2", id2);
			this.context.put("id1", id1);

			this.context.put("readmode", disability1);
			this.context.put("show_kemaskini_button", "yes");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);

			logic.setDataSimati(id);
			listSimati = logic.getDataSimati();
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

			logic_AO.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			list = logic_AO.getData();

			this.context.put("View", list);

			vm = "app/ppk/frmPrmhnnSek8Simati.jsp";

		}
		// simati

		else if ("Simati".equals(submit)) {
			if (doPost.equals("true")) {
				// System.out.println("browser true **********");
				bolehsimpan = "yes";
			} else {
				// System.out.println("browser false **********");
			}
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
			} else if ("batal_simati".equals(mode)) {
				this.context.put("show_simpan_button", "yes");
			} else if ("kemaskini_simati".equals(mode)) {
				this.context.put("show_simpan_button", "yes");
				this.context.put("show_batal_button", "yes");
			} else if ("simpan_simati".equals(mode)) {
				disability1 = "disabled";
				if (bolehsimpan.equals("yes")) {
					updatesimati(session);
				}
				this.context.put("readmode", disability1);
				this.context.put("show_kemaskini_button", "yes");
				logic_internal.setDataSimati(id);
				listSimati = logic_internal.getDataSimati();
				this.context.put("listSimati", listSimati);
				Hashtable h2 = (Hashtable) listSimati.get(0);
				if (h2.get("idnegeri").toString() != ""
						&& h2.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h2.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
			} else if ("getBandar".equals(mode)) {
				Hashtable h = new Hashtable();
				v = new Vector();
				h.put("idPermohonan", getParam("idPermohonanp"));
				h.put("idSimati", getParam("idSimati"));
				h.put("noKpBaru1", getParam("txtNoKPBaru1Simati"));
				h.put("noKpBaru2", getParam("txtNoKPBaru2Simati"));
				h.put("noKpBaru3", getParam("txtNoKPBaru3Simati"));
				h.put("noKpLama", getParam("txtNoKPLamaSimati"));
				h.put("jenisKp", getParam("socJenisKPLainSimati"));
				h.put("noKpLain", getParam("txtNoKPLainSimati"));
				h.put("namaSimati", getParam("txtNamaSimati"));
				h.put("namaLain", getParam("txtNamaLainSimati"));
				h.put("idPemohon", getParam("idPemohon"));
				h.put("masamati", getParam("socWaktuKematianSimati"));
				h.put("tarikhMati", getParam("txdTarikhMatiSimati"));
				h.put("jantina", getParam("socJantinaSimati"));
				h.put("jenisWarga", getParam("socWarganegaraSimati"));
				h.put("jenisAgama", getParam("socAgamaSimati"));
				h.put("umur", getParam("txtUmurSimati"));
				h.put("idBuktimati", getParam("socBuktiKematianSimati"));
				h.put("tempatMati", getParam("txtTempatMatiSimati"));
				h.put("noSijilMati", getParam("txtNoSijilMatiSimati"));
				h.put("masaMati", getParam("socWaktuKematianSimati"));
				h.put("sebabMati", getParam("txtSebabKematianSimati"));
				h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
				h.put("alamat1", getParam("txtAlamatTerakhir1Simati"));
				h.put("alamat2", getParam("txtAlamatTerakhir2Simati"));
				h.put("alamat3", getParam("txtAlamatTerakhir3Simati"));
				h.put("poskod", getParam("txtPoskodSimati"));
				h.put("jeniswaktu", getParam("jeniswaktu"));
				h.put("bandar", getParam(""));
				h.put("bandartetap", "");
				h.put("idnegeri", getParam("socNegeriSimati"));
				h.put("catatan", getParam("txtCatatanSimati"));

				h.put("tarikhLahirSimati", getParam("tarikhLahirSimati"));
				v.addElement(h);
				this.context.put("listSimati", v);
				if (getParam("socNegeriSimati") != ""
						&& getParam("socNegeriSimati") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriSimati")));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}
				this.context.put("show_simpan_button", "yes");
				this.context.put("show_batal_button", "yes");
			}
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			String idm = getParam("idPermohonan");
			list = logic_A.setData(idm,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idm, "Y", "", "T");
			this.context.put("View", list);
			vm = "app/ppk/frmPrmhnnSek8Simati.jsp";
		} else if ("Pemohon".equals(submit)) {
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
				logic_internal.setCheckPeguam(id);
				listCheckPeguam = logic_internal.getCheckPeguam();
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
				logic_internal.setCheckPeguam(id);
				listCheckPeguam = logic_internal.getCheckPeguam();
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
				logic_internal.setCheckPeguam(id);
				listCheckPeguam = logic_internal.getCheckPeguam();
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

					k.put("alamat1Surat", getParam("txtAlamatTerakhir1Pemohon"));
					k.put("alamat2Surat", getParam("txtAlamatTerakhir2Pemohon"));
					k.put("alamat3Surat", getParam("txtAlamatTerakhir3Pemohon"));
					k.put("poskodSurat", getParam("txtPoskodPemohon"));
					k.put("bandarsurat", id_bandartetap);
					k.put("idnegeriSurat", getParam("socNegeriPemohon"));

					k.put("nama_pelbagainegara",
							getParam("nama_pelbagainegara"));
					k.put("nama_pelbagainegara_surat",
							getParam("nama_pelbagainegara"));

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

					k.put("nama_pelbagainegara",
							getParam("nama_pelbagainegara"));
					k.put("nama_pelbagainegara_surat", "");

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

					k.put("nama_pelbagainegara",
							getParam("nama_pelbagainegara"));
					k.put("nama_pelbagainegara_surat",
							getParam("nama_pelbagainegara_surat"));

				}

				k.put("catatan", getParam("txtCatatanPemohon"));
				k.put("adataraf", getParam("adataraf"));
				k.put("namanegeri", getParam("socNegeriPemohon"));
				v.addElement(k);

				this.context.put("listPemohon", v);
				logic_internal.setCheckPeguam(id);
				listCheckPeguam = logic_internal.getCheckPeguam();
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

				if (bolehsimpan.equals("yes")) {
					updatepemohon(session);
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

				logic_internal.setCheckPeguam(id);
				listCheckPeguam = logic_internal.getCheckPeguam();
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
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			// list = logic_A.getData();
			this.context.put("View", list);
			vm = "app/ppk/frmPrmhnnSek8Pemohon.jsp";
		}

		else if ("Peguam".equals(submit)) {

			String id = getParam("idPermohonan");
			String id2 = getParam("idPemohon");
			logic_internal.setCheckPeguam(id);
			listCheckPeguam = logic_internal.getCheckPeguam();
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
				/*
				 * int id = Integer.parseInt(getParam("idPermohonan")); int id2
				 * = Integer.parseInt(getParam("idPemohon")); int mati =
				 * Integer.parseInt(getParam("id_Permohonansimati"));
				 * logic_internal.setDataPeguam(id2);
				 * listpeguam=logic_internal.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			}

			else if ("peguam_baru".equals(mode)) {
				/*
				 * int id = Integer.parseInt(getParam("idPermohonan")); int id2
				 * = Integer.parseInt(getParam("idPemohon"));
				 * logic_internal.setCheckPeguam(id); listCheckPeguam =
				 * logic_internal.getCheckPeguam();
				 * this.context.put("listCheckPeguam", listCheckPeguam);
				 * 
				 * logic_internal.setDataPeguam(id2);
				 * listpeguamcheck=logic_internal.getDataPeguam();
				 * logic_internal.setDataPeguam(id2);
				 * listpeguam=logic_internal.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

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
			} else if ("getBandar".equals(mode)) {

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
				/*
				 * int id = Integer.parseInt(getParam("idPermohonan")); int id2
				 * = Integer.parseInt(getParam("idPemohon"));
				 */
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
				/*
				 * logic_internal.setDataPeguam(id2);
				 * listpeguam=logic_internal.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

				this.context.put("show_simpan_button", "yes");
				this.context.put("button_tambah", "yes");
				this.context.put("add_new_peguam", "update");

			}

			else if ("GetPeguam".equals(mode)) {
				/*
				 * int id = Integer.parseInt(getParam("idPermohonan")); int id2
				 * = Integer.parseInt(getParam("idPemohon"));
				 */
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

				/*
				 * logic_internal.setDataPeguam(id2);
				 * listpeguam=logic_internal.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

				this.context.put("button_tambah", "yes");
				this.context.put("readmode", "disabled");
				this.context.put("add_new_peguam", "update");
				this.context.put("show_kemaskini_button", "yes");

			}

			else if ("tambah_peguam".equals(mode)) {
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
				// String id = Integer.parseInt(getParam("idPermohonan"));
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

				if (bolehsimpan.equals("yes")) {
					updatepeguam(session);
				}

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
			list = logic_A.setData(idX,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idX, "Y", "", "T");
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 1);
			this.context.put("selectedTabbawah", 1);
			this.context.put("selectedTabtepi", 0);
			this.context.put("View", list);
			vm = "app/ppk/frmPrmhnnSek8Peguam.jsp";

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
			} else if ("Newwaris".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String mati = getParam("idSimati");
				String id_Permohonansimati = getParam("id_Permohonansimati");
				this.context.put("show_batal_waris", "yes");
				this.context.put("buttonwarisSimpan", "Simpan");
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("addnew", "yes");
				this.context.put("show_table_waris", "yes");
				this.context.put("listBandarTetapbyNegeri", "");
				this.context.put("listBandarSuratbyNegeri", "");
				this.context.put("check_copy", "");
				this.context.put("check_copyP", "");
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
					this.context.put("nama_pelbagainegara_surat",
							getParam("nama_pelbagainegara"));

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
				list = logic_A.setData(id,
						(String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				// list = logic_A.getData();

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

					this.context.put("nama_pelbagainegara",
							x.get("nama_pelbagainegara").toString());
					this.context.put("nama_pelbagainegara_surat",
							x.get("nama_pelbagainegara_surat").toString());

					this.context.put("txtAlamatTerakhir1WarisSurat",
							x.get("alamat1surat").toString());
					this.context.put("txtAlamatTerakhir2WarisSurat",
							x.get("alamat2surat").toString());
					this.context.put("txtAlamatTerakhir3WarisSurat",
							x.get("alamat3surat").toString());
					this.context.put("txtPoskodWarisSurat", x
							.get("poskodsurat").toString());
					this.context.put("txtBandarWarisSurat",
							x.get("idbandarsurat").toString());
					this.context.put("socNegeriWarisSurat",
							x.get("idnegerisurat").toString());

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
				if (bolehsimpan.equals("yes")) {
					updatewaris(session);
				}
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
				if (bolehsimpan.equals("yes")) {
					updatewaris(session);
				}
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

			logic_internal.setDataWarisOB(id_Permohonansimati);
			listWarisOB = logic_internal.getDataWarisOB();
			this.context.put("listWarisOB", listWarisOB);

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 2);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			// list = logic_A.getData();
			this.context.put("View", list);
			Vector list_getListOBUpdate = null;
			list_getListOBUpdate = logic_A.getListOBUpdate(id_Permohonansimati);
			this.context.put("list_getListOBUpdate", list_getListOBUpdate);
			/*
			 * Vector kenegaraan = null; kenegaraan = mainheader.kenegaraan();
			 * this.context.put("kenegaraan",kenegaraan);
			 */

			vm = "app/ppk/frmPrmhnnSek8Waris.jsp";
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
			} else if ("tambah_penting".equals(mode)) {
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
					this.context.put("nama_pelbagainegara_surat",
							getParam("nama_pelbagainegara"));

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
					this.context.put("nama_pelbagainegara_surat",
							getParam("nama_pelbagainegara"));

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
					if (bolehsimpan.equals("yes")) {
						updatepenghutang(session);
					}
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
					if (bolehsimpan.equals("yes")) {
						updatepenting(session);
					}
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
			logic_internal.setDataPenghutang(id_Permohonansimati);
			listPenghutang = logic_internal.getDataPenghutang();
			this.context.put("listPenting", listPenghutang);

			this.context.put("selectedTabatas", 0);

			if ("Penting".equals(submit)) {
				this.context.put("selectedTabtengah", 3);
				this.context.put("listPenting", listPenting);
				vm = "app/ppk/FrmPrmhnnSek8OrgBerkepentingan.jsp";
			} else if ("Saksi".equals(submit)) {
				this.context.put("selectedTabtengah", 4);
				this.context.put("listPenting", listPenting);
				vm = "app/ppk/frmPrmhnnSek8Saksi.jsp";
			} else if ("Pemiutang".equals(submit)) {
				this.context.put("selectedTabtengah", 5);
				this.context.put("listPenting", listPenting);
				vm = "app/ppk/frmPrmhnnSek8Pemiutang.jsp";
			} else if ("Penghutang".equals(submit)) {
				this.context.put("selectedTabtengah", 6);
				this.context.put("listPenting", listPenghutang);
				vm = "app/ppk/frmPrmhnnSek8Penghutang.jsp";

			}
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			String id = getParam("idPermohonan");
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

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
				String mati = getParam("id_Permohonansimati");
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
			}

			else if ("add_new".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));
			} else if ("negerichange".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
			} else if ("daerahchange".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaam"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
			} else if ("checkWujudLot".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				Hashtable getLot = null;
				getLot = mainheader.getWujudLot(mati, getParam("txtNoPTHtaam"),
						getParam("socNegeriHtaam"), getParam("socDaerahHtaam"),
						getParam("socMukimHtaam"),
						getParam("socJenisHakmilikHtaam"),
						getParam("txtNoHakmilikHtaam"),
						getParam("txtNoPTHtaam"));
				this.context.put("nopt", getParam("txtNoPTHtaam"));
				myLogger.info("---- hashtable get LOT :"
						+ (String) getLot.get("ID_HTA"));

				if ((String) getLot.get("ID_HTA") != null) {
					this.context.put("noHakmilik",
							(String) getLot.get("NO_HAKMILIK"));
					this.context.put("idSimati",
							(String) getLot.get("ID_SIMATI"));
					this.context.put("nilai_Hta_memohon",
							(String) getLot.get("NILAI_HTA_TARIKHMOHON"));
					this.context.put("nilai_Hta_mati",
							(String) getLot.get("NILAI_HTA_TARIKHMATI"));
					this.context.put("kategori",
							(String) getLot.get("ID_KATEGORI"));
					this.context.put("jenishakmilik",
							(String) getLot.get("ID_JENISHM"));
					this.context.put("pemilikan",
							(String) getLot.get("STATUS_PEMILIKAN"));
					this.context
							.put("luashmp", (String) getLot.get("LUAS_HMP"));
					this.context.put("luasasal", (String) getLot.get("LUAS"));
					this.context.put("nopajakan",
							(String) getLot.get("NO_PAJAKAN"));
					this.context.put("jenistanah",
							(String) getLot.get("JENIS_TNH"));
					this.context.put("basimati",
							(String) getLot.get("BA_SIMATI"));
					this.context.put("bbsimati",
							(String) getLot.get("BB_SIMATI"));
					this.context.put("tanggungan",
							(String) getLot.get("TANGGUNGAN"));
					this.context.put("jenisluas",
							(String) getLot.get("ID_LUAS"));
					this.context.put("catatan", (String) getLot.get("CATATAN"));
					this.context.put("noperserahan",
							(String) getLot.get("NO_PERSERAHAN"));
					this.context.put("FLAG_DAFTAR",
							(String) getLot.get("FLAG_DAFTAR"));
					if (!((String) getLot.get("ID_NEGERI").toString())
							.equals("")) {
						int idnegeri = Integer.parseInt((String) getLot
								.get("ID_NEGERI"));
						listnegeribydaerah = logic_A
								.getListDaerahbyNegeri(idnegeri);
						this.context.put("listDaerahbyNegeri",
								listnegeribydaerah);
						this.context.put("negeri", idnegeri);
					} else {
						this.context.put("listDaerahbyNegeri", "");
						this.context.put("negeri", "");
					}
					if (!((String) getLot.get("ID_DAERAH").toString())
							.equals("")) {
						int iddaerah = Integer.parseInt((String) getLot
								.get("ID_DAERAH"));
						listmukim = logic_A.getListMukimbyDaerah(iddaerah);
						this.context.put("listMukimbyDaerah", listmukim);
						this.context.put("daerah", iddaerah);
					} else {
						this.context.put("listMukimbyDaerah", "");
						this.context.put("daerah", "");
					}
					if (!((String) getLot.get("ID_MUKIM").toString())
							.equals("")) {
						int idmukim = Integer.parseInt((String) getLot
								.get("ID_MUKIM"));
						this.context.put("mukim", idmukim);
					} else {
						this.context.put("mukim", "");
					}
					this.context.put("CheckWujudLot", "Y");
				} else {
					if (!getParam("socNegeriHtaam").equals("")) {
						int idnegeri = Integer
								.parseInt(getParam("socNegeriHtaam"));
						listnegeribydaerah = logic_A
								.getListDaerahbyNegeri(idnegeri);
						this.context.put("listDaerahbyNegeri",
								listnegeribydaerah);
						this.context.put("negeri", idnegeri);
					} else {
						this.context.put("listDaerahbyNegeri", "");
						this.context.put("negeri", "");
					}
					if (!getParam("socDaerahHtaam").equals("")) {
						int iddaerah = Integer
								.parseInt(getParam("socDaerahHtaam"));
						listmukim = logic_A.getListMukimbyDaerah(iddaerah);
						this.context.put("listMukimbyDaerah", listmukim);
						this.context.put("daerah", iddaerah);
					} else {
						this.context.put("listMukimbyDaerah", "");
						this.context.put("daerah", "");
					}
					if (!getParam("socMukimHtaam").equals("")) {
						int idmukim = Integer
								.parseInt(getParam("socMukimHtaam"));
						this.context.put("mukim", idmukim);
					} else {
						this.context.put("mukim", "");
					}
					this.context.put("noHakmilik",
							getParam("txtNoHakmilikHtaam"));
					this.context.put("idSimati", getParam("idSimati"));
					// this.context.put("nopt", getParam("txtNoPTHtaam"));
					this.context.put("nilai_Hta_memohon",
							getParam("txtNilaiTarikhMohonHtaa"));
					this.context.put("nilai_Hta_mati",
							getParam("txtNilaiTarikhMatiHtaam"));
					this.context.put("kategori",
							getParam("socKategoriTanahHtaam"));
					this.context.put("jenishakmilik",
							getParam("socJenisHakmilikHtaam"));
					this.context.put("pemilikan",
							getParam("socStatusPemilikanHtaam"));
					this.context.put("luashmp", getParam("txtLuasHMpHtaam"));
					this.context.put("luasasal", getParam("txtLuasAsalHtaam"));
					this.context.put("nopajakan", getParam("txtNoPajakan"));
					this.context.put("jenistanah",
							getParam("socJenisTanahHtaam"));
					this.context
							.put("basimati", getParam("txtBahagianSimati1"));
					this.context
							.put("bbsimati", getParam("txtBahagianSimati2"));
					this.context.put("tanggungan",
							getParam("txtTanggunganHtaam"));
					this.context
							.put("jenisluas", getParam("socJenisLuasHtaam"));
					this.context.put("catatan", getParam("txtCatatanHtaam"));
					this.context
							.put("noperserahan", getParam("txtNoPersHtaam"));
					this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
					this.context.put("CheckWujudLot", "T");
				}

				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");
			} else if ("negerichangeup".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
				// Tukar Status
				String mati = getParam("id_Permohonansimati");
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");

				String id = getParam("idPermohonan");
				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");

				if (bolehsimpan.equals("yes")) {
					// :::SUB
					// ID_STATUS : 9
					// ID_SUBURUSAN : 342
					// logic_A.htaamstatus(id, (String)
					// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
					logic_A.kemaskiniSubUrusanStatusFail(session, id,
							(String) session.getAttribute("_ekptg_user_id"),
							"9", "342", id_Fail);
				}
				/*
				 * String idhtaam = Integer.parseInt(getParam("idhtaamid")); int
				 * mati = Integer.parseInt(getParam("id_Permohonansimati"));
				 * String id = Integer.parseInt(getParam("idPermohonan"));
				 * String id_sub = Integer
				 * .parseInt(getParam("id_Suburusanstatusfail")); String id_Fail
				 * = Integer.parseInt(getParam("id_Fail"));
				 * 
				 * if (bolehsimpan.equals("yes")) { logic_A.htaamstatus(id,
				 * (String) session .getAttribute("_ekptg_user_id"), id_sub,
				 * id_Fail); } logic_internal.setDataHTAbyIdHtaam(idhtaam);
				 * listHTAid = logic_internal.getDataHTAbyIdHtaam();
				 * logic_internal.setDataHTA(mati); listHTA =
				 * logic_internal.getDataHTA(); this.context.put("listHTA",
				 * listHTA); this.context.put("idhtaam", idhtaam);
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
				String mati = getParam("id_Permohonansimati");
				logic_internal.deleteHtaam(session, idhtaam,
						getParam("id_Permohonansimati"));
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");
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
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
				logic_internal.setDataHTA(mati);
				listHTA = logic_internal.getDataHTA();
				this.context.put("listHTA", listHTA);
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
				String id = getParam("idPermohonan");
				if (bolehsimpan.equals("yes")) {
					logic_A.updateDataNilai(id, mati,
							(String) session.getAttribute("_ekptg_user_id"));
				}
			}

			this.context.put("selectedTabatas", 1);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			String id = getParam("idPermohonan");
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati,
					(String) session.getAttribute("_ekptg_user_id"));
			vm = "app/ppk/frmPrmhnnSek8HTAAH.jsp";
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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

			}

			if ("add_new".equals(mode)) {
				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				String radioX1 = getParam("radioHtaamViewX1");
				String radioX2 = getParam("radioHtaamViewX2");
				String radioX3 = getParam("radioHtaamViewX3");
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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

				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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

				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

				String id = getParam("idPermohonan");
				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

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

				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
				h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaaX"));
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
				h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
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

				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
				h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaaX"));
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
				h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
				h.put("nilai_Hta_memohon", getParam("txtNilaiTarikhMohonHtaaX"));
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
				h.put("tarikhperjanjian", getParam("txtTarikhPerjanjianHtaamX"));
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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");
				if (bolehsimpan.equals("yes")) {
					// :::SUB
					// ID_STATUS : 9
					// ID_SUBURUSAN : 342
					// logic_A.htaamstatus(id, (String)
					// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
					logic_A.kemaskiniSubUrusanStatusFail(session, id,
							(String) session.getAttribute("_ekptg_user_id"),
							"9", "342", id_Fail);

				}

			}

			else if ("getHtaamX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");
				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
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
					// :::SUB
					// ID_STATUS : 9
					// ID_SUBURUSAN : 342
					// logic_A.htaamstatus(id, (String)
					// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
					logic_A.kemaskiniSubUrusanStatusFail(session, id,
							(String) session.getAttribute("_ekptg_user_id"),
							"9", "342", id_Fail);
				}

				logic_internal.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic_internal.getDataHTAXbyIdHtaam();
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

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
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
				// String idhtaam=Integer.parseInt(getParam("idhtaamXid"));
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

				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
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
				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

			} else if ("daerahchangeX".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				logic_internal.setDataHTAX(mati);
				listHTAX = logic_internal.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

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
			// logic_A.setData(id);
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			// String id = Integer.parseInt(getParam("idPermohonan"));
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati,
					(String) session.getAttribute("_ekptg_user_id"));

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			context.put("DATEUTIL", new DateUtil());
			vm = "app/ppk/FrmPrmhnnSek8HTATH.jsp";

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
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();
				listppkhta = logic_B.getDataHta();
				logic_B.setDataHta(id);
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				logic_B.setDataHa(id);
				listppkha2 = logic_B.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				this.context
						.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

				this.context.put("showbuttonkembali", "yes");
				this.context.put("showbuttontambah", "yes");
				this.context.put("EventStatus", 1);

			}

			else if ("tambah_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");

				socJenisha = getParam("socJenisHartaAlih");
				this.context.put("socJenisHa", socJenisha);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();
				listppkhta = logic_B.getDataHta();
				logic_B.setDataHta(id);
				this.context.put("listhta2", listppkhta);
				int countListhta = listppkhta.size();
				this.context.put("jumListhta2", countList);

				logic_B.setDataHa(id);
				listppkha2 = logic_B.getHa2();
				this.context.put("listHa2", listppkha2);
				int countListha2 = listppkha2.size();
				this.context.put("jumListHa2", countListha2);

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("socJenisHa", getParam("socJenisHartaAlih"));
				this.context.put("norujukan", "");
				this.context.put("nosijil", "");
				this.context.put("bilunit", "");
				this.context.put("nilaiseunit", "");
				this.context.put("agensi", "");
				this.context.put("nama_saham", "");
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
				// ::
				this.context.put("butiran", getParam("butiran"));

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

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

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

				this.context.put("txtPoskod", "");
				this.context.put("nama_saham", "");
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("bhgnmati1", "");
				this.context.put("bhgnmati2", "");
				this.context.put("nilaitarikhmati", "");
				this.context.put("nilaitarikhmohon", "");
				this.context.put("catatan", "");
				this.context.put("butiran", "");
				this.context.put("readmodedaerah", "");

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
				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

				this.context.put("showbuttonkembali", "yes");
				this.context.put("showbuttontambah", "yes");
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", eventstatus);
				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);

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

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

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
				String idha = "0";
				idha = getParam("idha");

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);

				logic_A.setSelectedDataHa(mati, idha);
				selectedppkha = logic_A.getSelectedDataHa();
				this.context.put("DataHa", selectedppkha);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

				listdaerah = logic_A.getListDaerah();
				this.context.put("listDaerahbyNegeri", listdaerah);

				this.context.put("showbuttontambah", "yes");
				this.context.put("showadd", "1");
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", eventstatus);

			} else if ("kemaskini_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");

				String idha = "0";
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

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);
				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

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

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);
				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

				this.context.put("id", id);
				this.context.put("id1", id1);
				this.context.put("id2", id2);
				this.context.put("idha", idha);
				this.context.put("tutup", "yes");
				this.context.put("EventStatus", eventstatus);
				this.context.put("showbuttontambah", "yes");
				this.context.put("showadd", "1");

			} else if ("hapus_harta".equals(mode)) {

				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");
				String eventstatus = getParam("eventStatus");
				String idha = getParam("idha");
				delete_mode_ppkha(session, mati, idha);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

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
				this.context.put("nilaitarikhmati",
						getParam("txtNilaiTarikhMati"));
				this.context.put("nilaitarikhmohon",
						getParam("txtNilaiTarikhMohon"));
				this.context.put("catatan", getParam("txtCatatan"));
				this.context.put("butiran", getParam("butiran"));
				this.context.put("nama_saham", getParam("nama_saham"));

				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("EventStatus", 0);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

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

				// h.put("negeri", idnegeri);
				// h.put("daerah", getParam("socDaerahHtaam"));
				h.put("basimati", getParam("txtBhgnSimati1"));
				h.put("bbsimati", getParam("txtBhgnSimati2"));
				h.put("nilaiha_tarikhmati", getParam("txtNilaiTarikhMati"));
				h.put("nilaiha_tarikhmohon", getParam("txtNilaiTarikhMohon"));
				h.put("catatan", getParam("txtCatatan"));
				h.put("nama_saham", getParam("nama_saham"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
				h.put("butiran", getParam("butiran"));
				listabc.addElement(h);

				this.context.put("DataHa", listabc);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setOverallSum(mati);
				overallnilai = logic_A.getOverallSum();
				this.context.put("overall", overallnilai);

				logic_A.setOverallSumMati(mati);
				overallnilaimati = logic_A.getOverallSumMati();
				this.context.put("overallmati", overallnilaimati);

				listJenisha = logic_A.getJenisHa();
				this.context.put("ViewJenisHa", listJenisha);

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
			// logic_A.setData(id);
			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			// String id = Integer.parseInt(getParam("idPermohonan"));
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati,
					(String) session.getAttribute("_ekptg_user_id"));

			vm = "app/ppk/frmPrmhnnSek8HA.jsp";
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

				id1 = getParam("idSimati");
				if (bolehsimpan.equals("yes")) {
					logic_A.updateDataNilai(id, mati,
							(String) session.getAttribute("_ekptg_user_id"));
				}

				this.context.put("id", id);
				this.context.put("id2", id2);
				this.context.put("id1", id1);

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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

				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();
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

				updateStatus(session);
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
										HtaNilaiTarikhMati[i],
										String.valueOf(mati),
										String.valueOf(id));
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
										HaNilaiTarikhMati[i],
										String.valueOf(mati),
										String.valueOf(id));
							}
						}
					}
				}
				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
				int countList = listppkha.size();
				this.context.put("jumList", countList);

				logic_A.setSumDataHa(mati);
				sumppkha = logic_A.getSumDataHa();
				this.context.put("jumppkha", sumppkha);

				// FrmPrmhnnSek8SenaraiHTATHInternalData
				// frmPrmhnnSek8SenaraiHTATHInternalData = new
				// FrmPrmhnnSek8SenaraiHTATHInternalData();

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
										HtaNilaiTarikhMati[i],
										String.valueOf(mati),
										String.valueOf(id));
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
										HaNilaiTarikhMati[i],
										String.valueOf(mati),
										String.valueOf(id));
							}
						}
					}
				}
				logic_A.updateDataNilai(id, mati,
						(String) session.getAttribute("_ekptg_user_id"));

				logic_A.setSumDataHta(mati);
				sumppkhta = logic_A.getSumDataHta();
				this.context.put("jumppkhta", sumppkhta);

				logic_A.setOverallSumHta(mati);
				sumoverallppkhta = logic_A.getOverallSumHta();
				this.context.put("jumoverallppkhta", sumoverallppkhta);

				logic_A.setDataHa(mati);
				listppkha = logic_A.getDataHa();
				this.context.put("listHa", listppkha);
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
			String id = getParam("idPermohonan");

			list = logic_A.setData(id,
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");

			this.context.put("View", list);
			context.put("DATEUTIL", new DateUtil());
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			this.context.put("selectedTabatas", 3);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			// String id = Integer.parseInt(getParam("idPermohonan"));
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati,
					(String) session.getAttribute("_ekptg_user_id"));

			vm = "app/ppk/frmPrmhnnSek8NilaianHarta.jsp";

		} else if (("next".equals(submit)) || ("previous".equals(submit))) {
			
			
			
			
			
			if (getParam("subcommand").equals("lebih")) {
				list = logic_E.getList(String.valueOf(session
						.getAttribute("_ekptg_user_id")));
				onlineLebih = getParam("subcommand");

			} else {
				list = logic_E.setList(String.valueOf(session
						.getAttribute("_ekptg_user_id")));
				onlineLebih = "";

			}
			session.setAttribute("Senaraifail", list);
			prepareItemForDisplay(session, list, 25, submit);
			vm = "app/ppk/frmSenaraiPermohonanOnline.jsp";

		} else if ("Carian".equals(submit)) {
			myLogger.info("Carian" + getParam("command") + ","
					+ getParam("subcommand"));
			String usid = (String) session.getAttribute("_ekptg_user_id");
			String noFail = getParam("txtRujukanOnline");
			String namapemohon = getParam("txtNamaPemohon");
			String nokppemohon = getParam("txtnokppemohon");
			// logic_D.setListCarian(usid, noFail, namapemohon, nokppemohon);
			// list1 = logic_D.getListCarian();
			if (getParam("subcommand").equals("lebih")) {
				list1 = logic_D.getSenaraiCarian(usid, noFail, namapemohon,
						nokppemohon);
				onlineLebih = getParam("subcommand");

			} else {
				logic_D.setListCarian(usid, noFail, namapemohon, nokppemohon);
				list1 = logic_D.getListCarian();
				onlineLebih = "";

			}

			int countList = list1.size();
			this.context.put("Senaraifail", list1);
			this.context.put("CountList", countList);

			// this.context.put("Senaraifail",list1);
			this.context.put("noFail", noFail);
			this.context.put("namapemohon", namapemohon);
			this.context.put("nokppemohon", nokppemohon);
			listDaerahByUser = logic_AO.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUserX", listDaerahByUser);
			prepareItemForDisplay(session, list1, 25, "first");

			vm = "app/ppk/frmSenaraiPermohonanOnline.jsp";
			// vm = "app/ppk/frmSenaraiFailPusakaKecil.jsp";

		} else {
			myLogger.info("else " + getParam("command") + ","+ getParam("subcommand"));
			String usid = "";
			// usid="81"; //kelantan
			// session.setAttribute("_ekptg_user_id", usid);
			// this.context.put("usid",usid);
			usid = (String) session.getAttribute("_ekptg_user_id");
			this.context.put("usid", usid);

			this.context.put("noFail", "");
			this.context.put("namapemohon", "");
			this.context.put("nokppemohon", "");
			Carix = "1";
			this.context.put("carix", Carix);
			if (getParam("subcommand").equals("lebih")) {
				myLogger.info("Read here1");
				list = logic_E.getList(usid);
				onlineLebih = getParam("subcommand");

			} else {
				myLogger.info("Read here2");
				list = logic_E.setList(usid);
				onlineLebih = "";

			}
			int countList = list.size();
			myLogger.info("list size***** = "+list.size());
			this.context.put("SenaraifailOnline", list);
			this.context.put("CountList", countList);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));
			setupPage(session,action,list);
			//prepareItemForDisplay(session, list, 25, "first");
			vm = "app/ppk/frmSenaraiPermohonanOnline.jsp";

		}
		
		System.out.println("vm FrmPrmhnnSek8Online >>> "+vm);

		this.context.put("DATEUTIL", new DateUtil());

		Db db = null;
		try {
			db = new Db();

			Vector count_dunia = logic_A.getNofailduniaDb(2, 1, 8, db);
			this.context.put("count_dunia", count_dunia);

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
			Vector listpemilikan = logic_A.getListStatusPemilikDb(statuspemilik, db);
			this.context.put("listpemilikan", listpemilikan);

			Vector listtanah = logic_A.getListJenisTanahDb(db);
			this.context.put("listtanah", listtanah);

			listmukim = logic_A.getListMukimDb(db);
			this.context.put("listmukim", listmukim);

			String statushak = "Y";
			Vector listjenishakmilik = logic_A.getListJenisHakMilikDb(statushak, db);
			this.context.put("listJenisHakMilik", listjenishakmilik);

			Vector listkategori = logic_A.getListKategoriDb(db);
			this.context.put("listkategori", listkategori);

			this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
			this.context.put("flagForView", flagForView);
			this.context.put("flagFromSenaraiPermohonanSek8",flagFromSenaraiPermohonanSek8);

			Vector kenegaraan = null;
			kenegaraan = mainheader.kenegaraanDb(db);
			this.context.put("kenegaraan", kenegaraan);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahARBDb(db);
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData.getMaklumatMahkamahARB();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);
			// Kemaskini oleh Mohamad Rosli pada 11/11/2013, senarai permohonan
			// lebih 21 hari
			this.context.put("onlineLebih", onlineLebih);

			view1 = logic_A.getJenisKpDb(db);
			this.context.put("listkp", view1);
			this.context.put("listkp2", view1);

		} catch (SQLException se2) {
			throw new Exception("Error:" + se2.getMessage());
		} finally {
			if (db != null)
				db.close();
		}

		Template template = this.engine.getTemplate(vm);
		return template;

	}

	private void delete_semakan(HttpSession session, String idPermohonan)
			throws Exception {
		// FrmPrmhnnSek8SenaraiSemakOnlineData frmonline = new
		// FrmPrmhnnSek8SenaraiSemakOnlineData();
		logic_C.semakanDelete(idPermohonan);
	}

	private void updatePermohonan(HttpSession session, String userIdNeg,
			String userIdPejabat, String userIdKodDaerah,
			String userIdKodNegeri, String id_Daerah) throws Exception {
		String IdPermohonan = getParam("idPermohonan");
		String idFail = getParam("idFail");

		String txtbox = getParam("txtNomborResit1");
		String tarikhresit = getParam("txdTarikhByrn1");

		String nofail = getParam("txtNoFail");

		String idSimati = getParam("idSimati");
		String idPemohon = getParam("idPemohon");
		String id_Suburusanstatusfail = getParam("id_Suburusanstatusfail");

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
		String bandar = getParam("txtBandar");
		String idbandar = getParam("socBandar");
		String poskod = getParam("txtPoskod");
		String negeri = getParam("socNegeri");

		// String negId = getParam("negid");

		String txtUmurSimati = getParam("txtUmurSimati");
		String socJantinaSimati = getParam("socJantinaSimati");
		String txtUmurPemohon = getParam("txtUmurPemohon");
		String socJantinaPemohon = getParam("socJantinaPemohon");

		String no_tel = getParam("no_tel");
		String nama_pelbagainegara = getParam("nama_pelbagainegara");
		String taraf_penting = getParam("taraf_penting");
		String jenis_pemohon = getParam("jenis_pemohon");
		String socSaudaraWaris = getParam("socSaudaraWaris");

		// String no_daerah = getParam("socDaerah");

		String buktimati = getParam("buktimati");
		String sijilmati = getParam("sijilmati");

		Hashtable h = null;
		h = new Hashtable();
		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("no_daerah", id_Daerah);
		h.put("userIdPejabat", userIdPejabat);
		h.put("userIdKodDaerah", userIdKodDaerah);
		h.put("userIdKodNegeri", userIdKodNegeri);
		h.put("userIdNeg", userIdNeg);
		h.put("IdPermohonan", IdPermohonan);
		h.put("idFail", idFail);

		h.put("idPemohon", idPemohon);
		h.put("idSimati", idSimati);
		h.put("id_Suburusanstatusfail", id_Suburusanstatusfail);

		// h.put("no_daerah", id_Daerah);
		// h.put("no_daerah", "123");
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
		h.put("idbandar", idbandar);
		h.put("poskod", poskod);
		h.put("negeri", negeri);

		h.put("buktimati", buktimati);
		h.put("sijilmati", sijilmati);

		h.put("txtUmurSimati", txtUmurSimati);
		h.put("socJantinaSimati", socJantinaSimati);

		h.put("txtUmurPemohon", txtUmurPemohon);
		h.put("socJantinaPemohon", socJantinaPemohon);
		h.put("nofail", nofail);

		h.put("txtbox", txtbox);
		h.put("tarikhresit", tarikhresit);

		h.put("no_tel", no_tel);
		h.put("nama_pelbagainegara", nama_pelbagainegara);
		h.put("taraf_penting", taraf_penting);
		h.put("jenis_pemohon", jenis_pemohon);
		h.put("adaob", getParam("adaob"));
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_hp", getParam("no_hp"));

		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("socSaudaraWaris", getParam("socSaudaraWaris"));

		logic_AO.addXPermohonan(h, session);

	}

	/*
	 * private void updatePermohonan(HttpSession session) throws Exception {
	 * String idFail = getParam("idFail"); String IdSimati =
	 * getParam("idSimati"); String IdPemohon = getParam("idPemohon"); String
	 * IdPermohonan = getParam("idPermohonan"); String no_daerah =
	 * getParam("socDaerah"); String tarikh_daftar = getParam("tarikh_daftar");
	 * String tarikh_masuk = getParam("txdTarikhMohon"); String no_kpbaru_simati
	 * =getParam("txtNoKPBaruSimati1")+getParam("txtNoKPBaruSimati2")+getParam(
	 * "txtNoKPBaruSimati3"); String no_kplama_simati =
	 * getParam("txtNoKPLamaSimati"); String sel_jeniskp_simati =
	 * getParam("socJenisKPLainSimati"); String no_kplain_simati =
	 * getParam("txtNoKPLainSimati"); String nama_simati =
	 * getParam("txtNamaSimati"); String tarikh_simati =
	 * getParam("txtTarikhMati"); String no_kpbaru_pemohon =
	 * getParam("txtNoKPBaruPemohon1"
	 * )+getParam("txtNoKPBaruPemohon2")+getParam("txtNoKPBaruPemohon3"); String
	 * no_kplama_pemohon = getParam("txtNoKPLamaPemohon"); String
	 * sel_jeniskp_pemohon = getParam("socJenisKPLainPemohon"); String
	 * no_kplain_pemohon = getParam("txtNoKPLainPemohon"); String nama_pemohon =
	 * getParam("txtNamaPemohon"); String alamat1 = getParam("txtAlamat1");
	 * String alamat2 = getParam("txtAlamat2"); String alamat3 =
	 * getParam("txtAlamat3"); String bandar = getParam("txtBandar"); String
	 * poskod = getParam("txtPoskod"); String negeri = getParam("socNegeri");
	 * 
	 * 
	 * String txtUmurSimati = getParam("txtUmurSimati"); String socJantinaSimati
	 * = getParam("socJantinaSimati"); String txtUmurPemohon =
	 * getParam("txtUmurPemohon"); String socJantinaPemohon =
	 * getParam("socJantinaPemohon");
	 * 
	 * // if()
	 * 
	 * Hashtable h = null; h = new Hashtable(); h.put("IdFail", idFail);
	 * h.put("IdSimati", IdSimati); h.put("IdPemohon", IdPemohon);
	 * h.put("IdPermohonan", IdPermohonan); h.put("no_daerah", no_daerah);
	 * h.put("tarikh_daftar", tarikh_daftar); h.put("tarikh_masuk",
	 * tarikh_masuk); h.put("no_kp_baru", no_kpbaru_simati);
	 * h.put("no_kplama_simati", no_kplama_simati); h.put("sel_jeniskp_simati",
	 * sel_jeniskp_simati); h.put("no_kplain_simati", no_kplain_simati);
	 * h.put("nama_simati", nama_simati); h.put("tarikh_simati", tarikh_simati);
	 * h.put("no_kpbaru_pemohon", no_kpbaru_pemohon); h.put("no_kplama_pemohon",
	 * no_kplama_pemohon); h.put("sel_jeniskp_pemohon", sel_jeniskp_pemohon);
	 * h.put("no_kplain_pemohon", no_kplain_pemohon); h.put("nama_pemohon",
	 * nama_pemohon); h.put("alamat1", alamat1); h.put("alamat2", alamat2);
	 * h.put("alamat3", alamat3); h.put("bandar", bandar); h.put("poskod",
	 * poskod); h.put("negeri", negeri); String
	 * usid=(String)session.getAttribute("_ekptg_user_id");
	 * h.put("id_Masuk",usid); h.put("txtUmurSimati", txtUmurSimati);
	 * h.put("socJantinaSimati", socJantinaSimati); h.put("txtUmurPemohon",
	 * txtUmurPemohon); h.put("socJantinaPemohon", socJantinaPemohon);
	 * 
	 * FrmPrmhnnSek8DaftarSek8OnlineData.updatePermohonan(h); }
	 */

	private void updatesimati(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();
		Vector v = new Vector();

		h.put("id_Simati", getParam("idSimati"));
		h.put("nama_Simati", getParam("txtNamaSimati"));
		h.put("nama_Lain", getParam("txtNamaLainSimati"));
		String nkpbaru = getParam("txtNoKPBaru1Simati")
				+ getParam("txtNoKPBaru2Simati")
				+ getParam("txtNoKPBaru3Simati");
		h.put("no_Kp_Baru", nkpbaru);
		h.put("no_Kp_Lama", getParam("txtNoKPLamaSimati"));
		h.put("jenis_Kp", getParam("socJenisKPLainSimati"));
		h.put("n0_Kp_Lain", getParam("txtNoKPLainSimati"));
		if (getParam("txtUmurSimati").equals("")) {
			h.put("umur", 0);
		} else {
			h.put("umur", Integer.parseInt(getParam("txtUmurSimati")));
		}
		h.put("jantina", getParam("socJantinaSimati"));
		h.put("no_Sijil_Mati", getParam("txtNoSijilMatiSimati"));
		h.put("tempat_Mati", getParam("txtTempatMatiSimati"));
		h.put("alamat_1", getParam("txtAlamatTerakhir1Simati"));
		h.put("alamat_2", getParam("txtAlamatTerakhir2Simati"));
		h.put("alamat_3", getParam("txtAlamatTerakhir3Simati"));
		h.put("bandar", getParam("txtBandarSimati"));
		h.put("poskod", getParam("txtPoskodSimati"));
		h.put("tarikh_Mati", getParam("txdTarikhMatiSimati"));
		h.put("waktu_Kematian", getParam(""));
		h.put("jenis_Waktu_Mati", getParam("socWaktuKematianSimati"));
		h.put("sebab_Mati", getParam("txtSebabKematianSimati"));
		h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
		h.put("catatan", getParam("txtCatatanSimati"));
		if (getParam("socNegeriSimati").equals("")) {
			h.put("id_Negeri", 0);
		} else {
			h.put("id_Negeri", Integer.parseInt(getParam("socNegeriSimati")));
		}
		h.put("id_Buktimati", getParam("socBuktiKematianSimati"));
		h.put("jenis_Agama", getParam("socAgamaSimati"));
		h.put("jenis_Warga", getParam("socWarganegaraSimati"));
		h.put("tarikh_Kkini", getParam(""));
		h.put("id_Permohonan", getParam("idPermohonan"));
		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("id_Kemaskini", getParam(""));
		h.put("tarikh_Kemaskini", getParam(""));
		h.put("id_Db", getParam(""));
		h.put("jeniswaktu", getParam("jeniswaktu"));
		logic_internal.updatesimati(h);
	}

	private void updatesimatisemak(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();
		Vector v = new Vector();

		h.put("id_Simati", getParam("id_Simati"));
		h.put("no_Sijil_Mati", getParam("sijilmati"));
		h.put("id_Buktimati", getParam("buktimati"));

		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("id_Kemaskini", getParam(""));
		h.put("tarikh_Kemaskini", getParam(""));
		h.put("id_Db", getParam(""));

		logic_internal.updatesimatisemak(h);
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
		// System.out.println("XXX###" + getParam("adataraf"));
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
			k.put("taraf",
					Integer.parseInt(getParam("socTarafKePemohonanPemohon")));
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
			k.put("idnegeriSurat",
					Integer.parseInt(getParam("socNegeriPemohonSurat")));
		}

		k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
		k.put("nama_pelbagainegara_surat",
				getParam("nama_pelbagainegara_surat"));

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
			h.put("bandarSurat",
					Integer.parseInt(getParam("txtBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat",
					Integer.parseInt(getParam("socNegeriWarisSurat")));
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
			h.put("statusWaris", Integer.parseInt(getParam("socStatusOBWaris")));
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
		h.put("jeniswaktu", getParam("jeniswaktu"));

		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

		h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
		h.put("nama_pelbagainegara_surat",
				getParam("nama_pelbagainegara_surat"));

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
			h.put("bandarSurat",
					Integer.parseInt(getParam("txtBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat",
					Integer.parseInt(getParam("socNegeriWarisSurat")));
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
			h.put("statusWaris", Integer.parseInt(getParam("socStatusOBWaris")));
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
		h.put("nama_pelbagainegara_surat",
				getParam("nama_pelbagainegara_surat"));

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
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("idwaris", getParam("idwarisup"));
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
			h.put("bandarSurat",
					Integer.parseInt(getParam("txtBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat",
					Integer.parseInt(getParam("socNegeriWarisSurat")));
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
			h.put("statusWaris", Integer.parseInt(getParam("socStatusOBWaris")));
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
		h.put("nama_pelbagainegara_surat",
				getParam("nama_pelbagainegara_surat"));

		logic_internal.updateWaris(h);

	}

	private void addPenting(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();

		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("namaOB", getParam("txtNamaOBPenting"));
		// String id_Permohonansimati =
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
			h.put("taraf",
					Integer.parseInt(getParam("socTarafKepentinganPenting")));
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
			h.put("negeriSurat",
					Integer.parseInt(getParam("socNegeriWarisSurat")));
		}

		h.put("notel", getParam("txtNoTeleponPenting"));

		h.put("jenishutang", getParam("socJenisHutangPenting"));

		h.put("noakaun", getParam("txtNoAkaunPenting"));

		if (getParam("txtNilaiHutangPenting").equals("")) {
			h.put("nilaihutang", 0.0);
		} else {
			h.put("nilaihutang",
					Double.parseDouble(getParam("txtNilaiHutangPenting")));
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
			h.put("taraf",
					Integer.parseInt(getParam("socTarafKepentinganPentingU")));
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
			h.put("nilaihutang",
					Double.parseDouble(getParam("txtNilaiHutangPentingU")));
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
			h.put("idbandarSurat",
					Integer.parseInt(getParam("socBandarWarisSurat")));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", 0);
		} else {
			h.put("negeriSurat",
					Integer.parseInt(getParam("socNegeriWarisSurat")));
		}

		h.put("notel", getParam("txtNoTeleponPentingU"));

		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_fax", getParam("txtNoFaxPenting"));
		h.put("jenis_pemohon", getParam("jenis_pemohon"));
		h.put("no_hp", getParam("txtNoHpPenting"));

		if (getParam("txtBandarPentingU_X").equals("")) {
			h.put("bandartetap_x", 0);
		} else {
			h.put("bandartetap_x",
					Integer.parseInt(getParam("txtBandarPentingU_X")));
		}

		if (getParam("txtBandarWarisSurat_X").equals("")) {
			h.put("bandarsurat_x", 0);
		} else {
			h.put("bandarsurat_x",
					Integer.parseInt(getParam("txtBandarWarisSurat_X")));
		}

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

		logic_internal.updatePenting(h);

	}

	private void addPenghutang(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		// String id_Permohonansimati =
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
			h.put("nilaihutang",
					Double.parseDouble(getParam("txtNilaiHutangPenting")));
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
			h.put("nilaihutang",
					Double.parseDouble(getParam("txtNilaiHutangPentingU")));
		}

		// h.put("nilaihutang", getParam("txtNilaiHutangPentingU"));
		h.put("noakaun", getParam("txtNoAkaunPentingU"));
		h.put("butiranhutang", getParam("txtButiranHutangPentingU"));

		h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Kemaskini", currentDate);

		logic_internal.updatePenghutang(h);

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
			h.put("kategori",
					Integer.parseInt(getParam("socKategoriTanahHtaam")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik",
					Integer.parseInt(getParam("socJenisHakmilikHtaam")));
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
		logic_internal.addHtaam(h);

	}

	/*
	 * private void updateHtaam(HttpSession session) throws Exception {
	 * 
	 * Vector v = new Vector(); Hashtable h = new Hashtable();
	 * h.put("idhta",getParam("id_htaam")); h.put("noHakmilik",
	 * getParam("txtNoHakmilikHtaamUp")); h.put("idSimati",
	 * getParam("idSimati")); h.put("nopt", getParam("txtNoPTHtaamUp"));
	 * h.put("nilai_Hta_memohon",getParam("txtNilaiTarikhMohonHt"));
	 * h.put("nilai_Hta_mati",getParam("txtNilaiTarikhMatiHtaamUpd")); if
	 * (getParam("socKategoriTanahHtaamUp") != "") { h.put("kategori", Integer
	 * .parseInt(getParam("socKategoriTanahHtaamUp"))); } else {
	 * h.put("kategori", 0); } if (getParam("socJenisHakmilikHtaamUp") != "") {
	 * h.put("jenishakmilik", Integer
	 * .parseInt(getParam("socJenisHakmilikHtaamUp"))); } else {
	 * h.put("jenishakmilik", 0); } h.put("pemilikan",
	 * getParam("socStatusPemilikanHtaamUpd")); if (getParam("socNegeriHtaamUp")
	 * != "") { h.put("negeri", Integer.parseInt(getParam("socNegeriHtaamUp")));
	 * } else { h.put("negeri", 0); } if (getParam("socDaerahHtaamUp") != "") {
	 * h.put("daerah", Integer.parseInt(getParam("socDaerahHtaamUp"))); } else {
	 * h.put("daerah", 0); } if (getParam("socMukimHtaamUp") != "") {
	 * h.put("mukim", Integer.parseInt(getParam("socMukimHtaamUp"))); } else {
	 * h.put("mukim", 0); } h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
	 * h.put("luasasal", getParam("txtLuasAsalHtaamUpd")); h.put("nopajakan",
	 * getParam("txtNoPajakanUp")); h.put("jenistanah",
	 * getParam("socJenisTanahHtaamUpd"));
	 * 
	 * //Azam change on 30/4/2010 //change int to String utk cater for 14 digit
	 * bahagian simati //pembawah dan pengatas if
	 * (getParam("txtBahagianSimati1Up") != "") {
	 * h.put("basimati",getParam("txtBahagianSimati1Up")); } else {
	 * h.put("basimati", "0"); }
	 * 
	 * if (getParam("txtBahagianSimati2Up") != "") { h.put("bbsimati",
	 * getParam("txtBahagianSimati2Up")); } else { h.put("bbsimati", "0"); }
	 * 
	 * if (getParam("socJenisLuasHtaamUpd") != "") { h.put("jenisluas",
	 * Integer.parseInt(getParam("socJenisLuasHtaamUpd"))); } else {
	 * h.put("jenisluas", 0); }
	 * 
	 * h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
	 * 
	 * h.put("catatan", getParam("txtCatatanHt")); h.put("noperserahan",
	 * getParam("txtNoPersHtaamUp"));
	 * 
	 * h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
	 * h.put("tarikh_Kemaskini", currentDate); logic_internal.updateHtaam(h); }
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
			h.put("kategori",
					Integer.parseInt(getParam("socKategoriTanahHtaamX")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaamX") != "") {
			h.put("jenishakmilik",
					Integer.parseInt(getParam("socJenisHakmilikHtaamX")));
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
			h.put("jenisluas", Integer.parseInt(getParam("socJenisLuasHtaamX")));
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
			h.put("bandarpemaju",
					Integer.parseInt(getParam("txtBandarPemaju1HtaamX")));
		}

		if (getParam("socNegeriPemajuHtaamX").equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju",
					Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
		}

		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		// h.put("bandarhta",getParam("txtBandarHartaHtaamX"));

		if (getParam("txtBandarHartaHtaamX").equals("")) {
			h.put("bandarhta", 0);
		} else {
			h.put("bandarhta",
					Integer.parseInt(getParam("txtBandarHartaHtaamX")));
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
	 * h.put("idhta", getParam("idhtaamid")); // String
	 * idhtaam=Integer.parseInt(getParam("idhtaamid"));
	 * 
	 * h.put("idSimati", getParam("idSimati")); h.put("nopt",
	 * getParam("txtNoPTHtaamX"));
	 * 
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
	 * if (getParam("txtBahagianSimati2X") != "") { h
	 * .put("bbsimati",getParam("txtBahagianSimati2X")); } else {
	 * h.put("bbsimati", "0"); }
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
		h.put("idstatus", getParam("idstatus"));
		h.put("id_Fail", getParam("id_Fail"));

		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		h.put("id_Suburusanstatus", getParam("id_Suburusanstatus"));
		h.put("id_Suburusanstatusfail", getParam("id_Suburusanstatusfail"));
		// :::SUB
		// ID_STATUS : 14
		// ID_SUBURUSAN : 353
		// logic.updateStatus(h);
		logic_A.kemaskiniSubUrusanStatusFail(session, getParam("idPermohonan"),
				(String) session.getAttribute("_ekptg_user_id"), "14", "353",
				getParam("id_Fail"));

	}

	private void ha_negeri(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		String id2 = getParam("idPemohon");
		String id1 = getParam("idSimati");
		String mati = getParam("id_Permohonansimati");

		String eventstatus = getParam("eventStatus");

		this.context.put("id", id);
		this.context.put("id1", id1);

		Vector listnegeri = logic_A.getListnegeri();
		this.context.put("listnegeri", listnegeri);

		this.context.put("readmodenegeri", "");
		this.context.put("readmodedaerah", "");

		int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
		Vector listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
		this.context.put("listDaerahbyNegeri", listnegeribydaerah);

		Vector listabc = new Vector();
		Hashtable h;

		h = new Hashtable();

		h.put("negeri", idnegeri);
		h.put("daerah", "");
		// h.put("id_Jenisha", getParam("socJenisHartaAlih"));
		h.put("socJenisHa", getParam("socJenisHa"));
		h.put("norujukan", getParam("txtNoRujukan"));
		h.put("nosijil", getParam("txtNoSijil"));
		h.put("bilunit", getParam("txtBilUnit"));
		h.put("nilaiseunit", getParam("txtNilaiSeunit"));
		h.put("agensi", getParam("txtAgensi"));
		h.put("alamat1", getParam("txtAlamat1"));
		h.put("alamat2", getParam("txtAlamat2"));
		h.put("alamat3", getParam("txtAlamat3"));
		h.put("poskod", getParam("txtPoskod"));
		h.put("negeri", getParam("socNegeriHtaam"));
		h.put("daerah", getParam("socDaerahHtaam"));
		h.put("bhgnmati1", getParam("txtBhgnSimati1"));
		h.put("bhgnmati2", getParam("txtBhgnSimati2"));
		h.put("nilaitarikhmati", getParam("txtNilaiTarikhMati"));
		h.put("nilaitarikhmohon", getParam("txtNilaiTarikhMohon"));
		h.put("catatan", getParam("txtCatatan"));

		listabc.addElement(h);
		this.context.put("DataHa", listabc);
		this.context.put("tutup", "yes");
	}

	private void prepareItemForDisplay(HttpSession session, Vector objVector,
			int cntItemPage, String command) {
		int x;
		int startno = 0;
		if (command == null)
			command = "first";
		if (session.getAttribute("_portal_startnoInternalFailOnline") != null)
			startno = ((Integer) session
					.getAttribute("_portal_startnoInternalFailOnline"))
					.intValue();
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

		session.setAttribute("_portal_moduleVectorInternalFailOnline",
				moduleVector);

		this.context.put("startnoInt", new Integer(startno));
		this.context.put("totalInt", new Integer(objVector.size()));

		startno = i;

		session.setAttribute("_portal_startnoInternalFailOnline", new Integer(
				startno));

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
			h.put("kategori",
					Integer.parseInt(getParam("socKategoriTanahHtaamX")));
		} else {
			h.put("kategori", 0);
		}

		if (getParam("socJenisHakmilikHtaamX") != "") {
			h.put("jenishakmilik",
					Integer.parseInt(getParam("socJenisHakmilikHtaamX")));
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
			h.put("jenisluas", Integer.parseInt(getParam("socJenisLuasHtaamX")));
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
			h.put("bandarpemaju",
					Integer.parseInt(getParam("txtBandarPemaju1HtaamX")));
		}

		if (getParam("socNegeriPemajuHtaamX").equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju",
					Integer.parseInt(getParam("socNegeriPemajuHtaamX")));
		}

		h.put("alamathta1", getParam("txtAlamatHarta1HtaamX"));
		h.put("alamathta2", getParam("txtAlamatHarta2HtaamX"));
		h.put("alamathta3", getParam("txtAlamatHarta3HtaamX"));

		h.put("poskodhta", getParam("txtPoskodHartaHtaamX"));
		// h.put("bandarhta",getParam("txtBandarHartaHtaamX"));

		if (getParam("txtBandarHartaHtaamX").equals("")) {
			h.put("bandarhta", 0);
		} else {
			h.put("bandarhta",
					Integer.parseInt(getParam("txtBandarHartaHtaamX")));
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
			h.put("kategori",
					Integer.parseInt(getParam("socKategoriTanahHtaamUp")));
		} else {
			h.put("kategori", 0);
		}
		if (getParam("socJenisHakmilikHtaamUp") != "") {
			h.put("jenishakmilik",
					Integer.parseInt(getParam("socJenisHakmilikHtaamUp")));
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
			h.put("jenisluas",
					Integer.parseInt(getParam("socJenisLuasHtaamUpd")));
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
		logic_internal.updateHtaam(h);
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
	
public void setupPage(HttpSession session,String action,Vector list) {
	
		try {
		
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
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
		    
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraifailOnline",paging.getPage(page));
			    this.context.put("page", new Integer(page));
			    this.context.put("itemsPerPage", new Integer(itemsPerPage));
			    this.context.put("totalPages", new Integer(paging.getTotalPages()));
			    this.context.put("startNumber", new Integer(paging.getTopNumber()));
			    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
			    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
			   
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	

}
