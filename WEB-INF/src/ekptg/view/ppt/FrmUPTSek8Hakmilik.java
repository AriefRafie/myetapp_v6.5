package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8SiasatanData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.ws.identity.PPTSek8MobManager;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */
//import javax.swing.JOptionPane;

public class FrmUPTSek8Hakmilik extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmUPTSek8Hakmilik.class);

	// model
	FrmSek8SiasatanData logic = new FrmSek8SiasatanData();
	FrmUPTSek8HakmilikData model = new FrmUPTSek8HakmilikData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	PPTSek8MobManager myIdentity = new PPTSek8MobManager(); 

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = request.getSession();

		// command for paging
		String action = getParam("action");

		// get user login detail
		// String id_user = (String) session.getAttribute("_ekptg_user_id");
		String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
	

		String vm = "";
		String noLOT = "";
		String namaPB = "";
		String noSerah = "";
		String idExistPB = "";
		String idpegawai = "";

		Vector listPageDepan = new Vector();
		Vector listMaklumatTanahAcceptPenarikan = new Vector();
		Vector dataPermohonan = new Vector();
		Vector dataMaklumatTanah = new Vector();
		Vector dataDetailPB = new Vector();
		Vector dataBebanan = new Vector();
		Vector checkExistPBidHM = new Vector();
		Vector listSeqSubjaket = new Vector();
		Vector checkSizeBahagianPB = new Vector();
		Vector getIdSuburusanstatusfail = new Vector();
		Vector listMaklumatTanah = new Vector();
		//ArrayList id_penerima_array =

		getIdSuburusanstatusfail.clear();
		checkSizeBahagianPB.clear();
		listSeqSubjaket.clear();
		checkExistPBidHM.clear();
		dataBebanan.clear();
		dataDetailPB.clear();
		dataMaklumatTanah.clear();
		listPageDepan.clear();
		listMaklumatTanahAcceptPenarikan.clear();
		dataPermohonan.clear();
		listMaklumatTanah.clear();

		// screen jsp
		String listdepan = "app/ppt/HMdanPB/frmUPTSek8HMSenarai.jsp";
		String screenUtama = "app/ppt/HMdanPB/frmUPTSek8HakmilikList.jsp";
		String screenHakmilik = "app/ppt/HMdanPB/frmUPTSek8Hakmilik.jsp";
		String screenPB = "app/ppt/HMdanPB/frmUPTSek8PB.jsp";
		String screenBebanan = "app/ppt/HMdanPB/frmUPTSek8Bebanan.jsp";

		// lebah method
		context.put("Util", new lebah.util.Util());
		context.put("Utils", new ekptg.helpers.Utils());

		// default list
		//listPageDepan = model.getListPermohonan(userIdNeg);

		// prevent duplicate when refresh page
		String doPost = (String) session.getAttribute("doPost");

		// anchor
		String ScreenLocation = getParam("ScreenLocation");
		String CursorPoint = getParam("CursorPoint");
		context.put("ScreenLocation", ScreenLocation);
		context.put("CursorPoint", CursorPoint);

		String location = getParam("location");
		String point = getParam("point");
		this.context.put("location", location);
		this.context.put("point", point);

		// tabbed
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}
		//komen sementara
		// get nama pengarah dan id pengarah
		/*nameAndId(userIdNeg);
		String id_pengarah = nameAndId(userIdNeg);
		*/
		
		// paging
		/*
		String flagPaging = getParam("paging");
		if (flagPaging != "") {
			context.put("paging", getParam("paging"));
		} else {
			context.put("paging", "2");
		}
		*/
		context.put("paging", "2");

		// header
		String nama_kementerian = "";
		String tarikh_permohonan = "";
		String tujuan = "";
		String id_projekNegeri = "";
		String id_projekDaerah = "";
		String id_status = "";
		String flag_subjaket = "";
		String flagSegera = "";
		String id_fail = "";
		String no_fail = "";
				
		Vector dataHeader = null;
		String idpermohonan = getParam("id_permohonan");
		if(!idpermohonan.equals("")){
			header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if (dataHeader.size() != 0) {
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_projekNegeri = dh.get("id_projekNegeri").toString();
				id_projekDaerah = dh.get("id_projekDaerah").toString();
				id_status = dh.get("id_status").toString();
				flag_subjaket = dh.get("flag_subjaket").toString();
				flagSegera = dh.get("flag_segera").toString();
				id_fail = dh.get("id_fail").toString();
				no_fail = (String) dh.get("no_fail");
				tujuan = (String) dh.get("tujuan");
				tarikh_permohonan = (String) dh.get("tarikh_permohonan");
				nama_kementerian = (String) dh.get("nama_kementerian");
	
				Vector list_sub = null;
				list_sub = header.listPerjalananFail(idpermohonan);
				this.context.put("list_sub_header", list_sub);
	
			}
		
		}
		context.put("negeriIntegrasi",id_projekNegeri);

		//razman komen dlu, nampak tiada keperluan
		//paparData(getParam("id_fail"));
		
		
		context.put("flagSegera", flagSegera);

		// get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		if(!idpermohonan.equals(""))
		{
			modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
			getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
			if (getIdSuburusanstatusfail.size() != 0) {
				Hashtable idsb = (Hashtable) getIdSuburusanstatusfail.get(0);
				id_suburusanstatusfailppt = (String) idsb
						.get("id_suburusanstatusfailppt");
			}
		}
		
		/*
		modelUPT.setListMaklumatTanahSeksyen8(idpermohonan, "", "");
		listMaklumatTanah = modelUPT.getListMaklumatTanah();
		context.put("total_tanah", listMaklumatTanah.size());
		*/
		
		/*
		Db dbx = null;
		try {
			dbx = new Db();
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupHakmilik", dbx) == 0) { // reg
																				// class
				insertPopupReg("ekptg.view.ppt.SkrinPopupHakmilik",
						"Skrin Salin Hakmilik", "EKPTG - PPT", dbx);
			}
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik",
						"Skrin Capaian Hakmilik", "EKPTG - PPT", dbx);
			}
			if (checkRegPopup(
					"ekptg.view.ppt.SkrinPopupCarianHakmilikJanaSubjaket", dbx) == 0) {
				// reg class
				insertPopupReg(
						"ekptg.view.ppt.SkrinPopupCarianHakmilikJanaSubjaket",
						"Skrin Capaian Hakmilik Jana Subjaket", "EKPTG - PPT",
						dbx);
			}
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianPB", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupCarianPB",
						"Skrin Capaian PB", "EKPTG - PPT", dbx);
			}
			if (checkRegPopup("ekptg.view.ppt.FrmPopupPemegangAmanah_PopupPB",
					dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.FrmPopupPemegangAmanah_PopupPB",
						"Skrin Popup Pemegang Amanah Popup PB", "EKPTG - PPT",
						dbx);
			}
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupSalinPBHakmilik", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupSalinPBHakmilik",
						"Skrin Salin Maklumat PB Dan Hakmilik", "EKPTG - PPT",
						dbx);
			}
			checkFieldWujud("TBLPPTBEBANAN", "KETERANGAN_BEBANAN",
					"varchar2(4000)", dbx);
		} finally {
			if (dbx != null)
				dbx.close();
		}
		*/

		// default
		context.put("mode", "");
		context.put("isEdit", "");
		context.put("onchange", "no");
		context.put("idExistPB", "");
		context.put("PBexist", false);
		context.put("valConv", "");
		context.put("onchangeHM", "no");
		context.put("showJajahan", "no");
		context.put("hideAdd", "no");
		context.put("showAlertPaging", "no");
		context.put("showSJ", "no");

		// default hakmilik
		context.put("showLuput", "no");
		context.put("showWarta", "no");
		context.put("showLainLain", "no");
		context.put("showBoxAsal2", "no");
		context.put("showBoxAsal3", "no");
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");
		context.put("showButtonConvertAsal", "no");
		context.put("showButtonConvertAmbil", "no");
		context.put("showFieldAsalBeforeConvert", "no");
		context.put("showFieldAmbilBeforeConvert", "no");
		context.put("showDropdownUnitAsal", "no");
		context.put("showDropdownUnitAmbil", "no");

		// baru tambah PB

		Vector maklumat_siasatan_history = null;
		Vector maklumat_siasatanX = null;
		Vector senarai_perintah_award = null;
		Vector tempat_tampal = null;
		Vector maklumat_hakmilik_mmk_pb_siasatan = null;
		Vector senarai_pembatalan = null;
		Vector list_kementerian = null;
		Vector list_urusan = null;
		Vector list_status = null;
		Vector maklumat_pembatalan = null;
		Vector senarai_hakmilik = null;
		Vector senarai_hakmilik_batal = null;
		Vector senarai_pihak_penting = null;
		Vector listDokumen = null;
		Vector view_details_dokumen = null;
		Vector senarai_hakmilik_overall = null;
		Vector maklumat_tambahan = null;
		Vector maklumat_penyediaan = null;
		Vector maklumat_semakan_pegawai = null;
		Vector maklumat_semakan = null;
		Vector maklumat_keputusan = null;
		Vector maklumat_warta = null;
		Vector senarai_pihak_penting_pampasan = null;
		Vector maklumat_hakmilik_pampasan = null;
		Vector list_mukim_pampasan = null;
		Vector header_pampasan_pb = null;
		Vector maklumat_bayaran_bp = null;
		Vector maklumat_details_bayaran = null;
		Vector maklumat_am_tanah = null;
		Vector maklumat_hakmilik = null;
		Vector senarai_bangunan = null;
		Vector senarai_pihak_penting_bangunan = null;
		Vector list_negeri = null;
		Vector list_bandar = null;
		Vector list_siasatan = null;
		Vector maklumat_siasatan = null;
		Vector list_bandar_all = null;
		Vector list_kehadiran = null;
		Vector list_siasatan_penarikan = null;
		Vector list_check_kehadiran = null;
		Vector list_pegawai = null;
		Vector maklumat_siasatan_pb = null;
		Vector maklumat_kehadiran = null;
		Vector list_jenispb = null;
		Vector list_jenisnopb = null;
		Vector list_bank = null;
		Vector list_kehadiran_th = null;
		Vector list_jenisluas = null;
		Vector senarai_hakmilik_batal_penarikan_maklumat = null;
		Vector tarikh_akhir_tampal = null;
		Vector maklumat_pampasan_pb = null;
		Vector maklumat_bayaran_semua = null;
		Vector maklumat_hakmilik_mmk_lot = null;
		double maklumat_hakmilik_mmk_ekar = 0;
		double maklumat_hakmilik_mmk_hektar = 0;
		Vector maklumat_hakmilik_mmk_pb = null;
		Vector maklumat_am_tanah_permohonan = null;
		Vector nama_user = null;
		Vector senarai_lot_mmk = null;
		Vector senarai_pihak_penting_pampasan_perintah = null;
		Vector maklumat_hakmilik_mmk_pemilik_siasatan = null;
		Vector nowarta_lot_mmk = null;
		Vector list_subsiasatan = null;
		Vector dataSuburusanHakmilik = new Vector();
		Vector list_bangsa = null;
		Vector list_warga = null;
		Vector senarai_pihak_penting_bahagian = null;
		Vector list_bahagian = null;
		dataSuburusanHakmilik.clear();
		getIdSuburusanstatusfail.clear();
		Vector papar_pengguna = new Vector();//yati

		this.context.put("list_bahagian", "");
		this.context.put("senarai_pihak_penting_bahagian", "");
		this.context.put("list_bangsa", "");
		this.context.put("list_warga", "");
		this.context.put("list_default", "");
		this.context.put("tambah_kehadiran_wakil", "");
		this.context.put("tambah_kehadiran_negeri_wakil", "");

		this.context.put("maklumat_siasatan_history", "");
		this.context.put("maklumat_siasatanX", "");
		this.context.put("list_subsiasatan", "");
		this.context.put("nowarta_lot_mmk", "");
		this.context.put("maklumat_hakmilik_mmk_pemilik_siasatan", "");
		this.context.put("senarai_pihak_penting_pampasan_perintah", "");
		this.context.put("senarai_lot_mmk", "");
		this.context.put("tempat_tampal", "");
		this.context.put("maklumat_hakmilik_mmk_pb_siasatan", "");
		this.context.put("nama_user", "");
		this.context.put("maklumat_am_tanah_permohonan", "");
		this.context.put("maklumat_hakmilik_mmk_pb", "");
		this.context.put("maklumat_hakmilik_mmk_ekar", "");
		this.context.put("maklumat_hakmilik_mmk_hektar", "");
		this.context.put("maklumat_hakmilik_mmk_lot", "");
		this.context.put("maklumat_bayaran_semua", "");
		this.context.put("maklumat_pampasan_pb", "");
		this.context.put("tarikh_akhir_tampal", "");
		this.context.put("senarai_hakmilik_batal_penarikan_maklumat", "");
		this.context.put("list_jenisluas", "");
		this.context.put("list_kehadiran_th", "");
		this.context.put("list_jenispb", "");
		this.context.put("list_bank", "");
		this.context.put("list_jenisnopb", "");
		this.context.put("maklumat_kehadiran", "");
		this.context.put("maklumat_siasatan_pb", "");
		this.context.put("list_pegawai", "");
		this.context.put("list_check_kehadiran", "");
		this.context.put("list_siasatan_penarikan", "");
		this.context.put("list_bandar_all", "");
		this.context.put("list_kehadiran", "");
		this.context.put("maklumat_siasatan", "");
		this.context.put("list_siasatan", "");
		this.context.put("list_negeri", "");
		this.context.put("list_bandar", "");
		this.context.put("senarai_pihak_penting_bangunan", "");
		this.context.put("maklumat_hakmilik", "");
		this.context.put("senarai_bangunan", "");
		this.context.put("maklumat_am_tanah", "");
		this.context.put("maklumat_details_bayaran", "");
		this.context.put("maklumat_bayaran_bp", "");
		this.context.put("header_pampasan_pb", "");
		this.context.put("maklumat_hakmilik_pampasan", "");
		this.context.put("list_mukim_pampasan", "");
		this.context.put("senarai_pihak_penting_pampasan", "");
		this.context.put("maklumat_warta", "");
		this.context.put("maklumat_keputusan", "");
		this.context.put("maklumat_tambahan", "");
		this.context.put("maklumat_penyediaan", "");
		this.context.put("maklumat_semakan_pegawai", "");
		this.context.put("maklumat_semakan", "");
		this.context.put("header", "");
		this.context.put("maklumat_pembatalan", "");
		this.context.put("senarai_hakmilik", "");
		this.context.put("senarai_pihak_penting", "");
		this.context.put("listDokumen", "");
		this.context.put("listDokumen_size", "");
		this.context.put("id_permohonan", "");
		this.context.put("id_pembatalan", "");
		this.context.put("readmode", "");
		this.context.put("num_files", "");
		this.context.put("screen_name", "");
		this.context.put("view_details_dokumen", "");
		this.context.put("list_kementerian", "");
		this.context.put("list_urusan", "");
		this.context.put("list_status", "");
		this.context.put("listdepan", "");
		this.context.put("listdepan_size", "");
		this.context.put("first_masuk", "");
		this.context.put("display_error_message", "yes");
		this.context.put("senarai_hakmilik_overall", "");
		this.context.put("hakmilikoverall", "");
		this.context.put("hakmilik_belumbatal", "");
		this.context.put("hakmilik_sudahbatal", "");
		this.context.put("tajuk_header", "");
		this.context.put("txtNamaPB", "");
		this.context.put("txtNoPB", "");
		this.context.put("txtNoKPLama", "");
		this.context.put("txtNoLot", "");
		this.context.put("socMukim", "");
		this.context.put("list_mukim_pampasan", "");
		this.context.put("screen_kemasukan", "");
		this.context.put("id_siasatan", "");
		this.context.put("senarai_pembatalan", "");
		context.put("showAlertPaging", "no");
		context.put("hideAdd", "no");
		this.context.put("maklumat_PB_Salin", "");
		this.context.put("id_hakmilikpb_salin", "");
		this.context.put("id_hakmilik_salin", "");
		this.context.put("maklumat_Hakmilik_Salin", "");

		String flag_email = getParam("flag_email");
		String submit = getParam("command");
		String subminor_command = getParam("subminor_command");
		myLogger.info("submit : " + submit);
		myLogger.info("subminor_command minor : " + subminor_command);

		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());

		String bolehsimpan = "";
		String readmode = "";

		if (doPost.equals("true")) {
			bolehsimpan = "yes";
		}

		if ("semakHM".equals(submit)) {
			noLOT = getParam("carianNoLot");
			context.put("carianNoLot", noLOT.trim());
			
			//if flag hantar emel
			if(!flag_email.equals("Y")){
				flag_email = "";			
			}		
			this.context.put("flag_email",flag_email);		

			// data & list maklumat tanah
			// modelUPT.setListMaklumatTanahAcceptPenarikan_listHakmilik(idpermohonan,noLOT,idpegawai);
			// listMaklumatTanahAcceptPenarikan =
			// modelUPT.getListMaklumatTanahAcceptPenarikan();
			// context.put("listMaklumatTanah",
			// listMaklumatTanahAcceptPenarikan);
			context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));

			// list for create subjaket
			modelUPT.setListSeqSubjaket(idpermohonan);
			listSeqSubjaket = modelUPT.getListSeqSubjaket();
			context.put("listSeqSubjaket", listSeqSubjaket);

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			if ("janaSubjaketManual".equals(submit2)) {
				// validation sj
				context.put("showSJ", "yes");

				String submit3 = getParam("command3");
				myLogger.info("submit[3]: " + submit3);

				if ("simpanSj".equals(submit3)) {

					// validation sj
					context.put("showSJ", "no");

					if (doPost.equals("true")) {
						// jana subjaket
						simpanSj(session, idpermohonan);
					}

					// data & list maklumat tanah
					// modelUPT.setListMaklumatTanahAcceptPenarikan_listHakmilik(idpermohonan,noLOT,idpegawai);
					// listMaklumatTanahAcceptPenarikan =
					// modelUPT.getListMaklumatTanahAcceptPenarikan();
					// context.put("listMaklumatTanah",
					// listMaklumatTanahAcceptPenarikan);
					context.put("saiz_listTanah", modelUPT
							.setListMaklumatTanah_count(idpermohonan, noLOT,
									idpegawai));

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_subjaket = dh.get("flag_subjaket").toString();
					}

				}// close simpanSj

			}// close janaSubjaketManual

			// screen
			vm = screenUtama;

		}// close semakHM

		else if ("janaSubjaket".equals(submit)) {

			if (doPost.equals("true")) {
				// jana subjaket
				janaSubjaket(session, idpermohonan);
			}

			// data & list maklumat tanah
			// modelUPT.setListMaklumatTanahAcceptPenarikan_listHakmilik(idpermohonan,noLOT,idpegawai);
			// listMaklumatTanahAcceptPenarikan =
			// modelUPT.getListMaklumatTanahAcceptPenarikan();
			// context.put("listMaklumatTanah",
			// listMaklumatTanahAcceptPenarikan);
			// context.put("saiz_listTanah",
			// listMaklumatTanahAcceptPenarikan.size());
			context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(
					idpermohonan, noLOT, idpegawai));

			header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			if (dataHeader.size() != 0) {
				Hashtable dh = (Hashtable) dataHeader.get(0);
				flag_subjaket = dh.get("flag_subjaket").toString();
			}

			// screen
			vm = screenUtama;

		}// close janaSubjaket

		else if ("hapusHM".equals(submit)) {

			hapusHM(session);

			// form validation
			context.put("mode", "new");

			// clear value
			clearValueHM();

			noLOT = getParam("carianNoLot2");
			context.put("carianNoLot2", noLOT.trim());

			// list tanah
			listHakmilik(session, idpermohonan, noLOT);

			// get data from pendaftaran
			newDataSetting(idpermohonan);

			// screen
			vm = screenHakmilik;

		}// close hapusHM

		else if ("salinHakmilik".equals(submit)) {

			// form validation
			context.put("mode", "new");

			// get data from pendaftaran
			newDataSetting(idpermohonan);

			String id_hakmilik_salin = getParam("id_hakmilik_salin");
			this.context.put("id_hakmilik_salin", id_hakmilik_salin);
			this.context.put("maklumat_Hakmilik_Salin", "");

			if (!id_hakmilik_salin.equals("")
					&& !id_hakmilik_salin.equals(null)
					&& modelUPT.salin_maklumat_tanah(id_hakmilik_salin).size() > 0) {
				this.context.put("maklumat_Hakmilik_Salin",
						modelUPT.salin_maklumat_tanah(id_hakmilik_salin));
				dataHakmilik_copy(id_hakmilik_salin, "enabled");
			}

			vm = screenHakmilik;

		}// close tambahHM

		else if ("tambahHM".equals(submit)) {

			// form validation
			context.put("mode", "new");

			// clear value
			clearValueHM();

			noLOT = getParam("carianNoLot2");
			context.put("carianNoLot2", noLOT.trim());

			// list tanah
			listHakmilik(session, idpermohonan, noLOT);

			// get data from pendaftaran
			newDataSetting(idpermohonan);

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			if ("doOnchange".equals(submit2)) {
				
				// check validation jenis hakmilik & jenis rizab
				checkValOnChange();

				// check validation convert
				checkValConvert();

				// get and set data
				getAndSetHM(idpermohonan, "new", id_projekNegeri);

				String submit3 = getParam("command3");
				myLogger.info("submit[3] : " + submit3);

				if ("onchangeUnitLuasAsal".equals(submit3)) {

					// validations for luas asal
					validationConvertLuas();

					String submit4 = getParam("command4");
					myLogger.info("submit[4] : " + submit4);

					if ("convertNilaiAsal".equals(submit4)) {

						calculateNilaiAsal();

					}// close convertNilaiAsal

					else if ("clearConvertAsal".equals(submit4)) {

						clearConvertAsal("new");

					}// close clearConvertAsal

					else if ("onchangeUnitAsal".equals(submit4)) {

						// convert nilai lain
						changeUnitAsal();

					}// close onchangeUnitAsal

				}// close onchangeUnitLuasAsal

				else if ("onchangeUnitLuasAll".equals(submit3)) {
					// validations for luas asal
					validationConvertLuas();
					validationConvertLuasAmbil();

					String submit4 = getParam("command4");
					myLogger.info("submit[4] : " + submit4);

					if ("convertNilaiAll".equals(submit4)) {

						calculateNilaiAsal();
						calculateNilaiAmbil();

					}
				}

				else if ("onchangeUnitLuasAmbil".equals(submit3)) {

					// validations for luas ambil
					validationConvertLuasAmbil();

					String submit4 = getParam("command4");
					myLogger.info("submit[4] : " + submit4);

					if ("convertNilaiAmbil".equals(submit4)) {

						calculateNilaiAmbil();

					}// close convertNilaiAmbil

					else if ("clearConvertAmbil".equals(submit4)) {

						clearConvertAmbil("new");

					}// close clearConvertAmbil

					else if ("onchangeUnitAmbil".equals(submit4)) {

						// convert nilai lain
						changeUnitAmbil();

					}// close onchangeUnitAmbil

				}// close onchangeUnitLuasAmbil

			}// close doOnchange

			else if ("simpanHM".equals(submit2)) {

				if (doPost.equals("true")) {
					// simpan data
					simpanHM(session);
				}

				// form validation
				context.put("mode", "new");

				// clear value
				clearValueHM();

				// list tanah
				listHakmilik(session, idpermohonan, noLOT);

				// get data from pendaftaran
				newDataSetting(idpermohonan);

				header.setDataHeader(idpermohonan);
				dataHeader = header.getDataHeader();
				if (dataHeader.size() != 0) {
					Hashtable dh = (Hashtable) dataHeader.get(0);
					flag_subjaket = dh.get("flag_subjaket").toString();
				}

			}// close simpanHM

			// screen
			vm = screenHakmilik;

		}// close tambahHM

		else if ("viewHM".equals(submit)) {

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// form validation
			context.put("mode", "view");
			context.put("isEdit", "no");

			// check field validation
			checkFieldValidation(idHakmilik);

			// carian nolot
			noLOT = getParam("carianNoLot2");
			context.put("carianNoLot2", noLOT.trim());

			// carian nama pb
			namaPB = getParam("carianPB");
			context.put("carianPB", namaPB.trim());

			// carian no serah
			noSerah = getParam("carianNoSerah");
			context.put("carianNoSerah", noSerah.trim());

			// get total
			getTotalSyer(idHakmilik, "");

			// list PB
			listPB(session, idHakmilik, namaPB);

			// list Bebanan
			listBebanan(session, idHakmilik, noSerah);

			// list tanah
			listHakmilik(session, idpermohonan, noLOT);

			// dropdown hakmilik (disabled)
			dropdownHakmilikDISABLED(session, idHakmilik);

			// data hakmilik
			dataHakmilik(idHakmilik, "disabled");

			// disable untuk fasa 3
			/*
			 * list_kehadiran =
			 * logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
			 * this.context.put("list_kehadiran",list_kehadiran);
			 */
			this.context.put("list_kehadiran_size", logic.count_list_PB(
					getParam("id_hakmilik"), getParam("CariPB")));

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			this.context.put("view_pb", "");
			this.context.put("tambah_kehadiran_negeri", "");
			if ("View".equals(subminor_command)) {
				// getTotalSyer_Temp(idHakmilik,"");

			} else if ("Papar".equals(subminor_command)) {
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "view");
				maklumat_kehadiran = logic
						.maklumat_PB(getParam("id_hakmilikpb"));
				this.context.put("maklumat_kehadiran", maklumat_kehadiran);
				this.context.put("tajuk_header", "MAKLUMAT KEHADIRAN SIASATAN");
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);
				if (!h.get("ID_NEGERI").toString().equals("")) {
					list_bandar = logic.getListBandarByNegeri(h
							.get("ID_NEGERI").toString());
					this.context.put("list_bandar", list_bandar);
				}
			} else if ("tambah_wakil".equals(subminor_command)) {
				this.context.put("tambah_kehadiran_wakil", "yes");
				this.context.put("tambah_kehadiran_negeri_wakil", "yes");
				this.context.put("tambah_kehadiran", "");
				this.context.put("tambah_kehadiran_negeri", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(getParam("id_hakmilik"), "");
			} else if ("salin_pb".equals(subminor_command)) {
				String id_hakmilikpb_salin = getParam("id_hakmilikpb_salin");
				this.context.put("id_hakmilikpb_salin", id_hakmilikpb_salin);
				this.context.put("maklumat_PB_Salin", "");
				if (!id_hakmilikpb_salin.equals("")
						&& !id_hakmilikpb_salin.equals(null)
						&& logic.maklumat_PB_Salin(id_hakmilikpb_salin).size() > 0) {
					this.context.put("maklumat_PB_Salin",
							logic.maklumat_PB_Salin(id_hakmilikpb_salin));

					if (!logic.maklumat_PB_Salin(id_hakmilikpb_salin)
							.get("ID_NEGERI").equals("")) {
						list_bandar = logic.getListBandarByNegeri(logic
								.maklumat_PB_Salin(id_hakmilikpb_salin)
								.get("ID_NEGERI").toString());
						this.context.put("list_bandar", list_bandar);
					}

				}
				this.context.put("tambah_kehadiran_wakil", "yes");
				this.context.put("tambah_kehadiran_negeri_wakil", "yes");
				this.context.put("tambah_kehadiran", "");
				this.context.put("tambah_kehadiran_negeri", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(idHakmilik, "");

			}

			else if ("Simpan_Borang".equals(subminor_command)) {

				String[] ids1 = this.request.getParameterValues("ids1");
				String[] idPB = this.request.getParameterValues("idPB");
				String[] borangE = this.request.getParameterValues("borangE");
				String[] borangG = this.request.getParameterValues("borangG");
				String[] borangK = this.request.getParameterValues("borangK");

				if (idPB != null) {

					for (int i = 0; i < idPB.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(idPB[i], (String) session
									.getAttribute("_ekptg_user_id"), "clear");
						}
					}
				}

				if (ids1 != null) {

					for (int i = 0; i < ids1.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(ids1[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangC");
						}
					}
				}

				if (borangE != null) {

					for (int i = 0; i < borangE.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangE[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangE");
						}
					}
				}

				if (borangG != null) {

					for (int i = 0; i < borangG.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangG[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangG");
						}
					}
				}

				if (borangK != null) {

					for (int i = 0; i < borangK.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangK[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangK");
						}
					}
				}

			}
			// check size bahagian pb
			model.setSizeBahagianPB(idHakmilik);
			checkSizeBahagianPB = model.getSizeBahagianPB();
			context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());
			getTotalSyer_ALL(idHakmilik, "");
			senarai_pihak_penting_bahagian = logic
					.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
			this.context.put("senarai_pihak_penting_bahagian",
					senarai_pihak_penting_bahagian);
			modelUPT.setMaklumatTanah(getParam("id_hakmilik"));
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);
			// disable untuk fasa 3
			/*
			 * list_kehadiran =
			 * logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
			 * this.context.put("list_kehadiran",list_kehadiran);
			 */
			this.context.put("list_kehadiran_size", logic.count_list_PB(
					getParam("id_hakmilik"), getParam("CariPB")));

			if ("kemaskiniHM".equals(submit2)) {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "yes");

				// data hakmilik
				dataHakmilik(idHakmilik, "enabled");

				String submit3 = getParam("command3");
				myLogger.info("submit[3]: " + submit3);

				if ("doOnchangeUpdate".equals(submit3)) {

					// onchange validation
					context.put("onchangeHM", "yes");

					// check validation jenis hakmilik & jenis rizab
					checkValOnChange();

					// check validation convert
					checkValConvert();

					// get and set data
					getAndSetHM(idpermohonan, "view", id_projekNegeri);

					String submit4 = getParam("command4");
					myLogger.info("submit[4] : " + submit4);

					if ("onchangeUnitLuasAsalUpdate".equals(submit4)) {

						// validations for luas asal
						validationConvertLuas();

						String submit5 = getParam("command5");
						myLogger.info("submit[5] : " + submit5);

						if ("convertNilaiAsalUpdate".equals(submit5)) {

							calculateNilaiAsal();

						}// close convertNilaiAsalUpdate

						else if ("clearConvertAsalUpdate".equals(submit5)) {

							clearConvertAsal("view");

						}// close clearConvertAsalUpdate

						else if ("onchangeUnitAsalUpdate".equals(submit5)) {

							// convert nilai lain
							changeUnitAsal();

						}// close onchangeUnitAsalUpdate

					}// close onchangeUnitLuasAsalUpdate

					else if ("onchangeUnitLuasAmbilUpdate".equals(submit4)) {

						// validations for luas ambil
						validationConvertLuasAmbil();

						String submit5 = getParam("command5");
						myLogger.info("submit[5] : " + submit5);

						if ("convertNilaiAmbilUpdate".equals(submit5)) {

							calculateNilaiAmbil();

						}// close convertNilaiAmbilUpdate

						else if ("clearConvertAmbilUpdate".equals(submit5)) {

							clearConvertAmbil("view");

						}// close clearConvertAmbilUpdate

						else if ("onchangeUnitAmbilUpdate".equals(submit5)) {

							// convert nilai lain
							changeUnitAmbil();

						}// close onchangeUnitAmbilUpdate

					}// close onchangeUnitLuasAmbilUpdate

				}// close doOnchangeUpdate

				else if ("updateHM".equals(submit3)) {

					idHakmilik = getParam("id_hakmilik");
					context.put("id_hakmilik", idHakmilik);

					updateHM(session, idHakmilik, id_projekDaerah);

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "no");

					// check field validation
					checkFieldValidation(idHakmilik);

					// list tanah
					listHakmilik(session, idpermohonan, noLOT);

					// data hakmilik
					dataHakmilik(idHakmilik, "disabled");

				}// close updateHM

			}// close kemaskiniHM

			// screen
			vm = screenHakmilik;

		}// close viewHM

		else if ("tambahPB_temp".equals(submit)) {
			// if("tambahPB".equals(submit)){

			// form validation
			context.put("mode", "new");

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// data hakmilik
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);

			// carian nama pb
			namaPB = getParam("carianPB2");
			context.put("carianPB2", namaPB.trim());

			// list PB
			listPB(session, idHakmilik, namaPB);

			// get total
			getTotalSyer(idHakmilik, "");

			// reset data
			resetDataPB();

			// dropdown (new)
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB",
					null, "style=width:auto"));
			context.put("selectJenisPB",
					HTML.SelectJenisPb("socJenisPB", null, "style=width:300px"));
			context.put("selectBangsa",
					HTML.SelectBangsa("socBangsa", null, "style=width:300px"));
			context.put("selectWarga", HTML.SelectWarganegara("socWarga", null,
					"style=width:auto"));
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
					null, null,
					"style=width:300px onChange=\"onchangeNegeri();\""));
			context.put("selectBandar",
					HTML.SelectBandar("socBandar", null, "style=width:300px"));

			// get size bahagian pb and dropdown bahagian syer
			sizeAndDropdownBahagian(idHakmilik);

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			if ("onchangeNegeri".equals(submit2)) {

				// form validation
				context.put("hideAdd", "no");

				String idNegeri = getParam("socNegeri");
				String idBahagian = getParam("socBahagianPB");

				// dropdown
				context.put(
						"selectBahagianPB",
						HTML.SelectBahagianPBbyHakmilik(idHakmilik,
								"socBahagianPB", Utils.parseLong(idBahagian),
								null,
								"style=width:250px onChange=\"onchangeGetBahagian();\""));

				// dropdown by
				if (idNegeri != "") {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							idNegeri, "socBandar", null, "style=width:300px"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("socBandar",
							null, "style=width:300px"));
				}

				// get and set back
				getAndSetDataPB(session, "new");

			}// close onchangenegeri

			else if ("simpanPB".equals(submit2)) {

				idHakmilik = getParam("id_hakmilik");
				// idExistPB = getParam("idExistPB");

				// check exist id hakmilik
				/*
				 * model.setSizeExistPB(idHakmilik,idExistPB); checkExistPBidHM
				 * = model.getCheckSizeExistPB();
				 * 
				 * //EXIST if(checkExistPBidHM.size()!=0){
				 * 
				 * //alert jsp context.put("PBexist",true);
				 * 
				 * //NOT EXIST }else{
				 */
				if (doPost.equals("true")) {
					// simpan data
					simpanPB(session);
				}

				// }

				// list PB
				listPB(session, idHakmilik, namaPB);

				// get total
				getTotalSyer(idHakmilik, "");

				// get size bahagian pb and dropdown bahagian syer
				sizeAndDropdownBahagian(idHakmilik);

			}// close simpanPB

			else if ("onchangeGetBahagian".equals(submit2)) {

				// form validation
				context.put("hideAdd", "no");

				String idNegeri = getParam("socNegeri");
				String idBandar = getParam("socBandar");
				String idBahagian = getParam("socBahagianPB");

				// dropdown
				context.put(
						"selectBahagianPB",
						HTML.SelectBahagianPBbyHakmilik(idHakmilik,
								"socBahagianPB", Utils.parseLong(idBahagian),
								null,
								"style=width:250px onChange=\"onchangeGetBahagian();\""));

				// dropdown by
				if (idNegeri != "") {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							idNegeri, "socBandar", Utils.parseLong(idBandar),
							"style=width:300px"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("socBandar",
							Utils.parseLong(idBandar), "style=width:300px"));
				}

				// get and set back
				getAndSetDataPB(session, "new");

				// get bahagian
				getBahagianExist(idBahagian);

			}// close onchangeGetBahagian

			else if ("checkExistPB".equals(submit2)) {

				context.put("hideAdd", "no");

				idHakmilik = getParam("id_hakmilik");
				String idBahagian = getParam("socBahagianPB");

				// dropdown
				context.put(
						"selectBahagianPB",
						HTML.SelectBahagianPBbyHakmilik(idHakmilik,
								"socBahagianPB", Utils.parseLong(idBahagian),
								null,
								"style=width:250px onChange=\"onchangeGetBahagian();\""));

				// check exist pb
				checkExistPB(session);
				idExistPB = checkExistPB(session);

				// ID EXIST
				if (idExistPB != "") {

					// set exist pb detail
					getDetailExistPB(session, idExistPB, idHakmilik);
					// context.put("idExistPB", idExistPB);

					// ID NOT EXIST
				} else {

					// get and set back
					getAndSetDataPB(session, "new");

					String idNegeri = getParam("socNegeri");
					String idBandar = getParam("socBandar");

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								idNegeri, "socBandar",
								Utils.parseLong(idBandar), "style=width:300px"));
					} else {
						context.put("selectBandar", HTML.SelectBandar(
								"socBandar", Utils.parseLong(idBandar),
								"style=width:300px"));
					}

				}

			}// close checkExistPB

			// screen
			vm = screenPB;

		}// close tambahPB

		else if ("viewPB".equals(submit)) {

			// form validation
			context.put("mode", "view");
			context.put("isEdit", "no");

			// carian nama pb
			namaPB = getParam("carianPB2");
			context.put("carianPB2", namaPB.trim());

			String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
			context.put("id_pihakberkepentingan", id_pihakberkepentingan);

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// data hakmilik
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);

			// check size bahagian pb
			model.setSizeBahagianPB(idHakmilik);
			checkSizeBahagianPB = model.getSizeBahagianPB();
			context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());

			// get total
			getTotalSyer(idHakmilik, id_pihakberkepentingan);

			// data PB in TBLPPTHAKMILIKPB AND TBLPPTPIHAKBERKEPENTINGAN with
			// dropdown view (disabled)
			dataPBVIEW(session, id_pihakberkepentingan, idHakmilik);

			model.setDataDetailPB(id_pihakberkepentingan, idHakmilik, 0);
			dataDetailPB = model.getDataDetailPB();
			String id_jenisNoPB = "";
			String id_jenisPB = "";
			String id_bangsa = "";
			String id_warganegara = "";
			String id_negeri = "";
			String id_bandar = "";
			String id_hakmilikpb = "";
			String id_bahagianpb = "";
			if (dataDetailPB.size() != 0) {
				Hashtable pb = (Hashtable) dataDetailPB.get(0);
				id_jenisNoPB = pb.get("id_jenisnopb").toString();
				id_jenisPB = pb.get("id_jenispb").toString();
				id_bangsa = pb.get("id_bangsa").toString();
				id_warganegara = pb.get("id_warganegara").toString();
				id_negeri = pb.get("id_negeri").toString();
				id_bandar = pb.get("id_bandar").toString();
				id_hakmilikpb = (String) pb.get("id_hakmilikpb");
				id_bahagianpb = pb.get("id_bahagianpb").toString();
			}
			context.put("dataDetailPB", dataDetailPB);

			// id id_hakmilikpb
			context.put("id_hakmilikpb", id_hakmilikpb);

			// list PB
			listPB(session, idHakmilik, namaPB);

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			if ("kemaskiniPB".equals(submit2)) {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "yes");

				// dropdown (view)
				context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
						"socJenisNoPB", Utils.parseLong(id_jenisNoPB),
						"style=width:auto"));
				context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB",
						Utils.parseLong(id_jenisPB), "style=width:300px"));
				context.put("selectBangsa", HTML.SelectBangsa("socBangsa",
						Utils.parseLong(id_bangsa), "style=width:300px"));
				context.put("selectWarga", HTML.SelectWarganegara("socWarga",
						Utils.parseLong(id_warganegara), "style=width:auto"));
				context.put(
						"selectNegeri",
						HTML.SelectNegeriMampu("socNegeri",
								Utils.parseLong(id_negeri),
								"style=width:300px onChange=\"onchangeNegeriUpdate();\""));

				// dropdown bahagian
				context.put(
						"selectBahagianPB",
						HTML.SelectBahagianPBbyHakmilik(idHakmilik,
								"socBahagianPB",
								Utils.parseLong(id_bahagianpb), null,
								"style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

				if (id_negeri != "") {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							id_negeri, "socBandar", Utils.parseLong(id_bandar),
							"style=width:300px"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("socBandar",
							Utils.parseLong(id_bandar), "style=width:300px"));
				}

				String submit3 = getParam("command3");
				myLogger.info("submit[3]: " + submit3);

				if ("onchangeNegeriUpdate".equals(submit3)) {

					// onchange validation
					context.put("onchange", "yes");

					String idNegeri = getParam("socNegeri");
					id_bahagianpb = getParam("socBahagianPB");

					// dropdown bahagian
					context.put(
							"selectBahagianPB",
							HTML.SelectBahagianPBbyHakmilik(idHakmilik,
									"socBahagianPB",
									Utils.parseLong(id_bahagianpb), null,
									"style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								idNegeri, "socBandar", null,
								"style=width:300px"));
					} else {
						context.put("selectBandar", HTML.SelectBandar(
								"socBandar", null, "style=width:300px"));
					}

					// get and set back
					getAndSetDataPB(session, "view");

				}// close onchangeNegeriUpdate

				else if ("onchangeGetBahagianUpdate".equals(submit3)) {

					// onchange validation
					context.put("onchange", "yes");

					String idNegeri = getParam("socNegeri");
					String idBandar = getParam("socBandar");
					String idBahagian = getParam("socBahagianPB");

					// dropdown
					context.put(
							"selectBahagianPB",
							HTML.SelectBahagianPBbyHakmilik(idHakmilik,
									"socBahagianPB",
									Utils.parseLong(idBahagian), null,
									"style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								idNegeri, "socBandar",
								Utils.parseLong(idBandar), "style=width:300px"));
					} else {
						context.put("selectBandar", HTML.SelectBandar(
								"socBandar", Utils.parseLong(idBandar),
								"style=width:300px"));
					}

					// get and set back
					getAndSetDataPB(session, "view");

					// get bahagian
					getBahagianExist(idBahagian);

				}// close onchangeGetBahagian

				else if ("updatePB".equals(submit3)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "no");

					if (doPost.equals("true")) {
						updatePB(session);
					}

					idHakmilik = getParam("id_hakmilik");
					context.put("id_hakmilik", idHakmilik);

					id_pihakberkepentingan = getParam("id_pihakberkepentingan");
					context.put("id_pihakberkepentingan",
							id_pihakberkepentingan);

					// get total
					getTotalSyer(idHakmilik, "");

					// data PB with dropdown view (disabled)
					dataPBVIEW(session, id_pihakberkepentingan, idHakmilik);

					// list PB
					listPB(session, idHakmilik, namaPB);

				}// close updatePB

			}// close kemaskiniPB

			// screen
			vm = screenPB;

		}// close viewPB

		else if ("hapusPB".equals(submit)) {

			hapusPB(session);

			// form validation
			context.put("mode", "view");
			context.put("isEdit", "no");

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// list PB
			listPB(session, idHakmilik, namaPB);

			// list Bebanan
			listBebanan(session, idHakmilik, noSerah);

			// list tanah
			listHakmilik(session, idpermohonan, noLOT);

			// dropdown hakmilik (disabled)
			// dropdownHakmilikDISABLED(session,idHakmilik);

			// data hakmilik
			dataHakmilik(idHakmilik, "disabled");

			// screen
			vm = screenHakmilik;

		}// close hapusPB

		else if ("tambahBebanan".equals(submit)) {

			// form validation
			context.put("mode", "new");

			// carian no serah
			noSerah = getParam("carianNoSerah2");
			context.put("carianNoSerah2", noSerah.trim());

			// reset data
			context.put("txtPerserahan", "");
			context.put("keterangan_bebanan", "");
			context.put("txtNama", "");
			context.put("txtNoPB", "");
			context.put("txtAlamat1", "");
			context.put("txtAlamat2", "");
			context.put("txtAlamat3", "");
			context.put("txtPoskod", "");
			context.put("txdTarikhDaftar", "");

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// data hakmilik
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);

			// list Bebanan
			listBebanan(session, idHakmilik, noSerah);

			// dropdown (new)
			context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB",
					null, "style=width:auto"));
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
					null, null,
					"style=width:300px onChange=\"onchangeNegeriBebanan();\""));
			context.put("selectBandar",
					HTML.SelectBandar("socBandar", null, "style=width:300px"));
			context.put("selectBebanan", HTML.SelectBebanan("socJenisBebanan",
					null, "style=width:auto"));

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			if ("onchangeNegeriBebanan".equals(submit2)) {

				String idNegeri = getParam("socNegeri");

				// dropdown by
				if (idNegeri != "") {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							idNegeri, "socBandar", null, "style=width:300px"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("socBandar",
							null, "style=width:300px"));
				}

				// get and set back
				getAndSetDataBebanan(session, "new");

			}// close onchangeNegeriBebanan

			else if ("simpanBebanan".equals(submit2)) {

				if (doPost.equals("true")) {
					// simpan bebanan
					simpanBebanan(session);
				}

				// list Bebanan
				listBebanan(session, idHakmilik, noSerah);

			}// close simpanBebanan

			// screen
			vm = screenBebanan;

		}// close tambahBebanan

		else if ("viewBebanan".equals(submit)) {

			// form validation
			context.put("mode", "view");
			context.put("isEdit", "no");

			// carian no serah
			noSerah = getParam("carianNoSerah2");
			context.put("carianNoSerah2", noSerah.trim());

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// data hakmilik
			modelUPT.setMaklumatTanah(idHakmilik);
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);

			String idBebanan = getParam("id_bebanan");
			context.put("id_bebanan", idBebanan);

			// data bebanan (semak,disabled)
			dataBebanan(session, idBebanan);

			model.dataBebanan(idBebanan);
			dataBebanan = model.getDataBebanan();
			context.put("dataBebanan", dataBebanan);
			String id_jenisnopb = "";
			String id_negeri = "";
			String id_bandar = "";
			String id_jenisbebanan = "";
			if (dataBebanan.size() != 0) {
				Hashtable db_H = (Hashtable) dataBebanan.get(0);
				id_jenisnopb = db_H.get("id_jenisnopb").toString();
				id_negeri = db_H.get("id_negeri").toString();
				id_bandar = db_H.get("id_bandar").toString();
				id_jenisbebanan = db_H.get("id_kodbebanan").toString();
			}

			// list Bebanan
			listBebanan(session, idHakmilik, noSerah);

			String submit2 = getParam("command2");
			myLogger.info("submit[2]: " + submit2);

			if ("kemaskiniBebanan".equals(submit2)) {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "yes");

				// dropdown (enabled)
				context.put("selectJenisNoPB", HTML.SelectJenisNoPb(
						"socJenisNoPB", Utils.parseLong(id_jenisnopb),
						"style=width:auto"));
				context.put(
						"selectNegeri",
						HTML.SelectNegeriMampu("socNegeri",
								Utils.parseLong(id_negeri), null,
								"style=width:300px onChange=\"onchangeNegeriBebananUpdate();\""));
				context.put("selectBebanan", HTML.SelectBebanan(
						"socJenisBebanan", Utils.parseLong(id_jenisbebanan),
						"style=width:auto"));

				if (id_negeri != "") {
					context.put("selectBandar", HTML.SelectBandarByNegeri(
							id_negeri, "socBandar", Utils.parseLong(id_bandar),
							"style=width:300px"));
				} else {
					context.put("selectBandar", HTML.SelectBandar("socBandar",
							Utils.parseLong(id_bandar), "style=width:300px"));
				}

				String submit3 = getParam("command3");
				myLogger.info("submit[3]: " + submit3);

				if ("onchangeNegeriBebananUpdate".equals(submit3)) {

					// onchange validation
					context.put("onchange", "yes");

					String idNegeri = getParam("socNegeri");

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(
								idNegeri, "socBandar", null,
								"style=width:300px"));
					} else {
						context.put("selectBandar", HTML.SelectBandar(
								"socBandar", null, "style=width:300px"));
					}

					// get and set back
					getAndSetDataBebanan(session, "view");

				}// close onchangeNegeriBebananUpdate

				else if ("updateBebanan".equals(submit3)) {

					updateBebanan(session);

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "no");

					idHakmilik = getParam("id_hakmilik");
					context.put("id_hakmilik", idHakmilik);

					idBebanan = getParam("id_bebanan");
					context.put("id_bebanan", idBebanan);

					// data bebanan (semak,disabled)
					dataBebanan(session, idBebanan);

					// list Bebanan
					listBebanan(session, idHakmilik, noSerah);

				}// close updateBebanan

			}// close kemaskiniBebanan

			// screen
			vm = screenBebanan;

		}// close viewBebanan

		else if ("hapusBebanan".equals(submit)) {

			hapusBebanan(session);

			// form validation
			context.put("mode", "view");
			context.put("isEdit", "no");
			selectedTab = "1";

			String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// list PB
			listPB(session, idHakmilik, namaPB);

			// list Bebanan
			listBebanan(session, idHakmilik, noSerah);

			// list tanah
			listHakmilik(session, idpermohonan, noLOT);

			// dropdown hakmilik (disabled)
			dropdownHakmilikDISABLED(session, idHakmilik);

			// screen
			vm = screenHakmilik;

		}// close hapusBebanan

		// screen PB baru "PB" screen hakmilik

		else if ("tambahPB_screenhakmilik".equals(submit)) {
			String idHakmilik = "";
			idHakmilik = getParam("id_hakmilik");
			this.context.put("view_pb", "");
			this.context.put("tambah_kehadiran_negeri", "");
			if ("Papar".equals(subminor_command)) {
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "view");
				maklumat_kehadiran = logic
						.maklumat_PB(getParam("id_hakmilikpb"));
				this.context.put("maklumat_kehadiran", maklumat_kehadiran);
				this.context.put("tajuk_header", "MAKLUMAT KEHADIRAN SIASATAN");
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);
				if (!h.get("ID_NEGERI").toString().equals("")) {
					list_bandar = logic.getListBandarByNegeri(h
							.get("ID_NEGERI").toString());
					this.context.put("list_bandar", list_bandar);
				}
			} else if ("tambah_wakil".equals(subminor_command)) {
				this.context.put("tambah_kehadiran_wakil", "yes");
				this.context.put("tambah_kehadiran_negeri_wakil", "yes");
				this.context.put("tambah_kehadiran", "");
				this.context.put("tambah_kehadiran_negeri", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(idHakmilik, "");
			} else if ("salin_pb".equals(subminor_command)) {
				String id_hakmilikpb_salin = getParam("id_hakmilikpb_salin");
				this.context.put("id_hakmilikpb_salin", id_hakmilikpb_salin);
				this.context.put("maklumat_PB_Salin", "");
				if (!id_hakmilikpb_salin.equals("")
						&& !id_hakmilikpb_salin.equals(null)
						&& logic.maklumat_PB_Salin(id_hakmilikpb_salin).size() > 0) {
					this.context.put("maklumat_PB_Salin",
							logic.maklumat_PB_Salin(id_hakmilikpb_salin));
					if (!logic.maklumat_PB_Salin(id_hakmilikpb_salin)
							.get("ID_NEGERI").equals("")) {
						list_bandar = logic.getListBandarByNegeri(logic
								.maklumat_PB_Salin(id_hakmilikpb_salin)
								.get("ID_NEGERI").toString());
						this.context.put("list_bandar", list_bandar);
					}
				}
				this.context.put("tambah_kehadiran_wakil", "yes");
				this.context.put("tambah_kehadiran_negeri_wakil", "yes");
				this.context.put("tambah_kehadiran", "");
				this.context.put("tambah_kehadiran_negeri", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(idHakmilik, "");

			}

			else if ("Simpan_Borang".equals(subminor_command)) {

				String[] ids1 = this.request.getParameterValues("ids1");
				String[] idPB = this.request.getParameterValues("idPB");
				String[] borangE = this.request.getParameterValues("borangE");
				String[] borangG = this.request.getParameterValues("borangG");
				String[] borangK = this.request.getParameterValues("borangK");

				if (idPB != null) {

					for (int i = 0; i < idPB.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(idPB[i], (String) session
									.getAttribute("_ekptg_user_id"), "clear");
						}
					}
				}

				if (ids1 != null) {

					for (int i = 0; i < ids1.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(ids1[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangC");
						}
					}
				}

				if (borangE != null) {

					for (int i = 0; i < borangE.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangE[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangE");
						}
					}
				}

				if (borangG != null) {

					for (int i = 0; i < borangG.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangG[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangG");
						}
					}
				}

				if (borangK != null) {

					for (int i = 0; i < borangK.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangK[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangK");
						}
					}
				}

			}
			// check size bahagian pb
			model.setSizeBahagianPB(idHakmilik);
			checkSizeBahagianPB = model.getSizeBahagianPB();
			context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());
			getTotalSyer_ALL(idHakmilik, "");
			senarai_pihak_penting_bahagian = logic
					.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
			this.context.put("senarai_pihak_penting_bahagian",
					senarai_pihak_penting_bahagian);
			modelUPT.setMaklumatTanah(getParam("id_hakmilik"));
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);

			// disable untuk fasa 3
			/*
			 * list_kehadiran =
			 * logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
			 * this.context.put("list_kehadiran",list_kehadiran);
			 */
			this.context.put("list_kehadiran_size", logic.count_list_PB(
					getParam("id_hakmilik"), getParam("CariPB")));

			// String idHakmilik = getParam("id_hakmilik");
			context.put("id_hakmilik", idHakmilik);

			// form validation
			context.put("mode", "view");
			context.put("isEdit", "no");

			// check field validation
			checkFieldValidation(idHakmilik);

			// carian nolot
			noLOT = getParam("carianNoLot2");
			context.put("carianNoLot2", noLOT.trim());

			// carian nama pb
			namaPB = getParam("carianPB");
			context.put("carianPB", namaPB.trim());

			// carian no serah
			noSerah = getParam("carianNoSerah");
			context.put("carianNoSerah", noSerah.trim());

			// get total
			getTotalSyer(idHakmilik, "");

			// list PB
			listPB(session, idHakmilik, namaPB);

			// list Bebanan
			listBebanan(session, idHakmilik, noSerah);

			// list tanah
			listHakmilik(session, idpermohonan, noLOT);

			// dropdown hakmilik (disabled)
			dropdownHakmilikDISABLED(session, idHakmilik);

			// data hakmilik
			dataHakmilik(idHakmilik, "disabled");

			// disable untuk fasa 3
			/*
			 * list_kehadiran =
			 * logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
			 * this.context.put("list_kehadiran",list_kehadiran);
			 */
			this.context.put("list_kehadiran_size", logic.count_list_PB(
					getParam("id_hakmilik"), getParam("CariPB")));

			vm = screenHakmilik;
		}

		else if ("tambahPB".equals(submit)) {
			String idHakmilik = "";
			idHakmilik = getParam("id_hakmilik");

			this.context.put("view_pb", "");
			this.context.put("tambah_kehadiran_negeri", "");
			
			// dropdown (new)
			//context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB",null, "style=width:auto"));
			this.context.put("list_jenisnopb", logic.list_jenisnopb());
			
			if ("View".equals(subminor_command)) {
				getTotalSyer_Temp(idHakmilik, "");

			}else if ("Papar".equals(subminor_command)) {
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "view");
				maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));
				this.context.put("maklumat_kehadiran", maklumat_kehadiran);
				this.context.put("tajuk_header", "MAKLUMAT KEHADIRAN SIASATAN");
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);
				if (!h.get("ID_NEGERI").toString().equals("")) {
					list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
					this.context.put("list_bandar", list_bandar);
				}

			}else if ("tambah".equals(subminor_command)) {
				this.context.put("tambah_kehadiran", "yes");
				this.context.put("tambah_kehadiran_negeri", "yes");
				this.context.put("tambah_kehadiran_wakil", "");
				this.context.put("tambah_kehadiran_negeri_wakil", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(idHakmilik, "");

			}else if ("tambah_wakil".equals(subminor_command)) {
				this.context.put("tambah_kehadiran_wakil", "yes");
				this.context.put("tambah_kehadiran_negeri_wakil", "yes");
				this.context.put("tambah_kehadiran", "");
				this.context.put("tambah_kehadiran_negeri", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(idHakmilik, "");

			} else if ("salin_pb".equals(subminor_command)) {
				String id_hakmilikpb_salin = getParam("id_hakmilikpb_salin");
				this.context.put("id_hakmilikpb_salin", id_hakmilikpb_salin);
				this.context.put("maklumat_PB_Salin", "");
				if (!id_hakmilikpb_salin.equals("")
						&& !id_hakmilikpb_salin.equals(null)
						&& logic.maklumat_PB_Salin(id_hakmilikpb_salin).size() > 0) {
					this.context.put("maklumat_PB_Salin",
							logic.maklumat_PB_Salin(id_hakmilikpb_salin));
					if (!logic.maklumat_PB_Salin(id_hakmilikpb_salin)
							.get("ID_NEGERI").equals("")) {
						list_bandar = logic.getListBandarByNegeri(logic
								.maklumat_PB_Salin(id_hakmilikpb_salin)
								.get("ID_NEGERI").toString());
						this.context.put("list_bandar", list_bandar);
					}
				}
				this.context.put("tambah_kehadiran_wakil", "yes");
				this.context.put("tambah_kehadiran_negeri_wakil", "yes");
				this.context.put("tambah_kehadiran", "");
				this.context.put("tambah_kehadiran_negeri", "");
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				getTotalSyer_Temp(idHakmilik, "");

			}else if ("Simpan".equals(subminor_command)) {

				if (!getParam("id_hakmilikpb").equals("")) {
					String BC = this.request.getParameter("boxBorangC");
					String BE = this.request.getParameter("boxBorangE");
					String BG = this.request.getParameter("boxBorangG");
					String BK = this.request.getParameter("boxBorangK");

					String FC = "";
					String FE = "";
					String FG = "";
					String FK = "";

					myLogger.info("BC:" + BC + "BE:" + BE + "BG:" + BG + "BK:"+ BK);

					if (BC != null) {
						FC = "1";
					}
					if (BE != null) {
						FE = "1";
					}
					if (BG != null) {
						FG = "1";
					}
					if (BK != null) {
						FK = "1";
					}

					logic.updatePBSiasatan(getParam("id_pb"),
							getParam("txtNamaPB"), getParam("txtNoPB"),
							getParam("socJenisNOPB"), getParam("txtNamaPBKP"),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("socWarga"), getParam("socBangsa"),
							getParam("txtPenamaPenyata"), getParam("txtUmur"));
					logic.updateSiasatanPB(getParam("id_hakmilikpb"),
							getParam("socJenisPB"), getParam("txtNoAkaun"),
							getParam("socJenisBank"), getParam("txtAlamat1PB"),
							getParam("txtAlamat2PB"), getParam("txtAlamat3PB"),
							getParam("txtPoskodPB"), getParam("socNegeri"),
							getParam("socBandar"),
							(String) session.getAttribute("_ekptg_user_id"),
							getParam("txtNoHP"), getParam("txtNoTel"),
							getParam("txtNamaBank"),
							getParam("txtKeteranganPB"),
							getParam("txtCatatan"), FC, FE, FG, FK,
							getParam("id_bahagianpb"), getParam("txtSyorAtas"),
							getParam("txtSyorBawah"));

					maklumat_kehadiran = logic
							.maklumat_PB(getParam("id_hakmilikpb"));
					this.context.put("maklumat_kehadiran", maklumat_kehadiran);
					this.context.put("tajuk_header",
							"MAKLUMAT KEHADIRAN SIASATAN");
					Hashtable h = (Hashtable) maklumat_kehadiran.get(0);
					if (!h.get("ID_NEGERI").toString().equals("")) {
						list_bandar = logic.getListBandarByNegeri(h.get(
								"ID_NEGERI").toString());
						this.context.put("list_bandar", list_bandar);
					}
					this.context.put("view_pb", "yes");
					this.context.put("readmode", "view");
				} else {
					if (bolehsimpan.equals("yes")) {

						String BC = this.request.getParameter("boxBorangC");
						String BE = this.request.getParameter("boxBorangE");
						String BG = this.request.getParameter("boxBorangG");
						String BK = this.request.getParameter("boxBorangK");

						String FC = "";
						String FE = "";
						String FG = "";
						String FK = "";

						myLogger.info("BC:" + BC + "BE:" + BE + "BG:" + BG+ "BK:" + BK);

						if (BC != null) {
							FC = "1";
						}
						if (BE != null) {
							FE = "1";
						}
						if (BG != null) {
							FG = "1";
						}
						if (BK != null) {
							FK = "1";
						}

						logic.addSiasatanPB(
								getParam("id_pb"),
								getParam("txtNamaPB"),
								getParam("txtNoPB"),
								getParam("socJenisNOPB"),
								getParam("txtNamaPBKP"),
								(String) session.getAttribute("_ekptg_user_id"),
								getParam("id_hakmilikpb"),
								getParam("socJenisPB"),
								getParam("txtNoAkaun"),
								getParam("socJenisBank"),
								getParam("txtAlamat1PB"),
								getParam("txtAlamat2PB"),
								getParam("txtAlamat3PB"),
								getParam("txtPoskodPB"),
								getParam("socNegeri"),
								getParam("socBandar"),
								(String) session.getAttribute("_ekptg_user_id"),
								getParam("txtNoHP"), getParam("txtNoTel"),
								getParam("id_siasatan"),
								getParam("id_hakmilik"),
								getParam("txtPBhadir"),
								getParam("txtNamaBank"),
								getParam("txtKeteranganPB"),
								getParam("txtCatatan"), FC, FE, FG, FK,
								getParam("txtSyorAtas"),
								getParam("txtSyorBawah"), getParam("socWarga"),
								getParam("socBangsa"),
								getParam("id_bahagianpb"),
								getParam("txtPenamaPenyata"),
								getParam("txtUmur"));

						AuditTrail at = new AuditTrail();
						at.logActivity("", "1", null, session, "INS",
								"PIHAK BERKEPENTINGAN ["
										+ getParam("txtNamaPB") + "] INSERT ");

					}

				}
				getTotalSyer(idHakmilik, "");
			
			} else if ("DeleteTurutHadir".equals(subminor_command)) {
				this.context.put("view_pb", "");
				this.context.put("readmode", "");
				String NAMA_PB_temp = "";

				Db db = null;

				try {
					db = new Db();
					Statement stmt = db.getStatement();
					String sql = " SELECT NAMA_PB FROM TBLPPTPIHAKBERKEPENTINGAN "
							+ ""
							+ " WHERE ID_PIHAKBERKEPENTINGAN = '"
							+ getParam("id_pb") + "'";
					ResultSet rs = stmt.executeQuery(sql);
					myLogger.info("SQL  :" + sql);
					while (rs.next()) {
						NAMA_PB_temp = rs.getString("NAMA_PB");
					}
					AuditTrail at = new AuditTrail();
					at.logActivity("", "1", null, session, "DEL",
							"PIHAK BERKEPENTINGAN [" + NAMA_PB_temp
									+ "] DELETE");

				} finally {
					if (db != null)
						db.close();
				}

				logic.deleteMaklumatPB(getParam("id_pb"));
				logic.deleteHakmilikPB(getParam("id_hakmilikpb"));
			
			} else if ("Kemaskini".equals(subminor_command)) {
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));
				this.context.put("maklumat_kehadiran", maklumat_kehadiran);
				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);
				if (!h.get("ID_NEGERI").toString().equals("")) {
					list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
					this.context.put("list_bandar", list_bandar);
				}
				
			} else if ("batal".equals(subminor_command)) {
				this.context.put("view_pb", "yes");
				this.context.put("readmode", "edit");
				if (getParam("id_hakmilikpb") != ""
						&& getParam("id_hakmilikpb") != null) {
					maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));
					this.context.put("maklumat_kehadiran", maklumat_kehadiran);
					Hashtable h = (Hashtable) maklumat_kehadiran.get(0);
					if (!h.get("ID_NEGERI").toString().equals("")) {
						list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
						this.context.put("list_bandar", list_bandar);
					}
				}

			}else if ("getBandar".equals(subminor_command)) {
				String key = "";
				String value = "";
				Enumeration allparam = request.getParameterNames();
				for (; allparam.hasMoreElements();) {
					key = (String) allparam.nextElement();
					value = request.getParameter(key);
					this.context.put(key, value);
				}
				this.context.put("readmode", "edit");
				this.context.put("view_pb", "yes");
				if (!getParam("socNegeri").equals("")) {
					list_bandar = logic.getListBandarByNegeri(getParam("socNegeri"));
					this.context.put("list_bandar", list_bandar);
				}
				this.context.put("socBandar", "");
				if (getParam("id_hakmilikpb").equals("")) {
					this.context.put("tambah_kehadiran_negeri", "yes");
				}

				String BC = this.request.getParameter("boxBorangC");
				String BE = this.request.getParameter("boxBorangE");
				String BG = this.request.getParameter("boxBorangG");
				String BK = this.request.getParameter("boxBorangK");

				String FC = "";
				String FE = "";
				String FG = "";
				String FK = "";

				myLogger.info("BC:" + BC + "BE:" + BE + "BG:" + BG + "BK:" + BK);

				if (BC != null) {
					FC = "checked";
				}
				if (BE != null) {
					FE = "checked";
				}
				if (BG != null) {
					FG = "checked";
				}
				if (BK != null) {
					FK = "checked";
				}
				this.context.put("boxBorangC", FC);
				this.context.put("boxBorangE", FE);
				this.context.put("boxBorangG", FG);
				this.context.put("boxBorangK", FK);
			
			}else if ("Simpan_Borang".equals(subminor_command)) {

				String[] ids1 = this.request.getParameterValues("ids1");
				String[] idPB = this.request.getParameterValues("idPB");
				String[] borangE = this.request.getParameterValues("borangE");
				String[] borangG = this.request.getParameterValues("borangG");
				String[] borangK = this.request.getParameterValues("borangK");

				if (idPB != null) {

					for (int i = 0; i < idPB.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(idPB[i], (String) session
									.getAttribute("_ekptg_user_id"), "clear");
						}
					}
				}

				if (ids1 != null) {

					for (int i = 0; i < ids1.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(ids1[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangC");
						}
					}
				}

				if (borangE != null) {

					for (int i = 0; i < borangE.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangE[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangE");
						}
					}
				}

				if (borangG != null) {

					for (int i = 0; i < borangG.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangG[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangG");
						}
					}
				}

				if (borangK != null) {

					for (int i = 0; i < borangK.length; i++) {
						if (bolehsimpan.equals("yes")) {
							logic.updateBorang(borangK[i], (String) session
									.getAttribute("_ekptg_user_id"), "borangK");
						}
					}
				}

			}
			// check size bahagian pb
			model.setSizeBahagianPB(idHakmilik);
			checkSizeBahagianPB = model.getSizeBahagianPB();
			context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());

			getTotalSyer_ALL(idHakmilik, "");

			senarai_pihak_penting_bahagian = logic
					.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
			this.context.put("senarai_pihak_penting_bahagian",
					senarai_pihak_penting_bahagian);

			modelUPT.setMaklumatTanah(getParam("id_hakmilik"));
			dataMaklumatTanah = modelUPT.getMaklumatTanah();
			context.put("dataMaklumatTanah", dataMaklumatTanah);
			// context.put("id_siasatan",getParam("id_siasatan"));

			// disable untuk fasa 3
			/*
			 * list_kehadiran =
			 * logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
			 * this.context.put("list_kehadiran",list_kehadiran);
			 */
			this.context.put("list_kehadiran_size", logic.count_list_PB(
					getParam("id_hakmilik"), getParam("CariPB")));

			// list_kehadiran_th =
			// logic.list_siasatan_pb(getParam("id_siasatan"));
			// this.context.put("list_kehadiran_th",list_kehadiran_th);
			// list_check_kehadiran =
			// logic.list_check_kehadiran(getParam("id_siasatan"));
			// this.context.put("list_check_kehadiran",list_check_kehadiran);
			// maklumat_siasatan =
			// logic.maklumat_siasatan(getParam("id_siasatan"));
			// this.context.put("maklumat_siasatan",maklumat_siasatan);
			list_jenispb = logic.list_jenispb();
			this.context.put("list_jenispb", list_jenispb);
			list_jenisnopb = logic.list_jenisnopb();
			//this.context.put("list_jenisnopb", list_jenisnopb);
			list_bank = logic.list_bank();
			this.context.put("list_bank", list_bank);
			list_negeri = logic.getListnegeri();
			context.put("list_negeri", list_negeri);
			list_bangsa = logic.getListbangsa();
			context.put("list_bangsa", list_bangsa);
			list_warga = logic.getListwarga();
			context.put("list_warga", list_warga);
			this.context.put("tajuk_header", "MAKLUMAT PIHAK BERKEPENTINGAN");
			// vm = "app/ppt/frmSek8InfoBicaraPB.jsp";
			vm = screenPB;
		
		}

		else if ("hantar".equals(submit)) {

			// show paging alert
			context.put("showAlertPaging", "yes");
			// JOptionPane.showMessageDialog (null,
			// "Sila Teruskan 'Paging' No.4 Sekiranya Hakmilik Telah Diagihkan Oleh Pengarah",
			// "Langkah Seterusnya", JOptionPane.INFORMATION_MESSAGE);
			
			//*
			
			

			if (doPost.equals("true")) {
				// hantar ke laporan awal tanah
						
				hantar(session);
				updateSuburusanStatusFailPPT(session, idpermohonan, id_fail,id_suburusanstatusfailppt);				
				hantarEmel("content","Tindakan Pengarah untuk Agihan Tugas",no_fail, tujuan, tarikh_permohonan,nama_kementerian,getParam("id_fail"));						
			}

			// data & list maklumat tanah
			// modelUPT.setListMaklumatTanahAcceptPenarikan_listHakmilik(idpermohonan,noLOT,idpegawai);
			// listMaklumatTanahAcceptPenarikan =
			// modelUPT.getListMaklumatTanahAcceptPenarikan();
			// context.put("listMaklumatTanah",
			// listMaklumatTanahAcceptPenarikan);
			// context.put("saiz_listTanah",
			// listMaklumatTanahAcceptPenarikan.size());
			context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(
					idpermohonan, noLOT, idpegawai));

			// header
			header.setDataHeader(idpermohonan);
			dataHeader = header.getDataHeader();
			context.put("dataHeader", dataHeader);
			if (dataHeader.size() != 0) {
				Hashtable dh = (Hashtable) dataHeader.get(0);
				id_status = dh.get("id_status").toString();
			}

			// screen
			vm = screenUtama;

		}// close hantar

		else if ("cari".equals(submit)) {

			// carian
			ListCarianHM(session, userIdNeg);
			listPageDepan = model.getListCarian();

			// screen
			vm = listdepan;

		}// close cari
		
		else if ("viewMyIdentity".equals(submit)) {
			context.put("mode", "view");
			context.put("isEdit","yes");
			
			String jenisMode = "capaianIdentity";
			this.context.put("jenisMode", jenisMode);

			String noKadPengenalan = getParam("txtNoPB");
			Hashtable<String, String> data = myIdentity.getMaklumat(noKadPengenalan);
			
			this.context.put("txtNamaPB", data.get("namaPB"));
			this.context.put("txtNamaPBKP", data.get("namaPB"));
			this.context.put("list_jenisnopb", HTML.SelectJenisNoPb("socJenisNOPB", Long.parseLong(data.get("jenisNoPengenalanPB")), "", ""));
			this.context.put("txtNoPB", getParam("txtNoPB"));
			this.context.put("list_bangsa", HTML.SelectBangsa("socBangsa", Long.parseLong(data.get("bangsa")), "", ""));
			this.context.put("list_warga", HTML.SelectWarganegara("socWarga", Long.parseLong(data.get("warganegara")), "", ""));
			this.context.put("txtAlamat1PB", data.get("alamatPB"));
			this.context.put("txtPoskodPB", data.get("poskod"));
			this.context.put("list_negeri", HTML.SelectNegeri("socNegeri", Long.parseLong(data.get("negeri")), "", ""));
			this.context.put("list_bandar", HTML.SelectBandarByNegeri(data.get("negeri"), "socBandar", Long.parseLong(data.get("bandar")), "", ""));

			vm = screenPB;

		}// close MyIdentity
 
		else {

			listPageDepan = model.getListPermohonan(userIdNeg);

			context.put("nofail", "");
			context.put("carianTarikh", "");
			context.put("carianStatus", "");

			// screen
			vm = listdepan;

		}// close else

		// list permohonan
		context.put("listPermohonanHM", listPageDepan);
		context.put("list_size", listPageDepan.size());

		// flag
		context.put("flag_subjaket", flag_subjaket);

		// id
		context.put("id_permohonan", idpermohonan);
		context.put("id_status", id_status);
		context.put("id_fail", id_fail);

		// id daerah & negeri projek
		context.put("id_daerahProjek", id_projekDaerah);
		context.put("id_negeriProjek", id_projekNegeri);

		setupPage(session, action, listPageDepan);
		this.context.put("selectedTab", selectedTab);
		myLogger.info("vm : " + vm);
		return vm;

	}// close public template

	@SuppressWarnings({ "unchecked", "static-access" })
