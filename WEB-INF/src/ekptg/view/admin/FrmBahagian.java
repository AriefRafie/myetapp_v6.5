package ekptg.view.admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

	@SuppressWarnings("serial")
	public class FrmBahagian extends AjaxBasedModule {
		
		static Logger myLogger = Logger.getLogger(ekptg.view.admin.FrmBahagian.class);
		String skrin_name = "app/admin/BahagianUnit/indexBahagian.jsp";
		
		@Override
		public String doTemplate2() throws Exception {	
			
			List listBahagian = null;
			List listBahagianHQ = null;
			List listUnitHQ = null;
			Hashtable viewBahagianHQ = null;
			Hashtable viewUnitHQ = null;
			List list_TBLRUJNEGERI = null;
			List list_TBLRUJSEKSYEN = null;
			List list_TBLRUJBANDAR = null;
			List list_TBLRUJDAERAH = null;
			List listPrint = null;
			List listPengguna = null;
			List listDokumen = null;
			
			List listStatsJawatan = null;
			
			HttpSession session = this.request.getSession();
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
			
			String USER_LOGIN_ROLE =  (String) session.getAttribute("_portal_role");
			context.put("USER_LOGIN_ROLE",USER_LOGIN_ROLE);
			myLogger.info("_portal_role -- "+USER_LOGIN_ROLE);
			
			String user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
			myLogger.info("user_negeri_login -- "+user_negeri_login);
			
			String command = getParam("command");
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);
			myLogger.info("Page Bahagian command : "+command +" FlagCari : "+FlagCari);
			
			//initialize first
			this.context.put("carianBahagianHQ", "");
			this.context.put("FlagCari", "");
			this.context.put("listPrint", "");
			this.context.put("carianBahagian", "");
			this.context.put("listBahagian", "");
			this.context.put("viewBahagianHQ","");
			this.context.put("listDokumen", "");
			this.context.put("commandDoc", "");
			this.context.put("after_saveDOC", "");
			
	       	this.context.put("isComplete", false);
	       	this.context.put("COOR_UPLOAD","");
			this.context.put("after_uploadLampiran","");
			this.context.put("viewUnitHQ","");
			this.context.put("carianUnit","");
			
			String carianUmum = "";
			String carianBahagian = "";
			String carianUnitHQ = "";
			String carianBahagianHQ = "";
			String ID_NEGERI = "";
			
			
			String cetakReport = "";
			this.context.put("cetakReport", cetakReport);
			
			String action = getParam("action");
			
			if(command.equals("batalCarianBahagian"))
			{
				
				skrin_name = "app/admin/BahagianUnit/indexBahagian.jsp";
			}
			else if(command.equals("batalBahagian"))
			{
				
				skrin_name = "app/admin/BahagianUnit/blank_BahagianHQ.jsp";
			}
			else if(command.equals("carianUmum"))
			{
				this.context.put("FlagCari", FlagCari);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				
				skrin_name = "app/admin/BahagianUnit/SenaraiUtama.jsp";
			}
			else if(command.equals("printListBahagian"))
			{
				this.context.put("FlagCari", FlagCari);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				
				carianBahagianHQ = getParam("carianUmum");
				this.context.put("carianBahagianHQ", carianBahagianHQ);
				
				listPrint = getSenaraiBahagianHQ(session, carianBahagianHQ);
				this.context.put("listPrint",listPrint);
				
				skrin_name = "app/admin/BahagianUnit/SenaraiForPrint.jsp";
			}
			else if(command.equals("showCarianBahagian"))
			{
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				
				carianBahagian = getParam("carianBahagian");
				this.context.put("carianBahagian", carianBahagian);
				 this.context.put("listBahagian",listBahagian);
				 
				 skrin_name = "app/admin/BahagianUnit/CarianBahagianHQ.jsp";
			}
			else if(command.equals("showSenaraiBahagian"))
			{
				this.context.put("SuccessMesejDeleteUser", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				
				if(FLAG_DELETE.equals("Y"))
				{
					String ID_SEKSYEN = getParam("ID_SEKSYEN");
					deleteBahagian(session,ID_SEKSYEN);
					this.context.put("SuccessMesejDeleteUser", "Maklumat Bahagian Berjaya Dihapus");
				}
				/*
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);*/
				
				carianBahagianHQ = getParam("carianUmum");
				this.context.put("carianBahagianHQ", carianBahagianHQ);
				
				//if (TYPE.equals("HQ")){
				
				listBahagianHQ = getSenaraiBahagianHQ(session, carianBahagianHQ);
				setupPageList(session, action, listBahagianHQ, "listBahagianHQ",command,"div_senaraiUtama");
				//} else if (TYPE.equals("NEGERI")){
				//listBahagianHQ = getSenaraiBahagianHQ(session, carianBahagianHQ);
				//setupPageList(session, action, listBahagianHQ, "listBahagianHQ",command,"div_SenaraiBahagianNegeri");
				//}
				// this.context.put("listBahagianHQ",listBahagianHQ);
				 
				 skrin_name = "app/admin/BahagianUnit/SenaraiBahagianHQ.jsp";
			}
			else if (command.equals("viewDetailsBahagian")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				viewBahagianHQ = getDetailsBahagianHQ(session, ID_SEKSYEN);
				this.context.put("viewBahagianHQ",viewBahagianHQ);
				
				/*listDokumen = getListDoc(session, ID_SEKSYEN);
				setupPageList(session, action, listDokumen, "listDokumen",command,"displayDoc"+);
				*///this.context.put("listDokumen",listDokumen);
				
				skrin_name = "app/admin/BahagianUnit/ViewDetailsBahagian.jsp";
			}
			else if(command.equals("editBahagian")){
			
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				viewBahagianHQ = getDetailsBahagianHQ(session, ID_SEKSYEN);
				this.context.put("viewBahagianHQ",viewBahagianHQ);
				
				list_TBLRUJNEGERI = listTableRujukan(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				skrin_name = "app/admin/BahagianUnit/EditDetailsBahagian.jsp";
			}
			else if(command.equals("saveBahagian")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				if(ID_SEKSYEN==null || ID_SEKSYEN.equals("") || ID_SEKSYEN.equals("null")){
					System.out.println("ID_SEKSYEN insert? -- "+ID_SEKSYEN);
					ID_SEKSYEN = saveDetailsBahagian(session, ID_SEKSYEN);
					System.out.println("ID_SEKSYEN after insert? -- "+ID_SEKSYEN);
					
					carianBahagianHQ = getParam("carianBahagianHQ");
					
					listBahagianHQ = getSenaraiBahagianHQ(session, carianBahagianHQ);
					setupPageList(session, action, listBahagianHQ, "listBahagianHQ",command,"div_senaraiUtama");
					// this.context.put("listBahagianHQ",listBahagianHQ);
					 
					 skrin_name = "app/admin/BahagianUnit/SenaraiBahagianHQ.jsp";
					
					} else{
						
					System.out.println("ID_SEKSYEN update? -- "+ID_SEKSYEN);
					ID_SEKSYEN = saveDetailsBahagian(session, ID_SEKSYEN);
					System.out.println("ID_SEKSYEN after update? -- "+ID_SEKSYEN);
					
					viewBahagianHQ = getDetailsBahagianHQ(session, ID_SEKSYEN);
					this.context.put("viewBahagianHQ",viewBahagianHQ);
					
					listDokumen = getListDoc(session, ID_SEKSYEN);
					this.context.put("listDokumen",listDokumen);
					
					skrin_name = "app/admin/BahagianUnit/ViewDetailsBahagian.jsp";

					}

			}
			else if(command.equals("popupUpload")){
				
				this.context.put("isComplete", false);
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				
				skrin_name = "app/admin/BahagianUnit/FrmTambahLampiran.jsp";
			}
			
			else if(command.equals("simpanDokumen")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN_AFTERUPLOAD",ID_SEKSYEN);
				myLogger.info("ID_SEKSYEN simpanDokumen -- "+ID_SEKSYEN);
				
				String returnDivUpload = getParam("returnDivUpload");
				this.context.put("returnDivUpload",returnDivUpload);
				
				this.context.put("commandDoc", "showDokumen");
				this.context.put("after_saveDOC", "Y");
				
				uploadFiles(ID_SEKSYEN);
		       	this.context.put("isComplete", true);
		       	this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
				this.context.put("after_uploadLampiran","Y");			
				
				skrin_name = "app/admin/BahagianUnit/indexBahagian.jsp";;
			}
			else if(command.equals("showDokumen")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				myLogger.info("ID_SEKSYEN simpanDokumen -- "+ID_SEKSYEN);
				
				String FLAG_DELETE = getParam("FLAG_DELETE");
				
				if (FLAG_DELETE.equals("Y")){
					//delete dokumen
					String ID_DOKUMEN = getParam("ID_DOKUMEN");
					deleteDokumen(session, ID_SEKSYEN, ID_DOKUMEN);	
					this.context.put("SuccessMesejDeleteUser", "Dokumen Berjaya Dihapus");
				}
				
				listDokumen = getListDoc(session, ID_SEKSYEN);
				setupPageList(session, action, listDokumen, "listDokumen",command,"Senarai_Doc"+ID_SEKSYEN);
				
				/*listDokumen = getListDoc(session, ID_SEKSYEN);
				this.context.put("listDokumen",listDokumen);*/
				
				skrin_name = "app/admin/BahagianUnit/SenaraiLampiranBahagian.jsp";
			}
			
			else if(command.equals("carianUnit")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				myLogger.info("ID_SEKSYEN carianUnitHQ -- "+ID_SEKSYEN);
				
				list_TBLRUJNEGERI = listTableRujukan(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				carianUnitHQ = getParam("carianUnitHQ");
				this.context.put("carianUnit", carianUnitHQ);
				
				if (ID_SEKSYEN.equals("2")){
					ID_NEGERI = getParam("ID_NEGERI_"+ID_SEKSYEN);
					this.context.put("ID_NEGERI", ID_NEGERI);
					myLogger.info("ID_NEGERI  -- "+ID_NEGERI);
					
					listUnitHQ = getSenaraiUnitHQ(session, carianUnitHQ,ID_SEKSYEN,ID_NEGERI);
					setupPageList(session, action, listUnitHQ, "listUnitHQ",command,"div_SenaraiUnit");
					
					}else {
						
				listUnitHQ = getSenaraiUnitHQ(session, carianUnitHQ,ID_SEKSYEN,ID_NEGERI);
				setupPageList(session, action, listUnitHQ, "listUnitHQ",command,"div_ListMaklumatUnit");
					}
				
				skrin_name = "app/admin/BahagianUnit/CarianUnitHQ.jsp";
			}
			else if(command.equals("showSenaraiUnit"))
			{
				/*this.context.put("SuccessMesejDeleteUser", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				String JENISPEJ = getParam("JENISPEJ");
				
				if(FLAG_DELETE.equals("Y"))
				{
					String ID_PEJABAT = getParam("ID_PEJABAT");
					deletePengguna(session,ID_PEJABAT,JENISPEJ);
					this.context.put("SuccessMesejDeleteUser", "Maklumat Pejabat Berjaya Dihapus");
				}*/
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				myLogger.info("ID_SEKSYEN carianUnitHQ -- "+ID_SEKSYEN);
				
				carianUnitHQ = getParam("carianUnit");
				this.context.put("carianUnitHQ", carianUnitHQ);
				myLogger.info("carianUnitHQ  -- "+carianUnitHQ);
				
				if (ID_SEKSYEN.equals("2")){
				ID_NEGERI = getParam("ID_NEGERI_"+ID_SEKSYEN);
				this.context.put("ID_NEGERI", ID_NEGERI);
				myLogger.info("ID_NEGERI  -- "+ID_NEGERI);
				}
				
				listUnitHQ = getSenaraiUnitHQ(session, carianUnitHQ,ID_SEKSYEN,ID_NEGERI);
				setupPageList(session, action, listUnitHQ, "listUnitHQ",command,"div_SenaraiUnit");
				
				
				 skrin_name = "app/admin/BahagianUnit/SenaraiUnitHQ.jsp";
			}
			else if (command.equals("viewDetailsUnit")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				String ID_UNIT = getParam("ID_UNIT");

				
				viewUnitHQ = getDetailsUnitHQ(session, ID_SEKSYEN, ID_UNIT);
				this.context.put("viewUnitHQ",viewUnitHQ);
				
				skrin_name = "app/admin/BahagianUnit/ViewDetailsUnit.jsp";
			}
			else if(command.equals("editUnit")){
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				
				String ID_UNIT = getParam("ID_UNIT");
				this.context.put("ID_UNIT",ID_UNIT);
				
				viewUnitHQ = getDetailsUnitHQ(session, ID_SEKSYEN, ID_UNIT);
				this.context.put("viewUnitHQ",viewUnitHQ);
				
				list_TBLRUJNEGERI = listTableRujukan(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				skrin_name = "app/admin/BahagianUnit/EditDetailsUnitHQ.jsp";
			}
			else if(command.equals("saveUnitHQ")){
			
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				String ID_UNIT = getParam("ID_UNIT");
				
				if (ID_SEKSYEN.equals("2")){
				ID_NEGERI = getParam("ID_NEGERI_"+ID_SEKSYEN);
				this.context.put("ID_NEGERI", ID_NEGERI);
				myLogger.info("ID_NEGERI  -- "+ID_NEGERI);
				}
			
			if(ID_UNIT==null || ID_UNIT.equals("") || ID_UNIT.equals("null")){
				System.out.println("ID_UNIT insert? -- "+ID_UNIT);
				ID_UNIT = saveDetailsUnit(session, ID_SEKSYEN, ID_UNIT);
				System.out.println("ID_UNIT after insert? -- "+ID_UNIT);
				this.context.put("ID_UNIT",ID_UNIT);
				
				viewUnitHQ = getDetailsUnitHQ(session, ID_SEKSYEN, ID_UNIT);
				this.context.put("viewUnitHQ",viewUnitHQ);
				
				listUnitHQ = getSenaraiUnitHQ(session, carianUnitHQ,ID_SEKSYEN,ID_NEGERI);
				setupPageList(session, action, listUnitHQ, "listUnitHQ",command,"div_senaraiUnit"+ID_SEKSYEN);
				 //this.context.put("listBahagianHQ",listUnitHQ);
				 
				 skrin_name = "app/admin/BahagianUnit/ViewDetailsUnit.jsp";
				
				} else{
					
				System.out.println("ID_UNIT update? -- "+ID_UNIT);
				ID_UNIT = saveDetailsUnit(session, ID_SEKSYEN, ID_UNIT);
				System.out.println("ID_UNIT after update? -- "+ID_UNIT);

				viewUnitHQ = getDetailsUnitHQ(session, ID_SEKSYEN, ID_UNIT);
				this.context.put("viewUnitHQ",viewUnitHQ);
				
				skrin_name = "app/admin/BahagianUnit/ViewDetailsUnit.jsp";
				
				}
			}else if(command.equals("showListPejabat"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				ID_NEGERI = getParam("ID_NEGERI");
				myLogger.info("ID_NEGERI : "+ID_NEGERI);
				this.context.put("ID_NEGERI",ID_NEGERI);
				
				list_TBLRUJDAERAH = listTableRujukan(session,"TBLRUJDAERAHBYNEGERI",ID_NEGERI,"0");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				skrin_name = "app/admin/BahagianUnit/showListDaerah.jsp";
			}
			else if(command.equals("showListBandar"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				myLogger.info("ID_SEKSYEN : "+ID_SEKSYEN);
				
				ID_NEGERI = getParam("ID_NEGERI");
				myLogger.info("ID_NEGERI : "+ID_NEGERI);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH : "+ID_DAERAH);
				
				list_TBLRUJBANDAR = listTableRujukan(session,"TBLRUJBANDARBYNEGERI",ID_NEGERI,ID_DAERAH);
				this.context.put("list_TBLRUJBANDAR",list_TBLRUJBANDAR);
				
				skrin_name = "app/admin/BahagianUnit/showListBandar.jsp";
			}
			else if(command.equals("printDetailsBahagian"))
			{
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				if (ID_SEKSYEN.equals("2")){
					ID_NEGERI = getParam("ID_NEGERI_"+ID_SEKSYEN);
					this.context.put("ID_NEGERI", ID_NEGERI);
					myLogger.info("ID_NEGERI  -- "+ID_NEGERI);
				}
				
				viewBahagianHQ = getDetailsBahagianHQ(session, ID_SEKSYEN);
				this.context.put("viewBahagianHQ",viewBahagianHQ);
				
				listUnitHQ = getSenaraiUnitHQ(session, carianUnitHQ,ID_SEKSYEN,ID_NEGERI);
				//setupPageList(session, action, listUnitHQ, "listUnitHQ",command,"div_SenaraiUnit");
				this.context.put("listUnitHQ",listUnitHQ);
				
				//skrin_name = "app/admin/BahagianUnit/SenaraiForPrintDetailBahagian.jsp";
				skrin_name = "app/admin/BahagianUnit/SenaraiPrintDetailBahagian2.jsp";
			}
			else if(command.equals("cetakBorangSemakanPengguna"))
			{
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				
				viewBahagianHQ = getDetailsBahagianHQ(session, ID_SEKSYEN);
				this.context.put("viewBahagianHQ",viewBahagianHQ);
				
				listPengguna = getSenaraiPengguna(session, ID_SEKSYEN);
				setupPageList(session, action, listPengguna, "listPengguna",command,"div_SenaraiBahagian");
				this.context.put("listPengguna",listPengguna);
				
				listStatsJawatan = getStatsJawatan(session,ID_SEKSYEN);
				this.context.put("listStatsJawatan",listStatsJawatan);
				
				
				skrin_name = "app/admin/BahagianUnit/SenaraiPrintPenggunaEx.jsp";
			}
			else if(command.equals("showListPejabatUnit"))
			{
				String ID_PEJABATJKPTG = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABATJKPTG",ID_PEJABATJKPTG);
				
				ID_NEGERI = getParam("ID_NEGERI");
				myLogger.info("ID_NEGERI : "+ID_NEGERI);
				this.context.put("ID_NEGERI",ID_NEGERI);
				
				list_TBLRUJDAERAH = listTableRujukan(session,"TBLRUJDAERAHBYNEGERI",ID_NEGERI,"0");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				skrin_name = "app/admin/BahagianUnit/showListDaerahUnit.jsp";
			}
			else if(command.equals("showListBandarUnit"))
			{
				String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
				this.context.put("ID_PEJABATJKPTG",ID_PEJABATJKPTG);
				myLogger.info("ID_PEJABATJKPTG BANDAR : "+ID_PEJABATJKPTG);
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				myLogger.info("ID_SEKSYEN : "+ID_SEKSYEN);
				
				ID_NEGERI = getParam("ID_NEGERI");
				myLogger.info("ID_NEGERI : "+ID_NEGERI);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH : "+ID_DAERAH);
				
				list_TBLRUJBANDAR = listTableRujukan(session,"TBLRUJBANDARBYNEGERI",ID_NEGERI,ID_DAERAH);
				this.context.put("list_TBLRUJBANDAR",list_TBLRUJBANDAR);
				
				skrin_name = "app/admin/BahagianUnit/showListBandarUnit.jsp";
			}
			
			else
			{
				
				this.context.put("FlagCari","");
				this.context.put("listBahagianHQ","");
				skrin_name = "app/admin/BahagianUnit/indexBahagian.jsp";
			}
			
			return skrin_name;
		}

		public String checkIsNull(String txt) {
			if (txt == null) return "";
			else return txt;
		}
		
		public void deleteDokumen(HttpSession session,String ID_SEKSYEN, String ID_DOKUMEN) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				if (!ID_DOKUMEN.equals("")){
					r.add("ID_DOKUMEN", ID_DOKUMEN);
					sql = r.getSQLDelete("TBLBAHAGIANDOKUMEN");
					AuditTrail.logActivity(this,session,"DEL","DOKUMEN ["+ID_DOKUMEN+"] Deleted");
				}
				
				stmt.executeUpdate(sql);
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Delete DOKUMEN :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
		}
		
		public String saveDetailsUnit(HttpSession session,String ID_SEKSYEN, String ID_UNIT) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_UNIT_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String NAMA_UNIT = getParam("NAMA_UNIT_"+ID_SEKSYEN+ID_UNIT);
				String KOD_UNIT = getParam("KOD_UNIT_"+ID_SEKSYEN+ID_UNIT);
				String ALAMAT1 = getParam("ALAMAT1_"+ID_SEKSYEN+ID_UNIT);
				String ALAMAT2 = getParam("ALAMAT2_"+ID_SEKSYEN+ID_UNIT);
				String ALAMAT3 = getParam("ALAMAT3_"+ID_SEKSYEN+ID_UNIT);
				String POSKOD = getParam("POSKOD_"+ID_SEKSYEN+ID_UNIT);
				String ID_NEGERI = getParam("ID_NEGERI_"+ID_SEKSYEN+ID_UNIT);
				String ID_BANDAR = getParam("ID_BANDAR_"+ID_SEKSYEN+ID_UNIT);
				String NO_TEL = getParam("NO_TEL_"+ID_SEKSYEN+ID_UNIT);
				String NO_FAX = getParam("NO_FAX_"+ID_SEKSYEN+ID_UNIT);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_SEKSYEN+ID_UNIT);
				
				String ID_DAERAH = getParam("ID_DAERAH_"+ID_SEKSYEN+ID_UNIT);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r.add("NAMA_PEJABAT", NAMA_UNIT.toUpperCase());
				r.add("KOD_JKPTG", KOD_UNIT.toUpperCase());
				
				r.add("ALAMAT1", ALAMAT1.toUpperCase());
				r.add("ALAMAT2", ALAMAT2.toUpperCase());
				r.add("ALAMAT3", ALAMAT3.toUpperCase());
				
				r.add("POSKOD", POSKOD);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_BANDAR", ID_BANDAR);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				
				r.add("NO_TEL", NO_TEL);
				r.add("NO_FAX", NO_FAX);
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				r.add("ID_DAERAH",ID_DAERAH);
				
				if(ID_UNIT.equals("")){
					
				myLogger.info("MASUK INSERT TBLPJBTJKPTG : "+ID_UNIT);
					
				ID_UNIT_INSERT = DB.getNextID(db,"TBLRUJPEJABATJKPTG_SEQ");
				ID_UNIT = ID_UNIT_INSERT+"";
			
				r.add("ID_PEJABATJKPTG", ID_UNIT);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJPEJABATJKPTG");	
				
				}
				else{
				r.update("ID_PEJABATJKPTG", ID_UNIT);	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJPEJABATJKPTG");	
				
				}
				
				myLogger.info("ACTION UNIT (TBLRUJPEJABATJKPTG) : "+sql.toUpperCase());
				
				stmt.executeUpdate(sql);
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat action save UNIT :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_UNIT;
		}
		
		public String saveDetailsBahagian(HttpSession session,String ID_SEKSYEN) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_SEKSYEN_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String NAMA_SEKSYEN = getParam("NAMA_SEKSYEN_"+ID_SEKSYEN);
				String KOD_SEKSYEN = getParam("KOD_SEKSYEN_"+ID_SEKSYEN);
				String CATATAN = getParam("CATATAN_"+ID_SEKSYEN);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_SEKSYEN);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("NAMA_SEKSYEN", NAMA_SEKSYEN.toUpperCase());
				r.add("KOD_SEKSYEN", KOD_SEKSYEN.toUpperCase());
				r.add("CATATAN", CATATAN.toUpperCase());
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				if(ID_SEKSYEN.equals("")){
				ID_SEKSYEN_INSERT = DB.getNextID(db,"TBLRUJSEKSYEN_SEQ");
				ID_SEKSYEN = ID_SEKSYEN_INSERT+"";
				
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJSEKSYEN");	
				
				}
				else{
				r.update("ID_SEKSYEN", ID_SEKSYEN);	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJSEKSYEN");	
				
				}

				myLogger.info("ACTION TBLRUJSEKSYEN : "+sql.toUpperCase());
				
				stmt.executeUpdate(sql);
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat action save bahagian :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_SEKSYEN;
		}
		
		
		@SuppressWarnings("unchecked")//sql senarai pengguna by bahagian
		public List getSenaraiPengguna(HttpSession session, String ID_SEKSYEN)throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listPengguna = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT DISTINCT U.USER_ID, UPPER(U.USER_NAME) AS USER_NAME, UI.NO_KP, UI.FLAG_AKTIF, " +
									" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UI.FLAG_AKTIF = 2 " +
									" THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS KEAKTIFAN, UI.ID_JAWATAN, " +
									" J.KETERANGAN AS NAMA_JAWATAN, S.NAMA_SEKSYEN "+
									" FROM USERS U, USERS_INTERNAL UI, TBLAUDITTRAIL AT, TBLRUJSEKSYEN S, TBLRUJJAWATAN J " +
									" WHERE U.USER_ID = AT.ID_MASUK AND U.USER_ID = UI.USER_ID " +
									" AND UI.ID_JAWATAN = J.ID_JAWATAN "+
									" AND AT.ID_SEKSYEN = S.ID_SEKSYEN AND AT.ID_SEKSYEN = " + ID_SEKSYEN ;
						
						myLogger.debug("SQL LIST PENGGUNA BAHAGIAN - "+sql.toUpperCase());
						
						 rs = stmt.executeQuery(sql);
						
						 listPengguna = Collections.synchronizedList(new ArrayList());
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
							h.put("NO_KP",checkIsNull(rs.getString("NO_KP")));
							
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							h.put("KEAKTIFAN",checkIsNull(rs.getString("KEAKTIFAN")));
							h.put("ID_JAWATAN",checkIsNull(rs.getString("ID_JAWATAN")));
							
							h.put("NAMA_JAWATAN",checkIsNull(rs.getString("NAMA_JAWATAN")));
							h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYEN")));
							
							listPengguna.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listPengguna;
					
				}
		
		@SuppressWarnings("unchecked")//sql senarai unit by bahagian dan negeri
		public List getSenaraiUnitHQ(HttpSession session, String carianUnitHQ, String ID_SEKSYEN, String ID_NEGERI)throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listUnitHQ = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
							sql = 	" SELECT PEJ.ID_PEJABATJKPTG, " +
									" PEJ.KOD_JKPTG, " +
									" UPPER (PEJ.NAMA_PEJABAT) AS NAMA_UNIT, " +
									" UPPER (PEJ.ALAMAT1) AS ALAMAT1, " +
									" UPPER (PEJ.ALAMAT2) AS ALAMAT2, " +
									" UPPER (PEJ.ALAMAT3) AS ALAMAT3, " +
									" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, " +
									" UPPER (BAN.KETERANGAN) AS BANDAR, " +
									" UPPER (NEG.NAMA_NEGERI) AS NEGERI, " +
									" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX " +
									" FROM TBLRUJPEJABATJKPTG PEJ, TBLRUJBANDAR BAN, TBLRUJNEGERI NEG " +
									" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+) " +
									" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) " +
									//" AND PEJ.ID_NEGERI = 16 " + //hardcode id for HQ putrajaya
									" AND PEJ.ID_SEKSYEN = " + ID_SEKSYEN ;
							
							if (ID_SEKSYEN.equals("2") && !ID_NEGERI.equals("") )
							{
								sql += " AND PEJ.ID_NEGERI = "+ID_NEGERI;	
							}
							
							
							if(!carianUnitHQ.equals("") && carianUnitHQ != null)
							
							{
							sql += " AND (";
							
							sql += " UPPER(PEJ.NAMA_PEJABAT) LIKE UPPER('%"+carianUnitHQ.trim()+"%') ";
							sql += " OR UPPER(KOD_JKPTG) LIKE UPPER('%"+carianUnitHQ.trim()+"%') ";					
							//sql += " OR UPPER(KOD_BARU_SEKSYEN) LIKE UPPER('%"+carianUnitHQ.trim()+"%') ";
							
							sql += ") ";
							
							}
						
							sql+=	" ORDER BY   PEJ.KOD_JKPTG ";
						
						myLogger.debug("SQL LIST UNIT HQ - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listUnitHQ = Collections.synchronizedList(new ArrayList());
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
							h.put("rowCss2",rowCss);
							h.put("BIL",bil);
							h.put("ID_PEJABATJKPTG",checkIsNull(rs.getString("ID_PEJABATJKPTG")));
							h.put("KOD_JKPTG",checkIsNull(rs.getString("KOD_JKPTG")));
							h.put("NAMA_UNIT",checkIsNull(rs.getString("NAMA_UNIT")));
							h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1")));
							h.put("ALAMAT2",checkIsNull(rs.getString("ALAMAT2")));
							h.put("ALAMAT3",checkIsNull(rs.getString("ALAMAT3")));
							h.put("POSKOD",checkIsNull(rs.getString("POSKOD")));
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("ID_BANDAR",checkIsNull(rs.getString("ID_BANDAR")));
							h.put("BANDAR",checkIsNull(rs.getString("BANDAR")));
							h.put("NEGERI",checkIsNull(rs.getString("NEGERI")));
							h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYEN")));
							h.put("NO_TEL",checkIsNull(rs.getString("NO_TEL")));
							h.put("NO_FAX",checkIsNull(rs.getString("NO_FAX")));
							
							listUnitHQ.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listUnitHQ;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListDoc(HttpSession session, String ID_SEKSYEN)throws Exception {
					
			Db db = null; 
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listDoc = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT ID_DOKUMEN, ID_BAHAGIAN, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
									" TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK , ID_MASUK, " +
									" TARIKH_KEMASKINI, ID_KEMASKINI, KETERANGAN, JENISUP_DOKUMEN "+
									" FROM TBLBAHAGIANDOKUMEN "+
									" WHERE ID_BAHAGIAN = "+ ID_SEKSYEN;
						
						myLogger.debug("SQL LIST DOC - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listDoc = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_DOKUMEN",checkIsNull(rs.getString("ID_DOKUMEN")));
							h.put("ID_BAHAGIAN",checkIsNull(rs.getString("ID_BAHAGIAN")));
							h.put("NAMA_DOKUMEN",checkIsNull(rs.getString("NAMA_DOKUMEN")));
							h.put("JENIS_DOKUMEN",checkIsNull(rs.getString("JENIS_DOKUMEN")));
							h.put("DOKUMEN",checkIsNull(rs.getString("DOKUMEN")));
							h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
							h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
							h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
							h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
							h.put("KETERANGAN",checkIsNull(rs.getString("KETERANGAN")));
							h.put("JENISUP_DOKUMEN",checkIsNull(rs.getString("JENISUP_DOKUMEN")));
							
							listDoc.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listDoc;
					
				}
		
		@SuppressWarnings("unchecked")
		public List getSenaraiBahagianHQ(HttpSession session, String carianBahagian)
				throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listBahagian = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT ID_SEKSYEN, KOD_SEKSYEN, " +
									" UPPER (NAMA_SEKSYEN) AS NAMA_SEKSYEN, " +
									" (CASE WHEN FLAG_AKTIF = 'Y' THEN 'AKTIF' " +
									" WHEN FLAG_AKTIF = 'N' THEN 'TIDAK AKTIF' " +
									" ELSE 'AKTIF' END) AS FLAG_AKTIF, " +
									" CATATAN "+	
									" FROM   TBLRUJSEKSYEN "+
									" WHERE ID_SEKSYEN != 0 ";
							
							if(!carianBahagian.equals("") && carianBahagian != null)
							
							{
							sql += " AND (";
							
							sql += " UPPER(KOD_SEKSYEN) LIKE UPPER('%"+carianBahagian.trim()+"%') ";
							sql += " OR UPPER(NAMA_SEKSYEN) LIKE UPPER('%"+carianBahagian.trim()+"%') ";					
							sql += " OR UPPER(KOD_BARU_SEKSYEN) LIKE UPPER('%"+carianBahagian.trim()+"%') ";
							
							sql += ") ";
							
							}
						
							sql+=	" ORDER BY ID_SEKSYEN ";
						
						myLogger.debug("SQL LIST BAHAGIAN - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listBahagian = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYEN")));
							h.put("KOD_SEKSYEN",checkIsNull(rs.getString("KOD_SEKSYEN")));
							h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYEN")));
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							h.put("CATATAN",checkIsNull(rs.getString("CATATAN")));
							
							listBahagian.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listBahagian;
					
				}
		
		@SuppressWarnings("unchecked")
		public Hashtable getDetailsUnitHQ (HttpSession session, String ID_SEKSYEN, String ID_UNIT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_UNIT.equals(""))
				{
					
					sql = 	" SELECT   PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, " +
							" UPPER (PEJ.NAMA_PEJABAT) AS NAMA_UNIT, " +
							" UPPER (PEJ.ALAMAT1) AS ALAMAT1, " +
							" UPPER (PEJ.ALAMAT2) AS ALAMAT2, " +
							" UPPER (PEJ.ALAMAT3) AS ALAMAT3, " +
							" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, " +
							" UPPER (BAN.KETERANGAN) AS BANDAR, " +
							" UPPER (NEG.NAMA_NEGERI) AS NEGERI, " +
							" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX, " +
							" (CASE WHEN FLAG_AKTIF = 1 THEN 'AKTIF' " +
							" WHEN FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
							" ELSE 'AKTIF' END) AS FLAG_AKTIF "+
							" FROM   TBLRUJPEJABATJKPTG PEJ, TBLRUJBANDAR BAN, TBLRUJNEGERI NEG " +
							" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+) " +
							" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) " ;
						//	" AND PEJ.ID_NEGERI = 16 " +
							
					
					if (!ID_SEKSYEN.equals("") && !ID_UNIT.equals("")){	
					sql += " AND PEJ.ID_SEKSYEN = " + ID_SEKSYEN ;
					}
					
					if (!ID_UNIT.equals("")){	
					sql += " AND PEJ.ID_PEJABATJKPTG = "+ID_UNIT ;
					}
					
					rs = stmt.executeQuery(sql);	
					
					myLogger.info(" SQL Details Unit :" + sql.toUpperCase());
									
					while (rs.next()) {
						
						h.put("ID_PEJABATJKPTG",checkIsNull(rs.getString("ID_PEJABATJKPTG")));
						h.put("KOD_JKPTG",checkIsNull(rs.getString("KOD_JKPTG")));
						h.put("NAMA_UNIT",checkIsNull(rs.getString("NAMA_UNIT")));
						h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1")));
						h.put("ALAMAT2",checkIsNull(rs.getString("ALAMAT2")));
						h.put("ALAMAT3",checkIsNull(rs.getString("ALAMAT3")));
						h.put("POSKOD",checkIsNull(rs.getString("POSKOD")));
						h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
						h.put("ID_BANDAR",checkIsNull(rs.getString("ID_BANDAR")));
						h.put("BANDAR",checkIsNull(rs.getString("BANDAR")));
						h.put("NEGERI",checkIsNull(rs.getString("NEGERI")));
						h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYEN")));
						h.put("NO_TEL",checkIsNull(rs.getString("NO_TEL")));
						h.put("NO_FAX",checkIsNull(rs.getString("NO_FAX")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						}
				}
				else
				{
					h.put("ID_PEJABATJKPTG","");
					h.put("KOD_JKPTG","");
					h.put("NAMA_UNIT","");
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("ID_NEGERI","");
					h.put("ID_BANDAR","");
					h.put("BANDAR","");
					h.put("NEGERI","");
					h.put("ID_SEKSYEN","");
					h.put("NO_TEL","");
					h.put("NO_FAX","");
					h.put("FLAG_AKTIF","");
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
		
		@SuppressWarnings("unchecked")
		public Hashtable getDetailsBahagianHQ (HttpSession session, String ID_SEKSYEN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_SEKSYEN.equals(""))
				{
					
					sql = 	" SELECT S.ID_SEKSYEN, S.KOD_SEKSYEN, UPPER (S.NAMA_SEKSYEN) AS NAMA_SEKSYEN, " +
							" (CASE WHEN S.FLAG_AKTIF = 'Y' THEN 'AKTIF' WHEN S.FLAG_AKTIF = 'N' THEN 'TIDAK AKTIF' " +
							" ELSE 'AKTIF' END) AS FLAG_AKTIF, S.CATATAN, S.ID_KEMASKINI, U.USER_NAME, " +
							" S.ALAMAT_1, S.ALAMAT_2, S.ALAMAT_3, S.POSKOD, S.ID_NEGERI, N.NAMA_NEGERI, " +
							" S.NO_TEL , S.NO_FAKS , S.EMEL ,"+
							" TO_CHAR(S.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI " +
							" FROM TBLRUJSEKSYEN S, USERS U, TBLRUJNEGERI N " +
							" WHERE   S.ID_SEKSYEN = "+ID_SEKSYEN+ 
							" AND S.ID_KEMASKINI = U.USER_ID(+) "+
							" AND S.ID_NEGERI = N.ID_NEGERI(+) ";
					
					rs = stmt.executeQuery(sql);				
									
					while (rs.next()) {
						
						h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYEN")));
						h.put("KOD_SEKSYEN",checkIsNull(rs.getString("KOD_SEKSYEN")));
						h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYEN")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						h.put("CATATAN",checkIsNull(rs.getString("CATATAN")));
						h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
						h.put("USER_NAME",checkIsNull(rs.getString("USER_NAME")));
						h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
						
						h.put("ALAMAT_1",checkIsNull(rs.getString("ALAMAT_1")));
						h.put("ALAMAT_2",checkIsNull(rs.getString("ALAMAT_2")));
						h.put("ALAMAT_3",checkIsNull(rs.getString("ALAMAT_3")));
						h.put("POSKOD",checkIsNull(rs.getString("POSKOD")));
						h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
						h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
						h.put("NO_TEL",checkIsNull(rs.getString("NO_TEL")));
						
						h.put("NO_FAKS",checkIsNull(rs.getString("NO_FAKS")));
						h.put("EMEL",checkIsNull(rs.getString("EMEL")));
						
						
						}
				}
				else
				{
					h.put("ID_SEKSYEN","");
					h.put("KOD_SEKSYEN","");
					h.put("NAMA_SEKSYEN","");
					h.put("FLAG_AKTIF","");
					h.put("CATATAN","");
					
					h.put("ALAMAT_1","");
					h.put("ALAMAT_2","");
					h.put("ALAMAT_3","");
					h.put("POSKOD","");
					h.put("ID_NEGERI","");
					h.put("NAMA_NEGERI", "");
					h.put("NO_TEL","");
					
					h.put("NO_FAKS","");
					h.put("EMEL","");
				}
				
				myLogger.info(" SQL Details Bahagian :" + sql.toUpperCase());
				
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
		public List listTableRujukan(HttpSession session, String tableRujukan, String ID_NEGERI, String ID_DAERAH )throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listTableRujukan = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				if(tableRujukan.equals("TBLRUJJAWATAN"))
				{ 
					sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJAWATAN WHERE KOD_JAWATAN != '00' ORDER BY KOD_JAWATAN";
				}
				
				else if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
				{
					sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
				}
				else if(tableRujukan.equals("TBLRUJBANGSA"))
				{
					sql = " SELECT ID_BANGSA AS ID, KOD_BANGSA AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANGSA WHERE ID_BANGSA IN (1,2,3,7) ORDER BY ID_BANGSA";
				}
				else if(tableRujukan.equals("TBLRUJSEKSYEN"))
				{
					sql = " SELECT ID_SEKSYEN AS ID, KOD_SEKSYEN AS KOD, UPPER(NAMA_SEKSYEN) AS KETERANGAN FROM TBLRUJSEKSYEN ORDER BY ID_SEKSYEN";
				}
				else if(tableRujukan.equals("TBLRUJAGAMA"))
				{
					sql = " SELECT ID_AGAMA AS ID, KOD_AGAMA AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJAGAMA ORDER BY KOD_AGAMA";
				}
				else if(tableRujukan.equals("TBLRUJKEMENTERIAN"))
				{
					sql = " SELECT ID_KEMENTERIAN AS ID, KOD_KEMENTERIAN AS KOD, UPPER(NAMA_KEMENTERIAN) AS KETERANGAN  FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN IS NOT NULL ORDER BY KOD_KEMENTERIAN ";
				}
				
				else if(tableRujukan.equals("TBLRUJNEGERI"))
				{
					sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
					sql += " WHERE ID_NEGERI IS NOT NULL ORDER BY KETERANGAN";
				
				}
				
				else if(tableRujukan.equals("TBLRUJBANDAR"))
				{
					sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR";
					sql += " WHERE ID_BANDAR IS NOT NULL ORDER BY KETERANGAN, KOD_BANDAR ";
				
				}
				
				else if(tableRujukan.equals("TBLRUJDAERAH"))
				{
					sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN FROM TBLRUJDAERAH ORDER BY NAMA_DAERAH, KOD_DAERAH ";
				}
				
				else if(tableRujukan.equals("TBLRUJJENISPEJABAT"))
				{
					sql = "  SELECT ID_JENISPEJABAT AS ID, KOD_JENIS_PEJABAT AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJENISPEJABAT ORDER BY KETERANGAN ";
				}
				
				else if(tableRujukan.equals("TBLRUJDAERAHBYNEGERI"))
				{
					sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN " +
							" FROM TBLRUJDAERAH " +
							" WHERE ID_NEGERI = "+ID_NEGERI+
							" ORDER BY NAMA_DAERAH, KOD_DAERAH ";
				
				}
				else if(tableRujukan.equals("TBLRUJBANDARBYNEGERI"))
				{
					sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR";
					sql += " WHERE ID_BANDAR IS NOT NULL";
					sql += " AND ID_NEGERI = "+ID_NEGERI+ " AND ID_DAERAH = "+ID_DAERAH;
					sql += " ORDER BY KETERANGAN, KOD_BANDAR ";
				
				}
				else if(tableRujukan.equals("TBLRUJJENISPEJABATBYID"))
				{
					sql = " SELECT ID_JENISPEJABAT AS ID, KOD_JENIS_PEJABAT AS KOD, UPPER(KETERANGAN) AS KETERANGAN " +
							" FROM TBLRUJJENISPEJABAT" +
							" WHERE ID_JENISPEJABAT = "+ ID_NEGERI+
							" ORDER BY KETERANGAN";
				}

			//	myLog.info(" V3: SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
				rs = stmt.executeQuery(sql);
				listTableRujukan = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					h.put("BIL",bil);
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
					
					listTableRujukan.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listTableRujukan;

		}
		
		public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
			try {
				//myLog.info("action : "+action);
				setupPageDefaultPut();
				String scrolPosition = getParam("scrolPosition"+command);
				//myLog.info("scrolPosition pejurusan: "+scrolPosition);
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
				//	myLog.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
					itemsPerPage = getParam("itemsPerPage"+command) == "" ? 10
							: getParamAsInteger("itemsPerPage"+command);
				} else {
					itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
				}
				
				//myLog.info(" itemsPerPage *** : "+itemsPerPage);
				
				if (("getNext").equals(action)) {
					page++;
				} else if (("getPrevious").equals(action)) {
					page--;
				} else if (("getPage").equals(action)) {
					page = getParamAsInteger("value");
				} else if (("doChangeItemPerPage").equals(action)) {
					itemsPerPage = getParamAsInteger("itemsPerPage"+command);
				}
				//myLog.info(" **** itemsPerPage : "+itemsPerPage+"  list :"+list);
				
				Paging2 paging = new Paging2(session, list, itemsPerPage);

				if (page > paging.getTotalPages())
					page = 1;
				//myLog.info(" namaList : "+namaList+" #### page : "+page+"  list :"+paging.getPage(page));
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
		
		public void deleteBahagian(HttpSession session,String ID_SEKSYEN) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				if (!ID_SEKSYEN.equals("")){
					r.add("ID_SEKSYEN", ID_SEKSYEN);
					sql = r.getSQLDelete("TBLRUJSEKSYEN");
					AuditTrail.logActivity(this,session,"DEL","SEKSYEN ["+ID_SEKSYEN+"] Deleted");
				}
				
				stmt.executeUpdate(sql);
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Delete SEKSYEN :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
		}
		
		private void uploadFiles(String ID_SEKSYEN) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		    if (isMultipart) {
			    List items = upload.parseRequest(this.request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			      FileItem item = (FileItem)itr.next();
			      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	 saveData(item, ID_SEKSYEN);
			      }
			    }
		    }
		  }
	
	private void saveData(FileItem item, String ID_SEKSYEN) throws Exception {
  		Db db = null;
  		
  		HttpSession session = this.request.getSession();
  		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
  		
        try {
        	db = new Db();
        	long idLampiran = DB.getNextID("TBLBAHAGIANDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("insert into TBLBAHAGIANDOKUMEN " +
        			"(ID_DOKUMEN, ID_BAHAGIAN, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
        			" JENISUP_DOKUMEN, KETERANGAN, TARIKH_MASUK, ID_MASUK) " +
        			"values(?,?,?,?,?,?,?,SYSDATE,?)");
        	ps.setLong(1,idLampiran);
        	ps.setString(2, ID_SEKSYEN);
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.setString(6,getParam("noRujukan"));
        	ps.setString(7,getParam("keteranganDokumen"));
        	ps.setString(8,USER_ID_SYSTEM);
        	
        	ps.executeUpdate();
        	
        	
            con.commit();
	    } finally {
		      if (db != null) db.close();
	    }
  }
	
	
	@SuppressWarnings("unchecked")//sql senarai pengguna by bahagian
	public List getStatsJawatan(HttpSession session, String ID_SEKSYEN)throws Exception {
				
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listPengguna = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
						sql = 	" SELECT COUNT(USER_ID) AS JUMLAH_PENGGUNA, NAMA_JAWATAN FROM ( "+
								" SELECT DISTINCT U.USER_ID, UPPER(U.USER_NAME) AS USER_NAME, UI.NO_KP, UI.FLAG_AKTIF, " +
								" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UI.FLAG_AKTIF = 2 " +
								" THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS KEAKTIFAN, UI.ID_JAWATAN, " +
								" J.KETERANGAN AS NAMA_JAWATAN, S.NAMA_SEKSYEN "+
								" FROM USERS U, USERS_INTERNAL UI, TBLAUDITTRAIL AT, TBLRUJSEKSYEN S, TBLRUJJAWATAN J " +
								" WHERE U.USER_ID = AT.ID_MASUK AND U.USER_ID = UI.USER_ID " +
								" AND UI.ID_JAWATAN = J.ID_JAWATAN "+
								" AND AT.ID_SEKSYEN = S.ID_SEKSYEN AND AT.ID_SEKSYEN = " + ID_SEKSYEN +
								" ) GROUP BY NAMA_JAWATAN ORDER BY NAMA_JAWATAN ASC ";
					
					myLogger.debug("SQL COUNT JAWATAN PENGGUNA BAHAGIAN - "+sql.toUpperCase());
					
					 rs = stmt.executeQuery(sql);
					
					 listPengguna = Collections.synchronizedList(new ArrayList());
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
						
						h.put("JUMLAH_PENGGUNA",checkIsNull(rs.getString("JUMLAH_PENGGUNA")));
						h.put("NAMA_JAWATAN",checkIsNull(rs.getString("NAMA_JAWATAN")));
						
						listPengguna.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listPengguna;
				
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
