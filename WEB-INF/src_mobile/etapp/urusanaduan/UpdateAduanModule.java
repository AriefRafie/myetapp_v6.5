package etapp.urusanaduan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;
import lebah.template.DbPersistence;

import org.apache.velocity.Template;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import etapp.data.OnlineEAduanMobile;

public class UpdateAduanModule extends VTemplate {
	protected DbPersistence db = new DbPersistence();
	
	public Template doTemplate() throws Exception {
    	HttpSession session = request.getSession();
    	Date now = new Date();
		context.put("saveinfo", "");
		context.put("divList", "hidden");
    	String submit = getParam("command");
    	        
    	if ( submit.equals("update") ) {    					  		
    		Long idAduan = Long.parseLong(request.getParameter("idAduan"));
    		String catatanSelesai = request.getParameter("catatanSelesai");
    		String status = request.getParameter("status");
    		String tarikhKemaskini = request.getParameter("tarikhKemaskini");
    		OnlineEAduanMobile kemasAduan = db.find(OnlineEAduanMobile.class, idAduan);
			db.begin();
			kemasAduan.setCatatanSelesai(catatanSelesai.toUpperCase());
    		kemasAduan.setStatus(status.toUpperCase());
    		kemasAduan.setTarikhKemaskini(parseDate(tarikhKemaskini));
    		db.commit();
    		context.put("saveinfo", "Success");
    		context.put("idAduan", idAduan);
    	} else {
        	List<OnlineEAduanMobile> eAduan = db.list("SELECT p FROM OnlineEAduanMobile p WHERE p.id = '" + Long.parseLong(submit) + "'");
        	
        	context.put("eAduan", eAduan);
        	
        	context.put("now", now);
        	context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
    	}

	    Template template = engine.getTemplate("vtl/mobile/Aduan/update_complaint.vm"); 
	    return template;  
    }    
	
	private Date parseDate(String dateTxt) throws java.text.ParseException { 
		if ( dateTxt != null && !"".equals(dateTxt)) { 
			try { 
				return new SimpleDateFormat("dd-MM-yyyy").parse(dateTxt);			
			} catch (ParseException e) {
				return null;
			}
		}
			return null;
	}
}