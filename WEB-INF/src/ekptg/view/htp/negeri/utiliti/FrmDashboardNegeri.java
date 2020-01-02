
package ekptg.view.htp.negeri.utiliti;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.rekod.HTPStatusRekodBean;
import ekptg.view.admin.Pengumuman;

public class FrmDashboardNegeri extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1533214139048679989L;
	//private final String idUrusan = "11";
	//private final String IDSUBURUSANCUKAI = "43";
	private final String idUrusanPajakanKecil = "309";
	private final String IDSUBURUSANREKOD = "61";
	private final String PATH="app/htp/negeri/utiliti/";
	private IHTPPermohonan iHTPPermohonan = null;
	private IHTPStatus iStatus = null;
	private InternalUser iu = null;	
	static Logger myLog = Logger.getLogger(ekptg.view.htp.negeri.utiliti.FrmDashboardNegeri.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	Pengumuman logic = new Pengumuman();	

	@Override
	public String doTemplate2() throws Exception {	
			
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String portal_role = (String)session.getAttribute("myrole");
		iu = InternalUserUtil.getSeksyenId(userId);
		String idNegeri = iu.getIdNegeri();
		this.context.put("portal_role_",portal_role);
		//myLog.info(portal_role +"::"+(String)session.getAttribute("myrole"));
		//String command = getParam("command");
		
		Hashtable<String,String> get_stat = null;
		Integer check_notifikasi_aduan = 0;
		//Integer check_notifikasi_online8 = 0;
		//Integer check_notifikasi_online17 = 0;
		//Integer check_notifikasi_pindah = 0;
		Integer check_notifikasi_inbox = 0;
		Vector<Hashtable<String,String>> list_memo_aktif = null;
		
		role = (String)session.getAttribute("myrole");
		userId = (String)session.getAttribute("_ekptg_user_id");
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
			
		get_stat = (Hashtable<String,String>) statFail(idNegeri);
		String negeriServer = (String)get_stat.get("NAMA_NEGERI_SERVER");
		String jumlahKeseluruhan = (String)get_stat.get("JUMLAH_KESELURUHAN");
		String fail01 = (String)get_stat.get("JUMLAH_PEMBERIMILIKAN");
		String fail02 = (String)get_stat.get("JUMLAH_PEMBELIAN");
		String fail03 = (String)get_stat.get("JUMLAH_PAJAKAN");
		String fail04 = (String)get_stat.get("JUMLAH_PENSWASTAAN");
		String fail05 = (String)get_stat.get("JUMLAH_PERLETAKHAKAN");
		String fail10 = (String)get_stat.get("JUMLAH_PERIZAPAN");
		String fail108 = (String)get_stat.get("JUMLAH_GADAIAN");
		String fail309 = (String)get_stat.get("JUMLAH_PKECIL");
		String failHapus = (String)get_stat.get("JUMLAH_FAIL_HAPUS");
		String jumlahHakmilik = (String)get_stat.get("JUMLAH_HAKMILIK");
		String jumlahRizab = (String)get_stat.get("JUMLAH_RIZAB");
		//String fail_selesai = (String)get_stat.get("JUMLAH_SELESAI");
		//String fail_xselesai = (String)get_stat.get("JUMLAH_XSELESAI");
		
		check_notifikasi_aduan = getListNotifikasi_main_list(role,user_negeri_login,"","",userId,"NO");
		context.put("check_notifikasi_aduan",check_notifikasi_aduan);		
//		check_notifikasi_online8 =  getListNotifikasi_online8(userId,"8");
//		context.put("check_notifikasi_online8",check_notifikasi_online8);		
//		check_notifikasi_online17 =  getListNotifikasi_online8(userId,"17");
//		context.put("check_notifikasi_online17",check_notifikasi_online17);
//		check_notifikasi_pindah =  getListNotifikasi_pindah(userId);
//		context.put("check_notifikasi_pindah",check_notifikasi_pindah);	
		check_notifikasi_inbox =  getListNotifikasi_inbox(userId);
		context.put("check_notifikasi_inbox",check_notifikasi_inbox);
		
		context.put("negeriSever", negeriServer);
		context.put("jumlahKeseluruhan", jumlahKeseluruhan);
		context.put("failPemberimilikan", fail01);
		context.put("failPembelian", fail02);
		context.put("failPajakan", fail03);
		context.put("failPenswastaan", fail04);
		context.put("failPerletakhakan", fail05);
		context.put("failPerizapan", fail10);
		context.put("failGadaian", fail108);
		context.put("failPKecil", fail309);
		context.put("failHapus", failHapus);
		context.put("jumlahHakmilik", jumlahHakmilik);
		context.put("jumlahRizab", jumlahRizab);
			//context.put("fail_selesai", fail_selesai);
		//context.put("fail_xselesai", fail_xselesai);
		
		
		list_memo_aktif = logic.getMemo("","Aktif","1","0");
//		myLog.info("list_memo_aktif="+list_memo_aktif.size());
		context.put("list_memo_aktif",list_memo_aktif);		
		//
		int bilTugasan = 0; 
		
		String bilPajakanKecil = "0";
		int bilPajakanKecilInt = 0;
		
		//int bilTugasanCukai = 0; 
		int bilTugasanRekod = 0; 
		//String langkahCukai = "0";
		String langkahPajakanKecil= "0";
		String langkahRekod = "0";
		Vector<Hashtable<String,String>> vecMaklumatPembangunan = null;
		Vector<Hashtable<String,String>> vecPajakanKecil = null;
		if(portal_role.contains("PenggunaNegeri")){
  			langkahRekod = "18";
  			langkahPajakanKecil = "81";
 
		}else if(portal_role.contains("PegawaiNegeri")){
 			langkahRekod = "19";
  			langkahPajakanKecil = "82";
 		
		}else if(portal_role.contains("PengarahNegeri")){
 			langkahRekod = "20";
  			langkahPajakanKecil = "83";
 
		}
//		myLog.info("langkahRekod="+langkahRekod);
  		vecMaklumatPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahRekod,idNegeri);
  		bilTugasanRekod = vecMaklumatPembangunan.size();
//		myLog.info("bilTugasanRekod="+bilTugasanRekod);
		context.put("bilTugasanRekodInt",bilTugasanRekod);
		context.put("bilTugasanRekod",String.valueOf(bilTugasanRekod));
		
		vecPajakanKecil = getHTPermohonan().getPermohonanAktifLangkah(idUrusanPajakanKecil,langkahPajakanKecil);
		bilPajakanKecilInt = vecPajakanKecil.size();
//		myLog.info("bilPajakanKecilInt="+bilPajakanKecilInt);
		bilPajakanKecil = String.valueOf(vecPajakanKecil.size());
		context.put("bilPajakanKecil",bilPajakanKecil);
		
		bilTugasan = bilPajakanKecilInt+bilTugasanRekod;
//		myLog.info("bilTugasan="+bilTugasan);
		context.put("bilTugasanInt",bilTugasan);
		context.put("bilTugasan",String.valueOf(bilTugasan));
		/*
		if(command.equals("changeTab")){
			myLogger.info("INDEX :"+getParam("tab_index"));
			context.put("tab_index",getParam("tab_index"));				
		}
		else		
		{
			context.put("tab_index",0);			
		}
		*/
		if(session.getAttribute("rbFile") == null){
			ResourceBundle rb = ResourceBundle.getBundle("file");
			session.setAttribute("rbFile", rb);
		
		}
		
		String vm = PATH+"dashboard.jsp";
		return vm;
		
	}
	
	public Hashtable<String,String> statFail( String idNegeri) throws Exception {	
		Db db = null;
		String sql = "";
		//Hashtable<String,String> get_negeri = (Hashtable<String,String>) kod_negeri();
		//String kod_negeri = (String)get_negeri.get("KOD_NEGERI");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
			String sqlN = " AND FI.ID_NEGERI="+idNegeri;
			String sqlH = " AND D.ID_NEGERI="+idNegeri;
			sql = " " +
				" SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N " +
				" WHERE S.KOD_NEGERI = N.KOD_NEGERI " +
				" ) AS NAMA_NEGERI_SERVER "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN IN (1,10,5,4,3,2,108,309) "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") "+
				" ) FAIL_HTP "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 1 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PEMBERIMILIKAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 2 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PEMBELIAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 3 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PAJAKAN "+
				" ,NVL(( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 4 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ),'0') FAIL_PENSWASTAAN "+
				" ,NVL(( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 5 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ),'0') FAIL_PERLETAKHAKAN "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 10 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ) FAIL_PERIZAPAN "+
				" ,NVL(( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 108 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ),'0') FAIL_GADAIAN "+
				" ,NVL(( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN = 309 "+
				" 		AND (FI.ID_STATUS IS NULL OR FI.ID_STATUS <> '999' "+sqlN+") " +
				" 		GROUP BY FI.ID_URUSAN "+
				" ),'0') FAIL_PKECIL "+
				" ,( SELECT COUNT(FI.ID_URUSAN) FROM TBLPFDFAIL FI "+
				" 		WHERE FI.ID_URUSAN IN (1,10,5,4,3,2,108,309) "+
				" 		AND (FI.ID_STATUS = '999' "+sqlN+") "+
				" ) FAIL_HAPUS "+
				" ,(SELECT COUNT(*) "+
				" FROM TBLHTPHAKMILIK D "+
				" WHERE NVL(D.NO_HAKMILIK,' ')<>' ' "+
				" AND D.STATUS_SAH IN ( "+
				" 		SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH "+
				" 		WHERE AKTIF=1 "+
				" ) "+sqlH+" "+
				" ) AS JUMLAH_HAKMILIK "+
				" ,(SELECT COUNT(*) "+
				" FROM TBLHTPHAKMILIK D "+
				" WHERE NVL(D.NO_WARTA,' ')<>' ' "+
				" AND D.STATUS_SAH IN ( "+
				" 		SELECT STATUS_SAH FROM TBLHTPRUJSTATUSAH "+
				" 		WHERE AKTIF=1 "+
				" ) "+sqlH+" "+
				" ) AS JUMLAH_RIZAB "+
				" FROM DUAL ";			
			//myLog.info(" STATISTIK :"+sql.toUpperCase());
				
			ResultSet rs = stmt.executeQuery(sql);			
			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
			while (rs.next()) {
				h.put("NAMA_NEGERI_SERVER", Utils.isNull(rs.getString("NAMA_NEGERI_SERVER")));
				h.put("JUMLAH_KESELURUHAN", Utils.isNull(rs.getString("FAIL_HTP")));
				h.put("JUMLAH_PEMBERIMILIKAN",  Utils.isNull(rs.getString("FAIL_PEMBERIMILIKAN")));
				h.put("JUMLAH_PEMBELIAN",  Utils.isNull(rs.getString("FAIL_PEMBELIAN")));
				h.put("JUMLAH_PAJAKAN",  Utils.isNull(rs.getString("FAIL_PAJAKAN")));
				h.put("JUMLAH_PENSWASTAAN",  Utils.isNull(rs.getString("FAIL_PENSWASTAAN")));
				h.put("JUMLAH_PERLETAKHAKAN",  Utils.isNull(rs.getString("FAIL_PERLETAKHAKAN")));
				h.put("JUMLAH_PERIZAPAN",  Utils.isNull(rs.getString("FAIL_PERIZAPAN")));
				h.put("JUMLAH_PKECIL",  Utils.isNull(rs.getString("FAIL_PKECIL")));
				h.put("JUMLAH_GADAIAN",  Utils.isNull(rs.getString("FAIL_GADAIAN")));
				h.put("JUMLAH_FAIL_HAPUS", rs.getString("FAIL_HAPUS"));
				h.put("JUMLAH_HAKMILIK", rs.getString("JUMLAH_HAKMILIK"));
				h.put("JUMLAH_RIZAB", rs.getString("JUMLAH_RIZAB"));
				//h.put("JUMLAH_SELESAI", rs.getString("JUMLAH_SELESAI"));
				//h.put("JUMLAH_XSELESAI", rs.getString("JUMLAH_XSELESAI"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public Hashtable<String,String> kod_negeri() throws Exception {		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
			sql = "SELECT KOD_NEGERI FROM TBLLOOKUPSTATE S ";		
			//myLog.info(" KOD_NEGERI :"+sql.toUpperCase());				
			ResultSet rs = stmt.executeQuery(sql);		
			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
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
	
	Integer count_aduan = null;
	public Integer getListNotifikasi_main_list(String role,String id_negeri_user,String id_esaduan,String flag_notifikasi,String user_terima,String notread) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			//SQLRenderer r = new SQLRenderer();
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
			
			//myLog.info("LIST NOTIFICATION DASHBOARD LIST"+sql.toUpperCase());
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
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			
			//myLogger.info("LIST NOTIFICATION ONLINE 8 DASHBOARD LIST"+sql.toUpperCase());
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
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
				//myLog.info("SQL COUNT LIST MAIN INBOX XX :" + sql.toUpperCase());
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
	
	private IHTPPermohonan getHTPermohonan(){
		if(iHTPPermohonan == null){
			iHTPPermohonan = new HTPPermohonanBean();
		}
		return iHTPPermohonan;
		
	}
	
	private IHTPStatus getStatusRekod(){
		if(iStatus==null){
			iStatus = new HTPStatusRekodBean();
		}
		return iStatus;
				
	}


}






