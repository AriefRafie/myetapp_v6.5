package ekptg.view.ppt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8SiasatanData;
import ekptg.model.ppt.FrmUPTSek8BorangFData;
import ekptg.model.ppt.FrmUPTSek8HakmilikData;
import ekptg.model.ppt.PPTHeader;
import ekptg.ppt.helpers.HTMLPPT;
//import javax.swing.JOptionPane;
//import ekptg.model.ppt.FrmUPTSek8InfoTanahTerperinciBangunanData; //penambahan yati


public class FrmSek8Siasatan extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(FrmSek8Siasatan.class);
	
	FrmSek8SiasatanData logic = new FrmSek8SiasatanData();
	PPTHeader headerSek8 = new PPTHeader();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	FrmUPTSek8BorangFData modelBF = new FrmUPTSek8BorangFData();
	FrmUPTSek8HakmilikData model = new FrmUPTSek8HakmilikData();
	//FrmUPTSek8InfoTanahTerperinciBangunanData modelTanah = new FrmUPTSek8InfoTanahTerperinciBangunanData();
	private FrmSemakan frmSemak = null;

	
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
		//String id_bangunan = "";
		
		 String idStatus = getParam("idStatus");
		 String idPermohonan = getParam("id_permohonan");
		 Vector dataMaklumatJPPH = new Vector();
		 Vector dataIdSiasatan = new Vector();
		
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
		Vector getIdSuburusanstatusfail = new Vector();
		Vector dataSuburusanHakmilik = new Vector();
		Vector list_bangsa = null;
		Vector list_warga = null;
		Vector checkSizeBahagianPB = null;
		Vector senarai_pihak_penting_bahagian = null;	
		Vector list_bahagian= null;
		Vector senarai_tuntutan = null;
		Vector senarai_siasatan= null;
		

    	dataSuburusanHakmilik.clear();
    	getIdSuburusanstatusfail.clear();
		
    	//validation for "permohonan selesai"
    	context.put("willShowAlertSelesai","yes");
    	
		this.context.put("pampasani",getParam("pampasani"));

    	this.context.put("senarai_tuntutan", "");
    	this.context.put("list_bahagian", "");
    	this.context.put("senarai_pihak_penting_bahagian", "");
    	this.context.put("list_bangsa", "");
		this.context.put("list_warga", "");
		this.context.put("buka_cari", buka_cari);
		this.context.put("listdepan", listdepan);
		this.context.put("listdepan_size", 0);
		this.context.put("location", location);
		this.context.put("point", point);
		this.context.put("list_default", "");
		this.context.put("jenis_permohonan", jenis_permohonan);	
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
		context.put("showAlertPaging","no");
		context.put("hideAdd", "no");		
		this.context.put("nama_pb", "");
		this.context.put("jenis_nopb", "");
		this.context.put("nopb", "");
		this.context.put("no_lot", "");	
		this.context.put("id_bahagianbp","");
		this.context.put("syer_atas","");
		this.context.put("syer_bawah","");	
		this.context.put("id_hakmilikpb","");  	
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());		
		this.context.put("nk_add","");
		this.context.put("id_hakmilik_salin", "");
		context.put("hideAdd", "no");
		this.context.put("maklumat_PB_Salin", "");
		this.context.put("senarai_siasatan", "");
		
		/*
		//razman comment.. jangan call kat sini.. call dekat command yg sepatutnya display jenis banggunan
		String idJenisBangunan = getParam("socJenisBangunan");
		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(idJenisBangunan),null,"style=width:auto class=disabled"));
		*/
		
		String bolehsimpan = "";
		String readmode = "";
		
		
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
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupAfidavit", dbx) == 0) {
				// reg class
				insertPopupReg("ekptg.view.ppt.SkrinPopupAfidavit",
						"Skrin Afidavit", "EKPTG - PPT",
						dbx);
			}
			checkTableWujud("TBLPPTTUNTUANPENYEWA",dbx);
		} finally {
			if (dbx != null)
				dbx.close();
		}
		*/
		

		//paging
		/*
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "16");
    	}
    	*/
		context.put("paging", "16");
		
    	//user id
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");
    	
    	/*
    	modelUPT.setGetUserId(id_user);
	    Vector listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }
	    */
	   
	    
	    
    	//header
		String id_status = "";
		String flagSegera = "";
		String id_fail = "";
    	String idpermohonan = getParam("id_permohonan");
    	String nama_pengarah = "";
    	Vector dataHeader = null;
    	String id_suburusanstatusfailppt = "";
    	if(!idpermohonan.equals(""))
    	{
    	headerSek8.setDataHeader(idpermohonan);
		dataHeader = headerSek8.getDataHeader();
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
		
		//get current idsuburusanstatusfail
		
		modelUPT.setGetIdSuburusanstatusfail(idpermohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}		
		
		
		//GET NAMA PENGARAH
	   
	    modelUPT.setNamaPengarah(userIdNeg);
	    Vector dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }	    
	    
    	}
    	context.put("id_suburusanstatusfailppt",id_suburusanstatusfailppt);
    	context.put("nama_pengarah",nama_pengarah);
	    
	    
		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		
		
		if(!getParam("id_siasatan").equals("") && !getParam("id_siasatan").equals(null))
		{
			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
			senarai_tuntutan = senarai_tuntutan(getParam("id_siasatan"));
			if(senarai_tuntutan.size()==0)
			{
	    		String tuntutan_asal = "";
	    		if(maklumat_siasatan.size()!=0){
					Hashtable b = (Hashtable) maklumat_siasatan.get(0);
					tuntutan_asal = (String)b.get("TUNTUTAN_PB_BEBANAN");	
				}
	    		if(!tuntutan_asal.equals(""))
	    		{
	    			simpan_Tuntutan(getParam("id_siasatan"),tuntutan_asal,(String) session.getAttribute("_ekptg_user_id"));
	    		}
			}
		}
		
		
		if ("Siasatan".equals(main_command)){
		
			this.context.put("tambah_kehadiran", "");
        	if ("Senarai".equals(sub_command))
   			{	if ("View".equals(subminor_command))
   				{   			
   			
   				this.context.put("tajuk_header","SIASATAN");
   				vm = "app/ppt/frmSek8SetSiasatan.jsp";	
   				}
   			//list_siasatan_penarikan = logic.list_siasatan_penarikan(getParam("id_permohonan"));
			//this.context.put("list_siasatan_penarikan",list_siasatan_penarikan);
   			
   			}
        	//yati bt edit
        	else if ("TuanTanah".equals(sub_command))
   			{	
        		String socJenisBangunan = getParam("id_bangunan");
        		String socBangunan = getParam("socBangunan");   //penambahan yati
        		context.put("id_bangunan", socBangunan);

        		// Checkbox PPT-25 (ii) Jenis Pemilikan
        		Vector semakanList = FrmSemakan.getSenaraiSemakan("pptjenispemilikan");  //, "pptjenistanaman"
        		context.put("senaraiSemakan", semakanList); 
        		
        		// Checkbox PPT-25 (iii) Jenis Tanaman
        		Vector listTanaman = FrmSemakan.getSenaraiSemakan("pptjenistanaman"); // "jenispemilikan", 
        		context.put("senaraiSemakan2", listTanaman); 
        		context.put("semakanclass", new FrmSemakan());
        		
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
        		if ("View".equals(subminor_command))
   				{    
        			myLogger.info("masuk sini");
        			
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
            		if(h.get("TEMPOH_MILIK_TANAH").toString().equals("") && 
            				h.get("CARA_MILIK").toString().equals("") && 
            				h.get("HARGA_BELI").toString().equals("") && 
            				h.get("BEBANAN").toString().equals("") && 
            				h.get("KETERANGAN_TUAN_TANAH").toString().equals("") && 
            				h.get("JENIS_TANAMAN").toString().equals("") && 
            				h.get("JENIS_BANGUNAN").toString().equals("") && 
            				h.get("FLAG_PECAH_SEMPADAN").toString().equals("") && 
            				h.get("TARIKH_PECAH_SEMPADAN").toString().equals("") && 
            				h.get("FLAG_TUKAR_SYARAT").toString().equals("") && 
            				h.get("TARIKH_TUKAR_SYARAT").toString().equals("") &&
            				h.get("JENIS_BANGUNAN").toString().equals("")) 
            		{
            			Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1)
        	   			{
                			myLogger.info("AWAT XKELUAQ NI"); //YATI
                			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
            	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
            	   				}
            				this.context.put("readmode", "edit");    
            		}
            		else{
            			myLogger.info("view bangunan");
            			this.context.put("readmode", "view");  
            			context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(socJenisBangunan),null,"style=width:auto class=disabled"));
            		}         	
            		//String txtCaraMilikTanah = getParam("txtCaraMilikTanah");
            		
            		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(h.get("JENIS_BANGUNAN").toString()),null,"style=width:auto class=disabled"));
    		
            	
   				}
        	    if ("Kemaskini".equals(subminor_command))
   				{  
        		//String socJenisBangunan = getParam("socJenisBangunan");
        		this.context.put("readmode", "edit"); 
        		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(socJenisBangunan),null,"style=width:auto"));
   				}	    
        		else if ("Batal".equals(subminor_command))
   				{   
        		this.context.put("readmode", "edit");        		
   				}        		
        		else if ("Simpan".equals(subminor_command)){
        			String idSiasatan = getParam("id_siasatan");
        			// Checkbox PPT-25 (ii) Jenis Pemilikan
        			String[] cbsemak = this.request.getParameterValues("jenispemilikan");
        			frmSemak = new FrmSemakan();
//        			myLogger.info("Simpan pushdb, idSiasatan= " +idSiasatan); // debugger at log copy
        			frmSemak.semakanHapusByPermohonan(idSiasatan);
        			if (cbsemak != null) {
        				for (int i = 0; i < cbsemak.length; i++) {
        					frmSemak = new FrmSemakan();
        					frmSemak.semakanTambah(cbsemak[i], idSiasatan);
        					
        				}
        			}
        			

        			// Checkbox PPT-25 (iii) Jenis Tanaman
        			String[] cbsemaks = this.request.getParameterValues("jenistanaman");
        			frmSemak = new FrmSemakan();
        			//frmSemak.semakanHapusByPermohonan(idSiasatan);
        			if (cbsemaks != null) {
        				for (int i = 0; i < cbsemaks.length; i++) {
        					frmSemak = new FrmSemakan();
        					frmSemak.semakanTambah(cbsemaks[i], idSiasatan);
        				}
        			}
        			
        	    updateTuanTanah(session);
        		this.context.put("readmode", "view"); 
        		context.put("selectBangunan",HTMLPPT.SelectBangunan("socBangunan",Utils.parseLong(socBangunan),null,"style=width:auto class=disabled"));
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
   				vm = "app/ppt/frmSek8InfoBicaraTuanTanah.jsp";	
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
   				vm = "app/ppt/frmSek8InfoBicaraAgensi.jsp";	
   			}
        
        	else if ("Tuntutan".equals(sub_command))
   			{ 

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
        			if(bolehsimpan.equals("yes"))
        			{
        				updateTuntutan(session);
        			}
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
	   			
	   			senarai_tuntutan = senarai_tuntutan(getParam("id_siasatan"));
    			context.put("senarai_tuntutan", senarai_tuntutan);
    			
   				vm = "app/ppt/frmSek8InfoBicaraTuntutan.jsp";	
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
   				vm = "app/ppt/frmSek8InfoBicaraBantahan.jsp";	
   			}
        	
        	else if ("PB".equals(sub_command))
   			{ 
        	String idHakmilik = "";
        	idHakmilik = getParam("id_hakmilik");
        		
        	this.context.put("view_pb", "");	
        	this.context.put("tambah_kehadiran_negeri", "");		
        	if ("View".equals(subminor_command))   			
   			{
        		getTotalSyer(idHakmilik,"");
        		
   			} 
        	
        	else if ("Papar".equals(subminor_command))   			
   			{
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "view");	
        		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 				
   				
   			} 
        	
        	else if ("tambah".equals(subminor_command))   			
   			{
        		this.context.put("tambah_kehadiran", "yes");
        		this.context.put("tambah_kehadiran_negeri", "yes");
        		this.context.put("tambah_kehadiran_wakil", "");
        		this.context.put("tambah_kehadiran_negeri_wakil", "");
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit"); 
        		getTotalSyer(idHakmilik,"");
   				
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
        		
        		getTotalSyer(idHakmilik,"");
   				
   			} 
        	
        	else if ("Simpan".equals(subminor_command))   			
   			{
        	
        		if(!getParam("id_hakmilikpb").equals(""))
        		{
        			String BC = this.request.getParameter("boxBorangC");
    				String BE = this.request.getParameter("boxBorangE");
    				String BG = this.request.getParameter("boxBorangG");
    				String BK = this.request.getParameter("boxBorangK");
    				
    				String FC = "";
    				String FE = "";
    				String FG = "";
    				String FK = "";
    				
    				myLogger.info("BC:"+BC+"BE:"+BE+"BG:"+BG+"BK:"+BK);
    				
    			if(BC != null)
    			{
        		FC = "1";
    			}
    			if(BE != null)
    			{
        		FE = "1";
    			}
    			if(BG != null)
    			{
        		FG = "1";
    			}
    			if(BK != null)
    			{
        		FK = "1";
    			}
    			
				logic.updatePBSiasatan(getParam("id_pb"),getParam("txtNamaPB"),getParam("txtNoPB"),getParam("socJenisNOPB"),
						getParam("txtNamaPBKP"),(String) session.getAttribute("_ekptg_user_id"),getParam("socWarga"),getParam("socBangsa"),getParam("txtPenamaPenyata"),"");									
				logic.updateSiasatanPB(getParam("id_hakmilikpb"),getParam("socJenisPB"),getParam("txtNoAkaun"),getParam("socJenisBank"),getParam("txtAlamat1PB"),getParam("txtAlamat2PB"),
						getParam("txtAlamat3PB"),getParam("txtPoskodPB"),getParam("socNegeri"),getParam("socBandar"),(String) session.getAttribute("_ekptg_user_id"),getParam("txtNoHP"),
						getParam("txtNoTel"),getParam("txtNamaBank"),getParam("txtKeteranganPB"),getParam("txtCatatan"),FC,FE,FG,FK,getParam("id_bahagianpb"),getParam("txtSyorAtas")
			    		,getParam("txtSyorBawah"));									
	       		
        		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
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
        				
        				String BC = this.request.getParameter("boxBorangC");
        				String BE = this.request.getParameter("boxBorangE");
        				String BG = this.request.getParameter("boxBorangG");
        				String BK = this.request.getParameter("boxBorangK");
        				
        				String FC = "";
        				String FE = "";
        				String FG = "";
        				String FK = "";
        				
        				myLogger.info("BC:"+BC+"BE:"+BE+"BG:"+BG+"BK:"+BK);
        				
        			if(BC != null)
        			{
            		FC = "1";
        			}
        			if(BE != null)
        			{
            		FE = "1";
        			}
        			if(BG != null)
        			{
            		FG = "1";
        			}
        			if(BK != null)
        			{
            		FK = "1";
        			}		
        				
        				
        				
        				
        	logic.addSiasatanPB(getParam("id_pb"),getParam("txtNamaPB"),getParam("txtNoPB"),getParam("socJenisNOPB"),getParam("txtNamaPBKP"),(String) session.getAttribute("_ekptg_user_id"),
        	getParam("id_hakmilikpb"),getParam("socJenisPB"),getParam("txtNoAkaun"),getParam("socJenisBank"),getParam("txtAlamat1PB"),getParam("txtAlamat2PB"),getParam("txtAlamat3PB"),getParam("txtPoskodPB"),
        	getParam("socNegeri"),getParam("socBandar"),(String) session.getAttribute("_ekptg_user_id"),getParam("txtNoHP"),getParam("txtNoTel"),getParam("id_siasatan"),getParam("id_hakmilik"),getParam("txtPBhadir"),getParam("txtNamaBank"),getParam("txtKeteranganPB"),getParam("txtCatatan"),FC,FE,FG,FK,getParam("txtSyorAtas")
    		,getParam("txtSyorBawah"),getParam("socWarga"),getParam("socBangsa"),getParam("id_bahagianpb"),getParam("txtPenamaPenyata"),"");
					}
        				
        		}
        		getTotalSyer(idHakmilik,"");		
   			} 
        	else if ("DeleteTurutHadir".equals(subminor_command))   			
   			{	this.context.put("view_pb", "");	
        		this.context.put("readmode", "");
        	
        		logic.deleteMaklumatPB(getParam("id_pb"));        	
        		logic.deleteHakmilikPB(getParam("id_hakmilikpb")); 
        	}
        	else if ("Kemaskini".equals(subminor_command))   			
   			{
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");	
        		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 	
   			}
        	else if ("batal".equals(subminor_command))   			
   			{
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");   					
        		if(getParam("id_hakmilikpb")!="" && getParam("id_hakmilikpb")!= null)
        		{
        		maklumat_kehadiran = logic.maklumat_PB(getParam("id_hakmilikpb"));	
        		this.context.put("maklumat_kehadiran",maklumat_kehadiran);
   				Hashtable h = (Hashtable) maklumat_kehadiran.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
				{	
				list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
				this.context.put("list_bandar",list_bandar);
				} 
        		}
   				
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
			
			
			String BC = this.request.getParameter("boxBorangC");
			String BE = this.request.getParameter("boxBorangE");
			String BG = this.request.getParameter("boxBorangG");
			String BK = this.request.getParameter("boxBorangK");
			
			String FC = "";
			String FE = "";
			String FG = "";
			String FK = "";
			
			myLogger.info("BC:"+BC+"BE:"+BE+"BG:"+BG+"BK:"+BK);
			
			if(BC != null)
			{
			FC = "checked";
			}
			if(BE != null)
			{
			FE = "checked";
			}
			if(BG != null)
			{
			FG = "checked";
			}
			if(BK != null)
			{
			FK = "checked";
			}				
			this.context.put("boxBorangC",FC);
			this.context.put("boxBorangE",FE);
			this.context.put("boxBorangG",FG);
			this.context.put("boxBorangK",FK);
			}
        	
   			else if ("Simpan_Borang".equals(subminor_command))
			{     	 
   					
				String[] ids1 = this.request.getParameterValues("ids1");
				String[] idPB = this.request.getParameterValues("idPB");				
				String[] borangE = this.request.getParameterValues("borangE");
				String[] borangG = this.request.getParameterValues("borangG");
				String[] borangK = this.request.getParameterValues("borangK");
				
				if (idPB != null) {
					
					for (int i = 0; i < idPB.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateBorang(idPB[i],(String) session.getAttribute("_ekptg_user_id"),"clear");  									
							}
					}
				 }	
				
				if (ids1 != null) {
					
					
					for (int i = 0; i < ids1.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateBorang(ids1[i],(String) session.getAttribute("_ekptg_user_id"),"borangC");  									
							}
					}
				 }	
				
				if (borangE != null) {
					
					
					for (int i = 0; i < borangE.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateBorang(borangE[i],(String) session.getAttribute("_ekptg_user_id"),"borangE");  									
							}
					}
				 }	
				
				if (borangG != null) {
					
					
					for (int i = 0; i < borangG.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateBorang(borangG[i],(String) session.getAttribute("_ekptg_user_id"),"borangG");  									
							}
					}
				 }	
				
				if (borangK != null) {
					
					
					for (int i = 0; i < borangK.length; i++) {						
							if (bolehsimpan.equals("yes")) 
							{
								logic.updateBorang(borangK[i],(String) session.getAttribute("_ekptg_user_id"),"borangK");  									
							}
					}
				 }	
				
								
				
				
				
				
					
			}  	
        	//check size bahagian pb
        	model.setSizeBahagianPB(idHakmilik);
    		checkSizeBahagianPB = model.getSizeBahagianPB();	
        	context.put("checkSizeBahagianPB_size",checkSizeBahagianPB.size());
        	
        	getTotalSyer_ALL(idHakmilik,"");
        	
        	senarai_pihak_penting_bahagian = logic.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
			this.context.put("senarai_pihak_penting_bahagian",senarai_pihak_penting_bahagian);	
        	
   			context.put("id_siasatan",getParam("id_siasatan"));
   			//list_kehadiran = logic.list_PB(getParam("id_hakmilik"),getParam("CariPB"));
   			//this.context.put("list_kehadiran",list_kehadiran);
   			list_kehadiran_th = logic.list_siasatan_pb(getParam("id_siasatan"));
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
   			this.context.put("tajuk_header","MAKLUMAT PIHAK BERKEPENTINGAN");
   			
   			
   			list_bangsa = logic.getListbangsa();
   		   	context.put("list_bangsa",list_bangsa);   
   					
   		   	list_warga = logic.getListwarga();
   		   	context.put("list_warga",list_warga); 
   		   	
   			
		   	list_negeri = logic.getListnegeri();
	       	context.put("list_negeri",list_negeri);	

   			
   			
		    vm = "app/ppt/frmSek8InfoBicaraPB.jsp";	
				
   			}
        	
        	
        	
        	//TODO
        	
        	
        	else if ("Nilaian".equals(sub_command))
   			{ 
        	
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command)){
        			Hashtable h = (Hashtable) maklumat_siasatan.get(0);
        			if(h.get("HARGA_SEUNIT_SO").toString().equals("") && h.get("UNIT_HARGA_SO").toString().equals("") && h.get("HARGA_PASARAN_SO").toString().equals("") && h.get("ID_TANAH").toString().equals("")
        					&& h.get("BAYAR_PENJEJASAN").toString().equals("") && h.get("BAYAR_PECAHPISAH").toString().equals("") && h.get("BAYAR_NAIK_NILAISO").toString().equals("")
        					&& h.get("HARGA_SEUNIT_JPPH").toString().equals("") && h.get("UNIT_HARGA").toString().equals("") && h.get("HARGA_PASARAN").toString().equals("")
        					&& h.get("AMAUN_PENJEJASAN_JPPH").toString().equals("") && h.get("AMAUN_PECAHPISAH_JPPH").toString().equals("") && h.get("NAIK_NILAI_JPPH").toString().equals("") && h.get("STRUKTUR_BANGUNAN").toString().equals("")){
        				Hashtable hh = (Hashtable) maklumat_siasatan.get(0);	   			
                		if(Integer.parseInt(hh.get("BIL_SIASATAN").toString())>1){
	        	   			maklumat_siasatan_history = logic.maklumat_siasatan_history(hh.get("ID_PERMOHONAN").toString(),hh.get("ID_HAKMILIK").toString(),Integer.parseInt(hh.get("BIL_SIASATAN").toString())-1);
	        	   			this.context.put("maklumat_siasatan_history",maklumat_siasatan_history);
        	   			}
        				this.context.put("readmode", "edit");   
            		} else {
            			if(!h.get("FLAG_TUGASAN").toString().equals("")){
            				if(h.get("FLAG_TUGASAN").toString().equals("S")){
                    			this.context.put("maklumat_siasatan_history",maklumat_siasatan);
            				}
            			}
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
        		
        			if(getParam("id_tanah") == "" )
        			{
        				addNilaian(session);
        			}
        			else
        			{
        				updateNilaian(session);
        			}
        			
        		this.context.put("readmode", "view");   
        		
   				}else if("hantarJPPH".equals(subminor_command)) {
   					
   					
   					hantar(session,getParam("id_tanah"),"Tblppttanah","H");
   					

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
   				vm = "app/ppt/frmSek8InfoBicaraNilaian.jsp";	
   			}
        	
        	else if ("Status_Siasatan".equals(sub_command))
   			{       		
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));			
        		if ("View".equals(subminor_command))
   				{
        			
        			//get tarikh siasatan sebelum
        			this.context.put("id_siasatanX","");
        			
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
        		else if ("cetakUlang".equals(subminor_command))
   				{
        			updateCetakUlang(session);
        			this.context.put("readmode", "view"); 
   				}
        		else if ("Simpan".equals(subminor_command))
   				{
        			
        			//get size suburusanhakmilik
   		    		String id_suburusanstatushakmilik = "";
   		    		String id_suburusanstatus = "";
   		    		modelUPT.setDataSuburusanHakmilik(getParam("id_hakmilik"));
   		    		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
   		    		if(dataSuburusanHakmilik.size()!=0){
   		    			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
   		    			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
   		    			id_suburusanstatus = (String)dsh.get("id_suburusanstatus");
   		    		}
        			
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
        			
        			if (bolehsimpan.equals("yes") && getParam("socStatusSiasatan").equals("6")){
        				if(!id_suburusanstatus.equals("16102724")){
        					updateSuburusanHakmilik(session,idpermohonan,id_fail,getParam("id_hakmilik"),id_suburusanstatushakmilik,"16102724");
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
        	    //penambahan yati

        	  
        	    list_negeri = logic.getListnegeri();
        	   	context.put("list_negeri",list_negeri);
        	   
        		maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_permohonan"));	
            	this.context.put("tarikh_akhir_tampalX",tarikh_akhir_tampal);  				
   				this.context.put("nk_add",""); 
   				
   				Hashtable h2 = (Hashtable) maklumat_siasatan.get(0);
   				
   				maklumat_siasatanX = logic.maklumat_siasatanX(getParam("id_hakmilik"),Integer.parseInt(h2.get("BIL_SIASATAN").toString())+1);
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
	   		
	   			
	   			list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	   			this.context.put("list_pegawai",list_pegawai);	
	   			
	   			list_subsiasatan = logic.list_subsiasatan(getParam("id_siasatan"),"ALASAN_TANGGUH");
	   			this.context.put("list_subsiasatan",list_subsiasatan);
	   			
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
	   			vm = "app/ppt/frmSek8InfoBicaraStatusSiasatan.jsp";	
   			}
        	
        	else if ("Keputusan".equals(sub_command))
   			{  
        		this.context.put("flag_open_award", "");
        		this.context.put("id_siasatan_award", "");
        		this.context.put("id_siasatan_hakmilikpb", ""); 
        		this.context.put("nama_pb", "");
        		this.context.put("jenis_nopb", "");
        		this.context.put("nopb", "");
        		this.context.put("no_lot", "");
        		
        		this.context.put("id_bahagianbp", "");
        		this.context.put("syer_atas", "");
        		this.context.put("syer_bawah", "");
        		this.context.put("pampasani",getParam("pampasani"));
        		
        		this.context.put("id_hakmilik",getParam("id_hakmilik"));
        		
        		
        		myLogger.info("pampasani----"+getParam("pampasani"));
        		myLogger.info("id_hakmilik----"+getParam("id_hakmilik"));
        		
        		
        		
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
        			this.context.put("nama_pb", "");
            		this.context.put("jenis_nopb", "");
            		this.context.put("nopb", "");
            		this.context.put("no_lot", "");	
            		this.context.put("id_bahagianbp", "");
            		this.context.put("syer_atas", "");
            		this.context.put("syer_bawah", "");
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
		    		at.logActivity("","1",null,session,"UPD","SIASATAN AWARD [NO. KES : "+NO_KES_temp+"] KEMASKINI");
		    			
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
        			String[] temp_nilai =this.request.getParameterValues("temp_nilai");
        			
        			
        			//get size suburusanhakmilik
   		    		String id_suburusanstatushakmilik = "";
   		    		String id_suburusanstatus = "";
   		    		modelUPT.setDataSuburusanHakmilik(getParam("id_hakmilik"));
   		    		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
   		    		if(dataSuburusanHakmilik.size()!=0){
   		    			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
   		    			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
   		    			id_suburusanstatus = (String)dsh.get("id_suburusanstatus");
   		    		}
   		    		
        			if (bolehsimpan.equals("yes")){ 
        				if(!id_suburusanstatus.equals("1483")){
        					updateSuburusanHakmilik(session,idpermohonan,id_fail,getParam("id_hakmilik"),id_suburusanstatushakmilik,"1483");
    					}
        			}
        		
        		    String id_award_sementara = ""; 
        		   // String temp_nilai =getParam("txtPeratusJejas");
        			
    				if (id_hakmilik_pb != null) {
    					for (int i = 0; i < id_hakmilik_pb.length; i++) {
    						
    							if (bolehsimpan.equals("yes")) 
    							{
    								/*
    								if(id_status.equals("38") || id_status.equals("74")){
										logic.updateStatus(idpermohonan,id_user);
										updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
									}
									*/
    								if(modelUPT.cekStatusFailDahWujud(idpermohonan,"62","52")==false)
    			            		{
    			        			modelUPT.updateStatus(idpermohonan,id_user, "62");
    			        			updateSuburusanStatusFailPPT(session,idpermohonan,id_fail,id_suburusanstatusfailppt);
    			            		}
    								
    								headerSek8.setDataHeader(idpermohonan);
									dataHeader = headerSek8.getDataHeader();
									context.put("dataHeader", dataHeader);
									if(dataHeader.size()!=0){
										Hashtable dh = (Hashtable) dataHeader.get(0);
										id_status = (String)dh.get("id_status");
									}
									
    								if(!id_award[i].equals(""))
    								{
    								String id_siasatan = getParam("id_siasatan"); 
    								logic.updateAwardPerintah(id_siasatan,id_status_penerima[i],id_award[i], id_hakmilik_pb[i],pampasan[i], (String) session.getAttribute("_ekptg_user_id"),temp_xhadir[i],temp_perintah[i],temp_nilai[i]);
    								}
    								else
    								{
    									
    								if(id_hakmilik_pb[i].equals(getParam("id_siasatan_hakmilikpb")))
    			    				{
    									
    							    	String id_siasatan = getParam("id_siasatan");    									
    									long id_award_temp = DB.getNextID("TBLPPTAWARD_SEQ");  
    									id_award_sementara = id_award_temp+""; 
    									
    									
    									myLogger.info("id_award_temp:::"+id_award_temp);
    									
    								
        						    logic.addAwardPerintah(id_status_penerima[i],id_siasatan,id_award_temp, id_hakmilik_pb[i], pampasan[i], (String) session.getAttribute("_ekptg_user_id"),temp_xhadir[i],temp_perintah[i],temp_nilai[i]);
    								
        						    //alert paging 
        						    context.put("showAlertPaging","yes");
        						    
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
    			    								logic.simpan_subAward(txtUlasanKerosakan[t],txtUlasanKerosakanAward[t],id_award_temp,"BAYAR_TANAH",(String) session.getAttribute("_ekptg_user_id"));
    			    							
    			    								
    			    							
    			    							
    			    							
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
    			        			
    			        			String[] txtUlasanprosiding = this.request.getParameterValues("txtUlasanprosiding");
    			        		    String[] txtUlasanprosidingAward = this.request.getParameterValues("txtUlasanprosidingAward");    			        		    
    			        			if (txtUlasanKerosakan != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanprosiding.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanprosiding[t],txtUlasanprosidingAward[t],id_award_temp,"BAYAR_BANGGUNAN",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			
    			        			String[] txtUlasanpecahpisah = this.request.getParameterValues("txtUlasanpecahpisah");
    			        		    String[] txtUlasanpecahpisahAward = this.request.getParameterValues("txtUlasanpecahpisahAward");    			        		    
    			        			if (txtUlasanKerosakan != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanpecahpisah.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanpecahpisah[t],txtUlasanpecahpisahAward[t],id_award_temp,"BAYAR_PECAHPISAH",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			String[] txtUlasanpenjejasan = this.request.getParameterValues("txtUlasanpenjejasan");
    			         		    String[] txtUlasanpenjejasanAward = this.request.getParameterValues("txtUlasanpenjejasanAward");
    			         		    
    			         			if (txtUlasanpenjejasan != null ) {
    			     					for (int t = 0; t < txtUlasanpenjejasan.length; t++) {
    			     						
    			     							if (bolehsimpan.equals("yes")) 
    			     							{
    			     								
    			     								logic.simpan_subAward(txtUlasanpenjejasan[t],txtUlasanpenjejasanAward[t],id_award_temp,"BAYAR_PENJEJASAN",(String) session.getAttribute("_ekptg_user_id"));
    			     								
    			     							}
    			     						}
    			     					}
    			         			

    			         			String[] txtUlasankenaikan = this.request.getParameterValues("txtUlasankenaikan");
    			         		    String[] txtUlasankenaikanAward = this.request.getParameterValues("txtUlasankenaikanAward");
    			         		    
    			         			if (txtUlasankenaikan != null ) {
    			     					for (int t = 0; t < txtUlasankenaikan.length; t++) {
    			     						
    			     							if (bolehsimpan.equals("yes")) 
    			     							{
    			     								
    			     								logic.simpan_subAward(txtUlasankenaikan[t],txtUlasankenaikanAward[t],id_award_temp,"BAYAR_KENAIKAN",(String) session.getAttribute("_ekptg_user_id"));
    			     								
    			     							}
    			     						}
    			     					}
    			        			
    			        			//open yati
    			        			String[] txtUlasanLain = this.request.getParameterValues("txtUlasanLain");
    			        		    String[] txtUlasanLainAward = this.request.getParameterValues("txtUlasanLainAward");
    			        		 
    			        			if (txtUlasanLain != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanLain.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanLain[t],txtUlasanLainAward[t],id_award_temp,"BAYAR_LAIN",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}
    			        			
    			        			String[] txtUlasanPenilai = this.request.getParameterValues("txtUlasanPenilai");
    			        		    String[] txtUlasanPenilaiAward = this.request.getParameterValues("txtUlasanPenilaiAward");  
    			        		
    			        			if (txtUlasanPenilai != null ) 
    			        			{
    			    					for (int t = 0; t < txtUlasanPenilai.length; t++) 
    			    					    {    			    						
    			    							if (bolehsimpan.equals("yes")) 
    			    							{    			    								
    			    								logic.simpan_subAward(txtUlasanPenilai[t],txtUlasanPenilaiAward[t],id_award_temp,"BAYAR_PENILAI",(String) session.getAttribute("_ekptg_user_id"));
    			    							}
    			    						}
    			    				}			        			
    			        			//close yati
    			    				
    			        			    			        			
    			        			
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
    		        			this.context.put("nama_pb", "");
    		            		this.context.put("jenis_nopb", "");
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
    								
    								logic.simpan_subAward(txtUlasanKerosakan[i],txtUlasanKerosakanAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_TANAH",(String) session.getAttribute("_ekptg_user_id"));
    								
    							}
    						}
    					} 
        			
        			
        			String[] txtUlasanprosiding = this.request.getParameterValues("txtUlasanprosiding");
         		    String[] txtUlasanprosidingAward = this.request.getParameterValues("txtUlasanprosidingAward");
         		    
         			if (txtUlasanprosiding != null ) {
     					for (int i = 0; i < txtUlasanprosiding.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanprosiding[i],txtUlasanprosidingAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_BANGGUNAN",(String) session.getAttribute("_ekptg_user_id"));
     								
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
         			
         			
         			String[] txtUlasanpecahpisah = this.request.getParameterValues("txtUlasanpecahpisah");
         		    String[] txtUlasanpecahpisahAward = this.request.getParameterValues("txtUlasanpecahpisahAward");
         		    
         			if (txtUlasanpecahpisah != null ) {
     					for (int i = 0; i < txtUlasanpecahpisah.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanpecahpisah[i],txtUlasanpecahpisahAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_PECAHPISAH",(String) session.getAttribute("_ekptg_user_id"));
     								
     							}
     						}
     					}
        			
         			

         			String[] txtUlasanpenjejasan = this.request.getParameterValues("txtUlasanpenjejasan");
         		    String[] txtUlasanpenjejasanAward = this.request.getParameterValues("txtUlasanpenjejasanAward");
         		    
         			if (txtUlasanpenjejasan != null ) {
     					for (int i = 0; i < txtUlasanpenjejasan.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanpenjejasan[i],txtUlasanpenjejasanAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_PENJEJASAN",(String) session.getAttribute("_ekptg_user_id"));
     								
     							}
     						}
     					}
         			

         			String[] txtUlasankenaikan = this.request.getParameterValues("txtUlasankenaikan");
         		    String[] txtUlasankenaikanAward = this.request.getParameterValues("txtUlasankenaikanAward");
         		    
         			if (txtUlasankenaikan != null ) {
     					for (int i = 0; i < txtUlasankenaikan.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasankenaikan[i],txtUlasankenaikanAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_KENAIKAN",(String) session.getAttribute("_ekptg_user_id"));
     								
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
         			
         			//open yati
         			String[] txtUlasanPenilai = this.request.getParameterValues("txtUlasanPenilai");
         		    String[] txtUlasanPenilaiAward = this.request.getParameterValues("txtUlasanPenilaiAward");
         		    
         			if (txtUlasanPenilai != null ) {
     					for (int i = 0; i < txtUlasanPenilai.length; i++) {
     						
     							if (bolehsimpan.equals("yes")) 
     							{
     								
     								logic.simpan_subAward(txtUlasanPenilai[i],txtUlasanPenilaiAward[i],Long.parseLong(getParam("id_siasatan_award")),"BAYAR_PENILAI",(String) session.getAttribute("_ekptg_user_id"));
     								
     							}
     						}
     					}
         			//close yati
         			
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
        		
        //		this.context.put("txtUlasanTidakHadir", maklumat_pb.get("ULASAN_TIDAK_HADIR").toString());
        		
        		        		
        		this.context.put("id_siasatan_award", getParam("id_siasatan_award"));
        		this.context.put("id_siasatan_hakmilikpb", getParam("id_siasatan_hakmilikpb")); 
        		
   				}  
        		
	   			context.put("id_siasatan",getParam("id_siasatan"));   			
	   			maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
	   			this.context.put("maklumat_siasatan",maklumat_siasatan); 
	   			senarai_pihak_penting_pampasan_perintah = logic.senarai_pihak_penting_pampasan_perintah(getParam("id_permohonan"),getParam("id_siasatan"),getParam("CariPB"));
	   			this.context.put("senarai_pihak_penting_pampasan_perintah",senarai_pihak_penting_pampasan_perintah);	
	   				
	   			
	   			senarai_pihak_penting_bahagian = logic.senarai_pihak_penting_bahagian(getParam("id_hakmilik"));
				this.context.put("senarai_pihak_penting_bahagian",senarai_pihak_penting_bahagian);
	   			
				list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	   			this.context.put("list_pegawai",list_pegawai);	
				
	   			this.context.put("tajuk_header","MAKLUMAT SIASATAN");
   				vm = "app/ppt/frmSek8InfoBicaraKeputusan.jsp";	
   			}
        	
        	else if ("Kehadiran".equals(sub_command))
   			{ 
        	this.context.put("view_pb", "");	
        	this.context.put("tambah_kehadiran_negeri", "");		
        	if ("View".equals(subminor_command))   			
   			{
   						
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   			
   				String id_permohonan = getParam("id_permohonan");
   				String id_siasatan = getParam("id_siasatan");
   				myLogger.info("id_siasatan---"+id_siasatan);
   				
   				senarai_siasatan = model.senarai_siasatan(id_permohonan,getParam("id_siasatan"));
   				this.context.put("senarai_siasatan", senarai_siasatan);	
   				
   				String bilsiasatan = getParam("bil_siasatan");
   				this.context.put("bilsiasatan", bilsiasatan);
   				
   				
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
   				
   				senarai_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   				this.context.put("senarai_siasatan", senarai_siasatan);		
   			
   				
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
   				
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
   			} 
        	
        	else if ("tambah_wakil".equals(subminor_command))   			
   			{
        		this.context.put("tambah_kehadiran_wakil", "yes");
        		this.context.put("tambah_kehadiran_negeri_wakil", "yes");
        		this.context.put("tambah_kehadiran", "");
        		this.context.put("tambah_kehadiran_negeri", "");
        		this.context.put("view_pb", "yes");	
        		this.context.put("readmode", "edit");        		
   				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");  
   				
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
   				
   				
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
			vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
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
				String id_permohonan = getParam("id_permohonan");
				senarai_siasatan = model.senarai_siasatan(id_permohonan,getParam("id_siasatan"));
   				this.context.put("senarai_siasatan", senarai_siasatan);	
				
				String bilsiasatan = getParam("bil_siasatan");
   				this.context.put("bilsiasatan", bilsiasatan);
				
				this.context.put("tajuk_header","MAKLUMAT KEHADIRAN SIASATAN");
   				vm = "app/ppt/frmSek8SiasatanKehadiran.jsp";	
			} 
        	
        	
        	list_negeri = logic.getListnegeri();
           	context.put("list_negeri",list_negeri);
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
   			}
        	
        	else if ("RecordSiasatan".equals(sub_command))
   			{	
        		
        		maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
        		int countTDK = 0;
        		String flag_stop_siasatan = "";
        		if(maklumat_hakmilik.size()!=0){
        			Hashtable mh = (Hashtable)maklumat_hakmilik.get(0);	
        			countTDK = (Integer)mh.get("COUNT_TDK");
        			flag_stop_siasatan = (String)mh.get("FLAG_STOP_SIASATAN");
        		}
        		this.context.put("maklumat_hakmilik",maklumat_hakmilik);
        		this.context.put("countTDK",countTDK);
        		this.context.put("flag_stop_siasatan",flag_stop_siasatan);
        		this.context.put("id_hakmilik",getParam("id_hakmilik"));	
        		
        		
        		this.context.put("nk_add","");	
        		this.context.put("record_siasatan","");	
        		if ("View".equals(subminor_command))
   				{        	    
   				this.context.put("readmode", "edit");  				 				
   				this.context.put("tajuk_header","SENARAI SIASATAN");    				
   				}
        		else if ("tambah".equals(subminor_command))
   				{ 
        		
        		//reset value
        		resetValue();
        		
        		tarikh_akhir_tampal = logic.tarikh_akhir_tampal(getParam("id_permohonan"));	
        		this.context.put("tarikh_akhir_tampal",tarikh_akhir_tampal);	
   				this.context.put("readmode", "edit");
   				this.context.put("nk_add","yes");
   				this.context.put("record_siasatan","yes");	
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);
   				this.context.put("tajuk_header","SENARAI SIASATAN");   
   				
   				//get default data from tblpptborange
   				getAndSetDefaultDataBorangE(getParam("id_hakmilik"));
   				
   				
   				
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
        		context.put("id_permohonan",getParam("id_permohonan"));	
   				this.context.put("readmode", "view");   				
   				this.context.put("record_siasatan","yes");   				
   				maklumat_siasatan = logic.maklumat_siasatan(getParam("id_siasatan"));
   				this.context.put("maklumat_siasatan",maklumat_siasatan);
   				maklumat_hakmilik = logic.senarai_hakmilik_batal_penarikan_maklumat(getParam("id_permohonan"),getParam("id_hakmilik"));
   				this.context.put("maklumat_hakmilik",maklumat_hakmilik);   				
   				Hashtable h = (Hashtable) maklumat_siasatan.get(0);				
   				if(!h.get("ID_NEGERI").toString().equals(""))
   				{
   	   				String idBandar = (String) h.get("ID_BANDAR");	
   					list_bandar = logic.getListBandarByNegeri(h.get("ID_NEGERI").toString());
   					this.context.put("list_bandar",list_bandar);
   					this.context.put("idBandar",idBandar);
   					
				} 
   				
   				senarai_pihak_penting = logic.senarai_pihak_penting(getParam("id_permohonan"));
   				this.context.put("senarai_pihak_penting",senarai_pihak_penting); 
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
   		    		
   		    		//get size suburusanhakmilik
   		    		String id_suburusanstatushakmilik = "";
   		    		String id_suburusanstatus = "";
   		    		modelUPT.setDataSuburusanHakmilik(getParam("id_hakmilik"));
   		    		dataSuburusanHakmilik = modelUPT.getDataSuburusanHakmilik();
   		    		if(dataSuburusanHakmilik.size()!=0){
   		    			Hashtable dsh = (Hashtable)dataSuburusanHakmilik.get(0);
   		    			id_suburusanstatushakmilik = (String)dsh.get("id_suburusanstatushakmilik");
   		    			id_suburusanstatus = (String)dsh.get("id_suburusanstatus");
   		    		}
   		    		
   		    	if(getParam("id_siasatan").equals(""))
   		    	{
   		    		if(bolehsimpan=="yes")
					{
   		    			addMaklumatSiasatan(session);
   		    			
   		    				    			
   		    			AuditTrail at = new AuditTrail();
   		    			at.logActivity("","1",null,session,"INS","SIASATAN [NO. KES : "+getParam("txtNoKes")+"] INSERT");
   		    			
   		    			if(!id_suburusanstatus.equals("16102723")){
	        				updateSuburusanHakmilik(session,idpermohonan,id_fail,getParam("id_hakmilik"),id_suburusanstatushakmilik,"16102723");
	        			}
					}
   		    	}
   		    	else
   		    	{
   		    		
   		    		
   		    		
   		    		if(bolehsimpan=="yes"){
   		    			if(!id_suburusanstatus.equals("16102723")){
   	        				updateSuburusanHakmilik(session,idpermohonan,id_fail,getParam("id_hakmilik"),id_suburusanstatushakmilik,"16102723");
   	        			}
					}
   		    		
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
	    		at.logActivity("","1",null,session,"DEL","SIASATAN [NO. KES :"+NO_KES_temp+"] DELETE");
	    			
	    		} finally {
	    			if (db != null)
	    				db.close();
	    		}		
	    		
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
							
								Db db = null;
					    		String NO_KES_temp = "";
					    		try {
					    		db = new Db();
					    		Statement stmt = db.getStatement();
					    		String sql = " SELECT NO_KES FROM TBLPPTSIASATAN " +
					    				"" +
					    				" WHERE ID_SIASATAN = '"+ids1[i]+"'";			
					    		ResultSet rs = stmt.executeQuery(sql);	
					    		myLogger.info("SQL  :"+sql);
					    		while (rs.next()){				
					    			NO_KES_temp = rs.getString("NO_KES");				
					    	    }
					    	    AuditTrail at = new AuditTrail();
					    		at.logActivity("","1",null,session,"DEL","SIASATAN [NO. KES : "+NO_KES_temp+"] DELETE");
					    			
					    		} finally {
					    			if (db != null)
					    				db.close();
					    		}								
								
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
        		
   		   vm = "app/ppt/frmSek8SetSenaraiSiasatan.jsp";
        		
        		
   			}        	 
	
      		context.put("id_permohonan",getParam("id_permohonan"));
      		context.put("id_pembatalan",getParam("id_pembatalan"));	
      		context.put("id_hakmilik",getParam("id_hakmilik"));	
      		jenis_button = "5";
   			header = logic.content_header(getParam("id_permohonan"));   			
   			this.context.put("header",header);	
   			
   			
   			
   			//list_negeri = logic.getListnegeri();
   			//context.put("list_negeri",list_negeri);   			
   			//list_bangsa = logic.getListbangsa();
   			//context.put("list_bangsa",list_bangsa);   			
   			//list_warga = logic.getListwarga();
   			//context.put("list_warga",list_warga);   			
			//list_bandar_all = logic.list_bandar_all();
			//this.context.put("list_bandar_all",list_bandar_all);
   			//list_pegawai = logic.list_pegawai((String) session.getAttribute("_ekptg_user_negeri"),"1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
   			//this.context.put("list_pegawai",list_pegawai);	
			
   			
   			
//   			maklumat_penyediaan = logic.maklumat_penyediaan(getParam("id_permohonan"));
//   			this.context.put("maklumat_penyediaan", maklumat_penyediaan); 	
//   			maklumat_keputusan = logic.maklumat_keputusan(getParam("id_permohonan"));
//   			this.context.put("maklumat_keputusan", maklumat_keputusan); 
   			
   			//carian lot
   			//String carianLotPB = getParam("CariLot");
   			//context.put("CariLot", carianLotPB.trim());
   			
   			//senarai_hakmilik_batal = logic.senarai_hakmilik_batal_penarikan(getParam("id_permohonan"),carianLotPB);
			//this.context.put("senarai_hakmilik_batal",senarai_hakmilik_batal);
			
			
			
			/*command by razman */
			//senarai_pihak_penting = logic.senarai_pihak_penting(getParam("id_permohonan"));
			//this.context.put("senarai_pihak_penting",senarai_pihak_penting);	
			
			
   			if(getParam("id_hakmilik")!=null && getParam("id_hakmilik")!="")
			{
			//list_siasatan = logic.list_siasatan(getParam("id_permohonan"),getParam("id_hakmilik"));
			//this.context.put("list_siasatan",list_siasatan);
			}
			
			
			
//			
//			
//			tempat_tampal = logic.tempat_tampal(getParam("id_permohonan"));
//			this.context.put("tempat_tampal",tempat_tampal);
			
			if(getParam("id_siasatan")!=null && getParam("id_siasatan")!="")
			{
//			maklumat_hakmilik_mmk_pb_siasatan = logic.maklumat_hakmilik_mmk_pb_siasatan(getParam("id_siasatan"),getParam("id_permohonan"));
//			this.context.put("maklumat_hakmilik_mmk_pb_siasatan",maklumat_hakmilik_mmk_pb_siasatan);
//			
//			maklumat_hakmilik_mmk_pemilik_siasatan = logic.maklumat_hakmilik_mmk_pemilik_siasatan(getParam("id_siasatan"),getParam("id_permohonan"));
//			this.context.put("maklumat_hakmilik_mmk_pemilik_siasatan",maklumat_hakmilik_mmk_pemilik_siasatan);			
			}
		//	list_pegawai = logic.list_pegawai("1",(String) session.getAttribute("_ekptg_user_negeri"),(String) session.getAttribute("_portal_role"));
	   	//    this.context.put("list_pegawai",list_pegawai);
	   	    
	   	 
        	
         }
		else
		
		{
			//myLogger.info("MASUK");				
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
			vm = "app/ppt/frmSek8SiasatanCarian.jsp";	
		    setupPage(session,paging_action,listdepan);	
		    //(String) session.getAttribute("_ekptg_user_id")
		    //(String) session.getAttribute("_portal_role_")
		    //(String) session.getAttribute("_ekptg_user_negeri")
		    
		   
			
		
		}	
		
        context.put("id_status", id_status);		
        context.put("id_permohonan", idpermohonan);	
        
		this.context.put("jenis_button", jenis_button);	
		myLogger.info("VM:"+vm);
	    return vm;	
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
	
	@SuppressWarnings("unused")
	private void resetValue() throws Exception{
		
		context.put("txtAlamat1","");
		context.put("txtAlamat2","");
		context.put("txtAlamat3","");
		context.put("txtPoskod","");
		context.put("socNegeriSiasatan","");
		context.put("socBandarSiasatan","");
		context.put("txdTarikhSiasatan","");
		context.put("txtMasaSiasatan","");
		context.put("jeniswaktu","");
		context.put("list_bandar","");
		
	}//close resetValue
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetDefaultDataBorangE(String id_hakmilik) throws Exception{
		
		Vector dataBorangE = new Vector();
		Vector list_bandar = new Vector();
		
		dataBorangE.clear();
		list_bandar.clear();
		
		String txtAlamat1 = "";
		String txtAlamat2 = "";
		String txtAlamat3 = "";
		String txtPoskod = "";
		String id_negeri = "";
		String id_bandar = "";
		String txdTarikhSiasatan = "";
		String txtMasaSiasatan = "";
		String jeniswaktu = "";
		
		modelBF.setDataBorangEInBulk("",id_hakmilik);
 		dataBorangE = modelBF.getDataBorangEInBulk();
 		if(dataBorangE.size()!=0){
 			Hashtable dbe = (Hashtable)dataBorangE.get(0);
 			txtAlamat1 = (String)dbe.get("alamat1");
 			txtAlamat2 = (String)dbe.get("alamat2");
 			txtAlamat3 = (String)dbe.get("alamat3");
 			txtPoskod = (String)dbe.get("poskod");
 			id_negeri = (String)dbe.get("id_negeri");
 			id_bandar = (String)dbe.get("id_bandar");
 			txdTarikhSiasatan = (String)dbe.get("tarikh_siasatan");
 			txtMasaSiasatan = (String)dbe.get("masa_siasatan");
 			jeniswaktu = (String)dbe.get("jenis_waktu");
 		}
		
 		context.put("txtAlamat1",txtAlamat1);
		context.put("txtAlamat2",txtAlamat2);
		context.put("txtAlamat3",txtAlamat3);
		context.put("txtPoskod",txtPoskod);
		context.put("txdTarikhSiasatan",txdTarikhSiasatan);
		context.put("txtMasaSiasatan",txtMasaSiasatan);
		context.put("jeniswaktu",jeniswaktu);
		context.put("socNegeriSiasatan",id_negeri);
			
		if(!id_negeri.equals("")){	
			
			list_bandar = logic.getListBandarByNegeri(id_negeri);
			context.put("list_bandar",list_bandar);
		
		}else{
			context.put("list_bandar","");	
		}
			
		context.put("socBandarSiasatan",id_bandar);
			
	}//close getAndSetDefaultDataBorangE
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanHakmilik(HttpSession session,String id_permohonan,String id_fail,String id_hakmilik,String id_suburusanstatushakmilik,String idsubhakmilik) throws Exception{
    
		Hashtable h = new Hashtable();
		
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_hakmilik", id_hakmilik);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		modelUPT.updateSuburusanHakmilik(h,id_suburusanstatushakmilik,idsubhakmilik);
	
	}//close addSuburusanHakmilik
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{
    	
		Hashtable h = new Hashtable();
	
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));
		
		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1481");
		
	}//close updateSuburusanStatusFailPPT
	
	 @SuppressWarnings("unchecked")
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
	 
	 @SuppressWarnings("unchecked")
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
	 
	 @SuppressWarnings("unchecked")
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
	 
	 @SuppressWarnings("unchecked")
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
	 
	 
	 @SuppressWarnings("unchecked")
	private void updateTuanTanah(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txdMilikTanah", getParam("txdMilikTanah"));
			h.put("txtCaraMilikTanah", getParam("txtCaraMilikTanah"));
			h.put("txtHargaTanah", getParam("txtHargaTanah"));
			h.put("txtBebananTanah", getParam("txtBebananTanah"));
			h.put("txtKeteranganTuanTanah", getParam("txtKeteranganTuanTanah"));
			h.put("txtJenisTanaman", getParam("txtJenisTanaman"));
			h.put("socBangunan", getParam("socBangunan"));
			h.put("sorPecahSempadan", getParam("sorPecahSempadan"));
			h.put("txdPecahSempadan", getParam("txdPecahSempadan"));
			h.put("sorTukarSyarat", getParam("sorTukarSyarat"));
			h.put("txdTukarSyarat", getParam("txdTukarSyarat"));
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuanTanah(h);		
		}
	 @SuppressWarnings("unchecked")
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
	 
	 @SuppressWarnings("unchecked")
	private void updateAgensi(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", getParam("txtKeteranganAgensi"));
			h.put("txtKeteranganJurunilai", getParam("txtKeteranganJurunilai"));			
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateAgensi(h);		
		}	 
	 @SuppressWarnings("unchecked")
	private void updateAgensiHapus(HttpSession session) throws Exception {
		 
			Hashtable h = new Hashtable();	
			h.put("txtKeteranganAgensi", "");
			h.put("txtKeteranganJurunilai", "");			
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateAgensi(h);		
		}
	 
	 @SuppressWarnings("unchecked")
	 private void updateTuntutan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtTuntutanTuanTanah", getParam("txtTuntutanTuanTanah"));
			h.put("txtLainTuntutan", getParam("txtLainTuntutan"));
			h.put("txtPBDaftar", getParam("txtPBDaftar"));
			h.put("txtPBXDaftar", getParam("txtPBXDaftar"));		
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateTuntutan(h);	
			
			deleteTuntutan(getParam("id_siasatan"));
			
			String[] txtUlasanTUNTUTAN_MAIN = this.request.getParameterValues("txtUlasanTUNTUTAN_MAIN");
			if (txtUlasanTUNTUTAN_MAIN != null ) 
			{
				for (int t = 0; t < txtUlasanTUNTUTAN_MAIN.length; t++){    			    						
						simpan_Tuntutan( getParam("id_siasatan"),txtUlasanTUNTUTAN_MAIN[t],(String) session.getAttribute("_ekptg_user_id"));
				}
			}
			
			
		}
	 
	 public void deleteTuntutan(String id_siasatan) throws Exception {
		Db db = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				String sql = "DELETE FROM TBLPPTTUNTUANPENYEWA WHERE ID_SIASATAN = " + id_siasatan;
				stmt.executeUpdate(sql);
			} finally {
				if (db != null)
					db.close();
			}
		}
	 
	 
	 public void simpan_Tuntutan(String id_siasatan,String ulasan_tuntutan,String user_id) throws Exception {
	  		Db db = null;
	  		String sql = "";
	        try {
	        	db = new Db();  
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_SIASATAN", id_siasatan);
				r.add("KETERANGAN_TUNTUTAN", ulasan_tuntutan);				
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", user_id);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPPTTUNTUANPENYEWA");
				myLogger.info("insert tuntutan"+sql);
				stmt.executeUpdate(sql);           
		    }catch (SQLException se) { 
		    	throw new Exception("Ralat : Masalah simpan rekod");
		    }finally {
			      if (db != null) db.close();
		    }
	  }
	 
	 @SuppressWarnings("unchecked")
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
	 
	 @SuppressWarnings("unchecked")
	 private void updateBantahan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("txtBantahanLain", getParam("txtBantahanLain"));
			h.put("txtBantahanAgensi", getParam("txtBantahanAgensi"));
			h.put("txtBantahanTuanTanah", getParam("txtBantahanTuanTanah"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateBantahan(h);		
		}
	 
	 @SuppressWarnings("unchecked")
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
	 private void addNilaian(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
		 	h.put("txtHargaSeunitSO",getParam("txtHargaSeunitSO"));
		 	h.put("txtUnitHargaSO",getParam("txtUnitHargaSO"));
			h.put("txtHargaPasaranSO",getParam("txtHargaPasaranSO"));
			h.put("txtPenjejasanSO",getParam("txtPenjejasanSO"));
			h.put("txtPecahPisahSO",getParam("txtPecahPisahSO"));
			h.put("txtNaikNilaiSO",getParam("txtNaikNilaiSO"));
			//myLogger.info("txtNaikNilaiSO:"+getParam("txtNaikNilaiSO"));
			h.put("txtHargaSeunitJPPH",getParam("txtHargaSeunitJPPH"));
			h.put("txtUnitHargaJPPH",getParam("txtUnitHargaJPPH"));
			h.put("txtHargaPasaranJPPH",getParam("txtHargaPasaranJPPH"));
			h.put("txtPenjejasanJPPH",getParam("txtPenjejasanJPPH"));
			h.put("txtPecahPisahJPPH",getParam("txtPecahPisahJPPH"));
			h.put("txtNaikNilaiJPPH",getParam("txtNaikNilaiJPPH"));		
			h.put("id_siasatan", getParam("id_siasatan"));		
			h.put("id_hakmilik", getParam("id_hakmilik"));		
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
					
			h.put("txtStrukturBangunan",getParam("txtStrukturBangunan"));	
			
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
	 
	
	 @SuppressWarnings("unchecked")
	 private void updateNilaian(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();
		 	
			h.put("txtHargaSeunitSO",getParam("txtHargaSeunitSO"));
		 	h.put("txtUnitHargaSO",getParam("txtUnitHargaSO"));
			h.put("txtHargaPasaranSO",getParam("txtHargaPasaranSO"));
			h.put("txtPenjejasanSO",getParam("txtPenjejasanSO"));
			h.put("txtPecahPisahSO",getParam("txtPecahPisahSO"));
			h.put("txtNaikNilaiSO",getParam("txtNaikNilaiSO"));
			//myLogger.info("txtNaikNilaiSO:"+getParam("txtNaikNilaiSO"));
			h.put("txtHargaSeunitJPPH",getParam("txtHargaSeunitJPPH"));
			h.put("txtUnitHargaJPPH",getParam("txtUnitHargaJPPH"));
			h.put("txtHargaPasaranJPPH",getParam("txtHargaPasaranJPPH"));
			h.put("txtPenjejasanJPPH",getParam("txtPenjejasanJPPH"));
			h.put("txtPecahPisahJPPH",getParam("txtPecahPisahJPPH"));
			h.put("txtNaikNilaiJPPH",getParam("txtNaikNilaiJPPH"));
			h.put("id_tanah", getParam("id_tanah"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			
			h.put("txtStrukturBangunan",getParam("txtStrukturBangunan"));
			
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
	 
	 @SuppressWarnings("unchecked")
	 private void updateNilaianX(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();
		 	
			h.put("txtHargaSeunitSO","");
		 	h.put("txtUnitHargaSO","");
			h.put("txtHargaPasaranSO","");
			h.put("txtPenjejasanSO","");
			h.put("txtPecahPisahSO","");
			h.put("txtNaikNilaiSO","");
			//myLogger.info("txtNaikNilaiSO:"+getParam("txtNaikNilaiSO"));
			h.put("txtHargaSeunitJPPH","");
			h.put("txtUnitHargaJPPH","");
			h.put("txtHargaPasaranJPPH","");
			h.put("txtPenjejasanJPPH","");
			h.put("txtPecahPisahJPPH","");
			h.put("txtNaikNilaiJPPH","");
			h.put("txtStrukturBangunan","");
			h.put("id_tanah", getParam("id_tanah"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
					
			logic.updateNilaian(h);		
		}
	 
	 
	 @SuppressWarnings("unchecked")
	 private void updateCetakUlang(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateCetakUlang(h);		
		}
	 
	 @SuppressWarnings("unchecked")
	 private void updateStatusSiasatan(HttpSession session) throws Exception {
		 	Hashtable h = new Hashtable();	
			h.put("socPegawaiSiasatan", getParam("socPegawaiSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatan"));
			h.put("txtUlasanSiasatan", getParam("txtUlasanSiasatan"));				
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateStatusSiasatan(h);		
		}
	 
	 @SuppressWarnings("unchecked")
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
			h.put("socPegawaiSiasatan", getParam("socPegawaiSiasatan"));
			h.put("socStatusSiasatan", getParam("socStatusSiasatan"));
			h.put("txtUlasanPerintah", getParam("txtUlasanPerintah"));				
			h.put("socUnit", getParam("socUnit"));
			h.put("txtPeratusJejas", getParam("txtPeratusJejas"));
			h.put("nilai_seunit", getParam("nilai_seunit"));
			h.put("tarikh_perintah", getParam("txdTarikhPerintah"));					
			h.put("id_siasatan", getParam("id_siasatan"));	
			h.put("id_hakmilik", getParam("id_hakmilik"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			
			//tambahan keluasan muktamad  20042012
			h.put("txtLuasMuktamad", getParam("txtLuasMuktamad"));
			h.put("sorDropdownUnitMuktamad", getParam("sorDropdownUnitMuktamad"));
			
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
			h.put("txtPeratusJejas","");
			h.put("id_siasatan", getParam("id_siasatan"));			
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			
			//tambahan keluasan muktamad  20042012
			h.put("txtLuasMuktamad", "");
			h.put("sorDropdownUnitMuktamad", "");
			
			logic.updateKeputusan(h);		
		}
	 
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
	 /*
	 @SuppressWarnings({ "unchecked", "static-access" })
		private void getTotalSyer(String idHakmilik,String idpb) throws Exception{
	    	
			Vector totalSyer = new Vector();
			totalSyer.clear();
			
			String total = "";
			String hideAdd = "";
			model.setTotalSyer(idHakmilik,idpb);
			totalSyer = model.getTotalSyer();
			if(totalSyer.size()!=0){
				Hashtable ts = (Hashtable)totalSyer.get(0);
				total = (String)ts.get("total");
				hideAdd = (String)ts.get("hideAdd");
			}
			
			if(total!="")
			{
	    	context.put("totalSyer", total);
			}
			else
			{
			context.put("totalSyer", 0);				
			}
	    	//context.put("hideAdd", hideAdd);
	    	
	    	if(hideAdd=="yes")
	    	{
	    	//	JOptionPane.showMessageDialog(null, "Bahagian PB Telah Mencukupi.", "Etapp", 2);
	    		
	    	}
	    	else if(hideAdd=="notcomplete")
	    	{
	    	//	JOptionPane.showMessageDialog(null, "Bahagian PB Masih Tidak Mencukupi.", "Etapp", 2);	    		
	    	}
	    	
	    	
	    	
		}//close getTotalSyer
	 */
	 
	/* 
	 @SuppressWarnings({ "unchecked", "static-access" })
		private void getTotalSyer_ALL(String idHakmilik,String idpb) throws Exception{
	    	
			Vector totalSyer = new Vector();
			totalSyer.clear();
			
			String total = "";
			String hideAdd = "";
			model.setTotalSyer(idHakmilik,idpb);
			totalSyer = model.getTotalSyer();
			if(totalSyer.size()!=0){
				Hashtable ts = (Hashtable)totalSyer.get(0);
				total = (String)ts.get("total");
				hideAdd = (String)ts.get("hideAdd");
			}
	    	context.put("totalSyer", total);
	    	
	    	
		}//close getTotalSyer
	 */
	 
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
	 
	 
	 @SuppressWarnings("unchecked")
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
	 
	 @SuppressWarnings({ "unchecked", "static-access" })
		private void getTotalSyer_Temp(String idHakmilik, String idpb)
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

			if (total != "") {
				context.put("totalSyer", total);
			} else {
				context.put("totalSyer", 0);
			}

			//context.put("hideAdd", hideAdd);

			if (hideAdd == "yes") {
				// JOptionPane.showMessageDialog(null,
				// "Bahagian PB Telah Mencukupi.", "Etapp", 2);

			} else if (hideAdd == "notcomplete") {
				// JOptionPane.showMessageDialog(null,
				// "Bahagian PB Masih Tidak Mencukupi.", "Etapp", 2);
			}

		}// close getTotalSyer
	 
	 
	 public void checkTableWujud(String table_name,Db db)  throws Exception {
		  
		  	int total = 0;
		  	String sql="";
		  	ResultSet rs = null;
			try {
				sql = " SELECT COUNT(*) as total  FROM USER_TABLES WHERE TABLE_NAME = '"+table_name+"' ";	
				rs = db.getStatement().executeQuery(sql); 
			if ( rs.next() ) { 
				total = rs.getInt("total"); 
			} 
			} finally { 					
			} 
			
			if(total==0)
			{				
				sql = " CREATE SEQUENCE TBLPPTTUNTUANPENYEWA_SEQ "+
						" START WITH 1 "+
						" MAXVALUE 999999999999 "+
						" MINVALUE 1 "+
						" NOCYCLE "+
						" NOCACHE "+
						" NOORDER ";
				rs = db.getStatement().executeQuery(sql); 
				
				sql =		" CREATE TABLE TBLPPTTUNTUANPENYEWA "+
						" ( "+
						" ID_TUNTUTANPENYEWA  NUMBER  NOT NULL, "+
						" KETERANGAN_TUNTUTAN  VARCHAR(4000), "+
						" ID_SIASATAN            NUMBER, "+
						" ID_MASUK           NUMBER, "+
						" ID_KEMASKINI       NUMBER, "+
						" TARIKH_KEMASKINI   DATE, "+
						" TARIKH_MASUK       DATE "+
						" ) ";
				
				rs = db.getStatement().executeQuery(sql); 
				
				
				
				sql =		" ALTER TABLE TBLPPTTUNTUANPENYEWA ADD ( "+
						  " CONSTRAINT TBLPPTTUNTUANPENYEWA_PK PRIMARY KEY (ID_TUNTUTANPENYEWA)) ";
				rs = db.getStatement().executeQuery(sql); 
						               
						               
				sql =		"	CREATE OR REPLACE TRIGGER TBLPPTTUNTUANPENYEWA_TRI  "+
						"	BEFORE INSERT ON TBLPPTTUNTUANPENYEWA   "+
						"	FOR EACH ROW  "+
						"	BEGIN  "+
						"	 SELECT TBLPPTTUNTUANPENYEWA_SEQ.NEXTVAL  "+
						"	  INTO   :new.ID_TUNTUTANPENYEWA  "+
						"	  FROM   dual;  "+
						"	END;  "+
						"	 ";
						 
				myLogger.info("SQL ADD TABLE BARU :"+sql);
				//rs = db.getStatement().executeQuery(sql); 
				rs = db.getStatement().executeQuery(sql); 
				//ALTER TABLE supplier ADD supplier_name varchar2(50);				
			}
			
	  }
	 
	 
	 Vector senarai_tuntutan = null;
	 public Vector senarai_tuntutan(String id_siasatan) throws Exception {
		 senarai_tuntutan = new Vector();
	 	Db db = null;
	 	senarai_tuntutan.clear();
	 	String sql = "";

	 	try {
	 		db = new Db();
	 		Statement stmt = db.getStatement();
	 		
	 		sql = " SELECT "+
	 				" T.ID_TUNTUTANPENYEWA, T.KETERANGAN_TUNTUTAN, T.ID_SIASATAN,  "+
	 				"  T.ID_MASUK, T.ID_KEMASKINI, T.TARIKH_KEMASKINI,  "+
	 				" T.TARIKH_MASUK "+
	 				" FROM TBLPPTTUNTUANPENYEWA T where id_siasatan = '"+id_siasatan+"' ORDER BY T.ID_TUNTUTANPENYEWA ASC ";	 	
	 		myLogger.info("SUB TUNTUTAN:" + sql);

	 		ResultSet rs = stmt.executeQuery(sql);
	 		Hashtable h;

	 		while (rs.next()) {

	 			h = new Hashtable();
	 			h.put("ID_TUNTUTANPENYEWA", rs.getString("ID_TUNTUTANPENYEWA") == null ? "" : rs
	 					.getString("ID_TUNTUTANPENYEWA"));
	 			h.put("KETERANGAN_TUNTUTAN", rs.getString("KETERANGAN_TUNTUTAN") == null ? "" : rs
	 					.getString("KETERANGAN_TUNTUTAN"));	 			
	 			h.put("ID_SIASATAN",rs.getString("ID_SIASATAN")==null?"":Utils.unescapeJavaScript(rs.getString("ID_SIASATAN")));
	 			senarai_tuntutan.addElement(h);
	 		}
	 		return senarai_tuntutan;
	 	} finally {
	 		if (db != null)
	 			db.close();
	 	}
	 }
	 
	 private void hantar(HttpSession session, String id, String table, String flagTugasan) throws Exception {
		 	Hashtable h = new Hashtable();
			h.put("id", id);
			h.put("table", table);
			h.put("flagTugasan", flagTugasan);
			h.put("id_user", (String) session.getAttribute("_ekptg_user_id"));
			
		 	logic.hantar(h);
	}
}	
		
		
	