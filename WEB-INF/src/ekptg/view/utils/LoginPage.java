package ekptg.view.utils;


import javax.servlet.http.HttpSession;

import lebah.portal.db.AuthenticateUser;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class LoginPage extends VTemplate 
{
		
		  public Template doTemplate()
		    throws Exception
		  {
			HttpSession session = this.request.getSession();
			String template_name = "app/utils/OnlineLogin.jsp";
		   
		    String submit = getParam("command");
		    //System.out.println("submit:"+submit);
		    if ("doLogin".equals(submit)) {
	    	 String username = getParam("username");
		     String password = getParam("password");
		     AuthenticateUser auth = new AuthenticateUser();
             if(auth.lookup(username, password,"online"))
             {
            	 this.context.put("result","success");
                 session.setAttribute("nickname", username);
                 session.setAttribute("_portal_role", auth.getRole());
                 session.setAttribute("_portal_username", auth.getUserName());
                 session.setAttribute("_portal_login", auth.getUserLogin());
                 session.setAttribute("_portal_islogin", "true");
                 session.setAttribute("_ekptg_user_negeri", auth.getUserNegeri());
                 session.setAttribute("_ekptg_user_id", auth.getUserID());
                 session.setAttribute("_ekptg_loginType","online");
                 response.sendRedirect("");
             } else {
            	 this.context.put("result","failed");
             }
		      this.context.put("username",username);
		    } else {
		    	 this.context.put("username", "");
		    	 this.context.put("result","");
		    }
		    Template template = this.engine.getTemplate(template_name);
		    return template;
		  }
	
		

}
