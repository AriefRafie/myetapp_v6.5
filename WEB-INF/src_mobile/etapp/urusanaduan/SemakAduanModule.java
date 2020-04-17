package etapp.urusanaduan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import etapp.data.OnlineEAduanMobile;
import etapp.data.RujJenisAduanMobile;
import etapp.template.VMobile;

public class SemakAduanModule extends VMobile<OnlineEAduanMobile> {

	public Template doTemplate() throws Exception {
		HttpSession session = request.getSession();
    	this.setPersistentClass(OnlineEAduanMobile.class);    
    	this.setSelectStatement(null);
//		this.setOrderBy("tarikhMasuk");
//		this.setOrderType("DESC");
		this.setRowSize(5);
		this.setRowStart(0);
				
		String role = (String) session.getAttribute("_portal_role");
		
		if ( role.equals("online") ) {
			String submit = getParam("command");
	    	context.put("saveinfo", "");
	    	context.put("divList", "hidden");
	    	String[] submitCommand = submit.split("-");
	    	String login = (String) session.getAttribute("_portal_login");
	    	
	    	if ( submitCommand[0].equals("delete") ) {
	    		Long idAduan = Long.parseLong(submitCommand[1]);
	    		OnlineEAduanMobile semakAduan = db.find(OnlineEAduanMobile.class, idAduan);
	    		db.begin();
	    		db.remove(semakAduan);
	    		db.commit();
	    		context.put("saveinfo", "Success");    
	    		context.put("idAduan", idAduan);
	    	} else if ( submitCommand[0].equals("list") ) {
	        	OnlineEAduanMobile lAduan = db.find(OnlineEAduanMobile.class, Long.parseLong(submitCommand[1]));
	        	context.put("lAduan", lAduan);
	        	context.put("divList", "visible");
	        	context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
	    	}
	    	
	    	ArrayList idCollection = new ArrayList();
	    	
	    	idCollection.add("x.idPengadu = (SELECT a.id FROM UsersMobile a WHERE a.uLogin = '" + login + "')");
	    	
	    	listRecord(idCollection);
		} else {
	    	String submit = getParam("command");
			int selection = getParamAsInteger("pageNum");
			   	
//			System.out.println("Number of Selection : " + selection);
	    	if ( !"".equals(submit) ) { 
	    		listFindRecord();
	    	} else if ( selection > 0 ) {
	    		getPage(selection);
	    	} else {
	    		getPage(1);
//	    		listFindRecord();
	    	}
	    	
	    	List<RujJenisAduanMobile> rjenisaduan = db.list("SELECT p FROM RujJenisAduanMobile p");
	    	context.put("rjenisaduan", rjenisaduan);
	    	context.put("rowSize", 5);
		}
		
		context.put("role", role);
		
	    Template template = engine.getTemplate("vtl/mobile/Aduan/aduan.vm"); 
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
	
	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		String idJenisAduan = request.getParameter("idJenisAduan");
		String status = request.getParameter("Status");
		
		Map<String, Object> m = new HashMap<String, Object>();
		if ( !"".equals(idJenisAduan) ) m.put("jenisAduan.id", idJenisAduan);
		if ( !"".equals(status) ) m.put("status", status);
		
		return m;
	}
}
