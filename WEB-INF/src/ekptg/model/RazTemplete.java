package ekptg.model;


import java.io.StringReader;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.engine.CacheManager;
import ekptg.engine.CachedObject;
import ekptg.engine.StateLookup;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

/*
 * author : RAZMAN MD ZAINAL
 * date start : 02/06/2018 9.00 PM
 * version : 1.0
 * description : 
 * RazTemplete bertujuan untuk memperkenalkan kaedah programing yang lebih standard tetapi dynamic untuk kegunaan modul2/skrin2 baru.
 * Preparation untuk projek myetapp revamp V8
 * 
 * version description :
 * >>Version 1.0
 * 1-
 * 
 * 
 * 
 * Advantage :
 * >>adalah tempoh programming untuk sesuatu skrin akan lebih cepat dan tersusun
 * >>kaedah programming setiap programmer akan lebih standard
 * >>memudahkan programmer untuk membangunkan skrin, hanya mengunakan method yg sedia ada..
 * 
 * DisAdvantage :
 * >>programmer tidak akan merasai keperitan dan kesusahan programming seperti myetapp V1 hingga V6
 * >>method ini hanya sesuai untuk diguna pakai pada skrin2 yang baru hendak di bangunkan, kerana akan menjadi conplex jika nak pakai diskrin2 sedia ada
 * >>customization pada templete ini memerlukan programmer yang mahir & berpengalaman
 * >>jika ada request untuk customization, perlu request pada selected senior programmer(akan ditetapkan kemudian) untuk membuat penambahan dan pengubahsuaian
 * 
 * Warning!! :
 * >>Mana programmer yang ada keperluan mengubahsuai or penambahan pada method ini, mohon advice atau inform PIC(akan ditetapkan kemudian) yg bertanggungjawap 
 * 
 * Amanat :
 * >>marilah bersama memberi kerjasama untuk menjayakan method atau kaedah ini. Method ini akan di naik taraf dari masa ke semasa
 * 
 * **TERM [STANDARD] : merujukan, pekara2 standard/perlu di guna pakai untuk every modul/skrin yg ingin mengunakan kaedah ajax + RazTemplete
 */

public class RazTemplete extends VTemplate {
	
	public Logger myLogger = Logger.getLogger(ekptg.model.RazTemplete.class);
	public ResourceBundle rbRaz = ResourceBundle.getBundle("RazTemplete");
	public String currentSQL = "";  
	public String loadingMsg = "";
	public HttpSession session;
	public String modul = "";
		//public Db dbMain = null;
		//public Connection conn = null;
		
	public void setEnvironmentRT(VelocityEngine engine
		, VelocityContext context, HttpServletRequest request, HttpServletResponse response,HttpSession session, String modul){
	        this.engine = engine;
	        this.context = context;
	        this.request = request;
	        this.response = response;
	        this.session = session;
	        this.modul = modul;
	    }
		
				
		
		public void test()
		{
			 myLogger.info("RazTemplete >>>>>>>> modul : "+modul);
		}
		
		
		//ni standard templete yg kita pakai
		//return jsp
	
		
		//error DB mesej control
		//return skrin name
		public String RTerrorControl(String errorMesej) throws Exception
		{
			//access by ada 2 jenis : programmer or user
			//if programmer, system akan paparkan detail error atau masalah pada skrin
			//if user, hanya paparkan ganeral error mesej
			
			String msq = "";
			String accessBy = rbRaz.getString("accessBy");			
			//String accessBy = "programmer";			
			if(accessBy.equals("user"))
			{
				msq += errorMesej + "<br>" + "SILA HUBUNGI ETAPPSUPPORT ATAU MEMBUAT ADUAN MELALUI PLA. MAAF DIATAS KESULITAN." +
						"" + "<br>" + "currentSQL : "+currentSQL;
			}
			else
			{
				msq +=  errorMesej + "<br>" + "currentSQL : "+currentSQL;
			}
			//send error mesej
			context.put("errorMesej",msq);
			//jika ada error pada SQL, skrin akan di Divert pada skrin error.jsp yang umum supaya paparan error lebih teratur
			return "app/RazTemplate/error.jsp";	
			
		}
		
		
	
		//setup paging untuk senarai
		
