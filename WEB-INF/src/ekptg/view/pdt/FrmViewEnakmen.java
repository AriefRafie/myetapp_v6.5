package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmModelEnakmen;
//import ekptg.report.Utils;

public class FrmViewEnakmen extends AjaxBasedModule {
	private static Logger myLog = Logger.getLogger(ekptg.view.pdt.FrmViewEnakmen.class);
	FrmModelEnakmen enakmen = new FrmModelEnakmen();

	@SuppressWarnings("unchecked")

	Vector vCarian = null;
	Vector vPapar = null;
	Hashtable hPapar = null;
	///ENAKMEN
	String Enakmen_IDEnakmen = "";
	String Enakmen_NoEnakmen = "";
	String Enakmen_NamaEnakmen = "";
	String Enakmen_TarikhKuatkuasa = "";
	String Enakmen_TarikhMansuh = "";
	String Enakmen_NoWarta = "";
	String Enakmen_TarikhWarta = "";
	String Enakmen_TarafKeselamatan = "";
	String Enakmen_Seksyen = "";
	String Enakmen_NoFail = "";
	String Enakmen_Catatan = "";
	String Enakmen_Dokumen = "";
	String Enakmen_TarikhDaftar = "";
	//
	//PENGGAL
	String Penggal_IDPenggal = "";
	String Bahagian_IDBahagian ="";

	//BAB
	String Bab_IDBab = "";

	//
	String Seksyen_IDSeksyen = "";
	String SubSeksyen_IDSubSeksyen = "";

	String Para_IDPara = "";
	String SubPara_IDSubPara = "";

	//SOC
	String SOC_Penggal = "";
	String SOC_Bahagian = "";
	String SOC_Bab = "";
	String SOC_Seksyen = "";
	String SOC_SubSeksyen = "";
	String SOC_Para = "";
	String SOC_Jadual = "";

	String RO_General = "";
	String action = "";

	String Cari_NoEnakmen = "";
	String Cari_NamaEnakmen = "";
	String Cari_TarikhKuatkuasa = "";
	String Cari_Seksyen = "";
	String IDJadual = "";
	String IDJadualLampiran = "";

