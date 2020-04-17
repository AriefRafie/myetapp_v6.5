package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

public class FrmMesyuarat extends AjaxBasedModule {

	/**
	 * razman revamp mesyuarat
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH="app/pdt/mesyuarat/";
	static Logger myLogger = Logger.getLogger(FrmSenaraiDokumen.class);
	
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		String action = getParam("action");
		/*
		String FrmSenaraiDokumenOnLoad = (String) session.getAttribute("FrmSenaraiDokumenOnLoad");
		if(FrmSenaraiDokumenOnLoad==null)
		{
		session.setAttribute("FrmSenaraiDokumenOnLoad", "Y");
		}
		*/
		myLogger.info(" command : "+command+" action : "+action);
		
		String vm = PATH+"index.jsp";
		
		List listMesyuaratUtama = null;
		int ShowDalamTindakanContent = 0;
		int ShowDalamTindakanMain = 0;
		List listFolderSub = null;
		List listEmel = null;
		List listTelPIC = null;
		List listPIC = null;
		List listEmelPIC = null;
		List listBahagian = null;
		List listFolderTindakan = null;
		List listFolderLampiran = null;
		List listSubByID_REFER = null;
		Hashtable viewTajukUtama = null;
		Hashtable viewSubFoler = null;
		Hashtable viewMainFoler = null;
		Hashtable viewTindakan = null;
		Hashtable viewLampiran = null;
		
		defaultPut();
		String USER_ROLE = (String) session.getAttribute("myrole");
		this.context.put("USER_ROLE",USER_ROLE);
		myLogger.info("USER_ROLE : "+USER_ROLE);
		
		String FLAG_EDIT = "";
		String ID_BAHAGIANMESYUARAT = "";
		if(USER_ROLE.equals("(PDT) Pengguna Bahagian Pengurusan Perundangan Tanah")
		|| USER_ROLE.equals("(PDT)HQPengguna") || USER_ROLE.equals("meps") || USER_ROLE.equals("adminpdt"))
		{
			ID_BAHAGIANMESYUARAT = "14";
			if(USER_ROLE.equals("(PDT) Pengguna Bahagian Pengurusan Perundangan Tanah"))
			{
				FLAG_EDIT = "Y";
			}
		}
		else if(USER_ROLE.toUpperCase().contains("(STRATA)"))
		{
			ID_BAHAGIANMESYUARAT = "12";
			FLAG_EDIT = "Y";
		}
		this.context.put("FLAG_EDIT",FLAG_EDIT);	
		this.context.put("ID_BAHAGIANMESYUARAT",ID_BAHAGIANMESYUARAT);	
		
