package ekptg.view.pdt.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

import ekptg.helpers.Utils;
import ekptg.view.admin.Pengumuman;

@SuppressWarnings("serial")
public class FrmDashboard extends AjaxBasedModule {
	
	private final String PATH="app/pdt/utiliti/";
	static Logger myLog = Logger.getLogger(ekptg.view.pdt.utiliti.FrmDashboard.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	Pengumuman logic = new Pengumuman();
	
	List listFail = null;
	//4.8.17
	List listAktaPindaan = null;
	List listDokumen = null;
	List listEnakmen = null;
	List listEnakmenPindaan = null;
	List listKeputusanMahkamah = null;
	List listPUU = null;
	List listPekeliling = null;
	
	@Override
	public String doTemplate2() throws Exception {	
		HttpSession session = this.request.getSession();
		Hashtable get_stat = null;
		String userId = (String)session.getAttribute("_ekptg_user_id");
		String portal_role = (String)session.getAttribute("myrole");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		Vector list_memo_aktif = null;	
		context.put("portal_role",portal_role);
		String command = getParam("command");
		
		//Carian Fail
		if (command.equals("doCarianFail")) {
			return doCarianFail(session);
		}else if (command.equals("doCloseCarianFail")) {
			return doCloseCarianFail(session);
		}else if (command.equals("doGetListFail")) {
			return doGetListFail(session);
		} else if (command.equals("doCloseListFail")) {
			return doCloseListFail(session);
		}
		//Carian Akta Pindaan
		else if (command.equals("doCarianAktaPindaan")) {
			return doCarianAktaPindaan(session);
		}else if (command.equals("doCloseAktaPindaan")) {
			return doCloseAktaPindaan(session);
		}else if (command.equals("doGetListAktaPindaan")) {
			return doGetListAktaPindaan(session);
		} else if (command.equals("doCloseListAktaPindaan")) {
			return doCloseListAktaPindaan(session);
		}
		
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
		context.put("list_memo_aktif",list_memo_aktif);

		get_stat = (Hashtable) statistikMaklumat();
		String negeriServer = (String)get_stat.get("NAMA_NEGERI_SERVER");
		String jumlahKeseluruhan = (String)get_stat.get("JUMLAH_KESELURUHAN");
		String fail01 = (String)get_stat.get("JUMLAH_PEMBERIMILIKAN");
		String fail10 = (String)get_stat.get("JUMLAH_PERIZAPAN");
		String fail03 = (String)get_stat.get("JUMLAH_PAJAKAN");
		String fail309 = (String)get_stat.get("JUMLAH_PKECIL");
		String fail108 = (String)get_stat.get("JUMLAH_GADAIAN");
		String fail02 = (String)get_stat.get("JUMLAH_PEMBELIAN");
		String fail04 = (String)get_stat.get("JUMLAH_PENSWASTAAN");
		String fail05 = (String)get_stat.get("JUMLAH_MAHKAMAH");
		String fail06 = (String)get_stat.get("JUMLAH_PUU");
		String fail07 = (String)get_stat.get("JUMLAH_MAKLUMATTRM");
		String fail08 = (String)get_stat.get("JUMLAH_DOCTRM");

		context.put("negeriSever", negeriServer);
		context.put("jumlahKeseluruhan", jumlahKeseluruhan);
		context.put("failPemberimilikan", fail01);
		context.put("failPerizapan", fail10);
		context.put("failPajakan", fail03);
		context.put("failPKecil", fail309);
		context.put("failGadaian", fail108);
		context.put("failPembelian", fail02);
		context.put("failPenswastaan", fail04);
		context.put("failMahkamah", fail05);
		context.put("failPUU", fail06);
		context.put("failMaklumatTRM", fail07);
		context.put("failDocTRM", fail08);

		context.put("failPemberimilikann", String.valueOf(get_stat.get("JUMLAH_PEMBERIMILIKANN")));
		context.put("failPerizapann", String.valueOf(get_stat.get("JUMLAH_PERIZAPANN")));
		context.put("enakmenBaru", String.valueOf(get_stat.get("JUMLAH_PAJAKANN")));
		context.put("failPKeciln", String.valueOf(get_stat.get("JUMLAH_PKECILN")));
		context.put("failGadaiann", String.valueOf(get_stat.get("JUMLAH_GADAIANN")));
		context.put("failPembeliann", String.valueOf(get_stat.get("JUMLAH_PEMBELIANN")));
		context.put("failPenswastaann", String.valueOf(get_stat.get("JUMLAH_PENSWASTAANN")));
		context.put("failMahkamahn", String.valueOf(get_stat.get("JUMLAH_MAHKAMAHN")));
		context.put("failPUUn", String.valueOf(get_stat.get("JUMLAH_PUUN")));
		context.put("failMaklumatTRMn", String.valueOf(get_stat.get("JUMLAH_MAKLUMATTRMN")));
		context.put("failDocTRMn", String.valueOf(get_stat.get("JUMLAH_DOCTRMN")));

		Hashtable get_inbox_notifikasi = null;
		get_inbox_notifikasi = notifikasi(portal_role,user_negeri_login,"","",userId,"NO");
		int notifikasi_inbox = (Integer)get_inbox_notifikasi.get("INBOX");
		context.put("notifikasi_inbox",notifikasi_inbox);
		
		String vm = "app/pdt/utiliti/dashboard.jsp";
		
		return vm;
	}
	
	private String doCarianFail(HttpSession session) throws Exception {
		myLog.info("doCarianFail : "+getParam("search"));
		this.context.put("div_carianFail_open", "Y");
		listFail = ListFail(session, "fail", getParam("search"));
		this.context.put("listFail", listFail);

		return PATH+"/div_carianFail.jsp";
	}
	
	private String doCloseCarianFail(HttpSession session) throws Exception {
		this.context.put("div_carianFail_open", "N");
		return PATH+"/div_carianFail.jsp";
	}
		
	private List ListFail(HttpSession session, String type, String search)
			throws Exception {
		listFail = DBcarianFail(session, type, search);
		this.context.put("listFail", listFail);
		return listFail;
	}
	
	private String doGetListFail(HttpSession session) throws Exception {
		if (!getParam("div_getListFail_open").equals("Y")) {
			this.context.put("div_getListFail_open", "Y");
			listFail = ListFail(session, "fail", getParam("search"));
			this.context.put("listFail", listFail);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFail_open", "N");
		}
		return PATH+"/div_listFail.jsp";
	}
	
	private String doCloseListFail(HttpSession session) throws Exception {
		this.context.put("div_getListFail_open", "N");
		return PATH+"/div_listFail.jsp";
	}
	
	
	//AKTA PINDAAN
		private List ListAktaPindaan(HttpSession session, String type, String search) throws Exception {
			listAktaPindaan = DBcarianAktaPindaan(session, type, search);
			this.context.put("listAktaPindaan", listAktaPindaan);
			return listAktaPindaan;
		}
		private String doCarianAktaPindaan(HttpSession session) throws Exception {
				this.context.put("div_carianAktaPindaan_open", "Y");
				listAktaPindaan = ListAktaPindaan(session, "AktaPindaan", getParam("search"));
				this.context.put("listAktaPindaan", listAktaPindaan);

				return PATH+"/div_carianAktaPindaan.jsp";
			}
		private String doCloseAktaPindaan(HttpSession session) throws Exception {
			this.context.put("div_carianAktaPindaan_open", "N");
			return PATH+"/div_carianAktaPindaan.jsp";
		}
		private String doGetListAktaPindaan(HttpSession session) throws Exception {
			if (!getParam("div_getListAktaPindaan_open").equals("Y")) {
				this.context.put("div_getListAktaPindaan_open", "Y");
				listAktaPindaan = ListAktaPindaan(session, "AktaPindaan", getParam("search"));
				this.context.put("listAktaPindaan", listAktaPindaan);
				this.context.put("search", getParam("search"));
			} else {
				this.context.put("div_getListAktaPindaan_open", "N");
			}
			return PATH+"/div_listAktaPindaan.jsp";
		}
		private String doCloseListAktaPindaan(HttpSession session) throws Exception {
			this.context.put("div_getListAktaPindaan_open", "N");
			return PATH+"/div_carianAktaPindaan.jsp";
		}
		
		//--------------//
	
	//Carian
		@SuppressWarnings("unchecked")
		public List DBcarianFail(HttpSession session, String type, String search)throws Exception {

			String userId = (String) session.getAttribute("_ekptg_user_id");
			String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SimpleDateFormat sdf = null;

			List senaraiFail = null;

			String sql = "";
			Integer count = 0;

			try {

				db = new Db();
				sdf = new SimpleDateFormat("dd/MM/yyyy");
				stmt = db.getStatement();

				sql = "SELECT DISTINCT M.ID_AKTA, M.NO_AKTA, M.NAMA_AKTA, TO_CHAR(M.TARIKH_KUATKUASA, 'dd/MM/yyyy')as TARIKH_KUATKUASA, C.NAMA_SEKSYEN "+
					   "FROM TBLPDTAKTA M, TBLRUJSEKSYEN C " +
					   "WHERE M.ID_SEKSYEN = C.ID_SEKSYEN (+)";

				
				if (type.equals("fail")) {
					if (search != null) {
						if (!search.trim().equals("")) {
							sql += " AND ((" + " UPPER(M.ID_AKTA)) LIKE '%"
									+ search.toUpperCase().trim() + "%' "
									+ " OR UPPER(M.NO_AKTA) LIKE '%"
									+ search.toUpperCase().trim() + "%' "
									+ " OR UPPER(M.NAMA_AKTA) LIKE '%"
									+ search.toUpperCase().trim() + "%' "
									+ " OR UPPER(M.TARIKH_KUATKUASA) LIKE '%"
									+ search.toUpperCase().trim() + "%' "
									+ " OR UPPER(C.NAMA_SEKSYEN) LIKE '%"
									+ search.toUpperCase().trim() + "%' )";
						}
					}
				}
				
				
				sql += " ORDER BY M.ID_AKTA ";
				
				myLog.info("carian dashboard : "+sql);
				stmt.setFetchSize(10);
				rs = stmt.executeQuery(sql);

				int bil = 1;
				senaraiFail = Collections.synchronizedList(new ArrayList());
				Map h = null;

				while (rs.next()) {

					h = Collections.synchronizedMap(new HashMap());
					h.put("Bil", bil);
					h.put("ID_AKTA", rs.getString("ID_AKTA") == null ? "" : rs.getString("ID_AKTA"));
					h.put("NO_AKTA", rs.getString("NO_AKTA") == null ? "" : rs.getString("NO_AKTA"));
					h.put("NAMA_AKTA", rs.getString("NAMA_AKTA") == null ? "" : rs.getString("NAMA_AKTA"));
					h.put("TARIKH_KUATKUASA", rs.getString("TARIKH_KUATKUASA") == null ? "" : rs.getString("TARIKH_KUATKUASA"));
					h.put("NAMA_SEKSYEN", rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));

		
					senaraiFail.add(h);
					bil++;
					count++;
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return senaraiFail;

		}
		
		//Carian Akta Pindaan
				@SuppressWarnings("unchecked")
				public List DBcarianAktaPindaan(HttpSession session, String type, String search)throws Exception {

					String userId = (String) session.getAttribute("_ekptg_user_id");
					String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

					Db db = null;
					ResultSet rs = null;
					Statement stmt = null;
					SimpleDateFormat sdf = null;

					List senaraiAktaPindaan = null;

					String sql = "";
					Integer count = 0;

					try {

						db = new Db();
						sdf = new SimpleDateFormat("dd/MM/yyyy");
						stmt = db.getStatement();

						sql = "SELECT DISTINCT ID_AKTAPINDA, NO_AKTA_PINDAAN, NAMA_AKTA_PINDAAN, TO_CHAR(TARIKH_KUATKUASA, 'dd/MM/yyyy')as TARIKH_KUATKUASA "+
							   "FROM TBLPDTAKTAPINDA " +
							   "WHERE ";

						
						if (type.equals("AktaPindaan")) {
							if (search != null) {
								if (!search.trim().equals("")) {
									sql += "(( UPPER(ID_AKTAPINDA)) LIKE '%"
											+ search.toUpperCase().trim() + "%' "
											+ " OR UPPER(NO_AKTA_PINDAAN) LIKE '%"
											+ search.toUpperCase().trim() + "%' "
											+ " OR UPPER(NAMA_AKTA_PINDAAN) LIKE '%"
											+ search.toUpperCase().trim() + "%' "
											+ " OR UPPER(TARIKH_KUATKUASA) LIKE '%"
											+ search.toUpperCase().trim() + "%' )";
											
								}
							}
						}
						
						
						sql += " ORDER BY ID_AKTAPINDA ";
						
						myLog.info("carian dashboard AKTA PINDAAN: "+sql);
						stmt.setFetchSize(10);
						rs = stmt.executeQuery(sql);

						int bil = 1;
						senaraiAktaPindaan = Collections.synchronizedList(new ArrayList());
						Map h = null;

						while (rs.next()) {

							h = Collections.synchronizedMap(new HashMap());
							h.put("Bil", bil);
							h.put("ID_AKTAPINDA", rs.getString("ID_AKTAPINDA") == null ? "" : rs.getString("ID_AKTAPINDA"));
							h.put("NO_AKTA_PINDAAN", rs.getString("NO_AKTA_PINDAAN") == null ? "" : rs.getString("NO_AKTA_PINDAAN"));
							h.put("NAMA_AKTA_PINDAAN", rs.getString("NAMA_AKTA_PINDAAN") == null ? "" : rs.getString("NAMA_AKTA_PINDAAN"));
							h.put("TARIKH_KUATKUASA", rs.getString("TARIKH_KUATKUASA") == null ? "" : rs.getString("TARIKH_KUATKUASA"));

				
							senaraiAktaPindaan.add(h);
							bil++;
							count++;
						}

					} finally {
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db != null)
							db.close();
					}

					return senaraiAktaPindaan;

				}
	
	public Hashtable jumlah_dokumen(String role,String user_id) throws Exception {
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT (SELECT COUNT(*) FROM TBLPFDLOGDOKUMEN A, TBLPFDTAGDOKUMEN B WHERE A.ID_LOGDOKUMEN = B.ID_DOKUMEN(+) AND A.NO_RUJUKAN LIKE '%' ||''|| '%' and A.id_seksyen =(select id_seksyen from users_internal where user_id='4') and A.id_negeri =(select id_negeri from users_internal where user_id='4')) AS JUMLAH FROM DUAL";
			
			myLog.info("JUMLAH DOKUMEN :"+sql.toUpperCase());
			
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
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT (SELECT COUNT(*) FROM TBLPFDFAIL A, TBLRUJNEGERI B, TBLRUJSEKSYEN C, TBLRUJSTATUS D, TBLRUJSTATUS E,TBLPFDPERGERAKANFAIL F, TBLPFDRUJTARAFKESELAMATAN G WHERE  A.ID_FAIL = F.ID_FAIL(+) AND A.ID_NEGERI = B.ID_NEGERI AND A.ID_SEKSYEN = C.ID_SEKSYEN  AND A.ID_STATUS = D.ID_STATUS(+) AND F.ID_STATUS = E.ID_STATUS(+) AND A.ID_TARAFKESELAMATAN = G.ID_TARAFKESELAMATAN(+) AND A.ID_STATUS <> 999 AND A.ID_URUSAN NOT IN (382,6,108,3,4,2,12,7,8,9,13,17,1,309,108,5,10,11) and a.id_seksyen ='5' and a.id_negeri ='16') AS JUMLAH FROM DUAL";
			
			myLog.info("JUMLAH FAIL :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("Jumlah_Fail", rs.getString("JUMLAH"));
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
			
			myLog.info("--------------- LIST NOTIFICATION DASHBOARD LIST:"+sql.toUpperCase());
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
	public Hashtable statistikMaklumat() throws Exception {	
		Db db = null;
		String sql = "";
		Hashtable get_negeri = (Hashtable) kod_negeri();
		String kod_negeri = (String)get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " " +
				" SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N " +
				" WHERE S.KOD_NEGERI = N.KOD_NEGERI " +
				" ) AS NAMA_NEGERI_SERVER "+
				" ,(" +
				" ( SELECT count(*) FROM TBLPDTAKTA DTA)+ "+
				" (SELECT count(*) FROM TBLPDTAKTAPINDA DTAP)+ "+
				" (SELECT count(*) FROM TBLPDTENAKMEN DTE)+ " +
				" (SELECT count(*) FROM TBLPDTENAKMENPINDA DTEP)+ " +
				" (SELECT count(*) FROM TBLPDTPEKELILING DTP)+ " +
				" (SELECT count(*) FROM TBLPDTDOKUMEN DTD WHERE DTD.ID_SEKSYEN IS NOT NULL)+ " +
				" (SELECT count(*) FROM TBLPDTPERUNDANGAN DTU)+ "+
				" (SELECT count(*) FROM TBLPDTDOKUMEN DTD WHERE DTD.RUJ_MAHKAMAH IS NOT NULL)+ "+
				" (SELECT count(*) FROM TBLPDTPANDANGANUNDANGUTAMA DTPU)+ "+
				" (SELECT count(*) FROM TBLPDTWARTATRM DTW)+ "+
				" (SELECT count(*) FROM TBLPDTDOCTRMUTAMA DTDU) "+
				" ) JUMLAH "+
				" ,( SELECT count(*) FROM TBLPDTAKTA DTA) BIL_AKTA "+
				" ,( SELECT count(*) FROM TBLPDTAKTAPINDA DTAP) BIL_AKTAPINDA "+
				" ,( SELECT count(*) FROM TBLPDTENAKMEN DTE) BIL_ENAKMEN "+
				" ,( SELECT count(*) FROM TBLPDTENAKMENPINDA DTEP) BIL_ENAKMENPINDA "+
				" ,( SELECT count(*) FROM TBLPDTPEKELILING DTP) BIL_PEKELILING "+
				" ,( SELECT count(*) FROM TBLPDTDOKUMEN DTD WHERE DTD.ID_SEKSYEN IS NOT NULL) BIL_DOKUMEN "+
				" ,( SELECT count(*) FROM TBLPDTPERUNDANGAN DTU) BIL_PERUNDANGAN "+
				" ,( SELECT count(*) FROM TBLPDTDOKUMEN DTD WHERE DTD.RUJ_MAHKAMAH IS NOT NULL) BIL_MAHKAMAH "+
				" ,( SELECT count(*) FROM TBLPDTPANDANGANUNDANGUTAMA DTPU) BIL_PUU "+
				" ,( SELECT count(*) FROM TBLPDTWARTATRM DTW) BIL_MAKLUMATTRM "+
				" ,( SELECT count(*) FROM TBLPDTDOCTRMUTAMA DTDU) BIL_DOCTRM "+
				" ,( SELECT count(*) FROM TBLPDTAKTA DTA WHERE (SYSDATE - DTA.TARIKH_MASUK )< 21 OR (DTA.TARIKH_MASUK - SYSDATE)>0) BIL_AKTAN "+
				" ,( SELECT count(*) FROM TBLPDTAKTAPINDA DTAP WHERE (SYSDATE - DTAP.TARIKH_MASUK )< 21 OR (DTAP.TARIKH_MASUK - SYSDATE)>0) BIL_AKTAPINDAN "+
				" ,( SELECT count(*) FROM TBLPDTENAKMEN DTE WHERE (SYSDATE - DTE.TARIKH_MASUK )< 21 OR (DTE.TARIKH_MASUK - SYSDATE)>0) BIL_ENAKMENN "+
				" ,( SELECT count(*) FROM TBLPDTENAKMENPINDA DTEP WHERE (SYSDATE - DTEP.TARIKH_MASUK )< 21 OR (DTEP.TARIKH_MASUK - SYSDATE)>0) BIL_ENAKMENPINDAN "+
				" ,( SELECT count(*) FROM TBLPDTPEKELILING DTP WHERE (SYSDATE - DTP.TARIKH_MASUK )< 21 OR (DTP.TARIKH_MASUK - SYSDATE)>0) BIL_PEKELILINGN "+
				" ,( SELECT count(*) FROM TBLPDTDOKUMEN DTD WHERE DTD.ID_SEKSYEN IS NOT NULL AND (SYSDATE - DTD.TARIKH_MASUK )< 21 OR (DTD.TARIKH_MASUK - SYSDATE)>0) BIL_DOKUMENN "+
				" ,( SELECT count(*) FROM TBLPDTPERUNDANGAN DTU WHERE (SYSDATE - DTU.TARIKH_MASUK )< 21 OR (DTU.TARIKH_MASUK - SYSDATE)>0) BIL_PERUNDANGANN "+
				" ,( SELECT count(*) FROM TBLPDTDOKUMEN DTD WHERE (SYSDATE - DTD.TARIKH_MASUK) < 21 OR (DTD.TARIKH_MASUK - SYSDATE) > 0) BIL_MAHKAMAHN "+
				" ,( SELECT count(*) FROM TBLPDTPANDANGANUNDANGUTAMA DTPU WHERE (SYSDATE - DTPU.TARIKH_MASUK) < 21 OR (DTPU.TARIKH_MASUK - SYSDATE) > 0) BIL_PUUN "+
				" ,( SELECT count(*) FROM TBLPDTWARTATRM DTW WHERE (SYSDATE - DTW.TARIKH_MASUK) < 21 OR (DTW.TARIKH_MASUK - SYSDATE) > 0) BIL_MAKLUMATTRMN "+
				" ,( SELECT count(*) FROM TBLPDTDOCTRMUTAMA DTDU WHERE (SYSDATE - DTDU.TARIKH_MASUK) < 21 OR (DTDU.TARIKH_MASUK - SYSDATE) > 0) BIL_DOCTRMN "+
				" FROM DUAL ";			
			myLog.info(" STATISTIK :"+sql.toUpperCase());
			//(dt.tarikh_masuk - sysdate)< 21
				
			ResultSet rs = stmt.executeQuery(sql);			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("NAMA_NEGERI_SERVER", Utils.isNull(rs.getString("NAMA_NEGERI_SERVER")));
				h.put("JUMLAH_KESELURUHAN", Utils.isNull(rs.getString("JUMLAH")));
				h.put("JUMLAH_PEMBERIMILIKAN",  Utils.isNull(rs.getString("BIL_AKTA")));
				h.put("JUMLAH_PERIZAPAN",  Utils.isNull(rs.getString("BIL_AKTAPINDA")));
				h.put("JUMLAH_PAJAKAN",  Utils.isNull(rs.getString("BIL_ENAKMEN")));
				h.put("JUMLAH_PKECIL",  Utils.isNull(rs.getString("BIL_ENAKMENPINDA")));
				h.put("JUMLAH_GADAIAN",  Utils.isNull(rs.getString("BIL_PEKELILING")));			
				h.put("JUMLAH_PEMBELIAN",  Utils.isNull(rs.getString("BIL_DOKUMEN")));				
				h.put("JUMLAH_PENSWASTAAN",  Utils.isNull(rs.getString("BIL_PERUNDANGAN")));
				h.put("JUMLAH_MAHKAMAH",  Utils.isNull(rs.getString("BIL_MAHKAMAH")));
				h.put("JUMLAH_PUU",  Utils.isNull(rs.getString("BIL_PUU")));
				h.put("JUMLAH_MAKLUMATTRM",  Utils.isNull(rs.getString("BIL_MAKLUMATTRM")));
				h.put("JUMLAH_DOCTRM",  Utils.isNull(rs.getString("BIL_DOCTRM")));
				
				
				h.put("JUMLAH_PEMBERIMILIKANN",  Utils.isNull(rs.getString("BIL_AKTAN")));
				h.put("JUMLAH_PERIZAPANN",  Utils.isNull(rs.getString("BIL_AKTAPINDAN")));
				h.put("JUMLAH_PAJAKANN",  Utils.isNull(rs.getString("BIL_ENAKMENN")));
				h.put("JUMLAH_PKECILN",  Utils.isNull(rs.getString("BIL_ENAKMENPINDAN")));
				h.put("JUMLAH_GADAIANN",  Utils.isNull(rs.getString("BIL_PEKELILINGN")));			
				h.put("JUMLAH_PEMBELIANN",  Utils.isNull(rs.getString("BIL_DOKUMENN")));				
				h.put("JUMLAH_PENSWASTAANN",  Utils.isNull(rs.getString("BIL_PERUNDANGANN")));
				h.put("JUMLAH_MAHKAMAHN",  Utils.isNull(rs.getString("BIL_MAHKAMAHN")));
				h.put("JUMLAH_PUUN",  Utils.isNull(rs.getString("BIL_PUUN")));
				h.put("JUMLAH_MAKLUMATTRMN",  Utils.isNull(rs.getString("BIL_MAKLUMATTRMN")));
				h.put("JUMLAH_DOCTRMN",  Utils.isNull(rs.getString("BIL_DOCTRMN")));
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
			//myLog.info(" KOD_NEGERI :"+sql.toUpperCase());				
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