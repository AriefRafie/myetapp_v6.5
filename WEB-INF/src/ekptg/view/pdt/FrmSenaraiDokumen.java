
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
import ekptg.helpers.Paging2;

public class FrmSenaraiDokumen extends AjaxBasedModule {

	/**
	 * razman revamp pandangan undang2
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH="app/pdt/pandangan/";
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
			vm = "app/pdt/pandangan/SenaraiUtama.jsp";			
		}	
		else if(command.equals("countSubFolder"))
		{
			this.context.put("ID_PANDANGANUNDANGUTAMA", getParam("ID_PANDANGANUNDANGUTAMA"));
			this.context.put("ID_PANDANGANUNDANG", getParam("ID_PANDANGANUNDANG"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_PANDANGANUNDANGUTAMA")));
			this.context.put("TOTAL_SUB", getParam("TOTAL_SUB"));
			this.context.put("FLAG_SUB_OPENCLOSE", getParam("FLAG_SUB_OPENCLOSE"));
			vm = "app/pdt/pandangan/CountSubFolder.jsp";
		}
		else if(command.equals("countLampiran"))
		{
			this.context.put("ID_PANDANGANUNDANGUTAMA", getParam("ID_PANDANGANUNDANGUTAMA"));
			this.context.put("ID_PANDANGANUNDANG", getParam("ID_PANDANGANUNDANG"));
			this.context.put("AUTOLOAD", getParam("AUTOLOAD"));	
			this.context.put("LAYER", getParam("LAYER"));	
			this.context.put("MAX_LAYER", getMaxLayer(session, getParam("ID_PANDANGANUNDANGUTAMA")));
			this.context.put("TOTAL_LAMPIRAN", getParam("TOTAL_LAMPIRAN"));
			this.context.put("FLAG_LAMP_OPENCLOSE", getParam("FLAG_LAMP_OPENCLOSE"));
			vm = "app/pdt/pandangan/CountLampiran.jsp";
		}
		else if(command.equals("showLampiran") || command.equals("deleteLampiran"))
		{
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
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
				String ID_PANDANGANLAMPIRAN = getParam("ID_PANDANGANLAMPIRAN");
				String NAMA_DOC = getParam("NAMA_DOC");
				deleteLampiran(session,ID_PANDANGANLAMPIRAN,NAMA_DOC);
			}			
			this.context.put("MAX_LAYER", getMaxLayer(session, ID_PANDANGANUNDANGUTAMA));
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_LAMP_OPENCLOSE", "OPEN");
				listFolderLampiran = listFolderLampiran(session, ID_PANDANGANUNDANGUTAMA, ID_PANDANGANUNDANG, carianTerperinci,carianTerperinciLampiran);
				this.context.put("listFolderLampiran", listFolderLampiran);	
				vm = "app/pdt/pandangan/SenaraiLampiran.jsp";				
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_LAMP_OPENCLOSE.equals("OPEN"))
				{
					//this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					this.context.put("JUMLAH_LAMP", getParam("JUMLAH_LAMP"));
					this.context.put("FLAG_LAMP_OPENCLOSE", "CLOSE");
					vm = "app/pdt/pandangan/blank_lampiranFolder.jsp";
				}
				else
				{
					if(FLAG_LAMP_OPENCLOSE.equals("CLOSE"))
					{
						this.context.put("FLAG_LAMP_OPENCLOSE", "OPEN");						
					}
					listFolderLampiran = listFolderLampiran(session, ID_PANDANGANUNDANGUTAMA, ID_PANDANGANUNDANG, "","");
					this.context.put("listFolderLampiran", listFolderLampiran);	
					vm = "app/pdt/pandangan/SenaraiLampiran.jsp";
				}
			}					
		}	
		//'addSubDir','ID_REFER=$ID_PANDANGANUNDANG&LAYER=$LFS.NEXTLAYER&ID_PANDANGANUNDANGUTAMA=$LFS.ID_PANDANGANUNDANGUTAMA');
		else if(command.equals("addSubDir"))
		{			
			this.context.put("ID_REFER", getParam("ID_REFER"));
			myLogger.info(" getParam(ID_REFER) : "+getParam("ID_REFER"));
			this.context.put("LAYER", getParam("LAYER"));
			this.context.put("ID_PANDANGANUNDANGUTAMA", getParam("ID_PANDANGANUNDANGUTAMA"));		
			vm = "app/pdt/pandangan/addSubDir.jsp";			
		}		
		else if(command.equals("addLampiran"))
		{			
			this.context.put("ID_PANDANGANUNDANG", getParam("ID_PANDANGANUNDANG"));
			myLogger.info(" getParam(ID_PANDANGANUNDANG) : "+getParam("ID_PANDANGANUNDANG"));
			this.context.put("ID_PANDANGANUNDANGUTAMA", getParam("ID_PANDANGANUNDANGUTAMA"));
			
			vm = "app/pdt/pandangan/addLampiran.jsp";			
		}
		else if(command.equals("saveLampiran"))
		{						
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG_AFTERUPLOAD",ID_PANDANGANUNDANG);
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA_AFTERUPLOAD",ID_PANDANGANUNDANGUTAMA);
			saveLampiran(session,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA);
			this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");			
			vm = "app/pdt/pandangan/index.jsp";			
		}
		else if(command.equals("editSubDir"))
		{
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("LAYER", getParam("LAYER"));
			viewSubFoler = viewSubFoler(session,ID_PANDANGANUNDANG);
			this.context.put("viewSubFoler", viewSubFoler);
			vm = "app/pdt/pandangan/editSubDir.jsp";			
		}
		else if(command.equals("editLampiran"))
		{
			String ID_PANDANGANLAMPIRAN = getParam("ID_PANDANGANLAMPIRAN");
			this.context.put("ID_PANDANGANLAMPIRAN", ID_PANDANGANLAMPIRAN);
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			this.context.put("BIL", getParam("BIL"));
			viewLampiran = viewLampiran(session,ID_PANDANGANLAMPIRAN);
			this.context.put("viewLampiran", viewLampiran);
			vm = "app/pdt/pandangan/editLampiran.jsp";			
		}		
		else if(command.equals("validateMainDir"))
		{
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			String TAJUK = getParam("TAJUK");
			String duplicateMainDir = "N";
			if(checkDuplicateMainDir(session,ID_PANDANGANUNDANGUTAMA,TAJUK)==true)
			{
				duplicateMainDir = "Y";
			}
			this.context.put("duplicateMainDir", duplicateMainDir);
			vm = "app/pdt/pandangan/validateMainDir.jsp";			
		}	
		else if(command.equals("validateSubDir"))
		{
			String ID_PANDANGANLAMPIRAN = getParam("ID_PANDANGANLAMPIRAN");
			this.context.put("ID_PANDANGANLAMPIRAN", ID_PANDANGANLAMPIRAN);
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			String TAJUK = getParam("TAJUK");
			String duplicateSubDir = "N";
			if(checkDuplicateSubDir(session,ID_REFER,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA,TAJUK)==true)
			{
				duplicateSubDir = "Y";
			}
			this.context.put("duplicateSubDir", duplicateSubDir);
			vm = "app/pdt/pandangan/validateSubDir.jsp";			
		}	 
		else if(command.equals("validateLampiran"))
		{
			String ID_PANDANGANLAMPIRAN = getParam("ID_PANDANGANLAMPIRAN");
			this.context.put("ID_PANDANGANLAMPIRAN", ID_PANDANGANLAMPIRAN);
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			String NAMA_DOC = getParam("NAMA_DOC");
			String duplicateName = "N";
			if(checkDuplicateDocName(session,ID_PANDANGANLAMPIRAN,ID_PANDANGANUNDANG,NAMA_DOC,ID_PANDANGANUNDANGUTAMA)==true)
			{
				duplicateName = "Y";
			}
			this.context.put("duplicateName", duplicateName);
			vm = "app/pdt/pandangan/validateLampiran.jsp";			
		}	 
		else if(command.equals("editMainDir"))
		{
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			this.context.put("BIL", getParam("BIL"));
			viewMainFoler = viewMainFoler(session,ID_PANDANGANUNDANGUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			vm = "app/pdt/pandangan/editMainDir.jsp";			
		}	
		else if(command.equals("SimpanLampiran"))
		{
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
			this.context.put("BIL", getParam("BIL"));
			String ID_PANDANGANLAMPIRAN = getParam("ID_PANDANGANLAMPIRAN");
			this.context.put("ID_PANDANGANLAMPIRAN", ID_PANDANGANLAMPIRAN);
			
			String editLampiranField = getParam("editLampiranField_"+ID_PANDANGANUNDANG+"_"+ID_PANDANGANLAMPIRAN);
			myLogger.info(" ***** editLampiranField : "+editLampiranField);
			
			//function simpan disini	
			saveUpdateLampiran(session,ID_PANDANGANLAMPIRAN,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA,editLampiranField);
			
			viewLampiran = viewLampiran(session,ID_PANDANGANLAMPIRAN);
			this.context.put("viewLampiran", viewLampiran);
			vm = "app/pdt/pandangan/viewLampiran.jsp";			
		}
		else if(command.equals("SimpanSubDir"))
		{
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
			String ID_REFER = getParam("ID_REFER");
			this.context.put("ID_REFER", ID_REFER);
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			this.context.put("BIL", getParam("BIL"));
			String LAYER = getParam("LAYER");
			this.context.put("LAYER",LAYER);			
			String editSubDirField = getParam("editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_"+ID_PANDANGANUNDANG+"");
			myLogger.info("(editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_PANDANGANUNDANG+") :::: "+editSubDirField);
			saveUpdateSubDir(session,ID_REFER,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA,editSubDirField);			
			viewSubFoler = viewSubFoler(session,ID_PANDANGANUNDANG);
			this.context.put("viewSubFoler", viewSubFoler);
			vm = "app/pdt/pandangan/viewSubDir.jsp";			
		}
		else if(command.equals("SimpanMainDir"))
		{
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			this.context.put("BIL", getParam("BIL"));			
			String editMainDirField = getParam("editMainDirField_"+ID_PANDANGANUNDANGUTAMA);
			//function simpan disini
			saveUpdateMainDir(session,ID_PANDANGANUNDANGUTAMA,editMainDirField);			
			viewMainFoler = viewMainFoler(session,ID_PANDANGANUNDANGUTAMA);
			this.context.put("viewMainFoler", viewMainFoler);
			vm = "app/pdt/pandangan/viewMainDir.jsp";			
		}
		else if(command.equals("SimpanAddSubDir") || command.equals("showAllFolder") || command.equals("deleteSubDir"))
		{
			
			String carianTerperinci = getParam("carianTerperinci");
			this.context.put("carianTerperinci", carianTerperinci);		
			String carianTerperinciLampiran = getParam("carianTerperinciLampiran");
			this.context.put("carianTerperinciLampiran", carianTerperinciLampiran);
			
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			this.context.put("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
			String ID_PANDANGANUNDANG = getParam("ID_PANDANGANUNDANG");
			this.context.put("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
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
				String editSubDirField = getParam("editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_");
				myLogger.info("(editSubDirField_"+ID_PANDANGANUNDANGUTAMA+"_"+ID_REFER+"_"+ID_PANDANGANUNDANG+") -- editSubDirField :"+editSubDirField);
				saveInsertSubDir(session,ID_REFER,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA,editSubDirField,LAYER);
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
			
			
			
			String MAX_LAYER = getMaxLayer(session, getParam("ID_PANDANGANUNDANGUTAMA"))+"";
			this.context.put("MAX_LAYER", MAX_LAYER);
			
			this.context.put("AUTOLOAD", AUTOLOAD);
			Hashtable filter_carian_folder = null;
			String list_id_refer = "0";
			String list_id_actual = "0";
			if(AUTOLOAD.equals("Y") && (!carianTerperinci.equals("") || !carianTerperinciLampiran.equals("") ))
			{
				filter_carian_folder = new Hashtable();
				filter_carian_folder = getIDP_check(session, ID_PANDANGANUNDANGUTAMA, LAYER, carianTerperinci,carianTerperinciLampiran, MAX_LAYER);		
				list_id_refer = (String) filter_carian_folder.get("SET_ID_REFER");
				list_id_actual = (String) filter_carian_folder.get("SET_ID_PANDANGANUNDANG");
			}			
			myLogger.info(" **********filter_carian_folder : "+filter_carian_folder);
			listFolderSub = listFolderSub(session, ID_PANDANGANUNDANGUTAMA, ID_REFER, LAYER,carianTerperinci,carianTerperinciLampiran,list_id_refer,list_id_actual,AUTOLOAD);
			this.context.put("listFolderSub", listFolderSub);
			this.context.put("TOTAL_SUB", listFolderSub.size());
			this.context.put("NAMA_FOLDER", getParam("NAMA_FOLDER"));
			
			
			
			//myLogger.info(" **********LAYER : "+LAYER);
			//myLogger.info(" **********MAX_LAYER : "+MAX_LAYER);
			
			
			
			
			if(AUTOLOAD.equals("Y"))
			{
				this.context.put("FLAG_OPENCLOSE", "OPEN");
				this.context.put("FLAG_SUB_OPENCLOSE", "OPEN");
				
				vm = "app/pdt/pandangan/SenaraiSubFolder.jsp";
			}
			else if(AUTOLOAD.equals("N"))
			{
				if(FLAG_OPENCLOSE.equals("OPEN"))
				{
					this.context.put("FLAG_OPENCLOSE", "CLOSE");
					this.context.put("JUMLAH_SUB", getParam("JUMLAH_SUB"));
					//this.context.put("JUMLAH_LAMP", getParam("JUMLAH_LAMP"));
					vm = "app/pdt/pandangan/blank_subFolder.jsp";
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
						vm = "app/pdt/pandangan/blank_subFolder.jsp";
					}
					else
					{
						if(FLAG_SUB_OPENCLOSE.equals("CLOSE"))
						{
							this.context.put("FLAG_SUB_OPENCLOSE", "OPEN");						
						}
						vm = "app/pdt/pandangan/SenaraiSubFolder.jsp";
					}
						
				}
			}
			
			
		}
		else if(command.equals("batalCarianUtama") || command.equals("showFolderUtama") || command.equals("deleteFolderUtama"))
		{
			if(command.equals("deleteFolderUtama"))
			{
				//function delete
				String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
				deleteMainFolder(session,ID_PANDANGANUNDANGUTAMA);
			}
			listFolderUtama = listFolderUtama(session, "","");
			setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");
			vm = "app/pdt/pandangan/SenaraiUtama.jsp";	
		}
		else if(command.equals("addTajukUtama") || command.equals("batalTajukUtama"))
		{			
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			viewTajukUtama = viewTajukUtama(session, ID_PANDANGANUNDANGUTAMA);
			this.context.put("viewTajukUtama", viewTajukUtama);
			vm = "app/pdt/pandangan/addTajukUtama.jsp";	
		}
		else if(command.equals("saveTajukUtama"))
		{			
			String ID_PANDANGANUNDANGUTAMA = getParam("ID_PANDANGANUNDANGUTAMA");
			saveTajukUtama(session,ID_PANDANGANUNDANGUTAMA);
			listFolderUtama = listFolderUtama(session, "","");
			setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");
			vm = "app/pdt/pandangan/SenaraiUtama.jsp";
		}
		else if(command.equals("closeTajukUtama"))
		{			
			vm = "app/pdt/pandangan/blank.jsp";
		}
		else
		{
			//listFolderUtama = listFolderUtama(session, "");
			//setupPageList(session, action, listFolderUtama, "listFolderUtama",command,"div_senaraiUtama");
			vm = "app/pdt/pandangan/index.jsp";	
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
	public int getMaxLayer(HttpSession session, String ID_PANDANGANUNDANGUTAMA)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		int max_layer = 0;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = " SELECT MAX(NVL(LAYER,0)) AS MAX_LAYER FROM TBLPDTPANDANGANUNDANG K " +
					" WHERE K.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
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
			sql = " SELECT T.ID_PANDANGANUNDANGUTAMA, T.TAJUK, "+
					" NVL(SUB.SAIZ,0) AS JUMLAH_SUB FROM TBLPDTPANDANGANUNDANGUTAMA T ,  "+
					" (SELECT S.ID_PANDANGANUNDANGUTAMA, COUNT(S.ID_PANDANGANUNDANG) AS SAIZ " +
					" FROM TBLPDTPANDANGANUNDANG S WHERE S.LAYER = '1' "+
					" GROUP BY  S.ID_PANDANGANUNDANGUTAMA) SUB "+
					" WHERE T.ID_PANDANGANUNDANGUTAMA IS NOT NULL   "+
					" AND SUB.ID_PANDANGANUNDANGUTAMA(+) = T.ID_PANDANGANUNDANGUTAMA ";
					if(!carianTerperinci.equals(""))
					{
						sql += " AND UPPER(T.TAJUK) LIKE '%"+carianTerperinci.toUpperCase()+"%' ";
					}
					sql += " ORDER BY T.TAJUK ";		
			*/
			
