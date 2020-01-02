package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;
import ekptg.model.ppk.PerKebenaranKemaskiniData;

public class KebenaranKemaskini extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(KebenaranKemaskini.class);
	PerKebenaranKemaskiniData logic = new PerKebenaranKemaskiniData();
	String skrin_name = "app/ppk/kebenaranKemaskini/KKindex.jsp";
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		List listLogOT_forPrint = null;
		List listTT = null;
		List listPKK = null;
		List listPOT = null;
		List listPOT_Print = null;
		List listDates = null;
		List listLogOT = null;
		Hashtable viewTT = null;
		Hashtable viewPMT = null;
		Hashtable viewPOT = null;
		List listAktiviti = null;
		List listNegeri = null;
		List listUnit = null;
		List listPelulus = null;
		boolean checkTransaksiTT = true;
		boolean checkTransaksiPMT = true;
		boolean checkTransaksiPOT = true;
		boolean checkDB = true;
		boolean checkAllTT = true;
		boolean checkAllPMT = true;
		boolean checkAllPOT = true;
		String checkSyntaxQuery = "";
		
		
		Hashtable viewPKK = null;
		
		defaultPut();
		
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		myLogger.info("Permohonan Kebenaran Kemaskini command : "+command);
		this.context.put("command",command);
		String USER_ROLE = (String) session.getAttribute("myrole");
		this.context.put("USER_ROLE",USER_ROLE);
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		this.context.put("USER_UNIT",USER_UNIT);	
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);
		
		if(command.equals("showSenaraiTT") || command.equals("deleteTT") || command.equals("insertTT"))
		{			
			String ID_TRANSAKSI = getParam("ID_TRANSAKSI");
			if(command.equals("deleteTT"))
			{
				//function delete
				deleteTT(session,ID_TRANSAKSI);
			}
			else if(command.equals("insertTT"))
			{
				//function insert
				saveTableTT(session,"");
			}
			this.context.put("FLAG_TT_CARIAN", getParam("FLAG_TT_CARIAN"));
			this.context.put("TT_OPENCLOSE_CARIAN",getParam("TT_OPENCLOSE_CARIAN"));
			String action = getParam("action");
			listTT = listTT(session);
			setupPageList(session, action, listTT, "listTT",command,"div_SenaraiTT");
			
			skrin_name = "app/admin/OT/OTSenaraiTT.jsp";
		}
		else if(command.equals("checkTT"))
		{
			String ID_TRANSAKSI = getParam("ID_TRANSAKSI");
			String ID_SEKSYEN = getParam("ID_SEKSYEN_"+ID_TRANSAKSI);
			String NAMA_TABLE = getParam("NAMA_TABLE_"+ID_TRANSAKSI);
			String FIELD_CHECK = getParam("FIELD_CHECK_"+ID_TRANSAKSI);
			String QUERY_NOFAIL = getParam("QUERY_NOFAIL_"+ID_TRANSAKSI);
			String checkTransaksiTT_msg = "";
			myLogger.info(" NAMA_TABLE : "+NAMA_TABLE+" FIELD_CHECK : "+FIELD_CHECK+" ID_SEKSYEN : "+ID_SEKSYEN+" ID_TRANSAKSI : "+ID_TRANSAKSI);
				
			if(!NAMA_TABLE.equals(""))
			{
				checkTransaksiTT = checkTransaksiTT(session, ID_TRANSAKSI, ID_SEKSYEN, NAMA_TABLE);
				myLogger.info(" checkTransaksi : "+checkTransaksiTT);
				if(checkTransaksiTT==false)
				{
					checkAllTT = false;
					checkTransaksiTT_msg += "<font color='red' class='blink' >** Nama Table '<b>"+NAMA_TABLE.toUpperCase()+"</b>' telah wujud dalam senarai setup transaksi!</font><br>";				
				}
			}
			
			if(!FIELD_CHECK.equals("") && !NAMA_TABLE.equals(""))
			{
				checkDB = checkDB(session,FIELD_CHECK, NAMA_TABLE);
				if(checkDB==false)
				{
					checkAllTT = false;
					checkTransaksiTT_msg += "<font color='red' class='blink' >** Nama Table '<b>"+NAMA_TABLE.toUpperCase()+"</b>' dan field '<b>"+FIELD_CHECK.toUpperCase()+"</b>' tidak wujud didalam pengkalan data!</font><br>";	
				}
			}
			
			if(!QUERY_NOFAIL.equals(""))
			{
				checkSyntaxQuery = checkSyntaxQuery(session,QUERY_NOFAIL);
				if(!checkSyntaxQuery.equals(""))
				{
					checkAllTT = false;
					checkTransaksiTT_msg += "<font color='red' class='blink' >** Terdapat error pada query! Error Mesej : "+checkSyntaxQuery+"</font><br>";					
				}
			}
			
			this.context.put("checkTransaksiTT_msg", checkTransaksiTT_msg);
			this.context.put("ID_TRANSAKSI", ID_TRANSAKSI);	
			this.context.put("checkTransaksiTT", checkAllTT);
			skrin_name = "app/admin/OT/OTcheckTT.jsp";
		}
		else if(command.equals("addTT") || command.equals("editTT") || command.equals("saveTT") || command.equals("closeTT"))
		{
			String ID_TRANSAKSI = getParam("ID_TRANSAKSI");
			if(command.equals("saveTT") || command.equals("closeTT"))
			{
				if(command.equals("saveTT"))
				{
					//update function
					saveTableTT(session,ID_TRANSAKSI);
				}			
				skrin_name = "app/admin/OT/OTrowTT.jsp";
			}
			else
			{
				skrin_name = "app/admin/OT/OTeditTT.jsp";
			}
						
			
			viewTT = viewTT(session, ID_TRANSAKSI);
			this.context.put("viewTT", viewTT);
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			
		}
		else if(command.equals("showCARIAN_TT"))
		{
			this.context.put("FLAG_TT_CARIAN", getParam("FLAG_TT_CARIAN"));
			this.context.put("TT_OPENCLOSE_CARIAN",getParam("TT_OPENCLOSE_CARIAN"));
			skrin_name = "app/admin/OT/OTskrinCarianTT.jsp";
		}
		
		
		
		//----------------------------------------------------------------------------------------------------
		
		//ecah2
		else if(command.equals("showSenaraiPKK") || command.equals("deletePMT") || command.equals("insertPKK"))
		{
			
			
			
			
			int ID_PKE = parseInt(getParam("ID_PKE"));
			if(command.equals("deletePMT"))
			{
				//function delete
				//deletePMT(session,ID_PKE);
			}
			else if(command.equals("insertPKK"))
			{
				//function insert

				ID_PKE = insertTBLPPKKEBENARANEDIT(session,0);
				myLogger.info(" ID_PKE :::::::::: "+ID_PKE);
				if(ID_PKE!=0){
					insertTBLPPKKEBENARANSTAFF(session,ID_PKE);
					
					
				}
				
			}
			this.context.put("FLAG_PMT_CARIAN", getParam("FLAG_PMT_CARIAN"));
			this.context.put("PMT_OPENCLOSE_CARIAN",getParam("PMT_OPENCLOSE_CARIAN"));
			String action = getParam("action");
			listPKK = listPKK(session,USER_ID_SYSTEM);
			setupPageList(session, action, listPKK, "listPKK",command,"div_SenaraiPKK");
			
			skrin_name = "app/ppk/kebenaranKemaskini/KKSenaraiPKK.jsp";
		}
		else if(command.equals("checkPMT"))
		{
			String ID_MINITTRANSAKSI = getParam("ID_MINITTRANSAKSI");
			String ID_TRANSAKSI = getParam("ID_TRANSAKSI_"+ID_MINITTRANSAKSI);
			String checkTransaksiPMT_msg = "";
			myLogger.info(" ID_MINITTRANSAKSI : "+ID_MINITTRANSAKSI+" ID_TRANSAKSI : "+ID_TRANSAKSI);
				
			if(!ID_TRANSAKSI.equals(""))
			{
				checkTransaksiPMT = checkTransaksiPMT(session, ID_TRANSAKSI, ID_MINITTRANSAKSI);
				myLogger.info(" checkTransaksiPMT : "+checkTransaksiPMT);
				if(checkTransaksiPMT==false)
				{
					checkAllPMT = false;
					viewTT = viewTT(session, ID_TRANSAKSI);
					checkTransaksiPMT_msg += "<font color='red' class='blink' >** Nama Aktiviti '<b>"+(String)viewTT.get("NAMA_AKTIVITI")+"</b>' telah wujud dalam senarai setup minit transaksi!</font><br>";				
				}
			}			
			this.context.put("checkTransaksiPMT_msg", checkTransaksiPMT_msg);
			this.context.put("ID_TRANSAKSI", ID_TRANSAKSI);
			this.context.put("ID_MINITTRANSAKSI", ID_MINITTRANSAKSI);
			this.context.put("checkTransaksiPMT", checkAllPMT);
			skrin_name = "app/admin/OT/OTcheckPMT.jsp";
		}
		/*else if(command.equals("addPKK") || command.equals("editPMT") || command.equals("savePMT") || command.equals("closePMT"))*/
		else if(command.equals("addPKK"))
		{
			String ID_MINITTRANSAKSI = getParam("ID_MINITTRANSAKSI");
			String ID_PKE = getParam("ID_PKE");
			if(command.equals("savePMT") || command.equals("closePMT"))
			{
				if(command.equals("savePMT"))
				{
					//update function
					saveTablePMT(session,ID_MINITTRANSAKSI);
				}			
				skrin_name = "app/ppk/kebenaranKemaskini/KKPermohonan.jsp";
			}
			else
			{
				//listAktiviti = listAktiviti(session,"2",ID_MINITTRANSAKSI);
				//this.context.put("listAktiviti", listAktiviti);
				listNegeri = listNegeri(session);
				this.context.put("listNegeri",listNegeri);
				listUnit = listUnit(session,"");
				this.context.put("listUnit",listUnit);
				//ecah
				
				skrin_name = "app/ppk/kebenaranKemaskini/KKPermohonan.jsp";
			}
			viewPKK = viewPKK(session, ID_PKE);
			this.context.put("viewPMT", viewPMT);
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			
			this.context.put("viewPKK", viewPKK);
			
		}
		else if(command.equals("showCARIAN_PKK"))
		{
			this.context.put("FLAG_PKK_CARIAN", getParam("FLAG_PKK_CARIAN"));
			this.context.put("PKK_OPENCLOSE_CARIAN",getParam("PKK_OPENCLOSE_CARIAN"));
			listNegeri = listNegeri(session);
			this.context.put("listNegeri",listNegeri);
			listUnit = listUnit(session,"");
			this.context.put("listUnit",listUnit);
			skrin_name = "app/ppk/kebenaranKemaskini/KKskrinCarianPKK.jsp";
		}
		
		
		
		//----------------------------------------------------------------------------------------------------
		
		else if(command.equals("showSenaraiPOT_Print"))
		{			
			
			listPOT = listPOT(session);
			this.context.put("listPOT",listPOT);
			this.context.put("listLogOT_forPrint",listPOT);			
			skrin_name = "app/admin/OT/OTSenaraiPOT_Print.jsp";
		}
		else if(command.equals("showSenaraiPOT") || command.equals("deletePOT") || command.equals("insertPOT"))
		{			
			String ID_PERMOHONANOT = getParam("ID_PERMOHONANOT");
			if(command.equals("deletePOT"))
			{
				//function delete
				deletePOT(session,ID_PERMOHONANOT);
			}
			else if(command.equals("insertPOT"))
			{
				//function insert
				saveTablePOT(session,"");
			}
			this.context.put("FLAG_POT_CARIAN", getParam("FLAG_POT_CARIAN"));
			this.context.put("POT_OPENCLOSE_CARIAN",getParam("POT_OPENCLOSE_CARIAN"));
			String action = getParam("action");
			listPOT = listPOT(session);
			setupPageList(session, action, listPOT, "listPOT",command,"div_SenaraiPOT");
			
			skrin_name = "app/admin/OT/OTSenaraiPOT.jsp";
		}
		
		else if(command.equals("addPOT") || command.equals("editPOT") || command.equals("savePOT") || command.equals("closePOT"))
		{
			String ID_PERMOHONANOT = getParam("ID_PERMOHONANOT");
			if(command.equals("closePOT"))
			{
							
				skrin_name = "app/admin/OT/OTrowPOT.jsp";
			}
			else
			{
				if(ID_PERMOHONANOT.equals(""))
				{
					listPelulus = listPelulus(session,USER_NEGERI);
					this.context.put("listPelulus", listPelulus);
				}
				
				if(command.equals("savePOT"))
				{
					//update function
					//saveTablePOT(session,ID_MINITTRANSAKSI);
					saveTablePOT(session,ID_PERMOHONANOT);
				}
				
				skrin_name = "app/admin/OT/OTeditPOT.jsp";
			}
			viewPOT = viewPOT(session, ID_PERMOHONANOT);
			this.context.put("viewPOT", viewPOT);
			if(!ID_PERMOHONANOT.equals(""))
			{
				String DATE_MULA = (String)viewPOT.get("TARIKH_MULA");
				String DATE_AKHIR = (String)viewPOT.get("TARIKH_AKHIR");
				listDates = checkLogTransaksi(session,DATE_MULA,DATE_AKHIR,ID_PERMOHONANOT,(String)viewPOT.get("FLAG_CUTI"));			
				this.context.put("listDates",listDates);
			}
			
			this.context.put("rowCss", getParam("rowCss"));
			this.context.put("BIL", getParam("BIL"));
			
		}
		else if(command.equals("showCARIAN_POT"))
		{
			this.context.put("FLAG_POT_CARIAN", getParam("FLAG_POT_CARIAN"));
			this.context.put("POT_OPENCLOSE_CARIAN",getParam("POT_OPENCLOSE_CARIAN"));
			listNegeri = listNegeri(session);
			this.context.put("listNegeri",listNegeri);
			listUnit = listUnit(session,"");
			this.context.put("listUnit",listUnit);
			skrin_name = "app/admin/OT/OTskrinCarianPOT.jsp";
		}
		else if(command.equals("showUnit_PKK"))
		{//ecah1
			String PKK_ID_NEGERI = getParam("PKK_ID_NEGERI"); 
			System.out.println("PKK_ID_NEGERI====="+PKK_ID_NEGERI);
			listUnit = listUnit(session,PKK_ID_NEGERI);
			this.context.put("listUnit",listUnit);
			skrin_name = "app/ppk/kebenaranKemaskini/PKKUnitDropDown.jsp";
		}
		else if(command.equals("showPrintPOT"))
		{
			String ID_PERMOHONANOT = getParam("ID_PERMOHONANOT"); 
			String NO_OT = getParam("NO_OT"); 
			String WAKTU_KERJA = getParam("WAKTU_KERJA"); 
			String FLAG_CUTI = getParam("FLAG_CUTI"); 
			String TARIKH_MULA = getParam("TARIKH_MULA"); 
			String TARIKH_AKHIR = getParam("TARIKH_AKHIR"); 
			this.context.put("ID_PERMOHONANOT",ID_PERMOHONANOT);
			this.context.put("NO_OT",NO_OT);
			//viewPOT = viewPOT(session, ID_PERMOHONANOT);
			
			listLogOT_forPrint = listLogOT_forPrint(session,ID_PERMOHONANOT,
					 "2", TARIKH_MULA,TARIKH_AKHIR,WAKTU_KERJA,
					 FLAG_CUTI);
			this.context.put("listLogOT_forPrint",listLogOT_forPrint);
			
			
			skrin_name = "app/admin/OT/OTlistOT_POT_Print.jsp";
		}
		
		
		else if(command.equals("showListLogOT_POT"))
		{			
			String ID_PERMOHONANOT = getParam("ID_PERMOHONANOT"); 
			String BIL = getParam("BIL");
			String FLAG_CUTI = getParam("FLAG_CUTI");
			String TARIKH_OT = getParam("TARIKH_OT");
			String POT_FLAG_OPEN = getParam("POT_FLAG_OPEN_"+BIL+ID_PERMOHONANOT);
			myLogger.info(" **** POT_FLAG_OPEN : "+POT_FLAG_OPEN);
			this.context.put("ID_PERMOHONANOT",ID_PERMOHONANOT);
			this.context.put("BIL",BIL);
			if(POT_FLAG_OPEN.equals("close"))
			{
				listLogOT = listLogOT(session,ID_PERMOHONANOT,TARIKH_OT,FLAG_CUTI);
				this.context.put("listLogOT",listLogOT);
				skrin_name = "app/admin/OT/OTlistOT_POT.jsp";
			}
			else
			{
				skrin_name = "app/admin/OT/OTlistOTClose_POT.jsp";
			}
			
		}	
		else if(command.equals("checkDatePOT"))
		{
			boolean checkDuplicateDate = true;
			String ID_PERMOHONANOT = getParam("ID_PERMOHONANOT");			
			String POT_ID_PEMOHON = getParam("POT_ID_PEMOHON_"+ID_PERMOHONANOT);
			String POT_TARIKH_OT_MULA = getParam("POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT);
			String POT_TARIKH_OT_AKHIR = getParam("POT_TARIKH_OT_AKHIR_"+ID_PERMOHONANOT);
			String CHECK_POT_TARIKH_OT_MULA = getParam("CHECK_POT_TARIKH_OT_MULA_"+ID_PERMOHONANOT);
			String CHECK_POT_TARIKH_OT_AKHIR = getParam("CHECK_POT_TARIKH_OT_AKHIR_"+ID_PERMOHONANOT);
			String checkNO_OT = "";
			String checkDuplicateDate_msg = "";
			if(CHECK_POT_TARIKH_OT_MULA.equals("true") && CHECK_POT_TARIKH_OT_AKHIR.equals("true")
					&& !POT_TARIKH_OT_MULA.equals("") && !POT_TARIKH_OT_AKHIR.equals(""))
			{
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date date_mula = format.parse(POT_TARIKH_OT_MULA);
				Date date_akhir = format.parse(POT_TARIKH_OT_AKHIR);
				if(date_mula.after(date_akhir)) {
				    checkDuplicateDate_msg = "<font color='red' class='blink' >** Sila pastikan tarikh mula OT ('"+POT_TARIKH_OT_MULA+"') tidak melebihi tarikh akhir OT ('"+POT_TARIKH_OT_AKHIR+"')!</font><br>";				
					checkDuplicateDate = false;
				}
				else
				{
					checkNO_OT = checkDuplicateDate(session, POT_ID_PEMOHON,ID_PERMOHONANOT,POT_TARIKH_OT_MULA, POT_TARIKH_OT_AKHIR);
					if(!checkNO_OT.equals(""))
					{
						checkDuplicateDate_msg = "<font color='red' class='blink' >** Tarikh OT yang dimasukkan ("+POT_TARIKH_OT_MULA+"-"+POT_TARIKH_OT_AKHIR+") telah wujud didalam permohonan anda yang terdahulu! <br>No OT : '<b>"+checkNO_OT.toUpperCase()+"</b>'</font><br>";				
						checkDuplicateDate = false;
					}
				}
			}
			
			this.context.put("ID_PERMOHONANOT",ID_PERMOHONANOT);
			this.context.put("checkDuplicateDate_msg",checkDuplicateDate_msg);
			this.context.put("checkDuplicateDate",checkDuplicateDate);
			skrin_name = "app/admin/OT/OTcheckDatePOT.jsp";
		}
		
		
		
		myLogger.info(" PKK - VM :"+skrin_name);
		return skrin_name;
	}
	
	private int parseInt(String param) {
		// TODO Auto-generated method stub
		return 0;
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
		
		this.context.put("FLAG_POT_CARIAN", "");
		this.context.put("POT_OPENCLOSE_CARIAN","");
		this.context.put("POT_NAMA_AKTIVITI","");
		this.context.put("listNegeri","");	
		this.context.put("listUnit","");		
		this.context.put("checkTransaksiPOT", "");
		this.context.put("checkTransaksiPOT_msg", "");
		this.context.put("listPelulus", "");
		this.context.put("checkNO_OT","");
		this.context.put("checkDuplicateDate_msg","");
		this.context.put("listDates","");
		this.context.put("listLogOT","");		
		this.context.put("OVERALLMINIT", "");
		this.context.put("listLogOT_forPrint", "");
		
	}
	
	
	public void saveTableTT(HttpSession session,String ID_TRANSAKSI) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		long idTT = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String NAMA_AKTIVITI = getParam("NAMA_AKTIVITI_"+ID_TRANSAKSI);
			myLogger.info("NAMA_AKTIVITI_"+ID_TRANSAKSI+" : "+NAMA_AKTIVITI);
			String NAMA_TABLE = getParam("NAMA_TABLE_"+ID_TRANSAKSI);
			myLogger.info("NAMA_TABLE_"+ID_TRANSAKSI+" : "+NAMA_TABLE);
			String FIELD_CHECK = getParam("FIELD_CHECK_"+ID_TRANSAKSI);	
			myLogger.info("FIELD_CHECK_"+ID_TRANSAKSI+" : "+FIELD_CHECK);
			String QUERY_NOFAIL = getParam("QUERY_NOFAIL_"+ID_TRANSAKSI); 
			myLogger.info("QUERY_NOFAIL_"+ID_TRANSAKSI+" : "+QUERY_NOFAIL);
			String ID_SEKSYEN = getParam("ID_SEKSYEN_"+ID_TRANSAKSI);
			myLogger.info("ID_SEKSYEN_"+ID_TRANSAKSI+" : "+ID_SEKSYEN);
			
			if(!ID_TRANSAKSI.equals(""))
			{
				r.update("ID_TRANSAKSI", ID_TRANSAKSI);
			}
			else
			{
				idTT = DB.getNextID(db, "TBLTRANSAKSI_SEQ");
				r.add("ID_TRANSAKSI", idTT);
			}
			r.add("NAMA_AKTIVITI", NAMA_AKTIVITI.toUpperCase());
			r.add("NAMA_TABLE", NAMA_TABLE.toUpperCase());
			r.add("FIELD_CHECK", FIELD_CHECK.toUpperCase());
			r.add("QUERY_NOFAIL", QUERY_NOFAIL.toUpperCase());
			r.add("ID_SEKSYEN", ID_SEKSYEN);
			
			if(!ID_TRANSAKSI.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLTRANSAKSI");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLTRANSAKSI");	
			}
			myLogger.info("OT : SAVE TT : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			if(!ID_TRANSAKSI.equals(""))
			{
				AuditTrail.logActivity(this,session,"UPD","TBLTRANSAKSI [ID_TRANSAKSI : "+ID_TRANSAKSI+"] Updated");
			}
			else
			{
				AuditTrail.logActivity(this,session,"INS","TBLTRANSAKSI [ID_TRANSAKSI : "+idTT+"] Inserted");
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
	
	
	public void saveTablePMT(HttpSession session,String ID_MINITTRANSAKSI) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		long idPMT = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String ID_TRANSAKSI = getParam("ID_NEGERI"+ID_MINITTRANSAKSI);
			myLogger.info("ID_TRANSAKSI_"+ID_MINITTRANSAKSI+" : "+ID_TRANSAKSI);
			String MINIT_INSERT = getParam("MINIT_INSERT_"+ID_MINITTRANSAKSI);
			myLogger.info("MINIT_INSERT_"+ID_MINITTRANSAKSI+" : "+MINIT_INSERT);
			String MINIT_UPDATE = getParam("MINIT_UPDATE_"+ID_MINITTRANSAKSI);
			myLogger.info("MINIT_UPDATE_"+ID_MINITTRANSAKSI+" : "+MINIT_UPDATE);
			
			
			if(!ID_MINITTRANSAKSI.equals(""))
			{
				r.update("ID_MINITTRANSAKSI", ID_MINITTRANSAKSI);
			}
			else
			{
				idPMT = DB.getNextID(db, "TBLMINITTRANSAKSI_SEQ");
				r.add("ID_MINITTRANSAKSI", idPMT);
			}
			r.add("ID_TRANSAKSI", ID_TRANSAKSI);
			r.add("MINIT_INSERT", MINIT_INSERT);
			r.add("MINIT_UPDATE", MINIT_UPDATE);
			
			if(!ID_MINITTRANSAKSI.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLMINITTRANSAKSI");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLMINITTRANSAKSI");	
			}
			myLogger.info("OT : SAVE PMT : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			if(!ID_MINITTRANSAKSI.equals(""))
			{
				AuditTrail.logActivity(this,session,"UPD","TBLMINITTRANSAKSI [ID_MINITTRANSAKSI : "+ID_MINITTRANSAKSI+"] Updated");
			}
			else
			{
				AuditTrail.logActivity(this,session,"INS","TBLMINITTRANSAKSI [ID_MINITTRANSAKSI : "+idPMT+"] Inserted");
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
	
	
	public void saveTablePOT(HttpSession session,String ID_PERMOHONANOT) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		long idPOT = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String POT_ID_PEMOHON = getParam("POT_ID_PEMOHON"+"_"+ID_PERMOHONANOT);
			String POT_TARIKH_OT_MULA = getParam("POT_TARIKH_OT_MULA"+"_"+ID_PERMOHONANOT);
			String POT_TARIKH_OT_AKHIR = getParam("POT_TARIKH_OT_AKHIR"+"_"+ID_PERMOHONANOT);
			String POT_ID_PELULUS = getParam("POT_ID_PELULUS"+"_"+ID_PERMOHONANOT);
			String POT_CATATAN_PEMOHON = getParam("POT_CATATAN_PEMOHON"+"_"+ID_PERMOHONANOT);
			myLogger.info(" POT_CATATAN_PEMOHON : "+POT_CATATAN_PEMOHON);
			String POT_CATATAN_PELULUS = getParam("POT_CATATAN_PELULUS"+"_"+ID_PERMOHONANOT);
			String POT_ID_STATUS = getParam("POT_ID_STATUS"+"_"+ID_PERMOHONANOT);
			String POT_FLAG_CUTI = getParam("POT_FLAG_CUTI"+"_"+ID_PERMOHONANOT);
			myLogger.info(" POT_CATATAN_PELULUS : "+POT_CATATAN_PELULUS+" POT_ID_STATUS : "+POT_ID_STATUS+" POT_FLAG_CUTI : "+POT_FLAG_CUTI);
			
			
			if(!ID_PERMOHONANOT.equals(""))
			{
				r.update("ID_PERMOHONANOT", ID_PERMOHONANOT);
				r.add("CATATAN_PELULUS", POT_CATATAN_PELULUS);
				r.add("ID_STATUS",POT_ID_STATUS);
				r.add("FLAG_CUTI",POT_FLAG_CUTI);
				r.add("TARIKH_LULUS", r.unquote("sysdate"));
			}
			else
			{
				idPOT = DB.getNextID(db, "TBLPERMOHONANOT_SEQ");
				r.add("ID_PERMOHONANOT", idPOT);
				r.add("TARIKH_MOHON", r.unquote("sysdate"));
				
				Date now = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String tahun = formatter.format(now);
				String kod_negeri = getKodNegeri(session, USER_NEGERI);
				String no_rujukan_ot = "OT/"+tahun + "/"+ kod_negeri + "/PPK/"
						+ String.format("%06d", getSeqNoOT(tahun,USER_NEGERI,"PPK"));
				r.add("NO_OT", no_rujukan_ot);
				r.add("ID_SEKSYEN",2);
				r.add("ID_STATUS",1);//PERMOHONAN BARU
				r.add("ID_PEMOHON", POT_ID_PEMOHON);
				r.add("ID_PELULUS", POT_ID_PELULUS);
				r.add("CATATAN_PEMOHON", POT_CATATAN_PEMOHON);				
			}
			r.add("TARIKH_MULA", POT_TARIKH_OT_MULA);
			r.add("TARIKH_AKHIR", POT_TARIKH_OT_AKHIR);
			
			
			if(!ID_PERMOHONANOT.equals(""))
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPERMOHONANOT");
			}
			else
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("TBLPERMOHONANOT");	
			}
			myLogger.info("OT : SAVE POT : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			if(!ID_PERMOHONANOT.equals(""))
			{
				hantarEmel(session,ID_PERMOHONANOT);
				AuditTrail.logActivity(this,session,"UPD","TBLPERMOHONANOT [ID_PERMOHONANOT : "+ID_PERMOHONANOT+"] Updated");
				
			}
			else
			{
				hantarEmel(session,idPOT+"");
				AuditTrail.logActivity(this,session,"INS","TBLPERMOHONANOT [ID_PERMOHONANOT : "+idPOT+"] Inserted");
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
	
	
	public Hashtable viewTT(HttpSession session, String ID_TRANSAKSI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_TRANSAKSI.equals(""))
			{
				sql = " SELECT T.ID_TRANSAKSI, T.NAMA_AKTIVITI, T.NAMA_TABLE, "+
						" T.FIELD_CHECK, T.ID_MASUK, T.TARIKH_MASUK, T.ID_KEMASKINI, T.TARIKH_KEMASKINI,  "+
						" T.QUERY_NOFAIL,B.ID_SEKSYEN, B.NAMA_SEKSYEN  FROM TBLTRANSAKSI T, TBLRUJSEKSYEN B " +
						" WHERE T.ID_SEKSYEN = B.ID_SEKSYEN AND T.ID_TRANSAKSI = '"+ID_TRANSAKSI+"' ";
				myLogger.info(" OT : viewTT :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {
					h.put("ID_TRANSAKSI",rs.getString("ID_TRANSAKSI") == null ? "" : rs.getString("ID_TRANSAKSI"));
					h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI"));
					h.put("NAMA_TABLE",rs.getString("NAMA_TABLE") == null ? "" : rs.getString("NAMA_TABLE"));
					h.put("FIELD_CHECK",rs.getString("FIELD_CHECK") == null ? "" : rs.getString("FIELD_CHECK"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));				
					h.put("QUERY_NOFAIL",rs.getString("QUERY_NOFAIL") == null ? "" : rs.getString("QUERY_NOFAIL"));
					h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				}
			}
			else
			{
				h.put("ID_TRANSAKSI","");
				h.put("NAMA_AKTIVITI","");
				h.put("NAMA_TABLE","");
				h.put("FIELD_CHECK","");
				h.put("ID_MASUK","");
				h.put("TARIKH_MASUK","");
				h.put("ID_KEMASKINI","");
				h.put("TARIKH_KEMASKINI","");
				h.put("ID_SEKSYEN","2");				
				h.put("QUERY_NOFAIL","");
				h.put("NAMA_SEKSYEN","");
	
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
	
	
	public Hashtable viewPMT(HttpSession session, String ID_MINITTRANSAKSI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_MINITTRANSAKSI.equals(""))
			{
				sql = " SELECT UPPER(S.NAMA_SEKSYEN) AS NAMA_SEKSYEN, T.ID_MINITTRANSAKSI, T.MINIT_INSERT, T.MINIT_UPDATE, "+
						" T.ID_TRANSAKSI, B.NAMA_AKTIVITI, B.ID_SEKSYEN,T.ID_MASUK, T.TARIKH_MASUK, T.ID_KEMASKINI, T.TARIKH_KEMASKINI "+
						" FROM TBLMINITTRANSAKSI T, TBLTRANSAKSI B, TBLRUJSEKSYEN S WHERE T.ID_TRANSAKSI = B.ID_TRANSAKSI" +
						" AND B.ID_SEKSYEN = S.ID_SEKSYEN AND T.ID_MINITTRANSAKSI = '"+ID_MINITTRANSAKSI+"' ";
				
				myLogger.info(" OT : viewPMT :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {
					h.put("ID_TRANSAKSI",rs.getString("ID_TRANSAKSI") == null ? "" : rs.getString("ID_TRANSAKSI"));
					h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI"));
					h.put("ID_MINITTRANSAKSI",rs.getString("ID_MINITTRANSAKSI") == null ? "" : rs.getString("ID_MINITTRANSAKSI"));
					//h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
					//h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
					h.put("MINIT_UPDATE",rs.getString("MINIT_UPDATE") == null ? 0 : rs.getInt("MINIT_UPDATE"));
					h.put("MINIT_INSERT",rs.getString("MINIT_INSERT") == null ? 0 : rs.getInt("MINIT_INSERT"));
//					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
//					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
//					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
//					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
				}
			}
			else
			{
				h.put("ID_TRANSAKSI","");
				h.put("NAMA_AKTIVITI","");
				h.put("ID_MINITTRANSAKSI","");
				//h.put("ID_SEKSYEN","");
				//h.put("NAMA_SEKSYEN","");
				h.put("MINIT_UPDATE",0);
				h.put("MINIT_INSERT",0);
//				h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
//				h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
//				h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
//				h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
	
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
	
	
	
	public Hashtable viewPOT(HttpSession session, String ID_PERMOHONANOT) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_PERMOHONANOT.equals(""))
			{
				/*
				sql = " SELECT T.ID_PERMOHONANOT, T.ID_PEMOHON, T.ID_PELULUS, TO_CHAR(T.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, "+
						" TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_MULA, TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY') AS TARIKH_AKHIR,  "+
						" UPPER(T.CATATAN_PEMOHON) AS CATATAN_PEMOHON,UPPER(T.CATATAN_PELULUS) AS CATATAN_PELULUS,  "+
						" T.ID_STATUS, (CASE WHEN ID_STATUS = '1' THEN 'BARU' "+
						" WHEN ID_STATUS = '2' THEN 'LULUS' WHEN ID_STATUS = '3' THEN 'TOLAK' WHEN ID_STATUS = '4' THEN 'SELESAI' ELSE '' END) AS STATUS, "+
						" T.NO_OT, T.ID_SEKSYEN, UPPER(S.NAMA_SEKSYEN) AS BAHAGIAN, "+
						" PM.USER_NAME AS NAMA_PEMOHON, PL.USER_NAME AS NAMA_PELULUS "+
						" FROM TBLPERMOHONANOT T, TBLRUJSEKSYEN S, USERS PM, USERS PL, USERS_INTERNAL UI, TBLRUJPEJABATJKPTG PEJ "+
						" WHERE T.ID_SEKSYEN  = S.ID_SEKSYEN AND PM.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG ";										
				sql += " AND T.ID_PELULUS = PL.USER_ID AND T.ID_PEMOHON = PM.USER_ID AND T.ID_SEKSYEN = '2' AND T.ID_PERMOHONANOT = '"+ID_PERMOHONANOT+"'  ";
				*/
				
				sql = " SELECT NVL(T.FLAG_CUTI,'N') AS FLAG_CUTI, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI,T.ID_PERMOHONANOT, T.ID_PEMOHON, T.ID_PELULUS, " +
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
				sql += " AND T.ID_PELULUS = PL.USER_ID AND T.ID_PEMOHON = PM.USER_ID AND T.ID_SEKSYEN = '2' AND T.ID_PERMOHONANOT = '"+ID_PERMOHONANOT+"' ";
				
				myLogger.info(" OT : viewPOT :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					h.put("ID_PERMOHONANOT",rs.getString("ID_PERMOHONANOT") == null ? "" : rs.getString("ID_PERMOHONANOT"));
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
					h.put("NO_OT",rs.getString("NO_OT") == null ? "" : rs.getString("NO_OT"));
					h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
					h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
					h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
					h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
					h.put("FLAG_CUTI",rs.getString("FLAG_CUTI") == null ? "" : rs.getString("FLAG_CUTI"));
					h.put("WAKTU_KERJA",rs.getString("WAKTU_KERJA") == null ? "" : rs.getString("WAKTU_KERJA"));				
					//checkLogTransaksi(rs.getString("TARIKH_MULA"),rs.getString("TARIKH_AKHIR"),rs.getString("ID_PEMOHON"));
				}
			}
			else
			{

				h.put("ID_PERMOHONANOT","");
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
				h.put("NO_OT","");
				h.put("ID_SEKSYEN","");
				h.put("BAHAGIAN","");
				h.put("NAMA_PEMOHON",getUserFullName(session,USER_ID_SYSTEM));
				h.put("NAMA_PELULUS","");
				h.put("NAMA_NEGERI","");
				h.put("NAMA_UNIT","");
				h.put("FLAG_CUTI","");
				h.put("WAKTU_KERJA",getWaktuKerja(session,USER_ID_SYSTEM));
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
	public List listTT(HttpSession session)throws Exception {
		
		String FLAG_TT_CARIAN = getParam("FLAG_TT_CARIAN");
		myLogger.info("-------- FLAG_TT_CARIAN : "+FLAG_TT_CARIAN);
			
		String TT_NAMA_AKTIVITI = getParam("TT_NAMA_AKTIVITI");
		String TT_NAMA_TABLE = getParam("TT_NAMA_TABLE");
		String TT_NAMA_FIELD = getParam("TT_NAMA_FIELD");
		//String TT_SEKSYEN = getParam("TT_SEKSYEN");
		
		this.context.put("TT_NAMA_AKTIVITI",TT_NAMA_AKTIVITI.toUpperCase());
		this.context.put("TT_NAMA_TABLE",TT_NAMA_TABLE.toUpperCase());
		this.context.put("TT_NAMA_FIELD",TT_NAMA_FIELD.toUpperCase());
		//this.context.put("TT_SEKSYEN","");
		
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTT = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT T.ID_TRANSAKSI, T.NAMA_AKTIVITI, T.NAMA_TABLE, "+
					" T.FIELD_CHECK, T.ID_MASUK, T.TARIKH_MASUK, T.ID_KEMASKINI, T.TARIKH_KEMASKINI,  "+
					" T.QUERY_NOFAIL,B.ID_SEKSYEN, B.NAMA_SEKSYEN  FROM TBLTRANSAKSI T, TBLRUJSEKSYEN B " +
					" WHERE T.ID_SEKSYEN = B.ID_SEKSYEN ";
					sql += " AND T.ID_SEKSYEN = '2' "; 
			if(FLAG_TT_CARIAN.equals("Y"))
			{
				if(!TT_NAMA_AKTIVITI.equals(""))
				{
					sql += " AND UPPER(T.NAMA_AKTIVITI) LIKE '%"+TT_NAMA_AKTIVITI.toUpperCase()+"%' ";
				}
				if(!TT_NAMA_TABLE.equals(""))
				{
					sql += " AND UPPER(T.NAMA_TABLE) LIKE '%"+TT_NAMA_TABLE.toUpperCase()+"%' ";
				}
				if(!TT_NAMA_FIELD.equals(""))
				{
					sql += " AND UPPER(T.FIELD_CHECK) LIKE '%"+TT_NAMA_FIELD.toUpperCase()+"%' ";
				}
			}
			else
			{
				this.context.put("TT_NAMA_AKTIVITI","");
				this.context.put("TT_NAMA_TABLE","");
				this.context.put("TT_NAMA_FIELD","");
				this.context.put("TT_SEKSYEN","");
			}
			sql += " ORDER BY T.NAMA_AKTIVITI ";		
			myLogger.info(" OT : SQL listTT :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listTT = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_TRANSAKSI",rs.getString("ID_TRANSAKSI") == null ? "" : rs.getString("ID_TRANSAKSI"));
				h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI"));
				h.put("NAMA_TABLE",rs.getString("NAMA_TABLE") == null ? "" : rs.getString("NAMA_TABLE"));
				h.put("FIELD_CHECK",rs.getString("FIELD_CHECK") == null ? "" : rs.getString("FIELD_CHECK"));
				h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
				h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
				h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
				h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));				
				h.put("QUERY_NOFAIL",rs.getString("QUERY_NOFAIL") == null ? "" : rs.getString("QUERY_NOFAIL"));
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN"));
				listTT.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listTT;

	}
	
	/*
	
	*/
	
	@SuppressWarnings("unchecked")
	public List listPOT(HttpSession session)throws Exception {
		
		String FLAG_POT_CARIAN = getParam("FLAG_POT_CARIAN");
		myLogger.info("-------- FLAG_POT_CARIAN : "+FLAG_POT_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		
		myLogger.info("-------- USER_ID_SYSTEM : "+USER_ID_SYSTEM+" USER_ROLE : "+USER_ROLE+" USER_NEGERI : "+USER_NEGERI);
		
		String POT_NAMA_PELULUS = getParam("POT_NAMA_PELULUS");
		String POT_NAMA_PEMOHON = getParam("POT_NAMA_PEMOHON");
		String POT_NO_OT = getParam("POT_NO_OT");
		String POT_TARIKH_OT_MULA = getParam("POT_TARIKH_OT_MULA");
		String POT_TARIKH_OT_AKHIR = getParam("POT_TARIKH_OT_AKHIR");
		String POT_ID_NEGERI= getParam("POT_ID_NEGERI");
		String POT_ID_STATUS= getParam("POT_ID_STATUS");
		String POT_ID_UNIT= getParam("POT_ID_UNIT");
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPOT = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT NVL(T.FLAG_CUTI,'N') AS FLAG_CUTI,UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, UPPER(N.NAMA_NEGERI) AS NAMA_NEGERI,T.ID_PERMOHONANOT, T.ID_PEMOHON, T.ID_PELULUS, TO_CHAR(T.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, "+
					" TO_CHAR(T.TARIKH_MULA,'DD/MM/YYYY') AS TARIKH_MULA, TO_CHAR(T.TARIKH_AKHIR,'DD/MM/YYYY') AS TARIKH_AKHIR,  "+
					" UPPER(T.CATATAN_PEMOHON) AS CATATAN_PEMOHON,UPPER(T.CATATAN_PELULUS) AS CATATAN_PELULUS,  "+
					" T.ID_STATUS, (CASE WHEN ID_STATUS = '1' THEN 'BARU' "+
					" WHEN ID_STATUS = '2' THEN 'LULUS' WHEN ID_STATUS = '3' THEN 'TOLAK' WHEN ID_STATUS = '4' THEN 'SELESAI' ELSE '' END) AS STATUS, "+
					" T.NO_OT, T.ID_SEKSYEN, UPPER(S.NAMA_SEKSYEN) AS BAHAGIAN, "+
					" PM.USER_NAME AS NAMA_PEMOHON, PL.USER_NAME AS NAMA_PELULUS "+
					" FROM TBLPERMOHONANOT T, TBLRUJSEKSYEN S, USERS PM, USERS PL, USERS_INTERNAL UI, TBLRUJPEJABATJKPTG PEJ,TBLRUJNEGERI N "+
					" WHERE T.ID_SEKSYEN  = S.ID_SEKSYEN " +
					" AND PM.USER_ID = UI.USER_ID " +
					" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG" +
					" AND PEJ.ID_NEGERI = N.ID_NEGERI ";										
			sql += " AND T.ID_PELULUS = PL.USER_ID AND T.ID_PEMOHON = PM.USER_ID AND T.ID_SEKSYEN = '2' ";
					
			
					
			if(FLAG_POT_CARIAN.equals("Y"))
			{
				if(!POT_ID_NEGERI.equals(""))
				{
					if(!POT_ID_NEGERI.equals("ALL"))
					{
					sql += " AND PEJ.ID_NEGERI = '"+POT_ID_NEGERI+"' ";
					}
				}
				if(!POT_ID_STATUS.equals(""))
				{
					sql += " AND T.ID_STATUS = '"+POT_ID_STATUS+"' ";					
				}
				if(!POT_ID_UNIT.equals(""))
				{
					sql += " AND PEJ.ID_PEJABATJKPTG = '"+POT_ID_UNIT+"' ";					
				}
				if(!POT_NAMA_PEMOHON.equals(""))
				{
					sql += " AND UPPER(PM.USER_NAME) LIKE '%"+POT_NAMA_PEMOHON.toUpperCase()+"%' ";
				}
				if(!POT_NAMA_PELULUS.equals(""))
				{
					sql += " AND UPPER(PL.USER_NAME) LIKE '%"+POT_NAMA_PELULUS.toUpperCase()+"%' ";
				}
				if(!POT_NO_OT.equals(""))
				{
					sql += " AND UPPER(T.NO_OT) LIKE '%"+POT_NO_OT.toUpperCase()+"%' ";
				}
				
				if(!POT_TARIKH_OT_MULA.equals("") && !POT_TARIKH_OT_AKHIR.equals(""))
				{
					sql+= " AND ( "+ 
							" ( "+     
							" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) >= (TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" AND ( "+
							" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) >= TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" ) "+
							" OR "+
							" (  "+    
							" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) <= (TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" AND ( "+
							" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
							" 'DD/MM/YYYY' "+
							" ) >= TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
							" ) "+
							" ) "+
							" ) ";
					
					sql +=  " AND   (TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+POT_TARIKH_OT_AKHIR+"', 'DD/MM/YYYY') "+
							" OR   TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+POT_TARIKH_OT_AKHIR+"', 'DD/MM/YYYY'))  ";
				
				}
				else
				{
					if(!POT_TARIKH_OT_MULA.equals(""))
					{
						sql+= " AND ( "+ 
								" ( "+     
								" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) >= (TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" AND ( "+
								" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) >= TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" ) "+
								" OR "+
								" (  "+    
								" TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) <= (TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" AND ( "+
								" TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'), "+
								" 'DD/MM/YYYY' "+
								" ) >= TO_DATE ('"+POT_TARIKH_OT_MULA+"', 'DD/MM/YYYY') "+
								" ) "+
								" ) "+
								" ) ";
					}
					else if(!POT_TARIKH_OT_AKHIR.equals(""))
					{
						sql +=  " AND   (TO_DATE (TO_CHAR (T.TARIKH_AKHIR, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+POT_TARIKH_OT_AKHIR+"', 'DD/MM/YYYY') "+
								" OR   TO_DATE (TO_CHAR (T.TARIKH_MULA, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+POT_TARIKH_OT_AKHIR+"', 'DD/MM/YYYY'))  ";
					}
				}
					
					
				
				this.context.put("POT_NAMA_PELULUS",POT_NAMA_PELULUS.toUpperCase());
				this.context.put("POT_NAMA_PEMOHON",POT_NAMA_PEMOHON.toUpperCase());
				this.context.put("POT_NO_OT",POT_NO_OT.toUpperCase());
				this.context.put("POT_TARIKH_OT_MULA",POT_TARIKH_OT_MULA.toUpperCase());
				this.context.put("POT_TARIKH_OT_AKHIR",POT_TARIKH_OT_AKHIR.toUpperCase());
				this.context.put("POT_ID_NEGERI",POT_ID_NEGERI.toUpperCase());
				this.context.put("POT_ID_STATUS",POT_ID_STATUS.toUpperCase());		
				this.context.put("POT_ID_UNIT",POT_ID_UNIT.toUpperCase());
			}
			else
			{
				
				
				if(USER_ROLE.equals("user_ppk"))
				{
					sql += " AND PM.USER_ID = '"+USER_ID_SYSTEM+"' ";
				}
				else if(USER_ROLE.equals("adminppk"))
				{
					if(!USER_NEGERI.equals("16"))
					{
						sql += " AND PEJ.ID_NEGERI = '"+USER_NEGERI+"' ";
					}
				}
				
				this.context.put("POT_NAMA_PELULUS","");
				this.context.put("POT_NAMA_PEMOHON","");
				this.context.put("POT_NO_OT","");
				this.context.put("POT_TARIKH_OT_MULA","");
				this.context.put("POT_TARIKH_OT_AKHIR","");
				this.context.put("POT_ID_NEGERI","");
				this.context.put("POT_ID_STATUS","");
				this.context.put("POT_ID_UNIT","");				
			}
			sql += " ORDER BY " +
					" (CASE WHEN (T.ID_STATUS = '1' AND T.ID_PELULUS = '"+USER_ID_SYSTEM+"') THEN 1 ELSE 2 END) ASC," +
					" T.ID_STATUS ASC, "+
					" T.TARIKH_MULA DESC ";
					
					
			myLogger.info(" OT : SQL listPOT :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listPOT = Collections.synchronizedList(new ArrayList());
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
				
				h.put("ID_PERMOHONANOT",rs.getString("ID_PERMOHONANOT") == null ? "" : rs.getString("ID_PERMOHONANOT"));
				h.put("ID_PEMOHON",rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("ID_PELULUS",rs.getString("ID_PELULUS") == null ? "" : rs.getString("ID_PELULUS"));
				h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? "" : rs.getString("TARIKH_MOHON"));
				h.put("TARIKH_MULA",rs.getString("TARIKH_MULA") == null ? "" : rs.getString("TARIKH_MULA"));
				h.put("TARIKH_AKHIR",rs.getString("TARIKH_AKHIR") == null ? "" : rs.getString("TARIKH_AKHIR"));
				h.put("CATATAN_PEMOHON",rs.getString("CATATAN_PEMOHON") == null ? "" : rs.getString("CATATAN_PEMOHON"));
				h.put("CATATAN_PELULUS",rs.getString("CATATAN_PELULUS") == null ? "" : rs.getString("CATATAN_PELULUS"));
				h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("NO_OT",rs.getString("NO_OT") == null ? "" : rs.getString("NO_OT"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
				h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS"));
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("FLAG_CUTI",rs.getString("FLAG_CUTI") == null ? "" : rs.getString("FLAG_CUTI"));
				
				Hashtable rumusanOT = new Hashtable();
				rumusanOT = rumusanOT(session,rs.getString("TARIKH_MULA"),rs.getString("TARIKH_AKHIR"),rs.getString("ID_PERMOHONANOT"),rs.getString("FLAG_CUTI"));
				
				h.put("OV_MPD",rumusanOT.get("OV_MPD"));
				h.put("OV_MWH_ALL",rumusanOT.get("OV_MWH_ALL"));
				h.put("OV_MWH",rumusanOT.get("OV_MWH"));
				h.put("OV_MOT",rumusanOT.get("OV_MOT"));
				h.put("OV_DAYS",rumusanOT.get("OV_DAYS"));
				//checkLogTransaksi(session,rs.getString("TARIKH_MULA"),rs.getString("TARIKH_AKHIR"),rs.getString("ID_PERMOHONANOT"));
				
				listPOT.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPOT;

	}
	
	
	@SuppressWarnings("unchecked")
	public List listPKK(HttpSession session, String user_id)throws Exception {
		
		String FLAG_PKK_CARIAN = getParam("FLAG_PKK_CARIAN");
		myLogger.info("-------- FLAG_PKK_CARIAN : "+FLAG_PKK_CARIAN);
		
		String ID_NEGERI = getParam("PKK_ID_NEGERI");
		String ID_UNIT = getParam("PKK_ID_UNIT");

		this.context.put("PKK_ID_NEGERI",ID_NEGERI);
		this.context.put("PKK_ID_UNIT",ID_UNIT);
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPKK = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			/*sql = " SELECT A.ID_PKE, P.NAMA_PEJABAT, " +
				" TO_CHAR(C.TARIKH_DARI, 'DD/MM/YYYY') ||'-'|| TO_CHAR(C.TARIKH_DARI, 'DD/MM/YYYY') AS TEMPOH, " +
				" DECODE(C.STATUS_KELULUSAN, 1, 'BARU'," +
				" 2, 'LULUS'," +
				" 3, 'TIDAK LULUS'," +
				" 'BARU') STATUS, " +
				" N.NAMA_NEGERI " +
				" FROM TBLPPKKEBENARANEDIT A, TBLPPKKEBENARANSTAFF B, TBLPPKKEBENARANKELULUSAN C, " +
				" TBLRUJPEJABATJKPTG P, TBLRUJNEGERI N " +
				" WHERE A.ID_PKE = C.ID_PKE" +
				" AND A.ID_UNIT = P.ID_PEJABATJKPTG " +
				" AND A.ID_NEGERI = N.ID_NEGERI " +
				" AND A.ID_MASUK = "+user_id;*/
			
			
			sql = "SELECT A.ID_PKE,'' AS NAMA_PEJABAT, '' AS TEMPOH,'' AS STATUS, '' AS NAMA_NEGERI FROM TBLPPKKEBENARANEDIT A ";
					
					
			if(FLAG_PKK_CARIAN.equals("Y"))
			{
				if(!FLAG_PKK_CARIAN.equals(""))
				{
					
					if(!ID_NEGERI.equals(""))
					{
						sql += " AND A.ID_NEGERI = "+ID_NEGERI+"' ";
					}
					if(!ID_UNIT.equals(""))
					{
						sql += " AND A.ID_UNIT = "+ID_UNIT+"' ";
					}
					
				}
				
			}
			else
			{
				this.context.put("ID_NEGERI","");
				this.context.put("ID_UNIT","");
				
			}
			
					
					
			sql += " ORDER BY A.ID_NEGERI ";		
			myLogger.info(" OT : SQL listPKK :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listPKK = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_PKE",rs.getString("ID_PKE") == null ? "" : rs.getString("ID_PKE"));
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("TEMPOH",rs.getString("TEMPOH") == null ? "" : rs.getString("TEMPOH"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				
				/*h.put("MINIT_UPDATE",rs.getString("MINIT_UPDATE") == null ? 0 : rs.getInt("MINIT_UPDATE"));
				h.put("MINIT_INSERT",rs.getString("MINIT_INSERT") == null ? 0 : rs.getInt("MINIT_INSERT"));
				h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
				h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
				h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
				h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));*/
				
				listPKK.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPKK;

	}
	
	

	public boolean checkTransaksiTT(HttpSession session, String ID_TRANSAKSI, String ID_SEKSYEN, String NAMA_TABLE) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean check = true;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT ID_TRANSAKSI FROM TBLTRANSAKSI WHERE ID_SEKSYEN = '"+ID_SEKSYEN.toUpperCase()+"' AND NAMA_TABLE = '"+NAMA_TABLE.toUpperCase()+"' ";
				if(!ID_TRANSAKSI.equals(""))	
				{
					sql+=" AND ID_TRANSAKSI != '"+ID_TRANSAKSI+"' ";
				}				
				myLogger.info("OT - checkTransaksi :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_ID_TRANSAKSI = "";
				while (rs.next()) {
					
					GET_ID_TRANSAKSI = rs.getString("ID_TRANSAKSI") == null ? "" : rs.getString("ID_TRANSAKSI");
					myLogger.info("OT - GET_ID_TRANSAKSI :" + GET_ID_TRANSAKSI);
					if(!GET_ID_TRANSAKSI.equals(""))
					{
						check = false;
					}
					myLogger.info("OT - GET_ID_TRANSAKSI check :" + check);
				}
			
			return check;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkTransaksiPMT(HttpSession session, String ID_TRANSAKSI, String ID_MINITTRANSAKSI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean check = true;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT ID_MINITTRANSAKSI FROM TBLMINITTRANSAKSI WHERE ID_TRANSAKSI = '"+ID_TRANSAKSI+"' ";
				if(!ID_MINITTRANSAKSI.equals(""))	
				{
					sql+=" AND ID_MINITTRANSAKSI != '"+ID_MINITTRANSAKSI+"' ";
				}				
				myLogger.info("OT - checkTransaksiPMT :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_ID_MINITTRANSAKSI = "";
				while (rs.next()) {
					
					GET_ID_MINITTRANSAKSI = rs.getString("ID_MINITTRANSAKSI") == null ? "" : rs.getString("ID_MINITTRANSAKSI");
					myLogger.info("OT - GET_ID_MINITTRANSAKSI :" + GET_ID_MINITTRANSAKSI);
					if(!GET_ID_MINITTRANSAKSI.equals(""))
					{
						check = false;
					}
					myLogger.info("OT - GET_ID_MINITTRANSAKSI check :" + check);
				}
			
			return check;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public boolean checkDB(HttpSession session,String FIELD_CHECK, String NAMA_TABLE) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean check = true;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT TABLE_NAME FROM ALL_TAB_COLUMNS WHERE UPPER(TABLE_NAME) = '"+NAMA_TABLE.toUpperCase()+"' AND UPPER(COLUMN_NAME) = '"+FIELD_CHECK.toUpperCase()+"'  ";
							
				myLogger.info("OT - CHECKdb :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_TABLE_NAME = "";
				while (rs.next()) {
					
					GET_TABLE_NAME = rs.getString("TABLE_NAME") == null ? "" : rs.getString("TABLE_NAME");
					/*
					if(!GET_TABLE_NAME.equals(""))
					{
						check = false;
					}
					*/
				}
				
				if(GET_TABLE_NAME.equals(""))
				{
					check = false;
				}
				
			
			return check;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String checkSyntaxQuery(HttpSession session,String QUERY_NOFAIL) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String error_mesej = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();			
				sql = QUERY_NOFAIL.toUpperCase() + " AND ROWNUM = 1";							
				myLogger.info("OT - checkSyntaxQuery :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
		}
		catch (Exception ex) {
			error_mesej = "<b>"+ex.getMessage().toUpperCase()+"</b>" + " <br> (SQL : "+sql+")";
			//throw new DbException(ex.getMessage());
		}
		finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return error_mesej;
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
	
	public void deleteTT(HttpSession session,String ID_TRANSAKSI) throws Exception {
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
			r.add("ID_TRANSAKSI",ID_TRANSAKSI);
			sql = r.getSQLDelete("TBLTRANSAKSI");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLTRANSAKSI [ID_TRANSAKSI : "+ID_TRANSAKSI+"] Deleted");
		
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
	
	
	public void deletePOT(HttpSession session,String ID_PERMOHONANOT) throws Exception {
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
			r.add("ID_PERMOHONANOT",ID_PERMOHONANOT);
			sql = r.getSQLDelete("TBLPERMOHONANOT");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLPERMOHONANOT [ID_PERMOHONANOT : "+ID_PERMOHONANOT+"] Deleted");
		
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
	
	public void deletePMT(HttpSession session,String ID_MINITTRANSAKSI) throws Exception {
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
			r.add("ID_MINITTRANSAKSI",ID_MINITTRANSAKSI);
			sql = r.getSQLDelete("TBLMINITTRANSAKSI");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","TBLMINITTRANSAKSI [ID_MINITTRANSAKSI : "+ID_MINITTRANSAKSI+"] Deleted");		
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
	public List listAktiviti(HttpSession session, String ID_SEKSYEN,String ID_MINITTRANSAKSI)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listAktiviti = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT T.ID_TRANSAKSI, T.NAMA_AKTIVITI, T.NAMA_TABLE, T.FIELD_CHECK, T.ID_MASUK, T.TARIKH_MASUK, "+
					" T.ID_KEMASKINI, T.TARIKH_KEMASKINI, T.ID_SEKSYEN,  "+
					" T.QUERY_NOFAIL FROM TBLTRANSAKSI T " +
					" WHERE T.ID_SEKSYEN = '"+ID_SEKSYEN+"' ";
					if(ID_MINITTRANSAKSI.equals(""))
					{
						sql += " AND T.ID_TRANSAKSI NOT IN (SELECT ID_TRANSAKSI FROM TBLMINITTRANSAKSI) ";
					}			
							sql += "ORDER BY NAMA_AKTIVITI ";			
			myLogger.info(" V3: SQL listAktiviti :"+ sql);
			rs = stmt.executeQuery(sql);
			listAktiviti = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_TRANSAKSI",rs.getString("ID_TRANSAKSI") == null ? "" : rs.getString("ID_TRANSAKSI"));
				h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI").toUpperCase());
				listAktiviti.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listAktiviti;

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
			sql = " SELECT ID_NEGERI, NAMA_NEGERI FROM "+
					" TBLRUJNEGERI N WHERE ID_NEGERI NOT IN (0,17) "+
					" AND ID_NEGERI IS NOT NULL ";
			
			//if(USER_ROLE.equals("user_ppk") || (USER_ROLE.equals("adminppk") && !USER_NEGERI.equals("16")))
			//{
			//	sql += " AND ID_NEGERI = '"+USER_NEGERI+"' ";
			//}
			
			sql += "ORDER BY NAMA_NEGERI ";			
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
	public List listUnit(HttpSession session,String ID_NEGERI)throws Exception {
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
			sql = " SELECT TO_CHAR(P.ID_PEJABATJKPTG) AS ID_UNIT,UPPER(P.NAMA_PEJABAT) AS NAMA_UNIT, " +
					" N.ID_NEGERI AS ID_NEGERI, N.NAMA_NEGERI AS NAMA_NEGERI" +
					" FROM " +
					" TBLRUJPEJABATJKPTG P,TBLRUJNEGERI N " +
					" WHERE ID_SEKSYEN = '2' AND ID_JENISPEJABAT IN (21,22,23) AND P.ID_NEGERI = N.ID_NEGERI " +
					" AND P.ID_NEGERI = '"+ID_NEGERI+"' " +
					" AND P.ID_PEJABATJKPTG IS NOT NULL " +
					" ORDER BY NAMA_UNIT  ";		
			
			//myLogger.info(" V3: SQL listUnit :"+ sql);
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
					 " WHERE U.USER_ID = UI.USER_ID AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_SEKSYEN = '2' AND PEJ.ID_JENISPEJABAT IN (21,22,23) "+
					 " AND UI.ID_JAWATAN = J.ID_JAWATAN "+
					 " AND UI.ID_SEKSYEN = '2' AND (U.USER_ROLE = 'adminppk' OR U.USER_NAME IN (SELECT USER_ID FROM USER_ROLE WHERE ROLE_ID = 'adminppk')) ";
			sql += " AND PEJ.ID_NEGERI = '"+ID_NEGERI+"' ";			
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
	
	
	
	public String checkDuplicateDate(HttpSession session, String ID_PEMOHON,String ID_PERMOHONANOT,String TARIKH_MULA, String TARIKH_AKHIR) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String NO_OT="";
			sql = " SELECT T.NO_OT FROM TBLPERMOHONANOT T "+
					" WHERE T.ID_PERMOHONANOT IS NOT NULL AND T.ID_STATUS != '3' AND T.ID_PEMOHON = '"+ID_PEMOHON+"' "+
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
			
					if(!ID_PERMOHONANOT.equals(""))
					{
						sql += " AND  T.ID_PERMOHONANOT != '"+ID_PERMOHONANOT+"' ";
					}
					
				myLogger.info(" OT : checkDuplicateDate :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				int bil = 0;
				while (rs.next()) {				
					bil ++;
					if(bil>1 && rs.getString("NO_OT")!=null && !rs.getString("NO_OT").equals("") )
					{
						NO_OT += ", ";
					}	
					NO_OT += (rs.getString("NO_OT") == null ? "" : rs.getString("NO_OT"));
				}
			
			return NO_OT;
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
	
	public static synchronized int getNoOT(String tahun,String id_negeri,String kod_modul)
			throws DbException {
		return getSeqNoOT(tahun,id_negeri,kod_modul);
	}
		 public static synchronized int getSeqNoOT(String tahun,String id_negeri,String kod_modul)
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

			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQESNOOT WHERE ");
			sb.append(" TAHUN = '" + tahun + "'");
			sb.append(" AND ID_NEGERI = '" + id_negeri + "'");
			sb.append(" AND KOD_MODUL = '" + kod_modul + "'");

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			
			myLogger.info("found :"+found);
			
			
			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseNoOT(tahun,id_negeri,kod_modul);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNoOT(tahun,id_negeri,kod_modul);
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
		 public static void increaseNoOT(String tahun,String id_negeri,String kod_modul) throws DbException {

				Db db = null;

				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE TBLRUJSEQESNOOT  SET ");
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
		 public static void addNoOT(String tahun,String id_negeri,String kod_modul) throws DbException {

				Db db = null;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO TBLRUJSEQESNOOT (TAHUN,ID_NEGERI,KOD_MODUL,NO_TURUTAN)");
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
		 
		 public List checkLogTransaksi(HttpSession session,String DATE_MULA,String DATE_AKHIR,String ID_PERMOHONANOT,String FLAG_CUTI) throws Exception {
			// LocalDate dateStart = new LocalDate("2012-01-15");
			// LocalDate dateEnd = new LocalDate("2012-05-23");
			 // day by day:
			 
			
			 
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			 Date startDate = format.parse(DATE_MULA);
			 Date endDate = format.parse(DATE_AKHIR);
			 int total_days = daysBetween(startDate, endDate);
			 myLogger.info(" total_days ::: "+total_days);
			 //String[] arrayDates = null;
			 List listDates = null;
			 listDates = Collections.synchronizedList(new ArrayList());
			 Map h = null;
			 Calendar start = Calendar.getInstance();
			 start.setTime(startDate);
			 Calendar end = Calendar.getInstance();
			 end.setTime(endDate);
			 myLogger.info("**OPEN get date range "+startDate+" to "+endDate);
			 //arrayDates = new String[total_days];
			 //int index_arrayDates = 0;
			 Hashtable hash_overall_minit = new Hashtable();	
			 int bil = 0;
			 int overall_minit_per_day = 0;
			 int overall_minit_per_wh = 0;
			 int overall_minit_per_ot = 0;
			 int overall_hari = 0;
			 for (Date date = start.getTime(); start.before(end) || start.equals(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			     // Do your job here with `date`.
				 bil = bil + 1;
			     myLogger.info(" - "+format.format(date));
			    // new SimpleDateFormat("MM-dd-yyyy").format(myDate)
			     //arrayDates[index_arrayDates] = format.format(date);
			     //index_arrayDates ++;
			     h = Collections.synchronizedMap(new HashMap());
				 h.put("TARIKH",format.format(date));
				 h.put("BIL",bil);				 
				 Hashtable h_r = rumusanOTByTarikh(session,ID_PERMOHONANOT,format.format(date),FLAG_CUTI);
				 int mpd = (int)h_r.get("total_minit_per_day");
				 int mwh = (int)h_r.get("total_minit_per_wh");
				 int mot = (int)h_r.get("total_minit_per_ot");
				 
				 
				 int wh_all = 0;
				 if(!FLAG_CUTI.equals("Y"))
				 {
					 wh_all = 240; //4 jam				
				 }
				 else
				 {
					 mot = mot+mwh;
					 mwh = 0;
				 }		 
				 
				 h.put("TM_PD",mpd);
				 h.put("TM_WH",mwh);
			     h.put("TM_OT",mot);
			     h.put("WH_ALL",wh_all);
			     
			     overall_minit_per_day += mpd;
				 overall_minit_per_wh += mwh;
				 overall_minit_per_ot += mot;
				 overall_hari++;
				 listDates.add(h);				 
			 }
			 hash_overall_minit.put("OV_MPD",overall_minit_per_day);
			 int total_minit_kerja_harian = 0;
			 if(!FLAG_CUTI.equals("Y"))
			 {
				 total_minit_kerja_harian = 240; //4 jam				
			 }
			 else
			 {
				 overall_minit_per_ot = overall_minit_per_ot+overall_minit_per_wh;
				 overall_minit_per_wh = 0;
			 }
			 hash_overall_minit.put("OV_MWH_ALL",overall_hari*total_minit_kerja_harian);
			 hash_overall_minit.put("OV_MWH",overall_minit_per_wh);
			 hash_overall_minit.put("OV_MOT",overall_minit_per_ot);
			 hash_overall_minit.put("OV_DAYS",overall_hari);
			 myLogger.info("**CLOSE get date range "+startDate+" to "+endDate+" ARRAY : "+listDates);
			 this.context.put("OVERALLMINIT", hash_overall_minit);
			 return listDates;
		 }
		 
		 
		 
		 
		 public Hashtable rumusanOT(HttpSession session,String DATE_MULA,String DATE_AKHIR,String ID_PERMOHONANOT,String FLAG_CUTI) throws Exception {
				// LocalDate dateStart = new LocalDate("2012-01-15");
				// LocalDate dateEnd = new LocalDate("2012-05-23");
				 // day by day:
				 
				 
				 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				 Date startDate = format.parse(DATE_MULA);
				 Date endDate = format.parse(DATE_AKHIR);
				 int total_days = daysBetween(startDate, endDate);
				 myLogger.info(" total_days ::: "+total_days);
				 //String[] arrayDates = null;
				 List listDates = null;
				 listDates = Collections.synchronizedList(new ArrayList());
				 Map h = null;
				 Calendar start = Calendar.getInstance();
				 start.setTime(startDate);
				 Calendar end = Calendar.getInstance();
				 end.setTime(endDate);
				 myLogger.info("**OPEN get date range "+startDate+" to "+endDate);
				 //arrayDates = new String[total_days];
				 //int index_arrayDates = 0;
				 Hashtable hash_overall_minit = new Hashtable();	
				 int bil = 0;
				 int overall_minit_per_day = 0;
				 int overall_minit_per_wh = 0;
				 int overall_minit_per_ot = 0;
				 int overall_hari = 0;
				 for (Date date = start.getTime(); start.before(end) || start.equals(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				     // Do your job here with `date`.
					 bil = bil + 1;
				     myLogger.info(" - "+format.format(date));
				    // new SimpleDateFormat("MM-dd-yyyy").format(myDate)
				     //arrayDates[index_arrayDates] = format.format(date);
				     //index_arrayDates ++;
				     //h = Collections.synchronizedMap(new HashMap());
					 //h.put("TARIKH",format.format(date));
					 //h.put("BIL",bil);				 
					 Hashtable h_r = rumusanOTByTarikh(session,ID_PERMOHONANOT,format.format(date),FLAG_CUTI);
					 int mpd = (int)h_r.get("total_minit_per_day");
					 int mwh = (int)h_r.get("total_minit_per_wh");
					 int mot = (int)h_r.get("total_minit_per_ot");
					 //h.put("TM_PD",mpd);
					 //h.put("TM_WH",mwh);
				     //h.put("TM_OT",mot);	
				     overall_minit_per_day += mpd;
					 overall_minit_per_wh += mwh;
					 overall_minit_per_ot += mot;
					 overall_hari++;
					 //listDates.add(h);				 
				 }
				 hash_overall_minit.put("OV_MPD",overall_minit_per_day);
				 
				 int total_minit_kerja_harian = 0;
				 if(!FLAG_CUTI.equals("Y"))
				 {
					 total_minit_kerja_harian = 240; //4 jam				
				 }
				 else
				 {
					 overall_minit_per_ot = overall_minit_per_ot+overall_minit_per_wh;
					 overall_minit_per_wh = 0;
				 }
				 
				 hash_overall_minit.put("OV_MWH_ALL",overall_hari*total_minit_kerja_harian);
				 hash_overall_minit.put("OV_MWH",overall_minit_per_wh);
				 hash_overall_minit.put("OV_MOT",overall_minit_per_ot);
				 hash_overall_minit.put("OV_DAYS",overall_hari);
				 myLogger.info("**CLOSE get date range "+startDate+" to "+endDate+" ARRAY : "+listDates);
				 //this.context.put("OVERALLMINIT", hash_overall_minit);
				 return hash_overall_minit;
			 }
		 
		 
		 
		 
		 
		 
		 public String queryLogOT(HttpSession session,String ID_PERMOHONANOT,String TARIKH_OT)
		 {
			 String sql = " SELECT P.NO_OT, MT.ID_MINITTRANSAKSI, MT.ID_TRANSAKSI, "+
						" TO_NUMBER(CASE WHEN UPPER(LT.TRANSAKSI_TYPE) = 'INSERT' THEN MT.MINIT_INSERT "+
						" WHEN UPPER(LT.TRANSAKSI_TYPE) = 'UPDATE' THEN MT.MINIT_UPDATE "+
						" ELSE 0 END) AS MINIT,LT.TRANSAKSI_TYPE, TO_CHAR(MAX (LT.WAKTU_LOG),'DD/MM/YYYY HH:MM:SS AM') AS WAKTU_LOG," +
						" TO_CHAR(MAX (LT.WAKTU_LOG),'HH24:MM:SS') AS HMS, "+
						" TO_CHAR(LT.WAKTU_LOG,'DD/MM/YYYY') AS TARIKH_LOG, LT.VALUE_FIELD_CHECK, TT.NAMA_AKTIVITI, "+
						" LT.ID_MASUK, TT.NAMA_TABLE, TT.FIELD_CHECK, NVL(UI.WAKTU_KERJA,'') AS WAKTU_KERJA, UI.USER_ID  "+
						" FROM TBLLOGTRANSAKSI LT,TBLTRANSAKSI TT, TBLMINITTRANSAKSI MT, USERS_INTERNAL UI, TBLPERMOHONANOT P "+
						" WHERE LT.ID_TRANSAKSI = TT.ID_TRANSAKSI AND MT.ID_TRANSAKSI = TT.ID_TRANSAKSI  "+
						" AND P.ID_PEMOHON = LT.ID_MASUK AND P.ID_PEMOHON = UI.USER_ID AND '"+TARIKH_OT+"' = TO_CHAR(LT.WAKTU_LOG,'DD/MM/YYYY') "+
						" AND TT.ID_SEKSYEN = '2' AND LT.ID_MASUK = UI.USER_ID " +
						//" AND P.ID_STATUS = '2' "+
						" AND P.ID_PERMOHONANOT = '"+ID_PERMOHONANOT+"' "+
						" GROUP BY  MT.ID_MINITTRANSAKSI, MT.ID_TRANSAKSI, MT.MINIT_INSERT, MT.MINIT_UPDATE, LT.TRANSAKSI_TYPE, "+
						" LT.VALUE_FIELD_CHECK, TT.NAMA_AKTIVITI,LT.ID_MASUK,TT.NAMA_TABLE, TT.FIELD_CHECK,TO_CHAR(LT.WAKTU_LOG,'DD/MM/YYYY'), "+
						" UI.WAKTU_KERJA, UI.USER_ID,P.NO_OT  "+
						" ORDER BY TO_CHAR(LT.WAKTU_LOG,'DD/MM/YYYY'), TO_CHAR (MAX(LT.WAKTU_LOG), 'HH24:MM:SS') ASC ";	
			 
			 return sql;
		 }
		 
		 
		 public Hashtable rumusanOTByTarikh(HttpSession session,String ID_PERMOHONANOT,String TARIKH_OT,String FLAG_CUTI) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					Hashtable h;
					h = new Hashtable();					
					sql = queryLogOT(session,ID_PERMOHONANOT,TARIKH_OT);
					myLogger.info(" V3: SQL listLogOT :"+ sql);
					rs = stmt.executeQuery(sql);				
					int total_minit_per_day = 0;
					int total_minit_per_wh = 0;
					int total_minit_per_ot = 0;
					while (rs.next()) {
						SimpleDateFormat formatd = new SimpleDateFormat("HH:mm:ss");
						Date masa_transaksi = formatd.parse(rs.getString("HMS"));
						
						String str_waktu_balik = "17:30:00";
						if(rs.getString("WAKTU_KERJA").toUpperCase().equals("WP1"))
						{
							str_waktu_balik = "16:30:00";
						}
						else if(rs.getString("WAKTU_KERJA").toUpperCase().equals("WP2"))
						{
							str_waktu_balik = "17:00:00";
						}
						else if(rs.getString("WAKTU_KERJA").toUpperCase().equals("WP3"))
						{
							str_waktu_balik = "17:30:00";
						}
						Date date_waktu_balik = formatd.parse(str_waktu_balik);
						total_minit_per_day = total_minit_per_day + rs.getInt("MINIT");
						if(!FLAG_CUTI.equals("Y") && (masa_transaksi.before(date_waktu_balik) || masa_transaksi.equals(date_waktu_balik)))
						{
							total_minit_per_wh = total_minit_per_wh +  rs.getInt("MINIT");														
						}
						else
						{
							total_minit_per_ot = total_minit_per_ot +  rs.getInt("MINIT");
						}
					}
					h.put("total_minit_per_day",total_minit_per_day);
					h.put("total_minit_per_wh",total_minit_per_wh);
					h.put("total_minit_per_ot",total_minit_per_ot);
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
		 
		 
		 @SuppressWarnings("unchecked")
			public List listLogOT(HttpSession session,String ID_PERMOHONANOT,String TARIKH_OT,String FLAG_CUTI)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listLogOT = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement(); 
					sql = queryLogOT(session,ID_PERMOHONANOT,TARIKH_OT);
					myLogger.info(" V3: SQL listLogOT :"+ sql);
					rs = stmt.executeQuery(sql);
					listLogOT = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					int total_minit_per_day = 0;
					int total_minit_per_wh = 0;
					int total_minit_per_ot = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						
						h.put("NO_OT",rs.getString("NO_OT") == null ? "" : rs.getString("NO_OT").toUpperCase());
						h.put("ID_MINITTRANSAKSI",rs.getString("ID_MINITTRANSAKSI") == null ? "" : rs.getString("ID_MINITTRANSAKSI").toUpperCase());
						h.put("MINIT",rs.getString("MINIT") == null ? "" : rs.getInt("MINIT"));
						h.put("TRANSAKSI_TYPE",rs.getString("TRANSAKSI_TYPE") == null ? "" : rs.getString("TRANSAKSI_TYPE").toUpperCase());
						h.put("WAKTU_LOG",rs.getString("WAKTU_LOG") == null ? "" : rs.getString("WAKTU_LOG"));
						h.put("TARIKH_LOG",rs.getString("TARIKH_LOG") == null ? "" : rs.getString("TARIKH_LOG").toUpperCase());
						h.put("VALUE_FIELD_CHECK",rs.getString("VALUE_FIELD_CHECK") == null ? "" : rs.getString("VALUE_FIELD_CHECK").toUpperCase());
						h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI").toUpperCase());
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK").toUpperCase());
						h.put("NAMA_TABLE",rs.getString("NAMA_TABLE") == null ? "" : rs.getString("NAMA_TABLE").toUpperCase());
						h.put("FIELD_CHECK",rs.getString("FIELD_CHECK") == null ? "" : rs.getString("FIELD_CHECK").toUpperCase());
						h.put("WAKTU_KERJA",rs.getString("WAKTU_KERJA") == null ? "" : rs.getString("WAKTU_KERJA").toUpperCase());
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID").toUpperCase());
						h.put("HMS",rs.getString("HMS") == null ? "" : rs.getString("HMS").toUpperCase());
						myLogger.info(" masa_transaksi String : "+rs.getString("HMS"));
						SimpleDateFormat formatd = new SimpleDateFormat("HH:mm:ss");
						Date masa_transaksi = formatd.parse(rs.getString("HMS"));
						myLogger.info(" masa_transaksi Date : "+masa_transaksi);
						
						String str_waktu_balik = "17:30:00";
						if(rs.getString("WAKTU_KERJA").toUpperCase().equals("WP1"))
						{
							str_waktu_balik = "16:30:00";
						}
						else if(rs.getString("WAKTU_KERJA").toUpperCase().equals("WP2"))
						{
							str_waktu_balik = "17:00:00";
						}
						else if(rs.getString("WAKTU_KERJA").toUpperCase().equals("WP3"))
						{
							str_waktu_balik = "17:30:00";
						}
						Date date_waktu_balik = formatd.parse(str_waktu_balik);
						total_minit_per_day = total_minit_per_day + rs.getInt("MINIT");
						if(!FLAG_CUTI.equals("Y") && (masa_transaksi.before(date_waktu_balik) || masa_transaksi.equals(date_waktu_balik)))
						{
							total_minit_per_wh = total_minit_per_wh +  rs.getInt("MINIT");							
							/*
							bil++;
							h.put("BIL",bil);
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
							listLogOT.add(h);
							*/							
						}
						else
						{
							bil++;
							h.put("BIL",bil);
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
							total_minit_per_ot = total_minit_per_ot +  rs.getInt("MINIT");
							listLogOT.add(h);
						}
						
						
						
					}
					
					myLogger.info(" -------------------------------------------------- ");
					myLogger.info(" total_minit_per_day : "+total_minit_per_day);
					myLogger.info(" total_minit_per_wh : "+total_minit_per_wh);
					myLogger.info(" total_minit_per_ot : "+total_minit_per_ot);
					myLogger.info(" -------------------------------------------------- ");
					

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listLogOT;

			}
		 
		 
		 
		 public List listLogOT_forPrint(HttpSession session,String ID_PERMOHONANOT,
				 String ID_SESKYEN, String TARIKH_MULA,String TARIKH_AKHIR,String WAKTU_KERJA,
				 String FLAG_CUTI)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listLogOT_forPrint = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement(); 
					sql = " SELECT 1 AS LAYER, '' AS NAMA_AKTIVITI, 0 AS MINIT, '' AS TRANSAKSI_TYPE, '' AS HMS, "+
							" TO_CHAR(((TO_DATE('"+TARIKH_MULA+"','DD/MM/YYYY')-1)+LEVEL),'DD/MM/YYYY') AS TARIKH_LOG FROM DUAL "+
							" CONNECT BY LEVEL <= TO_DATE('"+TARIKH_AKHIR+"','DD/MM/YYYY')-(TO_DATE('"+TARIKH_MULA+"','DD/MM/YYYY')-1) "+
							" UNION ALL "+
							" SELECT 2 AS LAYER, NAMA_AKTIVITI, MINIT, TRANSAKSI_TYPE, HMS, TARIKH_LOG "+
							" FROM "+
							" (SELECT   TT.NAMA_AKTIVITI, "+
							" TO_NUMBER "+
							" (CASE "+
							" WHEN UPPER (LT.TRANSAKSI_TYPE) = 'INSERT' "+
							" THEN MT.MINIT_INSERT "+
							" WHEN UPPER (LT.TRANSAKSI_TYPE) = 'UPDATE' "+
							" THEN MT.MINIT_UPDATE "+
							" ELSE 0 "+
							" END "+
							" ) AS MINIT, "+
							" LT.TRANSAKSI_TYPE, "+
							" TO_CHAR (MAX (LT.WAKTU_LOG), 'HH24:MM:SS') AS HMS, "+
							" TO_CHAR (LT.WAKTU_LOG, 'DD/MM/YYYY') AS TARIKH_LOG "+
							" FROM TBLLOGTRANSAKSI LT, "+
							" TBLTRANSAKSI TT,TBLMINITTRANSAKSI MT,USERS_INTERNAL UI,TBLPERMOHONANOT P "+
							" WHERE LT.ID_TRANSAKSI = TT.ID_TRANSAKSI AND MT.ID_TRANSAKSI = TT.ID_TRANSAKSI "+
							" AND P.ID_PEMOHON = LT.ID_MASUK AND P.ID_PEMOHON = UI.USER_ID "+
							" AND TT.ID_SEKSYEN = '"+ID_SESKYEN+"' AND LT.ID_MASUK = UI.USER_ID "+
							//" AND P.ID_STATUS = '2' " +
							" AND P.ID_PERMOHONANOT = '"+ID_PERMOHONANOT+"' "+
							" GROUP BY MT.ID_MINITTRANSAKSI, MT.ID_TRANSAKSI, "+
							" MT.MINIT_INSERT,MT.MINIT_UPDATE,LT.TRANSAKSI_TYPE, "+
							" LT.VALUE_FIELD_CHECK,TT.NAMA_AKTIVITI,LT.ID_MASUK, "+
							" TT.NAMA_TABLE,TT.FIELD_CHECK,TO_CHAR (LT.WAKTU_LOG, 'DD/MM/YYYY'), "+
							" UI.WAKTU_KERJA,UI.USER_ID,P.NO_OT "+
							" ORDER BY TO_CHAR(LT.WAKTU_LOG, 'DD/MM/YYYY'), MAX (LT.WAKTU_LOG) ASC "+
							" ) "+
							" ORDER BY TARIKH_LOG, LAYER, HMS ";			
					myLogger.info(" V3: SQL listLogOT_forPrint :"+ sql);
					rs = stmt.executeQuery(sql);
					listLogOT_forPrint = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						if(rs.getInt("LAYER")==1)
						{
							bil = 0;
							h.put("BIL",bil);						
							h.put("LAYER",rs.getString("LAYER") == null ? 0 : rs.getInt("LAYER"));
							h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI").toUpperCase());
							h.put("MINIT",rs.getString("MINIT") == null ? 0 : rs.getInt("MINIT"));
							h.put("TARIKH_LOG",rs.getString("TARIKH_LOG") == null ? "" : rs.getString("TARIKH_LOG").toUpperCase());
							h.put("HMS",rs.getString("HMS") == null ? "" : rs.getString("HMS").toUpperCase());
							h.put("TRANSAKSI_TYPE",rs.getString("TRANSAKSI_TYPE") == null ? "" : rs.getString("TRANSAKSI_TYPE").toUpperCase());
						    
							SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
							Date date = format.parse(rs.getString("TARIKH_LOG"));
							Hashtable h_r = rumusanOTByTarikh(session,ID_PERMOHONANOT,format.format(date),FLAG_CUTI);
							int mpd = (int)h_r.get("total_minit_per_day");
							int mwh = (int)h_r.get("total_minit_per_wh");
							int mot = (int)h_r.get("total_minit_per_ot");
							 
							 
							 int wh_all = 0;
							 if(!FLAG_CUTI.equals("Y"))
							 {
								 wh_all = 240; //4 jam				
							 }
							 else
							 {
								 mot = mot+mwh;
								 mwh = 0;
							 }		 
							 
							 h.put("TM_PD",mpd);
							 h.put("TM_WH",mwh);
						     h.put("TM_OT",mot);
						     h.put("WH_ALL",wh_all);
							
							
							listLogOT_forPrint.add(h);
						}
						else
						{
							SimpleDateFormat formatd = new SimpleDateFormat("HH:mm:ss");
							Date masa_transaksi = formatd.parse(rs.getString("HMS"));
							String str_waktu_balik = "17:30:00";
							if(WAKTU_KERJA.equals("WP1"))
							{
								str_waktu_balik = "16:30:00";
							}
							else if(WAKTU_KERJA.equals("WP2"))
							{
								str_waktu_balik = "17:00:00";
							}
							else if(WAKTU_KERJA.equals("WP3"))
							{
								str_waktu_balik = "17:30:00";
							}
							Date date_waktu_balik = formatd.parse(str_waktu_balik);
							if((!FLAG_CUTI.equals("Y") && (masa_transaksi.after(date_waktu_balik))) || FLAG_CUTI.equals("Y"))
							{								
								bil++;							
								h.put("BIL",bil);						
								h.put("LAYER",rs.getString("LAYER") == null ? 0 : rs.getInt("LAYER"));
								h.put("NAMA_AKTIVITI",rs.getString("NAMA_AKTIVITI") == null ? "" : rs.getString("NAMA_AKTIVITI").toUpperCase());
								h.put("MINIT",rs.getString("MINIT") == null ? 0 : rs.getInt("MINIT"));
								h.put("TARIKH_LOG",rs.getString("TARIKH_LOG") == null ? "" : rs.getString("TARIKH_LOG").toUpperCase());
								h.put("HMS",rs.getString("HMS") == null ? "" : rs.getString("HMS").toUpperCase());
								h.put("TRANSAKSI_TYPE",rs.getString("TRANSAKSI_TYPE") == null ? "" : rs.getString("TRANSAKSI_TYPE").toUpperCase());
								h.put("TM_PD",0);
								h.put("TM_WH",0);
							    h.put("TM_OT",0);
							    h.put("WH_ALL",0);
								listLogOT_forPrint.add(h);
							}
							
						}
						
						
						
						
						
						
						
						
							
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listLogOT_forPrint;

			}
		

		 public void hantarEmel(HttpSession session,String ID_PERMOHONANOT) throws Exception {
				
				myLogger.info("MASUK FUNCTION EMEL");	
				
				EmailProperty pro = EmailProperty.getInstance();
				EmailSender email = EmailSender.getInstance();
				email.FROM = pro.getFrom();
				email.MULTIPLE_RECIEPIENT = new String[1];
				String subject = "";
				String content = "";
				
				Hashtable maklumatOT_USER = maklumatOT_USER(session, ID_PERMOHONANOT);				
				String ID_STATUS = (String)maklumatOT_USER.get("ID_STATUS");
				String CATATAN_PELULUS = (String)maklumatOT_USER.get("CATATAN_PELULUS");
				String CATATAN_PEMOHON = (String)maklumatOT_USER.get("CATATAN_PEMOHON");
				String NO_OT = (String)maklumatOT_USER.get("NO_OT");
				String NAMA_PEMOHON = (String)maklumatOT_USER.get("NAMA_PEMOHON");
				String NAMA_PELULUS = (String)maklumatOT_USER.get("NAMA_PELULUS");
				String EMEL_PEMOHON = (String)maklumatOT_USER.get("EMEL_PEMOHON");
				String EMEL_PELULUS = (String)maklumatOT_USER.get("EMEL_PELULUS");
				
				if(ID_STATUS.equals("2") || ID_STATUS.equals("3"))//permohonan lulus or tolak
				{
					subject = " KEPUTUSAN PERMOHONAN OT : "+NO_OT;
					if(ID_STATUS.equals("2"))
					{
						content = " Permohonan OT anda adalah diluluskan oleh "+EMEL_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
					}else
					{
						content = " Permohonan OT anda adalah ditolak oleh "+EMEL_PELULUS+". Sila semak permohonan ini di MyeTaPP.";
					}
					
					content += "<br><br>Catatan : "+CATATAN_PELULUS;
					email.MULTIPLE_RECIEPIENT[0] = EMEL_PEMOHON;
				}
				else if(ID_STATUS.equals("1"))//pemohonan baru
				{
					subject = " PERMOHONAN BARU OT : "+NO_OT;
					content = " Anda menerima permohonan OT daripada "+NAMA_PEMOHON+". Sila semak permohonan ini di MyeTaPP.";
					content += "<br><br>Catatan : "+CATATAN_PEMOHON;
					email.MULTIPLE_RECIEPIENT[0] = EMEL_PELULUS;
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

		 public Hashtable maklumatOT_USER(HttpSession session, String ID_PERMOHONANOT) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					Hashtable h;
					h = new Hashtable();
					
						sql = " SELECT OT.ID_STATUS, UPPER(U_PM.USER_NAME) AS NAMA_PEMOHON, UPPER(U_PL.USER_NAME) AS NAMA_PELULUS, "+
								" NVL(PM.EMEL,'') AS EMEL_PEMOHON, NVL(PL.EMEL,'') AS EMEL_PELULUS, "+
								" OT.NO_OT, UPPER(OT.CATATAN_PELULUS) AS CATATAN_PELULUS, UPPER(OT.CATATAN_PEMOHON) AS CATATAN_PEMOHON "+
								" FROM  USERS_INTERNAL PM, USERS_INTERNAL PL, TBLPERMOHONANOT OT, USERS U_PM, USERS U_PL "+
								" WHERE OT.ID_PEMOHON = PM.USER_ID AND OT.ID_PELULUS = PL.USER_ID "+
								" AND PM.USER_ID = U_PM.USER_ID AND  PL.USER_ID = U_PL.USER_ID AND OT.ID_PERMOHONANOT = '"+ID_PERMOHONANOT+"' ";
						
						myLogger.info(" OT : maklumatOT_USER :" + sql.toUpperCase());
						rs = stmt.executeQuery(sql);
						
						
						while (rs.next()) {
							h.put("NAMA_PEMOHON",rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
							h.put("NAMA_PELULUS",rs.getString("NAMA_PELULUS") == null ? "" : rs.getString("NAMA_PELULUS"));
							h.put("EMEL_PEMOHON",rs.getString("EMEL_PEMOHON") == null ? "" : rs.getString("EMEL_PEMOHON"));
							h.put("EMEL_PELULUS",rs.getString("EMEL_PELULUS") == null ? "" : rs.getString("EMEL_PELULUS"));
							h.put("NO_OT",rs.getString("NO_OT") == null ? "" : rs.getString("NO_OT"));
							h.put("ID_STATUS",rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
							h.put("CATATAN_PELULUS",rs.getString("CATATAN_PELULUS") == null ? "" : rs.getString("CATATAN_PELULUS"));
							h.put("CATATAN_PEMOHON",rs.getString("CATATAN_PEMOHON") == null ? "" : rs.getString("CATATAN_PEMOHON"));
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
		 
		 
		 //aishah start here
		 
		 public Hashtable viewPKK(HttpSession session, String ID_PKE) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					Hashtable h;
					h = new Hashtable();
					
					if(!ID_PKE.equals(""))
					{
						sql = " SELECT A.ID_PKE,A.ID_NEGERI, B.NAMA_NEGERI,A.ID_JKPTG AS ID_UNIT, C.NAMA_PEJABAT AS NAMA_UNIT, A.CATATAN, TO_CHAR(A.TARIKH_MOHON, 'DD/MM/YYYY') AS TARIKH_MOHON " +
								" FROM TBLPPKKEBENARANEDIT A, TBLRUJNEGERI B, TBLRUJPEJABATJKPTG C , USERS D " +
								" WHERE A.ID_NEGERI = B.ID_NEGERI " +
								" AND A.ID_JKPTG = C.ID_PEJABATJKPTG " +
								" AND A.USER_ID_MOHON = D.USER_ID " +
								" AND A.ID_PKE = '"+ID_PKE+"' ";
						
						myLogger.info(" KEBENARAN KEMASKINI : viewPKK :" + sql.toUpperCase());
						rs = stmt.executeQuery(sql);
						
						
						while (rs.next()) {
							
							h.put("ID_PKE",rs.getString("ID_PKE") == null ? "" : rs.getString("ID_PKE"));
							h.put("ID_NEGERI_",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
							h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
							h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
							h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
							h.put("CATATAN",rs.getString("CATATAN") == null ? 0 : rs.getInt("CATATAN"));
							h.put("TARIKH_MOHON",rs.getString("TARIKH_MOHON") == null ? 0 : rs.getInt("TARIKH_MOHON"));

						}
					}
					else
					{
						h.put("ID_PKE","");
						h.put("ID_NEGERI",0);
						h.put("NAMA_NEGERI","");
						h.put("ID_JKPTG",0);
						h.put("NAMA_PEJABAT","");
						h.put("CATATAN","");
						h.put("TARIKH_MOHON","");
			
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
		 
		 
		 public int insertTBLPPKKEBENARANEDIT(HttpSession session,int ID_PKE) throws Exception {
				Connection conn = null;
				Db db = null;
				String sql = "";
				long idPKE = 0;
				long idPKS = 0;
				long ID_PKS = 0;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				String ID_NEGERI = getParam("PKK_ID_NEGERI");
				myLogger.info("ID_NEGERI"+ID_NEGERI);
				String ID_UNIT = getParam("PKK_ID_UNIT");
				myLogger.info("ID_UNIT"+ID_UNIT);
				String catatan = getParam("catatan_kebenaran_edit");
				myLogger.info("catatan"+catatan);
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					System.out.println("#########idPKE =========="+ID_PKE);
					
					//INSERT TABLE TBLPPKKEBENARANEDIT
					if(ID_PKE!=0)
					{
						r.update("ID_PKE", ID_PKE);
						
					}
					else
					{
						idPKE = DB.getNextID(db, "TBLPPKKEBENARANEDIT_SEQ");
						
						System.out.println("#########idPKE =========="+idPKE);
						r.add("ID_PKE", idPKE);
					}
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("ID_UNIT", ID_UNIT);
					r.add("CATATAN", catatan);
					r.add("STATUS_KELULUSAN", "1");
					
					if(ID_PKE!=0)
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("TBLPPKKEBENARANEDIT");
					}
					else
					{
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLPPKKEBENARANEDIT");	
					}
					
									
					
					myLogger.info("OT : SAVE PMT : "+sql);				
					stmt.executeUpdate(sql);
					conn.commit();
					if(ID_PKE!=0)
					{
						AuditTrail.logActivity(this,session,"UPD","TBLPPKKEBENARANEDIT [ID_PERMOHONAN : "+ID_PKE+"] Updated");
					}
					else
					{
						AuditTrail.logActivity(this,session,"INS","TBLPPKKEBENARANEDIT [ID_PERMOHONAN : "+ID_PKE+"] Inserted");
					}
					
					return ID_PKE;
					
					
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
		 
		 
		 public int insertTBLPPKKEBENARANSTAFF(HttpSession session,int ID_PKE) throws Exception {
				Connection conn = null;
				Db db = null;
				String sql = "";
				long idPKE = 0;
				long idPKS = 0;
				long ID_PKS = 0;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					String ID_NEGERI = getParam("ID_NEGERI");
					myLogger.info("ID_NEGERI"+ID_NEGERI);
					String ID_UNIT = getParam("ID_UNIT");
					myLogger.info("ID_UNIT"+ID_UNIT);

				
					
				//insert TBLPPKKEBENARANSTAFF
					
					int countStaff  = logic.isExistStaffUnit(ID_PKE);
					if(countStaff != 0)
					{
						ID_PKS = 0;
					}
					else
					{
						// insert
						r.update("ID_PKE", idPKE);
						
						if(ID_PKS!=0)
						{
							r.update("ID_PKS", ID_PKS);
							
							r.add("ID_KEMASKINI", USER_ID_SYSTEM);
							r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
							sql = r.getSQLUpdate("TBLPPKKEBENARANSTAFF");
						}
						else
						{
							list_users = getListUsers(USER_ID_SYSTEM, ID_UNIT, ID_NEGERI, "", "", "2");
							
							if(list_users.size()>0){
								Hashtable get_user = (Hashtable) list_users.get(0);
								
								idPKS = DB.getNextID(db, "TBLPPKKEBENARANSTAFF_SEQ");
								r.add("ID_PKS", idPKS);
								
								r.add("ID_STAFF", (String) get_user.get("user_id"));
								r.add("ID_MASUK", USER_ID_SYSTEM);
								r.add("TARIKH_MASUK", r.unquote("sysdate"));
								sql = r.getSQLInsert("TBLPPKKEBENARANSTAFF");	
								
							
								//hantar email
								//hantarEmel("content","Tindakan Pengarah/Pegawai untuk Permohonan Kebenaran Kemaskini Fail",txtNoFailSub, tujuan,pegawaiAgihan ,idpegawaiMemohon,namaPegawaiMemohon,tarikMohon,namaSiMati);
							}
						}
						
						
						
						
						
					}
					
				
					
					
					myLogger.info("OT : SAVE PMT : "+sql);				
					stmt.executeUpdate(sql);
					conn.commit();
					if(ID_PKE!=0)
					{
						AuditTrail.logActivity(this,session,"UPD","TBLPPKKEBENARANSTAFF [ID_PKS : "+ID_PKS+"] Updated");
					}
					else
					{
						AuditTrail.logActivity(this,session,"INS","TBLPPKKEBENARANSTAFF [ID_PKS : "+ID_PKS+"] Inserted");
					}
					
					return ID_PKE;
					
					
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
					
					if(!id_pejabat.equals("") && !id_pejabat.equals(null))
					{
					sql += " AND UI.ID_PEJABATJKPTG = '"+id_pejabat+"'";	
					}
					if(!id_negeri.equals("") && !id_negeri.equals(null))
					{
					sql += " AND UI.ID_NEGERI = '"+id_negeri+"'";	
					}
					
					if(!role.equals("") && !role.equals(null))
					{
					sql += " AND U.USER_ROLE = 'adminppk";	
					}
					if(!id_seksyen.equals("") && !id_seksyen.equals(null))
					{
					sql += " AND UI.ID_SEKSYEN = '2'";	
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
			
			
			
		 
}