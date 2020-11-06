package ekptg.view.online;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.ResourceBundle;
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
	private final String PATHVER = ResourceBundle.getBundle("file").getString("ver_htp")+"/";
	private static final String PATH = "app/online/manuUtama/";
	private String vm = PATH + "frmMenuUtamaKJP.jsp";

	private IHtp iErr = null;
	//return Permohonan 1
	private IStatus iStatus = null;
	String idKementerian = "";
	Vector listDetailKJP = null;

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		listDetailKJP = getIdNegeriKJPByUserId(userId);
		if (!listDetailKJP.isEmpty() && listDetailKJP.size() > 0) {
			Hashtable hashRayuanDB = (Hashtable) listDetailKJP.get(0);
			idKementerian = hashRayuanDB.get("idKementerian").toString();
			myLog.info("IDKEMENTERIAN="+hashRayuanDB.get("idKementerian").toString());

		}

		this.context.put("idKementerian", idKementerian);	
		
		Pengumuman logic = new Pengumuman();
		// String portal_role = (String) session.getAttribute("_portal_role");
		// razman comment
		String portal_role = (String) session.getAttribute("myrole");
		String user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		myLog.info("*** user_negeri_login :"+user_negeri_login);

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
		String jumlah_notifikasi_permohonan = (String) get_notifikasi_permohonan.get("jumlah_Permohonan");
		context.put("jumlah_notifikasi_permohonan", Long.parseLong(jumlah_notifikasi_permohonan));
		context.put("jawatan", jawatan);
		context.put("portalRole", portal_role);
		//System.out.println("*** jumlah_notifikasi --- "+jumlah_notifikasi);
		
		Hashtable get_notifikasi_pembayaran = null;
		get_notifikasi_pembayaran = (Hashtable) notifikasi_pembayaran(user_id, Long.parseLong(jawatan));
		String jumlah_pembayaran = (String) get_notifikasi_pembayaran.get("jumlah_Pembayaran");
		context.put("jumlah_notifikasi_pembayaran", Long.parseLong(jumlah_pembayaran));
		context.put("jawatan", jawatan);
		context.put("portalRole", portal_role);

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

		 Hashtable get_notifikasi_tukarguna1 = null;
		 get_notifikasi_tukarguna1 = (Hashtable) notifikasi_tukarguna1(user_id);
		 String jumlah_notifikasi_tukarguna1 = (String)get_notifikasi_tukarguna1.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_tukarguna1", Long.parseLong(jumlah_notifikasi_tukarguna1));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);

		 Hashtable get_notifikasi_penyewaan = null;
		 get_notifikasi_penyewaan = (Hashtable) notifikasi_penyewaan(user_id);
		 String jumlah_notifikasi_penyewaan = (String)get_notifikasi_penyewaan.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_penyewaan", Long.parseLong(jumlah_notifikasi_penyewaan));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);

		 Hashtable get_notifikasi_penawaran = null;
		 get_notifikasi_penawaran = (Hashtable) notifikasi_penawaran(user_id);
		 String jumlah_notifikasi_penawaran = (String)get_notifikasi_penawaran.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_penawaran", Long.parseLong(jumlah_notifikasi_penawaran));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);
		 
		 Hashtable get_notifikasi_penerimaTawaran = null;
		 get_notifikasi_penerimaTawaran = (Hashtable) notifikasi_penerimaTawaran(idKementerian, user_id);
		 String jumlah_notifikasi_penerimaTawaran = (String)get_notifikasi_penerimaTawaran.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_penerimaTawaran", Long.parseLong(jumlah_notifikasi_penerimaTawaran));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);

		 Hashtable get_notifikasi_MOF = null;
		 get_notifikasi_MOF = (Hashtable) notifikasi_MOF( user_id);
		 String jumlah_notifikasi_MOF = (String)get_notifikasi_MOF.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_MOF", Long.parseLong(jumlah_notifikasi_MOF));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);

		 Hashtable get_notifikasi_apb = null;
		 get_notifikasi_apb = (Hashtable) notifikasi_apb(user_id);
		 String jumlah_notifikasi_apb = (String)get_notifikasi_apb.get("JUMLAHPERMOHONAN");
		 context.put("jumlah_notifikasi_apb", Long.parseLong(jumlah_notifikasi_apb));
		 context.put("jawatan", jawatan); context.put("portalRole", portal_role);
		 //return Permohonan 4
		 //PPT
		 context.put("bilPPTDikembali", dikembalikan("51").size() + dikembalikan("52").size());
