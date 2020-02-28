package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;
import lebah.util.DateUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SecaraOnlineData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiHTATHInternalData;
import ekptg.model.ppk.FrmPrmhnnSek8SenaraiSemakInternalData;
import ekptg.model.ppk.FrmSenaraiFailInternalCarianData;
import ekptg.model.ppk.FrmSenaraiFailInternalData;
import ekptg.model.ppk.PendaftaranCheckModel;

public class FrmPrmhnnBorangAMaklumatPemohon extends VTemplate {

	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	static Logger myLogger = Logger
			.getLogger(FrmPrmhnnBorangAMaklumatPemohon.class);

	private static final long serialVersionUID = 1L;
	FrmPrmhnnSek8InternalData logic = null;
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null;
	FrmPrmhnnSek8SenaraiHTATHInternalData logic_B = null;
	FrmPrmhnnSek8SenaraiSemakInternalData logic_C = null;
	FrmSenaraiFailInternalCarianData logic_D = null;
	FrmSenaraiFailInternalData logic_E = null;
	FrmPrmhnnSek8SecaraOnlineData logiconline = null;
	FrmHeaderPpk mainheader = new FrmHeaderPpk();

	String selectedTabatas = "";
	String selectedTabtengah = "";
	String selectedTabbawah = "";
	String selectedTabtepi = "";
	String selectLevelTab = "";
	String disability1 = "";
	String disability2 = "";
	String readability1 = "";
	String readability2 = "";

	@SuppressWarnings("unchecked")
	public Template doTemplate() throws Exception {
		logic = new FrmPrmhnnSek8InternalData();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		logic_B = new FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_C = new FrmPrmhnnSek8SenaraiSemakInternalData();
		logic_D = new FrmSenaraiFailInternalCarianData();
		logic_E = new FrmSenaraiFailInternalData();
		logiconline = new FrmPrmhnnSek8SecaraOnlineData();
		headerppk_baru_default();

		int flagno = 0;
		int idFlag = 0;
		int flag_no = 0;

		this.context.put("skrin_online", "yes");
		this.context.put("skrin_online_17", "");
		this.context.put("appear_skrin_info", "");

		Calendar cal = Calendar.getInstance();
		Calendar currentcal = Calendar.getInstance();
		cal.set(2009, Calendar.SEPTEMBER, 1);
		currentcal.set(currentcal.get(Calendar.YEAR), currentcal
				.get(Calendar.MONTH), currentcal.get(Calendar.DAY_OF_MONTH));

		String bolehsimpan = "";
		HttpSession session = this.request.getSession();
		String doPost = (String) session.getAttribute("doPost");

		Vector listBandarSuratbyNegeri = null;
		Vector jenis_user_list = logic.getUserType((String) session
				.getAttribute("_ekptg_user_id"));
		Hashtable jenis_user_hash = (Hashtable) jenis_user_list.get(0);
		String jenis_user = jenis_user_hash.get("USER_TYPE").toString();
		String upload = getParam("upload");

		if (jenis_user.equals("internal")) {
			this.context.put("skrin_online_popup", "yes");
		} else {
			this.context.put("skrin_online_popup", "");
		}

		if (doPost.equals("true")) {
			myLogger.info("browser true **********");
			bolehsimpan = "yes";
		} else {
			myLogger.info("browser false **********");
			if (jenis_user.equals("internal")) {
				bolehsimpan = "yes";
			} else {
				bolehsimpan = "";
			}

		}
		// bolehsimpan = "yes";

		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		String flagForView = getParam("flagForView");

		clearContext();

		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri"));

		String vm = "";
		int simpanStatus = 0;
		int eventStatus = 0;
		int backstatus = 0;

		if (!getParam("idFlag").equals("")) {
			idFlag = Integer.parseInt(getParam("idFlag"));
		}

		String submit = getParam("command");
		String mode = getParam("mode");
		String idAlert = getParam("idAlert");
		myLogger.info("------------------command :" + submit);
		myLogger.info("------------------mode :" + mode);

		this.context.put("SimpanStatus", simpanStatus);
		this.context.put("EventStatus", eventStatus);
		this.context.put("backStatus", backstatus);
		String NegId = "1";
		String IDPermohonan = "0";
		this.context.put("IdPermohonan", IDPermohonan);
		this.context.put("NegId", NegId);
		this.context.put("idFlag", idFlag);

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
		Vector v = null;
		Vector View_pengesahan_pemohonan = null;

		String hideTabPengesahan_pemohon = "";
		String hideTabPengesahan_simati = "";
		String hideTabPengesahan_hta = "";

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date);

		this.context.put("Util", new lebah.util.Util());

		String v_tab = getParam("v_tab");
		this.context.put("val_tab", v_tab);

		System.out.println("submit online ppk:::::"+submit);

