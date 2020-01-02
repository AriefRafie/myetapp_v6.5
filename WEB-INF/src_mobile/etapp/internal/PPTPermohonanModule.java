package etapp.internal;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;

import etapp.data.PPTPermohonanMobile;
import etapp.template.VMobile;

public class PPTPermohonanModule extends VMobile<PPTPermohonanMobile> {

	public Template doTemplate() throws Exception {
		HttpSession session = request.getSession();
//		this.setPersistentClass(PPTPermohonan.class);
//		this.setSelectStatement(null);
//		this.setRowSize(20);
//		this.setRowStart(0);
		
		context.put("divList", "hidden");
		String submit = getParam("command");
		String user = (String) session.getAttribute("_portal_login");
		
		if ( !"".equals(submit) ) {
			PPTPermohonanMobile details = db.find(PPTPermohonanMobile.class, Long.parseLong(submit));
			context.put("details", details); context.put("divList", "visible");
		}
		List<PPTPermohonanMobile> records = db.list("SELECT p FROM PPTPermohonanMobile p, UsersKementerianMobile uk WHERE p.fail.kementerian.id = uk.kementerian.id AND uk.users.uLogin = '" + user + "' AND p.fail.subUrusan.id IN (51,52,53) ORDER BY p.tarikhPermohonan DESC, p.tarikhKemaskini DESC");
		context.put("records", records);
		context.put("dateFrmt", new SimpleDateFormat("dd-MM-yyyy"));
    	
		Template template = engine.getTemplate("vtl/mobile/PPTPermohonan/permohonan.vm"); 
	    return template;
	}

	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}