package ekptg.view.ppt;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmTukaranStatus;

/*
 * @author
 * Razman
 */

public class FrmLaporanPUbyProjek extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(FrmStatusPampasan.class);	
	FrmTukaranStatus model = new FrmTukaranStatus();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	HttpSession session = null;
	String action = null;
	private final String PATH="app/ppt/LAPORAN/";
	private String vm = PATH +"frmLaporanPUbyProjek.jsp";
	List SenaraiFail = null;
	
	
	public String doTemplate2() throws Exception{
		session = request.getSession();
		String command = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +command);
		String doPost = (String) session.getAttribute("doPost");
		myLogger.info("check doPost :"+doPost);   
		String usid=(String)session.getAttribute("_ekptg_user_id");		
		context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));
    	
    	context.put("view_list_fail","");
    	context.put("txtNoFailSub",""); 
		
		if ("cariMainSub".equals(command))
		{
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());    		
    		SenaraiFail = carianFail(session,"fail",txtNoFailSub);
			this.context.put("SenaraiFail",SenaraiFail);
			context.put("view_list_fail","yes");
    	}		
		
		
		
	return vm;
	}
	

	
	
	@SuppressWarnings("unchecked")
	public List carianFail(HttpSession session,String type,String search) 
	throws Exception {

		String userId = (String)session.getAttribute("_ekptg_user_id");
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

			   sql = " SELECT DISTINCT"			    
				    +" (SELECT COUNT(DISTINCT HMX.ID_PEGAWAI) FROM TBLPPTHAKMILIK HMX " 
				   	+" WHERE HMX.ID_PEGAWAI IS NOT NULL AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN)AS BIL_PEGAWAI_BERTUGAS, "
				   	+" (SELECT COUNT(DISTINCT AGHM.ID_AGIHANHM) " 
				   	+" FROM TBLPPTHAKMILIK HMX, TBLPPTAGIHANHM AGHM " 
				   	+" WHERE AGHM.ID_HAKMILIK = HMX.ID_HAKMILIK "
				   	+" AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN "
				   	+" AND AGHM.BARIS = '2' )AS BIL_PEGAWAI_BERTUGAS_BARU, "			    
					+" F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+" U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH,P.TARIKH_MASUK AS TARIKH_MASUK_P "
					+" ,MK2.FLAG_MMK AS FLAG_MMK_PENARIKAN, MKK.STATUS_KEPUTUSAN,";
			   
				    if(type.equals("hakmilik"))
			   		{
				    //add field	
				    sql += " UPPER(PHM.NO_HAKMILIK) AS NO_HAKMILIK,UPPER(PHM.NO_LOT) AS NO_LOT,UPPER(JHM.KOD_JENIS_HAKMILIK) AS KOD_HAKMILIK,UPPER(JHM.KETERANGAN) AS JENIS_HAKMILIK, ";	
			   		}
				    
				    if(type.equals("pb"))
			   		{
				    //add field	
				    sql += " UPPER(PHM.NO_HAKMILIK) AS NO_HAKMILIK,UPPER(PHM.NO_LOT) AS NO_LOT,UPPER(JHM.KOD_JENIS_HAKMILIK) AS KOD_HAKMILIK,UPPER(JHM.KETERANGAN) AS JENIS_HAKMILIK, ";	
				    sql += " UPPER(PB.NAMA_PB) AS NAMA_PB,UPPER(PB.NO_PB) AS NO_PB,UPPER(HPB.NO_AKAUN) AS NO_AKAUN, ";
			   		}
					
					sql += " JK.NAMA_KEMENTERIAN "
					+" FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+" ,TBLPPTPENARIKANBALIK PB, TBLPPTMMK MK2, TBLPPTMMKKEPUTUSAN MKK,";
					
					if(type.equals("hakmilik"))
			   		{
				    //add table	
				    sql += " TBLPPTHAKMILIK PHM,TBLRUJJENISHAKMILIK JHM,";	
			   		}
					
					if(type.equals("pb"))
			   		{
				    //add table	
				    sql += " TBLPPTHAKMILIK PHM,TBLRUJJENISHAKMILIK JHM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB,";	
			   		
			   		}
					
					sql += " TBLRUJKEMENTERIAN JK "
					+" WHERE P.ID_FAIL = F.ID_FAIL" 
					+" AND JK.ID_KEMENTERIAN(+) = F.ID_KEMENTERIAN "
					+" AND S.ID_STATUS = P.ID_STATUS(+) " 
					+" AND P.ID_MASUK = U.USER_ID(+) "
					+" AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+" AND PB.ID_PERMOHONAN(+) = P.ID_PERMOHONAN"
					+" AND MK2.ID_PENARIKANBALIK(+) = PB.ID_PENARIKANBALIK "
					+" AND MK2.ID_MMK = MKK.ID_MMK(+) " 
					+" AND F.ID_SUBURUSAN IN (51,52,53) "
					+" AND P.ID_STATUS NOT IN (8,999) "
					+" AND U.USER_ID = UI.USER_ID(+) ";	   
			   
					if(type.equals("hakmilik"))
			   		{
				    //add condition
				    sql += " AND P.ID_PERMOHONAN = PHM.ID_PERMOHONAN AND PHM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) ";
			   		}
					
					
					if(type.equals("pb"))
			   		{
				    //add condition
				    sql += " AND P.ID_PERMOHONAN = PHM.ID_PERMOHONAN AND PHM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) ";
				    sql += " AND PHM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN ";
			   		}
			   		
			   		if(type.equals("agihan"))
			   		{
			        sql += " AND P.ID_STATUS IN (11,127,16,148,132,133,74,26) ";
			   		}
			   		//myLogger.debug("------------ masuk 1");
			   		if(type.equals("fail"))
			   		{
			   			//myLogger.debug("------------ masuk 2");
			   			if (search != null) 
				   		{
			   				//myLogger.debug("------------ masuk 3");
			   				if (!search.trim().equals("")) {
			   					//myLogger.debug("------------ masuk 4");
								sql += " AND (" +
										" UPPER(f.no_fail) LIKE '%" + search.toUpperCase().trim() + "%' " +
									" OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + search.toUpperCase().trim() + "%' " +
									" OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + search.toUpperCase().trim() + "%' " +
									" OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + search.toUpperCase().trim() + "%' ";
								sql += " OR  UPPER(P.TUJUAN)  LIKE UPPER('%' ||'"+search.toUpperCase().trim()+"'|| '%')";
								sql += " OR  UPPER(JK.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"+search.toUpperCase().trim()+"'|| '%')" +
										")  ";			
							}
				   		}
			   		}
			   		
			   		if(type.equals("hakmilik"))
			   		{
			   			//myLogger.debug("------------ masuk 5");
				    	if (search != null) {
					    	if (!search.trim().equals("")) {				    	
					    		 sql += " AND (";
					    			 sql +=" trim(UPPER(PHM.NO_HAKMILIK)) LIKE '%"+search.toUpperCase().trim()+"%' ";
					    			 sql +=" OR trim(UPPER(PHM.NO_LOT)) LIKE '%"+search.toUpperCase().trim()+"%' ";
					    			 sql +=" OR trim(UPPER(JHM.KOD_JENIS_HAKMILIK)) LIKE '%"+search.toUpperCase().trim()+"%' ";
					    			 //sql +=" OR trim(UPPER(JHM.KETERANGAN)) LIKE '%"+search.toUpperCase().trim()+"%' ";				    			 
					    			 sql +=" OR UPPER(f.no_fail) LIKE '%" + search.toUpperCase().trim() + "%' ";
					    			 sql +=" OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + search.toUpperCase().trim() + "%' ";
					    			 sql +=" OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + search.toUpperCase().trim() + "%' ";
					    			 sql +=" OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + search.toUpperCase().trim() + "%' ";
									 sql += " OR  UPPER(P.TUJUAN)  LIKE UPPER('%' ||'"+search.toUpperCase().trim()+"'|| '%')";
									 sql += " OR  UPPER(JK.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"+search.toUpperCase().trim()+"'|| '%')";
									
					    			 
					    			 sql += ")  ";
							   	 }
					     }			  
			   		}
			   		
			   		
			   		if(type.equals("pb"))
			   		{
			   			if (search != null) {
					    	if (!search.trim().equals("")) {				    	
					    		 sql += " AND (";
					    			 sql +=" trim(UPPER(PHM.NO_HAKMILIK)) LIKE '%"+search.toUpperCase().trim()+"%' ";
					    			 sql +=" OR trim(UPPER(PHM.NO_LOT)) LIKE '%"+search.toUpperCase().trim()+"%' ";
					    			 sql +=" OR trim(UPPER(JHM.KOD_JENIS_HAKMILIK)) LIKE '%"+search.toUpperCase().trim()+"%' ";				    							    			 
					    			 sql +=" OR UPPER(f.no_fail) LIKE '%" + search.toUpperCase().trim() + "%' ";
					    			 sql +=" OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + search.toUpperCase().trim() + "%' ";
					    			 sql +=" OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + search.toUpperCase().trim() + "%' ";
					    			 sql +=" OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + search.toUpperCase().trim() + "%' ";
									 sql += " OR  UPPER(P.TUJUAN)  LIKE UPPER('%' ||'"+search.toUpperCase().trim()+"'|| '%')";
									 sql += " OR  UPPER(JK.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"+search.toUpperCase().trim()+"'|| '%')";
									 sql += " OR UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"+search.trim()+"'|| '%')";
									 sql += " OR UPPER(PB.NO_PB)  LIKE ('%' ||'"+search.trim()+"'|| '%') "; 
									 sql += " OR UPPER(HPB.NO_AKAUN)  LIKE ('%' ||'"+search.trim()+"'|| '%') ";
					    			 sql += ")  ";
					    			 
					    			 
							   	 }
					     }			  
			   		}
			    	
			    	
			    	
					if(!negeriUser.equals("16") && !negeriUser.isEmpty()){
						if(negeriUser.equals("14")){
							sql += "AND F.ID_NEGERI in (14,15,16) ";
						}else{
							sql += "AND F.ID_NEGERI = '"+negeriUser+"'";
						}		
					}
				
					sql = sql + " ORDER BY " 
							+ " CASE nvl(p.flag_semak,0)"
							+ " WHEN '1' THEN 1" 
							+ " END asc,"
							+ " CASE nvl(p.id_status,0)"
							+ " WHEN 16 THEN 1"		
							+ " END asc,"
							+ " CASE nvl(mk.flag_semakan_pengarah,0)"
							+ " WHEN '1' THEN 1" 	
							+ " END asc,"
							+ " CASE nvl(FLAG_MMK_PENARIKAN,0)"
							+ " WHEN '1' THEN 1" 	
							+ " END asc,"		
						    +"" +
						    "p.tarikh_masuk DESC";	

			myLogger.debug("TYPE :"+type+"---------------------------------- FAIL TUGASAN PPT DASHBOARD :"+sql);	
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);

			int bil = 1;
			senaraiFail = Collections.synchronizedList(new ArrayList());
			Map h = null;
			
			while (rs.next()) {
				
				h = Collections.synchronizedMap(new HashMap());
				h.put("bil", bil);	
				
				if(type.equals("hakmilik"))
		   		{
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("KOD_HAKMILIK", rs.getString("KOD_HAKMILIK") == null ? "" : rs.getString("KOD_HAKMILIK"));
				h.put("JENIS_HAKMILIK", rs.getString("JENIS_HAKMILIK") == null ? "" : rs.getString("JENIS_HAKMILIK"));		    	
		   		}
				
				if(type.equals("pb"))
		   		{
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("KOD_HAKMILIK", rs.getString("KOD_HAKMILIK") == null ? "" : rs.getString("KOD_HAKMILIK"));
				h.put("JENIS_HAKMILIK", rs.getString("JENIS_HAKMILIK") == null ? "" : rs.getString("JENIS_HAKMILIK"));	
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB"));
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs.getString("NO_PB"));			
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN"));		    	
		   		}
				
				h.put("BIL_PEGAWAI_BERTUGAS_BARU", rs.getString("BIL_PEGAWAI_BERTUGAS_BARU") == null ? "" : rs.getString("BIL_PEGAWAI_BERTUGAS_BARU"));
				h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
				h.put("BIL_PEGAWAI_BERTUGAS", rs.getString("BIL_PEGAWAI_BERTUGAS") == null ? "" : rs.getString("BIL_PEGAWAI_BERTUGAS"));
				h.put("STATUS_KEPUTUSAN", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				h.put("FLAG_MMK_PENARIKAN", rs.getString("FLAG_MMK_PENARIKAN") == null ? "" : rs.getString("FLAG_MMK_PENARIKAN"));	
				h.put("FLAG_SEMAKAN_PENGARAH", rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs.getString("FLAG_SEMAKAN_PENGARAH"));	
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? "" : rs.getString("FLAG_SEMAK"));	
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));	
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));				
				h.put("FLAG_JENISPERMOHONAN", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));				
				h.put("ID_STATUS", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("NO_JKPTG", rs.getString("NO_JKPTG") == null ? "" : rs.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS", rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("USER_NAME", rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? "" : rs.getString("NEGERI_USER"));
				h.put("TUJUAN", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				
				if(rs.getString("ID_SUBURUSAN") != null){
					
					if(rs.getString("ID_SUBURUSAN").equals("51")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					}else if(rs.getString("ID_SUBURUSAN").equals("52")){
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					}else if(rs.getString("ID_SUBURUSAN").equals("53")){
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					}else{
						h.put("URUSAN", "");
					}
					
				}else{
					h.put("URUSAN", "");
				}
				
				senaraiFail.add(h);		
				bil++;
				count++;
			}
			
		} finally {
			if (rs != null) rs.close();	
			if (stmt != null) stmt.close();
			if (db != null) db.close();					
		}
		
		return senaraiFail;
		
	}



}
