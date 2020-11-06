package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.rekod.FrmTanahKementerianBean;
import ekptg.model.htp.rekod.ITanahKementerian;
import ekptg.model.permohonan.IPermohonan;
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmPermohonanUPTOnlineData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.model.ppt.PPTHeader;
import ekptg.model.ppt.PPTPermohonanBean;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.utils.emel.EmailConfig;
import ekptg.model.utils.emel.EmelSemakanBean;
import ekptg.model.utils.emel.IEmel;
import ekptg.model.utils.rujukan.UtilHTMLPilihanJenisHakmilik;
import ekptg.view.ppt.email.EmailOnline;

public class FrmPermohonanUPTOnline extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(ekptg.view.ppt.FrmPermohonanUPTOnline.class);
	// model
	FrmPermohonanUPTData model = new FrmPermohonanUPTData();
	FrmPermohonanUPTOnlineData modelOnline = new FrmPermohonanUPTOnlineData();
	FrmPembatalanInternalData logic1 = new FrmPembatalanInternalData();
	PPTHeader header = new PPTHeader();
	FrmUPTSek8HakmilikData modelHM = new FrmUPTSek8HakmilikData();
	MyInfoPPTData myInfo = new MyInfoPPTData();
	private IPermohonan iPermohonan = null;
	private IUtilHTMLPilihan iPilihanJH = null;
	private ekptg.model.utils.emel.IEmel iEmel = null;

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception {

		try {
			HttpSession session = request.getSession();
			// command for pagings
			String action = getParam("action");
			// Utils helper
			UtilsItem();
			// default
			String vm = "";
			String noLOT = "";
			String idpegawai = "";
			String namaPB = "";
			String noSerah = "";
			String idExistPB = "";
			
			String statusPembayaran = getParam("idStatus");

			context.put("showJajahan", "no");
			context.put("onchange", "no");
			context.put("onchangeHM", "no");

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
			context.put("showSegera", "yes");
			context.put("hideFieldHakmilik", "no");
			context.put("showSave", "no");

			// default pb
			context.put("hideAdd", "no");
			context.put("PBexist", false);
			context.put("idExistPB", "");

			// tmbh
			context.put("listPermohonan", "");
			context.put("list_size", "");

			Vector listPageDepan = new Vector();
			Vector listDelete = new Vector();
			Vector senaraiSemak = new Vector();
			Vector getIdSuburusanstatusfail = new Vector();
			Vector dataMaklumatTanah = new Vector();
			Vector checkExistPBidHM = new Vector();
			Vector checkSizeBahagianPB = new Vector();
			Vector dataDetailPB = new Vector();
			Vector dataBebanan = new Vector();
			Vector listFailExpired = new Vector();
			Vector checkEmail = new Vector();

			checkEmail.clear();
			listFailExpired.clear();
			dataBebanan.clear();
			dataDetailPB.clear();
			checkSizeBahagianPB.clear();
			checkExistPBidHM.clear();
			dataMaklumatTanah.clear();
			getIdSuburusanstatusfail.clear();
			senaraiSemak.clear();
			listPageDepan.clear();
			listDelete.clear();

			/*
			 * Db dbx = null; try { dbx = new Db();
			 * 
			 * if (checkRegPopup("ekptg.view.ppt.SkrinPopupHakmilik", dbx) == 0) { // reg
			 * class insertPopupReg("ekptg.view.ppt.SkrinPopupHakmilik",
			 * "Skrin Salin Hakmilik", "EKPTG - PPT", dbx); } if
			 * (checkRegPopup("ekptg.view.ppt.SkrinPopupCarianHakmilik", dbx) == 0) { // reg
			 * class insertPopupReg("ekptg.view.ppt.SkrinPopupCarianHakmilik",
			 * "Skrin Capaian Hakmilik", "EKPTG - PPT", dbx); } // KJP if
			 * (checkRegPopup_KJP("ekptg.view.ppt.SkrinPopupCarianHakmilik", dbx) == 0) { //
			 * reg class insertPopupReg_KJP("ekptg.view.ppt.SkrinPopupCarianHakmilik",
			 * "Skrin Capaian Hakmilik", "EKPTG - PPT", dbx); } if
			 * (checkRegPopup_KJP("ekptg.view.ppt.SkrinPopupHakmilik", dbx) == 0) { // reg
			 * class insertPopupReg_KJP("ekptg.view.ppt.SkrinPopupHakmilik",
			 * "Skrin Salin Hakmilik", "EKPTG - PPT", dbx); } if
			 * (checkRegPopup_KJP("ekptg.view.ppt.SkrinPopupSalinPBHakmilik", dbx) == 0) {
			 * // reg class insertPopupReg_KJP("ekptg.view.ppt.SkrinPopupSalinPBHakmilik",
			 * "Skrin Capaian Salin Hakmilik", "EKPTG - PPT", dbx); }
			 * 
			 * } finally { if (dbx != null) dbx.close(); }
			 */

			// screen jsp
			String listdepan = "app/ppt/frmSenaraiPermohonanUPTOnline.jsp";
			String screenUtama = "app/ppt/frmUPTDaftarOnline.jsp";
			String screenTanah = "app/ppt/frmUPTHakmilikTambahOnline.jsp";
			String screenDokumen = "app/ppt/frmUPTDocTambahOnline.jsp";
			String screenPB = "app/ppt/frmUPTOnlinePB.jsp";
			String screenBebanan = "app/ppt/frmUPTOnlineBebanan.jsp";
			String screenSemakan = "app/ppt/frmUPTSenaraiSemakanOnline.jsp";

			this.context.put("maklumat_PB_Salin", "");
			this.context.put("id_hakmilikpb_salin", "");
			this.context.put("id_hakmilik_salin", "");
			this.context.put("maklumat_Hakmilik_Salin", "");

			// prevent duplicate when refresh page
			String doPost = (String) session.getAttribute("doPost");
			//System.out.println("doPost >>> " + doPost);
			String portal_role = (String) session.getAttribute("_portal_role");

			// anchor
			anchor();
			// paging
			paging();
			// tabbed
			selectedTab();
			String selectedTab = selectedTab();
			// user login id
			String id_user = (String) session.getAttribute("_ekptg_user_id");
			// get nama pengarah dan id negeri user
			nameAndId(id_user);
			String userIdKementerian = nameAndId(id_user);
			// get id jawatan
			String id_jawatan_user = getJawatan(id_user);
			context.put("id_jawatan_user", id_jawatan_user);

			// get email user login
			checkEmail = myInfo.checkEmail(id_user);
			String emelUser = "";
			if (checkEmail.size() != 0) {
				Hashtable ce = (Hashtable) checkEmail.get(0);
				emelUser = (String) ce.get("EMEL");
			}

			context.put("emelUser", emelUser);

			// default list
			// listPageDepan = modelOnline.getListPemohon(id_user, portal_role,"");

			// header
			String flag_semakan_online = "";
			String id_projekDaerah = "";
			String flagStatusOnline = "";
			String catatan_status_online = "";
			String id_status = "";
			String flagSegera = "";
			String id_fail = "";
			String no_fail = "";
			String flag_subjaket = "";
			String id_projekNegeri = "";
			String id_suburusan = "";
			String namaProjek = "";
			String TarikhPermohonan = "";
			String noPermohonan = "";
			String idpermohonan = getParam("id_permohonan");
			String id_kementerian = getParam("socKementerian");
			String id_jawatan = getParam("id_jawatan");
			Vector dataHeader = null;
			if (!idpermohonan.equals("")) {
				header.setDataHeader(idpermohonan);
				dataHeader = header.getDataHeader();
				context.put("dataHeader", dataHeader);
				if (dataHeader.size() != 0) {
					Hashtable dh = (Hashtable) dataHeader.get(0);
					id_status = (String) dh.get("id_status");
					flagSegera = dh.get("flag_segera").toString();
					id_fail = dh.get("id_fail").toString();
					no_fail = dh.get("no_fail").toString();
					flag_subjaket = dh.get("flag_subjaket").toString();
					id_projekNegeri = dh.get("id_projekNegeri").toString();
					id_suburusan = dh.get("id_suburusan").toString();
					flagStatusOnline = (String) dh.get("flag_status_online");
					catatan_status_online = (String) dh.get("catatan_status_online");
					id_projekDaerah = dh.get("id_projekDaerah").toString();
					flag_semakan_online = dh.get("flag_semakan_online").toString();
					namaProjek = dh.get("tujuan").toString();
					TarikhPermohonan = dh.get("tarikh_permohonan").toString();
					noPermohonan = String.valueOf(dh.get("no_permohonan"));
				}
			}
			// get fail tamat tempoh
			if (idpermohonan.equals("")) {
				listFailExpired = modelOnline.getListFailExpired(id_user);
				context.put("listFailExpired", listFailExpired);
				context.put("typeList", "online");
			}

			// id Hakmilik
			String idHakmilik = getParam("id_hakmilik");

			// default
			context.put("mode", "");
			context.put("isEdit", "");
			context.put("showPopupHantar", "no");

			String dateToday = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
			context.put("dateToday", dateToday);

			String submit = getParam("command");
			myLogger.info("submit : " + submit);
			
			listPageDepan = modelOnline.getListPemohon(id_user, portal_role, "");
			context.put("id_fail", id_fail);
			context.put("no_fail", no_fail);
			myLogger.info("no_fail======="+no_fail);
			myLogger.info("id_fail======="+id_fail);
			

			if ("pendaftaran".equals(submit)) {

				// form validation
				context.put("mode", "new");
				context.put("isEdit", "no");

				// reset value
				resetValue();

				// dropdown
				// Dikemaskini oleh Mohamad Rosli, Pilihan negeri tidak termasuk
				// JOHOR
				// context.put("selectNegeriProjek", HTML.SelectNegeri(
				// "socNegeriProjek", null, null,
				// "style=width:300px onChange=\"doChangeProjekNegeri();\""));
				context.put("selectNegeriProjek", SelectNegeriPengambilan("socNegeriProjek", null, null,
						"style=width:300px onChange=\"doChangeProjekNegeri();\""));
				context.put("selectKementerian",
						HTML.SelectKementerian("socKementerian", Utils.parseLong(userIdKementerian),
								"disabled class=disabled style=width:500px onChange=\"doChangeKementerian();\""));
				context.put("selectNegeri",
						HTML.SelectNegeriMampu("socNegeri", null, "class=disabled disabled style=width:325px"));

				if (userIdKementerian != "") {
					context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", userIdKementerian, null,
							"id=socAgensi style=width:500px"));
				} else {
					context.put("selectAgensi", HTML.SelectAgensi("socAgensi", null, "id=socAgensi style=width:500px"));
				}

				// get and set data
				getAndSetDataOnchangeKementerian(userIdKementerian, getParam("sorUrusan"), "new");

				// if(userIdNeg!=""){
				// context.put("selectDaerah",HTML.SelectDaerahByNegeri(userIdNeg,"socDaerah",null,null,"id=socDaerah
				// style=width:300px"));
				// }else{
				context.put("selectDaerah",
						HTML.SelectDaerah("socDaerah", null, null, "id=socDaerah style=width:300px"));
				// }

				// validation jajahan
				// if(userIdNeg.equals("3")){
				// context.put("showJajahan","yes");
				// }else{
				// context.put("showJajahan","no");
				// }

				String submit2 = getParam("command2");
				myLogger.info("submit[2] : " + submit2);

				if ("doChangeKementerian".equals(submit2)) {

					// String id_kementerian = getParam("socKementerian");
					String id_urusan = getParam("sorUrusan");

					// get and set data
					getAndSetDataOnchangeKementerian(id_kementerian, id_urusan, "new");

				} // close doChangeKementerian

				else if ("doChangeProjekNegeri".equals(submit2)) {

					String id_urusan = getParam("sorUrusan");
					String id_negeriprojek = getParam("socNegeriProjek");

					// get and set data
					getAndSetDataOnchangeNegeri(id_negeriprojek, id_urusan, "new", userIdKementerian);

				} // close doChangeProjekNegeri

				else if ("doChangeUrusan".equals(submit2)) {

					String id_urusan = getParam("sorUrusan");
					String id_negeriprojek = getParam("socNegeriProjek");

					// get and set data
					getAndSetDataOnchangeNegeri(id_negeriprojek, id_urusan, "new", userIdKementerian);

				} // close doChangeUrusan

				else if ("simpanPendaftaran".equals(submit2)) {

					String result = "";
					if (doPost.equals("true")) {
						// simpan data
						result = simpanPendaftaran(session, userIdKementerian);
					}

					context.put("ResultAdd", result);

					idpermohonan = result;

					// MAIN PAGE
					if (idpermohonan != "") {

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");
						context.put("showSave", "yes");

						// update status
						header.setDataHeader(idpermohonan);
						dataHeader = header.getDataHeader();
						context.put("dataHeader", dataHeader);
						if (dataHeader.size() != 0) {
							Hashtable dh = (Hashtable) dataHeader.get(0);
							id_status = (String) dh.get("id_status");
							id_suburusan = (String) dh.get("id_suburusan");
							flag_semakan_online = dh.get("flag_semakan_online").toString();
						}

						// senarai semak
						ListCheckBox(idpermohonan);
//						senaraiSemak = model.getSenaraiSemakan(idpermohonan);
//		        		context.put("senaraiSemakan", senaraiSemak);
						
						// data pendaftaran
						DataPermohonan(idpermohonan, "disabled");
						
						// list dokumen
						ListDokumen(idpermohonan);
						
						// list dokumen pembayaran
						ListDokumenPembayaran(idpermohonan);
						
						// list hakmilik
						ListHakmilik(idpermohonan, noLOT, idpegawai);
						
						// LIST DEPAN
					} else {
						String jawatan = request.getParameter("jawatan");
						// System.out.println("JAWATAN DARI MENU UTAMA : " +jawatan);
						listPageDepan = modelOnline.getListPemohon(id_user, portal_role, "");

						resetValueCarian();
						
						// screen
						context.put("jawatan", jawatan);
						vm = listdepan;
						return vm;

					} // validation after add

				} // close simpanPendaftaran

				// screen
				vm = screenUtama;

			} // close pendaftaran

			else if ("semakPendaftaran".equals(submit)) {

				context.remove("flag_semakan_online");
				context.remove("flag_status_online");

				String submit2 = getParam("command2");
				myLogger.info("submit[2] : " + submit2);

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// carian no lot dan pt
				noLOT = getParam("carianNoLot");
				context.put("carianNoLot", noLOT.trim());

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// senarai semak
				ListCheckBox(idpermohonan);

				// data pendaftaran
				DataPermohonan(idpermohonan, "disabled");

				// list dokumen
				ListDokumen(idpermohonan);
				
				// list dokumen pembayaran
				ListDokumenPembayaran(idpermohonan);

				// GO TO ULASAN JABATAN TEKNIKAL
				Vector listJabatanTeknikal = new Vector();
				listJabatanTeknikal.clear();

				listJabatanTeknikal = modelOnline.setJabatanTeknikal(idpermohonan);
				this.context.put("listJabatanTeknikal", listJabatanTeknikal);

				if ("hantarSemakan".equals(submit2)) {

					// update flag
					updateFlag(session, "1");

					if (doPost.equals("true")) {
						sendEmail(namaProjek, TarikhPermohonan, userIdKementerian, id_jawatan_user, id_user,
								"hantarSemakan",session);
					}

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_semakan_online = dh.get("flag_semakan_online").toString();
						// flag_semakan_online = dh.get("flag_status_online").toString();
					}

				} else if ("sahSemakan".equals(submit2)) {

					// update flag
					updateFlag(session, "2");

					if (doPost.equals("true")) {
						sendEmail(namaProjek, TarikhPermohonan, userIdKementerian, id_jawatan_user, id_user,
								"hantarLulus",session);
					}
					//senarai semak
		    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
				    context.put("senaraiSemakan", senaraiSemak);

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_semakan_online = dh.get("flag_semakan_online").toString();
					}

				} else if ("lulusPermohonan".equals(submit2)) {
					// update flag

					updateFlag(session, "3");

					if (doPost.equals("true")) {
						sendEmail(namaProjek, TarikhPermohonan, userIdKementerian, id_jawatan_user, id_user
							, "lulus",session);
					}

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_semakan_online = dh.get("flag_semakan_online").toString();
					}

				} else if ("returnFile".equals(submit2)) {
					String flagreturn = getParam("returnType");
					//System.out.println("flagreturn ::::: " + flagreturn);
					// update flag
					updateFlag(session, flagreturn);
					// if (doPost.equals("true")) {
					// sendEmail(namaProjek,TarikhPermohonan,"hantarSemakan");
					// }

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_semakan_online = dh.get("flag_semakan_online").toString();

					}

				} else if ("hantar".equals(submit2)) {
					if (doPost.equals("true")) {
						hantarPermohonan(session, idpermohonan, flagStatusOnline);
						Permohonan permohonan = getPermohonan().getMaklumatPermohonan(idpermohonan);
//						EmailOnline_bak20200613 emel = new EmailOnline_bak20200613();
//						String kementerian = permohonan.getNamaAgensi() + ", "
//								+ permohonan.getPfdFail().getNamaKementerian();
//						String rujukanOnline = permohonan.getNoPermohonan();
//						emel.hantarEmel("", "", "", rujukanOnline, namaProjek, TarikhPermohonan, kementerian, id_user);
					}
					// update status
					dataHeader(idpermohonan);
					id_status = dataHeader(idpermohonan);

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_semakan_online = dh.get("flag_semakan_online").toString();

					}
					modelOnline.setDataPermohonan(idpermohonan);
					Vector dataPermohonan = modelOnline.getDataPermohonan();
					// data
					context.put("dataPermohonan", dataPermohonan);
					context.put("showPopupHantar", "yes");

				} else if ("kemaskiniPendaftaran".equals(submit2)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "yes");

					// data pendaftaran
					DataPermohonan(idpermohonan, "enabled");

					String submit3 = getParam("command3");
					myLogger.info("submit[3] : " + submit3);

					if ("doChangeKementerianUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						String id_urusan = getParam("sorUrusan");
						// String id_kementerian = getParam("socKementerian");

						// get and set data
						getAndSetDataOnchangeKementerian(id_kementerian, id_urusan, "view");

					} // close doChangeKementerianUpdate

					else if ("doChangeProjekNegeriUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						String id_urusan = getParam("sorUrusan");
						String id_negeriprojek = getParam("socNegeriProjek");

						// get and set data
						getAndSetDataOnchangeNegeri(id_negeriprojek, id_urusan, "view", userIdKementerian);
						
						//senarai semak
			    		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
					    context.put("senaraiSemakan", senaraiSemak);

					} // close doChangeProjekNegeriUpdate

					else if ("doChangeUrusanUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						String id_urusan = getParam("sorUrusan");
						String id_negeriprojek = getParam("socNegeriProjek");

						// get and set data
						getAndSetDataOnchangeNegeri(id_negeriprojek, id_urusan, "view", userIdKementerian);

					} // close doChangeUrusan

					else if ("updatePendaftaran".equals(submit3)) {

						updatePendaftaran(session, userIdKementerian);

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						// update data
						header.setDataHeader(idpermohonan);
						dataHeader = header.getDataHeader();
						context.put("dataHeader", dataHeader);
						if (dataHeader.size() != 0) {
							Hashtable dh = (Hashtable) dataHeader.get(0);
							flagSegera = dh.get("flag_segera").toString();
						}

						// senarai semak
						ListCheckBox(idpermohonan);

						// data pendaftaran
						DataPermohonan(idpermohonan, "disabled");

					} // close updatePendaftaran

				} // close kemaskiniPendaftaran

				// screen
				vm = screenUtama;

			} // close semakPendaftaran

			else if ("hapus_fail".equals(submit)) {

				logic1.deleteFail(id_fail, session);

				String notifikasi = request.getParameter("notifikasi") != null
						? (String) request.getParameter("notifikasi")
						: "0";
				listPageDepan = modelOnline.getListPemohon(id_user, portal_role, "");
				context.put("notifikasi", Integer.parseInt(notifikasi));
				//System.out.println("Jumlah Notifikasi : " + notifikasi);
				resetValueCarian();

				// screen
				vm = listdepan;
			}

			else if ("tambahDokumen".equals(submit)) {

				// alert jsp
				context.put("completed", false);

				// list dokumen
				ListDokumen(idpermohonan);
				
				// list dokumen pembayaran
				ListDokumenPembayaran(idpermohonan);

				String submit2 = getParam("command2");
				myLogger.info("submit[2] : " + submit2);

				if ("uploadFile".equals(submit2)) {

					if (doPost.equals("true")) {
						// upload file
						uploadFiles();
					}

					// list dokumen
					ListDokumen(idpermohonan);
					
					// list dokumen pembayaran
					ListDokumenPembayaran(idpermohonan);

					// alert jsp
					context.put("completed", true);

				} // close uploadFile

				// screen
				vm = screenDokumen;

			} // close tambahDokumen

			else if ("hapusDokumen".equals(submit)) {

				hapusDokumen(session);

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// senarai semak
				ListCheckBox(idpermohonan);

				// data pendaftaran
				DataPermohonan(idpermohonan, "disabled");

				// list dokumen
				ListDokumen(idpermohonan);
				

				// screen
				vm = screenUtama;

			} // close hapusDokumen
			
			else if ("hapusDokumenPembayaran".equals(submit)) {

				hapusDokumenPembayaran(session);

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// senarai semak
				ListCheckBox(idpermohonan);

				// data pendaftaran
				DataPermohonan(idpermohonan, "disabled");

				// list dokumen
				ListDokumen(idpermohonan);
				
				// list dokumen pembayaran
				ListDokumenPembayaran(idpermohonan);

				// screen
				vm = screenUtama;

			} // close hapusDokumen

			else if ("hapusDokumenII".equals(submit)) {

				hapusDokumen(session);

				// alert jsp
				context.put("completed", false);

				// list dokumen
				ListDokumen(idpermohonan);
				
				// list dokumen pembayaran
				ListDokumenPembayaran(idpermohonan);

				// screen
				vm = screenDokumen;

			} // close hapusDokumenII

			else if ("salinHakmilik".equals(submit)) {

				// form validation
				context.put("mode", "new");
				
				// Dapatkan balik Data
				getAndSetHM(idpermohonan, "new", id_projekNegeri);
				
				// get data from pendaftaran
				newDataSetting(idpermohonan);

				String id_hakmilik_salin = getParam("id_hakmilik_salin");
				this.context.put("id_hakmilik_salin", id_hakmilik_salin);
				this.context.put("maklumat_Hakmilik_Salin", "");

				if (!id_hakmilik_salin.equals("") && !id_hakmilik_salin.equals(null)
						&& model.salin_maklumat_tanah(id_hakmilik_salin).size() > 0) {
					this.context.put("maklumat_Hakmilik_Salin", model.salin_maklumat_tanah(id_hakmilik_salin));
					dataHakmilik_copy(id_hakmilik_salin, "enabled");
				}
				vm = screenTanah;

			} // close tambahHM
			else if ("tambahHakmilik".equals(submit)) {
				
				// form validation
				context.put("mode", "new");
				
				context.put("id_hakmilik", "");
				// clear value
				clearValueHM();
				
				noLOT = getParam("carianNoLot2");
				context.put("carianNoLot2", noLOT.trim());
				
				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);
				
				// get data from pendaftaran
				newDataSetting(idpermohonan);
				
				String submit2 = getParam("command2");
				myLogger.info("submit[2] : " + submit2);

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

						getAndSetHM(idpermohonan, "new", id_projekNegeri);
						// validations for luas asal
						validationConvertLuas();

						String submit4 = getParam("command4");
						myLogger.info("submit[4] : " + submit4);

						if ("convertNilaiAsal".equals(submit4)) {

							calculateNilaiAsal();

						} // close convertNilaiAsal

						else if ("clearConvertAsal".equals(submit4)) {

							clearConvertAsal("new");

						} // close clearConvertAsal

						else if ("onchangeUnitAsal".equals(submit4)) {

							// convert nilai lain
							changeUnitAsal();

						} // close onchangeUnitAsal

					} // close onchangeUnitLuasAsal

					else if ("onchangeUnitLuasAmbil".equals(submit3)) {

						// validations for luas ambil
						validationConvertLuasAmbil();

						String submit4 = getParam("command4");
						myLogger.info("submit[4] : " + submit4);

						if ("convertNilaiAmbil".equals(submit4)) {

							calculateNilaiAmbil();

						} // close convertNilaiAmbil

						else if ("clearConvertAmbil".equals(submit4)) {

							clearConvertAmbil("new");

						} // close clearConvertAmbil

						else if ("onchangeUnitAmbil".equals(submit4)) {

							// convert nilai lain
							changeUnitAmbil();

						} // close onchangeUnitAmbil

					} // close onchangeUnitLuasAmbil

				} // close doOnchange

				else if ("simpanHakmilik".equals(submit2)) {
					myLogger.info("simpanHakmilik idpermohonan ==== " + idpermohonan);
					myLogger.info("simpanHakmilik");

					String flagSubjaket = getParam("flag_subjaket");

					if (doPost.equals("false")) {
						// simpan hm
						myLogger.info("simpanHM(session)");
						String result = "";
						
						result = simpanHM(session);
					}
					
					// form validation
					context.put("mode", "new");
					context.put("showSave", "yes");

					// clear value
					clearValueHM();

					// list hakmilik
					ListHakmilik(idpermohonan, noLOT, idpegawai);
					
					// get data from pendaftaran
					newDataSetting(idpermohonan);
					
					// update header
					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flag_subjaket = dh.get("flag_subjaket").toString();
					}

				} // close simpanHakmilik

				// screen
				vm = screenTanah;

			} // close tambahHakmilik
			
			//maklumat pembayaran
			else if ("uploadDoc".equals(submit)) {
				myLogger.debug("uploadDoc=" + submit);

				String id_permohonan = getParam("id_permohonan");
				String txdTarikhPembayaran = getParam("txdTarikhPembayaran");
				//String namaDokumen = getParam("txtNamaDokumen");
				String xxxxx = getParam("txtNamaDokumen2");

				// myLog.debug("xxxxx="+xxxxx);
				myLogger.debug("session=" + session);

				uploadFiles(id_permohonan, txdTarikhPembayaran, session);
				//context.put("mode", "view");
				
				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// check field validation
				checkFieldValidation(idHakmilik);

				noLOT = getParam("carianNoLot2");
				context.put("carianNoLot2", noLOT.trim());
				
				// list dokumenhakmilik
				ListDokumenPembayaran(id_permohonan);

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// data hakmilik
				dataHakmilik(idHakmilik, "disabled");

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
				
				
				vm = screenTanah;
			}
			
			else if ("viewHM".equals(submit)) {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// check field validation
				checkFieldValidation(idHakmilik);

				noLOT = getParam("carianNoLot2");
				context.put("carianNoLot2", noLOT.trim());

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// data hakmilik
				dataHakmilik(idHakmilik, "disabled");

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

				String submit2 = getParam("command2");
				myLogger.info("submit[2] : " + submit2);

				if ("kemaskiniHM".equals(submit2)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "yes");

					// data hakmilik
					dataHakmilik(idHakmilik, "enabled");

					String submit3 = getParam("command3");
					myLogger.info("submit[3] : " + submit3);

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

							} // close convertNilaiAsalUpdate

							else if ("clearConvertAsalUpdate".equals(submit5)) {

								clearConvertAsal("view");

							} // close clearConvertAsalUpdate

							else if ("onchangeUnitAsalUpdate".equals(submit5)) {

								// convert nilai lain
								changeUnitAsal();

							} // close onchangeUnitAsalUpdate

						} // close onchangeUnitLuasAsalUpdate

						else if ("onchangeUnitLuasAmbilUpdate".equals(submit4)) {

							// validations for luas ambil
							validationConvertLuasAmbil();

							String submit5 = getParam("command5");
							myLogger.info("submit[5] : " + submit5);

							if ("convertNilaiAmbilUpdate".equals(submit5)) {

								calculateNilaiAmbil();

							} // close convertNilaiAmbilUpdate

							else if ("clearConvertAmbilUpdate".equals(submit5)) {

								clearConvertAmbil("view");

							} // close clearConvertAmbilUpdate

							else if ("onchangeUnitAmbilUpdate".equals(submit5)) {

								// convert nilai lain
								changeUnitAmbil();

							} // close onchangeUnitAmbilUpdate

						} // close onchangeUnitLuasAmbilUpdate

					} // close doOnchangeUpdate

					else if ("updateHM".equals(submit3)) {
						myLogger.info("updateHM idpermohonan ==== " + idpermohonan);
						String flagSubjaket = getParam("flag_subjaket");
						myLogger.info("updateHM flagSubjaket ===== "+flagSubjaket);
						// data hakmilik
						/*model.setMaklumatTanah(idHakmilik);
						dataMaklumatTanah = model.getMaklumatTanah();
						context.put("dataMaklumatTanah", dataMaklumatTanah);
						*/
						
						updateHM(session, idHakmilik, id_projekDaerah);
						
						// update header
						header.setDataHeader(idpermohonan);
						dataHeader = header.getDataHeader();
						context.put("dataHeader", dataHeader);
						if (dataHeader.size() != 0) {
							Hashtable dh = (Hashtable) dataHeader.get(0);
							flag_subjaket = dh.get("flag_subjaket").toString();
						}
						
						
						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						// check field validation
						checkFieldValidation(idHakmilik);

						// list hakmilik
						//ListHakmilik(idpermohonan, noLOT, idpegawai);

						// data hakmilik
						dataHakmilik(idHakmilik, "disabled");

					} // close updateHM

				} // close kemaskiniHM

				// screen
				vm = screenTanah;

			} // close viewHM

			else if ("hapusHM".equals(submit)) {

				hapusHM(session);

				// form validation
				context.put("mode", "new");

				// clear value
				clearValueHM();

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// get data from pendaftaran
				newDataSetting(idpermohonan);

				// screen
				vm = screenTanah;

			} // close hapusHM

			/************** PIHAK BERKEPENTINGAN *****************/

			else if ("tambahPB".equals(submit)) {

				// form validation
				context.put("mode", "new");

				// data hakmilik
				model.setMaklumatTanah(idHakmilik);
				dataMaklumatTanah = model.getMaklumatTanah();
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
				context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB", null, "style=width:auto"));
				context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB", null, "style=width:300px"));
				context.put("selectBangsa", HTML.SelectBangsa("socBangsa", null, "style=width:300px"));
				context.put("selectWarga", HTML.SelectWarganegara("socWarga", null, "style=width:auto"));
				context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", null, null,
						"style=width:300px onChange=\"onchangeNegeri();\""));
				context.put("selectBandar", HTML.SelectBandar("socBandar", null, "style=width:300px"));

				// get size bahagian pb and dropdown bahagian syer
				sizeAndDropdownBahagian(idHakmilik);

				String submit2 = getParam("command2");
				myLogger.info("submit[2]: " + submit2);

				if ("onchangeNegeri".equals(submit2)) {

					context.put("hideAdd", "no");

					String idNegeri = getParam("socNegeri");
					String idBahagian = getParam("socBahagianPB");

					// dropdown
					context.put("selectBahagianPB",
							HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB", Utils.parseLong(idBahagian),
									null, "style=width:250px onChange=\"onchangeGetBahagian();\""));

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar",
								HTML.SelectBandarByNegeri(idNegeri, "socBandar", null, "style=width:300px"));
					} else {
						context.put("selectBandar", HTML.SelectBandar("socBandar", null, "style=width:300px"));
					}

					// get and set back
					getAndSetDataPB(session, "new");

				} // close onchangenegeri

				else if ("simpanPB".equals(submit2)) {

					idExistPB = getParam("idExistPB");

					// check exist id hakmilik
					modelHM.setSizeExistPB(idHakmilik, idExistPB);
					checkExistPBidHM = modelHM.getCheckSizeExistPB();

					/*
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

				} // close simpanPB

				else if ("onchangeGetBahagian".equals(submit2)) {

					context.put("hideAdd", "no");

					String idNegeri = getParam("socNegeri");
					String idBandar = getParam("socBandar");
					String idBahagian = getParam("socBahagianPB");

					// dropdown
					context.put("selectBahagianPB",
							HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB", Utils.parseLong(idBahagian),
									null, "style=width:250px onChange=\"onchangeGetBahagian();\""));

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar",
								Utils.parseLong(idBandar), "style=width:300px"));
					} else {
						context.put("selectBandar",
								HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "style=width:300px"));
					}

					// get and set back
					getAndSetDataPB(session, "new");

					// get bahagian
					getBahagianExist(idBahagian);

				} // close onchangeGetBahagian

				else if ("checkExistPB".equals(submit2)) {

					context.put("hideAdd", "no");

					idHakmilik = getParam("id_hakmilik");
					String idBahagian = getParam("socBahagianPB");

					// dropdown
					context.put("selectBahagianPB",
							HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB", Utils.parseLong(idBahagian),
									null, "style=width:250px onChange=\"onchangeGetBahagian();\""));

					// check exist pb
					checkExistPB(session);
					idExistPB = checkExistPB(session);

					// ID EXIST
					if (idExistPB != "") {

						// set exist pb detail
						getDetailExistPB(session, idExistPB, idHakmilik);
						context.put("idExistPB", idExistPB);

						// ID NOT EXIST
					} else {

						// get and set back
						getAndSetDataPB(session, "new");

						String idNegeri = getParam("socNegeri");
						String idBandar = getParam("socBandar");

						// dropdown by
						if (idNegeri != "") {
							context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar",
									Utils.parseLong(idBandar), "style=width:300px"));
						} else {
							context.put("selectBandar",
									HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "style=width:300px"));
						}

					}

				} // close checkExistPB

				// screen
				vm = screenPB;

			} // close tambahPB

			else if ("viewPB".equals(submit)) {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// carian nama pb
				namaPB = getParam("carianPB2");
				context.put("carianPB2", namaPB.trim());

				String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
				context.put("id_pihakberkepentingan", id_pihakberkepentingan);

				// data hakmilik
				model.setMaklumatTanah(idHakmilik);
				dataMaklumatTanah = model.getMaklumatTanah();
				context.put("dataMaklumatTanah", dataMaklumatTanah);

				// check size bahagian pb
				modelHM.setSizeBahagianPB(idHakmilik);
				checkSizeBahagianPB = modelHM.getSizeBahagianPB();
				context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());

				// get total
				getTotalSyer(idHakmilik, id_pihakberkepentingan);

				// data PB in TBLPPTHAKMILIKPB AND TBLPPTPIHAKBERKEPENTINGAN
				// with
				// dropdown view (disabled)
				dataPBVIEW(session, id_pihakberkepentingan, idHakmilik);

				modelHM.setDataDetailPB(id_pihakberkepentingan, idHakmilik, 0);
				dataDetailPB = modelHM.getDataDetailPB();
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
					context.put("selectJenisNoPB",
							HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(id_jenisNoPB), "style=width:auto"));
					context.put("selectJenisPB",
							HTML.SelectJenisPb("socJenisPB", Utils.parseLong(id_jenisPB), "style=width:300px"));
					context.put("selectBangsa",
							HTML.SelectBangsa("socBangsa", Utils.parseLong(id_bangsa), "style=width:300px"));
					context.put("selectWarga",
							HTML.SelectWarganegara("socWarga", Utils.parseLong(id_warganegara), "style=width:auto"));
					context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri),
							"style=width:300px onChange=\"onchangeNegeriUpdate();\""));

					// dropdown bahagian
					context.put("selectBahagianPB",
							HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB", Utils.parseLong(id_bahagianpb),
									null, "style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

					if (id_negeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(id_negeri, "socBandar",
								Utils.parseLong(id_bandar), "style=width:300px"));
					} else {
						context.put("selectBandar",
								HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:300px"));
					}

					String submit3 = getParam("command3");
					myLogger.info("submit[3]: " + submit3);

					if ("onchangeNegeriUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						String idNegeri = getParam("socNegeri");
						id_bahagianpb = getParam("socBahagianPB");

						// dropdown bahagian
						context.put("selectBahagianPB",
								HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB",
										Utils.parseLong(id_bahagianpb), null,
										"style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

						// dropdown by
						if (idNegeri != "") {
							context.put("selectBandar",
									HTML.SelectBandarByNegeri(idNegeri, "socBandar", null, "style=width:300px"));
						} else {
							context.put("selectBandar", HTML.SelectBandar("socBandar", null, "style=width:300px"));
						}

						// get and set back
						getAndSetDataPB(session, "view");

					} // close onchangeNegeriUpdate

					else if ("onchangeGetBahagianUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						String idNegeri = getParam("socNegeri");
						String idBandar = getParam("socBandar");
						String idBahagian = getParam("socBahagianPB");

						// dropdown
						context.put("selectBahagianPB",
								HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB",
										Utils.parseLong(idBahagian), null,
										"style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

						// dropdown by
						if (idNegeri != "") {
							context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar",
									Utils.parseLong(idBandar), "style=width:300px"));
						} else {
							context.put("selectBandar",
									HTML.SelectBandar("socBandar", Utils.parseLong(idBandar), "style=width:300px"));
						}

						// get and set back
						getAndSetDataPB(session, "view");

						// get bahagian
						getBahagianExist(idBahagian);

					} // close onchangeGetBahagian

					else if ("updatePB".equals(submit3)) {

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						if (doPost.equals("true")) {
							updatePB(session);
						}

						id_pihakberkepentingan = getParam("id_pihakberkepentingan");
						context.put("id_pihakberkepentingan", id_pihakberkepentingan);

						// get total
						getTotalSyer(idHakmilik, "");

						// data PB with dropdown view (disabled)
						dataPBVIEW(session, id_pihakberkepentingan, idHakmilik);

						// list PB
						listPB(session, idHakmilik, namaPB);

					} // close updatePB

				} // close kemaskiniPB

				// screen
				vm = screenPB;

			} // close viewPB

			else if ("hapusPB".equals(submit)) {

				hapusPB(session);

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// list PB
				listPB(session, idHakmilik, namaPB);

				// list Bebanan
				listBebanan(session, idHakmilik, noSerah);

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// data hakmilik
				dataHakmilik(idHakmilik, "disabled");

				// get total
				getTotalSyer(idHakmilik, "");

				// screen
				vm = screenTanah;

			} // close hapusPB

			/*******************************/

			/************* BEBANAN ******************/

			else if ("tambahBebanan".equals(submit)) {

				// form validation
				context.put("mode", "new");

				// carian no serah
				noSerah = getParam("carianNoSerah2");
				context.put("carianNoSerah2", noSerah.trim());

				// reset data
				context.put("txtPerserahan", "");
				context.put("txtNama", "");
				context.put("txtNoPB", "");
				context.put("txtAlamat1", "");
				context.put("txtAlamat2", "");
				context.put("txtAlamat3", "");
				context.put("txtPoskod", "");
				context.put("txdTarikhDaftar", "");

				// data hakmilik
				model.setMaklumatTanah(idHakmilik);
				dataMaklumatTanah = model.getMaklumatTanah();
				context.put("dataMaklumatTanah", dataMaklumatTanah);

				// list Bebanan
				listBebanan(session, idHakmilik, noSerah);

				// dropdown (new)
				context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB", null, "style=width:auto"));
				context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", null, null,
						"style=width:300px onChange=\"onchangeNegeriBebanan();\""));
				context.put("selectBandar", HTML.SelectBandar("socBandar", null, "style=width:300px"));
				context.put("selectBebanan", HTML.SelectBebanan("socJenisBebanan", null, "style=width:auto"));

				String submit2 = getParam("command2");
				myLogger.info("submit[2]: " + submit2);

				if ("onchangeNegeriBebanan".equals(submit2)) {

					String idNegeri = getParam("socNegeri");

					// dropdown by
					if (idNegeri != "") {
						context.put("selectBandar",
								HTML.SelectBandarByNegeri(idNegeri, "socBandar", null, "style=width:300px"));
					} else {
						context.put("selectBandar", HTML.SelectBandar("socBandar", null, "style=width:300px"));
					}

					// get and set back
					getAndSetDataBebanan(session, "new");

				} // close onchangeNegeriBebanan

				else if ("simpanBebanan".equals(submit2)) {

					if (doPost.equals("true")) {
						// simpan bebanan
						simpanBebanan(session);
					}

					// list Bebanan
					listBebanan(session, idHakmilik, noSerah);

				} // close simpanBebanan

				// screen
				vm = screenBebanan;

			} // close tambahBebanan

			else if ("viewBebanan".equals(submit)) {

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// carian no serah
				noSerah = getParam("carianNoSerah2");
				context.put("carianNoSerah2", noSerah.trim());

				// data hakmilik
				model.setMaklumatTanah(idHakmilik);
				dataMaklumatTanah = model.getMaklumatTanah();
				context.put("dataMaklumatTanah", dataMaklumatTanah);

				String idBebanan = getParam("id_bebanan");
				context.put("id_bebanan", idBebanan);

				// data bebanan (semak,disabled)
				dataBebanan(session, idBebanan);

				modelHM.dataBebanan(idBebanan);
				dataBebanan = modelHM.getDataBebanan();
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

				// list Bebanan
				listBebanan(session, idHakmilik, noSerah);

				String submit2 = getParam("command2");
				myLogger.info("submit[2]: " + submit2);

				if ("kemaskiniBebanan".equals(submit2)) {

					// form validation
					context.put("mode", "view");
					context.put("isEdit", "yes");

					// dropdown (enabled)
					context.put("selectJenisNoPB",
							HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(id_jenisnopb), "style=width:auto"));
					context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri), null,
							"style=width:300px onChange=\"onchangeNegeriBebananUpdate();\""));
					context.put("selectBebanan", HTML.SelectBebanan("socJenisBebanan", Utils.parseLong(id_jenisbebanan),
							"style=width:auto"));

					if (id_negeri != "") {
						context.put("selectBandar", HTML.SelectBandarByNegeri(id_negeri, "socBandar",
								Utils.parseLong(id_bandar), "style=width:300px"));
					} else {
						context.put("selectBandar",
								HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:300px"));
					}

					String submit3 = getParam("command3");
					myLogger.info("submit[3]: " + submit3);

					if ("onchangeNegeriBebananUpdate".equals(submit3)) {

						// onchange validation
						context.put("onchange", "yes");

						String idNegeri = getParam("socNegeri");

						// dropdown by
						if (idNegeri != "") {
							context.put("selectBandar",
									HTML.SelectBandarByNegeri(idNegeri, "socBandar", null, "style=width:300px"));
						} else {
							context.put("selectBandar", HTML.SelectBandar("socBandar", null, "style=width:300px"));
						}

						// get and set back
						getAndSetDataBebanan(session, "view");

					} // close onchangeNegeriBebananUpdate

					else if ("updateBebanan".equals(submit3)) {

						updateBebanan(session);

						// form validation
						context.put("mode", "view");
						context.put("isEdit", "no");

						idBebanan = getParam("id_bebanan");
						context.put("id_bebanan", idBebanan);

						// data bebanan (semak,disabled)
						dataBebanan(session, idBebanan);

						// list Bebanan
						listBebanan(session, idHakmilik, noSerah);

					} // close updateBebanan

				} // close kemaskiniBebanan

				// screen
				vm = screenBebanan;

			} // close viewBebanan

			else if ("hapusBebanan".equals(submit)) {

				hapusBebanan(session);

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");
				selectedTab = "1";

				// list PB
				listPB(session, idHakmilik, namaPB);

				// list Bebanan
				listBebanan(session, idHakmilik, noSerah);

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// data hakmilik
				dataHakmilik(idHakmilik, "disabled");

				// get total
				getTotalSyer(idHakmilik, "");

				// screen
				vm = screenTanah;

			} // close hapusBebanan

			/*******************************/

			else if ("hantarPermohonan".equals(submit)) {

				// screen
				if (id_suburusan.equals("51") || id_suburusan.equals("52")) {

					vm = screenSemakan;

				} else {

					if (doPost.equals("true")) {
						// hantarPermohonan(session, idpermohonan, flagStatusOnline);
						Permohonan permohonan = getPermohonan().getMaklumatPermohonan(idpermohonan);
						String kementerian = permohonan.getNamaAgensi() + ", "
								+ permohonan.getPfdFail().getNamaKementerian();
						String rujukanOnline = permohonan.getNoPermohonan();
						hantarEmel("", "", "", rujukanOnline, namaProjek, TarikhPermohonan, kementerian, id_user);
					}

					header.setDataHeader(idpermohonan);
					dataHeader = header.getDataHeader();
					context.put("dataHeader", dataHeader);
					if (dataHeader.size() != 0) {
						Hashtable dh = (Hashtable) dataHeader.get(0);
						flagStatusOnline = (String) dh.get("flag_status_online");
						catatan_status_online = (String) dh.get("catatan_status_online");
					}

					vm = screenUtama;
				}

				header.setDataHeader(idpermohonan);
				dataHeader = header.getDataHeader();
				context.put("dataHeader", dataHeader);
				if (dataHeader.size() != 0) {
					Hashtable dh = (Hashtable) dataHeader.get(0);
					flag_semakan_online = dh.get("flag_semakan_online").toString();
				}

				// form validation
				context.put("mode", "view");
				context.put("isEdit", "no");

				// update status
				dataHeader(idpermohonan);
				id_status = dataHeader(idpermohonan);

				// list hakmilik
				ListHakmilik(idpermohonan, noLOT, idpegawai);

				// data pendaftaran
				DataPermohonan(idpermohonan, "disabled");

				// list dokumen
				ListDokumen(idpermohonan);
				
				// list dokumen pembayaran
				ListDokumenPembayaran(idpermohonan);

			} // close hantarPendaftaran
			/*
			 * else if("hantar".equals(submit)){
			 * 
			 * if (doPost.equals("true")) {
			 * hantarPermohonan(session,idpermohonan,flagStatusOnline); }
			 * 
			 * header.setDataHeader(idpermohonan); dataHeader = header.getDataHeader();
			 * context.put("dataHeader", dataHeader); if(dataHeader.size()!=0){ Hashtable dh
			 * = (Hashtable) dataHeader.get(0); flagStatusOnline =
			 * (String)dh.get("flag_status_online"); catatan_status_online =
			 * (String)dh.get("catatan_status_online"); }
			 * 
			 * listPageDepan = modelOnline.getListPemohon(id_user);
			 * 
			 * context.put("nofail", ""); context.put("carianTarikh", "");
			 * context.put("carianStatus", ""); context.put("sorUrusan", "");
			 * 
			 * //screen vm = listdepan;
			 * 
			 * }//close hantar
			 */
			else if ("cari".equals(submit)) {

				// carian
				String flag_noti = getParam("flag_noti");
				ListCarian(session, id_user, portal_role, flag_noti);
				listPageDepan = modelOnline.getListCarian();

				// screen
				vm = listdepan;

			} // close cari

			else {
				String flag_noti = getParam("flag_noti");
				String notifikasi = request.getParameter("notifikasi") != null
						? (String) request.getParameter("notifikasi")
						: "0";
				listPageDepan = modelOnline.getListPemohon(id_user, portal_role, flag_noti);
				context.put("notifikasi", Integer.parseInt(notifikasi));
				resetValueCarian();

				// screen

				vm = listdepan;

			} // close else
			//System.out.println("VM ni kat mana :::::: " + vm);
			// list permohonan
			context.put("listPermohonan", listPageDepan);
			context.put("list_size", listPageDepan.size());

			// for paging 1 ~ 24
			context.put("flagSegera", flagSegera);

			// flag subjaket
			context.put("flag_subjaket", flag_subjaket);

			// id
			context.put("id_permohonan", idpermohonan);
			context.put("id_hakmilik", idHakmilik);
			context.put("id_status", id_status);
			context.put("id_fail", id_fail);
			context.put("no_fail", no_fail);
			context.put("id_suburusan", id_suburusan);
			context.put("flagStatusOnline", flagStatusOnline);
			context.put("catatan_status_online", catatan_status_online);
			context.put("flag_semakan_online", flag_semakan_online);
			// context.put("ulasanjt", ulasanjt);
			
			

			this.context.put("selectedTab", selectedTab);
			setupPage(session, action, listPageDepan);
			return vm;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("[PPT ONLINE] " + e.getMessage());

		}

	}// close public template

	// --METHOD--//

	private void hantarEmel(String string, String string2, String string3, String rujukanOnline, String namaProjek,
			String tarikhPermohonan, String kementerian, String id_user) {
		// TODO Auto-generated method stub

	}

	public String getUserIC(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String getUserIC = "";
			sql = " SELECT UO.NO_KP_BARU FROM USERS U, USERS_ONLINE UO " + " WHERE U.USER_ID = UO.USER_ID "
					+ " AND U.USER_ID = '" + USER_ID + "'";
			myLogger.info(" OT : getUserIC :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				getUserIC = (rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));

			}

			return getUserIC;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}

	private void sendEmail(String nama_projek, String tarikh_permohonan, String userIdKementerian,
		String id_jawatan_user, String id_user, String purpose,HttpSession session) throws Exception {
		//hantarSemakan,hantarLulus,lulus
		EmailOnline et = new EmailOnline();
		et.setEmail("", "", purpose, "", nama_projek, tarikh_permohonan, "", userIdKementerian, id_jawatan_user,id_user
				,String.valueOf(session.getAttribute("portal_username")));
//		System.out.println("*** sendEmail : " + nama_projek + " " + tarikh_permohonan);

	}// close sendEmail

	private void resetValueCarian() throws Exception {

		// dropdown
		context.put("selectStatusSPT", HTML.SelectStatusSPT("socStatus", null, "style=width:auto"));
		context.put("selectJenisHMCarian",
				getJenisHakmilik().Pilihan("socJenisHakmilik", null, "id=selectJenisHMCarian style=width:auto"));
		context.put("selectNegeriCarian", HTML.SelectNegeriMampu("socNegeri", null, null, "style=width:auto"));
		context.put("txtNoFailCarian", "");
		context.put("txdTarikhPermohonan", "");
		context.put("tarikh_permohonan_kjp", "");
		context.put("sorUrusanCarian", "");
		context.put("txtBilPermohonanCarian", "");
		context.put("txtNamaPBCarian", "");
		context.put("txtNoPBCarian", "");
		context.put("txtNoHakmilikCarian", "");
		context.put("txtNoLotCarian", "");
		context.put("txtTujuanCarian", "");

	}// close resetValueCarian

	@SuppressWarnings({ "unchecked", "static-access" })
	private String getJawatan(String id_user) throws Exception {

		Vector dataUser = new Vector();
		dataUser.clear();
		model.setDataUser(id_user);
		dataUser = model.getDataUser();
		String ID_JAWATAN = "";
		if (dataUser.size() != 0) {
			Hashtable t = (Hashtable) dataUser.get(0);
			ID_JAWATAN = t.get("ID_JAWATAN").toString();
		}
		return ID_JAWATAN;

	}// close getJawatan

	@SuppressWarnings("unchecked")
	private void updateFlag(HttpSession session, String flag) throws Exception {

		Hashtable h = new Hashtable();

		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		h.put("flag_semakan_online", flag);

		//System.out.println("flag return 1 :::: " + flag);

		FrmPermohonanUPTData.updateFlag(h);

		//System.out.println("flag return 2 :::: " + flag);

	}// close updateFlag

	@SuppressWarnings({ "unchecked", "static-access" })
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

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelHM.updateBebanan(h);

	}// close simpanBebanan

	@SuppressWarnings({ "unchecked", "static-access" })
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

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		modelHM.simpanBebanan(h);

	}// close simpanBebanan

	private void getAndSetDataBebanan(HttpSession session, String mode) throws Exception {

		String idNegeri = getParam("socNegeri");
		String idJenisNoPB = getParam("socJenisNoPB");
		String idJenisBebanan = getParam("socJenisBebanan");

		// dropdown (new)
		context.put("selectJenisNoPB",
				HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(idJenisNoPB), "style=width:auto"));
		context.put("selectBebanan",
				HTML.SelectBebanan("socJenisBebanan", Utils.parseLong(idJenisBebanan), "style=width:auto"));

		if (mode.equals("new")) {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(idNegeri), null,
					"style=width:300px onChange=\"onchangeNegeriBebanan();\""));
		} else {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(idNegeri), null,
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

	@SuppressWarnings("unchecked")
	private void dataHakmilik_copy(String idHakmilik, String disability) throws Exception {

		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		model.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = model.getMaklumatTanah();
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
		context.put("SelectDaerahPenggawa", HTML.SelectDaerahPenggawa("socDaerahPenggawa",
				Utils.parseLong(id_daerahpenggawa), null, " " + mode + " style=width:274px"));

		// dropdown
		if (id_negeriprojek.equals("10")) {
			context.put("selectJenisHakmilik",
					HTML.SelectJenisHakmilikSelangor("socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
							"id=socJenisHakmilik " + mode + " style=width:auto onchange=doOnchange()"));
		} else {
			context.put("selectJenisHakmilik",
					HTML.SelectJenisHakmilik("socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
							"id=socJenisHakmilik " + mode + " style=width:auto onchange=doOnchange()"));
		}

		context.put("selectKategoriTanah", HTML.SelectKategoriTanah("socKategoriTanah",
				Utils.parseLong(id_kategoritanah), "id=socKategoriTanah " + mode + " style=width:auto", null));
		context.put("selectKodLot",
				HTML.SelectUnitPT("socKodLot", Utils.parseLong(id_lot), "style=width:auto " + mode + " "));

		// dropdown unit luas
		context.put("selectUnitLuasLot", HTML.SelectLuas("socUnitLuasLot", Utils.parseLong(id_luaslot),
				"style=width:250px " + mode + " id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		context.put("selectUnitLuasAmbil", HTML.SelectLuas("socUnitLuasAmbil", Utils.parseLong(id_unitluasambil),
				"style=width:250px " + mode + " id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));

		// dropdown by
		if (id_daerah != "") {
			context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(id_daerah, "socMukim", Utils.parseLong(id_mukim),
					"style=width:auto " + mode + ""));
		} else {
			context.put("selectMukim",
					HTML.SelectMukimNoKod("socMukim", Utils.parseLong(id_mukim), "style=width:auto " + mode + ""));
		}

	}// close dataHakmilik

	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataBebanan(HttpSession session, String idBebanan) throws Exception {

		modelHM.dataBebanan(idBebanan);
		Vector dataBebanan = modelHM.getDataBebanan();
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
		context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(id_jenisnopb),
				"style=width:auto disabled class=disabled"));
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri), null,
				"style=width:300px disabled class=disabled"));
		context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar),
				"style=width:300px disabled class=disabled"));
		context.put("selectBebanan", HTML.SelectBebanan("socJenisBebanan", Utils.parseLong(id_jenisbebanan),
				"style=width:auto disabled class=disabled"));

	}// close dataBebanan

	@SuppressWarnings("static-access")
	private void hapusBebanan(HttpSession session) throws Exception {

		String id_bebanan = getParam("id_bebanan");

		modelHM.hapusBebanan(id_bebanan);

	}// close hapusBebanan

	@SuppressWarnings("static-access")
	private void hapusPB(HttpSession session) throws Exception {

		String id_pihakberkepentingan = getParam("id_pihakberkepentingan");
		String id_hakmilik = getParam("id_hakmilik");

		modelHM.hapusPB(id_pihakberkepentingan, id_hakmilik);

	}// close hapusPB

	@SuppressWarnings({ "unchecked", "static-access" })
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

		h.put("txtNamaBank", getParam("txtNamaBank"));
		h.put("txtNoAkaun", getParam("txtNoAkaun"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		String id_exist_bahagianpb = getParam("socBahagianPB");

		String id_bahagianpb = "";

		if (getParam("txtSyorAtas") != "" && getParam("txtSyorBawah") != "") {
			if (id_exist_bahagianpb != "") {
				id_bahagianpb = id_exist_bahagianpb;
				modelHM.updateBahagianPB(h, id_bahagianpb);
			} else {
				id_bahagianpb = DB.getNextID("TBLPPTBAHAGIANPB_SEQ") + "";
				modelHM.simpanBahagianPB(h, id_bahagianpb);
			}
		}

		modelHM.updatePB(h, "update", id_bahagianpb);

	}// close updatePB

	@SuppressWarnings({ "unchecked", "static-access" })
	private void dataPBVIEW(HttpSession session, String idPB, String idHakmilik) throws Exception {

		Vector dataDetailPB = new Vector();
		dataDetailPB.clear();

		modelHM.setDataDetailPB(idPB, idHakmilik, 0);
		dataDetailPB = modelHM.getDataDetailPB();
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
		context.put("selectJenisNoPB", HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(id_jenisNoPB),
				"style=width:auto class=disabled disabled"));
		context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB", Utils.parseLong(id_jenisPB),
				"style=width:300px class=disabled disabled"));
		context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Utils.parseLong(id_bangsa),
				"style=width:300px class=disabled disabled"));
		context.put("selectWarga", HTML.SelectWarganegara("socWarga", Utils.parseLong(id_warganegara),
				"style=width:auto class=disabled disabled"));
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri),
				"style=width:300px class=disabled disabled"));
		context.put("selectBandar", HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar),
				"style=width:300px class=disabled disabled"));

		// dropdown bahagian
		context.put("selectBahagianPB",
				HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB", Utils.parseLong(id_bahagianpb), null,
						" class=disabled disabled style=width:250px onChange=\"onchangeGetBahagianUpdate();\""));

	}// close dataPBVIEW

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDetailExistPB(HttpSession session, String idPB, String idHakmilik) throws Exception {

		Vector dataDetailExistPB = new Vector();
		dataDetailExistPB.clear();

		modelHM.setDataDetailPB(idPB, idHakmilik, 1);
		dataDetailExistPB = modelHM.getDataDetailPB();

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
		context.put("selectJenisNoPB",
				HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(id_jenisNoPB), "style=width:auto"));
		context.put("selectJenisPB",
				HTML.SelectJenisPb("socJenisPB", Utils.parseLong(id_jenisPB), "style=width:300px"));
		context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Utils.parseLong(id_bangsa), "style=width:300px"));
		context.put("selectWarga",
				HTML.SelectWarganegara("socWarga", Utils.parseLong(id_warganegara), "style=width:auto"));
		context.put("selectNegeri",
				HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri), "style=width:300px"));

		if (id_negeri != "") {
			context.put("selectBandar",
					HTML.SelectBandarByNegeri(id_negeri, "socBandar", Utils.parseLong(id_bandar), "style=width:300px"));
		} else {
			context.put("selectBandar",
					HTML.SelectBandar("socBandar", Utils.parseLong(id_bandar), "style=width:300px"));
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

	}// close getDetailExistPB

	@SuppressWarnings({ "unchecked", "static-access" })
	private String checkExistPB(HttpSession session) throws Exception {

		Vector checkExistPB = new Vector();
		checkExistPB.clear();

		String socJenisNoPB = getParam("socJenisNoPB");
		String noPB = getParam("txtNoPB");

		modelHM.setCheckExistPB(socJenisNoPB, noPB);
		checkExistPB = modelHM.getCheckExistPB();
		String idExistPB = "";
		if (checkExistPB.size() != 0) {
			Hashtable ce = (Hashtable) checkExistPB.get(0);
			idExistPB = (String) ce.get("id_pihakberkepentingan");
		}

		return idExistPB;

	}// close checkExistPB

	@SuppressWarnings({ "static-access", "unchecked" })
	private void getBahagianExist(String idBahagian) throws Exception {

		Vector checkExistBahagianPB = new Vector();
		checkExistBahagianPB.clear();

		String syer_atas = "";
		String syer_bawah = "";
		modelHM.setDataExistBahagianPB(idBahagian);
		checkExistBahagianPB = modelHM.getDataExistBahagianPB();
		if (checkExistBahagianPB.size() != 0) {
			Hashtable ceb = (Hashtable) checkExistBahagianPB.get(0);
			syer_atas = (String) ceb.get("syer_atas");
			syer_bawah = (String) ceb.get("syer_bawah");
		}

		context.put("txtSyorAtas", syer_atas);
		context.put("txtSyorBawah", syer_bawah);

	}// close getBahagianExist

	@SuppressWarnings({ "unchecked", "static-access" })
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
				modelHM.updateBahagianPB(h, id_bahagianpb);
			} else {
				id_bahagianpb = String.valueOf(DB.getNextID("TBLPPTBAHAGIANPB_SEQ"));
				modelHM.simpanBahagianPB(h, id_bahagianpb);
			}
		}

		/*
		 * if(idExistPB!=""){ h.put("id_pihakberkepentingan", idExistPB);
		 * modelHM.updatePB(h,"updateOLD","");
		 * modelHM.simpanExistPB(h,id_hakmilikpb,id_bahagianpb); }else{
		 */long id_pihakberkepentingan = DB.getNextID("TBLPPTPIHAKBERKEPENTINGAN_SEQ");
		modelHM.simpanPB(h, id_pihakberkepentingan, id_hakmilikpb, id_bahagianpb);
		// }

	}// close simpanPB

	private void getAndSetDataPB(HttpSession session, String mode) throws Exception {

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
		context.put("selectJenisNoPB",
				HTML.SelectJenisNoPb("socJenisNoPB", Utils.parseLong(idJenisNoPB), "style=width:auto"));
		context.put("selectJenisPB", HTML.SelectJenisPb("socJenisPB", Utils.parseLong(idJenisPB), "style=width:300px"));
		context.put("selectBangsa", HTML.SelectBangsa("socBangsa", Utils.parseLong(idBangsa), "style=width:300px"));
		context.put("selectWarga", HTML.SelectWarganegara("socWarga", Utils.parseLong(idWarga), "style=width:auto"));

		if (mode.equals("new")) {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(idNegeri), null,
					"style=width:300px onChange=\"onchangeNegeri();\""));
		} else {
			context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(idNegeri), null,
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

	@SuppressWarnings({ "static-access", "unchecked" })
	private void sizeAndDropdownBahagian(String idHakmilik) throws Exception {

		Vector checkSizeBahagianPB = new Vector();
		checkSizeBahagianPB.clear();

		modelHM.setSizeBahagianPB(idHakmilik);
		checkSizeBahagianPB = modelHM.getSizeBahagianPB();
		context.put("checkSizeBahagianPB_size", checkSizeBahagianPB.size());

		// dropdown bahagian syer
		context.put("selectBahagianPB", HTML.SelectBahagianPBbyHakmilik(idHakmilik, "socBahagianPB", null, null,
				"style=width:250px onChange=\"onchangeGetBahagian();\""));

	}// close sizeAndDropdownBahagian

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

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listBebanan(HttpSession session, String idHakmilik, String noSerah) throws Exception {

		Vector listBebanan = new Vector();
		listBebanan.clear();

		modelHM.listBebanan(idHakmilik, noSerah);
		listBebanan = modelHM.getListBebanan();
		context.put("listBebanan", listBebanan);
		context.put("saiz_bebanan", listBebanan.size());

	}// close listBebanan

	@SuppressWarnings({ "unchecked", "static-access" })
	private void listPB(HttpSession session, String idHakmilik, String namaPB) throws Exception {

		Vector listPB = new Vector();
		listPB.clear();

		modelHM.setListPB(idHakmilik, namaPB);
		listPB = modelHM.getListPB();
		context.put("saiz_pb", listPB.size());
		context.put("listMaklumatPB", listPB);

	}// close listPB

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getTotalSyer(String idHakmilik, String idpb) throws Exception {

		Vector totalSyer = new Vector();
		totalSyer.clear();

		String total = "";
		String hideAdd = "";
		modelHM.setTotalSyer(idHakmilik, idpb);
		totalSyer = modelHM.getTotalSyer();
		if (totalSyer.size() != 0) {
			Hashtable ts = (Hashtable) totalSyer.get(0);
			total = (String) ts.get("total");
			hideAdd = (String) ts.get("hideAdd");
		}
		context.put("totalSyer", total);
		context.put("hideAdd", hideAdd);

	}// close getTotalSyer

	@SuppressWarnings({ "unchecked", "static-access" })
	private void hantarPermohonan(HttpSession session, String idpermohonan, String flagStatusOnline) throws Exception {

		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();

		Hashtable h = new Hashtable();

		h.put("id_permohonan", idpermohonan);
		h.put("id_fail", getParam("id_fail"));

		modelOnline.setDataPermohonan(idpermohonan);
		dataPermohonan = modelOnline.getDataPermohonan();
		String id_daerah = "";
		String id_negeriprojek = "";
		String id_kementerian = "";
		String id_negeri = "";
		if (dataPermohonan.size() != 0) {
			Hashtable dp = (Hashtable) dataPermohonan.get(0);
			id_negeri = dp.get("idNegeri").toString();
			id_kementerian = dp.get("idKementerian").toString();
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
		}

		h.put("id_projekNegeri", id_negeriprojek);
		h.put("idDaerah", id_daerah);
		h.put("id_kementerian", id_kementerian);
		h.put("id_negeri", id_negeri);

		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		// h.put("id_negeriuser", userIdNegeri);

		if (flagStatusOnline.equals("1")) {
			modelOnline.simpanCatatanTolak(idpermohonan);
		} else {
			modelOnline.hantarPermohonan(h);
		}

	}// close sah

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
		if (id_jenisHakmilik.equals("2") || id_jenisHakmilik.equals("5") || id_jenisHakmilik.equals("28")
				|| id_jenisHakmilik.equals("73") || id_jenisHakmilik.equals("74") || id_jenisHakmilik.equals("75")
				|| id_jenisHakmilik.equals("80") || id_jenisHakmilik.equals("85") || id_jenisHakmilik.equals("113")) {
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

	private void hapusHM(HttpSession session) throws Exception {

		String idHakmilik = getParam("id_hakmilik");

		FrmUPTSek8HakmilikData.hapusHM(idHakmilik);

	}// close hapusHM

	@SuppressWarnings("unchecked")
	private void checkFieldValidation(String idHakmilik) throws Exception {

		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		model.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = model.getMaklumatTanah();
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
		if (id_jenishakmilik.equals("2") || id_jenishakmilik.equals("5") || id_jenishakmilik.equals("28")
				|| id_jenishakmilik.equals("73") || id_jenishakmilik.equals("74") || id_jenishakmilik.equals("75")
				|| id_jenishakmilik.equals("80") || id_jenishakmilik.equals("85") || id_jenishakmilik.equals("113")) {
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

	@SuppressWarnings("unchecked")
	private void dataHakmilik(String idHakmilik, String disability) throws Exception {

		myLogger.info("masuk sini ");
		Vector dataMaklumatTanah = new Vector();
		dataMaklumatTanah.clear();

		// data hakmilik
		model.setMaklumatTanah(idHakmilik);
		dataMaklumatTanah = model.getMaklumatTanah();
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
		String id_suburusan = "";
		String id_hakmilik = "";
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
			id_suburusan = h.get("id_suburusan").toString();
			id_hakmilik = h.get("id_hakmilik").toString();
		}
		context.put(id_hakmilik,"id_hakmilik");
		myLogger.info(" id_hakmilik "+id_hakmilik);
		// validation hakmiliik by urusan
		if (id_suburusan.equals("51")) {
			context.put("hideFieldHakmilik", "yes");
		} else {
			context.put("hideFieldHakmilik", "no");
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
		context.put("SelectDaerahPenggawa", HTML.SelectDaerahPenggawa("socDaerahPenggawa",
				Utils.parseLong(id_daerahpenggawa), null, " " + mode + " style=width:274px"));

		// dropdown hakmilik
		if (id_negeriprojek.equals("10")) {
			context.put("selectJenisHakmilik",
					HTML.SelectJenisHakmilikSelangor("socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
							"id=socJenisHakmilik " + mode + " style=width:auto onchange=doOnchangeUpdate()"));
		} else {
			context.put("selectJenisHakmilik",
					HTML.SelectJenisHakmilik("socJenisHakmilik", Utils.parseLong(id_jenishakmilik),
							"id=socJenisHakmilik " + mode + " style=width:auto onchange=doOnchangeUpdate()"));
		}

		context.put("selectKategoriTanah", HTML.SelectKategoriTanah("socKategoriTanah",
				Utils.parseLong(id_kategoritanah), "id=socKategoriTanah " + mode + " style=width:auto", null));
		context.put("selectKodLot",
				HTML.SelectUnitPT("socKodLot", Utils.parseLong(id_lot), "style=width:auto " + mode + " "));

		// dropdown unit luas
		context.put("selectUnitLuasLot", HTML.SelectLuas("socUnitLuasLot", Utils.parseLong(id_luaslot),
				"style=width:250px " + mode + " id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		context.put("selectUnitLuasAmbil", HTML.SelectLuas("socUnitLuasAmbil", Utils.parseLong(id_unitluasambil),
				"style=width:250px " + mode + " id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));

		// dropdown by
		if (id_daerah != "") {
			myLogger.info("masukkk :"+id_daerah);
			context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(id_daerah, "socMukim", Utils.parseLong(id_mukim),
					"style=width:auto " + mode + ""));
		} else {
			context.put("selectMukim",
					HTML.SelectMukimNoKod("socMukim", Utils.parseLong(id_mukim), "style=width:auto " + mode + ""));
		}

	}// close dataHakmilik

	@SuppressWarnings("unchecked")
	private String simpanHM(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();

		String flagSubjaket = getParam("flag_subjaket");

		h.put("txtLokasi", "");
		h.put("txtSyaratNyata", "");
		h.put("txtSyaratKhas", "");
		h.put("txtSekatanKepentingan", "");
		h.put("txtSekatanHak", "");

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
		
//		Simpan Value Luas
		h.put("kodLot", getParam("socKodLot"));
		h.put("txtNoLot", getParam("txtNoLot"));
		h.put("txtNoPT", getParam("txtNoPT"));
		h.put("txtLuasAsal", Utils.RemoveSymbol(getParam("txtLuasAsal")));
		h.put("txtLuasAmbil", Utils.RemoveSymbol(getParam("txtLuasAmbil")));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txtseksyen", getParam("txtSeksyen"));
		h.put("txdTarikhPembayaran", getParam("txdTarikhPembayaran"));
		h.put("unitLuas", getParam("unitLuas"));
		h.put("unitLuasAmbil", getParam("unitLuasAmbil"));		myLogger.info("socUnitLuasAmbil: " +getParam("socUnitLuasAmbil"));
		h.put("txtLuasLotAsalSebelumConvert", getParam("txtLuasLotAsalSebelumConvert"));
		h.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));
		
//		rizab
		h.put("sorJenisRizab", getParam("sorJenisRizab"));
		h.put("txtLain", getParam("txtLain"));
		h.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		
//    	PPT-03 Penambahan Strata
		h.put("txtNoBangunan", getParam("txtNoBangunan"));	myLogger.info("txtNoBangunan: " +getParam("txtNoBangunan"));
    	h.put("txtNoTingkat", getParam("txtNoTingkat"));
    	h.put("txtNoPetak", getParam("txtNoPetak"));
    	
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// pengambilan segera
		h.put("socPSegera", getParam("socPSegera"));

		// FrmUPTSek8HakmilikData.
		return simpanHM2(h, flagSubjaket);
		// FrmUPTSek8HakmilikData.upload(h, getParam("id_permohonan"));

	}// close simpanHM

	public static String simpanHM2(Hashtable data, String flagSubjaket) throws Exception {
		myLogger.info("simpanHM2");
		Db db = null;
		String sql = "";
		String output = "";
		String id_hakmilikString = "";
		Connection conn = null;
		try {
			
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			String id_user = (String) data.get("id_user");
			long id_hakmilik = DB.getNextID("TBLPPTHAKMILIK_SEQ");
			id_hakmilikString = String.valueOf(id_hakmilik);
			// pengambilan segera
			String socPSegera = (String) data.get("socPSegera");
			
			String socDaerahPenggawa = (String) data.get("socDaerahPenggawa");
			String id_permohonan = (String) data.get("id_permohonan");
			String id_negeriProjek = (String) data.get("id_negeriProjek");
			String id_daerahProjek = (String) data.get("id_daerahProjek");
			String id_mukimProjek = (String) data.get("socMukim");
			String txtseksyen = (String) data.get("txtseksyen");
			String catatan = (String) data.get("txtCatatan");
			String txdTarikhPembayaran = (String) data.get("txdTarikhPembayaran");
			String txtnolot = (String) data.get("txtNoLot");
			String txtnopt = (String) data.get("txtNoPT");
			
			String id_jenishakmilik = (String) data.get("jenisHakMilik");
			String no_hakmilik = (String) data.get("txtNoHakmilik");
			String id_lot = (String) data.get("kodLot");
			String id_luasasal = (String) data.get("unitLuas");
			String luas_ambil = (String) data.get("txtLuasAmbil");
			String luas_asal = (String) data.get("txtLuasAsal");
			
			String tarikhLuput = (String) data.get("txdTarikhLuput");
			String tarikhDaftar = (String) data.get("txdTarikhDaftar");
			String baki = (String) data.get("txtBakiTempoh");
			String id_kategoriTanah = (String) data.get("socKategoriTanah");
			String lokasi = (String) data.get("txtLokasi");

			// new
			String id_luasambil = (String) data.get("unitLuasAmbil");
			String nama_luas_asal = (String) data.get("txtLuasLotAsalSebelumConvert");
			String nama_luas_ambil = (String) data.get("txtLuasLotAmbilSebelumConvert");
			String id_unitluaslot_convert = (String) data.get("sorDropdownUnitAsal");
			String id_unitluasambil_convert = (String) data.get("sorDropdownUnitAmbil");
			
			// rizab
			String sorJenisRizab = (String) data.get("sorJenisRizab");
			String txtLain = (String) data.get("txtLain");
			String txtNoWartaRizab = (String) data.get("txtNoWartaRizab");
			String txdTarikhWarta = (String) data.get("txdTarikhWarta");
			
			//	PPT-03 Penambahan Strata
    		String txtNoBangunan = (String)data.get("txtNoBangunan");
    		String txtNoTingkat = (String)data.get("txtNoTingkat");
    		String txtNoPetak =  (String)data.get("txtNoPetak");
    		
			String syaratNyata = (String) data.get("txtSyaratNyata");
			String syaratKhas = (String) data.get("txtSyaratKhas");
			String sekatanKepentingan = (String) data.get("txtSekatanKepentingan");
			String sekatanHak = (String) data.get("txtSekatanHak");
			String noSyit = (String) data.get("txtNoSyit");
			
			String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
			String TL = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
			String TD = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
			String TP = "to_date('" + txdTarikhPembayaran + "','dd/MM/yyyy')";
			
			String flagSebahagian = "0";
			
			if (!luas_asal.isEmpty() && !luas_ambil.isEmpty()) {
				
				// validate sebahagian / keseluruhan
				double luasAsal = Double.parseDouble(luas_asal);
				if (id_unitluaslot_convert.equals("1")) {
					luasAsal *= 10000;
				}
				
				double luasAmbil = Double.parseDouble(luas_ambil);
				if (id_unitluasambil_convert.equals("1")) {
					luasAmbil *= 10000;
				}
				
				if ((luasAsal - luasAmbil) > 0) {
					flagSebahagian = "1";
				} else if ((luasAsal - luasAmbil) == 0) {
					flagSebahagian = "2";
				} else {
					flagSebahagian = "0";
				}
				
			}
			
			// 1 = sebahagian
			// 2 = keseluruhan
			SQLRenderer r = new SQLRenderer();
			r.add("flag_sebahagian", flagSebahagian);
			// flag segera
			r.add("FLAG_SEGERA_SEBAHAGIAN", socPSegera);
			r.add("id_daerahpenggawa", socDaerahPenggawa);
			r.add("id_permohonan", id_permohonan);
			r.add("id_negeri", id_negeriProjek);
			r.add("id_jenishakmilik", id_jenishakmilik);
			r.add("id_daerah", id_daerahProjek);
			r.add("no_warta_rizab", txtNoWartaRizab);
			r.add("tarikh_warta_rizab", r.unquote(TW));
			r.add("flag_jenis_rizab", sorJenisRizab);
			r.add("nama_lain_rizab", txtLain);
			r.add("id_mukim", id_mukimProjek);
			r.add("id_unitluaslot", id_luasasal);
			r.add("id_lot", id_lot);
			r.add("luas_lot", luas_asal);
			r.add("luas_ambil", luas_ambil);
			r.add("no_hakmilik", no_hakmilik);
			r.add("no_lot", txtnolot);
			r.add("no_pt", txtnopt);
			r.add("catatan", catatan);
			r.add("tarikh_pembayaran", r.unquote(TP));
			r.add("seksyen", txtseksyen);
			r.add("tarikh_daftar", r.unquote(TD));
			r.add("tarikh_luput", r.unquote(TL));
			r.add("tempoh_luput", baki);
			r.add("id_kategoritanah", id_kategoriTanah);
			r.add("lokasi", lokasi);
			r.add("syarat_nyata", syaratNyata);
			r.add("syarat_khas", syaratKhas);
			r.add("sekatan_kepentingan", sekatanKepentingan);
			r.add("sekatan_hak", sekatanHak);
			r.add("no_syit", noSyit);
			
			// new
			r.add("id_unitluasambil", id_luasambil);
			r.add("nama_luas_asal", nama_luas_asal);
			r.add("nama_luas_ambil", nama_luas_ambil);
			r.add("id_unitluaslot_convert", id_unitluaslot_convert);
			r.add("id_unitluasambil_convert", id_unitluasambil_convert);
			
//    		PPT-03 Penambahan Strata
    		r.add("no_bangunan",txtNoBangunan);
    		r.add("no_tingkat",txtNoTingkat);
    		r.add("no_petak",txtNoPetak);
    		
			r.add("tarikh_masuk", r.unquote("sysdate"));
			r.add("id_masuk", id_user);
			r.add("id_hakmilik",id_hakmilikString);
			sql = r.getSQLInsert("tblppthakmilik");
			
			myLogger.info("sql add tanah : " + sql);
			stmt.executeUpdate(sql);
			output = ""+id_hakmilikString;
			myLogger.info("id hakmilik "+output);
			conn.commit();
			//uploadFiles(db, conn, id_permohonan);
			// remove subjaket kalau dah ada
			if (flagSubjaket.equals("1")) {
				
				r.clear();
				
				// update flag di tblpptpermohonan
				r.update("id_permohonan", id_permohonan);
				r.add("flag_subjaket", "");
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				r.add("id_kemaskini", id_user);
				
				sql = r.getSQLUpdate("Tblpptpermohonan");
				stmt.executeUpdate(sql);

				r.clear();

				r.update("id_permohonan", id_permohonan);
				r.add("no_subjaket", "");
				r.add("tarikh_kemaskini", r.unquote("sysdate"));
				r.add("id_kemaskini", id_user);
				sql = r.getSQLUpdate("Tblppthakmilik");
				stmt.executeUpdate(sql);
				conn.commit();
				//uploadFiles(db, conn, id_permohonan);
			}

		} catch (Exception re) {
			Category log = null;
			log.error("Error: ", re);
			throw re;
		} // close try
		finally {
			if (db != null)
				db.close();
		} // close finally
		return output;

	}// close simpanHM

	public void updateHM(Hashtable data, String flagSubjaket) throws Exception
	  {
		
	    Db db = null;
	    String sql = "";
	    Connection conn = null;
	    
	    try{
	      
	    		db = new Db();
	    		Statement stmt = db.getStatement();
	    		
	    		String id_user = (String)data.get("id_user");
	    	
	    		//pengambilan segera
	    		String socPSegera = (String)data.get("socPSegera");
	    		
	    		String socDaerahPenggawa = (String)data.get("socDaerahPenggawa");
	    		
	    		myLogger.info("Check Value update2");
	    		String id_permohonan = (String) data.get("id_permohonan");
	    		myLogger.info("id_permohonan==="+sql);
	    		String id_hakmilik = (String)data.get("id_hakmilik");	myLogger.info("id_hakmilik: " +id_hakmilik);
	    		String id_daerah = (String)data.get("id_daerah");   	myLogger.info("id_daerah: " +id_daerah);
	    		
	    		String id_mukimProjek = (String)data.get("socMukim");   	myLogger.info("id_mukimProjek: " +id_mukimProjek);
	    		String txtseksyen = (String)data.get("txtseksyen"); 
	    		String catatan = (String)data.get("txtCatatan");
	    		String txdTarikhPembayaran = (String) data.get("txdTarikhPembayaran");
	    		String txtnolot = (String)data.get("txtNoLot");
	    		String txtnopt = (String)data.get("txtNoPT");
	    		
	    		String id_jenishakmilik = (String)data.get("jenisHakMilik");
	    		String no_hakmilik = (String)data.get("txtNoHakmilik");
	    		String id_lot = (String)data.get("kodLot");  
	    		String id_luas = (String)data.get("unitLuas");	myLogger.info("id_luas: " +id_luas);
	    		String luas_ambil = (String)data.get("txtLuasAmbil");	myLogger.info("luas_ambil: " +luas_ambil);
	    		String luas_asal = (String)data.get("txtLuasAsal");	myLogger.info("luas_asal: " +luas_asal);

	    		String tarikhLuput = (String)data.get("txdTarikhLuput");	 
	    		String tarikhDaftar = (String)data.get("txdTarikhDaftar");
	    		String baki = (String)data.get("txtBakiTempoh");
	    		String id_kategoriTanah = (String)data.get("socKategoriTanah");
	    		String lokasi = (String)data.get("txtLokasi");	
	    	
	    		//rizab
		    	String sorJenisRizab = (String)data.get("sorJenisRizab");
		    	String txtLain = (String)data.get("txtLain");
		    	String txtNoWartaRizab = (String)data.get("txtNoWartaRizab");
		    	String txdTarikhWarta = (String)data.get("txdTarikhWarta");
		    	 
	    		String syaratNyata = (String)data.get("txtSyaratNyata");	 
	    		String syaratKhas = (String)data.get("txtSyaratKhas");
	    		String sekatanKepentingan = (String)data.get("txtSekatanKepentingan");
	    		String sekatanHak = (String)data.get("txtSekatanHak");
	    		String noSyit = (String)data.get("txtNoSyit");	
	    		
	    		//new
	    		String id_luasambil = (String)data.get("unitLuasAmbil");	myLogger.info("id_luasambil: " +id_luasambil);
	    		String nama_luas_asal = (String)data.get("txtLuasLotAsalSebelumConvert");  	myLogger.info("nama_luas_asal: " +nama_luas_asal);
	    		String nama_luas_ambil = (String)data.get("txtLuasLotAmbilSebelumConvert");	myLogger.info("nama_luas_ambil: " +nama_luas_ambil);
	    		String id_unitluaslot_convert = (String)data.get("sorDropdownUnitAsal");	myLogger.info("id_unitluaslot_convert: " +id_unitluaslot_convert);
	    		String id_unitluasambil_convert = (String)data.get("sorDropdownUnitAmbil");		myLogger.info("id_unitluasambil_convert: " +id_unitluasambil_convert);
	    		
	    		
	    		String TL = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";
	    		String TD = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";
	    		String TW = "to_date('" + txdTarikhWarta + "','dd/MM/yyyy')";
	    		String TP = "to_date('" + txdTarikhPembayaran + "','dd/MM/yyyy')";
	    		
	    		//PPT-03 Penambahan Strata
	    		String txtNoBangunan = (String)data.get("no_bangunan");
	    		String txtNoTingkat = (String)data.get("no_tingkat");
	    		String txtNoPetak =  (String)data.get("no_petak"); 		 myLogger.info("txtNoPetak: " +txtNoPetak);
	    		
	    		String flagSebahagian = "0";
	    		
	    		if(!luas_asal.isEmpty() && !luas_ambil.isEmpty()){
	    			
	    			//validate sebahagian / keseluruhan
	    			double luasAsal = Double.parseDouble(luas_asal);
	    			if(id_unitluaslot_convert.equals("1")){
	    				luasAsal *= 10000;
	    			}
	    		
	    			double luasAmbil = Double.parseDouble(luas_ambil);
	    			if(id_unitluasambil_convert.equals("1")){
	    				luasAmbil *= 10000;
	    			}
	    		
	    			if((luasAsal - luasAmbil) > 0 ){
	    				flagSebahagian = "1";
	    			}else if((luasAsal - luasAmbil) == 0 ){
	    				flagSebahagian = "2";
	    			}else{
	    				flagSebahagian = "0";
	    			}
	    			
	    		}
	    		// 1 = sebahagian
				// 2 = keseluruhan
	    		SQLRenderer r = new SQLRenderer();
	    		r.update("id_hakmilik", id_hakmilik);
	    		r.add("flag_sebahagian", flagSebahagian);
	    		r.add("FLAG_SEGERA_SEBAHAGIAN", socPSegera);
	    		
	    		//flag segera
	    		r.add("id_daerahpenggawa", socDaerahPenggawa);
	    		r.add("id_permohonan", id_permohonan);
	    		r.add("id_jenishakmilik", id_jenishakmilik);
	    		r.add("id_daerah", id_daerah);
	    		r.add("id_mukim", id_mukimProjek);
	    		r.add("id_unitluaslot", id_luas);
	    		r.add("id_lot", id_lot);
	    		r.add("luas_lot",luas_asal);
	    		r.add("no_warta_rizab", txtNoWartaRizab); 	
		    	r.add("tarikh_warta_rizab", r.unquote(TW));
		    	r.add("flag_jenis_rizab", sorJenisRizab); 	
		    	r.add("nama_lain_rizab", txtLain);
	    		r.add("luas_ambil", luas_ambil);
	    		r.add("no_hakmilik", no_hakmilik);
	    		r.add("no_lot", txtnolot);
	    		r.add("no_pt", txtnopt);
	    		r.add("catatan",catatan);
	    		r.add("tarikh_pembayaran", r.unquote(TP));
	    		r.add("seksyen",txtseksyen);	    		
	    		r.add("tarikh_daftar",r.unquote(TD));
	    		r.add("tarikh_luput",r.unquote(TL));
	    		r.add("tempoh_luput", baki);
	    		r.add("id_kategoritanah",id_kategoriTanah);
	    		r.add("lokasi",lokasi);	    		
	    		r.add("syarat_nyata", syaratNyata);
	    		r.add("syarat_khas", syaratKhas);
	    		r.add("sekatan_kepentingan",sekatanKepentingan);
	    		r.add("sekatan_hak",sekatanHak);
	    		r.add("no_syit",noSyit);
	    		
//	    		PPT-03 Strata
	    		r.add("no_bangunan",txtNoBangunan);
	    		r.add("no_tingkat",txtNoTingkat);
	    		r.add("no_petak",txtNoPetak);
	    		
	    		//new
	    		r.add("id_unitluasambil", id_luasambil);
	    		r.add("nama_luas_asal", nama_luas_asal);
	    		r.add("nama_luas_ambil", nama_luas_ambil);
	    		r.add("id_unitluaslot_convert", id_unitluaslot_convert);
	    		r.add("id_unitluasambil_convert", id_unitluasambil_convert);	
	    		
	    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
	    		r.add("id_kemaskini",id_user); 
	    		
	    		sql = r.getSQLUpdate("TBLPPTHAKMILIK");
	    		stmt.executeUpdate(sql);
	    		myLogger.info("updateHM ****  : "+sql);
	    		myLogger.info("flagSubjaket ****  : "+flagSubjaket);
	    		// remove subjaket kalau dah ada
				//if (flagSubjaket.equals("1")) {
					
					r.clear();
					
					// update flag di tblpptpermohonan
					myLogger.info("Baca id_permohonan upload:--------------"+id_permohonan);
					r.update("id_permohonan", id_permohonan);
					r.add("flag_subjaket", "");
					r.add("tarikh_kemaskini", r.unquote("sysdate"));
					r.add("id_kemaskini", id_user);
					
					sql = r.getSQLUpdate("Tblpptpermohonan");
					myLogger.info("Baca sql:--------------"+sql);
					stmt.executeUpdate(sql);

					r.clear();

					r.update("id_permohonan", id_permohonan);
					r.add("no_subjaket", "");
					r.add("tarikh_kemaskini", r.unquote("sysdate"));
					r.add("id_kemaskini", id_user);
					sql = r.getSQLUpdate("Tblppthakmilik");
					stmt.executeUpdate(sql);
					conn.commit();
					//uploadFiles(db, conn, id_permohonan);
				//}
  	
	    } catch (Exception re) {
	    	Category log = null;
	    	log.error("Error: ", re);
	    	throw re;
	    	}//close try 
	    finally {
	      if (db != null) db.close();
	    }//close finally
	   
	  }//close updateHM
	// upload start

	/*private void uploadFiles(Db db, Connection conn, String id_permohonan) throws Exception {
		myLogger.info("Baca uploadFiles:--------------");
		String nama_pemohon_lama2 = id_permohonan;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		myLogger.info("Baca uploadFiles1:--------------");
		List items = upload.parseRequest(request);
		myLogger.info("Baca uploadFiles2:--------------");
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
				//System.out.println("item.getName = " + item.getName());
				saveData(item, db, conn, id_permohonan);
			}
		}
	}*/

	/*private static void saveData(FileItem item, Db db, Connection conn, String id_permohonan) throws Exception {
		// Db db = null;
		myLogger.info("read here saveData");
		try {
			db = new Db();

			Connection con = db.getConnection();
			con.setAutoCommit(false);
			String id_permohonan2 = id_permohonan;
			// String id_permohonansimati = getParam("id_permohonansimati_atheader");
			PreparedStatement ps = con.prepareStatement(
					//"UPDATE tblpptdokumenhakmilik SET nama_dokumen = ?, kandungan = ?, format = ? WHERE (id_permohonan = ?)");
					"INSERT INTO tblpptdokumenhakmilik (nama_dokumen,kandungan,format,id_permohonan)VALUES(?,?,?,?)");
			// System.out.println("+nama_pemohon_lama3+ " + nama_pemohon_lama3);
			// System.out.println(con.prepareStatement("UPDATE TBLPPKTUKARPEMOHON SET bukti
			// = ?, content = ?, jenis_Mime = ? WHERE ID_PERMOHONANSIMATI = ?"));
			ps.setString(1, item.getName());
			ps.setBinaryStream(2, item.getInputStream(), (int) item.getSize());
			ps.setString(3, item.getContentType());
			// System.out.println("item.getInputStream = "+ item.getInputStream());
			// System.out.println("item.getSize = "+ item.getSize());
			// System.out.println("item.getContentType = "+ item.getContentType());
			ps.setString(4, id_permohonan2);
			// ps.setString(5,nama_pemohon_lama3);
			// ps.setString(4,getParam("id_permohonansimati_atheader"));
			myLogger.info("id_permohonan:---------------" + id_permohonan);
			myLogger.info("Baca SaveData:---------------");
			myLogger.info("Baca SaveData:************");
			myLogger.info("ps.executeUpdate()" + ps.executeUpdate());
			ps.executeUpdate();
			myLogger.info("Baca SaveData 2:---------------");
			con.commit();
		} finally {
			if (db != null)
				db.close();
		}
	}*/

	@SuppressWarnings("unchecked")
	private void updateHM(HttpSession session, String idHakmilik, String id_projekDaerah) throws Exception {

		Hashtable h = new Hashtable();
		String flagSubjaket = getParam("flag_subjaket");
		
		myLogger.info("Check Value update1");
		
		h.put("txtLokasi", "");
		h.put("txtSyaratNyata", "");
		h.put("txtSyaratKhas", "");
		h.put("txtSekatanKepentingan", "");
		h.put("txtSekatanHak", "");
		
		h.put("id_permohonan", getParam("id_permohonan"));
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
		h.put("txtLuasAsal", Utils.RemoveSymbol(getParam("txtLuasAsal")));	myLogger.info("txtLuasAsal betul tak?: " +Utils.RemoveSymbol(getParam("txtLuasAsal")));
		h.put("txtLuasAmbil", Utils.RemoveSymbol(getParam("txtLuasAmbil")));	myLogger.info("txtLuasAmbil betul tak?: " +Utils.RemoveSymbol(getParam("txtLuasAmbil")));
		h.put("txtCatatan", getParam("txtCatatan"));
		h.put("txdTarikhPembayaran", getParam("txdTarikhPembayaran"));
		h.put("txtseksyen", getParam("txtSeksyen"));
		
		h.put("unitLuas", getParam("unitLuas"));	myLogger.info("unitLuas: " +getParam("unitLuas"));
		h.put("unitLuasAmbil", getParam("unitLuasAmbil"));	myLogger.info("unitLuasAmbil: " +getParam("socUnitLuasAmbil"));
		h.put("txtLuasLotAsalSebelumConvert", getParam("txtLuasLotAsalSebelumConvert"));	myLogger.info("txtLuasLotAsalSebelumConvert: " +getParam("txtLuasLotAsalSebelumConvert"));
		h.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));	myLogger.info("txtLuasLotAmbilSebelumConvert: " +getParam("txtLuasLotAmbilSebelumConvert"));
		h.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));	myLogger.info("sorDropdownUnitAsal: " +getParam("sorDropdownUnitAsal"));
		h.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));	myLogger.info("sorDropdownUnitAmbil: " +getParam("sorDropdownUnitAmbil"));
		
		// rizab
		h.put("sorJenisRizab", getParam("sorJenisRizab"));
		h.put("txtLain", getParam("txtLain"));
		h.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		
