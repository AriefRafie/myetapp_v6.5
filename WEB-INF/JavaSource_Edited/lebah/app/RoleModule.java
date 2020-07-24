package lebah.app;
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
import ekptg.helpers.Paging2;

public class RoleModule extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(RoleModule.class);
	String skrin_name = "app/admin/RoleModule/index.jsp";	
	@Override
	public String doTemplate2() throws Exception {
		List listRole = null;
		List listGroup = null;
		Hashtable viewRole = null;
		Hashtable viewGroup = null;
		List listRoleBySaveModule = null;
		List listPengunaModul = null;
		List listModuleForStat = null;
		List listStatModuleRole = null;
		
		
		
		//diba tambah
		List listRolePrint = null;
		List listModul = null;
		List listDokumen = null;
		
		defaultPut();
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		//myLogger.info("**command : "+command);
		this.context.put("command",command);
		
		
		
		
		if(command.equals("carianUtama"))
		{
			String CSS_TITLE = "";
			
			if(getParam("DELETE").equals("Y"))
			{
				 CSS_TITLE = getParam("CSS_TITLE");
				deleteGroup(session,CSS_TITLE);
			}
			String carianTerperinci = getParam("carianTerperinci");
			listGroup = listGroupRole(session, carianTerperinci,"","");
			this.context.put("listGroup",listGroup);
			
			CSS_TITLE = getParam("CSS_TITLE");
			
			listDokumen = getListDokumen(session, carianTerperinci,CSS_TITLE, "manual");
			this.context.put("listDokumen",listDokumen);	
			skrin_name = "app/admin/RoleModule/SenaraiUtama.jsp";
		}
		else if(command.equals("saveGroup"))
		{			
			String divGroup = getParam("divGroup");
			this.context.put("divGroup",divGroup);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String CURRENT_CSS_NAME = getParam("CURRENT_CSS_NAME");
			this.context.put("CURRENT_CSS_NAME",CURRENT_CSS_NAME);			
			this.context.put("after_saveGroup","Y");			
			String getvalue_CSS_TITLE = getParam("getvalue_CSS_TITLE");
			String getvalue_CSS_NAME = getParam("getvalue_CSS_NAME");			
			//myLogger.info("***getCSSTITLE : "+getParam("CSS_TITLE_"+GROUPUNIK));
			//diba tambah
			String getvalue_MODUL_ID = getParam("getvalue_MODUL_ID");	
			
			saveGroup(session,GROUPUNIK,CSS_TITLE,CURRENT_CSS_NAME,getvalue_CSS_TITLE,getvalue_CSS_NAME,getvalue_MODUL_ID);			
			
			this.context.put("getPageCoor",getParam("getPageCoor"));
			skrin_name = "app/admin/RoleModule/index.jsp";
		}
		/*
		else if(command.equals("Upload"))
		{
			this.context.put("afterUpload","Y");
			uploadFiles();
			skrin_name = "app/admin/RoleModule/index.jsp";
		}
		*/		
		else if(command.equals("viewGroup"))
		{
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String MODE = getParam("MODE");
			this.context.put("MODE",MODE);
			
			/*
			String CURRENT_CSS_NAME = getParam("CURRENT_CSS_NAME");
			String SAVE = getParam("SAVE");
			if(SAVE.equals("Y"))
			{
				String get_Latest_CSS_TITLE = saveGroupRole(session,GROUPUNIK,CSS_TITLE,CURRENT_CSS_NAME);
				CSS_TITLE = getROWID_GROUP(session, get_Latest_CSS_TITLE);
			}
			*/
			
			viewGroup = viewGroup(session,CSS_TITLE,GROUPUNIK);
			this.context.put("viewGroup",viewGroup);
			
			listModul = listTableRujukan(session, "TBLRUJMODUL", "");
			this.context.put("listModul",listModul);
			
			skrin_name = "app/admin/RoleModule/viewGroup.jsp";
		}
		else if(command.equals("viewRole"))
		{
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String ROLEUNIK = getParam("ROLEUNIK");
			this.context.put("ROLEUNIK",ROLEUNIK);
			String BIL = getParam("BIL");
			this.context.put("BIL",BIL);
			String ID = getParam("ID");			
			
			String SAVE = getParam("SAVE");
			if(SAVE.equals("Y"))
			{
				String get_Latest_ROLE_NAME = saveRole(session,GROUPUNIK,ROLEUNIK,ID);
				ID = getROWID_ROLE(session, get_Latest_ROLE_NAME);
			}
			
			viewRole = viewRole(session,ID,ROLEUNIK);
			this.context.put("viewRole",viewRole);
			this.context.put("page",getParam("page"));
			skrin_name = "app/admin/RoleModule/viewRole.jsp";
		}
		else if(command.equals("closeRole"))
		{
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			skrin_name = "app/admin/RoleModule/blank_viewRole.jsp";
		}
		
		else if(command.equals("checkDuplicateGroup"))
		{					
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String CSS_TITLE_CONTENT = getParam("CSS_TITLE_CONTENT");
			myLogger.info(" CSS_TITLE_CONTENT :"+CSS_TITLE_CONTENT);			
			boolean checkGroup = checkGroup(session,CSS_TITLE,CSS_TITLE_CONTENT);
			this.context.put("checkDuplicateGroup",checkGroup);
			
			skrin_name = "app/admin/RoleModule/CheckDuplicateGroup.jsp";
		}
		
		else if(command.equals("checkDuplicateCSS"))
		{					
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String CSS_FILE_CONTENT = getParam("CSS_FILE_CONTENT");
			myLogger.info(" CSS_FILE_CONTENT :"+CSS_FILE_CONTENT);			
			boolean checkCSS = checkCSS(session,CSS_TITLE,CSS_FILE_CONTENT);
			this.context.put("checkDuplicateCSS",checkCSS);
			
			skrin_name = "app/admin/RoleModule/CheckDuplicateCSS.jsp";
		}
		
		else if(command.equals("checkDuplicateRole"))
		{			
			
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String ROLEUNIK = getParam("ROLEUNIK");
			this.context.put("ROLEUNIK",ROLEUNIK);
			String ID = getParam("ID");
			this.context.put("ID",ID);
			myLogger.info(" ROLE ID :"+ID);			
			String ROLE_NAME = getParam("ROLE_NAME");
			boolean checkRole = checkRole(session,ID,ROLE_NAME);
			this.context.put("checkDuplicateRole",checkRole);
			
			skrin_name = "app/admin/RoleModule/CheckDuplicateRole.jsp";
		}
		else if(command.equals("editRole"))
		{
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String ROLEUNIK = getParam("ROLEUNIK");
			this.context.put("ROLEUNIK",ROLEUNIK);
			String CSS_NAME = getParam("CSS_NAME");
			this.context.put("CSS_NAME",CSS_NAME);	
			String BIL = getParam("BIL");
			this.context.put("BIL",BIL);
			String ID = getParam("ID");
			viewRole = viewRole(session,ID,ROLEUNIK);
			this.context.put("viewRole",viewRole);
			listGroup = listGroupRole(session,"",CSS_NAME,"Y");
			this.context.put("listGroup",listGroup);
			this.context.put("page",getParam("page"));
			skrin_name = "app/admin/RoleModule/editRole.jsp";
		}
		else if(command.equals("carianUtamaModul"))
		{		
			String scrolPosition = getParam("scrolPosition"+command);
			this.context.put("scrolPosition", scrolPosition);
			String ROLEUNIK = getParam("ROLEUNIK");
			this.context.put("ROLEUNIK", ROLEUNIK);
			String ROLE_NAME = getParam("ROLE_NAME");
			this.context.put("ROLE_NAME",ROLE_NAME);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			Integer totalModul = getModulCount(session, ROLE_NAME);
			
			this.context.put("adaModul", "");
			if(totalModul>0)
			{
				this.context.put("totalModul", totalModul);
				this.context.put("adaModul", "Y");	
				
				String carianTerperinciModul = getParam("carianTerperinciModul_"+GROUPUNIK+ROLEUNIK);
				Date today_date = new Date();
				String today_date_str= new SimpleDateFormat("dd/MM/yyyy").format(today_date);
				
				String action = getParam("action");
				listPengunaModul = listPengunaModul(session,carianTerperinciModul,ROLE_NAME);
				setupPageList(session, action, listPengunaModul, "listPengunaModul",command,"div_ListModul"+GROUPUNIK+ROLEUNIK,"");				
				this.context.put("listPengunaModulforPrint",listPengunaModul);
				this.context.put("carianTerperinciModul",carianTerperinciModul);
				this.context.put("TARIKH_CURRENT",today_date_str);
			
			}			
			skrin_name = "app/admin/RoleModule/SenaraiModul.jsp";
		}
		else if(command.equals("showSenaraiRoleByGroup"))
		{
			this.context.put("SuccessMesejDeleteModule", "");
			String DELETE = getParam("DELETE");			
			if(DELETE.equals("Y"))
			{
				String ID = getParam("ID");
				deleteRole(session,ID);
			}
			
			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String BIL = getParam("BIL");
			this.context.put("BIL",BIL);
			
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
			
			
			listRole = listRole(session, carianTerperinci,CSS_TITLE,null);
			this.context.put("listRole",listRole);	
			
			//myLogger.info("action : "+action);
			//myLogger.info("carianTerperinci : "+carianTerperinci);
			//myLogger.info("BIL : "+BIL);
			//myLogger.info("CSS_TITLE : "+CSS_TITLE);
			
			setupPageList(session, action, listRole, "listRole",command,"div_Main"+BIL,CSS_TITLE);
			
			skrin_name = "app/admin/RoleModule/showlistRoleByGroup.jsp";
		}
		
		else if(command.equals("printRole"))
		{
			listRolePrint = listRolePrint(session);
			this.context.put("listRolePrint",listRolePrint);
			
			skrin_name = "app/admin/RoleModule/printAllRole.jsp";
		}
		
		else if(command.equals("showSenaraiUserManualByGroup"))
		{
			this.context.put("SuccessMesejDeleteModule", "");
			String DELETE = getParam("DELETE");			
			if(DELETE.equals("Y"))
			{
				String ID = getParam("ID");
				deleteRole(session,ID);
			}
			
			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			String GROUPUNIK = getParam("GROUPUNIK");
			this.context.put("GROUPUNIK",GROUPUNIK);
			String BIL = getParam("BIL");
			this.context.put("BIL",BIL);
			
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
			
			
			listDokumen = getListDokumen(session, carianTerperinci,CSS_TITLE, "manual");
			this.context.put("listDokumen",listDokumen);	
			
			//myLogger.info("action : "+action);
			//myLogger.info("carianTerperinci : "+carianTerperinci);
			//myLogger.info("BIL : "+BIL);
			//myLogger.info("CSS_TITLE : "+CSS_TITLE);
			
			setupPageList(session, action, listDokumen, "listDokumen",command,"div_Main"+BIL,CSS_TITLE);
			
			skrin_name = "app/admin/RoleModule/showlistDokumenbyGroup.jsp";
		}
		
		else
		{
			
			Db db = null;
			try {
				db = new Db();
				listModuleForStat = listModuleForStat(session,db);
				this.context.put("listModuleForStat",listModuleForStat);
				listStatModuleRole = listStatModuleRole(session,db);
				this.context.put("listStatModuleRole",listStatModuleRole);
				
			}
			finally {
				if (db != null)
					db.close();

				
			}
		}
		
		return skrin_name;
	}
	
	
	
	public void defaultPut()
	{
		this.context.put("listModuleForStat","");
		this.context.put("listStatModuleRole","");
		this.context.put("afterUpload","");
		this.context.put("command","");
		this.context.put("carianTerperinci","");
		this.context.put("listRole","");
		this.context.put("listGroup","");
		this.context.put("viewRole","");
		this.context.put("listDropDownGroup","");
		this.context.put("checkDuplicateModule","");	
		this.context.put("SuccessMesej", "");
		this.context.put("listRoleByModule", "");
		this.context.put("listRoleBySaveModule", "");
		this.context.put("SuccessMesejRole", "");		
		this.context.put("SuccessMesejDelete", "");
		this.context.put("SuccessMesejDeleteRole", "");
		this.context.put("NEXT_BIL","");
		this.context.put("TOTAL_LIST","");
		this.context.put("SHOWNEXT","");
		this.context.put("after_saveGroup","");
		this.context.put("getPageCoor","");		
		this.context.put("listPengunaModul","");
		this.context.put("carianTerperinciModul","");
		this.context.put("TARIKH_MULA_MODUL","");
		this.context.put("TARIKH_AKHIR_MODUL","");
		this.context.put("TARIKH_CURRENT","");
		this.context.put("listPengunaModulforPrint","");
		this.context.put("totalModul","");
		this.context.put("adaModul", "");
	}
	
	//diba tambah untuk manual
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getListDokumen(HttpSession session, String carianTerperinci, String CSS_TITLE, String flag)throws Exception {
				
		Db db = null; 
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listDoc = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
						sql = 	" SELECT ID_DOKUMENADMIN, ID_REF, FLAG_ADMIN, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
								" TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK , ID_MASUK, " +
								" TO_CHAR(TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, ID_KEMASKINI, KETERANGAN "+
								" FROM TBLRUJDOKUMENADMIN "+
								" WHERE FLAG_ADMIN = '"+ flag +"' "+
								" AND ID_REF = '"+ CSS_TITLE +"'";
					
					myLogger.debug("SQL LIST DOC - "+sql);
					 rs = stmt.executeQuery(sql);
					
					 listDoc = Collections.synchronizedList(new ArrayList());
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
						h.put("ID_DOKUMENADMIN", rs.getString("ID_DOKUMENADMIN") == null ? "" : rs.getString("ID_DOKUMENADMIN"));
						h.put("ID_REF",rs.getString("ID_REF") == null ? "" : rs.getString("ID_REF"));
						h.put("NAMA_DOKUMEN",rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
						h.put("JENIS_DOKUMEN",rs.getString("JENIS_DOKUMEN") == null ? "" : rs.getString("JENIS_DOKUMEN"));
						h.put("DOKUMEN",rs.getString("DOKUMEN") == null ? "" : rs.getString("DOKUMEN"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("FLAG_ADMIN",rs.getString("FLAG_ADMIN") == null ? "" : rs.getString("FLAG_ADMIN"));
						
						listDoc.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listDoc;
				
			}
	
	@SuppressWarnings("unchecked")
	public List listPengunaModul(HttpSession session,String carianTerperinci,String ROLE_NAME)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPengunaModul = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			if(!carianTerperinci.equals(""))
			{
				condition = "  AND (" +
						" UPPER(M.MODULE_ID) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(M.MODULE_CLASS) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(M.MODULE_DESCRIPTION) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(M.MODULE_GROUP) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" )";
			}
			
			sql = " SELECT M.MODULE_ID,M.MODULE_CLASS, M.MODULE_DESCRIPTION,M.MODULE_GROUP,R.NAME,M.MODULE_VERSION  " +
					" FROM ROLE_MODULE RM, ROLE R, MODULE M "+
					" WHERE RM.USER_ROLE = R.NAME " + condition +
					" AND RM.USER_ROLE = '"+ROLE_NAME+"' AND RM.MODULE_ID = M.MODULE_ID ";
			
			sql +=	" ORDER BY M.MODULE_GROUP,M.MODULE_ID ";
			myLogger.info(" V3: SQL listPengunaModul :"+ sql);	
				
			rs = stmt.executeQuery(sql);
			listPengunaModul = Collections.synchronizedList(new ArrayList());
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
				h.put("MODULE_ID",rs.getString("MODULE_ID") == null ? "" : rs.getString("MODULE_ID"));
				h.put("MODULE_CLASS",rs.getString("MODULE_CLASS") == null ? "" : rs.getString("MODULE_CLASS"));
				h.put("MODULE_DESCRIPTION",rs.getString("MODULE_DESCRIPTION") == null ? "" : rs.getString("MODULE_DESCRIPTION"));
				h.put("MODULE_GROUP",rs.getString("MODULE_GROUP") == null ? "" : rs.getString("MODULE_GROUP"));
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME"));
				h.put("MODULE_VERSION",rs.getString("MODULE_VERSION") == null ? "" : rs.getString("MODULE_VERSION"));
				listPengunaModul.add(h);
				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPengunaModul;

	}
	
	 @SuppressWarnings("unchecked")
		private void saveGroup(HttpSession session,String GROUPUNIK,String CSS_TITLE,String CURRENT_CSS_NAME,
				String getvalue_CSS_TITLE,String getvalue_CSS_NAME, String getvalue_MODUL_ID) throws Exception {
		 	Db db = null;
			try {
				db = new Db();	
					
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
				      
				      if(item.getFieldName().contains(GROUPUNIK)==true){
				      myLogger.info("fieldname ------- "+item.getFieldName()+" ada  : "+item.getFieldName().contains(GROUPUNIK)+" value : "+getParam(item.getFieldName()));
				      }
				      
				      //myLogger.info("::: item ------- "+item);			      
				      //myLogger.info("::: GROUPUNIK ------- "+GROUPUNIK+" CSS_TITLE :"+CSS_TITLE+" CURRENT_CSS_NAME : "+CURRENT_CSS_NAME);
				      String get_Latest_CSS_TITLE = saveGroupRole(session,GROUPUNIK,CSS_TITLE,CURRENT_CSS_NAME,getvalue_CSS_TITLE,getvalue_CSS_NAME,getvalue_MODUL_ID,db);
					  CSS_TITLE = getROWID_GROUP(session, get_Latest_CSS_TITLE,db);
					  
				      if ((!(item.isFormField())) && !CSS_TITLE.equals("") && item.getFieldName().contains(GROUPUNIK)==true && (item.getName() != null) && (!("".equals(item.getName())))) {
				    	 
				    	  saveGroupDB(item,GROUPUNIK,CSS_TITLE,db);
				    	 
				      }
				    }
			    
				}
				catch (Exception ex) {
				throw new DbException(ex.getMessage());
				}
				finally {
					if (db != null)
						db.close();
				}	
			  }
	
	 private void saveGroupDB(FileItem item,String GROUPUNIK,String CSS_TITLE,Db db) throws Exception {
		    myLogger.info("::: saveGroupDBitem ------- "+item+" GROUPUNIK : "+GROUPUNIK+ " CSS_TITLE : "+CSS_TITLE);
		 	HttpSession session = request.getSession();	
		 	
	  		Db db1 = null;
	        try {		        	
	        	//long id_esdokumen = DB.getNextID("TBLESDOKUMEN_SEQ");	
	        	if(db == null)
	        	{
	        		db1 = new Db();	
	        	}
	        	else
	        	{
	        		db1 = db;	
	        	}
	        	
	        		        	
	        	Connection con = db1.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("UPDATE PAGE_CSS SET CONTENT = ?, NAMA_DOC = ?, JENIS_MIME = ? WHERE ROWID = ?");
	        	ps.setBinaryStream(1, item.getInputStream(),(int)item.getSize());
	        	ps.setString(2, item.getName());
	        	ps.setString(3,item.getContentType());
	        	ps.setString(4,CSS_TITLE);      	
	        	ps.executeUpdate();
	            con.commit(); 
		    } finally {
		    	if(db == null)
	        	{
		    		if (db1 != null) db1.close();
	        	}
		    }
		    
	  }		 
	
	 @SuppressWarnings("unchecked")
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
			      String fieldname = item.getFieldName();
			      
			      myLogger.info("fieldname ------- "+fieldname);
			      
			      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	  //saveData(item);
			      }
			    }
			  }
	
	
	public void deleteGroup(HttpSession session,String ID) throws Exception {
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
			r.add("ROWID", ID);
			sql = r.getSQLDelete("PAGE_CSS");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","PAGE CSS ["+ID+"] Deleted");
			this.context.put("SuccessMesejDelete", "Delete");
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
	
	public void deleteRole(HttpSession session,String ID) throws Exception {
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
			r.add("ROWID", ID);
			sql = r.getSQLDelete("ROLE");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","ROLE ["+ID+"] Deleted");
			this.context.put("SuccessMesejDeleteRole", "Delete");
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
	public List listGroupRole(HttpSession session, String carianTerperinci,String CSS_NAME,String DROPDOW)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRole = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			if(!carianTerperinci.equals(""))
			{
				condition += " AND (UPPER(RR.NAME) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(RR.DESCRIPTION) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(RR.THEME) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(PP.CSS_TITLE) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
								" ) "; 		
			}	
			
			sql = " SELECT ROWNUM AS ID_TEMP,JENIS_MIME,NAMA_DOC,LAYER, NAME, DESCRIPTION, CSS_TITLE, TITLE, CSS_NAME, TOTAL_ROLE "+
	          " FROM ( "+
	          " SELECT DISTINCT P.JENIS_MIME, P.NAMA_DOC,1 AS LAYER, '' AS NAME, '' AS DESCRIPTION,P.ROWID AS CSS_TITLE, P.CSS_TITLE AS TITLE,P.CSS_NAME, "+
	          " (SELECT COUNT(*) FROM ROLE RR,PAGE_CSS PP " +
	          " WHERE RR.THEME = R.THEME " + condition + 
	          " AND RR.THEME = PP.CSS_NAME) AS TOTAL_ROLE  "+
	          " FROM ROLE R, PAGE_CSS P  "+
	          " WHERE P.CSS_NAME = R.THEME(+) ";
			if(DROPDOW.equals(""))
			{
			sql+=" UNION "+     
					" SELECT DISTINCT '' AS JENIS_MIME,'' AS NAMA_DOC,1 AS LAYER, '' AS NAME, '' AS DESCRIPTION, "+ 
					" P.ROWID AS CSS_TITLE, '' AS TITLE, "+ 
					" P.CSS_NAME, "+ 
					" (SELECT COUNT (*) "+ 
					" FROM ROLE RR, PAGE_CSS PP "+ 
					" WHERE RR.THEME = R.THEME "  + condition + 
					" AND RR.THEME = PP.CSS_NAME(+) AND PP.CSS_NAME IS NULL ) AS TOTAL_ROLE "+ 
					" FROM ROLE R, PAGE_CSS P "+ 
					" WHERE P.CSS_NAME(+) = R.THEME AND P.CSS_NAME IS NULL  ";
			}
	          sql+=") RRR ";
			
			sql += " WHERE LAYER IS NOT NULL ";
			if(!carianTerperinci.equals(""))
			{
	          sql += " AND RRR.TOTAL_ROLE > 0 ";
			}
			if(!CSS_NAME.equals(""))
			{
			  sql += " AND RRR.CSS_NAME = '"+CSS_NAME+"' ";
			}
			 sql+=" ORDER BY TITLE";
//			myLogger.info(" mm: SQL listGroupRole :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listRole = Collections.synchronizedList(new ArrayList());
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
				h.put("ID",rs.getString("ID_TEMP") == null ? "" : rs.getString("ID_TEMP"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME").toUpperCase());
				h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME").toUpperCase());
				h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC").toUpperCase());
				h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
				h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE"));
				h.put("TITLE",rs.getString("TITLE") == null ? "" : rs.getString("TITLE").toUpperCase());
				h.put("CSS_NAME",rs.getString("CSS_NAME") == null ? "" : rs.getString("CSS_NAME"));
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "NONE" : rs.getString("TOTAL_ROLE"));
				h.put("GROUPUNIK",rs.getString("TITLE") == null ? "" : replaceString(rs.getString("TITLE").toUpperCase()));
				listRole.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listRole;

	}
	
	@SuppressWarnings("unchecked")
	public Hashtable viewGroup(HttpSession session, String ID,String GROUPUNIK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			
			if(!ID.equals(""))
			{
				sql = " SELECT ROWNUM AS ID_TEMP,JENIS_MIME,NAMA_DOC,LAYER, NAME, DESCRIPTION, CSS_TITLE, TITLE, CSS_NAME, TOTAL_ROLE, MODUL_ID "+
		          " FROM ( "+
		          " SELECT DISTINCT P.JENIS_MIME, P.NAMA_DOC,1 AS LAYER, '' AS NAME, '' AS DESCRIPTION,P.ROWID AS CSS_TITLE, P.CSS_TITLE AS TITLE,P.CSS_NAME, "+
		          " 0 AS TOTAL_ROLE, P.MODUL_ID  "+
		          " FROM PAGE_CSS P  "+
		          " WHERE P.ROWID = '"+ID+"'  "+
		          " ) RRR  ";
				
				myLogger.info(" mm: SQL VIEWGROUP :"+ sql);
				
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					h = new Hashtable();
					
					h.put("ID",rs.getString("ID_TEMP") == null ? "" : rs.getString("ID_TEMP"));
					h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
					h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME").toUpperCase());
					h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
					h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE"));
					h.put("TITLE",rs.getString("TITLE") == null ? "" : rs.getString("TITLE").toUpperCase());
					h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME").toUpperCase());
					h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC").toUpperCase());
					
					h.put("CSS_NAME",rs.getString("CSS_NAME") == null ? "" : rs.getString("CSS_NAME"));
					h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "NONE" : rs.getString("TOTAL_ROLE"));
					if(!GROUPUNIK.equals(""))
					{
						h.put("GROUPUNIK",rs.getString("TITLE") == null ? "" : replaceString(rs.getString("TITLE").toUpperCase()));
					}
					else
					{
						h.put("GROUPUNIK","");
					}
					h.put("MODUL_ID",rs.getString("MODUL_ID") == null ? "" : rs.getString("MODUL_ID"));
				}
			}
			else
			{
				h = new Hashtable();				
				h.put("ID","");
				h.put("LAYER","");	
				h.put("NAME","");
				h.put("DESCRIPTION","");
				h.put("CSS_TITLE","");
				h.put("TITLE","");
				h.put("CSS_NAME","");
				h.put("TOTAL_ROLE","");
				h.put("GROUPUNIK","");
				h.put("JENIS_MIME","");
				h.put("NAMA_DOC","");
				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return h;

	}
	
	@SuppressWarnings("unchecked")
	public List listRole(HttpSession session, String carianTerperinci,String CSS_TITLE,Db db)throws Exception {
		
		myLogger.info(" ----------------------- CSS_TITLE : "+CSS_TITLE);
		
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRole = null;
		String sql = "";
		String condition = "";
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
			
			
			if(!carianTerperinci.equals(""))
			{
				condition += " AND (UPPER(R.NAME) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(R.DESCRIPTION) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(R.THEME) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(P.CSS_TITLE) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
								" )  "; 		
			}	
			
			sql = " SELECT ID,LAYER, NAME, DESCRIPTION, CSS_TITLE, TOTAL_ROLE "+
	          " FROM ( "+
	          " SELECT DISTINCT  R.ROWID AS ID,2 AS LAYER, R.NAME, R.DESCRIPTION,P.CSS_TITLE, "+
	          " 0 AS TOTAL_ROLE  "+
	          " FROM ROLE R, PAGE_CSS P  "+
	          " WHERE R.NAME IS NOT NULL ";
			  if(!CSS_TITLE.equals(""))
			  {
				  sql += " AND R.THEME = P.CSS_NAME  "+condition+
		          " AND P.ROWID = '"+CSS_TITLE+"' ";
			  }
			  else
			  {
				  sql += " AND R.THEME = P.CSS_NAME(+)  "+condition+
				          " AND P.CSS_NAME IS NULL ";
			  }
			  sql += " ORDER BY P.CSS_TITLE,R.NAME) RRR ";
			  
			
			myLogger.info(" mm: SQL listRole :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listRole = Collections.synchronizedList(new ArrayList());
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
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME"));
				h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
				h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE").toUpperCase());
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "NONE" : rs.getString("TOTAL_ROLE"));
				h.put("ROLEUNIK",rs.getString("NAME") == null ? "" : replaceString(rs.getString("NAME").toUpperCase()));
				listRole.add(h);
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

		return listRole;

	}
	
	//diba tambah
	@SuppressWarnings("unchecked")
	public List listRolePrint(HttpSession session)throws Exception {
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRole = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
					sql = 	" SELECT ID,LAYER, NAME, DESCRIPTION, CSS_TITLE "+
							" FROM ( "+
							" SELECT DISTINCT  R.ROWID AS ID,2 AS LAYER, R.NAME, R.DESCRIPTION,P.CSS_TITLE, "+
							" 0 AS TOTAL_ROLE  "+
							" FROM ROLE R, PAGE_CSS P  "+
							" WHERE R.NAME IS NOT NULL "+
							" AND R.THEME = P.CSS_NAME " +
							" ORDER BY P.CSS_TITLE, R.NAME) " ;
			 
			myLogger.info(" SQL listRolePrint :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listRole = Collections.synchronizedList(new ArrayList());
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
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME"));
				h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
				h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE").toUpperCase());
				listRole.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listRole;

	}
	
	
	
	public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div,String CSS_TITLE) {
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
			if (this.context.get("itemsPerPage"+command+CSS_TITLE) == null
					|| this.context.get("itemsPerPage"+command+CSS_TITLE) == "") {
				//myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
				itemsPerPage = getParam("itemsPerPage"+command+CSS_TITLE) == "" ? 10
						: getParamAsInteger("itemsPerPage"+command+CSS_TITLE);
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage"+command+CSS_TITLE);
			}
			
			if (("getNext").equals(action)) {
				page++;
			} else if (("getPrevious").equals(action)) {
				page--;
			} else if (("getPage").equals(action)) {
				page = getParamAsInteger("value");
			} else if (("doChangeItemPerPage").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage"+command+CSS_TITLE);
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
	
	
	public int getModulCount(HttpSession session, String ROLE_NAME) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			int cnt = 0;
			
			sql = " SELECT COUNT(*) AS CNT FROM ROLE_MODULE A " +
					" WHERE A.USER_ROLE = '"+ROLE_NAME+"' ";
				myLogger.info("GET_COUNT ROLE_NAME : "+sql);
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					cnt = rs.getString("CNT") == null ? 0 : rs.getInt("CNT");
				}			
			return cnt;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable viewRole(HttpSession session, String ID,String ROLEUNIK) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID.equals(""))
			{
				sql = " SELECT ID,LAYER, NAME, DESCRIPTION,ROLE_DETAILS, CSS_TITLE,CSS_NAME, TOTAL_ROLE "+
	          " FROM ( "+
	          " SELECT DISTINCT  R.ROWID AS ID,2 AS LAYER, R.NAME, R.DESCRIPTION,R.ROLE_DETAILS,P.CSS_TITLE,P.CSS_NAME, "+
	          " 0 AS TOTAL_ROLE  "+
	          " FROM ROLE R, PAGE_CSS P  "+
	          " WHERE R.THEME = P.CSS_NAME(+) AND R.ROWID = '"+ID+"' "+
	          " ORDER BY P.CSS_TITLE) RRR  "; 
				myLogger.info(" viewRole :" + sql);
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
					h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME"));
					h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
					h.put("ROLE_DETAILS",rs.getString("ROLE_DETAILS") == null ? "" : rs.getString("ROLE_DETAILS").toUpperCase());
					h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE"));
					h.put("CSS_NAME",rs.getString("CSS_NAME") == null ? "" : rs.getString("CSS_NAME"));
					h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "NONE" : rs.getString("TOTAL_ROLE"));
					if(!ROLEUNIK.equals(""))
					{
						h.put("ROLEUNIK",rs.getString("NAME") == null ? "" : replaceString(rs.getString("NAME").toUpperCase()));
					}
					else
					{
						h.put("ROLEUNIK","");
					}
				}
			}
			else
			{
				h.put("ID","");
				h.put("LAYER","");	
				h.put("NAME","");
				h.put("DESCRIPTION","");
				h.put("ROLE_DETAILS","");				
				h.put("CSS_TITLE","");
				h.put("TOTAL_ROLE","");
				h.put("CSS_NAME","");
				h.put("ROLEUNIK","");
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
	
	
	public boolean checkGroup(HttpSession session, String CSS_ID,String CSS_CONTENT) throws Exception {
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
			
				sql = " SELECT CSS_TITLE FROM PAGE_CSS WHERE CSS_TITLE IS NOT NULL ";			
				
				if(!CSS_ID.equals(""))
				{
					sql += " AND ROWID != '"+CSS_ID+"' " ;
				}
				
				sql += " AND UPPER(CSS_TITLE) = '"+CSS_CONTENT.toUpperCase()+"' " ;
				
					
				myLogger.info("V3 - checkgroup :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_CSS_TITLE = "";
				while (rs.next()) {
					
					GET_CSS_TITLE = rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE");
					if(!GET_CSS_TITLE.equals(""))
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
	
	public boolean checkCSS(HttpSession session, String CSS_ID,String CSS_CONTENT) throws Exception {
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
			
				sql = " SELECT CSS_NAME FROM PAGE_CSS WHERE CSS_NAME IS NOT NULL ";			
				
				if(!CSS_ID.equals(""))
				{
					sql += " AND ROWID != '"+CSS_ID+"' " ;
				}
				
				sql += " AND UPPER(CSS_NAME) = '"+CSS_CONTENT.toUpperCase()+"' " ;
				
					
				myLogger.info("V3 - checkCSS :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_CSS_NAME = "";
				while (rs.next()) {
					
					GET_CSS_NAME = rs.getString("CSS_NAME") == null ? "" : rs.getString("CSS_NAME");
					if(!GET_CSS_NAME.equals(""))
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
	
	public boolean checkRole(HttpSession session, String ID,String ROLE_NAME) throws Exception {
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
			
				sql = " SELECT NAME FROM ROLE WHERE NAME IS NOT NULL ";			
				
				if(!ID.equals(""))
				{
					sql += " AND ROWID != '"+ID+"' " ;
				}
				
				sql += " AND UPPER(NAME) = '"+ROLE_NAME.toUpperCase()+"' " ;
				
					
				myLogger.info("V3 - checkROLE :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_ROLE_NAME = "";
				while (rs.next()) {
					
					GET_ROLE_NAME = rs.getString("NAME") == null ? "" : rs.getString("NAME");
					if(!GET_ROLE_NAME.equals(""))
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
	
	
	public String saveRole(HttpSession session,String GROUP,String ROLEUNIK,String ROWID) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		
		String flag_operasi = "INSERT";
		if(!ROWID.equals(""))
		{
			flag_operasi = "UPDATE";
		}
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
			String ROLE_NAME_ = getParam("ROLE_NAME_"+GROUP+ROLEUNIK);
			String DESCRIPTION_ = getParam("DESCRIPTION_"+GROUP+ROLEUNIK);
			String ROLE_DETAILS_ = getParam("ROLE_DETAILS_"+GROUP+ROLEUNIK);
			String GROUP_ = getParam("GROUP_"+GROUP+ROLEUNIK);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(flag_operasi.equals("INSERT"))
			{
				returnUSERID = ROLE_NAME_;
				//r.add("MODULE_ID", MODULE_ID_);				
			}
			else
			{
				returnUSERID = ROLE_NAME_;
				r.update("ROWID", ROWID);
			}
			r.add("NAME", ROLE_NAME_);	
			r.add("DESCRIPTION", DESCRIPTION_);
			r.add("ROLE_DETAILS", ROLE_DETAILS_);
			r.add("THEME", GROUP_);
			
			
			if(flag_operasi.equals("INSERT"))
			{
				sql = r.getSQLInsert("ROLE");		
				myLogger.info("V3 : INSERT ROLE : "+sql);
				this.context.put("SuccessMesej", "Insert");
				AuditTrail.logActivity(this,session,"INS","ROLE ["+ROLE_NAME_+"] Added");				
			}
			else
			{
				sql = r.getSQLUpdate("ROLE");		
				myLogger.info("V3 : UPDATE ROLE : "+sql);
				this.context.put("SuccessMesej", "Update");
				AuditTrail.logActivity(this,session,"UPD","ROLE ROWID ["+ROWID+"] Updated");
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
	
	public String saveGroupRole(HttpSession session,String GROUP,String ROWID,String CURRENT_CSS_NAME,String getvalue_CSS_TITLE,String getvalue_CSS_NAME,String getvalue_MODUL_ID,Db db) throws Exception {
		Connection conn = null;
		Db db1 = null;
		String returnUSERID = "";
		String sql = "";
		
		String flag_operasi = "INSERT";
		if(!ROWID.equals(""))
		{
			flag_operasi = "UPDATE";
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
			//conn = db1.getConnection();
			//conn.setAutoCommit(false);
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
			String CSS_TITLE_ = getvalue_CSS_TITLE;
					//getParam("CSS_TITLE_"+GROUP);
			String CSS_NAME_ = getvalue_CSS_NAME;
					//getParam("CSS_NAME_"+GROUP);
			
			String MODUL_ID_ = getvalue_MODUL_ID;
			
			myLogger.info("***CSS_TITLE_"+GROUP+" : "+CSS_TITLE_+ " >  CSS_NAME_"+GROUP+" : "+CSS_NAME_);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(flag_operasi.equals("INSERT"))
			{
				returnUSERID = CSS_TITLE_;
				//r.add("MODULE_ID", MODULE_ID_);				
			}
			else
			{
				returnUSERID = CSS_TITLE_;
				r.update("ROWID", ROWID);
			}
			r.add("CSS_TITLE", CSS_TITLE_);	
			r.add("CSS_NAME", CSS_NAME_);	
			r.add("MODUL_ID", MODUL_ID_);
			
			if(flag_operasi.equals("INSERT"))
			{
				sql = r.getSQLInsert("PAGE_CSS");		
				myLogger.info("V3 : INSERT PAGE_CSS : "+sql);
				this.context.put("SuccessMesej", "Insert");
				AuditTrail.logActivity(this,session,"INS","PAGE_CSS ["+CSS_TITLE_+"] Added");				
			}
			else
			{
				sql = r.getSQLUpdate("PAGE_CSS");		
				myLogger.info("V3 : UPDATE PAGE_CSS : "+sql);
				this.context.put("SuccessMesej", "Update");
				AuditTrail.logActivity(this,session,"UPD","PAGE_CSS ROWID ["+ROWID+"] Updated");
			}		
			stmt.executeUpdate(sql);
			
			if(!CURRENT_CSS_NAME.equals(""))
			{
				r.clear();
				r.update("THEME", CURRENT_CSS_NAME);
				r.add("THEME", CSS_NAME_);
				sql = r.getSQLUpdate("ROLE");		
				//myLogger.info("V3 : UPDATE PAGE_CSS ROLE : "+sql);
				stmt.executeUpdate(sql);
			}

			//conn.commit();
			
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
			if(db == null)
			{
				if (db1 != null)
					db1.close();
			}
		}
		return returnUSERID;
	}
	
	
	public String getROWID_GROUP(HttpSession session, String CSS_TITLE,Db db) throws Exception {
		Db db1 = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String rid = "";
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
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT P.ROWID AS ID, P.CSS_TITLE FROM PAGE_CSS P WHERE UPPER(P.CSS_TITLE) = '"+CSS_TITLE.toUpperCase()+"' ";			
				myLogger.info("V3 - getROWID_GROUP :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {					
					rid = rs.getString("ID") == null ? "" : rs.getString("ID");					
				}			
			return rid;
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
	}
	
	public String getROWID_ROLE(HttpSession session, String ROLE_NAME) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String rid = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT P.ROWID AS ID, P.NAME FROM ROLE P WHERE UPPER(P.NAME) = '"+ROLE_NAME.toUpperCase()+"' ";			
				myLogger.info("V3 - getROWID_GROUP :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {					
					rid = rs.getString("ID") == null ? "" : rs.getString("ID");					
				}			
			return rid;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	public String replaceString(String asal)
	{		
		return asal.replace(".","_").replace(" ","").replace("/","X_X");
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
	public List listStatModuleRole(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listStatModuleRole = null;
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
			sql = " SELECT UPPER(MODULE_NAME) AS MODULE_NAME,S.ID_SEKSYEN,S.NAMA_SEKSYEN,COUNT(DISTINCT USER_ID) AS TOTAL_PENGGUNA  FROM "+
					" (SELECT U.USER_ID,UR.ROLE_ID, "+
					" REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''),'SabahSarawak','Sabah & Sarawak'),'STR','Strata') "+ 
					" AS MODULE_NAME, UI.ID_SEKSYEN  "+
					" FROM USERS U,USERS_INTERNAL UI,USER_ROLE UR, ROLE R "+
					" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID "+
					" AND UR.ROLE_ID IS NOT NULL AND UR.ROLE_ID = R.NAME "+
					" AND UI.ID_SEKSYEN IS NOT NULL "+
					" UNION ALL "+
					" SELECT U.USER_ID,U.USER_ROLE, "+
					" REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''),'SabahSarawak','Sabah & Sarawak'),'STR','Strata')  "+
					" AS MODULE_NAME,UI.ID_SEKSYEN FROM USERS U, ROLE R, USERS_INTERNAL UI "+
					" WHERE U.USER_ROLE = R.NAME AND U.USER_ID = UI.USER_ID "+
					" AND UI.ID_SEKSYEN IS NOT NULL "+
					" ) MODU, TBLRUJSEKSYEN S "+
					" WHERE MODULE_NAME NOT IN ('default','eTapp')  "+
					" AND MODU.ID_SEKSYEN = S.ID_SEKSYEN AND MODU.ID_SEKSYEN IN (20,1,2,3,4,5,11,12) "+
					" GROUP BY MODULE_NAME,S.NAMA_SEKSYEN,S.ID_SEKSYEN ORDER BY MODULE_NAME, S.ID_SEKSYEN ";
			myLogger.info(" V3: SQL listStatModuleRole :"+ sql);
			rs = stmt.executeQuery(sql);
			listStatModuleRole = Collections.synchronizedList(new ArrayList());
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
				h.put("MODULE_NAME",rs.getString("MODULE_NAME") == null ? "" : rs.getString("MODULE_NAME"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				h.put("TOTAL_PENGGUNA",rs.getString("TOTAL_PENGGUNA") == null ? 0 : rs.getInt("TOTAL_PENGGUNA"));		
				listStatModuleRole.add(h);
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

		return listStatModuleRole;

	}
	
	@SuppressWarnings("unchecked")
	public List listModuleForStat(HttpSession session,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listModuleForStat = null;
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
			sql = 	" SELECT S.ID_SEKSYEN, S.NAMA_SEKSYEN FROM TBLRUJSEKSYEN S " +
					" WHERE S.ID_SEKSYEN IN (20,1,2,3,4,5,11,12) ORDER BY S.ID_SEKSYEN ";
//			myLogger.info(" V3: SQL listModuleForStat :"+ sql);
			rs = stmt.executeQuery(sql);
			listModuleForStat = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				listModuleForStat.add(h);
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

		return listModuleForStat;

	}
	
	//LIST TABLE
			@SuppressWarnings("unchecked")
			public List listTableRujukan(HttpSession session, String tableRujukan, String ID_NEGERI )throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listTableRujukanV3 = null;
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					
					
					if(tableRujukan.equals("TBLRUJMODUL"))
					{ 
						sql = " SELECT ID_MODUL AS ID, KOD_MODUL AS KOD, UPPER(NAMA_MODUL) AS KETERANGAN FROM TBLRUJMODUL ";
					}
					
					else if(tableRujukan.equals("TBLRUJDAERAHBYNEGERI"))
					{
						sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN " +
								" FROM TBLRUJDAERAH " +
								" WHERE ID_NEGERI = "+ID_NEGERI+
								" ORDER BY NAMA_DAERAH, KOD_DAERAH ";
					}
					

				//	myLog.info(" V3: SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
					rs = stmt.executeQuery(sql);
					listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
						h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						listTableRujukanV3.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return listTableRujukanV3;

			}

}
		
		

/*
SELECT UPPER(MODULE_NAME) AS MODULE_NAME,S.ID_SEKSYEN,S.NAMA_SEKSYEN,COUNT(DISTINCT USER_ID) AS TOTAL_PENGGUNA  FROM 
(SELECT U.USER_ID,UR.ROLE_ID,
REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''),'SabahSarawak','Sabah & Sarawak'),'STR','Strata') 
AS MODULE_NAME, UI.ID_SEKSYEN 
FROM USERS U,USERS_INTERNAL UI,USER_ROLE UR, ROLE R
WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID
AND UR.ROLE_ID IS NOT NULL AND UR.ROLE_ID = R.NAME
AND UI.ID_SEKSYEN IS NOT NULL
UNION ALL
SELECT U.USER_ID,U.USER_ROLE,
REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''),'SabahSarawak','Sabah & Sarawak'),'STR','Strata') 
AS MODULE_NAME,UI.ID_SEKSYEN FROM USERS U, ROLE R, USERS_INTERNAL UI
WHERE U.USER_ROLE = R.NAME AND U.USER_ID = UI.USER_ID
AND UI.ID_SEKSYEN IS NOT NULL
) MODU, TBLRUJSEKSYEN S
WHERE MODULE_NAME NOT IN ('default','eTapp') 
AND MODU.ID_SEKSYEN = S.ID_SEKSYEN AND MODU.ID_SEKSYEN IN (20,1,2,3,4,5,11,12)
GROUP BY MODULE_NAME,S.NAMA_SEKSYEN,S.ID_SEKSYEN


SELECT S.ID_SEKSYEN, S.NAMA_SEKSYEN FROM TBLRUJSEKSYEN S WHERE S.ID_SEKSYEN IN (20,1,2,3,4,5,11,12)
*/