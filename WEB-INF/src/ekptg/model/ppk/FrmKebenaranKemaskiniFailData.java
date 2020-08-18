package ekptg.model.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;





public class FrmKebenaranKemaskiniFailData {
	
	static Logger myLogger = Logger.getLogger(FrmKebenaranKemaskiniFailData.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);
	
	//DISPLAY LIST USER AGIHAN
	/*
	    SELECT U.USER_NAME,U.USER_ID,U.USER_LOGIN FROM USERS U,USERS_INTERNAL UI
		WHERE 
		U.USER_ID = UI.USER_ID 
		AND (U.USER_ROLE IN ('admin_es','developer_es') 
		OR 
		U.USER_LOGIN IN 
		(SELECT DISTINCT USER_ROLE.USER_ID FROM USER_ROLE WHERE USER_ROLE.ROLE_ID IN ('admin_es','developer_es')))
	*/
	
	@SuppressWarnings("unchecked")
	Vector checkEmail = null;
	
	@SuppressWarnings("unchecked")
	public Vector checkEmail(String userId) throws Exception {
		
		checkEmail = new Vector();
		checkEmail.clear();
		
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT EMEL FROM USERS_INTERNAL WHERE USER_ID = '"+userId+"' AND EMEL IS NOT NULL";
	
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("EMEL", rs.getString("EMEL")== null?"":rs.getString("EMEL"));
				checkEmail.addElement(h);
			}
			return checkEmail;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector search_nofail = null;
	public Vector search_nofail(String no_fail,String usid) throws Exception {
		search_nofail = new Vector();
		search_nofail.clear();
		Db db = null;
		String sql = "";
	try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT ST.KETERANGAN AS NAMA_STATUS,F.ID_FAIL,F.NO_FAIL,P.ID_PERMOHONAN," +
				  " TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON,S.NAMA_SIMATI,PM.NAMA_PEMOHON "+
				  " FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM,TBLRUJSTATUS ST "+
				 // " WHERE trim(F.NO_FAIL) = '"+no_fail+"' "+
				  " WHERE F.ID_FAIL = P.ID_FAIL "+
				  " AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN ";
				
			sql += " AND ( trim(F.NO_FAIL) LIKE '%"+no_fail.trim()+"%' ";
			sql += " OR trim(S.NO_KP_BARU) LIKE '%"+no_fail.trim()+"%' ";
			sql += " OR trim(S.NO_KP_LAMA) LIKE '%"+no_fail.trim()+"%' ";
			sql += " OR trim(S.NO_KP_LAIN) LIKE '%"+no_fail.trim()+"%' ) ";
			
					
				  
			sql += " AND S.ID_SIMATI = PS.ID_SIMATI "+
				  " AND P.ID_PEMOHON = PM.ID_PEMOHON "+
				  " AND P.ID_STATUS = ST.ID_STATUS(+) ";
			sql += " AND P.ID_DAERAHMHN in ( "+
			" select distinct u.id_daerahurus from  TBLRUJPEJABATURUSAN u, users_internal ur "+
				   " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id= '"+usid+"' ";
				   
				    sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+usid+"  ";
				   
				   
				 sql += "  ) ";
			sql += " ORDER BY P.TARIKH_MOHON";
		   
			
			myLogger.info("SQL LIST NO_FAIL :"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				
				if (rs.getString("NAMA_STATUS") == null) {
					h.put("NAMA_STATUS", "");
				} else {
					h.put("NAMA_STATUS", rs.getString("NAMA_STATUS"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL"));
				}
				
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("TARIKH_MOHON") == null) {
					h.put("TARIKH_MOHON", "");
				} else {
					h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON"));
				}
				
				if (rs.getString("NAMA_SIMATI") == null) {
					h.put("NAMA_SIMATI", "");
				} else {
					h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI"));
				}
				
				if (rs.getString("NAMA_PEMOHON") == null) {
					h.put("NAMA_PEMOHON", "");
				} else {
					h.put("NAMA_PEMOHON", rs.getString("NAMA_PEMOHON"));
				}
				
				
				
				search_nofail.addElement(h);
			}
			return search_nofail;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
public Hashtable getMainFail(String ID_FAIL) throws Exception {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT P.ID_PEGAWAI_MOHON_EDIT,P.ID_PEMOHON_MOHON_EDIT, P.CATATAN_KEBENARAN_EDIT,U.USER_NAME,P.USER_ID_KEBENARAN_EDIT,P.FLAG_KEBENARAN_EDIT,P.ID_STATUS," +
					" F.ID_FAIL,P.ID_PERMOHONAN,F.NO_FAIL,F.ID_SUBURUSAN,UP.USER_NAME AS NAMA_PEGAWAI_EDIT,UM.USER_NAME AS NAMA_PEMOHON_EDIT, P.TUJUAN_PINDAAN, P.TARIKH_MULA_PINDA, P.TARIKH_AKHIR_PINDA,  " +
					" P.TARIKH_SELESAI_PINDA, P.FLAG_PINDA_SELESAI FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P,USERS U, USERS UP, USERS UM "+
				  " WHERE F.ID_FAIL = P.ID_FAIL AND F.ID_FAIL = '"+ID_FAIL+"' AND P.USER_ID_KEBENARAN_EDIT = U.USER_ID(+) " +
				  		" AND P.ID_PEGAWAI_MOHON_EDIT = UP.USER_ID(+) AND P.ID_PEMOHON_MOHON_EDIT = UM.USER_ID(+)  ";	
			
			/*sql = "SELECT * FROM (" +
					" SELECT US.USER_NAME, US.USER_ID, US.USER_LOGIN, US.USER_ROLE" +
					" FROM USERS US, USERS_INTERNAL UI, USER_ROLE UR" +
					" WHERE US.USER_ID = UI.USER_ID AND US.USER_LOGIN = UR.USER_ID(+)" +
					" AND UI.ID_PEJABATJKPTG IN (" +
					" SELECT DISTINCT UR.ID_PEJABATJKPTG FROM TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PJ" +
					" WHERE UR.ID_DAERAHURUS IN (" +
					" SELECT DISTINCT P.ID_DAERAHMHN" +
					" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P" +
					" WHERE P.ID_FAIL = F.ID_FAIL" +
					" AND F.ID_FAIL = '"+ID_FAIL+"' " +
					" ) AND UR.ID_JENISPEJABAT IN (22, 21)" +
					" AND PJ.ID_SEKSYEN = '2'" +
					" AND UR.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG" +
					" AND UR.ID_PEJABATJKPTG NOT IN '76')" +
					" AND (US.USER_ROLE IN ('adminppk') OR UR.ROLE_ID IN ('adminppk'))" +
					" UNION ALL" +
					" SELECT US.USER_NAME, US.USER_ID, US.USER_LOGIN, US.USER_ROLE" +
					" FROM USERS US, USERS_INTERNAL UI, (" +
					" SELECT BU.ID_PEMOHON FROM TBLPERMOHONANBANTUUNIT BU" +
					" WHERE BU.ID_UNIT IN (" +
					" SELECT DISTINCT UR.ID_PEJABATJKPTG" +
					" FROM TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PJ" +
					" WHERE UR.ID_DAERAHURUS IN (" +
					" SELECT DISTINCT P.ID_DAERAHMHN" +
					" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P" +
					" WHERE P.ID_FAIL = F.ID_FAIL" +
					" AND F.ID_FAIL = '"+ID_FAIL+"' " +
					" )) AND   ((TO_DATE (TO_CHAR (BU.TARIKH_MULA, 'DD/MM/YYYY'),'DD/MM/YYYY') >" +
					" SYSDATE AND (TO_DATE (TO_CHAR (BU.TARIKH_AKHIR, 'DD/MM/YYYY')," +
					" 'DD/MM/YYYY') >= SYSDATE )) OR (TO_DATE (TO_CHAR (BU.TARIKH_MULA, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=" +
					" (SYSDATE) AND (TO_DATE (TO_CHAR (BU.TARIKH_AKHIR, 'DD/MM/YYYY'), 'DD/MM/YYYY'" +
					" ) >= SYSDATE))) AND (   TO_DATE (TO_CHAR (BU.TARIKH_AKHIR, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=" +
					" SYSDATE OR TO_DATE (TO_CHAR (BU.TARIKH_MULA, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=" +
					" SYSDATE)) CHECKBU, USER_ROLE UR" +
					" WHERE US.USER_ID = UI.USER_ID AND US.USER_ID = CHECKBU.ID_PEMOHON" +
					" AND US.USER_LOGIN = UR.USER_ID(+)" +
					" AND (US.USER_ROLE IN ('adminppk') OR UR.ROLE_ID IN ('adminppk'))" +
					" ) ORDER BY USER_NAME";*/
			
			//myLogger.info("SQL MAIN FAIL DETAIL :" + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				
				if (rs.getString("ID_PEGAWAI_MOHON_EDIT") == null) {
					h.put("ID_PEGAWAI_MOHON_EDIT","");
				} else {
					h.put("ID_PEGAWAI_MOHON_EDIT", rs.getString("ID_PEGAWAI_MOHON_EDIT"));
				}
				
				if (rs.getString("ID_PEMOHON_MOHON_EDIT") == null) {
					h.put("ID_PEMOHON_MOHON_EDIT","");
				} else {
					h.put("ID_PEMOHON_MOHON_EDIT", rs.getString("ID_PEMOHON_MOHON_EDIT"));
				}
				
				if (rs.getString("NAMA_PEGAWAI_EDIT") == null) {
					h.put("NAMA_PEGAWAI_EDIT","");
				} else {
					h.put("NAMA_PEGAWAI_EDIT", rs.getString("NAMA_PEGAWAI_EDIT").toUpperCase());
				}
				
				if (rs.getString("NAMA_PEMOHON_EDIT") == null) {
					h.put("NAMA_PEMOHON_EDIT","");
				} else {
					h.put("NAMA_PEMOHON_EDIT", rs.getString("NAMA_PEMOHON_EDIT").toUpperCase());
				}
				
				if (rs.getString("CATATAN_KEBENARAN_EDIT") == null) {
					h.put("CATATAN_KEBENARAN_EDIT","");
				} else {
					h.put("CATATAN_KEBENARAN_EDIT", rs.getString("CATATAN_KEBENARAN_EDIT"));
				}
				
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME","");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME").toUpperCase());
				}
				
