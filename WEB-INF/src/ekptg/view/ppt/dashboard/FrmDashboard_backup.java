package ekptg.view.ppt.dashboard;

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



public class FrmDashboard_backup extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(FrmDashboard.class);
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
		context.put("portal_role",portal_role);
		String command = getParam("command");
		
		
		Hashtable get_stat = (Hashtable) stat_fail();
	//	Hashtable notifikasi = notifikasi(role,user_negeri_login,"","",userId,"NO");
		
		Vector list_memo_aktif = null;
	
		String negeri_sever = (String)get_stat.get("NAMA_NEGERI_SERVER");
		String jumlah_keseluruhan = (String)get_stat.get("JUMLAH_KESELURUHAN");
		String fail_sek8 = (String)get_stat.get("JUMLAH_KESELURUHAN_SEK8");
		String fail_sek17 = (String)get_stat.get("JUMLAH_KESELURUHAN_SEK17");
		String fail_hapus = (String)get_stat.get("JUMLAH_FAIL_HAPUS");
		String fail_selesai = (String)get_stat.get("JUMLAH_SELESAI");
		String fail_xselesai = (String)get_stat.get("JUMLAH_XSELESAI");
		
	//	myLogger.info("ONLINE 8"+(Integer)notifikasi.get("ONLINE_SEK8"));
		
		context.put("portal_role",portal_role);
