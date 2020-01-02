package ekptg.view.admin;

import lebah.portal.AjaxBasedModule;

public class UserListModule extends AjaxBasedModule {

	@Override
	public String doTemplate2() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
//
//	static Logger myLogger = Logger.getLogger(RegisterUserModule.class);
//
//	public String doTemplate2() throws Exception {
//		String tamplate = "vtl/users/user_list_v2.vm";
//		String portal_module = getParam("_portal_module");
//		HttpSession session = this.request.getSession();
//		String submit = getParam("command");
//		System.out.println("COMMAND: -----> " + submit);
//		if (submit.equals("detail")) {
//			return tamplate;
//		} else if (submit.equals("getJawatan")) {
//			String searchData = getParam("search");
//			System.out.println("Search data: ----------- > " + searchData );
//			getJawatan(session);
//			return tamplate;
//		} else if (submit.equals("getPejabat")) {
//			getPejabat(session);
//			return tamplate;
//		} else if (submit.equals("getSeksyen")) {
//			getSeksyen(session);
//			return tamplate;
//		} else if (submit.equals("getNegeri")) {
//			getNegeri(session);
//			return tamplate;
//		} else if (submit.equals("getRole")) {
//			getRole(session);
//			return tamplate;
//		} else if (submit.equals("clear") || portal_module.equals("ekptg.view.admin.UserListModule")) {
//			clearData(session);
//			return tamplate;
//		} else {
//			listUser(session);
//			
//			return tamplate;
//		}
//
//	}
//
//	private void listUser(HttpSession session) throws Exception {
//
//		UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
//		Utils utils = new Utils();
//
//		List<UserType> type = ULCB.listType();
//		//List<Jawatan> jawatan = ULCB.listJawatan();
//		List<UsersRole> role = ULCB.listUserRole();
//
//		String user_role = (String) session.getAttribute("_portal_role");
//		String portal_module = (String) session.getAttribute("_portal_module");
//		String actionUrl = utils.getTabID("User Management", user_role)
//				+ "?_portal_module=" + portal_module;
//
//		context.put("actionUrl", actionUrl);
//		context.put("listType", type);
//		
//		//context.put("listJawatan", jawatan);
//		context.put("listRole", role);
//	}
//
//	private void getJawatan(HttpSession session) throws Exception {
//		String id_Type = getParam("id");
//		System.out.println(id_Type);
//		if (!id_Type.equals("-1")) {
//			if(!id_Type.equals("ONLINE")){
//				UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
//				List<Jawatan> jawatan = ULCB.listJawatan();
//	
//				session.setAttribute("id_type", id_Type);
//	
//				context.put("listJawatan", jawatan);
//				context.put("selectType", id_Type);
//			}
//			else {
//				context.put("listJawatan", "");
//				context.put("selectType", id_Type);
//				context.put("listJawatan", false);
//			}
//		} 
//		else {
//			context.put("listJawatan", "");
//			context.put("selectType", "");
//			context.put("listJawatan", false);
//		}
//
//		listUser(session);
//	}
//
//	private void getSeksyen(HttpSession session) throws Exception {
//		String id_Jawatan = getParam("id");
//
//		if (!id_Jawatan.equals("-1")) {
//			UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
//			List<Seksyen> seksyen = ULCB.listSeksyen();
//			context.put("listSeksyen", seksyen);
//
//			Jawatan jawatan = ULCB.listJawatanById(id_Jawatan);
//			context.put("selectJawatan", jawatan);
//
//			session.setAttribute("select_jawatan", jawatan);
//		} else {
//			context.put("listSeksyen", "");
//			context.put("selectJawatan", "");
//			context.put("listSeksyen", false);
//			context.put("listNegeri", "");
//			context.put("selectSeksyen", "");
//			context.put("listNegeri", false);
//			context.put("listPejabat", "");
//			context.put("selectNegeri", "");
//			context.put("listPejabat", false);
//			context.put("listRole", "");
//			context.put("selectPejabat", "");
//			context.put("listRole", false);
//		}
//		listUser(session);
//	}
//
//	private void getNegeri(HttpSession session) throws Exception {
//		String id_seksyen = getParam("id");
//
//		if (!id_seksyen.equals("-1")) {
//			UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
//			List<Negeri> negeri = ULCB.listNegeri();
//			context.put("listNegeri", negeri);
//
//			Seksyen seksyen = ULCB.listSeksyenById(id_seksyen);
//			context.put("selectSeksyen", seksyen);
//
//			session.setAttribute("selectSeksyen", seksyen);
//		} else {
//			context.put("listNegeri", "");
//			context.put("selectSeksyen", "");
//			context.put("listNegeri", false);
//			context.put("listPejabat", "");
//			context.put("selectNegeri", "");
//			context.put("listPejabat", false);
//		}
//		listUser(session);
//	}
//
//	private void getPejabat(HttpSession session) throws Exception {
//
//		String id_negeri = getParam("id");
//
//		if (!id_negeri.equals("-1")) {
//			UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
//			List<Pejabat> pejabat = ULCB.listPejabat(id_negeri);
//			context.put("listPejabat", pejabat);
//			session.setAttribute("listPejabat", pejabat);
//
//			Negeri selectNegeri = ULCB.listNegeriById(id_negeri);
//			context.put("selectNegeri", selectNegeri);
//		} else {
//			context.put("listPejabat", "");
//			context.put("selectNegeri", "");
//			context.put("listPejabat", false);
//			context.put("listRole", "");
//			context.put("selectPejabat", "");
//			context.put("listRole", false);
//		}
//
//		listUser(session);
//	}
//
//	private void getRole(HttpSession session) throws Exception {
//		String id_Pejabat = getParam("id");
//
//		if (!id_Pejabat.equals("-1")) {
//			UsersListConnectionToDB ULCB = new UsersListConnectionToDB();
//			List<UsersRole> role = ULCB.listUserRole();
//			context.put("listRole", role);
//
//			Pejabat pejabat = ULCB.listPejabatById(id_Pejabat);
//			context.put("selectPejabat", pejabat);
//		} else {
//			context.put("listRole", "");
//			context.put("selectPejabat", "");
//			context.put("listRole", false);
//		}
//		listUser(session);
//	}
//
//	private void clearData(HttpSession session) throws Exception{
//		context.put("listType", "");
//		context.put("selectType", "");
//		context.put("listJawatan", "");
//		context.put("listJawatan", false);
//		context.put("selectJawatan", "");
//		context.put("listSeksyen", "");
//		context.put("listSeksyen", false);
//		context.put("selectSeksyen", "");
//		context.put("listNegeri", "");
//		context.put("listNegeri", false);
//		context.put("selectNegeri", "");
//		context.put("listPejabat", "");
//		context.put("listPejabat", false);
//		context.put("selectPejabat", "");
//		context.put("listRole", false);
//		context.put("listRole", "");
//		
//		session.removeAttribute("id_type");
//		session.removeAttribute("select_jawatan");
//		session.removeAttribute("listNegeri");
//		session.removeAttribute("selectSeksyen");
//		session.removeAttribute("listPejabat");
//		
//		listUser(session);
//	}
}