		public Map RTsetupPageList(List list,List selectedColumn, 
				//String namaList, 
				//String namaMap, 
				int maxRowNum, String param, String classTable, String skrinName) {
			Paging2 paging = null;
			String htmlPaging = "";
			int page = 0;
			Map h = Collections.synchronizedMap(new HashMap());
			
			int itemsPerPage = 0;
			
			try {
				
				String command = getParam("command");
				String actionajax = getParam("actionajax");				
				String div = getParam("div");
				myLogger.info("setupPageList :::: div"+div);
				String scrolPosition = getParam("scrolPosition");
				myLogger.info("setupPageList :::: scrolPosition :"+scrolPosition);
				context.put("scrolPosition", scrolPosition);
				context.put("namaList", skrinName+"list");
				//context.put("namaMap", namaMap);
				context.put("command", command);
				context.put("actionajax", actionajax);
				context.put("div", div);
				context.put("param", param);				
				context.put("totalRecords", list.size());
				context.put("maxRowNum", maxRowNum);
				page = getParam("page") == "" ? 1 : Integer.parseInt(getParam("page"));
				int page_mula = 1;
				
				String sortColumnType = getParam("sortColumnType"+skrinName+command);
				String sortColumn = getParam("sortColumn"+skrinName+command);
				String sortType = getParam("sortType"+skrinName+command);
				String sortDateFormat = getParam("sortDateFormat"+skrinName+command);
				String sortColumnPosition = getParam("sortColumnPosition"+skrinName+command);
				
				
				if (context.get("itemsPerPage"+skrinName+command) == null
						|| context.get("itemsPerPage"+skrinName+command) == "") {
					itemsPerPage = getParam("itemsPerPage"+skrinName+command) == "" ? 10
							: Integer.parseInt(getParam("itemsPerPage"+skrinName+command));
				} else {
					itemsPerPage = (Integer) context.get("itemsPerPage"+skrinName+command);
				}	
				
				
				
				if (("getNext").equals(actionajax)) {
					page++;
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
					
				} else if (("getPrevious").equals(actionajax)) {
					page--;
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
				} 
				
				else if (("sortColumn").equals(actionajax)) {
				 page = 1;
				 if(list.size()>1)
				 {
					 //myLogger.info("sortDateFormat >>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+sortDateFormat);
					 
					 list  = sortingListSingle(list, 
							 sortColumn.trim(),//nama column
							 sortColumnType.trim(), //datatype,
							 sortType.trim(), // ASC/DESC
							 //,selectedColumn
							 sortDateFormat.trim()
							 );
					 list = searchColumn(list, skrinName.trim(), selectedColumn);		
				 }
				}
				else if (("searchColumn").equals(actionajax)) {
					 page = 1;
					 list = searchColumn(list, skrinName.trim(), selectedColumn);					
				}
				else if (("getPage").equals(actionajax)) {
					page = Integer.parseInt(getParam("value"));
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
				} else if (("doChangeItemPerPage").equals(actionajax)) {
					myLogger.info("namaList : "+skrinName+"; command : "+command+"; itemsPerPage : "+getParam("itemsPerPage"+skrinName+command));
					itemsPerPage = Integer.parseInt(getParam("itemsPerPage"+skrinName+command));
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
					myLogger.info("setupPageList :::: itemsPerPage :"+itemsPerPage);
				}
				else if (("specialFromList").equals(actionajax)) {
					itemsPerPage = Integer.parseInt(getParam("itemsPerPage"+skrinName+command));
					page = Integer.parseInt(getParam("currentPage"+skrinName+command));
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
					myLogger.info("setupPageList :::: itemsPerPage :"+itemsPerPage);
				}	
				
				
				
				paging = new Paging2(session, list, itemsPerPage);
				if (page > paging.getTotalPages())
					page = 1;
				context.put(skrinName+"list", paging.getPage(page));
				context.put("page_mula", new Integer(page_mula));
				context.put("page", new Integer(page));
				context.put("itemsPerPage", new Integer(itemsPerPage));
				context.put("totalPages", new Integer(paging.getTotalPages()));
				context.put("startNumber", new Integer(paging.getTopNumber()));
				context.put("isFirstPage", new Boolean(paging.isFirstPage()));
				context.put("isLastPage", new Boolean(paging.isLastPage()));
				context.put("sortColumnType", sortColumnType);
				context.put("sortDateFormat", sortDateFormat);
				context.put("sortColumn", sortColumn);
				context.put("sortType", sortType);
				
				htmlPaging = setupPagingHTML(skrinName,scrolPosition,command,
						sortType,sortColumn,sortColumnPosition,sortColumnType,sortDateFormat,
						div,
						//namaList,
						//namaMap,
						param,
						new Boolean(paging.isFirstPage()),new Boolean(paging.isLastPage()),
						page,list.size(),maxRowNum,new Integer(itemsPerPage), page_mula, new Integer(paging.getTotalPages()),classTable);
				context.put("htmlPaging", htmlPaging);
				
			} catch (Exception e) {
				e.printStackTrace();
				context.put("errorMesej", e.getMessage());
			}
			
			h.put("htmlPaging",htmlPaging);
			h.put("listRekod",paging.getPage(page));
			
			h.put("startNumber", new Integer(paging.getTopNumber()));
			h.put("itemsPerPage", new Integer(itemsPerPage));
			
			return  h;
		}
		
		
		public Map RTsetupPageListEditor(List list,List selectedColumn, 
				//String namaList, 
				//String namaMap, 
				int maxRowNum, String param, String classTable, String skrinName) {
			Paging2 paging = null;
			String htmlPaging = "";
			int page = 0;
			Map h = Collections.synchronizedMap(new HashMap());
			
			try {
				
				String command = getParam("command");
				String actionajax = getParam("actionajax");	
				myLogger.info("actionajax :::: "+actionajax);
				String div = getParam("div");
				myLogger.info("setupPageList :::: div"+div);
				String scrolPosition = getParam("scrolPosition");
				myLogger.info("setupPageList :::: scrolPosition :"+scrolPosition);
				context.put("scrolPosition", scrolPosition);
				context.put("namaList", skrinName+"list");
				//context.put("namaMap", namaMap);
				context.put("command", command);
				context.put("actionajax", actionajax);
				context.put("div", div);
				context.put("param", param);				
				context.put("totalRecords", list.size());
				context.put("maxRowNum", maxRowNum);
				page = getParam("page") == "" ? 1 : Integer.parseInt(getParam("page"));
				int page_mula = 1;
				
				String sortColumnType = getParam("sortColumnType"+skrinName+command);
				String sortColumn = getParam("sortColumn"+skrinName+command);
				String sortType = getParam("sortType"+skrinName+command);
				String sortDateFormat = getParam("sortDateFormat"+skrinName+command);
				String sortColumnPosition = getParam("sortColumnPosition"+skrinName+command);
				
				int itemsPerPage;
				if (context.get("itemsPerPage"+skrinName+command) == null
						|| context.get("itemsPerPage"+skrinName+command) == "") {
					itemsPerPage = getParam("itemsPerPage"+skrinName+command) == "" ? 10
							: Integer.parseInt(getParam("itemsPerPage"+skrinName+command));
				} else {
					itemsPerPage = (Integer) context.get("itemsPerPage"+skrinName+command);
				}	
				
				
				
				if (("getNext").equals(actionajax)) {
					page++;
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
					
				} else if (("getPrevious").equals(actionajax)) {
					page--;
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
				} 
				
				else if (("sortColumn").equals(actionajax)) {
				 page = 1;
				 if(list.size()>1)
				 {
					 list  = sortingListSingleEditor(list, 
							 sortColumn.trim(),//nama column
							 sortColumnType.trim(), //datatype,
							 sortType.trim(), // ASC/DESC
							 sortDateFormat.trim() // DATE FORMAT JIKA ADA
							 //,selectedColumn
							 );
					 list = searchColumn(list, skrinName.trim(), selectedColumn);		
				 }
				}
				else if (("searchColumn").equals(actionajax)) {
					 page = 1;
					 list = searchColumn(list, skrinName.trim(), selectedColumn);					
				}
				else if (("getPage").equals(actionajax)) {
					page = Integer.parseInt(getParam("value"));
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
				} else if (("doChangeItemPerPage").equals(actionajax)) {
					myLogger.info("namaList : "+skrinName+"; command : "+command+"; itemsPerPage : "+getParam("itemsPerPage"+skrinName+command));
					itemsPerPage = Integer.parseInt(getParam("itemsPerPage"+skrinName+command));
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
					myLogger.info("setupPageList :::: itemsPerPage :"+itemsPerPage);
				}
				else if (("specialFromList").equals(actionajax)) {
					itemsPerPage = Integer.parseInt(getParam("itemsPerPage"+skrinName+command));
					page = Integer.parseInt(getParam("currentPage"+skrinName+command));
					list = searchColumn(list, skrinName.trim(), selectedColumn);	
					myLogger.info("setupPageList :::: itemsPerPage :"+itemsPerPage);
				}	
				
				
				
				paging = new Paging2(session, list, itemsPerPage);
				if (page > paging.getTotalPages())
					page = 1;
				context.put(skrinName+"list", paging.getPage(page));
				context.put("page_mula", new Integer(page_mula));
				context.put("page", new Integer(page));
				context.put("itemsPerPage", new Integer(itemsPerPage));
				context.put("totalPages", new Integer(paging.getTotalPages()));
				context.put("startNumber", new Integer(paging.getTopNumber()));
				context.put("isFirstPage", new Boolean(paging.isFirstPage()));
				context.put("isLastPage", new Boolean(paging.isLastPage()));
				context.put("sortColumnType", sortColumnType);
				context.put("sortColumn", sortColumn);
				context.put("sortType", sortType);
				
				htmlPaging = setupPagingHTML(skrinName,scrolPosition,command,
						sortType,sortColumn,sortColumnPosition,sortColumnType,sortDateFormat,
						div,
						//namaList,
						//namaMap,
						param,
						new Boolean(paging.isFirstPage()),new Boolean(paging.isLastPage()),
						page,list.size(),maxRowNum,new Integer(itemsPerPage), page_mula, new Integer(paging.getTotalPages()),classTable);
				context.put("htmlPaging", htmlPaging);
				
			} catch (Exception e) {
				e.printStackTrace();
				context.put("errorMesej", e.getMessage());
			}
			
			h.put("htmlPaging",htmlPaging);
			h.put("listRekod",paging.getPage(page));
			
			return  h;
		}
	
		
		
	
		//capture all column dibawah sesuatu query
		public List returnColumnNameFromQuery(String query, 
				String skrinName,
				boolean flagCache, boolean resetCache, boolean sortAlpa,String cacheID, Db db)throws Exception {
			List list= null;
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			String sql = "";
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			//myLogger.info(">>>>>>>>>>>>>>>>>>>>>>> returnColumnNameFromQuery cacheID : "+cacheID);
			CachedObject get_CachedObject = null;
			/*
			String cacheID = "";
			if(flagCache == true)
			{
				cacheID = "ColumnName"+skrinName+USER_ID_SYSTEM;
			}	
			myLogger.info("returnColumnNameFromQuery cacheID : "+cacheID);
			*/
			
			if(flagCache == true)
			{
				cacheID = "ColumnName"+cacheID;
			}
			
			if(resetCache == true)
			{
				CacheManager.removeCache(cacheID);
			}
			
			
			if(flagCache == true && !cacheID.equals(""))
			{
				get_CachedObject =  (CachedObject)CacheManager.getCache(cacheID);
				if (get_CachedObject != null)
				{
					list =  (List)get_CachedObject.object;
				}
				else
				{
					CacheManager.removeCache(cacheID);
				}
			}
			
			myLogger.info("returnColumnNameFromQuery get_CachedObject : "+get_CachedObject);
			
			if(get_CachedObject == null)					
			{		
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
					sql = "SELECT * FROM ("+query+") WHERE 1=0";
					currentSQL = sql;
					myLogger.info("returnColumnNameFromQuery : "+sql);
					//rs = stmt.executeQuery("SELECT * FROM ("+query+") WHERE 1=0");
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					rs = executeQueryRT(stmt,sql,methodName);
					
					ResultSetMetaData rsmd = rs.getMetaData();
					
					Map h = null;
					list = Collections.synchronizedList(new ArrayList());	
					//myLogger.info("rsmd.getColumnCount() : "+rsmd.getColumnCount());
					
					for (int i = 1; i <= rsmd.getColumnCount(); i++) 
					{
						
						
						h = Collections.synchronizedMap(new HashMap());
						String name = rsmd.getColumnName(i);
						int type = rsmd.getColumnType(i);
						int getColumnDisplaySize = rsmd.getColumnDisplaySize(i);
						String typeName = rsmd.getColumnTypeName(i);
						
						int nullable = rsmd.isNullable(i);
						String isNotNull = "Y";
						if(nullable == rsmd.columnNullable) {
						    //myLogger.info("COLUMN NAME : "+name+" nullable : "+nullable);
						    isNotNull = "";
						}
					   
						
						//myLogger.info("================== COLMUN NAME : "+name+" DATA TYPE : "+typeName+" SAIZ : "+getColumnDisplaySize);
						
						h.put("COLUMN_NAME",name);
						h.put("COLUMN_TYPE",typeName);
						h.put("COLUMN_SIZE",getColumnDisplaySize);
						h.put("COLUMN_NOTNULL",isNotNull);
						
						String dateType = "";
						if(typeName.equals("DATE"))
						{
							dateType = "yyyy-MM-dd HH:mm:ss.S";
						}
						else if(typeName.equals("TIMESTAMP"))
						{
							dateType = "yyyy-M-dd.H.m. s. S";
						}						
						h.put("DATE_FORMAT",dateType);
						list.add(h);
					}
					//myLogger.info(">>>> list column 1 : "+list);
					if(list.size()>1 && sortAlpa == true)
					{
						list = sortingListSingle(list, "COLUMN_NAME","VARCHAR2", "ASC");
					}
					
				} finally {
					if (db == null)
					{
						db1.close();
					}
				}
				
				//myLogger.info(">>>> list column 2 : "+list);
				
				if(flagCache==true && !cacheID.equals(""))
				{
					CachedObject set_CachedObject = new CachedObject(list, cacheID, 0);
					CacheManager.putCache(set_CachedObject);	
				}
			
			}
			return list;
		}
		//method setup column
		public Map getColumnInfo(String skrinName, String columnName, 
				String label, String align, String listName) 
				throws Exception
		{
			Map h = Collections.synchronizedMap(new HashMap());
			h.put("COLUMN_NAME",columnName);
			h.put("LABEL",label);
			h.put("SKRIN_NAME",skrinName);
			h.put("LIST_NAME",listName);
			h.put("ALIGN",align);
			//myLogger.info(" getColumnForSenarai : "+h);
			return h;	
		}
		
		//standard method to return list object without caching trick
		public List RTListRekodNoCachex(String query, 
				String namaList, // nama list
				String div, // div id
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				String dateFormat,
				Db db)throws Exception {
			   
		return RTListRekod( 
				query, 
				false, // list yg ada cache
				false, // keperluan mereset cache
				"", // unik cache ID
				namaList, // nama list
				div,// div name
				true,// ada paging
				columNameSort, // senarai nama column untuk  kita sort kan
				columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				sortType, // jenis sorting mengikut column
				dateFormat,
				0,
				db);			
		}
		
		//standard method to return list object with caching trick
		public List RTListRekodWithCache(String query, 
				String skrinName,
				String div,
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				String dateFormat,
				int maxRowNum,
				String cacheID,
				Db db)throws Exception {
			   
		List RTListRekod = RTListRekod(
				query, 
				true, // list yg ada cache
				false, // keperluan mereset cache
				cacheID, // unik cache ID
				skrinName, // nama skrinName
				div,// div name
				true,// ada paging
				columNameSort, // senarai nama column untuk  kita sort kan
				columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				sortType, // jenis sorting mengikut column
				dateFormat,
				maxRowNum,
				db);	
		
			Map RTsetupPageList = RTsetupPageList(RTListRekod,null, 
					//namaList, "", 
					maxRowNum, "","",skrinName);
			if(RTsetupPageList != null)
			{
				RTListRekod = (List) RTsetupPageList.get("listRekod");
				
			}		
			return RTListRekod;	
		
		}
		
		
		public String RThtmlViewMapAuto( 
				String command,
				String skrinName,
				String query, 
				String div,
				String mode,
				boolean editable,
				String tableName,
				String filterDB,
				String pkField,
				String param,
				String cacheID,
				Db db)throws Exception {
			String html = "";
			Map mapRekod = RTmapRekod(query, skrinName+"map",cacheID, db);
			List allColumnDefault = returnColumnNameFromQuery(query,skrinName+"map",true,false,true, cacheID,db);	
			if (allColumnDefault.size() != 0) {
				int columnSize = allColumnDefault.size();
				int columnSizeDevide = Math.round(columnSize/2);
				
				html += "<script>$jquery(document).ready(function () {";
				html += " $jquery('#'+'divView"+skrinName+getParam(pkField)+"').scrollView(); ";				
				html += "});</script>";
					
				
				html += "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > ";
				for (int i = 0; i < columnSize; i++) {
					
					if(columnSize > 1 && i == 0)
					{
						html += "<tr><td width='50%' valign='top'>";
						html += openHTMLTable();
					}					
					else if(columnSize > 1 && columnSizeDevide == i )
					{
						html += closeHTMLTable();
						html += "</td><td width='50%' valign='top'>";
						html += openHTMLTable();
					}
					else if(columnSize <= 1 && i == 0)
					{
						html += "<tr><td width='100%' valign='top'>";
						html += openHTMLTable();
					}
					
					
					Map map_column_name = (Map) allColumnDefault.get(i);
					String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
					String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");
					String COLUMN_NOTNULL = (String) map_column_name.get("COLUMN_NOTNULL");
					
					boolean mandatory = false;
					if(COLUMN_NOTNULL.equals("Y"))
					{
						 mandatory = true;
					}
					
					int COLUMN_SIZE = (Integer) map_column_name.get("COLUMN_SIZE");	
					
					if(COLUMN_TYPE.equals("CLOB"))
					{
						COLUMN_SIZE = 9999999;
					}
					
					
					String valueFromMap = (String) mapRekod.get(COLUMN_NAME);						
					String checkMode = mode;
					if(COLUMN_NAME.equals("ID_MASUK") || COLUMN_NAME.equals("ID_KEMASKINI") || COLUMN_NAME.equals("TARIKH_MASUK") || COLUMN_NAME.equals("TARIKH_KEMASKINI"))
					{
						checkMode = "view";
					}					
					
					//myLogger.info(">>>>>>>>>>>>>>>>>>>> COLUMN_NAME : "+COLUMN_NAME+" COLUMN_TYPE : "+COLUMN_TYPE+" pkField : "+pkField+" COLUMN_SIZE : "+COLUMN_SIZE);
					
					if(!COLUMN_TYPE.equals("BLOB"))
					{
						if(COLUMN_TYPE.equals("DATE"))
						{
							html += setRowTarikhAuto(skrinName,//skrinName
									checkMode,mapRekod,//label field 
									COLUMN_NAME,mandatory,// nama & id field, sama kan dengan nama filed dalam DB
									10,//setting maxlength
									"",//default value
									db //db object
									);
						}
						else
						{
							//myLogger.info("COLUMN_NAME : "+COLUMN_NAME+" SCRIPT TYPE : "+convertDBtype(COLUMN_TYPE));	
							if(pkField.equals(COLUMN_NAME))
							{
								checkMode = "view"; //PK xbleh edit
							}
							
							if(COLUMN_SIZE < 201)
							{
								html += setRowTextAuto(skrinName,checkMode,mapRekod,COLUMN_NAME,mandatory,convertDBtype(COLUMN_TYPE),COLUMN_SIZE,db);
							}
							else
							{
								html += setRowTextAreaAuto(skrinName,checkMode,mapRekod,COLUMN_NAME,mandatory,convertDBtype(COLUMN_TYPE),COLUMN_SIZE,20,db);
							}
						}
					}
					
					if(columnSize == (i+1))
					{
						html += closeHTMLTable();
						html += "</td></tr>";
					}				
				}
				html += "</table>";
				
				if(editable == true)
				{
					if(mode.equals("view"))
					{
						html += setRowEditButtonCRUD(skrinName,
								//namaList,
								pkField, filterDB, tableName, param);
					}
					else if(mode.equals("edit"))
					{
						html += setRowSaveButtonCRUD(skrinName,
								//namaList,
								pkField, filterDB, tableName, param);
					}					
				}
				//html += "</div>";
				
				
			}	
			
			
			return html;				
		}
			
		
		@SuppressWarnings("unchecked")	
		public String setRowEditButtonCRUD(String skrinName,
				//String namaList,
				String PK_FIELD, String filterDB, String tableName, String param) throws Exception {
			String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'  >";
			html += "<tr>";
			html += "<td align='center' valign='top' width='100%' style='border-top: 1px solid #000;' >";
			html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Kemaskini' onClick=\"doDivAjaxCall"+getFormName()+"('divView"+skrinName+getParam(PK_FIELD)+"','editCrud','&div=divView"+skrinName+getParam(PK_FIELD)+"&skrinName="+skrinName+"&"+PK_FIELD+"="+getParam(PK_FIELD)+"&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());\" > ";
			html += "<input type='button' id='cmdTutup"+skrinName+"' name='cmdTutup"+skrinName+"' value='Tutup' onClick=\"doDivAjaxCall"+getFormName()+"('viewCrud"+skrinName+"','refreshListCrud','&div=viewCrud"+skrinName+"&skrinName="+skrinName+"&"+PK_FIELD+"={"+PK_FIELD+"}&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());\" > ";	   
			html +=	"</td>";
			html += "</tr></table>";
			return html;
		}
		
		@SuppressWarnings("unchecked")	
		public String setRowSaveButtonCRUD(String skrinName,
				//String namaList,
				String PK_FIELD, String filterDB, String tableName, String param) throws Exception {
			String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px'  >";
			html += "<tr>";
			html += "<td align='center' valign='top' width='100%' style='border-top: 1px solid #000;' >";
			if(!getParam(PK_FIELD).equals(""))
			{
				html += "<input type='button' id='cmdSave"+skrinName+"' name='cmdSave"+skrinName+"' value='Simpan' onClick=\"if(confirm('Data akan disimpan. Adakah Anda Pasti?')){doDivAjaxCall"+getFormName()+"('divView"+skrinName+getParam(PK_FIELD)+"','saveCrud','&div=divView"+skrinName+getParam(PK_FIELD)+"&skrinName="+skrinName+"&"+PK_FIELD+"="+getParam(PK_FIELD)+"&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());}\" > ";
			}
			else
			{
				html += "<input type='button' id='cmdSave"+skrinName+"' name='cmdSave"+skrinName+"' value='Tambah' onClick=\"if(confirm('Data akan ditambah. Adakah Anda Pasti?')){doDivAjaxCall"+getFormName()+"('viewCrud"+skrinName+"','insertCrud','&div=viewCrud"+skrinName+"&skrinName="+skrinName+"&"+PK_FIELD+"=&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());}\" > ";	   
			}
			html += "<input type='button' id='cmdKemaskini"+skrinName+"' name='cmdKemaskini"+skrinName+"' value='Batal' onClick=\"doDivAjaxCall"+getFormName()+"('divView"+skrinName+getParam(PK_FIELD)+"','editCrud','&div=divView"+skrinName+getParam(PK_FIELD)+"&skrinName="+skrinName+"&"+PK_FIELD+"="+getParam(PK_FIELD)+"&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());\" > ";
			if(!getParam(PK_FIELD).equals(""))
			{
				html += "<input type='button' id='cmdHapus"+skrinName+"' name='cmdHapus"+skrinName+"' value='Hapus' onClick=\"if(confirm('Data akan dipadam. Adakah Anda Pasti?')){doDivAjaxCall"+getFormName()+"('viewCrud"+skrinName+"','deleteCrud','&div=viewCrud"+skrinName+"&skrinName="+skrinName+"&"+PK_FIELD+"={"+PK_FIELD+"}&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());}\" > ";	   
			}
			html += "<input type='button' id='cmdTutup"+skrinName+"' name='cmdTutup"+skrinName+"' value='Tutup' onClick=\"doDivAjaxCall"+getFormName()+"('viewCrud"+skrinName+"','refreshListCrud','&div=viewCrud"+skrinName+"&skrinName="+skrinName+"&"+PK_FIELD+"={"+PK_FIELD+"}&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());\" > ";	   
			html +=	"</td>";
			html += "</tr></table>";
			return html;
		}
		
		
		
		
		public String convertDBtype(String COLUMN_TYPE)
		{
			String type = "text";
			if(COLUMN_TYPE.contains("CHAR"))
			{
				type = "text";
			}
			else if(COLUMN_TYPE.equals("NUMBER") || COLUMN_TYPE.equals("INTEGER"))
			{
				type = "numbersOnly";
			}			
			return type;
		}
		
		public Map RTmapRekod(String query, String skrinName,String cacheID, Db db)throws Exception {
			return RTmapRekod(query, skrinName, cacheID, null, db);
		}
		
		public Map RTmapRekod(String query, String skrinName,String cacheID, Connection conn, Db db)throws Exception {
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			List allColumn = returnColumnNameFromQuery(query,skrinName,true,false,true,cacheID,db);			
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			String sql = "";
			Map h = Collections.synchronizedMap(new HashMap());	
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
				sql += query;
				myLogger.info(" RTmapRekod : "+sql);
				currentSQL = sql;
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				rs = executeQueryRT(stmt, sql ,methodName);
				//rs = executeQueryRTNew(stmt, sql, methodName, conn, db1);
					
				while (rs.next()) {	
					if (allColumn.size() != 0) {
						for (int i = 0; i < allColumn.size(); i++) {
							Map map_column_name = (Map) allColumn.get(i);
							String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");
							//myLogger.info(">>>>> COLUMN_NAME : "+COLUMN_NAME);
							String VALUE = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME));							
							//by default, column list kita jadikan string
							if(COLUMN_TYPE.equals("DATE") && !VALUE.equals("") && VALUE!= null)
							{
								VALUE = convertFormatDate(VALUE);
							}
							
							
							h.put(COLUMN_NAME,rs == null ? "" :VALUE == null ? "" : VALUE);
						}					
					}
				}
				rs.close();
			} finally {
				if (db == null)
				{
					db1.close();
				}
			}
				
			return h;
		}
		
		//full method to return list object with caching trick
				@SuppressWarnings("unchecked")
				public List RTListRekod(String query, 
						//List listColumnForSenarai, 
						boolean flagCache, // list yg ada cache
						boolean resetCache, // keperluan mereset cache
						String cacheID, // unik cache ID
						String skrinName, // nama skrin
						String div, // div list load
						boolean showPaging, // show Pagingnation
						String columNameSort, // senarai nama column untuk  kita sort kan
						String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
						String sortType, // jenis sorting mengikut column
						String dateFormat,
						int maxRowNum,Db db)throws Exception {
					
					return RTListRekod(query, 
							//List listColumnForSenarai, 
							 flagCache, // list yg ada cache
							 resetCache, // keperluan mereset cache
							 cacheID, // unik cache ID
							 skrinName, // nama skrin
							 div, // div list load
							 showPaging, // show Pagingnation
							 columNameSort, // senarai nama column untuk  kita sort kan
							 columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
							 sortType, // jenis sorting mengikut column
							 dateFormat,
							 maxRowNum, null,				
							 db);
				}
			
		//full method to return list object with caching trick
		@SuppressWarnings("unchecked")
		public List RTListRekod(String query, 
				//List listColumnForSenarai, 
				boolean flagCache, // list yg ada cache
				boolean resetCache, // keperluan mereset cache
				String cacheID, // unik cache ID
				String skrinName, // nama skrin
				String div, // div list load
				boolean showPaging, // show Pagingnation
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				String dateFormat,
				int maxRowNum, Connection conn,				
				Db db)throws Exception {
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			String command = getParam("command");
			String actionajax = getParam("actionajax");		
			
			String html = "";
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List list = null;
			
			String sql = "";
			String getCacheID = "";			
			
			CachedObject get_CachedObject = null;
			
			if(flagCache == true && cacheID.equals(""))
			{
				cacheID = "List"+cacheID;
			}
			
			if(flagCache == true && !cacheID.equals(""))
			{
				getCacheID = cacheID;
				get_CachedObject =  (CachedObject)CacheManager.getCache(getCacheID);
				myLogger.info("check get_CachedObject : "+get_CachedObject);
				if (get_CachedObject != null)
				{
					list =  (List)get_CachedObject.object;
				}
				else
				{
					CacheManager.removeCache(cacheID);
				}
			}
			
			myLogger.info(" >>>>>>>>>>>>>>>>>>> cacheID : "+cacheID+" RTListRekod get_CachedObject : "+get_CachedObject);
			List allColumn = null;
			if(get_CachedObject != null)
			{
				//reuse cache column
				allColumn = returnColumnNameFromQuery(query, skrinName+"list", true, false, true, cacheID, db);
			}
			
			
			
			if(actionajax.equals("") || get_CachedObject == null)					
			{	
				myLogger.info("reset get_CachedObject");
				//clear cache column dlu jika ada, then set balik
				allColumn = returnColumnNameFromQuery(query, skrinName+"list", true, true, true, cacheID, db);
				
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
															
					sql += query;
					
					myLogger.info(" RTListRekod : "+sql);
					currentSQL = sql;
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					
					
					rs = executeQueryRT(stmt, sql ,methodName);
					//rs = executeQueryRTNew(stmt, sql, methodName, conn, db1);
					
					//rs = stmt.executeQuery(sql);
					list = Collections.synchronizedList(new ArrayList());
				
					//myLogger.info("allColumn : "+allColumn);
					Map h = null;
					
					int BIL = 0;
					while (rs.next() && ((maxRowNum > 0 && BIL < maxRowNum) || maxRowNum == 0)) 
					{
						BIL++;
						
						
						h = Collections.synchronizedMap(new HashMap());
						String ROWCSS = "";
						
						if ( (BIL % 2) == 0 )
						{
							ROWCSS = "row2";
						}
				        else
				        {
				        	ROWCSS = "row1";
				        }
				        			
						
						h.put("ROWCSS",ROWCSS); //reserve.. mesti ada walaupun tidak pakai or display
						h.put("BILSORT",BIL); //reserve.. mesti ada walaupun tidak pakai or display
						
						
						if (allColumn.size() != 0) {
							for (int i = 0; i < allColumn.size(); i++) {
								Map map_column_name = (Map) allColumn.get(i);
								String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
								String VALUE = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME));
								//myLogger.info("COLUMN_NAME >>> : "+COLUMN_NAME);
								//by default, column list kita jadikan string
								h.put(COLUMN_NAME,rs == null ? "" :VALUE == null ? "" : VALUE);
							}					
						}
												
						list.add(h);
					}
					//myLogger.info(">>>>>>>> List 1 : "+list+" size : "+list.size());
					rs.close();
					
					/*
					if(list.size() > 0 && !columNameSort.equals("") && !sortType.equals("") && !columnTypeSort.equals(""))
					{						
						myLogger.info(">>>>>>>> List 2 : "+list+" size : "+list.size());
						
						if(list.size()>1)
						{
							list = sortingListCombined(list, columNameSort,columnTypeSort, sortType, dateFormat);
						}
					}
					*/
					
				} finally {
					if (db == null)
					{
						db1.close();
					}
				}
				
				if(flagCache==true && !cacheID.equals(""))
				{
					CachedObject set_CachedObject = new CachedObject(list, getCacheID, 0);
					CacheManager.putCache(set_CachedObject);	
				}
			}		
			
			context.put("namaList", skrinName+"list");
			context.put("div", div);
			context.put("showPaging", showPaging);
			context.put("totalRecords", list.size());
			//myLogger.info(">>>>>>>> List 3 : "+list+" size : "+list.size());
			
			
			
			
			
			return list;
		}
		
		
		public void afterSortCss(List list)
		{
			int BILSORT = 0;
			for (int i = 0; i < list.size(); i++) 
			{
				BILSORT++;
				String ROWCSSSORT = "";
				if ( (BILSORT % 2) == 0 )
				{
					ROWCSSSORT = "row2";
				}
		        else
		        {
		        	ROWCSSSORT = "row1";
		        }	
				Map mapSort = (Map) list.get(i);	
				mapSort.put("BILSORT", BILSORT);
				mapSort.put("ROWCSS", ROWCSSSORT);						
			}
		}
		
		
		
		public List sortingListCombined(List listAsal, String columNameSort,
				String columnTypeSort, String sortType) throws Exception {
			return sortingListCombined(listAsal, columNameSort,
					columnTypeSort, sortType, "");
		}
		
		
        //close sementara
		public List sortingListCombined(List listAsal, String columNameSort,
				String columnTypeSort, String sortType, String dateFormat) throws Exception {
			myLogger.info("sortingListCombined ::::::: ");
			List sortedList = Collections.synchronizedList(new ArrayList());
			//List 
			sortedList = listAsal;
			Object listTemp = Collections.synchronizedList(new ArrayList());
			
			if (listAsal.size() > 1) {
				
				String[] columnToSort = columNameSort.split(",");
				String[] columnTypeToSort = columnTypeSort.split(",");
				String[] typeOfSort = sortType.split(",");
				String[] dateFormatOfSort = dateFormat.split(",");
				for (int m = columnToSort.length-1; m >=0 ; m--) {
					myLogger.info("sortingListCombined >>>>>>>>> columnName : "+columnToSort[m]+" >>>>>>>>> SORT : "+typeOfSort[m]+" >>>>>>>>> columnType : "+columnTypeToSort[m]+" >>>>>>>>> dateFormatOfSort : "+dateFormatOfSort[m]);				
					/*
					if(columnTypeToSort[m].equals("DATE") || columnTypeToSort[m].equals("TIMESTAMP"))
					{
						Collections.sort(sortedList, new MapComparator(columnToSort[m],typeOfSort[m],columnTypeToSort[m],dateFormatOfSort[m]));
					}
					else
					{
						Collections.sort(sortedList, new MapComparator(columnToSort[m],typeOfSort[m],columnTypeToSort[m]));
					}
					*/
					if(columnTypeToSort[m].equals("DATE") || columnTypeToSort[m].equals("TIMESTAMP") && !dateFormat.equals(""))
					{					
						if(typeOfSort[m].equals("ASC"))
						{
							Collections.sort(sortedList, new MapComparator(columnToSort[m],typeOfSort[m],columnTypeToSort[m],dateFormatOfSort[m]));
						}
						else if(typeOfSort[m].equals("DESC"))
						{
							Collections.sort(sortedList, new MapComparator(columnToSort[m],typeOfSort[m],columnTypeToSort[m],dateFormatOfSort[m]));
							Collections.reverse(sortedList);							
						}							
					}
					else
					{
						
						if(typeOfSort[m].equals("ASC"))
						{
							Collections.sort(sortedList, new MapComparator(columnToSort[m],typeOfSort[m],columnTypeToSort[m]));
						}
						else if(typeOfSort[m].equals("DESC"))
						{
							Collections.sort(sortedList, new MapComparator(columnToSort[m],typeOfSort[m],columnTypeToSort[m]));
							Collections.reverse(sortedList);
						}
						
					}
					
				}
				
				
				/*
				for (int j = 0; j < listAsal.size() - 1; j++) {
					for (int i = 0; i < listAsal.size() - 1; i++) {
						
						String[] columnToSort = columNameSort.split(",");
						String[] columnTypeToSort = columnTypeSort.split(",");
						String[] typeOfSort = sortType.split(",");
						for (int m = columnToSort.length-1; m >=0 ; m--) {
							
							Map map1 = (Map) listAsal.get(i);
							String value1 = (String) map1.get(columnToSort[m]);
							Map map2 = (Map) listAsal.get(i + 1);
							String value2 = (String) map2.get(columnToSort[m]);
	
							//myLogger.info(" columnTypeToSort[m]"+columnTypeToSort[m]);
							
							long number1 = 0;
							long number2 = 0;
							if (columnTypeToSort[m].equals("NUMBER") || columnTypeToSort[m].equals("INTEGER")) {
								if (!value1.equals("")) {
									number1 = Long.parseLong(value1);
								}
								if (!value2.equals("")) {
									number2 = Long.parseLong(value2);
								}
							}
	
							Date date1 = new Date();
							Date date2 = new Date();
							if (columnTypeToSort[m].equals("DATE")) {
								if (!value1.equals("")) {
									date1 = stringToDate(value1, "dd/MM/yyyy");
								}
								if (!value2.equals("")) {
									date2 = stringToDate(value2, "dd/MM/yyyy");
								}
							}
							
							if ((columnTypeToSort[m].contains("CHAR") && compareTwoString(value1, value2).equals(typeOfSort[m])) // TYPE STRING COMPARISON
									|| ((columnTypeToSort[m].equals("NUMBER") || columnTypeToSort[m].equals("INTEGER")) && compareTwoNumber(number1, number2).equals(typeOfSort[m])) // TYPE NUMBER COMPARISON
									|| (columnTypeToSort[m].equals("DATE") && compareTwoDate(date1, date2).equals(typeOfSort[m]))) 
							{
								listTemp = listAsal.get(i);
								listAsal.set(i, listAsal.get(i + 1));
								listAsal.set(i + 1, listTemp);
								sortedList = listAsal;
							}
							else
							{
								sortedList = listAsal;
							}
						}
					}
				}
				*/
			}
			//myLogger.info(" sortedList Combined ::: " + sortedList);
			
			afterSortCss(sortedList);
			return sortedList;
		}
		
		
		
		public List sortingListSingle(List listAsal, String columnName, String columnType, String SORT) throws Exception
		{
			return sortingListSingle(listAsal, columnName, columnType, SORT, ""); 
		}
		
		
		
		public List sortingListSingle(List listAsal, String columnName, String columnType, String SORT, String dateFormat) throws Exception
		{
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			//myLogger.info("sortingListSingle ::::::: dateFormat :::: "+dateFormat);
			/*
			String regDisplay  = "";
			for (int cn = 0; cn < listColumn.size(); cn++) {
				Map mapCNquery = (Map) listColumn.get(cn);
				String COLUMN_NAME_QUERY = (String) mapCNquery.get("COLUMN_NAME");
				
				if(COLUMN_NAME_QUERY.equals(columnName))
				{
					regDisplay = (String) mapCNquery.get("DISPLAY");
					break;
				}
				
			}
			*/
			
			//myLogger.info("nonSorted columnName : "+columnName+" columnType : "+columnType+" SORT : "+SORT+" nonSortedList single  ::: "+listAsal);
			List sortedList = Collections.synchronizedList(new ArrayList());
			//List sortedList = listAsal;
			sortedList = listAsal;
			Object listTemp = Collections.synchronizedList(new ArrayList());
			if (listAsal.size() > 1) {
				
				//Collections.sort(listAsal, Collections.reverseOrder());
				//myLogger.info("sortingListSingle >>>>>>>>> columnName : "+columnName+" >>>>>>>>> SORT : "+SORT+" >>>>>>>>> columnType : "+columnType);
				
				if(columnType.equals("DATE") || columnType.equals("TIMESTAMP") && !dateFormat.equals(""))
				{					
					if(SORT.equals("ASC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
					}
					else if(SORT.equals("DESC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
						Collections.reverse(sortedList);
						
					}					
					//Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
				}
				else
				{
					
					if(SORT.equals("ASC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType));
					}
					else if(SORT.equals("DESC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType));
						Collections.reverse(sortedList);
					}
					
					//Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType));
				}
				
				

				/*
				for (int j = 0; j < listAsal.size() - 1; j++) {
					for (int i = 0; i < listAsal.size() - 1; i++) {
						Map map1 = (Map) listAsal.get(i);	
						String value1 = (String) map1.get(columnName);
						Map map2 = (Map) listAsal.get(i + 1);	
						String value2 = (String) map2.get(columnName);
						
						long number1 = 0;
						long number2 = 0;
						Date date1 = new Date();
						Date date2 = new Date();
						
						if(columnType.equals("NUMBER") || columnType.equals("INTEGER"))
						{
							if(!value1.equals(""))
							{
								number1 = Long.parseLong(value1);
							}
							if(!value2.equals(""))
							{
								number2 = Long.parseLong(value2);
							}
						}
						else if(columnType.equals("DATE"))
						{
							if(!value1.equals(""))
							{
								date1 = stringToDate(value1,"dd/MM/yyyy");
							}
							if(!value2.equals(""))
							{
								date2 = stringToDate(value2,"dd/MM/yyyy");
							}
						}
						else if(columnType.equals("TIMESTAMP"))
						{
							if(!value1.equals(""))
							{
								date1 = stringToDate(value1,"yyyy-M-dd.H.m. s. S");
							}
							if(!value2.equals(""))
							{
								date2 = stringToDate(value2,"yyyy-M-dd.H.m. s. S");
							}
						}
						
						if (
								(columnType.contains("CHAR") && compareTwoString(value1, value2).equals(SORT)) // TYPE STRING COMPARISON
								|| ((columnType.equals("NUMBER") || columnType.equals("INTEGER")) && compareTwoNumber(number1, number2).equals(SORT)) // TYPE NUMBER COMPARISON
								|| ((columnType.equals("TIMESTAMP") || columnType.equals("DATE")) && compareTwoDate(date1, date2).equals(SORT))
								) 
			            {
						
							listTemp = listAsal.get(i);	
							listAsal.set(i,listAsal.get(i + 1));
							listAsal.set(i + 1,listTemp);
							sortedList = listAsal;
			            }
						else
						{
							sortedList = listAsal;
						}
											
			        }
				}
				*/			
			}
			afterSortCss(sortedList);
			//myLogger.info(" sortedList single ::: "+sortedList);		
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			myLogger.info("**SORTED LIST SINGLE, TIME TAKEN : "+finish+" SECS");
			return sortedList;
		}
		
		
		public List sortingListSingleEditor(List listAsal, String columnName, String columnType, String SORT, String dateFormat) throws Exception
		{
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			/*
			String regDisplay  = "";
			for (int cn = 0; cn < listColumn.size(); cn++) {
				Map mapCNquery = (Map) listColumn.get(cn);
				String COLUMN_NAME_QUERY = (String) mapCNquery.get("COLUMN_NAME");
				
				if(COLUMN_NAME_QUERY.equals(columnName))
				{
					regDisplay = (String) mapCNquery.get("DISPLAY");
					break;
				}
				
			}
			*/
			
			//myLogger.info("nonSorted columnName : "+columnName+" columnType : "+columnType+" SORT : "+SORT+" nonSortedList single  ::: "+listAsal);
			List sortedList = Collections.synchronizedList(new ArrayList());
			//List 
			sortedList = listAsal;
			Object listTemp = Collections.synchronizedList(new ArrayList());
			if (listAsal.size() > 1) {
				
				myLogger.info("sortingListSingleEditor >>>>>>>>> columnName : "+columnName+" >>>>>>>>> SORT : "+SORT+" >>>>>>>>> columnType : "+columnType);
				
				//Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
				
				if(columnType.equals("DATE") || columnType.equals("TIMESTAMP") && !dateFormat.equals(""))
				{					
					if(SORT.equals("ASC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
					}
					else if(SORT.equals("DESC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
						Collections.reverse(sortedList);
						
					}					
					//Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType,dateFormat));
				}
				else
				{
					
					if(SORT.equals("ASC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType));
					}
					else if(SORT.equals("DESC"))
					{
						Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType));
						Collections.reverse(sortedList);
					}
					
					//Collections.sort(sortedList, new MapComparator(columnName,SORT,columnType));
				}
				
				
				
				/*
				for (int j = 0; j < listAsal.size() - 1; j++) {
					for (int i = 0; i < listAsal.size() - 1; i++) {
						Map map1 = (Map) listAsal.get(i);	
						String value1 = (String) map1.get(columnName);
						Map map2 = (Map) listAsal.get(i + 1);	
						String value2 = (String) map2.get(columnName);
						
						Date date1 = new Date();
						Date date2 = new Date();
						long number1 = 0;
						long number2 = 0;
						if(columnType.equals("NUMBER") || columnType.equals("INTEGER"))
						{
							//myLogger.info(">>>> value1 : "+value1);
							if(!value1.equals(""))
							{
								number1 = Long.parseLong(value1);
							}
							if(!value2.equals(""))
							{
								number2 = Long.parseLong(value2);
							}
						}
						else if(columnType.equals("DATE"))
						{
							if(!value1.equals(""))
							{
								date1 = stringToDate(value1,"yyyy-MM-dd HH:mm:ss.S");
							}
							if(!value2.equals(""))
							{
								date2 = stringToDate(value2,"yyyy-MM-dd HH:mm:ss.S");
							}
						}
						else if(columnType.equals("TIMESTAMP"))
						{
							if(!value1.equals(""))
							{
								date1 = stringToDate(value1,"yyyy-M-dd.H.m. s. S");
							}
							if(!value2.equals(""))
							{
								date2 = stringToDate(value2,"yyyy-M-dd.H.m. s. S");
							}
						}
						
						//myLogger.info("columnType >>>>>>>>> "+columnType);

						if (
								(columnType.contains("CHAR") && compareTwoString(value1, value2).equals(SORT)) // TYPE STRING COMPARISON
								|| ((columnType.equals("NUMBER") || columnType.equals("INTEGER")) && compareTwoNumber(number1, number2).equals(SORT)) // TYPE NUMBER COMPARISON
								|| ((columnType.equals("DATE") || columnType.equals("TIMESTAMP")) && compareTwoDate(date1, date2).equals(SORT))
								) 
			            {
							listTemp = listAsal.get(i);	
							listAsal.set(i,listAsal.get(i + 1));
							listAsal.set(i + 1,listTemp);
							sortedList = listAsal;
			            }
						else
						{
							sortedList = listAsal;
						}
											
			        }
				}
				*/
				
			}
			//afterSortCss(sortedList);
			//myLogger.info(" sortedList single ::: "+sortedList);	
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			myLogger.info("**SORTED LIST EDITOR, TIME TAKEN : "+finish+" SECS");
			return sortedList;
		}
		
		
		public String repPaternRegBracket(String str, Map data)
		{
			String newStr = str;
			Pattern p = Pattern.compile("\\{(.*?)\\}");
			Matcher m = p.matcher(str);
			
			while(m.find()) {
				String fieldName = m.group(1);			  
			    String value = (String) data.get(fieldName);
			    //myLogger.info(str+" >>> paternRegBracket : "+fieldName+" value : "+value);	
			    newStr = newStr.replace("{"+fieldName+"}", value);			    
			}			
			return newStr;
		}
		
		
		
		
		public List searchColumn(List listAsal, String namaList, List listColumn) throws Exception
		{
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			//myLogger.info("searchColumn namaList : "+namaList+" valueSearch : "+listColumn+" nonSortedList single  ::: "+listAsal+" size : "+listAsal.size());
			List sortedList = Collections.synchronizedList(new ArrayList());
			//List sortedList = listAsal;
			//Object listTemp = Collections.synchronizedList(new ArrayList());
			
			//myLogger.info(" >>>>>>>>>>>>> listColumn : "+listColumn);
			boolean doSearching = false;
			if (listColumn != null && listColumn.size() > 1) {
				for (int x = 0; x < listColumn.size(); x++) {
					Map mapCNquery = (Map) listColumn.get(x);
					String COLUMN_NAME_QUERY = (String) mapCNquery.get("COLUMN_NAME");
					String valueCarian = getParam("cs"+COLUMN_NAME_QUERY+namaList);
					if(!valueCarian.equals(""))
					{
						doSearching = true;
					}
				}
			}
			myLogger.info(" >>>>>>>>>>>>> doSearching : "+doSearching);
			if (listAsal.size() > 1 && doSearching == true) {
				for (int j = 0; j < listAsal.size(); j++) {
					Map map1 = (Map) listAsal.get(j);	
					
					boolean mapMatch = true;
					if (listColumn != null && listColumn.size() > 1) {
						for (int cn = 0; cn < listColumn.size(); cn++) {
							Map mapCNquery = (Map) listColumn.get(cn);
							//myLogger.info(">>>>>>>>>>>>>>> mapCNquery : "+mapCNquery);
							String COLUMN_NAME_QUERY = (String) mapCNquery.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) mapCNquery.get("COLUMN_TYPE");
							String valueCarian = getParam("cs"+COLUMN_NAME_QUERY+namaList);
							String valueList = map1.get(COLUMN_NAME_QUERY) == null ? "" :  (String) map1.get(COLUMN_NAME_QUERY);	
							valueList  = removeHtmlTags(valueList);//remove html tag
							String regDisplay = (String) mapCNquery.get("DISPLAY");
							//String valueList = repPaternRegBracket(regDisplay,map1);
							
							if(!COLUMN_NAME_QUERY.equals(""))
							{
								
								if(COLUMN_TYPE.equals("DATE") && !valueList.equals("") && valueList!= null)
								{
									valueList = convertFormatDate(valueList);									
								}
								
								//myLogger.info("valueList : "+valueList+" valueCarian : "+valueCarian + " COLUMN_NAME_QUERY : "+COLUMN_NAME_QUERY);
								if(!(valueList.toUpperCase()).contains(valueCarian.toUpperCase()) && !valueCarian.equals(""))
								{ 	
									mapMatch = false;
								}
							}
						}
					}
					if(mapMatch == true)
					{
						//myLogger.info("ada baru add >>> "+j);
						sortedList.add(map1);
					}
											        
				}			
			}
			else
			{
				sortedList = listAsal;
			}
			
			afterSortCss(sortedList);
			//myLogger.info(" sortedList single ::: "+sortedList);		
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			myLogger.info("**SEARCH LIST, TIME TAKEN : "+finish+" SECS");
			return sortedList;
			
		}
		
		
		
		public Date stringToDate(String strDate,String format) throws Exception {
			DateFormat sourceFormat = new SimpleDateFormat(format);
			Date date = sourceFormat.parse(strDate);
			return date;
		}
		
		//comparison with string value
		public String compareTwoDate(Date A, Date B)
		{
			String flag_compare = "";
			
			if (
					//A.before(B)
					A.compareTo(B) < 0
					){
				flag_compare = "DESC";
			} else if (
					//A.after(B)
					A.compareTo(B) > 0
					) {
				flag_compare = "ASC";
			} else {
				flag_compare = "EQUAL";
			}
			
			//myLogger.info(" A : "+A+" B : "+B+" flag_compare : "+flag_compare);
			return flag_compare;
		}
		
		
		//comparison with string value
		public String compareTwoString(String A, String B)
		{
			String A1 = A.toUpperCase();
			String B1 = B.toUpperCase();
			String flag_compare = "";
			int compare = A1.compareTo(B1);
			if (compare < 0){
				flag_compare = "DESC";
			} else if (compare > 0) {
				flag_compare = "ASC";
			} else {
				flag_compare = "EQUAL";
			}
			//myLogger.info(" A : "+A+" B : "+B+" flag_compare : "+flag_compare);
			return flag_compare;
		}
		
		
		//comparison with number value
		public String compareTwoNumber(long A, long B)
		{
			String flag_compare = "";
			if (A < B){
				flag_compare = "DESC";
			} else if (A > B) {
				flag_compare = "ASC";
			} else {
				flag_compare = "EQUAL";
			}
			//myLogger.info(" A : "+A+" B : "+B+" flag_compare : "+flag_compare);
			return flag_compare;
		}
		
		
		public String RTCrudSkrinAuto( 
				String command, // related command, get dlu..
				String skrinName,
				String seqName,
				String queryList, 
				List listColumnForSenarai, 
				String queryMap,
				//String namaList, // nama List
				//String namaMap, // nama List
				String div,
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				int maxRowNum,
				String tableName,
				String filterDB,
				String pkField,
				String divViewId,
				String param,
				Db db, Connection conn, String NO_FAIL, String ID_FAIL,
				String cacheID)throws Exception {
			
			
			
			String html = "";
			if(command.equals("openCrudSkrin") || command.equals("deleteCrud") || command.equals("refreshListCrud") || command.equals("insertCrud"))
			{
				html += "<input type='button' id='cmdAdd"+skrinName+"' name='cmdAdd"+skrinName+"' value='Tambah' onClick=\"setingTrDivTR('divId"+skrinName+"','divView"+skrinName+"');doDivAjaxCall"+getFormName()+"('divView"+skrinName+"','addCrud','&div=divView"+skrinName+"&skrinName="+skrinName+"&"+pkField+"=&pkField="+pkField+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());\" > ";
				html += "<input type='button' id='cmdRefreshList"+skrinName+"' name='cmdRefreshList"+skrinName+"' value='Refresh List' onClick=\"doDivAjaxCall"+getFormName()+"('"+div+"','refreshListCrud','&div="+div+"&skrinName="+skrinName+"&"+pkField+"={"+pkField+"}&pkField="+pkField+"&filterDB="+filterDB+param+"&tableName="+tableName+"&scrolPosition='+getPageLocation());\" >";
				
				String lastInsert = "";
				if(command.equals("insertCrud"))
				{
					conn.setAutoCommit(false);
					lastInsert = saveCrudAuto(tableName,seqName, skrinName, pkField, command, cacheID, db,conn);
					html += "<div class=\"blue\" ><br><b>"+lastInsert+"</b><br></div>";
					logActivityRT(null,session,"INS","NO. FAIL : "+NO_FAIL+", TABLE : "+tableName,"",conn,db);
					conn.commit();
				}
				
				html += RThtmlListRekod(command,skrinName, // skrinName
						queryList, //method query
						listColumnForSenarai, //column to display
						cacheID,
						div,"","","","", 1000,divViewId,param,conn,db);
						
				
			}
			else if(command.equals("viewCrud") || command.equals("editCrud") || command.equals("addCrud") || command.equals("saveCrud"))
			{	
				String mode = "view";
				if(command.equals("editCrud") || command.equals("addCrud"))
				{
					mode = "edit";
				}
				
				else if(command.equals("saveCrud"))
				{
					conn.setAutoCommit(false);
					Statement stmt = db.getStatement();
					String backupScriptUpdate = backupScriptUpdate(tableName, pkField, getParam(pkField), skrinName, stmt, db);
					myLogger.info(" >>>>>>>>>>>>>>>>>>>> backupScriptUpdate : "+backupScriptUpdate);
					saveCrudAuto(tableName,seqName, skrinName, pkField, command, cacheID, db,conn);
					logActivityRT(null,session,"UPD","NO. FAIL : "+NO_FAIL+", TABLE : "+tableName+", FILTER : "+pkField+" : "+getParam(pkField),backupScriptUpdate,conn,db);
					conn.commit();
				}
				
				html += RThtmlViewMapAuto( 
						command,
						skrinName,
						queryMap, //sql
						//namaList, //nama Map
						//namaMap,
						divViewId,
						mode,
						true, //non editable, view only
						tableName,
						filterDB,
						pkField,
						param,
						cacheID,
						db);	
						
			}
			
			
			
			return html;
		}
		
		
		
		
	
		//standard basic method to display list
		//with paging, cache
		public String RThtmlListRekod( 
				String command, // related command, get dlu..
				String skrinName,
				String query, List listColumnForSenarai, 
				//boolean flagCache, // list yg ada cache
				//boolean resetCache, // keperluan mereset cache
				String cacheID, // unik cache ID
				//String namaList, // nama List
				//String namaMap, // nama List
				//boolean showPaging,
				String div,
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				String dateFormat, // kalo nak sort by date, perlu ada date format
				int maxRowNum,
				String divViewId,
				String param,
				Db db)throws Exception {
			
			return RThtmlListRekod(
					command,
					skrinName,
					query, listColumnForSenarai, 
					true, // list yg ada cache
					false, // keperluan mereset cache
					cacheID, // unik cache ID // auto create nnti
					true,//show paging
					div,//div list
					columNameSort, // senarai nama column untuk  kita sort kan
					columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
					sortType, // jenis sorting mengikut column
					dateFormat,
					maxRowNum,
					divViewId,
					param,
					null,
					db);
			
		}
		
		
		public String RThtmlListRekod( 
				String command, // related command, get dlu..
				String skrinName,
				String query, List listColumnForSenarai, 
				String cacheID, // unik cache ID
				//boolean showPaging,
				String div,
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				String dateFormat, // kalo nak sort by date, perlu ada date format
				int maxRowNum,
				String divViewId,
				String param,
				Connection conn,
				Db db)throws Exception {
			
			return RThtmlListRekod(
					command,
					skrinName,
					query, listColumnForSenarai, 
					true, // list yg ada cache
					false, // keperluan mereset cache
					cacheID, // unik cache ID // auto create nnti
					true,//show paging
					div,//div list
					columNameSort, // senarai nama column untuk  kita sort kan
					columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
					sortType, // jenis sorting mengikut column
					dateFormat,
					maxRowNum,
					divViewId,
					param,
					conn,
					db);
			
		}
	
		
		
		//standard basic method to display list
				//with paging, cache
				public String RThtmlListRekodforEditor( 
						String command, // related command, get dlu..
						String skrinName,
						String query, 						
						String div,
						int maxRowNum,
						String cacheID,
						Connection conn,
						Db db)throws Exception {
					
					return RThtmlListRekodEditor(
							command,
							skrinName,
							query, //							
							div,//div list
							maxRowNum,
							cacheID,
							conn,
							db);
					
				}
		
		
				//full method to return list object with caching trick
				@SuppressWarnings("unchecked")
				public String RThtmlListRekodEditor( 
						String command,
						String skrinName,
						String query, 
						String div,
						int maxRowNum,
						String cacheID,
						Connection conn,
						Db db)throws Exception {
					//String html = "";
					
					List listRekod = RTListRekod(
							query,
							true, // list yg ada cache
							false, // keperluan mereset cache
							cacheID, // unik cache ID
							skrinName, // nama list
							div,true,
							"", // senarai nama column untuk  kita sort kan
							"", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
							"", // jenis sorting mengikut column
							"",
							maxRowNum,
							conn,
							db);
					//** SINI MASIH PROBLEM
					List listRekodAsal = listRekod;
					String param = "";
					String html = "";
					int totalRekodCount = listRekod.size(); // jumlah rekod sebelum di paging kan
				
					String classTable = "";
					if(!("searchColumn").equals(getParam("actionajax")))
					{
						classTable = "classFade";
					}
					
					String actionajax = getParam("actionajax");
					myLogger.info("RThtmlListRekodEditor actionajax : "+actionajax);
					
					boolean resetCache = false;
					if(actionajax.equals(""))
					{
						resetCache = true;
					}
					
					List listColumnForSenarai = returnColumnNameFromQuery(query,skrinName+"map",true,resetCache,false,cacheID,db);
					//myLogger.info(" listColumnForSenarai :::: "+listColumnForSenarai);
					String htmlPaging = "";
					Map RTsetupPageList = RTsetupPageListEditor(listRekod,listColumnForSenarai,maxRowNum,param,classTable,skrinName);
					if(RTsetupPageList != null)
					{
						listRekod = (List) RTsetupPageList.get("listRekod");
						htmlPaging += (String) RTsetupPageList.get("htmlPaging");
					}
					context.put("htmlPaging",htmlPaging);
					
					int totalColumn = 0;
					//get html column tr header
					
					
					
					html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" class=' adjust "+classTable+"' > ";
					
					if (listRekodAsal.size() > 1) {
						html += "<tr class=\"table_header\" > ";
						html += "<td   align=\"center\" valign=\"top\" ></td> ";
						for (int i = 0; i < listColumnForSenarai.size(); i++) {
							Map map_column_name = (Map) listColumnForSenarai.get(i);
														
							String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");
							int COLUMN_SIZE = (Integer) map_column_name.get("COLUMN_SIZE");
							//String COLUMN_NOTNULL = (String) map_column_name.get("COLUMN_NOTNULL");
						    //myLogger.info("cs"+COLUMN_NAME+skrinName+" ::::::: "+getParam("cs"+COLUMN_NAME+skrinName));
							
							if(!COLUMN_NAME.equals(""))
							{
								html += "<td valign=\"top\"  >";							
								String getColumnSearch = "";
								if(!getParam("actionajax").equals(""))
								{
									getColumnSearch = getParam("cs"+COLUMN_NAME+skrinName);
								}
								html += "<input type='text' " +
										" onkeyup=\"checkCursorLocation(this,'"+skrinName+"','"+command+"');searchColumn("+totalRekodCount+",'"+command+"','"+COLUMN_NAME+"','"+COLUMN_TYPE+"','"+div+"'," +
												"'"+skrinName+"','"+param+"');\" name='cs"+COLUMN_NAME+skrinName+"' " +
												"" +
												"id='cs"+COLUMN_NAME+skrinName+"' class='fullwidth_input' value = '"+getColumnSearch+"'  />";
								
								if(getParam("actionajax").equals("searchColumn") && getParam("sortColumn"+skrinName+command).equals(COLUMN_NAME))
								{
									html += "<script>document.getElementById(\"cs"+COLUMN_NAME+skrinName+"\").focus();document.getElementById(\"cs"+COLUMN_NAME+skrinName+"\").setSelectionRange("+getParam("sortColumnPosition"+skrinName+command)+", "+getParam("sortColumnPosition"+skrinName+command)+"); </script>";
								}
								
								html += "</td> ";
							}
							else
							{
								html += "<td valign=\"top\" ></td>";		
							}
						}				
						html += "</tr>";
					}
					if (listColumnForSenarai.size() != 0) {				
						html += "<tr class=\"table_header\" > ";
						html += "<td   align=\"center\" valign=\"top\" >Bil.</td> ";
						for (int i = 0; i < listColumnForSenarai.size(); i++) {
							Map map_column_name = (Map) listColumnForSenarai.get(i);
							String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");
							String DATE_FORMAT = (String) map_column_name.get("DATE_FORMAT");
							int COLUMN_SIZE = (Integer) map_column_name.get("COLUMN_SIZE");
							String COLUMN_NOTNULL = (String) map_column_name.get("COLUMN_NOTNULL");
							
							totalColumn++;
							//selain dari hidden, kita show semua
							html += "<td  valign=\"top\"   ";
							if(!COLUMN_NAME.equals(""))
							{
								html +=	" class=\"columnSort\" onclick=\"sortOnClick("+totalRekodCount+",'"+command+"','"+COLUMN_NAME+"','"+COLUMN_TYPE+"','"+div+"'," +
										"'"+skrinName+"','"+param+"','"+DATE_FORMAT+"');\" ";
							}
							html +=	"  ><table cellpadding=\"2\" cellspacing=\"0\" ><tr class=\"table_header\"><td valign=\"top\"> ";
							html += COLUMN_NAME;
							html += "</td>";
							if(totalRekodCount > 1)
						    {
						    	//jika rekod lebih dari 1 baru appear icon soarting
								if(!COLUMN_NAME.equals(""))
								{
						    		html += "<td valign=\"top\"><span class=\"arrow down\" ></span></td>";
								}
						    }						
							html += "</tr></table></td> ";
						}
						html += "</tr>";
					}
				
					
					//List columnNameFromQuery = returnColumnNameFromQuery(query, skrinName+"list", true, false, db);
				
					if (listRekod.size() != 0) {
						for (int i = 0; i < listRekod.size(); i++) {
							Map map_rekod = (Map) listRekod.get(i);
							html += "<tr > ";
							html += "<td class=\"adjust "+map_rekod.get("ROWCSS")+" \"  align=\"center\" valign=\"top\" >"+map_rekod.get("BILSORT")+"</td> ";
							
								
								for (int cn = 0; cn < listColumnForSenarai.size(); cn++) {
									String displayHtml = "";
									Map mapCNquery = (Map) listColumnForSenarai.get(cn);
									String COLUMN_NAME_QUERY = (String) mapCNquery.get("COLUMN_NAME");
									String COLUMN_TYPE = (String) mapCNquery.get("COLUMN_TYPE");									
									String valueCNquery = (String) map_rekod.get(COLUMN_NAME_QUERY);
									displayHtml = valueCNquery;
									
									if(COLUMN_TYPE.equals("DATE") && !valueCNquery.equals("") && valueCNquery!= null)
									{
										displayHtml = convertFormatDate(valueCNquery);
									}
									
									html += "<td id=\""+skrinName+map_rekod.get("BILSORT")+"\" class=\" adjust "+map_rekod.get("ROWCSS")+"\"   valign=\"top\" >";
									html += displayHtml;		
									html += "</td> ";
																			
								}	
														
								
							html += "</tr>";
							
							
						}
					}
					else
					{
						html += "<tr><td align=\"left\" valign=\"top\" colspan=\"15\" >TIADA REKOD</td></tr> ";
					}
					html += "</table>";
					return html;
				}		
				
		
	   //full method to return list object with caching trick
		@SuppressWarnings("unchecked")
		public String RThtmlListRekod( 
				String command,
				String skrinName,
				String query, List listColumnForSenarai, 
				boolean flagCache, // list yg ada cache
				boolean resetCache, // keperluan mereset cache
				String cacheID, // unik cache ID
				//String namaList, // nama List
				//String namaMap,
				boolean showPaging,
				String div,
				String columNameSort, // senarai nama column untuk  kita sort kan
				String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				String sortType, // jenis sorting mengikut column
				String dateFormat,
				int maxRowNum,
				String divViewId,
				String param,
				Connection conn,
				Db db)throws Exception {
			//String html = "";
			
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			List listRekod = RTListRekod(
					query,
					flagCache, // list yg ada cache
					resetCache, // keperluan mereset cache
					cacheID, // unik cache ID
					skrinName, // nama list
					div,showPaging,
					columNameSort, // senarai nama column untuk  kita sort kan
					columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
					sortType, // jenis sorting mengikut column
					dateFormat,
					maxRowNum,
					conn,
					db);
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			myLogger.info("**LOADING listRekod, TIME TAKEN : "+finish+" SECS");
			
			
			List listRekodAsal = listRekod;
			
			String html = "";
			int totalRekodCount = listRekod.size(); // jumlah rekod sebelum di paging kan
		
			String classTable = "";
			if(!("searchColumn").equals(getParam("actionajax")))
			{
				classTable = "classFade";
			}
			
			int itemsPerPage;
			
			int showStartPageNumber = 0;
			int showItemsPerPage = 0;
			if(showPaging==true)
			{
				Map RTsetupPageList = RTsetupPageList(listRekod,listColumnForSenarai,
						//namaList,namaMap,
						maxRowNum,param,classTable,skrinName);
				
				if(RTsetupPageList != null)
				{
					listRekod = (List) RTsetupPageList.get("listRekod");
					showStartPageNumber = (Integer) RTsetupPageList.get("startNumber");			
					showItemsPerPage = (Integer) RTsetupPageList.get("itemsPerPage");			
					html += (String) RTsetupPageList.get("htmlPaging");
				}
			}
			
			myLogger.info("showStartPageNumber :::::::::: "+showStartPageNumber+" showItemsPerPage :::::::::::::: "+showItemsPerPage);
			
			
			int totalColumn = 0;
			//get html column tr header
			
			
			
			html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" width=\"100%\" class='"+classTable+"' > ";
			if (listRekodAsal.size() > 1) {
				html += "<tr class=\"table_header\" > ";
				html += "<td   align=\"center\" valign=\"top\" ></td> ";
				for (int i = 0; i < listColumnForSenarai.size(); i++) {
					Map map_column_name = (Map) listColumnForSenarai.get(i);
					String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
					String LABEL = (String) map_column_name.get("LABEL");
					String LIST_NAME = (String) map_column_name.get("LIST_NAME");
					String ALIGN = (String) map_column_name.get("ALIGN");
					String DATATYPE = (String) map_column_name.get("DATATYPE");
					String ONCLICK = (String) map_column_name.get("ONCLICK");
					String CUSTOM = (String) map_column_name.get("CUSTOM");
					if(!ALIGN.equals("hidden"))
					{
						//myLogger.info("cs"+COLUMN_NAME+skrinName+" ::::::: "+getParam("cs"+COLUMN_NAME+skrinName));
						if(!COLUMN_NAME.equals(""))
						{
							html += "<td valign=\"top\" >";							
							String getColumnSearch = "";
							if(!getParam("actionajax").equals(""))
							{
								getColumnSearch = getParam("cs"+COLUMN_NAME+skrinName);
							}
							html += "<input type='text' " +
									" onkeyup=\"checkCursorLocation(this,'"+skrinName+"','"+command+"');searchColumn("+totalRekodCount+",'"+command+"','"+COLUMN_NAME+"','"+DATATYPE+"','"+div+"'," +
											//"'"+namaList+"'," +
													"'"+skrinName+"','"+param+"');\" name='cs"+COLUMN_NAME+skrinName+"' " +
											"" +
											"id='cs"+COLUMN_NAME+skrinName+"' class='fullwidth_input' value = '"+getColumnSearch+"'  />";
							
							if(getParam("actionajax").equals("searchColumn") && getParam("sortColumn"+skrinName+command).equals(COLUMN_NAME))
							{
								html += "<script>document.getElementById(\"cs"+COLUMN_NAME+skrinName+"\").focus();document.getElementById(\"cs"+COLUMN_NAME+skrinName+"\").setSelectionRange("+getParam("sortColumnPosition"+skrinName+command)+", "+getParam("sortColumnPosition"+skrinName+command)+"); </script>";
							}
							
							html += "</td> ";
						}
						else
						{
							html += "<td valign=\"top\" ></td>";		
						}
					}
				}				
				html += "</tr>";
			}
			if (listColumnForSenarai.size() != 0) {				
				html += "<tr class=\"table_header\" > ";
				html += "<td   align=\"center\" valign=\"top\" width=\"5%\">Bil.</td> ";
				for (int i = 0; i < listColumnForSenarai.size(); i++) {
					Map map_column_name = (Map) listColumnForSenarai.get(i);
					String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
					String DATE_FORMAT = (String) map_column_name.get("DATE_FORMAT");
					String LABEL = (String) map_column_name.get("LABEL");
					String LIST_NAME = (String) map_column_name.get("LIST_NAME");
					String ALIGN = (String) map_column_name.get("ALIGN");
					String DATATYPE = (String) map_column_name.get("DATATYPE");
					String ONCLICK = (String) map_column_name.get("ONCLICK");
					String CUSTOM = (String) map_column_name.get("CUSTOM");
					int width = (Integer) map_column_name.get("WIDTH");
					String widthStr = "";
					if(width > 0)
					{
						widthStr = " width=\""+width+"%\" ";
					}
					//ni untuk setting header & column list
					if(!ALIGN.equals("hidden"))
					{
						totalColumn++;
						//selain dari hidden, kita show semua
						html += "<td align=\""+ALIGN+"\" valign=\"top\" "+widthStr+" ";
						if(!COLUMN_NAME.equals(""))
						{
							html +=	" class=\"columnSort\" onclick=\"sortOnClick("+totalRekodCount+",'"+command+"','"+COLUMN_NAME+"','"+DATATYPE+"','"+div+"'," +
									//"'"+namaList+"'," +
											"'"+skrinName+"','"+param+"','"+DATE_FORMAT+"');\" ";
						}
						html +=	"> ";
						
						
						html += " <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" ><tr class=\"table_header\"><td valign=\"top\" align=\""+ALIGN+"\" > ";
						html += LABEL;
						html += "</td>";
						if(totalRekodCount > 1)
					    {
					    	//jika rekod lebih dari 1 baru appear icon soarting
							if(!COLUMN_NAME.equals(""))
							{
					    		html += "<td valign=\"top\"><span class=\"arrow down\" ></span></td>";
							}
					    }						
						html += "</tr></table>";
						
						/*
						html += LABEL;
						if(totalRekodCount > 1)
					    {
					    	//jika rekod lebih dari 1 baru appear icon soarting
							if(!COLUMN_NAME.equals(""))
							{
					    		html += "<span class=\"arrow down\" ></span>";
							}
					    }						
						html += "</td> ";
						*/
						
					}
				}
				html += "</tr>";
			}
			
			
			List columnNameFromQuery = returnColumnNameFromQuery(query, skrinName+"list", true, false, true, cacheID, db);
			
			//myLogger.info(" >>>>>>>>>>>> listColumnForSenarai : "+listColumnForSenarai);
			//myLogger.info(" >>>>>>>>>>>> columnNameFromQuery : "+columnNameFromQuery);
		
			//myLogger.info("showStartPageNumber :::::::::: "+showStartPageNumber+" showItemsPerPage :::::::::::::: "+showItemsPerPage);
			
			if (listRekod.size() != 0) {
				//for (int i = showStartPageNumber; i < (showStartPageNumber+showItemsPerPage); i++) {
				//myLogger.info("i :::::::: "+i+"; showStartPageNumber :::::::::: "+showStartPageNumber+"; (showStartPageNumber+showItemsPerPage) :::::::::::::: "+(showStartPageNumber+showItemsPerPage));
				for (int i = 0; i < listRekod.size(); i++) {
					Map map_rekod = (Map) listRekod.get(i);
					html += "<tr > ";
					html += "<td class=\""+map_rekod.get("ROWCSS")+"\"  align=\"center\" valign=\"top\" >"+map_rekod.get("BILSORT")+"</td> ";
					String resetDivViewId = "";
					
					
					for (int x = 0; x < listColumnForSenarai.size(); x++) {
						Map map_column_name = (Map) listColumnForSenarai.get(x);
						String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
						String LABEL = (String) map_column_name.get("LABEL");
						String LIST_NAME = (String) map_column_name.get("LIST_NAME");
						String ALIGN = (String) map_column_name.get("ALIGN");
						String DATATYPE = (String) map_column_name.get("DATATYPE");
						String ONCLICK = (String) map_column_name.get("ONCLICK");
						String DISPLAY = (String) map_column_name.get("DISPLAY");
						String CUSTOM = (String) map_column_name.get("CUSTOM");
						int width = (Integer) map_column_name.get("WIDTH");
						String widthStr = "";
						if(width > 0)
						{
							widthStr = " width=\""+width+"%\" ";
						}
						
						String displayDalamTd = "";
						String scriptCustom =  CUSTOM;
						String scriptOnClick =  ONCLICK;
						String displayHtml = DISPLAY;
						
						
						
						for (int cn = 0; cn < columnNameFromQuery.size(); cn++) {
							Map mapCNquery = (Map) columnNameFromQuery.get(cn);
							String COLUMN_NAME_QUERY = (String) mapCNquery.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) mapCNquery.get("COLUMN_TYPE");
							
							if(scriptCustom.contains("{"+COLUMN_NAME_QUERY+"}") 
									|| scriptOnClick.contains("{"+COLUMN_NAME_QUERY+"}") 
									|| divViewId.contains("{"+COLUMN_NAME_QUERY+"}") 
									|| displayHtml.contains("{"+COLUMN_NAME_QUERY+"}"))
							{
								String valueCNquery = (String) map_rekod.get(COLUMN_NAME_QUERY);
								scriptOnClick = scriptOnClick.replace("{"+COLUMN_NAME_QUERY+"}", valueCNquery);
								scriptCustom = scriptCustom.replace("{"+COLUMN_NAME_QUERY+"}", valueCNquery);
								displayHtml = displayHtml.replace("{"+COLUMN_NAME_QUERY+"}", valueCNquery);
								if(COLUMN_TYPE.equals("DATE") && !valueCNquery.equals("") && valueCNquery!= null)
								{
									displayHtml = convertFormatDate(valueCNquery);
								}								
								resetDivViewId = divViewId.replace("{"+COLUMN_NAME_QUERY+"}", valueCNquery);
							}										
						}
						
							
						
						if(!ONCLICK.equals(""))
						{																	
							displayDalamTd += "<a href=\"javascript:"+scriptOnClick+"\"  class=\"link\" >";
							displayDalamTd += displayHtml;
							displayDalamTd += "</a>";
						}
						else if(!CUSTOM.equals(""))
						{																	
							displayDalamTd += scriptCustom;
						}
						else
						{							
							displayDalamTd += displayHtml;
						}							
						
						//ni untuk setting header & column list
						if(!ALIGN.equals("hidden"))
						{							
							html += "<td id=\""+skrinName+map_rekod.get("BILSORT")+"\" class=\""+map_rekod.get("ROWCSS")+"\"  align=\""+ALIGN+"\" "+widthStr+" valign=\"top\" >";
							html += displayDalamTd;		
							html += "</td> ";
						}
					}
					html += "</tr>";
					
					if(!resetDivViewId.equals(""))
					{
						html += "<tr> ";
						html += "<td colspan = \""+totalColumn+2+"\" align=\"left\" valign=\"top\"  >";
						html += "<div id=\""+resetDivViewId+"\" ></div>";
						html += "</td>";
						html += "</tr> ";
					}
					
				}
			}
			else
			{
				html += "<tr><td align=\"left\" valign=\"top\" colspan=\"15\" >TIADA REKOD</td></tr> ";
			}
			html += "</table>";
			
			
			return html;
		}
		
		private static SimpleDateFormat inSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		private static SimpleDateFormat outSDF = new SimpleDateFormat("dd/MM/yyyy");
		public String convertFormatDate(String inDate) {
			//myLogger.info("inDate : "+inDate);
		    String outDate = "";
		    if (inDate != null && !inDate.equals("")) {
		        try {
		            Date date = inSDF.parse(inDate);
		            outDate = outSDF.format(date);
		        } 
		        catch (ParseException ex){ 
		        	outDate = inDate;
		        	//myLogger.info(ex);
		        }
		    }
		    return outDate;
		}
		
		
		public String removeHtmlTags(String html) {
		    StringBuilder sb = new StringBuilder(html);
		    int open;
		    while ((open = sb.indexOf("<")) != -1) {
		        int close = sb.indexOf(">", open + 1);
		        sb.delete(open, close + 1);
		    }
		    return sb.toString();
		}
		
		
		public Map getColumnForSenarai(String skrinName, 
				String columnName, 
				//String display,	
				int width,
				String label, String align, 
				//String listName, 
				String dataType,String SCRIPT,String CUSTOM) 
				throws Exception
		{
			return getColumnForSenarai(skrinName, 
					columnName, 
					//String display,	
					width,
					label,align, 
					//String listName, 
					dataType,SCRIPT,CUSTOM, ""); 
		}
		
		
		//method setup column
		public Map getColumnForSenarai(String skrinName, 
				String columnName, 
				//String display,	
				int width,
				String label, String align, 
				//String listName, 
				String dataType,String SCRIPT,String CUSTOM, String DATE_FORMAT) 
				throws Exception
		{
			Map h = Collections.synchronizedMap(new HashMap());
			h.put("COLUMN_NAME",columnName);
			String display = "";
			if(!columnName.equals(""))
			{
				display = "{"+columnName+"}";
			}
			h.put("DISPLAY",display);
			h.put("LABEL",label);
			h.put("ALIGN",align);	
			h.put("SKRIN_NAME",skrinName);
			h.put("LIST_NAME",skrinName+"list");					
			h.put("WIDTH",width);
			h.put("DATATYPE",dataType);
			h.put("COLUMN_TYPE",dataType);
			h.put("ONCLICK",SCRIPT);
			h.put("CUSTOM",CUSTOM);
			h.put("DATE_FORMAT",DATE_FORMAT);
			//myLogger.info(" getColumnForSenarai : "+h);
			return h;	
		}
		
		//[STANDARD]
		public void defaultContextPutRT()
		{
			//myLogger.info("defaultContextPutRT context : "+context);
			//segala context.put yang digunapakai dalam class ni mesti di initiate
			context.put("successInfo","");
			context.put("showPaging","");
			context.put("errorMesej","");
			context.put("div", "");
			context.put("namaList", "");
			context.put("namaMap", "");
			context.put("loadingMsg", "");
			context.put("scrolPosition", "");
			context.put("totalRecords", "");	
			context.put("mode", "");
			context.put("html", "");
			context.put("htmlPaging", "");			
		}
		
		/*
		public String onReadyAjaxCall(String command,String div, String namaList, String skrinName, boolean insideDiv, String otherParams)
		{
			String html = "";
			if(insideDiv == true)
			{
				html += "<div id=\""+div+"\" >";
			}
			html += " <script> ";
			html += " $jquery(document).ready(function (){ ";
			html += " doDivAjaxCall"+getFormName()+"('"+div+"','"+command+"','&div="+div+"&namaList="+namaList+"&skrinName="+skrinName+otherParams+"'); ";
			html += " });";
			html +=	" </script>";		
			if(insideDiv == true)
			{
				html += "</div >";
			}	
			myLogger.info("onReadyAjaxCall html : "+html);
			return html;		
		}
		*/
		
		public String ajaxCallFunction(String command,String div, String skrinName, String otherParams)
		{
			String html = "";			
			html += " doDivAjaxCall"+getFormName()+"('"+div+"','"+command+"','&div="+div+"&skrinName="+skrinName+otherParams+"'); ";			
			return html;		
		}
		
		public String customTdSort(String skrinName,String columnName, String label, String dataType, String align, double width, String param,int totalRekodCount,String command,String div)
		{
			return customTdSort(skrinName,columnName, label, dataType, align, width, param, totalRekodCount,command, div, "");
					
		}
		
		public String customTdSort(String skrinName,String columnName, String label, String dataType, String align, double width, String param,int totalRekodCount,String command,String div,String dateFormat)
		{
			String html = "<td align=\""+align+"\" valign=\"top\" ";
			if(width>0)
			{
				html += " width=\""+width+"%\" ";
			}
			html +=	" class=\"columnSort\" onclick=\"sortOnClick("+totalRekodCount+",'"+command+"','"+columnName+"','"+dataType+"','"+div+"'," +
					"'"+skrinName+"','"+param+"','"+dateFormat+"');\" ";		
			html +=	"> ";
			
			html += " <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\" ><tr class=\"table_header\"><td valign=\"top\" align=\""+align+"\"> ";
			html += label;
			html += "</td>";
			html += "<td valign=\"top\"><span class=\"arrow down\" ></span></td>";				
		    						
			html += "</tr></table>";
			
			//html += label;
			//html += "<span class=\"arrow down\" ></span>";								
			html += "</td>";
			return html;	
		}	
		
		public String customTdSearch(String skrinName, String div,  String namaColumn, String dataType, String command, int totalRekodCount, String param)
		{
			
			String html = "<td valign=\"top\" >";	
			String getColumnSearch= "";
			if(!getParam("actionajax").equals(""))
			{
				getColumnSearch = getParam("cs"+namaColumn+skrinName);
			}
			html += "<input type='text' onkeyup=\"checkCursorLocation(this,'"+skrinName+"','"+command+"');searchColumn("+totalRekodCount+",'"+command+"','"+namaColumn+"','"+dataType+"','"+div+"'," +
							"'"+skrinName+"','"+param+"');\" name='cs"+namaColumn+skrinName+"' id='cs"+namaColumn+skrinName+"' class='fullwidth_input' value = '"+getColumnSearch+"'  />";
			
			if(getParam("actionajax").equals("searchColumn") && getParam("sortColumn"+skrinName+command).equals(namaColumn))
			{
				html += "<script>document.getElementById(\"cs"+namaColumn+skrinName+"\").focus();document.getElementById(\"cs"+namaColumn+skrinName+"\").setSelectionRange("+getParam("sortColumnPosition"+skrinName+command)+", "+getParam("sortColumnPosition"+skrinName+command)+"); </script>";
			}			
			html += "</td> ";
			
			return html;
		}
		
		
		public String getFormName() 
		{
			//RT.getFormName((String)session.getAttribute("_portal_module")) ;
			//myLogger.info("portalID : "+(String)session.getAttribute("_portal_module"));
			String portalID = (String)session.getAttribute("_portal_module");			
			String formName = "";
			
			if(!portalID.equals(""))
			{
				formName = (new StringBuilder("F")).append(portalID.replace('.', '_')).toString();
			}
			
			return formName;
		}
		
		//open check loding time
		public long startLodingTime = 0;
		public String startTime = "";
		
		public void startTime()
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			startTime = dateFormat.format(cal.getTime());
			startLodingTime = System.currentTimeMillis();//mengukur masa loading time 			
		}	
		
		public double finishTime()
		{
			double finishLodingTime = (double) (System.currentTimeMillis() - startLodingTime) / 1000.0;
			return finishLodingTime;
		}
		//close check loading time
		
		
		public ResultSet executeQueryRTNew_X(Statement stmt, String sql, String methodName, Connection conn, Db db) throws SQLException
		{ 
			String accessBy = rbRaz.getString("accessBy");
			long start = System.currentTimeMillis();//mengukur masa loading time 
			ResultSet rs = null;
			myLogger.info("executeQueryRTNew Connection : "+conn);
			if(conn == null)
			{
				rs = stmt.executeQuery(sql);
			}
			else
			{
			
				conn = db.getConnection();
				stmt = conn.createStatement();
				stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
		                   ResultSet.CONCUR_READ_ONLY);
				//stmt.setFetchSize(1000000);
				rs = stmt.executeQuery(sql);			
			}
			
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			String _portal_module = (String) session.getAttribute("_portal_module");
			String msg = "**NEW LOADING QUERY EXECUTION AT METHOD : '"+methodName+"' IN CONTROLLER '"+_portal_module+"', TIME TAKEN : "+finish+" SECS";
			myLogger.info(msg);		
			loadingMsg += msg+"<br>";
			if(accessBy.equals("programmer"))
			{
				context.put("loadingMsg", loadingMsg);	
			}
			
			return rs;			
		}
		
		//centeralize execution time method
		public ResultSet executeQueryRT(Statement stmt, String sql, String methodName) throws SQLException
		{ 
			myLogger.info("executeQueryRT ");
			String accessBy = rbRaz.getString("accessBy");
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			ResultSet rs = stmt.executeQuery(sql);
			
			
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			String _portal_module = (String) session.getAttribute("_portal_module");
			String msg = "**LOADING QUERY EXECUTION AT METHOD : '"+methodName+"' IN CONTROLLER '"+_portal_module+"', TIME TAKEN : "+finish+" SECS";
			myLogger.info(msg);		
			loadingMsg += msg+"<br>";
			if(accessBy.equals("programmer"))
			{
				context.put("loadingMsg", loadingMsg);	
			}
			
			return rs;			
		}
		
		//centeralize execution time method
		public void executeUpdateRT(SQLRenderer r,String tableName,Statement stmt, String methodName, Db db) throws SQLException
		{ 
			
			String sql = r.getSQLUpdate(tableName,db);
			currentSQL = sql;
			myLogger.info("executeUpdateRT : "+sql);					
			stmt.executeUpdate(sql);
			
			String accessBy = rbRaz.getString("accessBy");
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			String _portal_module = (String) session.getAttribute("_portal_module");
			String msg = "**LOADING QUERY EXECUTION AT METHOD : '"+methodName+"' IN CONTROLLER '"+_portal_module+"', TIME TAKEN : "+finish+" SECS";
			myLogger.info(msg);		
			loadingMsg += msg+"<br>";
			if(accessBy.equals("programmer"))
			{
				context.put("loadingMsg", loadingMsg);	
			}		
		}
		
		
		//centeralize execution time method
		public void executeInsertRT(SQLRenderer r,String tableName,Statement stmt, String methodName, Db db) throws SQLException
		{ 
			
			String sql = r.getSQLInsert(tableName,db);
			currentSQL = sql;
			myLogger.info("executeInsertRT : "+sql);					
			stmt.executeUpdate(sql);
			
			String accessBy = rbRaz.getString("accessBy");
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			String _portal_module = (String) session.getAttribute("_portal_module");
			String msg = "**LOADING QUERY EXECUTION AT METHOD : '"+methodName+"' IN CONTROLLER '"+_portal_module+"', TIME TAKEN : "+finish+" SECS";
			myLogger.info(msg);		
			loadingMsg += msg+"<br>";
			if(accessBy.equals("programmer"))
			{
				context.put("loadingMsg", loadingMsg);	
			}		
		}
		
		
		
		//centeralize execution time method
		public void executeDeleteRT(String sql,Statement stmt, String methodName, Db db) throws SQLException
		{ 
			currentSQL = sql;
			//myLogger.info("executeDeleteRT : "+sql);					
			stmt.execute(sql);
			
			String accessBy = rbRaz.getString("accessBy");
			long start = System.currentTimeMillis();//mengukur masa loading time 	
			double finish = (double) (System.currentTimeMillis() - start) / 1000.0;
			String _portal_module = (String) session.getAttribute("_portal_module");
			String msg = "**LOADING QUERY EXECUTION AT METHOD : '"+methodName+"' IN CONTROLLER '"+_portal_module+"', TIME TAKEN : "+finish+" SECS";
			//myLogger.info(msg);		
			loadingMsg += msg+"<br>";
			if(accessBy.equals("programmer"))
			{
				context.put("loadingMsg", loadingMsg);	
			}		
		}
		
		
		//error DB mesej control
		//return skrin name
		public void RTloadingTimeControl(String skrinName) throws Exception
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String endTime = dateFormat.format(cal.getTime());	
			String command = getParam("command");
			String actionajax = getParam("actionajax");
			String _portal_module = (String) session.getAttribute("_portal_module");
			//access by ada 2 jenis : programmer or user
			//if programmer, system akan paparkan detail error atau masalah pada skrin
			//if user, hanya paparkan ganeral error mesej
			String accessBy = rbRaz.getString("accessBy");	
			String msg = "**LOADING TIME BY CONTROLLER : '"+_portal_module+"', skrin : "+skrinName+", command : '"+command+"', actionajax : '"+actionajax+"', START : '"+startTime+"', END : '"+endTime+"' TIME TAKEN : "+finishTime()+" SEC";
			loadingMsg += msg+"<br>";
			myLogger.info(msg);
			if(accessBy.equals("programmer"))
			{
				context.put("loadingMsg", loadingMsg);	
			}
		}
		
		@SuppressWarnings("unchecked")
		public String getValueFormMapData(Map map_data, String column_name, Db db)throws Exception {
			String value = map_data == null ? "" :(String) map_data.get(column_name) == null ? "" : (String) map_data.get(column_name);			
			return value;
		}
		
		
		public String setRowTarikhCarian(String skrinName,//skrinName
				//boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				//String mode,//mode : edit/view
				//Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				//boolean mandatory,// adakah field ini mandatory true=ya
				//String jenis_field,// hidden/text/currencyOnly/areaOnly 
				//boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				//boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				//boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onBlurFunction,//apa js yang dipanggil semasa onblur
				String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			
			return setRowTarikh(skrinName,//skrinName
					true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					"edit",//mode : edit/view
					null,//set data
					label,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					false,// adakah field ini mandatory true=ya
					"text",// hidden/text/currencyOnly/areaOnly 
					true,// display : diantara label & field
					maxLength,//setting maxlength
					true,//defaultUppercase pada value input
					defaultValue,//default value
					true,//adalah field ini untuk kegunaan carian
					onBlurFunction,//apa js yang dipanggil semasa onblur
					setDataList,//jika field ini kan menggunakan datalist
					db //db object
					);
		}
		
		
		public String settingCRUDTapakAuto(String skrinName, String seqName,//skrinName
				String tableName,String filterScript, String tajuk, //db object
				String PK_FIELD, Db db) throws Exception {
			
			String html = "<input type=\"hidden\" id=\"divId"+skrinName+"\" name=\"divId"+skrinName+"\" value=\"\" /> "+
			"<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-bottom:5px;cursor:pointer;\" class=\"box_shadow\"  "+
			" onclick=\"openCrudSkrinRT('"+skrinName+"','"+seqName+"','"+tableName+"','"+filterScript+"','"+PK_FIELD+"')\"> "+
			"<tr class=\"table_header\" > "+
			"<td width=\"2%\" class=\"underline_td_sub\"> "+
			"</td> "+
			"<td width=\"58%\" class=\"underline_td_sub\"> "+
			"<span id=\"icon_"+skrinName+"\" >> </span><strong>"+tajuk+"</strong><input type=\"hidden\" name=\"flag_"+skrinName+"\" id=\"flag_"+skrinName+"\" value=\"close\" /></td> "+
			"<td width=\"38%\" class=\"underline_td_sub\" align=\"right\" valign=\"top\" > "+	
			"</td> "+
			"<td width=\"2%\" class=\"underline_td_sub\"> "+
			"</td> "+
			"</tr> "+
			"</table> "+
			"<div style=\"margin-bottom:5px\" id=\"viewCrud"+skrinName+"\"></div> ";						
			return html;
		}
		
		
		
		public String setRowTarikhAuto(String skrinName,//skrinName
				String mode,Map mapRekod,
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,
				int maxLength,//setting maxlength
				String defaultValue,//default value
				Db db //db object
				) throws Exception {
			String labelName = fieldName.replace("_", " ");
			return setRowTarikh(skrinName,//skrinName
					true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					mode,//mode : edit/view
					mapRekod,//set data
					labelName,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					mandatory,// adakah field ini mandatory true=ya
					"text",// hidden/text/currencyOnly/areaOnly 
					true,// display : diantara label & field
					maxLength,//setting maxlength
					true,//defaultUppercase pada value input
					defaultValue,//default value
					true,//adalah field ini untuk kegunaan carian
					"",//apa js yang dipanggil semasa onblur
					"",//jika field ini kan menggunakan datalist
					db //db object
					);
		}

		
		

		public String setRowTextAuto(String skrinName,//skrinName
				//boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				//String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/text/currencyOnly/areaOnly 
				//boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				//boolean flagUppercase,//defaultUppercase pada value input
				//String defaultValue,//default value
				//boolean skrinCarian,//adalah field ini untuk kegunaan carian
				//String onBlurFunction,//apa js yang dipanggil semasa onblur
				//String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			
			String labelName = fieldName.replace("_", " ");
			
			return setRowText(skrinName,//skrinName
					true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					mode,//mode : edit/view
					mapData,//set data
					labelName,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					mandatory,// adakah field ini mandatory true=ya
					jenis_field,// hidden/text/currencyOnly/areaOnly 
					true,// display : diantara label & field
					maxLength,//setting maxlength
					false,//defaultUppercase pada value input
					"",//default value
					false,//adalah field ini untuk kegunaan carian
					"",//apa js yang dipanggil semasa onblur
					"",//jika field ini kan menggunakan datalist
					db //db object
					);
		}
		
		
		
		public String setRowTextCarian(String skrinName,//skrinName
				//boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				//String mode,//mode : edit/view
				//Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				//boolean mandatory,// adakah field ini mandatory true=ya
				//String jenis_field,// hidden/text/currencyOnly/areaOnly 
				//boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				//boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				//boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onBlurFunction,//apa js yang dipanggil semasa onblur
				String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			
			return setRowText(skrinName,//skrinName
					true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					"edit",//mode : edit/view
					null,//set data
					label,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					false,// adakah field ini mandatory true=ya
					"text",// hidden/text/currencyOnly/areaOnly 
					true,// display : diantara label & field
					maxLength,//setting maxlength
					true,//defaultUppercase pada value input
					defaultValue,//default value
					true,//adalah field ini untuk kegunaan carian
					onBlurFunction,//apa js yang dipanggil semasa onblur
					setDataList,//jika field ini kan menggunakan datalist
					db //db object
					);
		}
		
	
		//templete input fordate
		public String setRowTarikh(String skrinName,//skrinName
				boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/text
				boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onBlurFunction,//apa js yang dipanggil semasa onblur
				String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			
			fieldName = fieldName.toUpperCase().trim();
			String getValue = "";
			if(mapData!=null)
			{
				//set value pada filed jika data asal dari set data
				getValue = getValueFormMapData(mapData, fieldName, db);				
			}
			else
			{
				getValue = defaultValue;
			}
			
			String html = "";
			if(!jenis_field.equals("hidden"))
			{
				html += "<tr id='row"+skrinName+fieldName+"'>";
				html += "<td align='center' valign='top' >";
				if(mandatory==true && mode.equals("edit"))
				{
					html += "<font color='red'>*</font>";
				}
				html +=	"</td>";
				html += "<td align='left' valign='top' ><span id='label"+skrinName+fieldName+"' >";
				if(!label.equals(""))
				{
					html += label;
				}		
				html +=	"</span></td>";
				html += "<td align='center' valign='top' >";
				if(showTitik==true)
				{
					html += ":";
				}		
				html +=	"</td>";
				html += "<td align='left' valign='top' >";	
							
				if(mode.equals("view"))
				{	
					if(flagUppercase==true)
					{
						getValue = getValue.toUpperCase();
					}
					if(!getValue.equals(""))
					{
						html += getValue;
					}
					else
					{
						html += "";
					}
					html += " <input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  /> ";
				}
				else
				{
					String strMaxLength = "";
					if(maxLength>0)
					{
						strMaxLength = "maxlength='"+maxLength+"'";
					}
					
					String styleUppercase = "";
					if(flagUppercase==true)
					{
						styleUppercase = "text-transform:uppercase";
					}
					
					
					html += "<input type='text' "+strMaxLength+" size = '10' style='"+styleUppercase+"' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' onBlur=\""+onBlurFunction+"\" value = '"+getValue+"'  />";
					html += " <a href=\"javascript:displayDatePicker('"+skrinName+fieldName+"',false,'dmy');\"><img border=\"0\" src=\"../img/calendar.gif\"/></a>";
				}
				html +=	"</td>";
				html += "</tr>";
			}
			else
			{
				html += "<input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  />";
			}
			return html;
		}
		
		
		//templete input type text
		public String setRowText(String skrinName,//skrinName
				boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/text/currencyOnly/areaOnly 
				boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onBlurFunction,//apa js yang dipanggil semasa onblur
				String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			
			
			fieldName = fieldName.toUpperCase().trim();
			String getValue = "";
			if(mapData!=null)
			{
				//set value pada filed jika data asal dari set data
				getValue = getValueFormMapData(mapData, fieldName, db);
				//getValue = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name,db);				
			}
			else
			{
				getValue = defaultValue;
			}
			
			//myLogger.info(">>>>>>>>> setRowText flagUppercase : "+flagUppercase+ " getValue : "+getValue);
			
			String html = "";
			if(!jenis_field.equals("hidden"))
			{
				if(withTDTR == true)
				{
					html += "<tr id='row"+skrinName+fieldName+"' >";
					html += "<td align='center' valign='top' >";
					if(mandatory==true && mode.equals("edit"))
					{
						html += "<font color='red'>*</font>";
					}
					html +=	"</td>";
					html += "<td align='left' valign='top' ><span id='label"+skrinName+fieldName+"' >";
					if(!label.equals(""))
					{
						html += label;
					}		
					html +=	"</span></td>";
					html += "<td align='center' valign='top' >";
					if(showTitik==true)
					{
						html += ":";
					}		
					html +=	"</td>";
					html += "<td align='left' valign='top' >";	
				}
								
				if(mode.equals("view"))
				{	
					if(flagUppercase==true)
					{
						getValue = getValue.toUpperCase();
					}
					
					if(jenis_field.equals("currencyOnly"))
					{
						if(!getValue.equals(""))
						{
							try
							{
								DecimalFormat df = new DecimalFormat("###,###,##0.00");
								df.setRoundingMode(RoundingMode.CEILING);
								String getValueCurrency = df.format(Double.parseDouble(getValue));
								html += getValueCurrency;
							}
							catch(NumberFormatException e)
							{
								html += getValue;
							}
						}
						else 
						{
							html += "-";
						}
					}
					else if(jenis_field.equals("areaOnly"))
					{
						if(!getValue.equals(""))
						{
							try
							{
								DecimalFormat df = new DecimalFormat("########0.0000");
								df.setRoundingMode(RoundingMode.CEILING);
								String getValueArea = df.format(Double.parseDouble(getValue));														
								html += getValueArea;							
							}
							catch(NumberFormatException e)
							{
								html += getValue;
							}							
						}
					}
					else
					{
						html += getValue;
						html += "<input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  />";
					}
				}
				else
				{
								
					String strMaxLength = "";
					if(maxLength > 0)
					{
						strMaxLength = "maxlength='"+maxLength+"'";
					}
					
					String styleUppercase = "";
					if(flagUppercase==true)
					{
						styleUppercase = "text-transform:uppercase";
					}
														
					if(jenis_field.equals("numbersOnly"))
					{
						html += "<input type='text' "+strMaxLength+" onkeydown=\"validateNumber(event);\" onblur=\"recheckNumber(this);\" name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' onBlur=\""+onBlurFunction+"\" class='fullwidth_input' value = '"+getValue+"'  />";
					}
					else if(jenis_field.equals("currencyOnly"))
					{
						html += "<input type='text' "+strMaxLength+" style='"+styleUppercase+"'  onBlur=\"validateCurrencyBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\" name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' onBlur=\""+onBlurFunction+"\" class='fullwidth_input' value = '"+getValue+"'  />";
					}
					else if(jenis_field.equals("areaOnly"))
					{
						html += "<input type='text' "+strMaxLength+" style='"+styleUppercase+"'  onBlur=\"validateAreaBlur(event,this,this.value);\" onKeyUp=\"validateCurrency(event,this,this.value);\" name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"'  class='fullwidth_input' value = '"+getValue+"'  />";
					}
					else
					{	
						String callDataList = "";
						if(!setDataList.equals(""))
						{
							callDataList = " list = 'datalist"+skrinName+fieldName+"' ";
						}
						
						String classInput = "";
						if(skrinCarian==true)
						{
							classInput = "fullwidthCarian_input";
						}
						else 
						{
							classInput = "fullwidth_input";							
						}
						
						html += "<input type='text'   "+callDataList+"    "+strMaxLength+" style='"+styleUppercase+"' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' onBlur=\""+onBlurFunction+"\" class='"+classInput+"' value = \""+getValue+"\"  />";
						
						//bila field carian, kita bagi option untuk buat carian tepat atau wildcat
						if(skrinCarian==true)
						{
							html += "&nbsp<select id='WC"+skrinName+fieldName+"' id='WC"+skrinName+fieldName+"' class='fullwidthWC_input'  >";			
							html += "<option value='1' >TEPAT</option>";
							html += "<option value='2' >RAWAK</option>";
							html += "</select>";
							
						}
					
					}				
				}				
				if(withTDTR == true)
				{
					html +=	"</td>";
					html += "</tr>";
				}
			}
			else
			{
				html += "<input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  />";
			}
			return html;
		}

		public List list_HC(String namaHC_List) throws Exception
		{
			return list_HC(namaHC_List,null);
		}
		
		//SETUP REKOD UNTUK HARDCODED DATA
		public List list_HC(String namaHC_List, Db db) throws Exception
		{
			List hardCodedList = Collections.synchronizedList(new ArrayList());
			
			CachedObject get_CachedObject =  (CachedObject)CacheManager.getCache(namaHC_List);
			if (get_CachedObject != null)
			{
				hardCodedList =  (List)get_CachedObject.object;
			}
			else
			{
				CacheManager.removeCache(namaHC_List);
			}
			
			if(get_CachedObject == null)					
			{			
				if(namaHC_List.equals("HC_JENISMODUL") )
				{
					hardCodedList.add(hash_HC("2", "MODUL PUSAKA"));
					hardCodedList.add(hash_HC("1", "MODUL PENGAMBILAN"));
					hardCodedList.add(hash_HC("4", "MODUL PHP"));
					hardCodedList.add(hash_HC("3", "MODUL HTP"));
				}
				else if(namaHC_List.equals("HC_JENIS_KPI_PPT") )
				{
					hardCodedList.add(hash_HC("1", "WARTA SEHINGGA BORANG H"));
					hardCodedList.add(hash_HC("2", "BORANG H SEHINGGA BORANG K"));
					hardCodedList.add(hash_HC("3", "BORANG K SEHINGGA ENDORSAN BORANG K"));
				}
				
				if(!namaHC_List.equals(""))
				{
					CachedObject set_CachedObject = new CachedObject(hardCodedList, namaHC_List, 0);
					CacheManager.putCache(set_CachedObject);	
				}
			}
			return hardCodedList;
		}
		
		public String value_HC(String namaHC_List, String HC_value) throws Exception
		{
			List hardCodedList = Collections.synchronizedList(new ArrayList());			
			CachedObject get_CachedObject =  (CachedObject)CacheManager.getCache(namaHC_List);
			if (get_CachedObject != null)
			{
				hardCodedList =  (List)get_CachedObject.object;
			}
			else
			{
				CacheManager.removeCache(namaHC_List);
				hardCodedList = list_HC(namaHC_List);
			}
			
			String keterangan = "";
			for (int j = 0; j < hardCodedList.size(); j++) {
				Map mapSetupSkrin = (Map) hardCodedList.get(j);
				String VALUE = (String) mapSetupSkrin.get("VALUE");
				String KETERANGAN = (String) mapSetupSkrin.get("KETERANGAN");
				
				if(VALUE.equals(HC_value))
				{
					keterangan = KETERANGAN;
				}
			}
			return keterangan;
		}
		
		//method setup column
		public Map hash_HC(String value, String keterangan) 
				throws Exception
		{
			Map h = Collections.synchronizedMap(new HashMap());
			h.put("VALUE",value);
			h.put("KETERANGAN",keterangan);
			//myLogger.info(" getColumnForSenarai : "+h);
			return h;	
		}
		
		
		//templete input type text
		public String setRowSelectHC(String skrinName,//skrinName
				boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				String namaList_HC,// nama list hard code
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/select/radio
				boolean showTitik,// display : diantara label & field
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onChangeFunction,//apa js yang dipanggil semasa onChange
				Db db //db object
				) throws Exception {
			
			return setRowSelectMain(skrinName,//skrinName
					withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					mode,//mode : edit/view
					mapData,//set data
					label,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					namaList_HC,// nama list hard code
					mandatory,// adakah field ini mandatory true=ya
					jenis_field,// hidden/select/radio
					showTitik,// display : diantara label & field
					flagUppercase,//defaultUppercase pada value input
					defaultValue,//default value
					skrinCarian,//adalah field ini untuk kegunaan carian
					onChangeFunction,//apa js yang dipanggil semasa onChange
					"","","","","",
					db //db object
					);
		}
		
		
		//templete input type text
		public String setRowSelect(String skrinName,//skrinName
				boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/select/radio
				boolean showTitik,// display : diantara label & field
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onChangeFunction,//apa js yang dipanggil semasa onChange
				String refTable,String column_Value, String column_Display, String filter, String column_OrderBy,
				Db db //db object
				) throws Exception {
			
			return setRowSelectMain(skrinName,//skrinName
					withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					mode,//mode : edit/view
					mapData,//set data
					label,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					"",// nama list hard code
					mandatory,// adakah field ini mandatory true=ya
					jenis_field,// hidden/select/radio
					showTitik,// display : diantara label & field
					flagUppercase,//defaultUppercase pada value input
					defaultValue,//default value
					skrinCarian,//adalah field ini untuk kegunaan carian
					onChangeFunction,//apa js yang dipanggil semasa onChange
					refTable,column_Value, column_Display, filter, column_OrderBy,
					db //db object
					);
		}
		
		
		//templete input type text
		public String setRowSelectMain(String skrinName,//skrinName
				boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				String namaList_HC,// nama list hard code
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/select/radio
				boolean showTitik,// display : diantara label & field
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onChangeFunction,//apa js yang dipanggil semasa onChange
				String refTable,
				String column_Value, 
				String column_Display, 
				String filter, 
				String column_OrderBy,
				Db db //db object,				
				) throws Exception {
			
			
			fieldName = fieldName.toUpperCase().trim();
			String getValue = "";
			if(mapData!=null)
			{
				//set value pada filed jika data asal dari set data
				getValue = getValueFormMapData(mapData, fieldName, db);
				//getValue = getValue(session,ID_PERMOHONANSIMATI, map_data,table_name,field_main_PK,value_main_PK,id_perbicaraan,column_name,db);				
			}
			else
			{
				getValue = defaultValue;
			}
			
			//myLogger.info(">>>>>>>>> setRowText flagUppercase : "+flagUppercase+ " getValue : "+getValue);
			
			String html = "";
			if(!jenis_field.equals("hidden"))
			{
				if(withTDTR == true)
				{
					html += "<tr id='row"+skrinName+fieldName+"' >";
					html += "<td align='center' valign='top' >";
					if(mandatory==true && mode.equals("edit"))
					{
						html += "<font color='red'>*</font>";
					}
					html +=	"</td>";
					html += "<td align='left' valign='top' ><span id='label"+skrinName+fieldName+"' >";
					if(!label.equals(""))
					{
						html += label;
					}		
					html +=	"</span></td>";
					html += "<td align='center' valign='top' >";
					if(showTitik==true)
					{
						html += ":";
					}		
					html +=	"</td>";
					html += "<td align='left' valign='top' >";	
				}
								
				html += "<div id='div"+skrinName+fieldName+"' >";	
				if(mode.equals("view"))
				{	
					String displayValue = "";
					if(!namaList_HC.equals(""))
					{
						displayValue = value_HC(namaList_HC, getValue).toUpperCase();
					}
					else if(!refTable.equals(""))
					{
						displayValue = valueRefTable(session, skrinName, fieldName,refTable, column_Value, column_Display, " "+column_Value+" = '"+getValue+"' ", db);
					}
					
					if(flagUppercase==true)
					{
						displayValue = displayValue.toUpperCase();
					}
					
					html += displayValue;
					html += "<input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  />";
					
				}
				else
				{	
					String styleUppercase = "";
					if(flagUppercase==true)
					{
						styleUppercase = "text-transform:uppercase";
					}					
						
					
					List hardCodedList = null;
					if(!namaList_HC.equals(""))
					{
						hardCodedList = list_HC(namaList_HC);
					}
					else if(!refTable.equals(""))
					{
						hardCodedList = listRefTable(session, skrinName, fieldName,
								refTable,column_Value, column_Display, filter, column_OrderBy, db);
					}
							
					if(jenis_field.equals("select"))
					{					
						html += "<select id='"+skrinName+fieldName+"' name='"+skrinName+fieldName+"'  style='"+styleUppercase+"' class='fullwidth_input' onChange = \""+onChangeFunction+"\" >";
						String selectedSilaPilih = "";
						if(getValue.equals(""))
						{
							selectedSilaPilih = "selected";
						}
						html += "<option value='' "+selectedSilaPilih+" >SILA PILIH</option>";
						
						for (int j = 0; j < hardCodedList.size(); j++) {
							Map mapSetupSkrin = (Map) hardCodedList.get(j);
							String VALUE = (String) mapSetupSkrin.get("VALUE");
							String KETERANGAN = (String) mapSetupSkrin.get("KETERANGAN");
							if(flagUppercase==true)
							{
								KETERANGAN = KETERANGAN.toUpperCase();
							}
							
							String selected = "";
							if(getValue.equals(VALUE) && !getValue.equals(""))
							{
								selected = "selected";
							}
							html += "<option value='"+VALUE+"' "+selected+" >"+KETERANGAN+"</option>";
						}
						html += "</select>";
					}
					else if(jenis_field.equals("radio"))
					{							
						if (hardCodedList.size() > 0) {
							html += "<table width='75%' cellspacing='1' cellpadding='1' border='0' >";
							for (int i = 0; i < hardCodedList.size(); i++) {
								Map mapSetupSkrin = (Map) hardCodedList.get(i);
								String VALUE = (String) mapSetupSkrin.get("VALUE");
								String KETERANGAN = (String) mapSetupSkrin.get("KETERANGAN");
								if(flagUppercase==true)
								{
									KETERANGAN = KETERANGAN.toUpperCase();
								}
								String checked = "";
								if(getValue.equals(VALUE) && !getValue.equals(""))
								{
									checked = "checked";
								}
								html += "<tr>";
								html += "<td align='right' width='5%' valign='top'>";
								html += "<input type='radio' onclick=\"document.getElementById('"+skrinName+fieldName+"').value=this.value;"+onChangeFunction+"\" "+checked+" " +
										"name='"+skrinName+fieldName+"Radio' id='"+skrinName+fieldName+"Radio' value='"+VALUE+"' > ";
								html += "</td>";
								html += "<td width='1%' valign='top' ></td>";
								String alignRadioKeterangan = "left";
								String styleRadioKeterangan = "";
								String specialKeterangan = "";
								html += "<td align='"+alignRadioKeterangan+"' width='94%' valign='top'   >";
								html += "<div style=\""+styleRadioKeterangan+"\" >"+KETERANGAN+"</div>";
								html += "</td>";
								html += "</tr>";
							}
							String checkedSilaPilih = "";
							if(getValue.equals(""))
							{
								checkedSilaPilih = "checked";
							}
							html += "<tr>";
							html += "<td align='right' valign='top'>";
							html += "<input type='radio' onclick=\"document.getElementById('"+skrinName+fieldName+"').value=this.value;"+onChangeFunction+"\" "+checkedSilaPilih+" " +
									"name='"+skrinName+fieldName+"Radio' id='"+skrinName+fieldName+"Radio' value='' > ";
							html += "</td>";
							String alignRadioKeterangan = "left";
							String styleRadioKeterangan = "";
							String specialKeterangan = "";
							html += "<td valign='top' ></td>";
							html += "<td align='"+alignRadioKeterangan+"' valign='top'   >";
							String tiadaRekod = "Tiada Rekod";							
							if(flagUppercase==true)
							{
								tiadaRekod = tiadaRekod.toUpperCase();
							}							
							html += "<div style=\""+styleRadioKeterangan+"\" >"+tiadaRekod+"</div>";
							html += "</td>";
							html += "</tr>";							
							html += "</table>";
						}
						html += "<input type='hidden'  name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value='"+getValue+"' >";
					}
				}	
				html += "</div>";
				
				if(withTDTR == true)
				{
					html +=	"</td>";
					html += "</tr>";
				}
			}
			else
			{
				html += "<input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  />";
			}
			return html;
		}
		
		public String queryTableRef(HttpSession session, String skrinName, String column_name,
				String refTable,String column_Value, String column_Display, String filter, String column_OrderBy, Db db)
		{
			String sql = " SELECT DISTINCT "+column_Value+" AS VALUE, "+column_Display+" AS KETERANGAN FROM "+refTable+" WHERE "+column_Value+" IS NOT NULL ";
			if(!filter.equals(""))
			{
				sql += " AND "+filter+" ";
			}
			
			if(!column_OrderBy.equals(""))
			{
				sql += " ORDER BY "+column_OrderBy+" ";
			}
			
			return sql;
		}
		
		
		
		@SuppressWarnings("unchecked")
		public List listRefTable(HttpSession session, String skrinName, String column_name,
				String refTable,String column_Value, String column_Display, String filter, String column_OrderBy, Db db) throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listRefTable = null;
			String sql = "";	
			
			String CacheName = "REFLIST_"+column_Value+column_Display+refTable+filter;
			CacheName = CacheName.replace(" ", "_");
			myLogger.info("RT listRefTable >>> CacheName : "+CacheName);
			
			CachedObject get_CachedObject =  (CachedObject)CacheManager.getCache(CacheName);
			if (get_CachedObject != null)
			{
				listRefTable = Collections.synchronizedList(new ArrayList());
				listRefTable =  (List)get_CachedObject.object;
			}
			else
			{
				CacheManager.removeCache(CacheName);
			}
			
			if(get_CachedObject == null)					
			{		
			
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
					
					
					
					sql = queryTableRef(session,skrinName,column_name,refTable,column_Value,column_Display,filter,column_OrderBy,db);
					
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					rs = executeQueryRT(stmt, sql ,methodName);
					
					listRefTable = Collections.synchronizedList(new ArrayList());		
					Map h = null;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());	
						String VALUE = rs == null ? "" :rs.getString("VALUE") == null ? "" : rs.getString("VALUE");
						String KETERANGAN = rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN");
						h.put("VALUE",VALUE);		
						h.put("KETERANGAN",KETERANGAN);		
						listRefTable.add(h);
					}
					
					if(!CacheName.equals(""))
					{
						CachedObject set_CachedObject = new CachedObject(listRefTable, CacheName, 0);
						CacheManager.putCache(set_CachedObject);	
					}
					
				} finally {
					if (db == null)
					{
						db1.close();
					}
				}
			
			}
			return listRefTable;
		}
		
		
		@SuppressWarnings("unchecked")
		public String valueRefTable(HttpSession session, String skrinName, String column_name,
				String refTable,String column_Value, String column_Display, String filter, Db db) throws Exception {
			Db db1 = null;
			ResultSet rs = null;
			Statement stmt = null;
			//List listRefTable = null;
			String keterangan = "";
			String sql = "";	
			
			String CacheName = "REFVALUE_"+column_Value+column_Display+refTable+filter;
			CacheName = CacheName.replace(" ", "_");
			myLogger.info("RT valueRefTable >>> CacheName : "+CacheName);
			
			CachedObject get_CachedObject =  (CachedObject)CacheManager.getCache(CacheName);
			if (get_CachedObject != null)
			{
				keterangan =  (String)get_CachedObject.object;
			}
			else
			{
				CacheManager.removeCache(CacheName);
			}
			
			if(get_CachedObject == null)					
			{		
			
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
					
					sql = queryTableRef(session,skrinName,column_name,refTable,column_Value,column_Display,filter,"",db);
					
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					rs = executeQueryRT(stmt, sql ,methodName);
					
					while (rs.next()) {
						String KETERANGAN = rs == null ? "" :rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN");
						keterangan = KETERANGAN;
					}
					
					if(!CacheName.equals(""))
					{
						CachedObject set_CachedObject = new CachedObject(keterangan, CacheName, 0);
						CacheManager.putCache(set_CachedObject);	
					}
					
				} finally {
					if (db == null)
					{
						db1.close();
					}
				}
			
			}
			return keterangan;
		}
		
		
		public String setRowTextAreaAuto(String skrinName,//skrinName
				//boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				//String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/text/currencyOnly/areaOnly 
				//boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				int minHeight,
				//boolean flagUppercase,//defaultUppercase pada value input
				//String defaultValue,//default value
				//boolean skrinCarian,//adalah field ini untuk kegunaan carian
				//String onBlurFunction,//apa js yang dipanggil semasa onblur
				//String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			
			String labelName = fieldName.replace("_", " ");
			
			return setRowTextArea(skrinName,//skrinName
					true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
					mode,//mode : edit/view
					mapData,//set data
					labelName,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					mandatory,// adakah field ini mandatory true=ya
					jenis_field,// hidden/text/currencyOnly/areaOnly 
					true,// display : diantara label & field
					maxLength,//setting maxlength
					minHeight,//minimun height
					false,//defaultUppercase pada value input
					"",//default value
					false,//adalah field ini untuk kegunaan carian
					"",//apa js yang dipanggil semasa onblur
					"",//jika field ini kan menggunakan datalist
					db //db object
					);
		}
		
		
		public String setRowTextArea(String skrinName,//skrinName
				boolean withTDTR,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/text/currencyOnly/areaOnly 
				boolean showTitik,// display : diantara label & field
				int maxLength,//setting maxlength
				int minHeight,//minimum height
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				boolean skrinCarian,//adalah field ini untuk kegunaan carian
				String onBlurFunction,//apa js yang dipanggil semasa onblur
				String setDataList,//jika field ini kan menggunakan datalist
				Db db //db object
				) throws Exception {
			fieldName = fieldName.toUpperCase().trim();
			String getValue = "";
			if(mapData!=null)
			{
				//set value pada filed jika data asal dari set data
				getValue = getValueFormMapData(mapData, fieldName, db);
						
			}
			else
			{
				getValue = defaultValue;
			}
			
			String html = "";
			if(!jenis_field.equals("hidden"))
			{
				if(withTDTR == true)
				{
					html += "<tr id='row"+skrinName+fieldName+"' >";
					html += "<td align='center' valign='top' >";
					if(mandatory==true && mode.equals("edit"))
					{
						html += "<font color='red'>*</font>";
					}
					html +=	"</td>";
					html += "<td align='left' valign='top' ><span id='label"+skrinName+fieldName+"' >";
					if(!label.equals(""))
					{
						html += label;
					}		
					html +=	"</span></td>";
					html += "<td align='center' valign='top' >";
					if(showTitik==true)
					{
						html += ":";
					}		
					html +=	"</td>";
					html += "<td align='left' valign='top' >";	
				}
								
				if(mode.equals("view"))
				{	
					if(flagUppercase==true)
					{
						getValue = getValue.toUpperCase();
					}
					
					html += "<div class=\"cal_scroller\" ><div   class=\"cal_container\" >"+getValue+"</div></div>";
					html += "<textarea name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' style='display:none;' >"+getValue+"</textarea>";					
				}
				else
				{
						
					String strHeight = "";
					if(minHeight>0)
					{
						strHeight = "height:"+minHeight+"px;";
					}
					
					String strMaxLength = "";
					if(maxLength > 0)
					{
						strMaxLength = "maxlength='"+maxLength+"'";
					}
					
					String styleUppercase = "";
					if(flagUppercase==true)
					{
						styleUppercase = "text-transform:uppercase;";
					}
														
					
					
					
					html += "<textarea rows='4' style='"+styleUppercase+strHeight+"' "+strMaxLength+" spellcheck=\"false\" name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' ";
					if(skrinCarian==true)
					{
						html += " class='fullwidthCarian_input'  ";
					}
					else 
					{
						html += " class='fullwidth_input'  ";
					}
					html += " >"+getValue+"</textarea>  ";
								
				}				
				if(withTDTR == true)
				{
					html +=	"</td>";
					html += "</tr>";
				}
			}
			else
			{
				html += "<textarea name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' style='display:none;' >"+getValue+"</textarea>";
			}
			return html;
		}
				

		
		public String openHTMLTable() throws Exception {
			String html = "";
			html += "<table width='100%' align='center' border='0' cellspacing='1' cellpadding='2'  class='classFade' > ";
			html += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
			return html;
		}
		
		public String closeHTMLTable() throws Exception {
			String html = "";
			html += "</table>";
			return html;
		}
		
		
		public String setRowSelectCarian(String skrinName,//skrinName
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				String jenis_field,// hidden/select/radio
				String defaultValue,//default value
				String onChangeFunction,//apa js yang dipanggil semasa onblur
				String onReadyFunction,//apa js yang dipanggil masa load jsp
				String tableName,//nama table rujukan
				String fieldPK,//nama field PK
				String fieldKOD,//nama field KOD jika ada
				String fieldKeterangan,//nama field keterangan
				Db db //db object
				) throws Exception {
			
			return setRowSelect(skrinName,//skrinName
					"edit",//mode : edit/view
					null,//set data
					label,//label field 
					fieldName,// nama & id field, sama kan dengan nama filed dalam DB
					false,// adakah field ini mandatory true=ya
					jenis_field,// hidden/select/radio
					true,// display : diantara label & field
					true,//defaultUppercase pada value input
					defaultValue,//default value
					onChangeFunction,//apa js yang dipanggil semasa onblur
					onReadyFunction,//apa js yang dipanggil masa load jsp
					tableName,//nama table rujukan
					fieldPK,//nama field PK
					fieldKOD,//nama field KOD jika ada
					fieldKeterangan,//nama field keterangan
					db //db object
					); 
		}
		
		
		public String setRowSelect(String skrinName,//skrinName
				String mode,//mode : edit/view
				Map mapData,//set data
				String label,//label field 
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				boolean mandatory,// adakah field ini mandatory true=ya
				String jenis_field,// hidden/select/radio
				boolean showTitik,// display : diantara label & field
				boolean flagUppercase,//defaultUppercase pada value input
				String defaultValue,//default value
				String onChangeFunction,//apa js yang dipanggil semasa onblur
				String onReadyFunction,//apa js yang dipanggil masa load jsp
				String tableName,//nama table rujukan
				String fieldPK,//nama field PK
				String fieldKOD,//nama field KOD jika ada
				String fieldKeterangan,//nama field keterangan
				Db db //db object
				) throws Exception {
			String html = "";
			String getValue = "";
			if(mapData!=null)
			{
				getValue = getValueFormMapData(mapData, fieldName, db);
			}
			else
			{
				getValue = defaultValue;
			}
			
			String styleTR = "";
			if(jenis_field.equals("hidden"))
			{
				styleTR = "display:none;";
			}
			
			html += "<tr id='row"+skrinName+fieldName+"' style='"+styleTR+"' >";
			html += "<td align='center' valign='top' >";
			
			if(mandatory==true && mode.equals("edit"))
			{
				html += "<font color='red'>*</font>";
			}
			html +=	"</td>";
			html += "<td align='left' valign='top' ><span id='label"+skrinName+fieldName+"' >";
			if(!label.equals(""))
			{
				html += label;
			}		
			html +=	"</span></td>";
			html += "<td align='center' valign='top' >";
			if(showTitik==true)
			{
				html += ":";
			}		
			html +=	"</td>";
			html += "<td align='left' valign='top' >";
			html += "<div id='div"+skrinName+fieldName+"' >";	
			if(tableName.equals(""))
			{
				html += "";//hardcode
			}
			else
			{		
				myLogger.info("refDropDown >>>>>>>> "+fieldName +" getValue lepas ::: "+getValue);
				html += refDropDown(skrinName,//skrinName
						mode,//mode : edit/view
						mapData,//set data
						fieldName,// nama & id field, sama kan dengan nama filed dalam DB
						jenis_field,// hidden/select/radio
						defaultValue,//default value
						onChangeFunction,//apa js yang dipanggil semasa onblur
						onReadyFunction,//apa js yang dipanggil masa load jsp
						tableName,//nama table rujukan
						fieldPK,//nama field PK
						fieldKOD,//nama field KOD jika ada
						fieldKeterangan,//nama field keterangan
						getValue,
						db //db object
						);
			}			
			html += "</div>";
			
			html +=	"</td>";
			html += "</tr>";
		
			
			return html;			
		}
		
		public String refDropDown(String skrinName,//skrinName
				String mode,//mode : edit/view
				Map mapData,//set data
				String fieldName,// nama & id field, sama kan dengan nama filed dalam DB
				String jenis_field,// hidden/select/radio
				String defaultValue,//default value
				String onChangeFunction,//apa js yang dipanggil semasa onblur
				String onReadyFunction,//apa js yang dipanggil masa load jsp
				String tableName,//nama table rujukan
				String fieldPK,//nama field PK
				String fieldKOD,//nama field KOD jika ada
				String fieldKeterangan,//nama field keterangan
				String getValue,
				Db db //db object
				) throws Exception {
			
			String html = "";
						
			String styleTR = "";
			if(jenis_field.equals("hidden"))
			{
				styleTR = "display:none;";
			}
			
			
				myLogger.info("refDropDown >>> fieldName : "+fieldName+" table_name : "+onChangeFunction+" value : "+getValue);
							
				if(mode.equals("view"))
				{
					String valueDisplay = getValueRefTable(tableName,fieldPK,fieldKOD,fieldKeterangan,getValue, db);	
					html += valueDisplay;
					if(valueDisplay.equals(""))
					{
						html += "-";
					}
					html += " <input type='hidden' name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value = '"+getValue+"'  /> ";				
				}
				else
				{
					List listRefTable = listRefTable(skrinName, fieldName,tableName,fieldPK,fieldKOD,fieldKeterangan,db);
					if(jenis_field.equals("select"))
					{
						myLogger.info("DALAM SELECT >>> column_name : "+fieldName+" table_name : "+tableName+" value : "+getValue);
						
						html += "<select id='"+skrinName+fieldName+"' name='"+skrinName+fieldName+"' class='fullwidth_input'  onChange = \""+onChangeFunction+"\"> ";	
						//myLogger.info("listRefTable : "+listRefTable);
						html += "<option value='' >SILA PILIH</option>";
						if (listRefTable.size() != 0) {
							for (int i = 0; i < listRefTable.size(); i++) {
								Map map_column_name = (Map) listRefTable.get(i);
								String id = (String) map_column_name.get(fieldPK);
								String keterangan = (String) map_column_name.get(fieldKeterangan);
								String selected = "";
								if(getValue.equals(id))
								{
									selected = "selected";
								}
								html += "<option value='"+id+"' "+selected+" >"+keterangan+"</option>";									
							}					}		
						html += "</select>";
					}
					else if(jenis_field.equals("radio"))
					{							
						if (listRefTable.size() != 0) {
							html += "<table width='75%' cellspacing='0' cellpadding='0' border='0' >";
							for (int i = 0; i < listRefTable.size(); i++) {
								Map map_column_name = (Map) listRefTable.get(i);
								String id = (String) map_column_name.get(fieldPK);
								String keterangan = (String) map_column_name.get(fieldKeterangan);
								String checked = "";
								if(getValue.equals(id))
								{
									checked = "checked";
								}
								html += "<tr>";
								html += "<td align='center' width='5%' valign='top'>";
								
								html += "<input type='radio' onclick=\"document.getElementById('"+skrinName+fieldName+"').value=this.value;"+onChangeFunction+"\" "+checked+" " +
										"name='"+skrinName+fieldName+"Radio' id='"+skrinName+fieldName+"Radio' value='"+id+"' > ";
								html += "</td>";
															
								html += "<td align='left' width='95%' valign='top'   >";
								html += "<div  >"+keterangan+"</div>";
								html += "</td>";
								html += "</tr>";
							}
							
							
							
							html += "</table>";
						}
						
						html += "<input type='hidden'  name='"+skrinName+fieldName+"' id='"+skrinName+fieldName+"' value='"+getValue+"' >";
					}
				}
			
			html += "<script>$jquery(document).ready(function (){ "+onReadyFunction+" });</script>";
			
			return html;
				
			}
			
			public String filterSQLRef(String tableName, String skrinName)
			{
				String filter = "";
				
				if(tableName.equals("TBLRUJSTATUS") && modul.equals("PPK"))
				{
					filter += " AND ID_SEKSYEN = '2' ";
				}
				
				return filter;
			}
			
			public String orderBySQLRef(String tableName, String skrinName)
			{
				String order = " ORDER BY";
				
				if(tableName.equals("TBLRUJSTATUS"))
				{
					order += " KOD_STATUS ASC ";
				}
				
				return order;
			}
			
			/*
			public String successInfo(String content,String command,String skrinName)
			{				
				String html = "";
				if(!content.equals(""))
				{
				html += "<div id=\"displayInfo_"+skrinName+command+"\" class=\"info\"  align=\"left\" style=\"font-size:120%\">";
				html += content;
				html += "<script>";
				html += " $jquery(\"#displayInfo_"+skrinName+command+"\").show().delay(3000).fadeOut(); ";
				html += "</script>";
				html += "</div>";	
				}
				return html;
			}
			*/
		
			public void testSqlProblem(Db db)throws Exception {
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				Statement stmt = db.getStatement();
				executeDeleteRT("DELETE * FROM XXXXXXXXXXX",stmt, methodName, db);							
			}
			
			@SuppressWarnings("unchecked")
			public List listRefTable(String skrinName, String columnName,
					String tableName,String fieldPK,
					String fieldKOD,String fieldKeterangan, 
					Db db) throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				List list = null;
				String sql = "";	
				
				String filterSQL = filterSQLRef(tableName, skrinName);
				String orderBySQL =  orderBySQLRef(tableName, skrinName);
				
				CachedObject get_CachedObject = null;
				String cacheID = columnName+tableName+filterSQL+orderBySQL;
				
				if(!cacheID.equals(""))
				{
					get_CachedObject =  (CachedObject)CacheManager.getCache(cacheID);
					myLogger.info("get_CachedObject listRefTable : "+get_CachedObject);
					if (get_CachedObject != null)
					{
						list =  (List)get_CachedObject.object;
					}
					else
					{
						CacheManager.removeCache(cacheID);
					}
				}
				
				if(get_CachedObject == null)					
				{
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
						
						//extra field utk letak kat depan				
						sql = "SELECT ";
						sql += fieldPK+"," ;
											
						if(!fieldKOD.equals(""))
						{
							sql += fieldKOD+" || ' - ' ||  ";
						}
						
						sql += fieldKeterangan + " AS "+fieldKeterangan+" ";
						
						sql += " FROM "+tableName;
						
						sql += " WHERE "+fieldPK+" IS NOT NULL ";
						
						if(!filterSQL.equals(""))
						{
							sql += filterSQL;
						}
						
						if(!orderBySQL.equals(""))
						{
							sql += orderBySQL;
						}
						
						myLogger.info(" ("+tableName+") RT : SQL :"+ sql);	
						//rs = stmt.executeQuery(sql);
											
						currentSQL = sql;
						String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
						rs = executeQueryRT(stmt, sql ,methodName);
						
						
						list = Collections.synchronizedList(new ArrayList());		
						Map h = null;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());	
							h.put(fieldPK,rs == null ? "" :rs.getString(fieldPK) == null ? "" : rs.getString(fieldPK).toUpperCase());			
							String valueKeterangan = rs == null ? "" :rs.getString(fieldKeterangan) == null ? "" : rs.getString(fieldKeterangan);
							h.put(fieldKeterangan,valueKeterangan);			
							list.add(h);
						}
						rs.close();
					} finally {
						if (db == null)
						{
							db1.close();
						}
					}
					if(!cacheID.equals(""))
					{
						CachedObject set_CachedObject = new CachedObject(list, cacheID, 0);
						CacheManager.putCache(set_CachedObject);	
					}
				}
				
				return list;
			}
		
			
			@SuppressWarnings("unchecked")
			public String getValueRefTable(String tableName,String fieldPK,String fieldKod, 
					String fieldKeterangan, String value, Db db)throws Exception {
				Db db1 = null;
				ResultSet rs = null;
				Statement stmt = null;
				String sql = "";
				String returValue = "";
				try {
					if(db != null)
					{
						db1 = db;
					}
					else
					{
						db1 = new Db();
					}
					
					stmt = db1.getStatement();	
					sql = "SELECT ";
					if(!fieldKod.equals(""))
					{
						sql += fieldKod + " || ' - ' || ";
					}
					sql += " "+fieldKeterangan+" AS "+fieldKeterangan+" FROM "+tableName+" WHERE "+fieldPK+" = '"+value+"' ";		
					myLogger.info("getValueRefTable TR : SQL :"+ sql);			
					currentSQL = sql;
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					rs = executeQueryRT(stmt, sql ,methodName);
					Map h = null;		
					
					while (rs.next()) {	
						returValue = rs.getString(fieldKeterangan) == null ? "" : rs.getString(fieldKeterangan);						
					}
					rs.close();
					return returValue;
				} finally {
					if (db == null)
					{
						db1.close();
					}
				}
			}
			
			//fungsi ini untuk  cater ada 2 command param meeter yg sama..so kita akan pegang yang terakhir
			public String getLastParam(String paramName)
			{
				String[] pn = request.getParameterValues(paramName);
				String lastParam = getParam(paramName);
				if(pn != null)
				{ 
					for (String p : pn) {
						lastParam = p;
					}
				}
				return lastParam;
			}
			
			
			
			
			public String setupPagingHTML(String skrinName,String scrolPosition,String command,
					String sortType,String sortColumn,String sortColumnPosition,String sortColumnType,String sortDateFormat,
					String div,
					//String namaList,
					//String namaMap,
					String param,
					boolean isFirstPage, boolean isLastPage,
					int page,int totalRecords,int maxRowNum,int itemsPerPage, int page_mula, int totalPages, String classTable)
			{
				String formName = getFormName();
				String html = "";
				if(!scrolPosition.equals(""))
				{
					html += "<script>";
					html += "setPageLocation('"+scrolPosition+"');";
					html += "</script>";
				}							 
				html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" width=\"100%\"  class=\""+classTable+"\">"; 
				html += "<tr >";
				html += "<td align=\"left\" valign=\"top\" colspan=\"15\" >";				 
				html += "<input type=\"hidden\" id=\"sortType"+skrinName+command+"\" value=\""+sortType+"\" />";
				html += "<input type=\"hidden\" id=\"sortColumn"+skrinName+command+"\" value=\""+sortColumn+"\" />";
				html += "<input type=\"hidden\" id=\"sortColumnPosition"+skrinName+command+"\" value=\""+sortColumnPosition+"\" />";
				html += "<input type=\"hidden\" id=\"sortColumnType"+skrinName+command+"\" value=\""+sortColumnType+"\" />";
				html += "<input type=\"hidden\" id=\"sortDateFormat"+skrinName+command+"\" value=\""+sortDateFormat+"\" />";
				html += "<input type=\"hidden\" id=\"currentPage"+skrinName+command+"\"  name=\"currentPage"+skrinName+command+"\"  value=\""+page+"\"  />";
				html += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
				html += "<tr>";
				html += "<td  align=\"left\"><strong>Jumlah Rekod: "+totalRecords+"</strong>";				                    
                if(totalRecords > 0 && totalRecords == maxRowNum )
                {
                	html += "<div style=\"margin:5px\"><i><font color='blue'>Info</font> : Paparan maximum adalah "+maxRowNum+" rekod.</i></div>";	                   
                }
				html += "</td>";
				html += "<td  align=\"right\">";
				if(totalRecords > 0)
				{
					html += "Papar";
					html += "<select class=\"smallselect\" id=\"itemsPerPage"+skrinName+command+"\"  name=\"itemsPerPage"+skrinName+command+"\"  ";
					html += "onChange=\"doDivAjaxCall"+formName+"('"+div+"','"+command+"','div="+div+"&skrinName="+skrinName
							//+"&namaList="+namaList+"&namaMap="+namaMap
							+"&actionajax=doChangeItemPerPage"+param+"&scrolPosition='+getPageLocation());\" ";
					html += "> ";
					html += "<option value=\"10\" ";
					if(itemsPerPage == 10) 
					{
						html += "selected"; 
					}
					html += ">10</option>";
					html += "<option value=\"20\" ";
					if(itemsPerPage == 20) 
					{
						html += "selected"; 
					}
					html += ">20</option>";
					html += "<option value=\"30\" ";
					if(itemsPerPage == 30) 
					{
						html += "selected"; 
					}
					html += ">30</option>";
					html += "<option value=\"40\" ";
					if(itemsPerPage == 40) 
					{
						html += "selected"; 
					}
					html += ">40</option>";
					html += "<option value=\"50\" ";
					if(itemsPerPage == 50) 
					{
						html += "selected"; 
					}
					html += ">50</option>";
					html += "<option value=\"100\" ";
					if(itemsPerPage == 100) 
					{
						html += "selected"; 
					}
					html += ">100</option>";
					html += "</select>";
				}
				html += "</td>";
				html += "</tr>";
				html += "</table>";				            
				            
				html += "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
				html += "<tr>";
				html += "<td width=\"70%\" align=\"left\" valign=\"middle\">";
						
				if(isFirstPage)
				{
					html += "<img style=\"vertical-align=bottom;\" src=\"../img/paging/page-first-disabled.gif\">";
					html += "<img style=\"vertical-align=bottom;\" src=\"../img/paging/page-prev-disabled.gif\">";
				}
				else
				{
					int previous10 = page - 9;
					html += "<img title=\"First Page\" style=\"vertical-align=bottom;cursor:hand;cursor:pointer;\" ";
					html += "onClick=\"doDivAjaxCall"+formName+"('"+div+"','"+command+"','div="+div+"&skrinName="+skrinName
							//+"&namaList="+namaList+"&namaMap="+namaMap
							+"&actionajax=getPage"+param+"&value=1&scrolPosition='+getPageLocation());\" ";
					html += " src=\"../img/paging/page-first.gif\"> ";
					if (page > 10)
					{
						html += "<img title=\"Previous 10\" style=\"vertical-align=bottom;cursor:hand;cursor:pointer;\" ";
						html += " onClick=\"doDivAjaxCall"+formName+"('"+div+"','"+command+"','div="+div+"&skrinName="+skrinName
								//+"&namaList="+namaList+"&namaMap="+namaMap
								+"&actionajax=getPrevious"+param+"&page="+previous10+"&scrolPosition='+getPageLocation());\" ";
						html += " src=\"../img/paging/page-prev.gif\"> ";
					}
					else
					{
						html += "<img style=\"vertical-align=bottom;\" src=\"../img/paging/page-prev-disabled.gif\"> ";
					}
						   
				}
				html += "&nbsp;";
				
				int next10 = 0;
				if(totalPages > 0)
				{
					next10 = page + 9;
					int x = 0;
					for (int i = page_mula; i <= totalPages; i++)
					{
						x = x + 1;
						String classSquare = "";
						if (page == i)
						{
							classSquare = "squareV";
						}
						else
						{
							classSquare = "square";
						}
						
						if (i <= next10)
						{
							html += " <a href=\"javascript:doDivAjaxCall"+formName+"('"+div+"','"+command+"','div="+div+"&skrinName="+skrinName
									//+"&namaList="+namaList+"&namaMap="+namaMap
									+""+param+"&actionajax=getPage&value="+i+"&scrolPosition='+getPageLocation());\"> ";
							html += " <span class=\""+classSquare+"\">"+i+"</span> ";
							html += " </a> ";
						}
					}					
				}
				html += "&nbsp;";
				
				if(isLastPage)
				{
					html += "<img style=\"vertical-align=bottom;\" src=\"../img/paging/page-next-disabled.gif\"> ";
					html += "<img style=\"vertical-align=bottom;\" src=\"../img/paging/page-last-disabled.gif\"> ";
				}
				else
				{
					html += "<img title=\"Next 10\" style=\"vertical-align=bottom;cursor:hand;cursor:pointer;\"  ";
					html += " onClick=\"doDivAjaxCall"+formName+"('"+div+"','"+command+"','div="+div+"&skrinName="+skrinName
							//+"&namaList="+namaList+"&namaMap="+namaMap
							+"&actionajax=getNext"+param+"&page="+next10+"&scrolPosition='+getPageLocation());\" ";
					html += " src=\"../img/paging/page-next.gif\"> ";
					html += "<img title=\"Last page:"+totalPages+"\" style=\"vertical-align=bottom;cursor:hand;cursor:pointer;\"  ";
					html += " onClick=\"doDivAjaxCall"+formName+"('"+div+"','"+command+"','div="+div+"&skrinName="+skrinName
							//+"&namaList="+namaList+"&namaMap="+namaMap
							+"&actionajax=getPage"+param+"&value="+totalPages+"&scrolPosition='+getPageLocation());\" ";
					html += " src=\"../img/paging/page-last.gif\"> ";
					
				}
				html += "</td>";
				
				if (totalPages > 0)
				{
					html += " <td align=\"right\" nowrap>Mukasurat <b>"+page+"</b> dari "+totalPages+"</td> ";
				}
				html += "</tr>";
				html += "</table>";
				html += "</td>";
				html += "</tr>";
				html += "</table>";
				return html;
				   
			}
			
			public void editorUpdateEditor(String sql, Db db, Connection conn) throws SQLException
			{
				//conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				currentSQL = sql;
				myLogger.info(" editorUpdateEditor : "+sql);
				stmt.executeUpdate(sql);
				//conn.commit();
			}
			
			
            
            public void editorRunEditor(String sql, Db db, Connection conn) throws SQLException
			{
				//conn.setAutoCommit(false);
				Statement stmt = conn.createStatement();
				myLogger.info(" editorExecuteEditor : "+sql);
				currentSQL = sql;
	            stmt.executeUpdate(sql);				
				//conn.commit();
			}
			
			public void editorExecuteEditor(String sql, Db db, Connection conn) throws SQLException
			{
				//conn.setAutoCommit(false);
				Statement stmt = db.getStatement();
				currentSQL = sql;
				myLogger.info(" editorExecuteEditor : "+sql);
				stmt.execute(sql);
				//conn.commit();
			}
			
			public String saveCrudAuto(String tableName, String seqName, String skrinName, String pkField, String command, String cacheID, Db db, Connection conn) throws Exception {
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				String returnPK_tableName = "";			
				List columnNameFromQuery = returnColumnNameFromQuery(" SELECT * FROM "+tableName+" WHERE ROWNUM < 1 ", skrinName+"map", true, false, false, cacheID, db);
				if (columnNameFromQuery.size() != 0) {
					String pkValue = "";
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();		
					ArrayList<String> clobColumnName = new ArrayList<String>();
					for (int i = 0; i < columnNameFromQuery.size(); i++) {
						Map map_column_name = (Map) columnNameFromQuery.get(i);
						String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
						String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");				
						//myLogger.info("saveCrudAuto>>> pkField : "+pkField+" seqName : "+seqName+" COLUMN_NAME : "+COLUMN_NAME+" COLUMN_TYPE : "+COLUMN_TYPE+" VALUE : "+getParam(skrinName+COLUMN_NAME));
						
						if(!COLUMN_TYPE.equals("CLOB") && !COLUMN_TYPE.equals("BLOB"))
						{
							if(pkField.equals(COLUMN_NAME))
							{
								if(command.equals("saveCrud"))
								{
									pkValue =  getParam(skrinName+COLUMN_NAME);
									r.update(COLUMN_NAME, pkValue);
								}
								else if(command.equals("insertCrud"))
								{
									DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
									Date date = new Date();
									long id_seq = getNextID(db, seqName,stmt);
									returnPK_tableName = "Last Inserted ["+tableName+"]; PK : ["+id_seq+"]; Time : "+dateFormat.format(date);
									pkValue =  id_seq + "";
									r.add(COLUMN_NAME, id_seq);
								}
							}
							else if(command.equals("insertCrud") && (COLUMN_NAME.equals("ID_MASUK") || COLUMN_NAME.equals("ID_KEMASKINI")))
							{
								r.add(COLUMN_NAME, USER_ID_SYSTEM);
							}
							else if(command.equals("insertCrud") && (COLUMN_NAME.equals("TARIKH_MASUK") || COLUMN_NAME.equals("TARIKH_KEMASKINI")))
							{
								r.add(COLUMN_NAME, r.unquote("sysdate"));
							}
							else if(command.equals("saveCrud") && COLUMN_NAME.equals("ID_KEMASKINI"))
							{
								r.add(COLUMN_NAME, USER_ID_SYSTEM);
							}
							else if(command.equals("saveCrud") && COLUMN_NAME.equals("TARIKH_KEMASKINI"))
							{
								r.add(COLUMN_NAME, r.unquote("sysdate"));
							}
							else if(COLUMN_TYPE.equals("DATE"))
							{
								String formatTARIKH = "to_date('" + getParam(skrinName+COLUMN_NAME) + "','dd/MM/yyyy')";
								r.add(COLUMN_NAME, r.unquote(formatTARIKH));
							}						
							else
							{
								r.add(COLUMN_NAME, getParam(skrinName+COLUMN_NAME));
							}
							
							
						}
						else
						{
							clobColumnName.add(COLUMN_NAME);
						}
					}
					
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					if(command.equals("saveCrud"))
					{
						executeUpdateRT(r,tableName,stmt, methodName, db);
					}
					else if(command.equals("insertCrud"))
					{
						executeInsertRT(r,tableName,stmt, methodName, db);
					}
					
					if(clobColumnName.size()>0)
					{
						saveClobAuto(skrinName,clobColumnName,tableName,pkField,pkValue,db,conn);
					}
					
				}
				return returnPK_tableName;
			}
			
			public void saveClobAuto(String skrinName,ArrayList columnName,String tableName, String pkfield, String pkValue,Db db,Connection conn)throws Exception {	
							
					//Connection con = db.getConnection();
		        	//con.setAutoCommit(false);
				
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();	
					
					String sql = " UPDATE "+tableName+" SET ";
					for (int counter = 0; counter < columnName.size(); counter++) { 
						myLogger.info(" tableName : "+tableName+" >>>> column Name : "+columnName.get(counter));
				        //sql += " "+columnName.get(counter)+" = TRIM(REGEXP_REPLACE(?, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) ";
						sql += " "+columnName.get(counter)+" = ? ";
						if((counter+1) < columnName.size())
						{
							sql += ", ";
						}
				    }
					sql += " WHERE "+pkfield+" = ? ";
					
					myLogger.info("saveClobAuto sql : "+sql);
					myLogger.info(" >>>> columnName.size() : "+columnName.size());
					PreparedStatement ps = conn.prepareStatement(sql);
					for (int counter = 0; counter < columnName.size(); counter++) { 
						myLogger.info(" >>>> counter : "+(counter+1)+" >>>>>>>>>> column : "+skrinName+columnName.get(counter));
						//myLogger.info(" >>>> getParam : "+ getParam(skrinName+columnName.get(counter)));
						ps.setString((counter+1),getParam(skrinName+columnName.get(counter)));
				        //ps.setClob(counter+1, new StringReader(getParam(skrinName+columnName.get(counter)))); 
				        //ps.setclob
				    }
					ps.setString((columnName.size()+1), pkValue);
					ps.executeUpdate();	
					//con.commit();
					
					
					/*
				
					PreparedStatement ps = conn.prepareStatement("UPDATE TBLPPKBIKEHADIRAN SET " +
	        				"KETERANGAN = ?, " +
	        				"NOTA_PEGAWAI = ?  " +
	        				"WHERE ID_BIKEHADIRAN = ? ");
		        	ps.setString(1, getParam(skrinName+"KETERANGAN"));
		        	ps.setString(2, getParam(skrinName+"NOTA_PEGAWAI"));
		        	ps.setString(3, pkValue); 
		        	ps.executeUpdate();	
		        	con.commit();
						        
				    */
				
			}
			
			
			public void deleteCrudAuto(String skrinName,String tableName, String pkName, List listSetupSkrin, Db db, Connection conn, String NO_FAIL, String ID_FAIL) throws Exception
			{
				conn.setAutoCommit(false);
				List listTableToDelete = listTableToDelete(skrinName,tableName, pkName, listSetupSkrin);
				myLogger.info(">>>>>>>>>>>>>> deleteCrudAuto listTableToDelete : "+listTableToDelete);
				if (listTableToDelete.size() > 0 )
				{
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();		
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					for (int j = 0; j < listTableToDelete.size(); j++) {
						Map map = (Map) listTableToDelete.get(j);
						String sqlScript = (String) map.get("sqlScript");
						String tableNameChild = (String) map.get("tableName");	
						String filterDB = (String) map.get("filterDB");	
						String pkName_s = (String) map.get("pkName");	
						
						//myLogger.info(">>>>>>>>>>>>>> deleteCrudAuto DELETE STATEMENT : "+sqlScript);
						if(!tableNameChild.equals("TBLPPKSIMATI") || (tableNameChild.equals("TBLPPKSIMATI") && jumlahPermohonanSimatiPPK(ID_FAIL, db) <= 1))
						{
							backupScriptDelete(NO_FAIL,pkName_s,tableNameChild,sqlScript,skrinName, stmt, conn,db);
							//off jap delete
							myLogger.info(">>>>>>>>>>>>>>> SCRIPT DELETE : "+sqlScript);
							executeDeleteRT(sqlScript,stmt, methodName, db);
							
						}
					}
				}
				//testSqlProblem(db);
				conn.commit();
			}
			
			public void backupScriptDelete(String NO_FAIL,String pkName,String tableName,String sqlScript,String skrinName, Statement stmt,Connection conn,Db db) throws Exception
			{
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				//String backupScript = " INSERT ALL "; 
				String scriptSelect = sqlScript.replace("DELETE FROM", "SELECT * FROM");
				String setColumn = "(";
				//myLogger.info(tableName+" >>>>>>>>>>>>>> deleteCrudAuto SELECT STATEMENT : "+scriptSelect);
				List columnNameFromQuery = returnColumnNameFromQuery(" "+scriptSelect+" AND ROWNUM < 1 ", skrinName+"mapaudit", true, false, false, tableName + "cacheID", db);
				if (columnNameFromQuery.size() != 0) {
					String pkValue = "";
					ArrayList<String> clobColumnName = new ArrayList<String>();
					for (int i = 0; i < columnNameFromQuery.size(); i++) {
						Map map_column_name = (Map) columnNameFromQuery.get(i);
						String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
						String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");	
						//myLogger.info(">>>>>>>>>>>>>> deleteCrudAuto COLUMN_NAME : "+COLUMN_NAME);
						setColumn += COLUMN_NAME;
						if((i+1)!=columnNameFromQuery.size())
						{
							setColumn += ", ";
						}
					}
				}
				setColumn += ") ";
				//myLogger.info(">>>>>>>>>> "+setColumn);
				ResultSet rs = null;
				rs = executeQueryRT(stmt, scriptSelect ,methodName);
								
				List listRowDeleted = Collections.synchronizedList(new ArrayList());
				while (rs.next())
				{
					String scriptInsertBackup = " INSERT INTO "+tableName+" ";
					Map hashRowDeleted = Collections.synchronizedMap(new HashMap());
					
					//myLogger.info("MASUK result set >>>>>> "+rs.getString("ID_PERMOHONAN"));
					if (columnNameFromQuery.size() > 0) {
						
						scriptInsertBackup += setColumn;
						String setColumnValue = " VALUES (";
						for (int i = 0; i < columnNameFromQuery.size(); i++) {
							Map map_column_name = (Map) columnNameFromQuery.get(i);
							String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");	
							String VALUE = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME));
							//myLogger.info(">>>>>>>>>>>>>> "+COLUMN_NAME+" TYPE : "+COLUMN_TYPE+" deleteCrudAuto COLUMN_NAME_VALUE : "+VALUE);
							if(pkName.equals(COLUMN_NAME))
							{
							hashRowDeleted.put("PK_COLUMN",COLUMN_NAME);
							hashRowDeleted.put("PK_VALUE",VALUE);
							}
							if(rs.getString(COLUMN_NAME) == null)
							{
								setColumnValue += "NULL";		
							}
							else
							{
								if(COLUMN_TYPE.equals("DATE"))
								{
									//myLogger.info(" >>>>>>>>> DATE : "+VALUE.substring(0,19));
									setColumnValue += "TO_DATE('"+VALUE.substring(0,19)+"','yyyy-MM-dd HH24:mi:ss')";
								}
								else if(COLUMN_TYPE.contains("CHAR") || COLUMN_TYPE.equals("CLOB") )
								{
									setColumnValue += "'"+VALUE+"'";
								}
								else
								{
									setColumnValue += ""+VALUE+"";												
								}
							}			
							
							if((i+1)!=columnNameFromQuery.size())
							{
								setColumnValue += ", ";
							}
						}
						setColumnValue += ")";
						scriptInsertBackup += setColumnValue;
						
					}
					hashRowDeleted.put("SCRIPT_BC",scriptInsertBackup);
					listRowDeleted.add(hashRowDeleted);
				}
				rs.close();
				//scriptInsertBackup += " SELECT * FROM DUAL";
				//myLogger.info(" scriptInsertBackup ::::::::: "+scriptInsertBackup);
				
				if(listRowDeleted.size() > 0)
				{
					for (int i = 0; i < listRowDeleted.size(); i++) {
						Map map_column_BC = (Map) listRowDeleted.get(i);
						String PK_COLUMN = (String) map_column_BC.get("PK_COLUMN");
						String PK_VALUE = (String) map_column_BC.get("PK_VALUE");
						String SCRIPT_BC = (String) map_column_BC.get("SCRIPT_BC");
						logActivityRT(null,session,"DEL","NO. FAIL : "+NO_FAIL+", TABLE : "+tableName+", FILTER : "+PK_COLUMN+" = "+PK_VALUE,SCRIPT_BC,conn,db);
					}
				}
				
				
			}
			
			
			public String backupScriptUpdate(String tableName,String PKfield, String PKvalue, 
					String skrinName, Statement stmt,Db db) throws Exception
			{
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				
				List columnNameFromQuery = returnColumnNameFromQuery(" SELECT * FROM "+tableName+" WHERE ROWNUM < 1 ", skrinName+"mapaudit", true, false, false, tableName + "cacheID", db);
				
				ResultSet rs = null;
				rs = executeQueryRT(stmt, " SELECT * FROM "+tableName+" WHERE "+PKfield+"= "+PKvalue,methodName);
				String scriptUpdate = " UPDATE "+tableName+" SET ";
				while (rs.next())
				{
					if (columnNameFromQuery.size() != 0) {
						String pkValue = "";
						ArrayList<String> clobColumnName = new ArrayList<String>();
						for (int i = 0; i < columnNameFromQuery.size(); i++) {
							Map map_column_name = (Map) columnNameFromQuery.get(i);
							String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");	
							String VALUE = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME));
							
							//myLogger.info(">>>>>>>>>>>>>> deleteCrudAuto COLUMN_NAME : "+COLUMN_NAME);
							scriptUpdate += " "+COLUMN_NAME + " = " ;
							
							if(rs.getString(COLUMN_NAME) == null)
							{
								scriptUpdate += "NULL";		
							}
							else
							{
								if(COLUMN_TYPE.equals("DATE"))
								{
									scriptUpdate += "TO_DATE('"+VALUE.substring(0,19)+"','yyyy-MM-dd HH24:mi:ss')";
								}
								else if(COLUMN_TYPE.contains("CHAR") || COLUMN_TYPE.equals("CLOB") )
								{
									scriptUpdate += "'"+VALUE+"'";
								}
								else
								{
									scriptUpdate += ""+VALUE+"";												
								}
							}	
							
							if((i+1)!=columnNameFromQuery.size())
							{
								scriptUpdate += ", ";
							}
						}
					}
				}
				rs.close();
				scriptUpdate += " WHERE "+PKfield+"= "+PKvalue;
				
				
				/*
				
				//String backupScript = " INSERT ALL "; 
				String scriptSelect = sqlScript.replace("DELETE FROM", "SELECT * FROM");
				String setColumn = "(";
				myLogger.info(tableName+" >>>>>>>>>>>>>> deleteCrudAuto SELECT STATEMENT : "+scriptSelect);
				List columnNameFromQuery = returnColumnNameFromQuery(" "+scriptSelect+" AND ROWNUM < 1 ", skrinName+"map", true, false, false, tableName + "cacheID", db);
				if (columnNameFromQuery.size() != 0) {
					String pkValue = "";
					ArrayList<String> clobColumnName = new ArrayList<String>();
					for (int i = 0; i < columnNameFromQuery.size(); i++) {
						Map map_column_name = (Map) columnNameFromQuery.get(i);
						String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
						String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");	
						myLogger.info(">>>>>>>>>>>>>> deleteCrudAuto COLUMN_NAME : "+COLUMN_NAME);
						setColumn += COLUMN_NAME;
						if((i+1)!=columnNameFromQuery.size())
						{
							setColumn += ", ";
						}
					}
				}
				setColumn += ") ";
				myLogger.info(">>>>>>>>>> "+setColumn);
				ResultSet rs = null;
				rs = executeQueryRT(stmt, scriptSelect ,methodName);
				
				String scriptInsertBackup = " INSERT ALL ";
				
				while (rs.next())
				{
					//myLogger.info("MASUK result set >>>>>> "+rs.getString("ID_PERMOHONAN"));
					if (columnNameFromQuery.size() > 0) {
						scriptInsertBackup += " INTO "+tableName+" ";
						scriptInsertBackup += setColumn;
						String setColumnValue = " VALUES (";
						for (int i = 0; i < columnNameFromQuery.size(); i++) {
							Map map_column_name = (Map) columnNameFromQuery.get(i);
							String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
							String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");	
							String VALUE = (rs.getString(COLUMN_NAME) == null ? "" : rs.getString(COLUMN_NAME));
							myLogger.info(">>>>>>>>>>>>>> "+COLUMN_NAME+" TYPE : "+COLUMN_TYPE+" deleteCrudAuto COLUMN_NAME_VALUE : "+VALUE);

							if(rs.getString(COLUMN_NAME) == null)
							{
								setColumnValue += "NULL";		
							}
							else
							{
								if(COLUMN_TYPE.equals("DATE"))
								{
									setColumnValue += "TO_DATE('"+VALUE.substring(0,19)+"','yyyy-MM-dd HH24:mi:ss')";
								}
								else if(COLUMN_TYPE.contains("CHAR") || COLUMN_TYPE.equals("CLOB") )
								{
									setColumnValue += "'"+VALUE+"'";
								}
								else
								{
									setColumnValue += ""+VALUE+"";												
								}
							}			
							
							if((i+1)!=columnNameFromQuery.size())
							{
								setColumnValue += ", ";
							}
						}
						setColumnValue += ")";
						scriptInsertBackup += setColumnValue;
					}
				}
				scriptInsertBackup += " SELECT * FROM DUAL";
				myLogger.info(" scriptInsertBackup ::::::::: "+scriptInsertBackup);
				*/
				return scriptUpdate;
			}
			
			
			public int jumlahPermohonanSimatiPPK(String ID_FAIL, Db db) throws SQLException
			{
					Statement stmt = db.getStatement();
			        String sql = "SELECT COUNT(*) AS TOTAL_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_SIMATI IN ( "+
					"	SELECT SM.ID_SIMATI FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P,TBLPFDFAIL F "+
					"	WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"+ID_FAIL+"' ) ";		
					//myLogger.info("SQL MAIN FAIL DETAIL :" + sql);
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					ResultSet rs = executeQueryRT(stmt, sql ,methodName);
					Integer total_permohonansimati = 0;
					while (rs.next()) {
						total_permohonansimati = rs.getInt("TOTAL_PERMOHONANSIMATI");
					}
					rs.close();
					return total_permohonansimati;
			}
			
			public List listTableToDelete(String skrinName,String tableName, String pkName, List listSetupSkrin) throws Exception {
				String filter = " "+pkName +" = "+getParam(pkName);
				List listToDelete = Collections.synchronizedList(new ArrayList());
				listToDelete.add(mapToDelete(skrinName,tableName, pkName, filter));		
				listTableToDelete(skrinName,tableName,listToDelete, listSetupSkrin);		
				Collections.reverse(listToDelete);
				myLogger.info(">>>>>>>>>>>>> listToDelete "+listToDelete);
				return listToDelete;
			}
			
			
			public Map mapToDelete(String skrinName,String tableName, String pkName,String filter)
			{
				Map hSetupSkrin = Collections.synchronizedMap(new HashMap());
				hSetupSkrin.put("skrinName",skrinName);
				hSetupSkrin.put("tableName",tableName);
				hSetupSkrin.put("pkName",pkName);
				filter = filter.replace("&", "");
				hSetupSkrin.put("filterDB",filter);		
				String sqlScript = "DELETE FROM "+tableName+" WHERE "+filter;		
				hSetupSkrin.put("sqlScript",sqlScript);
				return hSetupSkrin;		
			}
			
			
			
			public List listTableToDelete(String skrinNameRef,String tableRef, List listToDelete, List listSetupSkrin) throws Exception {	 		
				//nak muntah mikir logic dynamic delete tree
				List listSetupSkrin_1stlayer = listSetupSkrin;
				if (listSetupSkrin_1stlayer.size() > 0 )
				{
					for (int j = 0; j < listSetupSkrin_1stlayer.size(); j++) {
						Map mapSetupSkrin = (Map) listSetupSkrin_1stlayer.get(j);
						String m_skrinName = (String) mapSetupSkrin.get("skrinName");
						String m_seqName = (String) mapSetupSkrin.get("seqName");
						String m_tableName = (String) mapSetupSkrin.get("tableName");
						String m_filterScript = (String) mapSetupSkrin.get("filterScript");
						String m_tajuk = (String) mapSetupSkrin.get("tajuk");
						String m_PK_FIELD = (String) mapSetupSkrin.get("PK_FIELD");
						String m_ParentTable = (String) mapSetupSkrin.get("ParentTable");
						String m_ParentSkrin = (String) mapSetupSkrin.get("ParentSkrin");
						
						//if(tableRef.equals(m_ParentTable))
						if(skrinNameRef.equals(m_ParentSkrin))
						{
							
							if (listToDelete.size() > 0 )
							{
								for (int i = 0; i < listToDelete.size(); i++) {
									Map mapToDelete = (Map) listToDelete.get(i);
									String d_skrinName = (String) mapToDelete.get("skrinName");
									String d_tableName = (String) mapToDelete.get("tableName");
									String d_pkName = (String) mapToDelete.get("pkName");
									String d_filter = (String) mapToDelete.get("filterDB");
									
									//if(m_ParentTable.equals(d_tableName))
									if(m_ParentSkrin.equals(d_skrinName))
									{
										String newFilerter = "";
										newFilerter = m_filterScript.replace("{DUMMY_FK}"," ( SELECT " +d_pkName+ " FROM "+d_tableName+" WHERE "+d_filter+")") ;
										//myLogger.info(">>>> newFilerter "+newFilerter);
										//myLogger.info(">>>>>>>>>>>>> m_tableName "+m_tableName);
										listToDelete.add(mapToDelete(m_skrinName,m_tableName, m_PK_FIELD, newFilerter));
									}
									
								}
							}					
							listTableToDelete(m_skrinName,m_tableName,listToDelete,listSetupSkrin);
						}
					}
				}		 		 
				return listToDelete;
			 }
			
			
			public void logActivityRT(VTemplate module,HttpSession session,
					String jenis_aktiviti,String keterangan,String backupScript, Connection conn,Db db) 
			throws Exception {
				logActivityRT("","","",module,session,jenis_aktiviti,keterangan,backupScript,conn,db);
			}
			
			public void logActivityRT(String id_suburusan,String id_status,String id_seksyen,VTemplate module,HttpSession session,
					String jenis_aktiviti,String keterangan,String backupScript,Connection conn,Db db) 
					throws Exception {
				logActivityRT(id_suburusan,id_status,id_seksyen,module,session,
						jenis_aktiviti,keterangan,backupScript,"", conn, db);
			}
			
			public void logActivityFrmPopup(String id_status,String id_seksyen,HttpSession session,
					String jenis_aktiviti,String keterangan,String namaModul,Db db) 
					throws Exception {
				logActivityRT("",id_status,id_seksyen,null,session,
						jenis_aktiviti,keterangan,"",namaModul, null, db);
			}
			
			public void logActivityRT(String id_suburusan,String id_status,String id_seksyen,VTemplate module,HttpSession session,
					String jenis_aktiviti,String keterangan,String backupScript,String namaModul, Connection conn,Db db) 
					throws Exception {
					String module_session = session.getAttribute("_portal_module").toString();
					String module_name = module_session;
					if (module != null) { 
						module_name = module.getClass().getName();
					}
					
					if(!namaModul.equals(""))
					{
						module_name = namaModul; 
					}
					
					String user_id = "1";
					String ip_address = "-";
					if (session != null) {
						user_id = (String)session.getAttribute("_ekptg_user_id");
						ip_address = (String)session.getAttribute("ip_address");
					}
					 
					String sql = "";					
						
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					String id_seq = getNextID(db, "TBLAUDITTRAIL_SEQ",stmt)+"";
					r.clear();
					r.add("ID_AUDITTRAIL", id_seq);
					r.add("ID_SUBURUSAN", id_suburusan);
					r.add("ID_STATUS", id_status);
					r.add("ID_SEKSYEN", id_seksyen);
					r.add("MODULE_NAME", module_name);
					r.add("JENIS_AKTIVITI", jenis_aktiviti);
					r.add("TARIKH_AKTIVITI", r.unquote("sysdate"));
					r.add("KETERANGAN", keterangan);
					r.add("IP_ADDRESS", ip_address);
					r.add("ID_MASUK", user_id);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					r.add("ID_KEMASKINI", user_id);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLAUDITTRAIL",db);	
					myLogger.info("INSERT AUDITTRAIL : "+sql);
					stmt.executeUpdate(sql);
					
					
					
		        	
		        	if(!backupScript.equals(""))
		        	{
		        		
		        		myLogger.info("UPDATE >>>>>>> "+"UPDATE TBLAUDITTRAIL " +
			        			" SET SCRIPT_BACKUP = TRIM(REGEXP_REPLACE("+backupScript+", '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) " +
			        			" WHERE ID_AUDITTRAIL = "+id_seq+" ");
		        		
		        		//Connection con1 = db.getConnection();
			        	//con.setAutoCommit(false);
		        		myLogger.info("ID SEQ AUDIT TRAIL : "+id_seq);
			        	PreparedStatement ps = conn.prepareStatement("UPDATE TBLAUDITTRAIL " +
			        			//" SET SCRIPT_BACKUP = TRIM(REGEXP_REPLACE(?, '([[:space:]][[:space:]]+)|([[:cntrl:]]+)', ' ')) " +
			        			" SET SCRIPT_BACKUP = ? " +
			        			" WHERE ID_AUDITTRAIL = ? ");
			        	ps.setString(1, backupScript);
			        	ps.setString(2, id_seq);      	
			        	ps.executeUpdate(); 
			        	//con1.commit();
		        	}
						
					
				
				}
			
			//
			public long getNextID(Db db, String seqName, Statement stmt) throws Exception {
				String statecode = StateLookup.getInstance().getTitle("StateCode");
				String sql = "select " + statecode + " || to_char(sysdate,'YY') || " + seqName + ".NEXTVAL  FROM DUAL ";
				//Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				long id = 0;
				while (rs.next()) {
					id = rs.getLong(1);
				}
				rs.close();
				
				return id;
			}
			
}
