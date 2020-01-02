/**
 * 
 */
package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging2;
import ekptg.model.ppk.FrmSenaraiPermohonanSeksyen8Data;


/**
 * 
 *
 */

public class FrmSenaraiEditPerintah extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiEditPerintah.class);

	FrmSenaraiPermohonanSeksyen8Data logic = null;
	//Vector list = null;
	List list = null;
	
	//String skrin_name = "app/ppk/perintah/index.jsp";	
	String skrin_name = "app/ppk/frmSenaraiEditPerintah.jsp";	

	@Override
	public String doTemplate2() throws Exception {
		logic = new FrmSenaraiPermohonanSeksyen8Data();
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String action = getParam("action");
		myLogger.info(" command :"+ command);
		String myIdSimati = "";
		String noFail = "";
		
		this.context.put("myIdSimati","");
		this.context.put("noFail","");
		this.context.put("SenaraiFail","");
		
		context.put("totalRecords", "");
        context.put("SenaraiFail", "");
        context.put("page","");
        context.put("itemsPerPage","");
        context.put("totalPages", "");
        context.put("startNumber", "");
        context.put("isFirstPage","");
        context.put("isLastPage", Boolean.valueOf(true));
		
		if(command.equals("carianUtama"))
		{
			 myIdSimati = getParam("myIdSimati");
			 noFail = getParam("noFail");
			
			 this.context.put("myIdSimati",myIdSimati);
			 this.context.put("noFail",noFail);
			
			// myLogger.info(" 888888888888888888888888888");
			//String carianTerperinci = getParam("carianTerperinci");
			list = listKemaskiniPerintah(session, myIdSimati,noFail);
			
			///this.context.put("SenaraiFail",list);
			skrin_name = "app/ppk/frmSenaraiEditPerintah.jsp";
			
			setupPageSEP(session,action,list);
			
			//skrin_name = "app/ppk/perintah/SenaraiUtama.jsp";
		}
		
		myLogger.info(" skrin_name :"+ skrin_name);
		return skrin_name;
	}
	protected void setupPageSEP(HttpSession session, String action, List lists)
    {
		myLogger.info(" ************** action :"+ action);
        if(lists == null) {
            context.put("totalRecords", Integer.valueOf(0));
            context.put("SenaraiFail", "");
            context.put("page", Integer.valueOf(0));
            context.put("itemsPerPage", Integer.valueOf(0));
            context.put("totalPages", Integer.valueOf(0));
            context.put("startNumber", Integer.valueOf(0));
            context.put("isFirstPage", Boolean.valueOf(true));
            context.put("isLastPage", Boolean.valueOf(true));
        } else {
            context.put("totalRecords", Integer.valueOf(lists.size()));
            int page = getParam("page") != "" ? getParamAsInteger("page") : 1;
            int itemsPerPage;
            if(context.get("itemsPerPage") == null || "".equals(context.get("itemsPerPage")) || "0".equals(context.get("itemsPerPage")))
                itemsPerPage = getParam("itemsPerPage") != "" ? getParamAsInteger("itemsPerPage") : 10;
            else
                itemsPerPage = ((Integer)context.get("itemsPerPage")).intValue();
            if("getNext".equals(action))
                page++;
            else
            if("getPrevious".equals(action))
                page--;
            else
            if("getPage".equals(action))
                page = getParamAsInteger("value");
            else
            if("doChangeItemPerPage".equals(action))
                itemsPerPage = getParamAsInteger("itemsPerPage");
            if(itemsPerPage == 0)
                itemsPerPage = 10;
            Paging2 paging = new Paging2(session, lists, itemsPerPage);
            if(page > paging.getTotalPages())
                page = 1;
            context.put("SenaraiFail", paging.getPage(page));
            context.put("page", new Integer(page));
            context.put("itemsPerPage", new Integer(itemsPerPage));
            context.put("totalPages", new Integer(paging.getTotalPages()));
            context.put("startNumber", new Integer(paging.getTopNumber()));
            context.put("isFirstPage", new Boolean(paging.isFirstPage()));
            context.put("isLastPage", new Boolean(paging.isLastPage()));
        }
    }
	
	public List listKemaskiniPerintah(HttpSession session, String nokp, String noFail)throws Exception {
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listModule = null;
			String sql = "";
			String condition = "";
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				if (noFail != null) {
					if (!noFail.trim().equals("")) {
					condition += "AND ( trim(F.NO_FAIL) LIKE '%"+noFail.toUpperCase()+"%' )" ;	
					}
				}	
				if (nokp != null) {
					if (!nokp.trim().equals("")) {
					condition += " AND (trim(S.NO_KP_BARU) LIKE '%"+nokp.toUpperCase()+"%' " +
								 " OR trim(S.NO_KP_LAMA) LIKE '%"+nokp.toUpperCase()+"%' " +
								 " OR trim(S.NO_KP_LAIN) LIKE '%"+nokp.toUpperCase()+"%' ) " ;
					}
				}	
				
				sql = " SELECT ST.KETERANGAN AS NAMA_STATUS,F.ID_FAIL,F.NO_FAIL,P.ID_PERMOHONAN, " +
					  " TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON,S.NAMA_SIMATI,PM.NAMA_PEMOHON , " +
					  " P.USER_ID_KEBENARAN_EDIT, P.FLAG_KEBENARAN_EDIT , " +
					  " US.USER_NAME," +
					  " TA.USER_ID, " +
					  " '' as user_id, '' as user_name, "+
					  "P.ID_STATUS, P.SEKSYEN, PS.ID_PERMOHONANSIMATI " +
					  " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM," +
					  " TBLRUJSTATUS ST " +
					  ", USERS US" +
					  ", TBLEDITAGIHAN TA " +
					  " WHERE F.ID_FAIL = P.ID_FAIL  AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN  " +	
					  //" AND P.FLAG_KEBENARAN_EDIT = 1" +
					  " AND S.ID_SIMATI = PS.ID_SIMATI  AND P.ID_PEMOHON = PM.ID_PEMOHON  " +
					  " AND P.ID_STATUS = ST.ID_STATUS  " +
					  " AND FLAG_EDIT_PERINTAH =1 " +
					  " AND US.USER_ID = TA.USER_ID " +
					  " AND TA.ID_FAIL = P.ID_FAIL " +
					  condition +
					 " AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur  " +
					  " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= '"+user_id+"' ";
					  
					   sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+user_id+"  ";
					  
					  sql += " ) " +
					  " ORDER BY P.TARIKH_MOHON ";
				
				
				myLogger.info(" mm: SQL listKemaskiniPerintah :"+ sql);
				
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
					h.put("NAMA_STATUS",rs.getString("NAMA_STATUS") == null ? "" : rs.getString("NAMA_STATUS"));	
					h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
					h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
					h.put("NAMA_SIMATI",rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI"));
					h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
					h.put("USER_ID_KEBENARAN_EDIT",rs.getString("USER_ID_KEBENARAN_EDIT") == null ? "" : rs.getInt("USER_ID_KEBENARAN_EDIT"));
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
					h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("SEKSYEN",rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
					h.put("ID_PERMOHONANSIMATI",rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI"));
					
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
	



}
