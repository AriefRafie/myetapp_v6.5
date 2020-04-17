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

import ekptg.helpers.DB;
import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmSementaraSiasatanData;
import ekptg.model.ppt.PPTHeader;


public class FrmSementaraSiasatan extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(FrmSementaraSiasatan.class);
	
	FrmSementaraSiasatanData logic = new FrmSementaraSiasatanData();
	PPTHeader headerSek8 = new PPTHeader();
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();		
		String vm = ""; 
		String main_command = getParam("command");
		String sub_command = getParam("sub_command");
		String subminor_command = getParam("subminor_command");
		myLogger.info("main_command :"+main_command+";sub_command :"+sub_command+";sub_minor_command :"+subminor_command);
		
		String paging_action = getParam("action");
		String location = getParam("location");
		String point = getParam("point");
		String buka_cari = getParam("buka_cari");
		String jenis_permohonan = "4";
		String jenis_button = "";		
		
		Vector senarai_pihak_penting_bahagian = null;
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
		} finally {
			if (dbx != null)
				dbx.close();
		}
		
		
		
		this.context.put("buka_cari", buka_cari);
		this.context.put("listdepan", listdepan);
		this.context.put("listdepan_size", 0);
		this.context.put("location", location);
		this.context.put("point", point);
		this.context.put("list_default", "");
		this.context.put("jenis_permohonan", jenis_permohonan);		
		
		this.context.put("senarai_pihak_penting_bahagian", "");
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
		this.context.put("maklumat_am_tanah_permohonan","");
		this.context.put("maklumat_hakmilik_mmk_pb","");
		this.context.put("maklumat_hakmilik_mmk_ekar","");
		this.context.put("maklumat_hakmilik_mmk_hektar","");
		this.context.put("maklumat_hakmilik_mmk_lot","");
		this.context.put("maklumat_bayaran_semua","");
		this.context.put("maklumat_pampasan_pb","");
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
		
		this.context.put("nk_add","");
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		String bolehsimpan = "";
		String readmode = "";   	
    	
		//paging
    	paging();
    	
    	//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	headerSek8.setDataHeader(idpermohonan);
		Vector dataHeader = headerSek8.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			flagSegera = dh.get("flag_segera").toString();
			id_fail = dh.get("id_fail").toString();
			
			Vector list_sub = null;
			list_sub = headerSek8.listPerjalananFail(idpermohonan);
			this.context.put("list_sub_header", list_sub);
		}
    	
		context.put("id_fail",id_fail);
		context.put("flagSegera",flagSegera);
		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		
		
		if ("Siasatan".equals(main_command)){
        	this.context.put("tambah_kehadiran", "");
        	if ("Senarai".equals(sub_command))
   			{	if ("View".equals(subminor_command))
   				{   				
   				this.context.put("tajuk_header","SIASATAN");
   				vm = "app/ppt/frmSementaraSetSiasatan.jsp";	
   				}
   			//list_siasatan_penarikan = logic.list_siasatan_penarikan(getParam("id_permohonan"));
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
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
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
   				vm = "app/ppt/frmSementaraInfoBicaraTuanTanah.jsp";	
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
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
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
   				vm = "app/ppt/frmSementaraInfoBicaraAgensi.jsp";	
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
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
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
   				vm = "app/ppt/frmSementaraInfoBicaraTuntutan.jsp";	
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
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
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
   				vm = "app/ppt/frmSementaraInfoBicaraBantahan.jsp";	
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
        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
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
        			updateNilaianX(session);
        		this.context.put("readmode", "edit");        		
   				}    
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmSementaraInfoBicaraNilaian.jsp";	
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
        				System.out.println("masuk");
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
       		    	    maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
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
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
					
				}
        		
        		
        		context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan);
	   			
	   			if (!subminor_command.equals("getBandar"))
				{ 
        	    myLogger.info("TEST!!!:"+subminor_command);
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_permohonan"));	
            	this.context.put("tarikh_akhir_tampalX",tarikh_akhir_tampal);  				
   				this.context.put("nk_add",""); 
   				
   				Hashtable h2 = (Hashtable) maklumat_siasatan.get(0);
   				
   				maklumat_siasatanX = logic.maklumat_siasatanX(getParam("id_permohonan"),Integer.parseInt(h2.get("BIL_SIASATAN").toString())+1);
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
   			    list_siasatan = logic.list_siasatan(getParam("id_permohonan"),getParam("id_hakmilik"));   				
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
	   				
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				       
				}
        		
	   			 
	   			senarai_pihak_penting_pampasan_perintah = logic.senarai_pihak_penting_pampasan_perintah(getParam("id_permohonan"),getParam("id_siasatan"),getParam("CariPB"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah",senarai_pihak_penting_pampasan_perintah);	
	   		
	   			
	   			list_subsiasatan = logic.list_subsiasatan(getParam("id_siasatan"),"ALASAN_TANGGUH");
	   			this.context.put("list_subsiasatan",list_subsiasatan);
	   			
	   			list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	   			this.context.put("list_pegawai",list_pegawai);
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmSementaraInfoBicaraStatusSiasatan.jsp";	
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
    								logic.updateAwardPerintah(id_status_penerima[i],temp_xhadir[i],temp_perintah[i],id_award[i], id_hakmilik_pb[i], pampasan[i], (String) session.getAttribute("_ekptg_user_id"),getParam("id_permohonan"),getParam("id_hakmilik"),getParam("id_siasatan"));
    								}
    								else
    								{    							
    								 								
    								
    								if(id_hakmilik_pb[i].equals(getParam("id_siasatan_hakmilikpb")))
    			    				{
    			    				
    									long id_award_temp = DB.getNextID("TBLPPTAWARD_SEQ");  
    									id_award_sementara = id_award_temp+""; 
        								logic.addAwardPerintah(id_status_penerima[i],getParam("id_siasatan"),id_award_temp, id_hakmilik_pb[i], pampasan[i], (String) session.getAttribute("_ekptg_user_id"),temp_xhadir[i],temp_perintah[i]);
        	    					   
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
    			    								logic.simpan_subAward(txtUlasanKerosakan[t],txtUlasanKerosakanAward[t],id_award_temp,"BAYAR_SEWAAN",(String) session.getAttribute("_ekptg_user_id"));
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
    			    								logic.simpan_subAward(txtUlasanprosiding[t],txtUlasanprosidingAward[t],id_award_temp,"BAYAR_KEROSAKAN",(String) session.getAttribute("_ekptg_user_id"));
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
        							
            		        		
        							senarai_perintah_award = logic.senarai_perintah_award(getParam("id_permohonan"),getParam("id_siasatan"),id_award_temp+"");
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
    								
    								logic.simpan_subAward(txtUlasanKerosakan[i],txtUlasanKerosakanAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_SEWAAN",(String) session.getAttribute("_ekptg_user_id"));
    								
    							}
    						}
    					} 
        			
        			
        			String[] txtUlasanprosiding = this.request.getParameterValues("txtUlasanprosiding");
         		    String[] txtUlasanprosidingAward = this.request.getParameterValues("txtUlasanprosidingAward");
         		    
         			if (txtUlasanprosiding != null ) {
     					for (int i = 0; i < txtUlasanprosiding.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanprosiding[i],txtUlasanprosidingAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_KEROSAKAN",(String) session.getAttribute("_ekptg_user_id"));
     								
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
        			
        			senarai_perintah_award = logic.senarai_perintah_award(getParam("id_permohonan"),getParam("id_siasatan"),getParam("id_siasatan_award"));
            		this.context.put("senarai_perintah_award",senarai_perintah_award);
    				}
        			
        			
        	    updateKeputusan(session);
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
        		
        	    this.context.put("flag_open_award", "yes");
        		this.context.put("readmode", getParam("readmode"));    
        	//	updateKeputusan(session);
        		
        		if(getParam("readmode").equals("edit"))
        		{
        		updateKeputusan(session);
        		}
        		
        		senarai_perintah_award = logic.senarai_perintah_award(getParam("id_permohonan"),getParam("id_siasatan"),getParam("id_siasatan_award"));
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
        		
        		senarai_pihak_penting_bahagian = logic.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
    			this.context.put("senarai_pihak_penting_bahagian",senarai_pihak_penting_bahagian);
        		
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan); 
	   			senarai_pihak_penting_pampasan_perintah = logic.senarai_pihak_penting_pampasan_perintah(getParam("id_permohonan"),getParam("id_siasatan"),getParam("CariPB"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah",senarai_pihak_penting_pampasan_perintah);	
	   				
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmSementaraInfoBicaraKeputusan.jsp";	
   			}
        	
        	else if ("Kehadiran".equals(sub_command))
   			{ 
        	this.context.put("view_pb", "");	
        	this.context.put("tambah_kehadiran_negeri", "");		
        	if ("View".equals(subminor_command))   			
   			{
   						
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
   			} 
        	else if ("tambah_wakil".equals(subminor_command))   			
   			{
        		this.context.put("tambah_kehadiran_wakil", "yes");
        		this.context.put("tambah_kehadiran_negeri_wakil", "yes");
        		this.context.put("tambah_kehadiran", "");
        		this.context.put("tambah_kehadiran_negeri", "");
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");   
        		//this.context.put("nk_add","yes");
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN"); 				
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				
   				
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
			vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSementaraSiasatanKehadiran.jsp";	
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
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");    				
   				}
        		else if ("tambah".equals(subminor_command))
   				{ 
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_permohonan"));	
        		this.context.put("tarikh_akhir_tampal",tarikh_akhir_tampal);	
   				this.context.put("readmode", "edit");
   				this.context.put("nk_add","yes");
   				this.context.put("record_siasatan","yes");	
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");   				
   				}
        		else if ("ulang".equals(subminor_command))
   				{
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_permohonan"));	
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
   				
   			    list_siasatan = logic.list_siasatan(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("bil_siasatan",list_siasatan.size()+1);
   				this.context.put("socBandarSiasatan",getParam("socBandarSiasatan"));
   				this.context.put("socStatusSiasatan","");
   				this.context.put("txdTarikhSiasatan",getParam("txdTarikhSiasatan"));
   				this.context.put("txtMasaSiasatan",getParam("txtMasaSiasatan"));
   				this.context.put("txtTkhAkhirNotis",getParam("txtTkhAkhirNotis"));
   				this.context.put("txtAlasan",getParam("txtAlasan"));
   				this.context.put("jeniswaktu",getParam("jeniswaktu"));
   				
   				this.context.put("record_siasatan","yes");	
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");   				
   				}        		
        		else if ("Papar".equals(subminor_command))
   				{        	    		
   				this.context.put("readmode", "view");   				
   				this.context.put("record_siasatan","yes");   				
   				maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   				this.context.put("maklumat_siasatan",maklumat_siasatan);
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);   				
   				Hashtable h = (Hashtable) maklumat_siasatan.get(0);				
   				/*if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				}  */
   					if(!h.get("ID_NEGERI").toString().equals(""))
   	   				{
   	   	   				String idBandar = (String) h.get("ID_BANDAR");	
   	   					list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
   	   					this.context.put("list_bandar",list_bandar);
   	   					this.context.put("idBandar",idBandar);
   	   					
   					} 
   				this.context.put("tajuk_header","SENARAI SIASATAN");    				
   				}
        		
        		else if ("kemaskini".equals(subminor_command))
   				{        	    		
   				this.context.put("readmode", "edit");   				
   				this.context.put("record_siasatan","yes");   				
   				maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   				this.context.put("maklumat_siasatan",maklumat_siasatan);
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
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
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
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
   		    	    maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
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
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				this.context.put("tajuk_header","SENARAI SIASATAN"); 		    	
			}
   		    else if ("Hapus".equals(subminor_command))
			{   
   		    	logic.delete_subsiasatan(getParam("id_siasatan"));
				logic.deleteSiasatan(getParam("id_siasatan"));				
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
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
				
				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
				this.context.put("tajuk_header","SENARAI SIASATAN"); 	
			}	
        		
        		list_negeri = logic.getListnegeri();
        	   	context.put("list_negeri",list_negeri);
        		list_siasatan = logic.list_siasatan(getParam("id_permohonan"),getParam("id_hakmilik"));
    			this.context.put("list_siasatan",list_siasatan);

        		
   		   vm = "app/ppt/frmSementaraSetSenaraiSiasatan.jsp";
        		
        		
   			}        	 
	
      		context.put("id_permohonan",getParam("id_permohonan"));
      		context.put("id_pembatalan",getParam("id_pembatalan"));	
      		context.put("id_hakmilik",getParam("id_hakmilik"));	
      		jenis_button = "5";
   			header = logic.content_header(getParam("id_permohonan"));   			
   			this.context.put("header",header);
   			
   			
//   			
//   			list_negeri = logic.getListnegeri();
//   			context.put("list_negeri",list_negeri);	
//   		//	maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
//   		//	this.context.put("maklumat_pembatalan", maklumat_pembatalan); 			
//   			maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_permohonan"));
//   			this.context.put("maklumat_penyediaan", maklumat_penyediaan); 	
//   			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_permohonan"));
//   			this.context.put("maklumat_keputusan", maklumat_keputusan); 
//   			senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_permohonan"));
//			this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
//			senarai_pihak_penting = logic.senarai_pihak_penting(getParam("id_permohonan"));
//			this.context.put("senarai_pihak_penting",senarai_pihak_penting);			
//			list_siasatan = logic.list_siasatan(getParam("id_permohonan"),getParam("id_hakmilik"));
//			this.context.put("list_siasatan",list_siasatan);
//			list_bandar_all = logic.list_bandar_all();
//			this.context.put("list_bandar_all",list_bandar_all);
//			
//			tempat_tampal = logic.tempat_tampal(getParam("id_permohonan"));
//			this.context.put("tempat_tampal",tempat_tampal);
//			
//			if(getParam("id_siasatan")!=null && getParam("id_siasatan")!="")
//			{
//			maklumat_hakmilik_mmk_pb_siasatan = logic.maklumat_hakmilik_mmk_pb_siasatan(getParam("id_siasatan"),getParam("id_permohonan"));
//			this.context.put("maklumat_hakmilik_mmk_pb_siasatan",maklumat_hakmilik_mmk_pb_siasatan);
//			
//			maklumat_hakmilik_mmk_pemilik_siasatan = logic.maklumat_hakmilik_mmk_pemilik_siasatan(getParam("id_siasatan"),getParam("id_permohonan"));
//			this.context.put("maklumat_hakmilik_mmk_pemilik_siasatan",maklumat_hakmilik_mmk_pemilik_siasatan);
//			
//			}
//		//	list_pegawai = logic.list_pegawai("1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
//	   	//    this.context.put("list_pegawai",list_pegawai);
//			
//			 list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
//				this.context.put("list_pegawai",list_pegawai);
//        	
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
			listdepan = logic.senarai_penarikan_carian(txtNoFail,txtNoRujJkptgNegeri,socKementerian,socUrusan,socStatus,"2",(String) session.getAttribute("_portal_role"),(String) session.getAttribute("_ekptg_user_negeri"));						
			this.context.put("listdepan",listdepan);
			this.context.put("listdepan_size",listdepan.size());
			vm = "app/ppt/frmSementaraSiasatanCarian.jsp";	
		    setupPage(session,paging_action,listdepan);	
		    //(String) session.getAttribute("_ekptg_user_id")
		    //(String) session.getAttribute("_portal_role_")
		    //(String) session.getAttribute("_ekptg_user_negeri")
		    
		   
			
		
		}	
        context.put("id_status", id_status);		
		this.context.put("jenis_button", jenis_button);	
		myLogger.info("VM:"+vm);
	    return vm;	
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
			h.put("id_pembatalan", getParam("id_permohonan"));
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
			h.put("id_pembatalan", getParam("id_permohonan"));
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
			h.put("id_pembatalan", getParam("id_permohonan"));
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
			h.put("id_pembatalan", getParam("id_permohonan"));
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
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateAgensi(h);		
		}	 
	 private void updateAgensiHapus(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", "");
			h.put("txtKeteranganJurunilai", "");			
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateAgensi(h);		
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
	 
	 private void updateNilaianX(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();
		 	
			h.put("txtHargaSeunitSO","");
		 	h.put("txtUnitHargaSO","");
			h.put("txtHargaPasaranSO","");
			h.put("txtPenjejasanSO","");
			h.put("txtPecahPisahSO","");
			h.put("txtNaikNilaiSO","");
			myLogger.info("txtNaikNilaiSO:"+getParam("txtNaikNilaiSO"));
			h.put("txtHargaSeunitJPPH","");
			h.put("txtUnitHargaJPPH","");
			h.put("txtHargaPasaranJPPH","");
			h.put("txtPenjejasanJPPH","");
			h.put("txtPecahPisahJPPH","");
			h.put("txtNaikNilaiJPPH","");
			h.put("id_tanah", getParam("id_tanah"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
					
			logic.updateNilaian(h);		
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

	 
	 @SuppressWarnings("unchecked")
	 private void updateKeputusan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			//h.put("socPegawaiSiasatan", getParam("socPegawaiSiasatan"));
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
	 
		private void paging() throws Exception{
			String flagPaging = getParam("paging");
	    	if(flagPaging!=""){
	    		context.put("paging", getParam("paging"));
	    	}else{
	    		context.put("paging", "13");
	    	}
		}//close paging	 
	 
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
		
	 
}	
		
		
	