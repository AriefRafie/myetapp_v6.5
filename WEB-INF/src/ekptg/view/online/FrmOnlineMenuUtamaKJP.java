package ekptg.view.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.utils.status.IStatus;
import ekptg.model.utils.status.StatusBean;
import ekptg.view.admin.Pengumuman;

public class FrmOnlineMenuUtamaKJP extends AjaxBasedModule {
	/**
	 * 
	 */
	static Logger myLog = Logger.getLogger(FrmOnlineMenuUtamaKJP.class);
	private static final long serialVersionUID = -4427185828234591107L;
	private static final String PATH = "app/online/manuUtama/";
	private String vm = PATH + "frmMenuUtamaKJP.jsp";
	private IHtp iErr = null;  
	//return Permohonan 1
	private IStatus iStatus = null;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		Pengumuman logic = new Pengumuman();
		// String portal_role = (String) session.getAttribute("_portal_role");
		// razman comment
		String portal_role = (String) session.getAttribute("myrole");
		String user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		System.out.println("*** user_negeri_login :"+user_negeri_login);
		 
		Vector list_memo_aktif = null;
		list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
		context.put("list_memo_aktif", list_memo_aktif);

		String login = (String) session.getAttribute("_portal_login");
		String user_id = (String) session.getAttribute("_ekptg_user_id");

		Hashtable get_specific_jawatan = null;
		get_specific_jawatan = (Hashtable) get_jawatan(login);
		String jawatan = (String) get_specific_jawatan.get("jawatan");

		Hashtable get_notifikasi_permohonan = null;
		get_notifikasi_permohonan = (Hashtable) notifikasi_permohonan(user_id, Long.parseLong(jawatan));
		String jumlah_notifikasi = (String) get_notifikasi_permohonan.get("jumlah_Permohonan");
		context.put("jumlah_notifikasi", Long.parseLong(jumlah_notifikasi));
		context.put("jawatan", jawatan);
		context.put("portalRole", portal_role);
		//System.out.println("*** jumlah_notifikasi --- "+jumlah_notifikasi);

		 Hashtable get_notifikasi_pelepasan = null; 
		 get_notifikasi_pelepasan = (Hashtable) notifikasi_pelepasan(user_id);
		 String jumlah_notifikasi_pelepasan = (String)get_notifikasi_pelepasan.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_pelepasan", Long.parseLong(jumlah_notifikasi_pelepasan));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);
		 
		 Hashtable get_notifikasi_tukarguna = null; 
		 get_notifikasi_tukarguna = (Hashtable) notifikasi_tukarguna(user_id);
		 String jumlah_notifikasi_tukarguna = (String)get_notifikasi_tukarguna.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_tukarguna", Long.parseLong(jumlah_notifikasi_tukarguna));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);
		 
		 Hashtable get_notifikasi_penyewaan = null; 
		 get_notifikasi_penyewaan = (Hashtable) notifikasi_penyewaan(user_id);
		 String jumlah_notifikasi_penyewaan = (String)get_notifikasi_penyewaan.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_penyewaan", Long.parseLong(jumlah_notifikasi_penyewaan));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);
		 
		 //return Permohonan 4
		 //PPT
		 context.put("bilPPTDikembali", dikembalikan("51").size() + dikembalikan("52").size());
//		 context.put("vecPPT4", dikembalikan("51"));
//		 context.put("vecPPT8", dikembalikan("52"));
 
