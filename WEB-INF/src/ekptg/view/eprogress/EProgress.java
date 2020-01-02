package ekptg.view.eprogress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.engine.GetAttachment;
import ekptg.helpers.DB;
import ekptg.view.admin.Push;

public class EProgress extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(EProgress.class);
	List listMainModul = null;
	List listSubModul = null;
	List listPenambahbaikkan = null;
	List listPIC = null;
	List listPICStatistik = null;
	Hashtable paparItemPenambahbaikkan = null;
	Hashtable paparItemSubmodul = null;
	Hashtable paparItemMainmodul = null;
	List listPaparItemPenambahbaikanStats = null;
	List listProjek = null;
	String current_role = null;	
	
	//open razman add new feature : attachment in bytes
	GetAttachment report = new GetAttachment();
	
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public String doTemplate2() throws Exception{
		
		Push push = new Push();
		
		//push.genMsgPush("IDFAI", "daftar");
		
		this.context.put("listMainModul", "");
		this.context.put("listSubModul", "");
		this.context.put("listPenambahbaikkan", "");
		this.context.put("getItemPenambahbaikkan", "");
		this.context.put("getItemSubmodul", "");
		this.context.put("getItemMainmodul", "");
		this.context.put("listPIC", "");
		this.context.put("listPICStatistik", "");
		this.context.put("openSectionPenambahbaikan", "close");
		this.context.put("openSectionSubmodul", "close");
		this.context.put("totalPenambahbaikan","");
		this.context.put("listPaparItemPenambahbaikanStats","");
		this.context.put("listProjek","");	
		this.context.put("ID_PROJEK","");	
		
		
		HttpSession session = this.request.getSession();
		current_role = (String)session.getAttribute("myrole");
		this.context.put("current_role",current_role);	
		String skrin_mame = "app/EProgress/index.jsp";
		String command = getParam("command");
		myLogger.info("*** command EProgress : "+command);		
		
		//MAIN MODUL
			if(command.equals("show_by_modul") ||  command.equals("add_item_mainmodul")  || command.equals("delete_mainmodul"))
			{
				String ID_PROJEK = getParam("ID_PROJEK");
				myLogger.info(" ******** ID_PROJEK : "+ID_PROJEK);
				if(command.equals("delete_mainmodul"))
				{
					String ID_MAINMODUL = getParam("ID_MAINMODUL");
					deleteMainmodul(ID_MAINMODUL);
				}
				else if(command.equals("add_item_mainmodul"))
				{
					String MAINMODUL = getParam("MAINMODUL_");
					String TURUTAN = getParam("TURUTAN_");
					Hashtable h = new Hashtable();			
					h.put("TURUTAN", TURUTAN);
					h.put("MAINMODUL", MAINMODUL);
					h.put("ID_PROJEK", ID_PROJEK);
					insertItemMainmodul(h);
				}
				
				listMainModul = listMainModul(session,ID_PROJEK);
				this.context.put("listMainModul", listMainModul);
				this.context.put("ID_PROJEK", ID_PROJEK);
				skrin_mame = "app/EProgress/listMainModul.jsp";
			}
			else if(command.equals("bataladd_item_mainmodul"))
			{
				skrin_mame = "app/EProgress/blank_addmainmodul.jsp";
			}
			else if(command.equals("edit_mainmodul"))
			{		
				String ID_PROJEK = getParam("ID_PROJEK");
				String ID_MAINMODUL = getParam("ID_MAINMODUL");				
				paparItemMainmodul = getItemMainmodul(session, ID_MAINMODUL,ID_PROJEK);
				this.context.put("getItemMainmodul",paparItemMainmodul);
				this.context.put("ID_PROJEK", ID_PROJEK);
				skrin_mame = "app/EProgress/editMainmodul.jsp";
			}
			else if(command.equals("bataledit_item_mainmodul") || command.equals("save_item_mainmodul"))
			{
				String ID_MAINMODUL = getParam("ID_MAINMODUL");	
				String ID_PROJEK = getParam("ID_PROJEK");
				if(command.equals("save_item_mainmodul"))
				{
					String MAINMODUL = getParam("MAINMODUL_"+ID_MAINMODUL);
					String TURUTAN = getParam("TURUTAN_"+ID_MAINMODUL);
					Hashtable h = new Hashtable();			
					h.put("ID_MAINMODUL", ID_MAINMODUL);
					h.put("TURUTAN", TURUTAN);
					h.put("MAINMODUL", MAINMODUL);
					updateItemMainmodul(h);
				}				 
				paparItemMainmodul = getItemMainmodul(session, ID_MAINMODUL, ID_PROJEK);
				this.context.put("getItemMainmodul",paparItemMainmodul);
				this.context.put("ID_PROJEK", ID_PROJEK);
				skrin_mame = "app/EProgress/viewMainmodul.jsp";
			}
			else if(command.equals("add_mainmodul"))
			{				
				skrin_mame = "app/EProgress/addMainmodul.jsp";
			}
			
			
			
			
			
			
			//SUB MODUL
			else if(command.equals("show_by_submodul") || command.equals("bataladd_item_submodul") ||  command.equals("add_item_submodul") || command.equals("delete_submodul") || command.equals("save_item_submodul"))
			{
				String ID_MAINMODUL = getParam("ID_MAINMODUL");
				this.context.put("ID_MAINMODUL",ID_MAINMODUL);
				String openSectionSubmodul = getParam("openSectionSubmodul_"+ID_MAINMODUL);			
				
				if(command.equals("save_item_submodul"))
				{
					String ID_SUBMODUL = getParam("ID_SUBMODUL");				
					String SUBMODUL = getParam("SUBMODUL_"+ID_SUBMODUL);
					String TURUTAN = getParam("TURUTAN_"+ID_SUBMODUL);
					Hashtable h = new Hashtable();			
					h.put("ID_SUBMODUL", ID_SUBMODUL);
					h.put("TURUTAN", TURUTAN);
					h.put("SUBMODUL", SUBMODUL);
					updateItemSubmodul(h);
					openSectionSubmodul = "close";
				}
				else if(command.equals("delete_submodul"))
				{
					String ID_SUBMODUL = getParam("ID_SUBMODUL");
					deleteSubmodul(ID_SUBMODUL);
					openSectionSubmodul = "close";
				}
				else if(command.equals("add_item_submodul"))
				{
					String SUBMODUL = getParam("SUBMODUL_"+ID_MAINMODUL);
					String TURUTAN = getParam("TURUTAN_"+ID_MAINMODUL);
					Hashtable h = new Hashtable();			
					h.put("ID_MAINMODUL", ID_MAINMODUL);
					h.put("TURUTAN", TURUTAN);
					h.put("SUBMODUL", SUBMODUL);
					insertItemSubmodul(h);
					openSectionSubmodul = "close";
				}
				else if(command.equals("bataladd_item_submodul"))
				{
					openSectionSubmodul = "close";
				}
				
				
					
				if(openSectionSubmodul.equals("close"))
				{
					openSectionSubmodul = "open";						
					listSubModul = listSubModul(session,ID_MAINMODUL);
					myLogger.info("*** check listSubModul : "+listSubModul);
					this.context.put("listSubModul",listSubModul);					
					skrin_mame = "app/EProgress/listSubModul.jsp";
				}
				else if(openSectionSubmodul.equals("open"))
				{
					openSectionSubmodul = "close";						
					this.context.put("listSubModul","");
					skrin_mame = "app/EProgress/blank_listsubmodul.jsp";
				}				
				this.context.put("openSectionSubmodul",openSectionSubmodul);				
			}

			else if(command.equals("edit_submodul"))
			{				
				String ID_SUBMODUL = getParam("ID_SUBMODUL");				
				paparItemSubmodul = getItemSubmodul(session, ID_SUBMODUL);
				this.context.put("getItemSubmodul",paparItemSubmodul);
				skrin_mame = "app/EProgress/editSubmodul.jsp";
			}
			
			else if(command.equals("bataledit_item_submodul"))
			{
				String ID_SUBMODUL = getParam("ID_SUBMODUL");				
				paparItemSubmodul = getItemSubmodul(session, ID_SUBMODUL);
				this.context.put("getItemSubmodul",paparItemSubmodul);
				skrin_mame = "app/EProgress/viewSubmodul.jsp";
			}
			else if(command.equals("add_submodul"))
			{				
				String ID_MAINMODUL = getParam("ID_MAINMODUL");
				this.context.put("ID_MAINMODUL",ID_MAINMODUL);
				skrin_mame = "app/EProgress/addSubmodul.jsp";
			}
			
			
			
			
			
			
			
			//PENAMBAHBAIKKAN
			else if(command.equals("bataledit_item_penambahbaikkan"))
			{
				String BIL_TEMP = getParam("BIL_TEMP");
				this.context.put("BIL_TEMP",BIL_TEMP);
				String id_penambahbaikan = getParam("ID_PENAMBAHBAIKAN");	
				String fromStatististik = getParam("fromStatististik");
				this.context.put("fromStatististik",fromStatististik);
				
				paparItemPenambahbaikkan = getItemPenambahbaikan(session, id_penambahbaikan);
				this.context.put("getItemPenambahbaikkan",paparItemPenambahbaikkan);
				listPIC = listPIC(session);
				this.context.put("listPIC",listPIC);
				skrin_mame = "app/EProgress/viewPenambahbaikkan.jsp";
			}
			else if(command.equals("edit_penambahbaikkan"))
			{
				
				String fromStatististik = getParam("fromStatististik");
				String id_penambahbaikan = getParam("ID_PENAMBAHBAIKAN");				
				paparItemPenambahbaikkan = getItemPenambahbaikan(session, id_penambahbaikan);
				this.context.put("getItemPenambahbaikkan",paparItemPenambahbaikkan);
				listPIC = listPIC(session);
				this.context.put("fromStatististik",fromStatististik);
				this.context.put("listPIC",listPIC);
				String BIL_TEMP = getParam("BIL_TEMP");
				this.context.put("BIL_TEMP",BIL_TEMP);
				skrin_mame = "app/EProgress/editPenambahbaikkan.jsp";
			}
			else if(command.equals("add_penambahbaikkan"))
			{				
				String ID_SUBMODUL = getParam("ID_SUBMODUL");
				this.context.put("ID_SUBMODUL",ID_SUBMODUL);
				
				this.context.put("totalPenambahbaikan",countPenambahbaikanBySubmodul(session, ID_SUBMODUL));				
				
				listPIC = listPIC(session);
				this.context.put("listPIC",listPIC);
				String BIL_TEMP = getParam("BIL_TEMP");
				this.context.put("BIL_TEMP",BIL_TEMP);
				skrin_mame = "app/EProgress/addPenambahbaikkan.jsp";
			}
			else if(command.equals("save_item_penambahbaikkan"))
			{
				String BIL_TEMP = getParam("BIL_TEMP");
				this.context.put("BIL_TEMP",BIL_TEMP);
				String id_penambahbaikan = getParam("ID_PENAMBAHBAIKAN");
				String fromStatististik = getParam("fromStatististik");
				
				String TARIKH_MULA = getParam("TARIKH_MULA"+"_"+id_penambahbaikan);
				String TARIKH_TARGET_SIAP = getParam("TARIKH_TARGET_SIAP"+"_"+id_penambahbaikan);
				String FS_LOCAL = getParam("FS_LOCAL"+"_"+id_penambahbaikan);
				String FS_STG = getParam("FS_STG"+"_"+id_penambahbaikan);
				String FS_DEV = getParam("FS_DEV"+"_"+id_penambahbaikan);
				String CATATAN = getParam("CATATAN"+"_"+id_penambahbaikan);
				String F_KIV = getParam("F_KIV"+"_"+id_penambahbaikan);
				
				String ID_PIC = getParam("ID_PIC_"+id_penambahbaikan);	
				String PENAMBAHBAIKAN = getParam("PENAMBAHBAIKAN_"+id_penambahbaikan);
				Hashtable h = new Hashtable();			
				h.put("ID_PENAMBAHBAIKAN", id_penambahbaikan);
				h.put("ID_PIC", ID_PIC);
				h.put("PENAMBAHBAIKAN", PENAMBAHBAIKAN);
				
				h.put("TARIKH_MULA", TARIKH_MULA);
				h.put("TARIKH_TARGET_SIAP", TARIKH_TARGET_SIAP);
				h.put("FS_LOCAL", FS_LOCAL);
				h.put("FS_STG", FS_STG);
				h.put("FS_DEV", FS_DEV);
				h.put("CATATAN", CATATAN);
				h.put("F_KIV", F_KIV);
				
				updateItemPenambahbaikan(h);
				
				paparItemPenambahbaikkan = getItemPenambahbaikan(session, id_penambahbaikan);
				this.context.put("getItemPenambahbaikkan",paparItemPenambahbaikkan);
				listPIC = listPIC(session);
				this.context.put("listPIC",listPIC);
				this.context.put("fromStatististik",fromStatististik);
				
				skrin_mame = "app/EProgress/viewPenambahbaikkan.jsp";
			}
			
			else if(command.equals("add_item_penambahbaikkan") || command.equals("delete_penambahbaikkan") || command.equals("bataladd_item_penambahbaikkan") || command.equals("show_by_penambahbaikkan"))
			{
				
				String ID_SUBMODUL = getParam("ID_SUBMODUL");
				this.context.put("ID_SUBMODUL",ID_SUBMODUL);
				String openSectionPenambahbaikan = getParam("openSectionPenambahbaikan_"+ID_SUBMODUL);
				
				if(command.equals("add_item_penambahbaikkan"))
				{
					String BIL_TEMP = getParam("BIL_TEMP");
					String ID_PIC = getParam("ID_PIC_"+ID_SUBMODUL);	
					String PENAMBAHBAIKAN = getParam("PENAMBAHBAIKAN_"+ID_SUBMODUL);
					
					String TARIKH_MULA = getParam("TARIKH_MULA"+"_"+ID_SUBMODUL);
					String TARIKH_TARGET_SIAP = getParam("TARIKH_TARGET_SIAP"+"_"+ID_SUBMODUL);
					String FS_LOCAL = getParam("FS_LOCAL"+"_"+ID_SUBMODUL);
					String FS_STG = getParam("FS_STG"+"_"+ID_SUBMODUL);
					String FS_DEV = getParam("FS_DEV"+"_"+ID_SUBMODUL);
					String CATATAN = getParam("CATATAN"+"_"+ID_SUBMODUL);
					String F_KIV = getParam("F_KIV"+"_"+ID_SUBMODUL);
										
					Hashtable h = new Hashtable();	
					h.put("ID_SUBMODUL", ID_SUBMODUL);
					h.put("ID_PIC", ID_PIC);
					h.put("PENAMBAHBAIKAN", PENAMBAHBAIKAN);
					h.put("TURUTAN", BIL_TEMP);
					
					h.put("TARIKH_MULA", TARIKH_MULA);
					h.put("TARIKH_TARGET_SIAP", TARIKH_TARGET_SIAP);
					h.put("FS_LOCAL", FS_LOCAL);
					h.put("FS_STG", FS_STG);
					h.put("FS_DEV", FS_DEV);
					h.put("CATATAN", CATATAN);
					h.put("F_KIV", F_KIV);
										
					insertItemPenambahbaikan(h);
					openSectionPenambahbaikan = "close";
				}
				else if(command.equals("delete_penambahbaikkan"))
				{
					String id_penambahbaikan = getParam("ID_PENAMBAHBAIKAN");
					deletePenambahbaikan(id_penambahbaikan);
					openSectionPenambahbaikan = "close";
				}
				else if(command.equals("bataladd_item_penambahbaikkan"))
				{
					openSectionPenambahbaikan = "close";
				}
				
				
				
				if(openSectionPenambahbaikan.equals("close"))
				{
					openSectionPenambahbaikan = "open";						
					this.context.put("ID_SUBMODUL",ID_SUBMODUL);
					listPenambahbaikkan = listPenambahbaikkan(session,ID_SUBMODUL);
					this.context.put("listPenambahbaikkan",listPenambahbaikkan);
					skrin_mame = "app/EProgress/listPenambahbaikkan.jsp";
				}
				else if(openSectionPenambahbaikan.equals("open"))
				{
					openSectionPenambahbaikan = "close";						
					this.context.put("ID_SUBMODUL",ID_SUBMODUL);
					this.context.put("listPenambahbaikkan","");
					skrin_mame = "app/EProgress/blank_listpenambahbaikan.jsp";
				}					
				this.context.put("openSectionPenambahbaikan",openSectionPenambahbaikan);				
			}
			
			
			
			else if(command.equals("show_statistik_modul"))
			{
				if(getParam("EMEL").equals("Y"))
				{
					hantarEmel(getParam("textAreaStatModul"),getParam("SUBJEK"));
				}
				
				String ID_PROJEK = getParam("ID_PROJEK");
				myLogger.info(" show_statistik_modul ID_PROJEK :"+ID_PROJEK);
				listMainModul = listModulStatistik_by_modul(session,ID_PROJEK);
				this.context.put("ID_PROJEK", ID_PROJEK);
				this.context.put("listMainModul", listMainModul);
				skrin_mame = "app/EProgress/skrinStatistikModul.jsp";
			}
			else if(command.equals("show_statistik_individu"))
			{
				if(getParam("EMEL").equals("Y"))
				{
					hantarEmel(getParam("textAreaStatIndividu"),getParam("SUBJEK"));
				}
				
				String ID_PROJEK = getParam("ID_PROJEK");
				myLogger.info(" show_statistik_modul ID_PROJEK :"+ID_PROJEK);
				listPICStatistik = listPICStatistik_by_individu(session,ID_PROJEK);
				this.context.put("ID_PROJEK", ID_PROJEK);
				this.context.put("listPICStatistik", listPICStatistik);
				skrin_mame = "app/EProgress/skrinStatistikIndividu.jsp";
			}	
			
			
			else if(command.equals("close_listitemby_individu"))
			{
				String ID_PIC = getParam("ID_PIC");
				this.context.put("ID_PIC", ID_PIC);
				this.context.put("ID_MAINMODUL", "");
				
				skrin_mame = "app/EProgress/blank_skrinSenaraiPenambahbaikanIndividu.jsp";
			}
			else if(command.equals("close_listitemby_modul"))
			{
				String ID_MAINMODUL = getParam("ID_MAINMODUL");
				this.context.put("ID_PIC", "");
				this.context.put("ID_MAINMODUL", ID_MAINMODUL);
				skrin_mame = "app/EProgress/blank_skrinSenaraiPenambahbaikanModul.jsp";
			}			
			else if(command.equals("blank_statistik"))
			{
				skrin_mame = "app/EProgress/blank_statistik.jsp";
			}
			else if(command.equals("show_listitemby_modul"))
			{
				String ID_MAINMODUL = getParam("ID_MAINMODUL");
				String ID_PROJEK = getParam("ID_PROJEK");
				String MAINMODUL = getParam("MAINMODUL");
				String flagStat = getParam("flagStat");
				String countItem = getParam("countItem");
				String CURRENTDATE = getParam("CURRENTDATE");
				
				this.context.put("ID_MAINMODUL", ID_MAINMODUL);
				this.context.put("MAINMODUL", MAINMODUL);
				this.context.put("flagStat", flagStat);
				this.context.put("countItem", countItem);
				this.context.put("CURRENTDATE", CURRENTDATE);
				this.context.put("ID_PIC", "");
				
				String arrayIDPenambahbaikan = getArrayID_Penambahbaikan_ByModul(session, ID_MAINMODUL, flagStat,ID_PROJEK);	
				myLogger.info(" *** arrayIDPenambahbaikan :"+arrayIDPenambahbaikan);
				listPaparItemPenambahbaikanStats = listPaparItemPenambahbaikanStats(session, arrayIDPenambahbaikan);
				this.context.put("listPaparItemPenambahbaikanStats",listPaparItemPenambahbaikanStats);
				
				
				skrin_mame = "app/EProgress/skrinSenaraiPenambahbaikanIndividu.jsp";
			}
			else if(command.equals("show_listitemby_individu"))
			{
				String ID_PIC = getParam("ID_PIC");
				String PIC = getParam("PIC");
				String flagStat = getParam("flagStat");
				String countItem = getParam("countItem");
				String CURRENTDATE = getParam("CURRENTDATE");
				String ID_PROJEK = getParam("ID_PROJEK");
				this.context.put("ID_MAINMODUL", "");
				this.context.put("ID_PIC", ID_PIC);
				this.context.put("PIC", PIC);
				this.context.put("flagStat", flagStat);
				this.context.put("countItem", countItem);
				this.context.put("CURRENTDATE", CURRENTDATE);
				
				String arrayIDPenambahbaikan = getArrayID_Penambahbaikan_ByIndividu(session, ID_PIC, flagStat,ID_PROJEK);	
				myLogger.info(" *** arrayIDPenambahbaikan :"+arrayIDPenambahbaikan);
				listPaparItemPenambahbaikanStats = listPaparItemPenambahbaikanStats(session, arrayIDPenambahbaikan);
				this.context.put("listPaparItemPenambahbaikanStats",listPaparItemPenambahbaikanStats);
				
				
				skrin_mame = "app/EProgress/skrinSenaraiPenambahbaikanIndividu.jsp";
			}
			else
			{
				listProjek = listProjek(session);
				this.context.put("listProjek",listProjek);
				
			}
			
			
		
		myLogger.info(" EP : SKRIN NAME : "+skrin_mame);
		return skrin_mame;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listMainModul(HttpSession session,String ID_PROJEK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiModulUtama = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT E.ID_PROJEK,E.ID_MAINMODUL, E.MAINMODUL, E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI, E.TARIKH_KEMASKINI, E.TURUTAN " +
					" FROM EP_MAINMODUL E WHERE E.ID_PROJEK = '"+ID_PROJEK+"' " +
					" ORDER BY TURUTAN,MAINMODUL ASC ";
			myLogger.debug(" EP: SENARAI MODUL UTAMA :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiModulUtama = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_MAINMODUL",rs.getString("ID_MAINMODUL") == null ? "" : rs.getString("ID_MAINMODUL"));
				h.put("MAINMODUL",rs.getString("MAINMODUL") == null ? "" : rs.getString("MAINMODUL"));
				h.put("ID_PROJEK",rs.getString("ID_PROJEK") == null ? "" : rs.getString("ID_PROJEK"));
				senaraiModulUtama.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return senaraiModulUtama;

	}
	
	@SuppressWarnings("unchecked")
	public List listPaparItemPenambahbaikanStats(HttpSession session, String arrayIdPenambahbaikan)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPaparItemPenambahbaikanStats = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " (SELECT 'B' AS LAYERS, TO_CHAR(E.MAINMODUL) AS NAMA_MAINMODUL, '' AS NAMA_SUBMODUL, '' AS PENAMBAHBAIKAN, "+
					" '' AS ID_PENAMBAHBAIKAN, '' AS ID_SUBMODUL, '' AS ID_PIC,  "+
					" '' AS CATATAN, '' AS FLAG_TAHAP_KESUKARAN,  "+
					" '' AS FS_LOCAL, '' AS FS_STG,  '' AS FS_DEV,  "+
					" '' AS TARIKH_MULA, '' AS TARIKH_TARGET_SIAP, '' AS TARIKH_ACTUAL_SIAP, "+
					" '' AS TURUTAN_D, '' AS F_KIV,   TO_CHAR(E.ID_MAINMODUL) AS ID_MAINMODUL, "+
					" '' AS PIC, '' AS TURUTAN_C, TO_CHAR(E.TURUTAN) AS TURUTAN_B "+
					" FROM EP_MAINMODUL E, EP_SUBMODUL EPS, EP_PENAMBAHBAIKAN EPP "+
					" WHERE E.ID_MAINMODUL = EPS.ID_MAINMODUL AND EPP.ID_SUBMODUL = EPS.ID_SUBMODUL AND EPP.ID_PENAMBAHBAIKAN IN ("+arrayIdPenambahbaikan+")) "+
					" UNION "+
					" (SELECT 'C' AS LAYERS, TO_CHAR(MM.MAINMODUL) AS NAMA_MAINMODUL, TO_CHAR(E.SUBMODUL) AS NAMA_SUBMODUL,'' AS PENAMBAHBAIKAN,  "+
					" '' AS ID_PENAMBAHBAIKAN, TO_CHAR(E.ID_SUBMODUL) AS ID_SUBMODUL, '' AS ID_PIC,  "+
					" '' AS CATATAN, '' AS FLAG_TAHAP_KESUKARAN,  "+
					" '' AS FS_LOCAL, '' AS FS_STG,  '' AS FS_DEV,  "+
					" '' AS TARIKH_MULA, '' AS TARIKH_TARGET_SIAP, '' AS TARIKH_ACTUAL_SIAP, "+
					" '' AS TURUTAN_D, '' AS F_KIV,   TO_CHAR(MM.ID_MAINMODUL) AS ID_MAINMODUL, "+
					" '' AS PIC, TO_CHAR(E.TURUTAN) AS TURUTAN_C, '' AS TURUTAN_B "+
					" FROM EP_SUBMODUL E, EP_MAINMODUL MM, EP_PENAMBAHBAIKAN EPP WHERE E.ID_MAINMODUL = MM.ID_MAINMODUL AND EPP.ID_SUBMODUL = E.ID_SUBMODUL AND EPP.ID_PENAMBAHBAIKAN IN ("+arrayIdPenambahbaikan+")) "+
					" UNION "+
					" (SELECT 'D' AS LAYERS, TO_CHAR(MM.MAINMODUL) AS NAMA_MAINMODUL,TO_CHAR(SM.SUBMODUL) AS NAMA_SUBMODUL,TO_CHAR(E.PENAMBAHBAIKAN) AS PENAMBAHBAIKAN, "+
					" TO_CHAR(E.ID_PENAMBAHBAIKAN) AS ID_PENAMBAHBAIKAN, TO_CHAR(E.ID_SUBMODUL) AS ID_SUBMODUL, TO_CHAR(E.ID_PIC) AS ID_PIC,  "+
					" TO_CHAR(E.CATATAN) AS CATATAN, TO_CHAR(E.FLAG_TAHAP_KESUKARAN) AS FLAG_TAHAP_KESUKARAN,  "+
					" TO_CHAR(E.FS_LOCAL) AS FS_LOCAL, TO_CHAR(E.FS_STG) AS FS_STG, TO_CHAR(E.FS_DEV) AS FS_DEV,  "+
					" TO_CHAR(E.TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_MULA, TO_CHAR(E.TARIKH_TARGET_SIAP,'DD/MM/YYYY') AS TARIKH_TARGET_SIAP, TO_CHAR(E.TARIKH_ACTUAL_SIAP,'DD/MM/YYYY') AS TARIKH_ACTUAL_SIAP, "+
					" TO_CHAR(E.TURUTAN) AS TURUTAN_D, TO_CHAR(E.F_KIV) AS F_KIV,  TO_CHAR(MM.ID_MAINMODUL) AS ID_MAINMODUL,  "+
					" TO_CHAR(P.PIC) AS PIC,'' AS TURUTAN_C, '' AS TURUTAN_B "+
					" FROM EP_PENAMBAHBAIKAN E,EP_PIC P, EP_SUBMODUL SM, EP_MAINMODUL MM  "+
					" WHERE E.ID_PIC = P.ID_PIC AND SM.ID_SUBMODUL = E.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL  AND E.ID_PENAMBAHBAIKAN IN ("+arrayIdPenambahbaikan+")) "+
					" ORDER BY ID_MAINMODUL,TURUTAN_B,NAMA_MAINMODUL,ID_SUBMODUL,TURUTAN_C,NAMA_SUBMODUL,TURUTAN_D ASC ";
			
			myLogger.debug(" EP: sql listPaparItemPenambahbaikanStats :"+ sql);
			rs = stmt.executeQuery(sql);
			listPaparItemPenambahbaikanStats = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("LAYERS",rs.getString("LAYERS") == null ? "" : rs.getString("LAYERS"));
				h.put("NAMA_MAINMODUL",rs.getString("NAMA_MAINMODUL") == null ? "" : rs.getString("NAMA_MAINMODUL"));
				h.put("NAMA_SUBMODUL",rs.getString("NAMA_SUBMODUL") == null ? "" : rs.getString("NAMA_SUBMODUL"));
				h.put("MAINMODUL",rs.getString("NAMA_MAINMODUL") == null ? "" : rs.getString("NAMA_MAINMODUL"));
				h.put("SUBMODUL",rs.getString("NAMA_SUBMODUL") == null ? "" : rs.getString("NAMA_SUBMODUL"));
				h.put("PENAMBAHBAIKAN",rs.getString("PENAMBAHBAIKAN") == null ? "" : rs.getString("PENAMBAHBAIKAN"));
				h.put("ID_PENAMBAHBAIKAN",rs.getString("ID_PENAMBAHBAIKAN") == null ? "" : rs.getString("ID_PENAMBAHBAIKAN"));
				h.put("ID_SUBMODUL",rs.getString("ID_SUBMODUL") == null ? "" : rs.getString("ID_SUBMODUL"));
				h.put("ID_PIC",rs.getString("ID_PIC") == null ? "" : rs.getString("ID_PIC"));
				h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("FLAG_TAHAP_KESUKARAN",rs.getString("FLAG_TAHAP_KESUKARAN") == null ? "" : rs.getString("FLAG_TAHAP_KESUKARAN"));
				h.put("FS_LOCAL",rs.getString("FS_LOCAL") == null ? "" : rs.getString("FS_LOCAL"));
				h.put("FS_STG",rs.getString("FS_STG") == null ? "" : rs.getString("FS_STG"));
				h.put("FS_DEV",rs.getString("FS_DEV") == null ? "" : rs.getString("FS_DEV"));
				h.put("TARIKH_MULA",rs.getString("TARIKH_MULA") == null ? "" : rs.getString("TARIKH_MULA"));
				h.put("TARIKH_TARGET_SIAP",rs.getString("TARIKH_TARGET_SIAP") == null ? "" : rs.getString("TARIKH_TARGET_SIAP"));
				h.put("TARIKH_ACTUAL_SIAP",rs.getString("TARIKH_ACTUAL_SIAP") == null ? "" : rs.getString("TARIKH_ACTUAL_SIAP"));
				h.put("TURUTAN_D",rs.getString("TURUTAN_D") == null ? "" : rs.getString("TURUTAN_D"));
				h.put("F_KIV",rs.getString("F_KIV") == null ? "" : rs.getString("F_KIV"));
				h.put("ID_MAINMODUL",rs.getString("ID_MAINMODUL") == null ? "" : rs.getString("ID_MAINMODUL"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));
				h.put("TURUTAN_C",rs.getString("TURUTAN_C") == null ? "" : rs.getString("TURUTAN_C"));
				h.put("TURUTAN_B",rs.getString("TURUTAN_B") == null ? "" : rs.getString("TURUTAN_B"));
				h.put("BIL","");
				listPaparItemPenambahbaikanStats.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return listPaparItemPenambahbaikanStats;

	}
	
	@SuppressWarnings("unchecked")
	public String getArrayID_Penambahbaikan_ByModul(HttpSession session, String ID_MAINMODUL, String FlagStat, String ID_PROJEK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String arrayIDPenambahbaikan = "0";
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			if(FlagStat.equals("JUMLAH_PENAMBAHBAIKAN"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E,EP_MAINMODUL MM,EP_SUBMODUL SM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.F_KIV IS NULL ";					
			}
			else if(FlagStat.equals("JUMLAH_SELESAI_CODING"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E,EP_MAINMODUL MM,EP_SUBMODUL SM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.FS_LOCAL = 1 AND E.F_KIV IS NULL ";					
			}
			else if(FlagStat.equals("JUMLAH_DELAY"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E,EP_MAINMODUL MM,EP_SUBMODUL SM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.FS_LOCAL IS NULL AND E.F_KIV IS NULL AND TO_DATE(E.TARIKH_TARGET_SIAP) < SYSDATE ";					
			}
			else if(FlagStat.equals("JUMLAH_PATUT_SELESAI"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E,EP_MAINMODUL MM,EP_SUBMODUL SM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.F_KIV IS NULL AND TO_DATE(E.TARIKH_TARGET_SIAP) <= SYSDATE ";					
			}
			else if(FlagStat.equals("JUMLAH_KIV"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E,EP_MAINMODUL MM,EP_SUBMODUL SM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.F_KIV IS NOT NULL ";					
			}
			
			if(!ID_MAINMODUL.equals("999999"))
			{
			sql += " AND MM.ID_MAINMODUL = "+ID_MAINMODUL+"  ";
			}
			sql += " AND MM.ID_PROJEK = "+ID_PROJEK+"" ;
			
			myLogger.debug(" EP: SQL getArrayID_Penambahbaikan_ByModul :"+ sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				arrayIDPenambahbaikan += ","+(rs.getString("ID_PENAMBAHBAIKAN") == null ? "0" : rs.getString("ID_PENAMBAHBAIKAN"));			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return arrayIDPenambahbaikan;

	}
	
	
	@SuppressWarnings("unchecked")
	public String getArrayID_Penambahbaikan_ByIndividu(HttpSession session, String ID_PIC, String FlagStat,String ID_PROJEK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		String arrayIDPenambahbaikan = "0";
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			if(FlagStat.equals("JUMLAH_PENAMBAHBAIKAN"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E, EP_SUBMODUL SM, EP_MAINMODUL MM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.F_KIV IS NULL ";					
			}
			else if(FlagStat.equals("JUMLAH_SELESAI_CODING"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E , EP_SUBMODUL SM, EP_MAINMODUL MM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.FS_LOCAL = 1 AND E.F_KIV IS NULL ";					
			}
			else if(FlagStat.equals("JUMLAH_DELAY"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E , EP_SUBMODUL SM, EP_MAINMODUL MM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.FS_LOCAL IS NULL AND E.F_KIV IS NULL AND TO_DATE(E.TARIKH_TARGET_SIAP) < SYSDATE ";					
			}
			else if(FlagStat.equals("JUMLAH_PATUT_SELESAI"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E , EP_SUBMODUL SM, EP_MAINMODUL MM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.F_KIV IS NULL AND TO_DATE(E.TARIKH_TARGET_SIAP) <= SYSDATE ";					
			}
			else if(FlagStat.equals("JUMLAH_KIV"))
			{
			sql = " SELECT E.ID_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E , EP_SUBMODUL SM, EP_MAINMODUL MM WHERE E.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL AND E.F_KIV IS NOT NULL ";					
			}
			
			if(!ID_PIC.equals("999999"))
			{
			sql += "AND E.ID_PIC = "+ID_PIC+" AND MM.ID_PROJEK = "+ID_PROJEK+" ";
			}
			
			
			myLogger.debug(" EP: SQL arrayIDPenambahbaikan :"+ sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				arrayIDPenambahbaikan += ","+(rs.getString("ID_PENAMBAHBAIKAN") == null ? "0" : rs.getString("ID_PENAMBAHBAIKAN"));			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return arrayIDPenambahbaikan;

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List listPICStatistik_by_individu(HttpSession session,String ID_PROJEK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPICStatistik = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT DISTINCT E.ID_PIC, E.PIC, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E1 , EP_SUBMODUL SM1, EP_MAINMODUL MM1 WHERE MM1.ID_PROJEK = MM.ID_PROJEK AND E1.ID_SUBMODUL = SM1.ID_SUBMODUL AND SM1.ID_MAINMODUL = MM1.ID_MAINMODUL AND E1.ID_PIC = E.ID_PIC AND E1.F_KIV IS NULL) AS JUMLAH_PENAMBAHBAIKAN, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E2 , EP_SUBMODUL SM2, EP_MAINMODUL MM2 WHERE MM2.ID_PROJEK = MM.ID_PROJEK AND E2.ID_SUBMODUL = SM2.ID_SUBMODUL AND SM2.ID_MAINMODUL = MM2.ID_MAINMODUL AND E2.ID_PIC = E.ID_PIC AND E2.FS_LOCAL = 1 AND E2.F_KIV IS NULL) AS JUMLAH_SELESAI_CODING, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E3 , EP_SUBMODUL SM3, EP_MAINMODUL MM3 WHERE MM3.ID_PROJEK = MM.ID_PROJEK AND E3.ID_SUBMODUL = SM3.ID_SUBMODUL AND SM3.ID_MAINMODUL = MM3.ID_MAINMODUL AND E3.ID_PIC = E.ID_PIC AND E3.FS_STG = 1 AND E3.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_STG, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E4 , EP_SUBMODUL SM4, EP_MAINMODUL MM4 WHERE MM4.ID_PROJEK = MM.ID_PROJEK AND E4.ID_SUBMODUL = SM4.ID_SUBMODUL AND SM4.ID_MAINMODUL = MM4.ID_MAINMODUL AND E4.ID_PIC = E.ID_PIC AND E4.FS_DEV = 1 AND E4.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_LIVE, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E5 , EP_SUBMODUL SM5, EP_MAINMODUL MM5 WHERE MM5.ID_PROJEK = MM.ID_PROJEK AND E5.ID_SUBMODUL = SM5.ID_SUBMODUL AND SM5.ID_MAINMODUL = MM5.ID_MAINMODUL AND E5.ID_PIC = E.ID_PIC AND E5.FS_LOCAL IS NULL AND E5.F_KIV IS NULL AND TO_DATE(E5.TARIKH_TARGET_SIAP) < SYSDATE) AS JUMLAH_DELAY,  "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E6 , EP_SUBMODUL SM6, EP_MAINMODUL MM6 WHERE MM6.ID_PROJEK = MM.ID_PROJEK AND E6.ID_SUBMODUL = SM6.ID_SUBMODUL AND SM6.ID_MAINMODUL = MM6.ID_MAINMODUL AND E6.ID_PIC = E.ID_PIC AND E6.F_KIV IS NULL AND TO_DATE(E6.TARIKH_TARGET_SIAP) <= SYSDATE) AS JUMLAH_PATUT_SELESAI, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E7 , EP_SUBMODUL SM7, EP_MAINMODUL MM7 WHERE MM7.ID_PROJEK = MM.ID_PROJEK AND E7.ID_SUBMODUL = SM7.ID_SUBMODUL AND SM7.ID_MAINMODUL = MM7.ID_MAINMODUL AND E7.ID_PIC = E.ID_PIC AND E7.F_KIV IS NOT NULL) AS JUMLAH_KIV,TO_CHAR(SYSDATE,'DD/MM/YYYY') AS CURRENTDATE "+
					/*" FROM EP_PIC E, EP_PENAMBAHBAIKAN P  "+
					" WHERE P.ID_PIC = E.ID_PIC  "+*/
					" FROM EP_PIC E, EP_PENAMBAHBAIKAN P, EP_SUBMODUL SM, EP_MAINMODUL MM  "+
					" WHERE MM.ID_PROJEK = '"+ID_PROJEK+"' AND P.ID_PIC = E.ID_PIC AND P.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL  "+
					
					
					" UNION  "+
					/*
					" SELECT DISTINCT 999999 AS ID_PIC, '' AS PIC, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E1 WHERE E1.F_KIV IS NULL) AS JUMLAH_PENAMBAHBAIKAN, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E2 WHERE E2.FS_LOCAL = 1 AND E2.F_KIV IS NULL) AS JUMLAH_SELESAI_CODING, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E3 WHERE E3.FS_STG = 1 AND E3.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_STG, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E4 WHERE E4.FS_DEV = 1 AND E4.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_LIVE, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E5 WHERE E5.FS_LOCAL IS NULL AND E5.F_KIV IS NULL AND TO_DATE(E5.TARIKH_TARGET_SIAP) < SYSDATE) AS JUMLAH_DELAY, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E6 WHERE E6.F_KIV IS NULL AND TO_DATE(E6.TARIKH_TARGET_SIAP) <= SYSDATE) AS JUMLAH_PATUT_SELESAI, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E7 WHERE E7.F_KIV IS NOT NULL) AS JUMLAH_KIV,TO_CHAR(SYSDATE,'DD/MM/YYYY') AS CURRENTDATE "+
					" FROM DUAL "+
					*/
					
					" SELECT DISTINCT 999999 AS ID_PIC, '' AS PIC, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E1, EP_SUBMODUL SM1, EP_MAINMODUL MM1 WHERE MM1.ID_PROJEK = E.ID_PROJEK AND E1.ID_SUBMODUL = SM1.ID_SUBMODUL AND SM1.ID_MAINMODUL = MM1.ID_MAINMODUL  AND E1.F_KIV IS NULL) AS JUMLAH_PENAMBAHBAIKAN, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E2, EP_SUBMODUL SM2, EP_MAINMODUL MM2 WHERE MM2.ID_PROJEK = E.ID_PROJEK AND E2.ID_SUBMODUL = SM2.ID_SUBMODUL AND SM2.ID_MAINMODUL = MM2.ID_MAINMODUL  AND E2.FS_LOCAL = 1 AND E2.F_KIV IS NULL) AS JUMLAH_SELESAI_CODING, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E3, EP_SUBMODUL SM3, EP_MAINMODUL MM3 WHERE MM3.ID_PROJEK = E.ID_PROJEK AND E3.ID_SUBMODUL = SM3.ID_SUBMODUL AND SM3.ID_MAINMODUL = MM3.ID_MAINMODUL  AND E3.FS_STG = 1 AND E3.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_STG, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E4, EP_SUBMODUL SM4, EP_MAINMODUL MM4 WHERE MM4.ID_PROJEK = E.ID_PROJEK AND E4.ID_SUBMODUL = SM4.ID_SUBMODUL AND SM4.ID_MAINMODUL = MM4.ID_MAINMODUL  AND E4.FS_DEV = 1 AND E4.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_LIVE, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E5, EP_SUBMODUL SM5, EP_MAINMODUL MM5 WHERE MM5.ID_PROJEK = E.ID_PROJEK AND E5.ID_SUBMODUL = SM5.ID_SUBMODUL AND SM5.ID_MAINMODUL = MM5.ID_MAINMODUL  AND E5.FS_LOCAL IS NULL AND E5.F_KIV IS NULL AND TO_DATE(E5.TARIKH_TARGET_SIAP) < SYSDATE) AS JUMLAH_DELAY,  "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E6, EP_SUBMODUL SM6, EP_MAINMODUL MM6 WHERE MM6.ID_PROJEK = E.ID_PROJEK AND E6.ID_SUBMODUL = SM6.ID_SUBMODUL AND SM6.ID_MAINMODUL = MM6.ID_MAINMODUL  AND E6.F_KIV IS NULL AND TO_DATE(E6.TARIKH_TARGET_SIAP) <= SYSDATE) AS JUMLAH_PATUT_SELESAI, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E7, EP_SUBMODUL SM7, EP_MAINMODUL MM7 WHERE MM7.ID_PROJEK = E.ID_PROJEK AND E7.ID_SUBMODUL = SM7.ID_SUBMODUL AND SM7.ID_MAINMODUL = MM7.ID_MAINMODUL  AND E7.F_KIV IS NOT NULL) AS JUMLAH_KIV,TO_CHAR(SYSDATE,'DD/MM/YYYY') AS CURRENTDATE "+
					" FROM EP_PROJEK E  "+
					" WHERE E.ID_PROJEK = '"+ID_PROJEK+"' "+
					
					" ORDER BY PIC ";
			myLogger.debug(" EP: listPICStatistik_by_individu :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiPICStatistik = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PIC",rs.getString("ID_PIC") == null ? "" : rs.getString("ID_PIC"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));
				h.put("JUMLAH_PENAMBAHBAIKAN",rs.getString("JUMLAH_PENAMBAHBAIKAN") == null ? "" : rs.getString("JUMLAH_PENAMBAHBAIKAN"));
				h.put("JUMLAH_SELESAI_CODING",rs.getString("JUMLAH_SELESAI_CODING") == null ? "" : rs.getString("JUMLAH_SELESAI_CODING"));
				h.put("JUMLAH_SELESAI_DPY_STG",rs.getString("JUMLAH_SELESAI_DPY_STG") == null ? "" : rs.getString("JUMLAH_SELESAI_DPY_STG"));
				h.put("JUMLAH_SELESAI_DPY_LIVE",rs.getString("JUMLAH_SELESAI_DPY_LIVE") == null ? "" : rs.getString("JUMLAH_SELESAI_DPY_LIVE"));
				h.put("JUMLAH_DELAY",rs.getString("JUMLAH_DELAY") == null ? "" : rs.getString("JUMLAH_DELAY"));
				h.put("JUMLAH_PATUT_SELESAI",rs.getString("JUMLAH_PATUT_SELESAI") == null ? "" : rs.getString("JUMLAH_PATUT_SELESAI"));
				h.put("JUMLAH_KIV",rs.getString("JUMLAH_KIV") == null ? "" : rs.getString("JUMLAH_KIV"));
				h.put("CURRENTDATE",rs.getString("CURRENTDATE") == null ? "" : rs.getString("CURRENTDATE"));
				/*
				double peratus_progress = round((rs.getDouble("JUMLAH_SELESAI_CODING") / rs.getDouble("JUMLAH_PATUT_SELESAI"))*100,2);
				myLogger.debug(" EP: peratus_progress :"+ peratus_progress);
				h.put("PERATUS_PROGRESS",peratus_progress);
				*/
				
				
				double peratus_progress = 100 - ((rs.getDouble("JUMLAH_DELAY") / rs.getDouble("JUMLAH_PATUT_SELESAI"))*100);
				myLogger.debug(" EP: peratus_progress :"+ peratus_progress);
				double peratus_overallprogress = ((rs.getDouble("JUMLAH_SELESAI_CODING") / rs.getDouble("JUMLAH_PENAMBAHBAIKAN"))*100);
				
				double ahead_behind = 0.00;
				if(peratus_progress>=100)
				{
					peratus_progress = round((rs.getDouble("JUMLAH_SELESAI_CODING") / rs.getDouble("JUMLAH_PATUT_SELESAI"))*100,2);
					ahead_behind = round(peratus_progress-100,2);
				}
				else
				{
					ahead_behind = round(peratus_progress-100,2);
				}
				
				h.put("PERATUS_PROGRESS",round(peratus_progress,2));
				h.put("PERATUS_OVERALLPROGRESS",round(peratus_overallprogress,2));
				h.put("PERATUS_AHEADBEHIND",ahead_behind);
				senaraiPICStatistik.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return senaraiPICStatistik;

	}
	
	
	@SuppressWarnings("unchecked")
	public List listModulStatistik_by_modul(HttpSession session,String ID_PROJEK)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiModulStatistik = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT DISTINCT MM.ID_MAINMODUL, MM.MAINMODUL, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E1, EP_SUBMODUL SM1, EP_MAINMODUL MM1 WHERE E1.ID_SUBMODUL = SM1.ID_SUBMODUL AND SM1.ID_MAINMODUL = MM1.ID_MAINMODUL AND MM1.ID_MAINMODUL = MM.ID_MAINMODUL AND E1.F_KIV IS NULL) AS JUMLAH_PENAMBAHBAIKAN, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E2, EP_SUBMODUL SM2, EP_MAINMODUL MM2 WHERE E2.ID_SUBMODUL = SM2.ID_SUBMODUL AND SM2.ID_MAINMODUL = MM2.ID_MAINMODUL AND MM2.ID_MAINMODUL = MM.ID_MAINMODUL AND E2.FS_LOCAL = 1 AND E2.F_KIV IS NULL) AS JUMLAH_SELESAI_CODING, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E3, EP_SUBMODUL SM3, EP_MAINMODUL MM3 WHERE E3.ID_SUBMODUL = SM3.ID_SUBMODUL AND SM3.ID_MAINMODUL = MM3.ID_MAINMODUL AND MM3.ID_MAINMODUL = MM.ID_MAINMODUL AND E3.FS_STG = 1 AND E3.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_STG, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E4, EP_SUBMODUL SM4, EP_MAINMODUL MM4 WHERE E4.ID_SUBMODUL = SM4.ID_SUBMODUL AND SM4.ID_MAINMODUL = MM4.ID_MAINMODUL AND MM4.ID_MAINMODUL = MM.ID_MAINMODUL AND E4.FS_DEV = 1 AND E4.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_LIVE, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E5, EP_SUBMODUL SM5, EP_MAINMODUL MM5 WHERE E5.ID_SUBMODUL = SM5.ID_SUBMODUL AND SM5.ID_MAINMODUL = MM5.ID_MAINMODUL AND MM5.ID_MAINMODUL = MM.ID_MAINMODUL AND E5.FS_LOCAL IS NULL AND E5.F_KIV IS NULL AND TO_DATE(E5.TARIKH_TARGET_SIAP) < SYSDATE) AS JUMLAH_DELAY,  "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E6, EP_SUBMODUL SM6, EP_MAINMODUL MM6 WHERE E6.ID_SUBMODUL = SM6.ID_SUBMODUL AND SM6.ID_MAINMODUL = MM6.ID_MAINMODUL AND MM6.ID_MAINMODUL = MM.ID_MAINMODUL AND E6.F_KIV IS NULL AND TO_DATE(E6.TARIKH_TARGET_SIAP) <= SYSDATE) AS JUMLAH_PATUT_SELESAI, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E7, EP_SUBMODUL SM7, EP_MAINMODUL MM7 WHERE E7.ID_SUBMODUL = SM7.ID_SUBMODUL AND SM7.ID_MAINMODUL = MM7.ID_MAINMODUL AND MM7.ID_MAINMODUL = MM.ID_MAINMODUL AND E7.F_KIV IS NOT NULL) AS JUMLAH_KIV,TO_CHAR(SYSDATE,'DD/MM/YYYY') AS CURRENTDATE "+
					" FROM EP_PIC E, EP_PENAMBAHBAIKAN P, EP_SUBMODUL SM, EP_MAINMODUL MM  "+
					" WHERE MM.ID_PROJEK = '"+ID_PROJEK+"' AND P.ID_PIC = E.ID_PIC AND P.ID_SUBMODUL = SM.ID_SUBMODUL AND SM.ID_MAINMODUL = MM.ID_MAINMODUL  "+
					" UNION  "+
					/*
					" SELECT DISTINCT 999999 AS ID_MAINMODUL, '' AS MAINMODUL, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E1 WHERE E1.F_KIV IS NULL) AS JUMLAH_PENAMBAHBAIKAN, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E2 WHERE E2.FS_LOCAL = 1 AND E2.F_KIV IS NULL) AS JUMLAH_SELESAI_CODING, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E3 WHERE E3.FS_STG = 1 AND E3.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_STG, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E4 WHERE E4.FS_DEV = 1 AND E4.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_LIVE, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E5 WHERE E5.FS_LOCAL IS NULL AND E5.F_KIV IS NULL AND TO_DATE(E5.TARIKH_TARGET_SIAP) < SYSDATE) AS JUMLAH_DELAY, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E6 WHERE E6.F_KIV IS NULL AND TO_DATE(E6.TARIKH_TARGET_SIAP) <= SYSDATE) AS JUMLAH_PATUT_SELESAI, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E7 WHERE E7.F_KIV IS NOT NULL) AS JUMLAH_KIV,TO_CHAR(SYSDATE,'DD/MM/YYYY') AS CURRENTDATE "+
					" FROM DUAL "+
					*/
					" SELECT DISTINCT 999999 AS ID_MAINMODUL, '' AS MAINMODUL, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E1, EP_SUBMODUL SM1, EP_MAINMODUL MM1 WHERE MM1.ID_PROJEK = E.ID_PROJEK AND E1.ID_SUBMODUL = SM1.ID_SUBMODUL AND SM1.ID_MAINMODUL = MM1.ID_MAINMODUL  AND E1.F_KIV IS NULL) AS JUMLAH_PENAMBAHBAIKAN, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E2, EP_SUBMODUL SM2, EP_MAINMODUL MM2 WHERE MM2.ID_PROJEK = E.ID_PROJEK AND E2.ID_SUBMODUL = SM2.ID_SUBMODUL AND SM2.ID_MAINMODUL = MM2.ID_MAINMODUL  AND E2.FS_LOCAL = 1 AND E2.F_KIV IS NULL) AS JUMLAH_SELESAI_CODING, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E3, EP_SUBMODUL SM3, EP_MAINMODUL MM3 WHERE MM3.ID_PROJEK = E.ID_PROJEK AND E3.ID_SUBMODUL = SM3.ID_SUBMODUL AND SM3.ID_MAINMODUL = MM3.ID_MAINMODUL  AND E3.FS_STG = 1 AND E3.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_STG, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E4, EP_SUBMODUL SM4, EP_MAINMODUL MM4 WHERE MM4.ID_PROJEK = E.ID_PROJEK AND E4.ID_SUBMODUL = SM4.ID_SUBMODUL AND SM4.ID_MAINMODUL = MM4.ID_MAINMODUL  AND E4.FS_DEV = 1 AND E4.F_KIV IS NULL) AS JUMLAH_SELESAI_DPY_LIVE, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E5, EP_SUBMODUL SM5, EP_MAINMODUL MM5 WHERE MM5.ID_PROJEK = E.ID_PROJEK AND E5.ID_SUBMODUL = SM5.ID_SUBMODUL AND SM5.ID_MAINMODUL = MM5.ID_MAINMODUL  AND E5.FS_LOCAL IS NULL AND E5.F_KIV IS NULL AND TO_DATE(E5.TARIKH_TARGET_SIAP) < SYSDATE) AS JUMLAH_DELAY,  "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E6, EP_SUBMODUL SM6, EP_MAINMODUL MM6 WHERE MM6.ID_PROJEK = E.ID_PROJEK AND E6.ID_SUBMODUL = SM6.ID_SUBMODUL AND SM6.ID_MAINMODUL = MM6.ID_MAINMODUL  AND E6.F_KIV IS NULL AND TO_DATE(E6.TARIKH_TARGET_SIAP) <= SYSDATE) AS JUMLAH_PATUT_SELESAI, "+
					" (SELECT COUNT(*) FROM EP_PENAMBAHBAIKAN E7, EP_SUBMODUL SM7, EP_MAINMODUL MM7 WHERE MM7.ID_PROJEK = E.ID_PROJEK AND E7.ID_SUBMODUL = SM7.ID_SUBMODUL AND SM7.ID_MAINMODUL = MM7.ID_MAINMODUL  AND E7.F_KIV IS NOT NULL) AS JUMLAH_KIV,TO_CHAR(SYSDATE,'DD/MM/YYYY') AS CURRENTDATE "+
					" FROM EP_PROJEK E  "+
					" WHERE E.ID_PROJEK = '"+ID_PROJEK+"' "+
					
					" ORDER BY MAINMODUL ";
			myLogger.debug(" EP: listModulStatistik_by_modul :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiModulStatistik = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_MAINMODUL",rs.getString("ID_MAINMODUL") == null ? "" : rs.getString("ID_MAINMODUL"));
				h.put("MAINMODUL",rs.getString("MAINMODUL") == null ? "" : rs.getString("MAINMODUL"));
				h.put("JUMLAH_PENAMBAHBAIKAN",rs.getString("JUMLAH_PENAMBAHBAIKAN") == null ? "" : rs.getString("JUMLAH_PENAMBAHBAIKAN"));
				h.put("JUMLAH_SELESAI_CODING",rs.getString("JUMLAH_SELESAI_CODING") == null ? "" : rs.getString("JUMLAH_SELESAI_CODING"));
				h.put("JUMLAH_SELESAI_DPY_STG",rs.getString("JUMLAH_SELESAI_DPY_STG") == null ? "" : rs.getString("JUMLAH_SELESAI_DPY_STG"));
				h.put("JUMLAH_SELESAI_DPY_LIVE",rs.getString("JUMLAH_SELESAI_DPY_LIVE") == null ? "" : rs.getString("JUMLAH_SELESAI_DPY_LIVE"));
				h.put("JUMLAH_DELAY",rs.getString("JUMLAH_DELAY") == null ? "" : rs.getString("JUMLAH_DELAY"));
				h.put("JUMLAH_PATUT_SELESAI",rs.getString("JUMLAH_PATUT_SELESAI") == null ? "" : rs.getString("JUMLAH_PATUT_SELESAI"));
				h.put("JUMLAH_KIV",rs.getString("JUMLAH_KIV") == null ? "" : rs.getString("JUMLAH_KIV"));
				h.put("CURRENTDATE",rs.getString("CURRENTDATE") == null ? "" : rs.getString("CURRENTDATE"));
				/*
				double peratus_progress = round((rs.getDouble("JUMLAH_SELESAI_CODING") / rs.getDouble("JUMLAH_PATUT_SELESAI"))*100,2);
				myLogger.debug(" EP: peratus_progress :"+ peratus_progress);
				h.put("PERATUS_PROGRESS",peratus_progress);
				*/
				
				
				double peratus_progress = 100 - ((rs.getDouble("JUMLAH_DELAY") / rs.getDouble("JUMLAH_PATUT_SELESAI"))*100);
				myLogger.debug(" EP: peratus_progress :"+ peratus_progress);
				double peratus_overallprogress = ((rs.getDouble("JUMLAH_SELESAI_CODING") / rs.getDouble("JUMLAH_PENAMBAHBAIKAN"))*100);
				
				double ahead_behind = 0.00;
				if(peratus_progress>=100)
				{
					peratus_progress = round((rs.getDouble("JUMLAH_SELESAI_CODING") / rs.getDouble("JUMLAH_PATUT_SELESAI"))*100,2);
					ahead_behind = round(peratus_progress-100,2);
				}
				else
				{
					ahead_behind = round(peratus_progress-100,2);
				}
				
				h.put("PERATUS_PROGRESS",round(peratus_progress,2));
				h.put("PERATUS_OVERALLPROGRESS",round(peratus_overallprogress,2));
				h.put("PERATUS_AHEADBEHIND",ahead_behind);
				senaraiModulStatistik.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return senaraiModulStatistik;

	}
	
	
	
	@SuppressWarnings("unchecked")
	public List listPICStatistik(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPICStatistik = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT DISTINCT E.ID_PIC, E.PIC FROM EP_PIC E, EP_PENAMBAHBAIKAN P "+
					" WHERE P.ID_PIC = E.ID_PIC ORDER BY PIC ASC ";
			myLogger.debug(" EP: listPICStatistik :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiPICStatistik = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PIC",rs.getString("ID_PIC") == null ? "" : rs.getString("ID_PIC"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));
				senaraiPICStatistik.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return senaraiPICStatistik;

	}
	
	@SuppressWarnings("unchecked")
	public List listProjek(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listProjek = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT E.ID_PROJEK, E.PROJEK, E.ID_MASUK, "+
					" E.TARIKH_MASUK, E.ID_KEMASKINI, E.TARIKH_KEMASKINI "+
					" FROM EP_PROJEK E ORDER BY PROJEK ASC ";
			myLogger.debug(" EP: listPICStatistik :"+ sql);
			rs = stmt.executeQuery(sql);
			listProjek = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PROJEK",rs.getString("ID_PROJEK") == null ? "" : rs.getString("ID_PROJEK"));
				h.put("PROJEK",rs.getString("PROJEK") == null ? "" : rs.getString("PROJEK"));
				listProjek.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW senaraiModulUtama :"+ senaraiModulUtama);
		return listProjek;

	}
	
	@SuppressWarnings("unchecked")
	public List listSubModul(HttpSession session,String id_mainmodul)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiSubUtama = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT E.ID_SUBMODUL, M.MAINMODUL, E.ID_MAINMODUL, E.SUBMODUL, E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI,  E.TARIKH_KEMASKINI, E.TURUTAN "+
				  " FROM EP_SUBMODUL E, EP_MAINMODUL M WHERE M.ID_MAINMODUL = E.ID_MAINMODUL AND E.ID_MAINMODUL = "+id_mainmodul+" ORDER BY TURUTAN,SUBMODUL ASC ";
			myLogger.debug(" EP: SENARAI MODUL SUB :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiSubUtama = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_SUBMODUL",rs.getString("ID_SUBMODUL") == null ? "" : rs.getString("ID_SUBMODUL"));
				h.put("ID_MAINMODUL",rs.getString("ID_MAINMODUL") == null ? "" : rs.getString("ID_MAINMODUL"));
				h.put("SUBMODUL",rs.getString("SUBMODUL") == null ? "" : rs.getString("SUBMODUL"));
				h.put("MAINMODUL",rs.getString("MAINMODUL") == null ? "" : rs.getString("MAINMODUL"));
				senaraiSubUtama.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		myLogger.debug(" EP: SHOW senaraiSubUtama :"+ senaraiSubUtama);
		return senaraiSubUtama;

	}
	
	@SuppressWarnings("unchecked")
	public List listPenambahbaikkan(HttpSession session,String id_submodul)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPenambahbaikkan = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT  E.ID_PENAMBAHBAIKAN, E.ID_SUBMODUL, E.ID_PIC, "+
				  " E.PENAMBAHBAIKAN, E.CATATAN, E.FLAG_TAHAP_KESUKARAN, "+
				  " E.FS_LOCAL, E.FS_STG, E.FS_DEV, E.F_KIV, "+
				  " E.TARIKH_MULA, E.TARIKH_TARGET_SIAP, E.TARIKH_ACTUAL_SIAP, "+
				  " E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI, "+
				  " E.TARIKH_KEMASKINI, E.TURUTAN, PIC.PIC "+
				  " FROM EP_PENAMBAHBAIKAN E, EP_PIC PIC WHERE PIC.ID_PIC = E.ID_PIC AND E.ID_SUBMODUL = "+id_submodul+" ORDER BY TURUTAN,PENAMBAHBAIKAN ASC ";
			myLogger.debug(" EP: SENARAI PENAMBAHBAIKKAN :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiPenambahbaikkan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_PENAMBAHBAIKAN",rs.getString("ID_PENAMBAHBAIKAN") == null ? "" : rs.getString("ID_PENAMBAHBAIKAN"));
				h.put("ID_SUBMODUL",rs.getString("ID_SUBMODUL") == null ? "" : rs.getString("ID_SUBMODUL"));
				h.put("ID_PIC",rs.getString("ID_PIC") == null ? "" : rs.getString("ID_PIC"));
				h.put("PENAMBAHBAIKAN",rs.getString("PENAMBAHBAIKAN") == null ? "" : rs.getString("PENAMBAHBAIKAN"));
				h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("FLAG_TAHAP_KESUKARAN",rs.getString("FLAG_TAHAP_KESUKARAN") == null ? "" : rs.getString("FLAG_TAHAP_KESUKARAN"));
				h.put("FS_LOCAL",rs.getString("FS_LOCAL") == null ? "" : rs.getString("FS_LOCAL"));
				h.put("FS_STG",rs.getString("FS_STG") == null ? "" : rs.getString("FS_STG"));
				h.put("FS_DEV",rs.getString("FS_DEV") == null ? "" : rs.getString("FS_DEV"));
				h.put("TARIKH_MULA",rs.getString("TARIKH_MULA") == null ? "" : formatter.format(rs.getDate("TARIKH_MULA")));
				h.put("TARIKH_TARGET_SIAP",rs.getString("TARIKH_TARGET_SIAP") == null ? "" : formatter.format(rs.getDate("TARIKH_TARGET_SIAP")));
				h.put("TARIKH_ACTUAL_SIAP",rs.getString("TARIKH_ACTUAL_SIAP") == null ? "" : formatter.format(rs.getDate("TARIKH_ACTUAL_SIAP")));
				h.put("TURUTAN",rs.getString("TURUTAN") == null ? "" : rs.getString("TURUTAN"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));
				h.put("F_KIV",rs.getString("F_KIV") == null ? "" : rs.getString("F_KIV"));
				senaraiPenambahbaikkan.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		//myLogger.debug(" EP: SHOW SENARAI PENAMBAHBAIKKAN :"+ senaraiPenambahbaikkan);
		return senaraiPenambahbaikkan;

	}
	
	
	public Hashtable getItemPenambahbaikan(HttpSession session, String id_penambahbaikan) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT E.ID_PENAMBAHBAIKAN, E.ID_SUBMODUL, E.ID_PIC, P.PIC,  E.PENAMBAHBAIKAN, E.CATATAN, E.FLAG_TAHAP_KESUKARAN, "+
				" E.FS_LOCAL, E.FS_STG, E.FS_DEV, E.F_KIV,  E.TARIKH_MULA, E.TARIKH_TARGET_SIAP, E.TARIKH_ACTUAL_SIAP, E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI,  "+
				" E.TARIKH_KEMASKINI, E.TURUTAN FROM EP_PENAMBAHBAIKAN E, EP_PIC P WHERE P.ID_PIC = E.ID_PIC AND E.ID_PENAMBAHBAIKAN = "+id_penambahbaikan+"";
			myLogger.info(" getItemPenambahbaikan :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_PENAMBAHBAIKAN",rs.getString("ID_PENAMBAHBAIKAN") == null ? "" : rs.getString("ID_PENAMBAHBAIKAN"));
				h.put("ID_SUBMODUL",rs.getString("ID_SUBMODUL") == null ? "" : rs.getString("ID_SUBMODUL"));
				h.put("ID_PIC",rs.getString("ID_PIC") == null ? "" : rs.getString("ID_PIC"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));
				h.put("PENAMBAHBAIKAN",rs.getString("PENAMBAHBAIKAN") == null ? "" : rs.getString("PENAMBAHBAIKAN"));
				h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("FLAG_TAHAP_KESUKARAN",rs.getString("FLAG_TAHAP_KESUKARAN") == null ? "" : rs.getString("FLAG_TAHAP_KESUKARAN"));
				h.put("FS_LOCAL",rs.getString("FS_LOCAL") == null ? "" : rs.getString("FS_LOCAL"));
				h.put("FS_STG",rs.getString("FS_STG") == null ? "" : rs.getString("FS_STG"));
				h.put("FS_DEV",rs.getString("FS_DEV") == null ? "" : rs.getString("FS_DEV"));
				h.put("TARIKH_MULA",rs.getString("TARIKH_MULA") == null ? "" : formatter.format(rs.getDate("TARIKH_MULA")));
				h.put("TARIKH_TARGET_SIAP",rs.getString("TARIKH_TARGET_SIAP") == null ? "" : formatter.format(rs.getDate("TARIKH_TARGET_SIAP")));
				h.put("TARIKH_ACTUAL_SIAP",rs.getString("TARIKH_ACTUAL_SIAP") == null ? "" : formatter.format(rs.getDate("TARIKH_ACTUAL_SIAP")));
				h.put("TURUTAN",rs.getString("TURUTAN") == null ? "" : rs.getString("TURUTAN"));
				h.put("F_KIV",rs.getString("F_KIV") == null ? "" : rs.getString("F_KIV"));
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
	
	
	public Hashtable getItemSubmodul(HttpSession session, String id_submodul) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT F.MAINMODUL, E.ID_SUBMODUL, E.ID_MAINMODUL, E.SUBMODUL,  E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI, "+
					" E.TARIKH_KEMASKINI, E.TURUTAN FROM EP_SUBMODUL E, EP_MAINMODUL F " +
					" WHERE F.ID_MAINMODUL = E.ID_MAINMODUL AND E.ID_SUBMODUL = "+id_submodul+" ";
			myLogger.info(" getItemSubmodul :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_SUBMODUL",rs.getString("ID_SUBMODUL") == null ? "" : rs.getString("ID_SUBMODUL"));
				h.put("ID_MAINMODUL",rs.getString("ID_MAINMODUL") == null ? "" : rs.getString("ID_MAINMODUL"));
				h.put("SUBMODUL",rs.getString("SUBMODUL") == null ? "" : rs.getString("SUBMODUL"));
				h.put("MAINMODUL",rs.getString("MAINMODUL") == null ? "" : rs.getString("MAINMODUL"));
				h.put("TURUTAN",rs.getString("TURUTAN") == null ? "" : rs.getString("TURUTAN"));
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
	
	
	public Hashtable getItemMainmodul(HttpSession session, String id_mainmodul,String id_projek) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT E.ID_PROJEK, E.ID_MAINMODUL, E.MAINMODUL, E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI, E.TARIKH_KEMASKINI, "+
					" E.TURUTAN FROM EP_MAINMODUL E " +
					" WHERE E.ID_MAINMODUL = '"+id_mainmodul+"' " +
							" AND E.ID_PROJEK = '"+id_projek+"' ";
			myLogger.info(" getItemMainmodul :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_MAINMODUL",rs.getString("ID_MAINMODUL") == null ? "" : rs.getString("ID_MAINMODUL"));
				h.put("MAINMODUL",rs.getString("MAINMODUL") == null ? "" : rs.getString("MAINMODUL"));
				h.put("TURUTAN",rs.getString("TURUTAN") == null ? "" : rs.getString("TURUTAN"));
				h.put("ID_PROJEK",rs.getString("ID_PROJEK") == null ? "" : rs.getString("ID_PROJEK"));
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
	
	
	public int countPenambahbaikanBySubmodul(HttpSession session, String id_submodul) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		int total = 0;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT COUNT(*) AS TOTAL_PENAMBAHBAIKAN FROM EP_PENAMBAHBAIKAN E WHERE E.ID_SUBMODUL = "+id_submodul+" ";
			myLogger.info(" get TOTAL_PENAMBAHBAIKAN :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				total = rs.getString("TOTAL_PENAMBAHBAIKAN") == null ? 0 : rs.getInt("TOTAL_PENAMBAHBAIKAN");
			}
			return total;
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
	public List listPIC(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPIC = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT E.ID_PIC, E.PIC, E.ID_MASUK, E.TARIKH_MASUK, E.ID_KEMASKINI, E.TARIKH_KEMASKINI FROM EP_PIC E ORDER BY PIC ASC ";
			myLogger.debug(" EP: SENARAI PIC :"+ sql);
			
			rs = stmt.executeQuery(sql);
			senaraiPIC = Collections.synchronizedList(new ArrayList());
			Map h = null;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_PIC",rs.getString("ID_PIC") == null ? "" : rs.getString("ID_PIC"));
				h.put("PIC",rs.getString("PIC") == null ? "" : rs.getString("PIC"));
				senaraiPIC.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return senaraiPIC;

	}
	
	public void updateItemPenambahbaikan(Hashtable data) throws Exception {
		myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String ID_PENAMBAHBAIKAN = (String) data.get("ID_PENAMBAHBAIKAN");
			String ID_PIC = (String) data.get("ID_PIC");
			String PENAMBAHBAIKAN = (String) data.get("PENAMBAHBAIKAN");
			
			String TARIKH_MULA = "to_date('" +(String) data.get("TARIKH_MULA")+ "','dd/MM/yyyy')";
			String TARIKH_TARGET_SIAP = "to_date('" +(String) data.get("TARIKH_TARGET_SIAP")+ "','dd/MM/yyyy')";
			String FS_LOCAL = (String) data.get("FS_LOCAL");
			String FS_STG = (String) data.get("FS_STG");
			String FS_DEV = (String) data.get("FS_DEV");
			String CATATAN = (String) data.get("CATATAN");
			String F_KIV = (String) data.get("F_KIV");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_PENAMBAHBAIKAN", ID_PENAMBAHBAIKAN);		
			r.add("ID_PIC", ID_PIC);
			r.add("PENAMBAHBAIKAN", PENAMBAHBAIKAN.toUpperCase());
			
			r.add("TARIKH_MULA", r.unquote(TARIKH_MULA));
			r.add("TARIKH_TARGET_SIAP", r.unquote(TARIKH_TARGET_SIAP));
			r.add("FS_LOCAL", FS_LOCAL);
			r.add("FS_STG", FS_STG);
			r.add("FS_DEV", FS_DEV);
			r.add("CATATAN", CATATAN.toUpperCase());
			r.add("F_KIV", F_KIV);
			
			
			sql = r.getSQLUpdate("EP_PENAMBAHBAIKAN");		
			System.out.println("UPDATE EP_PENAMBAHBAIKAN : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateItemSubmodul(Hashtable data) throws Exception {
		myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String ID_SUBMODUL = (String) data.get("ID_SUBMODUL");
			String SUBMODUL = (String) data.get("SUBMODUL");
			String TURUTAN = (String) data.get("TURUTAN");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_SUBMODUL", ID_SUBMODUL);		
			r.add("SUBMODUL", SUBMODUL.toUpperCase());
			//r.add("TURUTAN", TURUTAN);
			if(TURUTAN.equals(""))
			{
				r.add("TURUTAN", 1);
			}
			else
			{
				r.add("TURUTAN", TURUTAN);
			}
			sql = r.getSQLUpdate("EP_SUBMODUL");		
			myLogger.info("UPDATE EP_SUBMODUL : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateItemMainmodul(Hashtable data) throws Exception {
		myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String ID_MAINMODUL = (String) data.get("ID_MAINMODUL");
			String MAINMODUL = (String) data.get("MAINMODUL");
			String TURUTAN = (String) data.get("TURUTAN");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.update("ID_MAINMODUL", ID_MAINMODUL);		
			r.add("MAINMODUL", MAINMODUL.toUpperCase());
			if(TURUTAN.equals(""))
			{
				r.add("TURUTAN", 1);
			}
			else
			{
				r.add("TURUTAN", TURUTAN);
			}
			sql = r.getSQLUpdate("EP_MAINMODUL");		
			myLogger.info("UPDATE EP_MAINMODUL : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertItemMainmodul(Hashtable data) throws Exception {
		myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		long id_mainmodul = 0;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			id_mainmodul = DB.getNextID(db, "EP_MAINMODUL_SEQ");
			String MAINMODUL = (String) data.get("MAINMODUL");
			String TURUTAN = (String) data.get("TURUTAN");
			String ID_PROJEK = (String) data.get("ID_PROJEK");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_MAINMODUL", id_mainmodul);	
			r.add("MAINMODUL", MAINMODUL.toUpperCase());
			//r.add("TURUTAN", TURUTAN);
			if(TURUTAN.equals(""))
			{
				r.add("TURUTAN", 1);
			}
			else
			{
				r.add("TURUTAN", TURUTAN);
			}
			r.add("ID_PROJEK", ID_PROJEK);
			sql = r.getSQLInsert("EP_MAINMODUL");		
			myLogger.info("INSERT EP_MAINMODUL : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public void insertItemSubmodul(Hashtable data) throws Exception {
		myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		String sql = "";
		long id_submodul = 0;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			id_submodul = DB.getNextID(db, "EP_SUBMODUL_SEQ");
			String ID_MAINMODUL = (String) data.get("ID_MAINMODUL");
			String SUBMODUL = (String) data.get("SUBMODUL");
			String TURUTAN = (String) data.get("TURUTAN");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_SUBMODUL", id_submodul);	
			r.add("ID_MAINMODUL", ID_MAINMODUL);
			r.add("SUBMODUL", SUBMODUL.toUpperCase());
			if(TURUTAN.equals(""))
			{
				r.add("TURUTAN", 1);
			}
			else
			{
				r.add("TURUTAN", TURUTAN);
			}
			//r.add("TURUTAN", TURUTAN);
			sql = r.getSQLInsert("EP_SUBMODUL");		
			myLogger.info("INSERT EP_SUBMODUL : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public long insertItemPenambahbaikan(Hashtable data) throws Exception {
		myLogger.info("**** data : "+data);
		Connection conn = null;
		Db db = null;
		long id_penambahbaikan = 0;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			id_penambahbaikan = DB.getNextID(db, "EP_PENAMBAHBAIKAN_SEQ");
			String ID_SUBMODUL = (String) data.get("ID_SUBMODUL");
			String ID_PIC = (String) data.get("ID_PIC");
			String PENAMBAHBAIKAN = (String) data.get("PENAMBAHBAIKAN");
			String TURUTAN = (String) data.get("TURUTAN");
			
			String TARIKH_MULA = "to_date('" +(String) data.get("TARIKH_MULA")+ "','dd/MM/yyyy')";
			String TARIKH_TARGET_SIAP = "to_date('" +(String) data.get("TARIKH_TARGET_SIAP")+ "','dd/MM/yyyy')";
			String FS_LOCAL = (String) data.get("FS_LOCAL");
			String FS_STG = (String) data.get("FS_STG");
			String FS_DEV = (String) data.get("FS_DEV");
			String CATATAN = (String) data.get("CATATAN");
			String F_KIV = (String) data.get("F_KIV");
			
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_PENAMBAHBAIKAN", id_penambahbaikan);		
			r.add("ID_PIC", ID_PIC);
			r.add("PENAMBAHBAIKAN", PENAMBAHBAIKAN.toUpperCase());
			r.add("ID_SUBMODUL", ID_SUBMODUL);
			r.add("TURUTAN", TURUTAN);
			
			r.add("TARIKH_MULA", r.unquote(TARIKH_MULA));
			r.add("TARIKH_TARGET_SIAP", r.unquote(TARIKH_TARGET_SIAP));
			r.add("FS_LOCAL", FS_LOCAL);
			r.add("FS_STG", FS_STG);
			r.add("FS_DEV", FS_DEV);
			r.add("CATATAN", CATATAN.toUpperCase());
			r.add("F_KIV", F_KIV);
			
			
			sql = r.getSQLInsert("EP_PENAMBAHBAIKAN");		
			myLogger.info("UPDATE EP_PENAMBAHBAIKAN : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return id_penambahbaikan;
	}
	
	public void deletePenambahbaikan(String id_penambahbaikan) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			r.add("ID_PENAMBAHBAIKAN", id_penambahbaikan);
			sql = r.getSQLDelete("EP_PENAMBAHBAIKAN");
			stmt.executeUpdate(sql);
			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteSubmodul(String id_submodul) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_SUBMODUL", id_submodul);
			sql = r.getSQLDelete("EP_SUBMODUL");
			stmt.executeUpdate(sql);
			
			r.clear();
			r.add("ID_SUBMODUL", id_submodul);
			sql = r.getSQLDelete("EP_PENAMBAHBAIKAN");
			stmt.executeUpdate(sql);		
			
			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteMainmodul(String id_mainmodul) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "DELETE FROM EP_PENAMBAHBAIKAN WHERE ID_SUBMODUL IN (SELECT ID_SUBMODUL FROM EP_SUBMODUL WHERE ID_MAINMODUL = "+id_mainmodul+" )";
			myLogger.info("DELETE  EP_PENAMBAHBAIKAN : "+sql);
			stmt.executeUpdate(sql);	
			
			r.add("ID_MAINMODUL", id_mainmodul);
			sql = r.getSQLDelete("EP_MAINMODUL");
			myLogger.info("DELETE  EP_MAINMODUL : "+sql);
			stmt.executeUpdate(sql);
			
			r.clear();
			r.add("ID_MAINMODUL", id_mainmodul);
			sql = r.getSQLDelete("EP_SUBMODUL");
			myLogger.info("DELETE  EP_SUBMODUL : "+sql);
			stmt.executeUpdate(sql);
			
				
			
			conn.commit();
		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	
	
	//add razman attach
    public void hantarEmelWithAttachment(String content,String subjek) throws Exception {    	
    	//open razman add new feature : attachment in bytes
    	ServletContext application=getServletContext(); 
    	final Map<String, Object> myMap = new HashMap<String,Object>();
    	myMap.put("idfail", "249977");//parameter utk panggil report, boleh multiple    	
    	byte[] bytes1 = report.getReportBytes("ppk","KulitFail",request, response, application, myMap);
    	myMap.put("idfail", "69886");//parameter utk panggil report, boleh multiple
    	byte[] bytes2 = report.getReportBytes("ppk","KulitFail",request, response, application, myMap);
    	//close razman add new feature : attachment in bytes
    	
		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();		
		email.SUBJECT = subjek;
		email.MESSAGE = content;		
		email.MULTIPLE_RECIEPIENT = new String[1];		
		email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";
		
		
		////open razman add new feature : attachment in bytes
		email.ATTACHMENT_BYTES = new String[2];
		email.ATTACHMENT_BYTES[0] = new String(bytes1, "ISO-8859-1");;
		email.ATTACHMENT_BYTES[1] = new String(bytes2, "ISO-8859-1");;
		email.ATTACHMENT_BYTES_NAME = new String[2]; //kena sama dengan jumlah attachment
		email.ATTACHMENT_BYTES_NAME[0] = "Borang D 1";//letak nama file bersesuaian
		email.ATTACHMENT_BYTES_NAME[1] = "Borang D 2";
		//close razman add new feature : attachment in bytes		
		
		email.sendEmail();				 
	 }
    
    
    
	
	 public void hantarEmel(String content,String subjek) throws Exception {
		EmailProperty pro = EmailProperty.getInstance();
		//String EMAIL_HOST = pro.getHost_GM();
		EmailSender email = EmailSender.getInstance();
		//myLogger.info("EMAIL_HOST :"+EMAIL_HOST);
		//myLogger.info("EMAIL_FROM :"+pro.getFrom_GM());
		email.FROM = pro.getFrom();
		
		email.SUBJECT = subjek;
		email.MESSAGE = content;
		
		/*
		email.SUBJECT = "TEST";
		email.MESSAGE = "<html><table width='100%' border='1'><tr><td>column1</td><td>column2</td><td>column3</td></tr></table></html>";
		*/
		
		
		//email.RECIEPIENT = "razman@si-protech.com.my";
		
		
		email.MULTIPLE_RECIEPIENT = new String[5];
		email.MULTIPLE_RECIEPIENT[0] = "razman@si-protech.com.my";
		email.MULTIPLE_RECIEPIENT[1] = "nazzer@si-protech.com.my";
		email.MULTIPLE_RECIEPIENT[2] = "nik_rafizal@hla-group.com";
		email.MULTIPLE_RECIEPIENT[3] = "noorazam@hla-group.com";
		email.MULTIPLE_RECIEPIENT[4] = "shahrin@hla-group.com";
		
		/*
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";
		*/
		
		
		
		//email.MULTIPLE_RECIEPIENT[0] = "razman.zainal@gmail.com";	
		email.TO_CC = new String[1];		
		email.TO_CC[0] = "razman.zainal@gmail.com";
		email.sendEmail();		 
	 }
}
