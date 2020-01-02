package ekptg.view.online;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class WebTemplate extends VTemplate
{
  public WebTemplate(VelocityEngine engine, VelocityContext context, HttpServletRequest req, 
		  HttpServletResponse res)
  {
    super(engine, context, req, res);
  }

  public Template doTemplate() throws Exception {
    Template template = this.engine.getTemplate("app/online/WebTemplate.vm");
    return template;
  }
  
}