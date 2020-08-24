package ekptg.view.ppk;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.PPKUtilHTML;
import ekptg.view.admin.Push;

public class FrmSenaraiNotisSek17 extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;

	static Logger myLogger = Logger.getLogger(FrmSenaraiNotisSek17.class);

	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = request.getSession();

		// command for paging
		headerppk_baru_default();

		String action = getParam("action");

		String vm = "";

		String screen1 = "app/ppk/frmPrmhnnSek17NotisPerbicaraan.jsp";
		String screen2 = "app/ppk/frmPrmhnnSek17Penjaga.jsp";
		String screen3 = "app/ppk/frmSenaraiNotisSek17.jsp";

		String doPost = (String) session.getAttribute("doPost");

		// define new vector
		Vector dataOBbyID = new Vector();
		Vector selectedOB = new Vector();
		Vector dataOBNotis = new Vector();
		Vector listMaklumatPenjaga = new Vector();
		Vector list = new Vector();
		Vector listPenerimaNotis = new Vector();
		Vector listOB = new Vector();
		Vector listOBatas18 = new Vector();
		Vector dataNotis = new Vector();
		Vector alamatTempatBicara = new Vector();
		Vector keputusanPermohonan = new Vector();
		Vector listPenjaga = new Vector();
		Vector dataPenjaga = new Vector();
		Vector selectedDropdown = new Vector();
		Vector namaOBnKP = new Vector();
		Vector penghantarNotisByJkptg = new Vector();
		Vector penghantarNotis = new Vector();
		Vector getPenghantarNotis = new Vector();
		Vector getSelectedPenghantarNotis = new Vector();
		Vector getSelectedPenghantarNotisByJkptg = new Vector();
		Vector listOBsemak = new Vector();
		Vector listCountOB = new Vector();//aishahlatip
		Vector listCountPenerimaNotis = new Vector();//aishahlatip
		Vector listSerahanNotis = new Vector();//aishahlatip

		// -- 23122009
		Vector idpemohonSek8 = new Vector();
		idpemohonSek8.clear();

		// -- 23122009
		Vector selectedOB17 = new Vector();
		selectedOB17.clear();

		// -- 08122009
		Vector cetakAkuanSumpah = new Vector();
		cetakAkuanSumpah.clear();
		
		//aishahlatip - tab pengesahan pegawai 26102017
		Vector validPegPengendali = new Vector();
		validPegPengendali.clear();
		
		/** ADD BY PEJE - SENARAI PEMIUTANG **/
		Vector listPemiutang = new Vector();
		listPemiutang.clear();

		listOBsemak.clear();
		getSelectedPenghantarNotisByJkptg.clear();
		getSelectedPenghantarNotis.clear();
		getPenghantarNotis.clear();
		penghantarNotis.clear();
		penghantarNotisByJkptg.clear();
		listOBatas18.clear();
		namaOBnKP.clear();
		selectedDropdown.clear();
		dataOBbyID.clear();
		selectedOB.clear();
		dataOBNotis.clear();
		listPenerimaNotis.clear();
		dataPenjaga.clear();
		listMaklumatPenjaga.clear();
		listPenjaga.clear();
		listOB.clear();
		list.clear();
		dataNotis.clear();
		alamatTempatBicara.clear();
		keputusanPermohonan.clear();

		context.put("Util", new lebah.util.Util());
		context.put("Utils", new ekptg.helpers.Utils());

		context.put("duplicate", "");
		context.put("addressReadonly", "readonly");
		context.put("addressReadonlyClass", "class='disabled'");
		context.put("onchangeMyList", "no");
		context.put("onchangeMyListDropdown", "no");
		context.put("alertBIL", "");

		context.put("id_penjaga", "");

		// -- 09122009
		context.put("jenisWaktu", "");
		context.put("open_pupop","");

		String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
		String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
		String flagForView = getParam("flagForView");

		// -- 07122009
		String flagFromKeputusanPermohonan = getParam("flag_KP");

		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");

		// modified on 19122009
		Vector listdepan = new Vector();
		listdepan.clear();
		//razman comment
		/*
		modelNotis.setListDefaultSek17(usid);
		listdepan = modelNotis.getListDefaultSek17();
		*/
		
		String lain2PeghantarNotis = getParam("lain2PeghantarNotis");
		/// Untuk check sama ada notis telah dihantar atau belum - Start
		
		String emailAddress = getParam("txtAlamatEmel"); //code wp
		String tarikhEmail = getParam("txdTarikhHantar"); //code wp
		/// Untuk check sama ada notis telah dihantar atau belum - Start

		String id = getParam("id_permohonan");

		// get idsuburusanstatusfail
		Vector getIdSuburusanstatusfail = new Vector();
		getIdSuburusanstatusfail.clear();
		getIdSuburusanstatusfail = modelNotis.getIdSubUrusanStatusFail(id);
		String id_suburusanstatusfail = "";
		if (getIdSuburusanstatusfail.size() != 0) {
			Hashtable idsubf = (Hashtable) getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfail = idsubf.get("id_suburusanstatusfail")
					.toString();
		}

		modelNotis.setListSemak(id, usid);
		list = modelNotis.getListSemak();
		// hashtable maklumat header
		headerppk_baru(session, id, "Y", "", "T");

		String id_permohonansimati = "";
		String id_permohonansimatiINT = "";
		String idsm = "";
		if (list.size() != 0) {
			Hashtable ls = (Hashtable) list.get(0);
			id_permohonansimati = ls.get("id_permohonansimati").toString();
			id_permohonansimatiINT = ls.get("id_permohonansimati").toString();
			idsm = ls.get("idSimati").toString();
		}

		// -- 23122009
		// get id pemohon seksyen 8
		idpemohonSek8 = modelNotis.getIdpemohonSek8(idsm);
		String id_pemohonSek8 = "";
		if (idpemohonSek8.size() != 0) {
			Hashtable i8 = (Hashtable) idpemohonSek8.get(0);
			id_pemohonSek8 = i8.get("id_pemohon").toString();
		}
		context.put("id_pemohonSek8", id_pemohonSek8);

		String submit = getParam("command");
		myLogger.info("[submit] NOTIS SEK 17 :: " + submit);
		System.out.println("[submit] NOTIS SEK 17 :: " + submit);

		if ("semakNoData".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}
			context.put("selectedTab", selectedTab);

			id = getParam("id_permohonan");

			usid = (String) session.getAttribute("_ekptg_user_id");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";
			String idFail = "";
			String idPejabatJKPTG = "";
			String idNegeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idFail = ls.get("idFail").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				idNegeri = ls.get("pmidnegeri").toString();
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			if (idNegeri != "") {
				context.put("xidnegerix", idNegeri);
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17", null);
			listOB = modelNotis.getListSemuaOB();
			
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			modelNotis.setListPemiutang(id_permohonansimati, idSimati, "17", null);
			listPemiutang = modelNotis.getListPemiutang();

			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());

			// list maklumat penjaga
			System.out.println("list maklumat penjaga");
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();
			
			//untuk check samada nilaian PPBPP telah dicetak atau belum - start
			Vector list2 = new Vector();
			//list2.clear();
			String id2 = getParam("id_permohonan");
			System.out.println("id2 = "+id2);
			System.out.println("usid = "+usid);
			logic_A.setDataPPSPPSek17(id2, usid);
			list2 = logic_A.getDataPPSPP();
			this.context.put("list2", list2);
			System.out.println("id_jkptg1********* = "+idPejabatJKPTG);
			
			//untuk check samada nilaian PPBPP telah dicetak atau belum - end

			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String negeri = "";

			if (idPejabatJKPTG != "") {

				alamatTempatBicara = modelNotis
						.getAlamatTempatBicara(idPejabatJKPTG);

				if (alamatTempatBicara.size() != 0) {

					Hashtable AB = (Hashtable) alamatTempatBicara.get(0);

					alamat1 = AB.get("alamat1").toString();
					alamat2 = AB.get("alamat2").toString();
					alamat3 = AB.get("alamat3").toString();
					poskod = AB.get("poskod").toString();
					negeri = AB.get("id_negeri").toString();
				}
			}

			context.put("poskod", poskod);
			context.put("alamat1", alamat1);
			context.put("alamat2", alamat2);
			context.put("alamat3", alamat3);

			if (negeri != "") {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Utils.parseLong(negeri),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang", listPemiutang);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang_size", listPemiutang.size());

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("idnegeri", idNegeri);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("id_fail", idFail);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// dropdown pejabat jkptg
			if (idPejabatJKPTG != "") {
				context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicaraByPejabatJKPTG(
												idPejabatJKPTG,
												"socTempatBicara",
												Utils.parseLong(idPejabatJKPTG),
												null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
			} else {
				context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicara("socTempatBicara",
												null, null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
			}

			/* tukar pegawai by negeri 09102009 */

			if (idNegeri != "") {
				context.put("selectPegawai", HTML
						.SelectPegawaiPengendaliByNegeri(idNegeri,
								"socPegawai", null, "style=width:305"));
			} else {
				context.put("selectPegawai", HTML.SelectPegawaiPengendali(
						"socPegawai", null, "style=width:305"));
			}

			// context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:305"));

			// set empty data
			context.put("txdTarikhBicara", "");
			context.put("txtMasaBicara", "");
			context.put("txdTarikhNotis", "");
			context.put("checkP1", "");
			context.put("checkP2", "");
			context.put("checkP3", "");

			// form validation
			context.put("viewdata", "no");
			context.put("edit", "no");
			
			int flag_cuti = 0;
			//aishahlatip15052017 : untuk dapatkan samaada negeri cuti am pada jumaat sabtu atau sabtu ahad
			if (idNegeri != "") {
				flag_cuti = modelNotis.getFlagCuti(idNegeri);
			}
			
			context.put("flag_cuti", flag_cuti);
			

			// screen
			vm = screen1;

		}// close semak

		else if ("onchangeJenisPejabat".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}
			context.put("selectedTab", selectedTab);

			id = getParam("id_permohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";
			String idFail = "";
			String idPejabatJKPTG = "";
			String idNegeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idFail = ls.get("idFail").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				idNegeri = ls.get("pmidnegeri").toString();
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			if (idNegeri != "") {
				context.put("xidnegerix", idNegeri);
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());

			// list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("idnegeri", idNegeri);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("id_fail", idFail);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);

			// form validation
			context.put("viewdata", "no");
			context.put("edit", "no");

			// negeri validation
			String negeriReadonly = "class=disabled disabled style=width:305";

			String jenispejabat = "";

			jenispejabat = getParam("jenisPejabat");

			if (jenispejabat.equals("1")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					context
							.put(
									"selectBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"socTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
				} else {
					context
							.put(
									"selectBicara",
									HTML
											.SelectTempatBicara(
													"socTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (jenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					context
							.put(
									"selectBicara",
									HTML
											.SelectPejabatTanahByJKPTG(
													idPejabatJKPTG,
													"socTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
				} else {
					context
							.put(
									"selectBicara",
									HTML
											.SelectPejabatTanah(
													"socTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (jenispejabat.equals("0")) {
				// textfield pejabat tanah
				context
						.put(
								"selectBicara",
								"<input type='text' name='socTempatBicara' value='' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52' maxlength='60'> ");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

				// address validation (editable)
				context.put("addressReadonly", "");
				context.put("addressReadonlyClass", "");

				// negeri validation
				negeriReadonly = "style=width:305";

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					context
							.put(
									"selectBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"socTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
				} else {
					context
							.put(
									"selectBicara",
									HTML
											.SelectTempatBicara(
													"socTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			// set empty data
			context.put("poskod", "");
			context.put("alamat1", "");
			context.put("alamat2", "");
			context.put("alamat3", "");

			context.put("selectNegeri", HTML.SelectNegeri("socNegeri", null,
					negeriReadonly));

			String tarikh_bicara = getParam("txdTarikhBicara");
			String masa_bicara = getParam("txtMasaBicara");
			String tarikh_notis = getParam("txdTarikhNotis");
			String idPegawai = getParam("socPegawai");

			context.put("txdTarikhBicara", tarikh_bicara);
			context.put("txtMasaBicara", masa_bicara);
			context.put("txdTarikhNotis", tarikh_notis);

			// -- 09122009
			context.put("jenisWaktu", getParam("socJenisWaktu"));

			if (idNegeri != "") {
				if (idPegawai != "") {
					context.put("selectPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"socPegawai", Utils.parseLong(idPegawai),
									"style=width:305"));
				} else {
					context.put("selectPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"socPegawai", null, "style=width:305"));
				}

			} else {
				if (idPegawai != "") {
					context.put("selectPegawai", HTML.SelectPegawaiPengendali(
							"socPegawai", Utils.parseLong(idPegawai),
							"style=width:305"));
				} else {
					context.put("selectPegawai", HTML.SelectPegawaiPengendali(
							"socPegawai", null, "style=width:305"));
				}
			}

			// screen
			vm = screen1;

		}// close onchange jenis pejabat

		// tukar notis
		else if ("tukarNotis".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}
			context.put("selectedTab", selectedTab);

			id = getParam("id_permohonan");

			usid = (String) session.getAttribute("_ekptg_user_id");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";
			String idPejabatJKPTG = "";
			String idFail = "";
			String idNegeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				idFail = ls.get("idFail").toString();
				idNegeri = ls.get("pmidnegeri").toString();
			}

			// --get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// --data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String bil = "";
			int _bil = 0;

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				bil = idn.get("bil_bicara").toString();
				_bil = Integer.parseInt(bil) + 1;
			}

			if (_bil > 3) {
				context.put("alertBIL", "yes");
			} else {
				context.put("alertBIL", "");
			}
			context.put("NOalertBIL", bil);

			// --get data ob
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "17",null);
			listOB = modelNotis.getListSemuaOB();
			
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			modelNotis.setListPemiutang(id_permohonansimati, idSimati, "17", null);
			listPemiutang = modelNotis.getListPemiutang();

			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());

			// --list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

			int idJKPTG = 0;
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String negeri = "";

			if (idPejabatJKPTG != "") {

				alamatTempatBicara = modelNotis
						.getAlamatTempatBicara(idPejabatJKPTG);

				if (alamatTempatBicara.size() != 0) {

					Hashtable AB = (Hashtable) alamatTempatBicara.get(0);

					alamat1 = AB.get("alamat1").toString();
					alamat2 = AB.get("alamat2").toString();
					alamat3 = AB.get("alamat3").toString();
					poskod = AB.get("poskod").toString();
					negeri = AB.get("id_negeri").toString();
				}
			}

			context.put("poskod", poskod);
			context.put("alamat1", alamat1);
			context.put("alamat2", alamat2);
			context.put("alamat3", alamat3);

			if (negeri != "") {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Utils.parseLong(negeri),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			// --list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);
			context.put("previousBil", _bil);
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang", listPemiutang);

			// --set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang_size", listPemiutang.size());
			
			

			// --id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("idnegeri", idNegeri);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("id_fail", idFail);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:305"));

			if (idPejabatJKPTG != "") {
				context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicaraByPejabatJKPTG(
												idPejabatJKPTG,
												"socTempatBicara",
												Utils.parseLong(idPejabatJKPTG),
												null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
			} else {
				context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicara("socTempatBicara",
												null, null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
			}

			/* tukar pegawai by negeri 09102009 */

			if (idNegeri != "") {
				context.put("selectPegawai", HTML
						.SelectPegawaiPengendaliByNegeri(idNegeri,
								"socPegawai", null, "style=width:305"));
				context.put("xidnegerix", idNegeri);
			} else {
				context.put("selectPegawai", HTML.SelectPegawaiPengendali(
						"socPegawai", null, "style=width:305"));
				context.put("xidnegerix", "");
			}

			// --set empty data
			context.put("txdTarikhBicara", "");
			context.put("txtMasaBicara", "");
			context.put("txdTarikhNotis", "");
			context.put("checkP1", "");
			context.put("checkP2", "");
			context.put("checkP3", "");

			// --form validation
			context.put("viewdata", "no");
			context.put("edit", "no");

			// screen
			vm = screen1;

		}// close tukar notis

		else if ("doChangeidTempatBicara".equals(submit)) {

			id = getParam("id_permohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idStatus = "";
			String idSimati = "";
			String idPejabatJKPTG = "";
			String idFail = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idSimati = ls.get("idSimati").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				idFail = ls.get("idFail").toString();
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());

			// list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

			String idTempatBicara = getParam("socTempatBicara");
			String idPegawai = getParam("socPegawai");

			int idBicara = 0;
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String negeri = "";
			String jenispejabat = "";

			jenispejabat = getParam("jenisPejabat");

			if (jenispejabat.equals("1")) {
				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
			} else if (jenispejabat.equals("2")) {
				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
			} else {
				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
			}

			if (idTempatBicara != "") {

				if (jenispejabat.equals("1")) {
					alamatTempatBicara = modelNotis
							.getAlamatTempatBicara(idTempatBicara);
				} else if (jenispejabat.equals("2")) {
					alamatTempatBicara = modelNotis
							.getAlamatPejabatTanah(idTempatBicara);
				} else {
					alamatTempatBicara = modelNotis
							.getAlamatTempatBicara(idTempatBicara);
				}

				if (alamatTempatBicara.size() != 0) {

					Hashtable AB = (Hashtable) alamatTempatBicara.get(0);

					alamat1 = AB.get("alamat1").toString();
					alamat2 = AB.get("alamat2").toString();
					alamat3 = AB.get("alamat3").toString();
					poskod = AB.get("poskod").toString();
					negeri = AB.get("id_negeri").toString();
				}
			}

			String idNegeri = "";

			if (list.size() != 0) {
				Hashtable semak = (Hashtable) list.get(0);
				idNegeri = semak.get("pmidnegeri").toString();
			}

			if (idNegeri != "") {
				context.put("xidnegerix", idNegeri);
			}

			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());

			// get & post all field content
			context.put("poskod", poskod);
			context.put("alamat1", alamat1);
			context.put("alamat2", alamat2);
			context.put("alamat3", alamat3);
			context.put("txdTarikhBicara", getParam("txdTarikhBicara"));
			context.put("txtMasaBicara", getParam("txtMasaBicara"));
			context.put("txdTarikhNotis", getParam("txdTarikhNotis"));

			// -- 09122009
			context.put("jenisWaktu", getParam("socJenisWaktu"));

			// id
			context.put("id_permohonan", id);
			context.put("idnegeri", negeri);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("id_fail", idFail);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);

			// Dropdown negeri by tmpat bicara
			if (negeri != "") {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Utils.parseLong(negeri),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			/* tukar pegawai by negeri 09102009 */
			if (idNegeri != "") {
				if (idPegawai != "") {
					context.put("selectPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"socPegawai", Utils.parseLong(idPegawai),
									"style=width:305"));
				} else {
					context.put("selectPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"socPegawai", null, "style=width:305"));
				}
			} else {
				if (idPegawai != "") {
					context.put("selectPegawai", HTML.SelectPegawaiPengendali(
							"socPegawai", Utils.parseLong(idPegawai),
							"style=width:305"));
				} else {
					context.put("selectPegawai", HTML.SelectPegawaiPengendali(
							"socPegawai", null, "style=width:305"));
				}
			}

			if (idPejabatJKPTG != "") {

				if (idTempatBicara != "") {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"socTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"socTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					}
				} else {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"socTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"socTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					}
				}

			} else {

				if (idTempatBicara != "") {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicara(
														"socTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanah(
														"socTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					}
				} else {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicara(
														"socTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanah(
														"socTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicara();\" "));
					}
				}
			}

			// form validation
			context.put("viewdata", "no");
			context.put("edit", "no");

			// screen
			vm = screen1;

		}// close doChangeidTempatBicara

		//TODO
		else if ("semakWithData".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}
			context.put("selectedTab", selectedTab);

			id = getParam("id_permohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idStatus = "";
			String idSimati = "";
			String idPejabatJKPTG = "";
			String id_negeri = "";
			String idFail = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idSimati = ls.get("idSimati").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				id_negeri = ls.get("pmidnegeri").toString();
				idFail = ls.get("idFail").toString();
			}

			if (id_negeri != "") {
				context.put("xidnegerix", id_negeri);
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			Hashtable kp = new Hashtable();
			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// --data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idperbicaraan = "";
			String idpsk = "";
			String idNeg = "";
			String currentBil = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String tempatBicara = "";

			Hashtable idn = new Hashtable();

			if (dataNotis.size() != 0) {
				idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
				idpsk = idn.get("id_unitpsk").toString();
				idNeg = idn.get("id_negeribicara").toString();
				currentBil = idn.get("bil_bicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
			}

			// get selected ob
			modelNotis.setSelectedOB(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB = modelNotis.getSelectedOB();

			// get selected ob untuk 17
			modelNotis.setSelectedOB17(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB17 = modelNotis.getSelectedOB17();

			if (selectedOB17.size() != 0) {
				context.put("selectedOB", selectedOB17);
				context.put("selectedOB_size", selectedOB17.size());
			} else {
				context.put("selectedOB", selectedOB);
				context.put("selectedOB_size", selectedOB.size());
			}

			if (currentBil != "") {
				int _currentBil = Integer.parseInt(currentBil);

				if (_currentBil > 1) {
					context.put("showSenarai", "yes");
				} else {
					context.put("showSenarai", "no");
				}
			}
			
			//untuk check samada nilaian PPBPP telah dicetak atau belum - start
			Vector list2 = new Vector();
			//list2.clear();
			String id2 = getParam("id_permohonan");
			System.out.println("id2 = "+id2);
			System.out.println("usid = "+usid);
			logic_A.setDataPPSPPSek17(id2, usid);
			list2 = logic_A.getDataPPSPP();
			this.context.put("list2", list2);
			System.out.println("id_jkptg1********* = "+idPejabatJKPTG);
			
			//untuk check samada nilaian PPBPP telah dicetak atau belum - end
			
			//aishahlatip
			//get count ob
			modelNotis.setCountSemuaOB(id_permohonansimati, idSimati, "17", idperbicaraan);
			listCountOB = modelNotis.getCountSemuaOB();
			String countOB = "";
			if (listCountOB.size() != 0) {
				Hashtable ls = (Hashtable) listCountOB.get(0);
				countOB = ls.get("countAllOB").toString() ;
			}
			
			//aishahlatip
			//get count penerima notis
			modelNotis.setCountPenerimaNotis(idperbicaraan, id_permohonansimati);
			listCountPenerimaNotis = modelNotis.getCountPenerimaNotis();
			String countPenerimaNotis = "";
			if (listCountPenerimaNotis.size() != 0) {
				Hashtable ls = (Hashtable) listCountPenerimaNotis.get(0);
				countPenerimaNotis = ls.get("countPenerimaNotis").toString() ;
			}
			
			String isAllSendNotis = "";
			if(!countOB.equals("0")){
				if(countOB.equals(countPenerimaNotis)){
					isAllSendNotis = "yes";
				}else{
					isAllSendNotis = "no";
				}
			}
			context.put("isAllSendNotis", isAllSendNotis);
			System.out.println("isAllSendNotis==="+isAllSendNotis);
			System.out.println("countOB==="+countOB);
			System.out.println("countPenerimaNotis==="+countPenerimaNotis);
			System.out.println("id_permohonansimati==="+id_permohonansimati);
			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "17", idperbicaraan);
			listOB = modelNotis.getListSemuaOB();
			
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			modelNotis.setListPemiutang(id_permohonansimati, idSimati, "17", idperbicaraan);
			listPemiutang = modelNotis.getListPemiutang();

			// list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

			//code wp
			String idOBDTL = getParam("id_semaknotisobdtl");
			// get data semak penerima notis
			modelNotis.setDataOBNotis(idOBDTL, getParam("id_permohonansimati_atheader"));
			dataOBNotis = modelNotis.getDataOBNotis();
			
			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());
			
			// get senarai serahan notis
			modelNotis.setListSerahanNotis(idperbicaraan, id_permohonansimati);
			listSerahanNotis = modelNotis.getListSerahanNotis();
			context.put("listSerahanNotis", listSerahanNotis);			

			// get list cbsemak ob
			modelNotis.setListOBsemak(id_permohonansimatiINT, idperbicaraan,
					idSimati);
			listOBsemak = modelNotis.getListOBsemak();
			context.put("listOBsemak_size", listOBsemak.size());
			context.put("listOBsemak", listOBsemak);

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("id_fail", idFail);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang", listPemiutang);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang_size", listPemiutang.size());

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "no");
			context.put("dochange", "no");

			if (idjenispejabat.equals("22")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled "));
					} else {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled "));
					}
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"showBicara",
								"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52'  maxlength='60' value='"
										+ tempatBicara
										+ "' class='disabled' readonly>");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			/* tukar pegawai by negeri 09102009 */

			if (idNeg != "") {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						Utils.parseLong(idNeg),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			if (id_negeri != "") {

				if (idpsk != "") {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", Utils.parseLong(idpsk),
									"class=disabled disabled style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", null,
									"class=disabled disabled style=width:305"));
				}

			} else {

				if (idpsk != "") {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", Utils.parseLong(idpsk),
							"class=disabled disabled style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", null,
							"class=disabled disabled style=width:305"));
				}
			}

			// screen
			vm = screen1;

		}// close semakwithdata
		//TODO

		else if ("doChangeidTempatBicaraUPDATE".equals(submit)) {

			String idkp = "";
			String idb = "";

			id = getParam("id_permohonan");
			idkp = getParam("id_keputusanpermohonan");
			idb = getParam("id_perbicaraan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";
			String idPejabatJKPTG = "";
			String id_negeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				id_negeri = ls.get("pmidnegeri").toString();
				idSimati = ls.get("idSimati").toString();
			}

			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
			}

			String idTempatBicara = "";
			String idPegawai = "";

			idTempatBicara = getParam("editTempatBicara");
			idPegawai = getParam("editPegawai");

			int idBicara = 0;

			String alamatb1 = "";
			String alamatb2 = "";
			String alamatb3 = "";
			String poskodb = "";

			String idNegeri = "";
			String jenispejabat = "";

			jenispejabat = getParam("editjenisPejabat");

			if (jenispejabat.equals("1")) {
				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
			} else if (jenispejabat.equals("2")) {
				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
			} else {
				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
			}

			if (idTempatBicara != "") {

				if (jenispejabat.equals("1")) {
					alamatTempatBicara = modelNotis
							.getAlamatTempatBicara(idTempatBicara);
				} else if (jenispejabat.equals("2")) {
					alamatTempatBicara = modelNotis
							.getAlamatPejabatTanah(idTempatBicara);
				} else {
					alamatTempatBicara = modelNotis
							.getAlamatTempatBicara(idTempatBicara);
				}

				if (alamatTempatBicara.size() != 0) {

					Hashtable AB2 = (Hashtable) alamatTempatBicara.get(0);

					alamatb1 = AB2.get("alamat1").toString();
					alamatb2 = AB2.get("alamat2").toString();
					alamatb3 = AB2.get("alamat3").toString();
					poskodb = AB2.get("poskod").toString();
					idNegeri = AB2.get("id_negeri").toString();
				}

			}

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);

			// get & post all field content
			context.put("poskodb", poskodb);
			context.put("alamatb1", alamatb1);
			context.put("alamatb2", alamatb2);
			context.put("alamatb3", alamatb3);
			context.put("tarikhBicara", getParam("editTarikhBicara"));
			context.put("masaBicara", getParam("editMasaBicara"));
			context.put("tarikhNotis", getParam("editTarikhNotis"));

			// -- 09122009
			context.put("jenisWaktu", getParam("socJenisWaktu"));

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_perbicaraan", idb);
			context.put("idnegeri", idNegeri);
			context.put("id_status", idStatus);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// Dropdown
			if (idNegeri != "") {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						Utils.parseLong(idNegeri),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			if (idPejabatJKPTG != "") {

				if (idTempatBicara != "") {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				} else {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				}

			} else {

				if (idTempatBicara != "") {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicara(
														"editTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanah(
														"editTempatBicara",
														Utils
																.parseLong(idTempatBicara),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				} else {
					if (jenispejabat.equals("1")) {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicara(
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanah(
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				}
			}

			/* tukar pegawai by negeri 09102009 */

			if (id_negeri != "") {

				if (idPegawai != "") {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", Utils.parseLong(idPegawai),
									"style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", null, "style=width:305"));
				}
				context.put("xidnegerix", id_negeri);
			} else {

				if (idPegawai != "") {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", Utils.parseLong(idPegawai),
							"style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", null, "style=width:305"));
				}
				context.put("xidnegerix", "");
			}

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "yes");
			context.put("dochange", "yes");

			// screen
			vm = screen1;
		}

		else if ("kemaskini".equals(submit)) {

			String idkp = "";
			String idb = "";

			id = getParam("id_permohonan");
			idkp = getParam("id_keputusanpermohonan");
			idb = getParam("id_perbicaraan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idStatus = "";
			String idPejabatJKPTG = "";
			String id_negeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				id_negeri = ls.get("pmidnegeri").toString();
			}

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idpsk = "";
			String idnegeri = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String tempatBicara = "";
			String jenisW = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idpsk = idn.get("id_unitpsk").toString();
				idnegeri = idn.get("id_negeribicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
				jenisW = idn.get("jenis_masa_bicara").toString();
			}

			// -- 09122009
			context.put("jenisWaktu", jenisW);

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_perbicaraan", idb);
			context.put("idnegeri", idnegeri);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "yes");
			context.put("dochange", "no");

			// negeri validation
			String negeriReadonly = "class=disabled disabled style=width:305";

			if (idjenispejabat.equals("22")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				} else {
					if (idpejabat != "") {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicara(
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicara(
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				} else {
					if (idpejabat != "") {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanah(
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectPejabatTanah(
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"SELECTBicara",
								"<input type='text' name='editTempatBicara'  maxlength='60' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52' value='"
										+ tempatBicara + "' >");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

				// address validation (editable)
				context.put("addressReadonly", "");
				context.put("addressReadonlyClass", "");

				// negeri validation
				negeriReadonly = "style=width:305";

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				} else {
					if (idpejabat != "") {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicara(
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					} else {
						context
								.put(
										"SELECTBicara",
										HTML
												.SelectTempatBicara(
														"editTempatBicara",
														null, null,
														"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			/* tukar pegawai by negeri 09102009 */

			if (idnegeri != "") {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						Utils.parseLong(idnegeri), negeriReadonly));
			} else {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						null, negeriReadonly));
			}

			if (id_negeri != "") {
				if (idpsk != "") {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", Utils.parseLong(idpsk),
									"style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", null, "style=width:305"));
				}

			} else {
				if (idpsk != "") {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", Utils.parseLong(idpsk),
							"style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", null, "style=width:305"));
				}
			}
			
			int flag_cuti = 0;
			//aishahlatip15052017 : untuk dapatkan samaada negeri cuti am pada jumaat sabtu atau sabtu ahad
			if (idnegeri != "") {
				flag_cuti = modelNotis.getFlagCuti(idnegeri);
			}
			
			context.put("flag_cuti", flag_cuti);
			
			

			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idb,
					getParam("id_permohonansimati_atheader"));
			listPenerimaNotis = modelNotis.getListPenerimaNotis();
			context.put("listPenerimaNotis", listPenerimaNotis);
			
			boolean statusHantarPNB = false;
			// validate status hantar PNB
			statusHantarPNB = modelNotis.getPNBValidation(idb);//tutup jap
			if (statusHantarPNB) {
				context.put("statusPNB", "yes");
			} else {
				context.put("statusPNB", "no");
			}

			// screen
			vm = screen1;

		}// close kemaskini

		else if ("onchangeJenisPejabatUpdate".equals(submit)) {

			String idkp = "";
			String idb = "";

			id = getParam("id_permohonan");
			idkp = getParam("id_keputusanpermohonan");
			idb = getParam("id_perbicaraan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idStatus = "";
			String idPejabatJKPTG = "";
			String idNegeri = "";
			String idSimati = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				idNegeri = ls.get("pmidnegeri").toString();
				idSimati = ls.get("idSimati").toString();
			}

			// -- 17122009
			// get size ob != (<18 n tidakwaras)
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("param", listOBatas18.size());

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idpsk = "";
			String idnegeri = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idpsk = idn.get("id_unitpsk").toString();
				idnegeri = idn.get("id_negeribicara").toString();
			}

			// negeri validation
			String negeriReadonly = "class=disabled disabled style=width:305";

			String jenispejabat = "";

			jenispejabat = getParam("editjenisPejabat");

			if (jenispejabat.equals("1")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					context
							.put(
									"SELECTBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"editTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
				} else {
					context
							.put(
									"SELECTBicara",
									HTML
											.SelectTempatBicara(
													"editTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (jenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					context
							.put(
									"SELECTBicara",
									HTML
											.SelectPejabatTanahByJKPTG(
													idPejabatJKPTG,
													"editTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
				} else {
					context
							.put(
									"SELECTBicara",
									HTML
											.SelectPejabatTanah(
													"editTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (jenispejabat.equals("0")) {

				// textfield pejabat tanah
				context
						.put(
								"SELECTBicara",
								"<input type='text' name='editTempatBicara' value='' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52' maxlength='60'> ");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

				// address validation (editable)
				context.put("addressReadonly", "");
				context.put("addressReadonlyClass", "");

				// negeri validation
				negeriReadonly = "style=width:305";

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					context
							.put(
									"SELECTBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"editTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
				} else {
					context
							.put(
									"SELECTBicara",
									HTML
											.SelectTempatBicara(
													"editTempatBicara", null,
													null,
													"style=width:400 onChange=\"doChangeidTempatBicaraUPDATE();\" "));
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			// set empty data
			context.put("poskodb", "");
			context.put("alamatb1", "");
			context.put("alamatb2", "");
			context.put("alamatb3", "");

			context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri", null,
					negeriReadonly));

			String idPegawai = getParam("editPegawai");

			context.put("tarikhBicara", getParam("editTarikhBicara"));
			context.put("masaBicara", getParam("editMasaBicara"));
			context.put("tarikhNotis", getParam("editTarikhNotis"));

			// -- 09122009
			context.put("jenisWaktu", getParam("socJenisWaktu"));

			if (idNegeri != "") {

				if (idpsk != "") {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"editPegawai", Utils.parseLong(idPegawai),
									"style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"editPegawai", null, "style=width:305"));
				}

			} else {

				if (idpsk != "") {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", Utils.parseLong(idPegawai),
							"style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", null, "style=width:305"));
				}
			}

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_perbicaraan", idb);
			context.put("idnegeri", idnegeri);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "yes");
			context.put("dochange", "yes");

			// screen
			vm = screen1;

		}// close onchangeJenisPejabatUpdate

		else if ("updateNotis".equals(submit)) {

			String idb = "0";

			idb = getParam("id_perbicaraan");
			id = getParam("id_permohonan");

			if (doPost.equals("true")) {

				updateNotis(session);
			}

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idStatus = "";
			String idPejabatJKPTG = "";
			String id_negeri = "";
			String idSimati = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				id_negeri = ls.get("pmidnegeri").toString();
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String id_unitpsk = "";
			String idneg = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String currentBil = "";
			String tempatBicara = "";
			String idperbicaraan = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
				id_unitpsk = idn.get("id_unitpsk").toString();
				idneg = idn.get("id_negeribicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
				currentBil = idn.get("bil_bicara").toString();
			}

			if (currentBil != "") {
				int _currentBil = Integer.parseInt(currentBil);

				if (_currentBil > 1) {
					context.put("showSenarai", "yes");
				} else {
					context.put("showSenarai", "no");
				}
			}
			
			//aishahlatip
			//get count ob
			modelNotis.setCountSemuaOB(id_permohonansimati, idSimati, "8", idperbicaraan);
			listCountOB = modelNotis.getCountSemuaOB();
			String countOB = "";
			if (listCountOB.size() != 0) {
				Hashtable ls = (Hashtable) listCountOB.get(0);
				countOB = ls.get("countAllOB").toString() ;
			}
			
			//aishahlatip
			//get count penerima notis
			modelNotis.setCountPenerimaNotis(idperbicaraan, id_permohonansimati);
			listCountPenerimaNotis = modelNotis.getCountPenerimaNotis();
			String countPenerimaNotis = "";
			if (listCountPenerimaNotis.size() != 0) {
				Hashtable ls = (Hashtable) listCountPenerimaNotis.get(0);
				countPenerimaNotis = ls.get("countPenerimaNotis").toString() ;
			}
			
			String isAllSendNotis = "";
			if(!countOB.equals("0")){
				if(countOB.equals(countPenerimaNotis)){
					isAllSendNotis = "yes";
				}else{
					isAllSendNotis = "no";
				}
			}
			context.put("isAllSendNotis", isAllSendNotis);
			
			// get senarai serahan notis
			modelNotis.setListSerahanNotis(idperbicaraan, id_permohonansimati);
			listSerahanNotis = modelNotis.getListSerahanNotis();
			context.put("listSerahanNotis", listSerahanNotis);			

			// get list cbsemak ob
			modelNotis.setListOBsemak(id_permohonansimatiINT, idperbicaraan,
					idSimati);
			listOBsemak = modelNotis.getListOBsemak();
			context.put("listOBsemak_size", listOBsemak.size());
			context.put("listOBsemak", listOBsemak);
			
			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "17", idperbicaraan);
			listOB = modelNotis.getListSemuaOB();
			
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			modelNotis.setListPemiutang(id_permohonansimati, idSimati, "17", idperbicaraan);
			listPemiutang = modelNotis.getListPemiutang();

			// id
			context.put("id_permohonan", id);
			context.put("id_perbicaraan", idb);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_status", idStatus);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);
			
			context.put("listOB", listOB);
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang", listPemiutang);
			
			// set size
			context.put("listOB_size", listOB.size());
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang_size", listPemiutang.size());

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "no");
			context.put("dochange", "no");

			// dropdown
			if (idneg != "") {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						Utils.parseLong(idneg),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			if (idjenispejabat.equals("22")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled "));
					} else {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled "));
					}
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"showBicara",
								"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52'  maxlength='60' value='"
										+ tempatBicara
										+ "' class='disabled' readonly>");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			if (id_negeri != "") {

				if (id_unitpsk != "") {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", Utils.parseLong(id_unitpsk),
									"class=disabled disabled style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(id_negeri,
									"editPegawai", null,
									"class=disabled disabled style=width:305"));
				}

			} else {

				if (id_unitpsk != "") {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", Utils.parseLong(id_unitpsk),
							"class=disabled disabled style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", null,
							"class=disabled disabled style=width:305"));
				}
			}

			// screen
			vm = screen1;

		}// close updateNotis

		else if ("simpanNotisTambah".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}

			context.put("selectedTab", selectedTab);

			myLogger.info("simpannotistambah : " + doPost);

			if (doPost.equals("true")) {

				simpanNotisTambah(session);
			}

			id = getParam("id_permohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idPejabatJKPTG = "";
			String idStatus = "";
			String idNegeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idSimati = ls.get("idSimati").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				idNegeri = ls.get("pmidnegeri").toString();
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idneg = "";
			String idpsk = "";
			String currentBil = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String tempatBicara = "";
			String idperbicaraan = "";
			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idneg = idn.get("id_negeribicara").toString();
				idpsk = idn.get("id_unitpsk").toString();
				currentBil = idn.get("bil_bicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
				idperbicaraan = idn.get("id_perbicaraan").toString();
			}

			// dropdown

			if (idneg != "") {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						Utils.parseLong(idneg),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			if (idjenispejabat.equals("22")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled "));
					} else {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled "));
					}
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"showBicara",
								"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()'  maxlength='60' size='52' value='"
										+ tempatBicara
										+ "' class='disabled' readonly>");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			/* tukar pegawai by negeri 09102009 */
			if (idNegeri != "") {
				if (idpsk != "") {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"editPegawai", Utils.parseLong(idpsk),
									"class=disabled disabled style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML
							.SelectPegawaiPengendaliByNegeri(idNegeri,
									"editPegawai", null,
									"class=disabled disabled style=width:305"));
				}
			} else {
				if (idpsk != "") {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", Utils.parseLong(idpsk),
							"class=disabled disabled style=width:305"));
				} else {
					context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
							"editPegawai", null,
							"class=disabled disabled style=width:305"));
				}
			}

			if (currentBil != "") {
				int _currentBil = Integer.parseInt(currentBil);

				if (_currentBil > 1) {
					context.put("showSenarai", "yes");
				} else {
					context.put("showSenarai", "no");
				}
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17", idperbicaraan);
			listOB = modelNotis.getListSemuaOB();
			
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			modelNotis.setListPemiutang(id_permohonansimatiINT, idSimati, "17", idperbicaraan);
			listPemiutang = modelNotis.getListPemiutang();

			// get list cbsemak ob
			modelNotis.setListOBsemak(id_permohonansimatiINT, idperbicaraan,
					idSimati);
			listOBsemak = modelNotis.getListOBsemak();
			context.put("listOBsemak_size", listOBsemak.size());
			context.put("listOBsemak", listOBsemak);

			// list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();
			
			

			
			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idperbicaraan,id_permohonansimati);
			listPenerimaNotis = modelNotis.getListPenerimaNotis();
			context.put("listPenerimaNotis", listPenerimaNotis);
		
			modelNotis.setListSerahanNotis(idperbicaraan, id_permohonansimati);
			listSerahanNotis = modelNotis.getListSerahanNotis();
			context.put("listSerahanNotis", listSerahanNotis);
	
			
			//aishahlatip
			//get count ob
			modelNotis.setCountSemuaOB(id_permohonansimati, idSimati, "8", idperbicaraan);
			listCountOB = modelNotis.getCountSemuaOB();
			String countOB = "";
			if (listCountOB.size() != 0) {
				Hashtable ls = (Hashtable) listCountOB.get(0);
				countOB = ls.get("countAllOB").toString() ;
			}
			
			//aishahlatip
			//get count penerima notis
			modelNotis.setCountPenerimaNotis(idperbicaraan, id_permohonansimati);
			listCountPenerimaNotis = modelNotis.getCountPenerimaNotis();
			String countPenerimaNotis = "";
			if (listCountPenerimaNotis.size() != 0) {
				Hashtable ls = (Hashtable) listCountPenerimaNotis.get(0);
				countPenerimaNotis = ls.get("countPenerimaNotis").toString() ;
			}
			
			String isAllSendNotis = "";
			if(!countOB.equals("0")){
				if(countOB.equals(countPenerimaNotis)){
					isAllSendNotis = "yes";
				}else{
					isAllSendNotis = "no";
				}
			}
			context.put("isAllSendNotis", isAllSendNotis);


			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang", listPemiutang);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang_size", listPemiutang.size());

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "no");
			context.put("dochange", "no");

			// screen
			vm = screen1;

		}// close simpanNotisTambah

		else if ("simpan".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "0";
			}

			context.put("selectedTab", selectedTab);

			if (doPost.equals("true")) {
				//SIMPAN NOTIS BARU
				simpanNotis(session);
				//simpanNotis_temp(session);
			}

			id = getParam("id_permohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idStatus = "";
			String idSimati = "";
			String idPejabatJKPTG = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idStatus = ls.get("id_Status").toString();
				idSimati = ls.get("idSimati").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idpsk = "";
			String idneg = "";
			String currentBil = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String tempatBicara = "";
			String idperbicaraan = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idpsk = idn.get("id_unitpsk").toString();
				idneg = idn.get("id_negeribicara").toString();
				currentBil = idn.get("bil_bicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
				idperbicaraan = idn.get("id_perbicaraan").toString();
			}

			if (currentBil != "") {
				int _currentBil = Integer.parseInt(currentBil);

				if (_currentBil > 1) {
					context.put("showSenarai", "yes");
				} else {
					context.put("showSenarai", "no");
				}
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "17", idperbicaraan);
			listOB = modelNotis.getListSemuaOB();
			
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			modelNotis.setListPemiutang(id_permohonansimati, idSimati, "17", idperbicaraan);
			listPemiutang = modelNotis.getListPemiutang();
			
			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idperbicaraan,id_permohonansimati);
			listPenerimaNotis = modelNotis.getListPenerimaNotis();
			context.put("listPenerimaNotis", listPenerimaNotis);
		
			modelNotis.setListSerahanNotis(idperbicaraan, id_permohonansimati);
			listSerahanNotis = modelNotis.getListSerahanNotis();
			context.put("listSerahanNotis", listSerahanNotis);

			
			//aishahlatip
			//get count ob
			modelNotis.setCountSemuaOB(id_permohonansimati, idSimati, "8", idperbicaraan);
			listCountOB = modelNotis.getCountSemuaOB();
			String countOB = "";
			if (listCountOB.size() != 0) {
				Hashtable ls = (Hashtable) listCountOB.get(0);
				countOB = ls.get("countAllOB").toString() ;
			}
			
			//aishahlatip
			//get count penerima notis
			modelNotis.setCountPenerimaNotis(idperbicaraan, id_permohonansimati);
			listCountPenerimaNotis = modelNotis.getCountPenerimaNotis();
			String countPenerimaNotis = "";
			if (listCountPenerimaNotis.size() != 0) {
				Hashtable ls = (Hashtable) listCountPenerimaNotis.get(0);
				countPenerimaNotis = ls.get("countPenerimaNotis").toString() ;
			}
			
			String isAllSendNotis = "";
			if(!countOB.equals("0")){
				if(countOB.equals(countPenerimaNotis)){
					isAllSendNotis = "yes";
				}else{
					isAllSendNotis = "no";
				}
			}
			context.put("isAllSendNotis", isAllSendNotis);
			
			
			// get list cbsemak ob
			modelNotis.setListOBsemak(id_permohonansimatiINT, idperbicaraan,
					idSimati);
			listOBsemak = modelNotis.getListOBsemak();
			context.put("listOBsemak_size", listOBsemak.size());
			context.put("listOBsemak", listOBsemak);

			// list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("idpejabatjkptg", idPejabatJKPTG);

			// list & data
			context.put("dataNotis", dataNotis);
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenjaga", listMaklumatPenjaga);
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang", listPemiutang);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenjaga_size", listMaklumatPenjaga.size());
			/** ADD BY PEJE - SENARAI PEMIUTANG **/
			context.put("listPemiutang_size", listPemiutang.size());

			// form validation
			context.put("viewdata", "yes");
			context.put("edit", "no");
			context.put("dochange", "no");

			// dropdown
			if (idneg != "") {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						Utils.parseLong(idneg),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
						null, "class=disabled disabled style=width:305"));
			}

			if (idpsk != "") {
				context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
						"editPegawai", Utils.parseLong(idpsk),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
						"editPegawai", null,
						"class=disabled disabled style=width:305"));
			}

			if (idjenispejabat.equals("22")) {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						/*context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));*/
						context.put("showBicara",PPKUtilHTML.SelectTempatBicaraByPejabatJKPTG(
								usid,idPejabatJKPTG,"editTempatBicara",Utils.parseLong(idpejabat), null,"style=width:400 class=disabled disabled "));
					} else {
						/*context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));*/
						context.put("showBicara",PPKUtilHTML.SelectTempatBicaraByPejabatJKPTG(
								usid,idPejabatJKPTG,"editTempatBicara",null, null,"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"showBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled "));
					} else {
						context.put("showBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled "));
					}
				}

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"showBicara",
								"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()'  maxlength='60' size='52' value='"
										+ tempatBicara
										+ "' class='disabled' readonly>");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						/*context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));*/
						context.put("showBicara",PPKUtilHTML.SelectTempatBicaraByPejabatJKPTG(
								usid,idPejabatJKPTG,"editTempatBicara",Utils.parseLong(idpejabat), null,"style=width:400 class=disabled disabled "));
					} else {
						/*context
								.put(
										"showBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));*/
						context.put("showBicara",PPKUtilHTML.SelectTempatBicaraByPejabatJKPTG(
								usid,idPejabatJKPTG,"editTempatBicara",null, null,"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("showBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}

			// screen
			vm = screen1;

		}// close simpan

		else if ("maklumatPenjagaAdd".equals(submit)) {

			String namaOB = getParam("nama_ob");
			String idStatus = getParam("id_status");

			String id_status = "";
			String idob = "";

			id = getParam("id_permohonan");
			idob = getParam("id_ob");
			context.put("idobminor", idob);


			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String id_fail = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				id_fail = ls.get("idFail").toString();
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get selected dropdown
			modelNotis.setSelectedDropdown(id_permohonansimatiINT, idob,
					idSimati, "17");
			selectedDropdown = modelNotis.getSelectedDropdown();

			// get list penjaga
			modelNotis.setListPenjaga(idob,
					getParam("id_permohonansimati_atheader"));
			listPenjaga = modelNotis.getListPenjaga();

			// id
			context.put("id_status", id_status);
			context.put("id_permohonan", id);
			context.put("id_ob", idob);
			context.put("id_fail", id_fail);

			// data & list
			context.put("nama_ob", namaOB);
			context.put("listPenjaga", listPenjaga);
			context.put("listOB", listOB);
			context.put("selectedDropdown", selectedDropdown);
			context.put("listSemak", list);

			// set size
			context.put("listPenjaga_size", listPenjaga.size());
			context.put("selectedDropdown_size", selectedDropdown.size());

			// form validation
			context.put("editPenjaga", "no");
			context.put("viewLantikPenjaga", "no");
			context.put("editLantikPenjaga", "no");
			context.put("onchangeNegeri", "no");

			// dropdown
			context.put("selectNegeri", HTML.SelectNegeri("socNegeri", null,
					"class=disabled disabled style=width:280"));
			context.put("selectBandar", HTML.SelectBandar("txtbandar", null,
					"class=disabled disabled style=width:280"));

			// default selected radio
			context.put("sor1", "checked");
			context.put("sor2", "");
			context.put("dropdownDisable", "");
			context.put("onchange", "no");
			context.put("getdata", "no");
			context.put("disabilityMode", "yes");

			String submit2 = getParam("command2");
			myLogger.info("[submit]:2 :: " + submit2);

			if ("doChangeSorPenjagaOB".equals(submit2)) {

				String idNegeri = "";
				String idBandar = "";
				String POB = "0";

				String socPenjagaOB = getParam("socPenjagaOB");
				idStatus = getParam("id_status");

				// if(socPenjagaOB!="")
				// {
				// POB = socPenjagaOB;
				// }

				if (socPenjagaOB.equals("")) {
					context.put("default", "yes");
					context.put("value", "");
				} else {
					dataOBbyID = modelNotis.getDataOB(socPenjagaOB,
							getParam("id_permohonansimati_atheader"));
					Hashtable nob = (Hashtable) dataOBbyID.get(0);
					String _nob = nob.get("nama_ob").toString();
					idNegeri = nob.get("id_negeri").toString();
					idBandar = nob.get("id_bandar").toString();

					context.put("dataOBbyID", dataOBbyID);
					context.put("getdata", "yes");

					context.put("default", "no");
					context.put("value", socPenjagaOB);
					context.put("valueName", _nob);
					context.put("idnegeri", idNegeri);
				}

				// data
				context.put("socPenjagaOB", socPenjagaOB);

				// id
				context.put("id_status", idStatus);

				// validation
				context.put("disabilityMode", "yes");
				context.put("onchange", "yes");

				// selected radio
				context.put("sor1", "checked");
				context.put("sor2", "");
				context.put("dropdownDisable", "");

				// dropdown
				if (idNegeri != "") {
					context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
							Utils.parseLong(idNegeri),
							"class=disabled disabled style=width:280"));
				} else {
					context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
							null, "class=disabled disabled style=width:280"));
				}

				if (idBandar != "") {
					context.put("selectBandar", HTML.SelectBandar("txtbandar",
							Utils.parseLong(idBandar),
							"class=disabled disabled style=width:280"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("txtbandar",
							null, "class=disabled disabled style=width:280"));
				}

			} else if ("doChangeSorPenjagaBaru".equals(submit2)) {

				context.put("catatanPenjaga", "");

				idStatus = getParam("id_status");

				// validation
				context.put("disabilityMode", "no");
				context.put("onchange", "no");
				context.put("getdata", "no");

				// id
				context.put("id_status", idStatus);

				// selected radio
				context.put("sor1", "");
				context.put("sor2", "checked");
				context.put("dropdownDisable", "disabled");

				// dropdown
				context
						.put(
								"selectNegeri",
								HTML
										.SelectNegeri("socNegeri", null, null,
												"style=width:280 onchange=doChangeGetBandarByNegeri()"));
				context.put("selectBandar", HTML.SelectBandar("txtbandar",
						null, "style=width:280"));

				String submit3 = getParam("command3");
				myLogger.info("[submit]:3 :: " + submit3);

				if ("doChangeGetBandarByNegeri".equals(submit3)) {

					context.put("onchangeNegeri", "yes");

					String IDNegeri = "";

					idStatus = getParam("id_status");
					IDNegeri = getParam("socNegeri");

					if (IDNegeri != "") {
						context
								.put(
										"selectNegeri",
										HTML
												.SelectNegeri(
														"socNegeri",
														Utils
																.parseLong(IDNegeri),
														"style=width:280 onChange=\"doChangeGetBandarByNegeri();\" "));
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								IDNegeri, "txtbandar", null, null,
								"style=width:280"));
					} else {
						context
								.put(
										"selectNegeri",
										HTML
												.SelectNegeri("socNegeri",
														null,
														"style=width:280 onChange=\"doChangeGetBandarByNegeri();\" "));
						context.put("selectBandar", HTML.SelectBandar(
								"txtbandar", null, "style=width:280"));
					}

					// id
					context.put("id_status", idStatus);

					String a, b, c, txtNoKPBaru, txtNoKPLama, socJenisKp, txtJenisKP, txtNamaPenjaga, socJantina, socAgama, socWarganegara, txtUmur, txtalamat1, txtalamat2, txtalamat3, txtbandar, txtposkod, socNegeri, txtcatatan = "";

					// getdata back
					a = getParam("txtNoKPBaru1");
					b = getParam("txtNoKPBaru2");
					c = getParam("txtNoKPBaru3");

					txtNoKPBaru = a + b + c;

					txtNoKPLama = getParam("txtNoKPLama");
					socJenisKp = getParam("socJenisKp");
					txtJenisKP = getParam("txtJenisKP");
					txtNamaPenjaga = getParam("txtNamaPenjaga");
					socJantina = getParam("socJantina");
					socAgama = getParam("socAgama");
					socWarganegara = getParam("socWarganegara");
					txtUmur = getParam("txtUmur");
					txtalamat1 = getParam("txtalamat1");
					txtalamat2 = getParam("txtalamat2");
					txtalamat3 = getParam("txtalamat3");
					txtposkod = getParam("txtposkod");
					txtcatatan = getParam("txtcatatan");

					context.put("nokp1", a);
					context.put("nokp2", b);
					context.put("nokp3", c);
					context.put("nokplama", txtNoKPLama);
					context.put("jenis_kp", socJenisKp);
					context.put("txtjeniskp", txtJenisKP);
					context.put("nama", txtNamaPenjaga);
					context.put("jantina", socJantina);
					context.put("agama", socAgama);
					context.put("warganegara", socWarganegara);
					context.put("umur", txtUmur);
					context.put("alamat1", txtalamat1);
					context.put("alamat2", txtalamat2);
					context.put("alamat3", txtalamat3);
					context.put("poskod", txtposkod);
					context.put("catatanPenjaga", txtcatatan);

				}// close doChangeGetBandarByNegeri

			}// close doChangeSorPenjagaBaru

			// screen
			vm = screen2;

		}// close maklumatPenjagaAdd

		else if ("hapusPenjaga".equals(submit)) {

			myLogger.info("------------------hapus penjaga");
			modelNotis.deletePenjaga(getParam("id_penjaga"), getParam("id_ob"));

			String namaOB = getParam("nama_ob");
			String idStatus = getParam("id_status");

			String idob = "";

			id = getParam("id_permohonan");
			idob = getParam("id_ob");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String id_fail = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				id_fail = ls.get("idFail").toString();
			}

			context.put("id_fail", id_fail);

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get list penjaga
			modelNotis.setListPenjaga(idob,
					getParam("id_permohonansimati_atheader"));
			listPenjaga = modelNotis.getListPenjaga();

			// get selected dropdown
			modelNotis.setSelectedDropdown(id_permohonansimatiINT, idob,
					idSimati, "17");
			selectedDropdown = modelNotis.getSelectedDropdown();

			// id
			context.put("id_status", idStatus);
			context.put("id_permohonan", id);
			context.put("id_ob", idob);

			// data & list
			context.put("nama_ob", namaOB);
			context.put("listPenjaga", listPenjaga);
			context.put("listOB", listOB);
			context.put("selectedDropdown", selectedDropdown);
			context.put("listSemak", list);

			// set size
			context.put("listPenjaga_size", listPenjaga.size());

			// form validation
			context.put("editPenjaga", "no");
			context.put("viewLantikPenjaga", "no");
			context.put("editLantikPenjaga", "no");
			context.put("onchangeNegeri", "no");

			// dropdown
			context.put("selectNegeri", HTML.SelectNegeri("socNegeri", null,
					"class=disabled disabled style=width:280"));
			context.put("selectBandar", HTML.SelectBandar("txtbandar", null,
					"class=disabled disabled style=width:280"));

			// default selected radio
			context.put("sor1", "checked");
			context.put("sor2", "");
			context.put("dropdownDisable", "");
			context.put("onchange", "no");
			context.put("getdata", "no");
			context.put("disabilityMode", "yes");

			vm = screen2;

		}

		else if ("simpanLantikPenjaga".equals(submit)) {

			if (doPost.equals("true")) {

				addPenjaga(session);
			}

			String namaOB = getParam("nama_ob");
			String idStatus = getParam("id_status");

			String idob = "";

			id = getParam("id_permohonan");
			idob = getParam("id_ob");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String id_fail = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				id_fail = ls.get("idFail").toString();
			}

			context.put("id_fail", id_fail);

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get list penjaga
			modelNotis.setListPenjaga(idob,
					getParam("id_permohonansimati_atheader"));
			listPenjaga = modelNotis.getListPenjaga();

			// get selected dropdown
			modelNotis.setSelectedDropdown(id_permohonansimatiINT, idob,
					idSimati, "17");
			selectedDropdown = modelNotis.getSelectedDropdown();

			// id
			context.put("id_status", idStatus);
			context.put("id_permohonan", id);
			context.put("id_ob", idob);

			// data & list
			context.put("nama_ob", namaOB);
			context.put("listPenjaga", listPenjaga);
			context.put("listOB", listOB);
			context.put("selectedDropdown", selectedDropdown);
			context.put("listSemak", list);

			// set size
			context.put("listPenjaga_size", listPenjaga.size());

			// form validation
			context.put("editPenjaga", "no");
			context.put("viewLantikPenjaga", "no");
			context.put("editLantikPenjaga", "no");
			context.put("onchangeNegeri", "no");

			// dropdown
			context.put("selectNegeri", HTML.SelectNegeri("socNegeri", null,
					"class=disabled disabled style=width:280"));
			context.put("selectBandar", HTML.SelectBandar("txtbandar", null,
					"class=disabled disabled style=width:280"));

			// default selected radio
			context.put("sor1", "checked");
			context.put("sor2", "");
			context.put("dropdownDisable", "");
			context.put("onchange", "no");
			context.put("getdata", "no");
			context.put("disabilityMode", "yes");

			vm = screen2;

		}// simpan Lantik Penjaga

		else if ("semakDataPenjaga".equals(submit)) {

			String namaOB = getParam("nama_ob");

			String id_status = "";
			String idob = "";
			String id_penjaga = "";

			id_status = getParam("id_status");
			id = getParam("id_permohonan");
			idob = getParam("id_ob");
			id_penjaga = getParam("id_penjaga");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get list penjaga
			modelNotis.setListPenjaga(idob,
					getParam("id_permohonansimati_atheader"));
			listPenjaga = modelNotis.getListPenjaga();

			// get data penjaga
			modelNotis.setDataPenjaga(id_penjaga);
			dataPenjaga = modelNotis.getDataPenjaga();

			String idBandar = "";
			String idNegeri = "0";
			String editable = "";

			if (dataPenjaga.size() != 0) {
				Hashtable DP = (Hashtable) dataPenjaga.get(0);
				idBandar = DP.get("id_bandar").toString();
				idNegeri = DP.get("id_negeri").toString();
				editable = DP.get("editable").toString();
			}

			if (editable.equals("yes")) {
				context.put("editable", "yes");
			} else if (editable.equals("no")) {
				context.put("editable", "no");
			} else {
				context.put("editable", "yes");
			}

			if (idNegeri == "0") {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						null, "class=disabled disabled style=width:280"));
				if (idBandar != "") {
					context.put("selectBandar", HTML.SelectBandar("txtbandar",
							Utils.parseLong(idBandar),
							"class=disabled disabled style=width:280"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("txtbandar",
							null, "class=disabled disabled style=width:280"));
				}
			} else {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Utils.parseLong(idNegeri),
						"class=disabled disabled style=width:280"));
				if (idBandar != "") {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							idNegeri, "txtbandar", Utils.parseLong(idBandar),
							"class=disabled disabled style=width:280"));
				} else {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							idNegeri, "txtbandar", null,
							"class=disabled disabled style=width:280"));
				}
			}

			// id
			context.put("id_status", id_status);
			context.put("id_permohonan", id);
			context.put("id_ob", idob);
			context.put("id_penjaga", id_penjaga);

			// data
			context.put("nama_ob", namaOB);
			context.put("dataPenjaga", dataPenjaga);
			context.put("listOB", listOB);
			context.put("listPenjaga", listPenjaga);
			context.put("listSemak", list);

			// set size
			context.put("listPenjaga_size", listPenjaga.size());

			// form validation
			context.put("editPenjaga", "yes");
			context.put("viewLantikPenjaga", "yes");
			context.put("editLantikPenjaga", "no");
			context.put("onchangeNegeri", "no");

			// command2
			String submit2 = getParam("command2");
			myLogger.info("[submit]:2 :: " + submit2);

			if ("kemaskiniDataPenjaga".equals(submit2)) {

				// form validation
				context.put("editPenjaga", "yes");
				context.put("viewLantikPenjaga", "yes");
				context.put("editLantikPenjaga", "yes");
				context.put("onchangeNegeri", "no");

				if (idNegeri == "0") {
					context
							.put(
									"selectNegeri",
									HTML
											.SelectNegeri("EDITsocNegeri",
													null, null,
													"style=width:280 onChange=\"doChangeGetBandarByNegeriUpdate();\""));
					if (idBandar != "") {
						context.put("selectBandar", HTML.SelectBandar(
								"EDITtxtbandar", Utils.parseLong(idBandar),
								"style=width:280"));
					} else {
						context.put("selectBandar", HTML.SelectBandar(
								"EDITtxtbandar", null, "style=width:280"));
					}
				} else {
					context
							.put(
									"selectNegeri",
									HTML
											.SelectNegeri("EDITsocNegeri",
													Utils.parseLong(idNegeri),
													null,
													"style=width:280 onChange=\"doChangeGetBandarByNegeriUpdate();\""));
					if (idBandar != "") {
						context.put("selectBandar", HTML
								.SelectBandarByNegeri(idNegeri,
										"EDITtxtbandar", Utils
												.parseLong(idBandar),
										"style=width:280"));
					} else {
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								idNegeri, "EDITtxtbandar", null,
								"style=width:280"));
					}
				}

				// command4
				String submit3 = getParam("command3");
				myLogger.info("[submit]:3 :: " + submit3);

				if ("updateDataPenjaga".equals(submit3)) {

					if (doPost.equals("true")) {

						updateDataPenjaga(session);

					}

					// get data penjaga
					modelNotis.setDataPenjaga(id_penjaga);
					dataPenjaga = modelNotis.getDataPenjaga();

					String idNegeri2 = "0";
					String idBandar2 = "";

					if (dataPenjaga.size() != 0) {
						Hashtable DP2 = (Hashtable) dataPenjaga.get(0);
						idNegeri2 = DP2.get("id_negeri").toString();
						idBandar2 = DP2.get("id_bandar").toString();

					}

					// get list penjaga
					modelNotis.setListPenjaga(idob,
							getParam("id_permohonansimati_atheader"));
					listPenjaga = modelNotis.getListPenjaga();

					if (idNegeri2 == "0") {
						context.put("selectNegeri", HTML.SelectNegeri(
								"socNegeri", null,
								"class=disabled disabled style=width:280"));
						if (idBandar2 != "") {
							context
									.put(
											"selectBandar",
											HTML
													.SelectBandarByNegeri(
															idNegeri2,
															"txtbandar",
															Utils
																	.parseLong(idBandar2),
															null,
															"class=disabled disabled style=width:280"));
						} else {
							context
									.put(
											"selectBandar",
											HTML
													.SelectBandarByNegeri(
															idNegeri2,
															"txtbandar", null,
															null,
															"class=disabled disabled style=width:280"));
						}
					} else {
						context.put("selectNegeri", HTML.SelectNegeri(
								"socNegeri", Utils.parseLong(idNegeri2),
								"class=disabled disabled style=width:280"));
						if (idBandar2 != "") {
							context.put("selectBandar", HTML.SelectBandar(
									"txtbandar", Utils.parseLong(idBandar2),
									"class=disabled disabled style=width:280"));
						} else {
							context.put("selectBandar", HTML.SelectBandar(
									"txtbandar", null,
									"class=disabled disabled style=width:280"));
						}
					}

					// refresh data and size
					context.put("dataPenjaga", dataPenjaga);
					context.put("listPenjaga", listPenjaga);
					context.put("listPenjaga_size", listPenjaga.size());
					context.put("listSemak", list);

					// form validation
					context.put("editPenjaga", "yes");
					context.put("viewLantikPenjaga", "yes");
					context.put("editLantikPenjaga", "no");

				}// close submit3[update]

				else if ("doChangeGetBandarByNegeriUpdate".equals(submit3)) {

					// form validation
					context.put("editPenjaga", "yes");
					context.put("viewLantikPenjaga", "yes");
					context.put("editLantikPenjaga", "yes");
					context.put("onchangeNegeri", "yes");

					String IDNegeri = "";

					IDNegeri = getParam("EDITsocNegeri");

					if (IDNegeri != "") {
						context
								.put(
										"selectNegeri",
										HTML
												.SelectNegeri(
														"EDITsocNegeri",
														Utils
																.parseLong(IDNegeri),
														"style=width:280 onChange=\"doChangeGetBandarByNegeriUpdate();\" "));
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								IDNegeri, "EDITtxtbandar", null, null,
								"style=width:280"));
					} else {
						context.put("selectBandar", HTML.SelectBandar(
								"EDITtxtbandar", null, "style=width:280"));
						context
								.put(
										"selectNegeri",
										HTML
												.SelectNegeri("EDITsocNegeri",
														null,
														"style=width:280 onChange=\"doChangeGetBandarByNegeriUpdate();\" "));
					}

					String a, b, c, txtNoKPBaru, txtNoKPLama, socJenisKp, txtJenisKP, txtNamaPenjaga, socJantina, socAgama, socWarganegara, txtUmur, txtalamat1, txtalamat2, txtalamat3, txtbandar, txtposkod, socNegeri, txtcatatan = "";

					// getdata back
					a = getParam("EDITtxtNoKPBaru1");
					b = getParam("EDITtxtNoKPBaru2");
					c = getParam("EDITtxtNoKPBaru3");

					txtNoKPBaru = a + b + c;

					txtNoKPLama = getParam("EDITtxtNoKPLama");
					socJenisKp = getParam("EDITsocJenisKp");
					txtJenisKP = getParam("EDITtxtJenisKP");
					txtNamaPenjaga = getParam("EDITtxtNamaPenjaga");
					socJantina = getParam("EDITsocJantina");
					socAgama = getParam("EDITsocAgama");
					socWarganegara = getParam("EDITsocWarganegara");
					txtUmur = getParam("EDITtxtUmur");
					txtalamat1 = getParam("EDITtxtalamat1");
					txtalamat2 = getParam("EDITtxtalamat2");
					txtalamat3 = getParam("EDITtxtalamat3");
					txtposkod = getParam("EDITtxtposkod");
					txtcatatan = getParam("EDITtxtcatatan");

					context.put("noKPBaru1x", a);
					context.put("noKPBaru2x", b);
					context.put("noKPBaru3x", c);
					context.put("noKPLamax", txtNoKPLama);
					context.put("jenis_kpx", socJenisKp);
					context.put("noKPLainx", txtJenisKP);
					context.put("namaPenjagax", txtNamaPenjaga);
					context.put("jantinax", socJantina);
					context.put("agamax", socAgama);
					context.put("warganegarax", socWarganegara);
					context.put("umurx", txtUmur);
					context.put("alamat1x", txtalamat1);
					context.put("alamat2x", txtalamat2);
					context.put("alamat3x", txtalamat3);
					context.put("poskodx", txtposkod);
					context.put("catatanx", txtcatatan);

				}// close doChangeGetBandarByNegeriUpdate

			}// close submit2[kemaskini]

			vm = screen2;
		} else if ("semakDataLaporan".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "1";
			}

			// reset data
			context.put("daftarPos", "");
			context.put("namaPenghantar", "");
			context.put("tarikhSerahan", "");
			context.put("namaPenerima", "");
			context.put("catatan", "");
			context.put("kp1", "");
			context.put("kp2", "");
			context.put("kp3", "");
			context.put("kplama", "");
			context.put("kplain", "");
			context.put("serahA", "");
			context.put("serahB", "");
			context.put("statusA", "");
			context.put("statusB", "");
			context.put("statusC", "");
			context.put("statusD", "");
			context.put("disA", "no");
			context.put("disB", "no");
			context.put("disC", "no");
			context.put("disD", "no");
			context.put("alamatEmel", "");
			context.put("tarikhHantar", "");
			context.put("txtDis", "yes");
			context.put("txtDis_2", "yes");
			context.put("jeniskp", "");
			
			context.put("daftarPos_2", "");
			context.put("lain2PeghantarNotis", "");
			id = getParam("id_permohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";
			String idPejabatJKPTG = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
			}

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// --data notis
			String idpbrn = "";

			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idpbrn = idn.get("id_perbicaraan").toString();
			}
			context.put("dataNotis_size", dataNotis.size());

			// get list ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get list ob atas 18
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("listOBatas18", listOBatas18);

			context.put("selectedDropdown", "");

			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idpbrn,
					getParam("id_permohonansimati_atheader"));
			listPenerimaNotis = modelNotis.getListPenerimaNotis();

			// -- 08122009
			modelNotis.setCheckCetakAkuanSumpah(idpbrn,
					getParam("id_permohonansimati_atheader"));
			cetakAkuanSumpah = modelNotis.getCheckCetakAkuanSumpah();

			if (cetakAkuanSumpah.size() != 0) {
				context.put("showButtonCetakAkuanSumpah", "yes");
			} else {
				context.put("showButtonCetakAkuanSumpah", "no");
			}

			// get selected ob
			modelNotis.setSelectedOB(idpbrn, id_permohonansimatiINT, idSimati);
			selectedOB = modelNotis.getSelectedOB();

			// get selected ob untuk 17
			modelNotis
					.setSelectedOB17(idpbrn, id_permohonansimatiINT, idSimati);
			selectedOB17 = modelNotis.getSelectedOB17();

			/** EDITED BY PEJE - KELUARKAN SEMUA OB UNTUK PERMOHONAN TU */
