package ekptg.helpers;

import java.sql.Statement;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;

public class AuditTrail {
	static Logger myLogger = Logger.getLogger(ekptg.helpers.AuditTrail.class);
	
	public AuditTrail() {	}
	
	public static void logActivity(String jenis_aktiviti,String keterangan) 
	throws Exception {
		logActivity("","","",null,null,jenis_aktiviti,keterangan,null);
	}
	
	public static void logActivity(VTemplate module,HttpSession session,
			String jenis_aktiviti,String keterangan) 
	throws Exception {
		logActivity("","","",module,session,jenis_aktiviti,keterangan,null);
	}
	
	public static void logActivity(VTemplate module,HttpSession session,
			String jenis_aktiviti,String keterangan,Db db) 
	throws Exception {
		logActivity("","","",module,session,jenis_aktiviti,keterangan,db);
	}
	
	public static void logActivity(String id_status
		,String id_seksyen
		,VTemplate module,HttpSession session
		,String jenis_aktiviti,String keterangan) throws Exception {
		logActivity("",id_status,id_seksyen,module,session,jenis_aktiviti,keterangan,null);
	}
	
	public static void logActivity(String id_suburusan,String id_status,String id_seksyen,VTemplate module,HttpSession session,
		String jenis_aktiviti,String keterangan) 
		throws Exception {
		logActivity(id_suburusan,id_status,id_seksyen,module,session,jenis_aktiviti,keterangan,null);
	}
	
	public static void logActivity(String id_suburusan
		,String id_status
		,String id_seksyen
		,VTemplate module
		,HttpSession session
		,String jenis_aktiviti
		,String keterangan
		,Db db) throws Exception {		
		
		String module_session = "-";
		//myLogger.info(" module_session ::session= "+session);	
		String module_name = module_session;
		if (module != null) { 
			module_name = module.getClass().getName();
		}
		String user_id = "1";
		String ip_address = "-";
		if (session != null) {
			user_id = String.valueOf(session.getAttribute("_ekptg_user_id"));
			ip_address = String.valueOf(session.getAttribute("ip_address"));
			module_session = String.valueOf(session.getAttribute("_portal_module"));
		
		}
		 
		String sql = "";
		Db db1 = null;
		try {
			if(db == null){
				db1 = new Db(); 
			}else {
				db1 = db;
			}
			Statement stmt = db1.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.clear();
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
			sql = r.getSQLInsert("TBLAUDITTRAIL",db1);		
//			myLogger.info("V3 : INSERT TBLAUDITTRAIL : "+sql);
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			throw new Exception ("error log activity :"+e.getMessage());
		} finally {
			if(db == null)
			{
				if (db1 != null) db1.close();
			}
		}
	
	}
	
}
//20200824 20:36