//    	PPT-03 Penambahan Strata
		h.put("no_bangunan", getParam("txtNoBangunan"));	
    	h.put("no_tingkat", getParam("txtNoTingkat"));	
    	h.put("no_petak", getParam("txtNoPetak"));
    	
		// pengambilan segera
		h.put("socPSegera", getParam("socPSegera"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		updateHM(h, flagSubjaket);

	}// close updateHM

	private void changeUnitAmbil() throws Exception {

		String showButtonConvertAmbil = getParam("showButtonConvertAmbil");

		if (showButtonConvertAmbil.equals("yes")) {
			context.put("showButtonConvertAmbil", "yes");
		} else {
			context.put("showButtonConvertAmbil", "no");
		}

		String unitConvert = getParam("sorDropdownUnitAmbil");
		context.put("sorDropdownUnitAmbil", unitConvert);

		String txtLuasLotAmbil = Utils.RemoveSymbol(getParam("txtLuasLotAmbil"));

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
			context.put("selectUnitLuasLot", HTML.SelectLuas("socUnitLuasLot", null,
					"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		} else {
			context.put("selectUnitLuasLot", HTML.SelectLuas("socUnitLuasLot", null,
					"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
		}

	}// close clearConvertAsal

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
			context.put("selectUnitLuasAmbil", HTML.SelectLuas("socUnitLuasAmbil", null,
					"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		} else {
			context.put("selectUnitLuasAmbil", HTML.SelectLuas("socUnitLuasAmbil", null,
					"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}

	}// close clearConvertAmbil

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

		} // close kilometer persegi

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

		} // close hektar

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

		} // close meter persegi

		// 4 = ekar/rood/pole
		if (unitLuasAsal.equals("4")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2) / 4)
						+ (Double.parseDouble(luasAsal3) / 160)) * 0.404686;
				sorDropdownUnitAsal = "1";
			} else {
				total = (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2) / 4)
						+ (Double.parseDouble(luasAsal3) / 160)) * 4046.86;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";

		} // close ekar/rood/pole

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

		} // close kaki persegi

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

		} // close ekar perpuluhan

		// 7 = ekar/depa
		if (unitLuasAsal.equals("7")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2) / 1000)) * 0.404686;
				sorDropdownUnitAsal = "1";
			} else {
				total = (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2) / 1000)) * 4046.86;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";

		} // close ekar/depa

		// 8 = relong/jemba/kaki persegi
		if (unitLuasAsal.equals("8")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2) / 484)
						+ (Double.parseDouble(luasAsal3) / 30976)) * 0.711111 * 0.404686;
				sorDropdownUnitAsal = "1";
			} else {
				total = (Double.parseDouble(luasAsal1) + (Double.parseDouble(luasAsal2) / 484)
						+ (Double.parseDouble(luasAsal3) / 30976)) * 0.711111 * 4046.86;
				sorDropdownUnitAsal = "2";
			}

			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";

		} // close relong/jemba/kaki persegi

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

		} // close batu nautika

		// put data luas asal
		context.put("txtLuasLotAsal", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAsal", sorDropdownUnitAsal);
		context.put("txtLuasLotAsalSebelumConvert", luasAsal1 + " " + unitSebelumConvert1 + " " + luasAsal2 + " "
				+ unitSebelumConvert2 + " " + luasAsal3 + " " + unitSebelumConvert3);
		context.put("showBoxAsal2", "no");
		context.put("showBoxAsal3", "no");
		context.put("showButtonConvertAsal", "no");

	}// close calculateNilaiAsal

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

		} // close kilometer persegi

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

		} // close hektar

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

		} // close meter persegi

		// 4 = ekar/rood/pole
		if (unitLuasAmbil.equals("4")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2) / 4)
						+ (Double.parseDouble(luasAmbil3) / 160)) * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2) / 4)
						+ (Double.parseDouble(luasAmbil3) / 160)) * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "ROOD";
			unitSebelumConvert3 = "POLE";

		} // close ekar/rood/pole

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

		} // close kaki persegi

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

		} // close ekar perpuluhan

		// 7 = ekar/depa
		if (unitLuasAmbil.equals("7")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2) / 1000)) * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2) / 1000)) * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "EKAR";
			unitSebelumConvert2 = "DEPA";

		} // close ekar/depa

		// 8 = relong/jemba/kaki persegi
		if (unitLuasAmbil.equals("8")) {
			if (id_kategoritanah.equals("2")) {
				total = (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2) / 484)
						+ (Double.parseDouble(luasAmbil3) / 30976)) * 0.711111 * 0.404686;
				sorDropdownUnitAmbil = "1";
			} else {
				total = (Double.parseDouble(luasAmbil1) + (Double.parseDouble(luasAmbil2) / 484)
						+ (Double.parseDouble(luasAmbil3) / 30976)) * 0.711111 * 4046.86;
				sorDropdownUnitAmbil = "2";
			}

			unitSebelumConvert1 = "RELONG";
			unitSebelumConvert2 = "JEMBA";
			unitSebelumConvert3 = "KAKI PERSEGI";

		} // close relong/jemba/kaki persegi

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

		} // close batu nautika

		// put data luas ambil
		context.put("txtLuasLotAmbil", Utils.formatLuasHM(total));
		context.put("sorDropdownUnitAmbil", sorDropdownUnitAmbil);
		context.put("txtLuasLotAmbilSebelumConvert", luasAmbil1 + " " + unitSebelumConvert1 + " " + luasAmbil2 + " "
				+ unitSebelumConvert2 + " " + luasAmbil3 + " " + unitSebelumConvert3);
		context.put("showBoxAmbil2", "no");
		context.put("showBoxAmbil3", "no");
		context.put("showButtonConvertAmbil", "no");

	}// close calculateNilaiAmbil

	@SuppressWarnings("unchecked")
	private void getAndSetHM(String idpermohonan, String mode, String id_projekNegeri) throws Exception {

		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();

		if (mode.equals("new")) {

			if (id_projekNegeri.equals("10")) {
				context.put("selectJenisHakmilik",
						HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",
								Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
			} else {
				context.put("selectJenisHakmilik",
						HTML.SelectJenisHakmilik("socJenisHakmilik", Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
			}

			// dropdown unit luas
			context.put("selectUnitLuasLot",
					HTML.SelectLuas("socUnitLuasLot", Utils.parseLong(getParam("socUnitLuasLot")),
							"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
			context.put("selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil", Utils.parseLong(getParam("socUnitLuasAmbil")),
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));
		} else {

			if (id_projekNegeri.equals("10")) {
				context.put("selectJenisHakmilik",
						HTML.SelectJenisHakmilikSelangor("socJenisHakmilik",
								Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchangeUpdate()"));
			} else {
				context.put("selectJenisHakmilik",
						HTML.SelectJenisHakmilik("socJenisHakmilik", Utils.parseLong(getParam("socJenisHakmilik")),
								"id=socJenisHakmilik style=width:auto onchange=doOnchangeUpdate()"));
			}

			// dropdown unit luas
			context.put("selectUnitLuasLot",
					HTML.SelectLuas("socUnitLuasLot", Utils.parseLong(getParam("socUnitLuasLot")),
							"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsalUpdate()"));
			context.put("selectUnitLuasAmbil",
					HTML.SelectLuas("socUnitLuasAmbil", Utils.parseLong(getParam("socUnitLuasAmbil")),
							"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbilUpdate()"));
		}

		String id_daerah = "";
		modelOnline.setDataPermohonan(idpermohonan);
		dataPermohonan = modelOnline.getDataPermohonan();
		if (dataPermohonan.size() != 0) {
			Hashtable dp = (Hashtable) dataPermohonan.get(0);
			id_daerah = dp.get("idDaerah").toString();
		}

		// untuk kelantan shj
		context.put("SelectDaerahPenggawa", HTML.SelectDaerahPenggawa("socDaerahPenggawa",
				Utils.parseLong(getParam("socDaerahPenggawa")), null, "style=width:274px"));

		// dropdown
		context.put("selectKategoriTanah", HTML.SelectKategoriTanah("socKategoriTanah",
				Utils.parseLong(getParam("socKategoriTanah")), "id=socKategoriTanah style=width:auto", null));
		context.put("selectKodLot",
				HTML.SelectUnitPT("socKodLot", Utils.parseLong(getParam("socKodLot")), "style=width:auto"));

		// dropdown by
		if (id_daerah != "") {
			context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(id_daerah, "socMukim",
					Utils.parseLong(getParam("socMukim")), "style=width:auto"));
		} else {
			context.put("selectMukim",
					HTML.SelectMukimNoKod("socMukim", Utils.parseLong(getParam("socMukim")), "style=width:auto"));
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
		context.put("txtNoBangunan", getParam("txtNoBangunan"));
		context.put("txtNoTingkat", getParam("txtNoTingkat"));
		context.put("txtNoPetak", getParam("txtNoPetak"));

		if (resetRadio.equals("1")) {
			context.put("sorJenisRizab", "");
		} else {
			context.put("sorJenisRizab", getParam("sorJenisRizab"));
		}

		context.put("txtLain", getParam("txtLain"));
		context.put("txtNoWartaRizab", getParam("txtNoWartaRizab"));
		context.put("txdTarikhWarta", getParam("txdTarikhWarta"));
		context.put("txtCatatan", getParam("txtCatatan"));
		context.put("txdTarikhPembayaran", getParam("txdTarikhPembayaran"));

		context.put("txtLuasLotAsalSebelumConvert", getParam("txtLuasLotAsalSebelumConvert"));
		context.put("sorDropdownUnitAsal", getParam("sorDropdownUnitAsal"));

		context.put("txtLuasLotAmbilSebelumConvert", getParam("txtLuasLotAmbilSebelumConvert"));
		context.put("sorDropdownUnitAmbil", getParam("sorDropdownUnitAmbil"));

	}// close getAndSetHM

	private void clearValueHM() throws Exception {

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
		context.put("txdTarikhPembayaran", "");
		context.put("txtNoBangunan", "");
		context.put("txtNoTingkat", "");
		context.put("txtNoPetak", "");

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
		String id_urusan = "";
		String jumlah_hakmilik = "";

		modelOnline.setDataPermohonan(idpermohonan);
		dataPermohonan = modelOnline.getDataPermohonan();
		if (dataPermohonan.size() != 0) {
			Hashtable dp = (Hashtable) dataPermohonan.get(0);
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
			nama_daerah = dp.get("daerah").toString();
			nama_negeriprojek = dp.get("nama_negeriprojek").toString();
			id_urusan = dp.get("idSuburusan").toString();
			jumlah_hakmilik = dp.get("jumlah_hakmilik").toString();
		}

		context.put("id_daerah", id_daerah);
		context.put("id_negeriprojek", id_negeriprojek);
		context.put("jumlah_hakmilik", jumlah_hakmilik);
		context.put("nama_daerah", nama_daerah);
		context.put("nama_negeriprojek", nama_negeriprojek);

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		// untuk kelantan shj
		context.put("SelectDaerahPenggawa",
				HTML.SelectDaerahPenggawa("socDaerahPenggawa", null, null, "style=width:274px"));

		// dropdown
		if (id_negeriprojek.equals("10")) {
			context.put("selectJenisHakmilik", HTML.SelectJenisHakmilikSelangor("socJenisHakmilik", null,
					"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
		} else {
			context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", null,
					"id=socJenisHakmilik style=width:auto onchange=doOnchange()"));
		}

		context.put("selectKategoriTanah",
				HTML.SelectKategoriTanah("socKategoriTanah", null, "id=socKategoriTanah style=width:auto", null));
		context.put("selectKodLot", HTML.SelectUnitPT("socKodLot", null, "style=width:auto"));

		// dropdown unit luas
		context.put("selectUnitLuasLot", HTML.SelectLuas("socUnitLuasLot", null,
				"style=width:250px id=socUnitLuasLot onchange=onchangeUnitLuasAsal()"));
		context.put("selectUnitLuasAmbil", HTML.SelectLuas("socUnitLuasAmbil", null,
				"style=width:250px id=socUnitLuasAmbil onchange=onchangeUnitLuasAmbil()"));

		// dropdown by
		if (id_daerah != "") {
			context.put("selectMukim", HTML.SelectMukimNoKodByDaerah(id_daerah, "socMukim", null, "style=width:auto"));
		} else {
			context.put("selectMukim", HTML.SelectMukimNoKod("socMukim", null, "style=width:auto"));
		}

		if (id_urusan.equals("51")) {
			context.put("hideFieldHakmilik", "yes");
		} else {
			context.put("hideFieldHakmilik", "no");
		}

	}// close newDataSetting

	@SuppressWarnings("unchecked")
	private void hapusDokumen(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();
		h.put("id_dokumen", getParam("id_dokumen"));
		FrmPermohonanUPTData.hapusDokumen(h);

	}// close hapusdokumen
	
	@SuppressWarnings("unchecked")
	private void hapusDokumenPembayaran(HttpSession session) throws Exception {

		Hashtable h = new Hashtable();
		h.put("id_dokumen", getParam("id_dokumen"));
		FrmPermohonanUPTData.hapusDokumenPembayaran(h);

	}// close hapusdokumenpembayaran
	
	private void uploadFiles(String id_permohonan, String txdTarikhPembayaran,
			 HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
				saveData(item, id_permohonan, txdTarikhPembayaran, session);
			}
		}
	}
	
	private void saveData(FileItem item, String id_permohonan,
			String txdTarikhPembayaran, HttpSession session) throws Exception {
		//System.out.println("saveDAta ");
		Db db = null;
		Date date = null;
		long id_Dokumen = DB.getNextID("TBLPPTDOKUMENHAKMILIK_SEQ");
		String userId = (String) session.getAttribute("_ekptg_user_id"); 
		//String TP = "to_date('" + txdTarikhPembayaran + "','dd/MM/yyyy')";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		try {
			 date = format.parse(txdTarikhPembayaran);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			db = new Db();
			// TBLPPKDOKUMENSENARAIHUTANG
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPPTDOKUMENHAKMILIK"
							+ " (ID_DOKUMEN, ID_PERMOHONAN, NAMA_DOKUMEN, KANDUNGAN, FORMAT, ID_MASUK, TARIKH_PEMBAYARAN, TARIKH_MASUK)"
							+ " VALUES(?,?,?,?,?,?,?,SYSDATE)");
			
			ps.setLong(1, id_Dokumen);
			//ps.setLong(1, Long.valueOf(id_permohonan));
			ps.setString(2, id_permohonan);
			//ps.setString(3, namaDokumen);
			ps.setString(3, item.getName());
			ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize()); //content
			ps.setString(5, item.getContentType()); //jenis mime
			ps.setString(6, userId);
			ps.setDate(7, new java.sql.Date(date.getTime()));
			//ps.setString(6, txdTarikhPembayaran);
			//System.out.println("ps : " + ps.toString());
			ps.executeUpdate();
			con.commit();
		} catch (SQLException se) {
			throw new Exception("Ralat : Masalah muatnaik fail");
		} finally {
			if (db != null)
				db.close();
		}
		
		context.put("id_permohonan", id_permohonan);
	}

	@SuppressWarnings("unchecked")
	private void uploadFiles() throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		List items = upload.parseRequest(request);
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
				saveData(item);
			}
		}
	}

	private void saveData(FileItem item) throws Exception {
		Db db = null;
		try {
			db = new Db();
			long id_Dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN "
					+ "(id_Dokumen,id_permohonan,nama_Fail,jenis_Mime,content,tajuk,keterangan,id_jenisdokumen) "
					+ "values(?,?,?,?,?,?,?,?)");
			ps.setLong(1, id_Dokumen);
			ps.setString(2, getParam("id_permohonan"));
			ps.setString(3, item.getName());
			ps.setString(4, item.getContentType());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, getParam("nama_dokumen"));
			ps.setString(7, getParam("keterangan"));
			ps.setString(8, getParam("id_jenisdokumen"));
			ps.executeUpdate();
			con.commit();

		} catch (SQLException se) {
			throw new Exception("Ralat : Masalah muatnaik fail");
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	private String dataHeader(String idpermohonan) throws Exception {

		String id_status = "";
		header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if (dataHeader.size() != 0) {
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String) dh.get("id_status");
		}

		return id_status;

	}// close dataHeader

	@SuppressWarnings({ "unchecked" })
	private void ListHakmilik(String idpermohonan, String noLOT, String idpegawai) throws Exception {
		/*
		 * Vector listHakmilik = new Vector(); listHakmilik.clear();
		 * 
		 * 
		 * model.setListMaklumatTanah(idpermohonan, noLOT, idpegawai); listHakmilik =
		 * model.getListMaklumatTanah(); if (listHakmilik.size() != 0) { Hashtable lmt =
		 * (Hashtable) listHakmilik.get(0); nama2Mukim = (String) lmt.get("nama2Mukim");
		 * }
		 * 
		 * // data context.put("listMaklumatTanah", listHakmilik);
		 * context.put("saiz_listTanah", listHakmilik.size()); context.put("nama2Mukim",
		 * nama2Mukim);
		 */
		String nama2Mukim = "";
		Hashtable senarai_mukim_lot = model.getListMukimLot(idpermohonan, noLOT, idpegawai, 0, 0);
		if (senarai_mukim_lot.size() != 0) {
			nama2Mukim = senarai_mukim_lot.get("nama2Mukim").toString();
		}
		// context.put("listMaklumatTanah", listHakmilik);
		context.put("saiz_listTanah", model.setListMaklumatTanah_count(idpermohonan, noLOT, idpegawai));
		context.put("nama2Mukim", nama2Mukim);

	}// close ListHakmilik

	@SuppressWarnings({ "unchecked", "static-access" })
	private void ListDokumen(String idpermohonan) throws Exception {

		Vector listDokumen = new Vector();
		listDokumen.clear();

		Vector jenis_dokumen = new Vector();

		jenis_dokumen = model.dropdown_jenisdokumen();
		this.context.put("jenis_dokumen", jenis_dokumen);

		model.setListDokumen(idpermohonan);
		listDokumen = model.getListDokumen();
		context.put("listDokumen", listDokumen);
		context.put("listD_size", listDokumen.size());

	}// close ListDokumen
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void ListDokumenPembayaran(String id_permohonan) throws Exception {

		Vector listDokumenPembayaran = new Vector();
		listDokumenPembayaran.clear();

		model.setListDokumenPembayaran(id_permohonan);
		listDokumenPembayaran = model.getListDokumenPembayaran();
		context.put("listDokumenPembayaran", listDokumenPembayaran);
		context.put("listDPem_size", listDokumenPembayaran.size());

	}// close ListDokumen

	@SuppressWarnings({ "unchecked" })
	private void DataPermohonan(String idpermohonan, String disability) throws Exception {

		Vector dataPermohonan = new Vector();
		dataPermohonan.clear();

		String id_agensi = "";
		String id_negeri = "";
		String id_kementerian = "";
		String id_daerah = "";
		String id_negeriprojek = "";
		String id_urusan = "";
		String ulasanjt = "";

		modelOnline.setDataPermohonan(idpermohonan);
		dataPermohonan = modelOnline.getDataPermohonan();

		// data
		context.put("dataPermohonan", dataPermohonan);

		if (dataPermohonan.size() != 0) {
			Hashtable dp = (Hashtable) dataPermohonan.get(0);
			id_agensi = dp.get("idAgensi").toString();
			id_negeri = dp.get("idNegeri").toString();
			id_kementerian = dp.get("idKementerian").toString();
			id_daerah = dp.get("idDaerah").toString();
			id_negeriprojek = dp.get("idProjekNegeri").toString();
			id_urusan = dp.get("idSuburusan").toString();
			// ulasanjt = dp.get("ulasanjt").toString();

		}

		// context.put("ulasanjt", ulasanjt);

		String mode = "";
		if (disability.equals("enabled")) {
			mode = "";
		} else {
			mode = "disabled class=disabled";
		}

		// dropdown by
		if (id_kementerian != "") {
			context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,
					Utils.parseLong(id_agensi), "id=socAgensi " + mode + " style=width:500px"));
		} else {
			context.put("selectAgensi", HTML.SelectAgensi("socAgensi", Utils.parseLong(id_agensi),
					"id=socAgensi " + mode + " style=width:500px"));
		}

		if (id_negeriprojek != "") {
			context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeriprojek, "socDaerah",
					Utils.parseLong(id_daerah), null, "id=socDaerah " + mode + " style=width:300px"));
		} else {
			context.put("selectDaerah", HTML.SelectDaerah("socDaerah", Utils.parseLong(id_daerah), null,
					"id=socDaerah " + mode + " style=width:300px"));
		}

		// dropdown
		context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian), "",
				"disabled class=disabled style=width:500px " + mode + " onChange=\"doChangeKementerianUpdate();\""));
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri),
				"class=disabled disabled style=width:325px"));
		context.put("selectNegeriProjek", HTML.SelectNegeriMampu("socNegeriProjek", Utils.parseLong(id_negeriprojek),
				null, "style=width:300px " + mode + " onChange=\"doChangeProjekNegeriUpdate();\""));

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		// onchange validation
		if (id_urusan.equals("52")) {
			context.put("showSegera", "yes");
		} else {
			context.put("showSegera", "no");
		}

	}// close DataPermohonan

	//senarai semak
	@SuppressWarnings({ "unchecked", "static-access" })
	private void ListCheckBox(String idpermohonan) throws Exception {

		Vector senaraiSemak = new Vector();
		senaraiSemak.clear();

		String id_senaraisemak = "";
		senaraiSemak = model.getSenaraiSemakan(idpermohonan);
		if (senaraiSemak.size() != 0) {
			Hashtable ss = (Hashtable) senaraiSemak.get(0);
			id_senaraisemak = (String) ss.get("id_senaraisemak");
		}

		// data and id
		context.put("id_senaraisemak", id_senaraisemak);
		context.put("senaraiSemakan", senaraiSemak);

	}// close ListCheckBox

	@SuppressWarnings("unchecked")
	private String simpanPendaftaran(HttpSession session, String userIdKementerian) throws Exception {

		Hashtable h = new Hashtable();

		h.put("suburusan", getParam("sorUrusan"));
		h.put("no_rujukan_ptg", getParam("txtNoRujukanPTG"));
		h.put("no_rujukan_ptd", getParam("txtNoRujukanPTD"));
		h.put("no_rujukan_upt", getParam("txtNoRujukanUPT"));
		h.put("kementerian", userIdKementerian);
		h.put("agensi", getParam("socAgensi"));
		h.put("flag_peruntukan", getParam("sorFlagPeruntukan"));
		h.put("flag_segera", getParam("sorFlagSegera"));
		h.put("daerah", getParam("socDaerah"));
		h.put("tujuan", getParam("txtTujuan"));
		h.put("rujukan_kementerian", getParam("txtRujukanKementerian"));
		h.put("tarikh_kehendaki", getParam("txdTarikhKehendaki"));
		h.put("tarikh_surat", getParam("txdTarikhSurat"));
		h.put("projek_negeri", getParam("socNegeriProjek"));
		h.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		h.put("tarikh_permohonan_kjp", getParam("tarikh_permohonan_kjp"));
		h.put("ulasanjt", getParam("txtUlasan"));

		h.put("jenis_projek", getParam("sorJenisProjek"));

		h.put("jumlahHakmilik", getParam("txtJumHM"));

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		return FrmPermohonanUPTOnlineData.simpanPendaftaran(h);

	}// close simpanPendaftaran

	@SuppressWarnings("unchecked")
	private void updatePendaftaran(HttpSession session, String userIdKementerian) throws Exception {

		Hashtable h = new Hashtable();

		String id_urusan = getParam("sorUrusan");
		String id_negeriprojek = getParam("socNegeriProjek");

		h.put("id_senaraisemak", getParam("id_senaraisemak"));
		h.put("id_urusan", id_urusan);
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_fail", getParam("id_fail"));

		// checkbutton seksyen 4
		//h.put("semak10", "");
		//h.put("semak20", "");
		if(getParam("cbsemaks10")==null){h.put("semak10", "0");}else{h.put("semak10", getParam("cbsemaks10"));}
    	if(getParam("cbsemaks20")==null){h.put("semak20", "0");}else{h.put("semak20", getParam("cbsemaks20"));}

		// checkbutton seksyen 8
		if (getParam("cbsemaks1") == null) {
			h.put("semak1", "0");
		} else {
			h.put("semak1", getParam("cbsemaks1"));
		}
		if (getParam("cbsemaks2") == null) {
			h.put("semak2", "0");
		} else {
			h.put("semak2", getParam("cbsemaks2"));
		}
		if (getParam("cbsemaks3") == null) {
			h.put("semak3", "0");
		} else {
			h.put("semak3", getParam("cbsemaks3"));
		}
		if (getParam("cbsemaks4") == null) {
			h.put("semak4", "0");
		} else {
			h.put("semak4", getParam("cbsemaks4"));
		}
		if (getParam("cbsemaks5") == null) {
			h.put("semak5", "0");
		} else {
			h.put("semak5", getParam("cbsemaks5"));
		}
		if (getParam("cbsemaks6") == null) {
			h.put("semak6", "0");
		} else {
			h.put("semak6", getParam("cbsemaks6"));
		}
		if (getParam("cbsemaks7") == null) {
			h.put("semak7", "0");
		} else {
			h.put("semak7", getParam("cbsemaks7"));
		}
    	if (getParam("txdPermohonanLengkap") == null) {
			h.put("txdPermohonanLengkap", getParam("txdPermohonanLengkap"));
		}

		h.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		h.put("tarikh_permohonan_kjp", getParam("tarikh_permohonan_kjp"));
		h.put("tujuan", getParam("txtTujuan"));
		h.put("rujukan_kementerian", getParam("txtRujukanKementerian"));
		h.put("tarikh_hendak", getParam("txdTarikhKehendaki"));
		h.put("tarikh_surat", getParam("txdTarikhSurat"));
		h.put("flag_peruntukan", getParam("sorFlagPeruntukan"));
		h.put("flag_segera", getParam("sorFlagSegera"));
		h.put("daerah", getParam("socDaerah"));
		h.put("projeknegeri", id_negeriprojek);
		h.put("agensi", getParam("socAgensi"));
		h.put("kementerian", userIdKementerian);
		h.put("ulasanjt", getParam("txtUlasan"));

		h.put("no_rujukan_ptg", getParam("txtNoRujukanPTG"));
		h.put("no_rujukan_ptd", getParam("txtNoRujukanPTD"));
		h.put("no_rujukan_upt", getParam("txtNoRujukanUPT"));

		h.put("jenis_projek", getParam("sorJenisProjek"));

		h.put("jumlahHakmilik", getParam("txtJumHM"));
		h.put("sorJenisKodDaerah", getParam("sorJenisKodDaerah"));

		//h.put("sorJenisKodDaerah", "");

		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		// onchange validation
		if (id_urusan.equals("51")) {
			context.put("showSegera", "yes");
		} else {
			context.put("showSegera", "no");
		}

		h.put("txtTujuanBI", "");

		// FrmPermohonanUPTData.update(h);

		FrmPermohonanUPTOnlineData.update(h);

	}// close updatePendaftaran

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetDataOnchangeNegeri(String id_negeriprojek, String id_urusan, String mode,
			String userIdKementerian) throws Exception {

		String id_kementerian = userIdKementerian;

		if (mode.equals("new")) {
			context.put("selectNegeriProjek", HTML.SelectNegeriMampu("socNegeriProjek",
					Utils.parseLong(id_negeriprojek), null, "style=width:300px onChange=\"doChangeProjekNegeri();\" "));
			context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian),
					null, "disabled class=disabled style=width:500px onChange=\"doChangeKementerian();\" "));
		} else {
			context.put("selectNegeriProjek",
					HTML.SelectNegeriMampu("socNegeriProjek", Utils.parseLong(id_negeriprojek), null,
							"style=width:300px onChange=\"doChangeProjekNegeriUpdate();\" "));
			context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian),
					null, "disabled class=disabled style=width:500px onChange=\"doChangeKementerianUpdate();\" "));
		}

		Vector dataKementerian = model.getAlamatKementerian(id_kementerian);
		String id_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		if (dataKementerian.size() != 0) {
			Hashtable AK = (Hashtable) dataKementerian.get(0);
			id_negeri = AK.get("id_negeri").toString();
			alamat1 = AK.get("alamat1").toString();
			alamat2 = AK.get("alamat2").toString();
			alamat3 = AK.get("alamat3").toString();
			poskod = AK.get("poskod").toString();
		}

		context.put("txtPoskod", poskod);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);

		context.put("sorUrusan", getParam("sorUrusan"));
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		context.put("tarikh_permohonan_kjp", getParam("tarikh_permohonan_kjp"));
		context.put("txtNoRujukanPTG", getParam("txtNoRujukanPTG"));
		context.put("txtNoRujukanPTD", getParam("txtNoRujukanPTD"));
		context.put("txtNoRujukanUPT", getParam("txtNoRujukanUPT"));
		context.put("txtTujuan", getParam("txtTujuan"));
		context.put("txtRujukanKementerian", getParam("txtRujukanKementerian"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhKehendaki", getParam("txdTarikhKehendaki"));
		context.put("sorFlagPeruntukan", getParam("sorFlagPeruntukan"));
		context.put("sorFlagSegera", getParam("sorFlagSegera"));
		context.put("sorJenisProjek", getParam("sorJenisProjek"));
		context.put("txtJumHM", getParam("txtJumHM"));
		context.put("txdPermohonanLengkap", getParam("txdPermohonanLengkap"));

		String id_daerah = getParam("socDaerah");
		String id_agensi = getParam("socAgensi");

		// dropdown by
		if (id_kementerian != "") {
			context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", id_kementerian,
					Utils.parseLong(id_agensi), "id=socAgensi style=width:500px"));
		} else {
			context.put("selectAgensi",
					HTML.SelectAgensi("socAgensi", Utils.parseLong(id_agensi), "id=socAgensi style=width:500px"));
		}

		if (id_negeriprojek != "") {
			context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeriprojek, "socDaerah",
					Utils.parseLong(id_daerah), null, "id=socDaerah style=width:300px"));
		} else {
			context.put("selectDaerah",
					HTML.SelectDaerah("socDaerah", Utils.parseLong(id_daerah), null, "id=socDaerah style=width:300px"));
		}

		// dropdown
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri),
				"class=disabled disabled style=width:325px"));

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		// onchange validation
		if (id_urusan.equals("52")) {
			context.put("showSegera", "yes");
		} else {
			context.put("showSegera", "no");
		}

	}// close getAndSetDataKementerian

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetDataOnchangeKementerian(String id_kementerian, String id_urusan, String mode)
			throws Exception {

		String id_negeriprojek = getParam("socNegeriProjek");

		if (mode.equals("new")) {
			context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian),
					null, "disabled class=disabled style=width:500px onChange=\"doChangeKementerian();\" "));
			// Dikemaskini oleh Mohamad Rosli, Pilihan negeri tidak termasuk
			// JOHOR
			// context.put("selectNegeriProjek", HTML.SelectNegeri(
			// "socNegeriProjek", null, null,
			// "style=width:300px onChange=\"doChangeProjekNegeri();\""));
			context.put("selectNegeriProjek", SelectNegeriPengambilan("socNegeriProjek",
					Utils.parseLong(id_negeriprojek), null, "style=width:300px onChange=\"doChangeProjekNegeri();\""));

		} else {
			context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Utils.parseLong(id_kementerian),
					null, "disabled class=disabled style=width:500px onChange=\"doChangeKementerianUpdate();\" "));
			context.put("selectNegeriProjek",
					HTML.SelectNegeriMampu("socNegeriProjek", Utils.parseLong(id_negeriprojek), null,
							"style=width:300px onChange=\"doChangeProjekNegeriUpdate();\" "));
		}

		Vector dataKementerian = model.getAlamatKementerian(id_kementerian);
		String id_negeri = "";
		String alamat1 = "";
		String alamat2 = "";
		String alamat3 = "";
		String poskod = "";
		if (dataKementerian.size() != 0) {
			Hashtable AK = (Hashtable) dataKementerian.get(0);
			id_negeri = AK.get("id_negeri").toString();
			alamat1 = AK.get("alamat1").toString();
			alamat2 = AK.get("alamat2").toString();
			alamat3 = AK.get("alamat3").toString();
			poskod = AK.get("poskod").toString();
		}

		context.put("txtPoskod", poskod);
		context.put("txtAlamat1", alamat1);
		context.put("txtAlamat2", alamat2);
		context.put("txtAlamat3", alamat3);

		context.put("sorUrusan", getParam("sorUrusan"));
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan"));
		context.put("tarikh_permohonan_kjp", getParam("tarikh_permohonan_kjp"));
		context.put("txtNoRujukanPTG", getParam("txtNoRujukanPTG"));
		context.put("txtNoRujukanPTD", getParam("txtNoRujukanPTD"));
		context.put("txtNoRujukanUPT", getParam("txtNoRujukanUPT"));
		context.put("txtTujuan", getParam("txtTujuan"));
		context.put("txtRujukanKementerian", getParam("txtRujukanKementerian"));
		context.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		context.put("txdTarikhKehendaki", getParam("txdTarikhKehendaki"));
		context.put("sorFlagPeruntukan", getParam("sorFlagPeruntukan"));
		context.put("sorFlagSegera", getParam("sorFlagSegera"));
		context.put("sorJenisProjek", getParam("sorJenisProjek"));
		context.put("txtJumHM", getParam("txtJumHM"));

		String id_daerah = getParam("socDaerah");

		// dropdown by
		if (id_kementerian != "") {
			context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", id_kementerian, null,
					"id=socAgensi style=width:500px"));
		} else {
			context.put("selectAgensi", HTML.SelectAgensi("socAgensi", null, "id=socAgensi style=width:500px"));
		}
		if (id_negeriprojek != "") {
			context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeriprojek, "socDaerah",
					Utils.parseLong(id_daerah), null, "style=width:300px"));
		} else {
			context.put("selectDaerah",
					HTML.SelectDaerah("socDaerah", Utils.parseLong(id_daerah), null, "style=width:300px"));
		}

		// dropdown
		context.put("selectNegeri", HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(id_negeri),
				"class=disabled disabled style=width:325px"));

		// validation jajahan
		if (id_negeriprojek.equals("3")) {
			context.put("showJajahan", "yes");
		} else {
			context.put("showJajahan", "no");
		}

		// onchange validation
		if (id_urusan.equals("52")) {
			context.put("showSegera", "yes");
		} else {
			context.put("showSegera", "no");
		}

	}// close getAndSetDataKementerian

	private void resetValue() throws Exception {

		context.put("sorUrusan", "");
		context.put("txtNoRujukanPTG", "");
		context.put("txtNoRujukanPTD", "");
		context.put("txtNoRujukanUPT", "");
		context.put("txtAlamat1", "");
		context.put("txtAlamat2", "");
		context.put("txtAlamat3", "");
		context.put("txtPoskod", "");
		context.put("txtTujuan", "");
		context.put("txtRujukanKementerian", "");
		context.put("txdTarikhSurat", "");
		context.put("txdTarikhKehendaki", "");
		context.put("sorFlagPeruntukan", "");
		context.put("sorFlagSegera", "");
		context.put("sorJenisProjek", "");
		context.put("txtJumHM", "");

	}// close resetValue

	@SuppressWarnings({ "unchecked", "static-access" })
	private String nameAndId(String id_user) throws Exception {

		Vector dataKementerianOnline = new Vector();

		dataKementerianOnline.clear();

		model.setDataKementerianOnline(id_user);
		dataKementerianOnline = model.getDataKementerianOnline();
		String userIdKementerian = "";
		if (dataKementerianOnline.size() != 0) {
			Hashtable t = (Hashtable) dataKementerianOnline.get(0);
			userIdKementerian = t.get("id_kementerian").toString();
		}

		return userIdKementerian;

	}// close nameAndId

	private String selectedTab() throws Exception {
		String selectedTab = new String();
		selectedTab = getParam("tabId").toString();
		if (selectedTab == null || "".equals(selectedTab)) {
			selectedTab = "0";
		}

		return selectedTab;
	}// close selectedTab

	private void paging() throws Exception {
		String flagPaging = getParam("paging");
		if (flagPaging != "") {
			context.put("paging", getParam("paging"));
		} else {
			context.put("paging", "1");
		}
	}// close paging

	private void anchor() throws Exception {
		String ScreenLocation = getParam("ScreenLocation");
		String CursorPoint = getParam("CursorPoint");
		context.put("ScreenLocation", ScreenLocation);
		context.put("CursorPoint", CursorPoint);
	}// close anchor

	private void UtilsItem() throws Exception {
		context.put("Util", new lebah.util.Util());
		context.put("Utils", new ekptg.helpers.Utils());
	}// close UtilsItem

	@SuppressWarnings("static-access")
	private void ListCarian(HttpSession session, String id_user, String portalRole, String flag_noti) throws Exception {

		// dropdown
		context.put("selectStatusSPT",
				HTML.SelectStatusSPT("socStatus", Utils.parseLong(getParam("socStatus")), "style=width:auto"));
		context.put("selectJenisHMCarian", HTML.SelectJenisHakmilik("socJenisHakmilik",
				Utils.parseLong(getParam("socJenisHakmilik")), "id=selectJenisHMCarian style=width:auto"));
		context.put("selectNegeriCarian",
				HTML.SelectNegeriMampu("socNegeri", Utils.parseLong(getParam("socNegeri")), null, "style=width:auto"));

		context.put("txtNoFailCarian", getParam("txtNoFailCarian").trim());
		context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan").trim());
		// context.put("tarikh_permohonan_kjp",
		// getParam("tarikh_permohonan_kjp").trim());
		context.put("sorUrusanCarian", getParam("sorUrusanCarian"));
		context.put("txtBilPermohonanCarian", getParam("txtBilPermohonanCarian").trim());
		context.put("txtNamaPBCarian", getParam("txtNamaPBCarian").trim());
		context.put("txtNoPBCarian", getParam("txtNoPBCarian").trim());
		context.put("txtNoHakmilikCarian", getParam("txtNoHakmilikCarian").trim());
		context.put("txtNoLotCarian", getParam("txtNoLotCarian").trim());
		context.put("txtTujuanCarian", getParam("txtTujuanCarian").trim());

		modelOnline.setListCarian(getParam("socStatus"), getParam("socJenisHakmilik"), getParam("socNegeri"),
				getParam("txtNoFailCarian").trim(), getParam("txdTarikhPermohonan").trim(), getParam("sorUrusanCarian"),
				getParam("txtBilPermohonanCarian").trim(), getParam("txtNamaPBCarian").trim(),
				getParam("txtNoPBCarian").trim(), getParam("txtNoHakmilikCarian").trim(),
				getParam("txtNoLotCarian").trim(), getParam("txtTujuanCarian").trim(), id_user, portalRole, flag_noti);

	}// close listcarian

	@Override
	@SuppressWarnings("unchecked")
	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10 : getParamAsInteger("itemsPerPage");
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

	private IPermohonan getPermohonan() {
		if (iPermohonan == null)
			iPermohonan = new PPTPermohonanBean();
		return iPermohonan;
	}

	public String SelectNegeriPengambilan(String selectName, Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=>SILA PILIH</option>\n");
			Vector v = getNegeriPengambilan();
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">" + f.getKodNegeri() + " - "
						+ f.getNamaNegeri() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}

	public Vector getNegeriPengambilan() throws Exception {
		Db db = null;
		String sql = "" + " SELECT ID_NEGERI,KOD_NEGERI,NAMA_NEGERI FROM TBLRUJNEGERI "
				+ " WHERE ID_NEGERI NOT IN (0,1,12,13,17) ";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector v = new Vector();
			Tblrujnegeri s = null;
			while (rs.next()) {
				s = new Tblrujnegeri();
				s.setIdNegeri(rs.getLong(1));
				s.setKodNegeri(rs.getString(2));
				s.setNamaNegeri(rs.getString(3));
				v.addElement(s);

			}
			return v;

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void PopupReg_KJP(String nama_class, String tajuk_class, String group, Db db) throws Exception {
		// Db db = null;
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			/*
			 * String sql = " INSERT INTO MODULE ( "+
			 * " MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "+
			 * " MODULE_GROUP, MODULE_DESCRIPTION)  "+ " VALUES ('"+nama_class+"','"
			 * +tajuk_class+"','"+nama_class+"','"+group+"','') ";
			 * myLogger.info("REG CLASS :"+sql.toUpperCase()); stmt.executeUpdate(sql);
			 */

			String sql = " INSERT INTO ROLE_MODULE ( " + " MODULE_ID, USER_ROLE) " + " SELECT '" + nama_class
					+ "' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) = 'ONLINE_KJP' ";
			myLogger.info("REG ROLE CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			// if (db != null)db.close();
		}
	}

	public int checkRegPopup_KJP(String class_name, Db db) throws Exception {

		// Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// db = new Db();
			sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '" + class_name
					+ "'  AND UPPER(USER_ROLE) = 'ONLINE_KJP'";
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

	public void insertPopupReg(String nama_class, String tajuk_class, String group, Db db) throws Exception {
		// Db db = null;
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			String sql = " INSERT INTO MODULE ( " + " MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "
					+ " MODULE_GROUP, MODULE_DESCRIPTION)  " + " VALUES ('" + nama_class + "','" + tajuk_class + "','"
					+ nama_class + "','" + group + "','') ";
			myLogger.info("REG CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			sql = " INSERT INTO ROLE_MODULE ( " + " MODULE_ID, USER_ROLE) " + " SELECT '" + nama_class
					+ "' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%' ";
			myLogger.info("REG ROLE CLASS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			// if (db != null)db.close();
		}
	}

	public int checkRegPopup(String class_name, Db db) throws Exception {

		// Db db = null;
		int total = 0;
		String sql = "";
		ResultSet rs = null;
		try {
			// db = new Db();
			sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '" + class_name + "'";
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

	private IUtilHTMLPilihan getJenisHakmilik() {
		if (iPilihanJH == null)
			iPilihanJH = new UtilHTMLPilihanJenisHakmilik();
		return iPilihanJH;
	}
	
	
	private IEmel getEmelSemak(){
		if(iEmel == null)
			iEmel = new EmelSemakanBean();
		return iEmel;
	}
}//close here
