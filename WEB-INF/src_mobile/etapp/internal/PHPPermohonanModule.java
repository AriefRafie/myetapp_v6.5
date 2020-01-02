package etapp.internal;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;
import lebah.template.DbPersistence;

import org.apache.velocity.Template;

import etapp.data.PermohonanMobile;

public class PHPPermohonanModule extends VTemplate {
	protected DbPersistence db = new DbPersistence();
	
	public Template doTemplate() throws Exception {
		HttpSession session = request.getSession();
		String submit = getParam("command");
    	context.put("divList", "visibility:hidden");
    	String[] submitCommand = submit.split("-");
		String login = (String) session.getAttribute("_portal_login");
		
		if ( submitCommand[0].equals("list") ){
			PermohonanMobile sewa = db.find(PermohonanMobile.class, Long.parseLong(submitCommand[1]));
			context.put("sewa", sewa);context.put("divList", "visibility:visible");
		}
		
    	List<PermohonanMobile> sewaan = db.list("SELECT p FROM PermohonanMobile p WHERE p.pemohon.idMasuk = (SELECT a.id FROM UsersMobile a WHERE a.uLogin = '" + login + "')");
    	context.put("sewaan", sewaan);    	
    	context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
    	
    	Template template = engine.getTemplate("vtl/mobile/PHPPermohonan/permohonan.vm"); 
	    return template;
	}
}