	public String doTemplate2() throws Exception {
		//debugParams();
		String vm = "";
		action = getParam("action");
		String command = getParam("command");
		myLog.debug("REAL ACTION:"+action);
		String selectedTab = getParam("selectedTab");
		if ("".equalsIgnoreCase(selectedTab)) {
			selectedTab = "0";
		}

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		HttpSession session = this.request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String current_role = (String) session.getAttribute("myrole");
		this.context.put("current_role", current_role);
		String MAIN_LEGEND = "MAKLUMAT ENAKMEN";
		RO_General = "readonly=\"readonly\"";

		//----------------------------------------------
		// get parameters
		Cari_NoEnakmen = getParam("txtNoEnakmen");
		Cari_NamaEnakmen = getParam("txtNamaEnakmen");
		Cari_TarikhKuatkuasa = getParam("txdTarikhKuatkuasa");
		Cari_Seksyen = getParam("socSeksyen");

		Enakmen_IDEnakmen = getParam("Enakmen_IDEnakmen");
		Enakmen_NoEnakmen = getParam("txtNoEnakmen");
		Enakmen_NamaEnakmen = getParam("txtNamaEnakmen");
		Enakmen_TarikhKuatkuasa = getParam("txdTarikhKuatkuasa");
		Enakmen_TarikhMansuh = getParam("txdTarikhMansuh");
		Enakmen_NoWarta = getParam("txtNoWarta");
		Enakmen_TarikhWarta = getParam("txdTarikhWarta");
		Enakmen_TarafKeselamatan = getParam("sorTaraf");
		Enakmen_Seksyen = getParam("txtSeksyen");
		Enakmen_NoFail = getParam("txtNoFail");
		Enakmen_Catatan = getParam("txtCatatan");
		Enakmen_Dokumen = getParam("txfMuatNaikDokumen");
		Enakmen_TarikhDaftar = getParam("txdTarikhDaftar");

		Penggal_IDPenggal = getParam("Penggal_IDPenggal");

		//myLogger.debug("Penggal_IDPenggal get param:"+Penggal_IDPenggal);

		Bahagian_IDBahagian = getParam("Bahagian_IDBahagian");
		String Bahagian_NoBahagian = getParam("txtNoBahagian");
		String Bahagian_TajukBahagian = getParam("txtTajukBahagian");

		Bab_IDBab = getParam("Bab_IDBab");
		String Bab_NoBab = getParam("txtNoBab");
		String Bab_TajukBab = getParam("txtTajukBab");

		//////////////
		Seksyen_IDSeksyen = getParam("Seksyen_IDSeksyen");
		String Seksyen_Seksyen = getParam("txtSeksyen");
		String Seksyen_Proviso = getParam("txtProviso");
		String Seksyen_NoSeksyen = getParam("txtNoSeksyen");
		String Seksyen_TajukSeksyen = getParam("txtTajukSeksyen");

		////////////////
		SubSeksyen_IDSubSeksyen = getParam("SubSeksyen_IDSubSeksyen");
		String SubSeksyen_SubSeksyen = getParam("txtSubSeksyen");
		String SubSeksyen_SubProviso = getParam("txtProviso");
		String SubSeksyen_NoSubSeksyen = getParam("txtNoSubSeksyen");
		String SubSeksyen_TajukSubSeksyen = getParam("txtTajukSubSeksyen");


		Para_IDPara = getParam("Para_IDPara");
		String Para_Para = getParam("txtPara");
		String Para_Jadual = getParam("txtJadual");

		SubPara_IDSubPara = getParam("SubPara_IDSubPara");
		String SubPara_SubPara = getParam("txtSubPara");
		myLog.info("SubPara_SubPara :: "+SubPara_SubPara);
		String SubPara_Jadual = getParam("txtJadual");

		String JadualPara_IDJadualPara = getParam("JadualPara_IDJadualPara");

		String JadualSubPara_IDJadualSubPara = getParam("JadualSubPara_IDJadualSubPara");

		String JadualSubSubPara_IDJadualSubSubPara = getParam("JadualSubSubPara_IDJadualSubSubPara");


		IDJadual = getParam("IDJadual");
		IDJadualLampiran = getParam("IDJadualLampiran");
		SOC_Penggal = getParam("SOC_Penggal");
		SOC_Bahagian = getParam("SOC_Bahagian");
		SOC_Bab = getParam("SOC_Bab");
		SOC_Seksyen = getParam("SOC_Seksyen");
		SOC_SubSeksyen = getParam("SOC_SubSeksyen");
		SOC_Para = getParam("SOC_Para");
		SOC_Jadual = getParam("SOC_Jadual");

		//----------------------------------------------

		//----------------------------------------------
		// processing
		vCarian = new Vector();
		vPapar = new Vector();
		hPapar = new Hashtable();
		//setUploadFile(false);


		this.context.put("num_files",1);

		if (action.equalsIgnoreCase("tambah")) {
			vm = "app/pdt/frmEnakmenTabEnakmenBaru.jsp";
			clearFieldEnakmen();
			action = "update";
			RO_General = "";
			this.context.put("Enakmen_TarikhDaftar", sdf.format(now));
			this.context.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",null,null,""));

			//myLogger.debug("BAKPO vm="+vm);
		}
		else if (action.equalsIgnoreCase("save")) {
			myLog.debug("** SAVE **");
			vm = "app/pdt/frmEnakmenN.jsp";
			action = "view";
			String mode = getParam("mode");
			if ("0".equalsIgnoreCase(selectedTab)) {
				//Update ENAKMEN
				if ("update".equals(mode)) {
					vm = "app/pdt/frmEnakmenN.jsp";
					UploadFile(Enakmen_IDEnakmen);
					Hashtable hEnakmenUpdate = new Hashtable();
					hEnakmenUpdate.put("idEnakmen",Enakmen_IDEnakmen);
					hEnakmenUpdate.put("noEnakmen",Enakmen_NoEnakmen);
					hEnakmenUpdate.put("namaEnakmen",Enakmen_NamaEnakmen);
					hEnakmenUpdate.put("tarikhKuatkusa",Enakmen_TarikhKuatkuasa);
					hEnakmenUpdate.put("noWarta",Enakmen_NoWarta);
					hEnakmenUpdate.put("tarikhMansuh",Enakmen_TarikhMansuh);
					hEnakmenUpdate.put("tarikhWarta",Enakmen_TarikhWarta);
					hEnakmenUpdate.put("catatan", Enakmen_Catatan);
					hEnakmenUpdate.put("tarikhDaftar",Enakmen_TarikhDaftar);
					hEnakmenUpdate.put("noFail",Enakmen_NoFail);
					hEnakmenUpdate.put("tagDokumen", getParam("tag_dokumen"));
					hEnakmenUpdate.put("idTagDokumen", getParam("id_tagdokumen"));
					hEnakmenUpdate.put("idSeksyen", getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "" : Integer.parseInt(getParam("socSeksyen")));


					enakmen.enakmenUpdate(hEnakmenUpdate);

				} else {
					vm = "app/pdt/frmEnakmenTabEnakmenBaru.jsp";
					Hashtable hAddEnakmen = new Hashtable();
					hAddEnakmen.put("noEnakmen",Enakmen_NoEnakmen);
					hAddEnakmen.put("namaEnakmen",Enakmen_NamaEnakmen);
					hAddEnakmen.put("tarikhKuatkuasa",Enakmen_TarikhKuatkuasa);
//					hAddEnakmen.put("tarikhMansuh", getParam("txtTarikhMansuh"));
					hAddEnakmen.put("tarikhMansuh", Enakmen_TarikhMansuh);
					hAddEnakmen.put("noWarta",Enakmen_NoWarta);
					hAddEnakmen.put("tarikhWarta",Enakmen_TarikhWarta);

//					if(checked.equals("sulit"))
//						hAddEnakmen.put("idKeselamatan","3");
//					else
//						hAddEnakmen.put("idKeselamatan","1");
//
					hAddEnakmen.put("catatan",Enakmen_Catatan);
					hAddEnakmen.put("NoFail", getParam("socNoFail"));
					hAddEnakmen.put("idSeksyen", getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "" : Integer.parseInt(getParam("socSeksyen")));
					hAddEnakmen.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(getParam("socSeksyen")),null,null));
					hAddEnakmen.put("tarikhDaftar", Enakmen_TarikhDaftar);
					hAddEnakmen.put("idMasuk",user_id);
					hAddEnakmen.put("tarikhMasuk",Enakmen_TarikhDaftar);
					hAddEnakmen.put("tagDokumen", getParam("tag_dokumen"));
					hAddEnakmen.put("idTagDokumen", getParam("id_tagdokumen"));

					Enakmen_IDEnakmen = enakmen.enakmenAdd(hAddEnakmen);
					UploadFile(Enakmen_IDEnakmen);
					myLog.debug("id enakmen:"+Enakmen_IDEnakmen);
					myLog.debug("Simpan Enakmen");
					
				}
				showEnakmen();
				RO_General = "readonly=\"readonly\"";

			} else if ("1".equalsIgnoreCase(selectedTab)) {
				//SIMPAN PENGGAL
				Hashtable hAddPenggal = new Hashtable();
				if ("update".equals(mode)) {
					hAddPenggal.put("idEnakmenPenggal",Penggal_IDPenggal);
					hAddPenggal.put("noPenggal",getParam("txtNoPenggal"));
					hAddPenggal.put("tajukPenggal",getParam("txtTajukPenggal"));
					hAddPenggal.put("idMasuk",user_id);
					enakmen.penggalUpdate(hAddPenggal);
					action = "view";
					showPenggal();
				} else {
					hAddPenggal.put("idEnakmen",Enakmen_IDEnakmen);
					hAddPenggal.put("noPenggal",getParam("txtNoPenggal"));
					hAddPenggal.put("tajukPenggal",getParam("txtTajukPenggal"));
					hAddPenggal.put("idMasuk",user_id);
					hAddPenggal.put("tarikhMasuk", sdf.format(now));
					enakmen.penggalAdd(hAddPenggal);
					action = "update";
					RO_General = "";
				}
				showPenggalLists();

			} else if ("2".equalsIgnoreCase(selectedTab)) {
				if ("update".equals(mode)) {
					enakmen.bahagianSave("update",Bahagian_IDBahagian, Enakmen_IDEnakmen,
							SOC_Penggal, Bahagian_NoBahagian, Bahagian_TajukBahagian);
					action = "view";
					showBahagian();
				} else {
					enakmen.bahagianSave("save",Bahagian_IDBahagian, Enakmen_IDEnakmen,
							SOC_Penggal, Bahagian_NoBahagian, Bahagian_TajukBahagian);
					action = "update";
					RO_General = "";
				}
				showBahagianLists();
			} else if ("3".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Enakmen_IDEnakmen) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian)) {
//					enakmen.babSave(Bab_IDBab, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, Bab_NoBab, Bab_TajukBab);
//				}
				//BAB
				if ("update".equals(mode)) {
					enakmen.babSave("update",Bab_IDBab, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, Bab_NoBab, Bab_TajukBab);
					action = "view";
					showBab();
				} else {
					enakmen.babSave("save",Bab_IDBab, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, Bab_NoBab, Bab_TajukBab);
					action = "update";
					RO_General = "";
				}
				showBabLists();
			} else if ("4".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Enakmen_IDEnakmen) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab)) {
//					enakmen.seksyenSave(Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, Seksyen_Seksyen, Seksyen_Proviso);
//				}
				if ("update".equals(mode)) {
					enakmen.seksyenSave("update",Seksyen_IDSeksyen, Enakmen_IDEnakmen,
							SOC_Penggal, SOC_Bahagian, SOC_Bab,
							Enakmen_Seksyen, Seksyen_Proviso,
							Seksyen_NoSeksyen,Seksyen_TajukSeksyen,user_id);
					action = "view";
					showSeksyen();
				} else {
					enakmen.seksyenSave("save",Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian,
							SOC_Bab, Seksyen_Seksyen, Seksyen_Proviso,
							Seksyen_NoSeksyen,Seksyen_TajukSeksyen,user_id);
					action = "update";
					RO_General = "";
				}
				showSeksyenLists();
			} else if ("5".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Enakmen_IDEnakmen) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab) && !"".equalsIgnoreCase(SOC_Seksyen)) {
//					enakmen.subSeksyenSave(Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SubSeksyen_SubSeksyen, SubSeksyen_SubProviso);
//				}
				myLog.debug("tajuk sub seksyen:"+SubSeksyen_TajukSubSeksyen);
				if ("update".equals(mode)) {
					enakmen.subSeksyenSave("update",SubSeksyen_IDSubSeksyen, Enakmen_IDEnakmen,
							SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen,
							SubSeksyen_SubSeksyen, SubSeksyen_SubProviso,
							SubSeksyen_NoSubSeksyen,SubSeksyen_TajukSubSeksyen,user_id);
					action = "view";
					showSubSeksyen();
				} else {
					enakmen.subSeksyenSave("save",SubSeksyen_IDSubSeksyen, Enakmen_IDEnakmen, SOC_Penggal,
							SOC_Bahagian, SOC_Bab, SOC_Seksyen,
							SubSeksyen_SubSeksyen, SubSeksyen_SubProviso,
							SubSeksyen_NoSubSeksyen,SubSeksyen_TajukSubSeksyen,user_id);
					action = "update";
					RO_General = "";
				}
				showSubSeksyenLists();
			} else if ("6".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Enakmen_IDEnakmen) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab) && !"".equalsIgnoreCase(SOC_Seksyen) && !"".equalsIgnoreCase(SOC_SubSeksyen)) {
//					enakmen.paraSave(Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, Para_Para, Para_Jadual);
//				}
				if ("update".equals(mode)) {
					enakmen.paraSave("update",Para_IDPara, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, Para_Para, Para_Jadual);
					action = "view";
					showPara();
				} else {
					enakmen.paraSave("save",Para_IDPara, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, Para_Para, Para_Jadual);
					action = "update";
					RO_General = "";
				}
				showParaLists();
			} else if ("7".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Enakmen_IDEnakmen) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab) && !"".equalsIgnoreCase(SOC_Seksyen) && !"".equalsIgnoreCase(SOC_SubSeksyen) & !"".equalsIgnoreCase(SOC_Para)) {
//					enakmen.subParaSave(Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
//				}
				if ("update".equals(mode)) {
					enakmen.subParaSave("update",SubPara_IDSubPara, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
					//enakmen.subParaSave("update",SubPara_IDSubPara,Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
					action = "view";
					showSubPara();
				} else {
					enakmen.subParaSave("save",SubPara_IDSubPara, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
					//enakmen.subParaSave("save",SubPara_IDSubPara,Seksyen_IDSeksyen, Enakmen_IDEnakmen, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
					action = "update";
					RO_General = "";
				}
				showSubParaLists();
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				String NamaJadual=getParam("txtNamaJadual");
				String TajukJadual=getParam("txtTajukJadual");
				String CatatanJadual=getParam("txtCatatanJadual");

				if ("update".equals(mode)) {
					String ENAKMEN_IDJadual = enakmen.JadualParaSave("update", IDJadual, Enakmen_IDEnakmen,
							NamaJadual,TajukJadual,CatatanJadual,user_id);
					UploadJadual(IDJadual);
					action = "view";
					showJadual();
				} else {
					String ENAKMEN_IDJadual = enakmen.JadualParaSave("save", IDJadual, Enakmen_IDEnakmen,
							NamaJadual,TajukJadual,CatatanJadual,user_id);
					UploadJadual(ENAKMEN_IDJadual);
					action = "update";
					RO_General = "";
				}
				showEnakmen();
				showJadualLists();
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				myLog.debug("Simpan Jadual");
				//lampiran jadual save
				String tajuk = getParam("txtTajuk");
				String catatan = getParam("txtCatatan");
				myLog.debug("mode:"+mode);
				if ("update".equals(mode)) {
					String ENAKMEN_IDJadualLampiran = enakmen.JadualLampiranSave("update", IDJadualLampiran,
							SOC_Jadual,tajuk,catatan,user_id);
					//NEED TO CHANGE
					UploadJadualLampiran(IDJadualLampiran);
					action = "view";
				} else {
					String ENAKMEN_IDJadualLampiran = enakmen.JadualLampiranSave("save", IDJadualLampiran,
							SOC_Jadual,tajuk, catatan,user_id);
					UploadJadualLampiran(ENAKMEN_IDJadualLampiran);
					action = "update";
					RO_General = "";
				}
				showEnakmen();
				showJadualLampiran();
				showJadualLampiranLists();
				showJadualLampiranFail();
			} else if ("10".equalsIgnoreCase(selectedTab)) {

			}

		}
		else if ("cari".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmEnakmenCarian.jsp";
			doCarian(session);
		//} else if ("view".equalsIgnoreCase(action) || "update".equalsIgnoreCase(action)) {
			
		} else if ("view".equalsIgnoreCase(action)) {
			myLog.debug(" *** VIEW ***");
			this.context.put("enctype","enctype=\"multipart/form-data\"");
			vm = "app/pdt/frmEnakmenN.jsp";

			if ("update".equalsIgnoreCase(action)) {
				RO_General = "";
			}
			showEnakmen();
			
			context.put("Enakmen_IDEnakmen", Enakmen_IDEnakmen);
			context.put("Penggal_IDPenggal", Penggal_IDPenggal);
			context.put("Bahagian_IDBahagian", Bahagian_IDBahagian);
			context.put("Bab_IDBab", Bab_IDBab);
			context.put("Seksyen_IDSeksyen", Seksyen_IDSeksyen);
			//context.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(Seksyen_Seksyen),null,null));
			context.put("SubSeksyen_IDSubSeksyen", SubSeksyen_IDSubSeksyen);
			context.put("Para_IDPara", Para_IDPara);
			context.put("SubPara_IDSubPara", SubPara_IDSubPara);
			context.put("JadualPara_IDJadualPara", JadualPara_IDJadualPara);
			context.put("JadualSubPara_IDJadualSubPara", JadualSubPara_IDJadualSubPara);
			context.put("JadualSubSubPara_IDJadualSubSubPara", JadualSubSubPara_IDJadualSubSubPara);

			if ("0".equalsIgnoreCase(selectedTab)) {
				String mode = getParam("mode");

				if ("".equals(mode)) {
					action = "doUpdate";
				} else {
					action = "update";
				}

				myLog.debug("mode:"+mode);
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT ENAKMEN";
			} else if ("1".equalsIgnoreCase(selectedTab)) {
				showPenggalLists();
				context.put("Penggal_NoPenggal","");
				context.put("Penggal_TajukPenggal","");
				MAIN_LEGEND = "MAKLUMAT PENGGAL";
				action = "update";
				myLog.info("-1-");
				RO_General = "";
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				Bahagian_IDBahagian = "";
				showBahagian();
				showBahagianLists();
				context.put("txtNoBahagian","");
				context.put("txtTajukBahagian","");
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT BAHAGIAN";
			} else if ("3".equalsIgnoreCase(selectedTab)) {
				Bab_IDBab = "";
				showBab();
				showBabLists();
				doChanges(command);
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT BAB";
			} else if ("4".equalsIgnoreCase(selectedTab)) {
				myLog.debug("Enakmen_IDEnakmen:"+Enakmen_IDEnakmen);
				Seksyen_IDSeksyen="";
				showSeksyen();
				showSeksyenLists();
				doChanges(command);
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT SEKSYEN";
			} else if ("5".equalsIgnoreCase(selectedTab)) {
				SubSeksyen_IDSubSeksyen = "";
				showSubSeksyen();
				showSubSeksyenLists();
				doChanges(command);
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT SUBSEKSYEN";
			} else if ("6".equalsIgnoreCase(selectedTab)) {
				Para_IDPara = "";
				showPara();
				showParaLists();
				doChanges(command);
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT PARA";
			} else if ("7".equalsIgnoreCase(selectedTab)) {
				showSubPara();
				showSubParaLists();
				doChanges(command);
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT SUBPARA";
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				//showJadualPara();
				//showJadualParaList();
				showJadual();
				showJadualLists();
				action = "update";
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT JADUAL";
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				action = "update";
				RO_General = "";
				showJadualLampiran();
				showJadualLampiranLists();
				MAIN_LEGEND = "MAKLUMAT JADUAL(LAMPIRAN)";
				showJadualLampiranFail();
			} else if ("10".equalsIgnoreCase(selectedTab)) {
				MAIN_LEGEND = "MAKLUMAT JADUAL(SUB-SUBPARA)";
			}
		}
		else if ("update".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmEnakmenN.jsp";
			myLog.debug("UPDATING MODE");
			RO_General = "";
			action = "doUpdate";
			if ("0".equalsIgnoreCase(selectedTab)) {

			}
			else if ("1".equalsIgnoreCase(selectedTab)) {
				myLog.info("-doUpdate-"+action);
				showPenggal();

			} else if ("2".equalsIgnoreCase(selectedTab)) {
				context.put("txtNoBahagian","");
				context.put("txtTajukBahagian","");
				showBahagian();
				showBahagianLists();
				
			
		} else if ("3".equalsIgnoreCase(selectedTab)) {
			myLog.info("-ACTION-"+action);
			myLog.info("-Bab_IDBab-"+Bab_IDBab);
			showBab();
			showBabLists();
			
		}else if ("4".equalsIgnoreCase(selectedTab)) {
			myLog.info("-ACTION-"+action);
			myLog.info("-Bab_IDBab-"+Bab_IDBab);
			showSeksyen();
			showSeksyenLists();
			
		}else if ("7".equalsIgnoreCase(selectedTab)) {
			myLog.info("SubPara_IDSubPara -- "+SubPara_IDSubPara);
			showSubPara();
			showSubParaLists();
			
		}
		else if ("9".equalsIgnoreCase(selectedTab)) {
				IDJadualLampiran = (String)this.context.get("IDJadualLampiran");
				showJadualLampiran();
				showJadualLampiranFail();
			}
		} else if ("viewDetail".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmEnakmenN.jsp";
			RO_General = "";
			action = "doUpdate";
			if ("1".equalsIgnoreCase(selectedTab)) {
				showPenggal();
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				showBahagian();
				showBahagianLists();
			} else if ("3".equalsIgnoreCase(selectedTab)) {
				myLog.info("Bab_IDBab -- "+Bab_IDBab);
				showBab();
				showBabLists();
			} else if ("4".equalsIgnoreCase(selectedTab)) {
				showSeksyen();
			} else if ("5".equalsIgnoreCase(selectedTab)) {
				showSubSeksyen();
			} else if ("6".equalsIgnoreCase(selectedTab)) {
				showPara();
				showParaLists();
			} else if ("7".equalsIgnoreCase(selectedTab)) {
				myLog.info("SubPara_IDSubPara -- "+SubPara_IDSubPara);
				showSubPara();
				showSubParaLists();
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				showJadual();
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				showJadualLampiran();
				showJadualLampiranFail();
			}



		} else if ("delete".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmEnakmenN.jsp";
			if ("0".equalsIgnoreCase(selectedTab)) {
				Enakmen_IDEnakmen = getParam("Enakmen_IDEnakmen");
				vm = "app/pdt/frmEnakmenCarian.jsp";
				enakmen.enakmenDelete(Enakmen_IDEnakmen);
				doCarian(session);
			}
			else if ("1".equalsIgnoreCase(selectedTab)) {
				enakmen.penggalDelete(Penggal_IDPenggal);
				showPenggalLists();
				clearPenggal();
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				enakmen.bahagianDelete(Bahagian_IDBahagian);
				showBahagianLists();
			} else if ("3".equalsIgnoreCase(selectedTab)) {
				enakmen.babDelete(Bab_IDBab);
				showBabLists();
				clearBab();
			} else if ("4".equalsIgnoreCase(selectedTab)) {
				enakmen.seksyenDelete(Seksyen_IDSeksyen);
				showSeksyenLists();
			} else if ("5".equalsIgnoreCase(selectedTab)) {
				enakmen.subSeksyenDelete(SubSeksyen_IDSubSeksyen);
				showSubSeksyenLists();
			} else if ("6".equalsIgnoreCase(selectedTab)) {
				enakmen.paraDelete(Para_IDPara);
				showParaLists();
			} else if ("7".equalsIgnoreCase(selectedTab)) {
				enakmen.subParaDelete(SubPara_IDSubPara);
				showSubParaLists();
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				enakmen.jadualDelete(IDJadual);
				showJadualLists();
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				showJadualLampiran();
				myLog.debug("command:"+command);
				if ("deleteLampiran".equals(command)) {
					String idLampiranFail = getParam("idLampiranFail");
					myLog.debug("idLampiranFail="+idLampiranFail);
					enakmen.jadualLampiranFailDelete(idLampiranFail);
					showJadualLampiranFail();
				} else {
					enakmen.jadualLampiranDelete(IDJadualLampiran);
					showJadualLampiranLists();
				}


			}
			RO_General = "";
			action = "update";

		} else if ("doKemaskiniEnakmen".equals(action)) {
			showEnakmen();
			//vm = "app/pdt/frmEnakmenTabEnakmenBaru.jsp";
			vm = "app/pdt/frmEnakmenN.jsp";
			RO_General = "";
			action = "doUpdate";
		} else if ("changeLampiran".equals(action)) {
			vm = "app/pdt/frmEnakmenN.jsp";
			RO_General = "";
			//action = "update";
			String x = getParam("X");
			action = x;
				int j = getParamAsInteger("jumlahlampiran");
				this.context.put("num_files", j);
				context.put("SOC_Jadual", enakmen.getSOCJadual("SOC_Jadual", SOC_Jadual, Enakmen_IDEnakmen, "", ""));
				context.put("txtCatatan",getParam("txtCatatan"));
				context.put("txtTajuk",getParam("txtTajuk"));

			//showJadualLampiran();
			showJadualLampiranFail();
		} else if ("deleteLampiran".equals(action)) {
			vm = "app/pdt/frmEnakmenN.jsp";
			RO_General = "";
			action = "doUpdate";
			showJadualLampiran();
			String idLampiranFail = getParam("idLampiranFail");
			myLog.debug("idLampiranFail="+idLampiranFail);
			myLog.debug("IDJadualLampiran:"+IDJadualLampiran);
			enakmen.jadualLampiranFailDelete(idLampiranFail);
			showJadualLampiranFail();
		}
		else {
			vm = "app/pdt/frmEnakmenCarian.jsp";
			action = request.getParameter("action");
			doCarian(session);
			/*
			context.put("txtNoEnakmen", "");
			context.put("txtNamaEnakmen", "");
			context.put("txdTarikhKuatkuasa", "");
			*/
		}
		//----------------------------------------------
		context.put("Enakmen_IDEnakmen",Enakmen_IDEnakmen);
		context.put("SubSeksyen_IDSubSeksyen", SubSeksyen_IDSubSeksyen);
		context.put("MAIN_LEGEND", MAIN_LEGEND);
		context.put("action", action);
		context.put("selectedTab", selectedTab);
		context.put("RO_General", RO_General);

		//myLogger.debug(vm);
		myLog.debug("action:"+this.context.get("action"));
		myLog.debug("selectedTab:"+selectedTab);
		myLog.debug("vm:"+vm);
		return vm;
		
	}
	


	public void showEnakmen() throws Exception{
		vPapar = enakmen.enakmenPapar(Enakmen_IDEnakmen);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			//context.put("Enakmen_IDEnakmen", hPapar.get("Enakmen_IDEnakmen"));
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_TarikhKuatkuasa", hPapar.get("Enakmen_TarikhKuatkuasa"));
			context.put("Enakmen_TarikhMansuh", hPapar.get("Enakmen_TarikhMansuh"));
			context.put("Enakmen_NoWarta", hPapar.get("Enakmen_NoWarta"));
			context.put("Enakmen_TarikhWarta", hPapar.get("Enakmen_TarikhWarta"));
			if ("1".equalsIgnoreCase((String) hPapar.get("Enakmen_TarafKeselamatan"))) {
				context.put("Enakmen_EnakmenTerbuka", "checked=\"checked\"");
				context.put("Enakmen_EnakmenSulit", "");
			} else {
				context.put("Enakmen_EnakmenTerbuka", "");
				context.put("Enakmen_EnakmenSulit", "checked=\"checked\"");
			}
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_IDSeksyen", hPapar.get("Enakmen_IDSeksyen"));
			
			Long id_seksyen = (long) 0;
			if(!hPapar.get("Enakmen_IDSeksyen").equals("") && !hPapar.get("Enakmen_IDSeksyen").equals("-1"))
			{
				id_seksyen = Long.parseLong((String)(hPapar.get("Enakmen_IDSeksyen")));
			}
			else
			{
				id_seksyen = Utils.parseLong(getParam("socSeksyen"));
			}
			context.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",id_seksyen,null,null));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("Enakmen_Catatan", hPapar.get("Enakmen_Catatan"));
			context.put("Enakmen_TarikhDaftar", hPapar.get("Enakmen_TarikhDaftar"));
			context.put("tag_Dokumen", hPapar.get("tagging"));
			context.put("id_tagdokumen", hPapar.get("idTagging"));
		
		}
		
	}
	///////////////
	public void showPenggal() throws Exception {
		myLog.debug("** showPenggal ** :"+Penggal_IDPenggal);
		vPapar = enakmen.penggalPapar(Penggal_IDPenggal);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("Penggal_IDPenggal",Penggal_IDPenggal);
			context.put("Penggal_NoPenggal", hPapar.get("Penggal_NoPenggal"));
			context.put("Penggal_TajukPenggal", hPapar.get("Penggal_TajukPenggal"));
		}
	}

	public void showPenggalLists() throws Exception {
		vPapar = enakmen.penggalList(Enakmen_IDEnakmen);
		context.put("List_CarianPenggal", vPapar);
	}

	public void clearPenggal() throws Exception {
		context.put("Penggal_IDPenggal",Penggal_IDPenggal);
		context.put("Penggal_NoPenggal", hPapar.get("Penggal_NoPenggal"));
		context.put("Penggal_TajukPenggal", hPapar.get("Penggal_TajukPenggal"));
	}


	//////////////////
	public void showBahagian() throws Exception {
		context.put("Bahagian_NoBahagian","");
		context.put("Bahagian_TajukBahagian","");
		vPapar = enakmen.bahagianPapar(Bahagian_IDBahagian);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("Bahagian_IDBahagian",Bahagian_IDBahagian);
			context.put("Bahagian_NoBahagian", hPapar.get("Bahagian_NoBahagian"));
			context.put("Bahagian_TajukBahagian", hPapar.get("Bahagian_TajukBahagian"));
			context.put("Bahagian_Penggal", enakmen.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Enakmen_IDEnakmen, "", ""));
		} else {
			context.put("Bahagian_Penggal", enakmen.getSOCPenggal("SOC_Penggal", "", Enakmen_IDEnakmen, "", ""));
		}
	}

	public void showBahagianLists() throws Exception {
		vPapar = enakmen.bahagianList(Enakmen_IDEnakmen);
		context.put("List_CarianBahagian", vPapar);
	}
	
	public void showBab() throws Exception {
		//this.context.put("Bab_IDBab", Bab_IDBab);
		context.put("Bab_NoBab","");
		context.put("Bab_TajukBab","");
		context.put("Bab_Penggal", enakmen.getSOCPenggal("SOC_Penggal", SOC_Penggal, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("Bab_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
		vPapar = enakmen.babPapar(Bab_IDBab);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Bab_IDBab", hPapar.get("Bab_IDBab"));
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("Bab_NoBab", hPapar.get("Bab_NoBab"));
			context.put("Bab_TajukBab", hPapar.get("Bab_TajukBab"));
			context.put("Bab_Penggal", enakmen.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));		
			context.put("Bab_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
		} 

	}

	public void showBabLists() throws Exception {
		vPapar = enakmen.babList(Enakmen_IDEnakmen);
		context.put("List_CarianBab", vPapar);
	}

	public void clearBab() throws Exception {
			context.put("Bab_NoBab","");
			context.put("Bab_TajukBab","");
			context.put("Bab_Penggal", enakmen.getSOCPenggal("SOC_Penggal", SOC_Penggal, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Bab_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian",SOC_Bahagian, Enakmen_IDEnakmen, "", ""));
	}

	/////
	public void showSeksyen() throws Exception {
		context.put("Seksyen_NoSeksyen","");
		context.put("Seksyen_TajukSeksyen","");
		context.put("Seksyen_Seksyen","");
		context.put("Seksyen_Proviso","");
		context.put("Seksyen_IDSeksyen",Seksyen_IDSeksyen);
		context.put("Seksyen_Penggal", enakmen.getSOCPenggal("SOC_Penggal", SOC_Penggal, Enakmen_IDEnakmen, "", ""));
		context.put("Seksyen_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Enakmen_IDEnakmen, "", ""));
		/*kalau nak pakai onchange*/
				//, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
		context.put("Seksyen_Bab", enakmen.getSOCBab("SOC_Bab", SOC_Bab,Enakmen_IDEnakmen, "", ""));
		vPapar = enakmen.seksyenPapar(Seksyen_IDSeksyen);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Bab_IDBab", hPapar.get("Bab_IDBab"));
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("Seksyen_NoSeksyen", hPapar.get("Seksyen_NoSeksyen"));
			context.put("Seksyen_TajukSeksyen", hPapar.get("Seksyen_TajukSeksyen"));
			context.put("Seksyen_Seksyen", hPapar.get("Seksyen_Seksyen"));
			context.put("Seksyen_Proviso", hPapar.get("Seksyen_Proviso"));
			context.put("Seksyen_Penggal", enakmen.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Enakmen_IDEnakmen, "", ""));
			//context.put("Seksyen_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(), hPapar.get("Penggal_IDPenggal").toString(), RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
			context.put("Seksyen_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(),Enakmen_IDEnakmen, "", ""));
			context.put("Seksyen_Bab", enakmen.getSOCBab("SOC_Bab",(String)hPapar.get("Bab_IDBab"),Enakmen_IDEnakmen, "", ""));
		}

	}

	public void showSeksyenLists() throws Exception {
		vPapar = enakmen.seksyenList(Enakmen_IDEnakmen);
		context.put("List_CarianSeksyen", vPapar);
	}
	///////////
	public void showSubSeksyen() throws Exception {
		context.put("SubSeksyen_NoSubSeksyen","");
		context.put("SubSeksyen_TajukSubSeksyen","");
		context.put("SubSeksyen_SubSeksyen","");
		context.put("SubSeksyen_Proviso","");
		context.put("SubSeksyen_Penggal", enakmen.getSOCPenggal("SOC_Penggal", SOC_Penggal, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("SubSeksyen_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
		context.put("SubSeksyen_Bab", enakmen.getSOCBab("SOC_Bab", SOC_Bab, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBab');\" "));
		context.put("SubSeksyen_Seksyen", enakmen.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, Enakmen_IDEnakmen, "", ""));
		vPapar = enakmen.subSeksyenPapar(SubSeksyen_IDSubSeksyen);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("SubSeksyen_NoSubSeksyen", hPapar.get("SubSeksyen_NoSubSeksyen"));
			context.put("SubSeksyen_TajukSubSeksyen", hPapar.get("SubSeksyen_TajukSubSeksyen"));
			context.put("SubSeksyen_SubSeksyen", hPapar.get("SubSeksyen_SubSeksyen"));
			context.put("SubSeksyen_Proviso", hPapar.get("SubSeksyen_Proviso"));
			context.put("SubSeksyen_Penggal", enakmen.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("SubSeksyen_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
			context.put("SubSeksyen_Bab", enakmen.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(),Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBaB');\" "));
			context.put("SubSeksyen_Seksyen", enakmen.getSOCSeksyen("SOC_Seksyen", hPapar.get("Seksyen_IDSeksyen").toString(),Enakmen_IDEnakmen, "", ""));
		}

	}

	public void showSubSeksyenLists() throws Exception {
		vPapar = enakmen.subSeksyenList(Enakmen_IDEnakmen);
		context.put("List_CarianSubSeksyen", vPapar);
	}

	//////////

	public void showPara() throws Exception {
		
		context.put("Para_Para","");
		context.put("Para_Jadual","");
		context.put("Para_Penggal", enakmen.getSOCPenggal("SOC_Penggal", SOC_Penggal, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("Para_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
		//context.put("Para_Bab", enakmen.getSOCBab("SOC_Bab", SOC_Bab, Enakmen_IDEnakmen, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
		context.put("Para_Bab", enakmen.getSOCBab("SOC_Bab", SOC_Bab, Enakmen_IDEnakmen, "", ""));
		context.put("Para_Seksyen", enakmen.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeSeksyen');\" "));
		context.put("Para_SubSeksyen", enakmen.getSOCSubSeksyen("SOC_SubSeksyen", SOC_SubSeksyen, SOC_Seksyen, "", ""));
		vPapar = enakmen.paraPapar(Para_IDPara);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Para_IDPara", hPapar.get("Para_IDPara"));
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("Para_Para", hPapar.get("Para_Para"));
			context.put("Para_Jadual", hPapar.get("Para_Jadual"));
			context.put("Para_Penggal", enakmen.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Para_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(),Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
			//context.put("Para_Bab", enakmen.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(),Enakmen_IDEnakmen, RO_General, " onchange=\"doChangeSOC('changeBaB');\" "));
			context.put("Para_Bab", enakmen.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(),Enakmen_IDEnakmen, "", ""));
			context.put("Para_Seksyen", enakmen.getSOCSeksyen("SOC_Seksyen", hPapar.get("Seksyen_IDSeksyen").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeSeksyen');\" "));
			context.put("Para_SubSeksyen", enakmen.getSOCSubSeksyen("SOC_SubSeksyen", hPapar.get("SubSeksyen_IDSubSeksyen").toString(), hPapar.get("Seksyen_IDSeksyen").toString(), "", ""));
		}
	}

	public void showParaLists() throws Exception {
		myLog.debug("ENAKMEN ID="+Enakmen_IDEnakmen);
		vPapar = enakmen.paraList(Enakmen_IDEnakmen);
		context.put("List_CarianPara", vPapar);
	}

	/////////
	public void showSubPara() throws Exception {
		context.put("SubPara_SubPara","");
		context.put("SubPara_Jadual","");
		context.put("SubPara_Penggal", enakmen.getSOCPenggal("SOC_Penggal", SOC_Penggal, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("SubPara_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
		context.put("SubPara_Bab", enakmen.getSOCBab("SOC_Bab", SOC_Bab, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBab');\" "));
		context.put("SubPara_Seksyen", enakmen.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeSeksyen');\" "));
		context.put("SubPara_SubSeksyen", enakmen.getSOCSubSeksyen("SOC_SubSeksyen", SOC_SubSeksyen, SOC_Seksyen, "", ""));
		context.put("SubPara_Para", enakmen.getSOCPara("SOC_Para", SOC_Para, Enakmen_IDEnakmen, "", ""));
		vPapar = enakmen.subParaPapar(SubPara_IDSubPara);

		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Enakmen_IDEnakmen", hPapar.get("Enakmen_IDEnakmen"));
			context.put("SubPara_IDSubPara", hPapar.get("SubPara_IDSubPara"));
			context.put("Enakmen_NoEnakmen", hPapar.get("Enakmen_NoEnakmen"));
			context.put("Enakmen_NamaEnakmen", hPapar.get("Enakmen_NamaEnakmen"));
			context.put("Enakmen_NoFail", hPapar.get("Enakmen_NoFail"));
			context.put("SubPara_SubPara", hPapar.get("SubPara_SubPara"));
			context.put("SubPara_Jadual", hPapar.get("SubPara_Jadual"));
			context.put("SubPara_Penggal", enakmen.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("SubPara_Bahagian", enakmen.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(),Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBahagian');\" "));
			context.put("SubPara_Bab", enakmen.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(), Enakmen_IDEnakmen, "", " onchange=\"doChangeSOC('changeBaB');\" "));
			context.put("SubPara_Seksyen", enakmen.getSOCSeksyen("SOC_Seksyen", hPapar.get("Seksyen_IDSeksyen").toString(), hPapar.get("Enakmen_IDEnakmen").toString(), "", " onchange=\"doChangeSOC('changeSeksyen');\" "));
			context.put("SubPara_SubSeksyen", enakmen.getSOCSubSeksyen("SOC_SubSeksyen", hPapar.get("SubSeksyen_IDSubSeksyen").toString(), hPapar.get("Seksyen_IDSeksyen").toString(), "", ""));
			context.put("SubPara_Para", enakmen.getSOCPara("SOC_Para", hPapar.get("Para_IDPara").toString(),Enakmen_IDEnakmen, "", ""));

		}
	}

	public void showSubParaLists() throws Exception {
		vPapar = enakmen.subParaList(Enakmen_IDEnakmen);
		context.put("List_CarianSubPara", vPapar);
	}
	//
	public void doChanges(String command) throws Exception {


	}

	public void clearFieldEnakmen() {

		context.put("Enakmen_NoEnakmen","");
		context.put("Enakmen_NamaEnakmen","");
		context.put("Enakmen_TarikhKuatkuasa","");
		context.put("Enakmen_TarikhMansuh","");
		context.put("Enakmen_NoWarta","");
		context.put("Enakmen_TarikhWarta","");
		if ("1".equalsIgnoreCase((String) hPapar.get("Enakmen_TarafKeselamatan"))) {
			context.put("Enakmen_EnakmenTerbuka", "checked=\"checked\"");
			context.put("Enakmen_EnakmenSulit", "");
		} else {
			context.put("Enakmen_EnakmenTerbuka", "");
			context.put("Enakmen_EnakmenSulit", "checked=\"checked\"");
		}
		context.put("Enakmen_NoEnakmen","");
		context.put("Enakmen_IDSeksyen","");
		context.put("Enakmen_NoFail","");
		context.put("Enakmen_Catatan","");
		context.put("Enakmen_TarikhDaftar","");
	}

	///////////
	public void setViewMode() {
		action = "update";
		RO_General = "";
	}
	////////
	public void UploadFile(String idEnakmen) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	saveBlob(idEnakmen,item);
			    }
		    }
		}
	}

	public void saveBlob(String idEnakmen,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("UPDATE tblpdtenakmen " +
			 		"SET Content=?,jenis_mime=? WHERE id_enakmen=?");
			 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
			 ps.setString(2,item.getName());
			 ps.setString(3,idEnakmen);
			 ps.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}

/////////// JADUAL

	public void UploadJadual(String idJadual) throws Exception {
		myLog.debug("UploadJadual:"+idJadual);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);
		    //myLogger.debug("uploading..."+items);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	saveJadualBlob(idJadual,item);
			    }
		    }
		}
	}

	public void saveJadualBlob(String idJadual,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = con.prepareStatement("UPDATE tblpdtjadual " +
			 		"SET Content=?,jenis_mime=? WHERE id_jadual=?");
			 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
			 ps.setString(2,item.getName());
			 ps.setString(3,idJadual);
			 ps.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}

	/////////////////////// JADUAL LAMPIRAN
	public void UploadJadualLampiran(String idJadualLampiran) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);

		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	 myLog.debug("uploading..."+item);
			    	saveJadualLampiranBlob(item.getFieldName(),idJadualLampiran,item);
			    }
		    }
		}
	}

	public void saveJadualLampiranBlob(String fName,String idJadualLampiran,FileItem item) throws Exception {
		//myLogger.debug("saveJadualLampiranBlob:"+item);
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 PreparedStatement ps = null;

			 sql = "INSERT INTO tblpdtjaduallampiran_fail (id_jaduallampiran,content," +
			 		"nama_lampiran,jenis_mime,tarikh_masuk) VALUES (?,?,?,?,sysdate)";
			 ps = con.prepareStatement(sql);
			 ps.setString(1,idJadualLampiran);
			 ps.setBinaryStream(2,item.getInputStream(),(int)item.getSize());
			 ps.setString(3,item.getName());
			 ps.setString(4,item.getContentType());
			 ps.executeUpdate();
			 con.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}

	public void debugParams() {
		Enumeration allparam = request.getParameterNames();
		String name="";String value="";
		for (; allparam.hasMoreElements(); ) {
	        // Get the name of the request parameter
	        name = (String)allparam.nextElement();
	        // Get the value of the request parameter
	        value = request.getParameter(name);
	        //System.out.println(name +"="+value);
		    myLog.debug(name +"="+value);
		}
	}

	public void doCarian(HttpSession session) throws Exception{
		//vCarian = enakmen.enakmenCarian(Cari_NoEnakmen, Cari_NamaEnakmen	, Cari_TarikhKuatkuasa, Cari_Seksyen);
		String tag = getParam("tag_dokumen");
		vCarian = enakmen.enakmenCarian(Cari_NoEnakmen, Cari_NamaEnakmen
				, Cari_TarikhKuatkuasa, Cari_Seksyen,tag);
		context.put("txtNoEnakmen", "");
		context.put("txtNamaEnakmen", "");
		context.put("txdTarikhKuatkuasa", "");
		context.put("", "");
		context.put("tag_Dokumen",tag);
		//context.put("List_CarianEnakmen", vCarian);
		setupPage(session,action,vCarian);
		
	}
	
	///////
	public void showJadualLists() throws Exception {
		vPapar = enakmen.JadualList(Enakmen_IDEnakmen);
		context.put("List_CarianJadual", vPapar);
	}


	public void showJadual() throws Exception {
		context.put("IDJadual",IDJadual);
		context.put("NamaJadual","");
		context.put("TajukJadual","");
		context.put("CatatanJadual","");
		hPapar = enakmen.JadualPapar(IDJadual);
		if (hPapar != null) {
			context.put("NamaJadual", hPapar.get("NamaJadual"));
			context.put("TajukJadual", hPapar.get("TajukJadual"));
			context.put("CatatanJadual", hPapar.get("CatatanJadual"));
		}
	}

	////////

	public void showJadualLampiran() throws Exception {
		context.put("IDJadualLampiran", IDJadualLampiran);
		context.put("SOC_Jadual", enakmen.getSOCJadual("SOC_Jadual", SOC_Jadual, Enakmen_IDEnakmen, "", ""));
		context.put("txtCatatan","");
		context.put("txtTajuk","");
		hPapar = enakmen.JadualLampiranPapar(IDJadualLampiran);
		//if (!hPapar.isEmpty()) {
		if (hPapar != null) {
			context.put("SOC_Jadual", enakmen.getSOCJadual("SOC_Jadual",(String)hPapar.get("IDJadual"), Enakmen_IDEnakmen, "", ""));
			context.put("txtCatatan",(String)hPapar.get("Catatan"));
			context.put("txtTajuk",(String)hPapar.get("Tajuk"));
		}

	}

	public void showJadualLampiranLists() throws Exception {
		vPapar = enakmen.JadualLampiranList(Enakmen_IDEnakmen);
		context.put("List_CarianJadualLampiran", vPapar);
	}

	public void showJadualLampiranFail() throws Exception {
		//Show Lampiran fail
		this.context.put("IDJadualLampiran", IDJadualLampiran);
		vPapar = enakmen.JadualLampiranFailList(IDJadualLampiran);
		context.put("List_LampiranFail", vPapar);
	}


}