		if ("Kemaskini".equals(submit)) {
			String tempid = getParam("idtemp");
			String idper = tempid;
			String idp = getParam("idtemp");
			simpanStatus = 1;
			String eventstatus = getParam("eventStatus");
			this.context.put("EventStatus", eventstatus);
			this.context.put("SimpanStatus", simpanStatus);
			this.context.put("IdPermohonan", tempid);
			logic_C.setDataSemakan(idper);
			listSemak2 = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak2);
			this.context.put("list2", list2);
			if (list2.size() > 0) {
				this.context.put("nofail", "yes");
			}
			this.context.put("daftar", "yes");
			vm = "app/ppk/frmPrmhnnSek8SenaraiSemakDaftar_online";
		} else if ("papar".equals(submit)) {
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			eventStatus = 1;
			NegId = "1";
			int getnewpemohon = 0;
			this.context.put("GetNewPemohon", getnewpemohon);
			this.context.put("NegId", NegId);
			this.context.put("EventStatus", eventStatus);
			String idPermohonan = getParam("idpermohonan");
			this.context.put("IdPermohonan", idPermohonan);
			this.context.put("backStatus", backstatus);
			logic_C.setDataSemakan(idPermohonan);
			listSemak = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak);

			logic_C.setDataSemakanSimati(idPermohonan);

			listSemakSimati = logic_C.getListSemakanSimati();
			this.context.put("ListSemakanSimati", listSemakSimati);

			String idp = getParam("idpermohonan");
			list2 = logic_A.setData_online(idp, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idp, "Y", "", "T");
			if (list2.size() > 0) {
				this.context.put("nofail", "yes");
			}
			this.context.put("daftar", "yes");
			this.context.put("list2", list2);
			vm = "app/ppk/frmPrmhnnSek8SenaraiSemakDaftar_online.jsp";
		} else if ("kembali_daftar_pemohon".equals(submit)) {
			String tempid = getParam("idtemp");
			String idper = tempid;
			String eventstatus = "1";
			this.context.put("EventStatus", eventstatus);
			simpanStatus = 2;
			this.context.put("SimpanStatus", simpanStatus);
			logic_C.setDataSemakan(idper);
			this.context.put("IdPermohonan", tempid);
			listSemak = logic_C.getListSemakan();
			this.context.put("ListSemakan", listSemak);

			String idp = getParam("idtemp");
			list2 = logic_A.setData_online(idp, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idp, "Y", "", "T");
			if (list2.size() > 0) {
				this.context.put("nofail", "yes");
			}
			this.context.put("daftar", "yes");
			this.context.put("list2", list2);

			vm = "app/ppk/frmPrmhnnSek8SenaraiSemakDaftar_online.jsp";
		}

		else if ("Simpan".equals(submit)) {
			String uu = (String) session.getAttribute("_ekptg_user_id");
			String eventstatus = getParam("eventStatus");
			eventStatus = Integer.parseInt(eventstatus);
			if (eventStatus == 0) {
				long idPermohonan = DB.getNextID("TBLPPKPERMOHONAN_SEQ");
				eventStatus = 1;
				this.context.put("EventStatus", eventStatus);
				simpanStatus = 2;
				int getnewpemohon = 1;
				String idNeg = getParam("negid");
				this.context.put("NegId", idNeg);
				this.context.put("GetNewPemohon", getnewpemohon);
				this.context.put("SimpanStatus", simpanStatus);
				this.context.put("IdPermohonan", idPermohonan);
				vm = "app/ppk/frmPrmhnnSek8SenaraiSemakDaftar_online.jsp";

			}

			String idp = getParam("idtemp");
			list2 = logic_A.setData_online(idp, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idp, "Y", "", "T");
			if (list2.size() > 0) {
				this.context.put("nofail", "yes");
			}
			this.context.put("daftar", "yes");
			this.context.put("list2", list2);

			String idpp = getParam("idPermohonan");
			logic_A.checkData(idpp);
			chkId = logic_A.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());
			this.context.put("duplicate", "");
			if (cntID == 0) {
				this.context.put("tarikhmohondaftar", currentDate);
				int idflag = 0;
				int flagNo = 0;
				idAlert = "0";
				this.context.put("IdFail", idflag);
				this.context.put("flagno", flagNo);
				this.context.put("idAlert", idAlert);
				String tempid = getParam("idtemp");
				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				this.context.put("IdPemohon", idPemohon);
				this.context.put("negeri", "");
				this.context.put("daerah", "");
				this.context.put("listBandarbyNegeri", "");



				// comment dulu sebab online xleh dapat user id internal
				view_get_userid(session);
				listUserid = logic_A.getUserIds();
				// Hashtable t = (Hashtable)listUserid.get(0);
				// String userIdDaerah = t.get("idDaerah").toString();
				// String idNegeri = t.get("idNegeri").toString();
				// this.context.put("NegId", idNegeri);

				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				this.context.put("EventStatus", 0);
				this.context.put("IdSimati", 0);
				this.context.put("idDaerahx", "");
				logic_C.setDataSemakan(tempid);
				listSemak = new Vector();
				listSemak = logic_C.getListSemakan();
				this.context.put("ListSemakan", listSemak);
				this.context.put("ViewFail", "");
			}

			else {
				this.context.put("tarikhmohon", currentDate);
				String id = getParam("idtemp");
				list2 = logic_A.setData_online(id, (String) session.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				int idflag = 2;
				int flagNo = 2;
				idAlert = "0";
				int eventstatus_temp = 1;
				this.context.put("EventStatus", eventstatus_temp);
				this.context.put("idFlag", idflag);
				this.context.put("flagno", flagNo);
				this.context.put("idAlert", idAlert);
				String tempid = getParam("idtemp");
				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				this.context.put("IdPemohon", idPemohon);
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri","class=mediumselect"));
				String IdNeg = getParam("negid");
				this.context.put("NegId", IdNeg);
				this.context.put("View", list2);
				this.context.put("ViewX", list2);
				Hashtable h = (Hashtable) list2.get(0);
				this.context.put("IdSimati", Long.parseLong(h.get("idSimati").toString()));
				this.context.put("IdPemohon", Long.parseLong(h.get("idPemohon").toString()));
				if (h.get("pmidnegeri").toString() == "") {
					this.context.put("negeri", "");
					this.context.put("daerah", "");
				} else {
					this.context.put("negeri", h.get("pmidnegeri").toString());
					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h.get("pmidnegeri").toString()));
					this.context.put("listBandarbyNegeri", s3);
					this.context.put("daerah", "");
				}
				if (h.get("idbandar").toString() == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", h.get("idbandar").toString());
				}

				listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);
				this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(IdNeg, "socDaerah", Long.parseLong(h.get("idDaerah").toString()), "class=disabled"));
				this.context.put("tarikhmohondaftar", "");

				logic_C.setDataSemakan(tempid);

				listSemak = logic_C.getListSemakan();
				this.context.put("ListSemakan", listSemak);

				logic_A.setDataFail(tempid);
				listFail = logic_A.getDataFail();
				this.context.put("ViewFail", listFail);

			}

			logic.setDataPemohonOB(idpp);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			String txtbox = getParam("txtNomborResit1");
			String tarikhresit = getParam("txdTarikhByrn1");
			this.context.put("txtNomborResit1", txtbox);
			this.context.put("txdTarikhByrn1", tarikhresit);
			vm = "app/ppk/frmPrmhnnSek8DaftarSek8_online.jsp";

		}

		else if ("Kemaskini_daftar_pemohon".equals(submit)) {

			// list2 = new Vector();

			this.context.put("tarikhmohondaftar", "");
			this.context.put("tarikhmohon", currentDate);

			int evenstatus = 0;
			int idflag = 2;
			int flagNo = 1;
			this.context.put("idFlag", idflag);
			this.context.put("flagno", flagNo);
			this.context.put("EventStatus", evenstatus);

			String tempid = getParam("idPermohonan");

			String id = getParam("idPermohonan");

			this.context.put("IdPermohonan", tempid);
			list2 = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list2);
			this.context.put("ViewX", list2);

			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);

			if (list2.size() > 0) {
				Hashtable h = (Hashtable) list2.get(0);
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
				view_get_id(session);
				listIds = logic_A.getIds();

				Hashtable k = (Hashtable) listIds.get(0);
				this.context.put("IdSimati", Long.parseLong(k.get("idsimati")
						.toString()));
				this.context.put("IdPemohon", Long.parseLong(k.get("idpemohon")
						.toString()));
				this.context.put("IdPermohonan", getParam("idPermohonan"));
			} else {
				this.context.put("negeri", "");
				this.context.put("daerah", "");

			}

			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			this.context.put("duplicate", "");

			String txtbox = getParam("txtNomborResit1");
			String tarikhresit = getParam("txdTarikhByrn1");
			this.context.put("txtbox", txtbox);
			this.context.put("tarikhresit", tarikhresit);

			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPrmhnnSek8DaftarSek8_online.jsp";

		} else if ("seterusnya".equals(submit)) {

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
			// logic_A.setData_online(id);
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);
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

			this.context.put("readmode", disability1);

			this.context.put("show_kemaskini_button", "yes");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);

			hideTabPengesahan_simati = checkEmptyField_simati(id);
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(id);
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(id);
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);

			vm = "app/ppk/frmPrmhnnSek8Simati.jsp";

		} else if ("tab".equals(submit)) {
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			String id = getParam("idpermohonan");
			// System.out.println("IDDD :::"+id);
			// int id2 = Integer.parseInt(getParam("idPemohon"));
			// int id1 = Integer.parseInt(getParam("idSimati"));
			// this.context.put("id", id);
			// this.context.put("id2", id2);
			// this.context.put("id1", id1);
			// logic_A.setData_online(id);
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);
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

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);

			vm = "app/ppk/frmPrmhnnSek8Simati.jsp";

		}

		else if ("kembali_simati".equals(submit)) {

			String id = getParam("idPermohonan");
			/*
			view1 = logic_A.getJenisKp();
			this.context.put("listkp", view1);
			 */

			idFlag = 2;
			flag_no = 2;
			eventStatus = 1;
			idAlert = "0";
			this.context.put("idAlert", idAlert);
			this.context.put("tarikhmohon", getParam("tarikh_daftar"));
			this.context.put("flagno", flag_no);
			this.context.put("idFlag", idFlag);
			this.context.put("EventStatus", 1);
			list2 = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list2);
			this.context.put("ViewX", list2);
			String IdNeg = getParam("negid");
			this.context.put("NegId", IdNeg);
			Hashtable h = (Hashtable) list2.get(0);
			if (h.get("pmidnegeri").toString() == "") {
				this.context.put("negeri", "");
				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,"class=\"autoselect\" disabled "));
			} else {
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled "));
				this.context.put("negeri", h.get("pmidnegeri").toString());
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h
						.get("pmidnegeri").toString()));
				this.context.put("listBandarbyNegeri", s3);
				this.context.put("daerah", "");
			}
			if (h.get("idbandar").toString() == "") {

				this.context.put("daerah", "");

				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",null,"class=\"autoselect\" disabled "));
			} else {
				// this.context.put("selectNegeri",
				// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled "));
				this.context.put("daerah", h.get("idbandar").toString());

			}

			// logic_A.setData_online(id,(String)session.getAttribute("_ekptg_user_id"));
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

			this.context.put("tarikhmohondaftar", "");

			String ido = getParam("idPermohonan");
			logic_A.setDataFail(ido);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPrmhnnSek8DaftarSek8_online.jsp";

		}

		else if ("getDaftarStatus".equals(submit)) {

			// System.out.println("ST FAIL ::"+getParam("id_Suburusanstatusfail"));

			String id_sub = getParam("id_Suburusanstatusfail");
			String id_Fail = getParam("id_Fail");
			String id = getParam("idPermohonan");
			long idper = Long.parseLong(id);
			if (bolehsimpan.equals("yes")) {
				logic_A.daftarstatus(session, id, (String) session
						.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
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

			list2 = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list2);
			Hashtable h = (Hashtable) list2.get(0);
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
			this.context.put("IdSimati", Long.parseLong(k.get("idsimati")
					.toString()));
			this.context.put("IdPemohon", Long.parseLong(k.get("idpemohon")
					.toString()));
			this.context.put("IdPermohonan", getParam("idPermohonan"));
			listDaerahByUser = logic_A.getDaerahByNegeriUser((String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("ListDaerahByUser", listDaerahByUser);

			this.context.put("duplicate", "");

			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPrmhnnSek8DaftarSek8_online.jsp";
		} else if ("Simpanx".equals(submit)) {
			System.out.println("Simpanx");
			String passvalue = getParam("passvalue");
			this.context.put("duplicate", "");

			String IdPermohonan = getParam("idPermohonan");
			String Idppp = getParam("idpermohonan");

			System.out.println("IdPermohonan"+IdPermohonan);
			check_mode_permohonan(session);
			chkId = logic_A.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());

			String useridx = (String) session.getAttribute("_ekptg_user_id");
			// String useridx = "14";

			/*
			 * logic_A.setGetUserId(useridx); listUserid = logic_A.getUserIds();
			 * Hashtable t = (Hashtable)listUserid.get(0); String userIdDaerah =
			 * t.get("idDaerah").toString(); String userIdNeg =
			 * t.get("idNegeri").toString(); String userIdKodDaerah =
			 * t.get("kodDaerah").toString(); String userIdKodNegeri =
			 * t.get("kodnegeri").toString(); String userIdPejabat =
			 * t.get("idpejabat").toString();
			 */

			String no_kpbaru_simati = getParam("txtNoKPBaruSimati1")
					+ getParam("txtNoKPBaruSimati2")
					+ getParam("txtNoKPBaruSimati3");
			String no_kplama_simati = getParam("txtNoKPLamaSimati");
			String sel_jeniskp_simati = getParam("socJenisKPLainSimati");
			String no_kplain_simati = getParam("txtNoKPLainSimati");

			logic_E.setListKPSimati(IdPermohonan, no_kpbaru_simati,
					no_kplama_simati, no_kplain_simati);
			listKPSimati = logic_E.getListKPSimati();

			if (logic_E.checkKPSimati(IdPermohonan, no_kpbaru_simati,
					no_kplama_simati, no_kplain_simati) == true) {
				this.context.put("listKPSimati", 1);
			} else {
				this.context.put("listKPSimati", 0);
			}

			myLogger.info("dah wujud simati? :"
					+ logic_E.checkKPSimati(IdPermohonan, no_kpbaru_simati,
							no_kplama_simati, no_kplain_simati));

			myLogger.info("dah wujud pemohon? :" + cntID);

			if ((logic_E.checkKPSimati(IdPermohonan, no_kpbaru_simati,
					no_kplama_simati, no_kplain_simati)) == true) {
				if (cntID == 0) {
					this.context.put("duplicate", "yes");

					String nama_simati = "";
					String no_fail = "";
					String nama_pejabat = "";
					String daerah_mohon = "";
					String no_rujukan_online = "";
					String id_status_daftar = "";

					Vector maklumatSimati = new Vector();
					Vector maklumatSimati_WithoutNoFail = new Vector();
					maklumatSimati_WithoutNoFail.clear();
					maklumatSimati.clear();

					PendaftaranCheckModel.setmaklumatSimati(no_kpbaru_simati,
							no_kplama_simati, no_kplain_simati);
					maklumatSimati = PendaftaranCheckModel.getmaklumatSimati();

					PendaftaranCheckModel.setmaklumatSimati_WithoutNoFail(
							no_kpbaru_simati, no_kplama_simati,
							no_kplain_simati);
					maklumatSimati_WithoutNoFail = PendaftaranCheckModel
							.getmaklumatSimati_WithoutNoFail();

					if (maklumatSimati.size() != 0) {
						Hashtable mt = (Hashtable) maklumatSimati.get(0);
						nama_simati = mt.get("NAMA_SIMATI").toString();
						no_fail = mt.get("NO_FAIL").toString();
						nama_pejabat = mt.get("NAMA_PEJABAT").toString();
						daerah_mohon = mt.get("NAMA_DAERAH").toString();
						no_rujukan_online = mt.get("NO_PERMOHONAN_ONLINE")
								.toString();
						id_status_daftar = mt.get("ID_STATUS").toString();
					} else if (maklumatSimati_WithoutNoFail.size() != 0) {
						Hashtable mz = (Hashtable) maklumatSimati_WithoutNoFail
								.get(0);
						nama_simati = mz.get("NAMA_SIMATI").toString();
						no_rujukan_online = mz.get("NO_PERMOHONAN_ONLINE")
								.toString();
						id_status_daftar = mz.get("ID_STATUS").toString();
					}
					context.put("POPSimati", nama_simati.toUpperCase());
					context.put("POPNofail", no_fail);
					context.put("POPNamaPejabat", nama_pejabat.toUpperCase());
					context.put("POPDaerahMohon", daerah_mohon.toUpperCase());
					context.put("POPNoOnline", no_rujukan_online);
					context.put("id_status_daftar", id_status_daftar);
					/*
					view1 = logic_A.getJenisKp();
					this.context.put("listkp", view1);
					 */
					this.context.put("tarikhmohondaftar", currentDate);

					int idflag = 0;
					int flagNo = 0;
					idAlert = "1";
					this.context.put("IdFail", idflag);
					this.context.put("flagno", flagNo);
					this.context.put("idAlert", idAlert);

					String tempid = getParam("idtemp");

					this.context.put("IdPermohonan", tempid);
					long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
					this.context.put("IdPemohon", idPemohon);
					this.context.put("selectNegeri", HTML.SelectNegeri(
							"socNegeri", "class=autoselect"));

					view_get_userid(session);
					listUserid = logic_A.getUserIds();
					/*
					 * t = (Hashtable)listUserid.get(0); userIdDaerah =
					 * t.get("idDaerah").toString(); String idNegeri =
					 * t.get("idNegeri").toString(); this.context.put("NegId",
					 * idNegeri);
					 */

					listDaerahByUser = logic_A
							.getDaerahByNegeriUser((String) session
									.getAttribute("_ekptg_user_id"));
					this.context.put("ListDaerahByUser", listDaerahByUser);

					this.context.put("EventStatus", 0);
					this.context.put("IdSimati", 0);

					// long idp = Long.parseLong(tempid);

					// FrmPrmhnnSek8SenaraiSemakInternalData
					// frmPrmhnnSek8SenaraiSemakData = new
					// FrmPrmhnnSek8SenaraiSemakInternalData();
					logic_C.setDataSemakan(tempid);

					listSemak = logic_C.getListSemakan();
					this.context.put("ListSemakan", listSemak);

					this.context.put("noFail", getParam("txtNoFail"));
					this.context.put("idDaerahx", getParam("socDaerah"));

					String negri = getParam("socNegeri");

					// Hashtable h = (Hashtable)list2.get(0);
					if (negri == "") {
						// this.context.put("selectNegeri",
						// HTML.SelectNegeri("socNegeri",null,"class=\"autoselect\" "));
						this.context.put("negeri", "");
						this.context.put("daerah", "");

					} else {
						this.context.put("negeri", negri);
						this.context.put("daerah", getParam("socDaerah"));
						Vector s1 = logic_A.getListBandarByNegeri(Integer
								.parseInt(negri));
						this.context.put("listBandarbyNegeri", s1);

						// this.context.put("selectNegeri",
						// HTML.SelectNegeri("socNegeri",Long.parseLong(negri),"class=\"autoselect\" "));
					}
					if (getParam("socBandar") == "") {
						// this.context.put("selectNegeri",
						// HTML.SelectNegeri("socNegeri",null,"class=\"autoselect\" "));

						this.context.put("daerah", "");

					} else {

						this.context.put("daerah", getParam("socBandar"));

						// this.context.put("selectNegeri",
						// HTML.SelectNegeri("socNegeri",Long.parseLong(negri),"class=\"autoselect\" "));
					}

					this.context
					.put("tarikhMohonm", getParam("txdTarikhMohon"));
					this.context.put("namaSimatim", getParam("txtNamaSimati"));
					this.context.put("nokpbaru1m",
							getParam("txtNoKPBaruSimati1"));
					this.context.put("nokpbaru2m",
							getParam("txtNoKPBaruSimati2"));
					this.context.put("nokpbaru3m",
							getParam("txtNoKPBaruSimati3"));
					this.context.put("no_kplama_simatim",
							getParam("txtNoKPLamaSimati"));
					this.context.put("jenisKpMati",
							getParam("socJenisKPLainSimati"));
					this.context.put("no_kplain_simatim",
							getParam("txtNoKPLainSimati"));
					this.context.put("tarikh_simatim",
							getParam("txtTarikhMati"));
					this.context.put("no_kpbaru_pemohon1m",
							getParam("txtNoKPBaruPemohon1"));
					this.context.put("no_kpbaru_pemohon2m",
							getParam("txtNoKPBaruPemohon2"));
					this.context.put("no_kpbaru_pemohon3m",
							getParam("txtNoKPBaruPemohon3"));
					this.context.put("no_kplama_pemohonm",
							getParam("txtNoKPLamaPemohon"));
					// this.context.put("jenisKpPemohon",getParam("socJenisKPLainPemohon"));
					this.context.put("jenisKpPemohon",
							getParam("socJenisKPLainPemohon"));
					this.context.put("no_kplain_pemohonm",
							getParam("txtNoKPLainPemohon"));
					this.context.put("nama_pemohonm",
							getParam("txtNamaPemohon"));
					this.context.put("alamat1m", getParam("txtAlamat1"));
					this.context.put("alamat2m", getParam("txtAlamat2"));
					this.context.put("alamat3m", getParam("txtAlamat3"));
					this.context.put("bandarm", getParam("txtBandar"));
					this.context.put("poskodm", getParam("txtPoskod"));
					this.context.put("negerim", getParam("socNegeri"));
					this.context.put("umursimati", getParam("txtUmurSimati"));
					this.context.put("jantinasimati",
							getParam("socJantinaSimati"));
					this.context.put("umurpemohon", getParam("txtUmurPemohon"));
					this.context.put("jantinapemohon",
							getParam("socJantinaPemohon"));

					this.context
					.put("taraf_penting", getParam("taraf_penting"));
					this.context.put("no_tel", getParam("no_tel"));
					this.context.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
					this.context.put("no_hp", getParam("no_hp"));
					this.context.put("jenis_pej", getParam("jenis_pej"));
					this.context
					.put("jenis_pemohon", getParam("jenis_pemohon"));
					this.context.put("emel", getParam("emel"));
					this.context.put("socSaudaraWaris",
							getParam("socSaudaraWaris"));


				} else {
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
					// k.put("bandar",getParam("idPemohon"));
					k.put("bandar", getParam("txtBandar"));
					k.put("idDaerah", getParam("socDaerahinput"));

					k.put("umursimati", getParam("txtUmurSimati"));
					k.put("jantinasimati", getParam("socJantinaSimati"));
					k.put("umurpemohon", getParam("txtUmurPemohon"));
					k.put("jantinapemohon", getParam("socJantinaPemohon"));

					k.put("taraf_penting", getParam("taraf_penting"));
					k.put("no_tel", getParam("no_tel"));
					k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
					k.put("no_hp", getParam("no_hp"));
					k.put("jenis_pej", getParam("jenis_pej"));
					k.put("jenis_pemohon", getParam("jenis_pemohon"));
					k.put("socSaudaraWaris", getParam("socSaudaraWaris"));

					v.addElement(k);
					this.context.put("View", v);

					// retrieve data
					// view_mode_pemohon(session);
					String id = tempid;
					// logic_A.setData_online(id);
					list2 = logic_A.setData_online(id, (String) session
							.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, id, "Y", "", "T");
					this.context.put("View", list2);

					String IdNeg = getParam("negid");
					this.context.put("NegId", IdNeg);

					Hashtable h = (Hashtable) list2.get(0);
					if (getParam("socNegeri") == "") {

						this.context.put("negeri", "");
						// this.context.put("daerah","");
						// this.context.put("selectNegeri",
						// HTML.SelectNegeri("socNegeri","class=autoselect"));
					} else {
						this.context.put("negeri", getParam("socNegeri"));

						Vector s2 = logic_A.getListBandarByNegeri(Integer
								.parseInt(getParam("socNegeri")));
						this.context.put("listBandarbyNegeri", s2);
						// this.context.put("daerah","");
						// this.context.put("selectNegeri",
						// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
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

					view_get_id(session);
					listIds = logic_A.getIds();

					Hashtable kx = (Hashtable) listIds.get(0);
					this.context.put("IdSimati", Long.parseLong(kx.get(
							"idsimati").toString()));
					this.context.put("IdPemohon", Long.parseLong(kx.get(
							"idpemohon").toString()));
					this.context.put("IdPermohonan", getParam("idPermohonan"));

					this.context.put("duplicate", "yes");

				}

			}

			else {

				if (cntID == 0) {
					// addPermohonan(session,userIdNeg,userIdPejabat,userIdKodDaerah,userIdKodNegeri);
					if (bolehsimpan.equals("yes")) {
						System.out.println("Bolehsimpan");
						addPermohonan(session);
						this.context.put("appear_skrin_info", "simpan_daftar");
					}
				} else {
					if (bolehsimpan.equals("yes")) {
						System.out.println("BolehsimpanUpdate");
						updatePermohonan(session);
						this.context.put("appear_skrin_info", "kemaskini");
					}
				}
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
				// logic_A.setData_online(id);
				Vector list3 = logic_A.setData_online(id, (String) session
						.getAttribute("_ekptg_user_id"));
				headerppk_baru(session, id, "Y", "", "T");
				this.context.put("View", list3);
				this.context.put("ViewX", list2);
				Hashtable h = null;
				try {
					h = (Hashtable) list3.get(0);
				}

				catch (Exception e) {
					throw new Exception(
							"Ralat dalam mendapatkan maklumat.Sila cuba lagi.");
				}

				if (h.get("pmidnegeri").toString() == "") {
					this.context.put("negeri", "");
					this.context.put("daerah", "");
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri",null,"class=\"autoselect\" disabled "));
				} else {
					this.context.put("negeri", h.get("pmidnegeri").toString());
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(h.get("pmidnegeri").toString()));
					this.context.put("listBandarbyNegeri", s3);
					// this.context.put("daerah","");
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
				}

				if (h.get("idbandar").toString() == "") {
					this.context.put("daerah", "");
				} else {
					this.context.put("daerah", h.get("idbandar").toString());

				}

				// view_get_id(session);
				// int id = Integer.parseInt(getParam("idPermohonan"));
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

				// Vector listDaerahByUser =
				// FrmPrmhnnSek8DaftarSek8Data.getDaerahByNegeriUser("14");

				this.context.put("ListDaerahByUser", listDaerahByUser);
				this.context.put("tarikhmohondaftar", "");

			}

			String ido = getParam("idPermohonan");
			logic_A.setDataFail(ido);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			logic.setDataPemohonOB(ido);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPrmhnnSek8DaftarSek8_online.jsp";

		}

		else if ("getBandar".equals(submit)) {

			String IdPermohonan = getParam("idPermohonan");
			String Idppp = getParam("idpermohonan");

			// System.out.println("Idppp Duplicate"+Idppp);
			check_mode_permohonan(session);
			chkId = logic_A.getId();
			Hashtable b = (Hashtable) chkId.get(0);
			int cntID = Integer.parseInt(b.get("count_id").toString());

			// view_get_userid(session);
			String useridx = (String) session.getAttribute("_ekptg_user_id");
			// String useridx = "14";
			logic_A.setGetUserId(useridx);
			listUserid = logic_A.getUserIds();
			/*
			 * Hashtable t = (Hashtable)listUserid.get(0); String userIdDaerah =
			 * t.get("idDaerah").toString(); String userIdNeg =
			 * t.get("idNegeri").toString(); String userIdKodDaerah =
			 * t.get("kodDaerah").toString(); String userIdKodNegeri =
			 * t.get("kodnegeri").toString(); String userIdPejabat =
			 * t.get("idpejabat").toString();
			 */

			String negeribandar = getParam("socNegeri");
			String bandar_bandar = getParam("socBandar");

			if (cntID == 0) {

				// //System.out.println("DAH ADA SIMATI, size::"+listKPSimati.size());
				// this.context.put("duplicate", "yes");
				// this.context.put("duplicate", "");
				/*
				view1 = logic_A.getJenisKp();
				this.context.put("listkp", view1);
				 */
				this.context.put("tarikhmohondaftar", currentDate);

				int idflag = 0;
				int flagNo = 0;
				idAlert = "1";
				this.context.put("IdFail", idflag);
				this.context.put("flagno", flagNo);
				this.context.put("idAlert", idAlert);

				String tempid = getParam("idtemp");

				this.context.put("IdPermohonan", tempid);
				long idPemohon = DB.getNextID("TBLPPKPEMOHON_SEQ");
				this.context.put("IdPemohon", idPemohon);
				this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						"class=autoselect"));

				view_get_userid(session);
				listUserid = logic_A.getUserIds();
				// t = (Hashtable)listUserid.get(0);
				// userIdDaerah = t.get("idDaerah").toString();
				// String idNegeri = t.get("idNegeri").toString();
				// this.context.put("NegId", idNegeri);

				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);

				this.context.put("EventStatus", 0);
				this.context.put("IdSimati", 0);

				String idper = tempid;

				// FrmPrmhnnSek8SenaraiSemakInternalData
				// frmPrmhnnSek8SenaraiSemakData = new
				// FrmPrmhnnSek8SenaraiSemakInternalData();
				logic_C.setDataSemakan(idper);

				listSemak = logic_C.getListSemakan();
				this.context.put("ListSemakan", listSemak);

				this.context.put("noFail", getParam("txtNoFail"));
				this.context.put("idDaerahx", getParam("socDaerah"));

				String negri = getParam("socNegeri");

				// Hashtable h = (Hashtable)list2.get(0);
				if (negri == "") {
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri",null,"class=\"autoselect\" "));
					this.context.put("negeri", "");
					this.context.put("daerah", "");
					this.context.put("listBandarbyNegeri", "");
				} else {
					this.context.put("negeri", negri);
					Vector s = logic_A.getListBandarByNegeri(Integer
							.parseInt(negri));
					this.context.put("listBandarbyNegeri", s);
					this.context.put("daerah", "");
					// this.context.put("daerah","");

					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri",Long.parseLong(negri),"class=\"autoselect\" "));
				}

				this.context.put("tarikhMohonm", getParam("txdTarikhMohon"));
				this.context.put("namaSimatim", getParam("txtNamaSimati"));
				this.context.put("nokpbaru1m", getParam("txtNoKPBaruSimati1"));
				this.context.put("nokpbaru2m", getParam("txtNoKPBaruSimati2"));
				this.context.put("nokpbaru3m", getParam("txtNoKPBaruSimati3"));
				this.context.put("no_kplama_simatim",
						getParam("txtNoKPLamaSimati"));
				this.context.put("jenisKpMati",
						getParam("socJenisKPLainSimati"));
				this.context.put("no_kplain_simatim",
						getParam("txtNoKPLainSimati"));
				this.context.put("tarikh_simatim", getParam("txtTarikhMati"));
				this.context.put("no_kpbaru_pemohon1m",
						getParam("txtNoKPBaruPemohon1"));
				this.context.put("no_kpbaru_pemohon2m",
						getParam("txtNoKPBaruPemohon2"));
				this.context.put("no_kpbaru_pemohon3m",
						getParam("txtNoKPBaruPemohon3"));
				this.context.put("no_kplama_pemohonm",
						getParam("txtNoKPLamaPemohon"));
				// this.context.put("jenisKpPemohon",getParam("socJenisKPLainPemohon"));
				this.context.put("jenisKpPemohon",
						getParam("socJenisKPLainPemohon"));
				this.context.put("no_kplain_pemohonm",
						getParam("txtNoKPLainPemohon"));
				this.context.put("nama_pemohonm", getParam("txtNamaPemohon"));
				this.context.put("alamat1m", getParam("txtAlamat1"));
				this.context.put("alamat2m", getParam("txtAlamat2"));
				this.context.put("alamat3m", getParam("txtAlamat3"));
				this.context.put("bandarm", getParam("txtBandar"));
				this.context.put("poskodm", getParam("txtPoskod"));
				this.context.put("negerim", getParam("socNegeri"));

				this.context.put("umursimati", getParam("txtUmurSimati"));
				this.context.put("jantinasimati", getParam("socJantinaSimati"));
				this.context.put("umurpemohon", getParam("txtUmurPemohon"));
				this.context.put("jantinapemohon",
						getParam("socJantinaPemohon"));

				this.context.put("taraf_penting", getParam("taraf_penting"));
				this.context.put("no_tel", getParam("no_tel"));
				this.context.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
				this.context.put("no_hp", getParam("no_hp"));
				this.context.put("jenis_pej", getParam("jenis_pej"));
				this.context.put("jenis_pemohon", getParam("jenis_pemohon"));
				this.context
				.put("socSaudaraWaris", getParam("socSaudaraWaris"));

			} else {
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
				// k.put("bandar",getParam("idPemohon"));
				k.put("bandarsurat", getParam("txtBandar"));
				k.put("idDaerah", getParam("socDaerahinput"));

				k.put("umursimati", getParam("txtUmurSimati"));
				k.put("jantinasimati", getParam("socJantinaSimati"));
				k.put("umurpemohon", getParam("txtUmurPemohon"));
				k.put("jantinapemohon", getParam("socJantinaPemohon"));

				k.put("socSaudaraWaris", getParam("socSaudaraWaris"));

				k.put("taraf_penting", getParam("taraf_penting"));
				k.put("no_tel", getParam("no_tel"));
				k.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
				k.put("jenis_pej", getParam("jenis_pej"));
				k.put("no_hp", getParam("no_hp"));
				k.put("emel", getParam("emel"));
				k.put("jenis_pemohon", getParam("jenis_pemohon"));

				v.addElement(k);
				this.context.put("View", v);

				// retrieve data
				// view_mode_pemohon(session);
				//int id = Integer.parseInt(tempid);
				String id = getParam(tempid);
				// logic_A.setData_online(id);
				// list2 =
				// logic_A.setData_online(id,(String)session.getAttribute("_ekptg_user_id"));
				// this.context.put("View", list2);

				String IdNeg = getParam("negid");
				this.context.put("NegId", IdNeg);

				// Hashtable h = (Hashtable)list2.get(0);
				// if (h.get("pmidnegeri").toString() == "") {
				if (getParam("socNegeri") == "") {

					this.context.put("negeri", "");
					this.context.put("daerah", "");
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri","class=autoselect"));
				} else {
					this.context.put("negeri", getParam("socNegeri"));
					Vector s = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeri")));
					this.context.put("listBandarbyNegeri", s);
					this.context.put("daerah", "");
					// this.context.put("selectNegeri",
					// HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("pmidnegeri").toString()),"class=\"autoselect\" disabled"));
				}

				listDaerahByUser = logic_A
						.getDaerahByNegeriUser((String) session
								.getAttribute("_ekptg_user_id"));
				this.context.put("ListDaerahByUser", listDaerahByUser);

				view_get_id(session);
				listIds = logic_A.getIds();

				Hashtable kx = (Hashtable) listIds.get(0);
				this.context.put("IdSimati", Long.parseLong(kx.get("idsimati")
						.toString()));
				this.context.put("IdPemohon", Long.parseLong(kx
						.get("idpemohon").toString()));
				this.context.put("IdPermohonan", getParam("idPermohonan"));

				this.context.put("duplicate", "");

			}

			String ido = getParam("idPermohonan");
			logic_A.setDataFail(ido);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			logic.setDataPemohonOB(ido);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			vm = "app/ppk/frmPrmhnnSek8DaftarSek8_online.jsp";

		} else if ("Pemohon".equals(submit)) {
			String check_copy = getParam("copy");
			this.context.put("check_copy", check_copy);

			// System.out.println("COPY CHECK :"+check_copy);

			this.context.put("readmode", "");
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");

			if ("Pemohonview".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				logic.setDataPemohon(id);
				listPemohon = logic.getDataPemohon();
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

				logic.setDataPemohonOB(id);
				listPemohonOB = logic.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
				logic.setCheckPeguam_online(id);
				listCheckPeguam = logic.getCheckPeguam_online();
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

				logic.setCheckPeguam_online(id);
				listCheckPeguam = logic.getCheckPeguam_online();
				this.context.put("listCheckPeguam", listCheckPeguam);

				if (listCheckPeguam.size() != 0) {
					this.context.put("show_peguam_tab", "yes");
				} else {
					this.context.put("show_peguam_tab", "");
				}

				logic.setDataPemohon(id);
				listPemohon = logic.getDataPemohon();
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
				logic.setDataPemohonOB(id);
				listPemohonOB = logic.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
				logic.setDataSimati(id);
				listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);
				logic.setDataPemohon(id);
				listPemohon = logic.getDataPemohon();

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
				logic.setCheckPeguam_online(id);
				listCheckPeguam = logic.getCheckPeguam_online();
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
				logic.setDataPemohonOB(id);
				listPemohonOB = logic.getDataPemohonOB();
				this.context.put("listPemohonOB", listPemohonOB);
				String idsaudara = getParam("socTarafKePemohonanPemohon");
				// System.out.println("SAUUUU"+idsaudara);

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

				// System.out.println("bandar event :"+getParam("bandar_event"));

				if (bandarevent.equals("no")) {
					id_saudare = "";
					id_bandartetap = dapatbandar;
					id_bandarsurat = dapatbandarsurat;
					// //System.out.println("NO");
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
				k.put("status_peguam", getParam("status_peguam"));
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
				logic.setCheckPeguam_online(id);
				listCheckPeguam = logic.getCheckPeguam_online();
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
				this.context.put("appear_skrin_info", "kemaskini");
				logic.setDataPemohon(id);
				listPemohon = logic.getDataPemohon();
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

				logic.setCheckPeguam_online(id);
				listCheckPeguam = logic.getCheckPeguam_online();
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
			logic.setDataPemohonOB(id);
			listPemohonOB = logic.getDataPemohonOB();
			this.context.put("listPemohonOB", listPemohonOB);

			// logic_A.setData_online(id,(String)
			// session.getAttribute("_ekptg_user_id"));
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);

			vm = "app/ppk/frmPrmhnnSek8Pemohon.jsp";
		} else if ("Waris".equals(submit)) {
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
					logic.setDataWarisLapisan(idparent);
					listWarisLapisan = logic.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic.setDataWarisParent(idparent);
					listWarisParent = logic.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);
					this.context.put("show_lapisan_berikut_tambah", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisanSimpan", "Simpan");
				}
				if (getParam("up_in").equals("up_lap")) {

					String idparent = getParam("idparentlapis");
					logic.setDataWarisLapisan(idparent);
					listWarisLapisan = logic.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic.setDataWarisParent(idparent);
					listWarisParent = logic.getDataWarisParent();

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
					logic.setDataWarisLapisan(idparent);
					listWarisLapisan = logic.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic.setDataWarisParent(idparent);
					listWarisParent = logic.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);
					this.context.put("show_lapisan_berikut_tambah", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisanSimpan", "Simpan");
				}
				if (getParam("up_in").equals("up_lap")) {

					String idparent = getParam("idparentlapis");
					logic.setDataWarisLapisan(idparent);
					listWarisLapisan = logic.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);

					logic.setDataWarisParent(idparent);
					listWarisParent = logic.getDataWarisParent();
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
				list = logic_A.setData_online(id, (String) session
						.getAttribute("_ekptg_user_id"));
				myLogger.info(" LIST ATAS : "+list);
				headerppk_baru(session, id, "Y", "", "T");
				// list = logic_A.getData();

				Hashtable x = (Hashtable) list.get(0);
				myLogger.info(" HASHTABLE ATAS : "+x);
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
					myLogger.info("CHECK XXXXXXXXXXXXXX :"+x);
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
					logic.setDataWarisLapisan(idparent);
					listWarisLapisan = logic.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);
					logic.setDataWarisParent(idparent);
					listWarisParent = logic.getDataWarisParent();
					this.context.put("listWarisParent", listWarisParent);
					this.context.put("show_lapisan_berikut_tambah", "yes");
					this.context.put("show_button_lapisan", "yes");
					this.context.put("show_lapisan_berikut", "yes");
					this.context.put("buttonwarislapisanSimpan", "Simpan");
				}
				if (getParam("up_in").equals("up_lap")) {

					String idparent = getParam("idparentlapis");
					logic.setDataWarisLapisan(idparent);
					listWarisLapisan = logic.getDataWarisLapisan();
					this.context.put("listWarisLapisan", listWarisLapisan);

					logic.setDataWarisParent(idparent);
					listWarisParent = logic.getDataWarisParent();
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
				logic.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
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

				logic.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic
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
				logic.setDataWarisLapisan(idwaris);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic.setDataWarisParent(idwaris);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("readmode", "");
				this.context.put("listWa", listWarisParent);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");
			} else if ("Lapisan_berikut_lapisan".equals(mode)) {
				String idwaris = getParam("idwarisup");
				this.context.put("ip", idwaris);
				logic.setDataWarisLapisan(idwaris);
				logic.setDataWarisParent(idwaris);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic.setDataWarisParent(idwaris);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWa", listWarisParent);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");

			} else if ("tambah_waris_lapisan".equals(mode)) {
				String idparent = getParam("idparentlapis");
				String idwaris = getParam("idwarisup");
				if (bolehsimpan.equals("yes")) {
					addWarisBerikut(session);
					this.context.put("appear_skrin_info", "simpan");
				}
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);

				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic.setDataWaris(idwaris);
				listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");

			} else if ("GetwarisLapisan".equals(mode)) {
				this.context.put("readmode", disability1);
				String idwaris = getParam("idwarislapis");
				String idparent = getParam("idparentlapis");
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				logic.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				this.context.put("listWarisParent", listWarisParent);
				logic.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic
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
				logic.deleteWarisHubungan(idwarisup,
						getParam("id_Permohonansimati"));
				logic.deleteWaris(idwarisup, getParam("id_Permohonansimati"));
				String idparent = getParam("idparentlapis");
				this.context.put("idparent", idparent);
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				String id_Permohonansimati = getParam("id_Permohonansimati");

				logic.setDataWarisOB(id_Permohonansimati);
				listWarisOB = logic.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);

				this.context.put("show_lapisan_berikut", "yes");
				this.context.put("nk_tambah_lapisan", "yes");

			} else if ("GetwarisLapisanX".equals(mode)) {
				String idwaris = getParam("idwarislapisX");
				String idparent = getParam("idparentlapisX");
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				logic.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic
						.getDataWarisLapisanIdMatiDelete();
				this.context.put("listWarisLapisanIdMatiDelete",
						listWarisLapisanIdMatiDelete);
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
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

				logic.setDataWarisParent(idp);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				logic.setDataWarisLapisan(idp);
				listWarisLapisan = logic.getDataWarisLapisan();
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
					this.context.put("appear_skrin_info", "simpan");
				}
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				String id_Permohonansimati = getParam("id_Permohonansimati");

				logic.setDataWarisOB(id_Permohonansimati);
				listWarisOB = logic.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_tambah_waris1", "yes");
				this.context.put("button_Kembali1", "yes");
			} else if ("hapus_waris".equals(mode)) {
				String idwarisup = getParam("idwarisup");
				logic.deleteWaris(idwarisup, getParam("id_Permohonansimati"));
				if (getParam("checkHidupWaris").equals("")) {
					this.context.put("show_lapisan_waris", "");
				} else {
					this.context.put("show_lapisan_waris", "yes");
				}
				String id_Permohonansimati = getParam("id_Permohonansimati");

				logic.setDataWarisOB(id_Permohonansimati);
				listWarisOB = logic.getDataWarisOB();
				this.context.put("listWarisOB", listWarisOB);
				this.context.put("show_senarai_lapis_pertama", "yes");
				this.context.put("show_lapisan_bawah", "yes");
				this.context.put("show_tambah_waris1", "yes");
				this.context.put("button_Kembali1", "yes");
			} else if ("kemaskini_waris".equals(mode)) {
				String idwaris = getParam("idwarisup");
				logic.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
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
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				logic.setDataWarisUpdate(idwaris,
						getParam("id_Permohonansimati"));
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				listWarisUpdate = logic.getDataWarisUpdate();
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
				this.context.put("listWarisParent", listWarisParent);
				this.context.put("listWarisLapisan", listWarisLapisan);
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				logic.setDataWarisLapisanIdMatiDelete(idwaris);
				listWarisLapisanIdMatiDelete = logic
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
					this.context.put("appear_skrin_info", "kemaskini");
				}

				logic.setDataWarisUpdate(getParam("idwarisup"),
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
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
				// int idxx= Integer.parseInt(getParam("idwarisup"));
				disability1 = "disabled";
				if (bolehsimpan.equals("yes")) {
					updatewaris(session);
					this.context.put("appear_skrin_info", "kemaskini");
				}
				logic.setDataWarisUpdate(getParam("idwarisup"),
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisUpdate", listWarisUpdate);
				logic.setDataWarisUpdate(getParam("idwarisup"),
						getParam("id_Permohonansimati"));
				listWarisUpdate = logic.getDataWarisUpdate();
				this.context.put("listWarisLapisanUpdate", listWarisUpdate);
				logic.setDataWaris(getParam("idwarisup"));
				listWaris = logic.getDataWaris();
				this.context.put("listWaris", listWaris);
				logic.setDataWarisLapisan(idparent);
				listWarisLapisan = logic.getDataWarisLapisan();
				this.context.put("listWarisLapisan", listWarisLapisan);
				logic.setDataWarisParent(idparent);
				listWarisParent = logic.getDataWarisParent();
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
			logic.setDataSimati(id);
			listSimati = logic.getDataSimati();
			this.context.put("listSimati", listSimati);
			logic.setDataWaris(id_Permohonansimati);
			listWaris = logic.getDataWaris();
			this.context.put("listWaris", listWaris);
			logic.setDataWarisLapisanIdMati(id_Permohonansimati);
			listWarisLapisanIdMati = logic.getDataWarisLapisanIdMati();
			this.context.put("listWarisLapisanIdMati", listWarisLapisanIdMati);
			logic.setDataWarisOB(id_Permohonansimati);
			listWarisOB = logic.getDataWarisOB();
			this.context.put("listWarisOB", listWarisOB);
			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 2);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			// logic_A.setData_online(id,(String)
			// session.getAttribute("_ekptg_user_id"));
			// list = logic_A.getData();
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			this.context.put("View", list);
			headerppk_baru(session, id, "Y", "", "T");

			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);

			Vector list_getListOBUpdate = null;
			list_getListOBUpdate = logic_A.getListOBUpdate(id_Permohonansimati);
			this.context.put("list_getListOBUpdate", list_getListOBUpdate);
			/*
			Vector kenegaraan = null;
			kenegaraan = mainheader.kenegaraan();
			this.context.put("kenegaraan", kenegaraan);
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
						this.context.put("appear_skrin_info", "simpan");
					}
				} else {
					if (bolehsimpan.equals("yes")) {
						addPenting(session);
						this.context.put("appear_skrin_info", "simpan");
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
					// System.out.println(key+":"+value);
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
					logic.deletePenghutang(iddob);
				} else {
					logic.deletePenting(iddob);
				}
				this.context.put("tambah_ob_button", "yes");
				this.context.put("kembali_ob_button", "yes");
			}

			else if ("GetPenting".equals(mode)) {

				String idob = getParam("txtIdOb");

				if ("Penghutang".equals(submit)) {
					logic.setDataPenghutangbyIDOB(idob);
					listPenghutangbyIDOB = logic.getDataPenghutangbyIDOB();
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
					logic.setDataPentingbyIDOB(idob,
							getParam("id_Permohonansimati"));
					listPentingbyIDOB = logic.getDataPentingbyIDOB();
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
						this.context.put("appear_skrin_info", "kemaskini");
					}
					logic.setDataPenghutangbyIDOB(idob);
					listPenghutangbyIDOB = logic.getDataPenghutangbyIDOB();

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
						this.context.put("appear_skrin_info", "kemaskini");
					}
					logic.setDataPentingbyIDOB(idob,
							getParam("id_Permohonansimati"));
					listPentingbyIDOB = logic.getDataPentingbyIDOB();
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
			logic.setDataPenting(id_Permohonansimati);
			listPenting = logic.getDataPenting();
			this.context.put("listPenting", listPenting);
			logic.setDataPenghutang(id_Permohonansimati);
			listPenghutang = logic.getDataPenghutang();
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
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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

				this.context.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				this.context.put("nilaitarikhmati",
						getParam("txtNilaiTarikhMati"));
				this.context.put("nilaitarikhmohon",
						getParam("txtNilaiTarikhMohon"));
				this.context.put("catatan", getParam("txtCatatan"));
				// ::
				this.context.put("butiran", getParam("butiran"));
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
						listnegeribydaerah = logic_A.getListDaerahbyNegeri(idne);
						this.context.put("listDaerahbyNegeri", listnegeribydaerah);
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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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
					this.context.put("appear_skrin_info", "simpan");
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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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

				String idha = getParam("idha");

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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
				// this.context.put("EventStatus", eventstatus);
				this.context.put("EventStatus", 3);

			} else if ("kemaskini_harta".equals(mode)) {
				String id = getParam("idPermohonan");
				String id2 = getParam("idPemohon");
				String id1 = getParam("idSimati");
				String mati = getParam("id_Permohonansimati");

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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
					this.context.put("appear_skrin_info", "kemaskini");
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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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
				// this.context.put("EventStatus", eventstatus);
				this.context.put("EventStatus", 3);
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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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

				if (getParam("socNegeriHtaam") != ""
						&& getParam("socNegeriHtaam") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaam")));
					this.context.put("listBandarSuratbyNegeri", s3);
					System.out.println("listBandarSuratbyNegeri30");
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
					System.out.println("listBandarSuratbyNegeri30a");
				}

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

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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
			// logic_A.setData_online(id);
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			// String id = getParam("idPermohonan");
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati, (String) session
					.getAttribute("_ekptg_user_id"));

			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);

			vm = "app/ppk/frmPrmhnnSek8HA.jsp";
		}

		else if ("Peguam".equals(submit)) {

			String id = getParam("idPermohonan");
			String id2 = getParam("idPemohon");
			logic.setCheckPeguam_online(id);
			listCheckPeguam = logic.getCheckPeguam_online();
			this.context.put("listCheckPeguam", listCheckPeguam);
			logic.setDataPeguam(id2);
			listpeguamcheck = logic.getDataPeguam();
			logic.setDataPeguam(id2);
			listpeguam = logic.getDataPeguam();
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
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon"); String mati =
				 * getParam("id_Permohonansimati"); logic.setDataPeguam(id2);
				 * listpeguam=logic.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			}

			else if ("peguam_baru".equals(mode)) {
				/*
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon"); logic.setCheckPeguam_online(id);
				 * listCheckPeguam = logic.getCheckPeguam_online();
				 * this.context.put("listCheckPeguam", listCheckPeguam);
				 * 
				 * logic.setDataPeguam(id2);
				 * listpeguamcheck=logic.getDataPeguam();
				 * logic.setDataPeguam(id2); listpeguam=logic.getDataPeguam();
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
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon");
				 */
				// int idpeguam = Integer.parseInt(getParam("idPeguam"));
				logic.setDataPeguamX(getParam("idPeguam"));
				listpeguamX = logic.getDataPeguamX();
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
				 * logic.setDataPeguam(id2); listpeguam=logic.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

				this.context.put("show_simpan_button", "yes");
				this.context.put("button_tambah", "yes");
				this.context.put("add_new_peguam", "update");

			}

			else if ("GetPeguam".equals(mode)) {
				/*
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon");
				 */
				// int idpeguam = Integer.parseInt(getParam("idpeguam"));
				logic.setDataPeguamX(getParam("idpeguam"));
				listpeguamX = logic.getDataPeguamX();
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
				 * logic.setDataPeguam(id2); listpeguam=logic.getDataPeguam();
				 * this.context.put("listPenting",listpeguam);
				 */

				this.context.put("button_tambah", "yes");
				this.context.put("readmode", "disabled");
				this.context.put("add_new_peguam", "update");
				this.context.put("show_kemaskini_button", "yes");

			} else if ("tambah_peguam".equals(mode)) {
				if (bolehsimpan.equals("yes")) {
					tambahpeguam(session);
					this.context.put("appear_skrin_info", "simpan");
				}
				// int idX = Integer.parseInt(getParam("idPermohonan"));
				String id2X = getParam("idPemohon");
				logic.setDataPeguam(id2X);
				listpeguam = logic.getDataPeguam();
				this.context.put("listPenting", listpeguam);

				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			} else if ("hapus_peguam".equals(mode)) {
				// int idpeguam = Integer.parseInt(getParam("idPeguam"));
				logic.deletePeguam(getParam("idPeguam"));
				// String id = getParam("idPermohonan");
				String id2X = getParam("idPemohon");
				logic.setDataPeguam(id2X);
				listpeguam = logic.getDataPeguam();

				this.context.put("listPenting", listpeguam);
				this.context.put("button_Kembali", "yes");
				this.context.put("button_tambah", "yes");

			}

			else if ("simpan_peguam".equals(mode)) {

				String id2X = getParam("idPemohon");
				// int idpeguam = Integer.parseInt(getParam("idPeguam"));
				if (bolehsimpan.equals("yes")) {
					updatepeguam(session);
					this.context.put("appear_skrin_info", "kemaskini");
				}

				logic.setDataPeguamX(getParam("idPeguam"));
				listpeguamX = logic.getDataPeguamX();
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

				logic.setDataPeguam(id2X);
				listpeguam = logic.getDataPeguam();
				this.context.put("listPenting", listpeguam);

				this.context.put("add_new_peguam", "update");
				this.context.put("readmode", "disabled");
				this.context.put("show_kemaskini_button", "yes");
				this.context.put("button_tambah", "yes");

			}
			String idX = getParam("idPermohonan");
			list = logic_A.setData_online(idX, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idX, "Y", "", "T");

			this.context.put("selectedTabatas", 0);
			this.context.put("selectedTabtengah", 1);
			this.context.put("selectedTabbawah", 1);
			this.context.put("selectedTabtepi", 0);
			this.context.put("View", list);
			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);
			vm = "app/ppk/frmPrmhnnSek8Peguam.jsp";

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
			this.context.put("kemaskini_pejabat", "");

			initTabData();
			initInputPpkPengesahan();

			list = logiconline.semakDataSimati(getParam("idPermohonan"));
			this.context.put("View", list);

			View_pengesahan_pemohonan = logic_A.setData_online(getParam("idPermohonan"), (String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");
			this.context.put("View_pengesahan_pemohonan", View_pengesahan_pemohonan);

			Hashtable n = (Hashtable) list.get(0);
			String id1 = (String) n.get("idsimati");
			String id2 = (String) n.get("idPemohon");
			String id = getParam("idPermohonan");

			this.context.put("idStatus", (String) n.get("idstatus"));
			this.context.put("IdPermohonan", id);
			this.context.put("idSimati", id1);
			this.context.put("idPemohon", id2);

			if (n.get("nopermohonanonline") == "") n.put("nopermohonanonline", "");
			this.context.put("nopermohonanonline", n.get("nopermohonanonline").toString());

			if (n.get("flaghubungan") == "") n.put("flaghubungan", "0");
			int flaghub = Integer.parseInt(n.get("flaghubungan").toString());
			this.context.put("idpermohonansimati", n.get("idpermohonansimati"));
			this.context.put("flaghub", flaghub);

			// negeri based on negeri hta
			if (n.get("idpermohonansimati") == "") n.put("idpermohonansimati", "0");
			Vector listNegeriByPpkUnit = logiconline.getListNegeriByhta((String) getParam("id_Permohonansimati"));
			this.context.put("senaraiNegeriByPpkUnit", listNegeriByPpkUnit);
			this.context.put("saizData", listNegeriByPpkUnit.size());

			if (n.get("idnegerimhn").toString() != "") {
				myLogger.info("part 1");
				int idnegerimhn = Integer.parseInt(n.get("idnegerimhn")
						.toString());
				int iddaerahmhn = Integer.parseInt(n.get("iddaerahmhn")
						.toString());

				this.context.put("selNegeri", 0);
				this.context.put("selDaerah", 0);
				this.context.put("negerimhn", idnegerimhn);
				this.context.put("daerahmhn", iddaerahmhn);

				listnegeribydaerah = logiconline.getListDaerahByhta(
						idnegerimhn, id1);

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

			} else if (n.get("idnegerimhn").toString() != ""
					&& n.get("iddaerahmhn").toString() != "") {
				myLogger.info("part 2");
				Vector getAddressPpkDaerahmhn = logiconline.getAddress(Integer
						.parseInt(n.get("iddaerahmhn").toString()));
				this.context.put("selectedPpkAddress", getAddressPpkDaerahmhn);
			} else {
				myLogger.info("part 3");
				initInputPpkPengesahan();
			}

			if ("pengesahan_permohonan".equals(mode)) {
				initTabData();

				id = getParam("idPermohonan");
				id2 = getParam("idPemohon");
				id1 = getParam("idSimati");

				this.context.put("selNegeri", "0");
				this.context.put("selDaerah", "0");
				this.context.put("id", id);
				this.context.put("idPemohon", id2);
				this.context.put("idSimati", id1);

				this.context.put("namapejabat", "");
				this.context.put("alamat1", "");
				this.context.put("alamat2", "");
				this.context.put("alamat3", "");
				this.context.put("poskod", "");
				this.context.put("no_tel", "");
				this.context.put("nama_pelbagainegara", "");
				this.context.put("no_tel_samb", "");

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
					String nofax = m.get("nofax").toString();
					String notel_sambungan = m.get("notel_sambungan")
							.toString();
					String negeriNama = m.get("negerinama").toString();

					this.context.put("namapejabat", namaPejabat);
					this.context.put("alamat1", alamatOne);
					this.context.put("alamat2", alamatTwo);
					this.context.put("alamat3", alamatThree);
					this.context.put("poskod", poskod);
					this.context.put("no_tel", notel);
					this.context.put("no_fax", nofax);
					//this.context.put("nama_pelbagainegara", nama_pelbagainegara);
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

					listnegeribydaerah = logiconline.getListDaerahByhta(Integer
							.parseInt(getParam("socNegeriPengesahan")), id1);
					this.context.put("selectedDaerah", listnegeribydaerah);

				} else {
					this.context.put("selectedDaerah", "");
					this.context.put("selNegeri", 0);
				}
				this.context.put("kemaskini_pejabat", "yes");
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
				/*
				 * listnegeribydaerah =
				 * FrmPrmhnnSek8DaftarSek8Data.getListDaerahbyNegeri
				 * (Integer.parseInt(getParam("socNegeriPengesahan")));
				 * this.context.put("selectedDaerah",listnegeribydaerah);
				 */
				listnegeribydaerah = logiconline.getListDaerahByhta(Integer
						.parseInt(getParam("socNegeriPengesahan")), id1);
				this.context.put("selectedDaerah", listnegeribydaerah);
				Vector getAddressPpk = logiconline.getAddress(Integer
						.parseInt(getParam("socDaerahPengesahan")));
				this.context.put("selectedPpkAddress", getAddressPpk);
				this.context.put("kemaskini_pejabat", "yes");

			} else if ("cetak_surat".equals(mode)) {
				initTabData();

				list = logiconline.semakDataSimati(getParam("idPermohonan"));
				this.context.put("View", list);
				n = (Hashtable) list.get(0);
				id1 = (String) n.get("idsimati");
				id2 = (String) n.get("idPemohon");
				id = getParam("idPermohonan");
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
				if (bolehsimpan.equals("yes")) {
					logiconline.insertDaerahMohon(idnegeri, iddaerah,
							idPermohonan, (String) session
							.getAttribute("_ekptg_user_id"), (String) t
							.get("noidfail"));
					this.context.put("appear_skrin_info", "hantar");
				}
				Vector getAddressPpk = logiconline.getAddress(iddaerah);
				this.context.put("selectedPpkAddress", getAddressPpk);

				this.context.put("idStatus", logiconline
						.getIdStatus(getParam("idPermohonan")));

				list = logiconline.semakDataSimati(getParam("idPermohonan"));
				Hashtable nx = (Hashtable) list.get(0);
				int idnegerimhn = Integer.parseInt(nx.get("idnegerimhn")
						.toString());
				int iddaerahmhn = Integer.parseInt(nx.get("iddaerahmhn")
						.toString());

				listnegeribydaerah = logiconline.getListDaerahByhta(Integer
						.parseInt(getParam("socNegeriPengesahan")), 
						id1);
				this.context.put("selectedDaerah", listnegeribydaerah);

				this.context.put("negerimhn", idnegerimhn);
				this.context.put("daerahmhn", iddaerahmhn);
				// vm = "app/ppk/frmPrmhnnBorangAPengesahan.jsp";

			} else if ("kemaskini_pejabat".equals(mode)) {
				initTabData();

				list = logiconline.semakDataSimati(getParam("idPermohonan"));
				this.context.put("View", list);
				n = (Hashtable) list.get(0);
				id1 = (String) n.get("idsimati");
				id2 = (String) n.get("idPemohon");
				id = getParam("idPermohonan");
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

				// logiconline.insertDaerahMohon(idnegeri, iddaerah,
				// idPermohonan,(String)session.getAttribute("_ekptg_user_id"),(String)t.get("noidfail"));

				Vector getAddressPpk = logiconline.getAddress(iddaerah);
				this.context.put("selectedPpkAddress", getAddressPpk);

				this.context.put("idStatus", logiconline
						.getIdStatus(getParam("idPermohonan")));

				list = logiconline.semakDataSimati(getParam("idPermohonan"));
				Hashtable nx = (Hashtable) list.get(0);
				int idnegerimhn = Integer.parseInt(nx.get("idnegerimhn")
						.toString());
				int iddaerahmhn = Integer.parseInt(nx.get("iddaerahmhn")
						.toString());

				listnegeribydaerah = logiconline.getListDaerahByhta(Integer
						.parseInt(getParam("socNegeriPengesahan")),id1);
				this.context.put("selectedDaerah", listnegeribydaerah);

				this.context.put("negerimhn", idnegerimhn);
				this.context.put("daerahmhn", iddaerahmhn);
				this.context.put("kemaskini_pejabat", "yes");

				// vm = "app/ppk/frmPrmhnnBorangAPengesahan.jsp";

			}

			else if ("simpan_pejabat".equals(mode)) {
				initTabData();

				list = logiconline.semakDataSimati(getParam("idPermohonan"));
				this.context.put("View", list);
				n = (Hashtable) list.get(0);
				id1 = (String) n.get("idsimati");
				id2 = (String) n.get("idPemohon");
				id = getParam("idPermohonan");
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
				if (bolehsimpan.equals("yes")) {
					logiconline.insertDaerahMohon(idnegeri, iddaerah,
							idPermohonan, (String) session
							.getAttribute("_ekptg_user_id"), (String) t
							.get("noidfail"));
					this.context.put("appear_skrin_info", "simpan");
					View_pengesahan_pemohonan = logic_A.setData_online(
							getParam("idPermohonan"), (String) session
							.getAttribute("_ekptg_user_id"));
					headerppk_baru(session, getParam("idPermohonan"), "Y", "",
							"T");
					this.context.put("View_pengesahan_pemohonan",
							View_pengesahan_pemohonan);
				}

				Vector getAddressPpk = logiconline.getAddress(iddaerah);
				this.context.put("selectedPpkAddress", getAddressPpk);

				this.context.put("idStatus", logiconline
						.getIdStatus(getParam("idPermohonan")));

				list = logiconline.semakDataSimati(getParam("idPermohonan"));
				Hashtable nx = (Hashtable) list.get(0);
				int idnegerimhn = Integer.parseInt(nx.get("idnegerimhn")
						.toString());
				int iddaerahmhn = Integer.parseInt(nx.get("iddaerahmhn")
						.toString());

				listnegeribydaerah = logiconline.getListDaerahByhta(Integer
						.parseInt(getParam("socNegeriPengesahan")),id1);
				this.context.put("selectedDaerah", listnegeribydaerah);

				this.context.put("negerimhn", idnegerimhn);
				this.context.put("daerahmhn", iddaerahmhn);
				this.context.put("kemaskini_pejabat", "");

				// vm = "app/ppk/frmPrmhnnBorangAPengesahan.jsp";

			}
			// vm = "app/ppk/frmPrmhnnBorangAPengesahan.jsp";

			/*
			 * if ("view_nilai_harta".equals(mode)) { String id =
			 * getParam("idPermohonan"); String id2 = getParam("idPemohon");
			 * String id1 = getParam("idSimati"); String mati =
			 * getParam("id_Permohonansimati");
			 * 
			 * id1 = getParam("idSimati"); logic_A.updateDataNilai(id, mati,
			 * (String) session.getAttribute("_ekptg_user_id"));
			 * 
			 * this.context.put("id", id); this.context.put("id2", id2);
			 * this.context.put("id1", id1);
			 * 
			 * logic_A.setSumDataHta(mati); sumppkhta = logic_A.getSumDataHta();
			 * this.context.put("jumppkhta", sumppkhta);
			 * 
			 * logic_A.setOverallSumHta(mati); sumoverallppkhta =
			 * logic_A.getOverallSumHta(); this.context.put("jumoverallppkhta",
			 * sumoverallppkhta);
			 * 
			 * logic_A.setDataHa(mati); listppkha = logic_A.getDataHa();
			 * this.context.put("listHa", listppkha); int countList =
			 * listppkha.size(); this.context.put("jumList", countList);
			 * 
			 * logic_A.setSumDataHa(mati); sumppkha = logic_A.getSumDataHa();
			 * this.context.put("jumppkha", sumppkha);
			 * 
			 * 
			 * logic_B.setDataHta(id); listppkhta = logic_B.getDataHta();
			 * this.context.put("listhta2", listppkhta); int countListhta =
			 * listppkhta.size(); this.context.put("jumListhta2", countList);
			 * 
			 * logic_B.setDataHa(id); listppkha2 = logic_B.getHa2();
			 * this.context.put("listHa2", listppkha2); int countListha2 =
			 * listppkha2.size(); this.context.put("jumListHa2", countListha2);
			 * 
			 * logic_A.setOverallSum(mati); overallnilai =
			 * logic_A.getOverallSum(); this.context.put("overall",
			 * overallnilai);
			 * 
			 * logic_A.setOverallSumMati(mati); overallnilaimati =
			 * logic_A.getOverallSumMati(); this.context.put("overallmati",
			 * overallnilaimati);
			 * 
			 * logic_B.setDataHtath(id); listppkhtath = logic_B.getHtath();
			 * this.context.put("listHtaht2", listppkhtath); int countListhtath
			 * = listppkhtath.size(); this.context.put("jumListHtaht",
			 * countList);
			 * 
			 * this.context .put("selectNegeri",
			 * HTML.SelectNegeri("socNegeri")); this.context.put("readmode",
			 * "disabled"); this.context.put("show_kemaskini_button", "yes");
			 * 
			 * 
			 * 
			 * 
			 * 
			 * }
			 * 
			 * else if ("kemaskini_nilai_harta".equals(mode)) { String id =
			 * getParam("idPermohonan"); String id2 = getParam("idPemohon");
			 * String id1 = getParam("idSimati"); String mati =
			 * getParam("id_Permohonansimati");
			 * 
			 * this.context.put("EventStatus", 4); this.context.put("id", id);
			 * this.context.put("id1", id1);
			 * 
			 * logic_A.updateDataNilai(id, mati, (String)
			 * session.getAttribute("_ekptg_user_id"));
			 * 
			 * logic_A.setSumDataHta(mati); sumppkhta = logic_A.getSumDataHta();
			 * this.context.put("jumppkhta", sumppkhta);
			 * 
			 * logic_A.setOverallSumHta(mati); sumoverallppkhta =
			 * logic_A.getOverallSumHta(); this.context.put("jumoverallppkhta",
			 * sumoverallppkhta);
			 * 
			 * logic_A.setDataHa(mati); listppkha = logic_A.getDataHa();
			 * this.context.put("listHa", listppkha); int countList =
			 * listppkha.size(); this.context.put("jumList", countList);
			 * 
			 * logic_A.setSumDataHa(mati); sumppkha = logic_A.getSumDataHa();
			 * this.context.put("jumppkha", sumppkha);
			 * 
			 * // FrmPrmhnnSek8SenaraiHTATHInternalData //
			 * frmPrmhnnSek8SenaraiHTATHInternalData = new //
			 * FrmPrmhnnSek8SenaraiHTATHInternalData(); logic_B.setDataHta(id);
			 * listppkhta = logic_B.getDataHta(); this.context.put("listhta2",
			 * listppkhta); int countListhta = listppkhta.size();
			 * this.context.put("jumListhta2", countList);
			 * 
			 * logic_B.setDataHa(id); listppkha2 = logic_B.getHa2();
			 * this.context.put("listHa2", listppkha2); int countListha2 =
			 * listppkha2.size(); this.context.put("jumListHa2", countListha2);
			 * 
			 * logic_A.setOverallSum(mati); overallnilai =
			 * logic_A.getOverallSum(); this.context.put("overall",
			 * overallnilai);
			 * 
			 * logic_A.setOverallSumMati(mati); overallnilaimati =
			 * logic_A.getOverallSumMati(); this.context.put("overallmati",
			 * overallnilaimati);
			 * 
			 * logic_B.setDataHtath(id); listppkhtath = logic_B.getHtath();
			 * this.context.put("listHtaht2", listppkhtath); int countListhtath
			 * = listppkhtath.size(); this.context.put("jumListHtaht",
			 * countList);
			 * 
			 * } else if ("hantar_harta".equals(mode)) { String id =
			 * getParam("idPermohonan"); String id2 = getParam("idPemohon");
			 * String id1 = getParam("idSimati"); String mati =
			 * getParam("id_Permohonansimati");
			 * 
			 * String idstatus = getParam("idstatus"); //
			 * if(bolehsimpan.equals("yes")){ updateStatus(session); // }
			 * 
			 * this.context.put("EventStatus", 1); this.context.put("id", id);
			 * this.context.put("id1", id1);
			 * 
			 * String[] idHta = this.request.getParameterValues("idHta");
			 * String[] idHa = this.request.getParameterValues("idHa"); String[]
			 * HtaNilaiTarikhMohon = this.request
			 * .getParameterValues("txtHtaNilaiTarikhMohon"); String[]
			 * HtaNilaiTarikhMati = this.request
			 * .getParameterValues("txtHtaNilaiTarikhMati"); String[]
			 * HaNilaiTarikhMohon = this.request
			 * .getParameterValues("txtHaNilaiTarikhMohon"); String[]
			 * HaNilaiTarikhMati = this.request
			 * .getParameterValues("txtHaNilaiTarikhMati");
			 * 
			 * if (idHta != null) { for (int i = 0; i < idHta.length; i++) { for
			 * (int i2 = 0; i2 < HtaNilaiTarikhMohon.length; i2++) { for (int i3
			 * = 0; i3 < HtaNilaiTarikhMati.length; i3++) {
			 * logic.updateNilaiHartaBaruHta(idHta[i], HtaNilaiTarikhMohon[i],
			 * HtaNilaiTarikhMati[i], String.valueOf(mati), String.valueOf(id));
			 * } } } } if (idHa != null) { for (int i = 0; i < idHa.length; i++)
			 * { for (int i2 = 0; i2 < HaNilaiTarikhMohon.length; i2++) { for
			 * (int i3 = 0; i3 < HaNilaiTarikhMati.length; i3++) {
			 * logic.updateNilaiHartaBaruHa(idHa[i], HaNilaiTarikhMohon[i],
			 * HaNilaiTarikhMati[i], String.valueOf(mati), String.valueOf(id));
			 * } } } } logic_A.updateDataNilai(id, mati, (String)
			 * session.getAttribute("_ekptg_user_id"));
			 * 
			 * logic_A.setSumDataHta(mati); sumppkhta = logic_A.getSumDataHta();
			 * this.context.put("jumppkhta", sumppkhta);
			 * 
			 * logic_A.setOverallSumHta(mati); sumoverallppkhta =
			 * logic_A.getOverallSumHta(); this.context.put("jumoverallppkhta",
			 * sumoverallppkhta);
			 * 
			 * logic_A.setDataHa(mati); listppkha = logic_A.getDataHa();
			 * this.context.put("listHa", listppkha); int countList =
			 * listppkha.size(); this.context.put("jumList", countList);
			 * 
			 * logic_A.setSumDataHa(mati); sumppkha = logic_A.getSumDataHa();
			 * this.context.put("jumppkha", sumppkha);
			 * 
			 * // FrmPrmhnnSek8SenaraiHTATHInternalData //
			 * frmPrmhnnSek8SenaraiHTATHInternalData = new //
			 * FrmPrmhnnSek8SenaraiHTATHInternalData();
			 * 
			 * logic_B.setDataHta(id); listppkhta = logic_B.getDataHta();
			 * this.context.put("listhta2", listppkhta); int countListhta =
			 * listppkhta.size(); this.context.put("jumListhta2", countList);
			 * 
			 * logic_B.setDataHa(id); listppkha2 = logic_B.getHa2();
			 * this.context.put("listHa2", listppkha2); int countListha2 =
			 * listppkha2.size(); this.context.put("jumListHa2", countListha2);
			 * 
			 * logic_A.setOverallSum(mati); overallnilai =
			 * logic_A.getOverallSum(); this.context.put("overall",
			 * overallnilai);
			 * 
			 * logic_A.setOverallSumMati(mati); overallnilaimati =
			 * logic_A.getOverallSumMati(); this.context.put("overallmati",
			 * overallnilaimati);
			 * 
			 * logic_B.setDataHtath(id); listppkhtath = logic_B.getHtath();
			 * this.context.put("listHtaht2", listppkhtath); int countListhtath
			 * = listppkhtath.size(); this.context.put("jumListHtaht",
			 * countList);
			 * 
			 * }
			 * 
			 * else if ("simpan_nilai_harta".equals(mode)) { String id =
			 * getParam("idPermohonan"); String id2 = getParam("idPemohon");
			 * String id1 = getParam("idSimati"); String mati =
			 * getParam("id_Permohonansimati");
			 * 
			 * this.context.put("EventStatus", 1); this.context.put("id", id);
			 * this.context.put("id1", id1);
			 * 
			 * String[] idHta = this.request.getParameterValues("idHta");
			 * String[] idHa = this.request.getParameterValues("idHa"); String[]
			 * HtaNilaiTarikhMohon = this.request
			 * .getParameterValues("txtHtaNilaiTarikhMohon"); String[]
			 * HtaNilaiTarikhMati = this.request
			 * .getParameterValues("txtHtaNilaiTarikhMati"); String[]
			 * HaNilaiTarikhMohon = this.request
			 * .getParameterValues("txtHaNilaiTarikhMohon"); String[]
			 * HaNilaiTarikhMati = this.request
			 * .getParameterValues("txtHaNilaiTarikhMati");
			 * 
			 * if (idHta != null) { for (int i = 0; i < idHta.length; i++) { for
			 * (int i2 = 0; i2 < HtaNilaiTarikhMohon.length; i2++) { for (int i3
			 * = 0; i3 < HtaNilaiTarikhMati.length; i3++) {
			 * logic.updateNilaiHartaBaruHta(idHta[i], HtaNilaiTarikhMohon[i],
			 * HtaNilaiTarikhMati[i], String.valueOf(mati), String.valueOf(id));
			 * } } } } if (idHa != null) { for (int i = 0; i < idHa.length; i++)
			 * { for (int i2 = 0; i2 < HaNilaiTarikhMohon.length; i2++) { for
			 * (int i3 = 0; i3 < HaNilaiTarikhMati.length; i3++) {
			 * logic.updateNilaiHartaBaruHa(idHa[i], HaNilaiTarikhMohon[i],
			 * HaNilaiTarikhMati[i], String.valueOf(mati), String.valueOf(id));
			 * } } } } logic_A.updateDataNilai(id, mati, (String)
			 * session.getAttribute("_ekptg_user_id"));
			 * 
			 * logic_A.setSumDataHta(mati); sumppkhta = logic_A.getSumDataHta();
			 * this.context.put("jumppkhta", sumppkhta);
			 * 
			 * logic_A.setOverallSumHta(mati); sumoverallppkhta =
			 * logic_A.getOverallSumHta(); this.context.put("jumoverallppkhta",
			 * sumoverallppkhta);
			 * 
			 * logic_A.setDataHa(mati); listppkha = logic_A.getDataHa();
			 * this.context.put("listHa", listppkha); int countList =
			 * listppkha.size(); this.context.put("jumList", countList);
			 * 
			 * logic_A.setSumDataHa(mati); sumppkha = logic_A.getSumDataHa();
			 * this.context.put("jumppkha", sumppkha);
			 * 
			 * // FrmPrmhnnSek8SenaraiHTATHInternalData //
			 * frmPrmhnnSek8SenaraiHTATHInternalData = new //
			 * FrmPrmhnnSek8SenaraiHTATHInternalData();
			 * 
			 * logic_B.setDataHta(id); listppkhta = logic_B.getDataHta();
			 * this.context.put("listhta2", listppkhta); int countListhta =
			 * listppkhta.size(); this.context.put("jumListhta2", countList);
			 * 
			 * logic_B.setDataHa(id); listppkha2 = logic_B.getHa2();
			 * this.context.put("listHa2", listppkha2); int countListha2 =
			 * listppkha2.size(); this.context.put("jumListHa2", countListha2);
			 * 
			 * logic_A.setOverallSum(mati); overallnilai =
			 * logic_A.getOverallSum(); this.context.put("overall",
			 * overallnilai);
			 * 
			 * logic_A.setOverallSumMati(mati); overallnilaimati =
			 * logic_A.getOverallSumMati(); this.context.put("overallmati",
			 * overallnilaimati);
			 * 
			 * logic_B.setDataHtath(id); listppkhtath = logic_B.getHtath();
			 * this.context.put("listHtaht2", listppkhtath); int countListhtath
			 * = listppkhtath.size(); this.context.put("jumListHtaht",
			 * countList);
			 * 
			 * }
			 */

			list = logic_A.setData_online(getParam("idPermohonan"),
					(String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, getParam("idPermohonan"), "Y", "", "T");
			this.context.put("View", list);
			context.put("DATEUTIL", new DateUtil());
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			this.context.put("selectedTabatas", 3);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);

			// String id = getParam("idPermohonan");
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati, (String) session
					.getAttribute("_ekptg_user_id"));

			this.context.put("masuk_skrin_pengesahan", "yes");
			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);

			vm = "app/ppk/frmPrmhnnSek8NilaianHarta.jsp";

			// TODO START HTAAM
		} else if ("Htaam".equals(submit)) {
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
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");

			}

			else if ("add_new".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
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

				String id = getParam("idPermohonan");
				String mati = getParam("id_Permohonansimati");
				if (bolehsimpan.equals("yes")) {
					addHtaam(session);
					this.context.put("appear_skrin_info", "simpan");
				}
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");

				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));
			}

			else if ("negerichange".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
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

				// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));

				if (getParam("socNegeriHtaam") != ""
						&& getParam("socNegeriHtaam") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaam")));
					this.context.put("listBandarSuratbyNegeri", s3);
					System.out.println("listBandarSuratbyNegeri30");
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
					System.out.println("listBandarSuratbyNegeri30a");
				}
			}

			else if ("daerahchange".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaam"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaam"));
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
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

				// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
				this.context.put("sekatan", getParam("txtSekatan"));
				this.context.put("syaratNyata", getParam("txtSyaratNyata"));

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
					this.context.put("noHakmilik", (String) getLot
							.get("NO_HAKMILIK"));
					this.context.put("idSimati", (String) getLot
							.get("ID_SIMATI"));
					this.context.put("nilai_Hta_memohon", (String) getLot
							.get("NILAI_HTA_TARIKHMOHON"));
					this.context.put("nilai_Hta_mati", (String) getLot
							.get("NILAI_HTA_TARIKHMATI"));
					this.context.put("kategori", (String) getLot
							.get("ID_KATEGORI"));
					this.context.put("jenishakmilik", (String) getLot
							.get("ID_JENISHM"));
					this.context.put("pemilikan", (String) getLot
							.get("STATUS_PEMILIKAN"));
					this.context
					.put("luashmp", (String) getLot.get("LUAS_HMP"));
					this.context.put("luasasal", (String) getLot.get("LUAS"));
					this.context.put("nopajakan", (String) getLot
							.get("NO_PAJAKAN"));
					this.context.put("jenistanah", (String) getLot
							.get("JENIS_TNH"));
					this.context.put("basimati", (String) getLot
							.get("BA_SIMATI"));
					this.context.put("bbsimati", (String) getLot
							.get("BB_SIMATI"));
					this.context.put("tanggungan", (String) getLot
							.get("TANGGUNGAN"));
					this.context.put("jenisluas", (String) getLot
							.get("ID_LUAS"));
					this.context.put("catatan", (String) getLot.get("CATATAN"));
					this.context.put("noperserahan", (String) getLot
							.get("NO_PERSERAHAN"));
					this.context.put("FLAG_DAFTAR", (String) getLot
							.get("FLAG_DAFTAR"));
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

				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("show_simpan_add_htaam", "yes");
				this.context.put("show_batal_add_htaam", "yes");
				this.context.put("show_button", "yes");
				this.context.put("show_kembali_htaam", "yes");
				this.context.put("show_htaa_add_table", "yes");

			} else if ("negerichangeup".equals(mode)) {
				v = new Vector();
				String mati = getParam("id_Permohonansimati");
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
				this.context.put("negeriup", idnegeri);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				if (!getParam("socNegeriHtaamUp").equals("")
						&& !getParam("socNegeriHtaamUp").equals("0")) {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(getParam("socNegeriHtaamUp")));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}

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
				h.put("daerah", "");
				h.put("mukim", "");
				// this.context.put("mukim", getParam("socMukimHtaam"));
				h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
				h.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
				// this.context.put("nocagaran", "");
				h.put("nopajakan", getParam("txtNoPajakanUp"));
				h.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
				h.put("basimati", getParam("txtBahagianSimati1Up"));
				h.put("bbsimati", getParam("txtBahagianSimati2Up"));
				// this.context.put("jenishta","");
				h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
				h.put("jenisluas", getParam("socJenisLuasHtaamUpd"));
				h.put("catatan", getParam("txtCatatanHt"));
				h.put("noperserahan", getParam("txtNoPersHtaamUp"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
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
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamUp"));
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);

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
				// this.context.put("mukim", getParam("socMukimHtaam"));
				h.put("luashmp", getParam("txtLuasHMpHtaamUpd"));
				h.put("luasasal", getParam("txtLuasAsalHtaamUpd"));
				// this.context.put("nocagaran", "");
				h.put("nopajakan", getParam("txtNoPajakanUp"));
				h.put("jenistanah", getParam("socJenisTanahHtaamUpd"));
				h.put("basimati", getParam("txtBahagianSimati1Up"));
				h.put("bbsimati", getParam("txtBahagianSimati2Up"));
				// this.context.put("jenishta","");
				h.put("tanggungan", getParam("txtTanggunganHtaamUp"));
				h.put("jenisluas", getParam("socJenisLuasHtaamUpd"));
				h.put("catatan", getParam("txtCatatanHt"));
				h.put("noperserahan", getParam("txtNoPersHtaamUp"));
				h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

				// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
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

			}

			else if ("getHtaam".equals(mode)) {

				String idhtaam = getParam("idhtaam");
				String mati = getParam("id_Permohonansimati");

				logic.setDataHTAbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAid = logic.getDataHTAbyIdHtaam();
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


				myLogger.info("ID_NEGERI : "+nn);
				if (!nn.equals("")
						&& !nn.equals("0")) {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(nn));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}


				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();

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

			}

			else if ("getHtaamStatus".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				this.context.put("tambahharta", "yes");
				this.context.put("kembaliharta", "yes");

				String id = getParam("idPermohonan");
				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");

				if (bolehsimpan.equals("yes")) {
					logic_A.htaamstatus(session, id, (String) session
							.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
				}

				/*
				 * 
				 * int idhtaam=Integer.parseInt(getParam("idhtaamid")); String
				 * mati = getParam("id_Permohonansimati"); String id =
				 * getParam("idPermohonan"); int id_sub =
				 * Integer.parseInt(getParam("id_Suburusanstatusfail")); int
				 * id_Fail = Integer.parseInt(getParam("id_Fail"));
				 * if(bolehsimpan.equals("yes")){
				 * logic_A.htaamstatus(id,(String)
				 * session.getAttribute("_ekptg_user_id"),id_sub,id_Fail); }
				 * logic.setDataHTAbyIdHtaam(idhtaam); listHTAid =
				 * logic.getDataHTAbyIdHtaam(); logic.setDataHTA(mati); listHTA
				 * = logic.getDataHTA(); this.context.put("listHTA",listHTA);
				 * this.context.put("idhtaam", idhtaam);
				 * this.context.put("listHTAid", listHTAid);
				 * 
				 * this.context.put("show_kemaskini_htaam","yes");
				 * this.context.put("show_hapus_htaam","yes");
				 * this.context.put("show_kembali_htaam","yes");
				 * this.context.put("show_button","yes");
				 * this.context.put("show_htaa_update_table","yes");
				 * this.context.put("tambahharta","yes");
				 * this.context.put("readmodenegeri", "disabled");
				 * this.context.put("readmodedaerah", "disabled");
				 * this.context.put("readmodemukim", "disabled");
				 * this.context.put("readmode", "disabled");
				 */

			}
			/*
			 * else if ("batalHtaam".equals(mode)) { String id =
			 * getParam("idPermohonan"); String id2 = getParam("idPemohon");
			 * String mati = getParam("id_Permohonansimati"); int
			 * idhtaam=Integer.parseInt(getParam("idhtaamid"));
			 * logic.setDataHTAbyIdHtaam(idhtaam); listHTAid =
			 * logic.getDataHTAbyIdHtaam(); this.context.put("idhtaam",
			 * idhtaam); this.context.put("listHTAid", listHTAid);
			 * logic.setDataHTA(mati); listHTA = logic.getDataHTA();
			 * this.context.put("listHTA",listHTA);
			 * 
			 * this.context.put("tambahharta","yes");
			 * this.context.put("readmodedaerah", "disabled");
			 * this.context.put("readmodemukim", "disabled");
			 * this.context.put("show_save_update_htaam","yes");
			 * this.context.put("show_batal_update_htaam","yes");
			 * this.context.put("show_hapus_htaam","yes");
			 * this.context.put("show_kembali_htaam","yes");
			 * this.context.put("show_htaa_update_table","yes");
			 * this.context.put("show_button","yes");
			 * 
			 * }
			 */
			else if ("hapusHtaam".equals(mode)) {
				String idhtaam = getParam("idhtaamid");
				String mati = getParam("id_Permohonansimati");
				logic.deleteHtaam(session, idhtaam, getParam("id_Permohonansimati"));
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
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
				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();
				this.context.put("listHTA", listHTA);
				logic.setDataHTAbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAid = logic.getDataHTAbyIdHtaam();
				Hashtable b = (Hashtable) listHTAid.get(0);
				String nn = b.get("negeri").toString();
				String dd = b.get("daerah").toString();

				// System.out.println("negeri :"+nn+"daerah :"+dd);

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
			} 

			else if ("simpanHtaam".equals(mode)) {

				String idhtaam = getParam("idhtaamid");
				String mati = getParam("id_Permohonansimati");
				if (bolehsimpan.equals("yes")) {
					updateHtaam(session);
					uploadFilesA(idhtaam,session);
					this.context.put("appear_skrin_info", "kemaskini");
				}

				logic.setDataHTAbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAid = logic.getDataHTAbyIdHtaam();
				this.context.put("idhtaam", idhtaam);
				this.context.put("listHTAid", listHTAid);


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


				myLogger.info("ID_NEGERI : "+nn);
				if (!nn.equals("")
						&& !nn.equals("0")) {
					Vector s4 = logic_A.getListBandarByNegeri(Integer
							.parseInt(nn));
					this.context.put("listBandarSuratbyNegeri", s4);
				} else {
					this.context.put("listBandarSuratbyNegeri", "");
				}



				logic.setDataHTA(mati);
				listHTA = logic.getDataHTA();

				Hashtable hta = (Hashtable) listHTAid.get(0);
				String idPermohonan2 = hta.get("negeri").toString();



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

				String id = getParam("idpermohonan");
				//System.out.println("id >>> "+id+" dan >>> "+getParam("idpermohonan"));
				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));



			}

			this.context.put("selectedTabatas", 1);
			this.context.put("selectedTabtengah", 0);
			this.context.put("selectedTabbawah", 0);
			this.context.put("selectedTabtepi", 0);
			context.put("DATEUTIL", new DateUtil());
			String id = getParam("idPermohonan");
			// logic_A.setData_online(id);
			//if(!id.equals("$ID")){
			//	id = getParam("idPermohonan");
			//}
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);
			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);

			// String id = getParam("idPermohonan");
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati, (String) session
					.getAttribute("_ekptg_user_id"));
			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);
			vm = "app/ppk/frmPrmhnnSek8HTAAH.jsp";
		} // TODO END HTAAM

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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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

				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
					this.context.put("appear_skrin_info", "simpan");
				}
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

				String id = getParam("idPermohonan");
				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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

				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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

				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
				this.context.put("listHTAX", listHTAX);
				this.context.put("tambahbutton", "yes");
				this.context.put("kembalibutton", "yes");

				String id_sub = getParam("id_Suburusanstatusfail");
				String id_Fail = getParam("id_Fail");
				if (bolehsimpan.equals("yes")) {
					logic_A.htaamstatus(session, id, (String) session
							.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
				}

				/*
				 * String mati = getParam("id_Permohonansimati"); int
				 * idhtaam=Integer.parseInt(getParam("idhtaamXid"));
				 * logic.setDataHTAXbyIdHtaam(idhtaam); listHTAXid =
				 * logic.getDataHTAXbyIdHtaam(); logic.setDataHTAX(mati);
				 * listHTAX = logic.getDataHTAX();
				 * this.context.put("listHTAX",listHTAX); listmukim =
				 * logic_A.getListMukim(); this.context.put("listMukimbyDaerah",
				 * listmukim); String id = getParam("idPermohonan"); int id_sub
				 * = Integer.parseInt(getParam("id_Suburusanstatusfail")); int
				 * id_Fail = Integer.parseInt(getParam("id_Fail"));
				 * if(bolehsimpan.equals("yes")){
				 * logic_A.htaamstatus(id,(String)
				 * session.getAttribute("_ekptg_user_id"),id_sub,id_Fail); }
				 * this.context.put("tambahbutton","yes");
				 * this.context.put("show_button","yes");
				 * this.context.put("idhtaamX", idhtaam);
				 * this.context.put("idhtaam", idhtaam);
				 * this.context.put("listHTAXid", listHTAXid);
				 * this.context.put("readmodenegeri", "disabled");
				 * this.context.put("readmodedaerah", "disabled");
				 * this.context.put("readmodemukim", "disabled");
				 * this.context.put("readmode", "disabled");
				 * this.context.put("show_kemaskini_htaam","yes");
				 * this.context.put("show_hapus_htaam","yes");
				 * this.context.put("show_kembali_htaam","yes");
				 * this.context.put("show_htaa_update_table","yes");
				 */
			}

			else if ("getHtaamX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");
				logic.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic.getDataHTAXbyIdHtaam();
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
				this.context.put("listHTAX", listHTAX);

				Hashtable b = (Hashtable) listHTAXid.get(0);
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
					logic_A.htaamstatus(session, id, (String) session
							.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
				}

				logic.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic.getDataHTAXbyIdHtaam();
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
			/*
			 * else if ("getHtaamX".equals(mode)) {
			 * 
			 * String mati = getParam("id_Permohonansimati"); int
			 * idhtaam=Integer.parseInt(getParam("idhtaamXid"));
			 * logic.setDataHTAXbyIdHtaam(idhtaam); listHTAXid =
			 * logic.getDataHTAXbyIdHtaam();
			 * 
			 * Hashtable m = (Hashtable)listHTAXid.get(0);
			 * if(m.get("negeripemaju").toString()!="" &&
			 * m.get("negeripemaju").toString()!="0"){ Vector s3 =
			 * logic_A.getListBandarByNegeri
			 * (Integer.parseInt(m.get("negeripemaju").toString()));
			 * this.context.put("listBandarTetapbyNegeri",s3);}
			 * else{this.context.put("listBandarTetapbyNegeri","");}
			 * 
			 * Hashtable k = (Hashtable)listHTAXid.get(0);
			 * if(k.get("negeri").toString()!="" &&
			 * k.get("negeri").toString()!="0"){ Vector s3 =
			 * logic_A.getListBandarByNegeri
			 * (Integer.parseInt(k.get("negeri").toString()));
			 * this.context.put("listBandarSuratbyNegeri",s3);}
			 * else{this.context.put("listBandarSuratbyNegeri","");}
			 * 
			 * 
			 * logic.setDataHTAX(mati); listHTAX = logic.getDataHTAX();
			 * this.context.put("listHTAX",listHTAX); listmukim =
			 * logic_A.getListMukim(); this.context.put("listMukimbyDaerah",
			 * listmukim);
			 * 
			 * this.context.put("tambahbutton","yes");
			 * this.context.put("show_button","yes");
			 * this.context.put("idhtaamX", idhtaam);
			 * this.context.put("idhtaam", idhtaam);
			 * this.context.put("listHTAXid", listHTAXid);
			 * this.context.put("readmodenegeri", "disabled");
			 * this.context.put("readmodedaerah", "disabled");
			 * this.context.put("readmodemukim", "disabled");
			 * this.context.put("readmode", "disabled");
			 * this.context.put("show_kemaskini_htaam","yes");
			 * this.context.put("show_hapus_htaam","yes");
			 * this.context.put("show_kembali_htaam","yes");
			 * this.context.put("show_htaa_update_table","yes");
			 * 
			 * 
			 * 
			 * }
			 */

			else if ("batalHtaamX".equals(mode)) {

				String mati = getParam("id_Permohonansimati");
				String idhtaam = getParam("idhtaamXid");
				logic.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic.getDataHTAXbyIdHtaam();
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.deleteHtaam(session, idhtaam, getParam("id_Permohonansimati"));
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic.getDataHTAXbyIdHtaam();
				this.context.put("listHTAXid", listHTAXid);
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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

				// System.out.println("negeri :"+nn+"daerah :"+dd);

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

				String idhtaam = getParam("idhtaamid");
				String mati = getParam("id_Permohonansimati");
				if (bolehsimpan.equals("yes")) {
					updateHtaamX(session);
					uploadFilesA(idhtaam,session);
					this.context.put("appear_skrin_info", "kemaskini");
				}
				// int idhtaam=Integer.parseInt(getParam("idhtaamXid"));
				// h.put("idhta",getParam("idhtaamid"));

				logic.setDataHTAXbyIdHtaam(idhtaam,
						getParam("id_Permohonansimati"));
				listHTAXid = logic.getDataHTAXbyIdHtaam();
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

				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
				logic_A.updateDataNilai(id, mati, (String) session
						.getAttribute("_ekptg_user_id"));

			} else if ("daerahchangeX".equals(mode)) {
				String mati = getParam("id_Permohonansimati");
				int iddaerah = Integer.parseInt(getParam("socDaerahHtaamX"));
				int idnegeri = Integer.parseInt(getParam("socNegeriHtaamX"));
				listmukim = logic_A.getListMukimbyDaerah(iddaerah);
				this.context.put("listMukimbyDaerah", listmukim);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				logic.setDataHTAX(mati);
				listHTAX = logic.getDataHTAX();
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
			// logic_A.setData_online(id);
			list = logic_A.setData_online(id, (String) session
					.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, id, "Y", "", "T");
			this.context.put("View", list);

			// String id = getParam("idPermohonan");
			String mati = getParam("id_Permohonansimati");
			logic_A.updateDataNilai(id, mati, (String) session
					.getAttribute("_ekptg_user_id"));

			logic_A.setDataFail(id);
			listFail = logic_A.getDataFail();
			this.context.put("ViewFail", listFail);
			context.put("DATEUTIL", new DateUtil());
			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);
			vm = "app/ppk/frmPrmhnnSek8HTATH.jsp";

		}
		// simati
		else if ("Simati".equals(submit)) {

			readability2 = "readonly";
			disability1 = "disabled";
			this.context.put("show_kemaskini_button", "");
			this.context.put("show_simpan_button", "");
			this.context.put("show_batal_button", "");
			this.context.put("readmode", "");
			String id = getParam("idPermohonan");

			logic.setDataSimati(id);
			listSimati = logic.getDataSimati();
			this.context.put("listSimati", listSimati);
			Hashtable h1 = (Hashtable) listSimati.get(0);
			if (h1.get("idnegeri").toString() != "" && h1.get("idnegeri").toString() != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h1.get("idnegeri").toString()));
				this.context.put("listBandarTetapbyNegeri", s3);
			} else {
				this.context.put("listBandarTetapbyNegeri", "");
			}
			myLogger.info("mode-------------------------------------"+mode);
			if ("Simatiview".equals(mode)) {
				/*
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon"); String id1 = getParam("idSimati");
				 * logic.setDataSimati(id); listSimati = logic.getDataSimati();
				 * this.context.put("listSimati", listSimati);
				 * this.context.put("selectNegeri"
				 * ,HTML.SelectNegeri("socNegeri"));
				 */
				this.context.put("readmode", disability1);
				this.context.put("show_kemaskini_button", "yes");

			} else if ("batal_simati".equals(mode)) {
				/*
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon"); String id1 = getParam("idSimati");
				 * logic.setDataSimati(id); listSimati = logic.getDataSimati();
				 * this.context.put("listSimati", listSimati);
				 * this.context.put("selectNegeri"
				 * ,HTML.SelectNegeri("socNegeri"));
				 */
				this.context.put("show_simpan_button", "yes");
			} else if ("kemaskini_simati".equals(mode)) {
				/*
				 * String id = getParam("idPermohonan"); String id2 =
				 * getParam("idPemohon"); String id1 = getParam("idSimati");
				 * 
				 * logic.setDataSimati(id); listSimati = logic.getDataSimati();
				 * this.context.put("listSimati", listSimati);
				 */
				this.context.put("show_simpan_button", "yes");
				this.context.put("show_batal_button", "yes");
			} else if ("simpan_simati".equals(mode)) {
				// String id = getParam("idPermohonan");
				disability1 = "disabled";
				myLogger.info("PEJE START-------------------------------------");
				myLogger.info(bolehsimpan);
				// updatesimati(session);
				if (bolehsimpan.equals("yes")) {
					updatesimati(session);
					this.context.put("appear_skrin_info", "kemaskini");

				}

				/*
				 * logic.setDataSimati(id); listSimati = logic.getDataSimati();
				 * this.context.put("listSimati", listSimati);
				 */
				this.context.put("readmode", disability1);
				this.context.put("show_kemaskini_button", "yes");
				// String id = getParam("idPermohonan");
				logic.setDataSimati(id);
				listSimati = logic.getDataSimati();
				this.context.put("listSimati", listSimati);
				Hashtable h2 = (Hashtable) listSimati.get(0);
				if (h2.get("idnegeri").toString() != "" && h2.get("idnegeri").toString() != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(h2.get("idnegeri").toString()));
					this.context.put("listBandarTetapbyNegeri", s3);
				} else {
					this.context.put("listBandarTetapbyNegeri", "");
				}


				// System.out.println("PEJE END-------------------------------------");
			} else if ("getBandar".equals(mode)) {
				Hashtable h = new Hashtable();
				v = new Vector();

				h.put("idPermohonan", getParam("idPermohonanp"));
				h.put("idSimati", getParam("idSimati"));
				// h.put("noKpBaru",getParam(""));
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
				// h.put("jeniswaktumati",getParam(""));
				h.put("sebabMati", getParam("txtSebabKematianSimati"));
				h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
				h.put("alamat1", getParam("txtAlamatTerakhir1Simati"));
				h.put("alamat2", getParam("txtAlamatTerakhir2Simati"));
				h.put("alamat3", getParam("txtAlamatTerakhir3Simati"));
				h.put("poskod", getParam("txtPoskodSimati"));
				h.put("bandar", getParam(""));
				h.put("bandartetap", "");
				h.put("idnegeri", getParam("socNegeriSimati"));
				h.put("catatan", getParam("txtCatatanSimati"));
				h.put("jeniswaktu", getParam("jeniswaktu"));

				h.put("tarikhLahirSimati", getParam("tarikhLahirSimati"));
				v.addElement(h);
				this.context.put("listSimati", v);

				if (getParam("socNegeriSimati") != "" && getParam("socNegeriSimati") != "0") {
					Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(getParam("socNegeriSimati")));
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

			list = logic_A.setData_online(idm, (String) session.getAttribute("_ekptg_user_id"));
			headerppk_baru(session, idm, "Y", "", "T");
			this.context.put("View", list);

			hideTabPengesahan_simati = checkEmptyField_simati(getParam("idPermohonan"));
			context.put("hideTabPengesahan_simati", hideTabPengesahan_simati);
			hideTabPengesahan_pemohon = checkEmptyField_pemohon(getParam("idPermohonan"));
			context.put("hideTabPengesahan_pemohon", hideTabPengesahan_pemohon);
			hideTabPengesahan_hta = checkEmptyField_hta(getParam("idPermohonan"));
			context.put("hideTabPengesahan_hta", hideTabPengesahan_hta);
			vm = "app/ppk/frmPrmhnnSek8Simati.jsp";
		} else if ("simpanUploadHTAAH".equals(submit)) {
			System.out.println("peje");

			String idhtaam = getParam("idhtaam");//IL

			updateHtaam(session);	
			uploadFilesA(idhtaam,session);

			vm = "app/ppk/frmPrmhnnSek8HTAAHLampiran.jsp";
		} else {
			// System.out.println("cal :"+cal+"currentcal :"+currentcal);
			if (cal.before(currentcal)) {
				this.context.put("lepassatusept", "no");
			} else if (cal.after(currentcal)) {
				this.context.put("lepassatusept", "yes");
			} else {
				this.context.put("lepassatusept", "yes");
			}

			vm = "app/ppk/frmPrmhnnSek8SenaraiSemakDaftar_online.jsp";

		}

		System.out.println("vm :::::: "+vm);

		String usid = "";
		// usid="81"; //kelantan
		// session.setAttribute("_ekptg_user_id", usid);
		// this.context.put("usid",usid);

		usid = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", usid);

		this.context.put("DATEUTIL", new DateUtil());


		Db db = null;
		try {
			db = new Db();

			listnegeri = logic_A.getListnegeriDb(db);
			this.context.put("listnegeri", listnegeri);

			Vector count_dunia = logic_A.getNofailduniaDb(2, 1, 8,db);
			this.context.put("count_dunia", count_dunia);

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

			this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
			this.context.put("flagForView", flagForView);
			this.context.put("flagFromSenaraiPermohonanSek8",
					flagFromSenaraiPermohonanSek8);

			Vector kenegaraan = null;
			kenegaraan = mainheader.kenegaraanDb(db);
			this.context.put("kenegaraan", kenegaraan);

			FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahARBDb(db);
			Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
					.getMaklumatMahkamahARB();
			this.context.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);

			view1 = logic_A.getJenisKpDb(db);
			this.context.put("listkp", view1);
			this.context.put("listkp2", view1);


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

	private void clearContext() {
		readability1 = "";
		this.context.put("CheckWujudLot", "");
		this.context.put("daftar", "");
		this.context.put("nofail", "");
		this.context.put("masuk_skrin_pengesahan", "");
		this.context.put("hideTabPengesahan_pemohon", "");
		this.context.put("hideTabPengesahan_simati", "");
		this.context.put("hideTabPengesahan_hta", "");
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
		this.context.put("lepassatusept", "");

		this.context.put("View_pengesahan_pemohonan", "");
		this.context.put("ViewX", "");

		this.context.put("txtbox", "");
		this.context.put("tarikhresit", "");

		this.context.put("listPemohonOB", "");
	}

	// ////////////////////////////////////
	private void delete_semakan(HttpSession session, String idPermohonan)
			throws Exception {
		// FrmPrmhnnSek8SenaraiSemakInternalData frmonline = new
		// FrmPrmhnnSek8SenaraiSemakInternalData();
		logic_C.semakanDelete(idPermohonan);
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
		String bandar = getParam("txtBandar");
		String idbandar = getParam("socBandar");
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
		h.put("bandar", bandar);
		h.put("idbandar", idbandar);
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		String usid = (String) session.getAttribute("_ekptg_user_id");
		h.put("id_Masuk", usid);
		h.put("txtUmurSimati", txtUmurSimati);
		h.put("socJantinaSimati", socJantinaSimati);
		h.put("txtUmurPemohon", txtUmurPemohon);
		h.put("socJantinaPemohon", socJantinaPemohon);

		h.put("no_hp", getParam("no_hp"));
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("no_tel", no_tel);
		h.put("nama_pelbagainegara", nama_pelbagainegara);
		h.put("taraf_penting", taraf_penting);
		h.put("jenis_pemohon", jenis_pemohon);
		h.put("adaob", getParam("adaob"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		logic_A.updatePermohonan(h);
	}

	private void updatesimati(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();
		Vector v = new Vector();

		// h.put("id_Pemohonan", Integer.parseInt(getParam("idPermohonan")));
		h.put("id_Simati", getParam("idSimati"));
		h.put("nama_Simati", getParam("txtNamaSimati"));
		h.put("nama_Lain", getParam("txtNamaLainSimati"));
		String nkpbaru = getParam("txtNoKPBaru1Simati")+ getParam("txtNoKPBaru2Simati")+ getParam("txtNoKPBaru3Simati");
		h.put("no_Kp_Baru", nkpbaru);
		h.put("no_Kp_Lama", getParam("txtNoKPLamaSimati"));
		h.put("jenis_Kp", getParam("socJenisKPLainSimati"));
		h.put("n0_Kp_Lain", getParam("txtNoKPLainSimati"));

		// if (getParam("txtUmurSimati").equals("")) {
		// h.put("umur", 0);
		// } else {
		// h.put("umur", Integer.parseInt(getParam("txtUmurSimati")));
		// }

		// MODIFIED BY PEJE -- RECALCULATE UMUR SIMATI BASED ON TARIKH LAHIR
		// SIMATI.BUKAN PADA IC SIMATI -Y2K PROBLEM
		String tarikhLahirSimati = getParam("tarikhLahirSimati");
		h.put("tarikhLahirSimati", tarikhLahirSimati);
		Integer age = 0;
		if (tarikhLahirSimati != null && tarikhLahirSimati.length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar birthSimati = new GregorianCalendar();
			Date dateB = sdf.parse(tarikhLahirSimati);
			birthSimati.setTime(dateB);

			Calendar tarikhMati = new GregorianCalendar();
			Date dateM = sdf.parse(getParam("txdTarikhMatiSimati"));
			tarikhMati.setTime(dateM);
			age = tarikhMati.get(Calendar.YEAR)
					- birthSimati.get(Calendar.YEAR);
		}

		h.put("umur", String.valueOf(age));

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

		if(getParam("socBuktiKematianSimati").equals("2")){
			h.put("tarikhSuratAkuan", getParam("txdTarikhSuratAkuan"));
		}else{
			h.put("tarikhSuratAkuan", "");
		}



		logic.updatesimati_online(h);
	}

	private void updatepemohon(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		Hashtable k = new Hashtable();

		logic.checkpemohonwaris(getParam("idPemohon"));
		Vector cpw = logic.getcheckpemohonwaris();

		// System.out.println("CW SIZE :"+cpw.size());

		k.put("pemohonwaris", cpw.size());


		String nkpbarupemohon = getParam("txtnoKpBaru1Pemohon")
				+ getParam("txtnoKpBaru2Pemohon")
				+ getParam("txtnoKpBaru3Pemohon");

		k.put("adataraf", getParam("adataraf"));

		k.put("idPermohonan", getParam("idPermohonan"));
		k.put("idPemohon", getParam("idPemohon"));
		k.put("idSimati", getParam("idSimati"));
		// k.put("idSimati", Integer.parseInt(getParam("idSimati")));
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

		//if (getParam("txtUmurPemohon") != "") {
		//	k.put("umur", Integer.parseInt(getParam("txtUmurPemohon")));
		//}
		String umu = "0";
		if (!getParam("txtUmurPemohon").equals("")) {
			//k.put("umur", umu);
			umu = getParam("txtUmurPemohon");
		}
		k.put("umur", umu);

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

		k.put("status_Peguam", getParam("status_peguam"));
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

		logic.updatepemohon(k);

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

		logic.tambahpeguam(k);
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

		logic.updatepeguam(k);
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

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

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


		h.put("nama_pelbagainegara", getParam("nama_pelbagainegara"));
		h.put("nama_pelbagainegara_surat", getParam("nama_pelbagainegara_surat"));
		logic.addWaris(h);
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

		logic.addWarisBerikut(h);
	}

	private void addWarisHubungan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("idParent", Integer.parseInt(getParam("txtIdParent")));
		h.put("idOb", Integer.parseInt(getParam("txtIdParent")));
		h.put("saudara", Integer.parseInt(getParam("socSaudaraWaris")));

		logic.addWarisBerikut(h);
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
		h.put("emel", getParam("txtEmel"));
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
			h.put("bandarSurat", "0");
		} else {
			h.put("bandarSurat",getParam("txtBandarWarisSurat"));
		}

		if (getParam("socNegeriWarisSurat").equals("")) {
			h.put("negeriSurat", "0");
		} else {
			h.put("negeriSurat", getParam("socNegeriWarisSurat"));
		}

		// h.put("negeri", Integer.parseInt(getParam("socNegeriPenting")));
		h.put("catatan", getParam("txtCatatanWaris"));

		if (getParam("socSaudaraWaris").equals("")) {
			h.put("saudara", "0");
		} else {
			h.put("saudara", getParam("socSaudaraWaris"));
		}

		if (getParam("socStatusOBWaris").equals("")) {
			h.put("statusWaris", "0");
		} else {
			h
			.put("statusWaris", getParam("socStatusOBWaris"));
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

		logic.updateWaris(h);

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

		logic.addPenting(h);
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

		logic.updatePenting(h);

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

		logic.addPenghutang(h);

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

		logic.updatePenghutang(h);

	}

	public void addHtaam(HttpSession session) throws Exception {
		System.out.println("-------Read Here8----");
		Hashtable h = new Hashtable();

		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));

		// ADD BY AISHAH - TAMBAH FIELD ALAMAT
		h.put("alamat_hta1", getParam("txtAlamat1Htaam1"));
		h.put("alamat_hta2", getParam("txtAlamat2Htaam"));
		h.put("alamat_hta3", getParam("txtAlamat3Htaam"));
		h.put("poskod", getParam("txtAlamatPoskodHtaam"));

		h.put("id_bandarhta", getParam("txtBandarHartaHtaamX2"));


		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		// String mati = getParam("id_Permohonansimati");
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

		h.put("catatan", getParam("txtCatatanHtaam"));

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", getParam("txtSekatan") == null ? ""
				: getParam("txtSekatan"));
		h.put("syaratNyata", getParam("txtSyaratNyata") == null ? ""
				: getParam("txtSyaratNyata"));

		logic.addHtaam(h);
		System.out.println("-------Read Here8j ends----");
	}

	public void addHtaamX(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));

		// h.put("noHakmilik", getParam("txtNoHakmilikHtaam"));
		h.put("idSimati", getParam("idSimati"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		// String mati = getParam("id_Permohonansimati");
		h.put("nopt", getParam("txtNoPTHtaamX"));
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

		logic.addHtaamX(h);

	}

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

	private void view_mode(HttpSession session) throws Exception {
		String id = getParam("idtemp");
		logic_A.setData_online(id, (String) session
				.getAttribute("_ekptg_user_id"));
		headerppk_baru(session, id, "Y", "", "T");
	}

	private void view_mode_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setDataHa(id1);
	}

	private void view_sum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setDataHa(id1);
	}

	private void view_overallsum_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setOverallSum(id1);
	}

	private void view_overallsummati_ppkha(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setOverallSumMati(id1);
	}

	private void view_mode_ppkhta(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		// FrmPrmhnnSek8SenaraiHTATHInternalData
		// frmPrmhnnSek8SenaraiHTATHInternalData = new
		// FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_B.setDataHta(id1);
	}

	private void view_mode_ppkhtath(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		// FrmPrmhnnSek8SenaraiHTATHInternalData
		// frmPrmhnnSek8SenaraiHTATHInternalData = new
		// FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_B.setDataHtath(id1);
	}

	private void view_mode_ppkha2(HttpSession session, String id1)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		// FrmPrmhnnSek8SenaraiHTATHInternalData
		// frmPrmhnnSek8SenaraiHTATHInternalData = new
		// FrmPrmhnnSek8SenaraiHTATHInternalData();
		logic_B.setDataHa(id1);
	}

	private void delete_mode_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.deleteDataHa(id1, id3);
	}

	private void view_data_ppkha(HttpSession session, String id1, String id3)
			throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.setSelectedDataHa(id1, id3);
	}

	private void sum_nilai_ppkpermohonan(HttpSession session, String id,
			String id1) throws Exception {
		// int id2 = Integer.parseInt(getParam("id2"));
		logic_A.updateDataNilai(id, id1, (String) session
				.getAttribute("_ekptg_user_id"));
	}

	private void view_mode_pemohon(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.setData_online(id, (String) session
				.getAttribute("_ekptg_user_id"));
		headerppk_baru(session, id, "Y", "", "T");
	}

	private void check_mode_permohonan(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.checkData(id);
	}

	private void view_get_id(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		logic_A.setGetId(id);
	}

	private void view_get_userid(HttpSession session) throws Exception {
		String useridx = (String) session.getAttribute("_ekptg_user_id");
		logic_A.setGetUserId(useridx);
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

		logic.updateStatus(session, h);

	}

	private void addPermohonan(HttpSession session) throws Exception {
		Db db = null;
	    Connection conn = null;
		db = new Db();
        conn = db.getConnection();
		String txtbox = getParam("txtNomborResit1");
		String tarikhresit = getParam("txdTarikhByrn1");

		String id_Fail = getParam("id_Fail");

		String IdPermohonan = getParam("idPermohonan");
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
		String bandar = getParam("txtBandar");
		String idbandar = getParam("socBandar");
		String poskod = getParam("txtPoskod");
		String negeri = getParam("socNegeri");
		String negId = getParam("negid");

		String txtUmurSimati = getParam("txtUmurSimati");
		String socJantinaSimati = getParam("socJantinaSimati");
		String txtUmurPemohon = getParam("txtUmurPemohon");
		String socJantinaPemohon = getParam("socJantinaPemohon");

		String no_tel = getParam("no_tel");
		String nama_pelbagainegara = getParam("nama_pelbagainegara");
		String taraf_penting = getParam("taraf_penting");
		String jenis_pemohon = getParam("jenis_pemohon");

		String buktimati = getParam("buktimati");
		String sijilmati = getParam("sijilmati");

		String socSaudaraWaris = getParam("socSaudaraWaris");

		Hashtable h = null;
		h = new Hashtable();
		h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
		// h.put("userId","14");
		h.put("negId", negId);
		h.put("userIdPejabat", "");
		h.put("userIdKodDaerah", "");

		h.put("txtbox", txtbox);
		h.put("tarikhresit", tarikhresit);

		h.put("socSaudaraWaris", socSaudaraWaris);
		h.put("userIdKodNegeri", "");
		h.put("userIdNeg", "");
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
		h.put("idbandar", idbandar);
		h.put("poskod", poskod);
		h.put("negeri", negeri);

		h.put("buktimati", buktimati);
		h.put("sijilmati", sijilmati);

		h.put("txtUmurSimati", txtUmurSimati);
		h.put("socJantinaSimati", socJantinaSimati);

		h.put("txtUmurPemohon", txtUmurPemohon);
		h.put("socJantinaPemohon", socJantinaPemohon);

		h.put("no_tel", no_tel);
		h.put("nama_pelbagainegara", nama_pelbagainegara);
		h.put("no_hp", getParam("no_hp"));
		h.put("taraf_penting", taraf_penting);
		h.put("jenis_pemohon", jenis_pemohon);
		h.put("adaob", getParam("adaob"));
		h.put("jenis_pej", getParam("jenis_pej"));
		h.put("tahun", getParam("tahun"));
		myLogger.info("*****READ HERE1******"); 
		logic_A.addPermohonan_online(session, h);
		myLogger.info("*****READ HERE2******"); 
		String nokp = null;
		if (no_kpbaru_simati != null)
		{
			nokp = no_kpbaru_simati;
		}
		else if (no_kplama_simati != null)
		{
			nokp = no_kplama_simati;
		}
		else if (no_kplain_simati != null)
		{
			nokp = no_kplain_simati;
		}
		uploadFiles(db,conn,nokp);
	}
	
	private void uploadFiles(Db db,Connection conn, String nokp) throws Exception {
		myLogger.info("Baca uploadFiles:--------------"); 
		String no_kp = nokp;
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items = upload.parseRequest(this.request);
	    Iterator itr = items.iterator();	   
	    while (itr.hasNext()) {    	
	      FileItem item = (FileItem)itr.next();
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  System.out.println("item.getName = "+ item.getName());
	    	  saveData(item,db,conn,no_kp);
	      }
	    }
	  }
	
	private void saveData(FileItem item,Db db,Connection conn, String no_kp) throws Exception {
		//Db db = null;
	
    try {
    	db = new Db();

    	Connection con = db.getConnection();
    	con.setAutoCommit(false);
    	String nokp = no_kp;
    	//String id_permohonansimati = getParam("id_permohonansimati_atheader");
    	PreparedStatement ps = con.prepareStatement("UPDATE TBLPPKSIMATI SET nama_fail = ?, content = ?, jenis_Mime = ? WHERE (NO_KP_BARU = ? OR NO_KP_BARU = ? OR NO_KP_LAIN = ?)");		
    	//System.out.println("+nama_pemohon_lama3+ " + nama_pemohon_lama3);
    	//System.out.println(con.prepareStatement("UPDATE TBLPPKTUKARPEMOHON SET bukti = ?, content = ?, jenis_Mime = ? WHERE ID_PERMOHONANSIMATI = ?"));
    	ps.setString(1,item.getName());
    	ps.setBinaryStream(2,item.getInputStream(),(int)item.getSize());
    	ps.setString(3,item.getContentType());
    	//System.out.println("item.getInputStream = "+ item.getInputStream());
    	//System.out.println("item.getSize = "+ item.getSize());
    	//System.out.println("item.getContentType = "+ item.getContentType());
    	ps.setString(4,nokp);
    	ps.setString(5,nokp);
    	ps.setString(6,nokp);
    	//ps.setString(4,getParam("id_permohonansimati_atheader"));
    	myLogger.info("Baca SaveData:---------------"); 
    	ps.executeUpdate();	
    	myLogger.info("Baca SaveData 2:---------------"); 
        con.commit();
    } finally {
	      if (db != null) db.close();
    }
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
		this.context.put("nama_pelbagainegara", "");
		this.context.put("no_tel_samb", "");
		context.put("disabledDropdown", "");

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

		logic.updatesimatisemak(h);
	}

	public String checkEmptyField_simati(String idpermohonan) throws Exception {
		Vector listEmptyField = null;
		logiconline.checkFieldEmpty_simati(idpermohonan);
		listEmptyField = logiconline.getListEmptyField_simati();
		String hideTabPengesahan = "";
		Hashtable hEmpty = null;
		int i = 0;
		if (listEmptyField.size() == 0) {
			hideTabPengesahan = "hide";
		} else if (listEmptyField.size() == 1) {
			hEmpty = (Hashtable) listEmptyField.get(0);
			/*
			 * if("".equals(hEmpty.get("ID_BUKTIMATI"))||
			 * "".equals(hEmpty.get("TARIKH_MATI")) ||
			 * "".equals(hEmpty.get("ALAMAT_1")) ||
			 * "".equals(hEmpty.get("ALAMAT1_SURAT")) ||
			 * "".equals(hEmpty.get("POSKOD")) ||
			 * "".equals(hEmpty.get("POSKOD_SURAT")) ||
			 * "0".equals(hEmpty.get("ID_NEGERI")) ||
			 * "".equals(hEmpty.get("ID_NEGERISURAT")) ||
			 * hEmpty.get("ID_HTA").equals("0")){
			 * 
			 * hideTabPengesahan = "hide"; } else{ hideTabPengesahan = "show"; }
			 */
			if ("".equals(hEmpty.get("ID_BUKTIMATI"))
					|| "".equals(hEmpty.get("TARIKH_MATI"))) {

				hideTabPengesahan = "hide";
			} else {
				hideTabPengesahan = "show";
			}
		} else {
			hideTabPengesahan = "show";
		}

		return hideTabPengesahan;
	}

	public String checkEmptyField_pemohon(String idpermohonan) throws Exception {
		Vector listEmptyField = null;
		logiconline.checkFieldEmpty_pemohon(idpermohonan);
		listEmptyField = logiconline.getListEmptyField_pemohon();
		String hideTabPengesahan = "";
		Hashtable hEmpty = null;
		int i = 0;
		if (listEmptyField.size() == 0) {
			hideTabPengesahan = "hide";
		} else if (listEmptyField.size() == 1) {
			hEmpty = (Hashtable) listEmptyField.get(0);

			if ("".equals(hEmpty.get("ALAMAT_1"))
					|| "".equals(hEmpty.get("ALAMAT1_SURAT"))
					|| "".equals(hEmpty.get("POSKOD"))
					|| "".equals(hEmpty.get("POSKOD_SURAT"))
					|| "0".equals(hEmpty.get("ID_NEGERI"))
					|| "".equals(hEmpty.get("ID_NEGERISURAT"))) {

				hideTabPengesahan = "hide";
			} else {
				hideTabPengesahan = "show";
			}
		} else {
			hideTabPengesahan = "show";
		}

		return hideTabPengesahan;
	}

	public String checkEmptyField_hta(String idpermohonan) throws Exception {
		Vector listEmptyField = null;
		logiconline.checkFieldEmpty_hta(idpermohonan);
		listEmptyField = logiconline.getListEmptyField_hta();
		String hideTabPengesahan = "";
		Hashtable hEmpty = null;
		int i = 0;
		if (listEmptyField.size() == 0) {
			hideTabPengesahan = "hide";
		} else if (listEmptyField.size() == 1) {
			hEmpty = (Hashtable) listEmptyField.get(0);

			if ("".equals(hEmpty.get("ID_HTA"))) {

				hideTabPengesahan = "hide";
			} else {
				hideTabPengesahan = "show";
			}
		} else {
			hideTabPengesahan = "show";
		}

		return hideTabPengesahan;
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
		logic.updateHtaamX(h);

	}

	private void uploadFilesA(String idhtaam , HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					if (item.getSize() < 500000000L)
					{
						hapusDokumenPPK(idhtaam);
						saveDataA(idhtaam , item, session);
					}
					else
					{
						this.context.put("limitExceed", true);
					}
				}
			}
		}
	}

	public void hapusDokumenPPK(String idHta) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " DELETE FROM TBLPPKUPLOADPELAN P WHERE P.ID_HTA = '"+idHta+"'";
			System.out.println("sql1 >>> "+sql);
			stmt.executeUpdate(sql);

			sql = " DELETE FROM TBLPPKDOKUMEN D WHERE D.ID_DOKUMEN IN (SELECT P.ID_DOKUMEN FROM TBLPPKUPLOADPELAN P WHERE P.ID_HTA = '"+idHta+"') ";
			System.out.println("sql2 >>> "+sql);
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}


	private void saveDataA(String idhtaam ,FileItem item, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		//		String idHtaSession = (String) session.getAttribute("idHtaSession");

		try {
			db = new Db();

			// TBLPPKDOKUMEN
			long idDokumen = DB.getNextID("TBLPPKDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPPKDOKUMEN "
					+ "(ID_DOKUMEN,NAMA_DOKUMEN,ID_MASUK,TARIKH_MASUK,KANDUNGAN,FORMAT) " + "VALUES(?,?,?,SYSDATE,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("txtNamaPelanUp"));
			ps.setString(3, userId);
			ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize());
			ps.setString(5, item.getContentType());
			ps.executeUpdate();

			// TBLPPKUPLOADDOKUMEN
			long idPelan = DB.getNextID("TBLPPKUPLOADPELAN_SEQ");
			// long id_hta = DB.getNextID(db, "TBLPPKHTA_SEQ");
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO TBLPPKUPLOADPELAN"
					+ "(ID_PELAN,ID_DOKUMEN,ID_HTA,ID_MASUK,TARIKH_MASUK) " + "VALUES(?,?,?,?,SYSDATE)");
			ps1.setLong(1, idPelan);
			ps1.setLong(2, idDokumen);
			ps1.setString(3, idhtaam);
			ps1.setString(4, userId);
			ps1.executeUpdate();

			con.commit();

			session.setAttribute("idHtaSession", null);

		} finally {
			if (db != null)
				db.close();
		}
		this.context.put("completed", true);
	}

	private void updateHtaam(HttpSession session) throws Exception {

		Vector v = new Vector();
		Hashtable h = new Hashtable();
		h.put("FLAG_DAFTAR", getParam("FLAG_DAFTAR"));
		h.put("id_Permohonansimati", getParam("id_Permohonansimati"));
		h.put("idhta", getParam("id_htaam"));
		h.put("noHakmilik", getParam("txtNoHakmilikHtaamUp"));
		h.put("idSimati", getParam("idSimati"));
		h.put("nopt", getParam("txtNoPTHtaamUp"));

		// ADD BY SALNIZAM - TAMBAH FIELD ALAMAT
		h.put("alamat_hta1", getParam("txtAlamat1Htaam1"));
		h.put("alamat_hta2", getParam("txtAlamat2Htaam"));
		h.put("alamat_hta3", getParam("txtAlamat3Htaam"));
		h.put("poskod", getParam("txtAlamatPoskodHtaam"));
		myLogger.info("txtBandarHartaHtaamX2 : "+getParam("txtBandarHartaHtaamX2"));
		if (getParam("txtBandarHartaHtaamX2") != "") {
			h.put("id_bandarhta", getParam("txtBandarHartaHtaamX2"));
		} else {
			h.put("id_bandarhta", 0);
		}
		myLogger.info("txtAlamatPoskodHtaam :" + getParam("txtBandarHartaHtaamX2"));
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
		//System.out.println("txtNoPersHtaamUp >>> "+getParam("txtNoPersHtaamUp"));
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

		logic.updateHtaam(h);
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