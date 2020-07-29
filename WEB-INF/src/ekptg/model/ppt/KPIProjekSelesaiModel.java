package ekptg.model.ppt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.model.RazTemplete;

public class KPIProjekSelesaiModel  extends VTemplate{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//extends razTemplete
	public RazTemplete RT = new RazTemplete();

	public Logger myLogger = Logger.getLogger(ekptg.model.ppt.KPIProjekSelesaiModel.class);
	public HttpSession session;
	public String modul = "";
	
	//setting environtment untuk kegunaan controller, 
	//betujuan untuk kita mantain environment setting
	public void setKPIProjekSelesaiModel(VelocityEngine engine, VelocityContext context, HttpServletRequest request, HttpServletResponse response,HttpSession session,String modul)
    {
        this.engine = engine;
        this.context = context;
        this.request = request;
        this.response = response;
        this.session = session;
        this.modul = modul;
        //kena call main environment dalam RazTempelete, untuk memastikan clases inherite each others
        RT.setEnvironmentRT(this.engine, this.context, this.request, this.response, this.session, this.modul);	
    }
	
	public String queryMinMaxDate(Db db,String skrinName) throws Exception
	{
		String sql = "SELECT TO_CHAR(MIN(CASE WHEN MAX_TARIKH_BORANGH IS NOT NULL THEN TO_DATE(TARIKH_WARTA,'DD/MM/YYYY') END),'DD/MM/YYYY') AS MIN_TARIKH_WARTA, " +
				" TO_CHAR(MAX(TO_DATE(MAX_TARIKH_BORANGH,'DD/MM/YYYY')),'DD/MM/YYYY') AS MAX_TARIKH_BORANGH, "+
				" TO_CHAR(MIN(CASE WHEN MAX_TARIKH_BORANGK IS NOT NULL THEN TO_DATE(MIN_TARIKH_BORANGH,'DD/MM/YYYY') END),'DD/MM/YYYY') AS MIN_TARIKH_BORANGH, "+
				" TO_CHAR(MAX(TO_DATE(MAX_TARIKH_BORANGK,'DD/MM/YYYY')),'DD/MM/YYYY') AS MAX_TARIKH_BORANGK, "+
				" TO_CHAR(MIN(CASE WHEN MAX_TARIKH_ENDOSK IS NOT NULL THEN TO_DATE(MIN_TARIKH_BORANGK,'DD/MM/YYYY')END),'DD/MM/YYYY') AS MIN_TARIKH_BORANGK, "+
				" TO_CHAR(MAX(TO_DATE(MAX_TARIKH_ENDOSK,'DD/MM/YYYY')),'DD/MM/YYYY') AS MAX_TARIKH_ENDOSK, "+	
				" ROUND(MONTHS_BETWEEN (MAX(TO_DATE(MAX_TARIKH_BORANGH,'DD/MM/YYYY')),MIN(TO_DATE(TARIKH_WARTA,'DD/MM/YYYY')))) AS MAX_TEMPOH_WARTA_H, "+
				" ROUND(MONTHS_BETWEEN (MAX(TO_DATE(MAX_TARIKH_BORANGK,'DD/MM/YYYY')),MIN(TO_DATE(MIN_TARIKH_BORANGH,'DD/MM/YYYY')))) AS MAX_TEMPOH_H_K, "+
				" ROUND(MONTHS_BETWEEN (MAX(TO_DATE(MAX_TARIKH_ENDOSK,'DD/MM/YYYY')),MIN(TO_DATE(MIN_TARIKH_BORANGK,'DD/MM/YYYY')))) AS MAX_TEMPOH_K_ENDOS "+
				" FROM ("+queryProjekBased(db,skrinName)+")";
		
		myLogger.info("SQL queryMinMaxDate : "+sql);
		
		return sql;
	}
	
	public String queryHakmilikBased(Db db,String skrinName,String ID_FAIL) throws Exception
	{
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		String CARIAN_NO_FAIL = getParam(skrinName+"CARIAN_NO_FAIL");
		String CARIAN_JENIS_KPI = getParam("carianUtamaCARIAN_JENIS_KPI");
		String WCCARIAN_NO_FAIL = getParam("WC"+skrinName+"CARIAN_NO_FAIL");
		
		String CARIAN_PROJEK = getParam(skrinName+"CARIAN_PROJEK");
		myLogger.info("NAMA FIELD : "+skrinName+"CARIAN_PROJEK"+" >>>>>>>> VALUE : "+CARIAN_PROJEK);
		String WCCARIAN_PROJEK = getParam("WC"+skrinName+"CARIAN_PROJEK");
		
		String CARIAN_TAHUN_MULA = getParam(skrinName+"CARIAN_TAHUN_MULA");
		String CARIAN_TAHUN_AKHIR = getParam(skrinName+"CARIAN_TAHUN_AKHIR");
		
		String CARIAN_NEGERI = getParam(skrinName+"CARIAN_NEGERI");
		
		String sql = " SELECT F.NO_FAIL, ";
		sql += " CASE WHEN JHM.KOD_JENIS_HAKMILIK IS NULL OR JHM.KOD_JENIS_HAKMILIK = '0' OR JHM.KOD_JENIS_HAKMILIK = '00' THEN '' ELSE "+
				" JHM.KOD_JENIS_HAKMILIK "+
				" END|| CASE WHEN H.NO_HAKMILIK IS NULL OR H.NO_HAKMILIK = '0' THEN '' ELSE H.NO_HAKMILIK "+
				" END AS NO_HAKMILIK, H.NO_PT, H.NO_LOT, ";
				sql += " TO_NUMBER(NVL(H.NO_SUBJAKET,0)) AS NO_SUBJAKET, " +
				" CASE WHEN P.FLAG_JENIS_PROJEK = '1' OR P.FLAG_JENIS_PROJEK IS NULL "+
				" THEN 'JAJARAN' "+
				" WHEN P.FLAG_JENIS_PROJEK = '2' "+
				" THEN 'TAPAK' ELSE 'JAJARAN' END AS JENIS_PROJEK, "+
				" CASE WHEN HHH.TARIKH_BORANGH IS NOT NULL THEN 1 ELSE 0 END ADA_H, " +
				" CASE WHEN KKK.TARIKH_BORANGK IS NOT NULL THEN 1 ELSE 0 END ADA_K, " +
				" CASE WHEN KKK.TARIKH_BORANGK IS NOT NULL THEN 1 ELSE 0 END ADA_ENDOSK, " +
				" F.ID_FAIL, F.ID_NEGERI, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_UPT, P.TUJUAN, P.FLAG_JENIS_PROJEK, " +
				" P.TARIKH_PERMOHONAN, F.ID_KEMENTERIAN, P.ID_AGENSI, H.ID_HAKMILIK, P.ID_PERMOHONAN, "+
				" TO_CHAR(WWW.TARIKH_WARTA,'DD/MM/YYYY') AS TARIKH_WARTA, " +
				" TO_CHAR(HHH.TARIKH_BORANGH,'DD/MM/YYYY') AS TARIKH_BORANGH, " +
				" TO_CHAR(KKK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, " +
				" TO_CHAR(EEE.TARIKH_ENDOSK,'DD/MM/YYYY') AS TARIKH_ENDOSK, "+
				" ABS(TO_DATE(WWW.TARIKH_WARTA, 'DD/MM/YYYY') - TO_DATE(HHH.TARIKH_BORANGH, 'DD/MM/YYYY')) AS DAYS_WARTA_H, "+
				" ABS(TO_DATE(HHH.TARIKH_BORANGH, 'DD/MM/YYYY') - TO_DATE(KKK.TARIKH_BORANGK, 'DD/MM/YYYY')) AS DAYS_H_K,  "+
				" ABS(TO_DATE(KKK.TARIKH_BORANGK, 'DD/MM/YYYY') - TO_DATE(EEE.TARIKH_ENDOSK, 'DD/MM/YYYY')) AS DAYS_K_ENDOS,  "+
				" ROUND(MONTHS_BETWEEN (HHH.TARIKH_BORANGH,WWW.TARIKH_WARTA)) AS TEMPOH_WARTA_H, "+
				" (TRUNC(TO_DATE(KKK.TARIKH_BORANGK, 'DD/MM/YYYY'),'d')-TRUNC(TO_DATE(HHH.TARIKH_BORANGH,'DD/MM/YYYY'),'d'))/7 AS TEMPOH_H_K, "+
				" (TRUNC(TO_DATE(EEE.TARIKH_ENDOSK, 'DD/MM/YYYY'),'d')-TRUNC(TO_DATE(KKK.TARIKH_BORANGK,'DD/MM/YYYY'),'d'))/7 AS TEMPOH_K_ENDOS "+
				" FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLPPTHAKMILIK H, TBLRUJJENISHAKMILIK JHM, "+
				" (SELECT P.ID_PERMOHONAN, MIN(W.TARIKH_WARTA) AS TARIKH_WARTA "+
				" FROM  TBLPPTPERMOHONAN P, TBLPPTWARTA W "+
				" WHERE P.ID_PERMOHONAN = W.ID_PERMOHONAN "+
				" GROUP BY P.ID_PERMOHONAN) WWW, "+
				" (SELECT H.ID_PERMOHONAN, H.ID_HAKMILIK, MAX(BG.TARIKH_BORANGH) AS TARIKH_BORANGH "+
				" FROM TBLPPTSIASATAN S, TBLPPTBORANGG BG, TBLPPTHAKMILIK H, TBLPPTPERMOHONAN P "+
				" WHERE S.ID_SIASATAN = BG.ID_SIASATAN AND S.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "+
				" AND BG.TARIKH_BORANGH IS NOT NULL "+
				" GROUP BY H.ID_PERMOHONAN, H.ID_HAKMILIK) HHH, "+
				" (SELECT HM.ID_PERMOHONAN, HM.ID_HAKMILIK, MAX(BK.TARIKH_BORANGK) AS TARIKH_BORANGK FROM "+
				" TBLPPTHAKMILIKBORANGK HBK, TBLPPTHAKMILIK HM, TBLPPTBORANGK BK "+
				" WHERE HM.ID_HAKMILIK = HBK.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK "+
				" AND BK.TARIKH_BORANGK IS NOT NULL "+
				" GROUP BY HM.ID_PERMOHONAN, HM.ID_HAKMILIK) KKK, "+
				" (SELECT HM.ID_PERMOHONAN, HM.ID_HAKMILIK, MAX(EBK.TARIKH_CATATAN) AS TARIKH_ENDOSK FROM "+
				" TBLPPTHAKMILIKBORANGK HBK, TBLPPTHAKMILIK HM, TBLPPTBORANGK BK, TBLPPTENDOSANBORANGK EBK "+
				" WHERE HM.ID_HAKMILIK = HBK.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK AND BK.ID_BORANGK = EBK.ID_BORANGK "+
				" AND EBK.TARIKH_CATATAN IS NOT NULL "+
				" GROUP BY HM.ID_PERMOHONAN, HM.ID_HAKMILIK) EEE "+
				" WHERE F.ID_FAIL = P.ID_FAIL AND H.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) AND P.ID_PERMOHONAN = H.ID_PERMOHONAN "+
				" AND H.ID_HAKMILIK = HHH.ID_HAKMILIK(+) "+
				" AND H.ID_HAKMILIK = KKK.ID_HAKMILIK(+) "+
				" AND H.ID_HAKMILIK = EEE.ID_HAKMILIK(+) "+
				" AND P.ID_PERMOHONAN = WWW.ID_PERMOHONAN(+) "+
				" AND F.ID_SUBURUSAN = '52' ";
		
				if(ID_FAIL.equals(""))
				{
				
					if(!CARIAN_NO_FAIL.equals(""))
					{
						if(WCCARIAN_NO_FAIL.equals("1"))
						{
							sql += " AND UPPER(F.NO_FAIL) = '"+CARIAN_NO_FAIL.toUpperCase().trim()+"' ";
						}
						else if(WCCARIAN_NO_FAIL.equals("2"))
						{
							sql += " AND UPPER(F.NO_FAIL) LIKE '%"+CARIAN_NO_FAIL.toUpperCase().trim()+"%' ";
						}			
					}
					
					if(!CARIAN_PROJEK.equals(""))
					{
						if(WCCARIAN_PROJEK.equals("1"))
						{
							sql += " AND UPPER(P.TUJUAN) = '"+CARIAN_PROJEK.toUpperCase().trim()+"' ";
						}
						else if(WCCARIAN_PROJEK.equals("2"))
						{
							sql += " AND UPPER(P.TUJUAN) LIKE '%"+CARIAN_PROJEK.toUpperCase().trim()+"%' ";
						}			
					}
					
					if(!CARIAN_TAHUN_MULA.equals("") && !CARIAN_TAHUN_AKHIR.equals(""))
					{
						if(CARIAN_TAHUN_MULA.equals(CARIAN_TAHUN_AKHIR))
						{
							sql += " AND TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY') = '"+CARIAN_TAHUN_MULA+"' ";
						}
						else
						{
							sql += " AND P.TARIKH_PERMOHONAN BETWEEN TO_DATE('"+CARIAN_TAHUN_MULA+"','YYYY') AND TO_DATE('"+CARIAN_TAHUN_AKHIR+"','YYYY') ";
						}
					}
					
					if(!CARIAN_NEGERI.equals(""))
					{
						sql += " AND F.ID_NEGERI = '"+CARIAN_NEGERI+"' ";						
					}
				
				}
				
				//" AND MA.TARIKH_PERMOHONAN BETWEEN TO_DATE('01/2015','MM/YYYY') AND TO_DATE('12/2015','MM/YYYY') ";
				
				else
				{
					sql += " AND F.ID_FAIL = '"+ID_FAIL+"'  ";
				}
				
				sql += " AND (NVL(H.FLAG_PEMBATALAN, ' ') != 'Y' OR NVL(H.FLAG_PEMBATALAN_KESELURUHAN, ' ') != 'Y' "+
				" OR NVL(H.FLAG_PENARIKAN_BALIK, ' ') != 'Y' OR NVL(H.FLAG_PENARIKAN_KESELURUHAN, ' ') != 'Y') ";
				
				sql += " AND H.FLAG_AMBIL_SEGERA  IS NULL "; //asingkan pengambilan segera
				
				
				
				String orderBy = "";
				if(CARIAN_JENIS_KPI.equals("1"))
				{
					orderBy = "ABS(TO_DATE(WWW.TARIKH_WARTA, 'DD/MM/YYYY') - TO_DATE(HHH.TARIKH_BORANGH, 'DD/MM/YYYY')) ASC, ADA_H DESC,TARIKH_BORANGH ASC,NO_SUBJAKET ASC";
					
				}
				else if(CARIAN_JENIS_KPI.equals("2"))
				{
					orderBy = "ABS(TO_DATE(HHH.TARIKH_BORANGH, 'DD/MM/YYYY') - TO_DATE(KKK.TARIKH_BORANGK, 'DD/MM/YYYY')) ASC, ADA_K DESC,TARIKH_BORANGK ASC,NO_SUBJAKET ASC";
				}
				else if(CARIAN_JENIS_KPI.equals("3"))
				{
					orderBy = "ABS(TO_DATE(KKK.TARIKH_BORANGK, 'DD/MM/YYYY') - TO_DATE(EEE.TARIKH_ENDOSK, 'DD/MM/YYYY')) ASC, ADA_ENDOSK DESC,TARIKH_ENDOSK ASC,NO_SUBJAKET ASC";
				}
				
				
				
				sql += " ORDER BY "+orderBy;
				
				
				myLogger.info("SQL HAKMILIK BASED : "+sql);
				
		return sql;
	}
	
