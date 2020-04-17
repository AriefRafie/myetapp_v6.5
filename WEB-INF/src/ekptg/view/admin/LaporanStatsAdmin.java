package ekptg.view.admin;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;


	public class LaporanStatsAdmin {
		
		static Logger myLogger = Logger.getLogger(LaporanStatsAdmin.class);
		
		
		@SuppressWarnings("unchecked")
		public static  List getStatsKategUser() throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List StatsKateg = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT 'INDIVIDU' AS KATEGORI, COUNT(*) AS JUMLAHUSER FROM " +
									" (SELECT   U.USER_ID FROM   USERS U, USERS_ONLINE UO, ROLE R, WEB_LOGGER WL," +
									" TBLRUJNEGERI NEG, TBLRUJBANDAR BAN WHERE U.USER_ID = UO.USER_ID AND U.USER_ROLE = R.NAME(+)" +
									" AND U.USER_LOGIN = WL.USER_NAME(+) AND UO.ID_NEGERI = NEG.ID_NEGERI(+) AND UO.ID_BANDAR = BAN.ID_BANDAR(+) " +
									" AND (UPPER (UO.KATEGORI) = 'INDIVIDU') AND (   UPPER (UO.FLAG_AKTIF) IS NULL OR UPPER (UO.FLAG_AKTIF) = '1' " +
									" OR UPPER (UO.FLAG_AKTIF) = '') GROUP BY   U.USER_ID) ";

							sql+=	" UNION "; 
							
							sql+=	" SELECT 'SYARIKAT' AS KATEGORI, COUNT ( * ) AS JUMLAHUSER FROM (SELECT U.USER_ID FROM   USERS U, " +
									" USERS_ONLINE UO, ROLE R, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN " +
									" WHERE U.USER_ID = UO.USER_ID AND U.USER_ROLE = R.NAME(+) AND U.USER_LOGIN = WL.USER_NAME(+) " +
									" AND UO.ID_NEGERI = NEG.ID_NEGERI(+) AND UO.ID_BANDAR = BAN.ID_BANDAR(+) AND (UPPER (UO.KATEGORI) = 'SYARIKAT') " +
									" AND (UPPER (UO.FLAG_AKTIF) IS NULL OR UPPER (UO.FLAG_AKTIF) = '1' OR UPPER (UO.FLAG_AKTIF) = '') " +
									" GROUP BY   U.USER_ID) " ;
						
						myLogger.debug("SQL LIST KATEGUSER - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 StatsKateg = Collections.synchronizedList(new ArrayList());
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
							h.put("KATEGORI",checkIsNull(rs.getString("KATEGORI")));
							h.put("JUMLAHUSER",checkIsNull(rs.getString("JUMLAHUSER")));
							
							StatsKateg.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return StatsKateg;
					
		}	
		
		@SuppressWarnings("unchecked")
		public static  List getStatsUmur() throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List StatsUmur = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT   KATEGORIUMUR, COUNT (USER_ID) AS JUMLAHUSER " +
									" FROM   (  SELECT   U.USER_ID, HAD_UMUR.UMUR, (CASE " +
									" WHEN HAD_UMUR.UMUR BETWEEN 21 AND 30 THEN '21-30' " +
									" WHEN HAD_UMUR.UMUR BETWEEN 31 AND 40 THEN '31-40' " +
									" WHEN HAD_UMUR.UMUR BETWEEN 41 AND 50 THEN '41-50' " +
									" WHEN HAD_UMUR.UMUR BETWEEN 51 AND 100 THEN '51-100' " +
									" WHEN HAD_UMUR.UMUR > 101 THEN 'above 100' ELSE 'TIADA MAKLUMAT' END) AS KATEGORIUMUR " +
									" FROM   USERS U, USERS_ONLINE UO, ROLE R, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN," +
									" (SELECT   UOTEMP.USER_ID, TO_NUMBER(CASE WHEN UOTEMP.KATEGORI = 'Individu' " +
									" AND UOTEMP.TARIKH_LAHIR IS NOT NULL " +
									" THEN (TO_NUMBER(TO_CHAR (SYSDATE,'YYYY')- TO_NUMBER(TO_CHAR (TARIKH_LAHIR,'YYYY')))) " +
									" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH (UOTEMP.NO_KP_BARU) = 12 " +
									" AND UOTEMP.NO_KP_BARU IS NOT NULL AND REGEXP_LIKE (UOTEMP.NO_KP_BARU,'^[[:digit:]]+$')" +
									" AND TO_NUMBER(SUBSTR (UOTEMP.NO_KP_BARU,1,2)) >= 20 " +
									" THEN (TO_NUMBER (TO_CHAR (SYSDATE, 'YYYY')) - TO_NUMBER('19'|| SUBSTR (NO_KP_BARU,1,2)))" +
									" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH (UOTEMP.NO_KP_BARU) = 12 " +
									" AND UOTEMP.NO_KP_BARU IS NOT NULL AND REGEXP_LIKE (UOTEMP.NO_KP_BARU,'^[[:digit:]]+$') " +
									" AND TO_NUMBER(SUBSTR (UOTEMP.NO_KP_BARU,1,2)) < 20 " +
									" THEN (TO_NUMBER (TO_CHAR (SYSDATE, 'YYYY')) - TO_NUMBER('20' || SUBSTR (NO_KP_BARU,1,2 ))) " +
									" ELSE 0 END) AS UMUR FROM   USERS_ONLINE UOTEMP " +
									" WHERE (TO_NUMBER(CASE WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NOT NULL THEN " +
									" (TO_NUMBER(TO_CHAR (SYSDATE,'YYYY') - TO_NUMBER(TO_CHAR (TARIKH_LAHIR,'YYYY')))) " +
									" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH (UOTEMP.NO_KP_BARU) = 12 " +
									" AND UOTEMP.NO_KP_BARU IS NOT NULL AND REGEXP_LIKE (UOTEMP.NO_KP_BARU,'^[[:digit:]]+$') " +
									" AND TO_NUMBER(SUBSTR (UOTEMP.NO_KP_BARU,1,2)) >= 20 THEN " +
									" (TO_NUMBER (TO_CHAR (SYSDATE, 'YYYY')) - TO_NUMBER('19' || SUBSTR (NO_KP_BARU,1,2))) " +
									" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH (UOTEMP.NO_KP_BARU) = 12 " +
									" AND UOTEMP.NO_KP_BARU IS NOT NULL AND REGEXP_LIKE (UOTEMP.NO_KP_BARU,'^[[:digit:]]+$') " +
									" AND TO_NUMBER(SUBSTR (UOTEMP.NO_KP_BARU,1,2)) < 20 THEN " +
									" (TO_NUMBER (TO_CHAR (SYSDATE, 'YYYY')) - TO_NUMBER('20' || SUBSTR (NO_KP_BARU,1,2))) " +
									" ELSE 0 END)) > 0) HAD_UMUR " +
									" WHERE U.USER_ID = UO.USER_ID AND U.USER_ROLE = R.NAME(+) AND U.USER_ID = HAD_UMUR.USER_ID(+) " +
									" AND U.USER_LOGIN = WL.USER_NAME(+) AND UO.ID_NEGERI = NEG.ID_NEGERI(+) AND UO.ID_BANDAR = BAN.ID_BANDAR(+) " +
									" AND (UPPER (UO.KATEGORI) = 'INDIVIDU') AND (UPPER (UO.FLAG_AKTIF) IS NULL OR UPPER (UO.FLAG_AKTIF) = '1' " +
									" OR UPPER (UO.FLAG_AKTIF) = '') " +
									" GROUP BY U.USER_ID,HAD_UMUR.UMUR ORDER BY   U.USER_NAME) " +
									" GROUP BY   KATEGORIUMUR ORDER BY   KATEGORIUMUR " ;
						
						myLogger.debug("SQL LIST NEGERI - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 StatsUmur = Collections.synchronizedList(new ArrayList());
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
							h.put("KATEGORIUMUR",checkIsNull(rs.getString("KATEGORIUMUR")));
							h.put("JUMLAHUSER",checkIsNull(rs.getString("JUMLAHUSER")));
							
							StatsUmur.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return StatsUmur;
					
		}	
		
		@SuppressWarnings("unchecked")
		public static  List getStatsLogbyHari() throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List StatsHari = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT HARI, ORDERCOLUMN, COUNT (USER_ID) AS TOTALUSERONLINE FROM " +
									" (SELECT TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') AS HARI, " +
									" (case when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'ISNIN ' then 1 " +
									" when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'SELASA' then 2 " +
									" when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'RABU  ' then 3 " +
									" when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'KHAMIS' then 4 " +
									" when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'JUMAAT' then 5 " +
									" when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'SABTU' then 6 " +
									" when TO_CHAR(WL.LOG_DATE,'DAY','NLS_DATE_LANGUAGE = MALAY') = 'AHAD' then 7  else 99 end) " +
									" AS ORDERCOLUMN, U.USER_ID FROM   USERS U, USERS_ONLINE UO, ROLE R, WEB_LOGGER WL, " +
									" TBLRUJNEGERI NEG, TBLRUJBANDAR BAN WHERE U.USER_ID = UO.USER_ID AND U.USER_ROLE = R.NAME(+) " +
									" AND U.USER_LOGIN = WL.USER_NAME(+) AND UO.ID_NEGERI = NEG.ID_NEGERI(+) " +
									" AND UO.ID_BANDAR = BAN.ID_BANDAR(+) AND (UPPER (UO.FLAG_AKTIF) IS NULL OR UPPER (UO.FLAG_AKTIF) = '1' " +
									" OR UPPER (UO.FLAG_AKTIF) = '') GROUP BY   WL.LOG_DATE, U.USER_ID) " +
									" GROUP BY   HARI, ORDERCOLUMN ORDER BY ORDERCOLUMN " ;
						
						myLogger.debug("SQL LIST HARI BEKERJA - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 StatsHari = Collections.synchronizedList(new ArrayList());
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
							h.put("HARI",checkIsNull(rs.getString("HARI")));
							h.put("ORDERCOLUMN",checkIsNull(rs.getString("ORDERCOLUMN")));
							h.put("TOTALUSERONLINE",checkIsNull(rs.getString("TOTALUSERONLINE")));
							
							StatsHari.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return StatsHari;
					
		}	
		
		@SuppressWarnings("unchecked")
		public static  List getStatsLogbyNegeri(int jenisUser) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List StatsHari = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				if (jenisUser == 1){
					
					sql = 	" SELECT ID_NEGERI, NAMA_NEGERI, COUNT(*) AS TOTALUSERONLINE FROM (" +
							" SELECT U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI " +
							" FROM USERS U, USERS_INTERNAL UI, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN " +
							" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = WL.USER_NAME(+)" +
							" AND UI.ID_NEGERI = NEG.ID_NEGERI(+) AND UI.ID_BANDAR = BAN.ID_BANDAR(+)" +
							" AND (UPPER (UI.FLAG_AKTIF) IS NULL OR UPPER (UI.FLAG_AKTIF) = '1' " +
							" OR UPPER (UI.FLAG_AKTIF) = '') " +
							" GROUP BY   U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI) " +
							" WHERE ID_NEGERI != 0 AND ID_NEGERI = 16 " +
							" GROUP BY ID_NEGERI, NAMA_NEGERI ORDER BY ID_NEGERI ASC " ;
				
				} else if (jenisUser == 2){
					
					sql = 	" SELECT ID_NEGERI, NAMA_NEGERI, COUNT(*) AS TOTALUSERONLINE FROM (" +
							" SELECT U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI " +
							" FROM USERS U, USERS_INTERNAL UI, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN " +
							" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = WL.USER_NAME(+)" +
							" AND UI.ID_NEGERI = NEG.ID_NEGERI(+) AND UI.ID_BANDAR = BAN.ID_BANDAR(+)" +
							" AND (UPPER (UI.FLAG_AKTIF) IS NULL OR UPPER (UI.FLAG_AKTIF) = '1' " +
							" OR UPPER (UI.FLAG_AKTIF) = '') " +
							" GROUP BY   U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI) " +
							" WHERE ID_NEGERI != 0 " +
							" GROUP BY ID_NEGERI, NAMA_NEGERI ORDER BY ID_NEGERI ASC " ;	
				
				} else if (jenisUser == 3){
					
					sql = 	" SELECT ID_NEGERI, NAMA_NEGERI, COUNT(*) AS TOTALUSERONLINE FROM (" +
							" SELECT U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI " +
							" FROM USERS U, USERS_INTERNAL UI, USERS_KEMENTERIAN UK, WEB_LOGGER WL, " +
							" TBLRUJKEMENTERIAN TRK, TBLRUJAGENSI TRA, TBLRUJNEGERI NEG WHERE U.USER_ID = UK.USER_ID " +
							" AND UI.USER_ID = UK.USER_ID AND U.USER_LOGIN = WL.USER_NAME(+) " +
							" AND UK.ID_KEMENTERIAN = TRK.ID_KEMENTERIAN AND UK.ID_AGENSI = TRA.ID_AGENSI " +
							" AND TRK.ID_NEGERI = NEG.ID_NEGERI(+) AND (UPPER (UK.FLAG_AKTIF) IS NULL OR UPPER (UK.FLAG_AKTIF) = '1' " +
							" OR UPPER (UK.FLAG_AKTIF) = '') GROUP BY   U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI) " +
							" WHERE ID_NEGERI != 0  GROUP BY ID_NEGERI, NAMA_NEGERI ORDER BY ID_NEGERI ASC ";
				
				} else if (jenisUser == 4){
					
					sql = 	" SELECT ID_NEGERI, NAMA_NEGERI, COUNT ( * ) AS TOTALUSERONLINE FROM ( " +
							" SELECT U.USER_ID, N.ID_NEGERI, N.NAMA_NEGERI FROM   USERS U, USERS_INTERNAL UI, " +
							" USERS_INTEGRASI UIN, TBLRUJNEGERI N, WEB_LOGGER WL WHERE U.USER_ID = UI.USER_ID " +
							" AND UI.USER_ID = UIN.USER_ID AND UIN.ID_NEGERI = N.ID_NEGERI(+) AND U.USER_LOGIN = WL.USER_NAME(+) " +
							" AND (UPPER (UIN.FLAG_AKTIF) IS NULL OR UPPER (UIN.FLAG_AKTIF) = '1' OR UPPER (UIN.FLAG_AKTIF) = '') " +
							" GROUP BY U.USER_ID, N.ID_NEGERI, N.NAMA_NEGERI ORDER BY USER_ID) " +
							" GROUP BY ID_NEGERI, NAMA_NEGERI ORDER BY ID_NEGERI ASC ";
					
				}else if (jenisUser == 5){
					
					sql = 	" SELECT ID_NEGERI, NAMA_NEGERI, COUNT(*) AS TOTALUSERONLINE FROM (" +
							" SELECT U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI " +
							" FROM USERS U, USERS_ONLINE UO, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN " +
							" WHERE U.USER_ID = UO.USER_ID AND U.USER_LOGIN = WL.USER_NAME(+)" +
							" AND UO.ID_NEGERI = NEG.ID_NEGERI(+) AND UO.ID_BANDAR = BAN.ID_BANDAR(+)" +
							" AND (UPPER (UO.FLAG_AKTIF) IS NULL OR UPPER (UO.FLAG_AKTIF) = '1' " +
							" OR UPPER (UO.FLAG_AKTIF) = '') " +
							" GROUP BY   U.USER_ID, NEG.ID_NEGERI, NEG.NAMA_NEGERI) " +
							" WHERE ID_NEGERI != 0 GROUP BY ID_NEGERI, NAMA_NEGERI ORDER BY ID_NEGERI ASC " ;
				}
				else {
					
				}
				
							
						
						myLogger.debug("SQL LIST LOG BY NEGERI - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 StatsHari = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
							h.put("TOTALUSERONLINE",checkIsNull(rs.getString("TOTALUSERONLINE")));
							
							StatsHari.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return StatsHari;
					
		}	
		
		public static String checkIsNull(String txt) {
			if (txt == null) return "";
			else return txt;
		}
	
	}