//		context.put("check_notifikasi_aduan",(Integer)notifikasi.get("JUMLAH_ADUAN"));
//		context.put("check_notifikasi_online8",(Integer)notifikasi.get("ONLINE_SEK8"));
//		context.put("check_notifikasi_online17",(Integer)notifikasi.get("ONLINE_SEK17"));
//		context.put("getListCountBorangB",(Integer)notifikasi.get("TOTAL_BORANGB"));
//		context.put("check_notifikasi_pindah",(Integer)notifikasi.get("FAIL_PINDAH"));
//		context.put("check_notifikasi_inbox",(Integer)notifikasi.get("INBOX"));	
		context.put("negeri_sever", negeri_sever);
		context.put("jumlah_keseluruhan", jumlah_keseluruhan);
		context.put("fail_sek8", fail_sek8);
		context.put("fail_sek17", fail_sek17);
		context.put("fail_hapus", fail_hapus);
		context.put("fail_selesai", fail_selesai);
		context.put("fail_xselesai", fail_xselesai);		
		context.put("defaultTab", "0");
	
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);

		String vm = "app/ppt/dashboard/dashboard.jsp";
		
		if(command.equals("getTabDashboard"))
		{
			return getTabDashboard(role,user_negeri_login,"","",userId,"NO",fail_selesai,fail_xselesai);
		}
		
		return vm;
		
	}
	
	private String getTabDashboard(String role,String id_negeri_user,String id_esaduan,String flag_notifikasi,String user_terima,String notread,String fail_selesai,String fail_xselesai ) throws Exception
	{
		// TODO Auto-generated method stub
		Hashtable notifikasi_borangB = null;
		//notifikasi_borangB = notifikasi_borangB(role,id_negeri_user,"","",user_terima,notread);
		//context.put("getListCountBorangB",(Integer)notifikasi_borangB.get("TOTAL_BORANGB"));
		context.put("getListCountBorangB","1");
		Hashtable notifikasi_KIV = null;
		//notifikasi_KIV = notifikasi_KIV(role,id_negeri_user,"","",user_terima,notread);
		//context.put("getListKiv",(Integer)notifikasi_KIV.get("TOTAL_KIV"));
		context.put("getListKiv","1");
		
		Vector list_memo_aktif = null;
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);
		
		context.put("fail_selesai", fail_selesai);
		context.put("fail_xselesai", fail_xselesai);
		
		return "app/ppt/dashboard/dashboard_tab.jsp";
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Hashtable stat_fail() throws Exception {
	
		Db db = null;
		String sql = "";
		Hashtable get_negeri = (Hashtable) kod_negeri();
		String kod_negeri = (String)get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N WHERE S.KOD_NEGERI = N.KOD_NEGERI ) AS NAMA_NEGERI_SERVER,  "+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
				if(!kod_negeri.equals("16"))
				{
				sql +=	" AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				}
				sql +=" ) AS JUMLAH_KESELURUHAN,  "+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
				if(!kod_negeri.equals("16"))
				{	
				sql +=	" AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				}
				sql +=" AND SEKSYEN = '8' AND P.ID_STATUS <> '999') AS JUMLAH_KESELURUHAN_SEK8,  "+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
				if(!kod_negeri.equals("16"))
				{
				sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				}
				sql +=" AND SEKSYEN = '17' AND P.ID_STATUS <> '999') AS JUMLAH_KESELURUHAN_SEK17,  "+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
				if(!kod_negeri.equals("16"))
				{
				sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				}
				sql +=" AND P.ID_STATUS = '21' AND P.ID_STATUS <> '999' AND P.SEKSYEN IN (8,17)) AS JUMLAH_SELESAI,  "+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
				if(!kod_negeri.equals("16"))
				{
				sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				}
				sql +=" AND P.ID_STATUS <> '21' AND P.ID_STATUS <> '999' AND P.SEKSYEN IN (8,17)) AS JUMLAH_XSELESAI, "+ 
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
				if(!kod_negeri.equals("16"))
				{
				sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				}
				sql +=" AND P.ID_STATUS = '999') AS JUMLAH_FAIL_HAPUS  FROM DUAL ";
			
			myLogger.info(" STATISTIK :"+sql.toUpperCase());
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NAMA_NEGERI_SERVER") == null) {
					h.put("NAMA_NEGERI_SERVER", "");
				} else {
					h.put("NAMA_NEGERI_SERVER", rs.getString("NAMA_NEGERI_SERVER"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN") == null) {
					h.put("JUMLAH_KESELURUHAN", "");
				} else {
					h.put("JUMLAH_KESELURUHAN", rs.getString("JUMLAH_KESELURUHAN"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK8") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK8", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK8", rs.getString("JUMLAH_KESELURUHAN_SEK8"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK17") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK17", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK17", rs.getString("JUMLAH_KESELURUHAN_SEK17"));
				}
				if (rs.getString("JUMLAH_FAIL_HAPUS") == null) {
					h.put("JUMLAH_FAIL_HAPUS", "");
				} else {
					h.put("JUMLAH_FAIL_HAPUS", rs.getString("JUMLAH_FAIL_HAPUS"));
				}
				if (rs.getString("JUMLAH_SELESAI") == null) {
					h.put("JUMLAH_SELESAI", "");
				} else {
					h.put("JUMLAH_SELESAI", rs.getString("JUMLAH_SELESAI"));
				}
				if (rs.getString("JUMLAH_XSELESAI") == null) {
					h.put("JUMLAH_XSELESAI", "");
				} else {
					h.put("JUMLAH_XSELESAI", rs.getString("JUMLAH_XSELESAI"));
				}
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
public Hashtable kod_negeri() throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT KOD_NEGERI FROM TBLLOOKUPSTATE S ";		
			myLogger.info(" KOD_NEGERI :"+sql.toUpperCase());
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h.put("KOD_NEGERI", rs.getString("KOD_NEGERI"));
				}
				
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
		
		sql += " (SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
			+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
			+" WHERE" 
			+" D.id_daerah in ( select distinct u.id_daerahurus from"
			+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_terima+"')"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
			+" AND A.ID_FAIL = F.ID_FAIL(+)" 
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
			+" AND P.ID_SIMATI = MS.ID_SIMATI" 
			+" AND A.ID_STATUS <> '999'"
	    	+" AND A.SEKSYEN = '8'"  
			+" AND STA.AKTIF = 1" 
	        +" AND A.FLAG_JENIS_PERMOHONAN = 0 ) AS ONLINE_SEK8, "; 
		
		sql += " (SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
			+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
			+" WHERE" 
			+" D.id_daerah in ( select distinct u.id_daerahurus from"
			+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+user_terima+"')"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
			+" AND A.ID_FAIL = F.ID_FAIL(+)" 
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
			+" AND P.ID_SIMATI = MS.ID_SIMATI" 
			+" AND A.ID_STATUS <> '999'"
	    	+" AND A.SEKSYEN = '17'"  
			+" AND STA.AKTIF = 1" 
	        +" AND A.FLAG_JENIS_PERMOHONAN = 0 ) AS ONLINE_SEK17,"
			+"";
		
		sql += " (SELECT COUNT(*) AS TOTAL_FAIL  "+
		" FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF "+
		" WHERE PPP.ID_FAIL = FFF.ID_FAIL AND "+
		" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+
		" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
		" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
		" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+user_terima+"') "+
		" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
		" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
		" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
		" AND A.ID_STATUS <> '999' "+
		" AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND "+
		" ( "+
		" SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+
		" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
		" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
		" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+user_terima+"')  "+
		" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
		" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
		" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
		" AND A.ID_STATUS <> '999' "+
		" AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL "+
		" ) > 0 "+
		" AND "+
		" (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -  "+
		" (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+ 
		" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
		" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
		" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+user_terima+"') "+
		" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
		" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
		" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
		" AND A.ID_STATUS <> '999' "+
		" AND S.ID_STATUS = '170' "+
		" AND  FFF.ID_FAIL = FF.ID_FAIL))>30 ) AS TOTAL_BORANGB, ";
		
		
		sql += " (SELECT COUNT(distinct A.id_permohonan) as notification from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "+
	      "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "+
	      "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "+
	      "TBLRUJNEGERI R, TBLRUJDAERAH S "+
	      "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
	      "AND A.ID_FAIL  =  C.ID_FAIL "+
	      "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
	      "AND D.ID_SIMATI = E.ID_SIMATI "+
	      "AND A.ID_PEMOHON = F.ID_PEMOHON "+
	      "AND B.ID_NEGERI = G.ID_NEGERI "+
	      "AND B.ID_DAERAH = H.ID_DAERAH "+
	      //"and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
	      //"AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
	      "AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
	      "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
	      "AND M.AKTIF = 1 "+
	      "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
	      "AND N.ID_STATUS = O.ID_STATUS "+
	      "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
	      "AND P.USER_ID  =  Q.USER_ID "+
	      "AND A.ID_NEGERIMHN = R.ID_NEGERI "+
	      "AND A.ID_DAERAHMHN = S.ID_DAERAH "+
	      "AND Q.USER_ID = '"+user_terima+"' "+
	      "AND G.ID_NEGERI = P.ID_NEGERI "+
	      "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "+
	      "AND A.ID_STATUS = 56) AS FAIL_PINDAH, ";
		
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
			h.put("ONLINE_SEK8", rs.getString("ONLINE_SEK8")==null?"":rs.getInt("ONLINE_SEK8"));
			h.put("ONLINE_SEK17", rs.getString("ONLINE_SEK17")==null?"":rs.getInt("ONLINE_SEK17"));
			h.put("TOTAL_BORANGB", rs.getString("TOTAL_BORANGB")==null?"":rs.getInt("TOTAL_BORANGB"));
			h.put("FAIL_PINDAH", rs.getString("FAIL_PINDAH")==null?"":rs.getInt("FAIL_PINDAH"));
			h.put("INBOX", rs.getString("INBOX")==null?"":rs.getInt("INBOX"));	
		
			
			}
		return h;
	} finally {
		if (db != null)
			db.close();
	}
}

	
	
	Integer count_aduan = null;
	public Integer getListNotifikasi_main_list(String role,String id_negeri_user,String id_esaduan,String flag_notifikasi,String user_terima,String notread) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(*) as notification FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "+
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
			
			//myLogger.info("LIST NOTIFICATION DASHBOARD LIST"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			
				count_aduan = rs.getInt("notification");
				
				}
			return count_aduan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Integer count_8 = null;
	public Integer getListNotifikasi_online8(String userid,String seksyen) throws Exception {
		Db db = null;		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT COUNT(*) as notification FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P," 
			+" TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D" 
			+" WHERE" 
			+" D.id_daerah in ( select distinct u.id_daerahurus from"
			+" TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"+userid+"')"
			+" AND ST.ID_STATUS = S.ID_STATUS(+)"
			+" AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)" 	
			+" AND A.ID_PEMOHON = PM.ID_PEMOHON(+)" 
			+" AND A.ID_FAIL = F.ID_FAIL(+)" 
			+" AND A.ID_DAERAHMHN = D.ID_DAERAH"
			+" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN" 
			+" AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN" 
			+" AND P.ID_SIMATI = MS.ID_SIMATI" 
			+" AND A.ID_STATUS <> '999'"
	    	+" AND A.SEKSYEN = '"+seksyen+"'"  
			+" AND STA.AKTIF = 1" 
	        +" AND A.FLAG_JENIS_PERMOHONAN = 0" 
			+" ORDER BY STA.ID_SUBURUSANSTATUSFAIL"
			+"";
			
			myLogger.info("LIST NOTIFICATION ONLINE 8 DASHBOARD LIST"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
			
				count_8 = rs.getInt("notification");
				
				}
			return count_8;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Integer count_pindah = null;
	public Integer getListNotifikasi_pindah(String ekptg_user_id) throws Exception {
		Db db = null;
		
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT COUNT(distinct A.id_permohonan) as notification from TBLPPKPERMOHONAN A, TBLPPKBKE B,  TBLPFDFAIL C, TBLPPKPERMOHONANSIMATI D, TBLPPKSIMATI E, "+
		      "TBLPPKPEMOHON F,  TBLRUJNEGERI G,  TBLRUJDAERAH H,  TBLRUJPEJABATURUSAN I, TBLRUJPEJABATJKPTG J, "+
		      "TBLRUJSUBURUSANSTATUSFAIL M, TBLRUJSUBURUSANSTATUS N, TBLRUJSTATUS O, USERS_INTERNAL P, USERS Q, "+
		      "TBLRUJNEGERI R, TBLRUJDAERAH S "+
		      "WHERE A.ID_PERMOHONAN =  B.ID_PERMOHONAN "+
		      "AND A.ID_FAIL  =  C.ID_FAIL "+
		      "AND D.ID_PERMOHONAN  = A.ID_PERMOHONAN "+
		      "AND D.ID_SIMATI = E.ID_SIMATI "+
		      "AND A.ID_PEMOHON = F.ID_PEMOHON "+
		      "AND B.ID_NEGERI = G.ID_NEGERI "+
		      "AND B.ID_DAERAH = H.ID_DAERAH "+
		      //"and A.ID_NEGERIMHN = I.ID_NEGERIURUS "+
		      //"AND a.ID_DAERAHmhn = I.ID_DAERAHURUS "+
		      "AND I.ID_PEJABATJKPTG =  J.ID_PEJABATJKPTG "+
		      "AND M.ID_PERMOHONAN = A.ID_PERMOHONAN "+
		      "AND M.AKTIF = 1 "+
		      "AND M.ID_SUBURUSANSTATUS = N.ID_SUBURUSANSTATUS "+
		      "AND N.ID_STATUS = O.ID_STATUS "+
		      "AND J.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG "+
		      "AND P.USER_ID  =  Q.USER_ID "+
		      "AND A.ID_NEGERIMHN = R.ID_NEGERI "+
		      "AND A.ID_DAERAHMHN = S.ID_DAERAH "+
		      "AND Q.USER_ID = '"+ekptg_user_id+"' "+
		      "AND G.ID_NEGERI = P.ID_NEGERI "+
		      "AND (A.FLAG_PERMOHONAN <> '1' OR A.FLAG_PERMOHONAN IS NULL) "+
		      "AND A.ID_STATUS = 56 ";
   
		      //myLogger.info("LIST NOTIFICATION PINDAH DASHBOARD LIST"+sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
				
					count_pindah = rs.getInt("notification");
					
					}
				return count_pindah;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
	
	
	Integer count_inbox = null;
	public Integer getListNotifikasi_inbox(String userId) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();				
				sql = ""+
				" SELECT COUNT(*) as notification"+
				" FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "+
				" WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "+
				" AND B.USER_ID = '"+userId+"' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID ";		
				sql += "  ";				
				myLogger.info("SQL COUNT LIST MAIN INBOX XX :" + sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {					
					count_inbox = rs.getInt("notification");					
					}
				return count_inbox;
			} finally {
				if (db != null)
					db.close();
			}
		}
	
	
	Integer count_borangb = null;
	public Integer getListCountBorangB(String userId) throws Exception {
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();				
				sql = " SELECT COUNT(*) AS TOTAL_FAIL  "+
				" FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF "+
				" WHERE PPP.ID_FAIL = FFF.ID_FAIL AND "+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND "+
				" ( "+
				" SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"')  "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL "+
				" ) > 0 "+
				" AND "+
				" (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -  "+
				" (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL))>30";
				
				myLogger.info("SQL COUNT BORANG B :" + sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {					
					count_borangb = rs.getInt("TOTAL_FAIL");					
					}
				return count_borangb;
			} finally {
				if (db != null)
					db.close();
			}
		}
	
	Vector getListBorangB = new Vector();
	public Vector getListBorangB(String userId) throws Exception {
		Db db = null;
		getListBorangB.clear();
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT PPP.SEKSYEN,SSM.ID_SIMATI,PPP.ID_PERMOHONAN,FFF.ID_FAIL,FFF.NO_FAIL,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') AS CURRENT_DATE, "+
				" (SELECT TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "+
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "+
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "+
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL"+
				" AND STA.TARIKH_MASUK = ( "+
				" SELECT MAX(STA.TARIKH_MASUK) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' AND F.ID_FAIL = FF.ID_FAIL"+
				" )) AS TARIKH_BORANGB,"+
				" (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -"+ 
				" (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999'"+ 
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL)) AS DIFF"+
				" FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF,TBLPPKPERMOHONANSIMATI SSM"+
				" WHERE PPP.ID_FAIL = FFF.ID_FAIL AND PPP.ID_PERMOHONAN = SSM.ID_PERMOHONAN AND"+
				" (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND"+
				" ("+
				" SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH"+ 
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI"+ 
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL"+
				" ) > 0"+
				" AND"+
				" (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -"+ 
				" (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM,"+ 
				" TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "+
				" WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR"+ 
				" WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') "+
				" AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+)"+ 
				" AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "+
				" AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "+
				" AND A.ID_STATUS <> '999' "+
				" AND S.ID_STATUS = '170' "+
				" AND  FFF.ID_FAIL = FF.ID_FAIL))>30 AND ROWNUM < 50";
			
			myLogger.info("GET LIST FAIL BORANG B"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			
			while (rs.next()){
				h = new Hashtable();
				h.put("ID_SIMATI", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("NO_FAIL", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("SEKSYEN", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
			
				getListBorangB.addElement(h);
			}
			return getListBorangB;
		}finally {
			if(db != null) db.close();
		}
		}
	

}



//SELECT FFF.NO_FAIL,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') AS CURRENT_DATE,
//(SELECT TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY') FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, 
//TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D 
//WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR 
//WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') 
//AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) 
//AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH 
//AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI 
//AND A.ID_STATUS <> '999' 
//AND S.ID_STATUS = '170' 
//AND  FFF.ID_FAIL = FF.ID_FAIL
//AND STA.TARIKH_MASUK = (
//SELECT MAX(STA.TARIKH_MASUK) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, 
//TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D 
//WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR 
//WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') 
//AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) 
//AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH 
//AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI 
//AND A.ID_STATUS <> '999' 
//AND S.ID_STATUS = '170' AND F.ID_FAIL = FF.ID_FAIL
//)) AS TARIKH_BORANGB,
//(TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') - 
//(SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, 
//TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D 
//WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR 
//WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') 
//AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) 
//AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH 
//AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI 
//AND A.ID_STATUS <> '999' 
//AND S.ID_STATUS = '170' 
//AND  FFF.ID_FAIL = FF.ID_FAIL)) AS DIFF
//FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF
//WHERE PPP.ID_FAIL = FFF.ID_FAIL AND
//(SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, 
//TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D 
//WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR 
//WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') 
//AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) 
//AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH 
//AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI 
//AND A.ID_STATUS <> '999' 
//AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND
//(
//SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, 
//TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D 
//WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR 
//WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') 
//AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) 
//AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH 
//AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI 
//AND A.ID_STATUS <> '999' 
//AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL
//) > 0
//AND
//(TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') - 
//(SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, 
//TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D 
//WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR 
//WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"+userId+"') 
//AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) 
//AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH 
//AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI 
//AND A.ID_STATUS <> '999' 
//AND S.ID_STATUS = '170' 
//AND  FFF.ID_FAIL = FF.ID_FAIL))>30

 




