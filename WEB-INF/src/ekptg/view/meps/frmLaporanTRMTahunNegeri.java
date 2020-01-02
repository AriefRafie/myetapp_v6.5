package ekptg.view.meps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.meps.PDT_modeldata;

public class frmLaporanTRMTahunNegeri extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(frmLaporanTRMTahunNegeri.class);
	
	// MODEL
		PDT_modeldata model = new PDT_modeldata();
		
		@Override
		public String doTemplate2() throws Exception {

			// DEFAULT
			HttpSession session = request.getSession();
			String vm = "";
			String doPost = (String) session.getAttribute("doPost");
			String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
			String submit = getParam("command");
			String refence = "yes";

			// VECTOR
			Vector listPageDepan = new Vector();
			Vector listKod = new Vector();

			listPageDepan.clear();

			// DECLARE VARIABLES
			String id_tahunDari = "";
			
			// SKRIN JSP
			String skrinDepanSenaraiLaporan = "app/meps/pdt/frmLaporanTRMTahunNegeri.jsp";
			
			// TAB
			String selectedTab = "";
			selectedTab = getParam("tabId");
			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
			this.context.put("TAHUN", sdf.format(new Date()));

			myLogger.info("SUBMIT :: " + submit);
			myLogger.info("socTahunDari :: " + id_tahunDari);


			if ("BarGraph".equals(submit)) {

				id_tahunDari = getParam("id_tahunDari");

				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}
				

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// TAB
				this.context.put("selectedTab", selectedTab);
				
				// DROPDOWN
					listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);

				context.put("refence", refence);

				vm = skrinDepanSenaraiLaporan;
				
			} else if ("PieChart".equals(submit)) {

				id_tahunDari = getParam("id_tahunDari");


				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}
				

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// TAB
				this.context.put("selectedTab", selectedTab);

				// DROPDOWN

				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);
				

				context.put("refence", refence);

				vm = skrinDepanSenaraiLaporan;
				
			} else if ("LineChart".equals(submit)) {

				id_tahunDari = getParam("id_tahunDari");
				

				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}
				

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// TAB
				this.context.put("selectedTab", selectedTab);

				// DROPDOWN
				
				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);
				

				context.put("refence", refence);

				vm = skrinDepanSenaraiLaporan;
				
			} else if ("PieChart2".equals(submit)) {

				id_tahunDari = getParam("id_tahunDari");
				

				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}
				

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// TAB
				this.context.put("selectedTab", selectedTab);

				// DROPDOWN
				
				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);
				

				context.put("refence", refence);

				vm = skrinDepanSenaraiLaporan;
				
			} else if ("PieChart3".equals(submit)) {

				id_tahunDari = getParam("id_tahunDari");

				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}
				

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// TAB
				this.context.put("selectedTab", selectedTab);

				// DROPDOWN
				
				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);
				

				context.put("refence", refence);

				vm = skrinDepanSenaraiLaporan;
				
			} else if ("Laporan".equals(submit)) {
				System.out.println("LAPORAN =========");
				id_tahunDari = getParam("id_tahunDari");
				

				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}
				

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// TAB
				this.context.put("selectedTab", selectedTab);

				// DROPDOWN
				
				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);
				

				context.put("PermohonanList", listPageDepan);
				context.put("list_size", listPageDepan.size());

				context.put("refence", "");

				vm = skrinDepanSenaraiLaporan;
				
			} else if ("search_data".equals(submit)) {

				id_tahunDari = getParam("socTahunDari");


				if (id_tahunDari.equals("")) {
					id_tahunDari = "";
				} else {
					id_tahunDari = getParam("socTahunDari");
				}


				// DROPDOWN
				
				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);
				

				// DATA & SIZE DEFAULT LIST
				context.put("PermohonanList", listPageDepan);
				context.put("list_size", listPageDepan.size());

				// MAINTAIN SEARCHING VALUES
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				context.put("id_tahunDari", id_tahunDari);
				context.put("refence", refence);

				vm = skrinDepanSenaraiLaporan;
				
			} else {

				id_tahunDari = sdf.format(new Date());
				
				context.put("id_tahunDari", id_tahunDari);

				// DROP DOWN
				context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
				
				// GET LIST DATA
				listPageDepan = model.getListTotalKeseluruhanFailAbbrev(id_tahunDari);

				context.put("PermohonanList", listPageDepan);
				context.put("list_size", listPageDepan.size());

				vm = skrinDepanSenaraiLaporan;
			}
			
			// GENERATE REPORT TITLE
			String tajukLaporan = "Laporan Keluasan Kawasan Tanah Rizab Melayu "
					+ (id_tahunDari.equals("") ? "" : " (");
			if (!id_tahunDari.equals("")) {
//				
					tajukLaporan += id_tahunDari;
//					
				} 
//					
			
			tajukLaporan += (id_tahunDari.equals("") ? "" : ")");
				context.put("xml", model.generateXML(tajukLaporan));
			

			// GET KOD - NAMA NEGERI
			listKod = model.getAbbrev();
			context.put("SenaraiKod", listKod);
			context.put("listKod_size", listKod.size());
			// TAB
			this.context.put("selectedTab", selectedTab);
			this.context.put("tajukLaporan", tajukLaporan);

			return vm;

		}// close doTemplate2

		// METHOD --------------

}
