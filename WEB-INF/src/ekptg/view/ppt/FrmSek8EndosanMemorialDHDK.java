package ekptg.view.ppt;

import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8EndosanMemorialDHDKData;
import ekptg.model.ppt.PPTHeader;

/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmSek8EndosanMemorialDHDK extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmSek8EndosanMemorialDHDK.class);

	// model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmSek8EndosanMemorialDHDKData model = new FrmSek8EndosanMemorialDHDKData();
	PPTHeader header = new PPTHeader();

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = request.getSession();

		Vector listUserid = new Vector();
		Vector listPageDepan = new Vector();
		Vector listHakmilikPTGOnly = new Vector();
		Vector listHakmilikPTDOnly = new Vector();
		Vector dataNamaPengarah = new Vector();
		Vector getIdSuburusanstatusfail = new Vector();

		getIdSuburusanstatusfail.clear();
		dataNamaPengarah.clear();
		listHakmilikPTDOnly.clear();
		listHakmilikPTGOnly.clear();
		listPageDepan.clear();
		listUserid.clear();

		// command for paging
		String action = getParam("action");

		// get user login detail
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");

		context.put("userIdNeg", userIdNeg);

		// modelUPT.setGetUserId(id_user);
		// listUserid = modelUPT.getUserIds();
		// String userIdNeg = "";
		// if(listUserid.size()!=0){
		// Hashtable t = (Hashtable)listUserid.get(0);
		// userIdNeg = t.get("idNegeri").toString();
		// }

		String vm = "";

		// screen jsp
		String listdepan = "app/ppt/frmSek8EndosanMemorialDHDKSenarai.jsp";
		String mainscreen = "app/ppt/frmSek8EndosanMemorialDHDK.jsp";
		String screenPTG = "app/ppt/frmSek8EndosanMemorialDHDKTambahPTG.jsp";
		String screenPTD = "app/ppt/frmSek8EndosanMemorialDHDKTambahPTD.jsp";

		// default list
		// listPageDepan = model.getListPermohonan(userIdNeg);

		// prevent duplicate when refresh page
		String doPost = (String) session.getAttribute("doPost");

		// anchor
		String ScreenLocation = getParam("ScreenLocation");
		String CursorPoint = getParam("CursorPoint");
		context.put("ScreenLocation", ScreenLocation);
		context.put("CursorPoint", CursorPoint);

		// tabbed
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}

		// paging
		/*
		 * String flagPaging = getParam("paging"); if(flagPaging!=""){
		 * context.put("paging", getParam("paging")); }else{
		 * context.put("paging", "12"); }
		 */
		context.put("paging", "12");

		// header
		String id_status = "";
		String id_pejabat_ptg = "";
		String id_pejabat_ptd = "";
		String flagSegera = "";
		String id_fail = "";
		String idpermohonan = getParam("id_permohonan");
		String nama_pengarah = "";
		Vector dataHeader = null;
		if (!idpermohonan.equals("")) {
			header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if (dataHeader.size() != 0) {
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = (String) dh.get("id_status");
				id_pejabat_ptg = (String) dh.get("id_pejabat_ptg");
				id_pejabat_ptd = (String) dh.get("id_pejabat_ptd");
				flagSegera = dh.get("flag_segera").toString();
				id_fail = dh.get("id_fail").toString();

				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
			}

			// GET NAMA PENGARAH

			modelUPT.setNamaPengarah(userIdNeg);
			dataNamaPengarah = modelUPT.getNamaPengarah();
			if (dataNamaPengarah.size() != 0) {
				Hashtable np = (Hashtable) dataNamaPengarah.get(0);
				nama_pengarah = np.get("nama_pengarah").toString();
			}
		}

		context.put("nama_pengarah", nama_pengarah);

		context.put("flagSegera", flagSegera);

		// get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		if (!idpermohonan.equals("")) {
			modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
			getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
			if (getIdSuburusanstatusfail.size() != 0) {
				Hashtable idsb = (Hashtable) getIdSuburusanstatusfail.get(0);
				id_suburusanstatusfailppt = (String) idsb
						.get("id_suburusanstatusfailppt");
			}
		}

		// default
		context.put("mode", "");
		context.put("isEdit", "");
		context.put("selectPejabatPTGX", HTML.SelectPejabatPTG("socPejabatPTG",
				null, null, "style=width:auto class=disabled disabled"));
		context.put("selectPejabatPTDX", HTML.SelectPejabatPTD("socPejabatPTD",
				null, null, "style=width:auto class=disabled disabled"));
		context.put("showAlertPaging", "no");

		String submit = getParam("command");
		myLogger.info("submit : " + submit);

		if ("viewEndosan".equals(submit)) {

			// list hakmilik PTG shj
			listHakmilikPTGOnly(session, idpermohonan);

			// list hakmilik PTD shj
			listHakmilikPTDOnly(session, idpermohonan);

			// list hakmilik PTG
			listHakmilikPTG(session, idpermohonan);

			// list hakmilik PTD
			listHakmilikPTD(session, idpermohonan);

			// screen
			vm = mainscreen;

		}// close

		else if ("tambahPTG".equals(submit)) {

			// list hakmilik ptg shj
			model.setHakmilikPTGOnly(idpermohonan);
			listHakmilikPTGOnly = model.getListHakmilikPTGOnly();

			String submit2 = getParam("command2");
			myLogger.info("submit[2] : " + submit2);

			// NEW MODE
			if (listHakmilikPTGOnly.size() == 0) {

				// form validation
				context.put("mode", "new");

				// list hakmilik PTG
				listHakmilikPTG(session, idpermohonan);

				// dropdown pejabat ptg by negeri user login
				if (userIdNeg != "") {
					context.put("selectPejabatPTG", HTML
							.SelectPejabatPTGbyNegeri(userIdNeg,
									"socPejabatPTG", null, null,
									"style=width:auto"));
				} else {
					context.put("selectPejabatPTG", HTML.SelectPejabatPTG(
							"socPejabatPTG", null, null, "style=width:auto"));
				}

				if ("simpanPTG".equals(submit2)) {

					if (doPost.equals("true")) {
						// simpan data
						simpanPilihanPTG(session, id_fail);
						/*
						 * if(id_status.equals("31") || id_status.equals("74")){
						 * hantar(session);
						 * updateSuburusanStatusFailPPT(session,
						 * idpermohonan,id_fail,id_suburusanstatusfailppt); }
						 */
						if (modelUPT.cekStatusFailDahWujud(idpermohonan, "35",
								"52") == false) {
							modelUPT.updateStatus(idpermohonan, id_user, "35");
							updateSuburusanStatusFailPPT(session, idpermohonan,
									id_fail, id_suburusanstatusfailppt);
						}
					}

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "no");

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						id_status = (String) dh.get("id_status");
					}

					if (id_status.equals("35")) {
						// JOptionPane.showMessageDialog (null,
						// "Sila Klik Kembali dan Klik 'Paging' No.12 Untuk Mengisi Borang E dan F",
						// "Langkah Seterusnya",
						// JOptionPane.INFORMATION_MESSAGE);
						context.put("showAlertPaging", "yes");
					} else {
						context.put("showAlertPaging", "no");
					}

					// list hakmilik PTG
					listHakmilikPTG(session, idpermohonan);

					// list hakmilik PTG shj
					listHakmilikPTGOnly(session, idpermohonan);

					// data header (get id_status)
					dataHeaderStatus(session, idpermohonan);
					id_pejabat_ptg = dataHeaderStatus(session, idpermohonan);

					// dropdown
					context.put("selectPejabatPTG", HTML.SelectPejabatPTG(
							"socPejabatPTG", Utils.parseLong(id_pejabat_ptg),
							null, "style=width:auto disabled class=disabled"));

				}// close simpanMaklumatPTG

				// VIEW MODE
			} else {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// dropdown
				context.put("selectPejabatPTG", HTML.SelectPejabatPTG(
						"socPejabatPTG", Utils.parseLong(id_pejabat_ptg), null,
						"style=width:auto disabled class=disabled"));

				// list hakmilik PTG
				listHakmilikPTG(session, idpermohonan);

				// list hakmilik PTG shj
				listHakmilikPTGOnly(session, idpermohonan);

				if ("kemaskiniPTG".equals(submit2)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "yes");

					// dropdown pejabat ptg by negeri user login
					if (userIdNeg != "") {
						context.put("selectPejabatPTG", HTML
								.SelectPejabatPTGbyNegeri(userIdNeg,
										"socPejabatPTG",
										Utils.parseLong(id_pejabat_ptg), null,
										"style=width:auto"));
					} else {
						context.put("selectPejabatPTG", HTML.SelectPejabatPTG(
								"socPejabatPTG",
								Utils.parseLong(id_pejabat_ptg), null,
								"style=width:auto"));
					}

					String submit3 = getParam("command3");
					myLogger.info("submit[3] : " + submit3);

					if ("updatePTG".equals(submit3)) {

						if (doPost.equals("true")) {
							// update data
							updatePilihanPTG(session, id_fail);
						}

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						// list hakmilik PTG
						listHakmilikPTG(session, idpermohonan);

						// list hakmilik PTG shj
						listHakmilikPTGOnly(session, idpermohonan);

						// data header (get id_status)
						dataHeaderStatus(session, idpermohonan);
						id_pejabat_ptg = dataHeaderStatus(session, idpermohonan);

						// dropdown
						context.put("selectPejabatPTG", HTML.SelectPejabatPTG(
								"socPejabatPTG",
								Utils.parseLong(id_pejabat_ptg), null,
								"style=width:auto disabled class=disabled"));

					}// close updatePTG

				}// close kemaskiniPTG

			}// close view mode

			// screen
			vm = screenPTG;

		}// close tambahPTG

		else if ("tambahPTD".equals(submit)) {

			// list hakmilik ptd shj
			model.setHakmilikPTDOnly(idpermohonan);
			listHakmilikPTDOnly = model.getListHakmilikPTDOnly();

			String submit2 = getParam("command2");
			myLogger.info("submit[2] : " + submit2);

			// NEW MODE
			if (listHakmilikPTDOnly.size() == 0) {

				// form validation
				context.put("mode", "new");

				// list hakmilik PTD
				listHakmilikPTD(session, idpermohonan);

				// dropdown pejabat ptd by negeri user login
				if (userIdNeg != "") {
					context.put("selectPejabatPTD", HTML
							.SelectPejabatPTDbyNegeri(userIdNeg,
									"socPejabatPTD", null, null,
									"style=width:auto"));
				} else {
					context.put("selectPejabatPTD", HTML.SelectPejabatPTD(
							"socPejabatPTD", null, null, "style=width:auto"));
				}

				if ("simpanPTD".equals(submit2)) {

					if (doPost.equals("true")) {
						// simpan data
						simpanPilihanPTD(session, id_fail);

						/*
						 * if(id_status.equals("31") || id_status.equals("74")){
						 * hantar(session);
						 * updateSuburusanStatusFailPPT(session,
						 * idpermohonan,id_fail,id_suburusanstatusfailppt); }
						 */

						if (modelUPT.cekStatusFailDahWujud(idpermohonan, "35",
								"52") == false) {
							modelUPT.updateStatus(idpermohonan, id_user, "35");
							updateSuburusanStatusFailPPT(session, idpermohonan,
									id_fail, id_suburusanstatusfailppt);
						}
					}

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "no");

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						id_status = (String) dh.get("id_status");
					}

					if (id_status.equals("35")) {
						// JOptionPane.showMessageDialog (null,
						// "Sila Klik Kembali dan Klik 'Paging' No.12 Untuk Mengisi Borang E dan F",
						// "Langkah Seterusnya",
						// JOptionPane.INFORMATION_MESSAGE);
						context.put("showAlertPaging", "yes");
					} else {
						context.put("showAlertPaging", "no");
					}

					// list hakmilik PTD
					listHakmilikPTD(session, idpermohonan);

					// list hakmilik PTD shj
					listHakmilikPTDOnly(session, idpermohonan);

					// data header (get id_status)
					dataHeaderStatusPTD(session, idpermohonan);
					id_pejabat_ptd = dataHeaderStatusPTD(session, idpermohonan);

					// dropdown
					context.put("selectPejabatPTD", HTML.SelectPejabatPTD(
							"socPejabatPTD", Utils.parseLong(id_pejabat_ptd),
							null, "style=width:auto disabled class=disabled "));

				}// close simpanMaklumatPTD

				// VIEW MODE
			} else {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// dropdown
				context.put("selectPejabatPTD", HTML.SelectPejabatPTD(
						"socPejabatPTD", Utils.parseLong(id_pejabat_ptd), null,
						"style=width:auto disabled class=disabled"));

				// list hakmilik PTD
				listHakmilikPTD(session, idpermohonan);

				// list hakmilik PTD shj
				listHakmilikPTDOnly(session, idpermohonan);

				if ("kemaskiniPTD".equals(submit2)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "yes");

					// dropdown pejabat ptd by negeri user login
					if (userIdNeg != "") {
						context.put("selectPejabatPTD", HTML
								.SelectPejabatPTDbyNegeri(userIdNeg,
										"socPejabatPTD",
										Utils.parseLong(id_pejabat_ptd), null,
										"style=width:auto"));
					} else {
						context.put("selectPejabatPTD", HTML.SelectPejabatPTD(
								"socPejabatPTD",
								Utils.parseLong(id_pejabat_ptd), null,
								"style=width:auto"));
					}

					String submit3 = getParam("command3");
					myLogger.info("submit[3] : " + submit3);

					if ("updatePTD".equals(submit3)) {

						if (doPost.equals("true")) {
							// update data
							updatePilihanPTD(session, id_fail);
						}

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						// list hakmilik PTD
						listHakmilikPTD(session, idpermohonan);

						// list hakmilik PTD shj
						listHakmilikPTDOnly(session, idpermohonan);

						// data header (get id_status)
						dataHeaderStatusPTD(session, idpermohonan);
						id_pejabat_ptd = dataHeaderStatusPTD(session,
								idpermohonan);

						// dropdown
						context.put("selectPejabatPTD", HTML.SelectPejabatPTD(
								"socPejabatPTD",
								Utils.parseLong(id_pejabat_ptd), null,
								"style=width:auto disabled class=disabled"));

					}// close updatePTD

				}// close kemaskiniPTD

			}// close view mode

			// screen
			vm = screenPTD;

		}// close tambahPTD

		else if ("hantar".equals(submit)) {

			if (doPost.equals("true")) {
				// hantar maklumat ke upt
				hantar(session);
			}

			listPageDepan = model.getListPermohonan(userIdNeg);

			context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");

			// screen
			vm = listdepan;

		}// close cari

		else if ("cari".equals(submit)) {

			// carian
			ListCarian(session, userIdNeg);
			listPageDepan = model.getListCarian();

			// screen
			vm = listdepan;

		}// close cari

		else {

			listPageDepan = model.getListPermohonan(userIdNeg);

			context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");

			// screen
			vm = listdepan;

		}// close else

		// list permohonan
		context.put("listPermohonan", listPageDepan);
		context.put("list_size", listPageDepan.size());

		// id
		context.put("id_permohonan", idpermohonan);
		context.put("id_status", id_status);
		context.put("id_fail", id_fail);

		setupPage(session, action, listPageDepan);
		this.context.put("selectedTab", selectedTab);
		return vm;

	}// close public template

	// --METHOD--//

	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,
			String id_permohonan, String id_fail,
			String id_suburusanstatusfailppt) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h, id_suburusanstatusfailppt,
				"1472");

	}// close updateSuburusanStatusFailPPT

	private void ListCarian(HttpSession session, String userIdNeg)
			throws Exception {

		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");

		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);

		FrmSek8EndosanMemorialDHDKData.setListCarian(nofail, tarikh, status,
				userIdNeg);

	}// close listcarian

	@SuppressWarnings("unchecked")
	private void listHakmilikPTG(HttpSession session, String idpermohonan)
			throws Exception {

		// data & list maklumat tanah
		model.setHakmilikPTG(idpermohonan);
		Vector listHakmilikPTG = model.getListHakmilikPTG();
		context.put("listHakmilikPTG", listHakmilikPTG);
		context.put("saiz_listHakmilikPTG", listHakmilikPTG.size());

	}// close listHakmilik

	@SuppressWarnings("unchecked")
	private void listHakmilikPTD(HttpSession session, String idpermohonan)
			throws Exception {

		// data & list maklumat tanah
		model.setHakmilikPTD(idpermohonan);
		Vector listHakmilikPTD = model.getListHakmilikPTD();
		context.put("listHakmilikPTD", listHakmilikPTD);
		context.put("saiz_listHakmilikPTD", listHakmilikPTD.size());

	}// close listHakmilik

	@SuppressWarnings("unchecked")
	private void listHakmilikPTGOnly(HttpSession session, String idpermohonan)
			throws Exception {

		// list hakmilik ptg shj
		model.setHakmilikPTGOnly(idpermohonan);
		Vector listHakmilikPTGOnly = model.getListHakmilikPTGOnly();
		context.put("listHakmilikPTGOnly", listHakmilikPTGOnly);
		context.put("saiz_listHakmilikPTGOnly", listHakmilikPTGOnly.size());

	}// close listHakmilik

	@SuppressWarnings("unchecked")
	private void listHakmilikPTDOnly(HttpSession session, String idpermohonan)
			throws Exception {

		// list hakmilik ptg shj
		model.setHakmilikPTDOnly(idpermohonan);
		Vector listHakmilikPTDOnly = model.getListHakmilikPTDOnly();
		context.put("listHakmilikPTDOnly", listHakmilikPTDOnly);
		context.put("saiz_listHakmilikPTDOnly", listHakmilikPTDOnly.size());

	}// close listHakmilik

	@SuppressWarnings({ "unchecked", "static-access" })
	private String dataHeaderStatus(HttpSession session, String idpermohonan)
			throws Exception {

		// header
		String id_pejabat_ptg = "";
		header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if (dataHeader.size() != 0) {
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_pejabat_ptg = (String) dh.get("id_pejabat_ptg");
		}

		return id_pejabat_ptg;

	}// close dataHeaderStatus

	@SuppressWarnings({ "unchecked", "static-access" })
	private String dataHeaderStatusPTD(HttpSession session, String idpermohonan)
			throws Exception {

		// header
		String id_pejabat_ptd = "";
		header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if (dataHeader.size() != 0) {
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_pejabat_ptd = (String) dh.get("id_pejabat_ptd");
		}

		return id_pejabat_ptd;

	}// close dataHeaderStatusPTD

	@SuppressWarnings("unchecked")
	private void hantar(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmSek8EndosanMemorialDHDKData.hantar(h);

	}// close hantar

	// @SuppressWarnings("unchecked")
	// private void updateNamaPejabatPTG(HttpSession session) throws Exception{
	//
	// Hashtable h = new Hashtable();
	//
	// h.put("id_permohonan", getParam("id_permohonan"));
	// h.put("id_user", session.getAttribute("_ekptg_user_id"));
	// h.put("socPejabatPTG", getParam("socPejabatPTG"));
	//
	// FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTG(h);
	//
	// }//close updateNamaPejabatPTG

	@SuppressWarnings("unchecked")
	private void simpanPilihanPTG(HttpSession session, String idfail)
			throws Exception {
		
		Db db = null;
		try {
			db = new Db();

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		String socPejabatPTG = getParam("socPejabatPTG");
		h.put("txtCatatanPTG", getParam("txtCatatanPTG"));
		String idUser = (String) session.getAttribute("_ekptg_user_id");

		String[] cbsemaks = request.getParameterValues("cbsemaks");

		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				FrmSek8EndosanMemorialDHDKData.simpanFlagPilihanPTG(h, idUser,
						cbsemaks[i],db);
			}
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTG(h,
					socPejabatPTG,db);
		} else {
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTG(h, "",db);
		}
		
		} finally {
			if (db != null)
				db.close();
		}

	}// close simpanPilihanPTG

	@SuppressWarnings("unchecked")
	private void simpanPilihanPTD(HttpSession session, String idfail)
			throws Exception {
		
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTD", getParam("txtCatatanPTD"));
		String socPejabatPTD = getParam("socPejabatPTD");

		String idUser = (String) session.getAttribute("_ekptg_user_id");

		String[] cbsemaks = request.getParameterValues("cbsemaks");

		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				FrmSek8EndosanMemorialDHDKData.simpanFlagPilihanPTD(h, idUser,
						cbsemaks[i],db);
			}
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTD(h,
					socPejabatPTD,db);
		} else {
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTD(h, "", db);
		}
		
		}
		finally {
			if (db != null)
				db.close();
		}

	}// close simpanPilihanPTG

	@SuppressWarnings("unchecked")
	
	private void updatePilihanPTG(HttpSession session, String idfail)
			throws Exception {
		Db db = null;
		try {
			db = new Db();
		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTG", getParam("txtCatatanPTG"));
		String socPejabatPTG = getParam("socPejabatPTG");

		String idUser = (String) session.getAttribute("_ekptg_user_id");
		String idPermohonan = getParam("id_permohonan");

		FrmSek8EndosanMemorialDHDKData.deleteFlagPilihanPTG(idUser,
				idPermohonan,db);

		String[] cbsemaks = request.getParameterValues("cbsemaks");

		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				FrmSek8EndosanMemorialDHDKData.simpanFlagPilihanPTG(h, idUser,
						cbsemaks[i],db);
			}
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTG(h,
					socPejabatPTG,db);
		} else {
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTG(h, "",db);
		}
		
		} finally {
			if (db != null)
				db.close();
		}

	}// close updatePilihanPTG

	@SuppressWarnings("unchecked")
	
	private void updatePilihanPTD(HttpSession session, String idfail)
			throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", idfail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("txtCatatanPTD", getParam("txtCatatanPTD"));
		String socPejabatPTD = getParam("socPejabatPTD");
		String idUser = (String) session.getAttribute("_ekptg_user_id");
		String idPermohonan = getParam("id_permohonan");
		
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

		FrmSek8EndosanMemorialDHDKData.deleteFlagPilihanPTD(idUser,
				idPermohonan,db);
		String[] cbsemaks = request.getParameterValues("cbsemaks");		
		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				FrmSek8EndosanMemorialDHDKData.simpanFlagPilihanPTD(h, idUser,
						cbsemaks[i],db);
			}
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTD(h,
					socPejabatPTD,db);
		} else {
			FrmSek8EndosanMemorialDHDKData.updateNamaPejabatPTD(h, "",db);
		}
		
		}
		finally {
			if (db != null)
				db.close();
		}

	}// close updatePilihanPTD

	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("listPermohonan", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}

}// close class
