package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.CacheManager;
import ekptg.engine.CachedObject;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;
import ekptg.model.RazTemplete;
import ekptg.model.ppk.BicaraInteraktifData;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8BicaraData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8KptsanBicaraData;

public class BicaraInteraktif extends AjaxBasedModule {

	public ResourceBundle rbCetakan = ResourceBundle.getBundle("cetakan");
	static Logger myLogger = Logger.getLogger(BicaraInteraktif.class);
	String skrin_name = "app/ppk/BicaraInteraktif/index.jsp";
	String formNameAjax = "Fekptg_view_ppk_BicaraInteraktif";
	BicaraInteraktifData modelBI = new BicaraInteraktifData();
	FrmPrmhnnSek8KptsanBicaraData modelKeputusanPerbicaraan = new FrmPrmhnnSek8KptsanBicaraData();
	FrmPrmhnnSek8DaftarSek8InternalData modelInteralData = new FrmPrmhnnSek8DaftarSek8InternalData();
	FrmPrmhnnSek8BicaraData modelBicaraData = new FrmPrmhnnSek8BicaraData();
	public RazTemplete RT = new RazTemplete();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	// private final String BASENAME = "dbconnection";
	// private ResourceBundle rb = ResourceBundle.getBundle(BASENAME);
	public String fontSize = rbCetakan.getString("fontSizeCetakan");
	// public String fontSize = "font-size: 16px;";
	String checkedSelesai = "";// arief add
	String checkedTangguh = "";// arief add
	String checkedBatal = "";// arief add


	// List listPerbicaraan = null;
	@SuppressWarnings("unused")
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		// String namaSkema = rb.getString("user");
		// myLogger.info("namaSkema : "+namaSkema);
		this.context.put("fontSize", fontSize);
		String command = getParam("command");
		this.context.put("command", command);
		String action = getParam("action");
		String fromDashboard = getParam("fromDashboard");
		this.context.put("fromDashboard", fromDashboard);
		myLogger.info("command : " + command + " action : " + action);
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		Vector getrecord_perintah = new Vector(); // arief add
		String idpermohonan = getParam("idpermohonan");// arief add
		String idNegeriMhn = "";// arief add
		Vector list = new Vector();// arief add
		String STATUS_PERBICARAAN = ""; //arief add
		/*
		 * if(USER_ID_SYSTEM == null) { skrin_name = "app/blankSessionOut.jsp"; } else {
		 */
		Map getDetailUsers = modelBI.getDetailUsers(session, "", USER_ID_SYSTEM, "", null);
		String id_jawatan_login = "";
		String id_negeri_login = "";
		String id_seksyen_login = "";
		if (getDetailUsers != null) {
			id_jawatan_login = (String) getDetailUsers.get("ID_JAWATAN");
			id_negeri_login = (String) getDetailUsers.get("ID_NEGERI");
			id_seksyen_login = (String) getDetailUsers.get("ID_SEKSYEN");
		}
		this.context.put("id_jawatan_login", id_jawatan_login);
		this.context.put("id_negeri_login", id_negeri_login);
		this.context.put("id_seksyen_login", id_seksyen_login);

		List listKehadiran = null;
		List listPermohonanTukarPegawai = null;
		List listTurutHadir = null;
		Map viewTuruthadir = null;
		List listTidakHadir = null;// arief add
		Map viewTidakHadir = null;// arief add
		List listSaksi = null;// arief add
		Map viewSaksi = null;// arief add
		List listPerbicaraan = null;
		List listKronologiStatus = null;
		defaultPut();

		if (command.equals("showListPerbicaraan") || command.equals("cariListPerbicaraan")) {
			String paramsButton = "";
			String flagCari = "";
			String htmlSkrin = "";
			if (command.equals("cariListPerbicaraan")) {
				flagCari = "Y";
				// this.context.put("command","");
			}
			String cacheID = "listPerbicaraanBicaraInteraktif" + USER_ID_SYSTEM;
			CachedObject get_co_listPerbicaraan = (CachedObject) CacheManager.getCache(cacheID);

			myLogger.info("action : " + action + " get_co_listPerbicaraan : " + get_co_listPerbicaraan);
			Db db = null;
			try {
				db = new Db();
				String username = "";
				if (getDetailUsers != null) {
					username = (String) getDetailUsers.get("USER_NAME");
				}
				String list_id_pegawai = modelBI.getDetailPegawaiList(session, username, db);
				this.context.put("list_id_pegawai", list_id_pegawai);
				if (action.equals("")
						// || command.equals("cariListPerbicaraan")
						|| // comment dlu sementara
						get_co_listPerbicaraan == null) {
					listPerbicaraan = listPerbicaraan(session, USER_ID_SYSTEM, id_jawatan_login, id_negeri_login,
							flagCari, "", db);
					CachedObject set_co_listPerbicaraan = new CachedObject(listPerbicaraan, cacheID, 0);
					CacheManager.putCache(set_co_listPerbicaraan);
				} else if (get_co_listPerbicaraan != null) {
					listPerbicaraan = (List) get_co_listPerbicaraan.object;
				} else {
					CacheManager.removeCache(cacheID);
				}
				htmlSkrin = setupSkrinCarianBicara(session, "carianBicara", command, formName, "edit", paramsButton,
						db);

			} finally {
				if (db != null)
					db.close();
			}
			myLogger.info("SIZE : " + listPerbicaraan.size());
			this.context.put("div", "listPerbicaraan");
			this.context.put("htmlCarianBicara", htmlSkrin);
			this.context.put("flagOpenTP", getParam("flagOpenTP"));
			setupPageMainList(session, action, listPerbicaraan, "listPerbicaraan", command);
			skrin_name = "app/ppk/BicaraInteraktif/listPerbicaraan.jsp";
		} else if (command.equals("showPermohonanTukarPegawai") || command.equals("cariPermohonanTukarPegawai")) {
			String cacheID = "listPermohonanTukarPegawai" + USER_ID_SYSTEM;
			CachedObject get_co_listPermohonanTukarPegawai = (CachedObject) CacheManager.getCache(cacheID);
			myLogger.info("action : " + action + " get_co_listPerbicaraan : " + get_co_listPermohonanTukarPegawai);
			String htmlSkrin = "";
			String flagCari = "";
			String paramsButton = "";

			if (command.equals("cariPermohonanTukarPegawai")) {
				flagCari = "Y";
			}

			Db db = null;
			try {
				db = new Db();
				if (action.equals("")
						// || command.equals("cariListPerbicaraan")
						|| // comment dlu sementara
						get_co_listPermohonanTukarPegawai == null) {
					listPermohonanTukarPegawai = listPermohonanTukarPegawai(session, USER_ID_SYSTEM, id_jawatan_login,
							id_negeri_login, flagCari, "", "", "", null);
					CachedObject set_co_listPermohonanTukarPegawai = new CachedObject(listPermohonanTukarPegawai,
							cacheID, 0);
					CacheManager.putCache(set_co_listPermohonanTukarPegawai);
				} else if (get_co_listPermohonanTukarPegawai != null) {
					listPermohonanTukarPegawai = (List) get_co_listPermohonanTukarPegawai.object;
				} else {
					CacheManager.removeCache(cacheID);
				}
				htmlSkrin = setupSkrinCarianTukarPegawai(session, "carianTukarPegawai", command, formName, "edit",
						paramsButton, db);
			} finally {
				if (db != null)
					db.close();
			}

			myLogger.info("SIZE : " + listPermohonanTukarPegawai.size());
			this.context.put("div", "listPermohonanTukarPegawai");
			this.context.put("htmlCarianTukarPegawai", htmlSkrin);
			this.context.put("flagOpenTPK", getParam("flagOpenTPK"));
			setupPageMainList(session, action, listPermohonanTukarPegawai, "listPermohonanTukarPegawai", command);
			skrin_name = "app/ppk/BicaraInteraktif/listPermohonanTukarPegawai.jsp";
		} else if (command.equals("viewPerbicaraan") || command.equals("viewPerbicaraanFromPerintah")) {

			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			myLogger.info(">>>>>>>>>>>>>>> ID_PERBICARAAN : " + ID_PERBICARAAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			String ID_SIMATI = getParam("ID_SIMATI");
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");

			String FLAG_CREATE_HISTORY = "";
			Db db = null;

			try {
				db = new Db();
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				// this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
				FLAG_CREATE_HISTORY = (String) mainID.get("FLAG_CREATE_HISTORY");
				myLogger.info("FLAG_CREATE_HISTORY :::::::: " + FLAG_CREATE_HISTORY);
				// incase ada rekod yg belum ada history

				if (command.equals("viewPerbicaraanFromPerintah")) {
					ID_PERMOHONANSIMATI = (String) mainID.get("ID_PERMOHONANSIMATI");
					ID_PERMOHONAN = (String) mainID.get("ID_PERMOHONAN");
					ID_PEMOHON = (String) mainID.get("ID_PEMOHON");
					ID_SIMATI = (String) mainID.get("ID_SIMATI");
				}

				// off dlu, kat local lembab

				if (!FLAG_CREATE_HISTORY.equals("Y")) {
					mainheader.getHeaderData(session, ID_PERMOHONAN, "Y", "", "T");
				}

				listKronologiStatus = modelBI.listKronologiStatus(session, ID_PERMOHONAN, db);
				this.context.put("listKronologiStatus", listKronologiStatus);

				this.context.put("viewPerbicaraan",
						modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db));
				this.context.put("listPerbicaraanLain",
						modelBI.listPerbicaraanLain(session, ID_PERMOHONAN, ID_PERMOHONANSIMATI, db));
			} finally {
				if (db != null)
					db.close();
			}

			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);

			if (command.equals("viewPerbicaraanFromPerintah")) {
				skrin_name = "app/ppk/BicaraInteraktif/index.jsp";
			} else {
				skrin_name = "app/ppk/BicaraInteraktif/viewPerbicaraan.jsp";
			}
		}

		else if (command.equals("selectBicaraLain")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONANSIMATI = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			Db db = null;
			try {
				db = new Db();
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String) mainID.get("ID_PERMOHONANSIMATI");
				this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
				ID_PERMOHONAN = (String) mainID.get("ID_PERMOHONAN");
				this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
				ID_PEMOHON = (String) mainID.get("ID_PEMOHON");
				this.context.put("ID_PEMOHON", ID_PEMOHON);
				ID_SIMATI = (String) mainID.get("ID_SIMATI");
				this.context.put("ID_SIMATI", ID_SIMATI);
				this.context.put("viewPerbicaraan",
						modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db));
				this.context.put("listPerbicaraanLain",
						modelBI.listPerbicaraanLain(session, ID_PERMOHONAN, ID_PERMOHONANSIMATI, db));
			} finally {
				if (db != null)
					db.close();
			}
			skrin_name = "app/ppk/BicaraInteraktif/viewPerbicaraan.jsp";
		} else if (command.equals("showHeader")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);

			/*
			 * this.context.put("headerppk", mainheader.getHeaderData(session,
			 * ID_PERMOHONAN, "Y", "", "T")); Vector list_sub = null; list_sub =
			 * mainheader.carianFail(ID_PERMOHONAN, "Y","", "T");
			 * this.context.put("list_sub_header", list_sub);
			 */

			Db db = null;
			try {
				db = new Db();
				listKronologiStatus = modelBI.listKronologiStatus(session, ID_PERMOHONAN, db);
				this.context.put("listKronologiStatus", listKronologiStatus);
				this.context.put("viewPerbicaraan",
						modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db));
				this.context.put("listPerbicaraanLain",
						modelBI.listPerbicaraanLain(session, ID_PERMOHONAN, ID_PERMOHONANSIMATI, db));
			} finally {
				if (db != null)
					db.close();
			}
			skrin_name = "app/ppk/BicaraInteraktif/viewHeader.jsp";
		} else if (command.equals("openSkrinTukarPegawai_multiple") || command.equals("saveTukarPegawaiMultiple")
				|| command.equals("resetTukarPegawaiMultiple") || command.equals("lulusTukarPegawaiMultiple")
				|| command.equals("tolakTukarPegawaiMultiple")
		/*
		 * || command.equals("editTukarPegawai") || command.equals("resetTukarPegawai")
		 * || command.equals("saveTukarPegawai") || command.equals("lulusTukarPegawai")
		 * || command.equals("tolakTukarPegawai")
		 */) {

			String setupSkrin = "";
			String mode = getParam("mode");
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			this.context.put("NAMA_TABLE", NAMA_TABLE);
			String FIELD_PK = getParam("FIELD_PK");
			this.context.put("FIELD_PK", FIELD_PK);
			String standardParam = "NAMA_TABLE=" + NAMA_TABLE + "&FIELD_PK=" + FIELD_PK + "&mode=" + mode
					+ "&skrinName=" + skrinName + "&div=" + getParam("div");
			String openFrom = getParam("openFrom");
			String multipleDaftar = "Y";
			Db db = null;
			try {
				db = new Db();

				String ID_PERBICARAAN = "";
				String ID_PERMOHONANSIMATI = "";
				String ID_FAIL = "";
				String ID_PERMOHONAN = "";
				String ID_PEGAWAIASAL = "";
				String ID_PEMOHONTUKARPEGAWAI = USER_ID_SYSTEM;
				String STATUS_TUKARPEGAWAI = "";
				String ID_NEGERIPEGAWAIBARU = "";
				String ID_PEGAWAIBARU = "";
				String CATATAN_PEMOHON = "";
				String CATATAN_PELULUS = "";
				String TARIKH_MOHON = "";
				String TARIKH_KEPUTUSAN = "";
				String FLAG_DAFTAR_TERUS = "";
				String ID_NEGERIPEGAWAIASAL = "";
				String ID_TUKARPEGAWAI = "";
				String NAMA_PEGAWAI_BARU = "";

				String NO_FAIL = "";
				String TARIKH_BICARA = "";
				String MASA_BICARA = "";
				String BIL_BICARA = "";
				String PEG_PENGENDALI = "";

				List listBicaraMohonMultiple = Collections.synchronizedList(new ArrayList());
				Map h_listBicaraMohonMultiple = null;

				if (command.equals("saveTukarPegawaiMultiple")) {
					int bil = 0;
					String[] listID_PERBICARAAN = request.getParameterValues("listID_PERBICARAAN");
					if (listID_PERBICARAAN != null) {
						for (String lp : listID_PERBICARAAN) {
							myLogger.info("::: ID_PERIBICARAAN ::: " + lp);
							String checkBoxBicara = getParam("selectTP" + lp);
							myLogger.info("checkBoxBicara :::::::::: " + checkBoxBicara);

							if (!checkBoxBicara.equals("")) {
								h_listBicaraMohonMultiple = Collections.synchronizedMap(new HashMap());
								bil++;

								myLogger.info("skrinName :::::::::: " + skrinName);
								ID_PERBICARAAN = checkBoxBicara;
								ID_PERMOHONANSIMATI = getParam("listID_PERMOHONANSIMATI" + ID_PERBICARAAN);
								ID_FAIL = getParam("listID_FAIL" + ID_PERBICARAAN);
								ID_PERMOHONAN = getParam("listID_PERMOHONAN" + ID_PERBICARAAN);
								ID_PEGAWAIASAL = getParam("listID_UNITPSK" + ID_PERBICARAAN);
								ID_PEMOHONTUKARPEGAWAI = USER_ID_SYSTEM;
								STATUS_TUKARPEGAWAI = getParam(skrinName + "STATUS_TUKARPEGAWAI");
								ID_NEGERIPEGAWAIBARU = getParam(skrinName + "ID_NEGERIPEGAWAIBARU");
								ID_PEGAWAIBARU = getParam(skrinName + "ID_PEGAWAIBARU");
								CATATAN_PEMOHON = getParam(skrinName + "CATATAN_PEMOHON");
								CATATAN_PELULUS = getParam(skrinName + "CATATAN_PELULUS");
								TARIKH_MOHON = getParam(skrinName + "TARIKH_MOHON");
								TARIKH_KEPUTUSAN = getParam(skrinName + "TARIKH_KEPUTUSAN");
								FLAG_DAFTAR_TERUS = getParam(skrinName + "FLAG_DAFTAR_TERUS");
								ID_NEGERIPEGAWAIASAL = getParam("listID_NEGERI" + ID_PERBICARAAN);
								STATUS_PERBICARAAN = getParam("listkSTATUS_PERBICARAAN" + STATUS_PERBICARAAN);//arief add
								NO_FAIL = getParam("listNO_FAIL" + ID_PERBICARAAN);
								TARIKH_BICARA = getParam("listTARIKH_BICARA" + ID_PERBICARAAN);
								MASA_BICARA = getParam("listMASA_BICARA" + ID_PERBICARAAN);
								BIL_BICARA = getParam("listBIL_BICARA" + ID_PERBICARAAN);
								PEG_PENGENDALI = getParam("listPEG_PENGENDALI" + ID_PERBICARAAN);

								h_listBicaraMohonMultiple.put("NO", bil + "");
								h_listBicaraMohonMultiple.put("NO_FAIL", NO_FAIL);
								h_listBicaraMohonMultiple.put("TARIKH_BICARA", TARIKH_BICARA);
								h_listBicaraMohonMultiple.put("MASA_BICARA", MASA_BICARA);
								h_listBicaraMohonMultiple.put("BIL_BICARA", BIL_BICARA);
								h_listBicaraMohonMultiple.put("PEG_PENGENDALI", PEG_PENGENDALI);
								h_listBicaraMohonMultiple.put("NAMA_PEGAWAI_BARU", NAMA_PEGAWAI_BARU);
								h_listBicaraMohonMultiple.put("STATUS_PERBICARAAN", STATUS_PERBICARAAN);//arief add
								listBicaraMohonMultiple.add(h_listBicaraMohonMultiple);

								if (FLAG_DAFTAR_TERUS.equals("Y")) {
									STATUS_TUKARPEGAWAI = "2";
								}

								myLogger.info("ID_PERBICARAAN : " + ID_PERBICARAAN);
								myLogger.info("ID_PERMOHONANSIMATI : " + ID_PERMOHONANSIMATI);
								myLogger.info("ID_FAIL : " + ID_FAIL);
								myLogger.info("ID_PERMOHONAN : " + ID_PERMOHONAN);
								myLogger.info("ID_PEGAWAIASAL : " + ID_PEGAWAIASAL);
								myLogger.info("ID_PEMOHONTUKARPEGAWAI : " + ID_PEMOHONTUKARPEGAWAI);
								myLogger.info("STATUS_TUKARPEGAWAI : " + STATUS_TUKARPEGAWAI);
								myLogger.info("ID_NEGERIPEGAWAIBARU : " + ID_NEGERIPEGAWAIBARU);
								myLogger.info("ID_PEGAWAIBARU : " + ID_PEGAWAIBARU);
								myLogger.info("CATATAN_PEMOHON : " + CATATAN_PEMOHON);
								myLogger.info("CATATAN_PELULUS : " + CATATAN_PELULUS);
								myLogger.info("TARIKH_MOHON : " + TARIKH_MOHON);
								myLogger.info("TARIKH_KEPUTUSAN : " + TARIKH_KEPUTUSAN);
								myLogger.info("FLAG_DAFTAR_TERUS : " + FLAG_DAFTAR_TERUS);
								myLogger.info("ID_NEGERIPEGAWAIASAL : " + ID_NEGERIPEGAWAIASAL);
								myLogger.info("NO_FAIL : " + NO_FAIL);
								myLogger.info("TARIKH_BICARA : " + TARIKH_BICARA);
								myLogger.info("MASA_BICARA : " + MASA_BICARA);
								myLogger.info("BIL_BICARA : " + BIL_BICARA);
								myLogger.info("PEG_PENGENDALI : " + PEG_PENGENDALI);
								myLogger.info("STATUS_PERBICARAAN : " + STATUS_PERBICARAAN);//arief add
								myLogger.info("--------------------------------------------------------");

								Map setupTukarPegawai = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName,
										ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, NAMA_TABLE, db);
								saveTukarPegawai(session, command, setupTukarPegawai, ID_FAIL, ID_PERMOHONAN, "",
										ID_PERMOHONANSIMATI, formName, ID_PERBICARAAN, skrinName, ID_PEGAWAIASAL,
										ID_PEGAWAIBARU, ID_NEGERIPEGAWAIBARU, ID_NEGERIPEGAWAIASAL, CATATAN_PEMOHON,
										CATATAN_PELULUS, STATUS_TUKARPEGAWAI, TARIKH_MOHON, TARIKH_KEPUTUSAN,
										ID_PEMOHONTUKARPEGAWAI, FLAG_DAFTAR_TERUS, multipleDaftar, db);
							}
						}
					}
				} else if (command.equals("lulusTukarPegawaiMultiple") || command.equals("tolakTukarPegawaiMultiple")) {
					int bil = 0;
					String[] listkID_TUKARPEGAWAI = request.getParameterValues("listkID_TUKARPEGAWAI");
					if (listkID_TUKARPEGAWAI != null) {
						for (String lp : listkID_TUKARPEGAWAI) {
							myLogger.info("::: ID_TUKARPEGAWAI ::: " + lp);
							String checkBoxPegawai = getParam("selectTPK" + lp);
							myLogger.info("checkBoxPegawai :::::::::: " + checkBoxPegawai);
							if (!checkBoxPegawai.equals("")) {
								bil++;
								h_listBicaraMohonMultiple = Collections.synchronizedMap(new HashMap());

								myLogger.info("skrinName :::::::::: " + skrinName);
								ID_TUKARPEGAWAI = checkBoxPegawai;
								ID_PERBICARAAN = getParam("listkID_PERBICARAAN" + ID_TUKARPEGAWAI);
								ID_PERMOHONANSIMATI = getParam("listkID_PERMOHONANSIMATI" + ID_TUKARPEGAWAI);
								ID_FAIL = getParam("listkID_FAIL" + ID_TUKARPEGAWAI);
								ID_PERMOHONAN = getParam("listkID_PERMOHONAN" + ID_TUKARPEGAWAI);
								ID_NEGERIPEGAWAIASAL = getParam("listkID_NEGERIPEGAWAIASAL" + ID_TUKARPEGAWAI);
								ID_PEGAWAIASAL = getParam("listkID_PEGAWAIASAL" + ID_TUKARPEGAWAI);
								// STATUS_TUKARPEGAWAI = getParam(skrinName+"STATUS_TUKARPEGAWAI");
								if (command.equals("lulusTukarPegawaiMultiple")) {
									STATUS_TUKARPEGAWAI = "2";
									ID_NEGERIPEGAWAIBARU = getParam(skrinName + "ID_NEGERIPEGAWAIBARU");
									ID_PEGAWAIBARU = getParam(skrinName + "ID_PEGAWAIBARU");
								} else if (command.equals("tolakTukarPegawaiMultiple")) {
									STATUS_TUKARPEGAWAI = "3";
									ID_NEGERIPEGAWAIBARU = getParam("listkID_NEGERIPEGAWAIBARU" + ID_TUKARPEGAWAI);
									ID_PEGAWAIBARU = getParam("listkID_PEGAWAIBARU" + ID_TUKARPEGAWAI);
								}

								CATATAN_PEMOHON = getParam("listkCATATAN_PEMOHON" + ID_TUKARPEGAWAI);
								CATATAN_PELULUS = getParam(skrinName + "CATATAN_PELULUS");
								TARIKH_MOHON = getParam(skrinName + "TARIKH_MOHON");
								TARIKH_KEPUTUSAN = getParam(skrinName + "TARIKH_KEPUTUSAN");
								FLAG_DAFTAR_TERUS = getParam(skrinName + "FLAG_DAFTAR_TERUS");

								NO_FAIL = getParam("listkNO_FAIL" + ID_TUKARPEGAWAI);
								TARIKH_BICARA = getParam("listkTARIKH_BICARA" + ID_TUKARPEGAWAI);
								MASA_BICARA = getParam("listkMASA_BICARA" + ID_TUKARPEGAWAI);
								BIL_BICARA = getParam("listkBIL_BICARA" + ID_TUKARPEGAWAI);
								PEG_PENGENDALI = getParam("listkPEG_PENGENDALI" + ID_TUKARPEGAWAI);
								NAMA_PEGAWAI_BARU = getParam("listkNAMA_PEGAWAI_BARU" + ID_TUKARPEGAWAI);
								STATUS_PERBICARAAN = getParam("listkSTATUS_PERBICARAAN" + STATUS_PERBICARAAN);//arief add

								h_listBicaraMohonMultiple.put("NO", bil + "");
								h_listBicaraMohonMultiple.put("NO_FAIL", NO_FAIL);
								h_listBicaraMohonMultiple.put("TARIKH_BICARA", TARIKH_BICARA);
								h_listBicaraMohonMultiple.put("MASA_BICARA", MASA_BICARA);
								h_listBicaraMohonMultiple.put("BIL_BICARA", BIL_BICARA);
								h_listBicaraMohonMultiple.put("PEG_PENGENDALI", PEG_PENGENDALI);
								h_listBicaraMohonMultiple.put("NAMA_PEGAWAI_BARU", NAMA_PEGAWAI_BARU);
								h_listBicaraMohonMultiple.put("STATUS_PERBICARAAN", STATUS_PERBICARAAN);//arief add
								listBicaraMohonMultiple.add(h_listBicaraMohonMultiple);

								myLogger.info("ID_PERBICARAAN : " + ID_PERBICARAAN);
								myLogger.info("ID_PERMOHONANSIMATI : " + ID_PERMOHONANSIMATI);
								myLogger.info("ID_FAIL : " + ID_FAIL);
								myLogger.info("ID_PERMOHONAN : " + ID_PERMOHONAN);
								myLogger.info("ID_PEGAWAIASAL : " + ID_PEGAWAIASAL);
								myLogger.info("ID_PEMOHONTUKARPEGAWAI : " + ID_PEMOHONTUKARPEGAWAI);
								myLogger.info("STATUS_TUKARPEGAWAI : " + STATUS_TUKARPEGAWAI);
								myLogger.info("ID_NEGERIPEGAWAIBARU : " + ID_NEGERIPEGAWAIBARU);
								myLogger.info("ID_PEGAWAIBARU : " + ID_PEGAWAIBARU);
								myLogger.info("CATATAN_PEMOHON : " + CATATAN_PEMOHON);
								myLogger.info("CATATAN_PELULUS : " + CATATAN_PELULUS);
								myLogger.info("TARIKH_MOHON : " + TARIKH_MOHON);
								myLogger.info("TARIKH_KEPUTUSAN : " + TARIKH_KEPUTUSAN);
								myLogger.info("FLAG_DAFTAR_TERUS : " + FLAG_DAFTAR_TERUS);
								myLogger.info("ID_NEGERIPEGAWAIASAL : " + ID_NEGERIPEGAWAIASAL);
								myLogger.info("STATUS_PERBICARAAN : " + STATUS_PERBICARAAN);//arief add
								myLogger.info("--------------------------------------------------------");

								Map setupTukarPegawai = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName,
										ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, NAMA_TABLE, db);
								saveTukarPegawai(session, command, setupTukarPegawai, ID_FAIL, ID_PERMOHONAN,
										ID_TUKARPEGAWAI, ID_PERMOHONANSIMATI, formName, ID_PERBICARAAN, skrinName,
										ID_PEGAWAIASAL, ID_PEGAWAIBARU, ID_NEGERIPEGAWAIBARU, ID_NEGERIPEGAWAIASAL,
										CATATAN_PEMOHON, CATATAN_PELULUS, STATUS_TUKARPEGAWAI, TARIKH_MOHON,
										TARIKH_KEPUTUSAN, ID_PEMOHONTUKARPEGAWAI, FLAG_DAFTAR_TERUS, multipleDaftar,
										db);
							}
						}
					}

				}

				if (multipleDaftar.equals("Y") && (command.equals("lulusTukarPegawaiMultiple")
						|| command.equals("saveTukarPegawaiMultiple") || command.equals("tolakTukarPegawaiMultiple"))) {
					String SELECTED_NAMA_NEGERI = getParam("SELECTED_NAMA_NEGERI");
					String SELECTED_NAMA_PEGAWAI = getParam("SELECTED_NAMA_PEGAWAI");
					;

					// call emel function nnti kat sini
					modelBI.hantarEmelTukarPegawai(session, "", "", "", "", FLAG_DAFTAR_TERUS, ID_NEGERIPEGAWAIASAL,
							ID_PEGAWAIASAL, ID_NEGERIPEGAWAIBARU, ID_PEGAWAIBARU, STATUS_TUKARPEGAWAI, multipleDaftar,
							SELECTED_NAMA_NEGERI, SELECTED_NAMA_PEGAWAI, listBicaraMohonMultiple, db);
				}

				setupSkrin = setupPegawaiMultiple(session, mode, skrinName, null, NAMA_TABLE, FIELD_PK, command,
						id_jawatan_login, id_negeri_login, standardParam, "pemohon", openFrom, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("div", getParam("div"));
			this.context.put("htmlSkrinMaklumat", setupSkrin);

			if (command.equals("saveTukarPegawaiMultiple") || command.equals("lulusTukarPegawaiMultiple")
					|| command.equals("tolakTukarPegawaiMultiple")) {
				this.context.put("SuccessMesej", "PermohonTukarPegawai");
				if (command.equals("lulusTukarPegawaiMultiple") || command.equals("tolakTukarPegawaiMultiple")) {
					this.context.put("SuccessMesej", "KelulusanTukarPegawai");
				}
				skrin_name = "app/ppk/BicaraInteraktif/blank.jsp";
			} else {
				skrin_name = "app/ppk/BicaraInteraktif/viewTukarPegawai.jsp";
			}

		}

		else if (command.equals("openSkrinTukarPegawai") || command.equals("editTukarPegawai")
				|| command.equals("resetTukarPegawai") || command.equals("saveTukarPegawai")
				|| command.equals("lulusTukarPegawai") || command.equals("tolakTukarPegawai")) {
			String openFormTukarPegawai = getParam("openFormTukarPegawai");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String openFrom = getParam("openFrom");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_FAIL = getParam("ID_FAIL");
			this.context.put("ID_FAIL", ID_FAIL);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			this.context.put("NAMA_TABLE", NAMA_TABLE);
			String FIELD_PK = getParam("FIELD_PK");
			this.context.put("FIELD_PK", FIELD_PK);
			String mode = getParam("mode");
			this.context.put("div", getParam("div"));
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_TUKARPEGAWAI = getParam("ID_TUKARPEGAWAI");
			this.context.put("ID_TUKARPEGAWAI", ID_TUKARPEGAWAI);
			String standardParam = "ID_TUKARPEGAWAI=" + ID_TUKARPEGAWAI + "&ID_SIMATI=" + ID_SIMATI + "&ID_FAIL="
					+ ID_FAIL + "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN
					+ "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&NAMA_TABLE=" + NAMA_TABLE + "&FIELD_PK=" + FIELD_PK
					+ "&skrinName=" + skrinName + "&div=" + getParam("div");
			String setupSkrin = "";
			List listPermohonanTukarPegawaiSejarah = null;
			Db db = null;
			try {
				db = new Db();

				myLogger.info("dari skrin skrinName >>>>>>>>>> " + skrinName);

				Map setupTukarPegawai = null;
				if (skrinName.equals("tukarpegawaiKPP")) {
					setupTukarPegawai = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, "ID_TUKARPEGAWAI", ID_TUKARPEGAWAI, NAMA_TABLE, db);
				}

				if (command.equals("saveTukarPegawai") || command.equals("lulusTukarPegawai")
						|| command.equals("tolakTukarPegawai")) {
					// savePegawai
					ID_TUKARPEGAWAI = saveTukarPegawai(session, command, setupTukarPegawai, ID_FAIL, ID_PERMOHONAN,
							ID_TUKARPEGAWAI, ID_PERMOHONANSIMATI, formName, ID_PERBICARAAN, skrinName, "N", db);
					setupTukarPegawai = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, "ID_TUKARPEGAWAI", ID_TUKARPEGAWAI, NAMA_TABLE, db);
				} else if (command.equals("editTukarPegawai") || command.equals("resetTukarPegawai")) {
					mode = "edit";
				}

				if (!ID_PERBICARAAN.equals("")
				// && ( skrinName.equals("tukarpegawaiKPP") ||
				// (command.equals("saveTukarPegawai") || command.equals("lulusTukarPegawai") ||
				// command.equals("tolakTukarPegawai")))
				) {
					listPermohonanTukarPegawaiSejarah = listPermohonanTukarPegawai(session, USER_ID_SYSTEM, "", "", "",
							"", ID_PERBICARAAN, ID_TUKARPEGAWAI, db);
				}

				if (mode.equals("")) {
					if (setupTukarPegawai != null) {
						mode = "view";
					} else {
						mode = "edit";
					}
				}

				// untuk KPP

				String statusTukarPegawai = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupTukarPegawai,
						NAMA_TABLE, "", "", ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", db);
				myLogger.info("----------------------------------------");
				myLogger.info(">>>>>>>>>>> skrinName : " + skrinName);
				myLogger.info(">>>>>>>>>>> statusTukarPegawai : " + statusTukarPegawai);
				myLogger.info(">>>>>>>>>>> id_jawatan_login : " + id_jawatan_login);
				myLogger.info(">>>>>>>>>>> id_negeri_login : " + id_negeri_login);
				myLogger.info(">>>>>>>>>>> STATUS_PERBICARAAN : " + STATUS_PERBICARAAN);//arief add
				myLogger.info("----------------------------------------");

				if (skrinName.equals("tukarpegawaiKPP") && statusTukarPegawai.equals("1")
						&& (id_jawatan_login.equals("4") || id_jawatan_login.equals("5"))) {
					mode = "edit";
				}

				if (!openFormTukarPegawai.equals("N")) {
					int check_TP_baru = 0;
					String NO_TUKARPEGAWAI_CHECK = "";

					if (listPermohonanTukarPegawaiSejarah.size() != 0) {
						for (int i = 0; i < listPermohonanTukarPegawaiSejarah.size(); i++) {
							Map map_column_name = (Map) listPermohonanTukarPegawaiSejarah.get(i);
							String STATUS_TUKARPEGAWAI_CHECK = (String) map_column_name.get("STATUS_TUKARPEGAWAI");
							if (!NO_TUKARPEGAWAI_CHECK.equals("")) {
								NO_TUKARPEGAWAI_CHECK += ", " + (String) map_column_name.get("NO_TUKARPEGAWAI");
							} else {
								NO_TUKARPEGAWAI_CHECK += (String) map_column_name.get("NO_TUKARPEGAWAI");
							}
							if (STATUS_TUKARPEGAWAI_CHECK.equals("1")) {
								check_TP_baru++;
							}
						}
					}

					if (check_TP_baru > 0) {
						setupSkrin = "<br><div><i><font color='red' class='blink' >Perhatian</font> : Telah terdapat permohonan tukar pegawai untuk perbicaraan ini [<b>"
								+ NO_TUKARPEGAWAI_CHECK
								+ "</b>] dan permohonan tersebut sedang diproses.</i></div></br>";
					} else {
						setupSkrin = setupSkrinTukarPegawai(session, "", "", "", "", "", null, setupTukarPegawai,
								ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command, ID_PERMOHONAN,
								formNameAjax, "view_" + skrinName, NAMA_TABLE, FIELD_PK, mode, standardParam, "Y",
								"pemohon", id_jawatan_login, id_negeri_login, openFrom, db);
					}
				} else {
					setupSkrin = "<br><div><i><font color='red' class='blink' >Perhatian</font> : Permohonan menukar pegawai hanya untuk perbicaraan yang belum selesai.</i></div></br>";
				}

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("listPermohonanTukarPegawaiSejarah", listPermohonanTukarPegawaiSejarah);
			if (command.equals("saveTukarPegawai")) {
				this.context.put("SuccessMesej", "PermohonTukarPegawai");
			} else if (command.equals("lulusTukarPegawai") || command.equals("tolakTukarPegawai")) {
				this.context.put("SuccessMesej", "KelulusanTukarPegawai");
			}
			this.context.put("htmlSkrinMaklumat", setupSkrin);
			skrin_name = "app/ppk/BicaraInteraktif/viewTukarPegawai.jsp";
		} else if (command.equals("saveIntro")) {
			String ID_PERINTAH = getParam("ID_PERINTAH");
			String ID_INTROPERINTAH = getParam("ID_INTROPERINTAH");
			Db db = null;
			try {
				db = new Db();
				modelBI.simpanIntroPerintah(session, ID_PERINTAH, ID_INTROPERINTAH, db);
			} finally {
				if (db != null)
					db.close();
			}
			skrin_name = "app/ppk/BicaraInteraktif/blank.jsp";
		} else if (command.equals("autoSaveKeterangan") || command.equals("autoSaveKeteranganTuruthadir")
				|| command.equals("autoSaveKeteranganSaksi")) // arief add keterangan Saksi
		{
			String ACTION = getParam("ACTION");
			String ID_OBPERMOHONAN = getParam("ID_OBPERMOHONAN");
			String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
			String divUnique_id = getParam("divUnique_id");

			Db db = null;
			try {
				db = new Db();
				if (command.equals("autoSaveKeterangan")) {
					modelBI.simpanKeterangan(ID_BIKEHADIRAN, getParam("KETERANGAN_" + ID_OBPERMOHONAN),
							getParam("NOTA_PEGAWAI_" + ID_OBPERMOHONAN), db);
				} else if (command.equals("autoSaveKeteranganTuruthadir")) {
					modelBI.simpanKeterangan(ID_BIKEHADIRAN, getParam("KETERANGAN_TURUTHADIR_" + ID_BIKEHADIRAN),
							getParam("NOTA_PEGAWAI_TURUTHADIR_" + ID_BIKEHADIRAN), db);
				} else if (command.equals("autoSaveKeteranganSaksi")) {
					modelBI.simpanKeterangan(ID_BIKEHADIRAN, getParam("KETERANGAN_SAKSI_" + ID_BIKEHADIRAN),
							getParam("NOTA_PEGAWAI_SAKSI_" + ID_BIKEHADIRAN), db);// arief add
				}
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("ACTION", ACTION);
			this.context.put("ID_OBPERMOHONAN", ID_OBPERMOHONAN);
			this.context.put("ID_BIKEHADIRAN", ID_BIKEHADIRAN);
			this.context.put("divUnique_id", divUnique_id);

			skrin_name = "app/ppk/BicaraInteraktif/divTimeAutoSave.jsp";
		}

		else if (command.equals("autoSaveKeteranganPerintah")) {
			String ACTION = getParam("ACTION");
			String ID_PERINTAH = getParam("keputusanID_PERINTAH");
			String ID_PERBICARAAN = getParam("keputusanID_PERBICARAAN");
			String CATATAN_KEPUTUSAN_PERBICARAAN = getParam("keputusanCATATAN_KEPUTUSAN_PERBICARAAN");
			String CATATAN = getParam("keputusanCATATAN");
			String INTRO_CATATAN = getParam("keputusanINTRO_CATATAN");
			String CATATAN_DOCKIV = getParam("keputusanCATATAN_DOCKIV");
			String divUnique_id = getParam("divUnique_id");

			myLogger.info("autoSaveKeteranganPerintah >>>> ID_PERINTAH : " + ID_PERINTAH);
			myLogger.info("autoSaveKeteranganPerintah >>>> ID_PERBICARAAN : " + ID_PERBICARAAN);

			Db db = null;
			try {
				db = new Db();
				modelBI.simpanKeteranganPerintah(ID_PERINTAH, ID_PERBICARAAN, CATATAN_KEPUTUSAN_PERBICARAAN, CATATAN,
						INTRO_CATATAN, CATATAN_DOCKIV, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("ACTION", ACTION);
			this.context.put("ID_PERINTAH", ID_PERINTAH);
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			this.context.put("divUnique_id", divUnique_id);

			skrin_name = "app/ppk/BicaraInteraktif/divTimeAutoSave.jsp";
		}

		else if (command.equals("show_PeringatanMesra")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			int totalHM = 0;
			int totalKeterangan = 0;
			int totalTH = 0;
			int totalKeteranganTH = 0;
			int totalBezaHM = 0;
			int totalBezaTH = 0;
			Db db = null;
			try {
				db = new Db();
				List checkCountKeteranganOB = modelBI.checkCountKeteranganOB(session, ID_PERMOHONANSIMATI,
						ID_PERMOHONAN, ID_PERBICARAAN, "", "OB", db);

				Map map_checkCountKeteranganOB = (Map) checkCountKeteranganOB.get(0);
				if (map_checkCountKeteranganOB != null) {
					totalHM = (Integer) map_checkCountKeteranganOB.get("TOTAL_HADIR");
					totalKeterangan = (Integer) map_checkCountKeteranganOB.get("TOTAL_KETERANGAN");
					totalBezaHM = totalHM - totalKeterangan;
				}

				List checkCountKeteranganTH = modelBI.checkCountKeteranganOB(session, ID_PERMOHONANSIMATI,
						ID_PERMOHONAN, ID_PERBICARAAN, "", "TH", db);

				Map map_checkCountKeteranganTH = (Map) checkCountKeteranganTH.get(0);
				if (map_checkCountKeteranganTH != null) {
					totalTH = (Integer) map_checkCountKeteranganTH.get("TOTAL_HADIR");
					totalKeteranganTH = (Integer) map_checkCountKeteranganTH.get("TOTAL_KETERANGAN");
					totalBezaTH = totalTH - totalKeteranganTH;
				}
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("totalBezaHM", totalBezaHM);
			this.context.put("totalBezaTH", totalBezaTH);
			this.context.put("totalHM", totalHM);
			this.context.put("totalKeterangan", totalKeterangan);
			this.context.put("totalTH", totalTH);
			this.context.put("totalKeteranganTH", totalKeteranganTH);
			skrin_name = "app/ppk/BicaraInteraktif/viewPeringatanMesra.jsp";
		}

		else if (command.equals("showKeterangan") || command.equals("simpanKeterangan")
				|| command.equals("tutupKeterangan")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_OBPERMOHONAN = getParam("ID_OBPERMOHONAN");
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_OBPERMOHONAN", ID_OBPERMOHONAN);
			String ID_OBPERMOHONAN_ASAL = getParam("ID_OBPERMOHONAN_ASAL");
			this.context.put("ID_OBPERMOHONAN_ASAL", ID_OBPERMOHONAN_ASAL);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
			this.context.put("ID_BIKEHADIRAN", ID_BIKEHADIRAN);
			this.context.put("div", getParam("div"));
			String NAMA = "";
			String KETERANGAN = "";
			String NOTA_PEGAWAI = "";
			String ID_OBPERMOHONANMINOR = "";
			String LISTPENJAGA = "";
			viewTuruthadir = null;
			viewSaksi = null; // arief add
			Map viewPejagaWaris = null;
			Db db = null;
			String SEKSYEN = "";
			try {
				db = new Db();
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);

				SEKSYEN = (String) mainID.get("SEKSYEN");

				if (!ID_BIKEHADIRAN.equals("")) {
					viewTuruthadir = modelBI.viewTuruthadir(session, ID_BIKEHADIRAN, db);
					NAMA = (String) viewTuruthadir.get("NAMA") == null ? "" : (String) viewTuruthadir.get("NAMA");
					viewSaksi = modelBI.viewSaksi(session, ID_BIKEHADIRAN, db);// arief add
					NAMA = (String) viewSaksi.get("NAMA") == null ? "" : (String) viewSaksi.get("NAMA");// arief add
				}
				myLogger.info("NAMA ::::::::::: " + NAMA);

				if (command.equals("simpanKeterangan") || command.equals("tutupKeterangan")) {
					modelBI.simpanKeterangan(ID_BIKEHADIRAN, getParam("KETERANGAN_" + ID_OBPERMOHONAN),
							getParam("NOTA_PEGAWAI_" + ID_OBPERMOHONAN), db);
				}
				viewPejagaWaris = modelBI.viewPejagaWaris(session, ID_BIKEHADIRAN, ID_OBPERMOHONAN, db);

				if (viewPejagaWaris != null) {
					ID_OBPERMOHONANMINOR = (String) viewPejagaWaris.get("ID_OBPERMOHONAN") == null ? ""
							: (String) viewPejagaWaris.get("ID_OBPERMOHONAN");
					LISTPENJAGA = (String) viewPejagaWaris.get("LISTPENJAGA") == null ? ""
							: (String) viewPejagaWaris.get("LISTPENJAGA");
				}

				Map viewKeterangan = modelBI.viewKeterangan(session, ID_BIKEHADIRAN, db);
				if (viewKeterangan != null) {
					KETERANGAN = (String) viewKeterangan.get("KETERANGAN") == null ? ""
							: (String) viewKeterangan.get("KETERANGAN");
					NOTA_PEGAWAI = (String) viewKeterangan.get("NOTA_PEGAWAI") == null ? ""
							: (String) viewKeterangan.get("NOTA_PEGAWAI");
				}

				String bahasa = "Bersumpah dalam Bahasa Malaysia/Inggeris/Tamil/Cina dan faham akan maksudnya.<br>";
				if (!ID_PEMOHON.equals("") && KETERANGAN.equals("")) {

					String NO_KP_BARU = "";
					String NO_KP_LAMA = "";
					String NO_KP_LAIN = "";
					String ID_SIMATI = "";
					String NAMA_SIMATI = "";
					String ID_BUKTIMATI = "";
					String NAMA_BUKTIMATI = "";
					String TARIKH_SURAT_AKUAN = "";
					String TARIKH_MATI = "";
					String NO_SIJIL_MATI = "";
					String TEMPAT_MATI = "";
					String SEBAB_MATI = "";
					String ALAMAT_1 = "";
					String ALAMAT_2 = "";
					String ALAMAT_3 = "";
					String POSKOD = "";
					String ID_NEGERI = "";
					String NAMA_NEGERI = "";
					String ID_BANDAR = "";
					String NAMA_BANDAR = "";
					String NAMA_PELBAGAINEGARA = "";
					String UMUR = "";

					Map setupSkrinSimati = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, "simati",
							ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKSIMATI", db);
					if (setupSkrinSimati != null) {
						ID_SIMATI = (String) mainID.get("ID_SIMATI");
						NAMA_SIMATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "NAMA_SIMATI", db);
						NO_KP_BARU = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "NO_KP_BARU", db);
						NO_KP_LAMA = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "NO_KP_LAMA", db);
						NO_KP_LAIN = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "NO_KP_LAIN", db);
						ID_BUKTIMATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "ID_BUKTIMATI", db);
						NO_SIJIL_MATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "NO_SIJIL_MATI", db);
						TARIKH_SURAT_AKUAN = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati,
								"TBLPPKSIMATI", "ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "TARIKH_SURAT_AKUAN", db);
						TARIKH_MATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "TARIKH_MATI", db);
						TEMPAT_MATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "TEMPAT_MATI", db);
						SEBAB_MATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "SEBAB_MATI", db);
						ALAMAT_1 = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "ALAMAT_1", db);
						ALAMAT_2 = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "ALAMAT_2", db);
						ALAMAT_3 = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "ALAMAT_3", db);
						POSKOD = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "POSKOD", db);
						ID_NEGERI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "ID_NEGERI", db);
						ID_BANDAR = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "ID_BANDAR", db);
						NAMA_PELBAGAINEGARA = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati,
								"TBLPPKSIMATI", "ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "NAMA_PELBAGAINEGARA", db);
						UMUR = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinSimati, "TBLPPKSIMATI",
								"ID_SIMATI", ID_SIMATI, ID_PERBICARAAN, "UMUR", db);
						if (!ID_BUKTIMATI.equals("")) {
							NAMA_BUKTIMATI = modelBI.getValueRefTable(session, "TBLPPKRUJBUKTIMATI", "ID_BUKTIMATI", "",
									"KETERANGAN", ID_BUKTIMATI, db);
						}

						if (!ID_NEGERI.equals("")) {
							NAMA_NEGERI = modelBI.getValueRefTable(session, "TBLRUJNEGERI", "ID_NEGERI", "",
									"NAMA_NEGERI", ID_NEGERI, db);
						}

						if (!ID_BANDAR.equals("")) {
							NAMA_BANDAR = modelBI.getValueRefTable(session, "TBLRUJBANDAR", "ID_BANDAR", "",
									"KETERANGAN", ID_BANDAR, db);
						}

					}
					/*
					 * myLogger.info("showKeterangan >>>>>>>>> NAMA_SIMATI : "+NAMA_SIMATI);
					 * myLogger.info("showKeterangan >>>>>>>>> NAMA_BUKTIMATI : "+NAMA_BUKTIMATI);
					 * myLogger.info("showKeterangan >>>>>>>>> NO_SIJIL_MATI : "+NO_SIJIL_MATI);
					 * myLogger.info("showKeterangan >>>>>>>>> TARIKH_SURAT_AKUAN : "
					 * +TARIKH_SURAT_AKUAN);
					 * myLogger.info("showKeterangan >>>>>>>>> TARIKH_MATI : "+TARIKH_MATI);
					 * myLogger.info("showKeterangan >>>>>>>>> TEMPAT_MATI : "+TEMPAT_MATI);
					 * myLogger.info("showKeterangan >>>>>>>>> SEBAB_MATI : "+SEBAB_MATI);
					 * myLogger.info("showKeterangan >>>>>>>>> ALAMAT_1 : "+ALAMAT_1);
					 * myLogger.info("showKeterangan >>>>>>>>> ALAMAT_2 : "+ALAMAT_2);
					 * myLogger.info("showKeterangan >>>>>>>>> ALAMAT_3 : "+ALAMAT_3);
					 * myLogger.info("showKeterangan >>>>>>>>> POSKOD : "+POSKOD);
					 * myLogger.info("showKeterangan >>>>>>>>> NAMA_NEGERI : "+NAMA_NEGERI);
					 * myLogger.info("showKeterangan >>>>>>>>> NAMA_BANDAR : "+NAMA_BANDAR);
					 * myLogger.info("showKeterangan >>>>>>>>> NAMA_PELBAGAINEGARA : "
					 * +NAMA_PELBAGAINEGARA);
					 */
					String noKPSimati = "";
					if (!NO_KP_BARU.equals("")) {
						noKPSimati = NO_KP_BARU;
						if (!NO_KP_LAMA.equals("")) {
							noKPSimati += " (" + NO_KP_LAMA + ")";
						}
					} else if (!NO_KP_LAMA.equals("")) {
						noKPSimati = NO_KP_LAMA;
					} else if (!NO_KP_LAMA.equals("")) {
						noKPSimati = NO_KP_LAIN;
					}

					String alamatTerakhir = "";

					if (!TEMPAT_MATI.equals("")) {
						alamatTerakhir = modelBI.upperCaseAllFirst(modelBI.removeLastKoma(TEMPAT_MATI));
					}

					alamatTerakhir = modelBI.dotdotIfBlank(alamatTerakhir);

					String buktiMati = "";
					if (ID_BUKTIMATI.equals("1")) {
						buktiMati = "No. Sijil Kematian : ";
						if (!NO_SIJIL_MATI.equals("")) {
							buktiMati += modelBI.dotdotIfBlank(NO_SIJIL_MATI);
						}
					} else if (ID_BUKTIMATI.equals("2")) {
						buktiMati = "Tarikh Surat Akuan Kematian : ";
						if (!NO_SIJIL_MATI.equals("")) {
							buktiMati += modelBI.dotdotIfBlank(TARIKH_SURAT_AKUAN);
						}
					} else if (ID_BUKTIMATI.equals("3")) {
						buktiMati = " Bukti Mati : Anggapan Kematian";
					} else if (ID_BUKTIMATI.equals("4")) {
						buktiMati = "Permit Pengkuburan : ";
						if (!NO_SIJIL_MATI.equals("")) {
							buktiMati += modelBI.dotdotIfBlank(NO_SIJIL_MATI);
						}
					}
					buktiMati = modelBI.dotdotIfBlank(buktiMati);

					String keteranganSimati = "<br>Simati bernama " + modelBI.upperCaseAllFirst(NAMA_SIMATI)
							+ ", No. KP " + modelBI.dotdotIfBlank(noKPSimati) + ", mati ketika berumur "
							+ modelBI.dotdotIfBlank(UMUR) + " tahun, ";
					keteranganSimati += "meninggal dunia pada " + modelBI.dotdotIfBlank(TARIKH_MATI) + ", di "
							+ alamatTerakhir + " ";
					keteranganSimati += buktiMati + ". Meninggal dunia sebab " + modelBI.dotdotIfBlank("")
							+ ". Kebumi di " + modelBI.dotdotIfBlank("") + ".<br><br>";

					keteranganSimati += "Saya mengaku simati meninggalkan harta seperti berikut : <br>";

					String cadanganPembahagian = "<br><div style=\"border-bottom: 1px solid #000;width:100%;\" ><b><u>CADANGAN PEMBAHAGIAN</u></b></div><br>Saya mohon harta simati dibahagi seperti berikut :<br>";
					cadanganPembahagian += modelBI.defaultListHarta(session, (String) mainID.get("ID_PEMOHON"),
							(String) mainID.get("ID_SIMATI"), ID_PERBICARAAN,
							(String) mainID.get("ID_PERMOHONANSIMATI"), (String) mainID.get("ID_PERMOHONAN"), "Y", db,
							SEKSYEN);

					if (mainID != null) {
						KETERANGAN = bahasa + keteranganSimati
								+ modelBI.defaultListHarta(session, (String) mainID.get("ID_PEMOHON"),
										(String) mainID.get("ID_SIMATI"), ID_PERBICARAAN,
										(String) mainID.get("ID_PERMOHONANSIMATI"),
										(String) mainID.get("ID_PERMOHONAN"), "", db, SEKSYEN)
								+ cadanganPembahagian;
					}

				} else {
					if (KETERANGAN.equals("")) {
						KETERANGAN = bahasa;
					}
				}

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("viewPejagaWaris", viewPejagaWaris);
			this.context.put("ID_OBPERMOHONANMINOR", ID_OBPERMOHONANMINOR);
			this.context.put("LISTPENJAGA", LISTPENJAGA);
			this.context.put("NAMA", NAMA);
			this.context.put("KETERANGAN", KETERANGAN);
			this.context.put("NOTA_PEGAWAI", NOTA_PEGAWAI);
			this.context.put("scrolPosition", getParam("scrolPosition"));

			// delang
			this.context.put("listKehadiran", modelBI.listKehadiran(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN, ID_PERBICARAAN,
					ID_PEMOHON, null));

			if (command.equals("tutupKeterangan")) {
				skrin_name = "app/ppk/BicaraInteraktif/viewKeteranganTutup.jsp";
			} else {
				skrin_name = "app/ppk/BicaraInteraktif/viewKeterangan.jsp";
			}
		} else if (command.equals("show_kehadiran") || command.equals("refresh_kehadiran")) {
			this.context.put("div", "view_kehadiran");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			// String ID_PEMOHON = getParam("ID_PEMOHON");
			String ID_PEMOHON = "";
			// String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			String ID_PERMOHONANSIMATI = "";
			// String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			String ID_PERMOHONAN = "";
			String ID_SIMATI = "";
			Map mainID = null;
			Db db = null;
			try {
				db = new Db();
				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", "", db));
				this.context.put("dataHubungan", modelBI.setDataList(session, "dataHubungan", "", "",
						"TBLPPKRUJSAUDARA", "ID_SAUDARA", "KOD", "KETERANGAN", db));
				mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String) mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String) mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String) mainID.get("ID_PEMOHON");
				ID_SIMATI = (String) mainID.get("ID_SIMATI");
				listKehadiran = modelBI.listKehadiran(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN, ID_PERBICARAAN,
						ID_PEMOHON, null);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("mainID", mainID);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("listKehadiran", listKehadiran);
			skrin_name = "app/ppk/BicaraInteraktif/viewKehadiran.jsp";
		}

		else if (command.equals("show_bantahan") || command.equals("refresh_bantahan")
				|| command.equals("edit_bantahan") || command.equals("save_bantahan")) {
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_SEJARAHBIMAIN = getParam("ID_SEJARAHBIMAIN");
			this.context.put("ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			String setupSkrin = "";
			String current_previous = getParam("current_previous");
			String flag_gantiPemohon = getParam("flag_gantiPemohon");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));
			String jenis_transaction = "";
			myLogger.info(" div bantahan : " + getParam("div"));
			String mode = "view";
			if (command.equals("edit_bantahan")) {
				mode = "edit";
			}
			String aktiviti = "";
			String standardParam = "ID_PEMOHON=" + ID_PEMOHON + "&ID_SIMATI=" + ID_SIMATI + "&ID_PERMOHONANSIMATI="
					+ ID_PERMOHONANSIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
					+ "&NAMA_TABLE=" + NAMA_TABLE + "&FIELD_PK=" + FIELD_PK + "&skrinName=" + skrinName + "&aktiviti="
					+ aktiviti + "&current_previous=" + current_previous + "&jenis_transaction=" + jenis_transaction;

			Db db = null;
			try {
				db = new Db();

				if (command.equals("save_bantahan")) {
					modelBI.simpanBantahan(session, ID_PERBICARAAN, getParam(skrinName + "FLAG_BANTAHAN"),
							getParam(skrinName + "KETERANGAN_BANTAHAN"), db);
				}

				Map setupSkrinBantahan = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "ID_PERBICARAAN", ID_PERBICARAAN, "TBLPPKPERBICARAAN", db);
				setupSkrin = setupSkrinBantahan(session, jenis_transaction, current_previous, aktiviti, ID_SIMATI,
						ID_SEJARAHBIMAIN, null, setupSkrinBantahan, ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN,
						skrinName, command, ID_PERMOHONAN, formNameAjax, "view_" + skrinName, NAMA_TABLE, FIELD_PK,
						mode, standardParam, "Y", db);
			} finally {
				if (db != null)
					db.close();
			}

			// listKehadiran =
			// modelBI.listKehadiran(session,ID_PERMOHONANSIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PEMOHON,null);
			// this.context.put("listKehadiran", listKehadiran);
			this.context.put("htmlSkrinMaklumat", setupSkrin);
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
		} else if (command.equals("saveTurutHadir")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
			this.context.put("ID_BIKEHADIRAN", ID_BIKEHADIRAN);
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("scrolPosition", getParam("scrolPosition"));
			Db db = null;
			try {
				db = new Db();
				modelBI.simpanKehadiran(session, getParam("KETERANGAN_TURUTHADIR_" + ID_BIKEHADIRAN),
						getParam("NOTA_PEGAWAI_TURUTHADIR_" + ID_BIKEHADIRAN), ID_BIKEHADIRAN, ID_PERBICARAAN,
						getParam("NAMA_TURUTHADIR_" + ID_BIKEHADIRAN),
						getParam("HUBUNGAN_TURUTHADIR_" + ID_BIKEHADIRAN),
						getParam("PENGENALAN_TURUTHADIR_" + ID_BIKEHADIRAN),
						getParam("STATUS_TURUTHADIR_" + ID_BIKEHADIRAN), getParam("UMUR_TURUTHADIR_" + ID_BIKEHADIRAN),
						"T", "", db);
				viewTuruthadir = modelBI.viewTuruthadir(session, ID_BIKEHADIRAN, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("viewTuruthadir", viewTuruthadir);
			skrin_name = "app/ppk/BicaraInteraktif/viewRowTurutHadir.jsp";
		}
		// arief add saksi
		else if (command.equals("saveSaksi")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
			this.context.put("ID_BIKEHADIRAN", ID_BIKEHADIRAN);
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("scrolPosition", getParam("scrolPosition"));
			Db db = null;
			try {
				db = new Db();
				modelBI.simpanKehadiran(session, getParam("KETERANGAN_SAKSI_" + ID_BIKEHADIRAN),
						getParam("NOTA_PEGAWAI_SAKSI_" + ID_BIKEHADIRAN), ID_BIKEHADIRAN, ID_PERBICARAAN,
						getParam("NAMA_SAKSI_" + ID_BIKEHADIRAN), getParam("HUBUNGAN_SAKSI_" + ID_BIKEHADIRAN),
						getParam("PENGENALAN_SAKSI_" + ID_BIKEHADIRAN), getParam("STATUS_SAKSI_" + ID_BIKEHADIRAN),
						getParam("UMUR_SAKSI_" + ID_BIKEHADIRAN), "T", "", db);
				viewSaksi = modelBI.viewSaksi(session, ID_BIKEHADIRAN, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("viewSaksi", viewSaksi);
			skrin_name = "app/ppk/BicaraInteraktif/viewRowSaksi.jsp";
		}
		// arief add tidak hadir
		else if (command.equals("saveTidakHadir")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BITIDAKHADIR = getParam("ID_BITIDAKHADIR");
			this.context.put("ID_BTIDAKHADIR", ID_BITIDAKHADIR);
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("scrolPosition", getParam("scrolPosition"));
			Db db = null;
			try {
				db = new Db();
				modelBI.simpanKehadiran(session, ID_BITIDAKHADIR, ID_PERBICARAAN,
						getParam("NAMA_TIDAKHADIR_" + ID_BITIDAKHADIR),
						getParam("HUBUNGAN_TIDAKHADIR_" + ID_BITIDAKHADIR),
						getParam("PENGENALAN_TIDAKHADIR_" + ID_BITIDAKHADIR),
						getParam("STATUS_TIDAKHADIR_" + ID_BITIDAKHADIR),
						getParam("UMUR_TIDAKHADIR_" + ID_BITIDAKHADIR), "T", "", db);
				viewTidakHadir = modelBI.viewTidakHadir(session, ID_BITIDAKHADIR, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("viewTidakHadir", viewTidakHadir);
			skrin_name = "app/ppk/BicaraInteraktif/viewRowTidakHadir.jsp";
		}

		else if (command.equals("editTurutHadir") || command.equals("showKeteranganTurutHadir")
				|| command.equals("simpanTurutHadirKeterangan") || command.equals("tutupTurutHadirKeterangan")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
			this.context.put("ID_BIKEHADIRAN", ID_BIKEHADIRAN);

			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));

			String NAMA = "";
			String KETERANGAN = "";
			String NOTA_PEGAWAI = "";
			viewTuruthadir = null;
			Db db = null;
			try {
				db = new Db();

				if (!ID_BIKEHADIRAN.equals("")) {
					viewTuruthadir = modelBI.viewTuruthadir(session, ID_BIKEHADIRAN, db);
					NAMA = (String) viewTuruthadir.get("NAMA") == null ? "" : (String) viewTuruthadir.get("NAMA");
				}
				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", "", db));
				this.context.put("dataHubungan", modelBI.setDataList(session, "dataHubungan", "", "",
						"TBLPPKRUJSAUDARA", "ID_SAUDARA", "KOD", "KETERANGAN", db));

				if (command.equals("showKeteranganTurutHadir") || command.equals("simpanTurutHadirKeterangan")
						|| command.equals("tutupTurutHadirKeterangan")) {
					if (command.equals("simpanTurutHadirKeterangan") || command.equals("tutupTurutHadirKeterangan")) {
						modelBI.simpanKeterangan(ID_BIKEHADIRAN, getParam("KETERANGAN_TURUTHADIR_" + ID_BIKEHADIRAN),
								getParam("NOTA_PEGAWAI_TURUTHADIR_" + ID_BIKEHADIRAN), db);
					}
					Map viewKeterangan = modelBI.viewKeterangan(session, ID_BIKEHADIRAN, db);
					KETERANGAN = (String) viewKeterangan.get("KETERANGAN");
					NOTA_PEGAWAI = (String) viewKeterangan.get("NOTA_PEGAWAI");
				}
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("NAMA", NAMA);
			this.context.put("KETERANGAN", KETERANGAN);
			this.context.put("NOTA_PEGAWAI", NOTA_PEGAWAI);

			this.context.put("viewTuruthadir", viewTuruthadir);

			if (command.equals("showKeteranganTurutHadir") || command.equals("simpanTurutHadirKeterangan")) {
				skrin_name = "app/ppk/BicaraInteraktif/viewKeteranganTurutHadir.jsp";
			} else if (command.equals("tutupTurutHadirKeterangan")) {
				skrin_name = "app/ppk/BicaraInteraktif/viewKeteranganTurutHadirTutup.jsp";
			} else {
				skrin_name = "app/ppk/BicaraInteraktif/editTurutHadir.jsp";
			}
		}
		// arief add saksi
		else if (command.equals("editSaksi") || command.equals("showKeteranganSaksi")
				|| command.equals("simpanSaksiKeterangan") || command.equals("tutupSaksiKeterangan")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
			this.context.put("ID_BIKEHADIRAN", ID_BIKEHADIRAN);

			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));

			String NAMA = "";
			String KETERANGAN = "";
			String NOTA_PEGAWAI = "";
			viewSaksi = null;
			Db db = null;
			try {
				db = new Db();

				if (!ID_BIKEHADIRAN.equals("")) {
					viewSaksi = modelBI.viewSaksi(session, ID_BIKEHADIRAN, db);
					NAMA = (String) viewSaksi.get("NAMA") == null ? "" : (String) viewSaksi.get("NAMA");
				}
				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", "", db));
				this.context.put("dataHubungan", modelBI.setDataList(session, "dataHubungan", "", "",
						"TBLPPKRUJSAUDARA", "ID_SAUDARA", "KOD", "KETERANGAN", db));

				if (command.equals("showKeteranganSaksi") || command.equals("simpanSaksiKeterangan")
						|| command.equals("tutupSaksiKeterangan")) {
					if (command.equals("simpanSaksiKeterangan") || command.equals("tutupSaksiKeterangan")) {
						modelBI.simpanKeterangan(ID_BIKEHADIRAN, getParam("KETERANGAN_SAKSI_" + ID_BIKEHADIRAN),
								getParam("NOTA_PEGAWAI_SAKSI_" + ID_BIKEHADIRAN), db);
					}
					Map viewKeterangan = modelBI.viewKeterangan(session, ID_BIKEHADIRAN, db);
					KETERANGAN = (String) viewKeterangan.get("KETERANGAN");
					NOTA_PEGAWAI = (String) viewKeterangan.get("NOTA_PEGAWAI");
				}
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("NAMA", NAMA);
			this.context.put("KETERANGAN", KETERANGAN);
			this.context.put("NOTA_PEGAWAI", NOTA_PEGAWAI);

			this.context.put("viewSaksi", viewSaksi);

			if (command.equals("showKeteranganSaksi") || command.equals("simpanSaksiKeterangan")) {
				skrin_name = "app/ppk/BicaraInteraktif/viewKeteranganSaksi.jsp";
			} else if (command.equals("tutupSaksiKeterangan")) {
				skrin_name = "app/ppk/BicaraInteraktif/viewKeteranganSaksiTutup.jsp";
			} else {
				skrin_name = "app/ppk/BicaraInteraktif/editSaksi.jsp";
			}
		}
		// arief add tidak hadir
		else if (command.equals("editTidakHadir")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_BITIDAKHADIR = getParam("ID_BITIDAKHADIR");
			this.context.put("ID_BITIDAKHADIR", ID_BITIDAKHADIR);

			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));

			String NAMA = "";
			viewTidakHadir = null;
			Db db = null;
			try {
				db = new Db();

				if (!ID_BITIDAKHADIR.equals("")) {
					viewTidakHadir = modelBI.viewTidakHadir(session, ID_BITIDAKHADIR, db);
					NAMA = (String) viewTidakHadir.get("NAMA") == null ? "" : (String) viewTidakHadir.get("NAMA");
				}
				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", db));
				this.context.put("dataHubungan", modelBI.setDataList(session, "dataHubungan", "", "",
						"TBLPPKRUJSAUDARA", "ID_SAUDARA", "KOD", db));
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("NAMA", NAMA);

			this.context.put("viewTidakHadir", viewTidakHadir);
			skrin_name = "app/ppk/BicaraInteraktif/editTidakHadir.jsp";
		}

		else if (command.equals("show_turuthadir") || command.equals("tambah_turuthadir")
				|| command.equals("delete_turuthadir")) {
			this.context.put("div", "view_turuthadir");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			Db db = null;
			try {
				db = new Db();
				if (command.equals("tambah_turuthadir")) {
					modelBI.simpanKehadiran(session, getParam("KETERANGAN_TURUTHADIR_"),
							getParam("NOTA_PEGAWAI_TURUTHADIR_"), "", ID_PERBICARAAN, getParam("NAMA_TURUTHADIR_"),
							getParam("HUBUNGAN_TURUTHADIR_"), getParam("PENGENALAN_TURUTHADIR_"),
							getParam("STATUS_TURUTHADIR_"), getParam("UMUR_TURUTHADIR_"), "T", "", null);
				} else if (command.equals("delete_turuthadir")) {
					String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
					modelBI.deleteKehadiran(session, ID_BIKEHADIRAN, ID_PERBICARAAN, "T", db);
				}

				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", "", db));
				this.context.put("dataHubungan", modelBI.setDataList(session, "dataHubungan", "", "",
						"TBLPPKRUJSAUDARA", "ID_SAUDARA", "", "KETERANGAN", db));

				listTurutHadir = modelBI.listTurutHadir(session, ID_PERBICARAAN, null);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("listTurutHadir", listTurutHadir);
			this.context.put("scrolPosition", getParam("scrolPosition"));
			skrin_name = "app/ppk/BicaraInteraktif/viewTurutHadir.jsp";
		}
		// arief add saksi
		else if (command.equals("show_saksi") || command.equals("tambah_saksi") || command.equals("delete_saksi")) {
			this.context.put("div", "view_saksi");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			Db db = null;
			try {
				db = new Db();
				if (command.equals("tambah_saksi")) {
					modelBI.simpanKehadiran(session, getParam("KETERANGAN_SAKSI_"), getParam("NOTA_PEGAWAI_SAKSI_"), "",
							ID_PERBICARAAN, getParam("NAMA_SAKSI_"), getParam("HUBUNGAN_SAKSI_"),
							getParam("PENGENALAN_SAKSI_"), getParam("STATUS_SAKSI_"), getParam("UMUR_SAKSI_"), "T", "",
							null);
				} else if (command.equals("delete_saksi")) {
					String ID_BIKEHADIRAN = getParam("ID_BIKEHADIRAN");
					modelBI.deleteKehadiran(session, ID_BIKEHADIRAN, ID_PERBICARAAN, "T", db);
				}

				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", "", db));
				this.context.put("dataHubungan", modelBI.setDataList(session, "dataHubungan", "", "",
						"TBLPPKRUJSAUDARA", "ID_SAUDARA", "", "KETERANGAN", db));

				listSaksi = modelBI.listSaksi(session, ID_PERBICARAAN, null);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("listSaksi", listSaksi);
			this.context.put("scrolPosition", getParam("scrolPosition"));
			skrin_name = "app/ppk/BicaraInteraktif/viewSaksi.jsp";
		}
		// arief add tidak hadir
		else if (command.equals("show_tidakhadir") || command.equals("tambah_tidakhadir")
				|| command.equals("delete_tidakhadir")) {
			this.context.put("div", "view_tidakhadir");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			Db db = null;
			try {
				db = new Db();
				if (command.equals("tambah_tidakhadir")) {
					modelBI.simpanKehadiran(session, "", ID_PERBICARAAN, getParam("NAMA_TIDAKHADIR_"),
							getParam("HUBUNGAN_TIDAKHADIR_"), getParam("PENGENALAN_TIDAKHADIR_"),
							getParam("STATUS_TIDAKHADIR_"), getParam("UMUR_TIDAKHADIR_"), "T", "", null);
				} else if (command.equals("delete_tidakhadir")) {
					String ID_BITIDAKHADIR = getParam("ID_BITIDAKHADIR");
					modelBI.deleteTidakHadir(session, ID_BITIDAKHADIR, ID_PERBICARAAN, "T", db);
				}

				this.context.put("dataStatusOB",
						modelBI.setDataList(session, "dataStatusOB", "", "", "STATUS_OB", "", "", "", db));
				this.context.put("dataHubungan",
						modelBI.setDataList(session, "dataHubungan", "", "", "TBLPPKRUJSAUDARA", "ID_SAUDARA", "", db));

				listTidakHadir = modelBI.listTidakHadir(session, ID_PERBICARAAN, null);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("listTidakHadir", listTidakHadir);
			this.context.put("scrolPosition", getParam("scrolPosition"));
			skrin_name = "app/ppk/BicaraInteraktif/viewTidakHadir.jsp";
		}

		else if (command.equals("simpan_kehadiran")) {
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			this.context.put("div", "view_kehadiran");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("scrolPosition", getParam("scrolPosition"));
			Db db = null;
			try {
				db = new Db();
				modelBI.deleteKehadiran(session, "", ID_PERBICARAAN, "OB", db);
				modelBI.deleteTidakHadir(session, "", ID_PERBICARAAN, "OB", db);// arief add
				String[] checkKehadiran = request.getParameterValues("checkKehadiran");
				if (checkKehadiran != null) {
					for (String ck : checkKehadiran) {
						myLogger.info("::: CHECK KEHADIRAN ::: " + ck);
						myLogger.info("::: CHECK KETERANGAN_ ::: " + getParam("KETERANGAN_" + ck));
						myLogger.info("::: CHECK NOTA_PEGAWAI_ ::: " + getParam("NOTA_PEGAWAI_" + ck));
						modelBI.simpanKehadiran(session, getParam("KETERANGAN_" + ck), getParam("NOTA_PEGAWAI_" + ck),
								"", ID_PERBICARAAN, getParam("NAMA_" + ck), getParam("HUBUNGAN_" + ck),
								getParam("PENGENALAN_" + ck), getParam("STATUS_" + ck), getParam("UMUR_" + ck), "OB",
								ck, db);
					}
				}
				listKehadiran = modelBI.listKehadiran(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN, ID_PERBICARAAN,
						ID_PEMOHON, db);
				listTidakHadir = modelBI.listTidakHadir(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN, ID_PERBICARAAN,
						ID_PEMOHON, db);// arief add
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("listKehadiran", listKehadiran);
			this.context.put("listTidakHadir", listTidakHadir);
			skrin_name = "app/ppk/BicaraInteraktif/viewKehadiran.jsp";
		}

		// arief add OPEN
		/**
		 *
		 *
		 * else if(command.equals("simpan_tidakHadir")) { String ID_PERMOHONANSIMATI =
		 * getParam("ID_PERMOHONANSIMATI"); this.context.put("ID_PERMOHONANSIMATI",
		 * ID_PERMOHONANSIMATI); this.context.put("div", "view_kehadiran"); String
		 * ID_PERBICARAAN = getParam("ID_PERBICARAAN");
		 * this.context.put("ID_PERBICARAAN", ID_PERBICARAAN); String ID_PEMOHON =
		 * getParam("ID_PEMOHON"); this.context.put("ID_PEMOHON", ID_PEMOHON); String
		 * ID_PERMOHONAN = getParam("ID_PERMOHONAN"); this.context.put("ID_PERMOHONAN",
		 * ID_PERMOHONAN); this.context.put("scrolPosition", getParam("scrolPosition"));
		 * Db db = null; try { db = new Db();
		 * modelBI.deleteTidakHadir(session,"",ID_PERBICARAAN,"OB",db); String[]
		 * checkTidakHadir = request.getParameterValues("checkTidakHadir");
		 * if(checkTidakHadir != null) { for (String ck : checkTidakHadir) {
		 * myLogger.info("::: CHECK TIDAK HADIR ::: "+ck);
		 * modelBI.simpanTidakHadir(session,"", ID_PERBICARAAN, getParam("NAMA_"+ck),
		 * getParam("HUBUNGAN_"+ck), getParam("PENGENALAN_"+ck), getParam("STATUS_"+ck),
		 * getParam("UMUR_"+ck), "OB", ck, db); } } listTidakHadir =
		 * modelBI.listTidakHadir(session,ID_PERMOHONANSIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PEMOHON,db);
		 * } finally { if (db != null) db.close(); } this.context.put("listTidakHadir",
		 * listTidakHadir); skrin_name = "app/ppk/BicaraInteraktif/viewTidakHadir.jsp";
		 * }
		 **/
		// arief add CLOSE

		else if (command.equals("showSenarai") || command.equals("refreshList") || command.equals("deleteHistory")
		// || command.equals("deleteMaklumat")
		) {
			String tajukList = getParam("tajukList");
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String current_previous = getParam("current_previous");
			this.context.put("current_previous", current_previous);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_OBPERMOHONAN = getParam("ID_OBPERMOHONAN");
			this.context.put("ID_OBPERMOHONAN", ID_OBPERMOHONAN);
			String ID_PENGHUTANG = getParam("ID_PENGHUTANG");
			this.context.put("ID_PENGHUTANG", ID_PENGHUTANG);
			String ID_PEGUAM = getParam("ID_PEGUAM");
			this.context.put("ID_PEGUAM", ID_PEGUAM);
			String ID_HTAPERMOHONAN = getParam("ID_HTAPERMOHONAN");
			this.context.put("ID_HTAPERMOHONAN", ID_HTAPERMOHONAN);
			String ID_HAPERMOHONAN = getParam("ID_HAPERMOHONAN");
			this.context.put("ID_HAPERMOHONAN", ID_HAPERMOHONAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_SEJARAHBIMAIN = getParam("ID_SEJARAHBIMAIN");
			this.context.put("ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));

			String htmlSkrinMaklumat = "";
			Db db = null;
			try {
				db = new Db();

				if (command.equals("deleteHistory")) {
					myLogger.info("deleteHistory >>> ID_SEJARAHBIMAIN :::::::::::::::: " + ID_SEJARAHBIMAIN);
					modelBI.deleteHISTORY(command, ID_SEJARAHBIMAIN, "", "", "", "", db);
				}
				/*
				 * else if(command.equals("deleteMaklumat")) { String id = "";
				 * if(skrinName.equals("pemohon") || skrinName.equals("simati")) { id =
				 * ID_PERMOHONAN; } else if(skrinName.equals("waris") || skrinName.equals("ob")
				 * || skrinName.equals("saksi") || skrinName.equals("pemiutang")) { id =
				 * ID_OBPERMOHONAN; } else if(skrinName.equals("penghutang")) { id =
				 * ID_PENGHUTANG; } else if(skrinName.equals("peguam")) { id = ID_PEGUAM; } else
				 * if(skrinName.equals("htaah") || skrinName.equals("htaahx")) { id =
				 * ID_HTAPERMOHONAN; } else if(skrinName.equals("ha")) { id = ID_HAPERMOHONAN; }
				 *
				 * Map mapDataAsal =
				 * modelBI.getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,
				 * ID_PERMOHONANSIMATI, FIELD_PK, id, NAMA_TABLE, db);
				 * myLogger.info("ID_SEJARAHBIMAIN : "+ID_SEJARAHBIMAIN+" skrinName : "
				 * +skrinName+" id : "+id+" FIELD_PK : "
				 * +FIELD_PK+" getParam(skrinName+FIELD_PK) : "+getParam(skrinName+FIELD_PK)
				 * +" NAMA_TABLE : "+NAMA_TABLE+" ID_PERBICARAAN : "+ID_PERBICARAAN);
				 * savePerubahan(session,command,ID_SEJARAHBIMAIN,mapDataAsal,skrinName,
				 * ID_PERMOHONANSIMATI,id,FIELD_PK,id,formNameAjax,NAMA_TABLE,"DELETE",
				 * ID_PERBICARAAN,"",db); }
				 */

				htmlSkrinMaklumat = modelBI.htmlList(session, ID_PEMOHON, ID_SIMATI, ID_PERBICARAAN, ID_PERMOHONAN,
						tajukList, skrinName, current_previous, command, ID_PERMOHONANSIMATI, formNameAjax, db);

				if (current_previous.equals("current") && !skrinName.equals("peguam")
				// && !command.equals("refreshList")
				) {
					htmlSkrinMaklumat += "<script>$jquery(document).ready(function (){" + " doDivAjaxCall"
							+ formNameAjax + "('senarai_" + skrinName + "previous','showSenarai','NAMA_TABLE="
							+ NAMA_TABLE + "&FIELD_PK=" + FIELD_PK + "&ID_PERBICARAAN=" + ID_PERBICARAAN
							+ "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI
							+ "&skrinName=" + skrinName + "&ID_PEMOHON=" + ID_PEMOHON
							+ "&current_previous=previous&scrolPosition='+getPageLocation());" + " });</script>";
				}
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("htmlSkrinMaklumat", htmlSkrinMaklumat);
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
		}

		else if (command.equals("showMaklumatketeranganhadir") || command.equals("showMaklumatketeranganhadirPrint")) {

			this.context.put("tajukLaporan", "LAMPIRAN KETERANGAN PERBICARAAN");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String skrinName = "perubahan";
			this.context.put("skrinName", skrinName);
			this.context.put("scrolPosition", getParam("scrolPosition"));

			String ID_PERMOHONANSIMATI = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			String flagPrint = "N";
			String htmlSkrinMaklumat = "";
			Db db = null;
			try {
				db = new Db();
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String) mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String) mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String) mainID.get("ID_PEMOHON");
				ID_SIMATI = (String) mainID.get("ID_SIMATI");
				if (command.equals("showMaklumatketeranganhadirPrint")) {
					flagPrint = "Y";
				}

				if (flagPrint.equals("Y")) {
					this.context.put("viewPerbicaraan",
							modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db));
				}
				htmlSkrinMaklumat = modelBI.htmlListKeterangan(session, formName, ID_SIMATI, ID_PERMOHONANSIMATI,
						ID_PERBICARAAN, ID_PERMOHONAN, ID_PEMOHON, flagPrint, fontSize, db);

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			this.context.put("flagPrint", flagPrint);
			this.context.put("htmlSkrinMaklumat", htmlSkrinMaklumat);
			this.context.put("div", getParam("div"));
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
		} else if (command.equals("showMaklumatperubahan") || command.equals("showMaklumatperubahanPrint")) {
			this.context.put("tajukLaporan", "LAMPIRAN PERUBAHAN MAKLUMAT");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String skrinName = "perubahan";
			this.context.put("skrinName", skrinName);
			this.context.put("scrolPosition", getParam("scrolPosition"));
			String ID_PERMOHONANSIMATI = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			String flagPrint = "N";
			String htmlSkrinMaklumat = "";
			Db db = null;
			try {
				db = new Db();
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String) mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String) mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String) mainID.get("ID_PEMOHON");
				ID_SIMATI = (String) mainID.get("ID_SIMATI");
				if (command.equals("showMaklumatperubahanPrint")) {
					flagPrint = "Y";
				}

				if (flagPrint.equals("Y")) {
					this.context.put("viewPerbicaraan",
							modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db));
				}
				htmlSkrinMaklumat = modelBI.htmlListPerubahan(session, formName, ID_SIMATI, ID_PERMOHONANSIMATI,
						ID_PERBICARAAN, ID_PERMOHONAN, ID_PEMOHON, flagPrint, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			this.context.put("flagPrint", flagPrint);
			this.context.put("htmlSkrinMaklumat", htmlSkrinMaklumat);
			this.context.put("div", getParam("div"));
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
		}
		/*
		 * String cacheID = "listPermohonanTukarPegawai"+USER_ID_SYSTEM; CachedObject
		 * get_co_listPermohonanTukarPegawai =
		 * (CachedObject)CacheManager.getCache(cacheID);
		 * myLogger.info("action : "+action+" get_co_listPerbicaraan : "
		 * +get_co_listPermohonanTukarPegawai); String htmlSkrin = ""; String flagCari =
		 * ""; String paramsButton = "";
		 *
		 * if(command.equals("cariPermohonanTukarPegawai")) { flagCari = "Y"; }
		 *
		 * Db db = null; try { db = new Db(); if( action.equals("") //||
		 * command.equals("cariListPerbicaraan") || //comment dlu sementara
		 * get_co_listPermohonanTukarPegawai == null) { listPermohonanTukarPegawai =
		 * listPermohonanTukarPegawai(session,USER_ID_SYSTEM,id_jawatan_login,
		 * id_negeri_login,flagCari,"","","",null); CachedObject
		 * set_co_listPermohonanTukarPegawai = new
		 * CachedObject(listPermohonanTukarPegawai, cacheID, 0);
		 * CacheManager.putCache(set_co_listPermohonanTukarPegawai); } else if
		 * (get_co_listPermohonanTukarPegawai != null) { listPermohonanTukarPegawai =
		 * (List)get_co_listPermohonanTukarPegawai.object; } else {
		 * CacheManager.removeCache(cacheID); } htmlSkrin =
		 * setupSkrinCarianTukarPegawai(session,"carianTukarPegawai",command,formName,
		 * "edit",paramsButton,db); } finally { if (db != null) db.close(); }
		 *
		 * myLogger.info("SIZE : "+listPermohonanTukarPegawai.size());
		 * this.context.put("div", "listPermohonanTukarPegawai");
		 * this.context.put("htmlCarianTukarPegawai", htmlSkrin);
		 * this.context.put("flagOpenTPK", getParam("flagOpenTPK"));
		 * setupPageMainList(session, action,
		 * listPermohonanTukarPegawai,"listPermohonanTukarPegawai",command); skrin_name
		 * = "app/ppk/BicaraInteraktif/listPermohonanTukarPegawai.jsp";
		 *
		 *
		 */
		else if (command.equals("showMaklumatHistoryJana")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			if (ID_PERBICARAAN.equals("")) {
				ID_PERBICARAAN = getParam("ID_PERBICARAAN_TEMP");
			}

			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String skrinName = "perubahan";
			this.context.put("skrinName", skrinName);
			this.context.put("scrolPosition", getParam("scrolPosition"));
			String ID_PERMOHONANSIMATI = "";
			String ID_FAIL = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			List listHistoryJana = null;
			Db db = null;
			try {
				db = new Db();
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String) mainID.get("ID_PERMOHONANSIMATI");
				ID_FAIL = (String) mainID.get("ID_FAIL");
				ID_PERMOHONAN = (String) mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String) mainID.get("ID_PEMOHON");
				ID_SIMATI = (String) mainID.get("ID_SIMATI");

				listHistoryJana = modelBI.listHistoryjana(session, ID_FAIL, ID_PERBICARAAN, ID_SIMATI, db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_FAIL", ID_FAIL);
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			this.context.put("listHistoryJana", listHistoryJana);
			this.context.put("div", "view_historyJana");
			setupPageMainList(session, action, listHistoryJana, "listHistoryJana", command);
			skrin_name = "app/ppk/BicaraInteraktif/listHistoryJana.jsp";
		} else if (command.equals("viewSuplimentPerintah")) {

			// get data TBLPPKPERBICARAAN
			Hashtable h = FrmPrmhnnSek8KptsanBicaraData.setInfoBicaraList(idpermohonan);
			this.context.put("dataPerbicaraan", h);

			FrmPrmhnnSek8KptsanBicaraData.setInfoPerintahList(idpermohonan);
			getrecord_perintah = FrmPrmhnnSek8KptsanBicaraData.getDataPerintahViewList();
			String idUnitPsk = "";
			if (getrecord_perintah.size() != 0) {
				Hashtable d = (Hashtable) getrecord_perintah.get(0);
				String flag_jenis_keputusan = (String) d.get("flag_jenis_keputusan");
				idUnitPsk = d.get("id_unitpsk").toString();
				if (idUnitPsk != "") {
					context.put("selectViewPegawai", HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,
							"EDITsocPegawaiPengendali", Utils.parseLong(idUnitPsk), "disabled"));
				} else {
					context.put("selectViewPegawai", HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,
							"EDITsocPegawaiPengendali", null, "disabled"));
				}

				if (d.get("flag_jenis_keputusan").equals("0")) {
					setValueFlagJenisKeputusan("checked", "", "");
				}
				context.put("TEMPcheckedSelesai", checkedSelesai);
				context.put("TEMPcheckedTangguh", checkedTangguh);
				context.put("TEMPcheckedBatal", checkedBatal);

			} else {
				setValueFlagJenisKeputusan("checked", "", "");
				context.put("TEMPcheckedSelesai", checkedSelesai);
				context.put("selectViewPegawai", HTML.SelectPegawaiPengendaliByNegeri(idNegeriMhn,
						"EDITsocPegawaiPengendali", null, "disabled"));
			}
			this.context.put("dataPerintah", getrecord_perintah);

			// call flag
			context.put("mode", "view");
			context.put("flag", "selesai");
			context.put("button", "");
			context.put("tarikh", "perintah");
			context.put("listSemak", list);

			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_PERINTAH = getParam("ID_PERINTAH");
			this.context.put("ID_PERINTAH", ID_PERINTAH);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			String current_previous = getParam("current_previous");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));
			String FLAG_KEPUTUSAN = getParam("FLAG_KEPUTUSAN");
			String setupSkrin = "";
			String mode = getParam("mode");
			myLogger.info("MODE ::::::::::::: " + mode);
			Db db = null;
			try {
				db = new Db();
				String standardParam = "FLAG_KEPUTUSAN=" + FLAG_KEPUTUSAN + "&ID_PEMOHON=" + ID_PEMOHON + "&ID_SIMATI="
						+ ID_SIMATI + "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&ID_PERBICARAAN="
						+ ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&NAMA_TABLE=" + NAMA_TABLE
						+ "&FIELD_PK=" + FIELD_PK + "&skrinName=" + skrinName
						+ "&aktiviti=&current_previous=&jenis_transaction=";
				Map setupKeputusan = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKPERINTAH", db);

				setupSkrin = setupSkrinJenisKeputusan(session, FLAG_KEPUTUSAN, "", current_previous, "", ID_SIMATI, "",
						null, setupKeputusan, ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command,
						ID_PERMOHONAN, formNameAjax, "view_" + skrinName, NAMA_TABLE, FIELD_PK, mode, standardParam,
						"Y", ID_PEMOHON, db);
				this.context.put("htmlSkrinMaklumat", setupSkrin);

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("mode", mode);

			skrin_name = "app/ppk/BicaraInteraktif/viewKeputusanJenisPerintah.jsp";
		} else if (command.equals("viewSuplimentTangguh") || command.equals("viewSuplimentBatal")) {
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_PERINTAH = getParam("ID_PERINTAH");
			this.context.put("ID_PERINTAH", ID_PERINTAH);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			String current_previous = getParam("current_previous");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));
			String FLAG_TANGGUH = getParam("FLAG_TANGGUH");
			String FLAG_BATAL = getParam("FLAG_BATAL");
			String setupSkrin = "";
			String mode = getParam("mode");
			myLogger.info("MODE ::::::::::::: " + mode);
			myLogger.info("viewSuplimentTangguh FLAG_TANGGUH ::::::::::::: " + FLAG_TANGGUH);
			Db db = null;
			try {
				db = new Db();
				String standardParam = "ID_PEMOHON=" + ID_PEMOHON + "&ID_SIMATI=" + ID_SIMATI + "&ID_PERMOHONANSIMATI="
						+ ID_PERMOHONANSIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
						+ "&NAMA_TABLE=" + NAMA_TABLE + "&FIELD_PK=" + FIELD_PK + "&skrinName=" + skrinName
						+ "&aktiviti=&current_previous=&jenis_transaction=";
				if (command.equals("viewSuplimentTangguh")) {
					standardParam += "&FLAG_TANGGUH=" + FLAG_TANGGUH;
					if (FLAG_TANGGUH.equals("5"))// BORANGJ
					{
						Map setupTangguhBorangJ = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKBORANGJ", db);
						setupSkrin = setupSkrinTangguhBorangJ(session, FLAG_TANGGUH, "", current_previous, "",
								ID_SIMATI, "", null, setupTangguhBorangJ, ID_PERMOHONAN, ID_PERMOHONANSIMATI,
								ID_PERBICARAAN, skrinName, command, ID_PERMOHONAN, formNameAjax, "view_" + skrinName,
								NAMA_TABLE, FIELD_PK, mode, standardParam, "Y", ID_PEMOHON, db);
					} else if (FLAG_TANGGUH.equals("6"))// KOLETERAL
					{
						Map setupTangguhKoleteral = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN,
								skrinName, ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKKOLATERALMST", db);
						setupSkrin = setupSkrinTangguhKoleteral(session, FLAG_TANGGUH, "", current_previous, "",
								ID_SIMATI, "", null, setupTangguhKoleteral, ID_PERMOHONAN, ID_PERMOHONANSIMATI,
								ID_PERBICARAAN, skrinName, command, ID_PERMOHONAN, formNameAjax, "view_" + skrinName,
								NAMA_TABLE, FIELD_PK, mode, standardParam, "Y", ID_PEMOHON, db);
					}
				} else if (command.equals("viewSuplimentBatal")) {
					standardParam += "&FLAG_BATAL=" + FLAG_BATAL;
					if (FLAG_BATAL.equals("1"))// MAHKAMAH TINGGI WASIAT
					{
						Map setupBatal = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKPERINTAH", db);
						setupSkrin = setupSkrinBatalWasiat(session, FLAG_BATAL, "", current_previous, "", ID_SIMATI, "",
								null, setupBatal, ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
								command, ID_PERMOHONAN, formNameAjax, "view_" + skrinName, NAMA_TABLE, FIELD_PK, mode,
								standardParam, "Y", ID_PEMOHON, db);
					}
				}
				this.context.put("htmlSkrinMaklumat", setupSkrin);

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("mode", mode);

			skrin_name = "app/ppk/BicaraInteraktif/viewKeputusanTangguhBatal.jsp";
		}

		else if (command.equals("showKeputusan") || command.equals("resetKeputusanPerintah")
				|| command.equals("simpanKeputusanPerintah") || command.equals("editKeputusanPerintah")
		// || command.equals("janaCatatanPerintah")
		) {
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_PERINTAH = getParam(skrinName + "ID_PERINTAH");
			this.context.put("ID_PERINTAH", ID_PERINTAH);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			String current_previous = getParam("current_previous");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			String ID_INTROPERINTAH = getParam(skrinName + "ID_INTROPERINTAH");
			String htmlContentJana = "";
			String ID_FAIL = "";
			if (command.equals("showKeputusan")) {
				this.context.put("div", "view_keputusan");
			}
			String FLAG_JENIS_KEPUTUSAN = getParam(skrinName + "FLAG_JENIS_KEPUTUSAN");
			String setupSkrin = "";
			String mode = getParam("mode");
			Db db = null;
			String errorMesej = "";
			Connection conn = null;
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);

				if (command.equals("simpanKeputusanPerintah")
				// || command.equals("janaCatatanPerintah")
				) {
					// save perintah
					modelBI.simpanIntroPerintah(session, ID_PERINTAH, ID_INTROPERINTAH, db);
					Map setupKeputusanBeforeSave = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN,
							skrinName, ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKPERINTAH", db);
					savePerintah(session, setupKeputusanBeforeSave, ID_SIMATI, ID_PERMOHONAN, FLAG_JENIS_KEPUTUSAN,
							ID_PERINTAH, ID_PERMOHONANSIMATI, formName, ID_PERBICARAAN, skrinName, db);
					/*
					 * if(command.equals("janaCatatanPerintah")) { Map mainID =
					 * modelBI.mainID(session, ID_PERBICARAAN, db); ID_FAIL =
					 * (String)mainID.get("ID_FAIL"); htmlContentJana =
					 * modelBI.janaContentCatatanPerintah(session,ID_FAIL,formName,ID_SIMATI,
					 * ID_PERBICARAAN,ID_PERMOHONAN,ID_PERMOHONANSIMATI,ID_PEMOHON,ID_PERINTAH,db);
					 * saveContentCatatanBI(session,ID_PERINTAH, htmlContentJana,db);
					 * this.context.put("htmlContentJana",htmlContentJana); }
					 */
					this.context.put("viewPerbicaraan",
							modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db));
				}

				Map setupKeputusan = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKPERINTAH", db);
				if (command.equals("showKeputusan") || command.equals("editKeputusanPerintah")
						|| command.equals("resetKeputusanPerintah") || command.equals("simpanKeputusanPerintah")) {
					if (command.equals("editKeputusanPerintah") || command.equals("resetKeputusanPerintah")
							|| setupKeputusan == null) {
						mode = "edit";
					} else {
						mode = "view";
					}

					String standardParam = "mode=" + mode + "&ID_PEMOHON=" + ID_PEMOHON + "&ID_SIMATI=" + ID_SIMATI
							+ "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN
							+ "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&NAMA_TABLE=" + NAMA_TABLE + "&FIELD_PK=" + FIELD_PK
							+ "&skrinName=" + skrinName;

					myLogger.info("MODE KEPUTUSAN : " + mode);
					setupSkrin = setupSkrinKeputusan(session, "", current_previous, "", ID_SIMATI, "", null,
							setupKeputusan, ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command,
							ID_PERMOHONAN, formNameAjax, "view_" + skrinName, NAMA_TABLE, FIELD_PK, mode, standardParam,
							"Y", db);
					this.context.put("htmlSkrinMaklumat", setupSkrin);
				}

				if (command.equals("simpanKeputusanPerintah")
				// || command.equals("janaCatatanPerintah")
				) {
					listKronologiStatus = modelBI.listKronologiStatus(session, ID_PERMOHONAN, db);
					this.context.put("listKronologiStatus", listKronologiStatus);
				}

				conn.commit();
			} catch (SQLException se) {
				try {
					conn.rollback();
					myLogger.info("ROLLBACK SAVE PERINTAH MAIN");
				} catch (SQLException se2) {
					// errorMesej += "RALAT ROLLBACK :" + se2.getMessage()+"<br><br>SILA HUBUNGI
					// ETAPPSUPPORT ATAU MEMBUAT ADUAN MELALUI PLA. MAAF DIATAS KESULITAN.";
					// throw new Exception("ROLLBACK ERROR :" + se2.getMessage());
				}
				// se.printStackTrace();
				errorMesej = "RALAT ROLLBACK :" + se.getMessage().toUpperCase()
						+ "<br>SILA HUBUNGI ETAPPSUPPORT ATAU MEMBUAT ADUAN MELALUI PLA. MAAF DIATAS KESULITAN.";

				// throw new Exception("RALAT : " + se.getMessage());
			} finally {
				if (conn != null)
					conn.close();
				if (db != null)
					db.close();
			}

			this.context.put("mode", mode);

			if (command.equals("simpanKeputusanPerintah")
			// || command.equals("janaCatatanPerintah")
			) {
				skrin_name = "app/ppk/BicaraInteraktif/viewPerbicaraan.jsp";
			} else {
				skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
			}
			myLogger.info("errorMesej >>>>>>>>>>> " + errorMesej);
			if (!errorMesej.equals("")) {
				// send error mesej
				context.put("errorMesej", errorMesej);
				// jika ada error pada SQL, skrin akan di Divert pada skrin error.jsp yang umum
				// supaya paparan error lebih teratur
				skrin_name = "app/RazTemplate/error.jsp";
			}

		} else if (command.equals("showMaklumat") || command.equals("editMaklumat") || command.equals("resetMaklumat")
				|| command.equals("simpanMaklumat") || command.equals("hapusPerubahanMaklumat")
				|| command.equals("deleteMaklumat")) {
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_OBPERMOHONAN = getParam("ID_OBPERMOHONAN");
			this.context.put("ID_OBPERMOHONAN", ID_OBPERMOHONAN);
			String ID_PENGHUTANG = getParam("ID_PENGHUTANG");
			this.context.put("ID_PENGHUTANG", ID_PENGHUTANG);
			String ID_PEGUAM = getParam("ID_PEGUAM");
			this.context.put("ID_PEGUAM", ID_PEGUAM);
			String ID_HTAPERMOHONAN = getParam("ID_HTAPERMOHONAN");
			this.context.put("ID_HTAPERMOHONAN", ID_HTAPERMOHONAN);
			String ID_HAPERMOHONAN = getParam("ID_HAPERMOHONAN");
			this.context.put("ID_HAPERMOHONAN", ID_HAPERMOHONAN);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_SEJARAHBIMAIN = getParam("ID_SEJARAHBIMAIN");
			this.context.put("ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			String current_previous = getParam("current_previous");
			String flag_gantiPemohon = getParam("flag_gantiPemohon");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));
			String id = "";
			if (skrinName.equals("pemohon") || skrinName.equals("simati")) {
				id = ID_PERMOHONAN;
			} else if (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi")
					|| skrinName.equals("pemiutang")) {
				id = ID_OBPERMOHONAN;
			} else if (skrinName.equals("penghutang")) {
				id = ID_PENGHUTANG;
			} else if (skrinName.equals("peguam")) {
				id = ID_PEGUAM;
			} else if (skrinName.equals("htaah") || skrinName.equals("htaahx")) {
				id = ID_HTAPERMOHONAN;
			} else if (skrinName.equals("ha")) {
				id = ID_HAPERMOHONAN;
			}

			String mode = "view";
			String aktiviti = "";
			boolean saveTransaction = false;
			String setupSkrin = NAMA_TABLE;
			Db db = null;
			boolean reloadListAjaxCall = false;
			try {
				db = new Db();
				if (command.equals("editMaklumat") || command.equals("resetMaklumat")) {
					mode = "edit";
				} else if (command.equals("simpanMaklumat")) {
					saveTransaction = true;
					aktiviti = "UPDATE";
				} else if (command.equals("hapusPerubahanMaklumat")) {
					// delete perubahan
					modelBI.deleteHISTORY("deleteHistory", "", NAMA_TABLE, FIELD_PK, getParam(skrinName + FIELD_PK),
							ID_PERBICARAAN, db);
				} else if (command.equals("deleteMaklumat")) {
					Map mapDataAsal = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, FIELD_PK, id, NAMA_TABLE, db);
					myLogger.info("ID_SEJARAHBIMAIN : " + ID_SEJARAHBIMAIN + " skrinName : " + skrinName + " id : " + id
							+ " FIELD_PK : " + FIELD_PK + " getParam(skrinName+FIELD_PK) : "
							+ getParam(skrinName + FIELD_PK) + " NAMA_TABLE : " + NAMA_TABLE + " ID_PERBICARAAN : "
							+ ID_PERBICARAAN);
					savePerubahan(session, command, ID_SEJARAHBIMAIN, mapDataAsal, skrinName, ID_PERMOHONANSIMATI, id,
							FIELD_PK, id, formNameAjax, NAMA_TABLE, "DELETE", ID_PERBICARAAN, "", ID_SIMATI,
							ID_PERMOHONAN, db);
				}

				Map setupSkrinHistory = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN, "TBLPPKSEJARAHBIMAIN", db);

				if (saveTransaction == true) {
					myLogger.info("CHECK DATA ASAL skrinName : " + skrinName + " FIELD_PK : " + FIELD_PK + " NAMA_TABLE"
							+ NAMA_TABLE + " VALUE : " + getParam(skrinName + FIELD_PK));
					Map mapDataAsal = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, FIELD_PK, id, NAMA_TABLE, db);
					myLogger.info("CHECK DATA ASAL : " + mapDataAsal);

					if (aktiviti.equals("UPDATE")) {
						if (mapDataAsal == null) {
							if (setupSkrinHistory != null) {
								aktiviti = (String) setupSkrinHistory.get("JENIS_AKTIVITI");
							} else {
								aktiviti = "INSERT";
								reloadListAjaxCall = true;
							}
						}
					}

					if (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi")
							|| skrinName.equals("pemiutang")) {
						// jika nak ganti pemohon lain
						if (flag_gantiPemohon.equals("Y")) {
							// checkDluOB yg lain, kena reset
							Map checkCurrentPemohonPadaOB = modelBI.checkCurrentPemohonPadaOB(session,
									ID_PERMOHONANSIMATI, ID_PERBICARAAN, db);
							String ID_SEJARAHBIMAIN_MAP = (String) checkCurrentPemohonPadaOB.get("ID_SEJARAHBIMAIN");
							String ID_OBPERMOHONAN_MAP = (String) checkCurrentPemohonPadaOB.get("ID_OBPERMOHONAN");
							String ID_OB_MAP = (String) checkCurrentPemohonPadaOB.get("ID_OB");
							String ID_PEMOHON_MAP = (String) checkCurrentPemohonPadaOB.get("ID_PEMOHON");
							String ID_PEMOHON_SEBELUM_MAP = (String) checkCurrentPemohonPadaOB
									.get("ID_PEMOHON_SEBELUM");
							String ID_SIMATI_MAP = (String) checkCurrentPemohonPadaOB.get("ID_SIMATI");
							String ID_PERMOHONANSIMATI_MAP = (String) checkCurrentPemohonPadaOB
									.get("ID_PERMOHONANSIMATI");
							String JENIS_AKTIVITI_MAP = (String) checkCurrentPemohonPadaOB.get("JENIS_AKTIVITI");
							String ID_TARAFKPTG_MAP = (String) checkCurrentPemohonPadaOB.get("ID_TARAFKPTG");
							String SKRIN_NAME_MAP = (String) checkCurrentPemohonPadaOB.get("SKRIN_NAME");
							myLogger.info("---------------------------------------------------");
							myLogger.info("ID_SEJARAHBIMAIN_MAP : " + ID_SEJARAHBIMAIN_MAP);
							myLogger.info("ID_OBPERMOHONAN_MAP : " + ID_OBPERMOHONAN_MAP);
							myLogger.info("ID_OB_MAP : " + ID_OB_MAP);
							myLogger.info("ID_PEMOHON_MAP : " + ID_PEMOHON_MAP);
							myLogger.info("ID_PEMOHON_SEBELUM_MAP : " + ID_PEMOHON_SEBELUM_MAP);
							myLogger.info("ID_SIMATI_MAP : " + ID_SIMATI_MAP);
							myLogger.info("ID_PERMOHONANSIMATI_MAP : " + ID_PERMOHONANSIMATI_MAP);
							myLogger.info("JENIS_AKTIVITI_MAP : " + JENIS_AKTIVITI_MAP);
							myLogger.info("ID_TARAFKPTG_MAP : " + ID_TARAFKPTG_MAP);
							myLogger.info("SKRIN_NAME_MAP : " + SKRIN_NAME_MAP);
							myLogger.info("---------------------------------------------------");
							if (!ID_OBPERMOHONAN_MAP.equals("")) {
								if (!ID_SEJARAHBIMAIN_MAP.equals("")) {
									// check balik
									List listColumnByTable = modelBI.listColumnByTable(session, "waris",
											"TBLPPKOBPERMOHONAN", db);
									int countAdaFieldLain = 0;
									if (listColumnByTable.size() != 0) {
										for (int i = 0; i < listColumnByTable.size(); i++) {
											Map map_column_name = (Map) listColumnByTable.get(i);
											String column_name = (String) map_column_name.get("COLUMN_NAME");
											int checkFieldWujud = modelBI.checkFieldWujud(session, ID_SEJARAHBIMAIN_MAP,
													column_name, db);
											if (checkFieldWujud > 0 && !column_name.equals("ID_OBPERMOHONAN")
													&& !column_name.equals("ID_OB")
													&& !column_name.equals("ID_PERMOHONANSIMATI")
													&& !column_name.equals("ID_SIMATI")
													&& !column_name.equals("ID_PEMOHON")) {
												countAdaFieldLain++;
											}
										}
									}
									myLogger.info(":::::::::::::::: countAdaFieldLain : " + countAdaFieldLain);
									// sudah ada rekod history
									if (countAdaFieldLain > 0) {
										modelBI.updateFlagPemohonByON(session, ID_SEJARAHBIMAIN_MAP, "ID_PEMOHON",
												ID_PEMOHON_SEBELUM_MAP, "YA", "", "TIDAK", db);
									} else {
										modelBI.deleteHISTORY("deleteHistory", ID_SEJARAHBIMAIN_MAP, "", "", "",
												ID_PERBICARAAN, db);
										// delete
									}
								} else if (ID_SEJARAHBIMAIN_MAP.equals("")) {
									// belum ada rekod history
									// so kena add la
									String skrinNameOB = "";
									if (ID_TARAFKPTG_MAP.equals("1")) {
										skrinNameOB = "waris";
									} else if (!ID_TARAFKPTG_MAP.equals("1") && !ID_TARAFKPTG_MAP.equals("2")
											&& !ID_TARAFKPTG_MAP.equals("14")) {
										skrinNameOB = "ob";
									} else if (ID_TARAFKPTG_MAP.equals("14")) {
										skrinNameOB = "saksi";
									} else if (ID_TARAFKPTG_MAP.equals("2")) {
										skrinNameOB = "pemiutang";
									}

									String id_his_add = modelBI.saveHistoryUtama(session, "", skrinNameOB,
											"TBLPPKOBPERMOHONAN", "ID_OBPERMOHONAN", ID_OBPERMOHONAN_MAP, "UPDATE",
											ID_PERBICARAAN, ID_PERMOHONANSIMATI, db);
									modelBI.saveHistorySub(session, id_his_add, "", "ID_OBPERMOHONAN",
											ID_OBPERMOHONAN_MAP, "", ID_OBPERMOHONAN_MAP, "", 1, skrinNameOB, db);
									modelBI.saveHistorySub(session, id_his_add, "", "ID_OB", ID_OB_MAP, "", ID_OB_MAP,
											"", 2, skrinNameOB, db);
									modelBI.saveHistorySub(session, id_his_add, "", "ID_PERMOHONANSIMATI",
											ID_PERMOHONANSIMATI_MAP, "", ID_PERMOHONANSIMATI_MAP, "", 3, skrinNameOB,
											db);
									modelBI.saveHistorySub(session, id_his_add, "", "ID_SIMATI", ID_SIMATI_MAP, "",
											ID_SIMATI_MAP, "", 4, skrinNameOB, db);
									modelBI.saveHistorySub(session, id_his_add, "Sebagai Pemohon", "ID_PEMOHON",
											ID_PEMOHON_MAP, "YA", "", "TIDAK", 5, skrinNameOB, db);

								}
							}
						}
					}

					ID_SEJARAHBIMAIN = savePerubahan(session, command, ID_SEJARAHBIMAIN, mapDataAsal, skrinName,
							ID_PERMOHONANSIMATI, id, FIELD_PK, getParam(skrinName + FIELD_PK), formNameAjax, NAMA_TABLE,
							aktiviti, ID_PERBICARAAN, "", ID_SIMATI, ID_PERMOHONAN, db);

					if (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi")
							|| skrinName.equals("pemiutang")) {
						// kalo waris or ob adalah pemohon
						String ID_PEMOHON_OB = getParam(skrinName + "ID_PEMOHON");
						myLogger.info("OB ADALAH PEMOHON : " + ID_PEMOHON_OB);
						Map mapDataAsal_pemohon = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKPEMOHON", db);
						myLogger.info("mapDataAsal_pemohon: " + mapDataAsal_pemohon);

						if (!ID_PEMOHON_OB.equals("")) {
							savePerubahan(session, command, "", mapDataAsal_pemohon, skrinName, ID_PERMOHONANSIMATI,
									ID_PERMOHONAN, "ID_PEMOHON", ID_PEMOHON_OB, formNameAjax, "TBLPPKPEMOHON", "UPDATE",
									ID_PERBICARAAN, "pemohon", ID_SIMATI, ID_PERMOHONAN, db);
						}
						/*
						 * else { //get value id_pemohon String getValueID_PEMOHON = "";
						 * if(mapDataAsal_pemohon!=null) { //untuk kes kemaskini getValueID_PEMOHON =
						 * modelBI.getValue(session,ID_PERMOHONANSIMATI,
						 * mapDataAsal_pemohon,"","","",ID_PERBICARAAN,"ID_PEMOHON",db); }
						 * savePerubahan(session,command,"",mapDataAsal_pemohon,skrinName,
						 * ID_PERMOHONANSIMATI,ID_PERMOHONAN,"ID_PEMOHON",getValueID_PEMOHON,
						 * formNameAjax,"TBLPPKPEMOHON",aktiviti,ID_PERBICARAAN,"pemohon",db); }
						 */
					} else if (skrinName.equals("pemohon")) {
						// kalo waris or ob adalah pemohon
						String ID_OBPERMOHONAN_PEMOHON = getParam(skrinName + "ID_OBPERMOHONAN");
						String ID_TARAF_PEMOHON = getParam(skrinName + "ID_TARAFKPTG");
						myLogger.info("OB ADALAH OB : " + ID_OBPERMOHONAN_PEMOHON);
						if (!ID_OBPERMOHONAN_PEMOHON.equals("")) {
							String skrinNamePemohonOb = "";
							if (ID_TARAF_PEMOHON.equals("1")) {
								skrinNamePemohonOb = "waris";
							} else if (!ID_TARAF_PEMOHON.equals("1") && !ID_TARAF_PEMOHON.equals("2")
									&& !ID_TARAF_PEMOHON.equals("14")) {
								skrinNamePemohonOb = "ob";
							} else if (ID_TARAF_PEMOHON.equals("14")) {
								skrinNamePemohonOb = "saksi";
							} else if (ID_TARAF_PEMOHON.equals("2")) {
								skrinNamePemohonOb = "pemiutang";
							}

							Map mapDataAsal_ob = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
									ID_PERMOHONANSIMATI, "ID_OBPERMOHONAN", ID_OBPERMOHONAN_PEMOHON,
									"TBLPPKOBPERMOHONAN", db);
							myLogger.info("mapDataAsal_ob: " + mapDataAsal_ob);
							savePerubahan(session, command, "", mapDataAsal_ob, skrinName, ID_PERMOHONANSIMATI,
									ID_PERMOHONAN, "ID_OBPERMOHONAN", ID_OBPERMOHONAN_PEMOHON, formNameAjax,
									"TBLPPKOBPERMOHONAN", aktiviti, ID_PERBICARAAN, skrinNamePemohonOb, ID_SIMATI,
									ID_PERMOHONAN, db);
						}
					}
				}

				String jenis_transaction = modelBI.jenisTransaction(session, NAMA_TABLE, FIELD_PK, skrinName,
						getParam(FIELD_PK), ID_PERMOHONANSIMATI, ID_PERBICARAAN, db);
				myLogger.info(" jenis_transaction  :::::::::::::::::::::::: " + jenis_transaction);

				String standardParam = "ID_PEMOHON=" + ID_PEMOHON + "&ID_SIMATI=" + ID_SIMATI + "&ID_PERMOHONANSIMATI="
						+ ID_PERMOHONANSIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
						+ "&NAMA_TABLE=" + NAMA_TABLE + "&FIELD_PK=" + FIELD_PK + "&skrinName=" + skrinName
						+ "&aktiviti=" + aktiviti + "&current_previous=" + current_previous + "&jenis_transaction="
						+ jenis_transaction;

				if (skrinName.equals("pemohon")) {
					Map setupSkrinPemohon = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKPEMOHON", db);
					setupSkrin = setupSkrinPemohon(session, jenis_transaction, current_previous, aktiviti, ID_SIMATI,
							ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinPemohon, ID_PERMOHONAN, ID_PERMOHONANSIMATI,
							ID_PERBICARAAN, skrinName, command, ID_PERMOHONAN, formNameAjax, "view_" + skrinName,
							NAMA_TABLE, FIELD_PK, mode, standardParam, "Y", db);
				} else if (skrinName.equals("simati")) {
					Map setupSkrinSimati = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKSIMATI", db);
					setupSkrin = setupSkrinSimati(session, jenis_transaction, current_previous, aktiviti, ID_SIMATI,
							ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinSimati, ID_PERMOHONAN, ID_PERMOHONANSIMATI,
							ID_PERBICARAAN, skrinName, command, ID_PERMOHONAN, formNameAjax, "view_" + skrinName,
							NAMA_TABLE, FIELD_PK, mode, standardParam, "Y", db);
				} else if (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi")
						|| skrinName.equals("pemiutang") || skrinName.equals("penghutang") || skrinName.equals("peguam")
						|| skrinName.equals("htaah") || skrinName.equals("htaahx") || skrinName.equals("ha")) {
					String divId = getParam("divId");// open maklumat dalam senarai
					this.context.put("divId", divId);
					myLogger.info(" ::: divId ::: " + divId);
					String addParam = "&divId=" + divId;// div load ajax dynamicly by senarai
					Map setupSkrinWaris = null;
					if (skrinName.equals("waris") || skrinName.equals("ob") || skrinName.equals("saksi")
							|| skrinName.equals("pemiutang")) {
						addParam += "&ID_OBPERMOHONAN=" + ID_OBPERMOHONAN + "&ID_SEJARAHBIMAIN=" + ID_SEJARAHBIMAIN;
						setupSkrinWaris = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, FIELD_PK, ID_OBPERMOHONAN, "TBLPPKOBPERMOHONAN", db);
					} else if (skrinName.equals("penghutang")) {
						addParam += "&ID_PENGHUTANG=" + ID_PENGHUTANG + "&ID_SEJARAHBIMAIN=" + ID_SEJARAHBIMAIN;
						setupSkrinWaris = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, FIELD_PK, ID_PENGHUTANG, "TBLPPKPENGHUTANG", db);
					} else if (skrinName.equals("peguam")) {
						addParam += "&ID_PEGUAM=" + ID_PEGUAM + "&ID_SEJARAHBIMAIN=" + ID_SEJARAHBIMAIN;
						setupSkrinWaris = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, FIELD_PK, ID_PEGUAM, "TBLPPKPEGUAM", db);
					} else if (skrinName.equals("htaah") || skrinName.equals("htaahx")) {
						addParam += "&ID_HTAPERMOHONAN=" + ID_HTAPERMOHONAN + "&ID_SEJARAHBIMAIN=" + ID_SEJARAHBIMAIN;
						setupSkrinWaris = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, FIELD_PK, ID_HTAPERMOHONAN, "TBLPPKHTAPERMOHONAN", db);
					} else if (skrinName.equals("ha")) {
						addParam += "&ID_HAPERMOHONAN=" + ID_HAPERMOHONAN + "&ID_SEJARAHBIMAIN=" + ID_SEJARAHBIMAIN;
						setupSkrinWaris = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
								ID_PERMOHONANSIMATI, FIELD_PK, ID_HAPERMOHONAN, "TBLPPKHAPERMOHONAN", db);
					}
					myLogger.info(">>>>>>>>>>>>>> setupSkrinWaris : " + setupSkrinWaris);
					Map setupSkrinWarisHistory = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
							ID_PERMOHONANSIMATI, "ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN, "TBLPPKSEJARAHBIMAIN", db);

					if (mode.equals("view")) {
						if (setupSkrinWaris == null && setupSkrinWarisHistory == null) {
							mode = "edit";
						}
					}

					if (reloadListAjaxCall == true) {
						setupSkrin = "<script>$jquery(document).ready(function (){" + " doDivAjaxCall" + formNameAjax
								+ "('senarai_" + skrinName + "current','showSenarai','NAMA_TABLE=" + NAMA_TABLE
								+ "&ID_SIMATI=" + ID_SIMATI + "&FIELD_PK=" + FIELD_PK + "&ID_PERBICARAAN="
								+ ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_PERMOHONANSIMATI="
								+ ID_PERMOHONANSIMATI + "&skrinName=" + skrinName
								+ "&current_previous=current&tajukList=&scrolPosition='+getPageLocation());"
								+ " });$jquery(\"#" + divId + "\").html(\"\");</script>";
					} else {
						// kalo reload list, tak perlu load skrin
						if (skrinName.equals("waris") || skrinName.equals("saksi")) {
							setupSkrin = setupSkrinWaris(session, ID_PEMOHON, jenis_transaction, current_previous,
									aktiviti, ID_SIMATI, ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinWaris,
									ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command,
									ID_OBPERMOHONAN, formNameAjax, divId, NAMA_TABLE, FIELD_PK, mode,
									standardParam + addParam, "Y", db);
						} else if (skrinName.equals("ob") || skrinName.equals("pemiutang")) {
							setupSkrin = setupSkrinOb(session, ID_PEMOHON, jenis_transaction, current_previous,
									aktiviti, ID_SIMATI, ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinWaris,
									ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command,
									ID_OBPERMOHONAN, formNameAjax, divId, NAMA_TABLE, FIELD_PK, mode,
									standardParam + addParam, "Y", db);
						} else if (skrinName.equals("penghutang")) {
							setupSkrin = setupSkrinPenghutang(session, jenis_transaction, current_previous, aktiviti,
									ID_SIMATI, ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinWaris, ID_PERMOHONAN,
									ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command, ID_PENGHUTANG,
									formNameAjax, divId, NAMA_TABLE, FIELD_PK, mode, standardParam + addParam, "Y", db);
						} else if (skrinName.equals("peguam")) {
							setupSkrin = setupSkrinPeguam(session, jenis_transaction, current_previous, aktiviti,
									ID_SIMATI, ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinWaris, ID_PERMOHONAN,
									ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command, ID_PEGUAM, formNameAjax,
									divId, NAMA_TABLE, FIELD_PK, mode, standardParam + addParam, "Y", db);
						} else if (skrinName.equals("htaah") || skrinName.equals("htaahx")) {
							setupSkrin = setupSkrinHtaah(session, jenis_transaction, current_previous, aktiviti,
									ID_SIMATI, ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinWaris, ID_PERMOHONAN,
									ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command, ID_HTAPERMOHONAN,
									formNameAjax, divId, NAMA_TABLE, FIELD_PK, mode, standardParam + addParam, "Y", db);
						} else if (skrinName.equals("ha")) {
							setupSkrin = setupSkrinHa(session, jenis_transaction, current_previous, aktiviti, ID_SIMATI,
									ID_SEJARAHBIMAIN, setupSkrinHistory, setupSkrinWaris, ID_PERMOHONAN,
									ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName, command, ID_HAPERMOHONAN,
									formNameAjax, divId, NAMA_TABLE, FIELD_PK, mode, standardParam + addParam, "Y", db);
						}
					}
				}
				this.context.put("htmlSkrinMaklumat", setupSkrin);
			} finally {
				if (db != null)
					db.close();
			}
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
		}

		else if (command.equals("janaCatatanPerintah")) {
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			String ID_PERINTAH = getParam("ID_PERINTAH");
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String ID_SIMATI = getParam("ID_SIMATI");
			String ID_PEMOHON = getParam("ID_PEMOHON");
			String skrinName = getParam("skrinName");
			String FLAG_JENIS_KEPUTUSAN = getParam(skrinName + "FLAG_JENIS_KEPUTUSAN");
			String ID_INTROPERINTAH = getParam(skrinName + "ID_INTROPERINTAH");
			myLogger.info("ID_PERINTAH >>>>>>>>> " + ID_PERINTAH);
			myLogger.info("ID_PERMOHONANSIMATI >>>>>>>>> " + ID_PERMOHONANSIMATI);
			myLogger.info("ID_PERBICARAAN >>>>>>>>> " + ID_PERBICARAAN);
			myLogger.info("ID_PEMOHON >>>>>>>>> " + ID_PEMOHON);
			myLogger.info("skrinName >>>>>>>>> " + skrinName);
			String htmlContent = "";
			String ID_FAIL = "";
			String NO_FAIL = "";
			String NAMA_PEGAWAI = "";
			String BIL_BICARA = "";
			String WAKTU_BICARA = "";
			String NO_PINDAAN = "";

			Db db = null;
			try {
				db = new Db();
				// modelBI.simpanIntroPerintah(session, ID_PERINTAH, ID_INTROPERINTAH, db);
				Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
				ID_FAIL = (String) mainID.get("ID_FAIL");
				NO_FAIL = (String) mainID.get("NO_FAIL");
				Map getMaklumatPindaan = modelBI.getMaklumatPindaan(session, ID_FAIL, db);
				myLogger.info("getMaklumatPindaan atas : " + getMaklumatPindaan);
				if (getMaklumatPindaan != null) {
					myLogger.info("getMaklumatPindaan bawah : " + getMaklumatPindaan);
					NO_PINDAAN = (String) getMaklumatPindaan.get("NO_PINDAAN");
				}
				myLogger.info("NO_PINDAAN : " + NO_PINDAAN);
				Map setupKeputusanBeforeSave = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKPERINTAH", db);
				ID_PERINTAH = savePerintah(session, setupKeputusanBeforeSave, ID_SIMATI, ID_PERMOHONAN,
						FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI, formName, ID_PERBICARAAN, skrinName,
						db);
				htmlContent = modelBI.janaContentCatatanPerintah(session, ID_FAIL, formName, ID_SIMATI, ID_PERBICARAAN,
						ID_PERMOHONAN, ID_PERMOHONANSIMATI, ID_PEMOHON, ID_PERINTAH, NO_PINDAAN, fontSize, db);
				Map mapPerbicaraan = modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db);
				NAMA_PEGAWAI = (String) mapPerbicaraan.get("PEG_PENGENDALI");
				BIL_BICARA = (Integer) mapPerbicaraan.get("BIL_BICARA") + "";
				WAKTU_BICARA = (String) mapPerbicaraan.get("TARIKH_BICARA") + " "
						+ (String) mapPerbicaraan.get("MASA_BICARA");

				saveHistoryJana(session, ID_PERBICARAAN, ID_PERINTAH, ID_FAIL, NO_PINDAAN, htmlContent, NAMA_PEGAWAI,
						BIL_BICARA, WAKTU_BICARA, NO_FAIL, db);
				// saveHistoryJana(session,ID_PERBICARAAN, ID_PERINTAH, ID_FAIL, "",
				// htmlContent, db);
				saveContentCatatanBI(session, ID_PERINTAH, htmlContent, db);

			}

			finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERINTAH", ID_PERINTAH);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("skrinName", skrinName);
			this.context.put("htmlSkrinMaklumat", htmlContent);

			skrin_name = "app/ppk/BicaraInteraktif/dummyJanaCatatan.jsp";
		}

		else if (command.equals("reSetupCatatanPerintah")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String FIELD_NAME = getParam("FIELD_NAME");
			String content = "";
			Db db = null;
			try {
				db = new Db();
				Map setupKeputusan = modelBI.getValueColumn(session, "", "", "keputusan", "", "", ID_PERBICARAAN,
						"TBLPPKPERINTAH", db);
				String CATATAN_PERINTAH_BI = setupKeputusan == null ? ""
						: (String) setupKeputusan.get("CATATAN_PERINTAH_BI") == null ? ""
								: (String) setupKeputusan.get("CATATAN_PERINTAH_BI");
				content = CATATAN_PERINTAH_BI;
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("htmlSkrinMaklumat", content);
			this.context.put("FIELD_NAME", FIELD_NAME);
			skrin_name = "app/ppk/BicaraInteraktif/reSetupContent.jsp";
		} else if (command.equals("showCetakan")) {
			String skrinName = getParam("skrinName");
			this.context.put("skrinName", skrinName);
			String ID_OBPERMOHONAN = getParam("ID_OBPERMOHONAN");
			this.context.put("ID_OBPERMOHONAN", ID_OBPERMOHONAN);
			String ID_PENGHUTANG = getParam("ID_PENGHUTANG");
			this.context.put("ID_PENGHUTANG", ID_PENGHUTANG);
			String ID_PEGUAM = getParam("ID_PEGUAM");
			this.context.put("ID_PEGUAM", ID_PEGUAM);
			String ID_HTAPERMOHONAN = getParam("ID_HTAPERMOHONAN");
			this.context.put("ID_HTAPERMOHONAN", ID_HTAPERMOHONAN);
			String ID_HAPERMOHONAN = getParam("ID_HAPERMOHONAN");
			this.context.put("ID_HAPERMOHONAN", ID_HAPERMOHONAN);
			String ID_SIMATI = getParam("ID_SIMATI");
			this.context.put("ID_SIMATI", ID_SIMATI);
			String ID_SEJARAHBIMAIN = getParam("ID_SEJARAHBIMAIN");
			this.context.put("ID_SEJARAHBIMAIN", ID_SEJARAHBIMAIN);
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String ID_PEMOHON = getParam("ID_PEMOHON");
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			String NAMA_TABLE = getParam("NAMA_TABLE");
			String FIELD_PK = getParam("FIELD_PK");
			String current_previous = getParam("current_previous");
			String flag_gantiPemohon = getParam("flag_gantiPemohon");
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("div", getParam("div"));

			String setupSkrin = "";
			Db db = null;
			try {
				db = new Db();
				Map viewPerbicaraan = modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db);

				if (viewPerbicaraan != null) {
					setupSkrin = modelBI.openHTMLSenaraiCetakan(session, viewPerbicaraan, formName, ID_SIMATI,
							ID_PERBICARAAN, ID_PERMOHONAN, ID_PERMOHONANSIMATI, db);
				}
				this.context.put("htmlSkrinMaklumat", setupSkrin);
			} finally {
				if (db != null)
					db.close();
			}
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";
		} else if (command.equals("getLapis")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			String ID_PARENT = getParam("ID_PARENT");
			myLogger.info("ID_PARENT ::::: " + ID_PARENT);
			String skrinName = getParam("skrinName");
			String formDynamicDropDown = "";
			Db db = null;
			try {
				db = new Db();
				String lapis = "";
				if (!ID_PARENT.equals("")) {
					List listParentSimati = modelBI.listParentSimati(session, ID_PERMOHONANSIMATI, ID_PERBICARAAN,
							skrinName, ID_PARENT, db);
					if (listParentSimati.size() > 0) {
						Map setupOB = (Map) listParentSimati.get(0);
						if (setupOB != null) {
							lapis = (String) setupOB.get("LAPIS");
						}
					}
				}
				formDynamicDropDown = "<script>$jquery(document).ready(function () { setLapis('" + lapis + "','"
						+ skrinName + "'); });</script> ";
			} finally {
				if (db != null)
					db.close();
			}

			this.context.put("formDynamicDropDown", formDynamicDropDown);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		} else if (command.equals("getAlamatPejabat")) {
			String ID_ARB = getParam("ID_ARB");
			String skrinName = getParam("skrinName");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String formDynamicDropDown = "";
			Db db = null;
			try {
				db = new Db();
				Map setupSkrin = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName, "", "ID_PEJABAT",
						ID_ARB, "TBLRUJPEJABAT", db);
				// set data pejabat
				if (setupSkrin != null) {
					formDynamicDropDown = "<script>$jquery(document).ready(function () { setMapPejabat('" + skrinName
							+ "','" + (String) setupSkrin.get("NAMA_PEJABAT") + "','"
							+ (String) setupSkrin.get("ALAMAT1") + "', '" + (String) setupSkrin.get("ALAMAT2") + "', '"
							+ (String) setupSkrin.get("ALAMAT3") + "', '" + (String) setupSkrin.get("POSKOD") + "', '"
							+ (String) setupSkrin.get("ID_NEGERI") + "', '" + (String) setupSkrin.get("ID_BANDAR")
							+ "','" + (String) setupSkrin.get("NO_TEL") + "'); });</script> ";
				}
			} finally {
				if (db != null)
					db.close();
			}

			this.context.put("formDynamicDropDown", formDynamicDropDown);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		} else if (command.equals("getAlamatMahkamah") || command.equals("getAlamatJkptg")) {
			String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			String ID_MAHKAMAH = getParam("ID_MAHKAMAH");
			String skrinName = getParam("skrinName");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String formDynamicDropDown = "";
			Db db = null;
			Map setupSkrin = null;
			try {
				db = new Db();

				if (command.equals("getAlamatMahkamah")) {

					String flag_tangguh = getParam(skrinName + "FLAG_TANGGUH");
					myLogger.info("getAlamatMahkamah >>> flag_tangguh :: " + flag_tangguh);
					if (flag_tangguh.equals("6")) {
						setupSkrin = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName, "",
								"ID_PEJABATJKPTG", ID_MAHKAMAH, "TBLRUJPEJABATJKPTG", db);
					} else {
						setupSkrin = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName, "", "ID_PEJABAT",
								ID_MAHKAMAH, "TBLRUJPEJABAT", db);
					}
				}
				/*
				 * else if(command.equals("getAlamatJkptg")) { setupSkrin =
				 * modelBI.getValueColumn(session,"",ID_PERBICARAAN,skrinName,"",
				 * "ID_PEJABATJKPTG", ID_PEJABATJKPTG, "TBLRUJPEJABATJKPTG",db); }
				 */

				// set data pejabat
				if (setupSkrin != null) {
					formDynamicDropDown = "<script>$jquery(document).ready(function () { setMapPejabatMahkamah('"
							+ skrinName + "','" + (String) setupSkrin.get("NAMA_PEJABAT") + "','"
							+ (String) setupSkrin.get("ALAMAT1") + "', '" + (String) setupSkrin.get("ALAMAT2") + "', '"
							+ (String) setupSkrin.get("ALAMAT3") + "', '" + (String) setupSkrin.get("POSKOD") + "', '"
							+ (String) setupSkrin.get("ID_NEGERI") + "', '" + (String) setupSkrin.get("ID_BANDAR")
							+ "','" + (String) setupSkrin.get("NO_TEL") + "','" + (String) setupSkrin.get("NO_FAX")
							+ "'); });</script> ";
				} else {
					formDynamicDropDown = "<script>$jquery(document).ready(function () { setMapPejabatMahkamah('"
							+ skrinName + "','','', '', '', '', '','','',''); });</script> ";
				}
			} finally {
				if (db != null)
					db.close();
			}

			this.context.put("formDynamicDropDown", formDynamicDropDown);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		} else if (command.equals("getAlamatPemohon")) {
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			String skrinName = getParam("skrinName");
			String formDynamicDropDown = "";

			Db db = null;
			try {
				db = new Db();
				String ALAMAT_1 = "";
				String ALAMAT_2 = "";
				String ALAMAT_3 = "";
				String POSKOD = "";
				String ID_NEGERI = "";
				String ID_BANDAR = "";
				String NEGARA = "";
				String ID_PEMOHON = "";
				Map setupSkrin = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName, ID_PERMOHONANSIMATI, "",
						ID_PERMOHONAN, "TBLPPKPEMOHON", db);
				myLogger.info("setupSkrin pemohon : " + setupSkrin);
				// set data pejabat
				if (setupSkrin != null) {
					// get maklumat asal dlu
					ID_PEMOHON = (String) setupSkrin.get("ID_PEMOHON");
					ALAMAT_1 = (String) setupSkrin.get("ALAMAT_1");
					ALAMAT_2 = (String) setupSkrin.get("ALAMAT_2");
					ALAMAT_3 = (String) setupSkrin.get("ALAMAT_3");
					POSKOD = (String) setupSkrin.get("POSKOD");
					ID_NEGERI = (String) setupSkrin.get("ID_NEGERI");
					ID_BANDAR = (String) setupSkrin.get("ID_BANDAR");
					NEGARA = (String) setupSkrin.get("NAMA_PELBAGAINEGARA");
				}
				// check kalo ada perubahan maklumat
				if (!ID_PEMOHON.equals("")) {
					Map getChangesALAMAT_1 = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "ALAMAT_1", db);
					if (getChangesALAMAT_1 != null) {
						ALAMAT_1 = (String) getChangesALAMAT_1.get("VALUE_SELEPAS");
					}
					Map getChangesALAMAT_2 = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "ALAMAT_2", db);
					if (getChangesALAMAT_2 != null) {
						ALAMAT_2 = (String) getChangesALAMAT_2.get("VALUE_SELEPAS");
					}
					Map getChangesALAMAT_3 = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "ALAMAT_3", db);
					if (getChangesALAMAT_3 != null) {
						ALAMAT_3 = (String) getChangesALAMAT_3.get("VALUE_SELEPAS");
					}
					Map getChangesPOSKOD = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "POSKOD", db);
					if (getChangesPOSKOD != null) {
						POSKOD = (String) getChangesPOSKOD.get("VALUE_SELEPAS");
					}
					Map getChangesID_NEGERI = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "ID_NEGERI", db);
					if (getChangesID_NEGERI != null) {
						ID_NEGERI = (String) getChangesID_NEGERI.get("VALUE_SELEPAS");
					}
					Map getChangesID_BANDAR = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "ID_BANDAR", db);
					if (getChangesID_BANDAR != null) {
						ID_BANDAR = (String) getChangesID_BANDAR.get("VALUE_SELEPAS");
					}
					Map getChangesNEGARA = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, "TBLPPKPEMOHON",
							"ID_PEMOHON", ID_PEMOHON, ID_PERBICARAAN, "NAMA_PELBAGAINEGARA", db);
					if (getChangesNEGARA != null) {
						NEGARA = (String) getChangesNEGARA.get("VALUE_SELEPAS");
					}
				}
				formDynamicDropDown = "<script>$jquery(document).ready(function () { setMapPemohon('" + skrinName
						+ "','" + ID_PERMOHONAN + "','" + ID_PERMOHONANSIMATI + "','" + ID_PERBICARAAN + "'," + "'"
						+ ALAMAT_1 + "', '" + ALAMAT_2 + "', '" + ALAMAT_3 + "', '" + POSKOD + "', '" + ID_NEGERI
						+ "', '" + ID_BANDAR + "','" + NEGARA + "');});</script> ";
			} finally {
				if (db != null)
					db.close();
			}

			this.context.put("formDynamicDropDown", formDynamicDropDown);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		} else if (command.equals("showNegeriSurat") || command.equals("showNegeri")
				|| command.equals("showNegeriMahkamah")) {
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String ID_PERMOHONANSIMATI = getParam("ID_PERMOHONANSIMATI");
			String ID_NEGERI = getParam("ID_NEGERISALIN");
			String ID_BANDAR = getParam("ID_BANDARSALIN");
			String skrinName = getParam("skrinName");
			myLogger.info("--------- skrinName : " + skrinName);
			String divID = "";
			String commandSalin = "";
			String formDynamicDropDown = "";
			Db db = null;

			/*
			 * String NamaTable = "TBLPPKPEMOHON"; if(command.equals("showNegeriSurat") ||
			 * command.equals("showNegeri") || command.equals("showNegeriMahkamah")) {
			 *
			 * }
			 */

			try {
				db = new Db();
				if (command.equals("showNegeriSurat")) {
					divID = "div" + skrinName + "ID_BANDARSURAT";
					commandSalin = "showBandarSurat";
					formDynamicDropDown = modelBI.refDropDown(session, "select", ID_PERBICARAAN, skrinName, command,
							"TBLPPKPEMOHON", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
							"ID_NEGERISURAT", "edit", ID_NEGERI, "div" + skrinName + "ID_BANDARSURAT", "TBLRUJBANDAR",
							"ID_BANDARSURAT", "ID_NEGERI", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", formNameAjax, null,
							"", db);
				} else if (command.equals("showNegeri")) {
					divID = "div" + skrinName + "ID_BANDAR";
					commandSalin = "showBandar";
					formDynamicDropDown = modelBI.refDropDown(session, "select", ID_PERBICARAAN, skrinName, command,
							"TBLPPKPEMOHON", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
							"ID_NEGERI", "edit", ID_NEGERI, "div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR",
							"ID_BANDAR", "ID_NEGERI", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", formNameAjax, null, "",
							db);
				}

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("formDynamicDropDown", formDynamicDropDown);
			this.context.put("flagSalin", "Y");
			this.context.put("divID", divID);
			this.context.put("commandSalin", commandSalin);
			this.context.put("skrinName", skrinName);
			this.context.put("ID_BANDAR", ID_BANDAR);
			this.context.put("ID_NEGERI", ID_NEGERI);
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		} else if (command.equals("showBandarSurat") || command.equals("showBandar")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String ID_BANDAR = getParam("ID_BANDARSALIN");
			String ID_NEGERI = getParam("ID_NEGERISALIN");
			String skrinName = getParam("skrinName");
			myLogger.info("--------- skrinName : " + skrinName);
			String formDynamicDropDown = "";
			Db db = null;
			try {
				db = new Db();
				if (command.equals("showBandarSurat")) {
					formDynamicDropDown = modelBI.refDropDown(session, "select", ID_PERBICARAAN, skrinName, command,
							"TBLPPKPEMOHON", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI",
							ID_NEGERI, "ID_BANDARSURAT", "edit", ID_BANDAR, "", "", "", "", "", "", "", formNameAjax,
							null, "", db);
				} else if (command.equals("showBandar")) {
					formDynamicDropDown = modelBI.refDropDown(session, "select", ID_PERBICARAAN, skrinName, command,
							"TBLPPKPEMOHON", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI",
							ID_NEGERI, "ID_BANDAR", "edit", ID_BANDAR, "", "", "", "", "", "", "", formNameAjax, null,
							"", db);
				}
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("skrinName", skrinName);
			this.context.put("formDynamicDropDown", formDynamicDropDown);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		} else if (command.equals("showArbBaitulmal")) {
			String mainTable = getParam("mainTable");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String ID_TARAFKPTG = getParam("ID_TARAFKPTG");
			String ID_ARB = getParam("ID_ARB");
			String skrinName = getParam("skrinName");
			String formDynamicDropDown = "";
			Db db = null;
			try {
				db = new Db();
				formDynamicDropDown = modelBI.refDropDown(session, "select", ID_PERBICARAAN, skrinName, command,
						mainTable, "TBLRUJPEJABAT", "ID_PEJABAT", "KOD_PEJABAT", "NAMA_PEJABAT", "ID_JENISPEJABAT",
						ID_TARAFKPTG, "ID_ARB", "edit", ID_ARB, "", "", "", "", "", "", "", formNameAjax, null, "", db);
			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("formDynamicDropDown", formDynamicDropDown);
			this.context.put("ID_TARAFKPTG", ID_TARAFKPTG);
			this.context.put("ID_ARB", ID_ARB);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		}
		// open dynamic ajax call
		else if (command.equals("ajaxCallDropDown")) {
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			String currentValue = "";

			String mainTable = getParam("mainTable");
			String skrinName = getParam("skrinName");
			String refTable = getParam("refTable");
			String PK_refTable = getParam("PK_refTable");
			String field_KOD_refTable = getParam("field_KOD_refTable");
			String field_VALUE_refTable = getParam("field_VALUE_refTable");
			String field_FK_refTable = getParam("field_FK_refTable");
			String VALUE_FK_refTable = getParam(getParam("VALUE_FK_refTable"));
			String column_name = getParam("column_name");

			if (column_name.equals("ID_BANDARHTA")) {
				currentValue = getParam("currentValue");
				myLogger.info("currentValue ID_BANDARHTA : " + currentValue);

			}
			String id_jenispejabat_mahkamah = "8";
			if (column_name.equals("ID_MAHKAMAH") && skrinName.equals("keputusan")) {
				myLogger.info("BEFORE MAHKAMAH VALUE_FK_refTable : " + VALUE_FK_refTable);
				String flag_rujukan = getParam(skrinName + "FLAG_RUJUKAN");
				if (flag_rujukan.equals("1")) {
					id_jenispejabat_mahkamah = "41";
				}
				VALUE_FK_refTable = id_jenispejabat_mahkamah + VALUE_FK_refTable;
				myLogger.info("AFTER MAHKAMAH VALUE_FK_refTable : " + VALUE_FK_refTable);
			} else if (column_name.equals("ID_PEJABATMAHKAMAH") && skrinName.equals("keputusan")) {
				String flag_batal = getParam(skrinName + "FLAG_BATAL");
				if (flag_batal.equals("1")) {
					VALUE_FK_refTable = id_jenispejabat_mahkamah + VALUE_FK_refTable;
				}
			}

			myLogger.info("mainTable : " + mainTable);
			myLogger.info("refTable : " + refTable);
			myLogger.info("PK_refTable : " + PK_refTable);
			myLogger.info("field_KOD_refTable : " + field_KOD_refTable);
			myLogger.info("field_VALUE_refTable : " + field_VALUE_refTable);
			myLogger.info("field_FK_refTable : " + field_FK_refTable);
			myLogger.info("VALUE_FK_refTable : " + VALUE_FK_refTable);
			myLogger.info("column_name : " + column_name);

			String formDynamicDropDown = "";
			Db db = null;
			try {
				db = new Db();
				String divCall = "";
				String tableCall = "";
				String fieldtableCall = "";
				String field_FK_TableCall = "";
				String field_PK_TableCall = "";
				String field_KOD_TableCall = "";
				String field_VALUE_TableCall = "";

				if (column_name.equals("ID_DAERAH")) {
					// jika ada drop down tertentu yg perlukan next ajax call
					// cth : dropdown daerah yg akan call ajax mukim
					divCall = "div" + skrinName + "ID_MUKIM";
					tableCall = "TBLRUJMUKIM";
					fieldtableCall = "ID_MUKIM";
					field_FK_TableCall = "ID_DAERAH";
					field_PK_TableCall = "ID_MUKIM";
					field_KOD_TableCall = "KOD_MUKIM";
					field_VALUE_TableCall = "NAMA_MUKIM";
				}

				if (column_name.equals("STATUS_PEMOHON") || column_name.equals("JENIS_PEMIUTANG")) {
					formDynamicDropDown = modelBI.hardCoderefDropDown(session, "select", ID_PERBICARAAN, skrinName,
							command, mainTable, refTable, PK_refTable, field_KOD_refTable, field_VALUE_refTable,
							field_FK_refTable, VALUE_FK_refTable, column_name, "edit", "", divCall, tableCall,
							fieldtableCall, field_FK_TableCall, field_PK_TableCall, field_KOD_TableCall,
							field_VALUE_TableCall, formNameAjax, db);
				} else {
					formDynamicDropDown = modelBI.refDropDown(session, "select", ID_PERBICARAAN, skrinName, command,
							mainTable, refTable, PK_refTable, field_KOD_refTable, field_VALUE_refTable,
							field_FK_refTable, VALUE_FK_refTable, column_name, "edit", currentValue, divCall, tableCall,
							fieldtableCall, field_FK_TableCall, field_PK_TableCall, field_KOD_TableCall,
							field_VALUE_TableCall, formNameAjax, null, "", db);
				}

			} finally {
				if (db != null)
					db.close();
			}
			this.context.put("formDynamicDropDown", formDynamicDropDown);
			skrin_name = "app/ppk/BicaraInteraktif/formDynamicDropDown.jsp";
		}
		// }
		// close dynamic ajax call
		myLogger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> skrin_name : " + skrin_name);
		return skrin_name;
	}

	public void defaultPut() {
		context.put("errorMesej", "");
		this.context.put("carianBicaraNO_FAIL", "");
		this.context.put("carianBicaraNAMAPENGENALAN_SIMATI", "");
		this.context.put("carianBicaraNAMAPENGENALAN_PEMOHON", "");

		// initiate default value
		this.context.put("totalHM", "");
		this.context.put("totalBezaHM", "");
		this.context.put("totalKeterangan", "");
		this.context.put("totalTH", "");
		this.context.put("totalBezaTH", "");
		this.context.put("totalKeteranganTH", "");

		this.context.put("dataStatusOB", "");
		this.context.put("dataHubungan", "");

		this.context.put("listHistoryJana", "");
		this.context.put("ID_OBPERMOHONANMINOR", "");
		this.context.put("LISTPENJAGA", "");
		this.context.put("htmlContentJana", "");
		this.context.put("listPermohonanTukarPegawaiSejarah", "");
		this.context.put("list_id_pegawai", "");
		this.context.put("htmlCarianBicara", "");
		this.context.put("flag_jenis_vm", "");
		this.context.put("listKronologiStatus", "");
		this.context.put("skrinName", "");
		this.context.put("mode", "");
		this.context.put("tajukLaporan", "");
		this.context.put("rowCss", "");
		this.context.put("BIL", "");
		this.context.put("scrolPosition", "");
		this.context.put("div", "");
		this.context.put("viewPerbicaraan", "");
		this.context.put("listKehadiran", "");
		this.context.put("listTurutHadir", "");
		this.context.put("viewTuruthadir", "");
		this.context.put("listSaksi", "");// arief add
		this.context.put("viewSaksi", "");// arief add
		this.context.put("listTidakHadir", "");// arief add
		this.context.put("viewTidakHadirr", "");// arief add
		this.context.put("ID_PERBICARAAN", "");
		this.context.put("ID_PERMOHONAN", "");
		this.context.put("ID_PERMOHONANSIMATI", "");
		this.context.put("ID_SIMATI", "");
		this.context.put("htmlSkrinMaklumat", "");
		this.context.put("formDynamicDropDown", "");
		this.context.put("flagSalin", "");
		this.context.put("ID_BANDAR", "");
		this.context.put("ID_NEGERI", "");
		this.context.put("divID", "");
		this.context.put("commandSalin", "");
		this.context.put("skrinName", "");
		this.context.put("ID_OBPERMOHONAN", "");
		this.context.put("current_previous", "");
		this.context.put("divId", "");
		this.context.put("flagPrint", "N");
		this.context.put("listPerbicaraanLain", "");
		this.context.put("defaultListHarta", "");
		this.context.put("flagOpenTP", "");
		this.context.put("SuccessMesej", "");

	}

	public void setupPageMainList(HttpSession session, String action, List list, String namaList, String command) {
		try {
			String scrolPosition = getParam("scrolPosition");
			myLogger.info(" ------- scrolPosition :" + scrolPosition);
			this.context.put("scrolPosition", scrolPosition);
			this.context.put("namaList", namaList);
			this.context.put("command", command);
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			int page_mula = 1;

			int itemsPerPage;
			if (this.context.get("itemsPerPage" + command) == null
					|| this.context.get("itemsPerPage" + command) == "") {
				itemsPerPage = getParam("itemsPerPage" + command) == "" ? 10
						: getParamAsInteger("itemsPerPage" + command);
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage" + command);
			}

			if (("getNext").equals(action)) {
				page++;
			} else if (("getPrevious").equals(action)) {
				page--;
			} else if (("getPage").equals(action)) {
				page = getParamAsInteger("value");
			} else if (("doChangeItemPerPage").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage" + command);
				myLogger.info(" ------- itemsPerPage :" + itemsPerPage);
			} else if (("specialFromList").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage" + command);
				page = getParamAsInteger("currentPage" + command);
				myLogger.info(" ------- itemsPerPage :" + itemsPerPage);
			}

			Paging2 paging = new Paging2(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1;
			this.context.put(namaList, paging.getPage(page));
			this.context.put("page_mula", new Integer(page_mula));
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

	@SuppressWarnings("unchecked")
	public List listPerbicaraan(HttpSession session, String user_id, String id_jawatan, String id_negeri,
			String flagCari, String flagDashboard, Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPerbicaraan = null;
		String sql = "";

		// GET PARAM CARIAN
		String carianBicaraNO_FAIL = "";
		String carianBicaraNAMA_SIMATI = "";
		String carianBicaraPENGENALAN_SIMATI = "";
		String carianBicaraNAMA_PEMOHON = "";
		String carianBicaraPENGENALAN_PEMOHON = "";
		String carianBicaraID_NEGERIBICARA = "";
		String carianBicaraNAMA_PEGAWAIBICARA = "";
		String carianBicaraTARIKH_BICARAMULA = "";
		String carianBicaraTARIKH_BICARAAKHIR = "";
		String carianBicaraBIL_BICARA = "";
		String carianBicaraSTATUS_PERBICARAAN = "";
		String carianBicaraSTATUS = "";

		try {

			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			stmt = db1.getStatement();
			sql += modelBI.queryPerbicaraan(session, "", user_id, id_jawatan, id_negeri, flagCari, db1);

			if (flagCari.equals("Y")) {
				carianBicaraNO_FAIL = getParam("carianBicaraNO_FAIL");
				carianBicaraNAMA_SIMATI = getParam("carianBicaraNAMA_SIMATI");
				carianBicaraPENGENALAN_SIMATI = getParam("carianBicaraPENGENALAN_SIMATI");
				carianBicaraNAMA_PEMOHON = getParam("carianBicaraNAMA_PEMOHON");
				carianBicaraPENGENALAN_PEMOHON = getParam("carianBicaraPENGENALAN_PEMOHON");
				carianBicaraID_NEGERIBICARA = getParam("carianBicaraID_NEGERIBICARA");
				carianBicaraNAMA_PEGAWAIBICARA = getParam("carianBicaraNAMA_PEGAWAIBICARA");
				carianBicaraTARIKH_BICARAMULA = getParam("carianBicaraTARIKH_BICARAMULA");
				carianBicaraTARIKH_BICARAAKHIR = getParam("carianBicaraTARIKH_BICARAAKHIR");
				carianBicaraBIL_BICARA = getParam("carianBicaraBIL_BICARA");
				carianBicaraSTATUS_PERBICARAAN = getParam("carianBicaraSTATUS_PERBICARAAN");
				carianBicaraSTATUS = getParam("carianBicaraSTATUS");

				if (!carianBicaraNAMA_SIMATI.equals("")) {
					sql += " AND UPPER(SM.NAMA_SIMATI) LIKE '%" + carianBicaraNAMA_SIMATI.toUpperCase() + "%' ";
				}
				if (!carianBicaraPENGENALAN_SIMATI.equals("")) {
					sql += " AND ( " + " UPPER(SM.NO_KP_BARU) LIKE '%" + carianBicaraPENGENALAN_SIMATI.toUpperCase()
							+ "%' " + " OR UPPER(SM.NO_KP_LAMA) LIKE '%" + carianBicaraPENGENALAN_SIMATI.toUpperCase()
							+ "%' " + " OR UPPER(SM.NO_KP_LAIN) LIKE '%" + carianBicaraPENGENALAN_SIMATI.toUpperCase()
							+ "%' " + " ) ";
				}

				if (!carianBicaraSTATUS.equals("")) {
					sql += " AND P.ID_STATUS = '" + carianBicaraSTATUS + "' ";
				}

				if (!carianBicaraNAMA_PEMOHON.equals("")) {
					sql += " AND UPPER(PM.NAMA_PEMOHON) LIKE '%" + carianBicaraNAMA_PEMOHON.toUpperCase() + "%' ";
				}
				if (!carianBicaraPENGENALAN_PEMOHON.equals("")) {
					sql += " AND ( " + " UPPER(PM.NO_KP_BARU) LIKE '%" + carianBicaraPENGENALAN_PEMOHON.toUpperCase()
							+ "%' " + " OR UPPER(PM.NO_KP_LAMA) LIKE '%" + carianBicaraPENGENALAN_PEMOHON.toUpperCase()
							+ "%' " + " OR UPPER(PM.NO_KP_LAIN) LIKE '%" + carianBicaraPENGENALAN_PEMOHON.toUpperCase()
							+ "%' " + " ) ";
				}

				if (!carianBicaraNAMA_PEGAWAIBICARA.equals("")) {
					sql += " AND (UPPER(UP.NAMA_PEGAWAI) LIKE '%" + carianBicaraNAMA_PEGAWAIBICARA.toUpperCase()
							+ "%' OR UPPER(CHECK_TP.NAMA_PEGAWAI_BARU) LIKE '%"
							+ carianBicaraNAMA_PEGAWAIBICARA.toUpperCase() + "%') ";
				}

				if (!carianBicaraNO_FAIL.equals("")) {
					sql += " AND UPPER(F.NO_FAIL) LIKE '%" + carianBicaraNO_FAIL.toUpperCase().trim() + "%' ";
				}
				if (!carianBicaraID_NEGERIBICARA.equals("")) {
					sql += " AND PR.ID_NEGERIBICARA = '" + carianBicaraID_NEGERIBICARA + "' ";
				}
				if (!carianBicaraBIL_BICARA.equals("")) {
					sql += " AND PR.BIL_BICARA = '" + carianBicaraBIL_BICARA + "' ";
				}

				if (!carianBicaraSTATUS_PERBICARAAN.equals("") && !carianBicaraSTATUS_PERBICARAAN.equals("99")) {
					sql += " AND PH.FLAG_JENIS_KEPUTUSAN = '" + carianBicaraSTATUS_PERBICARAAN + "' ";
				} else if (carianBicaraSTATUS_PERBICARAAN.equals("99")) {
					// sql += " AND PH.FLAG_JENIS_KEPUTUSAN IS NULL ";
					sql += " AND NVL(PH.FLAG_JENIS_KEPUTUSAN,' ') NOT IN ('0','1','2') AND P.ID_STATUS = '18' ";
				}

				if (!carianBicaraTARIKH_BICARAMULA.equals("") && !carianBicaraTARIKH_BICARAAKHIR.equals("")) {
					sql += " AND PR.TARIKH_BICARA BETWEEN TO_DATE('" + carianBicaraTARIKH_BICARAMULA
							+ "','DD/MM/YYYY') AND TO_DATE('" + carianBicaraTARIKH_BICARAAKHIR + "','DD/MM/YYYY') ";
				}
				if (!carianBicaraTARIKH_BICARAMULA.equals("") && carianBicaraTARIKH_BICARAAKHIR.equals("")) {
					sql += " AND PR.TARIKH_BICARA > TO_DATE('" + carianBicaraTARIKH_BICARAMULA + "','DD/MM/YYYY') ";
				}
				if (carianBicaraTARIKH_BICARAMULA.equals("") && !carianBicaraTARIKH_BICARAAKHIR.equals("")) {
					sql += " AND PR.TARIKH_BICARA < TO_DATE('" + carianBicaraTARIKH_BICARAAKHIR + "','DD/MM/YYYY') ";
				}

			} else {
				// ni yg sebenar, by default akan memaparkan bicara pagawai berkenaan pada hari
				// tersebut sahaja
				// 2020/03/27 - komen untuk paparan local
				// sql += " AND TO_CHAR(PR.TARIKH_BICARA,'DD/MM/YYYY') =
				// TO_CHAR(SYSDATE,'DD/MM/YYYY') AND P.ID_STATUS = '18' ";

				// ni yg sementara
				// sql += " AND TO_CHAR(PR.TARIKH_BICARA,'DD/MM/YYYY') = '10/02/2010' ";
			}
			sql += " AND  PR.TARIKH_BICARA IS NOT NULL ";

			// OPEN CONDITION SEMENTARA
			/*
			 * sql += " AND TO_CHAR(P.TARIKH_MOHON,'YYYY') = '2017' "; sql +=
			 * " AND PR.ID_PERBICARAAN IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE TO_CHAR(TARIKH_BICARA,'YYYY') = '2017') "
			 * ; sql += " AND F.ID_NEGERI = 7 "; sql +=
			 * " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM TBLPPKHUBUNGANOBPERMOHONAN ) "
			 * ;
			 */
			// PEGUAM
			/*
			 * sql +=
			 * " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT PSM.ID_PERMOHONANSIMATI FROM TBLPPKPEGUAM PG, TBLPPKPEMOHON PM, "
			 * + " TBLPPKPEGUAMPEMOHON PP, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI PSM "+
			 * " WHERE PP.ID_PEMOHON = PM.ID_PEMOHON AND PG.ID_PEGUAM = PP.ID_PEGUAM  "+
			 * " AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PG.NAMA_FIRMA IS NOT NULL) "
			 * ;
			 */
			// sql += " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM
			// TBLPPKOBPERMOHONAN WHERE ID_TARAFKPTG NOT IN (1,2,14) ) ";
			// sql += " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM
			// TBLPPKOBPERMOHONAN WHERE ID_TARAFKPTG IN (1,2) ) ";
			// sql += " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM
			// TBLPPKPENGHUTANG ) ";
			// sql += " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM
			// TBLPPKHTAPERMOHONAN ) ";
			// sql += " AND PSM.ID_PERMOHONANSIMATI IN ( SELECT ID_PERMOHONANSIMATI FROM
			// TBLPPKHAPERMOHONAN ) ";
			// CLOSE CONDITION SEMENTARA

			sql += " GROUP BY  CHECK_TP.ID_PEGAWAIBARU, CHECK_TP.NAMA_PEGAWAI_BARU,F.ID_NEGERI,PR.FLAG_BANTAHAN,P.ID_STATUS, STP.KETERANGAN, PRMAX.MAX_BIL_BICARA, PH.FLAG_JENIS_KEPUTUSAN, PH.FLAG_TANGGUH, PH.FLAG_BATAL, P.SEKSYEN, "
					+ " PR.ID_UNITPSK, PR.BIL_BICARA, SM.ID_SIMATI, PM.ID_PEMOHON, PM.NAMA_PEMOHON, PR.ID_PERBICARAAN, P.TARIKH_MOHON, P.ID_PERMOHONAN, "
					+ " P.ID_FAIL, F.NO_FAIL, SM.NAMA_SIMATI,PR.MASA_BICARA, PR.JENIS_MASA_BICARA,PR.TARIKH_BICARA,UP.NAMA_PEGAWAI,PSM.ID_PERMOHONANSIMATI ";
			sql += " ORDER BY  " + " PR.TARIKH_BICARA DESC " + " ";

			sql += " ,(CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' AM' "
					+ " WHEN PR.JENIS_MASA_BICARA = '2' THEN ' PM'  "
					+ " WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PM' ELSE 'AM' END), "
					+ " CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN   "
					+ " (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' ||  "
					+ " CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END  "
					+ " WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0'  "
					+ " ELSE PR.MASA_BICARA END)  "
					+ " WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3)  "
					+ " ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' AM'  "
					+ " WHEN PR.JENIS_MASA_BICARA = '2' THEN ' PM'  "
					+ " WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PM' ELSE ' AM' END) ASC ";

			myLogger.info(" BICARA INTERAKTIF : SQL listPerbicaraan :" + sql);

			rs = stmt.executeQuery(sql);
			listPerbicaraan = Collections.synchronizedList(new ArrayList());

			if (flagDashboard.equals("")) {
				// RETURN PARAM CARIAN
				this.context.put("carianBicaraNO_FAIL", carianBicaraNO_FAIL);
				this.context.put("carianBicaraNAMA_SIMATI", carianBicaraNAMA_SIMATI);
				this.context.put("carianBicaraPENGENALAN_SIMATI", carianBicaraPENGENALAN_SIMATI);
				this.context.put("carianBicaraNAMA_PEMOHON", carianBicaraNAMA_PEMOHON);
				this.context.put("carianBicaraPENGENALAN_PEMOHON", carianBicaraPENGENALAN_PEMOHON);
				this.context.put("carianBicaraID_NEGERIBICARA", carianBicaraID_NEGERIBICARA);
				this.context.put("carianBicaraNAMA_PEGAWAIBICARA", carianBicaraNAMA_PEGAWAIBICARA);
				this.context.put("carianBicaraTARIKH_BICARAMULA", carianBicaraTARIKH_BICARAMULA);
				this.context.put("carianBicaraTARIKH_BICARAAKHIR", carianBicaraTARIKH_BICARAAKHIR);
				this.context.put("carianBicaraBIL_BICARA", carianBicaraBIL_BICARA);
				this.context.put("carianBicaraSTATUS_PERBICARAAN", carianBicaraSTATUS_PERBICARAAN);
			}

			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ((bil % 2) == 0) {
					rowCss = "row2";
				} else {
					rowCss = "row1";
				}
				listPerbicaraan.add(modelBI.getHashMapPerbicaraan(session, rs, rowCss, bil + "", db, ""));
			}

		} finally {
			/*
			 * if (rs != null) { rs.close(); } if (stmt != null) { stmt.close(); }
			 */
			if (db == null) {
				db1.close();
			}
		}
		return listPerbicaraan;

	}

	public List listPermohonanTukarPegawai(HttpSession session, String user_id, String id_jawatan_login,
			String id_negeri_login, String flagCari, String flagDashboard, String id_perbicaraan,
			String id_tukarpegawai, Db db) throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPermohonanTukarPegawai = null;
		String sql = "";

		// GET PARAM CARIAN
		String carianTukarPegawaiNO_FAIL = "";
		String carianTukarPegawaiNO_TUKARPEGAWAI = "";
		String carianTukarPegawaiID_NEGERIMHN = "";
		String carianTukarPegawaiID_NEGERIPEGAWAIBARU = "";
		String carianTukarPegawaiID_PEGAWAIASAL = "";
		String carianTukarPegawaiID_PEGAWAIBARU = "";
		String carianTukarPegawaiNAMAPEGAWAIASAL = "";
		String carianTukarPegawaiNAMAPEGAWAIBARU = "";
		String carianTukarPegawaiSTATUS_TUKARPEGAWAI = "";
		String carianTukarPegawaiTARIKH_MOHONMULA = "";
		String carianTukarPegawaiTARIKH_MOHONAKHIR = "";
		String carianTukarPegawaiTARIKH_BICARAMULA = "";
		String carianTukarPegawaiTARIKH_BICARAAKHIR = "";

		try {

			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			stmt = db1.getStatement();
			sql += " SELECT * FROM (SELECT  " +
			// " (CASE WHEN TP.STATUS_TUKARPEGAWAI = '1' AND (PR.TARIKH_BICARA < SYSDATE OR
			// PH.FLAG_JENIS_KEPUTUSAN IN (0,1,2)) THEN 'Y' ELSE '' END) AS PERBICARAAN_EXP,
			// "+
					" (CASE WHEN TP.ID_NEGERIPEGAWAIASAL != TP.ID_NEGERIPEGAWAIBARU THEN 'Y' ELSE '' END) AS KPP_HQ, N.NAMA_NEGERI AS NAMA_NEGERI_PEGAWAI_GANTI,"
					+ " (CASE WHEN TP.STATUS_TUKARPEGAWAI = '1' THEN 'PERMOHONAN BARU'  "
					+ " WHEN TP.STATUS_TUKARPEGAWAI = '2' THEN 'LULUS'  "
					+ " WHEN TP.STATUS_TUKARPEGAWAI = '3' THEN 'TOLAK' ELSE '' END) AS KETERANGAN_STATUS_TUKARPEGAWAI, "
					+ " F.NO_FAIL,R_A.NAMA_PEGAWAI AS NAMA_PEGAWAI_ASAL, R_B.NAMA_PEGAWAI AS NAMA_PEGAWAI_BARU, "
					+ " CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN   "
					+ " (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' ||   "
					+ " CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END  "
					+ " WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0'  "
					+ " ELSE PR.MASA_BICARA END)  "
					+ " WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3)  "
					+ " ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' PAGI'  "
					+ " WHEN PR.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI'  "
					+ " WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, PR.BIL_BICARA, "
					+ " TO_CHAR(PR.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, PH.FLAG_JENIS_KEPUTUSAN, "
					+ " (CASE WHEN PH.FLAG_JENIS_KEPUTUSAN = '0' THEN 'SELESAI' "
					+ " WHEN PH.FLAG_JENIS_KEPUTUSAN = '1' THEN 'TANGGUH' "
					+ " WHEN PH.FLAG_JENIS_KEPUTUSAN = '2' THEN 'BATAL' ELSE 'PROSES PERBICARAAN' END) AS STATUS_BICARA, P.ID_NEGERIMHN, P.ID_PERMOHONAN, SM.ID_PERMOHONANSIMATI, SM.ID_SIMATI, "
					+ " TP.*  FROM TBLRUJNEGERI N,TBLPPKTUKARPEGAWAI TP, TBLPFDFAIL F, TBLPPKRUJUNIT R_A, TBLPPKRUJUNIT R_B, TBLPPKPERBICARAAN PR, TBLPPKPERINTAH PH, TBLPPKPERMOHONAN P, "
					+ " TBLPPKPERMOHONANSIMATI SM "
					+ " WHERE TP.ID_FAIL = F.ID_FAIL AND TP.ID_NEGERIPEGAWAIBARU = N.ID_NEGERI "
					+ " AND TP.ID_PEGAWAIASAL = R_A.ID_UNITPSK " + " AND TP.ID_PEGAWAIBARU = R_B.ID_UNITPSK "
					+ " AND TP.ID_PERBICARAAN = PR.ID_PERBICARAAN " + " AND P.ID_PERMOHONAN = SM.ID_PERMOHONAN "
					+ " AND F.ID_FAIL = P.ID_FAIL " + " AND PR.ID_PERBICARAAN = PH.ID_PERBICARAAN(+) ";

			/*
			 * this.context.put("carianTukarPegawaiNO_FAIL",carianTukarPegawaiNO_FAIL);
			 * this.context.put("carianTukarPegawaiID_NEGERIMOHON",
			 * carianTukarPegawaiID_NEGERIMOHON);
			 * this.context.put("carianTukarPegawaiID_NEGERIPEGAWAIASAL",
			 * carianTukarPegawaiID_NEGERIPEGAWAIASAL);
			 * this.context.put("carianTukarPegawaiID_PEGAWAIASAL",
			 * carianTukarPegawaiID_PEGAWAIASAL);
			 * this.context.put("carianTukarPegawaiID_PEGAWAIBARU",
			 * carianTukarPegawaiID_PEGAWAIBARU);
			 * this.context.put("carianTukarPegawaiSTATUS_TUKARPEGAWAI",
			 * carianTukarPegawaiSTATUS_TUKARPEGAWAI);
			 */
			if (!id_perbicaraan.equals("")) {
				sql += " AND PR.ID_PERBICARAAN = '" + id_perbicaraan + "' ";
				if (!id_tukarpegawai.equals("")) {
					sql += " AND TP.ID_TUKARPEGAWAI NOT IN (" + id_tukarpegawai + ") ";
				}
			} else {
				if (flagCari.equals("Y")) {
					carianTukarPegawaiNO_TUKARPEGAWAI = getParam("carianTukarPegawaiNO_TUKARPEGAWAI");
					carianTukarPegawaiNO_FAIL = getParam("carianTukarPegawaiNO_FAIL");
					carianTukarPegawaiID_NEGERIMHN = getParam("carianTukarPegawaiID_NEGERIMHN");
					carianTukarPegawaiID_NEGERIPEGAWAIBARU = getParam("carianTukarPegawaiID_NEGERIPEGAWAIBARU");
					carianTukarPegawaiID_PEGAWAIASAL = getParam("carianTukarPegawaiID_PEGAWAIASAL");
					carianTukarPegawaiID_PEGAWAIBARU = getParam("carianTukarPegawaiID_PEGAWAIBARU");
					carianTukarPegawaiNAMAPEGAWAIASAL = getParam("carianTukarPegawaiNAMAPEGAWAIASAL");
					carianTukarPegawaiNAMAPEGAWAIBARU = getParam("carianTukarPegawaiNAMAPEGAWAIBARU");
					carianTukarPegawaiSTATUS_TUKARPEGAWAI = getParam("carianTukarPegawaiSTATUS_TUKARPEGAWAI");
					carianTukarPegawaiTARIKH_MOHONMULA = getParam("carianTukarPegawaiTARIKH_MOHONMULA");
					carianTukarPegawaiTARIKH_MOHONAKHIR = getParam("carianTukarPegawaiTARIKH_MOHONAKHIR");
					carianTukarPegawaiTARIKH_BICARAMULA = getParam("carianTukarPegawaiTARIKH_BICARAMULA");
					carianTukarPegawaiTARIKH_BICARAAKHIR = getParam("carianTukarPegawaiTARIKH_BICARAAKHIR");

					if (!carianTukarPegawaiNO_FAIL.equals("")) {
						sql += " AND UPPER(F.NO_FAIL) LIKE '%" + carianTukarPegawaiNO_FAIL.toUpperCase() + "%' ";
					}
					if (!carianTukarPegawaiNO_TUKARPEGAWAI.equals("")) {
						sql += " AND UPPER(TP.NO_TUKARPEGAWAI) LIKE '%"
								+ carianTukarPegawaiNO_TUKARPEGAWAI.toUpperCase() + "%' ";
					}
					if (!carianTukarPegawaiNAMAPEGAWAIASAL.equals("")) {
						sql += " AND UPPER(R_A.NAMA_PEGAWAI) LIKE '%" + carianTukarPegawaiNAMAPEGAWAIASAL.toUpperCase()
								+ "%' ";
					}
					if (!carianTukarPegawaiNAMAPEGAWAIBARU.equals("")) {
						sql += " AND UPPER(R_B.NAMA_PEGAWAI) LIKE '%" + carianTukarPegawaiNAMAPEGAWAIBARU.toUpperCase()
								+ "%' ";
					}

					// this.context.put("carianTukarPegawaiNAMAPEGAWAIASAL",carianTukarPegawaiNAMAPEGAWAIASAL);
					// this.context.put("carianTukarPegawaiNAMAPEGAWAIBARU",carianTukarPegawaiNAMAPEGAWAIBARU);

					/*
					 * if(!carianTukarPegawaiID_PEGAWAIASAL.equals("")) { sql +=
					 * " AND TP.ID_PEGAWAIASAL = '"+carianTukarPegawaiID_PEGAWAIASAL+"' "; }
					 * if(!carianTukarPegawaiID_PEGAWAIBARU.equals("")) { sql +=
					 * " AND TP.ID_PEGAWAIBARU = '"+carianTukarPegawaiID_PEGAWAIBARU+"' "; }
					 */

					if (!carianTukarPegawaiTARIKH_MOHONMULA.equals("")
							&& !carianTukarPegawaiTARIKH_MOHONAKHIR.equals("")) {
						sql += " AND TP.TARIKH_MOHON BETWEEN TO_DATE('" + carianTukarPegawaiTARIKH_MOHONMULA
								+ "','DD/MM/YYYY') AND TO_DATE('" + carianTukarPegawaiTARIKH_MOHONAKHIR
								+ "','DD/MM/YYYY') ";
					}
					if (!carianTukarPegawaiTARIKH_MOHONMULA.equals("")
							&& carianTukarPegawaiTARIKH_MOHONAKHIR.equals("")) {
						sql += " AND TP.TARIKH_MOHON > TO_DATE('" + carianTukarPegawaiTARIKH_MOHONMULA
								+ "','DD/MM/YYYY') ";
					}
					if (carianTukarPegawaiTARIKH_MOHONMULA.equals("")
							&& !carianTukarPegawaiTARIKH_MOHONAKHIR.equals("")) {
						sql += " AND TP.TARIKH_MOHON < TO_DATE('" + carianTukarPegawaiTARIKH_MOHONAKHIR
								+ "','DD/MM/YYYY') ";
					}

					if (!carianTukarPegawaiTARIKH_BICARAMULA.equals("")
							&& !carianTukarPegawaiTARIKH_BICARAAKHIR.equals("")) {
						sql += " AND PR.TARIKH_BICARA BETWEEN TO_DATE('" + carianTukarPegawaiTARIKH_BICARAMULA
								+ "','DD/MM/YYYY') AND TO_DATE('" + carianTukarPegawaiTARIKH_BICARAAKHIR
								+ "','DD/MM/YYYY') ";
					}
					if (!carianTukarPegawaiTARIKH_BICARAMULA.equals("")
							&& carianTukarPegawaiTARIKH_BICARAAKHIR.equals("")) {
						sql += " AND PR.TARIKH_BICARA > TO_DATE('" + carianTukarPegawaiTARIKH_BICARAMULA
								+ "','DD/MM/YYYY') ";
					}
					if (carianTukarPegawaiTARIKH_BICARAMULA.equals("")
							&& !carianTukarPegawaiTARIKH_BICARAAKHIR.equals("")) {
						sql += " AND PR.TARIKH_BICARA < TO_DATE('" + carianTukarPegawaiTARIKH_BICARAAKHIR
								+ "','DD/MM/YYYY') ";
					}

					if (!carianTukarPegawaiSTATUS_TUKARPEGAWAI.equals("")) {
						sql += " AND TP.STATUS_TUKARPEGAWAI = '" + carianTukarPegawaiSTATUS_TUKARPEGAWAI + "' ";
					}
					if (!carianTukarPegawaiSTATUS_TUKARPEGAWAI.equals("")) {
						sql += " AND TP.STATUS_TUKARPEGAWAI = '" + carianTukarPegawaiSTATUS_TUKARPEGAWAI + "' ";
					}
					if (!carianTukarPegawaiID_NEGERIMHN.equals("")) {
						sql += " AND P.ID_NEGERIMHN = '" + carianTukarPegawaiID_NEGERIMHN + "' ";
					}
					if (!carianTukarPegawaiID_NEGERIPEGAWAIBARU.equals("")) {
						sql += " AND TP.ID_NEGERIPEGAWAIBARU = '" + carianTukarPegawaiID_NEGERIPEGAWAIBARU + "' ";
					}
				} else {
					sql += " AND TP.STATUS_TUKARPEGAWAI = '1' ";
				}

				if (id_jawatan_login.equals("5") || id_jawatan_login.equals("4")) {
					if (!id_negeri_login.equals("16")) {
						sql += " AND TP.ID_NEGERIPEGAWAIBARU = '" + id_negeri_login + "' ";
					}
				} else {
					sql += " AND TP.ID_TUKARPEGAWAI = '' ";
				}
			}

			sql += " ORDER BY TP.STATUS_TUKARPEGAWAI ASC, TP.TARIKH_MOHON DESC, PR.TARIKH_BICARA DESC) ";

			myLogger.info(" BICARA INTERAKTIF : SQL listPermohonanTukarPegawai :" + sql);

			rs = stmt.executeQuery(sql);
			listPermohonanTukarPegawai = Collections.synchronizedList(new ArrayList());

			// RETURN PARAM CARIAN
			if (flagDashboard.equals("")) {
				this.context.put("carianTukarPegawaiNO_TUKARPEGAWAI", carianTukarPegawaiNO_TUKARPEGAWAI);
				this.context.put("carianTukarPegawaiNO_FAIL", carianTukarPegawaiNO_FAIL);
				this.context.put("carianTukarPegawaiID_NEGERIMHN", carianTukarPegawaiID_NEGERIMHN);
				this.context.put("carianTukarPegawaiID_NEGERIPEGAWAIBARU", carianTukarPegawaiID_NEGERIPEGAWAIBARU);
				this.context.put("carianTukarPegawaiID_PEGAWAIASAL", carianTukarPegawaiID_PEGAWAIASAL);
				this.context.put("carianTukarPegawaiID_PEGAWAIBARU", carianTukarPegawaiID_PEGAWAIBARU);
				this.context.put("carianTukarPegawaiNAMAPEGAWAIASAL", carianTukarPegawaiNAMAPEGAWAIASAL);
				this.context.put("carianTukarPegawaiNAMAPEGAWAIBARU", carianTukarPegawaiNAMAPEGAWAIBARU);
				this.context.put("carianTukarPegawaiSTATUS_TUKARPEGAWAI", carianTukarPegawaiSTATUS_TUKARPEGAWAI);
				this.context.put("carianTukarPegawaiTARIKH_MOHONMULA", carianTukarPegawaiTARIKH_MOHONMULA);
				this.context.put("carianTukarPegawaiTARIKH_MOHONAKHIR", carianTukarPegawaiTARIKH_MOHONAKHIR);
				this.context.put("carianTukarPegawaiTARIKH_BICARAMULA", carianTukarPegawaiTARIKH_BICARAMULA);
				this.context.put("carianTukarPegawaiTARIKH_BICARAAKHIR", carianTukarPegawaiTARIKH_BICARAAKHIR);
			}

			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";

				if ((bil % 2) == 0) {
					rowCss = "row2";
				} else {
					rowCss = "row1";
				}
				h.put("rowCss", rowCss);
				h.put("BIL", bil);
				h.put("NO_TUKARPEGAWAI", rs == null ? ""
						: rs.getString("NO_TUKARPEGAWAI") == null ? "" : rs.getString("NO_TUKARPEGAWAI"));
				h.put("ID_FAIL", rs == null ? "" : rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NAMA_NEGERI_PEGAWAI_GANTI",
						rs == null ? ""
								: rs.getString("NAMA_NEGERI_PEGAWAI_GANTI") == null ? ""
										: rs.getString("NAMA_NEGERI_PEGAWAI_GANTI"));
				h.put("ID_PERMOHONAN",
						rs == null ? "" : rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_PERMOHONANSIMATI", rs == null ? ""
						: rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
				h.put("ID_SIMATI",
						rs == null ? "" : rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("ID_TUKARPEGAWAI", rs == null ? ""
						: rs.getString("ID_TUKARPEGAWAI") == null ? "" : rs.getString("ID_TUKARPEGAWAI"));
				h.put("KETERANGAN_STATUS_TUKARPEGAWAI",
						rs == null ? ""
								: rs.getString("KETERANGAN_STATUS_TUKARPEGAWAI") == null ? ""
										: rs.getString("KETERANGAN_STATUS_TUKARPEGAWAI"));
				h.put("NO_FAIL", rs == null ? "" : rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NAMA_PEGAWAI_ASAL",
						rs == null ? ""
								: rs.getString("NAMA_PEGAWAI_ASAL") == null ? ""
										: rs.getString("NAMA_PEGAWAI_ASAL").toUpperCase());
				h.put("NAMA_PEGAWAI_BARU",
						rs == null ? ""
								: rs.getString("NAMA_PEGAWAI_BARU") == null ? ""
										: rs.getString("NAMA_PEGAWAI_BARU").toUpperCase());
				h.put("MASA_BICARA",
						rs == null ? "" : rs.getString("MASA_BICARA") == null ? "" : rs.getString("MASA_BICARA"));
				h.put("TARIKH_BICARA",
						rs == null ? "" : rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA"));
				h.put("BIL_BICARA",
						rs == null ? "" : rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
				h.put("STATUS_BICARA",
						rs == null ? "" : rs.getString("STATUS_BICARA") == null ? "" : rs.getString("STATUS_BICARA"));
				h.put("ID_PERBICARAAN",
						rs == null ? "" : rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
				// h.put("PERBICARAAN_EXP",rs == null ? "" :rs.getString("PERBICARAAN_EXP") ==
				// null ? "" : rs.getString("PERBICARAAN_EXP"));

				h.put("ID_PEMOHON",
						rs == null ? "" : rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("ID_PEGAWAIASAL",
						rs == null ? "" : rs.getString("ID_PEGAWAIASAL") == null ? "" : rs.getString("ID_PEGAWAIASAL"));
				h.put("ID_PEGAWAIBARU",
						rs == null ? "" : rs.getString("ID_PEGAWAIBARU") == null ? "" : rs.getString("ID_PEGAWAIBARU"));
				h.put("ID_NEGERIPEGAWAIASAL", rs == null ? ""
						: rs.getString("ID_NEGERIPEGAWAIASAL") == null ? "" : rs.getString("ID_NEGERIPEGAWAIASAL"));
				h.put("ID_NEGERIPEGAWAIBARU", rs == null ? ""
						: rs.getString("ID_NEGERIPEGAWAIBARU") == null ? "" : rs.getString("ID_NEGERIPEGAWAIBARU"));
				h.put("USER_ID_PEGAWAIBARU", rs == null ? ""
						: rs.getString("USER_ID_PEGAWAIBARU") == null ? "" : rs.getString("USER_ID_PEGAWAIBARU"));
				h.put("USER_ID_PEGAWAIASAL", rs == null ? ""
						: rs.getString("USER_ID_PEGAWAIASAL") == null ? "" : rs.getString("USER_ID_PEGAWAIASAL"));
				h.put("STATUS_TUKARPEGAWAI", rs == null ? ""
						: rs.getString("STATUS_TUKARPEGAWAI") == null ? "" : rs.getString("STATUS_TUKARPEGAWAI"));
				h.put("ID_PELULUS",
						rs == null ? "" : rs.getString("ID_PELULUS") == null ? "" : rs.getString("ID_PELULUS"));
				h.put("CATATAN_PEMOHON", rs == null ? ""
						: rs.getString("CATATAN_PEMOHON") == null ? "" : rs.getString("CATATAN_PEMOHON"));
				h.put("CATATAN_PELULUS", rs == null ? ""
						: rs.getString("CATATAN_PELULUS") == null ? "" : rs.getString("CATATAN_PELULUS"));

				listPermohonanTukarPegawai.add(h);
			}

		} finally {
			/*
			 * if (rs != null) { rs.close(); } if (stmt != null) { stmt.close(); }
			 */
			if (db == null) {
				db1.close();
			}
		}

		return listPermohonanTukarPegawai;
	}

	public String setupSkrinSenarai(HttpSession session, String skrinName, String command, String id, String formName,
			String divViewMaklumat, String table_name, String field_main_PK, String id_perbicaraan, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinBantahan(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			htmlPageSetup += "<a class=\"blue\" href=\"javascript:doDivAjaxCall" + formName + "('view_" + skrinName
					+ "','refresh_bantahan','NAMA_TABLE=" + table_name + "&FIELD_PK=" + field_main_PK + "&ID_SIMATI="
					+ ID_SIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
					+ "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&skrinName=" + skrinName
					+ "&scrolPosition='+getPageLocation());\"><u>'Refresh'</u></a>";
			htmlPageSetup += "<fieldset>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERBICARAAN", "",
					"hidden", "", "", "", ID_PERBICARAAN, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Status Bantahan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"FLAG_BANTAHAN", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName,
					"", 0, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Keterangan Bantahan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"KETERANGAN_BANTAHAN", "Y", "text", "Y", "4000", "Y", "", 0, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "</fieldset>";
			htmlPageSetup += modelBI.setupButtonBantahan(session, "", jenis_transaction, setupSkrinHistory,
					field_main_PK, id, current_previous, skrinName, formName, flag_editable, mode, setupSkrin,
					table_name, divViewMaklumat, paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinPemohon(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			htmlPageSetup += "<a class=\"blue\" href=\"javascript:doDivAjaxCall" + formName + "('view_" + skrinName
					+ "','showMaklumat','NAMA_TABLE=" + table_name + "&FIELD_PK=" + field_main_PK + "&ID_SIMATI="
					+ ID_SIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
					+ "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&skrinName=" + skrinName
					+ "&scrolPosition='+getPageLocation());\"><u>'Refresh'</u></a>";
			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_OB", "", "hidden", "",
					"", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_OBPERMOHONAN", "",
					"hidden", "", "", "", "", 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", "",
					"hidden", "", "", "", "", 3, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 4, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 5, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Taraf Kepentingan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_TARAFKPTG", "Y", "select", "Y", "TBLPPKRUJTARAFKPTG", "ID_TARAFKPTG", "KOD", "KETERANGAN", "",
					"", "div" + skrinName + "STATUS_PEMOHON", "", "STATUS_PEMOHON", "", "", "", "", formName, "", 6,
					db1);
			htmlPageSetup += modelBI.setRowSelect(
					session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin,
					"Jenis Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_PEMOHON", "Y",
					"select", "Y", "", "", "", "", "", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin,
							table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_TARAFKPTG", db1),
					"", "", "", "", "", "", "", formName, "", 7, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "ARB / Baitulmal", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_ARB", "", "select", "Y", "TBLRUJPEJABAT", "ID_PEJABAT", "KOD_PEJABAT", "NAMA_PEJABAT",
					"ID_JENISPEJABAT", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_TARAFKPTG", db1),
					"", "", "", "", "", "", "", formName, "", 8, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Baru", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_BARU", "",
					"numbersOnly", "Y", "12", "Y", "", 9, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAMA", "",
					"text", "Y", "12", "Y", "", 10, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KP", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "", 11,
					db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAIN", "",
					"text", "Y", "12", "Y", "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PEMOHON", "Y", "text", "Y", "200", "Y", "", 13, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_LAIN", "",
					"text", "Y", "200", "Y", "", 14, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jantina", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JANTINA",
					"", "select", "Y", "", "", "", "", "", "", "div" + skrinName + "ID_SAUDARA", "TBLPPKRUJSAUDARA",
					"ID_SAUDARA", "JANTINA", "ID_SAUDARA", "KOD", "KETERANGAN", formName, "", 15, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Talian Persaudaraan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_SAUDARA", "", "select", "Y", "TBLPPKRUJSAUDARA", "ID_SAUDARA", "KOD", "KETERANGAN", "JANTINA",
					modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "JANTINA", db1),
					"", "", "", "", "", "", "", formName, "", 16, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Agama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENIS_AGAMA",
					"", "select", "Y", "TBLRUJAGAMA", "ID_AGAMA", "KOD_AGAMA", "KETERANGAN", "", "", "", formName, "",
					"", "", "", "", "", "", 17, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Warganegara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_WARGA", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "",
					18, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Umur", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", "",
					"numbersOnly", "Y", "5", "Y", "", 19, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_1",
					"Y", "text", "Y", "150", "Y", "", 20, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_2",
					"", "text", "Y", "150", "Y", "", 21, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_3",
					"", "text", "Y", "150", "Y", "", 22, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD", "Y", "numbersOnly", "Y", "5", "Y", "", 23, db1);
			// info parameters
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, // namaSkrin
					command, // command
					mode, // view mode
					setupSkrin, // nama table
					"Negeri Alamat Tetap", // label
					table_name, // main table
					field_main_PK, // nama field PK main table
					value_main_PK, // value field PK main table
					ID_PERBICARAAN, // id perbicaraan
					"ID_NEGERI", // column nam dalam main table
					"Y", // flag mandatory 'Y'
					"select", // jenis field
					"Y", // flag 'Y' if nak show ':'
					"TBLRUJNEGERI", // nama table rujukan untuk drop down ini *kalo tiada condsider as hardcode
									// dropdown
					"ID_NEGERI", // primary key utk table dropdown ini
					"KOD_NEGERI", // masukkan nama field kod kalo nak display kod
					"NAMA_NEGERI", // nama field utk keterangan drop down
					"", // nama field FK untuk dropdown ini - contoh nak keluarkan list bandar by
						// id_negeri
					"", // value FK untuk dropdown ini - contoh nak keluarkan list bandar by id_negeri
					"div" + skrinName + "ID_BANDAR", // ajaxCall :: div load ajax, kalo nk trigger ajax call utk call
														// dropdown yg lain
					"TBLRUJBANDAR", // ajaxCall :: table rujukan yg kita nak tarik data untuk ajax call
					"ID_BANDAR", // ajaxCall :: nama & id field
					"ID_NEGERI", // ajaxCall :: FK field utk kita call list data
					"ID_BANDAR", // ajaxCall :: PK table yg kita nak load
					"KOD_BANDAR", // ajaxCall :: field kod table yg kita display
					"KETERANGAN", // ajaxCall :: field keterangan yg kita display
					formName, "", // default value for insert
					24, // turutan field, untuk display masa rekod perubahan
					db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDAR", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
					"", "", "", "", "", "", "", formName, "", 25, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA", "", "text", "Y", "200", "Y", "", 26, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			if (mode.equals("edit")) {
				htmlPageSetup += "<tr><td></td><td></td><td></td><td valign='top' align='left'><input type='checkbox' id='salinAlamat"
						+ skrinName + "' name='salinAlamat" + skrinName + "' onClick=\"salinAlamatTetap('" + skrinName
						+ "')\" >  <font color='blue'><i>Alamat surat menyurat adalah alamat tetap<i></font></td></tr>";
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT1_SURAT", "Y", "text", "Y", "150", "Y", "", 27, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT2_SURAT", "", "text", "Y", "150", "Y", "", 28, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT3_SURAT", "", "text", "Y", "150", "Y", "", 29, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD_SURAT", "Y",
					"numbersOnly", "Y", "5", "Y", "", 30, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERISURAT", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "",
					"", "div" + skrinName + "ID_BANDARSURAT", "TBLRUJBANDAR", "ID_BANDARSURAT", "ID_NEGERI",
					"ID_BANDAR", "KOD_BANDAR", "KETERANGAN", formName, "", 31, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDARSURAT", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERISURAT", db1),
					"", "", "", "", "", "", "", formName, "", 32, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA_SURAT", "", "text", "Y", "200", "Y", "", 33, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL_SURAT",
					"", "numbersOnly", "Y", "14", "Y", "", 34, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon Bimbit", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NO_HP_SURAT", "", "numbersOnly", "Y", "14", "Y", "", 35, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Emel", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "EMEL", "", "text",
					"Y", "50", "", "", 36, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN", "",
					"text", "Y", "2000", "Y", "", 37, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();
			htmlPageSetup += modelBI.setupButton(session, "", jenis_transaction, setupSkrinHistory, field_main_PK, id,
					current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name, divViewMaklumat,
					paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinPenghutang(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);

			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PENGHUTANG", "",
					"hidden", "", "", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 3, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Penghutang", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_PENGHUTANG", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "",
					formName, "", 4, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Baru", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_BARU", "",
					"numbersOnly", "Y", "12", "Y", "", 5, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAMA", "",
					"text", "Y", "12", "Y", "", 6, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KP", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "", 7,
					db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAIN", "",
					"text", "Y", "12", "Y", "", 8, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Penghutang", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PENGHUTANG", "Y", "text", "Y", "200", "Y", "", 9, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Agama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENIS_AGAMA",
					"", "select", "Y", "TBLRUJAGAMA", "ID_AGAMA", "KOD_AGAMA", "KETERANGAN", "", "", "", formName, "",
					"", "", "", "", "", "", 10, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Warganegara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_WARGA", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "",
					11, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nilai Hutang (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JUMLAH_HUTANG", "Y", "currencyOnly", "Y", "35", "Y", "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Akaun", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_AKAUN", "",
					"text", "Y", "20", "Y", "", 13, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_1", "Y",
					"text", "Y", "150", "Y", "", 14, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_2", "",
					"text", "Y", "150", "Y", "", 15, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_3", "",
					"text", "Y", "150", "Y", "", 16, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD", "Y",
					"numbersOnly", "Y", "5", "Y", "", 17, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI",
					"Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, "", 18, db1);// dynamic ajax call
			htmlPageSetup += modelBI
					.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin,
							"Bandar", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BANDAR", "",
							"select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
							"", "", "", "", "", "", "", formName, "", 19, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Butiran Hutang", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"BUTIRAN_HUTANG", "Y", "text", "Y", "250", "Y", "", 20, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();
			htmlPageSetup += modelBI.setupButton(session, "", jenis_transaction, setupSkrinHistory, field_main_PK, id,
					current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name, divViewMaklumat,
					paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinPeguam(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEGUAM", "", "hidden",
					"", "", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 3, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Firma", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_FIRMA",
					"Y", "text", "Y", "4000", "Y", "", 4, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Rujukan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NO_RUJUKAN_FIRMA", "Y", "text", "Y", "4000", "Y", "", 5, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT1", "Y",
					"text", "Y", "80", "Y", "", 6, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT2", "",
					"text", "Y", "80", "Y", "", 7, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT3", "",
					"text", "Y", "80", "Y", "", 8, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD", "Y",
					"numbersOnly", "Y", "5", "Y", "", 9, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();

			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI",
					"Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, "", 10, db1);// dynamic ajax call
			htmlPageSetup += modelBI
					.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin,
							"Bandar", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BANDAR", "",
							"select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
							"", "", "", "", "", "", "", formName, "", 11, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL", "Y",
					"numbersOnly", "Y", "14", "Y", "", 11, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Faks", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_FAX", "",
					"numbersOnly", "Y", "14", "Y", "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Emel", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "EMEL", "", "text",
					"Y", "50", "", "", 13, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();
			htmlPageSetup += modelBI.setupButton(session, "", jenis_transaction, setupSkrinHistory, field_main_PK, id,
					current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name, divViewMaklumat,
					paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinOb(HttpSession session, String ID_PEMOHON, String jenis_transaction,
			String current_previous, String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory,
			Map setupSkrin, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName,
			String command, String id, String formName, String divViewMaklumat, String table_name, String field_main_PK,
			String mode, String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);

			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_OB", "", "hidden", "",
					"", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_OBPERMOHONAN", "",
					"hidden", "", "", "", "", 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Sebagai Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_PEMOHON", "", "text", "Y", "", "", "", 3, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 4, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 5, db1);
			if (skrinName.equals("ob")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Taraf Kepentingan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_TARAFKPTG", "Y", "select", "Y", "TBLPPKRUJTARAFKPTG", "ID_TARAFKPTG", "KOD",
						"KETERANGAN", "", "", "div" + skrinName + "JENIS_PEMIUTANG", "", "JENIS_PEMIUTANG", "", "", "",
						"", formName, "", 6, db1);
			} else if (skrinName.equals("pemiutang")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Taraf Kepentingan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_TARAFKPTG", "Y", "select", "Y", "TBLPPKRUJTARAFKPTG", "ID_TARAFKPTG", "KOD",
						"KETERANGAN", "", "", "div" + skrinName + "JENIS_PEMIUTANG", "", "JENIS_PEMIUTANG", "", "", "",
						"", formName, "2", 7, db1);
			}
			String label_jenis = "";
			if (skrinName.equals("ob")) {
				label_jenis = "Jenis Ob";
			} else if (skrinName.equals("pemiutang")) {
				label_jenis = "Jenis Pemiutang";
			}
			htmlPageSetup += modelBI.setRowSelect(
					session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin, label_jenis,
					table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENIS_PEMIUTANG", "Y", "select", "Y", "",
					"", "", "", "", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_TARAFKPTG", db1),
					"", "", "", "", "", "", "", formName, "", 8, db1);
			if (skrinName.equals("ob")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "ARB / Baitulmal", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_ARB", "", "select", "Y", "TBLRUJPEJABAT", "ID_PEJABAT", "KOD_PEJABAT",
						"NAMA_PEJABAT", "ID_JENISPEJABAT",
						modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
								value_main_PK, ID_PERBICARAAN, "ID_TARAFKPTG", db1),
						"", "", "", "", "", "", "", formName, "", 9, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Baru", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_BARU", "",
					"numbersOnly", "Y", "12", "Y", "", 10, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAMA", "",
					"text", "Y", "12", "Y", "", 11, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KP", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "", 12,
					db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAIN", "",
					"text", "Y", "12", "Y", "", 13, db1);

			String label_nama = "";
			if (skrinName.equals("ob")) {
				label_nama = "Nama Ob";
			} else if (skrinName.equals("pemiutang")) {
				label_nama = "Nama Pemiutang";
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, label_nama, table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_OB", "Y",
					"text", "Y", "200", "Y", "", 14, db1);
			if (skrinName.equals("ob")) {
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Nama Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_LAIN",
						"", "text", "Y", "200", "Y", "", 15, db1);
			}
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jantina", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JANTINA",
					"", "select", "Y", "", "", "", "", "", "", "div" + skrinName + "ID_SAUDARA", "TBLPPKRUJSAUDARA",
					"ID_SAUDARA", "JANTINA", "ID_SAUDARA", "KOD", "KETERANGAN", formName, "", 16, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Agama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENIS_AGAMA",
					"", "select", "Y", "TBLRUJAGAMA", "ID_AGAMA", "KOD_AGAMA", "KETERANGAN", "", "", "", formName, "",
					"", "", "", "", "", "", 17, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Warganegara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_WARGA", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "",
					18, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Lahir", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_LAHIR", "", "text", "Y", "10", "Y", "", 19, db1);
			if (skrinName.equals("ob")) {
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "No. Surat Beranak", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"NO_SURAT_BERANAK", "", "text", "Y", "10", "Y", "", 20, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Umur", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", "",
					"numbersOnly", "Y", "5", "Y", "", 21, db1);
			if (skrinName.equals("pemiutang")) {
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Nilai Hutang (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"NILAI_HUTANG", "Y", "currencyOnly", "Y", "35", "Y", "", 22, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "No. Akaun", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_AKAUN",
						"", "text", "Y", "20", "Y", "", 23, db1);
			}
			if (skrinName.equals("ob")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Status Ob", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "STATUS_OB", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
						"", formName, "", 24, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_1",
					"Y", "text", "Y", "150", "Y", "", 25, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_2",
					"", "text", "Y", "150", "Y", "", 26, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_3",
					"", "text", "Y", "150", "Y", "", 27, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD", "Y", "numbersOnly", "Y", "5", "Y", "", 28, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERI", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, "", 29, db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDAR", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
					"", "", "", "", "", "", "", formName, "", 30, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA", "", "text", "Y", "200", "Y", "", 31, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			if (mode.equals("edit")) {
				htmlPageSetup += "<tr><td></td><td></td><td></td><td valign='top' align='left'><input type='checkbox' id='salinAlamat"
						+ skrinName + "' name='salinAlamat" + skrinName + "' onClick=\"salinAlamatTetap('" + skrinName
						+ "')\" >  <font color='blue'><i>Alamat surat menyurat adalah alamat tetap<i></font></td></tr>";
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT1_SURAT", "Y", "text", "Y", "150", "Y", "", 32, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT2_SURAT", "", "text", "Y", "150", "Y", "", 33, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT3_SURAT", "", "text", "Y", "150", "Y", "", 34, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD_SURAT", "Y", "numbersOnly", "Y", "5", "Y", "", 35, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERISURAT", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "",
					"", "div" + skrinName + "ID_BANDARSURAT", "TBLRUJBANDAR", "ID_BANDARSURAT", "ID_NEGERI",
					"ID_BANDAR", "KOD_BANDAR", "KETERANGAN", formName, "", 36, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDARSURAT", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERISURAT", db1),
					"", "", "", "", "", "", "", formName, "", 37, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA_SURAT", "", "text", "Y", "200", "Y", "", 38, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL_SURAT",
					"", "numbersOnly", "Y", "14", "Y", "", 39, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon Bimbit", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NO_HP_SURAT", "", "numbersOnly", "Y", "14", "Y", "", 40, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Emel", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "EMEL", "", "text",
					"Y", "50", "", "", 41, db1);
			if (skrinName.equals("pemiutang")) {
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Butiran Hutang", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"BUTIRAN_HUTANG", "Y", "text", "Y", "250", "Y", "", 42, db1);
			} else {
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN", "",
						"text", "Y", "250", "Y", "", 43, db1);
			}
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();

			// get value id_pemohon
			String getValueID_PEMOHON = "";
			String getValueSTATUS_OB = "";
			String getValueSTATUS_MATI = "";
			String getValueSTATUS_UMUR = "";
			Map getChanges = null;
			Map getChangesStatus = null;
			Map getChangesMati = null;
			Map getChangesUmur = null;
			if (setupSkrin != null) {
				// untuk kes kemaskini
				getValueID_PEMOHON = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", db1);
				getChanges = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", db1);
				getValueSTATUS_OB = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_OB", db1);
				getChangesStatus = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "STATUS_OB", db1);
				getValueSTATUS_MATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_HIDUP", db1);
				getChangesMati = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "STATUS_HIDUP", db1);
				getValueSTATUS_UMUR = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", db1);
				getChangesUmur = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "UMUR", db1);
			} else {
				// untuk kes tambah
				getChanges = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", db1);
				if (getChanges == null) {
					getValueID_PEMOHON = "";
				}

				getChangesStatus = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_OB", db1);
				if (getChangesStatus == null) {
					getValueSTATUS_OB = "";
				}

				getChangesMati = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_HIDUP", db1);
				if (getChangesMati == null) {
					getValueSTATUS_MATI = "";
				}
				getChangesUmur = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", db1);
				if (getChangesUmur == null) {
					getValueSTATUS_UMUR = "";
				}
			}

			if (getChanges != null) {
				String VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
				getValueID_PEMOHON = VALUE_SELEPAS;
			}
			if (getChangesStatus != null) {
				String VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
				getValueSTATUS_OB = VALUE_SELEPAS;
			}
			if (getChangesMati != null) {
				String VALUE_SELEPAS = (String) getChangesMati.get("VALUE_SELEPAS");
				getValueSTATUS_MATI = VALUE_SELEPAS;
			}
			if (getChangesUmur != null) {
				String VALUE_SELEPAS = (String) getChangesUmur.get("VALUE_SELEPAS");
				getValueSTATUS_UMUR = VALUE_SELEPAS;
			}

			int int_getValueSTATUS_UMUR = 0;
			if (!getValueSTATUS_UMUR.equals("")) {
				int_getValueSTATUS_UMUR = Integer.parseInt(getValueSTATUS_UMUR);
			}

			String flagLantik = "";
			if (getValueID_PEMOHON.equals("") && !getValueSTATUS_MATI.equals("1")
			// && int_getValueSTATUS_UMUR >= 18
					&& (getValueSTATUS_OB.equals("2") || getValueSTATUS_OB.equals("4"))) {
				flagLantik = "Y";
			}

			Map setupSkrinPemohon = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
					ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKPEMOHON", db1);
			String ID_PEMOHON_MAIN = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinPemohon, "TBLPPKPEMOHON",
					"", "", ID_PERBICARAAN, "ID_PEMOHON", db1);
			htmlPageSetup += modelBI.setupButton(session, flagLantik, jenis_transaction, setupSkrinHistory,
					field_main_PK, id, current_previous, skrinName, formName, flag_editable, mode, setupSkrin,
					table_name, divViewMaklumat, paramsButton, ID_PEMOHON_MAIN, db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinWaris(HttpSession session, String ID_PEMOHON, String jenis_transaction,
			String current_previous, String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory,
			Map setupSkrin, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName,
			String command, String id, String formName, String divViewMaklumat, String table_name, String field_main_PK,
			String mode, String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					id, ID_PERBICARAAN, field_main_PK, db1);
			myLogger.info("****** waris value_main_PK : " + value_main_PK);
			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_OB", "", "hidden", "",
					"", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_OBPERMOHONAN", "",
					"hidden", "", "", "", "", 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Sebagai Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_PEMOHON", "", "hidden", "Y", "", "", "", 3, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 4, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 5, db1);
			if (skrinName.equals("waris")) {
				// special setup utk hubungan attribute table
				htmlPageSetup += modelBI.setRowSelectParentWaris(session, value_main_PK, ID_SEJARAHBIMAIN,
						ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin, "Waris Yang Meninggal",
						ID_PERBICARAAN, "", "Y", formName, "", 6, db);
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Taraf Kepentingan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_TARAFKPTG", "", "select", "Y", "TBLPPKRUJTARAFKPTG", "ID_TARAFKPTG", "KOD",
						"KETERANGAN", "", "", "", "", "", "", "", "", "", formName, "1", 7, db1);
			} else if (skrinName.equals("saksi")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Taraf Kepentingan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_TARAFKPTG", "", "select", "Y", "TBLPPKRUJTARAFKPTG", "ID_TARAFKPTG", "KOD",
						"KETERANGAN", "", "", "", "", "", "", "", "", "", formName, "14", 8, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Baru", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_BARU", "",
					"numbersOnly", "Y", "12", "Y", "", 9, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAMA", "",
					"text", "Y", "12", "Y", "", 10, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KP", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "", 11,
					db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAIN", "",
					"text", "Y", "12", "Y", "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_OB", "Y",
					"text", "Y", "200", "Y", "", 13, db1);
			if (skrinName.equals("waris")) {
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Nama Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_LAIN",
						"", "text", "Y", "200", "Y", "", 14, db1);
			}
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jantina", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JANTINA",
					"", "select", "Y", "", "", "", "", "", "", "div" + skrinName + "ID_SAUDARA", "TBLPPKRUJSAUDARA",
					"ID_SAUDARA", "JANTINA", "ID_SAUDARA", "KOD", "KETERANGAN", formName, "", 15, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Agama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENIS_AGAMA",
					"", "select", "Y", "TBLRUJAGAMA", "ID_AGAMA", "KOD_AGAMA", "KETERANGAN", "", "", "", formName, "",
					"", "", "", "", "", "", 16, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Warganegara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_WARGA", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "",
					17, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Lahir", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_LAHIR", "", "text", "Y", "10", "Y", "", 18, db1);
			if (skrinName.equals("waris")) {
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "No. Surat Beranak", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"NO_SURAT_BERANAK", "", "text", "Y", "10", "Y", "", 19, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Umur", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", "Y",
					"numbersOnly", "Y", "5", "Y", "", 20, db1);
			if (skrinName.equals("waris")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Talian Persaudaraan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_SAUDARA", "Y", "select", "Y", "TBLPPKRUJSAUDARA", "ID_SAUDARA", "KOD",
						"KETERANGAN", "JANTINA",
						modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
								value_main_PK, ID_PERBICARAAN, "JANTINA", db1),
						"", "", "", "", "", "", "", formName, "", 21, db1);
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Status Waris", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "STATUS_OB", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
						"", formName, "", 22, db1);
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Sudah Meninggal Dunia", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "STATUS_HIDUP", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "",
						"", "", formName, "", 23, db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						mode, setupSkrin, "Tarikh Mati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"TARIKH_MATI", "", "text", "Y", "10", "Y", "", 24, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Waktu Kematian (12 jam HHMM)", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "WAKTU_KEMATIAN", "", "numbersOnly", "Y", "4", "Y", "", 25, db1);
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Jenis Waktu Kematian", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "JENIS_WAKTU_KEMATIAN", "", "select", "Y", "", "", "", "", "", "", "", "", "",
						"", "", "", "", formName, "", 26, db1);
			}
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();

			if (mode.equals("edit") && skrinName.equals("waris")) {
				htmlPageSetup += "<tr><td></td><td></td><td></td><td valign='top' align='left'><input type='checkbox' id='salinAlamatPemohon"
						+ skrinName + "' name='salinAlamatPemohon" + skrinName
						+ "' onClick=\"getMapPemohon('divSetupAlamatPemohon" + skrinName + "','" + ID_PERMOHONAN + "','"
						+ ID_PERMOHONANSIMATI + "','" + ID_PERBICARAAN + "','" + skrinName
						+ "');\" > <font color='blue'><i>Alamat waris adalah alamat pemohon<i></font><div id=\"divSetupAlamatPemohon"
						+ skrinName + "\" ></div></td></tr>";
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_1",
					"Y", "text", "Y", "150", "Y", "", 27, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_2",
					"", "text", "Y", "150", "Y", "", 28, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Tetap 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT_3",
					"", "text", "Y", "150", "Y", "", 29, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD", "Y", "numbersOnly", "Y", "5", "Y", "", 30, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERI", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, "", 31, db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDAR", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
					"", "", "", "", "", "", "", formName, "", 32, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Tetap", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA", "", "text", "Y", "200", "Y", "", 33, db1);

			if (mode.equals("edit")) {
				htmlPageSetup += "<tr><td></td><td></td><td></td><td valign='top' align='left'><input type='checkbox' id='salinAlamat"
						+ skrinName + "' name='salinAlamat" + skrinName + "' onClick=\"salinAlamatTetap('" + skrinName
						+ "')\" >  <font color='blue'><i>Alamat surat menyurat adalah alamat tetap<i></font></td></tr>";
			}

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT1_SURAT", "Y", "text", "Y", "150", "Y", "", 33, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT2_SURAT", "", "text", "Y", "150", "Y", "", 34, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Surat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT3_SURAT", "", "text", "Y", "150", "Y", "", 35, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD_SURAT", "Y", "numbersOnly", "Y", "5", "Y", "", 36, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERISURAT", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "",
					"", "div" + skrinName + "ID_BANDARSURAT", "TBLRUJBANDAR", "ID_BANDARSURAT", "ID_NEGERI",
					"ID_BANDAR", "KOD_BANDAR", "KETERANGAN", formName, "", 37, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDARSURAT", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERISURAT", db1),
					"", "", "", "", "", "", "", formName, "", 38, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Surat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA_SURAT", "", "text", "Y", "200", "Y", "", 39, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL_SURAT",
					"", "numbersOnly", "Y", "14", "Y", "", 40, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon Bimbit", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NO_HP_SURAT", "", "numbersOnly", "Y", "14", "Y", "", 41, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Emel", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "EMEL", "", "text",
					"Y", "50", "", "", 42, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN", "",
					"text", "Y", "2000", "Y", "", 43, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Urusan Kemasukkan Maklumat", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "FLAG_DAFTAR", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
					"", formName, "2", 44, db1);
			if (skrinName.equals("waris")) {
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Lapis", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "LAPIS", "",
						"text", "Y", "", "", "1", 45, db1);
			}
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();

			// get value id_pemohon
			String getValueID_PEMOHON = "";
			String getValueSTATUS_OB = "";
			String getValueSTATUS_MATI = "";
			String getValueSTATUS_UMUR = "";
			Map getChanges = null;
			Map getChangesMati = null;
			Map getChangesUmur = null;
			Map getChangesStatus = null;
			if (setupSkrin != null) {
				// untuk kes kemaskini
				getValueID_PEMOHON = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", db1);
				getChanges = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", db1);
				getValueSTATUS_OB = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_OB", db1);
				getChangesStatus = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "STATUS_OB", db1);
				getValueSTATUS_MATI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_HIDUP", db1);
				getChangesMati = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "STATUS_HIDUP", db1);
				getValueSTATUS_UMUR = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", db1);
				getChangesUmur = modelBI.getChanges(session, "", ID_PERMOHONANSIMATI, table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "UMUR", db1);
			} else {
				// untuk kes tambah
				getChanges = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHON", db1);
				if (getChanges == null) {
					getValueID_PEMOHON = "";
				}

				getChangesStatus = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_OB", db1);
				if (getChangesStatus == null) {
					getValueSTATUS_OB = "";
				}

				getChangesMati = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_HIDUP", db1);
				if (getChangesMati == null) {
					getValueSTATUS_MATI = "";
				}
				getChangesUmur = modelBI.getChanges(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, table_name,
						field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR", db1);
				if (getChangesUmur == null) {
					getValueSTATUS_UMUR = "";
				}
			}

			if (getChanges != null) {
				String VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
				getValueID_PEMOHON = VALUE_SELEPAS;
			}
			if (getChangesStatus != null) {
				String VALUE_SELEPAS = (String) getChanges.get("VALUE_SELEPAS");
				getValueSTATUS_OB = VALUE_SELEPAS;
			}
			if (getChangesMati != null) {
				String VALUE_SELEPAS = (String) getChangesMati.get("VALUE_SELEPAS");
				getValueSTATUS_MATI = VALUE_SELEPAS;
			}
			if (getChangesUmur != null) {
				String VALUE_SELEPAS = (String) getChangesUmur.get("VALUE_SELEPAS");
				getValueSTATUS_UMUR = VALUE_SELEPAS;
			}

			int int_getValueSTATUS_UMUR = 0;
			if (!getValueSTATUS_UMUR.equals("")) {
				int_getValueSTATUS_UMUR = Integer.parseInt(getValueSTATUS_UMUR);
			}

			String flagLantik = "";
			if (getValueID_PEMOHON.equals("") && !getValueSTATUS_MATI.equals("1")
			// && int_getValueSTATUS_UMUR >= 18
					&& (getValueSTATUS_OB.equals("2") || getValueSTATUS_OB.equals("4"))) {
				flagLantik = "Y";
			}

			Map setupSkrinPemohon = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
					ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKPEMOHON", db1);
			String ID_PEMOHON_MAIN = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrinPemohon, "TBLPPKPEMOHON",
					"", "", ID_PERBICARAAN, "ID_PEMOHON", db1);
			htmlPageSetup += modelBI.setupButton(session, flagLantik, jenis_transaction, setupSkrinHistory,
					field_main_PK, id, current_previous, skrinName, formName, flag_editable, mode, setupSkrin,
					table_name, divViewMaklumat, paramsButton, ID_PEMOHON_MAIN, db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	public String setupSkrinSimati(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			// Map setupSkrin = modelBI.getValueColumn(session,"", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			htmlPageSetup += "<a class=\"blue\" href=\"javascript:doDivAjaxCall" + formName + "('view_" + skrinName
					+ "','showMaklumat','NAMA_TABLE=" + table_name + "&FIELD_PK=" + field_main_PK + "&ID_SIMATI="
					+ ID_SIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
					+ "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&skrinName=" + skrinName
					+ "&scrolPosition='+getPageLocation());\"><u>'Refresh'</u></a>";
			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Baru", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_BARU", "",
					"numbersOnly", "Y", "12", "Y", "", 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAMA", "",
					"text", "Y", "12", "Y", "", 3, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KP", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "", 4,
					db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "MyID Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_KP_LAIN", "",
					"text", "Y", "12", "Y", "", 5, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Lahir Simati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_LAHIR", "", "text", "Y", "10", "Y", "", 6, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Simati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_SIMATI",
					"Y", "text", "Y", "200", "Y", "", 7, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Lain", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_LAIN", "",
					"text", "Y", "200", "Y", "", 8, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jantina", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JANTINA",
					"", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "", 9, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Agama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENIS_AGAMA",
					"", "select", "Y", "TBLRUJAGAMA", "ID_AGAMA", "KOD_AGAMA", "KETERANGAN", "", "", "", formName, "",
					"", "", "", "", "", "", 10, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Warganegara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_WARGA", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "",
					11, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bukti Kematian", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BUKTIMATI", "Y", "select", "Y", "TBLPPKRUJBUKTIMATI", "ID_BUKTIMATI", "KOD", "KETERANGAN", "",
					"", "", "", "", "", "", "", "", formName, "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Sijil Mati / Permit Menguburkan", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "NO_SIJIL_MATI", "", "text", "Y", "25", "Y", "", 13, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Surat Akuan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_SURAT_AKUAN", "", "text", "Y", "10", "Y", "", 14, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Mati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "TARIKH_MATI",
					"Y", "text", "Y", "10", "Y", "", 15, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Umur Ketika Mati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "UMUR",
					"", "numbersOnly", "Y", "5", "Y", "", 16, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Waktu Kematian (12 jam HHMM)", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "WAKTU_KEMATIAN", "", "numbersOnly", "Y", "4", "Y", "", 17, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Waktu Kematian", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_WAKTU_MATI", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName,
					"", 18, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tempat Mati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "TEMPAT_MATI",
					"Y", "text", "Y", "1000", "Y", "", 19, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Sebab Kematian", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"SEBAB_MATI", "Y", "text", "Y", "400", "Y", "", 20, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Terakhir 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_1", "Y", "text", "Y", "80", "Y", "", 21, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Terakhir 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_2", "", "text", "Y", "80", "Y", "", 22, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Terakhir 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_3", "", "text", "Y", "80", "Y", "", 23, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Terakhir", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD", "Y", "numbersOnly", "Y", "5", "Y", "", 24, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Terakhir", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "ID_NEGERI", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI",
					"NAMA_NEGERI", "", "", "div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI",
					"ID_BANDAR", "KOD_BANDAR", "KETERANGAN", formName, "", 25, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Terakhir", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "ID_BANDAR", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR",
					"KETERANGAN", "ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
					"", "", "", "", "", "", "", formName, "", 26, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Negara Alamat Terakhir", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_PELBAGAINEGARA", "", "text", "Y", "200", "Y", "", 27, db1);

			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN", "",
					"text", "Y", "300", "Y", "", 28, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();
			htmlPageSetup += modelBI.setupButton(session, "", jenis_transaction, setupSkrinHistory, field_main_PK, id,
					current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name, divViewMaklumat,
					paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinHtaah(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_HTAPERMOHONAN", "",
					"hidden", "", "", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_HTA", "", "hidden",
					"", "", "", "", 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 3, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 4, db1);

			String defaultJenisHarta = "Y";
			if (skrinName.equals("htaahx")) {
				defaultJenisHarta = "T";
			}

			String defaultKategoriHta = "";
			if (skrinName.equals("htaahx")) {
				defaultKategoriHta = "1";
			}

			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Harta Tak Alih", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_HTA", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName,
					defaultJenisHarta, 5, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Kategori Harta", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"FLAG_KATEGORI_HTA", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "",
					formName, defaultKategoriHta, 6, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI",
					"Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_DAERAH", "TBLRUJDAERAH", "ID_DAERAH", "ID_NEGERI", "ID_DAERAH",
					"KOD_DAERAH", "NAMA_DAERAH", formName, "", 7, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Daerah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_DAERAH",
					"Y", "select", "Y", "TBLRUJDAERAH", "ID_DAERAH", "KOD_DAERAH", "NAMA_DAERAH", "ID_NEGERI",
					modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_NEGERI", db1),
					"div" + skrinName + "ID_MUKIM", "TBLRUJMUKIM", "ID_MUKIM", "ID_DAERAH", "ID_MUKIM", "KOD_MUKIM",
					"NAMA_MUKIM", formName, "", 8, db1);
			htmlPageSetup += modelBI
					.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin,
							"Mukim", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_MUKIM", "Y",
							"select", "Y", "TBLRUJMUKIM", "ID_MUKIM", "KOD_MUKIM", "NAMA_MUKIM", "ID_DAERAH",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_DAERAH", db1),
							"", "", "", "", "", "", "", formName, "", 9, db1);
			String defaultJenisHakmilik = "";
			if (skrinName.equals("htaahx")) {
				defaultJenisHakmilik = "0";
			}
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Hakmilik", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_JENISHM", "Y", "select", "Y", "TBLRUJJENISHAKMILIK", "ID_JENISHAKMILIK", "KOD_JENIS_HAKMILIK",
					"KETERANGAN", "", "", "", "", "", "", "", "", "", formName, defaultJenisHakmilik, 10, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Hakmilik", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_HAKMILIK",
					"Y", "text", "Y", "50", "Y", "", 11, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Lot / PT", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_PT", "Y",
					"text", "Y", "50", "Y", "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Harta 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_HTA1", "", "text", "Y", "150", "Y", "", 13, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Harta 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_HTA2", "", "text", "Y", "150", "Y", "", 14, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Harta 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_HTA3", "", "text", "Y", "150", "Y", "", 15, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD_HTA", "",
					"numbersOnly", "Y", "5", "Y", "", 16, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Harta", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDARHTA", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
					"", "", "", "", "", "", "", formName, "", 17, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Pemaju", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_PEMAJU",
					"", "text", "Y", "100", "Y", "", 18, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Pemaju 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_PEMAJU1", "", "text", "Y", "150", "Y", "", 19, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Pemaju 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_PEMAJU2", "", "text", "Y", "150", "Y", "", 20, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat Pemaju 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ALAMAT_PEMAJU3", "", "text", "Y", "150", "Y", "", 21, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod Alamat Pemaju", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"POSKOD_PEMAJU", "", "numbersOnly", "Y", "5", "Y", "", 22, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Alamat Pemaju", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERIPEMAJU", "", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "",
					"", "div" + skrinName + "ID_BANDARPEMAJU", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, "", 23, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Bandar Alamat Pemaju", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_BANDARPEMAJU", "", "select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN",
					"ID_NEGERI", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEMAJU", db1),
					"", "", "", "", "", "", "", formName, "", 24, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Perjanjian", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NO_PERJANJIAN", "", "text", "Y", "40", "Y", "", 25, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Perjanjian", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_PERJANJIAN", "", "text", "Y", "10", "Y", "", 26, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Jenis Kepentingan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KEPENTINGAN", "", "text", "Y", "500", "Y", "", 27, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Rancangan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NAMA_RANCANGAN", "", "text", "Y", "80", "Y", "", 28, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. ROH", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_ROH", "",
					"text", "Y", "50", "Y", "", 29, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Lot ID", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_LOT_ID", "",
					"text", "Y", "50", "Y", "", 30, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Sekatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "SEKATAN", "",
					"text", "Y", "1000", "Y", "", 31, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Syarat Nyata", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"SYARAT_NYATA", "", "text", "Y", "300", "Y", "", 32, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr><td align='center' valign='top' >";
			if (mode.equals("edit")) {
				htmlPageSetup += "<font color='red'>*</font>";
			}
			htmlPageSetup += "</td><td align='left' valign='top' >Bahagian Simati</td><td align='center' valign='top'>:</td><td valign='top' align='left'><table width='60%' cellpadding='0' cellspacing='0' border='0'><tr><td width='48%' align='center' valign='top'>";
			htmlPageSetup += modelBI.setRowText(session, "Y", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Bahagian Simati (Atas)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"BA_SIMATI", "Y", "numbersOnly", "Y", "14", "Y", "", 33, db1);
			htmlPageSetup += "</td><td width='4%' align='center' valign='top'>/</td><td width='48%' align='center' valign='top'>";
			htmlPageSetup += modelBI.setRowText(session, "Y", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Bahagian Simati (bawah)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"BB_SIMATI", "Y", "numbersOnly", "Y", "14", "Y", "", 34, db1);
			htmlPageSetup += "</td></tr></table>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Kategori Tanah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_KATEGORI", "", "select", "Y", "TBLRUJKATEGORI", "ID_KATEGORI", "KOD_KATEGORI", "KETERANGAN", "",
					"", "", "", "", "", "", "", "", formName, "", 35, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Luas Asal", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "LUAS", "",
					"text", "Y", "40", "Y", "", 36, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Luas", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_LUAS",
					"", "select", "Y", "TBLRUJLUAS", "ID_LUAS", "KOD_LUAS", "KETERANGAN", "", "", "", "", "", "", "",
					"", "", formName, "", 37, db1);
			htmlPageSetup += "<tr id=\"tr_luasharta" + skrinName
					+ "\" style=\"display:none;\" ><td></td><td></td><td></td><td valign='top' align='left'>";
			htmlPageSetup += "<span id=\"luas1" + skrinName + "\" style=\"display:none;\">";
			htmlPageSetup += "<input name=\"txtLuasAsalHtaam1" + skrinName + "\" type=\"text\" id=\"txtLuasAsalHtaam1"
					+ skrinName
					+ "\" size=\"12\" maxlength=\"15\" onBlur=\"validateAreaBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\"/>";
			htmlPageSetup += "</span>&nbsp;";
			htmlPageSetup += "<span id=\"luas2" + skrinName + "\" style=\"display:none;\">";
			htmlPageSetup += "<input name=\"txtLuasAsalHtaam2" + skrinName + "\" type=\"text\" id=\"txtLuasAsalHtaam2"
					+ skrinName
					+ "\" size=\"12\" maxlength=\"15\" onBlur=\"validateAreaBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\"/>";
			htmlPageSetup += "</span>&nbsp;";
			htmlPageSetup += "<span id=\"luas3" + skrinName + "\" style=\"display:none;\">";
			htmlPageSetup += "<input name=\"txtLuasAsalHtaam3" + skrinName + "\" type=\"text\" id=\"txtLuasAsalHtaam3"
					+ skrinName
					+ "\" size=\"12\" maxlength=\"15\" onBlur=\"validateAreaBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\"/>";
			htmlPageSetup += "</span>&nbsp;";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"tr_luasharta_b" + skrinName
					+ "\" style=\"display:none;\" ><td></td><td></td><td></td><td valign='top' align='left'>";
			htmlPageSetup += "<input type=\"button\" name=\"buttonConvert" + skrinName + "\" id=\"buttonConvert"
					+ skrinName + "\" value=\"Convert\" ";
			htmlPageSetup += " onclick=\"ConvertLuasHartaV6('" + skrinName + "ID_LUAS','" + skrinName + "LUAS','"
					+ skrinName + "LUAS_HMP','meterhektar" + skrinName + "','txtLuasAsalHtaam1" + skrinName
					+ "','txtLuasAsalHtaam2" + skrinName + "','txtLuasAsalHtaam3" + skrinName + "','" + skrinName
					+ "ID_KATEGORI');\" ";
			htmlPageSetup += " />";
			htmlPageSetup += "</td></tr>";

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Luas (Hektar/MP)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"LUAS_HMP", "", "areaOnly", "Y", "40", "Y", "", 38, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nilai Tarikh Mohon (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NILAI_HTA_TARIKHMOHON", "", "currencyOnly", "Y", "35", "Y", "", 39, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nilai Tarikh Mati (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NILAI_HTA_TARIKHMATI", "", "currencyOnly", "Y", "35", "Y", "", 40, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Cagaran", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_CAGARAN",
					"", "text", "Y", "20", "Y", "", 41, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Pajakan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_PAJAKAN",
					"", "text", "Y", "20", "Y", "", 42, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Perserahan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NO_PERSERAHAN", "", "text", "Y", "40", "Y", "", 43, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Status Pemilikan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"STATUS_PEMILIKAN", "", "select", "Y", "TBLRUJJENISPB", "ID_JENISPB", "KOD_JENIS_PB", "KETERANGAN",
					"", "", "", "", "", "", "", "", "", formName, "", 44, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tanggungan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "TANGGUNGAN",
					"", "text", "Y", "50", "Y", "", 45, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Tanah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_TNH", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "",
					46, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN", "",
					"text", "Y", "2000", "Y", "", 47, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Urusan Kemasukkan Maklumat", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "FLAG_DAFTAR", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
					"", formName, "2", 48, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();
			htmlPageSetup += modelBI.setupButton(session, "", jenis_transaction, setupSkrinHistory, field_main_PK, id,
					current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name, divViewMaklumat,
					paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinHa(HttpSession session, String jenis_transaction, String current_previous, String aktiviti,
			String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin, String ID_PERMOHONAN,
			String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command, String id,
			String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			// Map setupSkrin = modelBI.getValueColumn(session, "", id, table_name, db1);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			htmlPageSetup += modelBI.openHTMLTableDivided_top(setupSkrinHistory, jenis_transaction, skrinName, mode);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_HAPERMOHONAN", "",
					"hidden", "", "", "", "", 1, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_HA", "", "hidden", "",
					"", "", "", 2, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERMOHONANSIMATI", "",
					"hidden", "", "", "", ID_PERMOHONANSIMATI, 3, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_SIMATI", "", "hidden",
					"", "", "", ID_SIMATI, 4, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Harta Alih", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_JENISHA", "Y", "select", "Y", "TBLPPKRUJJENISHA", "ID_JENISHA", "KOD", "KETERANGAN", "", "", "",
					"", "", "", "", "", "", formName, "", 5, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI",
					"", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_DAERAH", "TBLRUJDAERAH", "ID_DAERAH", "ID_NEGERI", "ID_DAERAH",
					"KOD_DAERAH", "NAMA_DAERAH", formName, "", 6, db1);
			htmlPageSetup += modelBI
					.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin,
							"Daerah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_DAERAH", "",
							"select", "Y", "TBLRUJDAERAH", "ID_DAERAH", "KOD_DAERAH", "NAMA_DAERAH", "ID_NEGERI",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
							"", "", "", "", "", "", "", formName, "", 7, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Butiran", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "BUTIRAN", "Y",
					"text", "Y", "1000", "Y", "", 8, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Jenis & Jenama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "JENAMA",
					"Y", "text", "Y", "255", "Y", "", 9, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama Saham", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_SAHAM",
					"Y", "text", "Y", "50", "Y", "", 10, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Daftar", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_DAFTAR",
					"Y", "text", "Y", "255", "Y", "", 11, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No. Sijil", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_SIJIL", "",
					"text", "Y", "100", "Y", "", 12, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Bil. Unit", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "BIL_UNIT", "",
					"numbersOnly", "Y", "10", "Y", "", 13, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nilai Seunit (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NILAI_SEUNIT", "", "currencyOnly", "Y", "13", "Y", "", 14, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr><td align='center' valign='top' >";
			if (mode.equals("edit")) {
				htmlPageSetup += "<font color='red'>*</font>";
			}
			htmlPageSetup += "</td><td align='left' valign='top' >Bahagian Simati</td><td align='center' valign='top'>:</td><td valign='top' align='left'><table width='60%' cellpadding='0' cellspacing='0' border='0'><tr><td width='48%' align='center' valign='top'>";
			htmlPageSetup += modelBI.setRowText(session, "Y", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Bahagian Simati (Atas)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"BA_SIMATI", "Y", "numbersOnly", "Y", "14", "Y", "", 15, db1);
			htmlPageSetup += "</td><td width='4%' align='center' valign='top'>/</td><td width='48%' align='center' valign='top'>";
			htmlPageSetup += modelBI.setRowText(session, "Y", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Bahagian Simati (bawah)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"BB_SIMATI", "Y", "numbersOnly", "Y", "14", "Y", "", 16, db1);
			htmlPageSetup += "</td></tr></table>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nilai Tarikh Mohon (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NILAI_HA_TARIKHMOHON", "", "currencyOnly", "Y", "35", "Y", "", 17, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nilai Tarikh Mati (RM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"NILAI_HA_TARIKHMATI", "", "currencyOnly", "Y", "35", "Y", "", 18, db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN", "",
					"text", "Y", "1000", "Y", "", 19, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Urusan Kemasukkan Maklumat", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "FLAG_DAFTAR", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
					"", formName, "2", 20, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();
			htmlPageSetup += modelBI.setupButton(session, "", jenis_transaction, setupSkrinHistory, field_main_PK, id,
					current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name, divViewMaklumat,
					paramsButton, "", db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinTangguhBorangJ(HttpSession session, String FLAG_TANGGUH, String jenis_transaction,
			String current_previous, String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory,
			Map setupSkrin, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName,
			String command, String id, String formName, String divViewMaklumat, String table_name, String field_main_PK,
			String mode, String paramsButton, String flag_editable, String ID_PEMOHON, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			myLogger.info("setupSkrinTangguh setupSkrin : " + setupSkrin);

			// yg ni untuk perintah
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			myLogger.info("setupSkrinJenisKeputusan perintah : " + value_main_PK);

			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BORANGJ", "",
					"hidden", "", "", "", "", 0, db1);

			htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Rujukan Kepada</td></tr>";
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Rujukan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_MOHON", "Y", "text", "Y", "10", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Jenis Rujukan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_RUJUKAN", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName,
					"", 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Rujukan Ruler Of The State", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "FLAG_RUJUKAN", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
					"", formName, "", 0, db1);

			String flag_rujukan = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					value_main_PK, ID_PERBICARAAN, "FLAG_RUJUKAN", db1);

			String id_jenispejabat_mahkamah = "8";
			if (flag_rujukan.equals("1")) {
				id_jenispejabat_mahkamah = "41";
			}

			htmlPageSetup += "<tr id=\"trTajukMahkamahTangguh\" ><td colspan=\"4\" class=\"table_header\" id=\"tdTajukMahkamahTangguh\" >Mahkamah Tinggi</td></tr>";
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri Mahkamah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERIMAHKAMAH", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI",
					"", "", "div" + skrinName + "ID_MAHKAMAH", "TBLRUJPEJABAT", "ID_MAHKAMAH", "ID_NEGERI",
					"ID_PEJABAT", "KOD_PEJABAT", "NAMA_PEJABAT", formName, "", 0, db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Mahkamah Tinggi", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_MAHKAMAH", "Y", "select", "Y", "TBLRUJPEJABAT", "ID_PEJABAT", "KOD_PEJABAT", "NAMA_PEJABAT",
					"ID_NEGERI",
					id_jenispejabat_mahkamah + modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERIMAHKAMAH", db1),
					"", "", "", "", "", "", "", formName, "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Nama", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_PEJABAT", "Y",
					"text", "Y", "100", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT1", "Y",
					"text", "Y", "80", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT2", "",
					"text", "Y", "80", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Alamat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT3", "",
					"text", "Y", "80", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD", "Y",
					"numbersOnly", "Y", "5", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI",
					"Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, "", 0, db1);// dynamic ajax call
			htmlPageSetup += modelBI
					.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode, setupSkrin,
							"Bandar", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BANDAR", "",
							"select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_NEGERI", db1),
							"", "", "", "", "", "", "", formName, "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL", "",
					"numbersOnly", "Y", "14", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "No Faks", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_FAX", "",
					"numbersOnly", "Y", "14", "Y", "", 0, db1);

			htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Fakta Guaman</td></tr>";
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "edit",
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN1", "",
					"text", "Y", "2000", "", "", 0, db1);
			htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
					+ "CATATAN1\" style=\"display:none\" ><td></td><td></td><td></td><td>";
			htmlPageSetup += "<div id=\"infobutton" + skrinName
					+ "CATATAN1\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"trword" + skrinName
					+ "CATATAN1\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN1\"></div>";
			htmlPageSetup += "</td></tr>";

			/*
			 * htmlPageSetup +=
			 * modelBI.setRowTextArea(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName
			 * ,mode,setupSkrin," Pendapat / Keputusan / Arahan	",table_name,field_main_PK,
			 * value_main_PK,ID_PERBICARAAN,"KEPUTUSAN_MAHKAMAH","","text","Y","2000","","",
			 * 0,db1); htmlPageSetup += "<tr id=\"trinfobutton"
			 * +skrinName+"KEPUTUSAN_MAHKAMAH\" style=\"display:none\" ><td></td><td></td><td></td><td>"
			 * ; htmlPageSetup += "<div id=\"infobutton"
			 * +skrinName+"KEPUTUSAN_MAHKAMAH\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>"
			 * ; htmlPageSetup += "</td></tr>"; htmlPageSetup += "<tr id=\"trword"
			 * +skrinName+"KEPUTUSAN_MAHKAMAH\"><td></td><td></td><td></td><td align=\"right\">"
			 * ; htmlPageSetup += "<div id=\"word"+skrinName+"KEPUTUSAN_MAHKAMAH\"></div>";
			 * htmlPageSetup += "</td></tr>";
			 */
			htmlPageSetup += modelBI.closeHTMLTable();
			// divSkrinSuplimentTangguhBatal_ListPB
			// String htmlPageSetup_Senarai =

			if (FLAG_TANGGUH.equals("5") || FLAG_TANGGUH.equals("6")) {
				htmlPageSetup += "<div id=\"setupSenaraiWarisBorangJKolateral\">";
				htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Senarai Orang Berkepentingan Terjejas</td></tr>";
				htmlPageSetup += setupSenaraiWarisBorangJKolateral(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN,
						ID_PERBICARAAN, ID_PEMOHON, FLAG_TANGGUH, mode, db1);
				htmlPageSetup += modelBI.closeHTMLTable();
				htmlPageSetup += "</div>";

				htmlPageSetup += "<script>" + "$jquery(document).ready(function (){" +
				// " alert('1');" +
						" document.getElementById('divSkrinSuplimentTangguhBatal_ListPB').innerHTML = document.getElementById('setupSenaraiWarisBorangJKolateral').innerHTML; "
						+ " document.getElementById('setupSenaraiWarisBorangJKolateral').innerHTML=''; " +
						// " alert('2');" +
						" });" + "</script>";
			}

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinTangguhKoleteral(HttpSession session, String FLAG_TANGGUH, String jenis_transaction,
			String current_previous, String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory,
			Map setupSkrin, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName,
			String command, String id, String formName, String divViewMaklumat, String table_name, String field_main_PK,
			String mode, String paramsButton, String flag_editable, String ID_PEMOHON, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			myLogger.info("setupSkrinTangguhKoleteral table_name : " + table_name);
			Map setupPerintah = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
					ID_PERMOHONANSIMATI, "", ID_PERBICARAAN, "TBLPPKPERINTAH", db);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			// myLogger.info("setupSkrinJenisKeputusan perintah : "+value_main_PK);

			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Maklumat Perbicaraan</td></tr>";
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_KOLATERALMST", "",
					"hidden", "", "", "", "", 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Perakuan Kolateral", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_PERAKUAN", "Y", "text", "Y", "10", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Tarikh Bicara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"TARIKH_BICARA", "Y", "text", "Y", "10", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Waktu Bicara (24 jam HHMM)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"MASA_BICARA", "Y", "numbersOnly", "Y", "4", "Y", "", 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupPerintah, "Tempat Bicara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_PEJABATMAHKAMAH", "Y", "select", "Y", "TBLRUJPEJABATJKPTG", "ID_PEJABATJKPTG", "KOD_JKPTG",
					"NAMA_PEJABAT", "ID_PERMOHONAN", ID_PERMOHONAN, "", "", "", "", "", "", "", formName, "", 0, db1);
			// htmlPageSetup += "<div
			// id=\"divSetPejabatKeputusanID_PEJABATMAHKAMAH\"></div>";
			myLogger.info(">>>>>>> ID_PEJABATMAHKAMAH ::::::::::: "
					+ modelBI.getValue(session, ID_PERMOHONANSIMATI, setupPerintah, table_name, field_main_PK,
							value_main_PK, ID_PERBICARAAN, "ID_PEJABATMAHKAMAH", db1));
			Map defaultAlamatPejabat = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName, "",
					"ID_PEJABATJKPTG", modelBI.getValue(session, ID_PERMOHONANSIMATI, setupPerintah, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEJABATMAHKAMAH", db1),
					"TBLRUJPEJABATJKPTG", db1);
			String nama_pejabat = "";
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String id_negeri = "";
			String id_bandar = "";
			String no_tel = "";
			String no_fax = "";
			myLogger.info(">>>>>>> defaultAlamatPejabat ::::::::::: " + defaultAlamatPejabat);
			if (defaultAlamatPejabat != null) {
				nama_pejabat = (String) defaultAlamatPejabat.get("NAMA_PEJABAT");
				alamat1 = (String) defaultAlamatPejabat.get("ALAMAT1");
				alamat2 = (String) defaultAlamatPejabat.get("ALAMAT2");
				alamat3 = (String) defaultAlamatPejabat.get("ALAMAT3");
				poskod = (String) defaultAlamatPejabat.get("POSKOD");
				id_negeri = (String) defaultAlamatPejabat.get("ID_NEGERI");
				id_bandar = (String) defaultAlamatPejabat.get("ID_BANDAR");
				no_tel = (String) defaultAlamatPejabat.get("NO_TEL");
				no_fax = (String) defaultAlamatPejabat.get("NO_FAX");
			}

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Nama Pejabat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_PEJABAT", "",
					"text", "Y", "100", "Y", nama_pejabat, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Alamat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT1", "", "text",
					"Y", "80", "Y", alamat1, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Alamat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT2", "", "text",
					"Y", "80", "Y", alamat2, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Alamat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT3", "", "text",
					"Y", "80", "Y", alamat3, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD", "",
					"numbersOnly", "Y", "5", "Y", poskod, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, null, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI", "",
					"select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, id_negeri, 0, db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, null, "Bandar", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BANDAR", "",
					"select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI", id_negeri, "",
					"", "", "", "", "", "", formName, id_bandar, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL", "",
					"numbersOnly", "Y", "14", "Y", no_tel, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "No Faks", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_FAX", "",
					"numbersOnly", "Y", "14", "Y", no_fax, 0, db1);

			htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Sebab Pertelingkahan</td></tr>";
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "edit",
					setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"SEBAB_PERTELINGKAHAN", "", "text", "Y", "2000", "", "", 0, db1);
			htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
					+ "SEBAB_PERTELINGKAHAN\" style=\"display:none\" ><td></td><td></td><td></td><td>";
			htmlPageSetup += "<div id=\"infobutton" + skrinName
					+ "SEBAB_PERTELINGKAHAN\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"trword" + skrinName
					+ "SEBAB_PERTELINGKAHAN\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "SEBAB_PERTELINGKAHAN\"></div>";
			htmlPageSetup += "</td></tr>";

			if (setupSkrin != null && modelBI.getValue(session, ID_PERMOHONANSIMATI, setupPerintah, "", "", "",
					ID_PERBICARAAN, "FLAG_TANGGUH", db1).equals("6")) {
				// skrin keputusan koleteral
				// htmlPageSetup += "<div id=\"setupSkrinSuplimentKeputusanKoleteral\"><div
				// class=\"viewMaklumatTR\" >";
				// htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Keputusan Koleteral</td></tr>";
				// htmlPageSetup +=
				// setupSenaraiWarisBorangJKolateral(session,ID_PERMOHONANSIMATI,ID_PERMOHONAN,ID_PERBICARAAN,ID_PEMOHON,FLAG_TANGGUH,mode,db1);
				Map setupBayaranKoleteral = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKBAYARAN", "17", db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranKoleteral, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"ID_BAYARAN17", "", "hidden", "", "", "", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranKoleteral, "Jumlah Bayaran (RM)", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "JUMLAH_BAYARAN17", "", "currencyOnly", "Y", "13", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranKoleteral, "No Resit", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK, ID_PERBICARAAN,
						"NO_RESIT17", "", "text", "Y", "20", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						mode, setupBayaranKoleteral, "Tarikh Bayaran", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "TARIKH_BAYARAN17", "", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"edit", setupSkrin, "Catatan Keputusan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "CATATAN_KEPUTUSAN", "", "text", "Y", "2000", "", "", 0, db1);
				htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
						+ "CATATAN_KEPUTUSAN\" style=\"display:none\" ><td></td><td></td><td></td><td>";
				htmlPageSetup += "<div id=\"infobutton" + skrinName
						+ "CATATAN_KEPUTUSAN\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += "<tr id=\"trword" + skrinName
						+ "CATATAN_KEPUTUSAN\"><td></td><td></td><td></td><td align=\"right\">";
				htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN_KEPUTUSAN\"></div>";
				htmlPageSetup += "</td></tr>";
				// htmlPageSetup += modelBI.closeHTMLTable();
				// htmlPageSetup += "</div></div>";

			}

			htmlPageSetup += modelBI.closeHTMLTable();

			if (FLAG_TANGGUH.equals("5") || FLAG_TANGGUH.equals("6")) {
				htmlPageSetup += "<div id=\"setupSenaraiWarisBorangJKolateral\"><div class=\"viewMaklumatTR\" >";
				htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Senarai Orang Berkepentingan (Plantif / Defenden)</td></tr>";
				htmlPageSetup += setupSenaraiWarisBorangJKolateral(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN,
						ID_PERBICARAAN, ID_PEMOHON, FLAG_TANGGUH, mode, db1);
				htmlPageSetup += modelBI.closeHTMLTable();
				htmlPageSetup += "</div></div>";

				// setupPerintah
				myLogger.info(">>>>>>>>>>>>>>>>>>>> FLAG_TANGGUH >>>>>>>>>>>>>>>>>>>>" + modelBI.getValue(session,
						ID_PERMOHONANSIMATI, setupPerintah, "", "", "", ID_PERBICARAAN, "FLAG_TANGGUH", db1));
				/*
				 * if(setupSkrin != null && modelBI.getValue(session,ID_PERMOHONANSIMATI,
				 * setupPerintah,"","","",ID_PERBICARAAN,"FLAG_TANGGUH",db1).equals("6")) {
				 * //skrin keputusan koleteral htmlPageSetup +=
				 * "<div id=\"setupSkrinSuplimentKeputusanKoleteral\"><div class=\"viewMaklumatTR\" >"
				 * ; htmlPageSetup += modelBI.openHTMLTable(); htmlPageSetup +=
				 * "<tr><td colspan=\"4\" class=\"table_header\">Keputusan Koleteral</td></tr>";
				 * //htmlPageSetup +=
				 * setupSenaraiWarisBorangJKolateral(session,ID_PERMOHONANSIMATI,ID_PERMOHONAN,
				 * ID_PERBICARAAN,ID_PEMOHON,FLAG_TANGGUH,mode,db1);
				 *
				 * Map setupBayaranKoleteral =
				 * modelBI.getValueColumn(session,ID_PEMOHON,ID_PERBICARAAN,skrinName,
				 * ID_PERMOHONANSIMATI,"",ID_PERMOHONAN, "TBLPPKBAYARAN", "17", db1);
				 * htmlPageSetup +=
				 * modelBI.setRowText(session,"",ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
				 * mode,setupBayaranKoleteral,"",table_name,field_main_PK,value_main_PK,
				 * ID_PERBICARAAN,"ID_BAYARAN17","","hidden","","","","",0,db1); htmlPageSetup
				 * +=
				 * modelBI.setRowText(session,"",ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
				 * mode,setupBayaranKoleteral,"Jumlah Bayaran (RM)","TBLPPKBAYARAN","ID_BAYARAN"
				 * ,value_main_PK,ID_PERBICARAAN,"JUMLAH_BAYARAN17","","currencyOnly","Y","13",
				 * "Y","",0,db1); htmlPageSetup +=
				 * modelBI.setRowText(session,"",ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
				 * mode,setupBayaranKoleteral,"No Resit","TBLPPKBAYARAN","ID_BAYARAN",
				 * value_main_PK,ID_PERBICARAAN,"NO_RESIT17","","text","Y","20","Y","",0,db1);
				 * htmlPageSetup +=
				 * modelBI.setRowTextTarikh(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,
				 * skrinName,mode,setupBayaranKoleteral,"Tarikh Bayaran","TBLPPKBAYARAN",
				 * "ID_BAYARAN",value_main_PK,ID_PERBICARAAN,"TARIKH_BAYARAN17","","text","Y",
				 * "10","Y","",0,db1); //htmlPageSetup +=
				 * "<tr><td colspan=\"4\" class=\"table_header\">Catatan Keputusan</td></tr>";
				 *
				 *
				 * htmlPageSetup += modelBI.closeHTMLTable(); htmlPageSetup += "</div></div>";
				 *
				 * }
				 */

				htmlPageSetup += "<script>" + "$jquery(document).ready(function (){" +
				// " alert('1');" +
						" document.getElementById('divSkrinSuplimentTangguhBatal_ListPB').innerHTML = document.getElementById('setupSenaraiWarisBorangJKolateral').innerHTML; "
						+ " document.getElementById('setupSenaraiWarisBorangJKolateral').innerHTML=''; ";
				// htmlPageSetup += "
				// document.getElementById('divSkrinSuplimentKeputusanKoleteral').innerHTML =
				// document.getElementById('setupSkrinSuplimentKeputusanKoleteral').innerHTML; "
				// +
				// "
				// document.getElementById('setupSkrinSuplimentKeputusanKoleteral').innerHTML='';
				// ";
				// " alert('2');" +

				if (FLAG_TANGGUH.equals("6")) {
					// kena hide field keputusan mahkamah

					// trinfokeputusanKEPUTUSAN_MAHKAMAH
					// rowkeputusanKEPUTUSAN_MAHKAMAH
					// trinfobuttonkeputusanKEPUTUSAN_MAHKAMAH

					htmlPageSetup += "document.getElementById('trinfo" + skrinName
							+ "KEPUTUSAN_MAHKAMAH').style.display = \"none\";";
					htmlPageSetup += "document.getElementById('row" + skrinName
							+ "KEPUTUSAN_MAHKAMAH').style.display = \"none\";";
					htmlPageSetup += "document.getElementById('trinfobutton" + skrinName
							+ "KEPUTUSAN_MAHKAMAH').style.display = \"none\";";
					htmlPageSetup += "document.getElementById('trword" + skrinName
							+ "KEPUTUSAN_MAHKAMAH').style.display = \"none\";";

					htmlPageSetup += "document.getElementById('trinfo" + skrinName
							+ "SEBAB_TANGGUH').style.display = \"none\";";
					htmlPageSetup += "document.getElementById('row" + skrinName
							+ "SEBAB_TANGGUH').style.display = \"none\";";
					htmlPageSetup += "document.getElementById('trinfobutton" + skrinName
							+ "SEBAB_TANGGUH').style.display = \"none\";";
					htmlPageSetup += "document.getElementById('trword" + skrinName
							+ "SEBAB_TANGGUH').style.display = \"none\";";
				} /*
					 * else { htmlPageSetup += "document.getElementById('trinfo"+
					 * skrinName+"KEPUTUSAN_MAHKAMAH').style.display = \"\";"; htmlPageSetup +=
					 * "document.getElementById('row"+
					 * skrinName+"KEPUTUSAN_MAHKAMAH').style.display = \"\";"; //htmlPageSetup +=
					 * "document.getElementById('trinfobutton"+
					 * skrinName+"KEPUTUSAN_MAHKAMAH').style.display = \"none\";"; htmlPageSetup +=
					 * "document.getElementById('trword"+
					 * skrinName+"KEPUTUSAN_MAHKAMAH').style.display = \"\";"; }
					 */

				htmlPageSetup += " });" + "</script>";

			}

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinBatalWasiat(HttpSession session, String FLAG_TANGGUH, String jenis_transaction,
			String current_previous, String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory,
			Map setupSkrin, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName,
			String command, String id, String formName, String divViewMaklumat, String table_name, String field_main_PK,
			String mode, String paramsButton, String flag_editable, String ID_PEMOHON, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}
			myLogger.info("setupSkrinBatalWasiat table_name : " + table_name);
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			// myLogger.info("setupSkrinJenisKeputusan perintah : "+value_main_PK);
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Maklumat Rujukan Mahkamah Tinggi</td></tr>";
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Kod Keluar Perintah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"JENIS_KELUAR_PERINTAH", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "",
					formName, "", 0, db1);

			// htmlPageSetup +=
			// modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,command,mode,setupSkrin,"Mahkamah",table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_PEJABATMAHKAMAH","Y","select","Y","TBLRUJPEJABATJKPTG","ID_PEJABATJKPTG","KOD_JKPTG","NAMA_PEJABAT","ID_PERMOHONAN",ID_PERMOHONAN,"","","","","","","",formName,"",0,db1);
			// myLogger.info(">>>>>>> ID_PEJABATMAHKAMAH ::::::::::: " +
			// modelBI.getValue(session,ID_PERMOHONANSIMATI,setupSkrin,table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_PEJABATMAHKAMAH",db1));
			Map defaultAlamatPejabat = modelBI.getValueColumn(session, "", ID_PERBICARAAN, skrinName, "", "ID_PEJABAT",
					modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_PEJABATMAHKAMAH", db1),
					"TBLRUJPEJABAT", db1);
			String nama_pejabat = "";
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String id_negeri = "";
			String id_bandar = "";
			String no_tel = "";
			String no_fax = "";
			myLogger.info(">>>>>>> defaultAlamatPejabat ::::::::::: " + defaultAlamatPejabat);
			if (defaultAlamatPejabat != null) {
				nama_pejabat = (String) defaultAlamatPejabat.get("NAMA_PEJABAT");
				alamat1 = (String) defaultAlamatPejabat.get("ALAMAT1");
				alamat2 = (String) defaultAlamatPejabat.get("ALAMAT2");
				alamat3 = (String) defaultAlamatPejabat.get("ALAMAT3");
				poskod = (String) defaultAlamatPejabat.get("POSKOD");
				id_negeri = (String) defaultAlamatPejabat.get("ID_NEGERI");
				id_bandar = (String) defaultAlamatPejabat.get("ID_BANDAR");
				no_tel = (String) defaultAlamatPejabat.get("NO_TEL");
				no_fax = (String) defaultAlamatPejabat.get("NO_FAX");
			}

			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, null, "Negeri Mahkamah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_NEGERIMAHKAMAH", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI",
					"", "", "div" + skrinName + "ID_PEJABATMAHKAMAH", "TBLRUJPEJABAT", "ID_PEJABATMAHKAMAH",
					"ID_NEGERI", "ID_PEJABAT", "KOD_PEJABAT", "NAMA_PEJABAT", formName, id_negeri, 0, db1);// dynamic
																											// ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, setupSkrin, "Mahkamah Tinggi", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_PEJABATMAHKAMAH", "Y", "select", "Y", "TBLRUJPEJABAT", "ID_PEJABAT", "KOD_PEJABAT",
					"NAMA_PEJABAT", "ID_NEGERI", "8" + id_negeri, "", "", "", "", "", "", "", formName, "", 0, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Nama Pejabat", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NAMA_PEJABAT", "",
					"text", "Y", "100", "Y", nama_pejabat, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Alamat 1", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT1", "", "text",
					"Y", "80", "Y", alamat1, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Alamat 2", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT2", "", "text",
					"Y", "80", "Y", alamat2, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Alamat 3", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ALAMAT3", "", "text",
					"Y", "80", "Y", alamat3, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "Poskod", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "POSKOD", "",
					"numbersOnly", "Y", "5", "Y", poskod, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, null, "Negeri", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERI", "",
					"select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
					"div" + skrinName + "ID_BANDAR", "TBLRUJBANDAR", "ID_BANDAR", "ID_NEGERI", "ID_BANDAR",
					"KOD_BANDAR", "KETERANGAN", formName, id_negeri, 0, db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					mode, null, "Bandar", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BANDAR", "",
					"select", "Y", "TBLRUJBANDAR", "ID_BANDAR", "KOD_BANDAR", "KETERANGAN", "ID_NEGERI", id_negeri, "",
					"", "", "", "", "", "", formName, id_bandar, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "No Telefon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_TEL", "",
					"numbersOnly", "Y", "14", "Y", no_tel, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					null, "No Faks", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "NO_FAX", "",
					"numbersOnly", "Y", "14", "Y", no_fax, 0, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinJenisKeputusan(HttpSession session, String FLAG_KEPUTUSAN, String jenis_transaction,
			String current_previous, String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory,
			Map setupSkrin, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName,
			String command, String id, String formName, String divViewMaklumat, String table_name, String field_main_PK,
			String mode, String paramsButton, String flag_editable, String ID_PEMOHON, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			Map setupPerbicaraan = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
					ID_PERMOHONANSIMATI, "ID_PERBICARAAN", ID_PERBICARAAN, "TBLPPKPERBICARAAN", db);

			// yg ni untuk perintah
			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			String ID_PERINTAH = setupSkrin == null ? ""
					: (String) setupSkrin.get("ID_PERINTAH") == null ? "" : (String) setupSkrin.get("ID_PERINTAH");
			myLogger.info("setupSkrinJenisKeputusan perintah : " + value_main_PK);

			String CATATAN_PERINTAH_TEMP = "";
			String CATATAN_KP_TEMP = "";
			String INTRO_CATATAN_TEMP = "";
			String CATATAN_DOCKIVT = "";

			if (ID_PERINTAH.equals("")) {
				CATATAN_PERINTAH_TEMP = modelBI.getValue(session, "", setupPerbicaraan, "", "", "", "",
						"CATATAN_PERINTAH_TEMP", db1);
				CATATAN_KP_TEMP = modelBI.getValue(session, "", setupPerbicaraan, "", "", "", "", "CATATAN_KP_TEMP",
						db1);
				INTRO_CATATAN_TEMP = modelBI.getValue(session, "", setupPerbicaraan, "", "", "", "",
						"INTRO_CATATAN_TEMP", db1);
				CATATAN_DOCKIVT = modelBI.getValue(session, "", setupPerbicaraan, "", "", "", "", "CATATAN_DOCKIVT",
						db1);
			}

			Map mainInfo = modelBI.mainID(session, ID_PERBICARAAN, db);
			String SEKSYEN = "";
			String SUMHARTA = "";
			String SUMHARTA_CONVERT = "0.00";
			String SUMHARTA_AR = "";
			String SUMHARTA_CONVERT_AR = "0.00";
			String BATAL_KUASA_PENTADBIR = "";
			String BATAL_P_AMANAH = "";
			String HARTA_TINGGAL = "";
			if (mainInfo != null) {
				SEKSYEN = (String) mainInfo.get("SEKSYEN");
				BATAL_KUASA_PENTADBIR = (String) mainInfo.get("BATAL_KUASA_PENTADBIR");
				BATAL_P_AMANAH = (String) mainInfo.get("BATAL_P_AMANAH");
				HARTA_TINGGAL = (String) mainInfo.get("HARTA_TINGGAL");
				ID_PEMOHON = (String) mainInfo.get("ID_PEMOHON");
			}
			String styleKP = "";
			if (FLAG_KEPUTUSAN.equals("0")) {
				Map setupBayaranPerintah = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKBAYARAN", "24", db1);
				Map setupBayaranBaitulmal = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKBAYARAN", "29", db1);
				Map setupBayaranCukai = modelBI.getValueColumn(session, ID_PEMOHON, ID_PERBICARAAN, skrinName,
						ID_PERMOHONANSIMATI, "", ID_PERMOHONAN, "TBLPPKBAYARAN", "26", db1);
				Map jumlahHartaMap = modelBI.jumlahHarta(session, ID_PERMOHONANSIMATI, ID_PERBICARAAN, "", db1);
				Map jumlahHartaAmanahRayaMap = modelBI.jumlahHarta(session, ID_PERMOHONANSIMATI, ID_PERBICARAAN, "Y",
						db1);

				if (jumlahHartaMap != null) {
					SUMHARTA = (String) jumlahHartaMap.get("SUMHARTA");
					SUMHARTA_CONVERT = (String) jumlahHartaMap.get("SUMHARTA_CONVERT");
				}
				if (jumlahHartaAmanahRayaMap != null) {
					SUMHARTA_AR = (String) jumlahHartaAmanahRayaMap.get("SUMHARTA");
					SUMHARTA_CONVERT_AR = (String) jumlahHartaAmanahRayaMap.get("SUMHARTA_CONVERT");
				}

				Double defaultBayaranPerintah = 0.00;
				Double defaultYuranPerintah = 0.00;
				Double totalharta = 0.00;
				Double totalhartaAR = 0.00;

				if (!SUMHARTA.equals("")) {
					totalharta = Double.parseDouble(SUMHARTA);
				}
				myLogger.info("totalharta : " + totalharta);
				if (!SUMHARTA_AR.equals("")) {
					totalhartaAR = Double.parseDouble(SUMHARTA_AR);
				}
				myLogger.info("totalhartaAR : " + totalhartaAR);

				if (SEKSYEN.equals("17")) {
					totalharta = totalharta - totalhartaAR;
				}
				myLogger.info("totalharta - totalhartaAR : " + totalharta);

				// arief comment
				// Boleh dibuka sekiranya Akta Nilaian Harta LEBIH RM5JUTA BELUM diLULUSkan di
				// Parlimen
				/**
				 * if(totalharta > 0) { if ( totalharta >= 1 && totalharta <= 1000 ) {
				 * if(SEKSYEN.equals("8")) { defaultYuranPerintah = 10.00; } else {
				 * defaultYuranPerintah = (0.2/100) * totalharta ; } defaultYuranPerintah =
				 * modelBI.getBundaranBayaran(defaultYuranPerintah); } else if ( (totalharta >=
				 * 1001) && (totalharta <= 50000) ){ if(SEKSYEN.equals("8")) {
				 * defaultYuranPerintah = 30.00; } else { defaultYuranPerintah = (0.2/100) *
				 * totalharta ; } defaultYuranPerintah =
				 * modelBI.getBundaranBayaran(defaultYuranPerintah); } else {
				 * defaultYuranPerintah = (0.2/100) * totalharta ; defaultYuranPerintah =
				 * modelBI.getBundaranBayaran(defaultYuranPerintah); } }
				 **/

				// arief add
				// open bila Akta Nilaian Harta LEBIH RM5JUTA diLULUSkan di Parlimen
				if (totalharta > 0) {
					if (totalharta >= 1 && totalharta <= 1000) {
						if (SEKSYEN.equals("8")) {
							defaultYuranPerintah = 10.00;
						}
					} else if ((totalharta >= 1001) && (totalharta <= 50000)) {
						if (SEKSYEN.equals("8")) {
							defaultYuranPerintah = 30.00;
						}
					} else if ((totalharta > 50000) && (totalharta <= 2000000)) {
						if (SEKSYEN.equals("8")) {
							defaultYuranPerintah = 0.002 * totalharta;
							defaultYuranPerintah = modelBI.getBundaranBayaran(defaultYuranPerintah);
						}
					} else {
						defaultYuranPerintah = 0.005 * totalharta;
						defaultYuranPerintah = modelBI.getBundaranBayaran(defaultYuranPerintah);
					}
				}
				myLogger.info("defaultYuranPerintah : " + defaultYuranPerintah);

				double extraBayaranPerintah = 0;
				if (totalhartaAR <= 0 && SEKSYEN.equals("17")) {
					if (BATAL_KUASA_PENTADBIR.equals("Y")) {
						extraBayaranPerintah += 30.00;
					}
					if (BATAL_P_AMANAH.equals("Y")) {
						extraBayaranPerintah += 30.00;
					}
				}

				myLogger.info("extraBayaranPerintah : " + extraBayaranPerintah);

				defaultBayaranPerintah = defaultYuranPerintah + extraBayaranPerintah;
				defaultBayaranPerintah = modelBI.getBundaranBayaran(defaultBayaranPerintah);

				if (defaultBayaranPerintah < 10 && totalhartaAR > 0 && SEKSYEN.equals("17")) {
					defaultBayaranPerintah = 10.00;
				}

				myLogger.info("defaultBayaranPerintah :::::::::::: " + defaultBayaranPerintah);

				String str_defaultBayaranPerintah = "";
				if (mode.equals("view") && setupBayaranPerintah == null) {
					str_defaultBayaranPerintah = "";
				} else {
					str_defaultBayaranPerintah = defaultBayaranPerintah + "";
				}

				List listInfoPerintahByHarta = modelBI.listInfoPerintahByHarta(session, ID_PERMOHONAN, db1);
				String info_perintah = "";
				String ID_PERINTAH_HARTA = "";
				if (listInfoPerintahByHarta.size() != 0) {
					// info_perintah += "<i><font class=\"red blink\">Perhatian</font> : Perintah
					// yang didaftarkan akan dihapuskan jika <b>Status</b> perintah dikemaskini
					// daripada '<b>SELESAI</b>' kepada '<b>TANGGUH</b>' atau
					// '<b>BATAL</b>'.</i><br>";
					info_perintah += "<i class=\"blue blink\">*Info Perintah Yang Didaftarkan</i><br>";

					for (int i = 0; i < listInfoPerintahByHarta.size(); i++) {
						Map mapInfoPerintahByHarta = (Map) listInfoPerintahByHarta.get(i);
						ID_PERINTAH_HARTA = (String) mapInfoPerintahByHarta.get("ID_PERINTAH");
						String JENIS_PERINTAH = (String) mapInfoPerintahByHarta.get("KETERANGAN");
						String TOTAL_HARTA = (String) mapInfoPerintahByHarta.get("TOTAL_HARTA");
						String TURUTAN_HARTA = (String) mapInfoPerintahByHarta.get("TURUTAN");
						String JENISHARTA = (String) mapInfoPerintahByHarta.get("JENISHARTA");
						info_perintah += TOTAL_HARTA + " " + JENISHARTA + " DIDAFTARKAN " + JENIS_PERINTAH;
						info_perintah += "<br>";
					}
					info_perintah += "<br>";
				}
				myLogger.info("info perintahssss : " + info_perintah);
				htmlPageSetup += info_perintah;
				htmlPageSetup += modelBI.openHTMLTable();
				// xx tanda sini
				htmlPageSetup += "<tr id=\"trMaklumatKiv\"><td colspan=\"4\" class=\"table_header\">Maklumat KIV (Jika Ada)</td></tr>";
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Status KIV", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "CHECK_KIV", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "",
						"", formName, "", 0, db1);
				myLogger.info("htmlPageSetup : " + htmlPageSetup);
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						mode, setupSkrin, "Tarikh Kiv", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"DATE_KIV", "Y", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Catatan Kiv", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_KIV", "Y", "text", "Y", "4000", "Y", "", 0, db1);

				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Bayaran Perintah</td></tr>";
				htmlPageSetup += "<tr><td valign=\"top\" align=\"left\"></td><td valign=\"top\" align=\"left\">Jumlah Harta (RM)</td><td valign=\"top\" align=\"center\">:</td><td valign=\"top\" align=\"left\" >"
						+ SUMHARTA_CONVERT + "</td></tr>";
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranPerintah, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"ID_BAYARAN24", "", "hidden", "", "", "", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranPerintah, "Jumlah Bayaran (RM)", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "JUMLAH_BAYARAN24", "", "currencyOnly", "Y", "13", "Y",
						str_defaultBayaranPerintah + "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranPerintah, "No Resit", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK, ID_PERBICARAAN,
						"NO_RESIT24", "", "text", "Y", "20", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						mode, setupBayaranPerintah, "Tarikh Bayaran", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "TARIKH_BAYARAN24", "", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Bayaran Cukai</td></tr>";
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranCukai, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_BAYARAN26",
						"", "hidden", "", "", "", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranCukai, "Jumlah Bayaran (RM)", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "JUMLAH_BAYARAN26", "", "currencyOnly", "Y", "13", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranCukai, "No Resit", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK, ID_PERBICARAAN,
						"NO_RESIT26", "", "text", "Y", "20", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						mode, setupBayaranCukai, "Tarikh Bayaran", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "TARIKH_BAYARAN26", "", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Bayaran Baitulmal</td></tr>";
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranBaitulmal, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"ID_BAYARAN29", "", "hidden", "", "", "", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranBaitulmal, "Jumlah Bayaran (RM)", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "JUMLAH_BAYARAN29", "", "currencyOnly", "Y", "13", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupBayaranBaitulmal, "No Resit", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK, ID_PERBICARAAN,
						"NO_RESIT29", "", "text", "Y", "20", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						mode, setupBayaranBaitulmal, "Tarikh Bayaran", "TBLPPKBAYARAN", "ID_BAYARAN", value_main_PK,
						ID_PERBICARAAN, "TARIKH_BAYARAN29", "", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.closeHTMLTable();

			} else if (FLAG_KEPUTUSAN.equals("1")) {
				htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Alasan Tangguh Perbicaraan", table_name, field_main_PK,
						value_main_PK, ID_PERBICARAAN, "FLAG_TANGGUH", "Y", "select", "Y", "", "", "", "", "", "", "",
						"", "", "", "", "", "", formName, "", 0, db1);
				htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Tempoh Menunggu (Hari)", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"TEMPOH_TUNGGU_FARID", "", "numbersOnly", "Y", "2", "Y", "", 0, db1);
				htmlPageSetup += modelBI.closeHTMLTable();
				htmlPageSetup += "<div id=\"divSkrinSuplimentTangguhBatal\" ></div>";
				htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += "<tr id=\"trinfo" + skrinName
						+ "SEBAB_TANGGUH\"><td colspan=\"4\" class=\"table_header\">Keterangan Tangguh</td></tr>";
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"edit", setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"SEBAB_TANGGUH", "Y", "text", "Y", "2000", "", "", 0, db1);
				htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
						+ "SEBAB_TANGGUH\" style=\"display:none\" ><td></td><td></td><td></td><td>";
				htmlPageSetup += "<div id=\"infobutton" + skrinName
						+ "SEBAB_TANGGUH\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += "<tr id=\"trword" + skrinName
						+ "SEBAB_TANGGUH\"><td></td><td></td><td></td><td align=\"right\">";
				htmlPageSetup += "<div id=\"word" + skrinName + "SEBAB_TANGGUH\"></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += "<tr id=\"trinfo" + skrinName
						+ "KEPUTUSAN_MAHKAMAH\"><td colspan=\"4\" class=\"table_header\">Pendapat / Keputusan Mahkamah</td></tr>";
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"edit", setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"KEPUTUSAN_MAHKAMAH", "", "text", "Y", "2000", "", "", 0, db1);
				htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
						+ "KEPUTUSAN_MAHKAMAH\" style=\"display:none\" ><td></td><td></td><td></td><td>";
				htmlPageSetup += "<div id=\"infobutton" + skrinName
						+ "KEPUTUSAN_MAHKAMAH\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += "<tr id=\"trword" + skrinName
						+ "KEPUTUSAN_MAHKAMAH\"><td></td><td></td><td></td><td align=\"right\">";
				htmlPageSetup += "<div id=\"word" + skrinName + "KEPUTUSAN_MAHKAMAH\"></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += modelBI.closeHTMLTable();
				htmlPageSetup += "<div id=\"divSkrinSuplimentTangguhBatal_ListPB\" ></div>";
				htmlPageSetup += "<div id=\"divSkrinSuplimentKeputusanKoleteral\" ></div>";
				styleKP = "display:none";
			} else if (FLAG_KEPUTUSAN.equals("2")) {
				htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Alasan Batal Perbicaraan", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "FLAG_BATAL", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "",
						"", "", formName, "", 0, db1);
				htmlPageSetup += modelBI.closeHTMLTable();
				htmlPageSetup += "<div id=\"divSkrinSuplimentTangguhBatal\" ></div>";
				htmlPageSetup += modelBI.openHTMLTable();
				htmlPageSetup += "<tr><td colspan=\"4\" class=\"table_header\">Keterangan Alasan Pembatalan</td></tr>";
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"edit", setupSkrin, "Catatan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"SEBAB_BATAL", "Y", "text", "Y", "2000", "", "", 0, db1);
				htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
						+ "SEBAB_BATAL\" style=\"display:none\" ><td></td><td></td><td></td><td>";
				htmlPageSetup += "<div id=\"infobutton" + skrinName
						+ "SEBAB_BATAL\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += "<tr id=\"trword" + skrinName
						+ "SEBAB_BATAL\"><td></td><td></td><td></td><td align=\"right\">";
				htmlPageSetup += "<div id=\"word" + skrinName + "SEBAB_BATAL\"></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += modelBI.closeHTMLTable();
				styleKP = "display:none";
			}

			htmlPageSetup += "<div style = \"" + styleKP + "\"  >";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr  ><td colspan=\"4\" class=\"table_header\">Keputusan Perbicaraan</td></tr>";

			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan Keputusan Perbicaraan", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "CATATAN_KEPUTUSAN_PERBICARAAN", "Y", "text", "Y", "4000", "", CATATAN_KP_TEMP, 0,
					db1);
			htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
					+ "CATATAN_KEPUTUSAN_PERBICARAAN\" style=\"display:none\" ><td></td><td></td><td></td><td>";
			htmlPageSetup += "<div id=\"infobutton" + skrinName
					+ "CATATAN_KEPUTUSAN_PERBICARAAN\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"trword" + skrinName
					+ "CATATAN_KEPUTUSAN_PERBICARAAN\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN_KEPUTUSAN_PERBICARAAN\"></div>";
			htmlPageSetup += "</td></tr>";

			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan Dokumen TELAH / GAGAL Dikemukakan", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "CATATAN_DOCKIV", "", "text", "", "4000", "", CATATAN_DOCKIVT, 0, db1); // arief add
																											// GAGAL
																											// Dikembalikan
			htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
					+ "CATATAN_DOCKIV\" style=\"display:none\" ><td></td><td></td><td></td><td>";
			htmlPageSetup += "<div id=\"infobutton" + skrinName
					+ "CATATAN_DOCKIV\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"trword" + skrinName
					+ "CATATAN_DOCKIV\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN_DOCKIV\"></div>";
			htmlPageSetup += "</td></tr>";

			String field_ID_INTROPERINTAH_type = "radio";
			if (mode.equals("view")) {
				field_ID_INTROPERINTAH_type = "hidden";
			}
			String label_CATATAN_PERINTAH_BI = "";
			String showTitik_CATATAN_PERINTAH_BI = "";
			// if(mode.equals("view"))
			// {
			label_CATATAN_PERINTAH_BI = "Nota Keterangan & Perintah";
			showTitik_CATATAN_PERINTAH_BI = "Y";
			// }

			if (FLAG_KEPUTUSAN.equals("0")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Perintah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"ID_INTROPERINTAH", "", field_ID_INTROPERINTAH_type, "Y", "TBLPPKRUJINTROPERINTAH",
						"ID_INTROPERINTAH", "", "INTRO", "SEKSYEN", SEKSYEN, "", "", "", "", "", "", "", formName, "",
						6, db1);
				// htmlPageSetup +=
				// modelBI.setRowTextArea(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,mode,setupSkrin,"",table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"INTRO_CATATAN","","text","","4000","",INTRO_CATATAN_TEMP,0,db1);//arief
				// comment
				htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
						+ "INTRO_CATATAN\" style=\"display:none\" ><td></td><td></td><td></td><td>";
				htmlPageSetup += "<div id=\"infobutton" + skrinName
						+ "INTRO_CATATAN\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
				htmlPageSetup += "</td></tr>";
				htmlPageSetup += "<tr id=\"trword" + skrinName
						+ "INTRO_CATATAN\"><td></td><td></td><td></td><td align=\"right\">";
				htmlPageSetup += "<div id=\"word" + skrinName + "INTRO_CATATAN\"></div>";
				htmlPageSetup += "</td></tr>";
			}

			// htmlPageSetup +=
			// modelBI.setRowTextArea(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,mode,setupSkrin,"Catatan
			// Perintah",table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"CATATAN","","hidden","Y","4000","",CATATAN_PERINTAH_TEMP,0,db1);
			// //arief comment
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan Perintah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN",
					"", "text", "Y", "4000", "", CATATAN_PERINTAH_TEMP, 0, db1); // arief add
			htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
					+ "CATATAN\" style=\"display:none\" ><td></td><td></td><td></td><td>";
			htmlPageSetup += "<div id=\"infobutton" + skrinName
					+ "CATATAN\" style=\"display:none\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"trword" + skrinName
					+ "CATATAN\" style=\"display:none\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN\" style=\"display:none\"></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "</div>";

			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr  ><td colspan=\"4\" class=\"table_header\">Nota</td></tr>";
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "view",
					setupSkrin, label_CATATAN_PERINTAH_BI, table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"CATATAN_PERINTAH_BI", "", "text", showTitik_CATATAN_PERINTAH_BI, "1000000", "", "", 0, db1);
			htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN_PERINTAH_BI\"></div>";
			htmlPageSetup += "<tr id=\"trword" + skrinName + "CP\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "CP\"></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<script>fckeditorWordCatatanPerintah(document.getElementById(\"divViewEditorkeputusanCATATAN_PERINTAH_BI\"),\"word"
					+ skrinName + "CP\");</script>";

			// ni condition mengikut sekatan
			/*
			 * if(mode.equals("edit")) { htmlPageSetup += ""; htmlPageSetup +=
			 * "<tr id=\"trJanaCatatan\" ><td colspan='3' ></td>"; htmlPageSetup +=
			 * "<td align='left' valign='top' id=\"tdDisplayLinkJana\" >";
			 * if(!ID_PERINTAH.equals("")) { htmlPageSetup +=
			 * " <a href=\"javascript:janaCatatanPerintah('"+ID_SIMATI+"','"+ID_PERINTAH+
			 * "','"+ID_PERMOHONAN+"','"+ID_PERMOHONANSIMATI+"','"+ID_PERBICARAAN+"','"+
			 * ID_PEMOHON+"','"+skrinName+"');\" >"; htmlPageSetup +=
			 * "<font color=\"blue\"><u><b>>> Simpan & Jana Catatan Perintah</b></u></font>"
			 * ; htmlPageSetup += "</a> "; } else { htmlPageSetup +=
			 * " <div style=\"margin:5px\"><i><font color='blue'>Info</font> : Sila simpan keputusan perbicaraan dahulu sebelum menjana keterangan perintah secara auto.</i></div>"
			 * ;
			 *
			 * } htmlPageSetup += " <span id=\"locationJanaCatatan\" ></span>";
			 * htmlPageSetup += "</td>"; htmlPageSetup += "</tr>"; }
			 */
			// ni kalo ada selesai, tp nak jana balik

			if (mode.equals("edit") || mode.equals("view")) {
				htmlPageSetup += "";
				htmlPageSetup += "<tr id=\"trJanaCatatan\" ><td colspan='3' ></td>";
				htmlPageSetup += "<td align='left' valign='top' id=\"tdDisplayLinkJana\" >";
				if (!ID_PERINTAH.equals("")) {
					if (mode.equals("edit")) {
						htmlPageSetup += " <a href=\"javascript:janaCatatanPerintah('" + ID_SIMATI + "','" + ID_PERINTAH
								+ "','" + ID_PERMOHONAN + "','" + ID_PERMOHONANSIMATI + "','" + ID_PERBICARAAN + "','"
								+ ID_PEMOHON + "','" + skrinName + "');\" >";
						htmlPageSetup += "<font color=\"blue\"><u><b>>> Simpan & Jana Catatan Perintah</b></u></font>";
						htmlPageSetup += "</a> ";
					} else {
						htmlPageSetup += " <a href=\"javascript:janaCatatanPerintah('" + ID_SIMATI + "','" + ID_PERINTAH
								+ "','" + ID_PERMOHONAN + "','" + ID_PERMOHONANSIMATI + "','" + ID_PERBICARAAN + "','"
								+ ID_PEMOHON + "','" + skrinName + "');\" >";
						htmlPageSetup += "&nbsp;";
						htmlPageSetup += "</a> ";
					}

				} else {
					if (mode.equals("edit")) {
						htmlPageSetup += " <div style=\"margin:5px\"><i><font color='blue'>Info</font> : Sila simpan keputusan perbicaraan dahulu sebelum menjana keterangan perintah secara auto.</i></div>";
					}
				}
				htmlPageSetup += " <span id=\"locationJanaCatatan\" ></span>";
				htmlPageSetup += "</td>";
				htmlPageSetup += "</tr>";
			}

			htmlPageSetup += "<tr id=\"trinfobutton" + skrinName
					+ "CATATAN_PERINTAH_BI\" style=\"display:none\" ><td></td><td></td><td></td><td>";
			htmlPageSetup += "<div id=\"infobutton" + skrinName
					+ "CATATAN_PERINTAH_BI\" ><i><font color='blue'>Info</font> : Sila tekan butang <font color='blue'>'Tab'</font> selepas selesai mengisi keterangan. Butang <font color='blue'>'Simpan Keputusan'</font> akan dipaparkan.</i></div>";
			htmlPageSetup += "</td></tr>";
			htmlPageSetup += "<tr id=\"trword" + skrinName
					+ "CATATAN_PERINTAH_BI\"><td></td><td></td><td></td><td align=\"right\">";
			htmlPageSetup += "<div id=\"word" + skrinName + "CATATAN_PERINTAH_BI\"></div>";
			htmlPageSetup += "</td></tr>";

			htmlPageSetup += modelBI.closeHTMLTable();
			//arief add
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += "<tr  ><td colspan=\"4\" class=\"table_header\">Perintah Perbicaraan</td></tr>";
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "<iframe src = \"?_portal_module=ekptg.view.ppk.FrmPerintahSek8\" style=\"border: 1px none; margin-right: -35px; height: 812px; width: 1070px;\"</iframe>";
			//htmlPageSetup += "<p><a href=\"?_portal_module=ekptg.view.ppk.FrmPerintahSek8\" target=\"iframe_a\">FrmPerintahSek8</a></p>";
			//perlu repair iframe bagi kegunaan daftar perintah di Bicara Interaktif :: 3/9/2020
			//arief add

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	public String setupSenaraiWarisBorangJKolateral(HttpSession session, String ID_PERMOHONANSIMATI,
			String ID_PERMOHONAN, String ID_PERBICARAAN, String ID_PEMOHON, String FLAG_TANGGUH, String mode, Db db)
			throws Exception {
		String html = "";

		List listPB = modelBI.listWarisBorangJKolateral(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN, ID_PERBICARAAN,
				ID_PEMOHON, FLAG_TANGGUH, db);
		String styleDisplay = "";
		if (mode.equals("view")) {
			styleDisplay = " style='display:none' ";
		}

		html += "<table border=\"0\"  cellspacing=\"1\" cellpadding=\"3\" width=\"100%\" >";
		html += "<tr class=\"table_header\" >";
		html += "<td   align=\"center\" valign=\"top\" width=\"5%\">Bil.</td>";
		html += "<td   align=\"left\" valign=\"top\" >Nama</td>";
		html += "<td   align=\"left\" valign=\"top\" >Pengenalan</td>";
		html += "<td   align=\"left\" valign=\"top\" >Kaitan / Hubungan</td>";
		html += "<td   align=\"center\" valign=\"top\" >Umur</td>";
		html += "<td   align=\"left\" valign=\"top\" >Status</td>";
		if (FLAG_TANGGUH.equals("6")) {
			html += "<td   align=\"center\" valign=\"top\" width=\"10%\">Plantif<br />";
			html += "<input type=\"checkbox\" style='display:none' id=\"mainPlantif_ob\" name=\"mainPlantif_ob\" title=\"Pilih Semua Plantif\" onclick=\"doCheckAllKehadiran('mainPlantif_ob','subPlantif_ob');\"/>";
			html += "</td>";
			html += "<td   align=\"center\" valign=\"top\" width=\"10%\">Defendan<br />";
			html += "<input type=\"checkbox\" style='display:none' id=\"mainDefendan_ob\" name=\"mainDefendan_ob\" title=\"Pilih Semua Defendan\" onclick=\"doCheckAllKehadiran('mainDefendan_ob','subDefendan_ob');\"/>";
			html += "</td>";
		} else {
			html += "<td   align=\"center\" valign=\"top\" width=\"10%\">Pilihan<br />";
			html += "<input type=\"checkbox\" " + styleDisplay
					+ " id=\"main_ob\" name=\"main_ob\" title=\"Pilih Semua\" onclick=\"doCheckAllKehadiran('main_ob','sub_ob');\"/>";
			html += "</td>";
		}
		html += "</tr>";

		if (listPB.size() > 0) {
			for (int i = 0; i < listPB.size(); i++) {
				html += "<tr  >";
				Map setupPB = (Map) listPB.get(i);
				if (setupPB != null) {
					String rowCSS = (String) setupPB.get("rowCss");
					String BIL = (String) setupPB.get("BIL");
					String ID_OB = (String) setupPB.get("ID_OB");
					String NAMA = (String) setupPB.get("NAMA");
					String PENGENALAN = (String) setupPB.get("PENGENALAN");
					String HUBUNGAN = (String) setupPB.get("HUBUNGAN");
					String UMUR = (String) setupPB.get("UMUR");
					String STATUS = (String) setupPB.get("STATUS");
					String ID_BORANGJDTL = "";
					String ID_KOLATERALDTL = "";
					String JENIS_OB_DTL = "";
					if (FLAG_TANGGUH.equals("6")) {
						ID_KOLATERALDTL = (String) setupPB.get("ID_KOLATERALDTL");
						JENIS_OB_DTL = (String) setupPB.get("JENIS_OB_DTL");
					} else {
						ID_BORANGJDTL = (String) setupPB.get("ID_BORANGJDTL");
					}

					html += "<td class=\"" + rowCSS + "\" align=\"center\" valign=\"top\" >" + BIL + "</td>";
					html += "<td class=\"" + rowCSS + "\" align=\"left\" valign=\"top\" >" + NAMA + "</td>";
					html += "<td class=\"" + rowCSS + "\" align=\"left\" valign=\"top\" >" + PENGENALAN + "</td>";
					html += "<td class=\"" + rowCSS + "\" align=\"left\" valign=\"top\" >" + HUBUNGAN + "</td>";
					html += "<td class=\"" + rowCSS + "\" align=\"center\" valign=\"top\" >" + UMUR + "</td>";
					html += "<td class=\"" + rowCSS + "\" align=\"left\" valign=\"top\" >" + STATUS + "</td>";

					if (FLAG_TANGGUH.equals("6")) {
						html += "<td class=\"" + rowCSS + "\" align=\"center\" valign=\"top\" >";
						html += "<input type=\"checkbox\" " + styleDisplay
								+ " id=\"subPlantif_ob\" name=\"subPlantif_ob\" value=\"" + ID_OB + "\" ";
						if (!ID_KOLATERALDTL.equals("") && JENIS_OB_DTL.equals("PL")) {
							html += "checked";
						}
						html += " onclick=\"doCheckKehadiran('subPlantif_ob', 'mainPlantif_ob');checkSubKolateral('Plantif','"
								+ ID_OB + "');\"  />";

						if (mode.equals("view")) {
							if (!ID_KOLATERALDTL.equals("") && JENIS_OB_DTL.equals("PL")) {
								html += " <img border=\"0\" src=\"../img/validyes.png\"/> ";
							} else {
								html += " <img border=\"0\" src=\"../img/validno.png\"/> ";
							}
						}
						html += " </td>";

						html += "<td class=\"" + rowCSS + "\" align=\"center\" valign=\"top\" >";
						html += "<input type=\"checkbox\" " + styleDisplay
								+ " id=\"subDefendan_ob\" name=\"subDefendan_ob\" value=\"" + ID_OB + "\" ";
						if (!ID_KOLATERALDTL.equals("") && JENIS_OB_DTL.equals("DF")) {
							html += "checked";
						}
						html += " onclick=\"doCheckKehadiran('subDefendan_ob', 'mainDefendan_ob');checkSubKolateral('Defendan','"
								+ ID_OB + "');\"  />";
						if (mode.equals("view")) {
							if (!ID_KOLATERALDTL.equals("") && JENIS_OB_DTL.equals("DF")) {
								html += " <img border=\"0\" src=\"../img/validyes.png\"/> ";
							} else {
								html += " <img border=\"0\" src=\"../img/validno.png\"/> ";
							}
						}
						html += " </td>";
					} else {
						html += "<td class=\"" + rowCSS + "\" align=\"center\" valign=\"top\" >";
						html += "<input type=\"checkbox\" " + styleDisplay + " id=\"sub_ob\" name=\"sub_ob\" value=\""
								+ ID_OB + "\" ";
						if (!ID_BORANGJDTL.equals("")) {
							html += "checked";
						}
						html += " onclick=\"doCheckKehadiran('sub_ob', 'main_ob');\"  />";

						if (mode.equals("view")) {
							if (!ID_BORANGJDTL.equals("")) {
								html += " <img border=\"0\" src=\"../img/validyes.png\"/> ";
							} else {
								html += " <img border=\"0\" src=\"../img/validno.png\"/> ";
							}
						}

						html += "</td>";
					}
				}
				html += "</tr >";
			}
		}
		html += "</table>";

		return html;
	}

	public String setupPegawaiMultiple(HttpSession session, String mode, String skrinName, Map setupSkrin,
			String table_name, String field_main_PK, String command, String id_jawatan_login, String id_negeri_login,
			String standardParam, String pemohonOrKPP, String openFrom, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date) + "";

		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			String STATUS_TUKARPEGAWAI = modelBI.getValue(session, "", setupSkrin, table_name, field_main_PK, "", "",
					"STATUS_TUKARPEGAWAI", db1);

			htmlPageSetup += "<br><div><i><font color='red'>Perhatian</font> : Pastikan label bertanda <font color='red'>*</font> diisi.</i></div>";
			htmlPageSetup += "<div><i><font color='blue'>Info</font> : Pegawai bicara yang ditetapkan semasa peringkat notis boleh memohon permohonan tukar pegawai perbicaraan sebelum perbicaraan berlangsung. Permohonan penukaran akan dipanjangkan kepada KPP negeri atau HQ untuk kelulusan.</i></div>";
			htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP negeri boleh menukar pegawai perbicaraan dikalangan negeri sendiri sahaja.</i></div>";
			htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP dan Pegarah Bahagian Pusaka di HQ boleh menukar pegawai perbicaraan meliputi seluruh negara.</i></div>";
			if (id_jawatan_login.equals("5") || id_jawatan_login.equals("4")) {
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : Untuk keputusan 'TOLAK' tidak perlu mengisi maklumat pegawai dan negeri ganti.</i></div>";
			}

			htmlPageSetup += "<br><fieldset>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "", table_name,
					field_main_PK, "", "", "ID_TUKARPEGAWAI", "", "hidden", "", "", "", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "", table_name,
					field_main_PK, "", "", "ID_PEMOHONTUKARPEGAWAI", "", "hidden", "", "", "", USER_ID_SYSTEM, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, "view", setupSkrin,
					"Tarikh Permohonan Tukar Pegawai", table_name, field_main_PK, "", "", "TARIKH_MOHON", "Y", "text",
					"Y", "10", "Y", currentDate, 0, db1);

			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, "view", setupSkrin,
					"Status Tukar Pegawai", table_name, field_main_PK, "", "", "STATUS_TUKARPEGAWAI", "Y", "select",
					"Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "1", 0, db1);

			String flag_daftar_terus = "";

			// PEGAWAI BICARA
			// GET ID_PEGAWAI BY LOGIN
			Map getDetailUsers = modelBI.getDetailUsers(session, "", USER_ID_SYSTEM, "", db);
			String username = "";
			String id_pegawai = "";
			if (getDetailUsers != null) {
				username = (String) getDetailUsers.get("USER_NAME");
			}
			String LIST_ID_UNITPSK = modelBI.getDetailPegawaiList(session, username, db);

			if ((id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))
			// && LIST_ID_UNITPSK.contains(defaultPegawai+",") == false
			) {
				if (id_negeri_login.equals("16")) {
					htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
							"Negeri Pegawai Ganti", table_name, field_main_PK, "", "", "ID_NEGERIPEGAWAIBARU", "Y",
							"select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
							"div" + skrinName + "ID_PEGAWAIBARU", "TBLPPKRUJUNIT", "ID_PEGAWAIBARU", "ID_NEGERI",
							"ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic ajax call
					htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
							"Pegawai Ganti", table_name, field_main_PK, "", "", "ID_PEGAWAIBARU", "Y", "select", "Y",
							"TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "", "", "", "", "", "", "", "", "",
							formName, "", 0, db1);
				} else {
					htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "",
							table_name, field_main_PK, "", "", "ID_NEGERIPEGAWAIBARU", "", "hidden", "", "", "",
							id_negeri_login, 0, db1);
					htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
							"Pegawai Ganti", table_name, field_main_PK, "", "", "ID_PEGAWAIBARU", "Y", "select", "Y",
							"TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "ID_NEGERI", id_negeri_login, "", "", "",
							"", "", "", "", formName, "", 0, db1);
				}
				flag_daftar_terus = "Y";
			} else {
				htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
						"Negeri Pegawai Ganti", table_name, field_main_PK, "", "", "ID_NEGERIPEGAWAIBARU", "Y",
						"select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
						"div" + skrinName + "ID_PEGAWAIBARU", "TBLPPKRUJUNIT", "ID_PEGAWAIBARU", "ID_NEGERI",
						"ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic ajax call
				htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
						"Pegawai Ganti", table_name, field_main_PK, "", "", "ID_PEGAWAIBARU", "Y", "select", "Y",
						"TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "", "", "", "", "", "", "", "", "", formName,
						"", 0, db1);
			}

			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "", table_name,
					field_main_PK, "", "", "FLAG_DAFTAR_TERUS", "", "hidden", "", "", "", flag_daftar_terus, 0, db1);

			if (skrinName.equals("tukarpegawaiKPP_multiple")) {
				// htmlPageSetup +=
				// modelBI.setRowTextArea(session,"","",skrinName,"view",setupSkrin,"Catatan
				// Pemohon",table_name,field_main_PK,"","","CATATAN_PEMOHON","","text","Y","4000","Y","",0,db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, "view", setupSkrin,
						"Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, "", "", "TARIKH_KEPUTUSAN", "Y",
						"hidden", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, "", "", skrinName, mode, setupSkrin, "Catatan Pelulus",
						table_name, field_main_PK, "", "", "CATATAN_PELULUS", "", "text", "Y", "4000", "Y", "", 0, db1);
			} else {
				htmlPageSetup += modelBI.setRowTextArea(session, "", "", skrinName, mode, setupSkrin, "Catatan Pemohon",
						table_name, field_main_PK, "", "", "CATATAN_PEMOHON", "", "text", "Y", "4000", "Y", "", 0, db1);

			}

			htmlPageSetup += "<tr>" + "<td></td>"
					+ "<td valign = \"top\" align = \"left\" >Jumlah Perbicaraan Dipilih</td>"
					+ "<td valign = \"top\" align = \"center\" >:</td>"
					+ "<td  valign = \"top\" align = \"left\" ><div id=\"div" + skrinName
					+ "DisplayJumlahPerbicaraan\" >0</div>" + "<input type=\"hidden\" id=\"" + skrinName
					+ "JumlahPerbicaraan\" name=\"" + skrinName + "JumlahPerbicaraan\" value=\"0\"  ></td>" + "</tr>";

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "</fieldset>";

			htmlPageSetup += modelBI.setupButtonTukarPegawaiMultiple(session, "", "", null, field_main_PK, id, "",
					skrinName, formName, "Y", mode, setupSkrin, table_name, "view_" + skrinName, standardParam, "",
					pemohonOrKPP, openFrom, db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}

		return htmlPageSetup;
	}

	//arief add skrin tukar pegawai 2
	public String setupPegawaiMultiple2(HttpSession session, String mode, String skrinName, Map setupSkrin,
			String table_name, String field_main_PK, String command, String id_jawatan_login, String id_negeri_login,
			String standardParam, String pemohonOrKPP, String openFrom, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date) + "";

		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			String STATUS_TUKARPEGAWAI = modelBI.getValue(session, "", setupSkrin, table_name, field_main_PK, "", "",
					"STATUS_TUKARPEGAWAI", db1);

			htmlPageSetup += "<br><div><i><font color='red'>Perhatian</font> : Pastikan label bertanda <font color='red'>*</font> diisi.</i></div>";
			htmlPageSetup += "<div><i><font color='blue'>Info</font> : Pegawai bicara yang ditetapkan semasa peringkat notis boleh memohon permohonan tukar pegawai perbicaraan sebelum perbicaraan berlangsung. Permohonan penukaran akan dipanjangkan kepada KPP negeri atau HQ untuk kelulusan.</i></div>";
			htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP negeri boleh menukar pegawai perbicaraan dikalangan negeri sendiri sahaja.</i></div>";
			htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP dan Pegarah Bahagian Pusaka di HQ boleh menukar pegawai perbicaraan meliputi seluruh negara.</i></div>";
			if (id_jawatan_login.equals("5") || id_jawatan_login.equals("4")) {
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : Untuk keputusan 'TOLAK' tidak perlu mengisi maklumat pegawai dan negeri ganti.</i></div>";
			}

			htmlPageSetup += "<br><fieldset>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "", table_name,
					field_main_PK, "", "", "ID_TUKARPEGAWAI", "", "hidden", "", "", "", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "", table_name,
					field_main_PK, "", "", "ID_PEMOHONTUKARPEGAWAI", "", "hidden", "", "", "", USER_ID_SYSTEM, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, "view", setupSkrin,
					"Tarikh Permohonan Tukar Pegawai", table_name, field_main_PK, "", "", "TARIKH_MOHON", "Y", "text",
					"Y", "10", "Y", currentDate, 0, db1);

			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, "view", setupSkrin,
					"Status Tukar Pegawai", table_name, field_main_PK, "", "", "STATUS_TUKARPEGAWAI", "Y", "select",
					"Y", "", "", "", "", "", "", "", "", "", "", "", "", "", formName, "1", 0, db1);

			String flag_daftar_terus = "";

			// PEGAWAI BICARA
			// GET ID_PEGAWAI BY LOGIN
			Map getDetailUsers = modelBI.getDetailUsers(session, "", USER_ID_SYSTEM, "", db);
			String username = "";
			String id_pegawai = "";
			if (getDetailUsers != null) {
				username = (String) getDetailUsers.get("USER_NAME");
			}
			String LIST_ID_UNITPSK = modelBI.getDetailPegawaiList(session, username, db);

			if ((id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))) {
				if (id_negeri_login.equals("16")) {
					htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
							"Negeri Pegawai di Ganti", table_name, field_main_PK, "", "", "ID_NEGERIPEGAWAIASAL", "Y",
							"select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
							"div" + skrinName + "ID_PEGAWAIASAL", "TBLPPKRUJUNIT", "ID_PEGAWAIASAL", "ID_NEGERI",
							"ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic ajax call
					htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
							"Pegawai di Ganti", table_name, field_main_PK, "", "", "ID_PEGAWAILASAL", "Y", "select", "Y",
							"TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "", "", "", "", "", "", "", "", "",
							formName, "", 0, db1);
				} else {
					htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "",
							table_name, field_main_PK, "", "", "ID_NEGERIPEGAWAIBARU", "", "hidden", "", "", "",
							id_negeri_login, 0, db1);
					htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
							"Pegawai di Ganti", table_name, field_main_PK, "", "", "ID_PEGAWAIASAL", "Y", "select", "Y",
							"TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "ID_NEGERI", id_negeri_login, "", "", "",
							"", "", "", "", formName, "", 0, db1);
				}
				flag_daftar_terus = "Y";
			} else {
				htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
						"Negeri Pegawai di Ganti", table_name, field_main_PK, "", "", "ID_NEGERIPEGAWAIASAL", "Y",
						"select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "",
						"div" + skrinName + "ID_PEGAWAIASAL", "TBLPPKRUJUNIT", "ID_PEGAWAIASAL", "ID_NEGERI",
						"ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic ajax call
				htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, setupSkrin,
						"Pegawai di Ganti", table_name, field_main_PK, "", "", "ID_PEGAWAIASAL", "Y", "select", "Y",
						"TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "", "", "", "", "", "", "", "", "", formName,
						"", 0, db1);
			}

			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, setupSkrin, "", table_name,
					field_main_PK, "", "", "FLAG_DAFTAR_TERUS", "", "hidden", "", "", "", flag_daftar_terus, 0, db1);

			if (skrinName.equals("tukarpegawaiKPP2")) {
				// htmlPageSetup +=
				// modelBI.setRowTextArea(session,"","",skrinName,"view",setupSkrin,"Catatan
				// Pemohon",table_name,field_main_PK,"","","CATATAN_PEMOHON","","text","Y","4000","Y","",0,db1);
				htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, "view", setupSkrin,
						"Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, "", "", "TARIKH_KEPUTUSAN", "Y",
						"hidden", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, "", "", skrinName, mode, setupSkrin, "Catatan Pelulus",
						table_name, field_main_PK, "", "", "CATATAN_PELULUS", "", "text", "Y", "4000", "Y", "", 0, db1);
			} else {
				htmlPageSetup += modelBI.setRowTextArea(session, "", "", skrinName, mode, setupSkrin, "Catatan Pemohon",
						table_name, field_main_PK, "", "", "CATATAN_PEMOHON", "", "text", "Y", "4000", "Y", "", 0, db1);

			}

			htmlPageSetup += "<tr>" + "<td></td>"
					+ "<td valign = \"top\" align = \"left\" >No.Fail </td>"
					+ "<td valign = \"top\" align = \"center\" >:</td>"
					+ "<td width=\"70%\"><input name=\"txtNoFailSub\" id=\"txtNoFailSub\" type=\"text\" value=\"$txtNoFailSub\" size=\"30\" maxlength=\"50\" style=\"text-transform:uppercase;\" onBlur=\"this.value=this.value.toUpperCase();\" />";



			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "</fieldset>";

			htmlPageSetup += modelBI.setupButtonTukarPegawai2(session, "", "", null, field_main_PK, id, "",
					skrinName, formName, "Y", mode, setupSkrin, table_name, "view_" + skrinName, standardParam, "",
					pemohonOrKPP, openFrom, db1);

		} finally {
			if (db == null) {
				db1.close();
			}
		}

		return htmlPageSetup;
	}

	@SuppressWarnings("unchecked")
	public String setupSkrinTukarPegawai(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, String pemohonOrKPP, String id_jawatan_login,
			String id_negeri_login, String openFrom, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date) + "";

		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			String defaultPegawai = "";
			Map viewPerbicaraan = modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db);
			if (viewPerbicaraan != null) {
				defaultPegawai = (String) viewPerbicaraan.get("ID_UNITPSK");
			}

			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			myLogger.info("value_main_PK perintah : " + value_main_PK);

			String id_negeri_fail = "";
			Map getFail = modelBI.getFail(session, ID_PERMOHONAN, db);
			if (getFail != null) {
				id_negeri_fail = (String) getFail.get("ID_NEGERI");
			}
			myLogger.info("id_negeri_fail perintah : " + id_negeri_fail);

			String STATUS_TUKARPEGAWAI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", db1);
			if (!mode.equals("view")) {
				htmlPageSetup += "<br><div><i><font color='red'>Perhatian</font> : Pastikan label bertanda <font color='red'>*</font> diisi.</i></div>";
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : Pegawai bicara yang ditetapkan semasa peringkat notis boleh memohon permohonan tukar pegawai perbicaraan sebelum perbicaraan berlangsung. Permohonan penukaran akan dipanjangkan kepada KPP negeri atau HQ untuk kelulusan.</i></div>";
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP negeri boleh menukar pegawai perbicaraan dikalangan negeri sendiri sahaja.</i></div>";
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP dan Pegarah Bahagian Pusaka di HQ boleh menukar pegawai perbicaraan meliputi seluruh negara.</i></div>";

			} else {
				htmlPageSetup += "<br>";
			}

			if (skrinName.equals("tukarpegawaiKPP")) {
				String NO_FAIL = "";
				String NAMA_SIMATI = "";
				String NAMA_PEMOHON = "";
				String WAKTU_BICARA = "";
				String BIL_BICARA = "";

				if (viewPerbicaraan != null) {
					NO_FAIL = (String) viewPerbicaraan.get("NO_FAIL");
					NAMA_SIMATI = (String) viewPerbicaraan.get("NAMA_SIMATI");
					NAMA_PEMOHON = (String) viewPerbicaraan.get("NAMA_PEMOHON");
					WAKTU_BICARA = (String) viewPerbicaraan.get("TARIKH_BICARA") + " "
							+ (String) viewPerbicaraan.get("MASA_BICARA");
					BIL_BICARA = ((Integer) viewPerbicaraan.get("BIL_BICARA")).toString();
					htmlPageSetup += "<fieldset>";
					htmlPageSetup += modelBI.openHTMLTable();
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							"view", null, "No. Fail", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"NO_FAILXX", "", "text", "Y", "", "", NO_FAIL, 0, db1);
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							"view", null, "Nama Simati", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"NAMA_SIMATIXX", "", "text", "Y", "", "", NAMA_SIMATI, 0, db1);
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							"view", null, "Nama Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"NAMA_PEMOHONXX", "", "text", "Y", "", "", NAMA_PEMOHON, 0, db1);
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							"view", null, "Waktu Bicara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"WAKTU_BICARAXX", "", "text", "Y", "", "", WAKTU_BICARA, 0, db1);
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							"view", null, "Bil. Bicara", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"BIL_BICARAXX", "", "text", "Y", "", "", BIL_BICARA, 0, db1);
					htmlPageSetup += modelBI.closeHTMLTable();
					htmlPageSetup += "</fieldset>" + " <br> ";
				}
			}

			htmlPageSetup += "<fieldset>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_TUKARPEGAWAI", "",
					"hidden", "", "", "", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHONTUKARPEGAWAI",
					"", "hidden", "", "", "", USER_ID_SYSTEM, 0, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "view",
					setupSkrin, "No. Permohonan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "NO_TUKARPEGAWAI", "", "text", "Y", "", "", "-", 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "view",
					setupSkrin, "Tarikh Permohonan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "TARIKH_MOHON", "Y", "text", "Y", "10", "Y", currentDate, 0, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIASAL",
					"", "hidden", "", "", "", id_negeri_fail, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					"view", setupSkrin, "Pegawai Asal", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_PEGAWAIASAL", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI",
					"ID_NEGERI", id_negeri_fail, "", "", "", "", "", "", "", formName, defaultPegawai, 0, db1);

			// PEGAWAI BICARA
			// GET ID_PEGAWAI BY LOGIN
			Map getDetailUsers = modelBI.getDetailUsers(session, "", USER_ID_SYSTEM, "", db);
			String username = "";
			String id_pegawai = "";
			if (getDetailUsers != null) {
				username = (String) getDetailUsers.get("USER_NAME");
			}
			String LIST_ID_UNITPSK = modelBI.getDetailPegawaiList(session, username, db);

			String flag_daftar_terus = "";
			if ((id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))
					&& LIST_ID_UNITPSK.contains(defaultPegawai + ",") == false) {
				if (id_negeri_login.equals("16")) {
					htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							command, mode, setupSkrin, "Negeri Pegawai Ganti", table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI",
							"KOD_NEGERI", "NAMA_NEGERI", "", "", "div" + skrinName + "ID_PEGAWAIBARU", "TBLPPKRUJUNIT",
							"ID_PEGAWAIBARU", "ID_NEGERI", "ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic
																													// ajax
																													// call
					htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							command, mode, setupSkrin, "Pegawai Ganti", table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "",
							"NAMA_PEGAWAI", "ID_NEGERI",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", db1),
							"", "", "", "", "", "", "", formName, "", 0, db1);
				} else {
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							mode, setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"ID_NEGERIPEGAWAIBARU", "", "hidden", "", "", "", id_negeri_fail, 0, db1);

					String id_negeri_pegawai = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", db1);
					if (id_negeri_pegawai.equals("")) {
						id_negeri_pegawai = id_negeri_fail;
					}

					htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							command, mode, setupSkrin, "Pegawai Ganti", table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "",
							"NAMA_PEGAWAI", "ID_NEGERI", id_negeri_pegawai, "", "", "", "", "", "", "", formName, "", 0,
							db1);
				}
				flag_daftar_terus = "Y";
			} else {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Negeri Pegawai Ganti", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI",
						"KOD_NEGERI", "NAMA_NEGERI", "", "", "div" + skrinName + "ID_PEGAWAIBARU", "TBLPPKRUJUNIT",
						"ID_PEGAWAIBARU", "ID_NEGERI", "ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic
																												// ajax
																												// call
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Pegawai Ganti", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "",
						"NAMA_PEGAWAI", "ID_NEGERI",
						modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
								value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", db1),
						"", "", "", "", "", "", "", formName, "", 0, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "FLAG_DAFTAR_TERUS", "",
					"hidden", "", "", "", flag_daftar_terus, 0, db1);

			/*
			 * String id_negeri_pegawai =
			 * modelBI.getValue(session,ID_PERMOHONANSIMATI,setupSkrin,table_name,
			 * field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_NEGERIPEGAWAIBARU",db1);
			 * if(id_negeri_pegawai.equals("")) { id_negeri_pegawai = id_negeri_fail; }
			 */
			// htmlPageSetup +=
			// modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,command,mode,setupSkrin,"Pegawai
			// Ganti",table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_PEGAWAIBARU","Y","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","ID_NEGERI",modelBI.getValue(session,ID_PERMOHONANSIMATI,setupSkrin,table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_NEGERIPEGAWAIBARU",db1),"","","","","","","",formName,"",0,db1);

			/*
			 * if((id_jawatan_login.equals("5") || id_jawatan_login.equals("4")) &&
			 * STATUS_TUKARPEGAWAI.equals("1") && skrinName.equals("tukarpegawaiKPP")) {
			 * htmlPageSetup +=
			 * modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
			 * command,mode,setupSkrin,"Status Tukar Pegawai",table_name,field_main_PK,
			 * value_main_PK,ID_PERBICARAAN,"STATUS_TUKARPEGAWAI","Y","select","Y","","","",
			 * "","","","","","","","","","",formName,"1",0,db1); } else { htmlPageSetup +=
			 * modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
			 * command,"view",setupSkrin,"Status Tukar Pegawai",table_name,field_main_PK,
			 * value_main_PK,ID_PERBICARAAN,"STATUS_TUKARPEGAWAI","Y","select","Y","","","",
			 * "","","","","","","","","","",formName,"1",0,db1); }
			 */
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					"view", setupSkrin, "Status Tukar Pegawai", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "",
					"", "", "", formName, "1", 0, db1);

			String statusTukarPegawai = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", db1);
			String tarikhKelulusan = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "TARIKH_KEPUTUSAN", db1);
			String catatanKelulusan = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN_PELULUS", db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"CATATAN_PEMOHON", "", "text", "Y", "4000", "Y", "", 0, db1);

			String ID_PELULUS = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					value_main_PK, ID_PERBICARAAN, "ID_PELULUS", db1);
			if (!ID_PELULUS.equals("")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, "view", setupSkrin, "Pegawai Pelulus", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_PELULUS", "Y", "select", "Y", "USERS", "USER_ID", "", "USER_NAME", "", "",
						"", "", "", "", "", "", "", formName, "", 0, db1);// dynamic ajax call
			}

			if (STATUS_TUKARPEGAWAI.equals("1") && (id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))) {
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "TARIKH_KEPUTUSAN", "Y", "hidden", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Catatan Pelulus", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_PELULUS", "", "text", "Y", "4000", "Y", "", 0, db1);

			} else if (STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3")) {
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "TARIKH_KEPUTUSAN", "Y", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Catatan Pelulus", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_PELULUS", "", "text", "Y", "4000", "Y", "", 0, db1);
			} else {
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "TARIKH_KEPUTUSAN", "Y", "hidden", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Catatan Pelulus", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_PELULUS", "", "hidden", "Y", "4000", "Y", "", 0, db1);
			}
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "</fieldset>";
			htmlPageSetup += modelBI.setupButtonTukarPegawai(session, "", jenis_transaction, setupSkrinHistory,
					field_main_PK, id, current_previous, skrinName, formName, flag_editable, mode, setupSkrin,
					table_name, divViewMaklumat, paramsButton, "", pemohonOrKPP, openFrom, db1);

			if (command.equals("saveTukarPegawai") || command.equals("lulusTukarPegawai")
					|| command.equals("tolakTukarPegawai")) {

				htmlPageSetup += "<script>" +
				// " $jquery(document).ready(function (){" +
						" doDivAjaxCall" + formNameAjax + "('viewHeader','showHeader','ID_PERBICARAAN=" + ID_PERBICARAAN
						+ "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "'); " +
						// "});" +
						" </script>";
			}
			/*
			 * setupSkrin = "<script>$jquery(document).ready(function (){" +
			 * " doDivAjaxCall"+formNameAjax+"('senarai_"+skrinName+
			 * "current','showSenarai','NAMA_TABLE="+NAMA_TABLE+"&ID_SIMATI="+ID_SIMATI+
			 * "&FIELD_PK="+FIELD_PK+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+
			 * ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName="+
			 * skrinName+
			 * "&current_previous=current&tajukList=&scrolPosition='+getPageLocation());" +
			 * " });$jquery(\"#"+divId+"\").html(\"\");</script>";
			 */

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	//arief add tukar pegawai 2
	@SuppressWarnings("unchecked")
	public String setupSkrinTukarPegawai2(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, String pemohonOrKPP, String id_jawatan_login,
			String id_negeri_login, String openFrom, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String currentDate = dateFormat.format(date) + "";

		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			String pegawaiAsal = "";
			Map viewPerbicaraan = modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db);
			if (viewPerbicaraan != null) {
				pegawaiAsal = (String) viewPerbicaraan.get("ID_UNITPSK");
			}

			String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					"", ID_PERBICARAAN, field_main_PK, db1);
			myLogger.info("value_main_PK perintah : " + value_main_PK);

			String id_negeri_fail = "";
			Map getFail = modelBI.getFail(session, ID_PERMOHONAN, db);
			if (getFail != null) {
				id_negeri_fail = (String) getFail.get("ID_NEGERI");
			}
			myLogger.info("id_negeri_fail perintah : " + id_negeri_fail);

			String STATUS_TUKARPEGAWAI = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", db1);
			if (!mode.equals("view")) {
				htmlPageSetup += "<br><div><i><font color='red'>Perhatian</font> : Pastikan label bertanda <font color='red'>*</font> diisi.</i></div>";
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : Pegawai bicara yang ditetapkan semasa peringkat notis boleh memohon permohonan tukar pegawai perbicaraan sebelum perbicaraan berlangsung. Permohonan penukaran akan dipanjangkan kepada KPP negeri atau HQ untuk kelulusan.</i></div>";
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP negeri boleh menukar pegawai perbicaraan dikalangan negeri sendiri sahaja.</i></div>";
				htmlPageSetup += "<div><i><font color='blue'>Info</font> : KPP dan Pegarah Bahagian Pusaka di HQ boleh menukar pegawai perbicaraan meliputi seluruh negara.</i></div>";

			} else {
				htmlPageSetup += "<br>";
			}

			htmlPageSetup += "<fieldset>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_TUKARPEGAWAI", "",
					"hidden", "", "", "", "", 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PEMOHONTUKARPEGAWAI",
					"", "hidden", "", "", "", USER_ID_SYSTEM, 0, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "view",
					setupSkrin, "No. Permohonan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "NO_TUKARPEGAWAI", "", "text", "Y", "", "", "-", 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, "view",
					setupSkrin, "Tarikh Permohonan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "TARIKH_MOHON", "Y", "text", "Y", "10", "Y", currentDate, 0, db1);

			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU",
					"", "hidden", "", "", "", id_negeri_fail, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					"view", setupSkrin, "Pegawai Baru", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI",
					"ID_NEGERI", id_negeri_fail, "", "", "", "", "", "", "", formName, pegawaiAsal, 0, db1);

			// PEGAWAI BICARA
			// GET ID_PEGAWAI BY LOGIN
			Map getDetailUsers = modelBI.getDetailUsers(session, "", USER_ID_SYSTEM, "", db);
			String username = "";
			String id_pegawai = "";
			if (getDetailUsers != null) {
				username = (String) getDetailUsers.get("USER_NAME");
			}
			String LIST_ID_UNITPSK = modelBI.getDetailPegawaiList(session, username, db);

			String flag_daftar_terus = "";
			if ((id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))
					&& LIST_ID_UNITPSK.contains(pegawaiAsal + ",") == false) {
				if (id_negeri_login.equals("16")) {
					htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							command, mode, setupSkrin, "Negeri Pegawai Ganti", table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI",
							"KOD_NEGERI", "NAMA_NEGERI", "", "", "div" + skrinName + "ID_PEGAWAIBARU", "TBLPPKRUJUNIT",
							"ID_PEGAWAIBARU", "ID_NEGERI", "ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic
																													// ajax
																													// call
					htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							command, mode, setupSkrin, "Pegawai Ganti", table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "",
							"NAMA_PEGAWAI", "ID_NEGERI",
							modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
									value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", db1),
							"", "", "", "", "", "", "", formName, "", 0, db1);
				} else {
					htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							mode, setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
							"ID_NEGERIPEGAWAIBARU", "", "hidden", "", "", "", id_negeri_fail, 0, db1);

					String id_negeri_pegawai = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
							field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", db1);
					if (id_negeri_pegawai.equals("")) {
						id_negeri_pegawai = id_negeri_fail;
					}

					htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
							command, mode, setupSkrin, "Pegawai Ganti", table_name, field_main_PK, value_main_PK,
							ID_PERBICARAAN, "ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "",
							"NAMA_PEGAWAI", "ID_NEGERI", id_negeri_pegawai, "", "", "", "", "", "", "", formName, "", 0,
							db1);
				}
				flag_daftar_terus = "Y";
			} else {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Negeri Pegawai Ganti", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", "Y", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI",
						"KOD_NEGERI", "NAMA_NEGERI", "", "", "div" + skrinName + "ID_PEGAWAIBARU", "TBLPPKRUJUNIT",
						"ID_PEGAWAIBARU", "ID_NEGERI", "ID_UNITPSK", "", "NAMA_PEGAWAI", formName, "", 0, db1);// dynamic
																												// ajax
																												// call
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, mode, setupSkrin, "Pegawai Ganti", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_PEGAWAIBARU", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "",
						"NAMA_PEGAWAI", "ID_NEGERI",
						modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
								value_main_PK, ID_PERBICARAAN, "ID_NEGERIPEGAWAIBARU", db1),
						"", "", "", "", "", "", "", formName, "", 0, db1);
			}
			htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "FLAG_DAFTAR_TERUS", "",
					"hidden", "", "", "", flag_daftar_terus, 0, db1);

			/*
			 * String id_negeri_pegawai =
			 * modelBI.getValue(session,ID_PERMOHONANSIMATI,setupSkrin,table_name,
			 * field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_NEGERIPEGAWAIBARU",db1);
			 * if(id_negeri_pegawai.equals("")) { id_negeri_pegawai = id_negeri_fail; }
			 */
			// htmlPageSetup +=
			// modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,command,mode,setupSkrin,"Pegawai
			// Ganti",table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_PEGAWAIBARU","Y","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","ID_NEGERI",modelBI.getValue(session,ID_PERMOHONANSIMATI,setupSkrin,table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"ID_NEGERIPEGAWAIBARU",db1),"","","","","","","",formName,"",0,db1);

			/*
			 * if((id_jawatan_login.equals("5") || id_jawatan_login.equals("4")) &&
			 * STATUS_TUKARPEGAWAI.equals("1") && skrinName.equals("tukarpegawaiKPP")) {
			 * htmlPageSetup +=
			 * modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
			 * command,mode,setupSkrin,"Status Tukar Pegawai",table_name,field_main_PK,
			 * value_main_PK,ID_PERBICARAAN,"STATUS_TUKARPEGAWAI","Y","select","Y","","","",
			 * "","","","","","","","","","",formName,"1",0,db1); } else { htmlPageSetup +=
			 * modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,
			 * command,"view",setupSkrin,"Status Tukar Pegawai",table_name,field_main_PK,
			 * value_main_PK,ID_PERBICARAAN,"STATUS_TUKARPEGAWAI","Y","select","Y","","","",
			 * "","","","","","","","","","",formName,"1",0,db1); }
			 */
			htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
					"view", setupSkrin, "Status Tukar Pegawai", table_name, field_main_PK, value_main_PK,
					ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "",
					"", "", "", formName, "1", 0, db1);

			String statusTukarPegawai = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "STATUS_TUKARPEGAWAI", db1);
			String tarikhKelulusan = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "TARIKH_KEPUTUSAN", db1);
			String catatanKelulusan = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name,
					field_main_PK, value_main_PK, ID_PERBICARAAN, "CATATAN_PELULUS", db1);
			htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
					setupSkrin, "Catatan Pemohon", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
					"CATATAN_PEMOHON", "", "text", "Y", "4000", "Y", "", 0, db1);

			String ID_PELULUS = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK,
					value_main_PK, ID_PERBICARAAN, "ID_PELULUS", db1);
			if (!ID_PELULUS.equals("")) {
				htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						command, "view", setupSkrin, "Pegawai Pelulus", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "ID_PELULUS", "Y", "select", "Y", "USERS", "USER_ID", "", "USER_NAME", "", "",
						"", "", "", "", "", "", "", formName, "", 0, db1);// dynamic ajax call
			}

			if (STATUS_TUKARPEGAWAI.equals("1") && (id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))) {
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "TARIKH_KEPUTUSAN", "Y", "hidden", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Catatan Pelulus", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_PELULUS", "", "text", "Y", "4000", "Y", "", 0, db1);

			} else if (STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3")) {
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "TARIKH_KEPUTUSAN", "Y", "text", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
						setupSkrin, "Catatan Pelulus", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_PELULUS", "", "text", "Y", "4000", "Y", "", 0, db1);
			} else {
				htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Tarikh Kelulusan Tukar Pegawai", table_name, field_main_PK, value_main_PK,
						ID_PERBICARAAN, "TARIKH_KEPUTUSAN", "Y", "hidden", "Y", "10", "Y", "", 0, db1);
				htmlPageSetup += modelBI.setRowTextArea(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
						"view", setupSkrin, "Catatan Pelulus", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
						"CATATAN_PELULUS", "", "hidden", "Y", "4000", "Y", "", 0, db1);
			}
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += "</fieldset>";
			htmlPageSetup += modelBI.setupButtonTukarPegawai(session, "", jenis_transaction, setupSkrinHistory,
					field_main_PK, id, current_previous, skrinName, formName, flag_editable, mode, setupSkrin,
					table_name, divViewMaklumat, paramsButton, "", pemohonOrKPP, openFrom, db1);

			if (command.equals("saveTukarPegawai") || command.equals("lulusTukarPegawai")
					|| command.equals("tolakTukarPegawai")) {

				htmlPageSetup += "<script>" +
				// " $jquery(document).ready(function (){" +
						" doDivAjaxCall" + formNameAjax + "('viewHeader','showHeader','ID_PERBICARAAN=" + ID_PERBICARAAN
						+ "&ID_PERMOHONAN=" + ID_PERMOHONAN + "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "'); " +
						// "});" +
						" </script>";
			}
			/*
			 * setupSkrin = "<script>$jquery(document).ready(function (){" +
			 * " doDivAjaxCall"+formNameAjax+"('senarai_"+skrinName+
			 * "current','showSenarai','NAMA_TABLE="+NAMA_TABLE+"&ID_SIMATI="+ID_SIMATI+
			 * "&FIELD_PK="+FIELD_PK+"&ID_PERBICARAAN="+ID_PERBICARAAN+"&ID_PERMOHONAN="+
			 * ID_PERMOHONAN+"&ID_PERMOHONANSIMATI="+ID_PERMOHONANSIMATI+"&skrinName="+
			 * skrinName+
			 * "&current_previous=current&tajukList=&scrolPosition='+getPageLocation());" +
			 * " });$jquery(\"#"+divId+"\").html(\"\");</script>";
			 */

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinKeputusan(HttpSession session, String jenis_transaction, String current_previous,
			String aktiviti, String ID_SIMATI, String ID_SEJARAHBIMAIN, Map setupSkrinHistory, Map setupSkrin,
			String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String command,
			String id, String formName, String divViewMaklumat, String table_name, String field_main_PK, String mode,
			String paramsButton, String flag_editable, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		// try{
		if (db != null) {
			db1 = db;
		} else {
			db1 = new Db();
		}

		String defaultPegawai = "";
		String defaultPegawaiAsal = "";
		String defaultPegawaiBaru = "";
		String defautTarikhPerintah = "";
		Map viewPerbicaraan = modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db);
		if (viewPerbicaraan != null) {
			defaultPegawaiAsal = (String) viewPerbicaraan.get("ID_UNITPSK");
			defaultPegawaiBaru = (String) viewPerbicaraan.get("ID_PEGAWAIBARU");

			if (!defaultPegawaiBaru.equals("")) {
				defaultPegawai = defaultPegawaiBaru;
			} else {
				defaultPegawai = defaultPegawaiAsal;
			}

			defautTarikhPerintah = (String) viewPerbicaraan.get("TARIKH_BICARA");
		}

		String value_main_PK = modelBI.getValue(session, ID_PERMOHONANSIMATI, setupSkrin, table_name, field_main_PK, "",
				ID_PERBICARAAN, field_main_PK, db1);
		myLogger.info("value_main_PK perintah : " + value_main_PK);

		String id_negeri_fail = "";
		Map getFail = modelBI.getFail(session, ID_PERMOHONAN, db);
		if (getFail != null) {
			id_negeri_fail = (String) getFail.get("ID_NEGERI");
		}
		myLogger.info("id_negeri_fail perintah : " + id_negeri_fail);

		htmlPageSetup += "<a class=\"blue\" href=\"javascript:doDivAjaxCall" + formName + "('view_" + skrinName
				+ "','showKeputusan','NAMA_TABLE=" + table_name + "&FIELD_PK=" + field_main_PK + "&ID_SIMATI="
				+ ID_SIMATI + "&ID_PERBICARAAN=" + ID_PERBICARAAN + "&ID_PERMOHONAN=" + ID_PERMOHONAN
				+ "&ID_PERMOHONANSIMATI=" + ID_PERMOHONANSIMATI + "&skrinName=" + skrinName
				+ "&scrolPosition='+getPageLocation());\"><u>'Refresh'</u></a>";
		if (!mode.equals("view")) {
			htmlPageSetup += "<div><i><font color='red'>Perhatian</font> : Pastikan label bertanda <font color='red'>*</font> diisi.</i></div>";
		}
		htmlPageSetup += "<fieldset>";
		htmlPageSetup += modelBI.openHTMLTable();
		htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
				setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERINTAH", "", "hidden",
				"", "", "", "", 0, db1);
		htmlPageSetup += modelBI.setRowText(session, "", ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, mode,
				setupSkrin, "", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN, "ID_PERBICARAAN", "",
				"hidden", "", "", "", ID_PERBICARAAN, 0, db1);
		String valueKeputusan = setupSkrin == null ? ""
				: (String) setupSkrin.get("FLAG_JENIS_KEPUTUSAN") == null ? ""
						: (String) setupSkrin.get("FLAG_JENIS_KEPUTUSAN");
		String modeControl = mode;
		if (!valueKeputusan.equals("")) {
			// boleh kemaskini nama pegawai & tarikh
			// modeControl = "view";
		}
		htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command,
				"view", setupSkrin, "Pegawai Pengendali", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
				"ID_UNITPSK", "Y", "select", "Y", "TBLPPKRUJUNIT", "ID_UNITPSK", "", "NAMA_PEGAWAI", "ID_NEGERI",
				id_negeri_fail, "", "", "", "", "", "", "", formName, defaultPegawai, 0, db1);
		htmlPageSetup += modelBI.setRowSelect(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName, command, mode,
				setupSkrin, "Keputusan Perbicaraan", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
				"FLAG_JENIS_KEPUTUSAN", "Y", "select", "Y", "", "", "", "", "", "", "", "", "", "", "", "", "",
				formName, "", 0, db1);
		htmlPageSetup += modelBI.setRowTextTarikh(session, ID_SEJARAHBIMAIN, ID_PERMOHONANSIMATI, skrinName,
				modeControl, setupSkrin, "Tarikh Perintah", table_name, field_main_PK, value_main_PK, ID_PERBICARAAN,
				"TARIKH_PERINTAH", "Y", "text", "Y", "10", "Y", defautTarikhPerintah, 0, db1);
		// htmlPageSetup +=
		// modelBI.setRowSelect(session,ID_SEJARAHBIMAIN,ID_PERMOHONANSIMATI,skrinName,command,modeControl,setupSkrin,"Bantahan?",table_name,field_main_PK,value_main_PK,ID_PERBICARAAN,"FLAG_BANTAHAN","","select","Y","","","","","","","","","","","","","",formName,"",0,db1);

		htmlPageSetup += "<tr id=\"trShow" + skrinName + "\" ><td colspan=\"10\"><fieldset><legend id=\"legend"
				+ skrinName + "\" ></legend>";
		htmlPageSetup += "<div id=\"divkeputusanPerintah\" ></div>";
		htmlPageSetup += "</fieldset></td></tr>";
		htmlPageSetup += modelBI.closeHTMLTable();
		htmlPageSetup += "</fieldset>";

		htmlPageSetup += modelBI.setupButtonKeputusan(session, valueKeputusan, "", jenis_transaction, setupSkrinHistory,
				field_main_PK, id, current_previous, skrinName, formName, flag_editable, mode, setupSkrin, table_name,
				divViewMaklumat, paramsButton, "", db1);

		if (mode.equals("edit")) {
			List checkCountKeteranganOB = modelBI.checkCountKeteranganOB(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN,
					ID_PERBICARAAN, "", "OB", db1);
			int totalHM = 0;
			int totalKeterangan = 0;
			Map map_checkCountKeteranganOB = (Map) checkCountKeteranganOB.get(0);
			if (map_checkCountKeteranganOB != null) {
				totalHM = (Integer) map_checkCountKeteranganOB.get("TOTAL_HADIR");
				totalKeterangan = (Integer) map_checkCountKeteranganOB.get("TOTAL_KETERANGAN");
			}

			List checkCountKeteranganTH = modelBI.checkCountKeteranganOB(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN,
					ID_PERBICARAAN, "", "TH", db1);
			int totalTH = 0;
			int totalKeteranganTH = 0;
			Map map_checkCountKeteranganTH = (Map) checkCountKeteranganTH.get(0);
			if (map_checkCountKeteranganTH != null) {
				totalTH = (Integer) map_checkCountKeteranganTH.get("TOTAL_HADIR");
				totalKeteranganTH = (Integer) map_checkCountKeteranganTH.get("TOTAL_KETERANGAN");
			}

			htmlPageSetup += modelBI.setRowPeringatanMesra(session, skrinName, mode, formName, paramsButton, "",
					totalHM, totalKeterangan, totalTH, totalKeteranganTH, db1);
		}

		// htmlPageSetup += "<script>$jquery(document).ready(function
		// (){divToTop(\"view_keputusan\");});</script>";
		/*
		 * } finally { if (db == null) { db1.close(); } }
		 */
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinCarianTukarPegawai(HttpSession session, String skrinName, String command, String formName,
			String mode, String paramsButton, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			String carianTukarPegawaiNO_TUKARPEGAWAI = getParam("carianTukarPegawaiNO_TUKARPEGAWAI");
			String carianTukarPegawaiNO_FAIL = getParam("carianTukarPegawaiNO_FAIL");
			String carianTukarPegawaiID_NEGERIPEGAWAIBARU = getParam("carianTukarPegawaiID_NEGERIPEGAWAIBARU");
			String carianTukarPegawaiID_NEGERIMHN = getParam("carianTukarPegawaiID_NEGERIMHN");
			String carianTukarPegawaiNAMAPEGAWAIASAL = getParam("carianTukarPegawaiNAMAPEGAWAIASAL");
			String carianTukarPegawaiNAMAPEGAWAIBARU = getParam("carianTukarPegawaiNAMAPEGAWAIBARU");
			String carianTukarPegawaiSTATUS_TUKARPEGAWAI = getParam("carianTukarPegawaiSTATUS_TUKARPEGAWAI");
			String carianTukarPegawaiTARIKH_MOHONMULA = getParam("carianTukarPegawaiTARIKH_MOHONMULA");
			String carianTukarPegawaiTARIKH_MOHONAKHIR = getParam("carianTukarPegawaiTARIKH_MOHONAKHIR");
			String carianTukarPegawaiTARIKH_BICARAMULA = getParam("carianTukarPegawaiTARIKH_BICARAMULA");
			String carianTukarPegawaiTARIKH_BICARAAKHIR = getParam("carianTukarPegawaiTARIKH_BICARAAKHIR");

			htmlPageSetup += "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > ";
			htmlPageSetup += "<tr><td width='50%' valign=top>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "No. Tukar Pegawai", "", "",
					"", "", "NO_TUKARPEGAWAI", "", "text", "Y", "", "Y", carianTukarPegawaiNO_TUKARPEGAWAI, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "No. Fail", "", "", "", "",
					"NO_FAIL", "", "text", "Y", "", "Y", carianTukarPegawaiNO_FAIL, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, null, "Negeri Permohonan",
					"", "", "", "", "ID_NEGERIMHN", "", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI",
					"NAMA_NEGERI", "", "", "", "", "", "", "", "", "", formName, carianTukarPegawaiID_NEGERIMHN, 0,
					db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, null,
					"Negeri Pegawai Ganti", "", "", "", "", "ID_NEGERIPEGAWAIBARU", "", "select", "Y", "TBLRUJNEGERI",
					"ID_NEGERI", "KOD_NEGERI", "NAMA_NEGERI", "", "", "", "", "", "", "", "", "", formName,
					carianTukarPegawaiID_NEGERIPEGAWAIBARU, 0, db1);// dynamic ajax call
			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, null,
					"Status Tukar Pegawai", "", "", "", "", "STATUS_TUKARPEGAWAI", "", "select", "Y", "", "", "", "",
					"", "", "", "", "", "", "", "", "", formName, carianTukarPegawaiSTATUS_TUKARPEGAWAI, 0, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Nama Pegawai Asal", "", "",
					"", "", "NAMAPEGAWAIASAL", "", "text", "Y", "", "Y", carianTukarPegawaiNAMAPEGAWAIASAL, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Nama Pegawai Ganti", "",
					"", "", "", "NAMAPEGAWAIBARU", "", "text", "Y", "", "Y", carianTukarPegawaiNAMAPEGAWAIBARU, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, mode, null,
					"Tarikh Mohon Pertukaran (Mula)", "", "", "", "", "TARIKH_MOHONMULA", "", "text", "Y", "10", "Y",
					carianTukarPegawaiTARIKH_MOHONMULA, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, mode, null,
					"Tarikh Mohon Pertukaran (Akhir)", "", "", "", "", "TARIKH_MOHONAKHIR", "", "text", "Y", "10", "Y",
					carianTukarPegawaiTARIKH_MOHONAKHIR, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, mode, null,
					"Tarikh Perbicaraan (Mula)", "", "", "", "", "TARIKH_BICARAMULA", "", "text", "Y", "10", "Y",
					carianTukarPegawaiTARIKH_BICARAMULA, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, mode, null,
					"Tarikh Perbicaraan (Akhir)", "", "", "", "", "TARIKH_BICARAAKHIR", "", "text", "Y", "10", "Y",
					carianTukarPegawaiTARIKH_BICARAAKHIR, 0, db1);

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();

			htmlPageSetup += modelBI.setupButtonCarianTukarPegawai(session, skrinName, formName);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	@SuppressWarnings("unchecked")
	public String setupSkrinCarianBicara(HttpSession session, String skrinName, String command, String formName,
			String mode, String paramsButton, Db db) throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try {
			if (db != null) {
				db1 = db;
			} else {
				db1 = new Db();
			}

			String carianBicaraNO_FAIL = getParam("carianBicaraNO_FAIL");
			String carianBicaraNAMA_SIMATI = getParam("carianBicaraNAMA_SIMATI");
			String carianBicaraPENGENALAN_SIMATI = getParam("carianBicaraPENGENALAN_SIMATI");
			String carianBicaraNAMA_PEMOHON = getParam("carianBicaraNAMA_PEMOHON");
			String carianBicaraPENGENALAN_PEMOHON = getParam("carianBicaraPENGENALAN_PEMOHON");
			String carianBicaraID_NEGERIBICARA = getParam("carianBicaraID_NEGERIBICARA");
			String carianBicaraNAMA_PEGAWAIBICARA = getParam("carianBicaraNAMA_PEGAWAIBICARA");
			String carianBicaraTARIKH_BICARAMULA = getParam("carianBicaraTARIKH_BICARAMULA");
			String carianBicaraTARIKH_BICARAAKHIR = getParam("carianBicaraTARIKH_BICARAAKHIR");
			String carianBicaraBIL_BICARA = getParam("carianBicaraBIL_BICARA");
			String carianBicaraSTATUS_PERBICARAAN = getParam("carianBicaraSTATUS_PERBICARAAN");
			String carianBicaraSTATUS = getParam("carianBicaraSTATUS");

			htmlPageSetup += "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > ";
			htmlPageSetup += "<tr><td width='50%' valign=top>";
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "No. Fail", "", "", "", "",
					"NO_FAIL", "", "text", "Y", "", "Y", carianBicaraNO_FAIL, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Nama Simati", "", "", "",
					"", "NAMA_SIMATI", "", "text", "Y", "", "Y", carianBicaraNAMA_SIMATI, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Pengenalan Simati", "", "",
					"", "", "PENGENALAN_SIMATI", "", "text", "Y", "", "Y", carianBicaraPENGENALAN_SIMATI, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Nama Pemohon", "", "", "",
					"", "NAMA_PEMOHON", "", "text", "Y", "", "Y", carianBicaraNAMA_PEMOHON, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Pengenalan Pemohon", "",
					"", "", "", "PENGENALAN_PEMOHON", "", "text", "Y", "", "Y", carianBicaraPENGENALAN_PEMOHON, 0, db1);
			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, null, "Status Permohonan",
					"", "", "", "", "STATUS", "", "select", "Y", "TBLRUJSTATUS", "ID_STATUS", "", "KETERANGAN", "", "",
					"", "", "", "", "", "", "", formName, carianBicaraSTATUS, 0, db1);// dynamic ajax call

			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_center();
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, null, "Negeri Perbicaraan",
					"", "", "", "", "ID_NEGERIBICARA", "", "select", "Y", "TBLRUJNEGERI", "ID_NEGERI", "KOD_NEGERI",
					"NAMA_NEGERI", "", "", "", "", "", "", "", "", "", formName, carianBicaraID_NEGERIBICARA, 0, db1);// dynamic
																														// ajax
																														// call
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Pegawai Perbicaraan", "",
					"", "", "", "NAMA_PEGAWAIBICARA", "", "text", "Y", "", "Y", carianBicaraNAMA_PEGAWAIBICARA, 0, db1);
			// htmlPageSetup +=
			// modelBI.setRowSelect(session,"","",skrinName,command,mode,null,"Negeri
			// Perbicaraan","","","","","ID_NEGERIBICARA","","select","Y","TBLRUJNEGERI","ID_NEGERI","KOD_NEGERI","NAMA_NEGERI","","","div"+skrinName+"ID_PEGAWAIBICARA","TBLPPKRUJUNIT","ID_PEGAWAIBICARA","ID_NEGERI","ID_UNITPSK","","NAMA_PEGAWAI",formName,carianBicaraID_NEGERIBICARA,0,db1);//dynamic
			// ajax call
			// htmlPageSetup +=
			// modelBI.setRowSelect(session,"","",skrinName,command,mode,null,"Pegawai
			// Perbicaraan","","","","","ID_PEGAWAIBICARA","","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","ID_NEGERI",carianBicaraID_NEGERIBICARA,"","","","","","","",formName,carianBicaraID_PEGAWAIBICARA,0,db1);
			htmlPageSetup += modelBI.setRowSelect(session, "", "", skrinName, command, mode, null, "Status Perbicaraan",
					"", "", "", "", "STATUS_PERBICARAAN", "", "select", "Y", "", "", "", "", "", "", "", "", "", "", "",
					"", "", formName, carianBicaraSTATUS_PERBICARAAN, 0, db1);
			htmlPageSetup += modelBI.setRowText(session, "", "", "", skrinName, mode, null, "Bil. Bicara", "", "", "",
					"", "BIL_BICARA", "", "numbersOnly", "Y", "5", "Y", carianBicaraBIL_BICARA, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, mode, null,
					"Tarikh Perbicaraan (Mula)", "", "", "", "", "TARIKH_BICARAMULA", "", "text", "Y", "10", "Y",
					carianBicaraTARIKH_BICARAMULA, 0, db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session, "", "", skrinName, mode, null,
					"Tarikh Perbicaraan (Akhir)", "", "", "", "", "TARIKH_BICARAAKHIR", "", "text", "Y", "10", "Y",
					carianBicaraTARIKH_BICARAAKHIR, 0, db1);
			htmlPageSetup += modelBI.closeHTMLTable();
			htmlPageSetup += modelBI.openHTMLTableDivided_bottom();

			htmlPageSetup += modelBI.setupButtonCarianBicara(session, skrinName, formName);

		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return htmlPageSetup;

	}

	public String savePerintah(HttpSession session, Map setupKeputusanBeforeSave, String ID_SIMATI,
			String ID_PERMOHONAN, String FLAG_JENIS_KEPUTUSAN, String ID_PERINTAH, String ID_PERMOHONANSIMATI,
			String formName, String ID_PERBICARAAN, String skrinName, Db db) throws Exception {
		myLogger.info("savePerintah FLAG_JENIS_KEPUTUSAN : " + FLAG_JENIS_KEPUTUSAN);
		myLogger.info("savePerintah ID_PERINTAH : " + ID_PERINTAH);
		myLogger.info("savePerintah ID_PERMOHONANSIMATI : " + ID_PERMOHONANSIMATI);
		myLogger.info("savePerintah ID_PERBICARAAN : " + ID_PERBICARAAN);

		// String FLAG_BANTAHAN = getParam(skrinName+"FLAG_BANTAHAN");

		String ID_UNITPSK = getParam(skrinName + "ID_UNITPSK");
		String TARIKH_PERINTAH = getParam(skrinName + "TARIKH_PERINTAH");
		String CHECK_KIV = getParam(skrinName + "CHECK_KIV");
		String DATE_KIV = getParam(skrinName + "DATE_KIV");
		String CATATAN_KIV = getParam(skrinName + "CATATAN_KIV");
		String FLAG_TANGGUH = getParam(skrinName + "FLAG_TANGGUH");
		String FLAG_BATAL = getParam(skrinName + "FLAG_BATAL");

		String CATATAN_PERINTAH_BI = getParam(skrinName + "CATATAN_PERINTAH_BI");
		String CATATAN_KEPUTUSAN_PERBICARAAN = getParam(skrinName + "CATATAN_KEPUTUSAN_PERBICARAAN");
		String INTRO_CATATAN = getParam(skrinName + "INTRO_CATATAN");
		String CATATAN_DOCKIV = getParam(skrinName + "CATATAN_DOCKIV");

		/**
		 * if(!CHECK_KIV.equals("0")) { CATATAN_DOCKIV = ""; }
		 **/
		if (!CHECK_KIV.equals("0") || !CHECK_KIV.equals("3")) // arief add
		{
			CATATAN_DOCKIV = "";
		}

		String CATATAN = getParam(skrinName + "CATATAN");
		String SEBAB_TANGGUH = getParam(skrinName + "SEBAB_TANGGUH");
		String SEBAB_BATAL = getParam(skrinName + "SEBAB_BATAL");
		String KEPUTUSAN_MAHKAMAH = getParam(skrinName + "KEPUTUSAN_MAHKAMAH");
		String CATATAN_KEPUTUSAN = getParam(skrinName + "CATATAN_KEPUTUSAN");

		String ID_PEJABATMAHKAMAH = getParam(skrinName + "ID_PEJABATMAHKAMAH");
		String JENIS_RUJUKAN = getParam(skrinName + "JENIS_RUJUKAN");
		String JENIS_KELUAR_PERINTAH = getParam(skrinName + "JENIS_KELUAR_PERINTAH");
		String ID_INTROPERINTAH = getParam(skrinName + "ID_INTROPERINTAH");

		if (!ID_INTROPERINTAH.equals("")) {
			INTRO_CATATAN = "";
		}

		String ID_INTROPERINTAHFIELD1 = getParam(ID_INTROPERINTAH + skrinName + "ID_INTROPERINTAHFIELD1");
		String ID_INTROPERINTAHFIELD2 = getParam(ID_INTROPERINTAH + skrinName + "ID_INTROPERINTAHFIELD2");
		String ID_INTROPERINTAHFIELD3 = getParam(ID_INTROPERINTAH + skrinName + "ID_INTROPERINTAHFIELD3");

		myLogger.info("savePerintah ID_UNITPSK : " + ID_UNITPSK);
		myLogger.info("savePerintah TARIKH_PERINTAH : " + TARIKH_PERINTAH);
		myLogger.info("savePerintah CHECK_KIV : " + CHECK_KIV);
		myLogger.info("savePerintah DATE_KIV : " + DATE_KIV);
		myLogger.info("savePerintah CATATAN_KIV : " + CATATAN_KIV);
		myLogger.info("savePerintah ID_INTROPERINTAH : " + ID_INTROPERINTAH);

		Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db);
		String ID_FAIL = (String) mainID.get("ID_FAIL");
		String NO_FAIL = (String) mainID.get("NO_FAIL");
		String SEKSYEN = (String) mainID.get("SEKSYEN");

		myLogger.info("savePerintah CATATAN_PERINTAH_BI : " + CATATAN_PERINTAH_BI);
		Connection conn = null;
		Db db1 = null;
		String return_ID_PERINTAH = "";
		String sql = "";
		long id_perintah_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		// try {
		if (db == null) {
			db1 = new Db();
		} else {
			db1 = db;
		}
		Statement stmt = db1.getStatement();
		SQLRenderer r = new SQLRenderer();
		if (ID_PERINTAH.equals("")) {
			id_perintah_long = DB.getNextID(db1, "TBLPPKPERINTAH_SEQ");
			return_ID_PERINTAH = id_perintah_long + "";
		} else {
			return_ID_PERINTAH = ID_PERINTAH;
		}

		if (!ID_PERINTAH.equals("")) {
			r.update("ID_PERINTAH", return_ID_PERINTAH);
		} else {
			r.add("ID_PERINTAH", id_perintah_long);
		}

		r.add("FLAG_JENIS_KEPUTUSAN", FLAG_JENIS_KEPUTUSAN);
		r.add("FLAG_TANGGUH", FLAG_TANGGUH);
		r.add("FLAG_BATAL", FLAG_BATAL);
		r.add("ID_UNITPSK", ID_UNITPSK);
		// r.add("FLAG_BANTAHAN", FLAG_BANTAHAN);
		r.add("ID_INTROPERINTAH", ID_INTROPERINTAH);
		r.add("CHECK_KIV", CHECK_KIV);

		r.add("DATE_KIV", r.unquote(modelBI.setDateFormat(DATE_KIV)));
		r.add("CATATAN_KIV", CATATAN_KIV.toUpperCase());
		// r.add("CATATAN", CATATAN);
		r.add("TARIKH_PERINTAH", r.unquote(modelBI.setDateFormat(TARIKH_PERINTAH)));
		r.add("ID_PERBICARAAN", ID_PERBICARAAN);

		if ((FLAG_JENIS_KEPUTUSAN.equals("1") && FLAG_TANGGUH.equals("5"))
				|| (FLAG_JENIS_KEPUTUSAN.equals("1") && FLAG_TANGGUH.equals("6"))
				|| (FLAG_JENIS_KEPUTUSAN.equals("2") && FLAG_BATAL.equals("1"))) {
			r.add("ID_JENISPERINTAH", "6");
			if (FLAG_TANGGUH.equals("6") || FLAG_BATAL.equals("1")) {
				r.add("ID_PEJABATMAHKAMAH", ID_PEJABATMAHKAMAH);
				if (FLAG_BATAL.equals("1")) {
					r.add("JENIS_KELUAR_PERINTAH", JENIS_KELUAR_PERINTAH);
				} else {
					r.add("JENIS_KELUAR_PERINTAH", "");
				}
			} else {
				r.add("ID_PEJABATMAHKAMAH", "");
				r.add("JENIS_KELUAR_PERINTAH", "");
			}
		} else {
			r.add("ID_JENISPERINTAH", "");
			r.add("ID_PEJABATMAHKAMAH", "");
			r.add("JENIS_KELUAR_PERINTAH", "");
		}

		if (FLAG_JENIS_KEPUTUSAN.equals("0"))// selesai
		{
			r.add("INTROFIELD1", ID_INTROPERINTAHFIELD1);
			r.add("INTROFIELD2", ID_INTROPERINTAHFIELD2);
			r.add("INTROFIELD3", ID_INTROPERINTAHFIELD3);
		} else {
			r.add("INTROFIELD1", "");
			r.add("INTROFIELD2", "");
			r.add("INTROFIELD3", "");
		}

		if (!ID_PERINTAH.equals("")) {
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPPKPERINTAH");
		} else {
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKPERINTAH");
		}
		myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKPERINTAH : " + sql);
		stmt.executeUpdate(sql);

		String ID_STATUS = "";
		String ID_SUBURUSANSTATUS = "";
		// kena check dlu.. kalo status yg sama tengah aktif xperlu add balik
		Map getUrusanStatusAktif = modelBI.getUrusanStatusAktif(session, ID_PERMOHONAN, db1);
		String ID_STATUS_STF = (String) mainID.get("ID_STATUS");

		if (FLAG_JENIS_KEPUTUSAN.equals("0"))// selesai
		{
			modelBI.deleteMaklumatBayaranKoleteral(session, ID_PERMOHONAN, db1);
			// ADD KIV HISTORY
			if (CHECK_KIV.equals("1") && FLAG_JENIS_KEPUTUSAN.equals("0")) {
				// add function simpan history
				modelBI.saveKivHistory(session, ID_PERINTAH, ID_PERBICARAAN, CHECK_KIV, DATE_KIV, CATATAN_KIV, db1);
				modelBicaraData.insertActivityEvent(ID_UNITPSK, ID_PERBICARAAN, NO_FAIL, ID_PERBICARAAN);
			}

			// bayaran perintah
			saveBayaran(session, FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
					ID_PERMOHONAN, "24", db1);
			// bayaran perintah
			saveBayaran(session, FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
					ID_PERMOHONAN, "26", db1);
			// bayaran baitulmal
			saveBayaran(session, FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
					ID_PERMOHONAN, "29", db1);

			ID_STATUS = "41"; // SELESAI TANGGUH PERBICARAAN
			if (SEKSYEN.equals("8")) {
				ID_SUBURUSANSTATUS = "373";
			} else if (SEKSYEN.equals("17")) {
				ID_SUBURUSANSTATUS = "410";
			}
		} else if (FLAG_JENIS_KEPUTUSAN.equals("1"))// tangguh
		{
			// clear kan maklumat bayaran dlu
			modelBI.deleteMaklumatBayaranSelesai(session, ID_PERMOHONAN, db1);
			conn = db1.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE TBLPPKPERINTAH SET SEBAB_TANGGUH = ?, KEPUTUSAN_MAHKAMAH = ? WHERE ID_PERINTAH = ?");
			ps.setString(1, SEBAB_TANGGUH);
			ps.setString(2, KEPUTUSAN_MAHKAMAH);
			ps.setString(3, return_ID_PERINTAH);
			ps.executeUpdate();

			ID_STATUS = "44"; // DEFAULT TANGGUH PERBICARAAN
			if (SEKSYEN.equals("8")) {
				ID_SUBURUSANSTATUS = "374";
			} else if (SEKSYEN.equals("17")) {
				ID_SUBURUSANSTATUS = "420";
			}

			if (FLAG_TANGGUH.equals("5")) {
				String ID_BORANGJ = saveBorangJ(session, FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI,
						formName, ID_PERBICARAAN, skrinName, ID_PERMOHONAN, db1);
				// delete jika ada rekod koleteral
				modelBI.deleteTableByPerbicaraan(ID_PERBICARAAN, "TBLPPKKOLATERALMST", db1);
				modelBI.deleteTableByPerbicaraan(ID_PERBICARAAN, "TBLPPKKOLATERALDTL", db1);
				saveDTL_ROS(session, ID_BORANGJ, ID_PERINTAH, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
						ID_PERMOHONAN, db1);

				if (JENIS_RUJUKAN.equals("1")) {
					ID_STATUS = "174";
					if (SEKSYEN.equals("8")) {
						ID_SUBURUSANSTATUS = "777";
					} else if (SEKSYEN.equals("17")) {
						ID_SUBURUSANSTATUS = "781";
					}
				} else if (JENIS_RUJUKAN.equals("2")) {
					ID_STATUS = "176";
					if (SEKSYEN.equals("8")) {
						ID_SUBURUSANSTATUS = "813";
					} else if (SEKSYEN.equals("17")) {
						ID_SUBURUSANSTATUS = "853";
					}
				}

			} else if (FLAG_TANGGUH.equals("6")) {
				// bayaran koleteral
				saveBayaran(session, FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
						ID_PERMOHONAN, "17", db1);

				ID_STATUS = "172"; // DEFAULT TANGGUH PERBICARAAN (KOLETERAL)
				if (SEKSYEN.equals("8")) {
					ID_SUBURUSANSTATUS = "775";
				} else if (SEKSYEN.equals("17")) {
					ID_SUBURUSANSTATUS = "779";
				}

				if (ID_STATUS_STF.equals("172") && (!getParam("keputusanJUMLAH_BAYARAN17").equals("")
						|| !getParam("keputusanNO_RESIT17").equals("")
						|| !getParam("keputusanTARIKH_BAYARAN17").equals("")
						|| !getParam("keputusanCATATAN_KEPUTUSAN").equals(""))) {
					ID_STATUS = "173"; // DEFAULT SELESAI PERBICARAAN (KOLETERAL)
					if (SEKSYEN.equals("8")) {
						ID_SUBURUSANSTATUS = "776";
					} else if (SEKSYEN.equals("17")) {
						ID_SUBURUSANSTATUS = "780";
					}
				}

				String ID_KOLATERALMST = saveKoleteral(session, FLAG_JENIS_KEPUTUSAN, ID_PERINTAH, ID_PERMOHONANSIMATI,
						formName, ID_PERBICARAAN, skrinName, ID_PERMOHONAN, db1);
				// delete jika ada rekod borangj
				modelBI.deleteTableByPerbicaraan(ID_PERBICARAAN, "TBLPPKBORANGJ", db1);
				modelBI.deleteTableByPerbicaraan(ID_PERBICARAAN, "TBLPPKBORANGJDTL", db1);
				saveDTL_Koleteral(session, ID_KOLATERALMST, ID_PERINTAH, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
						ID_PERMOHONAN, db1);
			}

		} else if (FLAG_JENIS_KEPUTUSAN.equals("2"))// batal
		{
			// clear kan maklumat bayaran dlu
			modelBI.deleteMaklumatBayaranSelesai(session, ID_PERMOHONAN, db1);
			modelBI.deleteMaklumatBayaranKoleteral(session, ID_PERMOHONAN, db1);
			conn = db1.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("UPDATE TBLPPKPERINTAH SET SEBAB_BATAL = ? WHERE ID_PERINTAH = ?");
			ps.setString(1, SEBAB_BATAL);
			ps.setString(2, return_ID_PERINTAH);
			ps.executeUpdate();

			ID_STATUS = "47"; // DEFAULT BATAL PERBICARAAN
			if (SEKSYEN.equals("8")) {
				ID_SUBURUSANSTATUS = "398";
			} else if (SEKSYEN.equals("17")) {
				ID_SUBURUSANSTATUS = "425";
			}
		}

		if (FLAG_JENIS_KEPUTUSAN.equals("0") || FLAG_JENIS_KEPUTUSAN.equals("1") || FLAG_JENIS_KEPUTUSAN.equals("2"))// batal
		{
			conn = db1.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE TBLPPKPERINTAH SET CATATAN_PERINTAH_BI = ?, CATATAN = ?, CATATAN_KEPUTUSAN_PERBICARAAN = ? , INTRO_CATATAN = ?, CATATAN_DOCKIV = ? WHERE ID_PERINTAH = ?");
			ps.setString(1, CATATAN_PERINTAH_BI);
			ps.setString(2, CATATAN);
			String catatKeputusanPerbicaraan = "";
			if (FLAG_JENIS_KEPUTUSAN.equals("1")) {
				catatKeputusanPerbicaraan = SEBAB_TANGGUH;
			} else if (FLAG_JENIS_KEPUTUSAN.equals("2")) {
				catatKeputusanPerbicaraan = SEBAB_BATAL;
			} else {
				catatKeputusanPerbicaraan = CATATAN_KEPUTUSAN_PERBICARAAN;
			}
			ps.setString(3, catatKeputusanPerbicaraan);
			ps.setString(4, INTRO_CATATAN);

			ps.setString(5, CATATAN_DOCKIV);
			ps.setString(6, return_ID_PERINTAH);
			ps.executeUpdate();

			PreparedStatement ps1 = conn.prepareStatement(
					"UPDATE TBLPPKPERBICARAAN SET CATATAN_PERINTAH_TEMP = ?, CATATAN_KP_TEMP = ?, INTRO_CATATAN_TEMP = ?, CATATAN_DOCKIVT = ? WHERE ID_PERBICARAAN = ?");
			ps1.setString(1, "");
			ps1.setString(2, "");
			ps1.setString(3, "");
			ps1.setString(4, "");
			ps1.setString(5, ID_PERBICARAAN);
			ps1.executeUpdate();
		}

		if (!ID_STATUS.equals("") && !ID_SUBURUSANSTATUS.equals("")) {
			if (!ID_STATUS_STF.equals(ID_STATUS) && !ID_STATUS_STF.equals("41") && !ID_STATUS_STF.equals("173")
					&& !ID_STATUS_STF.equals("25") && !ID_STATUS_STF.equals("21") && !ID_STATUS_STF.equals("64")
					&& !ID_STATUS_STF.equals("163") && !ID_STATUS_STF.equals("166") && !ID_STATUS_STF.equals("167")
					&& !ID_STATUS_STF.equals("180") && !ID_STATUS_STF.equals("164") && !ID_STATUS_STF.equals("165")
					&& !ID_STATUS_STF.equals("999")) {

				modelBI.kemaskiniSubUrusanStatusFail(session, ID_PERMOHONAN, USER_ID_SYSTEM, ID_STATUS,
						ID_SUBURUSANSTATUS, ID_FAIL, db);
			}
		}

		// update perubahan maklumat pada table sebenar

		String check_perintah_before_save = setupKeputusanBeforeSave == null ? ""
				: (String) setupKeputusanBeforeSave.get("FLAG_JENIS_KEPUTUSAN") == null ? ""
						: (String) setupKeputusanBeforeSave.get("FLAG_JENIS_KEPUTUSAN");
		if (check_perintah_before_save.equals(""))// comment sementara
		{// table ni akan save bila first time key in keputusan perintah
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "simati", "TBLPPKSIMATI", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "pemohon", "TBLPPKPEMOHON", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "peguam", "TBLPPKPEGUAM", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "waris", "TBLPPKOBPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "waris", "TBLPPKHUBUNGANOBPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "ob", "TBLPPKOBPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "saksi", "TBLPPKOBPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "pemiutang", "TBLPPKOBPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "penghutang", "TBLPPKPENGHUTANG", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "htaah", "TBLPPKHTAPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "htaahx", "TBLPPKHTAPERMOHONAN", db1);
			modelBI.setPerubahanBySkrin(session, ID_PERBICARAAN, "ha", "TBLPPKHAPERMOHONAN", db1);
			// update nilai harta
			modelBI.nilaiHartaTakAliByPermohonan(session, ID_PERMOHONANSIMATI, ID_PERMOHONAN, db1);
			if (FLAG_JENIS_KEPUTUSAN.equals("0"))// selesai
			{
				myLogger.info(">>>>>>>>>>>>> generateRowForPembahagianPerintahSebelum ");
				modelBI.generateListHartaSkrinPembahagian_BicaraInteraktif(session, ID_PERMOHONANSIMATI, db1,
						return_ID_PERINTAH);
				myLogger.info("<<<<<<<<<<<<< generateRowForPembahagianPerintahSebelum ");
			}

		}

		/*
		 * } catch (Exception re) { throw re; }finally { if (db == null) { db1.close();
		 * } }
		 */
		return return_ID_PERINTAH;
	}

	public void saveContentCatatanBI(HttpSession session, String ID_PERINTAH, String CATATAN_PERINTAH_BI, Db db)
			throws Exception {
		Connection conn = null;
		Db db1 = null;
		String sql = "";
		long id_perintah_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			conn = db1.getConnection();
			myLogger.info("UPDATE TBLPPKPERINTAH SET CATATAN_PERINTAH_BI = '" + CATATAN_PERINTAH_BI
					+ "' WHERE ID_PERINTAH = '" + ID_PERINTAH + "'");
			PreparedStatement ps = conn
					.prepareStatement("UPDATE TBLPPKPERINTAH SET CATATAN_PERINTAH_BI = ? WHERE ID_PERINTAH = ?");
			ps.setString(1, CATATAN_PERINTAH_BI);
			ps.setString(2, ID_PERINTAH);
			ps.executeUpdate();
		} catch (Exception re) {
			throw re;
		} finally {
			if (db == null) {
				db1.close();
			}
		}
	}

	public String saveTukarPegawai(HttpSession session, String command, Map setupTukarPegawaiBeforeSave, String ID_FAIL,
			String ID_PERMOHONAN, String ID_TUKARPEGAWAI, String ID_PERMOHONANSIMATI, String formName,
			String ID_PERBICARAAN, String skrinName, String FLAG_MULTiPLE, Db db) throws Exception {

		return saveTukarPegawai(session, command, setupTukarPegawaiBeforeSave, ID_FAIL, ID_PERMOHONAN, ID_TUKARPEGAWAI,
				ID_PERMOHONANSIMATI, formName, ID_PERBICARAAN, skrinName, null, null, null, null, null, null, null,
				null, null, null, null, FLAG_MULTiPLE, db);
	}

	public void saveHistoryJana(HttpSession session, String ID_PERBICARAAN, String ID_PERINTAH, String ID_FAIL,
			String NO_PINDAAN, String HTMLNOTA, String NAMA_PEGAWAI, String BIL_BICARA, String WAKTU_BICARA,
			String NO_FAIL, Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		String return_ID_PERINTAH = "";
		String sql = "";
		long id_perintah_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		String ID_HISTORYJANANOTA = "";
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			ID_HISTORYJANANOTA = DB.getNextID(db1, "TBLPPKHISTORYJANANOTA_SEQ") + "";

			r.add("ID_HISTORYJANANOTA", ID_HISTORYJANANOTA);
			r.add("ID_PERBICARAAN", ID_PERBICARAAN);
			r.add("ID_PERINTAH", ID_PERINTAH);
			r.add("ID_FAIL", ID_FAIL);
			r.add("NAMA_PEGAWAI", NAMA_PEGAWAI);
			r.add("BIL_BICARA", BIL_BICARA);
			r.add("WAKTU_BICARA", WAKTU_BICARA);
			r.add("NO_PINDAAN", NO_PINDAAN);
			r.add("NO_FAIL", NO_FAIL);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKHISTORYJANANOTA");
			myLogger.info("BICARA INTERAKTIF : INSERT saveHistoryJana : " + sql);
			stmt.executeUpdate(sql);

			// Statement stmt = db1.getStatement();
			// SQLRenderer r = new SQLRenderer();
			conn = db1.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("UPDATE TBLPPKHISTORYJANANOTA SET NOTA = ? WHERE ID_HISTORYJANANOTA = ?");
			ps.setString(1, HTMLNOTA);
			ps.setString(2, ID_HISTORYJANANOTA);
			ps.executeUpdate();
		} catch (Exception re) {
			throw re;
		} finally {
			if (db == null) {
				db1.close();
			}
		}
	}

	public String saveTukarPegawai(HttpSession session, String command, Map setupTukarPegawaiBeforeSave, String ID_FAIL,
			String ID_PERMOHONAN, String ID_TUKARPEGAWAI, String ID_PERMOHONANSIMATI, String formName,
			String ID_PERBICARAAN, String skrinName, String ID_PEGAWAIASAL, String ID_PEGAWAIBARU,
			String ID_NEGERIPEGAWAIBARU, String ID_NEGERIPEGAWAIASAL, String CATATAN_PEMOHON, String CATATAN_PELULUS,
			String STATUS_TUKARPEGAWAI, String TARIKH_MOHON, String TARIKH_KEPUTUSAN, String ID_PEMOHONTUKARPEGAWAI,
			String FLAG_DAFTAR_TERUS, String FLAG_MULTiPLE, Db db) throws Exception {
		myLogger.info("saveTukarPegawai ID_PERMOHONANSIMATI : " + ID_PERMOHONANSIMATI);
		myLogger.info("saveTukarPegawai ID_PERBICARAAN : " + ID_PERBICARAAN);
		myLogger.info("saveTukarPegawai ID_TUKARPEGAWAI : " + ID_TUKARPEGAWAI);
		/*
		 * String ID_PEGAWAIASAL = ""; String ID_PEGAWAIBARU = ""; String
		 * ID_NEGERIPEGAWAIBARU = ""; String ID_NEGERIPEGAWAIASAL = ""; String
		 * CATATAN_PEMOHON = ""; String CATATAN_PELULUS = ""; String STATUS_TUKARPEGAWAI
		 * = ""; String TARIKH_MOHON = ""; String TARIKH_KEPUTUSAN = ""; String
		 * ID_PEMOHONTUKARPEGAWAI = ""; String FLAG_DAFTAR_TERUS = "";
		 */
		myLogger.info("skrinName : " + skrinName);
		myLogger.info("ID_PEGAWAIASAL ATAS : " + ID_PEGAWAIASAL);
		if (ID_PEGAWAIASAL == null) {
			myLogger.info("ID_PEGAWAIASAL BAWAH : " + ID_PEGAWAIASAL);
			ID_PEGAWAIASAL = getParam(skrinName + "ID_PEGAWAIASAL");
		}
		if (ID_PEGAWAIBARU == null) {
			ID_PEGAWAIBARU = getParam(skrinName + "ID_PEGAWAIBARU");
		}
		if (ID_NEGERIPEGAWAIBARU == null) {
			ID_NEGERIPEGAWAIBARU = getParam(skrinName + "ID_NEGERIPEGAWAIBARU");
		}
		if (ID_NEGERIPEGAWAIASAL == null) {
			ID_NEGERIPEGAWAIASAL = getParam(skrinName + "ID_NEGERIPEGAWAIASAL");
		}
		if (CATATAN_PEMOHON == null) {
			CATATAN_PEMOHON = getParam(skrinName + "CATATAN_PEMOHON");
		}
		if (CATATAN_PELULUS == null) {
			CATATAN_PELULUS = getParam(skrinName + "CATATAN_PELULUS");
		}
		if (STATUS_TUKARPEGAWAI == null) {
			STATUS_TUKARPEGAWAI = getParam(skrinName + "STATUS_TUKARPEGAWAI");
		}
		if (TARIKH_MOHON == null) {
			TARIKH_MOHON = getParam(skrinName + "TARIKH_MOHON");
		}
		if (TARIKH_KEPUTUSAN == null) {
			TARIKH_KEPUTUSAN = getParam(skrinName + "TARIKH_KEPUTUSAN");
		}
		if (ID_PEMOHONTUKARPEGAWAI == null) {
			ID_PEMOHONTUKARPEGAWAI = getParam(skrinName + "ID_PEMOHONTUKARPEGAWAI");
		}
		if (FLAG_DAFTAR_TERUS == null) {
			FLAG_DAFTAR_TERUS = getParam(skrinName + "FLAG_DAFTAR_TERUS");
		}

		// if FLAG_DAFTAR_TERUS = "Y" terus aprove tukar pegawai

		Connection conn = null;
		Db db1 = null;
		String return_ID_TUKARPEGAWAI = "";
		String sql = "";
		long id_tukarpegawai_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			Map mainID = modelBI.mainID(session, ID_PERBICARAAN, db1);
			String NO_FAIL = (String) mainID.get("NO_FAIL");

			if (ID_TUKARPEGAWAI.equals("")) {
				id_tukarpegawai_long = DB.getNextID(db1, "TBLPPKTUKARPEGAWAI_SEQ");
				return_ID_TUKARPEGAWAI = id_tukarpegawai_long + "";
			} else {
				return_ID_TUKARPEGAWAI = ID_TUKARPEGAWAI;
			}

			if (!ID_TUKARPEGAWAI.equals("")) {
				r.update("ID_TUKARPEGAWAI", return_ID_TUKARPEGAWAI);
			} else {
				r.add("ID_TUKARPEGAWAI", id_tukarpegawai_long);
				r.add("ID_PEMOHON", USER_ID_SYSTEM);
				r.add("TARIKH_MOHON", r.unquote("sysdate"));

				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String tahun = formatter.format(now);

				String id_negerimhn = (String) mainID.get("ID_NEGERIMHN");
				String kod_negeri = modelBI.getKodNegeri(session, id_negerimhn, db1);
				String NO_TUKARPEGAWAI = "TP/" + tahun + "/" + kod_negeri + "/PPK/" + String.format("%06d",
						modelBI.getSeqNoTP(tahun, id_negerimhn, "PPK", "TBLRUJSEQNOTUKARPEGAWAI", db1));
				r.add("NO_TUKARPEGAWAI", NO_TUKARPEGAWAI);

				if (FLAG_DAFTAR_TERUS.equals("Y")) {
					STATUS_TUKARPEGAWAI = "2";
				}
			}
			r.add("ID_FAIL", ID_FAIL);
			r.add("ID_PEGAWAIASAL", ID_PEGAWAIASAL);
			r.add("ID_PEGAWAIBARU", ID_PEGAWAIBARU);
			r.add("ID_NEGERIPEGAWAIASAL", ID_NEGERIPEGAWAIASAL);
			r.add("ID_NEGERIPEGAWAIBARU", ID_NEGERIPEGAWAIBARU);
			r.add("CATATAN_PEMOHON", CATATAN_PEMOHON.toUpperCase());
			r.add("CATATAN_PELULUS", CATATAN_PELULUS.toUpperCase());

			if (command.equals("lulusTukarPegawai")) {
				STATUS_TUKARPEGAWAI = "2";
			} else if (command.equals("tolakTukarPegawai")) {
				STATUS_TUKARPEGAWAI = "3";
			}

			r.add("STATUS_TUKARPEGAWAI", STATUS_TUKARPEGAWAI);
			// r.add("TARIKH_MOHON", r.unquote(modelBI.setDateFormat(TARIKH_MOHON)));

			r.add("ID_PERBICARAAN", ID_PERBICARAAN);
			r.add("ID_PEMOHONTUKARPEGAWAI", ID_PEMOHONTUKARPEGAWAI);

			if (STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3")) {
				r.add("ID_PELULUS", USER_ID_SYSTEM);
				r.add("TARIKH_KEPUTUSAN", r.unquote("sysdate"));
			}

			Map getDetailPegawaiAsal = modelBI.getDetailPegawai(session, ID_PEGAWAIASAL, "", "", db1);
			String user_id_asal_pegawai = "";
			String user_id_baru_pegawai = "";
			String user_name_asal_pegawai = "";
			String user_name_baru_pegawai = "";
			if (getDetailPegawaiAsal != null) {
				user_name_asal_pegawai = (String) getDetailPegawaiAsal.get("NAMA_PEGAWAI") == null ? ""
						: (String) getDetailPegawaiAsal.get("NAMA_PEGAWAI");
				user_id_asal_pegawai = (String) getDetailPegawaiAsal.get("USER_ID") == null ? ""
						: (String) getDetailPegawaiAsal.get("USER_ID");
			}
			Map getDetailPegawaiBaru = modelBI.getDetailPegawai(session, ID_PEGAWAIBARU, "", "", db1);
			if (getDetailPegawaiBaru != null) {
				user_name_baru_pegawai = (String) getDetailPegawaiBaru.get("NAMA_PEGAWAI") == null ? ""
						: (String) getDetailPegawaiBaru.get("NAMA_PEGAWAI");
				user_id_baru_pegawai = (String) getDetailPegawaiBaru.get("USER_ID") == null ? ""
						: (String) getDetailPegawaiBaru.get("USER_ID");
			}

			String USER_ID_PEGAWAIASAL = "";
			if (!user_id_asal_pegawai.equals("")) {
				USER_ID_PEGAWAIASAL = user_id_asal_pegawai;
			} else {
				if (!user_name_asal_pegawai.equals("")) {
					Map getDetailUsersAsal = modelBI.getDetailUsers(session, user_name_asal_pegawai.toUpperCase(), "",
							"", db1);
					if (getDetailUsersAsal != null) {
						USER_ID_PEGAWAIASAL = (String) getDetailUsersAsal.get("USER_ID") == null ? ""
								: (String) getDetailUsersAsal.get("USER_ID");
					}
				}
			}

			String USER_ID_PEGAWAIBARU = "";
			if (!user_id_baru_pegawai.equals("")) {
				USER_ID_PEGAWAIBARU = user_id_baru_pegawai;
			} else {
				if (!user_name_baru_pegawai.equals("")) {
					Map getDetailUsersBaru = modelBI.getDetailUsers(session, user_name_baru_pegawai.toUpperCase(), "",
							"", db1);
					if (getDetailUsersBaru != null) {
						USER_ID_PEGAWAIBARU = (String) getDetailUsersBaru.get("USER_ID") == null ? ""
								: (String) getDetailUsersBaru.get("USER_ID");
					}
				}
			}

			r.add("USER_ID_PEGAWAIASAL", USER_ID_PEGAWAIASAL);
			r.add("USER_ID_PEGAWAIBARU", USER_ID_PEGAWAIBARU);

			if (!ID_TUKARPEGAWAI.equals("")) {
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKTUKARPEGAWAI");
			} else {
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKTUKARPEGAWAI");
			}
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKTUKARPEGAWAI : " + sql);
			stmt.executeUpdate(sql);

			// lulus
			if (STATUS_TUKARPEGAWAI.equals("2")) {
				// update pegawai bicara

				// nama pegawai dalam notis tidak berubah

				r.clear();
				r.update("ID_PERBICARAAN", ID_PERBICARAAN);
				r.add("ID_UNITPSK_NEW", ID_PEGAWAIBARU);
				sql = r.getSQLUpdate("TBLPPKPERBICARAAN");
				myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKPERBICARAAN : " + sql);
				stmt.executeUpdate(sql);

				AuditTrail.logActivity(this, session, "UPD",
						"TBLPPKPERBICARAAN [TUKAR PEGAWAI BICARA BARU : ID_UNITPSK ASAL (" + ID_PEGAWAIASAL
								+ "), ID_UNITPSK BARU (" + ID_PEGAWAIBARU + ")]",
						db1);

				String TARIKH_BICARA = "";
				Map viewPerbicaraan = modelBI.viewPerbicaraan(session, ID_PERBICARAAN, ID_PERMOHONAN, db1);
				if (viewPerbicaraan != null) {
					TARIKH_BICARA = (String) viewPerbicaraan.get("TARIKH_BICARA");
				}
				String id_unit = modelBI.idUnitTukarPegawai(ID_NEGERIPEGAWAIBARU);

				if (!id_unit.equals("")) {
					modelBI.saveTableBU(session, USER_ID_PEGAWAIBARU, USER_ID_SYSTEM, id_unit, ID_NEGERIPEGAWAIBARU,
							TARIKH_BICARA, db1);
				}
			}

			if (FLAG_MULTiPLE.equals("N")) {
				// emel
				modelBI.hantarEmelTukarPegawai(session, ID_PERBICARAAN, return_ID_TUKARPEGAWAI, ID_PERMOHONAN,
						ID_PERMOHONANSIMATI, "", "", "", "", "", STATUS_TUKARPEGAWAI, FLAG_MULTiPLE, "", "", null, db1);
			}

			String event_text = "PERBICARAAN PUSAKA KECIL BAGI FAIL " + NO_FAIL;
			String event_location = "";

			if (STATUS_TUKARPEGAWAI.equals("2")) {
				Map mainID_after = modelBI.mainID(session, ID_PERBICARAAN, db1);
				String MASA_BICARA = (String) mainID_after.get("MASA_BICARA");
				String TARIKH_BICARA_M = (String) mainID_after.get("TARIKH_BICARA");
				String TEMPAT_BICARA = (String) mainID_after.get("TEMPAT_BICARA");
				String ALAMAT_BICARA1 = (String) mainID_after.get("ALAMAT_BICARA1");
				String ALAMAT_BICARA2 = (String) mainID_after.get("ALAMAT_BICARA2");
				String ALAMAT_BICARA3 = (String) mainID_after.get("ALAMAT_BICARA3");
				String POSKOD = (String) mainID_after.get("POSKOD");
				String BANDAR = (String) mainID_after.get("BANDAR");
				String NAMA_NEGERI_BICARA = (String) mainID_after.get("NAMA_NEGERI_BICARA");
				String JENIS_MASA_BICARA = (String) mainID_after.get("JENIS_MASA_BICARA");
				String ID_UNITPSK = (String) mainID_after.get("ID_UNITPSK");
				String NAMA_PEGAWAI = (String) mainID_after.get("NAMA_PEGAWAI");

				Map getDetailPSK = modelBI.getDetailPSK(session, ID_PEGAWAIBARU, db1);
				String NAMA_PEGAWAI_PSK = "";
				if (getDetailPSK != null) {
					NAMA_PEGAWAI_PSK = (String) getDetailPSK.get("NAMA_PEGAWAI");
				}

				if (!"".equals(TEMPAT_BICARA))
					event_location = event_location + TEMPAT_BICARA + ", ";
				if (!"".equals(ALAMAT_BICARA1))
					event_location = event_location + ALAMAT_BICARA1 + ", ";
				if (!"".equals(ALAMAT_BICARA2))
					event_location = event_location + ALAMAT_BICARA2 + ", ";
				if (!"".equals(ALAMAT_BICARA3))
					event_location = event_location + ALAMAT_BICARA3 + ", ";
				if (!"".equals(POSKOD))
					event_location = event_location + POSKOD + ", ";
				if (!"".equals(BANDAR))
					event_location = event_location + BANDAR + ", ";
				if (!"".equals(NAMA_NEGERI_BICARA))
					event_location = event_location + " " + NAMA_NEGERI_BICARA;

				String USER_LOGIN_PSK = "";

				if (!NAMA_PEGAWAI_PSK.equals("")) {
					USER_LOGIN_PSK = modelBI.getUserLoginPSK(session, NAMA_PEGAWAI_PSK, db1);
				}

				myLogger.info("BI - save event  >>>>>> ID_UNITPSK : " + ID_UNITPSK);
				myLogger.info("BI - save event  >>>>>> userLoginPegawai : ");
				myLogger.info("BI - save event  >>>>>> id_perbicaraan : " + ID_PERBICARAAN);
				myLogger.info("BI - save event  >>>>>> event_text : " + event_text);
				myLogger.info("BI - save event  >>>>>> event_location : " + event_location);
				myLogger.info("BI - save event  >>>>>> tarikh_bicara : " + TARIKH_BICARA_M);
				myLogger.info("BI - save event  >>>>>> masa_bicara : " + MASA_BICARA);
				myLogger.info("BI - save event  >>>>>> socJenisWaktu : " + JENIS_MASA_BICARA);
				myLogger.info("BI - save event  >>>>>> NAMA_PEGAWAI : " + NAMA_PEGAWAI);
				myLogger.info("BI - save event  >>>>>> USER_LOGIN_PSK : " + USER_LOGIN_PSK);

				if (!USER_LOGIN_PSK.equals("")) {
					modelBI.saveActivityEvent(USER_LOGIN_PSK, Long.parseLong(ID_PERBICARAAN), event_text,
							event_location, TARIKH_BICARA_M, MASA_BICARA, JENIS_MASA_BICARA);
				}
			}

		} catch (Exception re) {
			throw re;
		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return return_ID_TUKARPEGAWAI;
	}

	public void saveDTL_ROS(HttpSession session, String ID_BORANGJ, String ID_PERINTAH, String ID_PERMOHONANSIMATI,
			String ID_PERBICARAAN, String skrinName, String ID_PERMOHONAN, Db db) throws Exception {
		modelBI.deleteTableByPerbicaraan(ID_PERBICARAAN, "TBLPPKBORANGJDTL", db);
		String[] sub_ob = request.getParameterValues("sub_ob");
		if (sub_ob != null) {
			for (int i = 0; i < sub_ob.length; i++) {
				myLogger.info("saveDTL_ROS >> sub_ob : " + sub_ob);
				// insertFunction TBLPPKBORANGJDTL
				modelBI.simpanInsertBorangjDTL(session, ID_BORANGJ, sub_ob[i] + "", ID_PERBICARAAN, db);
			}
		}
	}

	public void saveDTL_Koleteral(HttpSession session, String ID_KOLATERALMST, String ID_PERINTAH,
			String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String ID_PERMOHONAN, Db db)
			throws Exception {
		modelBI.deleteTableByPerbicaraan(ID_PERBICARAAN, "TBLPPKKOLATERALDTL", db);
		String[] subPlantif_ob = request.getParameterValues("subPlantif_ob");
		if (subPlantif_ob != null) {
			for (int i = 0; i < subPlantif_ob.length; i++) {
				myLogger.info("saveDTL_Koleteral >> subPlantif_ob : " + subPlantif_ob);
				// insertFunction TBLPPKKOLATERALDTL
				modelBI.simpanInsertKolateralDTL(session, ID_KOLATERALMST, subPlantif_ob[i] + "", "PL", ID_PERBICARAAN,
						db);
			}
		}

		String[] subDefendan_ob = request.getParameterValues("subDefendan_ob");
		if (subDefendan_ob != null) {
			for (int i = 0; i < subDefendan_ob.length; i++) {
				myLogger.info("saveDTL_Koleteral >> subDefendan_ob : " + subDefendan_ob);
				// insertFunction TBLPPKKOLATERALDTL
				modelBI.simpanInsertKolateralDTL(session, ID_KOLATERALMST, subDefendan_ob[i] + "", "DF", ID_PERBICARAAN,
						db);
			}
		}
	}

	public String saveBayaran(HttpSession session, String FLAG_JENIS_KEPUTUSAN, String ID_PERINTAH,
			String ID_PERMOHONANSIMATI, String ID_PERBICARAAN, String skrinName, String ID_PERMOHONAN,
			String ID_JENISBAYARAN, Db db) throws Exception {

		// bayaran perintah
		String ID_BAYARAN = getParam(skrinName + "ID_BAYARAN" + ID_JENISBAYARAN);
		String JUMLAH_BAYARAN = getParam(skrinName + "JUMLAH_BAYARAN" + ID_JENISBAYARAN);
		String NO_RESIT = getParam(skrinName + "NO_RESIT" + ID_JENISBAYARAN);
		String TARIKH_BAYARAN = getParam(skrinName + "TARIKH_BAYARAN" + ID_JENISBAYARAN);

		myLogger.info("savePerintah ID_BAYARAN : " + ID_BAYARAN);
		myLogger.info("savePerintah JUMLAH_BAYARAN : " + JUMLAH_BAYARAN);
		myLogger.info("savePerintah NO_RESIT : " + NO_RESIT);
		myLogger.info("savePerintah TARIKH_BAYARAN : " + TARIKH_BAYARAN);

		Db db1 = null;
		String return_ID_BAYARAN = "";
		String sql = "";
		long id_bayaran_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		// try {
		if (db == null) {
			db1 = new Db();
		} else {
			db1 = db;
		}
		Statement stmt = db1.getStatement();
		SQLRenderer r = new SQLRenderer();
		if (ID_BAYARAN.equals("")) {
			id_bayaran_long = DB.getNextID(db1, "TBLPPKBAYARAN_SEQ");
			return_ID_BAYARAN = id_bayaran_long + "";
		} else {
			return_ID_BAYARAN = ID_BAYARAN;
		}

		if (!ID_BAYARAN.equals("")) {
			r.update("ID_BAYARAN", return_ID_BAYARAN);
		} else {
			r.add("ID_BAYARAN", id_bayaran_long);
		}

		r.add("ID_JENISBAYARAN", ID_JENISBAYARAN);
		r.add("NO_RESIT", NO_RESIT.toUpperCase());
		r.add("TARIKH_BAYARAN", r.unquote(modelBI.setDateFormat(TARIKH_BAYARAN)));
		r.add("JUMLAH_BAYARAN", JUMLAH_BAYARAN);
		r.add("ID_PERMOHONAN", ID_PERMOHONAN);
		if (!ID_BAYARAN.equals("")) {
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("TBLPPKBAYARAN");
		} else {
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLPPKBAYARAN");
		}
		myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKBAYARAN : " + sql);
		stmt.executeUpdate(sql);
		/*
		 * } catch (Exception re) { throw re; }finally { if (db == null) { db1.close();
		 * } }
		 */
		return return_ID_BAYARAN;
	}

	public String saveBorangJ(HttpSession session, String FLAG_JENIS_KEPUTUSAN, String ID_PERINTAH,
			String ID_PERMOHONANSIMATI, String formName, String ID_PERBICARAAN, String skrinName, String ID_PERMOHONAN,
			Db db) throws Exception {

		// bayaran perintah
		String ID_BORANGJ = getParam(skrinName + "ID_BORANGJ");
		String TARIKH_MOHON = getParam(skrinName + "TARIKH_MOHON");
		String JENIS_RUJUKAN = getParam(skrinName + "JENIS_RUJUKAN");
		String FLAG_RUJUKAN = getParam(skrinName + "FLAG_RUJUKAN");
		String ID_NEGERIMAHKAMAH = getParam(skrinName + "ID_NEGERIMAHKAMAH");
		String ID_MAHKAMAH = getParam(skrinName + "ID_MAHKAMAH");
		String NAMA_PEJABAT = getParam(skrinName + "NAMA_PEJABAT");
		String ALAMAT1 = getParam(skrinName + "ALAMAT1");
		String ALAMAT2 = getParam(skrinName + "ALAMAT2");
		String ALAMAT3 = getParam(skrinName + "ALAMAT3");
		String POSKOD = getParam(skrinName + "POSKOD");
		String ID_NEGERI = getParam(skrinName + "ID_NEGERI");
		String ID_BANDAR = getParam(skrinName + "ID_BANDAR");
		String NO_TEL = getParam(skrinName + "NO_TEL");
		String NO_FAX = getParam(skrinName + "NO_FAX");
		String CATATAN1 = getParam(skrinName + "CATATAN1");
		String KEPUTUSAN_MAHKAMAH = getParam(skrinName + "KEPUTUSAN_MAHKAMAH");

		myLogger.info("saveBorangJ ID_BORANGJ : " + ID_BORANGJ);
		myLogger.info("saveBorangJ TARIKH_MOHON : " + TARIKH_MOHON);
		myLogger.info("saveBorangJ JENIS_RUJUKAN : " + JENIS_RUJUKAN);
		myLogger.info("saveBorangJ FLAG_RUJUKAN : " + FLAG_RUJUKAN);
		myLogger.info("saveBorangJ ID_NEGERIMAHKAMAH : " + ID_NEGERIMAHKAMAH);
		myLogger.info("saveBorangJ ID_MAHKAMAH : " + ID_MAHKAMAH);
		myLogger.info("saveBorangJ NAMA_PEJABAT : " + NAMA_PEJABAT);
		myLogger.info("saveBorangJ ALAMAT1 : " + ALAMAT1);
		myLogger.info("saveBorangJ ALAMAT2 : " + ALAMAT2);
		myLogger.info("saveBorangJ ALAMAT3 : " + ALAMAT3);
		myLogger.info("saveBorangJ POSKOD : " + POSKOD);
		myLogger.info("saveBorangJ ID_NEGERI : " + ID_NEGERI);
		myLogger.info("saveBorangJ ID_BANDAR : " + ID_BANDAR);
		myLogger.info("saveBorangJ NO_TEL : " + NO_TEL);
		myLogger.info("saveBorangJ NO_FAX : " + NO_FAX);
		myLogger.info("saveBorangJ CATATAN1 : " + CATATAN1);
		myLogger.info("saveBorangJ KEPUTUSAN_MAHKAMAH : " + KEPUTUSAN_MAHKAMAH);

		Connection conn = null;
		Db db1 = null;
		String return_ID_BORANGJ = "";
		String sql = "";
		long id_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			if (ID_BORANGJ.equals("")) {
				id_long = DB.getNextID(db1, "TBLPPKBORANGJ_SEQ");
				return_ID_BORANGJ = id_long + "";
			} else {
				return_ID_BORANGJ = ID_BORANGJ;
			}

			if (!ID_BORANGJ.equals("")) {
				r.update("ID_BORANGJ", return_ID_BORANGJ);
			} else {
				r.add("ID_BORANGJ", id_long);
			}

			r.add("JENIS_RUJUKAN", JENIS_RUJUKAN);
			r.add("FLAG_RUJUKAN", FLAG_RUJUKAN);
			r.add("TARIKH_MOHON", r.unquote(modelBI.setDateFormat(TARIKH_MOHON)));
			r.add("ID_NEGERIMAHKAMAH", ID_NEGERIMAHKAMAH);
			r.add("ID_MAHKAMAH", ID_MAHKAMAH);
			r.add("NAMA_PEJABAT", NAMA_PEJABAT.toUpperCase());
			r.add("ALAMAT1", ALAMAT1.toUpperCase());
			r.add("ALAMAT2", ALAMAT2.toUpperCase());
			r.add("ALAMAT3", ALAMAT3.toUpperCase());
			r.add("POSKOD", POSKOD);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_BANDAR", ID_BANDAR);
			r.add("NO_TEL", NO_TEL);
			r.add("NO_FAX", NO_FAX);
			r.add("ID_PERBICARAAN", ID_PERBICARAAN);

			if (!ID_BORANGJ.equals("")) {
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKBORANGJ");
			} else {
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKBORANGJ");
			}
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKBORANGJ : " + sql);
			stmt.executeUpdate(sql);

			conn = db1.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE TBLPPKBORANGJ SET CATATAN1 = ?, KEPUTUSAN_MAHKAMAH = ? WHERE ID_BORANGJ = ?");
			ps.setString(1, CATATAN1);
			ps.setString(2, KEPUTUSAN_MAHKAMAH);
			ps.setString(3, return_ID_BORANGJ);
			ps.executeUpdate();
		} catch (Exception re) {
			throw re;
		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return return_ID_BORANGJ;
	}

	public String saveKoleteral(HttpSession session, String FLAG_JENIS_KEPUTUSAN, String ID_PERINTAH,
			String ID_PERMOHONANSIMATI, String formName, String ID_PERBICARAAN, String skrinName, String ID_PERMOHONAN,
			Db db) throws Exception {

		// bayaran perintah
		String ID_KOLATERALMST = getParam(skrinName + "ID_KOLATERALMST");
		String TARIKH_BICARA = getParam(skrinName + "TARIKH_BICARA");
		String TARIKH_PERAKUAN = getParam(skrinName + "TARIKH_PERAKUAN");
		String MASA_BICARA = getParam(skrinName + "MASA_BICARA");
		String ID_UNITPSK = getParam(skrinName + "ID_UNITPSK");
		String ID_NEGERIBICARA = getParam(skrinName + "ID_NEGERI");
		String SEBAB_PERTELINGKAHAN = getParam(skrinName + "SEBAB_PERTELINGKAHAN");
		String CATATAN_KEPUTUSAN = getParam(skrinName + "CATATAN_KEPUTUSAN");

		myLogger.info("saveKoleteral ID_KOLATERALMST : " + ID_KOLATERALMST);
		myLogger.info("saveKoleteral TARIKH_BICARA : " + TARIKH_BICARA);
		myLogger.info("saveKoleteral TARIKH_PERAKUAN : " + TARIKH_PERAKUAN);
		myLogger.info("saveKoleteral MASA_BICARA : " + MASA_BICARA);
		myLogger.info("saveKoleteral ID_UNITPSK : " + ID_UNITPSK);
		myLogger.info("saveKoleteral ID_NEGERIBICARA : " + ID_NEGERIBICARA);

		Connection conn = null;
		Db db1 = null;
		String return_ID_KOLATERALMST = "";
		String sql = "";
		long id_long = 0;
		String USER_ID_SYSTEM = (String) session.getAttribute("_ekptg_user_id");
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			if (ID_KOLATERALMST.equals("")) {
				id_long = DB.getNextID(db1, "TBLPPKKOLATERALMST_SEQ");
				return_ID_KOLATERALMST = id_long + "";
			} else {
				return_ID_KOLATERALMST = ID_KOLATERALMST;
			}

			if (!ID_KOLATERALMST.equals("")) {
				r.update("ID_KOLATERALMST", return_ID_KOLATERALMST);
			} else {
				r.add("ID_KOLATERALMST", id_long);
			}

			myLogger.info("saveKoleteral ID_KOLATERALMST : " + ID_KOLATERALMST);
			myLogger.info("saveKoleteral TARIKH_BICARA : " + TARIKH_BICARA);
			myLogger.info("saveKoleteral TARIKH_PERAKUAN : " + TARIKH_PERAKUAN);
			myLogger.info("saveKoleteral MASA_BICARA : " + MASA_BICARA);
			myLogger.info("saveKoleteral ID_UNITPSK : " + ID_UNITPSK);
			myLogger.info("saveKoleteral ID_NEGERIBICARA : " + ID_NEGERIBICARA);

			r.add("MASA_BICARA", MASA_BICARA);
			r.add("ID_UNITPSK", ID_UNITPSK);
			r.add("ID_NEGERIBICARA", ID_NEGERIBICARA);
			r.add("TARIKH_BICARA", r.unquote(modelBI.setDateFormat(TARIKH_BICARA)));
			r.add("TARIKH_PERAKUAN", r.unquote(modelBI.setDateFormat(TARIKH_PERAKUAN)));

			r.add("ID_PERBICARAAN", ID_PERBICARAAN);

			if (!ID_KOLATERALMST.equals("")) {
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKKOLATERALMST");
			} else {
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPKKOLATERALMST");
			}
			myLogger.info("BICARA INTERAKTIF : INSERT / UPDATE TBLPPKKOLATERALMST : " + sql);
			stmt.executeUpdate(sql);

			conn = db1.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE TBLPPKKOLATERALMST SET SEBAB_PERTELINGKAHAN = ?,CATATAN_KEPUTUSAN = ? WHERE ID_KOLATERALMST = ?");
			ps.setString(1, SEBAB_PERTELINGKAHAN);
			ps.setString(2, CATATAN_KEPUTUSAN);
			ps.setString(3, return_ID_KOLATERALMST);
			ps.executeUpdate();
		} catch (Exception re) {
			throw re;
		} finally {
			if (db == null) {
				db1.close();
			}
		}
		return return_ID_KOLATERALMST;
	}

	public String savePerubahan(HttpSession session, String command, String ID_SEJARAHBIMAIN, Map mapDataAsal,
			String skrinName, String ID_PERMOHONANSIMATI, String VALUE_getValue, String FIELD_PK, String VALUE_PK,
			String formName, String table_name, String aktiviti, String ID_PERBICARAAN, String skrinNamePemohonOb,
			String ID_SIMATI, String ID_PERMOHONAN, Db db) throws Exception {
		// Map mapDataAsal = modelBI.getValueColumn(session, "", VALUE_getValue,
		// table_name, db);
		myLogger.info(":::: mapDataAsal :::: " + mapDataAsal);
		Map mapDataBaru = null;
		List listColumnByTable = modelBI.listColumnByTable(session, skrinName, table_name, db);
		if (listColumnByTable.size() != 0) {
			mapDataBaru = Collections.synchronizedMap(new HashMap());
			for (int i = 0; i < listColumnByTable.size(); i++) {
				Map map_column_name = (Map) listColumnByTable.get(i);
				String column_name = (String) map_column_name.get("COLUMN_NAME");
				if (!aktiviti.equals("DELETE")) {
					// myLogger.info(":::: GET :::: "+column_name+" skrinName : "+skrinName+" VALUE
					// : "+request.getParameter(skrinName+column_name));
					if (request.getParameter(skrinName + column_name) != null) {
						String val = getParam(skrinName + column_name);
						/*
						 * if(column_name.equals("ID_SIMATI")) { val = ID_SIMATI; } else
						 * if(column_name.equals("ID_PERMOHONAN")) { val = ID_PERMOHONAN; } else
						 * if(column_name.equals("ID_PERMOHONANSIMATI")) { val = ID_PERMOHONANSIMATI; }
						 */
						mapDataBaru.put(column_name, val);
					} else {
						if (column_name.equals("NO_KP_BARU")) {
							// special case untuk no kp baru
							mapDataBaru.put(column_name,
									getParam(skrinName + column_name + "1") + getParam(skrinName + column_name + "2")
											+ getParam(skrinName + column_name + "3"));
						}
						// special kes
						// kalo OB adalah pemohon
						else if (column_name.equals("NAMA_PEMOHON")) {
							if (request.getParameter(skrinName + "NAMA_OB") != null) {
								mapDataBaru.put(column_name, getParam(skrinName + "NAMA_OB"));
							}
						}
						// special kes
						// kalo pemohon adalah ob
						else if (column_name.equals("NAMA_OB")) {
							if (request.getParameter(skrinName + "NAMA_PEMOHON") != null) {
								mapDataBaru.put(column_name, getParam(skrinName + "NAMA_PEMOHON"));
							}
						}
					}
				} else {
					// untuk delete baca balik suma rekod asal & letak dekat baru
					if (mapDataAsal != null) {
						String value = "";
						if (mapDataAsal.get(column_name) != null) {
							value = mapDataAsal.get(column_name) == null ? "" : (String) mapDataAsal.get(column_name);
							mapDataBaru.put(column_name, value);
						}
					}
				}
			}
		}
		myLogger.info(":::: mapDataBaru :::: " + mapDataBaru);
		String skrin_asal = "";
		if (!skrinNamePemohonOb.equals("")) {
			skrin_asal = skrinName;
			skrinName = skrinNamePemohonOb;
		}
		return checkForHistory(session, command, skrin_asal, skrinName, ID_SEJARAHBIMAIN, table_name, FIELD_PK,
				VALUE_PK, mapDataAsal, mapDataBaru, aktiviti, ID_PERBICARAAN, ID_PERMOHONANSIMATI, db);
	}

	public String checkForHistory(HttpSession session, String command, String skrin_asal, String skrinName,
			String ID_SEJARAHBIMAIN, String tableName, String FIELD_PK, String VALUE_PK, Map ASAL, Map BARU,
			String AKTIVITI, String ID_PERBICARAAN, String ID_PERMOHONANSIMATI, Db db) throws Exception {

		String id_history_utama = "";
		Integer count_ada_changes = 0;
		Set<String> keys_check = BARU.keySet();
		boolean setHubungan = false;
		String id_obHubungan = "";
		String id_obParent = "";
		String id_saudaraHubungan = "";

		for (String key_c : keys_check) {
			String rekod_asal = null;
			if (ASAL != null) {
				rekod_asal = (String) ASAL.get(key_c);
			}
			String rekod_baru = (String) BARU.get(key_c);

			if (ASAL != null) {
				if (!(rekod_asal.toUpperCase().trim()).equals(rekod_baru.toUpperCase().trim())
						&& !((rekod_asal.equals("") && rekod_baru.equals("0"))
								|| (rekod_asal.equals("0") && rekod_baru.equals("")))
						|| (AKTIVITI.equals("DELETE") && !rekod_asal.equals(""))) {
					count_ada_changes++;
					// myLogger.info(" key_c : "+key_c+" rekod_asal : "+rekod_asal+" rekod_baru :
					// "+rekod_baru);
				}
			} else {
				count_ada_changes++;
			}
		}
		myLogger.info(" checkForHistory ada changes : " + count_ada_changes);
		if (count_ada_changes == 0) {
			modelBI.deleteHISTORY("deleteHistory", ID_SEJARAHBIMAIN, tableName, FIELD_PK, VALUE_PK, ID_PERBICARAAN, db);
		} else {
			modelBI.deleteHISTORY(command, ID_SEJARAHBIMAIN, tableName, FIELD_PK, VALUE_PK, ID_PERBICARAAN, db);
		}
		if (count_ada_changes > 0) {
			// create seq kalo ada data baru
			// suma table telibat kecuali pemohon simati
			long ID_PK = 0;
			if (VALUE_PK.equals("")) {
				ID_PK = DB.getNextID(db, tableName + "_SEQ");
				myLogger.info("SEQ ID_PK :::::: " + ID_PK);
				VALUE_PK = ID_PK + "";
			}

			/*
			 * String setSkrinName = skrinName; if(skrinName.equals("pemohon") &&
			 * tableName.equals("TBLPPKOBPERMOHONAN")) { setSkrinName = ""; } else
			 * if(skrinName.equals("waris") || skrinName.equals("ob") ||
			 * skrinName.equals("saksi") || skrinName.equals("pemiutang")) { setSkrinName =
			 * "pemohon"; }
			 */
			id_history_utama = modelBI.saveHistoryUtama(session, ID_SEJARAHBIMAIN, skrinName, tableName, FIELD_PK,
					VALUE_PK, AKTIVITI, ID_PERBICARAAN, ID_PERMOHONANSIMATI, db);
			Set<String> keys = BARU.keySet();
			for (String key : keys) {
				String rekod_asal = null;
				if (ASAL != null) {
					rekod_asal = (String) ASAL.get(key);
				}
				String rekod_baru = (String) BARU.get(key);
				String keterangan_asal = "";
				String keterangan_baru = "";
				boolean rekodChanges = false;

				if (ASAL != null) {
					if (key.equals("ID_OB")) {
						long ID_OB_PK = 0;
						if (rekod_baru.equals("")) {
							ID_OB_PK = DB.getNextID(db, "TBLPPKOB_SEQ");
							myLogger.info("SEQ ID_OB_PK :::::: " + ID_OB_PK);
						}

						myLogger.info("getParam(" + skrinName + " -- " + key + ") :::::: " + getParam(skrinName + key));

						/*
						 * else {
						 * myLogger.info("getParam(skrinName+key) :::::: "+getParam(skrinName+key));
						 * ID_OB_PK = Long.parseLong(getParam(skrinName+key)); rekod_baru = ID_OB_PK+"";
						 * }
						 */
						id_obHubungan = rekod_baru;
					} else if (key.equals("ID_SAUDARA")) {
						id_saudaraHubungan = rekod_baru;
					}

					if (!(rekod_asal.toUpperCase().trim()).equals(rekod_baru.toUpperCase().trim())
							// COLUMN2 KEY KENA REKOD JUGA WALAPUN TAK DA CHANGES
							|| (key.equals("ID_PERMOHONANSIMATI") || key.equals("ID_OBPERMOHONAN")
									|| key.equals("ID_OB") || key.equals("ID_SIMATI") || key.equals("ID_PEMOHON"))
							|| key.equals("ID_HTAPERMOHONAN") || key.equals("ID_HTA") || key.equals("ID_HAPERMOHONAN")
							|| key.equals("ID_HA") || key.equals("JENIS_HTA") || key.equals("ID_PEGUAM")
							|| (AKTIVITI.equals("DELETE") && !rekod_asal.equals(""))) {
						if (key.contains("ID_") && (rekod_asal.equals("0") && rekod_baru.equals(""))
								|| (rekod_asal.equals("") && rekod_baru.equals("0"))) {
							rekodChanges = false;
						} else {
							rekodChanges = true;
						}
					}
				} else {
					// untuk new data for HTAPERMOHONAN & HAPERMOHONAN KENA CREATE BALIK ID FK :
					// ID_HTA & ID_HA

					if (key.equals("ID_HTA") || key.equals("ID_HA")) {
						long ID_FK = 0;
						if (key.equals("ID_HTA") && rekod_baru.equals("")) {
							ID_FK = DB.getNextID(db, "TBLPPKHTA_SEQ");

						} else if (key.equals("ID_HA") && rekod_baru.equals("")) {
							ID_FK = DB.getNextID(db, "TBLPPKHA_SEQ");

						} else {
							ID_FK = Long.parseLong(getParam(skrinName + key));
						}
						rekod_baru = ID_FK + "";
					}

					rekodChanges = true;
				}

				/*
				 * if(key.equals("ID_PEMOHON") && tableName.equals("TBLPPKOBPERMOHONAN")) {
				 * myLogger.info("111:: REKOD ASAL CHECK :: "+key+" rekod_asal : "
				 * +rekod_asal+" keterangan_asal : "+keterangan_asal); }
				 */

				if (rekodChanges == true) {
					// myLogger.info("ASAL TRIM : " + rekod_asal.trim()+ " BARU TRIM :
					// "+rekod_baru.trim());
					if (!key.equals("EMEL")) {
						if (rekod_asal != null) {
							rekod_asal = rekod_asal.toUpperCase();
						} else {
							rekod_asal = "";
						}

						// SPECIAL CONDITION UNTUK HUBUNGAN
						if (tableName.equals("TBLPPKOBPERMOHONAN") && key.equals("ID_PARENTOB")) {
							if ((!rekod_asal.equals("") && rekod_baru.equals(""))
									|| (rekod_asal.equals("") && !rekod_baru.equals("")) || (!rekod_asal.equals("")
											&& !rekod_baru.equals("") && !rekod_asal.equals(rekod_baru))) {
								setHubungan = true;
								id_obParent = rekod_baru;
							}

							// myLogger.info("*** ADA CHANGES :::: "+key+" :::: ASAL : "+rekod_asal+" ::::
							// BARU : "+rekod_baru);
						}
						// myLogger.info("*** ADA CHANGES :::: "+key+" :::: ASAL : "+rekod_asal+" ::::
						// BARU : "+rekod_baru);
						// ni untuk kes insert, kena get juga value dari seq
						// myLogger.info(" :::::::::: FIELD_PK : "+FIELD_PK+" :::::::::::: keys :
						// "+key+" :::::::::::::: VALUE_PK : "+VALUE_PK);
						if (key.equals(FIELD_PK) && rekod_baru.equals("")) {
							rekod_baru = VALUE_PK;
						} else if (tableName.equals("TBLPPKOBPERMOHONAN") && key.equals("ID_OB")) {
							if (rekod_baru.equals("")) {
								rekod_baru = DB.getNextID(db, "TBLPPKOB_SEQ") + "";
							}
							/*
							 * else { rekod_baru = getParam(skrinName+key); }
							 */
							id_obHubungan = rekod_baru;
						} else if (tableName.equals("TBLPPKOBPERMOHONAN") && key.equals("ID_SAUDARA")) {
							id_saudaraHubungan = rekod_baru;
						}

						rekod_baru = rekod_baru.toUpperCase();
						// selain dari emel suma tukar uppercase
					} else {
						if (rekod_asal == null) {
							rekod_asal = "";
						}
					}

					// keterangan asal
					// simpan keterangan plak untuk case data rujukan

					if (rekod_asal != null) {
						if (key.equals("ID_PARENTOB")) {
							myLogger.info("rekod_asal >>>>>>>>>>>>>>>>>>>>> " + rekod_asal);
							String namaParent = "";
							if (!rekod_asal.equals("")) {
								List listParentSimati = null;
								listParentSimati = modelBI.listParentSimati(session, ID_PERMOHONANSIMATI,
										ID_PERBICARAAN, skrinName, rekod_asal, db);
								if (listParentSimati.size() > 0) {
									Map setupOB = (Map) listParentSimati.get(0);
									if (setupOB != null) {
										namaParent = (String) setupOB.get("NAMA_OB");
									}
								}
							}
							keterangan_asal = namaParent.toUpperCase();
						} else {

							keterangan_asal = modelBI.getValueKeterangan(session, tableName, key, rekod_asal, db)
									.toUpperCase();

						}
					} else {
						/*
						 * if(key.equals("ID_PEMOHON") && tableName.equals("TBLPPKOBPERMOHONAN")) {
						 * if(rekod_asal.equals("")) { keterangan_asal = "TIDAK"; } } else {
						 */
						if (key.equals("ID_PEMOHON") && tableName.equals("TBLPPKOBPERMOHONAN")) {
							keterangan_asal = "TIDAK";
						} else {
							keterangan_asal = "";
						}
						// }
					}
					/*
					 * if(key.equals("ID_PEMOHON") && tableName.equals("TBLPPKOBPERMOHONAN")) {
					 * myLogger.info("222:: REKOD ASAL CHECK :: "+key+" rekod_asal : "
					 * +rekod_asal+" keterangan_asal : "+keterangan_asal); }
					 */
					// keterangan baru
					if (key.equals("ID_PARENTOB")) {
						myLogger.info("rekod_baru >>>>>>>>>>>>>>>>>>>>> " + rekod_asal);
						String namaParent = "";
						if (!rekod_baru.equals("")) {
							List listParentSimati = null;
							listParentSimati = modelBI.listParentSimati(session, ID_PERMOHONANSIMATI, ID_PERBICARAAN,
									skrinName, rekod_baru, db);
							if (listParentSimati.size() > 0) {
								Map setupOB = (Map) listParentSimati.get(0);
								if (setupOB != null) {
									namaParent = (String) setupOB.get("NAMA_OB");
								}
							}
						}
						keterangan_baru = namaParent.toUpperCase();
					} else {
						keterangan_baru = modelBI.getValueKeterangan(session, tableName, key, rekod_baru, db)
								.toUpperCase();
					}
					// myLogger.info("CHANGES -- FIELDNAME : " + key + "; VALUE ASAL : " +
					// rekod_asal +"; KETERANGAN ASAL : "+keterangan_asal+ "; VALUE BARU : " +
					// rekod_baru +"; KETERANGAN BARU : "+keterangan_baru);
					// saveHistorySub(session,id_history_utama,rekod_asal,rekod_baru,key);
					String label = "";
					String _turutan_str = "";
					if (!skrin_asal.equals("") && !skrin_asal.equals(skrinName)) {
						String key_twist = "";
						if (key.equals("NAMA_OB")) {
							key_twist = "NAMA_PEMOHON";
						} else if (key.equals("NAMA_PEMOHON")) {
							key_twist = "NAMA_OB";
						} else {
							key_twist = key;
						}

						label = getParam(skrin_asal + key_twist + "_label");
						_turutan_str = getParam(skrin_asal + key_twist + "_turutan");
					} else {
						// myLogger.info(skrinName+key+"_label"+":::::::::: LABEL ::::::::: skrinName :
						// "+skrinName+" key : "+key+" value : "+getParam(skrinName+key+"_label"));
						label = getParam(skrinName + key + "_label");
						_turutan_str = getParam(skrinName + key + "_turutan");
					}

					double turutan = 0;
					if (!_turutan_str.equals("")) {
						turutan = Double.parseDouble(_turutan_str);
					}
					// label = label.replace("ID_", "");
					// label = label.replace("_", " ");
					modelBI.saveHistorySub(session, id_history_utama, label, key, rekod_asal, keterangan_asal,
							rekod_baru, keterangan_baru, turutan, skrinName, db);
				}
			}
		}

		// myLogger.info("CHECK >>>>>>>>>>>>>>>>> setHubungan ::::::::::::::::::::::
		// "+setHubungan);
		// UNTUK SETING HUBUNGAN OB
		if (setHubungan == true) {
			List listHubungan = modelBI.listHubungan(session, ID_PERMOHONANSIMATI, ID_PERBICARAAN, skrinName,
					id_obHubungan, db);
			// myLogger.info("CHECK >>>>>>>>>>>>>>>>> listHubungan.size() :
			// "+listHubungan.size());
			if (listHubungan.size() > 0) {

				for (int i = 0; i < listHubungan.size(); i++) {
					// myLogger.info("CHECK >>>>>>>>>>>>>>>>> ADA HUBUNGAN ");
					Map setupHubungan = (Map) listHubungan.get(i);
					if (setupHubungan != null) {
						// myLogger.info("CHECK >>>>>>>>>>>>>>>>> setupHubungan : "+setupHubungan);
						String ID_HUBUNGANOBPERMOHONAN_ASAL_HBG = (String) setupHubungan.get("ID_HUBUNGANOBPERMOHONAN");
						String ID_PERMOHONANSIMATI_ASAL_HBG = (String) setupHubungan.get("ID_PERMOHONANSIMATI");
						String ID_HUBUNGANOB_ASAL_HBG = (String) setupHubungan.get("ID_HUBUNGANOB");
						String ID_OB_ASAL_HBG = (String) setupHubungan.get("ID_OB");
						String ID_PARENTOB_ASAL_HBG = (String) setupHubungan.get("ID_PARENTOB");
						String ID_SAUDARA_ASAL_HBG = (String) setupHubungan.get("ID_SAUDARA");
						modelBI.deleteHISTORY(command, "", "TBLPPKHUBUNGANOBPERMOHONAN", "ID_HUBUNGANOBPERMOHONAN",
								ID_HUBUNGANOBPERMOHONAN_ASAL_HBG, ID_PERBICARAAN, db);

						String hubungan_Aktiviti = "UPDATE";
						if (id_obParent.equals("")) {
							hubungan_Aktiviti = "DELETE";
						}

						String id_his_delete = modelBI.saveHistoryUtama(session, "", skrinName,
								"TBLPPKHUBUNGANOBPERMOHONAN", "ID_HUBUNGANOBPERMOHONAN",
								ID_HUBUNGANOBPERMOHONAN_ASAL_HBG, hubungan_Aktiviti, ID_PERBICARAAN,
								ID_PERMOHONANSIMATI, db);
						// myLogger.info("CHECK >>>>>>>>>>>>>>>>> id_his_delete : "+id_his_delete);
						modelBI.saveHistorySub(session, id_his_delete, "ID_HUBUNGANOBPERMOHONAN",
								"ID_HUBUNGANOBPERMOHONAN", ID_HUBUNGANOBPERMOHONAN_ASAL_HBG, "",
								ID_HUBUNGANOBPERMOHONAN_ASAL_HBG, "", 1, skrinName, db);
						modelBI.saveHistorySub(session, id_his_delete, "ID_PERMOHONANSIMATI", "ID_PERMOHONANSIMATI",
								ID_PERMOHONANSIMATI_ASAL_HBG, "", ID_PERMOHONANSIMATI_ASAL_HBG, "", 2, skrinName, db);
						modelBI.saveHistorySub(session, id_his_delete, "ID_HUBUNGANOB", "ID_HUBUNGANOB",
								ID_HUBUNGANOB_ASAL_HBG, "", ID_HUBUNGANOB_ASAL_HBG, "", 3, skrinName, db);
						modelBI.saveHistorySub(session, id_his_delete, "ID_OB", "ID_OB", ID_OB_ASAL_HBG, "",
								id_obHubungan, "", 4, skrinName, db);
						modelBI.saveHistorySub(session, id_his_delete, "ID_PARENTOB", "ID_PARENTOB",
								ID_PARENTOB_ASAL_HBG, "", id_obParent, "", 5, skrinName, db);
						modelBI.saveHistorySub(session, id_his_delete, "ID_SAUDARA", "ID_SAUDARA", ID_SAUDARA_ASAL_HBG,
								"", id_saudaraHubungan, "", 6, skrinName, db);
					}
				}
			} else {
				// RESET DULU
				modelBI.deleteHISTORYHubungan(ID_PERMOHONANSIMATI, id_obHubungan, id_obParent, ID_PERBICARAAN, db);
				// baru insert
				long ID_HBP_PK = DB.getNextID(db, "TBLPPKHUBUNGANOBPERMOHONAN_SEQ");
				long ID_HB_PK = DB.getNextID(db, "TBLPPKHUBUNGANOB_SEQ");
				String id_his_add = modelBI.saveHistoryUtama(session, "", skrinName, "TBLPPKHUBUNGANOBPERMOHONAN",
						"ID_HUBUNGANOBPERMOHONAN", ID_HBP_PK + "", "INSERT", ID_PERBICARAAN, ID_PERMOHONANSIMATI, db);
				modelBI.saveHistorySub(session, id_his_add, "ID_HUBUNGANOBPERMOHONAN", "ID_HUBUNGANOBPERMOHONAN", "",
						"", ID_HBP_PK + "", "", 1, skrinName, db);
				modelBI.saveHistorySub(session, id_his_add, "ID_PERMOHONANSIMATI", "ID_PERMOHONANSIMATI", "", "",
						ID_PERMOHONANSIMATI, "", 2, skrinName, db);
				modelBI.saveHistorySub(session, id_his_add, "ID_HUBUNGANOB", "ID_HUBUNGANOB", "", "", ID_HB_PK + "", "",
						3, skrinName, db);
				modelBI.saveHistorySub(session, id_his_add, "ID_OB", "ID_OB", "", "", id_obHubungan, "", 4, skrinName,
						db);
				modelBI.saveHistorySub(session, id_his_add, "ID_PARENTOB", "ID_PARENTOB", "", "", id_obParent, "", 5,
						skrinName, db);
				modelBI.saveHistorySub(session, id_his_add, "ID_SAUDARA", "ID_SAUDARA", "", "", id_saudaraHubungan, "",
						6, skrinName, db);
			}

		}

		return id_history_utama;
	}

	public void setValueFlagJenisKeputusan(String checkedSelesai, String checkedTangguh, String checkedBatal) {

		this.checkedSelesai = checkedSelesai;
		this.checkedTangguh = checkedTangguh;
		this.checkedBatal = checkedBatal;
	}
}

//nota deployment
/*
 * NotaPerbicaraan.jrxml NotaPerbicaraan.jasper NotaPerbicaraan17.jrxml
 * NotaPerbicaraan17.jasper NotaPerbicaraan_OBBicaraOnline.jrxml
 * NotaPerbicaraan_OBBicaraOnline.jasper
 *
 *
 * BorangJ.jrxml BorangJ.jasper BorangL.jrxml BorangL.jasper BorangM.jrxml
 * BorangM.jasper BorangN.jrxml BorangN.jasper BorangIWasiatPerbicaraan.jrxml
 * BorangIWasiatPerbicaraan.jasper NotaPerbicaraan_THBicaraOnline.jrxml
 * NotaPerbicaraan_THBicaraOnline.jasper
 *
 * ekptg.view.ppk.FrmDashboard.class ekptg.view.ppk.BicaraInteraktif.class
 * ekptg.view.ppk.BicaraInteraktifPrint.class
 *
 *
 * ekptg.engine.CacheManager.class ekptg.engine.CachedObject.class
 *
 * ekptg.model.ppk.BicaraInteraktifData.class
 *
 * app/ppk/dashboard_showCountTukarPegawai.jsp
 * app/ppk/dashboard_showCountBicaraOnline.jsp app/ppk/dashboard.jsp
 * app/ppk/dashboard_showBorangB_stats.jsp app/ppk/frmPopupCetakLaporan.jsp
 * app/ppk/headerppk.jsp app/ppk/headerppk_script.jsp
 *
 *
 * //jquery baru library/js/jquery-1.7.2.min.js bootstrap-wysihtml5-master
 *
 * JavaSource_Edited/lebah/portal/DesktopController.class
 */

//790329015244 IC LOGIN (KELANTAN UTARA)
//770410086064 IC LOGIN (KELANTAN BARAT)
//581119085004 PPK HQ PENGARAH/KPP
//840819075338 PPK SELANGOR KPP

//USERLOGIN

//KELANTAN
////PP
//////771122146100 - KHAIRIAH
//////790329015244 - MASZURA
////KPP
//////590110035582 - SAADAH

//KEDAH
////PP
//////800518075046 - DAYANG
//////821109025948 - NOR HANAN
////KPP
//////860903295939 - SHAFIQ

//HQ
////KPP
//////591106046099 - SEH
////P
//////581119085004 - HASIAH
