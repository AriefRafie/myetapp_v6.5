package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.engine.EmailSender;
import ekptg.helpers.DB;
import ekptg.helpers.Paging;
//import ekptg.intergration.EkptgEmailSender;
//import ekptg.intergration.EmailProperty;

import ekptg.view.esaduan.EkptgEmailSender;
import ekptg.view.esaduan.EmailProperty;

import ekptg.model.esaduan.EtappSupportAduanData;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmKebenaranKemaskiniFailData;
import ekptg.model.ppt.MyInfoPPTData;

public class FrmKebenaranKemaskiniFail extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmKebenaranKemaskiniFail.class);
	FrmKebenaranKemaskiniFailData logic = new FrmKebenaranKemaskiniFailData();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	String user_negeri_login = "";
	String userId = "";
	String role = "";
	HttpSession session = null;
	String action = "";
	String usernamelogin = "";
	

	public String doTemplate2() throws Exception {

		String vm = "app/ppk/editfail/index.jsp";

		context.put("ScrollX", getParam("ScrollX"));
		context.put("ScrollY", getParam("ScrollY"));

		session = request.getSession();
		userId = (String) session.getAttribute("_ekptg_user_id");
		
		
		
		myLogger.info("userIdlogin :" + usernamelogin);
		context.put("pengguna_aktif", userId);
		user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		context.put("id_negeri_user", user_negeri_login);
		Hashtable id_seksyen_user = logic.user_seksyen(userId);
		context.put("id_seksyen_user", id_seksyen_user.get("id_seksyen_user")
				.toString());
		myLogger.info("id_seksyen_user :" + id_seksyen_user);
		role = (String) session.getAttribute("myrole");
		String command = getParam("command");
		String mode = getParam("mode");
		action = getParam("action");
		String flag_daftar = "";

		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");
		context.put("usid", usid);

		Hashtable user_name = (Hashtable) getUsername(usid);
		
		usernamelogin = (String) user_name.get("USER_NAME");
		
		context.put("user_name", (String) user_name.get("USER_NAME"));

		String bolehsimpan = "";
		String readmode = "";
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			bolehsimpan = "yes";
		}
		myLogger.info("COMMAND :" + command);
		myLogger.info("ROLE :" + role);
		context.put("role", role);

		/*
		 * context.put("showEmelAlert", "yes"); Vector checkEmail = null;
		 * checkEmail = logic.checkEmail(userId); if(checkEmail.size()!=0){
		 * context.put("showEmelAlert", "no"); }else{
		 * context.put("showEmelAlert", "yes"); }
		 */

		context.put("open_maklumat_teknikal", "yes");

		context.put("num_files", 1);
		Vector list_jenisaduan = null;
		context.put("list_jenisaduan", "");
		Vector list_aduan = null;
		context.put("list_aduan", "");
		Vector list_users = null;
		context.put("list_users", "");
		Vector list_sumberaduan = null;
		context.put("list_sumberaduan", "");
		Vector list_module = null;
		context.put("list_module", "");
		Vector listDokumen_aduan = null;
		context.put("listDokumen_aduan", "");
		Vector listComment_aduan = null;
		context.put("listComment_aduan", "");
		Vector listComment_aduan_tech = null;
		context.put("listComment_aduan_tech", "");
		Vector listAgihan_aduan = null;
		context.put("listAgihan_aduan", "");
		Vector listTechTeam_aduan = null;
		context.put("listTechTeam_aduan", "");
		Vector list_statusaduan = null;
		Vector list_laporanModul = null;
		Vector list_laporanNegeri = null;
		Vector list_laporanKategori = null;
		Vector list_negeri = null;
		Vector check_notifikasi_index_maklumbalas_aduan = null;
		Vector check_notifikasi_index_maklumbalas_teknikal = null;
		ArrayList id_penerima_array = null;

		context.put("check_notifikasi_index_maklumbalas_aduan", "");
		context.put("check_notifikasi_index_maklumbalas_teknikal", "");
		context.put("list_statusaduan", "");
		context.put("readmode", "edit");
		context.put("open_aduanlist", "no");
		context.put("open_aduancarian", "no");
		context.put("open_maklumat_aduan", "no");
		context.put("open_maklumat_teknikal", "no");
		context.put("open_upload", "no");
		context.put("open_agihan", "no");
		context.put("open_maklumbalas", "no");
		context.put("open_agihan", "no");
		context.put("flag_simpan_doc", "no");
		context.put("setDefaultUser", "no");
		context.put("flag_popup_alert_success", "");
		context.put("view_skrin", "aduan");
		context.put("tetap_filter", 3);
		context.put("tetap_filter_teknikal", 3);
		context.put("list_laporanModul", "");
		context.put("list_laporanNegeri", "");
		context.put("list_laporanKategori", "");
		context.put("list_negeri", "");
		context.put("flag_buka_upload", getParam("flag_buka_upload"));
		context.put("flag_buka_maklumbalasaduan",
				getParam("flag_buka_maklumbalasaduan"));
		context.put("flag_buka_maklumbalasteknikal",
				getParam("flag_buka_maklumbalasteknikal"));
		context.put("id_fail_carian", "");
		context.put("list_sub", "");
		context.put("cari_sub", "");
		context.put("list_fail","");
		context.put("list_status", "");
		context.put("view_list_fail", "");
		context.put("pilihpegawai", "");
		context.put("pemohonedit", "");
		context.put("getMainFail", "");
		context.put("txtTujPinda","");
		context.put("txtMula", "");
		context.put("txtAkhir", "");

		if (command.equals("bukaMaklumbalasTeknikal")) {
			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");

			return_after_load();
			String flag_buka_maklumbalasteknikal = getParam("flag_buka_maklumbalasteknikal");
			myLogger.info("flag_buka_maklumbalasteknikal :"
					+ flag_buka_maklumbalasteknikal);
			if (flag_buka_maklumbalasteknikal.equals("yes")) {
				context.put("flag_buka_maklumbalasteknikal", "no");
			} else {
				context.put("flag_buka_maklumbalasteknikal", "yes");
			}
			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			String id_fail_atheader = getParam("id_fail_atheader");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
		}
		
		else if (command.equals("simpanSelesaiPinda")) {
			String id_permohonan = "";
			String check_selesai = getParam("check_selesai");
			setSimpanSelesaiPinda(getParam("id_fail_carian"),session, getParam("id_permohonan_kebenaran"),check_selesai );

			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");
			
			updateNotification(session,getParam("id_fail_carian"),usid); 

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");
			
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			// urusan_status_selesai(session);

			context.put("listSemak", "");
			context.put("listSemak_size", "");
			context.put("nofail", "");
			context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
			context.put("id_keputusanpermohonan", "");
			context.put("id_perbicaraan", "");
			context.put("id_permohonan", "");

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			String id_fail_atheader = getParam("id_fail_carian");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TUJUAN_PINDAAN") != null) {
				context.put(
						"txtTujPinda",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TUJUAN_PINDAAN"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_MULA_PINDA") != null) {
				context.put(
						"txtMula",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_MULA_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_AKHIR_PINDA") != null) {
				context.put(
						"txtAkhir",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_AKHIR_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_SELESAI_PINDA") != null) {
				context.put(
						"check_selesai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_SELESAI_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_PINDA_SELESAI") != null) {
				context.put(
						"flag_pinda_selesai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_PINDA_SELESAI"));
			}
			
			System.out.println("flag_pinda ==="+logic.getMainFail(getParam("id_fail_carian")).get("FLAG_PINDA_SELESAI"));


			
			
		}
		
		else if (command.equals("simpanSemak")) {
			String id_permohonan = "";
			String check_selesai = getParam("check_selesai");
			setSimpanSelesaiPinda(getParam("id_fail_carian"),session, getParam("id_permohonan_kebenaran"),check_selesai );

			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");
			
			updateNotification(session,getParam("id_fail_carian"),usid); 

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");
			
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			// urusan_status_selesai(session);

			context.put("listSemak", "");
			context.put("listSemak_size", "");
			context.put("nofail", "");
			context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
			context.put("id_keputusanpermohonan", "");
			context.put("id_perbicaraan", "");
			context.put("id_permohonan", "");

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			String id_fail_atheader = getParam("id_fail_carian");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TUJUAN_PINDAAN") != null) {
				context.put(
						"txtTujPinda",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TUJUAN_PINDAAN"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_MULA_PINDA") != null) {
				context.put(
						"txtMula",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_MULA_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_AKHIR_PINDA") != null) {
				context.put(
						"txtAkhir",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_AKHIR_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_SELESAI_PINDA") != null) {
				context.put(
						"check_selesai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_SELESAI_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_PINDA_SELESAI") != null) {
				context.put(
						"flag_pinda_selesai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_PINDA_SELESAI"));
			}
			
			System.out.println("flag_pinda ==="+logic.getMainFail(getParam("id_fail_carian")).get("FLAG_PINDA_SELESAI"));


			
			
		}
		
		else if (command.equals("simpanPilihanPegawai")) {
			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			System.out.println("id_fail_carian :: "+ getParam("id_fail_carian"));
			
			String txtNoFailSub = getParam("txtNoFailSub");

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			context.put("list_status", "");
			context.put("view_list_fail", "yes");

			String tarikhMula = getParam("txtMula");
			String tarikhAkhir = getParam("txtAkhir");
			
			setSimpanPilihanPegawai(getParam("id_fail_carian"),session, getParam("id_permohonan_kebenaran"),
					getParam("user_id_kebenaran_edit"),
					getParam("flag_kebenaran_edit"),
					getParam("catatan_kebenaran_edit"),getParam("pilihpegawai"),getParam("txtTujPinda"), tarikhMula, tarikhAkhir );
			
			
			String tujuan = "Pemakluman Memohon Kebenaran Mengemaskini Fail "+txtNoFailSub+" oleh Pegawai";
			String pegawaiAgihan = getParam("pilihpegawai");
			String idpegawaiMemohon = getParam("userId");

			String namaPegawaiMemohon = (String) user_name.get("USER_NAME");
			
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
		    Date now = new Date();
		    String tarikMohon = sdfDate.format(now);
		    String namaSiMati = logic.getNamaSiMati(getParam("id_permohonan_kebenaran"));
		    
			//aishah 27/02/2017
			//salnizam disablekan dulu hantar email ---> hantarEmel("content","Tindakan Pengarah/Pegawai untuk Permohonan Kebenaran Kemaskini Fail",txtNoFailSub, tujuan,pegawaiAgihan ,idpegawaiMemohon,namaPegawaiMemohon,tarikMohon,namaSiMati);	
			
			
			defaultList("");
			String id_fail_atheader = getParam("id_fail_atheader");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			// paparAduan();

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("pilihpegawai", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");
			context.put("disableButtonSimpan", "disable");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			
			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));
				
				
				
				
			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TUJUAN_PINDAAN") != null) {
				context.put(
						"txtTujPinda",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TUJUAN_PINDAAN"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_MULA_PINDA") != null) {
				context.put(
						"txtMula",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_MULA_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_AKHIR_PINDA") != null) {
				context.put(
						"txtAkhir",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_AKHIR_PINDA"));
			}

			
			
			
		}

		else if (command.equals("simpanFlag")) {
			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");
			String tarikhMula = getParam("txtMula");
			String tarikhAkhir = getParam("txtAkhir");
			setFlagKebenaran(getParam("id_fail_carian"),session, getParam("id_permohonan_kebenaran"),
					getParam("user_id_kebenaran_edit"),
					getParam("flag_kebenaran_edit"),
					getParam("catatan_kebenaran_edit"),tarikhMula,tarikhAkhir);
//tambah tempoh
			defaultList("");
			String id_fail_atheader = getParam("id_fail_atheader");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			// paparAduan();

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			
			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}


			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TUJUAN_PINDAAN") != null) {
				context.put(
						"txtTujPinda",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TUJUAN_PINDAAN"));
			}
			
			//tambah tempoh		
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_MULA_PINDA") != null) {
				context.put(
						"txtMula",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_MULA_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_AKHIR_PINDA") != null) {
				context.put(
						"txtAkhir",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_AKHIR_PINDA"));
			}
		}

		else if (command.equals("daftarAgih")) {
			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");
			String tarikhMula = getParam("txtMula");
			String tarikhAkhir = getParam("txtAkhir");
			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");

			daftarAgih(logic.getMainFail(getParam("id_fail_carian")).get("ID_PEGAWAI_MOHON_EDIT").toString(),logic.getMainFail(getParam("id_fail_carian")).get("ID_PEMOHON_MOHON_EDIT").toString(),tarikhMula,tarikhAkhir);
			defaultList("");
			String id_fail_atheader = getParam("id_fail_atheader");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			// paparAduan();

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			//context.put("flag_popup_alert_success", "2");

			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
		}
		// skrin maklumbalas technical
		else if (command.equals("simpanMaklumbalasTeknikal")) {
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");
			
			addComment(session, "2",logic.getMainFail(getParam("id_fail_carian")).get("ID_PEGAWAI_MOHON_EDIT").toString(),logic.getMainFail(getParam("id_fail_carian")).get("ID_PEMOHON_MOHON_EDIT").toString());
			defaultList("");

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			String id_fail_atheader = getParam("id_fail_atheader");

			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
		}
		// skrin maklumbalas pengadu

		else if (command.equals("deleteComment")) {
			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");

			String[] id_escomment = this.request
					.getParameterValues("id_editcomment");
			if (id_escomment != null) {
				for (int i = 0; i < id_escomment.length; i++) {
					myLogger.info("id_escomment :" + id_escomment);
					logic.deleteComment(session, id_escomment[i]);

				}
			}

			defaultList("");
			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");

			context.put("open_maklumat_teknikal", "yes");

			String id_fail_atheader = getParam("id_fail_atheader");

			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
		}

		else if (command.equals("cariMainSub")) {
			String txtNoFailSub = getParam("txtNoFailSub");
			context.put("txtNoFailSub", txtNoFailSub.trim());
			context.put("list_fail",
					logic.search_nofail(txtNoFailSub.trim(), usid));
			context.put("view_list_fail", "yes");
			context.put("listSemak", "");
			context.put("listSemak_size", "");
			context.put("nofail", "");
			context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
			context.put("id_keputusanpermohonan", "");
			context.put("id_perbicaraan", "");
			context.put("id_permohonan", "");

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");

		} else if (command.equals("paparSub")) {
			
			context.put("id_fail_carian", getParam("id_fail_carian"));
			String txtNoFailSub = getParam("txtNoFailSub");
			
			updateNotification(session,getParam("id_fail_carian"),usid); 

			if (logic.getMainFail(getParam("id_fail_carian")).get("NO_FAIL") != null) {
				context.put(
						"txtNoFailSub_selected",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
			}
			context.put("txtNoFailSub",
					logic.getMainFail(getParam("id_fail_carian"))
							.get("NO_FAIL"));

			// logic.setListSub(getParam("id_fail_carian"));
			// list_sub = logic.getListSub();
			context.put("list_sub", "");
			context.put(
					"list_fail",
					logic.search_nofail(
							logic.getMainFail(getParam("id_fail_carian")).get(
									"NO_FAIL")
									+ "", usid));
			context.put("cari_sub", "yes");
			// list_status = logic.list_status(getParam("id_fail_carian"));
			context.put("list_status", "");
			context.put("view_list_fail", "yes");
			String id_permohonan = "";
			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_FAIL") != null) {
				id_permohonan = logic.getMainFail(getParam("id_fail_carian"))
						.get("ID_PERMOHONAN") + "";
				context.put(
						"ID_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_FAIL"));
				context.put(
						"ID_PERMOHONAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
				context.put(
						"NO_FAIL_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"NO_FAIL"));
				context.put(
						"ID_SUBURUSAN_TEMP",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_SUBURUSAN"));

			} else {
				context.put("ID_FAIL_TEMP", "");
				context.put("ID_PERMOHONAN_TEMP", "");
				context.put("NO_FAIL_TEMP", "");
				context.put("ID_SUBURUSAN_TEMP", "");

			}
			headerppk_baru(session, id_permohonan, "Y", "", "T");
			context.put("getMainFail", logic.getMainFail(getParam("id_fail_carian")));
			// urusan_status_selesai(session);

			context.put("listSemak", "");
			context.put("listSemak_size", "");
			context.put("nofail", "");
			context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
			context.put("id_keputusanpermohonan", "");
			context.put("id_perbicaraan", "");
			context.put("id_permohonan", "");
			context.put("disableButtonSimpan", "");

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");
			context.put("view_skrin", "aduan");

			// paparAduan();
			context.put("open_aduancarian", "yes");
			context.put("open_maklumat_aduan", "yes");
			context.put("open_upload", "yes");
			context.put("open_maklumbalas", "yes");
			context.put("open_agihan", "yes");
			context.put("papar_selesai", "yes");

			context.put("open_maklumat_teknikal", "yes");

			String id_fail_atheader = getParam("id_fail_carian");
			if (!id_fail_atheader.equals("") && !id_fail_atheader.equals(null)) {
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_fail_atheader, "2");
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_admin_team(user_negeri_login,
						role, userId,"all",id_fail_atheader);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get("ID_STATUS") != null) {
				context.put(
						"latest_idstatus",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_STATUS"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PERMOHONAN") != null) {
				context.put(
						"id_permohonan_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PERMOHONAN"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_KEBENARAN_EDIT") != null) {
				context.put(
						"flag_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"USER_ID_KEBENARAN_EDIT") != null) {
				context.put(
						"user_id_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_ID_KEBENARAN_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get("USER_NAME") != null) {
				context.put(
						"nama_user_yg_beri_kebenaran",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"USER_NAME"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"CATATAN_KEBENARAN_EDIT") != null) {
				context.put(
						"catatan_kebenaran_edit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"CATATAN_KEBENARAN_EDIT"));
			}

			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEGAWAI_MOHON_EDIT") != null) {
				context.put(
						"pilihpegawai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEGAWAI_MOHON_EDIT"));
			}
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"ID_PEMOHON_MOHON_EDIT") != null) {
				context.put(
						"pemohonedit",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"ID_PEMOHON_MOHON_EDIT"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TUJUAN_PINDAAN") != null) {
				context.put(
						"txtTujPinda",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TUJUAN_PINDAAN"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_MULA_PINDA") != null) {
				context.put(
						"txtMula",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_MULA_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"TARIKH_AKHIR_PINDA") != null) {
				context.put(
						"txtAkhir",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"TARIKH_AKHIR_PINDA"));
			}
			
			if (logic.getMainFail(getParam("id_fail_carian")).get(
					"FLAG_PINDA_SELESAI") != null) {
				context.put(
						"flag_pinda_selesai",
						logic.getMainFail(getParam("id_fail_carian")).get(
								"FLAG_PINDA_SELESAI"));
			}
			
			//System.out.println("tujuan pindaan ==="+logic.getMainFail(getParam("id_fail_carian")).get("TUJUAN_PINDAAN"));

		} else {

			context.put("open_aduancarian", "yes");
			context.put("log_aduan_cari", "");
			context.put("user_id_cari", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari", "");
			context.put("id_statusesaduan_cari", "");
			context.put("id_jenismodulesaduan_cari", "");

			context.put("listSemak", "");
			context.put("listSemak_size", "");
			context.put("nofail", "");
			context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			context.put("seksyen", "");
			context.put("id_perintah", "");
			context.put("id_keputusanpermohonan", "");
			context.put("id_perbicaraan", "");
			context.put("id_permohonan", "");
			context.put("txtNoFailSub", "");
			context.put("list_sub", "");
			context.put("list_fail", "");
			context.put("txtTujPinda", "");
			context.put("txtMula", "");
			context.put("txtAkhir", "");
			

			context.put("view_skrin", "aduan");

		}
		check_notifikasi_index_maklumbalas_teknikal = logic.senarai_listNotifikasi(userId,"2");
		context.put("check_notifikasi_index_maklumbalas_teknikal", check_notifikasi_index_maklumbalas_teknikal);
		
		System.out.println("-vm----"+ vm);
		System.out.println("-command----"+ command);
		return vm;
	}

	private void displaySenaraiAduan() throws Exception {
		Vector list_aduan = null;

		if (role.equals("adminsuper_es")) {
			list_aduan = logic.getAduan(userId, "", "", "", "", "", "", "", "",
					"", "", "", "", "");
		} else {
			list_aduan = logic.getAduan(userId, "", "", "", "",
					user_negeri_login, "", "", "", "", "", "", "", "");
		}
		context.put("SenaraiFail", list_aduan);
		setupPage(session, action, list_aduan);
	}

	private void daftarAgih(String id_pegawai,String id_pemohon, String tarikhMula, String tarikhAkhir) throws Exception {
		if (!getParam("id_fail_atheader").equals("")) {
			String[] selectagih = this.request.getParameterValues("selectagih");
			String[] catatan_agihan = this.request
					.getParameterValues("catatan_agihan");
			String id_fail_atheader = getParam("id_fail_atheader");
			logic.deleteAgihan(id_fail_atheader);

			ArrayList id_penerima_agihan_array = new ArrayList();

			if (selectagih != null) {
				for (int i = 0; i < selectagih.length; i++) {
					myLogger.info("selectagih :" + selectagih[i]);
					myLogger.info("catatan_agihan :" + catatan_agihan[i]);
					addAgihan(session, selectagih[i], catatan_agihan[i],
							id_fail_atheader,id_pegawai,id_pemohon, tarikhMula, tarikhAkhir);

					//id_penerima_agihan_array.add(selectagih[i]);
					

				}
				// addNotifikasiAgihan(session,id_fail_atheader,id_penerima_agihan_array);
			}
		}

	}

	private void defaultList(String flag_daftar) throws Exception {
		// flag_daftar..check dah daftar ke belum
		Vector list_jenisaduan = null;
		context.put("list_jenisaduan", "");
		Vector list_users = null;
		context.put("list_users", "");
		Vector list_sumberaduan = null;
		context.put("list_sumberaduan", "");
		Vector list_module = null;
		context.put("list_module", "");
		Vector list_statusaduan = null;
		context.put("list_statusaduan", "");

		list_jenisaduan = logic.getListJenisAduan();
		context.put("list_jenisaduan", list_jenisaduan);

		list_sumberaduan = logic.getListSumberAduan();
		context.put("list_sumberaduan", list_sumberaduan);

		list_module = logic.getListModule("");
		context.put("list_module", list_module);

		String command = getParam("command");

		if ((!role.equals("admin_es") && !role.equals("developer_es") && flag_daftar
				.equals("no"))) {
			userId = (String) session.getAttribute("_ekptg_user_id");
			list_users = logic.getListUsers(userId, "", "", "", "", "");
			context.put("list_users", list_users);
			context.put("setDefaultUser", "yes");

			if (list_users.size() != 0) {
				Hashtable get_user = (Hashtable) list_users.get(0);
				context.put("user_id", (String) get_user.get("user_id"));
				context.put("nama_pengadu", (String) get_user.get("user_name"));
				context.put("no_tel", (String) get_user.get("user_id"));
				context.put("seksyen", (String) get_user.get("nama_seksyen"));
				context.put("pejabat", (String) get_user.get("nama_pejabat"));
				context.put("negeri", (String) get_user.get("nama_negeri"));
				context.put("daerah", (String) get_user.get("nama_daerah"));
				context.put("emel", (String) get_user.get("emel"));
				context.put("nama_jawatan",
						(String) get_user.get("nama_jawatan"));
				context.put("id_seksyen_user",
						(String) get_user.get("id_seksyen"));
				context.put("id_negeri_pengadu",
						(String) get_user.get("id_negeri_pengadu"));
				context.put("id_pejabat_pengadu",
						(String) get_user.get("id_pejabat_pengadu"));
			}

		} else {
			list_users = logic.getListUsers("", "", user_negeri_login, "", "",
					"");
			context.put("list_users", list_users);
		}

		list_statusaduan = logic.getListStatusAduan();
		context.put("list_statusaduan", list_statusaduan);

	}

	
	private void return_after_load() throws Exception {
		String key = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
			key = (String) allparam.nextElement();
			value = request.getParameter(key);
			this.context.put(key, value);
		}
	}

	private void defaultAduan() throws Exception {
		context.put("id_esaduan", "");
		context.put("user_id", "");
		context.put("id_jenisaduan", "");
		context.put("nama_pengadu", "");
		context.put("emel", "");
		context.put("id_negeri_pengadu", "");
		context.put("id_pejabat_pengadu", "");
		context.put("id_sumberaduan", "");
		context.put("aduan", "");
		context.put("no_fail", "");
		context.put("no_tel", "");
		context.put("seksyen", "");
		context.put("pejabat", "");
		context.put("negeri", "");
		context.put("daerah", "");
		context.put("nama_modul", "");
		context.put("nama_skrin", "");
		context.put("log_aduan", "");
		context.put("id_jenismodulesaduan", "");
		context.put("id_esdokumen", "");
		context.put("tajuk", "");
		context.put("nama_jawatan", "");
		context.put("flag_masalah_db", "");
		context.put("flag_masalah_skrin", "");
		context.put("flag_masalah_report", "");
		context.put("flag_masalah_hw", "");
		context.put("flag_masalah_flow", "");
		context.put("flag_masalah_pertanyaan", "");
		context.put("flag_masalah_penambahan", "");
		context.put("tahap_kesusahan", "");
		context.put("ulasan_teknikal", "");
		context.put("id_statusesaduan", "");
		context.put("id_statusesaduan_DB", "");
		context.put("tarikh_aduan_hantar", "");
		context.put("tarikh_aduan_hantar_date", "");
		context.put("tarikh_aduan_hantar_hour", "");
		context.put("tarikh_aduan_hantar_minute", "");
		context.put("tarikh_aduan_hantar_ampm", "");

	}

	
	private void addAgihan(HttpSession session, String user_agih_id,
			String catatan_agihan, String id_fail,String id_pegawai,String id_pemohon, String tarikhMula, String tarikhAkhir) throws Exception {
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		String jenis_comment = "2";
		Vector check_notifikasi = null;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String user_id = (String) session.getAttribute("_ekptg_user_id");

			r.clear();
			r.add("ID_FAIL", id_fail);
			r.add("USER_ID", user_agih_id);
			r.add("KETERANGAN", catatan_agihan);
			r.add("ID_MASUK", user_id);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("TARIKH_MULA_PINDA", tarikhMula);
			r.add("TARIKH_AKHIR_PINDA", tarikhAkhir);
			sql = r.getSQLInsert("TBLEDITAGIHAN");
			myLogger.info("INSERT AGIHAN " + sql.toUpperCase());
			stmt.executeUpdate(sql);

			conn.commit();


			setNotificationPegawaiEdit(session, id_fail,user_id, "2",id_pegawai);
			setNotificationPegawaiEdit(session, id_fail,user_id, "2",id_pemohon);
			//setNotificationAdminppk(session, id_fail,user_id,jenis_comment);
			setNotificationKomen(session, id_fail,user_id,jenis_comment);
			setNotificationAgihan(session, id_fail,user_id,jenis_comment);
			
			
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

	

	private void addComment(HttpSession session, String jenis_comment,String id_pegawai,String id_pemohon)
			throws Exception {
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String id_fail_atheader = getParam("id_fail_atheader");

			String maklumbalas = "";
			if (jenis_comment.equals("1")) {
				maklumbalas = getParam("maklumbalas");
			} else if (jenis_comment.equals("2")) {
				maklumbalas = getParam("maklumbalas_teknikal");
			}
			String user_id_login = (String) session
					.getAttribute("_ekptg_user_id");
			String role = (String) session.getAttribute("myrole");
			int siapa_comment = 0;
			if (role.equals("adminppk")) {
				siapa_comment = 1;
			} else {
				siapa_comment = 2;
			}

			r.clear();
			r.add("ID_FAIL", id_fail_atheader);
			r.add("USER_ID", user_id_login);
			r.add("KETERANGAN", maklumbalas);
			/*
			 * -flag- 1-admin 2-developer 3-pengadu
			 */
			r.add("FLAG_SIAPA_COMMENT", siapa_comment);
			r.add("FLAG_PAPARAN_ADMIN", "");
			r.add("FLAG_PAPARAN_DEVELOPER", "");
			r.add("FLAG_PAPARAN_PENGADU", "");
			r.add("FLAG_JENIS_COMMENT", jenis_comment);
			r.add("ID_MASUK", user_id_login);
			r.add("ID_KEMASKINI", user_id_login);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLEDITCOMMENT");
			stmt.executeUpdate(sql);

			conn.commit();
			
			
			
			//setNotificationAdminppk(session, id_fail_atheader,user_id_login,jenis_comment);
			setNotificationPegawaiEdit(session, id_fail_atheader,user_id_login, "2",id_pegawai);
			setNotificationPegawaiEdit(session, id_fail_atheader,user_id_login, "2",id_pemohon);
			setNotificationKomen(session, id_fail_atheader,user_id_login,jenis_comment);
			setNotificationAgihan(session, id_fail_atheader,user_id_login,jenis_comment);
					
		} catch (SQLException se) {
			/*
			 * try { conn.rollback(); } catch (SQLException se2) { throw new
			 * Exception("Rollback error:" + se2.getMessage()); }
			 * se.printStackTrace(); throw new Exception("Ralat Simpan Aduan:" +
			 * se.getMessage());
			 */
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}
	
	private void setNotificationAdminppk(HttpSession session, String id_fail_atheader,
			String user_id_login, String jenis_comment) throws Exception {
		myLogger.info("MASUK SETNOTIFICATION");
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;
		Vector comment_aduan = null;

		try {
			myLogger.info("MASUK SETNOTIFICATION 1");
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			myLogger.info("MASUK SETNOTIFICATION 2");
			//comment_aduan = logic.senarai_comment_aduan_check(id_fail_atheader,"2");			
			myLogger.info("MASUK SETNOTIFICATION 3");

			// untuk admin ppk sahaja
			Vector check_tech = null;
			check_tech = logic.senarai_admin_team(user_negeri_login, role,
					userId,"",id_fail_atheader);
			myLogger.info("check_tech.size() ::::::::"+check_tech.size());
			if (check_tech.size() != 0) {
				for (int i = 0; i < check_tech.size(); i++) {
					Hashtable get_tech = (Hashtable) check_tech.get(i);
					String user_name = (String) get_tech.get("user_name");
					String user_id_terima = (String) get_tech.get("user_id");
					myLogger.info("user_name TECH TEAM :"
							+ user_name.toUpperCase());
					String id_esnotifikasi = "";
					check_notifikasi = logic
							.getListNotifikasi(id_fail_atheader, jenis_comment,
									user_id_terima, "");
					if (check_notifikasi.size() != 0) {
						Hashtable get_notifikasi = (Hashtable) check_notifikasi
								.get(0);
						id_esnotifikasi = (String) get_notifikasi
								.get("id_esnotifikasi");
					}

					if (!user_id_login.equals(user_id_terima)) {
						r.clear();
						if (!id_esnotifikasi.equals("")) {
							r.update("ID_EDITNOTIFIKASI", id_esnotifikasi);
						}
						r.add("FLAG_NOTIFIKASI", jenis_comment);
						r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
						r.add("ID_USER_NOTIFIKASI_TERIMA", user_id_terima);
						r.add("ID_FAIL", id_fail_atheader);
						r.add("FLAG_READ", "NO");
						r.add("ID_MASUK", user_id_login);
						r.add("ID_KEMASKINI", user_id_login);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						if (!id_esnotifikasi.equals("")) {
							sql1 = r.getSQLUpdate("TBLEDITNOTIFIKASI");
						} else {
							sql1 = r.getSQLInsert("TBLEDITNOTIFIKASI");
						}
						myLogger.info("UPDATE/INSERT NOTIFY :"+sql);
						stmt.executeUpdate(sql1);
						conn.commit();

					}
				}
			}

			
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}
	
	
	private void setNotificationPegawaiEdit(HttpSession session, String id_fail_atheader,
			String user_id_login, String jenis_comment,String id_pegawai) throws Exception {
		myLogger.info("MASUK SETNOTIFICATION");
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;
		Vector comment_aduan = null;

		try {
			myLogger.info("MASUK SETNOTIFICATION 1");
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			myLogger.info("MASUK SETNOTIFICATION 2");	
			myLogger.info("MASUK SETNOTIFICATION 3");

		
					String id_esnotifikasi = "";
					check_notifikasi = logic
							.getListNotifikasi(id_fail_atheader, jenis_comment,
									id_pegawai, "");
					if (check_notifikasi.size() != 0) {
						Hashtable get_notifikasi = (Hashtable) check_notifikasi
								.get(0);
						id_esnotifikasi = (String) get_notifikasi
								.get("id_esnotifikasi");
					}

					if (!user_id_login.equals(id_pegawai)) {
						r.clear();
						if (!id_esnotifikasi.equals("")) {
							r.update("ID_EDITNOTIFIKASI", id_esnotifikasi);
						}
						r.add("FLAG_NOTIFIKASI", jenis_comment);
						r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
						r.add("ID_USER_NOTIFIKASI_TERIMA", id_pegawai);
						r.add("ID_FAIL", id_fail_atheader);
						r.add("FLAG_READ", "NO");
						r.add("ID_MASUK", user_id_login);
						r.add("ID_KEMASKINI", user_id_login);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						if (!id_esnotifikasi.equals("")) {
							sql1 = r.getSQLUpdate("TBLEDITNOTIFIKASI");
						} else {
							sql1 = r.getSQLInsert("TBLEDITNOTIFIKASI");
						}
						myLogger.info("UPDATE/INSERT NOTIFY :"+sql1);
						stmt.executeUpdate(sql1);
						conn.commit();

					}
				
			

			
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}


	private void setNotificationKomen(HttpSession session, String id_fail_atheader,
			String user_id_login, String jenis_comment) throws Exception {
		myLogger.info("MASUK SETNOTIFICATION KOMEN");
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;
		Vector comment_aduan = null;
		
		comment_aduan = logic.senarai_comment_aduan_check(id_fail_atheader,"2");
		
		try {
			myLogger.info("MASUK SETNOTIFICATION KOMEN 1");
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			myLogger.info("comment_aduan.size()"+comment_aduan.size());
			
			if(comment_aduan.size()!=0){
				for(int i = 0;i < comment_aduan.size();i++)
				{
					Hashtable get_comment_siapa = (Hashtable) comment_aduan.get(i);
					String role_comment = (String)get_comment_siapa.get("flag_siapa_comment");	
					String siapacomment = (String)get_comment_siapa.get("user_id");
					myLogger.info("user_name TECH TEAM :"+siapacomment.toUpperCase());
					String id_esnotifikasi = "";
					check_notifikasi = logic.getListNotifikasi(id_fail_atheader,jenis_comment,siapacomment,"");
					myLogger.info("check_notifikasi.size() :::::"+check_notifikasi.size());	
					if(check_notifikasi.size()!=0){
							Hashtable get_notifikasi = (Hashtable) check_notifikasi.get(0);
							id_esnotifikasi = (String)get_notifikasi.get("id_esnotifikasi");					
						}
					    myLogger.info("id_esnotifikasi :::::: "+id_esnotifikasi);
						if(!user_id_login.equals(siapacomment))
						{	
						r.clear();
							if(!id_esnotifikasi.equals(""))
							{
								r.update("ID_EDITNOTIFIKASI",id_esnotifikasi);	
							}
						r.add("FLAG_NOTIFIKASI", jenis_comment);
						r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
						r.add("ID_USER_NOTIFIKASI_TERIMA", siapacomment);						
										
						r.add("ID_FAIL", id_fail_atheader);
						r.add("FLAG_READ", "NO");
						r.add("ID_MASUK",user_id_login);
						r.add("ID_KEMASKINI",user_id_login);
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						if(!id_esnotifikasi.equals(""))
						{
							sql1 = r.getSQLUpdate("TBLEDITNOTIFIKASI");
						}
						else
						{
							sql1 = r.getSQLInsert("TBLEDITNOTIFIKASI");	
						}
						stmt.executeUpdate(sql1);
						conn.commit();
						}						
				}										
			    }
			

			
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}
	
	
	private void setNotificationAgihan(HttpSession session, String id_fail_atheader,
			String user_id_login, String jenis_comment) throws Exception {
		myLogger.info("MASUK SETNOTIFICATION KOMEN");
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;
		Vector listAgihan_aduan = null;
	
		listAgihan_aduan = logic.senarai_agihan_aduan(id_fail_atheader);
		
		try {
			myLogger.info("MASUK SETNOTIFICATION KOMEN 1");
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			myLogger.info("comment_aduan.size()"+listAgihan_aduan.size());
			
			if(listAgihan_aduan.size()!=0){
				for(int i = 0;i < listAgihan_aduan.size();i++)
				{
					Hashtable listAgihan_aduan_siapa = (Hashtable) listAgihan_aduan.get(i);
					//String role_comment = (String)get_comment_siapa.get("flag_siapa_comment");	
					String siapacomment = (String)listAgihan_aduan_siapa.get("user_id");
					myLogger.info("user_name TECH TEAM :"+siapacomment.toUpperCase());
					String id_esnotifikasi = "";
					check_notifikasi = logic.getListNotifikasi(id_fail_atheader,jenis_comment,siapacomment,"");
					myLogger.info("check_notifikasi.size() :::::"+check_notifikasi.size());	
					if(check_notifikasi.size()!=0){
							Hashtable get_notifikasi = (Hashtable) check_notifikasi.get(0);
							id_esnotifikasi = (String)get_notifikasi.get("id_esnotifikasi");					
						}
					    myLogger.info("id_esnotifikasi :::::: "+id_esnotifikasi);
						if(!user_id_login.equals(siapacomment))
						{	
						r.clear();
							if(!id_esnotifikasi.equals(""))
							{
								r.update("ID_EDITNOTIFIKASI",id_esnotifikasi);	
							}
						r.add("FLAG_NOTIFIKASI", jenis_comment);
						r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
						r.add("ID_USER_NOTIFIKASI_TERIMA", siapacomment);						
										
						r.add("ID_FAIL", id_fail_atheader);
						r.add("FLAG_READ", "NO");
						r.add("ID_MASUK",user_id_login);
						r.add("ID_KEMASKINI",user_id_login);
						r.add("TARIKH_MASUK",r.unquote("sysdate"));
						r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
						if(!id_esnotifikasi.equals(""))
						{
							sql1 = r.getSQLUpdate("TBLEDITNOTIFIKASI");
						}
						else
						{
							sql1 = r.getSQLInsert("TBLEDITNOTIFIKASI");	
						}
						stmt.executeUpdate(sql1);
						conn.commit();
						}						
				}										
			    }
			

			
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}

	private void updateNotification(HttpSession session, String id_fail,
			String id_user) throws Exception {
		Connection conn = null;
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_FAIL", id_fail);
			r.update("ID_USER_NOTIFIKASI_TERIMA", id_user);
			r.add("ID_USER_NOTIFIKASI_TERIMA", id_user);
			r.add("ID_FAIL", id_fail);
			r.add("FLAG_READ", "YES");
			r.add("ID_KEMASKINI", id_user);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("TBLEDITNOTIFIKASI");
			myLogger.info("SET READ :"+sql1);
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

	@SuppressWarnings("unchecked")
	private void uploadFiles() throws Exception {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			myLogger.info("BUKAN MULTIPART");
		} else {
			myLogger.info("MULTIPART");
		}
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if ((!(item.isFormField())) && (item.getName() != null)
					&& (!("".equals(item.getName())))) {
				saveData(item);
			}
		}
	}

	private void saveData(FileItem item) throws Exception {
		HttpSession session = request.getSession();
		Db db = null;
		try {
			long id_esdokumen = DB.getNextID("TBLESDOKUMEN_SEQ");
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			SQLRenderer r = new SQLRenderer();
			PreparedStatement ps = con
					.prepareStatement("insert into TBLESDOKUMEN "
							+ "(id_esdokumen,id_esaduan,nama_Fail,jenis_Mime,content,tajuk,keterangan,id_masuk,id_kemaskini,tarikh_masuk,tarikh_kemaskini) "
							+ "values(?,?,?,?,?,?,?,?,?,"
							+ r.unquote("sysdate") + "," + r.unquote("sysdate")
							+ ")");
			ps.setLong(1, id_esdokumen);
			ps.setString(2, getParam("id_esaduan"));
			ps.setString(3, item.getName());
			ps.setString(4, item.getContentType());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, getParam("tajuk"));
			ps.setString(7, getParam("keterangan"));
			ps.setString(8, (String) session.getAttribute("_ekptg_user_id"));
			ps.setString(9, (String) session.getAttribute("_ekptg_user_id"));
			ps.executeUpdate();
			con.commit();
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void updateFiles(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_esaduan", getParam("id_esaduan"));
		h.put("id_esdokumen", getParam("id_esdokumen"));
		h.put("tajuk", getParam("tajuk"));
		h.put("keterangan", getParam("keterangan"));
		logic.updateFiles(h);
	}

	public static synchronized int getSeqNoAduanOnline(String tahun,
			String id_negeri, String kod_modul) throws DbException {
		return getSeqNoAduan(tahun, id_negeri, kod_modul);
	}

	public static synchronized int getSeqNoAduan(String tahun,
			String id_negeri, String kod_modul) throws DbException {

		Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQESNOADUAN WHERE ");
			sb.append(" TAHUN = '" + tahun + "'");
			sb.append(" AND ID_NEGERI = '" + id_negeri + "'");
			sb.append(" AND KOD_MODUL = '" + kod_modul + "'");

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;

			myLogger.info("found :" + found);

			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseSeqAduan(tahun, id_negeri, kod_modul);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNewAduan(tahun, id_negeri, kod_modul);
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}

		return seqno;
	}

	public static void increaseSeqAduan(String tahun, String id_negeri,
			String kod_modul) throws DbException {

		Db db = null;

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQESNOADUAN  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append(" TAHUN = '" + tahun + "'");
		sb.append(" AND id_negeri = '" + id_negeri + "'");
		sb.append(" AND kod_modul = '" + kod_modul + "'");

		try {
			db = new Db();
			try {
				db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {
				x.printStackTrace();
			}
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static void addNewAduan(String tahun, String id_negeri,
			String kod_modul) throws DbException {

		Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQESNOADUAN (TAHUN,ID_NEGERI,KOD_MODUL,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'" + tahun + "',");
		sb.append("'" + id_negeri + "',");
		sb.append("'" + kod_modul + "'");
		sb.append(",1)"); // initial value

		try {
			db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}
	}

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

	public void setupPageCari(HttpSession session, String action, Vector list) {

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

	public void hantarEmel(ArrayList id_penerima_array, HttpSession session,
			String jenis_emel, String emel_hantar, String emel_terima,
			String subjek, String content, String id_negeri, String id_esaduan,
			String user_id_penerima, String user_id_hantar, String commentlama)
			throws Exception {
		myLogger.info("id_esaduan emel : " + id_esaduan);
		/*
		 * jenis_emel = 1 = maklumbalas aduan jenis_emel = 2 = maklumbalas
		 * teknikal jenis_emel = 3 = agihan tugas jenis_emel = 4 = perubahan
		 * status aduan n aduan baru
		 */

		Vector list_aduan = null;
		Hashtable get_aduan = null;
		Vector list_users_hantar = null;

		Hashtable get_user_hantar = null;
		// maklumat Aduan
		if (!id_esaduan.equals("")) {
			list_aduan = logic.getAduan("", id_esaduan, "", "", "", "", "", "",
					"", "", "", "", "", "");
			if (list_aduan.size() != 0) {
				get_aduan = (Hashtable) list_aduan.get(0);
				/*
				 * context.put("user_id",(String)get_aduan.get("user_id"));
				 */
			}
		}

		if (!user_id_hantar.equals("")) {
			// Maklumat User Penerima
			list_users_hantar = logic.getListUsers(user_id_hantar, "", "", "",
					"", "");
			if (list_users_hantar.size() != 0) {
				get_user_hantar = (Hashtable) list_users_hantar.get(0);
			}
		}

		EmailProperty pro = EmailProperty.getInstance();
		String EMAIL_HOST = pro.getHost();

		EkptgEmailSender email = EkptgEmailSender.getInstance();

		/*
		 * if(EMAIL_HOST.equals("mail.hla-group.com")){ email.FROM =
		 * "flms.etapp@gmail.com"; }else{ email.FROM =
		 * "etapp_webmaster@ekptg.gov.my"; }
		 */

		email.FROM = "flms.etapp@gmail.com";

		myLogger.info("LOG ADUAN EMEL :" + (String) get_aduan.get("log_aduan"));

		if (jenis_emel.equals("1")) {
			email.SUBJECT = "FLMS: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Komen Log)";
		} else if (jenis_emel.equals("2")) {
			email.SUBJECT = "FLMS: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Komen Teknikal)";
		} else if (jenis_emel.equals("3")) {
			email.SUBJECT = "FLMS: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Agihan Tugas)";
		} else if (jenis_emel.equals("4")) {
			email.SUBJECT = "FLMS: " + (String) get_aduan.get("log_aduan")
					+ " (Status Log : "
					+ (String) get_aduan.get("nama_status_init") + ")";
		}
		// comment maklumbalas aduan

		String emel_html_intro = "";
		String emel_html_msg = "";
		String siapa_agih = "";
		Vector listAgihan_aduan = null;

		if (jenis_emel.equals("1")) {
			emel_html_intro += " "
					+ "<table width='100%' border='0'><tr><td width='5%' >&nbsp;</td><td  width='95%' >"
					+ "Anda telah menerima komen daripada <b>"
					+ (String) get_user_hantar.get("user_name_init")
					+ "</b> berkenaan log dibawah."
					+ "</td></tr></table><br><br>" + "";

			if (!commentlama.equals("")) {
				emel_html_msg += " <table width='100%' border='0'>"
						+ " <tr>"
						+ " <td width='5%'>&nbsp;</td>"
						+ " <td width='20%' valign='top'><b>Komen-Komen Sebelum</b></td>"
						+ " <td width='1%' valign='top'>:</td>"
						+ " <td width='50%' valign='top'>" + commentlama
						+ "</td>" + " <td width='24%'></td>"
						+ " </tr></table><br>";
			}

			emel_html_msg += " <table width='100%' border='0'>" + " <tr>"
					+ " <td width='5%'>&nbsp;</td>"
					+ " <td width='20%' valign='top'><b>Komen Terbaru</b></td>"
					+ " <td width='1%' valign='top'>:</td>"
					+ " <td width='50%' valign='top'><i>"
					+ (String) get_user_hantar.get("user_name_init") + " : \""
					+ content + "</i>\"</td>" + " <td width='24%'></td>"
					+ " </tr></table><br><br>";

		}

		if (jenis_emel.equals("2")) {
			emel_html_intro += " "
					+ "<table width='100%' border='0'><tr><td width='5%' >&nbsp;</td><td  width='95%' >"
					+ "Anda telah menerima komen teknikal daripada <b>"
					+ (String) get_user_hantar.get("user_name_init")
					+ "</b> berkenaan log dibawah."
					+ "</td></tr></table><br><br>" + "";

			if (!commentlama.equals("")) {
				emel_html_msg += " <table width='100%' border='0'>"
						+ " <tr>"
						+ " <td width='5%'>&nbsp;</td>"
						+ " <td width='20%' valign='top'><b>Komen-Komen Sebelum</b></td>"
						+ " <td width='1%' valign='top'>:</td>"
						+ " <td width='50%' valign='top'>" + commentlama
						+ "</td>" + " <td width='24%'></td>"
						+ " </tr></table><br>";
			}

			emel_html_msg += " <table width='100%' border='0'>" + " <tr>"
					+ " <td width='5%'>&nbsp;</td>"
					+ " <td width='20%' valign='top'><b>Komen Terbaru</b></td>"
					+ " <td width='1%' valign='top'>:</td>"
					+ " <td width='50%' valign='top'><i>"
					+ (String) get_user_hantar.get("user_name_init") + " : \""
					+ content + "</i>\"</td>" + " <td width='24%'></td>"
					+ " </tr></table><br><br>";

			listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan);
			if (listAgihan_aduan.size() != 0) {

				siapa_agih += "";
				siapa_agih += " <table width='100%' border='0'>"
						+ " <tr>"
						+ " <td width='5%'>&nbsp;</td>"
						+ " <td width='20%' valign='top'><b>Diagihkan kepada</b></td>"
						+ " <td width='1%' valign='top'>:</td>"
						+ " <td width='64%' valign='top'>";

				for (int i = 0; i < listAgihan_aduan.size(); i++) {
					Hashtable get_user_agih = (Hashtable) listAgihan_aduan
							.get(i);

					siapa_agih += "<table width='100%' border='0'>";
					siapa_agih += "<tr>";
					siapa_agih += "<td width='30%' valign='top'>"
							+ get_user_agih.get("user_name").toString()
							+ "</td>";
					if (!get_user_agih.get("catatan_agihan").toString()
							.equals("")) {
						siapa_agih += "<td width='13%' valign='top'><b>Catatan :</b></td>";
					} else {
						siapa_agih += "<td width='13%' valign='top'></td>";
					}
					siapa_agih += "<td width='57%' valign='top'>"
							+ get_user_agih.get("catatan_agihan").toString()
							+ "</td>";
					siapa_agih += "</tr>";

					siapa_agih += "</table>";
				}

				siapa_agih += "</td>" + " <td width='10%'></td>"
						+ " </tr></table><br><br>";
			}

		}

		if (jenis_emel.equals("3")) {
			emel_html_intro += " "
					+ "<table width='100%' border='0'><tr><td width='5%' >&nbsp;</td><td  width='95%' >"
					+ "Untuk makluman, agihan tugas telah dilakukan oleh <b>"
					+ (String) get_user_hantar.get("user_name_init")
					+ "</b> untuk menyelesaikan aduan dibawah."
					+ "</td></tr></table><br><br>" + "";
			emel_html_msg += " <table width='100%' border='0'>"
					+ " <tr>"
					+ " <td width='5%' valign='top'>&nbsp;</td>"
					+ " <td width='20%' valign='top'><b>Diagihkan kepada</b></td>"
					+ " <td width='1%' valign='top'>:</td>"
					+ " <td width='64%' valign='top'>" + content + "</td>"
					+ " <td width='10%'></td>" + " </tr></table><br><br>";

		}

		if (jenis_emel.equals("4")) {
			String check_status = (String) get_aduan.get("id_status");
			String check_nama_status = (String) get_aduan
					.get("nama_status_init");
			String check_content = content;
			String check_id_sumber = (String) get_aduan.get("id_sumberaduan");
			String check_nama_sumber = (String) get_aduan
					.get("nama_sumber_init");
			String ayat_sedap = "";

			myLogger.info("check_status:" + check_status);
			myLogger.info("check_nama_status :" + check_nama_status);
			myLogger.info("check_content :" + check_content);
			myLogger.info("check_id_sumber :" + check_id_sumber);
			myLogger.info("check_nama_sumber :" + check_nama_sumber);

			if (check_content.equals("permohonan_baru_kutipan_data")) {
				if (!check_id_sumber.equals("")
						&& !check_id_sumber.equals("16101")) {
					ayat_sedap = "Aduan ini baru didaftarkan melalui modul kutipan data FLMS, segala maklumat yang dipaparkan"
							+ " adalah bedasar dari aduan oleh pihak pengadu melalui sumber <b>"
							+ check_nama_sumber + "</b>. ";
				} else {
					ayat_sedap = "Aduan ini baru didaftarkan melalui modul kutipan data FLMS. ";
				}

				if (check_status.equals("16122")) {
					ayat_sedap += "Aduan ini masih lagi didalam semakan pihak kami. "
							+ "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
				}
				if (check_status.equals("16123")) {
					ayat_sedap += "Aduan ini telah diselesaikan. Terus tekun menggunakan eTaPP dan jika menghadapi masalah sila hubungi kami.";

				}

				if (check_status.equals("16121")) {
					ayat_sedap += "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";

				}

			}

			if (check_content.equals("update_status_permohonan")) {
				if (!role.equals("admin_es") && !role.equals("developer_es")) {
					if (check_status.equals("16121")) {
						ayat_sedap += "Log ini baru didaftarkan melalui eTaPP. Sila ambil tindakan. ";

					}
				} else {
					if (check_status.equals("16122")) {
						ayat_sedap += "Log ini telah kami terima dan masih didalam semakan. "
								+ "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
					}
					if (check_status.equals("16123")) {
						ayat_sedap += "Aduan ini telah diselesaikan. Terus tekun menggunakan eTaPP dan jika menghadapi masalah sila hubungi kami.";

					}
				}
			}

			emel_html_intro += " "
					+ "<table width='100%' border='0'><tr><td width='5%' >&nbsp;</td><td  width='95%' >"
					+ ayat_sedap + "</td></tr></table>" + "<br><br>";

			emel_html_msg += "";
		}

		email.MESSAGE = ""
				+ setHeader(jenis_emel, (String) get_aduan.get("id_negeri"))
				+ ""
				+ emel_html_intro
				+ ""
				+ setMaklumatAduan((String) get_aduan.get("log_aduan"),
						jenis_emel, id_negeri,
						(String) get_aduan.get("user_name_init"),
						(String) get_aduan.get("nama_pejabat_init"),
						(String) get_aduan.get("tarikh_aduan_hantar"),
						(String) get_aduan.get("jenis_module_init"),
						(String) get_aduan.get("aduan"),
						(String) get_aduan.get("nama_status_init"))
				+ siapa_agih + emel_html_msg
				+ setFooter(jenis_emel, (String) get_aduan.get("id_negeri"));

		if (id_penerima_array.size() > 0) {
			String inputLine = "";
			email.MULTIPLE_RECIEPIENT = new String[id_penerima_array.size()];
			for (int i = 0; i < id_penerima_array.size(); i++) {
				inputLine = id_penerima_array.get(i).toString();
				myLogger.info("CHECK SIZE" + id_penerima_array.size()
						+ " ID_USER_TERIMA :"
						+ id_penerima_array.get(i).toString());
				Hashtable emel = logic.get_user_emel(id_penerima_array.get(i)
						.toString());
				myLogger.info("DISPLAY EMEL PENERIMA :"
						+ emel.get("emel").toString());
				if (!emel.get("emel").toString().equals("")
						&& !emel.get("emel").toString().equals(null)) {
					email.MULTIPLE_RECIEPIENT[i] = emel.get("emel").toString();
				} else {
					email.MULTIPLE_RECIEPIENT[i] = "flms.etapp@gmail.com";
				}
			}
			if (jenis_emel.equals("4")) {
				email.TO_CC = new String[1];
				email.TO_CC[0] = "flms.etapp@gmail.com";
				// kat sini letak emel etappsupport yg hendak di CC
			}
			email.sendEmail();
		}

		/*
		 * email.MULTIPLE_RECIEPIENT = new String[1];
		 * email.MULTIPLE_RECIEPIENT[0] = (String)get_user_terima.get("emel");
		 * email.TO_CC = new String[1]; email.TO_CC[0] = "";
		 */

	}

	public static String setHeader(String jenis_emel, String id_negeri) {

		StringBuffer bff = new StringBuffer();
		bff.append("<table width='100%' border='0'>");
		bff.append("<tr>");
		bff.append("<td width='5%' >&nbsp;</td>");
		bff.append("<td  width='95%'>Tuan/Puan</td> ");
		bff.append("</tr>");
		bff.append("</table><br>");
		return bff.toString();
	}

	public static String setFooter(String jenis_emel, String id_negeri)
			throws Exception {
		String url_negeri = "";
		EtappSupportAduanData logic = new EtappSupportAduanData();
		Hashtable url = logic.get_url(id_negeri);
		url_negeri = url.get("url").toString();
		// emel.get("emel").toString()
		// Hashtable user_name =
		// logic1.get_user_emel(id_penerima_agihan_array.get(i).toString());

		StringBuffer bff = new StringBuffer();

		bff.append("" + "<br><br><table width='100%' border='0'>");
		bff.append("<tr>");
		bff.append("<td width='5%' >&nbsp;</td>");
		bff.append("<td  width='95%'>Sila <i>login</i> masuk ke <a href='"
				+ url_negeri
				+ "'>"
				+ "<font color='BLUE'>"
				+ url_negeri
				+ "</font></a> untuk semakan berkenaan dengan aduan ini.<br></td> ");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td>&nbsp;</td>");
		bff.append("<td>Sekian, terima kasih.<br></td> ");
		bff.append("</tr>");

		bff.append("<tr>");
		bff.append("<td  >&nbsp;</td>");
		bff.append("<td  >Nota : Emel ini dijana oleh Sistem eTaPP dan tidak perlu dibalas.</td>");
		bff.append("</tr>");
		bff.append("</table>");

		return bff.toString();
	}

	public static String setMaklumatAduan(String log, String jenis_emel,
			String id_negeri, String nama_pengadu, String nama_pejabat,
			String aduan_hantar, String nama_modul, String aduan,
			String nama_status) {

		StringBuffer bff = new StringBuffer();
		bff.append("<table width='100%' border='0'>");
		bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td  colspan='4'><b><u>MAKLUMAT LOG</u></b><br><br></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td width='5%'>&nbsp;</td>");
		bff.append("<td width='20%' valign='top' ><b>Nama Pengadu</b></td>");
		bff.append("<td width='1%'  valign='top'>:</td>");
		bff.append("<td width='64%'  valign='top'>" + nama_pengadu + "</td>");
		bff.append("<td width='10%'></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td valign='top'><b>Log</b></td>");
		bff.append("<td valign='top' >:</td>");
		bff.append("<td valign='top' >" + log + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td valign='top'><b>Pejabat</b></td>");
		bff.append("<td valign='top' >:</td>");
		bff.append("<td valign='top' >" + nama_pejabat + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td valign='top' ><b>Waktu Log Dihantar</b></td>");
		bff.append("<td valign='top' >:</td>");
		bff.append("<td valign='top' >" + aduan_hantar + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td valign='top' ><b>Modul</b></td>");
		bff.append("<td valign='top' >:</td>");
		bff.append("<td valign='top' >" + nama_modul + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td valign='top'><b>Keterangan Log</b></td>");
		bff.append("<td valign='top'>:</td>");
		bff.append("<td valign='top'>" + aduan + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		bff.append("<tr>");
		bff.append("<td valign='top' >&nbsp;</td>");
		bff.append("<td valign='top' ><b>Status Log</b></td>");
		bff.append("<td valign='top' >:</td>");
		bff.append("<td valign='top' >" + nama_status + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		bff.append("</table><br><br>");
		return bff.toString();

	}

	public Hashtable getUsername(String user_id) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT USER_NAME FROM USERS WHERE USER_ID = '" + user_id
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME", "");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME"));
				}
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void setFlagKebenaran(String id_fail,HttpSession session, String id_permohonan,
			String user_id_kebenaran_edit, String flag_kebenaran_edit,
			String catatan_kebenaran_edit, String tarikhMula, String tarikhAkhir) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql1 = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PERMOHONAN", id_permohonan);
			if (flag_kebenaran_edit.equals("")) {
				r.add("user_id_kebenaran_edit", "");
				r.add("catatan_kebenaran_edit", "");
			} else {
				r.add("user_id_kebenaran_edit", user_id_kebenaran_edit);
				r.add("catatan_kebenaran_edit", catatan_kebenaran_edit);
				r.add("tarikh_mula_pinda", tarikhMula);
				r.add("tarikh_akhir_pinda", tarikhAkhir);
			}
			r.add("flag_kebenaran_edit", flag_kebenaran_edit);
			sql1 = r.getSQLUpdate("TBLPPKPERMOHONAN");
			myLogger.info("UPDATE FLAG KEBENARAN:" + sql1);
			stmt.executeUpdate(sql1);			
			conn.commit();
			
			myLogger.info("getFLAG KEBENARAN :"+flag_kebenaran_edit);
			if(!flag_kebenaran_edit.equals("1"))
			{
			logic.deleteAgihan(id_fail);
			}
			
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
	
	private void setSimpanSelesaiPinda(String id_fail,HttpSession session, String id_permohonan,
			String flag_selesai_pinda) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql1 = "";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PERMOHONAN", id_permohonan);
			
			//r.add("TARIKH_MULA_PINDA", tarikh_selesai);
			r.add("TARIKH_SELESAI_PINDA", r.unquote("sysdate"));
			r.add("FLAG_PINDA_SELESAI", flag_selesai_pinda);
			
			sql1 = r.getSQLUpdate("TBLPPKPERMOHONAN");
			myLogger.info("UPDATE TARIKH dan FLAG SELESAI" + sql1);
			stmt.executeUpdate(sql1);			
			conn.commit();
			
			
			
			//setNotificationPegawaiEdit(session, id_fail,user_id_login, "2",id_pegawai);
			
			
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
	
	private void setSimpanPilihanPegawai(String id_fail,HttpSession session, String id_permohonan,
			String user_id_kebenaran_edit, String flag_kebenaran_edit,
			String catatan_kebenaran_edit,String id_pegawai, String tujuan_pindaan, String tarikhMula, String tarikhAkhir) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql1 = "";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_PERMOHONAN", id_permohonan);
			if (id_pegawai.equals("")) {
				r.add("ID_PEGAWAI_MOHON_EDIT", "");				
			} else {
				r.add("ID_PEGAWAI_MOHON_EDIT", id_pegawai);
			}
			
			if (user_id_login.equals("")) {
				r.add("ID_PEMOHON_MOHON_EDIT", "");				
			} else {
				r.add("ID_PEMOHON_MOHON_EDIT", user_id_login);
			}
			
			r.add("TUJUAN_PINDAAN", tujuan_pindaan);
			r.add("TARIKH_MULA_PINDA", tarikhMula);
			r.add("TARIKH_AKHIR_PINDA", tarikhAkhir);
			sql1 = r.getSQLUpdate("TBLPPKPERMOHONAN");
			myLogger.info("UPDATE PILIHAN PEGAWAI" + sql1);
			stmt.executeUpdate(sql1);			
			conn.commit();
			
			
			
			setNotificationPegawaiEdit(session, id_fail,user_id_login, "2",id_pegawai);
			
			
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
		this.context.put("flag_jenis_vm", "utiliti_ajax");
	}

	private void headerppk_baru_default() {
		Hashtable headerppk = null;
		this.context.put("headerppk", "");
		this.context.put("list_sub_header", "");
		this.context.put("flag_jenis_vm", "utiliti_ajax");
	}
	
	//aishah 27/02/2017
	 public void hantarEmel(String content,String subjek,String nofail,
			 String tujuan, String pegawaiAgihan, String idpegawaiMemohon,String namaPegawaiMemohon, String tarikMohon, String namaSiMati) throws Exception {
		 
		 
		
		 
			Vector checkEmailPegawaiAgihan = new Vector();
			checkEmailPegawaiAgihan.clear();
			System.out.println("id pegawaiAgihan :::"+pegawaiAgihan);

			// check email (pengarah)
			checkEmailPegawaiAgihan = logic.checkEmail(pegawaiAgihan);
			String emelPegawaiPilih = "";
			if (checkEmailPegawaiAgihan.size() != 0) {
				Hashtable ceP = (Hashtable) checkEmailPegawaiAgihan.get(0);
				emelPegawaiPilih = (String) ceP.get("EMEL");
			}

			System.out.println("emel ::"+emelPegawaiPilih);
			
			EmailProperty pro = EmailProperty.getInstance();
			EmailSender email = EmailSender.getInstance();
			email.FROM = pro.getFrom();
			
		email.SUBJECT = subjek;
		email.MESSAGE = "<html><table>" +
				"<tr><td colspan=3> EMAIL INI DIJANA BERTUJUAN UNTUK PENGUJIAN SISTEM MYETTAP. SILA ABAIKAN EMAIL INI. TERIMA KASIH. </td></tr>" +
				"<tr><td>Tujuan</td><td>:</td><td>"+tujuan+"</td></tr>" +
				"<tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
				"<tr><td>Nama Si Mati</td><td>:</td><td>"+namaSiMati+"</td></tr>" +
				"<tr><td>Nama Pegawai Memohon Pengemaskinian Fail </td><td>:</td><td>"+namaPegawaiMemohon+"</td></tr>" +
				"<tr><td>Tarikh Pegawai Mohon</td><td>:</td><td>"+tarikMohon+"</td></tr>" +			
				"<tr><td colspan=3>&nbsp;</td></tr>" +
				"<tr><td colspan=3><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
				"</table></html>" ;
		email.RECIEPIENT = emelPegawaiPilih;
		
		myLogger.info(" ---------- email :"+email);	
		//email.MULTIPLE_RECIEPIENT[0] = emelPegawaiPilih;	
		//email.TO_CC = new String[1];		
		//email.TO_CC[0] = "cikyatiey@gmail.com";
		email.sendEmail();
		
	 }

}
