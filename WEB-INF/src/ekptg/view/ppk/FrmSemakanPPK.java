package ekptg.view.ppk;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.model.RazTemplete;
import ekptg.model.ppk.FrmSemakanPPKModel;



public class FrmSemakanPPK extends AjaxBasedModule {
	public Logger myLogger = Logger.getLogger(FrmSemakanPPK.class);
	public FrmSemakanPPKModel MP = new FrmSemakanPPKModel();
	public RazTemplete RT = new RazTemplete();
	String skrin_name = "index.jsp";
	String skrinPath = "app/ppk/FrmSemakanPPK/";	
	String modul = "";	
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		RT.setEnvironmentRT(engine, context, request, response, session, modul);
		MP.setFrmSemakanPPKModel(engine, context, request, response, session, modul);		
		RT.startTime();		
		
		String command = RT.getLastParam("command");
		String mode = getParam("mode");
		String actionajax = getParam("actionajax");
		String skrinName = getParam("skrinName");
		String div = getParam("div");
		String errorMesej = "";
		
		myLogger.info("command : "+command+" actionajax : "+actionajax);
		myLogger.info("skrinName : "+skrinName);
		myLogger.info("div : "+div);
		
		RT.defaultContextPutRT();
		MP.defaultContextPut();
				
		Db dbMain = null;
		Connection conn = null;
		try {
			dbMain = new Db();			
			conn = dbMain.getConnection();
			
			//"viewMaklumat" adalah standard command untuk bukak maklumat atau buka skrin
			if(command.equals("viewMaklumatMain"))
			{
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				String html = RT.RThtmlViewMapAuto( 
						command,
						"headerFail",
						MP.queryHeaderFail(dbMain,skrinName,ID_SEKSYEN), //sql
						div,
						"view",
						false, //non editable, view only
						"",
						"",
						"",
						"",
						ID_SEKSYEN+skrinName+USER_ID_SYSTEM,
						dbMain);
				
				//setting tapak crud untuk table under ID_FAIL
				String ID_FAIL = getParam("ID_FAIL");
				
				html += RT.settingCRUDTapakAuto("fail","TBLPFDFAIL_SEQ","TBLPFDFAIL",  "&ID_FAIL = "+ID_FAIL+"","TABLE : TBLPFDFAIL","ID_FAIL",dbMain);
				
				context.put("html", html);
				
				skrin_name = skrinPath+"view.jsp";	
			}
			else if(command.equals("openCrudSkrin") || command.equals("viewCrud") || command.equals("editCrud") || command.equals("addCrud") || command.equals("insertCrud") 
					|| command.equals("saveCrud") || command.equals("deleteCrud") || command.equals("refreshListCrud"))
			{
				String tableName = getParam("tableName");
				String seqName = getParam("seqName");
				String filterDB = getParam("filterDB");
				String pkField = getParam("pkField");
				String param = "&"+pkField+"={"+pkField+"}&pkField="+pkField+"&filterDB="+filterDB+"&tableName="+tableName+"&seqName="+seqName;
				String NO_FAIL = getParam("headerFailNO_FAIL");
				String ID_FAIL = getParam("headerFailID_FAIL");
				String ID_SEKSYEN = getParam("headerFailID_SEKSYEN");
				String html = "";
				
				if(command.equals("deleteCrud"))
				{
					myLogger.info(" to delete >>>>>>>>>>>>>");
					
					RT.deleteCrudAuto(skrinName,tableName, pkField, MP.listSetupSkrin(ID_SEKSYEN), dbMain, conn, NO_FAIL, ID_FAIL);						
				}
				
				
				html = RT.RTCrudSkrinAuto(command,skrinName,seqName, // skrinName
						MP.queryByTABLElist(dbMain,skrinName,skrinName+"list",tableName, filterDB, ID_SEKSYEN+skrinName+USER_ID_SYSTEM), //method query
						MP.listColumnForSenaraiCRUD(div,skrinName,tableName,
								pkField,filterDB,param), //column to display
						MP.queryByTABLEMap(dbMain,skrinName,tableName, pkField, getParam(pkField), ID_SEKSYEN+skrinName+USER_ID_SYSTEM),
						div,"","","", 1000,
						tableName,
						filterDB,
						pkField,
						"divView"+skrinName+"{"+pkField+"}",
						param,
						dbMain,conn, NO_FAIL, ID_FAIL,ID_SEKSYEN+skrinName+USER_ID_SYSTEM);				
				
				
				if(command.equals("saveCrud") || command.equals("viewCrud"))
				{
					html += MP.htmlSetupSkrin(command, tableName, skrinName, dbMain);
				}
				
				context.put("html", html);
				
				if(command.equals("openCrudSkrin") || command.equals("deleteCrud") || command.equals("refreshListCrud") ||  command.equals("insertCrud"))
				{
					skrin_name = skrinPath+"senarai.jsp";
				}
				else if(command.equals("editCrud") || command.equals("addCrud") || command.equals("viewCrud") || command.equals("saveCrud"))
				{
					skrin_name = skrinPath+"view.jsp";
				}
			}
			
			//skrin depan -- carian
			else if(command.equals("showSenaraiUtama"))
			{	
				skrin_name = skrinPath+"senaraiUtama.jsp";	
			}
			else if(command.equals("carianSenaraiUtama"))
			{		
				//guna fully templete
				String html = RT.RThtmlListRekod( 
						command, // related command, get dlu..
						skrinName, // skrinName
						MP.querySenaraiFail(dbMain,skrinName), //method query
						MP.listColumnForSenaraiFail(skrinName), //column to display
						skrinName+USER_ID_SYSTEM,
						div,
						"TARIKH_MASUK,ID_FAIL", // senarai nama column untuk  kita sort kan
						"DATE,NUMBER", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
						"ASC,ASC", // jenis sorting mengikut column
						"'yyyy-MM-dd HH:mm:ss.S','-'",
						0, // set max row dispay, advice terperlu banyak2.. xde sapa nak scroll bayak2.. max letak 1K
						"",
						"",
						conn,
						dbMain);				
				context.put("html", html);
				skrin_name = skrinPath+"senaraiUtama.jsp";					
			}
			else if(command.equals("executeSQL"))
			{
				String stmtSQLeditor = getParam("stmtSQLeditor");
				String inputSQLeditor = getParam("inputSQLeditor");
				
				String html = "";
				
				if(stmtSQLeditor.equals("SELECT"))
				{
					html += RT.RThtmlListRekodforEditor( 
							command, // related command, get dlu..
							"sqlEditor",
							inputSQLeditor, 						
							div,
							100000,
							"sqlEditor"+USER_ID_SYSTEM,conn,
							dbMain);
					
				}
				else if(stmtSQLeditor.equals("INSERT") || stmtSQLeditor.equals("UPDATE"))
				{
					conn.setAutoCommit(false);
					RT.editorUpdateEditor(inputSQLeditor,dbMain,conn);
					String type = "UPD";
					if(stmtSQLeditor.equals("INSERT"))
					{
						type = "INS";
					}					
					AuditTrail.logActivity(null,session,type,"EDITOR : "+inputSQLeditor,dbMain);
					conn.commit();
					html += stmtSQLeditor+" berjaya. SQL : "+inputSQLeditor;
				}
				else if(stmtSQLeditor.equals("DELETE"))
				{
					conn.setAutoCommit(false);
					RT.editorExecuteEditor(inputSQLeditor,dbMain,conn);
					AuditTrail.logActivity(null,session,"DEL","EDITOR : "+inputSQLeditor,dbMain);
					conn.commit();
					html += stmtSQLeditor+" berjaya. SQL : "+inputSQLeditor;
				}
				else if(stmtSQLeditor.equals("RUN"))
				{
					conn.setAutoCommit(false);
					RT.editorRunEditor(inputSQLeditor,dbMain,conn);
					AuditTrail.logActivity(null,session,"ALT","EDITOR : "+inputSQLeditor,dbMain);
					conn.commit();
					html += "RUNSCRIPT berjaya. SCRIPT : "+inputSQLeditor;
				}

				
				context.put("html", html);
				
				skrin_name = skrinPath+"resultEditor.jsp";	
			}
			else
			{
				String html = MP.HTMLSkrinCarian(dbMain, "carianUtama", modul); 
				context.put("html", html);
				skrin_name = skrinPath+"index.jsp";
			}						
			
		}
		
		catch (SQLException se) {
			try {
				conn.rollback();
				myLogger.info("ROLLBACK CALLED");
			} catch (SQLException se2) {
				errorMesej += "RALAT ROLLBACK :" + se2.getMessage();
			}
			se.printStackTrace();
			errorMesej += "RALAT SQL :" + se.getMessage();
		} finally {
			
			if (conn != null)
				conn.close();
			if (dbMain != null)
				dbMain.close();
		}
		
		if(!errorMesej.equals(""))
		{
			skrin_name = RT.RTerrorControl(errorMesej);	
		}
		
		
		RT.RTloadingTimeControl(skrin_name); 
		
		this.context.put("command",command);//return balik current command
		this.context.put("actionajax",actionajax);//return balik current actionajax	
		this.context.put("skrinName",skrinName);//return balik current actionajax			
				
		myLogger.info("::: SKRIN NAME : "+skrin_name);
		return skrin_name;
	}
	
}
