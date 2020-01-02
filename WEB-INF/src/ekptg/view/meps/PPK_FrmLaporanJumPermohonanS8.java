package ekptg.view.meps;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.meps.PPK_modeldata;

public class PPK_FrmLaporanJumPermohonanS8 extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7398754193256331235L;
	static Logger myLogger = Logger.getLogger(PPK_FrmLaporanJumPermohonanS8.class);

	// MODEL
	PPK_modeldata model = new PPK_modeldata();

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
		String id_tahunHingga = "";
		String id_bulanDari = "";
		String id_bulanHingga = "";
		String txdTarikhMula = "";
		String txdTarikhAkhir = "";
		String idNegeri = "";
		
		// MID
		String ID_NEGERI = "";
		String ID_PEJABATJKPTG = "";
		context.put("ID_NEGERI", "");
		context.put("ID_PEJABATJKPTG", "");
		
		List list_TBLRUJNEGERI = null;
		List listPejabatJKPTG = null;

		// SKRIN JSP
		String skrinDepanSenaraiLaporan = "app/meps/ppk/frmLaporanPermohonanS8.jsp";

		// TAB
		String selectedTab = "";
		selectedTab = getParam("tabId");
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}
		myLogger.info("SUBMIT :: " + submit);
//		myLogger.info("socTahunDari :: " + id_tahunDari);
//		myLogger.info("socTahunHingga :: " + id_tahunHingga);
//		myLogger.info("socBulanDari :: " + id_bulanDari);
//		myLogger.info("socBulanHingga :: " + id_bulanHingga);

		if ("BarGraph".equals(submit)) {
			id_tahunDari = getParam("id_tahunDari");
			id_tahunHingga = getParam("id_tahunHingga");
			id_bulanDari = getParam("id_bulanDari");
			id_bulanHingga = getParam("id_bulanHingga");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			idNegeri = getParam("socNegeri");
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (id_bulanDari.equals("")) {
				id_bulanDari = "";
			} else {
				id_bulanDari = getParam("socBulanDari");
				if (id_bulanDari.length() < 2)
					id_bulanDari = "0" + id_bulanDari;
			}
			if (id_bulanHingga.equals("")) {
				id_bulanHingga = "";
			} else {
				id_bulanHingga = getParam("socBulanHingga");
				if (id_bulanHingga.length() < 2)
					id_bulanHingga = "0" + id_bulanHingga;
			}
			if (idNegeri.equals("")) {
				idNegeri = "";
			} else {
				idNegeri = getParam("idNegeri");
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
			context.put("idNegeri", idNegeri);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,
					txdTarikhMula, txdTarikhAkhir);*/
			myLogger.info(">>>>>>>>>>>>>>>> ID_NEGERI : "+ID_NEGERI);
			
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				System.out.println("::getListLaporanPrestasiUnit::");
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
				
			} else {
				System.out.println("::getListTotalSeksyen8Abbrev::");
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}

			// DEFAULT
			context.put("refence", refence);

			vm = skrinDepanSenaraiLaporan;

		} else if ("PieChart".equals(submit)) {

			id_tahunDari = getParam("id_tahunDari");
			id_tahunHingga = getParam("id_tahunHingga");
			id_bulanDari = getParam("id_bulanDari");
			id_bulanHingga = getParam("id_bulanHingga");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (id_bulanDari.equals("")) {
				id_bulanDari = "";
			} else {
				id_bulanDari = getParam("socBulanDari");
				if (id_bulanDari.length() < 2)
					id_bulanDari = "0" + id_bulanDari;
			}
			if (id_bulanHingga.equals("")) {
				id_bulanHingga = "";
			} else {
				id_bulanHingga = getParam("socBulanHingga");
				if (id_bulanHingga.length() < 2)
					id_bulanHingga = "0" + id_bulanHingga;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,
					txdTarikhMula, txdTarikhAkhir);*/
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
			} else {
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}

			// DEFAULT
			context.put("refence", refence);

			vm = skrinDepanSenaraiLaporan;

		} else if ("LineChart".equals(submit)) {

			id_tahunDari = getParam("id_tahunDari");
			id_tahunHingga = getParam("id_tahunHingga");
			id_bulanDari = getParam("id_bulanDari");
			id_bulanHingga = getParam("id_bulanHingga");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (id_bulanDari.equals("")) {
				id_bulanDari = "";
			} else {
				id_bulanDari = getParam("socBulanDari");
				if (id_bulanDari.length() < 2)
					id_bulanDari = "0" + id_bulanDari;
			}
			if (id_bulanHingga.equals("")) {
				id_bulanHingga = "";
			} else {
				id_bulanHingga = getParam("socBulanHingga");
				if (id_bulanHingga.length() < 2)
					id_bulanHingga = "0" + id_bulanHingga;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,
					txdTarikhMula, txdTarikhAkhir);*/
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
			} else {
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}

			// DEFAULT
			context.put("refence", refence);

			vm = skrinDepanSenaraiLaporan;

		} else if ("PieChart2".equals(submit)) {

			id_tahunDari = getParam("id_tahunDari");
			id_tahunHingga = getParam("id_tahunHingga");
			id_bulanDari = getParam("id_bulanDari");
			id_bulanHingga = getParam("id_bulanHingga");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (id_bulanDari.equals("")) {
				id_bulanDari = "";
			} else {
				id_bulanDari = getParam("socBulanDari");
				if (id_bulanDari.length() < 2)
					id_bulanDari = "0" + id_bulanDari;
			}
			if (id_bulanHingga.equals("")) {
				id_bulanHingga = "";
			} else {
				id_bulanHingga = getParam("socBulanHingga");
				if (id_bulanHingga.length() < 2)
					id_bulanHingga = "0" + id_bulanHingga;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,
					txdTarikhMula, txdTarikhAkhir);*/
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
			} else {
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}

			// DEFAULT
			context.put("refence", refence);

			vm = skrinDepanSenaraiLaporan;

		} else if ("Laporan".equals(submit)) {

			id_tahunDari = getParam("id_tahunDari");
			id_tahunHingga = getParam("id_tahunHingga");
			id_bulanDari = getParam("id_bulanDari");
			id_bulanHingga = getParam("id_bulanHingga");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (id_bulanDari.equals("")) {
				id_bulanDari = "";
			} else {
				id_bulanDari = getParam("socBulanDari");
				if (id_bulanDari.length() < 2)
					id_bulanDari = "0" + id_bulanDari;
			}
			if (id_bulanHingga.equals("")) {
				id_bulanHingga = "";
			} else {
				id_bulanHingga = getParam("socBulanHingga");
				if (id_bulanHingga.length() < 2)
					id_bulanHingga = "0" + id_bulanHingga;
			}

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			// TAB
			this.context.put("selectedTab", selectedTab);

			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,
					txdTarikhMula, txdTarikhAkhir);*/
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
			} else {
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}

			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			// DEFAULT
			context.put("refence", "");

			vm = skrinDepanSenaraiLaporan;

		} else if ("search_data".equals(submit)) {

			id_tahunDari = getParam("socTahunDari");
			id_tahunHingga = getParam("socTahunHingga");
			id_bulanDari = getParam("socBulanDari");
			id_bulanHingga = getParam("socBulanHingga");
			txdTarikhMula = getParam("txdTarikhMula");
			txdTarikhAkhir = getParam("txdTarikhAkhir");
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			if (id_tahunDari.equals("")) {
				id_tahunDari = "";
			} else {
				id_tahunDari = getParam("socTahunDari");
			}
			if (id_tahunHingga.equals("")) {
				id_tahunHingga = "";
			} else {
				id_tahunHingga = getParam("socTahunHingga");
			}

			if (id_bulanDari.equals("")) {
				id_bulanDari = "";
			} else {
				id_bulanDari = getParam("socBulanDari");
				if (id_bulanDari.length() < 2)
					id_bulanDari = "0" + id_bulanDari;
			}
			if (id_bulanHingga.equals("")) {
				id_bulanHingga = "";
			} else {
				id_bulanHingga = getParam("socBulanHingga");
				if (id_bulanHingga.length() < 2)
					id_bulanHingga = "0" + id_bulanHingga;
			}

			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,
					txdTarikhMula, txdTarikhAkhir);*/
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
			} else {
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}

			// DATA & SIZE DEFAULT LIST
			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			// MAINTAIN SEARCHING VALUES
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("id_tahunDari", id_tahunDari);
			context.put("id_tahunHingga", id_tahunHingga);
			context.put("id_bulanDari", id_bulanDari);
			context.put("id_bulanHingga", id_bulanHingga);
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			vm = skrinDepanSenaraiLaporan;
			// DEFAULT
			context.put("refence", refence);

		} 
		
		//filter dropdown list
		else if("doFilter".equals(submit)){
					System.out.println("id negeri: " +getParam("ID_NEGERI"));
					
					ID_NEGERI = getParam("ID_NEGERI");
					context.put("ID_NEGERI", ID_NEGERI);
					ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
					context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
					// MAINTAIN SEARCHING VALUES
					context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
					context.put("selectTahunHingga",HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
					context.put("selectBulanDari",HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
					context.put("selectBulanHingga",HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
					context.put("txdTarikhMula", txdTarikhMula);
					context.put("txdTarikhAkhir", txdTarikhAkhir);
//					context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Utils.parseLong(idNegeri), null, "style=width:130px"));
//					context.put("idNegeri", idNegeri);
					
					list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI",getParam("ID_NEGERI"),"");
					this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
					listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
					this.context.put("listPejabatJKPTG",listPejabatJKPTG);
					
					vm = skrinDepanSenaraiLaporan;
					
			}else {

			id_tahunDari = "";
			id_tahunHingga = "";
			id_bulanDari = "";
			id_bulanHingga = "";
			txdTarikhMula = "";
			txdTarikhAkhir = "";
			context.put("id_tahunDari", id_tahunDari);
			context.put("id_tahunHingga", id_tahunHingga);
			context.put("id_bulanDari", id_bulanDari);
			context.put("id_bulanHingga", id_bulanHingga);
			
			ID_NEGERI = getParam("ID_NEGERI");
			context.put("ID_NEGERI", ID_NEGERI);
			ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			context.put("ID_PEJABATJKPTG", ID_PEJABATJKPTG);

			// DROP DOWN
			context.put("selectTahunDari", HTML.SelectTahun("socTahunDari", id_tahunDari, null, "style=width:130px"));
			context.put("selectTahunHingga",
					HTML.SelectTahun("socTahunHingga", id_tahunHingga, null, "style=width:130px"));
			context.put("selectBulanDari",
					HTML.SelectBulan("socBulanDari", Utils.parseLong(id_bulanDari), null, "style=width:130px"));
			context.put("selectBulanHingga",
					HTML.SelectBulan("socBulanHingga", Utils.parseLong(id_bulanHingga), null, "style=width:130px"));
			context.put("txdTarikhMula", txdTarikhMula);
			context.put("txdTarikhAkhir", txdTarikhAkhir);
			
			list_TBLRUJNEGERI = model.listTableRujukanV3(session,"TBLRUJNEGERI","","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = model.listPejabatJKPTG(session,"2",getParam("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			
			// GET LIST DATA
			/*listPageDepan = model.getListTotalSeksyen8Abbrev(ID_NEGERI, ID_PEJABATJKPTG, 
							id_tahunDari, id_tahunHingga, id_bulanDari, id_bulanHingga,txdTarikhMula, txdTarikhAkhir);*/
			if (ID_NEGERI != null && ID_NEGERI.length() > 0) {
				listPageDepan = model.getListLaporanPrestasiUnit(id_tahunDari, id_tahunHingga, id_bulanDari,
						id_bulanHingga, txdTarikhMula, txdTarikhAkhir, ID_NEGERI, "8", ID_PEJABATJKPTG);
				refence = "no";
			} else {
				listPageDepan = model.getListTotalSeksyen8Abbrev(
						ID_NEGERI, ID_PEJABATJKPTG, id_tahunDari, id_tahunHingga, id_bulanDari,id_bulanHingga, txdTarikhMula, txdTarikhAkhir);
			}
			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			vm = skrinDepanSenaraiLaporan;
			// DEFAULT
			context.put("refence", "");
			
		}

		// GENERATE BAR & PIE CHART
		// context.put("xml", model.generateXML("Laporan Jumlah Permohonan
		// Seksyen 8 Pusaka Kecil"));
		String tajukLaporan = "LAPORAN JUMLAH PERMOHONAN SEKSYEN 8 PUSAKA KECIL "
				+ (id_tahunDari.equals("") && id_tahunHingga.equals("") ? "" : " (");
		if (!id_tahunDari.equals("") && !id_tahunHingga.equals("")) {
			if (!id_bulanDari.equals("") && !id_bulanHingga.equals("")) {
				tajukLaporan += ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanDari)) + " " + id_tahunDari;
				tajukLaporan += " - " + ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanHingga)) + " "
						+ id_tahunHingga;
			} else if (id_bulanDari.equals("") && id_bulanHingga.equals("")) {
				tajukLaporan += id_tahunDari + " - " + id_tahunHingga;
			} else if (!id_bulanDari.equals("") && id_bulanHingga.equals("")){
				tajukLaporan += ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanDari)) + " " + id_tahunDari;
				tajukLaporan += " - " +" DISEMBER " + id_tahunHingga;
			} else if (id_bulanDari.equals("") && !id_bulanHingga.equals("")){
				tajukLaporan += "JANUARI " + id_tahunDari;
				tajukLaporan += " - " + ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanHingga)) + " "
						+ id_tahunHingga;
			}
			
		} else if (!id_tahunDari.equals("") && id_tahunHingga.equals("")) {
			if (!id_bulanDari.equals("") && id_bulanHingga.equals("")) {
				tajukLaporan += ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanDari)) + " " + id_tahunDari;
			} else if (id_bulanDari.equals("") && id_bulanHingga.equals("")) {
				tajukLaporan += id_tahunDari;
			} else if (!id_bulanDari.equals("") && !id_bulanHingga.equals("")) {
				tajukLaporan += ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanDari)) + " " + id_tahunDari;
				tajukLaporan +=" - " + ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanHingga));
			}
		} else if (id_tahunDari.equals("") && !id_tahunHingga.equals("")) {
			if (id_bulanDari.equals("") && !id_bulanHingga.equals("")) {
				tajukLaporan += ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanHingga)) + " "
						+ id_tahunHingga;
			} else if (id_bulanDari.equals("") && id_bulanHingga.equals("")) {
				tajukLaporan += id_tahunHingga;
			} else if (!id_bulanDari.equals("") && !id_bulanHingga.equals("")){
				tajukLaporan += ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanDari));
				tajukLaporan += " - " + ekptg.report.Utils.getBulan(Integer.parseInt(id_bulanHingga)) + " " + id_tahunHingga;
			}
		}
		
		String negeri = model.getNamaNegeri(session, ID_NEGERI);
		if (!ID_NEGERI.equals("")) {
				tajukLaporan += " BAGI UNIT PEMBAHAGIAN NEGERI "+ negeri;		
		}
		
		/*String pej = model.getNamaPejabat(session, ID_PEJABATJKPTG);
		if (ID_NEGERI.equals("") || !ID_NEGERI.equals("")) {
			if (!ID_PEJABATJKPTG.equals("")) {
				tajukLaporan += " BAGI "+ pej;					
			} 
		}*/
		
		tajukLaporan += (id_tahunDari.equals("") && id_tahunHingga.equals("") ? "" : ")");
		//context.put("xml", model.generateXMLS8(tajukLaporan));
		context.put("xml", model.generateXMLUnit(tajukLaporan));
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

}// close class
