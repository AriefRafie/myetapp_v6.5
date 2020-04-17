package ekptg.view.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class FrmAuditTrail extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmAuditTrail.class);
	public String doTemplate2() throws Exception {
		
		
		myLogger.info("COMMAND :"+getParam("command"));
		
		HttpSession session = this.request.getSession();
		String submit = getParam("command"); // First Level - default by AjaxBasedModule Call
		
		
		
		String action = getParam("action"); // Second Level		
		
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
		
		
		this.context.put("id_suburusan", id_suburusan);
		this.context.put("id_status", id_status);
		this.context.put("txtKeterangan", keterangan);
		this.context.put("txtIP", ip);
		this.context.put("txtUser", user);
		this.context.put("txtTarikhAktiviti", tarikh_aktiviti);
		this.context.put("jenis_log", jenis_log);
		this.context.put("id_seksyen", id_seksyen);
		this.context.put("jenis_aktiviti",jenis_aktiviti);
		this.context.put("NO_FAIL",NO_FAIL);
		
		
		
		
		this.context.put("list_seksyen", getListSeksyen());
		
		
		String skrin = "";
		
		if ("kosongkan".equals(submit)) 
		{	
			this.context.put("txtKeterangan", "");
			this.context.put("txtIP", "");
			this.context.put("txtUser", "");
			this.context.put("txtTarikhAktiviti", "");
			this.context.put("jenis_log", "");
			this.context.put("id_seksyen", "");
			this.context.put("jenis_aktiviti","");
			this.context.put("id_suburusan","");
			this.context.put("NO_FAIL","");
			
			setupPage(session,action,getListing(null,null,null,null,null,null,null,null,null,null,null,null));	
			
			skrin = "app/utils/frmAuditTrail.jsp";
			
		} 		
		else {
			//setupPage(session,action,getListing(null,null,null,null,null,null,null));
			setupPage(session,action,getListing(id_suburusan,id_status,jenis_aktiviti,id_seksyen,jenis_log,keterangan,ip,user,tarikh_aktiviti,log_date,flag_aktif, NO_FAIL));
			skrin = "app/utils/frmAuditTrail.jsp";
		}
		
		
		
		//Vector lists = getListing();
		
		return skrin;
	}
	
	public Vector getListing(String id_suburusan,String id_status,String jenis_aktiviti,String id_seksyen,String jenis_log,String keterangan,String ip,String user,String tarikh_aktiviti, String log_date, String flag_aktif, String NO_FAIL) throws Exception{
			//Vector list = null;
			Db db = null;
			Vector lists = new Vector();
			String sql = "";
			boolean setLimit = true;
			//myLogger.info("JENIS LOG :"+jenis_log);
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				sql = "select ";
				sql += "(CASE WHEN A.KETERANGAN LIKE '%FAIL [%' THEN  SUBSTR(SUBSTR(A.KETERANGAN, 1, INSTR(A.KETERANGAN, ']') - 1) , "+
						" INSTR(SUBSTR(A.KETERANGAN, 1, INSTR(A.KETERANGAN, ']') - 1) , '[') + 1) "+
						" ELSE '' END) AS NO_FAIL,";						
				sql+= " SU.NAMA_SUBURUSAN as nama_suburusan,ST.keterangan as nama_status,S.ID_SEKSYEN,S.NAMA_SEKSYEN AS seksyen,A.module_Name,A.keterangan,A.jenis_aktiviti," +
					  "A.ip_address,A.TARIKH_KEMASKINI," +
					  "to_char(A.tarikh_aktiviti,'DD/MM/YYYY HH:MI AM') as masa_aktiviti," +
					  "B.user_name, TO_CHAR(MAX(WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') as dot, CASE b.FLAG_AKTIF WHEN '1' THEN 'AKTIF' WHEN '2' THEN 'TIDAK AKTIF' ELSE 'AKTIF'  END AS FLAG_AKTIF from tblaudittrail A,users B,tblrujseksyen S,tblrujstatus ST,tblrujsuburusan SU,WEB_LOGGER WL " +
					  "WHERE A.ID_MASUK = B.USER_ID AND A.ID_SEKSYEN = S.ID_SEKSYEN(+) AND A.ID_STATUS = ST.ID_STATUS(+) AND A.id_suburusan = SU.id_suburusan(+) AND B.USER_LOGIN = WL.USER_NAME " ;
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
						//sql = sql + " AND ROWNUM <= 100 ";				
				}	
				sql = sql + "GROUP BY SU.NAMA_SUBURUSAN,ST.keterangan, S.ID_SEKSYEN, S.NAMA_SEKSYEN, A.keterangan,  A.module_Name,  A.jenis_aktiviti,  A.ip_address,A.tarikh_aktiviti, B.user_name,A.TARIKH_KEMASKINI,b.FLAG_AKTIF " ;	

				
				sql = sql + " ORDER BY  TO_date(A.tarikh_kemaskini,'DD/MM/YYYY HH:MI AM') DESC";	
				
				myLogger.debug("AUDIT TRAIL : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable record = null;
				//new Hashtable();
				while(rs.next()) {
					record = new Hashtable();
					if (rs.getString("module_name") == null) {
						record.put("module_name", "");
						} else {
						record.put("module_name", rs.getString("module_name"));
						}
					
					if (rs.getString("seksyen") == null) {
						record.put("seksyen", "");
						} else {
						record.put("seksyen", rs.getString("seksyen"));
						}
					
					if (rs.getString("keterangan") == null) {
						record.put("keterangan", "");
						} else {
						record.put("keterangan", rs.getString("keterangan"));
						}
					
					if (rs.getString("ip_address") == null) {
						record.put("ip_address", "");
						} else {
						record.put("ip_address", rs.getString("ip_address"));
						}
					
					if (rs.getString("jenis_aktiviti") == null) {
						record.put("jenis_aktiviti", "");
						} else {
						record.put("jenis_aktiviti", rs.getString("jenis_aktiviti"));
						}
					
					if (rs.getString("masa_aktiviti") == null) {
						record.put("masa_aktiviti", "");
						} else {
						record.put("masa_aktiviti", rs.getString("masa_aktiviti"));
						}
					
					if (rs.getString("user_name") == null) {
						record.put("user_name", "");
						} else {
						record.put("user_name", rs.getString("user_name"));
						}
					
					if (rs.getString("nama_suburusan") == null) {
						record.put("nama_suburusan", "");
						} else {
						record.put("nama_suburusan", rs.getString("nama_suburusan"));
						}
					
					if (rs.getString("ID_SEKSYEN") == null) {
						record.put("ID_SEKSYEN", "");
						} else {
						record.put("ID_SEKSYEN", rs.getString("ID_SEKSYEN"));
						}
					if (rs.getString("dot") == null) {
						record.put("dot", "");
						} else {
						record.put("dot", rs.getString("dot"));
						}
					if (rs.getString("flag_aktif") == null) {
						record.put("flag_aktif", "");
						} else {
						record.put("flag_aktif", rs.getString("flag_aktif"));
						}
					if (rs.getString("NO_FAIL") == null) {
						record.put("NO_FAIL", "");
						} else {
						record.put("NO_FAIL", rs.getString("NO_FAIL"));
						}
				
					
					lists.addElement(record);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally  {
				if (db != null) db.close();
			}
			return lists;
			
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
			sql = "SELECT ID_SEKSYEN,KOD_SEKSYEN,NAMA_SEKSYEN FROM TBLRUJSEKSYEN WHERE ID_SEKSYEN IN ('1','2','3','4','6') ";
			
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
	
	Vector list_suburusan = null;
	public Vector getListSuburusan(String id_seksyen) throws Exception {
		list_suburusan = new Vector();
		list_suburusan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_SEKSYEN,KOD_SEKSYEN,NAMA_SEKSYEN FROM TBLRUJSEKSYEN WHERE ID_SEKSYEN IN ('1','2','3','4','6') ";
			
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
	

}
