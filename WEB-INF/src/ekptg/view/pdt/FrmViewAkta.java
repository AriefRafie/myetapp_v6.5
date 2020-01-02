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
import ekptg.model.pdt.FrmModelAkta;

public class FrmViewAkta extends AjaxBasedModule {
	private static Logger myLogger = Logger.getLogger(FrmViewAkta.class);
	FrmModelAkta akta = new FrmModelAkta();

	@SuppressWarnings("unchecked")
	
	Vector vCarian = null;
	Vector vPapar = null;
	Hashtable hPapar = null;
	///AKTA
	String Akta_IDAkta = "";
	String Akta_NoAkta = "";
	String Akta_NamaAkta = "";
	String Akta_TarikhKuatkuasa = "";
	String Akta_TarikhMansuh = "";
	String Akta_NoWarta = "";
	String Akta_TarikhWarta = "";
	String Akta_TarafKeselamatan = "";
	String Akta_Seksyen = "";
	String Akta_NoFail = "";
	String Akta_Catatan = "";
	String Akta_Dokumen = "";
	String Akta_TarikhDaftar = "";
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
	
	String Cari_NoAkta = "";
	String Cari_NamaAkta = "";
	String Cari_TarikhKuatkuasa = "";
	String Cari_Seksyen = "";
	String IDJadual = "";
	String IDJadualLampiran = "";
	