		if(command.equals("carianUtama"))
		{
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);			
			String carianMesyuarat = getParam("carianMesyuarat");
			this.context.put("carianMesyuarat", carianMesyuarat);
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);
			String carianBahagian = getParam("carianBahagian");
			this.context.put("carianBahagian", carianBahagian);
			String carianLampiran = getParam("carianLampiran");
			this.context.put("carianLampiran", carianLampiran);
			String carianTahun = getParam("carianTahun");
			this.context.put("carianTahun", carianTahun);
			String carianBilangan = getParam("carianBilangan");
			this.context.put("carianBilangan", carianBilangan);
			String carianStatus = getParam("carianStatus");
			this.context.put("carianStatus", carianStatus);
			String carianPIC = getParam("carianPIC");
			this.context.put("carianPIC", carianPIC);
			String carianStatusMesyuarat = getParam("carianStatusMesyuarat");
			this.context.put("carianStatusMesyuarat", carianStatusMesyuarat);
			String FLAG_AFTERINSERT = getParam("FLAG_AFTERINSERT");
			this.context.put("FLAG_AFTERINSERT", FLAG_AFTERINSERT);
			String FLAG_AUTOLOADSUB = getParam("FLAG_AUTOLOADSUB");
			this.context.put("FLAG_AUTOLOADSUB", FLAG_AUTOLOADSUB);
			
			myLogger.info(command + " : FLAG_AUTOLOADSUB : "+FLAG_AUTOLOADSUB+" carianMesyuarat : "+carianMesyuarat+" carianTahun : "+carianTahun+" carianBilangan : "+carianBilangan);
			
			listMesyuaratUtama = listMesyuaratUtama(session, carianTerperinci,carianBahagian,carianMesyuarat,carianLampiran,carianTahun,carianBilangan,carianStatus,carianStatusMesyuarat,carianPIC,FLAG_AUTOLOADSUB,ID_BAHAGIANMESYUARAT);
			setupPageList(session, action, listMesyuaratUtama, "listMesyuaratUtama",command,"div_senaraiUtama");			
			vm = "app/pdt/mesyuarat/SenaraiUtama.jsp";			
		}	
		else if(command.equals("countSubFolder"))
		{
			this.context.put("ID_MESYUARATUTAMA", getParam("ID_MESYUARATUTAMA"));
			this.context.put("ID_MESYUARATCONTENT", getParam("ID_MESYUARATCONTENT"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_MESYUARATUTAMA")));
			this.context.put("TOTAL_SUB", getParam("TOTAL_SUB"));
			this.context.put("FLAG_SUB_OPENCLOSE", getParam("FLAG_SUB_OPENCLOSE"));
			vm = "app/pdt/mesyuarat/CountSubFolder.jsp";
		}
		else if(command.equals("countTindakan"))
		{
			myLogger.info("GET TOTAL TINDAKAN : "+ getParam("TOTAL_TINDAKAN"));
			this.context.put("ID_MESYUARATUTAMA", getParam("ID_MESYUARATUTAMA"));
			this.context.put("ID_MESYUARATCONTENT", getParam("ID_MESYUARATCONTENT"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_MESYUARATUTAMA")));
			this.context.put("TOTAL_TINDAKAN", getParam("TOTAL_TINDAKAN"));
			this.context.put("FLAG_TINDAKAN_OPENCLOSE", getParam("FLAG_TINDAKAN_OPENCLOSE"));
			vm = "app/pdt/mesyuarat/CountTindakan.jsp";
		}
		else if(command.equals("showTindakan") || command.equals("emelTindakanContent") || command.equals("deleteTindakan"))
		{
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String AUTOLOAD = getParam("AUTOLOAD");		
			this.context.put("AUTOLOAD", AUTOLOAD);								
			String FLAG_TINDAKAN_OPENCLOSE = getParam("FLAG_TINDAKAN_OPENCLOSE");
			myLogger.info(" FLAG_TINDAKAN_OPENCLOSE : "+FLAG_TINDAKAN_OPENCLOSE+" AUTOLOAD :"+AUTOLOAD);
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);	
			String carianBahagian = getParam("carianBahagian");
			this.context.put("carianBahagian", carianBahagian);
			String carianStatus = getParam("carianStatus");
			this.context.put("carianStatus", carianStatus);
			
			if(command.equals("deleteTindakan"))
			{
				//delete function
				String ID_MESYUARATTINDAKAN = getParam("ID_MESYUARATTINDAKAN");
				String NAMA_DOC = getParam("NAMA_DOC");
				deleteTindakan(session,ID_MESYUARATTINDAKAN,NAMA_DOC);
			}
			else if(command.equals("emelTindakanContent"))
			{
				hantarEmel(session,ID_MESYUARATUTAMA,ID_MESYUARATCONTENT,"", "byContent");
			}			
			
			this.context.put("MAX_LAYER", getMaxLayer(session, ID_MESYUARATUTAMA));
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_TINDAKAN_OPENCLOSE", "OPEN");
				listFolderTindakan = listFolderTindakan(session, ID_MESYUARATUTAMA, ID_MESYUARATCONTENT, carianTerperinci,carianBahagian,carianStatus);
				this.context.put("listFolderTindakan", listFolderTindakan);	
				vm = "app/pdt/mesyuarat/SenaraiTindakan.jsp";				
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_TINDAKAN_OPENCLOSE.equals("OPEN"))
				{
					//this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					this.context.put("JUMLAH_TINDAKAN", getParam("JUMLAH_TINDAKAN"));
					this.context.put("FLAG_TINDAKAN_OPENCLOSE", "CLOSE");
					vm = "app/pdt/mesyuarat/blank_tindakanFolder.jsp";
				}
				else
				{
					if(FLAG_TINDAKAN_OPENCLOSE.equals("CLOSE"))
					{
						this.context.put("FLAG_TINDAKAN_OPENCLOSE", "OPEN");						
					}
					listFolderTindakan = listFolderTindakan(session, ID_MESYUARATUTAMA, ID_MESYUARATCONTENT, "","","");
					this.context.put("listFolderTindakan", listFolderTindakan);	
					vm = "app/pdt/mesyuarat/SenaraiTindakan.jsp";
				}
			}					
		}	
		//'addSubDir','ID_REFER=$ID_MESYUARATCONTENT&LAYER=$LFS.NEXTLAYER&ID_MESYUARATUTAMA=$LFS.ID_MESYUARATUTAMA');
		else if(command.equals("addSubDir"))
		{			
			this.context.put("ID_REFER", getParam("ID_REFER"));
			myLogger.info(" getParam(ID_REFER) : "+getParam("ID_REFER"));
			this.context.put("LAYER", getParam("LAYER"));
			this.context.put("ID_MESYUARATUTAMA", getParam("ID_MESYUARATUTAMA"));		
			vm = "app/pdt/mesyuarat/addSubDir.jsp";			
		}
		else if(command.equals("addTindakan"))
		{			
		
			this.context.put("ID_MESYUARATCONTENT", getParam("ID_MESYUARATCONTENT"));
			myLogger.info(" getParam(ID_MESYUARATCONTENT) : "+getParam("ID_MESYUARATCONTENT"));
			this.context.put("ID_MESYUARATUTAMA", getParam("ID_MESYUARATUTAMA"));
			this.context.put("ID_MESYUARATTINDAKAN", getParam("ID_MESYUARATTINDAKAN"));
			vm = "app/pdt/mesyuarat/addTindakan.jsp";			
		}
		else if(command.equals("ShowDalamTindakanContent"))
		{			
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			viewSubFoler = viewSubFoler(session,ID_MESYUARATCONTENT);
			this.context.put("viewSubFoler", viewSubFoler);
			this.context.put("DALAM_TINDAKAN", getCountDalamTindakan(session,ID_MESYUARATUTAMA,ID_MESYUARATCONTENT));		
			vm = "app/pdt/mesyuarat/ShowDalamTindakanContent.jsp";			
		}
		else if(command.equals("ShowDalamTindakanMain"))
		{			
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			viewMainFoler = viewTajukUtama(session,ID_MESYUARATUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			this.context.put("DALAM_TINDAKAN", getCountDalamTindakan(session, ID_MESYUARATUTAMA,""));		
			vm = "app/pdt/mesyuarat/ShowDalamTindakanMain.jsp";			
		}
		else if(command.equals("dropBahagian"))
		{			
		
			String SEARCH = getParam("SEARCH");
		    listBahagian = listBahagian(session, SEARCH, ID_BAHAGIANMESYUARAT);
		    this.context.put("listBahagian", listBahagian);
			vm = "app/pdt/mesyuarat/listBahagian.jsp";			
		}
		
		else if(command.equals("dropPIC"))
		{					
			String SEARCH = getParam("SEARCH");
		    listPIC = listPIC(session, SEARCH,ID_BAHAGIANMESYUARAT);
		    this.context.put("listPIC", listPIC);
			vm = "app/pdt/mesyuarat/listPIC.jsp";			
		}
		
		else if(command.equals("dropEmel") || command.equals("dropEmelPIC") || command.equals("dropEmelCC"))
		{	
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			String SEARCH = getParam("SEARCH");
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			String ID_MESYUARATTINDAKAN = getParam("ID_MESYUARATTINDAKAN");
			String BAHAGIAN = getParam("editBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
			String PIC = getParam("editPIC_"+ID_MESYUARATUTAMA);
			
			
			if(command.equals("dropEmel"))
			{
				String current_stored_emel = "";
				if(ID_MESYUARATTINDAKAN.equals(""))
				{
					current_stored_emel = getParam("editEmelBahagian_"+ID_MESYUARATCONTENT+"_X000000X");
				}
				else
				{
					current_stored_emel = getParam("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
				}
				listEmel = listEmel(session,SEARCH,BAHAGIAN,"",current_stored_emel,"TINDAKAN_TO",ID_BAHAGIANMESYUARAT);	
			}
			else if(command.equals("dropEmelCC"))
			{
				
				String current_stored_emel = "";
				if(ID_MESYUARATTINDAKAN.equals(""))
				{
					current_stored_emel = getParam("editEmelCCBahagian_"+ID_MESYUARATCONTENT+"_X000000X");
				}
				else
				{
					current_stored_emel = getParam("editEmelCCBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
				}
				myLogger.info(command +" --- > "+current_stored_emel);
				listEmel = listEmel(session,SEARCH,BAHAGIAN,"",current_stored_emel,"TINDAKAN_CC",ID_BAHAGIANMESYUARAT);	
			}
			else if(command.equals("dropEmelPIC"))
			{
				String current_stored_emel = "";
				if(ID_MESYUARATUTAMA.equals(""))
				{
					current_stored_emel = getParam("editEMEL_PIC_X000000X");
				}
				else
				{
					current_stored_emel = getParam("editEMEL_PIC_"+ID_MESYUARATUTAMA);
				}
				listEmel = listEmel(session,SEARCH,"",PIC,current_stored_emel,"UTAMA_PIC",ID_BAHAGIANMESYUARAT);				
			}
		    this.context.put("listEmel", listEmel);
		    
		    vm = "app/pdt/mesyuarat/listEmel.jsp";	
		    
		}		
		else if(command.equals("dropTelPIC"))
		{	
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			String SEARCH = getParam("SEARCH");
			String PIC = getParam("editPIC_"+ID_MESYUARATUTAMA);
			listTelPIC = listTelPIC(session,SEARCH,PIC,ID_BAHAGIANMESYUARAT);
			this.context.put("listTelPIC", listTelPIC);
		    vm = "app/pdt/mesyuarat/listTelPic.jsp";			    
		}
		else if(command.equals("editSubDir"))
		{
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("LAYER", getParam("LAYER"));
			viewSubFoler = viewSubFoler(session,ID_MESYUARATCONTENT);
			this.context.put("viewSubFoler", viewSubFoler);
			vm = "app/pdt/mesyuarat/editSubDir.jsp";			
		}
		else if(command.equals("editTindakan"))
		{
			String ID_MESYUARATTINDAKAN = getParam("ID_MESYUARATTINDAKAN");
			this.context.put("ID_MESYUARATTINDAKAN", ID_MESYUARATTINDAKAN);
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss", getParam("rowCss"));
			viewTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
			this.context.put("viewTindakan", viewTindakan);
			vm = "app/pdt/mesyuarat/editTindakan.jsp";			
		}		
		else if(command.equals("validateMainDir"))
		{
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String TAJUK = getParam("editMainDirField_"+ID_MESYUARATUTAMA);
			String TAHUN = getParam("editTahunField_"+ID_MESYUARATUTAMA);
			String BILANGAN = getParam("editBilanganField_"+ID_MESYUARATUTAMA);
			String duplicateMainDir = "N";
			
			
			if(checkDuplicateMainDir(session,ID_MESYUARATUTAMA,TAJUK,TAHUN,BILANGAN,ID_BAHAGIANMESYUARAT)==true)
			{
				duplicateMainDir = "Y";
			}
			this.context.put("duplicateMainDir", duplicateMainDir);
			vm = "app/pdt/mesyuarat/validateMainDir.jsp";			
		}	
		else if(command.equals("validateSubDir"))
		{
			String ID_MESYUARATTINDAKAN = getParam("ID_MESYUARATTINDAKAN");
			this.context.put("ID_MESYUARATTINDAKAN", ID_MESYUARATTINDAKAN);
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String TAJUK = getParam("TAJUK");
			String duplicateSubDir = "N";
			if(checkDuplicateSubDir(session,ID_REFER,ID_MESYUARATCONTENT,ID_MESYUARATUTAMA,TAJUK)==true)
			{
				duplicateSubDir = "Y";
			}
			this.context.put("duplicateSubDir", duplicateSubDir);
			vm = "app/pdt/mesyuarat/validateSubDir.jsp";			
		}	 
		else if(command.equals("validateTindakan"))
		{
			String ID_MESYUARATTINDAKAN = getParam("ID_MESYUARATTINDAKAN");
			this.context.put("ID_MESYUARATTINDAKAN", ID_MESYUARATTINDAKAN);
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String BAHAGIAN = getParam("BAHAGIAN");
			String duplicateName = "N";
			if(checkDuplicateBahagian(session,ID_MESYUARATTINDAKAN,ID_MESYUARATCONTENT,BAHAGIAN,ID_MESYUARATUTAMA)==true)
			{
				duplicateName = "Y";
			}
			this.context.put("duplicateName", duplicateName);
			vm = "app/pdt/mesyuarat/validateTindakan.jsp";			
		}	 
		else if(command.equals("editMainDir"))
		{
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("HID_OPENCLOSE", getParam("HID_OPENCLOSE_"+ID_MESYUARATUTAMA));
			viewMainFoler = viewTajukUtama(session,ID_MESYUARATUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			vm = "app/pdt/mesyuarat/editMainDir.jsp";			
		}	
		
		else if(command.equals("SimpanSubDir"))
		{
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			this.context.put("BIL", getParam("BIL"));
			String LAYER = getParam("LAYER");
			this.context.put("LAYER",LAYER);			
			String editSubDirField = getParam("editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_"+ID_MESYUARATCONTENT+"");
			myLogger.info("(editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_MESYUARATCONTENT+") :::: "+editSubDirField);
			saveUpdateSubDir(session,ID_REFER,ID_MESYUARATCONTENT,ID_MESYUARATUTAMA,editSubDirField);			
			viewSubFoler = viewSubFoler(session,ID_MESYUARATCONTENT);
			this.context.put("viewSubFoler", viewSubFoler);
			vm = "app/pdt/mesyuarat/viewSubDir.jsp";			
		}
		
		else if(command.equals("SimpanTindakan") || command.equals("emelTindakan"))
		{
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String ID_MESYUARATTINDAKAN = getParam("ID_MESYUARATTINDAKAN");
			this.context.put("ID_MESYUARATTINDAKAN", ID_MESYUARATTINDAKAN);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss", getParam("rowCss"));
			myLogger.info("**** AUTOLOAD : "+getParam("AUTOLOAD"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));
			String LAYER = getParam("LAYER");
			this.context.put("LAYER",LAYER);	
			
			if(command.equals("SimpanTindakan"))
			{
				String editBahagian = getParam("editBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
				String editEmelBahagian = "";
				String editEmelCCBahagian = "";
				
				if(ID_MESYUARATTINDAKAN.equals(""))
				{
					editEmelBahagian = getParam("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+"X000000X");
					editEmelCCBahagian = getParam("editEmelCCBahagian_"+ID_MESYUARATCONTENT+"_"+"X000000X");
				}else
				{
					editEmelBahagian = getParam("editEmelBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
					editEmelCCBahagian = getParam("editEmelCCBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
				}
				
				
				String editStatusBahagian = getParam("editStatusBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
				String editCatatanBahagian = getParam("editCatatanBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN);
				myLogger.info("(editBahagian_"+ID_MESYUARATCONTENT+"_"+ID_MESYUARATTINDAKAN+") :::: "+editBahagian);
				
				if(ID_MESYUARATTINDAKAN.equals(""))
				{
					saveInsertTindakan(session,ID_MESYUARATCONTENT,ID_MESYUARATUTAMA,editBahagian,editEmelBahagian,editEmelCCBahagian,editStatusBahagian,editCatatanBahagian,ID_BAHAGIANMESYUARAT);
					listFolderTindakan = listFolderTindakan(session, ID_MESYUARATUTAMA, ID_MESYUARATCONTENT, "","","");
					this.context.put("listFolderTindakan", listFolderTindakan);	
					vm = "app/pdt/mesyuarat/SenaraiTindakan.jsp";
				}
				else
				{
					saveTindakan(session,ID_MESYUARATCONTENT,ID_MESYUARATTINDAKAN,editBahagian,editEmelBahagian,editEmelCCBahagian,editStatusBahagian,editCatatanBahagian);
					viewTindakan = viewTindakan(session, ID_MESYUARATTINDAKAN);
					this.context.put("viewTindakan", viewTindakan);
					vm = "app/pdt/mesyuarat/viewTindakan.jsp";
				}
			}
			else
			{
				myLogger.info("--------- Hantar EMEL");
				
				hantarEmel(session,ID_MESYUARATUTAMA,ID_MESYUARATCONTENT,ID_MESYUARATTINDAKAN, "byIndividu");
				
				viewTindakan = viewTindakan(session, ID_MESYUARATTINDAKAN);
				this.context.put("viewTindakan", viewTindakan);
				vm = "app/pdt/mesyuarat/viewTindakan.jsp";
			}
			
			
						
		}
		
		else if(command.equals("SimpanMainDir"))
		{
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			
			myLogger.info(" BIL :::::::::::: "+ getParam("BIL_MAIN_"+ID_MESYUARATUTAMA));
			
			this.context.put("BIL", getParam("BIL_MAIN_"+ID_MESYUARATUTAMA));
			this.context.put("rowCss", getParam("rowCss_MAIN_"+ID_MESYUARATUTAMA));
			this.context.put("HID_OPENCLOSE", getParam("HID_OPENCLOSE_"+ID_MESYUARATUTAMA));
			String editMainDirField = getParam("editMainDirField_"+ID_MESYUARATUTAMA);
			String editTahunField = getParam("editTahunField_"+ID_MESYUARATUTAMA);
			String editBilanganField = getParam("editBilanganField_"+ID_MESYUARATUTAMA);
			String editStatusMesyuarat = getParam("editStatusMesyuarat_"+ID_MESYUARATUTAMA);
			String PIC = getParam("editPIC_"+ID_MESYUARATUTAMA);
			String EMEL_PIC = getParam("editEMEL_PIC_"+ID_MESYUARATUTAMA);
			String TEL_PIC = getParam("editTEL_PIC_"+ID_MESYUARATUTAMA);
			//function simpan disini
			saveUpdateMainDir(session,ID_MESYUARATUTAMA,editMainDirField,editTahunField,editBilanganField,editStatusMesyuarat,PIC,EMEL_PIC,TEL_PIC);			
			viewMainFoler = viewTajukUtama(session,ID_MESYUARATUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			
			
			String FLAG_ADD_EMEL = getParam("FLAG_ADD_EMEL");
			if(FLAG_ADD_EMEL.equals("Y"))
			{				
				vm = "app/pdt/mesyuarat/editMainDir.jsp";	
			}
			else
			{
				vm = "app/pdt/mesyuarat/viewMainDir.jsp";	
			}
		}
		else if(command.equals("validateLampiran"))
		{
			String ID_MESYUARATDOKUMEN = getParam("ID_MESYUARATDOKUMEN");
			this.context.put("ID_MESYUARATDOKUMEN", ID_MESYUARATDOKUMEN);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String NAMA_DOC = getParam("NAMA_DOC");
			String duplicateName = "N";
			if(checkDuplicateDocName(session,ID_MESYUARATDOKUMEN,ID_MESYUARATUTAMA,NAMA_DOC)==true)
			{
				duplicateName = "Y";
			}
			this.context.put("duplicateName", duplicateName);
			vm = "app/pdt/mesyuarat/validateLampiran.jsp";			
		}
		else if(command.equals("SimpanLampiran"))
		{
			String ID_MESYUARATDOKUMEN = getParam("ID_MESYUARATDOKUMEN");
			this.context.put("ID_MESYUARATDOKUMEN", ID_MESYUARATDOKUMEN);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss", getParam("rowCss"));
			
			String editLampiranField = getParam("editLampiranField_"+ID_MESYUARATDOKUMEN);
			myLogger.info(" ***** editLampiranField : "+editLampiranField);
			
			//function simpan disini	
			saveUpdateLampiran(session,ID_MESYUARATDOKUMEN,ID_MESYUARATUTAMA,editLampiranField);
			
			viewLampiran = viewLampiran(session,ID_MESYUARATDOKUMEN);
			this.context.put("viewLampiran", viewLampiran);
			vm = "app/pdt/mesyuarat/viewLampiran.jsp";			
		}
		else if(command.equals("showLampiran") || command.equals("deleteLampiran"))
		{
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String AUTOLOAD = getParam("AUTOLOAD");		
			this.context.put("AUTOLOAD", AUTOLOAD);								
			String FLAG_LAMP_OPENCLOSE = getParam("FLAG_LAMP_OPENCLOSE");
			myLogger.info(" FLAG_LAMP_OPENCLOSE : "+FLAG_LAMP_OPENCLOSE+" AUTOLOAD :"+AUTOLOAD);
			String carianLampiran = getParam("carianLampiran");
			this.context.put("carianLampiran", carianLampiran);	
			
			if(command.equals("deleteLampiran"))
			{
				//delete function
				String ID_MESYUARATDOKUMEN = getParam("ID_MESYUARATDOKUMEN");
				String NAMA_DOC = getParam("NAMA_DOC");
				deleteLampiran(session,ID_MESYUARATDOKUMEN,NAMA_DOC);
			}			
			this.context.put("MAX_LAYER", getMaxLayer(session, ID_MESYUARATUTAMA));
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_LAMP_OPENCLOSE", "OPEN");
				listFolderLampiran = listFolderLampiran(session, ID_MESYUARATUTAMA, carianLampiran);
				this.context.put("listFolderLampiran", listFolderLampiran);
				vm = "app/pdt/mesyuarat/SenaraiLampiran.jsp";				
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_LAMP_OPENCLOSE.equals("OPEN"))
				{
					//this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					this.context.put("JUMLAH_LAMP", getParam("JUMLAH_LAMP"));
					this.context.put("FLAG_LAMP_OPENCLOSE", "CLOSE");
					vm = "app/pdt/mesyuarat/blank_lampiranFolder.jsp";
				}
				else
				{
					if(FLAG_LAMP_OPENCLOSE.equals("CLOSE"))
					{
						this.context.put("FLAG_LAMP_OPENCLOSE", "OPEN");						
					}
					listFolderLampiran = listFolderLampiran(session, ID_MESYUARATUTAMA, carianLampiran);
					this.context.put("listFolderLampiran", listFolderLampiran);
					vm = "app/pdt/mesyuarat/SenaraiLampiran.jsp";
				}
			}					
		}	
		else if(command.equals("saveTajukUtama") || command.equals("SimpanAddSubDir") || command.equals("emelTindakanMain")  ||command.equals("showAllFolder") || command.equals("deleteSubDir"))
		{
			
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);	
			String carianMesyuarat = getParam("carianMesyuarat");
			this.context.put("carianMesyuarat", carianMesyuarat);	
			String carianBahagian = getParam("carianBahagian");
			this.context.put("carianBahagian", carianBahagian);
			String carianStatus = getParam("carianStatus");
			this.context.put("carianStatus", carianStatus);
			String carianLampiran = getParam("carianLampiran");
			this.context.put("carianLampiran", carianLampiran);
			
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			String ID_MESYUARATCONTENT = getParam("ID_MESYUARATCONTENT");
			this.context.put("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String LAYER = getParam("LAYER");
			this.context.put("LAYER", LAYER);
			String AUTOLOAD = getParam("AUTOLOAD");	
			String FLAG_OPENCLOSE = getParam("FLAG_OPENCLOSE");
			String FLAG_SUB_OPENCLOSE = getParam("FLAG_SUB_OPENCLOSE");
			myLogger.info(" AUTOLOAD : "+AUTOLOAD);
			this.context.put("NUMBERING", getParam("SEND_NUMBERING"));
			String ID_TO_DELETE = getParam("ID_TO_DELETE");
			myLogger.info(" ******ID_TO_DELETE : "+ID_TO_DELETE);
			
			
			if(command.equals("saveTajukUtama"))
			{				
				String TAJUK_MESYUARAT_AFTERINSERT = getParam("editMainDirField_"+ID_MESYUARATUTAMA);
				String TAHUN_AFTERINSERT = getParam("editTahunField_"+ID_MESYUARATUTAMA);
				String BILANGAN_AFTERINSERT = getParam("editBilanganField_"+ID_MESYUARATUTAMA);
				myLogger.info(" TAJUK_MESYUARAT_AFTERINSERT : "+TAJUK_MESYUARAT_AFTERINSERT
						+" TAHUN_AFTERINSERT : "+TAHUN_AFTERINSERT
						+" BILANGAN_AFTERINSERT : "+BILANGAN_AFTERINSERT);
				this.context.put("FLAG_AFTERINSERT", "Y");
				this.context.put("TAJUK_MESYUARAT_AFTERINSERT", TAJUK_MESYUARAT_AFTERINSERT.toUpperCase());
				this.context.put("TAHUN_AFTERINSERT", TAHUN_AFTERINSERT);
				this.context.put("BILANGAN_AFTERINSERT", BILANGAN_AFTERINSERT);
				ID_MESYUARATUTAMA = saveTajukUtama(session,ID_MESYUARATUTAMA,ID_BAHAGIANMESYUARAT);
			}
			else if(command.equals("deleteSubDir"))
			{
				//call delete function
				checkChildDeleteSub(session,ID_TO_DELETE);
				AUTOLOAD = "N";
				FLAG_SUB_OPENCLOSE = "CLOSE";
			}
			else if(command.equals("SimpanAddSubDir"))
			{
				//call add functon
				String editSubDirField = getParam("editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_");
				myLogger.info("(editSubDirField_"+ID_MESYUARATUTAMA+"_"+ID_REFER+"_"+ID_MESYUARATCONTENT+") -- editSubDirField :"+editSubDirField);
				saveInsertSubDir(session,ID_REFER,ID_MESYUARATCONTENT,ID_MESYUARATUTAMA,editSubDirField,LAYER);
			}
			else
			{
				if(command.equals("emelTindakanMain"))
				{
					hantarEmel(session,ID_MESYUARATUTAMA,"","", "byMain");
				}
				
				/*
				if(FlagCari.equals("Y"))
				{
					if(FLAG_OPENCLOSE.equals("OPEN"))
					{
						AUTOLOAD = "N";
					}
					else
					{
						if(!carianMesyuarat.equals("") && carianBahagian.equals("") && carianTerperinci.equals(""))
						{
							AUTOLOAD = "N";
						}						
						else
						{
							AUTOLOAD = "Y";
						}
					}
				}
				*/
				if(FlagCari.equals("Y"))
				{
					AUTOLOAD = "Y";
					if(FLAG_OPENCLOSE.equals("OPEN"))
					{
						AUTOLOAD = "N";
					}
				}
				
			}
			
			
			
			String MAX_LAYER = getMaxLayer(session, getParam("ID_MESYUARATUTAMA"))+"";
			this.context.put("MAX_LAYER", MAX_LAYER);
			
			this.context.put("AUTOLOAD", AUTOLOAD);
			Hashtable filter_carian_folder = null;
			String list_id_refer = "0";
			String list_id_actual = "0";
			if(AUTOLOAD.equals("Y") 
					//&& (!carianTerperinci.equals("") || !carianBahagian.equals("") )
					)
			{
				filter_carian_folder = new Hashtable();
				filter_carian_folder = getIDP_check(session, ID_MESYUARATUTAMA, LAYER, carianTerperinci,carianBahagian, MAX_LAYER);		
				list_id_refer = (String) filter_carian_folder.get("SET_ID_REFER");
				list_id_actual = (String) filter_carian_folder.get("SET_ID_MESYUARATCONTENT");
			}			
			myLogger.info(" **********filter_carian_folder : "+filter_carian_folder);
			listFolderSub = listFolderSub(session, ID_MESYUARATUTAMA, ID_REFER, LAYER,carianTerperinci,carianBahagian,carianStatus,list_id_refer,list_id_actual,AUTOLOAD);
			this.context.put("listFolderSub", listFolderSub);
			this.context.put("TOTAL_SUB", listFolderSub.size());
			this.context.put("NAMA_FOLDER", getParam("NAMA_FOLDER"));
			
			
			listFolderLampiran = listFolderLampiran(session, ID_MESYUARATUTAMA, carianLampiran);
			this.context.put("listFolderLampiran", listFolderLampiran);
			this.context.put("JUMLAH_LAMP", listFolderLampiran.size());
			
			
			//myLogger.info(" **********LAYER : "+LAYER);
			//myLogger.info(" **********MAX_LAYER : "+MAX_LAYER);
			
			
			
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_OPENCLOSE", "OPEN");
				this.context.put("FLAG_SUB_OPENCLOSE", "OPEN");
				
				vm = "app/pdt/mesyuarat/SenaraiSubFolder.jsp";
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_OPENCLOSE.equals("OPEN"))
				{
					this.context.put("FLAG_OPENCLOSE", "CLOSE");
					this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					//this.context.put("JUMLAH_TINDAKAN", getParam("JUMLAH_TINDAKAN"));
					vm = "app/pdt/mesyuarat/blank_subFolder.jsp";
				}
				else
				{
					if(FLAG_OPENCLOSE.equals("CLOSE"))
					{
						this.context.put("FLAG_OPENCLOSE", "OPEN");
					}
					
					myLogger.info(" FLAG_SUB_OPENCLOSE : "+FLAG_SUB_OPENCLOSE);
					if(FLAG_SUB_OPENCLOSE.equals("OPEN"))
					{
						this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
						//this.context.put("JUMLAH_TINDAKAN", getParam("JUMLAH_TINDAKAN"));
						this.context.put("FLAG_SUB_OPENCLOSE", "CLOSE");
						vm = "app/pdt/mesyuarat/blank_subFolder.jsp";
					}
					else
					{
						if(FLAG_SUB_OPENCLOSE.equals("CLOSE"))
						{
							this.context.put("FLAG_SUB_OPENCLOSE", "OPEN");						
						}
						vm = "app/pdt/mesyuarat/SenaraiSubFolder.jsp";
					}
						
				}
			}
			
			
		}
		else if(command.equals("saveLampiran"))
		{						
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA_AFTERUPLOAD",ID_MESYUARATUTAMA);
			saveLampiran(session,ID_MESYUARATUTAMA);
			this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");			
			vm = "app/pdt/mesyuarat/index.jsp";			
		}
		else if(command.equals("countLampiran"))
		{
			this.context.put("ID_MESYUARATUTAMA", getParam("ID_MESYUARATUTAMA"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_MESYUARATUTAMA")));
			this.context.put("TOTAL_LAMPIRAN", getParam("TOTAL_LAMPIRAN"));
			this.context.put("FLAG_LAMP_OPENCLOSE", getParam("FLAG_LAMP_OPENCLOSE"));
			vm = "app/pdt/mesyuarat/CountLampiran.jsp";
		}
		else if(command.equals("addLampiran"))
		{			
			this.context.put("ID_MESYUARATUTAMA", getParam("ID_MESYUARATUTAMA"));			
			vm = "app/pdt/mesyuarat/addLampiran.jsp";			
		}
		else if(command.equals("editLampiran"))
		{
			String ID_MESYUARATDOKUMEN = getParam("ID_MESYUARATDOKUMEN");
			this.context.put("ID_MESYUARATDOKUMEN", ID_MESYUARATDOKUMEN);
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss", getParam("rowCss"));
			viewLampiran = viewLampiran(session,ID_MESYUARATDOKUMEN);
			this.context.put("viewLampiran", viewLampiran);
			vm = "app/pdt/mesyuarat/editLampiran.jsp";			
		}	
		else if(command.equals("batalCarianUtama") || command.equals("salinRekod") || command.equals("showFolderUtama") || command.equals("deleteFolderUtama"))
		{
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			this.context.put("FLAG_AFTERINSERT", getParam("FLAG_AFTERINSERT"));
			this.context.put("TAJUK_MESYUARAT_AFTERINSERT", getParam("TAJUK_MESYUARAT_AFTERINSERT"));
			this.context.put("TAHUN_AFTERINSERT", getParam("TAHUN_AFTERINSERT"));
			this.context.put("BILANGAN_AFTERINSERT", getParam("BILANGAN_AFTERINSERT"));
			
			if(command.equals("deleteFolderUtama"))
			{
				//function delete
				
				deleteMainFolder(session,ID_MESYUARATUTAMA);
			}
			else if(command.equals("salinRekod"))
			{
				copyMesyuarat(session,ID_MESYUARATUTAMA,ID_BAHAGIANMESYUARAT);
			}
			listMesyuaratUtama = listMesyuaratUtama(session, "","", "","","","","","","","",ID_BAHAGIANMESYUARAT);
			setupPageList(session, action, listMesyuaratUtama, "listMesyuaratUtama",command,"div_senaraiUtama");
			vm = "app/pdt/mesyuarat/SenaraiUtama.jsp";	
		}
		else if(command.equals("addTajukUtama") || command.equals("batalTajukUtama"))
		{			
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			viewTajukUtama = viewTajukUtama(session, ID_MESYUARATUTAMA);
			this.context.put("viewTajukUtama", viewTajukUtama);
			vm = "app/pdt/mesyuarat/addTajukUtama.jsp";	
		}
		/*
		else if(command.equals("saveTajukUtama"))
		{			
			String ID_MESYUARATUTAMA = getParam("ID_MESYUARATUTAMA");
			saveTajukUtama(session,ID_MESYUARATUTAMA);
			listMesyuaratUtama = listMesyuaratUtama(session, "","","","","","","","");
			setupPageList(session, action, listMesyuaratUtama, "listMesyuaratUtama",command,"div_senaraiUtama");
			vm = "app/pdt/mesyuarat/SenaraiUtama.jsp";
		}
		*/
		else if(command.equals("closeTajukUtama"))
		{			
			vm = "app/pdt/mesyuarat/blank.jsp";
		}
		else
		{
			//listMesyuaratUtama = listMesyuaratUtama(session, "");
			//setupPageList(session, action, listMesyuaratUtama, "listMesyuaratUtama",command,"div_senaraiUtama");
			vm = "app/pdt/mesyuarat/index.jsp";	
		}
		
		
		myLogger.info("MESYUARAT---------VM"+vm);
		//session.setAttribute("FrmSenaraiDokumenOnLoad", "N");
		return vm;
		
	}

	public void defaultPut()
	{
		this.context.put("listFolderLampiran", "");
		this.context.put("carianMesyuarat", "");
		this.context.put("carianTerperinci", "");
		this.context.put("carianBahagian", "");
		this.context.put("carianLampiran","");
		this.context.put("carianStatus","");
		this.context.put("carianPIC","");
		this.context.put("carianStatusMesyuarat","");
		this.context.put("carianTahun","");
		this.context.put("carianBilangan","");
		this.context.put("FlagCari", "");
		this.context.put("listMesyuaratUtama", "");
		this.context.put("listFolderTindakan", "");
		this.context.put("listFolderSub", "");
		this.context.put("viewTajukUtama", "");	
		this.context.put("JUMLAH_SUB","");
		this.context.put("JUMLAH_TINDAKAN", "");
		this.context.put("viewSubFoler", "");
		this.context.put("viewMainFoler", "");
		this.context.put("BIL", "");
		this.context.put("after_uploadLampiran","");
		this.context.put("viewTindakan", "");		
		this.context.put("JUMLAH_LAMP", "");
		this.context.put("viewLampiran", "");	
		this.context.put("listBahagian", "");
		this.context.put("listEmel", "");
		this.context.put("ShowDalamTindakanContent", 0);
		this.context.put("ShowDalamTindakanMain", 0);
		this.context.put("FLAG_AFTERINSERT","");
		this.context.put("TAJUK_MESYUARAT_AFTERINSERT","");
		this.context.put("TAHUN_AFTERINSERT","");
		this.context.put("BILANGAN_AFTERINSERT","");
		this.context.put("FLAG_AUTOLOADSUB","");
		this.context.put("listTelPIC","");
		this.context.put("listEmelPIC","");
		this.context.put("listPIC","");		
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
	
	@SuppressWarnings("unchecked")
	public int getMaxLayer(HttpSession session, String ID_MESYUARATUTAMA)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		int max_layer = 0;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = " SELECT MAX(NVL(LAYER,0)) AS MAX_LAYER FROM TBLPDTMESYUARATCONTENT K " +
					" WHERE K.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
			myLogger.info(" SQL : listMesyuaratUtama :"+ sql);
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				max_layer = (rs.getString("MAX_LAYER") == null ? 0 : rs.getInt("MAX_LAYER"));				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return max_layer;
	}
	
	
	@SuppressWarnings("unchecked")
	public int getCountDalamTindakan(HttpSession session, String ID_MESYUARATUTAMA, String ID_MESYUARATCONTENT)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		int max_layer = 0;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = " SELECT COUNT(ID_MESYUARATTINDAKAN) AS DALAM_TINDAKAN FROM TBLPDTMESYUARATTINDAKAN WHERE ID_STATUS = 1 ";
			if(!ID_MESYUARATUTAMA.equals(""))
			{
				sql += " AND ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
			}
			if(!ID_MESYUARATCONTENT.equals(""))
			{
				sql += " AND ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"' ";
			}
			myLogger.info(" SQL : getCountDalamTindakan :"+ sql);
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				max_layer = (rs.getString("DALAM_TINDAKAN") == null ? 0 : rs.getInt("DALAM_TINDAKAN"));				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return max_layer;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listMesyuaratUtama(HttpSession session, String carianTerperinci,String carianBahagian,
			String carianMesyuarat,String carianLampiran,
			String carianTahun,String carianBilangan,String carianStatus,String carianStatusMesyuarat,
			String carianPIC,String FLAG_AUTOLOADSUB,String ID_BAHAGIAN)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listMesyuaratUtama = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			/*
			sql = " SELECT T.ID_MESYUARATUTAMA, T.TAJUK, "+
					" NVL(SUB.SAIZ,0) AS JUMLAH_SUB FROM TBLPDTMESYUARATUTAMA T ,  "+
					" (SELECT S.ID_MESYUARATUTAMA, COUNT(S.ID_MESYUARATCONTENT) AS SAIZ " +
					" FROM TBLPDTMESYUARATCONTENT S WHERE S.LAYER = '1' "+
					" GROUP BY  S.ID_MESYUARATUTAMA) SUB "+
					" WHERE T.ID_MESYUARATUTAMA IS NOT NULL   "+
					" AND SUB.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA ";
					if(!carianTerperinci.equals(""))
					{
						sql += " AND UPPER(T.TAJUK) LIKE '%"+carianTerperinci.toUpperCase()+"%' ";
					}
					sql += " ORDER BY T.TAJUK ";		
			*/
			
			
			
			
			sql = " SELECT   T.PIC,T.EMEL_PIC,T.TEL_PIC," +
					"T.ID_MESYUARATUTAMA,T.ID_MESYUARATUTAMA_PREV, T.BILANGAN," +
					" (CASE WHEN T.FLAG_AKTIF = 'A' THEN 'AKTIF' WHEN T.FLAG_AKTIF = 'T' THEN 'TANGGUH' ELSE '' END) AS STATUS_MESYUARAT, " +
					" T.FLAG_AKTIF," +
					" T.TAHUN, T.TAJUK_MESYUARAT, MAXBIL.TOTALTAHUN, MAXBIL.TOTALBILANGAN,NVL (SUB.SAIZ, 0) AS JUMLAH_SUB, "+
					" NVL (TF.SAIZ, 0) AS TOTAL_FOLDER,NVL (TL.SAIZ, 0) AS TOTAL_TINDAKAN, " +
					" (CASE WHEN T.FLAG_AKTIF = 'A' THEN NVL (TL.DALAM_TINDAKAN, 0) ELSE 0 END) AS DALAM_TINDAKAN, " +
					" NVL(ML.MAX_LAYER,0) AS MAX_LAYER "+
					" FROM TBLPDTMESYUARATUTAMA T, "+
					"  (SELECT MU.TAJUK_MESYUARAT, S.TAHUN AS TOTALTAHUN, MAX(MU.BILANGAN) AS TOTALBILANGAN FROM TBLPDTMESYUARATUTAMA MU, "+
					"  (SELECT TAJUK_MESYUARAT, MAX(TAHUN) AS TAHUN FROM "+
					"  (SELECT ID_MESYUARATUTAMA,TAJUK_MESYUARAT, TAHUN, BILANGAN FROM TBLPDTMESYUARATUTAMA)  "+
					"  GROUP BY TAJUK_MESYUARAT) S "+
					"  WHERE MU.TAJUK_MESYUARAT = S.TAJUK_MESYUARAT AND S.TAHUN = MU.TAHUN "+
					"  GROUP BY MU.TAJUK_MESYUARAT, S.TAHUN) MAXBIL, "+					
					" (SELECT   S.ID_MESYUARATUTAMA, "+
					" COUNT (DISTINCT S.ID_MESYUARATCONTENT) AS SAIZ "+
					" FROM TBLPDTMESYUARATCONTENT S "+
					" WHERE S.LAYER = '1' "+
					" GROUP BY S.ID_MESYUARATUTAMA) SUB, "+
					" (SELECT   S.ID_MESYUARATUTAMA, "+
					" COUNT (DISTINCT S.ID_MESYUARATCONTENT) AS SAIZ "+
					" FROM TBLPDTMESYUARATCONTENT S,TBLPDTMESYUARATTINDAKAN L "+
					" WHERE S.ID_MESYUARATCONTENT = L.ID_MESYUARATCONTENT(+) " +
					" ";  
			
					if(!carianTerperinci.equals(""))
					{
						sql += " AND UPPER(S.KETERANGAN) LIKE '%"+carianTerperinci.toUpperCase()+"%' ";
					}
					
					sql += " GROUP BY S.ID_MESYUARATUTAMA) TF, "+
					" (SELECT   S.ID_MESYUARATUTAMA, "+
					" COUNT (DISTINCT S.ID_MESYUARATTINDAKAN) AS SAIZ," +
					" COUNT (DISTINCT (CASE WHEN S.ID_STATUS = 1 THEN S.ID_MESYUARATTINDAKAN END)) AS DALAM_TINDAKAN "+
					" FROM TBLPDTMESYUARATTINDAKAN S WHERE S.ID_MESYUARATTINDAKAN IS NOT NULL ";
					if(!carianBahagian.equals(""))
					{
						sql += " AND UPPER(S.BAHAGIAN) LIKE '%"+carianBahagian.toUpperCase()+"%' ";
					}
					
					if(!carianStatus.equals(""))
					{
						sql += " AND UPPER(S.ID_STATUS) = '"+carianStatus+"' ";
					}
					sql += " GROUP BY S.ID_MESYUARATUTAMA) TL , "+
					
							
					" (SELECT   S.ID_MESYUARATUTAMA, "+
					" COUNT (DISTINCT S.ID_MESYUARATDOKUMEN) AS SAIZ "+
					" FROM TBLPDTMESYUARATDOKUMEN S WHERE S.ID_MESYUARATDOKUMEN IS NOT NULL ";
					if(!carianLampiran.equals(""))
					{
						sql += " AND UPPER(S.NAMA_DOC) LIKE '%"+carianLampiran.toUpperCase()+"%' ";
					}
					
					sql += " GROUP BY S.ID_MESYUARATUTAMA) TD , "+
							
							
							
							
							" (SELECT K.ID_MESYUARATUTAMA, MAX(NVL(LAYER,0)) AS MAX_LAYER FROM TBLPDTMESYUARATCONTENT K "+
							" GROUP BY K.ID_MESYUARATUTAMA) ML"+
					" WHERE T.ID_MESYUARATUTAMA IS NOT NULL AND T.ID_BAHAGIAN = '"+ID_BAHAGIAN+"'  "+
					" AND SUB.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA "+
					" AND TF.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA "+
					" AND TD.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA "+
					" AND TL.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA " +
					" AND MAXBIL.TAJUK_MESYUARAT = T.TAJUK_MESYUARAT "+
					" AND ML.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA ";
					
					/*
					if((!carianMesyuarat.equals("") || !carianBilangan.equals("") ||  !carianTahun.equals("")) 
							&& !carianTerperinci.equals("") && (!carianLampiran.equals("")) && (!carianBahagian.equals("") || !carianStatus.equals("")))
					{
						sql += " AND (" +
								" (UPPER(T.TAJUK_MESYUARAT) LIKE '%"+carianMesyuarat.toUpperCase()+"%' " +
								" OR NVL (TF.SAIZ, 0)>0) " +
								" OR NVL (TD.SAIZ, 0)>0) " +
								" OR NVL (TL.SAIZ, 0)>0)" +
								" ) ";
					}
					else
					*/
					{
						if(!carianMesyuarat.equals(""))
						{
							if(!FLAG_AUTOLOADSUB.equals("Y"))
							{
								sql += " AND (UPPER(T.TAJUK_MESYUARAT) LIKE '%"+carianMesyuarat.toUpperCase()+"%' )";
							}
							else
							{
								sql += " AND (UPPER(T.TAJUK_MESYUARAT) = '"+carianMesyuarat.toUpperCase()+"' )";
							}
						}
						if(!carianPIC.equals(""))
						{
							sql += " AND UPPER(T.PIC) LIKE '%"+carianPIC.toUpperCase()+"%' ";
						}
						if(!carianTahun.equals(""))
						{
							sql += " AND T.TAHUN = '"+carianTahun+"' ";
						}
						if(!carianBilangan.equals(""))
						{
							sql += " AND T.BILANGAN = '"+carianBilangan+"' ";
						}
						if(!carianStatusMesyuarat.equals(""))
						{
							sql += " AND T.FLAG_AKTIF = '"+carianStatusMesyuarat+"' ";
						}
						
						if(!carianLampiran.equals(""))
						{
							sql += " AND (NVL (TD.SAIZ, 0)>0)";
						}
						
						if(!carianTerperinci.equals(""))
						{
							sql += " AND (NVL (TF.SAIZ, 0)>0)";
						}
						
						if((!carianBahagian.equals("") || !carianStatus.equals("")))
						{
							sql += " AND NVL (TL.SAIZ, 0)>0 ";
						}
					}
					
					 
					sql += " ORDER BY T.TAHUN DESC, T.TAJUK_MESYUARAT, T.BILANGAN ";
					
			
			/*		sql = " SELECT T.ID_MESYUARATUTAMA, T.TAJUK_MESYUARAT, T.TAHUN, "+
					" T.BILANGAN, T.ID_MESYUARATUTAMA_PREV, T.ID_MASUK,  "+
					" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
					" FROM TBLPDTMESYUARATUTAMA T ";
			*/
					
			myLogger.info(" SQL : listMesyuaratUtama :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listMesyuaratUtama = Collections.synchronizedList(new ArrayList());
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
				
					
				h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));			
				h.put("TAJUK_MESYUARAT",rs.getString("TAJUK_MESYUARAT") == null ? "" : rs.getString("TAJUK_MESYUARAT").toUpperCase());	
				h.put("TAHUN", rs.getString("TAHUN") == null ? 1 : rs.getInt("TAHUN"));
				h.put("TOTALBILANGAN", rs.getString("TOTALBILANGAN") == null ? 1 : rs.getInt("TOTALBILANGAN"));	
				h.put("TOTALTAHUN", rs.getString("TOTALTAHUN") == null ? 0 : rs.getInt("TOTALTAHUN"));
				h.put("BILANGAN", rs.getString("BILANGAN") == null ? 1 : rs.getInt("BILANGAN"));
				h.put("STATUS_MESYUARAT", rs.getString("STATUS_MESYUARAT") == null ? "" : rs.getString("STATUS_MESYUARAT"));
				h.put("FLAG_AKTIF", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
				h.put("ID_MESYUARATUTAMA_PREV", rs.getString("ID_MESYUARATUTAMA_PREV") == null ? 0 : rs.getInt("ID_MESYUARATUTAMA_PREV"));
				h.put("DALAM_TINDAKAN",rs.getString("DALAM_TINDAKAN") == null ? 0 : rs.getInt("DALAM_TINDAKAN"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC").toUpperCase());	
				h.put("EMEL_PIC",rs.getString("EMEL_PIC") == null ? "" : rs.getString("EMEL_PIC"));
				h.put("TEL_PIC",rs.getString("TEL_PIC") == null ? "" : rs.getString("TEL_PIC"));
				listMesyuaratUtama.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listMesyuaratUtama;
	}
	
	@SuppressWarnings("unchecked")
	public List listFolderSub(HttpSession session, String ID_MESYUARATUTAMA, String ID_REFER, String LAYER,String CarianContent, String CarianTindakan,String CarianStatus,String id_PU_sebelum,String id_PU_current,String AUTOLOAD)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderSub = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT DISTINCT T.TARIKH_MASUK, T.ID_MESYUARATCONTENT, T.ID_MESYUARATUTAMA, T.KETERANGAN, T.LAYER, "+
					" T.ID_REFER, NVL (SUB.SAIZ, 0) AS JUMLAH_SUB, NVL (LAMP.SAIZ, 0) AS JUMLAH_TINDAKAN," +
					" (CASE WHEN MU.FLAG_AKTIF = 'A' THEN NVL (LAMP.DALAM_TINDAKAN, 0) ELSE 0 END) AS DALAM_TINDAKAN, "+
					" NVL (SUB_FIND.SAIZ, 0) AS JUMLAH_SUB_FIND, NVL (LAMP_FIND.SAIZ, 0) AS JUMLAH_TINDAKAN_FIND "+
					" FROM TBLPDTMESYUARATCONTENT T, "+
					" (SELECT   TEMP.ID_REFER, COUNT (TEMP.ID_MESYUARATCONTENT) AS SAIZ "+
					" FROM TBLPDTMESYUARATCONTENT TEMP "+
					" WHERE TEMP.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' "+
					" GROUP BY TEMP.ID_REFER) SUB, "+
					" (SELECT   TEMP.ID_REFER, COUNT (TEMP.ID_MESYUARATCONTENT) AS SAIZ "+
					" FROM TBLPDTMESYUARATCONTENT TEMP "+
					" WHERE TEMP.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' " +
					" AND TEMP.ID_MESYUARATCONTENT IN ("+id_PU_current+") "+
					" GROUP BY TEMP.ID_REFER) SUB_FIND, "+
					" (SELECT   TEMP.ID_MESYUARATCONTENT, " +
					" COUNT (TEMP.ID_MESYUARATTINDAKAN) AS SAIZ, "+
					" COUNT (DISTINCT (CASE WHEN TEMP.ID_STATUS = 1 THEN TEMP.ID_MESYUARATTINDAKAN END)) AS DALAM_TINDAKAN "+
					" FROM TBLPDTMESYUARATTINDAKAN TEMP "+
					" WHERE TEMP.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' "+
					" GROUP BY TEMP.ID_MESYUARATCONTENT) LAMP, " +
					" (SELECT   TEMP.ID_MESYUARATCONTENT, COUNT (TEMP.ID_MESYUARATTINDAKAN) AS SAIZ "+
					" FROM TBLPDTMESYUARATTINDAKAN TEMP "+
					" WHERE TEMP.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
					//" AND TEMP.ID_MESYUARATCONTENT IN ("+id_PU_current+") ";
					if(!CarianTindakan.equals("") && AUTOLOAD.equals("Y"))
					{
						sql += " AND UPPER(TEMP.BAHAGIAN) LIKE '%"+CarianTindakan.toUpperCase()+"%' ";
					}
					if(!CarianStatus.equals("") && AUTOLOAD.equals("Y"))
					{
						sql += " AND TEMP.ID_STATUS = '"+CarianStatus+"' ";
					}
					
					
					sql += " GROUP BY TEMP.ID_MESYUARATCONTENT) LAMP_FIND, " +
					" TBLPDTMESYUARATTINDAKAN TEMP_LAMP, TBLPDTMESYUARATUTAMA MU "+
					" WHERE MU.ID_MESYUARATUTAMA = T.ID_MESYUARATUTAMA AND T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
					if(ID_REFER.equals("") || ID_REFER==null)
					{
						sql += " AND (T.ID_REFER IS NULL OR T.ID_REFER = '') ";
					}
					else
					{
						sql += " AND T.ID_REFER = '"+ID_REFER+"' ";
					}	
					sql += " AND SUB.ID_REFER(+) = T.ID_MESYUARATCONTENT ";
					sql += " AND SUB_FIND.ID_REFER(+) = T.ID_MESYUARATCONTENT ";
					
					if(AUTOLOAD.equals("Y"))
					{	if(!CarianContent.equals("") || !CarianTindakan.equals("") || !CarianStatus.equals(""))
					    {							
							sql += " AND ( ";							
							if(!CarianContent.equals(""))
							{
								sql += " UPPER(T.KETERANGAN) LIKE '%"+CarianContent.toUpperCase()+"%' OR ";
								
							}
							if(!CarianTindakan.equals(""))
							{
								sql += " UPPER(TEMP_LAMP.BAHAGIAN) LIKE '%"+CarianTindakan.toUpperCase()+"%' OR ";
							}
							if(!CarianStatus.equals(""))
							{
								sql += " UPPER(TEMP_LAMP.ID_STATUS) = '"+CarianStatus.toUpperCase()+"' OR ";
							}
							
							sql += " T.ID_MESYUARATCONTENT IN ("+id_PU_sebelum+") ";
							
							
							sql += " ) ";
					    }
					}
					
					
					sql += " AND LAMP.ID_MESYUARATCONTENT(+) = T.ID_MESYUARATCONTENT ";
					sql += " AND LAMP_FIND.ID_MESYUARATCONTENT(+) = T.ID_MESYUARATCONTENT ";					
				    sql += " AND TEMP_LAMP.ID_MESYUARATCONTENT(+) = T.ID_MESYUARATCONTENT "+
					" ORDER BY T.TARIKH_MASUK"; 
			
		
			
			
			myLogger.info(" SQL : listFolderSub :"+ sql);			
			rs = stmt.executeQuery(sql);
			listFolderSub = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));			
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));			
				h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));			
				h.put("ID_REFER",rs.getString("ID_REFER") == null ? "" : rs.getString("ID_REFER"));			
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));	
				h.put("NEXTLAYER",rs.getString("LAYER") == null ? 1 : rs.getInt("LAYER")+1);
				h.put("DALAM_TINDAKAN",rs.getString("DALAM_TINDAKAN") == null ? 0 : rs.getInt("DALAM_TINDAKAN"));
				h.put("TD_SAIZ_1",(rs.getString("LAYER") == null && rs.getString("LAYER").equals(""))||(rs.getString("LAYER").equals("1")) ? "0%" : Integer.parseInt(rs.getString("LAYER"))+1+"%");
				
				int sub = 0;
				int lam = 0;
				
				if(AUTOLOAD.equals("Y"))
				{
					sub = rs.getString("JUMLAH_SUB_FIND") == null ? 0 : rs.getInt("JUMLAH_SUB_FIND");
					lam = rs.getString("JUMLAH_TINDAKAN_FIND") == null ? 0 : rs.getInt("JUMLAH_TINDAKAN_FIND");
				}
				else
				{
					sub  = rs.getString("JUMLAH_SUB") == null ? 0 : rs.getInt("JUMLAH_SUB");
					lam = rs.getString("JUMLAH_TINDAKAN") == null ? 0 : rs.getInt("JUMLAH_TINDAKAN");
				}
				
				
				h.put("JUMLAH_SUB",sub);
				h.put("JUMLAH_TINDAKAN",lam);
				//h.put("JUMLAH_SUB_FIND", rs.getString("JUMLAH_SUB_FIND") == null ? 0 : rs.getInt("JUMLAH_SUB_FIND"));
				//h.put("JUMLAH_TINDAKAN_FIND", rs.getString("JUMLAH_TINDAKAN_FIND") == null ? 0 : rs.getInt("JUMLAH_TINDAKAN_FIND"));
				listFolderSub.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listFolderSub;
	}
	
	
		public Hashtable getIDP_check(HttpSession session, String ID_MESYUARATUTAMA, String LAYER, String Carian, String CarianLampiran,String MAX_LAYER)throws Exception {
			
			int l = Integer.parseInt(LAYER);
			int max_l = Integer.parseInt(MAX_LAYER);
			
			String getID_PANDANGUNDANG = "0";
			String getID_PANDANGUNDANG_ACTUAL = "0";
			
			
			for(int i = max_l; i>l; i--)
			{
				   List listIDP_by_layer_check = listIDP_by_layer(session, ID_MESYUARATUTAMA, i+"", Carian, CarianLampiran,getID_PANDANGUNDANG);
				   String temp_id_p = "0";
				   String temp_id_p_actual = "0";
				   for(int x = 0; x < listIDP_by_layer_check.size();x++)
				   {
					   Map m = (Map) listIDP_by_layer_check.get(x);
					   temp_id_p += ","+m.get("ID_REFER");
					   temp_id_p_actual += ","+m.get("ID_MESYUARATCONTENT");
				   }			
				   getID_PANDANGUNDANG = temp_id_p;
				   getID_PANDANGUNDANG_ACTUAL = temp_id_p_actual;
				   myLogger.info(" *** LAYER : "+i+" MAX_LAYER : "+max_l+" getID_PANDANGUNDANG :"+getID_PANDANGUNDANG+" getID_PANDANGUNDANG_ACTUAL : "+getID_PANDANGUNDANG_ACTUAL);
			}			
			//return getID_PANDANGUNDANG;
			Hashtable h = new Hashtable();
			h.put("SET_ID_REFER", getID_PANDANGUNDANG);
			h.put("SET_ID_MESYUARATCONTENT", getID_PANDANGUNDANG_ACTUAL);
			
			return h;
		}
		
	
		/*
			 List listNegeri = listNegeri();
			   
			   for(int i = 0; i < listNegeri.size();i++)
			   {
				   Map m = (Map) listNegeri.get(i);
				   selectNegeri += "<option value='"+m.get("ID")+"' >"+m.get("KETERANGAN")+"</option>";
			   }
	 	*/
		
		@SuppressWarnings("unchecked")
		public List listIDREFER_by_layer(HttpSession session, String ID_REFER,String ID_MESYUARATUTAMA, String LAYER, String Carian)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listIDREFER_by_layer = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();				
				sql = " SELECT DISTINCT U.ID_REFER FROM  TBLPDTMESYUARATCONTENT U "+
						" WHERE U.LAYER = '"+LAYER+"' AND U.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";				
				myLogger.info(" SQL : listIDREFER_by_layer :"+ sql);			
				rs = stmt.executeQuery(sql);
				listIDREFER_by_layer = Collections.synchronizedList(new ArrayList());
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
					h.put("ID_REFER",rs.getString("ID_REFER") == null ? "" : rs.getString("ID_REFER"));	
					listIDREFER_by_layer.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listIDREFER_by_layer;
		}
		
		
		@SuppressWarnings("unchecked")
		public List listIDP_by_layer(HttpSession session, String ID_MESYUARATUTAMA, String LAYER, String CarianItem,String CarianBahagian, String getIdAnakfolder)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listIDP_by_layer = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();				
				sql = " SELECT DISTINCT U.ID_MESYUARATCONTENT,U.ID_REFER FROM TBLPDTMESYUARATTINDAKAN L, TBLPDTMESYUARATCONTENT U "+
						" WHERE L.ID_MESYUARATCONTENT(+) = U.ID_MESYUARATCONTENT " +
						" AND U.LAYER = '"+LAYER+"' AND U.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"'";
						
							
						sql += " AND ( ";
						
						if(!CarianItem.equals(""))
						{
							sql += " UPPER(U.KETERANGAN) LIKE '%"+CarianItem.toUpperCase()+"%' OR ";
							
						}
						if(!CarianBahagian.equals(""))
						{
							sql += " UPPER(L.BAHAGIAN) LIKE '%"+CarianBahagian.toUpperCase()+"%' OR ";
						}
						
						sql += " U.ID_MESYUARATCONTENT IN ("+getIdAnakfolder+") ";
						
						
						sql += " ) ";
										
						
										
				myLogger.info(" SQL : listIDP_by_layer :"+ sql);			
				rs = stmt.executeQuery(sql);
				listIDP_by_layer = Collections.synchronizedList(new ArrayList());
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
					h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));
					h.put("ID_REFER",rs.getString("ID_REFER") == null ? "" : rs.getString("ID_REFER"));
					listIDP_by_layer.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listIDP_by_layer;
		}
	
	@SuppressWarnings("unchecked")
	public List listFolderTindakan(HttpSession session, String ID_MESYUARATUTAMA, String ID_REFER, String Carian,String CarianBahagian,String carianStatus)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderTindakan = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			/*
			sql = " SELECT T.ID_MESYUARATTINDAKAN, T.ID_MESYUARATCONTENT, T.CONTENT, "+
					" T.NAMA_DOC, T.JENIS_MIME, T.ID_MASUK,  "+
					" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI,  "+
					" T.ID_MESYUARATUTAMA "+
					" FROM TBLPDTMESYUARATTINDAKAN T " +
					" WHERE T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' " +
					" AND T.ID_MESYUARATCONTENT = '"+ID_REFER+"' ";
					if(!CarianLampiran.equals(""))
					{
						sql += " AND UPPER(T.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' ";
					}
			sql += " ORDER BY T.NAMA_DOC "; 
			*/
			
			sql = " SELECT T.ID_MESYUARATUTAMA,T.ID_MESYUARATTINDAKAN, T.ID_MESYUARATCONTENT, T.BAHAGIAN,T.EMEL_ERROR,T.COUNT_EMEL, "+
					" TO_CHAR(T.EMEL) AS EMEL, TO_CHAR(T.EMEL_CC) AS EMEL_CC,  T.CATATAN, T.ID_STATUS," +
					" (CASE WHEN T.ID_STATUS = '1' THEN 'DALAM TINDAKAN' WHEN T.ID_STATUS = '2' THEN 'SELESAI' ELSE '' END) AS STATUS,  "+
					" T.ID_MASUK, T.ID_KEMASKINI, T.TARIKH_MASUK,  "+
					" T.TARIKH_KEMASKINI FROM TBLPDTMESYUARATTINDAKAN T  WHERE ID_MESYUARATTINDAKAN IS NOT NULL "+
					" AND T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' " +
					" AND T.ID_MESYUARATCONTENT = '"+ID_REFER+"' " +
							" ";
			
			if(!CarianBahagian.equals(""))
			{
				sql += " AND UPPER(T.BAHAGIAN) LIKE '%"+CarianBahagian.toUpperCase()+"%' ";
			}
			if(!carianStatus.equals(""))
			{
				sql += " AND T.ID_STATUS = '"+carianStatus+"' ";
			}
			
			
			sql += " ORDER BY T.BAHAGIAN ";
			
			myLogger.info(" SQL : listFolderTindakan :"+ sql);			
			rs = stmt.executeQuery(sql);
			listFolderTindakan = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_MESYUARATTINDAKAN",rs.getString("ID_MESYUARATTINDAKAN") == null ? "" : rs.getString("ID_MESYUARATTINDAKAN"));	
				h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));
				h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));
				h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));	
				h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
				h.put("EMEL_CC",rs.getString("EMEL_CC") == null ? "" : rs.getString("EMEL_CC"));	
				h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("COUNT_EMEL",rs.getString("COUNT_EMEL") == null ? 0 : rs.getInt("COUNT_EMEL"));
				h.put("EMEL_ERROR",rs.getString("EMEL_ERROR") == null ? "" : rs.getString("EMEL_ERROR").toUpperCase());
				
				listFolderTindakan.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listFolderTindakan;
	}
	
	
	
	public Hashtable viewSubFoler(HttpSession session, String ID_MESYUARATCONTENT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_MESYUARATCONTENT, T.ID_MESYUARATUTAMA, T.KETERANGAN, T.LAYER, T.ID_REFER, T.ID_MASUK, "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI, " +
						" (CASE WHEN MU.FLAG_AKTIF = 'A' THEN NVL (LAMP.DALAM_TINDAKAN, 0) ELSE 0 END) AS DALAM_TINDAKAN "+
						" FROM TBLPDTMESYUARATCONTENT T, TBLPDTMESYUARATUTAMA MU, " +
						" (SELECT   TEMP.ID_MESYUARATCONTENT, " +
						" COUNT (TEMP.ID_MESYUARATTINDAKAN) AS SAIZ, "+
						" COUNT (DISTINCT (CASE WHEN TEMP.ID_STATUS = 1 THEN TEMP.ID_MESYUARATTINDAKAN END)) AS DALAM_TINDAKAN "+
						" FROM TBLPDTMESYUARATTINDAKAN TEMP "+
						" WHERE TEMP.ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"' "+
						" GROUP BY TEMP.ID_MESYUARATCONTENT) LAMP " +
						" WHERE LAMP.ID_MESYUARATCONTENT(+) = T.ID_MESYUARATCONTENT AND MU.ID_MESYUARATUTAMA = T.ID_MESYUARATUTAMA AND T.ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"' ";
						
				myLogger.info(" viewSubFoler :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));
					h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
					h.put("ID_REFER",rs.getString("ID_REFER") == null ? "" : rs.getString("ID_REFER"));
					h.put("DALAM_TINDAKAN",rs.getString("DALAM_TINDAKAN") == null ? 0 : rs.getInt("DALAM_TINDAKAN"));
				}
			
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public Hashtable viewTindakan(HttpSession session, String ID_MESYUARATTINDAKAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			
			
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.EMEL_ERROR, T.COUNT_EMEL, T.ID_MESYUARATTINDAKAN,T.ID_MESYUARATUTAMA, T.ID_MESYUARATCONTENT, T.BAHAGIAN, "+ 
						" TO_CHAR(T.EMEL) AS EMEL,TO_CHAR(T.EMEL_CC) AS EMEL_CC, T.CATATAN, T.ID_STATUS,  "+ 
						" (CASE WHEN T.ID_STATUS = '1' THEN 'DALAM TINDAKAN' WHEN T.ID_STATUS = '2' THEN 'SELESAI' ELSE '' END) AS STATUS,  "+
						" T.ID_MASUK, T.ID_KEMASKINI, T.TARIKH_MASUK,  "+ 
						" T.TARIKH_KEMASKINI "+ 
						" FROM TBLPDTMESYUARATTINDAKAN T "+
						" WHERE T.ID_MESYUARATTINDAKAN = '"+ID_MESYUARATTINDAKAN+"' ";
						
				myLogger.info(" viewTindakan :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_MESYUARATTINDAKAN",rs.getString("ID_MESYUARATTINDAKAN") == null ? "" : rs.getString("ID_MESYUARATTINDAKAN"));
					h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));
					h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					h.put("EMEL_CC",rs.getString("EMEL_CC") == null ? "" : rs.getString("EMEL_CC"));
					h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));	
					h.put("COUNT_EMEL",rs.getString("COUNT_EMEL") == null ? 0 : rs.getInt("COUNT_EMEL"));
					h.put("EMEL_ERROR",rs.getString("EMEL_ERROR") == null ? "" : rs.getString("EMEL_ERROR").toUpperCase());
				}
				
			
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	public Hashtable viewMainFoler(HttpSession session, String ID_MESYUARATUTAMA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT  T.ID_MESYUARATUTAMA, T.TAJUK_MESYUARAT, T.TAHUN, "+
						" T.BILANGAN, T.ID_MESYUARATUTAMA_PREV, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
						" FROM TBLPDTMESYUARATUTAMA T WHERE T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"'  ";
						
				myLogger.info(" viewMainFoler :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));
					h.put("TAJUK",rs.getString("TAJUK_MESYUARAT") == null ? "" : rs.getString("TAJUK_MESYUARAT"));
				}
			
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	public Hashtable viewTajukUtama(HttpSession session, String ID_MESYUARATUTAMA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
			if(!ID_MESYUARATUTAMA.equals(""))
			{		
				sql = " SELECT T.PIC,T.EMEL_PIC,T.TEL_PIC," +
						"T.ID_MESYUARATUTAMA,  MAXBIL.TOTALTAHUN,MAXBIL.TOTALBILANGAN, T.TAJUK_MESYUARAT, T.TAHUN, "+
						" (CASE WHEN T.FLAG_AKTIF = 'A' THEN 'AKTIF' WHEN T.FLAG_AKTIF = 'T' THEN 'TANGGUH' ELSE '' END) AS STATUS_MESYUARAT, " +
						" T.FLAG_AKTIF," +
						" T.BILANGAN, T.ID_MESYUARATUTAMA_PREV, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI," +
						"  (CASE WHEN T.FLAG_AKTIF = 'A' THEN NVL (TL.DALAM_TINDAKAN, 0) ELSE 0 END) AS DALAM_TINDAKAN  " +
						" FROM TBLPDTMESYUARATUTAMA T, " +
						/*
						"  (SELECT TAJUK_MESYUARAT, MAX(BILANGAN) AS TOTALBILANGAN FROM "+
						" 		 (SELECT ID_MESYUARATUTAMA,TAJUK_MESYUARAT, BILANGAN FROM TBLPDTMESYUARATUTAMA)  "+
						" 		 GROUP BY TAJUK_MESYUARAT) MAXBIL, "+
						*/
						"  (SELECT MU.TAJUK_MESYUARAT, S.TAHUN AS TOTALTAHUN, MAX(MU.BILANGAN) AS TOTALBILANGAN FROM TBLPDTMESYUARATUTAMA MU, "+
						"  (SELECT TAJUK_MESYUARAT, MAX(TAHUN) AS TAHUN FROM "+
						"  (SELECT ID_MESYUARATUTAMA,TAJUK_MESYUARAT, TAHUN, BILANGAN FROM TBLPDTMESYUARATUTAMA)  "+
						"  GROUP BY TAJUK_MESYUARAT) S "+
						"  WHERE MU.TAJUK_MESYUARAT = S.TAJUK_MESYUARAT AND S.TAHUN = MU.TAHUN "+
						"  GROUP BY MU.TAJUK_MESYUARAT, S.TAHUN) MAXBIL, "+	
						
						"  (SELECT   S.ID_MESYUARATUTAMA, "+
						"            COUNT (DISTINCT S.ID_MESYUARATTINDAKAN) AS SAIZ, "+
						"           COUNT (DISTINCT (CASE WHEN S.ID_STATUS = 1 THEN S.ID_MESYUARATTINDAKAN END)) AS DALAM_TINDAKAN "+
						"      FROM TBLPDTMESYUARATTINDAKAN S "+
						"      WHERE S.ID_MESYUARATTINDAKAN IS NOT NULL "+
						"   GROUP BY S.ID_MESYUARATUTAMA) TL  "+
						" WHERE T.ID_MESYUARATUTAMA='"+ID_MESYUARATUTAMA+"'  " +
								" AND MAXBIL.TAJUK_MESYUARAT = T.TAJUK_MESYUARAT AND TL.ID_MESYUARATUTAMA(+) = T.ID_MESYUARATUTAMA ";
				myLogger.info(" viewTajukUtama :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));
					h.put("TAJUK_MESYUARAT",rs.getString("TAJUK_MESYUARAT") == null ? "" : rs.getString("TAJUK_MESYUARAT"));
					h.put("TOTALBILANGAN",rs.getString("TOTALBILANGAN") == null ? 1 : rs.getInt("TOTALBILANGAN"));
					h.put("TOTALTAHUN",rs.getString("TOTALTAHUN") == null ? 0 : rs.getInt("TOTALTAHUN"));
					h.put("TAHUN",rs.getString("TAHUN") == null ? 1 : rs.getInt("TAHUN"));
					h.put("BILANGAN",rs.getString("BILANGAN") == null ? 1 : rs.getInt("BILANGAN"));
					h.put("ID_MESYUARATUTAMA_PREV",rs.getString("ID_MESYUARATUTAMA_PREV") == null ? "" : rs.getString("ID_MESYUARATUTAMA_PREV"));
					h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
					h.put("STATUS_MESYUARAT",rs.getString("STATUS_MESYUARAT") == null ? "" : rs.getString("STATUS_MESYUARAT"));	
					h.put("DALAM_TINDAKAN",rs.getString("DALAM_TINDAKAN") == null ? 0 : rs.getInt("DALAM_TINDAKAN"));
					h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC").toUpperCase());
					h.put("EMEL_PIC",rs.getString("EMEL_PIC") == null ? "" : rs.getString("EMEL_PIC"));
					h.put("TEL_PIC",rs.getString("TEL_PIC") == null ? "" : rs.getString("TEL_PIC"));
				}
			}
			else
			{
				h.put("ID_MESYUARATUTAMA","");
				h.put("TAJUK_MESYUARAT","");
				int year = Calendar.getInstance().get(Calendar.YEAR);
				h.put("TAHUN",year);
				h.put("BILANGAN",1);
				h.put("ID_MESYUARATUTAMA_PREV","");
				h.put("FLAG_AKTIF","");
				h.put("STATUS_MESYUARAT","");
				h.put("DALAM_TINDAKAN",0);
				h.put("PIC","");
				h.put("EMEL_PIC","");
				h.put("TEL_PIC","");
			}
			
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	public void copyMesyuarat(HttpSession session,String ID_MESYUARATUTAMA,String ID_BAHAGIAN) throws Exception {
		Connection conn = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		Db db = null;
		String sql = "";
		
		//Hashtable viewMainFoler = viewTajukUtama(session,ID_MESYUARATUTAMA);
		
		
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			long long_ID_MESYUARATUTAMA = DB.getNextID(db, "TBLPDTMESYUARATUTAMA_SEQ");
			Statement stmt = db.getStatement();
			String sql_copy_main = " INSERT INTO TBLPDTMESYUARATUTAMA ( "+
					" ID_MESYUARATUTAMA, ID_BAHAGIAN, TAJUK_MESYUARAT, TAHUN, PIC, EMEL_PIC, TEL_PIC, "+
					" BILANGAN, ID_MESYUARATUTAMA_PREV, ID_MASUK,  "+
					"  TARIKH_MASUK,   "+
					" FLAG_AKTIF)  "+
					" SELECT  "+
					" "+long_ID_MESYUARATUTAMA+", T.ID_BAHAGIAN, T.TAJUK_MESYUARAT, T.TAHUN, T.PIC, T.EMEL_PIC, T.TEL_PIC, "+
					" T.BILANGAN + 1, "+ID_MESYUARATUTAMA+", "+USER_ID_SYSTEM+",  "+
					" SYSDATE, "+
					" 'A' "+
					" FROM TBLPDTMESYUARATUTAMA T WHERE ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
			myLogger.info("sql_copy_main : "+sql_copy_main);
			stmt.executeUpdate(sql_copy_main);	
			AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATUTAMA  [ID : "+long_ID_MESYUARATUTAMA+"] Inserted");
			
			
			List listContentCopy = listContentCopy(session, ID_MESYUARATUTAMA);
			for(int i = 0; i < listContentCopy.size();i++)
			{
				Map m = (Map) listContentCopy.get(i);
				String ID_MESYUARATCONTENT = (String) m.get("ID_MESYUARATCONTENT");
				long long_ID_MESYUARATCONTENT = DB.getNextID(db, "TBLPDTMESYUARATCONTENT_SEQ");
				String sql_copy_content = " INSERT INTO TBLPDTMESYUARATCONTENT ( "+
						" ID_MESYUARATCONTENT, ID_MESYUARATUTAMA, KETERANGAN,  "+
						" LAYER, ID_MASUK,  "+
						" TARIKH_MASUK)" +
						" SELECT "+
						" "+long_ID_MESYUARATCONTENT+", "+long_ID_MESYUARATUTAMA+", T.KETERANGAN,  "+
						" T.LAYER,"+USER_ID_SYSTEM+",SYSDATE  "+
						" FROM TBLPDTMESYUARATCONTENT T WHERE ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"'  ";					
				stmt.executeUpdate(sql_copy_content);
				myLogger.info("sql_copy_content : "+sql_copy_content);
				AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATCONTENT  [ID : "+long_ID_MESYUARATCONTENT+"] Inserted");
				
				List listTindakanCopy = listTindakanCopy(session, ID_MESYUARATCONTENT);
				for(int x = 0; x < listTindakanCopy.size();x++)
				{
					Map y = (Map) listTindakanCopy.get(x);
					String ID_MESYUARATTINDAKAN = (String) y.get("ID_MESYUARATTINDAKAN");
					long long_ID_MESYUARATTINDAKAN = DB.getNextID(db, "TBLPDTMESYUARATTINDAKAN_SEQ");
					String sql_copy_tindakan = " INSERT INTO TBLPDTMESYUARATTINDAKAN ( "+
							" ID_MESYUARATTINDAKAN, ID_MESYUARATCONTENT, BAHAGIAN,  "+
							" EMEL, CATATAN, ID_STATUS,  "+
							" ID_MASUK,  TARIKH_MASUK,  "+
							" ID_MESYUARATUTAMA, COUNT_EMEL,EMEL_ERROR) " +
							" SELECT  " +
							" "+long_ID_MESYUARATTINDAKAN+", "+long_ID_MESYUARATCONTENT+", T.BAHAGIAN,  " +
							" T.EMEL, T.CATATAN, T.ID_STATUS,  " +
							" "+USER_ID_SYSTEM+", SYSDATE,  " +
							"  "+long_ID_MESYUARATUTAMA+", T.COUNT_EMEL, T.EMEL_ERROR " +
							" FROM TBLPDTMESYUARATTINDAKAN T WHERE ID_MESYUARATTINDAKAN = '"+ID_MESYUARATTINDAKAN+"' ";					
					stmt.executeUpdate(sql_copy_tindakan);
					myLogger.info("sql_copy_tindakan : "+sql_copy_tindakan);
					AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATTINDAKAN  [ID : "+long_ID_MESYUARATTINDAKAN+"] Inserted");
				}
			}
			
			List listDokumenCopy = listDokumenCopy(session, ID_MESYUARATUTAMA);
			for(int k = 0; k < listDokumenCopy.size();k++)
			{
				Map q = (Map) listDokumenCopy.get(k);
				String ID_MESYUARATDOKUMEN = (String) q.get("ID_MESYUARATDOKUMEN");
				long long_ID_MESYUARATDOKUMEN = DB.getNextID(db, "TBLPDTMESYUARATDOKUMEN_SEQ");
				String sql_copy_dokumen = " INSERT INTO TBLPDTMESYUARATDOKUMEN ( "+
						" ID_MESYUARATDOKUMEN, ID_MESYUARATUTAMA, CONTENT,  "+
						" NAMA_DOC, JENIS_MIME, ID_MASUK, TARIKH_MASUK) " +
						" SELECT  " +
						" "+long_ID_MESYUARATDOKUMEN+", "+long_ID_MESYUARATUTAMA+", T.CONTENT,  " +
						" T.NAMA_DOC, T.JENIS_MIME, "+USER_ID_SYSTEM+", SYSDATE " +
						" FROM TBLPDTMESYUARATDOKUMEN T WHERE ID_MESYUARATDOKUMEN = '"+ID_MESYUARATDOKUMEN+"' ";					
				stmt.executeUpdate(sql_copy_dokumen);
				myLogger.info("sql_copy_dokumen : "+sql_copy_dokumen);
				AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATDOKUMEN  [ID : "+long_ID_MESYUARATDOKUMEN+"] Inserted");
			}
			
			
			conn.commit();
			
			
			
			//listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg",(String) viewPengguna.get("ID_NEGERI"));
			
			//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
			/*
			email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
			for(int i = 0; i < listPenggunaMengikutRole.size();i++)
			   {			   
				   Map m = (Map) listPenggunaMengikutRole.get(i);
				   myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
				   email.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");			  
			 }
			*/
			
			
			
			
			
			/*
			String TAJUK_MESYUARAT = getParam("editMainDirField_"+ID_MESYUARATUTAMA);
			String TAHUN = getParam("editTahunField_"+ID_MESYUARATUTAMA);
			String BILANGAN = getParam("editBilanganField_"+ID_MESYUARATUTAMA);
			String STATUS = getParam("editStatusMesyuarat_"+ID_MESYUARATUTAMA);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(!ID_MESYUARATUTAMA.equals("") && !ID_MESYUARATUTAMA.equals(null))
			{
				long_ID_MESYUARATUTAMA = Long.parseLong(ID_MESYUARATUTAMA);
				r.update("ID_MESYUARATUTAMA", long_ID_MESYUARATUTAMA);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			}
			else
			{
				long_ID_MESYUARATUTAMA = DB.getNextID(db, "TBLPDTMESYUARATUTAMA_SEQ");
				r.add("ID_MESYUARATUTAMA", long_ID_MESYUARATUTAMA);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("ID_MESYUARATUTAMA_PREV","");				
			}
			r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT.toUpperCase());	
			r.add("TAHUN",TAHUN);	
			r.add("BILANGAN", BILANGAN);
			r.add("FLAG_AKTIF", STATUS);
			if(!ID_MESYUARATUTAMA.equals("") && !ID_MESYUARATUTAMA.equals(null))
			{
				sql = r.getSQLUpdate("TBLPDTMESYUARATUTAMA");		
				myLogger.info("UPDATE TBLPDTMESYUARATUTAMA : "+sql);
			}
			else
			{
				sql = r.getSQLInsert("TBLPDTMESYUARATUTAMA");		
				myLogger.info("INSERT TBLPDTMESYUARATUTAMA : "+sql);
			}
			stmt.executeUpdate(sql);			
			
			AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATUTAMA  [ID : "+long_ID_MESYUARATUTAMA+"] Inserted");
			*/
			
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List listContentCopy(HttpSession session, String ID_MESYUARATUTAMA)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listContentCopy = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = " SELECT "+
					" T.ID_MESYUARATCONTENT "+ 
					" FROM TBLPDTMESYUARATCONTENT T WHERE ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"'  ";
			
			myLogger.info(" SQL : listContentCopy :"+ sql);			
			rs = stmt.executeQuery(sql);
			listContentCopy = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));
				listContentCopy.add(h);
			}
			return listContentCopy;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List listTindakanCopy(HttpSession session, String ID_MESYUARATCONTENT)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTindakanCopy = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = "SELECT  T.ID_MESYUARATTINDAKAN FROM TBLPDTMESYUARATTINDAKAN T WHERE ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"'  ";
			
			myLogger.info(" SQL : listTindakanCopy :"+ sql);			
			rs = stmt.executeQuery(sql);
			listTindakanCopy = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_MESYUARATTINDAKAN",rs.getString("ID_MESYUARATTINDAKAN") == null ? "" : rs.getString("ID_MESYUARATTINDAKAN"));
				listTindakanCopy.add(h);
			}
			return listTindakanCopy;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List listDokumenCopy(HttpSession session, String ID_MESYUARATUTAMA)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listDokumenCopy = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = "SELECT T.ID_MESYUARATDOKUMEN FROM TBLPDTMESYUARATDOKUMEN T WHERE ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
			
			myLogger.info(" SQL : listTindakanCopy :"+ sql);			
			rs = stmt.executeQuery(sql);
			listDokumenCopy = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_MESYUARATDOKUMEN",rs.getString("ID_MESYUARATDOKUMEN") == null ? "" : rs.getString("ID_MESYUARATDOKUMEN"));
				listDokumenCopy.add(h);
			}
			return listDokumenCopy;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String saveTajukUtama(HttpSession session,String ID_MESYUARATUTAMA,String ID_BAHAGIAN) throws Exception {
		Connection conn = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		Db db = null;
		String sql = "";
		long long_ID_MESYUARATUTAMA = 0;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String TAJUK_MESYUARAT = getParam("editMainDirField_"+ID_MESYUARATUTAMA);
			String TAHUN = getParam("editTahunField_"+ID_MESYUARATUTAMA);
			String BILANGAN = getParam("editBilanganField_"+ID_MESYUARATUTAMA);
			String STATUS = getParam("editStatusMesyuarat_"+ID_MESYUARATUTAMA);
			String PIC = getParam("editPIC_"+ID_MESYUARATUTAMA);
			String EMEL_PIC = "";
			if(ID_MESYUARATUTAMA.equals(""))
			{
				EMEL_PIC = getParam("editEMEL_PIC_X000000X");
			}else
			{
				EMEL_PIC = getParam("editEMEL_PIC_"+ID_MESYUARATUTAMA);
			}
			String TEL_PIC = getParam("editTEL_PIC_"+ID_MESYUARATUTAMA);
			myLogger.info(" EMEL_PIC BEFORE : "+EMEL_PIC);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			
			if(!ID_MESYUARATUTAMA.equals("") && !ID_MESYUARATUTAMA.equals(null))
			{
				
				long_ID_MESYUARATUTAMA = Long.parseLong(ID_MESYUARATUTAMA);
				r.update("ID_MESYUARATUTAMA", long_ID_MESYUARATUTAMA);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				
			}
			else
			{
				long_ID_MESYUARATUTAMA = DB.getNextID(db, "TBLPDTMESYUARATUTAMA_SEQ");
				r.add("ID_MESYUARATUTAMA", long_ID_MESYUARATUTAMA);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("ID_MESYUARATUTAMA_PREV","");
				r.add("ID_BAHAGIAN",ID_BAHAGIAN);
				
			}
			EMEL_PIC = EMEL_PIC.replaceAll("X000000X", long_ID_MESYUARATUTAMA+"");
			myLogger.info(" EMEL_PIC AFTER : "+EMEL_PIC);
			r.add("EMEL_PIC",EMEL_PIC);
			r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT.toUpperCase());	
			r.add("TAHUN",TAHUN);	
			r.add("BILANGAN", BILANGAN);
			r.add("FLAG_AKTIF", STATUS);
			r.add("PIC", PIC.toUpperCase());
			
			r.add("TEL_PIC", TEL_PIC);
			if(!ID_MESYUARATUTAMA.equals("") && !ID_MESYUARATUTAMA.equals(null))
			{
				sql = r.getSQLUpdate("TBLPDTMESYUARATUTAMA");		
				myLogger.info("UPDATE TBLPDTMESYUARATUTAMA : "+sql);
			}
			else
			{
				sql = r.getSQLInsert("TBLPDTMESYUARATUTAMA");		
				myLogger.info("INSERT TBLPDTMESYUARATUTAMA : "+sql);
			}
			stmt.executeUpdate(sql);			
			conn.commit();
			AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATUTAMA  [ID : "+long_ID_MESYUARATUTAMA+"] Inserted");
			
			
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return long_ID_MESYUARATUTAMA+"";
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
	
	
	
	
	
	 public void checkChildDeleteSub(HttpSession session,String ID_MESYUARATCONTENT) throws Exception {	
		 
		 myLogger.info(" DELETE : "+ID_MESYUARATCONTENT);
		 deleteSub(session,ID_MESYUARATCONTENT);
		 List listSubByID_REFER = listSubByID_REFER(ID_MESYUARATCONTENT);
		 for(int i = 0; i < listSubByID_REFER.size();i++)
		 {			   
			   Map m = (Map) listSubByID_REFER.get(i);
			   checkChildDeleteSub(session,(String) m.get("ID_MESYUARATCONTENT"));			   
		 }	 
	 }
	 
	 public void deleteSub(HttpSession session,String ID_MESYUARATCONTENT) throws Exception {
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
				r.add("ID_MESYUARATCONTENT",ID_MESYUARATCONTENT);
				sql = r.getSQLDelete("TBLPDTMESYUARATCONTENT");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATCONTENT [ID : "+ID_MESYUARATCONTENT+"] Deleted");
				
				r.clear();
				r.add("ID_MESYUARATCONTENT",ID_MESYUARATCONTENT);
				sql = r.getSQLDelete("TBLPDTMESYUARATTINDAKAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATTINDAKAN [ID : "+ID_MESYUARATCONTENT+"] Deleted");
				
				/*
				r.clear();
				r.add("ID_MESYUARATCONTENT",ID_MESYUARATCONTENT);
				sql = r.getSQLDelete("TBLPDTMESYUARATDOKUMEN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATTINDAKAN [ID_MESYUARATCONTENT : "+ID_MESYUARATCONTENT+"] Deleted");
				*/
				
				conn.commit();
			
			} 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
	 }
				
	
	
	
	 
	 
	 
	 public void deleteTindakan(HttpSession session,String ID_MESYUARATTINDAKAN,String NAMA) throws Exception {
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
				r.add("ID_MESYUARATTINDAKAN",ID_MESYUARATTINDAKAN);
				sql = r.getSQLDelete("TBLPDTMESYUARATTINDAKAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATTINDAKAN ["+NAMA+"] Deleted");
			
				conn.commit();
			
			} 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
			}
	 
	 
	 @SuppressWarnings("unchecked")
		public boolean checkDuplicateBahagian(HttpSession session,String ID_MESYUARATTINDAKAN,String ID_MESYUARATCONTENT,String BAHAGIAN,String ID_MESYUARATUTAMA)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateDocName = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_MESYUARATTINDAKAN) AS CNT FROM TBLPDTMESYUARATTINDAKAN T  " +
						" WHERE T.ID_MESYUARATTINDAKAN IS NOT NULL ";				
						if(!ID_MESYUARATTINDAKAN.equals(""))
						{
							sql += "  AND NVL(T.ID_MESYUARATTINDAKAN,0) <> '"+ID_MESYUARATTINDAKAN+"'  ";
						}						
						sql += " AND UPPER(T.BAHAGIAN) = '"+BAHAGIAN.toUpperCase()+"' AND T.ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"' AND T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
				
				myLogger.info(" SQL : checkDuplicateDocName :"+ sql);			
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					
					if(rs.getInt("CNT")>0)
					{
						checkDuplicateDocName = true;
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
			return checkDuplicateDocName;
		}
	 
	 @SuppressWarnings("unchecked")
		public boolean checkDuplicateSubDir(HttpSession session,String ID_REFER,String ID_MESYUARATCONTENT,String ID_MESYUARATUTAMA,String KETERANGAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateSubDir = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_MESYUARATCONTENT) AS CNT FROM TBLPDTMESYUARATCONTENT T  " +
						" WHERE T.ID_MESYUARATCONTENT IS NOT NULL ";
				
						if(!ID_MESYUARATCONTENT.equals(""))
						{
							sql += "  AND NVL(T.ID_MESYUARATCONTENT,0) <> '"+ID_MESYUARATCONTENT+"'  ";
						}						
						if(!ID_REFER.equals(""))
						{
							sql += "  AND NVL(T.ID_REFER,0) = '"+ID_REFER+"'  ";
						}
						sql += " AND UPPER(T.KETERANGAN) = '"+replace(KETERANGAN.toUpperCase())+"' " +
						" AND T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
				
				myLogger.info(" SQL : checkDuplicateSubDir :"+ sql);			
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					
					if(rs.getInt("CNT")>0)
					{
						checkDuplicateSubDir = true;
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
			return checkDuplicateSubDir;
		}
	 
	 
	 @SuppressWarnings("unchecked")
		public boolean checkDuplicateMainDir(HttpSession session,String ID_MESYUARATUTAMA,
				String TAJUK_MESYUARAT,String TAHUN, String BILANGAN,String ID_BAHAGIAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateMainDir = false;
			String sql = "";
			
			if(TAHUN.equals(""))
			{
				TAHUN = "0";
			}
			if(BILANGAN.equals(""))
			{
				BILANGAN = "0";
			}
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_MESYUARATUTAMA) AS CNT FROM TBLPDTMESYUARATUTAMA T  " +
						" WHERE T.ID_MESYUARATUTAMA IS NOT NULL AND ID_BAHAGIAN = '"+ID_BAHAGIAN+"' ";				
						if(!ID_MESYUARATUTAMA.equals(""))
						{
							sql += "  AND NVL(T.ID_MESYUARATUTAMA,0) <> '"+ID_MESYUARATUTAMA+"'  ";
						}
						sql += " AND UPPER(T.TAJUK_MESYUARAT) = '"+replace(TAJUK_MESYUARAT.toUpperCase())+"' ";
						sql += " AND T.TAHUN = "+TAHUN+" ";
						sql += " AND T.BILANGAN = "+BILANGAN+" ";
				
				myLogger.info(" SQL : checkDuplicateMainDir :"+ sql);			
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					
					if(rs.getInt("CNT")>0)
					{
						checkDuplicateMainDir = true;
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
			return checkDuplicateMainDir;
		}
		
	 public void deleteLampiran(HttpSession session,String ID_MESYUARATDOKUMEN,String NAMA) throws Exception {
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
				r.add("ID_MESYUARATDOKUMEN",ID_MESYUARATDOKUMEN);
				sql = r.getSQLDelete("TBLPDTMESYUARATDOKUMEN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATDOKUMEN ["+NAMA+"] Deleted");
			
				conn.commit();
			
			} 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
			}
	 
	 public String saveUpdateSubDir(HttpSession session,String ID_REFER,String ID_MESYUARATCONTENT,String ID_MESYUARATUTAMA,String KETERANGAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
				String USER_ROLE = (String) session.getAttribute("myrole");
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
				r.add("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
				r.add("ID_REFER", ID_REFER);
				r.add("KETERANGAN", KETERANGAN.toUpperCase());				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTMESYUARATCONTENT");		
				myLogger.info("V3 : UPDATE TBLPDTMESYUARATCONTENT : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTMESYUARATCONTENT  [ID : "+ID_MESYUARATCONTENT+"] Updated");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
	 
	 public String saveInsertTindakan(HttpSession session,String ID_MESYUARATCONTENT,String ID_MESYUARATUTAMA,String BAHAGIAN,
			 String EMEL,String EMEL_CC,String ID_STATUS,String CATATAN, String ID_BAHAGIAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");		
				
				
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				long id_p = DB.getNextID("TBLPDTMESYUARATTINDAKAN_SEQ");	 
				r.add("ID_MESYUARATTINDAKAN", id_p);
				r.add("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);				
				r.add("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
				r.add("BAHAGIAN", BAHAGIAN.toUpperCase());
				r.add("EMEL", EMEL.replaceAll("X000000X", id_p+""));
				r.add("EMEL_CC", EMEL_CC.replaceAll("X000000X", id_p+""));
				r.add("ID_STATUS", ID_STATUS);
				r.add("CATATAN", CATATAN.toUpperCase());
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));				
				sql = r.getSQLInsert("TBLPDTMESYUARATTINDAKAN");		
				myLogger.info("V3 : INSERT TBLPDTMESYUARATTINDAKAN : "+sql);					
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATTINDAKAN  [ID : "+ID_MESYUARATCONTENT+"] Inserted");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
	 
	 
	 public String saveTindakanEmelStatus(HttpSession session,String ID_MESYUARATTINDAKAN,int countEmel,String emelError) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			//editEmelBahagian,editStatusBahagian,editCatatanBahagian
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_MESYUARATTINDAKAN", ID_MESYUARATTINDAKAN);
				r.add("COUNT_EMEL", countEmel);
				r.add("EMEL_ERROR", emelError.toUpperCase());	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));				
				sql = r.getSQLUpdate("TBLPDTMESYUARATTINDAKAN");		
				myLogger.info("V3 : UPDATE TBLPDTMESYUARATTINDAKAN emel status : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTMESYUARATTINDAKAN  [ID : "+ID_MESYUARATTINDAKAN+"] Updated");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
	 
	 
	 public String saveTindakan(HttpSession session,String ID_MESYUARATCONTENT,String ID_MESYUARATTINDAKAN,String BAHAGIAN,String EMEL,
			 String EMEL_CC,String ID_STATUS, String CATATAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			//editEmelBahagian,editStatusBahagian,editCatatanBahagian
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_MESYUARATTINDAKAN", ID_MESYUARATTINDAKAN);
				r.add("ID_MESYUARATCONTENT", ID_MESYUARATCONTENT);
				r.add("BAHAGIAN", BAHAGIAN.toUpperCase());	
				r.add("EMEL", EMEL);
				r.add("EMEL_CC", EMEL_CC);
				r.add("CATATAN", CATATAN.toUpperCase());	
				r.add("ID_STATUS", ID_STATUS);	//1-dalam tindakan, 2-selesai
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTMESYUARATTINDAKAN");		
				myLogger.info("V3 : UPDATE TBLPDTMESYUARATTINDAKAN : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTMESYUARATTINDAKAN  [ID : "+ID_MESYUARATTINDAKAN+"] Updated");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
	 
	 public String saveUpdateMainDir(HttpSession session,String ID_MESYUARATUTAMA,String TAJUK_MESYUARAT,String TAHUN,String BILANGAN,String FLAG_AKTIF,
			String PIC,String EMEL_PIC,String TEL_PIC) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
				r.add("TAJUK_MESYUARAT", TAJUK_MESYUARAT.toUpperCase());
				r.add("TAHUN", TAHUN);
				r.add("BILANGAN", BILANGAN);
				r.add("PIC", PIC);
				r.add("EMEL_PIC", EMEL_PIC);
				r.add("TEL_PIC", TEL_PIC);
				r.add("FLAG_AKTIF", FLAG_AKTIF);				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTMESYUARATUTAMA");		
				myLogger.info("V3 : UPDATE TBLPDTMESYUARATUTAMA : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTMESYUARATUTAMA  [ID : "+ID_MESYUARATUTAMA+"] Updated");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
	 
	 
	 public String saveInsertSubDir(HttpSession session,String ID_REFER,String ID_MESYUARATCONTENT,String ID_MESYUARATUTAMA,String KETERANGAN,String LAYER) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				long id_p = DB.getNextID("TBLPDTMESYUARATCONTENT_SEQ");	 
				r.add("ID_MESYUARATCONTENT", id_p);
				r.add("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);
				r.add("ID_REFER", ID_REFER);
				r.add("LAYER", LAYER);				
				r.add("KETERANGAN", KETERANGAN.toUpperCase());				
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));				
				sql = r.getSQLInsert("TBLPDTMESYUARATCONTENT");		
				myLogger.info("V3 : INSERT TBLPDTMESYUARATCONTENT : "+sql);					
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"INS","TBLPDTMESYUARATCONTENT  [ID : "+ID_MESYUARATCONTENT+"] Inserted");
				
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
	 
	 public void deleteMainFolder(HttpSession session,String ID_MESYUARATUTAMA) throws Exception {
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
				r.add("ID_MESYUARATUTAMA",ID_MESYUARATUTAMA);
				sql = r.getSQLDelete("TBLPDTMESYUARATUTAMA");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATUTAMA [ID_MESYUARATUTAMA : "+ID_MESYUARATUTAMA+"] Deleted");
			
				r.clear();
				r.add("ID_MESYUARATUTAMA",ID_MESYUARATUTAMA);
				sql = r.getSQLDelete("TBLPDTMESYUARATDOKUMEN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATDOKUMEN [ID_MESYUARATUTAMA : "+ID_MESYUARATUTAMA+"] Deleted");
			
					
				r.clear();
				r.add("ID_MESYUARATUTAMA",ID_MESYUARATUTAMA);
				sql = r.getSQLDelete("TBLPDTMESYUARATCONTENT");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATCONTENT [ID_MESYUARATUTAMA : "+ID_MESYUARATUTAMA+"] Deleted");
			
				r.clear();
				r.add("ID_MESYUARATUTAMA",ID_MESYUARATUTAMA);
				sql = r.getSQLDelete("TBLPDTMESYUARATTINDAKAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTMESYUARATTINDAKAN [ID_MESYUARATUTAMA : "+ID_MESYUARATUTAMA+"] Deleted");
			
				
				conn.commit();
			
			} 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
			}
	 
	 
	 @SuppressWarnings("unchecked")
		public List listSubByID_REFER(String ID_REFER)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listSubByID_REFER = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT "+
						" T.ID_MESYUARATCONTENT, T.ID_MESYUARATUTAMA, T.KETERANGAN,  "+
						" T.LAYER, T.ID_REFER, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
						" FROM TBLPDTMESYUARATCONTENT T WHERE T.ID_REFER = '"+ID_REFER+"' ";
				
				/*
				sql = " SELECT T.ID_MESYUARATCONTENT, T.ID_MESYUARATUTAMA, T.TAJUK, "+
						" T.LAYER, T.ID_REFER, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
						" FROM TBLPDTMESYUARATCONTENT T " +
						" WHERE T.ID_REFER = '"+ID_REFER+"' "; 	
						*/			
				myLogger.info(" SQL : listSubByID_REFER :"+ sql);			
				rs = stmt.executeQuery(sql);
				listSubByID_REFER = Collections.synchronizedList(new ArrayList());
				Map h = null;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));			
					listSubByID_REFER.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listSubByID_REFER;
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

	    public String replace(String s)
	    {
	        return dblch(s, '\'');
	    }
	    
	    
	    
	    @SuppressWarnings("unchecked")
		public List listFolderLampiran(HttpSession session, String ID_MESYUARATUTAMA, String CarianLampiran)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listFolderLampiran = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT T.ID_MESYUARATDOKUMEN, T.ID_MESYUARATUTAMA, T.CONTENT, "+
						" T.NAMA_DOC, T.JENIS_MIME, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
						" FROM TBLPDTMESYUARATDOKUMEN T " +
						" WHERE T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";						
						if(!CarianLampiran.equals(""))
						{
							sql += " AND UPPER(T.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' ";
						}
				sql += " ORDER BY T.NAMA_DOC "; 
				
			
				
				
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
					h.put("ID_MESYUARATDOKUMEN",rs.getString("ID_MESYUARATDOKUMEN") == null ? "" : rs.getString("ID_MESYUARATDOKUMEN"));	
					h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));	
					h.put("CONTENT",rs.getString("CONTENT") == null ? "" : rs.getString("CONTENT"));	
					h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));	
					h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));	
					listFolderLampiran.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listFolderLampiran;
		}
	    
	    
	    @SuppressWarnings("unchecked")
		public List listBahagian(HttpSession session, String SEARCH, String ID_BAHAGIAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listBahagian = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT DISTINCT UPPER(T.BAHAGIAN) AS BAHAGIAN FROM TBLPDTMESYUARATTINDAKAN T, TBLPDTMESYUARATUTAMA M " +
						" WHERE T.ID_MESYUARATTINDAKAN IS NOT NULL " +
						" AND M.ID_BAHAGIAN = '"+ID_BAHAGIAN+"' AND T.ID_MESYUARATUTAMA = M.ID_MESYUARATUTAMA ";						
						if(!SEARCH.equals(""))
						{
							sql += " AND UPPER(T.BAHAGIAN) LIKE '%"+SEARCH.toUpperCase()+"%' ";
						}
				sql += " ORDER BY BAHAGIAN ";
				
				myLogger.info(" SQL : listBahagian :"+ sql);			
				rs = stmt.executeQuery(sql);
				listBahagian = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN").toUpperCase());	
					listBahagian.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listBahagian;
		}
	    
	    
	    /*
	    @SuppressWarnings("unchecked")
		public List listEmel(HttpSession session, String BAHAGIAN, String SEARCH)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listEmel = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT DISTINCT TO_CHAR(EMEL) AS EMEL FROM TBLPDTMESYUARATTINDAKAN " +
						" WHERE ID_MESYUARATTINDAKAN IS NOT NULL ";		
						if(!BAHAGIAN.equals(""))
						{
							sql += " AND UPPER(BAHAGIAN) = '"+BAHAGIAN.toUpperCase()+"' ";
						}
						if(!SEARCH.equals(""))
						{
							sql += " AND UPPER(EMEL) LIKE '%"+SEARCH.toUpperCase()+"%' ";
						}
				sql += " ORDER BY TO_CHAR(EMEL)  ";
				
				myLogger.info(" SQL : listEmel :"+ sql);			
				rs = stmt.executeQuery(sql);
				listEmel = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
					listEmel.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listEmel;
		}
		*/
	    
	    @SuppressWarnings("unchecked")
		public List listEmel(HttpSession session,String SEARCH,String BAHAGIAN,String PIC,String current_stored_emel,String type,String ID_BAHAGIAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listEmelPIC = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				if(type.equals("UTAMA_PIC"))
				{
					sql = " SELECT DISTINCT TO_CHAR(EMEL_PIC) AS EMEL FROM TBLPDTMESYUARATUTAMA " +
							" WHERE ID_MESYUARATUTAMA IS NOT NULL AND EMEL_PIC IS NOT NULL AND ID_BAHAGIAN = '"+ID_BAHAGIAN+"' ";	
							if(!PIC.equals(""))
							{
								if(!SEARCH.equals(""))
								{
									sql += " AND UPPER(EMEL_PIC) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
								else
								{
									sql += " AND PIC = '"+PIC+"' ";
									sql += " AND UPPER(EMEL_PIC) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
								
							}
							else {
								if(!SEARCH.equals(""))
								{
									sql += " AND UPPER(EMEL_PIC) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
							}
					sql += " ORDER BY TO_CHAR(EMEL_PIC) ";
				}
				else if(type.equals("TINDAKAN_TO"))
				{
					sql = " SELECT DISTINCT TO_CHAR(T.EMEL) AS EMEL FROM TBLPDTMESYUARATTINDAKAN T, TBLPDTMESYUARATUTAMA M " +
							" WHERE T.ID_MESYUARATTINDAKAN IS NOT NULL AND T.EMEL IS NOT NULL " +
							" AND T.ID_MESYUARATUTAMA = M.ID_MESYUARATUTAMA " +
							" AND M.ID_BAHAGIAN = '"+ID_BAHAGIAN+"' ";
							if(!BAHAGIAN.equals(""))
							{
								if(!SEARCH.equals(""))
								{
									sql += " AND UPPER(T.EMEL) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
								else
								{
									sql += " AND UPPER(T.BAHAGIAN) = '"+BAHAGIAN.toUpperCase()+"' ";
									sql += " AND UPPER(T.EMEL) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
								
							}
							else {
								if(!SEARCH.equals(""))
								{
									sql += " AND UPPER(T.EMEL) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
							}
							
					sql += " ORDER BY TO_CHAR(EMEL)  ";
				}
				else if(type.equals("TINDAKAN_CC"))
				{
					sql = " SELECT DISTINCT TO_CHAR(T.EMEL_CC) AS EMEL FROM TBLPDTMESYUARATTINDAKAN T, TBLPDTMESYUARATUTAMA M " +
							" WHERE T.ID_MESYUARATTINDAKAN IS NOT NULL AND T.EMEL IS NOT NULL " +
							" AND T.ID_MESYUARATUTAMA = M.ID_MESYUARATUTAMA " +
							" AND M.ID_BAHAGIAN = '"+ID_BAHAGIAN+"' ";
					
							if(!BAHAGIAN.equals(""))
							{
								if(!SEARCH.equals(""))
								{
									sql += " AND UPPER(T.EMEL_CC) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
								else
								{
									sql += " AND UPPER(T.BAHAGIAN) = '"+BAHAGIAN.toUpperCase()+"' ";
									sql += " AND UPPER(T.EMEL_CC) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
								
							}
							else {
								if(!SEARCH.equals(""))
								{
									sql += " AND UPPER(T.EMEL_CC) LIKE '%"+SEARCH.toUpperCase()+"%' ";
								}
							}
							
					sql += " ORDER BY TO_CHAR(T.EMEL_CC)  ";
				}
				
				myLogger.info(type+" :  SQL : listEmelPIC :"+ sql);			
				rs = stmt.executeQuery(sql);
				
				Map h = null;
				listEmelPIC = Collections.synchronizedList(new ArrayList());
				while (rs.next()) {
					String emel_to_check = rs.getString("EMEL") == null ? "" : rs.getString("EMEL");
					
					if(!emel_to_check.equals(""))
					{
						
						List<String> myEmelList = getTagValues(emel_to_check);
						myLogger.info(" ALL emel_to_check:"+getTagValues(emel_to_check));
						if(myEmelList.size()>0)
						{
							for (int i=0;i < myEmelList.size();i++)
							{
								h = Collections.synchronizedMap(new HashMap());
								String emel = myEmelList.get(i);
								//myLogger.info(" SINGLE EMEL PIC :"+emel);
								h.put("EMEL",emel);
								
								boolean check = false;
								for(int g = 0; g < listEmelPIC.size();g++)
								{
									Map m = (Map) listEmelPIC.get(g);
									String emelstored = (String) m.get("EMEL");
									
									if(emel.toLowerCase().equals(emelstored.toLowerCase()))
									{
										check = true;
									}
												
								}	
								
															
								List<String> myEmelList_stored = getTagValues(current_stored_emel);
								myLogger.info("RECORDED EMEL : "+current_stored_emel);
								for(int k = 0; k < myEmelList_stored.size();k++)
								{
									String emel_stored = myEmelList_stored.get(k);
									
									if(emel.toLowerCase().equals(emel_stored.toLowerCase()))
									{
										check = true;
									}
												
								}	
								
								
								if(check==false)
								{
									listEmelPIC.add(h);
								}
							}
						}
					}
					//h.put("EMEL",rs.getString("EMEL_PIC") == null ? "" : rs.getString("EMEL_PIC"));	
					
				}				
				/*
				listEmelPIC = Collections.synchronizedList(new ArrayList());
				h = Collections.synchronizedMap(new HashMap());
				listEmelPIC.add(h);
				*/
				
				myLogger.info(" :::  listEmel :"+listEmelPIC);

			} finally {
				
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listEmelPIC;
		}
	    
	    @SuppressWarnings("unchecked")
		public List listPIC(HttpSession session,String SEARCH,String ID_BAHAGIAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPIC = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT DISTINCT TO_CHAR(PIC) AS PIC FROM TBLPDTMESYUARATUTAMA " +
						" WHERE ID_MESYUARATUTAMA IS NOT NULL AND ID_BAHAGIAN = '"+ID_BAHAGIAN+"' ";	
						
						if(!SEARCH.equals(""))
						{
							sql += " AND UPPER(PIC) LIKE '"+SEARCH.toUpperCase()+"%' ";
						}
				sql += " ORDER BY TO_CHAR(PIC) ";
				
				myLogger.info(" SQL : listPIC :"+ sql);			
				rs = stmt.executeQuery(sql);
				listPIC = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));	
					listPIC.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listPIC;
		}
	    
	    
	    @SuppressWarnings("unchecked")
		public List listTelPIC(HttpSession session,String SEARCH,String PIC,String ID_BAHAGIAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listTelPIC = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT DISTINCT TEL_PIC AS TEL_PIC FROM TBLPDTMESYUARATUTAMA " +
						" WHERE ID_MESYUARATUTAMA IS NOT NULL AND ID_BAHAGIAN = '"+ID_BAHAGIAN+"' ";	
						if(!PIC.equals(""))
						{
							sql += " AND PIC = '"+PIC+"' ";
						}
						if(!SEARCH.equals(""))
						{
							sql += " AND TEL_PIC LIKE '"+SEARCH.toUpperCase()+"%' ";
						}
				sql += " ORDER BY TEL_PIC ";
				
				myLogger.info(" SQL : listTelPIC :"+ sql);			
				rs = stmt.executeQuery(sql);
				listTelPIC = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("TEL_PIC",rs.getString("TEL_PIC") == null ? "" : rs.getString("TEL_PIC"));	
					listTelPIC.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return listTelPIC;
		}
	    
	    private void saveLampiran(HttpSession session,String ID_MESYUARATUTAMA) throws Exception {
	   	 
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
	        	  myLogger.info(" ID_MESYUARATUTAMA : "+ID_MESYUARATUTAMA+" item : "+item);
	        	  saveLampiranDB(item,ID_MESYUARATUTAMA);
	        	 
	          }
	        }
	      }
	    
	    
	    private void saveLampiranDB(FileItem item,String ID_MESYUARATUTAMA) throws Exception {
		    HttpSession session = request.getSession();	
		    String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
	  		Db db = null;
	        try {		        	
	        	long id_lampiran = DB.getNextID("TBLPDTMESYUARATDOKUMEN_SEQ");	        	
	        	db = new Db();		        	
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPDTMESYUARATDOKUMEN ( "+
		    		  " ID_MESYUARATDOKUMEN, ID_MESYUARATUTAMA, CONTENT,  "+
		    		  " NAMA_DOC, JENIS_MIME, ID_MASUK,  "+
		    		  " TARIKH_MASUK)  "+
		    		  " VALUES (?,?,?,?,?,?,SYSDATE) ");
	        	
	        	
	        	String nama_asal_doc = item.getName().substring(0, item.getName().indexOf("."));
	        	String nama_doc = nama_asal_doc;	        	
	        	int copy_count = 0;
	        	do {
     			   // Statements
	        		myLogger.info(" ***** nama_doc ada duplicate : "+nama_doc);
	        		if(checkDuplicateDocName(session,"",ID_MESYUARATUTAMA,nama_doc.toUpperCase())==true)
	        		{
		        		copy_count++;
		        		nama_doc = nama_asal_doc + " COPY("+copy_count+")";
		        		myLogger.info(" ***** nama_doc baru : "+nama_doc);
	        		}
	        	}while(checkDuplicateDocName(session,"",ID_MESYUARATUTAMA,nama_doc.toUpperCase())==true);
	        	
	        	myLogger.info(" ***** nama_doc utk disimpan : "+nama_doc);
	        	
	        	
	        	
	        	ps.setString(1,id_lampiran+"");
	        	ps.setString(2,ID_MESYUARATUTAMA);
	        	ps.setBinaryStream(3, item.getInputStream(),(int)item.getSize());
	        	ps.setString(4, nama_doc.toUpperCase());
	        	ps.setString(5,item.getContentType());
	        	ps.setString(6,USER_ID_SYSTEM);
	        	ps.executeUpdate();	        	
	            con.commit(); 
	            
	            AuditTrail.logActivity(this,session,"INS","TBLPDTPANDANGANLAMPIRAN ["+item.getName()+"] Insert");
		    } finally {
			      if (db != null) db.close();
		    }
		    
	  }		
	    
	    
	    @SuppressWarnings("unchecked")
		public boolean checkDuplicateDocName(HttpSession session,String ID_MESYUARATDOKUMEN,String ID_MESYUARATUTAMA,String NAMA_DOC)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateDocName = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_MESYUARATDOKUMEN) AS CNT FROM TBLPDTMESYUARATDOKUMEN T  " +
						" WHERE T.ID_MESYUARATDOKUMEN IS NOT NULL ";
				
						if(!ID_MESYUARATDOKUMEN.equals(""))
						{
							sql += "  AND NVL(T.ID_MESYUARATDOKUMEN,0) <> '"+ID_MESYUARATDOKUMEN+"'  ";
						}
						
						sql += " AND UPPER(T.NAMA_DOC) = '"+NAMA_DOC.toUpperCase()+"' AND T.ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
				
				myLogger.info(" SQL : checkDuplicateDocName :"+ sql);			
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					
					if(rs.getInt("CNT")>0)
					{
						checkDuplicateDocName = true;
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
			return checkDuplicateDocName;
		}
	    
	    
	    public Hashtable viewLampiran(HttpSession session, String ID_MESYUARATDOKUMEN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			Hashtable h = null;
			try {
				
				
				db = new Db();
				stmt = db.getStatement();
				h = new Hashtable();
					sql = " SELECT T.ID_MESYUARATDOKUMEN, T.ID_MESYUARATUTAMA, T.CONTENT, "+
							" T.NAMA_DOC, T.JENIS_MIME "+
							" FROM TBLPDTMESYUARATDOKUMEN T "+
							" WHERE T.ID_MESYUARATDOKUMEN = '"+ID_MESYUARATDOKUMEN+"' ";
							
					myLogger.info(" viewLampiran :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					
					
					
					while (rs.next()) {
						h.put("ID_MESYUARATUTAMA",rs.getString("ID_MESYUARATUTAMA") == null ? "" : rs.getString("ID_MESYUARATUTAMA"));
						h.put("ID_MESYUARATDOKUMEN",rs.getString("ID_MESYUARATDOKUMEN") == null ? "" : rs.getString("ID_MESYUARATDOKUMEN"));
						h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));
					}
				
				return h;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
	    
	    
	    public String saveUpdateLampiran(HttpSession session,String ID_MESYUARATDOKUMEN,String ID_MESYUARATUTAMA,String NAMA_DOC) throws Exception {
	    	Connection conn = null;
	    	Db db = null;
	    	String returnUSERID = "";
	    	String sql = "";
	    	
	    	
	    	
	    	try {
	    		db = new Db();
	    		conn = db.getConnection();
	    		conn.setAutoCommit(false);
	    		
	    		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
	    		
	    		Statement stmt = db.getStatement();
	    		SQLRenderer r = new SQLRenderer();
	    		
	    		r.update("ID_MESYUARATDOKUMEN", ID_MESYUARATDOKUMEN);
	    		
	    		r.add("ID_MESYUARATUTAMA", ID_MESYUARATUTAMA);	
	    		r.add("NAMA_DOC", NAMA_DOC.toUpperCase());
	    		
	    		r.add("ID_KEMASKINI", USER_ID_SYSTEM);
	    		r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
	    		
	    		sql = r.getSQLUpdate("TBLPDTMESYUARATDOKUMEN");		
	    		myLogger.info("V3 : UPDATE TBLPDTMESYUARATDOKUMEN : "+sql);		    
	    				
	    		stmt.executeUpdate(sql);
	    		conn.commit();
	    		AuditTrail.logActivity(this,session,"UPD","TBLPDTMESYUARATDOKUMEN  [ID : "+ID_MESYUARATDOKUMEN+"] Updated");
	    		
	    		
	    	} 
	    	catch (SQLException se) { 
	    		myLogger.error(se);
	        	try {
	        		conn.rollback();
	        	} catch (SQLException se2) {
	        		throw new Exception("Rollback error:"+se2.getMessage());
	        	}
	        	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
	    	}
	    	catch (Exception re) {
	    		throw re;
	    	}finally {
	    		if (db != null)
	    			db.close();
	    	}
	    	return returnUSERID;
	    }
	    
	    
	    
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
				String PIC = (String)maklumatMesyuarat.get("PIC");
				String TEL_PIC = (String)maklumatMesyuarat.get("TEL_PIC");
				String EMEL_PIC = (String)maklumatMesyuarat.get("EMEL_PIC");
				String error_emel = "";
				
				String penutup = " <br><br><br>Sila beri maklumbalas berkenaan perkara diatas";
				if(!PIC.equals(""))
				{
					penutup += "  kepada <strong>"+PIC+"</strong>";
				}				
				if(!EMEL_PIC.equals(""))
				{
					penutup += " di";
					
					List<String> myEmel_pic = getTagValues(EMEL_PIC);
					myLogger.info(" myEmel_pic :"+myEmel_pic);
					if(myEmel_pic.size()>0)
					{
						for (int i=0;i < myEmel_pic.size();i++)
						{
						/*	if(i>0)
							{
								penutup += ";";
							}
							*/
							penutup += " ["+myEmel_pic.get(i)+"]";
						}
					}
					
				}
				if(!PIC.equals(""))
				{
					if(!EMEL_PIC.equals(""))
					{
						penutup += " atau";
					}				
					penutup += " dengan menghubungi talian <strong>"+TEL_PIC+"</strong>";
				}
				penutup += ". Terima kasih diatas kerjasama anda. ";
				penutup +=" <br><br>Nota : Emel ini dihantar bagi pihak <strong>"+PIC+"</strong> melalui Sistem MyeTaPP. ";
				
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
						String EMEL_CC = (String)maklumatTindakan.get("EMEL_CC");
						int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
						
						List<String> myEmel_to = getTagValues(EMEL);
						myLogger.info(" myEmel_to :"+myEmel_to);
						if(myEmel_to.size()>0)
						{
							email.MULTIPLE_RECIEPIENT = new String[myEmel_to.size()];
							for (int i=0;i < myEmel_to.size();i++)
							{
								myLogger.info(" MULTIPLE_RECIEPIENT myEmel_to :"+myEmel_to.get(i));
								email.MULTIPLE_RECIEPIENT[i] = myEmel_to.get(i);								
							}
						}
						
						List<String> myEmel_cc = getTagValues(EMEL_CC);
						myLogger.info(" myEmel_cc :"+myEmel_cc);
						if(myEmel_cc.size()>0)
						{
							email.TO_CC = new String[myEmel_cc.size()];
							for (int x=0;x < myEmel_cc.size();x++)
							{
								myLogger.info(" TO_CC myEmel_cc :"+myEmel_cc.get(x));
								email.TO_CC[x] = myEmel_cc.get(x);								
							}
						}
						
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
						//email.MULTIPLE_RECIEPIENT = new String[listTindakanByContent.size()];
						String byContent_EMEL="";
						String byContent_EMEL_CC="";
						for(int i = 0; i < listTindakanByContent.size();i++)
						{
							Map m = (Map) listTindakanByContent.get(i);
							ID_MESYUARATTINDAKAN = (String) m.get("ID_MESYUARATTINDAKAN");
							
							Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
							String EMEL = (String)maklumatTindakan.get("EMEL");
							String EMEL_CC = (String)maklumatTindakan.get("EMEL_CC");
							//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
							//email.MULTIPLE_RECIEPIENT[i] = EMEL;	
							byContent_EMEL += EMEL;
							byContent_EMEL_CC += EMEL_CC;
							myLogger.info(" error_emel ::::::: " +error_emel);							
						}
						
						
						List<String> myEmel_to = getTagValues(byContent_EMEL);
						myLogger.info(" myEmel_to :"+myEmel_to);
						if(myEmel_to.size()>0)
						{
							email.MULTIPLE_RECIEPIENT = new String[myEmel_to.size()];
							for (int w=0;w < myEmel_to.size();w++)
							{
								myLogger.info(" MULTIPLE_RECIEPIENT myEmel_to :"+myEmel_to.get(w));
								email.MULTIPLE_RECIEPIENT[w] = myEmel_to.get(w);								
							}
						}
						
						List<String> myEmel_cc = getTagValues(byContent_EMEL_CC);
						myLogger.info(" myEmel_cc :"+myEmel_cc);
						if(myEmel_cc.size()>0)
						{
							email.TO_CC = new String[myEmel_cc.size()];
							for (int x=0;x < myEmel_cc.size();x++)
							{
								myLogger.info(" TO_CC myEmel_cc :"+myEmel_cc.get(x));
								email.TO_CC[x] = myEmel_cc.get(x);								
							}
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
						//email.MULTIPLE_RECIEPIENT = new String[listTindakanByContent.size()];
						String byContent_EMEL="";
						String byContent_EMEL_CC="";
						for(int g = 0; g < listTindakanByContent.size();g++)
						{
							Map t = (Map) listTindakanByContent.get(g);
							ID_MESYUARATTINDAKAN = (String) t.get("ID_MESYUARATTINDAKAN");							
							Hashtable maklumatTindakan = viewTindakan(session,ID_MESYUARATTINDAKAN);
							String EMEL = (String)maklumatTindakan.get("EMEL");
							String EMEL_CC = (String)maklumatTindakan.get("EMEL_CC");
							byContent_EMEL += EMEL;
							byContent_EMEL_CC += EMEL_CC;
							//email.MULTIPLE_RECIEPIENT[g] = EMEL;							
							myLogger.info(" error_emel ::::::: " +error_emel);							
						}
						

						List<String> myEmel_to = getTagValues(byContent_EMEL);
						myLogger.info(" myEmel_to :"+myEmel_to);
						if(myEmel_to.size()>0)
						{
							email.MULTIPLE_RECIEPIENT = new String[myEmel_to.size()];
							for (int w=0;w < myEmel_to.size();w++)
							{
								myLogger.info(" MULTIPLE_RECIEPIENT myEmel_to :"+myEmel_to.get(w));
								email.MULTIPLE_RECIEPIENT[w] = myEmel_to.get(w);								
							}
						}
						
						List<String> myEmel_cc = getTagValues(byContent_EMEL_CC);
						myLogger.info(" myEmel_cc :"+myEmel_cc);
						if(myEmel_cc.size()>0)
						{
							email.TO_CC = new String[myEmel_cc.size()];
							for (int x=0;x < myEmel_cc.size();x++)
							{
								myLogger.info(" TO_CC myEmel_cc :"+myEmel_cc.get(x));
								email.TO_CC[x] = myEmel_cc.get(x);								
							}
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
				
						
				
				
				/*
				if(ID_STATUS.equals("2") || ID_STATUS.equals("3"))//permohonan lulus or tolak
				{
					subject = " KEPUTUSAN PERMOHONAN OT : "+NO_OT;
					if(ID_STATUS.equals("2"))
					{
						content = " Permohonan OT anda adalah diluluskan oleh "+EMEL_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
					}else
					{
						content = " Permohonan OT anda adalah ditolak oleh "+EMEL_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
					}
					
					content += "<br><br>Catatan : "+CATATAN_PELULUS;
					email.MULTIPLE_RECIEPIENT[0] = EMEL_PEMOHON;
				}
				else if(ID_STATUS.equals("1"))//pemohonan baru
				{
					subject = " PERMOHONAN BARU OT : "+NO_OT;
					content = " Anda menerima permohonan OT daripada "+NAMA_PEMOHON+". Sila semak permohonan ini di MyeTaPP.";
					content += "<br><br>Catatan : "+CATATAN_PEMOHON;
					email.MULTIPLE_RECIEPIENT[0] = EMEL_PELULUS;
				}
				
				
				email.SUBJECT = subject;
				email.MESSAGE = content;		
				*/
				
				
				//listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg",(String) viewPengguna.get("ID_NEGERI"));
				
				//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
				/*
				email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
				for(int i = 0; i < listPenggunaMengikutRole.size();i++)
				   {			   
					   Map m = (Map) listPenggunaMengikutRole.get(i);
					   myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
					   email.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");			  
				 }
				*/
				//email.TO_CC = new String[1];		
				//email.TO_CC[0] = "razman.zainal@gmail.com";
						 
				
				
			 }
		 
		 
		 
		 
		 @SuppressWarnings("unchecked")
			public List listTindakanByContent(HttpSession session, String ID_MESYUARATCONTENT)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listTindakanByContent = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement();
					
					sql = " SELECT T.ID_MESYUARATTINDAKAN FROM TBLPDTMESYUARATTINDAKAN T "+
							" WHERE T.ID_STATUS = 1 AND ID_MESYUARATCONTENT = '"+ID_MESYUARATCONTENT+"' ";
					
					myLogger.info(" SQL : listTindakanByContent :"+ sql);			
					rs = stmt.executeQuery(sql);
					listTindakanByContent = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("ID_MESYUARATTINDAKAN",rs.getString("ID_MESYUARATTINDAKAN") == null ? "" : rs.getString("ID_MESYUARATTINDAKAN"));	
						listTindakanByContent.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listTindakanByContent;
			}
		    
		 @SuppressWarnings("unchecked")
			public List listContentByMain(HttpSession session, String ID_MESYUARATUTAMA)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listContentByMain = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement();
					
					sql = " SELECT DISTINCT T.ID_MESYUARATCONTENT FROM TBLPDTMESYUARATTINDAKAN T  " +
							" WHERE T.ID_STATUS = 1  AND ID_MESYUARATUTAMA = '"+ID_MESYUARATUTAMA+"' ";
					
					myLogger.info(" SQL : listTindakanByContent :"+ sql);			
					rs = stmt.executeQuery(sql);
					listContentByMain = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("ID_MESYUARATCONTENT",rs.getString("ID_MESYUARATCONTENT") == null ? "" : rs.getString("ID_MESYUARATCONTENT"));	
						listContentByMain.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listContentByMain;
			}
		    
		 
		 
		 private static final Pattern TAG_REGEX = Pattern.compile("<xxx>(.+?)</xxx>");

		 private static List<String> getTagValues(final String str) {
		     final List<String> tagValues = new ArrayList<String>();
		     final Matcher matcher = TAG_REGEX.matcher(str);
		     while (matcher.find()) {
		         tagValues.add(matcher.group(1));
		     }
		     return tagValues;
		 }


}










