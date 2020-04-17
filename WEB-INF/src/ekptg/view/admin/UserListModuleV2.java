package ekptg.view.admin;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.app.RoleProcessor;
import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.portal.db.RegisterUser;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;
import ekptg.view.admin.utils.AgamaInformation;
import ekptg.view.admin.utils.BandarInformation;
import ekptg.view.admin.utils.BangsaInformation;
import ekptg.view.admin.utils.Converter;
import ekptg.view.admin.utils.JawatanInformation;
import ekptg.view.admin.utils.PejabatInformation;
import ekptg.view.admin.utils.USER_CSS;
import ekptg.view.admin.utils.UsersInternal;
import ekptg.view.admin.utils.UsersInternalDetail;
import ekptg.view.admin.utils.UsersKJP;
import ekptg.view.admin.utils.UsersListConnectionToDB;
import ekptg.view.admin.utils.UsersOnline;
import ekptg.view.admin.utils.UsersOnlineDetail;

public class UserListModuleV2 extends AjaxBasedModule {

	private final UsersListConnectionToDB ULCD = new UsersListConnectionToDB();
	private final Hashtable<?, ?> conProp = new Hashtable<Object, Object>();
	static Logger myLogger = Logger.getLogger(UserListModuleV2.class);

	@Override
	public String doTemplate2() throws Exception {
		String template = "vtl/users/user_list.jsp";

		String submit = getParam("command");
		HttpSession session = this.request.getSession();

		myLogger.debug("COMMAND v2: " + submit);
		if (submit.equals("detail")) {
			context.put("kemasMsg", false);
			String user_type = getParam("JenisPengguna");
			if (user_type.equalsIgnoreCase("ONLINE")) {
				template = "vtl/users/user_detail.jsp";
				doDetailOnline(session);
			} else if (user_type.equalsIgnoreCase("INTERNAL")) {
				template = "vtl/users/user_detail2.jsp";
				doDetailInternal(session);
			} else if (user_type.equalsIgnoreCase("KJP")) {
				template = "vtl/users/user_detail_KJP.jsp";
				doDetailKJP(session);
			}
		} else if (submit.equals("kemaskini")) {
			String user_type = getParam("JenisPengguna");
			myLogger.debug("JenisPengguna:" + user_type);
			if (user_type.equalsIgnoreCase("ONLINE")) {
				template = "vtl/users/user_kemaskini.jsp";
				doKemaskini(session);
			} else if (user_type.equalsIgnoreCase("INTERNAL")) {
				template = "vtl/users/user_kemaskini2.jsp";
				doKemaskini2(session);
			} else if (user_type.equalsIgnoreCase("KJP")) {
				template = "vtl/users/user_kemaskini3.jsp";
				doKemaskini3(session);
				// doKemaskini3(session);
			}
		} else if (submit.equals("batal")) {
			context.put("kemasMsg", false);
			template = "vtl/users/user_detail.jsp";
			doDetailOnline(session);
		} else if (submit.equals("batal2")) {
			context.put("kemasMsg", false);
			template = "vtl/users/user_detail2.jsp";
			doDetailInternal(session);
		} else if (submit.equals("batal3")) {
			context.put("kemasMsg", false);
			template = "vtl/users/user_list_kjp.jsp";
			String socKementerian = "", socAgensi = "";
			String selectSearch = (String) context.get("selectSearch");
			String action = getParam("action");
			socKementerian = (String) context.get("socKementerian");
			socAgensi = (String) context.get("socAgensi");

			List<UsersKJP> listUsers = ULCD.listUsersKJP(selectSearch,
					user.toLowerCase(), socKementerian, socAgensi);
			setupPage(session, action, listUsers);
		} else if (submit.equals("deleteUser")) {
			context.put("kemasMsg", false);
			deleteUser(session);
			String selectJenisPengguna = getParam("selectJenisPengguna");
			if ("KJP".equals(selectJenisPengguna)) {
				template = "vtl/users/user_list_kjp.jsp";
			}
			doJob(session, submit);
		} else if (submit.equals("simpanDataOnilne")) {
			template = "vtl/users/user_detail.jsp";
			doKemaskiniOnline(session);
			// context.put("listUser", "");
			doDetailOnline(session);
			// return template;
		} else if (submit.equals("simpanDataInternal")) {
			template = "vtl/users/user_detail2.jsp";
			doKemaskiniInternal(session);
			// context.put("listUser", "");
			doDetailInternal(session);
			// return template;
		} else if (submit.equals("updateDataKJP")) {
			template = "vtl/users/user_detail_KJP.jsp";
			doKemaskiniKJP(session);
			// context.put("listUser", "");
			doKemaskini3(session);
			// return template;
		} else {
			String selectJenisPengguna = getParam("selectJenisPengguna");
			if ("KJP".equals(selectJenisPengguna)) {
				template = "vtl/users/user_list_kjp.jsp";
			}
			doJob(session, submit);
			// return template;
		}
		myLogger.debug("template:" + template);
		// doJob(session);
		return template;
	}

