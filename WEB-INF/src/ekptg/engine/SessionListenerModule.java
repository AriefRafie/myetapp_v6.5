package ekptg.engine;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

public class SessionListenerModule extends VTemplate
{
  public Template doTemplate()
    throws Exception
  {
    HttpSession session = this.request.getSession();
    setShowVM(false);
    long sessionCount = SessionListener.getNumberOfSession();
    this.context.put("sessionCount", new Integer((int)sessionCount));

    Template template = this.engine.getTemplate("vtl/admin/session_listener.vm");
    return template;
  }
}