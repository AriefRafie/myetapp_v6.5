package ekptg.view.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.pfd.FrmKemaskiniPergerakanFailData;
import ekptg.model.pfd.FrmSenaraiTugasanPFD;
import ekptg.view.admin.Pengumuman;

@SuppressWarnings("serial")
public class FrmDashboard extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmDashboard.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	
	Pengumuman logic = new Pengumuman();
	FrmSenaraiTugasanPFD logic1 = new FrmSenaraiTugasanPFD();
	
	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception {	
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String portal_role = (String)session.getAttribute("myrole");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		Vector listLogDokumenByUserId = new Vector();
        Vector listAgihanTugasanByUserId = new Vector();
        Vector listPenerimaTugasanByUserId = new Vector();
        Vector senarai = new Vector();
        
        
		Vector list_memo_aktif = null;
	
		context.put("portal_role",portal_role);
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);

		Hashtable get_jumlah_dokumen = null;
		get_jumlah_dokumen = (Hashtable) jumlah_dokumen(portal_role,userId);
		String jumlah_dokumen = (String)get_jumlah_dokumen.get("Jumlah_Dokumen");
		context.put("jumlah_dokumen",jumlah_dokumen);
		
		Hashtable get_jumlah_fail = null;
		get_jumlah_fail = (Hashtable) jumlah_fail(portal_role,userId);
		String jumlah_fail = (String)get_jumlah_fail.get("Jumlah_Fail");
		context.put("jumlah_fail",jumlah_fail);		
		
		String negeri_server = (String)get_jumlah_fail.get("Nama_Negeri_Server");
		context.put("negeri_server", negeri_server);
		
		
		int jumlah_keseluruhan = Integer.parseInt(jumlah_dokumen) + Integer.parseInt(jumlah_fail);
		context.put("jumlah_keseluruhan",jumlah_keseluruhan);

		Hashtable get_inbox_notifikasi = null;
		get_inbox_notifikasi = notifikasi(portal_role,user_negeri_login,"","",userId,"NO");
		int notifikasi_inbox = (Integer)get_inbox_notifikasi.get("INBOX");
		context.put("notifikasi_inbox",notifikasi_inbox);
		
		System.out.println("SESSION : " + session);
		
    	listLogDokumenByUserId(session);
    	listLogDokumenByUserId = logic1.getListLogDokumen();
    	this.context.put("SenaraiDokumenByUserId", listLogDokumenByUserId);
    	
    	listAgihanTugasanByUserId(session);
    	listAgihanTugasanByUserId = logic1.getListAgihanTugasan();
    	this.context.put("SenaraiAgihanTugasanByUserId",listAgihanTugasanByUserId);
    	
    	listPenerimaTugasanByUserId(session);
    	listPenerimaTugasanByUserId = logic1.getListPenerimaTugasan();
    	this.context.put("SenaraiPenerimaTugasanByUserId",listPenerimaTugasanByUserId);
    	
       	listPergerakanPemohonFail(session);
    	senarai = FrmKemaskiniPergerakanFailData.getListPergerakanPemohonFail();
    	this.context.put("SenaraiPergerakanFail",senarai);
    	
		String vm = "app/pfd/dashboard.jsp";
		
		return vm;
	}
	
	private void listPergerakanPemohonFail(HttpSession session) throws Exception {
	    String user_id = (String)session.getAttribute("_ekptg_user_id");
	    //int id = Integer.parseInt(getParam("idFail"));
	    FrmKemaskiniPergerakanFailData.setListPergerakanPemohonFail(user_id);
	}
	
	private void listPenerimaTugasanByUserId(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic1.setListPenerimaTugasan(user_id);
	}
	
	private void listLogDokumenByUserId(HttpSession session) throws Exception {
	    String user_id = (String)session.getAttribute("_ekptg_user_id");
	    logic1.setListPergerakanPemohonFail(user_id);
	}
	
	private void listAgihanTugasanByUserId(HttpSession session) throws Exception {
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		logic1.setListAgihanTugasan(user_id);
	}
	
	public Hashtable jumlah_dokumen(String role,String user_id) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable get_negeri = (Hashtable) kod_negeri();
		String kod_negeri = (String)get_negeri.get("KOD_NEGERI");
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
					
			sql = "SELECT (SELECT COUNT(*) FROM TBLPFDLOGDOKUMEN A, TBLPFDTAGDOKUMEN B WHERE A.ID_LOGDOKUMEN = B.ID_DOKUMEN(+) AND A.NO_RUJUKAN LIKE '%' ||''|| '%' and A.id_seksyen =(select id_seksyen from users_internal where user_id='"+user_id+"') and A.id_negeri =(select id_negeri from users_internal where user_id='"+user_id+"')) AS JUMLAH FROM DUAL";
			
			myLogger.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("Jumlah_Dokumen", rs.getString("JUMLAH"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable jumlah_fail(String role,String user_id) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable get_negeri = (Hashtable) kod_negeri();
		String kod_negeri = (String)get_negeri.get("KOD_NEGERI");
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(!kod_negeri.equals("16"))
			{
			sql += " SELECT (SELECT INITCAP(U.NAMA_PEJABAT) FROM TBLRUJPEJABATJKPTG U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+user_id+"' ) AS NAMA_NEGERI_SERVER,  ";
			}
			else
			{
			sql += " SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N WHERE S.KOD_NEGERI = N.KOD_NEGERI ) AS NAMA_NEGERI_SERVER,  ";
			}
			
			
			sql += "(SELECT COUNT(DISTINCT A.ID_FAIL)  "+
		      		" FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F" + 
		      		" WHERE " +
		      		" A.ID_FAIL = F.ID_FAIL(+)" +
					" AND A.ID_NEGERI = B.ID_NEGERI" +
		      		" AND A.ID_SEKSYEN = C.ID_SEKSYEN " +
		      		" AND A.ID_STATUS = D.ID_STATUS(+)" +
		      		" AND F.ID_STATUS = E.ID_STATUS(+)"+
		      		" AND A.ID_URUSAN NOT IN (382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11,161042)";
		    	  if(kod_negeri.equals("16"))
			      {
		    		  sql +=	" and a.id_seksyen =(select id_seksyen from users_internal where user_id='"+user_id+"')";
			      }	      	
		      		sql +=	" and a.id_negeri =(select id_negeri from users_internal where user_id='"+user_id+"')";
			
			sql += ") AS JUMLAH FROM DUAL";
			
//			sql += " (SELECT COUNT(*) FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F, TBLPFDRUJTARAFKESELAMATAN G WHERE  A.ID_FAIL = F.ID_FAIL(+) AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_SEKSYEN = C.ID_SEKSYEN  AND A.ID_STATUS = D.ID_STATUS(+) AND F.ID_STATUS = E.ID_STATUS(+) AND A.ID_TARAFKESELAMATAN = G.ID_TARAFKESELAMATAN(+) AND A.ID_STATUS <> 999 AND A.ID_URUSAN NOT IN (382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11,161042) AND A.ID_NEGERI = '"+kod_negeri+"') AS JUMLAH FROM DUAL";
			
	
			myLogger.info("JUMLAH FAIL :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("Jumlah_Fail", rs.getString("JUMLAH"));
				h.put("Nama_Negeri_Server", rs.getString("NAMA_NEGERI_SERVER"));
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
}