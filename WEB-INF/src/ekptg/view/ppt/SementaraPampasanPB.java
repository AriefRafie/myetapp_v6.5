package ekptg.view.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPampasanPBByrnEFTData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPampasanPBData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPampasanPBNilaianData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPampasanPBSerahCekData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPampasanPBTerimaCekData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPampasanPBTuntutanData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiPampasanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiPampasanPBData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8PampasanData;
import ekptg.model.ppt.PPTHeader;

public class SementaraPampasanPB extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SementaraPampasanPB.class);
	
	FrmSek8PampasanData model = new FrmSek8PampasanData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public String doTemplate2() throws Exception {

		String vm = "";
		String noLOT = "";
		String submit = getParam("command");
        String action = getParam("action");
        String mode = getParam("mode");
        
        myLogger.info("submit >> "+submit);
        myLogger.info("action >> "+action);
        myLogger.info("mode >> "+mode);
        
        String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String id_status = getParam("id_status");
		context.put("id_status", id_status);
		myLogger.info("ID STATUS :: "+id_status);
		
//    	String id_fail = getParam("id_fail");
		
		String id_permohonan = getParam("id_permohonan");
		context.put("id_permohonan",id_permohonan);
		myLogger.info("idpermohonan :: "+id_permohonan);
		
		String id_pb = getParam("idPB");
		context.put("idPihakBerkepentingan",id_pb);
		
		String id_hakmilik = getParam("id_hakmilik");
		context.put("id_hakmilik",id_hakmilik);
		
		String id_hakmilikpb = getParam("idHakmilikPB");
		
		String id_siasatan = getParam("idSiasatan");
		
		String id_tanah = getParam("idTanah");
		
		String id_bayaran = getParam("idBayaran");
		context.put("idBayaran",id_bayaran);
		
		String id_bayaranEFT = getParam("idBayaranEFT");
		
//		context.put("ID_SIASATAN",id_siasatan);
		
    	HttpSession session = this.request.getSession();
    	this.context.put("Util",new lebah.util.Util());  // UNTUK FORMAT UTIL.DECIMAL (EX: 12,000.00)

    	FrmHakmilikSementaraSenaraiPampasanData listPampasan = new FrmHakmilikSementaraSenaraiPampasanData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraSenaraiPampasanPBData listPampasanPB = new FrmHakmilikSementaraSenaraiPampasanPBData();
		FrmHakmilikSementaraMaklumatPampasanPBData pbMaster = new FrmHakmilikSementaraMaklumatPampasanPBData();
		FrmHakmilikSementaraMaklumatPampasanPBTuntutanData tuntutan = new FrmHakmilikSementaraMaklumatPampasanPBTuntutanData();
		FrmHakmilikSementaraMaklumatPampasanPBNilaianData nilaian = new FrmHakmilikSementaraMaklumatPampasanPBNilaianData();
		FrmHakmilikSementaraMaklumatPampasanPBTerimaCekData terimaCek = new FrmHakmilikSementaraMaklumatPampasanPBTerimaCekData();
		FrmHakmilikSementaraMaklumatPampasanPBByrnEFTData eft = new FrmHakmilikSementaraMaklumatPampasanPBByrnEFTData();
		
		Vector listMaklumatTanahWithSiasatan = new Vector();
		Vector dataMaklumatTanah = new Vector();
		
		listMaklumatTanahWithSiasatan.clear();
		dataMaklumatTanah.clear();
		
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);
		
		String listHakmilik = "app/ppt/frmSementaraPampasanListHM.jsp";
		
    	Vector list = null;
    	Vector listAgensi = null;
    	Vector listPB = null;
    	Vector maklumatPB = null;
    	Vector paparTuntutan = null;
    	Vector paparNilaian = null;
    	Vector paparAmaun = null;
    	Vector paparTerimaCek = null;
    	Vector listEFT = null;
    	Vector paparEFT = null;
    	Vector checkHakmilik = null;
    	Vector listH = null;
    	
    	
    	Db dbx = null;
		try {
			dbx = new Db();
			if (checkRegPopup("ekptg.view.ppt.SkrinPopupHakmilik", dbx) == 0) { // reg
																				// class
				insertPopupReg("ekptg.view.ppt.SkrinPopupHakmilik",
						"Skrin Salin Hakmilik", "EKPTG - PPT", dbx);
			}
			
		} finally {
			if (dbx != null)
				dbx.close();
		}
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation"); 	
    	context.put("ScreenLocation", ScreenLocation);    	    
    	
    	//paging
    	String flagPaging = getParam("paging");
    	if(flagPaging!=""){
    		context.put("paging", getParam("paging"));
    	}else{
    		context.put("paging", "14");
    	}
    	
		//data hakmilik
		modelUPT.setMaklumatTanah(id_hakmilik);
		dataMaklumatTanah = modelUPT.getMaklumatTanah();
		context.put("dataMaklumatTanah", dataMaklumatTanah);  

		//header
//		String id_status = "";
		String nama_kementerian = "";
//		String flagSegera = "";
		String id_fail = "";
//		String id_negeriProjek = "";
//    	String idpermohonan = getParam("id_permohonan");
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
//			id_status = (String)dh.get("id_status");
			nama_kementerian = (String)dh.get("nama_kementerian");
//			flagSegera = dh.get("flag_segera").toString();
			id_fail = (String)dh.get("id_fail");
			myLogger.info("IDFAIL :: "+id_fail);