//			if (selectedOB17.size() != 0) {
//				context.put("selectedOB", selectedOB17);
//				context.put("selectedOB_size", selectedOB17.size());
//			} else {
//				context.put("selectedOB", selectedOB);
//				context.put("selectedOB_size", selectedOB.size());
//			}
			context.put("selectedOB", selectedOB);
			context.put("selectedOB_size", selectedOB.size());
				
			// list maklumat penjaga
			modelNotis.setMaklumatPenjaga(id_permohonansimatiINT, idSimati);
			listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

			modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
			penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
			// and
			modelNotis.setPenghantarNotis();
			penghantarNotis = modelNotis.getPenghantarNotis();

			if (penghantarNotisByJkptg.size() != 0) {
				context.put("penghantarNotis", penghantarNotisByJkptg);
			} else {
				context.put("penghantarNotis", penghantarNotis);
			}
			
			//code wp
			String idOBDTL = getParam("id_semaknotisobdtl");
			System.out.println("idOBDTL===="+idOBDTL);
			// get data semak penerima notis
			modelNotis.setDataOBNotis(idOBDTL, getParam("id_permohonansimati_atheader"));
			dataOBNotis = modelNotis.getDataOBNotis();

			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenerimaNotis", listPenerimaNotis);

			// set size
			context.put("dataOBNotis_size", dataOBNotis.size());//code wp
			context.put("dataOBNotis", dataOBNotis);//code wp
			context.put("listOB_size", listOB.size());
			context.put("listPenerimaNotis_size", listPenerimaNotis.size());

			context.put("tarikhHantar", tarikhEmail);//code wp
			context.put("alamatEmel", emailAddress);//code wp
			
			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);

			// form validation
			context.put("viewNotis", "no");
			context.put("editNotis", "no");
			context.put("selectedTab", selectedTab);

			String submit2 = getParam("command2");
			myLogger.info("[submit2] :: " + submit2);

			if ("onchangemyList".equals(submit2)) {

				// get info pemohon
				modelNotis.setListSemak(id, usid);
				list = modelNotis.getListSemak();
				// hashtable maklumat header
				headerppk_baru(session, id, "Y", "", "T");

				if (list.size() != 0) {
					Hashtable ls = (Hashtable) list.get(0);
					idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				}

				// get list ob
				modelNotis.setListOBatas18Semua(id_permohonansimatiINT,
						idSimati, "17");
				listOBatas18 = modelNotis.getListOBatas18Semua();
				context.put("listOBatas18", listOBatas18);

				String dropdown = getParam("myList");

				int jenis_serah = 0;
				int jenis_status = 0;

				jenis_serah = getParamAsInteger("sorJenisSerah");
				jenis_status = getParamAsInteger("sorStatus");

				String daftar_pos = getParam("txtNoDaftarPos");
				String daftar_pos_2 = getParam("txtNoDaftarPos_2");
				
				String id_penghantarnotis = getParam("txtNamaPenghantarNotis");
				String tarikh_serahan = getParam("txdTarikhSerahan");
				String nama_penerima = getParam("txtNamaPenerima");
				String catatan = getParam("txtCatatan");
				String kp1 = getParam("txtNoKPBaru1");
				String kp2 = getParam("txtNoKPBaru2");
				String kp3 = getParam("txtNoKPBaru3");
				String myList = getParam("myList");
				
				//aishahlatip
				String namaLain = "";
				if(id_penghantarnotis.equals("99999")){
					namaLain = getParam("lain2PeghantarNotis");
				}
				context.put("lain2PeghantarNotis",namaLain);

				if (id_penghantarnotis != "") {
					String nama = "";
					String kod = "";
					// get data keputusan permohonan
					getPenghantarNotis = modelNotis
							.getDetailPenghantarNotis(id_penghantarnotis);
					if (getPenghantarNotis.size() != 0) {
						Hashtable x = (Hashtable) getPenghantarNotis.get(0);
						nama = x.get("nama").toString();
						kod = x.get("kod_penghantar_notis").toString();
					}

					context.put("idPenghantar", id_penghantarnotis);
					context.put("namaPenghantar", nama);
					context.put("kodPenghantar", kod);

					// selected penghantar ob
					getSelectedPenghantarNotisByJkptg = modelNotis
							.getSelectedPenghantarNotisByJkptg(idPejabatJKPTG,
									id_penghantarnotis);
					// and
					getSelectedPenghantarNotis = modelNotis
							.getSelectedPenghantarNotis(id_penghantarnotis);
					// check penghantar notis ade o xde dlm negeri
					modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
					penghantarNotisByJkptg = modelNotis
							.getPenghantarNotisByJkptg();

					if (idPejabatJKPTG != "") {
						if (penghantarNotisByJkptg.size() != 0) {
							context.put("penghantarNotisONCHANGE",
									getSelectedPenghantarNotisByJkptg);
						} else {
							context.put("penghantarNotisONCHANGE",
									getSelectedPenghantarNotis);
						}
					} else {
						context.put("penghantarNotisONCHANGE",
								getSelectedPenghantarNotis);
					}

					context.put("onchangeMyList", "yes");

				} else {
					context.put("onchangeMyList", "no");
				}

				String namaPen = "";
				String xkp1 = "";
				String xkp2 = "";
				String xkp3 = "";
				String kplama = "";
				String kplain = "";

				namaOBnKP = modelNotis.getNameNKPByDropdown(dropdown,
						getParam("id_permohonansimati_atheader"));
				if (namaOBnKP.size() != 0) {
					Hashtable obkp = (Hashtable) namaOBnKP.get(0);
					namaPen = obkp.get("nama_ob").toString();
					xkp1 = obkp.get("no_kp_baru1").toString();
					xkp2 = obkp.get("no_kp_baru2").toString();
					xkp3 = obkp.get("no_kp_baru3").toString();
					kplama = obkp.get("no_kp_lama").toString();
					kplain = obkp.get("no_kp_lain").toString();
				}

				if (namaPen != "") {
					context.put("namaPenerima", namaPen);
				} else {
					context.put("namaPenerima", nama_penerima);
				}

				if (xkp1 != "") {
					context.put("jeniskp", "baru");
					context.put("kp1", xkp1);
					context.put("kp2", xkp2);
					context.put("kp3", xkp3);
				} else if (kplama != "") {
					context.put("jeniskp", "lama");
					context.put("kplama", kplama);
				} else if (kplain != "") {
					context.put("jeniskp", "lain");
					context.put("kplain", kplain);
				} else {
					context.put("jeniskp", "");
					context.put("kp1", "");
					context.put("kp2", "");
					context.put("kp3", "");
					context.put("kplama", "");
					context.put("kplain", "");
				}

				context.put("daftarPos", daftar_pos);
				context.put("tarikhSerahan", tarikh_serahan);
				context.put("catatan", catatan);
				context.put("daftarPos_2", daftar_pos_2);

				if (jenis_serah == 1) {
					context.put("serahA", "checked");
					context.put("serahB", "");
					context.put("serahC", "");
					context.put("serahD", "");
					context.put("disA", "yes");
					context.put("disB", "no");
					context.put("disC", "no");
					context.put("disD", "no");
				} else if (jenis_serah == 2) {
					context.put("serahA", "");
					context.put("serahB", "checked");
					context.put("serahC", "");
					context.put("serahD", "");
					context.put("disA", "no");
					context.put("disB", "yes");
					context.put("disC", "no");
					context.put("disD", "no");
				} else if (jenis_serah == 3) {
					context.put("serahA", "");
					context.put("serahB", "");
					context.put("serahC", "checked");
					context.put("serahD", "");
					context.put("disA", "no");
					context.put("disB", "no");
					context.put("disC", "yes");
					context.put("disD", "no");
				} else if (jenis_serah == 4) {
					context.put("serahA", "");
					context.put("serahB", "");
					context.put("serahC", "");
					context.put("serahD", "checked");
					context.put("disA", "no");
					context.put("disB", "no");
					context.put("disC", "no");
					context.put("disD", "yes");
						
				} else {
					context.put("serahA", "");
					context.put("serahB", "");
					context.put("serahC", "");
					context.put("serahD", "");
					context.put("disA", "no");
					context.put("disB", "no");
					context.put("disC", "no");
					context.put("disD", "no");
				}

				if (jenis_status == 1) {
					context.put("statusA", "checked");
					context.put("statusB", "");
					context.put("statusC", "");
					context.put("statusD", "");
					context.put("statusE", "");
					context.put("statusF", "");
					context.put("statusG", "");
					context.put("txtDis", "yes");
					context.put("txtDis_2", "yes");
				} else if (jenis_status == 2) {
					context.put("statusA", "");
					context.put("statusB", "checked");
					context.put("statusC", "");
					context.put("statusD", "");
					context.put("statusE", "");
					context.put("statusF", "");
					context.put("statusG", "");
					context.put("txtDis", "yes");
					context.put("txtDis_2", "yes");
				} else if (jenis_status == 3) {
					context.put("statusA", "");
					context.put("statusB", "");
					context.put("statusC", "checked");
					context.put("statusD", "");
					context.put("statusE", "");
					context.put("statusF", "");
					context.put("statusG", "");
					context.put("txtDis", "no");
					context.put("txtDis_2", "yes");
				} else if (jenis_status == 4) {
					context.put("statusA", "");
					context.put("statusB", "");
					context.put("statusC", "");
					context.put("statusD", "checked");
					context.put("statusE", "");
					context.put("statusF", "");
					context.put("statusG", "");
					context.put("txtDis", "yes");
					context.put("txtDis_2", "yes");
				} else if (jenis_status == 5) {
					context.put("statusA", "");
					context.put("statusB", "");
					context.put("statusC", "");
					context.put("statusD", "");
					context.put("statusE", "checked");
					context.put("statusF", "");
					context.put("statusG", "");
					context.put("txtDis", "yes");
					context.put("txtDis_2", "yes");
				} else if (jenis_status == 6) {
					context.put("statusA", "");
					context.put("statusB", "");
					context.put("statusC", "");
					context.put("statusD", "");
					context.put("statusE", "");
					context.put("statusF", "checked");
					context.put("statusG", "");
					context.put("txtDis", "yes");
					context.put("txtDis_2", "no");
				} else {
					context.put("statusA", "");
					context.put("statusB", "");
					context.put("statusC", "");
					context.put("statusD", "");
					context.put("statusE", "");
					context.put("statusF", "");
					context.put("statusG", "");
					context.put("txtDis", "yes");
					context.put("txtDis_2", "yes");
				}

			}// close onchangemyList

			vm = screen1;

		}// close semakNoLaporan

		else if ("tindakanPeg".equals(submit)) {
		String selectedTab = "";

		selectedTab = getParam("tabId").toString();

		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "1";
		}else{
			selectedTab = "1";
		}
		context.put("selectedTab", selectedTab);
		System.out.println("selectedTab2==="+selectedTab);
		// reset data
		
		context.put("daftarPos", "");
		context.put("daftarPos_2", "");

		id = getParam("id_permohonan");

		// get info pemohon
		modelNotis.setListSemak(id, usid);
		list = modelNotis.getListSemak();
		// hashtable maklumat header
		headerppk_baru(session, id, "Y", "", "T");

		String idSimati = "";
		String idStatus = "";
		String idPejabatJKPTG = "";
		String id_negeri = "";

		if (list.size() != 0) {
			Hashtable ls = (Hashtable) list.get(0);
			idSimati = ls.get("idSimati").toString();
			idStatus = ls.get("id_Status").toString();
			idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
			id_negeri = ls.get("pmidnegeri").toString();
		}
		
		if (id_negeri != "") {
			context.put("xidnegerix", id_negeri);
		}

		// get data keputusan permohonan
		keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

		String idkp = "";

		if (keputusanPermohonan.size() != 0) {
			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
			idkp = kp.get("id_keputusanpermohonan").toString();
		}
		
		

		// --data notis
		modelNotis.setListSemakWithData(idkp);
		dataNotis = modelNotis.getListSemakWithData();

		String idperbicaraan = "";
		String idpsk = "";
		String idNeg = "";
		String currentBil = "";
		String idpejabat = "";
		String idjenispejabat = "";
		String tempatBicara = "";

		

		// --data notis
		String idpbrn = "";
		String nama_peg_pengendali = "";

		modelNotis.setListSemakWithData(idkp);
		dataNotis = modelNotis.getListSemakWithData();

		if (dataNotis.size() != 0) {
			Hashtable idn = (Hashtable) dataNotis.get(0);
			idpbrn = idn.get("id_perbicaraan").toString();
			idpsk = idn.get("id_unitpsk").toString();
			idNeg = idn.get("id_negeribicara").toString();
			currentBil = idn.get("bil_bicara").toString();
			idpejabat = idn.get("id_pejabat").toString();
			tempatBicara = idn.get("tempat_bicara").toString();
			idjenispejabat = idn.get("id_jenispejabat").toString();
			nama_peg_pengendali = idn.get("peg_pengendali").toString();
			
			
			if(nama_peg_pengendali.equals("")){
				nama_peg_pengendali = "";
			}
		}
		context.put("dataNotis_size", dataNotis.size());
		context.put("nama_peg_pengendali", nama_peg_pengendali);
		
		
		//------------
		System.out.println("nama_peg_pengendali::"+nama_peg_pengendali);
		System.out.println("idjenispejabat::"+idjenispejabat);
		
		if (idjenispejabat.equals("22")) {
			// dropdown pejabat jkptg
			if (idPejabatJKPTG != "") {
				if (idpejabat != "") {
					context
							.put(
									"showBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"editTempatBicara",
													Utils
															.parseLong(idpejabat),
													null,
													"style=width:400 class=disabled disabled "));
				} else {
					context
							.put(
									"showBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"editTempatBicara",
													null, null,
													"style=width:400 class=disabled disabled "));
				}
			} else {
				if (idpejabat != "") {
					context.put("showBicara", HTML.SelectTempatBicara(
							"editTempatBicara", Utils.parseLong(idpejabat),
							null,
							"style=width:400 class=disabled disabled  "));
				} else {
					context.put("showBicara", HTML.SelectTempatBicara(
							"editTempatBicara", null, null,
							"style=width:400 class=disabled disabled  "));
				}
			}

			// radio button
			context.put("checkP1", "checked");
			context.put("checkP2", "");
			context.put("checkP3", "");

		} else if (idjenispejabat.equals("2")) {
			// dropdown pejabat tanah
			if (idPejabatJKPTG != "") {
				if (idpejabat != "") {
					context
							.put(
									"showBicara",
									HTML
											.SelectPejabatTanahByJKPTG(
													idPejabatJKPTG,
													"editTempatBicara",
													Utils
															.parseLong(idpejabat),
													null,
													"style=width:400 class=disabled disabled "));
				} else {
					context
							.put(
									"showBicara",
									HTML
											.SelectPejabatTanahByJKPTG(
													idPejabatJKPTG,
													"editTempatBicara",
													null, null,
													"style=width:400 class=disabled disabled "));
				}
			} else {
				if (idpejabat != "") {
					context.put("showBicara", HTML.SelectPejabatTanah(
							"editTempatBicara", Utils.parseLong(idpejabat),
							null,
							"style=width:400 class=disabled disabled "));
				} else {
					context.put("showBicara", HTML.SelectPejabatTanah(
							"editTempatBicara", null, null,
							"style=width:400 class=disabled disabled "));
				}
			}
			// radio button
			context.put("checkP1", "");
			context.put("checkP2", "checked");
			context.put("checkP3", "");

		} else if (idjenispejabat.equals("0")) {

			// dropdown pejabat tanah
			context
					.put(
							"showBicara",
							"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52'  maxlength='60' value='"
									+ tempatBicara
									+ "' class='disabled' readonly>");

			// radio button
			context.put("checkP1", "");
			context.put("checkP2", "");
			context.put("checkP3", "checked");

		} else {
			// dropdown pejabat jkptg
			if (idPejabatJKPTG != "") {
				if (idpejabat != "") {
					context
							.put(
									"showBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"editTempatBicara",
													Utils
															.parseLong(idpejabat),
													null,
													"style=width:400 class=disabled disabled "));
				} else {
					context
							.put(
									"showBicara",
									HTML
											.SelectTempatBicaraByPejabatJKPTG(
													idPejabatJKPTG,
													"editTempatBicara",
													null, null,
													"style=width:400 class=disabled disabled "));
				}
			} else {
				if (idpejabat != "") {
					context.put("showBicara", HTML.SelectTempatBicara(
							"editTempatBicara", Utils.parseLong(idpejabat),
							null,
							"style=width:400 class=disabled disabled  "));
				} else {
					context.put("showBicara", HTML.SelectTempatBicara(
							"editTempatBicara", null, null,
							"style=width:400 class=disabled disabled  "));
				}
			}

			// radio button
			context.put("checkP1", "checked");
			context.put("checkP2", "");
			context.put("checkP3", "");
		}
		

		
		if (idNeg != "") {
			context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
					Utils.parseLong(idNeg),
					"class=disabled disabled style=width:305"));
			
		} else {
			context.put("SELECTNegeri", HTML.SelectNegeri("editNegeri",
					null, "class=disabled disabled style=width:305"));
		}

		
		
		/*if (id_negeri != "") {
			
			if (idpsk != "") {
				context.put("SELECTPegawai", HTML
						.SelectPegawaiPengendaliByNegeri(id_negeri,
								"editPegawai", Utils.parseLong(idpsk),
								"class=disabled disabled style=width:305"));
				
				nama_peg_pengendali = modelNotis.getNamaPengendaliByNegeri(id_negeri,idpsk);
				context.put("nama_peg_pengendali", nama_peg_pengendali);
				
				System.out.println(":::::1:::::");
				
			} else {
				context.put("SELECTPegawai", HTML
						.SelectPegawaiPengendaliByNegeri(id_negeri,
								"editPegawai", null,
								"class=disabled disabled style=width:305"));
				
				nama_peg_pengendali = modelNotis.getNamaPengendaliByNegeri(id_negeri,null);
				context.put("nama_peg_pengendali", "");
				
				System.out.println(":::::2:::::");
			}

		} else {

			if (idpsk != "") {
				
				context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
						"editPegawai", Utils.parseLong(idpsk),
						"class=disabled disabled style=width:305"));
				
				nama_peg_pengendali = modelNotis.getNamaPengendali(idpsk);
				context.put("nama_peg_pengendali", nama_peg_pengendali);
				
				System.out.println(":::::3:::::");
				
			} else {
				context.put("SELECTPegawai", HTML.SelectPegawaiPengendali(
						"editPegawai", null,
						"class=disabled disabled style=width:305"));
				
				nama_peg_pengendali = modelNotis.getNamaPengendali(null);
				context.put("nama_peg_pengendali", "");
				
				System.out.println(":::::4:::::");
			}
		}*/
		
		//-----------
		
		
		
		
		
		//System.out.println("nama_peg_pengendali==="+nama_peg_pengendali);
		// -- aishahlatip 18052017
		String username = (String) session.getAttribute("_portal_username");
		modelNotis.setValidPegawaiPengendali(usid,idpbrn,nama_peg_pengendali,username );
		validPegPengendali = modelNotis.getValidPegawaiPengendali();

		System.out.println("validPegPengendali.size()==="+validPegPengendali.size());
		if (validPegPengendali.size() != 0) {
			context.put("enabledPegawai", "yes");
		} else {
			context.put("enabledPegawai", "no");
		}
		
		boolean statusHantarPNB = false;
		// validate status hantar PNB
		statusHantarPNB = modelNotis.getPNBValidation(idpbrn);
		if (statusHantarPNB) {
			context.put("statusPNB", "yes");
		} else {
			context.put("statusPNB", "no");
		}
		
		

		// get list ob
		modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "8",null);
		listOB = modelNotis.getListSemuaOB();

		// get list ob atas 18
		modelNotis.setListOBatas18Semua(id_permohonansimati, idSimati, "8");
		listOBatas18 = modelNotis.getListOBatas18Semua();
		context.put("listOBatas18", listOBatas18);

		context.put("selectedDropdown", "");

		// get senarai penerima notis
		modelNotis.setListPenerimaNotis(idpbrn,
				getParam("id_permohonansimati_atheader"));
		listPenerimaNotis = modelNotis.getListPenerimaNotis();

		// -- 08122009
		modelNotis.setCheckCetakAkuanSumpah(idpbrn,
				getParam("id_permohonansimati_atheader"));
		cetakAkuanSumpah = modelNotis.getCheckCetakAkuanSumpah();

		if (cetakAkuanSumpah.size() != 0) {
			context.put("showButtonCetakAkuanSumpah", "yes");
		} else {
			context.put("showButtonCetakAkuanSumpah", "no");
		}

		// get selected ob
		modelNotis.setSelectedOB(idpbrn, id_permohonansimati, idSimati);
		selectedOB = modelNotis.getSelectedOB();

		// list maklumat penjaga
		modelNotis.setMaklumatPenjaga(id_permohonansimati, idSimati);
		listMaklumatPenjaga = modelNotis.getMaklumatPenjaga();

		modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
		penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
		// and
		modelNotis.setPenghantarNotis();
		penghantarNotis = modelNotis.getPenghantarNotis();
		
		//code wp
		String idOBDTL = getParam("id_semaknotisobdtl");
		// get data semak penerima notis
		modelNotis.setDataOBNotis(idOBDTL, getParam("id_permohonansimati_atheader"));
		dataOBNotis = modelNotis.getDataOBNotis();

		if (penghantarNotisByJkptg.size() != 0) {
			context.put("penghantarNotis", penghantarNotisByJkptg);
		} else {
			context.put("penghantarNotis", penghantarNotis);
		}

		// list & data
		context.put("listSemak", list);
		context.put("listOB", listOB);
		context.put("listPenerimaNotis", listPenerimaNotis);
		context.put("selectedOB", selectedOB);

		// set size
		context.put("dataOBNotis_size", dataOBNotis.size());//code wp
		context.put("dataOBNotis", dataOBNotis);//code wp
		context.put("listOB_size", listOB.size());
		context.put("selectedOB_size", selectedOB.size());
		context.put("listPenerimaNotis_size", listPenerimaNotis.size());

		context.put("tarikhHantar", tarikhEmail);//code wp
		context.put("alamatEmel", emailAddress);//code wp
		
		// id
		context.put("id_permohonan", id);
		context.put("id_keputusanpermohonan", idkp);
		context.put("id_simati", idSimati);
		context.put("id_status", idStatus);

		// form validation
		context.put("viewNotis", "no");
		context.put("editNotis", "no");
		context.put("selectedTab", selectedTab);
		context.put("lain2PeghantarNotis", lain2PeghantarNotis);
		
		System.out.println("selectedTab3==="+selectedTab);
		
		String signedData = getParam("signedData");
		String open_pupop = getParam("open_pupop");
		if(open_pupop.equals("Y"))
		{
			//NO_FAIL="+NO_FAIL+"&id_perbicaraan="+id_perbicaraan+"&idfail="+idfail+
			context.put("open_pupop",open_pupop);
			context.put("NO_FAIL", getParam("NO_FAIL"));
			context.put("id_perbicaraan", getParam("id_perbicaraan"));
			context.put("idfail", getParam("idfail"));		
			context.put("signedData", getParam("signedData"));				
		}
		else
		{
			context.put("open_pupop","");
		}
		

		//GET SIGNEDDATA
		String dataDahSign = modelNotis.getSignedData(getParam("id_perbicaraan"));
		context.put("dataDahSign", dataDahSign);
		

		vm = screen1;

	}// close tindakanPeg


		else if ("simpanLaporan".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "2";

			}// close tab

			context.put("selectedTab", selectedTab);

			if (doPost.equals("true")) {

				simpanLaporan(session);
			}

			id = getParam("id_permohonan");

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
			}

			// get list ob
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("listOBatas18", listOBatas18);

			// --data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idperbicaraan = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
			}

			// get list ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idperbicaraan,
					getParam("id_permohonansimati_atheader"));
			listPenerimaNotis = modelNotis.getListPenerimaNotis();
			
			
			// -- 08122009
			modelNotis.setCheckCetakAkuanSumpah(idperbicaraan,
					getParam("id_permohonansimati_atheader"));
			cetakAkuanSumpah = modelNotis.getCheckCetakAkuanSumpah();

			if (cetakAkuanSumpah.size() != 0) {
				context.put("showButtonCetakAkuanSumpah", "yes");
			} else {
				context.put("showButtonCetakAkuanSumpah", "no");
			}

			// get selected ob
			modelNotis.setSelectedOB(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB = modelNotis.getSelectedOB();

			// get selected ob untuk 17
			modelNotis.setSelectedOB17(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB17 = modelNotis.getSelectedOB17();

			if (selectedOB17.size() != 0) {
				context.put("selectedOB", selectedOB17);
				context.put("selectedOB_size", selectedOB17.size());
			} else {
				context.put("selectedOB", selectedOB);
				context.put("selectedOB_size", selectedOB.size());
			}

			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenerimaNotis", listPenerimaNotis);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenerimaNotis_size", listPenerimaNotis.size());

			// reset data
			context.put("disabledStatus1", "");
			context.put("disabledStatus2", "");
			context.put("disabledStatus3", "");
			context.put("disabledStatus4", "");
			context.put("disabledTxtSurat", "");
			context.put("Skp1", "");
			context.put("Skp2", "");
			context.put("Skp3", "");
			context.put("Sdftr", "");
			context.put("SnamaPntr", "");
			context.put("Starikh", "");
			context.put("SnamaP", "");
			context.put("Scatatan", "");
			context.put("namaHantar", "");
			context.put("tarikhSerah", "");
			context.put("namaTerima", "");
			context.put("kp1", "");
			context.put("kp2", "");
			context.put("kp3", "");
			context.put("jeniskp", "");
			context.put("kplama", "");
			context.put("kplain", "");
			context.put("catatan", "");
			context.put("noSurat", "");
			context.put("daftarPos", "");
			context.put("namaPenghantar", "");
			context.put("tarikhSerahan", "");
			context.put("namaPenerima", "");
			context.put("serahA", "");
			context.put("serahB", "");
			context.put("statusA", "");
			context.put("statusB", "");
			context.put("statusC", "");
			context.put("statusD", "");
			context.put("disA", "no");
			context.put("disB", "no");
			context.put("txtDis", "yes");
			context.put("disC", "no");
			context.put("disD", "no");
			context.put("alamatEmel", "");
			context.put("tarikhHantar", "");
			context.put("txtDis", "yes");
			context.put("txtDis_2", "yes");

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);

			// form validation
			context.put("viewNotis", "no");
			context.put("editNotis", "no");

			vm = screen1;

		}// close simpanLaporan

		else if ("simpanTambahanLaporan".equals(submit)) {

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "2";

			}// close tab

			context.put("selectedTab", selectedTab);

			if (doPost.equals("true")) {

				simpanTambahanLaporan(session);
			}

			String idkp = "";

			id = getParam("id_permohonan");
			idkp = getParam("id_keputusanpermohonan");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
			}

			// get list ob
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("listOBatas18", listOBatas18);

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idperbicaraan = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
			}

			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idperbicaraan,
					getParam("id_permohonansimati_atheader"));
			listPenerimaNotis = modelNotis.getListPenerimaNotis();

			// -- 08122009
			modelNotis.setCheckCetakAkuanSumpah(idperbicaraan,
					getParam("id_permohonansimati_atheader"));
			cetakAkuanSumpah = modelNotis.getCheckCetakAkuanSumpah();

			if (cetakAkuanSumpah.size() != 0) {
				context.put("showButtonCetakAkuanSumpah", "yes");
			} else {
				context.put("showButtonCetakAkuanSumpah", "no");
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// get selected ob
			modelNotis.setSelectedOB(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB = modelNotis.getSelectedOB();

			// get selected ob untuk 17
			modelNotis.setSelectedOB17(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB17 = modelNotis.getSelectedOB17();

			if (selectedOB17.size() != 0) {
				context.put("selectedOB", selectedOB17);
				context.put("selectedOB_size", selectedOB17.size());
			} else {
				context.put("selectedOB", selectedOB);
				context.put("selectedOB_size", selectedOB.size());
			}

			// reset data
			context.put("disabledStatus1", "");
			context.put("disabledStatus2", "");
			context.put("disabledStatus3", "");
			context.put("disabledStatus4", "");
			context.put("disabledTxtSurat", "");
			context.put("Skp1", "");
			context.put("Skp2", "");
			context.put("Skp3", "");
			context.put("Sdftr", "");
			context.put("SnamaPntr", "");
			context.put("Starikh", "");
			context.put("SnamaP", "");
			context.put("Scatatan", "");
			context.put("namaHantar", "");
			context.put("tarikhSerah", "");
			context.put("namaTerima", "");
			context.put("kp1", "");
			context.put("kp2", "");
			context.put("kp3", "");
			context.put("jeniskp", "");
			context.put("kplama", "");
			context.put("kplain", "");
			context.put("catatan", "");
			context.put("noSurat", "");
			context.put("daftarPos", "");
			context.put("namaPenghantar", "");
			context.put("tarikhSerahan", "");
			context.put("namaPenerima", "");
			context.put("serahA", "");
			context.put("serahB", "");
			context.put("statusA", "");
			context.put("statusB", "");
			context.put("statusC", "");
			context.put("statusD", "");
			context.put("disA", "no");
			context.put("disB", "no");
			context.put("txtDis", "yes");

			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenerimaNotis", listPenerimaNotis);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenerimaNotis_size", listPenerimaNotis.size());

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);

			// form validation
			context.put("viewNotis", "no");
			context.put("editNotis", "no");

			vm = screen1;

		}// close simpanTambahanLaporan

		else if ("semakPenerimaNotis".equals(submit)) {

			String jenisSerah = "";
			String jenisPos = "";
			String serah1 = "";
			String serah2 = "";
			String pos1 = "";
			String pos2 = "";
			String pos1_2 = "";
			String pos2_2 = "";
			String emel1 = "";
			
			String jenisEmel = "";
			String jenisPosBiasa = "";

			String selectedTab = "";

			selectedTab = getParam("tabId").toString();

			if (selectedTab == null || "".equals(selectedTab)) {
				selectedTab = "2";
			}

			context.put("selectedTab", selectedTab);

			// reset data onchange
			context.put("Skp1", "");
			context.put("Skp2", "");
			context.put("Skp3", "");
			context.put("Sjeniskp", "");
			context.put("Skplama", "");
			context.put("Skplain", "");
			context.put("Sdftr", "");
			context.put("SnamaPntr", "");
			context.put("Starikh", "");
			context.put("SnamaP", "");
			context.put("Scatatan", "");
			context.put("jeniskpx", "");

			String idOBDTL = "";

			id = getParam("id_permohonan");
			idOBDTL = getParam("id_semaknotisobdtl");

			// get info pemohon
			modelNotis.setListSemak(id, usid);
			list = modelNotis.getListSemak();
			// hashtable maklumat header
			headerppk_baru(session, id, "Y", "", "T");

			String idSimati = "";
			String idStatus = "";
			String idPejabatJKPTG = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
			}

			// get list ob
			modelNotis.setListOBatas18Semua(id_permohonansimatiINT, idSimati,
					"17");
			listOBatas18 = modelNotis.getListOBatas18Semua();
			context.put("listOBatas18", listOBatas18);

			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(id);

			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// get data ob
			modelNotis.setListSemuaOB(id_permohonansimatiINT, idSimati, "17",
					null);
			listOB = modelNotis.getListSemuaOB();

			// data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();

			String idperbicaraan = "";

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
			}

			// get senarai penerima notis
			modelNotis.setListPenerimaNotis(idperbicaraan,
					getParam("id_permohonansimati_atheader"));
			listPenerimaNotis = modelNotis.getListPenerimaNotis();

			// get data semak penerima notis
			modelNotis.setDataOBNotis(idOBDTL,
					getParam("id_permohonansimati_atheader"));
			dataOBNotis = modelNotis.getDataOBNotis();
			String id_penghantarnotis = "";
			String daftarPos_2 = "";
			String nama_penghantar_lain ="";
			if (dataOBNotis.size() != 0) {
				Hashtable dob = (Hashtable) dataOBNotis.get(0);
				id_penghantarnotis = dob.get("id_penghantarnotis").toString();
				daftarPos_2  = dob.get("tarikh_serahan").toString();
				nama_penghantar_lain = dob.get("nama_penghantar_lain").toString();
			}

			// selected penghantar ob
			getSelectedPenghantarNotisByJkptg = modelNotis
					.getSelectedPenghantarNotisByJkptg(idPejabatJKPTG,
							id_penghantarnotis);
			// and
			getSelectedPenghantarNotis = modelNotis
					.getSelectedPenghantarNotis(id_penghantarnotis);
			// check penghantar notis ade o xde dlm negeri
			modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
			penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();

			if (idPejabatJKPTG != "") {
				if (penghantarNotisByJkptg.size() != 0) {
					context.put("dataPenghantarNotis",
							getSelectedPenghantarNotisByJkptg);
				} else {
					context.put("dataPenghantarNotis",
							getSelectedPenghantarNotis);
				}
			} else {
				context.put("dataPenghantarNotis", getSelectedPenghantarNotis);
			}

			String jenis_serah = "";
			String status_serah = "";

			String namaP = "";
			String kpbaru = "";
			String kplama = "";
			String kplain = "";

			Hashtable OBN = new Hashtable();

			if (dataOBNotis.size() != 0) {
				OBN = (Hashtable) dataOBNotis.get(0);
				jenis_serah = OBN.get("jenis_serah").toString();
				status_serah = OBN.get("status_serah").toString();
				namaP = OBN.get("nama_ob").toString();
				kpbaru = OBN.get("no_kp_baru").toString();
				kplama = OBN.get("no_kp_lama").toString();
				kplain = OBN.get("no_kp_lain").toString();
			}

			if (kpbaru != "") {
				context.put("jeniskpx", "baru");
			} else if (kplama != "") {
				context.put("jeniskpx", "lama");
			} else if (kplain != "") {
				context.put("jeniskpx", "lain");
			} else {
				context.put("jeniskpx", "");
			}

			// get selected ob
			modelNotis.setSelectedOB(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB = modelNotis.getSelectedOB();

			// get selected ob untuk 17
			modelNotis.setSelectedOB17(idperbicaraan, id_permohonansimatiINT,
					idSimati);
			selectedOB17 = modelNotis.getSelectedOB17();

			if (selectedOB17.size() != 0) {
				context.put("selectedOB", selectedOB17);
				context.put("selectedOB_size", selectedOB17.size());
			} else {
				context.put("selectedOB", selectedOB);
				context.put("selectedOB_size", selectedOB.size());
			}

			// radio button jenis serah
			if (jenis_serah.equals("1")) {
				jenisSerah = "checked";
				jenisPos = "";
				jenisEmel = "";
				jenisPosBiasa = "";
			} else if (jenis_serah.equals("2")) {
				jenisSerah = "";
				jenisPos = "checked";
				jenisEmel = "";
				jenisPosBiasa = "";
			} else if (jenis_serah.equals("3")) {
				jenisSerah = "";
				jenisPos ="";
				jenisEmel = "checked";
				jenisPosBiasa = "";
			} else if (jenis_serah.equals("4")) {
				jenisSerah = "";
				jenisPos ="";
				jenisEmel = "";
				jenisPosBiasa = "checked";
			} else {
				jenisSerah = "";
				jenisPos = "";
				jenisEmel = "";
				jenisPosBiasa = "";
			}
			
			//System.out.println("jenis_serah=="+jenis_serah);
			context.put("jenisSerah", jenisSerah);
			context.put("jenisPos", jenisPos);
			context.put("jenisEmel", jenisEmel);
			context.put("jenisPosBiasa", jenisPosBiasa);

			// radio button status serah
						if (status_serah.equals("1")) {
							serah1 = "checked";
							serah2 = "";
							pos1 = "";
							pos2 = "";
							pos1_2 = "";
							pos2_2 = "";
							emel1 = "";
							
							context.put("daftarPos_2", "");
						} else if (status_serah.equals("2")) {
							serah1 = "";
							serah2 = "checked";
							pos1 = "";
							pos2 = "";
							pos1_2 = "";
							pos2_2 = "";
							emel1 = "";
							
							context.put("daftarPos_2", "");
						} else if (status_serah.equals("3")) {
							serah1 = "";
							serah2 = "";
							pos1 = "checked";
							pos2 = "";
							pos1_2 = "";
							pos2_2 = "";
							emel1 = "";
							
							context.put("daftarPos_2", "");
						} else if (status_serah.equals("4")) {
							serah1 = "";
							serah2 = "";
							pos1 = "";
							pos2 = "checked";
							pos1_2 = "";
							pos2_2 = "";
							emel1 = "";
							
							context.put("daftarPos_2", "");
						} else if (status_serah.equals("5")) {
							serah1 = "";
							serah2 = "";
							pos1 = "";
							pos2 = "";
							pos1_2 = "";
							pos2_2 = "";
							emel1 = "checked";
							
							context.put("daftarPos_2", "");
						} else if (status_serah.equals("6")) {
							serah1 = "";
							serah2 = "";
							pos1 = "";
							pos2 = "";
							pos1_2 = "checked";
							pos2_2 = "";
							emel1 = "";
							
							context.put("daftarPos_2", daftarPos_2);
							
						} else if (status_serah.equals("7")) {
							serah1 = "";
							serah2 = "";
							pos1 = "";
							pos2 = "";
							pos1_2 = "";
							pos2_2 = "checked";
							emel1 = "";
							
							context.put("daftarPos_2", "");
						} else {
							serah1 = "";
							serah2 = "";
							pos1 = "";
							pos2 = "";
							pos1_2 = "";
							pos2_2 = "";
							emel1 = "";
							
							context.put("daftarPos_2", "");
						}
						context.put("serah1", serah1);
						context.put("serah2", serah2);
						context.put("pos1", pos1);
						context.put("pos2", pos2);
						context.put("pos1_2", pos1_2);
						context.put("pos2_2", pos2_2);
						context.put("emel1", emel1);
						
						context.put("lain2PeghantarNotis", nama_penghantar_lain);//aishahlatip


			// list & data
			context.put("listSemak", list);
			context.put("listOB", listOB);
			context.put("listPenerimaNotis", listPenerimaNotis);
			context.put("dataOBNotis", dataOBNotis);
			context.put("selectedOB", selectedOB);

			// set size
			context.put("listOB_size", listOB.size());
			context.put("listPenerimaNotis_size", listPenerimaNotis.size());

			// id
			context.put("id_permohonan", id);
			context.put("id_keputusanpermohonan", idkp);
			context.put("id_simati", idSimati);
			context.put("id_status", idStatus);
			context.put("id_semaknotisobdtl", idOBDTL);
			context.put("id_penghantarnotis", id_penghantarnotis);

			// form validation
			context.put("viewNotis", "yes");
			context.put("editNotis", "no");
			context.put("onchangeMyList", "no");

			String submit2 = getParam("command2");
			myLogger.info("[submit]2 :: " + submit2);

			if ("kemaskiniNotis".equals(submit2)) {

				// form validation
				context.put("viewNotis", "yes");
				context.put("editNotis", "yes");
				context.put("onchangeMyList", "no");

				// get selected ob
				modelNotis.setSelectedOB(idperbicaraan, id_permohonansimatiINT,
						idSimati);
				selectedOB = modelNotis.getSelectedOB();

				// get selected ob untuk 17
				modelNotis.setSelectedOB17(idperbicaraan,
						id_permohonansimatiINT, idSimati);
				selectedOB17 = modelNotis.getSelectedOB17();

				if (selectedOB17.size() != 0) {
					context.put("selectedOB", selectedOB17);
					context.put("selectedOB_size", selectedOB17.size());
				} else {
					context.put("selectedOB", selectedOB);
					context.put("selectedOB_size", selectedOB.size());
				}

				// get list ob
				modelNotis.setListOBatas18Semua(id_permohonansimatiINT,
						idSimati, "17");
				listOBatas18 = modelNotis.getListOBatas18Semua();
				context.put("listOBatas18", listOBatas18);

				String submit3 = getParam("command3");
				myLogger.info("[submit]3 :: " + submit3);
				
			
				if ("updateLaporan".equals(submit3)) {

					if (doPost.equals("true")) {

						updateLaporan(session);

					}

					// get senarai penerima notis
					modelNotis.setListPenerimaNotis(idperbicaraan,
							getParam("id_permohonansimati_atheader"));
					listPenerimaNotis = modelNotis.getListPenerimaNotis();

					// get data semak penerima notis
					modelNotis.setDataOBNotis(idOBDTL,
							getParam("id_permohonansimati_atheader"));
					dataOBNotis = modelNotis.getDataOBNotis();
				//	String lain2PeghantarNotis = "";
					if (dataOBNotis.size() != 0) {
						OBN = (Hashtable) dataOBNotis.get(0);
						jenis_serah = OBN.get("jenis_serah").toString();
						status_serah = OBN.get("status_serah").toString();
						lain2PeghantarNotis = OBN.get("nama_penghantar_lain").toString();
					}
					
					context.put("lain2PeghantarNotis", lain2PeghantarNotis);

					String namaPx = "";
					String kpbarux = "";
					String kplamax = "";
					String kplainx = "";

					if (dataOBNotis.size() != 0) {
						namaPx = OBN.get("nama_ob").toString();
						kpbarux = OBN.get("no_kp_baru").toString();
						kplamax = OBN.get("no_kp_lama").toString();
						kplainx = OBN.get("no_kp_lain").toString();
					}

					if (kpbarux != "") {
						context.put("jeniskpx", "baru");
					} else if (kplamax != "") {
						context.put("jeniskpx", "lama");
					} else if (kplainx != "") {
						context.put("jeniskpx", "lain");
					} else {
						context.put("jeniskpx", "");
					}

					// radio button jenis serah
					/*if (jenis_serah.equals("1")) {
						jenisSerah = "checked";
						jenisPos = "";
					} else if (jenis_serah.equals("2")) {
						jenisSerah = "";
						jenisPos = "checked";
					} else {
						jenisSerah = "";
						jenisPos = "";
					}
					context.put("jenisSerah", jenisSerah);
					context.put("jenisPos", jenisPos);

					// radio button status serah
					if (status_serah.equals("1")) {
						serah1 = "checked";
						serah2 = "";
						pos1 = "";
						pos2 = "";
					} else if (status_serah.equals("2")) {
						serah1 = "";
						serah2 = "checked";
						pos1 = "";
						pos2 = "";
					} else if (status_serah.equals("3")) {
						serah1 = "";
						serah2 = "";
						pos1 = "checked";
						pos2 = "";
					} else if (status_serah.equals("4")) {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "checked";
					} else {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "";
					}
					context.put("serah1", serah1);
					context.put("serah2", serah2);
					context.put("pos1", pos1);
					context.put("pos2", pos2);*/
					
					
					// radio button jenis serah
					if (jenis_serah.equals("1")) {
						jenisSerah = "checked";
						jenisPos = "";
						jenisEmel = "";
						jenisPosBiasa = "";
					} else if (jenis_serah.equals("2")) {
						jenisSerah = "";
						jenisPos = "checked";
						jenisEmel = "";
						jenisPosBiasa = "";
					} else if (jenis_serah.equals("3")) {
						jenisSerah = "";
						jenisPos ="";
						jenisEmel = "checked";
						jenisPosBiasa = "";
					} else if (jenis_serah.equals("4")) {
						jenisSerah = "";
						jenisPos ="";
						jenisEmel = "";
						jenisPosBiasa = "checked";
					} else {
						jenisSerah = "";
						jenisPos = "";
						jenisEmel = "";
						jenisPosBiasa = "";
					}
					
					//System.out.println("jenis_serah=="+jenis_serah);
					context.put("jenisSerah", jenisSerah);
					context.put("jenisPos", jenisPos);
					context.put("jenisEmel", jenisEmel);
					context.put("jenisPosBiasa", jenisPosBiasa);
					
					// radio button status serah
					if (status_serah.equals("1")) {
						serah1 = "checked";
						serah2 = "";
						pos1 = "";
						pos2 = "";
						pos1_2 = "";
						pos2_2 = "";
						emel1 = "";
						
						context.put("daftarPos_2", "");
					} else if (status_serah.equals("2")) {
						serah1 = "";
						serah2 = "checked";
						pos1 = "";
						pos2 = "";
						pos1_2 = "";
						pos2_2 = "";
						emel1 = "";
						
						context.put("daftarPos_2", "");
					} else if (status_serah.equals("3")) {
						serah1 = "";
						serah2 = "";
						pos1 = "checked";
						pos2 = "";
						pos1_2 = "";
						pos2_2 = "";
						emel1 = "";
						
						context.put("daftarPos_2", "");
					} else if (status_serah.equals("4")) {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "checked";
						pos1_2 = "";
						pos2_2 = "";
						emel1 = "";
						
						context.put("daftarPos_2", "");
					} else if (status_serah.equals("5")) {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "";
						pos1_2 = "";
						pos2_2 = "";
						emel1 = "checked";
						
						context.put("daftarPos_2", "");
					} else if (status_serah.equals("6")) {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "";
						pos1_2 = "checked";
						pos2_2 = "";
						emel1 = "";
						
						context.put("daftarPos_2", daftarPos_2);
						
					} else if (status_serah.equals("7")) {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "";
						pos1_2 = "";
						pos2_2 = "checked";
						emel1 = "";
						
						context.put("daftarPos_2", "");
					} else {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "";
						pos1_2 = "";
						pos2_2 = "";
						emel1 = "";
						
						context.put("daftarPos_2", "");
					}
					context.put("serah1", serah1);
					context.put("serah2", serah2);
					context.put("pos1", pos1);
					context.put("pos2", pos2);
					context.put("pos1_2", pos1_2);
					context.put("pos2_2", pos2_2);
					context.put("emel1", emel1);

					// data
					context.put("dataOBNotis", dataOBNotis);
					context.put("listPenerimaNotis", listPenerimaNotis);

					// get list ob
					modelNotis.setListOBatas18Semua(id_permohonansimatiINT,
							idSimati, "17");
					listOBatas18 = modelNotis.getListOBatas18Semua();
					context.put("listOBatas18", listOBatas18);

					// form validation
					context.put("viewNotis", "yes");
					context.put("editNotis", "no");
					context.put("onchangeMyList", "no");

				}// close update laporan

				else if ("onchangemyListUPDATE".equals(submit3)) {

					id = getParam("id_permohonan");

					// get info pemohon
					modelNotis.setListSemak(id, usid);
					list = modelNotis.getListSemak();
					// hashtable maklumat header
					headerppk_baru(session, id, "Y", "", "T");

					if (list.size() != 0) {
						Hashtable ls = (Hashtable) list.get(0);
						idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
					}

					String dropdown = getParam("EditMyList");

					String kp1 = getParam("EDITtxtNoKPBaru1");
					String kp2 = getParam("EDITtxtNoKPBaru2");
					String kp3 = getParam("EDITtxtNoKPBaru3");

					int jSerah = 0;
					int jStatus = 0;

					jSerah = getParamAsInteger("EDITsorJenisSerah");
					jStatus = getParamAsInteger("EDITsorStatus");

					String daftarPos = getParam("EDITtxtNoDaftarPos");
					String id_penghantarnotisUpdate = getParam("EDITtxtNamaPenghantarNotis");
					String tarikhSerahan = getParam("EDITtxdTarikhSerahan");
					String namaPenerima = getParam("EDITtxtNamaPenerima");
					String catatan = getParam("EDITtxtCatatan");
					
					
					//aishahlatip
					String namaLain = "";
					if(id_penghantarnotisUpdate.equals("99999")){
						namaLain = getParam("lain2PeghantarNotis");
					}
					context.put("lain2PeghantarNotis",namaLain);


					String namax = "";
					String kodx = "";

					if (id_penghantarnotisUpdate != "") {

						// get data keputusan permohonan
						getPenghantarNotis = modelNotis
								.getDetailPenghantarNotis(id_penghantarnotisUpdate);
						if (getPenghantarNotis.size() != 0) {
							Hashtable x = (Hashtable) getPenghantarNotis.get(0);
							namax = x.get("nama").toString();
							kodx = x.get("kod_penghantar_notis").toString();
						}

						// selected penghantar ob
						getSelectedPenghantarNotisByJkptg = modelNotis
								.getSelectedPenghantarNotisByJkptg(
										idPejabatJKPTG,
										id_penghantarnotisUpdate);
						// and
						getSelectedPenghantarNotis = modelNotis
								.getSelectedPenghantarNotis(id_penghantarnotisUpdate);
						// check penghantar notis ade o xde dlm negeri
						modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
						penghantarNotisByJkptg = modelNotis
								.getPenghantarNotisByJkptg();

						if (idPejabatJKPTG != "") {
							if (penghantarNotisByJkptg.size() != 0) {
								context.put("penghantarNotisONCHANGE",
										getSelectedPenghantarNotisByJkptg);
							} else {
								context.put("penghantarNotisONCHANGE",
										getSelectedPenghantarNotis);
							}

						} else {
							context.put("penghantarNotisONCHANGE",
									getSelectedPenghantarNotis);
						}

						context.put("onchangeMyListDropdown", "yes");

					} else {

						context.put("onchangeMyListDropdown", "no");

						modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
						penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
						// and
						modelNotis.setPenghantarNotis();
						penghantarNotis = modelNotis.getPenghantarNotis();

						if (penghantarNotisByJkptg.size() != 0) {
							context.put("penghantarNotis",
									penghantarNotisByJkptg);
						} else {
							context.put("penghantarNotis", penghantarNotis);
						}

					}

					context.put("SnamaPntr", namax);
					context.put("SkodPntr", kodx);
					context.put("SidPntr", id_penghantarnotisUpdate);

					String namaPen = "";
					String xkp1 = "";
					String xkp2 = "";
					String xkp3 = "";
					String xkpbaru = "";
					String xkplama = "";
					String xkplain = "";

					namaOBnKP = modelNotis.getNameNKPByDropdown(dropdown,getParam("id_permohonansimati_atheader"));

					if (namaOBnKP.size() != 0) {
						Hashtable obkp = (Hashtable) namaOBnKP.get(0);
						namaPen = obkp.get("nama_ob").toString();
						xkp1 = obkp.get("no_kp_baru1").toString();
						xkp2 = obkp.get("no_kp_baru2").toString();
						xkp3 = obkp.get("no_kp_baru3").toString();
						xkpbaru = obkp.get("no_kp_baru").toString();
						xkplama = obkp.get("no_kp_lama").toString();
						xkplain = obkp.get("no_kp_lain").toString();
					}

					if (namaPen != "") {
						context.put("SnamaP", namaPen);
					} else {
						context.put("SnamaP", namaPenerima);
					}

					if (xkpbaru != "") {
						context.put("jeniskpx", "baru");
						context.put("Skp1", xkp1);
						context.put("Skp2", xkp2);
						context.put("Skp3", xkp3);
					} else if (xkplama != "") {
						context.put("jeniskpx", "lama");
						context.put("Skplama", xkplama);
					} else if (xkplain != "") {
						context.put("jeniskpx", "lain");
						context.put("Skplain", xkplain);
					} else {
						context.put("jeniskpx", "");
						context.put("Skp1", "");
						context.put("Skp2", "");
						context.put("Skp3", "");
						context.put("Skplama", "");
						context.put("Skplain", "");
					}

					// set data
					context.put("Sdftr", daftarPos);
					context.put("Starikh", tarikhSerahan);
					context.put("Scatatan", catatan);

					// radio button jenis serah
					if (jSerah == 1) {
						jenisSerah = "checked";
						jenisPos = "";
					} else if (jSerah == 2) {
						jenisSerah = "";
						jenisPos = "checked";
					} else {
						jenisSerah = "";
						jenisPos = "";
					}
					context.put("jenisSerah", jenisSerah);
					context.put("jenisPos", jenisPos);

					// radio button status serah
					if (jStatus == 1) {
						serah1 = "checked";
						serah2 = "";
						pos1 = "";
						pos2 = "";
					} else if (jStatus == 2) {
						serah1 = "";
						serah2 = "checked";
						pos1 = "";
						pos2 = "";
					} else if (jStatus == 3) {
						serah1 = "";
						serah2 = "";
						pos1 = "checked";
						pos2 = "";
					} else if (jStatus == 4) {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "checked";
					} else {
						serah1 = "";
						serah2 = "";
						pos1 = "";
						pos2 = "";
					}
					context.put("serah1", serah1);
					context.put("serah2", serah2);
					context.put("pos1", pos1);
					context.put("pos2", pos2);

					if (jStatus == 1 || jStatus == 2) {
						context.put("disabledStatus1", "");
						context.put("disabledStatus2", "");
						context.put("disabledStatus3", "disabled");
						context.put("disabledStatus4", "disabled");
						context.put("disabledTxtSurat", "disabled");
					} else if (jStatus == 3) {
						context.put("disabledStatus1", "disabled");
						context.put("disabledStatus2", "disabled");
						context.put("disabledStatus3", "");
						context.put("disabledStatus4", "");
						context.put("disabledTxtSurat", "");
					} else if (jStatus == 4) {
						context.put("disabledStatus1", "disabled");
						context.put("disabledStatus2", "disabled");
						context.put("disabledStatus3", "");
						context.put("disabledStatus4", "");
						context.put("disabledTxtSurat", "disabled");
					}

					// onchange validation
					context.put("onchangeMyList", "yes");

					// get list ob
					modelNotis.setListOBatas18Semua(id_permohonansimatiINT,
							idSimati, "17");
					listOBatas18 = modelNotis.getListOBatas18Semua();
					context.put("listOBatas18", listOBatas18);

				}// close onchangemyListUPDATE

			}// close kemaskini notis

			vm = screen1;

		}

		else if ("Cari".equals(submit)) {

			carianNotis(session);
			listdepan = modelNotis.getListCarianSek17();

			// data & size list carian
			context.put("listNotis", listdepan);
			context.put("list_size", listdepan.size());

			vm = screen3;

		}// close cari
		else if ("kembali".equals(submit)) {

			modelNotis.setListDefaultSek17(usid);
			listdepan = modelNotis.getListDefaultSek17();

			// data & size default list
			context.put("listNotis", listdepan);
			context.put("list_size", listdepan.size());

			vm = screen3;

		} else {

			modelNotis.setListDefaultSek17(usid);
			listdepan = modelNotis.getListDefaultSek17();

			// data & size default list
			context.put("listNotis", listdepan);
			context.put("list_size", listdepan.size());

			vm = screen3;

		}// close else
		
		System.out.println("vm FrmSenaraiNotisSek17 : "+vm);

		this.context.put("flagFromSenaraiPermohonanSek8",
				flagFromSenaraiPermohonanSek8);
		this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
		this.context.put("flagForView", flagForView);

		// -- 07122009
		this.context.put("flagFromKeputusanPermohonan",
				flagFromKeputusanPermohonan);
		this.context.put("idpermohonansimati", id_permohonansimati);

		// Template template = engine.getTemplate(vm);
		// return template;

		setupPage(session, action, listdepan);
