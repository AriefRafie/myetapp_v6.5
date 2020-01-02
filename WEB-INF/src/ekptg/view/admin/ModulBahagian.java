package ekptg.view.admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

@SuppressWarnings("serial")
public class ModulBahagian extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(ekptg.view.admin.ModulBahagian.class);
	String skrin_name = "app/admin/ModulBahagian/index.jsp";
	
	@Override
	public String doTemplate2() throws Exception {
		
		List listBahagian = null;
		List list_TBLRUJSEKSYEN = null;
		
		List checklistModul = null;
		List listModulBhgn = null;
		
		HttpSession session = this.request.getSession();
		
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
		
		String command = getParam("command");
		myLogger.info("command : "+command);
		String FlagCari = getParam("FlagCari");
		this.context.put("FlagCari", FlagCari);
		myLogger.info("Page Bahagian command : "+command +" FlagCari : "+FlagCari);
		
		//initialize first
		this.context.put("carianBahagianHQ", "");
		this.context.put("FlagCari", "");
		
		String carianUmum = "";
		String carianBahagian = "";
		String carianUnitHQ = "";
		String carianBahagianHQ = "";
		String ID_NEGERI = "";
		
		
		String cetakReport = "";
		this.context.put("cetakReport", cetakReport);
		
		String action = getParam("action");
		
		//initiate
		this.context.put("list_TBLRUJSEKSYEN","");
		this.context.put("checklistModul", "");
		this.context.put("listTableModul","");
		this.context.put("listTableBahagian","");
		this.context.put("listModulBhgn", "");
		this.context.put("listTableBahagianModul", "");
		
		
		if(command.equals("batalCarianBahagian"))
		{
			skrin_name = "app/admin/ModulBahagian/index.jsp";		
		}
		else if (command.equals("showTable")){
			this.context.put("listTableModul",listTableModul(session));
			this.context.put("listTableBahagianModul", listTableBahagianModul(session));
			this.context.put("listTableBahagian",listTableBahagian(session));
			skrin_name = "app/admin/ModulBahagian/table.jsp";
		}
		
		else if (command.equals("showSelection")){
			String id_bahagian = getParam("id_bahagian");
			myLogger.info("id_bahagian : "+id_bahagian);
			checklistModul = listModul(session,id_bahagian);
			this.context.put("checklistModul", checklistModul);
			skrin_name = "app/admin/ModulBahagian/listModul.jsp";
		}
		else if (command.equals("simpan")){
			/*
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","0","0");
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);			
			checklistModul = listModul(session);
			this.context.put("checklistModul", checklistModul);
			*/
			String ID_MODULBAHAGIAN = getParam("ID_MODULBAHAGIAN");
			
			String[] CHECKLIST_ = request.getParameterValues("CHECKLIST_");
			
			int total_modul_update = 0;
			total_modul_update += saveModulByBahagian(session, ID_MODULBAHAGIAN, CHECKLIST_);
			
			listModulBhgn = listModulBahagian(session);
			this.context.put("listModulBhgn", listModulBhgn);
			
			skrin_name = "app/admin/ModulBahagian/SenaraiModulBahagian.jsp";
			
		}
		/*else if (command.equals("showModulSelected")){
			String ID_SEKSYEN = getParam
		}*/
		else {
			
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","0","0");
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
			
			
			
			listModulBhgn = listModulBahagian(session);
			this.context.put("listModulBhgn", listModulBhgn);
			
		}
		
	return skrin_name;	

	}
	
	//LIST TABLE
	@SuppressWarnings("unchecked")
	public List listTableRujukanV3(HttpSession session, String tableRujukan, String ID_NEGERI, String ID_DAERAH )throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukanV3 = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			if(tableRujukan.equals("TBLRUJJAWATAN"))
			{ 
				sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN " +
						" FROM TBLRUJJAWATAN WHERE  ID_JAWATAN != 0 ORDER BY KOD_JAWATAN";
			}
			
			else if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
			{
				sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
			}
			else if(tableRujukan.equals("TBLRUJBANGSA"))
			{
				sql = " SELECT ID_BANGSA AS ID, KOD_BANGSA AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANGSA WHERE ID_BANGSA IN (1,2,3,7) ORDER BY ID_BANGSA";
			}
			else if(tableRujukan.equals("TBLRUJSEKSYEN"))
			{
				sql = " SELECT ID_SEKSYEN AS ID, KOD_SEKSYEN AS KOD, UPPER(NAMA_SEKSYEN) AS KETERANGAN FROM TBLRUJSEKSYEN WHERE FLAG_AKTIF = 'Y' AND ID_SEKSYEN NOT IN (161728) ORDER BY ID_SEKSYEN";
			}
			else if(tableRujukan.equals("TBLRUJSUBUNIT"))
			{
				sql = " SELECT ID_SUBUNIT AS ID, ID_BAHAGIAN AS KOD, UPPER(NAMA_SUBUNIT) AS NAMA_SUBUNIT FROM TBLRUJSUBUNIT ORDER BY ID_SUBUNIT";
			}
			else if(tableRujukan.equals("TBLRUJKEMENTERIAN"))
			{
				sql = " SELECT ID_KEMENTERIAN AS ID, KOD_KEMENTERIAN AS KOD, UPPER(NAMA_KEMENTERIAN) AS KETERANGAN  FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN IS NOT NULL ORDER BY KOD_KEMENTERIAN ";
			}
			
			else if(tableRujukan.equals("TBLRUJNEGERI"))
			{
				sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
				sql += " WHERE ID_NEGERI IS NOT NULL ORDER BY KETERANGAN";
			
			}
			
			else if(tableRujukan.equals("TBLRUJBANDAR"))
			{
				sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR";
				sql += " WHERE ID_BANDAR IS NOT NULL ORDER BY KETERANGAN, KOD_BANDAR ";
			
			}
			
			else if(tableRujukan.equals("TBLRUJDAERAH"))
			{
				sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN FROM TBLRUJDAERAH ORDER BY NAMA_DAERAH, KOD_DAERAH ";
			}
			
			else if(tableRujukan.equals("TBLRUJJENISPEJABAT"))
			{
				sql = "  SELECT ID_JENISPEJABAT AS ID, KOD_JENIS_PEJABAT AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJENISPEJABAT ORDER BY KETERANGAN ";
			}
			
			else if(tableRujukan.equals("TBLRUJDAERAHBYNEGERI"))
			{
				sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN " +
						" FROM TBLRUJDAERAH " +
						" WHERE ID_NEGERI = "+ID_NEGERI+
						" ORDER BY NAMA_DAERAH, KOD_DAERAH ";
			
			}
			else if(tableRujukan.equals("TBLRUJBANDARBYNEGERI"))
			{
				sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR";
				sql += " WHERE ID_BANDAR IS NOT NULL";
				sql += " AND ID_NEGERI = "+ID_NEGERI+ " AND ID_DAERAH = "+ID_DAERAH;
				sql += " ORDER BY KETERANGAN, KOD_BANDAR ";
			
			}
			else if(tableRujukan.equals("TBLRUJJENISPEJABATBYID"))
			{
				sql = " SELECT ID_JENISPEJABAT AS ID, KOD_JENIS_PEJABAT AS KOD, UPPER(KETERANGAN) AS KETERANGAN " +
						" FROM TBLRUJJENISPEJABAT" +
						" WHERE ID_JENISPEJABAT = "+ ID_NEGERI+
						" ORDER BY KETERANGAN";
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
	
	@SuppressWarnings("unchecked")
	public List listModul(HttpSession session,String id_bahagian)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listModule = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT DISTINCT M.ID_MODUL, M.KOD_MODUL, M.NAMA_MODUL, M.ID_MASUK, MB.ID_MODULBAHAGIAN FROM TBLRUJMODUL M, TBLRUJMODULBAHAGIAN MB "+
					" WHERE M.ID_MODUL = MB.ID_MODUL(+) AND MB.ID_BAHAGIAN(+) = '"+id_bahagian+"' ORDER BY M.KOD_MODUL ";
			
			myLogger.info(" mm: SQL listModul :"+ sql);
			
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
				h.put("ID_MODUL",rs.getString("ID_MODUL") == null ? "" : rs.getString("ID_MODUL"));	
				h.put("KOD_MODUL",rs.getString("KOD_MODUL") == null ? "" : rs.getString("KOD_MODUL"));
				h.put("NAMA_MODUL",rs.getString("NAMA_MODUL") == null ? "" : rs.getString("NAMA_MODUL"));
				h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
				h.put("ID_MODULBAHAGIAN",rs.getString("ID_MODULBAHAGIAN") == null ? "" : rs.getString("ID_MODULBAHAGIAN"));	
			
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
	public List listModulBahagian(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listModule = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = 	" SELECT ID_BAHAGIAN, NAMA_SEKSYEN, COUNT(ID_MODULBAHAGIAN) AS JUMLAH FROM ( " +
					" SELECT A.ID_MODULBAHAGIAN, A.ID_MODUL, A.ID_BAHAGIAN, S.NAMA_SEKSYEN " +
					" FROM TBLRUJMODULBAHAGIAN A, TBLRUJSEKSYEN S " +
					" WHERE A.ID_BAHAGIAN = S.ID_SEKSYEN  )" +
					" GROUP BY ID_BAHAGIAN, NAMA_SEKSYEN ";
			
			myLogger.info(" mm: SQL LISTMODUL :"+ sql);
			
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
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));	
				h.put("JUMLAH",rs.getString("JUMLAH") == null ? "" : rs.getString("JUMLAH"));
				h.put("ID_BAHAGIAN",rs.getString("ID_BAHAGIAN") == null ? "" : rs.getString("ID_BAHAGIAN"));
			
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
	
	public int saveModulByBahagian(HttpSession session, String ID_MODULBAHAGIAN, String[] CHECKLIST_) throws Exception {
		
		Connection conn = null;
		Db db = null;
		String sql = "";
		int total_role_update = 0;
		long ID_MODULBAHAGIAN2 = 0;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			
			String ID_BAHAGIAN = getParam("ID_SEKSYEN");
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if (!ID_BAHAGIAN.equals("")){
			
			sql = "DELETE FROM TBLRUJMODULBAHAGIAN WHERE ID_BAHAGIAN = "+ ID_BAHAGIAN;
			
			
			
			myLogger.info("DELETE ALL MODUL by BAHAGIAN : "+sql);
			stmt.executeUpdate(sql);	
			
			}
			
			if(CHECKLIST_ != null)
			{
			for (String ID_MODUL : CHECKLIST_) {
				
				myLogger.info(" ID_MODUL : "+ID_MODUL);			
				if(ID_MODUL!=null)
				{
					
					sql = "";
					r.clear();
					
					r.add("ID_MODUL", ID_MODUL);
					
					ID_MODULBAHAGIAN2 = DB.getNextID(db,"TBLRUJMODULBAHAGIAN_SEQ");
					
					r.add("ID_MODULBAHAGIAN",ID_MODULBAHAGIAN2);
					
					r.add("ID_BAHAGIAN", ID_BAHAGIAN);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("TBLRUJMODULBAHAGIAN");		
					
					myLogger.info("INSERT TBLRUJMODULBAHAGIAN : "+sql);
					
					stmt.executeUpdate(sql);
					total_role_update++;
						
				}
				ID_MODUL = "";
			}
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
	    	throw new Exception("Ralat insert modul bahagian : "+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return total_role_update;
	}
	
	/*

SELECT S.ID_SEKSYEN, S.KOD_BARU_SEKSYEN, S.KOD_SEKSYEN, S.NAMA_SEKSYEN, NVL(UR.TOT,0) AS TOTAL_PENGGUNA 
FROM TBLRUJSEKSYEN S, 
(SELECT UI.ID_SEKSYEN, COUNT(UI.USER_ID) AS TOT FROM USERS_INTERNAL UI, USERS U 
WHERE U.USER_ID = UI.USER_ID
AND (UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '') 
GROUP BY  UI.ID_SEKSYEN) UR
WHERE S.FLAG_AKTIF = 'Y' AND S.ID_SEKSYEN = UR.ID_SEKSYEN(+)
AND S.ID_SEKSYEN NOT IN (161728)

SELECT M.KOD_MODUL, M.NAMA_MODUL, COUNT(R.NAME) AS TOTAL_ROLE FROM TBLRUJMODUL M, PAGE_CSS P, ROLE R
WHERE M.ID_MODUL = P.MODUL_ID AND P.CSS_NAME = R.THEME
GROUP BY M.KOD_MODUL, M.NAMA_MODUL

	*/
	
	
	
	
	@SuppressWarnings("unchecked")
	public List listTableBahagian(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableBahagian = null;
		String sql = "";		
		try {
			db = new Db();
			stmt = db.getStatement();			
			sql = 	" SELECT S.ID_SEKSYEN, S.KOD_BARU_SEKSYEN, S.KOD_SEKSYEN, S.NAMA_SEKSYEN, NVL(UR.TOT,0) AS TOTAL_PENGGUNA "+
					" FROM TBLRUJSEKSYEN S,  "+
					" (SELECT UI.ID_SEKSYEN, COUNT(UI.USER_ID) AS TOT FROM USERS_INTERNAL UI, USERS U  "+
					" WHERE U.USER_ID = UI.USER_ID "+
					" AND (UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '')  "+
					" GROUP BY  UI.ID_SEKSYEN) UR "+
					" WHERE S.FLAG_AKTIF = 'Y' AND S.ID_SEKSYEN = UR.ID_SEKSYEN(+) "+
					" AND S.ID_SEKSYEN NOT IN (161728) ORDER BY S.KOD_SEKSYEN ";			
			myLogger.info(" mm: SQL listTableBahagian :"+ sql);			
			rs = stmt.executeQuery(sql);
			listTableBahagian = Collections.synchronizedList(new ArrayList());
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
				h.put("KOD_BARU_SEKSYEN",rs.getString("KOD_BARU_SEKSYEN") == null ? "" : rs.getString("KOD_BARU_SEKSYEN"));
				h.put("KOD_SEKSYEN",rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN"));
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				h.put("TOTAL_PENGGUNA",rs.getString("TOTAL_PENGGUNA") == null ? "" : rs.getString("TOTAL_PENGGUNA"));			
				listTableBahagian.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listTableBahagian;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listTableModul(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableModul = null;
		String sql = "";		
		try {
			db = new Db();
			stmt = db.getStatement();			
			sql = 	" SELECT M.ID_MODUL,M.KOD_MODUL, M.NAMA_MODUL, COUNT(R.NAME) AS TOTAL_ROLE FROM TBLRUJMODUL M, PAGE_CSS P, ROLE R "+
					" WHERE M.ID_MODUL = P.MODUL_ID AND P.CSS_NAME = R.THEME "+
					" GROUP BY M.ID_MODUL,M.KOD_MODUL, M.NAMA_MODUL ORDER BY M.KOD_MODUL ";			
			myLogger.info(" mm: SQL listTableModul :"+ sql);			
			rs = stmt.executeQuery(sql);
			listTableModul = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_MODUL",rs.getString("ID_MODUL") == null ? "" : rs.getString("ID_MODUL"));
				h.put("KOD_MODUL",rs.getString("KOD_MODUL") == null ? "" : rs.getString("KOD_MODUL"));	
				h.put("NAMA_MODUL",rs.getString("NAMA_MODUL") == null ? "" : rs.getString("NAMA_MODUL"));
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "" : rs.getString("TOTAL_ROLE"));			
				listTableModul.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listTableModul;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listTableBahagianModul(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableBahagianModul = null;
		String sql = "";		
		try {
			db = new Db();
			stmt = db.getStatement();
			/*
			sql = 	" SELECT MB.ID_MODULBAHAGIAN, S.ID_SEKSYEN, S.NAMA_SEKSYEN, S.KOD_SEKSYEN, MB.ID_MODUL, M.KOD_MODUL, M.NAMA_MODUL, UR.TOTAL_USER, MODUL.TOTAL_ROLE "+
					" FROM TBLRUJMODULBAHAGIAN MB, TBLRUJSEKSYEN S, TBLRUJMODUL M,  "+
					" (SELECT UI.ID_SEKSYEN, COUNT(UI.USER_ID) AS TOTAL_USER FROM USERS_INTERNAL UI, USERS U  "+
					" WHERE U.USER_ID = UI.USER_ID "+
					" AND (UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '')  "+
					" GROUP BY  UI.ID_SEKSYEN) UR, (SELECT M.ID_MODUL, M.KOD_MODUL, M.NAMA_MODUL, COUNT(R.NAME) AS TOTAL_ROLE FROM TBLRUJMODUL M, PAGE_CSS P, ROLE R "+
					" WHERE M.ID_MODUL = P.MODUL_ID AND P.CSS_NAME = R.THEME "+
					" GROUP BY M.ID_MODUL, M.KOD_MODUL, M.NAMA_MODUL) MODUL "+
					" WHERE S.ID_SEKSYEN = MB.ID_BAHAGIAN(+) AND S.FLAG_AKTIF = 'Y' AND S.ID_SEKSYEN NOT IN (161728) "+
					" AND MB.ID_MODUL = M.ID_MODUL(+) AND S.ID_SEKSYEN = UR.ID_SEKSYEN(+) AND M.ID_MODUL = MODUL.ID_MODUL(+)  "+
					" ORDER BY S.KOD_SEKSYEN, M.KOD_MODUL ";
					*/
			
			sql = "SELECT ID_MODUL, ID_BAHAGIAN  FROM TBLRUJMODULBAHAGIAN";
			myLogger.info(" mm: SQL listTableBahagianModul :"+ sql);			
			rs = stmt.executeQuery(sql);
			listTableBahagianModul = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_MODUL",rs.getString("ID_MODUL") == null ? "" : rs.getString("ID_MODUL"));	
				h.put("ID_BAHAGIAN",rs.getString("ID_BAHAGIAN") == null ? "" : rs.getString("ID_BAHAGIAN"));	
				/*
				h.put("ID_MODULBAHAGIAN",rs.getString("ID_MODULBAHAGIAN") == null ? "" : rs.getString("ID_MODULBAHAGIAN"));	
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));	
				h.put("KOD_SEKSYEN",rs.getString("KOD_SEKSYEN") == null ? "" : rs.getString("KOD_SEKSYEN"));
				h.put("ID_MODUL",rs.getString("ID_MODUL") == null ? "" : rs.getString("ID_MODUL"));
				h.put("KOD_MODUL",rs.getString("KOD_MODUL") == null ? "" : rs.getString("KOD_MODUL"));
				h.put("NAMA_MODUL",rs.getString("NAMA_MODUL") == null ? "" : rs.getString("NAMA_MODUL"));
				h.put("TOTAL_USER",rs.getString("TOTAL_USER") == null ? "" : rs.getString("TOTAL_USER"));
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "" : rs.getString("TOTAL_ROLE"));
				*/
				listTableBahagianModul.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listTableBahagianModul;
	}

}