			sql = " SELECT   T.ID_PANDANGANUNDANGUTAMA, T.TAJUK, NVL (SUB.SAIZ, 0) AS JUMLAH_SUB, "+
					" NVL (TF.SAIZ, 0) AS TOTAL_FOLDER,NVL (TL.SAIZ, 0) AS TOTAL_LAMPIRAN, NVL(ML.MAX_LAYER,0) AS MAX_LAYER "+
					" FROM TBLPDTPANDANGANUNDANGUTAMA T, "+
					" (SELECT   S.ID_PANDANGANUNDANGUTAMA, "+
					" COUNT (DISTINCT S.ID_PANDANGANUNDANG) AS SAIZ "+
					" FROM TBLPDTPANDANGANUNDANG S "+
					" WHERE S.LAYER = '1' "+
					" GROUP BY S.ID_PANDANGANUNDANGUTAMA) SUB, "+
					" (SELECT   S.ID_PANDANGANUNDANGUTAMA, "+
					" COUNT (DISTINCT S.ID_PANDANGANUNDANG) AS SAIZ "+
					" FROM TBLPDTPANDANGANUNDANG S,TBLPDTPANDANGANLAMPIRAN L "+
					" WHERE S.ID_PANDANGANUNDANG = L.ID_PANDANGANUNDANG(+)    ";  
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
					sql += " GROUP BY S.ID_PANDANGANUNDANGUTAMA) TF, "+
					" (SELECT   S.ID_PANDANGANUNDANGUTAMA, "+
					" COUNT (DISTINCT S.ID_PANDANGANLAMPIRAN) AS SAIZ "+
					" FROM TBLPDTPANDANGANLAMPIRAN S ";
					if(!carianTerperinciLampiran.equals(""))
					{
						sql += " WHERE UPPER(S.NAMA_DOC) LIKE '%"+carianTerperinciLampiran.toUpperCase()+"%' ";
					}							
					sql += " GROUP BY S.ID_PANDANGANUNDANGUTAMA) TL , "+
							" (SELECT K.ID_PANDANGANUNDANGUTAMA, MAX(NVL(LAYER,0)) AS MAX_LAYER FROM TBLPDTPANDANGANUNDANG K "+
							" GROUP BY K.ID_PANDANGANUNDANGUTAMA) ML"+
					" WHERE T.ID_PANDANGANUNDANGUTAMA IS NOT NULL "+
					" AND SUB.ID_PANDANGANUNDANGUTAMA(+) = T.ID_PANDANGANUNDANGUTAMA "+
					" AND TF.ID_PANDANGANUNDANGUTAMA(+) = T.ID_PANDANGANUNDANGUTAMA "+
					" AND TL.ID_PANDANGANUNDANGUTAMA(+) = T.ID_PANDANGANUNDANGUTAMA " +
					" AND ML.ID_PANDANGANUNDANGUTAMA(+) = T.ID_PANDANGANUNDANGUTAMA ";
					
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
				h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));			
				h.put("TAJUK",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));	
				h.put("JUMLAH_SUB", rs.getString("JUMLAH_SUB") == null ? 0 : rs.getInt("JUMLAH_SUB"));
				h.put("TOTAL_FOLDER", rs.getString("TOTAL_FOLDER") == null ? 0 : rs.getInt("TOTAL_FOLDER"));
				h.put("TOTAL_LAMPIRAN", rs.getString("TOTAL_LAMPIRAN") == null ? 0 : rs.getInt("TOTAL_LAMPIRAN"));
				h.put("MAX_LAYER", rs.getString("MAX_LAYER") == null ? 0 : rs.getInt("MAX_LAYER"));
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
	public List listFolderSub(HttpSession session, String ID_PANDANGANUNDANGUTAMA, String ID_REFER, String LAYER,String Carian, String CarianLampiran,String id_PU_sebelum,String id_PU_current,String AUTOLOAD)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderSub = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT DISTINCT T.ID_PANDANGANUNDANG, T.ID_PANDANGANUNDANGUTAMA, T.TAJUK, T.LAYER, "+
					" T.ID_REFER, NVL (SUB.SAIZ, 0) AS JUMLAH_SUB, NVL (LAMP.SAIZ, 0) AS JUMLAH_LAMP," +
					" NVL (SUB_FIND.SAIZ, 0) AS JUMLAH_SUB_FIND, NVL (LAMP_FIND.SAIZ, 0) AS JUMLAH_LAMP_FIND "+
					" FROM TBLPDTPANDANGANUNDANG T, "+
					" (SELECT   TEMP.ID_REFER, COUNT (TEMP.ID_PANDANGANUNDANG) AS SAIZ "+
					" FROM TBLPDTPANDANGANUNDANG TEMP "+
					" WHERE TEMP.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' "+
					" GROUP BY TEMP.ID_REFER) SUB, "+
					" (SELECT   TEMP.ID_REFER, COUNT (TEMP.ID_PANDANGANUNDANG) AS SAIZ "+
					" FROM TBLPDTPANDANGANUNDANG TEMP "+
					" WHERE TEMP.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' " +
					" AND TEMP.ID_PANDANGANUNDANG IN ("+id_PU_current+") "+
					" GROUP BY TEMP.ID_REFER) SUB_FIND, "+
					" (SELECT   TEMP.ID_PANDANGANUNDANG, COUNT (TEMP.ID_PANDANGANLAMPIRAN) AS SAIZ "+
					" FROM TBLPDTPANDANGANLAMPIRAN TEMP "+
					" WHERE TEMP.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' "+
					" GROUP BY TEMP.ID_PANDANGANUNDANG) LAMP, " +
					" (SELECT   TEMP.ID_PANDANGANUNDANG, COUNT (TEMP.ID_PANDANGANLAMPIRAN) AS SAIZ "+
					" FROM TBLPDTPANDANGANLAMPIRAN TEMP "+
					" WHERE TEMP.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
					//" AND TEMP.ID_PANDANGANUNDANG IN ("+id_PU_current+") ";
					if(!CarianLampiran.equals("") && AUTOLOAD.equals("Y"))
					{
						sql += " AND UPPER(TEMP.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' ";
					}
					sql += " GROUP BY TEMP.ID_PANDANGANUNDANG) LAMP_FIND, " +
					" TBLPDTPANDANGANLAMPIRAN TEMP_LAMP "+
					" WHERE T.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
					if(ID_REFER.equals("") || ID_REFER==null)
					{
						sql += " AND (T.ID_REFER IS NULL OR T.ID_REFER = '') ";
					}
					else
					{
						sql += " AND T.ID_REFER = '"+ID_REFER+"' ";
					}	
					sql += " AND SUB.ID_REFER(+) = T.ID_PANDANGANUNDANG ";
					sql += " AND SUB_FIND.ID_REFER(+) = T.ID_PANDANGANUNDANG ";
					
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
						
						sql += " T.ID_PANDANGANUNDANG IN ("+id_PU_sebelum+") ";
						
						
						sql += " ) ";
					}
					
					
					sql += " AND LAMP.ID_PANDANGANUNDANG(+) = T.ID_PANDANGANUNDANG ";
					sql += " AND LAMP_FIND.ID_PANDANGANUNDANG(+) = T.ID_PANDANGANUNDANG ";					
				    sql += " AND TEMP_LAMP.ID_PANDANGANUNDANG(+) = T.ID_PANDANGANUNDANG "+
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
				h.put("ID_PANDANGANUNDANG",rs.getString("ID_PANDANGANUNDANG") == null ? "" : rs.getString("ID_PANDANGANUNDANG"));			
				h.put("TAJUK",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));			
				h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));			
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
	
	
		public Hashtable getIDP_check(HttpSession session, String ID_PANDANGANUNDANGUTAMA, String LAYER, String Carian, String CarianLampiran,String MAX_LAYER)throws Exception {
			
			int l = Integer.parseInt(LAYER);
			int max_l = Integer.parseInt(MAX_LAYER);
			
			String getID_PANDANGUNDANG = "0";
			String getID_PANDANGUNDANG_ACTUAL = "0";
			
			
			for(int i = max_l; i>l; i--)
			{
				   List listIDP_by_layer_check = listIDP_by_layer(session, ID_PANDANGANUNDANGUTAMA, i+"", Carian, CarianLampiran,getID_PANDANGUNDANG);
				   String temp_id_p = "0";
				   String temp_id_p_actual = "0";
				   for(int x = 0; x < listIDP_by_layer_check.size();x++)
				   {
					   Map m = (Map) listIDP_by_layer_check.get(x);
					   temp_id_p += ","+m.get("ID_REFER");
					   temp_id_p_actual += ","+m.get("ID_PANDANGANUNDANG");
				   }			
				   getID_PANDANGUNDANG = temp_id_p;
				   getID_PANDANGUNDANG_ACTUAL = temp_id_p_actual;
				   myLogger.info(" *** LAYER : "+i+" MAX_LAYER : "+max_l+" getID_PANDANGUNDANG :"+getID_PANDANGUNDANG+" getID_PANDANGUNDANG_ACTUAL : "+getID_PANDANGUNDANG_ACTUAL);
			}			
			//return getID_PANDANGUNDANG;
			Hashtable h = new Hashtable();
			h.put("SET_ID_REFER", getID_PANDANGUNDANG);
			h.put("SET_ID_PANDANGANUNDANG", getID_PANDANGUNDANG_ACTUAL);
			
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
		public List listIDREFER_by_layer(HttpSession session, String ID_REFER,String ID_PANDANGANUNDANGUTAMA, String LAYER, String Carian)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listIDREFER_by_layer = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();				
				sql = " SELECT DISTINCT U.ID_REFER FROM  TBLPDTPANDANGANUNDANG U "+
						" WHERE U.LAYER = '"+LAYER+"' AND U.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";				
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
		public List listIDP_by_layer(HttpSession session, String ID_PANDANGANUNDANGUTAMA, String LAYER, String Carian,String CarianLampiran, String getIdAnakfolder)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listIDP_by_layer = null;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();				
				sql = " SELECT DISTINCT U.ID_PANDANGANUNDANG,U.ID_REFER FROM TBLPDTPANDANGANLAMPIRAN L, TBLPDTPANDANGANUNDANG U "+
						" WHERE L.ID_PANDANGANUNDANG(+) = U.ID_PANDANGANUNDANG " +
						" AND U.LAYER = '"+LAYER+"' AND U.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"'";
						
							
						sql += " AND ( ";
						
						if(!Carian.equals(""))
						{
							sql += " UPPER(U.TAJUK) LIKE '%"+Carian.toUpperCase()+"%' OR ";
							
						}
						if(!CarianLampiran.equals(""))
						{
							sql += " UPPER(L.NAMA_DOC) LIKE '%"+CarianLampiran.toUpperCase()+"%' OR ";
						}
						
						sql += " U.ID_PANDANGANUNDANG IN ("+getIdAnakfolder+") ";
						
						
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
					h.put("ID_PANDANGANUNDANG",rs.getString("ID_PANDANGANUNDANG") == null ? "" : rs.getString("ID_PANDANGANUNDANG"));
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
	public List listFolderLampiran(HttpSession session, String ID_PANDANGANUNDANGUTAMA, String ID_REFER, String Carian,String CarianLampiran)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listFolderLampiran = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT T.ID_PANDANGANLAMPIRAN, T.ID_PANDANGANUNDANG, T.CONTENT, "+
					" T.NAMA_DOC, T.JENIS_MIME, T.ID_MASUK,  "+
					" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI,  "+
					" T.ID_PANDANGANUNDANGUTAMA "+
					" FROM TBLPDTPANDANGANLAMPIRAN T " +
					" WHERE T.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' " +
					" AND T.ID_PANDANGANUNDANG = '"+ID_REFER+"' ";
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
				h.put("ID_PANDANGANLAMPIRAN",rs.getString("ID_PANDANGANLAMPIRAN") == null ? "" : rs.getString("ID_PANDANGANLAMPIRAN"));	
				h.put("ID_PANDANGANUNDANG",rs.getString("ID_PANDANGANUNDANG") == null ? "" : rs.getString("ID_PANDANGANUNDANG"));	
				h.put("CONTENT",rs.getString("CONTENT") == null ? "" : rs.getString("CONTENT"));	
				h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));	
				h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));	
				h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));
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
	
	
	public Hashtable viewSubFoler(HttpSession session, String ID_PANDANGANUNDANG) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_PANDANGANUNDANG, T.ID_PANDANGANUNDANGUTAMA, T.TAJUK, T.LAYER, T.ID_REFER, T.ID_MASUK, "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI FROM TBLPDTPANDANGANUNDANG T " +
						" WHERE T.ID_PANDANGANUNDANG = '"+ID_PANDANGANUNDANG+"' ";
						
				myLogger.info(" viewSubFoler :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_PANDANGANUNDANG",rs.getString("ID_PANDANGANUNDANG") == null ? "" : rs.getString("ID_PANDANGANUNDANG"));
					h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));
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
	
	public Hashtable viewLampiran(HttpSession session, String ID_PANDANGANLAMPIRAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			
			
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_PANDANGANLAMPIRAN, T.ID_PANDANGANUNDANG, T.CONTENT, "+
						" T.NAMA_DOC, T.JENIS_MIME, T.ID_PANDANGANUNDANGUTAMA "+
						" FROM TBLPDTPANDANGANLAMPIRAN T "+
						" WHERE T.ID_PANDANGANLAMPIRAN = '"+ID_PANDANGANLAMPIRAN+"' ";
						
				myLogger.info(" viewLampiran :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_PANDANGANUNDANG",rs.getString("ID_PANDANGANUNDANG") == null ? "" : rs.getString("ID_PANDANGANUNDANG"));
					h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));
					h.put("ID_PANDANGANLAMPIRAN",rs.getString("ID_PANDANGANLAMPIRAN") == null ? "" : rs.getString("ID_PANDANGANLAMPIRAN"));
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
	
	
	public Hashtable viewMainFoler(HttpSession session, String ID_PANDANGANUNDANGUTAMA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
				sql = " SELECT T.ID_PANDANGANUNDANGUTAMA, T.TAJUK FROM TBLPDTPANDANGANUNDANGUTAMA T WHERE T.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
						
				myLogger.info(" viewMainFoler :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));
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
	
	
	
	
	public Hashtable viewTajukUtama(HttpSession session, String ID_PANDANGANUNDANGUTAMA) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			h = new Hashtable();
			if(!ID_PANDANGANUNDANGUTAMA.equals("") || !ID_PANDANGANUNDANGUTAMA.equals(""))
			{		
				sql = " SELECT T.ID_PANDANGANUNDANGUTAMA, T.TAJUK FROM TBLPDTPANDANGANUNDANGUTAMA T " +
						" WHERE T.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
				myLogger.info(" viewPejabatJKPTG :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				
				while (rs.next()) {
					h.put("ID_PANDANGANUNDANGUTAMA",rs.getString("ID_PANDANGANUNDANGUTAMA") == null ? "" : rs.getString("ID_PANDANGANUNDANGUTAMA"));
					h.put("TAJUK_UTAMA",rs.getString("TAJUK") == null ? "" : rs.getString("TAJUK"));
				}
			}
			else
			{
				h.put("ID_PANDANGANUNDANGUTAMA","");
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
	
	
	
	public String saveTajukUtama(HttpSession session,String ID_PANDANGANUNDANGUTAMA) throws Exception {
		Connection conn = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		Db db = null;
		String sql = "";
		long long_ID_PANDANGANUNDANGUTAMA = 0;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String TAJUK_UTAMA_ = getParam("TAJUK_UTAMA_"+ID_PANDANGANUNDANGUTAMA);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(!ID_PANDANGANUNDANGUTAMA.equals("") && !ID_PANDANGANUNDANGUTAMA.equals(null))
			{
				long_ID_PANDANGANUNDANGUTAMA = Long.parseLong(ID_PANDANGANUNDANGUTAMA);
				r.update("ID_PANDANGANUNDANGUTAMA", long_ID_PANDANGANUNDANGUTAMA);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			}
			else
			{
				long_ID_PANDANGANUNDANGUTAMA = DB.getNextID(db, "TBLPDTPANDANGANUNDANGUTAMA_SEQ");
				r.add("ID_PANDANGANUNDANGUTAMA", long_ID_PANDANGANUNDANGUTAMA);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_MASUK", USER_ID_SYSTEM);
			}
			r.add("TAJUK", TAJUK_UTAMA_.toUpperCase());			
			if(!ID_PANDANGANUNDANGUTAMA.equals("") && !ID_PANDANGANUNDANGUTAMA.equals(null))
			{
				sql = r.getSQLUpdate("TBLPDTPANDANGANUNDANGUTAMA");		
				myLogger.info("UPDATE TBLPDTPANDANGANUNDANGUTAMA : "+sql);
			}
			else
			{
				sql = r.getSQLInsert("TBLPDTPANDANGANUNDANGUTAMA");		
				myLogger.info("INSERT TBLPDTPANDANGANUNDANGUTAMA : "+sql);
			}
			stmt.executeUpdate(sql);			
			conn.commit();
			AuditTrail.logActivity(this,session,"INS","TBLPDTPANDANGANUNDANGUTAMA  [ID : "+long_ID_PANDANGANUNDANGUTAMA+"] Inserted");
			
			
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return long_ID_PANDANGANUNDANGUTAMA+"";
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
	
	private void saveLampiran(HttpSession session,String ID_PANDANGANUNDANG,String ID_PANDANGANUNDANGUTAMA) throws Exception {
		 
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
	    	  myLogger.info(" ID_PANDANGANUNDANG : "+ID_PANDANGANUNDANG+" ID_PANDANGANUNDANGUTAMA : "+ID_PANDANGANUNDANGUTAMA+" item : "+item);
	    	  saveLampiranDB(item,ID_PANDANGANUNDANG,ID_PANDANGANUNDANGUTAMA);
	    	 
	      }
	    }
	  }
	
	
	
	
	 public void checkChildDeleteSub(HttpSession session,String ID_PANDANGANUNDANG) throws Exception {	
		 
		 myLogger.info(" DELETE : "+ID_PANDANGANUNDANG);
		 deleteSub(session,ID_PANDANGANUNDANG);
		 List listSubByID_REFER = listSubByID_REFER(ID_PANDANGANUNDANG);
		 for(int i = 0; i < listSubByID_REFER.size();i++)
		 {			   
			   Map m = (Map) listSubByID_REFER.get(i);
			   checkChildDeleteSub(session,(String) m.get("ID_PANDANGANUNDANG"));			   
		 }	 
	 }
	 
	 public void deleteSub(HttpSession session,String ID_PANDANGANUNDANG) throws Exception {
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
				r.add("ID_PANDANGANUNDANG",ID_PANDANGANUNDANG);
				sql = r.getSQLDelete("TBLPDTPANDANGANUNDANG");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTPANDANGANUNDANG [ID : "+ID_PANDANGANUNDANG+"] Deleted");
				
				r.clear();
				r.add("ID_PANDANGANUNDANG",ID_PANDANGANUNDANG);
				sql = r.getSQLDelete("TBLPDTPANDANGANLAMPIRAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTPANDANGANLAMPIRAN [ID_PANDANGANUNDANG : "+ID_PANDANGANUNDANG+"] Deleted");
			
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
				
	
	
	 private void saveLampiranDB(FileItem item,String ID_PANDANGANUNDANG,String ID_PANDANGANUNDANGUTAMA) throws Exception {
		    HttpSession session = request.getSession();	
		    String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
	  		Db db = null;
	        try {		        	
	        	long id_lampiran = DB.getNextID("TBLPDTPANDANGANLAMPIRAN_SEQ");	        	
	        	db = new Db();		        	
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPDTPANDANGANLAMPIRAN ( "+
		    		  " ID_PANDANGANLAMPIRAN, ID_PANDANGANUNDANG, CONTENT,  "+
		    		  " NAMA_DOC, JENIS_MIME, ID_MASUK,  "+
		    		  " TARIKH_MASUK, ID_PANDANGANUNDANGUTAMA)  "+
		    		  " VALUES (?,?,?,?,?,?,SYSDATE,?) ");
	        	
	        	
	        	String nama_asal_doc = item.getName().substring(0, item.getName().indexOf("."));
	        	String nama_doc = nama_asal_doc;	        	
	        	int copy_count = 0;
	        	do {
     			   // Statements
	        		myLogger.info(" ***** nama_doc ada duplicate : "+nama_doc);
	        		if(checkDuplicateDocName(session,"",ID_PANDANGANUNDANG,nama_doc.toUpperCase(),ID_PANDANGANUNDANGUTAMA)==true)
	        		{
		        		copy_count++;
		        		nama_doc = nama_asal_doc + " COPY("+copy_count+")";
		        		myLogger.info(" ***** nama_doc baru : "+nama_doc);
	        		}
	        	}while(checkDuplicateDocName(session,"",ID_PANDANGANUNDANG,nama_doc.toUpperCase(),ID_PANDANGANUNDANGUTAMA)==true);
	        	
	        	myLogger.info(" ***** nama_doc utk disimpan : "+nama_doc);
	        	
	        	/*
	        	if(checkDuplicateDocName(session,"",ID_PANDANGANUNDANG,nama_doc.toUpperCase(),ID_PANDANGANUNDANGUTAMA)==true)
	        	{
	        		do {
	        			   // Statements
	        		}while(Boolean_expression);
	        		
	        		
	        		String current_ts = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
	        		nama_doc = nama_doc+"_COPY("+current_ts+")";
	        	}
	        	*/
	        	
	        	ps.setString(1,id_lampiran+"");
	        	ps.setString(2,ID_PANDANGANUNDANG);
	        	ps.setBinaryStream(3, item.getInputStream(),(int)item.getSize());
	        	ps.setString(4, nama_doc.toUpperCase());
	        	ps.setString(5,item.getContentType());
	        	ps.setString(6,USER_ID_SYSTEM);
	        	ps.setString(7,ID_PANDANGANUNDANGUTAMA);
	        	ps.executeUpdate();	        	
	            con.commit(); 
	            
	            AuditTrail.logActivity(this,session,"INS","TBLPDTPANDANGANLAMPIRAN ["+item.getName()+"] Insert");
		    } finally {
			      if (db != null) db.close();
		    }
		    
	  }		
	 
	 
	 
	 public void deleteLampiran(HttpSession session,String ID_PANDANGANLAMPIRAN,String NAMA) throws Exception {
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
				r.add("ID_PANDANGANLAMPIRAN",ID_PANDANGANLAMPIRAN);
				sql = r.getSQLDelete("TBLPDTPANDANGANLAMPIRAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTPANDANGANLAMPIRAN ["+NAMA+"] Deleted");
			
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
		public boolean checkDuplicateDocName(HttpSession session,String ID_PANDANGANLAMPIRAN,String ID_PANDANGANUNDANG,String NAMA_DOC,String ID_PANDANGANUNDANGUTAMA)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateDocName = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_PANDANGANLAMPIRAN) AS CNT FROM TBLPDTPANDANGANLAMPIRAN T  " +
						" WHERE T.ID_PANDANGANLAMPIRAN IS NOT NULL ";
				
						if(!ID_PANDANGANLAMPIRAN.equals(""))
						{
							sql += "  AND NVL(T.ID_PANDANGANLAMPIRAN,0) <> '"+ID_PANDANGANLAMPIRAN+"'  ";
						}
						
						sql += " AND UPPER(T.NAMA_DOC) = '"+NAMA_DOC.toUpperCase()+"' AND T.ID_PANDANGANUNDANG = '"+ID_PANDANGANUNDANG+"' AND T.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
				
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
		public boolean checkDuplicateSubDir(HttpSession session,String ID_REFER,String ID_PANDANGANUNDANG,String ID_PANDANGANUNDANGUTAMA,String TAJUK)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateSubDir = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_PANDANGANUNDANG) AS CNT FROM TBLPDTPANDANGANUNDANG T  " +
						" WHERE T.ID_PANDANGANUNDANG IS NOT NULL ";
				
						if(!ID_PANDANGANUNDANG.equals(""))
						{
							sql += "  AND NVL(T.ID_PANDANGANUNDANG,0) <> '"+ID_PANDANGANUNDANG+"'  ";
						}						
						if(!ID_REFER.equals(""))
						{
							sql += "  AND NVL(T.ID_REFER,0) = '"+ID_REFER+"'  ";
						}
						sql += " AND UPPER(T.TAJUK) = '"+TAJUK.toUpperCase()+"' " +
						" AND T.ID_PANDANGANUNDANGUTAMA = '"+ID_PANDANGANUNDANGUTAMA+"' ";
				
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
		public boolean checkDuplicateMainDir(HttpSession session,String ID_PANDANGANUNDANGUTAMA,String TAJUK)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			boolean checkDuplicateMainDir = false;
			String sql = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT COUNT(T.ID_PANDANGANUNDANGUTAMA) AS CNT FROM TBLPDTPANDANGANUNDANGUTAMA T  " +
						" WHERE T.ID_PANDANGANUNDANGUTAMA IS NOT NULL ";
				
						if(!ID_PANDANGANUNDANGUTAMA.equals(""))
						{
							sql += "  AND NVL(T.ID_PANDANGANUNDANGUTAMA,0) <> '"+ID_PANDANGANUNDANGUTAMA+"'  ";
						}
						sql += " AND UPPER(T.TAJUK) = '"+TAJUK.toUpperCase()+"' ";
				
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
		
	 public String saveUpdateLampiran(HttpSession session,String ID_PANDANGANLAMPIRAN,String ID_PANDANGANUNDANG,String ID_PANDANGANUNDANGUTAMA,String NAMA_DOC) throws Exception {
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
				
				r.update("ID_PANDANGANLAMPIRAN", ID_PANDANGANLAMPIRAN);
				
				r.add("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);	
				r.add("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
				r.add("NAMA_DOC", NAMA_DOC.toUpperCase());
				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTPANDANGANLAMPIRAN");		
				myLogger.info("V3 : UPDATE TBLPDTPANDANGANLAMPIRAN : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTPANDANGANLAMPIRAN  [ID : "+ID_PANDANGANLAMPIRAN+"] Updated");
				
				
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
	 
	 
	 public String saveUpdateSubDir(HttpSession session,String ID_REFER,String ID_PANDANGANUNDANG,String ID_PANDANGANUNDANGUTAMA,String TAJUK) throws Exception {
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
				
				r.update("ID_PANDANGANUNDANG", ID_PANDANGANUNDANG);
				r.add("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
				r.add("ID_REFER", ID_REFER);
				r.add("TAJUK", TAJUK.toUpperCase());				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTPANDANGANUNDANG");		
				myLogger.info("V3 : UPDATE TBLPDTPANDANGANUNDANG : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTPANDANGANUNDANG  [ID : "+ID_PANDANGANUNDANG+"] Updated");
				
				
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
	 
	 public String saveUpdateMainDir(HttpSession session,String ID_PANDANGANUNDANGUTAMA,String TAJUK) throws Exception {
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
				
				r.update("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
				r.add("TAJUK", TAJUK.toUpperCase());				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPDTPANDANGANUNDANGUTAMA");		
				myLogger.info("V3 : UPDATE TBLPDTPANDANGANUNDANGUTAMA : "+sql);		    
						
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"UPD","TBLPDTPANDANGANUNDANGUTAMA  [ID : "+ID_PANDANGANUNDANGUTAMA+"] Updated");
				
				
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
	 
	 
	 public String saveInsertSubDir(HttpSession session,String ID_REFER,String ID_PANDANGANUNDANG,String ID_PANDANGANUNDANGUTAMA,String TAJUK,String LAYER) throws Exception {
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
				long id_p = DB.getNextID("TBLPDTPANDANGANUNDANG_SEQ");	 
				r.add("ID_PANDANGANUNDANG", id_p);
				r.add("ID_PANDANGANUNDANGUTAMA", ID_PANDANGANUNDANGUTAMA);
				r.add("ID_REFER", ID_REFER);
				r.add("LAYER", LAYER);				
				r.add("TAJUK", TAJUK.toUpperCase());				
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));				
				sql = r.getSQLInsert("TBLPDTPANDANGANUNDANG");		
				myLogger.info("V3 : INSERT TBLPDTPANDANGANUNDANG : "+sql);					
				stmt.executeUpdate(sql);
				conn.commit();
				AuditTrail.logActivity(this,session,"INS","TBLPDTPANDANGANUNDANG  [ID : "+ID_PANDANGANUNDANG+"] Inserted");
				
				
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
	 
	 public void deleteMainFolder(HttpSession session,String ID_PANDANGANUNDANGUTAMA) throws Exception {
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
				r.add("ID_PANDANGANUNDANGUTAMA",ID_PANDANGANUNDANGUTAMA);
				sql = r.getSQLDelete("TBLPDTPANDANGANUNDANG");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTPANDANGANUNDANG [ID_PANDANGANUNDANGUTAMA : "+ID_PANDANGANUNDANGUTAMA+"] Deleted");
			
				r.clear();
				r.add("ID_PANDANGANUNDANGUTAMA",ID_PANDANGANUNDANGUTAMA);
				sql = r.getSQLDelete("TBLPDTPANDANGANLAMPIRAN");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTPANDANGANLAMPIRAN [ID_PANDANGANUNDANGUTAMA : "+ID_PANDANGANUNDANGUTAMA+"] Deleted");
			
					
				r.clear();
				r.add("ID_PANDANGANUNDANGUTAMA",ID_PANDANGANUNDANGUTAMA);
				sql = r.getSQLDelete("TBLPDTPANDANGANUNDANGUTAMA");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","TBLPDTPANDANGANUNDANGUTAMA [ID_PANDANGANUNDANGUTAMA : "+ID_PANDANGANUNDANGUTAMA+"] Deleted");
			
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
				
				sql = " SELECT T.ID_PANDANGANUNDANG, T.ID_PANDANGANUNDANGUTAMA, T.TAJUK, "+
						" T.LAYER, T.ID_REFER, T.ID_MASUK,  "+
						" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
						" FROM TBLPDTPANDANGANUNDANG T " +
						" WHERE T.ID_REFER = '"+ID_REFER+"' "; 				
				myLogger.info(" SQL : listSubByID_REFER :"+ sql);			
				rs = stmt.executeQuery(sql);
				listSubByID_REFER = Collections.synchronizedList(new ArrayList());
				Map h = null;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					h.put("ID_PANDANGANUNDANG",rs.getString("ID_PANDANGANUNDANG") == null ? "" : rs.getString("ID_PANDANGANUNDANG"));			
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