System.out.println("vm syeksen 17==============="+vm);
		return vm;

	}// close template

	/*
	 * 
	 * * METHOD **
	 */

	// List carian
	private void carianNotis(HttpSession session) throws Exception {

		String noFail = getParam("txtNoFail");
		String namaPemohon = getParam("txtPemohon");
		String namaSimati = getParam("txtSimati");
		String icSimati = getParam("txtIcSimati");
		String JenisIc = getParam("jenisIc");

		String usid = "";
		usid = (String) session.getAttribute("_ekptg_user_id");

		modelNotis.setCarianFailSek17(noFail, namaPemohon, namaSimati,
				icSimati, JenisIc, usid);
	}

	// Simpan notis
	private void simpanNotis(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String id_permohonansimati = getParam("idpermohonansimati");
		String idSimati = getParam("id_simati");
		String id_permohonan = getParam("id_permohonan");
		String id_fail = getParam("id_fail");
		String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

		String id_keputusanpermohonan = getParam("id_keputusanpermohonan");

		String tarikh_bicara = getParam("txdTarikhBicara");
		String masa_bicara = getParam("txtMasaBicara");
		String tempat_bicara = getParam("socTempatBicara");
		String alamat_bicara1 = getParam("txtAlamatBicara1");
		String alamat_bicara2 = getParam("txtAlamatBicara2");
		String alamat_bicara3 = getParam("txtAlamatBicara3");
		String poskod = getParam("txtPoskod");
		String pegawai = getParam("socPegawai");
		String tarikh_notis = getParam("txdTarikhNotis");

		// -- 09122009
		String socJenisWaktu = getParam("socJenisWaktu");
		h.put("socJenisWaktu", socJenisWaktu);

		String jenispejabat = getParam("jenisPejabat");

		String negeri = "";

		if (!jenispejabat.equals("0")) {
			negeri = getParam("idnegeri");
		} else {
			negeri = getParam("socNegeri");
		}

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_suburusanstatusfail", id_suburusanstatusfail);
		h.put("id_keputusanpermohonan", id_keputusanpermohonan);
		h.put("tarikh_bicara", tarikh_bicara);
		h.put("masa_bicara", masa_bicara);
		h.put("tempat_bicara", tempat_bicara);
		h.put("alamat_bicara1", alamat_bicara1);
		h.put("alamat_bicara2", alamat_bicara2);
		h.put("alamat_bicara3", alamat_bicara3);
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		h.put("pegawai", pegawai);
		h.put("tarikh_notis", tarikh_notis);
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

		if (jenispejabat.equals("1")) {
			h.put("jenispejabat", "22");
		} else if (jenispejabat.equals("2")) {
			h.put("jenispejabat", "2");
		} else if (jenispejabat.equals("0")) {
			h.put("jenispejabat", "0");
		} else {
			h.put("jenispejabat", "22");
		}

		String id_perbicaraan = modelNotis.addNotisSek17(session, h);
		System.out.println("idPerbicaraan===="+id_perbicaraan);

		h.put("id_perbicaraan", id_perbicaraan);

		
		String[] idsOB = request.getParameterValues("idsOB");
		if(idsOB!=null){
			for (int i = 0; i < idsOB.length; i++) {
    			modelNotis.addListOb(h,idsOB[i]);
    		}
		}
		
		String[] idsPemiutang = request.getParameterValues("idsPemiutang");
		if(idsPemiutang!=null){
			for (int i = 0; i < idsPemiutang.length; i++) {
    			modelNotis.addListOb(h,idsPemiutang[i]);
    		}
		}
		
		
		//aishahlatip
				Vector listCanSendOB = new Vector();
				modelNotis.setSemuaOB4HantarNotis(id_permohonansimati, idSimati, "8", id_perbicaraan);
				listCanSendOB = modelNotis.getListCanSendOB();
				
				String usid = (String) session.getAttribute("_ekptg_user_id");

				Vector penghantarNotisByJkptg = new Vector();
				Vector penghantarNotis = new Vector();
				Vector list = new Vector();
				
				modelNotis.setListSemak(id_permohonan, usid);
				list = modelNotis.getListSemak();
				
				
				String id_OB = "";
				String nama_ob = "";
				String no_kp_baru = "";
				String no_kp_lama = "";
				String no_kp_lain = "";
		/*		
				if (listCanSendOB.size() != 0) {
					Hashtable ls = (Hashtable) listCanSendOB.get(0);
					id_OB = ls.get("id_ob_can").toString() ;
					nama_ob = ls.get("nama_ob_can").toString() ;
					no_kp_baru = ls.get("no_kp_baru").toString() ;
					no_kp_lama = ls.get("no_kp_lama").toString() ;
					no_kp_lain = ls.get("no_kp_lain").toString() ;
				
					
					h.put("id_OB", id_OB);
					h.put("nama_ob", nama_ob);
					h.put("no_kp_baru", no_kp_baru);
					h.put("no_kp_lama", no_kp_lama);
					h.put("no_kp_lain", no_kp_lain);
					*/

					ServletContext application=getServletContext(); 
					//System.out.println("id_OB====="+id_OB);
					String[] id_ob_hidden = request.getParameterValues("idob_hidden");
					System.out.println("radioJenisSerah.length"+id_ob_hidden.length);
					String pilihanradio = "";
					if(id_ob_hidden!=null){
						for (int i = 0; i < id_ob_hidden.length; i++) {
							
							
							//aishahlatip
							Vector listOBhidden = new Vector();
							modelNotis.setMaklumatOBhidden(id_permohonansimati, idSimati, "17", id_perbicaraan,id_ob_hidden[i]);
							listOBhidden = modelNotis.getListOBhidden();
							
							if (listOBhidden.size() != 0) {
								Hashtable hs = (Hashtable) listOBhidden.get(0);
								id_OB = hs.get("id_ob_can").toString() ;
								nama_ob = hs.get("nama_ob_can").toString() ;
								no_kp_baru = hs.get("no_kp_baru").toString() ;
								no_kp_lama = hs.get("no_kp_lama").toString() ;
								no_kp_lain = hs.get("no_kp_lain").toString() ;
								
								
								h.put("id_OB", id_OB);
								h.put("nama_ob", nama_ob);
								h.put("no_kp_baru", no_kp_baru);
								h.put("no_kp_lama", no_kp_lama);
								h.put("no_kp_lain", no_kp_lain);
							
							}
							
							System.out.println("id_ob_hidden[i]==="+id_ob_hidden[i]);
					
						pilihanradio = request.getParameter("radioJenisSerah"+id_ob_hidden[i]);
						System.out.println("pilihanradio==="+pilihanradio);
				
						if(pilihanradio!=null){
						if(pilihanradio.equals("1")){//serahan Tangan
							
							//dapatkan maklumat penghantar notis
							String idStatus = "";
							String idPejabatJKPTG = "";
							String id_penghantarnotis = "";
							String nama_penghantar_notis = "";

							if (list.size() != 0) {
								Hashtable ls2 = (Hashtable) list.get(0);
								
								idPejabatJKPTG = ls2.get("id_pejabatjkptg").toString();
							}

							modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
							penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
							// and
							modelNotis.setPenghantarNotis();
							penghantarNotis = modelNotis.getPenghantarNotis();
							
							if (penghantarNotisByJkptg.size() != 0) {
						
									Hashtable ls3 = (Hashtable) penghantarNotisByJkptg.get(0);
									
									id_penghantarnotis = ls3.get("id_penghantarnotis").toString();
									nama_penghantar_notis = ls3.get("nama").toString();
									
									h.put("nama_penghantar_notis", nama_penghantar_notis);
									h.put("id_penghantarnotis", id_penghantarnotis);
								
							} else {
								h.put("nama_penghantar_notis", nama_penghantar_notis);
								h.put("id_penghantarnotis", id_penghantarnotis);
							}
							//end
							
							
							
							
							h.put("jenis_serah", "1");
							h.put("status_serah", "1");
							h.put("nama_penghantar_notis", "");
							h.put("id_penghantarnotis", "");
							h.put("alamatEmel", "");//nanti buat
							h.put("NAMA_PENGHANTAR_LAIN", "");
							
						}else if(pilihanradio.equals("3")){//send email
							h.put("jenis_serah", "3");
							h.put("status_serah", "");
							h.put("nama_penghantar_notis", "");
							h.put("id_penghantarnotis", "99999");
							h.put("alamatEmel", "");//nanti buat
							h.put("NAMA_PENGHANTAR_LAIN", "EMEL");
							
							
						}else if( pilihanradio.equals("5")){//send PNMB
							
							h.put("jenis_serah", "5");
							h.put("status_serah", "");
							h.put("nama_penghantar_notis", "");
							h.put("id_penghantarnotis", "99999");
							h.put("alamatEmel", "");
							h.put("NAMA_PENGHANTAR_LAIN", "PNMB");
							
						}
						
						//Simpan Tblppknotisobmst
						
						h.put("id_masuk", usid);
						modelNotis.addHantarLaporanNotis_temp(h,id_ob_hidden[i],id_perbicaraan,session,application,request, response); //hide attachment
						//modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],idPerbicaraan);
						
						}
						/*else{
							h.put("jenis_serah", "");
							h.put("status_serah", "");
							h.put("nama_penghantar_notis", "");
							h.put("id_penghantarnotis", "");
							h.put("alamatEmel", "");
							h.put("NAMA_PENGHANTAR_LAIN", "");
							
							//Simpan Tblppknotisobmst
							ServletContext application=getServletContext(); 
							h.put("id_masuk", usid);
							modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],id_perbicaraan,session,application,request, response); //hide attachment
							//modelNotis.addHantarLaporanNotis_temp(h,id_ob_hidden[i],id_perbicaraan,session,application,request, response); //hide attachment
							//modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],idPerbicaraan);
						}*/
			    	
					}
					 
				}
			//mobile notification 06092017
			Push.genMsgPush(id_fail, "bicara");

	}// close addnotis
	
	
	// Simpan notis_temp
		private void simpanNotis_temp(HttpSession session) throws Exception {

			Hashtable h = new Hashtable();

			String id_permohonansimati = getParam("idpermohonansimati");
			String idSimati = getParam("id_simati");
			
			
			String id_permohonan = getParam("id_permohonan");
			String id_fail = getParam("id_fail");
			String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

			String id_keputusanpermohonan = getParam("id_keputusanpermohonan");

			String tarikh_bicara = getParam("txdTarikhBicara");
			String masa_bicara = getParam("txtMasaBicara");
			String tempat_bicara = getParam("socTempatBicara");
			String alamat_bicara1 = getParam("txtAlamatBicara1");
			String alamat_bicara2 = getParam("txtAlamatBicara2");
			String alamat_bicara3 = getParam("txtAlamatBicara3");
			String poskod = getParam("txtPoskod");
			String pegawai = getParam("socPegawai");
			String tarikh_notis = getParam("txdTarikhNotis");

			// -- 09122009
			String socJenisWaktu = getParam("socJenisWaktu");
			h.put("socJenisWaktu", socJenisWaktu);

			String jenispejabat = getParam("jenisPejabat");

			String negeri = "";

			if (!jenispejabat.equals("0")) {
				negeri = getParam("idnegeri");
			} else {
				negeri = getParam("socNegeri");
			}

			h.put("id_permohonan", id_permohonan);
			h.put("id_fail", id_fail);
			h.put("id_suburusanstatusfail", id_suburusanstatusfail);
			h.put("id_keputusanpermohonan", id_keputusanpermohonan);
			h.put("tarikh_bicara", tarikh_bicara);
			h.put("masa_bicara", masa_bicara);
			h.put("tempat_bicara", tempat_bicara);
			h.put("alamat_bicara1", alamat_bicara1);
			h.put("alamat_bicara2", alamat_bicara2);
			h.put("alamat_bicara3", alamat_bicara3);
			h.put("poskod", poskod);
			h.put("negeri", negeri);
			h.put("pegawai", pegawai);
			h.put("tarikh_notis", tarikh_notis);
			h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

			if (jenispejabat.equals("1")) {
				h.put("jenispejabat", "22");
			} else if (jenispejabat.equals("2")) {
				h.put("jenispejabat", "2");
			} else if (jenispejabat.equals("0")) {
				h.put("jenispejabat", "0");
			} else {
				h.put("jenispejabat", "22");
			}

			//ADD NOTIS BARU
			String idPerbicaraan = modelNotis.addNotis(session, h);
			System.out.println("idPerbicaraan===="+idPerbicaraan);
			
			String[] idsOB = request.getParameterValues("idsOB");
			
			System.out.println("idsOB.length===="+idsOB.length);
			if(idsOB!=null){
				for (int x = 0; x < idsOB.length; x++) {
	    			modelNotis.addListOb(h,idsOB[x]);
	    		}
			}
			
			String[] idsPemiutang = request.getParameterValues("idsPemiutang");
			if(idsPemiutang!=null){
				for (int i = 0; i < idsPemiutang.length; i++) {
	    			modelNotis.addListOb(h,idsPemiutang[i]);
	    		}
			}
			
		
			//aishahlatip
			Vector listCanSendOB = new Vector();
			modelNotis.setSemuaOB4HantarNotis(id_permohonansimati, idSimati, "8", idPerbicaraan);
			listCanSendOB = modelNotis.getListCanSendOB();
			
			String usid = (String) session.getAttribute("_ekptg_user_id");

			Vector penghantarNotisByJkptg = new Vector();
			Vector penghantarNotis = new Vector();
			Vector list = new Vector();
			
			modelNotis.setListSemak(id_permohonan, usid);
			list = modelNotis.getListSemak();
			
			
			String id_OB = "";
			String nama_ob = "";
			String no_kp_baru = "";
			String no_kp_lama = "";
			String no_kp_lain = "";
	/*		
			if (listCanSendOB.size() != 0) {
				Hashtable ls = (Hashtable) listCanSendOB.get(0);
				id_OB = ls.get("id_ob_can").toString() ;
				nama_ob = ls.get("nama_ob_can").toString() ;
				no_kp_baru = ls.get("no_kp_baru").toString() ;
				no_kp_lama = ls.get("no_kp_lama").toString() ;
				no_kp_lain = ls.get("no_kp_lain").toString() ;
			
				
				h.put("id_OB", id_OB);
				h.put("nama_ob", nama_ob);
				h.put("no_kp_baru", no_kp_baru);
				h.put("no_kp_lama", no_kp_lama);
				h.put("no_kp_lain", no_kp_lain);
				*/

				
				//System.out.println("id_OB====="+id_OB);
				String[] id_ob_hidden = request.getParameterValues("idob_hidden");
				System.out.println("radioJenisSerah.length"+id_ob_hidden.length);
				String pilihanradio = "";
				if(id_ob_hidden!=null){
					for (int i = 0; i < id_ob_hidden.length; i++) {
						
						
						//aishahlatip
						Vector listOBhidden = new Vector();
						modelNotis.setMaklumatOBhidden(id_permohonansimati, idSimati, "8", idPerbicaraan,id_ob_hidden[i]);
						listOBhidden = modelNotis.getListOBhidden();
						
						if (listOBhidden.size() != 0) {
							Hashtable hs = (Hashtable) listOBhidden.get(0);
							id_OB = hs.get("id_ob_can").toString() ;
							nama_ob = hs.get("nama_ob_can").toString() ;
							no_kp_baru = hs.get("no_kp_baru").toString() ;
							no_kp_lama = hs.get("no_kp_lama").toString() ;
							no_kp_lain = hs.get("no_kp_lain").toString() ;
							
							
							h.put("id_OB", id_OB);
							h.put("nama_ob", nama_ob);
							h.put("no_kp_baru", no_kp_baru);
							h.put("no_kp_lama", no_kp_lama);
							h.put("no_kp_lain", no_kp_lain);
						
						}
						
						System.out.println("id_ob_hidden[i]==="+id_ob_hidden[i]);
				
					pilihanradio = request.getParameter("radioJenisSerah"+id_ob_hidden[i]);
					System.out.println("pilihanradio==="+pilihanradio);
			
					if(pilihanradio!=null){
					if(pilihanradio.equals("1")){//serahan Tangan
						
						//dapatkan maklumat penghantar notis
						String idStatus = "";
						String idPejabatJKPTG = "";
						String id_penghantarnotis = "";
						String nama_penghantar_notis = "";

						if (list.size() != 0) {
							Hashtable ls2 = (Hashtable) list.get(0);
							
							idPejabatJKPTG = ls2.get("id_pejabatjkptg").toString();
						}

						modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
						penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
						// and
						modelNotis.setPenghantarNotis();
						penghantarNotis = modelNotis.getPenghantarNotis();
						
						if (penghantarNotisByJkptg.size() != 0) {
					
								Hashtable ls3 = (Hashtable) penghantarNotisByJkptg.get(0);
								
								id_penghantarnotis = ls3.get("id_penghantarnotis").toString();
								nama_penghantar_notis = ls3.get("nama").toString();
								
								h.put("nama_penghantar_notis", nama_penghantar_notis);
								h.put("id_penghantarnotis", id_penghantarnotis);
							
						} else {
							h.put("nama_penghantar_notis", nama_penghantar_notis);
							h.put("id_penghantarnotis", id_penghantarnotis);
						}
						//end
						
						
						
						
						h.put("jenis_serah", "1");
						h.put("status_serah", "1");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "");
						h.put("alamatEmel", "");//nanti buat
						h.put("NAMA_PENGHANTAR_LAIN", "");
						
					}else if(pilihanradio.equals("3")){//send email
						h.put("jenis_serah", "3");
						h.put("status_serah", "");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "99999");
						h.put("alamatEmel", "");//nanti buat
						h.put("NAMA_PENGHANTAR_LAIN", "EMEL");
						
						
					}else if( pilihanradio.equals("5")){//send PNMB
						
						h.put("jenis_serah", "5");
						h.put("status_serah", "");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "99999");
						h.put("alamatEmel", "");
						h.put("NAMA_PENGHANTAR_LAIN", "PNMB");
						
					}
					}else{
						h.put("jenis_serah", "");
						h.put("status_serah", "");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "");
						h.put("alamatEmel", "");
						h.put("NAMA_PENGHANTAR_LAIN", "");
					}
					
					//Simpan Tblppknotisobmst
					ServletContext application=getServletContext(); 
					h.put("id_masuk", usid);
					//modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],idPerbicaraan,session,application,request, response); //hide attachment
					modelNotis.addHantarLaporanNotisTemp(h,id_ob_hidden[i],idPerbicaraan,session,application,request, response); //hide attachment
					//modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],idPerbicaraan);
		    	
				}
				 
			}
				
				//mobile notification 06092017
				Push.genMsgPush(id_fail, "bicara");
				

}// close addnotis

	// Simpan notis tambah
	private void simpanNotisTambah(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String id_permohonansimati = getParam("idpermohonansimati");
		String idSimati = getParam("id_simati");
		
		String id_permohonan = getParam("id_permohonan");
		String id_fail = getParam("id_fail");
		String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

		String id_keputusanpermohonan = getParam("id_keputusanpermohonan");

		String bil_bicara = getParam("latestBil");
		String tarikh_bicara = getParam("txdTarikhBicara");
		String masa_bicara = getParam("txtMasaBicara");
		String tempat_bicara = getParam("socTempatBicara");
		String alamat_bicara1 = getParam("TGAlamatBicara1");
		String alamat_bicara2 = getParam("TGAlamatBicara2");
		String alamat_bicara3 = getParam("TGAlamatBicara3");
		String poskod = getParam("TGPoskod");
		// String negeri = getParam("idnegeri");
		String pegawai = getParam("socPegawai");
		String tarikh_notis = getParam("txdTarikhNotis");

		String jenispejabat = getParam("jenisPejabat");

		String negeri = "";

		if (!jenispejabat.equals("0")) {
			negeri = getParam("idnegeri");
		} else {
			negeri = getParam("socNegeri");
		}

		// -- 09122009
		String socJenisWaktu = getParam("socJenisWaktu");
		h.put("socJenisWaktu", socJenisWaktu);

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_suburusanstatusfail", id_suburusanstatusfail);
		h.put("id_keputusanpermohonan", id_keputusanpermohonan);

		h.put("bil_bicara", bil_bicara);
		h.put("tarikh_bicara", tarikh_bicara);
		h.put("masa_bicara", masa_bicara);

		// id unitpsk
		h.put("pegawai", pegawai);

		h.put("tempat_bicara", tempat_bicara);
		h.put("alamat_bicara1", alamat_bicara1);
		h.put("alamat_bicara2", alamat_bicara2);
		h.put("alamat_bicara3", alamat_bicara3);
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		h.put("tarikh_notis", tarikh_notis);
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

		if (jenispejabat.equals("1")) {
			h.put("jenispejabat", "22");
		} else if (jenispejabat.equals("2")) {
			h.put("jenispejabat", "2");
		} else if (jenispejabat.equals("0")) {
			h.put("jenispejabat", "0");
		} else {
			h.put("jenispejabat", "22");
		}

		long idperbicaraan = DB.getNextID("TBLPPKPERBICARAAN_SEQ");
		String id_perbicaraan = Long.toString(idperbicaraan);

		h.put("id_perbicaraan", id_perbicaraan);

		modelNotis.addNotisTambahSek17(session, h);
		
		String[] idsOB = request.getParameterValues("idsOB");
		if(idsOB!=null){
			for (int i = 0; i < idsOB.length; i++) {
    			modelNotis.addListOb(h,idsOB[i]);
    		}
		}
		
		String[] idsPemiutang = request.getParameterValues("idsPemiutang");
		if(idsPemiutang!=null){
			for (int i = 0; i < idsPemiutang.length; i++) {
    			modelNotis.addListOb(h,idsPemiutang[i]);
    		}
		}
		
		
		
		//aishahlatip
		Vector listCanSendOB = new Vector();
		modelNotis.setSemuaOB4HantarNotis(id_permohonansimati, idSimati, "8", id_perbicaraan);
		listCanSendOB = modelNotis.getListCanSendOB();
		
		
		String usid = (String) session.getAttribute("_ekptg_user_id");

		Vector penghantarNotisByJkptg = new Vector();
		Vector penghantarNotis = new Vector();
		Vector list = new Vector();
		
		modelNotis.setListSemak(id_permohonan, usid);
		list = modelNotis.getListSemak();
		
		String id_OB = "";
		String nama_ob = "";
		String no_kp_baru = "";
		String no_kp_lama = "";
		String no_kp_lain = "";
		
		
		 ServletContext application=getServletContext();  //email attachment
			
			String[] id_ob_hidden = request.getParameterValues("idob_hidden");
			System.out.println("radioJenisSerah.length"+id_ob_hidden.length);
			String pilihanradio = "";
			if(id_ob_hidden!=null){
				for (int i = 0; i < id_ob_hidden.length; i++) {
					
					
					//aishahlatip
					Vector listOBhidden = new Vector();
					modelNotis.setMaklumatOBhidden(id_permohonansimati, idSimati, "8", id_perbicaraan,id_ob_hidden[i]);
					listOBhidden = modelNotis.getListOBhidden();
					
					if (listOBhidden.size() != 0) {
						Hashtable hs = (Hashtable) listOBhidden.get(0);
						id_OB = hs.get("id_ob_can").toString() ;
						nama_ob = hs.get("nama_ob_can").toString() ;
						no_kp_baru = hs.get("no_kp_baru").toString() ;
						no_kp_lama = hs.get("no_kp_lama").toString() ;
						no_kp_lain = hs.get("no_kp_lain").toString() ;
						
						
						h.put("id_OB", id_OB);
						h.put("nama_ob", nama_ob);
						h.put("no_kp_baru", no_kp_baru);
						h.put("no_kp_lama", no_kp_lama);
						h.put("no_kp_lain", no_kp_lain);
					
					}
					
					System.out.println("id_ob_hidden[i]==="+id_ob_hidden[i]);
			
				pilihanradio = request.getParameter("radioJenisSerah"+id_ob_hidden[i]);
				System.out.println("pilihanradio==="+pilihanradio);
		
				if(pilihanradio!=null){
				if(pilihanradio.equals("1")){//serahan Tangan
					
					//dapatkan maklumat penghantar notis
					String idStatus = "";
					String idPejabatJKPTG = "";
					String id_penghantarnotis = "";
					String nama_penghantar_notis = "";

					if (list.size() != 0) {
						Hashtable ls2 = (Hashtable) list.get(0);
						
						idPejabatJKPTG = ls2.get("id_pejabatjkptg").toString();
					}

					modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
					penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
					// and
					modelNotis.setPenghantarNotis();
					penghantarNotis = modelNotis.getPenghantarNotis();
					
					if (penghantarNotisByJkptg.size() != 0) {
				
							Hashtable ls3 = (Hashtable) penghantarNotisByJkptg.get(0);
							
							id_penghantarnotis = ls3.get("id_penghantarnotis").toString();
							nama_penghantar_notis = ls3.get("nama").toString();
							
							h.put("nama_penghantar_notis", nama_penghantar_notis);
							h.put("id_penghantarnotis", id_penghantarnotis);
						
					} else {
						h.put("nama_penghantar_notis", nama_penghantar_notis);
						h.put("id_penghantarnotis", id_penghantarnotis);
					}
					//end
					
					h.put("jenis_serah", "1");
					h.put("status_serah", "1");
					h.put("nama_penghantar_notis", "");
					h.put("id_penghantarnotis", "");
					h.put("alamatEmel", "");//nanti buat
					h.put("NAMA_PENGHANTAR_LAIN", "");
					
				}else if(pilihanradio.equals("3")){//send email
					h.put("jenis_serah", "3");
					h.put("status_serah", "");
					h.put("nama_penghantar_notis", "");
					h.put("id_penghantarnotis", "99999");
					h.put("alamatEmel", "");//nanti buat
					h.put("NAMA_PENGHANTAR_LAIN", "EMEL");
					
					
				}else if( pilihanradio.equals("5")){//send PNMB
					
					h.put("jenis_serah", "5");
					h.put("status_serah", "");
					h.put("nama_penghantar_notis", "");
					h.put("id_penghantarnotis", "99999");
					h.put("alamatEmel", "");
					h.put("NAMA_PENGHANTAR_LAIN", "PNMB");
					
				}
				
				
				h.put("id_masuk", usid);
				modelNotis.addHantarLaporanNotisTemp(h,id_ob_hidden[i],id_perbicaraan,session,application,request, response); //hide attachment
				}
				/*}else{
					h.put("jenis_serah", "");
					h.put("status_serah", "");
					h.put("nama_penghantar_notis", "");
					h.put("id_penghantarnotis", "");
					h.put("alamatEmel", "");
					h.put("NAMA_PENGHANTAR_LAIN", "");
					
					
					ServletContext application=getServletContext(); 
					h.put("id_masuk", usid);
					modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],idPerbicaraan,session,application,request, response); //hide attachment
					
				}*/
				
				//Simpan Tblppknotisobmst
				
				//modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],idPerbicaraan,session,application,request, response); //hide attachment
	    	
			}
		}

	}// close addnotis tambah

	// Update notis
	private void updateNotis(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String id_status = getParam("id_status");

		String id_perbicaraan = getParam("id_perbicaraan");

		String id_keputusanpermohonan = getParam("id_keputusanpermohonan");

		// String bilBicara = getParam("editBilBicara");
		String tarikh_bicara = getParam("editTarikhBicara");
		String masa_bicara = getParam("editMasaBicara");
		String tempat_bicara = getParam("editTempatBicara");
		String alamat_bicara1 = getParam("editAlamatBicara1");
		String alamat_bicara2 = getParam("editAlamatBicara2");
		String alamat_bicara3 = getParam("editAlamatBicara3");
		String poskod = getParam("editPoskod");
		// String negeri = getParam("idnegeri");
		String pegawai = getParam("editPegawai");
		String tarikh_notis = getParam("editTarikhNotis");

		String jenispejabat = getParam("editjenisPejabat");
		String id_permohonan = getParam("id_permohonan");
		String idSimati = getParam("id_simati");
		String id_permohonansimati = getParam("idpermohonansimati");
		String id_fail = getParam("id_fail_atheader");
		System.out.println("::::::::::::::::idSimati:::::::::::::::::"+idSimati);
		String negeri = "";

		if (!jenispejabat.equals("0")) {
			negeri = getParam("idnegeri");
		} else {
			negeri = getParam("editNegeri");
		}

		h.put("id_perbicaraan", id_perbicaraan);
		h.put("id_keputusanpermohonan", id_keputusanpermohonan);

		// -- 09122009
		String socJenisWaktu = getParam("socJenisWaktu");
		h.put("socJenisWaktu", socJenisWaktu);

		// status tangguh
		h.put("id_status", id_status);

		h.put("tarikh_bicara", tarikh_bicara);
		h.put("masa_bicara", masa_bicara);
		h.put("tempat_bicara", tempat_bicara);
		h.put("alamat_bicara1", alamat_bicara1);
		h.put("alamat_bicara2", alamat_bicara2);
		h.put("alamat_bicara3", alamat_bicara3);
		h.put("poskod", poskod);
		h.put("negeri", negeri);
		h.put("pegawai", pegawai);
		h.put("tarikh_notis", tarikh_notis);
		h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

		if (jenispejabat.equals("1")) {
			h.put("jenispejabat", "22");
		} else if (jenispejabat.equals("2")) {
			h.put("jenispejabat", "2");
		} else if (jenispejabat.equals("0")) {
			h.put("jenispejabat", "0");
		} else {
			h.put("jenispejabat", "22");
		}

		
		System.out.println("xxxxxxxxxxxxxxxxx");
		modelNotis.updateNotis(session,h);
		
		// delect checkbox
		modelNotis.obSemakDelete(id_perbicaraan);	
		String[] idsOB = request.getParameterValues("idsOB");
		if(idsOB!=null){
			for (int i = 0; i < idsOB.length; i++) {
    			modelNotis.addListOb(h,idsOB[i]);
    		}
		}
		
		String[] idsPemiutang = request.getParameterValues("idsPemiutang");
		if(idsPemiutang!=null){
			for (int i = 0; i < idsPemiutang.length; i++) {
    			modelNotis.addListOb(h,idsPemiutang[i]);
    		}
		}
		
		
		
		//aishahlatip
		boolean statusHantarPNB = false;
		// validate status hantar PNB
		statusHantarPNB = modelNotis.getPNBValidation(id_perbicaraan);//tutup jap
		System.out.println("statusHantarPNB====="+statusHantarPNB);
		
				Vector listCanSendOB = new Vector();
				modelNotis.setSemuaOB4HantarNotis(id_permohonansimati, idSimati, "17", id_perbicaraan);
				listCanSendOB = modelNotis.getListCanSendOB();
				
				String usid = (String) session.getAttribute("_ekptg_user_id");

				Vector penghantarNotisByJkptg = new Vector();
				Vector penghantarNotis = new Vector();
				Vector list = new Vector();
				
				modelNotis.setListSemak(id_permohonan, usid);
				list = modelNotis.getListSemak();
				
				
				String id_OB = "";
				String nama_obs = "";
				String no_kp_baru = "";
				String no_kp_lama = "";
				String no_kp_lain = "";

					
				//System.out.println("id_OB====="+id_OB);
				String[] id_ob_hidden = request.getParameterValues("idob_hidden");
				System.out.println("id_ob_hidden.length"+id_ob_hidden.length);
				String pilihanradio = "";
				if(id_ob_hidden!=null){
					
					String[] id_obx = request.getParameterValues("idob_hidden");
					if(id_obx!=null){
						
						//System.out.println(" id_obx.length==="+ id_obx.length);
						for (int w = 0; w < id_obx.length; w++) {
						
						Vector<Hashtable<String,String>> ID_NOTISOBMST = modelNotis.getID_NOTISOBMST(id_obx[w],statusHantarPNB);
						myLogger.info("---ID_NOTISOBMST size :"+ID_NOTISOBMST.size());
						//clear rekod HA balik
						for(int x=0; x < ID_NOTISOBMST.size(); x++)
						{
								Hashtable<String,String> h2 = (Hashtable<String,String>) ID_NOTISOBMST.get(x);
												
								myLogger.info("---ID_NOTISOBMST :"+h2.get("ID_NOTISOBMST").toString());
								//delete all table involved
								if(pilihanradio!=null){
										modelNotis.deleteTBLPPKNOTISOBDTL(h2.get("ID_NOTISOBMST").toString(),id_perbicaraan);	//delete TBLPPKNOTISOBDTL	
										modelNotis.deleteTBLPPKNOTISPERBICARAAN(h2.get("ID_NOTISOBMST").toString(),id_perbicaraan);	//delete TBLPPKNOTISPERBICARAAN	
										modelNotis.deleteTBLPPKNOTISOBMST(h2.get("ID_NOTISOBMST").toString(),id_perbicaraan);	//delete TBLPPKNOTISOBMST	
								}
								
						 }
						
						}
					}
					
					
					for (int i = 0; i < id_ob_hidden.length; i++) {
				
						
						//aishahlatip
						Vector listOBhidden = new Vector();
						modelNotis.setMaklumatOBhidden(id_permohonansimati, idSimati, "8", id_perbicaraan,id_ob_hidden[i]);
						listOBhidden = modelNotis.getListOBhidden();
						
						if (listOBhidden.size() != 0) {
							Hashtable hs = (Hashtable) listOBhidden.get(0);
							id_OB = hs.get("id_ob_can").toString() ;
							nama_obs = hs.get("nama_ob_can").toString() ;
							no_kp_baru = hs.get("no_kp_baru").toString() ;
							no_kp_lama = hs.get("no_kp_lama").toString() ;
							no_kp_lain = hs.get("no_kp_lain").toString() ;
							
							
							h.put("id_OB", id_OB);
							h.put("nama_obs", nama_obs);
							h.put("no_kp_baru", no_kp_baru);
							h.put("no_kp_lama", no_kp_lama);
							h.put("no_kp_lain", no_kp_lain);
							System.out.println("nama_obs==="+nama_obs);
						}
				
					pilihanradio = request.getParameter("radioJenisSerah"+id_ob_hidden[i]);
					System.out.println("pilihanradio==="+pilihanradio);
					
					
					if(pilihanradio!=null){
					if(pilihanradio.equals("1")){//serahan Tangan
						
						//dapatkan maklumat penghantar notis
						String idStatus = "";
						String idPejabatJKPTG = "";
						String id_penghantarnotis = "";
						String nama_penghantar_notis = "";

						if (list.size() != 0) {
							Hashtable ls2 = (Hashtable) list.get(0);
							
							idPejabatJKPTG = ls2.get("id_pejabatjkptg").toString();
						}

						modelNotis.setPenghantarNotisByJkptg(idPejabatJKPTG);
						penghantarNotisByJkptg = modelNotis.getPenghantarNotisByJkptg();
						// and
						modelNotis.setPenghantarNotis();
						penghantarNotis = modelNotis.getPenghantarNotis();
						
						if (penghantarNotisByJkptg.size() != 0) {
					
								Hashtable ls3 = (Hashtable) penghantarNotisByJkptg.get(0);
								
								id_penghantarnotis = ls3.get("id_penghantarnotis").toString();
								nama_penghantar_notis = ls3.get("nama").toString();
								
								System.out.println("id_penghantarnotis==="+id_penghantarnotis);
								System.out.println("nama_penghantar_notis==="+nama_penghantar_notis);
							
								h.put("nama_penghantar_notis", nama_penghantar_notis);
								h.put("id_penghantarnotis", id_penghantarnotis);
							
						} else {
							h.put("nama_penghantar_notis", nama_penghantar_notis);
							h.put("id_penghantarnotis", id_penghantarnotis);
						}
						//end
						
						/*h.put("jenis_serah", "1");
						h.put("status_serah", "1");
						h.put("alamatEmel", "");
						h.put("NAMA_PENGHANTAR_LAIN", "");*/
						
						h.put("jenis_serah", "1");
						h.put("status_serah", "1");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "");
						h.put("alamatEmel", "");//nanti buat
						h.put("NAMA_PENGHANTAR_LAIN", "");
						
					}else if(pilihanradio.equals("3")){//send email
						h.put("jenis_serah", "3");
						h.put("status_serah", "");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "99999");
						h.put("alamatEmel", "");//nanti buat
						h.put("NAMA_PENGHANTAR_LAIN", "EMEL");
						
						
						
						
					}else if( pilihanradio.equals("5")){//send PNMB
						
						h.put("jenis_serah", "5");
						h.put("status_serah", "");
						h.put("nama_penghantar_notis", "");
						h.put("id_penghantarnotis", "99999");
						h.put("alamatEmel", "");
						h.put("NAMA_PENGHANTAR_LAIN", "PNMB");
						
						
					}
					
					h.put("id_masuk", usid);
					//Simpan Tblppknotisobmst
					//utk ada attachment dlm email -- En man
					ServletContext application=getServletContext(); //hide attachment
					//modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],id_perbicaraan);
						if(statusHantarPNB){
							/*System.out.println("addHantarLaporanNotis");
							modelNotis.addHantarLaporanNotis(h,id_ob_hidden[i],id_perbicaraan,session,application,request, response);//hide attachment
							 */						
						}else{
							System.out.println("addHantarLaporanNotisTemp");
							modelNotis.addHantarLaporanNotisTemp(h,id_ob_hidden[i],id_perbicaraan,session,application,request, response);//hide attachment
						}
						
					}
					}//tutup for
				}
					
				//mobile notification 06092017
				Push.genMsgPush(id_fail, "bicara");

	}// close updatenotis

	// Simpan penjaga
	private void addPenjaga(HttpSession session) throws Exception {

		Hashtable i = new Hashtable();

		String vadPenjaga = getParam("sorPenjaga");
		int sorPenjaga = getParamAsInteger("sorPenjaga");
		String listPenjaga_size = getParam("listPenjaga_size");

		String id_obminor = getParam("id_ob");

		if (sorPenjaga == 0) {

			String id_ob = getParam("socPenjagaOB");

			String a = getParam("btxtNoKPBaru1");
			String b = getParam("btxtNoKPBaru2");
			String c = getParam("btxtNoKPBaru3");

			String txtNoKPBaru = a + b + c;

			String txtNoKPLama = getParam("btxtNoKPLama");
			String socJenisKp = getParam("bsocJenisKp");
			String txtJenisKP = getParam("btxtJenisKP");
			String txtNamaPenjaga = getParam("btxtNamaPenjaga");
			String socJantina = getParam("bsocJantina");
			String socAgama = getParam("bsocAgama");
			String socWarganegara = getParam("bsocWarganegara");
			String txtUmur = getParam("btxtUmur");
			String txtalamat1 = getParam("btxtalamat1");
			String txtalamat2 = getParam("btxtalamat2");
			String txtalamat3 = getParam("btxtalamat3");
			String txtbandar = getParam("btxtbandar");
			String txtposkod = getParam("btxtposkod");
			String socNegeri = getParam("bsocNegeri");
			String txtcatatan = "";

			i.put("id_ob", id_ob);
			i.put("txtNoKPBaru", txtNoKPBaru);
			i.put("txtNoKPLama", txtNoKPLama);
			i.put("socJenisKp", socJenisKp);
			i.put("txtJenisKP", txtJenisKP);
			i.put("txtNamaPenjaga", txtNamaPenjaga);
			i.put("socJantina", socJantina);
			i.put("socAgama", socAgama);
			i.put("socWarganegara", socWarganegara);
			i.put("txtUmur", txtUmur);
			i.put("txtalamat1", txtalamat1);
			i.put("txtalamat2", txtalamat2);
			i.put("txtalamat3", txtalamat3);
			i.put("txtbandar", txtbandar);
			i.put("txtposkod", txtposkod);
			i.put("socNegeri", socNegeri);
			i.put("txtcatatan", txtcatatan);
			i.put("id_pegawai", session.getAttribute("_ekptg_user_id"));

		} else if (sorPenjaga == 1) {

			String a = getParam("txtNoKPBaru1");
			String b = getParam("txtNoKPBaru2");
			String c = getParam("txtNoKPBaru3");

			String txtNoKPBaru = a + b + c;

			String txtNoKPLama = getParam("txtNoKPLama");
			String socJenisKp = getParam("socJenisKp");
			String txtJenisKP = getParam("txtJenisKP");
			String txtNamaPenjaga = getParam("txtNamaPenjaga");
			String socJantina = getParam("socJantina");
			String socAgama = getParam("socAgama");
			String socWarganegara = getParam("socWarganegara");
			String txtUmur = getParam("txtUmur");
			String txtalamat1 = getParam("txtalamat1");
			String txtalamat2 = getParam("txtalamat2");
			String txtalamat3 = getParam("txtalamat3");
			String txtbandar = getParam("txtbandar");
			String txtposkod = getParam("txtposkod");
			String socNegeri = getParam("socNegeri");
			String txtcatatan = "";
			txtcatatan = getParam("EDITtxtcatatan");

			String no = "0";
			i.put("id_ob", no);
			i.put("txtNoKPBaru", txtNoKPBaru);
			i.put("txtNoKPLama", txtNoKPLama);
			i.put("socJenisKp", socJenisKp);
			i.put("txtJenisKP", txtJenisKP);
			i.put("txtNamaPenjaga", txtNamaPenjaga);
			i.put("socJantina", socJantina);
			i.put("socAgama", socAgama);
			i.put("socWarganegara", socWarganegara);
			i.put("txtUmur", txtUmur);
			i.put("txtalamat1", txtalamat1);
			i.put("txtalamat2", txtalamat2);
			i.put("txtalamat3", txtalamat3);
			i.put("txtbandar", txtbandar);
			i.put("txtposkod", txtposkod);
			i.put("socNegeri", socNegeri);
			i.put("txtcatatan", txtcatatan);
			i.put("id_pegawai", session.getAttribute("_ekptg_user_id"));

		}

		i.put("id_obminor", id_obminor);
		i.put("vadPenjaga", vadPenjaga);
		i.put("listPenjaga_size", listPenjaga_size);

		modelNotis.addPenjaga(i);

	}// close add penjaga

	// Update penjaga
	private void updateDataPenjaga(HttpSession session) throws Exception {

		Hashtable i = new Hashtable();

		String id_penjaga = getParam("id_penjaga");

		String a = getParam("EDITtxtNoKPBaru1");
		String b = getParam("EDITtxtNoKPBaru2");
		String c = getParam("EDITtxtNoKPBaru3");

		String txtNoKPBaru = a + b + c;

		String txtNoKPLama = getParam("EDITtxtNoKPLama");
		String socJenisKp = getParam("EDITsocJenisKp");
		String txtJenisKP = getParam("EDITtxtJenisKP");
		String txtNamaPenjaga = getParam("EDITtxtNamaPenjaga");
		String socJantina = getParam("EDITsocJantina");
		String socAgama = getParam("EDITsocAgama");
		String socWarganegara = getParam("EDITsocWarganegara");
		String txtUmur = getParam("EDITtxtUmur");
		String txtalamat1 = getParam("EDITtxtalamat1");
		String txtalamat2 = getParam("EDITtxtalamat2");
		String txtalamat3 = getParam("EDITtxtalamat3");
		String txtbandar = getParam("EDITtxtbandar");
		String txtposkod = getParam("EDITtxtposkod");
		String socNegeri = getParam("EDITsocNegeri");
		String txtcatatan = getParam("EDITtxtcatatan");

		i.put("id_penjaga", id_penjaga);
		i.put("txtNoKPBaru", txtNoKPBaru);

		i.put("txtNoKPLama", txtNoKPLama);
		i.put("socJenisKp", socJenisKp);
		i.put("txtJenisKP", txtJenisKP);
		i.put("txtNamaPenjaga", txtNamaPenjaga);
		i.put("socJantina", socJantina);
		i.put("socAgama", socAgama);
		i.put("socWarganegara", socWarganegara);
		i.put("txtUmur", txtUmur);
		i.put("txtalamat1", txtalamat1);
		i.put("txtalamat2", txtalamat2);
		i.put("txtalamat3", txtalamat3);
		i.put("txtbandar", txtbandar);
		i.put("txtposkod", txtposkod);
		i.put("socNegeri", socNegeri);
		i.put("txtcatatan", txtcatatan);
		i.put("id_pegawai", session.getAttribute("_ekptg_user_id"));

		modelNotis.updateDataPenjaga(i);

	}// close update data penjaga

	// Simpan laporan
	private void simpanLaporan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String idkp = getParam("id_keputusanpermohonan");

		String jeniskp = "";

		jeniskp = getParam("jeniskp");
		String a, b, c = "";
		String kplama = "";
		String kplain = "";
		String kpbaru = "";

		a = getParam("txtNoKPBaru1");
		b = getParam("txtNoKPBaru2");
		c = getParam("txtNoKPBaru3");
		kplama = getParam("txtNoKPLama");
		kplain = getParam("txtNoKPLain");

		String jenis_serah = getParam("sorJenisSerah");
		String jenis_status = getParam("sorStatus");

		String daftar_pos = "";
		
		String daftar_pos1 = getParam("txtNoDaftarPos");
		String daftar_pos_2 = getParam("txtNoDaftarPos_2");//aishahlatip
		
		if(jenis_serah.equals("2")){
			daftar_pos = daftar_pos1;
		}else if(jenis_serah.equals("4")){
			daftar_pos = daftar_pos_2;
		}
		// String nama_penghantar = getParam("txtNamaPenghantarNotis");
		String tarikh_serahan = getParam("txdTarikhSerahan");
		String nama_penerima = getParam("txtNamaPenerima");
		String catatan = getParam("txtCatatan");
		
		String tarikhHantar = getParam("txdTarikhHantar");
		String alamatEmel = getParam("txtAlamatEmel");

		String id_penghantarnotis = getParam("txtNamaPenghantarNotis");
		String nama = "";
		Vector getPenghantarNotis = modelNotis
				.getDetailPenghantarNotis(id_penghantarnotis);
		if (getPenghantarNotis.size() != 0) {
			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
			nama = x.get("nama").toString();
		}

		if (jeniskp.equals("baru")) {
			kpbaru = a + b + c;
		} else if (jeniskp.equals("lama")) {
			kplama = kplama;
		} else if (jeniskp.equals("lain")) {
			kplain = kplain;
		} else {
			kpbaru = a + b + c;
		}

		h.put("noKP", kpbaru);
		h.put("kplama", kplama);
		h.put("kplain", kplain);

		h.put("nama_penghantar", nama);
		h.put("id_penghantar", id_penghantarnotis);

		h.put("idkp", idkp);
		h.put("jenis_serah", jenis_serah);
		h.put("jenis_status", jenis_status);
		h.put("daftar_pos", daftar_pos);
		
		h.put("tarikh_serahan", tarikh_serahan);
		h.put("nama_penerima", nama_penerima);
		h.put("catatan", catatan);
		h.put("tarikhHantar", tarikhHantar);//wp
		h.put("alamatEmel", alamatEmel);//wp
		
		String namaLain = "";
		//aishahlatip
		if(id_penghantarnotis.equals("99999")){
			namaLain = getParam("lain2PeghantarNotis");
		}
		h.put("namaLain", namaLain);
		
		h.put("id_pegawai", session.getAttribute("_ekptg_user_id"));

		String[] cbsemaks = request.getParameterValues("cbsemaks");
		
		List listEmailAdd = new ArrayList<Object>();//code wp
		List listIdObToSendMel = new ArrayList<Object>();//code wp

		for (int i = 0; i < cbsemaks.length; i++) {

			modelNotis.addLaporan(h, cbsemaks[i]);
			//code wp
			// jenis serahan by email = 3
			if ("3".equals(jenis_serah)) {
				listIdObToSendMel.add(cbsemaks[i]);
			}
		}
		
		//code wp
		// jenis serahan by email = 3
						if ("3".equals(jenis_serah)) {
							
							listEmailAdd.add(alamatEmel);
							String finalSqlOB = "";
							
							//susun all idOB utk dapatkan senarai email OB
							for (int j = 0; j < listIdObToSendMel.size(); j++) {
								String idOB = (String)listIdObToSendMel.get(j);
								if(j==0){
									finalSqlOB = idOB;
								}else{
									finalSqlOB = finalSqlOB +","+idOB;
								}
							}
							//get all address email OB from table OB
							if(finalSqlOB.length()>0){
								listEmailAdd = 	modelNotis.getListEmailAddress(listEmailAdd,finalSqlOB);
							}
							
							String idPermohonan = getParam("id_permohonan");

							if (idPermohonan != null && idPermohonan.length() > 0) {

								String idFail = "";
								// get info pemohonan
								Vector listPermohonan = modelNotis.getIdFail(idPermohonan);

								if (listPermohonan.size() != 0) {
									Hashtable ls = (Hashtable) listPermohonan.get(0);
									idFail = ls.get("idFail").toString();

									// send email
									String status = modelNotis.hantarEmail(idFail, listEmailAdd);

									// update column alamat email dan tarikh hantar
									if ("successEmail".equals(status)) {
										// handle message success
									}
								}
							}
						}

	}// close simpan laporan

	// Simpan laporan tambahan (generate id obdtl lain)
	private void simpanTambahanLaporan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String idkp = getParam("id_keputusanpermohonan");

		String jeniskp = "";

		jeniskp = getParam("jeniskp");
		String a, b, c = "";
		String kplama = "";
		String kplain = "";
		String kpbaru = "";

		a = getParam("txtNoKPBaru1");
		b = getParam("txtNoKPBaru2");
		c = getParam("txtNoKPBaru3");
		kplama = getParam("txtNoKPLama");
		kplain = getParam("txtNoKPLain");

		String jenis_serah = getParam("sorJenisSerah");
		String jenis_status = getParam("sorStatus");
		
		String daftar_pos = "";
		
		String daftar_pos1 = getParam("txtNoDaftarPos");
		String daftar_pos_2 = getParam("txtNoDaftarPos_2");//aishahlatip
		
		if(jenis_serah.equals("2")){
			daftar_pos = daftar_pos1;
		}else if(jenis_serah.equals("4")){
			daftar_pos = daftar_pos_2;
		}
		
		// String nama_penghantar = getParam("txtNamaPenghantarNotis");
		String tarikh_serahan = getParam("txdTarikhSerahan");
		String nama_penerima = getParam("txtNamaPenerima");
		String catatan = getParam("txtCatatan");
		
		String tarikhHantar = getParam("txdTarikhHantar");
		String alamatEmel = getParam("txtAlamatEmel");

		String id_penghantarnotis = getParam("txtNamaPenghantarNotis");
		String nama = "";
		Vector getPenghantarNotis = modelNotis
				.getDetailPenghantarNotis(id_penghantarnotis);
		if (getPenghantarNotis.size() != 0) {
			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
			nama = x.get("nama").toString();
		}

		if (jeniskp.equals("baru")) {
			kpbaru = a + b + c;
		} else if (jeniskp.equals("lama")) {
			kplama = kplama;
		} else if (jeniskp.equals("lain")) {
			kplain = kplain;
		} else {
			kpbaru = a + b + c;
		}

		h.put("nama_penghantar", nama);
		h.put("id_penghantar", id_penghantarnotis);

		h.put("noKP", kpbaru);
		h.put("kplama", kplama);
		h.put("kplain", kplain);

		h.put("idkp", idkp);
		h.put("jenis_serah", jenis_serah);
		h.put("jenis_status", jenis_status);
		h.put("daftar_pos", daftar_pos);
		// h.put("nama_penghantar",nama_penghantar);
		
		h.put("tarikh_serahan", tarikh_serahan);
		h.put("nama_penerima", nama_penerima);
		h.put("catatan", catatan);
		h.put("tarikhHantar", tarikhHantar);//wp
		h.put("alamatEmel", alamatEmel);//wp
		h.put("id_pegawai", session.getAttribute("_ekptg_user_id"));
		
		myLogger.info("[jenis_serah] simpanTambahanLaporan ::: " +jenis_serah);

		String[] cbsemaks = request.getParameterValues("cbsemaks");
		
		List listEmailAdd = new ArrayList<Object>();//code wp
		List listIdObToSendMel = new ArrayList<Object>();//code wp


		for (int i = 0; i < cbsemaks.length; i++) {
			modelNotis.addLaporanTambahan(h, cbsemaks[i]);
			//code wp
			// jenis serahan by email = 3
			if ("3".equals(jenis_serah)) {
				listIdObToSendMel.add(cbsemaks[i]);
			}
		}
		
		
		//code wp
				// jenis serahan by email = 3
						if ("3".equals(jenis_serah)) {
							
							listEmailAdd.add(alamatEmel);
							String finalSqlOB = "";
							
							//susun all idOB utk dapatkan senarai email OB
							for (int j = 0; j < listIdObToSendMel.size(); j++) {
								String idOB = (String)listIdObToSendMel.get(j);
								if(j==0){
									finalSqlOB = idOB;
								}else{
									finalSqlOB = finalSqlOB +","+idOB;
								}
							}
							//get all address email OB from table OB
							if(finalSqlOB.length()>0){
								listEmailAdd = 	modelNotis.getListEmailAddress(listEmailAdd,finalSqlOB);
							}
							
							String idPermohonan = getParam("id_permohonan");

							if (idPermohonan != null && idPermohonan.length() > 0) {

								String idFail = "";
								// get info pemohonan
								Vector listPermohonan = modelNotis.getIdFail(idPermohonan);

								if (listPermohonan.size() != 0) {
									Hashtable ls = (Hashtable) listPermohonan.get(0);
									idFail = ls.get("idFail").toString();

									// send email
									String status = modelNotis.hantarEmail(idFail, listEmailAdd);

									// update column alamat email dan tarikh hantar
									if ("successEmail".equals(status)) {
										// handle message success
									}
								}
							}
						}

	}// close simpan laporan tambahan

	// Update laporan
	private void updateLaporan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String idOBDTL = getParam("id_semaknotisobdtl");

		String jeniskp = "";

		jeniskp = getParam("jeniskpx");
		String a, b, c = "";
		String kplama = "";
		String kplain = "";
		String kpbaru = "";

		a = getParam("EDITtxtNoKPBaru1");
		b = getParam("EDITtxtNoKPBaru2");
		c = getParam("EDITtxtNoKPBaru3");
		kplama = getParam("EDITtxtNoKPLama");
		kplain = getParam("EDITtxtNoKPLain");

		if (jeniskp.equals("baru")) {
			kpbaru = a + b + c;
		} else if (jeniskp.equals("lama")) {
			kplama = kplama;
		} else if (jeniskp.equals("lain")) {
			kplain = kplain;
		} else {
			kpbaru = a + b + c;
		}

		h.put("noKP", kpbaru);
		h.put("kplama", kplama);
		h.put("kplain", kplain);

		String jenis_serah = getParam("EDITsorJenisSerah");
		String jenis_status = getParam("EDITsorStatus");
		String daftar_pos = getParam("EDITtxtNoDaftarPos");
		// String nama_penghantar = getParam("EDITtxtNamaPenghantarNotis");
		String tarikh_serahan = getParam("EDITtxdTarikhSerahan");
		String nama_penerima = getParam("EDITtxtNamaPenerima");
		String catatan = getParam("EDITtxtCatatan");

		String id_penghantarnotis = getParam("EDITtxtNamaPenghantarNotis");
		
		
		
		System.out.println("jenis_serah == "+jenis_serah);
		System.out.println("jenis_status == "+jenis_status);
		System.out.println("daftar_pos == "+daftar_pos);
		System.out.println("tarikh_serahan == "+tarikh_serahan);
		
		String daftar_pos_2 = getParam("EDITtxtNoDaftarPos_2");
		System.out.println("daftar_pos_2 == "+daftar_pos_2);
		
		if(jenis_serah.equals("2")){
			daftar_pos = daftar_pos;
		}else if(jenis_serah.equals("4")){
			daftar_pos = daftar_pos_2;
		}
		
		
		
		String nama = "";
		Vector getPenghantarNotis = modelNotis.getDetailPenghantarNotis(id_penghantarnotis);
		if (getPenghantarNotis.size() != 0) {
			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
			nama = x.get("nama").toString();
		}

		h.put("nama_penghantar", nama);
		h.put("id_penghantar", id_penghantarnotis);
		
		String namaLain = "";
		//aishahlatip
		if(id_penghantarnotis.equals("99999")){
			namaLain = getParam("lain2PeghantarNotis");
		}
		h.put("namaLain", namaLain);

		h.put("idOBDTL", idOBDTL);
		h.put("jenis_serah", jenis_serah);
		h.put("jenis_status", jenis_status);
		h.put("daftar_pos", daftar_pos);
		h.put("tarikh_serahan", tarikh_serahan);
		h.put("nama_penerima", nama_penerima);
		h.put("catatan", catatan);
		h.put("id_pegawai", session.getAttribute("_ekptg_user_id"));

		modelNotis.updateLaporan(h);

	}// close update laporan

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
			this.context.put("listNotis", paging.getPage(page));
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

	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail)
			throws Exception {
		// hashtable maklumat header
		this.context.put("headerppk", mainheader.getHeaderData(session,
				id_permohonan, flag_permohonan, id_fail, flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
				id_fail, flag_fail);
		this.context.put("list_sub_header", list_sub);
		this.context.put("flag_jenis_vm", "ajax");
	}

	private void headerppk_baru_default() {
		Hashtable headerppk = null;
		this.context.put("headerppk", "");
		this.context.put("list_sub_header", "");
		this.context.put("flag_jenis_vm", "ajax");
	}

}// close class
