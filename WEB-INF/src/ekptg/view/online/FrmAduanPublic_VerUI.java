package ekptg.view.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
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

public class FrmAduanPublic_VerUI extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmAduanPublic_VerUI.class);
	String skrin_name = "app/online/AduanPublic/index.jsp";
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		
		List list = null;
		Map view = null;
		Map getDetailsUser = null;	
		Map viewBaru = null;
		List listNegeri = null;
		List listNegeriBahagian = null;
		List listPegawaiBahagian = null;
		List listPengawaiIkutJawatan = null;
		List listNegeriPT 	= null;
		List listDaerahPT = null;
		List listPejabatPT = null;
		List listUnit = null;
		List listJenisAduan = null;
		List listDaerah = null;
		List listKategoriAduan = null;
		List listKATEGORIPERTANYAAN = null;
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
		
		defaultPut();
		
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		myLogger.info("ADUAN command : "+command);
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
		
		if(command.equals("showSenarai_Print"))
		{			
			list = listAduanPublic(session,"");
			this.context.put("list",list);
			this.context.put("listLog_forPrint",list);			
			skrin_name = "app/online/AduanPublic/SenaraiPrint.jsp";
		}
		else if(command.equals("showSenarai") || command.equals("delete"))
		{			
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(command.equals("delete"))
			{
				//function delete
				delete(session,ID_ADUANPUBLIC);
			}
			
			
			
			this.context.put("FLAG_CARIAN", getParam("FLAG_CARIAN"));
			this.context.put("OPENCLOSE_CARIAN",getParam("OPENCLOSE_CARIAN"));
			String action = getParam("action");
			
			String FLAG_NOTIFIKASI = getParam("FLAG_NOTIFIKASI");
			/*
			if(FLAG_NOTIFIKASI.equals("Y"))
			{						
			}
			*/
			this.context.put("FLAG_NOTIFIKASI", FLAG_NOTIFIKASI);
			list = listAduanPublic(session,FLAG_NOTIFIKASI);
			
			setupPageList(session, action, list, "list",command,"div_Senarai");
			
			skrin_name = "app/online/AduanPublic/Senarai.jsp";
		}		
		else if(command.equals("showDaerah") || command.equals("showDaerahCR"))
		{
			String ID_NEGERI = getParam("ID_NEGERI");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			listDaerah = listDaerah(session,ID_NEGERI,null);
			this.context.put("listDaerah", listDaerah);
			if(command.equals("showDaerahCR"))
			{
				skrin_name = "app/online/AduanPublic/listDaerahCR.jsp";
			}
			else
			{
				skrin_name = "app/online/AduanPublic/listDaerah.jsp";
			}
		}
		
		
		else if(command.equals("showListNotifikasi"))
		{
			listNotifikasi = listNotifikasi(session,USER_ID_SYSTEM,"", null);
			this.context.put("listNotifikasi", listNotifikasi);
			this.context.put("FLAG_NOTIFIKASI", getParam("FLAG_NOTIFIKASI"));		
			skrin_name = "app/online/AduanPublic/listNotifikasi.jsp";
		}		
		else if(command.equals("showKategoriAduan"))
		{
			String ID_JENISADUAN = getParam("ID_JENISADUAN");
			if(ID_JENISADUAN.equals("16101"))
			{
				listKategoriAduan = listKategoriAduan(session,null);
			}
			this.context.put("ID_JENISADUAN", ID_JENISADUAN);
			this.context.put("listKategoriAduan", listKategoriAduan);
			skrin_name = "app/online/AduanPublic/listKategoriAduan.jsp";
		}
		
		else if(command.equals("showKronologi"))
		{
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			Db db = null;
			try {
				db = new Db();	
				String current_status = getCurrentStatus(session, ID_ADUANPUBLIC,db);
				this.context.put("ID_STATUS", current_status);
				if(!current_status.equals("") && !current_status.equals("16125") && !current_status.equals("16124"))
				{
					listKronologi = listKronologi(session, ID_ADUANPUBLIC, db);
				}
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}			
			this.context.put("listKronologi", listKronologi);
			skrin_name = "app/online/AduanPublic/Kronologi.jsp";
		}
		
		else if(command.equals("showStatusSkrinUI"))
		{
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");			
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");	
			String ID_SUMBERTINDAKAN = getParam("ID_SUMBERTINDAKAN");		
			String current_status = "";
			Db db = null;
			try {
				db = new Db();	
				current_status = getCurrentStatus(session, ID_ADUANPUBLIC,db);
			    listStatus = listStatus(session,db,"form",current_status,ID_JENISTINDAKAN,ID_SUMBERTINDAKAN);	
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("ID_STATUS", current_status);
			this.context.put("ID_SUMBERTINDAKAN", ID_SUMBERTINDAKAN);
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listStatus", listStatus);
			skrin_name = "app/online/AduanPublic/listStatusSkrinUI.jsp";
		}
		else if(command.equals("showSumberBahagian"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(!ID_JENISTINDAKAN.equals("") && !ID_JENISTINDAKAN.equals("16176"))
			{
				listJenisSumberBahagian = listJenisSumber(session,null,ID_JENISTINDAKAN);
			}
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listJenisSumberBahagian", listJenisSumberBahagian);
			skrin_name = "app/online/AduanPublic/listSumberBahagian.jsp";
		}
		else if(command.equals("showNegeriBahagian"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(ID_JENISTINDAKAN.equals("16172") || ID_JENISTINDAKAN.equals("16171"))
			{
				listNegeriBahagian = listNegeri(session,null,ID_JENISTINDAKAN);
			}
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listNegeriBahagian", listNegeriBahagian);
			skrin_name = "app/online/AduanPublic/listNegeriBahagian.jsp";
		}
		else if(command.equals("showNegeriPT"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(ID_JENISTINDAKAN.equals("16173") || ID_JENISTINDAKAN.equals("16174"))
			{
				listNegeriPT = listNegeri(session,null,ID_JENISTINDAKAN);
			}
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listNegeriPT", listNegeriPT);
			skrin_name = "app/online/AduanPublic/listNegeriPT.jsp";
		}
		else if(command.equals("showDaerahPT"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			String ID_NEGERI = getParam("ID_NEGERI");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(ID_JENISTINDAKAN.equals("16174"))
			{
				listDaerahPT = listDaerah(session,ID_NEGERI,null);
			}
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_NEGERI", ID_NEGERI);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listDaerahPT", listDaerahPT);
			skrin_name = "app/online/AduanPublic/listDaerahPT.jsp";
		}
		else if(command.equals("showPejabatPT"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			String ID_NEGERI = getParam("ID_NEGERI");
			String ID_DAERAH = getParam("ID_DAERAH");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(ID_JENISTINDAKAN.equals("16174") || ID_JENISTINDAKAN.equals("16173"))
			{
				listPejabatPT = listPejabatPT(session,ID_NEGERI,ID_DAERAH,ID_JENISTINDAKAN,null);
			}
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_NEGERI", ID_NEGERI);
			this.context.put("ID_DAERAH", ID_DAERAH);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listPejabatPT", listPejabatPT);
			skrin_name = "app/online/AduanPublic/listPejabatPT.jsp";
		}
		else if(command.equals("showBahagian") || command.equals("showBahagianCR"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			if(ID_JENISTINDAKAN.equals("16171") || ID_JENISTINDAKAN.equals("16172"))
			{
				listBahagian = listBahagian(session,null);
			}
			this.context.put("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("listBahagian", listBahagian);
			
			
			if(command.equals("showBahagianCR"))
			{
				skrin_name = "app/online/AduanPublic/listBahagianCR.jsp";
			}
			else
			{
				skrin_name = "app/online/AduanPublic/listBahagian.jsp";
			}		
		}
		else if(command.equals("showNegeri"))
		{
			String ID_JENISADUAN = getParam("ID_JENISADUAN");
			if(ID_JENISADUAN.equals("16101"))
			{
				listNegeri = listNegeri(session,null);
			}
			this.context.put("listNegeri", listNegeri);
			skrin_name = "app/online/AduanPublic/listNegeri.jsp";
		}
		else if(command.equals("showPegawaiBahagian"))
		{
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			String ID_SUMBERTINDAKAN = getParam("ID_SUMBERTINDAKAN");			
			String ID_BAHAGIAN = getParam("ID_BAHAGIAN");
			String ID_NEGERI = getParam("ID_NEGERI");
			String modeUI = getParam("modeUI");
			
			this.context.put("ID_SUMBERTINDAKAN", ID_SUMBERTINDAKAN);
			this.context.put("ID_BAHAGIAN", ID_BAHAGIAN);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("ID_NEGERI", ID_NEGERI);	
			this.context.put("modeUI", modeUI);	
			listPegawaiBahagian = listPegawaiBahagian(session,ID_BAHAGIAN,ID_NEGERI, null);
			this.context.put("listPegawaiBahagian", listPegawaiBahagian);
			myLogger.info(" showPegawaiBahagian ::: modeUI : "+modeUI+" ID_SUMBERTINDAKAN : "+ID_SUMBERTINDAKAN+" ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+" ID_BAHAGIAN : "+ID_BAHAGIAN+" ID_NEGERI : "+ID_NEGERI);
			skrin_name = "app/online/AduanPublic/SenaraiPegawai.jsp";
		}
		
		
		else if(command.equals("showNegeriBahagianCR"))
		{
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN");
			if(ID_JENISTINDAKAN.equals("16171") || ID_JENISTINDAKAN.equals("16172"))
			{
				listNegeriBahagian = listNegeri(session,null,ID_JENISTINDAKAN);
			}
			this.context.put("listNegeriBahagian", listNegeriBahagian);
			skrin_name = "app/online/AduanPublic/listNegeriBahagianCR.jsp";
		}
				
		else if(command.equals("add") || command.equals("view") || command.equals("edit") || command.equals("editRecreateLog") || command.equals("showContentAduan") || command.equals("saveDeraf") || command.equals("save") || command.equals("close"))
		{
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			String ID_ADUANPUBLICBARU = "";
			String ID_ADUANPUBLICASAL = "";
			if(command.equals("close"))
			{	
				ID_ADUANPUBLICBARU = getParam("ID_ADUANPUBLICBARU");
				if(commandFrom.equals("list"))
				{
					skrin_name = "app/online/AduanPublic/row.jsp";
				}
				else
				{
					skrin_name = "app/online/AduanPublic/blank_tr.jsp";	
				}							
			}
			else  if(command.equals("add") || command.equals("view") || command.equals("editRecreateLog") || command.equals("edit") || command.equals("saveDeraf") || command.equals("save"))
			{
				
				this.context.put("fromDashboard", getParam("fromDashboard"));
				skrin_name = "app/online/AduanPublic/edit.jsp";
				
			}
			else if(command.equals("showContentAduan"))
			{
				String ID_JENISADUAN = getParam("ID_JENISADUAN");
				myLogger.info(" ID_JENISADUAN : "+ID_JENISADUAN);
				if(!ID_JENISADUAN.equals(""))
				{
					if(ID_JENISADUAN.equals("16101"))
					{
						skrin_name = "app/online/AduanPublic/ContentAduan.jsp";
					}
					else
					{
						skrin_name = "app/online/AduanPublic/ContentCadangan.jsp";
					}
				}
				else
				{
					skrin_name = "app/online/AduanPublic/blank.jsp";
				}
				this.context.put("ID_JENISADUAN", ID_JENISADUAN);
			}
			
			
			Db db = null;
			try {
				db = new Db();				
				if(command.equals("saveDeraf") || command.equals("save"))
				{
					//update function
					if(ID_ADUANPUBLIC.equals(""))
					{
						this.context.put("flag_reset_list","yes");
					}
					ID_ADUANPUBLIC = saveAduan(session,ID_ADUANPUBLIC,command,db);
					//autosave before upload
					this.context.put("flagUpload", getParam("flagUpload"));
				}			
				
				String current_status = getCurrentStatus(session, ID_ADUANPUBLIC, db);
				if(command.equals("view"))
				{					
					if(USER_ROLE.equals("user_unit_intergriti") && (current_status.equals("16121")))
					{
						String new_ID_STATUS = "16122";
						if(checkStatusExist(session, ID_ADUANPUBLIC, new_ID_STATUS, db)==false && !new_ID_STATUS.equals(""))
						{
							saveKronologi(session,ID_ADUANPUBLIC,new_ID_STATUS,db);	
							current_status = new_ID_STATUS;
						}
					}
					else if(USER_ROLE.equals("wakil_bahagian_aduan") && (current_status.equals("16126")))
					{
						String new_ID_STATUS = "16127";
						if(checkStatusExist(session, ID_ADUANPUBLIC, new_ID_STATUS, db)==false && !new_ID_STATUS.equals(""))
						{
							saveKronologi(session,ID_ADUANPUBLIC,new_ID_STATUS,db);		
							current_status = new_ID_STATUS;
						}
					}
				}
				
				
				if(command.equals("editRecreateLog"))
				{
					//replicate
					ID_ADUANPUBLICASAL = ID_ADUANPUBLIC;
					String ID_ADUANPUBLICASAL_NEW  = replicateAduan(session,ID_ADUANPUBLICASAL,command,db);
					view = view(session, ID_ADUANPUBLICASAL_NEW,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
					
				}
				else
				{
					view = view(session, ID_ADUANPUBLIC,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);
				}
				
				
				if(!ID_ADUANPUBLICBARU.equals(""))
				{
					viewBaru = view(session,ID_ADUANPUBLICBARU,getParam("rowCss"),getParam("BIL"),selectedLanguage,db);					
				}
				
				
				//update unread tblnotifikasi
				String id_pengadu = (String)view.get("ID_PENGADU");
				if(
						(
						(current_status.equals("16121") && !id_pengadu.equals(USER_ID_SYSTEM) && !USER_ROLE.equals("user_unit_intergriti"))
						||
						(current_status.equals("16126") && !USER_ROLE.equals("user_unit_intergriti"))
						||  
						current_status.equals("16122") 
						|| 
						current_status.equals("16127")
						|| 
						current_status.equals("16123")
						)
						&& !current_status.equals("16125") && !current_status.equals("16124")
				  )
				{
					saveTableNotifikasi(session,ID_ADUANPUBLIC,"","",current_status,db,"update");
				}
				
				
				/*
				this.context.put("ID_STATUS", current_status);
				if(!current_status.equals("") && !current_status.equals("16125") && !current_status.equals("16124"))
				{
					listKronologi = listKronologi(session, ID_ADUANPUBLIC, db);
				}
				*/
				
				listNegeri = listNegeri(session,db);
				String id_negeri = (String)view.get("ID_NEGERI");
				if(!id_negeri.equals(""))
				{
					listDaerah = listDaerah(session,id_negeri,db);
				}
				listJenisAduan = listJenisAduan(session,db);
				listKATEGORIPERTANYAAN = listKATEGORIPERTANYAAN(session,db);
				listKategoriAduan = listKategoriAduan(session,db);
				listJam = listJam(session,db);
				listMinit = listMinit(session,db);
				listJenisSumberPengadu = listJenisSumber(session,db);
				String id_jenistindakan = (String)view.get("ID_JENISTINDAKAN");
				String id_sumbertindakan = (String)view.get("ID_SUMBERTINDAKAN");
				String ID_NEGERI_PT = (String)view.get("ID_NEGERI_PT");
				String ID_DAERAH_PT = (String)view.get("ID_DAERAH_PT");
				
				if(!id_jenistindakan.equals(""))
				{
					listJenisSumberBahagian = listJenisSumber(session,db,id_jenistindakan);
					listBahagian = listBahagian(session,db);
					if(id_jenistindakan.equals("16171") || id_jenistindakan.equals("16172"))
					{
						listNegeriBahagian = listNegeri(session,db,id_jenistindakan);
					}
					else if(id_jenistindakan.equals("16173") || id_jenistindakan.equals("16174"))
					{
						listNegeriPT = listNegeri(session,db,id_jenistindakan);
						listPejabatPT = listPejabatPT(session,ID_NEGERI_PT,ID_DAERAH_PT,id_jenistindakan,db);
						if(id_jenistindakan.equals("16174"))
						{
							
							listDaerahPT = listDaerah(session,ID_NEGERI_PT,db);
						}
					}					
				}				
				listStatus = listStatus(session,db,"form",current_status,id_jenistindakan,id_sumbertindakan);
				listJenisTindakan = listJenisTindakan(session,db);
				
			}
			/*catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}*/
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("view", view);	
			this.context.put("viewBaru", viewBaru);				
			this.context.put("listNegeriBahagian",listNegeriBahagian);
			this.context.put("listNegeriPT",listNegeriPT);
			this.context.put("listDaerahPT",listDaerahPT);	
			this.context.put("listPejabatPT",listPejabatPT);	
			this.context.put("listNegeri",listNegeri);
			this.context.put("listStatus",listNegeri);
			this.context.put("listDaerah",listDaerah);
			this.context.put("listJenisAduan",listJenisAduan);
			this.context.put("listKategoriAduan",listKategoriAduan);
			this.context.put("listKATEGORIPERTANYAAN",listKATEGORIPERTANYAAN);			
			this.context.put("listJenisSumberPengadu",listJenisSumberPengadu);
			this.context.put("listJenisSumberBahagian",listJenisSumberBahagian);	
			this.context.put("listJenisTindakan",listJenisTindakan);	
			this.context.put("listBahagian",listBahagian);			
			this.context.put("listJam",listJam);
			this.context.put("listMinit",listMinit);			
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			this.context.put("mode",getParam("mode"));
			this.context.put("listStatus",listStatus);
			this.context.put("listKronologi", listKronologi);
			this.context.put("ID_ADUANPUBLICASAL", ID_ADUANPUBLICASAL);	
			this.context.put("ID_ADUANPUBLICBARU", ID_ADUANPUBLICBARU);				
						
		}		
		else if(command.equals("showCARIAN"))
		{
			this.context.put("FLAG_CARIAN", getParam("FLAG_CARIAN"));
			this.context.put("OPENCLOSE_CARIAN",getParam("OPENCLOSE_CARIAN"));
			
			Db db = null;
			try {
				db = new Db();
				listJenisAduan = listJenisAduan(session,db);
				listJenisSumberPengadu = listJenisSumber(session,db);
				listJenisSumberBahagian = listJenisSumber(session,db,"");
				listStatus = listStatus(session,db);
				//listBahagian = listBahagian(session,db);
				listJenisTindakan = listJenisTindakan(session,db);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("listNegeri",listNegeri);
			this.context.put("listNegeriBahagian",listNegeriBahagian);
			this.context.put("listDaerah",listDaerah);
			this.context.put("listJenisAduan",listJenisAduan);
			this.context.put("listKategoriAduan",listKategoriAduan);
			this.context.put("listJenisSumberPengadu",listJenisSumberPengadu);
			this.context.put("listJenisSumberBahagian",listJenisSumberBahagian);
			this.context.put("listBahagian",listBahagian);
			this.context.put("listStatus",listStatus);
			this.context.put("listJenisTindakan",listJenisTindakan);
			
			skrin_name = "app/online/AduanPublic/skrinCarian.jsp";
		}
		else if(command.equals("saveLampiran"))
		{						
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");		
			String ID_STATUS = getParam("ID_STATUS");
			String ID_PENGADU = getParam("ID_PENGADU");	
			String ID_PEGAWAI_UI = getParam("ID_PEGAWAI_UI");	
			String ID_PEGAWAI_BAHAGIAN = getParam("ID_PEGAWAI_BAHAGIAN");	
			String docType = getParam("docType");	
			Db db = null;
			try {
				db = new Db();
				saveLampiran(session,ID_ADUANPUBLIC,docType,db);
			}
			catch (Exception ex) {
			throw new DbException(ex.getMessage());
			}
			finally {
				if (db != null)
					db.close();
			}
			this.context.put("docType",docType);
			this.context.put("ID_ADUANPUBLIC_AFTERUPLOAD",ID_ADUANPUBLIC);
			this.context.put("ID_STATUS",ID_STATUS);
			this.context.put("ID_PENGADU",ID_PENGADU);
			this.context.put("ID_PEGAWAI_UI",ID_PEGAWAI_UI);
			this.context.put("ID_PEGAWAI_BAHAGIAN",ID_PEGAWAI_BAHAGIAN);
			this.context.put("mode",getParam("mode"));
			this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");			
			skrin_name = "app/online/AduanPublic/index.jsp";			
		}
		else if(command.equals("showLampiran") || command.equals("deleteLampiran"))
		{
			String ID_ADUANPUBLIC = getParam("ID_ADUANPUBLIC");
			String ID_STATUS = getParam("ID_STATUS");
			String ID_PENGADU = getParam("ID_PENGADU");
			String ID_PEGAWAI_UI = getParam("ID_PEGAWAI_UI");
			String ID_PEGAWAI_BAHAGIAN = getParam("ID_PEGAWAI_BAHAGIAN");
			String docType = getParam("docType");
					
			if(command.equals("deleteLampiran"))
			{
				//delete function
				String ID_ADUANPUBLICLAMPIRAN = getParam("ID_ADUANPUBLICLAMPIRAN");
				String NAMA_LAMPIRAN = getParam("NAMA_LAMPIRAN");
				deleteLampiran(session,ID_ADUANPUBLICLAMPIRAN,NAMA_LAMPIRAN);
			}			
			listLampiran = listLampiran(session, ID_ADUANPUBLIC,docType, null);
			this.context.put("listLampiran", listLampiran);
			this.context.put("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			this.context.put("ID_STATUS", ID_STATUS);
			this.context.put("ID_PENGADU", ID_PENGADU);
			this.context.put("ID_PEGAWAI_UI", ID_PEGAWAI_UI);
			this.context.put("ID_PEGAWAI_BAHAGIAN", ID_PEGAWAI_BAHAGIAN);
			this.context.put("docType", docType);
			this.context.put("mode",getParam("mode"));
			skrin_name = "app/online/AduanPublic/SenaraiLampiran.jsp";				
		}
		else
		{
			
		}
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
		
		
		
		myLogger.info(" Aduan Public - VM :"+skrin_name);
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
		this.context.put("listNegeriBahagian","");	
		this.context.put("listNegeriPT","");	
		this.context.put("listDaerahPT","");
		this.context.put("listPejabatPT","");		
		this.context.put("listJenisAduan","");
		this.context.put("listDaerah","");	
		this.context.put("listKategoriAduan","");
		this.context.put("listJam","");
		this.context.put("listMinit","");
		this.context.put("ID_ADUANPUBLIC","");
		this.context.put("flag_reset_list","");
		this.context.put("listJenisSumberPengadu","");
		this.context.put("listJenisSumberBahagian","");
		this.context.put("listBahagian","");
		this.context.put("listStatus","");		
		this.context.put("commandFrom","");
		this.context.put("ID_JENISADUAN","");
		this.context.put("ID_ADUANPUBLIC_AFTERUPLOAD","");
		this.context.put("COOR_UPLOAD","");
		this.context.put("after_uploadLampiran","");
		this.context.put("listLampiran","");
		this.context.put("ID_STATUS", "");
		this.context.put("ID_PENGADU", "");
		this.context.put("modeUI", "");
		this.context.put("listJenisTindakan", "");
		this.context.put("flagUpload", "");
		this.context.put("listStatusUI", "");
		this.context.put("listStatusBahagian", "");
		this.context.put("listKronologi", "");
		this.context.put("OVERALL_TIME_TAKEN", "");
		this.context.put("ov_show_day", 0);
   		this.context.put("ov_show_hour", 0);
   		this.context.put("ov_show_minute", 0);
   		this.context.put("ov_show_second", 0);
   	    this.context.put("lt_show_day", 0);
   		this.context.put("lt_show_hour", 0);
   		this.context.put("lt_show_minute", 0);
   		this.context.put("lt_show_second", 0);
   		this.context.put("listNotifikasi", "");  
   		this.context.put("viewBaru", "");  
   		this.context.put("ID_JENISADUAN", "");
	}
	
	public String saveAduan(HttpSession session,String ID_ADUANPUBLIC,String command,Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		String sql = "";
		long id = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String new_ID_STATUS = "";
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
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String ID_STATUS = getParam("ID_STATUS"+"_"+ID_ADUANPUBLIC);
			String NO_ADUAN = getParam("NO_ADUAN"+"_"+ID_ADUANPUBLIC);
			String ID_JENISADUAN = getParam("ID_JENISADUAN"+"_"+ID_ADUANPUBLIC);
			String FLAG_HIDE_INFO = getParam("FLAG_HIDE_INFO"+"_"+ID_ADUANPUBLIC);
			String ID_SUMBERADUAN = getParam("ID_SUMBERADUAN"+"_"+ID_ADUANPUBLIC);
			String ID_PENGADU = getParam("ID_PENGADU"+"_"+ID_ADUANPUBLIC);
			
			String NAMA_PENGADU = getParam("NAMA_PENGADU"+"_"+ID_ADUANPUBLIC);
			String TEL_PENGADU = getParam("TEL_PENGADU"+"_"+ID_ADUANPUBLIC);
			String EMEL_PENGADU = getParam("EMEL_PENGADU"+"_"+ID_ADUANPUBLIC);
			
			String ID_KATEGORIADUAN = getParam("ID_KATEGORIADUAN"+"_"+ID_ADUANPUBLIC);
			String ID_KATEGORIPERTANYAAN = getParam("ID_KATEGORIPERTANYAAN"+"_"+ID_ADUANPUBLIC);
			String SUBJEK_ADUAN = getParam("SUBJEK_ADUAN"+"_"+ID_ADUANPUBLIC);
			String TARIKH_KEJADIAN = getParam("TARIKH_KEJADIAN"+"_"+ID_ADUANPUBLIC);
			
			
			
			if(!TARIKH_KEJADIAN.equals(""))
			{
				TARIKH_KEJADIAN = "to_date('" +TARIKH_KEJADIAN+ "','dd/MM/yyyy')";
			}
			else
			{
				TARIKH_KEJADIAN = "''";
			}
			
			String JAM = getParam("JAM"+"_"+ID_ADUANPUBLIC);
			String MINIT = getParam("MINIT"+"_"+ID_ADUANPUBLIC);
			String JENIS_WAKTU = getParam("JENIS_WAKTU"+"_"+ID_ADUANPUBLIC);
			String LOKASI_KEJADIAN = getParam("LOKASI_KEJADIAN"+"_"+ID_ADUANPUBLIC);
			String ID_NEGERI = getParam("ID_NEGERI"+"_"+ID_ADUANPUBLIC);
			String ID_DAERAH = getParam("ID_DAERAH"+"_"+ID_ADUANPUBLIC);
			String NAMA_PIHAK_TERLIBAT = getParam("NAMA_PIHAK_TERLIBAT"+"_"+ID_ADUANPUBLIC);			
			String KETERANGAN_ADUAN = getParam("KETERANGAN_ADUAN"+"_"+ID_ADUANPUBLIC);			
			
			
			String ID_JENISTINDAKAN = getParam("ID_JENISTINDAKAN"+"_"+ID_ADUANPUBLIC);
			String ID_BAHAGIAN = getParam("ID_BAHAGIAN"+"_"+ID_ADUANPUBLIC);
			String ID_NEGERI_BAHAGIAN = getParam("ID_NEGERI_BAHAGIAN"+"_"+ID_ADUANPUBLIC);			
			String ID_SUMBERTINDAKAN = getParam("ID_SUMBERTINDAKAN"+"_"+ID_ADUANPUBLIC);
			String MAKLUMBALAS_UI = getParam("MAKLUMBALAS_UI"+"_"+ID_ADUANPUBLIC);
			String ID_STATUS_UI = getParam("ID_STATUS_UI"+"_"+ID_ADUANPUBLIC);
			
			String EMEL_BAHAGIAN_1 = getParam("EMEL_BAHAGIAN_1"+"_"+ID_ADUANPUBLIC);
			String EMEL_BAHAGIAN_2 = getParam("EMEL_BAHAGIAN_2"+"_"+ID_ADUANPUBLIC);
			String EMEL_BAHAGIAN_3 = getParam("EMEL_BAHAGIAN_3"+"_"+ID_ADUANPUBLIC);
			String EMEL_BAHAGIAN_4 = getParam("EMEL_BAHAGIAN_4"+"_"+ID_ADUANPUBLIC);
			String FLAG_LUAR_TINDAKAN = getParam("FLAG_LUAR_TINDAKAN"+"_"+ID_ADUANPUBLIC);
			
			String ID_NEGERI_PT = getParam("ID_NEGERI_PT"+"_"+ID_ADUANPUBLIC);
			String ID_DAERAH_PT = getParam("ID_DAERAH_PT"+"_"+ID_ADUANPUBLIC);
			String ID_PEJABATTANAH = getParam("ID_PEJABATTANAH"+"_"+ID_ADUANPUBLIC);
			
			String MAKLUMBALAS_BAHAGIAN = getParam("MAKLUMBALAS_BAHAGIAN"+"_"+ID_ADUANPUBLIC);
			String ID_STATUS_BAHAGIAN = getParam("ID_STATUS_BAHAGIAN"+"_"+ID_ADUANPUBLIC);
			
			if(!ID_ADUANPUBLIC.equals(""))
			{
				r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
			}
			else
			{
				id = DB.getNextID(db1, "TBLADUANPUBLIC_SEQ");
				r.add("ID_ADUANPUBLIC", id);
			}
			

			if(command.equals("save"))
			{
				if(NO_ADUAN.equals(""))//hantar log
				{
					Date now = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					String tahun = formatter.format(now);
					SimpleDateFormat formatter_month = new SimpleDateFormat("MM");
					String bulan  = formatter_month.format(now);
					String NO_RUJUKAN_ADUAN = ""+tahun+"/"+bulan+"/"+ String.format("%06d", getSeqNo(tahun,bulan,db));
					r.add("NO_ADUAN", NO_RUJUKAN_ADUAN);
					r.add("TARIKH_ADUAN", r.unquote("sysdate"));
					//r.add("ID_STATUS",16121); //LOG BARU
					new_ID_STATUS = "16121";
					myLogger.info("**** save new_ID_STATUS : "+new_ID_STATUS);
				}				
			}
			else if(command.equals("saveDeraf"))
			{
				if(ID_ADUANPUBLIC.equals(""))
				{
					//r.add("ID_STATUS",16125); //DERAF
					new_ID_STATUS = "16125";
					myLogger.info("**** saveDeraf new_ID_STATUS : "+new_ID_STATUS);
				}
			}
			
			
			if(ID_STATUS.equals("16125") || ID_STATUS.equals(""))
			{
				r.add("ID_JENISADUAN", ID_JENISADUAN);
				r.add("FLAG_HIDE_INFO", FLAG_HIDE_INFO);			
				if(ID_JENISADUAN.equals("16101"))
				{
					r.add("ID_KATEGORIADUAN", ID_KATEGORIADUAN);
					r.add("SUBJEK_ADUAN", SUBJEK_ADUAN.toUpperCase());
					r.add("TARIKH_KEJADIAN", r.unquote(TARIKH_KEJADIAN));
					r.add("JAM", JAM);
					r.add("MINIT", MINIT);
					r.add("JENIS_WAKTU", JENIS_WAKTU);
					r.add("LOKASI_KEJADIAN", LOKASI_KEJADIAN.toUpperCase());
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("ID_DAERAH", ID_DAERAH);
					r.add("NAMA_PIHAK_TERLIBAT", NAMA_PIHAK_TERLIBAT.toUpperCase());
					r.add("ID_KATEGORIPERTANYAAN", "");
				}				
				else
				{
					if(ID_JENISADUAN.equals("161021"))
					{
						r.add("ID_KATEGORIPERTANYAAN", ID_KATEGORIPERTANYAAN);
					}
					else
					{
						r.add("ID_KATEGORIPERTANYAAN", "");
					}
					
					
					r.add("ID_KATEGORIADUAN", "");
					r.add("SUBJEK_ADUAN", "");
					r.add("TARIKH_KEJADIAN", "");
					r.add("JAM", "");
					r.add("MINIT", "");
					r.add("JENIS_WAKTU", "");
					r.add("LOKASI_KEJADIAN", "");
					r.add("ID_NEGERI", "");
					r.add("ID_DAERAH", "");
					r.add("NAMA_PIHAK_TERLIBAT", "");	
					
				}
				r.add("KETERANGAN_ADUAN", KETERANGAN_ADUAN.toUpperCase());
				r.add("ID_SUMBERADUAN", ID_SUMBERADUAN);			
				if(ID_SUMBERADUAN.equals("16101"))
				{
					r.add("ID_PENGADU", ID_PENGADU);
				}
				else 
				{
					r.add("ID_PENGADU", "");
					r.add("NAMA_PENGADU", NAMA_PENGADU);
					r.add("TEL_PENGADU", TEL_PENGADU);
					r.add("EMEL_PENGADU", EMEL_PENGADU);
				}
			}
			else 
			{			
				if(ID_STATUS_UI.equals("16122") || ID_STATUS_UI.equals("16123") || ID_STATUS_UI.equals("16126"))
				{
					r.add("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
					r.add("ID_BAHAGIAN", ID_BAHAGIAN);
					r.add("ID_NEGERI_BAHAGIAN", ID_NEGERI_BAHAGIAN);
					r.add("ID_SUMBERTINDAKAN", ID_SUMBERTINDAKAN);
					r.add("MAKLUMBALAS_UI", MAKLUMBALAS_UI);
					
					r.add("ID_NEGERI_PT", ID_NEGERI_PT);
					r.add("ID_DAERAH_PT", ID_DAERAH_PT);
					r.add("ID_PEJABATTANAH", ID_PEJABATTANAH);
					
					r.add("EMEL_BAHAGIAN_1", EMEL_BAHAGIAN_1);
					r.add("EMEL_BAHAGIAN_2", EMEL_BAHAGIAN_2);
					r.add("EMEL_BAHAGIAN_3", EMEL_BAHAGIAN_3);
					r.add("EMEL_BAHAGIAN_4", EMEL_BAHAGIAN_4);
					myLogger.info(" FLAG_LUAR_TINDAKAN ::: "+FLAG_LUAR_TINDAKAN);
					r.add("FLAG_LUAR_TINDAKAN", FLAG_LUAR_TINDAKAN);
					
					if(!ID_STATUS_UI.equals(ID_STATUS))
					{
						new_ID_STATUS = ID_STATUS_UI;
					}
				}	
				else if(ID_STATUS_UI.equals("16127") || ID_STATUS_UI.equals("16123"))
				{
					r.add("MAKLUMBALAS_BAHAGIAN", MAKLUMBALAS_BAHAGIAN);					
					if(!ID_STATUS_BAHAGIAN.equals(ID_STATUS))
					{
						new_ID_STATUS = ID_STATUS_BAHAGIAN;
					}
				}
			}
			
			if(!ID_ADUANPUBLIC.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLADUANPUBLIC");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLADUANPUBLIC");	
			}
			myLogger.info("ADUAN : SAVE  : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			if(!ID_ADUANPUBLIC.equals(""))
			{
				//hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				AuditTrail.logActivity(this,session,"UPD","TBLADUANPUBLIC [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+"] Updated");
				
			}
			else
			{
				//hantarEmel(session,id+"");
				AuditTrail.logActivity(this,session,"INS","TBLADUANPUBLIC [ID_ADUANPUBLIC : "+id+"] Inserted");
				ID_ADUANPUBLIC = id+"";
			}
			
			
			//new_ID_STATUS
			if(checkStatusExist(session, ID_ADUANPUBLIC, new_ID_STATUS, db1)==false && !new_ID_STATUS.equals(""))
			{
				
				if(getParam("FLAG_SEMENTARA").equals("Y"))
				{
					//xxx
					//update no fail seq yang baru
					//update flag_sementara dari 'Y' to  ''					
					Date now = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					SimpleDateFormat formatter_month = new SimpleDateFormat("MM");
					String tahun = formatter.format(now);
					String bulan  = formatter_month.format(now);
					String NO_RUJUKAN_ADUAN = ""+tahun+"/"+ bulan+"/"+String.format("%06d", getSeqNo(tahun,bulan,db));						
					r.clear();
					r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
					r.add("FLAG_SEMENTARA", "");
					r.add("NO_ADUAN", NO_RUJUKAN_ADUAN);
					sql = r.getSQLUpdate("TBLADUANPUBLIC");
					stmt.executeUpdate(sql);
				}
				myLogger.info("**** 1st new_ID_STATUS : "+new_ID_STATUS);
				saveKronologi(session,ID_ADUANPUBLIC,new_ID_STATUS,db1);
				if(new_ID_STATUS.equals("16121") && !ID_SUMBERADUAN.equals("16101"))
				{
					//kalo daftar aduan secara manuall.. kena add status dalam tindakan terus
					new_ID_STATUS = "16122";
					myLogger.info("**** 2nd new_ID_STATUS : "+new_ID_STATUS);
					//myLogger.info("**** checkStatusExist new_ID_STATUS : "+new_ID_STATUS);
					saveKronologi(session,ID_ADUANPUBLIC,new_ID_STATUS,db1);
				}
			}
			
			
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
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return ID_ADUANPUBLIC;
	}
	
	
	
	public String replicateAduan(HttpSession session,String ID_ADUANPUBLICASAL,String command,Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		String sql = "";
		long ID_ADUANPUBLIC_NEW = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String new_ID_STATUS = "";
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
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			ID_ADUANPUBLIC_NEW = DB.getNextID(db1, "TBLADUANPUBLIC_SEQ");
						
			sql = " INSERT INTO TBLADUANPUBLIC ( "+
					" ID_ADUANPUBLIC, ID_STATUS,  NO_ADUAN,  ID_KATEGORIPERTANYAAN, "+
					" ID_SUMBERADUAN, ID_PENGADU, ID_PEGAWAI_UI, "+ 
					//" ID_PEGAWAI_BAHAGIAN, " +
					" TARIKH_ADUAN, TARIKH_TERIMA_ADUAN_UI,  "+
					//" TARIKH_TERIMA_ADUAN_BAHAGIAN, " +
					//" TARIKH_ADUAN_SELESAI, " +
					" NAMA_PENGADU,  "+
					" EMEL_PENGADU, TEL_PENGADU, ID_JENISADUAN,  "+
					" ID_KATEGORIADUAN, JAM, MINIT,  "+
					" SUBJEK_ADUAN, TARIKH_KEJADIAN, LOKASI_KEJADIAN,  "+
					" ID_NEGERI, ID_DAERAH, NAMA_PIHAK_TERLIBAT,  "+
					" KETERANGAN_ADUAN, " +
					/*" ID_JENISTINDAKAN, ID_BAHAGIAN,  "+
					" ID_SUMBERTINDAKAN, NAMA_PEGAWAI_BAHAGIAN, EMEL_PEGAWAI_BAHAGIAN,  "+
					" TEL_PEGAWAI_BAHAGIAN, " +*/
					"ID_MASUK, ID_KEMASKINI,  "+
					" TARIKH_MASUK, TARIKH_KEMASKINI, JENIS_WAKTU,  "+
					" FLAG_HIDE_INFO, " +
					//" MAKLUMBALAS_UI, MAKLUMBALAS_BAHAGIAN,  "+
					" ID_NEGERI_BAHAGIAN,FLAG_SEMENTARA)  "+
					" SELECT  "+
					" '"+ID_ADUANPUBLIC_NEW+"', " +
							//" T.ID_STATUS, " +
							" '16122', "+ // 'DALAM TINDAKAN UNIT INTERGRITI'
							" T.NO_ADUAN || '_TEMP"+ID_ADUANPUBLIC_NEW+"', T.ID_KATEGORIPERTANYAAN, "+
					//ID_ADUANPUBLIC_NEW
					" T.ID_SUMBERADUAN, T.ID_PENGADU, T.ID_PEGAWAI_UI,  "+
					//" T.ID_PEGAWAI_BAHAGIAN, " +
					" T.TARIKH_ADUAN, T.TARIKH_TERIMA_ADUAN_UI,  "+
					//" T.TARIKH_TERIMA_ADUAN_BAHAGIAN, " +
					//" T.TARIKH_ADUAN_SELESAI, " +
					" T.NAMA_PENGADU,  "+
					" T.EMEL_PENGADU, T.TEL_PENGADU, T.ID_JENISADUAN,  "+
					" T.ID_KATEGORIADUAN, T.JAM, T.MINIT,  "+
					" T.SUBJEK_ADUAN, T.TARIKH_KEJADIAN, T.LOKASI_KEJADIAN,  "+
					" T.ID_NEGERI, T.ID_DAERAH, T.NAMA_PIHAK_TERLIBAT,  "+
					" T.KETERANGAN_ADUAN, " +
					//" T.ID_JENISTINDAKAN, " +
					//" T.ID_BAHAGIAN,  "+
					//" T.ID_SUMBERTINDAKAN, " +
					//" T.NAMA_PEGAWAI_BAHAGIAN, T.EMEL_PEGAWAI_BAHAGIAN,  "+
					//" T.TEL_PEGAWAI_BAHAGIAN, " +
					" T.ID_MASUK, '"+USER_ID_SYSTEM+"',  "+
					" T.TARIKH_MASUK, SYSDATE, T.JENIS_WAKTU,  "+
					" T.FLAG_HIDE_INFO, " +
					//" T.MAKLUMBALAS_UI, " +
					//" T.MAKLUMBALAS_BAHAGIAN,  "+
					" T.ID_NEGERI_BAHAGIAN, 'Y' "+
					" FROM TBLADUANPUBLIC T WHERE T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLICASAL+"' ";
					myLogger.info("REPLICATE TBLADUANPUBLIC : "+sql);				
					stmt.executeUpdate(sql);


			sql = "	INSERT INTO TBLADUANPUBLICLAMPIRAN ( "+
					//"	ID_ADUANPUBLICLAMPIRAN, " +
					"	NAMA_LAMPIRAN, JENIS_MIME,  "+
					"	ID_ADUANPUBLIC, CONTENT, CONTENT_BASE64, ID_MASUK,  "+
					"	ID_KEMASKINI, TARIKH_MASUK, TARIKH_KEMASKINI)  "+
					"	SELECT  "+
					//"	T.ID_ADUANPUBLICLAMPIRAN, " +
					"	T.NAMA_LAMPIRAN, T.JENIS_MIME,  "+
					"	'"+ID_ADUANPUBLIC_NEW+"', T.CONTENT, T.CONTENT_BASE64, T.ID_MASUK,  "+
					"	T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
					"	FROM TBLADUANPUBLICLAMPIRAN T WHERE T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLICASAL+"'  ";
					myLogger.info("REPLICATE TBLADUANPUBLICLAMPIRAN : "+sql);				
					stmt.executeUpdate(sql);

			sql = "	INSERT INTO TBLKRONOLOGIADUANPUBLIC ( "+
					//"	ID_KRONOLOGI, " +
					"	ID_ADUANPUBLIC, ID_STATUS,   "+
					"	FLAG_AKTIF, ID_MASUK, TARIKH_MASUK)  "+
					"	SELECT  "+
					//"	T.ID_KRONOLOGI, " +
					"	'"+ID_ADUANPUBLIC_NEW+"', T.ID_STATUS,  "+
					"	T.FLAG_AKTIF, T.ID_MASUK, T.TARIKH_MASUK "+
					"	FROM TBLKRONOLOGIADUANPUBLIC T WHERE T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLICASAL+"'" +
							" AND T.ID_STATUS NOT IN (16123,16126,16127,16124)";
			
			
			
			
			
			
			
			
			
			
			
			//r.add("ID_ADUANPUBLIC", id);
			
			
			/*
			if(command.equals("save"))
			{
				if(NO_ADUAN.equals(""))//hantar log
				{
					Date now = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					String tahun = formatter.format(now);
					String NO_RUJUKAN_ADUAN = ""+tahun+"/"+ String.format("%06d", getSeqNo(tahun,db));
					r.add("NO_ADUAN", NO_RUJUKAN_ADUAN);
					r.add("TARIKH_ADUAN", r.unquote("sysdate"));
					//r.add("ID_STATUS",16121); //LOG BARU
					new_ID_STATUS = "16121";
					myLogger.info("**** save new_ID_STATUS : "+new_ID_STATUS);
				}				
			}
			else if(command.equals("saveDeraf"))
			{
				if(ID_ADUANPUBLIC.equals(""))
				{
					//r.add("ID_STATUS",16125); //DERAF
					new_ID_STATUS = "16125";
					myLogger.info("**** saveDeraf new_ID_STATUS : "+new_ID_STATUS);
				}
			}
			
			
			if(ID_STATUS.equals("16125") || ID_STATUS.equals(""))
			{
				r.add("ID_JENISADUAN", ID_JENISADUAN);
				r.add("FLAG_HIDE_INFO", FLAG_HIDE_INFO);			
				if(ID_JENISADUAN.equals("16101"))
				{
					r.add("ID_KATEGORIADUAN", ID_KATEGORIADUAN);
					r.add("SUBJEK_ADUAN", SUBJEK_ADUAN.toUpperCase());
					r.add("TARIKH_KEJADIAN", TARIKH_KEJADIAN);
					r.add("JAM", JAM);
					r.add("MINIT", MINIT);
					r.add("JENIS_WAKTU", JENIS_WAKTU);
					r.add("LOKASI_KEJADIAN", LOKASI_KEJADIAN.toUpperCase());
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("ID_DAERAH", ID_DAERAH);
					r.add("NAMA_PIHAK_TERLIBAT", NAMA_PIHAK_TERLIBAT.toUpperCase());				
				}
				else
				{
					r.add("ID_KATEGORIADUAN", "");
					r.add("SUBJEK_ADUAN", "");
					r.add("TARIKH_KEJADIAN", "");
					r.add("JAM", "");
					r.add("MINIT", "");
					r.add("JENIS_WAKTU", "");
					r.add("LOKASI_KEJADIAN", "");
					r.add("ID_NEGERI", "");
					r.add("ID_DAERAH", "");
					r.add("NAMA_PIHAK_TERLIBAT", "");				
				}
				r.add("KETERANGAN_ADUAN", KETERANGAN_ADUAN.toUpperCase());
				r.add("ID_SUMBERADUAN", ID_SUMBERADUAN);			
				if(ID_SUMBERADUAN.equals("16101"))
				{
					r.add("ID_PENGADU", ID_PENGADU);
				}
				else 
				{
					r.add("ID_PENGADU", "");
					r.add("NAMA_PENGADU", NAMA_PENGADU);
					r.add("TEL_PENGADU", TEL_PENGADU);
					r.add("EMEL_PENGADU", EMEL_PENGADU);
				}
			}
			else 
			{			
				if(ID_STATUS_UI.equals("16122") || ID_STATUS_UI.equals("16123") || ID_STATUS_UI.equals("16126"))
				{
					r.add("ID_JENISTINDAKAN", ID_JENISTINDAKAN);
					r.add("ID_BAHAGIAN", ID_BAHAGIAN);
					r.add("ID_NEGERI_BAHAGIAN", ID_NEGERI_BAHAGIAN);
					r.add("ID_SUMBERTINDAKAN", ID_SUMBERTINDAKAN);
					r.add("MAKLUMBALAS_UI", MAKLUMBALAS_UI);
					
					if(!ID_STATUS_UI.equals(ID_STATUS))
					{
						new_ID_STATUS = ID_STATUS_UI;
					}
				}	
				else if(ID_STATUS_UI.equals("16127") || ID_STATUS_UI.equals("16123"))
				{
					r.add("MAKLUMBALAS_BAHAGIAN", MAKLUMBALAS_BAHAGIAN);					
					if(!ID_STATUS_BAHAGIAN.equals(ID_STATUS))
					{
						new_ID_STATUS = ID_STATUS_BAHAGIAN;
					}
				}
			}
			
			
			if(!ID_ADUANPUBLIC.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLADUANPUBLIC");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLADUANPUBLIC");	
			}
			*/
			myLogger.info("ADUAN : SAVE  : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			
			
			AuditTrail.logActivity(this,session,"INS","TBLADUANPUBLIC [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC_NEW+"] Inserted",db1);
				
			
			//new_ID_STATUS
			/*
			if(checkStatusExist(session, ID_ADUANPUBLIC, new_ID_STATUS, db1)==false && !new_ID_STATUS.equals(""))
			{
				myLogger.info("**** 1st new_ID_STATUS : "+new_ID_STATUS);
				saveKronologi(session,ID_ADUANPUBLIC,new_ID_STATUS,db1);
				if(new_ID_STATUS.equals("16121") && !ID_SUMBERADUAN.equals("16101"))
				{
					//kalo daftar aduan secara manuall.. kena add status dalam tindakan terus
					new_ID_STATUS = "16122";
					myLogger.info("**** 2nd new_ID_STATUS : "+new_ID_STATUS);
					//myLogger.info("**** checkStatusExist new_ID_STATUS : "+new_ID_STATUS);
					saveKronologi(session,ID_ADUANPUBLIC,new_ID_STATUS,db1);
				}
			}
			*/
			
			
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
			if(db==null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return ID_ADUANPUBLIC_NEW+"";
	}
	
	
	public Map view(HttpSession session, String ID_ADUANPUBLIC,String rowCss,String bil,String selectedLanguage,Db db) throws Exception {
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
			if(!ID_ADUANPUBLIC.equals(""))
			{
				//bila ada data
				sql = sqlAduanPublic(session,"");
				sql += "  AND T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' ";
				myLogger.info(" SQL VIEW ADUAN :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {	
					h = getHashMapAduan(session,rs,rowCss,bil,selectedLanguage,db1);					
				}	
			}
			else
			{
				//bila 1st time insert, kosongkan object
				h = getHashMapAduan(session,null,rowCss,bil,selectedLanguage,db1);
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
	
	
	public String sqlAduanPublic(HttpSession session,String FLAG_NOTIFIKASI)
	{
		myLogger.info("FLAG_NOTIFIKASI : "+FLAG_NOTIFIKASI);
		myLogger.info("ID_TERIMA_NOTI : "+getParam("ID_TERIMA_NOTI"));
		myLogger.info("ID_STATUS_NOTI : "+getParam("ID_STATUS_NOTI"));
		
		String sql = " SELECT T.ID_PEJABATTANAH, T.ID_NEGERI_PT, T.ID_DAERAH_PT, " +
				" NEGERI_PT.NAMA_NEGERI AS NEGERI_PT, DAERAH_PT.NAMA_DAERAH AS DAERAH_PT, PEJ_PT.NAMA_PEJABAT AS PEJABAT_PT," +
				"T.FLAG_LUAR_TINDAKAN,T.EMEL_BAHAGIAN_1,T.EMEL_BAHAGIAN_2,T.EMEL_BAHAGIAN_3,T.EMEL_BAHAGIAN_4," +
				"T.FLAG_SEMENTARA,T.ID_MASUK, TO_CHAR(T.TARIKH_KEMASKINI,'DD/MM/YYYY HH:MI:SS AM') AS TARIKH_KEMASKINI, T.FLAG_HIDE_INFO,T.ID_ADUANPUBLIC, T.ID_STATUS, T.NO_ADUAN, T.ID_SUMBERADUAN, T.ID_PENGADU, T.ID_PEGAWAI_UI, "+
				" T.ID_PEGAWAI_BAHAGIAN, TO_CHAR(T.TARIKH_ADUAN,'DD/MM/YYYY') AS TARIKH_ADUAN, " +
				" TO_CHAR(T.TARIKH_TERIMA_ADUAN_UI,'DD/MM/YYYY') AS TARIKH_TERIMA_ADUAN_UI,  "+
				" TO_CHAR(T.TARIKH_TERIMA_ADUAN_BAHAGIAN,'DD/MM/YYYY') AS TARIKH_TERIMA_ADUAN_BAHAGIAN, TO_CHAR(T.TARIKH_ADUAN_SELESAI,'DD/MM/YYYY') AS TARIKH_ADUAN_SELESAI, " +				
				" (CASE WHEN T.ID_PENGADU IS NULL OR T.ID_PENGADU = '' THEN UPPER(T.NAMA_PENGADU) ELSE  (USERONLINE.NAMA_PENGADU) END) AS NAMA_PENGADU, " +
				" (CASE WHEN T.ID_PENGADU IS NULL OR T.ID_PENGADU = '' THEN (T.EMEL_PENGADU) ELSE (USERONLINE.EMEL) END) AS EMEL_PENGADU, " +
				" (CASE WHEN T.ID_PENGADU IS NULL OR T.ID_PENGADU = '' THEN UPPER(T.TEL_PENGADU) ELSE UPPER (USERONLINE.NO_HP ) END) AS TEL_PENGADU,   " +     
				" (CASE WHEN T.ID_PEGAWAI_BAHAGIAN IS NULL OR T.ID_PEGAWAI_BAHAGIAN = '' THEN UPPER(T.NAMA_PEGAWAI_BAHAGIAN) ELSE UPPER (USERBAHAGIAN.NAMA_PEGAWAI_BAHAGIAN) END) AS NAMA_PEGAWAI_BAHAGIAN, " +
				" (CASE WHEN T.ID_PEGAWAI_BAHAGIAN IS NULL OR T.ID_PEGAWAI_BAHAGIAN = '' THEN (T.EMEL_PEGAWAI_BAHAGIAN) ELSE (USERBAHAGIAN.EMEL) END) AS EMEL_PEGAWAI_BAHAGIAN, " +
				" (CASE WHEN T.ID_PEGAWAI_BAHAGIAN IS NULL OR T.ID_PEGAWAI_BAHAGIAN = '' THEN UPPER(T.TEL_PEGAWAI_BAHAGIAN) ELSE UPPER (USERBAHAGIAN.NO_TEL) END) AS TEL_PEGAWAI_BAHAGIAN,  " +   
				" T.ID_JENISADUAN,JA.JENIS_ADUAN, JA.JENIS_ADUAN_ENG,  "+
				" T.ID_KATEGORIADUAN, KAT.KATEGORIADUAN, KAT.KATEGORIADUAN_ENG, " +
				" T.ID_KATEGORIPERTANYAAN, KATP.KATEGORIPERTANYAAN, KATP.KATEGORIPERTANYAAN_ENG, " +
				" T.JAM, T.MINIT, T.JENIS_WAKTU, T.SUBJEK_ADUAN, " +
				" TO_CHAR(T.TARIKH_KEJADIAN,'DD/MM/YYYY') AS TARIKH_KEJADIAN, T.LOKASI_KEJADIAN, BAHAGIAN.NAMA_SEKSYEN AS NAMA_BAHAGIAN, "+
				" T.ID_NEGERI, T.ID_NEGERI_BAHAGIAN, T.ID_DAERAH, T.NAMA_PIHAK_TERLIBAT,  "+
				" T.KETERANGAN_ADUAN, T.MAKLUMBALAS_UI, T.MAKLUMBALAS_BAHAGIAN, " +
				" T.ID_JENISTINDAKAN,  "+
				" T.ID_BAHAGIAN, T.ID_SUMBERTINDAKAN, " +				
				" UPPER(UUI.USER_NAME) AS PEGAWAI_UI,UIUI.EMEL AS EMEL_PEGAWAI_UI, PEJUI.NO_TEL AS NO_TEL_PEGAWAI_UI, "+
				" UPPER(STA.KETERANGAN) AS STATUS, UPPER(STA.KETERANGAN_ENG) AS STATUS_ENG, "+
				" (SUM_PENGADU.NAMA_SUMBER) AS SUMBER_PENGADU,(SUM_PENGADU.NAMA_SUMBER_ENG) AS SUMBER_PENGADU_ENG, "+
				" (SUM_BAHAGIAN.NAMA_SUMBER) AS SUMBER_BAHAGIAN, (SUM_BAHAGIAN.NAMA_SUMBER_ENG) AS SUMBER_BAHAGIAN_ENG, "+
				" UPPER(KATTINDAKAN.KATEGORITINDAKAN) AS KATEGORITINDAKAN, UPPER(KATTINDAKAN.KATEGORITINDAKAN_ENG) AS KATEGORITINDAKAN_ENG, "+
				" UPPER(DAERAH.NAMA_DAERAH) AS DAERAH, UPPER(NEGERI.NAMA_NEGERI) AS NEGERI, UPPER(NEGERI_BAHAGIAN.NAMA_NEGERI) AS NEGERI_BAHAGIAN "+
				" FROM TBLADUANPUBLIC T, ";
				if(FLAG_NOTIFIKASI.equals("Y"))
				{
					sql += " TBLADUANPUBLICNOTIFIKASI NOTIF,";					
				}		
				sql += "USERS UUI,USERS_INTERNAL UIUI,TBLRUJJENISESADUAN JA, TBLRUJSTATUSADUANPUBLIC STA,TBLRUJSUMBERESADUAN SUM_PENGADU,TBLRUJSUMBERESADUAN SUM_BAHAGIAN,TBLRUJSEKSYEN BAHAGIAN, "+
				" TBLRUJKATEGORIADUANPUBLIC KAT,TBLRUJKATEGORIPERTANYAAN KATP,TBLRUJKATEGORITINDAKAN KATTINDAKAN, " +
				" TBLRUJNEGERI NEGERI, TBLRUJNEGERI NEGERI_BAHAGIAN, TBLRUJDAERAH DAERAH, "+
				" TBLRUJNEGERI NEGERI_PT, TBLRUJDAERAH DAERAH_PT, TBLRUJPEJABAT PEJ_PT, TBLRUJPEJABATJKPTG PEJUI,"+
				" (SELECT SU.USER_ID, UPPER(SU.USER_NAME) AS NAMA_PENGADU, SUO.NO_HP, SUO.EMEL FROM USERS SU, USERS_ONLINE SUO WHERE SU.USER_ID = SUO.USER_ID) USERONLINE, "+
				" (SELECT SU2.USER_ID, UPPER(SU2.USER_NAME) AS NAMA_PEGAWAI_BAHAGIAN, PEJ.NO_TEL, SUI.EMEL FROM USERS SU2, USERS_INTERNAL SUI, TBLRUJPEJABATJKPTG PEJ  "+
				" WHERE SU2.USER_ID = SUI.USER_ID AND SUI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG) USERBAHAGIAN  "+
				" WHERE T.ID_ADUANPUBLIC IS NOT NULL  AND UIUI.ID_PEJABATJKPTG = PEJUI.ID_PEJABATJKPTG(+) AND T.ID_PENGADU = USERONLINE.USER_ID(+)  AND T.ID_JENISADUAN = JA.ID_JENISADUAN(+) "+
				" AND T.ID_PEGAWAI_BAHAGIAN = USERBAHAGIAN.USER_ID(+) AND T.ID_PEGAWAI_UI = UUI.USER_ID(+) AND UUI.USER_ID = UIUI.USER_ID(+) "+
				" AND T.ID_PEJABATTANAH = PEJ_PT.ID_PEJABAT(+) AND T.ID_NEGERI_PT = NEGERI_PT.ID_NEGERI(+) AND T.ID_DAERAH_PT = DAERAH_PT.ID_DAERAH(+)  "+
				" AND T.ID_STATUS = STA.ID_STATUS(+) AND T.ID_SUMBERADUAN = SUM_PENGADU.ID_SUMBERADUAN(+)  "+
				" AND T.ID_SUMBERTINDAKAN = SUM_BAHAGIAN.ID_SUMBERADUAN(+) AND T.ID_BAHAGIAN = BAHAGIAN.ID_SEKSYEN(+)  ";
				
				if(FLAG_NOTIFIKASI.equals("Y"))
				{
					String ID_TERIMA_NOTI = getParam("ID_TERIMA_NOTI");
					String ID_STATUS_NOTI = getParam("ID_STATUS_NOTI");
					sql += " AND NOTIF.ID_ADUANPUBLIC = T.ID_ADUANPUBLIC AND NOTIF.ID_STATUS =  T.ID_STATUS " +
						" AND T.ID_STATUS = '"+ID_STATUS_NOTI+"' " +
						" AND NOTIF.ID_TERIMA= '"+ID_TERIMA_NOTI+"' " +
						" AND NOTIF.FLAG_READ = 'N'   ";					
				}	
				
				sql += " AND T.ID_KATEGORIADUAN = KAT.ID_KATEGORIADUANPUBLIC(+) AND T.ID_KATEGORIPERTANYAAN = KATP.ID_KATEGORIPERTANYAAN(+) AND T.ID_JENISTINDAKAN = KATTINDAKAN.ID_KATEGORITINDAKAN(+)  "+
				" AND T.ID_DAERAH = DAERAH.ID_DAERAH(+) AND T.ID_NEGERI = NEGERI.ID_NEGERI(+) AND T.ID_NEGERI_BAHAGIAN = NEGERI_BAHAGIAN.ID_NEGERI(+)  ";
		return sql;
	}
	
	
	public List listAduanPublic(HttpSession session,String FLAG_NOTIFIKASI)throws Exception {
		
		String FLAG_CARIAN = getParam("FLAG_CARIAN");
		myLogger.info("-------- FLAG_CARIAN : "+FLAG_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		String CR_NO_ADUAN = getParam("CR_NO_ADUAN").trim();
		String CR_NAMA_PENGADU = getParam("CR_NAMA_PENGADU").trim();
		String CR_ID_JENISADUAN = getParam("CR_ID_JENISADUAN").trim();
		String CR_ID_KATEGORIADUAN = getParam("CR_ID_KATEGORIADUAN").trim();
		String CR_ID_KATEGORIPERTANYAAN = getParam("CR_ID_KATEGORIPERTANYAAN").trim();
		String CR_ID_SUMBERADUAN = getParam("CR_ID_SUMBERADUAN").trim();
		String CR_ID_NEGERI = getParam("CR_ID_NEGERI").trim();
		String CR_ID_DAERAH = getParam("CR_ID_DAERAH").trim();
		String CR_ID_STATUS = getParam("CR_ID_STATUS").trim();		
		String CR_TARIKH_MULA = getParam("CR_TARIKH_MULA").trim();
		String CR_TARIKH_AKHIR = getParam("CR_TARIKH_AKHIR").trim();
		String CR_DETAILS_ADUAN = getParam("CR_DETAILS_ADUAN").trim();
		String CR_ID_JENISTINDAKAN = getParam("CR_ID_JENISTINDAKAN").trim();
		String CR_ID_BAHAGIAN = getParam("CR_ID_BAHAGIAN").trim();
		String CR_ID_NEGERI_BAHAGIAN = getParam("CR_ID_NEGERI_BAHAGIAN").trim();
		
		
		
		
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List list = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			//DELETE EXPIRED DRAFT LOG AFTER 48 HOURS : 172800
			deleteExpredDeraf(session,172800,db);		
			
			//DELETE EXPIRED TEMP LOG AFTER 48 HOURS : 172800
			deleteAduanTemp(session,172800,db);
			
			sql = sqlAduanPublic(session,FLAG_NOTIFIKASI);
					
			if(FLAG_CARIAN.equals("Y"))
			{
				
				if(!CR_NO_ADUAN.equals(""))
				{
					sql += " AND T.NO_ADUAN LIKE '%"+CR_NO_ADUAN.toUpperCase()+"%' ";
					
				}
				if(!CR_NAMA_PENGADU.equals(""))
				{
					sql += " AND (T.FLAG_HIDE_INFO = '2' AND (UPPER(USERONLINE.NAMA_PENGADU) LIKE '%"+CR_NAMA_PENGADU.toUpperCase()+"%' OR UPPER(T.NAMA_PENGADU) LIKE '%"+CR_NAMA_PENGADU.toUpperCase()+"%')) ";
				}
				if(!CR_ID_JENISADUAN.equals(""))
				{
					sql += " AND T.ID_JENISADUAN = '"+CR_ID_JENISADUAN+"' ";
				}
				if(!CR_ID_KATEGORIADUAN.equals(""))
				{
					sql += " AND T.ID_KATEGORIADUAN = '"+CR_ID_KATEGORIADUAN+"' ";
				}
				if(!CR_ID_SUMBERADUAN.equals(""))
				{
					sql += " AND T.ID_SUMBERADUAN = '"+CR_ID_SUMBERADUAN+"' ";
				}
				if(!CR_ID_NEGERI.equals(""))
				{
					sql += " AND T.ID_NEGERI = '"+CR_ID_NEGERI+"' ";
				}
				if(!CR_ID_DAERAH.equals(""))
				{
					sql += " AND T.ID_DAERAH = '"+CR_ID_DAERAH+"' ";
				}
				if(!CR_ID_STATUS.equals(""))
				{
					sql += " AND T.ID_STATUS = '"+CR_ID_STATUS+"' ";
				}
				if(!CR_ID_JENISTINDAKAN.equals(""))
				{
					sql += " AND T.ID_JENISTINDAKAN = '"+CR_ID_JENISTINDAKAN+"' ";
				}
				if(!CR_ID_BAHAGIAN.equals(""))
				{
					sql += " AND T.ID_BAHAGIAN = '"+CR_ID_BAHAGIAN+"' ";
				}
				
				
				
				/*
				if(!CR_TARIKH_MULA.equals("") && !CR_TARIKH_AKHIR.equals(""))
				{
					sql += " AND T.TARIKH_ADUAN BETWEEN TO_DATE('"+CR_TARIKH_MULA+"','DD/MM/YYYY') AND TO_DATE('"+CR_TARIKH_AKHIR+"','DD/MM/YYYY') ";
				}
				*/
				if(!CR_TARIKH_MULA.equals("") 
						//&& CR_TARIKH_AKHIR.equals("")
						)
				{
					sql += " AND T.TARIKH_ADUAN >= TO_DATE('"+CR_TARIKH_MULA+"','DD/MM/YYYY') ";
				}
				if(!CR_TARIKH_AKHIR.equals("") 
						//&& CR_TARIKH_MULA.equals("")
						)
				{
					sql += " AND T.TARIKH_ADUAN <= TO_DATE('"+CR_TARIKH_AKHIR+"','DD/MM/YYYY') ";
				}
				if(!CR_DETAILS_ADUAN.equals(""))
				{
					sql += " AND (UPPER(T.SUBJEK_ADUAN) LIKE '%"+CR_DETAILS_ADUAN.toUpperCase()+"%'  OR  UPPER(T.KETERANGAN_ADUAN) LIKE '%"+CR_DETAILS_ADUAN.toUpperCase()+"%' ) ";
					
				}				
				if(!CR_ID_NEGERI_BAHAGIAN.equals(""))
				{
					sql += " AND T.ID_NEGERI_BAHAGIAN = '"+CR_ID_NEGERI_BAHAGIAN+"' ";
				}
				
				
				
				this.context.put("CR_NO_ADUAN",CR_NO_ADUAN.toUpperCase());
				this.context.put("CR_NAMA_PENGADU",CR_NAMA_PENGADU.toUpperCase());
				this.context.put("CR_ID_KATEGORIADUAN",CR_ID_KATEGORIADUAN);
				this.context.put("CR_ID_KATEGORIPERTANYAAN",CR_ID_KATEGORIPERTANYAAN);				
				this.context.put("CR_ID_JENISADUAN",CR_ID_JENISADUAN);
				this.context.put("CR_ID_SUMBERADUAN",CR_ID_SUMBERADUAN);
				this.context.put("CR_ID_NEGERI",CR_ID_NEGERI);
				this.context.put("CR_ID_DAERAH",CR_ID_DAERAH);
				this.context.put("CR_ID_STATUS",CR_ID_STATUS);
				this.context.put("CR_TARIKH_MULA",CR_TARIKH_MULA);
				this.context.put("CR_TARIKH_AKHIR",CR_TARIKH_AKHIR);
				this.context.put("CR_DETAILS_ADUAN",CR_DETAILS_ADUAN);
				this.context.put("CR_ID_JENISTINDAKAN",CR_ID_JENISTINDAKAN);
				this.context.put("CR_ID_BAHAGIAN",CR_ID_BAHAGIAN);
				this.context.put("CR_ID_NEGERI_BAHAGIAN",CR_ID_NEGERI_BAHAGIAN);
				
			}
			else
			{	
				this.context.put("CR_NO_ADUAN","");
				this.context.put("CR_NAMA_PENGADU","");
				this.context.put("CR_ID_KATEGORIADUAN","");
				this.context.put("CR_ID_JENISADUAN","");
				this.context.put("CR_ID_SUMBERADUAN","");
				this.context.put("CR_ID_NEGERI","");
				this.context.put("CR_ID_DAERAH","");
				this.context.put("CR_ID_STATUS","");
				this.context.put("CR_TARIKH_MULA","");
				this.context.put("CR_TARIKH_AKHIR","");
				this.context.put("CR_DETAILS_ADUAN","");
				this.context.put("CR_ID_JENISTINDAKAN","");
				this.context.put("CR_ID_BAHAGIAN","");
				this.context.put("CR_ID_NEGERI_BAHAGIAN","");
				this.context.put("CR_ID_KATEGORIPERTANYAAN","");
				
				//by default jgn display yg selesai.. performance
				if((USER_ROLE.equals("user_unit_intergriti") || USER_ROLE.equals("wakil_bahagian_aduan")) && !FLAG_NOTIFIKASI.equals("Y"))
				{
				    sql += " AND T.ID_STATUS NOT IN (16123) ";	
				}
			}
			
			
			String order_by = " ORDER BY  ";
			if(USER_ROLE.equals("online") || USER_ROLE.equals("ppt-online-user") || USER_ROLE.equals("php-online-user") || USER_ROLE.equals("ppk-online-user") )
			{
				sql += " AND T.ID_PENGADU = '"+USER_ID_SYSTEM+"' ";
				order_by += " (CASE " +
						" WHEN (T.ID_STATUS = '16125' AND T.ID_PENGADU = '"+USER_ID_SYSTEM+"') THEN 1 " +
						" WHEN (T.ID_STATUS != '16123' AND T.ID_PENGADU = '"+USER_ID_SYSTEM+"') THEN 2 " +
						" ELSE 3 END) ASC, ";
				
			}
			else if(USER_ROLE.equals("user_unit_intergriti"))
			{
				sql += " AND (((T.ID_PENGADU IS NULL OR T.ID_PENGADU = '') AND T.ID_STATUS = '16125') OR T.ID_STATUS != '16125')  ";
				order_by += " (CASE " +
						"WHEN (T.ID_STATUS = '16125') THEN 1 " +
						"WHEN (T.ID_STATUS = '16121') THEN 2 " +
						"WHEN (T.ID_STATUS = '16122' AND T.ID_PEGAWAI_UI = '"+USER_ID_SYSTEM+"') THEN 3 " +
						"WHEN (T.ID_STATUS = '16122' AND T.ID_PEGAWAI_UI != '"+USER_ID_SYSTEM+"') THEN 4 " +
						"WHEN (T.ID_STATUS != '16122' AND T.ID_PEGAWAI_UI = '"+USER_ID_SYSTEM+"') THEN 5 " +
						"ELSE 6 END) ASC, ";
			}
			else if(USER_ROLE.equals("wakil_bahagian_aduan"))
			{				
				Map getDetailsUser = getDetailsUser(session,db);
				String ID_SEKSYEN_USER = "";
				String ID_NEGERI_USER = "";
				if(getDetailsUser!=null)
				{
					ID_SEKSYEN_USER = (String)getDetailsUser.get("ID_SEKSYEN");
					ID_NEGERI_USER = (String)getDetailsUser.get("ID_NEGERI");	
				}				
				sql += " AND T.ID_NEGERI_BAHAGIAN = '"+ID_NEGERI_USER+"' AND ID_BAHAGIAN = '"+ID_SEKSYEN_USER+"' ";		
				
				order_by += " (CASE " +
						"WHEN (T.ID_STATUS = '16126') THEN 1 " +
						"WHEN (T.ID_STATUS = '16127' AND T.ID_PEGAWAI_BAHAGIAN = '"+USER_ID_SYSTEM+"') THEN 2 " +
						"WHEN (T.ID_STATUS = '16127' AND T.ID_PEGAWAI_BAHAGIAN != '"+USER_ID_SYSTEM+"') THEN 3 " +
						"WHEN (T.ID_STATUS != '16127' AND T.ID_PEGAWAI_BAHAGIAN = '"+USER_ID_SYSTEM+"') THEN 4 " +
						"ELSE 5 END) ASC, ";				
			}			
			order_by += " T.TARIKH_ADUAN DESC, T.TARIKH_KEMASKINI DESC ";
		    
			sql += order_by;
					
			myLogger.info(" SQL : LIST_ADUAN_PUBLIC :"+ sql);
			
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
				list.add(getHashMapAduan(session,rs,rowCss,bil+"",selectedLanguage,db));
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
	
	public Map getHashMapAduan(HttpSession session, ResultSet rs,String rowCss, String bil,String selectedLanguage, Db db) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		Hashtable getUserOnline = null;
		Hashtable getSumber = null;
		String UO_FULLNAME = "";
		String UO_EMEL = "";
		String UO_TEL= "";
		String DEFAULT_NAMA_SUMBER= "";
		if(rs == null)
		{
			getUserOnline = getUserOnline(session, USER_ID_SYSTEM,db);
			UO_FULLNAME = (String)getUserOnline.get("FULLNAME");
			UO_EMEL = (String)getUserOnline.get("EMEL");
			UO_TEL = (String)getUserOnline.get("NO_HP");
			
			getSumber = getSumber(session,"16101",db);
			DEFAULT_NAMA_SUMBER = (String)getSumber.get("NAMA_SUMBER");
		}
		
		Map h = Collections.synchronizedMap(new HashMap());		
		try {
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			
			//default jika public yg insert aduan
			
			String temp_id_sumberaduan = "16101";
			String temp_id_user = USER_ID_SYSTEM;
			if(rs==null && USER_ROLE.equals("user_unit_intergriti"))
			{
				temp_id_sumberaduan = "";
				temp_id_user = "";
				UO_FULLNAME = "";
				UO_EMEL = "";
				UO_TEL = "";
			}
			h.put("ID_MASUK",rs == null ? "" : (rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK")));
			h.put("ID_SUMBERADUAN",rs == null ? temp_id_sumberaduan : (rs.getString("ID_SUMBERADUAN") == null ? "" : rs.getString("ID_SUMBERADUAN")));
			h.put("ID_PENGADU",rs == null ? temp_id_user : (rs.getString("ID_PENGADU") == null ? "" : rs.getString("ID_PENGADU")));
			h.put("NAMA_PENGADU",rs == null ? UO_FULLNAME : (rs.getString("NAMA_PENGADU") == null ? "" : rs.getString("NAMA_PENGADU").toUpperCase()));
			h.put("EMEL_PENGADU",rs == null ? UO_EMEL : (rs.getString("EMEL_PENGADU") == null ? "" : rs.getString("EMEL_PENGADU")));
			h.put("TEL_PENGADU",rs == null ? UO_TEL : (rs.getString("TEL_PENGADU") == null ? "" : rs.getString("TEL_PENGADU")));
			
			String SUMBER_PENGADU = rs == null ? "" : (rs.getString("SUMBER_PENGADU") == null ? "" : rs.getString("SUMBER_PENGADU"));
			if(selectedLanguage.equals("ENGLISH"))
			{
				SUMBER_PENGADU = rs == null ? "" : (rs.getString("SUMBER_PENGADU_ENG") == null ? "" : rs.getString("SUMBER_PENGADU_ENG"));
			}
			h.put("SUMBER_PENGADU",rs == null ? DEFAULT_NAMA_SUMBER : SUMBER_PENGADU);
			
			
			h.put("NAMA_BAHAGIAN",rs == null ? "" : (rs.getString("NAMA_BAHAGIAN") == null ? "" : rs.getString("NAMA_BAHAGIAN").toUpperCase()));
			h.put("ID_ADUANPUBLIC", rs == null ? "" : (rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC")));		
			h.put("ID_STATUS",rs == null ? "" : (rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS")));
			h.put("NO_ADUAN",rs == null ? "" : (rs.getString("NO_ADUAN") == null ? "" : rs.getString("NO_ADUAN").toUpperCase()));
			h.put("ID_PEGAWAI_UI",rs == null ? "" : (rs.getString("ID_PEGAWAI_UI") == null ? "" : rs.getString("ID_PEGAWAI_UI")));
			h.put("ID_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("ID_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("ID_PEGAWAI_BAHAGIAN")));
			h.put("TARIKH_ADUAN",rs == null ? "" : (rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN")));
			h.put("TARIKH_TERIMA_ADUAN_UI",rs == null ? "" : (rs.getString("TARIKH_TERIMA_ADUAN_UI") == null ? "" : rs.getString("TARIKH_TERIMA_ADUAN_UI")));
			h.put("TARIKH_TERIMA_ADUAN_BAHAGIAN",rs == null ? "" : (rs.getString("TARIKH_TERIMA_ADUAN_BAHAGIAN") == null ? "" : rs.getString("TARIKH_TERIMA_ADUAN_BAHAGIAN")));
			h.put("TARIKH_ADUAN_SELESAI",rs == null ? "" : (rs.getString("TARIKH_ADUAN_SELESAI") == null ? "" : rs.getString("TARIKH_ADUAN_SELESAI")));
			h.put("FLAG_HIDE_INFO",rs == null ? "2" : (rs.getString("FLAG_HIDE_INFO") == null ? "" : rs.getString("FLAG_HIDE_INFO")));
			h.put("ID_JENISADUAN",rs == null ? "" : (rs.getString("ID_JENISADUAN") == null ? "" : rs.getString("ID_JENISADUAN")));
			h.put("ID_KATEGORIADUAN",rs == null ? "" : (rs.getString("ID_KATEGORIADUAN") == null ? "" : rs.getString("ID_KATEGORIADUAN")));
			h.put("ID_KATEGORIPERTANYAAN",rs == null ? "" : (rs.getString("ID_KATEGORIPERTANYAAN") == null ? "" : rs.getString("ID_KATEGORIPERTANYAAN")));
			
						
			h.put("JAM",rs == null ? "" : (rs.getString("JAM") == null ? "" : rs.getString("JAM")));
			h.put("MINIT",rs == null ? "" : (rs.getString("MINIT") == null ? "" : rs.getString("MINIT")));
			h.put("JENIS_WAKTU",rs == null ? "" : (rs.getString("JENIS_WAKTU") == null ? "" : rs.getString("JENIS_WAKTU")));			
			h.put("SUBJEK_ADUAN",rs == null ? "" : (rs.getString("SUBJEK_ADUAN") == null ? "" : rs.getString("SUBJEK_ADUAN").toUpperCase()));
			h.put("TARIKH_KEJADIAN",rs == null ? "" : (rs.getString("TARIKH_KEJADIAN") == null ? "" : rs.getString("TARIKH_KEJADIAN")));
			h.put("LOKASI_KEJADIAN",rs == null ? "" : (rs.getString("LOKASI_KEJADIAN") == null ? "" : rs.getString("LOKASI_KEJADIAN").toUpperCase()));
			h.put("ID_NEGERI",rs == null ? "" : (rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI")));
			h.put("ID_NEGERI_BAHAGIAN",rs == null ? "" : (rs.getString("ID_NEGERI_BAHAGIAN") == null ? "" : rs.getString("ID_NEGERI_BAHAGIAN")));
			h.put("ID_DAERAH",rs == null ? "" : (rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH")));
			h.put("NAMA_PIHAK_TERLIBAT",rs == null ? "" : (rs.getString("NAMA_PIHAK_TERLIBAT") == null ? "" : rs.getString("NAMA_PIHAK_TERLIBAT").toUpperCase()));
			h.put("KETERANGAN_ADUAN",rs == null ? "" : (rs.getString("KETERANGAN_ADUAN") == null ? "" : rs.getString("KETERANGAN_ADUAN").toUpperCase()));
			h.put("MAKLUMBALAS_UI",rs == null ? "" : (rs.getString("MAKLUMBALAS_UI") == null ? "" : rs.getString("MAKLUMBALAS_UI").toUpperCase()));
			h.put("MAKLUMBALAS_BAHAGIAN",rs == null ? "" : (rs.getString("MAKLUMBALAS_BAHAGIAN") == null ? "" : rs.getString("MAKLUMBALAS_BAHAGIAN").toUpperCase()));
			h.put("ID_JENISTINDAKAN",rs == null ? "" : (rs.getString("ID_JENISTINDAKAN") == null ? "" : rs.getString("ID_JENISTINDAKAN")));
			h.put("ID_BAHAGIAN",rs == null ? "" : (rs.getString("ID_BAHAGIAN") == null ? "" : rs.getString("ID_BAHAGIAN")));
			h.put("ID_SUMBERTINDAKAN",rs == null ? "" : (rs.getString("ID_SUMBERTINDAKAN") == null ? "" : rs.getString("ID_SUMBERTINDAKAN")));
			h.put("NAMA_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("NAMA_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("NAMA_PEGAWAI_BAHAGIAN").toUpperCase()));
			h.put("EMEL_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("EMEL_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("EMEL_PEGAWAI_BAHAGIAN")));
			h.put("TEL_PEGAWAI_BAHAGIAN",rs == null ? "" : (rs.getString("TEL_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("TEL_PEGAWAI_BAHAGIAN")));
			
			h.put("NO_TEL_PEGAWAI_UI",rs == null ? "" : (rs.getString("NO_TEL_PEGAWAI_UI") == null ? "" : rs.getString("NO_TEL_PEGAWAI_UI").toUpperCase()));
			h.put("PEGAWAI_UI",rs == null ? "" : (rs.getString("PEGAWAI_UI") == null ? "" : rs.getString("PEGAWAI_UI").toUpperCase()));
			h.put("EMEL_PEGAWAI_UI",rs == null ? "" : (rs.getString("EMEL_PEGAWAI_UI") == null ? "" : rs.getString("EMEL_PEGAWAI_UI")));		
			h.put("TARIKH_KEMASKINI",rs == null ? "" : (rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI").toUpperCase()));
			
			
			h.put("EMEL_BAHAGIAN_1",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_1") == null ? "" : rs.getString("EMEL_BAHAGIAN_1")));
			h.put("EMEL_BAHAGIAN_2",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_2") == null ? "" : rs.getString("EMEL_BAHAGIAN_2")));
			h.put("EMEL_BAHAGIAN_3",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_3") == null ? "" : rs.getString("EMEL_BAHAGIAN_3")));
			h.put("EMEL_BAHAGIAN_4",rs == null ? "" : (rs.getString("EMEL_BAHAGIAN_4") == null ? "" : rs.getString("EMEL_BAHAGIAN_4")));
			h.put("FLAG_LUAR_TINDAKAN",rs == null ? "" : (rs.getString("FLAG_LUAR_TINDAKAN") == null ? "" : rs.getString("FLAG_LUAR_TINDAKAN")));
			String FLAG_LUAR_TINDAKAN = rs == null ? "" : (rs.getString("FLAG_LUAR_TINDAKAN") == null ? "" : rs.getString("FLAG_LUAR_TINDAKAN"));
			String LUAR_TINDAKAN = "";
			
			if(FLAG_LUAR_TINDAKAN.equals("Y"))
			{
				LUAR_TINDAKAN = "YA";
				if(selectedLanguage.equals("ENGLISH"))
				{
					LUAR_TINDAKAN = "YES";
				}
			}
			else
			{
				LUAR_TINDAKAN = "TIDAK";
				if(selectedLanguage.equals("ENGLISH"))
				{
					LUAR_TINDAKAN = "NO";
				}
			}			
			h.put("LUAR_TINDAKAN",LUAR_TINDAKAN);
			
			
			h.put("ID_PEJABATTANAH",rs == null ? "" : (rs.getString("ID_PEJABATTANAH") == null ? "" : rs.getString("ID_PEJABATTANAH").toUpperCase()));
			h.put("ID_NEGERI_PT",rs == null ? "" : (rs.getString("ID_NEGERI_PT") == null ? "" : rs.getString("ID_NEGERI_PT").toUpperCase()));
			h.put("ID_DAERAH_PT",rs == null ? "" : (rs.getString("ID_DAERAH_PT") == null ? "" : rs.getString("ID_DAERAH_PT").toUpperCase()));
			h.put("NEGERI_PT",rs == null ? "" : (rs.getString("NEGERI_PT") == null ? "" : rs.getString("NEGERI_PT").toUpperCase()));
			h.put("DAERAH_PT",rs == null ? "" : (rs.getString("DAERAH_PT") == null ? "" : rs.getString("DAERAH_PT").toUpperCase()));
			h.put("PEJABAT_PT",rs == null ? "" : (rs.getString("PEJABAT_PT") == null ? "" : rs.getString("PEJABAT_PT").toUpperCase()));
			
			
			
			//x;
			
			String mesej_notifikasi_tindakan = "";
			if(rs != null)
			{
				String tarikh_kemaskini = rs == null ? "" : (rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
				String id_status = (rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
								
				if(USER_ROLE.equals("user_unit_intergriti"))
				{	
					String id_pegawai_ui = (rs.getString("ID_PEGAWAI_UI") == null ? "" : rs.getString("ID_PEGAWAI_UI"));
					if(id_status.equals("16122") && id_pegawai_ui.equals(USER_ID_SYSTEM))
					{
						mesej_notifikasi_tindakan = "alert_masih_didalam_tindakan_ui";
					}
					else if(id_status.equals("16121"))
					{
						mesej_notifikasi_tindakan = "alert_perlu_tindakan_ui";
					}					
					else if(id_status.equals("16125"))
					{
						Date actual_date = new Date();
						Date date_current = formatter.parse(tarikh_kemaskini);							
						long duration  = actual_date.getTime() - date_current.getTime();
						long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);	
						if(diffInSeconds > 86400)//alert appear selepas 24 jam : 86400
						{
							mesej_notifikasi_tindakan = "alert_delete_log_deraf";
						}
					}
				}
				else if(USER_ROLE.equals("wakil_bahagian_aduan"))
				{	
					String id_pegawai_bahagian = (rs.getString("ID_PEGAWAI_BAHAGIAN") == null ? "" : rs.getString("ID_PEGAWAI_BAHAGIAN"));
					if(id_status.equals("16127") && id_pegawai_bahagian.equals(USER_ID_SYSTEM))
					{
						mesej_notifikasi_tindakan = "alert_masih_didalam_tindakan_bahagian";
					}
					else if(id_status.equals("16126"))
					{
						mesej_notifikasi_tindakan = "alert_perlu_tindakan_bahagian";
					}
				}
				else
				{
					if(id_status.equals("16125"))
					{
						Date actual_date = new Date();
						Date date_current = formatter.parse(tarikh_kemaskini);							
						long duration  = actual_date.getTime() - date_current.getTime();
						long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);	
						if(diffInSeconds > 86400)//alert appear selepas 24 jam : 86400
						{
							mesej_notifikasi_tindakan = "alert_delete_log_deraf";
						}
					}
				}
			}
			
			h.put("MESEJ_TINDAKAN",mesej_notifikasi_tindakan);
			
			String STATUS = rs == null ? "" : (rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
			if(selectedLanguage.equals("ENGLISH"))
			{
				STATUS = rs == null ? "" : (rs.getString("STATUS_ENG") == null ? "" : rs.getString("STATUS_ENG").toUpperCase());
			}
			h.put("STATUS",STATUS);
			
			
			
			
			String SUMBER_BAHAGIAN = rs == null ? "" : (rs.getString("SUMBER_BAHAGIAN") == null ? "" : rs.getString("SUMBER_BAHAGIAN"));
			if(selectedLanguage.equals("ENGLISH"))
			{
				SUMBER_BAHAGIAN = rs == null ? "" : (rs.getString("SUMBER_BAHAGIAN_ENG") == null ? "" : rs.getString("SUMBER_BAHAGIAN_ENG"));
			}
			h.put("SUMBER_BAHAGIAN",SUMBER_BAHAGIAN);
			
			String KATEGORITINDAKAN = rs == null ? "" : (rs.getString("KATEGORITINDAKAN") == null ? "" : rs.getString("KATEGORITINDAKAN"));
			if(selectedLanguage.equals("ENGLISH"))
			{
				KATEGORITINDAKAN = rs == null ? "" : (rs.getString("KATEGORITINDAKAN_ENG") == null ? "" : rs.getString("KATEGORITINDAKAN_ENG").toUpperCase());
			}
			h.put("KATEGORITINDAKAN",KATEGORITINDAKAN);
			
			
			String KATEGORIADUAN = rs == null ? "" : (rs.getString("KATEGORIADUAN") == null ? "" : rs.getString("KATEGORIADUAN").toUpperCase());
			if(selectedLanguage.equals("ENGLISH"))
			{
				KATEGORIADUAN = rs == null ? "" : (rs.getString("KATEGORIADUAN_ENG") == null ? "" : rs.getString("KATEGORIADUAN_ENG").toUpperCase());
			}
			h.put("KATEGORIADUAN",KATEGORIADUAN);
			
			String KATEGORIPERTANYAAN = rs == null ? "" : (rs.getString("KATEGORIPERTANYAAN") == null ? "" : rs.getString("KATEGORIPERTANYAAN").toUpperCase());
			if(selectedLanguage.equals("ENGLISH"))
			{
				KATEGORIPERTANYAAN = rs == null ? "" : (rs.getString("KATEGORIPERTANYAAN_ENG") == null ? "" : rs.getString("KATEGORIPERTANYAAN_ENG").toUpperCase());
			}
			h.put("KATEGORIPERTANYAAN",KATEGORIPERTANYAAN);
			
			
			
			
			String JENIS_ADUAN = rs == null ? "" : (rs.getString("JENIS_ADUAN") == null ? "" : rs.getString("JENIS_ADUAN").toUpperCase());
			if(selectedLanguage.equals("ENGLISH"))
			{
				JENIS_ADUAN = rs == null ? "" : (rs.getString("JENIS_ADUAN_ENG") == null ? "" : rs.getString("JENIS_ADUAN_ENG").toUpperCase());
			}
			h.put("JENIS_ADUAN",JENIS_ADUAN);
			
			
			
			h.put("DAERAH",rs == null ? "" : (rs.getString("DAERAH") == null ? "" : rs.getString("DAERAH").toUpperCase()));
			h.put("NEGERI",rs == null ? "" : (rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI").toUpperCase()));	
			h.put("NEGERI_BAHAGIAN",rs == null ? "" : (rs.getString("NEGERI_BAHAGIAN") == null ? "" : rs.getString("NEGERI_BAHAGIAN").toUpperCase()));	
			h.put("FLAG_SEMENTARA",rs == null ? "" : (rs.getString("FLAG_SEMENTARA") == null ? "" : rs.getString("FLAG_SEMENTARA").toUpperCase()));
			
		
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
		
	
	//DELETE FROM TBLADUANPUBLIC T  WHERE T.ID_STATUS = 16125 AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > 172800
	
	public void deleteExpredDeraf(HttpSession session,long duration, Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
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
				//conn = db.getConnection();
				//conn.setAutoCommit(false);
				
				Statement stmt = db1.getStatement();
				SQLRenderer r = new SQLRenderer();	
								
				stmt.executeUpdate("DELETE FROM TBLADUANPUBLICNOTIFIKASI L WHERE L.ID_ADUANPUBLIC IN  (SELECT T.ID_ADUANPUBLIC FROM TBLADUANPUBLIC T  WHERE T.ID_STATUS = 16125 AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration+")");
				//AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICNOTIFIKASI [STATUS DRAFT IS EXPIRED] Deleted");
				
				stmt.executeUpdate("DELETE FROM TBLKRONOLOGIADUANPUBLIC L WHERE L.ID_ADUANPUBLIC IN  (SELECT T.ID_ADUANPUBLIC FROM TBLADUANPUBLIC T  WHERE T.ID_STATUS = 16125 AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration+")");
				//AuditTrail.logActivity(this,session,"DEL","TBLKRONOLOGIADUANPUBLIC [STATUS DRAFT IS EXPIRED] Deleted");
				
				
				stmt.executeUpdate("DELETE FROM TBLADUANPUBLICLAMPIRAN L WHERE L.ID_ADUANPUBLIC IN  (SELECT T.ID_ADUANPUBLIC FROM TBLADUANPUBLIC T  WHERE T.ID_STATUS = 16125 AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration+")");
				//AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICLAMPIRAN [STATUS DRAFT IS EXPIRED] Deleted");
				
				stmt.executeUpdate("DELETE FROM TBLADUANPUBLIC T  WHERE T.ID_STATUS = 16125 AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration);
				//AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLIC [STATUS DRAFT IS EXPIRED] Deleted");
				
				
//				conn.commit();
			
		} /*
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
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
	}
	
	
	public void deleteAduanTemp(HttpSession session,long duration, Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
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
				//conn = db.getConnection();
				//conn.setAutoCommit(false);
				
				Statement stmt = db1.getStatement();
				SQLRenderer r = new SQLRenderer();	
								
				stmt.executeUpdate("DELETE FROM TBLADUANPUBLICNOTIFIKASI L WHERE L.ID_ADUANPUBLIC IN  (SELECT T.ID_ADUANPUBLIC FROM TBLADUANPUBLIC T  WHERE T.FLAG_SEMENTARA = 'Y' AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration+")");
				//AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICNOTIFIKASI [STATUS TEMP IS EXPIRED] Deleted");
				
				stmt.executeUpdate("DELETE FROM TBLKRONOLOGIADUANPUBLIC L WHERE L.ID_ADUANPUBLIC IN  (SELECT T.ID_ADUANPUBLIC FROM TBLADUANPUBLIC T  WHERE T.FLAG_SEMENTARA = 'Y' AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration+")");
				//AuditTrail.logActivity(this,session,"DEL","TBLKRONOLOGIADUANPUBLIC [STATUS TEMP IS EXPIRED] Deleted");
				
				
				stmt.executeUpdate("DELETE FROM TBLADUANPUBLICLAMPIRAN L WHERE L.ID_ADUANPUBLIC IN  (SELECT T.ID_ADUANPUBLIC FROM TBLADUANPUBLIC T  WHERE T.FLAG_SEMENTARA = 'Y' AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration+")");
				//AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICLAMPIRAN [STATUS TEMP IS EXPIRED] Deleted");
				
				stmt.executeUpdate("DELETE FROM TBLADUANPUBLIC T  WHERE T.FLAG_SEMENTARA = 'Y' AND ((SYSDATE - T.TARIKH_KEMASKINI)*24*60*60) > "+duration);
				//AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLIC [STATUS TEMP IS EXPIRED] Deleted");
				
				
//				conn.commit();
			
		} /*
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
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
	}
	
	
	public void delete(HttpSession session,String ID_ADUANPUBLIC) throws Exception {
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
			r.add("ID_ADUANPUBLIC",ID_ADUANPUBLIC);
			sql = r.getSQLDelete("TBLADUANPUBLICNOTIFIKASI");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICNOTIFIKASI [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+"] Deleted");
				
			r.clear();
			r.add("ID_ADUANPUBLIC",ID_ADUANPUBLIC);
			sql = r.getSQLDelete("TBLADUANPUBLIC");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLIC [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+"] Deleted");
			
			
			r.clear();
			r.add("ID_ADUANPUBLIC",ID_ADUANPUBLIC);
			sql = r.getSQLDelete("TBLADUANPUBLICLAMPIRAN");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLADUANPUBLICLAMPIRAN [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+"] Deleted");
			
			
			r.clear();
			r.add("ID_ADUANPUBLIC",ID_ADUANPUBLIC);
			sql = r.getSQLDelete("TBLKRONOLOGIADUANPUBLIC");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLKRONOLOGIADUANPUBLIC [ID_ADUANPUBLIC : "+ID_ADUANPUBLIC+"] Deleted");
		
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
			sql = " SELECT ID_NEGERI, KOD_NEGERI, NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI NOT IN (0,17) ";
			
			if(ID_JENISTINDAKAN.equals("16171"))
			{
				sql += " AND ID_NEGERI IN (16) ";
			}
			else if(ID_JENISTINDAKAN.equals("16172"))
			{
				sql += " AND ID_NEGERI NOT IN (16) ";
			}
			else if(ID_JENISTINDAKAN.equals("16173") || ID_JENISTINDAKAN.equals("16174"))
			{
				sql += " AND ID_NEGERI NOT IN (12,13) ";
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
	public List listDaerah(HttpSession session,String ID_NEGERI,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listDaerah = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");		
		String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		*/
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
			sql = " SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH, ID_NEGERI FROM TBLRUJDAERAH WHERE KOD_DAERAH NOT IN ('0','00') ";
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
	public List listPejabatPT(HttpSession session,String ID_NEGERI,String ID_DAERAH,String ID_JENISTINDAKAN,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listPejabatPT = null;
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
			sql = " SELECT T.ID_NEGERI, T.ID_DAERAH, T.NAMA_PEJABAT,T.ID_PEJABAT  " +
					" FROM  TBLRUJPEJABAT T WHERE T.ID_PEJABAT IS NOT NULL ";
			
			if(!ID_NEGERI.equals(""))
			{
				sql += " AND T.ID_NEGERI = '"+ID_NEGERI+"'  ";
			}   
		    if(!ID_DAERAH.equals(""))
			{
				sql += " AND T.ID_DAERAH = '"+ID_DAERAH+"'  ";
			}
		    if(ID_JENISTINDAKAN.equals("16173"))
			{
		    	sql += " AND T.ID_JENISPEJABAT IN (1)  ";
			}
		    else if(ID_JENISTINDAKAN.equals("16174"))
			{
		    	sql += " AND T.ID_JENISPEJABAT IN (2)  ";
			}
		    
			sql += " AND ID_SEKSYEN = 1 ORDER BY T.KOD_PEJABAT ";			
			myLogger.info(" ADUAN : SQL listPejabat :"+ sql);
			rs = stmt.executeQuery(sql);
			listPejabatPT = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH").toUpperCase());
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT").toUpperCase());
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				listPejabatPT.add(h);
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
		return listPejabatPT;
	}
	
	@SuppressWarnings("unchecked")
	public List listJenisAduan(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listJenisAduan = null;
		String sql = "";
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
			sql = " SELECT ID_JENISADUAN, KOD_JENIS_ADUAN, JENIS_ADUAN, JENIS_ADUAN_ENG FROM TBLRUJJENISESADUAN "+
				" ORDER BY KOD_JENIS_ADUAN ";			
			myLogger.info(" ADUAN : SQL listJenisAduan :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_JENISADUAN",rs.getString("ID_JENISADUAN") == null ? "" : rs.getString("ID_JENISADUAN").toUpperCase());
				h.put("KOD_JENIS_ADUAN",rs.getString("KOD_JENIS_ADUAN") == null ? "" : rs.getString("KOD_JENIS_ADUAN").toUpperCase());
				String JENIS_ADUAN = rs == null ? "" : (rs.getString("JENIS_ADUAN") == null ? "" : rs.getString("JENIS_ADUAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					JENIS_ADUAN = rs == null ? "" : (rs.getString("JENIS_ADUAN_ENG") == null ? "" : rs.getString("JENIS_ADUAN_ENG").toUpperCase());
				}
				h.put("JENIS_ADUAN",JENIS_ADUAN);				
				listJenisAduan.add(h);
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
		return listJenisAduan;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listJenisTindakan(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listJenisTindakan = null;
		String sql = "";
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
			sql = " SELECT ID_KATEGORITINDAKAN, KATEGORITINDAKAN, KATEGORITINDAKAN_ENG FROM TBLRUJKATEGORITINDAKAN ORDER BY ID_KATEGORITINDAKAN ";			
			myLogger.info(" ADUAN : SQL listJenisTindakan :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisTindakan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_KATEGORITINDAKAN",rs.getString("ID_KATEGORITINDAKAN") == null ? "" : rs.getString("ID_KATEGORITINDAKAN").toUpperCase());
				String KATEGORITINDAKAN = rs == null ? "" : (rs.getString("KATEGORITINDAKAN") == null ? "" : rs.getString("KATEGORITINDAKAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					KATEGORITINDAKAN = rs == null ? "" : (rs.getString("KATEGORITINDAKAN_ENG") == null ? "" : rs.getString("KATEGORITINDAKAN_ENG").toUpperCase());
				}
				h.put("KATEGORITINDAKAN",KATEGORITINDAKAN);				
				listJenisTindakan.add(h);
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
		return listJenisTindakan;
	}
	
	public List listJenisSumber(HttpSession session,Db db)throws Exception {
		return listJenisSumber(session,db,"");
	}
	
	
	@SuppressWarnings("unchecked")
	public List listJenisSumber(HttpSession session,Db db,String ID_JENISTINDAKAN)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listJenisSumber = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		*/
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
			sql = " SELECT ID_SUMBERADUAN, KOD_SUMBER, NAMA_SUMBER, NAMA_SUMBER_ENG FROM TBLRUJSUMBERESADUAN WHERE ID_SUMBERADUAN IS NOT NULL ";
			
			if(ID_JENISTINDAKAN.equals("16176"))
			{
				sql += " AND ID_SUMBERADUAN IN (16101) ";
			}
			else if(!ID_JENISTINDAKAN.equals("16171") && !ID_JENISTINDAKAN.equals("16172") && !ID_JENISTINDAKAN.equals(""))
			{
				sql += " AND ID_SUMBERADUAN NOT IN (16101) ";
			}
			
			sql += "ORDER BY KOD_SUMBER ";			
			myLogger.info(" ADUAN : SQL listJenisSumberPengadu :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisSumber = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_SUMBERADUAN",rs.getString("ID_SUMBERADUAN") == null ? "" : rs.getString("ID_SUMBERADUAN").toUpperCase());
				h.put("KOD_SUMBER",rs.getString("KOD_SUMBER") == null ? "" : rs.getString("KOD_SUMBER").toUpperCase());
				String NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER") == null ? "" : rs.getString("NAMA_SUMBER"));
				if(selectedLanguage.equals("ENGLISH"))
				{
					NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER_ENG") == null ? "" : rs.getString("NAMA_SUMBER_ENG"));
				}
				h.put("NAMA_SUMBER",NAMA_SUMBER);				
				listJenisSumber.add(h);
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
		return listJenisSumber;
	}
	
	
	public List listStatus(HttpSession session,Db db)throws Exception {
		return listStatus(session,db,"","","","");
	}	
	
	@SuppressWarnings("unchecked")
	public List listStatus(HttpSession session,Db db,String mode,String CURRENT_ID_STATUS,String ID_JENISTINDAKAN,String ID_SUMBERTINDAKAN)throws Exception {
		Db db1 = null;		
		ResultSet rs = null;
		Statement stmt = null;
		List listStatus = null;
		String sql = "";
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
			sql = " SELECT ID_STATUS, KOD_STATUS, KETERANGAN, KETERANGAN_ENG FROM TBLRUJSTATUSADUANPUBLIC ";
			sql += " WHERE ID_STATUS IS NOT NULL AND ID_STATUS NOT IN (16124) "; 
			if(mode.equals("form"))
			{
				if(!ID_JENISTINDAKAN.equals(""))
				{
					if(ID_JENISTINDAKAN.equals("16171") || ID_JENISTINDAKAN.equals("16172"))
					{
						if(CURRENT_ID_STATUS.equals("16122"))//status dalam tindakan ui
						{						
							if(ID_SUMBERTINDAKAN.equals("16101"))//myetapp
							{
								sql += " AND ID_STATUS IN (16122,16126) ";
							}
							else
							{
								sql += " AND ID_STATUS IN (16122,16123) ";
							}
						}
						else if(CURRENT_ID_STATUS.equals("16127"))//status dalam tindakan bahagian
						{						
							if(ID_SUMBERTINDAKAN.equals("16101"))//myetapp
							{
								sql += " AND ID_STATUS IN (16127,16123) ";
							}
						}
					}
					else
					{
						sql += " AND ID_STATUS IN (16122,16123) ";
					}
				}
				else
				{
					sql += " AND ID_STATUS IN (16122,16123) ";
				}
			}
			
			
			sql += " ORDER BY LANGKAH ";			
			myLogger.info(" ADUAN : SQL listStatus :"+ sql);
			rs = stmt.executeQuery(sql);
			listStatus = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS").toUpperCase());
				h.put("KOD_STATUS",rs.getString("KOD_STATUS") == null ? "" : rs.getString("KOD_STATUS").toUpperCase());
				String KETERANGAN = rs == null ? "" : (rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					KETERANGAN = rs == null ? "" : (rs.getString("KETERANGAN_ENG") == null ? "" : rs.getString("KETERANGAN_ENG").toUpperCase());
				}
				h.put("KETERANGAN",KETERANGAN);				
				listStatus.add(h);
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
		return listStatus;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listBahagian(HttpSession session,Db db)throws Exception {
		Db db1 = null;		
		ResultSet rs = null;
		Statement stmt = null;
		List listBahagian = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		*/
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
			sql = " SELECT ID_SEKSYEN, KOD_SEKSYEN, NAMA_SEKSYEN FROM TBLRUJSEKSYEN WHERE ID_SEKSYEN NOT IN (0) ORDER BY ID_SEKSYEN ";			
			myLogger.info(" ADUAN : SQL listBahagian :"+ sql);
			rs = stmt.executeQuery(sql);
			listBahagian = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN").toUpperCase());
				h.put("KOD_SEKSYEN",rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN").toUpperCase());
				String NAMA_SEKSYEN = rs == null ? "" : (rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN").toUpperCase());
				/*if(selectedLanguage.equals("ENGLISH"))
				{
					NAMA_SUMBER = rs == null ? "" : (rs.getString("NAMA_SUMBER_ENG") == null ? "" : rs.getString("NAMA_SUMBER_ENG").toUpperCase());
				}
				*/
				h.put("NAMA_SEKSYEN",NAMA_SEKSYEN);				
				listBahagian.add(h);
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
		return listBahagian;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listKategoriAduan(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listKategoriAduan = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		*/
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
			sql = " SELECT ID_KATEGORIADUANPUBLIC, KATEGORIADUAN, KATEGORIADUAN_ENG  FROM TBLRUJKATEGORIADUANPUBLIC ";
			
			if(selectedLanguage.equals("ENGLISH"))
			{
				sql += " ORDER BY (CASE WHEN ID_KATEGORIADUANPUBLIC = '16178' THEN 2 ELSE 1 END) ASC, KATEGORIADUAN_ENG ";
			}
			else
			{
				sql += " ORDER BY (CASE WHEN ID_KATEGORIADUANPUBLIC = '16178' THEN 2 ELSE 1 END) ASC, KATEGORIADUAN ";
			}
			
			myLogger.info(" ADUAN : SQL KATEGORIADUAN :"+ sql);
			rs = stmt.executeQuery(sql);
			listKategoriAduan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_KATEGORIADUAN",rs.getString("ID_KATEGORIADUANPUBLIC") == null ? "" : rs.getString("ID_KATEGORIADUANPUBLIC").toUpperCase());
				String KATEGORIADUAN = rs == null ? "" : (rs.getString("KATEGORIADUAN") == null ? "" : rs.getString("KATEGORIADUAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					KATEGORIADUAN = rs == null ? "" : (rs.getString("KATEGORIADUAN_ENG") == null ? "" : rs.getString("KATEGORIADUAN_ENG").toUpperCase());
				}
				h.put("KATEGORIADUAN",KATEGORIADUAN);				
				listKategoriAduan.add(h);
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
		return listKategoriAduan;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listKATEGORIPERTANYAAN(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listKATEGORIPERTANYAAN = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		*/
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
			sql = " SELECT ID_KATEGORIPERTANYAAN, KATEGORIPERTANYAAN, KATEGORIPERTANYAAN_ENG  FROM TBLRUJKATEGORIPERTANYAAN ";
			
			if(selectedLanguage.equals("ENGLISH"))
			{
				sql += " ORDER BY  KATEGORIPERTANYAAN_ENG ";
			}
			else
			{
				sql += " ORDER BY  KATEGORIPERTANYAAN ";
			}
			
			myLogger.info(" ADUAN : SQL KATEGORIPERTANYAAN :"+ sql);
			rs = stmt.executeQuery(sql);
			listKATEGORIPERTANYAAN = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_KATEGORIPERTANYAAN",rs.getString("ID_KATEGORIPERTANYAAN") == null ? "" : rs.getString("ID_KATEGORIPERTANYAAN").toUpperCase());
				String KATEGORIPERTANYAAN = rs == null ? "" : (rs.getString("KATEGORIPERTANYAAN") == null ? "" : rs.getString("KATEGORIPERTANYAAN").toUpperCase());
				if(selectedLanguage.equals("ENGLISH"))
				{
					KATEGORIPERTANYAAN = rs == null ? "" : (rs.getString("KATEGORIPERTANYAAN_ENG") == null ? "" : rs.getString("KATEGORIPERTANYAAN_ENG").toUpperCase());
				}
				h.put("KATEGORIPERTANYAAN",KATEGORIPERTANYAAN);				
				listKATEGORIPERTANYAAN.add(h);
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
		return listKATEGORIPERTANYAAN;
	}

	

	@SuppressWarnings("unchecked")
	public List listJam(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listJam = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		*/
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
			sql = " SELECT JAM FROM TBLRUJJAM ORDER BY JAM ";
			
			
			myLogger.info(" ADUAN : SQL JAM :"+ sql);
			rs = stmt.executeQuery(sql);
			listJam = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("JAM",rs.getString("JAM") == null ? "" : rs.getString("JAM").toUpperCase());							
				listJam.add(h);
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
		return listJam;
	}
	
	@SuppressWarnings("unchecked")
	public List listMinit(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listMinit = null;
		String sql = "";
		/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		*/
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
			sql = " SELECT MINIT FROM TBLRUJMINIT ORDER BY MINIT ";
			
			
			myLogger.info(" ADUAN : SQL MINIT :"+ sql);
			rs = stmt.executeQuery(sql);
			listMinit = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("MINIT",rs.getString("MINIT") == null ? "" : rs.getString("MINIT").toUpperCase());							
				listMinit.add(h);
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
		return listMinit;
	}

	
	
	@SuppressWarnings("unchecked")
	public List listUnit(HttpSession session,String ID_NEGERI,String ID_UNIT_PEMOHON)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listUnit = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		try {
			db = new Db();
			stmt = db.getStatement(); 
			sql = " SELECT LAYER, ID_UNIT,NAMA_UNIT,NAMA_NEGERI,ID_NEGERI,ID_JENISPEJABAT FROM "+
				" ( " +" SELECT 2 AS LAYER, ID_JENISPEJABAT,TO_CHAR(ID_PEJABATJKPTG) AS ID_UNIT, UPPER(NAMA_PEJABAT) AS NAMA_UNIT, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, " +
					" TO_CHAR(P.ID_NEGERI) AS ID_NEGERI FROM TBLRUJPEJABATJKPTG P,TBLRUJNEGERI N  "+
					" WHERE ID_SEKSYEN = '2' AND ID_JENISPEJABAT IN " +
					"(22,24) ";
					
					if(!ID_UNIT_PEMOHON.equals(""))
					{
						sql += " AND ID_PEJABATJKPTG != "+ID_UNIT_PEMOHON+" ";
					}
					
					sql += " AND P.ID_NEGERI = N.ID_NEGERI";
					
			sql += " AND P.ID_NEGERI = '"+ID_NEGERI+"' ";
					sql += " )  "+
					" WHERE ID_UNIT IS NOT NULL ";
			sql += " ORDER BY LAYER,NAMA_NEGERI, NAMA_UNIT  ";			
			myLogger.info(" V3: SQL listUnit :"+ sql);
			rs = stmt.executeQuery(sql);
			listUnit = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT").toUpperCase());
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT").toUpperCase());
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT").toUpperCase());
				
				listUnit.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listUnit;

	}
	
	
	@SuppressWarnings("unchecked")
	public List listPelulus(HttpSession session,String ID_NEGERI)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPelulus = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement(); 
			sql = " SELECT U.USER_ID AS ID_PELULUS, UPPER(U.USER_NAME) AS NAMA_PELULUS, UPPER(J.KETERANGAN) AS JAWATAN FROM USERS U, USERS_INTERNAL UI, TBLRUJPEJABATJKPTG PEJ, TBLRUJJAWATAN J "+
					 " WHERE U.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_JENISPEJABAT IN (21,22,23) "+
					 " AND UI.ID_JAWATAN = J.ID_JAWATAN "+
					 " AND UI.ID_SEKSYEN = '2' AND (U.USER_ROLE = 'adminppk' OR U.USER_NAME IN (SELECT USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk')) ";
			sql += " AND PEJ.ID_NEGERI = '"+ID_NEGERI+"' ";			
			sql += " ORDER BY U.USER_NAME ";			
			myLogger.info(" V3: SQL listPelulus :"+ sql);
			rs = stmt.executeQuery(sql);
			listPelulus = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_PELULUS",rs.getString("ID_PELULUS") == null ? "" : rs.getString("ID_PELULUS").toUpperCase());
				h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS").toUpperCase());
				h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN").toUpperCase());
				listPelulus.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPelulus;

	}
	
	
	
	public String checkDuplicateDate(HttpSession session, String ID_PEMOHON,String ID_PERMOHONANBANTUUNIT,
			String TARIKH_MULA, String TARIKH_AKHIR,String _ID_NEGERI,String _ID_UNIT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			db = new Db();
			stmt = db.getStatement();
			String NO_BANTUUNIT="";
			sql = " SELECT T.NO_BANTUUNIT FROM TBLPERMOHONANBANTUUNIT T "+
					" WHERE T.ID_PERMOHONANBANTUUNIT IS NOT NULL " +
					//" AND T.ID_STATUS != '3' " +					
		 			" AND (CASE  "+
					" WHEN T.ID_STATUS = '2' "+
					" THEN  "+
					" (CASE WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') < TO_DATE(TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+
					" THEN 4 "+
					" WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+
					" THEN 5 "+
					" END) "+
					" ELSE T.ID_STATUS END "+
					" ) NOT IN (3,5) "+				
					" AND T.ID_PEMOHON = '"+ID_PEMOHON+"' AND T.ID_NEGERI = '"+_ID_NEGERI+"' AND T.ID_UNIT = '"+_ID_UNIT+"' "+
					" AND (   (    TO_DATE (TO_CHAR (t.tarikh_mula, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY') >= "+
					" (TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" AND (TO_DATE (TO_CHAR (t.tarikh_akhir, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY' "+
					" ) >= TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" ) "+
					" OR (    TO_DATE (TO_CHAR (t.tarikh_mula, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY') <= "+
					" (TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" AND (TO_DATE (TO_CHAR (t.tarikh_akhir, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY' "+
					" ) >= TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" ) "+
					" ) "+
					" AND (   TO_DATE (TO_CHAR (t.tarikh_akhir, 'DD/MM/YYYY'), 'DD/MM/YYYY') <= "+
					" TO_DATE ('"+TARIKH_AKHIR+"', 'DD/MM/YYYY') "+
					" OR TO_DATE (TO_CHAR (t.tarikh_mula, 'DD/MM/YYYY'), 'DD/MM/YYYY') <= "+
					" TO_DATE ('"+TARIKH_AKHIR+"', 'DD/MM/YYYY') "+
					" ) ";	
			
					if(!ID_PERMOHONANBANTUUNIT.equals(""))
					{
						sql += " AND  T.ID_PERMOHONANBANTUUNIT != '"+ID_PERMOHONANBANTUUNIT+"' ";
					}
					
				myLogger.info(" OT : checkDuplicateDate :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				int bil = 0;
				while (rs.next()) {				
					bil ++;
					if(bil>1 && rs.getString("NO_BANTUUNIT")!=null && !rs.getString("NO_BANTUUNIT").equals("") )
					{
						NO_BANTUUNIT += ", ";
					}	
					NO_BANTUUNIT += (rs.getString("NO_BANTUUNIT") == null ? "" : rs.getString("NO_BANTUUNIT"));
				}
			
			return NO_BANTUUNIT;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String getKodNegeri(HttpSession session, String ID_NEGERI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String KOD_NEGERI="";
			sql = " SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"+ID_NEGERI+"' ";	
			myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					KOD_NEGERI = (rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				}			
			return KOD_NEGERI;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
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
	
	
	
	public static synchronized int getSeqNo(String tahun,String bulan, Db db)
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

			sb.append("SELECT NO_TURUTAN,BULAN  FROM TBLRUJSEQNOADUANPUBLIC WHERE ");
			sb.append(" TAHUN = '" + tahun + "'   AND  BULAN = '" + bulan + "'");
			ResultSet rs = db1.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			
			myLogger.info("found SEQ :"+found);
			
			
			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseNo(tahun,bulan,db);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNo(tahun,bulan,db);
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
		 public static void increaseNo(String tahun,String bulan,Db db) throws DbException {

				Db db1 = null;
				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE TBLRUJSEQNOADUANPUBLIC  SET ");
				sb.append("no_turutan = no_turutan + 1 ");
				sb.append(" WHERE ");
				sb.append(" TAHUN = '" + tahun + "'");
				sb.append(" AND BULAN = '" + bulan + "'");
				myLogger.info("increaseNo: "+sb.toString());
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
		 public static void addNo(String tahun,String bulan,Db db) throws DbException {

			 
				Db db1 = null;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO TBLRUJSEQNOADUANPUBLIC (TAHUN,BULAN,NO_TURUTAN)");
				sb.append(" VALUES (");
				sb.append("'" + tahun + "'");
				sb.append(",'" + bulan + "'");
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
		 
		 public void hantarEmel(HttpSession session,String ID_ADUANPUBLIC, String[] PENERIMA, String ID_STATUS,Db db) throws Exception {
			 hantarEmel(session,ID_ADUANPUBLIC, PENERIMA, ID_STATUS,db, "");
		 }
		
		 public void hantarEmel(HttpSession session,String ID_ADUANPUBLIC, String[] PENERIMA, String ID_STATUS,Db db, String Flag_Akuan_terima) throws Exception {
			    String selectedLanguage = (String) session.getAttribute("selectedLanguage");
			    String FLAG_CC_PENGADU = "";
			    String FLAG_CC_PENGARAH_BAHAGIAN = "";
			 	if(PENERIMA.length>0)
			 	{
			 		Map view = view(session, ID_ADUANPUBLIC,"","",selectedLanguage,db);
			 		
			 		String ID_BAHAGIAN = (String)view.get("ID_BAHAGIAN");
			 		String EMEL_PENGADU = (String)view.get("EMEL_PENGADU");
			 		String NO_ADUAN = (String)view.get("NO_ADUAN");
			 		String JENIS_ADUAN = (String)view.get("JENIS_ADUAN");
			 		String STATUS = (String)view.get("STATUS");
			 		String NAMA_BAHAGIAN = (String)view.get("NAMA_BAHAGIAN");
			 		String NEGERI_BAHAGIAN = (String)view.get("NEGERI_BAHAGIAN");
			 		//String EMEL_BAHAGIAN_1 = (String)view.get("EMEL_BAHAGIAN_1");
			 		//String EMEL_BAHAGIAN_2 = (String)view.get("EMEL_BAHAGIAN_2");
			 		//String EMEL_BAHAGIAN_3 = (String)view.get("EMEL_BAHAGIAN_3");
			 		//String EMEL_BAHAGIAN_4 = (String)view.get("EMEL_BAHAGIAN_4");
			 		
			 		
			 		//String content_maklumat_aduan = "*infoaduan - amik maklumat asas - buat dalam table lek lok*";
			 		String content_maklumat_aduan =  returnHTMLMaklumatAduan(session,view,db);
			 		
			 		
			 		
					myLogger.info("MASUK FUNCTION EMEL ADUAN");				
					EmailProperty pro = EmailProperty.getInstance();
					EmailSender email = EmailSender.getInstance();
					email.FROM = pro.getFrom();
					email.MULTIPLE_RECIEPIENT = new String[1];
					String penutup ="";
					if(selectedLanguage.equals("ENGLISH"))
					{
						//convert version melayu to english
						penutup = "<br><br>Note: This email generated by MyeTaPP that requires no response. ";
					}
					else
					{
						penutup = "<br><br>Nota : Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.";
					}
							
					String subject = "";
					//String subject_terimaan = "";//untuk akuan terima
					String content = "";
					//String content_terimaan = "";
					
					if(ID_STATUS.equals("16121"))//log baru
					{
						if(Flag_Akuan_terima.equals("Y"))
						{
							String subject_akuan_terima = "LOG DITERIMA";
							if(selectedLanguage.equals("ENGLISH"))
							{
								subject_akuan_terima = "LOG RECEIVED";
							}
							subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+subject_akuan_terima+"";
						}
						else
						{
							subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+"";
						}
						
					}
					else if(ID_STATUS.equals("16122"))// status : dalam tindakan unit intergriti
					{
						/*
						if(selectedLanguage.equals("ENGLISH"))
						{
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+"";
							//convert version melayu to english
						}
						else
						{
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+"";
							//content += content_maklumat_aduan;
						}
						*/			
						subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+"";
					}
					else if(ID_STATUS.equals("16126"))//hantar untuk tindakan bahagian
					{
						/*
						if(selectedLanguage.equals("ENGLISH"))
						{
							//convert version melayu to english
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
						}
						else
						{
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
							//content += content_maklumat_aduan;
						}
						*/
						subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
						
						FLAG_CC_PENGADU = "Y";
						FLAG_CC_PENGARAH_BAHAGIAN = "Y";
						
					}
					else if(ID_STATUS.equals("16127"))// status : dalam tindakan BAHAGIAN
					{
						/*
						if(selectedLanguage.equals("ENGLISH"))
						{
							//convert version melayu to english
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
						}
						else
						{
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
							//content += content_maklumat_aduan;
						}
						*/
						subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+" ["+NAMA_BAHAGIAN+", "+NEGERI_BAHAGIAN+"]  ";
					}
					else if(ID_STATUS.equals("16123"))// status : selesai
					{
						/*
						if(selectedLanguage.equals("ENGLISH"))
						{
							//convert version melayu to english
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ";
						}
						else
						{
							subject += " LOG("+JENIS_ADUAN+") "+NO_ADUAN+" : "+STATUS+" ";
							
						}
						*/	
						subject += "LOG ["+NO_ADUAN+" - "+JENIS_ADUAN+"] : "+STATUS+"";
					}
					
					
					
					myLogger.info(" EMEL PENERIMA : "+PENERIMA);
					content += content_maklumat_aduan;
					email.MULTIPLE_RECIEPIENT = PENERIMA;	
					
					List<String> emel_cc = new ArrayList<String>();				
					
					if(FLAG_CC_PENGADU.equals("Y"))
					{
						emel_cc.add(EMEL_PENGADU);	
					}
					
					List listPengawaiIkutJawatan = null;
					if(FLAG_CC_PENGARAH_BAHAGIAN.equals("Y"))
					{
						//get pengarah bahagian di hq
						listPengawaiIkutJawatan = listPengawaiIkutJawatan(session,ID_BAHAGIAN,"16","4", db);
						for(int i = 0; i < listPengawaiIkutJawatan.size();i++)
						{
							Map m = (Map) listPengawaiIkutJawatan.get(i);
							String emel_peg = (String) m.get("EMEL");
							myLogger.info("EMEL PENGARAH untuk DI CC : "+emel_peg);
							emel_cc.add(emel_peg);	
						}
					}
					
					
									
					email.TO_CC = new String[emel_cc.size()];
					for(int i = 0; i < emel_cc.size();i++)
					{
						email.TO_CC[i] = emel_cc.get(i);
					}
					
					
					
					
					
					email.SUBJECT = subject;
					email.MESSAGE = content + penutup;		
					email.sendEmail();	
					
					
					
			 	}
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
		    private void saveLampiran(HttpSession session,String ID_ADUANPUBLIC,String DOC_TYPE,Db db) throws Exception {
			   	 
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
		        	  myLogger.info(" ID_MESYUARATUTAMA : "+ID_ADUANPUBLIC+" item : "+item);
		        	  saveLampiranDB(item,ID_ADUANPUBLIC,DOC_TYPE,db);
		        	 
		          }
		        }
		      }
		    
		    
		    private void saveLampiranDB(FileItem item,String ID_ADUANPUBLIC,String DOC_TYPE,Db db) throws Exception {
			    HttpSession session = request.getSession();	
			    String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		  		Db db1 = null;
		        try {	
		        	if(db==null)
		        	{
		        		db1 = new Db();
		        	}
		        	else
		        	{
		        		db1 = db;
		        	}
		        	
		        	long id_lampiran = DB.getNextID(db1,"TBLADUANPUBLICLAMPIRAN_SEQ");	        			        	
		        	Connection con = db1.getConnection();
		        	con.setAutoCommit(false);
		        	SQLRenderer r = new SQLRenderer();
		        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLADUANPUBLICLAMPIRAN ( "+
			    		  " ID_ADUANPUBLICLAMPIRAN, ID_ADUANPUBLIC, CONTENT,  "+
			    		  " NAMA_LAMPIRAN, JENIS_MIME, ID_MASUK,  "+
			    		  " TARIKH_MASUK,DOC_TYPE)  "+
			    		  " VALUES (?,?,?,?,?,?,SYSDATE,?) ");
		        	
		        	
		        	String nama_asal_doc = item.getName().substring(0, item.getName().indexOf("."));
		        	String nama_doc = nama_asal_doc;	        	
		        	int copy_count = 0;
		        	do {
	     			   // Statements
		        		myLogger.info(" ***** nama_doc ada duplicate : "+nama_doc);
		        		if(checkDuplicateDocName(session,"",ID_ADUANPUBLIC,nama_doc.toUpperCase(),DOC_TYPE,db1)==true)
		        		{
			        		copy_count++;
			        		nama_doc = nama_asal_doc + " COPY("+copy_count+")";
			        		myLogger.info(" ***** nama_doc baru : "+nama_doc);
		        		}
		        	}while(checkDuplicateDocName(session,"",ID_ADUANPUBLIC,nama_doc.toUpperCase(),DOC_TYPE,db1)==true);
		        	
		        	myLogger.info(" ***** nama_doc utk disimpan : "+nama_doc);
		        	myLogger.info(" ***** item utk disimpan : "+item.getContentType());
		        	
		        	ps.setString(1,id_lampiran+"");
		        	ps.setString(2,ID_ADUANPUBLIC);
		        	ps.setBinaryStream(3, item.getInputStream(),(int)item.getSize());
		        	ps.setString(4,nama_doc);
		        	ps.setString(5,item.getContentType());
		        	ps.setString(6,USER_ID_SYSTEM);
		        	ps.setString(7,DOC_TYPE);		        	
		        	ps.executeUpdate();	        	
		            con.commit(); 
		            
		            AuditTrail.logActivity(this,session,"INS","TBLPDTPANDANGANLAMPIRAN ["+item.getName()+"] Insert");
			    } finally {
			    	if(db==null)
		        	{
				      if (db1 != null) db1.close();
		        	}
			    }
			    
		  }		
		    
		    
		    @SuppressWarnings("unchecked")
			public boolean checkDuplicateDocName(HttpSession session,String ID_ADUANPUBLICLAMPIRAN,String ID_ADUANPUBLIC,String NAMA_DOC,String DOC_TYPE,Db db)throws Exception {
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
					sql = " SELECT COUNT(T.ID_ADUANPUBLICLAMPIRAN) AS CNT FROM TBLADUANPUBLICLAMPIRAN T  " +
							" WHERE T.ID_ADUANPUBLICLAMPIRAN IS NOT NULL ";
					
							if(!ID_ADUANPUBLICLAMPIRAN.equals(""))
							{
								sql += "  AND NVL(T.ID_ADUANPUBLICLAMPIRAN,0) <> '"+ID_ADUANPUBLICLAMPIRAN+"'  ";
							}
							
							sql += " AND UPPER(T.NAMA_LAMPIRAN) = '"+NAMA_DOC.toUpperCase()+"' AND T.DOC_TYPE = '"+DOC_TYPE+"' AND T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' ";
					
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
		 
		    @SuppressWarnings("unchecked")
			public List listLampiran(HttpSession session, String ID_ADUANPUBLIC,String DOC_TYPE, Db db)throws Exception {
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
					
					sql = " SELECT T.DOC_TYPE,T.ID_ADUANPUBLICLAMPIRAN, T.NAMA_LAMPIRAN, T.JENIS_MIME, "+
							" T.ID_ADUANPUBLIC, T.CONTENT, T.ID_MASUK, "+
							" T.ID_KEMASKINI, T.TARIKH_MASUK, T.TARIKH_KEMASKINI "+
							" FROM TBLADUANPUBLICLAMPIRAN T WHERE T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' AND T.DOC_TYPE = '"+DOC_TYPE+"' " +
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
						
						h.put("ID_ADUANPUBLICLAMPIRAN",rs.getString("ID_ADUANPUBLICLAMPIRAN") == null ? "" : rs.getString("ID_ADUANPUBLICLAMPIRAN"));	
						h.put("ID_ADUANPUBLIC",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC"));	
						h.put("CONTENT",rs.getString("CONTENT") == null ? "" : rs.getString("CONTENT"));	
						h.put("NAMA_LAMPIRAN",rs.getString("NAMA_LAMPIRAN") == null ? "" : rs.getString("NAMA_LAMPIRAN"));	
						h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));	
						h.put("DOC_TYPE",rs.getString("DOC_TYPE") == null ? "" : rs.getString("DOC_TYPE"));						
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
		    
		    public void deleteLampiran(HttpSession session,String ID_ADUANPUBLICLAMPIRAN,String NAMA_LAMPIRAN) throws Exception {
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
					r.add("ID_ADUANPUBLICLAMPIRAN",ID_ADUANPUBLICLAMPIRAN);
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
		 
		    
		    
		    
		    public void saveKronologi(HttpSession session,String ID_ADUANPUBLIC, String ID_STATUS,Db db) throws Exception {
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
					
					
					
					if(!ID_ADUANPUBLIC.equals(""))
					{
						r.clear();
						r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
						r.update("FLAG_AKTIF", 1);
						r.add("FLAG_AKTIF", 0);	
						sql = r.getSQLUpdate("TBLKRONOLOGIADUANPUBLIC");
						myLogger.info("ADUAN REMOVE AKTIF : "+sql);				
						stmt.executeUpdate(sql);
					}
					
					r.clear();
					r.add("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
					r.add("FLAG_AKTIF", 1);
					r.add("ID_STATUS", ID_STATUS);	
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLKRONOLOGIADUANPUBLIC");	
					myLogger.info("ADD STATUS AKTIF TBLKRONOLOGIADUANPUBLIC : "+sql);				
					stmt.executeUpdate(sql);
					
					r.clear();
					r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
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
					saveNotifikasi(session,ID_ADUANPUBLIC, ID_STATUS,db1);
					
					
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
		    
		    
		    public void saveNotifikasi(HttpSession session,String ID_ADUANPUBLIC, String ID_STATUS,Db db) throws Exception {
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
					
					Map view = view(session, ID_ADUANPUBLIC,"","",selectedLanguage,db);
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
				    String EMEL_BAHAGIAN_1 = (String)view.get("EMEL_BAHAGIAN_1");
			 		String EMEL_BAHAGIAN_2 = (String)view.get("EMEL_BAHAGIAN_2");
			 		String EMEL_BAHAGIAN_3 = (String)view.get("EMEL_BAHAGIAN_3");
			 		String EMEL_BAHAGIAN_4 = (String)view.get("EMEL_BAHAGIAN_4");
			 		
				    
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
								saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,user_id_pegawai_ui,ID_STATUS,db1,"insert");
								rep_listPegawaiUI[i] = emel_id_pegawai_ui;								
							}							
							
						    rep_listPegawaiUI = checkArray(rep_listPegawaiUI);						   
						    hantarEmel(session,ID_ADUANPUBLIC,rep_listPegawaiUI,ID_STATUS,db1);
						  //EMEL KEPADA UI
						}
						
						String[] rep_emel_akuan_terima = new String[1];
						rep_emel_akuan_terima[0] = EMEL_PENGADU;
						hantarEmel(session,ID_ADUANPUBLIC,rep_emel_akuan_terima,ID_STATUS,db1,"Y");
						
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
							saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,ID_PENGADU,ID_STATUS,db1,"insert");
							rep_pemohon[0] = EMEL_PENGADU;	
							//EMEL KEPADA PEMOHON
						}
						rep_pemohon = checkArray(rep_pemohon);
						//hantarEmel(session,ID_ADUANPUBLIC,rep_pemohon,ID_STATUS,db1);
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
							saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,user_id_pegawai_bahagian,ID_STATUS,db1,"insert");
							rep_listPegawaiBahagian[i] = emel_id_pegawai_bahagian;
							//EMEL KEPADA bahagian
						}
						rep_listPegawaiBahagian = checkArray(rep_listPegawaiBahagian);
						hantarEmel(session,ID_ADUANPUBLIC,rep_listPegawaiBahagian,ID_STATUS,db1);
					}
					else if(ID_STATUS.equals("16127"))//DALAM TINDAKAN BAHAGIAN
					{
						saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,ID_PEGAWAI_UI,ID_STATUS,db1,"insert");
						String[] rep_pegawai_ui = new String[1];
						rep_pegawai_ui[0] = EMEL_PEGAWAI_UI;
						rep_pegawai_ui = checkArray(rep_pegawai_ui);
						//hantarEmel(session,ID_ADUANPUBLIC,rep_pegawai_ui,ID_STATUS,db1);
						//emel kepada pegawai ui
					}
					else if(ID_STATUS.equals("16123"))//SELESAI
					{
						int size_rep = 2;
						if(!EMEL_BAHAGIAN_1.equals(""))
						{
							size_rep++;
						}
						if(!EMEL_BAHAGIAN_2.equals(""))
						{
							size_rep++;
						}
						if(!EMEL_BAHAGIAN_3.equals(""))
						{
							size_rep++;
						}
						if(!EMEL_BAHAGIAN_4.equals(""))
						{
							size_rep++;
						}
						
						
						
						String[] rep_pegawai_ui_pemohon = new String[size_rep];
						if(!ID_PEGAWAI_BAHAGIAN.equals(""))
						{
							saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,ID_PEGAWAI_UI,ID_STATUS,db1,"insert");
							rep_pegawai_ui_pemohon[0] = EMEL_PEGAWAI_UI;
							if(!ID_PENGADU.equals(""))
							{
								saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,ID_PENGADU,ID_STATUS,db1,"insert");
								rep_pegawai_ui_pemohon[1] = EMEL_PENGADU;	
							}
							//emel tu pengadu ngn pegawai ui
							
						}
						else
						{
							saveTableNotifikasi(session,ID_ADUANPUBLIC,USER_ID_SYSTEM,ID_PENGADU,ID_STATUS,db1,"insert");
							rep_pegawai_ui_pemohon[0] = EMEL_PENGADU;	
							//emel tu pengadu
						}
						
						if(!EMEL_BAHAGIAN_1.equals(""))
						{
							rep_pegawai_ui_pemohon[2] = EMEL_BAHAGIAN_1;	
						}
						if(!EMEL_BAHAGIAN_2.equals(""))
						{
							rep_pegawai_ui_pemohon[3] = EMEL_BAHAGIAN_2;
						}
						if(!EMEL_BAHAGIAN_3.equals(""))
						{
							rep_pegawai_ui_pemohon[4] = EMEL_BAHAGIAN_3;
						}
						if(!EMEL_BAHAGIAN_4.equals(""))
						{
							rep_pegawai_ui_pemohon[5] = EMEL_BAHAGIAN_4;
						}
						
						rep_pegawai_ui_pemohon = checkArray(rep_pegawai_ui_pemohon);
						hantarEmel(session,ID_ADUANPUBLIC,rep_pegawai_ui_pemohon,ID_STATUS,db1);
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
			    	  myLogger.info("S:::: "+s); 
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
		    
		    
		    
		    public List listPengawaiIkutJawatan(HttpSession session,String ID_BAHAGIAN,String ID_NEGERI,String ID_JAWATAN, Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listPengawaiIkutJawatan = null;
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
					
					sql = " SELECT DISTINCT U.USER_ID,U.USER_LOGIN, UI.EMEL, U.USER_NAME, UI.ID_NEGERI, UI.ID_SEKSYEN, J.KETERANGAN, J.ID_JAWATAN,UI.FLAG_AKTIF   "+
							" FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J   "+
							" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN  AND UI.EMEL IS NOT NULL  "+
							" AND UI.ID_NEGERI = '"+ID_NEGERI+"' AND UI.ID_SEKSYEN = '"+ID_BAHAGIAN+"'   "+
							" AND UI.ID_JAWATAN = '"+ID_JAWATAN+"' AND NVL(UI.FLAG_AKTIF,' ') IN (' ', '1') "; 
					
					myLogger.info(" SQL : listPengawaiIkutJawatan :"+ sql);			
					rs = stmt.executeQuery(sql);
					listPengawaiIkutJawatan = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());	
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
						listPengawaiIkutJawatan.add(h);
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
				return listPengawaiIkutJawatan;
			}
		    
		    
		    public List listNotifikasi(HttpSession session,String USER_ID,String ID_STATUS, Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listNotifikasi = null;
				String sql = "";
				String selectedLanguage = (String) session.getAttribute("selectedLanguage");
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
					
					sql = " SELECT DISTINCT N.ID_TERIMA, P.ID_STATUS,S.KETERANGAN,S.KETERANGAN_ENG, S.LANGKAH, " +
						   " COUNT(N.ID_ADUANPUBLICNOTIFIKASI) AS CNT " +
						   " FROM TBLADUANPUBLICNOTIFIKASI N, TBLADUANPUBLIC P, TBLRUJSTATUSADUANPUBLIC S " +
						   " WHERE N.ID_ADUANPUBLIC = P.ID_ADUANPUBLIC AND P.ID_STATUS = S.ID_STATUS AND P.ID_STATUS = N.ID_STATUS  " +
						   " AND N.FLAG_READ = 'N' ";					
						   sql += " AND N.ID_TERIMA = '"+USER_ID+"' "; 						   
						   if(!ID_STATUS.equals(""))
						   {
							   sql += " AND P.ID_STATUS = '"+ID_STATUS+"' "; 
						   }					
					sql += " GROUP BY N.ID_TERIMA, P.ID_STATUS,S.KETERANGAN,S.KETERANGAN_ENG, S.LANGKAH " +
						   " ORDER BY S.LANGKAH  ";
					
					myLogger.info(" SQL : listNotifikasi :"+ sql);			
					rs = stmt.executeQuery(sql);
					listNotifikasi = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("ID_TERIMA",rs.getString("ID_TERIMA") == null ? "" : rs.getString("ID_TERIMA"));	
						h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));						
						String KETERANGAN = rs == null ? "" : (rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						if(selectedLanguage.equals("ENGLISH"))
						{
							KETERANGAN = rs == null ? "" : (rs.getString("KETERANGAN_ENG") == null ? "" : rs.getString("KETERANGAN_ENG").toUpperCase());
						}
						h.put("KETERANGAN",KETERANGAN);						
						h.put("CNT",rs.getString("CNT") == null ? 0 : rs.getInt("CNT"));	
						listNotifikasi.add(h);
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
				return listNotifikasi;
			}
		    
		    
		    public void saveTableNotifikasi(HttpSession session,String ID_ADUANPUBLIC,String ID_HANTAR,String ID_TERIMA, String ID_STATUS,Db db,String flag) throws Exception {
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
						r.add("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
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
						//String id_current_status = getCurrentStatus(session, ID_ADUANPUBLIC, db1);
						r.clear();
						r.update("ID_ADUANPUBLIC", ID_ADUANPUBLIC);
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
					/*
					else if(flag.equals("flagNotifikasi"))
					{
						r.clear();
						r.update("FLAG_READ", "N");
						r.update("ID_TERIMA", ID_TERIMA);
						r.update("ID_STATUS", ID_STATUS);
						r.add("FLAG_READ", "Y");						
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("TBLADUANPUBLICNOTIFIKASI");
					}
					*/
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
		    
		    
		    
		    public boolean checkStatusExist(HttpSession session, String ID_ADUANPUBLIC, String ID_STATUS, Db db) throws Exception {
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
					
					sql = " SELECT T.ID_KRONOLOGI, T.ID_ADUANPUBLIC, T.ID_STATUS, T.FLAG_AKTIF, T.ID_MASUK, T.TARIKH_MASUK " +
							" FROM TBLKRONOLOGIADUANPUBLIC T WHERE T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' AND T.ID_STATUS = '"+ID_STATUS+"' ";	
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
		    
		    
		    public String getCurrentStatus(HttpSession session, String ID_ADUANPUBLIC, Db db) throws Exception {
				Db db1 = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				String ID_STATUS = "";
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
					
					sql = " SELECT T.ID_STATUS FROM TBLADUANPUBLIC T WHERE T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' ";	
					//myLogger.info(" ADUAN : getCurrentStatus :" + sql.toUpperCase());
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {								
							ID_STATUS = rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS");							
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
				return ID_STATUS;
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
			
		    
		    @SuppressWarnings("unchecked")
			public List listKronologi(HttpSession session, String ID_ADUANPUBLIC, Db db)throws Exception {
		    	String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		    	String id_current_status = getCurrentStatus(session, ID_ADUANPUBLIC, db);
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listKronologi = null;
				long totalseconds = 0;
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
					
					sql = " SELECT ID_KRONOLOGI,ID_ADUANPUBLIC,NO_ADUAN,ID_STATUS, "+
							" KETERANGAN,KETERANGAN_ENG,FLAG_AKTIF, "+
							" USER_ID,TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY HH:MI:SS AM') AS TARIKH_MASUK,USER_NAME, LANGKAH   "+
							" FROM ( "+
							" SELECT T.ID_KRONOLOGI, T.ID_ADUANPUBLIC, P.NO_ADUAN,   "+
							" T.ID_STATUS, S.KETERANGAN, S.KETERANGAN_ENG,  "+ 
							" T.FLAG_AKTIF, U.USER_ID, T.TARIKH_MASUK, U.USER_NAME,S.LANGKAH   "+
							" FROM TBLKRONOLOGIADUANPUBLIC T, TBLRUJSTATUSADUANPUBLIC S,  "+
							" USERS U, TBLADUANPUBLIC P   "+
							" WHERE T.ID_STATUS = S.ID_STATUS  "+
							" AND T.ID_MASUK = U.USER_ID  "+
							" AND T.ID_ADUANPUBLIC = P.ID_ADUANPUBLIC  "+
							" AND T.ID_STATUS NOT IN ('16125')  "+
							" AND T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"' ";
							if(!id_current_status.equals("16123"))
							{
								sql += " UNION ";
								sql += " SELECT NULL AS ID_KRONOLOGI,NULL AS ID_ADUANPUBLIC, NULL AS NO_ADUAN,ID_STATUS, "+
								" KETERANGAN,KETERANGAN_ENG,NULL AS FLAG_AKTIF, "+
								" NULL AS USER_ID,NULL AS TARIKH_MASUK, NULL AS USER_NAME, LANGKAH "+
								" FROM TBLRUJSTATUSADUANPUBLIC WHERE ID_STATUS NOT IN  "+
								" (SELECT T.ID_STATUS   "+
								" FROM TBLKRONOLOGIADUANPUBLIC T, TBLRUJSTATUSADUANPUBLIC S,  "+
								" USERS U, TBLADUANPUBLIC P   "+
								" WHERE T.ID_STATUS = S.ID_STATUS  "+
								" AND T.ID_MASUK = U.USER_ID  "+
								" AND T.ID_ADUANPUBLIC = P.ID_ADUANPUBLIC  "+
								" AND T.ID_STATUS NOT IN ('16125')  "+
								" AND T.ID_ADUANPUBLIC = '"+ID_ADUANPUBLIC+"') ";
							}
							sql += " ) WHERE ID_STATUS NOT IN ('16125','16124') "+
							" ORDER BY LANGKAH "; 
					
					myLogger.info(" SQL : listKronologi :"+ sql);			
					rs = stmt.executeQuery(sql);
					listKronologi = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
					Date date_sebelum= null;
					boolean getLastAktivitiDate = false;
					
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
						h.put("LAST_AKTIVITI",false);
						h.put("ID_KRONOLOGI",rs.getString("ID_KRONOLOGI") == null ? "" : rs.getString("ID_KRONOLOGI"));
						h.put("ID_ADUANPUBLIC",rs.getString("ID_ADUANPUBLIC") == null ? "" : rs.getString("ID_ADUANPUBLIC"));
						h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
						String KETERANGAN = rs == null ? "" : (rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						if(selectedLanguage.equals("ENGLISH"))
						{
							KETERANGAN = rs == null ? "" : (rs.getString("KETERANGAN_ENG") == null ? "" : rs.getString("KETERANGAN_ENG").toUpperCase());
						}
						h.put("KETERANGAN",KETERANGAN);						
						h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("TIME_TAKEN","");
						String tarikh_transaksi = rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK");
						//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss am") ;
						//DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						//inputFormat.parse(tarikh_transaksi);
						//df.parse(tarikh_transaksi);
						
						//myLogger.info(" atas tarikh_transaksi : "+tarikh_transaksi);
						//x;
						if(date_sebelum==null)
						{
							h.put("TIME_TAKEN","");
							
							if(!tarikh_transaksi.equals(""))
							{
								date_sebelum = formatter.parse(tarikh_transaksi);
								myLogger.info("1 : 1st date_sebelum : "+formatter.format(date_sebelum)+" string format : "+formatter.format(date_sebelum) );
							}
						}
						else
						{
							if(!tarikh_transaksi.equals(""))
							{
								Date date_current = formatter.parse(tarikh_transaksi);							
								long duration  = date_current.getTime() - date_sebelum.getTime();
								long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);									
								myLogger.info("2: next date_sebelum : "+formatter.format(date_sebelum)+" date_current : "+formatter.format(date_current)+" diffInSeconds :"+diffInSeconds);
								Hashtable h_trasnsaksi = convertTime(session, diffInSeconds);
								String display_h_trasnsaksi = (String)h_trasnsaksi.get("display");								
								h.put("TIME_TAKEN",display_h_trasnsaksi);
								date_sebelum = date_current;
								totalseconds += diffInSeconds;
							}
							else
							{
								
								if(getLastAktivitiDate==false)
								{
									Date actual_date = new Date();
									long duration  = actual_date.getTime() - date_sebelum.getTime();
									long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);									
									myLogger.info("3: next date_sebelum : "+formatter.format(date_sebelum)+" date_current : "+formatter.format(actual_date)+" diffInSeconds :"+diffInSeconds);
									
									
									Hashtable h_last_trasnsaksi = convertTime(session, diffInSeconds);
									String display_h_last_trasnsaksi = (String)h_last_trasnsaksi.get("display");									
									this.context.put("lt_show_day", (int)h_last_trasnsaksi.get("show_day"));
									this.context.put("lt_show_hour", (long)h_last_trasnsaksi.get("show_hour"));
									this.context.put("lt_show_minute", (long)h_last_trasnsaksi.get("show_minute"));
									this.context.put("lt_show_second", (long)h_last_trasnsaksi.get("show_second"));
									this.context.put("TIME_TAKEN", display_h_last_trasnsaksi);
									//h.put("TIME_TAKEN",convertTime(session, diffInSeconds,"last"));
									totalseconds += diffInSeconds;
									getLastAktivitiDate = true;
									h.put("LAST_AKTIVITI",true);
								}
								else
								{
									h.put("TIME_TAKEN","");
								}
							}
						}
											
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME").toUpperCase());
						h.put("LANGKAH",rs.getString("LANGKAH") == null ? 0 : rs.getInt("LANGKAH"));
						
						listKronologi.add(h);
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
				
				Hashtable h_overall = convertTime(session, totalseconds);
				String display_overall = (String)h_overall.get("display");
				this.context.put("OVERALL_TIME_TAKEN", display_overall);
				this.context.put("ov_show_day", (int)h_overall.get("show_day"));
				this.context.put("ov_show_hour", (long)h_overall.get("show_hour"));
				this.context.put("ov_show_minute", (long)h_overall.get("show_minute"));
				this.context.put("ov_show_second", (long)h_overall.get("show_second"));
				
				return listKronologi;
			}
		    
		    
		   		    
		    
		    public Hashtable convertTime(HttpSession session, long seconds) {
		    	 String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		    	 String display = "";
		    	 int show_day = 0;
		    	 long show_hour = 0;
		    	 long show_minute = 0;
		    	 long show_second = 0;		
		    	 
		    	 int day = (int)TimeUnit.SECONDS.toDays(seconds); 
		    	 if(day>=0)
		    	 {
		    		show_day = day;
		    		if(day>0)
			    	{		    		
			    		if(selectedLanguage.equals("ENGLISH"))
			    		{
			    			 if(day==1)
			    			 {
			    				 display += day+" Day ";
			    			 }
			    			 else
			    			 {
			    				 display += day+" Days ";
			    			 }
			    		}
			    		else
			    		{
			    			display += day+" Hari ";
			    		}
			    	}
		    	 }
		    	 long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
		    	 if(hours>=0)
		    	 {
		    		show_hour = hours; 
		    		if(hours>0)
			    	{
			    		if(selectedLanguage.equals("ENGLISH"))
			    		{
			    			 if(hours==1)
			    			 {
			    				 display += hours+" Hour ";
			    			 }
			    			 else
			    			 {
			    				 display += hours+" Hours ";
			    			 }
			    		}
			    		else
			    		{
			    			display += hours+" Jam ";
			    		}
			    	}
		    	 }
		    	 long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
		    	 if(minute>=0)
		    	 {
		    		show_minute = minute; 
		    		if(minute>0)
			    	{
			    		if(selectedLanguage.equals("ENGLISH"))
			    		{
			    			 if(minute==1)
			    			 {
			    				 display += minute+" Minute ";
			    			 }
			    			 else
			    			 {
			    				 display += minute+" Minutes ";
			    			 }
			    		}
			    		else
			    		{
			    			display += minute+" Minit ";
			    		}
			    	}
		    	 }
		    	 long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);
		    	 if(second>=0)
		    	 {
		    		show_second = second;
		    		if(second>0)
			    	{
			    		if(selectedLanguage.equals("ENGLISH"))
			    		{
			    			 if(second==1)
			    			 {
			    				 display += second+" Second ";
			    			 }
			    			 else
			    			 {
			    				 display += second+" Seconds ";
			    			 }
			    		}
			    		else
			    		{
			    			display += second+" Saat ";
			    		}
			    	}
		    	 }
		    	 
		    	 
		    	 Hashtable h;
				 h = new Hashtable();				 
				 h.put("show_day", show_day);
				 h.put("show_hour", show_hour);
				 h.put("show_minute", show_minute);
				 h.put("show_second", show_second);
				 h.put("display", display);
		    	 
				 /*
		    	 if(flag_display.equals("overall"))
		    	 {
		    		 this.context.put("ov_show_day", show_day);
		    		 this.context.put("ov_show_hour", show_hour);
		    		 this.context.put("ov_show_minute", show_minute);
		    		 this.context.put("ov_show_second", show_second);
		    	 }		    	 
		    	 if(flag_display.equals("last"))
		    	 {
		    		 this.context.put("lt_show_day", show_day);
		    		 this.context.put("lt_show_hour", show_hour);
		    		 this.context.put("lt_show_minute", show_minute);
		    		 this.context.put("lt_show_second", show_second);
		    	 }		    	 
		    	 return display;
		    	 */
				 return h;
		    }
		    
		    public String returnHTMLMaklumatAduan(HttpSession session,Map MaklumatAduan,Db db)
		    {
		    	String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		    	
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
		 		
		 		String EMEL_BAHAGIAN_1 = (String)view.get("EMEL_BAHAGIAN_1");
		 		String EMEL_BAHAGIAN_2 = (String)view.get("EMEL_BAHAGIAN_2");
		 		String EMEL_BAHAGIAN_3 = (String)view.get("EMEL_BAHAGIAN_3");
		 		String EMEL_BAHAGIAN_4 = (String)view.get("EMEL_BAHAGIAN_4");
		 		
		 		String NEGERI_PT = (String)view.get("NEGERI_PT");
		 		String DAERAH_PT = (String)view.get("DAERAH_PT");
		 		String PEJABAT_PT = (String)view.get("PEJABAT_PT");
		 		
		 		
		 		/*
		 		h.put("LUAR_TINDAKAN",LUAR_TINDAKAN);
			
			
			h.put("ID_PEJABATTANAH",rs == null ? "" : (rs.getString("ID_PEJABATTANAH") == null ? "" : rs.getString("ID_PEJABATTANAH").toUpperCase()));
			h.put("ID_NEGERI_PT",rs == null ? "" : (rs.getString("ID_NEGERI_PT") == null ? "" : rs.getString("ID_NEGERI_PT").toUpperCase()));
			h.put("ID_DAERAH_PT",rs == null ? "" : (rs.getString("ID_DAERAH_PT") == null ? "" : rs.getString("ID_DAERAH_PT").toUpperCase()));
			h.put("NEGERI_PT",rs == null ? "" : (rs.getString("NEGERI_PT") == null ? "" : rs.getString("NEGERI_PT").toUpperCase()));
			h.put("DAERAH_PT",rs == null ? "" : (rs.getString("DAERAH_PT") == null ? "" : rs.getString("DAERAH_PT").toUpperCase()));
			h.put("PEJABAT_PT",rs == null ? "" : (rs.getString("PEJABAT_PT") == null ? "" : rs.getString("PEJABAT_PT").toUpperCase()));
					 		
		 		*/
		 		
		 		String FLAG_LUAR_TINDAKAN = (String)view.get("FLAG_LUAR_TINDAKAN");
		 		
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
		    			maklumat += returnTDTR(session,"label_emel_pengadu",EMEL_PENGADU+";","content");
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
			    		
			    		
			    		if(!NEGERI_PT.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_negeri_pt",NEGERI_PT,"content");
			    		}
			    		if(!DAERAH_PT.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_daerah_pt",DAERAH_PT,"content");
			    		}
			    		if(!PEJABAT_PT.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_pejabat_pt",PEJABAT_PT,"content");
			    		}
			    		
			    		
			    		if(!NEGERI_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_negeri_bahagian",NEGERI_BAHAGIAN,"content");
			    		}
			    		if(!NAMA_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_tindakan_bahagian",NAMA_BAHAGIAN,"content");
			    		}
			    		if(!NAMA_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_tindakan_bahagian",NAMA_BAHAGIAN,"content");
			    		}			    		
			    		
			    		if(!EMEL_BAHAGIAN_1.equals("") || !EMEL_BAHAGIAN_2.equals("") || !EMEL_BAHAGIAN_3.equals("") || !EMEL_BAHAGIAN_4.equals(""))
			    		{
			    			String listEmel = "";
			    			if(!EMEL_BAHAGIAN_1.equals(""))
			    			{
			    				listEmel += EMEL_BAHAGIAN_1+"; ";
			    			}
			    			if(!EMEL_BAHAGIAN_2.equals(""))
			    			{
			    				listEmel += EMEL_BAHAGIAN_2+"; ";
			    			}
			    			if(!EMEL_BAHAGIAN_3.equals(""))
			    			{
			    				listEmel += EMEL_BAHAGIAN_3+"; ";
			    			}
			    			if(!EMEL_BAHAGIAN_4.equals(""))
			    			{
			    				listEmel += EMEL_BAHAGIAN_4+"; ";
			    			}
			    			maklumat += returnTDTR(session,"label_emel_bahagian",listEmel,"content");
			    		}
			    		
			    		if(!SUMBER_BAHAGIAN.equals(""))
			    		{
			    			maklumat += returnTDTR(session,"label_sumber_bahagian",SUMBER_BAHAGIAN,"content");
			    		}
			    		
			    		String LUAR_TINDAKAN = ""; 
			    		if(FLAG_LUAR_TINDAKAN.equals("Y"))
			    		{			    			
					    	if(selectedLanguage.equals("ENGLISH"))
				      		{
					    		LUAR_TINDAKAN = "YES";						
				      		}
				      		else
				      		{
				      			LUAR_TINDAKAN = "YA";
				      		}
			    		}
			    		else
			    		{
			    			if(selectedLanguage.equals("ENGLISH"))
				      		{
					    		LUAR_TINDAKAN = "NO";						
				      		}
				      		else
				      		{
				      			LUAR_TINDAKAN = "TIDAK";
				      		}
			    		}
			    		maklumat += returnTDTR(session,"label_luar_tindakan",LUAR_TINDAKAN,"content");
			    		
			    		
			    		
			    		
			    		
			    		
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
		    
		    
		    
		    /*
		    User Public : 840819071111 
			User UI : 840819075337
			User Bahagian : 840819075335 (selangor ppt)
			USER BAHAGian : 840819075339 (kedah ppk)		
		    */

		    
		    
		    
}