	public String doTemplate2() throws Exception {
		//debugParams();
		String vm = "";
		action = getParam("action");
		myLogger.debug("REAL ACTION:::::::::::"+getParam("action"));
		String command = getParam("command");
		
		String selectedTab = getParam("selectedTab");
		if ("".equalsIgnoreCase(selectedTab)) {
			selectedTab = "0";
		}
		
		myLogger.info("selectedTab : "+selectedTab);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
		HttpSession session = this.request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String MAIN_LEGEND = "MAKLUMAT AKTA";
		RO_General = "readonly=\"readonly\"";
		
		//----------------------------------------------
		// get parameters
		Cari_NoAkta = getParam("txtNoAkta");
		Cari_NamaAkta = getParam("txtNamaAkta");
		Cari_TarikhKuatkuasa = getParam("txdTarikhKuatkuasa");
		Cari_Seksyen = getParam("socSeksyen");
		
		Akta_IDAkta = getParam("Akta_IDAkta");
		myLogger.info("Akta_IDAkta :::::::::: "+Akta_IDAkta);
		Akta_NoAkta = getParam("txtNoAkta");
		Akta_NamaAkta = getParam("txtNamaAkta");
		Akta_TarikhKuatkuasa = getParam("txdTarikhKuatkuasa");
		Akta_TarikhMansuh = getParam("txdTarikhMansuh");
		Akta_NoWarta = getParam("txtNoWarta");
		Akta_TarikhWarta = getParam("txdTarikhWarta");
		Akta_TarafKeselamatan = getParam("sorTaraf");
		Akta_Seksyen = getParam("txtSeksyen");
		Akta_NoFail = getParam("txtNoFail");
		Akta_Catatan = getParam("txtCatatan");
		Akta_Dokumen = getParam("txfMuatNaikDokumen");
		Akta_TarikhDaftar = getParam("txdTarikhDaftar");
		
		Penggal_IDPenggal = getParam("Penggal_IDPenggal");		
		myLogger.info("Penggal_IDPenggal get param:"+Penggal_IDPenggal);
		
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
			vm = "app/pdt/frmAktaTabAktaBaru.jsp";
			clearFieldAkta();
			action = "update";
			RO_General = "";
			this.context.put("Akta_TarikhDaftar", sdf.format(now));
			this.context.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",null,null,""));
			
		}else if (action.equalsIgnoreCase("save")) {
			myLogger.debug("** SAVE **");
			vm = "app/pdt/frmAktaN.jsp";
			action = "view";
			String mode = getParam("mode");
			if (selectedTab.equalsIgnoreCase("0")) {
				//Update AKTA
				if (mode.equals("update")) {
					vm = "app/pdt/frmAktaN.jsp";
					myLogger.debug("** MASUK UPLOAD ** Akta_IDAkta : "+Akta_IDAkta);
					UploadFile(Akta_IDAkta);
					Hashtable hAktaUpdate = new Hashtable();
					hAktaUpdate.put("idAkta",Akta_IDAkta);
					hAktaUpdate.put("noAkta",Akta_NoAkta);
					hAktaUpdate.put("namaAkta",Akta_NamaAkta);
					hAktaUpdate.put("tarikhKuatkusa",Akta_TarikhKuatkuasa);
					hAktaUpdate.put("noWarta",Akta_NoWarta);
					hAktaUpdate.put("tarikhMansuh",Akta_TarikhMansuh);
					hAktaUpdate.put("tarikhWarta",Akta_TarikhWarta);
					hAktaUpdate.put("catatan", Akta_Catatan);
					hAktaUpdate.put("tarikhDaftar",Akta_TarikhDaftar);
					hAktaUpdate.put("noFail",Akta_NoFail);
					hAktaUpdate.put("tagDokumen", getParam("tag_dokumen"));
					hAktaUpdate.put("idSeksyen", getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "" : Integer.parseInt(getParam("socSeksyen")));
					akta.aktaUpdate(hAktaUpdate);
					
					
					
				} else {
					vm = "app/pdt/frmAktaTabAktaBaru.jsp";
					Hashtable hAddAkta = new Hashtable();
					hAddAkta.put("noAkta",Akta_NoAkta);
					hAddAkta.put("namaAkta",Akta_NamaAkta);
					hAddAkta.put("tarikhKuatkuasa",Akta_TarikhKuatkuasa);
//					hAddAkta.put("tarikhMansuh", getParam("txtTarikhMansuh"));
					hAddAkta.put("tarikhMansuh", Akta_TarikhMansuh);
					hAddAkta.put("noWarta",Akta_NoWarta);
					hAddAkta.put("tarikhWarta",Akta_TarikhWarta);
				
//					if(checked.equals("sulit"))	
//						hAddAkta.put("idKeselamatan","3");
//					else
//						hAddAkta.put("idKeselamatan","1");
//					
					myLogger.info("Akta_Catatan="+Akta_Catatan);
					hAddAkta.put("catatan",Akta_Catatan);
					hAddAkta.put("NoFail", getParam("socNoFail"));
					hAddAkta.put("idSeksyen", getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "" : Integer.parseInt(getParam("socSeksyen")));
					hAddAkta.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(getParam("socSeksyen")),null,null));
					hAddAkta.put("tarikhDaftar", Akta_TarikhDaftar);
					hAddAkta.put("idMasuk",user_id);
					hAddAkta.put("tarikhMasuk",Akta_TarikhDaftar);
					hAddAkta.put("tagDokumen", getParam("tag_dokumen"));
					hAddAkta.put("txfMuatNaikDokumen", getParam("Akta_Dokumen"));
					
					Akta_IDAkta = akta.aktaAdd(hAddAkta);
					UploadFile(Akta_IDAkta);
					myLogger.debug("id akta:"+Akta_IDAkta);
					myLogger.debug("Simpan Akta");
				
				}
				showAkta();
				RO_General = "readonly=\"readonly\"";
			
			} else if ("1".equalsIgnoreCase(selectedTab)) {
				//SIMPAN PENGGAL
				Hashtable hAddPenggal = new Hashtable();
				if ("update".equals(mode)) {
					hAddPenggal.put("idAktaPenggal",Penggal_IDPenggal);
					hAddPenggal.put("noPenggal",getParam("txtNoPenggal"));
					hAddPenggal.put("tajukPenggal",getParam("txtTajukPenggal"));
					hAddPenggal.put("idMasuk",user_id);
					akta.penggalUpdate(hAddPenggal);
					action = "view";
					showPenggal();
				} else {
					hAddPenggal.put("idAkta",Akta_IDAkta);
					hAddPenggal.put("noPenggal",getParam("txtNoPenggal"));
					hAddPenggal.put("tajukPenggal",getParam("txtTajukPenggal"));
					hAddPenggal.put("idMasuk",user_id);
					hAddPenggal.put("tarikhMasuk", sdf.format(now));
					akta.penggalAdd(hAddPenggal);
					action = "update";
					RO_General = "";
				}
				showPenggalLists();
				
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				if ("update".equals(mode)) {
					akta.bahagianSave("update",Bahagian_IDBahagian, Akta_IDAkta, 
							SOC_Penggal, Bahagian_NoBahagian, Bahagian_TajukBahagian);
					action = "view";
					showBahagian();
				} else {
					akta.bahagianSave("save",Bahagian_IDBahagian, Akta_IDAkta, 
							SOC_Penggal, Bahagian_NoBahagian, Bahagian_TajukBahagian);
					action = "update";
					RO_General = "";
				}
				showBahagianLists();
			} else if ("3".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Akta_IDAkta) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian)) {
//					akta.babSave(Bab_IDBab, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, Bab_NoBab, Bab_TajukBab);
//				}
				//BAB
				if ("update".equals(mode)) {
					akta.babSave("update",Bab_IDBab, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, Bab_NoBab, Bab_TajukBab);
					action = "view";
					showBab();
				} else {
					akta.babSave("save",Bab_IDBab, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, Bab_NoBab, Bab_TajukBab);
					action = "update";
					RO_General = "";
				}
				showBabLists();
			} else if ("4".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Akta_IDAkta) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab)) {
//					akta.seksyenSave(Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, Seksyen_Seksyen, Seksyen_Proviso);
//				}
				if ("update".equals(mode)) {
					akta.seksyenSave("update",Seksyen_IDSeksyen, Akta_IDAkta, 
							SOC_Penggal, SOC_Bahagian, SOC_Bab, 
							Seksyen_Seksyen, Seksyen_Proviso,
							Seksyen_NoSeksyen,Seksyen_TajukSeksyen,user_id);
					action = "view";
					showSeksyen();
				} else {
					akta.seksyenSave("save",Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, 
							SOC_Bab, Seksyen_Seksyen, Seksyen_Proviso,
							Seksyen_NoSeksyen,Seksyen_TajukSeksyen,user_id);
					action = "update";
					RO_General = "";
				}
				showSeksyenLists();
			} else if ("5".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Akta_IDAkta) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab) && !"".equalsIgnoreCase(SOC_Seksyen)) {
//					akta.subSeksyenSave(Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SubSeksyen_SubSeksyen, SubSeksyen_SubProviso);
//				}
				myLogger.debug("tajuk sub seksyen:"+SubSeksyen_TajukSubSeksyen);
				if ("update".equals(mode)) {
					akta.subSeksyenSave("update",SubSeksyen_IDSubSeksyen, Akta_IDAkta, 
							SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen,
							SubSeksyen_SubSeksyen, SubSeksyen_SubProviso,
							SubSeksyen_NoSubSeksyen,SubSeksyen_TajukSubSeksyen,user_id);
					action = "view";
					showSubSeksyen();
				} else {
					akta.subSeksyenSave("save",SubSeksyen_IDSubSeksyen, Akta_IDAkta, SOC_Penggal, 
							SOC_Bahagian, SOC_Bab, SOC_Seksyen, 
							SubSeksyen_SubSeksyen, SubSeksyen_SubProviso,
							SubSeksyen_NoSubSeksyen,SubSeksyen_TajukSubSeksyen,user_id);
					action = "update";
					RO_General = "";
				}
				showSubSeksyenLists();				
			} else if ("6".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Akta_IDAkta) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab) && !"".equalsIgnoreCase(SOC_Seksyen) && !"".equalsIgnoreCase(SOC_SubSeksyen)) {
//					akta.paraSave(Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, Para_Para, Para_Jadual);
//				}
				if ("update".equals(mode)) {
					akta.paraSave("update",Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, Para_Para, Para_Jadual);
					action = "view";
					showPara();
				} else {
					akta.paraSave("save",Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, Para_Para, Para_Jadual);
					action = "update";
					RO_General = "";
				}
				showParaLists();	
			} else if ("7".equalsIgnoreCase(selectedTab)) {
//				if (!"".equalsIgnoreCase(Akta_IDAkta) && !"".equalsIgnoreCase(SOC_Penggal) && !"".equalsIgnoreCase(SOC_Bahagian) && !"".equalsIgnoreCase(SOC_Bab) && !"".equalsIgnoreCase(SOC_Seksyen) && !"".equalsIgnoreCase(SOC_SubSeksyen) & !"".equalsIgnoreCase(SOC_Para)) {
//					akta.subParaSave(Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
//				}
				if ("update".equals(mode)) {
					akta.subParaSave("update",Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
					action = "view";
					showSubPara();
				} else {
					akta.subParaSave("save",Seksyen_IDSeksyen, Akta_IDAkta, SOC_Penggal, SOC_Bahagian, SOC_Bab, SOC_Seksyen, SOC_SubSeksyen, SOC_Para, SubPara_SubPara, SubPara_Jadual);
					action = "update";
					RO_General = "";
				}
				showSubParaLists();				
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				String NamaJadual=getParam("txtNamaJadual");
				String TajukJadual=getParam("txtTajukJadual");
				String CatatanJadual=getParam("txtCatatanJadual");
				
				if ("update".equals(mode)) {
					String AKTA_IDJadual = akta.JadualParaSave("update", IDJadual, Akta_IDAkta, 
							NamaJadual,TajukJadual,CatatanJadual,user_id);
					UploadJadual(IDJadual);
					action = "view";
					showJadual();
				} else {
					String AKTA_IDJadual = akta.JadualParaSave("save", IDJadual, Akta_IDAkta, 
							NamaJadual,TajukJadual,CatatanJadual,user_id);
					UploadJadual(AKTA_IDJadual);
					action = "update";
					RO_General = "";
				}
				showAkta();
				showJadualLists();
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				myLogger.debug("Simpan Jadual");
				myLogger.debug("mode:"+mode);
				//lampiran jadual save
				String tajuk = getParam("txtTajuk");
				String catatan = getParam("txtCatatan");
				if ("update".equals(mode)) {
					String AKTA_IDJadualLampiran = akta.JadualLampiranSave("update", IDJadualLampiran, 
							SOC_Jadual,tajuk,catatan,user_id);
					//NEED TO CHANGE 
					UploadJadualLampiran(IDJadualLampiran);
					action = "view";
				} else {
					String AKTA_IDJadualLampiran = akta.JadualLampiranSave("save", IDJadualLampiran, 
							SOC_Jadual,tajuk, catatan,user_id);
					UploadJadualLampiran(AKTA_IDJadualLampiran);
					action = "update";
					RO_General = "";
				}
				showAkta();
				showJadualLampiran();
				showJadualLampiranLists();
				showJadualLampiranFail();
			} else if ("10".equalsIgnoreCase(selectedTab)) {
				
			}
			
		}
		else if ("cari".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmAktaCarian.jsp";
			doCarian(session);
		//} else if ("view".equalsIgnoreCase(action) || "update".equalsIgnoreCase(action)) {
		} else if (action.equalsIgnoreCase("view")) {
			myLogger.debug(" *** VIEW ***");
			this.context.put("enctype","enctype=\"multipart/form-data\"");
			vm = "app/pdt/frmAktaN.jsp";
			
			if ("update".equalsIgnoreCase(action)) {
				RO_General = "";
			}
			showAkta();
			context.put("Akta_IDAkta", Akta_IDAkta);
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

			if (selectedTab.equalsIgnoreCase("0")) {
				String mode = getParam("mode");
				
				if ("".equals(mode)) {
					action = "doUpdate";
				} else {
					action = "update";
				}
				
				myLogger.debug("mode:"+mode);
				RO_General = "";
				MAIN_LEGEND = "MAKLUMAT AKTA";
			} else if ("1".equalsIgnoreCase(selectedTab)) {
				showPenggalLists();
				context.put("Penggal_NoPenggal","");
				context.put("Penggal_TajukPenggal","");
				MAIN_LEGEND = "MAKLUMAT PENGGAL";
				action = "update";
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
				myLogger.debug("Akta_IDAkta:"+Akta_IDAkta);
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
			vm = "app/pdt/frmAktaN.jsp";
			myLogger.debug("UPDATING MODE");
			RO_General = "";
			action = "doUpdate";
			if ("0".equalsIgnoreCase(selectedTab)) {
				showAkta();
			}
			else if ("1".equalsIgnoreCase(selectedTab)) {
				
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				context.put("txtNoBahagian","");
				context.put("txtTajukBahagian","");
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				IDJadualLampiran = (String)this.context.get("IDJadualLampiran");
				showJadualLampiran();
				showJadualLampiranFail();
			}
		} else if ("viewDetail".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmAktaN.jsp";
			RO_General = "";
			action = "doUpdate";
			if ("1".equalsIgnoreCase(selectedTab)) {
				showPenggal();
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				showBahagian();
			} else if ("3".equalsIgnoreCase(selectedTab)) {
				showBab();
			} else if ("4".equalsIgnoreCase(selectedTab)) {
				showSeksyen();
			} else if ("5".equalsIgnoreCase(selectedTab)) {
				showSubSeksyen();
			} else if ("6".equalsIgnoreCase(selectedTab)) {
				showPara();
			} else if ("7".equalsIgnoreCase(selectedTab)) {
				showSubPara();
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				showJadual();
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				showJadualLampiran();
				showJadualLampiranFail();
			}


			
		} else if ("delete".equalsIgnoreCase(action)) {
			vm = "app/pdt/frmAktaN.jsp";
			if ("0".equalsIgnoreCase(selectedTab)) {
				Akta_IDAkta = getParam("Akta_IDAkta");
				vm = "app/pdt/frmAktaCarian.jsp";
				akta.aktaDelete(Akta_IDAkta);
				doCarian(session);
			}
			else if ("1".equalsIgnoreCase(selectedTab)) {
				akta.penggalDelete(Penggal_IDPenggal);
				showPenggalLists();
				clearPenggal();
			} else if ("2".equalsIgnoreCase(selectedTab)) {
				akta.bahagianDelete(Bahagian_IDBahagian);
				showBahagianLists();
			} else if ("3".equalsIgnoreCase(selectedTab)) {
				akta.babDelete(Bab_IDBab);
				showBabLists();
				clearBab();
			} else if ("4".equalsIgnoreCase(selectedTab)) {
				akta.seksyenDelete(Seksyen_IDSeksyen);
				showSeksyenLists();
			} else if ("5".equalsIgnoreCase(selectedTab)) {
				akta.subSeksyenDelete(SubSeksyen_IDSubSeksyen);
				showSubSeksyenLists();
			} else if ("6".equalsIgnoreCase(selectedTab)) {
				akta.paraDelete(Para_IDPara);
				showParaLists();
			} else if ("7".equalsIgnoreCase(selectedTab)) {
				akta.subParaDelete(SubPara_IDSubPara);
				showSubParaLists();
			} else if ("8".equalsIgnoreCase(selectedTab)) {
				akta.jadualDelete(IDJadual);
				showJadualLists();
			} else if ("9".equalsIgnoreCase(selectedTab)) {
				showJadualLampiran();
				myLogger.debug("command:"+command);
				if ("deleteLampiran".equals(command)) {
					String idLampiranFail = getParam("idLampiranFail");
					myLogger.debug("idLampiranFail="+idLampiranFail);
					akta.jadualLampiranFailDelete(idLampiranFail);
					showJadualLampiranFail();
				} else {
					akta.jadualLampiranDelete(IDJadualLampiran);
					showJadualLampiranLists();
				}
				
				
			} 
			RO_General = "";
			action = "update";
			
		} else if ("doKemaskiniAkta".equals(action)) {
			showAkta();
			//vm = "app/pdt/frmAktaTabAktaBaru.jsp";
			vm = "app/pdt/frmAktaN.jsp";
			RO_General = "";
			action = "doUpdate";
		} else if ("changeLampiran".equals(action)) {
			vm = "app/pdt/frmAktaN.jsp";
			RO_General = "";
			String x = getParam("X");
			action = x;
			//action = getParam("action");
			//action = "update";
			
				int j = getParamAsInteger("jumlahlampiran");
				this.context.put("num_files", j);
				context.put("SOC_Jadual", akta.getSOCJadual("SOC_Jadual", SOC_Jadual, Akta_IDAkta, RO_General, ""));
				context.put("txtCatatan",getParam("txtCatatan"));
				context.put("txtTajuk",getParam("txtTajuk"));
			
			//showJadualLampiran();
			showJadualLampiranFail();
		} else if ("deleteLampiran".equals(action)) {
			vm = "app/pdt/frmAktaN.jsp";
			RO_General = "";
			action = "doUpdate";
			showJadualLampiran();
			String idLampiranFail = getParam("idLampiranFail");
			myLogger.debug("idLampiranFail="+idLampiranFail);
			myLogger.debug("IDJadualLampiran:"+IDJadualLampiran);
			akta.jadualLampiranFailDelete(idLampiranFail);
			showJadualLampiranFail();
		
		}else {
			vm = "app/pdt/frmAktaCarian.jsp";
			action = request.getParameter("action");
			doCarian(session);
			/*
			context.put("txtNoAkta", "");
			context.put("txtNamaAkta", "");
			context.put("txdTarikhKuatkuasa", "");
			*/
		}
		//----------------------------------------------
		context.put("Akta_IDAkta",Akta_IDAkta);
		context.put("SubSeksyen_IDSubSeksyen", SubSeksyen_IDSubSeksyen);
		context.put("MAIN_LEGEND", MAIN_LEGEND);
		context.put("action", action);
		context.put("selectedTab", selectedTab);
		context.put("RO_General", RO_General);
		//myLogger.debug(vm);
		myLogger.debug("action:"+this.context.get("action")+",selectedTab:"+selectedTab);
		//myLogger.debug("selectedTab:"+selectedTab);
		myLogger.debug("vm:"+vm);
		return vm;
	
	}
	
	
	public void showAkta() throws Exception{
		vPapar = akta.aktaPapar(Akta_IDAkta);
		myLogger.info(" vPapar :: "+vPapar);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Akta_IDAkta", hPapar.get("Akta_IDAkta"));
			context.put("Akta_NoAkta", hPapar.get("Akta_NoAkta"));
			context.put("Akta_NamaAkta", hPapar.get("Akta_NamaAkta"));
			context.put("Akta_TarikhKuatkuasa", hPapar.get("Akta_TarikhKuatkuasa"));
			context.put("Akta_TarikhMansuh", hPapar.get("Akta_TarikhMansuh"));
			context.put("Akta_NoWarta", hPapar.get("Akta_NoWarta"));
			context.put("Akta_TarikhWarta", hPapar.get("Akta_TarikhWarta"));
			if ("1".equalsIgnoreCase((String) hPapar.get("Akta_TarafKeselamatan"))) {
				context.put("Akta_AktaTerbuka", "checked=\"checked\"");
				context.put("Akta_AktaSulit", "");
			} else {
				context.put("Akta_AktaTerbuka", "");
				context.put("Akta_AktaSulit", "checked=\"checked\"");
			}
			context.put("Akta_NoAkta", hPapar.get("Akta_NoAkta"));
			context.put("Akta_IDSeksyen", hPapar.get("Akta_IDSeksyen"));
			//context.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(getParam("socSeksyen")),null,null));
			myLogger.info(" XXXXXXXXXXXX :"+hPapar.get("Akta_IDSeksyen"));
			Long id_seksyen = (long) 0;
			if(!hPapar.get("Akta_IDSeksyen").equals("") && !hPapar.get("Akta_IDSeksyen").equals("-1"))
			{
				id_seksyen = Long.parseLong((String)(hPapar.get("Akta_IDSeksyen")));
			}
			else
			{
				id_seksyen = Utils.parseLong(getParam("socSeksyen"));
			}
			
			myLogger.info(" id_seksyen :"+id_seksyen);
			
			context.put("socSeksyen",HTML.SelectSeksyen("socSeksyen",id_seksyen,null,null));
			context.put("Akta_NoFail", hPapar.get("Akta_NoFail"));
			context.put("Akta_Catatan", hPapar.get("Akta_Catatan"));
			context.put("Akta_TarikhDaftar", hPapar.get("Akta_TarikhDaftar"));
			context.put("tag_Dokumen", hPapar.get("tagging"));
			context.put("id_tagdokumen", hPapar.get("idTagging"));
			context.put("Akta_Doc", hPapar.get("Akta_Doc"));
			
		}
	}
	///////////////
	public void showPenggal() throws Exception {
		myLogger.debug("** showPenggal ** :"+Penggal_IDPenggal);
		vPapar = akta.penggalPapar(Penggal_IDPenggal);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Penggal_IDPenggal",Penggal_IDPenggal);
			context.put("Penggal_NoPenggal", hPapar.get("Penggal_NoPenggal"));
			context.put("Penggal_TajukPenggal", hPapar.get("Penggal_TajukPenggal"));
		}
	}
	
	public void showPenggalLists() throws Exception {
		vPapar = akta.penggalList(Akta_IDAkta);
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
		vPapar = akta.bahagianPapar(Bahagian_IDBahagian);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Bahagian_IDBahagian",Bahagian_IDBahagian);
			context.put("Bahagian_NoBahagian", hPapar.get("Bahagian_NoBahagian"));
			context.put("Bahagian_TajukBahagian", hPapar.get("Bahagian_TajukBahagian"));
			context.put("Bahagian_Penggal", akta.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Akta_IDAkta, RO_General, ""));
		} else {
			context.put("Bahagian_Penggal", akta.getSOCPenggal("SOC_Penggal", "", Akta_IDAkta, RO_General, ""));
		}
	}
	
	public void showBahagianLists() throws Exception {
		vPapar = akta.bahagianList(Akta_IDAkta);
		context.put("List_CarianBahagian", vPapar);
	}
	/////////
	public void showBab() throws Exception {
		this.context.put("Bab_IDBab", Bab_IDBab);
		context.put("Bab_NoBab","");
		context.put("Bab_TajukBab","");
		vPapar = akta.babPapar(Bab_IDBab);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Bab_NoBab", hPapar.get("Bab_NoBab"));
			context.put("Bab_TajukBab", hPapar.get("Bab_TajukBab"));
			context.put("Bab_Penggal", akta.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Bab_Bahagian", akta.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(), Akta_IDAkta, RO_General, ""));
		} else {
			context.put("Bab_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Bab_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Akta_IDAkta, RO_General, ""));
		}

	}
	
	public void showBabLists() throws Exception {
		vPapar = akta.babList(Akta_IDAkta);
		context.put("List_CarianBab", vPapar);
	}
	
	public void clearBab() throws Exception {
			context.put("Bab_NoBab","");
			context.put("Bab_TajukBab","");
			context.put("Bab_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Bab_Bahagian", akta.getSOCBahagian("SOC_Bahagian",SOC_Bahagian, Akta_IDAkta, RO_General, ""));
	}
	
	/////
	public void showSeksyen() throws Exception {
		context.put("Seksyen_NoSeksyen","");
		context.put("Seksyen_TajukSeksyen","");
		context.put("Seksyen_Seksyen","");
		context.put("Seksyen_Proviso","");
		context.put("Seksyen_IDSeksyen",Seksyen_IDSeksyen);
		context.put("Seksyen_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("Seksyen_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
		context.put("Seksyen_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab,Akta_IDAkta, RO_General, ""));
		vPapar = akta.seksyenPapar(Seksyen_IDSeksyen);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Seksyen_NoSeksyen", hPapar.get("Seksyen_NoSeksyen"));
			context.put("Seksyen_TajukSeksyen", hPapar.get("Seksyen_TajukSeksyen"));
			context.put("Seksyen_Seksyen", hPapar.get("Seksyen_Seksyen"));
			context.put("Seksyen_Proviso", hPapar.get("Seksyen_Proviso"));
			context.put("Seksyen_Penggal", akta.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			//context.put("Seksyen_Bahagian", akta.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(), hPapar.get("Penggal_IDPenggal").toString(), RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
			context.put("Seksyen_Bahagian", akta.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(),Akta_IDAkta, RO_General, ""));
			context.put("Seksyen_Bab", akta.getSOCBab("SOC_Bab",(String)hPapar.get("Bab_IDBab"),Akta_IDAkta, RO_General, ""));
		} else {
			context.put("Seksyen_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Seksyen_Bahagian", akta.getSOCBahagian("SOC_Bahagian",SOC_Bahagian, Akta_IDAkta, RO_General, ""));
		}

	}
	
	public void showSeksyenLists() throws Exception {
		vPapar = akta.seksyenList(Akta_IDAkta);
		context.put("List_CarianSeksyen", vPapar);
	}
	///////////
	public void showSubSeksyen() throws Exception {
		context.put("SubSeksyen_NoSubSeksyen","");
		context.put("SubSeksyen_TajukSubSeksyen","");
		context.put("SubSeksyen_SubSeksyen","");
		context.put("SubSeksyen_Proviso","");
		context.put("SubSeksyen_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("SubSeksyen_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
		context.put("SubSeksyen_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
		context.put("SubSeksyen_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, Akta_IDAkta, RO_General, ""));
		vPapar = akta.subSeksyenPapar(SubSeksyen_IDSubSeksyen);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("SubSeksyen_NoSubSeksyen", hPapar.get("SubSeksyen_NoSubSeksyen"));
			context.put("SubSeksyen_TajukSubSeksyen", hPapar.get("SubSeksyen_TajukSubSeksyen"));
			context.put("SubSeksyen_SubSeksyen", hPapar.get("SubSeksyen_SubSeksyen"));
			context.put("SubSeksyen_Proviso", hPapar.get("SubSeksyen_Proviso"));
			context.put("SubSeksyen_Penggal", akta.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("SubSeksyen_Bahagian", akta.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
			context.put("SubSeksyen_Bab", akta.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(),Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBaB');\" "));
			context.put("SubSeksyen_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", hPapar.get("Seksyen_IDSeksyen").toString(),Akta_IDAkta, RO_General, ""));
		}

	}
	
	public void showSubSeksyenLists() throws Exception {
		vPapar = akta.subSeksyenList(Akta_IDAkta);
		context.put("List_CarianSubSeksyen", vPapar);
	}	
	
	//////////
	
	public void showPara() throws Exception {
		context.put("Para_Para","");
		context.put("Para_Jadual","");
		context.put("Para_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("Para_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
		//context.put("Para_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
		context.put("Para_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, Akta_IDAkta, RO_General, ""));
		context.put("Para_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
		context.put("Para_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", SOC_SubSeksyen, SOC_Seksyen, RO_General, ""));
		vPapar = akta.paraPapar(Para_IDPara);
		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("Para_Para", hPapar.get("Para_Para"));
			context.put("Para_Jadual", hPapar.get("Para_Jadual"));
			context.put("Para_Penggal", akta.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("Para_Bahagian", akta.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(),Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
			//context.put("Para_Bab", akta.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(),Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBaB');\" "));
			context.put("Para_Bab", akta.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(),Akta_IDAkta, RO_General, ""));
			context.put("Para_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", hPapar.get("Seksyen_IDSeksyen").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
			context.put("Para_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", hPapar.get("SubSeksyen_IDSubSeksyen").toString(), hPapar.get("Seksyen_IDSeksyen").toString(), RO_General, ""));
		}
	}
	
	public void showParaLists() throws Exception {
		myLogger.debug("AKTA ID="+Akta_IDAkta);
		vPapar = akta.paraList(Akta_IDAkta);
		context.put("List_CarianPara", vPapar);
	}
	
	/////////
	public void showSubPara() throws Exception {
		context.put("SubPara_SubPara","");
		context.put("SubPara_Jadual","");
		context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
		context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
		context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
		context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
		context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", SOC_SubSeksyen, SOC_Seksyen, RO_General, ""));
		context.put("SubPara_Para", akta.getSOCPara("SOC_Para", SOC_Para, Akta_IDAkta, RO_General, ""));
		vPapar = akta.subParaPapar(SubPara_IDSubPara);

		if (!vPapar.isEmpty()) {
			hPapar = (Hashtable) vPapar.get(0);
			context.put("SubPara_SubPara", hPapar.get("SubPara_SubPara"));
			context.put("SubPara_Jadual", hPapar.get("SubPara_Jadual"));
			context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", hPapar.get("Penggal_IDPenggal").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
			context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", hPapar.get("Bahagian_IDBahagian").toString(),Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
			context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", hPapar.get("Bab_IDBab").toString(), Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changeBaB');\" "));
			context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", hPapar.get("Seksyen_IDSeksyen").toString(), hPapar.get("Bab_IDBab").toString(), RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
			context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", hPapar.get("SubSeksyen_IDSubSeksyen").toString(), hPapar.get("Seksyen_IDSeksyen").toString(), RO_General, ""));
			context.put("SubPara_Para", akta.getSOCPara("SOC_Para", hPapar.get("Para_IDPara").toString(),Akta_IDAkta, RO_General, ""));

		}
	}
	
	public void showSubParaLists() throws Exception {
		vPapar = akta.subParaList(Akta_IDAkta);
		context.put("List_CarianSubPara", vPapar);
	}
	//
	public void doChanges(String command) throws Exception {
//		myLogger.debug("doChanges "+command);
//		if ("changePenggal".equalsIgnoreCase(command)) {
//			context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
//			context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", "", SOC_Penggal, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
//			context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", "", "", RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
//			context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", "", "", RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
//			context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", "", "", RO_General, " onchange=\"doChangeSOC('changeSubSeksyen');\" "));
//			context.put("SubPara_Para", akta.getSOCPara("SOC_Para", "", "", RO_General, ""));
//		}
//		if ("changeBahagian".equalsIgnoreCase(command)) {
//			context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
//			context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, SOC_Penggal, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
//			context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", "", SOC_Bahagian, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
//			context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", "", "", RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
//			context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", "", "", RO_General, " onchange=\"doChangeSOC('changeSubSeksyen');\" "));
//			context.put("SubPara_Para", akta.getSOCPara("SOC_Para", "", "", RO_General, ""));
//		}
//		if ("changeBab".equalsIgnoreCase(command)) {
//			context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
//			context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, SOC_Penggal, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
//			context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, SOC_Bahagian, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
//			context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", "", SOC_Bab, RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
//			context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", "", "", RO_General, " onchange=\"doChangeSOC('changeSubSeksyen');\" "));
//			context.put("SubPara_Para", akta.getSOCPara("SOC_Para", "", "", RO_General, ""));
//		}
//		if ("changeSeksyen".equalsIgnoreCase(command)) {
//			context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
//			context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, SOC_Penggal, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
//			context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, SOC_Bahagian, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
//			context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, SOC_Bab, RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
//			context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", "", SOC_Seksyen, RO_General, " onchange=\"doChangeSOC('changeSubSeksyen');\" "));
//			context.put("SubPara_Para", akta.getSOCPara("SOC_Para", "", "", RO_General, ""));
//		}
//		if ("changeSubSeksyen".equalsIgnoreCase(command)) {
//			context.put("SubPara_Penggal", akta.getSOCPenggal("SOC_Penggal", SOC_Penggal, Akta_IDAkta, RO_General, " onchange=\"doChangeSOC('changePenggal');\" "));
//			context.put("SubPara_Bahagian", akta.getSOCBahagian("SOC_Bahagian", SOC_Bahagian, SOC_Penggal, RO_General, " onchange=\"doChangeSOC('changeBahagian');\" "));
//			context.put("SubPara_Bab", akta.getSOCBab("SOC_Bab", SOC_Bab, SOC_Bahagian, RO_General, " onchange=\"doChangeSOC('changeBab');\" "));
//			context.put("SubPara_Seksyen", akta.getSOCSeksyen("SOC_Seksyen", SOC_Seksyen, SOC_Bab, RO_General, " onchange=\"doChangeSOC('changeSeksyen');\" "));
//			context.put("SubPara_SubSeksyen", akta.getSOCSubSeksyen("SOC_SubSeksyen", SOC_SubSeksyen, SOC_Seksyen, RO_General, " onchange=\"doChangeSOC('changeSubSeksyen');\" "));
//			context.put("SubPara_Para", akta.getSOCPara("SOC_Para", "", SOC_SubSeksyen, RO_General, ""));
//		}
		
		//JADUAL
		
	}
	
	public void clearFieldAkta() {
		
		context.put("Akta_NoAkta","");
		context.put("Akta_NamaAkta","");
		context.put("Akta_TarikhKuatkuasa","");
		context.put("Akta_TarikhMansuh","");
		context.put("Akta_NoWarta","");
		context.put("Akta_TarikhWarta","");
		if ("1".equalsIgnoreCase((String) hPapar.get("Akta_TarafKeselamatan"))) {
			context.put("Akta_AktaTerbuka", "checked=\"checked\"");
			context.put("Akta_AktaSulit", "");
		} else {
			context.put("Akta_AktaTerbuka", "");
			context.put("Akta_AktaSulit", "checked=\"checked\"");
		}
		context.put("Akta_NoAkta","");
		context.put("Akta_IDSeksyen","");
		context.put("Akta_NoFail","");
		context.put("Akta_Catatan","");
		context.put("Akta_TarikhDaftar","");
		context.put("Akta_Doc","");
	}
	
	///////////
	public void setViewMode() {
		action = "update";
		RO_General = "";
	}
	////////
	public void UploadFile(String idAkta) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		    	FileItem item = (FileItem)itr.next();
			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	saveBlob(idAkta,item);
			    }
		    }
		}
	}
	
	public void saveBlob(String idAkta,FileItem item) throws Exception {
		 Db db = null;
		 try {
			 db = new Db();
			 Connection con = db.getConnection();
			 con.setAutoCommit(false);
			 myLogger.debug("** item **"+item.getName()+" idAkta : "+idAkta);
			 PreparedStatement ps = con.prepareStatement("UPDATE tblpdtakta " +
			 		"SET Content=?,jenis_mime=? WHERE id_akta=?");
			 ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
			 ps.setString(2,item.getName());
			 ps.setString(3,idAkta);
			 ps.executeUpdate();
			 con.commit();
			 myLogger.debug("** MASUK UPLOAD 2 **");
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			      if (db != null) db.close();
		 }
	}
	
/////////// JADUAL
	
	public void UploadJadual(String idJadual) throws Exception {
		myLogger.debug("UploadJadual:"+idJadual);
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
			    	 myLogger.debug("uploading..."+item);
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
			 myLogger.debug(" ** nama ***"+item.getName());
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
		    myLogger.debug(name +"="+value);
		}
	}
	
	public void doCarian(HttpSession session) throws Exception{
		String tag = getParam("tag_dokumen");
		//vCarian = akta.aktaCarian(Cari_NoAkta, Cari_NamaAkta, Cari_TarikhKuatkuasa, Cari_Seksyen);
		vCarian = akta.aktaCarian(Cari_NoAkta, Cari_NamaAkta
				, Cari_TarikhKuatkuasa, Cari_Seksyen,tag);
		context.put("txtNoAkta", "");
		context.put("txtNamaAkta", "");
		context.put("txdTarikhKuatkuasa", "");
		context.put("", "");
		context.put("tag_Dokumen",tag);
		
		//context.put("List_CarianAkta", vCarian);
		setupPage(session,action,vCarian);	
		
	}
	///////
	public void showJadualLists() throws Exception {
		vPapar = akta.JadualList(Akta_IDAkta);
		context.put("List_CarianJadual", vPapar);
	}
	
	
	public void showJadual() throws Exception {
		context.put("IDJadual",IDJadual);
		context.put("NamaJadual","");
		context.put("TajukJadual","");
		context.put("CatatanJadual","");
		hPapar = akta.JadualPapar(IDJadual);
		if (hPapar != null) {
			context.put("NamaJadual", hPapar.get("NamaJadual"));
			context.put("TajukJadual", hPapar.get("TajukJadual"));
			context.put("CatatanJadual", hPapar.get("CatatanJadual"));
		}
	}
	
	////////
	
	public void showJadualLampiran() throws Exception {
		context.put("IDJadualLampiran", IDJadualLampiran);
		context.put("SOC_Jadual", akta.getSOCJadual("SOC_Jadual", SOC_Jadual, Akta_IDAkta, RO_General, ""));
		context.put("txtCatatan","");
		context.put("txtTajuk","");
		hPapar = akta.JadualLampiranPapar(IDJadualLampiran);
		//if (!hPapar.isEmpty()) {
		if (hPapar != null) {
			context.put("SOC_Jadual", akta.getSOCJadual("SOC_Jadual",(String)hPapar.get("IDJadual"), Akta_IDAkta, RO_General, ""));
			context.put("txtCatatan",(String)hPapar.get("Catatan"));
			context.put("txtTajuk",(String)hPapar.get("Tajuk"));
		}

	}	
	
	public void showJadualLampiranLists() throws Exception {
		vPapar = akta.JadualLampiranList(Akta_IDAkta);
		context.put("List_CarianJadualLampiran", vPapar);
	}
	
	public void showJadualLampiranFail() throws Exception {
		//Show Lampiran fail 
		this.context.put("IDJadualLampiran", IDJadualLampiran);
		vPapar = akta.JadualLampiranFailList(IDJadualLampiran);
		context.put("List_LampiranFail", vPapar);
	}
	
	
}
