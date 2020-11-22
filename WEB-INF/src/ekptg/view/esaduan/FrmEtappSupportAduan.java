package ekptg.view.esaduan;

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

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.DB;
import ekptg.helpers.Paging;
import ekptg.model.esaduan.EtappSupportAduanData;
//import ekptg.view.esaduan.EkptgEmailSender;
//import ekptg.model.utils.IUserPegawai;

public class FrmEtappSupportAduan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2632941808313804539L;
	static Logger myLogger = Logger.getLogger(FrmEtappSupportAduan.class);
	private final String PATH = "app/esaduan/";
	// private IUserPegawai iUser = null;
	private String vm = PATH + "index.jsp";
	String userId = null;
	String role = null;
	String status = null;
	String user_negeri_login = null;
	String user_login = null;

	HttpSession session = null;
	String action = null;
	EtappSupportAduanData logic = new EtappSupportAduanData();
	
	public String doTemplate2() throws Exception {

		session = request.getSession();
		userId = (String) session.getAttribute("_ekptg_user_id");
		context.put("pengguna_aktif", userId);
		user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		context.put("id_negeri_user", user_negeri_login);
		Hashtable id_seksyen_user = logic.user_seksyen(userId);
		context.put("id_seksyen_user", id_seksyen_user.get("id_seksyen_user")
				.toString());
		// myLogger.info("id_seksyen_user :"+id_seksyen_user);
		role = (String) session.getAttribute("myrole");
		String command = getParam("command");
		this.context.put("command", command);
		
		myLogger.info(">>>>>>>>>> user name :: "+getParam("user_name"));
		if(!command.equals(""))
		{
			context.put("log_aduan_cari", getParam("log_aduan_cari"));
			context.put("user_name", getParam("user_name"));
			context.put("no_fail_cari", getParam("no_fail_cari"));
			context.put("tarikh_aduan_hantar_date_cari",
					getParam("tarikh_aduan_hantar_date_cari"));
			context.put("tarikh_aduan_hantar_date_akhir",
					getParam("tarikh_aduan_hantar_date_akhir"));
			context.put("id_statusesaduan_cari",
					getParam("id_statusesaduan_cari"));
			context.put("id_jenismodulesaduan_cari",
					getParam("id_jenismodulesaduan_cari"));
		}
		else
		{
			context.put("log_aduan_cari", "");
			context.put("user_name", "");
			context.put("no_fail_cari", "");
			context.put("tarikh_aduan_hantar_date_cari","");
			context.put("tarikh_aduan_hantar_date_akhir","");
			context.put("id_statusesaduan_cari","");
			context.put("id_jenismodulesaduan_cari","");
		}
		
		String mode = getParam("mode");
		action = getParam("action");
		String flag_daftar = "";
		
		user_login = (String) session.getAttribute("_portal_login");
		context.put("user_login", user_login);
		//System.out.println(" user_login ::::::: " + user_login);

		//System.out.println(" role ::::::: " + role);

		String bolehsimpan = "";
		String readmode = "";
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			bolehsimpan = "yes";
		}
		// myLogger.info("COMMAND :"+command);
		// myLogger.info("ROLE :"+role);
		context.put("role", role);

		/*
		 * context.put("showEmelAlert", "yes"); Vector checkEmail = null;
		 * checkEmail = logic.checkEmail(userId); if(checkEmail.size()!=0){
		 * context.put("showEmelAlert", "no"); }else{
		 * context.put("showEmelAlert", "yes"); }
		 */

		if (!user_login.equals("620428065305")) {
			context.put("open_maklumat_teknikal", "yes");
			} else {
				context.put("open_maklumat_teknikal", "no");
			}

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
		Vector list_pejabat = null;
		context.put("list_pejabat", "");

		Vector list_statusaduan = null;
		Vector list_laporanModul = null;
		Vector list_laporanNegeri = null;
		// penambahan pla
		// Vector list_pejabat = null;
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
		//context.put("view_skrin", "");
		context.put("id_status", "");
		// penambahan pla
		context.put("list_pejabat", "");
		// flag_popup_alert_success
		// 1-berjaya simpan maklumat aduan
		// 2-berjaya simpan maklumat agihan
		context.put("flag_buka_upload", getParam("flag_buka_upload"));
		context.put("flag_buka_maklumbalasaduan",
				getParam("flag_buka_maklumbalasaduan"));
		context.put("flag_buka_maklumbalasteknikal",
				getParam("flag_buka_maklumbalasteknikal"));

		myLogger.info("command :: " + command);
		if (command.equals("daftarBaru")) {
			Db db = null;
			try {
				db = new Db();
				defaultAduan();
				context.put("thpKesukaran", thpKesukaran(""));
				context.put("txtDari", "");
				context.put("txtHingga", "");
				this.context.put("mode", mode);
				defaultList("no", db);
				context.put("open_maklumat_aduan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					// context.put("open_aduanlist", "no");
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}
				// wani
				/*
				 * else{ context.put("open_aduanlist", "no"); }
				 */
			} finally {
				if (db != null)
					db.close();
			}
		} else if (command.equals("pilihUser")) {
			vm = PATH + "index.jsp";
			Db db = null;
			try {
				db = new Db();
				String id_esaduan = getParam("id_esaduan");
				String user_id = getParam("user_id");

				return_after_load();

				if (!user_id.equals("")) {
					list_users = logic.getListUsers(user_id, "", "", "", "",
							"", db);
					if (list_users.size() != 0) {
						Hashtable get_user = (Hashtable) list_users.get(0);
						context.put("user_id", (String) get_user.get("user_id"));
						context.put("nama_pengadu",
								(String) get_user.get("user_name"));
						context.put("no_tel", (String) get_user.get("user_id"));
						context.put("seksyen",
								(String) get_user.get("nama_seksyen"));
						context.put("pejabat",
								(String) get_user.get("nama_pejabat"));
						context.put("negeri",
								(String) get_user.get("nama_negeri"));
						context.put("daerah",
								(String) get_user.get("nama_daerah"));
						context.put("emel", (String) get_user.get("emel"));
						context.put("nama_jawatan",
								(String) get_user.get("nama_jawatan"));
						context.put("id_seksyen_user",
								(String) get_user.get("id_seksyen"));
						context.put("id_negeri_pengadu",
								(String) get_user.get("id_negeri_pengadu"));
						context.put("id_pejabat_pengadu",
								(String) get_user.get("id_pejabat_pengadu"));
						context.put("id_seksyen",
								(String) get_user.get("id_seksyen"));
						context.put("nama_kementerian",
								(String) get_user.get("nama_kementerian"));
						context.put("nama_agensi",
								(String) get_user.get("nama_agensi"));
					}
				}

				defaultList("", db);

				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}

				context.put("open_maklumat_aduan", "yes");

				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					context.put("open_upload", "yes");
					context.put("open_maklumbalas", "yes");
					context.put("open_agihan", "yes");
					context.put("open_agihan", "yes");
				}

				if (role.equals("admin_es") || role.equals("developer_es")) {
					
					if (!user_login.equals("620428065305")) {
					context.put("open_maklumat_teknikal", "yes");
					} else {
						context.put("open_maklumat_teknikal", "no");
					}
					
				}

			} finally {
				if (db != null)
					db.close();
			}

		} else if (command.equals("simpanAduan")
				|| command.equals("simpanAduan_hantar")) {
			// return_after_load();

			Db db = null;
			try {
				db = new Db();
				addAduan(session, db);
				// daftarAgih();
				defaultList("", db);
				String id_esaduan = getParam("id_esaduan");
				// razman
				// comment dlu
				// emailKePengadu(logic.getAduan("",id_esaduan,"","","","","","","","","","","","","",db),getParam("tarikh_selesai"),getParam("txtDari"),getParam("txtHingga"),userId,db);

				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");

				if ((!role.equals("admin_es") && !role.equals("developer_es"))) {
					context.put("setDefaultUser", "yes");
				}
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}
				if (command.equals("simpanAduan")) {
					context.put("flag_popup_alert_success", "1");
				}
				if (command.equals("simpanAduan_hantar")) {
					context.put("flag_popup_alert_success", "3");
				}

			} finally {
				if (db != null)
					db.close();
			}

		}

		else if (command.equals("paparAduan")) {

			Db db = null;
			try {
				db = new Db();
				paparAduan(db);
				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}
			} finally {
				if (db != null)
					db.close();
			}
		} else if (command.equals("bukaUpload")) {
			Db db = null;
			try {
				db = new Db();

				paparAduan(db);
				return_after_load();
				String flag_buka_upload = getParam("flag_buka_upload");
				myLogger.info("flag_buka_upload :" + flag_buka_upload);
				if (flag_buka_upload.equals("yes")) {
					context.put("flag_buka_upload", "no");
				} else {
					context.put("flag_buka_upload", "yes");
				}
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				context.put("open_maklumat_aduan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		}

		else if (command.equals("bukaMaklumbalasAduan")) {
			Db db = null;
			try {
				db = new Db();

				paparAduan(db);
				return_after_load();
				String flag_buka_maklumbalasaduan = getParam("flag_buka_maklumbalasaduan");
				myLogger.info("flag_buka_maklumbalasaduan :"
						+ flag_buka_maklumbalasaduan);
				if (flag_buka_maklumbalasaduan.equals("yes")) {
					context.put("flag_buka_maklumbalasaduan", "no");
				} else {
					context.put("flag_buka_maklumbalasaduan", "yes");
				}
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				context.put("open_maklumat_aduan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		}

		else if (command.equals("bukaMaklumbalasTeknikal")) {
			Db db = null;
			try {
				db = new Db();

				paparAduan(db);
				return_after_load();
				String flag_buka_maklumbalasteknikal = getParam("flag_buka_maklumbalasteknikal");
				myLogger.info("flag_buka_maklumbalasteknikal :"
						+ flag_buka_maklumbalasteknikal);
				if (flag_buka_maklumbalasteknikal.equals("yes")) {
					context.put("flag_buka_maklumbalasteknikal", "no");
				} else {
					context.put("flag_buka_maklumbalasteknikal", "yes");
				}
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				context.put("open_maklumat_aduan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		}

		// skrin agihan
		else if (command.equals("daftarAgih")) {
			// selectagih = user_id
			// addAduan(session)s;

			Db db = null;
			try {
				db = new Db();
				daftarAgih(db);
				defaultList("", db);
				String id_esaduan = getParam("id_esaduan");
				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}

				paparAduan(db);

				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

				context.put("flag_popup_alert_success", "2");
			} finally {
				if (db != null)
					db.close();
			}
		}

		// skrin maklumbalas technical
		else if (command.equals("simpanMaklumbalasTeknikal")) {
			Db db = null;
			try {
				db = new Db();
				addComment(session, "2", db);
				defaultList("", db);
				String id_esaduan = getParam("id_esaduan");
				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}
				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}
			} finally {
				if (db != null)
					db.close();
			}
		}
		// skrin maklumbalas pengadu
		else if (command.equals("simpanMaklumbalas")) {
			Db db = null;
			try {
				db = new Db();
				addComment(session, "1", db);
				defaultList("", db);
				String id_esaduan = getParam("id_esaduan");
				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}
				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		}

		else if (command.equals("deleteComment")) {
			Db db = null;
			try {
				db = new Db();

				String[] id_escomment = this.request
						.getParameterValues("id_escomment");
				if (id_escomment != null) {
					for (int i = 0; i < id_escomment.length; i++) {
						myLogger.info("id_escomment :" + id_escomment);
						logic.deleteComment(session, id_escomment[i], db);

					}
				}

				defaultList("", db);
				String id_esaduan = getParam("id_esaduan");
				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}
				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}

		}
		// skrin dokumen
		else if (command.equals("simpanDokumen")) {

			Db db = null;
			try {
				db = new Db();

				// addAduan(session);
				String id_esaduan = getParam("id_esaduan");
				context.put("id_esaduan", id_esaduan);
				String id_esdokumen = getParam("id_esdokumen");
				String flag_buka_upload = getParam("flag_buka_upload");
				myLogger.info("flag_buka_upload :" + flag_buka_upload);

				context.put("flag_buka_upload", "yes");

				uploadFiles(db);
				context.put("id_esaduan", id_esaduan);

				defaultList("", db);

				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}
				paparAduan(db);

				// return_after_load();

				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("flag_simpan_doc", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		} else if (command.equals("hapusDokumen")) {
			// addAduan(session);

			Db db = null;
			try {
				db = new Db();

				String id_esaduan = getParam("id_esaduan");
				context.put("id_esaduan", id_esaduan);
				String id_esdokumen = getParam("id_esdokumen");

				String[] ids1 = this.request.getParameterValues("ids1");
				if (ids1 != null) {
					for (int i = 0; i < ids1.length; i++) {
						myLogger.info("ids1 :" + ids1);
						logic.deleteDokumen(ids1[i], db);
					}
				}

				defaultList("", db);
				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}
				paparAduan(db);
				// return_after_load();
				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		} else if (command.equals("deleteDokumen1by1")) {
			// addAduan(session);
			String id_esaduan = getParam("id_esaduan");
			context.put("id_esaduan", id_esaduan);
			String id_esdokumen = getParam("id_esdokumen");

			Db db = null;
			try {
				db = new Db();

				if (!id_esdokumen.equals("")) {
					logic.deleteDokumen(id_esdokumen, db);
				}

				defaultList("", db);
				if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
					listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan,
							db);
					context.put("listDokumen_aduan", listDokumen_aduan);
					listComment_aduan = logic.senarai_comment_aduan(id_esaduan,
							"1", db);
					context.put("listComment_aduan", listComment_aduan);
					listComment_aduan_tech = logic.senarai_comment_aduan(
							id_esaduan, "2", db);
					context.put("listComment_aduan_tech",
							listComment_aduan_tech);
					listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan,
							db);
					context.put("listAgihan_aduan", listAgihan_aduan);
					listTechTeam_aduan = logic.senarai_tech_team(
							user_negeri_login, role, db);
					context.put("listTechTeam_aduan", listTechTeam_aduan);
				}
				paparAduan(db);
				// return_after_load();
				context.put("open_maklumat_aduan", "yes");
				context.put("open_upload", "yes");
				context.put("open_maklumbalas", "yes");
				context.put("open_agihan", "yes");
				if (role.equals("admin_es") || role.equals("developer_es")) {
					if (!user_login.equals("620428065305")) {
						context.put("open_maklumat_teknikal", "yes");
						} else {
							context.put("open_maklumat_teknikal", "no");
						}
				}

			} finally {
				if (db != null)
					db.close();
			}
		}
		// papar laporan

		else if (command.equals("paparLaporan")) {

			Db db = null;
			try {
				db = new Db();

				context.put("view_skrin", "laporan");
				list_laporanModul = logic.getLaporanModul("", "", "", "", "",
						"", db);
				context.put("list_laporanModul", list_laporanModul);
				// list_laporanNegeri =
				// logic.getLaporanNegeri(user_negeri_login,"","","");

				list_laporanNegeri = logic.getLaporanNegeri("", "", "", "", "",
						"", db);
				context.put("list_laporanNegeri", list_laporanNegeri);

				list_laporanKategori = logic.getLaporanKategori("", "", "", "",
						"", "", db);
				context.put("list_laporanKategori", list_laporanKategori);
				context.put("id_jenismodulesaduan_cari", "");
				context.put("tarikh_mula", "");
				context.put("tarikh_akhir", "");
				context.put("id_negeri_cari", "");
				// penambahan pla 19/12/16
				context.put("id_pejabatjkptg", "");

				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);

				list_negeri = logic.getListNegeri("", db);
				context.put("list_negeri", list_negeri);

				// list_pejabat = logic.getListPejabat("",db);
				// context.put("list_pejabat",list_pejabat);

				// wani
				if (role.equals("admin_es") || role.equals("developer_es")) {
					list_pejabat = logic.senarai_pejabat(user_negeri_login, db);
				} else {
					list_pejabat = logic.senarai_pejabat("", db);
				}
				context.put("list_pejabat", list_pejabat);

				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

			} finally {
				if (db != null)
					db.close();
			}

		}

		else if (command.equals("cariLaporan")) {
			Db db = null;
			try {
				db = new Db();

				String id_jenismodulesaduan_cari = getParam("id_jenismodulesaduan_cari");
				// penambahan pla
				String id_statusesaduan_cari = getParam("id_statusesaduan_cari");
				myLogger.info("*** id_statusesaduan_cari :"
						+ id_statusesaduan_cari);
				String tarikh_mula = getParam("tarikh_mula");
				String tarikh_akhir = getParam("tarikh_akhir");
				String id_negeri_cari = getParam("id_negeri_cari");
				String id_pejabatjkptg = getParam("id_pejabatjkptg");

				context.put("id_jenismodulesaduan_cari",
						id_jenismodulesaduan_cari);
				// penambahan pla
				context.put("id_statusesaduan_cari", id_statusesaduan_cari);
				context.put("tarikh_mula", tarikh_mula);
				context.put("tarikh_akhir", tarikh_akhir);
				context.put("id_negeri_cari", id_negeri_cari);
				context.put("id_pejabatjkptg", id_pejabatjkptg);

				// penambahan pla
				Vector list_aduan_cari = null;
				context.put("open_aduanlist", "yes");

				context.put("view_skrin", "laporan");
				list_laporanModul = logic.getLaporanModul(id_negeri_cari,
						id_statusesaduan_cari, tarikh_mula, tarikh_akhir,
						id_jenismodulesaduan_cari, id_pejabatjkptg, db);
				context.put("list_laporanModul", list_laporanModul);

				if (!id_negeri_cari.equals("") && !id_negeri_cari.equals(null)) {
					list_laporanNegeri = logic.getLaporanNegeri(id_negeri_cari,
							id_statusesaduan_cari, tarikh_mula, tarikh_akhir,
							id_jenismodulesaduan_cari, id_pejabatjkptg, db);
				} else {
					// list_laporanNegeri =
					// logic.getLaporanNegeri(user_negeri_login,tarikh_mula,tarikh_akhir,id_jenismodulesaduan_cari);
					list_laporanNegeri = logic.getLaporanNegeri(id_negeri_cari,
							id_statusesaduan_cari, tarikh_mula, tarikh_akhir,
							id_jenismodulesaduan_cari, id_pejabatjkptg, db);
				}

				context.put("list_laporanNegeri", list_laporanNegeri);

				// penambahan pla
				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);

				list_laporanKategori = logic.getLaporanKategori(id_negeri_cari,
						id_statusesaduan_cari, tarikh_mula, tarikh_akhir,
						id_jenismodulesaduan_cari, id_pejabatjkptg, db);
				context.put("list_laporanKategori", list_laporanKategori);

				if (role.equals("admin_es") || role.equals("developer_es")) {
					list_pejabat = logic.senarai_pejabat(user_negeri_login, db);
				} else {
					list_pejabat = logic.senarai_pejabat("", db);
				}
				context.put("list_pejabat", list_pejabat);

				list_negeri = logic.getListNegeri("", db);
				context.put("list_negeri", list_negeri);

				// penambahan pla
				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

			} finally {
				if (db != null)
					db.close();
			}
		}

		// carian aduan
		/*
		else if (command.equals("cariAduan")) {
			Db db = null;
			try {
				db = new Db();

				String log_aduan_cari = getParam("log_aduan_cari");
				String user_id_cari = getParam("user_id_cari");
				String user_name = getParam("user_name");

				String no_fail_cari = getParam("no_fail_cari");
				String tarikh_aduan_hantar_date_cari = getParam("tarikh_aduan_hantar_date_cari");
				String id_statusesaduan_cari = getParam("id_statusesaduan_cari");
				String id_jenismodulesaduan_cari = getParam("id_jenismodulesaduan_cari");

				context.put("log_aduan_cari", getParam("log_aduan_cari"));
				context.put("user_id_cari", getParam("user_id_cari"));
				context.put("user_name", getParam("user_name"));

				context.put("no_fail_cari", getParam("no_fail_cari"));
				context.put("tarikh_aduan_hantar_date_cari",
						getParam("tarikh_aduan_hantar_date_cari"));
				context.put("id_statusesaduan_cari",
						getParam("id_statusesaduan_cari"));
				context.put("id_jenismodulesaduan_cari",
						getParam("id_jenismodulesaduan_cari"));

				Vector list_aduan_cari = null;
				context.put("open_aduanlist", "yes");
				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);
				list_users = logic.getListUsers("", "", user_negeri_login, "",
						"", "", db);
				context.put("list_users", list_users);
				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				if (role.equals("adminsuper_es")) {
					list_aduan_cari = logic.getAduan(userId, "", "", "", "",
							"", "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari, user_name, db);

				} else if (role.equals("admin_es")
						|| role.equals("developer_es")) {

					list_aduan_cari = logic.getAduan(userId, "", "", "", "",
							user_negeri_login, "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari, user_name, db);

				} else {

					list_aduan_cari = logic.getAduan(userId, "", userId, "",
							"", "", "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari, user_name, db);

				}

				//context.put("SenaraiFail", list_aduan_cari);
				setupPageCari(session, action, list_aduan_cari);

			} finally {
				if (db != null)
					db.close();
			}

		}
		*/

		// command hapus aduan
		else if (command.equals("hapusAduan")) {

			Db db = null;
			try {
				db = new Db();

				String[] ids1_delete = this.request
						.getParameterValues("ids1_delete");
				if (ids1_delete != null) {
					for (int i = 0; i < ids1_delete.length; i++) {
						myLogger.info("ids1_delete :" + ids1_delete);
						logic.deleteAduan(ids1_delete[i], db);
					}
				}

				displaySenaraiAduan(db);
				context.put("open_aduanlist", "yes");
				context.put("log_aduan_cari", "");
				context.put("user_id_cari", "");
				context.put("user_name", "");
				context.put("no_fail_cari", "");
				context.put("tarikh_aduan_hantar_date_cari", "");
				context.put("tarikh_aduan_hantar_date_akhir", "");
				context.put("id_statusesaduan_cari", "");
				context.put("id_jenismodulesaduan_cari", "");

				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);

				list_users = logic.getListUsers("", "", user_negeri_login, "",
						"", "", db);
				context.put("list_users", list_users);

				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				check_notifikasi_index_maklumbalas_teknikal = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "2", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_teknikal",
						check_notifikasi_index_maklumbalas_teknikal);

				context.put("view_skrin", "aduan");

				context.put("flag_popup_alert_success", "4");

			} finally {
				if (db != null)
					db.close();
			}

		} 
		/*
		else {
			myLogger.info("::::::::::ELSE:::::::::::::");
			
			Db db = null;
			try {
				db = new Db();
				displaySenaraiAduan(db);
				context.put("open_aduanlist", "yes");
				context.put("log_aduan_cari", "");
				context.put("id_status", "");
				// penambahan pla
				context.put("user_name", "");
				context.put("no_fail_cari", "");
				context.put("tarikh_aduan_hantar_date_cari", "");
				if (command.equals("resetcari")) {
					context.put("user_id_cari", "");
					context.put("id_statusesaduan_cari", "");
					context.put("id_jenismodulesaduan_cari", "");

				} else {
					context.put("user_id_cari", (!getParam("user_id_cari")
							.equals("") ? getParam("user_id_cari") : ""));
					context.put(
							"id_statusesaduan_cari",
							(!getParam("id_statusesaduan_cari").equals("") ? getParam("id_statusesaduan_cari")
									: ""));
					context.put(
							"id_jenismodulesaduan_cari",
							(!getParam("id_jenismodulesaduan_cari").equals("") ? getParam("id_jenismodulesaduan_cari")
									: ""));

				}
				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);

				list_users = logic.getListUsers("", "", user_negeri_login, "",
						"", "", db);
				context.put("list_users", list_users);

				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);
				System.out.println("hai" + list_statusaduan);

				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				check_notifikasi_index_maklumbalas_teknikal = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "2", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_teknikal",
						check_notifikasi_index_maklumbalas_teknikal);

				context.put("view_skrin", "aduan");
			} finally {
				if (db != null)
					db.close();
			}

		}*/
		
		else 
			if (command.equals("cariAduan") || command.equals("nextPapar")) 
			{
			Db db = null;
			try {
				db = new Db();

				String log_aduan_cari = getParam("log_aduan_cari");
				String user_id_cari = getParam("user_id_cari");
				String user_name = getParam("user_name");
				String no_fail_cari = getParam("no_fail_cari");
				String tarikh_aduan_hantar_date_cari = getParam("tarikh_aduan_hantar_date_cari");
				String tarikh_aduan_hantar_date_akhir = getParam("tarikh_aduan_hantar_date_akhir");
				String id_statusesaduan_cari = getParam("id_statusesaduan_cari");
				String id_jenismodulesaduan_cari = getParam("id_jenismodulesaduan_cari");

				context.put("user_id_cari", getParam("user_id_cari"));
			
				context.put("log_aduan_cari", getParam("log_aduan_cari"));
				
				context.put("user_name", getParam("user_name"));

				context.put("no_fail_cari", getParam("no_fail_cari"));
				context.put("tarikh_aduan_hantar_date_cari",
						getParam("tarikh_aduan_hantar_date_cari"));
				context.put("tarikh_aduan_hantar_date_akhir",
						getParam("tarikh_aduan_hantar_date_akhir"));
				context.put("id_statusesaduan_cari",
						getParam("id_statusesaduan_cari"));
				context.put("id_jenismodulesaduan_cari",
						getParam("id_jenismodulesaduan_cari"));

				Vector list_aduan_cari = null;
				context.put("open_aduanlist", "yes");
				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);
				list_users = logic.getListUsers("", "", user_negeri_login, "",
						"", "", db);
				context.put("list_users", list_users);
				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				if (role.equals("adminsuper_es")) {
					list_aduan_cari = logic.getAduan(userId, "", "", role, "",
							"", "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari,tarikh_aduan_hantar_date_akhir, user_name, db);

				} else if (role.equals("admin_es")
						|| role.equals("developer_es")) {

					list_aduan_cari = logic.getAduan(userId, "", "", role, "",
							user_negeri_login, "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari,tarikh_aduan_hantar_date_akhir, user_name, db);

				} else {

					list_aduan_cari = logic.getAduan(userId, "", userId, role,
							"", "", "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari,tarikh_aduan_hantar_date_akhir, user_name, db);

				}

				//context.put("SenaraiFail", list_aduan_cari);
				
				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				check_notifikasi_index_maklumbalas_teknikal = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "2", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_teknikal",
						check_notifikasi_index_maklumbalas_teknikal);
				
				
				setupPageCari(session, action, list_aduan_cari);

			} finally {
				if (db != null)
					db.close();
			}

		}
		else
		{
			//diba tambah
			//this.context.put("command", "");
			
			Db db = null;
			try {
				db = new Db();

				String log_aduan_cari = getParam("log_aduan_cari");
				String user_id_cari = getParam("user_id_cari");
				String user_name = getParam("user_name");
				String no_fail_cari = getParam("no_fail_cari");
				String tarikh_aduan_hantar_date_cari = getParam("tarikh_aduan_hantar_date_cari");
				String tarikh_aduan_hantar_date_akhir = getParam("tarikh_aduan_hantar_date_akhir");
				String id_statusesaduan_cari = getParam("id_statusesaduan_cari");
				String id_jenismodulesaduan_cari = getParam("id_jenismodulesaduan_cari");

				context.put("user_id_cari", getParam("user_id_cari"));
			
				context.put("log_aduan_cari", getParam("log_aduan_cari"));
				
				context.put("user_name", getParam("user_name"));

				context.put("no_fail_cari", getParam("no_fail_cari"));
				context.put("tarikh_aduan_hantar_date_cari",
						getParam("tarikh_aduan_hantar_date_cari"));
				context.put("tarikh_aduan_hantar_date_akhir",
						getParam("tarikh_aduan_hantar_date_akhir"));
				context.put("id_statusesaduan_cari",
						getParam("id_statusesaduan_cari"));
				context.put("id_jenismodulesaduan_cari",
						getParam("id_jenismodulesaduan_cari"));

				Vector list_aduan_cari = null;
				context.put("open_aduanlist", "yes");
				list_module = logic.getListModule("", db);
				context.put("list_module", list_module);
				list_users = logic.getListUsers("", "", user_negeri_login, "",
						"", "", db);
				context.put("list_users", list_users);
				list_statusaduan = logic.getListStatusAduan(db);
				context.put("list_statusaduan", list_statusaduan);

				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				if (role.equals("adminsuper_es")) {
					list_aduan_cari = logic.getAduan(userId, "", "", role, "",
							"", "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari,tarikh_aduan_hantar_date_akhir, user_name, db);

				} else if (role.equals("admin_es")
						|| role.equals("developer_es")) {

					list_aduan_cari = logic.getAduan(userId, "", "", role, "",
							user_negeri_login, "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari,tarikh_aduan_hantar_date_akhir, user_name, db);

				} else {

					list_aduan_cari = logic.getAduan(userId, "", userId, role,
							"", "", "", "", no_fail_cari, "",
							id_jenismodulesaduan_cari, log_aduan_cari,
							id_statusesaduan_cari,
							tarikh_aduan_hantar_date_cari,tarikh_aduan_hantar_date_akhir, user_name, db);

				}

				//context.put("SenaraiFail", list_aduan_cari);
				
				check_notifikasi_index_maklumbalas_aduan = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "1", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_aduan",
						check_notifikasi_index_maklumbalas_aduan);

				check_notifikasi_index_maklumbalas_teknikal = logic
						.getListNotifikasi_main_list(role, user_negeri_login,
								"", "2", userId, "NO", db);
				context.put("check_notifikasi_index_maklumbalas_teknikal",
						check_notifikasi_index_maklumbalas_teknikal);
				
				
				setupPageCari(session, action, list_aduan_cari);

			} finally {
				if (db != null)
					db.close();
			}
			
		}

		myLogger.info("vm=" + vm + "\ncommand=" + command);
		return vm;
	}

	private void emailKePengadu(Vector list_aduan, String tarikh_selesai,
			String dari, String hingga, String userId, Db db) throws Exception {
		EmailSender email = EmailSender.getInstance();
		// Hashtable data = findMohonBayaran(idHakmilik,idJawatan);
		// razman kena adjust ni.. buat proper

		EtappSupportAduanData logic = new EtappSupportAduanData();

		String username = logic.getUsername(userId, db);

		Hashtable get_aduan = new Hashtable();

		get_aduan = (Hashtable) list_aduan.get(0);

		// email.FROM = "appmailer@ilaunch.com.my";
		// //"etappsupport@jkptg.gov.my";//email_from;
		email.SUBJECT = "Peringatan untuk Membuat Ulasan Teknikal";// subject_emel;
		if (get_aduan.get("nama_status").toString().equals("SELESAI"))
			email.MESSAGE = FrmEtappSupportAduanSendEmail.setParaGraphSelesai(
					get_aduan, tarikh_selesai, username, db);// setMessageTable(emailType,nama_projek)+""+setParaGraph(emailType,"",nofail,tarikh_permohonan,nama_kementerian,nama_projek);
		else
			email.MESSAGE = FrmEtappSupportAduanSendEmail
					.setParaGraphDalamTindakann(get_aduan, dari, hingga,
							username, db);
		// System.out.println(email.MESSAGE);
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = "etappsupport@jkptg.gov.my"; // get_aduan.get("emel").toString();
																	// //
																	// "mohd.syahrir@ilaunch.com.my";//data.get("emel");//"etappsupport@jkptg.gov.my";
		email.TO_CC = new String[1];
		Hashtable hUser = logic.get_user_emel(userId, db);

		email.TO_CC[0] = !String.valueOf(hUser.get("emel")).equals("") ? String
				.valueOf(hUser.get("emel")) : get_aduan.get("emel").toString();
		// email.TO_CC[1] = "mahani.omar@ilaunch.com.my";
		// email.sendEmailNoAttache();
	}

	private void displaySenaraiAduan(Db db) throws Exception {
		Vector list_aduan = null;

		if (role.equals("adminsuper_es")) {
			list_aduan = logic.getAduan(userId, "", "", role, "", "", "", "", "",
					"", "", "", "", "","", "", db);
		}
		/*
		 * else{ list_aduan =
		 * logic.getAduan(userId,"","","","",user_negeri_login
		 * ,"","","","","","","","",""); //list_aduan =
		 * logic.getAduan(userId,"","","","","","","","","","","","","",""); }
		 */

		else if (role.equals("admin_es") || role.equals("developer_es")) {

			list_aduan = logic.getAduan(userId, "", "", role, "",
					user_negeri_login, "", "", "", "", "", "", "", "", "","", db);

		}
		// diba tambah untuk online_kjp
		/*
		 * else if(role.equals("online_kjp")){
		 * 
		 * System.out.println("role in getAduan : "+role);
		 * System.out.println("user_negeri_login in getAduan : "
		 * +user_negeri_login); list_aduan =
		 * logic.getAduan("","",userId,"","",user_negeri_login
		 * ,"","","","","","","","","",db);
		 * 
		 * }
		 */
		else {

			list_aduan = logic.getAduan(userId, "", userId, role, "", "", "", "",
					"", "", "", "", "", "", "","", db);
			// list_aduan =
			// logic.getAduan(userId,"","","","","","","","","","","","","","");
		}
		/*
		if (getParam("command").equals("doChanges")) {
			// context.put("user_id", getParam("user_id_cari"));
			list_aduan = logic
					.getAduan(
							userId,
							"",
							!getParam("user_id_cari").equals("") ? getParam("user_id_cari")
									: "",
							!getParam("user_name").equals("") ? getParam("user_name")
									: "",
							"",
							"",
							user_negeri_login,
							"",
							"",
							!getParam("no_fail_cari").equals("") ? getParam("no_fail_cari")
									: "",
							"",
							!getParam("id_jenismodulesaduan_cari").equals("") ? getParam("id_jenismodulesaduan_cari")
									: "",
							!getParam("log_aduan_cari").equals("") ? getParam("log_aduan_cari")
									: "",
							!getParam("id_statusesaduan_cari").equals("") ? getParam("id_statusesaduan_cari")
									: "",
							!getParam("tarikh_aduan_hantar_date_cari").equals(
									"") ? getParam("tarikh_aduan_hantar_date_cari")
									: "");

		}
		*/
		//context.put("SenaraiFail", list_aduan);
		setupPage(session, action, list_aduan);

	}

	private void daftarAgih(Db db) throws Exception {
		if (!getParam("id_esaduan").equals("")) {
			String[] selectagih = this.request.getParameterValues("selectagih");
			String[] catatan_agihan = this.request
					.getParameterValues("catatan_agihan");
			String id_esaduan_agih = getParam("id_esaduan");
			logic.deleteAgihan(id_esaduan_agih, db);

			ArrayList id_penerima_agihan_array = new ArrayList();

			if (selectagih != null) {
				for (int i = 0; i < selectagih.length; i++) {
					myLogger.info("selectagih :" + selectagih[i]);
					myLogger.info("catatan_agihan :" + catatan_agihan[i]);
					addAgihan(session, selectagih[i], catatan_agihan[i],
							id_esaduan_agih, db);

					id_penerima_agihan_array.add(selectagih[i]);

				}
				addNotifikasiAgihan(session, id_esaduan_agih,
						id_penerima_agihan_array, db);
			}
		}

	}

	private void defaultList(String flag_daftar, Db db) throws Exception {
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

		list_jenisaduan = logic.getListJenisAduan(db);
		context.put("list_jenisaduan", list_jenisaduan);

		list_sumberaduan = logic.getListSumberAduan(db);
		context.put("list_sumberaduan", list_sumberaduan);

		list_module = logic.getListModule("", db);
		context.put("list_module", list_module);

		String command = getParam("command");

		if ((!role.equals("admin_es") && !role.equals("developer_es") && flag_daftar
				.equals("no"))) {
			userId = (String) session.getAttribute("_ekptg_user_id");
			list_users = logic.getListUsers(userId, "", "", "", "", "", db);
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
				context.put("id_seksyen", (String) get_user.get("id_seksyen"));
				context.put("nama_kementerian",
						(String) get_user.get("nama_kementerian"));
				context.put("nama_agensi", (String) get_user.get("nama_agensi"));

			}

		} else {
			list_users = logic.getListUsers("", "", user_negeri_login, "", "",
					"", db);
			context.put("list_users", list_users);

		}

		list_statusaduan = logic.getListStatusAduan(db);
		context.put("list_statusaduan", list_statusaduan);

	}

	private void paparAduan(Db db) throws Exception {

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
		Vector listAgihan_aduan = null;
		context.put("listAgihan_aduan", "");
		Vector listTechTeam_aduan = null;
		context.put("listTechTeam_aduan", "");
		Vector listComment_aduan_tech = null;
		context.put("listComment_aduan_tech", "");

		Hashtable get_user = null;
		Hashtable get_aduan = null;

		String id_esaduan = getParam("id_esaduan");

		if (!id_esaduan.equals("")) {
			list_aduan = logic.getAduan("", id_esaduan, "", role, "", "", "", "",
					"", "", "", "", "", "","", "", db);
			if (list_aduan.size() != 0) {
				get_aduan = (Hashtable) list_aduan.get(0);
				context.put("user_id", (String) get_aduan.get("user_id"));
				//context.put("user_name", (String) get_aduan.get("user_name"));
				context.put("id_esaduan", (String) get_aduan.get("id_esaduan"));
				context.put("id_jenisaduan",
						(String) get_aduan.get("id_jenisaduan"));
				context.put("id_sumberaduan",
						(String) get_aduan.get("id_sumberaduan"));
				context.put("aduan", (String) get_aduan.get("aduan"));
				context.put("no_fail", (String) get_aduan.get("no_fail"));
				context.put("nama_modul", (String) get_aduan.get("nama_modul"));
				context.put("nama_skrin", (String) get_aduan.get("nama_skrin"));
				context.put("log_aduan", (String) get_aduan.get("log_aduan"));
				context.put("nama_status",
						(String) get_aduan.get("nama_status"));
				context.put("id_status", (String) get_aduan.get("id_status"));
				context.put("tarikh_selesai",
						(String) get_aduan.get("tarikh_selesai"));
				context.put("no_fail", (String) get_aduan.get("no_fail"));
				context.put("id_jenismodulesaduan",
						(String) get_aduan.get("id_jenismodulesaduan"));
				context.put("no_telefon", (String) get_aduan.get("no_telefon"));
				// context.put("nama_kementerian",(String)get_aduan.get("nama_kementerian"));
				// context.put("nama_agensi",(String)get_aduan.get("nama_agensi"));
				// context.put("id_seksyen",(String)get_aduan.get("id_seksyen"));

				context.put(
						"thpKesukaran",
						thpKesukaran((String) get_aduan.get("perihalKesukaran")));
				context.put("txtDari", (String) get_aduan.get("jangkaMasaDari"));
				context.put("txtHingga",
						(String) get_aduan.get("jangkaMasaHingga"));

				context.put("flag_masalah_db",
						(String) get_aduan.get("flag_masalah_db"));
				context.put("flag_masalah_skrin",
						(String) get_aduan.get("flag_masalah_skrin"));
				context.put("flag_masalah_report",
						(String) get_aduan.get("flag_masalah_report"));
				context.put("flag_masalah_hw",
						(String) get_aduan.get("flag_masalah_hw"));
				context.put("flag_masalah_flow",
						(String) get_aduan.get("flag_masalah_flow"));
				context.put("flag_masalah_pertanyaan",
						(String) get_aduan.get("flag_masalah_pertanyaan"));
				context.put("flag_masalah_penambahan",
						(String) get_aduan.get("flag_masalah_penambahan"));
				context.put("tahap_kesusahan",
						(String) get_aduan.get("tahap_kesusahan"));
				context.put("ulasan_teknikal",
						(String) get_aduan.get("ulasan_teknikal"));

				context.put("id_statusesaduan",
						(String) get_aduan.get("id_statusesaduan"));
				context.put("id_statusesaduan_DB",
						(String) get_aduan.get("id_statusesaduan"));
				context.put("tarikh_aduan_hantar",
						(String) get_aduan.get("tarikh_aduan_hantar"));

				context.put("tarikh_aduan_hantar_date",
						(String) get_aduan.get("tarikh_aduan_hantar_date"));
				context.put("tarikh_aduan_hantar_hour",
						(String) get_aduan.get("tarikh_aduan_hantar_hour"));
				context.put("tarikh_aduan_hantar_minute",
						(String) get_aduan.get("tarikh_aduan_hantar_minute"));
				context.put("tarikh_aduan_hantar_ampm",
						(String) get_aduan.get("tarikh_aduan_hantar_ampm"));
				//tmbh column diba
				context.put("hari_takselesai",
						(int) get_aduan.get("hari_takselesai"));
				context.put("hari_selesai",
						(int) get_aduan.get("hari_selesai"));
				
				updateNotification(session, id_esaduan, userId, db);

				list_users = logic.getListUsers(
						(String) get_aduan.get("user_id"), "", "", "", "", "",
						db);
				if (list_users.size() != 0) {
					get_user = (Hashtable) list_users.get(0);
					context.put("user_id", (String) get_user.get("user_id"));
					context.put("nama_pengadu",
							(String) get_user.get("user_name"));
					context.put("no_tel", (String) get_user.get("no_tel"));
					context.put("seksyen",
							(String) get_user.get("nama_seksyen"));
					context.put("pejabat",
							(String) get_user.get("nama_pejabat"));
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
					context.put("id_seksyen",
							(String) get_user.get("id_seksyen"));
					context.put("nama_kementerian",
							(String) get_user.get("nama_kementerian"));
					myLogger.info("Kementerian : "
							+ (String) get_user.get("nama_kementerian"));

					context.put("nama_agensi",
							(String) get_user.get("nama_agensi"));
				}
			}

		}

		defaultList("", db);

		if (!id_esaduan.equals("") && !id_esaduan.equals(null)) {
			listDokumen_aduan = logic.senarai_dokumen_aduan(id_esaduan);
			context.put("listDokumen_aduan", listDokumen_aduan);
			listComment_aduan = logic.senarai_comment_aduan(id_esaduan, "1");
			context.put("listComment_aduan", listComment_aduan);
			listComment_aduan_tech = logic.senarai_comment_aduan(id_esaduan,
					"2");
			context.put("listComment_aduan_tech", listComment_aduan_tech);
			listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan);
			context.put("listAgihan_aduan", listAgihan_aduan);
			listTechTeam_aduan = logic.senarai_tech_team(user_negeri_login,
					role);
			context.put("listTechTeam_aduan", listTechTeam_aduan);
		}
	}

	private Object thpKesukaran(String perihal) {
		StringBuffer sb = new StringBuffer("");

		try {
			sb.append("<select name='txtThpKesukaran' id='txtThpKesukaran' onChange='javascript:jangkaMasa();'");
			sb.append(" > ");
			// sb.append("<option value=>SILA PILIH TAHAP KESUKARAN LOG</option>\n");
			// sb.append("<option value=>SILA PILIH TAHAP KESUKARAN LOG</option>\n");

			if (perihal.equals("MINOR")) {
				sb.append("<option selected value='1'>1.Minor</option>\n");
			} else {
				sb.append("<option value='1'>1.Minor</option>\n");
			}

			if (perihal.equals("MODERATE")) {
				sb.append("<option selected value='2'>2.Moderate</option>\n");
			} else {
				sb.append("<option value='2'>2.Moderate</option>\n");
			}

			if (perihal.equals("MAJOR")) {
				sb.append("<option selected value='3'>3.Major</option>\n");
			} else {
				sb.append("<option value='3'>3.Major</option>\n");
			}

			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

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
		context.put("user_name", "");
		context.put("id_jenisaduan", "");
		context.put("nama_pengadu", "");
		context.put("emel", "");
		context.put("id_negeri_pengadu", "");
		context.put("id_pejabat_pengadu", "");
		context.put("id_sumberaduan", "");
		context.put("aduan", "");
		context.put("no_fail", "");
		context.put("no_telefon", "");
		context.put("seksyen", "");
		context.put("pejabat", "");
		context.put("negeri", "");
		context.put("daerah", "");
		context.put("nama_modul", "");
		context.put("nama_skrin", "");
		context.put("log_aduan", "");
		context.put("nama_kementerian", "");
		context.put("nama_agensi", "");
		context.put("id_seksyen", "");
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
		context.put("id_seksyen", "");
		context.put("nama_kementerian", "");
		context.put("nama_agensi", "");
		context.put("id_status", "");
	}

	private void addAduan(HttpSession session, Db db) throws Exception {

		// Connection conn = null;
		// Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		String getCurrStatus = "";

		try {
			// db1 = new Db();
			// conn = db.getConnection();
			// conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			myLogger.info("user_id :" + getParam("user_id"));

			long id_esaduan = 0;
			String id_esaduan_update = getParam("id_esaduan");
			String user_id = getParam("user_id");// pengadu
			String id_negeri_pengadu = getParam("id_negeri_pengadu");// pengadu
			String id_pejabat_pengadu = getParam("id_pejabat_pengadu");// pengadu
			String id_jenisaduan = getParam("id_jenisaduan");
			String id_sumberaduan = getParam("id_sumberaduan");
			String aduan = getParam("aduan");
			String emel = getParam("emel");
			String no_fail = getParam("no_fail");
			String nama_modul = getParam("nama_modul");
			String nama_skrin = getParam("nama_skrin");
			String id_jenismodulesaduan = getParam("id_jenismodulesaduan");
			String flag_masalah_db = getParam("flag_masalah_db");
			String flag_masalah_skrin = getParam("flag_masalah_skrin");
			String flag_masalah_report = getParam("flag_masalah_report");
			String flag_masalah_hw = getParam("flag_masalah_hw");
			String ID_TBLRUJTAHAPKESUKARAN = getParam("txtThpKesukaran");
			String tarikh_selesai = getParam("tarikh_selesai");

			String flag_masalah_flow = getParam("flag_masalah_flow");
			String ulasan_teknikal = getParam("ulasan_teknikal");
			String id_statusesaduan = getParam("id_statusesaduan");
			myLogger.info("id_statusesaduan :::::::::::" + id_statusesaduan);
			String command = getParam("command");
			String no_rujukan_aduan = "";

			String tarikh_aduan_manual = "''";
			String tarikh_aduan_manual_convert = "''";

			if (!getParam("tarikh_aduan_hantar_date").equals("")
					&& !getParam("tarikh_aduan_hantar_hour").equals("")
					&& !getParam("tarikh_aduan_hantar_minute").equals("")
					&& !getParam("tarikh_aduan_hantar_ampm").equals("")) {
				tarikh_aduan_manual = getParam("tarikh_aduan_hantar_date")
						+ " " + getParam("tarikh_aduan_hantar_hour") + ":"
						+ getParam("tarikh_aduan_hantar_minute") + ":00 "
						+ getParam("tarikh_aduan_hantar_ampm");
				tarikh_aduan_manual_convert = "to_date('" + tarikh_aduan_manual
						+ "','dd/mm/yyyy hh:mi:ss am')";

			}
			myLogger.info("tarikh_aduan_manual_convert :"
					+ tarikh_aduan_manual_convert);

			if (command.equals("simpanAduan_hantar")) {
				// command ni hanya digunakan oleh "pengadu biasa" selepas
				// menekan butang "Hantar Aduan"
				// kalo admin/developer yang register aduan,tidak perlu execute
				// command ni..sebab log aduan telah generate
				// Nak generate no Log aduan
				// get tahun
				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String tahun = formatter.format(now);
				// get kod modul
				String kod_modul = "";
				if (!id_jenismodulesaduan.equals("")) {
					Vector list_module = logic.getListModule(
							id_jenismodulesaduan, db);
					if (list_module.size() != 0) {
						Hashtable kodmodul = (Hashtable) list_module.get(0);
						kod_modul = (String) kodmodul.get("kod_module");
					}

				}
				// get kod negeri by user(pengadu)
				String kod_negeri = "";
				String id_negeri = "";
				if (!user_id.equals("")) {
					Vector list_user = logic.getListUsers(user_id, "", "", "",
							"", "", db);
					if (list_user.size() != 0) {
						Hashtable negeri = (Hashtable) list_user.get(0);
						kod_negeri = (String) negeri.get("kod_negeri");
						id_negeri = (String) negeri.get("id_negeri");
					}

				}
				no_rujukan_aduan = tahun
						+ "/"
						+ kod_negeri
						+ "/"
						+ kod_modul
						+ "/"
						+ String.format(
								"%06d",
								getSeqNoAduanOnline(tahun, id_negeri,
										kod_modul, db));

			} else {
				if (id_esaduan_update.equals("")
						&& (role.equals("admin_es") || role
								.equals("developer_es"))) {
					// hanya untuk admin/developer sahaja bila daftar
					// aduan...lepas daftar terus generate Log Aduan
					// Nak ganerate no Log aduan
					// get tahun
					Date now = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					String tahun = formatter.format(now);
					// get kod modul
					String kod_modul = "";
					if (!id_jenismodulesaduan.equals("")) {
						Vector list_module = logic.getListModule(
								id_jenismodulesaduan, db);
						if (list_module.size() != 0) {
							Hashtable kodmodul = (Hashtable) list_module.get(0);
							kod_modul = (String) kodmodul.get("kod_module");
						}
					}
					// get kod negeri by user(pengadu)
					String kod_negeri = "";
					String id_negeri = "";
					if (!user_id.equals("")) {
						Vector list_user = logic.getListUsers(user_id, "", "",
								"", "", "", db);
						if (list_user.size() != 0) {
							Hashtable negeri = (Hashtable) list_user.get(0);
							kod_negeri = (String) negeri.get("kod_negeri");
							id_negeri = (String) negeri.get("id_negeri");
						}

					}
					no_rujukan_aduan = tahun
							+ "/"
							+ kod_negeri
							+ "/"
							+ kod_modul
							+ "/"
							+ String.format(
									"%06d",
									getSeqNoAduanOnline(tahun, id_negeri,
											kod_modul, db));
				}
			}

			r.clear();
			if (id_esaduan_update.equals("")) {
				id_esaduan = DB.getNextID(db, "TBLESADUAN_SEQ");
				r.add("ID_ESADUAN", id_esaduan);
				r.add("LOG_ADUAN", no_rujukan_aduan);// BILA HANTAR BARU
														// GENARATE LOG

				if (role.equals("admin_es") || role.equals("developer_es")) {
					// kalo teknikal team yang daftarkan
					r.add("ID_STATUS", id_statusesaduan);// status aduan baru
					getCurrStatus = id_statusesaduan;
					if (!tarikh_aduan_manual.equals("")) {
						r.add("TARIKH_ADUAN_HANTAR",
								r.unquote(tarikh_aduan_manual_convert));
					}
				} else {
					// kalo pengadu yang daftar...1st status adalah masih belum
					// dihantar/deraf
					getCurrStatus = "16125";
					r.add("ID_STATUS", "16125");// aduan masih belum
												// dihantar/deraf
				}
			} else {
				r.update("ID_ESADUAN", id_esaduan_update);

				if (command.equals("simpanAduan_hantar")) {
					// untuk pengadu biasa..command ini akan generate Log aduan
					r.add("LOG_ADUAN", no_rujukan_aduan);
					r.add("ID_STATUS", "16121"); // status betukar jadi aduan
													// baru
					id_statusesaduan = "16121";
					r.add("TARIKH_ADUAN_HANTAR", r.unquote("sysdate"));
					getCurrStatus = id_statusesaduan;
				} else {
					// getParam status dari JSP
					r.add("ID_STATUS", id_statusesaduan);
					getCurrStatus = id_statusesaduan;
					r.add("TARIKH_ADUAN_HANTAR",
							r.unquote(tarikh_aduan_manual_convert));
				}
			}

			r.add("ADUAN", aduan);
			r.add("ID_SUMBERADUAN", id_sumberaduan);
			r.add("ID_JENISADUAN", id_jenisaduan);
			r.add("USER_ID", user_id);
			r.add("ID_NEGERI_PENGADU", id_negeri_pengadu);
			r.add("ID_PEJABAT_PENGADU", id_pejabat_pengadu);

			/*
			 * --STATUS ADUAN-- 16121 - ADUAN BARU 16122 - SEDANG DIAMBIL
			 * TINDAKAN 16123 - SELESAI 16124 - ADUAN DITOLAK 16125 - ADUAN
			 * BELUM DIHANTAR
			 */

			r.add("ID_JENISMODULESADUAN", id_jenismodulesaduan);
			r.add("NAMA_SKRIN", nama_skrin);
			r.add("TARIKH_ADUAN_TERIMA", "");

			if (!"".equals(tarikh_selesai)) {
				r.add("TARIKH_SELESAI",
						r.unquote("to_date('" + tarikh_selesai
								+ "','dd/MM/yyyy')"));
			}

			r.add("TAHAP_PENYELESAIAN", "");
			r.add("NAMA_MODUL", nama_modul);
			r.add("NO_FAIL", no_fail);
			r.add("ALERT_ADMIN", "");
			r.add("ALERT_DEVELOPER", "");
			r.add("ALERT_PENGADU", "");

			r.add("FLAG_MASALAH_DB", flag_masalah_db);
			r.add("FLAG_MASALAH_SKRIN", flag_masalah_skrin);
			r.add("FLAG_MASALAH_REPORT", flag_masalah_report);
			r.add("FLAG_MASALAH_HW", flag_masalah_hw);
			r.add("FLAG_MASALAH_FLOW", flag_masalah_flow);
			r.add("FLAG_MASALAH_PERTANYAAN", "");
			r.add("FLAG_MASALAH_PENAMBAHAN", "");
			r.add("TAHAP_KESUSAHAN", "");
			r.add("ULASAN_TEKNIKAL", ulasan_teknikal);

			//r.add("ID_MASUK", user_id_login);
			//r.add("TARIKH_MASUK", r.unquote("sysdate"));
			
			if(id_esaduan_update.equals(""))
			{
				//insert baru add
				r.add("ID_MASUK", user_id_login);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
			}
			
			r.add("ID_KEMASKINI", user_id_login);			
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_TBLRUJTAHAPKESUKARAN", ID_TBLRUJTAHAPKESUKARAN);

			// ruangan ini bertujuan untuk notification
			// check dlu ada tak id_esaduan_update?
			// then tgk status sebelum ni...adakah status berubah untuk save
			// yang terkini,kalo ubah kena update notifikasi
			Vector check_status_aduan = null;
			check_status_aduan = logic.getAduan("", id_esaduan_update, "", role,
					"", "", "", "", "", "", "", "", "","", "", "", db);

			if (id_esaduan_update.equals("")) {
				sql = r.getSQLInsert("TBLESADUAN", db);
			} else {
				sql = r.getSQLUpdate("TBLESADUAN", db);
			}
			myLogger.info("INSERT UPDATE ADUAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			Hashtable get_aduan_status = null;
			String id_status_asal = "";
			String update_notifikasi = "no";
			String daftar_kutipan_data = "no";
			if (!id_esaduan_update.equals("")) {

				if (check_status_aduan.size() != 0) {
					get_aduan_status = (Hashtable) check_status_aduan.get(0);
					id_status_asal = (String) get_aduan_status.get("id_status");
				}
				myLogger.info("::::::::: id_esaduan_update : "
						+ id_esaduan_update + " id_statusesaduan : "
						+ id_statusesaduan + " id_status_asal : "
						+ id_status_asal);

				if (!id_statusesaduan.equals("")
						&& !id_statusesaduan.equals("16125")
						&& !id_status_asal.equals(id_statusesaduan)) {
					// update notifikasi n emel to etapp support
					update_notifikasi = "yes";
				}
			} else {
				// if belum 1st time register aduan
				if (!id_statusesaduan.equals("")
						&& !id_statusesaduan.equals("16125")
						&& (role.equals("admin_es") || role
								.equals("developer_es"))) {
					// update notifikasi to etapp support
					// notify untuk user pengadu sahaja
					// update_notifikasi = "yes";
					daftar_kutipan_data = "yes";
				}
			}

			ArrayList id_penerima_array = new ArrayList();
			String maklumbalas_emel = "";
			String id_esaduan_emel = "";
			String id_penghantar_emel = "";

			if (daftar_kutipan_data.equals("yes")) {
				Vector check_tech = null;
				Vector check_notifikasi = null;
				check_tech = logic.senarai_tech_team(user_negeri_login, role,
						db);

				if (check_tech.size() != 0) {
					for (int i = 0; i < check_tech.size(); i++) {
						Hashtable get_tech = (Hashtable) check_tech.get(i);
						String user_name = (String) get_tech.get("user_name");
						String user_id_terima = (String) get_tech
								.get("user_id");
						myLogger.info("user_name TECH TEAM :"
								+ user_name.toUpperCase());
						String id_esnotifikasi = "";
						check_notifikasi = logic.getListNotifikasi(id_esaduan
								+ "", "1", user_id_terima, "", db);
						if (check_notifikasi.size() != 0) {
							Hashtable get_notifikasi = (Hashtable) check_notifikasi
									.get(0);
							id_esnotifikasi = (String) get_notifikasi
									.get("id_esnotifikasi");
						}

						if (!user_id_login.equals(user_id_terima)) {
							r.clear();
							if (!id_esnotifikasi.equals("")) {
								r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
							}
							r.add("FLAG_NOTIFIKASI", "1");
							r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
							r.add("ID_USER_NOTIFIKASI_TERIMA", user_id_terima);
							r.add("ID_ESADUAN", id_esaduan);
							r.add("FLAG_READ", "NO");

							id_penerima_array.add(user_id_terima);
							maklumbalas_emel = "permohonan_baru_kutipan_data";
							id_esaduan_emel = id_esaduan + "";
							id_penghantar_emel = user_id_login;

							r.add("ID_MASUK", user_id_login);
							r.add("ID_KEMASKINI", user_id_login);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							if (!id_esnotifikasi.equals("")) {
								sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db);
							} else {
								sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db);
							}
							myLogger.info(":::::::::::::::: INSERT NOTIFIKASI : "
									+ sql1);
							stmt.executeUpdate(sql1);
						}
					}
				}
				r.clear();
				r.add("FLAG_NOTIFIKASI", "1");
				r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
				r.add("ID_USER_NOTIFIKASI_TERIMA", user_id);

				id_penerima_array.add(user_id);
				maklumbalas_emel = "permohonan_baru_kutipan_data";
				id_esaduan_emel = id_esaduan + "";
				id_penghantar_emel = user_id_login;

				r.add("ID_ESADUAN", id_esaduan);
				r.add("FLAG_READ", "NO");
				r.add("ID_MASUK", user_id_login);
				r.add("ID_KEMASKINI", user_id_login);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db);
				stmt.executeUpdate(sql1);
			}
			myLogger.info("---------------------------------- sini "
					+ update_notifikasi);
			if (update_notifikasi.equals("yes")) {

				Vector list_aduan = null;
				Hashtable get_aduan = null;
				String user_first = "";
				Vector check_notifikasi = null;

				if (!id_esaduan_update.equals("")) {
					list_aduan = logic.getAduan("", id_esaduan_update, "", role,
							"", "", "", "", "", "", "", "", "","", "", "", db);
					if (list_aduan.size() != 0) {
						get_aduan = (Hashtable) list_aduan.get(0);
						user_first = (String) get_aduan.get("user_id");

						if (!user_first.equals("") && !user_first.equals(null)) {
							String id_esnotifikasi = "";
							check_notifikasi = logic.getListNotifikasi(
									id_esaduan_update, "1", user_first, "", db);
							if (check_notifikasi.size() != 0) {
								Hashtable get_notifikasi = (Hashtable) check_notifikasi
										.get(0);
								id_esnotifikasi = (String) get_notifikasi
										.get("id_esnotifikasi");
							}

							if (!user_first.equals(user_id_login)) {
								r.clear();
								if (!id_esnotifikasi.equals("")) {
									r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
								}

								id_penerima_array.add(user_first);
								maklumbalas_emel = "update_status_permohonan";
								id_esaduan_emel = id_esaduan_update;
								id_penghantar_emel = user_id_login;

								r.add("FLAG_NOTIFIKASI", "1");
								r.add("ID_USER_NOTIFIKASI_HANTAR",
										user_id_login);
								r.add("ID_USER_NOTIFIKASI_TERIMA", user_first);
								r.add("ID_ESADUAN", id_esaduan_update);
								r.add("FLAG_READ", "NO");

								r.add("ID_MASUK", user_id_login);
								r.add("ID_KEMASKINI", user_id_login);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								if (!id_esnotifikasi.equals("")) {
									sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db);
								} else {
									sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db);
								}
								myLogger.info(":::::::::::::::: INSERT NOTIFIKASI 2 : "
										+ sql1);
								stmt.executeUpdate(sql1);
							}
						}
					}
				}

				Vector comment_aduan = null;
				// Vector check_notifikasi = null;
				comment_aduan = logic.senarai_comment_aduan_check(
						id_esaduan_update, "1", db);

				if (comment_aduan.size() != 0) {
					for (int i = 0; i < comment_aduan.size(); i++) {
						Hashtable get_comment_siapa = (Hashtable) comment_aduan
								.get(i);
						String role_comment = (String) get_comment_siapa
								.get("flag_siapa_comment");
						String siapacomment = (String) get_comment_siapa
								.get("user_id");
						myLogger.info("user_name TECH TEAM :"
								+ siapacomment.toUpperCase());
						String id_esnotifikasi = "";
						check_notifikasi = logic.getListNotifikasi(
								id_esaduan_update, "1", siapacomment, "", db);
						if (check_notifikasi.size() != 0) {
							Hashtable get_notifikasi = (Hashtable) check_notifikasi
									.get(0);
							id_esnotifikasi = (String) get_notifikasi
									.get("id_esnotifikasi");
						}

						if (!user_id_login.equals(siapacomment)) {
							r.clear();
							if (!id_esnotifikasi.equals("")) {
								r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
							}

							id_penerima_array.add(siapacomment);
							maklumbalas_emel = "update_status_permohonan";
							id_esaduan_emel = id_esaduan_update;
							id_penghantar_emel = user_id_login;

							r.add("FLAG_NOTIFIKASI", "1");
							r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
							r.add("ID_USER_NOTIFIKASI_TERIMA", siapacomment);
							r.add("ID_ESADUAN", id_esaduan_update);
							r.add("FLAG_READ", "NO");
							r.add("ID_MASUK", user_id_login);
							r.add("ID_KEMASKINI", user_id_login);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							if (!id_esnotifikasi.equals("")) {
								sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db);
							} else {
								sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db);
							}
							stmt.executeUpdate(sql1);
						}
					}
				}

				Vector check_tech = null;
				check_tech = logic.senarai_tech_team(user_negeri_login, role,
						db);
				myLogger.info(" check_tech size : " + check_tech.size());
				if (check_tech.size() != 0) {
					for (int i = 0; i < check_tech.size(); i++) {
						Hashtable get_tech = (Hashtable) check_tech.get(i);
						String user_name = (String) get_tech.get("user_name");
						String user_id_terima = (String) get_tech
								.get("user_id");
						myLogger.info("user_name TECH TEAM :"
								+ user_name.toUpperCase());
						String id_esnotifikasi = "";
						check_notifikasi = logic.getListNotifikasi(
								id_esaduan_update, "1", user_id_terima, "", db);
						if (check_notifikasi.size() != 0) {
							Hashtable get_notifikasi = (Hashtable) check_notifikasi
									.get(0);
							id_esnotifikasi = (String) get_notifikasi
									.get("id_esnotifikasi");
						}

						if (!user_id_login.equals(user_id_terima)) {
							r.clear();
							if (!id_esnotifikasi.equals("")) {
								r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
							}

							r.add("FLAG_NOTIFIKASI", "1");
							r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
							r.add("ID_USER_NOTIFIKASI_TERIMA", user_id_terima);
							r.add("ID_ESADUAN", id_esaduan_update);
							r.add("FLAG_READ", "NO");

							id_penerima_array.add(user_id_terima);
							maklumbalas_emel = "update_status_permohonan";
							id_esaduan_emel = id_esaduan_update;
							id_penghantar_emel = user_id_login;

							r.add("ID_MASUK", user_id_login);
							r.add("ID_KEMASKINI", user_id_login);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							if (!id_esnotifikasi.equals("")) {
								sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db);
							} else {
								sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db);
							}
							stmt.executeUpdate(sql1);
						}
					}
				}
			}

			if (!user_id.equals("") && !emel.equals("")) {
				r.clear();
				r.update("USER_ID", user_id);
				r.add("EMEL", emel);
				sql1 = r.getSQLUpdate("USERS_INTERNAL", db);
				stmt.executeUpdate(sql1);
			}

			// conn.commit();

			Long id_esaduanLong = id_esaduan;
			String idEsaduan = id_esaduan_update == "" ? id_esaduanLong
					.toString() : id_esaduan_update;

			if (command.equals("simpanAduan")
					|| command.equals("simpanAduan_hantar")) {
				// razman
				myLogger.info(" getCurrStatus : " + getCurrStatus);
				if (!getCurrStatus.equals("16125")) {
					hantarEmelAduan(emel, user_id_login, id_penerima_array,
					session, "4", "", "", "", maklumbalas_emel, "",
							idEsaduan, "", id_penghantar_emel, "", db);
				}

				if (getCurrStatus.equals("16121")) {
					hantarEmelAduan(emel, user_id_login, id_penerima_array,
							session, "5", "", "", "", maklumbalas_emel, "",
							idEsaduan, "", id_penghantar_emel, "", db);
				}
			}

			// if(!id_esaduan_emel.equals("") && !id_esaduan_emel.equals(null)
			// && !id_penghantar_emel.equals("") &&
			// !id_penghantar_emel.equals(null)) {
			// hantarEmel(id_penerima_array,session,"4","","","",maklumbalas_emel,"",id_esaduan_emel,"",id_penghantar_emel,"");
			// }

			Vector list_aduan = null;
			Vector list_users = null;
			Hashtable get_user = null;
			Hashtable get_aduan = null;
			Vector listDokumen_aduan = null;
			Vector listComment_aduan = null;
			Vector listComment_aduan_tech = null;
			Vector listAgihan_aduan = null;
			Vector listTechTeam_aduan = null;

			if (id_esaduan_update.equals("")) {
				list_aduan = logic.getAduan(userId, id_esaduan + "", "", role,
						"", "", "", "", "", "", "", "", "","", "", "", db);
				listDokumen_aduan = logic.senarai_dokumen_aduan(
						id_esaduan + "", db);
				context.put("listDokumen_aduan", listDokumen_aduan);
				listComment_aduan = logic.senarai_comment_aduan(
						id_esaduan + "", "1", db);
				context.put("listComment_aduan", listComment_aduan);
				listComment_aduan_tech = logic.senarai_comment_aduan(id_esaduan
						+ "", "2", db);
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan + "",
						db);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_tech_team(user_negeri_login,
						role, db);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			} else {
				list_aduan = logic.getAduan(userId, id_esaduan_update + "", "",
						role, "", "", "", "", "", "", "", "", "", "","", "", db);
				listDokumen_aduan = logic.senarai_dokumen_aduan(
						id_esaduan_update + "", db);
				context.put("listDokumen_aduan", listDokumen_aduan);
				listComment_aduan = logic.senarai_comment_aduan(
						id_esaduan_update + "", "1", db);
				context.put("listComment_aduan", listComment_aduan);
				listComment_aduan_tech = logic.senarai_comment_aduan(
						id_esaduan_update + "", "2", db);
				context.put("listComment_aduan_tech", listComment_aduan_tech);
				listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan_update
						+ "", db);
				context.put("listAgihan_aduan", listAgihan_aduan);
				listTechTeam_aduan = logic.senarai_tech_team(user_negeri_login,
						role, db);
				context.put("listTechTeam_aduan", listTechTeam_aduan);
			}
			if (list_aduan.size() != 0) {
				get_aduan = (Hashtable) list_aduan.get(0);
				context.put("user_id", (String) get_aduan.get("user_id"));
				context.put("id_esaduan", (String) get_aduan.get("id_esaduan"));
				context.put("id_jenisaduan",
						(String) get_aduan.get("id_jenisaduan"));
				context.put("id_sumberaduan",
						(String) get_aduan.get("id_sumberaduan"));
				context.put("aduan", (String) get_aduan.get("aduan"));
				context.put("no_fail", (String) get_aduan.get("no_fail"));
				context.put("nama_modul", (String) get_aduan.get("nama_modul"));
				context.put("nama_skrin", (String) get_aduan.get("nama_skrin"));
				context.put("log_aduan", (String) get_aduan.get("log_aduan"));
				context.put("nama_status",
						(String) get_aduan.get("nama_status"));
				context.put("id_status", (String) get_aduan.get("id_status"));
				context.put("no_fail", (String) get_aduan.get("no_fail"));
				context.put("id_jenismodulesaduan",
						(String) get_aduan.get("id_jenismodulesaduan"));

				// context.put("nama_kementerian",(String)get_aduan.get("nama_kementerian"));
				// context.put("nama_agensi",(String)get_aduan.get("nama_agensi"));
				// context.put("id_seksyen",(String)get_aduan.get("id_seksyen"));

				context.put("flag_masalah_db",
						(String) get_aduan.get("flag_masalah_db"));
				context.put("flag_masalah_skrin",
						(String) get_aduan.get("flag_masalah_skrin"));
				context.put("flag_masalah_report",
						(String) get_aduan.get("flag_masalah_report"));
				context.put("flag_masalah_hw",
						(String) get_aduan.get("flag_masalah_hw"));
				context.put("flag_masalah_flow",
						(String) get_aduan.get("flag_masalah_flow"));
				context.put("flag_masalah_pertanyaan",
						(String) get_aduan.get("flag_masalah_pertanyaan"));
				context.put("flag_masalah_penambahan",
						(String) get_aduan.get("flag_masalah_penambahan"));
				context.put("tahap_kesusahan",
						(String) get_aduan.get("tahap_kesusahan"));
				context.put("ulasan_teknikal",
						(String) get_aduan.get("ulasan_teknikal"));

				context.put("id_statusesaduan",
						(String) get_aduan.get("id_statusesaduan"));
				context.put("id_statusesaduan_DB",
						(String) get_aduan.get("id_statusesaduan"));
				context.put("tarikh_aduan_hantar",
						(String) get_aduan.get("tarikh_aduan_hantar"));
				context.put("tarikh_aduan_hantar_date",
						(String) get_aduan.get("tarikh_aduan_hantar_date"));
				context.put("tarikh_aduan_hantar_hour",
						(String) get_aduan.get("tarikh_aduan_hantar_hour"));
				context.put("tarikh_aduan_hantar_minute",
						(String) get_aduan.get("tarikh_aduan_hantar_minute"));
				context.put("tarikh_aduan_hantar_ampm",
						(String) get_aduan.get("tarikh_aduan_hantar_ampm"));

				list_users = logic.getListUsers(
						(String) get_aduan.get("user_id"), "", "", "", "", "",
						db);
				if (list_users.size() != 0) {
					get_user = (Hashtable) list_users.get(0);
					context.put("user_id", (String) get_user.get("user_id"));
					context.put("nama_pengadu",
							(String) get_user.get("user_name"));
					context.put("no_tel", (String) get_user.get("user_id"));
					context.put("seksyen",
							(String) get_user.get("nama_seksyen"));
					context.put("pejabat",
							(String) get_user.get("nama_pejabat"));
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
			}
		}
		/*
		 * catch (SQLException se) { try { conn.rollback(); } catch
		 * (SQLException se2) { throw new Exception("Rollback error:" +
		 * se2.getMessage()); } se.printStackTrace(); throw new
		 * Exception("Ralat Simpan Aduan:" + se.getMessage()); }
		 */
		finally {
			// if (conn != null)
			// conn.close();
			// if (db != null)
			// db.close();
		}

	}

	private void addAgihan(HttpSession session, String user_agih_id,
			String catatan_agihan, String id_esaduan, Db db) throws Exception {
		//Connection conn = null;
		// Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		String jenis_comment = "2";
		Vector check_notifikasi = null;

		try {
			// db = new Db();
			// conn = db.getConnection();
			// conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String user_id = (String) session.getAttribute("_ekptg_user_id");

			r.clear();
			r.add("ID_ESADUAN", id_esaduan);
			r.add("USER_ID", user_agih_id);
			r.add("KETERANGAN", catatan_agihan);
			r.add("ID_MASUK", user_id);
			r.add("ID_KEMASKINI", user_id);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLESAGIHAN");
			myLogger.info("INSERT AGIHAN " + sql.toUpperCase());
			stmt.executeUpdate(sql);

			// conn.commit();
		} /*
		 * catch (SQLException se) { try { conn.rollback(); } catch
		 * (SQLException se2) { throw new Exception("Rollback error:" +
		 * se2.getMessage()); } se.printStackTrace(); throw new
		 * Exception("Ralat Simpan Aduan:" + se.getMessage());
		 * 
		 * }
		 */finally {
			//if (conn != null)
			//	conn.close();
			// if (db != null)
			// db.close();
		}

	}

	private void addNotifikasiAgihan(HttpSession session, String id_esaduan,
			ArrayList id_penerima_agihan_array, Db db) throws Exception {
		//Connection conn = null;
		Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		String jenis_comment = "2";
		Vector check_notifikasi = null;

		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			//conn = db1.getConnection();
			//conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String user_id = (String) session.getAttribute("_ekptg_user_id");

			ArrayList id_penerima_array = new ArrayList();
			String maklumbalas_emel = "";
			String id_esaduan_emel = "";
			String id_penghantar_emel = "";

			if (role.equals("admin_es") || role.equals("developer_es")) {
				Vector check_tech = null;
				check_tech = logic.senarai_tech_team(user_negeri_login, role,
						db);
				if (check_tech.size() != 0) {
					for (int i = 0; i < check_tech.size(); i++) {
						Hashtable get_tech = (Hashtable) check_tech.get(i);
						String user_name = (String) get_tech.get("user_name");
						String user_id_terima = (String) get_tech
								.get("user_id");
						myLogger.info("user_name TECH TEAM :"
								+ user_name.toUpperCase());
						String id_esnotifikasi = "";
						check_notifikasi = logic.getListNotifikasi(id_esaduan,
								jenis_comment, user_id_terima, "", db);
						if (check_notifikasi.size() != 0) {
							Hashtable get_notifikasi = (Hashtable) check_notifikasi
									.get(0);
							id_esnotifikasi = (String) get_notifikasi
									.get("id_esnotifikasi");
						}

						if (!user_id.equals(user_id_terima)) {
							r.clear();
							if (!id_esnotifikasi.equals("")) {
								r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
							}
							r.add("FLAG_NOTIFIKASI", jenis_comment);
							r.add("ID_USER_NOTIFIKASI_HANTAR", user_id);
							r.add("ID_USER_NOTIFIKASI_TERIMA", user_id_terima);

							id_penerima_array.add(user_id_terima);
							// maklumbalas_emel = maklumbalas;
							id_esaduan_emel = id_esaduan;
							id_penghantar_emel = user_id;

							r.add("ID_ESADUAN", id_esaduan);
							r.add("FLAG_READ", "NO");
							r.add("ID_MASUK", user_id);
							r.add("ID_KEMASKINI", user_id);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							if (!id_esnotifikasi.equals("")) {
								sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db);
							} else {
								sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db);
							}
							stmt.executeUpdate(sql1);
							// hantarEmel(session,"4","","","",maklumbalas,"",id_esaduan,user_id_terima,user_id_login);

						}
					}
				}
			}

			if (id_penerima_agihan_array.size() > 0) {
				String nama_pengagih = "";
				for (int i = 0; i < id_penerima_agihan_array.size(); i++) {

					myLogger.info("CHECK AGIHAN SIZE"
							+ id_penerima_agihan_array.size()
							+ " ID_USER_TERIMA AGIHAN :"
							+ id_penerima_agihan_array.get(i).toString());
					Hashtable user_name = logic.get_user_emel(
							id_penerima_agihan_array.get(i).toString(), db);
					Hashtable arahan = logic.get_arahan_agihan(id_esaduan,
							id_penerima_agihan_array.get(i).toString(), db);

					myLogger.info("NAMA YANG DIAGIH :"
							+ user_name.get("user_name").toString());

					maklumbalas_emel += "<table width='100%' border='0'>";

					maklumbalas_emel += "<tr>";
					maklumbalas_emel += "<td width='40%' valign='top'>"
							+ user_name.get("user_name").toString() + "</td>";
					if (!arahan.get("arahan").toString().equals("")) {
						maklumbalas_emel += "<td width='13%' valign='top'><b>Catatan </b></td><td width='1%' valign='top'><b>: </b></td>";
					} else {
						maklumbalas_emel += "<td width='13%' valign='top'></td>";
					}
					maklumbalas_emel += "<td width='46%' valign='top'>"
							+ arahan.get("arahan").toString() + "</td>";
					maklumbalas_emel += "</tr>";
					// maklumbalas_emel +=
					// user_name.get("user_name").toString()+" : "+arahan.get("arahan").toString()+"<br>";
					maklumbalas_emel += "</table>";
				}

			}
			if (!id_esaduan_emel.equals("") && !id_esaduan_emel.equals(null)
					&& !id_penghantar_emel.equals("")
					&& !id_penghantar_emel.equals(null)) {
				// razman
				hantarEmel(id_penerima_array, session, "4", "", "", "",
						maklumbalas_emel, "", id_esaduan_emel, "",
						id_penghantar_emel, "", db); //asal 3
			}

			//conn.commit();
		} /*catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
		} */finally {
			// if (conn != null)
			// conn.close();
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}

	}

	private void addComment(HttpSession session, String jenis_comment, Db db)
			throws Exception {
		Connection conn = null;
		Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;

		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			conn = db1.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			String id_esaduan = getParam("id_esaduan");

			myLogger.info("XXXXXXXXXXXX id_esaduan :" + id_esaduan);

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
			if (role.equals("admin_es")) {
				siapa_comment = 1;
			} else if (role.equals("developer_es")) {
				siapa_comment = 2;
			} else if (role.equals("adminsuper_es")) {
				siapa_comment = 3;
			} else {
				siapa_comment = 3;
			}
			r.clear();
			r.add("ID_ESADUAN", id_esaduan);
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
			sql = r.getSQLInsert("TBLESCOMMENT", db1);
			stmt.executeUpdate(sql);

			// jenis_comment = 1 = maklumbalas Pengadu
			// jenis_comment = 2 = maklumbalas Teknikal

			// comment teknikal team untuk pengadu

			ArrayList id_penerima_array = new ArrayList();
			String maklumbalas_emel = "";
			String id_esaduan_emel = "";
			String id_penghantar_emel = "";
			Vector list_aduan = null;
			Hashtable get_aduan = null;
			String user_first = "";

			// hantarEmel(id_penerima_array,session,jenis_comment,"","","",maklumbalas_emel,"",id_esaduan_emel,"",id_penghantar_emel,"",db1);
			
			if (jenis_comment.equals("1")) {
				myLogger.info("AAAAAAAAAAAAAAAAAAAA MASUK 1");
				Vector comment_aduan = null;
				comment_aduan = logic.senarai_comment_aduan_check(id_esaduan,
						"1", db1);

				if (!id_esaduan.equals("")) {
					list_aduan = logic.getAduan("", id_esaduan, "", role, "", "",
							"", "", "", "", "", "", "", "","", "", db1);
					if (list_aduan.size() != 0) {
						get_aduan = (Hashtable) list_aduan.get(0);
						user_first = (String) get_aduan.get("user_id");

						if (!user_first.equals("") && !user_first.equals(null)) {
							String id_esnotifikasi = "";
							check_notifikasi = logic.getListNotifikasi(
									id_esaduan, jenis_comment, user_first, "",
									db1);
							if (check_notifikasi.size() != 0) {
								Hashtable get_notifikasi = (Hashtable) check_notifikasi
										.get(0);
								id_esnotifikasi = (String) get_notifikasi
										.get("id_esnotifikasi");
							}

							if (!user_first.equals(user_id_login)) {
								r.clear();
								if (!id_esnotifikasi.equals("")) {
									r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
								}
								r.add("FLAG_NOTIFIKASI", jenis_comment);
								r.add("ID_USER_NOTIFIKASI_HANTAR",
										user_id_login);
								r.add("ID_USER_NOTIFIKASI_TERIMA", user_first);
								id_penerima_array.add(user_first);
								maklumbalas_emel = maklumbalas;
								id_esaduan_emel = id_esaduan;
								id_penghantar_emel = user_id_login;
								r.add("ID_ESADUAN", id_esaduan);
								r.add("FLAG_READ", "NO");
								r.add("ID_MASUK", user_id_login);
								r.add("ID_KEMASKINI", user_id_login);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								if (!id_esnotifikasi.equals("")) {
									sql1 = r.getSQLUpdate("TBLESNOTIFIKASI",
											db1);
								} else {
									sql1 = r.getSQLInsert("TBLESNOTIFIKASI",
											db1);
								}
								stmt.executeUpdate(sql1);
							}

						}
					}
				}

				if (comment_aduan.size() != 0) {
					for (int i = 0; i < comment_aduan.size(); i++) {
						Hashtable get_comment_siapa = (Hashtable) comment_aduan
								.get(i);
						String role_comment = (String) get_comment_siapa
								.get("flag_siapa_comment");
						String siapacomment = (String) get_comment_siapa
								.get("user_id");
						myLogger.info("user_name TECH TEAM :"
								+ siapacomment.toUpperCase());
						String id_esnotifikasi = "";
						check_notifikasi = logic.getListNotifikasi(id_esaduan,
								jenis_comment, siapacomment, "", db1);
						if (check_notifikasi.size() != 0) {
							Hashtable get_notifikasi = (Hashtable) check_notifikasi
									.get(0);
							id_esnotifikasi = (String) get_notifikasi
									.get("id_esnotifikasi");
						}

						if (!user_id_login.equals(siapacomment)) {
							r.clear();
							if (!id_esnotifikasi.equals("")) {
								r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
							}
							r.add("FLAG_NOTIFIKASI", jenis_comment);
							r.add("ID_USER_NOTIFIKASI_HANTAR", user_id_login);
							r.add("ID_USER_NOTIFIKASI_TERIMA", siapacomment);
							id_penerima_array.add(siapacomment);
							maklumbalas_emel = maklumbalas;
							id_esaduan_emel = id_esaduan;
							id_penghantar_emel = user_id_login;
							r.add("ID_ESADUAN", id_esaduan);
							r.add("FLAG_READ", "NO");
							r.add("ID_MASUK", user_id_login);
							r.add("ID_KEMASKINI", user_id_login);
							r.add("TARIKH_MASUK", r.unquote("sysdate"));
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							if (!id_esnotifikasi.equals("")) {
								sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db1);
							} else {
								sql1 = r.getSQLInsert("TBLESNOTIFIKASI", db1);
							}
							stmt.executeUpdate(sql1);
						}
					}
				}

				if (!role.equals("admin_es") && !role.equals("developer_es")) {
					myLogger.info("AAAAAAAAAAAAAAAAAAAA MASUK 2");

					Vector check_tech = null;
					check_tech = logic.senarai_tech_team(user_negeri_login,
							role, db1);
					if (check_tech.size() != 0) {
						for (int i = 0; i < check_tech.size(); i++) {
							Hashtable get_tech = (Hashtable) check_tech.get(i);
							String user_name = (String) get_tech
									.get("user_name");
							String user_id_terima = (String) get_tech
									.get("user_id");
							myLogger.info("user_name TECH TEAM :"
									+ user_name.toUpperCase());
							String id_esnotifikasi = "";
							check_notifikasi = logic.getListNotifikasi(
									id_esaduan, jenis_comment, user_id_terima,
									"", db1);
							if (check_notifikasi.size() != 0) {
								Hashtable get_notifikasi = (Hashtable) check_notifikasi
										.get(0);
								id_esnotifikasi = (String) get_notifikasi
										.get("id_esnotifikasi");
							}

							if (!user_id_login.equals(user_id_terima)) {
								r.clear();
								if (!id_esnotifikasi.equals("")) {
									r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
								}
								r.add("FLAG_NOTIFIKASI", jenis_comment);
								r.add("ID_USER_NOTIFIKASI_HANTAR",
										user_id_login);
								r.add("ID_USER_NOTIFIKASI_TERIMA",
										user_id_terima);

								id_penerima_array.add(user_id_terima);
								maklumbalas_emel = maklumbalas;
								id_esaduan_emel = id_esaduan;
								id_penghantar_emel = user_id_login;

								r.add("ID_ESADUAN", id_esaduan);
								r.add("FLAG_READ", "NO");
								r.add("ID_MASUK", user_id_login);
								r.add("ID_KEMASKINI", user_id_login);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								if (!id_esnotifikasi.equals("")) {
									sql1 = r.getSQLUpdate("TBLESNOTIFIKASI",
											db1);
								} else {
									sql1 = r.getSQLInsert("TBLESNOTIFIKASI",
											db1);
								}
								stmt.executeUpdate(sql1);

							}
						}
					}
				}
				/*
				 * else { id_penerima_array.add(user_id_login); maklumbalas_emel
				 * = maklumbalas; id_esaduan_emel = id_esaduan;
				 * id_penghantar_emel = user_id_login; }
				 */
				// /hantarEmel(id_penerima_array,session,jenis_comment,"","","",maklumbalas_emel,"",id_esaduan_emel,"",id_penghantar_emel,db1);
				/*
				 * String comment_sebelum1 = ""; Vector listComment_aduan1 =
				 * null; if(!id_esaduan_emel.equals("") &&
				 * !id_penghantar_emel.equals("")) { listComment_aduan1 =
				 * logic.senarai_comment_aduan
				 * (id_esaduan_emel,jenis_comment,db1);
				 * if(listComment_aduan1.size()!=0){ comment_sebelum1 +=
				 * "<table width='100%'>"; for(int i = 0;i <
				 * listComment_aduan1.size();i++) { comment_sebelum1 += "<tr>";
				 * Hashtable get_pengcomment = (Hashtable)
				 * listComment_aduan1.get(i); String nama_user =
				 * (String)get_pengcomment.get("nama_user_init"); String comment
				 * = (String)get_pengcomment.get("maklumbalas"); if ( (i % 2) !=
				 * 1 ) { comment_sebelum1 += "<td colspan='2'><i>"+nama_user
				 * +": \""+comment+"\"</i></td>"; } else { comment_sebelum1 +=
				 * "<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><i>"
				 * +nama_user +": \""+comment+"\"</i></td>"; }
				 * 
				 * comment_sebelum1 += "<tr>"; } comment_sebelum1 += "</table>";
				 * } }
				 * hantarEmel(id_penerima_array,session,jenis_comment,"","",""
				 * ,maklumbalas_emel
				 * ,"",id_esaduan_emel,"",id_penghantar_emel,comment_sebelum1
				 * ,db1);
				 */
			}

			else if (jenis_comment.equals("2"))// untuk maklumabalas teknikal
			{
				// team teknikal sahaja yang dapat notification ni

				if (role.equals("admin_es") || role.equals("developer_es")) {
					myLogger.info("AAAAAAAAAAAAAAAAAAAA MASUK 3");

					Vector check_tech = null;
					check_tech = logic.senarai_tech_team(user_negeri_login,
							role, db1);
					if (check_tech.size() != 0) {
						for (int i = 0; i < check_tech.size(); i++) {
							Hashtable get_tech = (Hashtable) check_tech.get(i);
							String user_name = (String) get_tech
									.get("user_name");
							String user_id_terima = (String) get_tech
									.get("user_id");
							myLogger.info("user_name TECH TEAM :"
									+ user_name.toUpperCase());
							String id_esnotifikasi = "";
							check_notifikasi = logic.getListNotifikasi(
									id_esaduan, jenis_comment, user_id_terima,
									"", db1);
							if (check_notifikasi.size() != 0) {
								Hashtable get_notifikasi = (Hashtable) check_notifikasi
										.get(0);
								id_esnotifikasi = (String) get_notifikasi
										.get("id_esnotifikasi");
							}

							if (!user_id_login.equals(user_id_terima)) {
								r.clear();
								if (!id_esnotifikasi.equals("")) {
									r.update("ID_ESNOTIFIKASI", id_esnotifikasi);
								}
								r.add("FLAG_NOTIFIKASI", jenis_comment);
								r.add("ID_USER_NOTIFIKASI_HANTAR",
										user_id_login);
								r.add("ID_USER_NOTIFIKASI_TERIMA",
										user_id_terima);

								id_penerima_array.add(user_id_terima);
								maklumbalas_emel = maklumbalas;
								id_esaduan_emel = id_esaduan;
								id_penghantar_emel = user_id_login;

								r.add("ID_ESADUAN", id_esaduan);
								r.add("FLAG_READ", "NO");
								r.add("ID_MASUK", user_id_login);
								r.add("ID_KEMASKINI", user_id_login);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
								if (!id_esnotifikasi.equals("")) {
									sql1 = r.getSQLUpdate("TBLESNOTIFIKASI",
											db1);
								} else {
									sql1 = r.getSQLInsert("TBLESNOTIFIKASI",
											db1);
								}
								stmt.executeUpdate(sql1);

							}
						}
					}
				}
			}

			myLogger.info("XXX jenis_comment :" + jenis_comment);
			myLogger.info("XXX id_esaduan_emel :" + id_esaduan_emel);
			myLogger.info("XXX id_penghantar_emel :" + id_penghantar_emel);

			String comment_sebelum = "";
			Vector listComment_aduan = null;
			if (!id_esaduan_emel.equals("") && !id_penghantar_emel.equals("")) {
				listComment_aduan = logic.senarai_comment_aduan(
						id_esaduan_emel, jenis_comment, db1);
				if (listComment_aduan.size() != 0) {
					comment_sebelum += "<table width='100%'>";
					for (int i = 0; i < listComment_aduan.size(); i++) {
						comment_sebelum += "<tr>";
						Hashtable get_pengcomment = (Hashtable) listComment_aduan
								.get(i);
						String nama_user = (String) get_pengcomment
								.get("nama_user_init");
						String comment = (String) get_pengcomment
								.get("maklumbalas");
						if ((i % 2) != 1) {
							comment_sebelum += "<td colspan='2'><i>"
									+ nama_user + ": \"" + comment
									+ "\"</i></td>";
						} else {
							comment_sebelum += "<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td><i>"
									+ nama_user
									+ ": \""
									+ comment
									+ "\"</i></td>";
						}

						comment_sebelum += "<tr>";
					}
					comment_sebelum += "</table>";
				}

				hantarEmel(id_penerima_array, session, jenis_comment, "", "",
						"", maklumbalas_emel, "", id_esaduan_emel, "",
						id_penghantar_emel, comment_sebelum, db1);
			}

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
			// if (conn != null)
			// conn.close();
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}

	}

	private void updateNotification(HttpSession session, String id_esaduan,
			String id_user, Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String sql1 = "";
		Vector check_notifikasi = null;
		try {
			if (db == null) {
				db1 = new Db();
			} else {
				db1 = db;
			}
			conn = db1.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_ESADUAN", id_esaduan);
			r.update("ID_USER_NOTIFIKASI_TERIMA", id_user);
			r.add("ID_USER_NOTIFIKASI_TERIMA", id_user);
			r.add("ID_ESADUAN", id_esaduan);
			r.add("FLAG_READ", "YES");
			r.add("ID_KEMASKINI", id_user);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql1 = r.getSQLUpdate("TBLESNOTIFIKASI", db);
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
			// if (conn != null)
			// conn.close();
			if (db == null) {
				if (db1 != null)
					db1.close();
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void uploadFiles(Db db) throws Exception {

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
				saveData(item, db);
			}
		}
	}

	private void saveData(FileItem item, Db db) throws Exception {
		HttpSession session = request.getSession();
		// Db db = null;
		try {
			long id_esdokumen = DB.getNextID(db, "TBLESDOKUMEN_SEQ");
			// db = new Db();
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
			// if (db != null) db.close();
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
			String id_negeri, String kod_modul, Db db) throws DbException {
		return getSeqNoAduan(tahun, id_negeri, kod_modul, db);
	}

	public static synchronized int getSeqNoAduan(String tahun,
			String id_negeri, String kod_modul, Db db) throws DbException {

		// Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			// db = new Db();
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
				increaseSeqAduan(tahun, id_negeri, kod_modul, db);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNewAduan(tahun, id_negeri, kod_modul, db);
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
			/*
			 * if (db != null) db.close();
			 */
		}

		return seqno;
	}

	public static void increaseSeqAduan(String tahun, String id_negeri,
			String kod_modul, Db db) throws DbException {

		// Db db = null;

		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE TBLRUJSEQESNOADUAN  SET ");
		sb.append("no_turutan = no_turutan + 1 ");
		sb.append(" WHERE ");
		sb.append(" TAHUN = '" + tahun + "'");
		sb.append(" AND id_negeri = '" + id_negeri + "'");
		sb.append(" AND kod_modul = '" + kod_modul + "'");

		try {
			// db = new Db();
			try {
				db.getStatement().executeUpdate(sb.toString());
			} catch (SQLException x) {
				x.printStackTrace();
			}
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static void addNewAduan(String tahun, String id_negeri,
			String kod_modul, Db db) throws DbException {

		// Db db = null;
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO TBLRUJSEQESNOADUAN (TAHUN,ID_NEGERI,KOD_MODUL,NO_TURUTAN)");
		sb.append(" VALUES (");
		sb.append("'" + tahun + "',");
		sb.append("'" + id_negeri + "',");
		sb.append("'" + kod_modul + "'");
		sb.append(",1)"); // initial value

		try {
			// db = new Db();
			db.getStatement().executeUpdate(sb.toString());
		} catch (Exception ex) {
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			// if (db != null)
			// db.close();
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
			String user_id_penerima, String user_id_hantar, String commentlama,
			Db db) throws Exception {
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
		myLogger.info("id_esaduan --- " + id_esaduan);
		if (!id_esaduan.equals("")) {
			list_aduan = logic.getAduan("", id_esaduan, "", role, "", "", "", "",
					"", "", "", "", "", "", "","", db);
			myLogger.info(" list_aduan --- " + list_aduan);
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
					"", "", db);
			if (list_users_hantar.size() != 0) {
				get_user_hantar = (Hashtable) list_users_hantar.get(0);
			}
		}

		EmailProperty pro = EmailProperty.getInstance();
		String EMAIL_HOST = pro.getHost();

		// EmailSenderFLMS email = EmailSenderFLMS.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();
		/*
		 * email.FROM = "appmailer@ilaunch.com.my";
		 * //"etappsupport@jkptg.gov.my";//email_from;
		 * if(EMAIL_HOST.equals("smtp.gmail.com")){ email.FROM =
		 * "etapp.pla@gmail.com"; }else
		 * if(EMAIL_HOST.equals("mail.hla-group.com")){ email.FROM =
		 * "etappsupport@hla-group.com"; }else{ email.FROM =
		 * "appmailer@ilaunch.com.my";
		 * //"etappsupport@jkptg.gov.my";//email_from; }
		 */
		// myLogger.info("LOG ADUAN EMEL :"+(String)get_aduan.get("log_aduan"));

		if (jenis_emel.equals("1")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Komen Log)";
		} else if (jenis_emel.equals("2")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Komen Teknikal)";
		} else if (jenis_emel.equals("3")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Agihan Tugas)";
		} else if (jenis_emel.equals("4")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
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

			listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan, db);
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

			// myLogger.info("check_status:"+check_status);
			// myLogger.info("check_nama_status :"+check_nama_status);
			// myLogger.info("check_content :"+check_content);
			// myLogger.info("check_id_sumber :"+check_id_sumber);
			// myLogger.info("check_nama_sumber :"+check_nama_sumber);

			if (check_content.equals("permohonan_baru_kutipan_data")) {
				if (!check_id_sumber.equals("")
						&& !check_id_sumber.equals("16101")) {
					ayat_sedap = "Aduan ini baru didaftarkan melalui modul kutipan data PLA, segala maklumat yang dipaparkan"
							+ " adalah bedasar dari aduan oleh pihak pengadu melalui sumber <b>"
							+ check_nama_sumber + "</b>. ";
				} else {
					ayat_sedap = "Aduan ini baru didaftarkan melalui modul kutipan data PLA. ";
				}

				if (check_status.equals("16122")) {
					ayat_sedap += "Aduan ini masih lagi didalam semakan pihak kami. "
							+ "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
				}
				if (check_status.equals("16123")) {
					ayat_sedap += "Aduan ini telah diselesaikan. Terus tekun menggunakan MyeTaPP dan jika menghadapi masalah sila hubungi kami.";

				}

				if (check_status.equals("16121")) {
					ayat_sedap += "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";

				}

			}

			if (check_content.equals("update_status_permohonan")) {
				if (!role.equals("admin_es") && !role.equals("developer_es")) {
					if (check_status.equals("16121")) {
						ayat_sedap += "Log ini baru didaftarkan melalui MyeTaPP. Sila ambil tindakan. ";

					}
				} else {
					if (check_status.equals("16122")) {
						ayat_sedap += "Log ini telah kami terima dan masih didalam semakan. "
								+ "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
					}
					if (check_status.equals("16123")) {
						ayat_sedap += "Aduan ini telah diselesaikan. Terus tekun menggunakan MyeTaPP dan jika menghadapi masalah sila hubungi kami.";

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
						(String) get_aduan.get("nama_status_init"),
						(String) get_aduan.get("nama_kementerian"),
						(String) get_aduan.get("nama_agensi"),
						(String) get_aduan.get("id_seksyen"), 
						(String) get_aduan.get("no_fail")) + siapa_agih
				+ emel_html_msg
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
						.toString(), db);
				myLogger.info("DISPLAY EMEL PENERIMA :"
						+ emel.get("emel").toString());
				if (!emel.get("emel").toString().equals("")
						&& !emel.get("emel").toString().equals(null)) {
					email.MULTIPLE_RECIEPIENT[i] = emel.get("emel").toString();
				} else {
					email.MULTIPLE_RECIEPIENT[i] = "etapp.pla@gmail.com";
				}

			}

			if (jenis_emel.equals("4")) {
				Hashtable hUser = logic.get_user_emel(userId, db);
				// Hashtable hUser = getIUser().getPengguna(userId);
				email.TO_CC = new String[1];
				// email.TO_CC[0] = "etappsupport@kptg.gov.my";
				// email.TO_CC[0] = pro.getAduanCc();
				email.TO_CC[0] = !String.valueOf(hUser.get("emel")).equals("") ? String
						.valueOf(hUser.get("emel")) : get_aduan.get("emel")
						.toString();
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
		bff.append("<td  >Nota : Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.</td>");
		bff.append("</tr>");
		bff.append("</table>");

		return bff.toString();
	}

	public static String setMaklumatAduan(String log, String jenis_emel,
			String id_negeri, String nama_pengadu, String nama_pejabat,
			String aduan_hantar, String nama_modul, String aduan,
			String nama_status, String nama_kementerian, String nama_agensi,
			String id_seksyen, String no_fail) {

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
		if (!nama_pejabat.equals("") && nama_kementerian.equals("")) {
			bff.append("<tr>");
			bff.append("<td >&nbsp;</td>");
			bff.append("<td valign='top'><b>Pejabat</b></td>");
			bff.append("<td valign='top' >:</td>");
			bff.append("<td valign='top' >" + nama_pejabat + "</td>");
			bff.append("<td ></td>");
			bff.append("</tr>");
		}
		if (!nama_kementerian.equals("")) {
			bff.append("<tr>");
			bff.append("<td >&nbsp;</td>");
			bff.append("<td valign='top'><b>Kementerian</b></td>");
			bff.append("<td valign='top' >:</td>");
			bff.append("<td valign='top' >" + nama_kementerian + "</td>");
			bff.append("<td ></td>");
			bff.append("</tr>");
		}
		if (!nama_agensi.equals("")) {
			bff.append("<tr>");
			bff.append("<td >&nbsp;</td>");
			bff.append("<td valign='top'><b>Agensi</b></td>");
			bff.append("<td valign='top' >:</td>");
			bff.append("<td valign='top' >" + nama_agensi + "</td>");
			bff.append("<td ></td>");
			bff.append("</tr>");
		}
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
		/* diba tambah no fail */
		 bff.append("<tr>");
		bff.append("<td >&nbsp;</td>");
		bff.append("<td valign='top' ><b>No Fail</b></td>");
		bff.append("<td valign='top' >:</td>");
		bff.append("<td valign='top' >" + no_fail + "</td>");
		bff.append("<td ></td>");
		bff.append("</tr>");
		 /* end */
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

	/*
	 * private IUserPegawai getIUser(){ if(iUser==null){ iUser = new UserBean();
	 * } return iUser;
	 * 
	 * }
	 */
	public void hantarEmelAduan(String pengadu, String user_id_login,
			ArrayList id_penerima_array, HttpSession session,
			String jenis_emel, String emel_hantar, String emel_terima,
			String subjek, String content, String id_negeri, String id_esaduan,
			String user_id_penerima, String user_id_hantar, String commentlama,
			Db db) throws Exception {
		myLogger.info("id_esaduan emel : " + id_esaduan);

		Vector list_aduan = null;
		Hashtable get_aduan = null;
		Vector list_users_hantar = null;

		Hashtable get_user_hantar = null;
		// maklumat Aduan
		if (!id_esaduan.equals("")) {
			list_aduan = logic.getAduan("", id_esaduan, "", role, "", "", "", "",
					"", "", "", "", "", "","", "", db);
			if (list_aduan.size() != 0) {
				get_aduan = (Hashtable) list_aduan.get(0);
			}
		}

		if (!user_id_hantar.equals("")) {
			// Maklumat User Penerima
			list_users_hantar = logic.getListUsers(user_id_hantar, "", "", "",
					"", "", db);
			if (list_users_hantar.size() != 0) {
				get_user_hantar = (Hashtable) list_users_hantar.get(0);
			}
		}

		EmailProperty pro = EmailProperty.getInstance();
		String EMAIL_HOST = pro.getHost();

		EmailSender email = EmailSender.getInstance();

		// email.FROM = "appmailer@ilaunch.com.my";
		// //"etappsupport@jkptg.gov.my";//email_from;
		// myLogger.info("LOG ADUAN EMEL :"+(String)get_aduan.get("log_aduan"));

		if (jenis_emel.equals("1")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Komen Log)";
		} else if (jenis_emel.equals("2")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Komen Teknikal)";
		} else if (jenis_emel.equals("3")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Agihan Tugas)";
		} else if (jenis_emel.equals("4")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Status Log : "
					+ (String) get_aduan.get("nama_status_init") + ")";
		} else if (jenis_emel.equals("5")) {
			email.SUBJECT = "PLA: " + (String) get_aduan.get("log_aduan")
					+ " (Notifikasi : Log Bejaya Dihantar)";
		}

		// comment maklumbalas aduan
		String emel_html_intro = "";
		String emel_html_msg = "";
		String siapa_agih = "";
		Vector listAgihan_aduan = null;
		myLogger.info(" ::::::: jenis_emel : " + jenis_emel);
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

		else if (jenis_emel.equals("2")) {
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

			listAgihan_aduan = logic.senarai_agihan_aduan(id_esaduan, db);
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

		else if (jenis_emel.equals("3")) {
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

		else if (jenis_emel.equals("4")) {
			String check_status = (String) get_aduan.get("id_status");
			String check_nama_status = (String) get_aduan
					.get("nama_status_init");
			String check_content = content;
			String check_id_sumber = (String) get_aduan.get("id_sumberaduan");
			String check_nama_sumber = (String) get_aduan
					.get("nama_sumber_init");
			String ayat_sedap = "";

			if (check_content.equals("permohonan_baru_kutipan_data")) {
				if (!check_id_sumber.equals("")
						&& !check_id_sumber.equals("16101")) {
					ayat_sedap = "Aduan ini baru didaftarkan melalui modul kutipan data PLA, segala maklumat yang dipaparkan"
							+ " adalah bedasar dari aduan oleh pihak pengadu melalui sumber <b>"
							+ check_nama_sumber + "</b>. ";
				} else if (check_status.equals("16122")) {
					ayat_sedap += "Aduan ini masih lagi didalam semakan pihak kami. "
							+ "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
				} else if (check_status.equals("16123")) {
					ayat_sedap += "Aduan ini telah diselesaikan. Terus tekun menggunakan MyeTaPP dan jika menghadapi masalah sila hubungi kami.";
				}

				else if (check_status.equals("16121")) {
					ayat_sedap += "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
				} else {
					ayat_sedap = "Aduan ini baru didaftarkan melalui modul kutipan data PLA. ";
				}
			}

			if (check_content.equals("update_status_permohonan")) {
				if (!role.equals("admin_es") && !role.equals("developer_es")) {
					if (check_status.equals("16121")) {
						ayat_sedap += "Log ini baru didaftarkan melalui MyeTaPP. Sila ambil tindakan. ";
					}
				} else {
					if (check_status.equals("16122")) {
						ayat_sedap += "Log ini telah kami terima dan masih didalam semakan. "
								+ "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
					} else if (check_status.equals("16123")) {
						ayat_sedap += "Aduan ini telah diselesaikan. Terus tekun menggunakan MyeTaPP dan jika menghadapi masalah sila hubungi kami.";
					}
				}
			}

			emel_html_intro += " "
					+ "<table width='100%' border='0'><tr><td width='5%' >&nbsp;</td><td  width='95%' >"
					+ ayat_sedap + "</td></tr></table>" + "<br><br>";

			emel_html_msg += "";
		}

		else if (jenis_emel.equals("5")) {
			String check_status = (String) get_aduan.get("id_status");
			String check_nama_status = (String) get_aduan
					.get("nama_status_init");
			String check_content = content;
			String check_id_sumber = (String) get_aduan.get("id_sumberaduan");
			String check_nama_sumber = (String) get_aduan
					.get("nama_sumber_init");
			String ayat_sedap = "";

			//if (check_content.equals("permohonan_baru_kutipan_data")) {
				/*
				 * if(!check_id_sumber.equals("") &&
				 * !check_id_sumber.equals("16101")){ ayat_sedap =
				 * "Aduan ini baru didaftarkan melalui modul kutipan data PLA, segala maklumat yang dipaparkan"
				 * +
				 * " adalah bedasar dari aduan oleh pihak pengadu melalui sumber <b>"
				 * +check_nama_sumber+"</b>. "; }else{ ayat_sedap =
				 * "Aduan ini baru didaftarkan melalui modul kutipan data PLA. "
				 * ; }
				 * 
				 * if(check_status.equals("16122")){ ayat_sedap +=
				 * "Aduan ini masih lagi didalam semakan pihak kami. " +
				 * "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan."
				 * ; } if(check_status.equals("16123")){ ayat_sedap +=
				 * "Aduan ini telah diselesaikan. Terus tekun menggunakan MyeTaPP dan jika menghadapi masalah sila hubungi kami."
				 * ; }
				 */
				if (check_status.equals("16121")) {
					myLogger.info("CHECK NAK RETURN TO PENGADU : "+check_status);
					ayat_sedap += "Log berjaya dihantar. Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan.";
				}
			//}
			/*
			 * if(check_content.equals("update_status_permohonan")){
			 * if(!role.equals("admin_es") && !role.equals("developer_es")){
			 * if(check_status.equals("16121")){ ayat_sedap +=
			 * "Log ini baru didaftarkan melalui eTaPP. Sila ambil tindakan. ";
			 * } }else{ if(check_status.equals("16122")){ ayat_sedap +=
			 * "Log ini telah kami terima dan masih didalam semakan. " +
			 * "Pihak pengadu akan dimaklumkan perkembangannya setelah perkara ini disemak dan diselesaikan."
			 * ; } if(check_status.equals("16123")){ ayat_sedap +=
			 * "Aduan ini telah diselesaikan. Terus tekun menggunakan MyeTaPP dan jika menghadapi masalah sila hubungi kami."
			 * ; } } }
			 */
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
						(String) get_aduan.get("nama_status_init"),
						(String) get_aduan.get("nama_kementerian"),
						(String) get_aduan.get("nama_agensi"),
						(String) get_aduan.get("id_seksyen"), 
						(String) get_aduan.get("no_fail")) + siapa_agih
				+ emel_html_msg
				+ setFooter(jenis_emel, (String) get_aduan.get("id_negeri"));

		Hashtable hUser = logic.get_user_emel(userId, db);
		String emel_user = "";
		int cnt_cc_ppk = 1;
		if (hUser.size() > 0) {
			emel_user = (String) hUser.get("emel");
		}

		if (emel_user.equals("")) {
			emel_user = get_aduan.get("emel").toString();
		}

		if (jenis_emel.equals("5")) {
			myLogger.info("REPLY TO PENGADU : "+emel_user);
			email.MULTIPLE_RECIEPIENT = new String[1];
			email.MULTIPLE_RECIEPIENT[0] = emel_user;
		} else {
			if (id_penerima_array.size() > 0) {
				String inputLine = "";
				email.MULTIPLE_RECIEPIENT = new String[id_penerima_array.size()];
				for (int i = 0; i < id_penerima_array.size(); i++) {
					inputLine = id_penerima_array.get(i).toString();
					myLogger.info("CHECK SIZE" + id_penerima_array.size()
							+ " ID_USER_TERIMA :"
							+ id_penerima_array.get(i).toString());
					Hashtable emel = logic.get_user_emel(
							id_penerima_array.get(i).toString(), db);
					myLogger.info("DISPLAY EMEL PENERIMA :"
							+ emel.get("emel").toString());
					if (!emel.get("emel").toString().equals("")
							&& !emel.get("emel").toString().equals(null)) {
						email.MULTIPLE_RECIEPIENT[i] = emel.get("emel")
								.toString();
					} else {
						email.MULTIPLE_RECIEPIENT[i] = pro.getFrom();
					}
				}

				if (jenis_emel.equals("1") || jenis_emel.equals("4")) { //untuk cc pusaka status komen log atau selesai
					String check_status = (String) get_aduan.get("id_status");

					Vector senarai_ppk_hq_team = null;
					int cnt_senarai_ppk_hq_team = 0;
					String id_jenismodulesaduan = get_aduan.get(
							"id_jenismodulesaduan").toString();
					if (id_jenismodulesaduan.equals("16121")
							&& check_status.equals("16121")) {
						senarai_ppk_hq_team = logic.senarai_ppk_hq_team(db);
						cnt_senarai_ppk_hq_team = senarai_ppk_hq_team.size();
					}
					myLogger.info("cnt_senarai_ppk_hq_team : "
							+ cnt_senarai_ppk_hq_team);
					 myLogger.info(" pro.getFrom()  :  "+pro.getFrom()+" emel check : "+emel_user);
					email.TO_CC = new String[1 + cnt_senarai_ppk_hq_team];
					email.TO_CC[0] = pro.getFrom();
					// email.TO_CC[1] = emel_user;

					// open hantar ke team PPK
					if (cnt_senarai_ppk_hq_team > 0) {
						for (int i = 0; i < cnt_senarai_ppk_hq_team; i++) {
							Hashtable get_senarai_ppk_hq_team = (Hashtable) senarai_ppk_hq_team
									.get(i);
							String ppk_emel = (String) get_senarai_ppk_hq_team.get("emel");
							//String ppk_emel = "simple1001plan@gmail.com";
							myLogger.info(i + "emel_user : " + ppk_emel);
							email.TO_CC[i + 1] = ppk_emel; //diba open
						}
					}
					// open hantar ke team PPK - log baru dan komen log
					
					//login 900706145583
					//password admin123

				}

			}
		}
				
		email.sendEmail();

	}


}
