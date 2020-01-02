package etapp.template;
import java.sql.Connection;
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
import ekptg.helpers.Paging2;

public class ModulesManagement extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(ModulesManagement.class);
	String skrin_name = "app/admin/ModuleEditor/index.jsp";	
	@Override
	public String doTemplate2() throws Exception {
		List listModule = null;
		List listGroup = null;
		Hashtable viewModul = null;
		List listDropDownGroup = null;
		List listRoleByModule = null;
		List listRoleBySaveModule = null;
		
		defaultPut();
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		String command2 = getParam("command2");
		myLogger.info("MM command : "+command);
		this.context.put("command",command);
		
		if(command.equals("upload"))
		{
			uploadFiles();
			skrin_name = "app/admin/ModuleEditor/testUpload.jsp";
		}
		
		
		
		else if(command.equals("carianUtama"))
		{
			if(getParam("FLAG_UPDATE_GROUP").equals("Y"))
			{
				String MODULE_GROUP_TRIM = getParam("MODULE_GROUP");
				this.context.put("MODULE_GROUP",MODULE_GROUP_TRIM);
				String MODULE_GROUP_ASAL = getParam("MODULE_GROUP_ASAL");
				this.context.put("MODULE_GROUP_ASAL",MODULE_GROUP_ASAL);
				String textGroup = getParam("textGroup"+MODULE_GROUP_TRIM);
				myLogger.info(" textGroup :"+ textGroup);
				updateGroup(session,MODULE_GROUP_ASAL,textGroup);
			}
			
			String carianTerperinci = getParam("carianTerperinci");
			listGroup = listGroupModule(session, carianTerperinci);
			this.context.put("listGroup",listGroup);
			skrin_name = "app/admin/ModuleEditor/SenaraiUtama.jsp";
		}
		
		else if(command.equals("editGroup"))
		{
			String MODULE_GROUP_TRIM = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP_TRIM);
			String MODULE_GROUP_ASAL = getParam("MODULE_GROUP_ASAL");
			this.context.put("MODULE_GROUP_ASAL",MODULE_GROUP_ASAL);
			myLogger.info("MODULE_GROUP_ASAL : "+MODULE_GROUP_ASAL);
			myLogger.info("MODULE_GROUP_TRIM : "+MODULE_GROUP_TRIM);
			skrin_name = "app/admin/ModuleEditor/editGroup.jsp";
		}
		else if(command.equals("save_AddRole"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID_ASAL = getParam("MODULE_ID_ASAL");
			this.context.put("MODULE_ID_ASAL",MODULE_ID_ASAL);
			String MODULE_ID_TRIM = getParam("MODULE_ID_TRIM");
			this.context.put("MODULE_ID_TRIM",MODULE_ID_TRIM);
			
			//this.context.put("SuccessMesejRole", "");
			//this.context.put("SuccessMesejRole", 0+" Peranan/Role Telah Didaftarkan Kepada '"+MODULE_ID_ASAL+"'");
			
			String[] TEMP_GROUP_CHECKLIST = request.getParameterValues("TEMP_GROUP_CHECKLIST_"+MODULE_GROUP+MODULE_ID_TRIM);			
			
			int total_role_update = 0;
			total_role_update += simpanAdditionalRoles(session,MODULE_ID_ASAL, MODULE_GROUP, MODULE_ID_TRIM, TEMP_GROUP_CHECKLIST);
			
			
			if(total_role_update>=0)
			{
				this.context.put("SuccessMesejRole", total_role_update+" Peranan/Role Telah Didaftarkan Kepada '"+MODULE_ID_ASAL+"'");
			}
			else
			{
				this.context.put("SuccessMesejRole", "");
			}
			
			
			listRoleBySaveModule = listRoleBySaveModule(session,MODULE_ID_ASAL);
			this.context.put("listRoleBySaveModule",listRoleBySaveModule);
			skrin_name = "app/admin/ModuleEditor/editRole.jsp";
		}
		else if(command.equals("edit_AddRole"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID_ASAL = getParam("MODULE_ID_ASAL");
			this.context.put("MODULE_ID_ASAL",MODULE_ID_ASAL);
			String MODULE_ID_TRIM = getParam("MODULE_ID_TRIM");
			this.context.put("MODULE_ID_TRIM",MODULE_ID_TRIM);
			listRoleBySaveModule = listRoleBySaveModule(session,MODULE_ID_ASAL);
			this.context.put("listRoleBySaveModule",listRoleBySaveModule);
			skrin_name = "app/admin/ModuleEditor/editRole.jsp";
		}
		else if(command.equals("showDisplayRole"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID_ASAL = getParam("MODULE_ID");
			this.context.put("MODULE_ID_ASAL",MODULE_ID_ASAL);
			String MODULE_ID_TRIM = getParam("MODULE_ID_TRIM");
			this.context.put("MODULE_ID_TRIM",MODULE_ID_TRIM);			
			listRoleByModule = listRoleBySaveModule_Selected(session,MODULE_ID_ASAL);
			this.context.put("listRoleByModule",listRoleByModule);
			skrin_name = "app/admin/ModuleEditor/showDisplayRole.jsp";
		}
		else if(command.equals("batalCarianUtama"))
		{
			skrin_name = "app/admin/ModuleEditor/blank_carian.jsp";
		}	
		else if(command.equals("deleteModule"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID = getParam("MODULE_ID");
			skrin_name = "app/admin/ModuleEditor/blank_viewModul.jsp";
		}
		else if(command.equals("viewModule"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID = getParam("MODULE_ID");
			viewModul = viewModule(session,MODULE_ID);
			this.context.put("viewModul",viewModul);
			skrin_name = "app/admin/ModuleEditor/viewModul.jsp";
		}
		else if(command.equals("closeModule"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			skrin_name = "app/admin/ModuleEditor/blank_viewModul.jsp";
		}
		else if(command.equals("saveModule"))
		{
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID = getParam("MODULE_ID");
			String CURRENT_MODULE_ID_ = getParam("CURRENT_MODULE_ID_"+MODULE_GROUP+(MODULE_ID.replace(".","_").replace(" ","")));
			
			/*PENAMBAHAN ADMIN 18/1/2017
			 * 
			TAMAT*/
			String NEW_MODULE_ID = simpanModule(session,MODULE_GROUP,CURRENT_MODULE_ID_);			
			
			viewModul = viewModule(session,NEW_MODULE_ID);
			this.context.put("viewModul",viewModul);
			skrin_name = "app/admin/ModuleEditor/viewModul.jsp";
		}
		else if(command.equals("checkDuplicateModule"))
		{			
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID = getParam("MODULE_ID");
			this.context.put("MODULE_ID",MODULE_ID);
			String NEW_MODULE_ID = getParam("MODULE_ID_"+MODULE_GROUP+MODULE_ID);
			
			myLogger.info(" MODULE_GROUP :"+MODULE_GROUP);
			myLogger.info(" MODULE_ID :"+MODULE_ID);
			myLogger.info(" NEW_MODULE_ID :"+NEW_MODULE_ID);
			
			boolean checkModul = checkModul(session, MODULE_ID.trim(),NEW_MODULE_ID.trim());
			this.context.put("checkDuplicateModule",checkModul);
			
			skrin_name = "app/admin/ModuleEditor/CheckDuplicateModule.jsp";
		}
		else if(command.equals("editModule"))
		{
			listDropDownGroup = listDropDownGroup(session);
			this.context.put("listDropDownGroup",listDropDownGroup);
			String MODULE_GROUP = getParam("MODULE_GROUP");
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			String MODULE_ID = getParam("MODULE_ID");
			viewModul = viewModule(session,MODULE_ID);
			this.context.put("viewModul",viewModul);
			skrin_name = "app/admin/ModuleEditor/editModul.jsp";
		}
		else if(command.equals("showSenaraiModulByGroup"))
		{
			this.context.put("SuccessMesejDeleteModule", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");			
			if(FLAG_DELETE.equals("Y"))
			{
				String MODULE_ID = getParam("MODULE_ID");
				deleteModule(session,MODULE_ID); 
				this.context.put("SuccessMesejDeleteModule", "Maklumat Modul '"+MODULE_ID+"' Berjaya Dihapus");
			}
			
			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			String MODULE_GROUP = getParam("MODULE_GROUP");
			String BIL = getParam("BIL");
			this.context.put("BIL",BIL);
			this.context.put("MODULE_GROUP",MODULE_GROUP);
			
			
			
			String SHOWNEXT = getParam("SHOWNEXT");
			this.context.put("SHOWNEXT","");
			this.context.put("NEXT_BIL","");
			this.context.put("TOTAL_LIST","");
			if(!BIL.equals("") && SHOWNEXT.equals("Y"))
			{
				String TOTAL_LIST = getParam("TOTAL_LIST");
				Integer nextbil = Integer.parseInt(BIL)+1;
				this.context.put("SHOWNEXT","Y");
				if(nextbil <= Integer.parseInt(TOTAL_LIST))
				{
					this.context.put("NEXT_BIL",nextbil);
					this.context.put("TOTAL_LIST",TOTAL_LIST);					
				}
			}
			
			
			listModule = listModule(session, carianTerperinci,MODULE_GROUP);
			this.context.put("listModule",listModule);	
			
			myLogger.info("action : "+action);
			myLogger.info("carianTerperinci : "+carianTerperinci);
			myLogger.info("BIL : "+BIL);
			myLogger.info("MODULE_GROUP : "+MODULE_GROUP);
			
			setupPageList(session, action, listModule, "listModule",command,"div_Main"+BIL);
			
			skrin_name = "app/admin/ModuleEditor/showListModuleByGroup.jsp";
		}
		
		return skrin_name;
	}
	
	private void uploadFiles() throws Exception {
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
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	
	    	  myLogger.info("SIZE DOC :"+item.getSize());
	    	  if(item.getSize()>2097152)
	    	  {
	    		  context.put("alert_saiz","yes");
	    	  }
	    	  else
	    	  {
	    	  //saveData(item);			    	  
	    	  }
	      }
	    }
	  }
	
	public void defaultPut()
	{
		this.context.put("command","");
		this.context.put("carianTerperinci","");
		this.context.put("listModule","");
		this.context.put("listGroup","");
		this.context.put("viewModul","");
		this.context.put("listDropDownGroup","");
		this.context.put("checkDuplicateModule","");	
		this.context.put("SuccessMesej", "");
		this.context.put("listRoleByModule", "");
		this.context.put("listRoleBySaveModule", "");
		this.context.put("SuccessMesejRole", "");		
	}
	
	
	public void deleteModule(HttpSession session,String MODULE_ID) throws Exception {
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
			r.add("MODULE_ID", MODULE_ID);
			sql = r.getSQLDelete("ROLE_MODULE");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","ROLE MODULE ["+MODULE_ID+"] Deleted");
			
			r.clear();
			r.add("MODULE_ID", MODULE_ID);
			sql = r.getSQLDelete("MODULE");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","MODULE ["+MODULE_ID+"] Deleted");			

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
	
	public List listDropDownGroup(HttpSession session)throws Exception {
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listDropDownGroup = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
		
			sql = " SELECT DISTINCT M.MODULE_GROUP FROM MODULE M WHERE M.MODULE_GROUP IS NOT NULL ORDER BY MODULE_GROUP ";
			myLogger.info(" mm: SQL listDropDownGroup :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listDropDownGroup = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("MODULE_GROUP",rs.getString("MODULE_GROUP") == null ? "NONE" : rs.getString("MODULE_GROUP"));
				listDropDownGroup.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listDropDownGroup;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listModule(HttpSession session, String carianTerperinci,String MODULE_GROUP)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listModule = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
		
			sql = " SELECT 2 AS LAYER, " +
					"MODULE_ID AS MODULE_ID_ASAL, " +
					" replace(replace(MODULE_ID,'.', '_'),' ', '') AS MODULE_ID_TRIM, " +
					  " MODULE_TITLE, MODULE_CLASS, MODULE_GROUP, MODULE_DESCRIPTION, 0 AS TOTAL_MODULE FROM MODULE " +
					  " WHERE  MODULE_ID IS NOT NULL ";
				
				if(!carianTerperinci.equals(""))
				{
				sql += " AND (UPPER(MODULE_TITLE) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(MODULE_ID) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(MODULE_DESCRIPTION) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
								" OR UPPER(NVL(MODULE_GROUP,'NONE')) LIKE '%"+carianTerperinci.toUpperCase()+"%' ) "; 
				
						if(MODULE_GROUP.equals("NONE"))
						{
							sql += " AND MODULE_GROUP IS NULL ";
						}
						else
						{
							sql += " AND replace (MODULE_GROUP, ' ', '') = '"+MODULE_GROUP+"' ";
						}
				
				}
				else
				{
					if(MODULE_GROUP.equals("NONE"))
					{
						sql += " AND MODULE_GROUP IS NULL ";
					}
					else
					{
						sql += " AND replace ( MODULE_GROUP, ' ', '') = '"+MODULE_GROUP+"' ";
					}
				}
		
					
			sql +=	" ORDER BY MODULE_GROUP, LAYER, MODULE_ID";
			myLogger.info(" mm: SQL listModule :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listModule = Collections.synchronizedList(new ArrayList());
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
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));	
				h.put("MODULE_ID",rs.getString("MODULE_ID_TRIM") == null ? "" : rs.getString("MODULE_ID_TRIM"));
				h.put("MODULE_ID_ASAL",rs.getString("MODULE_ID_ASAL") == null ? "" : rs.getString("MODULE_ID_ASAL"));
				h.put("MODULE_TITLE",rs.getString("MODULE_TITLE") == null ? "" : rs.getString("MODULE_TITLE"));
				h.put("MODULE_CLASS",rs.getString("MODULE_CLASS") == null ? "" : rs.getString("MODULE_CLASS"));
				h.put("MODULE_GROUP",rs.getString("MODULE_GROUP") == null ? "NONE" : rs.getString("MODULE_GROUP"));
				h.put("MODULE_DESCRIPTION",rs.getString("MODULE_DESCRIPTION") == null ? "" : rs.getString("MODULE_DESCRIPTION"));
				h.put("TOTAL_MODULE",rs.getString("TOTAL_MODULE") == null ? "" : rs.getInt("TOTAL_MODULE"));
				listModule.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listModule;

	}
	
	@SuppressWarnings("unchecked")
	public List listGroupModule(HttpSession session, String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listModule = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			if(!carianTerperinci.equals(""))
			{
				condition += " WHERE (UPPER(MODULE_TITLE) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(MODULE_ID) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(MODULE_DESCRIPTION) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
								" OR UPPER(NVL(MODULE_GROUP,'NONE')) LIKE '%"+carianTerperinci.toUpperCase()+"%' ) "; 		
			}	
			
			sql = " SELECT LAYER, MODULE_ID, MODULE_TITLE, MODULE_CLASS, " +
					" NVL(MODULE_GROUP,'NONE') AS MODULE_GROUP_ASAL, " +
					" replace(NVL(MODULE_GROUP,'NONE'),' ', '') AS MODULE_GROUP_TRIM, " +
					"MODULE_DESCRIPTION, TOTAL_MODULE FROM  " +
					"(SELECT DISTINCT 1 AS LAYER, '' AS MODULE_ID, '' AS MODULE_TITLE, '' AS MODULE_CLASS, MODULE_GROUP, '' AS MODULE_DESCRIPTION, "+
					" (SELECT COUNT(*) FROM "+
					" (SELECT MODULE_ID, MODULE_TITLE, MODULE_CLASS, MODULE_GROUP, MODULE_DESCRIPTION FROM MODULE "+condition+" ) M_TEMP "+
					" WHERE M_TEMP.MODULE_GROUP = M.MODULE_GROUP) AS TOTAL_MODULE "+
					" FROM MODULE M "+condition;
			
			sql += ") WHERE TOTAL_MODULE > 0 ";
			sql +=	" ORDER BY MODULE_GROUP, LAYER, MODULE_ID";
			myLogger.info(" mm: SQL listGroupModule :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listModule = Collections.synchronizedList(new ArrayList());
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
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));	
				h.put("MODULE_ID",rs.getString("MODULE_ID") == null ? "" : rs.getString("MODULE_ID"));
				h.put("MODULE_TITLE",rs.getString("MODULE_TITLE") == null ? "" : rs.getString("MODULE_TITLE"));
				h.put("MODULE_CLASS",rs.getString("MODULE_CLASS") == null ? "" : rs.getString("MODULE_CLASS"));
				h.put("MODULE_GROUP_ASAL",rs.getString("MODULE_GROUP_ASAL") == null ? "NONE" : rs.getString("MODULE_GROUP_ASAL"));
				h.put("MODULE_GROUP",rs.getString("MODULE_GROUP_TRIM") == null ? "NONE" : rs.getString("MODULE_GROUP_TRIM"));
				h.put("MODULE_DESCRIPTION",rs.getString("MODULE_DESCRIPTION") == null ? "" : rs.getString("MODULE_DESCRIPTION"));
				h.put("TOTAL_MODULE",rs.getString("TOTAL_MODULE") == null ? "" : rs.getInt("TOTAL_MODULE"));
				listModule.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listModule;

	}
	public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
		try {
			String scrolPosition = getParam("scrolPosition"+command);
			//myLogger.info(" scrolPosition : "+scrolPosition);
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
			/*
			myLogger.info(" namaList : "+paging.getPage(page));
			myLogger.info(" new Integer(page_mula) : "+new Integer(page_mula));
			myLogger.info(" new Integer(page) : "+new Integer(page));
			myLogger.info(" new Integer(itemsPerPage) : "+new Integer(itemsPerPage));
			myLogger.info(" new Integer(paging.getTotalPages()) : "+new Integer(paging.getTotalPages()));
			myLogger.info(" new Integer(paging.getTopNumber()) : "+new Integer(paging.getTopNumber()));
			myLogger.info(" new Boolean(paging.isFirstPage()) : "+new Boolean(paging.isFirstPage()));
			myLogger.info(" new Boolean(paging.isLastPage()) : "+new Boolean(paging.isLastPage()));
			*/
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
	
	public Hashtable viewModule(HttpSession session, String MODULE_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!MODULE_ID.equals(""))
			{
				sql = " SELECT " +
						"M.MODULE_ID AS MODULE_ID_ASAL, " +
						" replace(replace(M.MODULE_ID,'.', '_'),' ', '') AS MODULE_ID_TRIM, " +
						"M.MODULE_TITLE, M.MODULE_CLASS, "+
						" M.MODULE_GROUP, M.MODULE_DESCRIPTION" +
						" ,M.ID_KEMASKINI, M.ID_MASUK, TO_CHAR(M.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, TO_CHAR(M.TARIKH_MASUK, 'DD/MM/YYYY') AS TARIKH_MASUK,  " +
						" UPPER(U_ADD.USER_NAME) AS USER_ADD,  UPPER(U_UPD.USER_NAME) AS USER_UPD, M.MODULE_VERSION " +
						" FROM MODULE M,USERS U_ADD, USERS U_UPD " +
						" WHERE M.MODULE_ID = '"+MODULE_ID+"' " +
								" AND M.ID_MASUK = U_ADD.USER_ID(+)  AND M.ID_KEMASKINI = U_UPD.USER_ID(+) ";
				myLogger.info(" viewModul :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					h.put("MODULE_ID",rs.getString("MODULE_ID_TRIM") == null ? "" : rs.getString("MODULE_ID_TRIM"));
					h.put("MODULE_ID_ASAL",rs.getString("MODULE_ID_ASAL") == null ? "" : rs.getString("MODULE_ID_ASAL"));
					h.put("MODULE_TITLE",rs.getString("MODULE_TITLE") == null ? "" : rs.getString("MODULE_TITLE"));
					h.put("MODULE_CLASS",rs.getString("MODULE_CLASS") == null ? "" : rs.getString("MODULE_CLASS"));
					h.put("MODULE_GROUP",rs.getString("MODULE_GROUP") == null ? "" : rs.getString("MODULE_GROUP"));
					h.put("MODULE_DESCRIPTION",rs.getString("MODULE_DESCRIPTION") == null ? "" : rs.getString("MODULE_DESCRIPTION"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "-" : rs.getString("TARIKH_KEMASKINI"));
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "-" : rs.getString("TARIKH_MASUK"));
					h.put("USER_ADD",rs.getString("USER_ADD") == null ? "-" : rs.getString("USER_ADD"));
					h.put("USER_UPD",rs.getString("USER_UPD") == null ? "-" : rs.getString("USER_UPD"));
					//PENAMBAHAN ADMIN 19/1/2017
					h.put("MODULE_VERSION",rs.getString("MODULE_VERSION") == null ? "-" : rs.getString("MODULE_VERSION"));
					//TAMAT
				}
			}
			else
			{
				h.put("MODULE_ID","");
				h.put("MODULE_ID_ASAL","");
				h.put("MODULE_TITLE","");
				h.put("MODULE_CLASS","");
				h.put("MODULE_GROUP","");
				h.put("MODULE_DESCRIPTION","");
				h.put("TARIKH_KEMASKINI","");
				h.put("TARIKH_MASUK","");
				h.put("USER_ADD","");
				h.put("USER_UPD","");
				h.put("MODULE_VERSION","");
				
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
	
	
	public boolean checkModul(HttpSession session, String CURRENT_MODULE_ID,String NEW_MODULE_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean check = true;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT MODULE_ID FROM MODULE WHERE MODULE_ID IS NOT NULL ";			
				
				if(!CURRENT_MODULE_ID.equals(""))
				{
					sql += " AND MODULE_ID != '"+CURRENT_MODULE_ID+"' " ;
				}
				
				sql += " AND MODULE_ID = '"+NEW_MODULE_ID+"' " ;
				
					
				myLogger.info("V3 - checkMODUL :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_MODULE_ID = "";
				while (rs.next()) {
					
					GET_MODULE_ID = rs.getString("MODULE_ID") == null ? "" : rs.getString("MODULE_ID");
					if(!GET_MODULE_ID.equals(""))
					{
						check = false;
					}
					
				}
			
			return check;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String simpanModule(HttpSession session,String MODULE_GROUP,String CURRENT_MODULE_ID) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		
		String flag_operasi = "INSERT";
		if(!CURRENT_MODULE_ID.equals(""))
		{
			flag_operasi = "UPDATE";
		}
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
			String MODULE_ID_ = getParam("MODULE_ID_"+MODULE_GROUP+CURRENT_MODULE_ID.replace(".","_").replace(" ",""));
			String MODULE_TITLE_ = getParam("MODULE_TITLE_"+MODULE_GROUP+CURRENT_MODULE_ID.replace(".","_").replace(" ",""));
			String MODULE_CLASS_ = getParam("MODULE_CLASS_"+MODULE_GROUP+CURRENT_MODULE_ID.replace(".","_").replace(" ",""));
			String MODULE_GROUP_ = getParam("MODULE_GROUP_"+MODULE_GROUP+CURRENT_MODULE_ID.replace(".","_").replace(" ",""));
			String MODULE_DESCRIPTION_ = getParam("MODULE_DESCRIPTION_"+MODULE_GROUP+CURRENT_MODULE_ID.replace(".","_").replace(" ",""));
			//PENAMBAHAN ADMIN 18/1/2017
			String MODULE_VERSION_ = getParam("MODULE_VERSION_"+MODULE_GROUP+CURRENT_MODULE_ID.replace(".","_").replace(" ",""));
			//TAMAT
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(flag_operasi.equals("INSERT"))
			{
				returnUSERID = MODULE_ID_;
				//r.add("MODULE_ID", MODULE_ID_);				
			}
			else
			{
				returnUSERID = MODULE_ID_;
				r.update("MODULE_ID", CURRENT_MODULE_ID);
			}
			
			r.add("MODULE_ID", MODULE_ID_);		
			r.add("MODULE_TITLE", MODULE_TITLE_);
			r.add("MODULE_CLASS", MODULE_CLASS_);
			r.add("MODULE_GROUP", MODULE_GROUP_);
			r.add("MODULE_DESCRIPTION", MODULE_DESCRIPTION_);
			//PENAMBAHAN ADMIN 18/1/2017
			r.add("MODULE_VERSION", MODULE_VERSION_);			
			//TAMAT
			
			if(flag_operasi.equals("INSERT"))
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("MODULE");		
				myLogger.info("V3 : INSERT MODULE : "+sql);
				this.context.put("SuccessMesej", "Insert");
				AuditTrail.logActivity(this,session,"INS","MODULE ["+MODULE_ID_+"] Added");				
			}
			else
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("MODULE");		
				myLogger.info("V3 : UPDATE MODULE : "+sql);
				this.context.put("SuccessMesej", "Update");
				AuditTrail.logActivity(this,session,"UPD","MODULE ["+MODULE_ID_+"] Updated");
			}		
			stmt.executeUpdate(sql);			

			conn.commit();
			
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
	
	
	public String updateGroup(HttpSession session,String MODULE_GROUP_ASAL,String NEW_MODULE_GROUP) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		
		
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			r.update("MODULE_GROUP", MODULE_GROUP_ASAL);
			r.add("MODULE_GROUP", NEW_MODULE_GROUP);			
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("MODULE");		
			myLogger.info("V3 : UPDATE MODULE : "+sql);
			this.context.put("SuccessMesej", "Update");
			AuditTrail.logActivity(this,session,"UPD","MODULE GROUP NAME ["+MODULE_GROUP_ASAL+"] to ["+NEW_MODULE_GROUP+"] Updated");
					
			stmt.executeUpdate(sql);			

			conn.commit();
			
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
	
	
	@SuppressWarnings("unchecked")
	public List listRoleByModule(HttpSession session, String MODULE_ID)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRoleByModule = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT R.NAME, R.DESCRIPTION FROM ROLE_MODULE RM, ROLE R "+
					" WHERE RM.USER_ROLE = R.NAME AND RM.MODULE_ID = '"+MODULE_ID.trim()+"' " +
							" ORDER BY R.NAME ";
			myLogger.info(" V3: SQL listRoleByModule :"+ sql);
			rs = stmt.executeQuery(sql);
			listRoleByModule = Collections.synchronizedList(new ArrayList());
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
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME"));
				h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION"));				
				listRoleByModule.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listRoleByModule;

	}
	
	@SuppressWarnings("unchecked")
	public List listRoleBySaveModule(HttpSession session, String MODULE_ID)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRoleBySaveModule = null;
		String sql = "";		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT R.NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(R.DESCRIPTION) AS KETERANGAN, 2 AS LAYER, 'Y' AS TICK, 0 AS TOTAL "+
					" FROM ROLE R, ROLE_MODULE UR WHERE UR.USER_ROLE = R.NAME AND UR.MODULE_ID = '"+MODULE_ID+"' "+
					" UNION "+
					" SELECT R1.NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(R1.THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(R1.DESCRIPTION) AS KETERANGAN, 2 AS LAYER, '' AS TICK,  0 AS TOTAL "+
					" FROM ROLE R1 WHERE R1.NAME NOT IN (SELECT UR1.USER_ROLE FROM ROLE_MODULE UR1 WHERE UR1.MODULE_ID = '"+MODULE_ID+"') "+
					" UNION "+
					" SELECT DISTINCT '' AS ID,   REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') AS KOD, '' AS KETERANGAN, 1 AS LAYER, '' AS TICK,  "+
					" (SELECT count(*)   "+
					" FROM ROLE R, ROLE_MODULE UR WHERE UR.USER_ROLE = R.NAME AND UR.MODULE_ID = '"+MODULE_ID+"' AND REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') = REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''))  "+
					" AS TOTAL FROM ROLE R3  "+
					" ORDER BY KOD, LAYER ";
			myLogger.info(" V3: SQL listRoleBySaveModule :"+ sql);
			rs = stmt.executeQuery(sql);
			listRoleBySaveModule = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
				h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));
				h.put("TOTAL",rs.getString("TOTAL") == null ? "" : rs.getInt("TOTAL"));				
				listRoleBySaveModule.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listRoleBySaveModule;
	}
	
	@SuppressWarnings("unchecked")
	public List listRoleBySaveModule_Selected(HttpSession session, String MODULE_ID)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRoleBySaveModule_Selected = null;
		String sql = "";		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT R.NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(R.DESCRIPTION) AS KETERANGAN, 2 AS LAYER, 'Y' AS TICK, 0 AS TOTAL "+
					" FROM ROLE R, ROLE_MODULE UR WHERE UR.USER_ROLE = R.NAME AND UR.MODULE_ID = '"+MODULE_ID+"' "+
					" UNION "+
					" SELECT DISTINCT '' AS ID,   REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') AS KOD, '' AS KETERANGAN, 1 AS LAYER, '' AS TICK,  "+
					" (SELECT count(*)   "+
					" FROM ROLE R, ROLE_MODULE UR WHERE UR.USER_ROLE = R.NAME AND UR.MODULE_ID = '"+MODULE_ID+"' AND REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') = REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''))  "+
					" AS TOTAL FROM ROLE R3, ROLE_MODULE UR3 WHERE  UR3.USER_ROLE = R3.NAME AND UR3.MODULE_ID = '"+MODULE_ID+"'"+
					" ORDER BY KOD, LAYER ";
			myLogger.info(" V3: SQL listRoleBySaveModule :"+ sql);
			rs = stmt.executeQuery(sql);
			listRoleBySaveModule_Selected = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			int new_bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				if(rs.getString("LAYER").equals("1"))
				{
					new_bil = 0;
				}
				if(rs.getString("LAYER").equals("2"))
				{
					new_bil++;
				}
				
				h.put("BIL",bil);
				h.put("NEWBIL",new_bil);
				
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
				h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));
				h.put("TOTAL",rs.getString("TOTAL") == null ? "" : rs.getInt("TOTAL"));				
				listRoleBySaveModule_Selected.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listRoleBySaveModule_Selected;
	}
	
	public int simpanAdditionalRoles(HttpSession session,String MODULE_ID_ASAL, String MODULE_GROUP, String MODULE_ID_TRIM, String[] TEMP_GROUP_CHECKLIST) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		int total_role_update = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "DELETE FROM ROLE_MODULE WHERE MODULE_ID = '"+MODULE_ID_ASAL+"'";
			myLogger.info("V3 : SQL-CLEAR USER ROLE : "+sql);
			stmt.executeUpdate(sql);			
			
			
			String[] CHECKLIST_ROLES = null;
			for (String GROUP_KOD : TEMP_GROUP_CHECKLIST) {
				myLogger.info(" GROUP_KOD : "+GROUP_KOD);
				CHECKLIST_ROLES = request.getParameterValues("CHECKLIST_"+MODULE_GROUP+MODULE_ID_TRIM+GROUP_KOD);
				myLogger.info(" CHECKLIST_ROLES : "+CHECKLIST_ROLES);				
				if(CHECKLIST_ROLES!=null)
				{
					for (String ROLE_ID : CHECKLIST_ROLES) {
						myLogger.info(GROUP_KOD+" --- ROLE_ID "+ROLE_ID);
						sql = "";
						r.clear();
						r.add("MODULE_ID", MODULE_ID_ASAL);
						r.add("USER_ROLE", ROLE_ID);
						sql = r.getSQLInsert("ROLE_MODULE");		
						myLogger.info("V3 : INSERT USER_ROLE : "+sql);
						stmt.executeUpdate(sql);
						total_role_update++;
					}
				}
				CHECKLIST_ROLES = null;
			}
			
			conn.commit();
			
		} 
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran Role:"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return total_role_update;
	}

}
		
		