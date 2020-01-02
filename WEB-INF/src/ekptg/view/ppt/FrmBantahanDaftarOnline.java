package ekptg.view.ppt;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.BantahanDaftar;
import ekptg.model.ppt.BantahanDaftarOnline;
import ekptg.model.ppt.BantahanDaftarOperations;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.PPTHeader;

public class FrmBantahanDaftarOnline extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmBantahanSenaraiOnline.class);

	// MODEL
	BantahanDaftar model = new BantahanDaftar();
	BantahanDaftarOnline modelOnline = new BantahanDaftarOnline();
	BantahanDaftarOperations modelOperations = new BantahanDaftarOperations();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();

	// MODEL ONLINE
	// BantahanAgensiDaftarOnline modelOnlinePB = new
	// BantahanAgensiDaftarOnline();
	BantahanDaftarOnline modelOnlinePB = new BantahanDaftarOnline();

	// DECLARATION
	String checkedsbcBantahan1 = "";
	String checkedsbcBantahan2 = "";
	String checkedsbcBantahan3 = "";
	String checkedsbcBantahan4 = "";

	@Override
	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		// get user login detail
		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");
		userData(usid);

		String vm = "";
		String noLOT = "";
		String idpegawai = "";

		Vector listHeader = null;
		Vector listC = null;
		Vector listE = null;
		Vector listH = null;
		Vector listJ = null;
		Vector maklumatBantahan = null;

		Vector dataNamaPengarah = new Vector();
		Vector listMaklumatTanah = new Vector();
		Vector getIdSuburusanstatusfail = new Vector();
		Vector dataSuburusanHakmilik = new Vector();
		Vector listPageDepan = new Vector();

		dataNamaPengarah.clear();
		listMaklumatTanah.clear();
		listPageDepan.clear();

		String doPost = (String) session.getAttribute("doPost");
		String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
		String submit = getParam("command");
		myLogger.info("SUBMIT :: " + submit);
		this.context.put("Util", new lebah.util.Util()); // UNTUK FORMAT
															// UTIL.DECIMAL (EX:
															// 12,000.00)

		String location = getParam("location");
		context.put("location", location);

		String point = getParam("point");
		context.put("point", point);

		String id_fail = getParam("id_fail");
		context.put("id_fail", id_fail);

		String id_permohonan = getParam("id_permohonan");
		context.put("id_permohonan", id_permohonan);

		String id_hakmilik = getParam("id_hakmilik");
		context.put("id_hakmilik", id_hakmilik);

		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		context.put("id_pihakberkepentingan", id_pihakberkepentingan);

		String id_hakmilikpb = getParam("id_hakmilikpb");
		context.put("id_hakmilikpb", id_hakmilikpb);

		String status_bantahan = getParam("status_bantahan");
		context.put("status_bantahan", status_bantahan);

		String id_bantahan = getParam("id_bantahan");
		context.put("id_bantahan", "id_bantahan");

		// myLogger.info("id_permohonan :: "+id_permohonan);
		// myLogger.info("id_hakmilik :: "+id_hakmilik);
		// myLogger.info("id_pihakberkepentingan :: "+id_pihakberkepentingan);
		// myLogger.info("id_hakmilikpb :: "+id_hakmilikpb);
		// myLogger.info("status_bantahan :: "+status_bantahan);
		// myLogger.info("id_bantahan :: "+id_bantahan);

		listHeader = model.getHeader(id_fail, id_permohonan);
		context.put("Header", listHeader);
		String idNegeriMhn = "";
		if (listHeader.size() != 0) {
			Hashtable h = (Hashtable) listHeader.get(0);
			idNegeriMhn = h.get("id_negeri").toString();
			context.put("idNegeriMhn", idNegeriMhn);
		}

		Hashtable statusFail = model.getStatusFail(id_permohonan);
		this.context.put("statusFail", statusFail);

		String selectedtab = getParam("selectedtab");
		if ("".equals(selectedtab)) {
			selectedtab = "0";
		}
		this.context.put("selectedtab", selectedtab);

		// CLEARKAN FORM
		String key = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
			key = (String) allparam.nextElement();
			value = request.getParameter(key);
			this.context.put(key, value);
		}
		// END

		// HEADER
		String negeriMMK = "";
		header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if (dataHeader.size() != 0) {
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_fail = (String) dh.get("id_fail");
			negeriMMK = (String) dh.get("id_projekNegeri");
		}

		// get size suburusanhakmilik
		String id_suburusanstatushakmilik = "";
		modelUPT.setDataSuburusanHakmilik(id_hakmilik);
		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
		if (dataSuburusanHakmilik.size() != 0) {
			Hashtable dsh = (Hashtable) dataSuburusanHakmilik.get(0);
			id_suburusanstatushakmilik = (String) dsh
					.get("id_suburusanstatushakmilik");
		}

		// get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(id_permohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if (getIdSuburusanstatusfail.size() != 0) {
			Hashtable idsb = (Hashtable) getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String) idsb
					.get("id_suburusanstatusfailppt");
		}

		// screen jsp
		String skrinDaftarOnline = "app/ppt/frmBantahanDaftarOnline.jsp";
		String skrinListPermohonanPB = "app/ppt/frmBantahanSenaraiOnline.jsp";
		String listHMscreen = "app/ppt/frmBantahanListHakmilikPBOnline.jsp";

		if ("screenBorangN".equals(submit)) {

			// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
			modelOnline.setListMaklumatTanah_DgnBantahan(id_hakmilikpb, usid,
					"");

			listMaklumatTanah = modelOnline.getListMaklumatTanahBantahan();

			context.put("saiz_listTanah", listMaklumatTanah.size());

			// GETHAKMILIK
			getValueHakmilik(id_hakmilik, usid);

			// NEW FORM
			if (listMaklumatTanah.size() == 0) {

				// GET CURRENT DATE
				context.put("txdMohon",
						lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));

				// CLEAR
				context.put("clearForm", "yes");
				context.put("mode", "");
				context.put("button", "add");
				context.put("flag", "");

				// VIEW FORM
			} else {

				Hashtable a = (Hashtable) listMaklumatTanah.get(0);
				id_bantahan = (String) a.get("id_bantahan");

				context.put("id_bantahan", id_bantahan);

				// GET DATA BANTAHAN
				getDataBantahan(id_permohonan, id_hakmilikpb, id_bantahan);

				// CLEAR
				context.put("clearForm", "");
				context.put("mode", "disabled");
				context.put("button", "view");
				context.put("flag", "semak");
			}

			vm = skrinDaftarOnline;

		} else if ("doChangeNegeri".equals(submit)) {

			String id_negeri = getParam("socNegeri");
			context.put("id_negeri", id_negeri);

			// DROP DOWN
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
					Utils.parseLong(id_negeri),
					"style=width:300px onChange=\"doChangeNegeri();\""));

			if (id_negeri != "") {
				context.put("selectBandar", HTML.SelectBandarByNegeri(
						id_negeri, "socBandar", Utils.parseLong(id_negeri),
						"style=width:300px"));
			} else {
				context.put("selectBandar", HTML.SelectBandar("socBandar",
						null, "style=width:300px"));
			}

			vm = skrinDaftarOnline;

		} else if ("sahkan_permohonan".equals(submit)) {

			// UPDATE FLAG ONLINE
			updateFlagOnline(id_bantahan, usid);

			// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
			modelOnline.setListMaklumatTanah_DgnBantahan(id_hakmilikpb, usid,
					"");
			listMaklumatTanah = modelOnline.getListMaklumatTanahBantahan();
			context.put("saiz_listTanah", listMaklumatTanah.size());
			if (listMaklumatTanah.size() != 0) {
				Hashtable a = (Hashtable) listMaklumatTanah.get(0);
				id_bantahan = (String) a.get("id_bantahan");
			}

			context.put("id_bantahan", id_bantahan);

			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,
					id_hakmilikpb, id_bantahan);
			context.put("getMaklumatBantahan", maklumatBantahan);
			String id_status_bantahan = "";
			if (maklumatBantahan.size() != 0) {
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String) b
						.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String) b
						.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String) b.get("flag_ukur_luas");
				String flag_pampasan = (String) b.get("flag_pampasan");
				id_status_bantahan = (String) b.get("status_bantahan");

				if (flag_penerima_pampasan.equals("Y")) {
					setValueBantahanTerhadap("checked", "", "", "");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "checked", "", "");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")) {
					setValueBantahanTerhadap("", "", "checked", "");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "", "", "checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}

			context.put("id_status_bantahan", id_status_bantahan);

			// GETHAKMILIK
			Hashtable getHakmilik = modelOnline.getHakmilik(id_hakmilik, usid);
			String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
			id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
			id_pihakberkepentingan = (String) getHakmilik
					.get("id_pihakberkepentingan");
			String no_lotpt = (String) getHakmilik.get("no_lotpt");
			String nama_pb = (String) getHakmilik.get("nama_pb");
			String alamat1 = (String) getHakmilik.get("alamat1");
			String alamat2 = (String) getHakmilik.get("alamat2");
			String alamat3 = (String) getHakmilik.get("alamat3");
			String poskod = (String) getHakmilik.get("poskod");
			String id_bandar = (String) getHakmilik.get("id_bandar");
			String id_negeri = (String) getHakmilik.get("id_negeri");

			context.put("id_pihakberkepentingan", id_pihakberkepentingan);
			context.put("id_hakmilikpb", id_hakmilikpb);
			context.put("txtNoHakmilik", no_hakmilik);
			context.put("txtNoLot", no_lotpt);
			context.put("txtNamaPembantah", nama_pb);
			context.put("txtAlamat1", alamat1);
			context.put("txtAlamat2", alamat2);
			context.put("txtAlamat3", alamat3);
			context.put("txtPoskod", poskod);
			context.put("id_bandar", id_bandar);
			context.put("id_negeri", id_negeri);

			// DROP DOWN
			context.put(
					"selectNegeri",
					HTML.SelectNegeriMampu("socNegeri",
							Utils.parseLong(id_negeri), null,
							"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
			context.put("selectBandar", HTML.SelectBandar("socBandar",
					Utils.parseLong(id_bandar), "style=width:300px disabled"));

			// CLEAR
			context.put("clearForm", "");
			context.put("mode", "disabled");
			context.put("button", "after_sahkan");
			context.put("flag", "semak");

			vm = skrinDaftarOnline;

		} else if ("add_bantahanOnline".equals(submit)) { // FASA 1

			if (doPost.equals("true")) {

				// INSERT TBLPPTBANTAHAN & INSERT TBLPPTSUBURUSANSTATUSBANTAHAN
				// & UPDATE TBLPPTHAKMILIKPB
				String result = "";
				if (doPost.equals("true")) {
					// simpan data
					result = daftarBantahan(usid);
				}

				context.put("id_bantahan", result);

				id_bantahan = result;

				// UPDATE TBLPPTPERMOHONAN
				updateStatusDalamProses(id_permohonan, usid);

				// UPDATE TBLPPTSUBURUSANHAKMILIK
				// updateSuburusanHakmilik(session,id_permohonan,id_fail,id_hakmilik,id_suburusanstatushakmilik);

				// UPDATE TBLPPTSUBURUSANSTATUSFAILPPT
				// updateSuburusanStatusFailPPT(session,id_permohonan,id_fail,id_suburusanstatusfailppt);

				// UPDATE TBLPPTRUJSUBURUSANSTATUSBANTAHAN
				// updateSuburusanStatusBantahan(usid,id_bantahan,id_permohonan,id_hakmilik,id_fail);
			}

			// GET MAX NO SIASATAN
			listH = model.getNoSiasatan(id_permohonan, id_hakmilik);
			context.put("getNoSiasatan", listH);
			String _MaxIdSiasatan = "";
			if (listH.size() != 0) {
				Hashtable b = (Hashtable) listH.get(0);
				_MaxIdSiasatan = (String) b.get("id_siasatan");
			}
			// END

			// GET MAX NO WARTA
			listJ = model.getNoWarta(id_permohonan);
			context.put("getNoSiasatan", listJ);
			String id_warta = "";
			if (listJ.size() != 0) {
				Hashtable e = (Hashtable) listJ.get(0);
				id_warta = e.get("id_warta").toString();
			}
			// END

			maklumatBantahan = model.getMaklumatBantahan(id_hakmilikpb,
					_MaxIdSiasatan, id_warta);
			context.put("getMaklumatBantahan", maklumatBantahan);
			if (maklumatBantahan.size() != 0) {
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String id_negeri = (String) b.get("id_negeri");
				String id_bandar = (String) b.get("id_bandar");
				String jenis_pembantah = (String) b.get("jenis_pembantah");
				String flag_penerima_pampasan = (String) b
						.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String) b
						.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String) b.get("flag_ukur_luas");
				String flag_pampasan = (String) b.get("flag_pampasan");

				if (jenis_pembantah.equals("1")) {
					setValueJenisPembantah("1", "");
				} else {
					setValueJenisPembantah("", "2");
				}

				if (flag_penerima_pampasan.equals("Y")) {
					setValueBantahanTerhadap("checked", "", "", "");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "checked", "", "");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")) {
					setValueBantahanTerhadap("", "", "checked", "");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "", "", "checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}

			// GETHAKMILIK
			Hashtable getHakmilik = modelOnline.getHakmilik(id_hakmilik, usid);
			String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
			id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
			String no_lotpt = (String) getHakmilik.get("no_lotpt");
			String nama_pb = (String) getHakmilik.get("nama_pb");
			String alamat1 = (String) getHakmilik.get("alamat1");
			String alamat2 = (String) getHakmilik.get("alamat2");
			String alamat3 = (String) getHakmilik.get("alamat3");
			String poskod = (String) getHakmilik.get("poskod");
			String id_bandar = (String) getHakmilik.get("id_bandar");
			String id_negeri = (String) getHakmilik.get("id_negeri");

			context.put("id_hakmilikpb", id_hakmilikpb);
			context.put("txtNoHakmilik", no_hakmilik);
			context.put("txtNoLot", no_lotpt);
			context.put("txtNamaPembantah", nama_pb);
			context.put("txtAlamat1", alamat1);
			context.put("txtAlamat2", alamat2);
			context.put("txtAlamat3", alamat3);
			context.put("txtPoskod", poskod);
			context.put("id_bandar", id_bandar);
			context.put("id_negeri", id_negeri);

			// DROP DOWN
			context.put(
					"selectNegeri",
					HTML.SelectNegeriMampu("socNegeri",
							Utils.parseLong(id_negeri), null,
							"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
			context.put("selectBandar", HTML.SelectBandar("socBandar",
					Utils.parseLong(id_bandar), "style=width:300px disabled"));

			// CLEAR
			context.put("clearForm", "");
			context.put("mode", "disabled");
			context.put("button", "view");
			context.put("flag", "semak");

			vm = skrinDaftarOnline;

		} else if ("kemaskiniBantahan".equals(submit)) {

			// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
			modelOnline.setListMaklumatTanah_DgnBantahan(id_hakmilikpb, usid,
					"");
			listMaklumatTanah = modelOnline.getListMaklumatTanahBantahan();
			context.put("saiz_listTanah", listMaklumatTanah.size());
			Hashtable a = (Hashtable) listMaklumatTanah.get(0);
			id_bantahan = (String) a.get("id_bantahan");
			context.put("id_bantahan", id_bantahan);

			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,
					id_hakmilikpb, id_bantahan);
			context.put("getMaklumatBantahan", maklumatBantahan);
			if (maklumatBantahan.size() != 0) {
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String) b
						.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String) b
						.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String) b.get("flag_ukur_luas");
				String flag_pampasan = (String) b.get("flag_pampasan");

				if (flag_penerima_pampasan.equals("Y")) {
					setValueBantahanTerhadap("checked", "", "", "");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "checked", "", "");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")) {
					setValueBantahanTerhadap("", "", "checked", "");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "", "", "checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}

			// GETHAKMILIK
			Hashtable getHakmilik = modelOnline.getHakmilik(id_hakmilik, usid);
			String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
			id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
			id_pihakberkepentingan = (String) getHakmilik
					.get("id_pihakberkepentingan");
			String no_lotpt = (String) getHakmilik.get("no_lotpt");
			String nama_pb = (String) getHakmilik.get("nama_pb");
			String alamat1 = (String) getHakmilik.get("alamat1");
			String alamat2 = (String) getHakmilik.get("alamat2");
			String alamat3 = (String) getHakmilik.get("alamat3");
			String poskod = (String) getHakmilik.get("poskod");
			String id_bandar = (String) getHakmilik.get("id_bandar");
			String id_negeri = (String) getHakmilik.get("id_negeri");

			context.put("id_pihakberkepentingan", id_pihakberkepentingan);
			context.put("id_hakmilikpb", id_hakmilikpb);
			context.put("txtNoHakmilik", no_hakmilik);
			context.put("txtNoLot", no_lotpt);
			context.put("txtNamaPembantah", nama_pb);
			context.put("txtAlamat1", alamat1);
			context.put("txtAlamat2", alamat2);
			context.put("txtAlamat3", alamat3);
			context.put("txtPoskod", poskod);
			context.put("id_bandar", id_bandar);
			context.put("id_negeri", id_negeri);

			// DROP DOWN
			context.put(
					"selectNegeri",
					HTML.SelectNegeriMampu("socNegeri",
							Utils.parseLong(id_negeri), null,
							"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
			context.put("selectBandar", HTML.SelectBandar("socBandar",
					Utils.parseLong(id_bandar), "style=width:300px disabled"));

			// CLEAR
			context.put("clearForm", "");
			context.put("mode", "");
			context.put("button", "edit");
			context.put("flag", "semak");

			vm = skrinDaftarOnline;

		} else if ("simpanEditBantahan".equals(submit)) {

			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,
					id_hakmilikpb, id_bantahan);
			String id_status_bantahan = "";
			if (maklumatBantahan.size() != 0) {
				Hashtable idsb = (Hashtable) maklumatBantahan.get(0);
				id_status_bantahan = (String) idsb.get("status_bantahan");
			}

			context.put("id_status_bantahan", id_status_bantahan);

			// ---UPDATE
			simpanEditBantahan(id_bantahan, usid, id_status_bantahan);

			// GET LIST HAKMILIK YG DAH BUAT BANTAHAN
			modelOnline.setListMaklumatTanah_DgnBantahan(id_hakmilikpb, usid,
					"");
			listMaklumatTanah = modelOnline.getListMaklumatTanahBantahan();
			context.put("saiz_listTanah", listMaklumatTanah.size());
			Hashtable a = (Hashtable) listMaklumatTanah.get(0);
			id_bantahan = (String) a.get("id_bantahan");
			context.put("id_bantahan", id_bantahan);

			maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,
					id_hakmilikpb, id_bantahan);
			context.put("getMaklumatBantahan", maklumatBantahan);
			if (maklumatBantahan.size() != 0) {
				Hashtable b = (Hashtable) maklumatBantahan.get(0);
				String flag_penerima_pampasan = (String) b
						.get("flag_penerima_pampasan");
				String flag_bahagian_pampasan = (String) b
						.get("flag_bahagian_pampasan");
				String flag_ukur_luas = (String) b.get("flag_ukur_luas");
				String flag_pampasan = (String) b.get("flag_pampasan");
				if (flag_penerima_pampasan.equals("Y")) {
					setValueBantahanTerhadap("checked", "", "", "");
					context.put("TEMPchecked1", checkedsbcBantahan1);
				}
				if (flag_bahagian_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "checked", "", "");
					context.put("TEMPchecked2", checkedsbcBantahan2);
				}
				if (flag_ukur_luas.equals("Y")) {
					setValueBantahanTerhadap("", "", "checked", "");
					context.put("TEMPchecked3", checkedsbcBantahan3);
				}
				if (flag_pampasan.equals("Y")) {
					setValueBantahanTerhadap("", "", "", "checked");
					context.put("TEMPchecked4", checkedsbcBantahan4);
				}
			}

			// GETHAKMILIK
			Hashtable getHakmilik = modelOnline.getHakmilik(id_hakmilik, usid);
			String no_hakmilik = (String) getHakmilik.get("no_hakmilik");
			id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
			id_pihakberkepentingan = (String) getHakmilik
					.get("id_pihakberkepentingan");
			String no_lotpt = (String) getHakmilik.get("no_lotpt");
			String nama_pb = (String) getHakmilik.get("nama_pb");
			String alamat1 = (String) getHakmilik.get("alamat1");
			String alamat2 = (String) getHakmilik.get("alamat2");
			String alamat3 = (String) getHakmilik.get("alamat3");
			String poskod = (String) getHakmilik.get("poskod");
			String id_bandar = (String) getHakmilik.get("id_bandar");
			String id_negeri = (String) getHakmilik.get("id_negeri");

			context.put("id_pihakberkepentingan", id_pihakberkepentingan);
			context.put("id_hakmilikpb", id_hakmilikpb);
			context.put("txtNoHakmilik", no_hakmilik);
			context.put("txtNoLot", no_lotpt);
			context.put("txtNamaPembantah", nama_pb);
			context.put("txtAlamat1", alamat1);
			context.put("txtAlamat2", alamat2);
			context.put("txtAlamat3", alamat3);
			context.put("txtPoskod", poskod);
			context.put("id_bandar", id_bandar);
			context.put("id_negeri", id_negeri);

			// DROP DOWN
			context.put(
					"selectNegeri",
					HTML.SelectNegeriMampu("socNegeri",
							Utils.parseLong(id_negeri), null,
							"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
			context.put("selectBandar", HTML.SelectBandar("socBandar",
					Utils.parseLong(id_bandar), "style=width:300px disabled"));

			// CLEAR
			context.put("clearForm", "");
			context.put("mode", "disabled");
			context.put("button", "view");
			context.put("flag", "semak");

			vm = skrinDaftarOnline;

		} else if ("papar_ListHakmilik".equals(submit)) {

			// GET TOTAL HAKMILIK
			modelUPT.setListMaklumatTanah(id_permohonan, "", "");
			listMaklumatTanah = modelUPT.getListMaklumatTanah();
			context.put("saiz_listTanah", listMaklumatTanah.size());

			id_bantahan = getParam("id_bantahan");
			context.put("id_bantahan", id_bantahan);

			noLOT = getParam("carianNoLot");
			context.put("carianNoLot", noLOT.trim());

			// list maklumat tanah
			listHakmilik(id_permohonan, usid, noLOT);

			vm = listHMscreen;

		} else if ("search_data".equals(submit)) {

			carianBantahan(usid);
			listPageDepan = model.getListCarian();

			// data & size default list
			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());
			setupPageBantahan(session, action, listPageDepan);

			// maintain searching value
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context
					.put("tarikh_permohonan", getParam("tarikh_permohonan"));

			vm = skrinListPermohonanPB;

		} else {

			String txtNoFail = "";
			context.put("txtNoFail", "");

			String tarikh_permohonan = "";
			context.put("tarikh_permohonan", tarikh_permohonan);

			// GET LIST DATA
			listPageDepan = modelOnlinePB.getListPemohonPortal(usid);

			context.put("PermohonanList", listPageDepan);
			context.put("list_size", listPageDepan.size());

			vm = skrinListPermohonanPB;
		}
		setupPageBantahan(session, action, listPageDepan);
		return vm;
	}

	// METHOD

	@SuppressWarnings("unchecked")
	public void getDataBantahan(String id_permohonan, String id_hakmilikpb,
			String id_bantahan) throws Exception {

		Vector maklumatBantahan = new Vector();
		maklumatBantahan.clear();

		maklumatBantahan = modelOnline.getMaklumatBantahan(id_permohonan,
				id_hakmilikpb, id_bantahan);
		context.put("getMaklumatBantahan", maklumatBantahan);
		String id_status_bantahan = "";
		if (maklumatBantahan.size() != 0) {
			Hashtable b = (Hashtable) maklumatBantahan.get(0);
			String flag_penerima_pampasan = (String) b
					.get("flag_penerima_pampasan");
			String flag_bahagian_pampasan = (String) b
					.get("flag_bahagian_pampasan");
			String flag_ukur_luas = (String) b.get("flag_ukur_luas");
			String flag_pampasan = (String) b.get("flag_pampasan");
			id_status_bantahan = (String) b.get("status_bantahan");

			if (flag_penerima_pampasan.equals("Y")) {
				setValueBantahanTerhadap("checked", "", "", "");
				context.put("TEMPchecked1", checkedsbcBantahan1);
			}
			if (flag_bahagian_pampasan.equals("Y")) {
				setValueBantahanTerhadap("", "checked", "", "");
				context.put("TEMPchecked2", checkedsbcBantahan2);
			}
			if (flag_ukur_luas.equals("Y")) {
				setValueBantahanTerhadap("", "", "checked", "");
				context.put("TEMPchecked3", checkedsbcBantahan3);
			}
			if (flag_pampasan.equals("Y")) {
				setValueBantahanTerhadap("", "", "", "checked");
				context.put("TEMPchecked4", checkedsbcBantahan4);
			}
		}

		context.put("id_status_bantahan", id_status_bantahan);

	}// close getDataBantahan

	@SuppressWarnings("unchecked")
	public void getValueHakmilik(String id_hakmilik, String usid)
			throws Exception {

		String id_hakmilikpb = "";
		String id_pihakberkepentingan = "";
		String no_hakmilik = "";
		String no_lotpt = "";
		String nama_pb = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		String id_bandar = "";
		String id_negeri = "";
		String id_kementerian = "";
		String id_agensi = "";
		Hashtable getHakmilik = modelOnline.getHakmilik(id_hakmilik, usid);
		if (getHakmilik.size() != 0) {
			no_hakmilik = (String) getHakmilik.get("no_hakmilik");
			id_hakmilikpb = (String) getHakmilik.get("id_hakmilikpb");
			no_lotpt = (String) getHakmilik.get("no_lotpt");
			nama_pb = (String) getHakmilik.get("nama_pb");
			alamat1 = (String) getHakmilik.get("alamat1");
			alamat2 = (String) getHakmilik.get("alamat2");
			alamat3 = (String) getHakmilik.get("alamat3");
			poskod = (String) getHakmilik.get("poskod");
			id_bandar = (String) getHakmilik.get("id_bandar");
			id_negeri = (String) getHakmilik.get("id_negeri");
			id_pihakberkepentingan = (String) getHakmilik
					.get("id_pihakberkepentingan");
			id_kementerian = (String) getHakmilik.get("id_kementerian");
			id_agensi = (String) getHakmilik.get("id_agensi");
		}

		context.put("id_kementerian", id_kementerian);
		context.put("id_agensi", id_agensi);
		context.put("id_pihakberkepentingan", id_pihakberkepentingan);
		context.put("id_hakmilikpb", id_hakmilikpb);
		context.put("txtNoHakmilik", no_hakmilik);
		context.put("txtNoLot", no_lotpt);
		context.put("txtNamaPembantah", nama_pb);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);
		context.put("txtPoskod", poskod);
		context.put("id_bandar", id_bandar);
		context.put("id_negeri", id_negeri);

		// DROP DOWN
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
				Utils.parseLong(id_negeri), null,
				"style=width:300px onChange=\"doChangeNegeri();\" disabled "));
		context.put("selectBandar", HTML.SelectBandar("socBandar",
				Utils.parseLong(id_bandar), "style=width:300px disabled "));

	}// close getValueHakmilik

	private void updateSuburusanStatusBantahan(String usid, String id_bantahan,
			String id_permohonan, String id_hakmilik, String id_fail)
			throws Exception {

		// update suburusanstatusfailppt
		// modelOperations.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1499");

	}// close updateSuburusanStatusBantahan

	public void setValueBantahanTerhadap(String checkedsbcBantahan1,
			String checkedsbcBantahan2, String checkedsbcBantahan3,
			String checkedsbcBantahan4) {
		this.checkedsbcBantahan1 = checkedsbcBantahan1;
		this.checkedsbcBantahan2 = checkedsbcBantahan2;
		this.checkedsbcBantahan3 = checkedsbcBantahan3;
		this.checkedsbcBantahan4 = checkedsbcBantahan4;
	}

	public void setValueJenisPembantah(String PB, String AP) {
		context.put("jenis_pembantah", PB);
		context.put("jenis_pembantah", AP);
	}

	private void simpanEditBantahan(String id_bantahan, String usid,
			String id_status_bantahan) throws Exception {

		String txdMohon = getParam("txdMohon");
		String txdBrgN = getParam("txdBrgN");
		String txtKptgnAtasTnh = getParam("txtKptgnAtasTnh");

		// BANTAHAN TERHADAP
		String ukuran_luas = getParam("ukuran_luas");
		String amaun_pampasan = getParam("amaun_pampasan");
		String terima_pampasan = getParam("terima_pampasan");
		String umpuk_pampasan = getParam("umpuk_pampasan");

		String txtAmaunTuntutan = getParam("txtAmaunTuntutan");
		String txtAlasanBantahan = getParam("txtAlasanBantahan");

		modelOnline.simpanEditBantahan(usid, id_bantahan, txdMohon, txdBrgN,
				txtKptgnAtasTnh, ukuran_luas, amaun_pampasan, terima_pampasan,
				umpuk_pampasan, txtAmaunTuntutan, txtAlasanBantahan,
				id_status_bantahan);
	}

	private void updateSuburusanStatusFailPPT(HttpSession session,
			String id_permohonan, String id_fail,
			String id_suburusanstatusfailppt) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// update suburusanstatusfailppt
		modelOperations.updateSuburusanStatusFailPPT(h,
				id_suburusanstatusfailppt, "1499");

	}// close updateSuburusanStatusFailPPT

	private void updateSuburusanHakmilik(HttpSession session,
			String id_permohonan, String id_fail, String id_hakmilik,
			String id_suburusanstatushakmilik) throws Exception {

		Hashtable h = new Hashtable();
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelOperations.updateSuburusanHakmilik(h, id_suburusanstatushakmilik,
				"1499");

	}// close addSuburusanHakmilik

	private void updateStatusDalamProses(String id_permohonan, String usid)
			throws Exception {
		modelOperations.updateStatusDalamProses(id_permohonan, usid);

	}

	private void updateFlagOnline(String id_bantahan, String usid)
			throws Exception {
		modelOnline.updateFlagOnline(id_bantahan, usid);

	}

	private String daftarBantahan(String usid) throws Exception {

		String txdMohon = getParam("txdMohon");
		String txdBrgN = getParam("txdBrgN");
		String txtNoLot = getParam("txtNoLot");
		String txtNoHakmilik = getParam("txtNoHakmilik");

		String txtNama = getParam("txtNamaPembantah");
		String txtAlamat1 = getParam("txtAlamat1");
		String txtAlamat2 = getParam("txtAlamat2");
		String txtAlamat3 = getParam("txtAlamat3");
		String txtPoskod = getParam("txtPoskod");
		String txtKptgnAtasTnh = getParam("txtKptgnAtasTnh");
		String txtAlasanBantahan = getParam("txtAlasanBantahan");

		String id_kementerian = getParam("id_kementerian");
		String id_agensi = getParam("id_agensi");
		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilik = getParam("id_hakmilik");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		String jenis_pembantah = getParam("jenis_pembantah");

		// ALASAN BANTAHAN
		String ukuran_luas = getParam("ukuran_luas");
		String amaun_pampasan = getParam("amaun_pampasan");
		String terima_pampasan = getParam("terima_pampasan");
		String umpuk_pampasan = getParam("umpuk_pampasan");

		String txtAmaunTuntutan = getParam("txtAmaunTuntutan");
		String id_permohonan = getParam("id_permohonan");

		return modelOnline.daftarBantahan(txdMohon, txdBrgN, txtNoLot,
				txtNoHakmilik, txtNama, txtAlamat1, txtAlamat2, txtAlamat3,
				txtPoskod, txtKptgnAtasTnh, txtAlasanBantahan, usid,
				jenis_pembantah, ukuran_luas, amaun_pampasan, id_kementerian,
				id_agensi, terima_pampasan, umpuk_pampasan,
				id_pihakberkepentingan, id_hakmilik, id_hakmilikpb,
				txtAmaunTuntutan, id_permohonan);
	}

	private void listHakmilik(String id_permohonan, String usid, String noLOT)
			throws Exception {

		Vector listMaklumatTanah = new Vector();
		listMaklumatTanah.clear();

		model.setListMaklumatTanah(id_permohonan, usid, noLOT);
		listMaklumatTanah = model.getListMaklumatTanah();
		context.put("listMaklumatTanah", listMaklumatTanah);
		context.put("saiz_listTanah", listMaklumatTanah.size());

	}// close listHakmilik

	private void carianBantahan(String usid) throws Exception {

		String txtNoFail = getParam("txtNoFail");
		String tarikh_permohonan = getParam("tarikh_permohonan");
		model.setCarianFailPBOnline(usid, txtNoFail, tarikh_permohonan);

	}

	@SuppressWarnings("unchecked")
	public void setupPageBantahan(HttpSession session, String action,
			Vector listPageDepan) {

		try {

			this.context.put("totalRecords", listPageDepan.size());
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

			Paging paging = new Paging(session, listPageDepan, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("PermohonanList", paging.getPage(page));
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

	private String userData(String id_user) throws Exception {

		Vector listUserid = new Vector();
		listUserid.clear();

		modelUPT.setGetUserId(id_user);
		listUserid = modelUPT.getUserIds();
		String userIdNeg = "";
		if (listUserid.size() != 0) {
			Hashtable t = (Hashtable) listUserid.get(0);
			userIdNeg = t.get("idNegeri").toString();
		}

		return userIdNeg;
	}

}
