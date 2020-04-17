package ekptg.view.online.aduan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.view.admin.Pengumuman;


@SuppressWarnings("serial")
public class ComplaintDashboardModule extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(ComplaintDashboardModule.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	
	Pengumuman logic = new Pengumuman();	
	
	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception {	
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String portal_role = (String)session.getAttribute("myrole");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		
		Vector list_memo_aktif = null;
	
		context.put("portal_role",portal_role);
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);

		Hashtable get_jumlah = null;
		get_jumlah = (Hashtable) jumlah(portal_role,userId);
		String jumlah = (String)get_jumlah.get("Jumlah");
		String jumlah_aduan = (String)get_jumlah.get("Jumlah_Aduan");
		String jumlah_pertanyaan = (String)get_jumlah.get("Jumlah_Pertanyaan");
		String jumlah_cadangan = (String)get_jumlah.get("Jumlah_Cadangan");
		String jumlah_lain = (String)get_jumlah.get("Jumlah_Lain");
		context.put("jumlah",jumlah);
		context.put("jumlah_aduan",jumlah_aduan);
		context.put("jumlah_pertanyaan",jumlah_pertanyaan);
		context.put("jumlah_cadangan",jumlah_cadangan);
		context.put("jumlah_lain",jumlah_lain);

		Hashtable get_stat_fail = null;
		get_stat_fail = (Hashtable) stat_fail(portal_role,userId);
		String jumlah_selesai_aduan = (String)get_stat_fail.get("Jumlah_Selesai_Aduan");
		String jumlah_dalam_proses_aduan = (String)get_stat_fail.get("Jumlah_Dalam_Proses_Aduan");
		String jumlah_selesai_pertanyaan = (String)get_stat_fail.get("Jumlah_Selesai_Pertanyaan");
		String jumlah_dalam_proses_pertanyaan = (String)get_stat_fail.get("Jumlah_Dalam_Proses_Pertanyaan");
		String jumlah_selesai_cadangan = (String)get_stat_fail.get("Jumlah_Selesai_Cadangan");
		String jumlah_dalam_proses_cadangan = (String)get_stat_fail.get("Jumlah_Dalam_Proses_Cadangan");
		String jumlah_selesai_lain = (String)get_stat_fail.get("Jumlah_Selesai_Lain");
		String jumlah_dalam_proses_lain = (String)get_stat_fail.get("Jumlah_Dalam_Proses_Lain");
		context.put("jumlah_selesai_aduan",jumlah_selesai_aduan);
		context.put("jumlah_dalam_proses_aduan",jumlah_dalam_proses_aduan);
		context.put("jumlah_selesai_pertanyaan",jumlah_selesai_pertanyaan);
		context.put("jumlah_dalam_proses_pertanyaan",jumlah_dalam_proses_pertanyaan);
		context.put("jumlah_selesai_cadangan",jumlah_selesai_cadangan);
		context.put("jumlah_dalam_proses_cadangan",jumlah_dalam_proses_cadangan);
		context.put("jumlah_selesai_lain",jumlah_selesai_lain);
		context.put("jumlah_dalam_proses_lain",jumlah_dalam_proses_lain);
		
		Hashtable get_inbox_notifikasi = null;
		get_inbox_notifikasi = notifikasi(portal_role,user_negeri_login,"","",userId,"NO");
		int notifikasi_inbox = (Integer)get_inbox_notifikasi.get("INBOX");
		context.put("notifikasi_inbox",notifikasi_inbox);
		
		String vm = "app/online/aduan/dashboard/dashboard.jsp";
		
		return vm;
	}
	
	public Hashtable jumlah(String role,String user_id) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT (SELECT COUNT(*) FROM TBLONLINEEADUAN) AS JUMLAH," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE ID_JENISADUAN = 16103) AS JUMLAH_LAIN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE ID_JENISADUAN = 16102) AS JUMLAH_CADANGAN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE ID_JENISADUAN = 161021) AS JUMLAH_PERTANYAAN,"
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE ID_JENISADUAN = 16101) AS JUMLAH_ADUAN" 
				+ " FROM DUAL";
			
			myLogger.info("STATEMENT BAGI JUMLAH :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("Jumlah", rs.getString("JUMLAH"));
				h.put("Jumlah_Aduan", rs.getString("JUMLAH_ADUAN"));
				h.put("Jumlah_Pertanyaan", rs.getString("JUMLAH_PERTANYAAN"));
				h.put("Jumlah_Cadangan", rs.getString("JUMLAH_CADANGAN"));
				h.put("Jumlah_Lain", rs.getString("JUMLAH_LAIN"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable stat_fail(String role,String user_id) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT (SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'SELESAI' AND ID_JENISADUAN = 16101) AS JUMLAH_SELESAI_ADUAN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'DALAM PROSES' AND ID_JENISADUAN = 16101) AS JUMLAH_DALAM_PROSES_ADUAN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'SELESAI' AND ID_JENISADUAN = 161021) AS JUMLAH_SELESAI_PERTANYAAN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'DALAM PROSES' AND ID_JENISADUAN = 161021) AS JUMLAH_DALAM_PROSES_PERTANYAAN,"
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'SELESAI' AND ID_JENISADUAN = 16102) AS JUMLAH_SELESAI_CADANGAN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'DALAM PROSES' AND ID_JENISADUAN = 16102) AS JUMLAH_DALAM_PROSES_CADANGAN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'SELESAI' AND ID_JENISADUAN = 16103) AS JUMLAH_SELESAI_LAIN," 
				+ "(SELECT COUNT(*) FROM TBLONLINEEADUAN WHERE STATUS = 'DALAM PROSES' AND ID_JENISADUAN = 16103) AS JUMLAH_DALAM_PROSES_LAIN" 
				+ " FROM DUAL";
			
			myLogger.info("STATEMENT BAGI JUMLAH ADUAN YANG SELESAI :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("Jumlah_Selesai_Aduan", rs.getString("JUMLAH_SELESAI_ADUAN"));
				h.put("Jumlah_Dalam_Proses_Aduan", rs.getString("JUMLAH_DALAM_PROSES_ADUAN"));
				h.put("Jumlah_Selesai_Pertanyaan", rs.getString("JUMLAH_SELESAI_PERTANYAAN"));
				h.put("Jumlah_Dalam_Proses_Pertanyaan", rs.getString("JUMLAH_DALAM_PROSES_PERTANYAAN"));
				h.put("Jumlah_Selesai_Cadangan", rs.getString("JUMLAH_SELESAI_CADANGAN"));
				h.put("Jumlah_Dalam_Proses_Cadangan", rs.getString("JUMLAH_DALAM_PROSES_CADANGAN"));
				h.put("Jumlah_Selesai_Lain", rs.getString("JUMLAH_SELESAI_LAIN"));
				h.put("Jumlah_Dalam_Proses_Lain", rs.getString("JUMLAH_DALAM_PROSES_LAIN"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
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
			
			myLogger.info("LIST NOTIFICATION DASHBOARD LIST:"+sql.toUpperCase());
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