//			id_negeriProjek = (String)dh.get("id_projekNegeri");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(id_permohonan);
			this.context.put("list_sub_header", list_sub);
		}

		context.put("idFail",id_fail);
		context.put("kementerianEFT", nama_kementerian);
    	
    	
    	if("viewlistHM".equals(submit)){
    		
//    		vm = "app/ppt/frmSementaraPampasanListHM.jsp";
    		
//    		noLOT = getParam("carianNoLot");
//    		context.put("carianNoLot", noLOT.trim());
//    		
//    		//data & list maklumat tanah
//    		model.setListMaklumatTanahWithSiasatan(id_permohonan,noLOT);
//     		listMaklumatTanahWithSiasatan = model.getListMaklumatTanahWithSiasatan();
//     		context.put("listMaklumatTanah", listMaklumatTanahWithSiasatan);
//     		context.put("saiz_listTanah", listMaklumatTanahWithSiasatan.size());
     		
     		
     		context.put("saiz_listTanah", model.setListMaklumatTanahWithSiasatan_count(id_permohonan,noLOT));
    		//screen
    		vm = listHakmilik;
     		     		
     		
    	}//close viewlistHM
    	
    	else if("newPampasanPB".equals(submit)){
    	
    		vm = "app/ppt/frmHakmilikSementaraSenaraiPampasanPB.jsp";
    		
    		myLogger.info("VM >>> "+vm);
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
    		String namaKementerian = "";
    		String tarikh_terima = "";
    		String projek_negeri = "";
    		String nama_daerah = "";
    		String tarikh_kehendaki = "";
    		String tujuan = "";
    		String no_fail = "";
    		String no_rujukan_surat = "";
    		String no_rujukan_ptd = "";
    		String no_rujukan_ptg = "";
    		String no_rujukan_upt = "";
    		String keterangan = "";
    		String idAgensi = "";
    		if(getrecord_permohonan.size()!=0){
    			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
    			 namaKementerian = (String)h.get("nama_kementerian");
        		 tarikh_terima = (String)h.get("tarikh_terima");
        		 projek_negeri = (String)h.get("projek_negeri");
        		 nama_daerah = (String)h.get("nama_daerah");
        		 tarikh_kehendaki = (String)h.get("tarikh_kehendaki");
        		 tujuan = (String)h.get("tujuan");
        		 no_fail = (String)h.get("no_fail");
        		 no_rujukan_surat = (String)h.get("no_rujukan_surat");
        		 no_rujukan_ptd = (String)h.get("no_rujukan_ptd");
        		 no_rujukan_ptg = (String)h.get("no_rujukan_ptg");
        		 no_rujukan_upt = (String)h.get("no_rujukan_upt");
        		 keterangan = (String)h.get("keterangan");
        		 idAgensi = h.get("id_agensi").toString();
    		}
			
			context.put("nama_kementerian",namaKementerian);
			context.put("tarikh_terima",tarikh_terima);
			context.put("projek_negeri",projek_negeri);
			context.put("nama_daerah",nama_daerah);
			context.put("tarikh_kehendaki",tarikh_kehendaki);
			context.put("tujuan",tujuan);
			context.put("no_fail",no_fail);
			context.put("no_rujukan_surat",no_rujukan_surat);
			context.put("no_rujukan_ptd",no_rujukan_ptd);
			context.put("no_rujukan_ptg",no_rujukan_ptg);
			context.put("no_rujukan_upt",no_rujukan_upt);
			context.put("keterangan",keterangan);
			
			
			if(idAgensi!="")
			{
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			//todo
			
			//GET MAX NO SIASATAN
//			listH = prmhnnMaster.getNoSiasatan(id_permohonan,id_hakmilik);
//			context.put("getNoSiasatan", listH);
//			String _MaxIdSiasatan = "";
//			if(listH.size()!=0){
//				Hashtable b = (Hashtable) listH.get(0);
//				_MaxIdSiasatan = (String)b.get("id_siasatan");	
//				myLogger.info("_MaxIdSiasatan >>> "+_MaxIdSiasatan);
//			}			
			//END
			
			
			listPampasanPB.setSenaraiPB(id_permohonan,id_hakmilik);
			listPB = listPampasanPB.getSenaraiPB();
			context.put("SenaraiPB",listPB);
			
    		
    	}
    	else if ("tabTuntutan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTuntutan.jsp";
    		    		
    		maklumatPB = pbMaster.getRecordDetails(id_permohonan,id_pb);
    		Hashtable hM = (Hashtable)maklumatPB.get(0);
    		context.put("NAMA_PB", hM.get("NAMA_PB"));
    		context.put("NO_PB", hM.get("NO_PB"));
    		context.put("NO_LOT", hM.get("NO_LOT"));
    		context.put("NO_PT", hM.get("NO_PT"));
    		context.put("NAMA_MUKIM", hM.get("NAMA_MUKIM"));
    		context.put("SYER_ATAS", hM.get("SYER_ATAS"));
    		context.put("BAYAR_PAMPASAN", hM.get("BAYAR_PAMPASAN"));
    		context.put("LUAS_AMBIL", hM.get("LUAS_AMBIL"));	
    		context.put("idHakmilikPB",hM.get("ID_HAKMILIKPB"));
    		
    		terimaCek.setDataCheck(id_hakmilikpb);
    		checkHakmilik = terimaCek.getDataCheck();
    		
    		if (checkHakmilik.size()== 0){
    			context.put("idBayaran","");
    			
    		}
    		else{
    			Hashtable hT = (Hashtable)checkHakmilik.get(0);
    			context.put("idBayaran",hT.get("ID_BAYARAN"));
    		}

    		
    		tuntutan.setDataTuntutan(id_hakmilik);
    		myLogger.info("test 123 :: "+id_hakmilik);
			paparTuntutan = tuntutan.getDataTuntutan();
			Hashtable hT = (Hashtable)paparTuntutan.get(0);
			
			
			
			
			if(hT.get("STATUS_TUNTUTAN").equals("")){
				context.put("modeTuntutan", "newTuntutan");
	    		context.put("readonlyTuntutanA", "readonly");
	    		context.put("disabledTuntutanA","disabled");
	    		context.put("readonlyTuntutanB","");
	    		context.put("disabledTuntutanB","");
				
				context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
				context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
				context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
				context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
				context.put("ID_SIASATAN",hT.get("ID_SIASATAN"));
				context.put("STATUS_TUNTUTAN","");
				
			}
			else{
				context.put("modeTuntutan", "paparTuntutan");
	    		context.put("readonlyTuntutanA", "readonly");
	    		context.put("disabledTuntutanA","disabled");
	    		context.put("readonlyTuntutanB","readonly");
	    		context.put("disabledTuntutanB","disabled");
				
				context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
				context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
				context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
				context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
				context.put("ID_SIASATAN",hT.get("ID_SIASATAN"));
				context.put("STATUS_TUNTUTAN",hT.get("STATUS_TUNTUTAN"));
				
			}

    	}
    	else if ("updateTuntutan".equals(action)){
    		
    		updateTuntutan(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTuntutan.jsp";
    		context.put("modeTuntutan", "paparTuntutan");
    		context.put("readonlyTuntutanA", "readonly");
    		context.put("disabledTuntutanA","disabled");
    		context.put("readonlyTuntutanB","readonly");
    		context.put("disabledTuntutanB","disabled");
    		
    		tuntutan.setDataTuntutan(id_hakmilik);
			paparTuntutan = tuntutan.getDataTuntutan();
			Hashtable hT = (Hashtable)paparTuntutan.get(0);
			
			context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
			context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
			context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
			context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
			context.put("ID_SIASATAN",hT.get("ID_SIASATAN"));
			context.put("STATUS_TUNTUTAN",hT.get("STATUS_TUNTUTAN"));
    		
    		
    	}
    	else if ("kemaskiniTuntutan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTuntutan.jsp";
    		context.put("modeTuntutan", "kemaskiniTuntutan");
    		context.put("readonlyTuntutanA", "readonly");
    		context.put("disabledTuntutanA","disabled");
    		context.put("readonlyTuntutanB","");
    		context.put("disabledTuntutanB","");
    		
    		tuntutan.setDataTuntutan(id_hakmilik);
			paparTuntutan = tuntutan.getDataTuntutan();
			Hashtable hT = (Hashtable)paparTuntutan.get(0);
			
			context.put("TUNTUTAN_TUANTNH",hT.get("TUNTUTAN_TUANTNH"));
			context.put("TUNTUTAN_PB_BEBANAN",hT.get("TUNTUTAN_PB_BEBANAN"));
			context.put("TUNTUTAN_PB_TDKDAFTAR",hT.get("TUNTUTAN_PB_TDKDAFTAR"));
			context.put("TUNTUTAN_PB_LAIN",hT.get("TUNTUTAN_PB_LAIN"));
			context.put("ID_SIASATAN",hT.get("ID_SIASATAN"));
			context.put("STATUS_TUNTUTAN",hT.get("STATUS_TUNTUTAN"));
    		
    	}
    	else if ("tabNilaian".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBNilaian.jsp";

    		context.put("modeNilaian","paparNilaian");
    		context.put("readonlyNilaianA","readonly");
    		context.put("disabledNilaianA","disabled");
    		context.put("readonlyNilaianB","readonly");
    		context.put("disabledNilaianB","disabled");
//    		context.put("error", false);

    		myLogger.info("id_permohonan >> "+id_permohonan);
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
    		
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			nilaian.setDataJPPH(id_permohonan);
			paparNilaian = nilaian.getDataJPPH();
			
			if(paparNilaian.size()== 0){
				
				//context.put("error", true);
				context.put("modeNilaian", "baruNilaian");
				
			}
			else{
				
				
				
				Hashtable hN = (Hashtable)paparNilaian.get(0);
				
				context.put("NO_RUJUKANSURATJT", hN.get("NO_RUJUKANSURATJT"));
				context.put("TARIKH_SURATJT", hN.get("TARIKH_SURATJT"));
				context.put("TARIKH_TERIMAJT", hN.get("TARIKH_TERIMAJT"));
				
				nilaian.setAmaun(id_siasatan);
				paparAmaun = nilaian.getAmaun();
				
				myLogger.info("paparAmaun >>>> "+paparAmaun);
				context.put("paparAmaun", paparAmaun);
				
				Hashtable hA = (Hashtable)paparAmaun.get(0);
				
				if(hA.get("NILAIAN_JPPH").equals("")){
					context.put("NILAIAN_JPPH","");

				}
				else{
					context.put("NILAIAN_JPPH",Utils.format2Decimal(Double.parseDouble(hA.get("NILAIAN_JPPH").toString())));

				}
				
			}
    		
    	}

    	else if ("kemaskiniNilaian".equals(action)){    		    	
    		
    		context.put("modeNilaian","kemaskiniNilaian");
    		context.put("readonlyNilaianA","");
    		context.put("disabledNilaianA","");
    		context.put("readonlyNilaianB","");
    		context.put("disabledNilaianB","");
    		
    		nilaian.setDataJPPH(id_permohonan);
			paparNilaian = nilaian.getDataJPPH();
			if (paparNilaian.size()!=0){				
				Hashtable hN = (Hashtable)paparNilaian.get(0);
				
				context.put("NO_RUJUKANSURATJT", hN.get("NO_RUJUKANSURATJT"));
				context.put("TARIKH_SURATJT", hN.get("TARIKH_SURATJT"));
				context.put("TARIKH_TERIMAJT", hN.get("TARIKH_TERIMAJT"));
			}else{
				context.put("modeNilaian", "baruNilaian");
			}
			
			myLogger.info("ID SIASATAN :: "+id_siasatan);
			nilaian.setAmaun(id_siasatan);                                                                       
			paparAmaun = nilaian.getAmaun();
			Hashtable hA = (Hashtable)paparAmaun.get(0);
			double NILAIAN_JPPH = Double.parseDouble(hA.get("NILAIAN_JPPH").toString());
//			String a = (String)hA.get("NILAIAN_JPPH");
			myLogger.info("NILAIAN_JPPH ::"+NILAIAN_JPPH);
		
			context.put("NILAIAN_JPPH",hA.get("NILAIAN_JPPH"));
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBNilaian.jsp";
    		
    	}
    	
    	else if ("simpanNilaian".equals(action)){
    		
    		simpanNilaian(session,id_permohonan);
    		
       		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBNilaian.jsp";
    		context.put("modeNilaian","paparNilaian");
    		context.put("readonlyNilaianA","readonly");
    		context.put("disabledNilaianA","disabled");
    		context.put("readonlyNilaianB","readonly");
    		context.put("disabledNilaianB","disabled");
    		
    		nilaian.setDataJPPH(id_permohonan);
			paparNilaian = nilaian.getDataJPPH();
			Hashtable hN = (Hashtable)paparNilaian.get(0);
			
			context.put("NO_RUJUKANSURATJT", hN.get("NO_RUJUKANSURATJT"));
			context.put("TARIKH_SURATJT", hN.get("TARIKH_SURATJT"));
			context.put("TARIKH_TERIMAJT", hN.get("TARIKH_TERIMAJT"));
			
			nilaian.setAmaun(id_siasatan);
			paparAmaun = nilaian.getAmaun();
			Hashtable hA = (Hashtable)paparAmaun.get(0);
			
			context.put("NILAIAN_JPPH",Utils.format2Decimal(Double.parseDouble(hA.get("NILAIAN_JPPH").toString())));	
		
    		
    	}
    	else if ("updateNilaian".equals(action)){
    		
    		updateNilaian(session,id_permohonan);
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBNilaian.jsp";
    		context.put("modeNilaian","paparNilaian");
    		context.put("readonlyNilaianA","readonly");
    		context.put("disabledNilaianA","disabled");
    		context.put("readonlyNilaianB","readonly");
    		context.put("disabledNilaianB","disabled");
    		
    		nilaian.setDataJPPH(id_permohonan);
			paparNilaian = nilaian.getDataJPPH();
			if(paparNilaian.size()!=0){
				Hashtable hN = (Hashtable)paparNilaian.get(0);
				
				context.put("NO_RUJUKANSURATJT", hN.get("NO_RUJUKANSURATJT"));
				context.put("TARIKH_SURATJT", hN.get("TARIKH_SURATJT"));
				context.put("TARIKH_TERIMAJT", hN.get("TARIKH_TERIMAJT"));
			}else{
				context.put("modeNilaian", "baruNilaian");
			}
			
			
			nilaian.setAmaun(id_siasatan);
			paparAmaun = nilaian.getAmaun();
			context.put("paparAmaun", paparAmaun);
			Hashtable hA = (Hashtable)paparAmaun.get(0);
			
			context.put("NILAIAN_JPPH",Utils.format2Decimal(Double.parseDouble(hA.get("NILAIAN_JPPH").toString())));	
			
    	}
    	else if ("tabTerimaCek".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTerimaCek.jsp";
    		
    		terimaCek.setDataCheck(id_hakmilikpb);
    		checkHakmilik = terimaCek.getDataCheck();
    		
    		
    		if (checkHakmilik.size()== 0){
    			
    			context.put("modeTerimaCek", "newTerimaCek");
        		context.put("readonlyTerimaCekA", "");
        		context.put("disabledTerimaCekA","");
        		context.put("readonlyTerimaCekB", "");
        		context.put("disabledTerimaCekB","");
    			
    			maklumatPB = pbMaster.getRecord(id_pb);
        		
        		
        		if(maklumatPB.size()!=0){
        			Hashtable hM = (Hashtable)maklumatPB.get(0);
        			context.put("NAMA_PENERIMA",hM.get("NAMA_PB"));
        		}else{
        			context.put("NAMA_PENERIMA","");
        		}
        		
        		context.put("TARIKH_TERIMA","");        		
        		context.put("NO_BAYARAN","");
        		context.put("TARIKH_CEK","");
        		context.put("AMAUN_BAYARAN","");
        		context.put("TARIKH_AKHIR_CEK","");
        		context.put("TARIKH_AMBIL_CEK","");
        		context.put("MASA_AMBIL_CEK","");    			
    		}
    		else{
    			
    			context.put("modeTerimaCek", "paparTerimaCek");
        		context.put("readonlyTerimaCekA", "readonly");
        		context.put("disabledTerimaCekA","disabled");
        		context.put("readonlyTerimaCekB", "readonly");
        		context.put("disabledTerimaCekB","disabled");
        		
        		Hashtable hC = (Hashtable)checkHakmilik.get(0);
        		
        		terimaCek.setDataTerimaCek(hC.get("ID_BAYARAN").toString());
        		paparTerimaCek = terimaCek.getDataTerimaCek();
        		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
        		
        		context.put("TARIKH_TERIMA",hT.get("TARIKH_TERIMA"));
        		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
        		context.put("NO_BAYARAN",hT.get("NO_BAYARAN"));
        		context.put("TARIKH_CEK",hT.get("TARIKH_CEK"));
        		context.put("AMAUN_BAYARAN",Utils.format2Decimal(Double.parseDouble(hT.get("AMAUN_BAYARAN").toString())));
        		context.put("TARIKH_AKHIR_CEK",hT.get("TARIKH_AKHIR_CEK"));
        		context.put("TARIKH_AMBIL_CEK",hT.get("TARIKH_AMBIL_CEK"));
        		context.put("MASA_AMBIL_CEK",hT.get("MASA_AMBIL_CEK"));
        		context.put("idBayaran",hT.get("ID_BAYARAN"));

    		}
	
    	}
    	else if("simpanTerimaCek".equals(action)){
    		
    		String idBayaran = simpanTerimaCek(session);
    		context.put("idBayaran",idBayaran);
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTerimaCek.jsp";
    		context.put("modeTerimaCek", "paparTerimaCek");
    		context.put("readonlyTerimaCekA", "readonly");
    		context.put("disabledTerimaCekA","disabled");
    		context.put("readonlyTerimaCekB", "readonly");
    		context.put("disabledTerimaCekB","disabled");
    		
    		terimaCek.setDataTerimaCek(idBayaran);
    		paparTerimaCek = terimaCek.getDataTerimaCek();
    		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
    		
    		context.put("TARIKH_TERIMA",hT.get("TARIKH_TERIMA"));
    		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
    		context.put("NO_BAYARAN",hT.get("NO_BAYARAN"));
    		context.put("TARIKH_CEK",hT.get("TARIKH_CEK"));
    		context.put("AMAUN_BAYARAN",Utils.format2Decimal(Double.parseDouble(hT.get("AMAUN_BAYARAN").toString())));
    		context.put("TARIKH_AKHIR_CEK",hT.get("TARIKH_AKHIR_CEK"));
    		context.put("TARIKH_AMBIL_CEK",hT.get("TARIKH_AMBIL_CEK"));
    		context.put("MASA_AMBIL_CEK",hT.get("MASA_AMBIL_CEK"));
    		context.put("idBayaran",hT.get("ID_BAYARAN"));

    		
    	}
    	else if("kemaskiniTerimaCek".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTerimaCek.jsp";

    		context.put("modeTerimaCek", "updateTerimaCek");
    		context.put("readonlyTerimaCekA", "readonly");
    		context.put("disabledTerimaCekA","disabled");
    		context.put("readonlyTerimaCekB", "");
    		context.put("disabledTerimaCekB","");
    		
    		terimaCek.setDataTerimaCek(id_bayaran);
    		paparTerimaCek = terimaCek.getDataTerimaCek();
    		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
    		
    		context.put("TARIKH_TERIMA",hT.get("TARIKH_TERIMA"));
    		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
    		context.put("NO_BAYARAN",hT.get("NO_BAYARAN"));
    		context.put("TARIKH_CEK",hT.get("TARIKH_CEK"));
    		context.put("AMAUN_BAYARAN",hT.get("AMAUN_BAYARAN"));
    		context.put("TARIKH_AKHIR_CEK",hT.get("TARIKH_AKHIR_CEK"));
    		context.put("TARIKH_AMBIL_CEK",hT.get("TARIKH_AMBIL_CEK"));
    		context.put("MASA_AMBIL_CEK",hT.get("MASA_AMBIL_CEK"));
    		context.put("idBayaran",hT.get("ID_BAYARAN"));

    		
    	}
    	else if ("updateTerimaCek".equals(action)){
    		
    		updateTerimaCek(session);
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBTerimaCek.jsp";
    		context.put("modeTerimaCek", "paparTerimaCek");
    		context.put("readonlyTerimaCekA", "readonly");
    		context.put("disabledTerimaCekA","disabled");
    		context.put("readonlyTerimaCekB", "readonly");
    		context.put("disabledTerimaCekB","disabled");
    		
    		terimaCek.setDataTerimaCek(id_bayaran);
    		paparTerimaCek = terimaCek.getDataTerimaCek();
    		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
    		
    		context.put("TARIKH_TERIMA",hT.get("TARIKH_TERIMA"));
    		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
    		context.put("NO_BAYARAN",hT.get("NO_BAYARAN"));
    		context.put("TARIKH_CEK",hT.get("TARIKH_CEK"));
    		context.put("AMAUN_BAYARAN",Utils.format2Decimal(Double.parseDouble(hT.get("AMAUN_BAYARAN").toString())));
    		context.put("TARIKH_AKHIR_CEK",hT.get("TARIKH_AKHIR_CEK"));
    		context.put("TARIKH_AMBIL_CEK",hT.get("TARIKH_AMBIL_CEK"));
    		context.put("MASA_AMBIL_CEK",hT.get("MASA_AMBIL_CEK"));
    		context.put("idBayaran",hT.get("ID_BAYARAN"));


    	}
    	else if ("tabSerahCek".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBSerahCek.jsp";

    		
    		terimaCek.setDataTerimaCek(id_bayaran);
    		paparTerimaCek = terimaCek.getDataTerimaCek();
    		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
    		
    		if(hT.get("TARIKH_SERAH_CEK").equals("")){
    			context.put("modeSerahCek","updateSerahCek");
    			context.put("readonlySerahCekA","readonly");
    			context.put("disabledSerahCekA","disabled");
    			context.put("readonlySerahCekB","");
    			context.put("disabledSerahCekB","");
    			
    			context.put("NO_PB",hT.get("NO_PB"));
        		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
    			context.put("TARIKH_SERAH_CEK","");
    			context.put("FLAG_SERAH_CEK","0");
        		context.put("idBayaran",hT.get("ID_BAYARAN"));

    			
    		}
    		else{
    			context.put("modeSerahCek","paparSerahCek");
    			context.put("readonlySerahCekA","readonly");
    			context.put("disabledSerahCekA","disabled");
    			context.put("readonlySerahCekB","readonly");
    			context.put("disabledSerahCekB","disabled");
    			
    			context.put("NO_PB",hT.get("NO_PB"));
        		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
    			context.put("TARIKH_SERAH_CEK",hT.get("TARIKH_SERAH_CEK"));
    			context.put("FLAG_SERAH_CEK",hT.get("FLAG_SERAH_CEK"));
        		context.put("idBayaran",hT.get("ID_BAYARAN"));

    		}

    		
    	}
    	else if ("updateSerahCek".equals(action)){
    		updateSerahCek(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBSerahCek.jsp";
    		context.put("modeSerahCek","paparSerahCek");
    		context.put("readonlySerahCekA","readonly");
			context.put("disabledSerahCekA","disabled");
			context.put("readonlySerahCekB","readonly");
			context.put("disabledSerahCekB","disabled");
			
    		
			terimaCek.setDataTerimaCek(id_bayaran);
    		paparTerimaCek = terimaCek.getDataTerimaCek();
    		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
    		
			context.put("NO_PB",hT.get("NO_PB"));
    		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
			context.put("TARIKH_SERAH_CEK",hT.get("TARIKH_SERAH_CEK"));
			context.put("FLAG_SERAH_CEK",hT.get("FLAG_SERAH_CEK"));
    		context.put("idBayaran",hT.get("ID_BAYARAN"));

    		
    	}
    	else if("kemaskiniSerahCek".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBSerahCek.jsp";
    		context.put("modeSerahCek","updateSerahCek");
    		context.put("readonlySerahCekA","readonly");
			context.put("disabledSerahCekA","disabled");
			context.put("readonlySerahCekB","");
			context.put("disabledSerahCekB","");
			
    		
			terimaCek.setDataTerimaCek(id_bayaran);
    		paparTerimaCek = terimaCek.getDataTerimaCek();
    		Hashtable hT = (Hashtable)paparTerimaCek.get(0);
    		
    		
			context.put("NO_PB",hT.get("NO_PB"));
    		context.put("NAMA_PENERIMA",hT.get("NAMA_PB"));
			context.put("TARIKH_SERAH_CEK",hT.get("TARIKH_SERAH_CEK"));
			context.put("FLAG_SERAH_CEK",hT.get("FLAG_SERAH_CEK"));
    		context.put("idBayaran",hT.get("ID_BAYARAN"));

    	}
    	else if ("tabEFT".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBByrnEFT.jsp";
    		
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
    		
    		
    			context.put("modeEFT","newEFT");
    			context.put("readonlyEFTA","readonly");
    			context.put("disabledEFTA","disabled");
    			context.put("readonlyEFTB","");
    			context.put("disabledEFTB","");
    			
    			context.put("NO_EFT","");
        		context.put("NO_RUJUKAN_SURATEFT","");
    			context.put("TARIKH_TERIMA_EFT","");
    			context.put("TARIKH_SURAT_EFT","");
    			context.put("TARIKH_BAYARAN_EFT","");
    			context.put("AMAUN_BAYARAN","");
    			
    			terimaCek.setDataCheck(id_hakmilikpb);
        		checkHakmilik = terimaCek.getDataCheck();
        		
        		if(checkHakmilik.size()!=0){
        			Hashtable hC = (Hashtable)checkHakmilik.get(0);
            		context.put("idBayaran",hC.get("ID_BAYARAN"));
        		}
        			
        		
   
	    		eft.setListEFT(id_hakmilikpb);
	    		listEFT = eft.getListEFT();
	    		context.put("SenaraiEFT", listEFT);
    		
    	}
    	else if ("simpanEFT".equals(action)){
    		
    		String idBayaranEFT = simpanEFT(session);
    		context.put("idBayaranEFT",idBayaranEFT);
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBByrnEFT.jsp";
    		context.put("modeEFT","paparEFT");
			context.put("readonlyEFTA","readonly");
			context.put("disabledEFTA","disabled");
			context.put("readonlyEFTB","readonly");
			context.put("disabledEFTB","disabled");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			
			eft.setDataEFT(idBayaranEFT);
    		paparEFT = eft.getDataEFT();
    		Hashtable hT = (Hashtable)paparEFT.get(0);
			
			context.put("NO_EFT",hT.get("NO_EFT"));
    		context.put("NO_RUJUKAN_SURATEFT",hT.get("NO_RUJUKAN_SURATEFT"));
			context.put("TARIKH_TERIMA_EFT",hT.get("TARIKH_TERIMA_EFT"));
			context.put("TARIKH_SURAT_EFT",hT.get("TARIKH_SURAT_EFT"));
			context.put("TARIKH_BAYARAN_EFT",hT.get("TARIKH_BAYARAN_EFT"));
			context.put("AMAUN_BAYARAN",Utils.format2Decimal(Double.parseDouble(hT.get("AMAUN_BAYARAN").toString())));
			context.put("idBayaranEFT",hT.get("ID_BAYARAN"));

			eft.setListEFT(id_hakmilikpb);
    		listEFT = eft.getListEFT();
    		context.put("SenaraiEFT", listEFT);
    		
    	}
    	else if ("kemaskiniEFT".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBByrnEFT.jsp";
    		context.put("modeEFT","updateEFT");
			context.put("readonlyEFTA","readonly");
			context.put("disabledEFTA","disabled");
			context.put("readonlyEFTB","");
			context.put("disabledEFTB","");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			
			eft.setDataEFT(id_bayaranEFT);
    		paparEFT = eft.getDataEFT();
    		Hashtable hT = (Hashtable)paparEFT.get(0);
			
			context.put("NO_EFT",hT.get("NO_EFT"));
    		context.put("NO_RUJUKAN_SURATEFT",hT.get("NO_RUJUKAN_SURATEFT"));
			context.put("TARIKH_TERIMA_EFT",hT.get("TARIKH_TERIMA_EFT"));
			context.put("TARIKH_SURAT_EFT",hT.get("TARIKH_SURAT_EFT"));
			context.put("TARIKH_BAYARAN_EFT",hT.get("TARIKH_BAYARAN_EFT"));
			context.put("AMAUN_BAYARAN",hT.get("AMAUN_BAYARAN"));
			context.put("idBayaranEFT",hT.get("ID_BAYARAN"));
			
			eft.setListEFT(id_hakmilikpb);
    		listEFT = eft.getListEFT();
    		context.put("SenaraiEFT", listEFT);
    		
    	}
    	else if ("updateEFT".equals(action)){
    		
    		updateEFT(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBByrnEFT.jsp";
    		context.put("modeEFT","paparEFT");
			context.put("readonlyEFTA","readonly");
			context.put("disabledEFTA","disabled");
			context.put("readonlyEFTB","readonly");
			context.put("disabledEFTB","disabled");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			
			eft.setDataEFT(id_bayaranEFT);
    		paparEFT = eft.getDataEFT();
    		Hashtable hT = (Hashtable)paparEFT.get(0);
			
			context.put("NO_EFT",hT.get("NO_EFT"));
    		context.put("NO_RUJUKAN_SURATEFT",hT.get("NO_RUJUKAN_SURATEFT"));
			context.put("TARIKH_TERIMA_EFT",hT.get("TARIKH_TERIMA_EFT"));
			context.put("TARIKH_SURAT_EFT",hT.get("TARIKH_SURAT_EFT"));
			context.put("TARIKH_BAYARAN_EFT",hT.get("TARIKH_BAYARAN_EFT"));
			context.put("AMAUN_BAYARAN",Utils.format2Decimal(Double.parseDouble(hT.get("AMAUN_BAYARAN").toString())));
			context.put("idBayaranEFT",hT.get("ID_BAYARAN"));
			
			eft.setListEFT(id_hakmilikpb);
    		listEFT = eft.getListEFT();
    		context.put("SenaraiEFT", listEFT);
    		
    	}
    	else if("paparEFT".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMaklumatPampasanPBByrnEFT.jsp";
    		context.put("modeEFT","paparEFT");
			context.put("readonlyEFTA","readonly");
			context.put("disabledEFTA","disabled");
			context.put("readonlyEFTB","readonly");
			context.put("disabledEFTB","disabled");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			
			eft.setDataEFT(id_bayaranEFT);
    		paparEFT = eft.getDataEFT();
    		Hashtable hT = (Hashtable)paparEFT.get(0);
			
			context.put("NO_EFT",hT.get("NO_EFT"));
    		context.put("NO_RUJUKAN_SURATEFT",hT.get("NO_RUJUKAN_SURATEFT"));
			context.put("TARIKH_TERIMA_EFT",hT.get("TARIKH_TERIMA_EFT"));
			context.put("TARIKH_SURAT_EFT",hT.get("TARIKH_SURAT_EFT"));
			context.put("TARIKH_BAYARAN_EFT",hT.get("TARIKH_BAYARAN_EFT"));
			context.put("AMAUN_BAYARAN",Utils.format2Decimal(Double.parseDouble(hT.get("AMAUN_BAYARAN").toString())));
			context.put("idBayaranEFT",hT.get("ID_BAYARAN"));

			eft.setListEFT(id_hakmilikpb);
    		listEFT = eft.getListEFT();
    		context.put("SenaraiEFT", listEFT);
    		
    	}
    	else {
    		vm = "app/ppt/frmHakmilikSementaraSenaraiPampasan.jsp";
    		
    		if (!"".equals(getParam("txdTarikh")));
    		tarikhmohon = getParam("txdTarikh");
    	
    		if (!"".equals(getParam("txtNoFail")));
    			nofail = getParam("txtNoFail");
    			
    		if (!"".equals(getParam("txtNoRujJKPTGNegeri")));
    			noJKPTG = getParam("txtNoRujJKPTGNegeri");
    		
    		if(!"".equals(getParam("socStatus")))
    			cStatus = getParam("socStatus");
    		
    		listPampasan.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG, userIdNeg);		
    		list = listPampasan.getList();
    							
    	
    	    context.put("PermohonanList", list);
    	    context.put("list_size", list.size());
    	    context.put("CarianFail", nofail);  
    	    context.put("CarianNoJKPTG",noJKPTG );
    	    context.put("tarikhPermohonan", tarikhmohon);
    	    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    	    setupPage(session,action,list);
    	}
    	
		return vm;
	}

	private String userData(String id_user) throws Exception{

		Vector listUserid = new Vector();
		listUserid.clear();

		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }

	    return userIdNeg;

	}//close userData

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
		this.context.put("PermohonanList",paging.getPage(page));
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
			
	private void updateTuntutan(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idSiasatan = getParam("idSiasatan");
		
		h.put("id_permohonan",getParam("id_permohonan"));
		h.put("idSiasatan",idSiasatan);
		h.put("statusTuntutan",getParam("sorStatusTuntutan"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraMaklumatPampasanPBTuntutanData.updateTuntutan(h);
	}
	
	private void updateNilaian(HttpSession session,String id_permohonan) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idSiasatan = getParam("idSiasatan");
		
		h.put("idSiasatan",idSiasatan);
		h.put("nilaianJPPH",getParam("txtAmaunNilaiSebln"));
		h.put("idKemaskini",user_id);
		
		h.put("txtNoRujSurat",getParam("txtNoRujSurat"));
		h.put("txdTkhTerimaSurat",getParam("txdTkhTerimaSurat"));
		h.put("txdTkhSurat",getParam("txdTkhSurat"));
		
		FrmHakmilikSementaraMaklumatPampasanPBNilaianData.updateNilaian(h,id_permohonan);
	}
	
	private void simpanNilaian(HttpSession session,String id_permohonan) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idSiasatan = getParam("idSiasatan");
		
		h.put("idSiasatan",idSiasatan);
		h.put("nilaianJPPH",getParam("txtAmaunNilaiSebln"));
		h.put("idKemaskini",user_id);
		h.put("id_permohonan",id_permohonan);
		
		FrmHakmilikSementaraMaklumatPampasanPBNilaianData.simpanNilaian(h);
	}	

	private String simpanTerimaCek(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idHakmilikPB = getParam("idHakmilikPB");
		
		h.put("idHakmilikPB",idHakmilikPB);
		h.put("tarikhTerimaCek",getParam("txdTkhTerima"));
		h.put("noCek",getParam("txtNoCek"));
		h.put("tarikhCek",getParam("txdTkhCek"));
		h.put("amaunCek",getParam("txtAmaunCek"));
		h.put("tarikhAkhirCek",getParam("txdTkhAkhirCek"));
		h.put("masaAmbilCek",getParam("txtMasaAmbilCek"));
		h.put("tarikhAmbilCek",getParam("txdTkhAmbilCek"));
		h.put("idMasuk",user_id);
		
		return FrmHakmilikSementaraMaklumatPampasanPBTerimaCekData.simpanTerimaCek(h);
	}
	
	private void updateTerimaCek(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idBayaran = getParam("idBayaran");
		
		h.put("idBayaran",idBayaran);
		h.put("tarikhTerimaCek",getParam("txdTkhTerima"));
		h.put("noCek",getParam("txtNoCek"));
		h.put("tarikhCek",getParam("txdTkhCek"));
		h.put("amaunCek",getParam("txtAmaunCek"));
		h.put("tarikhAkhirCek",getParam("txdTkhAkhirCek"));
		h.put("masaAmbilCek",getParam("txtMasaAmbilCek"));
		h.put("tarikhAmbilCek",getParam("txdTkhAmbilCek"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraMaklumatPampasanPBTerimaCekData.updateTerimaCek(h);
	}
	
	private void updateSerahCek(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idBayaran = getParam("idBayaran");
		String id_permohonan = getParam("id_permohonan");
		
		h.put("idBayaran",idBayaran);
		h.put("tarikhSerahCek",getParam("txdTkhSerah"));
		h.put("flagSerahCek",getParam("socStatusSerahCek"));
		h.put("id_permohonan",id_permohonan);
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraMaklumatPampasanPBSerahCekData.updateSerahCek(h);
	}
	
	private String simpanEFT(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idHakmilikPB = getParam("idHakmilikPB");
		
		h.put("idHakmilikPB",idHakmilikPB);
		h.put("noRujSuratEFT",getParam("txtNoRujSurat"));
		h.put("tarikhSuratEFT",getParam("txdTkhSurat"));
		h.put("tarikhTerimaEFT",getParam("txdTkhTerimaSurat"));
		h.put("noEFT",getParam("txtNoEFT"));
		h.put("amaunEFT",getParam("txtAmaun"));
		h.put("tarikhBayaranEFT",getParam("txdTkh"));
		h.put("idMasuk",user_id);
		
		return FrmHakmilikSementaraMaklumatPampasanPBByrnEFTData.simpanEFT(h);
	}
	
	private void updateEFT(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idBayaran = getParam("idBayaranEFT");
		
		h.put("idBayaran",idBayaran);
		h.put("noRujSuratEFT",getParam("txtNoRujSurat"));
		h.put("tarikhSuratEFT",getParam("txdTkhSurat"));
		h.put("tarikhTerimaEFT",getParam("txdTkhTerimaSurat"));
		h.put("noEFT",getParam("txtNoEFT"));
		h.put("amaunEFT",getParam("txtAmaun"));
		h.put("tarikhBayaranEFT",getParam("txdTkh"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraMaklumatPampasanPBByrnEFTData.updateEFT(h);
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
