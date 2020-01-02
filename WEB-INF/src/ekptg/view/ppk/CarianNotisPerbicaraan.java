package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging2;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;

public class CarianNotisPerbicaraan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(CarianNotisPerbicaraan.class);
	String skrin_name = "app/ppk/notisP/NPindex.jsp";
	
	
	// model
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	
	@Override
	public String doTemplate2() throws Exception {
		
		List listNP_forPrint = null;
		List listTT = null;
		List listPMT = null;
		List listNP = null;
		List listNP_Print = null;
		List listDates = null;
		List listLogOT = null;
		Hashtable viewTT = null;
		Hashtable viewPMT = null;
		Hashtable viewBU = null;
		List listAktiviti = null;
		List listNegeri = null;
		List listUnit = null;
		List listPelulus = null;
		boolean checkTransaksiTT = true;
		boolean checkTransaksiPMT = true;
		boolean checkTransaksiBU = true;
		boolean checkDB = true;
		boolean checkAllTT = true;
		boolean checkAllPMT = true;
		boolean checkAllBU = true;
		String checkSyntaxQuery = "";
		Vector list = new Vector();

		
		
		HttpSession session = this.request.getSession();
		
		list.clear();


		
		
		defaultPut();
		
		
		String command = getParam("command");
		myLogger.info("OT command : "+command);
		this.context.put("command",command);
		String USER_ROLE = (String) session.getAttribute("myrole");
		this.context.put("USER_ROLE",USER_ROLE);
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		this.context.put("USER_UNIT",USER_UNIT);	
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);
		
		
		String usid =  (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid",usid);
		
		
		if(command.equals("showSenaraiNP_Print"))
		{			
			listNP = listNP(session);
			this.context.put("listNP",listNP);
			this.context.put("listNP_forPrint",listNP);		
			
			String action = getParam("action");
			setupPage(session,action,list);
			 
			 //vm = "app/ppk/printKIV.jsp";
			
			skrin_name = "app/ppk/notisP/NPSenaraiNP_Print.jsp";
		}
		else if(command.equals("showSenaraiNP"))
		{			
			
			
			this.context.put("FLAG_NP_CARIAN", getParam("FLAG_NP_CARIAN"));
			this.context.put("NP_OPENCLOSE_CARIAN",getParam("NP_OPENCLOSE_CARIAN"));
			String action = getParam("action");
			listNP = listNP(session);
			setupPageList(session, action, listNP, "listNP",command,"div_SenaraiNP");
			
			skrin_name = "app/ppk/notisP/NPSenaraiNP.jsp";
		}
		else if(command.equals("showCARIAN_NP"))
		{
			
			this.context.put("FLAG_NP_CARIAN", getParam("FLAG_NP_CARIAN"));
			this.context.put("NP_OPENCLOSE_CARIAN",getParam("NP_OPENCLOSE_CARIAN"));
		
			skrin_name = "app/ppk/notisP/NPskrinCarianNP.jsp";
		}
		
		
		myLogger.info(" OT - VM :"+skrin_name);
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
		this.context.put("FLAG_TT_CARIAN", "");
		this.context.put("TT_OPENCLOSE_CARIAN","");
		this.context.put("TT_NAMA_AKTIVITI","");
		this.context.put("TT_NAMA_TABLE","");
		this.context.put("TT_NAMA_FIELD","");
		this.context.put("TT_SEKSYEN","");
		this.context.put("viewTT", "");
		this.context.put("rowCss", "");
		this.context.put("BIL", "");
		
		this.context.put("FLAG_PMT_CARIAN", "");
		this.context.put("PMT_OPENCLOSE_CARIAN","");
		this.context.put("PMT_NAMA_AKTIVITI","");
		this.context.put("listAktiviti","");	
		this.context.put("checkTransaksiPMT", "");
		this.context.put("checkTransaksiPMT_msg", "");
		
		this.context.put("FLAG_NP_CARIAN", "");
		this.context.put("NP_OPENCLOSE_CARIAN","");
		this.context.put("BU_NAMA_AKTIVITI","");
		this.context.put("listNP_forPrint", "");
		
	}
	
	
	
	
	
	public String getUserFullName(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String fulname="";
			sql = " SELECT UPPER(USER_NAME) AS FULLNAME FROM USERS WHERE USER_ID = '"+USER_ID+"'";				
				myLogger.info(" OT : getUserFullName :" + sql.toUpperCase());
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
	
	
	
	@SuppressWarnings("unchecked")
	
	public String sqlNotisPerbicaraan(HttpSession session)
	{
		String sql = " SELECT DISTINCT F.NO_FAIL, SM.NAMA_SIMATI, S.KETERANGAN, " +
				" MAX(TO_CHAR(B.TARIKH_NOTIS,'DD/MM/YYYY')) AS TARIKH_NOTIS, MAX(B.BIL_BICARA) AS BIL_BICARA, COUNT(B.ID_PERBICARAAN) AS C_ID_PERBICARAAN ," +
				" P.ID_PERMOHONAN, P.ID_STATUS, sm.ID_SIMATI, p.SEKSYEN " +
				" FROM TBLPPKPERMOHONAN P, TBLPFDFAIL F, TBLPPKPERBICARAAN B, TBLPPKSIMATI SM, " +
				" TBLPPKPERMOHONANSIMATI PSM, TBLPPKKEPUTUSANPERMOHONAN KP, TBLRUJSTATUS S " +
				" WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN " +
				" AND KP.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN " +
				" AND PSM.ID_PERMOHONAN = P.ID_PERMOHONAN AND PSM.ID_SIMATI = SM.ID_SIMATI " +
				" AND P.ID_STATUS IN (18,44) AND P.ID_STATUS = S.ID_STATUS ";

		return sql;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listNP(HttpSession session)throws Exception {
		
		String FLAG_NP_CARIAN = getParam("FLAG_NP_CARIAN");
		//myLogger.info("-------- FLAG_NP_CARIAN : "+FLAG_NP_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		
		//myLogger.info("-------- USER_ID_SYSTEM : "+USER_ID_SYSTEM+" USER_ROLE : "+USER_ROLE+" USER_NEGERI : "+USER_NEGERI);
		
		String NO_FAIL = getParam("NO_FAIL");
		String TARIKH_DARI = getParam("TARIKH_DARI");
		String TARIKH_HINGGA = getParam("TARIKH_HINGGA");
		
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listNP = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
							
			sql = sqlNotisPerbicaraan(session);
					
			if(FLAG_NP_CARIAN.equals("Y"))
			{
				if(!NO_FAIL.equals(""))
				{
					sql += " AND F.NO_FAIL = '"+NO_FAIL+"' ";
					
				}
				
				
				
				if(!TARIKH_DARI.equals("") && !TARIKH_HINGGA.equals(""))
				{
					sql+= "  AND TARIKH_NOTIS between TO_DATE('"+TARIKH_DARI+"', 'DD/MM/YYYY') and TO_DATE('"+TARIKH_HINGGA+"', 'DD/MM/YYYY')";
					
				
				}
				else
				{
					if(!TARIKH_DARI.equals(""))
					{
						sql+= " and TARIKH_NOTIS = TO_DATE('"+TARIKH_DARI+"', 'DD/MM/YYYY') ";
					}
					else if(!TARIKH_HINGGA.equals(""))
					{
						sql+= " and TARIKH_NOTIS = TO_DATE('"+TARIKH_HINGGA+"', 'DD/MM/YYYY') ";
					}
				}		
				this.context.put("NO_FAIL",NO_FAIL.toUpperCase());
				this.context.put("TARIKH_DARI",TARIKH_DARI.toUpperCase());
				this.context.put("TARIKH_HINGGA",TARIKH_HINGGA.toUpperCase());

			}
			else
			{				
				this.context.put("NO_FAIL","");
				this.context.put("TARIKH_DARI","");
				this.context.put("TARIKH_HINGGA","");
						
			}
			
			if(USER_ROLE.equals("user_ppk"))
			{
				//sql += " AND PM.USER_ID = '"+USER_ID_SYSTEM+"' ";
				sql += "AND  p.id_daerahmhn IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR " +
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
				+ USER_ID_SYSTEM
				+ "' ";
				
				 sql += " UNION "+                                                                            
				" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
				" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
				" WHERE ID_STATUS = 2  "+ 
				" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
				" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
				" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
				" AND PBU.ID_PEMOHON = '"+USER_ID_SYSTEM+"'  )";
			}
			else if(USER_ROLE.equals("adminppk"))
			{
				sql += "AND  f.ID_NEGERI in( SELECT DISTINCT u.ID_NEGERI " +
						" FROM tblrujpejabaturusan u, users_internal ur " +
						" WHERE u.id_pejabatjkptg = ur.id_pejabatjkptg " +
						" AND ur.user_id = '"+USER_ID_SYSTEM+"') ";
			}
			sql += " GROUP BY F.NO_FAIL, SM.NAMA_SIMATI, S.KETERANGAN, P.ID_PERMOHONAN, P.ID_STATUS, sm.ID_SIMATI, p.SEKSYEN ";
			sql += " ORDER BY P.ID_PERMOHONAN desc, TARIKH_NOTIS desc ";
					
					
			myLogger.info(" OT : SQL listNP :"+ sql);
			System.out.println("carianNotisPerbicaraan : "+sql);
			rs = stmt.executeQuery(sql);
			listNP = Collections.synchronizedList(new ArrayList());
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
				
				
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NAMA_SIMATI",rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("TARIKH_NOTIS",rs.getString("TARIKH_NOTIS") == null ? "" : rs.getString("TARIKH_NOTIS"));
				h.put("BIL_BICARA",rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));				
				h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("ID_SIMATI",rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI"));
				h.put("SEKSYEN",rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
				
				
				listNP.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listNP;

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
		
	
		 
		 
}