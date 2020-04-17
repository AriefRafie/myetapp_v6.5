package etapp.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;

import etapp.data.HTPHakmilikMobile;
import etapp.data.RujDaerahMobile;
import etapp.data.RujKementerianMobile;
import etapp.data.RujMukimMobile;
import etapp.data.RujNegeriMobile;
import etapp.data.UsersKementerianMobile;
import etapp.template.VMobile;

public class HTPHakmilikModule extends VMobile<HTPHakmilikMobile> {
	private String path = "vtl/mobile/HTPHakmilik/";
	
	public Template doTemplate() throws Exception {
		HttpSession session = request.getSession();
		this.setTableHeaderPage(path + "row_title.vm");
		this.setDataRowPage(path + "row_data.vm");
		this.setPersistentClass(HTPHakmilikMobile.class);
		this.setSelectStatement(null);
		this.setRowSize(10);
		this.setRowStart(0);
		String submit = getParam("command");
		String subReturn = getParam("submission");
		String HTPPath = session.getAttribute("_HTP_path") != null ? 
				(String) session.getAttribute("_HTP_path") : "";
				
		if ( submit.equals("getResult") || subReturn.equals("getResult")) {
			session.setAttribute("_HTP_path", "result_page.vm");
			int selection = getParamAsInteger("pageNum");
			long rData = getParamAsInteger("dataRecord");
			
			context.put("divList", "hidden");
			       
			if ( !"".equals(submit) ) {
				listFindRecord();		
			} else if ( selection > 0 ) {
				getPage(selection);
				
				if ( rData > 0 ) {
					HTPHakmilikMobile listHakmilik = db.find(HTPHakmilikMobile.class, rData);
					context.put("listHakmilik", listHakmilik); context.put("divList", "visible");
				}	
			}		 
			
			context.put("row_data_page", dataRowPage);context.put("row_title_page", tableHeaderPage);
			context.put("rowSize", 10);
		} else {
			session.setAttribute("_HTP_path", "search_page.vm");
			List<RujNegeriMobile> rnegeri = db.list("SELECT p FROM RujNegeriMobile p");
			
			String role = (String) session.getAttribute("_portal_role");
			String user = (String) session.getAttribute("_portal_login");
			
			if ( submit.equals("getDaerah") ) {			
				String idNegeri = request.getParameter("idNegeri");
				List<RujKementerianMobile> rkementerian = db.list("SELECT p FROM RujKementerianMobile p");
				List<RujDaerahMobile> rdaerah = db.list("SELECT p FROM RujDaerahMobile p WHERE p.negeri.id = " + idNegeri);
				context.put("rkementerian", rkementerian);context.put("rdaerah", rdaerah);context.put("m_negeri", idNegeri);
			} else if ( submit.equals("getMukim")) {
				String idDaerah = request.getParameter("idDaerah");
				List<RujMukimMobile> rmukim = db.list("SELECT p FROM RujMukimMobile p WHERE p.daerah.id = " + idDaerah);
				context.put("rmukim", rmukim);context.put("m_daerah", idDaerah);
			} 
			
			if ( role.equals("online_kjp")) {
				List<UsersKementerianMobile> userKJP = db.list("SELECT p FROM UsersKementerianMobile p WHERE p.users.uLogin = '" + user + "'");
				context.put("userKJP", userKJP);
			}
				
		  	context.put("rnegeri", rnegeri);
		  	context.put("role", role);	
		}		  	
		
		Template template = engine.getTemplate(path + (String) request.getSession().getAttribute("_HTP_path")); 
	    return template;        
    }

	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		long idNegeri = Long.parseLong(request.getParameter("idNegeri"));
		long idDaerah = Long.parseLong(request.getParameter("idDaerah"));
		long idMukim = Long.parseLong(request.getParameter("idMukim"));
		long idKementerian = Long.parseLong(request.getParameter("idKementerian"));
		String noHakmilik = request.getParameter("noHakmilik");
		String noWarta = request.getParameter("noWarta");
		String noLot = request.getParameter("noLot");
				
		Map<String, Object> m = new HashMap<String, Object>();
		if ( idNegeri != 0 ) { m.put("negeri.id", idNegeri);RujNegeriMobile rNegeri = db.find(RujNegeriMobile.class, idNegeri);context.put("rNegeri", rNegeri); } else { context.put("rNegeri", idNegeri); }
		if ( idDaerah != 0 ) { m.put("daerah.id", idDaerah);RujDaerahMobile rDaerah = db.find(RujDaerahMobile.class, idDaerah);context.put("rDaerah", rDaerah); } else { context.put("rDaerah", idDaerah); }
		if ( idMukim != 0 ) { m.put("mukim.id", idMukim);RujMukimMobile rMukim = db.find(RujMukimMobile.class, idMukim);context.put("rMukim", rMukim); } else { context.put("rMukim", idMukim); }
		if ( idKementerian != 0 ) { m.put("kementerian.id", idKementerian);RujKementerianMobile rKementerian = db.find(RujKementerianMobile.class, idKementerian);context.put("rKementerian", rKementerian); } else { context.put("rKementerian", idKementerian); }
		if ( !"".equals(noHakmilik) ) m.put("noHakmilik", "%" + noHakmilik + "%");context.put("noHakmilik", noHakmilik);
		if ( !"".equals(noWarta) ) m.put("noWarta", "%" + noWarta + "%");context.put("noWarta", noWarta);
		if ( !"".equals(noLot) ) m.put("noLot", "%" + noLot + "%");context.put("noLot", noLot);
		
		return m;
	}
}