/**
 * 
 */
package ekptg.view.str;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.view.admin.Pengumuman;

public class FrmDashboard extends AjaxBasedModule {
	
	
	private static final long serialVersionUID = -3455784952255888226L;
	static Logger myLogger = Logger.getLogger(FrmDashboard.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	Pengumuman logic = new Pengumuman();
	
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String portal_role = (String) session.getAttribute("myrole");
		context.put("portal_role", portal_role);
		String command = getParam("command");
		System.out.println(command);

		role = (String) session.getAttribute("myrole");
		userId = (String) session.getAttribute("_ekptg_user_id");
		user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		Vector list_memo_aktif = null;	
		context.put("defaultTab", "0");
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);
		
		Hashtable get_inbox_notifikasi = null;
		get_inbox_notifikasi = notifikasi(portal_role,user_negeri_login,"","",userId,"NO");
		int notifikasi_inbox = (Integer)get_inbox_notifikasi.get("INBOX");
		context.put("notifikasi_inbox",notifikasi_inbox);

		String vm = "app/str/dashboard.jsp";

		return vm;
	}


public Hashtable notifikasi(String role,String id_negeri_user,String id_esaduan,String flag_notifikasi,String user_terima,String notread ) throws Exception {
	
	Db db = null;
	String sql = "";
	try {
		db = new Db();
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
	
		sql += " SELECT ( ";
		sql += "  SELECT COUNT(*) FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "+
		" TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "+
		" WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "+
		" AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "+
		" AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "+
		" AND A.USER_ID = U.USER_ID "+
		" AND X.ID_ESADUAN = A.ID_ESADUAN "+
		" AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "+
		" AND U.USER_ID = UI.USER_ID "+
		" AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "+
		" AND UI.ID_NEGERI = N.ID_NEGERI(+) "+
		" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "+
		" AND UI.ID_DAERAH = D.ID_DAERAH(+)" +
		" AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";		
		sql += " AND X.ID_ESNOTIFIKASI is not null ";
		if(!id_esaduan.equals(""))
		{
		sql += " AND X.ID_ESADUAN = '"+id_esaduan+ "' ";
		}
		if(!id_negeri_user.equals("") && !role.equals("adminsuper_es"))
		{
		sql += " AND A.ID_NEGERI_PENGADU = '"+id_negeri_user+ "' ";
		}
		if(!user_terima.equals(""))
		{
		sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '"+user_terima+"' ";
		}
		if(!flag_notifikasi.equals(""))
		{
		sql += " AND X.FLAG_NOTIFIKASI = '"+flag_notifikasi+"'";
		}			
		if(!notread.equals(""))
		{
		sql += " AND X.FLAG_READ = '"+notread+"'";
		}
		sql += " ) AS JUMLAH_ADUAN,";
		
		sql += " (SELECT COUNT(*) as notification"+
		" FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "+
		" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "+
		" AND B.USER_ID = '"+user_terima+"' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID) AS INBOX ";	
	
		sql += " FROM DUAL";
		
		myLogger.info("--------------- LIST NOTIFICATION DASHBOARD LIST:"+sql.toUpperCase());
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h = null;
		
		while (rs.next()){
			h = new Hashtable();
			h.put("JUMLAH_ADUAN", rs.getString("JUMLAH_ADUAN")==null?"":rs.getInt("JUMLAH_ADUAN"));
			h.put("INBOX", rs.getString("INBOX")==null?"":rs.getInt("INBOX"));	
			}
		return h;
	} finally {
		if (db != null)
			db.close();
	}
}
}