	private void doJob(HttpSession session, String submit) throws Exception {

		if (submit.equals("")) {
			context.put("listUsers", false);
			context.put("totalUsers", "0");
		}

		String selectKementerian = "", selectAgensi = "";
		String socKementerian = "", socAgensi = "";
		String user = getParam("user");
		String selectJenisPengguna = getParam("selectJenisPengguna");
		String selectSearch = getParam("searchType");
		String id_seksyen = getParam("id_seksyen");
		String id_negeri = getParam("id_negeri");
		String id_pejabatjkptg = getParam("id_pejabatjkptg");
		String id_jawatan = getParam("id_jawatan");
		String user_role = selectRole(getParam("user_role"));
		String action = getParam("action");
		String orderBy = getParam("orderBy");

		context.put("kemasMsg", false);
		context.put("submit", submit);
		context.put("user", user);
		context.put("id_seksyen", id_seksyen);
		context.put("id_negeri", id_negeri);
		context.put("selectJenisPengguna", selectJenisPengguna);
		context.put("id_pejabatjkptg", id_pejabatjkptg);
		context.put("id_jawatan", id_jawatan);
		context.put("selectSearch", selectSearch);
		context.put("searchType", listSearch());
		context.put("selectRole", user_role);
		context.put("errorMsg", false);
		context.put("listUsers", false);
		context.put("totalUsers", "0");
		context.put("submit", submit);
		context.put("userOnline", "");
		context.put("orderBy", orderBy);

		// List<UserType> listType = ULCD.listType();
		// context.put("listJenis", listType);

		RoleProcessor processor = new RoleProcessor(this.conProp);
		Vector<?> userRoles = processor.getRoles();
		context.put("userRoles", userRoles);

		if (submit.equals("clear")) {
			context.put("submit", "");
			context.put("user", "");
			context.put("id_seksyen", "");
			context.put("id_negeri", "");
			id_negeri = "";
			context.put("selectJenisPengguna", "");
			context.put("id_pejabatjkptg", "");
			context.put("id_jawatan", "");
			context.put("selectSearch", "");
			context.put("selectRole", "");
			context.put("urlLinkImg", "");
			context.put("errorMsg", false);
			context.put("listUsers", false);
			context.put("totalUsers", "0");
			context.put("errorMsg", false);
		} else {
			if (selectJenisPengguna.equals("Silah Pilih Jenis Pengguna")
					&& (!submit.equals("detail"))) {
				context.put("errorMsg", "Sila Pilih Jenis Pengguna!");
			}
		}

		// List<UsersOnline> list = ULCD.listUsers();
		// setupPage(session, action, list);

		if (submit.equals("doChanges")) {
			myLogger.debug("DO CHANGES");
			context.put("listUsers", false);
		} else if (submit.equals("doPaging")) {
			context.put("errorMsg", false);
		} else if (submit.equals("deleteUser")) {
			context.put("errorMsg", false);
		}

		if (action.equals("getPage")) {
			context.put("errorMsg", false);
		}

		if (!("".equals(submit))) {
			context.put(
					"selectJawatan",
					HTML.SelectJawatan("id_jawatan",
							Utils.parseLong(id_jawatan)));
			context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen",
					Utils.parseLong(id_seksyen), null,
					"onChange=\"doChanges()\" "));
			context.put("selectNegeri", HTML.SelectNegeri("id_negeri",
					id_negeri == "" ? null : Utils.parseLong(id_negeri), null,
					"onChange=\"doChanges()\" "));
			context.put(
					"selectPejabatJKPTG",
					SelectPejabatJKPTG("id_pejabatjkptg", id_negeri, null,
							id_seksyen, id_pejabatjkptg, null,
							"onChange=\"doChanges()\" "));