	//method untuk susun column dalam senarai
		//dalam case ni tak pakai	
	public List listColumnForHakmilik(String skrinName)throws Exception {
		List listColumnForSenarai = null;
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_HAKMILIK",12,"NO. HAKMILIK","left","VARCHAR2","",""));	
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_LOT",12,"NO. LOT","left","VARCHAR2","",""));	
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"",76,"TEMPOH", "left","","XXXXX",""));		
		return listColumnForSenarai;
	}
	
	//method untuk susun column dalam senarai
			//dalam case ni tak pakai	
	public List listColumnForProjek(String skrinName)throws Exception {
		String CARIAN_JENIS_KPI = getParam(skrinName+"CARIAN_JENIS_KPI");
		myLogger.info("listColumnForProjek CARIAN_JENIS_KPI : "+CARIAN_JENIS_KPI);
		List listColumnForSenarai = null;
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"PROJEK",15,"PROJEK<br>[JENIS PROJEK]","left","VARCHAR2","",""));	
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TARIKH_PERMOHONAN",6,"TARIKH PERMOHONAN","center","DATE","","","dd/MM/yyyy"));
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TOTAL_HAKMILIK",6,"JUMLAH<br>LOT","center","NUMBER","",""));	
		if(CARIAN_JENIS_KPI.equals("1"))
		{
			listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TOTAL_HAKMILIK_H",6,"JUMLAH<br>LOT<br>(BORANG H)","center","NUMBER","",""));	
		}
		if(CARIAN_JENIS_KPI.equals("2"))
		{
			listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TOTAL_HAKMILIK_K",6,"JUMLAH<br>LOT<br>(BORANG K)","center","NUMBER","",""));	
		}
		if(CARIAN_JENIS_KPI.equals("2"))
		{
			listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TOTAL_HAKMILIK_ENDOSK",6,"JUMLAH<br>LOT<br>(ENDORS K)","center","NUMBER","",""));
		}
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"",65,"TEMPOH", "left","","",""));	
		
		
		
		
		
		return listColumnForSenarai;
	}
	
	
	@SuppressWarnings("unchecked")
	public String htmlProjekBased( 
			String command,
			String skrinName,
			String query, List listColumnForSenarai, 
			boolean flagCache, // list yg ada cache
			boolean resetCache, // keperluan mereset cache
			String cacheID, // unik cache ID
			boolean showPaging,
			String div,
			String columNameSort, // senarai nama column untuk  kita sort kan
			String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
			String sortType, // jenis sorting mengikut column
			String dateFormat,
			int maxRowNum,
			String divViewId,
			String param,
			Db db)throws Exception {
		
		String CARIAN_JENIS_KPI = getParam(skrinName+"CARIAN_JENIS_KPI");
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		
		List listRekod = RT.RTListRekod(
				query,
				flagCache, // list yg ada cache
				resetCache, // keperluan mereset cache
				cacheID, // unik cache ID
				skrinName, // nama list
				div,showPaging,
				columNameSort, // senarai nama column untuk  kita sort kan
				columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				sortType, // jenis sorting mengikut column
				dateFormat,
				maxRowNum,
				db);
		
		List listMinMaxDate = RT.RTListRekod(queryMinMaxDate(db,skrinName),
				flagCache, // list yg ada cache
				resetCache, // keperluan mereset cache
				"MinMax"+cacheID, // unik cache ID
			skrinName, // nama list
			div,showPaging,
			"", // senarai nama column untuk  kita sort kan
			"", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
			"", // jenis sorting mengikut column
			"", // date format
			0,
			db);
		
		Map map_MinMaxDate = (Map) listMinMaxDate.get(0);
		double diff_warta_h = 0;
		double diff_h_k = 0;
		double diff_k_endos = 0;
		int INT_MAX_TEMPOH_WARTA_H = 0;
		int INT_MAX_TEMPOH_H_K= 0;
		int INT_MAX_TEMPOH_K_ENDOS = 0;
		String MIN_TARIKH_WARTA = "";
		String MAX_TARIKH_BORANGH = "";
		String MIN_TARIKH_BORANGH = "";
		String MAX_TARIKH_BORANGK = "";
		String MIN_TARIKH_BORANGK = "";
		String MAX_TARIKH_ENDOSK = "";
		String MAX_TEMPOH_WARTA_H = "";
		String MAX_TEMPOH_H_K = "";
		String MAX_TEMPOH_K_ENDOS = "";
		
		if(map_MinMaxDate != null)
		{
			MIN_TARIKH_WARTA = (String) map_MinMaxDate.get("MIN_TARIKH_WARTA");
			MAX_TARIKH_BORANGH = (String) map_MinMaxDate.get("MAX_TARIKH_BORANGH");
			MIN_TARIKH_BORANGH = (String) map_MinMaxDate.get("MIN_TARIKH_BORANGH");
			MAX_TARIKH_BORANGK = (String) map_MinMaxDate.get("MAX_TARIKH_BORANGK");
			MIN_TARIKH_BORANGK = (String) map_MinMaxDate.get("MIN_TARIKH_BORANGK");
			MAX_TARIKH_ENDOSK = (String) map_MinMaxDate.get("MAX_TARIKH_ENDOSK");			
			MAX_TEMPOH_WARTA_H = (String) map_MinMaxDate.get("MAX_TEMPOH_WARTA_H");
			MAX_TEMPOH_H_K = (String) map_MinMaxDate.get("MAX_TEMPOH_H_K");
			MAX_TEMPOH_K_ENDOS = (String) map_MinMaxDate.get("MAX_TEMPOH_K_ENDOS");
			
			if(!MAX_TEMPOH_WARTA_H.equals(""))
			{
				INT_MAX_TEMPOH_WARTA_H = Integer.parseInt(MAX_TEMPOH_WARTA_H);				
			}
			if(!MAX_TEMPOH_H_K.equals(""))
			{
				INT_MAX_TEMPOH_H_K = Integer.parseInt(MAX_TEMPOH_H_K);
			}
			if(!MAX_TEMPOH_K_ENDOS.equals(""))
			{
				INT_MAX_TEMPOH_K_ENDOS = Integer.parseInt(MAX_TEMPOH_K_ENDOS);
			}
			
			//-kumpul dlu semua maklumat sebelum nak buat gantt char
			//-kena cari formula untuk divide column space by % of max tempoh
			
			diff_warta_h = daysBetween2dates(MIN_TARIKH_WARTA, MAX_TARIKH_BORANGH, "MONTHS");
			diff_h_k = daysBetween2dates(MIN_TARIKH_BORANGH, MAX_TARIKH_BORANGK, "WEEKS");
			diff_k_endos = daysBetween2dates(MIN_TARIKH_BORANGK, MAX_TARIKH_ENDOSK, "WEEKS");
		    myLogger.info(">>>>>>>>>>>>>>> day diff_warta_h ["+MIN_TARIKH_WARTA+" --> "+MAX_TARIKH_BORANGH+"] : "+diff_warta_h);
		    myLogger.info(">>>>>>>>>>>>>>> day diff_h_k ["+MIN_TARIKH_BORANGH+" --> "+MAX_TARIKH_BORANGK+"] : "+diff_h_k);
		    myLogger.info(">>>>>>>>>>>>>>> day diff_k_endos ["+MIN_TARIKH_BORANGK+" --> "+MAX_TARIKH_ENDOSK+"] : "+diff_k_endos);
			
			myLogger.info("INT_MAX_TEMPOH_WARTA_H : "+INT_MAX_TEMPOH_WARTA_H+"; INT_MAX_TEMPOH_H_K : "+INT_MAX_TEMPOH_H_K+"; INT_MAX_TEMPOH_K_ENDOS : "+INT_MAX_TEMPOH_K_ENDOS);
		}
		myLogger.info("map_MinMaxDate :::: "+map_MinMaxDate);
		
		
		
		
		List listRekodAsal = listRekod;
		
		String html = "";
		int totalRekodCount = listRekod.size(); // jumlah rekod sebelum di paging kan
	
		String classTable = "";
		if(!("searchColumn").equals(getParam("actionajax")))
		{
			classTable = "classFade";
		}
		
		int showPageNumber = 0;
		if(showPaging==true)
		{
			Map RTsetupPageList = RT.RTsetupPageList(listRekod,listColumnForSenarai,
					//namaList,namaMap,
					maxRowNum,param,classTable,skrinName);
			
			if(RTsetupPageList != null)
			{
				listRekod = (List) RTsetupPageList.get("listRekod");
				showPageNumber = (Integer) RTsetupPageList.get("startNumber");				
				html += (String) RTsetupPageList.get("htmlPaging");
			}
		}
		
		myLogger.info("showPageNumber :::::::::: "+showPageNumber);
		
		int totalColumn = 0;
		//get html column tr header
		
		html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" width=\"100%\" class='"+classTable+"' > ";
		if (listRekodAsal.size() > 1) {
			html += "<tr class=\"table_header\" > ";
			html += "<td align=\"center\" valign=\"top\" ></td> ";
			html += RT.customTdSearch(skrinName, div, "PROJEK", "VARCHAR2", command, totalRekodCount, param);
			html += RT.customTdSearch(skrinName, div, "TARIKH_PERMOHONAN", "DATE", command, totalRekodCount, param);
			html += RT.customTdSearch(skrinName, div, "TOTAL_HAKMILIK", "NUMBER", command, totalRekodCount, param);
			if(CARIAN_JENIS_KPI.equals("1"))
			{
				html += RT.customTdSearch(skrinName, div, "TOTAL_HAKMILIK_H", "NUMBER", command, totalRekodCount, param);
			}
			else if(CARIAN_JENIS_KPI.equals("2"))
			{
				html += RT.customTdSearch(skrinName, div, "TOTAL_HAKMILIK_K", "NUMBER", command, totalRekodCount, param);
			}
			else if(CARIAN_JENIS_KPI.equals("3"))
			{
				html += RT.customTdSearch(skrinName, div, "TOTAL_HAKMILIK_ENDOSK", "NUMBER", command, totalRekodCount, param);
			}
			
			html += "<td align=\"left\" valign=\"top\" ></td> ";			
			html += "</tr>";
		}
				
		html += "<tr class=\"table_header\" > ";
		html += "<td   align=\"center\" valign=\"top\" width=\"2%\">BIL</td> ";
		html += RT.customTdSort(skrinName,"PROJEK", "[NO. FAIL JKPTG]<br>[NO. FAIL PTG]<br>[JENIS PROJEK]<br><br>PROJEK", "VARCHAR2", "left", 15 , param, totalRekodCount, command, div);
		html += RT.customTdSort(skrinName,"TARIKH_PERMOHONAN", "TARIKH PERMOHONAN", "DATE", "center", 6 , param, totalRekodCount, command, div , "dd/MM/yyyy");
		html += RT.customTdSort(skrinName,"TOTAL_HAKMILIK", "JUMLAH<br>LOT", "NUMBER", "center", 6 , param, totalRekodCount, command, div);
		if(CARIAN_JENIS_KPI.equals("1"))
		{
			html += RT.customTdSort(skrinName,"TOTAL_HAKMILIK_H", "JUMLAH<br>LOT<br>(BORANG H)", "NUMBER", "center", 6 , param, totalRekodCount, command, div);
		}
		else if(CARIAN_JENIS_KPI.equals("2"))
		{
			html += RT.customTdSort(skrinName,"TOTAL_HAKMILIK_K", "JUMLAH<br>LOT<br>(BORANG K)", "NUMBER", "center", 6 , param, totalRekodCount, command, div);
		}
		else if(CARIAN_JENIS_KPI.equals("3"))
		{
			html += RT.customTdSort(skrinName,"TOTAL_HAKMILIK_ENDOSK", "JUMLAH<br>LOT<br>(ENDORS K)", "NUMBER", "center", 6 , param, totalRekodCount, command, div);
		}
		
		String jenisKPI = "";
		if(CARIAN_JENIS_KPI.equals("1"))
		{
			jenisKPI = "TEMPOH WARTA SEHINGGA BORANG H (BULAN)<br>[JAJARAN : MAKSIMUM 6 BULAN]<br>[TAPAK : MAKSIMUM 4 BULAN]";			
		}
		else if(CARIAN_JENIS_KPI.equals("2"))
		{
			jenisKPI = "TEMPOH BORANG H SEHINGGA BORANG K (MINGGU)<br>[MAKSIMUM 2 MINGGU]";
		}
		else if(CARIAN_JENIS_KPI.equals("3"))
		{
			jenisKPI = "TEMPOH BORANG K SEHINGGA ENDORSAN BORANG K (MINGGU)<br>[MAKSIMUM 2 MINGGU]";
		}
		
		jenisKPI += "<br><br>B=BULAN; M=MINGGU; H=HARI;";
		
		html += "<td align=\"left\" valign=\"top\" width=\"65%\">"+jenisKPI+"</td>";
		
		if (listRekod.size() != 0) {
			for (int i = 0; i < listRekod.size(); i++) {
				Map map_rekod = (Map) listRekod.get(i);
				
				//myLogger.info("map_rekod ::::::: "+map_rekod);
				
				String ID_FAIL = (String) map_rekod.get("ID_FAIL");
				String PROJEK = (String) map_rekod.get("PROJEK");
				String NO_FAIL = (String) map_rekod.get("NO_FAIL");
				String MAX_MONTH_WARTA_TO_H = (String) map_rekod.get("MAX_MONTH_WARTA_TO_H");
				String TOTAL_HAKMILIK = (String) map_rekod.get("TOTAL_HAKMILIK");
				String TOTAL_HAKMILIK_H = (String) map_rekod.get("TOTAL_HAKMILIK_H");
				String TOTAL_HAKMILIK_K = (String) map_rekod.get("TOTAL_HAKMILIK_K");
				String TOTAL_HAKMILIK_ENDOSK = (String) map_rekod.get("TOTAL_HAKMILIK_ENDOSK");
				String MAX_WEEK_H_TO_K = (String) map_rekod.get("MAX_WEEK_H_TO_K");
				String MAX_WEEK_K_TO_ENDOS = (String) map_rekod.get("MAX_WEEK_K_TO_ENDOS");
				String TARIKH_WARTA = (String) map_rekod.get("TARIKH_WARTA");
				String TARIKH_PERMOHONAN = (String) map_rekod.get("TARIKH_PERMOHONAN");
				String MAX_TARIKH_BORANGH_P = (String) map_rekod.get("MAX_TARIKH_BORANGH");
				String MIN_TARIKH_BORANGH_P = (String) map_rekod.get("MIN_TARIKH_BORANGH");
				String MAX_TARIKH_BORANGK_P = (String) map_rekod.get("MAX_TARIKH_BORANGK");
				String MIN_TARIKH_BORANGK_P = (String) map_rekod.get("MIN_TARIKH_BORANGK");
				String MAX_TARIKH_ENDOSK_P = (String) map_rekod.get("MAX_TARIKH_ENDOSK");
				String JENIS_PROJEK = (String) map_rekod.get("JENIS_PROJEK");		
				String TEMPOH_H_K_MEET = (String) map_rekod.get("TEMPOH_H_K_MEET");		
				String TEMPOH_WARTA_H_MEET = (String) map_rekod.get("TEMPOH_WARTA_H_MEET");		
				String TEMPOH_K_ENDOS_MEET = (String) map_rekod.get("TEMPOH_K_ENDOS_MEET");		
				
				
				//myLogger.info(">>>>>>>>> MIN_TARIKH_WARTA  : "+MIN_TARIKH_WARTA+" TARIKH_WARTA : "+TARIKH_WARTA);
				
				double diff_warta_gap = daysBetween2dates(MIN_TARIKH_WARTA, TARIKH_WARTA, "MONTHS");
				double diff_warta_to_h = daysBetween2dates(TARIKH_WARTA, MAX_TARIKH_BORANGH_P, "MONTHS");
				String ayat_diff_warta_to_h = ayatBetween2dates(TARIKH_WARTA, MAX_TARIKH_BORANGH_P, "MONTHS");
				double diff_h_gap = daysBetween2dates(MIN_TARIKH_BORANGH, MIN_TARIKH_BORANGH_P, "WEEKS");
				double diff_h_to_k = daysBetween2dates(MIN_TARIKH_BORANGH_P, MAX_TARIKH_BORANGK_P, "WEEKS");
				String ayat_diff_h_to_k = ayatBetween2dates(MIN_TARIKH_BORANGH_P, MAX_TARIKH_BORANGK_P, "WEEKS");
				double diff_k_gap = daysBetween2dates(MIN_TARIKH_BORANGK, MIN_TARIKH_BORANGK_P, "WEEKS");
				double diff_k_to_endosk = daysBetween2dates(MIN_TARIKH_BORANGK_P, MAX_TARIKH_ENDOSK_P, "WEEKS");
				String ayat_diff_k_to_endosk = ayatBetween2dates(MIN_TARIKH_BORANGK_P, MAX_TARIKH_ENDOSK_P, "WEEKS");
				
				double perc_warta_gap = percentageGap(diff_warta_gap, diff_warta_h);
				double perc_warta_to_h = percentageGap(diff_warta_to_h, diff_warta_h);
				
				double max_tempoh_h = 4;
				if(JENIS_PROJEK.equals("JAJARAN"))
				{
					max_tempoh_h = 6;
				}
				
				double perc_warta_to_h_max = percentageGap(max_tempoh_h, diff_warta_h);
				double perc_h_gap = percentageGap(diff_h_gap, diff_h_k);
				double perc_h_to_k = percentageGap(diff_h_to_k, diff_h_k);
				double perc_h_to_k_max = percentageGap(2, diff_h_k);
				double perc_k_gap = percentageGap(diff_k_gap, diff_k_endos);
				double perc_k_to_endosk = percentageGap(diff_k_to_endosk, diff_k_endos);
				double perc_k_to_endosk_max = percentageGap(2, diff_k_endos);
				
				
				html += "<tr>";
			    html += "<td id=\"tdRowSpan"+ID_FAIL+"\" rowspan=\"1\" class=\"row2\"  align=\"center\" valign=\"top\" >"+map_rekod.get("BILSORT")+"</td>";
			    html += "<td class=\"row2\" align=\"left\" valign=\"top\">"+PROJEK+"</td>";
			    html += "<td class=\"row2\" align=\"center\" valign=\"top\">"+TARIKH_PERMOHONAN+"</td>";
			    html += "<td class=\"row2\" align=\"center\" valign=\"top\">"+TOTAL_HAKMILIK+"</td>";
			    
			    String displayTotalHakmilikKPI = "";
			    if(CARIAN_JENIS_KPI.equals("1"))
				{
			    	displayTotalHakmilikKPI = TOTAL_HAKMILIK_H;
				}
			    else if(CARIAN_JENIS_KPI.equals("2"))
				{
			    	displayTotalHakmilikKPI = TOTAL_HAKMILIK_K;
				}
			    else if(CARIAN_JENIS_KPI.equals("3"))
				{
			    	displayTotalHakmilikKPI = TOTAL_HAKMILIK_ENDOSK;
				}
			    
			    html += "<td class=\"row2\" align=\"center\" valign=\"top\">"+displayTotalHakmilikKPI+"</td>";
			    html += "<td class=\"row2\" align=\"left\" valign=\"top\">";
			    
			    
			    String displayMeetTarget = "";
			    if(CARIAN_JENIS_KPI.equals("1"))
				{
			    	displayMeetTarget = TEMPOH_WARTA_H_MEET;
				}
			    else if(CARIAN_JENIS_KPI.equals("2"))
				{
			    	displayMeetTarget = TEMPOH_H_K_MEET;
				}
			    else if(CARIAN_JENIS_KPI.equals("3"))
				{
			    	displayMeetTarget = TEMPOH_K_ENDOS_MEET;
				}
			    String displayInfoMeet = "";
			    if(!displayMeetTarget.equals(""))
			    {
			    	//if(Integer.parseInt(displayMeetTarget) > 0)
			    	{
			    		if(CARIAN_JENIS_KPI.equals("1"))
			    		{
			    			String tempohJenisH = "";
			    			if(JENIS_PROJEK.equals("JAJARAN"))
							{
			    				tempohJenisH = "6";
							}
			    			else if(JENIS_PROJEK.equals("TAPAK"))
							{
			    				tempohJenisH = "4";
							}
			    			displayInfoMeet += "JUMLAH LOT YANG DISELESAIKAN BORANG H DALAM TEMPOH KURANG DARI "+tempohJenisH+" BULAN : ";
			    		}	
			    		else if(CARIAN_JENIS_KPI.equals("2"))
			    		{			    			
			    			displayInfoMeet += "JUMLAH LOT YANG DISELESAIKAN BORANG K DALAM TEMPOH KURANG DARI 2 MINGGU : ";
			    		}
			    		else if(CARIAN_JENIS_KPI.equals("3"))
			    		{			    			
			    			displayInfoMeet += "JUMLAH LOT YANG DIENDORS BORANG K DALAM TEMPOH KURANG DARI 2 MINGGU : ";
			    		}
			    		
			    		double peratusSelesai = 0.00;
			    		if(Integer.parseInt(displayTotalHakmilikKPI) > 0)
			    		{
			    			double displayMeetTarget_int = Integer.parseInt(displayMeetTarget);
			    			double displayTotalHakmilikKPI_int = Integer.parseInt(displayTotalHakmilikKPI);
			    			
			    			peratusSelesai = (displayMeetTarget_int / displayTotalHakmilikKPI_int) * 100;
			    			//myLogger.info("displayMeetTarget_int : "+displayMeetTarget_int+"; displayTotalHakmilikKPI_int : "+displayTotalHakmilikKPI_int+";  	peratusSelesai >>>>>>>>>>>>>>>>>> "+peratusSelesai+"; displayMeetTarget_int / displayTotalHakmilikKPI_int : "+(displayMeetTarget_int / displayTotalHakmilikKPI_int));
			    		}
			    		
			    		displayInfoMeet += "<br><b><font color='blue'>"+displayMeetTarget+"/"+displayTotalHakmilikKPI+"</font> ["+round(peratusSelesai,2)+"%]</b><br><br>";
			    		
			    		
			    	}
			    }
			    
			    //html += "<div style=\"width:0; overflow:visible; white-space:nowrap;\" class=\"normalBarHM\"><b>"+displayInfoMeet+"</b></div>";
			    html += ""+displayInfoMeet+"";
			    		
			    	String jenisBar = "normal";
			    	
			    	
			    	if(CARIAN_JENIS_KPI.equals("1"))
					{
			    		if((diff_warta_to_h > 6 && JENIS_PROJEK.equals("JAJARAN")) || (diff_warta_to_h > 4 && JENIS_PROJEK.equals("TAPAK")))
				    	{
				    		jenisBar = "delay";
				    	}
			    		
			    		if(diff_warta_to_h > 0)
			    		{  
			    			html += barChart(perc_warta_gap,perc_warta_to_h,perc_warta_to_h_max,TARIKH_WARTA,MAX_TARIKH_BORANGH_P,jenisBar,diff_warta_to_h+"",ayat_diff_warta_to_h,ID_FAIL,CARIAN_JENIS_KPI,"PROJEK");
			    		}
					}			    	
			    	else if(CARIAN_JENIS_KPI.equals("2"))
					{
			    		if(diff_h_to_k > 2)
				    	{
				    		jenisBar = "delay";
				    	}
			    		
			    		if(diff_h_to_k > 0)
			    		{
			    			html += barChart(perc_h_gap,perc_h_to_k,perc_h_to_k_max,MIN_TARIKH_BORANGH_P,MAX_TARIKH_BORANGK_P,jenisBar,diff_h_to_k+"",ayat_diff_h_to_k,ID_FAIL,CARIAN_JENIS_KPI,"PROJEK");
			    		}
					}
			    	else if(CARIAN_JENIS_KPI.equals("3"))
					{
			    		if(diff_k_to_endosk > 2)
				    	{
				    		jenisBar = "delay";
				    	}
			    		
			    		if(diff_k_to_endosk > 0)
			    		{
			    			html += barChart(perc_k_gap,perc_k_to_endosk,perc_k_to_endosk_max,MIN_TARIKH_BORANGK_P,MAX_TARIKH_ENDOSK_P,jenisBar,diff_k_to_endosk+"",ayat_diff_k_to_endosk,ID_FAIL,CARIAN_JENIS_KPI,"PROJEK");
			    		}
					}
			    	
			    	 html += "<div id=\"div_hakmilikBased"+ID_FAIL+"\" ></div><input type=\"hidden\" id=\"setOpenClose"+ID_FAIL+"\" name=\"setOpenClose"+ID_FAIL+"\" value=\"close\" >";
						
			    
			    html += "</td>";
			    
			    html += "</tr>";
			    
								
			}
		}
		else
		{
			html += "<tr><td align=\"left\" valign=\"top\" colspan=\"15\" >TIADA REKOD</td></tr> ";
		}
		html += "</table>";
		return html;
	}
	
	
	@SuppressWarnings("unchecked")
	public String htmlHakmilikBased( 
			String command,
			String skrinName,
			String query, List listColumnForSenarai, 
			boolean flagCache, // list yg ada cache
			boolean resetCache, // keperluan mereset cache
			String cacheID, // unik cache ID
			boolean showPaging,
			String div,
			String columNameSort, // senarai nama column untuk  kita sort kan
			String columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
			String sortType, // jenis sorting mengikut column
			String dateFormat,
			int maxRowNum,
			String divViewId,
			String param,
			String ID_FAIL,
			Db db)throws Exception {
		
		List listRekod = RT.RTListRekod(
				query,
				flagCache, // list yg ada cache
				resetCache, // keperluan mereset cache
				cacheID, // unik cache ID
				skrinName, // nama list
				div,showPaging,
				columNameSort, // senarai nama column untuk  kita sort kan
				columnTypeSort, // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
				sortType, // jenis sorting mengikut column
				dateFormat,
				maxRowNum,
				db);
		
		String CARIAN_JENIS_KPI = getParam("carianUtamaCARIAN_JENIS_KPI");
		
		List listRekodAsal = listRekod;
		
		String html = "";
		int totalRekodCount = listRekod.size(); // jumlah rekod sebelum di paging kan
	
		String classTable = "";
		if(!("searchColumn").equals(getParam("actionajax")))
		{
			classTable = "classFade";
		}
		
		
		
		if(showPaging==true)
		{
			Map RTsetupPageList = RT.RTsetupPageList(listRekod,listColumnForSenarai,
					//namaList,namaMap,
					maxRowNum,param,classTable,skrinName);
			if(RTsetupPageList != null)
			{
				listRekod = (List) RTsetupPageList.get("listRekod");
				html += (String) RTsetupPageList.get("htmlPaging");
			}
		}
		
		int totalColumn = 0;
		//get html column tr header
		
		html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" width=\"100%\" class='"+classTable+"' > ";
		if (listRekodAsal.size() > 1) {
			html += "<tr class=\"table_header\" > ";
			html += "<td align=\"center\" valign=\"top\" ></td> ";
			html += RT.customTdSearch(skrinName, div, "NO_HAKMILIK", "VARCHAR2", command, totalRekodCount, param);
			html += RT.customTdSearch(skrinName, div, "NO_LOT", "VARCHAR2", command, totalRekodCount, param);
			html += "<td align=\"center\" valign=\"top\" ></td> ";			
			html += "</tr>";
		}
		
		html += "<tr class=\"table_header\" > ";
		html += "<td   align=\"center\" valign=\"top\" width=\"2%\">BIL</td> ";
		html += RT.customTdSort(skrinName,"NO_HAKMILIK", "NO. HAKMILIK", "VARCHAR2", "left", 12 , param, totalRekodCount, command, div);
		html += RT.customTdSort(skrinName,"NO_LOT", "NO. LOT", "VARCHAR2", "left", 12 , param, totalRekodCount, command, div);
				
		String jenisKPI = "";
		if(CARIAN_JENIS_KPI.equals("1"))
		{
			jenisKPI = "TEMPOH WARTA SEHINGGA BORANG H (BULAN)";
		}
		else if(CARIAN_JENIS_KPI.equals("2"))
		{
			jenisKPI = "TEMPOH BORANG H SEHINGGA BORANG K (MINGGU)";
		}
		else if(CARIAN_JENIS_KPI.equals("3"))
		{
			jenisKPI = "TEMPOH BORANG K SEHINGGA ENDORSAN BORANG K (MINGGU)";
		}
		
		html += "<td align=\"left\" valign=\"top\" width=\"78%\">"+jenisKPI+"</td>";
		
		
		List columnNameFromQuery = RT.returnColumnNameFromQuery(query, skrinName+"list", true, false, true, cacheID, db);
		
		myLogger.info("CHECK >>>>>>>>>>> minDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		
		String MIN_TARIKH_WARTA_HM = getParam("minDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MIN_TARIKH_BORANGH_HM  = getParam("minDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MIN_TARIKH_BORANGK_HM  = getParam("minDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MIN_TARIKH_ENDOSK_HM  = getParam("minDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MAX_TARIKH_WARTA_HM = getParam("maxDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MAX_TARIKH_BORANGH_HM  = getParam("maxDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MAX_TARIKH_BORANGK_HM  = getParam("maxDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		String MAX_TARIKH_ENDOSK_HM  = getParam("maxDateGC_"+ID_FAIL+"_"+CARIAN_JENIS_KPI);
		
		double diff_warta_h = daysBetween2dates(MIN_TARIKH_WARTA_HM, MAX_TARIKH_BORANGH_HM, "MONTHS");
		double diff_h_k = daysBetween2dates(MIN_TARIKH_BORANGH_HM, MAX_TARIKH_BORANGK_HM, "WEEKS");
		double diff_k_endos = daysBetween2dates(MIN_TARIKH_BORANGK_HM, MAX_TARIKH_ENDOSK_HM, "WEEKS");
		
		myLogger.info("HM ::: MIN_TARIKH_WARTA_HM : "+MIN_TARIKH_WARTA_HM+"; MAX_TARIKH_BORANGH_HM : "+MAX_TARIKH_BORANGH_HM);
		myLogger.info("HM ::: diff_warta_h : "+diff_warta_h+"; diff_h_k : "+diff_h_k+"; diff_k_endos : "+diff_k_endos);
		
		if (listRekod.size() != 0) {
			for (int i = 0; i < listRekod.size(); i++) {
				Map map_rekod = (Map) listRekod.get(i);
				String NO_HAKMILIK = (String) map_rekod.get("NO_HAKMILIK");
				String NO_LOT = (String) map_rekod.get("NO_LOT");
				
				String TARIKH_WARTA = (String) map_rekod.get("TARIKH_WARTA");
				String TARIKH_BORANGH = (String) map_rekod.get("TARIKH_BORANGH");
				String TARIKH_BORANGK = (String) map_rekod.get("TARIKH_BORANGK");
				String TARIKH_ENDOSK = (String) map_rekod.get("TARIKH_ENDOSK");
				String JENIS_PROJEK = (String) map_rekod.get("JENIS_PROJEK");				
								
				double diff_warta_gap = daysBetween2dates(MIN_TARIKH_WARTA_HM, TARIKH_WARTA, "MONTHS");
				double diff_warta_to_h = daysBetween2dates(TARIKH_WARTA, TARIKH_BORANGH, "MONTHS");
				String ayat_diff_warta_to_h = ayatBetween2dates(TARIKH_WARTA, TARIKH_BORANGH, "MONTHS");
				double diff_h_gap = daysBetween2dates(MIN_TARIKH_BORANGH_HM, TARIKH_BORANGH, "WEEKS");
				double diff_h_to_k = daysBetween2dates(TARIKH_BORANGH, TARIKH_BORANGK, "WEEKS");
				String ayat_diff_h_to_k = ayatBetween2dates(TARIKH_BORANGH, TARIKH_BORANGK, "WEEKS");
				double diff_k_gap = daysBetween2dates(MIN_TARIKH_BORANGK_HM, TARIKH_BORANGK, "WEEKS");
				double diff_k_to_endosk = daysBetween2dates(TARIKH_BORANGK, TARIKH_ENDOSK, "WEEKS");
				String ayat_diff_k_to_endosk = ayatBetween2dates(TARIKH_BORANGK, TARIKH_ENDOSK, "WEEKS");
				
				double perc_warta_gap = percentageGap(diff_warta_gap, diff_warta_h);
				double perc_warta_to_h = percentageGap(diff_warta_to_h, diff_warta_h);
				
				double max_tempoh_h = 4;
				if(JENIS_PROJEK.equals("JAJARAN"))
				{
					max_tempoh_h = 6;
				}
				
				double perc_warta_to_h_max = percentageGap(max_tempoh_h, diff_warta_h);
				double perc_h_gap = percentageGap(diff_h_gap, diff_h_k);
				double perc_h_to_k = percentageGap(diff_h_to_k, diff_h_k);
				double perc_h_to_k_max = percentageGap(2, diff_h_k);
				double perc_k_gap = percentageGap(diff_k_gap, diff_k_endos);
				double perc_k_to_endosk = percentageGap(diff_k_to_endosk, diff_k_endos);
				double perc_k_to_endosk_max = percentageGap(2, diff_k_endos);
				
				
				
				html += "<tr > ";
				html += "<td class=\"" +"row2"+
						//""+map_rekod.get("ROWCSS")+"" +
								"\"  align=\"center\" valign=\"top\" >"+map_rekod.get("BILSORT")+"</td> ";
				html += "<td class=\"" +"row2"+
						//""+map_rekod.get("ROWCSS")+"" +
								"\"  align=\"left\" valign=\"top\" >"+NO_HAKMILIK+"</td> ";
				html += "<td class=\"" +"row2"+
						//""+map_rekod.get("ROWCSS")+"" +
								"\"  align=\"left\" valign=\"top\" >"+NO_LOT+"</td> ";
				html += "<td class=\"" +"row2"+
						//""+map_rekod.get("ROWCSS")+"" +
								"\"  align=\"left\" valign=\"top\" >";
				
				String jenisBar = "normal";
		    	
				
				
		    	if(CARIAN_JENIS_KPI.equals("1"))
				{
		    		if((diff_warta_to_h > 6 && JENIS_PROJEK.equals("JAJARAN")) || (diff_warta_to_h > 4 && JENIS_PROJEK.equals("TAPAK")))
			    	{
			    		jenisBar = "delay";
			    	}
		    		
		    		if(diff_warta_to_h > 0)
		    		{  
		    			html += barChart(perc_warta_gap,perc_warta_to_h,perc_warta_to_h_max,TARIKH_WARTA,TARIKH_BORANGH,jenisBar,diff_warta_to_h+"",ayat_diff_warta_to_h,"",CARIAN_JENIS_KPI,"HAKMILIK");
		    		}
				}			    	
		    	else if(CARIAN_JENIS_KPI.equals("2"))
				{
		    		if(diff_h_to_k > 2)
			    	{
			    		jenisBar = "delay";
			    	}
		    		
		    		if(diff_h_to_k > 0)
		    		{
		    			html += barChart(perc_h_gap,perc_h_to_k,perc_h_to_k_max,TARIKH_BORANGH,TARIKH_BORANGK,jenisBar,diff_h_to_k+"",ayat_diff_h_to_k,"",CARIAN_JENIS_KPI,"HAKMILIK");
		    		}
				}
		    	else if(CARIAN_JENIS_KPI.equals("3"))
				{
		    		if(diff_k_to_endosk > 2)
			    	{
			    		jenisBar = "delay";
			    	}
		    		
		    		if(diff_k_to_endosk > 0)
		    		{
		    			html += barChart(perc_k_gap,perc_k_to_endosk,perc_k_to_endosk_max,TARIKH_BORANGK,TARIKH_ENDOSK,jenisBar,diff_k_to_endosk+"",ayat_diff_k_to_endosk,ID_FAIL,CARIAN_JENIS_KPI,"HAKMILIK");
		    		}
				}
				
				html += "</td> ";
				
				html += "</tr>";
								
			}
		}
		else
		{
			html += "<tr><td align=\"left\" valign=\"top\" colspan=\"15\" >TIADA REKOD</td></tr> ";
		}
		html += "</table>";
		return html;
	}
	
	public String queryProjekBased(Db db,String skrinName) throws Exception
	{
		
		//jika melibatkan carian
		//untuk parameter carian, tolong tambah 'CARIAN_' didepan. untuk mengelak duplicate parameter dari main rekod
		
		String sql = " SELECT  MA.ID_FAIL, MA.NO_FAIL, MA.NO_RUJUKAN_PTD, MA.NO_RUJUKAN_PTG, MA.NO_RUJUKAN_UPT, "+
				" UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, KM.NAMA_KEMENTERIAN, "+
				" '[' || MA.NO_FAIL || ']' || '<br>' || CASE WHEN MA.NO_RUJUKAN_PTG IS NOT NULL THEN '[' || MA.NO_RUJUKAN_PTG || ']' || '<br>' ELSE '' END || " +
				" CASE WHEN MA.FLAG_JENIS_PROJEK = '1' "+
				" THEN '[JAJARAN]<br>' "+
				" WHEN MA.FLAG_JENIS_PROJEK = '2' "+
				" THEN '[TAPAK]<br>' ELSE '[JAJARAN]<br>' END || '<br>' ||  "+
				" UPPER(MA.TUJUAN) AS PROJEK, " +
				" TO_CHAR(MA.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN, "+
				" CASE WHEN MA.FLAG_JENIS_PROJEK = '1' "+
				" THEN 'JAJARAN' "+
				" WHEN MA.FLAG_JENIS_PROJEK = '2' "+
				" THEN 'TAPAK' ELSE 'JAJARAN' END AS JENIS_PROJEK, "+
				" COUNT(MA.ID_HAKMILIK) AS TOTAL_HAKMILIK, "+
				" COUNT(MA.TARIKH_BORANGH) AS TOTAL_HAKMILIK_H, "+
				" COUNT(MA.TARIKH_BORANGK) AS TOTAL_HAKMILIK_K, "+
				" COUNT(MA.TARIKH_ENDOSK) AS TOTAL_HAKMILIK_ENDOSK, "+
				" TO_CHAR(MAX(TO_DATE(MA.TARIKH_WARTA,'DD/MM/YYYY')),'DD/MM/YYYY') AS TARIKH_WARTA,  "+
				" REPLACE(MAX(MA.TEMPOH_WARTA_H),0,1) AS MAX_MONTH_WARTA_TO_H, "+
				" REPLACE(MAX(MA.TEMPOH_H_K),0,1) AS MAX_WEEK_H_TO_K, "+
				" REPLACE(MAX(MA.TEMPOH_K_ENDOS),0,1) AS MAX_WEEK_K_TO_ENDOS, "+
				" TO_CHAR(MAX(TO_DATE(MA.TARIKH_BORANGH,'DD/MM/YYYY')),'DD/MM/YYYY') AS MAX_TARIKH_BORANGH, "+  
				" TO_CHAR(MIN(TO_DATE(MA.TARIKH_BORANGH,'DD/MM/YYYY')),'DD/MM/YYYY') AS MIN_TARIKH_BORANGH, "+  
				" TO_CHAR(MAX(TO_DATE(MA.TARIKH_BORANGK,'DD/MM/YYYY')),'DD/MM/YYYY') AS MAX_TARIKH_BORANGK, "+  
				" TO_CHAR(MIN(TO_DATE(MA.TARIKH_BORANGK,'DD/MM/YYYY')),'DD/MM/YYYY') AS MIN_TARIKH_BORANGK, "+  
				" TO_CHAR(MAX(TO_DATE(MA.TARIKH_ENDOSK,'DD/MM/YYYY')),'DD/MM/YYYY') AS MAX_TARIKH_ENDOSK,  "+
				
				" SUM(TEMPOH_WARTA_H_MEET) AS TEMPOH_WARTA_H_MEET, "+
				" SUM(TEMPOH_H_K_MEET) AS TEMPOH_H_K_MEET, "+
				" SUM(TEMPOH_K_ENDOS_MEET ) AS TEMPOH_K_ENDOS_MEET "+
				
				" FROM ( "+
				
				" SELECT MAINLOT.*,  "+
				" (CASE WHEN DAYS_WARTA_H > 0 AND DAYS_WARTA_H <= 168 AND (FLAG_JENIS_PROJEK = 1 OR FLAG_JENIS_PROJEK IS NULL) THEN 1  "+
				" WHEN DAYS_WARTA_H > 0 AND DAYS_WARTA_H <= 112 AND FLAG_JENIS_PROJEK = 2 THEN 1  "+
				" ELSE 0 END) AS TEMPOH_WARTA_H_MEET,  "+
				" (CASE WHEN DAYS_H_K > 0 AND DAYS_H_K <= 14 THEN 1  "+
				" ELSE 0 END) AS TEMPOH_H_K_MEET,  "+
				" (CASE   "+
				" WHEN DAYS_K_ENDOS > 0 AND DAYS_K_ENDOS <= 14 THEN 1  "+
				" ELSE 0 END) AS TEMPOH_K_ENDOS_MEET   "+
				" FROM (   "+
				
				queryHakmilikBased(db,skrinName,"")+
				
				" ) MAINLOT "+
				
						" ) MA, TBLRUJNEGERI N, TBLRUJKEMENTERIAN KM "+
				" WHERE MA.ID_NEGERI = N.ID_NEGERI(+) AND MA.ID_KEMENTERIAN = KM.ID_KEMENTERIAN(+) AND MA.NO_FAIL IS NOT NULL AND MA.TARIKH_PERMOHONAN IS NOT NULL  ";
		
				//" AND MA.TARIKH_PERMOHONAN BETWEEN TO_DATE('01/2015','MM/YYYY') AND TO_DATE('12/2015','MM/YYYY') ";
				//" AND MA.ID_NEGERI = '2' ";
				//" AND MA.TARIKH_BORANGH IS NOT NULL "+
				//" AND MA.ID_KEMENTERIAN= '1' "+
				//" AND UPPER(MA.TUJUAN) LIKE UPPER('%NAIKTARAF%') "+				
		
				sql += " GROUP BY MA.ID_FAIL,MA.NO_FAIL,MA.NO_RUJUKAN_PTD, MA.NO_RUJUKAN_PTG, MA.NO_RUJUKAN_UPT, MA.FLAG_JENIS_PROJEK, "+
				" MA.TUJUAN, MA.TARIKH_PERMOHONAN, N.NAMA_NEGERI, KM.NAMA_KEMENTERIAN ";
				
				sql += " ORDER BY MA.TARIKH_PERMOHONAN ASC ";
				
				
				//" ORDER BY MA.TARIKH_PERMOHONAN ASC ";
				myLogger.info("QUERY PROJEK BASED : "+sql);
		
		return sql;
	}
	
	
	public String htmlProjekBased(String query,boolean flagCache,boolean resetCache, String cacheID,String skrinName, String div, 
			boolean showPaging, Db db) throws Exception
	{
		String CARIAN_JENIS_KPI = getParam(skrinName+"CARIAN_JENIS_KPI");
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
	    String html = "";
		List listProjekBased = RT.RTListRekod(query,
				flagCache, // list yg ada cache
				resetCache, // keperluan mereset cache
				cacheID, // unik cache ID
			skrinName, // nama list
			div,showPaging,
			"", // senarai nama column untuk  kita sort kan
			"", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
			"", // jenis sorting mengikut column
			"",
			0,
			db);
		
		List listMinMaxDate = RT.RTListRekod(queryMinMaxDate(db,skrinName),
				flagCache, // list yg ada cache
				resetCache, // keperluan mereset cache
				cacheID, // unik cache ID
			skrinName, // nama list
			div,showPaging,
			"", // senarai nama column untuk  kita sort kan
			"", // senarai data type untuk colmun [STRING,DATE,NUMBER] -- AKAN DITAMBAH KEMUDIAN
			"", // jenis sorting mengikut column
			"", // date format
			0,
			db);
		
		Map map_MinMaxDate = (Map) listMinMaxDate.get(0);
		double diff_warta_h = 0;
		double diff_h_k = 0;
		double diff_k_endos = 0;
		int INT_MAX_TEMPOH_WARTA_H = 0;
		int INT_MAX_TEMPOH_H_K= 0;
		int INT_MAX_TEMPOH_K_ENDOS = 0;
		String MIN_TARIKH_WARTA = "";
		String MAX_TARIKH_BORANGH = "";
		String MIN_TARIKH_BORANGH = "";
		String MAX_TARIKH_BORANGK = "";
		String MIN_TARIKH_BORANGK = "";
		String MAX_TARIKH_ENDOSK = "";
		String MAX_TEMPOH_WARTA_H = "";
		String MAX_TEMPOH_H_K = "";
		String MAX_TEMPOH_K_ENDOS = "";
		
		if(map_MinMaxDate != null)
		{
			MIN_TARIKH_WARTA = (String) map_MinMaxDate.get("MIN_TARIKH_WARTA");
			MAX_TARIKH_BORANGH = (String) map_MinMaxDate.get("MAX_TARIKH_BORANGH");
			MIN_TARIKH_BORANGH = (String) map_MinMaxDate.get("MIN_TARIKH_BORANGH");
			MAX_TARIKH_BORANGK = (String) map_MinMaxDate.get("MAX_TARIKH_BORANGK");
			MIN_TARIKH_BORANGK = (String) map_MinMaxDate.get("MIN_TARIKH_BORANGK");
			MAX_TARIKH_ENDOSK = (String) map_MinMaxDate.get("MAX_TARIKH_ENDOSK");			
			MAX_TEMPOH_WARTA_H = (String) map_MinMaxDate.get("MAX_TEMPOH_WARTA_H");
			MAX_TEMPOH_H_K = (String) map_MinMaxDate.get("MAX_TEMPOH_H_K");
			MAX_TEMPOH_K_ENDOS = (String) map_MinMaxDate.get("MAX_TEMPOH_K_ENDOS");
			
			if(!MAX_TEMPOH_WARTA_H.equals(""))
			{
				INT_MAX_TEMPOH_WARTA_H = Integer.parseInt(MAX_TEMPOH_WARTA_H);				
			}
			if(!MAX_TEMPOH_H_K.equals(""))
			{
				INT_MAX_TEMPOH_H_K = Integer.parseInt(MAX_TEMPOH_H_K);
			}
			if(!MAX_TEMPOH_K_ENDOS.equals(""))
			{
				INT_MAX_TEMPOH_K_ENDOS = Integer.parseInt(MAX_TEMPOH_K_ENDOS);
			}
			
			//-kumpul dlu semua maklumat sebelum nak buat gantt char
			//-kena cari formula untuk divide column space by % of max tempoh
			
			diff_warta_h = daysBetween2dates(MIN_TARIKH_WARTA, MAX_TARIKH_BORANGH, "MONTHS");
			diff_h_k = daysBetween2dates(MIN_TARIKH_BORANGH, MAX_TARIKH_BORANGK, "WEEKS");
			diff_k_endos = daysBetween2dates(MIN_TARIKH_BORANGK, MAX_TARIKH_ENDOSK, "WEEKS");
		    myLogger.info(">>>>>>>>>>>>>>> day diff_warta_h ["+MIN_TARIKH_WARTA+" --> "+MAX_TARIKH_BORANGH+"] : "+diff_warta_h);
		    myLogger.info(">>>>>>>>>>>>>>> day diff_h_k ["+MIN_TARIKH_BORANGH+" --> "+MAX_TARIKH_BORANGK+"] : "+diff_h_k);
		    myLogger.info(">>>>>>>>>>>>>>> day diff_k_endos ["+MIN_TARIKH_BORANGK+" --> "+MAX_TARIKH_ENDOSK+"] : "+diff_k_endos);
			
			myLogger.info("INT_MAX_TEMPOH_WARTA_H : "+INT_MAX_TEMPOH_WARTA_H+"; INT_MAX_TEMPOH_H_K : "+INT_MAX_TEMPOH_H_K+"; INT_MAX_TEMPOH_K_ENDOS : "+INT_MAX_TEMPOH_K_ENDOS);
		}
		myLogger.info("map_MinMaxDate :::: "+map_MinMaxDate);
		
		html += "<table border=\"0\" cellspacing=\"1\" cellpadding=\"3\" width=\"100%\" class='classFade' > ";
		html += "<tr  class=\"table_header\" >";
		html += "<td  align=\"center\" valign=\"top\" width=\"2%\" ><strong>BIL</strong></td>";
		html += "<td  align=\"left\" valign=\"top\" width=\"15%\" ><strong>PROJEK<br>[JENIS PROJEK]</strong></td>";
		html += "<td  align=\"center\" valign=\"top\" width=\"6%\" ><strong>TARIKH<br>PERMOHONAN</strong></td>";
		html += "<td  align=\"center\" valign=\"top\" width=\"6%\"><strong>TOTAL<br>HAKMILIK</strong></td>";
		
		String displayJenisHakmilikKPI = "";
	    if(CARIAN_JENIS_KPI.equals("1"))
		{
	    	displayJenisHakmilikKPI = "TOTAL<br>HAKMILIK<br>(BORANG H)";
		}
	    else if(CARIAN_JENIS_KPI.equals("2"))
		{
	    	displayJenisHakmilikKPI = "TOTAL<br>HAKMILIK<br>(BORANG K)";
		}
	    else if(CARIAN_JENIS_KPI.equals("3"))
		{
	    	displayJenisHakmilikKPI = "TOTAL<br>HAKMILIK<br>(ENDORSAN BORANG K)";
		}
		
		html += "<td  align=\"center\" valign=\"top\" width=\"6%\"><strong>"+displayJenisHakmilikKPI+"</strong></td>";
		String jenisKPI = "";
		if(CARIAN_JENIS_KPI.equals("1"))
		{
			jenisKPI = "TEMPOH WARTA SEHINGGA BORANG H (BULAN)";
		}
		else if(CARIAN_JENIS_KPI.equals("2"))
		{
			jenisKPI = "TEMPOH BORANG H SEHINGGA BORANG K (MINGGU)";
		}
		else if(CARIAN_JENIS_KPI.equals("3"))
		{
			jenisKPI = "TEMPOH BORANG K SEHINGGA ENDORSAN BORANG K (MINGGU)";
		}
		html += "<td  align=\"left\" valign=\"top\" width=\"65%\"><strong>"+jenisKPI+"</strong></td>";
		
		html += "</tr>";
		
		if (listProjekBased.size() > 0) {	
			for (int i = 0; i < listProjekBased.size(); i++) {
				Map map_rekod = (Map) listProjekBased.get(i);
				String ID_FAIL = (String) map_rekod.get("ID_FAIL");
				String PROJEK = (String) map_rekod.get("PROJEK");
				String NO_FAIL = (String) map_rekod.get("NO_FAIL");
				String MAX_MONTH_WARTA_TO_H = (String) map_rekod.get("MAX_MONTH_WARTA_TO_H");
				String TOTAL_HAKMILIK = (String) map_rekod.get("TOTAL_HAKMILIK");
				String TOTAL_HAKMILIK_H = (String) map_rekod.get("TOTAL_HAKMILIK_H");
				String TOTAL_HAKMILIK_K = (String) map_rekod.get("TOTAL_HAKMILIK_K");
				String TOTAL_HAKMILIK_ENDOSK = (String) map_rekod.get("TOTAL_HAKMILIK_ENDOSK");
				String MAX_WEEK_H_TO_K = (String) map_rekod.get("MAX_WEEK_H_TO_K");
				String MAX_WEEK_K_TO_ENDOS = (String) map_rekod.get("MAX_WEEK_K_TO_ENDOS");
				String TARIKH_WARTA = (String) map_rekod.get("TARIKH_WARTA");
				String TARIKH_PERMOHONAN = (String) map_rekod.get("TARIKH_PERMOHONAN");
				String MAX_TARIKH_BORANGH_P = (String) map_rekod.get("MAX_TARIKH_BORANGH");
				String MIN_TARIKH_BORANGH_P = (String) map_rekod.get("MIN_TARIKH_BORANGH");
				String MAX_TARIKH_BORANGK_P = (String) map_rekod.get("MAX_TARIKH_BORANGK");
				String MIN_TARIKH_BORANGK_P = (String) map_rekod.get("MIN_TARIKH_BORANGK");
				String MAX_TARIKH_ENDOSK_P = (String) map_rekod.get("MAX_TARIKH_ENDOSK");
				String JENIS_PROJEK = (String) map_rekod.get("JENIS_PROJEK");				
				
				double diff_warta_gap = daysBetween2dates(MIN_TARIKH_WARTA, TARIKH_WARTA, "MONTHS");
				double diff_warta_to_h = daysBetween2dates(TARIKH_WARTA, MAX_TARIKH_BORANGH_P, "MONTHS");
				String ayat_diff_warta_to_h = ayatBetween2dates(TARIKH_WARTA, MAX_TARIKH_BORANGH_P, "MONTHS");
				myLogger.info("diff_warta_to_h >>>>>>> "+diff_warta_to_h);
				
				double diff_h_gap = daysBetween2dates(MIN_TARIKH_BORANGH, MIN_TARIKH_BORANGH_P, "WEEKS");
				double diff_h_to_k = daysBetween2dates(MIN_TARIKH_BORANGH_P, MAX_TARIKH_BORANGK_P, "WEEKS");
				String ayat_diff_h_to_k = ayatBetween2dates(MIN_TARIKH_BORANGH_P, MAX_TARIKH_BORANGK_P, "WEEKS");
				myLogger.info("diff_h_to_k >>>>>>> "+diff_h_to_k);
				
				double diff_k_gap = daysBetween2dates(MIN_TARIKH_BORANGK, MIN_TARIKH_BORANGK_P, "WEEKS");				
				double diff_k_to_endosk = daysBetween2dates(MIN_TARIKH_BORANGK_P, MAX_TARIKH_ENDOSK_P, "WEEKS");
				String ayat_diff_k_to_endosk = ayatBetween2dates(MIN_TARIKH_BORANGK_P, MAX_TARIKH_ENDOSK_P, "WEEKS");
				myLogger.info("diff_k_to_endosk >>>>>>> "+diff_k_to_endosk);
				
				double perc_warta_gap = percentageGap(diff_warta_gap, diff_warta_h);
				double perc_warta_to_h = percentageGap(diff_warta_to_h, diff_warta_h);
				
				double max_tempoh_h = 4;
				if(JENIS_PROJEK.equals("JAJARAN"))
				{
					max_tempoh_h = 6;
				}
				
				double perc_warta_to_h_max = percentageGap(max_tempoh_h, diff_warta_h);
				double perc_h_gap = percentageGap(diff_h_gap, diff_h_k);
				double perc_h_to_k = percentageGap(diff_h_to_k, diff_h_k);
				double perc_h_to_k_max = percentageGap(2, diff_h_k);
				double perc_k_gap = percentageGap(diff_k_gap, diff_k_endos);
				double perc_k_to_endosk = percentageGap(diff_k_to_endosk, diff_k_endos);
				double perc_k_to_endosk_max = percentageGap(2, diff_k_endos);
				
				//myLogger.info("NO_FAIL : "+NO_FAIL+" >>>> totalgap : "+diff_warta_h+"; diff_warta_gap : "+diff_warta_gap+"; diff_warta_to_h : "+diff_warta_to_h+"; diff_h_to_k : "+diff_h_to_k+"; diff_k_to_endosk : "+diff_k_to_endosk);
				//myLogger.info("PERSENTAGE NO_FAIL : "+NO_FAIL+" >>>> totalgap : "+diff_warta_h+"; diff_warta_gap : "+perc_warta_gap+"%; diff_warta_to_h : "+perc_warta_to_h+"%; diff_h_to_k : "+perc_h_to_k+"%; diff_k_to_endosk : "+perc_k_to_endosk+"%");
				   
			    html += "<tr>";
			    html += "<td id=\"tdRowSpan"+ID_FAIL+"\" rowspan=\"1\" class=\"row2\"  align=\"center\" valign=\"top\" >"+(i+1)+"</td>";
			    html += "<td class=\"row2\" align=\"left\" valign=\"top\">"+PROJEK+"&nbsp;<b>["+JENIS_PROJEK+"]</b></td>";
			    html += "<td class=\"row2\" align=\"center\" valign=\"top\">"+TARIKH_PERMOHONAN+"</td>";
			    html += "<td class=\"row2\" align=\"center\" valign=\"top\">"+TOTAL_HAKMILIK+"</td>";
			    
			    String displayTotalHakmilikKPI = "";
			    if(CARIAN_JENIS_KPI.equals("1"))
				{
			    	displayTotalHakmilikKPI = TOTAL_HAKMILIK_H;
				}
			    else if(CARIAN_JENIS_KPI.equals("2"))
				{
			    	displayTotalHakmilikKPI = TOTAL_HAKMILIK_K;
				}
			    else if(CARIAN_JENIS_KPI.equals("3"))
				{
			    	displayTotalHakmilikKPI = TOTAL_HAKMILIK_ENDOSK;
				}
			    
			    html += "<td class=\"row2\" align=\"center\" valign=\"top\">"+displayTotalHakmilikKPI+"</td>";
			    html += "<td class=\"row2\" align=\"left\" valign=\"top\">";
			    
			    	String jenisBar = "normal";
			    	
			    	
			    	if(CARIAN_JENIS_KPI.equals("1"))
					{
			    		if((diff_warta_to_h > 6 && JENIS_PROJEK.equals("JAJARAN")) || (diff_warta_to_h > 4 && JENIS_PROJEK.equals("TAPAK")))
				    	{
				    		jenisBar = "delay";
				    	}
			    		
			    		if(diff_warta_to_h > 0)
			    		{  
			    			html += barChart(perc_warta_gap,perc_warta_to_h,perc_warta_to_h_max,TARIKH_WARTA,MAX_TARIKH_BORANGH_P,jenisBar,diff_warta_to_h+"",ayat_diff_warta_to_h,ID_FAIL,CARIAN_JENIS_KPI,"PROJEK");
			    		}
					}			    	
			    	else if(CARIAN_JENIS_KPI.equals("2"))
					{
			    		if(diff_h_to_k > 2)
				    	{
				    		jenisBar = "delay";
				    	}
			    		
			    		if(diff_h_to_k > 0)
			    		{
			    			html += barChart(perc_h_gap,perc_h_to_k,perc_h_to_k_max,MIN_TARIKH_BORANGH_P,MAX_TARIKH_BORANGK_P,jenisBar,diff_h_to_k+"",ayat_diff_h_to_k,ID_FAIL,CARIAN_JENIS_KPI,"PROJEK");
			    		}
					}
			    	else if(CARIAN_JENIS_KPI.equals("3"))
					{
			    		if(diff_k_to_endosk > 2)
				    	{
				    		jenisBar = "delay";
				    	}
			    		
			    		if(diff_k_to_endosk > 0)
			    		{
			    			html += barChart(perc_k_gap,perc_k_to_endosk,perc_k_to_endosk_max,MIN_TARIKH_BORANGK_P,MAX_TARIKH_ENDOSK_P,jenisBar,diff_k_to_endosk+"",ayat_diff_k_to_endosk,ID_FAIL,CARIAN_JENIS_KPI,"PROJEK");
			    		}
					}
			    	
			    	 html += "<div id=\"div_hakmilikBased"+ID_FAIL+"\" ></div><input type=\"hidden\" id=\"setOpenClose"+ID_FAIL+"\" name=\"setOpenClose"+ID_FAIL+"\" value=\"close\" >";
						
			    
			    html += "</td>";
			    
			    html += "</tr>";
			    
			    /*
			    html += "<tr id=\"trHakmilikBase"+ID_FAIL+"\" style=\"display:none;\" >";
			    html += "<td class=\"row2\" colspan=\"5\">";
			    html += "<div id=\"div_hakmilikBased"+ID_FAIL+"\" ></div><input type=\"hidden\" id=\"setOpenClose"+ID_FAIL+"\" name=\"setOpenClose"+ID_FAIL+"\" value=\"close\" >";
				html += "</td>";
			    html += "</tr>";
			    */
			    
			    /*
			    html += "<tr>";
			    html += "<td ></td>";
			    html += "<td ></td>";
			    html += "<td colspan=\"3\"  >";
			    html += "<div id=\"div_hakmilikBased"+ID_FAIL+"\" ></div><input type=\"hidden\" id=\"setOpenClose"+ID_FAIL+"\" name=\"setOpenClose"+ID_FAIL+"\" value=\"close\" >";
			    html += "</td>";
			    html += "</tr>";
				*/
			}
		}	
		else
		{
			html += "<tr><td align=\"left\" valign=\"top\" colspan=\"15\" >TIADA REKOD</td></tr> ";
		}
			html += "</table>";
		return html;
	}
	
	public String HTMLSkrinCarian(Db db, String skrinName) throws Exception
	{ 
		String html = "";
		html += "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > ";
		html += "<tr><td width='50%' valign=top>";
		html += RT.openHTMLTable();
		
		html += RT.setRowTextCarian(skrinName,//skrinName
				"No. Fail",//label field 
				"CARIAN_NO_FAIL",// nama & id field, sama kan dengan nama filed dalam DB
				100,//setting maxlength
				"",//default value
				"",//apa js yang dipanggil semasa onblur
				"",//jika field ini kan menggunakan datalist
				db //db object
				);
		
		
		
		html += RT.setRowSelectHC(skrinName,//skrinName
				true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				"edit",//mode : edit/view
				null,//set data
				"Jenis KPI",//label field 
				"CARIAN_JENIS_KPI",// nama & id field, sama kan dengan nama filed dalam DB
				"HC_JENIS_KPI_PPT",// nama list hard code
				true,// adakah field ini mandatory true=ya
				"select",// hidden/select/radio
				true,// display : diantara label & field
				true,//defaultUppercase pada value input
				"",//default value
				true,//adalah field ini untuk kegunaan carian
				"",//apa js yang dipanggil semasa onChange
				db //db object
				);
		
		html += RT.setRowSelect(skrinName,//skrinName
				true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				"edit",//mode : edit/view
				null,//set data
				"Negeri",//label field 
				"CARIAN_NEGERI",// nama & id field, sama kan dengan nama filed dalam DB
				false,// adakah field ini mandatory true=ya
				"select",// hidden/select/radio
				true,// display : diantara label & field
				true,//defaultUppercase pada value input
				"",//default value
				true,//adalah field ini untuk kegunaan carian
				"",//apa js yang dipanggil semasa onChange
				"TBLRUJNEGERI",//ref table
				"ID_NEGERI",// value to display
				"NAMA_NEGERI",// keterangan to display 
				" ID_NEGERI NOT IN (12,13,1,17,0) ", // filter
				"KOD_NEGERI ASC",//order by
				db //db object
				);
		
		html += RT.closeHTMLTable();	
		html += "</td><td width='50%' valign=top>";
		
		html += RT.openHTMLTable();
		html += RT.setRowTextCarian(skrinName,"Projek","CARIAN_PROJEK",1000,"","","",db);
		
		html += RT.setRowSelect(skrinName,//skrinName
				true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				"edit",//mode : edit/view
				null,//set data
				"Tahun Permohonan (Mula)",//label field 
				"CARIAN_TAHUN_MULA",// nama & id field, sama kan dengan nama filed dalam DB
				true,// adakah field ini mandatory true=ya
				"select",// hidden/select/radio
				true,// display : diantara label & field
				true,//defaultUppercase pada value input
				"",//default value
				true,//adalah field ini untuk kegunaan carian
				"setCopyToOther(this.value,'"+skrinName+"CARIAN_TAHUN_AKHIR')",//apa js yang dipanggil semasa onChange
				"TBLPPTPERMOHONAN P",//ref table
				"TO_NUMBER(TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY'))",// value to display
				"TO_NUMBER(TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY'))",// keterangan to display 
				"", // filter
				"TO_NUMBER(TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY')) ASC",//order by
				db //db object
				);
		html += RT.setRowSelect(skrinName,//skrinName
				true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				"edit",//mode : edit/view
				null,//set data
				"Tahun Permohonan (Akhir)",//label field 
				"CARIAN_TAHUN_AKHIR",// nama & id field, sama kan dengan nama filed dalam DB
				true,// adakah field ini mandatory true=ya
				"select",// hidden/select/radio
				true,// display : diantara label & field
				true,//defaultUppercase pada value input
				"",//default value
				true,//adalah field ini untuk kegunaan carian
				"",//apa js yang dipanggil semasa onChange
				"TBLPPTPERMOHONAN P",//ref table
				"TO_NUMBER(TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY'))",// value to display
				"TO_NUMBER(TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY'))",// keterangan to display 
				"", // filter
				"TO_NUMBER(TO_CHAR(P.TARIKH_PERMOHONAN,'YYYY')) ASC",//order by
				db //db object
				);
		/*
		html += RT.setRowTextCarian(skrinName,//skrinName
				"Id Fail",//label field 
				"CARIAN_ID_FAIL",// nama & id field, sama kan dengan nama filed dalam DB
				100,//setting maxlength
				"",//default value
				"",//apa js yang dipanggil semasa onblur
				"",//jika field ini kan menggunakan datalist
				db //db object
				);	
		*/			
		html += RT.closeHTMLTable();
	    
		html += "</td></tr></table>";
		html += setupButtonCarian(skrinName);
		html += "";
		return html;
	}
	
	public String setupButtonCarian(String skrinName) throws Exception {
		
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<tr><td width='100%'  align='center'  style='border-top: 1px solid #000;'>";
		html += " <input type=\"button\" id=\"cmdCari"+skrinName+"\" name=\"cmdCari"+skrinName+"\" value=\"Cari\" onClick=\"if(checkSkrinCarian('"+skrinName+"') == true){"+RT.ajaxCallFunction("showProjekBased","div_projekBased",skrinName,"&div=div_projekBased&skrinName=projekBased")+"}\" >";
		html += " <input type=\"button\" id=\"cmdReset"+skrinName+"\" name=\"cmdReset"+skrinName+"\" value=\"Reset\" onClick=\"resetSkrinCarian('"+skrinName+"');"+RT.ajaxCallFunction("showSenaraiUtama","div_carianUtama",skrinName,"")+"\" >";
		html +=	"</td>";
		html += "</tr></table>";		
		return html;
	}
	
	
	public String barChart(double percent1,double percent2, double percent3, String tarikhMula, String tarikhAkhir, String type, String gap,String ayat_gap, String ID_FAIL,String JENIS_KPI,String typeBar)
	{
		
		double remaining_perc = 100 - (percent1+percent2);
		remaining_perc = round(remaining_perc,2);
		if(remaining_perc < 1)
		{
			remaining_perc = 1;
		}
		String cssTypeBar = "";
		String cssDefaultNormalInDelay = "";
		String cssTypeBorderAwal = "";
		String cssTypeBorderAkhir = "";
		int colspan = 0;
		if(typeBar.equals("PROJEK"))
		{
			if(type.equals("normal"))
			{
				colspan = 2;
				cssTypeBar = "normalBar showCursor overlayBG";
				cssTypeBorderAwal = "normalBorderAwal";
				cssTypeBorderAkhir = "normalBorderAkhir";
			}
			else if(type.equals("delay"))
			{
				colspan = 3;
				cssTypeBar = "delayBar showCursor overlayBG";
				cssTypeBorderAwal = "delayBorderAwal";
				cssTypeBorderAkhir = "delayBorderAkhir";
				cssDefaultNormalInDelay = "normalBar showCursor overlayBG";
			}
		}
		else
		{
			if(type.equals("normal"))
			{
				colspan = 2;
				cssTypeBar = "normalBarHM overlayBG";
				cssTypeBorderAwal = "normalBorderAwal";
				cssTypeBorderAkhir = "normalBorderAkhir";
			}
			else if(type.equals("delay"))
			{
				colspan = 3;
				cssTypeBar = "delayBarHM overlayBG";
				cssTypeBorderAwal = "delayBorderAwal";
				cssTypeBorderAkhir = "delayBorderAkhir";
				cssDefaultNormalInDelay = "normalBarHM overlayBG";
			}
		}
		//myLogger.info(">>>>>>>> percent1 : "+percent1+"; percent2 : "+percent2+"; remaining_perc : "+remaining_perc+";");
		
		
		
		String html = "";
		html += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"1\" width=\"100%\"  >";
		html += "<tr>";
		html += "<td ></td>";
		html += "<td colspan=\""+colspan+"\" align=\"left\" class=\"normalBorderAwal\" >"+tarikhMula+"";
		
		String onClickBar = "";
		if(typeBar.equals("PROJEK"))
		{
			html += "<input type=\"hidden\" id=\"minDateGC_"+ID_FAIL+"_"+JENIS_KPI+"\" name=\"minDateGC_"+ID_FAIL+"_"+JENIS_KPI+"\" value=\""+tarikhMula+"\">";
			onClickBar = " onClick=\"openHakmilikBased('"+ID_FAIL+"')\" ";
		}	
		html += "</td>";
		html += "</tr>";
		html += "<tr>";
		html += "<td width=\""+percent1+"%\"></td>";
		
		
		if(type.equals("delay"))
		{
			html += "<td " +onClickBar+ " width=\""+percent3+"%\"  class=\""+cssDefaultNormalInDelay+"\"><div style=\"width:0; overflow:visible; white-space:nowrap;\"><b>"+ayat_gap+"</b></div></td>";
			html += "<td " +onClickBar+" width=\""+round((percent2-percent3),2)+"%\"  class=\""+cssTypeBar+"\"></td>";
		}
		else
		{
			html += "<td "+onClickBar+" width=\""+percent2+"%\"  class=\""+cssTypeBar+"\"><div style=\"width:0; overflow:visible; white-space:nowrap;\"><b>"+ayat_gap+"</b></div></td>";
		}
		html += "<td width=\""+remaining_perc+"%\"></td>";
		html += "</tr>";
		html += "<tr>";		
		html += "<td colspan=\""+colspan+"\" align=\"right\" class=\""+cssTypeBorderAkhir+"\" >"+tarikhAkhir+"";
		if(typeBar.equals("PROJEK"))
		{
			html += "<input type=\"hidden\" id=\"maxDateGC_"+ID_FAIL+"_"+JENIS_KPI+"\" name=\"maxDateGC_"+ID_FAIL+"_"+JENIS_KPI+"\" value=\""+tarikhAkhir+"\">";
		}
		html += "</td>";
		html += "<td ></td>";
		html += "</tr>";
		
		
		
		html += "</table>";
		
		return html;
	}
		
	
	public double percentageGap(double val1, double val2)
	{
		//myLogger.info("value 1 : "+val1+" value 2 : "+val2);
		double percentage = 0.0;
		if(val2 > 0)
		{
			double c =(val1 * 100.0f) / val2;
			//myLogger.info("value 1 : "+val1+" value 2 : "+val2+" calc : "+c);
			percentage = round(c,2);
		}
		if(percentage < 1)
		{
			percentage = 1;
		}
		return percentage;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public double daysBetween2dates(String tarikhAwal, String tarikhAkhir, String jenisUnit) throws ParseException
	{
		
		double diff = 0.0;
		if(!tarikhAwal.equals("") && !tarikhAkhir.equals(""))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		    Date firstDate = sdf.parse(tarikhAwal);
		    Date secondDate = sdf.parse(tarikhAkhir);		 
		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		    		    	
		    long diff_cals = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);	
		    
		    if(jenisUnit.equals("DAYS"))
		    {
		    	diff = diff_cals;
		    }
		    else if(jenisUnit.equals("MONTHS"))
		    {
		    	diff = round((diff_cals / 28.0),2);
		    	myLogger.info(diff + " >>> " + ((int) (diff_cals / 28.0)) + " > BAIK HARI DALAM MONTHS : "+diff_cals % 28.0);
		    }
		    else if(jenisUnit.equals("WEEKS"))
		    {
		    	diff = round((diff_cals / 7.0),2);
		    	myLogger.info(diff + " >>> " + ((int) (diff_cals / 7.0)) + " > BAIK HARI DALAM WEEKS : "+diff_cals % 7);
		    }		    
		}
		//myLogger.info(" tarikhAwal : "+tarikhAwal+" >>>>>>>>>>>>>> tarikhAkhir : "+tarikhAkhir+" jenisUnit : "+jenisUnit+" diff : "+diff);
		
	    return diff;
	}
	
	
	public String ayatBetween2dates(String tarikhAwal, String tarikhAkhir, String jenisUnit) throws ParseException
	{
		String ayat = "";
		double diff = 0.0;
		if(!tarikhAwal.equals("") && !tarikhAkhir.equals(""))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		    Date firstDate = sdf.parse(tarikhAwal);
		    Date secondDate = sdf.parse(tarikhAkhir);		 
		    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		    		    	
		    long diff_cals = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);	
		    
		    if(jenisUnit.equals("DAYS"))
		    {
		    	diff = diff_cals;
		    	ayat = diff + "H";
		    }
		    else if(jenisUnit.equals("MONTHS"))
		    {
		    	diff = round((diff_cals / 28),2);
		    	myLogger.info(diff + " >>> " + ((int) (diff_cals / 28)) + " > BAIK HARI DALAM MONTHS : "+diff_cals % 28);
		    	ayat = ((int) (diff_cals / 28)) + "B ";
		    	if((diff_cals % 28) > 0)
		    	{
		    		ayat += (diff_cals % 28)+"H";
		    	}
		    	
		    }
		    else if(jenisUnit.equals("WEEKS"))
		    {
		    	diff = round((diff_cals / 7),2);
		    	myLogger.info(diff + " >>> " + ((int) (diff_cals / 7)) + " > BAIK HARI DALAM WEEKS : "+diff_cals % 7);
		    	ayat = ((int) (diff_cals / 7)) + "M ";
		    	if((diff_cals % 7) > 0)
		    	{
		    		ayat += (diff_cals % 7)+"H";
		    	}
		    }		    
		}
		//myLogger.info(" tarikhAwal : "+tarikhAwal+" >>>>>>>>>>>>>> tarikhAkhir : "+tarikhAkhir+" jenisUnit : "+jenisUnit+" diff : "+diff);
		
	    return ayat;
	}
	
	
	
	public void defaultContextPut()
	{
		//segala context.put yang digunapakai dalam class ni mesti di initiate
		context.put("listProjekBased", "");		
	}

}
