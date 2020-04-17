package ekptg.view.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

	public class FrmPengurusanPerkhidmatan extends AjaxBasedModule {
		
		static Logger myLogger = Logger.getLogger(ekptg.view.admin.FrmPengurusanPerkhidmatan.class);
		String skrin_name = "app/admin/PerkhidmatanJawatan/index.jsp";
		
		@Override
		public String doTemplate2() throws Exception {	
			
			Hashtable viewGred = null;
			Hashtable viewKlasifikasi = null;
			Hashtable viewKhidmat = null;
			Hashtable viewSkimKhidmat= null;
			

			List list_TBLRUJKLASIFIKASI = null;
			List list_TBLRUJKHIDMAT = null;
			List list_TBLRUJGRED = null;
			List listGred = null;
			List listGredByIdPejabat = null;
			List listDataGred =null;
			List listDataKlasifikasi =null;
			List listDataKhidmat =null;
			List listDataSkimKhidmat = null;
			
			this.context.put("carianUmum", "");
			
			String carianUmum = "";
			//this.context.put("carianUmum", carianUmum);
			
			String cetakReportGred = "";
			this.context.put("cetakReportGred", cetakReportGred);
			
			String cetakReportKlasifikasi = "";
			this.context.put("cetakReportKlasifikasi", cetakReportKlasifikasi);
			
			String cetakReportKhidmat= "";
			this.context.put("cetakReportKhidmat", cetakReportKhidmat);
			
			String cetakReportSkimKhidmat= "";
			this.context.put("cetakReportSkimKhidmat", cetakReportSkimKhidmat);
			
			HttpSession session = this.request.getSession();
			String command = getParam("command");
			
			/*String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
			String USER_LOGIN_ROLE =  (String) session.getAttribute("_portal_role");
			
			myLogger.info(" USER_LOGIN_SYSTEM :" + USER_LOGIN_SYSTEM);
			myLogger.info(" USER_LOGIN_ROLE :" + USER_LOGIN_ROLE);*/
		
			myLogger.info("PK command --- "+command );
			
			if(command.equals("batalCarianUtama"))
			{
				skrin_name = "app/admin/PerkhidmatanJawatan/index.jsp";
			}
			
			else if(command.equals("carianUmum"))
			{
				String action = getParam("action");
				
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				//myLogger.info("carianUmum -- "+carianUmum);
				
				listDataKlasifikasi = listDataKlasifikasi(session, "",carianUmum);
				setupPageList(session, action, listDataKlasifikasi, "listDataKlasifikasi",command,"div_Klasifikasi");
				//this.context.put("PrintlistDataKlasifikasi",listDataKlasifikasi);
				
				listDataGred = listDataGred(session, "", carianUmum);
				setupPageList(session, action, listDataGred, "listDataGred",command,"div_Gred");
				//this.context.put("PrintlistDataGred",listDataGred);
				
				listDataKhidmat = listDataKhidmat(session, "",carianUmum);
				setupPageList(session, action, listDataKhidmat, "listDataKhidmat",command,"div_KumpKhidmat");
				//this.context.put("PrintlistDataKhidmat",listDataKhidmat);
				
				listDataSkimKhidmat = listDataSkimKhidmat(session, "",carianUmum);
				//this.context.put("listDataSkimKhidmat",listDataSkimKhidmat);
				setupPageList(session, action, listDataSkimKhidmat, "listDataSkimKhidmat",command,"div_SkimKhidmat");
				
				
				
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiUtama.jsp";
				
			}
		
			else if(command.equals("link_gred"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/CTGred.jsp";
			}
			
			else if(command.equals("tutuplink"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewLinkGred.jsp";
			}
			
			else if(command.equals("close_senaraiUtama"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_senaraiUtama.jsp";
			}

			else if(command.equals("showSenaraiGred"))
			{
				myLogger.info("masuk  cari ---- : " );
				
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				myLogger.info("carianUmum -- "+carianUmum);

				String CarianGred = getParam("CarianGred");
				this.context.put("CarianGred", CarianGred);
				
				cetakReportGred = getParam("cetakReportGred");
				this.context.put("cetakReportGred", cetakReportGred);
				
				
				this.context.put("SuccessMesejDeleteGred", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				//String GREDID = getParam("GREDID");
				String ID_GRED = getParam("ID_GRED");
				myLogger.info("ID_GRED cari !!!!! ---- : "+ID_GRED );
				if(FLAG_DELETE.equals("Y"))
				{
					deleteGred(session,ID_GRED);
					this.context.put("SuccessMesejDeleteGred", "Maklumat Gred Berjaya Dihapus");
				}
	
				String action = getParam("action");
				
				if(getParam("cetakReportGred").equals("Y"))
				{

				listDataGred = listDataGred(session, CarianGred, carianUmum);
				setupPageList(session, action, listDataGred, "listDataGred",command,"div_Gred");
				this.context.put("PrintlistDataGred",listDataGred);
				}
				
				if(getParam("FlagCetak").equals("Y"))
				{
					myLogger.info("FlagCetak 1 masuk :-- "+getParam("FlagCetak"));
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiGred_Print.jsp";
				}
				else
				{
					listDataGred = listDataGred(session, CarianGred, carianUmum);
					setupPageList(session, action, listDataGred, "listDataGred",command,"div_Gred");
					
					myLogger.info("FlagCetak 2 masuk :-- "+getParam("FlagCetak"));
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiGred.jsp";
				}
				 myLogger.info("FlagCetak -- "+getParam("FlagCetak"));
			}
			
			//viewGred
			else if(command.equals("viewGred"))
			{
				String ID_GRED = getParam("ID_GRED");
				
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				viewGred = viewDataGred(session,ID_GRED);			
				this.context.put("viewGred", viewGred);

				skrin_name = "app/admin/PerkhidmatanJawatan/viewGred.jsp";
			}	
	
			//papar skrin addGred
			else if(command.equals("addGred")){
				
				String ID_GRED = getParam("ID_GRED");
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				
				viewGred = viewDataGred(session,ID_GRED);			
				this.context.put("viewGred", viewGred);
				
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				skrin_name = "app/admin/PerkhidmatanJawatan/AddGred.jsp";
			}	
			else if(command.equals("checkNamaGred")){
				
				String ID_GRED = getParam("ID_GRED");
				
				String NAMA_GRED = getParam("NAMA_GRED_"+ID_GRED);
				//myLog.info("KOD_JENIS_PEJABAT ---- : "+KOD_JENIS_PEJABAT );
				
				boolean checkGred = checkNamaKlasifikasi(session, NAMA_GRED,2);

				String mesej = "";
					if(checkGred==false)
					{
						mesej = "<font color='red' class='blink' >Klasifikasi Telah Wujud!Sila Pilih Kod Lain.</font>";
					}else {
						mesej = "<font color='black' class='blink' >Klasifikasi Belum Wujud!</font>";	
					}
					
					this.context.put("mesej", mesej);
					this.context.put("checkKod", checkGred);
					
					skrin_name = "app/admin/Utilities/checkKodJenisPejabat.jsp";
			}
			
			//Insert Gred	
			else if(command.equals("insertGred"))
			{
				
				String ID_GRED = getParam("ID_GRED");
				
				System.out.println("ID_GRED insert? -- "+ID_GRED);
				ID_GRED = insertDataGred(session, ID_GRED);
				System.out.println("ID_GRED after insert? -- "+ID_GRED);

				viewGred = viewDataGred(session, ID_GRED);
				this.context.put("viewGred", viewGred);
				
				//String CarianGred = getParam("CarianGred");
				
				listDataGred = listDataGred(session, "", "");
				
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiGred.jsp";
				//}	
			}	
			
			//Update Gred	
			else if(command.equals("updateGred"))
			{
				
				String ID_GRED = getParam("ID_GRED");
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");

				System.out.println("ID_GRED UPDATE? -- "+ID_GRED);
				ID_GRED = updateDataGred(session, ID_GRED);
				System.out.println("ID_GRED after UPDATE? -- "+ID_GRED);
				
				viewGred = viewDataGred(session, ID_GRED);
				this.context.put("viewGred", viewGred);
				
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				//this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				skrin_name = "app/admin/PerkhidmatanJawatan/viewGred.jsp";

			}
			//close_AddGred
			else if(command.equals("close_AddGred")){
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewAddGred.jsp";
			}
			
			//KLASIFIKASI
			//++++++KLASIFIKASI++++//
			
			else if(command.equals("tutuplinkKlasifikasi"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewLinkKlasifikasi.jsp";
			}
			
			else if(command.equals("close_senaraiUtamaKlasifikasi"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_senaraiUtamaKlasifikasi.jsp";
			}
			
			else if(command.equals("link_klasifikasi"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/CTKlasifikasi.jsp";
			}
			
			else if(command.equals("showSenaraiKlasifikasi"))
			{
				
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				myLogger.info("carianUmum -- "+carianUmum);
				
				this.context.put("SuccessMesejDeleteKlasifikasi", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				
				String carianKlasifikasi = getParam("carianKlasifikasi");
				this.context.put("carianKlasifikasi", carianKlasifikasi);
				
				cetakReportKlasifikasi = getParam("cetakReportKlasifikasi");
				this.context.put("cetakReportKlasifikasi", cetakReportKlasifikasi);
				
				if(FLAG_DELETE.equals("Y"))
				{
					String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
					deleteKlasifikasi(session,ID_KLASIFIKASI);
					this.context.put("SuccessMesejDeleteKlasifikasi", "Maklumat Pejabat Berjaya Dihapus");
				}
				
				String action = getParam("action");
				
				if( getParam("cetakReportKlasifikasi").equals("Y"))
				{
				
				listDataKlasifikasi = listDataKlasifikasi(session, carianKlasifikasi,carianUmum);
				setupPageList(session, action, listDataKlasifikasi, "listDataKlasifikasi",command,"div_Klasifikasi");
				this.context.put("PrintlistDataKlasifikasi",listDataKlasifikasi);
				}
				if(!carianKlasifikasi.equals("") && getParam("FlagCetak").equals("Y"))
				{
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKlasifikasi_Print.jsp";
				}	
				else
				{
					listDataKlasifikasi = listDataKlasifikasi(session, carianKlasifikasi,carianUmum);
					setupPageList(session, action, listDataKlasifikasi, "listDataKlasifikasi",command,"div_Klasifikasi");
					//this.context.put("PrintlistDataKlasifikasi",listDataKlasifikasi);
					
					
					myLogger.info("FlagCetak 2 masuk :-- "+getParam("FlagCetak"));
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKlasifikasi.jsp";
				}
				 
			}
			//viewKlasifikasi
			else if(command.equals("viewKlasifikasi"))
			{
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				
				viewKlasifikasi = viewDataKlasifikasi(session,ID_KLASIFIKASI);			
				this.context.put("viewKlasifikasi", viewKlasifikasi);

				skrin_name = "app/admin/PerkhidmatanJawatan/viewKlasifikasi.jsp";
			}	
			
			//papar skrin addKlasifikasi
			else if(command.equals("addKlasifikasi")){
				
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				
				viewKlasifikasi = viewDataKlasifikasi(session,ID_KLASIFIKASI);			
				this.context.put("viewKlasifikasi", viewKlasifikasi);		
				
				skrin_name = "app/admin/PerkhidmatanJawatan/AddKlasifikasi.jsp";
			}	
			else if(command.equals("checkNamaKlasifikasi")){
				
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				
				String NAMA_KLASIFIKASI = getParam("NAMA_KLASIFIKASI_"+ID_KLASIFIKASI);
				//myLog.info("KOD_JENIS_PEJABAT ---- : "+KOD_JENIS_PEJABAT );
				
				boolean checkKod = checkNamaKlasifikasi(session, NAMA_KLASIFIKASI,1);

				String mesej = "";
					if(checkKod==false)
					{
						mesej = "<font color='red' class='blink' >Klasifikasi Telah Wujud!Sila Pilih Kod Lain.</font>";
					}else {
						mesej = "<font color='black' class='blink' >Klasifikasi Belum Wujud!</font>";	
					}
					
					this.context.put("mesej", mesej);
					this.context.put("checkKod", checkKod);
					
					skrin_name = "app/admin/Utilities/checkKodJenisPejabat.jsp";
			}
			
			//insert Klasifikasi
			
			else if(command.equals("insertKlasifikasi"))
			{
				
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
			
				System.out.println("ID_KLASIFIKASI insert? -- "+ID_KLASIFIKASI);
				ID_KLASIFIKASI = insertDataKlasifikasi(session, ID_KLASIFIKASI);
				System.out.println("ID_KLASIFIKASI after insert? -- "+ID_KLASIFIKASI);

				/*viewKlasifikasi = viewDataKlasifikasi(session, ID_KLASIFIKASI);
				this.context.put("viewKlasifikasi", viewKlasifikasi);*/
				
				String carianKlasifikasi = getParam("carianKlasifikasi");
				String action = getParam("action");
				
				listDataKlasifikasi = listDataKlasifikasi(session, carianKlasifikasi,carianUmum);
				setupPageList(session, action, listDataKlasifikasi, "listDataKlasifikasi",command,"div_senaraiUtamaKlasifikasi");
				
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKlasifikasi.jsp";
			}	
			
			else if(command.equals("updateKlasifikasi"))
			{
				
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
			
				System.out.println("ID_KLASIFIKASI insert? -- "+ID_KLASIFIKASI);
				ID_KLASIFIKASI = updateDataKlasifikasi(session, ID_KLASIFIKASI);
				System.out.println("ID_KLASIFIKASI after insert? -- "+ID_KLASIFIKASI);
				
				viewKlasifikasi = viewDataKlasifikasi(session, ID_KLASIFIKASI);
				this.context.put("viewKlasifikasi", viewKlasifikasi);
				
				//list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				//this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKlasifikasi.jsp";
			}	
			
			//close AddKlasifikasi
			else if(command.equals("close_AddKlasifikasi")){
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewAddKlasifikasi.jsp";
			}
			
			//SKIM PERKHIDMATAN//
			//++SKIM PERKHIDMATAN++//
			
			else if(command.equals("tutuplinkSkimKhidmat"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewLinkSkimKhidmat.jsp";
			}
			
			else if(command.equals("close_senaraiUtamaSkimKhidmat"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_senaraiUtamaSkimKhidmat.jsp";
			}
			
			else if(command.equals("link_SkimKhidmat"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/CTSkimKhidmat.jsp";
			}
			
			else if(command.equals("showSenaraiSkimKhidmat"))
			{
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
			//	myLogger.info("carianUmum -- "+carianUmum);

				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				String ID_JAWATAN = getParam("ID_JAWATAN");
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				String ID_GRED = getParam("ID_GRED");
				
				cetakReportSkimKhidmat = getParam("cetakReportSkimKhidmat");
				this.context.put("cetakReportSkimKhidmat", cetakReportSkimKhidmat);
				
				String carianSkimKhidmat = getParam("carianSkimKhidmat");
				this.context.put("carianSkimKhidmat", carianSkimKhidmat);
				
				this.context.put("SuccessMesejDeleteSkimKhidmat", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				list_TBLRUJKHIDMAT = list_TBLRUJKHIDMAT(session,ID_KHIDMAT);
				this.context.put("list_TBLRUJKHIDMAT",list_TBLRUJKHIDMAT);
				
				list_TBLRUJGRED = list_TBLRUJGRED(session,ID_GRED);
				this.context.put("list_TBLRUJGRED",list_TBLRUJGRED);
			
				if(FLAG_DELETE.equals("Y"))
				{
				
					deleteSkimKhidmat(session,ID_JAWATAN);
					this.context.put("SuccessMesejDeleteSkimKhidmat", "Maklumat Skim Khidmat Berjaya Dihapus");
				}
				
				String action = getParam("action");
				if(getParam("cetakReportSkimKhidmat").equals("Y"))
				{

				listDataSkimKhidmat = listDataSkimKhidmat(session, carianSkimKhidmat, carianUmum);
				this.context.put("listDataSkimKhidmat",listDataSkimKhidmat);
				setupPageList(session, action, listDataSkimKhidmat, "listDataSkimKhidmat",command,"div_SkimKhidmat");
				this.context.put("PrintlistDataSkimKhidmat",listDataSkimKhidmat);
				}
				if(getParam("FlagCetak").equals("Y"))
				{
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiSkimKhidmat_Print.jsp";
				}
				else
				{
					listDataSkimKhidmat = listDataSkimKhidmat(session, carianSkimKhidmat, carianUmum);
					setupPageList(session, action, listDataSkimKhidmat, "listDataSkimKhidmat",command,"div_SkimKhidmat");
					
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiSkimKhidmat.jsp";
				}

			}	
		
			//viewSkimKhidmat
			else if(command.equals("viewSkimKhidmat"))
			{
				String ID_JAWATAN = getParam("ID_JAWATAN");
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				String ID_GRED = getParam("ID_GRED");
				
				viewSkimKhidmat = viewDataSkimKhidmat(session,ID_JAWATAN);			
				this.context.put("viewSkimKhidmat", viewSkimKhidmat);
				
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				list_TBLRUJKHIDMAT = list_TBLRUJKHIDMAT(session,ID_KHIDMAT);
				this.context.put("list_TBLRUJKHIDMAT",list_TBLRUJKHIDMAT);
				
				list_TBLRUJGRED = list_TBLRUJGRED(session,ID_GRED);
				this.context.put("list_TBLRUJGRED",list_TBLRUJGRED);
			
				listGred = listGred(session, ID_GRED, ID_JAWATAN);
				this.context.put("listGred", listGred);	

				skrin_name = "app/admin/PerkhidmatanJawatan/viewSkimKhidmat.jsp";
			}	
			
			//papar skrin addSkimKhidmat
			else if(command.equals("addSkimKhidmat")){
				
				String ID_JAWATAN = getParam("ID_JAWATAN");
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				String ID_GRED = getParam("ID_GRED");
				
				
				viewSkimKhidmat = viewDataSkimKhidmat(session,ID_JAWATAN);			
				this.context.put("viewSkimKhidmat", viewSkimKhidmat);		
				
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				list_TBLRUJKHIDMAT = list_TBLRUJKHIDMAT(session,ID_KHIDMAT);
				this.context.put("list_TBLRUJKHIDMAT",list_TBLRUJKHIDMAT);
				
				list_TBLRUJGRED = list_TBLRUJGRED(session,ID_GRED);
				this.context.put("list_TBLRUJGRED",list_TBLRUJGRED);
			
				skrin_name = "app/admin/PerkhidmatanJawatan/AddSkimKhidmat.jsp";
			}	
			
			//insert Skim
			
			else if(command.equals("insertSkimKhidmat"))
			{
				
				String ID_JAWATAN = getParam("ID_JAWATAN");
			
				System.out.println("ID_JAWATAN insert? -- "+ID_JAWATAN);
				ID_JAWATAN = insertDataSkimKhidmat(session, ID_JAWATAN);
				System.out.println("ID_JAWATAN after insert? -- "+ID_JAWATAN);

				viewSkimKhidmat = viewDataSkimKhidmat(session, ID_JAWATAN);
				this.context.put("viewSkimKhidmat", viewSkimKhidmat);
				
				//list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				//this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);
				
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiSkimKhidmat.jsp";
			}	
			//updateSkimKhidmat
			else if(command.equals("updateSkimKhidmat"))
			{
				
				String ID_JAWATAN = getParam("ID_JAWATAN");
				String ID_GRED = getParam("ID_GRED");
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				
			
				System.out.println("ID_KHIDMAT insert? -- "+ID_JAWATAN);
				ID_JAWATAN = updateDataSkimKhidmat(session, ID_JAWATAN);
				System.out.println("ID_KLASIFIKASI after update? -- "+ID_JAWATAN);
				
				viewSkimKhidmat = viewDataSkimKhidmat(session, ID_JAWATAN);
				this.context.put("viewSkimKhidmat", viewSkimKhidmat);
				
				list_TBLRUJKLASIFIKASI = list_TBLRUJKLASIFIKASI(session,ID_KLASIFIKASI);
				this.context.put("list_TBLRUJKLASIFIKASI",list_TBLRUJKLASIFIKASI);

				skrin_name = "app/admin/PerkhidmatanJawatan/viewSkimKhidmat.jsp";
			}	
			
			//close AddSkimKhidmat
			else if(command.equals("close_AddSkimKhidmat")){
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewAddSkimKhidmat.jsp";
			}
			
			//showDisplayGred
			else if(command.equals("showDisplayGred"))
			{
				String ID_JAWATAN = getParam("ID_JAWATAN");
				this.context.put("ID_JAWATAN", ID_JAWATAN);
				
				String ID_GRED = getParam("ID_GRED");
				this.context.put("ID_GRED", ID_GRED);
			
				
				viewSkimKhidmat = viewDataSkimKhidmat(session, ID_JAWATAN);
				this.context.put("viewSkimKhidmat", viewSkimKhidmat);
				
				
				myLogger.info("ID_JAWATAN cari ---- : "+ID_JAWATAN );
				
				listGred = listGred(session, ID_GRED, ID_JAWATAN);
				this.context.put("listGred", listGred);	
				
				myLogger.info("listGred cari ---- : "+listGred );
				
				skrin_name = "app/admin/PerkhidmatanJawatan/showDisplayGred.jsp";
			}
			//saveGred
			else if(command.equals("saveGred"))
			{
				String ID_JAWATAN = getParam("ID_JAWATAN");
				this.context.put("ID_JAWATAN", ID_JAWATAN);
				myLogger.info("ID_JAWATAN -- "+ ID_JAWATAN);
				
				String ID_GRED = getParam("ID_GRED");
				myLogger.info("ID_GRED -- "+ ID_GRED);
				
				String ID_GREDJAWATAN = getParam("ID_GREDJAWATAN");
				myLogger.info("ID_GREDJAWATAN -- "+ ID_GREDJAWATAN);
				
				String[] CHECKLIST_ = request.getParameterValues("CHECKLIST_"+ID_JAWATAN);
				
				int total_gred_update = 0;
				total_gred_update += saveGredbyPejabat(session, ID_JAWATAN, ID_GRED,ID_GREDJAWATAN, CHECKLIST_);
				myLogger.info("ID_GRED -- "+ ID_GRED);
				listGred = listGred(session, ID_GRED, ID_JAWATAN);
				this.context.put("listGred", listGred);	
				
				skrin_name = "app/admin/PerkhidmatanJawatan/showDisplayGred.jsp";
			
			}		
			
			//TAMBAH GRED//
			else if(command.equals("editAddGred"))
			{
				String ID_GRED = getParam("ID_GRED");
				this.context.put("ID_GRED", ID_GRED);
				
				String ID_JAWATAN = getParam("ID_JAWATAN");
				this.context.put("ID_JAWATAN", ID_JAWATAN);
				
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI");
				this.context.put("ID_KLASIFIKASI", ID_KLASIFIKASI);
				
				viewSkimKhidmat = viewDataSkimKhidmat(session, ID_JAWATAN);
				this.context.put("viewSkimKhidmat", viewSkimKhidmat);

				listGredByIdPejabat = listGredByIdPejabat(session,ID_GRED, ID_JAWATAN, ID_KLASIFIKASI);
				this.context.put("listGredByIdPejabat", listGredByIdPejabat);		
			
				skrin_name = "app/admin/PerkhidmatanJawatan/editAddGred.jsp";
			}
			
			//KUMPULAN PERKHIDMATAN
			//++++++KUMPULAN PERKHIDMATAN++++//
			
			else if(command.equals("tutuplinkKhidmat"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewLinkKhidmat.jsp";
			}
			
			else if(command.equals("close_senaraiUtamaKhidmat"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_senaraiUtamaKhidmat.jsp";
			}
			
			else if(command.equals("link_Khidmat"))
			{
				
				skrin_name = "app/admin/PerkhidmatanJawatan/CTKhidmat.jsp";
			}
			
			else if(command.equals("showSenaraiKhidmat"))
			{
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				myLogger.info("carianUmum -- "+carianUmum);

				
				String carianKhidmat = 	getParam("carianKhidmat");
				this.context.put("carianKhidmat", carianKhidmat);
				
				cetakReportKhidmat = getParam("cetakReportKhidmat");
				this.context.put("cetakReportKhidmat", cetakReportKhidmat);
				
				this.context.put("SuccessMesejDeleteKhidmat", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				if(FLAG_DELETE.equals("Y"))
				{
				deleteKhidmat(session,ID_KHIDMAT);
				this.context.put("SuccessMesejDeleteKhidmat", "Maklumat Khidmat Berjaya Dihapus");
				}

				String action = getParam("action");
				
				if(getParam("cetakReportKhidmat").equals("Y"))
				{
				listDataKhidmat = listDataKhidmat(session, carianKhidmat,carianUmum);
				setupPageList(session, action, listDataKhidmat, "listDataKhidmat",command,"div_KumpKhidmat");
				this.context.put("PrintlistDataKhidmat",listDataKhidmat);
				}
				
				if(getParam("FlagCetak").equals("Y"))
				{
					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKhidmat_Print.jsp";
				}
				else
				{
					listDataKhidmat = listDataKhidmat(session, carianKhidmat,carianUmum);
					setupPageList(session, action, listDataKhidmat, "listDataKhidmat",command,"div_KumpKhidmat");

					skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKhidmat.jsp";	
				}	
			}
		
			//viewKhidmat
			else if(command.equals("viewKhidmat"))
			{
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				
				viewKhidmat = viewDataKhidmat(session,ID_KHIDMAT);			
				this.context.put("viewKhidmat", viewKhidmat);

				skrin_name = "app/admin/PerkhidmatanJawatan/viewKhidmat.jsp";
			}	
			
			//papar skrin addKhidmat
			else if(command.equals("addKhidmat")){
				
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				
				viewKhidmat = viewDataKhidmat(session,ID_KHIDMAT);			
				this.context.put("viewKhidmat", viewKhidmat);	
				
				list_TBLRUJGRED = list_TBLRUJGRED(session,"");
				this.context.put("list_TBLRUJGRED",list_TBLRUJGRED);
				
				skrin_name = "app/admin/PerkhidmatanJawatan/AddKhidmat.jsp";
			}	
			
			//insert Khidmat
			
			else if(command.equals("insertKhidmat"))
			{
				
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
				
				if (ID_KHIDMAT.equals("")){
			
				System.out.println("ID_KHIDMAT insert? -- "+ID_KHIDMAT);
				ID_KHIDMAT = insertDataKhidmat(session, ID_KHIDMAT);
				System.out.println("ID_KHIDMAT after insert? -- "+ID_KHIDMAT);
				}
				else {
				System.out.println("ID_KHIDMAT update? -- "+ID_KHIDMAT);
				ID_KHIDMAT = insertDataKhidmat(session, ID_KHIDMAT);
				System.out.println("ID_KHIDMAT after update? -- "+ID_KHIDMAT);	
				}
				viewKhidmat = viewDataKhidmat(session, ID_KHIDMAT);
				this.context.put("viewKhidmat", viewKhidmat);
			
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKhidmat.jsp";
			}	
			
			else if(command.equals("updateKlasifikasi"))
			{
				
				String ID_KHIDMAT = getParam("ID_KHIDMAT");
			
				System.out.println("ID_KHIDMAT insert? -- "+ID_KHIDMAT);
				ID_KHIDMAT = updateDataKhidmat(session, ID_KHIDMAT);
				System.out.println("ID_KLASIFIKASI after insert? -- "+ID_KHIDMAT);
				
				viewKhidmat = viewDataKhidmat(session, ID_KHIDMAT);
				this.context.put("viewKhidmat", viewKhidmat);
				
				skrin_name = "app/admin/PerkhidmatanJawatan/SenaraiKhidmat.jsp";
			}	
			
			//close AddKhidmat
			else if(command.equals("close_AddKhidmat")){
				
				skrin_name = "app/admin/PerkhidmatanJawatan/blank_viewAddKhidmat.jsp";
			}
			
			else
			{
			
				skrin_name = "app/admin/PerkhidmatanJawatan/index.jsp";
			}
			myLogger.info(" skrin_name : "+skrin_name);
			return skrin_name;
		}
		//Setup Paging
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
		
		public boolean checkNamaKlasifikasi(HttpSession session, String NAMA_, int mode) throws Exception {
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
				
				if (mode == 1){
				
					sql = 	" SELECT NAMA_KLASIFIKASI FROM TBLRUJKLASIFIKASI " +
							" WHERE NAMA_KLASIFIKASI = '"+NAMA_+"'" ;
				}
				else if (mode == 2){
					
					sql = 	" SELECT NAMA_GRED FROM TBLRUJGRED " +
							" WHERE NAMA_GRED = '"+NAMA_+"'" ;
				}
					myLogger.info(" checkNamaKlasifikasi :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						if (mode == 1){
						NAMA_ = rs.getString("NAMA_KLASIFIKASI") == null ? "" : rs.getString("NAMA_KLASIFIKASI");
						} 
						else if (mode == 2){
						NAMA_ = rs.getString("NAMA_GRED") == null ? "" : rs.getString("NAMA_GRED");
						}
						if(!NAMA_.equals(""))
						{
							check = false;
						}	
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
		
		//++SKIM KHIDMAT++//
		//listGredByIdPejabat
		@SuppressWarnings("unchecked")
		public List listGredByIdPejabat(HttpSession session, String ID_GRED, String ID_JAWATAN,String ID_KLASIFIKASI)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listGredByIdPejabat = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = 	/*"SELECT DISTINCT G.ID_GRED AS ID, UPPER(G.NAMA_GRED) AS KETERANGAN, "+
	                    "(CASE  WHEN G.ID_JAWATAN = '"+ID_JAWATAN+"' THEN 'Y' ELSE '' END) AS TICK "+
	                    "FROM TBLRUJGRED G";*/
						
						  "SELECT DISTINCT G.ID_GRED AS ID, UPPER(G.NAMA_GRED) AS KETERANGAN, 'Y' AS TICK " +
						  " FROM TBLRUJGRED G ,  TBLRUJGREDJAWATAN GJ " +
						  " WHERE G.ID_GRED = GJ.ID_GRED " +
						  " AND  GJ.ID_JAWATAN = '"+ID_JAWATAN+"'"+
						  " AND  G.ID_KLASIFIKASI = '"+ID_KLASIFIKASI+"'"+
						  " UNION " +
						  " SELECT DISTINCT G.ID_GRED AS ID, UPPER(G.NAMA_GRED) AS KETERANGAN, '' AS TICK " +
						  " FROM TBLRUJGRED G " +
						  " WHERE G.ID_GRED NOT IN (SELECT DISTINCT GJ.ID_GRED  " +
						  " FROM TBLRUJGREDJAWATAN GJ WHERE GJ.ID_JAWATAN = '"+ID_JAWATAN+"')" +
						  " AND  G.ID_KLASIFIKASI = '"+ID_KLASIFIKASI+"'"; 
				
				myLogger.info(" SQL listGredByIdPejabat :"+ sql);
				rs = stmt.executeQuery(sql);
				listGredByIdPejabat = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					h.put("BIL",bil);
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));	
					h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));	
					//h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
					
					listGredByIdPejabat.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listGredByIdPejabat;

		}
		
		//display Checkbox list gred

		@SuppressWarnings("unchecked")
		public List listGred(HttpSession session,  String ID_GRED,String ID_JAWATAN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listGred = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = "SELECT DISTINCT G.ID_GRED AS ID, UPPER(G.NAMA_GRED) AS KETERANGAN "+
						  " FROM TBLRUJGRED G ,  TBLRUJGREDJAWATAN GJ " +
						  " WHERE G.ID_GRED = GJ.ID_GRED " +
						  " AND  GJ.ID_JAWATAN = '"+ID_JAWATAN+"'";

				myLogger.info(" SQL listCheckGred:"+ sql);
				rs = stmt.executeQuery(sql);
				listGred = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				int new_bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
				/*	
					if(rs.getString("LAYER").equals("1"))
					{
						new_bil = 0;
					}
					if(rs.getString("LAYER").equals("2"))
					{
						new_bil++;
					}*/
					
					h.put("BIL",bil);
					h.put("NEWBIL",new_bil);
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));		
					
					listGred.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listGred;

		}

		//Update Skim Khidmat

		public String updateDataSkimKhidmat(HttpSession session,String ID_JAWATAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			String sql = "";

			//long ID_JAWATAN_UPDATE= 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String KETERANGAN = getParam("KETERANGAN_"+ID_JAWATAN);
				String KOD_JAWATAN = getParam("KOD_JAWATAN_"+ID_JAWATAN);
				String SHORTNAME_JAWATAN = getParam("SHORTNAME_JAWATAN_"+ID_JAWATAN);
				String KOD_SKIM = getParam("KOD_SKIM_"+ID_JAWATAN);
				String KETERANGAN_TUGASAN= getParam("KETERANGAN_TUGASAN_"+ID_JAWATAN);
				String SUMBER_MAKLUMAT = getParam("SUMBER_MAKLUMAT_"+ID_JAWATAN);
				String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_JAWATAN);
				String ID_MASUK = getParam("ID_MASUK_"+ID_JAWATAN);
				String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_JAWATAN);
				String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_JAWATAN);
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI_"+ID_JAWATAN);
				String ID_KHIDMAT = getParam("ID_KHIDMAT_"+ID_JAWATAN);
				//String ID_GRED = getParam("ID_GRED_"+ID_JAWATAN);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
	
				//UPDATE
				r.update("ID_JAWATAN", ID_JAWATAN);
				//ID_JAWATAN = ID_JAWATAN_UPDATE+"";
				
				r.add("KOD_JAWATAN", KOD_JAWATAN);
				r.add("KETERANGAN", KETERANGAN);
				r.add("SHORTNAME_JAWATAN", SHORTNAME_JAWATAN);
				r.add("KOD_SKIM", KOD_SKIM);
				r.add("KETERANGAN_TUGASAN", KETERANGAN_TUGASAN);
				r.add("SUMBER_MAKLUMAT", SUMBER_MAKLUMAT);
				r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
				r.add("ID_KHIDMAT", ID_KHIDMAT);
				//r.add("ID_GRED", ID_GRED);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
			
		
				
				sql = r.getSQLUpdate("TBLRUJJAWATAN");		
				System.out.println("UPDATE TBLRUJJAWATAN: "+sql);
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_JAWATAN;
		}
		
		//insertDataSkimKhidmat
	
		public String insertDataSkimKhidmat(HttpSession session,String ID_JAWATAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			String sql = "";

			long ID_JAWATAN_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String KOD_JAWATAN = getParam("KOD_JAWATAN_"+ID_JAWATAN);
				String KETERANGAN = getParam("KETERANGAN_"+ID_JAWATAN);
				String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_JAWATAN);
				String ID_MASUK = getParam("ID_MASUK_"+ID_JAWATAN);
				String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_JAWATAN);
				String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_JAWATAN);
				String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_JAWATAN);
				String SHORTNAME_JAWATAN = getParam("SHORTNAME_JAWATAN_"+ID_JAWATAN);
				String KOD_SKIM = getParam("KOD_SKIM_"+ID_JAWATAN);
				String KETERANGAN_TUGASAN = getParam("KETERANGAN_TUGASAN_"+ID_JAWATAN);
				String SUMBER_MAKLUMAT = getParam("SUMBER_MAKLUMAT_"+ID_JAWATAN);
				String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI_"+ID_JAWATAN);
				String ID_KHIDMAT = getParam("ID_KHIDMAT_"+ID_JAWATAN);
				//String ID_GRED = getParam("ID_GRED_"+ID_JAWATAN);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				//INSERT
				
				ID_JAWATAN_INSERT = DB.getNextID(db,"TBLRUJJAWATAN_SEQ");
				ID_JAWATAN = ID_JAWATAN_INSERT+"";
				
				r.add("ID_JAWATAN", ID_JAWATAN);
				r.add("KOD_JAWATAN", KOD_JAWATAN);
				r.add("KETERANGAN", KETERANGAN);
				r.add("SHORTNAME_JAWATAN", SHORTNAME_JAWATAN);
				r.add("KOD_SKIM", KOD_SKIM);
				r.add("KETERANGAN_TUGASAN", KETERANGAN_TUGASAN);
				r.add("SUMBER_MAKLUMAT", SUMBER_MAKLUMAT);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
				r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
				r.add("ID_KHIDMAT",ID_KHIDMAT);
				//r.add("ID_GRED",ID_GRED);	

				sql = r.getSQLInsert("TBLRUJJAWATAN");		
				System.out.println("INSERT TBLRUJJAWATAN : "+sql);
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_JAWATAN;
		}
		
		//viewSkimKhidmat
		@SuppressWarnings({ "unchecked" })
		public Hashtable viewDataSkimKhidmat (HttpSession session, String ID_JAWATAN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_JAWATAN.equals(""))
				{
					sql = querySkimKhidmat(session,ID_JAWATAN);
					myLogger.info(" viewDataSkimKhidmat :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {		
						h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
						h.put("KOD_JAWATAN",rs.getString("KOD_JAWATAN") == null ? "" : rs.getString("KOD_JAWATAN"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("SHORTNAME_JAWATAN",rs.getString("SHORTNAME_JAWATAN") == null ? "" : rs.getString("SHORTNAME_JAWATAN"));
						h.put("KOD_SKIM",rs.getString("KOD_SKIM") == null ? "" : rs.getString("KOD_SKIM"));
						h.put("KETERANGAN_TUGASAN",rs.getString("KETERANGAN_TUGASAN") == null ? "" : rs.getString("KETERANGAN_TUGASAN"));
						h.put("SUMBER_MAKLUMAT",rs.getString("SUMBER_MAKLUMAT") == null ? "" : rs.getString("SUMBER_MAKLUMAT"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));
						h.put("ID_KHIDMAT",rs.getString("ID_KHIDMAT") == null ? "" : rs.getString("ID_KHIDMAT"));
						h.put("NAMA_KHIDMAT",rs.getString("NAMA_KHIDMAT") == null ? "" : rs.getString("NAMA_KHIDMAT"));
						h.put("NAMA_KLASIFIKASI",rs.getString("NAMA_KLASIFIKASI") == null ? "" : rs.getString("NAMA_KLASIFIKASI"));
						
						}
					}
					else
					{
						h.put("ID_JAWATAN","");
						h.put("KOD_JAWATAN","");
						h.put("KETERANGAN","");
						h.put("SHORTNAME_JAWATAN","");
						h.put("KOD_SKIM","");
						h.put("KETERANGAN_TUGASAN","");
						h.put("SUMBER_MAKLUMAT","");
						h.put("ID_KEMASKINI","");
						h.put("TARIKH_KEMASKINI","");
						h.put("ID_MASUK","");
						h.put("TARIKH_MASUK","");
						h.put("ID_KLASIFIKASI","");
						h.put("ID_KHIDMAT","");
						h.put("NAMA_KHIDMAT","");
						h.put("NAMA_KLASIFIKASI","");
					
						
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

		//Query Skim Khidmat
		public String querySkimKhidmat(HttpSession session,String ID_JAWATAN) throws Exception {	
			
			String query = "";
			
					query += "SELECT SK.ID_JAWATAN, "+ 
	                        "SK.KOD_JAWATAN, "+
	                        "SK.KETERANGAN, "+
	                        "SK.ID_MASUK, "+
	                        "SK.ID_KEMASKINI, "+
	                        "SK.SHORTNAME_JAWATAN, "+      
	                        "SK.KOD_SKIM, "+        
	                        "SK.KETERANGAN_TUGASAN, "+ 
	                        "SK.SUMBER_MAKLUMAT, "+ 
	                        "SK.ID_KLASIFIKASI, "+ 
	                        "SK.ID_KHIDMAT, "+ 
	                        "K.NAMA_KLASIFIKASI, "+ 
	                        "KH.NAMA_KHIDMAT," +
	                        "TO_CHAR(SK.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
	                        "TO_CHAR(SK.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK "+       
	                        "FROM "+
	                        "TBLRUJJAWATAN SK, TBLRUJKHIDMAT KH, TBLRUJKLASIFIKASI K "+
	                        "WHERE SK.ID_KHIDMAT = KH.ID_KHIDMAT(+) "+
	                        "AND SK.ID_KLASIFIKASI = K.ID_KLASIFIKASI(+) ";


					if(!ID_JAWATAN.trim().equals(""))
					{
						query += " AND SK.ID_JAWATAN = '"+ID_JAWATAN.trim()+"' ";
					}

			return query;
		}
		
		//listDataSkimKhidmat

		@SuppressWarnings("unchecked")
		public List listDataSkimKhidmat(HttpSession session, String carianSkimKhidmat, String carianUmum)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listDataSkimKhidmat = null;
			String sql = "";
			
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = "SELECT SK.ID_JAWATAN, "+ 
                        "SK.KOD_JAWATAN, "+
                        "SK.KETERANGAN, "+
                        "SK.ID_MASUK, "+
                        "SK.ID_KEMASKINI, "+
                        "SK.SHORTNAME_JAWATAN, "+      
                        "SK.KOD_SKIM, "+        
                        "SK.KETERANGAN_TUGASAN, "+ 
                        "SK.SUMBER_MAKLUMAT, "+ 
                        "SK.ID_KLASIFIKASI, "+ 
                        "SK.ID_KHIDMAT, "+ 
                        "K.NAMA_KLASIFIKASI, "+ 
                        "KH.NAMA_KHIDMAT, "+ 
                        "TO_CHAR(SK.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
                        "TO_CHAR(SK.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK "+       
                        "FROM "+
                        "TBLRUJJAWATAN SK, TBLRUJKHIDMAT KH, TBLRUJKLASIFIKASI K "+
                        "WHERE SK.ID_KHIDMAT = KH.ID_KHIDMAT(+) "+
                        "AND SK.ID_KLASIFIKASI = K.ID_KLASIFIKASI(+) ";
				
				if(!carianUmum.equals("") && carianUmum != null)
				{
				sql += " AND (";
				
				sql += " UPPER(K.NAMA_KLASIFIKASI) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(SK.KETERANGAN) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(SK.KOD_SKIM) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(SK.SUMBER_MAKLUMAT) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.NAMA_KHIDMAT) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				//sql += " OR UPPER(G.NAMA_GRED) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";					
				//sql += " OR UPPER(KH.SUMBER_MAKLUMAT) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";
				
				sql += ")";
				
				}
				
				else if(!carianSkimKhidmat.equals("") && carianSkimKhidmat != null)
				{
				sql += " AND (";
				
				sql += " UPPER(K.NAMA_KLASIFIKASI) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(SK.KETERANGAN) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(SK.KOD_SKIM) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(SK.SUMBER_MAKLUMAT) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.NAMA_KHIDMAT) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";
				//sql += " OR UPPER(G.NAMA_GRED) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";					
				//sql += " OR UPPER(KH.SUMBER_MAKLUMAT) LIKE UPPER('%"+carianSkimKhidmat.trim().toUpperCase()+"%') ";
				
				sql += ") ";
				
				}
				
				sql += "ORDER BY ID_JAWATAN ASC ";
			
				myLogger.info(" SQL listDataSkimKhidmat  : "+ sql);
				rs = stmt.executeQuery(sql);
				listDataSkimKhidmat = Collections.synchronizedList(new ArrayList());
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
					h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
					h.put("KOD_JAWATAN",rs.getString("KOD_JAWATAN") == null ? "" : rs.getString("KOD_JAWATAN"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					h.put("SHORTNAME_JAWATAN",rs.getString("SHORTNAME_JAWATAN") == null ? "" : rs.getString("SHORTNAME_JAWATAN"));
					h.put("KOD_SKIM",rs.getString("KOD_SKIM") == null ? "" : rs.getString("KOD_SKIM"));
					h.put("KETERANGAN_TUGASAN",rs.getString("KETERANGAN_TUGASAN") == null ? "" : rs.getString("KETERANGAN_TUGASAN"));
					h.put("SUMBER_MAKLUMAT",rs.getString("SUMBER_MAKLUMAT") == null ? "" : rs.getString("SUMBER_MAKLUMAT"));
					h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));
					h.put("ID_KHIDMAT",rs.getString("ID_KHIDMAT") == null ? "" : rs.getString("ID_KHIDMAT"));
					//h.put("ID_GRED",rs.getString("ID_GRED") == null ? "" : rs.getString("ID_GRED"));
					//h.put("NAMA_GRED",rs.getString("NAMA_GRED") == null ? "" : rs.getString("NAMA_GRED"));
					h.put("NAMA_KLASIFIKASI",rs.getString("NAMA_KLASIFIKASI") == null ? "" : rs.getString("NAMA_KLASIFIKASI"));
					h.put("NAMA_KHIDMAT",rs.getString("NAMA_KHIDMAT") == null ? "" : rs.getString("NAMA_KHIDMAT"));
					
					listDataSkimKhidmat.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listDataSkimKhidmat;

		}
		//saveGredbyPejabat
		public int saveGredbyPejabat(HttpSession session, String ID_JAWATAN,String ID_GREDJAWATAN, String ID_GRED, String[] CHECKLIST_) throws Exception {
			
			Connection conn = null;
			Db db = null;
			String sql = "";
			int total_gred_update = 0;
			long ID_GRED_INSERT = 0;
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "DELETE FROM TBLRUJGREDJAWATAN  WHERE ID_JAWATAN = "+ ID_JAWATAN;
				
				myLogger.info("DELETE ALL GRED : "+sql);
				stmt.executeUpdate(sql);			
				
				for (String GRED_ID : CHECKLIST_) {
							
					if(GRED_ID!=null)
					{
						
						sql = "";
						r.clear();
						
						ID_GRED_INSERT = DB.getNextID(db,"TBLRUJGREDJAWATAN_SEQ");
						ID_GREDJAWATAN = ID_GRED_INSERT+"";
						
						r.add("ID_GREDJAWATAN", ID_GREDJAWATAN);
						r.add("ID_JAWATAN", ID_JAWATAN);
						r.add("ID_GRED", GRED_ID);
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLRUJGREDJAWATAN");		
						
						myLogger.info("SKIM PERKHIDMATAN : INSERT TBLRUJGREDJAWATAN : "+sql);
						
						stmt.executeUpdate(sql);
						total_gred_update++;
							
					}
					GRED_ID = "";
				}
				
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLogger.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat insert gred : "+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return total_gred_update;
		}
		
		//delete Skim Khidmat
		
		public void deleteSkimKhidmat(HttpSession session,String ID_JAWATAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				myLogger.info("Masuk delete JAWATAN == "+ ID_JAWATAN);
				
			
					r.add("ID_JAWATAN", ID_JAWATAN);
					sql = r.getSQLDelete("TBLRUJJAWATAN");
					AuditTrail.logActivity(this,session,"DEL","JAWATAN ["+ID_JAWATAN+"] Deleted");
			
				
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
		    	throw new Exception("Ralat Delete Skim Khidmat :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
		}
		
		
		//+++KHIDMAT+++//
		
		//Update Khidmat

		public String updateDataKhidmat(HttpSession session,String ID_KHIDMAT) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			String sql = "";

			long ID_KHIDMAT_UPDATE= 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				String NAMA_KHIDMAT = getParam("NAMA_KHIDMAT_"+ID_KHIDMAT);
				String NAMA_LAIN_KHIDMAT = getParam("NAMA_LAIN_KHIDMAT_"+ID_KHIDMAT);
				String KETERANGAN_KHIDMAT = getParam("KETERANGAN_KHIDMAT_"+ID_KHIDMAT);
				String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_KHIDMAT);
				String ID_MASUK = getParam("ID_MASUK_"+ID_KHIDMAT);
				String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_KHIDMAT);
				String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_KHIDMAT);
				//String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_KHIDMAT);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
	
				//UPDATE
				r.update("ID_KHIDMAT", ID_KHIDMAT);
				ID_KHIDMAT = ID_KHIDMAT_UPDATE+"";
				
				r.add("NAMA_KHIDMAT", NAMA_KHIDMAT);
				r.add("NAMA_LAIN_KHIDMAT", NAMA_LAIN_KHIDMAT);
				r.add("KETERANGAN_KHIDMAT", KETERANGAN_KHIDMAT);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
				//r.add("FLAG_STATUS", FLAG_STATUS);
		
				
				sql = r.getSQLUpdate("TBLRUJKHIDMAT");		
				System.out.println("INSERT TBLRUJKHIDMAT : "+sql);
				stmt.executeUpdate(sql);	
				
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_KHIDMAT;
		}
		
		//Insert Khidmat
		//ID_KHIDMAT
		
		public String insertDataKhidmat(HttpSession session,String ID_KHIDMAT) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			String sql = "";

			long ID_KHIDMAT_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				String NAMA_KHIDMAT = getParam("NAMA_KHIDMAT_"+ID_KHIDMAT);
				String NAMA_LAIN_KHIDMAT = getParam("NAMA_LAIN_KHIDMAT_"+ID_KHIDMAT);
				String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_KHIDMAT);
				String ID_MASUK = getParam("ID_MASUK_"+ID_KHIDMAT);
				String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_KHIDMAT);
				String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_KHIDMAT);
				//String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_KHIDMAT);
				String KETERANGAN_KHIDMAT = getParam("KETERANGAN_KHIDMAT_"+ID_KHIDMAT);
				String SKOP_GRED1 = getParam("SKOP_GRED1_"+ID_KHIDMAT);
				String SKOP_GRED2 = getParam("SKOP_GRED2_"+ID_KHIDMAT);
				String SUMBER_MAKLUMAT = getParam("SUMBER_MAKLUMAT_"+ID_KHIDMAT);
				String CATATAN = getParam("CATATAN_"+ID_KHIDMAT);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("NAMA_KHIDMAT", NAMA_KHIDMAT);
				r.add("NAMA_LAIN_KHIDMAT", NAMA_LAIN_KHIDMAT);
				r.add("KETERANGAN_KHIDMAT", KETERANGAN_KHIDMAT);
				r.add("SKOP_GRED1", SKOP_GRED1);
				r.add("SKOP_GRED2", SKOP_GRED2);
				r.add("CATATAN", CATATAN);
				r.add("SUMBER_MAKLUMAT", SUMBER_MAKLUMAT);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
				//r.add("FLAG_STATUS", FLAG_STATUS);

				if (ID_KHIDMAT.equals("")){
				ID_KHIDMAT_INSERT = DB.getNextID(db,"TBLRUJKHIDMAT_SEQ");
				ID_KHIDMAT = ID_KHIDMAT_INSERT+"";
					r.add("ID_KHIDMAT", ID_KHIDMAT);
					sql = r.getSQLInsert("TBLRUJKHIDMAT");		
					System.out.println("INSERT TBLRUJKHIDMAT : "+sql);
					}
				else {
					r.update("ID_KHIDMAT", ID_KHIDMAT);	
					sql = r.getSQLUpdate("TBLRUJKHIDMAT");		
					System.out.println("UPDATE TBLRUJKHIDMAT : "+sql);
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_KHIDMAT;
		}
		
		//viewKhidmat
		public Hashtable viewDataKhidmat (HttpSession session, String ID_KHIDMAT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_KHIDMAT.equals(""))
				{
					sql = queryKhidmat(session,ID_KHIDMAT);
					myLogger.info(" viewDataKhidmat :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {		
						h.put("ID_KHIDMAT",rs.getString("ID_KHIDMAT") == null ? "" : rs.getString("ID_KHIDMAT"));
						h.put("NAMA_KHIDMAT",rs.getString("NAMA_KHIDMAT") == null ? "" : rs.getString("NAMA_KHIDMAT"));
						h.put("NAMA_LAIN_KHIDMAT",rs.getString("NAMA_LAIN_KHIDMAT") == null ? "" : rs.getString("NAMA_LAIN_KHIDMAT"));
						h.put("KETERANGAN_KHIDMAT",rs.getString("KETERANGAN_KHIDMAT") == null ? "" : rs.getString("KETERANGAN_KHIDMAT"));
						h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
						h.put("SKOP_GRED1",rs.getString("SKOP_GRED1") == null ? "" : rs.getString("SKOP_GRED1"));
						h.put("SKOP_GRED2",rs.getString("SKOP_GRED2") == null ? "" : rs.getString("SKOP_GRED2"));
						h.put("SUMBER_MAKLUMAT",rs.getString("SUMBER_MAKLUMAT") == null ? "" : rs.getString("SUMBER_MAKLUMAT"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					//	h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
					
						}
					}
					else
					{
						h.put("ID_KHIDMAT","");
						h.put("NAMA_KHIDMAT","");
						h.put("NAMA_LAIN_KHIDMAT","");
						h.put("KETERANGAN_KHIDMAT","");
						h.put("CATATAN","");
						h.put("SKOP_GRED1","");
						h.put("SKOP_GRED2","");
						h.put("SUMBER_MAKLUMAT","");
						h.put("ID_KEMASKINI","");
						h.put("TARIKH_KEMASKINI","");
						h.put("ID_MASUK","");
						h.put("TARIKH_MASUK","");
						//h.put("FLAG_STATUS","");
					
						
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

		//Query Khidmat
		public String queryKhidmat(HttpSession session,String ID_KHIDMAT) throws Exception {	
			
			String query = "";
			
					query += "SELECT KH.ID_KHIDMAT, "+ 
							   "KH.NAMA_KHIDMAT, "+
							   "KH.NAMA_LAIN_KHIDMAT, "+
							   "KH.SKOP_GRED1, "+
							   "KH.SKOP_GRED2, "+
							   "KH.CATATAN, "+
							   "KH.SUMBER_MAKLUMAT, "+
						       "KH.KETERANGAN_KHIDMAT, "+      
						       "KH.ID_KEMASKINI, "+      
						       "KH.ID_MASUK, "+        
						       "TO_CHAR(KH.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
						       "TO_CHAR(KH.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK "+        
						      /* "(CASE WHEN KH.FLAG_STATUS = 1 THEN 'AKTIF' WHEN K.FLAG_STATUS = 2 THEN 'TIDAK AKTIF' "+
						       " ELSE 'AKTIF' END ) AS FLAG_STATUS "+  */ 
						       "FROM "+
						       "TBLRUJKHIDMAT KH ";


					if(!ID_KHIDMAT.trim().equals(""))
					{
						query += " WHERE KH.ID_KHIDMAT = '"+ID_KHIDMAT.trim()+"' ";
					}

			return query;
		}
		
		//listDataKhidmat

		@SuppressWarnings("unchecked")
		public List listDataKhidmat(HttpSession session, String carianKhidmat, String carianUmum)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listDataKhidmat = null;
			String sql = "";
			
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = "SELECT KH.ID_KHIDMAT, "+ 
						   "KH.NAMA_KHIDMAT, "+
						   "KH.NAMA_LAIN_KHIDMAT, "+
						   "KH.SKOP_GRED1, "+
						   "KH.SKOP_GRED2, "+
						   "KH.CATATAN, "+
						   "KH.SUMBER_MAKLUMAT, "+
					       "KH.KETERANGAN_KHIDMAT, "+      
					       "KH.ID_KEMASKINI, "+      
					       "KH.ID_MASUK, "+        
					       "TO_CHAR(KH.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
					       "TO_CHAR(KH.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK "+        
					      /* "(CASE WHEN KH.FLAG_STATUS = 1 THEN 'AKTIF' WHEN K.FLAG_STATUS = 2 THEN 'TIDAK AKTIF' "+
					       " ELSE 'AKTIF' END ) AS FLAG_STATUS "+  */ 
					       "FROM "+
					       "TBLRUJKHIDMAT KH";
					      /* "WHERE  KH.ID_KHIDMAT = ";*/

				if(!carianUmum.equals("") && carianUmum != null)
				{
				sql += " WHERE (";
				
				sql += " UPPER(KH.NAMA_KHIDMAT) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(KH.KETERANGAN_KHIDMAT) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.NAMA_LAIN_KHIDMAT) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.SKOP_GRED1) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(KH.SKOP_GRED2) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(KH.CATATAN) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.SUMBER_MAKLUMAT) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				
				sql += ") ORDER BY ID_KHIDMAT";
				
				}
				
				else if(!carianKhidmat.equals("") && carianKhidmat != null)
				{
				sql += " WHERE (";
				
				sql += " UPPER(KH.NAMA_KHIDMAT) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(KH.KETERANGAN_KHIDMAT) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.NAMA_LAIN_KHIDMAT) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.SKOP_GRED1) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(KH.SKOP_GRED2) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(KH.CATATAN) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(KH.SUMBER_MAKLUMAT) LIKE UPPER('%"+carianKhidmat.trim().toUpperCase()+"%') ";
				
				sql += ") ORDER BY ID_KHIDMAT";
				
				}
			
				
				rs = stmt.executeQuery(sql);
				listDataKhidmat = Collections.synchronizedList(new ArrayList());
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
					h.put("ID_KHIDMAT",rs.getString("ID_KHIDMAT") == null ? "" : rs.getString("ID_KHIDMAT"));
					h.put("NAMA_KHIDMAT",rs.getString("NAMA_KHIDMAT") == null ? "" : rs.getString("NAMA_KHIDMAT"));
					h.put("NAMA_LAIN_KHIDMAT",rs.getString("NAMA_LAIN_KHIDMAT") == null ? "" : rs.getString("NAMA_LAIN_KHIDMAT"));
					h.put("SUMBER_MAKLUMAT",rs.getString("SUMBER_MAKLUMAT") == null ? "" : rs.getString("SUMBER_MAKLUMAT"));
					h.put("KETERANGAN_KHIDMAT",rs.getString("KETERANGAN_KHIDMAT") == null ? "" : rs.getString("KETERANGAN_KHIDMAT"));
					h.put("SKOP_GRED1",rs.getString("SKOP_GRED1") == null ? "" : rs.getString("SKOP_GRED1"));
					h.put("SKOP_GRED2",rs.getString("SKOP_GRED2") == null ? "" : rs.getString("SKOP_GRED2"));
					h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					//h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
											
					listDataKhidmat.add(h);
				}
				myLogger.info(" SQL listDataKhidmat  :--"+ sql);

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listDataKhidmat;

		}
		
		//delete Khidmat
		
				public void deleteKhidmat(HttpSession session,String ID_KHIDMAT) throws Exception {
					Connection conn = null;
					Db db = null;
					String sql = "";
					
					try {
						db = new Db();
						conn = db.getConnection();
						conn.setAutoCommit(false);
						
						Statement stmt = db.getStatement();
						SQLRenderer r = new SQLRenderer();
						
						myLogger.info("Masuk delete KHIDMAT == "+ ID_KHIDMAT);
						
					
							r.add("ID_KHIDMAT", ID_KHIDMAT);
							sql = r.getSQLDelete("TBLRUJKHIDMAT");
							AuditTrail.logActivity(this,session,"DEL","KHIDMAT ["+ID_KHIDMAT+"] Deleted");
					
						
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
				    	throw new Exception("Ralat Delete Khidmat :"+se.getMessage());
					}
					catch (Exception re) {
						throw re;
					}finally {
						if (db != null)
							db.close();
					}
				}
		
		//+++KLASIFIKASI+++//
		
		//Update Klasifikasi

		public String updateDataKlasifikasi(HttpSession session,String ID_KLASIFIKASI) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			//String returnID_PEJABAT = "";
			String sql = "";

			long ID_KLASIFIKASI_UPDATE= 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				String NAMA_KLASIFIKASI = getParam("NAMA_KLASIFIKASI_"+ID_KLASIFIKASI);
				String KETERANGAN = getParam("KETERANGAN_"+ID_KLASIFIKASI);
				String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_KLASIFIKASI);
				String ID_MASUK = getParam("ID_MASUK_"+ID_KLASIFIKASI);
				String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_KLASIFIKASI);
				String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_KLASIFIKASI);
				String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_KLASIFIKASI);
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
	
				//UPDATE
				r.update("ID_KLASIFIKASI", ID_KLASIFIKASI);
				ID_KLASIFIKASI = ID_KLASIFIKASI_UPDATE+"";
				
				r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
				r.add("NAMA_KLASIFIKASI", NAMA_KLASIFIKASI);
				r.add("KETERANGAN", KETERANGAN);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
				r.add("FLAG_STATUS", FLAG_STATUS);
		
				
				sql = r.getSQLUpdate("TBLRUJKLASIFIKASI");		
				System.out.println("INSERT TBLRUJKLASIFIKASI : "+sql);
				stmt.executeUpdate(sql);	
				
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_KLASIFIKASI;
		}
		
		//Insert Klasifikasi
		
		public String insertDataKlasifikasi(HttpSession session,String ID_KLASIFIKASI) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			String sql = "";

			long ID_KLASIFIKASI_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				String NAMA_KLASIFIKASI = getParam("NAMA_KLASIFIKASI_"+ID_KLASIFIKASI);
				String KETERANGAN = getParam("KETERANGAN_"+ID_KLASIFIKASI);
				String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_KLASIFIKASI);
				String ID_MASUK = getParam("ID_MASUK_"+ID_KLASIFIKASI);
				String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_KLASIFIKASI);
				String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_KLASIFIKASI);
				String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_KLASIFIKASI);

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				//INSERT
				
				ID_KLASIFIKASI_INSERT = DB.getNextID(db,"TBLRUJKLASIFIKASI_SEQ");
				ID_KLASIFIKASI = ID_KLASIFIKASI_INSERT+"";
				
				r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
				r.add("NAMA_KLASIFIKASI", NAMA_KLASIFIKASI);
				r.add("KETERANGAN", KETERANGAN);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				r.add("TARIKH_MASUK", r.unquote("sysdate"));	
				r.add("FLAG_STATUS", FLAG_STATUS);

				sql = r.getSQLInsert("TBLRUJKLASIFIKASI");		
				System.out.println("INSERT TBLRUJKLASIFIKASI : "+sql);
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_KLASIFIKASI;
		}
		
		//viewKlasifikasi
		
		public Hashtable viewDataKlasifikasi (HttpSession session, String ID_KLASIFIKASI) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_KLASIFIKASI.equals(""))
				{
					sql = queryKlasifikasi(session,ID_KLASIFIKASI);
					myLogger.info(" viewDataKlasifikasi :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {		
						h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));
						h.put("NAMA_KLASIFIKASI",rs.getString("NAMA_KLASIFIKASI") == null ? "" : rs.getString("NAMA_KLASIFIKASI"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
					
						}
					}
					else
					{
						h.put("ID_KLASIFIKASI","");
						h.put("NAMA_KLASIFIKASI","");
						h.put("KETERANGAN","");
						h.put("ID_KEMASKINI","");
						h.put("TARIKH_KEMASKINI","");
						h.put("ID_MASUK","");
						h.put("TARIKH_MASUK","");
						h.put("FLAG_STATUS","");
					
						
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

		//Query Klasifikasi
		public String queryKlasifikasi(HttpSession session,String ID_KLASIFIKASI) throws Exception {	
			
			String query = "";
			
					query += "SELECT K.ID_KLASIFIKASI, "+ 
							   "UPPER(K.NAMA_KLASIFIKASI) NAMA_KLASIFIKASI, "+
						       "UPPER(K.KETERANGAN) KETERANGAN, "+      
						       "K.ID_KEMASKINI, "+      
						       "K.ID_MASUK, "+        
						       "TO_CHAR(K.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
						       "TO_CHAR(K.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK, "+        
						       "(CASE WHEN K.FLAG_STATUS = 1 THEN 'AKTIF' WHEN K.FLAG_STATUS = 2 THEN 'TIDAK AKTIF' "+
						       " ELSE 'AKTIF' END ) AS FLAG_STATUS "+   
						       "FROM "+
						       "TBLRUJKLASIFIKASI K ";


					if(!ID_KLASIFIKASI.trim().equals(""))
					{
						query += " WHERE K.ID_KLASIFIKASI = '"+ID_KLASIFIKASI.trim()+"' ";
					}

			return query;
		}
		
		//listDataKlasifikasi

		@SuppressWarnings("unchecked")
		public List listDataKlasifikasi(HttpSession session, String carianKlasifikasi, String carianUmum)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listDataKlasifikasi = null;
			String sql = "";
			
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = "SELECT K.ID_KLASIFIKASI, "+ 
						   "K.NAMA_KLASIFIKASI, "+
					       "K.KETERANGAN, "+      
					       "K.ID_KEMASKINI, "+      
					       "K.ID_MASUK, "+        
					       "TO_CHAR(K.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
					       "TO_CHAR(K.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK, "+  
					       "(CASE WHEN K.FLAG_STATUS = 1 THEN 'AKTIF' WHEN K.FLAG_STATUS = 2 THEN 'TIDAK AKTIF' "+
					       " ELSE 'AKTIF' END ) AS FLAG_STATUS "+   
					       "FROM "+
					       "TBLRUJKLASIFIKASI K ";	;
				
		       if(!carianUmum.equals("") && carianUmum != null)
				{
				sql += " where (";
				
				sql += " UPPER(K.NAMA_KLASIFIKASI) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(K.KETERANGAN) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(K.FLAG_STATUS) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				
				sql += ") ORDER BY ID_KLASIFIKASI";
				
				}
					       
		       else if(!carianKlasifikasi.equals("") && carianKlasifikasi != null)
				{
				sql += " where (";
				
				sql += " UPPER(K.NAMA_KLASIFIKASI) LIKE UPPER('%"+carianKlasifikasi.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(K.KETERANGAN) LIKE UPPER('%"+carianKlasifikasi.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(K.FLAG_STATUS) LIKE UPPER('%"+carianKlasifikasi.trim().toUpperCase()+"%') ";
				
				sql += ") ORDER BY ID_KLASIFIKASI";
				
				}
			
				myLogger.info(" SQL listDataKlasifikasi :--"+ sql);
				rs = stmt.executeQuery(sql);
				listDataKlasifikasi = Collections.synchronizedList(new ArrayList());
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
					h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));
					h.put("NAMA_KLASIFIKASI",rs.getString("NAMA_KLASIFIKASI") == null ? "" : rs.getString("NAMA_KLASIFIKASI"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
											
					listDataKlasifikasi.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listDataKlasifikasi;

		}
		
		//delete Klasifikasi
		
		public void deleteKlasifikasi(HttpSession session,String ID_KLASIFIKASI) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				myLogger.info("Masuk delete KLASIFIKASI == "+ ID_KLASIFIKASI);
				
			
					r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
					sql = r.getSQLDelete("TBLRUJKLASIFIKASI");
					AuditTrail.logActivity(this,session,"DEL","KLASIFIKASI ["+ID_KLASIFIKASI+"] Deleted");
			
				
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
		    	throw new Exception("Ralat Delete Klasifikasi :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
		}
		//+++GRED+++//
		
		//listDataGred
		
		@SuppressWarnings("unchecked")
		public List listDataGred(HttpSession session,String CarianGred,String carianUmum)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listDataGred = null;
			String sql = "";
	
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = "SELECT G.ID_GRED, "+ 
						   "G.NAMA_GRED, "+
					       "G.KETERANGAN, "+      
					       "G.ID_KEMASKINI, "+      
					       "G.ID_MASUK, "+        
					       "TO_CHAR(G.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
					       "TO_CHAR(G.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK, "+    
					       "(CASE WHEN G.FLAG_STATUS = 1 THEN 'AKTIF' WHEN G.FLAG_STATUS = 2 THEN 'TIDAK AKTIF' "+
					       " ELSE 'AKTIF' END ) AS FLAG_STATUS, "+  
					       "G.ID_KLASIFIKASI "+
					       "FROM "+
					       "TBLRUJGRED G, TBLRUJKLASIFIKASI K "+
					       "WHERE G.ID_KLASIFIKASI = K.ID_KLASIFIKASI ";
					      
				if(!carianUmum.equals("") && carianUmum != null)
				{
				sql += " AND (";
				
				sql += " UPPER(G.NAMA_GRED) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(G.KETERANGAN) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(G.FLAG_STATUS ) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(G.ID_GRED ) LIKE UPPER('%"+carianUmum.trim().toUpperCase()+"%') ";
				
				sql += ")ORDER BY ID_GRED,NAMA_GRED " ;
	
				
				}	     
	
				else if(!CarianGred.equals("") && CarianGred != null)
				{
				sql += " AND (";
				
				sql += " UPPER(G.NAMA_GRED) LIKE UPPER('%"+CarianGred.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(G.KETERANGAN) LIKE UPPER('%"+CarianGred.trim().toUpperCase()+"%') ";					
				sql += " OR UPPER(G.FLAG_STATUS ) LIKE UPPER('%"+CarianGred.trim().toUpperCase()+"%') ";
				sql += " OR UPPER(G.ID_GRED ) LIKE UPPER('%"+CarianGred.trim().toUpperCase()+"%') ";
				
				sql += ")ORDER BY ID_GRED,NAMA_GRED " ;
	
				
				}
				
				
		
				myLogger.info(" SQL listDataGred :--"+ sql);
				rs = stmt.executeQuery(sql);
				listDataGred = Collections.synchronizedList(new ArrayList());
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
					h.put("ID_GRED",rs.getString("ID_GRED") == null ? "" : rs.getString("ID_GRED"));
					h.put("NAMA_GRED",rs.getString("NAMA_GRED") == null ? "" : rs.getString("NAMA_GRED"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
					h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));
					
					listDataGred.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listDataGred;

		}

			//viewGred 
			
		public Hashtable viewDataGred (HttpSession session, String ID_GRED) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_GRED.equals(""))
				{
					sql = queryGred(session,ID_GRED);
					myLogger.info(" viewDataGred :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {		
						h.put("ID_GRED",rs.getString("ID_GRED") == null ? "" : rs.getString("ID_GRED"));
						h.put("NAMA_GRED",rs.getString("NAMA_GRED") == null ? "" : rs.getString("NAMA_GRED"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
						h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));
						h.put("NAMA_KLASIFIKASI",rs.getString("NAMA_KLASIFIKASI") == null ? "" : rs.getString("NAMA_KLASIFIKASI"));

						}
					}
					else
					{
						h.put("ID_GRED","");
						h.put("NAMA_GRED","");
						h.put("KETERANGAN","");
						h.put("ID_KEMASKINI","");
						h.put("TARIKH_KEMASKINI","");
						h.put("ID_MASUK","");
						h.put("TARIKH_MASUK","");
						h.put("FLAG_STATUS","");
						h.put("ID_KLASIFIKASI","");
						h.put("NAMA_KLASIFIKASI","");
						
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
		//Query Gred
			
			public String queryGred(HttpSession session,String ID_GRED) throws Exception {	
				
				String query = "";
				
						query += "SELECT G.ID_GRED, "+ 
								   "G.NAMA_GRED, "+
							       "G.KETERANGAN, "+      
							       "G.ID_KEMASKINI, "+      
							       "G.ID_MASUK, "+        
							       "TO_CHAR(G.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
							       "TO_CHAR(G.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK, "+      
							       "(CASE WHEN G.FLAG_STATUS = 1 THEN 'AKTIF' WHEN G.FLAG_STATUS = 2 THEN 'TIDAK AKTIF' "+
							       " ELSE 'AKTIF' END ) AS FLAG_STATUS, "+   
							       "G.ID_KLASIFIKASI, K.NAMA_KLASIFIKASI "+
							       "FROM "+
							       "TBLRUJGRED G, TBLRUJKLASIFIKASI K "+
							       "WHERE G.ID_KLASIFIKASI = K.ID_KLASIFIKASI";

						if(!ID_GRED.trim().equals(""))
						{
							query += " AND G.ID_GRED = '"+ID_GRED.trim()+"' ";
						}

				return query;
			}

			//Update Gred

			public String updateDataGred(HttpSession session,String ID_GRED) throws Exception {
				Connection conn = null;
				Db db = null;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				//String returnID_PEJABAT = "";
				String sql = "";

				long ID_GRED_INSERT= 0;
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					
					String NAMA_GRED = getParam("NAMA_GRED_"+ID_GRED);
					String KETERANGAN = getParam("KETERANGAN_"+ID_GRED);
					String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_GRED);
					String ID_MASUK = getParam("ID_MASUK_"+ID_GRED);
					String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_GRED);
					String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_GRED);
					String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_GRED);
					String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI_"+ID_GRED);
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
		
					//UPDATE
					r.update("ID_GRED", ID_GRED);
					//ID_GRED = ID_GRED_UPDATE+"";
					
					//r.add("ID_GRED", ID_GRED);
					r.add("NAMA_GRED", NAMA_GRED);
					r.add("KETERANGAN", KETERANGAN);
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					r.add("TARIKH_MASUK", r.unquote("sysdate"));	
					r.add("FLAG_STATUS", FLAG_STATUS);
					r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);
					
					
					sql = r.getSQLUpdate("TBLRUJGRED");		
					System.out.println("UPDATE TBLRUJGRED : "+sql);
					stmt.executeUpdate(sql);	
					
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
			    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
				return ID_GRED;
			}
			

			

			
			//Insert Gred
			
			public String insertDataGred(HttpSession session,String ID_GRED) throws Exception {
				Connection conn = null;
				Db db = null;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				//String returnID_PEJABAT = "";
				String sql = "";

				long ID_GRED_INSERT = 0;
				long ID_KLASIFIKASI_INSERT = 0;
				
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					
					String NAMA_GRED = getParam("NAMA_GRED_"+ID_GRED);
					String KETERANGAN = getParam("KETERANGAN_"+ID_GRED);
					String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_GRED);
					String ID_MASUK = getParam("ID_MASUK_"+ID_GRED);
					String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_GRED);
					String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_GRED);
					String FLAG_STATUS = getParam("FLAG_STATUS_"+ID_GRED);
					String ID_KLASIFIKASI = getParam("ID_KLASIFIKASI_"+ID_GRED);
				
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					//INSERT
					
					ID_GRED_INSERT = DB.getNextID(db,"TBLRUJGRED_SEQ");
					ID_GRED = ID_GRED_INSERT+"";
					
					r.add("ID_GRED", ID_GRED);
					r.add("NAMA_GRED", NAMA_GRED);
					r.add("KETERANGAN", KETERANGAN);
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					r.add("TARIKH_MASUK", r.unquote("sysdate"));	
					r.add("FLAG_STATUS", FLAG_STATUS);
					r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);

					sql = r.getSQLInsert("TBLRUJGRED");		
					System.out.println("INSERT TBLRUJGRED : "+sql);
					stmt.executeUpdate(sql);
					
					/*r.clear();
					
					ID_KLASIFIKASI_INSERT = DB.getNextID(db,"TBLRUJKLASIFIKASI_SEQ");
					ID_KLASIFIKASI = ID_KLASIFIKASI_INSERT+"";
				
					r.add("ID_GRED", ID_GRED);
					r.add("NAMA_KLASIFIKASI", NAMA_GRED);
					r.add("KETERANGAN", KETERANGAN);
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					r.add("TARIKH_MASUK", r.unquote("sysdate"));	
					r.add("FLAG_STATUS", FLAG_STATUS);
					r.add("ID_KLASIFIKASI", ID_KLASIFIKASI);

					sql = r.getSQLInsert("TBLRUJKLASIFIKASI");		
					System.out.println("INSERT TBLRUJKLASIFIKASI : "+sql);
					stmt.executeUpdate(sql);	*/
					
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
				return ID_GRED;
			}
			
			//list tblrujklasifikasi

			@SuppressWarnings("unchecked")
			public List list_TBLRUJKLASIFIKASI(HttpSession session, String ID_KLASIFIKASI)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List list_TBLRUJKLASIFIKASI = null;
				String seperator = "/";
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					
					
						sql = " SELECT ID_KLASIFIKASI AS ID, UPPER(NAMA_KLASIFIKASI) AS KETERANGAN, " +
								" UPPER(NAMA_LAIN_KLASIFIKASI) AS KETERANGAN2 FROM TBLRUJKLASIFIKASI " +
								" WHERE ID_KLASIFIKASI IS NOT NULL ORDER BY NAMA_KLASIFIKASI" ;
					
					myLogger.info(" SQL TBLRUJKLASIFIKASI  :"+ sql);
					rs = stmt.executeQuery(sql);
					list_TBLRUJKLASIFIKASI = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
						//h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						h.put("KETERANGAN2",rs.getString("KETERANGAN2") == null ? "" : rs.getString("KETERANGAN2").toUpperCase());
						list_TBLRUJKLASIFIKASI.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return list_TBLRUJKLASIFIKASI;

			}
			
			//list tblrujkhidmat

			@SuppressWarnings("unchecked")
			public List list_TBLRUJKHIDMAT(HttpSession session, String ID_KHIDMAT)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List list_TBLRUJKHIDMAT = null;
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					
					
						sql = " SELECT ID_KHIDMAT AS ID, UPPER(NAMA_KHIDMAT) AS KETERANGAN FROM TBLRUJKHIDMAT WHERE ID_KHIDMAT IS NOT NULL ORDER BY NAMA_KHIDMAT";
					
					myLogger.info(" SQL TBLRUJKHIDMAT  :"+ sql);
					rs = stmt.executeQuery(sql);
					list_TBLRUJKHIDMAT = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						list_TBLRUJKHIDMAT.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return list_TBLRUJKHIDMAT;

			}
			
			//list tblrujGRED
			@SuppressWarnings("unchecked")
			public List list_TBLRUJGRED(HttpSession session, String ID_GRED)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List list_TBLRUJGRED = null;
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					
					
						sql = " SELECT ID_GRED AS ID, UPPER(NAMA_GRED) AS KETERANGAN FROM TBLRUJGRED WHERE ID_GRED IS NOT NULL ORDER BY NAMA_GRED";
					
					myLogger.info(" SQL TBLRUJKHIDMAT  :"+ sql);
					rs = stmt.executeQuery(sql);
					list_TBLRUJGRED = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						h.put("BIL",bil);
						h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						list_TBLRUJGRED.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}

				return list_TBLRUJGRED;

			}
			
			//delete gred
			
			public void deleteGred(HttpSession session,String ID_GRED) throws Exception {
				Connection conn = null;
				Db db = null;
				String sql = "";
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					myLogger.info("Masuk delete GRED == "+ ID_GRED);
					
				
						r.add("ID_GRED", ID_GRED);
						sql = r.getSQLDelete("TBLRUJGRED");
						AuditTrail.logActivity(this,session,"DEL","GRED ["+ID_GRED+"] Deleted");
				
					
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
			    	throw new Exception("Ralat Delete Gred :"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
			}

			
			//Setup paging
			public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
				try {
					setupPageDefaultPut();
					String scrolPosition = getParam("scrolPosition"+command);
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
						myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
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
}
		
