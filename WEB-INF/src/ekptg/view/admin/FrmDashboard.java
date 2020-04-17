package ekptg.view.admin;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging2;

	@SuppressWarnings("serial")
	public class FrmDashboard extends AjaxBasedModule {
		
		Pengumuman logic = new Pengumuman();
		private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
		
		static Logger myLog = Logger.getLogger(ekptg.view.admin.FrmDashboard.class);
		String skrin_name = "app/admin/Dashboard/indexDashboard.jsp";
		
		@Override
		public String doTemplate2() throws Exception {	
			
			List listTugasan = null;
			List listAktiviti = null;
			List listAduan = null;
			
			List statMonthPLA = null;
			List statModulePLA = null;
			List statJKPTGPLA = null;
			
			List statLogin = null;
			List statDaftar = null;
			
			Integer check_notifikasi_aduan = 0;
			int checkNotifikasiPermohonan = 0;
			
			HttpSession session = this.request.getSession();
			String command = getParam("command");
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
			String USER_LOGIN_ROLE =  (String) session.getAttribute("_portal_role");
			//String role = (String) session.getAttribute("myrole");
			String portal_role = (String)session.getAttribute("myrole");
			context.put("portal_role",portal_role);
			myLog.info("portal_role -- "+portal_role);
			String user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
			
			String cssUser = getCss(portal_role);
			myLog.info("cssUser -- "+cssUser);
			
			Pengumuman logic = new Pengumuman();
			Vector list_memo_aktif = null;
			
			Vector check_notifikasi_index_maklumbalas_aduan = null;
			Vector check_notifikasi_index_maklumbalas_teknikal = null;
			
			list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
			context.put("list_memo_aktif", list_memo_aktif);
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
			this.context.put("TAHUN", sdf.format(new Date()));
			
			String action = getParam("action");
			
			myLog.info(" USER_ID_SYSTEM :" + USER_ID_SYSTEM);
			myLog.info(" USER_LOGIN_SYSTEM :" + USER_LOGIN_SYSTEM);
			myLog.info(" USER_LOGIN_ROLE :" + USER_LOGIN_ROLE);
			
			
			myLog.info(" command :" + command);
			
			//if(command.equals("viewDashboard"))
			//{	
				
				list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
				//list_memo_aktif = logic.getMemo("1", "Aktif", "1", "0","", "", "", "");
				context.put("list_memo_aktif", list_memo_aktif);
				
				Hashtable get_inbox_notifikasi = null;
				get_inbox_notifikasi = notifikasi(portal_role,user_negeri_login,"","",USER_ID_SYSTEM,"NO");
				int notifikasi_inbox = (Integer)get_inbox_notifikasi.get("INBOX");
				context.put("notifikasi_inbox",notifikasi_inbox);
				
				context.put("check_notifikasi_index_maklumbalas_aduan", "");
				context.put("check_notifikasi_index_maklumbalas_teknikal", "");
				

				check_notifikasi_index_maklumbalas_aduan = getListNotifikasi_main_list(portal_role,user_negeri_login,"","1",USER_ID_SYSTEM,"NO");
				context.put("check_notifikasi_index_maklumbalas_aduan",check_notifikasi_index_maklumbalas_aduan);
				
				check_notifikasi_index_maklumbalas_teknikal = getListNotifikasi_main_list(portal_role,user_negeri_login,"","2",USER_ID_SYSTEM,"NO");
				context.put("check_notifikasi_index_maklumbalas_teknikal",check_notifikasi_index_maklumbalas_teknikal);
				
				context.put("check_notifikasi_aduan", notifikasi_Aduan(portal_role, user_negeri_login, "", "", USER_ID_SYSTEM, "NO"));
				
				checkNotifikasiPermohonan = getNotifikasiPemohonan(user_negeri_login);
				this.context.put("checkNotifikasiPermohonan", checkNotifikasiPermohonan);
				 
				
			//skrin_name = "app/admin/Dashboard/dashboard.jsp";
			//}
			//else 
				if(command.equals("getTabStatDataPrint"))
			{	
				String printId = getParam("printId");
				
				if (printId.equals("1")){ //stats log masuk
					
					statLogin = getStatLogin (session);
					this.context.put("statLogin", statLogin);
					
			skrin_name = "app/admin/Dashboard/statsLogMasuk.jsp";
				
				} else if (printId.equals("2")){
			skrin_name = "app/admin/Dashboard/logJumlahDaftar.jsp";
				
				} else if (printId.equals("3")){
					
					listAktiviti = getListAktiviti(session,USER_ID_SYSTEM);
					this.context.put("listAktiviti", listAktiviti);
					//setupPageList(session, action, listAktiviti, "listAktiviti",command,"senaraiAktivitiPengguna");
					
			skrin_name = "app/admin/Dashboard/listLogPengguna.jsp";
				
				} else if (printId.equals("4")){
			skrin_name = "app/admin/Dashboard/printAduanBulan.jsp";
				
				} else if (printId.equals("5")){
			skrin_name = "app/admin/Dashboard/printAduanModul.jsp";
				
				} else if (printId.equals("6")){
			skrin_name = "app/admin/Dashboard/printAduanPejabat.jsp";
				
				} else if (printId.equals("7")){
					
					listAduan = getListAduan(session,USER_ID_SYSTEM);
					this.context.put("listAduan", listAduan);
					//setupPageList(session, action, listAduan, "listAduan",command,"senaraiLogAduan");
					
			skrin_name = "app/admin/Dashboard/printListAduan.jsp";
						
				} else
				{
				skrin_name = "app/admin/Dashboard/indexDashboard.jsp";			
				}
			
			}
			else if(command.equals("getTabDashboard"))
			{	
				/*Vector list_memo_aktif = null;
				list_memo_aktif = logic.getMemo("", "Aktif", "1", "0");
				context.put("list_memo_aktif", list_memo_aktif);*/
				statMonthPLA = getStatMonthPLA (session);
				this.context.put("statMonthPLA", statMonthPLA);
				
				statModulePLA = getStatModulePLA (session);
				this.context.put("statModulePLA", statModulePLA);
				
				statJKPTGPLA = getStatJKPTGPLA (session);
				this.context.put("statJKPTGPLA", statJKPTGPLA);
			
			skrin_name = "app/admin/Dashboard/dashboard_tab.jsp";
			
			}
			else if(command.equals("showSenaraiTugasan"))
			{
				listTugasan = getListTugasan(session,USER_ID_SYSTEM);
				//this.context.put("listTugasan", listTugasan);
				setupPageList(session, action, listTugasan, "listTugasan",command,"senaraiTugasan");
				
			skrin_name = "app/admin/Dashboard/SenaraiTugasan.jsp";
			}
			else if(command.equals("showSenaraiAktiviti"))
			{
				listAktiviti = getListAktiviti(session,USER_ID_SYSTEM);
				//this.context.put("listAktiviti", listAktiviti);
				setupPageList(session, action, listAktiviti, "listAktiviti",command,"senaraiAktivitiPengguna");
				
			skrin_name = "app/admin/Dashboard/SenaraiAktivitiPengguna.jsp";
			}
			
			else if(command.equals("showSenaraiAduan"))
			{
				listAduan = getListAduan(session,USER_ID_SYSTEM);
				//this.context.put("listAktiviti", listAktiviti);
				setupPageList(session, action, listAduan, "listAduan",command,"senaraiLogAduan");
				
			skrin_name = "app/admin/Dashboard/SenaraiLogAduan.jsp";
			}
			else if(command.equals("getTabStatData"))
			{	
				statMonthPLA = getStatMonthPLA (session);
				this.context.put("statMonthPLA", statMonthPLA);
				
				statLogin = getStatLogin (session);
				this.context.put("statLogin", statLogin);
				
				statDaftar = getStatDaftar (session);
				this.context.put("statDaftar", statDaftar);
			
			skrin_name = "app/admin/Dashboard/tabStatData.jsp";
			
			}
			myLog.info(" skrin_name :" + skrin_name);
			return skrin_name;
		}
		
		
		private String getCss(String portal_role) throws SQLException, DbException {
			
			Db db = null;
			String sql = "";
			String css = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
					sql = 	" SELECT THEME FROM ROLE WHERE NAME = '"+portal_role+"'" ;
							
				myLog.info("GET CSS : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h = null;

				while (rs.next()) {
					h = new Hashtable();
					
					if(rs.getString("THEME") != null)
					{
						css = rs.getString("THEME");
					}
				}
				
				myLog.info("css xx -- "+css);
				return css;
			} finally {
				if (db != null)
					db.close();
			}
		}


		public int getNotifikasiPemohonan(String ID_NEGERI) throws Exception {
			
			
			Db db = null;
			String sql = "";
			int jumlah_notifikasi = 0;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
					sql = 	" SELECT COUNT (*) AS TOTAL FROM USERS PPTM, PERMOHONANIDPENGGUNA P, TBLRUJPEJABATJKPTG PEJ, " +
							" TBLRUJNEGERI NEG, TBLRUJJAWATAN J, TBLRUJSEKSYEN S,TBLRUJGRED G " +
							" WHERE P.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) AND P.ID_GRED = G.ID_GRED(+) " +
							" AND P.ID_NEGERI = NEG.ID_NEGERI AND P.ID_PENYEMAK = PPTM.USER_ID(+) AND P.ID_JAWATAN = J.ID_JAWATAN(+) " +
							" AND P.ID_SEKSYEN = S.ID_SEKSYEN(+) AND P.FLAG_STATUS = 1 " ;
					
					if (ID_NEGERI.equals("16")){
					
						sql+=	" AND P.ID_NEGERI = "+ID_NEGERI ;
					
					}else {
						
						sql+=	" AND P.ID_NEGERI != "+ID_NEGERI ;
					}
					
					
					sql+=	" ORDER BY   P.FLAG_STATUS ASC, " +
							" P.TARIKH_PENDAFTARAN DESC, P.ID_JAWATAN ASC, P.USER_NAME ASC " ;

				myLog.info("notifikasi permohonan : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h = null;

				while (rs.next()) {
					h = new Hashtable();
					
					if(rs.getString("TOTAL") != null)
					{
						jumlah_notifikasi = rs.getInt("TOTAL");
					}
				}
				
				myLog.info("jumlah_notifikasi -- "+jumlah_notifikasi);
				return jumlah_notifikasi;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		public Integer notifikasi_Aduan(String role, String id_negeri_user,
				String id_esaduan, String flag_notifikasi, String user_terima,
				String notread) throws Exception {

			Db db = null;
			String sql = "";
			Integer jumlah_notifikasi = 0;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT COUNT (*) AS JUMLAH_ADUAN FROM TBLESADUAN A, "+
						 " USERS U,USERS_INTERNAL UI,TBLESNOTIFIKASI X,TBLRUJPEJABATJKPTG PEJ  "+             
						 " WHERE A.USER_ID = U.USER_ID AND X.ID_ESADUAN = A.ID_ESADUAN "+
						 " AND U.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "+
						 " AND A.ID_STATUS NOT IN ('16125') AND X.ID_ESADUAN = A.ID_ESADUAN "+
						 " AND A.ID_STATUS IS NOT NULL AND X.ID_ESNOTIFIKASI IS NOT NULL" ;
				
						if (!id_esaduan.equals("")) {
							sql += " AND X.ID_ESADUAN = '" + id_esaduan + "' ";
						}
						if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
							sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
						}
						if (!user_terima.equals("")) {
							sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima
									+ "' ";
						}
						if (!flag_notifikasi.equals("")) {
							sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
						}
						if (!notread.equals("")) {
							sql += " AND X.FLAG_READ = '" + notread + "'";
						}
					
						myLog.info("notifikasi aduan : "+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h = null;

				while (rs.next()) {
					h = new Hashtable();
					
					if(rs.getString("JUMLAH_ADUAN") != null)
					{
						jumlah_notifikasi = rs.getInt("JUMLAH_ADUAN");
					}
					
//					h.put("JUMLAH_ADUAN", rs.getString("JUMLAH_ADUAN") == null ? ""
//							: rs.getInt("JUMLAH_ADUAN"));
//					h.put("ONLINE_SEK8", rs.getString("ONLINE_SEK8") == null ? ""
//							: rs.getInt("ONLINE_SEK8"));
//					h.put("ONLINE_SEK17", rs.getString("ONLINE_SEK17") == null ? ""
//							: rs.getInt("ONLINE_SEK17"));
//					h.put("FAIL_PINDAH", rs.getString("FAIL_PINDAH") == null ? ""
//							: rs.getInt("FAIL_PINDAH"));
//					h.put("INBOX",
//							rs.getString("INBOX") == null ? "" : rs.getInt("INBOX"));
//					h.put("TOTAL_KIV",
//							rs.getString("TOTAL_KIV") == null ? "" : rs
//									.getInt("TOTAL_KIV"));
//					h.put("KEBENARAN_EDIT",
//							rs.getString("KEBENARAN_EDIT") == null ? "" : rs
//									.getInt("KEBENARAN_EDIT"));

				}
				return jumlah_notifikasi;
			} finally {
				if (db != null)
					db.close();
			}
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getStatDaftar(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List statDaftar = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT MONTH_VALUE, MONTH_DISPLAY, SUM(JUMLAH_DAFTAR) JUMLAH_DAFTAR FROM ";
									
							sql+=	" (SELECT COUNT(*) AS JUMLAH_DAFTAR, MO.MONTH_VALUE , MO.MONTH_DISPLAY  " +
									" FROM USERS U, USERS_INTERNAL UI, WWV_FLOW_MONTHS_MONTH MO " +
									" WHERE  U.USER_ID = UI.USER_ID AND  " +
									" TO_CHAR(U.DATE_REGISTERED,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND " +
									" TO_CHAR(UI.TARIKH_MASUK,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND ( " +
									" UI.FLAG_AKTIF IS NULL OR UI.FLAG_AKTIF = 1 OR U.FLAG_AKTIF IS NULL " +
									" OR U.FLAG_AKTIF = 1) AND ( TO_CHAR(U.DATE_REGISTERED,'MM') = MO.MONTH_VALUE " +
									" OR TO_CHAR(UI.TARIKH_MASUK,'MM') = MO.MONTH_VALUE) " +
									" GROUP BY MO.MONTH_VALUE, MO.MONTH_DISPLAY " ;
							
							sql+= 	" UNION ";
							
							sql+= 	" SELECT COUNT(*) AS JUMLAH_DAFTAR, MO.MONTH_VALUE , MO.MONTH_DISPLAY " +
									" FROM USERS U, USERS_INTEGRASI UI, WWV_FLOW_MONTHS_MONTH MO " +
									" WHERE  U.USER_ID = UI.USER_ID AND " +
									" TO_CHAR(U.DATE_REGISTERED,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND " +
									" TO_CHAR(UI.TARIKH_MASUK,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND ( " +
									" UI.FLAG_AKTIF IS NULL OR UI.FLAG_AKTIF = 1 OR U.FLAG_AKTIF IS NULL OR U.FLAG_AKTIF = 1) " +
									" AND (TO_CHAR(U.DATE_REGISTERED,'MM') = MO.MONTH_VALUE OR " +
									" TO_CHAR(UI.TARIKH_MASUK,'MM') = MO.MONTH_VALUE) " +
									" GROUP BY MO.MONTH_VALUE, MO.MONTH_DISPLAY ";
							
							sql+= 	" UNION "; 
							
							sql+= 	" SELECT COUNT(*) AS JUMLAH_DAFTAR, MO.MONTH_VALUE , MO.MONTH_DISPLAY " +
									" FROM USERS U, USERS_KEMENTERIAN UK, WWV_FLOW_MONTHS_MONTH MO " +
									" WHERE  U.USER_ID = UK.USER_ID AND " +
									" TO_CHAR(U.DATE_REGISTERED,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND " +
									" TO_CHAR(UK.TARIKH_MASUK,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND " +
									" (UK.FLAG_AKTIF IS NULL OR UK.FLAG_AKTIF = 1 OR U.FLAG_AKTIF IS NULL " +
									" OR U.FLAG_AKTIF = 1) AND (TO_CHAR(U.DATE_REGISTERED,'MM') = MO.MONTH_VALUE " +
									" OR TO_CHAR(UK.TARIKH_MASUK,'MM') = MO.MONTH_VALUE) " +
									" GROUP BY MO.MONTH_VALUE, MO.MONTH_DISPLAY ";
							
							sql+= 	" UNION ";
							
							sql+= 	" SELECT COUNT(*) AS JUMLAH_DAFTAR, MO.MONTH_VALUE , MO.MONTH_DISPLAY " +
									" FROM USERS U, USERS_ONLINE UO, WWV_FLOW_MONTHS_MONTH MO " +
									" WHERE  U.USER_ID = UO.USER_ID AND " +
									" TO_CHAR(U.DATE_REGISTERED,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND " +
									" TO_CHAR(UO.TARIKH_MASUK,'YYYY') = TO_CHAR (SYSDATE, 'YYYY') AND ( " +
									" UO.FLAG_AKTIF IS NULL OR UO.FLAG_AKTIF = 1 OR U.FLAG_AKTIF IS NULL OR U.FLAG_AKTIF = 1) " +
									" AND (TO_CHAR(U.DATE_REGISTERED,'MM') = MO.MONTH_VALUE OR TO_CHAR(UO.TARIKH_MASUK,'MM') = MO.MONTH_VALUE)" +
									" GROUP BY MO.MONTH_VALUE, MO.MONTH_DISPLAY)";
							
							sql+= 	" GROUP BY MONTH_VALUE, MONTH_DISPLAY " +
									" ORDER BY MONTH_VALUE ASC ";
							
						
						myLog.debug("SQL statDaftar - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 statDaftar = Collections.synchronizedList(new ArrayList());
						
						 Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
					
							h.put("rowCss",rowCss);
							h.put("MONTH_VALUE",checkIsNull(rs.getString("MONTH_VALUE")));
							h.put("MONTH_DISPLAY",checkIsNull(rs.getString("MONTH_DISPLAY")));
							h.put("JUMLAH_DAFTAR",checkIsNull(rs.getString("JUMLAH_DAFTAR")));
							
							
							statDaftar.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return statDaftar;
					
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getStatLogin(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List statLogin = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT MO.MONTH_VALUE, MO.MONTH_DISPLAY, COUNT (WL.USER_NAME) JUMLAH " +
									" FROM WEB_LOGGER WL, WWV_FLOW_MONTHS_MONTH MO " +
									" WHERE WL.LOG_YEAR = EXTRACT (YEAR FROM SYSDATE) " +
									" AND WL.LOG_MONTH = MO.MONTH_VALUE " +
									" GROUP BY MONTH_VALUE,MONTH_DISPLAY " +
									" ORDER BY MONTH_VALUE ASC ";
						
						myLog.debug("SQL getStatLogin - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 statLogin = Collections.synchronizedList(new ArrayList());
						
						 Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
					
							h.put("rowCss",rowCss);
							h.put("MONTH_VALUE",checkIsNull(rs.getString("MONTH_VALUE")));
							h.put("MONTH_DISPLAY",checkIsNull(rs.getString("MONTH_DISPLAY")));
							h.put("JUMLAH",checkIsNull(rs.getString("JUMLAH")));
							
							
							statLogin.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return statLogin;
					
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getStatJKPTGPLA(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List statJKPTGPLA = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT N.NAMA_NEGERI, COUNT(P.NAMA_PEJABAT) JUMLAH " +
									" FROM TBLESADUAN AD, TBLRUJPEJABATJKPTG P, TBLRUJNEGERI N " +
									" WHERE AD.ID_PEJABAT_PENGADU = P.ID_PEJABATJKPTG " +
									" AND AD.ID_NEGERI_PENGADU = N.ID_NEGERI " +
									" AND TO_CHAR (AD.TARIKH_ADUAN_HANTAR, 'YYYY') = EXTRACT(YEAR FROM sysdate) "+
									" GROUP BY N.NAMA_NEGERI " +
									" ORDER BY NAMA_NEGERI ASC ";
						
						myLog.debug("SQL statJKPTGPLA - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 statJKPTGPLA = Collections.synchronizedList(new ArrayList());
						
						 Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
					
							h.put("rowCss",rowCss);
							h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
						//	h.put("MONTH_DISPLAY",checkIsNull(rs.getString("MONTH_DISPLAY")));
							h.put("JUMLAH",checkIsNull(rs.getString("JUMLAH")));
							
							
							statJKPTGPLA.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return statJKPTGPLA;
					
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getStatModulePLA(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List statModulePLA = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT UPPER (TRM.KOD_JENISMODULESADUAN) AS MODUL, COUNT (ES.LOG_ADUAN) JUMLAH " +
									" FROM   TBLESADUAN ES, TBLRUJJENISMODULESADUAN TRM " +
									" WHERE   ES.ID_STATUS NOT IN (16125) " +
									" AND ES.ID_JENISMODULESADUAN = TRM.ID_JENISMODULESADUAN(+)" +
									" AND TO_CHAR (ES.TARIKH_ADUAN_HANTAR, 'YYYY') = EXTRACT(YEAR FROM sysdate) "+
									" GROUP BY TRM.KOD_JENISMODULESADUAN " +
									" ORDER BY KOD_JENISMODULESADUAN ASC ";
						
						myLog.debug("SQL statModulePLA - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 statModulePLA = Collections.synchronizedList(new ArrayList());
						
						 Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
					
							h.put("rowCss",rowCss);
							h.put("MODUL",checkIsNull(rs.getString("MODUL")));
						//	h.put("MONTH_DISPLAY",checkIsNull(rs.getString("MONTH_DISPLAY")));
							h.put("JUMLAH",checkIsNull(rs.getString("JUMLAH")));
							
							
							statModulePLA.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return statModulePLA;
					
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getStatMonthPLA(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List statMonthPLA = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT MO.MONTH_VALUE, MO.MONTH_DISPLAY, COUNT (AD.LOG_ADUAN ) JUMLAH, EXTRACT(YEAR FROM sysdate) TAHUN " +
									" FROM TBLESADUAN AD, WWV_FLOW_MONTHS_MONTH MO " +
									" WHERE TO_CHAR(AD.TARIKH_ADUAN_HANTAR,'MM') = MO.MONTH_VALUE " +
									" AND TO_CHAR (AD.TARIKH_ADUAN_HANTAR, 'YYYY') = EXTRACT(YEAR FROM sysdate) "+
									" GROUP BY MONTH_VALUE, MONTH_DISPLAY " +
									" ORDER BY MONTH_VALUE ASC";
						
						myLog.debug("SQL statMonthPLA - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 statMonthPLA = Collections.synchronizedList(new ArrayList());
						
						 Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
					
							h.put("rowCss",rowCss);
							h.put("MONTH_VALUE",checkIsNull(rs.getString("MONTH_VALUE")));
							h.put("MONTH_DISPLAY",checkIsNull(rs.getString("MONTH_DISPLAY")));
							h.put("JUMLAH",checkIsNull(rs.getString("JUMLAH")));
							h.put("TAHUN",checkIsNull(rs.getString("TAHUN")));
							
							statMonthPLA.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return statMonthPLA;
					
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListAduan(HttpSession session, String USER_ID_SYSTEM) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listAduan = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT   V.USER_ID, V.USER_LOGIN, V.PENGADU, V.ID_ESADUAN, V.NAMA_SUMBER, V.JENIS_ADUAN, " +
									" V.STATUS, V.LOG_ADUAN, V.NAMA_SKRIN, V.TARIKH_ADUAN, V.TARIKH_TERIMA_ADUAN, V.TARIKH_SELESAI_ADUAN," +
									" V.ADUAN, V.NO_FAIL, V.MODUL, V.ULASAN_TEKNIKAL, V.NEGERI_PENGADU, V.NAMA_PEJABAT, V.FLAG_MASALAH_DB, " +
									" V.FLAG_MASALAH_FLOW, V.FLAG_MASALAH_HW, V.FLAG_MASALAH_PENAMBAHAN, V.FLAG_MASALAH_PERTANYAAN, " +
									" V.FLAG_MASALAH_REPORT, V.FLAG_MASALAH_SKRIN " +
									" FROM   (  SELECT   ES.USER_ID, U.USER_LOGIN, UPPER (U.USER_NAME) AS PENGADU, ES.ID_ESADUAN, " +
									" UPPER (TRSA.NAMA_SUMBER) AS NAMA_SUMBER, UPPER (TRJA.JENIS_ADUAN) AS JENIS_ADUAN, " +
									" UPPER (TRST.KETERANGAN) AS STATUS, ES.LOG_ADUAN, UPPER (ES.NAMA_SKRIN) AS NAMA_SKRIN, " +
									" TO_CHAR (ES.TARIKH_ADUAN_HANTAR, 'DD/MM/YYYY') AS TARIKH_ADUAN, " +
									" TO_CHAR (ES.TARIKH_ADUAN_TERIMA, 'DD/MM/YYYY') AS TARIKH_TERIMA_ADUAN, " +
									" TO_CHAR (ES.TARIKH_SELESAI, 'DD/MM/YYYY') AS TARIKH_SELESAI_ADUAN, UPPER (ES.ADUAN) AS ADUAN, " +
									" ES.NO_FAIL, UPPER (TRM.KETERANGAN) AS MODUL, UPPER (ES.ULASAN_TEKNIKAL) AS ULASAN_TEKNIKAL, " +
									" UPPER (RN.NAMA_NEGERI) AS NEGERI_PENGADU, UPPER (PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, ES.FLAG_MASALAH_DB, " +
									" ES.FLAG_MASALAH_FLOW, ES.FLAG_MASALAH_HW, ES.FLAG_MASALAH_PENAMBAHAN, ES.FLAG_MASALAH_PERTANYAAN, " +
									" ES.FLAG_MASALAH_REPORT, ES.FLAG_MASALAH_SKRIN " +
									" FROM   TBLESADUAN ES, TBLRUJSUMBERESADUAN TRSA, TBLRUJJENISESADUAN TRJA, USERS U, TBLRUJSTATUSESADUAN TRST, " +
									" TBLRUJJENISMODULESADUAN TRM, TBLRUJNEGERI RN, TBLRUJPEJABATJKPTG PEJ " +
									" WHERE ES.ID_SUMBERADUAN = TRSA.ID_SUMBERADUAN(+) AND ES.ID_JENISADUAN = TRJA.ID_JENISADUAN(+) " +
									" AND ES.USER_ID = U.USER_ID AND ES.ID_STATUS = TRST.ID_STATUSESADUAN AND ES.ID_STATUS NOT IN (16125) " +
									" AND ES.ID_JENISMODULESADUAN = TRM.ID_JENISMODULESADUAN(+) AND ES.ID_NEGERI_PENGADU = RN.ID_NEGERI(+) " +
									" AND ES.ID_PEJABAT_PENGADU = PEJ.ID_PEJABATJKPTG(+) " +
									" ORDER BY   ES.TARIKH_ADUAN_HANTAR DESC) V " +
									" WHERE   ROWNUM <= 50 ";
						
					//	myLog.debug("SQL listAktiviti - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listAduan = Collections.synchronizedList(new ArrayList());
						
						Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
							h.put("rowCss",rowCss);
							h.put("BIL",bil);
							h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
							h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
							h.put("PENGADU",rs.getString("PENGADU") == null ? "" : rs.getString("PENGADU"));
							h.put("ID_ESADUAN",rs.getString("ID_ESADUAN") == null ? "" : rs.getString("ID_ESADUAN"));
							h.put("NAMA_SUMBER",rs.getString("NAMA_SUMBER") == null ? "" : rs.getString("NAMA_SUMBER"));
							h.put("JENIS_ADUAN",rs.getString("JENIS_ADUAN") == null ? "" : rs.getString("JENIS_ADUAN"));
							h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
							h.put("LOG_ADUAN",rs.getString("LOG_ADUAN") == null ? "" : rs.getString("LOG_ADUAN"));
							h.put("NAMA_SKRIN",rs.getString("NAMA_SKRIN") == null ? "" : rs.getString("NAMA_SKRIN"));
							h.put("TARIKH_ADUAN",rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN"));
							h.put("ADUAN",rs.getString("ADUAN") == null ? "" : rs.getString("ADUAN"));
							h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
							h.put("MODUL",rs.getString("MODUL") == null ? "" : rs.getString("MODUL"));
							h.put("ULASAN_TEKNIKAL",rs.getString("ULASAN_TEKNIKAL") == null ? "" : rs.getString("ULASAN_TEKNIKAL"));
							
							
							listAduan.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listAduan;
					
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListAktiviti(HttpSession session, String USER_ID_SYSTEM) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listAktiviti = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT   * " +
									" FROM (SELECT U.USER_ID, U.USER_NAME, " +
									" TO_CHAR(WL.LOG_DATE,'DD/MM/YY HH:MI PM') LOG_DATE, " +
									" (CASE AT.JENIS_AKTIVITI WHEN 'DEL' THEN 'DELETE' " +
									" WHEN 'INS' THEN 'INSERT' WHEN 'UPD' THEN 'UPDATE' " +
									" ELSE '-' END) AS JENIS_AKTIVITI, AT.KETERANGAN "+
									" FROM WEB_LOGGER WL, USERS U, TBLAUDITTRAIL AT " +
									" WHERE U.USER_LOGIN = WL.USER_NAME(+) " +
									" AND U.USER_ID = TO_CHAR (AT.ID_MASUK(+)) " +
									" AND WL.LOG_YEAR = TO_CHAR(SYSDATE, 'YYYY') " +
									" ORDER BY   WL.LOG_DATE DESC) " +
									" WHERE   ROWNUM <= 50 ";
						
					//	myLog.debug("SQL listAktiviti - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						listAktiviti = Collections.synchronizedList(new ArrayList());
						
						Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
							h.put("rowCss",rowCss);
							h.put("BIL",bil);
							
							h.put("USER_ID",checkIsNull(rs.getString("USER_ID")));
							h.put("USER_NAME",checkIsNull(rs.getString("USER_NAME")));
							h.put("LOG_DATE",checkIsNull(rs.getString("LOG_DATE")));
							h.put("JENIS_AKTIVITI",checkIsNull(rs.getString("JENIS_AKTIVITI")));
							h.put("KETERANGAN",checkIsNull(rs.getString("KETERANGAN")));
							
							listAktiviti.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listAktiviti;
					
		}
		
		@SuppressWarnings("unchecked")
		public List getListTugasan(HttpSession session, String USER_ID_SYSTEM) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listNegeri = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT DISTINCT (p.id_Fail) id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, " +
									" um.tab_id, um.module_id, f.id_kemaskini " +
									" FROM   Tblpermohonan p, Tblpfdfail f, Tblrujsuburusanstatusfail sf, " +
									" Tblrujsuburusanstatus us, Tblrujstatus s, user_module_template um " +
									" WHERE p.id_Fail = f.id_Fail " +
									" AND p.id_Permohonan = sf.id_Permohonan " +
									" AND sf.id_Suburusanstatus = us.id_Suburusanstatus " +
									" AND us.id_Status = s.id_Status " +
									" AND us.module_id = um.module_id " +
									" AND um.user_login = '"+USER_ID_SYSTEM+"' AND sf.aktif = '1' " +
									" AND f.id_Masuk = '"+USER_ID_SYSTEM+"'"+
									" ORDER BY f.id_kemaskini";
						
						myLog.debug("SQL getListTugasan - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listNegeri = Collections.synchronizedList(new ArrayList());
						Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							String rowCss = "";
							if ( (bil % 2) == 0 )
							{
								rowCss = "row2";
							}
					        else
					        {
					        	rowCss = "row1";
					        }
							h.put("rowCss",rowCss);
							h.put("BIL",bil);
							
							h.put("id_Fail",checkIsNull(rs.getString("id_Fail")));
							h.put("no_Fail",checkIsNull(rs.getString("no_Fail")));
							h.put("tajuk_Fail",checkIsNull(rs.getString("tajuk_Fail")));
							
							h.put("keterangan",checkIsNull(rs.getString("keterangan")));
							h.put("tab_id",checkIsNull(rs.getString("tab_id")));
							h.put("module_id",checkIsNull(rs.getString("module_id")));
							h.put("id_kemaskini",checkIsNull(rs.getString("id_kemaskini")));
							
							listNegeri.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listNegeri;
					
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
		
		Vector list_notifikasi_main_list = null;
		public Vector getListNotifikasi_main_list(String role,String id_negeri_user,String id_esaduan,String flag_notifikasi,String user_terima,String notread) throws Exception {
			list_notifikasi_main_list = new Vector();
			list_notifikasi_main_list.clear();
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				sql = " SELECT X.ID_ESNOTIFIKASI,X.FLAG_NOTIFIKASI,X.ID_USER_NOTIFIKASI_HANTAR," +
				" X.ID_USER_NOTIFIKASI_TERIMA,X.ID_ESADUAN,X.FLAG_READ,S.NAMA_SUMBER,J.JENIS_ADUAN,U.USER_NAME,ST.KETERANGAN AS NAMA_STATUS,A.ID_ESADUAN,A.ID_SUMBERADUAN,A.ID_JENISADUAN,U.USER_ROLE, "+
				" SK.NAMA_SEKSYEN, PEJ.NAMA_PEJABAT,N.NAMA_NEGERI,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL,JM.KETERANGAN AS JENIS_MODULE,JM.KOD_JENISMODULESADUAN AS KOD_MODULE, "+
				" A.USER_ID,A.ID_STATUS,ST.ID_STATUSESADUAN,A.LOG_ADUAN,A.ID_JENISMODULESADUAN,A.NAMA_SKRIN," +
				" TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') AS TARIKH_ADUAN_HANTAR_DATE," +
				" TO_CHAR(A.TARIKH_ADUAN_HANTAR,'hh') AS TARIKH_ADUAN_HANTAR_HOUR," +
				" TO_CHAR(A.TARIKH_ADUAN_HANTAR,'mi') AS TARIKH_ADUAN_HANTAR_MINUTE," +
				" TO_CHAR(A.TARIKH_ADUAN_HANTAR,'am') AS TARIKH_ADUAN_HANTAR_AMPM," +
				" TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy hh:mi am') AS TARIKH_ADUAN_HANTAR," +			
				" A.TARIKH_ADUAN_TERIMA, "+
				" A.TARIKH_SELESAI,A.TAHAP_PENYELESAIAN,A.NAMA_MODUL,A.ALERT_ADMIN,A.ALERT_DEVELOPER,A.ALERT_PENGADU,A.ADUAN,A.TARIKH_KEMASKINI,TO_CHAR(A.TARIKH_KEMASKINI,'dd/mm/yyyy hh:mi am') AS TARIKH_KEMASKINI_ADUAN,A.NO_FAIL, "+
				" A.FLAG_MASALAH_DB,A.FLAG_MASALAH_SKRIN,A.FLAG_MASALAH_REPORT,A.FLAG_MASALAH_HW,A.FLAG_MASALAH_FLOW,A.FLAG_MASALAH_PERTANYAAN,A.FLAG_MASALAH_PENAMBAHAN,A.ULASAN_TEKNIKAL,A.TAHAP_KESUSAHAN "+ 
				" FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "+
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
				//sql += " AND A.ID_NEGERI_PENGADU = '"+id_negeri_user+ "' ";
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
				
				sql +=  " ORDER BY A.TARIKH_KEMASKINI DESC";
				
				//myLogger.info("LIST NOTIFICATION LIST"+sql.toUpperCase());
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Hashtable h = new Hashtable();
					h.put("id_esnotifikasi", rs.getString("ID_ESNOTIFIKASI"));
	h.put("user_id", rs.getString("USER_ID"));
					
					
					if (rs.getString("USER_NAME") == null) {
						h.put("user_name", "");
					} else {
						h.put("user_name", rs.getString("USER_NAME").toUpperCase());
					}
					
					if (rs.getString("USER_ROLE") == null) {
						h.put("user_role", "");
					} else {
						h.put("user_role", rs.getString("USER_ROLE").toUpperCase());
					}
					
					if (rs.getString("NAMA_SEKSYEN") == null) {
						h.put("nama_seksyen", "");
					} else {
						h.put("nama_seksyen", rs.getString("NAMA_SEKSYEN").toUpperCase());
					}
					
					if (rs.getString("NAMA_PEJABAT") == null) {
						h.put("nama_pejabat", "");
					} else {
						h.put("nama_pejabat", rs.getString("NAMA_PEJABAT").toUpperCase());
					}
					
					if (rs.getString("NAMA_NEGERI") == null) {
						h.put("nama_negeri", "");
					} else {
						h.put("nama_negeri", rs.getString("NAMA_NEGERI").toUpperCase());
					}
					
					if (rs.getString("NAMA_DAERAH") == null) {
						h.put("nama_daerah", "");
					} else {
						h.put("nama_daerah", rs.getString("NAMA_DAERAH").toUpperCase());
					}
					
					if (rs.getString("EMEL") == null) {
						h.put("emel", "");
					} else {
						h.put("emel", rs.getString("EMEL"));
					}
					
					if (rs.getString("NO_TEL") == null) {
						h.put("no_tel", "");
					} else {
						h.put("no_tel", rs.getString("NO_TEL"));
					}
					
					if (rs.getString("NAMA_SUMBER") == null) {
						h.put("nama_sumber", "");
					} else {
						h.put("nama_sumber", rs.getString("NAMA_SUMBER"));
					}
					
					if (rs.getString("JENIS_ADUAN") == null) {
						h.put("jenis_aduan", "");
					} else {
						h.put("jenis_aduan", rs.getString("JENIS_ADUAN"));
					}
					
					if (rs.getString("NAMA_STATUS") == null) {
						h.put("nama_status", "");
					} else {
						h.put("nama_status", rs.getString("NAMA_STATUS"));
					}
					
					if (rs.getString("ID_ESADUAN") == null) {
						h.put("id_esaduan", "");
					} else {
						h.put("id_esaduan", rs.getString("ID_ESADUAN"));
					}
					
					if (rs.getString("ID_SUMBERADUAN") == null) {
						h.put("id_sumberaduan", "");
					} else {
						h.put("id_sumberaduan", rs.getString("ID_SUMBERADUAN"));
					}
					
					if (rs.getString("ID_JENISADUAN") == null) {
						h.put("id_jenisaduan", "");
					} else {
						h.put("id_jenisaduan", rs.getString("ID_JENISADUAN"));
					}
					
					if (rs.getString("ID_STATUS") == null) {
						h.put("id_status", "");
					} else {
						h.put("id_status", rs.getString("ID_STATUS"));
					}
					
					if (rs.getString("LOG_ADUAN") == null) {
						h.put("log_aduan", "");
					} else {
						h.put("log_aduan", rs.getString("LOG_ADUAN"));
					}
					
					if (rs.getString("NAMA_SKRIN") == null) {
						h.put("nama_skrin", "");
					} else {
						h.put("nama_skrin", rs.getString("NAMA_SKRIN"));
					}
					
					if (rs.getString("TARIKH_ADUAN_HANTAR") == null) {
						h.put("tarikh_aduan_hantar", "");
					} else {
						h.put("tarikh_aduan_hantar", rs.getString("TARIKH_ADUAN_HANTAR").toUpperCase());
					}
					
					if (rs.getString("TARIKH_ADUAN_HANTAR_DATE") == null) {
						h.put("tarikh_aduan_hantar_date", "");
					} else {
						h.put("tarikh_aduan_hantar_date", rs.getString("TARIKH_ADUAN_HANTAR_DATE").toUpperCase());
					}
					
					if (rs.getString("TARIKH_ADUAN_HANTAR_HOUR") == null) {
						h.put("tarikh_aduan_hantar_hour", "");
					} else {
						h.put("tarikh_aduan_hantar_hour", rs.getString("TARIKH_ADUAN_HANTAR_HOUR").toUpperCase());
					}
					
					if (rs.getString("TARIKH_ADUAN_HANTAR_MINUTE") == null) {
						h.put("tarikh_aduan_hantar_minute", "");
					} else {
						h.put("tarikh_aduan_hantar_minute", rs.getString("TARIKH_ADUAN_HANTAR_MINUTE").toUpperCase());
					}
					
					if (rs.getString("TARIKH_ADUAN_HANTAR_AMPM") == null) {
						h.put("tarikh_aduan_hantar_ampm", "");
					} else {
						h.put("tarikh_aduan_hantar_ampm", rs.getString("TARIKH_ADUAN_HANTAR_AMPM").toUpperCase());
					}
					
					if (rs.getString("TARIKH_ADUAN_TERIMA") == null) {
						h.put("tarikh_aduan_terima", "");
					} else {
						h.put("tarikh_aduan_terima", Format.format(rs.getDate("TARIKH_ADUAN_TERIMA")));
					}
					
					if (rs.getString("TARIKH_SELESAI") == null) {
						h.put("tarikh_selesai", "");
					} else {
						h.put("tarikh_selesai", Format.format(rs.getDate("TARIKH_SELESAI")));
					}
					
					if (rs.getString("TAHAP_PENYELESAIAN") == null) {
						h.put("tahap_penyelesaian", "");
					} else {
						h.put("tahap_penyelesaian", rs.getString("TAHAP_PENYELESAIAN"));
					}
					
					if (rs.getString("TARIKH_KEMASKINI_ADUAN") == null) {
						h.put("tarikh_kemaskini", "");
					} else {
						h.put("tarikh_kemaskini", rs.getString("TARIKH_KEMASKINI_ADUAN").toUpperCase());
					}
					
					if (rs.getString("NAMA_MODUL") == null) {
						h.put("nama_modul", "");
					} else {
						h.put("nama_modul", rs.getString("NAMA_MODUL"));
					}
					
					if (rs.getString("ALERT_ADMIN") == null) {
						h.put("alert_admin", "");
					} else {
						h.put("alert_admin", rs.getString("ALERT_ADMIN"));
					}
					
					if (rs.getString("ALERT_DEVELOPER") == null) {
						h.put("alert_developer", "");
					} else {
						h.put("alert_developer", rs.getString("ALERT_DEVELOPER"));
					}
					
					if (rs.getString("ALERT_PENGADU") == null) {
						h.put("alert_pengadu", "");
					} else {
						h.put("alert_pengadu", rs.getString("ALERT_PENGADU"));
					}
					
					if (rs.getString("ADUAN") == null) {
						h.put("aduan", "");
					} else {
						h.put("aduan", rs.getString("ADUAN"));
					}
					
					if (rs.getString("NO_FAIL") == null) {
						h.put("no_fail", "");
					} else {
						h.put("no_fail", rs.getString("NO_FAIL"));
					}
					
					if (rs.getString("ID_JENISMODULESADUAN") == null) {
						h.put("id_jenismodulesaduan", "");
					} else {
						h.put("id_jenismodulesaduan", rs.getString("ID_JENISMODULESADUAN"));
					}
					
					if (rs.getString("JENIS_MODULE") == null) {
						h.put("jenis_module", "");
					} else {
						h.put("jenis_module", rs.getString("JENIS_MODULE"));
					}
					
					if (rs.getString("KOD_MODULE") == null) {
						h.put("kod_module", "");
					} else {
						h.put("kod_module", rs.getString("KOD_MODULE"));
					}
					
					if (rs.getString("FLAG_MASALAH_DB") == null) {
						h.put("flag_masalah_db", "");
					} else {
						h.put("flag_masalah_db", rs.getString("FLAG_MASALAH_DB"));
					}
					
					if (rs.getString("FLAG_MASALAH_SKRIN") == null) {
						h.put("flag_masalah_skrin", "");
					} else {
						h.put("flag_masalah_skrin", rs.getString("FLAG_MASALAH_SKRIN"));
					}
					
					if (rs.getString("FLAG_MASALAH_REPORT") == null) {
						h.put("flag_masalah_report", "");
					} else {
						h.put("flag_masalah_report", rs.getString("FLAG_MASALAH_REPORT"));
					}
					
					if (rs.getString("FLAG_MASALAH_HW") == null) {
						h.put("flag_masalah_hw", "");
					} else {
						h.put("flag_masalah_hw", rs.getString("FLAG_MASALAH_HW"));
					}
					
					if (rs.getString("FLAG_MASALAH_FLOW") == null) {
						h.put("flag_masalah_flow", "");
					} else {
						h.put("flag_masalah_flow", rs.getString("FLAG_MASALAH_FLOW"));
					}
					
					if (rs.getString("FLAG_MASALAH_PERTANYAAN") == null) {
						h.put("flag_masalah_pertanyaan", "");
					} else {
						h.put("flag_masalah_pertanyaan", rs.getString("FLAG_MASALAH_PERTANYAAN"));
					}
					
					if (rs.getString("FLAG_MASALAH_PENAMBAHAN") == null) {
						h.put("flag_masalah_penambahan", "");
					} else {
						h.put("flag_masalah_penambahan", rs.getString("FLAG_MASALAH_PENAMBAHAN"));
					}
					
					if (rs.getString("TAHAP_KESUSAHAN") == null) {
						h.put("tahap_kesusahan", "");
					} else {
						h.put("tahap_kesusahan", rs.getString("TAHAP_KESUSAHAN"));
					}
					
					if (rs.getString("ULASAN_TEKNIKAL") == null) {
						h.put("ulasan_teknikal", "");
					} else {
						h.put("ulasan_teknikal", rs.getString("ULASAN_TEKNIKAL"));
					}
					
					if (rs.getString("ID_STATUSESADUAN") == null) {
						h.put("id_statusesaduan", "");
					} else {
						h.put("id_statusesaduan", rs.getString("ID_STATUSESADUAN"));
					}
					list_notifikasi_main_list.addElement(h);
					}
				return list_notifikasi_main_list;
			} 
			
			 catch (Exception re) {
				 myLog.error("Error: ", re);
				 throw re;
				 }
			
			finally {
				if (db != null)
					db.close();
			}
		}
		
		public String checkIsNull(String txt) {
			if (txt == null) return "";
			else return txt;
		}
		
		public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
			try {
				setupPageDefaultPut();
				String scrolPosition = getParam("scrolPosition"+command);
				this.context.put("scrolPosition", scrolPosition);
				this.context.put("namaList", namaList);
				this.context.put("command", command);
				this.context.put("div", div);
				this.context.put("totalRecords", list.size());
				int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
				int page_mula = 1;
				
				int itemsPerPage;
				if (this.context.get("itemsPerPage"+command) == null
						|| this.context.get("itemsPerPage"+command) == "") {
					//myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
					itemsPerPage = getParam("itemsPerPage"+command) == "" ? 10
							: getParamAsInteger("itemsPerPage"+command);
				} else {
					itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
				}
				
				if (("getNext").equals(action)) {
					page++;
				} else if (("getPrevious").equals(action)) {
					page--;
				} else if (("getPage").equals(action)) {
					page = getParamAsInteger("value");
				} else if (("doChangeItemPerPage").equals(action)) {
					itemsPerPage = getParamAsInteger("itemsPerPage"+command);
				}
				//myLogger.info(" itemsPerPage : "+itemsPerPage);
				
				Paging2 paging = new Paging2(session, list, itemsPerPage);

				if (page > paging.getTotalPages())
					page = 1;
				this.context.put(namaList, paging.getPage(page));
				this.context.put("page_mula", new Integer(page_mula));
				this.context.put("page", new Integer(page));
				this.context.put("itemsPerPage", new Integer(itemsPerPage));
				this.context.put("totalPages", new Integer(paging.getTotalPages()));
				this.context.put("startNumber", new Integer(paging.getTopNumber()));
				this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
				this.context.put("isLastPage", new Boolean(paging.isLastPage()));

				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				this.context.put("error", e.getMessage());
			}
		}
		
		public void setupPageDefaultPut()
		{
			this.context.put("page_mula", "");
			this.context.put("page", "");
			this.context.put("itemsPerPage", "");
			this.context.put("totalPages","");
			this.context.put("startNumber", "");
			this.context.put("isFirstPage", "");
			this.context.put("isLastPage", "");
			this.context.put("scrolPosition", "");
			this.context.put("namaList", "");
			this.context.put("command", "");
			this.context.put("div", "");
			this.context.put("totalRecords", "");
		}
}
