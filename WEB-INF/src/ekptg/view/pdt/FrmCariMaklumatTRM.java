package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

public class FrmCariMaklumatTRM extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmCariMaklumatTRM.class);
	String skrin_name = "app/pdt/trm/index.jsp";
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		
		List list = null;
		Map view = null;
		Map viewWartaAsal = null;
		Map viewAsalInduk = null;
		Map viewBaruInduk = null;
		//Map viewWartaWujud = null;
		//Map viewWartaWujud_edit = null;
		//Map viewTRM = null;
		List listNegeri = null;
		List listDaerah = null;
		List listMukim = null;
		List listWARTATRMHISTORY = null;
		List listWARTATRMHISTORY_SUB = null;
		List listREPORTTAHUNNEGERI = null;
		List listREPORTNEGERI = null;
		List listTahun = null;
		Map viewBaru = null;
		Map viewAsal = null;
		Map viewInduk = null;
		/*
		Map getDetailsUser = null;		
		
		List listNegeriBahagian = null;
		List listUnit = null;
		List listJenisAduan = null;		
		List listKategoriAduan = null;
		List listJam = null;
		List listMinit = null;
		List listBahagian = null;
		List listJenisSumberPengadu = null;
		List listJenisSumberBahagian = null;
		List listStatus = null;
		List listStatusUI = null;
		List listStatusBahagian= null;
		List listJenisTindakan = null;
		List listLampiran = null;
		List listKronologi = null;		
		Hashtable h_last_trasnsaksi = null;
		Hashtable h_trasnsaksi = null;
		Hashtable h_overall = null;
		List listNotifikasi = null;
		*/
		
		defaultPut();
		
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		myLogger.info("TRM command : "+command);
		this.context.put("command",command);
		String fromDashboard = getParam("fromDashboard");
		myLogger.info(" fromDashboard : "+fromDashboard);
		this.context.put("fromDashboard",fromDashboard);		
		String USER_ROLE = (String) session.getAttribute("myrole");
		this.context.put("USER_ROLE",USER_ROLE);
		myLogger.info("USER_ROLE : "+USER_ROLE);
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		this.context.put("USER_UNIT",USER_UNIT);	
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);
		String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		this.context.put("selectedLanguage",selectedLanguage);
		myLogger.info("selectedLanguage command : "+selectedLanguage);
		//String mode = getParam("mode");		
		String commandFrom = getParam("commandFrom");//list = dari senarai
		this.context.put("commandFrom",commandFrom);
		String BIL = getParam("BIL");//list = dari senarai
		this.context.put("BIL",BIL);
		String rowCss = getParam("rowCss");//list = dari senarai
		this.context.put("rowCss",rowCss);
		
		if(command.equals("JanaTahun"))
		{
			int LAP_TAHUN_DARI = Integer.parseInt(getParam("LAP_TAHUN_DARI"));
			int LAP_TAHUN_HINGGA = Integer.parseInt(getParam("LAP_TAHUN_HINGGA"));			
			listTahun = listTahun(session,null, LAP_TAHUN_DARI,LAP_TAHUN_HINGGA);
			this.context.put("listTahun",listTahun);
			skrin_name = "app/pdt/trm/listTahun.jsp";
		}
		else if(command.equals("JanaLaporan") || command.equals("TarikDataByTahun") || command.equals("SaveLaporan") || command.equals("EditLaporan"))
		{		
			String FLAG_CARIAN_LAPORAN = getParam("FLAG_CARIAN_LAPORAN");
			this.context.put("FLAG_CARIAN_LAPORAN", FLAG_CARIAN_LAPORAN);			
			this.context.put("scrolPosition",getParam("scrolPosition"));
			this.context.put("modeReport",getParam("modeReport"));
			//int LAP_TAHUN_DARI = Integer.parseInt(getParam("LAP_TAHUN_DARI"));
			//int LAP_TAHUN_HINGGA = Integer.parseInt(getParam("LAP_TAHUN_HINGGA"));
			String[] NEGERI_CHECKLIST = request.getParameterValues("checkSUB_NEGERI");
			String[] TAHUN_CHECKLIST = request.getParameterValues("checkSUB_TAHUN");	
			
			//myLogger.info("LAP_TAHUN_DARI : "+LAP_TAHUN_DARI);
			//myLogger.info("LAP_TAHUN_HINGGA : "+LAP_TAHUN_HINGGA);
			
						
			String list_ID_NEGERI = " '000000'";
			for (String GETID_NEGERI : NEGERI_CHECKLIST) {
				list_ID_NEGERI += ",'"+GETID_NEGERI+"'";
			}
			String list_TAHUN = " '0000'";
			for (String GETTAHUN : TAHUN_CHECKLIST) {
				list_TAHUN += ",'"+GETTAHUN+"'";
			}		
			
			Db db = null;
			try {
				db = new Db();
				if(command.equals("JanaLaporan") || command.equals("SaveLaporan") || command.equals("TarikDataByTahun"))
				{
					if(command.equals("JanaLaporan"))
					{
						deletePilihan(session,db);	
					}
										
					for (String TAHUN: TAHUN_CHECKLIST)
					{
						int i = Integer.parseInt(TAHUN);
						//int col_TAHUN = i;
						
						if(command.equals("JanaLaporan"))
						{
							insertPilihanTahun(session,i+"",db); 
						}	
						
						
						
						for (String CN : NEGERI_CHECKLIST) {
							myLogger.info("TAHUN : "+i+" ID NEGERI : "+CN);
							
							
							if(command.equals("TarikDataByTahun"))
							{
								String REGE_TAHUN = getParam("REGE_TAHUN");
								if(Integer.parseInt(REGE_TAHUN)==i)
								{
									//update untuk selected year saja
									//saveLaporanTahunNegeri(session,ID_TRMREPTAHUNNEGERI,i+"",CN,LUAS,db);
									reganerateByYearTarikData(session,i+"",CN,db);
								}								
								
							}
							else if(command.equals("SaveLaporan"))
							{
								String ID_TRMREPTAHUNNEGERI = getParam("ID_TRMREPTAHUNNEGERI_"+i+CN);
								String LUAS = getParam("LUAS_"+i+CN);
								String LUAS_ASAL = getParam("LUAS_ASAL_"+i+CN);
								
								//myLogger.info("ID_TRMREPTAHUNNEGERI : "+ID_TRMREPTAHUNNEGERI+" Double.parseDouble(LUAS) : "+Double.parseDouble(LUAS)+" Double.parseDouble(LUAS_ASAL) : "+Double.parseDouble(LUAS_ASAL));
								if(Double.parseDouble(LUAS) != Double.parseDouble(LUAS_ASAL))
								{
									saveLaporanTahunNegeri(session,ID_TRMREPTAHUNNEGERI,i+"",CN,LUAS,db);
								}
							}
							else
							{
								if(checkTahunNegeriLaporan(session,i,Long.parseLong(CN),db)==false)
								{
									//insert tapak
									saveLaporanTahunNegeri(session,"",i+"",CN,"",db);
								}
							}
						}
					}
				}
				listREPORTTAHUNNEGERI = listREPORTTAHUNNEGERI(session,list_ID_NEGERI,list_TAHUN, db);
				listREPORTNEGERI = listREPORTNEGERI(session, list_ID_NEGERI,  db);
			}/*
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}*/
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("listREPORTTAHUNNEGERI",listREPORTTAHUNNEGERI);
			this.context.put("listREPORTNEGERI",listREPORTNEGERI);
			
			skrin_name = "app/pdt/trm/laporanTRMTahunNegeri.jsp";
		}
		else if(command.equals("resetLaporan"))
		{	
			this.context.put("modeReport",getParam("modeReport"));
			skrin_name = "app/pdt/trm/blankLaporan.jsp";
		}
		else if(command.equals("showCARIANLaporan"))
		{
			this.context.put("modeReport",getParam("modeReport"));
			this.context.put("FLAG_CARIAN_LAPORAN", getParam("FLAG_CARIAN_LAPORAN"));
			this.context.put("OPENCLOSE_CARIAN_LAPORAN",getParam("OPENCLOSE_CARIAN_LAPORAN"));			
			Db db = null;
			try {
				db = new Db();
				listNegeri = listNegeri(session,db);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("listNegeri",listNegeri);	
			this.context.put("LAP_TAHUN_HINGGA","");	
			this.context.put("LAP_TAHUN_DARI","");	
			skrin_name = "app/pdt/trm/skrinCarianLaporan.jsp";
		}
		
		
		
		
		else if(command.equals("showSenarai_Print"))
		{	
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			list = listTRM(session,"","",command);
			this.context.put("list",list);
			this.context.put("listLog_forPrint",list);			
			skrin_name = "app/pdt/trm/SenaraiPrint.jsp";
		}
		else if(command.equals("delete"))
		{
			
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			myLogger.info(" showSenaraiBatal ID_WARTATRMINDUK : "+ID_WARTATRMINDUK);
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String LOCATION = getParam("LOCATION");
			this.context.put("LOCATION",LOCATION);			
			String ID_WARTATRM= getParam("ID_WARTATRM");
			this.context.put("LOCATION",LOCATION);
			this.context.put("AFTERDELETE","Y");
			String ID_WARTATRMASAL = getParam("ID_WARTATRMASAL"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
			
			
			/*
			String IDSET = "";
			if(!ID_WARTATRMINDUK.equals(""))
			{
				IDSET = ID_WARTATRMINDUK;
			}
			else if(!ID_WARTATRMASAL.equals(""))
			{
				IDSET = ID_WARTATRMASAL;
			}
			
			if(!IDSET.equals(""))
			{
				Db db = null;
				try {
					db = new Db();
					viewAsalInduk = view(session, IDSET,"","","",db);
					//delete
					delete(session,ID_WARTATRM,db);
					viewBaruInduk = view(session, IDSET,"","","",db);
					checkForHistory(session,IDSET, viewAsalInduk, viewBaruInduk,"UPDATE",db);	
				}
				finally {
					if (db != null)
						db.close();
				}
				}
			*/
			
			delete(session,ID_WARTATRM,null);
			
			String action = getParam("action");				
			String ID_W = "";
			String J_S = "";
			
			if(!ID_WARTATRMINDUK.equals(""))
			{
				ID_W = ID_WARTATRMINDUK;
				J_S = JENISSUB;
			}			
			if(JENISSUB.equals("B"))
			{
				command = "showSenaraiBatal";
			}
			else if(JENISSUB.equals("G"))
			{
				command = "showSenaraiGanti";
			}
			
			list = listTRM(session,ID_W,J_S,command);			
			setupPageList(session, action, list, "list",command,LOCATION);			
			skrin_name = "app/pdt/trm/Senarai.jsp";
			
		}	
		else if(command.equals("showSenaraiBatal") 
				|| command.equals("showSenaraiGanti") )
		{
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			myLogger.info(" showSenaraiBatal ID_WARTATRMINDUK : "+ID_WARTATRMINDUK);
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String ID_WARTATRM = getParam("ID_WARTATRM");
			this.context.put("ID_WARTATRM",ID_WARTATRM);
			String LOCATION = getParam("LOCATION");
			this.context.put("LOCATION",LOCATION);
			String action = getParam("action");	
			list = listTRM(session,ID_WARTATRMINDUK,JENISSUB,command);			
			setupPageList(session, action, list, "list",command,LOCATION);			
			skrin_name = "app/pdt/trm/Senarai.jsp";
			
		}		
		else if(command.equals("showSenarai"))
		{			
			//String ID_WARTATRM = getParam("ID_WARTATRM");					
			this.context.put("FLAG_CARIAN", getParam("FLAG_CARIAN"));
			this.context.put("OPENCLOSE_CARIAN",getParam("OPENCLOSE_CARIAN"));
			String action = getParam("action");			
			list = listTRM(session,"","",command);			
			setupPageList(session, action, list, "list",command,"div_Senarai");			
			skrin_name = "app/pdt/trm/Senarai.jsp";
		}		
		else if(command.equals("showDaerah") || command.equals("showDaerahCR"))
		{
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String ID_NEGERI = getParam("ID_NEGERI");
			//this.context.put("IDWARTAFRMLIST", getParam("IDWARTAFRMLIST"));
			String ID_WARTATRM = getParam("ID_WARTATRM");
			this.context.put("ID_WARTATRM", ID_WARTATRM);
			this.context.put("TABKOD", getParam("TABKOD"));
			listDaerah = listDaerah(session,ID_NEGERI,null);
			this.context.put("listDaerah", listDaerah);
			if(command.equals("showDaerahCR"))
			{
				skrin_name = "app/pdt/trm/listDaerahCR.jsp";
			}
			else
			{
				skrin_name = "app/pdt/trm/listDaerah.jsp";
			}
		}
		else if(command.equals("showMukim") || command.equals("showMukimCR"))
		{
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String ID_DAERAH = getParam("ID_DAERAH");
			//this.context.put("IDWARTAFRMLIST", getParam("IDWARTAFRMLIST"));
			String ID_WARTATRM = getParam("ID_WARTATRM");			
			this.context.put("ID_WARTATRM", ID_WARTATRM);
			this.context.put("TABKOD", getParam("TABKOD"));
			listMukim = listMukim(session,ID_DAERAH,null);
			this.context.put("listMukim", listMukim);
			if(command.equals("showMukimCR"))
			{
				skrin_name = "app/pdt/trm/listMukimCR.jsp";
			}
			else
			{
				skrin_name = "app/pdt/trm/listMukim.jsp";
			}
		}
		else if(command.equals("displayUpload"))
		{
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String TEMPID_WARTATRM = getParam("TEMPID_WARTATRM");
			this.context.put("TEMPID_WARTATRM",TEMPID_WARTATRM);
			String ID_WARTATRM = getParam("ID_WARTATRM");
			String COOR_UPLOAD = getParam("COOR_UPLOAD");
			String type = getParam("type");
			String mode = getParam("mode");	
			Db db = null;
			try {
				db = new Db();
				
				deleteFloatingDoc(db);//lampiran yg tergantung selama 120 minit akan dihapuskan dari DB
				
				if(!TEMPID_WARTATRM.equals(""))
				{
					view = view(session,TEMPID_WARTATRM,getParam("rowCss"),getParam("BIL"),selectedLanguage,TEMPID_WARTATRM,db);
				}
				else
				{
					view = view(session,ID_WARTATRM,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
				}	
			
			}
			finally {
				if (db != null)
					db.close();
			}
			
			this.context.put("view",view);
			this.context.put("COOR_UPLOAD",COOR_UPLOAD);
			this.context.put("type",type);
			this.context.put("mode",mode);
			this.context.put("ID_WARTATRM",ID_WARTATRM);
			
			skrin_name = "app/pdt/trm/displayUpload.jsp";
		}	
		else if(command.equals("close_viewHistory"))
		{
			skrin_name = "app/pdt/trm/blank_viewHISTORY.jsp";
		}		
		else if(command.equals("checkWartaDuplicate"))
		{
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String ID_WARTATRM = getParam("ID_WARTATRM");
			this.context.put("ID_WARTATRM", ID_WARTATRM);
			String NO_WARTA = getParam("NO_WARTA"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
			String wartaDuplicate = "N";		
			
			if(checkDuplicateWarta(session,ID_WARTATRM,NO_WARTA)==true)
			{
				wartaDuplicate = "Y";
			}
			
			this.context.put("wartaDuplicate", wartaDuplicate);
			skrin_name = "app/pdt/trm/wartaDuplicate.jsp";			
		}	
		else if(command.equals("displayUtamaHISTORY") || command.equals("carianUtamaHISTORY"))
		{	
			/*String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);*/
			String ID_WARTATRM = getParam("ID_WARTATRM");
			this.context.put("ID_WARTATRM", ID_WARTATRM);			
			this.context.put("adaHISTORY", "");
			this.context.put("FLAG_DEFAULT_HISTORY", getParam("FLAG_DEFAULT_HISTORY"));
			
			Db db = null;
			try {
				db = new Db();
				Integer totalHISTORY = getHISTORYCount(session,ID_WARTATRM,db);
				if(totalHISTORY>0)
				{
					this.context.put("totalHISTORY", totalHISTORY);
					this.context.put("adaHISTORY", "Y");			
					
					
					String carianTerperinciHISTORY = getParam("carianTerperinciHISTORY_"+ID_WARTATRM);
					String TARIKH_MULA_HISTORY = getParam("TARIKH_MULA_HISTORY_"+ID_WARTATRM);
					String TARIKH_AKHIR_HISTORY = getParam("TARIKH_AKHIR_HISTORY_"+ID_WARTATRM);
					
					Date today_date = new Date();
					String today_date_str= new SimpleDateFormat("dd/MM/yyyy").format(today_date);
					
					String action = getParam("action");
					listWARTATRMHISTORY = listWARTATRMHISTORY(session,ID_WARTATRM,"","UTAMA",
							carianTerperinciHISTORY,TARIKH_MULA_HISTORY, TARIKH_AKHIR_HISTORY,db);
					setupPageList(session, action, listWARTATRMHISTORY, "listWARTATRMHISTORY",command,"div_ListHISTORY"+ID_WARTATRM);				
					
					/*
					listPengunaHISTORYforPrint = listPengunaHISTORY(session,USER_ID,"","LAPORAN",
							carianTerperinciHISTORY,TARIKH_MULA_HISTORY, TARIKH_AKHIR_HISTORY);				
					this.context.put("listPengunaHISTORYforPrint",listPengunaHISTORYforPrint);
					*/
					this.context.put("ID_WARTATRM",ID_WARTATRM);
					this.context.put("carianTerperinciHISTORY",carianTerperinciHISTORY.toUpperCase());
					this.context.put("TARIKH_MULA_HISTORY",TARIKH_MULA_HISTORY);
					this.context.put("TARIKH_AKHIR_HISTORY",TARIKH_AKHIR_HISTORY);
					this.context.put("TARIKH_CURRENT",today_date_str);
				
				}
			}
			finally {
				if (db != null)
					db.close();
			}
			skrin_name = "app/pdt/trm/SenaraiWARTATRMHISTORY.jsp";
		}
		
		else if(command.equals("viewHISTORY_SUB"))
		{
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String ID_WARTATRM = getParam("ID_WARTATRM");
			this.context.put("ID_WARTATRM", ID_WARTATRM);
			this.context.put("AKTIVITI", getParam("AKTIVITI"));
			this.context.put("TARIKH_MASUK", getParam("TARIKH_MASUK"));
			String ID_SEJARAHWTRMUTAMA = getParam("ID_SEJARAHWTRMUTAMA");
			this.context.put("ID_SEJARAHWTRMUTAMA", ID_SEJARAHWTRMUTAMA);	
			listWARTATRMHISTORY_SUB = listWARTATRMHISTORY(session,ID_WARTATRM,ID_SEJARAHWTRMUTAMA,"SUB",
					"","","",null);	
			this.context.put("listWARTATRMHISTORY_SUB", listWARTATRMHISTORY_SUB);	
			skrin_name = "app/pdt/trm/SenaraiWARTATRMHISTORY_SUB.jsp";
		}
		
		else if(command.equals("reloadTRM") || command.equals("reloadTRMFromHistory"))
		{
			String ID_WARTATRM = getParam("ID_WARTATRM");
			/*
			this.context.put("ID_WARTATRM",ID_WARTATRM);			
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);			
			String BIL = getParam("BIL");
			this.context.put("BIL",BIL);
			String rowCss = getParam("rowCss");
			this.context.put("rowCss",rowCss);
			*/
			view = view(session, ID_WARTATRM,getParam("rowCss"),getParam("BIL"),selectedLanguage,null);
			this.context.put("view",view);
			skrin_name = "app/pdt/trm/edit_TRM.jsp";
		}
		else if(command.equals("add") || command.equals("view") || command.equals("edit") 
				|| command.equals("save") || command.equals("close")
				|| command.equals("viewWarta"))
		{		
			String mode = getParam("mode");
			String FlagFrom = getParam("FlagFrom");
			String ID_WARTATRMASAL = getParam("ID_WARTATRMASAL");
			String setJENISWARTA = getParam("setJENISWARTA");	
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			String JENISSUB = getParam("JENISSUB");			;
			String ID_WARTATRM = getParam("ID_WARTATRM");
			//String commandFrom = getParam("commandFrom");
			myLogger.info(" commandFrom ::::::::: "+commandFrom);
			
			//String IDWARTAFRMLIST = getParam("IDWARTAFRMLIST");
			//String TABKODCHECK = getParam("TABKODCHECK"+IDWARTAFRMLIST);
			//String ID_WARTATRMCHECK = getParam("ID_WARTATRMCHECK"+IDWARTAFRMLIST);
			//String ID_TRMUTAMACHECK = getParam("ID_TRMUTAMACHECK"+IDWARTAFRMLIST);
			//String ID_WARTAWUJUDCHECK = getParam("ID_WARTAWUJUDCHECK"+IDWARTAFRMLIST);
			
			myLogger.info(" getParam(ID_WARTATRM) ::::::::: "+getParam("ID_WARTATRM"));
			if(ID_WARTATRM.equals("") | ID_WARTATRM==null)
			{
				//ID_WARTATRM = ID_WARTATRMCHECK;
				//myLogger.info(" NEW ID_WARTATRM ::::::::: "+ID_WARTATRM);
			}
			//myLogger.info(" TABKODCHECK : "+TABKODCHECK+" IDWARTAFRMLIST : "+IDWARTAFRMLIST+" ID_WARTATRMCHECK"+ID_WARTATRMCHECK+" ID_WARTAWUJUDCHECK : "+ID_WARTAWUJUDCHECK);
			
			if(command.equals("close"))
			{		
				if(commandFrom.equals("list"))
				{
					skrin_name = "app/pdt/trm/row.jsp";
				}
				else
				{					
					this.context.put("ID_WARTATRM", ID_WARTATRM);
					this.context.put("flag_reset_list","yes");
					skrin_name = "app/pdt/trm/blank_tr.jsp";	
				}							
			}
			else  if(command.equals("add") || command.equals("view") || command.equals("edit") || command.equals("save"))
			{
				skrin_name = "app/pdt/trm/edit.jsp";				
			}
			/*
			else  if(command.equals("viewWarta") || command.equals("save"))
			{		
				if(FlagFrom.equals("T"))
				{
					mode = "view";
				}
				this.context.put("flag_reset_list",getParam("flag_reset_list"));
				this.context.put("TABKOD", getParam("TABKOD"));
				this.context.put("ID_WARTATRMCHECK", getParam("ID_WARTATRMCHECK"));
				skrin_name = "app/pdt/trm/edit_TRM.jsp";				
			}		
			*/
			
			
			
			
			Db db = null;
			try {
				db = new Db();				
				if(command.equals("save"))
				{
					
					String TEMPID_WARTATRM = getParam("TEMPID_WARTATRM");
					myLogger.info(" saveWarta TEMPID_WARTATRM : "+TEMPID_WARTATRM);
					ID_WARTATRM = saveWarta(session,ID_WARTATRM,command,TEMPID_WARTATRM,db);
					/*
					if(IDWARTAFRMLIST.equals(""))
					{
						IDWARTAFRMLIST = ID_WARTATRM;
					}
					*/
					this.context.put("flagUpload", getParam("flagUpload"));
				}
				view = view(session, ID_WARTATRM,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);//view dalam
				
				String ID_WARTATRMMST = getIDWARTAASAL(session,ID_WARTATRM,db);
				if(!ID_WARTATRMMST.equals("") && ID_WARTATRMINDUK.equals("") && JENISSUB.equals(""))
				{
					viewWartaAsal = view(session, ID_WARTATRMMST,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
					this.context.put("viewWartaAsal", viewWartaAsal);
				}
				else
				{
					this.context.put("viewWartaAsal", "");
				}
				
				/*
				if(ID_WARTATRMINDUK.equals(""))
				{
					
				}
				*/
				
				/*
				if(ID_WARTATRMCHECK.equals("") && !ID_WARTAWUJUDCHECK.equals("") && TABKODCHECK.equals("B"))
				{
					//untuk kes batal, kena salin dlu maklumat warta wujud asal
					viewWartaWujud_edit = view(session, ID_WARTAWUJUDCHECK,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);					
				}
				
				if(TABKODCHECK.equals("B") || TABKODCHECK.equals("G"))
				{
					viewWartaWujud = view(session, ID_WARTAWUJUDCHECK,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);	
				}
				*/
				
				listNegeri = listNegeri(session,db);
				
				/*
				Map viewForList = null;
				if(!((String)viewTRM.get("ID_WARTATRM")).equals(""))
				{
					viewForList = viewTRM;
				}
				else
				{
					if(viewWartaWujud_edit!=null)
					{
						viewForList = viewWartaWujud_edit;
					}
				}
				*/

				//if(viewForList!=null)
				//{
				//String id_negeri = (String)viewForList.get("ID_NEGERI");
				
				
				
				/*
				if(!ID_WARTATRMINDUK.equals("") && JENISSUB.equals("B") && ID_WARTATRM.equals(""))
				{
					viewInduk = view(session, ID_WARTATRMINDUK,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
				}
				else
				{
					viewInduk = view;
				}
				*/	
				
				/*
					String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			String JENISSUB = getParam("JENISSUB");			;
			String ID_WARTATRM = getParam("ID_WARTATRM");
				*/
				
				Map getInduk = null;
				String ID_WARTATRMMST_INDUK = "";
				if(!ID_WARTATRMINDUK.equals(""))
				{
					viewInduk = view(session, ID_WARTATRMINDUK,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
					this.context.put("viewInduk", viewInduk);
					
					if(!ID_WARTATRM.equals(""))
					{
						getInduk = view;
					}
					else
					{
						if(JENISSUB.equals("B"))
						{
							getInduk = viewInduk;
						}
						else if(JENISSUB.equals("G"))
						{
							getInduk = null;
						}
					}
				}				
				else
				{
					ID_WARTATRMMST_INDUK = getIDWARTAASAL(session,ID_WARTATRM,db);
					if(!ID_WARTATRMMST_INDUK.equals(""))
					{
						viewInduk = view(session,ID_WARTATRMMST_INDUK,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
						this.context.put("viewInduk", viewInduk);
					}
					else
					{
						this.context.put("viewInduk", "");
					}
					
					
					if(!ID_WARTATRM.equals(""))
					{
						getInduk = view;
					}
					else
					{
						getInduk = null;
					}
				}
				myLogger.info("ID_WARTATRMINDUK : "+ID_WARTATRMINDUK+" ID_WARTATRMMST_INDUK : "+ID_WARTATRMMST_INDUK);
					
				if(getInduk!=null)
				{
					String id_negeri = (String)getInduk.get("ID_NEGERI");
					if(!id_negeri.equals(""))
					{
						listDaerah = listDaerah(session,id_negeri,db);
					}
					//String id_daerah = (String)viewForList.get("ID_DAERAH");
					String id_daerah = (String)getInduk.get("ID_DAERAH");
					if(!id_daerah.equals(""))
					{
						listMukim = listMukim(session,id_daerah,db);
					}
				}
				
				
			}
			finally {
				if (db != null)
					db.close();
			}
			
			this.context.put("view", view);
			//this.context.put("viewTRM", viewTRM);
			//this.context.put("viewWartaWujud", viewWartaWujud);	
			//this.context.put("viewWartaWujud_edit", viewWartaWujud_edit);			
			this.context.put("listNegeri",listNegeri);
			this.context.put("listDaerah",listDaerah);
			this.context.put("listMukim",listMukim);
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("mode",mode);
			this.context.put("FlagFrom",FlagFrom);
			this.context.put("ID_WARTATRM",ID_WARTATRM);			
			//this.context.put("TABKODCHECK",TABKODCHECK);
			//this.context.put("ID_WARTATRMCHECK",ID_WARTATRMCHECK);
			//this.context.put("ID_TRMUTAMACHECK",ID_TRMUTAMACHECK);
			//this.context.put("IDWARTAFRMLIST",IDWARTAFRMLIST);
			this.context.put("ID_WARTATRMASAL",ID_WARTATRMASAL);
			this.context.put("setJENISWARTA",setJENISWARTA);
			//this.context.put("commandFrom",commandFrom);
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			this.context.put("JENISSUB",JENISSUB);
			//this.context.put("viewInduk",viewInduk);
			//this.context.put("viewWartaAsal", viewWartaAsal);
				
						
		}		
		else if(command.equals("showCARIAN"))
		{
			this.context.put("FLAG_CARIAN", getParam("FLAG_CARIAN"));
			this.context.put("OPENCLOSE_CARIAN",getParam("OPENCLOSE_CARIAN"));
			
			Db db = null;
			try {
				db = new Db();
				listNegeri = listNegeri(session,db);
				/*
				listJenisAduan = listJenisAduan(session,db);
				listJenisSumberPengadu = listJenisSumber(session,db);
				listJenisSumberBahagian = listJenisSumber(session,db,"");
				listStatus = listStatus(session,db);
				listJenisTindakan = listJenisTindakan(session,db);
				*/
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("listNegeri",listNegeri);
			this.context.put("listDaerah",listDaerah);
			this.context.put("listMukim",listMukim);
			/*
			this.context.put("listNegeriBahagian",listNegeriBahagian);
			this.context.put("listJenisAduan",listJenisAduan);
			this.context.put("listKategoriAduan",listKategoriAduan);
			this.context.put("listJenisSumberPengadu",listJenisSumberPengadu);
			this.context.put("listJenisSumberBahagian",listJenisSumberBahagian);
			this.context.put("listBahagian",listBahagian);
			this.context.put("listStatus",listStatus);
			this.context.put("listJenisTindakan",listJenisTindakan);
			*/
			
			skrin_name = "app/pdt/trm/skrinCarian.jsp";
		}
		else if(command.equals("saveLampiran"))
		{						
			String ID_WARTATRM = getParam("ID_WARTATRM");
			String divLampiran = getParam("divLampiran");
			String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
			this.context.put("ID_WARTATRMINDUK",ID_WARTATRMINDUK);
			String JENISSUB = getParam("JENISSUB");
			this.context.put("JENISSUB",JENISSUB);
			String type = getParam("type");
			String mode = getParam("mode");
			String field_name = getParam("field_name");
			//myLogger.info(" getParam(TEMPID_WARTATRM) : "+"(TEMPID_WARTATRM_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM+")");
			String TEMPID_WARTATRM = getParam("TEMPID_WARTATRM");
			
				
			
			Db db = null;
			try {
				db = new Db();
				/*
				if(ID_WARTATRM.equals(""))
				{
					if(TEMPID_WARTATRM.equals(""))
					{
						TEMPID_WARTATRM = DB.getNextID(db, "TBLPDTWARTATRM_SEQ")+"";
					} 
				}
				*/
				myLogger.info("TEMPID_WARTATRM BEFORE SAVE : "+TEMPID_WARTATRM);
				TEMPID_WARTATRM = saveLampiran(session,ID_WARTATRM,type,field_name,type,TEMPID_WARTATRM,db);
				myLogger.info("TEMPID_WARTATRM AFTER SAVE : "+TEMPID_WARTATRM);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_WARTATRM_AFTERUPLOAD",ID_WARTATRM);
			this.context.put("mode",mode);
			this.context.put("type",type);
			this.context.put("divLampiran",divLampiran);
			this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");
			this.context.put("TEMPID_WARTATRM",TEMPID_WARTATRM);	
			
			skrin_name = "app/pdt/trm/index.jsp";			
		}
		/*
		else if(command.equals("showLampiran") || command.equals("deleteLampiran"))
		{
			String ID_WARTATRM = getParam("ID_WARTATRM");
			String ID_STATUS = getParam("ID_STATUS");
			String ID_PENGADU = getParam("ID_PENGADU");
					
			if(command.equals("deleteLampiran"))
			{
				//delete function
				String ID_WARTATRMLAMPIRAN = getParam("ID_WARTATRMLAMPIRAN");
				String NAMA_LAMPIRAN = getParam("NAMA_LAMPIRAN");
				deleteLampiran(session,ID_WARTATRMLAMPIRAN,NAMA_LAMPIRAN);
			}			
			listLampiran = listLampiran(session, ID_WARTATRM, null);
			this.context.put("listLampiran", listLampiran);
			this.context.put("ID_WARTATRM", ID_WARTATRM);
			this.context.put("ID_STATUS", ID_STATUS);
			this.context.put("ID_PENGADU", ID_PENGADU);
			this.context.put("ID_PENGADU", ID_PENGADU);
			this.context.put("mode",getParam("mode"));
			skrin_name = "app/pdt/trm/SenaraiLampiran.jsp";				
		}
		*/
		/*
			ResourceBundle rb_lang = null;
			Enumeration bundleKeys = null;	
			if(selectedLanguage.equals("ENGLISH"))
      		{
      			rb_lang = ResourceBundle.getBundle("eng_lang");      						
      		}
      		else
      		{
      			rb_lang = ResourceBundle.getBundle("malay_lang");
      		}
      		
      		if(rb_lang!=null)
      		{
      			bundleKeys = rb_lang.getKeys();
      			while (bundleKeys.hasMoreElements()) {
      			    String key = (String)bundleKeys.nextElement();
      			    String value = rb_lang.getString(key);
      			    //myLogger.info("key : "+key+" value : "+value);		
      			    context.put(key,value);			   
      			}
      		}
		*/
		
		
		myLogger.info(" TRM Public - VM :"+skrin_name);
		return skrin_name;
	}
	
	public void setupPageDefaultPut()
	{
		this.context.put("page_mula", "");
		this.context.put("page", "");
		this.context.put("itemsPerPage", "");
		this.context.put("totalPages","");
		this.context.put("startNumber", "");
		this.context.put("isFirstPage", "");
		this.context.put("isLastPage", "");
		this.context.put("scrolPosition", "");
		this.context.put("namaList", "");
		this.context.put("command", "");
		this.context.put("div", "");
		this.context.put("totalRecords", "");
	}
	
	public void defaultPut()
	{
		this.context.put("FLAG_NOTIFIKASI", "");
		this.context.put("BIL", "");
		this.context.put("rowCss", "");		
		this.context.put("FLAG_CARIAN", "");
		this.context.put("listNegeri","");
		this.context.put("listDaerah","");	
		this.context.put("listMukim","");		
		this.context.put("ID_WARTATRM","");
		this.context.put("COOR_UPLOAD","");
		this.context.put("after_uploadLampiran","");	
		//this.context.put("TABKODCHECK","");
		//this.context.put("TABKOD","");
		//this.context.put("ID_WARTATRMCHECK","");
		//this.context.put("ID_TRMUTAMACHECK","");
		//this.context.put("IDWARTAFRMLIST","");
		this.context.put("flag_reset_list","");		
		//this.context.put("viewWartaWujud", "");
		//this.context.put("viewWartaWujud_edit", "");		
		this.context.put("viewTRM", "");		
		this.context.put("commandFrom","");
		this.context.put("listWARTATRMHISTORY","");
		this.context.put("ID_WARTATRMASAL","");
		this.context.put("setJENISWARTA","");
		this.context.put("FLAG_DEFAULT_HISTORY", "");
		this.context.put("ID_WARTATRMINDUK", "");	
		this.context.put("JENISSUB","");
		this.context.put("viewInduk","");
		this.context.put("TEMPID_WARTATRM","");
		this.context.put("LOCATION","");
		this.context.put("wartaDuplicate", "");
		this.context.put("viewWartaAsal", "");
		this.context.put("AFTERDELETE","");
		this.context.put("listREPORTTAHUNNEGERI","");		
		this.context.put("listREPORTNEGERI","");
		this.context.put("modeReport","");
		this.context.put("listTahun","");
		
	}
	
	public String saveWarta(HttpSession session,String ID_WARTATRM,String command,String TEMPID_WARTATRM,Db db) throws Exception {
		//Connection conn = null;
		Db db1 = null;
		String sql = "";
		long id = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		/*
		String IDWARTAFRMLIST = getParam("IDWARTAFRMLIST");
		String TABKODCHECK = getParam("TABKODCHECK"+IDWARTAFRMLIST);
		String ID_WARTATRMCHECK = getParam("ID_WARTATRMCHECK"+IDWARTAFRMLIST);
		String ID_TRMUTAMACHECK = getParam("ID_TRMUTAMACHECK"+IDWARTAFRMLIST);		
		myLogger.info("SAVE - IDWARTAFRMLIST : "+IDWARTAFRMLIST+" TABKODCHECK : "+TABKODCHECK+" ID_WARTATRMCHECK : "+ID_WARTATRMCHECK);
		*/
		
		String ID_WARTATRMINDUK = getParam("ID_WARTATRMINDUK");
		String JENISSUB = getParam("JENISSUB");			
		String ID_NEGERI = getParam("ID_NEGERI"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String ID_DAERAH = getParam("ID_DAERAH"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String ID_MUKIM = getParam("ID_MUKIM"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String NO_WARTA = getParam("NO_WARTA"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String TARIKH_WARTA = getParam("TARIKH_WARTA"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String KAWASAN = getParam("KAWASAN"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String NO_PELAN = getParam("NO_PELAN"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String LUAS = getParam("LUAS"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);			
		String LUASASAL = getParam("LUASASAL"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String LUAS_BEFORE_EDIT = getParam("LUAS_BEFORE_EDIT"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);			
		String FLAG_STATUSWARTA = getParam("FLAG_STATUSWARTA"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String FLAG_JENISWARTA = getParam("FLAG_JENISWARTA"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		String ID_WARTATRMASAL = getParam("ID_WARTATRMASAL"+"_"+JENISSUB+ID_WARTATRMINDUK+ID_WARTATRM);
		
		
		//21.07.2017
		if(!TARIKH_WARTA.equals(""))
		{
			TARIKH_WARTA = "to_date('" +TARIKH_WARTA+ "','dd/MM/yyyy')";
		}
		else
		{
			TARIKH_WARTA = "''";
		}
		
		//open history
		Map viewBaru = null;
		Map viewAsal = null;
		Map viewBaruInduk = null;
		Map viewAsalInduk = null;
		String aktiviti = "UPDATE";
		if(ID_WARTATRM.equals(""))
		{
			if(!TEMPID_WARTATRM.equals(""))
			{
				aktiviti = "UPDATE";
			}
			else
			{
				aktiviti = "INSERT";
			}
		}			
		//close history
		
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			
			if(!TEMPID_WARTATRM.equals(""))
			{
				viewAsal = view(session, ID_WARTATRM,"","","",TEMPID_WARTATRM,db1);	
			}
			else
			{
				viewAsal = view(session, ID_WARTATRM,"","","",db1);									
			}
			
			
			if(!ID_WARTATRMINDUK.equals("") || !ID_WARTATRMASAL.equals(""))
			{
				if(!ID_WARTATRMINDUK.equals(""))
				{
					viewAsalInduk = view(session, ID_WARTATRMINDUK,"","","",db1);
				}
				else
				{
					viewAsalInduk = view(session, ID_WARTATRMASAL,"","","",db1);
				}
			}
			
			
			myLogger.info(" ID_WARTATRMASAL : "+ID_WARTATRMASAL+" ID_WARTATRMINDUK : "+ID_WARTATRMINDUK);
			myLogger.info("***viewAsalInduk : "+viewAsalInduk);
			myLogger.info("***ASAL NO WARTA : "+(String)viewAsal.get("NO_WARTA"));
			//conn = db1.getConnection();
			//conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			
			
			double newLuasAsal = 0.0000;
			double newLuas = 0.0000;
			boolean flag_update_luas_induk = false;			
			if(!ID_WARTATRMINDUK.equals("") || !ID_WARTATRMASAL.equals(""))
			{
				if(FLAG_JENISWARTA.equals("B") || FLAG_JENISWARTA.equals("G"))
				{
					if(LUAS_BEFORE_EDIT.equals(""))
					{
						newLuas = Double.parseDouble(LUAS) - 0;
					}
					else
					{
						newLuas = Double.parseDouble(LUAS) - Double.parseDouble(LUAS_BEFORE_EDIT);
						myLogger.info(" luas dalam field before edit : "+Double.parseDouble(LUAS_BEFORE_EDIT));
					}
					myLogger.info(" luas dalam field : "+Double.parseDouble(LUAS));					
					myLogger.info(" newLuas : "+newLuas);			
					
					if(FLAG_JENISWARTA.equals("B"))
					{
						newLuasAsal = Double.parseDouble(LUASASAL) - newLuas;
					}
					else if(FLAG_JENISWARTA.equals("G"))
					{
						newLuasAsal = Double.parseDouble(LUASASAL) + newLuas;
					}
					
										
				}
				flag_update_luas_induk = true;
			}	
			myLogger.info(" new_LuasAsal : "+newLuasAsal);
			
			
			if(!ID_WARTATRM.equals(""))
			{
				r.update("ID_WARTATRM", ID_WARTATRM);
			}			
			else if(!TEMPID_WARTATRM.equals(""))
			{	
				r.update("ID_WARTATRM", TEMPID_WARTATRM);
			}
			else
			{
				id = DB.getNextID(db1, "TBLPDTWARTATRM_SEQ");
				r.add("ID_WARTATRM", id);
			}
			
			
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_DAERAH", ID_DAERAH);
			r.add("ID_MUKIM", ID_MUKIM);
			r.add("NO_WARTA", NO_WARTA);
			//21.07.2017
			r.add("TARIKH_WARTA", r.unquote(TARIKH_WARTA));
			r.add("KAWASAN", KAWASAN);
			r.add("NO_PELAN", NO_PELAN);
			r.add("LUAS", LUAS);
			r.add("FLAG_STATUSWARTA", FLAG_STATUSWARTA);
			r.add("FLAG_JENISWARTA", FLAG_JENISWARTA);
			
			if(!ID_WARTATRM.equals("") || !TEMPID_WARTATRM.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPDTWARTATRM",db1);
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPDTWARTATRM",db1);	
			}
			myLogger.info("trm : SAVE  : "+sql);				
			stmt.executeUpdate(sql);
			
			
			
			if(ID_WARTATRM.equals("") && !ID_WARTATRMINDUK.equals("") && !JENISSUB.equals("") )
			{
				r.clear();
				r.add("ID_WARTATRMMST", ID_WARTATRMINDUK);
				if(!TEMPID_WARTATRM.equals(""))
				{
					r.add("ID_WARTATRMSUB", TEMPID_WARTATRM);
				}
				else
				{
					r.add("ID_WARTATRMSUB", id);
				}
				r.add("TYPE", JENISSUB);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));				
				sql = r.getSQLInsert("TBLPDTWARTATRMBATALGANTI",db1);	
				myLogger.info("trm : SAVE  TBLPDTWARTATRMBATALGANTI : "+sql);
				stmt.executeUpdate(sql);
			}
			
			
			
			
			//conn.commit();
			if(!ID_WARTATRM.equals("") || !TEMPID_WARTATRM.equals(""))
			{
				//hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				if(!TEMPID_WARTATRM.equals(""))
				{
					AuditTrail.logActivity(this,session,"UPD","KEMASKINI MAKLUMAT WARTA TANAH RIZAB MELAYU [ID_WARTATRM : "+TEMPID_WARTATRM+"]");
				}
				else
				{
					AuditTrail.logActivity(this,session,"UPD","KEMASKINI MAKLUMAT WARTA TANAH RIZAB MELAYU [ID_WARTATRM : "+ID_WARTATRM+"]");
				}		
			}
			else
			{
				//hantarEmel(session,id+"");
				AuditTrail.logActivity(this,session,"INS","KEMASUKKAN MAKLUMAT WARTA TANAH RIZAB MELAYU [ID_WARTATRM : "+id+"]");
				ID_WARTATRM = id+"";
			}
			
			
			
			if(!TEMPID_WARTATRM.equals(""))
			{
				ID_WARTATRM = TEMPID_WARTATRM;
			}
			//open history
			viewBaru = view(session, ID_WARTATRM,"","","",db1);
			//myLogger.info("***BARU NO WARTA : "+(String)viewBaru.get("NO_WARTA"));
			checkForHistory(session,ID_WARTATRM, viewAsal, viewBaru,aktiviti,db1);
			//close history
			
			
			
			/*
			if(flag_update_luas_induk==true)
			{
				String IDSET = "";
				if(!ID_WARTATRMINDUK.equals(""))
				{
					IDSET = ID_WARTATRMINDUK;
				}
				else if(!ID_WARTATRMASAL.equals(""))
				{
					IDSET = ID_WARTATRMASAL;
				}
								
				viewAsal = view(session, IDSET,"","","",db1);
				r.clear();
				r.update("ID_WARTATRM", IDSET);
				r.add("LUAS", newLuasAsal);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPDTWARTATRM");
				myLogger.info("trm : SAVE  flag_update_luas_induk : "+sql);
				stmt.executeUpdate(sql);
				viewBaru = view(session, IDSET,"","","",db1);
				checkForHistory(session,IDSET, viewAsal, viewBaru,"UPDATE",db1);
			}
			*/
			
			if(!ID_WARTATRMINDUK.equals("") || !ID_WARTATRMASAL.equals(""))
			{
				String IDSET = "";
				if(!ID_WARTATRMINDUK.equals(""))
				{
					IDSET = ID_WARTATRMINDUK;
				}
				else if(!ID_WARTATRMASAL.equals(""))
				{
					IDSET = ID_WARTATRMASAL;
				}				
				if(flag_update_luas_induk==true)
				{
					r.clear();
					r.update("ID_WARTATRM", IDSET);
					r.add("LUAS", newLuasAsal);
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("TBLPDTWARTATRM",db1);
					myLogger.info("trm : SAVE  flag_update_luas_induk : "+sql);
					stmt.executeUpdate(sql);
				}
				viewBaruInduk = view(session, IDSET,"","","",db1);
				myLogger.info("***viewBaruInduk : "+viewBaruInduk);
				checkForHistory(session,IDSET, viewAsalInduk, viewBaruInduk,"UPDATE",db1);				
			}
			
			
			
		} /*
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.get1Message());
	    	}
	    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
		} */
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return ID_WARTATRM;
	}
	
	
	
	public String saveWartaUtama(HttpSession session,String ID_UTAMA,
			String ID_WARTA_WUJUD,String ID_WARTA_BATAL,String ID_WARTA_GANTI,Db db) throws Exception {
		//Connection conn = null;
		Db db1 = null;
		String sql = "";
		long id = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			//conn = db1.getConnection();
			//conn.setAutoCommit(false);
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if(!ID_UTAMA.equals(""))
			{
				r.update("ID_TRMUTAMA", ID_UTAMA);
			}
			else
			{
				id = DB.getNextID(db1, "TBLPDTTRMUTAMA_SEQ");
				r.add("ID_TRMUTAMA", id);
			}
			
			if(!ID_WARTA_WUJUD.equals(""))
			{
				r.add("ID_WARTA_WUJUD", ID_WARTA_WUJUD);
			}
			if(!ID_WARTA_BATAL.equals(""))
			{
				r.add("ID_WARTA_BATAL", ID_WARTA_BATAL);
			}
			if(!ID_WARTA_GANTI.equals(""))
			{
				r.add("ID_WARTA_GANTI", ID_WARTA_GANTI);
			}
			
			if(!ID_UTAMA.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPDTTRMUTAMA",db1);
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPDTTRMUTAMA",db1);	
			}
			myLogger.info("trm : SAVE UTAMA : "+sql);				
			stmt.executeUpdate(sql);
			//conn.commit();
			if(!ID_UTAMA.equals(""))
			{
				//hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				AuditTrail.logActivity(this,session,"UPD","TBLPDTTRMUTAMA [ID_UTAMA : "+ID_UTAMA+"] Updated");
				
			}
			else
			{
				//hantarEmel(session,id+"");
				AuditTrail.logActivity(this,session,"INS","TBLPDTTRMUTAMA [ID_UTAMA : "+id+"] Inserted");
				ID_UTAMA = id+"";
			}
			
			
			
			
		} /*
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
		}*/
		catch (Exception re) {
			throw re;
		}finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return ID_UTAMA;
	}
	
	
	public Map view(HttpSession session, String ID_WARTATRM,String rowCss,String bil,String selectedLanguage,Db db) throws Exception {
		return view(session, ID_WARTATRM,rowCss,bil,selectedLanguage,"",db);
	}
	
	public Map view(HttpSession session, String ID_WARTATRM,String rowCss,String bil,String selectedLanguage,String TEMPID_WARTATRM,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			
			stmt = db1.getStatement();			
			Map h = Collections.synchronizedMap(new HashMap());		
			if(!ID_WARTATRM.equals("") || !TEMPID_WARTATRM.equals(""))
			{
				//bila ada data				
				if(!TEMPID_WARTATRM.equals(""))
				{
					sql = sqlTRM(session,"","","",TEMPID_WARTATRM);
					sql += "  AND T.ID_WARTATRM = '"+TEMPID_WARTATRM+"' ";
				}
				else
				{
					sql = sqlTRM(session,"","","","");
					sql += "  AND T.ID_WARTATRM = '"+ID_WARTATRM+"' ";
				}
				
				myLogger.info(" SQL VIEW TRM :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {	
					h = getHashMapTRM(session,rs,rowCss,bil,selectedLanguage,db1);					
				}	
			}
			else
			{
				//bila 1st time insert, kosongkan object
				h = getHashMapTRM(session,null,rowCss,bil,selectedLanguage,db1);
			}
			return h;
		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	public Hashtable getSumber(HttpSession session, String ID_SUMBERADUAN, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			Hashtable h;
			h = new Hashtable();			
			sql = " SELECT ID_SUMBERADUAN, KOD_SUMBER, NAMA_SUMBER, NAMA_SUMBER_ENG FROM TBLRUJSUMBERESADUAN WHERE ID_SUMBERADUAN = '"+ID_SUMBERADUAN+"'  ORDER BY KOD_SUMBER ";
				myLogger.info(" getSumber :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					h.put("ID_SUMBERADUAN",rs.getString("ID_SUMBERADUAN") == null ? "" : rs.getString("ID_SUMBERADUAN"));
					h.put("KOD_SUMBER",rs.getString("KOD_SUMBER") == null ? "" : rs.getString("KOD_SUMBER"));
					String NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER") == null ? "" : rs.getString("NAMA_SUMBER"));
					if(selectedLanguage.equals("ENGLISH"))
					{
						NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER_ENG") == null ? "" : rs.getString("NAMA_SUMBER_ENG"));
					}
					h.put("NAMA_SUMBER",NAMA_SUMBER);					
				}			
			return h;
		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	public Hashtable getUserOnline(HttpSession session, String USER_ID, Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			Hashtable h;
			h = new Hashtable();			
			sql = " SELECT UPPER(U.USER_NAME) AS FULLNAME, UO.EMEL, UO.NO_HP FROM USERS U, USERS_ONLINE UO "+
					" WHERE U.USER_ID = UO.USER_ID AND U.USER_ID = '"+USER_ID+"'  ";
				myLogger.info(" getUserOnline :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					h.put("USER_ID",USER_ID);
					h.put("FULLNAME",rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME"));
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));					
				}
			
			return h;
		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
	}
	
	
	public String getUserFullName(String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String fulname="";
			sql = " SELECT UPPER(USER_NAME) AS FULLNAME FROM USERS WHERE USER_ID = '"+USER_ID+"'";				
				myLogger.info(" ADUAN : getUserFullName :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					fulname = (rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME"));
					
				}
			
			return fulname;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getUnitName(HttpSession session, String ID_UNIT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String fulname="";
			sql = " SELECT UPPER(NAMA_PEJABAT) AS NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = '"+ID_UNIT+"'";				
				myLogger.info(" OT : getUnitName :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					fulname = (rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					
				}
			
			return fulname;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String sqlTRM(HttpSession session,String ID_WARTATRMINDUK,String JENISSUB, String command,String TEMPID_WARTATRM)
	{
		/*
		String sql = " SELECT U.ID_TRMUTAMA,T.ID_WARTATRM, T.FLAG_JENISWARTA, "+
				" (CASE "+
				" WHEN T.FLAG_JENISWARTA = 'W' "+
				" THEN 'PEWUJUDAN' "+
				" WHEN T.FLAG_JENISWARTA = 'B' "+
				" THEN 'PEMBATALAN' "+
				" ELSE '' "+
				" END "+
				" ) AS JENISWARTA, "+
				" T.FLAG_STATUSWARTA, "+
				" (CASE "+
				" WHEN T.FLAG_STATUSWARTA = 'K' "+
				" THEN 'KUATKUASA' "+
				" WHEN T.FLAG_STATUSWARTA = 'T' "+
				" THEN 'TIDAK KUATKUASA' "+
				" ELSE '' "+
				" END "+
				" ) AS STATUSWARTA, "+
				" T.NO_WARTA, TO_CHAR (T.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "+
				" T.NAMA_FAIL_WARTA, T.JENIS_MIME_WARTA, T.CONTENT_WARTA, T.NO_PELAN, "+
				" T.KAWASAN, T.NAMA_FAIL_PELAN, T.JENIS_MIME_PELAN, T.CONTENT_PELAN, "+
				" T.ID_NEGERI, T.ID_DAERAH, T.ID_MUKIM, "+
				" TO_CHAR (T.LUAS, '99999999999990.9999') AS LUAS, "+
				" UPPER (N.NAMA_NEGERI) AS NEGERI, TO_CHAR (D.NAMA_DAERAH) AS DAERAH, "+
				" TO_CHAR (M.NAMA_MUKIM) AS MUKIM, "+
				" U.ID_WARTA_WUJUD, U.ID_WARTA_BATAL, U.ID_WARTA_GANTI, "+
				" (CASE WHEN GANTI.ID_WARTATRM IS NOT NULL AND T.FLAG_JENISWARTA = 'W' THEN GANTI.NO_WARTA ELSE '' END) AS NO_WARTA_GANTI, "+
				" (CASE WHEN BATAL.ID_WARTATRM IS NOT NULL AND T.FLAG_JENISWARTA = 'B' THEN BATAL.NO_WARTA ELSE '' END) AS NO_WARTA_BATAL "+
				" FROM TBLPDTWARTATRM T, " +
				" TBLPDTTRMUTAMA GANTIUTAMA, "+
				" TBLPDTTRMUTAMA BATALUTAMA, "+
				" TBLPDTWARTATRM GANTI, "+
				" TBLPDTWARTATRM BATAL, TBLPDTTRMUTAMA U,TBLRUJNEGERI N,TBLRUJMUKIM M,TBLRUJDAERAH D "+
				" WHERE ((T.ID_WARTATRM = U.ID_WARTA_WUJUD) OR (T.ID_WARTATRM = U.ID_WARTA_BATAL)) "+
				" AND T.ID_NEGERI = N.ID_NEGERI AND T.ID_DAERAH = D.ID_DAERAH AND T.ID_MUKIM = M.ID_MUKIM" +
				" AND T.ID_WARTATRM = GANTIUTAMA.ID_WARTA_GANTI(+) AND GANTIUTAMA.ID_WARTA_WUJUD = GANTI.ID_WARTATRM(+) "+
				" AND T.ID_WARTATRM = BATALUTAMA.ID_WARTA_BATAL(+) AND BATALUTAMA.ID_WARTA_WUJUD = BATAL.ID_WARTATRM(+) ";
				*/
		
		String sql = " SELECT T.ID_WARTATRM, T.FLAG_JENISWARTA, "+
				" (CASE "+
				" WHEN T.FLAG_JENISWARTA = 'W' "+
				" THEN 'PEWUJUDAN' "+
				" WHEN T.FLAG_JENISWARTA = 'B' "+
				" THEN 'PEMBATALAN' "+
				" WHEN T.FLAG_JENISWARTA = 'G' "+
				" THEN 'PENGGANTIAN' "+
				" ELSE '' "+
				" END "+
				" ) AS JENISWARTA, "+
				" T.FLAG_STATUSWARTA, "+
				" (CASE "+
				" WHEN T.FLAG_STATUSWARTA = 'K' "+
				" THEN 'KUATKUASA' "+
				" WHEN T.FLAG_STATUSWARTA = 'T' "+
				" THEN 'TIDAK KUATKUASA' "+
				" WHEN T.FLAG_STATUSWARTA = 'X' "+
				" THEN 'TIDAK DIKETAHUI' "+
				" ELSE '' "+
				" END "+
				" ) AS STATUSWARTA, "+
				" T.NO_WARTA, WARTAASAL.NO_WARTA AS NO_WARTA_ASAL, TO_CHAR (T.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, "+
				" T.NAMA_FAIL_WARTA, T.JENIS_MIME_WARTA, T.CONTENT_WARTA, T.NO_PELAN, "+
				" T.KAWASAN, T.NAMA_FAIL_PELAN, T.JENIS_MIME_PELAN, T.CONTENT_PELAN, "+
				" T.ID_NEGERI, T.ID_DAERAH, T.ID_MUKIM, "+
				" TO_CHAR (T.LUAS, '99999999999990.9999') AS LUAS," +
				" TO_CHAR (T.LUAS, '9,999,999,990.9999') AS LUAS_DISPLAY," +
				" TO_CHAR ((T.LUAS - NVL(VIEWBG.TOTAL_GANTI,0) + NVL(VIEWBG.TOTAL_BATAL,0)), '99999999999990.9999') AS LUAS_ASAL, "+
				" TO_CHAR ((T.LUAS - NVL(VIEWBG.TOTAL_GANTI,0) + NVL(VIEWBG.TOTAL_BATAL,0)), '9,999,999,990.9999') AS LUAS_ASAL_DISPLAY, "+
				" TO_CHAR (VIEWBG.TOTAL_BATAL, '99999999999990.9999') AS LUAS_BATAL, " +
				" TO_CHAR (VIEWBG.TOTAL_BATAL, '9,999,999,990.9999') AS LUAS_BATAL_DISPLAY, " +
				" TO_CHAR (VIEWBG.TOTAL_GANTI, '99999999999990.9999') AS LUAS_GANTI, "+
				" TO_CHAR (VIEWBG.TOTAL_GANTI, '9,999,999,990.9999') AS LUAS_GANTI_DISPLAY, "+
				" TO_CHAR (VIEWBG.KOSONG, '99999999999990.9999') AS KOSONG, "+
				" TO_CHAR (VIEWBG.KOSONG, '9,999,999,990.9999') AS KOSONG_DISPLAY, "+
				" UPPER (N.NAMA_NEGERI) AS NEGERI, TO_CHAR (D.NAMA_DAERAH) AS DAERAH, "+
				" TO_CHAR (M.NAMA_MUKIM) AS MUKIM "+
				" FROM TBLPDTWARTATRM T, TBLRUJNEGERI N, TBLRUJMUKIM M, TBLRUJDAERAH D ";
		
		
			sql += " ,(SELECT TRMBG.ID_WARTATRMMST, "+
				" NVL(SUM(CASE WHEN TRMBG.TYPE = 'B' THEN NVL(WSUB.LUAS,0) END),0) AS TOTAL_BATAL, "+
				" NVL(SUM(CASE WHEN TRMBG.TYPE = 'G' THEN NVL(WSUB.LUAS,0) END),0) AS TOTAL_GANTI," +
				" (CASE WHEN (NVL(SUM(CASE WHEN TRMBG.TYPE = 'B' THEN NVL(WSUB.LUAS,0) END),0) - NVL(SUM(CASE WHEN TRMBG.TYPE = 'G' THEN NVL(WSUB.LUAS,0) END),0)) < 0 THEN 0 " +
				" ELSE NVL(SUM(CASE WHEN TRMBG.TYPE = 'B' THEN NVL(WSUB.LUAS,0) END),0) - NVL(SUM(CASE WHEN TRMBG.TYPE = 'G' THEN NVL(WSUB.LUAS,0) END),0) END) AS KOSONG "+
				" FROM TBLPDTWARTATRMBATALGANTI TRMBG, TBLPDTWARTATRM WSUB, TBLPDTWARTATRM WMAIN "+
				" WHERE TRMBG.ID_WARTATRMMST = WMAIN.ID_WARTATRM AND TRMBG.ID_WARTATRMSUB = WSUB.ID_WARTATRM "+
				" GROUP BY TRMBG.ID_WARTATRMMST) VIEWBG  ";
			sql += " ,TBLPDTWARTATRMBATALGANTI BG, TBLPDTWARTATRM WARTAASAL ";
			    				
				if(command.equals("showSenaraiBatal") || command.equals("showSenaraiGanti"))
				{
					//sql += " ,TBLPDTWARTATRMBATALGANTI BG ";
				}

				sql += " WHERE T.ID_WARTATRM IS NOT NULL ";
				sql += " AND T.ID_WARTATRM = BG.ID_WARTATRMSUB(+) AND BG.ID_WARTATRMMST = WARTAASAL.ID_WARTATRM(+) ";
				
				if(!TEMPID_WARTATRM.equals(""))
				{
					sql += " AND T.ID_NEGERI = N.ID_NEGERI(+) AND T.ID_DAERAH = D.ID_DAERAH(+) AND T.ID_MUKIM = M.ID_MUKIM(+) AND T.ID_WARTATRM = VIEWBG.ID_WARTATRMMST(+) ";
				}
				else
				{
					sql += " AND T.ID_NEGERI = N.ID_NEGERI AND T.ID_DAERAH = D.ID_DAERAH AND T.ID_MUKIM = M.ID_MUKIM  AND T.ID_WARTATRM = VIEWBG.ID_WARTATRMMST(+) ";
				}
				
				if(command.equals("showSenaraiBatal") || command.equals("showSenaraiGanti"))
				{
					sql += " AND BG.ID_WARTATRMMST = '"+ID_WARTATRMINDUK+"' AND BG.TYPE = '"+JENISSUB+"' ";
				}
				
				return sql;
	}
	
	
	public List listTRM(HttpSession session,String ID_WARTATRMINDUK,String JENISSUB, String command)throws Exception {
		
		String FLAG_CARIAN = getParam("FLAG_CARIAN");
		myLogger.info("-------- FLAG_CARIAN : "+FLAG_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		String selectedLanguage = (String) session.getAttribute("selectedLanguage");
				
		String CR_NO_WARTA = getParam("CR_NO_WARTA").trim();
		String CR_ID_DAERAH = getParam("CR_ID_DAERAH").trim();
		String CR_ID_NEGERI = getParam("CR_ID_NEGERI").trim();
		String CR_ID_MUKIM = getParam("CR_ID_MUKIM").trim();
		String CR_KAWASAN = getParam("CR_KAWASAN").trim();
		String CR_NO_PELAN = getParam("CR_NO_PELAN").trim();
		
		
		String CR_TARIKH_MULA = getParam("CR_TARIKH_MULA").trim();
		String CR_TARIKH_AKHIR = getParam("CR_TARIKH_AKHIR").trim();
		
		String CR_FLAG_JENISWARTA = getParam("CR_FLAG_JENISWARTA").trim();
		String CR_FLAG_STATUSWARTA = getParam("CR_FLAG_STATUSWARTA").trim();				
		
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			sql = sqlTRM(session,ID_WARTATRMINDUK,JENISSUB,command,"");
					
			if(FLAG_CARIAN.equals("Y"))
			{
				if(!CR_NO_WARTA.equals(""))
				{
					sql += " AND UPPER(T.NO_WARTA) LIKE '%"+CR_NO_WARTA.toUpperCase()+"%' ";
					
				}
				if(!CR_ID_NEGERI.equals(""))
				{
					sql += " AND T.ID_NEGERI = '"+CR_ID_NEGERI.toUpperCase()+"' ";
					
				}
				if(!CR_ID_DAERAH.equals(""))
				{
					sql += " AND T.ID_DAERAH = '"+CR_ID_DAERAH.toUpperCase()+"' ";
					
				}
				if(!CR_ID_MUKIM.equals(""))
				{
					sql += " AND T.ID_MUKIM = '"+CR_ID_MUKIM.toUpperCase()+"' ";
					
				}
				if(!CR_KAWASAN.equals(""))
				{
					sql += " AND UPPER(T.KAWASAN) LIKE '%"+CR_KAWASAN.toUpperCase()+"%' ";
					
				}
				if(!CR_NO_PELAN.equals(""))
				{
					sql += " AND UPPER(T.NO_PELAN) LIKE '%"+CR_NO_PELAN.toUpperCase()+"%' ";
					
				}
				if(!CR_TARIKH_MULA.equals(""))
				{
					sql += " AND T.TARIKH_WARTA >= TO_DATE('"+CR_TARIKH_MULA+"','DD/MM/YYYY') ";
				}
				if(!CR_TARIKH_AKHIR.equals(""))
				{
					sql += " AND T.TARIKH_WARTA <= TO_DATE('"+CR_TARIKH_AKHIR+"','DD/MM/YYYY') ";
				}
				
				if(!CR_FLAG_JENISWARTA.equals(""))
				{
					sql += " AND T.FLAG_JENISWARTA = '"+CR_FLAG_JENISWARTA.toUpperCase()+"' ";
					
				}
				
				if(!CR_FLAG_STATUSWARTA.equals(""))
				{
					if(CR_FLAG_STATUSWARTA.equals("G"))
					{
						sql += " AND VIEWBG.KOSONG > 0 ";
					}
					else
					{
						sql += " AND T.FLAG_STATUSWARTA = '"+CR_FLAG_STATUSWARTA.toUpperCase()+"' ";
					}					
				}
				
				this.context.put("CR_NO_WARTA",CR_NO_WARTA.toUpperCase());
				this.context.put("CR_ID_DAERAH",CR_ID_DAERAH.toUpperCase());
				this.context.put("CR_ID_NEGERI",CR_ID_NEGERI.toUpperCase());
				this.context.put("CR_ID_MUKIM",CR_ID_MUKIM.toUpperCase());
				this.context.put("CR_KAWASAN",CR_KAWASAN.toUpperCase());
				this.context.put("CR_NO_PELAN",CR_NO_PELAN.toUpperCase());
				this.context.put("CR_TARIKH_MULA",CR_TARIKH_MULA.toUpperCase());
				this.context.put("CR_TARIKH_AKHIR",CR_TARIKH_AKHIR.toUpperCase());				
				this.context.put("CR_FLAG_JENISWARTA",CR_FLAG_JENISWARTA.toUpperCase());
				this.context.put("CR_FLAG_STATUSWARTA",CR_FLAG_STATUSWARTA.toUpperCase());	
				
			}
			else
			{	
				myLogger.info("********* command : "+command);
				if(command.equals("showSenarai") || command.equals("showCARIAN"))
				{
					sql += " AND T.FLAG_JENISWARTA = 'W' ";
					this.context.put("CR_FLAG_JENISWARTA","W");
				}
				else
				{
					this.context.put("CR_FLAG_JENISWARTA","");
				}
				this.context.put("CR_NO_WARTA","");
				this.context.put("CR_ID_DAERAH","");
				this.context.put("CR_ID_NEGERI","");
				this.context.put("CR_ID_MUKIM","");
				this.context.put("CR_KAWASAN","");
				this.context.put("CR_NO_PELAN","");
				this.context.put("CR_TARIKH_MULA","");
				this.context.put("CR_TARIKH_AKHIR","");				
				this.context.put("CR_FLAG_JENISWARTA","W");
				this.context.put("CR_FLAG_STATUSWARTA","");
				
			}
			
			
			String order_by = " ORDER BY  ";
			order_by += " T.TARIKH_WARTA DESC ";
		    
			sql += order_by;
					
			myLogger.info(" SQL : LIST TRM :"+ sql);
			
			rs = stmt.executeQuery(sql);
			list = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				//h = Collections.synchronizedMap(new HashMap());
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
				list.add(getHashMapTRM(session,rs,rowCss,bil+"",selectedLanguage,db));
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return list;

	}
	
	public Map getHashMapTRM(HttpSession session, ResultSet rs,String rowCss, String bil,String selectedLanguage, Db db) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		
		Map h = Collections.synchronizedMap(new HashMap());		
		try {
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("ID_WARTATRM",rs == null ? "" : (rs.getString("ID_WARTATRM") == null ? "" : rs.getString("ID_WARTATRM").toUpperCase()));
			h.put("FLAG_JENISWARTA",rs == null ? "" : (rs.getString("FLAG_JENISWARTA") == null ? "" : rs.getString("FLAG_JENISWARTA").toUpperCase()));
			h.put("JENISWARTA",rs == null ? "" : (rs.getString("JENISWARTA") == null ? "" : rs.getString("JENISWARTA").toUpperCase()));
			h.put("FLAG_STATUSWARTA",rs == null ? "" : (rs.getString("FLAG_STATUSWARTA") == null ? "" : rs.getString("FLAG_STATUSWARTA").toUpperCase()));
			h.put("STATUSWARTA",rs == null ? "" : (rs.getString("STATUSWARTA") == null ? "" : rs.getString("STATUSWARTA").toUpperCase()));
			h.put("NO_WARTA",rs == null ? "" : (rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA").toUpperCase()));
			h.put("NO_WARTA_ASAL",rs == null ? "" : (rs.getString("NO_WARTA_ASAL") == null ? "" : rs.getString("NO_WARTA_ASAL").toUpperCase()));		
			h.put("TARIKH_WARTA",rs == null ? "" : (rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA").toUpperCase()));
			h.put("NAMA_FAIL_WARTA",rs == null ? "" : (rs.getString("NAMA_FAIL_WARTA") == null ? "" : rs.getString("NAMA_FAIL_WARTA")));
			h.put("JENIS_MIME_WARTA",rs == null ? "" : (rs.getString("JENIS_MIME_WARTA") == null ? "" : rs.getString("JENIS_MIME_WARTA")));
			h.put("CONTENT_WARTA",rs == null ? "" : (rs.getString("CONTENT_WARTA") == null ? "" : rs.getString("CONTENT_WARTA").toUpperCase()));
			h.put("NO_PELAN",rs == null ? "" : (rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN").toUpperCase()));
			h.put("KAWASAN",rs == null ? "" : (rs.getString("KAWASAN") == null ? "" : rs.getString("KAWASAN").toUpperCase()));
			h.put("NAMA_FAIL_PELAN",rs == null ? "" : (rs.getString("NAMA_FAIL_PELAN") == null ? "" : rs.getString("NAMA_FAIL_PELAN")));
			h.put("JENIS_MIME_PELAN",rs == null ? "" : (rs.getString("JENIS_MIME_PELAN") == null ? "" : rs.getString("JENIS_MIME_PELAN")));
			h.put("CONTENT_PELAN",rs == null ? "" : (rs.getString("CONTENT_PELAN") == null ? "" : rs.getString("CONTENT_PELAN").toUpperCase()));
			h.put("ID_NEGERI",rs == null ? "" : (rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase()));
			h.put("ID_DAERAH",rs == null ? "" : (rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH").toUpperCase()));
			h.put("ID_MUKIM",rs == null ? "" : (rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM").toUpperCase()));
			
			
			String LUAS = (String) (rs == null ? "0" : (rs.getString("LUAS") == null ? "0": rs.getString("LUAS").toUpperCase()));
			LUAS = LUAS.indexOf(".") < 0 ? LUAS : LUAS.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS",Double.parseDouble(LUAS.trim()));
			
			String LUAS_DISPLAY = (String) (rs == null ? "0" : (rs.getString("LUAS_DISPLAY") == null ? "0": rs.getString("LUAS_DISPLAY").toUpperCase()));
			LUAS_DISPLAY = LUAS_DISPLAY.indexOf(".") < 0 ? LUAS_DISPLAY : LUAS_DISPLAY.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_DISPLAY",LUAS_DISPLAY.trim());
			
			//h.put("LUAS",rs == null ? 0 : (rs.getString("LUAS") == null ? 0 : Double.parseDouble(rs.getString("LUAS").trim())));
			//h.put("LUAS_DISPLAY",rs == null ? "" : (rs.getString("LUAS_DISPLAY") == null ? "" : rs.getString("LUAS_DISPLAY").trim()));
			
			
			String LUAS_BATAL = (String) (rs == null ? "0" : (rs.getString("LUAS_BATAL") == null ? "0": rs.getString("LUAS_BATAL").toUpperCase()));
			LUAS_BATAL = LUAS_BATAL.indexOf(".") < 0 ? LUAS_BATAL : LUAS_BATAL.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_BATAL",Double.parseDouble(LUAS_BATAL.trim()));
			
			String LUAS_BATAL_DISPLAY = (rs == null ? "0" : (String) (rs.getString("LUAS_BATAL_DISPLAY") == null ? "0": rs.getString("LUAS_BATAL_DISPLAY").toUpperCase()));
			LUAS_BATAL_DISPLAY = LUAS_BATAL_DISPLAY.indexOf(".") < 0 ? LUAS_BATAL_DISPLAY : LUAS_BATAL_DISPLAY.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_BATAL_DISPLAY",LUAS_BATAL_DISPLAY.trim());
			
			//h.put("LUAS_BATAL",rs == null ? 0 : (rs.getString("LUAS_BATAL") == null ? 0 : Double.parseDouble(rs.getString("LUAS_BATAL").trim())));
			//h.put("LUAS_BATAL_DISPLAY",rs == null ? "" : (rs.getString("LUAS_BATAL_DISPLAY") == null ? "" : rs.getString("LUAS_BATAL_DISPLAY").trim()));
			
			
			
			String LUAS_GANTI = (String) (rs == null ? "0" : (rs.getString("LUAS_GANTI") == null ? "0": rs.getString("LUAS_GANTI").toUpperCase()));
			LUAS_GANTI = LUAS_GANTI.indexOf(".") < 0 ? LUAS_GANTI : LUAS_GANTI.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_GANTI",Double.parseDouble(LUAS_GANTI.trim()));
			
			String LUAS_GANTI_DISPLAY = (String) (rs == null ? "0" : (rs.getString("LUAS_GANTI_DISPLAY") == null ? "0": rs.getString("LUAS_GANTI_DISPLAY").toUpperCase()));
			LUAS_GANTI_DISPLAY = LUAS_GANTI_DISPLAY.indexOf(".") < 0 ? LUAS_GANTI_DISPLAY : LUAS_GANTI_DISPLAY.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_GANTI_DISPLAY",LUAS_GANTI_DISPLAY.trim());
			
			//h.put("LUAS_GANTI",rs == null ? 0 : (rs.getString("LUAS_GANTI") == null ? 0 : Double.parseDouble(rs.getString("LUAS_GANTI").trim())));
			//h.put("LUAS_GANTI_DISPLAY",rs == null ? "" : (rs.getString("LUAS_GANTI_DISPLAY") == null ? "" : rs.getString("LUAS_GANTI_DISPLAY").trim()));
			
			
			String KOSONG = (String) (rs == null ? "0" : (rs.getString("KOSONG") == null ? "0": rs.getString("KOSONG").toUpperCase()));
			KOSONG = KOSONG.indexOf(".") < 0 ? KOSONG : KOSONG.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("KOSONG",Double.parseDouble(KOSONG.trim()));
			
			String KOSONG_DISPLAY = (String) (rs == null ? "0" : (rs.getString("KOSONG_DISPLAY") == null ? "0": rs.getString("KOSONG_DISPLAY").toUpperCase()));
			KOSONG_DISPLAY = KOSONG_DISPLAY.indexOf(".") < 0 ? KOSONG_DISPLAY : KOSONG_DISPLAY.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("KOSONG_DISPLAY",KOSONG_DISPLAY.trim());
			
			//h.put("KOSONG",rs == null ? 0 : (rs.getString("KOSONG") == null ? 0 : Double.parseDouble(rs.getString("KOSONG").trim())));	
			//h.put("KOSONG_DISPLAY",rs == null ? "" : (rs.getString("KOSONG_DISPLAY") == null ? "" : rs.getString("KOSONG_DISPLAY").trim()));
			
			String LUAS_ASAL = (String) (rs == null ? "0" : (rs.getString("LUAS_ASAL") == null ? "0": rs.getString("LUAS_ASAL").toUpperCase()));
			LUAS_ASAL = LUAS_ASAL.indexOf(".") < 0 ? LUAS_ASAL : LUAS_ASAL.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_ASAL",Double.parseDouble(LUAS_ASAL.trim()));
			
			String LUAS_ASAL_DISPLAY = (rs == null ? "0" : (String) (rs.getString("LUAS_ASAL_DISPLAY") == null ? "0": rs.getString("LUAS_ASAL_DISPLAY").toUpperCase()));
			LUAS_ASAL_DISPLAY = LUAS_ASAL_DISPLAY.indexOf(".") < 0 ? LUAS_ASAL_DISPLAY : LUAS_ASAL_DISPLAY.replaceAll("0*$", "").replaceAll("\\.$", "");
			h.put("LUAS_ASAL_DISPLAY",LUAS_ASAL_DISPLAY.trim());
			
			
			h.put("NEGERI",rs == null ? "" : (rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI").toUpperCase()));
			h.put("DAERAH",rs == null ? "" : (rs.getString("DAERAH") == null ? "" : rs.getString("DAERAH").toUpperCase()));
			h.put("MUKIM",rs == null ? "" : (rs.getString("MUKIM") == null ? "" : rs.getString("MUKIM").toUpperCase()));
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h;
	}
	
	
	public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
		try {
			setupPageDefaultPut();
			String scrolPosition = getParam("scrolPosition"+command);
			myLogger.info(" ------- scrolPosition :"+scrolPosition);
			this.context.put("scrolPosition", scrolPosition);
			this.context.put("namaList", namaList);
			this.context.put("command", command);
			this.context.put("div", div);
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			int page_mula = 1;
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage"+command) == null
					|| this.context.get("itemsPerPage"+command) == "") {
				//myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
				itemsPerPage = getParam("itemsPerPage"+command) == "" ? 10
						: getParamAsInteger("itemsPerPage"+command);
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
			}
			
			if (("getNext").equals(action)) {
				page++;
			} else if (("getPrevious").equals(action)) {
				page--;
			} else if (("getPage").equals(action)) {
				page = getParamAsInteger("value");
			} else if (("doChangeItemPerPage").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage"+command);
			}
			//myLogger.info(" itemsPerPage : "+itemsPerPage);
			
			Paging2 paging = new Paging2(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1;
			this.context.put(namaList, paging.getPage(page));
			this.context.put("page_mula", new Integer(page_mula));
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
	
	
	
	public void delete(HttpSession session,String ID_WARTATRM, Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		String sql = "";
		Map viewAsalInduk = null;
		Map viewBaruInduk = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			//conn = db.getConnection();
			//conn.setAutoCommit(false);
			
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			String ID_WARTATRMMST = getIDWARTAASAL(session,ID_WARTATRM,db1);
			Map view = view(session, ID_WARTATRM,"","","",db1);
			String FLAG_JENISWARTA = (String)view.get("FLAG_JENISWARTA");
			//String LUAS = (String)view.get("LUAS");
			double luas_warta = (Double)view.get("LUAS");
			String NO_WARTA = (String)view.get("NO_WARTA");
			
			if(!ID_WARTATRMMST.equals(""))
			{
			viewAsalInduk = view(session, ID_WARTATRMMST,"","","",db1);
			//**********warta ini adalah sub			
			//jika warta gantian - tidak perlu update master			
			
			//jika warta pembatalan ke update master.. luas semasa master + luas batal	
			if(FLAG_JENISWARTA.equals("B") || FLAG_JENISWARTA.equals("G"))
			{
				double new_luas_master = 0.0;
				double current_luas_master = (Double)viewAsalInduk.get("LUAS");
				if(FLAG_JENISWARTA.equals("B"))
				{
				new_luas_master = current_luas_master + luas_warta;
				}
				else if(FLAG_JENISWARTA.equals("G"))
				{
				new_luas_master = current_luas_master - luas_warta;
				}					
				r.clear();
				r.update("ID_WARTATRM", ID_WARTATRMMST);
				r.add("LUAS", new_luas_master);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPDTWARTATRM",db1);
				myLogger.info("trm : SAVE  UDPATE LUAS AFTER DELETE : "+sql);
				stmt.executeUpdate(sql);			
			}
			
			viewBaruInduk = view(session, ID_WARTATRMMST,"","","",db1);
			checkForHistory(session,ID_WARTATRMMST, viewAsalInduk, viewBaruInduk,"UPDATE",db1);	
			}
			
			//check if id warta ni adalah master.. kena delete anak-anak.. tetapi warta batal saja			
			String delete_sub_ref = " DELETE FROM TBLPDTWARTATRMBATALGANTI M WHERE M.ID_WARTATRMBATALGANTI IN ( "+
					" SELECT G.ID_WARTATRMBATALGANTI FROM TBLPDTWARTATRMBATALGANTI G, TBLPDTWARTATRM A "+
					" WHERE G.ID_WARTATRMSUB = A.ID_WARTATRM AND G.ID_WARTATRMMST = '"+ID_WARTATRM+"' AND G.TYPE IN ('B','G') ) ";
			stmt.executeUpdate(delete_sub_ref);
			
			String delete_sub = " DELETE FROM TBLPDTWARTATRM M WHERE M.ID_WARTATRM IN ( "+
					" SELECT A.ID_WARTATRM FROM TBLPDTWARTATRMBATALGANTI G, TBLPDTWARTATRM A "+
					" WHERE G.ID_WARTATRMSUB = A.ID_WARTATRM AND G.ID_WARTATRMMST = '"+ID_WARTATRM+"' AND G.TYPE IN ('B','G') ) ";
			stmt.executeUpdate(delete_sub);
			AuditTrail.logActivity(this,session,"DEL","HAPUS WARTA PEMBATALAN TANAH RIZAB MELAYU [NO WARTA INDUK : "+NO_WARTA+"; ID_WARTATRM : "+ID_WARTATRM+"] Deleted");
			
			
			//sini baru la delete warta tu			
			r.clear();
			r.add("ID_WARTATRM",ID_WARTATRM);
			sql = r.getSQLDelete("TBLPDTWARTATRM");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","HAPUS MAKLUMAT WARTA TANAH RIZAB MELAYU [NO WARTA : "+NO_WARTA+"; ID_WARTATRM : "+ID_WARTATRM+"] Deleted");
			
			
			r.clear();
			r.add("ID_WARTATRM",ID_WARTATRM);
			sql = r.getSQLDelete("TBLSEJARAHWTRMUTAMA");
			stmt.executeUpdate(sql);
			
			String delete_sejarah_sub = " SELECT * FROM TBLSEJARAHWTRMSUB UU WHERE UU.ID_SEJARAHWTRMUTAMA IN ( "+
					" SELECT A.ID_SEJARAHWTRMUTAMA FROM TBLSEJARAHWTRMUTAMA A, TBLSEJARAHWTRMSUB B  "+
					" WHERE A.ID_WARTATRM = '"+ID_WARTATRM+"' AND A.ID_SEJARAHWTRMUTAMA = B.ID_SEJARAHWTRMUTAMA) ";
			stmt.executeUpdate(delete_sejarah_sub);
			
					
			
			/*
			
			double newLuasAsal = 0.0000;
			double newLuas = 0.0000;
			boolean flag_update_luas_induk = false;			
			if(!ID_WARTATRMINDUK.equals("") || !ID_WARTATRMASAL.equals(""))
			{
				if(FLAG_JENISWARTA.equals("B"))
				{
					if(LUAS_BEFORE_EDIT.equals(""))
					{
						newLuas = Double.parseDouble(LUAS) - 0;
					}
					else
					{
						newLuas = Double.parseDouble(LUAS) - Double.parseDouble(LUAS_BEFORE_EDIT);
						myLogger.info(" luas dalam field before edit : "+Double.parseDouble(LUAS_BEFORE_EDIT));
					}
					myLogger.info(" luas dalam field : "+Double.parseDouble(LUAS));					
					myLogger.info(" newLuas : "+newLuas);					
					newLuasAsal = Double.parseDouble(LUASASAL) - newLuas;
					flag_update_luas_induk = true;
				}
			}	
			myLogger.info(" new_LuasAsal : "+newLuasAsal);
			*/
			
			
			/*
			r.clear();
			r.add("ID_WARTATRM",ID_WARTATRM);
			sql = r.getSQLDelete("TBLADUANPUBLICNOTIFIKASI");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICNOTIFIKASI [ID_WARTATRM : "+ID_WARTATRM+"] Deleted");
			*/
			
			
			
			
			/*
			r.clear();
			r.add("ID_WARTATRM",ID_WARTATRM);
			sql = r.getSQLDelete("TBLADUANPUBLIC");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLIC [ID_WARTATRM : "+ID_WARTATRM+"] Deleted");		
			r.clear();
			r.add("ID_WARTATRM",ID_WARTATRM);
			sql = r.getSQLDelete("TBLADUANPUBLICLAMPIRAN");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICLAMPIRAN [ID_WARTATRM : "+ID_WARTATRM+"] Deleted");			
			r.clear();
			r.add("ID_WARTATRM",ID_WARTATRM);
			sql = r.getSQLDelete("TBLKRONOLOGIADUANPUBLIC");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLKRONOLOGIADUANPUBLIC [ID_WARTATRM : "+ID_WARTATRM+"] Deleted");
			*/
			//conn.commit();
		
	}
	/*
	catch (SQLException se) { 
		myLogger.error(se);
    	try {
    		conn.rollback();
    	} catch (SQLException se2) {
    		throw new Exception("Rollback error:"+se2.getMessage());
    	}
    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	}
	*/
	catch (Exception re) {
		throw re;
	}finally {
		if(db==null)
		{
			if (db1 != null)
				db1.close();
		}
	}
}
	
		
	
	public List listNegeri(HttpSession session,Db db)throws Exception {
		return listNegeri(session,db,"");
	}
	
	@SuppressWarnings("unchecked")
	public List listNegeri(HttpSession session,Db db, String ID_JENISTINDAKAN)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listNegeri = null;
		String sql = "";
		
		try {
			if(db==null)
			{
			db1 = new Db();
			}
			else
			{
				db1 = db;
			}			
			stmt = db1.getStatement();
			sql = " SELECT ID_NEGERI, KOD_NEGERI, NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI NOT IN (0,17,15,12,13) ";
			
			if(ID_JENISTINDAKAN.equals("16171"))
			{
				sql += " AND ID_NEGERI IN (16) ";
			}
			else if(ID_JENISTINDAKAN.equals("16172"))
			{
				sql += " AND ID_NEGERI NOT IN (16) ";
			}
			
			
			
		    sql += " ORDER BY KOD_NEGERI ";			
			myLogger.info(" ADUAN : SQL listNegeri :"+ sql);
			rs = stmt.executeQuery(sql);
			listNegeri = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("KOD_NEGERI",rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI").toUpperCase());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());				
				listNegeri.add(h);
			}

		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		return listNegeri;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listTahun(HttpSession session,Db db, int TAHUN_MULA, int TAHUN_AKHIR)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listTahun = null;
		String sql = "";
		ArrayList currentPilihan = new ArrayList();
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}			
			
			listTahun = Collections.synchronizedList(new ArrayList());
			Map h = null;
			
			currentPilihan = checkPilihanTahun(session, db1);
			
			for(int i = TAHUN_MULA; i <= TAHUN_AKHIR; i++)
			{
				h = Collections.synchronizedMap(new HashMap());
				
				String FLAG_WUJUD = "N";
				if (currentPilihan.contains(i+"")) {
					FLAG_WUJUD = "Y";
				}
				
				h.put("FLAG_WUJUD",FLAG_WUJUD);
				h.put("TAHUN",i);
				listTahun.add(h);				
			}
			
			

		} finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return listTahun;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listDaerah(HttpSession session,String ID_NEGERI,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listDaerah = null;
		String sql = "";
		try {
			if(db==null)
			{
			db1 = new Db();
			}
			else
			{
				db1 = db;
			}			
			stmt = db1.getStatement();
			sql = " SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH, ID_NEGERI FROM TBLRUJDAERAH WHERE KOD_DAERAH NOT IN ('0') ";
					if(!ID_NEGERI.equals(""))
					{
						sql += " AND ID_NEGERI = '"+ID_NEGERI+"'  ";
					}
			sql += " ORDER BY KOD_DAERAH ";			
			myLogger.info(" ADUAN : SQL listDaerah :"+ sql);
			rs = stmt.executeQuery(sql);
			listDaerah = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH").toUpperCase());
				h.put("KOD_DAERAH",rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH").toUpperCase());
				h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				listDaerah.add(h);
			}

		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		return listDaerah;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listMukim(HttpSession session,String ID_DAERAH,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listMukim = null;
		String sql = "";
		try {
			if(db==null)
			{
			db1 = new Db();
			}
			else
			{
				db1 = db;
			}			
			stmt = db1.getStatement();
			sql = " SELECT ID_MUKIM, KOD_MUKIM, UPPER(NAMA_MUKIM) AS NAMA_MUKIM, ID_DAERAH  "+
				  " FROM TBLRUJMUKIM WHERE KOD_MUKIM NOT IN  ('0','00')   ";
					if(!ID_DAERAH.equals(""))
					{
						sql += " AND ID_DAERAH = '"+ID_DAERAH+"'  ";
					}
			sql += " ORDER BY KOD_MUKIM ";			
			myLogger.info(" ADUAN : SQL listMukim :"+ sql);
			rs = stmt.executeQuery(sql);
			listMukim = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_MUKIM",rs.getString("KOD_MUKIM") == null ? "" : rs.getString("KOD_MUKIM").toUpperCase());
				h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH").toUpperCase());
				listMukim.add(h);
			}

		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		return listMukim;
	}
	
	
	public String getIdUnitByUserId(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String ID_PEJABATJKPTG="";
			sql = " SELECT ID_PEJABATJKPTG FROM USERS_INTERNAL WHERE USER_ID = '"+USER_ID+"' ";	
			myLogger.info(" OT : getIdUnitByUserId :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					ID_PEJABATJKPTG = (rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				}			
			return ID_PEJABATJKPTG;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	public static synchronized int getSeqNo(String tahun,Db db)
			throws DbException {

		Db db1 = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			
			conn = db1.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQNOADUANPUBLIC WHERE ");
			sb.append(" TAHUN = '" + tahun + "'");
			ResultSet rs = db1.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			
			myLogger.info("found :"+found);
			
			
			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseNo(tahun,db);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNo(tahun,db);
			}
			ResultSet rs2 = db1.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}

		return seqno;
	}
		 public static void increaseNo(String tahun,Db db) throws DbException {

				Db db1 = null;
				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE TBLRUJSEQNOADUANPUBLIC  SET ");
				sb.append("no_turutan = no_turutan + 1 ");
				sb.append(" WHERE ");
				sb.append(" TAHUN = '" + tahun + "'");
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					try {
						db1.getStatement().executeUpdate(sb.toString());
					} catch (SQLException x) {
						x.printStackTrace();
					}
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
			}
		 public static void addNo(String tahun,Db db) throws DbException {

			 
				Db db1 = null;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO TBLRUJSEQNOADUANPUBLIC (TAHUN,NO_TURUTAN)");
				sb.append(" VALUES (");
				sb.append("'" + tahun + "'");
				sb.append(",1)"); // initial value

				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					db1.getStatement().executeUpdate(sb.toString());
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
			}
		 
		 
		 public int daysBetween(Date d1, Date d2){
             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
         }
		
		
		 /*
		 public void hantarEmel(HttpSession session,String ID_MESYUARATUTAMA,String ID_MESYUARATCONTENT,String ID_MESYUARATTINDAKAN, String flagSend) throws Exception {
				
				myLogger.info("MASUK FUNCTION EMEL");	
				
				EmailProperty pro = EmailProperty.getInstance();
				EmailSender email = EmailSender.getInstance();
				email.FROM = pro.getFrom();
				email.MULTIPLE_RECIEPIENT = new String[1];
				String subject = "";
				String content = "";
				
				Hashtable maklumatMesyuarat = viewTajukUtama(session,ID_MESYUARATUTAMA);
				String TAJUK_MESYUARAT = (String)maklumatMesyuarat.get("TAJUK_MESYUARAT");
				int TAHUN_MESYUARAT = (int)maklumatMesyuarat.get("TAHUN");
				int BILANGAN_MESYUARAT = (int)maklumatMesyuarat.get("BILANGAN");
				String error_emel = "";
				
				String penutup =" <br><br><br>Sila beri maklumbalas untuk perkara diatas kepada Bahagian Pengurusan dan Perundangan Tanah. Terima kasih diatas kerjasama anda. ";
				penutup +=" <br><br>Nota : Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.";
				
				subject = " PERMOHONAN MAKLUMBALAS MINIT MESYUARAT : "+TAJUK_MESYUARAT+" "+TAHUN_MESYUARAT+"/"+BILANGAN_MESYUARAT;
				if(flagSend.equals("byIndividu") || flagSend.equals("byContent"))
				{
					
					Hashtable maklumatContent = viewSubFoler(session,ID_MESYUARATCONTENT);					
					String CONTENT_MESYUARAT = (String)maklumatContent.get("KETERANGAN");
					content = " Pihak Tuan/Puan hendaklah memberi maklumbalas tindakan untuk pekara dibawah : ";
					content +=" <br><br><br><i>'"+CONTENT_MESYUARAT+"'</i> ";
					content += penutup;
					
					if(flagSend.equals("byIndividu"))
					{
						Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
						String EMEL = (String)maklumatTindakan.get("EMEL");
						int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
						email.MULTIPLE_RECIEPIENT[0] = EMEL;
						email.SUBJECT = subject;
						email.MESSAGE = content;
						error_emel = email.sendEmail_returnEM();
						myLogger.info(" error_emel ::::::: " +error_emel);
						
						if(!error_emel.equals(""))
						{
							COUNT_EMEL = COUNT_EMEL;
						}
						else
						{
							COUNT_EMEL = COUNT_EMEL + 1;
						}
						saveTindakanEmelStatus(session,ID_MESYUARATTINDAKAN,COUNT_EMEL,error_emel);
					}
					else if(flagSend.equals("byContent"))
					{
						List listTindakanByContent = listTindakanByContent(session, ID_MESYUARATCONTENT);
						email.MULTIPLE_RECIEPIENT = new String[listTindakanByContent.size()];
						for(int i = 0; i < listTindakanByContent.size();i++)
						{
							Map m = (Map) listTindakanByContent.get(i);
							ID_MESYUARATTINDAKAN = (String) m.get("ID_MESYUARATTINDAKAN");
							
							Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
							String EMEL = (String)maklumatTindakan.get("EMEL");
							//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
							email.MULTIPLE_RECIEPIENT[i] = EMEL;
							
							myLogger.info(" error_emel ::::::: " +error_emel);							
						}
						email.SUBJECT = subject;
						email.MESSAGE = content;
						error_emel = email.sendEmail_returnEM();
						
						for(int k = 0; k < listTindakanByContent.size();k++)
						{
							Map b = (Map) listTindakanByContent.get(k);
							ID_MESYUARATTINDAKAN = (String) b.get("ID_MESYUARATTINDAKAN");							
							Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
							int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
							if(!error_emel.equals(""))
							{
								COUNT_EMEL = COUNT_EMEL;
							}
							else
							{
								COUNT_EMEL = COUNT_EMEL + 1;
							}
							saveTindakanEmelStatus(session,ID_MESYUARATTINDAKAN,COUNT_EMEL,error_emel);
						}
					}
					
					
				}
				else if(flagSend.equals("byMain"))
				{
					List listContentByMain = listContentByMain(session, ID_MESYUARATUTAMA);
					for(int j = 0; j < listContentByMain.size();j++)
					{
						Map y = (Map) listContentByMain.get(j);
						ID_MESYUARATCONTENT = (String) y.get("ID_MESYUARATCONTENT");
						Hashtable maklumatContent = viewSubFoler(session,ID_MESYUARATCONTENT);					
						String CONTENT_MESYUARAT = (String)maklumatContent.get("KETERANGAN");
						content = " Pihak Tuan/Puan hendaklah memberi maklumbalas tindakan untuk pekara dibawah : ";
						content +=" <br><br><br><i>'"+CONTENT_MESYUARAT+"'</i> ";
						content += penutup;
						
						List listTindakanByContent = listTindakanByContent(session, ID_MESYUARATCONTENT);
						email.MULTIPLE_RECIEPIENT = new String[listTindakanByContent.size()];
						for(int g = 0; g < listTindakanByContent.size();g++)
						{
							Map t = (Map) listTindakanByContent.get(g);
							ID_MESYUARATTINDAKAN = (String) t.get("ID_MESYUARATTINDAKAN");							
							Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
							String EMEL = (String)maklumatTindakan.get("EMEL");
							email.MULTIPLE_RECIEPIENT[g] = EMEL;							
							myLogger.info(" error_emel ::::::: " +error_emel);							
						}
						email.SUBJECT = subject;
						email.MESSAGE = content;
						error_emel = email.sendEmail_returnEM();
						
						for(int c = 0; c < listTindakanByContent.size();c++)
						{
							Map z = (Map) listTindakanByContent.get(c);
							ID_MESYUARATTINDAKAN = (String) z.get("ID_MESYUARATTINDAKAN");							
							Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
							int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
							if(!error_emel.equals(""))
							{
								COUNT_EMEL = COUNT_EMEL;
							}
							else
							{
								COUNT_EMEL = COUNT_EMEL + 1;
							}
							saveTindakanEmelStatus(session,ID_MESYUARATTINDAKAN,COUNT_EMEL,error_emel);
						}
					}
				}
				
						
				
				
				
			 }
		 
		 */
		 
		 /*
		 public List listEmelPelulus(HttpSession session, String ID_UNIT)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listEmelPelulus = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement();
					
					sql = " SELECT DISTINCT UI.EMEL FROM USERS U, USERS_INTERNAL UI, (  "+
							" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk' "+ 
							" ) UR "+
							" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)  "+
							" AND (U.USER_ROLE = 'adminppk' OR UR.ROLE_ID = 'adminppk' )  "+
							" AND UI.ID_PEJABATJKPTG = "+ID_UNIT+"  "+
							" AND UI.EMEL IS NOT NULL ";
					
					myLogger.info(" SQL : listEmelPelulus :"+ sql);			
					rs = stmt.executeQuery(sql);
					listEmelPelulus = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
						listEmelPelulus.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listEmelPelulus;
			}
			*/
		    private String saveLampiran(HttpSession session,String ID_WARTATRM,String TYPE,String field_name,String type,String TEMPID_WARTATRM,Db db) throws Exception {				   	 
			        DiskFileItemFactory factory = new DiskFileItemFactory();
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			        if(!isMultipart)
			        {
			        myLogger.info("BUKAN MULTIPART");
			        } 
			        else
			        {
			        myLogger.info("MULTIPART");			    	
			        }	    
			        List items = upload.parseRequest(request);
			        Iterator itr = items.iterator();
			        while (itr.hasNext()) {
			          FileItem item = (FileItem)itr.next();		         	
			          if ((item.getName() != null) && (!("".equals(item.getName())))) {
			        	  //myLogger.info(" ID_WARTATRM : "+ID_WARTATRM+" item : "+item+" type : "+TYPE);
			        	  myLogger.info(" ID_WARTATRM : "+ID_WARTATRM+" field_name : "+field_name+" getFieldName : "+item.getFieldName() +" type : "+TYPE);
			        	  
			        	  if(field_name.equals(item.getFieldName()))
			        	  {
			        		  TEMPID_WARTATRM = saveLampiranDB(item,ID_WARTATRM,type,TEMPID_WARTATRM,db);
			        	  }
			        	 
			          }
			        }
			        return TEMPID_WARTATRM;
		      }
		    
		    
		    private String saveLampiranDB(FileItem item,String ID_WARTATRM,String type,String TEMPID_WARTATRM,
		    		Db db) throws Exception {
			    HttpSession session = request.getSession();	
			    String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		  		Db db1 = null;
		  		Map viewBaru = null;
				Map viewAsal = null;
				String aktiviti = "UPDATE";
				if(ID_WARTATRM.equals(""))
				{
					if(!TEMPID_WARTATRM.equals(""))
					{
						aktiviti = "UPDATE";
					}
					else
					{
						aktiviti = "INSERT";
					}
				}	
		        try {	
		        	if(db==null)
		        	{
		        		db1 = new Db();
		        	}
		        	else
		        	{
		        		db1 = db;
		        	}
		        	
		        	if(!TEMPID_WARTATRM.equals(""))
		        	{
		        		viewAsal = view(session, ID_WARTATRM,"","","",TEMPID_WARTATRM,db1);	
		        	}
		        	else
		        	{
		        		viewAsal = view(session, ID_WARTATRM,"","","",db1);	
		        	}
		        	//long id_lampiran = DB.getNextID(db1,"TBLADUANPUBLICLAMPIRAN_SEQ");	        			        	
		        	Connection con = db1.getConnection();
		        	//con.setAutoCommit(false);
		        	SQLRenderer r = new SQLRenderer();
		        	String sql = "";
		        	
		        	if(TEMPID_WARTATRM.equals("") && ID_WARTATRM.equals(""))
		        	{
		        		
		        		sql = " INSERT INTO TBLPDTWARTATRM ( "+
		        				" ID_WARTATRM, ";
				        		if(type.equals("WARTA"))
					        	{
				        			sql += " NAMA_FAIL_WARTA, JENIS_MIME_WARTA, CONTENT_WARTA, ";
					        	}
				        		else if(type.equals("PELAN"))
					        	{
				        			sql += " NAMA_FAIL_PELAN, JENIS_MIME_PELAN, CONTENT_PELAN, ";
					        	}
				        		sql+= "  ID_MASUK, ID_KEMASKINI, TARIKH_MASUK, TARIKH_KEMASKINI)";
				        		sql+= " VALUES (?,?,?,?,?,?,SYSDATE,SYSDATE)";
		        	}
		        	else
		        	{
			        	sql = "UPDATE TBLPDTWARTATRM SET ";
			        	if(type.equals("WARTA"))
			        	{
				    		  sql += " CONTENT_WARTA = ?,  "+
				    		  " NAMA_FAIL_WARTA = ?,  "+
				    		  " JENIS_MIME_WARTA = ?,  ";
			        	}
			        	else if(type.equals("PELAN"))
			        	{
				    		  sql += " CONTENT_PELAN = ?,  "+
				    		  " NAMA_FAIL_PELAN = ?,  "+
				    		  " JENIS_MIME_PELAN = ?,  ";
			        	}
			        	sql += " ID_KEMASKINI = ?, TARIKH_KEMASKINI = SYSDATE ";
			        	sql += " WHERE ID_WARTATRM = ? ";
		        	}
		        	
		        	PreparedStatement ps = con.prepareStatement(sql);
		        	
		        	String nama_asal_doc = item.getName().substring(0, item.getName().indexOf("."));
		        	String nama_doc = nama_asal_doc;
		        	/*
		        	int copy_count = 0;
		        	do {
	     			   	myLogger.info(" ***** nama_doc ada duplicate : "+nama_doc);
		        		if(checkDuplicateDocName(session,ID_WARTATRM,nama_doc.toUpperCase(),type,db1)==true)
		        		{
			        		copy_count++;
			        		nama_doc = nama_asal_doc + " COPY("+copy_count+")";
			        		myLogger.info(" ***** nama_doc baru : "+nama_doc);
		        		}
		        	}while(checkDuplicateDocName(session,ID_WARTATRM,nama_doc.toUpperCase(),type,db1)==true);
		        	*/
		        	
		        	myLogger.info(" ***** nama_doc utk disimpan : "+nama_doc);
		        	myLogger.info(" ***** item utk disimpan : "+item.getContentType());
		        	
		        	if(TEMPID_WARTATRM.equals("") && ID_WARTATRM.equals(""))
		        	{
		        		TEMPID_WARTATRM = DB.getNextID(db1, "TBLPDTWARTATRM_SEQ")+"";
		        		ps.setLong(1, Long.parseLong(TEMPID_WARTATRM));				        	
			        	ps.setString(2,nama_doc);
			        	ps.setString(3,item.getContentType());
			        	ps.setBinaryStream(4, item.getInputStream(),(int)item.getSize());
			        	ps.setString(5,USER_ID_SYSTEM);
			        	ps.setString(6,USER_ID_SYSTEM);
			        	ps.executeUpdate();	
		        	}
		        	else
		        	{
			        	ps.setBinaryStream(1, item.getInputStream(),(int)item.getSize());
			        	ps.setString(2,nama_doc);
			        	ps.setString(3,item.getContentType());
			        	ps.setString(4,USER_ID_SYSTEM);
			        	if(!TEMPID_WARTATRM.equals(""))
			        	{
			        		ps.setString(5,TEMPID_WARTATRM);
			        	}
			        	else
			        	{
			        		ps.setString(5,ID_WARTATRM);
			        	}
			        	ps.executeUpdate();	
		        	}
		            //con.commit(); 
		            
		            AuditTrail.logActivity(this,session,"UPD","MEMUAT NAIK LAMPIRAN "+type+" ["+item.getName()+"]");
		          //open history
					
					
					if(!TEMPID_WARTATRM.equals(""))
		        	{
						viewBaru = view(session, TEMPID_WARTATRM,"","","",TEMPID_WARTATRM,db1);
						checkForHistory(session,TEMPID_WARTATRM, viewAsal, viewBaru,aktiviti,db1);
		        	}
		        	else
		        	{
		        		viewBaru = view(session, ID_WARTATRM,"","","",db1);
		        		checkForHistory(session,ID_WARTATRM, viewAsal, viewBaru,aktiviti,db1);
		        	}
					
					//myLogger.info("***BARU NO WARTA : "+(String)viewBaru.get("NO_WARTA"));
					
					//close history
					
					return TEMPID_WARTATRM;
			    } finally {
			    	if(db==null)
		        	{
				      if (db1 != null) db1.close();
		        	}
			    }
			    
		  }		
		    
		    
		    
		    
		    @SuppressWarnings("unchecked")
			public ArrayList checkPilihanTahun(HttpSession session,Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				ArrayList listTahun = new ArrayList();
				//boolean checkDuplicateDocName = false;
				String sql = "";
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					stmt = db1.getStatement();
					sql = " SELECT TAHUN FROM TBLPDTTRMPILIHANTAHUN ORDER BY TAHUN ";					
					myLogger.info(" SQL : checkPilihanTahun :"+ sql);			
					rs = stmt.executeQuery(sql);
										
					while (rs.next()) {
						listTahun.add(rs.getString("TAHUN"));
					}

				} finally {
					if(db==null)
		        	{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
		        	}
				}
				return listTahun;
			}
		    
		    
		    @SuppressWarnings("unchecked")
			public boolean checkDuplicateDocName(HttpSession session,String ID_WARTATRM,String NAMA_DOC,String type,Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				boolean checkDuplicateDocName = false;
				String sql = "";
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					stmt = db1.getStatement();
					sql = " SELECT COUNT(T.ID_WARTATRM) AS CNT FROM TBLPDTWARTATRM T WHERE T.ID_WARTATRM IS NOT NULL ";
							if(type.equals("WARTA"))
							{
								sql += " AND UPPER(T.NAMA_FAIL_WARTA) = '"+NAMA_DOC.toUpperCase()+"' ";
							}
							else if(type.equals("PELAN"))
							{
								sql += " AND UPPER(T.NAMA_FAIL_PELAN) = '"+NAMA_DOC.toUpperCase()+"' ";
							}
							sql += " AND T.ID_WARTATRM = '"+ID_WARTATRM+"' ";
					
					myLogger.info(" SQL : checkDuplicateDocName :"+ sql);			
					rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						
						if(rs.getInt("CNT")>0)
						{
							checkDuplicateDocName = true;
						}
					}

				} finally {
					if(db==null)
		        	{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
		        	}
				}
				return checkDuplicateDocName;
			}
		 
		    /*
		    @SuppressWarnings("unchecked")
			public List listLampiran(HttpSession session, String ID_WARTATRM, Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listFolderLampiran = null;
				String sql = "";
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db1.getStatement();
					
					sql = " SELECT T.ID_WARTATRMLAMPIRAN, T.NAMA_LAMPIRAN, T.JENIS_MIME, "+
							" T.ID_WARTATRM, T.CONTENT, T.ID_MASUK, "+
							" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
							" FROM TBLADUANPUBLICLAMPIRAN T WHERE T.ID_WARTATRM = '"+ID_WARTATRM+"' " +
							" ORDER BY T.TARIKH_MASUK DESC "; 
					
					myLogger.info(" SQL : listFolderLampiran :"+ sql);			
					rs = stmt.executeQuery(sql);
					listFolderLampiran = Collections.synchronizedList(new ArrayList());
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
						
						h.put("ID_WARTATRMLAMPIRAN",rs.getString("ID_WARTATRMLAMPIRAN") == null ? "" : rs.getString("ID_WARTATRMLAMPIRAN"));	
						h.put("ID_WARTATRM",rs.getString("ID_WARTATRM") == null ? "" : rs.getString("ID_WARTATRM"));	
						h.put("CONTENT",rs.getString("CONTENT") == null ? "" : rs.getString("CONTENT"));	
						h.put("NAMA_LAMPIRAN",rs.getString("NAMA_LAMPIRAN") == null ? "" : rs.getString("NAMA_LAMPIRAN"));	
						h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));	
						listFolderLampiran.add(h);
					}

				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return listFolderLampiran;
			}
			
		    
		    public void deleteLampiran(HttpSession session,String ID_WARTATRMLAMPIRAN,String NAMA_LAMPIRAN) throws Exception {
				Connection conn = null;
				Db db = null;
				String sql = "";
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();	
						
					r.clear();
					r.add("ID_WARTATRMLAMPIRAN",ID_WARTATRMLAMPIRAN);
					sql = r.getSQLDelete("TBLADUANPUBLICLAMPIRAN");
					stmt.executeUpdate(sql);
					AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICLAMPIRAN ["+NAMA_LAMPIRAN+"] Deleted");
				
					conn.commit();
				
				} 
					catch (SQLException se) { 
						myLogger.error(se);
				    	try {
				    		conn.rollback();
				    	} catch (SQLException se2) {
				    		throw new Exception("Rollback error:"+se2.getMessage());
				    	}
				    	throw new Exception("Ralat :"+se.getMessage());
					}
					catch (Exception re) {
						throw re;
					}finally {
						if (db != null)
							db.close();
					}
				}
		 
		    
		    
		    
		    public void saveKronologi(HttpSession session,String ID_WARTATRM, String ID_STATUS,Db db) throws Exception {
				Db db1 = null;
				String sql = "";
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				try {
					
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					
					
					if(!ID_WARTATRM.equals(""))
					{
						r.clear();
						r.update("ID_WARTATRM", ID_WARTATRM);
						r.update("FLAG_AKTIF", 1);
						r.add("FLAG_AKTIF", 0);	
						sql = r.getSQLUpdate("TBLKRONOLOGIADUANPUBLIC");
						myLogger.info("ADUAN REMOVE AKTIF : "+sql);				
						stmt.executeUpdate(sql);
					}
					
					r.clear();
					r.add("ID_WARTATRM", ID_WARTATRM);
					r.add("FLAG_AKTIF", 1);
					r.add("ID_STATUS", ID_STATUS);	
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLKRONOLOGIADUANPUBLIC");	
					myLogger.info("ADD STATUS AKTIF TBLKRONOLOGIADUANPUBLIC : "+sql);				
					stmt.executeUpdate(sql);
					
					r.clear();
					r.update("ID_WARTATRM", ID_WARTATRM);
					r.add("ID_STATUS", ID_STATUS);	
					
					if(ID_STATUS.equals("16122"))//dalam tindakan ui
					{
						r.add("TARIKH_TERIMA_ADUAN_UI", r.unquote("sysdate"));
						r.add("ID_PEGAWAI_UI", USER_ID_SYSTEM);						
					}
					else if(ID_STATUS.equals("16123"))//selesai
					{
						r.add("TARIKH_ADUAN_SELESAI", r.unquote("sysdate"));
						//r.add("ID_PEGAWAI_UI", USER_ID_SYSTEM);						
					}
					else if(ID_STATUS.equals("16127"))//dalam tindakan bahagian
					{
						r.add("TARIKH_TERIMA_ADUAN_BAHAGIAN", r.unquote("sysdate"));
						r.add("ID_PEGAWAI_BAHAGIAN", USER_ID_SYSTEM);						
					}
					
					
					sql = r.getSQLUpdate("TBLADUANPUBLIC");	
					myLogger.info("ADD STATUS PERMOHONAN : "+sql);				
					stmt.executeUpdate(sql);
					
					
					
					//setnofikasi
					saveNotifikasi(session,ID_WARTATRM, ID_STATUS,db1);
					
					
				} 				
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
			}
		    
		    
		    public void saveNotifikasi(HttpSession session,String ID_WARTATRM, String ID_STATUS,Db db) throws Exception {
				Db db1 = null;
				String sql = "";
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
				String selectedLanguage = (String) session.getAttribute("selectedLanguage");
				List listPegawaiBahagian = null;
				List listPegawaiUI = null;
				try {
					
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					Map view = view(session, ID_WARTATRM,"","",selectedLanguage,db);
					String ID_PENGADU = (String)view.get("ID_PENGADU");
				    String ID_SUMBERADUAN = (String)view.get("ID_SUMBERADUAN");
				    String ID_PEGAWAI_UI = (String)view.get("ID_PEGAWAI_UI");
				    String ID_PEGAWAI_BAHAGIAN = (String)view.get("ID_PEGAWAI_BAHAGIAN");
				    String ID_JENISADUAN = (String)view.get("ID_JENISADUAN");
				    String ID_KATEGORIADUAN = (String)view.get("ID_KATEGORIADUAN");
				    String ID_MASUK = (String)view.get("ID_MASUK");					
				    String ID_BAHAGIAN = (String)view.get("ID_BAHAGIAN");	
				    String ID_NEGERI_BAHAGIAN = (String)view.get("ID_NEGERI_BAHAGIAN");					    
				    String EMEL_PENGADU = (String)view.get("EMEL_PENGADU");
				    String EMEL_PEGAWAI_BAHAGIAN = (String)view.get("EMEL_PEGAWAI_BAHAGIAN");
				    String EMEL_PEGAWAI_UI= (String)view.get("EMEL_PEGAWAI_UI");
				    
					if(ID_STATUS.equals("16121"))//LOG_BARU
					{
						if(ID_SUMBERADUAN.equals("16101"))
						{
							listPegawaiUI = listPegawaiUI(session,db1);
							String[] rep_listPegawaiUI = new String[listPegawaiUI.size()];
							for(int i = 0; i < listPegawaiUI.size();i++)
							{
								Map m = (Map) listPegawaiUI.get(i);
								String user_id_pegawai_ui = (String) m.get("USER_ID");
								String emel_id_pegawai_ui = (String) m.get("EMEL");
								saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,user_id_pegawai_ui,ID_STATUS,db1,"insert");
								rep_listPegawaiUI[i] = emel_id_pegawai_ui;								
							}							
							
						    rep_listPegawaiUI = checkArray(rep_listPegawaiUI);
						    hantarEmel(session,ID_WARTATRM,rep_listPegawaiUI,ID_STATUS,db1);
						  //EMEL KEPADA UI
						}
					}
					else if(ID_STATUS.equals("16122"))//DALAM TINDAKAN UNIT INTEGRITI
					{
						String[] rep_pemohon = new String[1];						
						if(!ID_SUMBERADUAN.equals("16101") && !ID_SUMBERADUAN.equals(""))
						{
							//EMEL KEPADA PEMOHON
							rep_pemohon[0] = EMEL_PENGADU;	
						}
						else
						{
							saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,ID_PENGADU,ID_STATUS,db1,"insert");
							rep_pemohon[0] = EMEL_PENGADU;	
							//EMEL KEPADA PEMOHON
						}
						rep_pemohon = checkArray(rep_pemohon);
						//hantarEmel(session,ID_WARTATRM,rep_pemohon,ID_STATUS,db1);
					}
					else if(ID_STATUS.equals("16126"))//HANTAR KE BAHAGIAN
					{
						listPegawaiBahagian = listPegawaiBahagian(session,ID_BAHAGIAN,ID_NEGERI_BAHAGIAN,db1);
						String[] rep_listPegawaiBahagian = new String[listPegawaiBahagian.size()];
						for(int i = 0; i < listPegawaiBahagian.size();i++)
						{
							Map m = (Map) listPegawaiBahagian.get(i);
							String user_id_pegawai_bahagian = (String) m.get("USER_ID");
							String emel_id_pegawai_bahagian = (String) m.get("EMEL");
							saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,user_id_pegawai_bahagian,ID_STATUS,db1,"insert");
							rep_listPegawaiBahagian[i] = emel_id_pegawai_bahagian;
							//EMEL KEPADA bahagian
						}
						rep_listPegawaiBahagian = checkArray(rep_listPegawaiBahagian);
						hantarEmel(session,ID_WARTATRM,rep_listPegawaiBahagian,ID_STATUS,db1);
					}
					else if(ID_STATUS.equals("16127"))//DALAM TINDAKAN BAHAGIAN
					{
						saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,ID_PEGAWAI_UI,ID_STATUS,db1,"insert");
						String[] rep_pegawai_ui = new String[1];
						rep_pegawai_ui[0] = EMEL_PEGAWAI_UI;
						rep_pegawai_ui = checkArray(rep_pegawai_ui);
						//hantarEmel(session,ID_WARTATRM,rep_pegawai_ui,ID_STATUS,db1);
						//emel kepada pegawai ui
					}
					else if(ID_STATUS.equals("16123"))//SELESAI
					{
						String[] rep_pegawai_ui_pemohon = new String[2];
						if(!ID_PEGAWAI_BAHAGIAN.equals(""))
						{
							saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,ID_PEGAWAI_UI,ID_STATUS,db1,"insert");
							if(!ID_PENGADU.equals(""))
							{
								saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,ID_PENGADU,ID_STATUS,db1,"insert");
							}
							//emel tu pengadu ngn pegawai ui
							rep_pegawai_ui_pemohon[0] = EMEL_PEGAWAI_UI;
						}
						else
						{
							saveTableNotifikasi(session,ID_WARTATRM,USER_ID_SYSTEM,ID_PENGADU,ID_STATUS,db1,"insert");
							rep_pegawai_ui_pemohon[0] = EMEL_PENGADU;	
							//emel tu pengadu
						}
						rep_pegawai_ui_pemohon = checkArray(rep_pegawai_ui_pemohon);
						hantarEmel(session,ID_WARTATRM,rep_pegawai_ui_pemohon,ID_STATUS,db1);
					}			
				} 				
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
			}
		    
		    public String[] checkArray(String[] array_asal)
		    {
			    List<String> list = new ArrayList<String>();
			    for(String s : array_asal) {
			       if(s != null && s.length() > 0) {
			          list.add(s);
			       }
			    }
			    return list.toArray(new String[list.size()]);
		    }
		    
		    public List listPegawaiUI(HttpSession session, Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listPegawaiUI = null;
				String sql = "";
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db1.getStatement();
					
					sql = " SELECT DISTINCT U.USER_ID,UI.EMEL, U.USER_NAME FROM USERS U, USERS_INTERNAL UI, (  "+
							" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'user_unit_intergriti'   "+
							" ) UR WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)   "+
							" AND (U.USER_ROLE = 'user_unit_intergriti' OR UR.ROLE_ID = 'user_unit_intergriti' )  ";
					
					myLogger.info(" SQL : listPegawaiUI :"+ sql);			
					rs = stmt.executeQuery(sql);
					listPegawaiUI = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());	
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
						listPegawaiUI.add(h);
					}

				} finally {
					if(db == null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return listPegawaiUI;
			}
		    
		    
		    public List listPegawaiBahagian(HttpSession session,String ID_BAHAGIAN,String ID_NEGERI, Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listPegawaiBahagian = null;
				String sql = "";
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db1.getStatement();
					
					sql = " SELECT DISTINCT U.USER_ID,UI.EMEL, U.USER_NAME FROM USERS U, USERS_INTERNAL UI, (  "+
							" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'wakil_bahagian_aduan'  "+ 
							" ) UR  "+
							" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)   "+
							" AND (U.USER_ROLE = 'wakil_bahagian_aduan' OR UR.ROLE_ID = 'wakil_bahagian_aduan') "+ 
							" AND UI.ID_NEGERI = '"+ID_NEGERI+"' AND UI.ID_SEKSYEN = '"+ID_BAHAGIAN+"'  ";
					
					myLogger.info(" SQL : listPegawaiBahagian :"+ sql);			
					rs = stmt.executeQuery(sql);
					listPegawaiBahagian = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());	
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
						listPegawaiBahagian.add(h);
					}

				} finally {
					if(db == null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return listPegawaiBahagian;
			}
		    
		    		    
		    
		    public void saveTableNotifikasi(HttpSession session,String ID_WARTATRM,String ID_HANTAR,String ID_TERIMA, String ID_STATUS,Db db,String flag) throws Exception {
				Db db1 = null;
				String sql = "";
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				try {
					
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					
					if(flag.equals("insert"))
					{
						r.clear();
						r.add("ID_WARTATRM", ID_WARTATRM);
						r.add("FLAG_READ", "N");
						r.add("ID_HANTAR", ID_HANTAR);	
						r.add("ID_TERIMA", ID_TERIMA);	
						r.add("ID_STATUS", ID_STATUS);						
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLADUANPUBLICNOTIFIKASI");	
					}
					else if(flag.equals("update"))
					{
						//String id_current_status = getCurrentStatus(session, ID_WARTATRM, db1);
						r.clear();
						r.update("ID_WARTATRM", ID_WARTATRM);
						r.update("FLAG_READ", "N");
						if(ID_STATUS.equals("16127") || ID_STATUS.equals("16122") || ID_STATUS.equals("16123"))
						{
							r.update("ID_TERIMA", USER_ID_SYSTEM);	
						}
						r.add("FLAG_READ", "Y");						
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("TBLADUANPUBLICNOTIFIKASI");
					}
					
					myLogger.info("ADD TBLADUANPUBLICNOTIFIKASI : "+sql);				
					stmt.executeUpdate(sql);
					
				} 				
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
			}
		    
		    
		    
		    public boolean checkStatusExist(HttpSession session, String ID_WARTATRM, String ID_STATUS, Db db) throws Exception {
				Db db1 = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				boolean check = false;
				try {
					
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db.getStatement();
					
					sql = " SELECT T.ID_KRONOLOGI, T.ID_WARTATRM, T.ID_STATUS, T.FLAG_AKTIF, T.ID_MASUK, T.TARIKH_MASUK " +
							" FROM TBLKRONOLOGIADUANPUBLIC T WHERE T.ID_WARTATRM = '"+ID_WARTATRM+"' AND T.ID_STATUS = '"+ID_STATUS+"' ";	
					myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {								
							if(!(rs.getString("ID_KRONOLOGI") == null ? "" : rs.getString("ID_KRONOLOGI")).equals(""))
							{
								check = true;
							}
						}			
					return check;
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
			}
		  
		    @SuppressWarnings("unchecked")
			public Map getDetailsUser(HttpSession session,Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				String sql = "";
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
				Map h = null;
						
				try {
						
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					
					stmt = db1.getStatement();		
				    sql = 	" SELECT U.USER_ID, UI.ID_SEKSYEN, UI.ID_NEGERI FROM USERS U, USERS_INTERNAL UI, "+
				    		" (SELECT UR.USER_ID AS USER_LOGIN, UR.ROLE_ID  FROM USER_ROLE UR WHERE ROLE_ID = 'wakil_bahagian_aduan') USERROLE "+
				    		" WHERE U.USER_ID = UI.USER_ID AND U.USER_ID = '"+USER_ID_SYSTEM+"'  "+
				    		" AND U.USER_LOGIN = USERROLE.USER_LOGIN(+)  "+
				    		" AND (USERROLE.ROLE_ID = 'wakil_bahagian_aduan' OR U.USER_ROLE = 'wakil_bahagian_aduan')  ";
					myLogger.info(" ADUAN: SQL getDetailsUser :"+ sql);			
					rs = stmt.executeQuery(sql);
					while (rs.next()) {	
						h = Collections.synchronizedMap(new HashMap());	
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
						h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));						
					}
					
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return h;

			}
			
		    
		    
		    public String returnHTMLMaklumatAduan(HttpSession session,Map MaklumatAduan,Db db)
		    {
		    	Map view = MaklumatAduan;
		    	String SUMBER_PENGADU = (String)view.get("SUMBER_PENGADU");
		 		String ID_STATUS = (String)view.get("ID_STATUS");
		 		String ID_SUMBERTINDAKAN = (String)view.get("ID_SUMBERTINDAKAN");		 		
		 		String STATUS = (String)view.get("STATUS");
		 		String TARIKH_ADUAN = (String)view.get("TARIKH_ADUAN");
		 		String TARIKH_ADUAN_SELESAI = (String)view.get("TARIKH_ADUAN_SELESAI");
		 		String NO_ADUAN = (String)view.get("NO_ADUAN");
		 		String JENIS_ADUAN = (String)view.get("JENIS_ADUAN");
		 		String FLAG_HIDE_INFO = (String)view.get("FLAG_HIDE_INFO");
		 		String EMEL_PENGADU = (String)view.get("EMEL_PENGADU");
		 		String TEL_PENGADU = (String)view.get("TEL_PENGADU");
		 		String NAMA_PENGADU = (String)view.get("NAMA_PENGADU");
		 		String ID_JENISADUAN = (String)view.get("ID_JENISADUAN");
		 		String KATEGORIADUAN = (String)view.get("KATEGORIADUAN");
		 		String SUBJEK_ADUAN = (String)view.get("SUBJEK_ADUAN");
		 		String TARIKH_KEJADIAN = (String)view.get("TARIKH_KEJADIAN");
		 		String JAM = (String)view.get("JAM");
		 		String MINIT = (String)view.get("MINIT");
		 		String JENIS_WAKTU = (String)view.get("JENIS_WAKTU");
		 		String LOKASI_KEJADIAN = (String)view.get("LOKASI_KEJADIAN");
		 		String NEGERI = (String)view.get("NEGERI");
		 		String DAERAH = (String)view.get("DAERAH");
		 		String NAMA_PIHAK_TERLIBAT = (String)view.get("NAMA_PIHAK_TERLIBAT");
		 		String KETERANGAN_ADUAN = (String)view.get("KETERANGAN_ADUAN");
		 		
		 		String KATEGORITINDAKAN = (String)view.get("KATEGORITINDAKAN");
		 		String NEGERI_BAHAGIAN = (String)view.get("NEGERI_BAHAGIAN");
		 		String NAMA_BAHAGIAN = (String)view.get("NAMA_BAHAGIAN");
		 		String SUMBER_BAHAGIAN = (String)view.get("SUMBER_BAHAGIAN");
		 		String MAKLUMBALAS_UI = (String)view.get("MAKLUMBALAS_UI");
		 		
		 		String MAKLUMBALAS_BAHAGIAN = (String)view.get("MAKLUMBALAS_BAHAGIAN");
		 		
		    	String maklumat = "";
		    	maklumat += "<table width=\"100%\" border=\"0\">";
		    	//maklumat pengadu
		    	maklumat += returnTDTR(session,"legend_pengadu","","tajuk");
		    	maklumat += returnTDTR(session,"label_sumber_pengadu",SUMBER_PENGADU,"content");
		    	if(FLAG_HIDE_INFO.equals("1"))
		    	{
			    	maklumat += returnTDTR(session,"label_nama_pengadu",returnTDTR(session,"label_maklumat_dilindungi","","justConvert"),"content");
			    	maklumat += returnTDTR(session,"label_tel_pengadu",returnTDTR(session,"label_maklumat_dilindungi","","justConvert"),"content");
			    	maklumat += returnTDTR(session,"label_emel_pengadu",returnTDTR(session,"label_maklumat_dilindungi","","justConvert"),"content");
		    	}
		    	else
		    	{
		    		maklumat += returnTDTR(session,"label_nama_pengadu",NAMA_PENGADU,"content");
		    		if(!TEL_PENGADU.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_tel_pengadu",TEL_PENGADU,"content");
		    		}
		    		if(!EMEL_PENGADU.equals(""))
		    		{
		    			maklumat += returnTDTR(session,"label_emel_pengadu",EMEL_PENGADU,"content");
		    		}
		    	}
		    	
		    	//maklumat aduan
		    	maklumat += returnTDTR(session,"legend_form_aduan_edit","","tajuk");
		    	maklumat += returnTDTR(session,"label_status_aduan",STATUS,"content");
		    	maklumat += returnTDTR(session,"label_tarikh_aduan",TARIKH_ADUAN,"content");
		    	if(!TARIKH_ADUAN_SELESAI.equals(""))
		    	{
		    		maklumat += returnTDTR(session,"label_tarikh_aduan_selesai",TARIKH_ADUAN_SELESAI,"content");
		    	}
		    	maklumat += returnTDTR(session,"label_no_aduan",NO_ADUAN,"content");
		    	maklumat += returnTDTR(session,"label_jenis_aduan",JENIS_ADUAN,"content");
		    	//maklumat += returnTDTR(session,"label_hide_personal_info",FLAG_HIDE_INFO,"content");
		    	
		    	if(!ID_JENISADUAN.equals(""))
		    	{
			    	if(ID_JENISADUAN.equals("16101"))
			    	{
			    		//content aduan
			    		maklumat += returnTDTR(session,"","","tajuk");
			    		if(!KATEGORIADUAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_kategori_aduan",KATEGORIADUAN,"content");
			    		}
			    		if(!SUBJEK_ADUAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_subjek",SUBJEK_ADUAN,"content");
			    		}
			    		if(!TARIKH_KEJADIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_tarikh_kejadian",TARIKH_KEJADIAN,"content");
			    		}
			    		if(!JAM.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_masa_kejadian",JAM+":"+MINIT+" "+JENIS_WAKTU,"content");
			    		}
			    		if(!LOKASI_KEJADIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_lokasi_kejadian",LOKASI_KEJADIAN,"content");
			    		}
			    		if(!NEGERI.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_negeri",NEGERI,"content");
			    		}
			    		if(!DAERAH.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_daerah",DAERAH,"content");
			    		}
			    		if(!NAMA_PIHAK_TERLIBAT.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_pihak_telibat",NAMA_PIHAK_TERLIBAT,"content");
			    		}
			    		maklumat += returnTDTR(session,"label_keterangan_aduan",KETERANGAN_ADUAN,"content");
			    	}
			    	else
			    	{
			    		maklumat += returnTDTR(session,"","","tajuk");
			    		maklumat += returnTDTR(session,"label_keterangan_cadangan",KETERANGAN_ADUAN,"content");
			    	}
		    	}  	
		    	
		    	if(ID_STATUS.equals("16123"))
		    	{
		    	
		    		if(!ID_SUMBERTINDAKAN.equals("16101"))
		    		{
				    	//maklumat skrin UI
				    	maklumat += returnTDTR(session,"legend_form_tindakan_ui","","tajuk");
			    		if(!KATEGORITINDAKAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_jenis_tindakan_aduan",KATEGORITINDAKAN,"content");
			    		}
			    		if(!NEGERI_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_negeri_bahagian",NEGERI_BAHAGIAN,"content");
			    		}
			    		if(!NAMA_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_tindakan_bahagian",NAMA_BAHAGIAN,"content");
			    		}
			    		if(!SUMBER_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_sumber_bahagian",SUMBER_BAHAGIAN,"content");
			    		}
			    		if(!MAKLUMBALAS_UI.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_keterangan_ui",MAKLUMBALAS_UI,"content");
			    		}
		    		}
		    		else
		    		{
			    		//maklumat skrin bahagian
			    		maklumat += returnTDTR(session,"legend_form_tindakan_bahagian","","tajuk");
			    		if(!MAKLUMBALAS_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_keterangan_bahagian",MAKLUMBALAS_BAHAGIAN,"content");
			    		}
		    		}		    		
		    	
		    	}
		    	
		    	maklumat += "</table>";
		    	return maklumat;
		    }
		    
		    public String returnTDTR(HttpSession session,String label,String Content,String type)
		    {
		    	if(!label.equals(""))
		    	{
			    	String selectedLanguage = (String) session.getAttribute("selectedLanguage");
			    	ResourceBundle rb_lang = null;
					Enumeration bundleKeys = null;	
					if(selectedLanguage.equals("ENGLISH"))
		      		{
		      			rb_lang = ResourceBundle.getBundle("eng_lang");      						
		      		}
		      		else
		      		{
		      			rb_lang = ResourceBundle.getBundle("malay_lang");
		      		}
		      		
		      		if(rb_lang!=null)
		      		{
		      			bundleKeys = rb_lang.getKeys();
		      			while (bundleKeys.hasMoreElements()) {
		      			    String key = (String)bundleKeys.nextElement();
		      			    String value = rb_lang.getString(key);
		      			    //myLogger.info("key : "+key+" value : "+value);		
		      			    context.put(key,value);	
		      			    if(label.equals(key))
		      			    {
		      			    	label = value;
		      			    	break;
		      			    }
		      			}
		      		}	
		    	}
	      		
	      		
		    	String html = "";
		    	if(type.equals("content"))
		    	{
			    	html += "<tr>" +
			    			"<td valign=\"top\"  width=\"1%\"></td>" +
			    			"<td valign=\"top\"  width=\"28%\">"+label.toUpperCase()+"</td>" +
			    			"<td valign=\"top\"  width=\"1%\">:</td>" +
			    			"<td valign=\"top\"  width=\"70%\">"+Content+"</td>" +		    			
			    			"</tr>";
		    	}
		    	else if(type.equals("tajuk"))
		    	{
		    		html += "<tr>" +
		    	            "<td colspan=\"4\" style=\"border-bottom: 1px solid black;\"><b>"+label.toUpperCase()+"</b></td> "+
			    			"</tr>";
		    	}
		    	else if(type.equals("justConvert"))
		    	{
		    		html += label.toUpperCase();
		    	}
		    	return html;
		    }
		    */
		    
		    
		    
		    public List listWARTATRMHISTORY(HttpSession session,String ID_WARTATRM,String ID_UTAMA,String TYPE,
		    		String carianTerperinciHISTORY,String TARIKH_MULA_HISTORY, String TARIKH_AKHIR_HISTORY,Db db)throws Exception {
		    	Db db1 = null;
		    	
		    	ResultSet rs = null;
		    	Statement stmt = null;
		    	List listWARTATRMHISTORY = null;
		    	String sql = "";
		    	String filter = "";
		    	
		    	
		    	if(!carianTerperinciHISTORY.equals(""))
		    	{
		    		filter += " AND ( " +
		    				" UPPER(UTAMA.JENIS_AKTIVITI) LIKE '%"+carianTerperinciHISTORY.toUpperCase()+"%' "+
		    				" OR UPPER(SUB.REKOD_SEBELUM) LIKE UPPER ('%"+carianTerperinciHISTORY.toUpperCase()+"%') "+
		    				" OR UPPER(SUB.REKOD_SELEPAS) LIKE UPPER ('%"+carianTerperinciHISTORY.toUpperCase()+"%') "+
		    				" OR UPPER(SUB.REKOD_LABEL) LIKE UPPER ('%"+carianTerperinciHISTORY.toUpperCase()+"%') "+						
		    				"  )         ";
		    	}


		    	if(!TARIKH_MULA_HISTORY.equals("") && !TARIKH_AKHIR_HISTORY.equals(""))
		    	{
		    		filter += " AND UTAMA.TARIKH_MASUK BETWEEN TO_DATE('"+TARIKH_MULA_HISTORY+"','DD/MM/YYYY') AND TO_DATE('"+TARIKH_AKHIR_HISTORY+"','DD/MM/YYYY') ";
		    	}
		    	if(!TARIKH_MULA_HISTORY.equals("") && TARIKH_AKHIR_HISTORY.equals(""))
		    	{
		    		filter += " AND UTAMA.TARIKH_MASUK >= TO_DATE('"+TARIKH_MULA_HISTORY+"','DD/MM/YYYY') ";
		    	}
		    	if(TARIKH_MULA_HISTORY.equals("") && !TARIKH_AKHIR_HISTORY.equals(""))
		    	{
		    		filter += " AND UTAMA.TARIKH_MASUK <= TO_DATE('"+TARIKH_AKHIR_HISTORY+"','DD/MM/YYYY') ";
		    	}		    	
		    	if(!ID_WARTATRM.equals(""))
		    	{
		    		filter += " AND UTAMA.ID_WARTATRM = '"+ID_WARTATRM+"' ";
		    	}
		    	
		    	
		    	
		    	try {
		    		if(db == null)
			    	{
			    		db1 = new Db();
			    	}
			    	else
			    	{
			    		db1 = db;
			    	}
		    		stmt = db1.getStatement();			
		    		
		    		if(TYPE.equals("UTAMA"))
		    		{
		    			sql = " SELECT DISTINCT UPPER(U.USER_NAME) AS USER_NAME, U.USER_LOGIN, UTAMA.JENIS_AKTIVITI AS AKTIVITI, NULL AS ID_SEJARAHWTRMSUB, UTAMA.ID_SEJARAHWTRMUTAMA, " +
		    					" NULL AS REKOD_SELEPAS, "+
		    					" NULL AS REKOD_SEBELUM, NULL AS REKOD_LABEL, TO_CHAR(UTAMA.TARIKH_MASUK,'DD/MM/YYYY hh24:mi:ss') AS TARIKH_MASUK , UTAMA.ID_MASUK, 1 AS LAYER  "+
		    					" FROM TBLSEJARAHWTRMUTAMA UTAMA, USERS U, TBLSEJARAHWTRMSUB SUB  "+
		    					" WHERE UTAMA.ID_MASUK = U.USER_ID AND SUB.ID_SEJARAHWTRMUTAMA(+) = UTAMA.ID_SEJARAHWTRMUTAMA ";		    			
		    			sql += filter;
		    			sql +=	" ORDER BY TARIKH_MASUK DESC,ID_SEJARAHWTRMUTAMA ASC,LAYER ASC, REKOD_LABEL ASC  ";
		    		}
		    		else if(TYPE.equals("SUB"))
		    		{
		    			sql = " SELECT '' AS USER_NAME, '' AS USER_LOGIN,'' AS AKTIVITI, SUB.ID_SEJARAHWTRMSUB,SUB.ID_SEJARAHWTRMUTAMA,SUB.REKOD_SELEPAS, "+
		    					" SUB.REKOD_SEBELUM,SUB.REKOD_LABEL, SUB.TARIKH_MASUK AS TARIKH_MASUK , SUB.ID_MASUK, 2 AS LAYER "+
		    					" FROM TBLSEJARAHWTRMSUB SUB WHERE SUB.ID_SEJARAHWTRMUTAMA = '"+ID_UTAMA+"' "+
		    					" ORDER BY TARIKH_MASUK,ID_SEJARAHWTRMUTAMA,LAYER, REKOD_LABEL ";
		    		}
		    		/*
		    		else if(TYPE.equals("LAPORAN"))
		    		{
		    			sql = " SELECT DISTINCT UPPER (U.USER_NAME) AS USER_NAME, U.USER_LOGIN, "+
		    					" UTAMA.JENIS_AKTIVITI AS AKTIVITI, "+
		    					" NULL AS ID_SEJARAHWTRMSUB, UTAMA.ID_SEJARAHWTRMUTAMA, "+
		    					" NULL AS REKOD_SELEPAS, NULL AS REKOD_SEBELUM, "+
		    					" NULL AS REKOD_LABEL, "+
		    					" TO_CHAR (UTAMA.TARIKH_MASUK, "+
		    					" 'DD/MM/YYYY hh24:mi:ss' "+
		    					" ) AS TARIKH_MASUK, "+
		    					" UTAMA.ID_MASUK, 1 AS LAYER "+
		    					" FROM TBLSEJARAHWTRMUTAMA UTAMA, "+
		    					" USERS U, "+
		    					" TBLSEJARAHWTRMSUB SUB "+
		    					" WHERE UTAMA.ID_MASUK = U.USER_ID ";
		    					sql += filter;
		    			sql += " AND SUB.ID_SEJARAHWTRMUTAMA(+) = UTAMA.ID_SEJARAHWTRMUTAMA "+
		    					" UNION                 "+
		    					" SELECT   '' AS USER_NAME, '' AS USER_LOGIN, '' AS AKTIVITI, "+
		    					" SUB.ID_SEJARAHWTRMSUB, SUB.ID_SEJARAHWTRMUTAMA, "+
		    					" SUB.REKOD_SELEPAS, SUB.REKOD_SEBELUM, SUB.REKOD_LABEL, "+
		    					" TO_CHAR (SUB.TARIKH_MASUK, "+
		    					" 'DD/MM/YYYY hh24:mi:ss' "+
		    					" ) AS TARIKH_MASUK, SUB.ID_MASUK, 2 AS LAYER "+
		    					" FROM TBLSEJARAHWTRMSUB SUB, TBLSEJARAHWTRMUTAMA UTAMA "+
		    					" WHERE SUB.ID_SEJARAHWTRMUTAMA = UTAMA.ID_SEJARAHWTRMUTAMA    ";
		    			sql += filter;
		    			sql +=	" ORDER BY TARIKH_MASUK DESC, "+
		    					" ID_SEJARAHWTRMUTAMA ASC, "+
		    					" LAYER ASC, "+
		    					" REKOD_LABEL ASC ";
		    		}
		    		*/
		    		myLogger.info(" V3: SQL listWARTATRMHISTORY ["+TYPE+"] :"+ sql);			
		    		rs = stmt.executeQuery(sql);
		    		listWARTATRMHISTORY = Collections.synchronizedList(new ArrayList());
		    		Map h = null;
		    		int bil = 0;
		    		int bil_sub = 0;
		    		while (rs.next()) {
		    			h = Collections.synchronizedMap(new HashMap());
		    			String bil_temp = "";
		    			if(TYPE.equals("LAPORAN"))
		    			{
		    				if(rs.getString("LAYER").equals("1"))
		    				{
		    					bil++;	
		    					bil_temp = bil+"";
		    					bil_sub = 0;
		    				}
		    				else
		    				{
		    					bil_sub++;
		    					bil_temp = bil + "." +bil_sub+"";
		    				}
		    			}
		    			else
		    			{
		    				bil++;
		    				bil_temp = bil+"";
		    			}
		    			
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
		    			h.put("BIL",bil_temp);
		    			h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
		    			h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
		    			h.put("AKTIVITI",rs.getString("AKTIVITI") == null ? "" : rs.getString("AKTIVITI"));
		    			h.put("ID_SEJARAHWTRMSUB",rs.getString("ID_SEJARAHWTRMSUB") == null ? "" : rs.getString("ID_SEJARAHWTRMSUB"));
		    			h.put("ID_SEJARAHWTRMUTAMA",rs.getString("ID_SEJARAHWTRMUTAMA") == null ? "" : rs.getString("ID_SEJARAHWTRMUTAMA"));
		    			h.put("REKOD_SELEPAS",rs.getString("REKOD_SELEPAS") == null ? "" : rs.getString("REKOD_SELEPAS"));
		    			h.put("REKOD_SEBELUM",rs.getString("REKOD_SEBELUM") == null ? "" : rs.getString("REKOD_SEBELUM"));
		    			h.put("REKOD_LABEL",rs.getString("REKOD_LABEL") == null ? "" : rs.getString("REKOD_LABEL"));
		    			h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));	
		    			h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));	
		    			h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));	
		    			listWARTATRMHISTORY.add(h);
		    		}

		    	} finally {
		    		if(db==null)
		    		{
			    		if (rs != null)
			    			rs.close();
			    		if (stmt != null)
			    			stmt.close();
			    		if (db1 != null)
			    			db1.close();
		    		}
		    	}
		    	return listWARTATRMHISTORY;

		    }
		   
		    
		    public void deleteFloatingDoc(Db db) throws Exception {
		    	//Connection conn = null;
		    	Db db1 = null;
		    	
		    	String sql = "";
		    	String sql_condition = "";
		    	try {
		    		if(db==null)
			    	{
			    		db1 = new Db();
			    	}
			    	else
			    	{
			    		db1 = db;
			    	}
		    		//conn = db1.getConnection();
		    		//conn.setAutoCommit(false);				
		    		Statement stmt = db1.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		
		    		sql = "DELETE FROM TBLPDTWARTATRM WHERE ID_WARTATRM  IN (SELECT CW.ID_WARTATRM FROM TBLPDTWARTATRM CW "+
		    				" WHERE CW.NO_WARTA IS NULL AND  ((SYSDATE - CW.TARIKH_MASUK) * 24 * 60) > 120) ";
		    		myLogger.info("DELETE  TBLPDTWARTATRM : "+sql);
		    		stmt.executeUpdate(sql);
		    		
		    		//conn.commit();
		    	} catch (Exception re) {
		    		throw re;
		    	}finally {
		    		if(db==null)
		    		{
			    		if (db1 != null)
			    			db1.close();
		    		}
		    	}
		    }

		    
		    	
		    public void deleteHISTORY(String ID_WARTATRM,Db db) throws Exception {
		    	//Connection conn = null;
		    	Db db1 = null;
		    	
		    	String sql = "";
		    	String sql_condition = "";
		    	try {
		    		if(db==null)
			    	{
			    		db1 = new Db();
			    	}
			    	else
			    	{
			    		db1 = db;
			    	}
		    		//conn = db1.getConnection();
		    		//conn.setAutoCommit(false);				
		    		Statement stmt = db1.getStatement();
		    		SQLRenderer r = new SQLRenderer();
		    		
		    		sql_condition = " SELECT ID_SEJARAHWTRMUTAMA FROM "+
		    				" (SELECT ID_SEJARAHWTRMUTAMA, TARIKH_MASUK, ROWNUM R "+
		    				" FROM ( "+
		    				" SELECT ID_SEJARAHWTRMUTAMA, TARIKH_MASUK FROM TBLSEJARAHWTRMUTAMA WHERE ID_WARTATRM = '"+ID_WARTATRM+"'  "+
		    				" ORDER BY TARIKH_MASUK DESC "+
		    				" ) K ) RW "+
		    				" WHERE RW.R > 50 ";
		    		
		    		
		    		
		    		sql = "DELETE FROM TBLSEJARAHWTRMSUB WHERE ID_SEJARAHWTRMUTAMA IN ("+sql_condition+")";
		    		myLogger.info("DELETE  TBLSEJARAHWTRMSUB : "+sql);
		    		stmt.executeUpdate(sql);
		    		
		    		sql = "DELETE FROM TBLSEJARAHWTRMUTAMA WHERE ID_SEJARAHWTRMUTAMA IN ("+sql_condition+")";
		    		myLogger.info("DELETE  TBLSEJARAHWTRMSUB : "+sql);
		    		stmt.executeUpdate(sql);
		    		
		    		
		    		//conn.commit();
		    	} catch (Exception re) {
		    		throw re;
		    	}finally {
		    		if(db==null)
		    		{
			    		if (db1 != null)
			    			db1.close();
		    		}
		    	}
		    }

		   
		    public int getHISTORYCount(HttpSession session, String ID_WARTATRM,Db db) throws Exception {
				Db db1 = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db1.getStatement();
					int cnt = 0;
					
					sql = " SELECT COUNT(*) AS CNT FROM TBLSEJARAHWTRMUTAMA A WHERE A.ID_WARTATRM = "+ID_WARTATRM+" ";
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {
							cnt = rs.getString("CNT") == null ? 0 : rs.getInt("CNT");
						}
					
					return cnt;
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
			}
		    
		    
		    public String getIDWARTAASAL(HttpSession session, String ID_WARTATRMSUB,Db db) throws Exception {
				Db db1 = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				String ID_WARTATRMMST = "";
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db1.getStatement();
					
					
					sql = " SELECT BG.ID_WARTATRMMST FROM TBLPDTWARTATRMBATALGANTI BG "+
							" WHERE BG.ID_WARTATRMBATALGANTI IS NOT NULL AND BG.ID_WARTATRMSUB = '"+ID_WARTATRMSUB+"' ";
						myLogger.info("getIDWARTAASAL  :"+sql);
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {
							ID_WARTATRMMST = rs.getString("ID_WARTATRMMST") == null ? "" : rs.getString("ID_WARTATRMMST");
						}					
					
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return ID_WARTATRMMST;
			}
		    
		    
		    public void checkForHistory(HttpSession session,String ID_WARTATRM, Map ASAL, Map BARU, String AKTIVITI, Db db)
					throws Exception {
				
				myLogger.info(" *****checkForHistory ID_WARTATRM : "+ID_WARTATRM+" ASAL : "+ASAL+" BARU : "+BARU +" AKTIVITI :"+AKTIVITI);	
				
				//LAST_CHANGEPASSWORD
				//DAYS_AFTERLASTLOGIN
				
				
				Integer count_ada_changes = 0;
				Set<String> keys_check = BARU.keySet();
				//myLogger.info(" keys_check BARU : "+keys_check);
				for(String key_c: keys_check){
					//myLogger.info(" key : "+key_c);
					String rekod_asal = (String) ASAL.get(key_c).toString();
					String rekod_baru = (String) BARU.get(key_c).toString();
				   
					//myLogger.info("all rekod_asal : "+rekod_asal+" rekod_baru : "+rekod_baru);
					if(!rekod_asal.equals(rekod_baru) 
							&& !key_c.toUpperCase().contains("ID_")
							&& !key_c.toUpperCase().contains("_ID")
							&& !key_c.toUpperCase().contains("FLAG")
							&& !key_c.toUpperCase().contains("JENIS_MIME")
							&& !key_c.toUpperCase().equals("LUAS_BATAL")
							&& !key_c.toUpperCase().equals("LUAS")
							&& !key_c.toUpperCase().equals("LUAS_GANTI")
							&& !key_c.toUpperCase().equals("KOSONG")
							)
					{
						myLogger.info("detect rekod_asal : "+rekod_asal+" rekod_baru : "+rekod_baru);
						count_ada_changes++;
					}
				}
				
				
				if(count_ada_changes>0)
				{
					String id_history_utama = saveHistoryUtama(session,ID_WARTATRM,AKTIVITI,db);		
					Set<String> keys = BARU.keySet();
					for(String key: keys){
						//myLogger.info(" key : "+key);
						String rekod_asal = (String) ASAL.get(key).toString();
						String rekod_baru = (String) BARU.get(key).toString();
						if(!rekod_asal.equals(rekod_baru) 
								&& !key.toUpperCase().contains("ID_")
								&& !key.toUpperCase().contains("_ID")
								&& !key.toUpperCase().contains("FLAG")
								&& !key.toUpperCase().contains("JENIS_MIME")
								&& !key.toUpperCase().equals("LUAS_BATAL")
								&& !key.toUpperCase().equals("LUAS")
								&& !key.toUpperCase().equals("LUAS_GANTI")
								&& !key.toUpperCase().equals("KOSONG")
								)
						{
							myLogger.info(" **** ADA PERUBAHAN -> ID_WARTATRM : "+ID_WARTATRM+" AKTIVITI :"+AKTIVITI+" key : "+key+" ASAL : "+rekod_asal+" BARU : "+rekod_baru);
//							key = key.replace("_PEJ", "_PEJABAT");
//							key = key.replace("ROLE_MAIN", "ROLE ID");
//							key = key.replace("ROLE_MAIN_DETAILS", "ROLE NAME");
							key = key.replace("JENISWARTA", "JENIS WARTA");
							key = key.replace("STATUSWARTA", "STATUS WARTA");
							key = key.replace("_DISPLAY", "");
							key = key.replace("_", " ");
							key = key.replace("KOSONG", "LUAS TIDAK DIGANTI");
							saveHistorySub(session,id_history_utama,rekod_asal,rekod_baru,key,db);
						}
					}
				}
				
				deleteHISTORY(ID_WARTATRM,db);
			}
			
			
			public String saveHistoryUtama(HttpSession session,String ID_WARTATRM,String AKTIVITI,Db db) throws Exception {
				//Connection conn = null;
				Db db1 = null;
				String ID_UTAMA = "";
				String sql = "";
				long id_historyutama = 0;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					//conn = db.getConnection();
					//conn.setAutoCommit(false);
					
					
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					id_historyutama = DB.getNextID(db1, "TBLSEJARAHWTRMUTAMA_SEQ");
					ID_UTAMA = id_historyutama+"";
					r.add("ID_SEJARAHWTRMUTAMA", id_historyutama);	
					r.add("JENIS_AKTIVITI", AKTIVITI);
					r.add("ID_WARTATRM", ID_WARTATRM);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLSEJARAHWTRMUTAMA",db1);		
					myLogger.info("V3 : INSERT TBLSEJARAHWTRMUTAMA : "+sql);				
					stmt.executeUpdate(sql);
					//conn.commit();ID_SEJARAHWTRMUTAMA
					
				}/* 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
				}*/
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
				return ID_UTAMA;
			}
			
			
			public String saveHistorySub(HttpSession session,String ID_SEJARAHMAIN,String ASAL,String BARU,String LABEL,Db db) throws Exception {
				//Connection conn = null;
				Db db1 = null;
				String ID_SUB = "";
				String sql = "";
				long id_historysub = 0;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					//conn = db.getConnection();
					//conn.setAutoCommit(false);
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					id_historysub = DB.getNextID(db1, "TBLSEJARAHWTRMSUB_SEQ");
					ID_SUB = id_historysub+"";
					r.add("ID_SEJARAHWTRMSUB", id_historysub);
					r.add("ID_SEJARAHWTRMUTAMA", ID_SEJARAHMAIN);
					r.add("REKOD_SEBELUM", ASAL);
					r.add("REKOD_SELEPAS", BARU);
					r.add("REKOD_LABEL", LABEL);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLSEJARAHWTRMSUB",db1);		
					myLogger.info("V3 : INSERT TBLSEJARAHWTRMSUB : "+sql);				
					stmt.executeUpdate(sql);
					//conn.commit();
					
				}/* 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
				}*/
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
				return ID_SUB;
			}
		    
			
			@SuppressWarnings("unchecked")
			public boolean checkDuplicateWarta(HttpSession session,String ID_WARTATRM,String NO_WARTA)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				boolean checkDuplicateWarta = false;
				String sql = "";				
				try {
					db = new Db();
					stmt = db.getStatement();
					sql = " SELECT COUNT(T.ID_WARTATRM) AS CNT FROM TBLPDTWARTATRM T  " +
							" WHERE T.ID_WARTATRM IS NOT NULL ";				
							if(!ID_WARTATRM.equals(""))
							{
								sql += "  AND NVL(T.ID_WARTATRM,0) <> '"+ID_WARTATRM+"'  ";
							}
							sql += " AND UPPER(T.NO_WARTA) = '"+replace(NO_WARTA.toUpperCase())+"' ";					
					myLogger.info(" SQL : checkDuplicateWarta :"+ sql);			
					rs = stmt.executeQuery(sql);					
					while (rs.next()) {						
						if(rs.getInt("CNT")>0)
						{
							checkDuplicateWarta = true;
						}
					}
				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return checkDuplicateWarta;
			}
		    
			public String replace(String s)
		    {
		        return dblch(s, '\'');
		    }
			public String dblch(String s, char c)
		    {
		        StringBuffer stringbuffer = new StringBuffer("");
		        if(s != null)
		        {
		            for(int i = 0; i < s.length(); i++)
		            {
		                char c1 = s.charAt(i);
		                if(c1 == c)
		                    stringbuffer.append(c).append(c);
		                else
		                    stringbuffer.append(String.valueOf(c1));
		            }

		        } else
		        {
		            stringbuffer.append("");
		        }
		        return stringbuffer.toString();
		    }
		    
			
			
			
			@SuppressWarnings("unchecked")
			public boolean checkTahunNegeriLaporan(HttpSession session,int tahun,long id_negeri,Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				boolean checkTahunNegeriLaporan = false;
				String sql = "";				
				try {
					if(db==null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					stmt = db1.getStatement();
					sql = " SELECT COUNT(T.ID_TRMREPTAHUNNEGERI) AS CNT FROM TBLPDTTRMREPTAHUNNEGERI T  " +
							" WHERE T.ID_TRMREPTAHUNNEGERI IS NOT NULL AND TAHUN = '"+tahun+"' AND ID_NEGERI = '"+id_negeri+"' ";											
					//myLogger.info(" SQL : checkTahunNegeriLaporan :"+ sql);			
					rs = stmt.executeQuery(sql);					
					while (rs.next()) {						
						if(rs.getInt("CNT")>0)
						{
							checkTahunNegeriLaporan = true;
						}
					}
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return checkTahunNegeriLaporan;
			}
			
			
			public String saveLaporanTahunNegeri(HttpSession session,String ID_TRMREPTAHUNNEGERI,
					String TAHUN,String ID_NEGERI,String LUAS,Db db) throws Exception {
				//Connection conn = null;
				Db db1 = null;
				String ID_SUB = "";
				String sql = "";
				long long_ID_TRMREPTAHUNNEGERI = 0;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				//myLogger.info("ID_TRMREPTAHUNNEGERI : "+ID_TRMREPTAHUNNEGERI);
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					//conn = db.getConnection();
					//conn.setAutoCommit(false);
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					if(ID_TRMREPTAHUNNEGERI.equals(""))
					{
						long_ID_TRMREPTAHUNNEGERI = DB.getNextID(db1, "TBLPDTTRMREPTAHUNNEGERI_SEQ");
						//myLogger.info("long_ID_TRMREPTAHUNNEGERI : "+long_ID_TRMREPTAHUNNEGERI);
						//ID_TRMREPTAHUNNEGERI = long_ID_TRMREPTAHUNNEGERI+"";
						r.add("ID_TRMREPTAHUNNEGERI", long_ID_TRMREPTAHUNNEGERI);
						r.add("LUAS", 0);
						r.add("TAHUN", TAHUN);
						r.add("ID_NEGERI", ID_NEGERI);
					}
					else
					{
						r.update("ID_TRMREPTAHUNNEGERI", ID_TRMREPTAHUNNEGERI);
						r.add("LUAS", LUAS);
					}
					
					
					
					
					
					if(ID_TRMREPTAHUNNEGERI.equals(""))
					{
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLPDTTRMREPTAHUNNEGERI",db1);		
					}
					else
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						sql = r.getSQLUpdate("TBLPDTTRMREPTAHUNNEGERI",db1);		
					}
					
					//myLogger.info("INSERT/UPDATE TBLPDTTRMREPTAHUNNEGERI : "+sql);				
					stmt.executeUpdate(sql);
					//conn.commit();
					
				}/* 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
				}*/
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
				return ID_SUB;
			}
			
			
			
			
			public void deletePilihan(HttpSession session,Db db) throws Exception {
				//Connection conn = null;
				Db db1 = null;
				//String ID_SUB = "";
				String sql = "";
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					//conn = db.getConnection();
					//conn.setAutoCommit(false);
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					//r.add("TAHUN", TAHUN);
					//sql = r.getSQLInsert("TBLPDTTRMPILIHANTAHUN",db1);		
					
					r.clear();
					//r.add("ID_WARTATRM",ID_WARTATRM);
					//sql = r.getSQLDelete("TBLPDTTRMPILIHANTAHUN");
					stmt.executeUpdate("DELETE FROM TBLPDTTRMPILIHANTAHUN");
					
					//myLogger.info("INSERT/UPDATE TBLPDTTRMREPTAHUNNEGERI : "+sql);				
					//stmt.executeUpdate(sql);
					//conn.commit();
					
				}
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
				//return ID_SUB;
			}
			
			public String insertPilihanTahun(HttpSession session,String TAHUN, Db db) throws Exception {
				//Connection conn = null;
				Db db1 = null;
				String ID_SUB = "";
				String sql = "";
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					//conn = db.getConnection();
					//conn.setAutoCommit(false);
					Statement stmt = db1.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("TAHUN", TAHUN);
					sql = r.getSQLInsert("TBLPDTTRMPILIHANTAHUN",db1);		
					
					
					myLogger.info("INSERT TBLPDTTRMPILIHANTAHUN : "+sql);				
					stmt.executeUpdate(sql);
					//conn.commit();
					
				}
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
				return ID_SUB;
			}
			
			
			
			public String reganerateByYearTarikData(HttpSession session,String TAHUN,String ID_NEGERI,Db db) throws Exception {
				//Connection conn = null;
				Db db1 = null;
				String ID_SUB = "";
				String sql = "";
				long long_ID_TRMREPTAHUNNEGERI = 0;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				//myLogger.info("ID_TRMREPTAHUNNEGERI : "+ID_TRMREPTAHUNNEGERI);
				try {
					if(db == null)
					{
						db1 = new Db();
					}
					else
					{
						db1 = db;
					}
					//conn = db.getConnection();
					//conn.setAutoCommit(false);
					Statement stmt = db1.getStatement();
					sql = " UPDATE TBLPDTTRMREPTAHUNNEGERI REP SET REP.LUAS = "+							
							" NVL(" +
							"(" +
							/*
							"SELECT SUM(W.LUAS) FROM TBLPDTWARTATRM W WHERE FLAG_JENISWARTA = 'W' " +
							" AND TO_CHAR(W.TARIKH_WARTA,'YYYY') <= '"+TAHUN+"' " +
									" AND ID_NEGERI = '"+ID_NEGERI+"'" +
									*/
							" SELECT SUM(LUAS) FROM ( "+
							" SELECT "+
							/*
							(W.LUAS + NVL(REF_LUAS_ASAL.TOTAL_BATAL,0) - NVL(REF_LUAS_ASAL.TOTAL_GANTI,0)),
							NVL(REF_BG.TOTAL_BATAL,0), NVL(REF_BG.TOTAL_GANTI,0)
							*/
							" NVL((W.LUAS + NVL(REF_LUAS_ASAL.TOTAL_BATAL,0) - NVL(REF_LUAS_ASAL.TOTAL_GANTI,0)) - NVL(REF_BG.TOTAL_BATAL,0) +  NVL(REF_BG.TOTAL_GANTI,0),0) AS LUAS "+
							" FROM TBLPDTWARTATRM W, "+
							" (SELECT BG.ID_WARTATRMMST, "+
							" NVL(SUM(CASE WHEN W.FLAG_JENISWARTA = 'B' THEN NVL(W.LUAS,0) END),0) AS TOTAL_BATAL, "+
							" NVL(SUM(CASE WHEN W.FLAG_JENISWARTA = 'G' THEN NVL(W.LUAS,0) END),0) AS TOTAL_GANTI "+
							" FROM TBLPDTWARTATRM W, TBLPDTWARTATRMBATALGANTI BG "+
							" WHERE W.ID_WARTATRM = BG.ID_WARTATRMSUB "+
							" AND TO_CHAR (W.TARIKH_WARTA, 'YYYY') <= '"+TAHUN+"' "+
							" GROUP BY BG.ID_WARTATRMMST) REF_BG, "+
							" (SELECT BG.ID_WARTATRMMST, "+
							" NVL(SUM(CASE WHEN W.FLAG_JENISWARTA = 'B' THEN NVL(W.LUAS,0) END),0) AS TOTAL_BATAL, "+
							" NVL(SUM(CASE WHEN W.FLAG_JENISWARTA = 'G' THEN NVL(W.LUAS,0) END),0) AS TOTAL_GANTI "+
							" FROM TBLPDTWARTATRM W, TBLPDTWARTATRMBATALGANTI BG "+
							" WHERE W.ID_WARTATRM = BG.ID_WARTATRMSUB "+
							" GROUP BY BG.ID_WARTATRMMST) REF_LUAS_ASAL "+
							" WHERE   W.FLAG_JENISWARTA = 'W' "+
							" AND W.ID_WARTATRM = REF_BG.ID_WARTATRMMST(+) "+
							" AND W.ID_WARTATRM = REF_LUAS_ASAL.ID_WARTATRMMST(+) "+
							" AND TO_CHAR (W.TARIKH_WARTA, 'YYYY') <= '"+TAHUN+"' "+
							" AND ID_NEGERI = '"+ID_NEGERI+"' "+
							" ) "+
							" ) "+
							" ,0) "+
							
									
							" WHERE REP.TAHUN = '"+TAHUN+"' AND REP.ID_NEGERI = '"+ID_NEGERI+"' ";
					myLogger.info("MYLOGGER reganerateByYearTarikData : "+sql);
					stmt.executeUpdate(sql);
					
					
					
				}/* 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
				}*/
				catch (Exception re) {
					throw re;
				}finally {
					if(db==null)
					{
						if (db1 != null)
							db1.close();
					}
				}
				return ID_SUB;
			}
			
			
			
			@SuppressWarnings("unchecked")
			public List listREPORTTAHUNNEGERI(HttpSession session,String ID_NEGERI, String TAHUN, Db db)throws Exception {
				Db db1 = null;
				
				ResultSet rs = null;
				Statement stmt = null;
				List listREPORTTAHUNNEGERI = null;
				String sql = "";
				
				try {
					if(db==null)
					{
					db1 = new Db();
					}
					else
					{
						db1 = db;
					}			
					stmt = db1.getStatement();
					/*
					sql = " SELECT UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, R.ID_TRMREPTAHUNNEGERI, R.TAHUN, R.ID_NEGERI, TO_CHAR (R.LUAS, '99999999999990.9999') AS LUAS, " +
							" TO_CHAR (R.LUAS, '9,999,999,990.9999') AS LUAS_DISPLAY " +
							" FROM TBLPDTTRMREPTAHUNNEGERI R, TBLRUJNEGERI N ";					
					sql += " WHERE " +
							//" R.TAHUN >= "+TAHUN_MULA+" AND R.TAHUN <= "+TAHUN_AKHIR+" ";
						   " R.TAHUN IN ("+TAHUN+") ";
					sql += " AND R.ID_NEGERI IN ("+ID_NEGERI+") AND R.ID_NEGERI = N.ID_NEGERI  " ;					
				    sql += " ORDER BY R.TAHUN DESC,R.ID_NEGERI ASC ";
				    */				    
				    sql = " SELECT UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, R.ID_TRMREPTAHUNNEGERI, R.TAHUN, R.ID_NEGERI, "+ 
				    		" TO_CHAR (R.LUAS, '99999999999990.9999') AS LUAS,  TO_CHAR (R.LUAS, '9,999,999,990.9999') AS LUAS_DISPLAY "+ 
				    		" FROM TBLPDTTRMREPTAHUNNEGERI R, TBLRUJNEGERI N  "+ 
				    		" WHERE  R.TAHUN IN ("+TAHUN+")  "+
				    		" AND R.ID_NEGERI IN ("+ID_NEGERI+") AND R.ID_NEGERI = N.ID_NEGERI  "+
				    		" UNION "+
				    		" SELECT 'JUMLAH' AS NAMA_NEGERI, NULL AS ID_TRMREPTAHUNNEGERI, TAHUN, 999999 AS ID_NEGERI, "+
				    		" TO_CHAR (TOTAL_LUAS, '99999999999990.9999') AS LUAS,  TO_CHAR (TOTAL_LUAS, '9,999,999,990.9999') AS LUAS_DISPLAY "+
				    		" FROM "+
				    		" (   "+
				    		" SELECT R.TAHUN, SUM(R.LUAS) AS TOTAL_LUAS "+
				    		" FROM TBLPDTTRMREPTAHUNNEGERI R, TBLRUJNEGERI N   "+
				    		" WHERE  R.TAHUN IN ("+TAHUN+")   "+
				    		" AND R.ID_NEGERI IN ("+ID_NEGERI+") AND R.ID_NEGERI = N.ID_NEGERI    "+
				    		" GROUP BY R.TAHUN "+
				    		" ) "+
				    		" ORDER BY TAHUN DESC,ID_NEGERI ASC  ";
				    
					myLogger.info("SQL listREPORTTAHUNNEGERI :"+ sql);
					rs = stmt.executeQuery(sql);
					listREPORTTAHUNNEGERI = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID_TRMREPTAHUNNEGERI",rs.getString("ID_TRMREPTAHUNNEGERI") == null ? "" : rs.getString("ID_TRMREPTAHUNNEGERI").toUpperCase());
						h.put("TAHUN",rs.getString("TAHUN") == null ? "" : rs.getString("TAHUN").toUpperCase());
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
						//BigDecimal b = new BigDecimal(Double.parseDouble(rs.getString("LUAS")));
						
						String LUAS = (String) (rs.getString("LUAS") == null ? "0": rs.getString("LUAS").toUpperCase());
						LUAS = LUAS.indexOf(".") < 0 ? LUAS : LUAS.replaceAll("0*$", "").replaceAll("\\.$", "");
						h.put("LUAS",LUAS.trim());
						
						String LUAS_FIELD = (String) (rs.getString("LUAS") == null ? "0": rs.getString("LUAS").toUpperCase());
						LUAS_FIELD = LUAS_FIELD.indexOf(".") < 0 ? LUAS_FIELD : LUAS_FIELD.replaceAll("0*$", "").replaceAll("\\.$", "");
						h.put("LUAS_FIELD",LUAS_FIELD.trim());
						
						String LUAS_DISPLAY = (String) (rs.getString("LUAS_DISPLAY") == null ? "0": rs.getString("LUAS_DISPLAY").toUpperCase());
						LUAS_DISPLAY = LUAS_DISPLAY.indexOf(".") < 0 ? LUAS_DISPLAY : LUAS_DISPLAY.replaceAll("0*$", "").replaceAll("\\.$", "");
						h.put("LUAS_DISPLAY",rs.getString("LUAS_DISPLAY") == null ? "" : LUAS_DISPLAY);
						
						h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
						listREPORTTAHUNNEGERI.add(h);
					}
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return listREPORTTAHUNNEGERI;
			}
			
			
			@SuppressWarnings("unchecked")
			public List listREPORTNEGERI(HttpSession session,String ID_NEGERI, Db db)throws Exception {
				Db db1 = null;
				
				ResultSet rs = null;
				Statement stmt = null;
				List listREPORTNEGERI = null;
				String sql = "";
				
				try {
					if(db==null)
					{
					db1 = new Db();
					}
					else
					{
						db1 = db;
					}			
					stmt = db1.getStatement();
					sql = " SELECT N.ID_NEGERI, N.KOD_NEGERI, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI FROM TBLRUJNEGERI N ";					
					sql += " WHERE N.ID_NEGERI IN ("+ID_NEGERI+") AND N.ID_NEGERI NOT IN (0,17,15,12,13) " ;					
				    sql += " ORDER BY N.ID_NEGERI ASC ";			
					myLogger.info("SQL listREPORTNEGERI :"+ sql);
					rs = stmt.executeQuery(sql);
					listREPORTNEGERI = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
						h.put("KOD_NEGERI",rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI").toUpperCase());
						h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase().replace("WILAYAH PERSEKUTUAN ", ""));
						listREPORTNEGERI.add(h);
					}
					
					
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					h.put("BIL",bil);
					h.put("ID_NEGERI","999999");
					h.put("KOD_NEGERI","999999");
					h.put("NAMA_NEGERI","JUMLAH KELUASAN");
					listREPORTNEGERI.add(h);
					
					
				} finally {
					if(db==null)
					{
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db1 != null)
							db1.close();
					}
				}
				return listREPORTNEGERI;
			}
}