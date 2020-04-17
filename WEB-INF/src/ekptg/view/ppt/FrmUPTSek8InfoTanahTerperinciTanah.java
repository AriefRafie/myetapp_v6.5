package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciBangunanData;
import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciTanahData;
import ekptg.model.ppt.PPTHeader;

/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmUPTSek8InfoTanahTerperinciTanah extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger
			.getLogger(FrmUPTSek8InfoTanahTerperinciTanah.class);

	// model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8InfoTanahTerperinciTanahData model = new FrmUPTSek8InfoTanahTerperinciTanahData();
	FrmUPTSek8InfoTanahTerperinciBangunanData model_bg = new FrmUPTSek8InfoTanahTerperinciBangunanData();
	PPTHeader header = new PPTHeader();

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = request.getSession();

		// command for pagings
		String action = getParam("action");

		// get user login detail
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		String portal_role = (String) session.getAttribute("_portal_role");

		userData(user_id);
		String userIdNeg = userData(user_id);

		// helper
		context.put("Util", new lebah.util.Util());
		context.put("Utils", new ekptg.helpers.Utils());

		String vm = "";
		String noLOT = "";
		String idpegawai = "";

		Vector listPageDepan = new Vector();
		Vector listMaklumatTanah = new Vector();
		Vector dataMaklumatTanahTerperinci = new Vector();
		Vector checkSizeTbltanah = new Vector();
		Vector dataSuburusanHakmilik = new Vector();
		Vector getIdSuburusanstatusfail = new Vector();

		dataSuburusanHakmilik.clear();
		checkSizeTbltanah.clear();
		dataMaklumatTanahTerperinci.clear();
		listMaklumatTanah.clear();
		listPageDepan.clear();
		getIdSuburusanstatusfail.clear();

		// screen jsp
		String listdepan = "app/ppt/frmUPTSek8InfoTanahTerperinciTanahSenarai.jsp";
		String listHMscreen = "app/ppt/frmUPTSek8InfoTanahTerperinciTanahListHM.jsp";
		String screentanah = "app/ppt/frmUPTSek8InfoTanahTerperinciTanah.jsp";

		// default list
		// listPageDepan = model.getListPermohonan(userIdNeg);

		// prevent duplicate when refresh page
		String doPost = (String) session.getAttribute("doPost");
		myLogger.info("doPost !!!!!!!!!!!!!!!!!!!!!!!!!!!! :" + doPost);

		// anchor
		String ScreenLocation = getParam("ScreenLocation");
		String CursorPoint = getParam("CursorPoint");
		context.put("ScreenLocation", ScreenLocation);
		context.put("CursorPoint", CursorPoint);

		// tabbed
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}

		// paging
		/*
		 * String flagPaging = getParam("paging"); if (flagPaging != "") {
		 * context.put("paging", getParam("paging")); } else {
		 * context.put("paging", "11"); }
		 */
		context.put("paging", "11");

		/*
		 * Db db = null; try { db = new Db();
		 * 
		 * if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik", db) ==
		 * 0) { // reg class
		 * insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik",
		 * "Skrin Capaian Hakmilik", "EKPTG - PPT", db); }
		 * 
		 * if (checkRegPopup("ekptg.view.ppt.SkrinPopupUploadDokumen", db) == 0)
		 * { // reg class
		 * insertPopupReg("ekptg.view.ppt.SkrinPopupUploadDokumen",
		 * "Skrin Senarai Dokumen PPT", "EKPTG - PPT", db); }
		 * 
		 * 
		 * checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",db);
		 * checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",db);
		 * checkFieldWujud("TBLPPTDOKUMEN","ID_NOTISAWAM","NUMBER",db);
		 * checkFieldWujud("TBLPPTDOKUMEN","ID_BUKTIPENYAMPAIAN","NUMBER",db);
		 * checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGH","NUMBER",db);
		 * checkFieldWujud("TBLPPTDOKUMEN","ID_BORANGK","NUMBER",db);
		 * 
		 * alterFieldSize(db);
		 * 
		 * } finally { if (db != null) db.close(); }
		 */

		// header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
		String id_projekNegeri = "";
		String flag_subjaket = "";
		String idpermohonan = getParam("id_permohonan");

		if (!idpermohonan.equals("")) {
			header.setDataHeader(idpermohonan);
			Vector dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if (dataHeader.size() != 0) {
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = (String) dh.get("id_status");
				flagSegera = dh.get("flag_segera").toString();
				id_fail = dh.get("id_fail").toString();
				id_projekNegeri = dh.get("id_projekNegeri").toString();
				flag_subjaket = dh.get("flag_subjaket").toString();

				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}
		}

		context.put("flag_subjaket", flag_subjaket);
		context.put("flagSegera", flagSegera);

		// header hakmilik
		String idHakmilik = getParam("id_hakmilik");
		// get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		String id_suburusanstatus = "";
		Vector dataMaklumatTanah = null;
		if (!idHakmilik.equals("")) {
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);

			modelUPT.setDataSuburusanHakmilik(idHakmilik);
			dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
			if (dataSuburusanHakmilik.size() != 0) {
				Hashtable dsh = (Hashtable) dataSuburusanHakmilik.get(0);
				id_suburusanstatushakmilik = (String) dsh
						.get("id_suburusanstatushakmilik");
				id_suburusanstatus = (String) dsh.get("id_suburusanstatus");
			}
		}

		// get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		if (!idpermohonan.equals("")) {
			modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
			getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
			if (getIdSuburusanstatusfail.size() != 0) {
				Hashtable idsb = (Hashtable) getIdSuburusanstatusfail.get(0);
				id_suburusanstatusfailppt = (String) idsb
						.get("id_suburusanstatusfailppt");
			}
		}

		// default
		context.put("mode", "");
		context.put("isEdit", "");
		context.put("onchange", "no");

		String submit = getParam("command");
		myLogger.info("submit : " + submit);

		if ("getSenaraiBanggunan".equals(submit)) {
			// data & list maklumat tanah
			context.put("Utils", new ekptg.helpers.Utils());
			Vector listMaklumatBangunan = null;
			model_bg.setListBangunan(idHakmilik);
			listMaklumatBangunan = model_bg.getListBangunan();
			context.put("listMaklumatBangunan", listMaklumatBangunan);
			context.put("saiz_bangunan", listMaklumatBangunan.size());
			context.put("check_edit", getParam("check_edit"));
			return "app/ppt/div_getSenaraiBanggunan.jsp";

		} else if ("simpanNilaianJpph".equals(submit)) {
			context.put("Utils", new ekptg.helpers.Utils());
			Vector listMaklumatBangunan = null;
			model_bg.setListBangunan(idHakmilik);
			listMaklumatBangunan = model_bg.getListBangunan();
			if (listMaklumatBangunan.size() > 0) {
				for (int i = 1; i < (listMaklumatBangunan.size() + 1); i++) {
					String id_bangunan_jpph = "id_bangunan_jpph" + i;
					String nilai_jpph_bg = "nilai_jpph_bg" + i;
					updateNilaianJPPHBG(session, user_id,
							getParam(id_bangunan_jpph), getParam(nilai_jpph_bg));
				}
			}
			model_bg.setListBangunan(idHakmilik);
			listMaklumatBangunan = model_bg.getListBangunan();
			context.put("listMaklumatBangunan", listMaklumatBangunan);
			context.put("saiz_bangunan", listMaklumatBangunan.size());
			context.put("check_edit", getParam("check_edit"));
			return "app/ppt/div_getSenaraiBanggunan.jsp";

		}

		else if ("viewListHM".equals(submit)) {

			noLOT = getParam("carianNoLot");
			context.put("carianNoLot", noLOT.trim());

			// data & list maklumat tanah
			/*
			 * modelUPT.setListMaklumatTanah(idpermohonan,noLOT,idpegawai);
			 * listMaklumatTanah = modelUPT.getListMaklumatTanah();
			 * context.put("listMaklumatTanah", listMaklumatTanah);
			 * context.put("saiz_listTanah", listMaklumatTanah.size());
			 */
			context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(
					idpermohonan, noLOT, idpegawai));

			// screen
			vm = listHMscreen;

		}// close

		else if ("maklumatTanah".equals(submit)) {

			// reset values
			resetValue();

			// validation by size
			model.setCheckTbltanah(idHakmilik);
			checkSizeTbltanah = model.getCheckTbltanah();

			// get current date
			String sysdate = "";
			sysdate = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
			context.put("sysdate", sysdate);

			// data maklumat tanah
			String id_pegawai = "";
			/*
			 * modelUPT.setMaklumatTanah(idHakmilik); dataMaklumatTanah =
			 * modelUPT.getMaklumatTanah();
			 */
			if (dataMaklumatTanah.size() != 0) {
				Hashtable tt = (Hashtable) dataMaklumatTanah.get(0);
				id_pegawai = (String) tt.get("id_pegawai");
			}

			String mode = "";
			if ((id_pegawai == "")
					|| (id_pegawai.equals(user_id))
					|| (portal_role.equals("(PPT)PengarahUnit")
							|| portal_role.equals("(PPT)PenolongPengarahUnit") || portal_role
								.equals("adminppt"))) {
				mode = "enabled";
			} else {
				// mode = "disabled";
				mode = "enabled";
			}

			// dropdown
			context.put("selectPegawai", HTML.SelectPegawaiPPTByNegeri(
					id_projekNegeri, "socPegawai", null, null,
					"style=width:325px"));

			// data tblppttanah dan tblppthakmilik
			// DataTanahTerperinci(session, idHakmilik, mode, id_projekNegeri);

			/*
			 * model.setMaklumatTanahTerperinci(idHakmilik);
			 * dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
			 */
			String id_tanah = "";
			if (dataMaklumatTanahTerperinci.size() != 0) {
				Hashtable tt = (Hashtable) dataMaklumatTanahTerperinci.get(0);
				id_tanah = (String) tt.get("id_tanah");
			}

			// list dokumen
			listDokumen(id_tanah);

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			// NEW
			if (checkSizeTbltanah.size() == 0) {

				// get previous values
				// getPreviousValue(idpermohonan, sysdate, id_projekNegeri);

				// form validation
				context.put("mode", "new");
				DataTanahTerperinci(session, idHakmilik, mode, id_projekNegeri);

				if ("onchangeUnitAsal".equals(submit2)) {
					// onchange validation
					context.put("onchange", "yes");

					// convert nilai lain
					changeUnitAsal();

					// get and set data
					getAndSetData("1");

				}// close onchangeUnitAsal

				else if ("onchangeUnitAmbil".equals(submit2)) {

					// onchange validation
					context.put("onchange", "yes");

					// convert nilai lain
					changeUnitAmbil();

					// get and set data
					getAndSetData("2");

				}// close onchangeUnitAsal

				else if ("simpanTanahTerperinci".equals(submit2)) {

					if (doPost.equals("true")) {
						// simpan data
						simpanTanahTerperinci(session, id_status);

						if (modelUPT.cekStatusFailDahWujud(idpermohonan, "46",
								"52") == false) {
							modelUPT.updateStatus(idpermohonan, user_id, "46");
							updateSuburusanStatusFailPPT(session, idpermohonan,
									id_fail, id_suburusanstatusfailppt);
						}

						// SELAIN DARI STATUS PERMOHONAN LAPORAN TANAH
						// TERPERINCI
						if (!id_suburusanstatus.equals("1475")) {// id_status 46
							updateSuburusanHakmilik(session, idpermohonan,
									id_fail, idHakmilik,
									id_suburusanstatushakmilik);
						}

						/*
						 * if (!id_suburusanstatus.equals("1475")) {
						 * updateSuburusanHakmilik(session, idpermohonan,
						 * id_fail, idHakmilik, id_suburusanstatushakmilik); }
						 */
					}

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "no");

					// data tblppttanah dan tblppthakmilik
					DataTanahTerperinci(session, idHakmilik, "disabled",
							id_projekNegeri);

					String id_pelapor = "";
					/*
					 * model.setMaklumatTanahTerperinci(idHakmilik);
					 * dataMaklumatTanahTerperinci = model
					 * .getMaklumatTanahTerperinci();
					 */
					if (dataMaklumatTanahTerperinci.size() != 0) {
						Hashtable tt = (Hashtable) dataMaklumatTanahTerperinci
								.get(0);
						id_tanah = (String) tt.get("id_tanah");
						id_pelapor = (String) tt.get("id_pelapor");
					}

					// dropdown
					// context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(id_projekNegeri,"socPegawai",Utils.parseLong(id_pelapor),null,"style=width:325px"));

					// list dokumen
					listDokumen(id_tanah);

				}// close simpanTanahTerperinci

				// VIEW
			} else {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// data tblppttanah dan tblppthakmilik
				DataTanahTerperinci(session, idHakmilik, "disabled",
						id_projekNegeri);

				if ("kemaskiniTanahTerperinci".equals(submit2)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "yes");

					// data tblppttanah dan tblppthakmilik
					DataTanahTerperinci(session, idHakmilik, "enabled",
							id_projekNegeri);

					String submit3 = getParam("command3");
					myLogger.info("submit[3]: " + submit3);

					if ("onchangeUnitAsalUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						// convert nilai lain
						changeUnitAsal();

						// get and set data
						getAndSetData("1");

					}// close onchangeUnitAsalUpdate

					else if ("onchangeUnitAmbilUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						// convert nilai lain
						changeUnitAmbil();

						// get and set data
						getAndSetData("2");

					}// close onchangeUnitAmbilUpdate

					else if ("updateTanahTerperinci".equals(submit3)) {

						updateTanahTerperinci(session);

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						// data tblppttanah dan tblppthakmilik
						DataTanahTerperinci(session, idHakmilik, "disabled",
								id_projekNegeri);

						// list dokumen
						listDokumen(id_tanah);

					}// close updateTanahTerperinci

				}// close kemaskiniTanahTerperinci

			}// check size

			// screen
			vm = screentanah;

		}// close maklumatTanah

		else if ("hapusDokumen".equals(submit)) {

			if (doPost.equals("true")) {
				// hapus data
				hapusDokumen();
			}

			// data maklumat tanah
			String id_pegawai = "";
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			if (dataMaklumatTanah.size() != 0) {
				Hashtable tt = (Hashtable) dataMaklumatTanah.get(0);
				id_pegawai = (String) tt.get("id_pegawai");
			}

			String mode = "";
			if ((id_pegawai == "")
					|| (id_pegawai.equals(user_id))
					|| (portal_role.equals("(PPT)PengarahUnit")
							|| portal_role.equals("(PPT)PenolongPengarahUnit") || portal_role
								.equals("adminppt"))) {
				mode = "enabled";
			} else {
				// mode = "disabled";
				mode = "enabled";
			}

			// data tblppttanah dan tblppthakmilik
			DataTanahTerperinci(session, idHakmilik, mode, id_projekNegeri);

			model.setMaklumatTanahTerperinci(idHakmilik);
			dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
			String id_tanah = "";
			if (dataMaklumatTanahTerperinci.size() != 0) {
				Hashtable tt = (Hashtable) dataMaklumatTanahTerperinci.get(0);
				id_tanah = (String) tt.get("id_tanah");
			}

			// list dokumen
			listDokumen(id_tanah);

			// screen
			vm = screentanah;

		}// close hapusDokumen

		else if ("cari".equals(submit)) {

			// carian
			ListCarian(session, userIdNeg);
			listPageDepan = model.getListCarian();

			// screen
			vm = listdepan;

		}// close cari

		else {

			listPageDepan = model.getListPermohonan(userIdNeg);

			context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");

			// screen
			vm = listdepan;

		}// close else

		// list permohonan
		context.put("listPermohonan", listPageDepan);
		context.put("list_size", listPageDepan.size());

		// id
		context.put("id_permohonan", idpermohonan);
		context.put("id_hakmilik", idHakmilik);
		context.put("id_status", id_status);
		context.put("id_fail", id_fail);

		setupPage(session, action, listPageDepan);
		this.context.put("selectedTab", selectedTab);
		return vm;

	}// close public template

	// --METHOD--//

	private void updateNilaianJPPHBG(HttpSession session, String user_id,
			String id_bangunan, String nilai) throws Exception {
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql1 = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_BANGUNAN", id_bangunan);
			r.add("NILAIAN_JPPH", nilai);
			// r.add("ID_KEMASKINI",user_id);
			// r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("TBLPPTBANGUNAN");
			myLogger.info("UPDATE TBLPPKBANGGUNAN :" + sql1);
			stmt.executeUpdate(sql1);
			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,
			String id_permohonan, String id_fail, String id_hakmilik,
			String id_suburusanstatushakmilik) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelUPT.updateSuburusanHakmilik(h, id_suburusanstatushakmilik, "1475");

	}// close addSuburusanHakmilik

	@SuppressWarnings({ "unchecked", "static-access" })
	private String userData(String id_user) throws Exception {

		Vector listUserid = new Vector();
		listUserid.clear();

		modelUPT.setGetUserId(id_user);
		listUserid = modelUPT.getUserIds();
		String userIdNeg = "";
		if (listUserid.size() != 0) {
			Hashtable t = (Hashtable) listUserid.get(0);
			userIdNeg = t.get("idNegeri").toString();
		}

		return userIdNeg;

	}// close userData

	@SuppressWarnings("unchecked")
	private void getPreviousValue(String idpermohonan, String sysdate,
			String id_projekNegeri) throws Exception {

		Vector dataMaklumatTanahTerperinci = new Vector();
		dataMaklumatTanahTerperinci.clear();

		// String tarikh_ulasan = sysdate;
		String id_pelapor = "";
		String ulasan_pegawai = "";
		String unit_harga_so = "";
		String unit_harga = "";
		double harga_seunit_so = 0;
		double bayar_penjejasan = 0;
		double bayar_pecahpisah = 0;
		double bayar_naik_nilaiso = 0;
		double harga_seunit_jpph = 0;
		double amaun_penjejasan_jpph = 0;
		double amaun_pecahpisah_jpph = 0;
		double naik_nilai_jpph = 0;
		double struktur_bangunan_so = 0;
		double struktur_bangunan = 0;
		String pecah_pisah = "";
		String pendahuluan = "";
		String status_tanah = "";
		String tarikh_mula_lawat = "";
		String tarikh_akhir_lawat = "";
		String lokasi_tanah = "";
		String jalan_utama = "";
		String jalan_masuk = "";
		String nama_tempat = "";
		String jarak_bandar = "";
		String perumahan = "";
		String industri = "";
		String nama_pbt = "";
		String flag_pbt = "";
		String flag_rezab_melayu = "";
		String flag_diusaha = "";
		String flag_lembah = "";
		String flag_lurah = "";
		String flag_paya = "";
		String flag_rendah = "";
		String flag_rata = "";
		String flag_landai = "";
		String flag_bukit = "";
		String flag_semak = "";
		String flag_belukar = "";
		String flag_hutan = "";
		String flag_terbiar = "";
		String flag_lapang = "";
		String rupabumi = "";
		String keadaan_tanah = "";
		String halangan = "";
		String tanaman = "";
		String kemudahan_awam = "";
		String sempadan_utara = "";
		String sempadan_selatan = "";
		String sempadan_timur = "";
		String sempadan_barat = "";
		String flag_saliran = "";
		model.setPreviousMaklumatTanahTerperinci(idpermohonan);
		dataMaklumatTanahTerperinci = model
				.getPreviousMaklumatTanahTerperinci();
		if (dataMaklumatTanahTerperinci.size() != 0) {
			Hashtable tt = (Hashtable) dataMaklumatTanahTerperinci.get(0);
			pendahuluan = (String) tt.get("pendahuluan");
			status_tanah = (String) tt.get("status_tanah");
			tarikh_mula_lawat = (String) tt.get("tarikh_mula_lawat");
			tarikh_akhir_lawat = (String) tt.get("tarikh_akhir_lawat");
			lokasi_tanah = (String) tt.get("lokasi_tanah");
			jalan_utama = (String) tt.get("jalan_utama");
			jalan_masuk = (String) tt.get("jalan_masuk");
			nama_tempat = (String) tt.get("nama_tempat");
			jarak_bandar = (String) tt.get("jarak_bandar");
			perumahan = (String) tt.get("perumahan");
			industri = (String) tt.get("industri");
			nama_pbt = (String) tt.get("nama_pbt");
			flag_pbt = (String) tt.get("flag_pbt");
			flag_rezab_melayu = (String) tt.get("flag_rezab_melayu");
			flag_diusaha = (String) tt.get("flag_diusaha");
			flag_lembah = (String) tt.get("flag_lembah");
			flag_lurah = (String) tt.get("flag_lurah");
			flag_paya = (String) tt.get("flag_paya");
			flag_rendah = (String) tt.get("flag_rendah");
			flag_rata = (String) tt.get("flag_rata");
			flag_landai = (String) tt.get("flag_landai");
			flag_bukit = (String) tt.get("flag_bukit");
			flag_semak = (String) tt.get("flag_semak");
			flag_belukar = (String) tt.get("flag_belukar");
			flag_hutan = (String) tt.get("flag_hutan");
			flag_terbiar = (String) tt.get("flag_terbiar");
			flag_lapang = (String) tt.get("flag_lapang");
			rupabumi = (String) tt.get("rupabumi");
			keadaan_tanah = (String) tt.get("keadaan_tanah");
			halangan = (String) tt.get("halangan");
			tanaman = (String) tt.get("tanaman");
			kemudahan_awam = (String) tt.get("kemudahan_awam");
			sempadan_utara = (String) tt.get("sempadan_utara");
			sempadan_selatan = (String) tt.get("sempadan_selatan");
			sempadan_timur = (String) tt.get("sempadan_timur");
			sempadan_barat = (String) tt.get("sempadan_barat");
			flag_saliran = (String) tt.get("flag_saliran");
			unit_harga_so = (String) tt.get("unit_harga_so");
			harga_seunit_so = (Double) tt.get("harga_seunit_so");
			bayar_penjejasan = (Double) tt.get("bayar_penjejasan");
			bayar_pecahpisah = (Double) tt.get("bayar_pecahpisah");
			bayar_naik_nilaiso = (Double) tt.get("bayar_naik_nilaiso");
			unit_harga = (String) tt.get("unit_harga");
			harga_seunit_jpph = (Double) tt.get("harga_seunit_jpph");
			amaun_penjejasan_jpph = (Double) tt.get("amaun_penjejasan_jpph");
			amaun_pecahpisah_jpph = (Double) tt.get("amaun_pecahpisah_jpph");
			naik_nilai_jpph = (Double) tt.get("naik_nilai_jpph");
			pecah_pisah = (String) tt.get("pecah_pisah");
			// tarikh_ulasan = (String)tt.get("tarikh_ulasan");
			ulasan_pegawai = (String) tt.get("ulasan_pegawai");
			id_pelapor = (String) tt.get("id_pelapor");
			struktur_bangunan_so = (Double) tt.get("struktur_bangunan_so");
			struktur_bangunan = (Double) tt.get("struktur_bangunan");
		}

		// TAB 1
		context.put("txdTarikhLawatMula", tarikh_mula_lawat);
		context.put("txdTarikhLawatAkhir", tarikh_akhir_lawat);
		context.put("txtStatusTanah", status_tanah);
		context.put("txtPendahuluan", pendahuluan);
		context.put("txtLokasi", lokasi_tanah);
		context.put("txtJalanUtama", jalan_utama);
		context.put("txtJalanMasuk", jalan_masuk);
		context.put("txtNamaTempat", nama_tempat);
		context.put("txtJarak", jarak_bandar);
		context.put("txtPerumahan", perumahan);
		context.put("txtIndustri", industri);
		context.put("txtNamaPBT", nama_pbt);
		context.put("sorPBT", flag_pbt);
		context.put("sorRizab", flag_rezab_melayu);

		// TAB 2
		context.put("flagLembah", flag_lembah);
		context.put("flagLurah", flag_lurah);
		context.put("flagBerpaya", flag_paya);
		context.put("flagRendah", flag_rendah);
		context.put("flagRata", flag_rata);
		context.put("flagLandai", flag_landai);
		context.put("flagBukit", flag_bukit);
		context.put("flagUsaha", flag_diusaha);
		context.put("flagSemak", flag_semak);
		context.put("flagBelukar", flag_belukar);
		context.put("flagHutan", flag_hutan);
		context.put("flagTerbiar", flag_terbiar);
		context.put("flagLapang", flag_lapang);
		context.put("txtPerihalRupabumi", rupabumi);
		context.put("txtPerihalKeadaan", keadaan_tanah);
		context.put("txtHalangan", halangan);
		context.put("txtTanaman", tanaman);

		// TAB 3
		context.put("txtKemudahan", kemudahan_awam);
		context.put("txtSempadanUtara", sempadan_utara);
		context.put("txtSempadanSelatan", sempadan_selatan);
		context.put("txtSempadanTimur", sempadan_timur);
		context.put("txtSempadanBarat", sempadan_barat);
		context.put("sorSaliran", flag_saliran);

		// TAB 4
		context.put("socUnitHargaSO", unit_harga_so);
		context.put("txtHargaSeunitSO", harga_seunit_so);
		context.put("txtPenjejasanSO", bayar_penjejasan);
		context.put("txtPecahSO", bayar_pecahpisah);
		context.put("txtKenaikanSO", bayar_naik_nilaiso);
		context.put("socUnitHargaJP", unit_harga);
		context.put("txtHargaSeunitJP", harga_seunit_jpph);
		context.put("txtPenjejasanJP", amaun_penjejasan_jpph);
		context.put("txtPecahJP", amaun_pecahpisah_jpph);
		context.put("txtKenaikanJP", naik_nilai_jpph);
		context.put("txtJenisPisah", pecah_pisah);

		context.put("txtStrukturBangunanSO", struktur_bangunan_so);
		context.put("txtStrukturBangunan", struktur_bangunan);

		// TAB 5
		// context.put("txdTarikhUlasan",tarikh_ulasan);
		context.put("txtUlasanPegawai", ulasan_pegawai);
		// dropdown
		context.put("selectPegawai", HTML.SelectPegawaiPPTByNegeri(
				id_projekNegeri, "socPegawai", Utils.parseLong(id_pelapor),
				null, "style=width:325px"));

	}// close resetValue

	private void getAndSetData(String mode) throws Exception {

		if (mode.equals("1")) {
			context.put("txtLuasLotAmbil", getParam("txtLuasLotAmbil"));
			context.put("sorDropdownUnitAmbil",
					getParam("sorDropdownUnitAmbil"));
		} else {
			context.put("txtLuasLotAsal", getParam("txtLuasLotAsal"));
			context.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		}

		// dropdown
		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah",
				Utils.parseLong(getParam("socKategoriTanah")),
				"id=socKategoriTanah style=width:auto", null));
		context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
				"socJenisHakmilik",
				Utils.parseLong(getParam("socJenisHakmilik")),
				"id=socJenisHakmilik style=width:250"));

		context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		context.put("txtNoSyit", getParam("txtNoSyit"));

		context.put("txdTarikhLawatMula", getParam("txdTarikhLawatMula"));
		context.put("txdTarikhLawatAkhir", getParam("txdTarikhLawatAkhir"));
		context.put("txtStatusTanah", getParam("txtStatusTanah"));
		context.put("txtPendahuluan", getParam("txtPendahuluan"));
		context.put("txtLokasi", getParam("txtLokasi"));
		context.put("txtJalanUtama", getParam("txtJalanUtama"));
		context.put("txtJalanMasuk", getParam("txtJalanMasuk"));
		context.put("txtNamaTempat", getParam("txtNamaTempat"));
		context.put("txtJarak", getParam("txtJarak"));
		context.put("txtPerumahan", getParam("txtPerumahan"));
		context.put("txtIndustri", getParam("txtIndustri"));
		context.put("txtNamaPBT", getParam("txtNamaPBT"));
		context.put("sorPBT", getParam("sorPBT"));
		context.put("sorRizab", getParam("sorRizab"));

	}// close getAndSetData

	private void resetValue() throws Exception {

		context.put("txdTarikhLawatMula", "");
		context.put("txdTarikhLawatAkhir", "");
		context.put("txtStatusTanah", "");
		context.put("txtPendahuluan", "");
		context.put("txtLokasi", "");
		context.put("txtJalanUtama", "");
		context.put("txtJalanMasuk", "");
		context.put("txtNamaTempat", "");
		context.put("txtJarak", "");
		context.put("txtPerumahan", "");
		context.put("txtIndustri", "");
		context.put("txtNamaPBT", "");
		context.put("sorPBT", "");
		context.put("sorRizab", "");
		context.put("txtPerihalRupabumi", "");
		context.put("txtPerihalKeadaan", "");
		context.put("txtHalangan", "");
		context.put("txtTanaman", "");
		context.put("txtKemudahan", "");
		context.put("txtSempadanUtara", "");
		context.put("txtSempadanSelatan", "");
		context.put("txtSempadanTimur", "");
		context.put("txtSempadanBarat", "");
		context.put("sorSaliran", "");
		context.put("socUnitHargaSO", "");
		context.put("txtHargaSeunitSO", "");
		context.put("txtPenjejasanSO", "");
		context.put("txtPecahSO", "");
		context.put("txtKenaikanSO", "");
		context.put("socUnitHargaJP", "");
		context.put("txtHargaSeunitJP", "");
		context.put("txtPenjejasanJP", "");
		context.put("txtPecahJP", "");
		context.put("txtKenaikanJP", "");
		context.put("txtJenisPisah", "");
		context.put("txdTarikhUlasan", "");
		context.put("txtUlasanPegawai", "");
		context.put("txtStrukturBangunanSO", "");
		context.put("txtStrukturBangunan", "");

	}// close resetValue

	private void changeUnitAmbil() throws Exception {

		String unitConvert = getParam("sorDropdownUnitAmbil");
		context.put("sorDropdownUnitAmbil", unitConvert);

		String txtLuasLotAmbil = Utils
				.RemoveSymbol(getParam("txtLuasLotAmbil"));

		Double total = 0.0000;

		// hektar convert to meter persegi
		if (unitConvert.equals("1")) {
			total = Double.parseDouble(txtLuasLotAmbil) * 0.0001;
			// meter persegi convert to hektar
		} else {
			total = Double.parseDouble(txtLuasLotAmbil) * 10000;
		}

		// put data
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));

	}// close changeUnitAmbil

	private void changeUnitAsal() throws Exception {

		String unitConvert = getParam("sorDropdownUnitAsal");
		context.put("sorDropdownUnitAsal", unitConvert);

		String txtLuasLotAsal = Utils.RemoveSymbol(getParam("txtLuasLotAsal"));

		Double total = 0.0000;

		// hektar convert to meter persegi
		if (unitConvert.equals("1")) {
			total = Double.parseDouble(txtLuasLotAsal) * 0.0001;
			// meter persegi convert to hektar
		} else {
			total = Double.parseDouble(txtLuasLotAsal) * 10000;
		}

		// put data
		context.put("txtLuasLotAsal", Utils.formatLuasHM(total));

	}// close changeUnitAsal

	private void ListCarian(HttpSession session, String userIdNeg)
			throws Exception {

		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");

		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);

		FrmUPTSek8InfoTanahTerperinciTanahData.setListCarian(nofail, tarikh,
				status, userIdNeg);

	}// close listcarian

	@SuppressWarnings("unchecked")
	private void listDokumen(String id_tanah) throws Exception {
		/*
		 * Vector listDokumen = new Vector(); listDokumen.clear();
		 * 
		 * model.setListDokumen(id_tanah); listDokumen = model.getListDokumen();
		 * context.put("listDokumen", listDokumen);
		 * context.put("saiz_listDokumen", listDokumen.size());
		 */
	}// close listDokumen

	@SuppressWarnings("static-access")
	private void hapusDokumen() throws Exception {

		model.hapusDokumen(getParam("id_dokumen"));

	}// close hapusDokumen

	@SuppressWarnings("unchecked")
	private void simpanTanahTerperinci(HttpSession session, String status)
			throws Exception {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		Hashtable h = new Hashtable();

		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("id_permohonan", getParam("id_permohonan"));

		// update tblppthakmilik
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("socKategoriTanah", getParam("socKategoriTanah"));
		h.put("socJenisHakmilik", getParam("socJenisHakmilik"));

		h.put("txtLuasLotAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("txtLuasLotAmbil",
				Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));

		// insert tblppttanah
		h.put("txtJenisPisah", getParam("txtJenisPisah"));
		h.put("txtStatusTanah", getParam("txtStatusTanah"));
		h.put("txtPendahuluan", getParam("txtPendahuluan"));
		h.put("txdTarikhLawatMula", getParam("txdTarikhLawatMula"));
		h.put("txdTarikhLawatAkhir", getParam("txdTarikhLawatAkhir"));
		h.put("txtJalanUtama", getParam("txtJalanUtama"));
		h.put("txtJalanMasuk", getParam("txtJalanMasuk"));
		h.put("txtNamaTempat", getParam("txtNamaTempat"));
		h.put("txtPerumahan", getParam("txtPerumahan"));
		h.put("txtIndustri", getParam("txtIndustri"));
		// h.put("socUnitLuasLot",getParam("socUnitLuasLot"));
		// h.put("socUnitLuasAmbil",getParam("socUnitLuasAmbil"));
		// h.put("txtLuasLot",getParam("txtLuasLot"));
		// h.put("txtLuasAmbil",getParam("txtLuasAmbil"));
		h.put("sorSaliran", getParam("sorSaliran"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtJarak", getParam("txtJarak"));
		h.put("sorPBT", getParam("sorPBT"));
		h.put("txtNamaPBT", getParam("txtNamaPBT"));
		h.put("sorRizab", getParam("sorRizab"));
		h.put("flagBukit", getParam("flagBukit"));
		h.put("flagLandai", getParam("flagLandai"));
		h.put("flagRata", getParam("flagRata"));
		h.put("flagRendah", getParam("flagRendah"));
		h.put("flagBerpaya", getParam("flagBerpaya"));
		h.put("flagLurah", getParam("flagLurah"));
		h.put("flagLembah", getParam("flagLembah"));
		h.put("txtPerihalRupabumi", getParam("txtPerihalRupabumi"));
		h.put("flagUsaha", getParam("flagUsaha"));
		h.put("flagLapang", getParam("flagLapang"));
		h.put("flagTerbiar", getParam("flagTerbiar"));
		h.put("flagHutan", getParam("flagHutan"));
		h.put("flagBelukar", getParam("flagBelukar"));
		h.put("flagSemak", getParam("flagSemak"));
		h.put("txtPerihalKeadaan", getParam("txtPerihalKeadaan"));
		h.put("txtHalangan", getParam("txtHalangan"));
		h.put("txtTanaman", getParam("txtTanaman"));
		h.put("txtKemudahan", getParam("txtKemudahan"));
		h.put("txtSempadanUtara", getParam("txtSempadanUtara"));
		h.put("txtSempadanSelatan", getParam("txtSempadanSelatan"));
		h.put("txtSempadanTimur", getParam("txtSempadanTimur"));
		h.put("txtSempadanBarat", getParam("txtSempadanBarat"));
		h.put("socUnitHargaSO", getParam("socUnitHargaSO"));
		h.put("txtHargaSeunitSO", getParam("txtHargaSeunitSO"));
		h.put("txtHargaPasaranSO", getParam("txtHargaPasaranSO"));
		h.put("txtPenjejasanSO", getParam("txtPenjejasanSO"));
		h.put("txtPecahSO", getParam("txtPecahSO"));
		h.put("txtKenaikanSO", getParam("txtKenaikanSO"));
		h.put("socUnitHargaJP", getParam("socUnitHargaJP"));
		h.put("txtHargaSeunitJP", getParam("txtHargaSeunitJP"));
		h.put("txtHargaPasaranJP", getParam("txtHargaPasaranJP"));
		h.put("txtPenjejasanJP", getParam("txtPenjejasanJP"));
		h.put("txtPecahJP", getParam("txtPecahJP"));
		h.put("txtKenaikanJP", getParam("txtKenaikanJP"));
		h.put("socUnitHargaG", getParam("socUnitHargaG"));
		h.put("txtHargaBorangG", getParam("txtHargaBorangG"));
		h.put("txtUlasanPegawai", getParam("txtUlasanPegawai"));
		// h.put("idPelapor", session.getAttribute("_ekptg_user_id"));
		// h.put("txtPelapor",getParam("txtPelapor"));
		h.put("socPegawai", getParam("socPegawai"));
		h.put("txdTarikhUlasan", getParam("txdTarikhUlasan"));

		h.put("txtStrukturBangunanSO", getParam("txtStrukturBangunanSO"));
		h.put("txtStrukturBangunan", getParam("txtStrukturBangunan"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// if status = JPBD/JPPH
		// if(status.equals("43")){
		// FrmUPTSek8InfoTanahTerperinciTanahData.updateStatus(h);
		// }

		// get id
		long id_tanah = DB.getNextID("TBLPPTTANAH_SEQ");

		// update into tblppthakmilik
		FrmUPTSek8InfoTanahTerperinciTanahData.updateHMtanah(h);

		// insert into tblppttanah
		FrmUPTSek8InfoTanahTerperinciTanahData.simpanTanahTerperinci(h,
				id_tanah);

		String valItem = getParam("txtTajuk");

		if (valItem != "") {
			List items = upload.parseRequest(request);

			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, id_tanah);
				}
			}
		}// close valItem

	}// close simpanTanahTerperinci

	public void insertPopupReg(String nama_class, String tajuk_class,
			String group, Db db) throws Exception {
		// Db db = null;
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			String sql = " INSERT INTO MODULE ( "
					+ " MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "
					+ " MODULE_GROUP, MODULE_DESCRIPTION)  " + " VALUES ('"
					+ nama_class + "','" + tajuk_class + "','" + nama_class
					+ "','" + group + "','') ";
			myLogger.info("REG CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			sql = " INSERT INTO ROLE_MODULE ( "
					+ " MODULE_ID, USER_ROLE) "
					+ " SELECT '"
					+ nama_class
					+ "' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%'";
			myLogger.info("REG ROLE CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public int checkRegPopup(String class_name, Db db) throws Exception {

		// Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// db = new Db();
			sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '"
					+ class_name + "'";
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			// Close the database connection
			// if ( db != null ) db.close();
			// if (rs != null) rs.close();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	private void updateTanahTerperinci(HttpSession session) throws Exception {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		Hashtable h = new Hashtable();

		String id_tanah = getParam("id_tanah");

		h.put("id_tanah", id_tanah);
		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("id_permohonan", getParam("id_permohonan"));

		// update tblppthakmilik
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("socKategoriTanah", getParam("socKategoriTanah"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("socJenisHakmilik", getParam("socJenisHakmilik"));

		h.put("txtLuasLotAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("txtLuasLotAmbil",
				Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));

		// insert tblppttanah
		h.put("txtJenisPisah", getParam("txtJenisPisah"));
		h.put("txtStatusTanah", getParam("txtStatusTanah"));
		h.put("txtPendahuluan", getParam("txtPendahuluan"));
		h.put("txdTarikhLawatMula", getParam("txdTarikhLawatMula"));
		h.put("txdTarikhLawatAkhir", getParam("txdTarikhLawatAkhir"));
		h.put("txtJalanUtama", getParam("txtJalanUtama"));
		h.put("txtJalanMasuk", getParam("txtJalanMasuk"));
		h.put("txtNamaTempat", getParam("txtNamaTempat"));
		h.put("txtPerumahan", getParam("txtPerumahan"));
		h.put("txtIndustri", getParam("txtIndustri"));
		h.put("sorSaliran", getParam("sorSaliran"));
		// h.put("socUnitLuasLot",getParam("socUnitLuasLot"));
		// h.put("socUnitLuasAmbil",getParam("socUnitLuasAmbil"));
		// h.put("txtLuasLot",getParam("txtLuasLot"));
		// h.put("txtLuasAmbil",getParam("txtLuasAmbil"));
		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtJarak", getParam("txtJarak"));
		h.put("sorPBT", getParam("sorPBT"));
		h.put("txtNamaPBT", getParam("txtNamaPBT"));
		h.put("sorRizab", getParam("sorRizab"));
		h.put("flagBukit", getParam("flagBukit"));
		h.put("flagLandai", getParam("flagLandai"));
		h.put("flagRata", getParam("flagRata"));
		h.put("flagRendah", getParam("flagRendah"));
		h.put("flagBerpaya", getParam("flagBerpaya"));
		h.put("flagLurah", getParam("flagLurah"));
		h.put("flagLembah", getParam("flagLembah"));
		h.put("txtPerihalRupabumi", getParam("txtPerihalRupabumi"));
		h.put("flagUsaha", getParam("flagUsaha"));
		h.put("flagLapang", getParam("flagLapang"));
		h.put("flagTerbiar", getParam("flagTerbiar"));
		h.put("flagHutan", getParam("flagHutan"));
		h.put("flagBelukar", getParam("flagBelukar"));
		h.put("flagSemak", getParam("flagSemak"));
		h.put("txtPerihalKeadaan", getParam("txtPerihalKeadaan"));
		h.put("txtHalangan", getParam("txtHalangan"));
		h.put("txtTanaman", getParam("txtTanaman"));
		h.put("txtKemudahan", getParam("txtKemudahan"));
		h.put("txtSempadanUtara", getParam("txtSempadanUtara"));
		h.put("txtSempadanSelatan", getParam("txtSempadanSelatan"));
		h.put("txtSempadanTimur", getParam("txtSempadanTimur"));
		h.put("txtSempadanBarat", getParam("txtSempadanBarat"));
		h.put("socUnitHargaSO", getParam("socUnitHargaSO"));
		h.put("txtHargaSeunitSO", getParam("txtHargaSeunitSO"));
		h.put("txtHargaPasaranSO", getParam("txtHargaPasaranSO"));
		h.put("txtPenjejasanSO", getParam("txtPenjejasanSO"));
		h.put("txtPecahSO", getParam("txtPecahSO"));
		h.put("txtKenaikanSO", getParam("txtKenaikanSO"));
		h.put("socUnitHargaJP", getParam("socUnitHargaJP"));
		h.put("txtHargaSeunitJP", getParam("txtHargaSeunitJP"));
		h.put("txtHargaPasaranJP", getParam("txtHargaPasaranJP"));
		h.put("txtPenjejasanJP", getParam("txtPenjejasanJP"));
		h.put("txtPecahJP", getParam("txtPecahJP"));
		h.put("txtKenaikanJP", getParam("txtKenaikanJP"));
		h.put("socUnitHargaG", getParam("socUnitHargaG"));
		h.put("txtHargaBorangG", getParam("txtHargaBorangG"));
		h.put("txtUlasanPegawai", getParam("txtUlasanPegawai"));
		// h.put("idPelapor", session.getAttribute("_ekptg_user_id"));
		// h.put("txtPelapor",getParam("txtPelapor"));
		h.put("txdTarikhUlasan", getParam("txdTarikhUlasan"));

		h.put("txtStrukturBangunanSO", getParam("txtStrukturBangunanSO"));
		h.put("txtStrukturBangunan", getParam("txtStrukturBangunan"));

		h.put("socPegawai", getParam("socPegawai"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		String valItem = getParam("txtTajuk");

		if (valItem != "") {
			List items = upload.parseRequest(request);

			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, Utils.parseLong(id_tanah));
				}
			}
		} else {

			// update into tblppthakmilik
			FrmUPTSek8InfoTanahTerperinciTanahData.updateHMtanah(h);

			// update into tblppttanah
			FrmUPTSek8InfoTanahTerperinciTanahData.updateTanahTerperinci(h);

		}

	}// close updateTanahTerperinci

	private void saveData(FileItem item, long id_tanah) throws Exception {
		Db db = null;
		try {
			db = new Db();
			long id_dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPPTDOKUMEN "
							+ "(id_dokumen,id_tanah,nama_fail,keterangan,tajuk,jenis_Mime,content) "
							+ "values(?,?,?,?,?,?,?)");
			ps.setLong(1, id_dokumen);
			ps.setLong(2, id_tanah);
			ps.setString(3, item.getName());
			ps.setString(4, getParam("txtKeterangan"));
			ps.setString(5, getParam("txtTajuk"));
			ps.setString(6, item.getContentType());
			ps.setBinaryStream(7, item.getInputStream(), (int) item.getSize());
			ps.executeUpdate();
			con.commit();

		} catch (SQLException se) {
			throw new Exception("Ralat : Masalah muatnaik fail");
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	private void DataTanahTerperinci(HttpSession session, String idHakmilik,
			String disability, String id_projekNegeri) throws Exception {

		model.setMaklumatTanahTerperinci(idHakmilik);
		Vector dataMaklumatTanahTerperinci = model.getMaklumatTanahTerperinci();
		String id_kategoritanah = "";
		String txtNoSyit = "";
		String id_tanah = "";
		String id_pegawai = "";
		String id_jenishakmilik = "";
		String id_pelapor = "";
		if (dataMaklumatTanahTerperinci.size() != 0) {
			Hashtable tt = (Hashtable) dataMaklumatTanahTerperinci.get(0);
			id_kategoritanah = (String) tt.get("id_kategoritanah");
			txtNoSyit = (String) tt.get("no_syit");
			id_tanah = (String) tt.get("id_tanah");
			id_pegawai = (String) tt.get("id_pegawai");
			id_jenishakmilik = (String) tt.get("id_jenishakmilik");
			id_pelapor = (String) tt.get("id_pelapor");
		}

		// data and id
		context.put("dataMaklumatTanahTerperinci", dataMaklumatTanahTerperinci);
		context.put("txtNoSyit", txtNoSyit);
		context.put("id_tanah", id_tanah);
		context.put("id_pegawai", id_pegawai);
		context.put("id_kategoritanah", id_kategoritanah);
		context.put("id_jenishakmilik", id_jenishakmilik);
		// System.out.println("ID KATEGORI TANAH --"+id_kategoritanah);

		String mode = "";
		if (disability.equals("enabled")) {
			mode = "";
		} else {
			mode = "disabled class=disabled";
		}

		// dropdown
		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah", Utils.parseLong(id_kategoritanah),
				"id=socKategoriTanah " + mode + " style=width:auto", null));
		context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
				"socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
				"id=socJenisHakmilik " + mode + " style=width:250"));
		context.put("selectPegawai", HTML.SelectPegawaiPPTByNegeri(
				id_projekNegeri, "socPegawai", Utils.parseLong(id_pelapor),
				null, "style=width:325px " + mode + " "));

	}// close NewDataTanahTerperinci

	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
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
			this.context.put("listPermohonan", paging.getPage(page));
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

	public void checkFieldWujud(String table_name, String column_name,
			String data_type, Db db) throws Exception {

		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"
					+ table_name + "' AND COLUMN_NAME = '" + column_name + "' ";
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} finally {
		}

		if (total == 0) {
			sql = "ALTER TABLE " + table_name + " " + " ADD " + column_name
					+ " " + data_type + " ";
			rs = db.getStatement().executeQuery(sql);
			// ALTER TABLE supplier ADD supplier_name varchar2(50);
		}

	}

	public void alterFieldSize(Db db) throws Exception {
		String sql = "";
		ResultSet rs = null;
		try {
			sql = "ALTER TABLE TBLPPTDOKUMEN MODIFY TAJUK VARCHAR(4000) ";
			rs = db.getStatement().executeQuery(sql);
			// ALTER TABLE supplier ADD supplier_name varchar2(50);
		} finally {
		}

	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,
			String id_permohonan, String id_fail,
			String id_suburusanstatusfailppt) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h, id_suburusanstatusfailppt,
				"1475");

	}// close updateSuburusanStatusFailPPT

}// close class
