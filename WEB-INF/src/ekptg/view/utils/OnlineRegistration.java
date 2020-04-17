package ekptg.view.utils;


import lebah.portal.AjaxBasedModule;

public class OnlineRegistration extends AjaxBasedModule  {
	
	  public String doTemplate2() throws Exception
	  {
		String template = "app/online/frmDaftarPengguna.jsp";
	    this.context.put("name", "");
	    String submit = getParam("command");
	    if ("register".equals(submit)) {
	      String name = getParam("hello-name");
	      this.context.put("name", name);
	      System.out.println("saying hello to = " + name);
	    }
	    
	    return template;
	  }
}