			// KEMENTERIAN
			if ("KJP".equals(selectJenisPengguna)) {
				socKementerian = getParam("socKementerian");
				socAgensi = getParam("socAgensi");
				selectKementerian = HTML.SelectKementerian("socKementerian",
						Utils.parseLong(socKementerian), "",
						"onChange='doChanges();'");
				selectAgensi = HTML.SelectAgensiByKementerian("socAgensi",
						socKementerian, Utils.parseLong(socAgensi), "");
				context.put("selectKementerian", selectKementerian);
				context.put("selectAgensi", selectAgensi);
			}

			if ("carian".equals(submit)) {
				context.put("selectSubmit", "carian");
				myLogger.debug("jenis pengguna:" + selectJenisPengguna);

				String order_field = "";
				if ("ID Pengguna".equals(orderBy)) {
					order_field = "A.USER_LOGIN";
				} else if ("Nama".equals(orderBy)) {
					order_field = "A.USER_NAME";
				} else if ("Negeri".equals(orderBy)) {
					order_field = "B.ID_NEGERI";
				} else if ("Role".equals(orderBy)) {
					order_field = "A.USER_ROLE";
				} else {
					order_field = "A.USER_LOGIN";
				}

				String user1 = user.toLowerCase();

				// ////////////// CARIAN
				if ("INTERNAL".equals(selectJenisPengguna)) {
					// ////////// INTERNAL USER
					if ("NAMA".equals(selectSearch)) {
						List<UsersInternal> listUsers = ULCD
								.listUsersInternalByName(user1.trim(),
										id_jawatan, id_seksyen, id_negeri,
										id_pejabatjkptg, user_role, order_field);
						setupPage(session, action, listUsers);
					} else {
						// BY USER LOGIN
						List<UsersInternal> listUsers = ULCD
								.listUsersInternalById(user1.trim(),
										id_jawatan, id_seksyen, id_negeri,
										id_pejabatjkptg, user_role);
						setupPage(session, action, listUsers);
					}

				} else if ("KJP".equals(selectJenisPengguna)) {
					//
					socKementerian = getParam("socKementerian");
					socAgensi = getParam("socAgensi");
					this.context.put("socKementerian", socKementerian);
					this.context.put("socAgensi", socAgensi);

					List<UsersKJP> listUsers = ULCD.listUsersKJP(selectSearch,
							user1.trim(), socKementerian, socAgensi);
					setupPage(session, action, listUsers);

				} else {
					// /ONLINE USER
					if ("NAMA".equals(selectSearch)) {
						List<UsersOnline> listUsers = ULCD
								.listUsersOnlineByName(user1.trim(), id_negeri,
										user_role);
						setupPage(session, action, listUsers);
					} else {
						List<UsersOnline> listUsers = ULCD.listUsersOnlineById(
								user1.trim(), id_negeri, user_role);
						setupPage(session, action, listUsers);
					}
				}

				// ////////////// END CARIAN

			}
		} else {
			this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen",
					Utils.parseLong(id_seksyen), null,
					"onChange=\"doChanges()\" "));
			this.context
					.put("selectNegeri", HTML.SelectNegeri("id_negeri",
							"onChange=\"doChanges()\" "));
			this.context.put("selectJawatan", HTML.Selectjawatan("id_jawatan"));
			this.context.put("selectPejabatJKPTG",
					SelectPejabatJKPTG("id_pejabatjkptg"));

		}
	}

	private void doDetailOnline(HttpSession session) throws Exception {
		String user_id = getParam("id_user");
		UsersOnlineDetail listUser = ULCD.listUsersOnlineByUserId(user_id);
		context.put("listUser", listUser);
	}

	private void doDetailOnline2(HttpSession session) throws Exception {
		String user_id = getParam("id_user");
		UsersOnlineDetail listUser = ULCD.listUsersOnlineByUserId(user_id);
		context.put("listUser", listUser);
	}

	private void doDetailInternal(HttpSession session) throws Exception {

		Converter converter = new Converter();
		String user_id = getParam("id_user");

		UsersInternalDetail listUser = ULCD.listUsersInternalByUserId(user_id);

		String id_jawatan = "";
		String id_agama = "";
		String id_bandar = "";
		String id_bangsa = "";

		if (listUser != null) {
			id_jawatan = converter.getStringValue(listUser.getId_jawatan());
			id_agama = converter.getStringValue(listUser.getId_agama());
			id_bandar = converter.getStringValue(listUser.getId_bandar());
			id_bangsa = converter.getStringValue(listUser.getId_bangsa());
		}

		if (!id_jawatan.equals("")) {
			JawatanInformation ji = ULCD.listJawatanById(id_jawatan);
			context.put("listJawatan", ji);
		} else {
			context.put("listJawatan", false);
		}

		if (!id_agama.equals("")) {
			AgamaInformation ai = ULCD.listAgamaById(id_agama);
			context.put("listAgama", ai);
		} else {
			context.put("listAgama", false);
		}

		if (!id_bandar.equals("")) {
			BandarInformation bi = ULCD.listBandarById(id_bandar);
			context.put("listBandar", bi);
		} else {
			context.put("listBandar", false);
		}

		if (!id_bangsa.equals("")) {
			BangsaInformation bi = ULCD.listBangsaById(id_bangsa);
			context.put("listBangsa", bi);
		} else {
			context.put("listBangsa", false);
		}

		// UsersOnlineDetail listUser = ULCD.listUsersOnlineByUserId(user_id);
		context.put("listUser", listUser);
	}

	private void doDetailInternal2(HttpSession session) throws Exception {

		Converter converter = new Converter();
		String user_id = getParam("id_user");
		myLogger.debug(user_id);

		// UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
		// UsersInternalDetail listUser =
		// ULCD.listUsersInternalByUserId(user_id);
		UsersInternalDetail listUser = null;

		try {
			listUser = ULCD.listUsersInternalByUserId(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String id_jawatan =
		// converter.getStringValue(listUser.getId_jawatan());
		// String id_agama = converter.getStringValue(listUser.getId_agama());
		// String id_bandar = converter.getStringValue(listUser.getId_bandar());
		// String id_bangsa = converter.getStringValue(listUser.getId_bangsa());
		//
		// if (!id_jawatan.equals("")) {
		// JawatanInformation ji = ULCD.listJawatanById(id_jawatan);
		// context.put("listJawatan", ji);
		// } else {
		// context.put("listJawatan", false);
		// }
		//
		// if (!id_agama.equals("")) {
		// AgamaInformation ai = ULCD.listAgamaById(id_agama);
		// context.put("listAgama", ai);
		// } else {
		// context.put("listAgama", false);
		// }
		//
		// if (!id_bandar.equals("")) {
		// BandarInformation bi = ULCD.listBandarById(id_bandar);
		// context.put("listBandar", bi);
		// } else {
		// context.put("listBandar", false);
		// }
		//
		// if (!id_bangsa.equals("")) {
		// BangsaInformation bi = ULCD.listBangsaById(id_bangsa);
		// context.put("listBangsa", bi);
		// } else {
		// context.put("listBangsa", false);
		// }

		// UsersOnlineDetail listUser = ULCD.listUsersOnlineByUserId(user_id);
		context.put("listUser", listUser);
	}

	private void doDetailKJP(HttpSession session) throws Exception {
		String user_id = getParam("id_user");
		UsersKJP listUser = ULCD.UsersKJPDetail(user_id);
		context.put("listUser", listUser);
	}

	private void deleteUser(HttpSession session) throws Exception {
		String[] user_login = this.request.getParameterValues("users");
		String msg = doDelete(user_login);
		context.put("statusDelete", msg);
		context.put("errorMsg", false);
		myLogger.debug("STATUS (DELETE): " + msg);
		// doJob(session);
	}

	private void doKemaskini(HttpSession session) throws Exception {
		String user_id = getParam("id_user");
		String id_negeri = getParam("id_negeri");
		String user_type = getParam("JenisPengguna");
		String user_role = getParam("user_role");
		String selectJantina = getParam("selectJantina");
		String selectTP = getParam("selectTP");
		String user_login = getParam("user_login");

		List<USER_CSS> sp = ULCD.listPageStyleByUserLogin(user_login);

		UsersOnlineDetail listUser = ULCD.listUsersOnlineByUserId(user_id);

		context.put("selectNegeri", HTML.SelectNegeri("id_negeri",
				id_negeri == "" ? null : Utils.parseLong(id_negeri), null,
				"onChange=\"doChanges()\" "));
		context.put("selectJenisPengguna", user_type);
		context.put("listUser", listUser);
		context.put("searchType", listSearch());
		// context.put("selectRole", user_role.toLowerCase());
		context.put("selectRole", user_role);
		context.put("selectJantina", selectJantina);
		context.put("selectTP", selectTP);
		context.put("listStyle", ULCD.listPageStyle());
		context.put("selectPS", sp);
	}

	private void doKemaskini3(HttpSession session) throws Exception {
		String mode = getParam("mode");
		String user_id = getParam("id_user");
		UsersKJP listUser = ULCD.UsersKJPDetail(user_id);

		if ("doChanges".equals(mode)) {
			String socKementerian = getParam("socKementerian");
			String socAgensi = getParam("socAgensi");
			String selectKementerian = HTML.SelectKementerian("socKementerian",
					Utils.parseLong(socKementerian), "",
					"onChange='doChangesKJP();'");
			String selectAgensi = HTML.SelectAgensiByKementerian("socAgensi",
					socKementerian, Utils.parseLong(socAgensi), "");
			context.put("selectKementerian", selectKementerian);
			context.put("selectAgensi", selectAgensi);
		} else if (listUser != null) {
			String socKementerian = listUser.getId_kementerian();
			String socAgensi = listUser.getId_agensi();
			String selectKementerian = HTML.SelectKementerian("socKementerian",
					Utils.parseLong(socKementerian), "",
					"onChange='doChangesKJP();'");
			String selectAgensi = HTML.SelectAgensiByKementerian("socAgensi",
					socKementerian, Utils.parseLong(socAgensi), "");
			context.put("selectKementerian", selectKementerian);
			context.put("selectAgensi", selectAgensi);
		}
		context.put("listUser", listUser);
	}

	private void doKemaskini2(HttpSession session) throws Exception {
		Converter converter = new Converter();
		String user_id = getParam("id_user");

		UsersInternalDetail listUser = ULCD.listUsersInternalByUserId(user_id);

		String user_role = getParam("user_role");
		String page_style = getParam("page_style");
		String id_seksyen = getParam("id_seksyen") == "" ? listUser
				.getId_seksyen() : getParam("id_seksyen");
		String id_negeri = getParam("id_negeri") == "" ? listUser
				.getId_negeri() : getParam("id_negeri");
		String id_daerah = getParam("id_daerah") == "" ? listUser
				.getId_daerah() : getParam("id_daerah");
		String id_pejabatjkptg = getParam("id_pejabatjkptg") == "" ? listUser
				.getId_pejabat() : getParam("id_pejabatjkptg");
		String id_jawatan = getParam("id_jawatan") == "" ? converter
				.getStringValue(listUser.getId_jawatan())
				: getParam("id_jawatan");
		String id_agama = converter.getStringValue(listUser.getId_agama());
		String id_bandar = converter.getStringValue(listUser.getId_bandar());
		String id_bangsa = converter.getStringValue(listUser.getId_bangsa());

		/*
		 * String selectRole = getParam("user_role") == "" ? listUser
		 * .getUser_role().toLowerCase() : getParam("user_role");
		 */
		String selectRole = getParam("user_role") == "" ? listUser
				.getUser_role() : getParam("user_role");

		String selectPages = getParam("page_style") == "" ? listUser
				.getCss_name2() : getParam("page_style");
		String selectAgama = getParam("selectAgama") == "" ? listUser
				.getId_agama() : getParam("selectAgama");
		String selectBangsa = getParam("selectBangsa") == "" ? listUser
				.getId_bangsa() : getParam("selectBangsa");

		PejabatInformation pi = ULCD.listPejabatById(id_pejabatjkptg);

		if (!id_jawatan.equals("")) {
			JawatanInformation ji = ULCD.listJawatanById(id_jawatan);
			context.put("listJawatan", ji);
		} else {
			context.put("listJawatan", false);
		}

		if (!id_agama.equals("")) {
			AgamaInformation ai = ULCD.listAgamaById(id_agama);
			context.put("listAgama", ai);
		} else {
			context.put("listAgama", false);
		}

		if (!id_bandar.equals("")) {
			BandarInformation bi = ULCD.listBandarById(id_bandar);
			context.put("listBandar", bi);
		} else {
			context.put("listBandar", false);
		}

		if (!id_bangsa.equals("")) {
			BangsaInformation bi = ULCD.listBangsaById(id_bangsa);
			context.put("listBangsa", bi);
		} else {
			context.put("listBangsa", false);
		}

		context.put("selectPages", selectPages);
		context.put("listUser", listUser);
		context.put("listAgama", ULCD.listAgama());
		context.put("listBangsa", ULCD.listBangsa());
		context.put("selectAgama", selectAgama);
		context.put("selectBangsa", selectBangsa);
		this.context.put("user_role", user_role);
		this.context.put("page_style", page_style);
		this.context.put("id_seksyen", id_seksyen);
		this.context.put("id_negeri", id_negeri);
		this.context.put("id_daerah", id_daerah);
		this.context.put("id_pejabatjkptg", id_pejabatjkptg);
		this.context.put("id_jawatan", id_jawatan);
		this.context.put("pejabatInfo", pi);
		this.context.put("selectSeksyen", HTML.SelectSeksyen("id_seksyen",
				Utils.parseLong(id_seksyen), null,
				"class=\"inputField3\" onChange=\"doChanges()\" "));
		this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri",
				id_negeri == "" ? null : Utils.parseLong(id_negeri), null,
				"class=\"inputField9\" onChange=\"doChanges()\" "));
		this.context.put(
				"selectPejabatJKPTG",
				SelectPejabatJKPTG("id_pejabatjkptg", id_negeri, id_daerah,
						id_seksyen, id_pejabatjkptg, null,
						"onChange=\"doChanges()\" "));
		this.context.put("selectJawatan", HTML.SelectJawatan("id_jawatan",
				Utils.parseLong(id_jawatan), null, "class=\"inputField3\""));
		this.context.put("daerahJagaan", getDaerahJagaan(id_pejabatjkptg));
		;
		RoleProcessor processor = new RoleProcessor(this.conProp);
		Vector<?> userRoles = processor.getRoles();
		this.context.put("userRoles", userRoles);
		// this.context.put("selectRole", selectRole.toLowerCase());
		this.context.put("selectRole", selectRole);

		Vector<?> pageStyleList = RegisterUser.getPageStyles();
		this.context.put("pageStyleList", pageStyleList);

	}

	private String doDelete(String[] user_login) {
		String msg = "";
		try {
			if (user_login != null) {
				for (int i = 0; i < user_login.length; i++) {
					String userData = user_login[i];
					// myLogger.debug(i + " - " + userData);
					String seperator = "/";
					String userType = userData.substring(userData
							.lastIndexOf(seperator) + 1);
					String userLogin = userData.substring(0,
							userData.lastIndexOf(seperator));
					if (userType.equalsIgnoreCase("ONLINE")) {
						ULCD.deleteUserOnline(userLogin);
					} else if (userType.equalsIgnoreCase("INTERNAL")) {
						ULCD.deleteUserInternal(userLogin);
					} else if (userType.equalsIgnoreCase("KJP")) {
						myLogger.debug("delete user KJP");
						ULCD.deleteUserKJP(userLogin);
					}
				}
			}
			return msg = "Data berjaya dihapuskan";
		} catch (Exception e) {
			e.printStackTrace();
			return msg = "Data tidak berjaya dihapuskan!";
		}
	}

	private void doKemaskiniOnline(HttpSession session) throws Exception {

		String id_kemaskini = (String) session.getAttribute("_ekptg_user_id");

		String user_id = getParam("id_user");
		String user_login = getParam("id_pengguna");
		String user_name = getParam("user_name");
		String kata_laluan = getParam("user_pass");
		String alamat1 = getParam("alamat1");
		String alamat2 = getParam("alamat2");
		String alamat3 = getParam("alamat3");
		String id_negeri = getParam("id_negeri");
		String poskod = getParam("poskod");
		String emel = getParam("emel");
		String no_hp = getParam("no_cellphone");
		String no_tel = getParam("no_tel");
		String no_fax = getParam("no_faks");
		String kp1 = getParam("kp1");
		String kp2 = getParam("kp2");
		String kp3 = getParam("kp3");
		String kad_pengenalan = kp1 + kp2 + kp3;
		String umur = getParam("umur");
		String jantina = getParam("selectJantina");
		String gander = "";

		if (jantina.equalsIgnoreCase("Sila Pilih Jantina")) {
			gander = "";
		} else {
			gander = jantina;
		}

		String taraf_perkahwinan = getParam("selectTarafPerkahwinan");
		String TP = "";
		if (taraf_perkahwinan.equalsIgnoreCase("Sila Pilih Taraf Perkahwinan")) {
			TP = "";
		} else {
			TP = taraf_perkahwinan;
		}

		String tarikh_lahir = getParam("txdTarikhLahir");

		try {
			ULCD.updateUser(user_id, user_login, kata_laluan, user_name, "",
					id_kemaskini);
			ULCD.updateUserOnline(user_id, alamat1, alamat2, alamat3,
					id_negeri, poskod, emel, no_hp, no_tel, no_fax,
					kad_pengenalan, umur, gander, TP, tarikh_lahir,
					id_kemaskini);
			context.put("kemasMsg", true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void doKemaskiniKJP(HttpSession session) throws Exception {

		String id_kemaskini = (String) session.getAttribute("_ekptg_user_id");

		myLogger.debug("id kemaskini:" + id_kemaskini);

		String user_id = getParam("id_user");
		String user_login = getParam("id_pengguna");
		String user_name = getParam("user_name");
		String user_password = getParam("user_pass");
		String socKementerian = getParam("socKementerian");
		String socAgensi = getParam("socAgensi");

		try {
			ULCD.updateKJP(user_id, user_name, user_login, user_password,
					socKementerian, socAgensi, id_kemaskini);
			context.put("kemasMsg", true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void doKemaskiniInternal(HttpSession session) throws Exception {

		String id_kemaskini = (String) session.getAttribute("_ekptg_user_id");

		String user_id = getParam("id_user");
		String user_login = getParam("id_pengguna");
		String user_name = getParam("user_name");
		String kata_laluan = getParam("user_pass");
		String user_role = getParam("user_role");
		String id_bangsa = getParam("selectBangsa");
		String id_agama = getParam("selectAgama");
		String id_negeri = getParam("id_negeri");
		String id_seksyen = getParam("id_seksyen");
		String id_daerah = getParam("id_daerah");
		String id_jawatan = getParam("id_jawatan");
		String id_pejabatjkptg = getParam("id_pejabatjkptg");
		String emel = getParam("emel");

		try {
			ULCD.updateUser(user_id, user_login, kata_laluan, user_name,
					user_role, id_kemaskini);
			ULCD.updateUserInternal(user_id, id_seksyen, id_negeri, id_daerah,
					id_pejabatjkptg, id_agama, id_bangsa, id_jawatan,
					id_kemaskini, emel);
			context.put("kemasMsg", true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<String> listSearch() throws Exception {
		List<String> data = new ArrayList<String>();
		data.add("NAMA");
		data.add("ID");
		return data;
	}

	private String SelectPejabatJKPTG(String selectName) throws Exception {
		return SelectPejabatJKPTG(selectName, null, null, null, null, null,
				null);
	}

	private String SelectPejabatJKPTG(String selectName, String idNegeri,
			String idDaerah, String idSeksyen, String selectedValue)
			throws Exception {
		return SelectPejabatJKPTG(selectName, idNegeri, idDaerah, idSeksyen,
				selectedValue, null, null);
	}

	private String SelectPejabatJKPTG(String selectName, String idNegeri,
			String idDaerah, String idSeksyen, String selectedValue,
			String disability, String jsFunction) throws Exception {

		if (idNegeri == null)
			idNegeri = "";

		StringBuffer sb = new StringBuffer();
		sb.append("<select name='" + selectName + "'");
		if (disability != null)
			sb.append(disability);
		if (jsFunction != null)
			sb.append(jsFunction);
		sb.append(" class=\"inputField3\" > ");
		sb.append("<option value=>Sila Pilih Pejabat</option>\n");

		Db db = null;
		String selected = "";
		try {
			db = new Db();
			String sql = "select A.ID_PEJABATJKPTG,A.KOD_JKPTG,"
					+ "A.NAMA_PEJABAT "
					+ "AS NAMA_PEJABAT from tblRujPejabatJKPTG A ";
			sql = sql + " WHERE id_negeri='" + idNegeri + "'";

			if (idSeksyen != null && !"".equals(idSeksyen)
					&& !"0".equals(idSeksyen)) {
				sql = sql + " AND id_seksyen='" + idSeksyen + "'";
			}
			sql = sql + " ORDER BY A.KOD_JKPTG";
			Statement stmt = db.getStatement();

			ResultSet rs = stmt.executeQuery(sql);
			String id = "";
			String nama = "";
			String kod = "";
			while (rs.next()) {
				id = rs.getString("ID_PEJABATJKPTG");
				kod = rs.getString("KOD_JKPTG");
				nama = rs.getString("NAMA_PEJABAT");
				if (id.equals(selectedValue))
					selected = "selected";
				else
					selected = "";
				sb.append("<option " + selected + " value=" + id + ">" + kod
						+ " - " + nama + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (db != null)
				db.close();
		}
		return sb.toString();

	}

	@Override
	public void setupPage(HttpSession session, String action, List list) {
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

			Paging2 paging = new Paging2(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1;
			this.context.put("listUsers", paging.getPage(page));
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

	private String selectRole(String txt) throws Exception {
		if (txt.equals("Sila Pilih Role Pengguna")) {
			return "";
		} else
			return txt;

	}

	private Hashtable doGetPejabatJKPTG(String ID_PEJABATJKPTG)
			throws Exception {

		Db db = null;
		Hashtable result = new Hashtable();
		try {
			db = new Db();
			String sql = "select A.ID_PEJABATJKPTG,A.KOD_JKPTG,"
					+ "A.NAMA_PEJABAT,ALAMAT1,ALAMAT2,ALAMAT3,A.ID_DAERAH from tblRujPejabatJKPTG A ";
			sql = sql + " WHERE A.ID_PEJABATJKPTG='" + ID_PEJABATJKPTG + "'";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String address = null;
			while (rs.next()) {
				result.put("id", getValue(rs.getString(1)));
				result.put("kod_jkptg", getValue(rs.getString(2)));
				result.put("nama_pejabat", getValue(rs.getString(3)));
				result.put("alamat1", getValue(rs.getString(4)));
				result.put("alamat2", getValue(rs.getString(5)));
				result.put("alamat3", getValue(rs.getString(6)));
				result.put("id_daerah", getValue(rs.getString(7)));
			}
			address = checkIsNull(result.get("alamat1")) + "\n"
					+ checkIsNull(result.get("alamat2")) + "\n"
					+ checkIsNull(result.get("alamat3"));
			result.put("address", address);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (db != null)
				db.close();
		}

		return result;
	}

	public String checkIsNull(Object txt) {
		if (txt == null)
			return "";
		else
			return (String) txt;
	}

	public String getValue(String txt) throws Exception {
		if (txt == null)
			return "";
		else
			return txt;
	}

	public String getDaerahJagaan(String id_pejabatjkptg) throws Exception {
		Db db = null;
		String sql = "";
		String output = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "Select nama_daerah from tblRujDaerah where "
					+ "id_daerah in (select distinct id_daerahurus "
					+ "from TBLRUJPEJABATURUSAN where id_pejabatjkptg='"
					+ id_pejabatjkptg + "') " + "order by kod_daerah";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				output = output + rs.getString("nama_daerah") + ",";
			}
			if (output.length() > 0)
				output = output.substring(0, output.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	}
}
