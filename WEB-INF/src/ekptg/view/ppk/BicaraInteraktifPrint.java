package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.CacheManager;
import ekptg.engine.CachedObject;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;
import ekptg.model.ppk.BicaraInteraktifData;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;

public class BicaraInteraktifPrint extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(BicaraInteraktifPrint.class);
	String skrin_name = "app/ppk/BicaraInteraktif/index.jsp";	
	String formNameAjax = "Fekptg_view_ppk_BicaraInteraktifPrint";
	BicaraInteraktifData modelBI = new BicaraInteraktifData();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	//List listPerbicaraan = null;
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		//String action = getParam("action");
		String todayAsString = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		myLogger.info("TARIKH HARI NI : "+todayAsString);
		this.context.put("command",command);
		String action = getParam("action");
		myLogger.info("command : "+command+" action : "+action);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		
		List listKehadiran = null;
		List listTurutHadir = null;
		Map viewTuruthadir = null;
		List listPerbicaraan = null;
		defaultPut();
		if(command.equals("showMaklumatperubahanPrint"))
		{
			this.context.put("tajukLaporan", "LAMPIRAN PERUBAHAN MAKLUMAT");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);	
			//String skrinName = "perubahan";
			//this.context.put("skrinName", skrinName);			
			this.context.put("scrolPosition", getParam("scrolPosition"));
			String ID_PERMOHONANSIMATI = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			String flagPrint = "Y";
			String htmlSkrinMaklumat = "";
			Db db = null;
			try {
				db = new Db();		
				Map mainID = modelBI. mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String)mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String)mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String)mainID.get("ID_PEMOHON");
				ID_SIMATI = (String)mainID.get("ID_SIMATI");
				this.context.put("viewPerbicaraan", modelBI.viewPerbicaraan(session,ID_PERBICARAAN,ID_PERMOHONAN,db));
				htmlSkrinMaklumat = modelBI.htmlListPerubahan(session,formName,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,flagPrint,db);
				
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);	
			this.context.put("flagPrint",flagPrint);
			this.context.put("htmlSkrinMaklumat",htmlSkrinMaklumat);			
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";	
		}
		else if(command.equals("showLaporanStatsPegawai"))
		{
			List listStatsJumlahBicara = null;			
			String statsBicaraID_NEGERI = "";
			String statsBicaraTARIKH_MULA = todayAsString;
			String statsBicaraTARIKH_AKHIR = todayAsString;
			String statsBicaraNAMA_PEGAWAI = "";
			String flagPrint = "";
			//String htmlSkrin = "";
			String paramsButton = "";
			List listNegeri = null;
			Db db = null;
			try {
				db = new Db();
				if(action.equals("Cetak"))
				{
					flagPrint = "Y";
				}
				
				String cacheID = "listStatsJumlahBicara"+USER_ID_SYSTEM;
				CachedObject get_co_listStatsJumlahBicara =  (CachedObject)CacheManager.getCache(cacheID);
				
				if(action.equals("Cari") || action.equals("Cetak"))
				{
					statsBicaraID_NEGERI = getParam("statsBicaraID_NEGERI");
					statsBicaraTARIKH_MULA = getParam("statsBicaraTARIKH_MULA");
					statsBicaraTARIKH_AKHIR = getParam("statsBicaraTARIKH_AKHIR");
					statsBicaraNAMA_PEGAWAI = getParam("statsBicaraNAMA_PEGAWAI");
				}
				
				myLogger.info("check get_co_listStatsJumlahBicara : "+get_co_listStatsJumlahBicara);
				
				if(action.equals("Cari") && (!statsBicaraID_NEGERI.equals("") || !statsBicaraTARIKH_MULA.equals("") || !statsBicaraTARIKH_AKHIR.equals("") || !statsBicaraNAMA_PEGAWAI.equals("")))
				{					
					listStatsJumlahBicara = modelBI.listStatsJumlahBicara(session,statsBicaraID_NEGERI,statsBicaraTARIKH_MULA,statsBicaraTARIKH_AKHIR, statsBicaraNAMA_PEGAWAI, db);
					CachedObject set_co_listStatsJumlahBicara = new CachedObject(listStatsJumlahBicara, cacheID, 0);
					CacheManager.putCache(set_co_listStatsJumlahBicara);
				}
				else if(action.equals("Reset"))
				{
					CacheManager.removeCache(cacheID);
				}				
				else if (action.equals("Cetak") && get_co_listStatsJumlahBicara != null)
				{
					listStatsJumlahBicara =  (List)get_co_listStatsJumlahBicara.object;
				}
				else
				{
					CacheManager.removeCache(cacheID);
				}				
				listNegeri = modelBI.listRefTable(session,"","","TBLRUJNEGERI","ID_NEGERI","KOD_NEGERI","NAMA_NEGERI","","", db);
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("listNegeri",listNegeri);
			this.context.put("statsBicaraID_NEGERI",statsBicaraID_NEGERI);
			this.context.put("statsBicaraTARIKH_MULA",statsBicaraTARIKH_MULA);
			this.context.put("statsBicaraTARIKH_AKHIR",statsBicaraTARIKH_AKHIR);
			this.context.put("statsBicaraNAMA_PEGAWAI",statsBicaraNAMA_PEGAWAI);
			this.context.put("flagPrint",flagPrint);
			this.context.put("listStatsJumlahBicara",listStatsJumlahBicara);	
			if(command.equals("cariLaporanStatsPegawai"))
			{
				skrin_name = "app/ppk/BicaraInteraktif/senaraiStatsPegawai.jsp";	
			}
			skrin_name = "app/ppk/BicaraInteraktif/popupStatsPegawai.jsp";	
		}
		else if(command.equals("showCatatanPerintah") || command.equals("showJanaNota"))
		{
			this.context.put("tajukLaporan", "");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);	
			String ID_HISTORYJANANOTA = getParam("ID_HISTORYJANANOTA");
			this.context.put("ID_HISTORYJANANOTA", ID_HISTORYJANANOTA);	
			//String skrinName = "perubahan";
			//this.context.put("skrinName", skrinName);			
			this.context.put("scrolPosition", getParam("scrolPosition"));
			String ID_PERMOHONANSIMATI = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			String flagPrint = "Y";
			String htmlSkrinMaklumat = "";
			String CATATAN_PERINTAH_BI = "";
			String CATATAN = "";
			String NO_FAIL = "";
			Db db = null;
			try {
				db = new Db();		
				Map mainID = modelBI. mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String)mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String)mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String)mainID.get("ID_PEMOHON");
				ID_SIMATI = (String)mainID.get("ID_SIMATI");
				NO_FAIL = (String)mainID.get("NO_FAIL");
				this.context.put("viewPerbicaraan", modelBI.viewPerbicaraan(session,ID_PERBICARAAN,ID_PERMOHONAN,db));
				//htmlSkrinMaklumat = modelBI.htmlListPerubahan(session,formName,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,flagPrint,db);
				
				if(command.equals("showCatatanPerintah"))
				{
					Map perintahInfo = modelBI.getMaklumatPerintahByIdPerbicaraan(session, ID_PERBICARAAN, db);
					myLogger.info("perintahInfo : "+perintahInfo);
					//getmaklumatperintah
					CATATAN_PERINTAH_BI = (String)perintahInfo.get("CATATAN_PERINTAH_BI");
					CATATAN = (String)perintahInfo.get("CATATAN");
				}
				else if(command.equals("showJanaNota"))
				{
					Map getNotaHistoryJana = modelBI.getNotaHistoryJana(session, ID_HISTORYJANANOTA, db);
					myLogger.info("getNotaHistoryJana : "+getNotaHistoryJana);
					//getmaklumatperintah
					CATATAN_PERINTAH_BI = (String)getNotaHistoryJana.get("NOTA");
				}
				
				
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("NO_FAIL", NO_FAIL);
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);	
			this.context.put("flagPrint",flagPrint);
			
			
			myLogger.info("CATATAN_SKRIN_PERINTAH  ::::::::::::::: "+CATATAN);
	    	/*		
	    	String htmlPageSetup = "";
	    	if(!CATATAN.equals(""))
	    	{
		    	htmlPageSetup += "<br>"+modelBI.openHTMLTableCatatanPerintah();
		    	htmlPageSetup += "<tr>";
		        htmlPageSetup += "<td valign=\"top\"  align=\"left\" style='border-bottom: 1px solid #000;font-size: 100%;' > ";
		    	htmlPageSetup += "<b>CATATAN</b>";
		    	htmlPageSetup += "</td>";
		    	htmlPageSetup += "</tr>";
		    	htmlPageSetup += "<tr>";
		        htmlPageSetup += "<td  valign=\"top\"  ><br>";
		    	htmlPageSetup += CATATAN;
		    	htmlPageSetup += "</td>";
		    	htmlPageSetup += "</tr>";
		    	htmlPageSetup += modelBI.closeHTMLTableCatatanPerintah();
	    	}
	    	*/
	    	/*
	    	htmlPageSetup += "<tr>";
	    	htmlPageSetup += "<td  valign=\"top\"  >"+CATATAN_SKRIN_PERINTAH+"</td>";
	    	htmlPageSetup += "</tr>";
	    	*/
	    	
	    	
			
			this.context.put("htmlSkrinMaklumat",CATATAN_PERINTAH_BI);			
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";	
		}
		else if(command.equals("showMaklumatketeranganhadirPrint"))
		{
			this.context.put("tajukLaporan", "LAMPIRAN KETERANGAN PERBICARAAN");
			String ID_PERBICARAAN = getParam("ID_PERBICARAAN");
			this.context.put("ID_PERBICARAAN", ID_PERBICARAAN);	
			//String skrinName = "perubahan";
			//this.context.put("skrinName", skrinName);			
			this.context.put("scrolPosition", getParam("scrolPosition"));
			String ID_PERMOHONANSIMATI = "";
			String ID_PERMOHONAN = "";
			String ID_PEMOHON = "";
			String ID_SIMATI = "";
			String flagPrint = "Y";
			String htmlSkrinMaklumat = "";
			Db db = null;
			try {
				db = new Db();		
				Map mainID = modelBI. mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String)mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String)mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String)mainID.get("ID_PEMOHON");
				ID_SIMATI = (String)mainID.get("ID_SIMATI");
				this.context.put("viewPerbicaraan", modelBI.viewPerbicaraan(session,ID_PERBICARAAN,ID_PERMOHONAN,db));
				htmlSkrinMaklumat = modelBI.htmlListKeterangan(session,formName,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,flagPrint,db);
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);	
			this.context.put("flagPrint",flagPrint);
			this.context.put("htmlSkrinMaklumat",htmlSkrinMaklumat);
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";	
		}
		else if(command.equals("showLaporanTukarpegawai"))
		{
			this.context.put("tajukLaporan", "LAPORAN PENUKARAN PEGAWAI PERBICARAAN");
			String carianTukarPegawaiNO_TUKARPEGAWAI = getParam("carianTukarPegawaiNO_TUKARPEGAWAI");
			String carianTukarPegawaiNO_FAIL = getParam("carianTukarPegawaiNO_FAIL");
			String carianTukarPegawaiID_NEGERIPEGAWAIBARU = getParam("carianTukarPegawaiID_NEGERIPEGAWAIBARU");
			String carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT = getParam("carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT");
			String carianTukarPegawaiID_NEGERIMHN = getParam("carianTukarPegawaiID_NEGERIMHN");
			String carianTukarPegawaiID_NEGERIMHNCONTENT = getParam("carianTukarPegawaiID_NEGERIMHNCONTENT");
			String carianTukarPegawaiNAMAPEGAWAIASAL = getParam("carianTukarPegawaiNAMAPEGAWAIASAL");
			String carianTukarPegawaiNAMAPEGAWAIBARU = getParam("carianTukarPegawaiNAMAPEGAWAIBARU");
			String carianTukarPegawaiSTATUS_TUKARPEGAWAI = getParam("carianTukarPegawaiSTATUS_TUKARPEGAWAI");
			String carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT = getParam("carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT");
			String carianTukarPegawaiTARIKH_MOHONMULA = getParam("carianTukarPegawaiTARIKH_MOHONMULA");
			String carianTukarPegawaiTARIKH_MOHONAKHIR = getParam("carianTukarPegawaiTARIKH_MOHONAKHIR");
			String carianTukarPegawaiTARIKH_BICARAMULA = getParam("carianTukarPegawaiTARIKH_BICARAMULA");
			String carianTukarPegawaiTARIKH_BICARAAKHIR = getParam("carianTukarPegawaiTARIKH_BICARAAKHIR");
			
			myLogger.info(">>> carianTukarPegawaiNO_TUKARPEGAWAI : "+carianTukarPegawaiNO_TUKARPEGAWAI);
			myLogger.info(">>> carianTukarPegawaiNO_FAIL : "+carianTukarPegawaiNO_FAIL);
			myLogger.info(">>> carianTukarPegawaiID_NEGERIPEGAWAIBARU : "+carianTukarPegawaiID_NEGERIPEGAWAIBARU);
			myLogger.info(">>> carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT : "+carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT);
			myLogger.info(">>> carianTukarPegawaiID_NEGERIMHN : "+carianTukarPegawaiID_NEGERIMHN);
			myLogger.info(">>> carianTukarPegawaiID_NEGERIMHNCONTENT : "+carianTukarPegawaiID_NEGERIMHNCONTENT);
			myLogger.info(">>> carianTukarPegawaiNAMAPEGAWAIASAL : "+carianTukarPegawaiNAMAPEGAWAIASAL);
			myLogger.info(">>> carianTukarPegawaiNAMAPEGAWAIBARU : "+carianTukarPegawaiNAMAPEGAWAIBARU);
			myLogger.info(">>> carianTukarPegawaiSTATUS_TUKARPEGAWAI : "+carianTukarPegawaiSTATUS_TUKARPEGAWAI);
			myLogger.info(">>> carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT  : "+carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT );
			myLogger.info(">>> carianTukarPegawaiTARIKH_MOHONMULA : "+carianTukarPegawaiTARIKH_MOHONMULA);
			myLogger.info(">>> carianTukarPegawaiTARIKH_MOHONAKHIR : "+carianTukarPegawaiTARIKH_MOHONAKHIR);
			myLogger.info(">>> carianTukarPegawaiTARIKH_BICARAMULA : "+carianTukarPegawaiTARIKH_BICARAMULA);
			myLogger.info(">>> carianTukarPegawaiTARIKH_BICARAAKHIR : "+carianTukarPegawaiTARIKH_BICARAAKHIR);
			
			Map hFilter = Collections.synchronizedMap(new HashMap());
			hFilter.put("carianTukarPegawaiNO_TUKARPEGAWAI",carianTukarPegawaiNO_TUKARPEGAWAI);
			hFilter.put("carianTukarPegawaiNO_FAIL",carianTukarPegawaiNO_FAIL);
			hFilter.put("carianTukarPegawaiID_NEGERIPEGAWAIBARU",carianTukarPegawaiID_NEGERIPEGAWAIBARU);
			hFilter.put("carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT",carianTukarPegawaiID_NEGERIPEGAWAIBARUCONTENT);
			hFilter.put("carianTukarPegawaiID_NEGERIMHN",carianTukarPegawaiID_NEGERIMHN);
			hFilter.put("carianTukarPegawaiID_NEGERIMHNCONTENT",carianTukarPegawaiID_NEGERIMHNCONTENT);
			hFilter.put("carianTukarPegawaiNAMAPEGAWAIASAL",carianTukarPegawaiNAMAPEGAWAIASAL);
			hFilter.put("carianTukarPegawaiNAMAPEGAWAIBARU",carianTukarPegawaiNAMAPEGAWAIBARU);
			hFilter.put("carianTukarPegawaiSTATUS_TUKARPEGAWAI",carianTukarPegawaiSTATUS_TUKARPEGAWAI);
			hFilter.put("carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT",carianTukarPegawaiSTATUS_TUKARPEGAWAICONTENT);
			hFilter.put("carianTukarPegawaiTARIKH_MOHONMULA",carianTukarPegawaiTARIKH_MOHONMULA);
			hFilter.put("carianTukarPegawaiTARIKH_MOHONAKHIR",carianTukarPegawaiTARIKH_MOHONAKHIR);
			hFilter.put("carianTukarPegawaiTARIKH_BICARAMULA",carianTukarPegawaiTARIKH_BICARAMULA);
			hFilter.put("carianTukarPegawaiTARIKH_BICARAAKHIR",carianTukarPegawaiTARIKH_BICARAAKHIR);
			
			this.context.put("scrolPosition", getParam("scrolPosition"));
			this.context.put("viewPerbicaraan","");
			
			String flagPrint = "Y";
			String htmlSkrinMaklumat = "";
			Db db = null;
			List listPermohonanTukarPegawai = null;
			try {
				db = new Db();
				
				Map getDetailUsers = modelBI.getDetailUsers(session, "", USER_ID_SYSTEM, "", null);
				String id_jawatan_login = "";
				String id_negeri_login = "";
				String id_seksyen_login = "";
				if(getDetailUsers!=null)
				{
					id_jawatan_login = (String)getDetailUsers.get("ID_JAWATAN");
					id_negeri_login = (String)getDetailUsers.get("ID_NEGERI");
					id_seksyen_login = (String)getDetailUsers.get("ID_SEKSYEN");
				}
				
				listPermohonanTukarPegawai = listPermohonanTukarPegawai(session,USER_ID_SYSTEM,id_jawatan_login,id_negeri_login,"Y","",null);
				htmlSkrinMaklumat = modelBI.htmlListLaporanTukarPegawai(hFilter,session,listPermohonanTukarPegawai,db);
				/*
				Map mainID = modelBI. mainID(session, ID_PERBICARAAN, db);
				ID_PERMOHONANSIMATI = (String)mainID.get("ID_PERMOHONANSIMATI");
				ID_PERMOHONAN = (String)mainID.get("ID_PERMOHONAN");
				ID_PEMOHON = (String)mainID.get("ID_PEMOHON");
				ID_SIMATI = (String)mainID.get("ID_SIMATI");
				this.context.put("viewPerbicaraan", modelBI.viewPerbicaraan(session,ID_PERBICARAAN,ID_PERMOHONAN,db));
				htmlSkrinMaklumat = modelBI.htmlListKeterangan(session,formName,ID_SIMATI,ID_PERMOHONANSIMATI,ID_PERBICARAAN,ID_PERMOHONAN,ID_PEMOHON,flagPrint,db);
				*/
				//htmlSkrinMaklumat = "LAPORAN PENUKARAN PEGAWAI PERBICARAAN";
			}
			finally {
				if (db != null)
					db.close();
			}
			/*
			this.context.put("ID_PEMOHON", ID_PEMOHON);
			this.context.put("ID_SIMATI", ID_SIMATI);
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			this.context.put("ID_PERMOHONANSIMATI", ID_PERMOHONANSIMATI);	
			*/
			this.context.put("flagPrint",flagPrint);
			this.context.put("htmlSkrinMaklumat",htmlSkrinMaklumat);
			skrin_name = "app/ppk/BicaraInteraktif/viewMaklumat.jsp";	
		}
		
		myLogger.info("skrin_name : "+skrin_name);
		return skrin_name;
	}
		
	public void defaultPut()
	{
		//initiate default value
		this.context.put("tajukLaporan", "");
		this.context.put("rowCss","");
		this.context.put("BIL","");
		this.context.put("scrolPosition","");
		this.context.put("div","");
		this.context.put("viewPerbicaraan","");
		this.context.put("listKehadiran","");
		this.context.put("listTurutHadir","");
		this.context.put("viewTuruthadir","");		
		this.context.put("ID_PERBICARAAN","");
		this.context.put("ID_PERMOHONAN","");
		this.context.put("ID_PERMOHONANSIMATI","");
		this.context.put("ID_SIMATI", "");
		this.context.put("htmlSkrinMaklumat","");
		this.context.put("formDynamicDropDown","");
		this.context.put("flagSalin", "");
		this.context.put("ID_BANDAR", "");
		this.context.put("ID_NEGERI", "");
		this.context.put("divID", "");
		this.context.put("commandSalin", "");
		this.context.put("skrinName", "");
		this.context.put("ID_OBPERMOHONAN", "");
		this.context.put("current_previous", "");
		this.context.put("divId","");
		this.context.put("flagPrint","N");
	}	
	
	
	public List listPermohonanTukarPegawai(HttpSession session, String user_id,String id_jawatan_login,String id_negeri_login,String flagCari,String flagDashboard, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPermohonanTukarPegawai = null;
		String sql = "";	
		
		//GET PARAM CARIAN
		String carianTukarPegawaiNO_FAIL = "";
		String carianTukarPegawaiNO_TUKARPEGAWAI = "";
		String carianTukarPegawaiID_NEGERIMHN = "";
		String carianTukarPegawaiID_NEGERIPEGAWAIBARU = "";
		String carianTukarPegawaiNAMAPEGAWAIASAL = "";
		String carianTukarPegawaiNAMAPEGAWAIBARU = "";
		String carianTukarPegawaiSTATUS_TUKARPEGAWAI = "";
		String carianTukarPegawaiTARIKH_MOHONMULA = "";
		String carianTukarPegawaiTARIKH_MOHONAKHIR = "";
		String carianTukarPegawaiTARIKH_BICARAMULA = "";
		String carianTukarPegawaiTARIKH_BICARAAKHIR = "";
		
		try{
			
		if(db != null)
		{
			db1 = db;
		}
		else
		{
			db1 = new Db();
		}
		
		stmt = db1.getStatement();			
		sql += " SELECT * FROM (SELECT  " +
				//" (CASE WHEN TP.STATUS_TUKARPEGAWAI = '1' AND (PR.TARIKH_BICARA < SYSDATE OR  PH.FLAG_JENIS_KEPUTUSAN IN (0,1,2)) THEN 'Y' ELSE '' END) AS PERBICARAAN_EXP, "+
				" (CASE WHEN TP.ID_NEGERIPEGAWAIASAL != TP.ID_NEGERIPEGAWAIBARU THEN 'Y' ELSE '' END) AS KPP_HQ, N.NAMA_NEGERI AS NAMA_NEGERI_PEGAWAI_GANTI,"+
				" (CASE WHEN TP.STATUS_TUKARPEGAWAI = '1' THEN 'PERMOHONAN BARU'  "+
				" WHEN TP.STATUS_TUKARPEGAWAI = '2' THEN 'LULUS'  "+
				" WHEN TP.STATUS_TUKARPEGAWAI = '3' THEN 'TOLAK' ELSE '' END) AS KETERANGAN_STATUS_TUKARPEGAWAI, "+
				" F.NO_FAIL,R_A.NAMA_PEGAWAI AS NAMA_PEGAWAI_ASAL, R_B.NAMA_PEGAWAI AS NAMA_PEGAWAI_BARU, "+
				" CASE WHEN PR.MASA_BICARA LIKE '%.%' THEN   "+
				" (CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, 1, INSTR(PR.MASA_BICARA, '.') - 1)),0) = 1 THEN '0' ||   "+
				" CASE WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0' ELSE PR.MASA_BICARA END  "+
				" WHEN NVL(length(SUBSTR(PR.MASA_BICARA, INSTR(PR.MASA_BICARA, '.') + 1)),0) = 1 THEN PR.MASA_BICARA || '0'  "+
				" ELSE PR.MASA_BICARA END)  "+
				" WHEN LENGTH(PR.MASA_BICARA) = 4 THEN SUBSTR(PR.MASA_BICARA, 1, 2) || '.' || SUBSTR(PR.MASA_BICARA, 3)  "+
				" ELSE '' END || (CASE WHEN PR.JENIS_MASA_BICARA = '1' THEN ' PAGI'  "+
				" WHEN PR.JENIS_MASA_BICARA = '2' THEN ' TENGAH HARI'  "+
				" WHEN PR.JENIS_MASA_BICARA = '3' THEN ' PETANG' ELSE '' END) AS MASA_BICARA, PR.BIL_BICARA, "+
				" TO_CHAR(PR.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, PH.FLAG_JENIS_KEPUTUSAN, "+
				" (CASE WHEN PH.FLAG_JENIS_KEPUTUSAN = '0' THEN 'SELESAI' "+
				" WHEN PH.FLAG_JENIS_KEPUTUSAN = '1' THEN 'TANGGUH' "+
				" WHEN PH.FLAG_JENIS_KEPUTUSAN = '2' THEN 'BATAL' ELSE 'PROSES PERBICARAAN' END) AS STATUS_BICARA, P.ID_NEGERIMHN, P.ID_PERMOHONAN, SM.ID_PERMOHONANSIMATI, SM.ID_SIMATI, "+
				" TP.*  FROM TBLRUJNEGERI N,TBLPPKTUKARPEGAWAI TP, TBLPFDFAIL F, TBLPPKRUJUNIT R_A, TBLPPKRUJUNIT R_B, TBLPPKPERBICARAAN PR, TBLPPKPERINTAH PH, TBLPPKPERMOHONAN P, TBLPPKPERMOHONANSIMATI SM" +
				//" ,TBLRUJDAERAH DD "+ //SAJA UNTUK TEST BANYAK ROWS
				" WHERE TP.ID_FAIL = F.ID_FAIL AND TP.ID_NEGERIPEGAWAIBARU = N.ID_NEGERI "+
				" AND TP.ID_PEGAWAIASAL = R_A.ID_UNITPSK "+
				" AND TP.ID_PEGAWAIBARU = R_B.ID_UNITPSK "+
				" AND TP.ID_PERBICARAAN = PR.ID_PERBICARAAN " +
				" AND P.ID_PERMOHONAN = SM.ID_PERMOHONAN " +
				" AND F.ID_FAIL = P.ID_FAIL "+
				"AND PR.ID_PERBICARAAN = PH.ID_PERBICARAAN(+) ";
		
				if(flagCari.equals("Y"))
				{
					carianTukarPegawaiNO_TUKARPEGAWAI = getParam("carianTukarPegawaiNO_TUKARPEGAWAI");
					carianTukarPegawaiNO_FAIL = getParam("carianTukarPegawaiNO_FAIL");
					carianTukarPegawaiID_NEGERIMHN = getParam("carianTukarPegawaiID_NEGERIMHN");
					carianTukarPegawaiID_NEGERIPEGAWAIBARU = getParam("carianTukarPegawaiID_NEGERIPEGAWAIBARU");
					carianTukarPegawaiNAMAPEGAWAIASAL = getParam("carianTukarPegawaiNAMAPEGAWAIASAL");
					carianTukarPegawaiNAMAPEGAWAIBARU = getParam("carianTukarPegawaiNAMAPEGAWAIBARU");
					carianTukarPegawaiSTATUS_TUKARPEGAWAI = getParam("carianTukarPegawaiSTATUS_TUKARPEGAWAI");
					carianTukarPegawaiTARIKH_MOHONMULA = getParam("carianTukarPegawaiTARIKH_MOHONMULA");
					carianTukarPegawaiTARIKH_MOHONAKHIR = getParam("carianTukarPegawaiTARIKH_MOHONAKHIR");
					carianTukarPegawaiTARIKH_BICARAMULA = getParam("carianTukarPegawaiTARIKH_BICARAMULA");
					carianTukarPegawaiTARIKH_BICARAAKHIR = getParam("carianTukarPegawaiTARIKH_BICARAAKHIR");
					
					if(!carianTukarPegawaiNO_FAIL.equals(""))
					{
						sql += " AND UPPER(F.NO_FAIL) LIKE '%"+carianTukarPegawaiNO_FAIL.toUpperCase()+"%' "; 
					}
					if(!carianTukarPegawaiNO_TUKARPEGAWAI.equals(""))
					{
						sql += " AND UPPER(TP.NO_TUKARPEGAWAI) LIKE '%"+carianTukarPegawaiNO_TUKARPEGAWAI.toUpperCase()+"%' "; 
					}
					if(!carianTukarPegawaiNAMAPEGAWAIASAL.equals(""))
					{
						sql += " AND UPPER(R_A.NAMA_PEGAWAI) LIKE '%"+carianTukarPegawaiNAMAPEGAWAIASAL.toUpperCase()+"%' "; 
					}
					if(!carianTukarPegawaiNAMAPEGAWAIBARU.equals(""))
					{
						sql += " AND UPPER(R_B.NAMA_PEGAWAI) LIKE '%"+carianTukarPegawaiNAMAPEGAWAIBARU.toUpperCase()+"%' "; 
					}					
					
					if(!carianTukarPegawaiTARIKH_MOHONMULA.equals("") && !carianTukarPegawaiTARIKH_MOHONAKHIR.equals(""))
					{
					sql += " AND TP.TARIKH_MOHON BETWEEN TO_DATE('"+carianTukarPegawaiTARIKH_MOHONMULA+"','DD/MM/YYYY') AND TO_DATE('"+carianTukarPegawaiTARIKH_MOHONAKHIR+"','DD/MM/YYYY') ";
					}
					if(!carianTukarPegawaiTARIKH_MOHONMULA.equals("") && carianTukarPegawaiTARIKH_MOHONAKHIR.equals(""))
					{
					sql += " AND TP.TARIKH_MOHON > TO_DATE('"+carianTukarPegawaiTARIKH_MOHONMULA+"','DD/MM/YYYY') ";
					}
					if(carianTukarPegawaiTARIKH_MOHONMULA.equals("") && !carianTukarPegawaiTARIKH_MOHONAKHIR.equals(""))
					{
					sql += " AND TP.TARIKH_MOHON < TO_DATE('"+carianTukarPegawaiTARIKH_MOHONAKHIR+"','DD/MM/YYYY') ";
					}
					
					
					if(!carianTukarPegawaiTARIKH_BICARAMULA.equals("") && !carianTukarPegawaiTARIKH_BICARAAKHIR.equals(""))
					{
					sql += " AND PR.TARIKH_BICARA BETWEEN TO_DATE('"+carianTukarPegawaiTARIKH_BICARAMULA+"','DD/MM/YYYY') AND TO_DATE('"+carianTukarPegawaiTARIKH_BICARAAKHIR+"','DD/MM/YYYY') ";
					}
					if(!carianTukarPegawaiTARIKH_BICARAMULA.equals("") && carianTukarPegawaiTARIKH_BICARAAKHIR.equals(""))
					{
					sql += " AND PR.TARIKH_BICARA > TO_DATE('"+carianTukarPegawaiTARIKH_BICARAMULA+"','DD/MM/YYYY') ";
					}
					if(carianTukarPegawaiTARIKH_BICARAMULA.equals("") && !carianTukarPegawaiTARIKH_BICARAAKHIR.equals(""))
					{
					sql += " AND PR.TARIKH_BICARA < TO_DATE('"+carianTukarPegawaiTARIKH_BICARAAKHIR+"','DD/MM/YYYY') ";
					}
													
					if(!carianTukarPegawaiSTATUS_TUKARPEGAWAI.equals(""))
					{
						sql += " AND TP.STATUS_TUKARPEGAWAI = '"+carianTukarPegawaiSTATUS_TUKARPEGAWAI+"' ";
					}
					if(!carianTukarPegawaiSTATUS_TUKARPEGAWAI.equals(""))
					{
						sql += " AND TP.STATUS_TUKARPEGAWAI = '"+carianTukarPegawaiSTATUS_TUKARPEGAWAI+"' ";
					}
					if(!carianTukarPegawaiID_NEGERIMHN.equals(""))
					{
						sql += " AND P.ID_NEGERIMHN = '"+carianTukarPegawaiID_NEGERIMHN+"' ";
					}
					if(!carianTukarPegawaiID_NEGERIPEGAWAIBARU.equals(""))
					{
						sql += " AND TP.ID_NEGERIPEGAWAIBARU = '"+carianTukarPegawaiID_NEGERIPEGAWAIBARU+"' ";
					}
				}
				else
				{
					sql += " AND TP.STATUS_TUKARPEGAWAI = '1' ";
				}
				
				if(id_jawatan_login.equals("5") || id_jawatan_login.equals("4"))
				{
					if(!id_negeri_login.equals("16"))
					{
						sql += " AND TP.ID_NEGERIPEGAWAIBARU = '"+id_negeri_login+"' ";
					}
				}
				else
				{
					sql += " AND TP.ID_TUKARPEGAWAI = '' ";
				}
				
		
		sql += " ORDER BY TP.STATUS_TUKARPEGAWAI ASC, TP.TARIKH_MOHON DESC, PR.TARIKH_BICARA DESC) ";		
		
		
		myLogger.info(" BICARA INTERAKTIF : SQL listPermohonanTukarPegawai :"+ sql);
		
		rs = stmt.executeQuery(sql);
		listPermohonanTukarPegawai = Collections.synchronizedList(new ArrayList());
		
		
		Map h = null;
		int bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			String rowCss = "";
			
			if ( (bil % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }			
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("NO_TUKARPEGAWAI",rs == null ? "" :rs.getString("NO_TUKARPEGAWAI") == null ? "" : rs.getString("NO_TUKARPEGAWAI"));	
			h.put("ID_FAIL",rs == null ? "" :rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));			
			h.put("NAMA_NEGERI_PEGAWAI_GANTI",rs == null ? "" :rs.getString("NAMA_NEGERI_PEGAWAI_GANTI") == null ? "" : rs.getString("NAMA_NEGERI_PEGAWAI_GANTI"));
			h.put("ID_PERMOHONAN",rs == null ? "" :rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
			h.put("ID_PERMOHONANSIMATI",rs == null ? "" :rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
			h.put("ID_SIMATI",rs == null ? "" :rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
			h.put("ID_TUKARPEGAWAI",rs == null ? "" :rs.getString("ID_TUKARPEGAWAI") == null ? "" : rs.getString("ID_TUKARPEGAWAI"));
			h.put("KETERANGAN_STATUS_TUKARPEGAWAI",rs == null ? "" :rs.getString("KETERANGAN_STATUS_TUKARPEGAWAI") == null ? "" : rs.getString("KETERANGAN_STATUS_TUKARPEGAWAI"));
			h.put("NO_FAIL",rs == null ? "" :rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
			h.put("NAMA_PEGAWAI_ASAL",rs == null ? "" :rs.getString("NAMA_PEGAWAI_ASAL") == null ? "" : rs.getString("NAMA_PEGAWAI_ASAL").toUpperCase());
			h.put("NAMA_PEGAWAI_BARU",rs == null ? "" :rs.getString("NAMA_PEGAWAI_BARU") == null ? "" : rs.getString("NAMA_PEGAWAI_BARU").toUpperCase());
			h.put("MASA_BICARA",rs == null ? "" :rs.getString("MASA_BICARA") == null ? "" : rs.getString("MASA_BICARA"));
			h.put("TARIKH_BICARA",rs == null ? "" :rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA"));
			h.put("BIL_BICARA",rs == null ? "" :rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
			h.put("STATUS_BICARA",rs == null ? "" :rs.getString("STATUS_BICARA") == null ? "" : rs.getString("STATUS_BICARA"));		
			h.put("ID_PERBICARAAN",rs == null ? "" :rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));	
			listPermohonanTukarPegawai.add(h);
		}

		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		
		
		return listPermohonanTukarPegawai;
	}
	
	/*
	@SuppressWarnings("unchecked")	
	public String setupSkrinCarianLaporanStatsPegawai(HttpSession session,String skrinName,String command,String formName,String mode,String paramsButton,Db db)throws Exception {
		String htmlPageSetup = "";
		Db db1 = null;
		try{
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			
			String carianStatsNamaPegawai = getParam("carianStatsNamaPegawai");
			String carianStatsIdNegeri = getParam("carianStatsIdNegeri");
			String carianStatsTarikhMula = getParam("carianStatsTarikhMula");
			String carianStatsTarikhAkhir = getParam("carianStatsTarikhAkhir");
						
			htmlPageSetup += modelBI.openHTMLTable();
			htmlPageSetup += modelBI.setRowText(session,"","","",skrinName,mode,null,"Nama Pegawai","","","","","carianStatsNamaPegawai","","text","Y","","Y",carianStatsNamaPegawai,0,db1);
			htmlPageSetup += modelBI.setRowSelect(session,"","",skrinName,command,mode,null,"Negeri Pegawai","","","","","carianStatsNamaPegawai","","select","Y","TBLRUJNEGERI","ID_NEGERI","KOD_NEGERI","NAMA_NEGERI","","","","","","","","","",formName,carianStatsNamaPegawai,0,db1);//dynamic ajax call
			htmlPageSetup += modelBI.setRowTextTarikh(session,"","",skrinName,mode,null,"Tarikh Perbicaraan (Mula)","","","","","carianStatsTarikhMula","","text","Y","10","Y",carianStatsTarikhMula,0,db1);
			htmlPageSetup += modelBI.setRowTextTarikh(session,"","",skrinName,mode,null,"Tarikh Perbicaraan (Akhir)","","","","","carianStatsTarikhAkhir","","text","Y","10","Y",carianStatsTarikhAkhir,0,db1);
			htmlPageSetup += modelBI.closeHTMLTable();
							
			htmlPageSetup += modelBI.setupButtonCarianPegawaiStats(session,skrinName,formName);
			
			
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
		return htmlPageSetup;		
		
	}
	*/
	
	

}

