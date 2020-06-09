package ekptg.view.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.IPortalUtility;
import ekptg.engine.OnlineUser;
import ekptg.engine.PortalUtility;
import ekptg.model.IStatus;
import ekptg.model.StatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.view.admin.Pengumuman;

public class FrmOnlineMenuUtama extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7224997368253941733L;
	static Logger myLogger = Logger.getLogger(FrmOnlineMenuUtama.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	
	Pengumuman logic = new Pengumuman();
	
	private static final String PATH="app/online/manuUtama/";
	private String vm = PATH+"index.jsp";
	private IPortalUtility portalUtility;
	private IHtp iErr = null;  
	//return Permohonan 1
	private IStatus iStatus = null;

	
	@Override
	public String doTemplate2() throws Exception {
		
		String nModul = getParam("namamodul");
		this.context.put("nModul", nModul);
		
		String nTab = getParam("namatab");
		this.context.put("nTab", nTab);
		
		String command = getParam("command");
		String action = getParam("action");
		
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		context.put("user_id", userId);
		String portal_role = (String)session.getAttribute("myrole");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		String user_login = (String)session.getAttribute("_ekptg_user_id");
		
		String log_terakhir = LogTerakhir(userId);
		context.put("log_terakhir",log_terakhir);
		
		getUserDetail(user_login);
		
		context.put("portal_role",portal_role);
		
		int notifikasiaAduan = 0;
		this.context.put("notifikasiaAduan", "");
		
			/*
			if(command.equals("showNotifikasiAduan"))
			{
				notifikasiaAduan = notifikasiaAduan(session,userId,null);
				this.context.put("notifikasiaAduan", notifikasiaAduan);
				vm = "app/online/manuUtama/divNotifikasiAduan.jsp";
			}
			*/
		    
			notifikasiaAduan = notifikasiaAduan(session,userId,null);
			this.context.put("notifikasiaAduan", notifikasiaAduan);
		
			Vector list_memo_aktif = null;
			list_memo_aktif = logic.getMemo("","Aktif","0","1");
			context.put("list_memo_aktif",list_memo_aktif);

			
			Hashtable get_inbox_notifikasi = null;
			get_inbox_notifikasi = notifikasi(userId);
			int notifikasi_inbox = (Integer)get_inbox_notifikasi.get("INBOX");
			context.put("notifikasi_inbox",notifikasi_inbox);			
			context.put("notifikasiDeraf", notifikasiDeraf(userId));
		
		context.put("bilPPKkembali", dikembalikanByUrusan("382").size());
	
		return vm;
		
	}
	
	public int notifikasiDeraf(String userID) throws Exception {
		int returnValue=0;	
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT COUNT(DISTINCT P.ID_PERMOHONAN) JUMLAH_PERMOHONAN " +
					" FROM TBLPPKPERMOHONAN P, TBLPPKOBPERMOHONAN OBP, TBLPPKPERMOHONANSIMATI PSM, " +
					" TBLPFDFAIL F, USERS_ONLINE UO, TBLPPKSIMATI SM, TBLPPKPEMOHON PM, TBLRUJSTATUS S, TBLRUJDAERAH D "+
					" WHERE P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_PERMOHONANSIMATI = OBP.ID_PERMOHONANSIMATI " +
					" AND PM.ID_PEMOHON = P.ID_PEMOHON AND P.ID_FAIL = F.ID_FAIL AND F.ID_SEKSYEN = 2 " +
					" AND (OBP.NO_KP_BARU = UO.NO_KP_BARU OR F.ID_MASUK = '"+userID+"') " +
					" AND PSM.ID_SIMATI = SM.ID_SIMATI AND S.ID_STATUS = P.ID_STATUS AND D.ID_DAERAH(+) = P.ID_DAERAHMHN " +
					" AND P.NO_PERMOHONAN_ONLINE IS NULL AND P.ID_STATUS IN ('150', '160') " +
					" AND NVL (OBP.NO_KP_BARU, ' ') NOT IN ('-', 'TIADA', ' ', '0') AND UO.USER_ID = '"+userID+"' ";
			
			System.out.println("JUMLAH DOKUMEN :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnValue = rs.getInt("JUMLAH_PERMOHONAN");
			}

		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
		
	}
	
	public String LogTerakhir(String user_id) throws Exception {
		String log_terakhir = "";
		Db db = null;
		String sql = "SELECT MAX(WL.LOG_DATE) AS LAST_LOGIN " +
				" FROM USERS U,USER_TRACKER WL " +
				" WHERE U.USER_LOGIN = WL.USER_LOGIN " +
				" AND U.USER_ID = "+user_id+" "+
				" GROUP BY U.USER_ID, U.USER_NAME ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
//			sql = "SELECT MAX(WL.LOG_DATE) AS LAST_LOGIN "+
//				" FROM USERS U, USERS_ONLINE UO, WEB_LOGGER WL "+ 
//				" WHERE U.USER_ID = UO.USER_ID "+
//				" AND U.USER_LOGIN = WL.USER_NAME(+) "+
//				" AND U.USER_ID = "+user_id+" "+
//				" GROUP BY U.USER_ID, U.USER_NAME ";
//				//System.out.println(" SQL USER LIST FROM MODEL :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				log_terakhir = rs.getString("LAST_LOGIN") == null ? "" : rs.getString("LAST_LOGIN");
			}
			return log_terakhir;
			
		}catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Hashtable notifikasi(String user_terima) throws Exception {		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
					
			sql += " SELECT ";
						
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
//				h.put("JUMLAH_ADUAN", rs.getString("JUMLAH_ADUAN")==null?"":rs.getInt("JUMLAH_ADUAN"));
				h.put("INBOX", rs.getString("INBOX")==null?"":rs.getInt("INBOX"));	
				}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void getUserDetail(String user_login){
		OnlineUser user = getUtility().getUserOnline(user_login);
		context.put("user", user);
	}
	
	private IPortalUtility getUtility(){
		if(portalUtility == null)
			portalUtility = new PortalUtility();
		return portalUtility;
	}
	
	public int notifikasiaAduan(HttpSession session,String USER_ID,Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listNotifikasi = null;
		String sql = "";
		String selectedLanguage = (String) session.getAttribute("selectedLanguage");
		try {
			if(db == null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			//($N.ID_STATUS == "16122" || $N.ID_STATUS == "16123" || $N.ID_STATUS == "16127")
			sql = " SELECT COUNT(N.ID_ADUANPUBLICNOTIFIKASI) AS CNT " +
				   " FROM TBLADUANPUBLICNOTIFIKASI N, TBLADUANPUBLIC P, TBLRUJSTATUSADUANPUBLIC S " +
				   " WHERE N.ID_ADUANPUBLIC = P.ID_ADUANPUBLIC AND P.ID_STATUS = S.ID_STATUS AND P.ID_STATUS = N.ID_STATUS  " +
				   " AND N.FLAG_READ = 'N' AND N.ID_STATUS IN ('16122','16123','16127')  ";					
				   sql += " AND N.ID_TERIMA = '"+USER_ID+"' "; 	
			
			myLogger.info(" SQL : count notifikasi :"+ sql);			
			rs = stmt.executeQuery(sql);
			listNotifikasi = Collections.synchronizedList(new ArrayList());
			int jumlah_notifikasi = 0;
			while (rs.next()) {
				jumlah_notifikasi = rs.getString("CNT") == null ? 0 : rs.getInt("CNT");	
			}
			return jumlah_notifikasi;
		} finally {
			if(db == null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		
	}
	//return Permohonan 3
	public Vector<Hashtable<String, String>> dikembalikan(String idSubUrusan) throws Exception {
		Vector<Hashtable<String, String>> returnVal = null;
		try {
			returnVal = getStatus().getInfoStatusPermohonan("",idSubUrusan,"50");
		}catch(Exception e){
			throw new Exception(getErr().getErrorHTML("inquery:"+idSubUrusan+"::"+e.getMessage()));
		}
		return returnVal ;
		
	}
	
	public Vector<Hashtable<String, String>> dikembalikanByUrusan(String idUrusan) throws Exception {
		Vector<Hashtable<String, String>> returnVal = null;
		try {
			returnVal = getStatus().getInfoStatusPermohonan(idUrusan,"","50");
		}catch(Exception e){
			throw new Exception(getErr().getErrorHTML("inquery:"+idUrusan+"::"+e.getMessage()));
		}
		return returnVal ;
		
	}
	//return Permohonan 2
	private IStatus getStatus(){
		if (iStatus==null){
			iStatus=new StatusBean();
		}
		return iStatus;
	}
		
	private IHtp getErr(){
		if(iErr== null)
			iErr = new HtpBean();
		return iErr;
	}
	
}