
package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;

public class FrmDokBerkaitanTanahRizabMelayu extends AjaxBasedModule {

	/**
	 * razman revamp pandangan undang2
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH="app/pdt/DocTanahRizab/";
	static Logger myLogger = Logger.getLogger(FrmSenaraiDokumen.class);
	
	
	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		String action = getParam("action");
		/*VIEW BY ROLE*/
		String portal_role = (String) session.getAttribute("myrole");
		context.put("portal_role", portal_role);
		String editable = "";
		if(portal_role.equals("(PDT)HQPengguna"))
		{
			editable = "Y";
		}
		context.put("editable", editable);
		/*
		String FrmSenaraiDokumenOnLoad = (String) session.getAttribute("FrmSenaraiDokumenOnLoad");
		if(FrmSenaraiDokumenOnLoad==null)
		{
		session.setAttribute("FrmSenaraiDokumenOnLoad", "Y");
		}
		*/
		myLogger.info(" command : "+command+" action : "+action);
		
		
		String FLAG_SHOWPOPUP = getParam("FLAG_SHOWPOPUP");
		myLogger.info(" FLAG_SHOWPOPUP : "+FLAG_SHOWPOPUP);
		this.context.put("FLAG_SHOWPOPUP",FLAG_SHOWPOPUP);
		String seksyen = getParam("socSeksyen");
		this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Utils.parseLong(seksyen),null,null));
		//String txdTarikhDokumen = getParam("txdTarikhDokumen"+ID_DOCTRM);
		//this.context.put("txdTarikhDokumen","");
    	  
		String vm = PATH+"index.jsp";
		
		List listFolderUtama = null;
		List listFolderSub = null;
		List listFolderLampiran = null;
		List listSubByID_REFER = null;
		Hashtable viewTajukUtama = null;
		Hashtable viewSubFoler = null;
		Hashtable viewMainFoler = null;
		Hashtable viewLampiran = null;
		
		defaultPut();
		
		
		if(command.equals("carianUtama"))
		{
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);
			String carianTerperinciLampiran = getParam("carianTerperinciLampiran");
			this.context.put("carianTerperinciLampiran", carianTerperinciLampiran);
			listFolderUtama = listFolderUtama(session, carianTerperinci,carianTerperinciLampiran);
			setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");			
			vm = "app/pdt/DocTanahRizab/SenaraiUtama.jsp";			
		}	
		else if(command.equals("countSubFolder"))
		{
			this.context.put("ID_DOCTRMUTAMA", getParam("ID_DOCTRMUTAMA"));
			this.context.put("ID_DOCTRM", getParam("ID_DOCTRM"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_DOCTRMUTAMA")));
			this.context.put("TOTAL_SUB", getParam("TOTAL_SUB"));
			this.context.put("FLAG_SUB_OPENCLOSE", getParam("FLAG_SUB_OPENCLOSE"));
			vm = "app/pdt/DocTanahRizab/CountSubFolder.jsp";
		}
		else if(command.equals("countLampiran"))
		{
			this.context.put("ID_DOCTRMUTAMA", getParam("ID_DOCTRMUTAMA"));
			this.context.put("ID_DOCTRM", getParam("ID_DOCTRM"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_DOCTRMUTAMA")));
			this.context.put("TOTAL_LAMPIRAN", getParam("TOTAL_LAMPIRAN"));
			this.context.put("FLAG_LAMP_OPENCLOSE", getParam("FLAG_LAMP_OPENCLOSE"));
			vm = "app/pdt/DocTanahRizab/CountLampiran.jsp";
		}
		else if(command.equals("showLampiran") || command.equals("deleteLampiran"))
		{
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			String AUTOLOAD = getParam("AUTOLOAD");		
			this.context.put("AUTOLOAD", AUTOLOAD);								
			String FLAG_LAMP_OPENCLOSE = getParam("FLAG_LAMP_OPENCLOSE");
			myLogger.info(" FLAG_LAMP_OPENCLOSE : "+FLAG_LAMP_OPENCLOSE+" AUTOLOAD :"+AUTOLOAD);
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);	
			String carianTerperinciLampiran = getParam("carianTerperinciLampiran");
			this.context.put("carianTerperinciLampiran", carianTerperinciLampiran);
			
			
			if(command.equals("deleteLampiran"))
			{
				//delete function
				String ID_DOCTRMLAMPIRAN = getParam("ID_DOCTRMLAMPIRAN");
				String NAMA_DOC = getParam("NAMA_DOC");
				deleteLampiran(session,ID_DOCTRMLAMPIRAN,NAMA_DOC);
			}			
			this.context.put("MAX_LAYER", getMaxLayer(session, ID_DOCTRMUTAMA));
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_LAMP_OPENCLOSE", "OPEN");
				listFolderLampiran = listFolderLampiran(session, ID_DOCTRMUTAMA, ID_DOCTRM, carianTerperinci,carianTerperinciLampiran);
				this.context.put("listFolderLampiran", listFolderLampiran);	
				vm = "app/pdt/DocTanahRizab/SenaraiLampiran.jsp";				
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_LAMP_OPENCLOSE.equals("OPEN"))
				{
					//this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					this.context.put("JUMLAH_LAMP", getParam("JUMLAH_LAMP"));
					this.context.put("FLAG_LAMP_OPENCLOSE", "CLOSE");
					vm = "app/pdt/DocTanahRizab/blank_lampiranFolder.jsp";
				}
				else
				{
					if(FLAG_LAMP_OPENCLOSE.equals("CLOSE"))
					{
						this.context.put("FLAG_LAMP_OPENCLOSE", "OPEN");						
					}
					listFolderLampiran = listFolderLampiran(session, ID_DOCTRMUTAMA, ID_DOCTRM, "","");
					this.context.put("listFolderLampiran", listFolderLampiran);	
					vm = "app/pdt/DocTanahRizab/SenaraiLampiran.jsp";
				}
			}					
		}	
		//'addSubDir','ID_REFER=$ID_DOCTRM&LAYER=$LFS.NEXTLAYER&ID_DOCTRMUTAMA=$LFS.ID_DOCTRMUTAMA');
		else if(command.equals("addSubDir"))
		{			
			this.context.put("ID_REFER", getParam("ID_REFER"));
			myLogger.info(" getParam(ID_REFER) : "+getParam("ID_REFER"));
			this.context.put("LAYER", getParam("LAYER"));
			this.context.put("ID_DOCTRMUTAMA", getParam("ID_DOCTRMUTAMA"));		
			vm = "app/pdt/DocTanahRizab/addSubDir.jsp";			
		}		
		else if(command.equals("addLampiran"))
		{			
			this.context.put("ID_DOCTRM", getParam("ID_DOCTRM"));
			myLogger.info(" getParam(ID_DOCTRM) : "+getParam("ID_DOCTRM"));
			this.context.put("ID_DOCTRMUTAMA", getParam("ID_DOCTRMUTAMA"));
			
			vm = "app/pdt/DocTanahRizab/addLampiran.jsp";			
		}
		else if(command.equals("saveLampiran"))
		{						
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM_AFTERUPLOAD",ID_DOCTRM);
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA_AFTERUPLOAD",ID_DOCTRMUTAMA);
			
			String txdTarikhDokumen = getParam("txdTarikhDokumen");
			
			
			
			myLogger.info(" getParam(txdTarikhDokumen) : "+txdTarikhDokumen);
			this.context.put("txdTarikhDokumen",txdTarikhDokumen);
			
			saveLampiran(session,ID_DOCTRM,ID_DOCTRMUTAMA,txdTarikhDokumen);
			this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");			
			vm = "app/pdt/DocTanahRizab/index.jsp";			
		}
		else if(command.equals("editSubDir"))
		{
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("LAYER", getParam("LAYER"));
			viewSubFoler = viewSubFoler(session,ID_DOCTRM);
			this.context.put("viewSubFoler", viewSubFoler);
			vm = "app/pdt/DocTanahRizab/editSubDir.jsp";			
		}
		else if(command.equals("editLampiran"))
		{
			String ID_DOCTRMLAMPIRAN = getParam("ID_DOCTRMLAMPIRAN");
			this.context.put("ID_DOCTRMLAMPIRAN", ID_DOCTRMLAMPIRAN);
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			this.context.put("BIL", getParam("BIL"));
			viewLampiran = viewLampiran(session,ID_DOCTRMLAMPIRAN);
			this.context.put("viewLampiran", viewLampiran);
			vm = "app/pdt/DocTanahRizab/editLampiran.jsp";			
		}		
		else if(command.equals("validateMainDir"))
		{
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			String TAJUK = getParam("TAJUK");
			String duplicateMainDir = "N";
			if(checkDuplicateMainDir(session,ID_DOCTRMUTAMA,TAJUK)==true)
			{
				duplicateMainDir = "Y";
			}
			this.context.put("duplicateMainDir", duplicateMainDir);
			vm = "app/pdt/DocTanahRizab/validateMainDir.jsp";			
		}	
		else if(command.equals("validateSubDir"))
		{
			String ID_DOCTRMLAMPIRAN = getParam("ID_DOCTRMLAMPIRAN");
			this.context.put("ID_DOCTRMLAMPIRAN", ID_DOCTRMLAMPIRAN);
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			String TAJUK = getParam("TAJUK");
			String duplicateSubDir = "N";
			if(checkDuplicateSubDir(session,ID_REFER,ID_DOCTRM,ID_DOCTRMUTAMA,TAJUK)==true)
			{
				duplicateSubDir = "Y";
			}
			this.context.put("duplicateSubDir", duplicateSubDir);
			vm = "app/pdt/DocTanahRizab/validateSubDir.jsp";			
		}	 
		else if(command.equals("validateLampiran"))
		{
			String ID_DOCTRMLAMPIRAN = getParam("ID_DOCTRMLAMPIRAN");
			this.context.put("ID_DOCTRMLAMPIRAN", ID_DOCTRMLAMPIRAN);
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			String NAMA_DOC = getParam("NAMA_DOC");
			String duplicateName = "N";
			if(checkDuplicateDocName(session,ID_DOCTRMLAMPIRAN,ID_DOCTRM,NAMA_DOC,ID_DOCTRMUTAMA)==true)
			{
				duplicateName = "Y";
			}
			this.context.put("duplicateName", duplicateName);
			vm = "app/pdt/DocTanahRizab/validateLampiran.jsp";			
		}	 
		else if(command.equals("editMainDir"))
		{
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			this.context.put("BIL", getParam("BIL"));
			viewMainFoler = viewMainFoler(session,ID_DOCTRMUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			vm = "app/pdt/DocTanahRizab/editMainDir.jsp";			
		}	
		else if(command.equals("SimpanLampiran"))
		{
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			this.context.put("BIL", getParam("BIL"));
			String ID_DOCTRMLAMPIRAN = getParam("ID_DOCTRMLAMPIRAN");
			this.context.put("ID_DOCTRMLAMPIRAN", ID_DOCTRMLAMPIRAN);
			
			String editLampiranField = getParam("editLampiranField_"+ID_DOCTRM+"_"+ID_DOCTRMLAMPIRAN);
			String tarikhDaftar = getParam("tarikhDaftar_"+ID_DOCTRM+"_"+ID_DOCTRMLAMPIRAN);
			myLogger.info(" ***** editLampiranField : "+editLampiranField);
			
			//function simpan disini	
			saveUpdateLampiran(session,ID_DOCTRMLAMPIRAN,ID_DOCTRM,ID_DOCTRMUTAMA,editLampiranField,tarikhDaftar);
			
			viewLampiran = viewLampiran(session,ID_DOCTRMLAMPIRAN);
			this.context.put("viewLampiran", viewLampiran);
			vm = "app/pdt/DocTanahRizab/viewLampiran.jsp";			
		}
		else if(command.equals("SimpanSubDir"))
		{
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			this.context.put("BIL", getParam("BIL"));
			String LAYER = getParam("LAYER");
			this.context.put("LAYER",LAYER);			
			String editSubDirField = getParam("editSubDirField_"+ID_DOCTRMUTAMA+"_"+ID_REFER+"_"+ID_DOCTRM+"");
			myLogger.info("(editSubDirField_"+ID_DOCTRMUTAMA+"_"+ID_DOCTRM+") :::: "+editSubDirField);
			saveUpdateSubDir(session,ID_REFER,ID_DOCTRM,ID_DOCTRMUTAMA,editSubDirField);			
			viewSubFoler = viewSubFoler(session,ID_DOCTRM);
			this.context.put("viewSubFoler", viewSubFoler);
			vm = "app/pdt/DocTanahRizab/viewSubDir.jsp";			
		}
		else if(command.equals("SimpanMainDir"))
		{
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			this.context.put("BIL", getParam("BIL"));			
			String editMainDirField = getParam("editMainDirField_"+ID_DOCTRMUTAMA);
			//function simpan disini
			saveUpdateMainDir(session,ID_DOCTRMUTAMA,editMainDirField);			
			viewMainFoler = viewMainFoler(session,ID_DOCTRMUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			vm = "app/pdt/DocTanahRizab/viewMainDir.jsp";			
		}
		else if(command.equals("SimpanAddSubDir") || command.equals("showAllFolder") || command.equals("deleteSubDir"))
		{
			
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);		
			String carianTerperinciLampiran = getParam("carianTerperinciLampiran");
			this.context.put("carianTerperinciLampiran", carianTerperinciLampiran);
			
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			this.context.put("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
			String ID_DOCTRM = getParam("ID_DOCTRM");
			this.context.put("ID_DOCTRM", ID_DOCTRM);
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
			
			if(command.equals("deleteSubDir"))
			{
				//call delete function
				checkChildDeleteSub(session,ID_TO_DELETE);
				AUTOLOAD = "N";
				FLAG_SUB_OPENCLOSE = "CLOSE";
			}
			else if(command.equals("SimpanAddSubDir"))
			{
				//call add functon
				String editSubDirField = getParam("editSubDirField_"+ID_DOCTRMUTAMA+"_"+ID_REFER+"_");
				myLogger.info("(editSubDirField_"+ID_DOCTRMUTAMA+"_"+ID_REFER+"_"+ID_DOCTRM+") -- editSubDirField :"+editSubDirField);
				saveInsertSubDir(session,ID_REFER,ID_DOCTRM,ID_DOCTRMUTAMA,editSubDirField,LAYER);
			}
			else
			{
				if(FlagCari.equals("Y"))
				{
					AUTOLOAD = "Y";
					if(FLAG_OPENCLOSE.equals("OPEN"))
					{
						AUTOLOAD = "N";
					}
				}
			}
			
			
			
			String MAX_LAYER = getMaxLayer(session, getParam("ID_DOCTRMUTAMA"))+"";
			this.context.put("MAX_LAYER", MAX_LAYER);
			
			this.context.put("AUTOLOAD", AUTOLOAD);
			Hashtable filter_carian_folder = null;
			String list_id_refer = "0";
			String list_id_actual = "0";
			if(AUTOLOAD.equals("Y") && (!carianTerperinci.equals("") || !carianTerperinciLampiran.equals("") ))
			{
				filter_carian_folder = new Hashtable();
				filter_carian_folder = getIDP_check(session, ID_DOCTRMUTAMA, LAYER, carianTerperinci,carianTerperinciLampiran, MAX_LAYER);		
				list_id_refer = (String) filter_carian_folder.get("SET_ID_REFER");
				list_id_actual = (String) filter_carian_folder.get("SET_ID_DOCTRM");
			}			
			myLogger.info(" **********filter_carian_folder : "+filter_carian_folder);
			listFolderSub = listFolderSub(session, ID_DOCTRMUTAMA, ID_REFER, LAYER,carianTerperinci,carianTerperinciLampiran,list_id_refer,list_id_actual,AUTOLOAD);
			this.context.put("listFolderSub", listFolderSub);
			this.context.put("TOTAL_SUB", listFolderSub.size());
			this.context.put("NAMA_FOLDER", getParam("NAMA_FOLDER"));
			
			
			
			//myLogger.info(" **********LAYER : "+LAYER);
			//myLogger.info(" **********MAX_LAYER : "+MAX_LAYER);
			
			
			
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_OPENCLOSE", "OPEN");
				this.context.put("FLAG_SUB_OPENCLOSE", "OPEN");
				
				vm = "app/pdt/DocTanahRizab/SenaraiSubFolder.jsp";
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_OPENCLOSE.equals("OPEN"))
				{
					this.context.put("FLAG_OPENCLOSE", "CLOSE");
					this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					//this.context.put("JUMLAH_LAMP", getParam("JUMLAH_LAMP"));
					vm = "app/pdt/DocTanahRizab/blank_subFolder.jsp";
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
						//this.context.put("JUMLAH_LAMP", getParam("JUMLAH_LAMP"));
						this.context.put("FLAG_SUB_OPENCLOSE", "CLOSE");
						vm = "app/pdt/DocTanahRizab/blank_subFolder.jsp";
					}
					else
					{
						if(FLAG_SUB_OPENCLOSE.equals("CLOSE"))
						{
							this.context.put("FLAG_SUB_OPENCLOSE", "OPEN");						
						}
						vm = "app/pdt/DocTanahRizab/SenaraiSubFolder.jsp";
					}
						
				}
			}
			
			
		}
		else if(command.equals("batalCarianUtama") || command.equals("showFolderUtama") || command.equals("deleteFolderUtama"))
		{
			if(command.equals("deleteFolderUtama"))
			{
				//function delete
				String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
				deleteMainFolder(session,ID_DOCTRMUTAMA);
			}
			listFolderUtama = listFolderUtama(session, "","");
			setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");
			vm = "app/pdt/DocTanahRizab/SenaraiUtama.jsp";	
		}
		else if(command.equals("addTajukUtama") || command.equals("batalTajukUtama"))
		{			
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			viewTajukUtama = viewTajukUtama(session, ID_DOCTRMUTAMA);
			this.context.put("viewTajukUtama", viewTajukUtama);
			vm = "app/pdt/DocTanahRizab/addTajukUtama.jsp";	
		}
		else if(command.equals("saveTajukUtama"))
		{			
			String ID_DOCTRMUTAMA = getParam("ID_DOCTRMUTAMA");
			String ID_SEKSYEN = getParam("socSeksyen");
			saveTajukUtama(session,ID_DOCTRMUTAMA, ID_SEKSYEN);
			listFolderUtama = listFolderUtama(session, "","");
			setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");
			vm = "app/pdt/DocTanahRizab/SenaraiUtama.jsp";
		}
		else if(command.equals("closeTajukUtama"))
		{			
			vm = "app/pdt/DocTanahRizab/blank.jsp";
		}
		else
		{
			//listFolderUtama = listFolderUtama(session, "");
			//setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");
			vm = "app/pdt/DocTanahRizab/index.jsp";	
		}
		
		
		myLogger.info("---------VM"+vm);
		//session.setAttribute("FrmSenaraiDokumenOnLoad", "N");
		return vm;
		
	}

	public void defaultPut()
	{
		this.context.put("carianTerperinci", "");
		this.context.put("carianTerperinciLampiran", "");
		this.context.put("FlagCari", "");
		this.context.put("listFolderUtama", "");
		this.context.put("listFolderLampiran", "");
		this.context.put("listFolderSub", "");
		this.context.put("viewTajukUtama", "");	
		this.context.put("JUMLAH_SUB","");
		this.context.put("JUMLAH_LAMP", "");
		this.context.put("viewSubFoler", "");
		this.context.put("viewMainFoler", "");
		this.context.put("BIL", "");
		this.context.put("after_uploadLampiran","");
		this.context.put("viewLampiran", "");
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
	public int getMaxLayer(HttpSession session, String ID_DOCTRMUTAMA)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		int max_layer = 0;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = " SELECT MAX(NVL(LAYER,0)) AS MAX_LAYER FROM TBLPDTDOCTRM K " +
					" WHERE K.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
			myLogger.info(" SQL : listFolderUtama :"+ sql);
			
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
	public List listFolderUtama(HttpSession session, String carianTerperinci,String carianTerperinciLampiran)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderUtama = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			/*
			sql = " SELECT T.ID_DOCTRMUTAMA, T.TAJUK, "+
					" NVL(SUB.SAIZ,0) AS JUMLAH_SUB FROM TBLPDTDOCTRMUTAMA T ,  "+
					" (SELECT S.ID_DOCTRMUTAMA, COUNT(S.ID_DOCTRM) AS SAIZ " +
					" FROM TBLPDTDOCTRM S WHERE S.LAYER = '1' "+
					" GROUP BY  S.ID_DOCTRMUTAMA) SUB "+
					" WHERE T.ID_DOCTRMUTAMA IS NOT NULL   "+
					" AND SUB.ID_DOCTRMUTAMA(+) = T.ID_DOCTRMUTAMA ";
					if(!carianTerperinci.equals(""))
					{
						sql += " AND UPPER(T.TAJUK) LIKE '%"+carianTerperinci.toUpperCase()+"%' ";
					}
					sql += " ORDER BY T.TAJUK ";		
			*/
			
			sql = " SELECT   T.ID_DOCTRMUTAMA, T.TAJUK, NVL (SUB.SAIZ, 0) AS JUMLAH_SUB, "+
					" NVL (TF.SAIZ, 0) AS TOTAL_FOLDER,NVL (TL.SAIZ, 0) AS TOTAL_LAMPIRAN, NVL(ML.MAX_LAYER,0) AS MAX_LAYER, "+
					" SK.NAMA_SEKSYEN, SK.ID_SEKSYEN " +
					" FROM   TBLPDTDOCTRMUTAMA T, TBLRUJSEKSYEN SK, "+
					" (SELECT   S.ID_DOCTRMUTAMA, "+
					" COUNT (DISTINCT S.ID_DOCTRM) AS SAIZ "+
					" FROM TBLPDTDOCTRM S "+
					" WHERE S.LAYER = '1' "+
					" GROUP BY S.ID_DOCTRMUTAMA) SUB, "+
					" (SELECT   S.ID_DOCTRMUTAMA, "+
					" COUNT (DISTINCT S.ID_DOCTRM) AS SAIZ "+
					" FROM TBLPDTDOCTRM S,TBLPDTDOCTRMLAMPIRAN L "+
					" WHERE S.ID_DOCTRM = L.ID_DOCTRM(+)    ";  
					if(!carianTerperinci.equals(""))
					{
						sql += " AND UPPER(S.TAJUK) LIKE '%"+carianTerperinci.toUpperCase()+"%' ";
					}
					/*
					if(!carianTerperinciLampiran.equals(""))
					{
						sql += " AND UPPER(L.NAMA_DOC) LIKE '%"+carianTerperinciLampiran.toUpperCase()+"%' ";
					}
					*/
					sql += " GROUP BY S.ID_DOCTRMUTAMA) TF, "+
					" (SELECT   S.ID_DOCTRMUTAMA, "+
					" COUNT (DISTINCT S.ID_DOCTRMLAMPIRAN) AS SAIZ "+
					" FROM TBLPDTDOCTRMLAMPIRAN S ";
					if(!carianTerperinciLampiran.equals(""))
					{
						sql += " WHERE UPPER(S.NAMA_DOC) LIKE '%"+carianTerperinciLampiran.toUpperCase()+"%' ";
					}							
					sql += " GROUP BY S.ID_DOCTRMUTAMA) TL , "+
							" (SELECT K.ID_DOCTRMUTAMA, MAX(NVL(LAYER,0)) AS MAX_LAYER FROM TBLPDTDOCTRM K "+
							" GROUP BY K.ID_DOCTRMUTAMA) ML"+
					" WHERE T.ID_DOCTRMUTAMA IS NOT NULL AND T.JENIS_AKTIVITI = 'TANAHRIZAB' "+
					" AND SUB.ID_DOCTRMUTAMA(+) = T.ID_DOCTRMUTAMA "+
					" AND TF.ID_DOCTRMUTAMA(+) = T.ID_DOCTRMUTAMA "+
					" AND TL.ID_DOCTRMUTAMA(+) = T.ID_DOCTRMUTAMA " +
					" AND ML.ID_DOCTRMUTAMA(+) = T.ID_DOCTRMUTAMA " +
					" AND SK.ID_SEKSYEN(+) = T.ID_SEKSYEN ";
					
					if(!carianTerperinci.equals("") && !carianTerperinciLampiran.equals(""))
					{
						sql += " AND ((UPPER(T.TAJUK) LIKE '%"+carianTerperinci.toUpperCase()+"%' OR NVL (TF.SAIZ, 0)>0) OR NVL (TL.SAIZ, 0)>0) ";
					}
					else
					{
						if(!carianTerperinci.equals(""))
						{
							sql += " AND (UPPER(T.TAJUK) LIKE '%"+carianTerperinci.toUpperCase()+"%' OR NVL (TF.SAIZ, 0)>0)";
						}
						
						if(!carianTerperinciLampiran.equals(""))
						{
							sql += " AND NVL (TL.SAIZ, 0)>0 ";
						}
					}
					
					 
					sql += " ORDER BY T.TAJUK ";
			myLogger.info(" SQL : listFolderUtama :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listFolderUtama = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));			
				h.put("TAJUK",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));	
				h.put("JUMLAH_SUB", rs.getString("JUMLAH_SUB") == null ? 0 : rs.getInt("JUMLAH_SUB"));
				h.put("TOTAL_FOLDER", rs.getString("TOTAL_FOLDER") == null ? 0 : rs.getInt("TOTAL_FOLDER"));
				h.put("TOTAL_LAMPIRAN", rs.getString("TOTAL_LAMPIRAN") == null ? 0 : rs.getInt("TOTAL_LAMPIRAN"));
				h.put("MAX_LAYER", rs.getString("MAX_LAYER") == null ? 0 : rs.getInt("MAX_LAYER"));
				h.put("NAMA_SEKSYEN", rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				h.put("ID_SEKSYEN", rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				
				listFolderUtama.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listFolderUtama;
	}
	
	@SuppressWarnings("unchecked")
	public List listFolderSub(HttpSession session, String ID_DOCTRMUTAMA, String ID_REFER, String LAYER,String Carian, String CarianLampiran,String id_PU_sebelum,String id_PU_current,String AUTOLOAD)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderSub = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT DISTINCT T.ID_DOCTRM, T.ID_DOCTRMUTAMA, T.TAJUK, T.LAYER, "+
					" T.ID_REFER, NVL (SUB.SAIZ, 0) AS JUMLAH_SUB, NVL (LAMP.SAIZ, 0) AS JUMLAH_LAMP," +
					" NVL (SUB_FIND.SAIZ, 0) AS JUMLAH_SUB_FIND, NVL (LAMP_FIND.SAIZ, 0) AS JUMLAH_LAMP_FIND "+
					" FROM TBLPDTDOCTRM T, "+
					" (SELECT   TEMP.ID_REFER, COUNT (TEMP.ID_DOCTRM) AS SAIZ "+
					" FROM TBLPDTDOCTRM TEMP "+
					" WHERE TEMP.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' "+
					" GROUP BY TEMP.ID_REFER) SUB, "+
					" (SELECT   TEMP.ID_REFER, COUNT (TEMP.ID_DOCTRM) AS SAIZ "+
					" FROM TBLPDTDOCTRM TEMP "+
					" WHERE TEMP.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' " +
					" AND TEMP.ID_DOCTRM IN ("+id_PU_current+") "+
					" GROUP BY TEMP.ID_REFER) SUB_FIND, "+
					" (SELECT   TEMP.ID_DOCTRM, COUNT (TEMP.ID_DOCTRMLAMPIRAN) AS SAIZ "+
					" FROM TBLPDTDOCTRMLAMPIRAN TEMP "+
					" WHERE TEMP.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' "+
					" GROUP BY TEMP.ID_DOCTRM) LAMP, " +
					" (SELECT   TEMP.ID_DOCTRM, COUNT (TEMP.ID_DOCTRMLAMPIRAN) AS SAIZ "+
					" FROM TBLPDTDOCTRMLAMPIRAN TEMP "+
					" WHERE TEMP.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
					//" AND TEMP.ID_DOCTRM IN ("+id_PU_current+") ";
					if(!CarianLampiran.equals("") && AUTOLOAD.equals("Y"))
					{
						sql += " AND UPPER(TEMP.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' ";
					}
					sql += " GROUP BY TEMP.ID_DOCTRM) LAMP_FIND, " +
					" TBLPDTDOCTRMLAMPIRAN TEMP_LAMP "+
					" WHERE T.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
					if(ID_REFER.equals("") || ID_REFER==null)
					{
						sql += " AND (T.ID_REFER IS NULL OR T.ID_REFER = '') ";
					}
					else
					{
						sql += " AND T.ID_REFER = '"+ID_REFER+"' ";
					}	
					sql += " AND SUB.ID_REFER(+) = T.ID_DOCTRM ";
					sql += " AND SUB_FIND.ID_REFER(+) = T.ID_DOCTRM ";
					
					if(AUTOLOAD.equals("Y"))
					{		
						sql += " AND ( ";
						
						if(!Carian.equals(""))
						{
							sql += " UPPER(T.TAJUK) LIKE '%"+Carian.toUpperCase()+"%' OR ";
							
						}
						if(!CarianLampiran.equals(""))
						{
							sql += " UPPER(TEMP_LAMP.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' OR ";
						}
						
						sql += " T.ID_DOCTRM IN ("+id_PU_sebelum+") ";
						
						
						sql += " ) ";
					}
					
					
					sql += " AND LAMP.ID_DOCTRM(+) = T.ID_DOCTRM ";
					sql += " AND LAMP_FIND.ID_DOCTRM(+) = T.ID_DOCTRM ";					
				    sql += " AND TEMP_LAMP.ID_DOCTRM(+) = T.ID_DOCTRM "+
					" ORDER BY T.TAJUK"; 
			
		
			
			
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
				h.put("ID_DOCTRM",rs.getString("ID_DOCTRM") == null ? "" : rs.getString("ID_DOCTRM"));			
				h.put("TAJUK",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));			
				h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));			
				h.put("ID_REFER",rs.getString("ID_REFER") == null ? "" : rs.getString("ID_REFER"));			
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));	
				h.put("NEXTLAYER",rs.getString("LAYER") == null ? 1 : rs.getInt("LAYER")+1);	
				h.put("TD_SAIZ_1",(rs.getString("LAYER") == null && rs.getString("LAYER").equals(""))||(rs.getString("LAYER").equals("1")) ? "0%" : Integer.parseInt(rs.getString("LAYER"))+1+"%");
				
				int sub = 0;
				int lam = 0;
				
				if(AUTOLOAD.equals("Y"))
				{
					sub = rs.getString("JUMLAH_SUB_FIND") == null ? 0 : rs.getInt("JUMLAH_SUB_FIND");
					lam = rs.getString("JUMLAH_LAMP_FIND") == null ? 0 : rs.getInt("JUMLAH_LAMP_FIND");
				}
				else
				{
					sub  = rs.getString("JUMLAH_SUB") == null ? 0 : rs.getInt("JUMLAH_SUB");
					lam = rs.getString("JUMLAH_LAMP") == null ? 0 : rs.getInt("JUMLAH_LAMP");
				}
				
				
				h.put("JUMLAH_SUB",sub);
				h.put("JUMLAH_LAMP",lam);
				//h.put("JUMLAH_SUB_FIND", rs.getString("JUMLAH_SUB_FIND") == null ? 0 : rs.getInt("JUMLAH_SUB_FIND"));
				//h.put("JUMLAH_LAMP_FIND", rs.getString("JUMLAH_LAMP_FIND") == null ? 0 : rs.getInt("JUMLAH_LAMP_FIND"));
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
	
	
		public Hashtable getIDP_check(HttpSession session, String ID_DOCTRMUTAMA, String LAYER, String Carian, String CarianLampiran,String MAX_LAYER)throws Exception {
			
			int l = Integer.parseInt(LAYER);
			int max_l = Integer.parseInt(MAX_LAYER);
			
			String getID_DOCTRM = "0";
			String getID_DOCTRM_ACTUAL = "0";
			
			
			for(int i = max_l; i>l; i--)
			{
				   List listIDP_by_layer_check = listIDP_by_layer(session, ID_DOCTRMUTAMA, i+"", Carian, CarianLampiran,getID_DOCTRM);
				   String temp_id_p = "0";
				   String temp_id_p_actual = "0";
				   for(int x = 0; x < listIDP_by_layer_check.size();x++)
				   {
					   Map m = (Map) listIDP_by_layer_check.get(x);
					   temp_id_p += ","+m.get("ID_REFER");
					   temp_id_p_actual += ","+m.get("ID_DOCTRM");
				   }			
				   getID_DOCTRM = temp_id_p;
				   getID_DOCTRM_ACTUAL = temp_id_p_actual;
				   myLogger.info(" *** LAYER : "+i+" MAX_LAYER : "+max_l+" getID_DOCTRM :"+getID_DOCTRM+" getID_DOCTRM_ACTUAL : "+getID_DOCTRM_ACTUAL);
			}			
			//return getID_DOCTRM;
			Hashtable h = new Hashtable();
			h.put("SET_ID_REFER", getID_DOCTRM);
			h.put("SET_ID_DOCTRM", getID_DOCTRM_ACTUAL);
			
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
		public List listIDREFER_by_layer(HttpSession session, String ID_REFER,String ID_DOCTRMUTAMA, String LAYER, String Carian)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listIDREFER_by_layer = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();				
				sql = " SELECT DISTINCT U.ID_REFER FROM  TBLPDTDOCTRM U "+
						" WHERE U.LAYER = '"+LAYER+"' AND U.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";				
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
		public List listIDP_by_layer(HttpSession session, String ID_DOCTRMUTAMA, String LAYER, String Carian,String CarianLampiran, String getIdAnakfolder)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listIDP_by_layer = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();				
				sql = " SELECT DISTINCT U.ID_DOCTRM,U.ID_REFER FROM TBLPDTDOCTRMLAMPIRAN L, TBLPDTDOCTRM U "+
						" WHERE L.ID_DOCTRM(+) = U.ID_DOCTRM " +
						" AND U.LAYER = '"+LAYER+"' AND U.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"'";
						
							
						sql += " AND ( ";
						
						if(!Carian.equals(""))
						{
							sql += " UPPER(U.TAJUK) LIKE '%"+Carian.toUpperCase()+"%' OR ";
							
						}
						if(!CarianLampiran.equals(""))
						{
							sql += " UPPER(L.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' OR ";
						}
						
						sql += " U.ID_DOCTRM IN ("+getIdAnakfolder+") ";
						
						
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
					h.put("ID_DOCTRM",rs.getString("ID_DOCTRM") == null ? "" : rs.getString("ID_DOCTRM"));
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
	public List listFolderLampiran(HttpSession session, String ID_DOCTRMUTAMA, String ID_REFER, String Carian,String CarianLampiran)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderLampiran = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT T.ID_DOCTRMLAMPIRAN, T.ID_DOCTRM, T.CONTENT, "+
					" T.NAMA_DOC, T.JENIS_MIME, T.ID_MASUK,  "+
					" T.ID_KEMASKINI,TO_CHAR(T.TARIKH_DAFTAR,'DD/MM/YYYY') AS TARIKH_DAFTAR,T.TARIKH_MASUK, T.TARIKH_KEMASKINI,  "+
					" T.ID_DOCTRMUTAMA "+
					" FROM TBLPDTDOCTRMLAMPIRAN T " +
					" WHERE T.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' " +
					" AND T.ID_DOCTRM = '"+ID_REFER+"' ";
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
				h.put("ID_DOCTRMLAMPIRAN",rs.getString("ID_DOCTRMLAMPIRAN") == null ? "" : rs.getString("ID_DOCTRMLAMPIRAN"));	
				h.put("ID_DOCTRM",rs.getString("ID_DOCTRM") == null ? "" : rs.getString("ID_DOCTRM"));	
				h.put("CONTENT",rs.getString("CONTENT") == null ? "" : rs.getString("CONTENT"));	
				h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));
				h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR") == null ? "" : rs.getString("TARIKH_DAFTAR"));
				h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));	
				h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));
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
	
	
	public Hashtable viewSubFoler(HttpSession session, String ID_DOCTRM) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_DOCTRM, T.ID_DOCTRMUTAMA, T.TAJUK, T.LAYER, T.ID_REFER, T.ID_MASUK, "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI FROM TBLPDTDOCTRM T " +
						" WHERE T.ID_DOCTRM = '"+ID_DOCTRM+"' ";
						
				myLogger.info(" viewSubFoler :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_DOCTRM",rs.getString("ID_DOCTRM") == null ? "" : rs.getString("ID_DOCTRM"));
					h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));
					h.put("TAJUK",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));
					h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
					h.put("ID_REFER",rs.getString("ID_REFER") == null ? "" : rs.getString("ID_REFER"));
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
	
	public Hashtable viewLampiran(HttpSession session, String ID_DOCTRMLAMPIRAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			
			
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_DOCTRMLAMPIRAN, T.ID_DOCTRM, T.CONTENT, "+
						" T.NAMA_DOC, T.JENIS_MIME, T.ID_DOCTRMUTAMA, TO_CHAR(T.TARIKH_DAFTAR,'DD/MM/YYYY') AS TARIKH_DAFTAR "+
						" FROM TBLPDTDOCTRMLAMPIRAN T "+
						" WHERE T.ID_DOCTRMLAMPIRAN = '"+ID_DOCTRMLAMPIRAN+"' ";
						
				myLogger.info(" viewLampiran :" + sql.toUpperCase());
				myLogger.info(" ID_DOCTRMLAMPIRAN :" + ID_DOCTRMLAMPIRAN);
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_DOCTRM",rs.getString("ID_DOCTRM") == null ? "" : rs.getString("ID_DOCTRM"));
					h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));
					h.put("ID_DOCTRMLAMPIRAN",rs.getString("ID_DOCTRMLAMPIRAN") == null ? "" : rs.getString("ID_DOCTRMLAMPIRAN"));
					h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));
					h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR") == null ? "" : rs.getString("TARIKH_DAFTAR"));
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
	
	
	public Hashtable viewMainFoler(HttpSession session, String ID_DOCTRMUTAMA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_DOCTRMUTAMA, T.TAJUK FROM TBLPDTDOCTRMUTAMA T WHERE T.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
						
				myLogger.info(" viewMainFoler :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));
					h.put("TAJUK",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));
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
	
	
	
	
	public Hashtable viewTajukUtama(HttpSession session, String ID_DOCTRMUTAMA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
			if(!ID_DOCTRMUTAMA.equals("") || !ID_DOCTRMUTAMA.equals(""))
			{		
				sql = " SELECT T.ID_DOCTRMUTAMA, T.TAJUK FROM TBLPDTDOCTRMUTAMA T " +
						" WHERE T.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
				myLogger.info(" viewPejabatJKPTG :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_DOCTRMUTAMA",rs.getString("ID_DOCTRMUTAMA") == null ? "" : rs.getString("ID_DOCTRMUTAMA"));
					h.put("TAJUK_UTAMA",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));
				}
			}
			else
			{
				h.put("ID_DOCTRMUTAMA","");
				h.put("TAJUK_UTAMA","");
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
	
	
	
	public String saveTajukUtama(HttpSession session,String ID_DOCTRMUTAMA,String ID_SEKSYEN) throws Exception {
		Connection conn = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		Db db = null;
		String sql = "";
		long long_ID_DOCTRMUTAMA = 0;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String TAJUK_UTAMA_ = getParam("TAJUK_UTAMA_"+ID_DOCTRMUTAMA);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(!ID_DOCTRMUTAMA.equals("") && !ID_DOCTRMUTAMA.equals(null))
			{
				long_ID_DOCTRMUTAMA = Long.parseLong(ID_DOCTRMUTAMA);
				r.update("ID_DOCTRMUTAMA", long_ID_DOCTRMUTAMA);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			}
			else
			{
				long_ID_DOCTRMUTAMA = DB.getNextID(db, "TBLPDTDOCTRMUTAMA_SEQ");
				r.add("ID_DOCTRMUTAMA", long_ID_DOCTRMUTAMA);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_MASUK", USER_ID_SYSTEM);
			}
			r.add("TAJUK", TAJUK_UTAMA_.toUpperCase());
			r.add("JENIS_AKTIVITI", "TANAHRIZAB");
			r.add("ID_SEKSYEN", ID_SEKSYEN);
			
			if(!ID_DOCTRMUTAMA.equals("") && !ID_DOCTRMUTAMA.equals(null))
			{
				sql = r.getSQLUpdate("TBLPDTDOCTRMUTAMA");		
				myLogger.info("UPDATE TBLPDTDOCTRMUTAMA : "+sql);
			}
			else
			{
				sql = r.getSQLInsert("TBLPDTDOCTRMUTAMA");		
				myLogger.info("INSERT TBLPDTDOCTRMUTAMA : "+sql);
			}
			stmt.executeUpdate(sql);			
			conn.commit();
			AuditTrail.logActivity(this,session,"INS","TBLPDTDOCTRMUTAMA  [ID : "+long_ID_DOCTRMUTAMA+"] Inserted");
			
			
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return long_ID_DOCTRMUTAMA+"";
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
	
	private void saveLampiran(HttpSession session,String ID_DOCTRM,String ID_DOCTRMUTAMA,String txdTarikhDokumen) throws Exception {
		 
		myLogger.info("txdTarikhDokumen -- "+txdTarikhDokumen);
	    
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
	    	  myLogger.info(" ID_DOCTRM : "+ID_DOCTRM+" ID_DOCTRMUTAMA : "+ID_DOCTRMUTAMA+" item : "+item);
	    	  saveLampiranDB(item,ID_DOCTRM,ID_DOCTRMUTAMA,txdTarikhDokumen);
	    	 
	      }
	    }
	  }
	
	
	
	
	 public void checkChildDeleteSub(HttpSession session,String ID_DOCTRM) throws Exception {	
		 
		 myLogger.info(" DELETE : "+ID_DOCTRM);
		 deleteSub(session,ID_DOCTRM);
		 List listSubByID_REFER = listSubByID_REFER(ID_DOCTRM);
		 for(int i = 0; i < listSubByID_REFER.size();i++)
		 {			   
			   Map m = (Map) listSubByID_REFER.get(i);
			   checkChildDeleteSub(session,(String) m.get("ID_DOCTRM"));			   
		 }	 
	 }
	 
	 public void deleteSub(HttpSession session,String ID_DOCTRM) throws Exception {
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
				r.add("ID_DOCTRM",ID_DOCTRM);
				sql = r.getSQLDelete("TBLPDTDOCTRM");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTDOCTRM [ID : "+ID_DOCTRM+"] Deleted");
				
				r.clear();
				r.add("ID_DOCTRM",ID_DOCTRM);
				sql = r.getSQLDelete("TBLPDTDOCTRMLAMPIRAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTDOCTRMLAMPIRAN [ID_DOCTRM : "+ID_DOCTRM+"] Deleted");
			
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
				
	
	
	 private void saveLampiranDB(FileItem item,String ID_DOCTRM,String ID_DOCTRMUTAMA,String txdTarikhDokumen) throws Exception {
		    HttpSession session = request.getSession();	
		    String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		  //21.07.2017
			if(!txdTarikhDokumen.equals(""))
			{
				txdTarikhDokumen = "to_date('" +txdTarikhDokumen+ "','dd/MM/yyyy')";
			}
			else
			{
				txdTarikhDokumen = "''";
			}
	  		Db db = null;
	        try {		        	
	        	long id_lampiran = DB.getNextID("TBLPDTDOCTRMLAMPIRAN_SEQ");	        	
	        	db = new Db();		        	
	        	
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPDTDOCTRMLAMPIRAN ( "+
		    		  " ID_DOCTRMLAMPIRAN, ID_DOCTRM, CONTENT,  "+
		    		  " NAMA_DOC, JENIS_MIME, ID_MASUK,  "+
		    		  " TARIKH_MASUK, ID_DOCTRMUTAMA,JENIS_AKTIVITI)  "+
		    		  " VALUES (?,?,?,?,?,?,SYSDATE,?,?) ");
	        	
	        	
	        	String nama_asal_doc = item.getName().substring(0, item.getName().indexOf("."));
	        	String nama_doc = nama_asal_doc;	        	
	        	int copy_count = 0;
	        	do {
     			   // Statements
	        		myLogger.info(" ***** nama_doc ada duplicate : "+nama_doc);
	        		if(checkDuplicateDocName(session,"",ID_DOCTRM,nama_doc.toUpperCase(),ID_DOCTRMUTAMA)==true)
	        		{
		        		copy_count++;
		        		nama_doc = nama_asal_doc + " COPY("+copy_count+")";
		        		myLogger.info(" ***** nama_doc baru : "+nama_doc);
	        		}
	        	}while(checkDuplicateDocName(session,"",ID_DOCTRM,nama_doc.toUpperCase(),ID_DOCTRMUTAMA)==true);
	        	
	        	myLogger.info(" ***** nama_doc utk disimpan : "+nama_doc);
	        	
	        	/*
	        	if(checkDuplicateDocName(session,"",ID_DOCTRM,nama_doc.toUpperCase(),ID_DOCTRMUTAMA)==true)
	        	{
	        		do {
	        			   // Statements
	        		}while(Boolean_expression);
	        		
	        		
	        		String current_ts = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
	        		nama_doc = nama_doc+"_COPY("+current_ts+")";
	        	}
	        	*/
	        	
	        	ps.setString(1,id_lampiran+"");
	        	ps.setString(2,ID_DOCTRM);
	        	ps.setBinaryStream(3, item.getInputStream(),(int)item.getSize());
	        	ps.setString(4, nama_doc.toUpperCase());
	        	ps.setString(5,item.getContentType());
	        	ps.setString(6,USER_ID_SYSTEM);
	        	ps.setString(7,ID_DOCTRMUTAMA);
	        	ps.setString(8,"TANAHRIZAB");
	        	//ps.setString(9, txdTarikhDokumen);
	        	ps.executeUpdate();	        	
	           
	            myLogger.info(" ***** SQL INSERT DOC : "+ps);
	            
	            String sql = "";
	            Statement stmt = db.getStatement();
				SQLRenderer r1 = new SQLRenderer();
				r.update("ID_DOCTRMLAMPIRAN", id_lampiran);
				r.add("TARIKH_DAFTAR", r1.unquote(txdTarikhDokumen));
				sql = r.getSQLUpdate("TBLPDTDOCTRMLAMPIRAN",db);	
				myLogger.info(" ***** UPDATE TBLPDTDOCTRMLAMPIRAN: "+sql);
				stmt.executeUpdate(sql);
				con.commit(); 
				 
	            AuditTrail.logActivity(this,session,"INS","TBLPDTDOCTRMLAMPIRAN ["+item.getName()+"] Insert");
		    } finally {
			      if (db != null) db.close();
		    }
		    
	  }		
	 
	 
	 
	 public void deleteLampiran(HttpSession session,String ID_DOCTRMLAMPIRAN,String NAMA) throws Exception {
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
				r.add("ID_DOCTRMLAMPIRAN",ID_DOCTRMLAMPIRAN);
				sql = r.getSQLDelete("TBLPDTDOCTRMLAMPIRAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTDOCTRMLAMPIRAN ["+NAMA+"] Deleted");
			
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
		public boolean checkDuplicateDocName(HttpSession session,String ID_DOCTRMLAMPIRAN,String ID_DOCTRM,String NAMA_DOC,String ID_DOCTRMUTAMA)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateDocName = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_DOCTRMLAMPIRAN) AS CNT FROM TBLPDTDOCTRMLAMPIRAN T  " +
						" WHERE T.ID_DOCTRMLAMPIRAN IS NOT NULL ";
				
						if(!ID_DOCTRMLAMPIRAN.equals(""))
						{
							sql += "  AND NVL(T.ID_DOCTRMLAMPIRAN,0) <> '"+ID_DOCTRMLAMPIRAN+"'  ";
						}
						
						sql += " AND UPPER(T.NAMA_DOC) = '"+NAMA_DOC.toUpperCase()+"' AND T.ID_DOCTRM = '"+ID_DOCTRM+"' AND T.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
				
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
		public boolean checkDuplicateSubDir(HttpSession session,String ID_REFER,String ID_DOCTRM,String ID_DOCTRMUTAMA,String TAJUK)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateSubDir = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_DOCTRM) AS CNT FROM TBLPDTDOCTRM T  " +
						" WHERE T.ID_DOCTRM IS NOT NULL ";
				
						if(!ID_DOCTRM.equals(""))
						{
							sql += "  AND NVL(T.ID_DOCTRM,0) <> '"+ID_DOCTRM+"'  ";
						}						
						if(!ID_REFER.equals(""))
						{
							sql += "  AND NVL(T.ID_REFER,0) = '"+ID_REFER+"'  ";
						}
						sql += " AND UPPER(T.TAJUK) = '"+TAJUK.toUpperCase()+"' " +
						" AND T.ID_DOCTRMUTAMA = '"+ID_DOCTRMUTAMA+"' ";
				
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
		public boolean checkDuplicateMainDir(HttpSession session,String ID_DOCTRMUTAMA,String TAJUK)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateMainDir = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_DOCTRMUTAMA) AS CNT FROM TBLPDTDOCTRMUTAMA T  " +
						" WHERE T.ID_DOCTRMUTAMA IS NOT NULL ";
				
						if(!ID_DOCTRMUTAMA.equals(""))
						{
							sql += "  AND NVL(T.ID_DOCTRMUTAMA,0) <> '"+ID_DOCTRMUTAMA+"'  ";
						}
						sql += " AND UPPER(T.TAJUK) = '"+TAJUK.toUpperCase()+"' AND T.JENIS_AKTIVITI = 'TANAHRIZAB' ";
				
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
		
	 public String saveUpdateLampiran(HttpSession session,String ID_DOCTRMLAMPIRAN,String ID_DOCTRM,String ID_DOCTRMUTAMA,String NAMA_DOC,String TARIKH_DAFTAR) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");		
				String TarikhDaftar = "to_date('" + TARIKH_DAFTAR + "','dd/MM/yyyy')";
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_DOCTRMLAMPIRAN", ID_DOCTRMLAMPIRAN);
				
				r.add("ID_DOCTRM", ID_DOCTRM);	
				r.add("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
				r.add("NAMA_DOC", NAMA_DOC.toUpperCase());
				r.add("TARIKH_DAFTAR", r.unquote(TarikhDaftar));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTDOCTRMLAMPIRAN");		
				myLogger.info("V3 : UPDATE TBLPDTDOCTRMLAMPIRAN : "+sql);
				myLogger.info("TARIKH DAFTAR ::: "+TARIKH_DAFTAR);
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTDOCTRMLAMPIRAN  [ID : "+ID_DOCTRMLAMPIRAN+"] Updated");
				
				
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
	 
	 
	 public String saveUpdateSubDir(HttpSession session,String ID_REFER,String ID_DOCTRM,String ID_DOCTRMUTAMA,String TAJUK) throws Exception {
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
				
				r.update("ID_DOCTRM", ID_DOCTRM);
				r.add("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
				r.add("ID_REFER", ID_REFER);
				r.add("TAJUK", TAJUK.toUpperCase());				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTDOCTRM");		
				myLogger.info("V3 : UPDATE TBLPDTDOCTRM : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTDOCTRM  [ID : "+ID_DOCTRM+"] Updated");
				
				
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
	 
	 public String saveUpdateMainDir(HttpSession session,String ID_DOCTRMUTAMA,String TAJUK) throws Exception {
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
				
				r.update("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
				r.add("TAJUK", TAJUK.toUpperCase());				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTDOCTRMUTAMA");		
				myLogger.info("V3 : UPDATE TBLPDTDOCTRMUTAMA : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTDOCTRMUTAMA  [ID : "+ID_DOCTRMUTAMA+"] Updated");
				
				
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
	 
	 
	 public String saveInsertSubDir(HttpSession session,String ID_REFER,String ID_DOCTRM,String ID_DOCTRMUTAMA,String TAJUK,String LAYER) throws Exception {
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
				long id_p = DB.getNextID("TBLPDTDOCTRM_SEQ");	 
				r.add("ID_DOCTRM", id_p);
				r.add("ID_DOCTRMUTAMA", ID_DOCTRMUTAMA);
				r.add("ID_REFER", ID_REFER);
				r.add("LAYER", LAYER);				
				r.add("TAJUK", TAJUK.toUpperCase());				
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("JENIS_AKTIVITI", "TANAHRIZAB");
				sql = r.getSQLInsert("TBLPDTDOCTRM");		
				myLogger.info("V3 : INSERT TBLPDTDOCTRM : "+sql);					
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"INS","TBLPDTDOCTRM  [ID : "+ID_DOCTRM+"] Inserted");
				
				
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
	 
	 public void deleteMainFolder(HttpSession session,String ID_DOCTRMUTAMA) throws Exception {
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
				r.add("ID_DOCTRMUTAMA",ID_DOCTRMUTAMA);
				sql = r.getSQLDelete("TBLPDTDOCTRM");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTDOCTRM [ID_DOCTRMUTAMA : "+ID_DOCTRMUTAMA+"] Deleted");
			
				r.clear();
				r.add("ID_DOCTRMUTAMA",ID_DOCTRMUTAMA);
				sql = r.getSQLDelete("TBLPDTDOCTRMLAMPIRAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTDOCTRMLAMPIRAN [ID_DOCTRMUTAMA : "+ID_DOCTRMUTAMA+"] Deleted");
			
					
				r.clear();
				r.add("ID_DOCTRMUTAMA",ID_DOCTRMUTAMA);
				sql = r.getSQLDelete("TBLPDTDOCTRMUTAMA");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTDOCTRMUTAMA [ID_DOCTRMUTAMA : "+ID_DOCTRMUTAMA+"] Deleted");
			
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
				
				sql = " SELECT T.ID_DOCTRM, T.ID_DOCTRMUTAMA, T.TAJUK, "+
						" T.LAYER, T.ID_REFER, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
						" FROM TBLPDTDOCTRM T " +
						" WHERE T.ID_REFER = '"+ID_REFER+"' "; 				
				myLogger.info(" SQL : listSubByID_REFER :"+ sql);			
				rs = stmt.executeQuery(sql);
				listSubByID_REFER = Collections.synchronizedList(new ArrayList());
				Map h = null;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_DOCTRM",rs.getString("ID_DOCTRM") == null ? "" : rs.getString("ID_DOCTRM"));			
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
}
