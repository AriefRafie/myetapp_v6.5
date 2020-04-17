package ekptg.view.integrasi;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.model.integrasi.FrmModelIntChangePassword;

public class FrmViewIntChangePassword extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String User_NewPass1 = "";
	private String User_NewPass2 = "";
	private String User_CurPass = "";

	private Boolean changePass = null;
	private String userName = "";
	//private String userRole = "";
	private String userID = "";
	
	public String doTemplate2() throws Exception{
		
		String vm = "";
        HttpSession session = this.request.getSession();     
        FrmModelIntChangePassword modelINTChangePass = new FrmModelIntChangePassword();
        
        String action2 = getParam("action2");
        //System.out.println(action2);
        User_NewPass1 = getParam("User_NewPass1");
        User_NewPass2 = getParam("User_NewPass2");
        User_CurPass = getParam("User_CurPass");
       
        userName = (String) session.getAttribute("_portal_username");
        //userRole = (String) session.getAttribute("_portal_role");
        userID = (String) session.getAttribute("_ekptg_user_id");
        context.put("User_Name", userName);

        vm = "app/integrasi/frmIntChangePassword.jsp";
        if ("updatePass".equalsIgnoreCase(action2)) {
            //System.out.println("updatePass 1="+PasswordService.compare(User_CurPass, "t2lWVXEdPTGbBMIBcwukrR05pPk="));
            changePass = modelINTChangePass.changePassword(userID, User_NewPass1, User_NewPass2, User_CurPass);
     
        }
        context.put("changePass", changePass);
        return vm;
	
	}	
	
	
}