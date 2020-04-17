package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPT_modeldata;

public class PPT_LaporanSiasatan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(PPT_LaporanSiasatan.class);

	// MODEL
	PPT_modeldata model = new PPT_modeldata();

	@Override
	public String doTemplate2() throws Exception {

		// DEFAULT
		HttpSession session = request.getSession();
		String vm = "";
		String doPost = (String) session.getAttribute("doPost");
		String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
		String submit = getParam("command");

		// VECTOR
		Vector listPageDepan = new Vector();

		listPageDepan.clear();

		// DECLARE VARIABLES
		String id_tahun = "";
		String id_bulan = "";

		// SKRIN JSP
		String skrinDepanSenaraiLaporan = "app/meps/ppt/LaporanSiasatan.jsp";

		// TAB
		String selectedTab = "";
		selectedTab = getParam("tabId");
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}

		myLogger.info("SUBMIT :: " + submit);
		myLogger.info("socTahun :: " + id_tahun);
		myLogger.info("socBulan :: " + id_bulan);

		if ("BarGraph".equals(submit)) {

			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");

			if (id_tahun.equals("")) {
				id_tahun = "";
			} else {
				id_tahun = getParam("socTahun");
			}

			if (id_bulan.equals("")) {
				id_bulan = "";
			} else {
				id_bulan = getParam("socBulan");
				if (id_bulan.length() < 2)
					id_bulan = "0" + id_bulan;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahun", HTML.SelectTahun("socTahun", id_tahun,
					null, "style=width:130px"));
			context.put("selectBulan", HTML.SelectBulan("socBulan",
					Utils.parseLong(id_bulan), null, "style=width:130px"));

			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			listPageDepan = model.getListTotalSiasatan(id_tahun, id_bulan);

			vm = skrinDepanSenaraiLaporan;

		} else if ("PieChart".equals(submit)) {

			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");

			if (id_tahun.equals("")) {
				id_tahun = "";
			} else {
				id_tahun = getParam("socTahun");
			}

			if (id_bulan.equals("")) {
				id_bulan = "";
			} else {
				id_bulan = getParam("socBulan");
				if (id_bulan.length() < 2)
					id_bulan = "0" + id_bulan;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahun", HTML.SelectTahun("socTahun", id_tahun,
					null, "style=width:130px"));
			context.put("selectBulan", HTML.SelectBulan("socBulan",
					Utils.parseLong(id_bulan), null, "style=width:130px"));

			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			listPageDepan = model.getListTotalSiasatan(id_tahun, id_bulan);

			vm = skrinDepanSenaraiLaporan;

		} else if ("Laporan".equals(submit)) {

			id_tahun = getParam("id_tahun");
			id_bulan = getParam("id_bulan");

			if (id_tahun.equals("")) {
				id_tahun = "";
			} else {
				id_tahun = getParam("socTahun");
			}

			if (id_bulan.equals("")) {
				id_bulan = "";
			} else {
				id_bulan = getParam("socBulan");
				if (id_bulan.length() < 2)
					id_bulan = "0" + id_bulan;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahun", HTML.SelectTahun("socTahun", id_tahun,
					null, "style=width:130px"));
			context.put("selectBulan", HTML.SelectBulan("socBulan",
					Utils.parseLong(id_bulan), null, "style=width:130px"));

			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			listPageDepan = model.getListTotalSiasatan(id_tahun, id_bulan);

			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			vm = skrinDepanSenaraiLaporan;

		} else if ("search_data".equals(submit)) {

			id_tahun = getParam("socTahun");
			id_bulan = getParam("socBulan");

			if (id_tahun.equals("")) {
				id_tahun = "";
			} else {
				id_tahun = getParam("socTahun");
			}

			if (id_bulan.equals("")) {
				id_bulan = "";
			} else {
				id_bulan = getParam("socBulan");
				if (id_bulan.length() < 2)
					id_bulan = "0" + id_bulan;
			}

			// GET LIST DATA
			listPageDepan = model.getListTotalSiasatan(id_tahun, id_bulan);

			// DATA & SIZE DEFAULT LIST
			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahun", HTML.SelectTahun("socTahun", id_tahun,
					null, "style=width:130px"));
			context.put("selectBulan", HTML.SelectBulan("socBulan",
					Utils.parseLong(id_bulan), null, "style=width:130px"));
			context.put("id_tahun", id_tahun);
			context.put("id_bulan", id_bulan);

			vm = skrinDepanSenaraiLaporan;

		} else {

			id_tahun = "";
			id_bulan = "";
			context.put("id_tahun", id_tahun);
			context.put("id_bulan", id_bulan);

			// DROP DOWN
			context.put("selectTahun", HTML.SelectTahun("socTahun", id_tahun,
					null, "style=width:130px"));
			context.put("selectBulan", HTML.SelectBulan("socBulan",
					Utils.parseLong(id_bulan), null, "style=width:130px"));

			// GET LIST DATA
			listPageDepan = model.getListTotalSiasatan(id_tahun, id_bulan);

			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			vm = skrinDepanSenaraiLaporan;
		}

		// GENERATE BAR & PIE CHART
		context.put("xml", model.generateXML(""));

		// TAB
		this.context.put("selectedTab", selectedTab);

		return vm;

	}// close doTemplate2

	// METHOD --------------

}// close class
