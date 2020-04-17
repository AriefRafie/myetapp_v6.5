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
import etapp.data.RujJenisAduanMobile;
import etapp.data.RujSumberAduanMobile;
import etapp.data.UsersDataMobile;
import etapp.data.UsersMobile;

public class TambahAduanModule extends VTemplate {
	protected DbPersistence db = new DbPersistence();
	
	public Template doTemplate() throws Exception {
    	HttpSession session = request.getSession();
    	Date now = new Date();
		context.put("saveinfo", "");
    	String submit = getParam("command");
    	
    	String login = (String) session.getAttribute("_portal_login");
    	List<UsersMobile> uOn = db.list("SELECT us FROM UsersMobile us WHERE us.uLogin = '" + login + "'");
    	List<UsersDataMobile> uData = db.list("SELECT p FROM UsersDataMobile p WHERE p.id = (SELECT u.id FROM UsersMobile u WHERE u.uLogin = '" + login + "')");
    	context.put("uData", uData);
    	context.put("uOn", uOn);
    	
    	List<RujJenisAduanMobile> rjenisaduan = db.list("SELECT p FROM RujJenisAduanMobile p");
        
    	if ( !"".equals(submit) ) {    				
    		//String rndmNo = lebah.db.UniqueID.getUID();
    		//Long randomNo = Long.parseLong(rndmNo.substring(5, 12));
    		Long idPengadu = Long.parseLong(request.getParameter("idPengadu"));
			String namaPengadu = request.getParameter("namaPengadu");
			String emailPengadu = request.getParameter("emailPengadu");
			String phonePengadu = request.getParameter("phonePengadu");
			String status = "DALAM PROSES";
			
    		Long idJenisAduan = Long.parseLong(request.getParameter("idJenisAduan"));
    		RujJenisAduanMobile jenisAduan = db.find(RujJenisAduanMobile.class, idJenisAduan);
    		
    		Long idSumber = Long.parseLong("161024");
    		RujSumberAduanMobile sumberAduan = db.find(RujSumberAduanMobile.class, idSumber);
    		
			String catatan = request.getParameter("catatan");
			String tarikhMasuk = request.getParameter("tarikhMasuk");
			try {
				db.begin();
				OnlineEAduanMobile aduan = new OnlineEAduanMobile();
	    		aduan.setIdPengadu(idPengadu);
	    		aduan.setNamaPengadu(namaPengadu);
	    		aduan.setEmailPengadu(emailPengadu);
	    		aduan.setPhonePengadu(phonePengadu);
	    		aduan.setJenisAduan(jenisAduan);
	    		aduan.setCatatan(catatan.toUpperCase());
	    		aduan.setStatus(status.toUpperCase());
	    		aduan.setSumberAduan(sumberAduan);
	    		aduan.setTarikhMasuk(parseDate(tarikhMasuk));
	    		db.persist(aduan);
	    		db.commit();
	    		context.put("saveinfo", "Success");
	    		context.put("idAduan", aduan);
			} catch(Exception e){
				context.put("saveinfo", "unSuccess");
				context.put("eMessage", e);
			}
    	}
    	
    	context.put("rjenisaduan", rjenisaduan);
    	
    	context.put("now", now);
    	context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));

	    Template template = engine.getTemplate("vtl/mobile/Aduan/add_complaint.vm"); 
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