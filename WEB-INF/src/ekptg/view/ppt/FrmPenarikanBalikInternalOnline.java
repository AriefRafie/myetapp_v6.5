//jp = 3

package ekptg.view.ppt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmPermohonanUPTOnlineData;
import ekptg.model.ppt.PPTHeader;
import ekptg.view.ppt.email.EmailOnline;


public class FrmPenarikanBalikInternalOnline extends AjaxBasedModule{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 590483814003750088L;
	static Logger myLogger = Logger.getLogger(FrmPembatalanInternal.class);
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
			
	FrmPermohonanUPTData model = new FrmPermohonanUPTData();
	FrmPermohonanUPTOnlineData modelOnline = new FrmPermohonanUPTOnlineData();
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	
	@Override
	public String doTemplate2() throws Exception{		
		HttpSession session = request.getSession();		
		String vm = ""; 
		
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
		String jenis_permohonan = "3";
		String jenis_button = "";		
		
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
		Vector listPenarikanExpired = new Vector();
		
		listPenarikanExpired.clear();		
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
		
		//user login id
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
		
    	//get id jawatan
    	String id_jawatan_user = getJawatan(id_user);
    	context.put("id_jawatan_user",id_jawatan_user);
    	
		//fail penarikan expired
		listPenarikanExpired = modelOnline.getListPenarikanExpired(id_user);    		
		context.put("listPenarikanExpired",listPenarikanExpired);
		context.put("typeList","online");
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		String bolehsimpan = "";
		String readmode = "";
		
		String flag_semakan_online = "";
		maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
		if(maklumat_pembatalan.size()!=0){
			Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
			flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
		}
		
		
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
			
			//09022012 hantar semakan online			
			else if ("hantarSemakan".equals(sub_command)){
				
				PPTHeader infoPermohonan = new PPTHeader();
				String namaProjek = "";
				infoPermohonan.setDataHeader(getParam("id_permohonan"));
				Vector dataHeader = infoPermohonan.getDataHeader();
				if(dataHeader.size()!=0){
					Hashtable dh = (Hashtable) dataHeader.get(0);
					namaProjek = dh.get("tujuan").toString();
				}
				
				//update flag
    			updateFlag(session,"1");
				
				if (bolehsimpan.equals("yes")){
					sendEmail(namaProjek,"","","",id_user,"hantarSemakanPenarikan",session);    	
				}
    	
    			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
    			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
    			if(maklumat_pembatalan.size()!=0){
    				Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
    				flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
    			}
    			
				this.context.put("readmode", "view");
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
			
			//09022012 sahkan semakan online			
			else if ("sahSemakan".equals(sub_command)){
				
				PPTHeader infoPermohonan = new PPTHeader();
				String namaProjek = "";
				infoPermohonan.setDataHeader(getParam("id_permohonan"));
				Vector dataHeader = infoPermohonan.getDataHeader();
				if(dataHeader.size()!=0){
					Hashtable dh = (Hashtable) dataHeader.get(0);
					namaProjek = dh.get("tujuan").toString();
				}
				
				//update flag
    			updateFlag(session,"2");
				
    			if (bolehsimpan.equals("yes")){
					sendEmail(namaProjek,"","","",id_user,"hantarLulusPenarikan",session);    	
				}
    
    			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
    			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
    			if(maklumat_pembatalan.size()!=0){
    				Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
    				flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
    			}
    			
				this.context.put("readmode", "view");
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
			
			//09022012 lulus permohonan online			
			else if ("lulusPermohonan".equals(sub_command)){
				
				//update flag
    			updateFlag(session,"3");
				
    
    			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
    			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
    			if(maklumat_pembatalan.size()!=0){
    				Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
    				flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
    			}
    			
				this.context.put("readmode", "view");
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
				updatePembatalanOnline(session);
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
				updatePembatalanOnlineTolak(session);
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
						logic.addPenarikanOnline(h);	
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
			
			senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
			this.context.put("senarai_pihak_penting",senarai_pihak_penting);			
			if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
			{
			String id_pembatalan = getParam("id_pembatalan");			
     		listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		context.put("listDokumen", listDokumen);
    		context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			context.put("listDokumen", "");
			context.put("listDokumen_size", 0);
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
			//09022012 hantar semakan online			
			else if ("hantarSemakan".equals(sub_command)){
				
				//update flag
    			updateFlag(session,"1");
				
    			this.context.put("readmode", "view");
    			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
    			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
    			if(maklumat_pembatalan.size()!=0){
    				Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
    				flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
    			}

	    		vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			
			//09022012 sahkan semakan online			
			else if ("sahSemakan".equals(sub_command)){
				
				//update flag
    			updateFlag(session,"2");
				
    			this.context.put("readmode", "view");
    			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
    			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
    			if(maklumat_pembatalan.size()!=0){
    				Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
    				flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
    			}

    			vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";
	    	}
			
			//09022012 lulus permohonan online			
			else if ("lulusPermohonan".equals(sub_command)){
				
				//update flag
    			updateFlag(session,"3");
				
    			this.context.put("readmode", "view");
    			maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
    			this.context.put("maklumat_pembatalan", maklumat_pembatalan);
    			if(maklumat_pembatalan.size()!=0){
    				Hashtable hmp = (Hashtable) maklumat_pembatalan.get(0);
    				flag_semakan_online = hmp.get("FLAG_SEMAKAN_ONLINE").toString();
    			}

    			vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";
	    	}
			else if ("hantar".equals(sub_command)){
				this.context.put("readmode", "view");		
	    		updatePembatalanOnline(session);
				vm = "app/ppt/frmPenarikanInfoLotPenarikanBalik.jsp";	
	    	}
			else if ("tolak".equals(sub_command)){
				updatePembatalanOnlineTolak(session);
				this.context.put("readmode", "view");
			/*	maklumat_pembatalan = logic.maklumat_penarikan(getParam("id_pembatalan"));
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
	    		*/
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
			senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
			this.context.put("senarai_pihak_penting",senarai_pihak_penting);
			senarai_hakmilik_batal_penarikan_status = logic.senarai_hakmilik_batal_penarikan_status(getParam("id_pembatalan"));
    		this.context.put("senarai_hakmilik_batal_penarikan_status",senarai_hakmilik_batal_penarikan_status);
    					
			if(getParam("id_pembatalan")!="" && getParam("id_pembatalan")!=null)
			{
			String id_pembatalan = getParam("id_pembatalan");			
     		listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		context.put("listDokumen", listDokumen);
    		context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			context.put("listDokumen", "");
			context.put("listDokumen_size", 0);
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
			String id_pembatalan = getParam("id_pembatalan");			
     		listDokumen = logic.senarai_dokumen_penarikan(id_pembatalan);
    		context.put("listDokumen", listDokumen);
    		context.put("listDokumen_size", listDokumen.size());
			}
			else
			{
			context.put("listDokumen", "");
			context.put("listDokumen_size", 0);
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
			senarai_pihak_penting = logic.senarai_pihak_penting(id_permohonan);
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
		else if("clearValue".equals(main_command)){
			
			resetValueCarian();
			
			listdepan = logic.senarai_penarikan_carian_online("","","","","","","","","","","",id_user,(String)session.getAttribute("_portal_role"));						
			this.context.put("listdepan",listdepan);
			this.context.put("listdepan_size",listdepan.size());
			setupPage(session,paging_action,listdepan);	
			
			vm = "app/ppt/frmPenarikanCarian.jsp";	
		}
		else
		{
				
			
			//dropdown
			context.put("selectStatusSPT",HTML.SelectStatusSPT("socStatus",Utils.parseLong(getParam("socStatus")),"style=width:auto"));
			context.put("selectJenisHMCarian",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(getParam("socJenisHakmilik")),"id=selectJenisHMCarian style=width:auto"));
			context.put("selectNegeriCarian",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(getParam("socNegeri")),null,"style=width:auto"));	
			
			context.put("txtNoFailCarian", getParam("txtNoFailCarian").trim());
			context.put("txdTarikhPermohonan", getParam("txdTarikhPermohonan").trim());
			context.put("txtBilPermohonanCarian", getParam("txtBilPermohonanCarian").trim());			
			context.put("txtNamaPBCarian", getParam("txtNamaPBCarian").trim());
			context.put("txtNoPBCarian", getParam("txtNoPBCarian").trim());
			context.put("txtNoHakmilikCarian", getParam("txtNoHakmilikCarian").trim());			
			context.put("txtNoLotCarian", getParam("txtNoLotCarian").trim());
			context.put("txtTujuanCarian", getParam("txtTujuanCarian").trim());
			
			listdepan = logic.senarai_penarikan_carian_online(getParam("socStatus"),getParam("socJenisHakmilik"),getParam("socNegeri"),getParam("txtNoFailCarian").trim(),
					getParam("txdTarikhPermohonan").trim(),getParam("txtBilPermohonanCarian").trim(),
					getParam("txtNamaPBCarian").trim(),getParam("txtNoPBCarian").trim(),getParam("txtNoHakmilikCarian").trim(),getParam("txtNoLotCarian").trim(),
					getParam("txtTujuanCarian").trim(),id_user,(String)session.getAttribute("_portal_role"));						
			this.context.put("listdepan",listdepan);
			this.context.put("listdepan_size",listdepan.size());
			setupPage(session,paging_action,listdepan);	
			
			vm = "app/ppt/frmPenarikanCarian.jsp";	
		
		}
	    
     	this.context.put("current_date", dateFormat.format(date));
	    myLogger.info("USER ID:"+(String) session.getAttribute("_ekptg_user_id"));
	    myLogger.info("ROLE:"+(String) session.getAttribute("_portal_role"));
	    myLogger.info("NEGERI USER:"+ (String) session.getAttribute("_ekptg_user_negeri"));
	    this.context.put("portal_role",(String) session.getAttribute("_portal_role"));
	    this.context.put("negeri_user",(String) session.getAttribute("_ekptg_user_negeri"));
	
	   // list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	   //	this.context.put("list_pegawai",list_pegawai);	
	
	    nama_user = logic.nama_user((String) session.getAttribute("_ekptg_user_id"));
	    this.context.put("nama_user",nama_user);
	    this.context.put("jenis_button", jenis_button);
	    
	    context.put("listPenarikanExpired",listPenarikanExpired);
		context.put("typeList","online");
		context.put("flag_semakan_online",flag_semakan_online);
		
	    myLogger.info("vm : "+vm);
        return vm;
 
        
        
	
}

	private void sendEmail(String nama_projek,String tarikh_permohonan,String userIdKementerian, String id_jawatan_user, String id_user
			, String purpose,HttpSession session) throws Exception{
		EmailOnline et = new EmailOnline();
		et.setEmail("","",purpose,"",nama_projek,tarikh_permohonan,"",userIdKementerian, id_jawatan_user, id_user
				,String.valueOf(session.getAttribute("portal_username")));
		
	}//close sendEmail
	
	private void resetValueCarian() throws Exception{
		
		//dropdown
		context.put("selectStatusSPT",HTML.SelectStatusSPT("socStatus",null,"style=width:auto"));
		context.put("selectJenisHMCarian",HTML.SelectJenisHakmilik("socJenisHakmilik",null,"id=selectJenisHMCarian style=width:auto"));
		context.put("selectNegeriCarian",HTML.SelectNegeriMampu("socNegeri",null,null,"style=width:auto"));	
		
		context.put("txtNoFailCarian", "");
		context.put("txdTarikhPermohonan", "");
		context.put("sorUrusanCarian", "");
		context.put("txtBilPermohonanCarian", "");			
		context.put("txtNamaPBCarian", "");
		context.put("txtNoPBCarian", "");
		context.put("txtNoHakmilikCarian", "");			
		context.put("txtNoLotCarian", "");
		context.put("txtTujuanCarian", "");
		
	}//close resetValueCarian

	@SuppressWarnings("unchecked")
	private void updateFlag(HttpSession session,String flag) throws Exception{
		
	    	Hashtable h = new Hashtable();
	       
	    	h.put("id_pembatalan", getParam("id_pembatalan"));
	    	h.put("id_user", session.getAttribute("_ekptg_user_id"));
	    	h.put("flag_semakan_online", flag);
	    	FrmPembatalanInternalData.updateFlag(h,"tarik");
	    	
	}//close updateFlag
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private String getJawatan(String id_user) throws Exception{
		
		Vector dataUser = new Vector();

		dataUser.clear();

		model.setDataUser(id_user);
		dataUser = model.getDataUser();
	    String ID_JAWATAN = "";
	    if(dataUser.size()!=0){
	    	Hashtable t = (Hashtable)dataUser.get(0);
	    	ID_JAWATAN = t.get("ID_JAWATAN").toString();
	    }
	   
	    return ID_JAWATAN;
	    
	}//close getJawatan
	
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
	
	private void updatePembatalanOnline(HttpSession session) throws Exception {
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
		logic.updatePenarikanOnline(h);
		
	}
	
	private void updatePembatalanOnlineTolak(HttpSession session) throws Exception {
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
		logic.updatePenarikanOnlineTolak(h);
		
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
	
}
