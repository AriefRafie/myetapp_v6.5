package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.model.RazTemplete;
import ekptg.model.ppt.KPIProjekSelesaiModel;

public class KPIProjekSelesai extends AjaxBasedModule {
	public Logger myLogger = Logger.getLogger(KPIProjekSelesai.class);
	public KPIProjekSelesaiModel MP = new KPIProjekSelesaiModel();
	public RazTemplete RT = new RazTemplete();
	String skrin_name = "index.jsp";
	String skrinPath = "app/ppt/KPISelesai/";	
	String modul = "";	
	
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		RT.setEnvironmentRT(engine, context, request, response, session, modul);
		MP.setKPIProjekSelesaiModel(engine, context, request, response, session, modul);		
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
			
			String html = "";			
			if(command.equals("showProjekBased"))
			{
				
				//html = MP.htmlProjekBased(MP.queryProjekBased(dbMain,skrinName),true,false,skrinName+USER_ID_SYSTEM,skrinName,div,false, dbMain);			
				html = MP.htmlProjekBased( 
						command,
						skrinName,
						MP.queryProjekBased(dbMain,skrinName), //method query
						MP.listColumnForProjek(skrinName), //column to display
						true, // list yg ada cache
						false, // keperluan mereset cache
						skrinName+USER_ID_SYSTEM, // unik cache ID
						true,
						div,
						"", // senarai nama column untuk  kita sort kan
						"", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
						"", // jenis sorting mengikut column
						"",
						0,
						"",
						"",
						dbMain);
				context.put("html", html);	
				skrin_name = skrinPath+"projekBased.jsp";				
			}
			else if(command.equals("showHakmilikBased"))
			{	
				String CARIAN_JENIS_KPI = getParam("carianUtamaCARIAN_JENIS_KPI");
				String ID_FAIL = getParam("ID_FAIL");
				String columnToSort = "";
				String columnTypeToSort = "";
				String typeToSort = "";
				String dateFormat = "";
				
				/*
				if(CARIAN_JENIS_KPI.equals("1"))
				{
					columnToSort = "ADA_H,TARIKH_BORANGH,NO_SUBJAKET";
					columnTypeToSort = "NUMBER,DATE,NUMBER";
				    typeToSort = "DESC,ASC,ASC";
					dateFormat = "'-','dd/MM/yyyy','-'";
				}
				else if(CARIAN_JENIS_KPI.equals("2"))
				{
					columnToSort = "ADA_K,TARIKH_BORANGK,NO_SUBJAKET";
					columnTypeToSort = "NUMBER,DATE,NUMBER";
				    typeToSort = "DESC,ASC,ASC";
					dateFormat = "'-','dd/MM/yyyy','-'";
				}
				else if(CARIAN_JENIS_KPI.equals("3"))
				{
					columnToSort = "ADA_ENDOSK,TARIKH_ENDOSK,NO_SUBJAKET";
					columnTypeToSort = "NUMBER,DATE,NUMBER";
				    typeToSort = "DESC,ASC,ASC";
					dateFormat = "'-','dd/MM/yyyy','-'";
				}
				*/
				html = MP.htmlHakmilikBased( 
						command,
						skrinName,
						MP.queryHakmilikBased(dbMain,skrinName,ID_FAIL), //method query
						MP.listColumnForHakmilik(skrinName), //column to display
						true, // list yg ada cache
						false, // keperluan mereset cache
						skrinName+USER_ID_SYSTEM, // unik cache ID
						true,
						div,
						columnToSort, // senarai nama column untuk  kita sort kan
						columnTypeToSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
						typeToSort, // jenis sorting mengikut column
						dateFormat,
						0,
						"",
						"&ID_FAIL="+ID_FAIL,
						ID_FAIL,
						dbMain);
				
				context.put("html", html);
				skrin_name = skrinPath+"hakmilikBased.jsp";		
			}			
			else
			{
				html = MP.HTMLSkrinCarian(dbMain, "carianUtama"); 
				context.put("html", html);
				skrin_name = skrinPath+"index.jsp";
			}	
			
		}
		catch (SQLException se) {
			se.printStackTrace();
			errorMesej += "RALAT SQL :" + se.getMessage();
		} 		
		finally {
			
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
	