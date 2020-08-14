package ekptg.model.ppk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.model.RazTemplete;

public class MyInfoPPKModel  extends VTemplate{
	
	//extends razTemplete
	public RazTemplete RT = new RazTemplete();

	public Logger myLogger = Logger.getLogger(ekptg.model.ppk.MyInfoPPKModel.class);
	public HttpSession session;
	public String modul = "";
	
	//setting environtment untuk kegunaan controller, 
	//betujuan untuk kita mantain environment setting
	public void setMyInfoPPKModel(VelocityEngine engine
		, VelocityContext context, HttpServletRequest request, HttpServletResponse response,HttpSession session,String modul){
        this.engine = engine;
        this.context = context;
        this.request = request;
        this.response = response;
        this.session = session;
        this.modul = modul;
        //kena call main environment dalam RazTempelete, untuk memastikan clases inherite each others
        RT.setEnvironmentRT(this.engine, this.context, this.request, this.response, this.session, this.modul);	
    }
	
	public void test()
	{
		 myLogger.info("MyInfoPPKModel >>>>>>>> modul : "+modul);
	}
	
	
	//disini, kita setting skrin carian, 
	//ongoing, sy akan setup mcm2 untuk templete ini bagi kegunaan semua
	public String HTMLSkrinCarian(Db db, String skrinName, String modul) throws Exception
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
		html += RT.setRowTextCarian(skrinName,"Nama Simati","CARIAN_NAMA_SIMATI",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,"MyID Simati","CARIAN_PENGENALAN_SIMATI",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,"Nama Pemohon","CARIAN_NAMA_PEMOHON",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,"MyID Pemohon","CARIAN_PENGENALAN_PEMOHON",100,"","","",db);
		
