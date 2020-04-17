package ekptg.view.integrasi.macgdi;

import java.text.SimpleDateFormat;
import java.util.Date;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;

public class FrmPopupLaporanRizabKementerian  extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3576494755269810197L;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
	static Logger myLog = Logger.getLogger(FrmPopupLaporanHTP.class);

	@Override
	public String doTemplate2() throws Exception {
		String vm = "";
		String submit = getParam("command");
		String id = getParam("id");
		
		context.put("id", id);
		String id_tahunDari = "";
		String id_tahunHingga = "";
		id_tahunDari = getParam("socTahunDari");
		myLog.info("id tahun dari:id_tahunDari="+id_tahunDari);
		myLog.info("id tahun dari:submit="+submit);
		
		// String doPost = (String) session.getAttribute("doPost");
		// Maklumat Hakmilik Pembangunan Strata
		if("doFilter".equals(submit)){
			
			if (id_tahunDari.equals("")) {
				id_tahunDari = sdfTahun.format(new Date());
				System.out.println("id tahun dari-------"+id_tahunDari);
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = sdfTahun.format(new Date());
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}
			
			this.context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			this.context.put("selectTahunHingga", HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			
		}else{
			this.context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			this.context.put("selectTahunHingga", HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			
		}
		
		vm = "app/integrasi/macgdi/frmPopupRizabMengikutKementerian.jsp";
		System.out.println("command"+submit);
		return vm;
	}
}