				if (rs.getString("USER_ID_KEBENARAN_EDIT") == null) {
					h.put("USER_ID_KEBENARAN_EDIT","");
				} else {
					h.put("USER_ID_KEBENARAN_EDIT", rs.getString("USER_ID_KEBENARAN_EDIT"));
				}
				
				if (rs.getString("FLAG_KEBENARAN_EDIT") == null) {
					h.put("FLAG_KEBENARAN_EDIT", "");
				} else {
					h.put("FLAG_KEBENARAN_EDIT", rs.getString("FLAG_KEBENARAN_EDIT"));
				}
				
				if (rs.getString("ID_FAIL") == null) {
					h.put("ID_FAIL", "");
				} else {
					h.put("ID_FAIL", rs.getString("ID_FAIL"));
				}
				
				if (rs.getString("ID_PERMOHONAN") == null) {
					h.put("ID_PERMOHONAN", "");
				} else {
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN"));
				}
				
				if (rs.getString("NO_FAIL") == null) {
					h.put("NO_FAIL", "");
				} else {
					h.put("NO_FAIL", rs.getString("NO_FAIL").trim());
				}
				
				if (rs.getString("ID_SUBURUSAN") == null) {
					h.put("ID_SUBURUSAN", "");
				} else {
					h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN"));
				}
				
				if (rs.getString("ID_STATUS") == null) {
					h.put("ID_STATUS", "");
				} else {
					h.put("ID_STATUS", rs.getString("ID_STATUS"));
				}
				
				if (rs.getString("TUJUAN_PINDAAN") == null) {
					h.put("TUJUAN_PINDAAN", "");
				} else {
					h.put("TUJUAN_PINDAAN", rs.getString("TUJUAN_PINDAAN"));
				}
				
				if (rs.getString("TARIKH_MULA_PINDA") == null) {
					h.put("TARIKH_MULA_PINDA", "");
				} else {
					h.put("TARIKH_MULA_PINDA", sdf.format(rs.getDate("TARIKH_MULA_PINDA")));
				}
				
				if (rs.getString("TARIKH_AKHIR_PINDA") == null) {
					h.put("TARIKH_AKHIR_PINDA", "");
				} else {
					h.put("TARIKH_AKHIR_PINDA", sdf.format(rs.getDate("TARIKH_AKHIR_PINDA")));
				}
				
				if (rs.getString("TARIKH_SELESAI_PINDA") == null) {
					h.put("TARIKH_SELESAI_PINDA", "");
				} else {
					h.put("TARIKH_SELESAI_PINDA", sdf.format(rs.getDate("TARIKH_SELESAI_PINDA")));
				}
				
