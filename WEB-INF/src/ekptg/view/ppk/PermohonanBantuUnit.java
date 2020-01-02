package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

public class PermohonanBantuUnit extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(PermohonanBantuUnit.class);
	String skrin_name = "app/ppk/BantuUnit/BUindex.jsp";
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		List listLogOT_forPrint = null;
		List listTT = null;
		List listPMT = null;
		List listBU = null;
		List listBU_Print = null;
		List listDates = null;
		List listLogOT = null;
		Hashtable viewTT = null;
		Hashtable viewPMT = null;
		Hashtable viewBU = null;
		List listAktiviti = null;
		List listNegeri = null;
		List listUnit = null;
		List listPelulus = null;
		boolean checkTransaksiTT = true;
		boolean checkTransaksiPMT = true;
		boolean checkTransaksiBU = true;
		boolean checkDB = true;
		boolean checkAllTT = true;
		boolean checkAllPMT = true;
		boolean checkAllBU = true;
		String checkSyntaxQuery = "";
		boolean checkJawatanKPP = false;
		boolean checkJawatanPengarahHQ = false;
		
		
		List userDetail = null;
		
		defaultPut();
		
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		myLogger.info("OT command : "+command);
		this.context.put("command",command);
		String USER_ROLE = (String) session.getAttribute("myrole");
		this.context.put("USER_ROLE",USER_ROLE);
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		
		//myLogger.info("USER_ROLE=="+USER_ROLE);
		//myLogger.info("USER_NEGERI=="+USER_NEGERI);
		
		
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		this.context.put("USER_UNIT",USER_UNIT);	
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);

		
		checkJawatanKPP = getJawatanKPP(session,USER_ID_SYSTEM);
		this.context.put("jawatanKpp",checkJawatanKPP);
		
		checkJawatanPengarahHQ = getJawatanPengarahHQ(session,USER_ID_SYSTEM);
		this.context.put("jawatanPengarah",checkJawatanPengarahHQ);
		
		
		if(command.equals("showSenaraiBU_Print"))
		{			
			listBU = listBU(session);
			this.context.put("listBU",listBU);
			this.context.put("listLogOT_forPrint",listBU);			
			skrin_name = "app/ppk/BantuUnit/BUSenaraiBU_Print.jsp";
		}
		else if(command.equals("showSenaraiBU") || command.equals("deleteBU") || command.equals("insertBU"))
		{			
			String ID_PERMOHONANBANTUUNIT = getParam("ID_PERMOHONANBANTUUNIT");
			if(command.equals("deleteBU"))
			{
				//function delete
				deleteBU(session,ID_PERMOHONANBANTUUNIT);
			}
			else if(command.equals("insertBU"))
			{
				//function insert
				saveTableBU(session,"");
			}
			this.context.put("FLAG_BU_CARIAN", getParam("FLAG_BU_CARIAN"));
			this.context.put("BU_OPENCLOSE_CARIAN",getParam("BU_OPENCLOSE_CARIAN"));
			String action = getParam("action");
			listBU = listBU(session);
			setupPageList(session, action, listBU, "listBU",command,"div_SenaraiBU");
			
			skrin_name = "app/ppk/BantuUnit/BUSenaraiBU.jsp";
		}
		
		else if(command.equals("addBU") || command.equals("editBU") || command.equals("saveBU") || command.equals("closeBU"))
		{
			String ID_PERMOHONANBANTUUNIT = getParam("ID_PERMOHONANBANTUUNIT");
			if(command.equals("closeBU"))
			{							
				skrin_name = "app/ppk/BantuUnit/BUrowBU.jsp";
			}
			else
			{
				
				if(command.equals("saveBU"))
				{
					//update function
					saveTableBU(session,ID_PERMOHONANBANTUUNIT);
				}
				
				skrin_name = "app/ppk/BantuUnit/BUeditBU.jsp";
			}
			viewBU = viewBU(session, ID_PERMOHONANBANTUUNIT);
			this.context.put("viewBU", viewBU);
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				//String DATE_MULA = (String)viewBU.get("TARIKH_MULA");
				//String DATE_AKHIR = (String)viewBU.get("TARIKH_AKHIR");
				//listDates = checkLogTransaksi(session,DATE_MULA,DATE_AKHIR,ID_PERMOHONANBANTUUNIT,(String)viewBU.get("FLAG_CUTI"));			
				//this.context.put("listDates",listDates);
			}
			
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			
			listNegeri = listNegeri(session);
			this.context.put("listNegeri",listNegeri);
			
			String BU_ID_NEGERI = (String)viewBU.get("ID_NEGERI");
			String BU_ID_PEMOHON = getParam("BU_ID_PEMOHON_"+ID_PERMOHONANBANTUUNIT);			
			listUnit = listUnit(session,BU_ID_NEGERI,getIdUnitByUserId(session, BU_ID_PEMOHON));
			this.context.put("listUnit",listUnit);
			
		}
		
		else if(command.equals("showCARIAN_BU"))
		{
			this.context.put("FLAG_BU_CARIAN", getParam("FLAG_BU_CARIAN"));
			this.context.put("BU_OPENCLOSE_CARIAN",getParam("BU_OPENCLOSE_CARIAN"));
			listNegeri = listNegeri(session);
			this.context.put("listNegeri",listNegeri);
			listUnit = listUnit(session,"","");
			this.context.put("listUnit",listUnit);
			skrin_name = "app/ppk/BantuUnit/BUskrinCarianBU.jsp";
		}
		else if(command.equals("showUnit_BU"))
		{
			String BU_ID_NEGERI = getParam("BU_ID_NEGERI"); 
			listUnit = listUnit(session,BU_ID_NEGERI,"");
			this.context.put("listUnit",listUnit);
			skrin_name = "app/ppk/BantuUnit/OTskrinUnitBU.jsp";
		}
		else if(command.equals("showUnit_editBU"))
		{
			String ID_PERMOHONANBANTUUNIT = getParam("ID_PERMOHONANBANTUUNIT");
			this.context.put("ID_PERMOHONANBANTUUNIT",ID_PERMOHONANBANTUUNIT);
			String BU_ID_NEGERI = getParam("BU_ID_NEGERI_"+ID_PERMOHONANBANTUUNIT); 
			String BU_ID_PEMOHON = getParam("BU_ID_PEMOHON_"+ID_PERMOHONANBANTUUNIT);			
			listUnit = listUnit(session,BU_ID_NEGERI,getIdUnitByUserId(session, BU_ID_PEMOHON));
			this.context.put("listUnit",listUnit);
			skrin_name = "app/ppk/BantuUnit/OTskrinUniteditBU.jsp";
		}
		
		
		else if(command.equals("checkDateBU"))
		{
			boolean checkDuplicateDate = true;
			String ID_PERMOHONANBANTUUNIT = getParam("ID_PERMOHONANBANTUUNIT");			
			String BU_ID_PEMOHON = getParam("BU_ID_PEMOHON_"+ID_PERMOHONANBANTUUNIT);
			String BU_TARIKH_BU_MULA = getParam("BU_TARIKH_BU_MULA_"+ID_PERMOHONANBANTUUNIT);
			String BU_TARIKH_BU_AKHIR = getParam("BU_TARIKH_BU_AKHIR_"+ID_PERMOHONANBANTUUNIT);
			String CHECK_BU_TARIKH_BU_MULA = getParam("CHECK_BU_TARIKH_BU_MULA_"+ID_PERMOHONANBANTUUNIT);
			String CHECK_BU_TARIKH_BU_AKHIR = getParam("CHECK_BU_TARIKH_BU_AKHIR_"+ID_PERMOHONANBANTUUNIT);
			String BU_ID_NEGERI = getParam("BU_ID_NEGERI_"+ID_PERMOHONANBANTUUNIT);
			String BU_ID_UNIT = getParam("BU_ID_UNIT_"+ID_PERMOHONANBANTUUNIT);
			
			String checkNO_BU = "";
			String checkDuplicateDate_msg = "";
			if(CHECK_BU_TARIKH_BU_MULA.equals("true") && CHECK_BU_TARIKH_BU_AKHIR.equals("true")
					&& !BU_TARIKH_BU_MULA.equals("") && !BU_TARIKH_BU_AKHIR.equals("")
					&& !BU_ID_NEGERI.equals("") && !BU_ID_UNIT.equals(""))
			{
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date date_mula = format.parse(BU_TARIKH_BU_MULA);
				Date date_akhir = format.parse(BU_TARIKH_BU_AKHIR);
				if(date_mula.after(date_akhir)) {
				    checkDuplicateDate_msg = "<font color='red' class='blink' >** Sila pastikan tarikh mula bantuan ('"+BU_TARIKH_BU_MULA+"') tidak melebihi tarikh akhir bantuan ('"+BU_TARIKH_BU_AKHIR+"')!</font><br>";				
					checkDuplicateDate = false;
				}
				else
				{
					checkNO_BU = checkDuplicateDate(session, BU_ID_PEMOHON,ID_PERMOHONANBANTUUNIT,BU_TARIKH_BU_MULA, BU_TARIKH_BU_AKHIR,BU_ID_NEGERI,BU_ID_UNIT);
					if(!checkNO_BU.equals(""))
					{
						checkDuplicateDate_msg = "<font color='red' class='blink' >** Tarikh bantuan yang dimasukkan <b>("+BU_TARIKH_BU_MULA+"-"+BU_TARIKH_BU_AKHIR+")</b> telah wujud pada unit <b>"+getUnitName(session, BU_ID_UNIT)+"</b>! <br>No. Bantu Unit : '<b>"+checkNO_BU.toUpperCase()+"</b>'</font><br>";				
						checkDuplicateDate = false;
					}
				}
			}
			
			this.context.put("ID_PERMOHONANBANTUUNIT",ID_PERMOHONANBANTUUNIT);
			this.context.put("checkDuplicateDate_msg",checkDuplicateDate_msg);
			this.context.put("checkDuplicateDate",checkDuplicateDate);
			skrin_name = "app/ppk/BantuUnit/BUcheckDateBU.jsp";
		}
		
		
		
		myLogger.info(" OT - VM :"+skrin_name);
		return skrin_name;
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
	
	public void defaultPut()
	{
		this.context.put("FLAG_TT_CARIAN", "");
		this.context.put("TT_OPENCLOSE_CARIAN","");
		this.context.put("TT_NAMA_AKTIVITI","");
		this.context.put("TT_NAMA_TABLE","");
		this.context.put("TT_NAMA_FIELD","");
		this.context.put("TT_SEKSYEN","");
		this.context.put("viewTT", "");
		this.context.put("rowCss", "");
		this.context.put("BIL", "");
		this.context.put("checkTransaksi", "");
		this.context.put("checkTransaksi_msg", "");	
		this.context.put("ID_TRANSAKSI", "");
		this.context.put("checkTransaksiTT", "");
		this.context.put("checkTransaksiTT_msg", "");	
		
		this.context.put("FLAG_PMT_CARIAN", "");
		this.context.put("PMT_OPENCLOSE_CARIAN","");
		this.context.put("PMT_NAMA_AKTIVITI","");
		this.context.put("listAktiviti","");	
		this.context.put("checkTransaksiPMT", "");
		this.context.put("checkTransaksiPMT_msg", "");
		
		this.context.put("FLAG_BU_CARIAN", "");
		this.context.put("BU_OPENCLOSE_CARIAN","");
		this.context.put("BU_NAMA_AKTIVITI","");
		this.context.put("listNegeri","");	
		this.context.put("listUnit","");		
		this.context.put("checkTransaksiBU", "");
		this.context.put("checkTransaksiBU_msg", "");
		this.context.put("listPelulus", "");
		this.context.put("checkNO_OT","");
		this.context.put("checkDuplicateDate_msg","");
		this.context.put("listDates","");
		this.context.put("listLogOT","");		
		this.context.put("OVERALLMINIT", "");
		this.context.put("listLogOT_forPrint", "");
		
	}
	
	
	
	
	public void saveTableBU(HttpSession session,String ID_PERMOHONANBANTUUNIT) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		long idBU = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		boolean checkJawatanKPP = false;
		boolean checkJawatanPengarahHQ = false;
		checkJawatanKPP = getJawatanKPP(session,USER_ID_SYSTEM);
		checkJawatanPengarahHQ = getJawatanPengarahHQ(session,USER_ID_SYSTEM);
		
	
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String BU_ID_PEMOHON = getParam("BU_ID_PEMOHON"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_TARIKH_BU_MULA = getParam("BU_TARIKH_BU_MULA"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_TARIKH_BU_AKHIR = getParam("BU_TARIKH_BU_AKHIR"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_ID_PELULUS = getParam("BU_ID_PELULUS"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_CATATAN_PEMOHON = getParam("BU_CATATAN_PEMOHON"+"_"+ID_PERMOHONANBANTUUNIT);
			myLogger.info(" BU_CATATAN_PEMOHON : "+BU_CATATAN_PEMOHON);
			String BU_CATATAN_PELULUS = getParam("BU_CATATAN_PELULUS"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_ID_STATUS = getParam("BU_ID_STATUS"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_ID_NEGERI = getParam("BU_ID_NEGERI"+"_"+ID_PERMOHONANBANTUUNIT);
			String BU_ID_UNIT = getParam("BU_ID_UNIT"+"_"+ID_PERMOHONANBANTUUNIT);
			
			String checkPermohonanNegeri = "";
			checkPermohonanNegeri = checkIfPermohonanNegeri(session,BU_ID_UNIT,USER_NEGERI);
			
			myLogger.info("checkPermohonanNegeri===="+checkPermohonanNegeri);
			
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				r.update("ID_PERMOHONANBANTUUNIT", ID_PERMOHONANBANTUUNIT);
				
				if(checkJawatanKPP==true || checkJawatanPengarahHQ == true){
					myLogger.info("BU_CATATAN_PELULUS.toUpperCase()===="+BU_CATATAN_PELULUS.toUpperCase());
					myLogger.info("BU_ID_STATUS===="+BU_ID_STATUS);
					myLogger.info("BU_ID_PELULUS===="+BU_ID_PELULUS);
					
					
					
					
				r.add("CATATAN_PELULUS", BU_CATATAN_PELULUS.toUpperCase());
				r.add("ID_STATUS",BU_ID_STATUS);
				r.add("ID_PELULUS",BU_ID_PELULUS);
				r.add("TARIKH_LULUS", r.unquote("sysdate"));
				}
				
				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String tahun = formatter.format(now);
				String kod_negeri = getKodNegeri(session, BU_ID_NEGERI);
				String no_rujukan_ot = "BU/"+tahun + "/"+ kod_negeri + "/PPK/"
						+ String.format("%06d", getSeqNoBU(tahun,BU_ID_NEGERI,"PPK"));
				r.add("NO_BANTUUNIT", no_rujukan_ot);				
				r.add("ID_PEMOHON", BU_ID_PEMOHON);
				r.add("CATATAN_PEMOHON", BU_CATATAN_PEMOHON.toUpperCase());		
								
			
				/*if(checkPermohonanNegeri!=null){
					r.add("PERMOHONAN_NEGERI", "Y");
				}else{
					
					r.add("PERMOHONAN_NEGERI", "");
				}*/
				
			}
			else
			{
				idBU = DB.getNextID(db, "TBLPERMOHONANBANTUUNIT_SEQ");
				
				myLogger.info("idBU===="+idBU);
				r.add("ID_PERMOHONANBANTUUNIT", idBU);
				r.add("TARIKH_MOHON", r.unquote("sysdate"));
				
				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String tahun = formatter.format(now);
				String kod_negeri = getKodNegeri(session, BU_ID_NEGERI);
				String no_rujukan_ot = "BU/"+tahun + "/"+ kod_negeri + "/PPK/"
						+ String.format("%06d", getSeqNoBU(tahun,BU_ID_NEGERI,"PPK"));
				r.add("NO_BANTUUNIT", no_rujukan_ot);
				r.add("ID_STATUS",1);//PERMOHONAN BARU
				r.add("ID_PEMOHON", BU_ID_PEMOHON);
				r.add("CATATAN_PEMOHON", BU_CATATAN_PEMOHON.toUpperCase());		
				
				
				if(checkPermohonanNegeri==""){
					r.add("PERMOHONAN_NEGERI", "");
				}else{
					r.add("PERMOHONAN_NEGERI", "Y");
				}
			}
			
			String TARIKH_BU_MULA = "to_date('" + BU_TARIKH_BU_MULA + "','dd/MM/yyyy')";
			String TARIKH_BU_AKHIR = "to_date('" + BU_TARIKH_BU_AKHIR + "','dd/MM/yyyy')";
			
			r.add("TARIKH_MULA", r.unquote(TARIKH_BU_MULA));
			r.add("TARIKH_AKHIR", r.unquote(TARIKH_BU_AKHIR));
			r.add("ID_UNIT", BU_ID_UNIT);
			r.add("ID_NEGERI", BU_ID_NEGERI);
			
			
			
			
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPERMOHONANBANTUUNIT");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPERMOHONANBANTUUNIT");	
			}
			myLogger.info("OT : SAVE BU : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				//hantarEmel(session,ID_PERMOHONANBANTUUNIT);
				AuditTrail.logActivity(this,session,"UPD","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+ID_PERMOHONANBANTUUNIT+"] Updated");
				
			}
			else
			{
				//hantarEmel(session,idBU+"");
				AuditTrail.logActivity(this,session,"INS","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+idBU+"] Inserted");
			}
			
			
		} 
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	public Hashtable viewBU(HttpSession session, String ID_PERMOHONANBANTUUNIT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_PERMOHONANBANTUUNIT.equals(""))
			{
				/*
				sql = " SELECT T.ID_PERMOHONANBANTUUNIT, T.ID_PEMOHON, T.ID_PELULUS, TO_CHAR(T.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, "+
						" TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_MULA, TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY') AS TARIKH_AKHIR,  "+
						" UPPER(T.CATATAN_PEMOHON) AS CATATAN_PEMOHON,UPPER(T.CATATAN_PELULUS) AS CATATAN_PELULUS,  "+
						" T.ID_STATUS, (CASE WHEN ID_STATUS = '1' THEN 'BARU' "+
						" WHEN ID_STATUS = '2' THEN 'LULUS' WHEN ID_STATUS = '3' THEN 'TOLAK' WHEN ID_STATUS = '4' THEN 'SELESAI' ELSE '' END) AS STATUS, "+
						" T.NO_OT, T.ID_SEKSYEN, UPPER(S.NAMA_SEKSYEN) AS BAHAGIAN, "+
						" PM.USER_NAME AS NAMA_PEMOHON, PL.USER_NAME AS NAMA_PELULUS "+
						" FROM TBLPERMOHONANOT T, TBLRUJSEKSYEN S, USERS PM, USERS PL, USERS_INTERNAL UI, TBLRUJPEJABATJKPTG PEJ "+
						" WHERE T.ID_SEKSYEN  = S.ID_SEKSYEN AND PM.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG ";										
				sql += " AND T.ID_PELULUS = PL.USER_ID AND T.ID_PEMOHON = PM.USER_ID AND T.ID_SEKSYEN = '2' AND T.ID_PERMOHONANBANTUUNIT = '"+ID_PERMOHONANBANTUUNIT+"'  ";
				*/
				/*
				sql = " SELECT NVL(T.FLAG_CUTI,'N') AS FLAG_CUTI, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI,T.ID_PERMOHONANBANTUUNIT, T.ID_PEMOHON, T.ID_PELULUS, " +
						" TO_CHAR(T.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON,  TO_CHAR(T.TARIKH_LULUS,'DD/MM/YYYY') AS TARIKH_LULUS, "+
						" TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_MULA, TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY') AS TARIKH_AKHIR,  "+
						" UPPER(T.CATATAN_PEMOHON) AS CATATAN_PEMOHON,UPPER(T.CATATAN_PELULUS) AS CATATAN_PELULUS,  "+
						" T.ID_STATUS, (CASE WHEN ID_STATUS = '1' THEN 'BARU' "+
						" WHEN ID_STATUS = '2' THEN 'LULUS' WHEN ID_STATUS = '3' THEN 'TOLAK' WHEN ID_STATUS = '4' THEN 'SELESAI' ELSE '' END) AS STATUS, "+
						" T.NO_OT, T.ID_SEKSYEN, UPPER(S.NAMA_SEKSYEN) AS BAHAGIAN, "+
						" PM.USER_NAME AS NAMA_PEMOHON, PL.USER_NAME AS NAMA_PELULUS, UPPER(UI.WAKTU_KERJA) AS WAKTU_KERJA "+
						" FROM TBLPERMOHONANOT T, TBLRUJSEKSYEN S, USERS PM, USERS PL, USERS_INTERNAL UI, TBLRUJPEJABATJKPTG PEJ,TBLRUJNEGERI N "+
						" WHERE T.ID_SEKSYEN  = S.ID_SEKSYEN AND PM.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG" +
						" AND PEJ.ID_NEGERI = N.ID_NEGERI ";										
				sql += " AND T.ID_PELULUS = PL.USER_ID AND T.ID_PEMOHON = PM.USER_ID AND T.ID_SEKSYEN = '2' AND T.ID_PERMOHONANBANTUUNIT = '"+ID_PERMOHONANBANTUUNIT+"' ";
				*/				
				sql = sqlBantuUnit(session);
				sql += " AND T.ID_PERMOHONANBANTUUNIT = '"+ID_PERMOHONANBANTUUNIT+"' ";
				myLogger.info(" OT : viewBU :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					h.put("ID_PERMOHONANBANTUUNIT",rs.getString("ID_PERMOHONANBANTUUNIT") == null ? "" : rs.getString("ID_PERMOHONANBANTUUNIT"));
					h.put("ID_PEMOHON",rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
					
					h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
					h.put("TARIKH_LULUS",rs.getString("TARIKH_LULUS") == null ? "" : rs.getString("TARIKH_LULUS"));
					h.put("TARIKH_MULA",rs.getString("TARIKH_MULA") == null ? "" : rs.getString("TARIKH_MULA"));
					h.put("TARIKH_AKHIR",rs.getString("TARIKH_AKHIR") == null ? "" : rs.getString("TARIKH_AKHIR"));
					h.put("CATATAN_PEMOHON",rs.getString("CATATAN_PEMOHON") == null ? "-" : rs.getString("CATATAN_PEMOHON"));
					h.put("CATATAN_PELULUS",rs.getString("CATATAN_PELULUS") == null ? "-" : rs.getString("CATATAN_PELULUS"));
					h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					
					String id_status = (rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
					String id_unit = (rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
					
					myLogger.info("id_status==============="+id_status);
					if(id_status.equals("1") && USER_ROLE.equals("adminppk") || (id_status.equals("1") && USER_NEGERI.equals("16")))
					{
						myLogger.info("sini la masuk1");
						h.put("ID_PELULUS",USER_ID_SYSTEM);
						h.put("NAMA_PELULUS",getUserFullName(session,USER_ID_SYSTEM));
					}
					else
					{
						myLogger.info("sini la masuk2");
						h.put("ID_PELULUS",rs.getString("ID_PELULUS") == null ? "" : rs.getString("ID_PELULUS"));
						h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS"));
					}
					
					h.put("NO_BANTUUNIT",rs.getString("NO_BANTUUNIT") == null ? "" : rs.getString("NO_BANTUUNIT"));
					h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
					
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
					h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));					
					//h.put("DAERAH_JAGAAN",rs.getString("DAERAH_JAGAAN") == null ? "" : rs.getString("DAERAH_JAGAAN"));	
					h.put("DAERAH_JAGAAN","");	//aishah comment
					h.put("NEGERI_PELULUS",rs.getString("NEGERI_PELULUS") == null ? "" : rs.getString("NEGERI_PELULUS"));
					h.put("NEGERI_PEMOHON",rs.getString("NEGERI_PEMOHON") == null ? "" : rs.getString("NEGERI_PEMOHON"));
					h.put("PEJABAT_PEMOHON",rs.getString("PEJABAT_PEMOHON") == null ? "" : rs.getString("PEJABAT_PEMOHON"));
					h.put("PEJABAT_PELULUS",rs.getString("PEJABAT_PELULUS") == null ? "" : rs.getString("PEJABAT_PELULUS"));
					h.put("EMEL_PEMOHON",rs.getString("EMEL_PEMOHON") == null ? "" : rs.getString("EMEL_PEMOHON"));
					h.put("PERMOHONAN_NEGERI",rs.getString("PERMOHONAN_NEGERI") == null ? "" : rs.getString("PERMOHONAN_NEGERI"));
					
				
				}
			}
			else
			{
				h.put("ID_PERMOHONANBANTUUNIT","");
				h.put("ID_PEMOHON",USER_ID_SYSTEM);
				h.put("ID_PELULUS","");
				h.put("TARIKH_MOHON","");
				h.put("TARIKH_LULUS","");
				h.put("TARIKH_MULA","");
				h.put("TARIKH_AKHIR","");
				h.put("CATATAN_PEMOHON","");
				h.put("CATATAN_PELULUS","");
				h.put("ID_STATUS","");
				h.put("STATUS","");
				h.put("NO_BANTUUNIT","");
				h.put("NAMA_PEMOHON",getUserFullName(session,USER_ID_SYSTEM));
				h.put("NAMA_PELULUS","");
				h.put("NAMA_NEGERI","");
				h.put("NAMA_UNIT","");
				h.put("ID_UNIT","");
				h.put("ID_NEGERI","");
				h.put("DAERAH_JAGAAN","");				
				h.put("NEGERI_PELULUS","");
				h.put("NEGERI_PEMOHON","");
				h.put("PEJABAT_PEMOHON","");
				h.put("PEJABAT_PELULUS","");
				h.put("EMEL_PEMOHON","");
				h.put("PERMOHONAN_NEGERI","");
			
			}
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getUserFullName(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String fulname="";
			sql = " SELECT UPPER(USER_NAME) AS FULLNAME FROM USERS WHERE USER_ID = '"+USER_ID+"'";				
				myLogger.info(" OT : getUserFullName :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					fulname = (rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME"));
					
				}
			
			return fulname;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getUnitName(HttpSession session, String ID_UNIT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String fulname="";
			sql = " SELECT UPPER(NAMA_PEJABAT) AS NAMA_PEJABAT FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = '"+ID_UNIT+"'";				
				myLogger.info(" OT : getUnitName :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					fulname = (rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					
				}
			
			return fulname;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getWaktuKerja(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String waktu_kerja="";
			sql = " SELECT UPPER(WAKTU_KERJA) AS WAKTU_KERJA FROM USERS_INTERNAL WHERE USER_ID = '"+USER_ID+"'";				
				myLogger.info(" OT : getUserFullName :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					waktu_kerja = (rs.getString("WAKTU_KERJA") == null ? "" : rs.getString("WAKTU_KERJA"));
					
				}
			
			return waktu_kerja;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	
	public String sqlBantuUnit(HttpSession session)
	{
		String sql = " SELECT UPPER(NEG_PL.NAMA_NEGERI) AS NEGERI_PELULUS, UPPER(NEG_PM.NAMA_NEGERI) AS NEGERI_PEMOHON, " +
		" UPPER(PEJ_PL.NAMA_PEJABAT) AS PEJABAT_PELULUS, UPPER(PEJ_PM.NAMA_PEJABAT) AS PEJABAT_PEMOHON, " +
		" T.ID_PERMOHONANBANTUUNIT, UPPER(PEJABAT.NAMA_PEJABAT) AS NAMA_UNIT, T.ID_PEMOHON, T.ID_PELULUS, "+
		" TO_CHAR (T.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON, "+
		" TO_CHAR (T.TARIKH_LULUS, 'DD/MM/YYYY') AS TARIKH_LULUS, "+
		" TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY') AS TARIKH_MULA, "+
		" TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY') AS TARIKH_AKHIR, "+
		" UPPER (T.CATATAN_PEMOHON) AS CATATAN_PEMOHON, "+
		" UPPER (T.CATATAN_PELULUS) AS CATATAN_PELULUS,   "+
		/*
		" (CASE  "+
		" WHEN ID_STATUS = '2' "+
		" THEN  "+
		" (CASE WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') < TO_DATE(TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+
		" THEN 4 "+
		" WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+
		" THEN 5 "+
		" END) "+
		" ELSE T.ID_STATUS END "+
		" ) AS ID_STATUS, "+
		*/
		" T.ID_STATUS, "+
		/*
		" (CASE WHEN ID_STATUS = '1' "+
		" THEN 'BARU' "+
		" WHEN ID_STATUS = '2' "+
		" THEN  "+
		" (CASE WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') < TO_DATE(TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+
		" THEN 'BELUM BERMULA' "+
		" WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+
		" THEN 'LUPUT' "+
		" ELSE 'LULUS' END) "+
		" WHEN ID_STATUS = '3' "+
		" THEN 'TOLAK' "+
		" ELSE  "+
		" '' "+
		" END "+
		" ) AS STATUS, "+
		*/
		" (CASE WHEN ID_STATUS = '1' "+
		" THEN 'BARU' "+
		" WHEN ID_STATUS = '2' "+
		" THEN 'LULUS' "+
		" WHEN ID_STATUS = '3' "+
		" THEN 'TOLAK' "+
		" ELSE  "+
		" '' "+
		" END "+
		" ) AS STATUS, "+
		" T.NO_BANTUUNIT, UPPER(PM.USER_NAME) AS NAMA_PEMOHON, UPPER(PL.USER_NAME) AS NAMA_PELULUS, "+
		" UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, T.ID_NEGERI, T.ID_UNIT,  "+
		//"  UPPER(REGEXP_REPLACE(LOWER(REPLACE(SUBSTR(PEJABATURUSAN.LIST_DAERAH, 1, INSTR(PEJABATURUSAN.LIST_DAERAH , ',', -1)-1), '.', ' ')),', ',' DAN ',INSTR(LOWER(REPLACE(SUBSTR(PEJABATURUSAN.LIST_DAERAH, 1, INSTR(PEJABATURUSAN.LIST_DAERAH , ',', -1)-1), '.', ' ')) ,', ',-1))) AS DAERAH_JAGAAN, "+
		" UI_PM.EMEL AS EMEL_PEMOHON, T.PERMOHONAN_NEGERI "+
		" FROM TBLPERMOHONANBANTUUNIT T," +
		" USERS PM, USERS_INTERNAL UI_PM, TBLRUJPEJABATJKPTG PEJ_PM, TBLRUJNEGERI NEG_PM," +
		" TBLRUJNEGERI N,  "+
		" (SELECT P.ID_PEJABATJKPTG,P.ALAMAT1,P.ALAMAT2,P.ALAMAT3,P.ID_BANDAR,P.ID_NEGERI,P.NAMA_PEJABAT   "+
		" FROM TBLRUJPEJABATJKPTG P WHERE ID_JENISPEJABAT IN (22,24) AND ID_SEKSYEN = 2 ORDER BY ID_NEGERI) PEJABAT, "+
		" (SELECT PU.ID_PEJABATJKPTG,  "+
		" UPPER(RTRIM (XMLAGG (XMLELEMENT (E, (D.NAMA_DAERAH || ', '))).EXTRACT ('//text()'), ',')) AS LIST_DAERAH "+
		" FROM TBLRUJPEJABATURUSAN PU, TBLRUJDAERAH D  "+
		" WHERE PU.ID_DAERAHURUS = D.ID_DAERAH AND PU.ID_SEKSYEN = 2 " +
		" AND PU.ID_JENISPEJABAT IN (22,24) "+
		" GROUP BY PU.ID_PEJABATJKPTG) PEJABATURUSAN, "+
		" USERS PL,USERS_INTERNAL UI_PL, TBLRUJPEJABATJKPTG PEJ_PL,TBLRUJNEGERI NEG_PL "+
		" WHERE " +
		" T.ID_PELULUS = PL.USER_ID(+) " +
		" AND PL.USER_ID = UI_PL.USER_ID(+) " +
		" AND UI_PL.ID_PEJABATJKPTG = PEJ_PL.ID_PEJABATJKPTG(+) " +
		" AND UI_PL.ID_NEGERI = NEG_PL.ID_NEGERI(+) " +
		" AND T.ID_PEMOHON = PM.USER_ID AND PM.USER_ID = UI_PM.USER_ID AND UI_PM.ID_PEJABATJKPTG = PEJ_PM.ID_PEJABATJKPTG AND UI_PM.ID_NEGERI = NEG_PM.ID_NEGERI "+
		" AND T.ID_NEGERI = N.ID_NEGERI  "+
		" AND T.ID_UNIT = PEJABAT.ID_PEJABATJKPTG(+) AND T.ID_UNIT = PEJABATURUSAN.ID_PEJABATJKPTG(+) ";

		return sql;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listBU(HttpSession session)throws Exception {
		
		String FLAG_BU_CARIAN = getParam("FLAG_BU_CARIAN");
		myLogger.info("-------- FLAG_BU_CARIAN : "+FLAG_BU_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		
		myLogger.info("-------- USER_ID_SYSTEM : "+USER_ID_SYSTEM+" USER_ROLE : "+USER_ROLE+" USER_NEGERI : "+USER_NEGERI);
		
		String BU_NAMA_PELULUS = getParam("BU_NAMA_PELULUS");
		String BU_NAMA_PEMOHON = getParam("BU_NAMA_PEMOHON");
		String BU_NO_BANTUUNIT = getParam("BU_NO_BANTUUNIT");
		String BU_TARIKH_BU_MULA = getParam("BU_TARIKH_BU_MULA");
		String BU_TARIKH_BU_AKHIR = getParam("BU_TARIKH_BU_AKHIR");
		String BU_ID_NEGERI= getParam("BU_ID_NEGERI");
		String BU_ID_STATUS= getParam("BU_ID_STATUS");
		String BU_ID_UNIT= getParam("BU_ID_UNIT");
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listBU = null;
		String sql = "";
		
		boolean checkJawatanKPP = false;
		boolean checkJawatanPengarahHQ = false;
		
		try {
			db = new Db();
			stmt = db.getStatement();
							
			sql = sqlBantuUnit(session);
	
			checkJawatanKPP = getJawatanKPP(session,USER_ID_SYSTEM);
			checkJawatanPengarahHQ = getJawatanPengarahHQ(session,USER_ID_SYSTEM);
					
			myLogger.info("-------- checkJawatanKPP : "+checkJawatanKPP);
			myLogger.info("-------- checkJawatanPengarahHQ : "+checkJawatanPengarahHQ);
			myLogger.info("-------- FLAG_BU_CARIAN : "+FLAG_BU_CARIAN);
			if(FLAG_BU_CARIAN.equals("Y"))
			{
				if(!BU_ID_NEGERI.equals(""))
				{
					sql += " AND T.ID_NEGERI = '"+BU_ID_NEGERI+"' ";
					
				}
				if(!BU_ID_STATUS.equals(""))
				{
					sql += " AND (CASE  "+
					" WHEN ID_STATUS = '2' "+
					" THEN  "+
					" (CASE WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') < TO_DATE(TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+
					" THEN 4 "+
					" WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+
					" THEN 5 "+
					" END) "+
					" ELSE T.ID_STATUS END "+
					" ) = '"+BU_ID_STATUS+"' ";					
				}
				if(!BU_ID_UNIT.equals(""))
				{
					sql += " AND T.ID_UNIT = '"+BU_ID_UNIT+"' ";					
				}
				if(!BU_NAMA_PEMOHON.equals(""))
				{
					sql += " AND UPPER(PM.USER_NAME) LIKE '%"+BU_NAMA_PEMOHON.toUpperCase()+"%' ";
				}
				if(!BU_NAMA_PELULUS.equals(""))
				{
					sql += " AND UPPER(PL.USER_NAME) LIKE '%"+BU_NAMA_PELULUS.toUpperCase()+"%' ";
				}
				if(!BU_NO_BANTUUNIT.equals(""))
				{
					sql += " AND UPPER(T.NO_BANTUUNIT) LIKE '%"+BU_NO_BANTUUNIT.toUpperCase()+"%' ";
				}
				
				if(!BU_TARIKH_BU_MULA.equals("") && !BU_TARIKH_BU_AKHIR.equals(""))
				{
					sql+= " AND ( "+ 
							" ( "+     
							" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) >= (TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" AND ( "+
							" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) >= TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" ) "+
							" OR "+
							" (  "+    
							" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) <= (TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" AND ( "+
							" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) >= TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" ) "+
							" ) ";
					
					sql +=  " AND   (TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+BU_TARIKH_BU_AKHIR+"', 'DD/MM/YYYY') "+
							" OR   TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+BU_TARIKH_BU_AKHIR+"', 'DD/MM/YYYY'))  ";
				
				}
				else
				{
					if(!BU_TARIKH_BU_MULA.equals(""))
					{
						sql+= " AND ( "+ 
								" ( "+     
								" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) >= (TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" AND ( "+
								" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) >= TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" ) "+
								" OR "+
								" (  "+    
								" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) <= (TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" AND ( "+
								" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) >= TO_DATE ('"+BU_TARIKH_BU_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" ) "+
								" ) ";
					}
					else if(!BU_TARIKH_BU_AKHIR.equals(""))
					{
						sql +=  " AND   (TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+BU_TARIKH_BU_AKHIR+"', 'DD/MM/YYYY') "+
								" OR   TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+BU_TARIKH_BU_AKHIR+"', 'DD/MM/YYYY'))  ";
					}
				}				
				this.context.put("BU_NAMA_PELULUS",BU_NAMA_PELULUS.toUpperCase());
				this.context.put("BU_NAMA_PEMOHON",BU_NAMA_PEMOHON.toUpperCase());
				this.context.put("BU_NO_BANTUUNIT",BU_NO_BANTUUNIT.toUpperCase());
				this.context.put("BU_TARIKH_BU_MULA",BU_TARIKH_BU_MULA.toUpperCase());
				this.context.put("BU_TARIKH_BU_AKHIR",BU_TARIKH_BU_AKHIR.toUpperCase());
				this.context.put("BU_ID_NEGERI",BU_ID_NEGERI.toUpperCase());
				this.context.put("BU_ID_STATUS",BU_ID_STATUS.toUpperCase());		
				this.context.put("BU_ID_UNIT",BU_ID_UNIT.toUpperCase());
			}
			else
			{				
				this.context.put("BU_NAMA_PELULUS","");
				this.context.put("BU_NAMA_PEMOHON","");
				this.context.put("BU_NO_BANTUUNIT","");
				this.context.put("BU_TARIKH_BU_MULA","");
				this.context.put("BU_TARIKH_BU_AKHIR","");
				this.context.put("BU_ID_NEGERI","");
				this.context.put("BU_ID_STATUS","");
				this.context.put("BU_ID_UNIT","");				
			}
			
			if(USER_ROLE.equals("user_ppk"))
			{
				sql += " AND PM.USER_ID = '"+USER_ID_SYSTEM+"' ";
				sql += " AND UI_PM.ID_NEGERI = '"+USER_NEGERI+"' ";
				
				//sql += " AND (T.PERMOHONAN_NEGERI is null) ";
			}
			if(USER_ROLE.equals("adminppk") && checkJawatanKPP == false && checkJawatanPengarahHQ == false)
			{
				//sql += " AND PM.USER_ID = '"+USER_ID_SYSTEM+"' ";
				sql += " AND UI_PM.ID_NEGERI = '"+USER_NEGERI+"' ";
				
				//sql += " AND (T.PERMOHONAN_NEGERI is null) ";
			}
			//else if(USER_ROLE.equals("adminppk"))
			if(checkJawatanKPP == true && !USER_NEGERI.equals("16"))				
			{
				sql += " AND (PM.USER_ID = '"+USER_ID_SYSTEM+"' OR T.ID_NEGERI = '"+USER_NEGERI+"') ";
				sql += " AND (T.PERMOHONAN_NEGERI is null) ";
			}
			
			if(USER_NEGERI.equals("16")){
				sql += " AND (T.PERMOHONAN_NEGERI = 'Y') ";
			}
			if(checkJawatanPengarahHQ == true )				
			{
				//sql += " AND (PM.USER_ID = '"+USER_ID_SYSTEM+"' OR T.ID_NEGERI = '"+USER_NEGERI+"') ";
				sql += " AND (T.PERMOHONAN_NEGERI = 'Y') ";
			}
			
			sql += " ORDER BY " +
					" (CASE WHEN (T.ID_STATUS = '1' AND '"+USER_ROLE+"' = 'adminppk' " +
							"AND T.ID_NEGERI = '"+USER_NEGERI+"'" +
							") THEN 1 ELSE 2 END) ASC," +
					" T.ID_STATUS ASC, "+
					" T.TARIKH_MULA DESC ";
					
					
			myLogger.info(" OT : SQL listBU :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listBU = Collections.synchronizedList(new ArrayList());
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
				
				h.put("ID_PERMOHONANBANTUUNIT",rs.getString("ID_PERMOHONANBANTUUNIT") == null ? "" : rs.getString("ID_PERMOHONANBANTUUNIT"));
				h.put("ID_PEMOHON",rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("ID_PELULUS",rs.getString("ID_PELULUS") == null ? "" : rs.getString("ID_PELULUS"));
				h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
				h.put("TARIKH_LULUS",rs.getString("TARIKH_LULUS") == null ? "" : rs.getString("TARIKH_LULUS"));
				h.put("TARIKH_MULA",rs.getString("TARIKH_MULA") == null ? "" : rs.getString("TARIKH_MULA"));
				h.put("TARIKH_AKHIR",rs.getString("TARIKH_AKHIR") == null ? "" : rs.getString("TARIKH_AKHIR"));
				h.put("CATATAN_PEMOHON",rs.getString("CATATAN_PEMOHON") == null ? "" : rs.getString("CATATAN_PEMOHON"));
				h.put("CATATAN_PELULUS",rs.getString("CATATAN_PELULUS") == null ? "" : rs.getString("CATATAN_PELULUS"));
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("NO_BANTUUNIT",rs.getString("NO_BANTUUNIT") == null ? "" : rs.getString("NO_BANTUUNIT"));
				h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS"));
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				//h.put("DAERAH_JAGAAN",rs.getString("DAERAH_JAGAAN") == null ? "" : rs.getString("DAERAH_JAGAAN"));	
				h.put("DAERAH_JAGAAN","");	//aishah comment
				h.put("NEGERI_PELULUS",rs.getString("NEGERI_PELULUS") == null ? "" : rs.getString("NEGERI_PELULUS"));
				h.put("NEGERI_PEMOHON",rs.getString("NEGERI_PEMOHON") == null ? "" : rs.getString("NEGERI_PEMOHON"));
				h.put("PEJABAT_PEMOHON",rs.getString("PEJABAT_PEMOHON") == null ? "" : rs.getString("PEJABAT_PEMOHON"));
				h.put("PEJABAT_PELULUS",rs.getString("PEJABAT_PELULUS") == null ? "" : rs.getString("PEJABAT_PELULUS"));
				
				
				DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
		        String hariini = sdf.format(date);	        
		        Date date1 = sdf.parse(hariini);
		        String tarikhAkhir = "";
		       
		        if(rs.getString("TARIKH_AKHIR")!=null){
		    	   tarikhAkhir = rs.getString("TARIKH_AKHIR");
		        }
		       
		        Date date2 = sdf.parse(tarikhAkhir);		       
		        boolean tempohTamat = false;
		       
		        if(date2.before(date1) ){
		    	   tempohTamat = true;
		        }	
		       
		        h.put("tempohTamat",tempohTamat);
				
				listBU.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listBU;

	}
	
	
	public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
		try {
			setupPageDefaultPut();
			String scrolPosition = getParam("scrolPosition"+command);
			myLogger.info(" ------- scrolPosition :"+scrolPosition);
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
		
	
	public void deleteBU(HttpSession session,String ID_PERMOHONANBANTUUNIT) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
				
			r.clear();
			r.add("ID_PERMOHONANBANTUUNIT",ID_PERMOHONANBANTUUNIT);
			sql = r.getSQLDelete("TBLPERMOHONANBANTUUNIT");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLPERMOHONANBANTUUNIT [ID_PERMOHONANBANTUUNIT : "+ID_PERMOHONANBANTUUNIT+"] Deleted");
		
			conn.commit();
		
	} 
	catch (SQLException se) { 
		myLogger.error(se);
    	try {
    		conn.rollback();
    	} catch (SQLException se2) {
    		throw new Exception("Rollback error:"+se2.getMessage());
    	}
    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	}
	catch (Exception re) {
		throw re;
	}finally {
		if (db != null)
			db.close();
	}
}
	
		
	@SuppressWarnings("unchecked")
	public List listNegeri(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listNegeri = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT LAYER, ID_NEGERI, NAMA_NEGERI FROM "+
					" ( "+
					//" SELECT 1 AS LAYER,'ALL' AS ID_NEGERI, 'KESELURUHAN NEGARA' AS NAMA_NEGERI FROM DUAL "+
					//" UNION "+
					" SELECT 2 AS LAYER, TO_CHAR(N.ID_NEGERI) AS ID_NEGERI, N.NAMA_NEGERI FROM TBLRUJNEGERI N WHERE ID_NEGERI NOT IN (0,17) "+
					" ) "+
					" WHERE ID_NEGERI IS NOT NULL ";
			
			if(USER_ROLE.equals("user_ppk") || (USER_ROLE.equals("adminppk") && !USER_NEGERI.equals("16")))
			{
				//sql += " AND ID_NEGERI = '"+USER_NEGERI+"' ";
			}
			
			sql += "ORDER BY LAYER,NAMA_NEGERI ";			
			myLogger.info(" V3: SQL listNegeri :"+ sql);
			rs = stmt.executeQuery(sql);
			listNegeri = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				listNegeri.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listNegeri;

	}
	
	
	@SuppressWarnings("unchecked")
	public List listUnit(HttpSession session,String ID_NEGERI,String ID_UNIT_PEMOHON)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listUnit = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		try {
			db = new Db();
			stmt = db.getStatement(); 
			sql = " SELECT LAYER, ID_UNIT,NAMA_UNIT,NAMA_NEGERI,ID_NEGERI,ID_JENISPEJABAT FROM "+
				" ( " +
			/*	"SELECT 1 AS LAYER, 'ALL' AS ID_UNIT, ";
					if(USER_ROLE.equals("adminppk") && USER_NEGERI.equals("16"))
					{
						if(ID_NEGERI.equals("ALL") || ID_NEGERI.equals(""))
						{
							sql += " 'UNIT KESELURUHAN NEGARA' ";
						}
						else
						{
							sql += " 'UNIT KESELURUHAN NEGERI' ";
						}
						
					}
					else
					{
						sql += " 'UNIT KESELURUHAN NEGERI' ";
					}
					
					sql += " AS NAMA_UNIT, '' AS NAMA_NEGERI, '' AS ID_NEGERI FROM DUAL "+*/
					//" UNION "+
					" SELECT 2 AS LAYER, ID_JENISPEJABAT,TO_CHAR(ID_PEJABATJKPTG) AS ID_UNIT, UPPER(NAMA_PEJABAT) AS NAMA_UNIT, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI, " +
					" TO_CHAR(P.ID_NEGERI) AS ID_NEGERI FROM TBLRUJPEJABATJKPTG P,TBLRUJNEGERI N  "+
					" WHERE ID_SEKSYEN = '2' AND ID_JENISPEJABAT IN " +
					//"(21,22,23) " +
					"(22,24) ";
					
					if(!ID_UNIT_PEMOHON.equals(""))
					{
						sql += " AND ID_PEJABATJKPTG != "+ID_UNIT_PEMOHON+" ";
					}
					
					sql += " AND P.ID_NEGERI = N.ID_NEGERI";
					/*
					if(USER_ROLE.equals("adminppk"))
					{
						if(!ID_NEGERI.equals("") && !ID_NEGERI.equals("ALL"))
						{
							sql += " AND P.ID_NEGERI = '"+ID_NEGERI+"' ";
						}
						else
						{
							if(!USER_NEGERI.equals("16"))
							{
								sql += " AND P.ID_NEGERI = '"+USER_NEGERI+"' ";
							}
						}
					}
					*/
			sql += " AND P.ID_NEGERI = '"+ID_NEGERI+"' ";
					sql += " )  "+
					" WHERE ID_UNIT IS NOT NULL ";	
					/*
					if(USER_ROLE.equals("user_ppk"))
					{
						sql += " AND ID_UNIT = '"+USER_UNIT+"' ";
					}
					*/
			
			sql += " ORDER BY LAYER,NAMA_NEGERI, NAMA_UNIT  ";			
			myLogger.info(" V3: SQL listUnit :"+ sql);
			rs = stmt.executeQuery(sql);
			listUnit = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT").toUpperCase());
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT").toUpperCase());
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT").toUpperCase());
				
				listUnit.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listUnit;

	}
	
	
	@SuppressWarnings("unchecked")
	public List listPelulus(HttpSession session,String ID_NEGERI)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPelulus = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement(); 
			sql = " SELECT U.USER_ID AS ID_PELULUS, UPPER(U.USER_NAME) AS NAMA_PELULUS, UPPER(J.KETERANGAN) AS JAWATAN FROM USERS U, USERS_INTERNAL UI, TBLRUJPEJABATJKPTG PEJ, TBLRUJJAWATAN J "+
					 " WHERE U.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_SEKSYEN = '2' "+
					 " AND UI.ID_JAWATAN = J.ID_JAWATAN "+
					 " AND UI.ID_SEKSYEN = '2' AND (U.USER_ROLE = 'adminppk' OR U.USER_NAME IN (SELECT USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk')) ";
			sql += " AND PEJ.ID_NEGERI = '"+ID_NEGERI+"'" +
					" AND UI.FLAG_AKTIF != 2 ";			
			sql += " ORDER BY U.USER_NAME ";			
			myLogger.info(" V3: SQL listPelulus :"+ sql);
			rs = stmt.executeQuery(sql);
			listPelulus = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_PELULUS",rs.getString("ID_PELULUS") == null ? "" : rs.getString("ID_PELULUS").toUpperCase());
				h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS").toUpperCase());
				h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN").toUpperCase());
				listPelulus.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPelulus;

	}
	
	
	
	public String checkDuplicateDate(HttpSession session, String ID_PEMOHON,String ID_PERMOHONANBANTUUNIT,
			String TARIKH_MULA, String TARIKH_AKHIR,String BU_ID_NEGERI,String BU_ID_UNIT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			db = new Db();
			stmt = db.getStatement();
			String NO_BANTUUNIT="";
			sql = " SELECT T.NO_BANTUUNIT FROM TBLPERMOHONANBANTUUNIT T "+
					" WHERE T.ID_PERMOHONANBANTUUNIT IS NOT NULL " +
					//" AND T.ID_STATUS != '3' " +					
		 			" AND (CASE  "+
					" WHEN T.ID_STATUS = '2' "+
					" THEN  "+
					" (CASE WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') < TO_DATE(TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+
					" THEN 4 "+
					" WHEN TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') > TO_DATE(TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+
					" THEN 5 "+
					" END) "+
					" ELSE T.ID_STATUS END "+
					" ) NOT IN (3,5) "+				
					" AND T.ID_PEMOHON = '"+ID_PEMOHON+"' AND T.ID_NEGERI = '"+BU_ID_NEGERI+"' AND T.ID_UNIT = '"+BU_ID_UNIT+"' "+
					" AND (   (    TO_DATE (TO_CHAR (t.tarikh_mula, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY') >= "+
					" (TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" AND (TO_DATE (TO_CHAR (t.tarikh_akhir, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY' "+
					" ) >= TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" ) "+
					" OR (    TO_DATE (TO_CHAR (t.tarikh_mula, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY') <= "+
					" (TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" AND (TO_DATE (TO_CHAR (t.tarikh_akhir, 'DD/MM/YYYY'), "+
					" 'DD/MM/YYYY' "+
					" ) >= TO_DATE ('"+TARIKH_MULA+"', 'DD/MM/YYYY') "+
					" ) "+
					" ) "+
					" ) "+
					" AND (   TO_DATE (TO_CHAR (t.tarikh_akhir, 'DD/MM/YYYY'), 'DD/MM/YYYY') <= "+
					" TO_DATE ('"+TARIKH_AKHIR+"', 'DD/MM/YYYY') "+
					" OR TO_DATE (TO_CHAR (t.tarikh_mula, 'DD/MM/YYYY'), 'DD/MM/YYYY') <= "+
					" TO_DATE ('"+TARIKH_AKHIR+"', 'DD/MM/YYYY') "+
					" ) ";	
			
					if(!ID_PERMOHONANBANTUUNIT.equals(""))
					{
						sql += " AND  T.ID_PERMOHONANBANTUUNIT != '"+ID_PERMOHONANBANTUUNIT+"' ";
					}
					
				myLogger.info(" OT : checkDuplicateDate :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				int bil = 0;
				while (rs.next()) {				
					bil ++;
					if(bil>1 && rs.getString("NO_BANTUUNIT")!=null && !rs.getString("NO_BANTUUNIT").equals("") )
					{
						NO_BANTUUNIT += ", ";
					}	
					NO_BANTUUNIT += (rs.getString("NO_BANTUUNIT") == null ? "" : rs.getString("NO_BANTUUNIT"));
				}
			
			return NO_BANTUUNIT;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String getKodNegeri(HttpSession session, String ID_NEGERI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String KOD_NEGERI="";
			sql = " SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"+ID_NEGERI+"' ";	
			myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					KOD_NEGERI = (rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				}			
			return KOD_NEGERI;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getIdUnitByUserId(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String ID_PEJABATJKPTG="";
			sql = " SELECT ID_PEJABATJKPTG FROM USERS_INTERNAL WHERE USER_ID = '"+USER_ID+"' ";	
			myLogger.info(" OT : getIdUnitByUserId :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					ID_PEJABATJKPTG = (rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				}			
			return ID_PEJABATJKPTG;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	
	public static synchronized int getSeqNoBU(String tahun,String id_negeri,String kod_modul)
			throws DbException {

		Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQESNOBU WHERE ");
			sb.append(" TAHUN = '" + tahun + "'");
			sb.append(" AND ID_NEGERI = '" + id_negeri + "'");
			sb.append(" AND KOD_MODUL = '" + kod_modul + "'");

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			
			myLogger.info("found :"+found);
			
			
			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseNoBU(tahun,id_negeri,kod_modul);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNoBU(tahun,id_negeri,kod_modul);
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}

		return seqno;
	}
		 public static void increaseNoBU(String tahun,String id_negeri,String kod_modul) throws DbException {

				Db db = null;

				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE TBLRUJSEQESNOBU  SET ");
				sb.append("no_turutan = no_turutan + 1 ");
				sb.append(" WHERE ");
				sb.append(" TAHUN = '" + tahun + "'");
				sb.append(" AND id_negeri = '" + id_negeri + "'");
				sb.append(" AND kod_modul = '" + kod_modul + "'");


				try {
					db = new Db();
					try {
						db.getStatement().executeUpdate(sb.toString());
					} catch (SQLException x) {
						x.printStackTrace();
					}
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}
			}
		 public static void addNoBU(String tahun,String id_negeri,String kod_modul) throws DbException {

				Db db = null;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO TBLRUJSEQESNOBU (TAHUN,ID_NEGERI,KOD_MODUL,NO_TURUTAN)");
				sb.append(" VALUES (");
				sb.append("'" + tahun + "',");
				sb.append("'" + id_negeri + "',");
				sb.append("'" + kod_modul + "'");
				sb.append(",1)"); // initial value

				try {
					db = new Db();
					db.getStatement().executeUpdate(sb.toString());
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public int daysBetween(Date d1, Date d2){
             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
         }
		 
		
		 public void hantarEmel(HttpSession session,String ID_PERMOHONANBANTUUNIT) throws Exception {
				
				myLogger.info("MASUK FUNCTION EMEL");	
				
				EmailProperty pro = EmailProperty.getInstance();
				EmailSender email = EmailSender.getInstance();
				email.FROM = pro.getFrom();
				email.MULTIPLE_RECIEPIENT = new String[1];
				String subject = "";
				
				String content = "";
				
				Hashtable viewBU = viewBU(session, ID_PERMOHONANBANTUUNIT);				
				String ID_STATUS = (String)viewBU.get("ID_STATUS");
				String CATATAN_PELULUS = (String)viewBU.get("CATATAN_PELULUS");
				String CATATAN_PEMOHON = (String)viewBU.get("CATATAN_PEMOHON");
				String NO_BANTUUNIT = (String)viewBU.get("NO_BANTUUNIT");
				String NAMA_PEMOHON = (String)viewBU.get("NAMA_PEMOHON");
				String NAMA_PELULUS = (String)viewBU.get("NAMA_PELULUS");
				String EMEL_PEMOHON = (String)viewBU.get("EMEL_PEMOHON");
				String ID_UNIT = (String)viewBU.get("ID_UNIT"); 
				String PEJABAT_PEMOHON = (String)viewBU.get("PEJABAT_PEMOHON"); 
				String NAMA_UNIT = (String)viewBU.get("NAMA_UNIT");
				//String EMEL_PELULUS = (String)maklumatOT_USER.get("EMEL_PELULUS");
				myLogger.info("ID_STATUS===="+ID_STATUS);
				if(ID_STATUS.equals("2") || ID_STATUS.equals("3"))//permohonan lulus or tolak
				{
					subject = " KEPUTUSAN PERMOHONAN BANTU UTILITI NEGERI : "+NO_BANTUUNIT;
					content = " Tujuan : Pemakluman status Permohonan Bantu Utiliti Negeri. ";
					content += "<br><br>";
					if(ID_STATUS.equals("2"))
					{
						content = " Permohonan bantu utiliti negeri anda adalah diluluskan oleh "+NAMA_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
					}else
					{
						content = " Permohonan bantu utiliti negeri anda adalah ditolak oleh "+NAMA_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
					}
					
					content += "<br><br>Catatan Pelulus: "+CATATAN_PELULUS;
					content += "<br><br>";
					content += "<br><br><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em> ";
					
					email.MULTIPLE_RECIEPIENT[0] = EMEL_PEMOHON;
				}
				else if(ID_STATUS.equals("1"))//pemohonan baru
				{
					subject = " PERMOHONAN BARU BANTU UTILITI NEGERI : "+NO_BANTUUNIT;
					content = " Tujuan : Pemakluman bahawa anda menerima permohonan Bantu Utiliti Negeri.";
					
					content += "<br> Permohonan telah dibuat oleh "+NAMA_PEMOHON+" daripada "+PEJABAT_PEMOHON+" pada unit "+NAMA_UNIT+"";
					content += "<br> Catatan Pemohon : "+CATATAN_PEMOHON;
					content += "<br><br> Tindakan : Sila buat kelulusan bagi permohonan di MyeTaPP.";
					content += "<br> Menu : Login ke MyeTaPP >> Utility >> BANTU UTILITI NEGERI";
			
					content += "<br><br><br>";
					content += "<br><br><em>Emel ini dijana oleh sistem MyeTaPP dan tidak perlu dibalas.</em> ";
					//email.MULTIPLE_RECIEPIENT[0] = EMEL_PELULUS;
					
					List listEmelPelulus = listEmelPelulus(session, ID_UNIT);
					email.MULTIPLE_RECIEPIENT = new String[listEmelPelulus.size()];
					for(int i = 0; i < listEmelPelulus.size();i++)
					{
						Map m = (Map) listEmelPelulus.get(i);
						String EMEL = (String) m.get("EMEL");
						//int COUNT_EMEL = (int)maklumatTindakan.get("COUNT_EMEL");
						email.MULTIPLE_RECIEPIENT[i] = EMEL;						
					}
				}
				
				
				email.SUBJECT = subject;
				email.MESSAGE = content;		
				
				//listPenggunaMengikutRole = listPenggunaMengikutRole(session,"adminekptg",(String) viewPengguna.get("ID_NEGERI"));
				
				//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
				/*
				email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
				for(int i = 0; i < listPenggunaMengikutRole.size();i++)
				   {			   
					   Map m = (Map) listPenggunaMengikutRole.get(i);
					   myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
					   email.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");			  
				 }
				*/
				//email.TO_CC = new String[1];		
				//email.TO_CC[0] = "razman.zainal@gmail.com";
				email.sendEmail();		 
			 }
		 
		 public List listEmelPelulus(HttpSession session, String ID_UNIT)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listEmelPelulus = null;
				String sql = "";
				
				String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
				this.context.put("USER_NEGERI",USER_NEGERI);
				
				try {
					db = new Db();
					stmt = db.getStatement();
					myLogger.info("------------masuk dlm emel UPP bantu unit-----------");
					String checkPermohonanNegeri = "";
					checkPermohonanNegeri = checkIfPermohonanNegeri(session,ID_UNIT,USER_NEGERI);
					
					sql = " SELECT DISTINCT UI.EMEL FROM USERS U, USERS_INTERNAL UI, (  "+
							" SELECT ROLE_ID, USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk' "+ 
							" ) UR "+
							" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+)  "+
							" AND (U.USER_ROLE = 'adminppk' OR UR.ROLE_ID = 'adminppk' )  ";
							if(checkPermohonanNegeri.equals("Y")){
								sql+=" AND UI.ID_PEJABATJKPTG = 26";
							}else{
								sql+=" AND UI.ID_PEJABATJKPTG = "+ID_UNIT;
							}
					sql+=" AND UI.ID_JAWATAN = 5  "+ //KPP
						" AND UI.EMEL IS NOT NULL ";
					
					myLogger.info(" SQL : listEmelPelulus :"+ sql);			
					rs = stmt.executeQuery(sql);
					listEmelPelulus = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));	
						listEmelPelulus.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listEmelPelulus;
			}
		 
		 
		 public boolean getJawatanKPP(HttpSession session, String USER_ID) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					boolean JawatanKpp=false;
					sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
							" PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL," +
							" J.KETERANGAN AS NAMA_JAWATAN , J.SHORTNAME_JAWATAN KOD_JWTN" +
							" FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D " +
							" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " +
							" AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
							" AND UI.ID_NEGERI = N.ID_NEGERI(+) " +
							//" AND UI.ID_NEGERI = 11" +
							" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) " +
							" AND UI.ID_DAERAH = D.ID_DAERAH(+) " +
							" AND J.ID_JAWATAN IN (5)  " +
							//" AND pej.id_jenispejabat IN (24) " +
							" AND UI.FLAG_AKTIF != 2 " +
							" AND U.USER_ID = "+USER_ID;
					
					myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
					
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {				
							
							JawatanKpp = true;
						}		
						
						myLogger.info("JawatanKpp==="+JawatanKpp);
					return JawatanKpp;
				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public boolean getJawatanPengarahHQ(HttpSession session, String USER_ID) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					boolean JawatanKpp=false;
					sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
							" PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL," +
							" J.KETERANGAN AS NAMA_JAWATAN , J.SHORTNAME_JAWATAN KOD_JWTN" +
							" FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D " +
							" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " +
							" AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
							" AND UI.ID_NEGERI = N.ID_NEGERI(+) " +
							" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) " +
							" AND UI.ID_DAERAH = D.ID_DAERAH(+) " +
							" AND J.ID_JAWATAN IN (4)  " +
							" AND pej.id_jenispejabat IN (21) " +
							" AND UI.FLAG_AKTIF != 2 " +
							" AND U.USER_ID = "+USER_ID;
					
					myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
					
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {				
							
							JawatanKpp = true;
						}		
						
						myLogger.info("PengarahHQ======"+JawatanKpp);
					return JawatanKpp;
				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
			}
		 
		 public String checkIfPermohonanNegeri(HttpSession session, String UNIT_ID, String USER_NEGERI) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					String permohonanNegeri="";
					sql = "  SELECT A.ID_PEJABATJKPTG " +
							"  FROM TBLRUJPEJABATJKPTG A " +
							" WHERE A.ID_JENISPEJABAT=24 " +
							" AND A.ID_PEJABATJKPTG =  "+UNIT_ID;
							myLogger.info(" checkIfPermohonanNegeri BESAR:::: " + sql.toUpperCase());
					
						rs = stmt.executeQuery(sql);				
						if (rs.next()) {				
							permohonanNegeri = "Y";
							
						}else{
							myLogger.info("masuk else");
							String negeripej = "";
							
							sql = "  SELECT A.ID_NEGERI " +
									" FROM TBLRUJPEJABATJKPTG A " +
									" WHERE " +
									" A.ID_PEJABATJKPTG =  "+UNIT_ID;
									myLogger.info(" checkIfPermohonanNegeri KECIK:::: " + sql.toUpperCase());
							
								rs = stmt.executeQuery(sql);	
								if (rs.next()) {

									
									negeripej = rs.getString("ID_NEGERI");
									if(negeripej.equals(USER_NEGERI)){
										permohonanNegeri = "";
									}else{
										permohonanNegeri = "Y";
									}
								}
								
								
							
						}
					return permohonanNegeri;
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