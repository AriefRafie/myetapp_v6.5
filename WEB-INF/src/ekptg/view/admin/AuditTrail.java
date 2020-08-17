package ekptg.view.admin;

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
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging2;

@SuppressWarnings("serial")
public class AuditTrail extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(ekptg.view.admin.AuditTrail.class);
	String skrin_name = "app/admin/AuditTrail/index.jsp";
	
	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		List auditTrail = null;		
		String command = getParam("command");
		
		//Initialize
		this.context.put("carianAT","");
		this.context.put("TARIKH_MULA_AT","");
		this.context.put("TARIKH_AKHIR_AT", "");
		this.context.put("NO_FAIL", "");
		this.context.put("txtKeterangan", "");
		this.context.put("jenis_log", "");
		this.context.put("jenis_aktiviti", "");
		this.context.put("list_seksyen", getListSeksyen());
		
		myLogger.info("Page Audit Trail command : "+command );
		String action = getParam("action");
		
		if(command.equals("batalCarian"))
		{
			skrin_name = "app/admin/AuditTrail/index.jsp";
		
		}
		else if(command.equals("showSenaraiAT"))
		{
			auditTrail = getAuditTrail(session);
			//this.context.put("auditTrail", auditTrail);
			setupPageList(session, action, auditTrail, "auditTrail",command,"div_ListAT");
			skrin_name = "app/admin/AuditTrail/SenaraiAT.jsp";
		}
		else if(command.equals("printAT"))
		{
			
			auditTrail = getAuditTrail(session);
			this.context.put("auditTrail", auditTrail);
			skrin_name = "app/admin/AuditTrail/SenaraiPrintAT.jsp";
		}
		else 
		{
			auditTrail = getAuditTrail(session);
			//this.context.put("auditTrail", auditTrail);
			setupPageList(session, action, auditTrail, "auditTrail","showSenaraiAT","div_ListAT");
			skrin_name = "app/admin/AuditTrail/index.jsp";
		}
		
		return skrin_name;	
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getAuditTrail(HttpSession session) throws Exception {
				
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listAudit = null;
		String sql = "";
		boolean setLimit = true;
		
		String keterangan = getParam("txtKeterangan");
		String ip = getParam("txtIP");
		String user = getParam("txtUser");
		String tarikh_aktiviti = getParam("txtTarikhAktiviti");
		String jenis_log = getParam("jenis_log");
		String id_seksyen = getParam("id_seksyen");
		String jenis_aktiviti = getParam("jenis_aktiviti");
		String id_status = getParam("id_status");
		String id_suburusan = getParam("id_suburusan");
		String NO_FAIL = getParam("NO_FAIL");
		//penambahan admin 4/1/2017
		String log_date = getParam("log_date");
		String flag_aktif = getParam("flag_aktif");
		
		
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT " +
					//"NO_FAIL, NAMA_SUBURUSAN, NAMA_STATUS, ID_SEKSYEN, SEKSYEN, MODULE_NAME, KETERANGAN, JENIS_AKTIVITI," +
					//" IP_ADDRESS,TARIKH_KEMASKINI,MASA_AKTIVITI,USER_NAME,DOT, FLAG_AKTIF  "
					" * " +
					" FROM ( ";
			
			sql += "SELECT REPLACE ( NVL ( ";
			
			sql += " (CASE WHEN (A.KETERANGAN LIKE '%FAIL [%' OR A.KETERANGAN LIKE '%FAIL PERMOHONAN [%' " +
					" OR A.KETERANGAN LIKE '% [%') THEN  SUBSTR(SUBSTR(A.KETERANGAN, 1, INSTR(A.KETERANGAN, ']') - 1) , "+
					" INSTR(SUBSTR(A.KETERANGAN, 1, INSTR(A.KETERANGAN, ']') - 1) , '[') + 1) "+
					" ELSE '' END),''),'null','')AS NO_FAIL,";	
			
			sql+= " SU.NAMA_SUBURUSAN as nama_suburusan,ST.keterangan as nama_status,S.ID_SEKSYEN, " +
					"S.NAMA_SEKSYEN AS seksyen,A.module_Name,A.keterangan,A.jenis_aktiviti, " +
				  "A.ip_address,A.TARIKH_KEMASKINI," +
				  "to_char(A.tarikh_aktiviti,'DD/MM/YYYY HH:MI AM') as masa_aktiviti," +
				  "B.user_name, TO_CHAR(MAX(WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') as dot, " +
				  " CASE b.FLAG_AKTIF WHEN '1' THEN 'AKTIF' WHEN '2' THEN 'TIDAK AKTIF' ELSE 'AKTIF'  END AS FLAG_AKTIF " +
				  " from tblaudittrail A,users B,tblrujseksyen S,tblrujstatus ST,tblrujsuburusan SU,WEB_LOGGER WL " +
				  "WHERE A.ID_MASUK = B.USER_ID " +
				  "AND A.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
				  "AND A.ID_STATUS = ST.ID_STATUS(+) " +
				  "AND A.id_suburusan = SU.id_suburusan(+) " +
				  "AND B.USER_LOGIN = WL.USER_NAME " ;
			
			if (NO_FAIL != null && !NO_FAIL.equals("")) {
				setLimit = false;
				sql = sql + "AND lower(A.keterangan) like '%"+NO_FAIL.toLowerCase()+"%' ";
			}
			if (keterangan != null && !keterangan.equals("")) {
				setLimit = false;
				sql = sql + "AND lower(A.keterangan) like '%"+keterangan.toLowerCase()+"%' ";
			}
			if (ip != null && !ip.equals("") ) {
				setLimit = false;
				sql = sql + "AND lower(A.ip_address) like '%"+ip.toLowerCase()+"%' ";
			}
			if (user != null && !user.equals("")) {
				setLimit = false;
				sql = sql + "AND lower(B.user_name) like '%"+user.toLowerCase()+"%' ";
			}
			
			if (jenis_aktiviti != null && !jenis_aktiviti.equals("")) {
				setLimit = false;
				sql = sql + "AND A.JENIS_AKTIVITI = '"+jenis_aktiviti+"' ";
			}
			
			if (jenis_log != null && !jenis_log.equals("")) {
				setLimit = false;
				sql = sql + "AND lower(A.keterangan) like '%"+jenis_log.toLowerCase()+"%' ";
			}
			
			if (id_seksyen != null && !id_seksyen.equals("")) 
			{
				setLimit = false;
				sql = sql + "AND A.id_seksyen = '"+id_seksyen+"' ";
			}
			
			if (id_status != null && !id_status.equals("")) 
			{
				setLimit = false;
				sql = sql + "AND A.id_status = '"+id_status+"' ";
			}
			
			
			if (id_suburusan != null && !id_suburusan.equals("")) 
			{
				setLimit = false;
				sql = sql + "AND A.id_suburusan = '"+id_suburusan+"' ";
			}
			
			
			if (tarikh_aktiviti != null && !tarikh_aktiviti.equals("")) {
				if (!tarikh_aktiviti.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND REPLACE(TO_CHAR(A.tarikh_aktiviti,'dd/MM/YYYY'),' ','') = '" + tarikh_aktiviti +"' ";
				}
			}
			//default			
			if (setLimit) {	//RESERVED BY AZAM
					//sql = sql + " AND ROWNUM <= 2000 ";				
			}	
			sql = sql + "GROUP BY SU.NAMA_SUBURUSAN,ST.keterangan, S.ID_SEKSYEN, S.NAMA_SEKSYEN, A.keterangan,  A.module_Name,  A.jenis_aktiviti,  A.ip_address,A.tarikh_aktiviti, B.user_name,A.TARIKH_KEMASKINI,b.FLAG_AKTIF " ;	

			
			sql = sql + " ORDER BY  A.tarikh_kemaskini DESC";	
			
			sql += ") WHERE ROWNUM < 1000 ";
			
			myLogger.debug("AUDIT TRAIL ADMIN : "+sql);
					 rs = stmt.executeQuery(sql);
					
					 listAudit = Collections.synchronizedList(new ArrayList());
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
						h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
						h.put("nama_suburusan",checkIsNull(rs.getString("nama_suburusan")));
						h.put("nama_status",checkIsNull(rs.getString("nama_status")));
						h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYEN")));
						h.put("seksyen",checkIsNull(rs.getString("seksyen")));
						h.put("module_Name",checkIsNull(rs.getString("module_Name")));
						h.put("keterangan",checkIsNull(rs.getString("keterangan")));
						h.put("jenis_aktiviti",checkIsNull(rs.getString("jenis_aktiviti")));
						h.put("ip_address",checkIsNull(rs.getString("ip_address")));
						
						h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
						h.put("masa_aktiviti",checkIsNull(rs.getString("masa_aktiviti")));
						
						h.put("user_name",checkIsNull(rs.getString("user_name")));
						h.put("dot",checkIsNull(rs.getString("dot")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						
						listAudit.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listAudit;
				
	}
	
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	Vector list_seksyen = null;
	public Vector getListSeksyen() throws Exception {
		list_seksyen = new Vector();
		list_seksyen.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT ID_SEKSYEN,KOD_SEKSYEN,NAMA_SEKSYEN FROM TBLRUJSEKSYEN WHERE FLAG_AKTIF = 'Y'" +
					" ORDER BY ID_SEKSYEN ";//WHERE ID_SEKSYEN IN ('1','2','3','4','6') ";
			
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				if (rs.getString("KOD_SEKSYEN") == null) {
					h.put("kod_seksyen", "");
				} else {
					h.put("kod_seksyen", rs.getString("KOD_SEKSYEN"));
				}
				if (rs.getString("NAMA_SEKSYEN") == null) {
					h.put("seksyen", "");
				} else {
					h.put("seksyen", rs.getString("NAMA_SEKSYEN"));
				}
				if (rs.getString("ID_SEKSYEN") == null) {
					h.put("id_seksyen", "");
				} else {
					h.put("id_seksyen", rs.getString("ID_SEKSYEN"));
				}
				list_seksyen.addElement(h);
			}
			return list_seksyen;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
		try {
			myLogger.info("command : "+command);
			setupPageDefaultPut();
			String scrolPosition = getParam("scrolPosition"+command);
			//myLog.info("scrolPosition pejurusan: "+scrolPosition);
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
				myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
				itemsPerPage = getParam("itemsPerPage"+command) == "" ? 10
						: getParamAsInteger("itemsPerPage"+command);
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
			}
			
			myLogger.info(" itemsPerPage *** : "+itemsPerPage);
			
			if (("getNext").equals(action)) {
				page++;
			} else if (("getPrevious").equals(action)) {
				page--;
			} else if (("getPage").equals(action)) {
				page = getParamAsInteger("value");
			} else if (("doChangeItemPerPage").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage"+command);
			}
			//myLog.info(" **** itemsPerPage : "+itemsPerPage+"  list :"+list);
			
			Paging2 paging = new Paging2(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1;
			//myLog.info(" namaList : "+namaList+" #### page : "+page+"  list :"+paging.getPage(page));
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
	
	public void setupPageListSmall(HttpSession session, String action, List list, String namaList, String command, String div) {
		try {
			myLogger.info("command : "+command);
			setupPageDefaultPut();
			String scrolPosition = getParam("scrolPosition"+command);
			//myLog.info("scrolPosition pejurusan: "+scrolPosition);
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
				itemsPerPage = getParam("itemsPerPage"+command) == "" ? 5
						: getParamAsInteger("itemsPerPage"+command);
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
			}
			
			//myLogger.info(" itemsPerPage *** : "+itemsPerPage);
			
			if (("getNext").equals(action)) {
				page++;
			} else if (("getPrevious").equals(action)) {
				page--;
			} else if (("getPage").equals(action)) {
				page = getParamAsInteger("value");
			} else if (("doChangeItemPerPage").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage"+command);
			}
			//myLog.info(" **** itemsPerPage : "+itemsPerPage+"  list :"+list);
			
			Paging2 paging = new Paging2(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1;
			//myLog.info(" namaList : "+namaList+" #### page : "+page+"  list :"+paging.getPage(page));
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
	
	public void setupPageDefaultPut(){
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
	
	
	
}
