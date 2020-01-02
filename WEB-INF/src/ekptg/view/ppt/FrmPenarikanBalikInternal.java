package ekptg.view.ppt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.PPTHeader;
import ekptg.view.ppt.email.Email_PenarikanBalik;




public class FrmPenarikanBalikInternal extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(FrmPembatalanInternal.class);
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	Email_PenarikanBalik email_penarikan = new Email_PenarikanBalik();
	PPTHeader header = new PPTHeader();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();		
		String vm = ""; 
		
		//get user login detail
    	//String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	//get nama pengarah dan id pengarah
    	nameAndId(userIdNeg);
    	String id_pengarah = nameAndId(userIdNeg);
		
		
		String flag_MyInfoPPT = getParam("flag_MyInfoPPT");
		this.context.put("flag_MyInfoPPT", flag_MyInfoPPT);
		String flag_UtilitiKemaskiniFail = getParam("flag_UtilitiKemaskiniFail");
		this.context.put("flag_UtilitiKemaskiniFail", flag_UtilitiKemaskiniFail);		
		
		String main_command = getParam("command");
		String sub_command = getParam("sub_command");
		String subminor_command = getParam("subminor_command");
		myLogger.info("main_command :"+main_command+";sub_command :"+sub_command);
		
		String paging_action = getParam("action");
		String location = getParam("location");
		String point = getParam("point");
		String buka_cari = getParam("buka_cari");
		String jenis_permohonan = "4";
		String jenis_button = "";	
		
		String nama_kementerian = "";
    	String tarikh_permohonan = "";
    	String tujuan = "";
		String id_status = "";
		String id_fail = "";
		String negeriMMK = "";
		String flagSegera = "";
		String no_fail = "";
		String projek_daerah = "";
		String no_rujukan_ptg = "";
		String id_projekNegeri = "";
		
		String idpermohonan = getParam("id_permohonan");
		Vector dataHeader = null;
		if(!idpermohonan.equals(""))
		{
    	header.setDataHeader(idpermohonan);
		dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			id_fail = (String)dh.get("id_fail");
			negeriMMK = (String)dh.get("id_projekNegeri");
			flagSegera = dh.get("flag_segera").toString();
			no_fail = (String)dh.get("no_fail");
			projek_daerah = (String)dh.get("projek_daerah");
			no_rujukan_ptg = (String)dh.get("no_rujukan_ptg");
			tujuan = (String)dh.get("tujuan");
			tarikh_permohonan = (String)dh.get("tarikh_permohonan");
			nama_kementerian = (String)dh.get("nama_kementerian");
			id_projekNegeri = (String)dh.get("id_projekNegeri");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
		}
		
		Vector senarai_hakmilik_batal_penarikan_status = null;
		Vector senarai_hakmilik_penarikan_all = null;
		Vector senarai_pihak_penting_pampasan_perintah_award = null;
		Vector senarai_laporan_tanah = null;
		Vector maklumat_siasatan_history = null;
		Vector maklumat_siasatanX = null;
		Vector senarai_perintah_award = null;
		Vector tempat_tampal = null;
	    Vector maklumat_hakmilik_mmk_pb_siasatan = null;
		Vector senarai_pembatalan = null;	
		Vector listdepan = null;
		Vector list_kementerian = null;
		Vector list_urusan = null;
		Vector list_status = null;
		Vector header = null;
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
		Vector maklumat_subpampasan_pb = null;
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
		Vector senarai_submmk = null;
		
		
		this.context.put("buka_cari", buka_cari);
		this.context.put("listdepan", listdepan);
		this.context.put("listdepan_size", 0);
		this.context.put("location", location);
		this.context.put("point", point);
		this.context.put("list_default", "");
		this.context.put("jenis_permohonan", jenis_permohonan);		
		
		
		this.context.put("senarai_hakmilik_batal_penarikan_status", "");
		this.context.put("senarai_hakmilik_penarikan_all", "");
		this.context.put("senarai_pihak_penting_pampasan_perintah_award", "");
		this.context.put("senarai_laporan_tanah", "");
		this.context.put("maklumat_siasatan_history", "");
		this.context.put("senarai_submmk", "");
		this.context.put("maklumat_siasatanX", "");
		this.context.put("list_subsiasatan", "");
		this.context.put("nowarta_lot_mmk", "");
		this.context.put("maklumat_hakmilik_mmk_pemilik_siasatan", "");
		this.context.put("senarai_pihak_penting_pampasan_perintah", "");
		this.context.put("senarai_lot_mmk", "");
		this.context.put("tempat_tampal", "");
		this.context.put("maklumat_hakmilik_mmk_pb_siasatan", "");		
		this.context.put("nama_user", "");
		this.context.put("maklumat_am_tanah_permohonan","");
		this.context.put("maklumat_hakmilik_mmk_pb","");
		this.context.put("maklumat_hakmilik_mmk_ekar","");
		this.context.put("maklumat_hakmilik_mmk_hektar","");
		this.context.put("maklumat_hakmilik_mmk_lot","");
		this.context.put("maklumat_bayaran_semua","");
		this.context.put("maklumat_pampasan_pb","");
		this.context.put("maklumat_subpampasan_pb","");
		this.context.put("tarikh_akhir_tampal","");
		this.context.put("senarai_hakmilik_batal_penarikan_maklumat","");
		this.context.put("list_jenisluas","");
		this.context.put("list_kehadiran_th","");
		this.context.put("list_jenispb","");
		this.context.put("list_bank","");
		this.context.put("list_jenisnopb","");
		this.context.put("maklumat_kehadiran","");
		this.context.put("maklumat_siasatan_pb","");
		this.context.put("list_pegawai","");
		this.context.put("list_check_kehadiran","");
		this.context.put("list_siasatan_penarikan","");
		this.context.put("list_bandar_all","");
		this.context.put("list_kehadiran","");
		this.context.put("maklumat_siasatan","");
		this.context.put("list_siasatan","");
		this.context.put("list_negeri","");
		this.context.put("list_bandar","");
		this.context.put("senarai_pihak_penting_bangunan","");
		this.context.put("maklumat_hakmilik","");
		this.context.put("senarai_bangunan","");
		this.context.put("maklumat_am_tanah","");
		this.context.put("maklumat_details_bayaran","");
		this.context.put("maklumat_bayaran_bp","");
		this.context.put("header_pampasan_pb","");
		this.context.put("maklumat_hakmilik_pampasan","");
		this.context.put("list_mukim_pampasan","");
		this.context.put("senarai_pihak_penting_pampasan","");
		this.context.put("maklumat_warta","");
		this.context.put("maklumat_keputusan","");
		this.context.put("maklumat_tambahan","");
		this.context.put("maklumat_penyediaan","");
		this.context.put("maklumat_semakan_pegawai","");
		this.context.put("maklumat_semakan","");
		this.context.put("header","");
		this.context.put("maklumat_pembatalan","");
		this.context.put("senarai_hakmilik","");
		this.context.put("senarai_pihak_penting","");
		this.context.put("listDokumen","");
		this.context.put("listDokumen_size","");
		this.context.put("id_permohonan","");
		this.context.put("id_pembatalan","");
		this.context.put("readmode","");		
		this.context.put("num_files","");
		this.context.put("screen_name","");
		this.context.put("view_details_dokumen","");		
		this.context.put("list_kementerian","");
		this.context.put("list_urusan","");
		this.context.put("list_status","");
		this.context.put("listdepan","");
		this.context.put("listdepan_size","");
		this.context.put("first_masuk","");	
		this.context.put("display_error_message","yes");
		this.context.put("senarai_hakmilik_overall","");
		this.context.put("hakmilikoverall","");
		this.context.put("hakmilik_belumbatal","");
		this.context.put("hakmilik_sudahbatal","");
		this.context.put("tajuk_header","");		
		this.context.put("txtNamaPB", "");
		this.context.put("txtNoPB", "");
		this.context.put("txtNoKPLama", "");  	
		this.context.put("txtNoLot", "");	
		this.context.put("socMukim", ""); 
		this.context.put("list_mukim_pampasan", ""); 
		this.context.put("screen_kemasukan", ""); 
		this.context.put("id_siasatan","");		
		this.context.put("senarai_pembatalan","");
		this.context.put("tambah_kehadiran_wakil", "");
		this.context.put("tambah_kehadiran_negeri_wakil", "");
		context.put("typeList","internal");
		
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
			if(checkRegPopup("ekptg.view.ppt.SkrinPopupUploadDokumen",dbx)==0)
	    	{
	    		//reg class
	    		insertPopupReg("ekptg.view.ppt.SkrinPopupUploadDokumen","Skrin Senarai Dokumen PPT", "EKPTG - PPT",dbx);
	    	}	    	
	    	checkFieldWujud("TBLPPTDOKUMEN","JENIS_DOKUMEN","varchar2(50)",dbx);
	    	checkFieldWujud("TBLPPTDOKUMEN","ID_HAKMILIK","NUMBER",dbx);
		} finally {
			if (dbx != null)
				dbx.close();
		}
		*/
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		String bolehsimpan = "";
		String readmode = "";
		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		myLogger.info("BOLEH SIMPAN :"+bolehsimpan);
		
		if ("senarai".equals(main_command)){
			if ("kembali_senarai_depan".equals(sub_command)){
				vm = "app/ppt/frmPembatalanCarian.jsp";	
	    	}
			else if ("papar".equals(sub_command)){
				this.context.put("first_masuk","yes");	
				String id_permohonan = getParam("id_permohonan");
				header = logic.content_header(id_permohonan);
				this.context.put("header",header);				
				
				senarai_pembatalan = logic.senarai_penarikan(id_permohonan);
				this.context.put("senarai_pembatalan", senarai_pembatalan);
				
			
				vm = "app/ppt/frmPenarikanBalikSenarai.jsp";	
	    	}
			
			jenis_button = "1";
			senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
			this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
			this.context.put("tajuk_header","PENARIKAN BALIK PENGAMBILAN TANAH");
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
    	}
		
		else if ("PembatalanAmbilTanahPT".equals(main_command)){						
			
			if ("kembali_senarai_depan".equals(sub_command)){
				vm = "app/ppt/frmPembatalanCarian.jsp";	
	    	}
			else if ("view_PembatalanAmbilTanahPT".equals(sub_command)){
				String id_permohonan = getParam("id_permohonan");
				header = logic.content_header(id_permohonan);
				this.context.put("header",header);				
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				myLogger.info("maklumat_pembatalan size :"+maklumat_pembatalan.size());
				
				if(maklumat_pembatalan.size()==0)
				{
					this.context.put("readmode", "edit");
					this.context.put("display_error_message","no");	
				}else
				{
					this.context.put("readmode", "view");
				}
				
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(id_permohonan,getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);
				
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(id_permohonan,getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				
				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);	    		
	    		senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
	    		
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	} 
			else if ("hapus_dokumen".equals(sub_command)){
				String[] ids1 = this.request.getParameterValues("ids1");
				if (ids1 != null) {
					for (int i = 0; i < ids1.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.deleteDokumen(ids1[i]);
							}
						}
					}
				this.context.put("readmode", getParam("readmode"));	
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				    		
	    		senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
				
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);
				
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
								
				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			else if ("pilih_pembatalan".equals(sub_command)){
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);	
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			else if ("kemaskini".equals(sub_command)){
				this.context.put("readmode", "edit");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);	
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			else if ("hantar".equals(sub_command)){
				updatePembatalanOnline3(session,idpermohonan);
				this.context.put("readmode", "view");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);	
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
	    		
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			
			else if ("tolak".equals(sub_command)){
				updatePenarikanOnline3Tolak(session);
				this.context.put("readmode", "view");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);	
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
	    		
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			
			else if ("hapus".equals(sub_command)){
				
				
				logic.delete_subsiasatan_byPenarikan(getParam("id_pembatalan"));			
				
				String[] lot_tarik = this.request.getParameterValues("lot_tarikX");
				String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarikX");
				String[] lot_ambil = this.request.getParameterValues("lot_ambilX");
				String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asalX");
				String[] luas_lot = this.request.getParameterValues("luas_lotX");	
				String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luasX");
				
				
				myLogger.info("ID_LOT_TARIK:"+id_lot_tarik);
				
				if (id_lot_tarik != null ) 
				{
				for (int t = 0; t < id_lot_tarik.length; t++) 
				{
					
					
						if (bolehsimpan.equals("yes")) 
						{
							myLogger.info("lot_tarik:"+lot_tarik[t]);
							myLogger.info("id_lot_tarik:"+id_lot_tarik[t]);
							myLogger.info("lot_ambil:"+lot_ambil[t]);
							myLogger.info("lot_ambil_asal:"+lot_ambil_asal[t]);
							myLogger.info("luas_lot:"+luas_lot[t]);
							myLogger.info("id_hakmilik_luas:"+id_hakmilik_luas[t]);
							
						logic.hapus_update_pilih_penarikan(id_lot_tarik[t],id_hakmilik_luas[t],lot_tarik[t],lot_ambil[t],lot_ambil_asal[t],luas_lot[t],(String) session.getAttribute("_ekptg_user_id"),getParam("id_pembatalan"));
						logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
								(String) session.getAttribute("_ekptg_user_id"),"16102710","hapus",getParam("id_fail"));
					
						}									
												
				}
				}	
				if (id_lot_tarik != null ) 
				{
				for (int t = 0; t < id_lot_tarik.length; t++) 
				{
					
						if (bolehsimpan.equals("yes")) 
						{
						logic.hapus_pilih_penarikan(id_lot_tarik[t]);
						}									
												
				}
				}
				logic.deletePenarikanInternal(getParam("id_pembatalan"));
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
				
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				this.context.put("senarai_hakmilik",senarai_hakmilik);				
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				
				this.context.put("readmode", "edit");
				this.context.put("display_error_message","no");	
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			else if ("simpan".equals(sub_command)){
				String id_permohonan = getParam("id_permohonan");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				
				
				String id_pembatalan = "";
				if(maklumat_pembatalan.size()==0)
				{
					if(bolehsimpan=="yes")
					{
					//	addPembatalan(session);
						long id_pembatalan_long = DB.getNextID("TBLPPTPENARIKANBALIK_SEQ");
						id_pembatalan = id_pembatalan_long+"";
						Hashtable h = new Hashtable();
						h.put("id_pembatalan", id_pembatalan);
						h.put("txtNoPembatalan", getParam("txtNoPembatalan"));
						h.put("txdTarikhPembatalan", getParam("txdTarikhPembatalan"));
						h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
						h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));
						h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
						h.put("sorJenisPembatalan", getParam("sorJenisPembatalan"));
						h.put("txtSebabPembatalan", getParam("txtSebabPembatalan"));
						h.put("id_permohonan", getParam("id_permohonan"));
						h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
						logic.addPenarikan(h);	
					}
					
					updateStatusPenarikan(session);
					
				}else
				{
					updatePembatalan(session);
					id_pembatalan = getParam("id_pembatalan");
				}
				this.context.put("readmode", "view");				
						
				String[] ids = this.request.getParameterValues("ids");
				String[] lot_tarik = this.request.getParameterValues("lot_tarik");
				String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarik");
				String[] lot_ambil = this.request.getParameterValues("lot_ambil");
				String[] luas_lot = this.request.getParameterValues("luas_lot");	
				String[] id_kat = this.request.getParameterValues("id_kat");	
				myLogger.info("LOT TARIK ARRAY:"+id_lot_tarik);	
				
				
				
				if (ids != null && id_lot_tarik != null ) 
				{
					for (int i = 0; i < ids.length; i++) 
					{					
							for (int t = 0; t < id_lot_tarik.length; t++) 
							{
								myLogger.info("I:"+ids[i]+";LT:"+lot_tarik[t]);	
								if(id_lot_tarik[t].equals(ids[i]))
								{
									if (bolehsimpan.equals("yes")) 
									{
									logic.pilih_penarikan(id_kat[t],id_lot_tarik[t],lot_tarik[t],lot_ambil[t],luas_lot[t],(String) session.getAttribute("_ekptg_user_id"),id_pembatalan);
								
									logic.update_status_hakmilik(ids[i], getParam("id_permohonan"),
											(String) session.getAttribute("_ekptg_user_id"),"16102710","add",getParam("id_fail"));
									
									
									}
									
								}								
							}
						
						
					}
				}
		
				if(id_pembatalan.equals("") || id_pembatalan.equals(null))
				{
					this.context.put("first_masuk","yes");	
					id_permohonan = getParam("id_permohonan");
					header = logic.content_header(id_permohonan);
					this.context.put("header",header);			
					senarai_pembatalan = logic.senarai_penarikan(id_permohonan);
					this.context.put("senarai_pembatalan", senarai_pembatalan);					
					vm = "app/ppt/frmPenarikanBalikSenarai.jsp";					
				}
				else
				{
					maklumat_pembatalan = logic.maklumat_penarikan(id_pembatalan);
					this.context.put("maklumat_pembatalan", maklumat_pembatalan);
					senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),id_pembatalan);
					this.context.put("senarai_hakmilik",senarai_hakmilik);
					this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
					senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
					this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
					senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
		    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
					
					senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(id_pembatalan);
		    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
					vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
				}
	    	}
			else if ("batal".equals(sub_command)){
				this.context.put("readmode", "edit");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);	
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				this.context.put("senarai_hakmilik",senarai_hakmilik);
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			else if ("batalkan_lot".equals(sub_command)){
			
				String[] ids = this.request.getParameterValues("ids");
				String[] lot_tarik = this.request.getParameterValues("lot_tarik");
				if (ids != null) {
					for (int i = 0; i < ids.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								
								if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
								{
								logic.batalkan_penarikan(ids[i],lot_tarik[i],(String) session.getAttribute("_ekptg_user_id"),getParam("id_pembatalan"));
								}
								
							}
						}
					}
				this.context.put("readmode", getParam("readmode"));	
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
	    		senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
	    		senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
	    		this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				this.context.put("senarai_hakmilik",senarai_hakmilik);
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			
			String id_permohonan = getParam("id_permohonan");
			header = logic.content_header(id_permohonan);
			this.context.put("header",header);
			
			//senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
			this.context.put("senarai_pihak_penting",senarai_pihak_penting);	
			
			if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
			{
			//String id_pembatalan = getParam("id_pembatalan");			
     		//listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		//context.put("listDokumen", listDokumen);
    		//context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			//context.put("listDokumen", "");
			//context.put("listDokumen_size", 0);
			}
			context.put("id_permohonan",getParam("id_permohonan"));
    		context.put("id_pembatalan",getParam("id_pembatalan"));
    		
    		
    		senarai_hakmilik_overall = logic.senarai_hakmilik_overall(id_permohonan);
			this.context.put("senarai_hakmilik_overall",senarai_hakmilik_overall);
			this.context.put("hakmilikoverall",senarai_hakmilik_overall.size());
			
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
			jenis_button = "1";
			this.context.put("tajuk_header","PENARIKAN BALIK PENGAMBILAN TANAH");
			list_jenisluas = logic.list_jenisluas();
			this.context.put("list_jenisluas", list_jenisluas);			
    	}
		
		else if ("PembatalanAmbilTanahLotUnit".equals(main_command)){			
			if ("kembali_senarai_depan".equals(sub_command)){
				vm = "app/ppt/frmPembatalanCarian.jsp";	
	    	}
			else if ("tolak".equals(sub_command)){
				updatePenarikanOnline3Tolak(session);
				this.context.put("readmode", "view");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik = logic.senarai_hakmilik_penarikan(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik",senarai_hakmilik);	
				this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
				senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
				this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);				
				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
	    		
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";	
	    	}
			else if ("view_PembatalanAmbilTanahLotUnit".equals(sub_command)){
				String id_permohonan = getParam("id_permohonan");
				header = logic.content_header(id_permohonan);
				this.context.put("header",header);				
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
	    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				
				myLogger.info("maklumat_pembatalan size :"+maklumat_pembatalan.size());
				if(maklumat_pembatalan.size()==0)
				{
					this.context.put("readmode", "edit");
					this.context.put("display_error_message","no");	
				}else
				{
					this.context.put("readmode", "view");
				}
			
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	} 
			else if ("hapus_dokumen".equals(sub_command)){
				String[] ids1 = this.request.getParameterValues("ids1");
				if (ids1 != null) {
					for (int i = 0; i < ids1.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.deleteDokumen(ids1[i]);
							}
						}
					}
				this.context.put("readmode", getParam("readmode"));
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			else if ("pilih_pembatalan".equals(sub_command)){
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			else if ("kemaskini".equals(sub_command)){
				this.context.put("readmode", "edit");
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			else if ("hantar".equals(sub_command)){
				updatePembatalanOnline3(session,idpermohonan);
				this.context.put("readmode", "view");
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			
			
			
			else if ("hapus".equals(sub_command)){
				logic.delete_subsiasatan_byPenarikan(getParam("id_pembatalan"));
			
				String[] lot_tarik = this.request.getParameterValues("lot_tarik");
				String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarik");
				String[] lot_ambil = this.request.getParameterValues("lot_ambil");
				String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asal");
				String[] luas_lot = this.request.getParameterValues("luas_lot");	
				String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luas");
				
				myLogger.info("ID_LOT_TARIK:"+id_lot_tarik);
				
				if (id_lot_tarik != null ) 
				{
				for (int t = 0; t < id_lot_tarik.length; t++) 
				{
					
						if (bolehsimpan.equals("yes")) 
						{
					    logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
									(String) session.getAttribute("_ekptg_user_id"),"16102710","hapus",getParam("id_fail"));
						
						logic.hapus_update_pilih_penarikan(id_lot_tarik[t],id_hakmilik_luas[t],lot_tarik[t],lot_ambil[t],lot_ambil_asal[t],luas_lot[t],(String) session.getAttribute("_ekptg_user_id"),getParam("id_pembatalan"));
						}									
												
				}
				}	
				if (id_lot_tarik != null ) 
				{
				for (int t = 0; t < id_lot_tarik.length; t++) 
				{
					
						if (bolehsimpan.equals("yes")) 
						{
						logic.hapus_pilih_penarikan(id_lot_tarik[t]);
						}									
												
				}
				}
				logic.deletePenarikanInternal(getParam("id_pembatalan"));
				this.context.put("readmode", "edit");
				this.context.put("display_error_message","no");	
				vm = "app/ppt/frmPenarikanInfoSenaraiLot.jsp";		
	    	}
			else if ("simpan".equals(sub_command)){
				String id_permohonan = getParam("id_permohonan");
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				if(maklumat_pembatalan.size()==0)
				{
					if(bolehsimpan=="yes")
					{
						//addPembatalan(session);
					}
					
				}else
				{
					updatePembatalan(session);
				}
				this.context.put("readmode", "view");
				
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));			
				Hashtable h = (Hashtable) maklumat_pembatalan.get(0);			
			
			
				String[] ids = this.request.getParameterValues("ids");
				String[] lot_tarik = this.request.getParameterValues("lot_tarik");
				String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarik");
				String[] lot_ambil = this.request.getParameterValues("lot_ambil");
				String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asal");
				String[] luas_lot = this.request.getParameterValues("luas_lot");	
				String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luas");
				String[] id_kat = this.request.getParameterValues("id_kat");
				myLogger.info("LOT TARIK ARRAY:"+id_lot_tarik);	
				
				if (id_lot_tarik != null ) 
				{
				for (int t = 0; t < id_lot_tarik.length; t++) 
				{
					
						if (bolehsimpan.equals("yes") && ids != null) 
						{
						for (int i = 0; i < ids.length; i++) 
						{						
					    if(ids[i].equals(id_lot_tarik[t]))
					    {
						logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
						(String) session.getAttribute("_ekptg_user_id"),"16102710","hapus",getParam("id_fail"));
					    }
						}
						logic.update_pilih_penarikan(id_kat[t],id_lot_tarik[t],id_hakmilik_luas[t],lot_tarik[t],lot_ambil[t],lot_ambil_asal[t],luas_lot[t],(String) session.getAttribute("_ekptg_user_id"),getParam("id_pembatalan"));
						}									
												
				}
				}
				
				if (ids != null ) {
					for (int i = 0; i < ids.length; i++) {
						
							if (bolehsimpan.equals("yes")) 
							{
								if (bolehsimpan.equals("yes")) 
								{
								logic.hapus_pilih_penarikan(ids[i]);
								}
							}
						}
					}
				
				
			
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			else if ("batal".equals(sub_command)){
				this.context.put("readmode", "edit");
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			
			String id_permohonan = getParam("id_permohonan");
			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
			header = logic.content_header(id_permohonan);
			this.context.put("header",header);	
			senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
			//senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
			this.context.put("senarai_pihak_penting",senarai_pihak_penting);
			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    					
			if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
			{
			//String id_pembatalan = getParam("id_pembatalan");			
     		//listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		//context.put("listDokumen", listDokumen);
    		//context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			//context.put("listDokumen", "");
			//context.put("listDokumen_size", 0);
			}
			context.put("id_permohonan",getParam("id_permohonan"));
    		context.put("id_pembatalan",getParam("id_pembatalan"));
    		
    		senarai_hakmilik = logic.senarai_hakmilik_penarikan(id_permohonan,getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik",senarai_hakmilik);			
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
  			senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
			
			senarai_hakmilik_overall = logic.senarai_hakmilik_overall(id_permohonan);
			this.context.put("senarai_hakmilik_overall",senarai_hakmilik_overall);
			this.context.put("hakmilikoverall",senarai_hakmilik_overall.size());
			this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
			this.context.put("hakmilik_sudahbatal",senarai_hakmilik_batal.size());
			jenis_button = "1";
			this.context.put("tajuk_header","PENARIKAN BALIK PENGAMBILAN TANAH");
			list_jenisluas = logic.list_jenisluas();
			this.context.put("list_jenisluas", list_jenisluas);
			
    	}
		
		
		else if ("Dokumen".equals(main_command)){
			
			if ("tambah_dokumen".equals(sub_command)){	            
	            context.put("num_files", 5);
	            context.put("screen_name",getParam("screen_name"));	
	            this.context.put("readmode", "edit");
	            context.put("view_details_dokumen","");
	            this.context.put("display_error_message","no");	
	           	vm = "app/ppt/frmKJPDaftarDocTambah.jsp";	
	    	}
			else if ("kemaskini_dokumen".equals(sub_command)){
				String id_dokumen = getParam("id_dokumen");
				view_details_dokumen = logic.view_details_dokumen(id_dokumen);				
				context.put("view_details_dokumen",view_details_dokumen);
				this.context.put("readmode", "edit");
				vm = "app/ppt/frmKJPDaftarDocTambah.jsp";	
	    	}
			else if ("batal_dokumen".equals(sub_command)){
				String id_dokumen = getParam("id_dokumen");
				view_details_dokumen = logic.view_details_dokumen(id_dokumen);				
				context.put("view_details_dokumen",view_details_dokumen);
				this.context.put("readmode", "edit");
				vm = "app/ppt/frmKJPDaftarDocTambah.jsp";	
	    	}
			else if ("view_Dokumen_Details".equals(sub_command)){
				String id_dokumen = getParam("id_dokumen");
				view_details_dokumen = logic.view_details_dokumen(id_dokumen);				
				context.put("view_details_dokumen",view_details_dokumen);
				this.context.put("readmode", "view");
				vm = "app/ppt/frmKJPDaftarDocTambah.jsp";	
	    	}			
			else if ("hapus_dokumen".equals(sub_command)){
				String[] ids1 = this.request.getParameterValues("ids1");
				if (ids1 != null) {
					for (int i = 0; i < ids1.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.deleteDokumen(ids1[i]);
							}
						}
					}
				this.context.put("readmode", getParam("readmode"));
				
				String id_dokumen = getParam("id_dokumen");
				view_details_dokumen = logic.view_details_dokumen(id_dokumen);
				
				if(view_details_dokumen.size()>0)
				{
				context.put("view_details_dokumen",view_details_dokumen);
				this.context.put("readmode", "view");
				}
				else
				{
			    context.put("view_details_dokumen","");
				this.context.put("readmode", "edit");	
				}
				
				
				vm = "app/ppt/frmKJPDaftarDocTambah.jsp";	
	    	}			
			else if ("hapus_dokumen_papar".equals(sub_command)){				
				    logic.deleteDokumen(getParam("id_dokumen"));
				    context.put("num_files", 5);
		            context.put("screen_name",getParam("screen_name"));	
		            this.context.put("readmode", "edit");
		            context.put("view_details_dokumen","");
		            this.context.put("display_error_message","no");	
				vm = "app/ppt/frmKJPDaftarDocTambah.jsp";	
	    	}			
			else if ("upload_dokumen".equals(sub_command)){				
				String id_dokumen = getParam("id_dokumen");				
				if(id_dokumen == "")
				{							
					uploadFiles();						
			    this.context.put("readmode", "edit");
			    context.put("id_permohonan",getParam("id_permohonan"));
	    		context.put("id_pembatalan",getParam("id_pembatalan"));
				}
				else
				{
					updateFiles(session);
					view_details_dokumen = logic.view_details_dokumen(id_dokumen);				
					context.put("view_details_dokumen",view_details_dokumen);
					this.context.put("readmode", "view");
					context.put("id_permohonan",getParam("id_permohonan"));
		    		context.put("id_pembatalan",getParam("id_pembatalan"));
				}				
				
				vm = "app/ppt/frmKJPDaftarDocTambah.jsp";				
			}			
    		context.put("id_permohonan",getParam("id_permohonan"));
    		context.put("id_pembatalan",getParam("id_pembatalan"));
    		context.put("screen_name",getParam("screen_name"));
    		
    		if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
			{
			//String id_pembatalan = getParam("id_pembatalan");			
     		//listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		//context.put("listDokumen", listDokumen);
    		//context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			//context.put("listDokumen", "");
			//context.put("listDokumen_size", 0);
			}
    		
    		String id_permohonan = getParam("id_permohonan");
			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
			header = logic.content_header(id_permohonan);
			this.context.put("header",header);	
			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
			//senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
			this.context.put("senarai_pihak_penting",senarai_pihak_penting);			
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
			context.put("num_files", 5);
			jenis_button = "1";
		}
		
		else if ("Maklumat_Tambahan".equals(main_command)){
			
			if ("view_maklumat_tambahan".equals(sub_command)){	
	            context.put("screen_name",getParam("screen_name"));	          
	           	
	    	}
					
    		context.put("id_permohonan",getParam("id_permohonan"));
    		context.put("id_pembatalan",getParam("id_pembatalan"));
    		context.put("screen_name",getParam("screen_name"));
    		String id_permohonan = getParam("id_permohonan");
			maklumat_tambahan = logic.maklumat_tambahan(id_permohonan);
			this.context.put("maklumat_tambahan", maklumat_tambahan);
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
			vm = "app/ppt/frmPenarikanInfoCetak.jsp";	
			
		}
		
		else if ("LaporanTanah".equals(main_command)){			
			if ("view".equals(sub_command)){
				String id_permohonan = getParam("id_permohonan");
				header = logic.content_header(id_permohonan);
				this.context.put("header",header);				
				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
	    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
				myLogger.info("maklumat_pembatalan size :"+maklumat_pembatalan.size());
				if(maklumat_pembatalan.size()==0)
				{
					this.context.put("readmode", "edit");
					this.context.put("display_error_message","no");	
				}else
				{
					this.context.put("readmode", "view");
				}
			
				vm = "app/ppt/frmPenarikanSenaraiLaporan.jsp";	
	    	} 
			
			String id_permohonan = getParam("id_permohonan");
			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		header = logic.content_header(id_permohonan);
			this.context.put("header",header);	
			senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
			//senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
			this.context.put("senarai_pihak_penting",senarai_pihak_penting);
			
			if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
			{
			//String id_pembatalan = getParam("id_pembatalan");			
     		//listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		//context.put("listDokumen", listDokumen);
    		//context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			//context.put("listDokumen", "");
			//context.put("listDokumen_size", 0);
			}
			context.put("id_permohonan",getParam("id_permohonan"));
    		context.put("id_pembatalan",getParam("id_pembatalan"));
    		
    		senarai_hakmilik = logic.senarai_hakmilik_penarikan(id_permohonan,getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik",senarai_hakmilik);	
			senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
			
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
			senarai_hakmilik_overall = logic.senarai_hakmilik_overall(id_permohonan);
			this.context.put("senarai_hakmilik_overall",senarai_hakmilik_overall);
			this.context.put("hakmilikoverall",senarai_hakmilik_overall.size());
			this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
			this.context.put("hakmilik_sudahbatal",senarai_hakmilik_batal.size());
			jenis_button = "2";
			this.context.put("tajuk_header","LAPORAN TANAH");
			list_jenisluas = logic.list_jenisluas();
			this.context.put("list_jenisluas", list_jenisluas);
			
			
			
    	}
		
		
		
         else if ("Laporan_Tanah".equals(main_command)){ 
        	maklumat_am_tanah = logic.maklumat_am_tanah(getParam("id_pembatalan"),getParam("id_hakmilik"));
        	senarai_laporan_tanah = logic.senarai_laporan_tanah(getParam("id_hakmilik"));
        	
 			if ("Maklumat_Am".equals(sub_command))
			{		                 
				if ("View".equals(subminor_command))
				{
					if(maklumat_am_tanah.size()>0)
					{
					Hashtable h = (Hashtable) maklumat_am_tanah.get(0);
					if(h.get("TARIKH_KEMASUKAN").toString().equals("") && h.get("PERIHAL_SYIT").toString().equals("") && h.get("TARIKH_PEMERIKSAAN").toString().equals("")
            				&& h.get("FLAG_JENIS_TANAH").toString().equals("") && h.get("NO_GAZET").toString().equals("") && h.get("FLAG_DLM_SIMPANAN").toString().equals("")
            				&& h.get("FLAG_LUAR_SIMPANAN").toString().equals("") && h.get("FLAG_LUAR_MAJLIS").toString().equals("") && h.get("FLAG_DLM_MAJLIS").toString().equals("")
            				)
            		{
            			this.context.put("readmode", "edit");		
            		}else
            		{
            			 this.context.put("readmode", "view");			
            		}						
					}
					else
					{
					 this.context.put("readmode", "edit");	
					}
				}
				
				else if ("UpdateSuburusan".equals(subminor_command))
				{
					if (bolehsimpan.equals("yes")) 
					{
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102700","add",getParam("id_fail"));
					}
					this.context.put("readmode", "view");	
					
				}
				
				
				else if ("Simpan".equals(subminor_command))
				{
					 if(getParam("id_tanahumum")!="" && getParam("id_tanahumum")!=null)
					 {
						 if (bolehsimpan.equals("yes")) 
							{
					updateMaklumatAmTanah(session);	
					/*	logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
								(String) session.getAttribute("_ekptg_user_id"),"16102699","add");
					*/
							}		}	 
					 else
					 {
						 if (bolehsimpan.equals("yes")) 
							{
						addMaklumatAmTanah(session);				
						logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
								(String) session.getAttribute("_ekptg_user_id"),"16102699","add",getParam("id_fail"));
							}
					 }
					 this.context.put("readmode", "view");	
				}
				else if("Batal".equals(subminor_command) ||  "Kemaskini".equals(subminor_command))
				{
					this.context.put("readmode", "edit");	
				}
				
				else if("Hapus".equals(subminor_command))
				{
					if (bolehsimpan.equals("yes")) 
					{
					logic.deleteMaklumatAm(getParam("id_tanahumum"));
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102699","hapus",getParam("id_fail"));
					this.context.put("readmode", "edit");	
					}
				}
							
				vm = "app/ppt/frmPenarikanLaporanAwalTanah.jsp";
	    	}
 			if ("Perihal_Tanah".equals(sub_command))
			{		                 
				if ("View".equals(subminor_command))
				{
				
					if(maklumat_am_tanah.size()>0)
					{
					Hashtable h = (Hashtable) maklumat_am_tanah.get(0);
					
					if(h.get("LOKASI_TANAH").toString().equals("") && h.get("LOT_SELURUH_LOT").toString().equals("") && h.get("LOT_JENIS_TANAMAN").toString().equals("")
            				&& h.get("LOT_BERHAMPIRAN").toString().equals("") && h.get("LOT_KEADAAN_TANAMAN").toString().equals("") && h.get("ULASAN").toString().equals("")
            				&& h.get("RUPABUMI_SELURUH_LOT").toString().equals("") && h.get("RUPABUMI_KWSN_TERLIBAT").toString().equals("") 
            				&& h.get("MELIBATKAN_BANGUNAN").toString().equals("") && h.get("BILANGAN_BANGUNAN").toString().equals("")
            				)
            		{
            			this.context.put("readmode", "edit");		
            		}else
            		{
            			 this.context.put("readmode", "view");			
            		}						
					}
					else
					{
					 this.context.put("readmode", "edit");	
					}
				}
				else if ("Simpan".equals(subminor_command))
				{
					 if(getParam("id_tanahumum")!="" && getParam("id_tanahumum")!=null)
					 {						
						 if (bolehsimpan.equals("yes")) 
							{
							 updatePerihalTanah(session);	
				/*		logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
								(String) session.getAttribute("_ekptg_user_id"),"16102699","add");*/
					 		}						
					 }	 
					 else
					 {							
						if (bolehsimpan.equals("yes")) 
						{
							addPerihalTanah(session);
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102699","add",getParam("id_fail"));
				 		}	
					 }
					 this.context.put("readmode", "view");	
				}
				else if("Batal".equals(subminor_command) ||  "Kemaskini".equals(subminor_command))
				{
					this.context.put("readmode", "edit");	
				}
				else if ("UpdateSuburusan".equals(subminor_command))
				{
					if (bolehsimpan.equals("yes")) 
					{
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102700","add",getParam("id_fail"));
					}
					this.context.put("readmode", "view");	
					
				}
				
				else if("Hapus".equals(subminor_command))
				{				
					if (bolehsimpan.equals("yes")) 
					{
						logic.deleteMaklumatAm(getParam("id_tanahumum"));
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102699","hapus",getParam("id_fail"));
					this.context.put("readmode", "edit");	
					}				
					
					this.context.put("readmode", "edit");	
				}
							
				vm = "app/ppt/frmPenarikanBalikPerihalTanah.jsp";
	    	}			
 			if ("Laporan_Kerosakan".equals(sub_command))
			{		                 
				if ("View".equals(subminor_command))
				{
				
					if(maklumat_am_tanah.size()>0)
					{
					Hashtable h = (Hashtable) maklumat_am_tanah.get(0);

					if(h.get("KEROSAKAN_TANAH").toString().equals("") && h.get("KEROSAKAN_TANAMAN").toString().equals("") && h.get("KEROSAKAN_BANGUNAN").toString().equals("")
            				&& h.get("KOS_DITANGGUNG").toString().equals(""))
            		{
            			this.context.put("readmode", "edit");		
            		}else
            		{
            			 this.context.put("readmode", "view");			
            		}						
					}
					else
					{
					 this.context.put("readmode", "edit");	
					}
				}
				else if ("Simpan".equals(subminor_command))
				{
							
					 if(getParam("id_tanahumum")!="" && getParam("id_tanahumum")!=null)
					 {						
						 if (bolehsimpan.equals("yes")) 
							{
							 updateMaklumatKerosakan(session);	
					/*		
						logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
								(String) session.getAttribute("_ekptg_user_id"),"16102699","add");
					 	*/	}						
					 }	 
					 else
					 {							
						if (bolehsimpan.equals("yes")) 
						{
							 addMaklumatKerosakan(session);		
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102699","add",getParam("id_fail"));
				 		}	
					 }
					
					
					 this.context.put("readmode", "view");	
				}
				else if ("UpdateSuburusan".equals(subminor_command))
				{
					if (bolehsimpan.equals("yes")) 
					{
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102700","add",getParam("id_fail"));
					}
					this.context.put("readmode", "view");	
					
				}
				else if("Batal".equals(subminor_command) ||  "Kemaskini".equals(subminor_command))
				{
					this.context.put("readmode", "edit");	
				}
				
				else if("Hapus".equals(subminor_command))
				{
					if (bolehsimpan.equals("yes")) 
					{
						logic.deleteMaklumatAm(getParam("id_tanahumum"));
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102699","hapus",getParam("id_fail"));
					
					}	
					this.context.put("readmode", "edit");	
				}
							
				vm = "app/ppt/frmPenarikanBalikLaporanTanahKerosakan.jsp";
	    	}			
 			
			context.put("id_permohonan",getParam("id_permohonan"));
			context.put("id_pembatalan",getParam("id_pembatalan"));
    		context.put("id_hakmilik",getParam("id_hakmilik"));	
    		
    		senarai_pihak_penting_bangunan = logic.senarai_pihak_penting_bangunan(getParam("id_hakmilik"));
			this.context.put("senarai_pihak_penting_bangunan",senarai_pihak_penting_bangunan);
    		senarai_bangunan = logic.senarai_bangunan(getParam("id_hakmilik"),getParam("id_pembatalan"));
    		this.context.put("senarai_bangunan", senarai_bangunan);
    		maklumat_am_tanah = logic.maklumat_am_tanah(getParam("id_pembatalan"),getParam("id_hakmilik"));
			this.context.put("maklumat_am_tanah", maklumat_am_tanah);    
			maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
			this.context.put("maklumat_hakmilik", maklumat_hakmilik); 		
			header = logic.content_header(getParam("id_permohonan"));
			this.context.put("header",header);				
			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
  			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);  			
  			maklumat_am_tanah_permohonan = logic.maklumat_am_tanah_permohonan(getParam("id_permohonan"));
  			this.context.put("maklumat_am_tanah_permohonan", maklumat_am_tanah_permohonan);  			
  			this.context.put("senarai_laporan_tanah", senarai_laporan_tanah);  			
			jenis_button = "2";
			this.context.put("tajuk_header","LAPORAN TANAH");			
		}
		
         else if ("MMK".equals(main_command)){
        	 
        	 
        	senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
     		maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_pembatalan")); 
           	maklumat_semakan = logic.maklumat_semakan(getParam("id_pembatalan"));
           	maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
           	
           	
 			if ("Penyediaan".equals(sub_command))
 			{	if ("View".equals(subminor_command))
 				{	if(maklumat_penyediaan.size() > 0)
 					{this.context.put("readmode", "view");}
 					else
 					{this.context.put("readmode", "edit");}  }
 			
 			    else if ("Hantar".equals(subminor_command))
				{	
 			    	
 			    	
				}
 			   else if ("UpdateSuburusan".equals(sub_command)){
 					String[] lot_tarik = this.request.getParameterValues("lot_tarikX");
 					String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarikX");
 					String[] lot_ambil = this.request.getParameterValues("lot_ambilX");
 					String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asalX");
 					String[] luas_lot = this.request.getParameterValues("luas_lotX");	
 					String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luasX"); 					
 					if (id_lot_tarik != null ) 
 					{
 					for (int t = 0; t < id_lot_tarik.length; t++) 
 					{
 							if (bolehsimpan.equals("yes")) 
 							{
 							logic.update_status_hakmilik(id_lot_tarik[t], getParam("id_permohonan"),
 									(String) session.getAttribute("_ekptg_user_id"),"16102701","add",getParam("id_fail"));
 							}									
 													
 					}
 					}	
 					this.context.put("readmode", "view"); 
 		    	} 			
 				else if ("Simpan".equals(subminor_command))
 				{	
 					if(maklumat_penyediaan.size() > 0)
 					{}else
 					{
 					String[] lot_tarik = this.request.getParameterValues("lot_tarikX");
 					String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarikX");
 					String[] lot_ambil = this.request.getParameterValues("lot_ambilX");
 					String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asalX");
 					String[] luas_lot = this.request.getParameterValues("luas_lotX");	
 					String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luasX"); 					
 					if (id_lot_tarik != null ) 
 					{
 					for (int t = 0; t < id_lot_tarik.length; t++) 
 					{
 							if (bolehsimpan.equals("yes")) 
 							{
 							logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
 									(String) session.getAttribute("_ekptg_user_id"),"16102701","add",getParam("id_fail"));
 							}									
 													
 					}
 					}	
 					}
 					
 					if(maklumat_penyediaan.size() > 0)
 					{
 						
 					updatePenyediaanMMK(session);
 					if (bolehsimpan.equals("yes")) 
					{
        			logic.delete_subMMK(getParam("id_mmk"));
        			
        			String[] txtUlasanTUJUAN_MAIN = this.request.getParameterValues("txtUlasanTUJUAN_MAIN");
        			if (txtUlasanTUJUAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanTUJUAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanTUJUAN_MAIN[t],"MAIN",getParam("id_mmk"),"TUJUAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanLATARBELAKANG_MAIN = this.request.getParameterValues("txtUlasanLATARBELAKANG_MAIN");
        			if (txtUlasanLATARBELAKANG_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanLATARBELAKANG_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanLATARBELAKANG_MAIN[t],"MAIN",getParam("id_mmk"),"LATARBELAKANG",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanMOHON_AMBIL_TANAH_MAIN = this.request.getParameterValues("txtUlasanMOHON_AMBIL_TANAH_MAIN");
        			if (txtUlasanMOHON_AMBIL_TANAH_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanMOHON_AMBIL_TANAH_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanMOHON_AMBIL_TANAH_MAIN[t],"MAIN",getParam("id_mmk"),"MOHON_AMBIL_TANAH",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanPERAKUAN_PTG_MAIN = this.request.getParameterValues("txtUlasanPERAKUAN_PTG_MAIN");
        			if (txtUlasanPERAKUAN_PTG_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPERAKUAN_PTG_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPERAKUAN_PTG_MAIN[t],"MAIN",getParam("id_mmk"),"PERAKUAN_PTG",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanPERAKUAN_PENTADBIR_MAIN = this.request.getParameterValues("txtUlasanPERAKUAN_PENTADBIR_MAIN");
        			if (txtUlasanPERAKUAN_PENTADBIR_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPERAKUAN_PENTADBIR_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPERAKUAN_PENTADBIR_MAIN[t],"MAIN",getParam("id_mmk"),"PERAKUAN_PENTADBIR",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanPENUTUP_MAIN = this.request.getParameterValues("txtUlasanPENUTUP_MAIN");
        			if (txtUlasanPENUTUP_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPENUTUP_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPENUTUP_MAIN[t],"MAIN",getParam("id_mmk"),"PENUTUP",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanSEBABPENARIKAN_MAIN = this.request.getParameterValues("txtUlasanSEBABPENARIKAN_MAIN");
        			if (txtUlasanSEBABPENARIKAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanSEBABPENARIKAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanSEBABPENARIKAN_MAIN[t],"MAIN",getParam("id_mmk"),"SEBABPENARIKAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanPERAKUAN_MAIN = this.request.getParameterValues("txtUlasanPERAKUAN_MAIN");
        			if (txtUlasanPERAKUAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPERAKUAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPERAKUAN_MAIN[t],"MAIN",getParam("id_mmk"),"PERAKUAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanSYORJAJAHAN_MAIN = this.request.getParameterValues("txtUlasanSYORJAJAHAN_MAIN");
        			if (txtUlasanSYORJAJAHAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanSYORJAJAHAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanSYORJAJAHAN_MAIN[t],"MAIN",getParam("id_mmk"),"SYORJAJAHAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanULASANPTG_MAIN = this.request.getParameterValues("txtUlasanULASANPTG_MAIN");
        			if (txtUlasanULASANPTG_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanULASANPTG_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanULASANPTG_MAIN[t],"MAIN",getParam("id_mmk"),"ULASANPTG",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanKEPUTUSAN_MMK_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MMK_MAIN");
        			if (txtUlasanKEPUTUSAN_MMK_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanKEPUTUSAN_MMK_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanKEPUTUSAN_MMK_MAIN[t],"MAIN",getParam("id_mmk"),"KEPUTUSAN_MMK",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanKEPUTUSANMAJLIS_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSANMAJLIS_MAIN");
        			if (txtUlasanKEPUTUSANMAJLIS_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanKEPUTUSANMAJLIS_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanKEPUTUSANMAJLIS_MAIN[t],"MAIN",getParam("id_mmk"),"KEPUTUSANMAJLIS",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanSOKONGAN_PENGARAH_MAIN = this.request.getParameterValues("txtUlasanSOKONGAN_PENGARAH_MAIN");
        			if (txtUlasanSOKONGAN_PENGARAH_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanSOKONGAN_PENGARAH_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanSOKONGAN_PENGARAH_MAIN[t],"MAIN",getParam("id_mmk"),"SOKONGAN_PENGARAH",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN = this.request.getParameterValues("txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN");
        			if (txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN[t],"MAIN",getParam("id_mmk"),"MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanULASAN_PENGARAH_MAIN = this.request.getParameterValues("txtUlasanULASAN_PENGARAH_MAIN");
        			if (txtUlasanULASAN_PENGARAH_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanULASAN_PENGARAH_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanULASAN_PENGARAH_MAIN[t],"MAIN",getParam("id_mmk"),"ULASAN_PENGARAH",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanKEPUTUSAN_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MAIN");
        			if (txtUlasanKEPUTUSAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanKEPUTUSAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanKEPUTUSAN_MAIN[t],"MAIN",getParam("id_mmk"),"KEPUTUSAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanSYOR_MAIN = this.request.getParameterValues("txtUlasanSYOR_MAIN");
        			if (txtUlasanSYOR_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanSYOR_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanSYOR_MAIN[t],"MAIN",getParam("id_mmk"),"SYOR",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanIMPLIKASI_MAIN = this.request.getParameterValues("txtUlasanIMPLIKASI_MAIN");
        			if (txtUlasanIMPLIKASI_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanIMPLIKASI_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanIMPLIKASI_MAIN[t],"MAIN",getParam("id_mmk"),"IMPLIKASI",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanPERIHALTANAH_MAIN = this.request.getParameterValues("txtUlasanPERIHALTANAH_MAIN");
        			if (txtUlasanPERIHALTANAH_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPERIHALTANAH_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPERIHALTANAH_MAIN[t],"MAIN",getParam("id_mmk"),"PERIHALTANAH",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanKESIMPULAN_MAIN = this.request.getParameterValues("txtUlasanKESIMPULAN_MAIN");
        			if (txtUlasanKESIMPULAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanKESIMPULAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanKESIMPULAN_MAIN[t],"MAIN",getParam("id_mmk"),"KESIMPULAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanLAPORANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanLAPORANTEKNIKAL_MAIN");
        			if (txtUlasanLAPORANTEKNIKAL_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanLAPORANTEKNIKAL_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanLAPORANTEKNIKAL_MAIN[t],"MAIN",getParam("id_mmk"),"LAPORANTEKNIKAL",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanASASPERTIMBANGAN_MAIN = this.request.getParameterValues("txtUlasanASASPERTIMBANGAN_MAIN");
        			if (txtUlasanASASPERTIMBANGAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanASASPERTIMBANGAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanASASPERTIMBANGAN_MAIN[t],"MAIN",getParam("id_mmk"),"ASASPERTIMBANGAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanPERUNTUKAN_MAIN");
        			if (txtUlasanPERUNTUKAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPERUNTUKAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPERUNTUKAN_MAIN[t],"MAIN",getParam("id_mmk"),"PERUNTUKAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanISTIHAR_PENGAMBILAN_MAIN = this.request.getParameterValues("txtUlasanISTIHAR_PENGAMBILAN_MAIN");
        			if (txtUlasanISTIHAR_PENGAMBILAN_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanISTIHAR_PENGAMBILAN_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanISTIHAR_PENGAMBILAN_MAIN[t],"MAIN",getParam("id_mmk"),"ISTIHAR_PENGAMBILAN",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanWASILAN_HAKMILIK_MAIN = this.request.getParameterValues("txtUlasanWASILAN_HAKMILIK_MAIN");
        			if (txtUlasanWASILAN_HAKMILIK_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanWASILAN_HAKMILIK_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanWASILAN_HAKMILIK_MAIN[t],"MAIN",getParam("id_mmk"),"WASILAN_HAKMILIK",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			String[] txtUlasanLOT_TINGGAL_MAIN = this.request.getParameterValues("txtUlasanLOT_TINGGAL_MAIN");
        			if (txtUlasanLOT_TINGGAL_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanLOT_TINGGAL_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanLOT_TINGGAL_MAIN[t],"MAIN",getParam("id_mmk"),"LOT_TINGGAL",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			
        			String[] txtUlasanPENYAMPAIAN_AWARD_MAIN = this.request.getParameterValues("txtUlasanPENYAMPAIAN_AWARD_MAIN");
        			if (txtUlasanPENYAMPAIAN_AWARD_MAIN != null ) 
        			{
    					for (int t = 0; t < txtUlasanPENYAMPAIAN_AWARD_MAIN.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanPENYAMPAIAN_AWARD_MAIN[t],"MAIN",getParam("id_mmk"),"PENYAMPAIAN_AWARD",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			
        			
        			String[] txtUlasanULASAN_PENTADBIR = this.request.getParameterValues("txtUlasanULASAN_PENTADBIR");
        			
        			myLogger.info("");
        			
        			if (txtUlasanULASAN_PENTADBIR != null ) 
        			{
    					for (int t = 0; t < txtUlasanULASAN_PENTADBIR.length; t++) 
    					    {    			    						
    							if (bolehsimpan.equals("yes")) 
    							{    			    								
    								logic.update_subMMK(txtUlasanULASAN_PENTADBIR[t],"MAIN",getParam("id_mmk"),"ULASAN_PENTADBIR",(String) session.getAttribute("_ekptg_user_id"));
    							}
    						}
    				}
        			
        			
        			
        			
        			senarai_submmk = logic.senarai_submmk(getParam("id_mmk"));
        			this.context.put("senarai_submmk", senarai_submmk);
        			
        			
        			
        			if(getParam("flag_mmk").equals("1"))
        			{
        			myLogger.info("TEST HANTAR SEMAK MMK");
        			sendEmail(id_pengarah,no_fail,tujuan,tarikh_permohonan,nama_kementerian);  
        			}
        			
        			
        			
					}
 					
 					}
 					else
 					{ 					
 						if(bolehsimpan=="yes")
 						{ 
 							long id_mmk = DB.getNextID("TBLPPTMMK_SEQ");							
 							addPenyediaanMMK(session,id_mmk+""); 
 							
 							
 							String[] txtUlasanTUJUAN_MAIN = this.request.getParameterValues("txtUlasanTUJUAN_MAIN");
		        			if (txtUlasanTUJUAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanTUJUAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanTUJUAN_MAIN[t],"MAIN",id_mmk,"TUJUAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanPERAKUAN_PENTADBIR_MAIN = this.request.getParameterValues("txtUlasanPERAKUAN_PENTADBIR_MAIN");
		        			if (txtUlasanPERAKUAN_PENTADBIR_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPERAKUAN_PENTADBIR_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPERAKUAN_PENTADBIR_MAIN[t],"MAIN",id_mmk,"PERAKUAN_PENTADBIR",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanPERAKUAN_PTG_MAIN = this.request.getParameterValues("txtUlasanPERAKUAN_PTG_MAIN");
		        			if (txtUlasanPERAKUAN_PTG_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPERAKUAN_PTG_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPERAKUAN_PTG_MAIN[t],"MAIN",id_mmk,"PERAKUAN_PTG",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanLATARBELAKANG_MAIN = this.request.getParameterValues("txtUlasanLATARBELAKANG_MAIN");
		        			if (txtUlasanLATARBELAKANG_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanLATARBELAKANG_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanLATARBELAKANG_MAIN[t],"MAIN",id_mmk,"LATARBELAKANG",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanMOHON_AMBIL_TANAH_MAIN = this.request.getParameterValues("txtUlasanMOHON_AMBIL_TANAH_MAIN");
		        			if (txtUlasanMOHON_AMBIL_TANAH_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanMOHON_AMBIL_TANAH_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanMOHON_AMBIL_TANAH_MAIN[t],"MAIN",id_mmk,"MOHON_AMBIL_TANAH",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanSYOR_MAIN = this.request.getParameterValues("txtUlasanSYOR_MAIN");
		        			if (txtUlasanSYOR_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanSYOR_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanSYOR_MAIN[t],"MAIN",id_mmk,"SYOR",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			
		        			String[] txtUlasanASASPERTIMBANGAN_MAIN = this.request.getParameterValues("txtUlasanASASPERTIMBANGAN_MAIN");
		        			if (txtUlasanASASPERTIMBANGAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanASASPERTIMBANGAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanASASPERTIMBANGAN_MAIN[t],"MAIN",id_mmk,"ASASPERTIMBANGAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanIMPLIKASI_MAIN = this.request.getParameterValues("txtUlasanIMPLIKASI_MAIN");
		        			if (txtUlasanIMPLIKASI_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanIMPLIKASI_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanIMPLIKASI_MAIN[t],"MAIN",id_mmk,"IMPLIKASI",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanPERIHALTANAH_MAIN = this.request.getParameterValues("txtUlasanPERIHALTANAH_MAIN");
		        			if (txtUlasanPERIHALTANAH_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPERIHALTANAH_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPERIHALTANAH_MAIN[t],"MAIN",id_mmk,"PERIHALTANAH",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanKESIMPULAN_MAIN = this.request.getParameterValues("txtUlasanKESIMPULAN_MAIN");
		        			if (txtUlasanKESIMPULAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanKESIMPULAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanKESIMPULAN_MAIN[t],"MAIN",id_mmk,"KESIMPULAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanLAPORANTEKNIKAL_MAIN = this.request.getParameterValues("txtUlasanLAPORANTEKNIKAL_MAIN");
		        			if (txtUlasanLAPORANTEKNIKAL_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanLAPORANTEKNIKAL_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanLAPORANTEKNIKAL_MAIN[t],"MAIN",id_mmk,"LAPORANTEKNIKAL",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			
		        			String[] txtUlasanPERUNTUKAN_MAIN = this.request.getParameterValues("txtUlasanPERUNTUKAN_MAIN");
		        			if (txtUlasanPERUNTUKAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPERUNTUKAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPERUNTUKAN_MAIN[t],"MAIN",id_mmk,"PERUNTUKAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanISTIHAR_PENGAMBILAN_MAIN = this.request.getParameterValues("txtUlasanISTIHAR_PENGAMBILAN_MAIN");
		        			if (txtUlasanISTIHAR_PENGAMBILAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanISTIHAR_PENGAMBILAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanISTIHAR_PENGAMBILAN_MAIN[t],"MAIN",id_mmk,"ISTIHAR_PENGAMBILAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanWASILAN_HAKMILIK_MAIN = this.request.getParameterValues("txtUlasanWASILAN_HAKMILIK_MAIN");
		        			if (txtUlasanWASILAN_HAKMILIK_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanWASILAN_HAKMILIK_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanWASILAN_HAKMILIK_MAIN[t],"MAIN",id_mmk,"WASILAN_HAKMILIK",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanLOT_TINGGAL_MAIN = this.request.getParameterValues("txtUlasanLOT_TINGGAL_MAIN");
		        			if (txtUlasanLOT_TINGGAL_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanLOT_TINGGAL_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanLOT_TINGGAL_MAIN[t],"MAIN",id_mmk,"LOT_TINGGAL",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanPENYAMPAIAN_AWARD_MAIN = this.request.getParameterValues("txtUlasanPENYAMPAIAN_AWARD_MAIN");
		        			if (txtUlasanPENYAMPAIAN_AWARD_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPENYAMPAIAN_AWARD_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPENYAMPAIAN_AWARD_MAIN[t],"MAIN",id_mmk,"PENYAMPAIAN_AWARD",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanSEBABPENARIKAN_MAIN = this.request.getParameterValues("txtUlasanSEBABPENARIKAN_MAIN");
		        			if (txtUlasanSEBABPENARIKAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanSEBABPENARIKAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanSEBABPENARIKAN_MAIN[t],"MAIN",id_mmk,"SEBABPENARIKAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanPENUTUP_MAIN = this.request.getParameterValues("txtUlasanPENUTUP_MAIN");
		        			if (txtUlasanPENUTUP_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPENUTUP_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPENUTUP_MAIN[t],"MAIN",id_mmk,"PENUTUP",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanPERAKUAN_MAIN = this.request.getParameterValues("txtUlasanPERAKUAN_MAIN");
		        			if (txtUlasanPERAKUAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanPERAKUAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanPERAKUAN_MAIN[t],"MAIN",id_mmk,"PERAKUAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanSYORJAJAHAN_MAIN = this.request.getParameterValues("txtUlasanSYORJAJAHAN_MAIN");
		        			if (txtUlasanSYORJAJAHAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanSYORJAJAHAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanSYORJAJAHAN_MAIN[t],"MAIN",id_mmk,"SYORJAJAHAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanULASANPTG_MAIN = this.request.getParameterValues("txtUlasanULASANPTG_MAIN");
		        			if (txtUlasanULASANPTG_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanULASANPTG_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanULASANPTG_MAIN[t],"MAIN",id_mmk,"ULASANPTG",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanKEPUTUSAN_MMK_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MMK_MAIN");
		        			if (txtUlasanKEPUTUSAN_MMK_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanKEPUTUSAN_MMK_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanKEPUTUSAN_MMK_MAIN[t],"MAIN",id_mmk,"KEPUTUSAN_MMK",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			
		        			String[] txtUlasanKEPUTUSANMAJLIS_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSANMAJLIS_MAIN");
		        			if (txtUlasanKEPUTUSANMAJLIS_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanKEPUTUSANMAJLIS_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanKEPUTUSANMAJLIS_MAIN[t],"MAIN",id_mmk,"KEPUTUSANMAJLIS",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			
		        			
		        			String[] txtUlasanSOKONGAN_PENGARAH_MAIN = this.request.getParameterValues("txtUlasanSOKONGAN_PENGARAH_MAIN");
		        			if (txtUlasanSOKONGAN_PENGARAH_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanSOKONGAN_PENGARAH_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanSOKONGAN_PENGARAH_MAIN[t],"MAIN",id_mmk,"SOKONGAN_PENGARAH",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN = this.request.getParameterValues("txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN");
		        			if (txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanMOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN_MAIN[t],"MAIN",id_mmk,"MOHON_TARIK_BALIK_ISTIHAR_PGAMBILAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			String[] txtUlasanULASAN_PENGARAH_MAIN = this.request.getParameterValues("txtUlasanULASAN_PENGARAH_MAIN");
		        			if (txtUlasanULASAN_PENGARAH_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanULASAN_PENGARAH_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanULASAN_PENGARAH_MAIN[t],"MAIN",id_mmk,"ULASAN_PENGARAH",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			
		        			
		        			String[] txtUlasanKEPUTUSAN_MAIN = this.request.getParameterValues("txtUlasanKEPUTUSAN_MAIN");
		        			if (txtUlasanKEPUTUSAN_MAIN != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanKEPUTUSAN_MAIN.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanKEPUTUSAN_MAIN[t],"MAIN",id_mmk,"KEPUTUSAN",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
 							
		        			
		        			
		        			
		        			
		        			String[] txtUlasanULASAN_PENTADBIR = this.request.getParameterValues("txtUlasanULASAN_PENTADBIR");
		        			if (txtUlasanULASAN_PENTADBIR != null ) 
		        			{
		    					for (int t = 0; t < txtUlasanULASAN_PENTADBIR.length; t++) 
		    					    {    			    						
		    							if (bolehsimpan.equals("yes")) 
		    							{    			    								
		    								logic.simpan_subMMK(txtUlasanULASAN_PENTADBIR[t],"MAIN",id_mmk,"ULASAN_PENTADBIR",(String) session.getAttribute("_ekptg_user_id"));
		    							}
		    						}
		    				}
		        			
		        			
		        			senarai_submmk = logic.senarai_submmk(id_mmk+"");
		        			this.context.put("senarai_submmk", senarai_submmk);
		        			
		        			
		        			
		        			
 						}
 					}
 				this.context.put("readmode", "view"); 	}	
 				else if ("Batal".equals(subminor_command))
 				{this.context.put("readmode", "edit");} 
 				else if ("Kemaskini".equals(subminor_command))
 				{this.context.put("readmode", "edit");} 
 				else if ("Hapus".equals(subminor_command))
 				{   logic.deleteMMKPenyediaan(getParam("id_mmk"));
 					this.context.put("readmode", "edit");} 
 			
 			
 			if(!getParam("id_pembatalan").equals("") && getParam("id_pembatalan")!= null )
 			{
 			senarai_submmk = logic.senarai_submmk_bypenarikan(getParam("id_pembatalan"));
			this.context.put("senarai_submmk", senarai_submmk);
 			}
			
 				vm = "app/ppt/frmPenarikanUrusanMMKMBKMLC.jsp";		
 	    	}
 			
 			if ("Semakan".equals(sub_command))
 			{	if ("View".equals(subminor_command))
 				{	if(maklumat_semakan.size() > 0){
 						this.context.put("readmode", "view");					
 					}else{						
 						context.put("selectPengarah",HTML.SelectPengarahDanBekasPengarah(id_projekNegeri,"socPengarah",null,null,"style=width:240px"));
 						this.context.put("readmode", "edit");					
 					}  
 				
 				}else if ("Simpan".equals(subminor_command)){	
 					
 					
 					String[] lot_tarik = this.request.getParameterValues("lot_tarikX");
 					String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarikX");
 					String[] lot_ambil = this.request.getParameterValues("lot_ambilX");
 					String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asalX");
 					String[] luas_lot = this.request.getParameterValues("luas_lotX");	
 					String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luasX"); 					
 					if (id_lot_tarik != null ) 
 					{
 					for (int t = 0; t < id_lot_tarik.length; t++) 
 					{
 							if (bolehsimpan.equals("yes")) 
 							{
 							if(getParam("socKeputusan").equals("1"))
 							{	
 							logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
 									(String) session.getAttribute("_ekptg_user_id"),"16102702","add",getParam("id_fail"));
 							}
 							}									
 													
 					}
 					}	
 					
 					
 					updateSemakanMMK(session); 	
 					
 					this.context.put("readmode", "view"); 	}	
 				else if ("Batal".equals(subminor_command))
 				{this.context.put("readmode", "edit");} 
 				else if ("Kemaskini".equals(subminor_command)){
 					this.context.put("readmode", "edit");
 					String id_penyemak = "";
						maklumat_semakan = logic.maklumat_semakan(getParam("id_pembatalan"));
						if(maklumat_semakan.size()!=0){
							Hashtable ms = (Hashtable)maklumat_semakan.get(0);
							id_penyemak = (String)ms.get("USER_ID");
						}
						
						if(id_penyemak.equals("") || id_penyemak.isEmpty()){
							context.put("selectPengarah",HTML.SelectPengarahDanBekasPengarah(id_projekNegeri,"socPengarah",null,null,"style=width:240px"));
						}else{
							context.put("selectPengarah",HTML.SelectPengarahDanBekasPengarah(id_projekNegeri,"socPengarah",Long.parseLong(id_penyemak),null,"style=width:240px"));
						}
 				} 
 				else if ("Hapus".equals(subminor_command))
 				{   logic.deleteSemakanMMK(getParam("id_mmk"),(String) session.getAttribute("_ekptg_user_id"));
 					
 				this.context.put("readmode", "edit");} 
 			vm = "app/ppt/frmPenarikanUrusanMMKSemakan.jsp";		
 	    	}
 			
 			if ("Keputusan".equals(sub_command))
 			{	if ("View".equals(subminor_command))
 				{	if(maklumat_keputusan.size() > 0)
 					{this.context.put("readmode", "view");}
 					else
 					{this.context.put("readmode", "edit");}  }
 				else if ("Simpan".equals(subminor_command))
 				{	
 					 					
 					
 					if(maklumat_keputusan.size() > 0)
 					{updateKeputusanMMK(session);}
 					else
 					{addKeputusanMMK(session);
 					
 					
 					
 					String[] lot_tarik = this.request.getParameterValues("lot_tarikX");
 					String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarikX");
 					String[] lot_ambil = this.request.getParameterValues("lot_ambilX");
 					String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asalX");
 					String[] luas_lot = this.request.getParameterValues("luas_lotX");	
 					String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luasX"); 					
 					if (id_lot_tarik != null ) 
 					{
 					for (int t = 0; t < id_lot_tarik.length; t++) 
 					{
 							if (bolehsimpan.equals("yes")) 
 							{
 							
 							logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
 									(String) session.getAttribute("_ekptg_user_id"),"16102703","add",getParam("id_fail"));
 							
 							}									
 													
 					}
 					}	
 					
 					
 					
 					}
 					this.context.put("readmode", "view"); 	}	
 				else if ("Batal".equals(subminor_command))
 				{this.context.put("readmode", "edit");} 
 				else if ("Kemaskini".equals(subminor_command))
 				{this.context.put("readmode", "edit");} 
 				else if ("Hapus".equals(subminor_command))
 				{   logic.deleteMMKKeputusan(getParam("id_mmk"));
 					this.context.put("readmode", "edit");} 
 			vm = "app/ppt/frmPenarikanUrusanMMKKeputusan.jsp";		
 	    	}			
 			
    		context.put("id_permohonan",getParam("id_permohonan"));
    		context.put("id_pembatalan",getParam("id_pembatalan"));		
 			header = logic.content_header(getParam("id_permohonan"));
 			this.context.put("header",header);				
 			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
 			this.context.put("maklumat_pembatalan", maklumat_pembatalan); 			
 			maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_pembatalan"));
 			this.context.put("maklumat_penyediaan", maklumat_penyediaan); 	
 			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
 			this.context.put("maklumat_keputusan", maklumat_keputusan); 	
 			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
 			jenis_button = "3";
 			maklumat_semakan_pegawai = logic.maklumat_semakan_pegawai((String) session.getAttribute("_ekptg_user_id"));
 			this.context.put("maklumat_semakan_pegawai", maklumat_semakan_pegawai);
 			maklumat_semakan = logic.maklumat_semakan(getParam("id_pembatalan"));
 			this.context.put("maklumat_semakan", maklumat_semakan);
 			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		maklumat_hakmilik_mmk_hektar = logic.maklumat_hakmilik_mmk_hektar(getParam("id_pembatalan"));
			this.context.put("maklumat_hakmilik_mmk_hektar", maklumat_hakmilik_mmk_hektar);
			maklumat_hakmilik_mmk_ekar = logic.maklumat_hakmilik_mmk_ekar(getParam("id_pembatalan"));
			this.context.put("maklumat_hakmilik_mmk_ekar", maklumat_hakmilik_mmk_ekar);
			maklumat_hakmilik_mmk_lot = logic.maklumat_hakmilik_mmk_lot(getParam("id_pembatalan"));
			this.context.put("maklumat_hakmilik_mmk_lot", maklumat_hakmilik_mmk_lot);
			maklumat_hakmilik_mmk_pb = logic.maklumat_hakmilik_mmk_pb(getParam("id_pembatalan"));
			this.context.put("maklumat_hakmilik_mmk_pb", maklumat_hakmilik_mmk_pb);
			senarai_lot_mmk = logic.senarai_lot_mmk(getParam("id_pembatalan"));
			this.context.put("senarai_lot_mmk", senarai_lot_mmk);
			nowarta_lot_mmk = logic.nowarta_lot_mmk(getParam("id_permohonan"));
			this.context.put("nowarta_lot_mmk", nowarta_lot_mmk);			
			this.context.put("tajuk_header","URUSAN KERTAS MMK/MB/KM/LC");  			
 			senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
 			
 		}
		
		
		
         else if ("Warta".equals(main_command)){
            	maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_pembatalan")); 
            	maklumat_semakan = logic.maklumat_semakan(getParam("id_pembatalan"));
            	maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
          		maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			if ("Warta".equals(sub_command))
  			{	
  				senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
  	    		
  				if ("View".equals(subminor_command))
  				{	if(maklumat_warta.size() > 0)
  					{this.context.put("readmode", "view");}
  					else
  					{this.context.put("readmode", "edit");}  }
  				else if ("Simpan".equals(subminor_command))
  				{	if(maklumat_warta.size() > 0)
  					{updateWartaMMK(session);}
  					else
  					{if(bolehsimpan=="yes")
					{ 
  						
  						addWartaMMK(session); 
  						
  						
  	 					String[] lot_tarik = this.request.getParameterValues("lot_tarikX");
  	 					String[] id_lot_tarik = this.request.getParameterValues("id_lot_tarikX");
  	 					String[] lot_ambil = this.request.getParameterValues("lot_ambilX");
  	 					String[] lot_ambil_asal = this.request.getParameterValues("lot_ambil_asalX");
  	 					String[] luas_lot = this.request.getParameterValues("luas_lotX");	
  	 					String[] id_hakmilik_luas = this.request.getParameterValues("id_hakmilik_luasX"); 					
  	 					if (id_lot_tarik != null ) 
  	 					{
  	 					for (int t = 0; t < id_lot_tarik.length; t++) 
  	 					{
  	 							if (bolehsimpan.equals("yes")) 
  	 							{
  	 							
  	 							logic.update_status_hakmilik(id_hakmilik_luas[t], getParam("id_permohonan"),
  	 									(String) session.getAttribute("_ekptg_user_id"),"16102704","add",getParam("id_fail"));
  	 							
  	 							}									
  	 													
  	 					}
  	 					}	
  	 					
					
					}}
  					this.context.put("readmode", "view"); 	}	
  				else if ("Batal".equals(subminor_command))
  				{this.context.put("readmode", "edit");} 
  				else if ("Kemaskini".equals(subminor_command))
  				{this.context.put("readmode", "edit");} 
  				else if ("Hapus".equals(subminor_command))
  				{   logic.deleteWarta(getParam("id_warta"));
  					this.context.put("readmode", "edit");}
  				this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
  				vm = "app/ppt/frmPenarikanWarta.jsp";		
  	    	}
  					
  			
     		context.put("id_permohonan",getParam("id_permohonan"));
     		context.put("id_pembatalan",getParam("id_pembatalan"));		
  			header = logic.content_header(getParam("id_permohonan"));
  			this.context.put("header",header);				
  			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
  			this.context.put("maklumat_pembatalan", maklumat_pembatalan); 
  			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
  			maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_pembatalan"));
  			this.context.put("maklumat_penyediaan", maklumat_penyediaan); 	
  			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan); 	
  			jenis_button = "4";
  			maklumat_semakan_pegawai = logic.maklumat_semakan_pegawai((String) session.getAttribute("_ekptg_user_id"));
  			this.context.put("maklumat_semakan_pegawai", maklumat_semakan_pegawai);
  			maklumat_semakan = logic.maklumat_semakan(getParam("id_pembatalan"));
  			this.context.put("maklumat_semakan", maklumat_semakan);
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
  			this.context.put("tajuk_header","MAKLUMAT WARTA");     		
  		}
		
         else if ("Siasatan".equals(main_command)){
        	this.context.put("tambah_kehadiran", "");
        	if ("Senarai".equals(sub_command))
   			{	if ("View".equals(subminor_command))
   				{   				
   				this.context.put("tajuk_header","SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikSetSiasatan.jsp";	
   				}
   			//list_siasatan_penarikan = logic.list_siasatan_penarikan(getParam("id_pembatalan"));
			//this.context.put("list_siasatan_penarikan",list_siasatan_penarikan);
   			}
        	else if ("TuanTanah".equals(sub_command))
   			{	
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
        		if ("View".equals(subminor_command))
   				{ 
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
            		if(h.get("TEMPOH_MILIK_TANAH").toString().equals("") && h.get("CARA_MILIK").toString().equals("") && h.get("HARGA_BELI").toString().equals("")
            				&& h.get("BEBANAN").toString().equals("") && h.get("KETERANGAN_TUAN_TANAH").toString().equals("") && h.get("JENIS_TANAMAN").toString().equals("")
            				&& h.get("JENIS_BANGUNAN").toString().equals("") && h.get("FLAG_PECAH_SEMPADAN").toString().equals("") && h.get("TARIKH_PECAH_SEMPADAN").toString().equals("")
            				&& h.get("FLAG_TUKAR_SYARAT").toString().equals("") && h.get("TARIKH_TUKAR_SYARAT").toString().equals(""))
            		{
            			Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PENARIKANBALIK").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
            			
            			this.context.put("readmode", "edit");   
            		}
            		else{
            		this.context.put("readmode", "view");   
            		}      		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        	    updateTuanTanah(session);
        		this.context.put("readmode", "view");        		
   				} 
        		else if ("Hapus".equals(subminor_command))
   				{
        	    updateTuanTanahHapus(session);
        		this.context.put("readmode", "edit");        		
   				}         		
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);	
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
	   			
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraTuanTanah.jsp";	
   			}
        	else if ("Agensi".equals(sub_command))
   			{	
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{   
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("KETERANGAN_AGENSI").toString().equals("") && h.get("KETERANGAN_JURUNILAI").toString().equals(""))
            		{
        				
        				Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PENARIKANBALIK").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
        				this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		updateAgensi(session);
        		this.context.put("readmode", "view");        		
   				} 
        		else if ("Hapus".equals(subminor_command))
   				{
        		updateAgensiHapus(session);
        		this.context.put("readmode", "edit");        		
   				} 
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);
	   			
	   			
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraAgensi.jsp";	
   			}
        	
        	else if ("Kerosakan".equals(sub_command))
   			{	
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{   
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("KEROSAKAN_TANAH").toString().equals("") && h.get("KOS_DITANGGUNG").toString().equals(""))
            		{
        				
        				Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PENARIKANBALIK").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
        				this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		updateKerosakan(session);
        		this.context.put("readmode", "view");        		
   				} 
        		else if ("Hapus".equals(subminor_command))
   				{
        		updateKerosakanHapus(session);
        		this.context.put("readmode", "edit");        		
   				} 
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);
	   			
	   			
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraNilaian.jsp";	
   			}
        	else if ("Tuntutan".equals(sub_command))
   			{       		
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{   
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("TUNTUTAN_TUANTNH").toString().equals("") && h.get("TUNTUTAN_PB_BEBANAN").toString().equals("")
        					&& h.get("TUNTUTAN_PB_TDKDAFTAR").toString().equals("") && h.get("TUNTUTAN_PB_LAIN").toString().equals(""))
            		{
        				Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PENARIKANBALIK").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
        				this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		updateTuntutan(session);
        		this.context.put("readmode", "view");        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        		updateTuntutanHapus(session);
        		this.context.put("readmode", "edit");        		
   				}    
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);
	   			
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraTuntutan.jsp";	
   			}
        	
        	else if ("Bantahan".equals(sub_command))
   			{       		
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("BANTAHAN_LAIN").toString().equals("") && h.get("BANTAHAN_AGENSI").toString().equals("")
        					&& h.get("BANTAHAN_TUANTNH").toString().equals(""))
            		{
        				
        				Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PENARIKANBALIK").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
        				this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		updateBantahan(session);
        		this.context.put("readmode", "view");        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        		updateBantahanHapus(session);
        		this.context.put("readmode", "edit");        		
   				}    
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraBantahan.jsp";	
   			}
        	
        	else if ("Nilaian".equals(sub_command))
   			{ 
        	
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("HARGA_SEUNIT_SO").toString().equals("") && h.get("UNIT_HARGA_SO").toString().equals("") && h.get("HARGA_PASARAN_SO").toString().equals("") && h.get("ID_TANAH").toString().equals("")
        					&& h.get("BAYAR_PENJEJASAN").toString().equals("") && h.get("BAYAR_PECAHPISAH").toString().equals("") && h.get("BAYAR_NAIK_NILAISO").toString().equals("")
        					&& h.get("HARGA_SEUNIT_JPPH").toString().equals("") && h.get("UNIT_HARGA").toString().equals("") && h.get("HARGA_PASARAN").toString().equals("")
        					&& h.get("AMAUN_PENJEJASAN_JPPH").toString().equals("") && h.get("AMAUN_PECAHPISAH_JPPH").toString().equals("") && h.get("NAIK_NILAI_JPPH").toString().equals(""))
            		{
        				Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PENARIKANBALIK").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
        				
        				this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		
        			if(getParam("id_tanah") == "" )
        			{
        				addNilaian(session);
        			}
        			else
        			{
        				updateNilaian(session);
        			}
        			
        		this.context.put("readmode", "view");        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        		logic.deleteTanahNilaian(getParam("id_tanah"));
        		this.context.put("readmode", "edit");        		
   				}    
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);	
	   			
	   		
	   			
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraNilaian.jsp";	
   			}
        	
        	else if ("Status_Siasatan".equals(sub_command))
   			{       		
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("ID_PEGAWAI_SIASATAN").toString().equals("") && h.get("ULASAN_SIASATAN").toString().equals(""))
            		{this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        			
        			
        			updateStatusSiasatan(session);
        			if (bolehsimpan.equals("yes")) 
					{
        			logic.delete_subsiasatan(getParam("id_siasatan"));
        			
					}
        			
        		    String[] txtUlasanTangguh = this.request.getParameterValues("txtUlasanTangguh");
        			if (txtUlasanTangguh != null ) {
    					for (int i = 0; i < txtUlasanTangguh.length; i++) {
    						
    							if (bolehsimpan.equals("yes")) 
    							{
    								if (bolehsimpan.equals("yes") && getParam("socStatusSiasatan").equals("7")) 
    								{    								
    								logic.simpan_subsiasatan(txtUlasanTangguh[i],getParam("id_siasatan"),"ALASAN_TANGGUH",(String) session.getAttribute("_ekptg_user_id"));
    								}
    							}
    						}
    					}
        			
        			if (bolehsimpan.equals("yes")) 
					{
						if (bolehsimpan.equals("yes") && getParam("socStatusSiasatan").equals("7")) 
						{ 
        			
        			if(getParam("id_siasatanX").equals(""))
       		    	{
       		    		if(bolehsimpan=="yes")
    					{
       		    		addMaklumatSiasatanX(session);
    					}
       		    	}
       		    	else
       		    	{
       		    		updateMaklumatSiasatanX(session);
       		    		this.context.put("readmode", "view");       		    		
       		    						
       		    	}
       		    	    maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
    					this.context.put("maklumat_hakmilik",maklumat_hakmilik);
						}
						else
						{
							if(!getParam("id_siasatanX").equals(""))
			    			{
			    			logic.deleteSiasatan(getParam("id_siasatanX"));	
			    			}							
						}
						
					}
        			
        			
        		this.context.put("readmode", "view");        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        	    updateStatusSiasatanHapus(session);
        	    if(!getParam("id_siasatanX").equals(""))
    			{
    			logic.deleteSiasatan(getParam("id_siasatanX"));	
    			}
        		this.context.put("readmode", "edit");        		
   				} 
        		else if ("getBandar".equals(subminor_command))
				{ 
        			updateStatusSiasatan(session);
        			if (bolehsimpan.equals("yes")) 
					{
        			logic.delete_subsiasatan(getParam("id_siasatan"));	
					}
        			
        		    String[] txtUlasanTangguh = this.request.getParameterValues("txtUlasanTangguh");
        			if (txtUlasanTangguh != null ) {
    					for (int i = 0; i < txtUlasanTangguh.length; i++) {
    						
    							if (bolehsimpan.equals("yes")) 
    							{
    								if (bolehsimpan.equals("yes") && getParam("socStatusSiasatan").equals("7")) 
    								{
    								
    								logic.simpan_subsiasatan(txtUlasanTangguh[i],getParam("id_siasatan"),"ALASAN_TANGGUH",(String) session.getAttribute("_ekptg_user_id"));
    								}
    							}
    						}
    					}
        			
        			this.context.put("txtNoKesX",getParam("txtNoKes"));
       				this.context.put("txtNoSiasatanX",getParam("txtNoSiasatan"));
       				this.context.put("txtTempatSiasatanX",getParam("txtNoSiasatan"));
       				this.context.put("txtPoskodX",getParam("txtPoskod"));
       				this.context.put("txtAlamat1X",getParam("txtAlamat1"));
       				this.context.put("txtAlamat2X",getParam("txtAlamat2"));
       				this.context.put("txtAlamat3X",getParam("txtAlamat3"));
       				this.context.put("socNegeriSiasatanX",getParam("socNegeriSiasatan"));      				
       				this.context.put("bil_siasatanX",getParam("bil_siasatan"));
       				this.context.put("socBandarSiasatanX",getParam("socBandarSiasatan"));
       				this.context.put("socStatusSiasatanX",getParam("socStatusSiasatanX"));
       				this.context.put("txdTarikhSiasatanX",getParam("txdTarikhSiasatan"));
       				this.context.put("txtMasaSiasatanX",getParam("txtMasaSiasatan"));
       				this.context.put("txtTkhAkhirNotisX",getParam("txtTkhAkhirNotis"));
       				this.context.put("txtAlasanX",getParam("txtAlasan"));
       				this.context.put("jeniswaktuX",getParam("jeniswaktu"));
       				this.context.put("id_siasatanX",getParam("id_siasatanX"));       				
       				this.context.put("socPegawaiSiasatan",getParam("socPegawaiSiasatan"));
       				this.context.put("socStatusSiasatan",getParam("socStatusSiasatan"));
       				this.context.put("txtUlasanSiasatan",getParam("txtUlasanSiasatan"));
        			
				this.context.put("readmode", "edit");
				this.context.put("record_siasatan","yes");
				if(!getParam("socNegeriSiasatan").equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(getParam("socNegeriSiasatan"));
				this.context.put("list_bandar",list_bandar);
				}	
				this.context.put("socBandarSiasatanX","");
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				}
        		       		
        		context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);
	   			
	   			if (!subminor_command.equals("getBandar"))
				{ 
        	    myLogger.info("TEST!!!:"+subminor_command);
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_pembatalan"));	
            	this.context.put("tarikh_akhir_tampalX",tarikh_akhir_tampal);  				
   				this.context.put("nk_add",""); 
   				
   				Hashtable h2 = (Hashtable) maklumat_siasatan.get(0);
   				
   				maklumat_siasatanX = logic.maklumat_siasatanX(getParam("id_pembatalan"),Integer.parseInt(h2.get("BIL_SIASATAN").toString())+1);
	   			this.context.put("maklumat_siasatanX",maklumat_siasatanX);
   					
	   			if(maklumat_siasatanX.size()>0)
	   			{	
	   			Hashtable h1 = (Hashtable) maklumat_siasatanX.get(0);
	   			this.context.put("id_siasatanX",h1.get("ID_SIASATAN").toString()); 
	   			this.context.put("txtNoKesX",h1.get("NO_KES").toString());   				
   				this.context.put("txtNoSiasatanX",h1.get("NO_SIASATAN").toString());   				
   				this.context.put("txtTempatSiasatanX",h1.get("TEMPAT").toString());
   				this.context.put("txtPoskodX",h1.get("POSKOD").toString());
   				this.context.put("txtAlamat1X",h1.get("ALAMAT1").toString());
   				this.context.put("txtAlamat2X",h1.get("ALAMAT2").toString());
   				this.context.put("txtAlamat3X",h1.get("ALAMAT3").toString());
   				
   				
   				this.context.put("socNegeriSiasatanX",h1.get("ID_NEGERI").toString());
   				if(!h1.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h1.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 
   				else
   				{
   				this.context.put("list_bandar","");	
   				}   				
   			  			
   				this.context.put("socBandarSiasatanX",h1.get("ID_BANDAR").toString());
   				if(!h1.get("BIL_SIASATAN").toString().equals(""))
   			    {
   				this.context.put("bil_siasatanX",h1.get("BIL_SIASATAN").toString());
   			    }
   			    else
   			    {
   			    this.context.put("bil_siasatanX","");	
   			    }
   				
   				this.context.put("socStatusSiasatanX",h1.get("STATUS_SIASATAN").toString());
   				this.context.put("txdTarikhSiasatanX",h1.get("TARIKH_SIASATAN").toString());
   				this.context.put("txtMasaSiasatanX",h1.get("MASA_SIASATAN").toString());
   				this.context.put("txtTkhAkhirNotisX",h1.get("TARIKH_AKHIR_TAMPAL").toString());   				
   				this.context.put("jeniswaktuX",h1.get("JENIS_WAKTU_SIASATAN").toString());
	   			}
	   			else
	   			{
	   		
	   			Hashtable h1 = (Hashtable) maklumat_siasatan.get(0);
	   			this.context.put("id_siasatanX","");
	   			this.context.put("txtNoKesX",h1.get("NO_KES").toString());   				
   				this.context.put("txtNoSiasatanX",h1.get("NO_SIASATAN").toString());   				
   				this.context.put("txtTempatSiasatanX",h1.get("TEMPAT").toString());
   				this.context.put("txtPoskodX",h1.get("POSKOD").toString());
   				this.context.put("txtAlamat1X",h1.get("ALAMAT1").toString());
   				this.context.put("txtAlamat2X",h1.get("ALAMAT2").toString());
   				this.context.put("txtAlamat3X",h1.get("ALAMAT3").toString());
   				this.context.put("socNegeriSiasatanX",h1.get("ID_NEGERI").toString());
   				
   				
   				this.context.put("socNegeriSiasatanX",h1.get("ID_NEGERI").toString());
   				if(!h1.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h1.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 
   				else
   				{
   				this.context.put("list_bandar","");	
   				}   				
   			    list_siasatan = logic.list_siasatan(getParam("id_pembatalan"),getParam("id_hakmilik"));   				
   				this.context.put("socBandarSiasatanX",h1.get("ID_BANDAR").toString());
			   			    
   			    if(!h1.get("BIL_SIASATAN").toString().equals(""))
   			    {
   				this.context.put("bil_siasatanX",Integer.parseInt(h1.get("BIL_SIASATAN").toString())+1);
   			    }
   			    else
   			    {
   			    this.context.put("bil_siasatanX","");	
   			    }
   				this.context.put("socStatusSiasatanX","4");
   				this.context.put("txdTarikhSiasatanX",h1.get("TARIKH_SIASATAN").toString());
   				this.context.put("txtMasaSiasatanX",h1.get("MASA_SIASATAN").toString());
   				this.context.put("txtTkhAkhirNotisX",h1.get("TARIKH_AKHIR_TAMPAL").toString());   				
   				this.context.put("jeniswaktuX",h1.get("JENIS_WAKTU_SIASATAN").toString());
	   		
	   			}
	   				
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				       
				}
        		
	   			 
	   			senarai_pihak_penting_pampasan_perintah = logic.senarai_pihak_penting_pampasan_perintah(getParam("id_pembatalan"),getParam("id_siasatan"),getParam("CariPB"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah",senarai_pihak_penting_pampasan_perintah);	
	   			
	   			senarai_pihak_penting_pampasan_perintah_award = logic.senarai_pihak_penting_pampasan_perintah_award(getParam("id_pembatalan"),getParam("id_siasatan"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah_award",senarai_pihak_penting_pampasan_perintah_award);	
	   			
	   			
	   			list_subsiasatan = logic.list_subsiasatan(getParam("id_siasatan"),"ALASAN_TANGGUH");
	   			this.context.put("list_subsiasatan",list_subsiasatan);
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraStatusSiasatan.jsp";	
   			}
        	
        	else if ("Keputusan".equals(sub_command))
   			{  
        		this.context.put("flag_open_award", "");
        		this.context.put("id_siasatan_award", "");
        		this.context.put("id_siasatan_hakmilikpb", ""); 
        		this.context.put("nama_pb","");
        		this.context.put("jenis_nopb","");
        		this.context.put("nopb", "");
        		this.context.put("no_lot", "");
        		this.context.put("id_bahagianbp","");
        		this.context.put("syer_atas","");
        		this.context.put("syer_bawah","");
        		
        	       		
        		
        		
        		
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("ID_PEGAWAI_SIASATAN").toString().equals("") && h.get("ULASAN_PERINTAH").toString().equals(""))
            		{this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{ 
        			
        	    this.context.put("flag_open_award", getParam("flag_open_award"));
        		this.context.put("readmode", "edit");        	
        		senarai_perintah_award = logic.senarai_perintah_award(getParam("id_pembatalan"),getParam("id_siasatan"),getParam("id_siasatan_award"));
        		this.context.put("senarai_perintah_award",senarai_perintah_award);
        		
        		
        		if(!getParam("id_siasatan_hakmilikpb").equals("") && !getParam("id_siasatan_hakmilikpb").equals(null))
        		{
        		        		
        		Hashtable maklumat_pb = logic.maklumat_pb(getParam("id_siasatan_hakmilikpb"));        		
        		this.context.put("nama_pb", maklumat_pb.get("NAMA_PB").toString());
        		this.context.put("jenis_nopb", maklumat_pb.get("KETERANGAN").toString());
        		this.context.put("nopb", maklumat_pb.get("NO_PB").toString());
        		this.context.put("no_lot", maklumat_pb.get("NO_LOT").toString());
        		this.context.put("id_bahagianbp", maklumat_pb.get("ID_BAHAGIANPB").toString());
        		this.context.put("syer_atas",  maklumat_pb.get("SYER_ATAS").toString());
        		this.context.put("syer_bawah",  maklumat_pb.get("SYER_BAWAH").toString());
        		}
        		else
        		{
        			this.context.put("nama_pb","");
            		this.context.put("jenis_nopb","");
            		this.context.put("nopb", "");
            		this.context.put("no_lot", "");
            		this.context.put("id_bahagianbp","");
            		this.context.put("syer_atas","");
            		this.context.put("syer_bawah","");
        		}
        		this.context.put("id_siasatan_award", getParam("id_siasatan_award"));
        		this.context.put("id_siasatan_hakmilikpb", getParam("id_siasatan_hakmilikpb")); 
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        			
        			Db db = null;
		    		String NO_KES_temp = "";
		    		try {
		    		db = new Db();
		    		Statement stmt = db.getStatement();
		    		String sql = " SELECT NO_KES FROM TBLPPTSIASATAN " +
		    				"" +
		    				" WHERE ID_SIASATAN = '"+getParam("id_siasatan")+"'";			
		    		ResultSet rs = stmt.executeQuery(sql);	
		    		myLogger.info("SQL  :"+sql);
		    		while (rs.next()){				
		    			NO_KES_temp = rs.getString("NO_KES");				
		    	    }
		    	    AuditTrail at = new AuditTrail();
		    		at.logActivity("","1",null,session,"UPD","PENARIKAN BALIK SIASATAN AWARD [NO. KES : "+NO_KES_temp+"] KEMASKINI");
		    			
		    		} finally {
		    			if (db != null)
		    				db.close();
		    		}	
		    		
        			String[] id_hakmilik_pb = this.request.getParameterValues("id_hakmilik_pb");
        			String[] id_award = this.request.getParameterValues("id_award");
        			String[] pampasan = this.request.getParameterValues("pampasan");
        			
        			String[] id_status_penerima = this.request.getParameterValues("id_status_penerima");
        			String[] temp_perintah = this.request.getParameterValues("temp_perintah");
        			String[] temp_xhadir = this.request.getParameterValues("temp_xhadir");
        			
        		    String id_award_sementara = ""; 
        			
    				if (id_hakmilik_pb != null) {
    					for (int i = 0; i < id_hakmilik_pb.length; i++) {						
    							if (bolehsimpan.equals("yes")) 
    							{
    								
    								if(!id_award[i].equals(""))
    								{
    									logic.updateAwardPerintah(id_status_penerima[i],id_award[i], id_hakmilik_pb[i], pampasan[i], (String) session.getAttribute("_ekptg_user_id"),temp_xhadir[i],temp_perintah[i]);
        									}
    								else
    								{
    									
    								 								
    								
    								if(id_hakmilik_pb[i].equals(getParam("id_siasatan_hakmilikpb")))
    			    				{
    			    				
    									long id_award_temp = DB.getNextID("TBLPPTAWARD_SEQ");  
    									String id_siasatan = getParam("id_siasatan");
    									id_award_sementara = id_award_temp+""; 
    									logic.addAwardPerintah(id_status_penerima[i],id_siasatan,id_award_temp, id_hakmilik_pb[i], pampasan[i], (String) session.getAttribute("_ekptg_user_id"),temp_xhadir[i],temp_perintah[i]);
            	    							
        							if(getParam("flag_open_award").equals("yes"))
        							{
    			        		    String[] txtUlasanKerosakan = this.request.getParameterValues("txtUlasanKerosakan");
    			        		    String[] txtUlasanKerosakanAward = this.request.getParameterValues("txtUlasanKerosakanAward");    			        		    
    			        			if (txtUlasanKerosakan != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanKerosakan.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanKerosakan[t],txtUlasanKerosakanAward[t],id_award_temp,"BAYAR_KEROSAKAN",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			String[] txtUlasanprosiding = this.request.getParameterValues("txtUlasanprosiding");
    			        		    String[] txtUlasanprosidingAward = this.request.getParameterValues("txtUlasanprosidingAward");    			        		    
    			        			if (txtUlasanKerosakan != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanprosiding.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanprosiding[t],txtUlasanprosidingAward[t],id_award_temp,"BAYAR_PROSIDING",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			
    			        			String[] txtUlasanlainpampasan = this.request.getParameterValues("txtUlasanlainpampasan");
    			        		    String[] txtUlasanlainpampasanAward = this.request.getParameterValues("txtUlasanlainpampasanAward");    			        		    
    			        			if (txtUlasanKerosakan != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanlainpampasan.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanlainpampasan[t],txtUlasanlainpampasanAward[t],id_award_temp,"BAYAR_LAINPAMPASAN",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			
    			        			String[] txtUlasanLain = this.request.getParameterValues("txtUlasanLain");
    			        		    String[] txtUlasanLainAward = this.request.getParameterValues("txtUlasanLainAward");    			        		    
    			        			if (txtUlasanKerosakan != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanLain.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanLain[t],txtUlasanLainAward[t],id_award_temp,"BAYAR_LAIN",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			
    			    				}
        							
            		        		
        							senarai_perintah_award = logic.senarai_perintah_award(getParam("id_pembatalan"),getParam("id_siasatan"),id_award_temp+"");
    				        		this.context.put("senarai_perintah_award",senarai_perintah_award);
    				        		
    				        		this.context.put("id_siasatan_award", id_award_temp);
            		        		this.context.put("id_siasatan_hakmilikpb", getParam("id_siasatan_hakmilikpb"));
    				        		
    			    				}
    								
    								
    								}
    							}
    						}
    					
    					
    					
    					    this.context.put("flag_open_award", getParam("flag_open_award"));
    		        	
    		        		if(!getParam("id_siasatan_hakmilikpb").equals("") && !getParam("id_siasatan_hakmilikpb").equals(null))
    		        		{
    		        			Hashtable maklumat_pb = logic.maklumat_pb(getParam("id_siasatan_hakmilikpb"));        		
    		            		this.context.put("nama_pb", maklumat_pb.get("NAMA_PB").toString());
    		            		this.context.put("jenis_nopb", maklumat_pb.get("KETERANGAN").toString());
    		            		this.context.put("nopb", maklumat_pb.get("NO_PB").toString());
    		            		this.context.put("no_lot", maklumat_pb.get("NO_LOT").toString());
    		            		this.context.put("id_bahagianbp", maklumat_pb.get("ID_BAHAGIANPB").toString());
    		            		this.context.put("syer_atas",  maklumat_pb.get("SYER_ATAS").toString());
    		            		this.context.put("syer_bawah",  maklumat_pb.get("SYER_BAWAH").toString());
    		        		}
    		        		else
    		        		{
    		        			this.context.put("nama_pb","");
    		            		this.context.put("jenis_nopb","");
    		            		this.context.put("nopb", "");
    		            		this.context.put("no_lot", "");
    		            		this.context.put("id_bahagianbp","");
    		            		this.context.put("syer_atas","");
    		            		this.context.put("syer_bawah","");
    		        		}
    		        		 
    					    					
    					}
    				
    				if(getParam("flag_open_award").equals("yes") && !getParam("id_siasatan_award").equals(""))
    				{
    					id_award_sementara = getParam("id_siasatan_award"); 
    				if (bolehsimpan.equals("yes")) 
					{
        			logic.delete_subaward(getParam("id_siasatan_award"));	
					}
        			
        		    String[] txtUlasanKerosakan = this.request.getParameterValues("txtUlasanKerosakan");
        		    String[] txtUlasanKerosakanAward = this.request.getParameterValues("txtUlasanKerosakanAward");
        		    
        			if (txtUlasanKerosakan != null ) {
    					for (int i = 0; i < txtUlasanKerosakan.length; i++) {
    						
    							if (bolehsimpan.equals("yes")) 
    							{
    								
    								logic.simpan_subAward(txtUlasanKerosakan[i],txtUlasanKerosakanAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_KEROSAKAN",(String) session.getAttribute("_ekptg_user_id"));
    								
    							}
    						}
    					} 
        			
        			
        			String[] txtUlasanprosiding = this.request.getParameterValues("txtUlasanprosiding");
         		    String[] txtUlasanprosidingAward = this.request.getParameterValues("txtUlasanprosidingAward");
         		    
         			if (txtUlasanprosiding != null ) {
     					for (int i = 0; i < txtUlasanprosiding.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanprosiding[i],txtUlasanprosidingAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_PROSIDING",(String) session.getAttribute("_ekptg_user_id"));
     								
     							}
     						}
     					}
        			
         			
         			String[] txtUlasanlainpampasan = this.request.getParameterValues("txtUlasanlainpampasan");
         		    String[] txtUlasanlainpampasanAward = this.request.getParameterValues("txtUlasanlainpampasanAward");
         		    
         			if (txtUlasanlainpampasan != null ) {
     					for (int i = 0; i < txtUlasanlainpampasan.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanlainpampasan[i],txtUlasanlainpampasanAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_LAINPAMPASAN",(String) session.getAttribute("_ekptg_user_id"));
     								
     							}
     						}
     					}
         			
         			String[] txtUlasanLain = this.request.getParameterValues("txtUlasanLain");
         		    String[] txtUlasanLainAward = this.request.getParameterValues("txtUlasanLainAward");
         		    
         			if (txtUlasanLain != null ) {
     					for (int i = 0; i < txtUlasanLain.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanLain[i],txtUlasanLainAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_LAIN",(String) session.getAttribute("_ekptg_user_id"));
     								
     							}
     						}
     					}
         			
         			
        			this.context.put("id_siasatan_award", getParam("id_siasatan_award"));
	        		this.context.put("id_siasatan_hakmilikpb", getParam("id_siasatan_hakmilikpb"));
        			
        			senarai_perintah_award = logic.senarai_perintah_award(getParam("id_pembatalan"),getParam("id_siasatan"),getParam("id_siasatan_award"));
            		this.context.put("senarai_perintah_award",senarai_perintah_award);
    				}     			
        			
        	    updateKeputusan(session);
        	    if (bolehsimpan.equals("yes")) 
				{				
				logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
						(String) session.getAttribute("_ekptg_user_id"),"16102707","add",getParam("id_fail"));
				}	
        		this.context.put("readmode", "view"); 
        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        			updateKeputusanHapus(session);
        		this.context.put("readmode", "edit");        		
   				} 
        		
        		else if ("Hapus_Award".equals(subminor_command))
   				{ 
        		
        		logic.delete_siasatanaward(getParam("id_siasatan_award"));
        			
        	    this.context.put("flag_open_award", getParam("flag_open_award"));
        		this.context.put("readmode", "view");        	
        		senarai_perintah_award = logic.senarai_perintah_award(getParam("id_permohonan"),getParam("id_siasatan"),getParam("id_siasatan_award"));
        		this.context.put("senarai_perintah_award",senarai_perintah_award);        		
        		
        		if(!getParam("id_siasatan_hakmilikpb").equals("") && !getParam("id_siasatan_hakmilikpb").equals(null))
        		{
        		Hashtable maklumat_pb = logic.maklumat_pb(getParam("id_siasatan_hakmilikpb"));        		
        		this.context.put("nama_pb", maklumat_pb.get("NAMA_PB").toString());
        		this.context.put("jenis_nopb", maklumat_pb.get("KETERANGAN").toString());
        		this.context.put("nopb", maklumat_pb.get("NO_PB").toString());
        		this.context.put("no_lot", maklumat_pb.get("NO_LOT").toString());
        		this.context.put("id_bahagianbp", maklumat_pb.get("ID_BAHAGIANPB").toString());
        		this.context.put("syer_atas",  maklumat_pb.get("SYER_ATAS").toString());
        		this.context.put("syer_bawah",  maklumat_pb.get("SYER_BAWAH").toString());
        		}       		
        		 
   				}
        		
        		else if ("Open_Award".equals(subminor_command))
   				{
        		
        		
        			
        			this.context.put("txtUlasanPerintah", getParam("txtUlasanPerintah"));
        			this.context.put("socAwardKepada", getParam("socAwardKepada"));
        			this.context.put("nilai_seunit", getParam("nilai_seunit"));
        			this.context.put("socUnit", getParam("socUnit"));
        		//	updateKeputusan(session);
        			
        			if(getParam("readmode").equals("edit"))
            		{
            		updateKeputusan(session);
            		}
        		
        	    this.context.put("flag_open_award", "yes");
        		this.context.put("readmode", getParam("readmode"));    
        		
        		
        		senarai_perintah_award = logic.senarai_perintah_award(getParam("id_pembatalan"),getParam("id_siasatan"),getParam("id_siasatan_award"));
        		this.context.put("senarai_perintah_award",senarai_perintah_award);
        		
        		        		
        		Hashtable maklumat_pb = logic.maklumat_pb(getParam("id_siasatan_hakmilikpb"));        		
        		this.context.put("nama_pb", maklumat_pb.get("NAMA_PB").toString());
        		this.context.put("jenis_nopb", maklumat_pb.get("KETERANGAN").toString());
        		this.context.put("nopb", maklumat_pb.get("NO_PB").toString());
        		this.context.put("no_lot", maklumat_pb.get("NO_LOT").toString());
        		this.context.put("id_bahagianbp", maklumat_pb.get("ID_BAHAGIANPB").toString());
        		this.context.put("syer_atas",  maklumat_pb.get("SYER_ATAS").toString());
        		this.context.put("syer_bawah",  maklumat_pb.get("SYER_BAWAH").toString());
        		
        		this.context.put("id_siasatan_award", getParam("id_siasatan_award"));
        		this.context.put("id_siasatan_hakmilikpb", getParam("id_siasatan_hakmilikpb")); 
        		
   				} 
        		
        		
        		
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan); 
	   			senarai_pihak_penting_pampasan_perintah = logic.senarai_pihak_penting_pampasan_perintah(getParam("id_pembatalan"),getParam("id_siasatan"),getParam("CariPB"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah",senarai_pihak_penting_pampasan_perintah);	
	   		
	   			senarai_pihak_penting_pampasan_perintah_award = logic.senarai_pihak_penting_pampasan_perintah_award(getParam("id_pembatalan"),getParam("id_siasatan"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah_award",senarai_pihak_penting_pampasan_perintah_award);	
	   			
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikInfoBicaraKeputusan.jsp";	
   			}
        	
        	else if ("Kehadiran".equals(sub_command))
   			{ 
        	this.context.put("view_pb", "");      
        	this.context.put("tambah_kehadiran_negeri", "");		
        	if ("View".equals(subminor_command))   			
   			{
   						
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			} 
        	
        	else if ("Papar".equals(subminor_command))   			
   			{
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "view");	
        		maklumat_kehadiran = logic.maklumat_kehadiran(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				}   				
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			} 
        	
        	else if ("tambah".equals(subminor_command))   			
   			{
        		this.context.put("tambah_kehadiran", "yes");
        		this.context.put("tambah_kehadiran_negeri", "yes");
        		this.context.put("tambah_kehadiran_wakil", "");
        		this.context.put("tambah_kehadiran_negeri_wakil", "");
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");        		
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");     				
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			} 
        	else if ("tambah_wakil".equals(subminor_command))   			
   			{
        		this.context.put("tambah_kehadiran_wakil", "yes");
        		this.context.put("tambah_kehadiran_negeri_wakil", "yes");
        		this.context.put("tambah_kehadiran", "yes");
        		this.context.put("tambah_kehadiran_negeri", "yes");
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");        		
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN"); 				
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			} 
        	else if ("Simpan".equals(subminor_command))   			
   			{       		
        		if(!getParam("id_hakmilikpb").equals(""))
        		{
        		if(!getParam("id_kehadiran").equals(""))
        		{ logic.deleteKehadiranPB(getParam("id_kehadiran")); }
        		if(getParam("txtPBhadir").equals("1")){
				logic.updateKehadiran(getParam("id_hakmilikpb"),getParam("id_siasatan"),(String) session.getAttribute("_ekptg_user_id"));  
        		}
				logic.updatePBKehadiran(getParam("id_pb"),getParam("txtNamaPB"),getParam("txtNoPB"),getParam("socJenisNOPB"),getParam("txtNamaPBKP"),(String) session.getAttribute("_ekptg_user_id"));									
				logic.updatePBKehadiranDetails(getParam("id_hakmilikpb"),getParam("socJenisPB"),getParam("txtNoAkaun"),getParam("socJenisBank"),getParam("txtAlamat1PB"),getParam("txtAlamat2PB"),getParam("txtAlamat3PB"),getParam("txtPoskodPB"),getParam("socNegeri"),getParam("socBandar"),(String) session.getAttribute("_ekptg_user_id"),getParam("txtNoHP"),getParam("txtNoTel"),getParam("txtNamaBank"),getParam("txtKeteranganPB"));									
	       		
        		maklumat_kehadiran = logic.maklumat_kehadiran(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 
   				this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "view");      
        		}
        		else
        		{
        			if (bolehsimpan.equals("yes")) 
					{
        	logic.addPBKehadiran(getParam("id_pb"),getParam("txtNamaPB"),getParam("txtNoPB"),getParam("socJenisNOPB"),getParam("txtNamaPBKP"),(String) session.getAttribute("_ekptg_user_id"),
        	getParam("id_hakmilikpb"),getParam("socJenisPB"),getParam("txtNoAkaun"),getParam("socJenisBank"),getParam("txtAlamat1PB"),getParam("txtAlamat2PB"),getParam("txtAlamat3PB"),getParam("txtPoskodPB"),
        	getParam("socNegeri"),getParam("socBandar"),(String) session.getAttribute("_ekptg_user_id"),getParam("txtNoHP"),getParam("txtNoTel"),getParam("id_siasatan"),getParam("id_hakmilik"),getParam("txtPBhadir"),getParam("txtNamaBank"),getParam("txtKeteranganPB"));									
        	this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
					}
        				
        		}
   				
   				
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			} 
        	else if ("DeleteTurutHadir".equals(subminor_command))   			
   			{	this.context.put("view_pb", "");	
        		this.context.put("readmode", "");
        		if(getParam("id_kehadiran")!="" && getParam("id_kehadiran")!= null)
        		{
        		logic.deleteKehadiranPB(getParam("id_kehadiran"));
        		}
        		logic.deleteMaklumatPB(getParam("id_pb"));        	
        		logic.deleteHakmilikPB(getParam("id_hakmilikpb")); 
        		this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			}
        	else if ("Kemaskini".equals(subminor_command))   			
   			{
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");	
        		maklumat_kehadiran = logic.maklumat_kehadiran(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 	
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			}
        	else if ("batal".equals(subminor_command))   			
   			{
        		
        		
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");        		
   					
        		if(getParam("id_hakmilikpb")!="" && getParam("id_hakmilikpb")!= null)
        		{
        		maklumat_kehadiran = logic.maklumat_kehadiran(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 
        		}
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
   			}
        	
        	else if ("getBandar".equals(subminor_command))
			{
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
			if(!getParam("socNegeri").equals(""))
			{	
			list_bandar = logic.getListBandarByNegeri(getParam("socNegeri"));
			this.context.put("list_bandar",list_bandar);
			}	
			this.context.put("socBandar","");			
			if(getParam("id_hakmilikpb").equals(""))
			{
				this.context.put("tambah_kehadiran_negeri", "yes");	
			}
			this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");	
			vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
			}
        	
   			else if ("Update_kehadiran".equals(subminor_command))
			{     	 
   				logic.deleteKehadiran(getParam("id_siasatan"));				
				String[] ids1 = this.request.getParameterValues("ids1");				
				if (ids1 != null) {
					myLogger.info("ids1 length"+ids1.length);
					
					for (int i = 0; i < ids1.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateKehadiran(ids1[i],getParam("id_siasatan"),(String) session.getAttribute("_ekptg_user_id"));  									
							}
					}
				 }
							
				String[] ids2 = this.request.getParameterValues("ids2");				
				if (ids2 != null) {
					myLogger.info("ids2 length2"+ids2.length);
					
					for (int i = 0; i < ids2.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateKehadiran(ids2[i],getParam("id_siasatan"),(String) session.getAttribute("_ekptg_user_id"));  									
							}
					}
				 }
				
				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				vm = "app/ppt/frmPenarikanBalikSiasatanKehadiran.jsp";	
			}  			
   			context.put("id_siasatan",getParam("id_siasatan"));
   			list_kehadiran = logic.list_kehadiran(getParam("id_siasatan"));
   			this.context.put("list_kehadiran",list_kehadiran);
   			list_kehadiran_th = logic.list_kehadiran_th(getParam("id_siasatan"));
   			this.context.put("list_kehadiran_th",list_kehadiran_th);
   			list_check_kehadiran = logic.list_check_kehadiran(getParam("id_siasatan"));
   			this.context.put("list_check_kehadiran",list_check_kehadiran);
   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   			this.context.put("maklumat_siasatan",maklumat_siasatan);	
   			list_jenispb = logic.list_jenispb();
   			this.context.put("list_jenispb",list_jenispb);
   			list_jenisnopb = logic.list_jenisnopb();
   			this.context.put("list_jenisnopb",list_jenisnopb);  
   			list_bank = logic.list_bank();
   			this.context.put("list_bank",list_bank); 
   			list_negeri = logic.getListnegeri();
   		   	context.put("list_negeri",list_negeri);

   			}
        	
        	else if ("RecordSiasatan".equals(sub_command))
   			{	
        		this.context.put("nk_add","");	
        		this.context.put("record_siasatan","");	
        		if ("View".equals(subminor_command))
   				{        	    
   				this.context.put("readmode", "edit");
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");    				
   				}
        		else if ("tambah".equals(subminor_command))
   				{ 
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_pembatalan"));	
        		this.context.put("tarikh_akhir_tampal",tarikh_akhir_tampal);	
   				this.context.put("readmode", "edit");
   				this.context.put("nk_add","yes");
   				this.context.put("record_siasatan","yes");	
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");   				
   				}
        		else if ("ulang".equals(subminor_command))
   				{
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_pembatalan"));	
            	this.context.put("tarikh_akhir_tampal",tarikh_akhir_tampal);	
   				this.context.put("readmode", "edit");
   				this.context.put("nk_add","");   				
   				this.context.put("txtNoKes",getParam("txtNoKes"));
   				this.context.put("txtNoSiasatan",getParam("txtNoSiasatan"));
   				this.context.put("txtTempatSiasatan",getParam("txtNoSiasatan"));
   				this.context.put("txtPoskod",getParam("txtPoskod"));
   				this.context.put("txtAlamat1",getParam("txtAlamat1"));
   				this.context.put("txtAlamat2",getParam("txtAlamat2"));
   				this.context.put("txtAlamat3",getParam("txtAlamat3"));
   				this.context.put("socNegeriSiasatan",getParam("socNegeriSiasatan"));
   				if(!getParam("socNegeriSiasatan").equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(getParam("socNegeriSiasatan"));
				this.context.put("list_bandar",list_bandar);
				} 
   				else
   				{
   					this.context.put("list_bandar","");	
   				}
   				
   			    list_siasatan = logic.list_siasatan(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("bil_siasatan",list_siasatan.size()+1);
   				this.context.put("socBandarSiasatan",getParam("socBandarSiasatan"));
   				this.context.put("socStatusSiasatan","");
   				this.context.put("txdTarikhSiasatan",getParam("txdTarikhSiasatan"));
   				this.context.put("txtMasaSiasatan",getParam("txtMasaSiasatan"));
   				this.context.put("txtTkhAkhirNotis",getParam("txtTkhAkhirNotis"));
   				this.context.put("txtAlasan",getParam("txtAlasan"));
   				this.context.put("jeniswaktu",getParam("jeniswaktu"));
   				
   				this.context.put("record_siasatan","yes");	
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");   				
   				}        		
        		else if ("Papar".equals(subminor_command))
   				{        	    		
   				this.context.put("readmode", "view");   				
   				this.context.put("record_siasatan","yes");   				
   				maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   				this.context.put("maklumat_siasatan",maklumat_siasatan);
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);   				
   				Hashtable h = (Hashtable) maklumat_siasatan.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				}  				
   				this.context.put("tajuk_header","SENARAI SIASATAN");    				
   				}
        		
        		else if ("kemaskini".equals(subminor_command))
   				{        	    		
   				this.context.put("readmode", "edit");   				
   				this.context.put("record_siasatan","yes");   				
   				maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   				this.context.put("maklumat_siasatan",maklumat_siasatan);
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);   				
   				Hashtable h = (Hashtable) maklumat_siasatan.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				}  				
   				this.context.put("tajuk_header","SENARAI SIASATAN");    				
   				}
        		
   		    	else if ("getBandar".equals(subminor_command))
				{ 
   		    		String key = "";
   					String value = "";
   					Enumeration allparam = request.getParameterNames();
   					for (; allparam.hasMoreElements();) {
   						key = (String) allparam.nextElement();
   						value = request.getParameter(key);
   						this.context.put(key, value);
   					}   						
				this.context.put("readmode", "edit");
				this.context.put("record_siasatan","yes");
				if(!getParam("socNegeriSiasatan").equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(getParam("socNegeriSiasatan"));
				this.context.put("list_bandar",list_bandar);
				}	
				this.context.put("socBandarSiasatan","");
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				this.context.put("tajuk_header","SENARAI SIASATAN"); 				
				}
        		
   		    	else if ("Simpan".equals(subminor_command))
				{ 
   		    		
   		    	if(getParam("id_siasatan").equals(""))
   		    	{
   		    		if(bolehsimpan=="yes")
					{
   		    		addMaklumatSiasatan(session);
   		    		
   		    		
	 				
	 							if (bolehsimpan.equals("yes")) 
	 							{
	 							
	 							logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
	 									(String) session.getAttribute("_ekptg_user_id"),"16102706","add",getParam("id_fail"));
	 							
	 							}									
	 													
	 					
					}
   		    	}
   		    	else
   		    	{
   		    		updateMaklumatSiasatan(session);
   		    		this.context.put("readmode", "view");
   		    		this.context.put("record_siasatan","yes");
   		    		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   	   				this.context.put("maklumat_siasatan",maklumat_siasatan);			
   	   				Hashtable h = (Hashtable) maklumat_siasatan.get(0);				
   	   				if(!h.get("ID_NEGERI").toString().equals(""))
   					{	
   					list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
   					this.context.put("list_bandar",list_bandar);
   					}   					
   		    	 }
   		    	    maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
					this.context.put("maklumat_hakmilik",maklumat_hakmilik);
					this.context.put("tajuk_header","SENARAI SIASATAN");  		    	
				}
   		 	else if ("batal".equals(subminor_command))
			{ 	    		
		    	if(getParam("id_siasatan").equals(""))
		    	{
		    		this.context.put("readmode", "edit");
		    		this.context.put("record_siasatan","yes");
		    	}
		    	else
		    	{		    		
		    		this.context.put("readmode", "edit");
		    		this.context.put("record_siasatan","yes");
		    		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   				this.context.put("maklumat_siasatan",maklumat_siasatan);			
	   				Hashtable h = (Hashtable) maklumat_siasatan.get(0);				
	   				if(!h.get("ID_NEGERI").toString().equals(""))
					{	
					list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
					this.context.put("list_bandar",list_bandar);
					} 				
		    	 }
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				this.context.put("tajuk_header","SENARAI SIASATAN"); 		    	
			}
   		    else if ("Hapus".equals(subminor_command))
			{   
   		    	logic.delete_subsiasatan(getParam("id_siasatan"));
				logic.deleteSiasatan(getParam("id_siasatan"));
				if (bolehsimpan.equals("yes")) 
					{
					
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102706","hapus",getParam("id_fail"));
					
					}		
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				this.context.put("tajuk_header","SENARAI SIASATAN"); 
			}   			
			else if ("Hapus_beramai".equals(subminor_command))
			{     	 					
				
				String[] ids1 = this.request.getParameterValues("ids1");
				if (ids1 != null) {
					for (int i = 0; i < ids1.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.deleteSiasatan(ids1[i]);  									
							}
					}
			} 
				
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				this.context.put("tajuk_header","SENARAI SIASATAN"); 	
			}	
        		
        		list_negeri = logic.getListnegeri();
        	   	context.put("list_negeri",list_negeri);

        		
        		list_siasatan = logic.list_siasatan(getParam("id_pembatalan"),getParam("id_hakmilik"));
    			this.context.put("list_siasatan",list_siasatan);

        		
        		
   		    vm = "app/ppt/frmPenarikanBalikSetSenaraiSiasatan.jsp";
   			}        	 

      		context.put("id_permohonan",getParam("id_permohonan"));
      		context.put("id_pembatalan",getParam("id_pembatalan"));	
      		context.put("id_hakmilik",getParam("id_hakmilik"));	
      		jenis_button = "5";
   			header = logic.content_header(getParam("id_permohonan"));   			
   			this.context.put("header",header);		
   			//list_negeri = logic.getListnegeri();
   			//context.put("list_negeri",list_negeri);	
   			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
   			this.context.put("maklumat_pembatalan", maklumat_pembatalan); 
   			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_pembatalan"));
   			this.context.put("maklumat_penyediaan", maklumat_penyediaan); 	
   			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
   			this.context.put("maklumat_keputusan", maklumat_keputusan); 
   			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
   			senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
			this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
			//senarai_pihak_penting = logic.senarai_pihak_penting(getParam("id_permohonan"));
			//this.context.put("senarai_pihak_penting",senarai_pihak_penting);			
			//list_siasatan = logic.list_siasatan(getParam("id_pembatalan"),getParam("id_hakmilik"));
			//this.context.put("list_siasatan",list_siasatan);
			//list_bandar_all = logic.list_bandar_all();
			//this.context.put("list_bandar_all",list_bandar_all);			
			tempat_tampal = logic.tempat_tampal(getParam("id_permohonan"));
			this.context.put("tempat_tampal",tempat_tampal);	
			maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
			this.context.put("maklumat_hakmilik", maklumat_hakmilik); 	
			if(getParam("id_siasatan")!=null && getParam("id_siasatan")!="")
			{
			maklumat_hakmilik_mmk_pb_siasatan = logic.maklumat_hakmilik_mmk_pb_siasatan(getParam("id_siasatan"),getParam("id_pembatalan"));
			this.context.put("maklumat_hakmilik_mmk_pb_siasatan",maklumat_hakmilik_mmk_pb_siasatan);
			maklumat_hakmilik_mmk_pemilik_siasatan = logic.maklumat_hakmilik_mmk_pemilik_siasatan(getParam("id_siasatan"),getParam("id_pembatalan"));
			this.context.put("maklumat_hakmilik_mmk_pemilik_siasatan",maklumat_hakmilik_mmk_pemilik_siasatan);
			}
        	
         }
 		
         else if ("Pampasan".equals(main_command)){
        	 
        	 String tab_index = "0";
        	 String close_tuntutan = "";
        	 String close_nilaian= "";
        	 
        			
        	 if ("Senarai".equals(sub_command))
   			{	if ("View".equals(subminor_command))
   				{	
     				String id_permohonan = getParam("id_permohonan");
     				header = logic.content_header(id_permohonan);
     				this.context.put("header",header);				
     				maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
     				this.context.put("maklumat_pembatalan", maklumat_pembatalan);
     				senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
     	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
     	    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
     	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
     				myLogger.info("maklumat_pembatalan size :"+maklumat_pembatalan.size());
     				if(maklumat_pembatalan.size()==0)
     				{
     					this.context.put("readmode", "edit");
     					this.context.put("display_error_message","no");	
     				}else
     				{
     					this.context.put("readmode", "view");
     				}
     				     				
         			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
         			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
         			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
             		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
             		header = logic.content_header(id_permohonan);
         			this.context.put("header",header);	
         			senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
         			this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
         			//senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
         			this.context.put("senarai_pihak_penting",senarai_pihak_penting);
         			
         			if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
         			{
         			//String id_pembatalan = getParam("id_pembatalan");			
              		//listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
             		//context.put("listDokumen", listDokumen);
             		//context.put("listDokumen_size", listDokumen.size());
         			}
         			else
         			{
         			//context.put("listDokumen", "");
         			//context.put("listDokumen_size", 0);
         			}
         			context.put("id_permohonan",getParam("id_permohonan"));
             		context.put("id_pembatalan",getParam("id_pembatalan"));
             		
             		senarai_hakmilik = logic.senarai_hakmilik_penarikan(id_permohonan,getParam("id_pembatalan"));
         			this.context.put("senarai_hakmilik",senarai_hakmilik);	
         			senarai_hakmilik_penarikan_all = logic.senarai_hakmilik_penarikan_all(getParam("id_permohonan"),getParam("id_pembatalan"));
         			this.context.put("senarai_hakmilik_penarikan_all",senarai_hakmilik_penarikan_all);
         			
         			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
           			this.context.put("maklumat_keputusan", maklumat_keputusan);
           			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
          			this.context.put("maklumat_warta", maklumat_warta);
         			senarai_hakmilik_overall = logic.senarai_hakmilik_overall(id_permohonan);
         			this.context.put("senarai_hakmilik_overall",senarai_hakmilik_overall);
         			this.context.put("hakmilikoverall",senarai_hakmilik_overall.size());
         			this.context.put("hakmilik_belumbatal",senarai_hakmilik.size());
         			this.context.put("hakmilik_sudahbatal",senarai_hakmilik_batal.size());
         			jenis_button = "6";
         			this.context.put("tajuk_header","URUSAN PAMPASAN"); 
         			list_jenisluas = logic.list_jenisluas();
         			this.context.put("list_jenisluas", list_jenisluas);
     			
     				vm = "app/ppt/frmPenarikanPampasanHakmilik.jsp";	
     	    	} 
         }
     			
       
        
        	 else if ("SenaraiPB".equals(sub_command))
  			{	if ("View".equals(subminor_command))
  				{	
  				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik); 			
  	  		    maklumat_hakmilik_pampasan = logic.maklumat_hakmilik_pampasan(getParam("id_hakmilik"));
  	  		    this.context.put("maklumat_hakmilik_pampasan", maklumat_hakmilik_pampasan);
  				}
  			    else if ("UpdateStatus".equals(subminor_command))
				{	
  			    	if (bolehsimpan.equals("yes")) 
					{
					logic.update_status_hakmilik(getParam("id_hakmilik"), getParam("id_permohonan"),
							(String) session.getAttribute("_ekptg_user_id"),"16102705","add",getParam("id_fail"));
					}	
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
			    this.context.put("maklumat_hakmilik",maklumat_hakmilik); 			
	  		    maklumat_hakmilik_pampasan = logic.maklumat_hakmilik_pampasan(getParam("id_hakmilik"));
	  		    this.context.put("maklumat_hakmilik_pampasan", maklumat_hakmilik_pampasan);
				}
	  			String txtNamaPB = getParam("txtNamaPB");
				String txtNoPB = getParam("txtNoPB");
				String txtNoKPLama = getParam("txtNoKPLama");
				String txtNoLot = getParam("txtNoLot");
				String socMukim = getParam("socMukim");
				this.context.put("socMukim", socMukim);
				this.context.put("txtNamaPB", txtNamaPB);
				this.context.put("txtNoPB", txtNoPB);
				this.context.put("txtNoKPLama", txtNoKPLama); 
				this.context.put("txtNoLot", txtNoLot); 
				senarai_pihak_penting_pampasan = logic.senarai_pihak_penting_pampasan(getParam("id_pembatalan"),txtNamaPB,txtNoPB,txtNoKPLama,txtNoLot,socMukim,getParam("id_hakmilik"));
			    this.context.put("senarai_pihak_penting_pampasan", senarai_pihak_penting_pampasan);  
	  			setupPagePB(session,paging_action,senarai_pihak_penting_pampasan);
	  			this.context.put("tajuk_header","URUSAN PAMPASAN"); 
	  			maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_pembatalan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik); 
	  			
  				vm = "app/ppt/frmPenarikanPampasan.jsp";		
  	    	}
  			
           	else if ("Tuntutan".equals(sub_command))
  			{
           	          		
           		maklumat_siasatan_pb = logic.maklumat_siasatan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
           		this.context.put("maklumat_siasatan_pb",maklumat_siasatan_pb); 
           		if(maklumat_siasatan_pb.size()>0)
           		{
           			
           		  tab_index = "1";
              	  close_tuntutan = "";
              	  close_nilaian= "";
           			
        		if ("View".equals(subminor_command))
   				{  
        			
        			
        			Hashtable h = (Hashtable) maklumat_siasatan_pb.get(0);
        			if(h.get("TUNTUTAN_TUANTNH").toString().equals("") && h.get("TUNTUTAN_PB_BEBANAN").toString().equals("")
        					&& h.get("TUNTUTAN_PB_TDKDAFTAR").toString().equals("") && h.get("TUNTUTAN_PB_LAIN").toString().equals(""))
            		{this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		updateTuntutanPB(session);
        		this.context.put("readmode", "view");        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        	//	updateTuntutanHapus(session);
        		this.context.put("readmode", "edit");        		
   				}    
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan_pb = logic.maklumat_siasatan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
	   			this.context.put("maklumat_siasatan_pb",maklumat_siasatan_pb);		   		      		
	  			this.context.put("tajuk_header","URUSAN PAMPASAN"); 			
	  			header_pampasan_pb = logic.header_pampasan_pb(getParam("id_hakmilikpb"));
	  			this.context.put("header_pampasan_pb",header_pampasan_pb);
	  			maklumat_bayaran_bp = logic.maklumat_bayaran_bp(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));
	  			this.context.put("maklumat_bayaran_bp",maklumat_bayaran_bp);	  			
	  			maklumat_bayaran_semua = logic.maklumat_bayaran_semua(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));
	  			this.context.put("maklumat_bayaran_semua",maklumat_bayaran_semua);
  			
  			vm = "app/ppt/frmPenarikanPampasanTuntutan.jsp";
           		}
           		else
           		{
           			String location_screen = "";
               		location_screen = "app/ppt/frmPenarikanPampasanTerimaCek.jsp"; 
      				this.context.put("screen_kemasukan", ""); 

      				
      				 tab_index = "1";
                 	 close_tuntutan = "yes";
                 	 close_nilaian= "yes";
      				
      			this.context.put("tajuk_header","URUSAN PAMPASAN");  			
      			header_pampasan_pb = logic.header_pampasan_pb(getParam("id_hakmilikpb"));
      			this.context.put("header_pampasan_pb",header_pampasan_pb); 			
      			
           		maklumat_bayaran_bp = logic.maklumat_bayaran_bp(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan")); 
           		this.context.put("maklumat_bayaran_bp",maklumat_bayaran_bp);
           		maklumat_bayaran_semua = logic.maklumat_bayaran_semua(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));
	  			this.context.put("maklumat_bayaran_semua",maklumat_bayaran_semua);
           		
      			vm = location_screen;		
           		}
  	    	}
         	 
        	else if ("Nilaian".equals(sub_command))
  			{
           	          		
        		maklumat_siasatan_pb = logic.maklumat_siasatan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
        		
        	
        		
           		this.context.put("maklumat_siasatan_pb",maklumat_siasatan_pb); 		
        		if ("View".equals(subminor_command))
   				{
        			Hashtable h = (Hashtable) maklumat_siasatan_pb.get(0);
        			if(h.get("HARGA_SEUNIT_SO").toString().equals("") && h.get("UNIT_HARGA_SO").toString().equals("") && h.get("HARGA_PASARAN_SO").toString().equals("") && h.get("ID_TANAH").toString().equals("")
        					&& h.get("BAYAR_PENJEJASAN").toString().equals("") && h.get("BAYAR_PECAHPISAH").toString().equals("") && h.get("BAYAR_NAIK_NILAISO").toString().equals("")
        					&& h.get("HARGA_SEUNIT_JPPH").toString().equals("") && h.get("UNIT_HARGA").toString().equals("") && h.get("HARGA_PASARAN").toString().equals("")
        					&& h.get("AMAUN_PENJEJASAN_JPPH").toString().equals("") && h.get("AMAUN_PECAHPISAH_JPPH").toString().equals("") && h.get("NAIK_NILAI_JPPH").toString().equals(""))
            		{this.context.put("readmode", "edit");   
            		}
            		else{this.context.put("readmode", "view");}         		
   				}
        		else if ("Kemaskini".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command))
   				{
        		
        			if(getParam("id_tanah") == "" )
        			{
        				addNilaian(session);
        			}
        			else
        			{
        				updateNilaian(session);
        			}
        			
        		this.context.put("readmode", "view");        		
   				}    
        		else if ("Hapus".equals(subminor_command))
   				{
        		logic.deleteTanahNilaian(getParam("id_tanah"));
        		this.context.put("readmode", "edit");        		
   				}    
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan_pb = logic.maklumat_siasatan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
	   			this.context.put("maklumat_siasatan_pb",maklumat_siasatan_pb);		   		      		
	  			this.context.put("tajuk_header","URUSAN PAMPASAN"); 			
	  			header_pampasan_pb = logic.header_pampasan_pb(getParam("id_hakmilikpb"));
	  			this.context.put("header_pampasan_pb",header_pampasan_pb);
	  			maklumat_bayaran_bp = logic.maklumat_bayaran_bp(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));
	  			this.context.put("maklumat_bayaran_bp",maklumat_bayaran_bp);
	  			maklumat_bayaran_semua = logic.maklumat_bayaran_semua(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));
	  			this.context.put("maklumat_bayaran_semua",maklumat_bayaran_semua);
  			
  			vm = "app/ppt/frmPenarikanNilaian.jsp";		
  	    	} 
  			
           	else if (("Penerimaan_Check".equals(sub_command)) || ("Penyerahan_Check".equals(sub_command)) || ("EFT".equals(sub_command)))
  			{
           		maklumat_siasatan_pb = logic.maklumat_siasatan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
           		this.context.put("maklumat_siasatan_pb",maklumat_siasatan_pb); 
           		
           		String location_screen = "";
           		if("Penerimaan_Check".equals(sub_command))
           		{
           			if(maklumat_siasatan_pb.size()>0)
            		{
            			 tab_index = "3";
                    	 close_tuntutan = "";
                    	 close_nilaian= "";
            		}
            		else
            		{
            			 tab_index = "1";
                    	 close_tuntutan = "yes";
                    	 close_nilaian= "yes";
            		}
           			location_screen = "app/ppt/frmPenarikanPampasanTerimaCek.jsp";}
           		else if("Penyerahan_Check".equals(sub_command))
           		{
           			
           			if(maklumat_siasatan_pb.size()>0)
            		{
            			 tab_index = "4";
                    	 close_tuntutan = "";
                    	 close_nilaian= "";
            		}
            		else
            		{
            			 tab_index = "2";
                    	 close_tuntutan = "yes";
                    	 close_nilaian= "yes";
            		}
           			location_screen = "app/ppt/frmPenarikanSerahCek.jsp";}
           		else if("EFT".equals(sub_command))
           		{
           			if(maklumat_siasatan_pb.size()>0)
            		{
            			 tab_index = "5";
                    	 close_tuntutan = "";
                    	 close_nilaian= "";
            		}
            		else
            		{
            			 tab_index = "3";
                    	 close_tuntutan = "yes";
                    	 close_nilaian= "yes";
            		}
           			
           			location_screen = "app/ppt/frmPenarikanPampasanEFT.jsp";}
           		
           		if ("View".equals(subminor_command))
  				{	
  				this.context.put("screen_kemasukan", ""); 
  				this.context.put("readmode", "view"); 
  				}
  			    else if ("Tambah".equals(subminor_command))
				{
  			    this.context.put("screen_kemasukan", "yes");
			    this.context.put("readmode", "edit"); 		    
				} 
  			    else if ("papar".equals(subminor_command))
				{
			    this.context.put("screen_kemasukan", "yes");
			    this.context.put("readmode", "view"); 		
			    maklumat_details_bayaran = logic.maklumat_details_bayaran(getParam("id_bayaran"));
			    this.context.put("maklumat_details_bayaran", maklumat_details_bayaran); 
				} 
  				else if ("Simpan".equals(subminor_command))
  				{	
  				if(getParam("id_bayaran")=="" || getParam("id_bayaran").equals(""))
  				{
  					if(bolehsimpan=="yes")
					{	
  						if(("Penerimaan_Check".equals(sub_command)) || ("Penyerahan_Check".equals(sub_command)))
  						{
  							AddMaklumatBayaran(session);
  						}
  						if("EFT".equals(sub_command))
  						{
  						    AddMaklumatBayaranEFT(session); 
  						}					
					}
  					this.context.put("screen_kemasukan", "");
  				    this.context.put("readmode", ""); 	
  				}
  				else
  				{  					
  					
  					
  					if(("Penerimaan_Check".equals(sub_command)) || ("Penyerahan_Check".equals(sub_command)))
						{
  						    UpdateMaklumatBayaran(session);
						}
						if("EFT".equals(sub_command))
						{
						    UpdateMaklumatBayaranEFT(session); 
						}
  					
  					
  					this.context.put("screen_kemasukan", "yes");
  				    this.context.put("readmode", "view"); 	
  	  				maklumat_details_bayaran = logic.maklumat_details_bayaran(getParam("id_bayaran"));
  				    this.context.put("maklumat_details_bayaran", maklumat_details_bayaran); 

  				}			
  			    this.context.put("readmode", "view");  			    
  				} 			
  				else if ("Batal".equals(subminor_command))
  				{  					
  					if(getParam("id_bayaran")=="" || getParam("id_bayaran").equals(""))
  	  				{  	  				
  	  					this.context.put("screen_kemasukan", "yes");
  	  				    this.context.put("readmode", "edit"); 	
  	  				}
  	  				else
  	  				{  					
  	  					this.context.put("screen_kemasukan", "yes");
  	  				    this.context.put("readmode", "edit"); 	
  	  	  				maklumat_details_bayaran = logic.maklumat_details_bayaran(getParam("id_bayaran"));
  	  				    this.context.put("maklumat_details_bayaran", maklumat_details_bayaran);
  	  				}  				
  				} 
  				else if ("Kemaskini".equals(subminor_command))
  				{
  				this.context.put("readmode", "edit");
  				this.context.put("screen_kemasukan", "yes");
  				maklumat_details_bayaran = logic.maklumat_details_bayaran(getParam("id_bayaran"));
			    this.context.put("maklumat_details_bayaran", maklumat_details_bayaran); 
  				} 
  				else if ("Hapus".equals(subminor_command))
  				{    					
  					logic.deleteBayaran(getParam("id_bayaran"));
  					this.context.put("readmode", "");
  					this.context.put("screen_kemasukan", "");
  				}   			
  				else if ("Hapus_beramai".equals(subminor_command))
  				{     	 					
  					this.context.put("readmode", "");
  					this.context.put("screen_kemasukan", "");
  					String[] ids1 = this.request.getParameterValues("ids1");
  					if (ids1 != null) {
  						for (int i = 0; i < ids1.length; i++) {						
  								if (bolehsimpan.equals("yes")) 
  								{
  									logic.deleteBayaran(ids1[i]);  									
  								}
  						}
  				}  
  		   } 
  							
  			
  			this.context.put("tajuk_header","URUSAN PAMPASAN");  			
  			header_pampasan_pb = logic.header_pampasan_pb(getParam("id_hakmilikpb"));
  			this.context.put("header_pampasan_pb",header_pampasan_pb); 			
  			if("Penerimaan_Check".equals(sub_command))
       		{maklumat_bayaran_bp = logic.maklumat_bayaran_bp(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));}
       		else if("Penyerahan_Check".equals(sub_command))
       		{maklumat_bayaran_bp = logic.maklumat_bayaran_bp(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));}
       		else if("EFT".equals(sub_command))
       		{maklumat_bayaran_bp = logic.maklumat_bayaran_bp(getParam("id_hakmilikpb"),"2",getParam("id_pembatalan"));} 			
  			this.context.put("maklumat_bayaran_bp",maklumat_bayaran_bp);
  			maklumat_bayaran_semua = logic.maklumat_bayaran_semua(getParam("id_hakmilikpb"),"1",getParam("id_pembatalan"));
  			this.context.put("maklumat_bayaran_semua",maklumat_bayaran_semua);
  			
  			vm = location_screen;		
  	    	}
          			
  			context.put("id_hakmilikpb",getParam("id_hakmilikpb"));
     		context.put("id_permohonan",getParam("id_permohonan"));
     		context.put("id_pembatalan",getParam("id_pembatalan"));	
     		
     		list_mukim_pampasan = logic.list_mukim_pampasan(getParam("id_pembatalan"));
			this.context.put("list_mukim_pampasan", list_mukim_pampasan);  			
     		maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
  			this.context.put("maklumat_keputusan", maklumat_keputusan); 
  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
  			this.context.put("maklumat_warta", maklumat_warta);
  			jenis_button = "6";
  			header = logic.content_header(getParam("id_permohonan"));
  			this.context.put("header",header);				
  			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
  			this.context.put("maklumat_pembatalan", maklumat_pembatalan); 
  			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);  			
  			this.context.put("tab_index", tab_index);
			this.context.put("close_tuntutan", close_tuntutan);
			this.context.put("close_nilaian", close_nilaian);			
			maklumat_siasatan_pb = logic.maklumat_siasatan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
       		this.context.put("maklumat_siasatan_pb",maklumat_siasatan_pb); 
       		maklumat_pampasan_pb = logic.maklumat_pampasan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
       		this.context.put("maklumat_pampasan_pb",maklumat_pampasan_pb);
       		maklumat_subpampasan_pb = logic.maklumat_subpampasan_pb(getParam("id_hakmilikpb"),getParam("id_pembatalan"));
       		this.context.put("maklumat_subpampasan_pb",maklumat_subpampasan_pb);      		
       		
  			
  		}
 		
		
		else
		{	
					
		    
		    if(getParam("list_name").equals("list_pampasan"))
			{
		    	
		    	String txtNamaPB = getParam("txtNamaPB");
				String txtNoPB = getParam("txtNoPB");
				String txtNoKPLama = getParam("txtNoKPLama");
				String txtNoLot = getParam("txtNoLot");
				String buka_cari1 = getParam("buka_cari");
				String socMukim = getParam("socMukim");
				this.context.put("socMukim", socMukim);
				this.context.put("txtNamaPB", txtNamaPB);
				this.context.put("txtNoLot", txtNoLot);
				this.context.put("txtNoPB", txtNoPB);
				this.context.put("txtNoKPLama", txtNoKPLama);
				this.context.put("buka_cari", buka_cari1);
		    	
		    	context.put("id_permohonan",getParam("id_permohonan"));
	     		context.put("id_pembatalan",getParam("id_pembatalan"));	
	     		maklumat_keputusan = logic.maklumat_keputusan(getParam("id_pembatalan"));
	  			this.context.put("maklumat_keputusan", maklumat_keputusan);
	  			maklumat_warta = logic.maklumat_warta(getParam("id_pembatalan"));
	  			this.context.put("maklumat_warta", maklumat_warta);
	  			jenis_button = "6";
	  			header = logic.content_header(getParam("id_permohonan"));
	  			this.context.put("header",header);				
	  			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
	  			this.context.put("maklumat_pembatalan", maklumat_pembatalan); 	
	  			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
	    		senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_pembatalan"));
	    		this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
	  			this.context.put("tajuk_header","URUSAN PAMPASAN"); 
				senarai_pihak_penting_pampasan = logic.senarai_pihak_penting_pampasan(getParam("id_pembatalan"),txtNamaPB,txtNoPB,txtNoKPLama,txtNoLot,socMukim,getParam("id_hakmilik"));
				this.context.put("senarai_pihak_penting_pampasan", senarai_pihak_penting_pampasan);	
				list_mukim_pampasan = logic.list_mukim_pampasan(getParam("id_pembatalan"));
				this.context.put("list_mukim_pampasan", list_mukim_pampasan);  
				setupPagePB(session,paging_action,senarai_pihak_penting_pampasan);				
			    vm = "app/ppt/frmPenarikanPampasan.jsp";
			}
			else
			{
				myLogger.info("MASUK");				
				String txtNoFail = getParam("txtNoFail");
				String txtNoRujJkptgNegeri = getParam("txtNoRujJkptgNegeri");
				String socKementerian = getParam("socKementerian");
				String socUrusan = getParam("socUrusan");
				String socStatus = getParam("socStatus");			
				this.context.put("txtNoFail", txtNoFail);
				this.context.put("txtNoRujJkptgNegeri", txtNoRujJkptgNegeri);
				this.context.put("socKementerian", socKementerian);
				this.context.put("socUrusan", socUrusan);
				this.context.put("socStatus", socStatus);			
				list_kementerian = logic.list_kementerian();
				this.context.put("list_kementerian",list_kementerian);
				list_urusan = logic.list_urusan();
				this.context.put("list_urusan",list_urusan);
				list_status = logic.list_status();
				this.context.put("list_status",list_status);
				listdepan = logic.senarai_penarikan_carian((String) session.getAttribute("_ekptg_user_negeri"),txtNoFail,txtNoRujJkptgNegeri,socKementerian,socUrusan,socStatus,"2",(String) session.getAttribute("_portal_role"),(String) session.getAttribute("_ekptg_user_negeri"));						
				this.context.put("listdepan",listdepan);
				this.context.put("listdepan_size",listdepan.size());
				vm = "app/ppt/frmPenarikanCarian.jsp";	
			    setupPage(session,paging_action,listdepan);	
			    //(String) session.getAttribute("_ekptg_user_id")
			    //(String) session.getAttribute("_portal_role_")
			    //(String) session.getAttribute("_ekptg_user_negeri")
			    
			   
				
			
			}
		    
		}
		    
	     	this.context.put("current_date", dateFormat.format(date));
		    myLogger.info("USER ID:"+(String) session.getAttribute("_ekptg_user_id"));
		    myLogger.info("ROLE:"+(String) session.getAttribute("_portal_role"));
		    myLogger.info("NEGERI USER:"+ (String) session.getAttribute("_ekptg_user_negeri"));
		    this.context.put("portal_role",(String) session.getAttribute("_portal_role"));
		    this.context.put("negeri_user",(String) session.getAttribute("_ekptg_user_negeri"));		
		    list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
   			this.context.put("list_pegawai",list_pegawai);	
		
		    nama_user = logic.nama_user((String) session.getAttribute("_ekptg_user_id"));
		    this.context.put("nama_user",nama_user);
		    this.context.put("id_permohonan",idpermohonan);
		    
		this.context.put("jenis_button", jenis_button);
		myLogger.info("SKRIN : "+vm);
        return vm;
     
		
	}// close doTemplate2

	/*
	private void addPembatalan(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("txtNoPembatalan", getParam("txtNoPembatalan"));
		h.put("txdTarikhPembatalan", getParam("txdTarikhPembatalan"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("sorJenisPembatalan", getParam("sorJenisPembatalan"));
		h.put("txtSebabPembatalan", getParam("txtSebabPembatalan"));
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.addPenarikan(h);		
	}
	*/
	
	private void addPenyediaanMMK(HttpSession session,String id_mmk) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_mmk", id_mmk);
		h.put("txtTujuan", getParam("txtTujuan"));
		h.put("txtLatarbelakang", getParam("txtLatarbelakang"));		
		h.put("txtImplikasi", getParam("txtImplikasi"));
		h.put("txtUlasan", getParam("txtUlasan"));
		h.put("txtSyor", getParam("txtSyor"));
		h.put("txtLokasi", getParam("txtLokasi"));
		
		h.put("txtAsasPertimbangan", getParam("txtAsasPertimbangan"));
		h.put("txtNilaiAtasTanah", getParam("txtNilaiAtasTanah"));
		h.put("txtSebabPenarikan", getParam("txtSebabPenarikan"));
		h.put("txtPerihalTanah", getParam("txtPerihalTanah"));
		h.put("txtKesimpulan", getParam("txtKesimpulan"));
		h.put("txtUlasanPengarah", getParam("txtUlasanPengarah"));
		h.put("txtTajuk", getParam("txtTajuk"));
		
		h.put("txtKeputusan", getParam("txtKeputusan"));
		h.put("txtPerihalPohon", getParam("txtPerihalPohon"));
		h.put("txtNamaTuan", getParam("txtNamaTuan"));
		h.put("txtPerakuanPentadbir", getParam("txtPerakuanPentadbir"));
		h.put("txtPeruntukan", getParam("txtPeruntukan"));
		
		h.put("txtSidang", getParam("txtSidang"));
		h.put("txtTempatSidang", getParam("txtTempatSidang"));
		h.put("txtTarikhSidang", getParam("txtTarikhSidang"));
		h.put("txtMasaSidang", getParam("txtMasaSidang"));
		h.put("jeniswaktu", getParam("jeniswaktu"));
		
		
		
		
	
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.addPenyediaanMMK(h);		
	}
	
	private void addWartaMMK(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("socProcesWarta", getParam("socProcesWarta"));
		h.put("txtNoWarta", getParam("txtNoWarta"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.addWartaMMK(h);		
	}
	
	private void updateWartaMMK(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_warta", getParam("id_warta"));
		h.put("socProcesWarta", getParam("socProcesWarta"));
		h.put("txtNoWarta", getParam("txtNoWarta"));
		h.put("txdTarikhWarta", getParam("txdTarikhWarta"));
				
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.updateWartaMMK(h);		
	}
	
	private void updatePenyediaanMMK(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_mmk", getParam("id_mmk"));
		h.put("txtTujuan", getParam("txtTujuan"));
		h.put("txtLatarbelakang", getParam("txtLatarbelakang"));
		h.put("txtLokasi", getParam("txtLokasi"));
		
		h.put("txtImplikasi", getParam("txtImplikasi"));
		h.put("txtUlasan", getParam("txtUlasan"));
		h.put("txtSyor", getParam("txtSyor"));
		
		h.put("txtAsasPertimbangan", getParam("txtAsasPertimbangan"));
		h.put("txtNilaiAtasTanah", getParam("txtNilaiAtasTanah"));
		h.put("txtSebabPenarikan", getParam("txtSebabPenarikan"));
		h.put("txtPerihalTanah", getParam("txtPerihalTanah"));
		h.put("txtKesimpulan", getParam("txtKesimpulan"));
		h.put("txtUlasanPengarah", getParam("txtUlasanPengarah"));
		h.put("txtTajuk", getParam("txtTajuk"));
		
		h.put("txtKeputusan", getParam("txtKeputusan"));
		h.put("txtPerihalPohon", getParam("txtPerihalPohon"));
		h.put("txtNamaTuan", getParam("txtNamaTuan"));
		h.put("txtPerakuanPentadbir", getParam("txtPerakuanPentadbir"));
		h.put("txtPeruntukan", getParam("txtPeruntukan"));
		h.put("flag_mmk", getParam("flag_mmk"));
		
		h.put("txtSidang", getParam("txtSidang"));
		h.put("txtTempatSidang", getParam("txtTempatSidang"));
		h.put("txtTarikhSidang", getParam("txtTarikhSidang"));
		h.put("txtMasaSidang", getParam("txtMasaSidang"));
		h.put("jeniswaktu", getParam("jeniswaktu"));
		
		
		
		
		
		
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.updatePenyediaanMMK(h);		
	}
	
	
	private void addKeputusanMMK(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("txtNoRujMMK", getParam("txtNoRujMMK"));
		h.put("txdTarikhMMK", getParam("txdTarikhMMK"));
		h.put("txdTarikhHantar", getParam("txdTarikhHantar"));
		h.put("txdTarikhKeputusan", getParam("txdTarikhKeputusan"));
		h.put("txdTarikhTerimaKeputusan", getParam("txdTarikhTerimaKeputusan"));
		h.put("socKeputusan", getParam("socKeputusan"));
		h.put("txtUlasan", getParam("txtUlasan"));		
		h.put("id_mmk", getParam("id_mmk"));
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.addKeputusanMMK(h);		
	}
	
	private void updateKeputusanMMK(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_mmkkeputusan", getParam("id_mmkkeputusan"));
		h.put("txtNoRujMMK", getParam("txtNoRujMMK"));
		h.put("txdTarikhMMK", getParam("txdTarikhMMK"));
		h.put("txdTarikhHantar", getParam("txdTarikhHantar"));
		h.put("txdTarikhKeputusan", getParam("txdTarikhKeputusan"));
		h.put("txdTarikhTerimaKeputusan", getParam("txdTarikhTerimaKeputusan"));
		h.put("socKeputusan", getParam("socKeputusan"));
		h.put("txtUlasan", getParam("txtUlasan"));		
		h.put("id_mmk", getParam("id_mmk"));
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.updateKeputusanMMK(h);		
	}
	
	
	private void updateSemakanMMK(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_mmk", getParam("id_mmk"));
		h.put("user_id", getParam("socPengarah"));
		h.put("socKeputusan", getParam("socKeputusan"));
		h.put("txtUlasan", getParam("txtUlasan"));
		h.put("txdTarikhSemakan", getParam("txdTarikhSemakan"));
		h.put("txdTarikhHantar", getParam("txdTarikhHantar"));	
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.updateSemakanMMK(h);		
	}
	
	
	private void updatePembatalan(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_pembatalan", getParam("id_pembatalan"));
		h.put("txtNoPembatalan", getParam("txtNoPembatalan"));
		h.put("txdTarikhPembatalan", getParam("txdTarikhPembatalan"));
		h.put("txdTarikhSurat", getParam("txdTarikhSurat"));
		h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));
		h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
		h.put("sorJenisPembatalan", getParam("sorJenisPembatalan"));
		h.put("txtSebabPembatalan", getParam("txtSebabPembatalan"));
		h.put("id_permohonan", getParam("id_permohonan"));
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.updatePenarikan(h);
		
	}
	
	private void updateFiles(HttpSession session) throws Exception {
		Hashtable h = new Hashtable();
		h.put("id_dokumen", getParam("id_dokumen"));
		h.put("txtnamadokumen", getParam("txtnamadokumen"));
		h.put("txtketerangandokumen", getParam("txtketerangandokumen"));		
		h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
		logic.updateFiles(h);
		
	}
	
	public void setupPagePB(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
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
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("senarai_pihak_penting_pampasan",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	
	
	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
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
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("listdepan",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	
	
	@SuppressWarnings("unchecked")
	private void uploadFiles() throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData(item);
		      }
		    }
		  }
	 private void saveData(FileItem item) throws Exception {
		 	HttpSession session = request.getSession();		
	  		Db db = null;
	        try {
	        	
	        	long id_Dokumen = DB.getNextID("TBLPPTDOKUMEN_SEQ");	        	
	        	db = new Db();			      
			     
	        	
	        	
	        	
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("insert into TBLPPTDOKUMEN " +
	        			"(id_Dokumen,id_penarikanbalik,nama_Fail,jenis_Mime,content,tajuk,keterangan,id_masuk,id_kemaskini) " +
	        			"values(?,?,?,?,?,?,?,?,?)");
	        	ps.setLong(1, id_Dokumen);
	        	ps.setString(2, getParam("id_pembatalan"));
	        	ps.setString(3,item.getName());
	        	ps.setString(4,item.getContentType());
	        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	        	ps.setString(6, getParam("txtnamadokumen"));
	        	ps.setString(7, getParam("txtketerangandokumen"));	        	
	        	ps.setString(8,(String) session.getAttribute("_ekptg_user_id"));	        	      	
	        	ps.setString(9,(String) session.getAttribute("_ekptg_user_id"));        	
	        	ps.executeUpdate();
	            con.commit();
	            
	        	
		    } finally {
			      if (db != null) db.close();
		    }
	  }
	
	 //AddMaklumatSiasatan
	 
	 
	 private void AddMaklumatBayaran(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("txdTarikhTerimaCek", getParam("txdTarikhTerimaCek"));
			h.put("txtpenamacek", getParam("txtpenamacek"));			
			h.put("txtnocek", getParam("txtnocek"));
			h.put("txdTarikhCek", getParam("txdTarikhCek"));
			h.put("txtAmaunCek", getParam("txtAmaunCek"));
			h.put("txdTarikhAkhirCek", getParam("txdTarikhAkhirCek"));
			h.put("cara_bayar", getParam("cara_bayar"));
			h.put("txdTarikhSerahCek", getParam("txdTarikhSerahCek"));			
			h.put("jeniswaktu", getParam("jeniswaktu"));
			h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
			h.put("txtTempat", getParam("txtTempat"));			
			h.put("txtNamaBank", getParam("txtNamaBank"));
			h.put("txtUlasanBayaran", getParam("txtUlasanBayaran"));
			
			
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.AddMaklumatBayaran(h);		
		}
	 
	 private void UpdateMaklumatBayaran(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			
			h.put("txtNamaBank", getParam("txtNamaBank"));
			h.put("txtUlasanBayaran", getParam("txtUlasanBayaran"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
			h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
			h.put("txtTempat", getParam("txtTempat"));
			
			
			h.put("id_bayaran", getParam("id_bayaran"));
			h.put("txdTarikhTerimaCek", getParam("txdTarikhTerimaCek"));
			h.put("txtpenamacek", getParam("txtpenamacek"));			
			h.put("txtnocek", getParam("txtnocek"));
			h.put("txdTarikhCek", getParam("txdTarikhCek"));
			h.put("txtAmaunCek", getParam("txtAmaunCek"));
			h.put("txdTarikhAkhirCek", getParam("txdTarikhAkhirCek"));
			h.put("cara_bayar", getParam("cara_bayar"));
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));			
			h.put("txtwakil", getParam("txtwakil"));
			h.put("txtNoKP", getParam("txtNoKP"));
			h.put("txdTarikhSerahCek", getParam("txdTarikhSerahCek"));
			h.put("socStatusPenyerahan", getParam("socStatusPenyerahan"));
			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.UpdateMaklumatBayaran(h);		
		}
	 
	 
	 private void AddMaklumatBayaranEFT(HttpSession session) throws Exception {
			 
			Hashtable h = new Hashtable();
			h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
			h.put("txdTarikhSurat", getParam("txdTarikhSurat"));			
			h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));
			h.put("txtNamaKementerian", getParam("txtNamaKementerian"));
			h.put("txtNoEFT", getParam("txtNoEFT"));
			h.put("txtAmaunEFT", getParam("txtAmaunEFT"));
			h.put("txdTarikh", getParam("txdTarikh"));
			h.put("cara_bayar", getParam("cara_bayar"));
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("txtUlasanBayaran", getParam("txtUlasanBayaran"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.AddMaklumatBayaranEFT(h);		
		}
	 
	 
	 private void UpdateMaklumatBayaranEFT(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();
			h.put("id_bayaran", getParam("id_bayaran"));
			h.put("txtNoRujSurat", getParam("txtNoRujSurat"));
			h.put("txdTarikhSurat", getParam("txdTarikhSurat"));			
			h.put("txdTarikhTerimaSurat", getParam("txdTarikhTerimaSurat"));
			h.put("txtNamaKementerian", getParam("txtNamaKementerian"));
			h.put("txtNoEFT", getParam("txtNoEFT"));
			h.put("txtAmaunEFT", getParam("txtAmaunEFT"));
			h.put("txdTarikh", getParam("txdTarikh"));
			h.put("cara_bayar", getParam("cara_bayar"));
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
			h.put("txtUlasanBayaran", getParam("txtUlasanBayaran"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.UpdateMaklumatBayaranEFT(h);		
		}
	 
	 
	 private void addMaklumatAmTanah(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();
			h.put("txdTarikhKemasukan", getParam("txdTarikhKemasukan"));
			h.put("txdTarikhPemeriksaan", getParam("txdTarikhPemeriksaan"));
			h.put("txtbilsyit", getParam("txtbilsyit"));
			h.put("sorJenisTanah", getParam("sorJenisTanah"));
			h.put("sorKedudukan", getParam("sorKedudukan"));
			h.put("txtNoGazet", getParam("txtNoGazet"));
			h.put("sorJenisGazet", getParam("sorJenisGazet"));
			h.put("txtNoGazetDaerah", getParam("txtNoGazetDaerah"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.addMaklumatAmTanah(h);		
		}
	 
	 private void updateMaklumatAmTanah(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();
			h.put("txdTarikhKemasukan", getParam("txdTarikhKemasukan"));
			h.put("txdTarikhPemeriksaan", getParam("txdTarikhPemeriksaan"));
			h.put("txtbilsyit", getParam("txtbilsyit"));
			h.put("sorJenisTanah", getParam("sorJenisTanah"));
			h.put("sorKedudukan", getParam("sorKedudukan"));
			h.put("txtNoGazet", getParam("txtNoGazet"));
			h.put("sorJenisGazet", getParam("sorJenisGazet"));
			h.put("txtNoGazetDaerah", getParam("txtNoGazetDaerah"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_tanahumum", getParam("id_tanahumum"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateMaklumatAmTanah(h);		
		}
	 
	 
	 private void addPerihalTanah(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();		
			h.put("txtLokasiTanah", getParam("txtLokasiTanah"));
			h.put("txtKeadaanLot", getParam("txtKeadaanLot"));
			h.put("txtJenisTanaman", getParam("txtJenisTanaman"));
			h.put("txtBerhampiran", getParam("txtBerhampiran"));
			h.put("txtKeadaanTanaman", getParam("txtKeadaanTanaman"));
			h.put("txtUlasan", getParam("txtUlasan"));
			h.put("txtKeseluruhanLot", getParam("txtKeseluruhanLot"));
			h.put("txtKawasan", getParam("txtKawasan"));
			h.put("sorBangunan", getParam("sorBangunan"));
			h.put("txtBilBangunan", getParam("txtBilBangunan"));
			
			h.put("txtKawasanTerlibat", getParam("txtKawasanTerlibat"));
			
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.addPerihalTanah(h);		
		}
	 
	 private void updatePerihalTanah(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();
			h.put("txtKawasanTerlibat", getParam("txtKawasanTerlibat"));
			
			h.put("txtLokasiTanah", getParam("txtLokasiTanah"));
			h.put("txtKeadaanLot", getParam("txtKeadaanLot"));
			h.put("txtJenisTanaman", getParam("txtJenisTanaman"));
			h.put("txtBerhampiran", getParam("txtBerhampiran"));
			h.put("txtKeadaanTanaman", getParam("txtKeadaanTanaman"));
			h.put("txtUlasan", getParam("txtUlasan"));
			h.put("txtKeseluruhanLot", getParam("txtKeseluruhanLot"));
			h.put("txtKawasan", getParam("txtKawasan"));
			h.put("sorBangunan", getParam("sorBangunan"));
			h.put("txtBilBangunan", getParam("txtBilBangunan"));			
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_tanahumum", getParam("id_tanahumum"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updatePerihalTanah(h);		
		}
	 
	 private void addMaklumatSiasatan(HttpSession session) throws Exception {
				 
			Hashtable h = new Hashtable();		
			h.put("txtNoKes", getParam("txtNoKes"));
			h.put("txtNoSiasatan", getParam("txtNoSiasatan"));
			h.put("txtTempatSiasatan", getParam("txtTempatSiasatan"));
			h.put("txtPoskod", getParam("txtPoskod"));
			h.put("txtAlamat1", getParam("txtAlamat1"));
			h.put("txtAlamat2", getParam("txtAlamat2"));
			h.put("txtAlamat3", getParam("txtAlamat3"));
			h.put("socNegeriSiasatan", getParam("socNegeriSiasatan"));
			h.put("socBandarSiasatan", getParam("socBandarSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatan"));
			h.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
			h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
			h.put("txtAlasan", getParam("txtAlasan"));
			h.put("txtTkhAkhirNotis", getParam("txtTkhAkhirNotis"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
			h.put("bil_siasatan", getParam("bil_siasatan"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.addMaklumatSiasatan(h);		
		}
	 
	 private void addMaklumatSiasatanX(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();		
			h.put("txtNoKes", getParam("txtNoKes"));
			h.put("txtNoSiasatan", getParam("txtNoSiasatan"));
			h.put("txtTempatSiasatan", getParam("txtTempatSiasatan"));
			h.put("txtPoskod", getParam("txtPoskod"));
			h.put("txtAlamat1", getParam("txtAlamat1"));
			h.put("txtAlamat2", getParam("txtAlamat2"));
			h.put("txtAlamat3", getParam("txtAlamat3"));
			h.put("socNegeriSiasatan", getParam("socNegeriSiasatan"));
			h.put("socBandarSiasatan", getParam("socBandarSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatanX"));
			h.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
			h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
			h.put("txtAlasan", getParam("txtAlasan"));
			h.put("txtTkhAkhirNotis", getParam("txtTkhAkhirNotis"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
			h.put("bil_siasatan", getParam("bil_siasatan"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.addMaklumatSiasatan(h);		
		}
	 
	 private void updateMaklumatSiasatan(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("id_siasatan", getParam("id_siasatan"));
			h.put("txtNoKes", getParam("txtNoKes"));
			h.put("txtNoSiasatan", getParam("txtNoSiasatan"));
			h.put("txtTempatSiasatan", getParam("txtTempatSiasatan"));
			h.put("txtPoskod", getParam("txtPoskod"));
			h.put("txtAlamat1", getParam("txtAlamat1"));
			h.put("txtAlamat2", getParam("txtAlamat2"));
			h.put("txtAlamat3", getParam("txtAlamat3"));
			h.put("socNegeriSiasatan", getParam("socNegeriSiasatan"));
			h.put("socBandarSiasatan", getParam("socBandarSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatan"));
			h.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
			h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
			h.put("txtAlasan", getParam("txtAlasan"));
			h.put("txtTkhAkhirNotis", getParam("txtTkhAkhirNotis"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("bil_siasatan", getParam("bil_siasatan"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateMaklumatSiasatan(h);		
		}
	 
	 private void updateMaklumatSiasatanX(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("id_siasatan", getParam("id_siasatanX"));
			h.put("txtNoKes", getParam("txtNoKes"));
			h.put("txtNoSiasatan", getParam("txtNoSiasatan"));
			h.put("txtTempatSiasatan", getParam("txtTempatSiasatan"));
			h.put("txtPoskod", getParam("txtPoskod"));
			h.put("txtAlamat1", getParam("txtAlamat1"));
			h.put("txtAlamat2", getParam("txtAlamat2"));
			h.put("txtAlamat3", getParam("txtAlamat3"));
			h.put("socNegeriSiasatan", getParam("socNegeriSiasatan"));
			h.put("socBandarSiasatan", getParam("socBandarSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatanX"));
			h.put("txdTarikhSiasatan", getParam("txdTarikhSiasatan"));
			h.put("txtMasaSiasatan", getParam("txtMasaSiasatan"));
			h.put("txtAlasan", getParam("txtAlasan"));
			h.put("txtTkhAkhirNotis", getParam("txtTkhAkhirNotis"));
			h.put("jeniswaktu", getParam("jeniswaktu"));
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("bil_siasatan", getParam("bil_siasatan"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateMaklumatSiasatan(h);		
		}
	 
	 
	 private void updateTuanTanah(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txdMilikTanah", getParam("txdMilikTanah"));
			h.put("txtCaraMilikTanah", getParam("txtCaraMilikTanah"));
			h.put("txtHargaTanah", getParam("txtHargaTanah"));
			h.put("txtBebananTanah", getParam("txtBebananTanah"));
			h.put("txtKeteranganTuanTanah", getParam("txtKeteranganTuanTanah"));
			h.put("txtJenisTanaman", getParam("txtJenisTanaman"));
			h.put("txtJenisBangunan", getParam("txtJenisBangunan"));
			h.put("sorPecahSempadan", getParam("sorPecahSempadan"));
			h.put("txdPecahSempadan", getParam("txdPecahSempadan"));
			h.put("sorTukarSyarat", getParam("sorTukarSyarat"));
			h.put("txdTukarSyarat", getParam("txdTukarSyarat"));
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuanTanah(h);		
		}
	 private void updateTuanTanahHapus(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txdMilikTanah", "");
			h.put("txtCaraMilikTanah", "");
			h.put("txtHargaTanah", "");
			h.put("txtBebananTanah", "");
			h.put("txtKeteranganTuanTanah", "");
			h.put("txtJenisTanaman", "");
			h.put("txtJenisBangunan", "");
			h.put("sorPecahSempadan","");
			h.put("txdPecahSempadan", "");
			h.put("sorTukarSyarat", "");
			h.put("txdTukarSyarat", "");
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuanTanah(h);		
		}
	 
	 private void updateAgensi(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", getParam("txtKeteranganAgensi"));
			h.put("txtKeteranganJurunilai", getParam("txtKeteranganJurunilai"));
			
			h.put("txtKeteranganJPPH", getParam("txtKeteranganJPPH"));
			h.put("txtKeteranganAkujanji", getParam("txtKeteranganAkujanji"));
			
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateAgensi(h);		
		}	 
	 private void updateAgensiHapus(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", "");
			h.put("txtKeteranganJurunilai", "");
			h.put("txtKeteranganJPPH","");
			h.put("txtKeteranganAkujanji","");
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateAgensi(h);		
		}
	 
	 private void updateKerosakan(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", getParam("txtKeteranganAgensi"));
			h.put("txtKeteranganJurunilai", getParam("txtKeteranganJurunilai"));			
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateKerosakan(h);		
		}	 
	 private void updateKerosakanHapus(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", "");
			h.put("txtKeteranganJurunilai", "");			
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateKerosakan(h);		
		}
	 
	 
	 private void updateTuntutan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtTuntutanTuanTanah", getParam("txtTuntutanTuanTanah"));
			h.put("txtLainTuntutan", getParam("txtLainTuntutan"));
			h.put("txtPBDaftar", getParam("txtPBDaftar"));
			h.put("txtPBXDaftar", getParam("txtPBXDaftar"));		
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuntutan(h);		
		}
	 
	 private void updateTuntutanPB(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtTuntutanTuanTanah", getParam("txtTuntutanTuanTanah"));
			h.put("txtLainTuntutan", getParam("txtLainTuntutan"));
			h.put("txtPBDaftar", getParam("txtPBDaftar"));
			h.put("txtPBXDaftar", getParam("txtPBXDaftar"));
			h.put("txtStatus", getParam("txtStatus"));
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuntutanPB(h);		
		}
	 
	 private void updateTuntutanHapus(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtTuntutanTuanTanah","");
			h.put("txtLainTuntutan",""); 
			h.put("txtPBDaftar", "");
			h.put("txtPBXDaftar", "");		
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuntutan(h);		
		}
	 
	 private void updateBantahan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtBantahanLain", getParam("txtBantahanLain"));
			h.put("txtBantahanAgensi", getParam("txtBantahanAgensi"));
			h.put("txtBantahanTuanTanah", getParam("txtBantahanTuanTanah"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateBantahan(h);		
		}
	 
	 private void updateBantahanHapus(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtBantahanLain","");
			h.put("txtBantahanAgensi","");
			h.put("txtBantahanTuanTanah","");				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateBantahan(h);		
		}
	 
	 
	 @SuppressWarnings("unchecked")
	 private void updateKeputusan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("socPegawaiSiasatan", getParam("socPegawaiSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatan"));
			h.put("txtUlasanPerintah", getParam("txtUlasanPerintah"));				
			h.put("socUnit", getParam("socUnit"));
			h.put("nilai_seunit", getParam("nilai_seunit"));
			h.put("tarikh_perintah", getParam("txdTarikhPerintah"));					
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateKeputusan(h);		
		}
	 
	 @SuppressWarnings("unchecked")
	 private void updateKeputusanHapus(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
		 	
		 	h.put("socUnit", "");
			h.put("nilai_seunit", "");
			h.put("tarikh_perintah", "");		 	
			h.put("socPegawaiSiasatan","");
			h.put("socStatusSiasatan", "");
			h.put("txtUlasanPerintah", "");				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateKeputusan(h);		
		}
	 
	 private void updateStatusSiasatan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("socPegawaiSiasatan", getParam("socPegawaiSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatan"));
			h.put("txtUlasanSiasatan", getParam("txtUlasanSiasatan"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateStatusSiasatan(h);		
		}
	 
	 	
	 
	 private void updateStatusSiasatanHapus(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("socPegawaiSiasatan","");
			h.put("socStatusSiasatan", "");
			h.put("txtUlasanSiasatan", "");				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateStatusSiasatan(h);		
		}
	 private void addNilaian(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
		 	h.put("txtHargaSeunitSO",getParam("txtHargaSeunitSO"));
		 	h.put("txtUnitHargaSO",getParam("txtUnitHargaSO"));
			h.put("txtHargaPasaranSO",getParam("txtHargaPasaranSO"));
			h.put("txtPenjejasanSO",getParam("txtPenjejasanSO"));
			h.put("txtPecahPisahSO",getParam("txtPecahPisahSO"));
			h.put("txtNaikNilaiSO",getParam("txtNaikNilaiSO"));
			myLogger.info("txtNaikNilaiSO:"+getParam("txtNaikNilaiSO"));
			h.put("txtHargaSeunitJPPH",getParam("txtHargaSeunitJPPH"));
			h.put("txtUnitHargaJPPH",getParam("txtUnitHargaJPPH"));
			h.put("txtHargaPasaranJPPH",getParam("txtHargaPasaranJPPH"));
			h.put("txtPenjejasanJPPH",getParam("txtPenjejasanJPPH"));
			h.put("txtPecahPisahJPPH",getParam("txtPecahPisahJPPH"));
			h.put("txtNaikNilaiJPPH",getParam("txtNaikNilaiJPPH"));		
			h.put("id_siasatan", getParam("id_siasatan"));		
			h.put("id_hakmilik", getParam("id_hakmilik"));		
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
						
			h.put("id_award", getParam("id_award"));	
			h.put("txtFee", getParam("txtFee"));	
			h.put("txtBangunan", getParam("txtBangunan"));	
			h.put("txtTanah", getParam("txtTanah"));	
			h.put("txtPenjejasan", getParam("txtPenjejasan"));	
			h.put("txtGantiRugi", getParam("txtGantiRugi"));	
			h.put("txtNaik", getParam("txtNaik"));	
			h.put("txtLainPampasan", getParam("txtLainPampasan"));	
			h.put("txtJumlah", getParam("txtJumlah"));	
			h.put("txtPecahpisah", getParam("txtPecahpisah"));	
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
			
			
			logic.addNilaian(h);		
		}
	 
	 private void updateStatusPenarikan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();
		 	h.put("id_permohonan", getParam("id_permohonan"));
			h.put("id_Fail", getParam("id_fail"));
			h.put("userId", (String) session.getAttribute("_ekptg_user_id"));
			h.put("id_suburusanstatus", getParam("id_suburusanstatus"));
			h.put("id_suburusanstatusfail", getParam("id_suburusanstatusfail"));
			logic.updateStatusPenarikan(h);	 	
	 }
	 
	 
	 private void updateNilaian(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();
		 	
			h.put("txtHargaSeunitSO",getParam("txtHargaSeunitSO"));
		 	h.put("txtUnitHargaSO",getParam("txtUnitHargaSO"));
			h.put("txtHargaPasaranSO",getParam("txtHargaPasaranSO"));
			h.put("txtPenjejasanSO",getParam("txtPenjejasanSO"));
			h.put("txtPecahPisahSO",getParam("txtPecahPisahSO"));
			h.put("txtNaikNilaiSO",getParam("txtNaikNilaiSO"));
			myLogger.info("txtNaikNilaiSO:"+getParam("txtNaikNilaiSO"));
			h.put("txtHargaSeunitJPPH",getParam("txtHargaSeunitJPPH"));
			h.put("txtUnitHargaJPPH",getParam("txtUnitHargaJPPH"));
			h.put("txtHargaPasaranJPPH",getParam("txtHargaPasaranJPPH"));
			h.put("txtPenjejasanJPPH",getParam("txtPenjejasanJPPH"));
			h.put("txtPecahPisahJPPH",getParam("txtPecahPisahJPPH"));
			h.put("txtNaikNilaiJPPH",getParam("txtNaikNilaiJPPH"));
			h.put("id_tanah", getParam("id_tanah"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			
			h.put("id_award", getParam("id_award"));	
			h.put("txtFee", getParam("txtFee"));	
			h.put("txtBangunan", getParam("txtBangunan"));	
			h.put("txtTanah", getParam("txtTanah"));	
			h.put("txtPenjejasan", getParam("txtPenjejasan"));	
			h.put("txtGantiRugi", getParam("txtGantiRugi"));	
			h.put("txtNaik", getParam("txtNaik"));	
			h.put("txtLainPampasan", getParam("txtLainPampasan"));	
			h.put("txtJumlah", getParam("txtJumlah"));	
			h.put("txtPecahpisah", getParam("txtPecahpisah"));
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));
			
			
			
			logic.updateNilaian(h);		
		}
	 
	 
	 private void addMaklumatKerosakan(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("txtKerosakanTanah", getParam("txtKerosakanTanah"));
			h.put("txtKerosakanTanaman", getParam("txtKerosakanTanaman"));
			h.put("txtKerosakanBangunan", getParam("txtKerosakanBangunan"));
			h.put("txtKosDitanggung", getParam("txtKosDitanggung"));
		
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.addMaklumatKerosakan(h);		
		}
	 
	 private void updateMaklumatKerosakan(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();
			h.put("txtKerosakanTanah", getParam("txtKerosakanTanah"));
			h.put("txtKerosakanTanaman", getParam("txtKerosakanTanaman"));
			h.put("txtKerosakanBangunan", getParam("txtKerosakanBangunan"));
			h.put("txtKosDitanggung", getParam("txtKosDitanggung"));
			
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_tanahumum", getParam("id_tanahumum"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateMaklumatKerosakan(h);		
		}
	
	 private void updatePembatalanOnline3(HttpSession session,String idpermohonan) throws Exception {
			Hashtable h = new Hashtable();
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_permohonan", idpermohonan);
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updatePenarikanOnline3(h);
			
		}
	 
	 private void updatePenarikanOnline3Tolak(HttpSession session) throws Exception {
	        Hashtable h = new Hashtable();
			h.put("id_pembatalan", getParam("id_pembatalan"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updatePenarikanOnline3Tolak(h);
			
		}
	 
		@SuppressWarnings({ "unchecked", "static-access"})
		private void sendEmail(String id_pengarah,String nofail,String nama_projek,String tarikh_permohonan,String nama_kementerian) throws Exception{
			
	    	Vector checkEmailPengarah = new Vector();
	    	checkEmailPengarah.clear();
	  
			//check email (pengarah)
			checkEmailPengarah = email_penarikan.checkEmail(id_pengarah);
			String emelPengarah = "";
			if(checkEmailPengarah.size()!=0){
				Hashtable ceP = (Hashtable)checkEmailPengarah.get(0);
				emelPengarah = (String)ceP.get("EMEL");
			}

			//EmailTester et = new EmailTester();
			
			if(emelPengarah!=""){
				email_penarikan.setEmail_MMK_Penarikan(emelPengarah,"mmk_penarikanbalik",nofail,nama_projek,tarikh_permohonan,nama_kementerian);
			}
			
			//jenis email
			// - hantar pengesahan (pt - pengarah)
			// - hantar untuk diagihankan
			// - hantar untuk semakan mmk (pt - pengarah)
			
		}//close sendEmail
		
		@SuppressWarnings({ "unchecked", "static-access" })
		private String nameAndId(String userIdNeg) throws Exception{
			
			Vector dataNamaPengarah = new Vector();		
			dataNamaPengarah.clear();
			
		    //GET NAMA PENGARAH DAN ID PENGARAH
		    String nama_pengarah = "";
		    String id_pengarah = "";
		    email_penarikan.setNamaPengarah(userIdNeg);
		    dataNamaPengarah = email_penarikan.getNamaPengarah();
		    if(dataNamaPengarah.size()!=0){
		    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
		    	nama_pengarah = (String)np.get("nama_pengarah");
		    	id_pengarah = (String)np.get("user_id");
		    }
		    
		    context.put("nama_pengarah",nama_pengarah);

		    return id_pengarah;
		    
		}//close userData
		
		public void insertPopupReg(String nama_class,String tajuk_class, String group,Db db) throws Exception {
			//	Db db = null;
				try {
				//	db = new Db();
					Statement stmt = db.getStatement();
					String sql = " INSERT INTO MODULE ( "+
							" MODULE_ID, MODULE_TITLE, MODULE_CLASS,  "+
							" MODULE_GROUP, MODULE_DESCRIPTION)  "+
							" VALUES ('"+nama_class+"','"+tajuk_class+"','"+nama_class+"','"+group+"','') ";					
					myLogger.info("REG CLASS :"+sql.toUpperCase());
					stmt.executeUpdate(sql);
					
					sql = " INSERT INTO ROLE_MODULE ( "+
							" MODULE_ID, USER_ROLE) "+
							" SELECT '"+nama_class+"' AS MODULE_ID,NAME AS USER_ROLE FROM ROLE WHERE UPPER(NAME) LIKE '%PPT%'";
					myLogger.info("REG ROLE CLASS :"+sql.toUpperCase());
					stmt.executeUpdate(sql);
							
				} finally {
				//	if (db != null)
				//		db.close();
				}
			}
			
			
			
			public int checkRegPopup(String class_name, Db db)  throws Exception {
			  
			  	//Db db = null; 
			  	int total = 0;
			  	String sql="";
			  	ResultSet rs = null;
				try {
				//	db = new Db(); 
					sql = " SELECT COUNT(*) AS CHECK_COUNT FROM ROLE_MODULE WHERE MODULE_ID = '"+class_name+"'";	
					rs = db.getStatement().executeQuery(sql); 
				if ( rs.next() ) { 
					total = rs.getInt(1); 
				} 
				} finally { 
				//Close the database connection 
				//if ( db != null ) db.close(); 
				//if (rs != null) rs.close();			
				} 
				return total;
		  }
		

			public void checkFieldWujud(String table_name,String column_name,String data_type,Db db)  throws Exception {
				  
			  	int total = 0;
			  	String sql="";
			  	ResultSet rs = null;
				try {
					sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+table_name+"' AND COLUMN_NAME = '"+column_name+"' ";	
					rs = db.getStatement().executeQuery(sql); 
				if ( rs.next() ) { 
					total = rs.getInt("total"); 
				} 
				} finally { 					
				} 
				
				if(total==0)
				{				
					sql = "ALTER TABLE "+table_name+" "+
							" ADD "+column_name+" "+data_type+" ";
					rs = db.getStatement().executeQuery(sql); 			
					//ALTER TABLE supplier ADD supplier_name varchar2(50);				
				}
				
		  }
	 
	 
}// close class
