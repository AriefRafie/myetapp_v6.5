package etapp.internal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import etapp.data.PPKPermohonanSimatiMobile;
import etapp.template.VMobile;

public class PPKPusakaModule extends VMobile<PPKPermohonanSimatiMobile> {

	public Template doTemplate() throws Exception {
		String path = "vtl/mobile/PPKPusaka/";
		String PPKPath = request.getSession().getAttribute("_PPK_path") != null ? 
				(String) request.getSession().getAttribute("_PPK_path") : "";
		Date now = new Date();
		this.setTableHeaderPage(path + "row_title.vm");
		this.setDataRowPage(path + "row_data.vm"); 
		this.setPersistentClass(PPKPermohonanSimatiMobile.class);
		this.setSelectStatement(null);
		this.setRowSize(10);
		this.setRowStart(0);
		String submit = getParam("command");
		String PPKSelection = getParam("selection"); 
		String subReturn = getParam("submission");
		
		if ( submit.equals("getResult") || subReturn.equals("getResult") )	{
			request.getSession().setAttribute("_PPK_path", "result_page.vm");
			long rData = getParamAsInteger("dataRecord");
			context.put("divList", "hidden");
			
			System.out.println(rData);
			if ( !"".equals(submit)) {
				listFindRecord();	
				if ( rData > 0 ) {
					PPKPermohonanSimatiMobile simati = db.find(PPKPermohonanSimatiMobile.class, rData);
					context.put("recSimati", simati); context.put("divList", "visible");
				}
			}
			context.put("row_data_page", dataRowPage);context.put("row_title_page", tableHeaderPage);	
			context.put("PPKSelection", PPKSelection);
		} else {
			request.getSession().setAttribute("_PPK_path", "search_page.vm");
			String pemohon = getParam("jenisKPPemohon");
			String simati = getParam("jenisKPSimati");
			
			if (!"".equals(pemohon)) {
				System.out.println("Jenis KP Pemohon : " + pemohon);
				context.put("dispSimati", "display:none");
				context.put("dispPemohonText", "display:visible");
				context.put("m_pemohon", pemohon);
				context.put("selection", "pemohon");
			} else if (!"".equals(simati)) {
				context.put("dispPemohon", "display:none");
				context.put("dispSimatiText", "display:visible");
				context.put("m_simati", simati);
				context.put("selection", "simati");
			}
		}
		
    	context.put("now", now);
    	context.put("dateFormat", new SimpleDateFormat("dd-MM-yyyy"));
    	
	    Template template = engine.getTemplate(path + (String) request.getSession().getAttribute("_PPK_path")); 
	    return template; 
	}
	
	@Override
	public Map<String, Object> searchCriteria() throws Exception {
		String idPemohon = request.getParameter("idPemohon"), idSimati = request.getParameter("idSimati");
		String jenisKPPemohon = request.getParameter("jenisKPPemohon"), jenisKPSimati = request.getParameter("jenisKPSimati");
		
		Map<String, Object> m = new HashMap<String, Object>();
		if ( !"".equals(jenisKPPemohon) ) m.put("permohonan.pemohon." + jenisKPPemohon, idPemohon);
		if ( !"".equals(jenisKPSimati) ) m.put("simati." + jenisKPSimati, idSimati);
		
		context.put("idPemohon", idPemohon);
		context.put("idSimati", idSimati);
		context.put("jenisKPPemohon", jenisKPPemohon);
		context.put("jenisKPSimati", jenisKPSimati);
		return m;
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