//		 context.put("vecPPT4", dikembalikan("51"));
//		 context.put("vecPPT8", dikembalikan("52"));
		 
		String command = getParam("command");

		if(command.equals("getdikembalikanHTP")) {
				
//				String id_cukaitemp = getParam("id_cukaitemp");				
//				if(id_cukaitemp!=""){
//					updateRead(session,id_cukaitemp);
//				}
				
				this.context.put("div_senaraidikembalikan", "Y");
				//Vector senaraiDikembalikan = DBListKemaskiniCukai(session);
				//this.context.put("senaraiDikembalikan", senaraiDikembalikan);
				vm = "app/htp/"+PATHVER+"/dashboard/div_SenaraiFailDikembalikan.jsp";
			
		}

		return vm;

	}
	
	
	public Vector getIdNegeriKJPByUserId(String userId) throws Exception {
		Db db = null;
		String sql = "";
		Hashtable h;
		Vector listDetailKJP = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.USER_ID, A.USER_NAME, C.ID_NEGERI, B.ID_KEMENTERIAN, B.ID_AGENSI FROM USERS A, USERS_KEMENTERIAN B, TBLRUJAGENSI C, TBLRUJKEMENTERIAN D "
					+ " WHERE A.USER_ID = B.USER_ID AND B.ID_AGENSI = C.ID_AGENSI AND B.ID_KEMENTERIAN = D.ID_KEMENTERIAN AND A.USER_ID = '"
					+ userId + "'";

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("listDetailKJP::::: "+sql);

			if (rs.next()) {
				h = new Hashtable();
				h.put("userId", rs.getString("USER_ID").toString());
				h.put("idNegeri", rs.getString("ID_NEGERI").toString());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN").toString());
				h.put("idAgensi", rs.getString("ID_AGENSI").toString());
				h.put("namaPemohon", rs.getString("USER_NAME").toString());
				listDetailKJP.addElement(h);

				return listDetailKJP;
			} else {
				return listDetailKJP;
			}

		} finally {
			if (db != null)
				db.close();
		}
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

			myLog.info("JUMLAH DOKUMEN PERMOHONAN:"+sql.toUpperCase());
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
	
	public Hashtable notifikasi_pembayaran(String userID, long jawatan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();


			
				sql += " SELECT (SELECT COUNT (p.flag_peruntukan) "
				        +" FROM tblpptpermohonan p, tblpfdfail f, tblrujsuburusan su, tblrujstatus s, tblrujkementerian k, "
				        +" users u, users_kementerian uk "
				        +" WHERE f.id_fail = p.id_fail AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian "
				        +" AND u.user_id = uk.user_id AND p.id_status = s.id_status AND uk.id_kementerian = k.id_kementerian "
				        +" AND f.id_suburusan IN ('51', '52', '53') AND u.user_id ='" + userID + "' "
				        + " AND UPPER (s.id_status) = '76' ) AS jumlahpermohonan FROM DUAL ";
			

			myLog.info("JUMLAH DOKUMEN PEMBAYARAN :"+sql.toUpperCase());
			System.out.println("**** sql PPT --- "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			//System.out.println("**** sql PPT --- "+sql);

			Hashtable h;
			h = new Hashtable();
			// h.put("jumlah_Permohonan", "0");
			while (rs.next()) {
				h.put("jumlah_Pembayaran",
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
	
	public Hashtable notifikasi_penawaran(String userID)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();


			sql = "SELECT (SELECT COUNT (*) "
					+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P"
					+ " ,USERS_KEMENTERIAN UK, USERS H "
					+ " ,TBLRUJSTATUS RS, tblphppemohon C, tblhtphakmilikagensi D,"
					+ " tblphphakmilikpermohonan E"
					+ " WHERE F.ID_FAIL = P.ID_FAIL"
					+ " AND F.ID_SUBURUSAN = '32'"
					+ " AND P.ID_STATUS = RS.ID_STATUS "
					+ " AND P.ID_STATUS NOT IN (1610199)"
					+ " AND F.ID_KEMENTERIAN = UK.ID_KEMENTERIAN "
					+ " AND P.ID_PEMOHON = C.ID_PEMOHON "
					+ " AND P.ID_PERMOHONAN = E.ID_PERMOHONAN "
					+ " AND H.USER_ID = UK.USER_ID "
					+ " AND E.ID_HAKMILIKAGENSI = D.ID_HAKMILIKAGENSI "
					+ " AND UK.USER_ID = '" + userID + "') AS jumlahpermohonan "
					+ " FROM DUAL ";

			myLog.info("JUMLAH DOKUMEN PENAWARAN:"+sql.toUpperCase());
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
	
	public Hashtable notifikasi_penerimaTawaran(String idKementerian, String userID) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT (SELECT COUNT (*) "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, "
					+ " TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, "
					+ " TBLPHPHAKMILIK F, USERS H, TBLRUJNEGERI RUJNEGERI "
					+ " WHERE A.ID_SEKSYEN = 4 "
					+ " AND A.ID_URUSAN = '6' "
					+ " AND A.ID_SUBURUSAN = '32' "
					+ " AND A.ID_FAIL = B.ID_FAIL "
					+ " AND B.ID_STATUS = D.ID_STATUS"
					+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+)"
					+ " AND F.ID_NEGERI = RUJNEGERI.ID_NEGERI(+) "
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON "
					+ " AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "
					+ " AND A.NO_FAIL IS NOT NULL"
					+ " AND A.ID_MASUK = H.USER_ID(+)"
					+ " AND D.ID_STATUS = '1610210' " //TAWARAN
					+ " AND C.ID_KEMENTERIAN != '"+idKementerian +"') "
					+ " AS JUMLAHPERMOHONAN FROM DUAL "
					; 

			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("JUMLAH DOKUMEN PENAWARAN:"+sql.toUpperCase());
			

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
				   //+ " AND users_kementerian.id_agensi = tblphpulasanteknikal.id_agensi "
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

	public Hashtable notifikasi_MOF(String userID)
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
				   + " AND tblpfdfail.id_suburusan = '34' "
				   + " AND tblphphakmilikpermohonan.flag_hakmilik = 'U' "
				   + " AND TBLPERMOHONAN.ID_STATUS = '1610203'"
				   + " AND users.user_id = users_kementerian.user_id "
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
			System.out.println("notifikasi_tukarguna :"+sql.toUpperCase());
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

	public Hashtable notifikasi_tukarguna1(String userID)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT (SELECT COUNT (*) "
					+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P,USERS_KEMENTERIAN UK,TBLRUJSTATUS RS "
					//+ " ,USERS H "
					//+ " ,tblphppemohon C, tblhtphakmilikagensi D,"
					//+ " tblphphakmilikpermohonan E"
					+ " WHERE F.ID_FAIL = P.ID_FAIL"
					+ " AND F.ID_SUBURUSAN = '33'"
					+ " AND P.ID_STATUS = RS.ID_STATUS "
					+ " AND P.ID_STATUS NOT IN (1610199)"
					//+ " AND F.ID_KEMENTERIAN = UK.ID_KEMENTERIAN "
					//+ " AND P.ID_PEMOHON = C.ID_PEMOHON "
					//+ " AND P.ID_PERMOHONAN = E.ID_PERMOHONAN "
					//+ " AND H.USER_ID = UK.USER_ID "
					//+ " AND E.ID_HAKMILIKAGENSI = D.ID_HAKMILIKAGENSI "
					+ " AND UK.USER_ID = '" + userID + "') AS jumlahpermohonan "
					+ " FROM DUAL ";

			// myLogger.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("notifikasi_tukarguna :"+sql.toUpperCase());
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

	public Hashtable notifikasi_apb(String userID)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			/*sql = "SELECT (SELECT COUNT (*) "
					+ " FROM TBLPFDFAIL F, TBLPERMOHONAN P"
					+ " ,USERS_KEMENTERIAN UK, USERS H "
					+ " ,TBLRUJSTATUS RS, tblphppemohon C, tblhtphakmilikagensi D,"
					+ " tblphphakmilikpermohonan E"
					+ " WHERE F.ID_FAIL = P.ID_FAIL"
					+ " AND F.ID_SUBURUSAN = '33'"
					+ " AND P.ID_STATUS = RS.ID_STATUS "
					+ " AND P.ID_STATUS NOT IN (1610199)"
					+ " AND F.ID_KEMENTERIAN = UK.ID_KEMENTERIAN "
					+ " AND P.ID_PEMOHON = C.ID_PEMOHON "
					+ " AND P.ID_PERMOHONAN = E.ID_PERMOHONAN "
					+ " AND H.USER_ID = UK.USER_ID "
					+ " AND E.ID_HAKMILIKAGENSI = D.ID_HAKMILIKAGENSI "
					+ " AND UK.USER_ID = '" + userID + "') AS jumlahpermohonan "
					+ " FROM DUAL ";*/

			sql = "SELECT (SELECT COUNT (*) "
					+ " FROM TBLPHPULASANTEKNIKAL, TBLPERMOHONAN, TBLPHPPEMOHON, TBLPFDFAIL, TBLPHPPMOHONNJDUALPERTAMA, USERS, USERS_KEMENTERIAN"
					+ " WHERE TBLPHPULASANTEKNIKAL.FLAG_STATUS = 1 AND TBLPHPULASANTEKNIKAL.FLAG_AKTIF = 'Y'"
					+ " AND TBLPHPULASANTEKNIKAL.ID_PERMOHONAN = TBLPERMOHONAN.ID_PERMOHONAN"
					+ " AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON AND TBLPERMOHONAN.ID_FAIL = TBLPFDFAIL.ID_FAIL"
					+ " AND TBLPHPPMOHONNJDUALPERTAMA.ID_PERMOHONAN = TBLPERMOHONAN.ID_PERMOHONAN"
					+ " AND TBLPFDFAIL.ID_SEKSYEN = 4 AND TBLPFDFAIL.ID_URUSAN = 9 AND TBLPFDFAIL.ID_SUBURUSAN = 57"
					+ " AND TBLPERMOHONAN.ID_STATUS NOT IN (1610212, 1610207, 1610208)"
					+ " AND TBLPHPULASANTEKNIKAL.ID_MENTERI = USERS_KEMENTERIAN.ID_KEMENTERIAN"
					+ " AND USERS_KEMENTERIAN.ID_AGENSI = TBLPHPULASANTEKNIKAL.ID_AGENSI"
					+ " AND USERS.USER_ID = USERS_KEMENTERIAN.USER_ID AND USERS.USER_ID = '" + userID + "') AS jumlahpermohonan "
					+ " FROM DUAL ";

			// myLogger.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("notifikasi_apb :"+sql.toUpperCase());
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