				if (rs.getString("FLAG_PINDA_SELESAI") == null) {
					h.put("FLAG_PINDA_SELESAI", "");
				} else {
					h.put("FLAG_PINDA_SELESAI", rs.getString("FLAG_PINDA_SELESAI"));
				}
				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Hashtable user_seksyen = null;
	public Hashtable user_seksyen(String user_id) throws Exception {
		user_seksyen = new Hashtable();
		user_seksyen.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "select ui.id_seksyen from users u,users_internal ui where ui.user_id = u.user_id ";
			if(!user_id.equals(""))
			{
			sql += " AND u.user_id = '"+user_id+"'";	
			}
			
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("id_seksyen") == null) {
					h.put("id_seksyen_user", "");
				} else {
					h.put("id_seksyen_user", rs.getString("id_seksyen"));
				}				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Hashtable get_user_emel = null;
	public Hashtable get_user_emel(String user_id) throws Exception {
		get_user_emel = new Hashtable();
		get_user_emel.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "select ui.emel,u.user_name from users u,users_internal ui where ui.user_id = u.user_id ";
			if(!user_id.equals(""))
			{
			sql += " AND u.user_id = '"+user_id+"'";	
			}
			
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("emel") == null) {
					h.put("emel", "");
				} else {
					h.put("emel", rs.getString("emel"));
				}	
				if (rs.getString("user_name") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("user_name"));
				}	
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	Hashtable arahan_agihan = null;
	public Hashtable get_arahan_agihan(String id_esaduan,String user_id) throws Exception {
		arahan_agihan = new Hashtable();
		arahan_agihan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "select user_id,keterangan from tblesagihan where user_id = "+user_id+" and id_esaduan = "+id_esaduan+" ";
			
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("user_id") == null) {
					h.put("user_id", "");
				} else {
					h.put("user_id", rs.getString("user_id"));
				}	
				if (rs.getString("keterangan") == null) {
					h.put("arahan", "");
				} else {
					h.put("arahan", rs.getString("keterangan"));
				}	
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_module = null;
	public Vector getListModule(String id_jenismodulesaduan) throws Exception {
		list_module = new Vector();
		list_module.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_JENISMODULESADUAN,KOD_JENISMODULESADUAN,KETERANGAN,ID_SEKSYEN FROM TBLRUJJENISMODULESADUAN ";
			if(!id_jenismodulesaduan.equals(""))
			{
			sql += " WHERE ID_JENISMODULESADUAN = '"+id_jenismodulesaduan+"'";	
			}
			
			sql +=	" ORDER BY KETERANGAN ASC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_jenismodulesaduan", rs.getString("ID_JENISMODULESADUAN"));
				if (rs.getString("KOD_JENISMODULESADUAN") == null) {
					h.put("kod_module", "");
				} else {
					h.put("kod_module", rs.getString("KOD_JENISMODULESADUAN"));
				}
				if (rs.getString("KETERANGAN") == null) {
					h.put("jenis_module", "");
				} else {
					h.put("jenis_module", rs.getString("KETERANGAN"));
				}
				if (rs.getString("ID_SEKSYEN") == null) {
					h.put("id_seksyen", "");
				} else {
					h.put("id_seksyen", rs.getString("ID_SEKSYEN"));
				}
				list_module.addElement(h);
			}
			return list_module;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_jenisaduan = null;
	public Vector getListJenisAduan() throws Exception {
		list_jenisaduan = new Vector();
		list_jenisaduan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_JENISADUAN,KOD_JENIS_ADUAN,JENIS_ADUAN FROM TBLRUJJENISESADUAN" +
					" ORDER BY KOD_JENIS_ADUAN ASC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_jenisaduan", rs.getString("ID_JENISADUAN"));
				if (rs.getString("kod_jenis_aduan") == null) {
					h.put("kod_jenis_aduan", "");
				} else {
					h.put("kod_jenis_aduan", rs.getString("KOD_JENIS_ADUAN"));
				}
				if (rs.getString("jenis_aduan") == null) {
					h.put("jenis_aduan", "");
				} else {
					h.put("jenis_aduan", rs.getString("JENIS_ADUAN"));
				}
				list_jenisaduan.addElement(h);
			}
			return list_jenisaduan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_statusaduan = null;
	public Vector getListStatusAduan() throws Exception {
		list_statusaduan = new Vector();
		list_statusaduan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_STATUSESADUAN,KOD_STATUSESADUAN,KETERANGAN AS NAMA_STATUS FROM TBLRUJSTATUSESADUAN" +
					" ORDER BY KOD_STATUSESADUAN ASC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_statusesaduan", rs.getString("ID_STATUSESADUAN"));
				if (rs.getString("KOD_STATUSESADUAN") == null) {
					h.put("kod_statusesaduan", "");
				} else {
					h.put("kod_statusesaduan", rs.getString("KOD_STATUSESADUAN"));
				}
				if (rs.getString("NAMA_STATUS") == null) {
					h.put("nama_status", "");
				} else {
					h.put("nama_status", rs.getString("NAMA_STATUS"));
				}
				list_statusaduan.addElement(h);
			}
			return list_statusaduan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_sumberaduan = null;
	public Vector getListSumberAduan() throws Exception {
		list_sumberaduan = new Vector();
		list_sumberaduan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_SUMBERADUAN,KOD_SUMBER,NAMA_SUMBER FROM TBLRUJSUMBERESADUAN" +
					" ORDER BY KOD_SUMBER ASC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_sumberaduan", rs.getString("ID_SUMBERADUAN"));
				if (rs.getString("KOD_SUMBER") == null) {
					h.put("kod_sumber", "");
				} else {
					h.put("kod_sumber", rs.getString("KOD_SUMBER"));
				}
				if (rs.getString("NAMA_SUMBER") == null) {
					h.put("nama_sumber", "");
				} else {
					h.put("nama_sumber", rs.getString("NAMA_SUMBER"));
				}
				list_sumberaduan.addElement(h);
			}
			return list_sumberaduan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_negeri = null;
	public Vector getListNegeri(String id_negeri) throws Exception {
		list_negeri = new Vector();
		list_negeri.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_NEGERI,KOD_NEGERI,NAMA_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI NOT IN ('17','0')";
			if(!id_negeri.equals(""))
			{
			sql += " AND ID_NEGERI = '"+id_negeri+"' ";
			}
			sql += " ORDER BY KOD_NEGERI ASC";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_negeri", rs.getString("ID_NEGERI"));
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("kod_negeri", "");
				} else {
					h.put("kod_negeri", rs.getString("KOD_NEGERI"));
				}
				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("nama_negeri", "");
				} else {
					h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				}
				list_negeri.addElement(h);
			}
			return list_negeri;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	Vector list_users = null;
	public Vector getListUsers(String user_id,String id_pejabat,String id_negeri,String id_daerah,String role,String id_seksyen) throws Exception {
		
		myLogger.info("XX USER ID :"+user_id);
		list_users = new Vector();
		list_users.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
				  " PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL,J.KETERANGAN AS NAMA_JAWATAN "+
				  " FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D "+
				  " WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "+
				  " AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
                  " AND UI.ID_NEGERI = N.ID_NEGERI(+) "+
                  " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+)"+
                  " AND UI.ID_DAERAH = D.ID_DAERAH(+)";
			if(!user_id.equals("") && !user_id.equals(null))
			{
			sql += " AND UI.USER_ID = '"+user_id+"'";	
			}
			if(!id_pejabat.equals("") && !id_pejabat.equals(null))
			{
			sql += " AND UI.ID_PEJABATJKPTG = '"+id_pejabat+"'";	
			}
			if(!id_negeri.equals("") && !id_negeri.equals(null))
			{
			sql += " AND UI.ID_NEGERI = '"+id_negeri+"'";	
			}
			if(!id_daerah.equals("") && !id_daerah.equals(null))
			{
			sql += " AND UI.ID_DAERAH = '"+id_daerah+"'";		
			}
			if(!role.equals("") && !role.equals(null))
			{
			sql += " AND UI.ROLE = '"+role+"'";	
			}
			if(!id_seksyen.equals("") && !id_seksyen.equals(null))
			{
			sql += " AND UI.ID_SEKSYEN = '"+id_seksyen+"'";	
			}
			
			sql +=  " ORDER BY U.USER_LOGIN ASC";
			
			myLogger.info("GET LIST USER :"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("user_id", rs.getString("USER_ID"));
				
				if (rs.getString("USER_LOGIN") == null) {
					h.put("user_login", "");
				} else {
					h.put("user_login", rs.getString("USER_LOGIN"));
				}
				
				if (rs.getString("ID_SEKSYEN") == null) {
					h.put("id_seksyen", "");
				} else {
					h.put("id_seksyen", rs.getString("ID_SEKSYEN"));
				}
				
				if (rs.getString("USER_NAME") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("USER_NAME").toUpperCase());
				}
				
				if (rs.getString("USER_NAME_INIT") == null) {
					h.put("user_name_init", "");
				} else {
					h.put("user_name_init", rs.getString("USER_NAME_INIT"));
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
				
				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("ID_NEGERI").toUpperCase());
				}
				
				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri_pengadu", "");
				} else {
					h.put("id_negeri_pengadu", rs.getString("ID_NEGERI").toUpperCase());
				}
				
				if (rs.getString("ID_PEJABATJKPTG") == null) {
					h.put("id_pejabat_pengadu", "");
				} else {
					h.put("id_pejabat_pengadu", rs.getString("ID_PEJABATJKPTG").toUpperCase());
				}
				
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("kod_negeri", "");
				} else {
					h.put("kod_negeri", rs.getString("KOD_NEGERI").toUpperCase());
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
				
				if (rs.getString("NAMA_JAWATAN") == null) {
					h.put("nama_jawatan", "");
				} else {
					h.put("nama_jawatan", rs.getString("NAMA_JAWATAN"));
				}
				
				list_users.addElement(h);
			}
			return list_users;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_aduan = null;
	public Vector getAduan(String session_user,String id_esaduan,String user_id,String role,String id_pejabat,String id_negeri,String id_daerah,String id_seksyen,String no_fail,String aduan,String id_module,String log_aduan,String id_esstatus,String tarikh_aduan) throws Exception {
		
	
		list_aduan = new Vector();
		list_aduan.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT UI.ID_NEGERI,S.NAMA_SUMBER,INITCAP(S.NAMA_SUMBER) AS NAMA_SUMBER_INIT,J.JENIS_ADUAN,U.USER_NAME,INITCAP(U.USER_NAME) AS USER_NAME_INIT,ST.KETERANGAN AS NAMA_STATUS,INITCAP(ST.KETERANGAN) AS NAMA_STATUS_INIT, A.ID_ESADUAN,A.ID_SUMBERADUAN,A.ID_JENISADUAN,U.USER_ROLE, "+
				" SK.NAMA_SEKSYEN, PEJ.NAMA_PEJABAT,INITCAP(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT_INIT,N.NAMA_NEGERI,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL,INITCAP(JM.KETERANGAN) AS JENIS_MODULE_INIT,JM.KETERANGAN AS JENIS_MODULE,JM.KOD_JENISMODULESADUAN AS KOD_MODULE, "+
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
				" TBLRUJSEKSYEN SK,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "+
				" WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "+
				" AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "+
				" AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "+
				" AND A.USER_ID = U.USER_ID "+
				" AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "+
				" AND U.USER_ID = UI.USER_ID "+
				" AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "+
				" AND UI.ID_NEGERI = N.ID_NEGERI(+) "+
				" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "+
				" AND UI.ID_DAERAH = D.ID_DAERAH(+)";
			
			
			if(!session_user.equals("") && !session_user.equals(null))
			{
			sql += " AND ((A.ID_MASUK = '"+session_user+"' AND A.ID_STATUS = '16125') OR (A.ID_STATUS <> '16125'))";	
			}
			
			if(!id_esaduan.equals("") && !id_esaduan.equals(null))
			{
			sql += " AND A.ID_ESADUAN = '"+id_esaduan+"'";	
			}
			if(!user_id.equals("") && !user_id.equals(null))
			{
			sql += " AND UI.USER_ID = '"+user_id+"'";	
			}
			if(!id_pejabat.equals("") && !id_pejabat.equals(null))
			{
			sql += " AND UI.ID_PEJABATJKPTG = '"+id_pejabat+"'";	
			}
			if(!id_negeri.equals("") && !id_negeri.equals(null))
			{
			sql += " AND UI.ID_NEGERI = '"+id_negeri+"'";	
			}
			if(!id_daerah.equals("") && !id_daerah.equals(null))
			{
			sql += " AND UI.ID_DAERAH = '"+id_daerah+"'";		
			}
			if(!role.equals("") && !role.equals(null))
			{
			sql += " AND UI.ROLE = '"+role+"'";	
			}
			if(!id_seksyen.equals("") && !id_seksyen.equals(null))
			{
			sql += " AND UI.ID_SEKSYEN = '"+id_seksyen+"'";	
			}
			
			if(!no_fail.trim().equals("") && !no_fail.trim().equals(null))
			{
			sql += " AND A.NO_FAIL LIKE '%"+no_fail.trim()+"%'";	
			}
			
			if(!aduan.trim().equals("") && !aduan.trim().equals(null))
			{
			sql += " AND A.ADUAN LIKE '%"+aduan.trim()+"%'";	
			}
			
			if(!id_module.equals("") && !id_module.equals(null))
			{
			sql += " AND A.ID_JENISMODULESADUAN = '"+id_module+"'";	
			}
			
			if(!log_aduan.trim().equals("") && !log_aduan.trim().equals(null))
			{
			sql += " AND A.LOG_ADUAN LIKE '%"+log_aduan.trim()+"%'";	
			}
			
			if(!id_esstatus.equals("") && !id_esstatus.equals(null))
			{
			sql += " AND A.ID_STATUS = '"+id_esstatus+"'";	
			}
			
			if(!tarikh_aduan.equals("") && !tarikh_aduan.equals(null))
			{
			sql += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') = '"+tarikh_aduan+"'";	
			}
			
			
			
			
			sql +=  " ORDER BY A.TARIKH_KEMASKINI DESC";
			
			myLogger.info("LIST ADUAN :"+sql.toUpperCase());
			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("user_id", rs.getString("USER_ID"));
				
				
				if (rs.getString("USER_NAME") == null) {
					h.put("user_name", "");
				} else {
					h.put("user_name", rs.getString("USER_NAME").toUpperCase());
				}
				
				
				if (rs.getString("ID_NEGERI") == null) {
					h.put("id_negeri", "");
				} else {
					h.put("id_negeri", rs.getString("ID_NEGERI").toUpperCase());
				}
				
				
				
				
				if (rs.getString("USER_NAME_INIT") == null) {
					h.put("user_name_init", "");
				} else {
					h.put("user_name_init", rs.getString("USER_NAME_INIT"));
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
				
				if (rs.getString("NAMA_PEJABAT_INIT") == null) {
					h.put("nama_pejabat_init", "");
				} else {
					h.put("nama_pejabat_init", rs.getString("NAMA_PEJABAT_INIT"));
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
				
				if (rs.getString("NAMA_SUMBER_INIT") == null) {
					h.put("nama_sumber_init", "");
				} else {
					h.put("nama_sumber_init", rs.getString("NAMA_SUMBER_INIT"));
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
				
				if (rs.getString("NAMA_STATUS_INIT") == null) {
					h.put("nama_status_init", "");
				} else {
					h.put("nama_status_init", rs.getString("NAMA_STATUS_INIT"));
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
				
				if (rs.getString("JENIS_MODULE_INIT") == null) {
					h.put("jenis_module_init", "");
				} else {
					h.put("jenis_module_init", rs.getString("JENIS_MODULE_INIT"));
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
				
				list_aduan.addElement(h);
			}
			return list_aduan;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void updateFiles(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		String id_dokumen = (String) data.get("id_dokumen");
		String txtnamadokumen = (String) data.get("txtnamadokumen");
		String txtketerangandokumen = (String) data.get("txtketerangandokumen");
		String id_Masuk = (String) data.get("id_Masuk");

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_DOKUMEN", id_dokumen);
			r.add("TAJUK", txtnamadokumen);
			r.add("KETERANGAN", txtketerangandokumen);
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

			sql = r.getSQLUpdate("tblpptdokumen");

			myLogger.info("SQL UPDATE DOKUMEN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	Vector listDokumen_aduan = null;
	public Vector senarai_dokumen_aduan(String id_esaduan)
			throws Exception {
		listDokumen_aduan = new Vector();
		Db db = null;
		listDokumen_aduan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_ESADUAN,A.ID_ESDOKUMEN, A.NAMA_FAIL, A.JENIS_MIME, A.TAJUK, A.KETERANGAN,"
					+ " A.CONTENT  FROM TBLESDOKUMEN A,TBLESADUAN P WHERE A.ID_ESADUAN = '"
					+ id_esaduan
					+ "'  "
					+ " AND A.ID_ESADUAN = P.ID_ESADUAN";
			myLogger.info("SQL DOKUMEN ADUAN :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("id_esaduan",
						rs.getString("ID_ESADUAN") == null ? "" : rs
								.getString("ID_ESADUAN"));
				h.put("id_esdokumen", rs.getString("ID_ESDOKUMEN") == null ? ""
						: rs.getString("ID_ESDOKUMEN"));
				h.put("nama_fail", rs.getString("NAMA_FAIL") == null ? "" : rs
						.getString("NAMA_FAIL"));
				h.put("jenis_mime", rs.getString("JENIS_MIME") == null ? ""
						: rs.getString("JENIS_MIME"));
				h.put("tajuk", rs.getString("TAJUK") == null ? "" : rs
						.getString("TAJUK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));

				listDokumen_aduan.addElement(h);

			}

			return listDokumen_aduan;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void deleteDokumen(String id_esdokumen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLESDOKUMEN WHERE ID_ESDOKUMEN = "
					+ id_esdokumen;
			
			myLogger.info("DELETE DOC :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void deleteAduan(String id_esaduan) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "DELETE FROM TBLESDOKUMEN WHERE ID_ESADUAN = "
					+ id_esaduan;			
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM TBLESCOMMENT WHERE ID_ESADUAN = "
				+ id_esaduan;			
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM TBLESNOTIFIKASI WHERE ID_ESADUAN = "
				+ id_esaduan;			
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM TBLESAGIHAN WHERE ID_ESADUAN = "
				+ id_esaduan;			
			stmt.executeUpdate(sql);
			
			sql = "DELETE FROM TBLESADUAN WHERE ID_ESADUAN = "
				+ id_esaduan;			
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteAgihan(String id_fail) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLEDITAGIHAN WHERE ID_FAIL = "
					+ id_fail;
			
			myLogger.info("DELETE AGIHAN :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void deleteComment(HttpSession session,String id_escomment) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";
		String user_id_login = (String) session.getAttribute("_ekptg_user_id");
		String role = (String) session.getAttribute("myrole");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			 sql = "DELETE FROM TBLEDITCOMMENT WHERE ID_EDITCOMMENT = "
					+ id_escomment;
			
			myLogger.info("DELETE COMMENT :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector listNotifikasi = null;
	public Vector senarai_listNotifikasi(String user_id,String jenis_comment)
			throws Exception {
		listNotifikasi = new Vector();
		Db db = null;
		listNotifikasi.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ST.KETERANGAN AS NAMA_STATUS,PM.NAMA_PEMOHON,S.NAMA_SIMATI,F.NO_FAIL,F.ID_FAIL,E.ID_EDITNOTIFIKASI,E.FLAG_NOTIFIKASI,E.ID_USER_NOTIFIKASI_TERIMA,E.ID_USER_NOTIFIKASI_HANTAR,E.ID_FAIL,"
				+" E.FLAG_READ,E.TARIKH_MASUK FROM TBLEDITNOTIFIKASI E,TBLPFDFAIL F," +
						" TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PS,TBLPPKSIMATI S,TBLPPKPEMOHON PM,TBLRUJSTATUS ST " +
						" WHERE E.FLAG_READ = 'NO' AND P.ID_STATUS = ST.ID_STATUS(+) AND P.ID_PEMOHON = PM.ID_PEMOHON AND S.ID_SIMATI = PS.ID_SIMATI AND P.ID_PERMOHONAN = PS.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND E.ID_FAIL = F.ID_FAIL AND E.ID_USER_NOTIFIKASI_TERIMA = '"+user_id+"' " +
						" ORDER BY TO_DATE(E.TARIKH_MASUK,'DD/MM/YYYY') DESC";
			myLogger.info("-------------------SQL listNotifikasi:" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("NAMA_STATUS",
						rs.getString("NAMA_STATUS") == null ? "" : rs
								.getString("NAMA_STATUS").toUpperCase());
				h.put("NAMA_PEMOHON",
						rs.getString("NAMA_PEMOHON") == null ? "" : rs
								.getString("NAMA_PEMOHON").toUpperCase());
				h.put("NAMA_SIMATI",
						rs.getString("NAMA_SIMATI") == null ? "" : rs
								.getString("NAMA_SIMATI").toUpperCase());
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NO_FAIL",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL").toUpperCase());
				h.put("ID_EDITNOTIFIKASI",
						rs.getString("ID_EDITNOTIFIKASI") == null ? "" : rs
								.getString("ID_EDITNOTIFIKASI"));
				h.put("FLAG_NOTIFIKASI",
						rs.getString("FLAG_NOTIFIKASI") == null ? "" : rs
								.getString("FLAG_NOTIFIKASI"));
				h.put("ID_USER_NOTIFIKASI_TERIMA",
						rs.getString("ID_USER_NOTIFIKASI_TERIMA") == null ? "" : rs
								.getString("ID_USER_NOTIFIKASI_TERIMA"));
				h.put("ID_USER_NOTIFIKASI_HANTAR",
						rs.getString("ID_USER_NOTIFIKASI_HANTAR") == null ? "" : rs
								.getString("ID_USER_NOTIFIKASI_HANTAR"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("FLAG_READ",
						rs.getString("FLAG_READ") == null ? "" : rs
								.getString("FLAG_READ"));
				

				listNotifikasi.addElement(h);
			}

			return listNotifikasi;

		} finally {
			if (db != null)
				db.close();
		}

	}
	

	
	Vector listComment_aduan = null;
	public Vector senarai_comment_aduan(String id_fail,String jenis_comment)
			throws Exception {
		listComment_aduan = new Vector();
		Db db = null;
		listComment_aduan.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.FLAG_JENIS_COMMENT,U.USER_NAME,INITCAP(U.USER_NAME) AS USER_NAME_INIT," +
					"A.ID_EDITCOMMENT,A.ID_FAIL, A.USER_ID, A.KETERANGAN, FLAG_SIAPA_COMMENT, " +
					"A.FLAG_PAPARAN_ADMIN,"
					+ " A.FLAG_PAPARAN_DEVELOPER,A.FLAG_PAPARAN_PENGADU," +
							"TO_CHAR(A.TARIKH_KEMASKINI,'dd/mm/yyyy hh:mi am') AS TARIKH_KEMASKINI," +
							"TO_CHAR(A.TARIKH_MASUK,'dd/mm/yyyy hh:mi am') AS TARIKH_MASUK,A.ID_MASUK  " +
							" FROM TBLEDITCOMMENT A,TBLPFDFAIL P,USERS U WHERE A.ID_FAIL = '"
					+ id_fail
					//flag_jenis_comment 1-untuk pengadu 2-untuk tech team
					+ "' AND A.FLAG_JENIS_COMMENT = '"+jenis_comment+"'"
					+ " AND A.ID_FAIL = P.ID_FAIL AND U.USER_ID = A.USER_ID ORDER BY A.TARIKH_MASUK ASC";
			myLogger.info("SQL COMMENT ADUAN :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("id_escomment",
						rs.getString("ID_EDITCOMMENT") == null ? "" : rs
								.getString("ID_EDITCOMMENT"));
				h.put("id_masuk",
						rs.getString("ID_MASUK") == null ? "" : rs
								.getString("ID_MASUK"));
				h.put("id_fail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("user_id",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID"));
				h.put("maklumbalas",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("flag_siapa_comment",
						rs.getString("FLAG_SIAPA_COMMENT") == null ? "" : rs
								.getString("FLAG_SIAPA_COMMENT"));
				h.put("flag_papar_admin",
						rs.getString("FLAG_PAPARAN_ADMIN") == null ? "" : rs
								.getString("FLAG_PAPARAN_ADMIN"));
				h.put("flag_papar_developer",
						rs.getString("FLAG_PAPARAN_DEVELOPER") == null ? "" : rs
								.getString("FLAG_PAPARAN_DEVELOPER"));
				h.put("flag_papar_pengadu",
						rs.getString("FLAG_PAPARAN_PENGADU") == null ? "" : rs
								.getString("FLAG_PAPARAN_PENGADU"));
				if (rs.getString("TARIKH_MASUK") == null) {
					h.put("tarikh_masuk", "");
				} else {
					h.put("tarikh_masuk", rs.getString("TARIKH_MASUK"));
				}
				if (rs.getString("TARIKH_KEMASKINI") == null) {
					h.put("tarikh_kemaskini", "");
				} else {
					h.put("tarikh_kemaskini", rs.getString("TARIKH_KEMASKINI"));
				}
				h.put("nama_user",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME").toUpperCase());
				h.put("nama_user_init",
						rs.getString("USER_NAME_INIT") == null ? "" : rs
								.getString("USER_NAME_INIT"));
				h.put("flag_jenis_comment",
						rs.getString("FLAG_JENIS_COMMENT") == null ? "" : rs
								.getString("FLAG_JENIS_COMMENT").toUpperCase());
				
				

				listComment_aduan.addElement(h);
			}

			return listComment_aduan;

		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	Vector listComment_aduan_check = null;
	public Vector senarai_comment_aduan_check(String id_fail,String jenis_comment)
			throws Exception {
		listComment_aduan_check = new Vector();
		Db db = null;
		listComment_aduan_check.clear();
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.FLAG_JENIS_COMMENT,U.USER_NAME,A.ID_EDITCOMMENT,A.ID_FAIL, A.USER_ID, A.KETERANGAN, " +
					" FLAG_SIAPA_COMMENT, A.FLAG_PAPARAN_ADMIN,"
					+ " A.FLAG_PAPARAN_DEVELOPER,A.FLAG_PAPARAN_PENGADU," +
							" TO_CHAR(A.TARIKH_MASUK,'dd/mm/yyyy hh:mi am') AS TARIKH_MASUK  " +
							" FROM TBLEDITCOMMENT A,TBLPFDFAIL P,USERS U WHERE A.ID_FAIL = '"
					+ id_fail
					//flag_jenis_comment 1-untuk pengadu 2-untuk tech team
					+ "' AND A.FLAG_JENIS_COMMENT = '"+jenis_comment+"'"
					+ " AND A.ID_FAIL = P.ID_FAIL " +
							//" AND A.FLAG_SIAPA_COMMENT NOT IN (1,2) " +
							" AND U.USER_ID = A.USER_ID ORDER BY A.TARIKH_MASUK ASC";
			myLogger.info("SQL COMMENT ADUAN CHECK :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;

			while (rs.next()) {

				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("id_escomment",
						rs.getString("ID_EDITCOMMENT") == null ? "" : rs
								.getString("ID_EDITCOMMENT"));				
				h.put("id_esaduan",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("user_id",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID"));
				h.put("maklumbalas",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("flag_siapa_comment",
						rs.getString("FLAG_SIAPA_COMMENT") == null ? "" : rs
								.getString("FLAG_SIAPA_COMMENT"));
				h.put("flag_papar_admin",
						rs.getString("FLAG_PAPARAN_ADMIN") == null ? "" : rs
								.getString("FLAG_PAPARAN_ADMIN"));
				h.put("flag_papar_developer",
						rs.getString("FLAG_PAPARAN_DEVELOPER") == null ? "" : rs
								.getString("FLAG_PAPARAN_DEVELOPER"));
				h.put("flag_papar_pengadu",
						rs.getString("FLAG_PAPARAN_PENGADU") == null ? "" : rs
								.getString("FLAG_PAPARAN_PENGADU"));
				if (rs.getString("TARIKH_MASUK") == null) {
					h.put("tarikh_masuk", "");
				} else {
					h.put("tarikh_masuk", rs.getString("TARIKH_MASUK"));
				}
				h.put("nama_user",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME").toUpperCase());
				h.put("flag_jenis_comment",
						rs.getString("FLAG_JENIS_COMMENT") == null ? "" : rs
								.getString("FLAG_JENIS_COMMENT").toUpperCase());
				
				

				listComment_aduan_check.addElement(h);
			}

			return listComment_aduan_check;

		} finally {
			if (db != null)
				db.close();
		}

	}

	
	
	

	
	Vector listAgihan_aduan = null;
	public Vector senarai_agihan_aduan(String id_fail)
			throws Exception {
		listAgihan_aduan = new Vector();
		Db db = null;
		listAgihan_aduan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_EDITAGIHAN,A.ID_FAIL,A.USER_ID,U.USER_NAME,A.KETERANGAN AS CATATAN_AGIHAN " +
					" FROM TBLEDITAGIHAN A,USERS U WHERE A.USER_ID = U.USER_ID AND A.ID_FAIL = '"
					+ id_fail
					+ "'  "
					+ " ";
			myLogger.info("SQL AGIHAN ADUAN :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("id_esagihan",
						rs.getString("ID_EDITAGIHAN") == null ? "" : rs
								.getString("ID_EDITAGIHAN"));				
				h.put("id_fail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				
				h.put("user_name",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("user_id",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID"));
				h.put("catatan_agihan",
						rs.getString("CATATAN_AGIHAN") == null ? "" : rs
								.getString("CATATAN_AGIHAN"));				
				listAgihan_aduan.addElement(h);
			}
			return listAgihan_aduan;
		} finally {
			if (db != null)
				db.close();
		}

	}

	
	
	/*
	Vector listTechTeam_aduan = null;
	public Vector senarai_admin_team(String id_negeri_user,String role,String user_id,String flag_all)
			throws Exception {
		listTechTeam_aduan = new Vector();
		Db db = null;
		listTechTeam_aduan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT US.USER_NAME,US.USER_ID,US.USER_LOGIN FROM USERS US,USERS_INTERNAL UI WHERE US.USER_ID = UI.USER_ID ";
			if(flag_all.equals("all"))
			{
				sql += " AND US.USER_ROLE IN ('adminppk','user_ppk')";
			}
			else
			{
				sql += " AND US.USER_ROLE IN ('adminppk')";	
			}
			
			sql += " AND UI.ID_PEJABATJKPTG IN "+
			 " (SELECT DISTINCT U.ID_PEJABATJKPTG FROM TBLRUJPEJABATURUSAN U, "+
			 " USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
			 " AND UR.USER_ID = '"+user_id+"')";	
			myLogger.info("SQL TEAM TECHNICAL xxxxxx :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("user_name",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));				
				h.put("user_id",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID"));
				h.put("user_login",
						rs.getString("USER_LOGIN") == null ? "" : rs
								.getString("USER_LOGIN"));				
				listTechTeam_aduan.addElement(h);
			}
			return listTechTeam_aduan;
		} finally {
			if (db != null)
				db.close();
		}

	}
	*/
	
	
	Vector listTechTeam_aduan = null;
	public Vector senarai_admin_team(String id_negeri_user,String role,String user_id,String flag_all,String id_fail)
			throws Exception {
		listTechTeam_aduan = new Vector();
		Db db = null;
		listTechTeam_aduan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			/*sql = " SELECT US.USER_NAME,US.USER_ID,US.USER_LOGIN,US.USER_ROLE FROM USERS US,USERS_INTERNAL UI WHERE US.USER_ID = UI.USER_ID ";
			if(flag_all.equals("all"))
			{
				sql += " AND US.USER_ROLE IN ('adminppk','user_ppk')";
			}
			else
			{
				sql += " AND US.USER_ROLE IN ('adminppk')";	
			}
			
			sql += " AND UI.ID_PEJABATJKPTG IN "+
			 " (SELECT DISTINCT UR.ID_PEJABATJKPTG FROM TBLRUJPEJABATURUSAN UR,TBLRUJPEJABATJKPTG PJ  WHERE UR.ID_DAERAHURUS IN "+
				"	(SELECT DISTINCT P.ID_DAERAHMHN FROM TBLPFDFAIL F,TBLPPKPERMOHONAN P WHERE P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"+id_fail+"' ) "+
				"	AND UR.id_jenispejabat IN (22,21) AND PJ.ID_SEKSYEN = '2' AND UR.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG  AND UR.ID_PEJABATJKPTG NOT IN '76') ";
			
			*/
			
			sql = "SELECT * FROM (" +
					" SELECT distinct US.USER_NAME, US.USER_ID, US.USER_LOGIN, US.USER_ROLE" +
					" FROM USERS US, USERS_INTERNAL UI, USER_ROLE UR" +
					" WHERE US.USER_ID = UI.USER_ID AND US.USER_LOGIN = UR.USER_ID(+)" +
					" AND UI.ID_PEJABATJKPTG IN (" +
					" SELECT DISTINCT UR.ID_PEJABATJKPTG FROM TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PJ" +
					" WHERE UR.ID_DAERAHURUS IN (" +
					" SELECT DISTINCT P.ID_DAERAHMHN" +
					" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P" +
					" WHERE P.ID_FAIL = F.ID_FAIL" +
					" AND F.ID_FAIL = '"+id_fail+"' " +
					" ) AND UR.ID_JENISPEJABAT IN (22, 21,24)" +
					" AND PJ.ID_SEKSYEN = '2'" +
					" AND UR.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG" +
					" AND UR.ID_PEJABATJKPTG NOT IN '76')" ;
					if(flag_all.equals("all"))
					{
						//sql += " AND US.USER_ROLE IN ('adminppk','user_ppk')";
						sql += " AND (US.USER_ROLE IN ('adminppk','user_ppk','usernegeri_ppk') OR UR.ROLE_ID IN ('adminppk','user_ppk','usernegeri_ppk'))";	
					}
					else
					{
						//sql += " AND US.USER_ROLE IN ('adminppk')";	
						sql += "  AND (US.USER_ROLE IN ('adminppk') OR UR.ROLE_ID IN ('adminppk') OR UR.ROLE_ID IN ('usernegeri_ppk'))";	
					}
					//" AND (US.USER_ROLE IN ('adminppk') OR UR.ROLE_ID IN ('adminppk'))" +
					sql += " UNION ALL" +
					" SELECT distinct US.USER_NAME, US.USER_ID, US.USER_LOGIN, US.USER_ROLE" +
					" FROM USERS US, USERS_INTERNAL UI, (" +
					" SELECT BU.ID_PEMOHON FROM TBLPERMOHONANBANTUUNIT BU" +
					" WHERE BU.ID_UNIT IN (" +
					" SELECT DISTINCT UR.ID_PEJABATJKPTG" +
					" FROM TBLRUJPEJABATURUSAN UR, TBLRUJPEJABATJKPTG PJ" +
					" WHERE UR.ID_DAERAHURUS IN (" +
					" SELECT DISTINCT P.ID_DAERAHMHN" +
					" FROM TBLPFDFAIL F, TBLPPKPERMOHONAN P" +
					" WHERE P.ID_FAIL = F.ID_FAIL" +
					" AND F.ID_FAIL = '"+id_fail+"' " +
					" )) AND   ((TO_DATE (TO_CHAR (BU.TARIKH_MULA, 'DD/MM/YYYY'),'DD/MM/YYYY') >" +
					" SYSDATE AND (TO_DATE (TO_CHAR (BU.TARIKH_AKHIR, 'DD/MM/YYYY')," +
					" 'DD/MM/YYYY') >= SYSDATE )) OR (TO_DATE (TO_CHAR (BU.TARIKH_MULA, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=" +
					" (SYSDATE) AND (TO_DATE (TO_CHAR (BU.TARIKH_AKHIR, 'DD/MM/YYYY'), 'DD/MM/YYYY'" +
					" ) >= SYSDATE))) AND (   TO_DATE (TO_CHAR (BU.TARIKH_AKHIR, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=" +
					" SYSDATE OR TO_DATE (TO_CHAR (BU.TARIKH_MULA, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=" +
					" SYSDATE)) CHECKBU, USER_ROLE UR" +
					" WHERE US.USER_ID = UI.USER_ID AND US.USER_ID = CHECKBU.ID_PEMOHON" +
					" AND US.USER_LOGIN = UR.USER_ID(+)" ;
					if(flag_all.equals("all"))
					{
						//sql += " AND US.USER_ROLE IN ('adminppk','user_ppk')";
						sql += " AND (US.USER_ROLE IN ('adminppk','user_ppk','usernegeri_ppk') OR UR.ROLE_ID IN ('adminppk','user_ppk','usernegeri_ppk'))";	
					}
					else
					{
						//sql += " AND US.USER_ROLE IN ('adminppk')";	
						sql += "  AND (US.USER_ROLE IN ('adminppk') OR UR.ROLE_ID IN ('adminppk') OR UR.ROLE_ID IN ('usernegeri_ppk'))";	
					}
					//" AND (US.USER_ROLE IN ('adminppk') OR UR.ROLE_ID IN ('adminppk'))" +
					sql += " ) ORDER BY USER_NAME";
			
			
			
			myLogger.info("SQL TEAM TECHNICAL xxxxxx :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("user_name",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));				
				h.put("user_id",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID"));
				h.put("user_login",
						rs.getString("USER_LOGIN") == null ? "" : rs
								.getString("USER_LOGIN"));
				h.put("USER_ROLE",
						rs.getString("USER_ROLE") == null ? "" : rs
								.getString("USER_ROLE"));
				listTechTeam_aduan.addElement(h);
			}
			return listTechTeam_aduan;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public Vector senarai_pegawai_PPKNegeri(String idNegeri,String role,String user_id,String flag_all,String id_fail)
			throws Exception {
		listTechTeam_aduan = new Vector();
		Db db = null;
		listTechTeam_aduan.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT ID_UNITPSK, USER_ID, NVL(KOD,'') KOD, upper(NAMA_PEGAWAI) NAMA_PEGAWAI,STATUS_PEG " + 
					" ,case " + 
					" 	when STATUS_PEG=1 then '(Aktif)' "+ 
					" 	else '(Tidak Aktif)' " + " " +
					" end AS CATATAN " + 
					" FROM TBLPPKRUJUNIT WHERE ID_NEGERI = '" + idNegeri+ "' " +
					" ORDER BY STATUS_PEG desc ,NAMA_PEGAWAI asc";
			
			
			
			myLogger.info("SQL TEAM TECHNICAL xxxxxx :" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				
				h.put("user_name",
						rs.getString("NAMA_PEGAWAI") == null ? "" : rs
								.getString("NAMA_PEGAWAI"));				
				h.put("user_id",
						rs.getString("ID_UNITPSK") == null ? "" : rs
								.getString("ID_UNITPSK"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("userID",
						rs.getString("USER_ID") == null ? "" : rs
								.getString("USER_ID"));
				
				listTechTeam_aduan.addElement(h);
			}
			return listTechTeam_aduan;
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	
	Vector list_notifikasi = null;
	public Vector getListNotifikasi(String id_fail,String flag_notifikasi,String user_terima,String notread) throws Exception {
		list_notifikasi = new Vector();
		list_notifikasi.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT A.ID_EDITNOTIFIKASI,A.FLAG_NOTIFIKASI,A.ID_USER_NOTIFIKASI_HANTAR,A.ID_USER_NOTIFIKASI_TERIMA," +
					"A.ID_FAIL,A.FLAG_READ " +
					"FROM TBLEDITNOTIFIKASI A";
			sql += " WHERE A.ID_EDITNOTIFIKASI is not null ";
			if(!id_fail.equals(""))
			{
			sql += " AND A.ID_FAIL = '"+id_fail+ "' ";
			}
			if(!user_terima.equals(""))
			{
			sql += " AND A.ID_USER_NOTIFIKASI_TERIMA = '"+user_terima+"' ";
			}
			if(!flag_notifikasi.equals(""))
			{
			sql += " AND A.FLAG_NOTIFIKASI = '"+flag_notifikasi+"'";
			}			
			if(!notread.equals(""))
			{
			sql += " AND A.FLAG_READ = '"+notread+"'";
			}
			myLogger.info("GET PERNAH NOTIFY :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_esnotifikasi", rs.getString("ID_EDITNOTIFIKASI"));
				list_notifikasi.addElement(h);
			}
			return list_notifikasi;
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
			myLogger.info("LIST NOTIFICATION LIST"+sql.toUpperCase());
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
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_url = null;
	public Vector getListURL(String id_negeriurl) throws Exception {
		list_url = new Vector();
		list_url.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_NEGERIURL,URL FROM TBLRUJNEGERIURL" +
					" WHERE ID_NEGERIURL = "+id_negeriurl;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				h.put("id_negeriurl", rs.getString("ID_NEGERIURL"));
				if (rs.getString("URL") == null) {
					h.put("url", "");
				} else {
					h.put("url", rs.getString("URL"));
				}
				
				list_url.addElement(h);
			}
			return list_url;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	Hashtable url = null;
	public Hashtable get_url(String id_negeriurl) throws Exception {
		url = new Hashtable();
		url.clear();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_NEGERIURL,URL FROM TBLRUJNEGERIURL" +
			" WHERE ID_NEGERIURL = "+id_negeriurl;
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("id_negeriurl", rs.getString("ID_NEGERIURL"));
				if (rs.getString("URL") == null) {
					h.put("url", "");
				} else {
					h.put("url", rs.getString("URL"));
				}			
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	
	Vector list_laporanmodul = null;
	public Vector getLaporanModul(String id_negeri,String tarikh_mula,String tarikh_akhir,String id_modul) throws Exception {
		
		list_laporanmodul = new Vector();
		list_laporanmodul.clear();
		Db db = null;
		String sql = "";
		String sql_filter = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			 if(!tarikh_mula.equals("") && !tarikh_mula.equals(null) )
				{
			    	sql_filter += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') >= '"+tarikh_mula+"'  ";	
				}
				if( !tarikh_akhir.equals("") && !tarikh_akhir.equals(null))
				{
					sql_filter += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') <= '"+tarikh_akhir+"'";	
				}				
				if(!id_modul.equals("") && !id_modul.equals(null))
				{
					sql_filter += " AND A.ID_JENISMODULESADUAN = '"+id_modul+"' ";	
				}
				if(!id_negeri.equals("") && !id_negeri.equals(null))
				{		
					sql_filter += " AND A.ID_NEGERI_PENGADU = '"+id_negeri+"'";		
				}
			
			
			sql = " SELECT JM.ID_JENISMODULESADUAN,JM.KETERANGAN AS NAMA_MODUL, "+
			
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS NOT IN ('16125') " +
				"AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN ";			
				sql += sql_filter;
				sql +=	") AS JUMLAH_ADUAN, "+
				
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16121') AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN ";
				sql += sql_filter;
				sql +=	") AS ADUAN_BARU, "+
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16122') AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN ";
				sql += sql_filter;
				sql += ")  AS ADUAN_DALAM_TINDAKAN, "+
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16123') AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN ";
				sql += sql_filter;
				sql += ") AS ADUAN_SELESAI ";
				
				
				sql += " FROM TBLRUJJENISMODULESADUAN JM ";
			
			if(!id_modul.equals("") && !id_modul.equals(null))
			{		
			sql += " WHERE JM.ID_JENISMODULESADUAN = '"+id_modul+"'";		
			}
			myLogger.info("LIST LAPORAN MODUL :"+sql.toUpperCase());
			
			
			int bil = 0;

			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);				
				if (rs.getString("NAMA_MODUL") == null) {
					h.put("nama_modul", "");
				} else {
					h.put("nama_modul", rs.getString("NAMA_MODUL"));
				}				
				if (rs.getString("JUMLAH_ADUAN") == null) {
					h.put("jumlah_aduan", "");
				} else {
					h.put("jumlah_aduan", rs.getInt("JUMLAH_ADUAN"));
				}				
				if (rs.getString("ADUAN_BARU") == null) {
					h.put("aduan_baru", "");
				} else {
					h.put("aduan_baru", rs.getInt("ADUAN_BARU"));
				}				
				if (rs.getString("ADUAN_DALAM_TINDAKAN") == null) {
					h.put("aduan_dalam_tindakan", "");
				} else {
					h.put("aduan_dalam_tindakan", rs.getInt("ADUAN_DALAM_TINDAKAN"));
				}				
				if (rs.getString("ADUAN_SELESAI") == null) {
					h.put("aduan_selesai", "");
				} else {
					h.put("aduan_selesai", rs.getInt("ADUAN_SELESAI"));
				}
				
				list_laporanmodul.addElement(h);
			}
			return list_laporanmodul;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_laporannegeri = null;
	public Vector getLaporanNegeri(String id_negeri,String tarikh_mula,String tarikh_akhir,String id_modul) throws Exception {
		
		list_laporannegeri = new Vector();
		list_laporannegeri.clear();
		Db db = null;
		String sql = "";
		String sql_filter = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			 if(!tarikh_mula.equals("") && !tarikh_mula.equals(null) )
				{
			    	sql_filter += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') >= '"+tarikh_mula+"'  ";	
				}
				if( !tarikh_akhir.equals("") && !tarikh_akhir.equals(null))
				{
					sql_filter += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') <= '"+tarikh_akhir+"'";	
				}				
				if(!id_modul.equals("") && !id_modul.equals(null))
				{
					sql_filter += " AND A.ID_JENISMODULESADUAN = '"+id_modul+"' ";	
				}
				if(!id_negeri.equals("") && !id_negeri.equals(null))
				{		
					sql_filter += " AND A.ID_NEGERI_PENGADU = '"+id_negeri+"'";		
				}
			
			
			sql = " SELECT JM.ID_NEGERI,JM.NAMA_NEGERI, "+
			
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS NOT IN ('16125') " +
				"AND A.ID_NEGERI_PENGADU = JM.ID_NEGERI ";			
				sql += sql_filter;
				sql +=	") AS JUMLAH_ADUAN, "+
				
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16121') AND A.ID_NEGERI_PENGADU = JM.ID_NEGERI ";
				sql += sql_filter;
				sql +=	") AS ADUAN_BARU, "+
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16122') AND A.ID_NEGERI_PENGADU = JM.ID_NEGERI ";
				sql += sql_filter;
				sql += ")  AS ADUAN_DALAM_TINDAKAN, "+
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16123') AND A.ID_NEGERI_PENGADU = JM.ID_NEGERI ";
				sql += sql_filter;
				sql += ") AS ADUAN_SELESAI ";
				
				
				sql += " FROM TBLRUJNEGERI JM WHERE JM.ID_NEGERI NOT IN ('17','0') ";
			
			if(!id_negeri.equals("") && !id_negeri.equals(null))
			{		
			sql += " AND JM.ID_NEGERI = '"+id_negeri+"'";		
			}
			myLogger.info("LIST LAPORAN NEGERI :"+sql.toUpperCase());
			
			
			int bil = 0;

			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);				
				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("nama_negeri", "");
				} else {
					h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
				}				
				if (rs.getString("JUMLAH_ADUAN") == null) {
					h.put("jumlah_aduan", "");
				} else {
					h.put("jumlah_aduan", rs.getInt("JUMLAH_ADUAN"));
				}				
				if (rs.getString("ADUAN_BARU") == null) {
					h.put("aduan_baru", "");
				} else {
					h.put("aduan_baru", rs.getInt("ADUAN_BARU"));
				}				
				if (rs.getString("ADUAN_DALAM_TINDAKAN") == null) {
					h.put("aduan_dalam_tindakan", "");
				} else {
					h.put("aduan_dalam_tindakan", rs.getInt("ADUAN_DALAM_TINDAKAN"));
				}				
				if (rs.getString("ADUAN_SELESAI") == null) {
					h.put("aduan_selesai", "");
				} else {
					h.put("aduan_selesai", rs.getInt("ADUAN_SELESAI"));
				}
				
				list_laporannegeri.addElement(h);
			}
			return list_laporannegeri;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	Vector list_laporankategori = null;
	public Vector getLaporanKategori(String id_negeri,String tarikh_mula,String tarikh_akhir,String id_modul) throws Exception {
		
		list_laporankategori = new Vector();
		list_laporankategori.clear();
		Db db = null;
		String sql = "";
		String sql_filter = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			    if(!tarikh_mula.equals("") && !tarikh_mula.equals(null) )
				{
			    	sql_filter += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') >= '"+tarikh_mula+"'  ";	
				}
				if( !tarikh_akhir.equals("") && !tarikh_akhir.equals(null))
				{
					sql_filter += " AND TO_CHAR(A.TARIKH_ADUAN_HANTAR,'dd/mm/yyyy') <= '"+tarikh_akhir+"'";	
				}				
				if(!id_modul.equals("") && !id_modul.equals(null))
				{
					sql_filter += " AND A.ID_JENISMODULESADUAN = '"+id_modul+"' ";	
				}
				if(!id_negeri.equals("") && !id_negeri.equals(null))
				{		
					sql_filter += " AND A.ID_NEGERI_PENGADU = '"+id_negeri+"'";		
				}
				
			
			sql = " SELECT JM.ID_JENISADUAN,JM.JENIS_ADUAN, "+
			
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS NOT IN ('16125') " +
				"AND A.ID_JENISADUAN = JM.ID_JENISADUAN ";			
				sql += sql_filter;
				sql +=	") AS JUMLAH_ADUAN, "+
				
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16121') AND A.ID_JENISADUAN = JM.ID_JENISADUAN ";
				sql += sql_filter;
				sql +=	") AS ADUAN_BARU, "+
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16122') AND A.ID_JENISADUAN = JM.ID_JENISADUAN ";
				sql += sql_filter;
				sql += ")  AS ADUAN_DALAM_TINDAKAN, "+
				" (SELECT COUNT(*) FROM TBLESADUAN A WHERE A.ID_STATUS IN ('16123') AND A.ID_JENISADUAN = JM.ID_JENISADUAN ";
				sql += sql_filter;
				sql += ") AS ADUAN_SELESAI ";
				
				
				sql += " FROM TBLRUJJENISESADUAN JM  ";
			
			
			myLogger.info("LIST LAPORAN KATEGORI :"+sql.toUpperCase());
			
			
			int bil = 0;

			
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Hashtable h = new Hashtable();
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);				
				if (rs.getString("JENIS_ADUAN") == null) {
					h.put("jenis_aduan", "");
				} else {
					h.put("jenis_aduan", rs.getString("JENIS_ADUAN"));
				}				
				if (rs.getString("JUMLAH_ADUAN") == null) {
					h.put("jumlah_aduan", "");
				} else {
					h.put("jumlah_aduan", rs.getInt("JUMLAH_ADUAN"));
				}				
				if (rs.getString("ADUAN_BARU") == null) {
					h.put("aduan_baru", "");
				} else {
					h.put("aduan_baru", rs.getInt("ADUAN_BARU"));
				}				
				if (rs.getString("ADUAN_DALAM_TINDAKAN") == null) {
					h.put("aduan_dalam_tindakan", "");
				} else {
					h.put("aduan_dalam_tindakan", rs.getInt("ADUAN_DALAM_TINDAKAN"));
				}				
				if (rs.getString("ADUAN_SELESAI") == null) {
					h.put("aduan_selesai", "");
				} else {
					h.put("aduan_selesai", rs.getInt("ADUAN_SELESAI"));
				}
				
				list_laporankategori.addElement(h);
			}
			return list_laporankategori;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getNamaSiMati( String id_permohonan) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String namaSimati = "";
			
			sql = 	" SELECT C.NAMA_SIMATI " +
					" FROM " +
					" TBLPPKPERMOHONANSIMATI A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C " +
					" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN " +
					" AND A.ID_SIMATI = C.ID_SIMATI " +
					" AND A.ID_PERMOHONAN = "+id_permohonan ;
			
					
	
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					namaSimati = rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI");
				}
			
			return namaSimati;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getNokpSiMati( String id_permohonan) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String namaSimati = "";
			
			sql = 	" SELECT C.NAMA_SIMATI " +
					" FROM " +
					" TBLPPKPERMOHONANSIMATI A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C " +
					" WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN " +
					" AND A.ID_SIMATI = C.ID_SIMATI " +
					" AND A.ID_PERMOHONAN = "+id_permohonan ;
			
					
	
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					namaSimati = rs.getString("NAMA_SIMATI") == null ? "" : rs.getString("NAMA_SIMATI");
				}
			
			return namaSimati;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String getNamaUnit( String id_unit) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String namaUnit = "";
			
			sql = 	" SELECT NAMA_PEJABAT " +
					" FROM " +
					" TBLRUJPEJABATJKPTG A " +
					" WHERE ID_PEJABATJKPTG = "+id_unit ;
			
					
	
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					namaUnit = rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT");
				}
			
			return namaUnit;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	
}