		html += RT.setRowSelectCarian(skrinName,//skrinName
				"Status Permohonan",//label field y
				"CARIAN_STATUS",// nama & id field, sama kan dengan nama filed dalam DB
				"select",// hidden/select/radio
				"",//default value
				"",//apa js yang dipanggil semasa onchange
				"",//apa js yang dipanggil masa load jsp
				"TBLRUJSTATUS",//nama table rujukan
				"ID_STATUS",//nama field PK
				"KOD_STATUS",//nama field KOD jika ada
				"KETERANGAN",//nama field keterangan
				db //db object
				);
		html += RT.closeHTMLTable();	
		html += "</td><td width='50%' valign=top>";
		html += RT.openHTMLTable();
		html += RT.setRowTextCarian(skrinName,"No. Hakmilik","CARIAN_NO_HAKMILIK",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,"No. Lot","CARIAN_NO_PT",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,"No. Sijil Mati","CARIAN_NO_SIJILMATI",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,"Pendaftar Fail","CARIAN_PENDAFTAR",100,"","","",db);
		html += RT.setRowTarikhCarian(skrinName,"Tarikh Permohonan","CARIAN_TARIKH_MOHON",100,"","","",db);
		html += RT.setRowTarikhCarian(skrinName,"Tarikh Perbicaraan","CARIAN_TARIKH_BICARA",100,"","","",db);
		html += RT.closeHTMLTable();
		html += "</td></tr></table>";
		html += setupButtonCarian(skrinName);
		html += "";
		return html;
	}
	
	
	public String setupButtonCarian(String skrinName) throws Exception {
		
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<tr><td width='100%'  align='center'>";
		html += " <input type=\"button\" id=\"cmdCari"+skrinName+"\" name=\"cmdCari"+skrinName+"\" value=\"Cari\" onClick=\"if(checkSkrinCarian('"+skrinName+"') == true){"+RT.ajaxCallFunction("carianSenarai","div_myinfoppk_list", skrinName,"")+"}\" >";
		html += " <input type=\"button\" id=\"cmdReset"+skrinName+"\" name=\"cmdReset"+skrinName+"\" value=\"Reset\" onClick=\"resetSkrinCarian('"+skrinName+"');"+RT.ajaxCallFunction("showSenarai","div_myinfoppk_list",  skrinName,"")+"\" >";
		html +=	"</td>";
		html += "</tr></table>";		
		return html;
	}
	
	
	//setting custom query untuk  carian
	//disini boleh accept parameter carian
	//tolong hati2 dengan query, make sure table2 are properly indexed
	public String querySenaraiFail(Db db,String skrinName) throws Exception
	{
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		
		//jika melibatkan carian
		//untuk parameter carian, tolong tambah 'CARIAN_' didepan. untuk mengelak duplicate parameter dari main rekod
		String CARIAN_NO_FAIL = getParam(skrinName+"CARIAN_NO_FAIL");
		String WCCARIAN_NO_FAIL = getParam("WC"+skrinName+"CARIAN_NO_FAIL");
		String CARIAN_NAMA_SIMATI = getParam(skrinName+"CARIAN_NAMA_SIMATI");
		String WCCARIAN_NAMA_SIMATI = getParam("WC"+skrinName+"CARIAN_NAMA_SIMATI");
		String CARIAN_PENGENALAN_SIMATI = getParam(skrinName+"CARIAN_PENGENALAN_SIMATI");
		String WCCARIAN_PENGENALAN_SIMATI = getParam("WC"+skrinName+"CARIAN_PENGENALAN_SIMATI");
		String CARIAN_NAMA_PEMOHON = getParam(skrinName+"CARIAN_NAMA_PEMOHON");
		String WCCARIAN_NAMA_PEMOHON = getParam("WC"+skrinName+"CARIAN_NAMA_PEMOHON");
		String CARIAN_PENGENALAN_PEMOHON = getParam(skrinName+"CARIAN_PENGENALAN_PEMOHON");
		String WCCARIAN_PENGENALAN_PEMOHON = getParam("WC"+skrinName+"CARIAN_PENGENALAN_PEMOHON");
		String CARIAN_NO_HAKMILIK = getParam(skrinName+"CARIAN_NO_HAKMILIK");
		String WCCARIAN_NO_HAKMILIK = getParam("WC"+skrinName+"CARIAN_NO_HAKMILIK");
		String CARIAN_NO_PT = getParam(skrinName+"CARIAN_NO_PT");
		String WCCARIAN_NO_PT = getParam("WC"+skrinName+"CARIAN_NO_PT");
		String CARIAN_NO_SIJILMATI = getParam(skrinName+"CARIAN_NO_SIJILMATI");
		String WCCARIAN_NO_SIJILMATI = getParam("WC"+skrinName+"CARIAN_NO_SIJILMATI");
		String CARIAN_TARIKH_MOHON = getParam(skrinName+"CARIAN_TARIKH_MOHON");
		String CARIAN_TARIKH_BICARA = getParam(skrinName+"CARIAN_TARIKH_BICARA");
		String CARIAN_STATUS = getParam(skrinName+"CARIAN_STATUS");
		String CARIAN_PENDAFTAR = getParam(skrinName+"CARIAN_PENDAFTAR");
		String WCCARIAN_PENDAFTAR = getParam("WC"+skrinName+"CARIAN_PENDAFTAR");
		/*
		myLogger.info("CARIAN NO FAIL : "+CARIAN_NO_FAIL+" WCCARIAN_NO_FAIL : "+WCCARIAN_NO_FAIL);
		myLogger.info("CARIAN PENGENALAN SIMATI : "+CARIAN_PENGENALAN_SIMATI+" WCCARIAN_PENGENALAN_SIMATI : "+WCCARIAN_PENGENALAN_SIMATI);
		myLogger.info("CARIAN PEMOHON : "+CARIAN_NAMA_PEMOHON+" WCCARIAN_NAMA_PEMOHON : "+WCCARIAN_NAMA_PEMOHON);
		myLogger.info("CARIAN PENGENALAN PEMOHON : "+CARIAN_PENGENALAN_PEMOHON+" WCCARIAN_PENGENALAN_PEMOHON : "+WCCARIAN_PENGENALAN_PEMOHON);
		myLogger.info("CARIAN_NO_HAKLMILIK : "+CARIAN_NO_HAKMILIK+" WCCARIAN_NO_HAKMILIK : "+WCCARIAN_NO_HAKMILIK);
		myLogger.info("CARIAN SIMATI : "+CARIAN_NAMA_SIMATI+" WCCARIAN_NAMA_SIMATI : "+WCCARIAN_NAMA_SIMATI);
		myLogger.info("CARIAN_NO_PT : "+CARIAN_NO_PT+" WCCARIAN_NO_PT : "+WCCARIAN_NO_PT);
		myLogger.info("CARIAN_NO_SIJILMATI : "+CARIAN_NO_SIJILMATI+" WCCARIAN_NO_SIJILMATI : "+WCCARIAN_NO_SIJILMATI);
		myLogger.info("CARIAN_PENDAFTAR : "+CARIAN_PENDAFTAR+" WCCARIAN_PENDAFTAR : "+WCCARIAN_PENDAFTAR);
		*/
		String sql = " SELECT DISTINCT F.NO_FAIL, SM.NAMA_SIMATI, PM.NAMA_PEMOHON, TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, ST.KETERANGAN AS STATUS, P.SEKSYEN, P.ID_STATUS, "+
				" PSM.ID_PERMOHONANSIMATI, F.ID_FAIL, F.TARIKH_DAFTAR_FAIL, F.FLAG_JENIS_FAIL, "+ 
				" P.ID_PERMOHONAN, BC.PETISYEN_NO,SM.ID_SIMATI, "+
				" PEND.PENDAFTAR AS PENDAFTAR  "+
				" FROM  "+
				" TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLRUJSTATUS ST, "+
				" (SELECT SUTF.ID_FAIL, UU.USER_NAME AS PENDAFTAR, UU.USER_LOGIN FROM USERS UU, TBLRUJSUBURUSANSTATUSFAIL SUTF  "+
				" WHERE UU.USER_ID = SUTF.ID_MASUK AND SUTF.ID_SUBURUSANSTATUS IN (340, 430)) PEND, "+
				" (SELECT K.IDKEPUTUSAN AS ID_KEPUTUSAN,K.KEPUTUSANBORANGC AS KEPUTUSAN_BORANG_C,K.TARIKHJANABORANGC AS TARIKH_JANA_BORANG_C, "+
				" K.IDKADBIRU AS ID_KAD_BIRU,K.JENISTRANSAKSI AS JENIS_TRANSAKSI,K.TARIKHPROSES AS TARIKH_PROSES, "+
				" K.BORANGCEXTRACTKOD AS EXTRACTION_KOD,K.FLAG_REP AS FLAG_REP, "+
				" K.TARIKH_REP AS TARIKH_REP,M.PETISYENNO AS PETISYEN_NO FROM TBLINTMTKEPUTUSAN K, TBLINTMTPERMOHONAN M "+
				" WHERE M.FLAG_REP = '3' AND K.IDKADBIRU = M.IDKADBIRU) BC "+
				" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PEMOHON = PM.ID_PEMOHON AND F.ID_FAIL = PEND.ID_FAIL(+) "+
				" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND SM.ID_SIMATI = PSM.ID_SIMATI AND P.ID_STATUS = ST.ID_STATUS "+
				" AND F.NO_FAIL = BC.PETISYEN_NO(+) "+
				" AND P.ID_DAERAHMHN IN ( "+
				" SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U,USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG "+
				" AND UR.USER_ID = '"+USER_ID_SYSTEM+"' "+
				" UNION "+
				" SELECT DISTINCT PBU_U.ID_DAERAHURUS FROM TBLPERMOHONANBANTUUNIT PBU, "+
				" TBLRUJPEJABATURUSAN PBU_U WHERE ID_STATUS = 2 "+
				" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >=TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+
				" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <=TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+
				" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG AND PBU.ID_PEMOHON = '"+USER_ID_SYSTEM+"') ";
		
		
		if(!CARIAN_PENDAFTAR.equals(""))
		{
			if(WCCARIAN_PENDAFTAR.equals("1"))
			{
				sql += " AND UPPER(PEND.PENDAFTAR) = '"+CARIAN_PENDAFTAR.toUpperCase().trim()+"' ";
			}
			else if(WCCARIAN_PENDAFTAR.equals("2"))
			{
				sql += " AND UPPER(PEND.PENDAFTAR) LIKE '%"+CARIAN_PENDAFTAR.toUpperCase().trim()+"%' ";
			}			
		}
		
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
		
		if(!CARIAN_NAMA_SIMATI.equals(""))
		{
			if(WCCARIAN_NAMA_SIMATI.equals("1"))
			{
				sql += " AND UPPER(SM.NAMA_SIMATI) = '"+CARIAN_NAMA_SIMATI.toUpperCase().trim()+"' ";
			}
			else if(WCCARIAN_NAMA_SIMATI.equals("2"))
			{
				sql += " AND UPPER(SM.NAMA_SIMATI) LIKE '%"+CARIAN_NAMA_SIMATI.toUpperCase().trim()+"%' ";
			}			
		}
		
		if(!CARIAN_PENGENALAN_SIMATI.equals(""))
		{
			if(WCCARIAN_PENGENALAN_SIMATI.equals("1"))
			{
				sql += " AND ( UPPER(SM.NO_KP_BARU) = '"+CARIAN_PENGENALAN_SIMATI.toUpperCase().trim()+"'" +
						" OR UPPER(SM.NO_KP_LAMA) = '"+CARIAN_PENGENALAN_SIMATI.toUpperCase().trim()+"'" +
								" OR UPPER(SM.NO_KP_LAIN) = '"+CARIAN_PENGENALAN_SIMATI.toUpperCase().trim()+"' )";
			}
			else if(WCCARIAN_PENGENALAN_SIMATI.equals("2"))
			{
				sql += " AND ( UPPER(SM.NO_KP_BARU) LIKE '%"+CARIAN_PENGENALAN_SIMATI.toUpperCase().trim()+"%'" +
						" OR UPPER(SM.NO_KP_LAMA) LIKE '%"+CARIAN_PENGENALAN_SIMATI.toUpperCase().trim()+"%'" +
								" OR UPPER(SM.NO_KP_LAIN) LIKE '%"+CARIAN_PENGENALAN_SIMATI.toUpperCase().trim()+"%' )";
			}			
		}
		
		
		if(!CARIAN_NAMA_PEMOHON.equals(""))
		{
			if(WCCARIAN_NAMA_PEMOHON.equals("1"))
			{
				sql += " AND UPPER(PM.NAMA_PEMOHON) = '"+CARIAN_NAMA_PEMOHON.toUpperCase().trim()+"' ";
			}
			else if(WCCARIAN_NAMA_PEMOHON.equals("2"))
			{
				sql += " AND UPPER(PM.NAMA_PEMOHON) LIKE '%"+CARIAN_NAMA_PEMOHON.toUpperCase().trim()+"%' ";
			}			
		}
		
		if(!CARIAN_PENGENALAN_PEMOHON.equals(""))
		{
			if(WCCARIAN_PENGENALAN_PEMOHON.equals("1"))
			{
				sql += " AND ( UPPER(PM.NO_KP_BARU) = '"+CARIAN_PENGENALAN_PEMOHON.toUpperCase().trim()+"'" +
						" OR UPPER(PM.NO_KP_LAMA) = '"+CARIAN_PENGENALAN_PEMOHON.toUpperCase().trim()+"'" +
								" OR UPPER(PM.NO_KP_LAIN) = '"+CARIAN_PENGENALAN_PEMOHON.toUpperCase().trim()+"' )";
			}
			else if(WCCARIAN_PENGENALAN_PEMOHON.equals("2"))
			{
				sql += " AND ( UPPER(PM.NO_KP_BARU) LIKE '%"+CARIAN_PENGENALAN_PEMOHON.toUpperCase().trim()+"%'" +
						" OR UPPER(PM.NO_KP_LAMA) LIKE '%"+CARIAN_PENGENALAN_PEMOHON.toUpperCase().trim()+"%'" +
								" OR UPPER(PM.NO_KP_LAIN) LIKE '%"+CARIAN_PENGENALAN_PEMOHON.toUpperCase().trim()+"%' )";
			}			
		}
		
		
		if(!CARIAN_NO_HAKMILIK.equals("") || !CARIAN_NO_PT.equals(""))
		{
			
			sql += " AND PSM.ID_PERMOHONANSIMATI IN (SELECT ID_PERMOHONANSIMATI FROM TBLPPKHTA HTA WHERE JENIS_HTA = 'Y' ";
			if(!CARIAN_NO_HAKMILIK.equals(""))
			{	
				if(WCCARIAN_NO_HAKMILIK.equals("1"))
				{
					sql += " AND UPPER(NO_HAKMILIK) = '"+CARIAN_NO_HAKMILIK.toUpperCase().trim()+"' ";
				}
				else if(WCCARIAN_NO_HAKMILIK.equals("2"))
				{
					sql += " AND UPPER(NO_HAKMILIK) LIKE '%"+CARIAN_NO_HAKMILIK.toUpperCase().trim()+"%' ";
				}
			}
			
			if(!CARIAN_NO_PT.equals(""))
			{	
				if(WCCARIAN_NO_PT.equals("1"))
				{
					sql += " AND UPPER(NO_PT) = '"+CARIAN_NO_PT.toUpperCase().trim()+"' ";
				}
				else if(WCCARIAN_NO_PT.equals("2"))
				{
					sql += " AND UPPER(NO_PT) LIKE '%"+CARIAN_NO_PT.toUpperCase().trim()+"%' ";
				}
			}
			sql += " ) ";
		}
		
		
		if(!CARIAN_NO_SIJILMATI.equals(""))
		{
			if(WCCARIAN_NO_SIJILMATI.equals("1"))
			{
				sql += " AND UPPER(SM.NO_SIJIL_MATI) = '"+CARIAN_NO_SIJILMATI.toUpperCase().trim()+"' ";
			}
			else if(WCCARIAN_NO_SIJILMATI.equals("2"))
			{
				sql += " AND UPPER(SM.NO_SIJIL_MATI) LIKE '%"+CARIAN_NO_SIJILMATI.toUpperCase().trim()+"%' ";
			}			
		}
		
		if(!CARIAN_TARIKH_MOHON.equals(""))
		{
			sql += " AND TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') = '"+CARIAN_TARIKH_MOHON.toUpperCase().trim()+"' ";
		}
		
		if(!CARIAN_TARIKH_BICARA.equals(""))
		{
			sql += " AND P.ID_PERMOHONAN IN (SELECT KP.ID_PERMOHONAN FROM TBLPPKPERBICARAAN PB, TBLPPKKEPUTUSANPERMOHONAN KP " +
					" WHERE PB.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND TO_CHAR(PB.TARIKH_BICARA,'DD/MM/YYYY') = '"+CARIAN_TARIKH_BICARA.toUpperCase().trim()+"') ";
		}
		
		if(!CARIAN_STATUS.equals(""))
		{
			sql += " AND P.ID_STATUS = '"+CARIAN_STATUS+"' ";
		}
		
		//send balik context put pada skrin carian..
		//context.put("CARIAN_NO_FAIL",CARIAN_NO_FAIL);
		return sql;
	}
	
	
	//method untuk susun column dalam senarai
	//dalam case ni tak pakai	
	public List listColumnForSenaraiFail(String skrinName,String namaList)throws Exception {
		List listColumnForSenarai = null;
		myLogger.info("listColumnForList namaList : "+namaList);
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		//setting nama column yang kita nak display dari query
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, 
				"NO_FAIL", //COLUMN NAME
				15,//width
				"No. Fail", //LABEL
				"left", // ALIGN
				
				"VARCHAR2", // DATATYPE
				"papar('{ID_PERMOHONAN}','{ID_STATUS}','{ID_FAIL}','{ID_SIMATI}','{ID_PERMOHONANSIMATI}','{TARIKH_MOHON}'," +
				"'{FLAG_JENIS_FAIL}','{SEKSYEN}')",""//ni tempat letak script, incase untuk custom display >> function ONCLIK LINK mesti diletakkan dalam index app skrin ini
				));	
		//jika param2 untuk onclick script ni akan ditarik dari maklumat list, buat mcm ni {}
		//makesure column yang kita nak panggil ni ada dalam query list kita
		
		
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "NAMA_SIMATI",15,"Nama Simati", "left","VARCHAR2","",""));
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "NAMA_PEMOHON",15,"Nama Pemohon", "left", "VARCHAR2","",""));
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "TARIKH_MOHON",10,"Tarikh Mohon", "center", "DATE","","","dd/MM/yyyy"));
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "STATUS",15, "Status","left", "VARCHAR2","",""));
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "PENDAFTAR",15,"Didaftar Oleh", "left", "VARCHAR2","",""));
		
		
		//listColumnForSenarai.add(RT.getColumnForSenarai(session,skrinName, "", "Tindakan", "left", namaList, ""));
		return listColumnForSenarai;
	}
	

	//[STANDARD]
	//boleh custome mengikut keperluan
	public void defaultContextPut()
	{
		//myLogger.info("defaultContextPut context : "+context);
		//segala context.put yang digunapakai dalam class ni mesti di initiate
		//context.put("htmlSenaraiFail", "");
		context.put("htmlSkrinCarian", "");
		context.put("htmlOnStart", "");
		context.put("htmlSenaraiFail", "");
		context.put("listSenaraiFail", "");
	}

	

}