		return vm;
		
	}

	public Hashtable notifikasi_permohonan(String userID, long jawatan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
						
//			sql += " SELECT SUM(JUMLAHPERMOHONAN) as jumlahPermohonan FROM ( ";
//			if (jawatan == 9) {
//				sql += "Select (SELECT COUNT(p.flag_semakan_online) FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u, Users_kementerian uk WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian AND f.id_suburusan in ('51','52','53') AND u.user_id ='"
//						+ userID
//						+ "' AND p.flag_semakan_online = 1) as jumlahPermohonan From Dual";
//				sql += " UNION ALL ";
//			} else if (jawatan == 4) {
//				sql += "Select (SELECT COUNT(p.flag_semakan_online) FROM Tblpptpermohonan p, Tblpfdfail f,Tblrujsuburusan su,Tblrujstatus s, Tblrujkementerian k, Users u, Users_kementerian uk WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian AND f.id_suburusan in ('51','52','53') AND u.user_id ='"
//						+ userID
//						+ "' AND p.flag_semakan_online = 2) as jumlahPermohonan From Dual";
//				sql += " UNION ALL ";
//			}
//
//			sql += " SELECT (SELECT COUNT(P.FLAG_STATUS_ONLINE) FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJSUBURUSAN SU,TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN AND F.ID_SUBURUSAN IN ('51','52','53') AND U.USER_ID ='"
//					+ userID
//					+ "' AND P.FLAG_STATUS_ONLINE = 1) AS JUMLAHPERMOHONAN FROM DUAL ";
//			sql += ") ";
			
			if (jawatan == 24) {
				/*sql += " SELECT SUM (jumlahpermohonan) AS jumlahpermohonan "
						+" FROM (SELECT (SELECT COUNT (p.flag_semakan_online) "
						+" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
						+" users u, users_kementerian uk "
						+" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
						+" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
						+" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userID + "' "
						+" AND p.flag_semakan_online = 1) AS jumlahpermohonan FROM DUAL "
						+" UNION ALL "
						+" SELECT (SELECT COUNT (p.flag_status_online) "
						+" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
						+" users u, users_kementerian uk "
						+" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
						+" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
						+" AND f.id_suburusan IN ('51', '52', '53') AND U.USER_ID ='" + userID + "' "
						+" AND p.flag_status_online = 1) AS jumlahpermohonan FROM DUAL)";*/	
				/*sql += " SELECT (SELECT COUNT (p.flag_status_online) "
				        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
				        +" users u, users_kementerian uk "
				        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
				        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
				        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userID + "' "
				        +" AND p.flag_status_online = 'Y' AND p.flag_status_online = 1) AS jumlahpermohonan FROM DUAL ";*/
				
				sql += " SELECT SUM (JUMLAHPERMOHONAN) AS JUMLAHPERMOHONAN " +
						" FROM (SELECT (SELECT COUNT (P.FLAG_STATUS_ONLINE) " +
						" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSUBURUSAN SU, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK " +
						" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
						" AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
						" AND F.ID_SUBURUSAN IN ('51', '52', '53') AND U.USER_ID = '" + userID + "' " +
						/*" AND P.FLAG_STATUS_ONLINE = 'Y' " +*/
						" AND P.FLAG_STATUS_ONLINE = 1) AS JUMLAHPERMOHONAN FROM DUAL " +
						" UNION ALL " +
						" SELECT (SELECT COUNT (P.FLAG_SEMAKAN_ONLINE) " +
						" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJSUBURUSAN SU, TBLRUJSTATUS S, TBLRUJKEMENTERIAN K, USERS U, USERS_KEMENTERIAN UK " +
						" WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_SUBURUSAN = SU.ID_SUBURUSAN AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
						" AND U.USER_ID = UK.USER_ID AND P.ID_STATUS = S.ID_STATUS AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN " +
						" AND F.ID_SUBURUSAN IN ('51', '52', '53') AND U.USER_ID = '" + userID + "' " +
						" AND P.FLAG_SEMAKAN_ONLINE = 4) AS JUMLAHPERMOHONAN FROM DUAL) ";
			} else if (jawatan == 9) {
				sql += " SELECT (SELECT COUNT (p.flag_semakan_online) "
				        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
				        +" users u, users_kementerian uk "
				        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
				        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
				        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userID + "' "
				        +" AND p.flag_semakan_online = 1) AS jumlahpermohonan FROM DUAL ";
			} else if (jawatan == 4) {
				sql += " SELECT (SELECT COUNT (p.flag_semakan_online) "
				        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
				        +" users u, users_kementerian uk "
				        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
				        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
				        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userID + "' "
				        +" AND p.flag_semakan_online = 2) AS jumlahpermohonan FROM DUAL ";
			} else { 
				sql += " SELECT (SELECT COUNT (p.flag_semakan_online) "
				        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
				        +" users u, users_kementerian uk "
				        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
				        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
				        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userID + "' "
				        +" AND p.flag_semakan_online = 2) AS jumlahpermohonan FROM DUAL "; 
			}

			///myLogger.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			System.out.println("**** sql PPT --- "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			//System.out.println("**** sql PPT --- "+sql);

			Hashtable h;
			h = new Hashtable();
			// h.put("jumlah_Permohonan", "0");
			while (rs.next()) {
				h.put("jumlah_Permohonan",
						rs.getString("jumlahPermohonan") == null ? "0" : rs
								.getString("jumlahPermohonan"));
			}
			return h;

			/*
			 * } else { Hashtable h; h = new Hashtable();
			 * h.put("jumlah_Permohonan", "0"); return h; }
			 */
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable notifikasi_penyewaan(String userID)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT (SELECT COUNT (*) "
				  + " FROM tblphpulasanteknikal, tblpermohonan, tblphppemohon, tblpfdfail, tblphphakmilikpermohonan, "
				       + " tblphphakmilik, users, users_kementerian "
				 + " WHERE tblphpulasanteknikal.flag_status = 1 "
				   + " AND tblphpulasanteknikal.flag_aktif = 'Y' "
				   + " AND tblphpulasanteknikal.id_permohonan = tblpermohonan.id_permohonan "
				   + " AND tblpermohonan.id_pemohon = tblphppemohon.id_pemohon "
				   + " AND tblpermohonan.id_fail = tblpfdfail.id_fail "
				   + " AND tblpermohonan.id_permohonan = tblphphakmilikpermohonan.id_permohonan "
				   + " AND tblphphakmilikpermohonan.id_hakmilikpermohonan =  tblphphakmilik.id_hakmilikpermohonan "
				   + " AND tblpfdfail.id_seksyen = 4 "
				   + " AND tblpfdfail.id_urusan IN (7, 12, 13) "
				   + " AND tblphphakmilikpermohonan.flag_hakmilik = 'U' "
				   + " AND tblpermohonan.id_status NOT IN (1610212, 1610207, 1610208) "
				   + " AND users.user_id = users_kementerian.user_id "
				   + " AND users_kementerian.id_agensi = tblphpulasanteknikal.id_agensi "
				   + " AND users.user_id = '" + userID + "') AS jumlahpermohonan "
				  + " FROM DUAL ";
			
			System.out.println("JUMLAH DOKUMEN :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("JUMLAHPERMOHONAN",
						rs.getString("JUMLAHPERMOHONAN"));
			}
			return h;

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable notifikasi_tukarguna(String userID)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT (SELECT COUNT (*) "
				  + " FROM tblphpulasanteknikal, tblpermohonan, tblphppemohon, tblpfdfail, tblphphakmilikpermohonan, "
				       + " tblphphakmilik, users, users_kementerian "
				 + " WHERE tblphpulasanteknikal.flag_status = 1 "
				   + " AND tblphpulasanteknikal.flag_aktif = 'Y' "
				   + " AND tblphpulasanteknikal.id_permohonan = tblpermohonan.id_permohonan "
				   + " AND tblpermohonan.id_pemohon = tblphppemohon.id_pemohon "
				   + " AND tblpermohonan.id_fail = tblpfdfail.id_fail "
				   + " AND tblpermohonan.id_permohonan = tblphphakmilikpermohonan.id_permohonan "
				   + " AND tblphphakmilikpermohonan.id_hakmilikpermohonan = tblphphakmilik.id_hakmilikpermohonan "
				   + " AND tblpfdfail.id_seksyen = 4 "
				   + " AND tblpfdfail.id_urusan = '6' "
				   + " AND tblpfdfail.id_suburusan = '33' "
				   + " AND tblphphakmilikpermohonan.flag_hakmilik = 'U' "
				   + " AND tblpermohonan.id_status NOT IN (1610212, 1610207, 1610208) "
				   + " AND users.user_id = users_kementerian.user_id "
				   + " AND users_kementerian.id_agensi = tblphpulasanteknikal.id_agensi "
				   + " AND users.user_id = '" + userID + "') AS jumlahpermohonan "
				  + " FROM DUAL ";
			
			// myLogger.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("JUMLAHPERMOHONAN",
						rs.getString("JUMLAHPERMOHONAN"));
			}
			return h;

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Hashtable notifikasi_pelepasan(String userID)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT (SELECT COUNT (*) "
				  + " FROM tblphpulasanteknikal, tblpermohonan, tblphppemohon, tblpfdfail, tblphphakmilikpermohonan, tblphphakmilik, "
				  + " users, users_kementerian "
				 + " WHERE tblphpulasanteknikal.flag_status = 1 "
				   + " AND tblphpulasanteknikal.flag_aktif = 'Y' "
				   + " AND tblphpulasanteknikal.id_permohonan = tblpermohonan.id_permohonan "
				   + " AND tblpermohonan.id_pemohon = tblphppemohon.id_pemohon "
				   + " AND tblpermohonan.id_fail = tblpfdfail.id_fail "
				   + " AND tblpermohonan.id_permohonan = tblphphakmilikpermohonan.id_permohonan "
				   + " AND tblphphakmilikpermohonan.id_hakmilikpermohonan = tblphphakmilik.id_hakmilikpermohonan "
				   + " AND tblpfdfail.id_seksyen = 4 "
				   + " AND tblpfdfail.id_urusan = '6' "
				   + " AND tblpfdfail.id_suburusan = '34' "
				   + " AND tblphphakmilikpermohonan.flag_hakmilik = 'U' "
				   + " AND tblpermohonan.id_status NOT IN (1610212, 1610207, 1610208) "
				   + " AND users.user_id = users_kementerian.user_id "
				   + " AND users_kementerian.id_agensi = tblphpulasanteknikal.id_agensi "
				   + " AND users.user_id = '" + userID + "') AS jumlahpermohonan "
				  + " FROM DUAL ";
			
			// 
			myLog.info("notifikasi_pelepasan :sql="+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("JUMLAHPERMOHONAN",
						rs.getString("JUMLAHPERMOHONAN"));
			}
			return h;

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public Hashtable get_jawatan(String login) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT u.user_id, u.user_login, ui.id_jawatan, j.KETERANGAN FROM USERS u, USERS_INTERNAL ui, TBLRUJJAWATAN j WHERE u.user_id = ui.user_id AND ui.id_jawatan = j.ID_JAWATAN AND u.user_login = '"
					+ login + "'";

			// myLogger.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			System.out.println("SQL NK DPTKAN JAWATAN DIA : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("jawatan", rs.getString("id_jawatan"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
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