/*	private void sendEmail(String id_pengarah, String nofail,
			String nama_projek, String tarikh_permohonan,
			String nama_kementerian) throws Exception {

		Vector checkEmailPengarah = new Vector();
		checkEmailPengarah.clear();

		// check email (pengarah)
		checkEmailPengarah = myInfo.checkEmail(id_pengarah);
		String emelPengarah = "";
		if (checkEmailPengarah.size() != 0) {
			Hashtable ceP = (Hashtable) checkEmailPengarah.get(0);
			emelPengarah = (String) ceP.get("EMEL");
		}

		EmailTester et = new EmailTester();

		// if(emelPengarah!=""){
		et.setEmail("seksyen8", emelPengarah, "hantarUntukAgihan", nofail,
				nama_projek, tarikh_permohonan, nama_kementerian);
		// }

		// jenis email
		// - hantar pengesahan (pt - pengarah)
		// - hantar untuk diagihankan
		// - hantar untuk semakan mmk (pt - pengarah)

	}// close sendEmail
*/
	private void simpanSj(HttpSession session, String idpermohonan)
			throws Exception {

		String[] listIdHM = request.getParameterValues("ListIdHM");
		String[] txtSj = request.getParameterValues("txtSj");
		String id_user = (String) session.getAttribute("_ekptg_user_id");

		if ((listIdHM != null && txtSj != null)) {

			for (int i = 0; i < listIdHM.length; i++) {
				FrmUPTSek8HakmilikData.simpanSj(listIdHM[i], txtSj[i],
						idpermohonan, id_user);

			}
		}

	}// close simpanSj

	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndId(String userIdNeg) throws Exception {

		Vector dataNamaPengarah = new Vector();
		dataNamaPengarah.clear();

		// GET NAMA PENGARAH DAN ID PENGARAH
		String nama_pengarah = "";
		String id_pengarah = "";
		modelUPT.setNamaPengarah(userIdNeg);
		dataNamaPengarah = modelUPT.getNamaPengarah();
		if (dataNamaPengarah.size() != 0) {
			Hashtable np = (Hashtable) dataNamaPengarah.get(0);
			nama_pengarah = (String) np.get("nama_pengarah");
			id_pengarah = (String) np.get("user_id");
			
		}

		//context.put("getNamaPengarah",dataNamaPengarah);
		context.put("nama_pengarah", nama_pengarah);
	
		//System.out.println("getNamaPengarah :::"+dataNamaPengarah);

		return id_pengarah;

	}// close userData

	//penambahan function-yati
	public void paparData(String id_fail) throws Exception
	{
		//Vector papar_pengguna = new Vector(); 
		Vector dataListPengarah = new Vector(); 
		String nama_pengarah = "";
		String id_pengarah = ""; 
		String emel = ""; 
	
		dataListPengarah = modelUPT.setListPengarah(id_fail);
	//	System.out.println("dataListPengarah.size() :::"+dataListPengarah.size());
		if (dataListPengarah.size() != 0) {
			Hashtable npp = (Hashtable) dataListPengarah.get(0);

		}
		context.put("getListPengarah",dataListPengarah);
	
		//System.out.println("getListPengarah :::"+dataListPengarah);
	
		if (dataListPengarah.size() > 0){
			
			String inputLine = ""; 
			
			for(int i = 0;i < dataListPengarah.size();i++){
				inputLine = dataListPengarah.get(i).toString();		
				//myLogger.info("CHECK SIZE"+dataListPengarah.size()+" ID_USER_TERIMA :"+dataListPengarah.get(i).toString());
				
				Hashtable nppp = (Hashtable) dataListPengarah.get(i);
				nama_pengarah = (String) nppp.get("nama_pengarah").toString();
				id_pengarah = (String) nppp.get("user_id").toString();
				emel = (String) nppp.get("emel").toString();

				//myLogger.info("DISPLAY EMEL PENERIMA :"+nppp.get("emel").toString());
			
			
			}
			
			
		}	
	
	}

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
				"1467");

	}// close updateSuburusanStatusFailPPT

	@SuppressWarnings({ "static-access", "unchecked" })
	private void getBahagianExist(String idBahagian) throws Exception {

		Vector checkExistBahagianPB = new Vector();
		checkExistBahagianPB.clear();

		String syer_atas = "";
		String syer_bawah = "";
		model.setDataExistBahagianPB(idBahagian);
		checkExistBahagianPB = model.getDataExistBahagianPB();
		if (checkExistBahagianPB.size() != 0) {
			Hashtable ceb = (Hashtable) checkExistBahagianPB.get(0);
			syer_atas = (String) ceb.get("syer_atas");
			syer_bawah = (String) ceb.get("syer_bawah");
		}

		context.put("txtSyorAtas", syer_atas);
		context.put("txtSyorBawah", syer_bawah);

	}// close getBahagianExist

	@SuppressWarnings({ "static-access", "unchecked" })
	private void sizeAndDropdownBahagian(String idHakmilik) throws Exception {

		Vector checkSizeBahagianPB = new Vector();
		checkSizeBahagianPB.clear();

		model.setSizeBahagianPB(idHakmilik);
		checkSizeBahagianPB = model.getSizeBahagianPB();
		context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());

		// dropdown bahagian syer
		context.put("selectBahagianPB", HTML.SelectBahagianPBbyHakmilik(
				idHakmilik, "socBahagianPB", null, null,
				"style=width:250px onChange=\"onchangeGetBahagian();\""));

	}// close sizeAndDropdownBahagian

	@SuppressWarnings("unchecked")
	private void dataHakmilik(String idHakmilik, String disability)
			throws Exception {

		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);

		String id_negeriprojek = "";
		String id_daerah = "";
		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		String id_daerahpenggawa = "";
		String id_unitluasambil = "";
		if (dataMaklumatTanah.size() != 0) {
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
			id_daerah = h.get("id_daerah").toString();
			id_negeriprojek = h.get("id_negeri").toString();
			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
			id_unitluasambil = h.get("id_unitluasambil").toString();
		}

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		String mode = "";
		if (disability.equals("enabled")) {
			mode = "";
		} else {
			mode = "disabled class=disabled";
		}

		// untuk kelantan shj
		context.put(
				"SelectDaerahPenggawa",
				HTML.SelectDaerahPenggawa("socDaerahPenggawa",
						Utils.parseLong(id_daerahpenggawa), null, " " + mode
								+ " style=width:274px"));

		// dropdown
		if (id_negeriprojek.equals("10")) {
			context.put(
					"selectJenisHakmilik",
					HTML.SelectJenisHakmilikSelangor(
							"socJenisHakmilik",
							Utils.parseLong(id_jenishakmilik),
							"id=socJenisHakmilik "
									+ mode
									+ " style=width:auto onchange=doOnchangeUpdate()"));
		} else {
			context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
					"socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
					"id=socJenisHakmilik " + mode
							+ " style=width:auto onchange=doOnchangeUpdate()"));
		}

		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah", Utils.parseLong(id_kategoritanah),
				"id=socKategoriTanah " + mode + " style=width:auto", null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",
				Utils.parseLong(id_lot), "style=width:auto " + mode + " "));

		// dropdown unit luas
		context.put(
				"selectUnitLuasLot",
				HTML.SelectLuas(
						"socUnitLuasLot",
						Utils.parseLong(id_luaslot),
						"style=width:250px "
								+ mode
								+ " id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		context.put(
				"selectUnitLuasAmbil",
				HTML.SelectLuas(
						"socUnitLuasAmbil",
						Utils.parseLong(id_unitluasambil),
						"style=width:250px "
								+ mode
								+ " id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));

		// dropdown by
		if (id_daerah != "") {
			context.put(
					"selectMukim",
					HTML.SelectMukimNoKodByDaerah(id_daerah, "socMukim",
							Utils.parseLong(id_mukim), "style=width:auto "
									+ mode + ""));
		} else {
			context.put("selectMukim", HTML.SelectMukimNoKod("socMukim",
					Utils.parseLong(id_mukim), "style=width:auto " + mode + ""));
		}

	}// close dataHakmilik

	@SuppressWarnings("unchecked")
	private void dataHakmilik_copy(String idHakmilik, String disability)
			throws Exception {

		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);

		String id_negeriprojek = "";
		String id_daerah = "";
		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		String id_daerahpenggawa = "";
		String id_unitluasambil = "";
		if (dataMaklumatTanah.size() != 0) {
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
			id_daerah = h.get("id_daerah").toString();
			id_negeriprojek = h.get("id_negeri").toString();
			id_daerahpenggawa = h.get("id_daerahpenggawa").toString();
			id_unitluasambil = h.get("id_unitluasambil").toString();
		}

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		String mode = "";
		if (disability.equals("enabled")) {
			mode = "";
		} else {
			mode = "disabled class=disabled";
		}

		// untuk kelantan shj
		context.put(
				"SelectDaerahPenggawa",
				HTML.SelectDaerahPenggawa("socDaerahPenggawa",
						Utils.parseLong(id_daerahpenggawa), null, " " + mode
								+ " style=width:274px"));

		// dropdown
		if (id_negeriprojek.equals("10")) {
			context.put(
					"selectJenisHakmilik",
					HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",
							Utils.parseLong(id_jenishakmilik),
							"id=socJenisHakmilik " + mode
									+ " style=width:auto onchange=doOnchange()"));
		} else {
			context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
					"socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
					"id=socJenisHakmilik " + mode
							+ " style=width:auto onchange=doOnchange()"));
		}

		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah", Utils.parseLong(id_kategoritanah),
				"id=socKategoriTanah " + mode + " style=width:auto", null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",
				Utils.parseLong(id_lot), "style=width:auto " + mode + " "));

		// dropdown unit luas
		context.put("selectUnitLuasLot", HTML.SelectLuas("socUnitLuasLot",
				Utils.parseLong(id_luaslot), "style=width:250px " + mode
						+ " id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		context.put(
				"selectUnitLuasAmbil",
				HTML.SelectLuas(
						"socUnitLuasAmbil",
						Utils.parseLong(id_unitluasambil),
						"style=width:250px "
								+ mode
								+ " id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));

		// dropdown by
		if (id_daerah != "") {
			context.put(
					"selectMukim",
					HTML.SelectMukimNoKodByDaerah(id_daerah, "socMukim",
							Utils.parseLong(id_mukim), "style=width:auto "
									+ mode + ""));
		} else {
			context.put("selectMukim", HTML.SelectMukimNoKod("socMukim",
					Utils.parseLong(id_mukim), "style=width:auto " + mode + ""));
		}

	}// close dataHakmilik

	@SuppressWarnings("unchecked")
	private void checkFieldValidation(String idHakmilik) throws Exception {

		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		modelUPT.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		String id_jenishakmilik = "";
		String sorJenisRizab = "";
		String luas_asal = "";
		String luas_ambil = "";
		if (dataMaklumatTanah.size() != 0) {
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			sorJenisRizab = h.get("flag_jenis_rizab").toString();
			luas_asal = h.get("luas_lot").toString();
			luas_ambil = h.get("luas_ambil").toString();
		}

		// check date luput if jenis hakmilik = pajakan
		if (id_jenishakmilik.equals("2") || id_jenishakmilik.equals("5")
				|| id_jenishakmilik.equals("28")
				|| id_jenishakmilik.equals("73")
				|| id_jenishakmilik.equals("74")
				|| id_jenishakmilik.equals("75")
				|| id_jenishakmilik.equals("80")
				|| id_jenishakmilik.equals("85")
				|| id_jenishakmilik.equals("113")) {
			context.put("showLuput", "yes");
		} else {
			context.put("showLuput", "no");
		}

		// check jenis rizab field validation
		if (sorJenisRizab.equals("1")) {
			context.put("showWarta", "yes");
			context.put("showLainLain", "no");
		} else if (sorJenisRizab.equals("5")) {
			context.put("showWarta", "no");
			context.put("showLainLain", "yes");
		} else {
			context.put("showWarta", "no");
			context.put("showLainLain", "no");
		}

		// show value before convert and dropdown unit
		if (luas_asal != "") {
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		} else {
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}

		if (luas_ambil != "") {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

	}// close checkJenisHakmilik

	private void changeUnitAmbil() throws Exception {

		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");

		if (showButtonConvertAmbil.equals("yes")) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		String unitConvert = getParam("sorDropdownUnitAmbil");
		context.put("sorDropdownUnitAmbil", unitConvert);

		String txtLuasLotAmbil = Utils
				.RemoveSymbol(getParam("txtLuasLotAmbil"));

		Double total = 0.0000;

		// hektar convert to meter persegi
		if (unitConvert.equals("1")) {
			total = Double.parseDouble(txtLuasLotAmbil) * 0.0001;
			// meter persegi convert to hektar
		} else {
			total = Double.parseDouble(txtLuasLotAmbil) * 10000;
		}

		// put data
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");
		context.put("showButtonConvertAmbil", "no");

	}// close changeUnitAmbil

	private void clearConvertAmbil(String mode) throws Exception {

		// luas ambil
		context.put("txtLuasLotAmbil", "");
		context.put("showFieldAmbilBeforeConvert", "no");
		context.put("showDropdownUnitAmbil", "no");
		context.put("showButtonConvertAmbil", "no");
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");

		// dropdown unit luas
		if (mode.equals("new")) {
			context.put(
					"selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil", null,
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		} else {
			context.put(
					"selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil", null,
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}

	}// close clearConvertAmbil

	private void calculateNilaiAmbil() throws Exception {

		String id_kategoritanah = getParam("socKategoriTanah");

		// data luas ambil
		String unitLuasAmbil = getParam("socUnitLuasAmbil");
		String luasAmbil1 = Utils.RemoveSymbol(getParam("txtLuasLotAmbil"));
		String luasAmbil2 = getParam("txtLuasLotAmbil2");
		String luasAmbil3 = getParam("txtLuasLotAmbil3");

		// field validation
		if (unitLuasAmbil != "") {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

		double total = 0.0000;

		String unitSebelumConvert1 = "";
		String unitSebelumConvert2 = "";
		String unitSebelumConvert3 = "";
		String sorDropdownUnitAmbil = "";

		// 1 = kilometer persegi
		if (unitLuasAmbil.equals("1")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 100;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1000000;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "KILOMETER PERSEGI";

		}// close kilometer persegi

		// 2 = hektar
		if (unitLuasAmbil.equals("2")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 10000;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "HEKTAR";

		}// close hektar

		// 3 = meter persegi
		if (unitLuasAmbil.equals("3")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "METER PERSEGI";

		}// close meter persegi

		// 4 = ekar/rood/pole
		if (unitLuasAmbil.equals("4")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 4) + (Double
						.parseDouble(luasAmbil3) / 160)) * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 4) + (Double
						.parseDouble(luasAmbil3) / 160)) * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";

		}// close ekar/rood/pole

		// 5 = kaki persegi
		if (unitLuasAmbil.equals("5")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.000009290304;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 0.09290304;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "KAKI PERSEGI";

		}// close kaki persegi

		// 6 = ekar perpuluhan
		if (unitLuasAmbil.equals("6")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR PERPULUHAN";

		}// close ekar perpuluhan

		// 7 = ekar/depa
		if (unitLuasAmbil.equals("7")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1) + (Double
						.parseDouble(luasAmbil2) / 1000)) * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1) + (Double
						.parseDouble(luasAmbil2) / 1000)) * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";

		}// close ekar/depa

		// 8 = relong/jemba/kaki persegi
		if (unitLuasAmbil.equals("8")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 484) + (Double
						.parseDouble(luasAmbil3) / 30976)) * 0.711111 * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1)
						+ (Double.parseDouble(luasAmbil2) / 484) + (Double
						.parseDouble(luasAmbil3) / 30976)) * 0.711111 * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";

		}// close relong/jemba/kaki persegi

		// 9 = batu nautika
		if (unitLuasAmbil.equals("9")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAmbil1) * 0.0001;
				sorDropdownUnitAmbil = "1";
			} else {
				total = Double.parseDouble(luasAmbil1) * 1;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "BATU NAUTIKA";

		}// close batu nautika

		// put data luas ambil
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAmbil", sorDropdownUnitAmbil);
		context.put("txtLuasLotAmbilSebelumConvert", luasAmbil1 + " "
				+ unitSebelumConvert1 + " " + luasAmbil2 + " "
				+ unitSebelumConvert2 + " " + luasAmbil3 + " "
				+ unitSebelumConvert3);
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");
		context.put("showButtonConvertAmbil", "no");

	}// close calculateNilaiAmbil

	private void validationConvertLuasAmbil() throws Exception {

		String socUnitLuasAmbil = getParam("socUnitLuasAmbil");
		String showButtonConvertAsal = getParam("showButtonConvertAsal");
		String showBoxAsal2 = getParam("showBoxAsal2");
		String showBoxAsal3 = getParam("showBoxAsal3");

		if (socUnitLuasAmbil != "") {
			if (socUnitLuasAmbil.equals("4") || socUnitLuasAmbil.equals("8")) {
				context.put("showBoxAmbil2", "yes");
				context.put("showBoxAmbil3", "yes");
			} else if (socUnitLuasAmbil.equals("7")) {
				context.put("showBoxAmbil2", "yes");
				context.put("showBoxAmbil3", "no");
			} else {
				context.put("showBoxAmbil2", "no");
				context.put("showBoxAmbil3", "no");
			}
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

		/* Validation button convert Ambil */
		if (!socUnitLuasAmbil.isEmpty()) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		/* Validation button convert Asal */
		if (showButtonConvertAsal.equals("yes")) {
			context.put("showButtonConvertAsal", "yes");
		} else {
			context.put("showButtonConvertAsal", "no");
		}

		if (showBoxAsal3.equals("yes")) {
			context.put("showBoxAsal2", "yes");
			context.put("showBoxAsal3", "yes");
		} else if (showBoxAsal2.equals("yes")) {
			context.put("showBoxAsal2", "yes");
			context.put("showBoxAsal3", "no");
		} else {
			context.put("showBoxAsal2", "no");
			context.put("showBoxAsal3", "no");
		}

		// validation convert
		String showFieldAsalBeforeConvert = getParam("showFieldAsalBeforeConvert");
		if (showFieldAsalBeforeConvert.equals("yes")) {
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		} else {
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}

	}// close validationConvertLuasAmbil

	private void changeUnitAsal() throws Exception {

		String showButtonConvertAsal = getParam("showButtonConvertAsal");

		if (showButtonConvertAsal.equals("yes")) {
			context.put("showButtonConvertAsal", "yes");
		} else {
			context.put("showButtonConvertAsal", "no");
		}

		String unitConvert = getParam("sorDropdownUnitAsal");
		context.put("sorDropdownUnitAsal", unitConvert);

		String txtLuasLotAsal = Utils.RemoveSymbol(getParam("txtLuasLotAsal"));

		Double total = 0.0000;

		// hektar convert to meter persegi
		if (unitConvert.equals("1")) {
			total = Double.parseDouble(txtLuasLotAsal) * 0.0001;
			// meter persegi convert to hektar
		} else {
			total = Double.parseDouble(txtLuasLotAsal) * 10000;
		}

		// put data
		context.put("txtLuasLotAsal", Utils.formatLuasHM(total));
		context.put("showBoxAsal2", "no");
		context.put("showBoxAsal3", "no");
		context.put("showButtonConvertAsal", "no");

	}// close changeUnitAsal

	private void clearConvertAsal(String mode) throws Exception {

		// luas asal
		context.put("txtLuasLotAsal", "");
		context.put("showFieldAsalBeforeConvert", "no");
		context.put("showDropdownUnitAsal", "no");
		context.put("showButtonConvertAsal", "no");
		context.put("showBoxAsal2", "no");
		context.put("showBoxAsal3", "no");

		// dropdown unit luas
		if (mode.equals("new")) {
			context.put(
					"selectUnitLuasLot",
					HTML.SelectLuas("socUnitLuasLot", null,
							"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		} else {
			context.put(
					"selectUnitLuasLot",
					HTML.SelectLuas("socUnitLuasLot", null,
							"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		}

	}// close clearConvertAsal

	private void calculateNilaiAsal() throws Exception {

		String id_kategoritanah = getParam("socKategoriTanah");

		// data luas asal
		String unitLuasAsal = getParam("socUnitLuasLot");
		String luasAsal1 = Utils.RemoveSymbol(getParam("txtLuasLotAsal"));
		String luasAsal2 = Utils.RemoveSymbol(getParam("txtLuasLotAsal2"));
		String luasAsal3 = Utils.RemoveSymbol(getParam("txtLuasLotAsal3"));

		// field validation
		if (unitLuasAsal != "") {
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		} else {
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}

		double total = 0.0000;

		String unitSebelumConvert1 = "";
		String unitSebelumConvert2 = "";
		String unitSebelumConvert3 = "";
		String sorDropdownUnitAsal = "";

		// 1 = kilometer persegi
		if (unitLuasAsal.equals("1")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAsal1) * 100;
				sorDropdownUnitAsal = "1";
			} else {
				total = Double.parseDouble(luasAsal1) * 1000000;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "KILOMETER PERSEGI";

		}// close kilometer persegi

		// 2 = hektar
		if (unitLuasAsal.equals("2")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "1";
			} else {
				total = Double.parseDouble(luasAsal1) * 10000;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "HEKTAR";

		}// close hektar

		// 3 = meter persegi
		if (unitLuasAsal.equals("3")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAsal1) * 0.0001;
				sorDropdownUnitAsal = "1";
			} else {
				total = Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "METER PERSEGI";

		}// close meter persegi

		// 4 = ekar/rood/pole
		if (unitLuasAsal.equals("4")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAsal1)
						+ (Double.parseDouble(luasAsal2) / 4) + (Double
						.parseDouble(luasAsal3) / 160)) * 0.404686;
				sorDropdownUnitAsal = "1";
			} else {
				total = (Double.parseDouble(luasAsal1)
						+ (Double.parseDouble(luasAsal2) / 4) + (Double
						.parseDouble(luasAsal3) / 160)) * 4046.86;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";

		}// close ekar/rood/pole

		// 5 = kaki persegi
		if (unitLuasAsal.equals("5")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAsal1) * 0.000009290304;
				sorDropdownUnitAsal = "1";
			} else {
				total = Double.parseDouble(luasAsal1) * 0.09290304;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "KAKI PERSEGI";

		}// close kaki persegi

		// 6 = ekar perpuluhan
		if (unitLuasAsal.equals("6")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAsal1) * 0.0001;
				sorDropdownUnitAsal = "1";
			} else {
				total = Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "EKAR PERPULUHAN";

		}// close ekar perpuluhan

		// 7 = ekar/depa
		if (unitLuasAsal.equals("7")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAsal1) + (Double
						.parseDouble(luasAsal2) / 1000)) * 0.404686;
				sorDropdownUnitAsal = "1";
			} else {
				total = (Double.parseDouble(luasAsal1) + (Double
						.parseDouble(luasAsal2) / 1000)) * 4046.86;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";

		}// close ekar/depa

		// 8 = relong/jemba/kaki persegi
		if (unitLuasAsal.equals("8")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAsal1)
						+ (Double.parseDouble(luasAsal2) / 484) + (Double
						.parseDouble(luasAsal3) / 30976)) * 0.711111 * 0.404686;
				sorDropdownUnitAsal = "1";
			} else {
				total = (Double.parseDouble(luasAsal1)
						+ (Double.parseDouble(luasAsal2) / 484) + (Double
						.parseDouble(luasAsal3) / 30976)) * 0.711111 * 4046.86;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";

		}// close relong/jemba/kaki persegi

		// 9 = batu nautika
		if (unitLuasAsal.equals("9")) {
			if (id_kategoritanah.equals("2")) {
				total = Double.parseDouble(luasAsal1) * 0.0001;
				sorDropdownUnitAsal = "1";
			} else {
				total = Double.parseDouble(luasAsal1) * 1;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "BATU NAUTIKA";

		}// close batu nautika

		// put data luas asal
		context.put("txtLuasLotAsal", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAsal", sorDropdownUnitAsal);
		context.put("txtLuasLotAsalSebelumConvert", luasAsal1 + " "
				+ unitSebelumConvert1 + " " + luasAsal2 + " "
				+ unitSebelumConvert2 + " " + luasAsal3 + " "
				+ unitSebelumConvert3);
		context.put("showBoxAsal2", "no");
		context.put("showBoxAsal3", "no");
		context.put("showButtonConvertAsal", "no");

	}// close calculateNilaiAsal

	private void validationConvertLuas() throws Exception {

		String socUnitLuasLot = getParam("socUnitLuasLot");
		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");
		String showBoxAmbil2 = getParam("showBoxAmbil2");
		String showBoxAmbil3 = getParam("showBoxAmbil3");

		if (socUnitLuasLot != "") {
			if (socUnitLuasLot.equals("4") || socUnitLuasLot.equals("8")) {
				context.put("showBoxAsal2", "yes");
				context.put("showBoxAsal3", "yes");
			} else if (socUnitLuasLot.equals("7")) {
				context.put("showBoxAsal2", "yes");
				context.put("showBoxAsal3", "no");
			} else {
				context.put("showBoxAsal2", "no");
				context.put("showBoxAsal3", "no");
			}
		} else {
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}

		/* Validation button convert Asal */
		if (!socUnitLuasLot.isEmpty()) {
			context.put("showButtonConvertAsal", "yes");
		} else {
			context.put("showButtonConvertAsal", "no");
		}

		/* Validation button convert Ambil */
		if (showButtonConvertAmbil.equals("yes")) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		if (showBoxAmbil3.equals("yes")) {
			context.put("showBoxAmbil2", "yes");
			context.put("showBoxAmbil3", "yes");
		} else if (showBoxAmbil2.equals("yes")) {
			context.put("showBoxAmbil2", "yes");
			context.put("showBoxAmbil3", "no");
		} else {
			context.put("showBoxAmbil2", "no");
			context.put("showBoxAmbil3", "no");
		}

		// validation convert
		String showFieldAmbilBeforeConvert = getParam("showFieldAmbilBeforeConvert");
		if (showFieldAmbilBeforeConvert.equals("yes")) {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

	}// close validationConvertLuas

	@SuppressWarnings("unchecked")
	private void getAndSetHM(String idpermohonan, String mode,
			String id_projekNegeri) throws Exception {

		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();

		if (mode.equals("new")) {

			if (id_projekNegeri.equals("10")) {
				context.put(
						"selectJenisHakmilik",
						HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",
								Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
			} else {
				context.put(
						"selectJenisHakmilik",
						HTML.SelectJenisHakmilik("socJenisHakmilik",
								Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
			}

			// dropdown unit luas
			context.put(
					"selectUnitLuasLot",
					HTML.SelectLuas("socUnitLuasLot",
							Utils.parseLong(getParam("socUnitLuasLot")),
							"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
			context.put(
					"selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil",
							Utils.parseLong(getParam("socUnitLuasAmbil")),
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		} else {

			if (id_projekNegeri.equals("10")) {
				context.put(
						"selectJenisHakmilik",
						HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",
								Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchangeUpdate()"));
			} else {
				context.put(
						"selectJenisHakmilik",
						HTML.SelectJenisHakmilik("socJenisHakmilik",
								Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchangeUpdate()"));
			}

			// dropdown unit luas
			context.put(
					"selectUnitLuasLot",
					HTML.SelectLuas("socUnitLuasLot",
							Utils.parseLong(getParam("socUnitLuasLot")),
							"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
			context.put(
					"selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil",
							Utils.parseLong(getParam("socUnitLuasAmbil")),
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}

		String id_daerah = "";
		modelUPT.setListPohon(idpermohonan);
		dataPermohonan = modelUPT.getListPohon();
		if (dataPermohonan.size() != 0) {
			Hashtable dp = (Hashtable) dataPermohonan.get(0);
			id_daerah = dp.get("idDaerah").toString();
		}

		// untuk kelantan shj
		context.put("SelectDaerahPenggawa", HTML.SelectDaerahPenggawa(
				"socDaerahPenggawa",
				Utils.parseLong(getParam("socDaerahPenggawa")), null,
				"style=width:274px"));

		// dropdown
		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah",
				Utils.parseLong(getParam("socKategoriTanah")),
				"id=socKategoriTanah style=width:auto", null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot",
				Utils.parseLong(getParam("socKodLot")), "style=width:auto"));

		// dropdown by
		if (id_daerah != "") {
			context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(id_daerah,
					"socMukim", Utils.parseLong(getParam("socMukim")),
					"style=width:auto"));
		} else {
			context.put("selectMukim", HTML.SelectMukimNoKod("socMukim",
					Utils.parseLong(getParam("socMukim")), "style=width:auto"));
		}

		// data
		context.put("txtSeksyen", getParam("txtSeksyen"));
		if (getParam("socJenisHakmilik").equals("116")) {
			context.put("txtNoHakmilik", "RH");
		} else {
			context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		}

		String resetRadio = getParam("resetRadio");

		context.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
		context.put("txdTarikhLuput", getParam("txdTarikhLuput"));
		context.put("txtBakiTempoh", getParam("txtBakiTempoh"));
		context.put("txtNoSyit", getParam("txtNoSyit"));
		context.put("txtNoPT", getParam("txtNoPT"));
		context.put("txtNoLot", getParam("txtNoLot"));
		context.put("txtLuasLotAsal", getParam("txtLuasLotAsal"));
		context.put("txtLuasLotAsal2", getParam("txtLuasLotAsal2"));
		context.put("txtLuasLotAsal3", getParam("txtLuasLotAsal3"));
		context.put("txtLuasLotAmbil", getParam("txtLuasLotAmbil"));
		context.put("txtLuasLotAmbil", getParam("txtLuasLotAmbil"));
		context.put("txtLuasLotAmbil2", getParam("txtLuasLotAmbil2"));
		context.put("txtLuasLotAmbil3", getParam("txtLuasLotAmbil3"));

		if (resetRadio.equals("1")) {
			context.put("sorJenisRizab", "");
		} else {
			context.put("sorJenisRizab", getParam("sorJenisRizab"));
		}

		context.put("txtLain", getParam("txtLain"));
		context.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		context.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		context.put("txtCatatan", getParam("txtCatatan"));

		context.put("txtLokasi", getParam("txtLokasi"));
		context.put("txtSyaratNyata", getParam("txtSyaratNyata"));
		context.put("txtSyaratKhas", getParam("txtSyaratKhas"));
		context.put("txtSekatanKepentingan", getParam("txtSekatanKepentingan"));
		context.put("txtSekatanHak", getParam("txtSekatanHak"));

		context.put("txtLuasLotAsalSebelumConvert",
				getParam("txtLuasLotAsalSebelumConvert"));
		context.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));

		context.put("txtLuasLotAmbilSebelumConvert",
				getParam("txtLuasLotAmbilSebelumConvert"));
		context.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));

	}// close getAndSetHM

	private void checkValConvert() throws Exception {

		// validation convert
		String showFieldAsalBeforeConvert = getParam("showFieldAsalBeforeConvert");
		String showFieldAmbilBeforeConvert = getParam("showFieldAmbilBeforeConvert");
		String showButtonConvertAsal = getParam("showButtonConvertAsal");
		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");
		String showBoxAsal2 = getParam("showBoxAsal2");
		String showBoxAsal3 = getParam("showBoxAsal3");
		String showBoxAmbil2 = getParam("showBoxAmbil2");
		String showBoxAmbil3 = getParam("showBoxAmbil3");

		if (showFieldAsalBeforeConvert.equals("yes")) {
			context.put("showFieldAsalBeforeConvert", "yes");
			context.put("showDropdownUnitAsal", "yes");
		} else {
			context.put("showFieldAsalBeforeConvert", "no");
			context.put("showDropdownUnitAsal", "no");
		}

		if (showFieldAmbilBeforeConvert.equals("yes")) {
			context.put("showFieldAmbilBeforeConvert", "yes");
			context.put("showDropdownUnitAmbil", "yes");
		} else {
			context.put("showFieldAmbilBeforeConvert", "no");
			context.put("showDropdownUnitAmbil", "no");
		}

		/* Validation button convert Asal */
		if (showButtonConvertAsal.equals("yes")) {
			context.put("showButtonConvertAsal", "yes");
		} else {
			context.put("showButtonConvertAsal", "no");
		}

		/* Validation button convert Ambil */
		if (showButtonConvertAmbil.equals("yes")) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		if (showBoxAsal3.equals("yes")) {
			context.put("showBoxAsal2", "yes");
			context.put("showBoxAsal3", "yes");
		} else if (showBoxAsal2.equals("yes")) {
			context.put("showBoxAsal2", "yes");
			context.put("showBoxAsal3", "no");
		} else {
			context.put("showBoxAsal2", "no");
			context.put("showBoxAsal3", "no");
		}

		if (showBoxAmbil3.equals("yes")) {
			context.put("showBoxAmbil2", "yes");
			context.put("showBoxAmbil3", "yes");
		} else if (showBoxAmbil2.equals("yes")) {
			context.put("showBoxAmbil2", "yes");
			context.put("showBoxAmbil3", "no");
		} else {
			context.put("showBoxAmbil2", "no");
			context.put("showBoxAmbil3", "no");
		}

	}// close checkValConvert

	private void checkValOnChange() throws Exception {

		String resetRadio = getParam("resetRadio");

		// validation jenis hakmilik & jenis rizab
		String id_jenisHakmilik = getParam("socJenisHakmilik");
		String sorJenisRizab = getParam("sorJenisRizab");

		/* validation jenis hakmilik */
		if (id_jenisHakmilik.equals("2") || id_jenisHakmilik.equals("5")
				|| id_jenisHakmilik.equals("28")
				|| id_jenisHakmilik.equals("73")
				|| id_jenisHakmilik.equals("74")
				|| id_jenisHakmilik.equals("75")
				|| id_jenisHakmilik.equals("80")
				|| id_jenisHakmilik.equals("85")
				|| id_jenisHakmilik.equals("113")) {
			context.put("showLuput", "yes");
		} else {
			context.put("showLuput", "no");
		}

		if (resetRadio.equals("1")) {
			context.put("showWarta", "no");
			context.put("showLainLain", "no");
		} else {
			/* validation jenis rizab */
			if (sorJenisRizab.equals("1")) {
				context.put("showWarta", "yes");
				context.put("showLainLain", "no");
			} else if (sorJenisRizab.equals("5")) {
				context.put("showWarta", "no");
				context.put("showLainLain", "yes");
			} else {
				context.put("showWarta", "no");
				context.put("showLainLain", "no");
			}
		}

	}// close checkValOnChange

	private void clearValueHM() throws Exception {

		context.put("txtLokasi", "");
		context.put("txtSyaratNyata", "");
		context.put("txtSyaratKhas", "");
		context.put("txtSekatanKepentingan", "");
		context.put("txtSekatanHak", "");

		context.put("txtSeksyen", "");
		context.put("txtNoHakmilik", "");
		context.put("txdTarikhDaftar", "");
		context.put("txdTarikhLuput", "");
		context.put("txtBakiTempoh", "");
		context.put("txtNoSyit", "");
		context.put("txtNoPT", "");
		context.put("txtNoLot", "");
		context.put("txtLuasLotAsal", "");
		context.put("txtLuasLotAsal2", "");
		context.put("txtLuasLotAsal3", "");
		context.put("txtLuasLotAmbil", "");
		context.put("txtLuasLotAmbil2", "");
		context.put("txtLuasLotAmbil3", "");
		context.put("sorJenisRizab", "");
		context.put("txtLain", "");
		context.put("txtNoWartaRizab", "");
		context.put("txdTarikhWarta", "");
		context.put("txtCatatan", "");

		context.put("txtLuasLotAsalSebelumConvert", "");
		context.put("sorDropdownUnitAsal", "");

		context.put("txtLuasLotAmbilSebelumConvert", "");
		context.put("sorDropdownUnitAmbil", "");

	}// close clearValueHM

	@SuppressWarnings("unchecked")
	private void newDataSetting(String idpermohonan) throws Exception {

		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();

		String id_daerah = "";
		String id_negeriprojek = "";
		String nama_daerah = "";
		String nama_negeriprojek = "";

		modelUPT.setListPohon(idpermohonan);
		dataPermohonan = modelUPT.getListPohon();
		if (dataPermohonan.size() != 0) {
			Hashtable dp = (Hashtable) dataPermohonan.get(0);
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
			nama_daerah = dp.get("daerah").toString();
			nama_negeriprojek = dp.get("nama_negeriprojek").toString();
		}

		context.put("id_daerah", id_daerah);
		context.put("id_negeriprojek", id_negeriprojek);
		context.put("nama_daerah", nama_daerah);
		context.put("nama_negeriprojek", nama_negeriprojek);

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		// untuk kelantan shj
		context.put("SelectDaerahPenggawa", HTML.SelectDaerahPenggawa(
				"socDaerahPenggawa", null, null, "style=width:274px"));

		// dropdown
		if (id_negeriprojek.equals("10")) {
			context.put(
					"selectJenisHakmilik",
					HTML.SelectJenisHakmilikSelangor("socJenisHakmilik", null,
							"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
		} else {
			context.put(
					"selectJenisHakmilik",
					HTML.SelectJenisHakmilik("socJenisHakmilik", null,
							"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
		}

		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah", null,
				"id=socKategoriTanah style=width:auto", null));
		context.put("selectKodLot",
				HTML.SelectUnitPT("socKodLot", null, "style=width:auto"));

		// dropdown unit luas
		context.put(
				"selectUnitLuasLot",
				HTML.SelectLuas("socUnitLuasLot", null,
						"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		context.put(
				"selectUnitLuasAmbil",
				HTML.SelectLuas("socUnitLuasAmbil", null,
						"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));

		// dropdown by
		if (id_daerah != "") {
			context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(id_daerah,
					"socMukim", null, "style=width:auto"));
		} else {
			context.put("selectMukim",
					HTML.SelectMukimNoKod("socMukim", null, "style=width:auto"));
		}

	}// close newDataSetting

	@SuppressWarnings("unchecked")
	private void hantar(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8HakmilikData.hantar(h);

	}// close hantar

	private void resetDataPB() throws Exception {

		context.put("txtNama", "");
		context.put("txtNoPB", "");
		context.put("txtJenisPB", "");
		context.put("txtSyorAtas", "");
		context.put("txtSyorBawah", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txtNoTelefon", "");
		context.put("txtNoBimbit", "");
		context.put("txtNoFaks", "");

		context.put("txtNamaBank", "");
		context.put("txtNoAkaun", "");

	}// close resetDataPB

	private void ListCarianHM(HttpSession session, String userIdNeg)
			throws Exception {

		String nofail = getParam("nofail");
		String tarikh = getParam("tarikh_permohonan");
		String status = getParam("carianStatus");

		context.put("nofail", nofail.trim());
		context.put("carianTarikh", tarikh.trim());
		context.put("carianStatus", status);

		FrmUPTSek8HakmilikData.setListCarian(nofail, tarikh, status, userIdNeg);

	}// close listcarian

	private void janaSubjaket(HttpSession session, String idpermohonan)
			throws Exception {

		String[] listIdHM = request.getParameterValues("ListIdHM");
		String id_user = (String) session.getAttribute("_ekptg_user_id");

		if ((listIdHM != null)) {
			int bil = 0;
			for (int i = 0; i < listIdHM.length; i++) {
				bil++;
				FrmUPTSek8HakmilikData.janaSubjaket(listIdHM[i], bil,
						idpermohonan, id_user);
			}
		}

	}// close janaSubjaket

	private void hapusHM(HttpSession session) throws Exception {

		String idHakmilik = getParam("id_hakmilik");

		Db db = null;
		String no_hakmilik_temp = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " SELECT NO_HAKMILIK FROM TBLPPTHAKMILIK " + ""
					+ " WHERE ID_HAKMILIK = '" + idHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("SQL  :" + sql);
			while (rs.next()) {
				no_hakmilik_temp = rs.getString("NO_HAKMILIK");
			}
			AuditTrail at = new AuditTrail();
			at.logActivity("", "1", null, session, "DEL", "HAKMILIK ["
					+ no_hakmilik_temp + "] DELETE");

		} finally {
			if (db != null)
				db.close();
		}

		FrmUPTSek8HakmilikData.hapusHM(idHakmilik);

	}// close hapusHM

	private void hapusBebanan(HttpSession session) throws Exception {

		String id_bebanan = getParam("id_bebanan");

		FrmUPTSek8HakmilikData.hapusBebanan(id_bebanan);

	}// close hapusHM

	@SuppressWarnings("unchecked")
	private void simpanHM(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String flagSubjaket = getParam("flag_subjaket");

		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtSyaratNyata", getParam("txtSyaratNyata"));
		h.put("txtSyaratKhas", getParam("txtSyaratKhas"));
		h.put("txtSekatanKepentingan", getParam("txtSekatanKepentingan"));
		h.put("txtSekatanHak", getParam("txtSekatanHak"));

		h.put("socDaerahPenggawa", getParam("socDaerahPenggawa"));
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_negeriProjek", getParam("id_negeriprojek"));
		h.put("id_daerahProjek", getParam("id_daerah"));
		h.put("jenisHakMilik", getParam("socJenisHakmilik"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txdTarikhLuput", getParam("txdTarikhLuput"));
		h.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
		h.put("txtBakiTempoh", getParam("txtBakiTempoh"));
		h.put("socKategoriTanah", getParam("socKategoriTanah"));
		h.put("socMukim", getParam("socMukim"));
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("kodLot", getParam("socKodLot"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txtNoPT", getParam("txtNoPT"));
		h.put("txtLuasAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("txtLuasAmbil", Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtseksyen", getParam("txtSeksyen"));

		// PPT-03 
		h.put("txtNoBangunan", getParam("txtNoBangunan"));
		h.put("txtNoTingkat", getParam("txtNoTingkat"));
		h.put("txtNoPetak", getParam("txtNoPetak"));

		h.put("unitLuas", getParam("socUnitLuasLot"));
		h.put("unitLuasAmbil", getParam("socUnitLuasAmbil"));
		h.put("txtLuasLotAsalSebelumConvert",
				getParam("txtLuasLotAsalSebelumConvert"));
		h.put("txtLuasLotAmbilSebelumConvert",
				getParam("txtLuasLotAmbilSebelumConvert"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));

		// rizab
		h.put("sorJenisRizab", getParam("sorJenisRizab"));
		h.put("txtLain", getParam("txtLain"));
		h.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));

		// pengambilan segera
		h.put("socPSegera", getParam("socPSegera"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8HakmilikData.simpanHM(h, flagSubjaket);

		/*
		 * Db db = null; String no_fail_temp = ""; try { db = new Db();
		 * Statement stmt = db.getStatement(); String sql =
		 * "SELECT F.NO_FAIL FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = '"
		 * +getParam("id_permohonan")+"'"; ResultSet rs =
		 * stmt.executeQuery(sql); myLogger.info("SQL  :"+sql); while
		 * (rs.next()){ no_fail_temp = rs.getString("NO_FAIL"); }
		 */
		AuditTrail at = new AuditTrail();
		at.logActivity("", "1", null, session, "INS", "HAKMILIK ["
				+ getParam("txtNoHakmilik") + "] INSERT");
		/*
		 * } finally { if (db != null) db.close(); }
		 */

	}// close simpanHM

	@SuppressWarnings("unchecked")
	private void updateHM(HttpSession session, String idHakmilik,
			String id_projekDaerah) throws Exception {

		Hashtable h = new Hashtable();

		h.put("txtLokasi", getParam("txtLokasi"));
		h.put("txtSyaratNyata", getParam("txtSyaratNyata"));
		h.put("txtSyaratKhas", getParam("txtSyaratKhas"));
		h.put("txtSekatanKepentingan", getParam("txtSekatanKepentingan"));
		h.put("txtSekatanHak", getParam("txtSekatanHak"));

		h.put("id_hakmilik", idHakmilik);
		h.put("id_daerah", id_projekDaerah);

		h.put("socDaerahPenggawa", getParam("socDaerahPenggawa"));
		h.put("jenisHakMilik", getParam("socJenisHakmilik"));
		h.put("txtNoHakmilik", getParam("txtNoHakmilik"));
		h.put("txdTarikhLuput", getParam("txdTarikhLuput"));
		h.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
		h.put("txtBakiTempoh", getParam("txtBakiTempoh"));
		h.put("socKategoriTanah", getParam("socKategoriTanah"));
		h.put("socMukim", getParam("socMukim"));
		h.put("txtNoSyit", getParam("txtNoSyit"));
		h.put("kodLot", getParam("socKodLot"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txtNoPT", getParam("txtNoPT"));
		h.put("txtLuasAsal", Utils.RemoveSymbol(getParam("txtLuasLotAsal")));
		h.put("txtLuasAmbil", Utils.RemoveSymbol(getParam("txtLuasLotAmbil")));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtseksyen", getParam("txtSeksyen"));

		// PPT-03 
		h.put("txtNoBangunan", getParam("txtNoBangunan"));
		h.put("txtNoTingkat", getParam("txtNoTingkat"));
		h.put("txtNoPetak", getParam("txtNoPetak"));

		h.put("unitLuas", getParam("socUnitLuasLot"));
		h.put("unitLuasAmbil", getParam("socUnitLuasAmbil"));
		h.put("txtLuasLotAsalSebelumConvert",
				getParam("txtLuasLotAsalSebelumConvert"));
		h.put("txtLuasLotAmbilSebelumConvert",
				getParam("txtLuasLotAmbilSebelumConvert"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));

		// rizab
		h.put("sorJenisRizab", getParam("sorJenisRizab"));
		h.put("txtLain", getParam("txtLain"));
		h.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// pengambilan segera
		h.put("socPSegera", getParam("socPSegera"));

		FrmUPTSek8HakmilikData.updateHM(h);

	}// close updateHM

	@SuppressWarnings("unchecked")
	private void simpanPB(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("id_hakmilikpb", "");

		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("socJenisPB", getParam("socJenisPB"));
		h.put("socBangsa", getParam("socBangsa"));
		h.put("socWarga", getParam("socWarga"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtJenisPB", getParam("txtJenisPB"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtNoTelefon", getParam("txtNoTelefon"));
		h.put("txtNoBimbit", getParam("txtNoBimbit"));
		h.put("txtNoFaks", getParam("txtNoFaks"));

		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txtNoAkaun", getParam("txtNoAkaun"));

		// for tblpptbahagianpb
		h.put("txtSyorAtas", getParam("txtSyorAtas"));
		h.put("txtSyorBawah", getParam("txtSyorBawah"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// String idExistPB = getParam("idExistPB");
		String id_hakmilikpb = DB.getNextID("TBLPPTHAKMILIKPB_SEQ") + "";

		String id_exist_bahagianpb = getParam("socBahagianPB");

		String id_bahagianpb = "";

		if (getParam("txtSyorAtas") != "" && getParam("txtSyorBawah") != "") {
			if (id_exist_bahagianpb != "") {
				id_bahagianpb = id_exist_bahagianpb;
				FrmUPTSek8HakmilikData.updateBahagianPB(h, id_bahagianpb);
			} else {
				id_bahagianpb = String.valueOf(DB
						.getNextID("TBLPPTBAHAGIANPB_SEQ"));
				FrmUPTSek8HakmilikData.simpanBahagianPB(h, id_bahagianpb);
			}
		}

		/*
		 * if(idExistPB!=""){ h.put("id_pihakberkepentingan", idExistPB);
		 * FrmUPTSek8HakmilikData.updatePB(h,"updateOLD","");
		 * FrmUPTSek8HakmilikData.simpanExistPB(h,id_hakmilikpb,id_bahagianpb);
		 * }else{
		 */long id_pihakberkepentingan = DB
				.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
		FrmUPTSek8HakmilikData.simpanPB(h, id_pihakberkepentingan,
				id_hakmilikpb, id_bahagianpb);
		// }

	}// close simpanPB

	@SuppressWarnings("unchecked")
	private void updatePB(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_pihakberkepentingan", getParam("id_pihakberkepentingan"));

		h.put("id_hakmilikpb", getParam("id_hakmilikpb"));

		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("socJenisPB", getParam("socJenisPB"));
		h.put("socBangsa", getParam("socBangsa"));
		h.put("socWarga", getParam("socWarga"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtJenisPB", getParam("txtJenisPB"));
		h.put("txtSyorAtas", getParam("txtSyorAtas"));
		h.put("txtSyorBawah", getParam("txtSyorBawah"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("txtNoTelefon", getParam("txtNoTelefon"));
		h.put("txtNoBimbit", getParam("txtNoBimbit"));
		h.put("txtNoFaks", getParam("txtNoFaks"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txtNoAkaun", getParam("txtNoAkaun"));

		String id_exist_bahagianpb = getParam("socBahagianPB");

		String id_bahagianpb = "";

		if (getParam("txtSyorAtas") != "" && getParam("txtSyorBawah") != "") {
			if (id_exist_bahagianpb != "") {
				id_bahagianpb = id_exist_bahagianpb;
				FrmUPTSek8HakmilikData.updateBahagianPB(h, id_bahagianpb);
			} else {
				id_bahagianpb = DB.getNextID("TBLPPTBAHAGIANPB_SEQ") + "";
				FrmUPTSek8HakmilikData.simpanBahagianPB(h, id_bahagianpb);
			}
		}

		FrmUPTSek8HakmilikData.updatePB(h, "update", id_bahagianpb);

	}// close updatePB

	private void hapusPB(HttpSession session) throws Exception {

		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilik = getParam("id_hakmilik");

		FrmUPTSek8HakmilikData.hapusPB(id_pihakberkepentingan, id_hakmilik);

	}// close hapusPB

	@SuppressWarnings("unchecked")
	private void simpanBebanan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_hakmilik", getParam("id_hakmilik"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("socJenisBebanan", getParam("socJenisBebanan"));
		h.put("keterangan_bebanan", getParam("keterangan_bebanan"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8HakmilikData.simpanBebanan(h);

	}// close simpanBebanan

	@SuppressWarnings("unchecked")
	private void updateBebanan(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_bebanan", getParam("id_bebanan"));

		h.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));
		h.put("txtPerserahan", getParam("txtPerserahan"));
		h.put("txtNama", getParam("txtNama"));
		h.put("socJenisNoPB", getParam("socJenisNoPB"));
		h.put("txtNoPB", getParam("txtNoPB"));
		h.put("txtAlamat1", getParam("txtAlamat1"));
		h.put("txtAlamat2", getParam("txtAlamat2"));
		h.put("txtAlamat3", getParam("txtAlamat3"));
		h.put("txtPoskod", getParam("txtPoskod"));
		h.put("socNegeri", getParam("socNegeri"));
		h.put("socBandar", getParam("socBandar"));
		h.put("socJenisBebanan", getParam("socJenisBebanan"));
		h.put("keterangan_bebanan", getParam("keterangan_bebanan"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		FrmUPTSek8HakmilikData.updateBebanan(h);

	}// close simpanBebanan

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
			this.context.put("listPermohonanHM", paging.getPage(page));
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

	@SuppressWarnings("unchecked")
	private void listHakmilik(HttpSession session, String idpermohonan,
			String noLOT) throws Exception {

		String idpegawai = "";

		// list maklumat tanah
		// modelUPT.setListMaklumatTanahAcceptPenarikan_listHakmilik(idpermohonan,noLOT,idpegawai);
		// Vector listMaklumatTanahAcceptPenarikan =
		// modelUPT.getListMaklumatTanahAcceptPenarikan();
		// context.put("listMaklumatTanah", listMaklumatTanahAcceptPenarikan);
		// context.put("saiz_listTanah",
		// listMaklumatTanahAcceptPenarikan.size());
		context.put("saiz_listTanah", modelUPT.setListMaklumatTanah_count(
				idpermohonan, noLOT, idpegawai));

	}// close dataTanah

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalSyer(String idHakmilik, String idpb) throws Exception {

		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		model.setTotalSyer(idHakmilik, idpb);
		totalSyer = model.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}
		context.put("totalSyer", total);
		context.put("hideAdd", hideAdd);

	}// close getTotalSyer

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPB(HttpSession session, String idHakmilik, String namaPB)
			throws Exception {

		Vector listPB = new Vector();
		listPB.clear();

		model.setListPB(idHakmilik, namaPB);
		listPB = model.getListPB();
		context.put("saiz_pb", listPB.size());
		context.put("listMaklumatPB", listPB);

	}// close listPB

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBebanan(HttpSession session, String idHakmilik,
			String noSerah) throws Exception {

		Vector listBebanan = new Vector();
		listBebanan.clear();

		model.listBebanan(idHakmilik, noSerah);
		listBebanan = model.getListBebanan();
		context.put("listBebanan", listBebanan);
		context.put("saiz_bebanan", listBebanan.size());

	}// close listBebanan

	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataPBVIEW(HttpSession session, String idPB, String idHakmilik)
			throws Exception {

		Vector dataDetailPB = new Vector();
		dataDetailPB.clear();

		model.setDataDetailPB(idPB, idHakmilik, 0);
		dataDetailPB = model.getDataDetailPB();
		String id_jenisNoPB = "";
		String id_jenisPB = "";
		String id_bangsa = "";
		String id_warganegara = "";
		String id_negeri = "";
		String id_bandar = "";
		String id_bahagianpb = "";
		if (dataDetailPB.size() != 0) {
			Hashtable pb = (Hashtable) dataDetailPB.get(0);
			id_jenisNoPB = pb.get("id_jenisnopb").toString();
			id_jenisPB = pb.get("id_jenispb").toString();
			id_bangsa = pb.get("id_bangsa").toString();
			id_warganegara = pb.get("id_warganegara").toString();
			id_negeri = pb.get("id_negeri").toString();
			id_bandar = pb.get("id_bandar").toString();
			id_bahagianpb = pb.get("id_bahagianpb").toString();
		}

		context.put("dataDetailPB", dataDetailPB);

		// dropdown (view)
		context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB",
				Utils.parseLong(id_jenisNoPB),
				"style=width:auto class=disabled disabled"));
		context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB",
				Utils.parseLong(id_jenisPB),
				"style=width:300px class=disabled disabled"));
		context.put("selectBangsa", HTML.SelectBangsa("socBangsa",
				Utils.parseLong(id_bangsa),
				"style=width:300px class=disabled disabled"));
		context.put("selectWarga", HTML.SelectWarganegara("socWarga",
				Utils.parseLong(id_warganegara),
				"style=width:auto class=disabled disabled"));
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
				Utils.parseLong(id_negeri),
				"style=width:300px class=disabled disabled"));
		context.put("selectBandar", HTML.SelectBandar("socBandar",
				Utils.parseLong(id_bandar),
				"style=width:300px class=disabled disabled"));

		// dropdown bahagian
		context.put(
				"selectBahagianPB",
				HTML.SelectBahagianPBbyHakmilik(
						idHakmilik,
						"socBahagianPB",
						Utils.parseLong(id_bahagianpb),
						null,
						" class=disabled disabled style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

	}// close dataPBVIEW

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDetailExistPB(HttpSession session, String idPB,
			String idHakmilik) throws Exception {

		Vector dataDetailExistPB = new Vector();
		dataDetailExistPB.clear();

		model.setDataDetailPB(idPB, idHakmilik, 1);
		dataDetailExistPB = model.getDataDetailPB();
		String txtNama = "";
		String txtNoPB = "";
		String txtJenisPB = "";
		String txtSyorAtas = "";
		String txtSyorBawah = "";
		String txtAlamat1 = "";
		String txtAlamat2 = "";
		String txtAlamat3 = "";
		String txtPoskod = "";
		String txtNoTelefon = "";
		String txtNoBimbit = "";
		String txtNoFaks = "";
		String id_jenisNoPB = "";
		String id_jenisPB = "";
		String id_bangsa = "";
		String id_warganegara = "";
		String id_negeri = "";
		String id_bandar = "";
		if (dataDetailExistPB.size() != 0) {
			Hashtable pb = (Hashtable) dataDetailExistPB.get(0);
			id_jenisNoPB = pb.get("id_jenisnopb").toString();
			id_jenisPB = pb.get("id_jenispb").toString();
			id_bangsa = pb.get("id_bangsa").toString();
			id_warganegara = pb.get("id_warganegara").toString();
			id_negeri = pb.get("id_negeri").toString();
			id_bandar = pb.get("id_bandar").toString();
			txtNama = pb.get("nama_pb").toString();
			txtNoPB = pb.get("no_pb").toString();
			txtJenisPB = pb.get("jenis_lain2_pb").toString();
			txtSyorAtas = pb.get("syer_atas").toString();
			txtSyorBawah = pb.get("syer_bawah").toString();
			txtAlamat1 = pb.get("alamat1").toString();
			txtAlamat2 = pb.get("alamat2").toString();
			txtAlamat3 = pb.get("alamat3").toString();
			txtPoskod = pb.get("poskod").toString();
			txtNoTelefon = pb.get("no_tel_rumah").toString();
			txtNoBimbit = pb.get("no_hp").toString();
			txtNoFaks = pb.get("no_fax").toString();
		}

		// dropdown (new)
		context.put(
				"selectJenisNoPB",
				HTML.SelectJenisNoPb("socJenisNoPB",
						Utils.parseLong(id_jenisNoPB), "style=width:auto"));
		context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB",
				Utils.parseLong(id_jenisPB), "style=width:300px"));
		context.put("selectBangsa", HTML.SelectBangsa("socBangsa",
				Utils.parseLong(id_bangsa), "style=width:300px"));
		context.put(
				"selectWarga",
				HTML.SelectWarganegara("socWarga",
						Utils.parseLong(id_warganegara), "style=width:auto"));
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
				Utils.parseLong(id_negeri), "style=width:300px"));

		if (id_negeri != "") {
			context.put(
					"selectBandar",
					HTML.SelectBandarByNegeri(id_negeri, "socBandar",
							Utils.parseLong(id_bandar), "style=width:300px"));
		} else {
			context.put("selectBandar", HTML.SelectBandar("socBandar",
					Utils.parseLong(id_bandar), "style=width:300px"));
		}

		// set exist data pb
		context.put("txtNama", txtNama);
		context.put("txtNoPB", txtNoPB);
		context.put("txtJenisPB", txtJenisPB);
		context.put("txtSyorAtas", txtSyorAtas);
		context.put("txtSyorBawah", txtSyorBawah);
		context.put("txtAlamat1", txtAlamat1);
		context.put("txtAlamat2", txtAlamat2);
		context.put("txtAlamat3", txtAlamat3);
		context.put("txtPoskod", txtPoskod);
		context.put("txtNoTelefon", txtNoTelefon);
		context.put("txtNoBimbit", txtNoBimbit);
		context.put("txtNoFaks", txtNoFaks);

	}// close dataPBVIEW

	@SuppressWarnings({ "unchecked", "static-access" })
	private String checkExistPB(HttpSession session) throws Exception {

		Vector checkExistPB = new Vector();
		checkExistPB.clear();

		String socJenisNoPB = getParam("socJenisNoPB");
		String noPB = getParam("txtNoPB");

		model.setCheckExistPB(socJenisNoPB, noPB);
		checkExistPB = model.getCheckExistPB();
		String idExistPB = "";
		if (checkExistPB.size() != 0) {
			Hashtable ce = (Hashtable) checkExistPB.get(0);
			idExistPB = (String) ce.get("id_pihakberkepentingan");
		}

		return idExistPB;

	}// close checkExistPB

	@SuppressWarnings("unchecked")
	private void dropdownHakmilikDISABLED(HttpSession session, String idHakmilik)
			throws Exception {

		// data & list maklumat tanah
		modelUPT.setMaklumatTanah(idHakmilik);
		Vector dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);
		context.put("saiz_dataTanah", dataMaklumatTanah.size());

		String id_mukim = "";
		String id_jenishakmilik = "";
		String id_luaslot = "";
		String id_lot = "";
		String id_kategoritanah = "";
		if (dataMaklumatTanah.size() != 0) {
			Hashtable h = (Hashtable) dataMaklumatTanah.get(0);
			id_mukim = h.get("id_mukim").toString();
			id_jenishakmilik = h.get("id_jenishakmilik").toString();
			id_luaslot = h.get("id_luasLot").toString();
			id_lot = h.get("id_lot").toString();
			id_kategoritanah = h.get("id_kategoritanah").toString();
		}

		// dropdown (view)
		context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik(
				"jenisHakMilik", Utils.parseLong(id_jenishakmilik),
				"style=width:auto class=disabled disabled"));
		context.put("selectUnitLuas", HTML.SelectLuas("unitLuas",
				Utils.parseLong(id_luaslot),
				"style=width:auto class=disabled disabled"));
		context.put("selectKodLot", HTML.SelectUnitPT("kodLot",
				Utils.parseLong(id_lot),
				"style=width:auto class=disabled disabled"));
		context.put("selectMukim", HTML.SelectMukimNoKod("socMukim",
				Utils.parseLong(id_mukim),
				"style=width:auto class=disabled disabled"));
		context.put("selectKategoriTanah", HTML.SelectKategoriTanah(
				"socKategoriTanah", Utils.parseLong(id_kategoritanah),
				"style=width:auto class=disabled disabled", null));

	}// close dropdownHakmilikDISABLED

	private void getAndSetDataPB(HttpSession session, String mode)
			throws Exception {

		String idNegeri = getParam("socNegeri");
		String idJenisNoPB = getParam("socJenisNoPB");
		String idJenisPB = getParam("socJenisPB");
		String idBangsa = getParam("socBangsa");
		String idWarga = getParam("socWarga");

		String txtNama = getParam("txtNama");
		String txtNoPB = getParam("txtNoPB");
		String txtJenisPB = getParam("txtJenisPB");
		String txtSyorAtas = getParam("txtSyorAtas");
		String txtSyorBawah = getParam("txtSyorBawah");
		String txtAlamat1 = getParam("txtAlamat1");
		String txtAlamat2 = getParam("txtAlamat2");
		String txtAlamat3 = getParam("txtAlamat3");
		String txtPoskod = getParam("txtPoskod");
		String txtNoTelefon = getParam("txtNoTelefon");
		String txtNoBimbit = getParam("txtNoBimbit");
		String txtNoFaks = getParam("txtNoFaks");

		String txtNamaBank = getParam("txtNamaBank");
		String txtNoAkaun = getParam("txtNoAkaun");

		// dropdown (new)
		context.put(
				"selectJenisNoPB",
				HTML.SelectJenisNoPb("socJenisNoPB",
						Utils.parseLong(idJenisNoPB), "style=width:auto"));
		context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB",
				Utils.parseLong(idJenisPB), "style=width:300px"));
		context.put("selectBangsa", HTML.SelectBangsa("socBangsa",
				Utils.parseLong(idBangsa), "style=width:300px"));
		context.put("selectWarga", HTML.SelectWarganegara("socWarga",
				Utils.parseLong(idWarga), "style=width:auto"));

		if (mode.equals("new")) {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
					Utils.parseLong(idNegeri), null,
					"style=width:300px onChange=\"onchangeNegeri();\""));
		} else {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
					Utils.parseLong(idNegeri), null,
					"style=width:300px onChange=\"onchangeNegeriUpdate();\""));
		}

		// set
		context.put("txtNama", txtNama);
		context.put("txtNoPB", txtNoPB);
		context.put("txtJenisPB", txtJenisPB);
		context.put("txtSyorAtas", txtSyorAtas);
		context.put("txtSyorBawah", txtSyorBawah);
		context.put("txtAlamat1", txtAlamat1);
		context.put("txtAlamat2", txtAlamat2);
		context.put("txtAlamat3", txtAlamat3);
		context.put("txtPoskod", txtPoskod);
		context.put("txtNoTelefon", txtNoTelefon);
		context.put("txtNoBimbit", txtNoBimbit);
		context.put("txtNoFaks", txtNoFaks);

		context.put("txtNamaBank", txtNamaBank);
		context.put("txtNoAkaun", txtNoAkaun);

	}// close getAndSetDataPB

	private void getAndSetDataBebanan(HttpSession session, String mode)
			throws Exception {

		String idNegeri = getParam("socNegeri");
		String idJenisNoPB = getParam("socJenisNoPB");
		String idJenisBebanan = getParam("socJenisBebanan");

		// dropdown (new)
		context.put(
				"selectJenisNoPB",
				HTML.SelectJenisNoPb("socJenisNoPB",
						Utils.parseLong(idJenisNoPB), "style=width:auto"));
		context.put(
				"selectBebanan",
				HTML.SelectBebanan("socJenisBebanan",
						Utils.parseLong(idJenisBebanan), "style=width:auto"));

		if (mode.equals("new")) {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
					Utils.parseLong(idNegeri), null,
					"style=width:300px onChange=\"onchangeNegeriBebanan();\""));
		} else {
			context.put(
					"selectNegeri",
					HTML.SelectNegeriMampu("socNegeri",
							Utils.parseLong(idNegeri), null,
							"style=width:300px onChange=\"onchangeNegeriBebananUpdate();\""));
		}

		// set
		context.put("txtPerserahan", getParam("txtPerserahan"));
		context.put("txtNama", getParam("txtNama"));
		context.put("txtNoPB", getParam("txtNoPB"));
		context.put("txtAlamat1", getParam("txtAlamat1"));
		context.put("txtAlamat2", getParam("txtAlamat2"));
		context.put("txtAlamat3", getParam("txtAlamat3"));
		context.put("txtPoskod", getParam("txtPoskod"));
		context.put("txdTarikhDaftar", getParam("txdTarikhDaftar"));

	}// close getAndSetDataBebanan

	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataBebanan(HttpSession session, String idBebanan)
			throws Exception {

		model.dataBebanan(idBebanan);
		Vector dataBebanan = model.getDataBebanan();
		context.put("dataBebanan", dataBebanan);
		String id_jenisnopb = "";
		String id_negeri = "";
		String id_bandar = "";
		String id_jenisbebanan = "";
		if (dataBebanan.size() != 0) {
			Hashtable db = (Hashtable) dataBebanan.get(0);
			id_jenisnopb = db.get("id_jenisnopb").toString();
			id_negeri = db.get("id_negeri").toString();
			id_bandar = db.get("id_bandar").toString();
			id_jenisbebanan = db.get("id_kodbebanan").toString();
		}

		// dropdown (disabled)
		context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB",
				Utils.parseLong(id_jenisnopb),
				"style=width:auto disabled class=disabled"));
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri",
				Utils.parseLong(id_negeri), null,
				"style=width:300px disabled class=disabled"));
		context.put("selectBandar", HTML.SelectBandar("socBandar",
				Utils.parseLong(id_bandar),
				"style=width:300px disabled class=disabled"));
		context.put("selectBebanan", HTML.SelectBebanan("socJenisBebanan",
				Utils.parseLong(id_jenisbebanan),
				"style=width:auto disabled class=disabled"));

	}// close dataBebanan

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalSyer_ALL(String idHakmilik, String idpb)
			throws Exception {

		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		model.setTotalSyer(idHakmilik, idpb);
		totalSyer = model.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}
		context.put("totalSyer", total);

	}// close getTotalSyer

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalSyer_Temp(String idHakmilik, String idpb) throws Exception {
		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		model.setTotalSyer(idHakmilik, idpb);
		totalSyer = model.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}

		if (total != "") {
			context.put("totalSyer", total);
		} else {
			context.put("totalSyer", 0);
		}

		// context.put("hideAdd", hideAdd);

		if (hideAdd == "yes") {
			// JOptionPane.showMessageDialog(null,
			// "Bahagian PB Telah Mencukupi.", "Etapp", 2);

		} else if (hideAdd == "notcomplete") {
			// JOptionPane.showMessageDialog(null,
			// "Bahagian PB Masih Tidak Mencukupi.", "Etapp", 2);
		}

	}// close getTotalSyer

	public void insertPopupReg(String nama_class, String tajuk_class,
			String group, Db db) throws Exception {
		// Db db = null;
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			String sql = " INSERT INTO MODULE ( "
					+ " MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "
					+ " MODULE_GROUP, MODULE_DESCRIPTION)  " + " VALUES ('"
					+ nama_class + "','" + tajuk_class + "','" + nama_class
					+ "','" + group + "','') ";
			myLogger.info("REG CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			sql = " INSERT INTO ROLE_MODULE ( "
					+ " MODULE_ID, USER_ROLE) "
					+ " SELECT '"
					+ nama_class
					+ "' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%'";
			myLogger.info("REG ROLE CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public int checkRegPopup(String class_name, Db db) throws Exception {

		// Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// db = new Db();
			sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '"
					+ class_name + "'";
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			// Close the database connection
			// if ( db != null ) db.close();
			// if (rs != null) rs.close();
		}
		return total;
	}

	public void checkFieldWujud(String table_name, String column_name,
			String data_type, Db db) throws Exception {

		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"
					+ table_name + "' AND COLUMN_NAME = '" + column_name + "' ";
			rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} finally {
		}

		if (total == 0) {
			sql = "ALTER TABLE " + table_name + " " + " ADD " + column_name
					+ " " + data_type + " ";
			rs = db.getStatement().executeQuery(sql);
			// ALTER TABLE supplier ADD supplier_name varchar2(50);
		}

	}
	//function email-penambahan yati 17/1/2016
		 public void hantarEmel(String content,String subjek,String nofail,
				 String nama_projek, String tarikh_permohonan, String nama_kementerian,String id_fail) throws Exception {
			 
				//Vector checkEmailPengarah = new Vector();
				//checkEmailPengarah.clear();
				
				//System.out.println("id dataListPengarah :::"+id_pengarah);

				// check email (pengarah)
				//checkEmailPengarah = myInfo.checkEmail(emel);
				//String emelPengarah = "";
				/*if (checkEmailPengarah.size() != 0) {
					Hashtable ceP = (Hashtable) checkEmailPengarah.get(0);
					emelPengarah = (String) ceP.get("EMEL");
				}
				*/
	
				//System.out.println("emel ::"+emel);
				
			 
				EmailProperty pro = EmailProperty.getInstance();
				//String EMAIL_HOST = pro.getHost_GM();
				EmailSender email = EmailSender.getInstance();
			
			//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
			//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
			email.FROM = pro.getFrom();
			email.SUBJECT = subjek;
			email.MESSAGE = "<html><table><tr><td>No.Fail</td><td>:</td><td>"+nofail+"</td></tr>" +
					"<tr><td>Nama Projek</td><td>:</td><td>"+nama_projek+"</td></tr>" +
					"<tr><td>Tarikh Permohonan</td><td>:</td><td>"+tarikh_permohonan+"</td></tr>" +
					"<tr><td>Nama Kementerian</td><td>:</td><td>"+nama_kementerian+"</td></tr>" +
					"<tr><td>&nbsp;</td><td>&nbsp;</td><td><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em></td></tr>" +
					"</table></html>" ;
			//email.RECIEPIENT = "norhayati@si-protech.com.my";
			//email.MULTIPLE_RECIEPIENT[i] = emel.get("emel").toString();
			
			
			
			//email.MULTIPLE_RECIEPIENT[i] = emelPengarah;
			//email.MULTIPLE_RECIEPIENT[1] = "";
			//email.MULTIPLE_RECIEPIENT[2] = "nik_rafizal@hla-group.com";
			//email.MULTIPLE_RECIEPIENT[3] = "noorazam@hla-group.com";
			Vector dataListPengarah = new Vector();
			dataListPengarah = modelUPT.setListPengarah(id_fail);
			email.MULTIPLE_RECIEPIENT = new String[dataListPengarah.size()];
			//email.MULTIPLE_RECIEPIENT = new String[2];
			
			for(int i = 0;i < dataListPengarah.size();i++){
				Hashtable nppp = null;
				String nama_pengarah = "";
				String emel = ""; 
				String idfail ="";
				
				nppp = (Hashtable) dataListPengarah.get(i);				
				nama_pengarah = (String) nppp.get("nama_pengarah").toString();
				idfail = (String) nppp.get("id_fail").toString();
				emel = (String) nppp.get("emel").toString();
				//myLogger.info("emel : "+emel+" i :"+i);
				email.MULTIPLE_RECIEPIENT[i] = emel;
				
			}
			//email.MULTIPLE_RECIEPIENT[0] = "cikyatiey@gmail.com";
			//email.MULTIPLE_RECIEPIENT[1] = "razman.zainal@gmail.com";
			
			myLogger.info(" ---------- email :"+email);	
			//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
			email.TO_CC = new String[1];		
			email.TO_CC[0] = "testingetapp@gmail.com";
			email.sendEmail();
			
		 }


		 
}// close class
