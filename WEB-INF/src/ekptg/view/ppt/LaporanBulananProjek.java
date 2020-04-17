package ekptg.view.ppt;
import java.sql.ResultSet;
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
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPembatalanInternalData;

@SuppressWarnings("serial")
public class LaporanBulananProjek extends AjaxBasedModule{	
	
	static Logger myLogger = Logger.getLogger(LaporanBulananProjek.class);
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	
	List listFail = null;
	
	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();
		
		Vector dataPejabatJKPTG = new Vector();
		dataPejabatJKPTG.clear();
		List listLampiranA = null;
		String vm = ""; 
		String mainpage = "app/ppt/LAPORAN/LaporanBulananProjek.jsp";
		String userIdNegeri = (String) session.getAttribute("_ekptg_user_negeri");
		
		//default
		String jenisTempoh = "";
		String bulanSE = "";
		String tahunSE = "";
		
		//param
		String id_pejabat = "";
		String id_daerah = "";
		String bulan = lebah.util.Util.getDateTime(new Date(), "MM");
		String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
		
		String submit = getParam("command");
    	myLogger.info("submit : " + submit);
    	id_pejabat = getParam("socPejabat"); 		
		bulan = getParam("socBulan");
		tahun = getParam("socTahun"); 
		bulanSE = getParam("socBulanSehingga");
		tahunSE = getParam("socTahunSehingga"); 	
		jenisTempoh = getParam("sorJenisTempoh");			
		String tempIdPejabat = getParam("id_pejabat");		
		context.put("listLampiranA","");
    	if("doChange".equals(submit)){
    		
    		
    		if(tempIdPejabat.equals(id_pejabat)){
    			id_daerah = getParam("socDaerah");  
    		}else{
    			id_daerah = "000";
    		}

    		getAndSetDataLaporan(id_pejabat,id_daerah,bulan,tahun,bulanSE,tahunSE);
    		vm = mainpage;
    	}
    	/*
    	else if("showHtmlReport".equals(submit)){
    		long startTime = System.currentTimeMillis()/1000;
    		//listLampiranA = listREPORTHTML(session,"10",null);
    		long stopTime = System.currentTimeMillis()/1000;
			myLogger.info(" showHtmlReport :::: START : "+startTime+""+" STOP : "+stopTime+" TOTAL : "+ (stopTime- startTime));			
    		context.put("listLampiranA",listLampiranA);
    		vm = "app/ppt/LAPORAN/frmLampiranA.jsp";
    	}
    	*/
    	else{
    		
    		//default
    		id_daerah = "000";
    		jenisTempoh = "sehingga";
    		
			logic.setDataPejabatJKPTG(userIdNegeri);
	     	dataPejabatJKPTG = logic.getDataPejabatJKPTG();
	     	if(dataPejabatJKPTG.size()!=0){
	     		Hashtable dpj = (Hashtable)dataPejabatJKPTG.get(0);
	     		id_pejabat = (String)dpj.get("ID_PEJABATJKPTG");
	     	}			     		

    		context.put("selectPejabat",HTML.SelectPejabatJKPTGLaporan("socPejabat",Utils.parseLong(id_pejabat),null,"id='socPejabat' style=width:auto onChange=\"doChange()\""));
    		
    		if(id_pejabat!=""){
    			context.put("selectDaerah",HTML.SelectDaerahLaporanByIdPejabatJKPTGPPT(id_pejabat,"socDaerah",null,null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
    		}else{
    			context.put("selectDaerah",HTML.SelectDaerahLaporan("socDaerah",null,null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
    		}
    		
    		context.put("selectBulan",HTML.SelectBulan("socBulan",Utils.parseLong(bulan),null,"id='socBulan' style=width:200px onChange=\"doChange()\""));
    		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px onChange=\"doChange()\""));
    		
    		//tambahan untuk julat masa
    		context.put("selectBulanSehingga",HTML.SelectBulan("socBulanSehingga",Utils.parseLong(bulan),null,"id='socBulanSehingga' style=width:200px onChange=\"doChange()\""));
    		context.put("selectTahunSehingga",HTML.SelectTahun("socTahunSehingga",tahun,null,"id='socTahunSehingga' style=width:200px onChange=\"doChange()\""));
    		vm = mainpage;
    	}
    	
    	//listFail = ListFail(session,"fail",getParam("search"));
		//this.context.put("listFail",listFail);
    	
    	context.put("jenisTempoh", jenisTempoh);
    	context.put("id_pejabat",id_pejabat);
    	context.put("id_daerah",id_daerah);
    	
    	context.put("bulan",bulan);
    	context.put("tahun",tahun);
    	context.put("bulanSE",bulanSE);
    	context.put("tahunSE",tahunSE);
    	myLogger.info("id_pejabat : "+id_pejabat);
    	myLogger.info("id_daerah : "+id_daerah);
    	
    	
    	return vm;
     
		
	}// close doTemplate2

	
	
	private void getAndSetDataLaporan(String id_pejabat, String id_daerah, String bulan, String tahun, String bulanSE, String tahunSE) throws Exception{
		
		context.put("selectPejabat",HTML.SelectPejabatJKPTGLaporan("socPejabat",Utils.parseLong(id_pejabat),null,"id='socPejabat' style=width:auto onChange=\"doChange()\""));
		
		if(id_pejabat!=""){
			context.put("selectDaerah",HTML.SelectDaerahLaporanByIdPejabatJKPTGPPT(id_pejabat,"socDaerah",Utils.parseLong(id_daerah),null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
		}else{
			context.put("selectDaerah",HTML.SelectDaerahLaporan("socDaerah",Utils.parseLong(id_daerah),null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
		}
		
		context.put("selectBulan",HTML.SelectBulan("socBulan",Utils.parseLong(bulan),null,"id='socBulan' style=width:200px onChange=\"doChange()\""));
		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px onChange=\"doChange()\""));
		
		//tambahan untuk julat masa
		context.put("selectBulanSehingga",HTML.SelectBulan("socBulanSehingga",Utils.parseLong(bulanSE),null,"id='socBulanSehingga' style=width:200px onChange=\"doChange()\""));
		context.put("selectTahunSehingga",HTML.SelectTahun("socTahunSehingga",tahunSE,null,"id='socTahunSehingga' style=width:200px onChange=\"doChange()\""));
		
		
	}//close getAndSetDataLaporan
	

	private List ListFail(HttpSession session,String type,String search) throws Exception
	{
		listFail = carianFail(session,type,search);
		this.context.put("listFail", listFail);	
		return listFail;	
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
	/*
	@SuppressWarnings("unchecked")
	public List listREPORTHTML(HttpSession session,String ID_NEGERI, Db db)throws Exception {
		Db db1 = null;
		
		ResultSet rs = null;
		Statement stmt = null;
		List listREPORTHTML = null;
		String sql = "";
		
		try {
			if(db==null)
			{
			db1 = new Db();
			}
			else
			{
				db1 = db;
			}			
			stmt = db1.getStatement();
			
			
			String condition = "";
			if(!ID_NEGERI.equals(""))
			{
				condition += " AND F.ID_NEGERI = '"+ID_NEGERI+"' "; 
			}
			
			sql = " SELECT DISTINCT 1 AS TYPE, "+
			" TO_CHAR(F.ID_FAIL) AS ID_FAIL, NULL AS ID_HAKMILIK, "+
			" NULL AS NO_SUBJAKET, "+
			" UPPER(TO_CHAR(K.NAMA_KEMENTERIAN)) AS NAMA_KEMENTERIAN,  "+
			" TO_CHAR(PP.ID_PERMOHONAN) AS ID_PERMOHONAN, "+
			" UPPER(PP.TUJUAN) AS TUJUAN, UPPER(F.NO_FAIL) AS NO_FAIL,TO_CHAR(PP.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,  "+
			" TO_CHAR(PP.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN_DIS,  "+
			" UPPER(D.NAMA_DAERAH) AS NAMA_DAERAH, "+
			" CASE  "+
			" WHEN PP.FLAG_JENIS_PROJEK = '1' THEN 'PENGAMBILAN JAJARAN' "+
			" WHEN PP.FLAG_JENIS_PROJEK = '2' THEN 'PENGAMBILAN TAPAK' "+
			" ELSE '' "+
			" END AS JENIS_PENGAMBILAN,  "+
			" NVL(REPLACE(TO_CHAR( "+
			" SUM( "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" CASE WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1' THEN HM.LUAS_AMBIL * 1 ELSE HM.LUAS_AMBIL / 10000 END "+
			" END "+
			" ),'999,990.9999'),' '),0) AS LUAS_SELURUH, "+
			" NVL(REPLACE(TO_CHAR( "+
			" SUM( "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" CASE WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1' AND HM.FLAG_JENIS_RIZAB = '1' THEN HM.LUAS_AMBIL * 1 ELSE HM.LUAS_AMBIL / 10000 END "+
			" END "+
			" ),'999,990.9999'),' '),0) AS LUAS_SELURUH_RIZAB, "+
			" NVL(COUNT(DISTINCT HM.ID_HAKMILIK),0) AS JUM_LOT, "+
			" NVL(COUNT(DISTINCT  "+
			" CASE WHEN HBK.ID_HAKMILIKBORANGK IS NOT NULL AND NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" HM.ID_HAKMILIK "+
			" END "+
			" ),0) AS JUM_LOT_BORANGK, "+
			" NVL(REPLACE(TO_CHAR( "+
			" SUM( "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" CASE WHEN HMP.ID_HAKMILIKPB IS NOT NULL AND HMP.ID_JENISPB NOT IN (27,40,41,42) THEN AW.BAYAR_PAMPASAN ELSE 0 END "+
			" END),'999,999,990.99'),' '),0) AS JUM_PAMPASAN, "+
			" NVL(REPLACE(TO_CHAR(SUM( "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" CASE WHEN HMP.ID_HAKMILIKPB IS NOT NULL AND HMP.ID_JENISPB NOT IN (27,40,41,42) THEN AW.DENDA_LEWAT ELSE 0 END "+
			" END),'999,999,990.99'),' '),0) AS JUM_PAMPASAN_LEWAT, "+
			" NVL(COUNT(DISTINCT CASE WHEN HMP_PM.ID_HAKMILIKPB IS NOT NULL AND HMP_PM.ID_JENISPB IN (1) THEN HMP_PM.ID_HAKMILIKPB END),0) AS JUM_PEMILIK, "+
			" MAX(TO_CHAR(MMK.TARIKH_MMK,'DD.MM.YYYY')) AS TARIKH_MMK, MAX(TO_CHAR(MMK.TARIKH_HANTAR,'DD.MM.YYYY')) AS TARIKH_HANTAR, "+
			" MAX(TO_CHAR(WARTA.TARIKH_WARTA,'DD.MM.YYYY')) AS TARIKH_WARTA,MAX(TO_CHAR(MMK_K.TARIKH_TERIMA,'DD.MM.YYYY')) AS TARIKH_MMK_KEPUTUSAN, "+
			" NULL AS TEMPOH_BH_BULAN, "+
			" TO_CHAR(ROUND(MONTHS_BETWEEN(MAX(BH.TARIKH_BORANGH),PP.TARIKH_PERMOHONAN))) AS TEMPOH_BH_BULAN_INDIVIDU, "+
			" (CASE WHEN "+
			" NVL(COUNT(DISTINCT  "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" HM.ID_HAKMILIK "+
			" END "+
			" ),0) = "+
			" NVL(COUNT(DISTINCT  "+
			" CASE WHEN HBK.ID_HAKMILIKBORANGK IS NOT NULL AND NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" HM.ID_HAKMILIK "+
			" END "+
			" ),0) "+
			" AND  "+
			" NVL(COUNT(DISTINCT  "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') != 'Y' AND NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_BALIK,' ') != 'Y' AND NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y' THEN "+
			" HM.ID_HAKMILIK "+
			" END "+
			" ),0) > 0 THEN 'SELESAI'  "+
			" ELSE  "+
			" CASE WHEN "+
			" NVL(COUNT(DISTINCT  "+
			" CASE WHEN NVL(HM.FLAG_PEMBATALAN,' ') = 'Y' OR NVL(HM.FLAG_PEMBATALAN_KESELURUHAN,' ') = 'Y' OR NVL(HM.FLAG_PENARIKAN_BALIK,' ') = 'Y' OR NVL(HM.FLAG_PENARIKAN_KESELURUHAN,' ') = 'Y' THEN "+
			" HM.ID_HAKMILIK "+
			" END "+
			" ),0) "+
			" = "+
			" NVL(COUNT(DISTINCT HM.ID_HAKMILIK),0) "+
			" AND  "+
			" NVL(COUNT(DISTINCT HM.ID_HAKMILIK),0)>0 "+
			" THEN 'BATAL/TARIK BALIK KESELURUHAN' ELSE '' END "+
			" END) AS STATUS_PERMOHONAN, "+
			" NULL AS STATUS_LOT, "+
			" MAX(TO_CHAR(BI.TARIKH_BORANGI,'DD.MM.YYYY')) AS TARIKH_BORANGI, S.KETERANGAN AS STATUS, "+
			" TO_CHAR(SYSDATE,'DD') AS HARI_SYSDATE, "+
			" CASE  "+
			" WHEN TO_CHAR(SYSDATE,'MM')='01' THEN 'Januari' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='02' THEN 'Februari'  "+
			" WHEN TO_CHAR(SYSDATE,'MM')='03' THEN 'Mac' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='04' THEN 'April' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='05' THEN 'Mei' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='06' THEN 'Jun' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='07' THEN 'Julai' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='08' THEN 'Ogos' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='09' THEN 'September' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='10' THEN 'Oktober' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='11' THEN 'November' "+
			" WHEN TO_CHAR(SYSDATE,'MM')='12' THEN 'Disember' "+
			" END AS BULAN_SYSDATE, TO_CHAR(SYSDATE,'yyyy') AS TAHUN_SYSDATE, NULL AS TARIKH_SIASATAN, "+
			" NULL AS NO_LOTPT, NULL AS NAMA_MUKIM, NULL AS TEMPOH_SIASATAN_BULAN, NULL AS NO_HAKMILIK, NULL AS TARIKH_BORANGK, "+
			" NULL AS TARIKH_BORANGE, NULL AS TARIKH_BORANGG,NULL AS TARIKH_BORANGH,NULL AS TARIKH_BORANGH_INDIVIDU, NULL AS TARIKH_BAYARAN," +
			" NULL AS STATUS_AMBIL, NULL AS CATATAN_HM, NULL AS KOD_MUKIM "+
			" FROM TBLPPTPERMOHONAN PP,TBLRUJSTATUS S, "+
			" TBLPFDFAIL F, TBLRUJNEGERI NEG,TBLRUJDAERAH D,TBLRUJKEMENTERIAN K, "+
			" TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HMP,TBLPPTAWARD AW,TBLPPTHAKMILIKPB HMP_PM, "+
			" TBLPPTMMK MMK,TBLPPTWARTA WARTA,TBLPPTMMKKEPUTUSAN MMK_K,TBLPPTBORANGH BH, "+
			" TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGI BI "+
			" WHERE F.ID_FAIL = PP.ID_FAIL "+
			" AND F.ID_KEMENTERIAN = K.ID_KEMENTERIAN "+
			" AND PP.ID_STATUS(+) = S.ID_STATUS "+
			" AND F.ID_SUBURUSAN = '52' "+
			" AND TO_CHAR(PP.TARIKH_PERMOHONAN,'YYYY') IS NOT NULL "+
			" AND PP.ID_PERMOHONAN = HM.ID_PERMOHONAN(+) "+
			" AND PP.ID_PERMOHONAN = BI.ID_PERMOHONAN(+) "+
			" AND PP.ID_DAERAH = D.ID_DAERAH "+
			" AND F.ID_NEGERI = NEG.ID_NEGERI "+
			" AND HMP.ID_HAKMILIKPB=AW.ID_HAKMILIKPB(+)  "+
			" AND HM.ID_HAKMILIK = HMP.ID_HAKMILIK(+) "+
			" AND HM.ID_HAKMILIK = HMP_PM.ID_HAKMILIK(+) "+
			" AND PP.ID_PERMOHONAN = MMK.ID_PERMOHONAN(+) "+
			" AND PP.ID_PERMOHONAN = WARTA.ID_PERMOHONAN(+) "+
			" AND MMK.ID_MMK = MMK_K.ID_MMK(+) "+
			" AND HMP.ID_HAKMILIKPB = BH.ID_HAKMILIKPB(+) "+
			" AND HM.ID_HAKMILIK = HBK.ID_HAKMILIK(+) ";
			sql += condition;
			sql += " GROUP BY F.ID_FAIL,K.NAMA_KEMENTERIAN,PP.ID_PERMOHONAN,PP.TUJUAN,F.NO_FAIL,PP.TARIKH_PERMOHONAN,D.NAMA_DAERAH,PP.FLAG_JENIS_PROJEK,S.KETERANGAN "+
			" UNION ALL "+
			" SELECT DISTINCT 2 AS TYPE, TO_CHAR (F.ID_FAIL) AS ID_FAIL,
			" HM.ID_HAKMILIK AS ID_HAKMILIK,
			" COALESCE(TO_NUMBER(REGEXP_SUBSTR (REPLACE (REPLACE (HM.NO_SUBJAKET,'SJ-',''),' ',''),'^(-|+)?\d+(\.|,)?(\d+)?$')),0) AS NO_SUBJAKET,
			" NULL AS NAMA_KEMENTERIAN, NULL AS ID_PERMOHONAN, NULL AS TUJUAN, NULL AS NO_FAIL,
			" NULL AS TARIKH_PERMOHONAN,NULL AS TARIKH_PERMOHONAN_DIS,
			" NULL AS NAMA_DAERAH,NULL AS JENIS_PENGAMBILAN,
			" NVL(REPLACE(TO_CHAR(CASE  WHEN NVL (HM.FLAG_PEMBATALAN, ' ') != 'Y' AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN, ' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_BALIK, ' ') != 'Y' AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN, ' ') != 'Y'
			" THEN CASE WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1' THEN HM.LUAS_AMBIL * 1
			" ELSE HM.LUAS_AMBIL / 10000
			" END
			" END,'999,990.9999'),' '),0) AS LUAS_SELURUH,
			" NVL(REPLACE(TO_CHAR(CASE WHEN NVL (HM.FLAG_PEMBATALAN, ' ') != 'Y' AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN, ' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_BALIK, ' ') != 'Y' AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN, ' ') != 'Y'
			" THEN CASE WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1' AND HM.FLAG_JENIS_RIZAB = '1' THEN HM.LUAS_AMBIL * 1
			" ELSE HM.LUAS_AMBIL / 10000
			" END
			" END,'999,990.9999'),' '),0) AS LUAS_SELURUH_RIZAB,
			" NULL AS JUM_LOT, NULL AS JUM_LOT_BORANGK,
			" NVL(REPLACE(TO_CHAR(SUM(CASE
			" WHEN NVL (HM.FLAG_PEMBATALAN, ' ') != 'Y'
			" AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_BALIK, ' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y'
			" THEN CASE
			" WHEN HMP.ID_HAKMILIKPB IS NOT NULL
			" AND HMP.ID_JENISPB NOT IN (27, 40, 41, 42)
			" THEN AW.BAYAR_PAMPASAN
			" ELSE 0 END
			" END),'999,999,990.99'),' '),0) AS JUM_PAMPASAN,
			" NVL(REPLACE(TO_CHAR(SUM(CASE WHEN NVL (HM.FLAG_PEMBATALAN, ' ') != 'Y' AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN,' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_BALIK, ' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN,' ') != 'Y'
			" THEN CASE
			" WHEN HMP.ID_HAKMILIKPB IS NOT NULL
			" AND HMP.ID_JENISPB NOT IN (27, 40, 41, 42)
			" THEN AW.DENDA_LEWAT
			" ELSE 0 END
			" END),'999,999,990.99'),' '),0) AS JUM_PAMPASAN_LEWAT,
			" NVL (COUNT (DISTINCT CASE WHEN HMP_PM.ID_HAKMILIKPB IS NOT NULL
			" AND HMP_PM.ID_JENISPB IN (1) THEN HMP_PM.ID_HAKMILIKPB
			" END),0) AS JUM_PEMILIK,
			" NULL AS TARIKH_MMK, NULL AS TARIKH_HANTAR,
			" NULL AS TARIKH_WARTA, NULL AS TARIKH_MMK_KEPUTUSAN,
			" TO_CHAR(ROUND (MONTHS_BETWEEN (MAX (BG.TARIKH_BORANGH),PP.TARIKH_PERMOHONAN))) AS TEMPOH_BH_BULAN,
			" NULL AS TEMPOH_BH_BULAN_INDIVIDU, NULL AS STATUS_PERMOHONAN,
			" (CASE
			" WHEN HBK.ID_HAKMILIKBORANGK IS NOT NULL
			" AND NVL (HM.FLAG_PEMBATALAN, ' ') != 'Y'
			" AND NVL (HM.FLAG_PEMBATALAN_KESELURUHAN, ' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_BALIK, ' ') != 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN, ' ') != 'Y'
			" THEN 'SELESAI'
			" ELSE CASE
			" WHEN NVL (HM.FLAG_PEMBATALAN, ' ') = 'Y'
			" OR NVL (HM.FLAG_PEMBATALAN_KESELURUHAN, ' ') = 'Y'
			" THEN 'BATAL'
			" WHEN NVL (HM.FLAG_PENARIKAN_BALIK, ' ') = 'Y'
			" AND NVL (HM.FLAG_PENARIKAN_KESELURUHAN, ' ') = 'Y'
			" THEN 'TARIK BALIK'
			" ELSE '' END END) AS STATUS_LOT,
			" NULL AS TARIKH_BORANGI, NULL AS STATUS, NULL AS HARI_SYSDATE,
			" NULL AS BULAN_SYSDATE, NULL AS TAHUN_SYSDATE,
			" TO_CHAR(MAX(TO_DATE(CASE WHEN SS.ID_PERMOHONAN IS NOT NULL
			" THEN SS.TARIKH_SIASATAN
			" ELSE NULL END)),'DD.MM.YYYY') AS TARIKH_SIASATAN,
			" CASE WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN LT.KETERANGAN || HM.NO_PT
			" WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN LT.KETERANGAN || HM.NO_PT
			" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN LT.KETERANGAN
			" || HM.NO_PT || CHR (32) || CHR (40) || HM.NO_LOT || CHR (41)
			" ELSE '' END AS NO_LOTPT,
			" UPPER (M.NAMA_MUKIM) AS NAMA_MUKIM,
			" TO_CHAR(ROUND (MONTHS_BETWEEN (NVL (HM.TARIKH_STOP_SIASATAN,SYSDATE),PP.TARIKH_PERMOHONAN))) AS TEMPOH_SIASATAN_BULAN,
			" (CASE WHEN HM.ID_JENISHAKMILIK IS NOT NULL AND HM.ID_JENISHAKMILIK NOT IN (0, 333)
			" THEN UPPER (JHM.KOD_JENIS_HAKMILIK) ELSE '' END || HM.NO_HAKMILIK) AS NO_HAKMILIK,
			" TO_CHAR (MAX (BK.TARIKH_BORANGK),'DD.MM.YYYY') AS TARIKH_BORANGK,
			" TO_CHAR (MAX (BE.TARIKH_BORANGE),'DD.MM.YYYY') AS TARIKH_BORANGE,
			" TO_CHAR (MAX (BG.TARIKH_BORANGG),'DD.MM.YYYY') AS TARIKH_BORANGG,
			" TO_CHAR (MAX (BG.TARIKH_BORANGH),'DD.MM.YYYY') AS TARIKH_BORANGH,
			" TO_CHAR (MAX (BH.TARIKH_BORANGH),'DD.MM.YYYY') AS TARIKH_BORANGH_INDIVIDU,
			" TO_CHAR(MAX (CASE WHEN BAY.CARA_BAYAR = '1' THEN BAY.TARIKH_SERAH_CEK
			" WHEN BAY.CARA_BAYAR = '2' THEN BAY.TARIKH_SURAT_EFT
			" ELSE NULL END),'DD.MM.YYYY') AS TARIKH_BAYARAN,
			" (CASE
			" WHEN (NVL
			" (CASE
			" WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1'
			" THEN HM.LUAS_AMBIL * 1
			" ELSE HM.LUAS_AMBIL / 10000
			" END,
			" 0
			" ) <
			" NVL
			"(CASE
			" WHEN HM.ID_UNITLUASLOT_CONVERT = '1'
			" THEN HM.LUAS_LOT * 1
			" ELSE HM.LUAS_LOT / 10000
			" END,
			" 0
			" )
			" )
			" AND NVL
			" (CASE
			" WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1'
			" THEN HM.LUAS_AMBIL * 1
			" ELSE HM.LUAS_AMBIL / 10000
			" END,
			" 0
			" ) > 0
			" AND NVL
			" (CASE
			" WHEN HM.ID_UNITLUASLOT_CONVERT = '1'
			" THEN HM.LUAS_LOT * 1
			" ELSE HM.LUAS_LOT / 10000
			" END,
			" 0
			" ) > 0
			" THEN ' (AMBIL SBHG  - QT)'
			" WHEN (NVL
			" (CASE
			" WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1'
			" THEN HM.LUAS_AMBIL * 1
			" ELSE HM.LUAS_AMBIL / 10000
			" END,
			" 0
			" ) =
			" NVL
			" (CASE
			" WHEN HM.ID_UNITLUASLOT_CONVERT = '1'
			" THEN HM.LUAS_LOT * 1
			" ELSE HM.LUAS_LOT / 10000
			" END,
			" 0
			" )
			" )
			" AND NVL
                           (CASE
                               WHEN HM.ID_UNITLUASAMBIL_CONVERT = '1'
                                  THEN HM.LUAS_AMBIL * 1
                               ELSE HM.LUAS_AMBIL / 10000
                            END,
                            0
                           ) > 0
                    AND NVL
                           (CASE
                               WHEN HM.ID_UNITLUASLOT_CONVERT = '1'
                                  THEN HM.LUAS_LOT * 1
                               ELSE HM.LUAS_LOT / 10000
                            END,
                            0
                           ) > 0
                       THEN ' (KESEMUA - FT)'
                    ELSE ''
                 END
                ) AS STATUS_AMBIL,
                UPPER (HM.CATATAN) AS CATATAN_HM, NULL AS KOD_MUKIM
           FROM TBLPPTPERMOHONAN PP,TBLPFDFAIL F,
                TBLPPTHAKMILIK HM,TBLPPTHAKMILIKPB HMP,TBLPPTAWARD AW,
                TBLPPTHAKMILIKPB HMP_PM,TBLPPTBORANGH BH,TBLPPTHAKMILIKBORANGK HBK,
                TBLPPTSIASATAN SS,TBLRUJLOT LT,TBLRUJMUKIM M,
                TBLRUJJENISHAKMILIK JHM,TBLPPTBORANGK BK,
                TBLPPTBORANGE BE,TBLPPTBORANGG BG,TBLPPTBAYARAN BAY
          WHERE F.ID_FAIL = PP.ID_FAIL AND F.ID_SUBURUSAN = '52'
            AND TO_CHAR (PP.TARIKH_PERMOHONAN, 'YYYY') IS NOT NULL
            AND PP.ID_PERMOHONAN = HM.ID_PERMOHONAN
            AND HM.ID_MUKIM = M.ID_MUKIM AND HMP.ID_HAKMILIKPB = AW.ID_HAKMILIKPB(+)
            AND HMP.ID_HAKMILIKPB = BAY.ID_HAKMILIKPB(+) AND HM.ID_HAKMILIK = HMP.ID_HAKMILIK(+)
            AND HM.ID_HAKMILIK = BE.ID_HAKMILIK(+) AND HM.ID_HAKMILIK = SS.ID_HAKMILIK(+)
            AND HM.ID_HAKMILIK = HMP_PM.ID_HAKMILIK(+) AND HMP.ID_HAKMILIKPB = BH.ID_HAKMILIKPB(+)
            AND HM.ID_HAKMILIK = HBK.ID_HAKMILIK(+) AND HBK.ID_BORANGK = BK.ID_BORANGK(+)
            AND HM.ID_LOT = LT.ID_LOT(+) AND HM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+)
            AND SS.ID_SIASATAN = BG.ID_SIASATAN(+)";
			sql += condition;
			sql += " GROUP BY F.ID_FAIL,HM.ID_HAKMILIK,K.NAMA_KEMENTERIAN,PP.ID_PERMOHONAN,HM.FLAG_PEMBATALAN,HM.FLAG_PEMBATALAN_KESELURUHAN, "+
			" HM.FLAG_PENARIKAN_BALIK,HM.FLAG_PENARIKAN_KESELURUHAN,HBK.ID_HAKMILIKBORANGK, "+
			" PP.TUJUAN,F.NO_FAIL,PP.TARIKH_PERMOHONAN,D.NAMA_DAERAH,PP.FLAG_JENIS_PROJEK,S.KETERANGAN,HM.NO_PT,HM.NO_LOT,LT.KETERANGAN, "+
			" HM.ID_UNITLUASAMBIL_CONVERT,HM.LUAS_AMBIL,HM.FLAG_JENIS_RIZAB,HM.NO_SUBJAKET," +
			"HM.TARIKH_STOP_SIASATAN, M.NAMA_MUKIM, "+
			" HM.ID_JENISHAKMILIK,JHM.KOD_JENIS_HAKMILIK,HM.NO_HAKMILIK,HM.ID_UNITLUASLOT_CONVERT, HM.LUAS_LOT, HM.CATATAN,M.KOD_MUKIM "+
			" ORDER BY TARIKH_PERMOHONAN DESC,ID_FAIL ASC,TYPE ASC,NO_SUBJAKET ASC,KOD_MUKIM ASC,TARIKH_SIASATAN DESC ";

				
			myLogger.info("SQL listREPORTHTML :"+ sql);
			rs = stmt.executeQuery(sql);
			listREPORTHTML = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("TYPE",rs.getString("TYPE") == null ? "" : rs.getString("TYPE").toUpperCase());
				h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL").toUpperCase());
				h.put("ID_HAKMILIK",rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("NO_SUBJAKET",rs.getString("NO_SUBJAKET") == null ? "" : rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("NAMA_KEMENTERIAN",rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("TUJUAN",rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN").toUpperCase());
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("TARIKH_PERMOHONAN",rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("TARIKH_PERMOHONAN_DIS",rs.getString("TARIKH_PERMOHONAN_DIS") == null ? "" : rs.getString("TARIKH_PERMOHONAN_DIS").toUpperCase());
				h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("JENIS_PENGAMBILAN",rs.getString("JENIS_PENGAMBILAN") == null ? "" : rs.getString("JENIS_PENGAMBILAN").toUpperCase());
				h.put("LUAS_SELURUH",rs.getString("LUAS_SELURUH") == null ? "" : rs.getString("LUAS_SELURUH").toUpperCase());
				h.put("LUAS_SELURUH_RIZAB",rs.getString("LUAS_SELURUH_RIZAB") == null ? "" : rs.getString("LUAS_SELURUH_RIZAB").toUpperCase());
				h.put("JUM_LOT",rs.getString("JUM_LOT") == null ? "" : rs.getString("JUM_LOT").toUpperCase());
				h.put("JUM_LOT_BORANGK",rs.getString("JUM_LOT_BORANGK") == null ? "" : rs.getString("JUM_LOT_BORANGK").toUpperCase());
				h.put("JUM_PAMPASAN",rs.getString("JUM_PAMPASAN") == null ? "" : rs.getString("JUM_PAMPASAN").toUpperCase());
				h.put("JUM_PAMPASAN_LEWAT",rs.getString("JUM_PAMPASAN_LEWAT") == null ? "" : rs.getString("JUM_PAMPASAN_LEWAT").toUpperCase());
				h.put("JUM_PEMILIK",rs.getString("JUM_PEMILIK") == null ? "" : rs.getString("JUM_PEMILIK").toUpperCase());
				h.put("TARIKH_MMK",rs.getString("TARIKH_MMK") == null ? "" : rs.getString("TARIKH_MMK").toUpperCase());
				h.put("TARIKH_HANTAR",rs.getString("TARIKH_HANTAR") == null ? "" : rs.getString("TARIKH_HANTAR").toUpperCase());
				h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA").toUpperCase());
				h.put("TARIKH_MMK_KEPUTUSAN",rs.getString("TARIKH_MMK_KEPUTUSAN") == null ? "" : rs.getString("TARIKH_MMK_KEPUTUSAN").toUpperCase());
				h.put("TEMPOH_BH_BULAN",rs.getString("TEMPOH_BH_BULAN") == null ? "" : rs.getString("TEMPOH_BH_BULAN").toUpperCase());
				h.put("TEMPOH_BH_BULAN_INDIVIDU",rs.getString("TEMPOH_BH_BULAN_INDIVIDU") == null ? "" : rs.getString("TEMPOH_BH_BULAN_INDIVIDU").toUpperCase());
				h.put("STATUS_PERMOHONAN",rs.getString("STATUS_PERMOHONAN") == null ? "" : rs.getString("STATUS_PERMOHONAN").toUpperCase());
				h.put("STATUS_LOT",rs.getString("STATUS_LOT") == null ? "" : rs.getString("STATUS_LOT").toUpperCase());
				h.put("TARIKH_BORANGI",rs.getString("TARIKH_BORANGI") == null ? "" : rs.getString("TARIKH_BORANGI").toUpperCase());
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS").toUpperCase());
				h.put("HARI_SYSDATE",rs.getString("HARI_SYSDATE") == null ? "" : rs.getString("HARI_SYSDATE").toUpperCase());
				h.put("BULAN_SYSDATE",rs.getString("BULAN_SYSDATE") == null ? "" : rs.getString("BULAN_SYSDATE").toUpperCase());
				h.put("TAHUN_SYSDATE",rs.getString("TAHUN_SYSDATE") == null ? "" : rs.getString("TAHUN_SYSDATE").toUpperCase());
				h.put("TARIKH_SIASATAN",rs.getString("TARIKH_SIASATAN") == null ? "" : rs.getString("TARIKH_SIASATAN").toUpperCase());
				h.put("NO_LOTPT",rs.getString("NO_LOTPT") == null ? "" : rs.getString("NO_LOTPT").toUpperCase());
				h.put("NAMA_MUKIM",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("TEMPOH_SIASATAN_BULAN",rs.getString("TEMPOH_SIASATAN_BULAN") == null ? "" : rs.getString("TEMPOH_SIASATAN_BULAN").toUpperCase());
				h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("TARIKH_BORANGK",rs.getString("TARIKH_BORANGK") == null ? "" : rs.getString("TARIKH_BORANGK").toUpperCase());
				h.put("TARIKH_BORANGE",rs.getString("TARIKH_BORANGE") == null ? "" : rs.getString("TARIKH_BORANGE").toUpperCase());
				h.put("TARIKH_BORANGG",rs.getString("TARIKH_BORANGG") == null ? "" : rs.getString("TARIKH_BORANGG").toUpperCase());
				h.put("TARIKH_BORANGH",rs.getString("TARIKH_BORANGH") == null ? "" : rs.getString("TARIKH_BORANGH").toUpperCase());
				h.put("TARIKH_BORANGH_INDIVIDU",rs.getString("TARIKH_BORANGH_INDIVIDU") == null ? "" : rs.getString("TARIKH_BORANGH_INDIVIDU").toUpperCase());
				h.put("TARIKH_BAYARAN",rs.getString("TARIKH_BAYARAN") == null ? "" : rs.getString("TARIKH_BAYARAN").toUpperCase());				
				h.put("STATUS_AMBIL",rs.getString("STATUS_AMBIL") == null ? "" : rs.getString("STATUS_AMBIL").toUpperCase());
				h.put("CATATAN_HM",rs.getString("CATATAN_HM") == null ? "" : rs.getString("CATATAN_HM").toUpperCase());
				h.put("KOD_MUKIM",rs.getString("KOD_MUKIM") == null ? "" : rs.getString("KOD_MUKIM").toUpperCase());
				listREPORTHTML.add(h);
			}
			
			
			
			
		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}
		return listREPORTHTML;
	}
	*/
	
}// close class
