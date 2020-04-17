package ekptg.view.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;

	@SuppressWarnings("serial")
	public class SpatialAdmin extends AjaxBasedModule {
		
		static Logger myLogger = Logger.getLogger(ekptg.view.admin.SpatialAdmin.class);
		String skrin_name = "app/admin/Spatial/index.jsp";
		
		@Override
		public String doTemplate2() throws Exception {

			List listNegeri = null;
			List listDaerah = null;
			List listMukim = null;
			List printKodUPI = null;
			List statNegeri = null;
			List listSeksyen = null;
			
			Hashtable detailDaerah = null;
			Hashtable detailMukim = null;
			Hashtable detailsNegeri = null;
			Hashtable detailSeksyen = null;
			
			List list_TBLRUJNEGERI = null;
			List list_TBLRUJDAERAH = null;
			List list_TBLRUJMUKIM = null;
			List list_TBLRUJSEKSYEN = null;
			
			//initialize
			this.context.put("cetakReport", "");
			this.context.put("FlagCetak", "");
			
			this.context.put("carianNegeri", "");
			this.context.put("list_TBLRUJNEGERI","");
			this.context.put("list_TBLRUJDAERAH","");
			this.context.put("list_TBLRUJMUKIM","");
			this.context.put("list_TBLRUJSEKSYEN","");
			
			this.context.put("PrintlistUPI","");
			this.context.put("ID_DAERAH","");
			this.context.put("ID_NEGERI", "");
			this.context.put("ID_MUKIM", "");
			this.context.put("ID_SEKSYEN", "");
			
			this.context.put("listMukim","");
			this.context.put("listDaerah","");
			this.context.put("printKodUPI", "");
			this.context.put("statNegeri", "");
			this.context.put("listSeksyen","");
			this.context.put("listNegeri","");
			
			this.context.put("carianDaerah", "");
			this.context.put("carianMukim", "");
			this.context.put("carianUmum", "");
			this.context.put("carianSeksyen", "");
			
			this.context.put("NAMA_NEGERI", "");
			this.context.put("NAMA_DAERAH", "");
			this.context.put("NAMA_MUKIM", "");
			this.context.put("NAMA_SEKSYEN", "");
			
			HttpSession session = this.request.getSession();
			String command = getParam("command");
			
			String carianUmum = "";
			String cetakReport = "";
			this.context.put("cetakReport", cetakReport);
			
			String FlagCetak = "";
			this.context.put("FlagCetak", FlagCetak);
			
			String buttonM = "";
			this.context.put("buttonM", buttonM);
			
			
			String FlagCari = getParam("FlagCari");
			this.context.put("FlagCari", FlagCari);
			
			myLogger.info("Page Spatial command : "+command +" FlagCari : "+FlagCari);
			
			String action = getParam("action");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.context.put("tarikhPrint", sdf.format(new Date()));
			
			
			if(command.equals("batalCarianBahagian"))
			{
				
				skrin_name = "app/admin/Spatial/indexBahagian.jsp";
			}
			else if(command.equals("batalBahagian"))
			{
				
				skrin_name = "app/admin/Spatial/blankPage.jsp";
			}
			else if(command.equals("showCT")){
				skrin_name = "app/admin/Spatial/CarianSpatial.jsp";
			}
			else if(command.equals("showCarianUmum"))
			{
				
				FlagCari = getParam("FlagCari");
				this.context.put("FlagCari", FlagCari);
				myLogger.info("FlagCari : "+FlagCari);
				
				 carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				myLogger.info("carianUmum : "+carianUmum);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				
				/*String carianNegeri = getParam("Form_id_negeri");
				this.context.put("carianNegeri", carianNegeri);
				
				String carianDaerah = getParam("carianDaerah");
				this.context.put("carianDaerah", carianDaerah);
				
				String carianMukim = getParam("carianMukim");
				this.context.put("carianMukim", carianMukim);
				
				if (!carianUmum.equals("") && getParam("cetakReport").equals("Y")){
					myLogger.info("sini 1");
					listNegeri = getSenaraiNegeri(session, carianUmum, carianNegeri, carianDaerah, carianMukim);
					setupPageList(session, action, listNegeri, "listNegeri",command,"SenaraiNegeri");
					
				}else {*/
					myLogger.info("sini 2");
					listNegeri = getSenaraiNegeri(session, carianUmum, "", "", "");
					//setupPageList(session, action, listNegeri, "listNegeri",command,"div_SenaraiNegeri");
					this.context.put("PrintlistUPI",listNegeri);
				//}
				
				//skrin_name = "app/admin/Spatial/SenaraiNegeri.jsp";
				skrin_name = "app/admin/Spatial/SenaraiUtama2.jsp";
			}
			else if(command.equals("editNegeri"))
			{
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				detailsNegeri = getDetailsNegeri(session, ID_NEGERI);
				this.context.put("detailsNegeri", detailsNegeri);
				
				 skrin_name = "app/admin/Spatial/AddNegeri.jsp";
			}
			else if(command.equals("showSenaraiNegeri"))
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
				
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				myLogger.info("carianUmum : "+carianUmum);
				
				this.context.put("FlagCari", FlagCari);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				
				String carianNegeri = getParam("Form_id_negeri");
				this.context.put("carianNegeri", carianNegeri);
				myLogger.info("carianNegeri : "+ carianNegeri);
				
				String carianDaerah = getParam("carianDaerah");
				this.context.put("carianDaerah", carianDaerah);
				
				String carianMukim = getParam("carianMukim");
				this.context.put("carianMukim", carianMukim);
				
				String carianSeksyen = getParam("carianSeksyen");
				this.context.put("carianSeksyen", carianSeksyen);
				
				/*if (getParam("cetakReport").equals("Y") && getParam("FlagCetak").equals("Y")){
				listNegeri = getSenaraiNegeri(session, carianNegeri);
				//setupPageList(session, action, listNegeri, "listNegeri",command,"div_SenaraiNegeri");
				this.context.put("PrintlistUPI",listNegeri);
				
				}else*/
				if (carianNegeri != null && getParam("cetakReport").equals("Y")){
					myLogger.info("sini ct");
				listNegeri = getSenaraiNegeri(session, carianUmum, carianNegeri, carianDaerah, carianMukim);
				setupPageList(session, action, listNegeri, "listNegeri",command,"div_senaraiUtama");
				this.context.put("PrintlistUPI",listNegeri);
				
				}else {
				listNegeri = getSenaraiNegeri(session, carianUmum, carianNegeri, carianDaerah, carianMukim);
				//setupPageList(session, action, listNegeri, "listNegeri",command,"div_SenaraiNegeri");
				this.context.put("PrintlistUPI",listNegeri);
				}
				
				/*if(getParam("FlagCetak").equals("Y")){
					
					myLogger.info("MASUK CETAK negeri -- ");
					skrin_name = "app/admin/Spatial/SenaraiPrintByNegeri.jsp";
				}else{*/
					myLogger.info("MASUK not CETAK negeri -- ");
					skrin_name = "app/admin/Spatial/SenaraiNegeri.jsp";
				//}
	 
			}
			
			else if(command.equals("saveNegeri"))
			{
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				if(ID_NEGERI==null || ID_NEGERI.equals("") || ID_NEGERI.equals("null")){
					//insert
					System.out.println("ID_NEGERI insert? -- "+ID_NEGERI);
					ID_NEGERI = saveNegeri(session, ID_NEGERI);
					System.out.println("ID_NEGERI after insert? -- "+ID_NEGERI);
					
				}else {
					//update
					System.out.println("ID_NEGERI update? -- "+ID_NEGERI);
					ID_NEGERI = saveNegeri(session, ID_NEGERI);
					System.out.println("ID_NEGERI after update? -- "+ID_NEGERI);
					
				}
				
				listNegeri = getSenaraiNegeri(session, "", ID_NEGERI, "", "");
				//setupPageList(session, action, listNegeri, "listNegeri",command,"div_SenaraiNegeri");
				this.context.put("PrintlistUPI",listNegeri);
				
				 skrin_name = "app/admin/Spatial/SenaraiDaerah.jsp";
			}
			
			else if(command.equals("showListDaerah"))
			{
				String ID_NEGERI = getParam("ID_NEGERI");
				//myLog.info("ID_NEGERI : "+ID_NEGERI);
				
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAHBYNEGERI",ID_NEGERI,"0");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				skrin_name = "app/admin/Spatial/showListDaerah.jsp";
			}
			
			else if(command.equals("showListMukim"))
			{
				String ID_DAERAH = getParam("ID_DAERAH");
				//myLog.info("ID_NEGERI : "+ID_NEGERI);
				
				list_TBLRUJMUKIM = listTableRujukanV3(session,"TBLRUJMUKIMBYDAERAH","",ID_DAERAH);
				this.context.put("list_TBLRUJMUKIM",list_TBLRUJMUKIM);
				
				skrin_name = "app/admin/Spatial/showListMukim.jsp";
			}
			
			else if(command.equals("showListSeksyen"))
			{
				String ID_MUKIM = getParam("ID_MUKIM");
				//myLog.info("ID_NEGERI : "+ID_NEGERI);
				
				list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYENBYMUKIM","",ID_MUKIM);
				this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
				
				skrin_name = "app/admin/Spatial/showListSeksyen.jsp";
			}
			
			else if(command.equals("showSenaraiDaerah"))
			{
				
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				myLogger.info("ID_NEGERI D : "+ID_NEGERI);
				
				String NAMA_NEGERI = getParam("NAMA_NEGERI");
				this.context.put("NAMA_NEGERI", NAMA_NEGERI);
				myLogger.info("NAMA_NEGERI D : "+NAMA_NEGERI);
				
				String NAMA_DAERAH = getParam("NAMA_DAERAH");
				//this.context.put("NAMA_DAERAH", NAMA_DAERAH);
				myLogger.info("NAMA_DAERAH D : "+NAMA_DAERAH);
				
				String NAMA_MUKIM = getParam("NAMA_MUKIM");
				//this.context.put("NAMA_MUKIM", NAMA_MUKIM);
				myLogger.info("NAMA_MUKIM D : "+NAMA_MUKIM);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				myLogger.info("cetakReport D : "+cetakReport);
				
				FlagCetak = getParam("FlagCetak");
				this.context.put("FlagCetak", FlagCetak);
				myLogger.info("FlagCetak D : "+FlagCetak);
				
				 carianUmum = getParam("carianUmum");
				//this.context.put("NAMA_MUKIM", NAMA_MUKIM);
				myLogger.info("carianUmum D : "+carianUmum);
				
				/*if (cetakReport.equals("Y") && FlagCetak.equals("Y")){
					myLogger.info("MASUK CETAK D -- ");
				listDaerah = getSenaraiDaerah(session, ID_NEGERI);
				//setupPageListSmall(session, action, listDaerah, "listDaerah",command,"div_viewDaerahByNegeri");
				this.context.put("listDaerah",listDaerah);
				}
				else */
				if (cetakReport.equals("Y")){
					myLogger.info("MASUK NOT CETAK D -- ");
				listDaerah = getSenaraiDaerah(session, ID_NEGERI, NAMA_DAERAH, NAMA_MUKIM, carianUmum);
				//setupPageListSmall(session, action, listDaerah, "listDaerah",command,"div_viewDaerahByNegeri");
				this.context.put("listDaerah",listDaerah);
				}
				else {
					myLogger.info("MASUK CETAK else D -- ");
				listDaerah = getSenaraiDaerah(session, ID_NEGERI, NAMA_DAERAH, NAMA_MUKIM, carianUmum);
				//setupPageListSmall(session, action, listDaerah, "listDaerah",command,"div_viewDaerahByNegeri");
				this.context.put("listDaerah",listDaerah);
				}

				 skrin_name = "app/admin/Spatial/SenaraiDaerah.jsp";
			}
			else if(command.equals("showSenaraiMukim"))
			{
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				this.context.put("ID_DAERAH",ID_DAERAH);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				myLogger.info("cetakReport M : "+cetakReport);
				
				/*String FlagCetak = getParam("FlagCetak");
				this.context.put("FlagCetak", FlagCetak);
				myLogger.info("FlagCetak M : "+FlagCetak);*/
				
				buttonM = getParam("buttonM");
				this.context.put("buttonM", buttonM);
				myLogger.info("buttonM M : "+buttonM);
				
				String NAMA_DAERAH = getParam("NAMA_DAERAH");
				this.context.put("NAMA_DAERAH", NAMA_DAERAH);
				myLogger.info("NAMA_DAERAH M : "+NAMA_DAERAH);
				
				String NAMA_MUKIM = getParam("NAMA_MUKIM");
				//this.context.put("NAMA_MUKIM", NAMA_MUKIM);
				myLogger.info("NAMA_MUKIM M : "+NAMA_MUKIM);
				
				 carianUmum = getParam("carianUmum");
				//this.context.put("NAMA_MUKIM", NAMA_MUKIM);
				myLogger.info("carianUmum M : "+carianUmum);
				
				/*if (cetakReport.equals("Y") && buttonM.equals("Y")){
					myLogger.info("MASUK CETAK M -- ");
				listMukim = getSenaraiMukim(session, ID_DAERAH);
				//setupPageListSmall(session, action, listMukim, "listMukim",command,"div_viewMukimByDaerah"+ID_DAERAH);
				this.context.put("listMukim",listMukim);
				}
				else */
				//if (!ID_DAERAH.equals("") ){
				if (cetakReport.equals("Y") && buttonM.equals("Y")){
					myLogger.info("MASUK NOT CETAK M -- ");
				listMukim = getSenaraiMukim(session, ID_DAERAH, NAMA_MUKIM, carianUmum);
				setupPageListSmall(session, action, listMukim, "listMukim",command,"div_viewMukimByDaerahPrint"+ID_DAERAH);
				this.context.put("listMukim",listMukim);
				}
				else {
					myLogger.info("MASUK CETAK else M -- ");
				listMukim = getSenaraiMukim(session, ID_DAERAH, NAMA_MUKIM, carianUmum);
				//setupPageListSmall(session, action, listMukim, "listMukim",command,"div_viewMukimByDaerah"+ID_DAERAH);
				this.context.put("listMukim",listMukim);
				}
				//}
				
				 skrin_name = "app/admin/Spatial/SenaraiMukim.jsp";
			}
			
			else if(command.equals("showSenaraiSeksyen"))
			{
				String ID_MUKIM = getParam("ID_MUKIM");
				myLogger.info("ID_MUKIM -- " + ID_MUKIM);
				this.context.put("ID_MUKIM",ID_MUKIM);
				
				String NAMA_MUKIM = getParam("NAMA_MUKIM");
				myLogger.info("NAMA_MUKIM -- " + NAMA_MUKIM);
				this.context.put("NAMA_MUKIM",NAMA_MUKIM);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				myLogger.info("cetakReport M : "+cetakReport);
				
				/*String FlagCetak = getParam("FlagCetak");
				this.context.put("FlagCetak", FlagCetak);
				myLogger.info("FlagCetak M : "+FlagCetak);*/
				
				buttonM = getParam("buttonM");
				this.context.put("buttonM", buttonM);
				myLogger.info("buttonM M : "+buttonM);
				
				String NAMA_SEKSYEN = getParam("NAMA_SEKSYEN");
				//this.context.put("NAMA_MUKIM", NAMA_MUKIM);
				myLogger.info("NAMA_SEKSYEN S : "+NAMA_SEKSYEN);
				
				 carianUmum = getParam("carianUmum");
				//this.context.put("NAMA_MUKIM", NAMA_MUKIM);
				myLogger.info("carianUmum S : "+carianUmum);
				
				/*if (cetakReport.equals("Y") && buttonM.equals("Y")){
					myLogger.info("MASUK CETAK M -- ");
				listMukim = getSenaraiMukim(session, ID_DAERAH);
				//setupPageListSmall(session, action, listMukim, "listMukim",command,"div_viewMukimByDaerah"+ID_DAERAH);
				this.context.put("listMukim",listMukim);
				}
				else */
				if (cetakReport.equals("Y") && buttonM.equals("Y")){
					myLogger.info("MASUK NOT CETAK M -- ");
				listSeksyen = getSenaraiSeksyen(session,ID_MUKIM, NAMA_SEKSYEN, carianUmum);
				setupPageListSmall(session, action, listSeksyen, "listSeksyen",command,"div_viewSeksyenByMukimPrint"+ID_MUKIM);
				this.context.put("listSeksyen",listSeksyen);
				}
				else {
					myLogger.info("MASUK CETAK else M -- ");
				listSeksyen = getSenaraiSeksyen(session,ID_MUKIM, NAMA_SEKSYEN, carianUmum);
				//setupPageListSmall(session, action, listSeksyen, "listSeksyen",command,"div_viewSeksyenByMukimPrint"+ID_MUKIM);
				this.context.put("listSeksyen",listSeksyen);
				}
				
				 skrin_name = "app/admin/Spatial/SenaraiSeksyen.jsp";
			}
			
			else if(command.equals("viewDaerah"))
			{
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				detailDaerah = getDetailsDaerah(session, ID_DAERAH);
				this.context.put("detailDaerah", detailDaerah);
				
				 skrin_name = "app/admin/Spatial/ViewDaerah.jsp";
			}
			else if(command.equals("editDaerah"))
			{
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",ID_NEGERI,"");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);	
				
				detailDaerah = getDetailsDaerah(session, ID_DAERAH);
				this.context.put("detailDaerah", detailDaerah);
				
				 skrin_name = "app/admin/Spatial/AddDaerah.jsp";
			}
			else if(command.equals("checkKodJenisPejabat"))
			{			
				String ID_NEGERI = getParam("ID_NEGERI");
				//myLog.info("ID_PEJABAT ---- : "+ID_PEJABAT );
				
				String KOD_JENIS_PEJABAT = getParam("KOD_DAERAH_"+ID_NEGERI);
				//myLog.info("KOD_JENIS_PEJABAT ---- : "+KOD_JENIS_PEJABAT );
				
				boolean checkKod = checkKodJenisPjbt(session, KOD_JENIS_PEJABAT, ID_NEGERI);

				String mesej = "";
					if(checkKod==false)
					{
						mesej = "<font color='red' class='blink' >Kod Daerah Telah Wujud!Sila Pilih Kod Lain.</font>";
					}else {
						mesej = "<font color='black' class='blink' >Kod Daerah Belum Wujud!</font>";	
					}
					
					this.context.put("mesej", mesej);
					this.context.put("checkKod", checkKod);
					
					skrin_name = "app/admin/Utilities/checkKodJenisPejabat.jsp";
			}
			else if(command.equals("checkKodUPI"))
			{			
				String ID_NEGERI = getParam("ID_NEGERI");
				//myLog.info("ID_PEJABAT ---- : "+ID_PEJABAT );
				
				String KOD_UPI = getParam("KOD_DAERAH_UPI_"+ID_NEGERI);
				//myLog.info("KOD_JENIS_PEJABAT ---- : "+KOD_JENIS_PEJABAT );
				
				boolean checkKodUPI = checkKodUPI(session, KOD_UPI, ID_NEGERI);

				String mesejUPI = "";
					if(checkKodUPI==false)
					{
						mesejUPI = "<font color='red' class='blink' >Kod UPI Daerah Telah Wujud!Sila Pilih Kod Lain.</font>";
					}else {
						mesejUPI = "<font color='black' class='blink' >Kod UPI Daerah Belum Wujud!</font>";	
					}
					
					this.context.put("mesejUPI", mesejUPI);
					this.context.put("checkKodUPI", checkKodUPI);
					
					skrin_name = "app/admin/Spatial/checkKodUPI.jsp";
			}
			else if(command.equals("saveDaerah"))
			{
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				
				if(ID_DAERAH==null || ID_DAERAH.equals("") || ID_DAERAH.equals("null")){
					//insert
					System.out.println("ID_DAERAH insert? -- "+ID_DAERAH);
					ID_DAERAH = saveDaerah(session, ID_DAERAH, ID_NEGERI);
					System.out.println("ID_DAERAH after insert? -- "+ID_DAERAH);
					
				}else {
					//update
					System.out.println("ID_DAERAH update? -- "+ID_DAERAH);
					ID_DAERAH = saveDaerah(session, ID_DAERAH, ID_NEGERI);
					System.out.println("ID_DAERAH after update? -- "+ID_DAERAH);
					
				}
				
				detailDaerah = getDetailsDaerah(session, ID_DAERAH);
				this.context.put("detailDaerah", detailDaerah);
				
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				 skrin_name = "app/admin/Spatial/ViewDaerah.jsp";
			}
			else if(command.equals("viewMukim"))
			{
				String ID_MUKIM = getParam("ID_MUKIM");
				myLogger.info("ID_MUKIM -- " + ID_MUKIM);
				this.context.put("ID_MUKIM", ID_MUKIM);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				detailMukim = getDetailsMukim(session, ID_DAERAH, ID_MUKIM);
				this.context.put("detailMukim",detailMukim); 
				
				 skrin_name = "app/admin/Spatial/ViewMukim.jsp";
			}
			else if(command.equals("editMukim"))
			{
				String ID_MUKIM = getParam("ID_MUKIM");
				myLogger.info("ID_MUKIM -- " + ID_MUKIM);
				this.context.put("ID_MUKIM", ID_MUKIM);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",ID_NEGERI,"");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				if (!ID_NEGERI.equals("")){
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAHBYNEGERI",ID_NEGERI,"");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				} else {
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",ID_NEGERI,"");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);	
				}
				
				detailMukim = getDetailsMukim(session, ID_DAERAH, ID_MUKIM);
				this.context.put("detailMukim",detailMukim); 
				
				 skrin_name = "app/admin/Spatial/AddMukim.jsp";
			}
			else if(command.equals("saveMukim"))
			{
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				String ID_MUKIM = getParam("ID_MUKIM");
				
				if(ID_MUKIM==null || ID_MUKIM.equals("") || ID_MUKIM.equals("null")){
					//insert
					System.out.println("ID_MUKIM insert? -- "+ID_MUKIM);
					ID_MUKIM = saveMukim(session, ID_DAERAH, ID_MUKIM);
					System.out.println("ID_MUKIM after insert? -- "+ID_MUKIM);
					
				}else {
					//update
					System.out.println("ID_MUKIM update? -- "+ID_MUKIM);
					ID_MUKIM = saveMukim(session, ID_DAERAH, ID_MUKIM);
					System.out.println("ID_MUKIM after update? -- "+ID_MUKIM);
				}
				
				detailMukim = getDetailsMukim(session, ID_DAERAH, ID_MUKIM);
				this.context.put("detailMukim",detailMukim); 
				
				 skrin_name = "app/admin/Spatial/ViewMukim.jsp";
			}
			else if(command.equals("checkKodMukim"))
			{			
				String ID_DAERAH = getParam("ID_DAERAH");
				//myLog.info("ID_PEJABAT ---- : "+ID_PEJABAT );
				
				String KOD_JENIS_PEJABAT = getParam("KOD_MUKIM_"+ID_DAERAH);
				//myLog.info("KOD_JENIS_PEJABAT ---- : "+KOD_JENIS_PEJABAT );
				
				boolean checkKod = checkKodMukim(session, KOD_JENIS_PEJABAT, ID_DAERAH);

				String mesej = "";
					if(checkKod==false)
					{
						mesej = "<font color='red' class='blink' >Kod Mukim Telah Wujud!Sila Pilih Kod Lain.</font>";
					}else {
						mesej = "<font color='black' class='blink' >Kod Mukim Belum Wujud!</font>";	
					}
					
					this.context.put("mesej", mesej);
					this.context.put("checkKod", checkKod);
					
					skrin_name = "app/admin/Utilities/checkKodJenisPejabat.jsp";
			}
			else if(command.equals("cetakListKod"))
			{
				String ID_NEGERI = getParam("ID_NEGERI");
				myLogger.info("ID_NEGERI ---- : "+ID_NEGERI );
				
				printKodUPI = getListPrintKod(session, ID_NEGERI);
				this.context.put("printKodUPI",printKodUPI);
				
				statNegeri =  getTotalRekod(session);
				this.context.put("statNegeri",statNegeri);
				
				skrin_name = "app/admin/Spatial/PrintListKodUPI.jsp";
			}
			
			else if(command.equals("viewSeksyen"))
			{
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				myLogger.info("ID_SEKSYEN -- " + ID_SEKSYEN);
				this.context.put("ID_SEKSYEN", ID_SEKSYEN);
				
				/*String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);*/
				
				detailSeksyen = getDetailsSeksyen(session, ID_SEKSYEN);
				this.context.put("detailSeksyen", detailSeksyen);
				
				 skrin_name = "app/admin/Spatial/ViewSeksyen.jsp";
			}
			
			else if(command.equals("editSeksyen"))
			{
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				myLogger.info("ID_SEKSYEN -- " + ID_SEKSYEN);
				this.context.put("ID_SEKSYEN", ID_SEKSYEN);
				
				String ID_MUKIM = getParam("ID_MUKIM");
				myLogger.info("ID_MUKIM -- " + ID_MUKIM);
				this.context.put("ID_MUKIM", ID_MUKIM);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLogger.info("ID_DAERAH -- " + ID_DAERAH);
				this.context.put("ID_DAERAH", ID_DAERAH);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",ID_NEGERI,"");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				if (!ID_NEGERI.equals("")){
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAHBYNEGERI",ID_NEGERI,"");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				} else {
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",ID_NEGERI,"");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);	
				}
				
				if (!ID_DAERAH.equals("")){
				list_TBLRUJMUKIM = listTableRujukanV3(session,"TBLRUJMUKIMBYDAERAH","",ID_DAERAH);
				this.context.put("list_TBLRUJMUKIM",list_TBLRUJMUKIM);
				} else {
				list_TBLRUJMUKIM = listTableRujukanV3(session,"TBLRUJMUKIM",ID_DAERAH,"");
				this.context.put("list_TBLRUJMUKIM",list_TBLRUJMUKIM);	
				}
				
				detailSeksyen = getDetailsSeksyen(session, ID_SEKSYEN);
				this.context.put("detailSeksyen",detailSeksyen); 
				
				 skrin_name = "app/admin/Spatial/AddSeksyen.jsp";
			}
			
			else if(command.equals("saveSeksyen"))
			{
				
				String ID_MUKIM = getParam("ID_MUKIM");
				this.context.put("ID_MUKIM",ID_MUKIM);
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN",ID_SEKSYEN);
				
				if(ID_SEKSYEN==null || ID_SEKSYEN.equals("") || ID_SEKSYEN.equals("null")){
					//insert
					System.out.println("ID_SEKSYEN insert? -- "+ID_SEKSYEN);
					ID_SEKSYEN = saveSeksyen(session, ID_MUKIM, ID_SEKSYEN);
					System.out.println("ID_SEKSYEN after insert? -- "+ID_SEKSYEN);
					
				}else {
					//update
					System.out.println("ID_SEKSYEN update? -- "+ID_SEKSYEN);
					ID_SEKSYEN = saveSeksyen(session, ID_MUKIM, ID_SEKSYEN);
					System.out.println("ID_SEKSYEN after update? -- "+ID_SEKSYEN);
				}
				
				detailSeksyen = getDetailsSeksyen(session, ID_SEKSYEN);
				this.context.put("detailSeksyen",detailSeksyen); 
				
				 skrin_name = "app/admin/Spatial/ViewSeksyen.jsp";
			}
			
			else 
			{
				listNegeri = getSenaraiNegeri(session, "","","","");
				this.context.put("listNegeri",listNegeri);
				
				myLogger.info("MASUK not CETAK negeri else -- ");
				
				skrin_name = "app/admin/Spatial/index.jsp";
			}
			myLogger.info("skrin_name : "+skrin_name );
			return skrin_name;	
		
			
		}
		
		@SuppressWarnings("unchecked")
		public List getTotalRekod(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listStatNegeri = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = 	" SELECT V.NEGERI, SUM (V.JUMLAH_DAERAH) AS JUMLAH_DAERAH, SUM(V.JUMLAH_MUKIM) AS JUMLAH_MUKIM FROM (";
				
				sql+= 	" SELECT 1 AS LAYER, COUNT (D.NAMA_DAERAH) AS JUMLAH_DAERAH, 0 AS JUMLAH_MUKIM, N.ID_NEGERI, " +
						" D.ID_DAERAH AS ID_DAERAH, N.NAMA_NEGERI AS NEGERI FROM   TBLRUJNEGERI N, TBLRUJDAERAH D " +
						" WHERE N.ID_NEGERI = D.ID_NEGERI AND N.ID_NEGERI != 0 AND N.ID_NEGERI != 17 " +
						" GROUP BY   N.ID_NEGERI, N.NAMA_NEGERI, D.ID_DAERAH ";

				sql+=	" UNION ";

				sql+=	" SELECT 2 AS LAYER, 0 AS JUMLAH_DAERAH, COUNT (M.NAMA_MUKIM) AS JUMLAH_MUKIM, N.ID_NEGERI, " +
						" D.ID_DAERAH AS ID_DAERAH, N.NAMA_NEGERI AS NEGERI FROM   TBLRUJNEGERI N, TBLRUJDAERAH D, TBLRUJMUKIM M " +
						" WHERE M.ID_DAERAH = D.ID_DAERAH AND N.ID_NEGERI = D.ID_NEGERI AND N.ID_NEGERI != 0 " +
						" GROUP BY N.ID_NEGERI, N.NAMA_NEGERI, D.ID_DAERAH " +
						" ORDER BY ID_NEGERI, JUMLAH_DAERAH, ID_DAERAH, JUMLAH_MUKIM ASC) V ";
				
				sql+= 	" GROUP BY ID_NEGERI, NEGERI ORDER BY ID_NEGERI ASC ";
				
				myLogger.debug("SQL LIST STATISTIK - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
					listStatNegeri = Collections.synchronizedList(new ArrayList());
						
				
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
							h.put("NEGERI",checkIsNull(rs.getString("NEGERI")));
							h.put("JUMLAH_DAERAH",checkIsNull(rs.getString("JUMLAH_DAERAH")));
							h.put("JUMLAH_MUKIM",checkIsNull(rs.getString("JUMLAH_MUKIM")));
							
							listStatNegeri.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listStatNegeri;
					
		}
		
		public String saveSeksyen(HttpSession session, String ID_MUKIM, String ID_SEKSYEN) throws Exception {
			
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_SEKSYEN_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String ID_MUKIM2 = getParam("ID_MUKIM_"+ID_SEKSYEN);
				String NAMA_SEKSYEN = getParam("NAMA_SEKSYEN_"+ID_SEKSYEN);
				String KOD_SEKSYEN = getParam("KOD_SEKSYEN_UPI_"+ID_SEKSYEN);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_SEKSYEN);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r.add("NAMA_SEKSYENUPI", NAMA_SEKSYEN.toUpperCase());
				r.add("KOD_SEKSYENUPI", KOD_SEKSYEN.toUpperCase());
				r.add("ID_MUKIM", ID_MUKIM2);
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				if(ID_SEKSYEN.equals("")){
					
				ID_SEKSYEN_INSERT = DB.getNextID(db,"TBLRUJSEKSYENUPI_SEQ");
				ID_SEKSYEN = ID_SEKSYEN_INSERT+"";
			
				r.add("ID_SEKSYENUPI", ID_SEKSYEN);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJSEKSYENUPI");	
				
				}
				else{
				
				r.update("ID_SEKSYENUPI", ID_SEKSYEN);
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJSEKSYENUPI");	
				
				}
				
				myLogger.info("ACTION TBLRUJSEKSYENUPI : "+sql.toUpperCase());
				
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
		    	throw new Exception("Ralat action save TBLRUJSEKSYENUPI :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_SEKSYEN;
		}
		
		public String saveMukim(HttpSession session, String ID_DAERAH, String ID_MUKIM) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_MUKIM_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String NAMA_MUKIM = getParam("NAMA_MUKIM_"+ID_MUKIM);
				String KOD_MUKIM = getParam("KOD_MUKIM_"+ID_MUKIM);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_MUKIM);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r.add("NAMA_MUKIM", NAMA_MUKIM.toUpperCase());
				r.add("KOD_MUKIM", KOD_MUKIM.toUpperCase());
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				if(ID_MUKIM.equals("")){
					
				myLogger.info("MASUK INSERT TBLRUJMUKIM : "+ID_MUKIM);
					
				ID_MUKIM_INSERT = DB.getNextID(db,"TBLRUJMUKIM_SEQ");
				ID_MUKIM = ID_MUKIM_INSERT+"";
			
				r.add("ID_MUKIM", ID_MUKIM);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJMUKIM");	
				
				}
				else{
				r.update("ID_MUKIM", ID_MUKIM);	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJMUKIM");	
				
				}
				
				myLogger.info("ACTION TBLRUJMUKIM : "+sql.toUpperCase());
				
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
		    	throw new Exception("Ralat action save MUKIM :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_MUKIM;
		}
		
		
		public String saveNegeri(HttpSession session, String ID_NEGERI) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_NEGERI_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String NAMA_NEGERI = getParam("NAMA_NEGERI_"+ID_NEGERI);
				String KOD_NEGERI = getParam("KOD_NEGERI_"+ID_NEGERI);
			//	String KOD_NEGERI_UPI = getParam("KOD_NEGERI_UPI_"+ID_NEGERI);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r.add("NAMA_NEGERI", NAMA_NEGERI.toUpperCase());
				r.add("KOD_NEGERI", KOD_NEGERI.toUpperCase());
			//	r.add("KOD_NEGERI_UPI", KOD_NEGERI_UPI.toUpperCase());
				r.add("ID_NEGARA", 1);
				
				if(ID_NEGERI.equals("")){
					
				myLogger.info("MASUK INSERT TBLRUJNEGERI : "+ID_NEGERI);
					
				ID_NEGERI_INSERT = DB.getNextID(db,"TBLRUJNEGERI_SEQ");
				ID_NEGERI = ID_NEGERI_INSERT+"";
			
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJNEGERI");	
				
				}
				else{
				r.update("ID_NEGERI", ID_NEGERI);	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJNEGERI");	
				
				}
				
				myLogger.info("ACTION TBLRUJNEGERI : "+sql.toUpperCase());
				
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
		    	throw new Exception("Ralat action save TBLRUJNEGERI :"+se.getMessage());
			}
			
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_NEGERI;
		}
		
		public String saveDaerah(HttpSession session, String ID_DAERAH, String ID_NEGERI) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_DAERAH_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String NAMA_DAERAH = getParam("NAMA_DAERAH_"+ID_DAERAH);
				String KOD_DAERAH = getParam("KOD_DAERAH_"+ID_DAERAH);
				String KOD_DAERAH_UPI = getParam("KOD_DAERAH_UPI_"+ID_DAERAH);
				
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_DAERAH);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r.add("NAMA_DAERAH", NAMA_DAERAH.toUpperCase());
				r.add("KOD_DAERAH", KOD_DAERAH.toUpperCase());
				r.add("KOD_DAERAH_UPI", KOD_DAERAH_UPI.toUpperCase());
				r.add("ID_NEGERI", ID_NEGERI);
				
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				if(ID_DAERAH.equals("")){
					
				myLogger.info("MASUK INSERT TBLRUJDAERAH : "+ID_DAERAH);
					
				ID_DAERAH_INSERT = DB.getNextID(db,"TBLRUJDAERAH_SEQ");
				ID_DAERAH = ID_DAERAH_INSERT+"";
			
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJDAERAH");	
				
				}
				else{
				r.update("ID_DAERAH", ID_DAERAH);	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJDAERAH");	
				
				}
				
				myLogger.info("ACTION TBLRUJDAERAH : "+sql.toUpperCase());
				
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
		    	throw new Exception("Ralat action save DAERAH :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_DAERAH;
		}
		
		public boolean checkKodMukim(HttpSession session, String KOD_JENIS_PEJABAT, String ID_DAERAH) throws Exception {
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
				
					sql = 	" SELECT KOD_MUKIM FROM TBLRUJMUKIM " +
							" WHERE KOD_MUKIM = '"+KOD_JENIS_PEJABAT+"'"+
							" AND ID_DAERAH = "+ ID_DAERAH;
					
					myLogger.info(" checkKodMukim :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						
						KOD_JENIS_PEJABAT = rs.getString("KOD_MUKIM") == null ? "" : rs.getString("KOD_MUKIM");
						if(!KOD_JENIS_PEJABAT.equals(""))
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
		
		public boolean checkKodUPI(HttpSession session, String KOD_UPI, String ID_NEGERI) throws Exception {
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
				
					sql = 	" SELECT KOD_DAERAH_UPI FROM TBLRUJDAERAH " +
							" WHERE KOD_DAERAH = '"+KOD_UPI+"'"+
							" AND ID_NEGERI = "+ ID_NEGERI;
					
					myLogger.info(" checkKodUPI :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						
						KOD_UPI = rs.getString("KOD_DAERAH_UPI") == null ? "" : rs.getString("KOD_DAERAH_UPI");
						if(!KOD_UPI.equals(""))
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
		
		public boolean checkKodJenisPjbt(HttpSession session, String KOD_JENIS_PEJABAT, String ID_NEGERI) throws Exception {
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
				
					sql = 	" SELECT KOD_DAERAH FROM TBLRUJDAERAH " +
							" WHERE KOD_DAERAH = '"+KOD_JENIS_PEJABAT+"'"+
							" AND ID_NEGERI = "+ ID_NEGERI;
					
					myLogger.info(" checkKodDaerah :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						
						KOD_JENIS_PEJABAT = rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH");
						if(!KOD_JENIS_PEJABAT.equals(""))
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
		
		@SuppressWarnings("unchecked")
		public List getSenaraiNegeri(HttpSession session, String carianUmum, String carianNegeri, String carianDaerah, String carianMukim) 
				throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listNegeri = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();

					sql = 	" SELECT N.ID_NEGERI, N.KOD_NEGERI, N.NAMA_NEGERI " +
							" FROM TBLRUJNEGERI N " +
							" WHERE N.KOD_NEGERI <> '0' " +
							" AND N.ID_NEGERI NOT IN (17,0)";
				
				if (!carianUmum.equals("")){
					
					sql +=	" AND ( " +
							" UPPER (N.NAMA_NEGERI) LIKE ('%"+carianUmum.toUpperCase()+"%')"+
							" OR UPPER (N.KOD_NEGERI) LIKE ('%"+carianUmum.toUpperCase()+"%')"+
							" )";
				}
				
				if (!carianNegeri.equals("") && carianNegeri != null){
				
							sql += 	" AND N.ID_NEGERI = "+ carianNegeri;
					}

					myLogger.info("SQL LIST NEGERI - "+sql.toUpperCase());
					
					rs = stmt.executeQuery(sql);

					 listNegeri  = Collections.synchronizedList(new ArrayList());
						Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							//myLogger.debug("BIL 1 LIST NEGERI - "+bil);
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
							//myLogger.debug("BIL LIST NEGERI - "+bil);
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("KOD_NEGERI",checkIsNull(rs.getString("KOD_NEGERI")));
							h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
							
							listNegeri.add(h);
						}
						myLogger.debug("BIL LIST NEGERI - "+bil);
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) 
							db.close();
					}
					return listNegeri;
					
		}
		
		@SuppressWarnings("unchecked")
		public List getSenaraiDaerah(HttpSession session, String ID_NEGERI, String NAMA_DAERAH, String NAMA_MUKIM, String carianUmum) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listDaerah = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
					
					sql = 	" SELECT D.ID_DAERAH, D.KOD_DAERAH, D.NAMA_DAERAH, D.KOD_DAERAH_UPI, D.ID_NEGERI " +
							" FROM TBLRUJDAERAH D, TBLRUJMUKIM M " +
							" WHERE D.ID_NEGERI = "+ ID_NEGERI+
							" AND D.ID_DAERAH != 0 AND D.KOD_DAERAH != 0 " +
							" AND M.ID_DAERAH = D.ID_DAERAH " ;
					
					if (!NAMA_MUKIM.equals("")){
						
					sql += " AND M.NAMA_MUKIM LIKE UPPER ('%"+NAMA_MUKIM.trim()+"%') " ;
					
					if (!NAMA_DAERAH.equals("")){
						
						sql+= 	" AND NAMA_DAERAH LIKE UPPER ('%"+NAMA_DAERAH.trim()+"%') ";
			
						}
							
						sql+= 	" GROUP BY D.ID_DAERAH, D.KOD_DAERAH, D.NAMA_DAERAH, D.KOD_DAERAH_UPI ";
							
							
					} else {
					
					sql = 	" SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH, KOD_DAERAH_UPI, ID_NEGERI " +
							" FROM TBLRUJDAERAH " +
							" WHERE ID_DAERAH != 0 "+
							" AND KOD_DAERAH != 0 ";
					
					if (!ID_NEGERI.equals("")){
						
						sql+= " AND ID_NEGERI = "+ ID_NEGERI ;
						
					}
						if (!NAMA_DAERAH.equals("")){
					
								sql+= 	" AND NAMA_DAERAH LIKE UPPER ('%"+NAMA_DAERAH.trim()+"%') ";
					
						}
						
						if (!carianUmum.equals("")){
							
							sql += " AND NAMA_DAERAH LIKE UPPER ('%"+carianUmum.trim()+"%') ";
						}
					
				}
						myLogger.debug("SQL LIST DAERAH - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listDaerah = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_DAERAH",checkIsNull(rs.getString("ID_DAERAH")));
							h.put("KOD_DAERAH",checkIsNull(rs.getString("KOD_DAERAH")));
							h.put("NAMA_DAERAH",checkIsNull(rs.getString("NAMA_DAERAH")));
							h.put("KOD_DAERAH_UPI",checkIsNull(rs.getString("KOD_DAERAH_UPI")));
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							listDaerah.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listDaerah;
					
		}
		
		
		@SuppressWarnings("unchecked")
		public List getSenaraiMukim(HttpSession session, String ID_DAERAH, String NAMA_MUKIM, String carianUmum) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listMukim = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT ID_MUKIM, KOD_MUKIM, NAMA_MUKIM, ID_DAERAH, KOD_MUKIM_UPI " +
									" FROM TBLRUJMUKIM " +
									" WHERE ID_MUKIM != 0  AND KOD_MUKIM != '00' ";
							
						
						if (!NAMA_MUKIM.equals("")){
								
								sql+= 	" AND NAMA_MUKIM LIKE UPPER ('%"+NAMA_MUKIM.trim()+"%') ";
								
						}
						
						if (!carianUmum.equals("")){
							
							sql+= 	" AND NAMA_MUKIM LIKE UPPER ('%"+carianUmum.trim()+"%') ";
							
					}
						
						if (!ID_DAERAH.equals("")) {
						
							sql+= " AND ID_DAERAH = "+ ID_DAERAH ;
						
						}
						
						myLogger.debug("SQL LIST MUKIM - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listMukim = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_MUKIM",checkIsNull(rs.getString("ID_MUKIM")));
							h.put("KOD_MUKIM",checkIsNull(rs.getString("KOD_MUKIM")));
							h.put("NAMA_MUKIM",checkIsNull(rs.getString("NAMA_MUKIM")));
							h.put("ID_DAERAH",checkIsNull(rs.getString("ID_DAERAH")));
							h.put("KOD_MUKIM_UPI",checkIsNull(rs.getString("KOD_MUKIM_UPI")));
							listMukim.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listMukim;
					
		}
		
		@SuppressWarnings("unchecked")
		public List getSenaraiSeksyen(HttpSession session, String ID_MUKIM, String NAMA_SEKSYEN, String carianUmum) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listMukim = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT ID_SEKSYENUPI, KOD_SEKSYENUPI, NAMA_SEKSYENUPI, FLAG_AKTIF	" +
									" FROM TBLRUJSEKSYENUPI " +
									" WHERE ID_MUKIM != 0 ";
							
						
						if (!NAMA_SEKSYEN.equals("")){
								
								sql+= 	" AND NAMA_SEKSYENUPI LIKE UPPER ('%"+NAMA_SEKSYEN.trim()+"%') ";
								
						}
						
						if (!carianUmum.equals("")){
							
							sql+= 	" AND NAMA_SEKSYENUPI LIKE UPPER ('%"+carianUmum.trim()+"%') ";
							
					}
						
						if (!ID_MUKIM.equals("")) {
						
							sql+= " AND ID_MUKIM = "+ ID_MUKIM ;
						
						}
						
						myLogger.debug("SQL LIST SEKSYEN - "+sql.toUpperCase());
						 rs = stmt.executeQuery(sql);
						
						 listMukim = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYENUPI")));
							h.put("KOD_SEKSYEN",checkIsNull(rs.getString("KOD_SEKSYENUPI")));
							h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYENUPI")));
						//	h.put("KOD_MUKIM_UPI",checkIsNull(rs.getString("KOD_MUKIM_UPI")));
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							listMukim.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listMukim;
					
		}
		
		@SuppressWarnings("unchecked")
		public Hashtable getDetailsNegeri (HttpSession session, String ID_NEGERI) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_NEGERI.equals(""))
				{
					
					sql = 	" SELECT ID_NEGERI, KOD_NEGERI, NAMA_NEGERI " +
							" FROM TBLRUJNEGERI " +
							" WHERE ID_NEGERI = "+ ID_NEGERI;
					
					rs = stmt.executeQuery(sql);				
									
					while (rs.next()) {
						
						h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
						h.put("KOD_NEGERI",checkIsNull(rs.getString("KOD_NEGERI")));
						h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
						//h.put("KOD_DAERAH_UPI",checkIsNull(rs.getString("KOD_DAERAH_UPI")));
						}
				}
				else
				{
					h.put("ID_NEGERI","");
					h.put("KOD_NEGERI","");
					h.put("NAMA_NEGERI","");
				//	h.put("KOD_DAERAH_UPI","");
				}
				
				myLogger.info(" SQL Details NEGERI :" + sql.toUpperCase());
				
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
		public Hashtable getDetailsDaerah (HttpSession session, String ID_DAERAH) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_DAERAH.equals(""))
				{
					
					sql = 	" SELECT ID_DAERAH, KOD_DAERAH, NAMA_DAERAH, KOD_DAERAH_UPI, FLAG_AKTIF " +
							" FROM TBLRUJDAERAH " +
							" WHERE ID_DAERAH = "+ ID_DAERAH;
					
					rs = stmt.executeQuery(sql);				
									
					while (rs.next()) {
						
						h.put("ID_DAERAH",checkIsNull(rs.getString("ID_DAERAH")));
						h.put("KOD_DAERAH",checkIsNull(rs.getString("KOD_DAERAH")));
						h.put("NAMA_DAERAH",checkIsNull(rs.getString("NAMA_DAERAH")));
						h.put("KOD_DAERAH_UPI",checkIsNull(rs.getString("KOD_DAERAH_UPI")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						}
				}
				else
				{
					h.put("ID_DAERAH","");
					h.put("KOD_DAERAH","");
					h.put("NAMA_DAERAH","");
					h.put("KOD_DAERAH_UPI","");
					h.put("FLAG_AKTIF","");
				}
				
				myLogger.info(" SQL Details Daerah :" + sql.toUpperCase());
				
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
		public Hashtable getDetailsMukim (HttpSession session, String ID_DAERAH, String ID_MUKIM) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_MUKIM.equals(""))
				{
					
					sql = 	" SELECT ID_MUKIM, KOD_MUKIM, NAMA_MUKIM, KOD_MUKIM_UPI, FLAG_AKTIF  " +
							" FROM TBLRUJMUKIM " +
							" WHERE ID_MUKIM = "+ ID_MUKIM+
							" AND ID_DAERAH = "+ ID_DAERAH;
					
					rs = stmt.executeQuery(sql);	
									
					while (rs.next()) {
						
						h.put("ID_MUKIM",checkIsNull(rs.getString("ID_MUKIM")));
						h.put("KOD_MUKIM",checkIsNull(rs.getString("KOD_MUKIM")));
						h.put("NAMA_MUKIM",checkIsNull(rs.getString("NAMA_MUKIM")));
						h.put("KOD_MUKIM_UPI",checkIsNull(rs.getString("KOD_MUKIM_UPI")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						
					}
				}
				else
				{
					h.put("ID_MUKIM","");
					h.put("KOD_MUKIM","");
					h.put("KOD_MUKIM_UPI","");
					h.put("NAMA_MUKIM","");
				}
				
				myLogger.info(" SQL Details Mukim :" + sql.toUpperCase());
				
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
		public Hashtable getDetailsSeksyen (HttpSession session, String ID_SEKSYENUPI) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_SEKSYENUPI.equals(""))
				{
					
					sql = 	" SELECT ID_SEKSYENUPI, KOD_SEKSYENUPI, NAMA_SEKSYENUPI, FLAG_AKTIF, ID_MUKIM	" +
							" FROM TBLRUJSEKSYENUPI " +
							" WHERE ID_SEKSYENUPI = "+ ID_SEKSYENUPI ;
					
					rs = stmt.executeQuery(sql);	
									
					while (rs.next()) {
						
						h.put("ID_SEKSYEN",checkIsNull(rs.getString("ID_SEKSYENUPI")));
						h.put("KOD_SEKSYEN",checkIsNull(rs.getString("KOD_SEKSYENUPI")));
						h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYENUPI")));
					//	h.put("KOD_MUKIM_UPI",checkIsNull(rs.getString("KOD_MUKIM_UPI")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						h.put("ID_MUKIM",checkIsNull(rs.getString("ID_MUKIM")));
					}
				}
				else
				{
					h.put("ID_SEKSYEN","");
					h.put("KOD_SEKSYEN","");
					h.put("NAMA_SEKSYEN","");
					h.put("FLAG_AKTIF","");
					h.put("ID_MUKIM","");
				}
				
				myLogger.info(" SQL Details Seksyen :" + sql.toUpperCase());
				
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
		public List getListPrintKod (HttpSession session, String ID_NEGERI) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listKod = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
					sql = 	" SELECT 1 AS LAYER, A.NAMA_NEGERI AS LAYER_1,'' AS LAYER_2, '' AS LAYER_3, A.KOD_NEGERI AS KOD_UPI, " +
							" TO_CHAR(A.ID_NEGERI) AS ID_NEGERI, '' AS ID_DAERAH, '' AS ID_MUKIM " +
							" FROM TBLRUJNEGERI A WHERE A.ID_NEGERI IS NOT NULL " +
							" AND A.KOD_NEGERI != '00' AND A.KOD_NEGERI != '0' " ;
					
					if (!ID_NEGERI.equals("")){
						
						sql += " AND A.ID_NEGERI = "+ID_NEGERI;
					}
					
					
					sql +=	" UNION " ;
							
					sql +=	" SELECT 2 AS LAYER, '' AS LAYER_1, A.NAMA_DAERAH AS LAYER_2, '' AS LAYER_3, A.KOD_DAERAH_UPI AS KOD_UPI, " +
							" TO_CHAR(B.ID_NEGERI) AS ID_NEGERI, TO_CHAR(A.ID_DAERAH) AS ID_DAERAH, '' AS ID_MUKIM " +
							" FROM TBLRUJDAERAH A, TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI " +
							" AND A.ID_DAERAH IS NOT NULL AND A.KOD_DAERAH_UPI != '00' AND A.KOD_DAERAH != '0' " ;
							
					if (!ID_NEGERI.equals("")){
						
						sql += " AND B.ID_NEGERI = "+ID_NEGERI;
					}

					sql +=	" UNION " ;
							
					sql +=	" SELECT 3 AS LAYER, '' AS LAYER_1, '' AS LAYER_2, A.NAMA_MUKIM AS LAYER_3, A.KOD_MUKIM AS KOD_UPI, " +
							" TO_CHAR(C.ID_NEGERI) AS ID_NEGERI, TO_CHAR(B.ID_DAERAH) AS ID_DAERAH, TO_CHAR(A.ID_MUKIM) AS ID_MUKIM " +
							" FROM TBLRUJMUKIM A,TBLRUJDAERAH B,TBLRUJNEGERI C WHERE A.ID_DAERAH = B.ID_DAERAH " +
							" AND B.ID_NEGERI = C.ID_NEGERI AND A.ID_MUKIM IS NOT NULL AND A.KOD_MUKIM != '00' AND A.KOD_MUKIM != '0' " ;
					
					if (!ID_NEGERI.equals("")){
						
						sql += " AND C.ID_NEGERI = "+ID_NEGERI;
					}

					
					sql+=	" ORDER BY ID_NEGERI,LAYER_1,ID_DAERAH,LAYER_2,ID_MUKIM, LAYER_3 ASC ";
						
					myLogger.debug("SQL PRINTLIST - "+sql.toUpperCase());
					
						rs = stmt.executeQuery(sql);
						listKod = Collections.synchronizedList(new ArrayList());
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
							h.put("LAYER",checkIsNull(rs.getString("LAYER")));
							h.put("LAYER_1",checkIsNull(rs.getString("LAYER_1")));
							h.put("LAYER_2",checkIsNull(rs.getString("LAYER_2")));
							h.put("LAYER_3",checkIsNull(rs.getString("LAYER_3")));
							
							h.put("KOD_UPI",checkIsNull(rs.getString("KOD_UPI")));
							
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("ID_DAERAH",checkIsNull(rs.getString("ID_DAERAH")));
							h.put("ID_MUKIM",checkIsNull(rs.getString("ID_MUKIM")));
							
							listKod.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listKod;
					
		}
		
		public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
			try {
				//myLogger.info("command : "+command);
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
					myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
					itemsPerPage = getParam("itemsPerPage"+command) == "" ? 10
							: getParamAsInteger("itemsPerPage"+command);
				} else {
					itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
				}
				
				myLogger.info(" itemsPerPage *** : "+itemsPerPage);
				
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
		
		public void setupPageListSmall(HttpSession session, String action, List list, String namaList, String command, String div) {
			try {
				myLogger.info("command : "+command);
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
					//myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
					itemsPerPage = getParam("itemsPerPage"+command) == "" ? 5
							: getParamAsInteger("itemsPerPage"+command);
				} else {
					itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
				}
				
				//myLogger.info(" itemsPerPage *** : "+itemsPerPage);
				
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
	
		public String checkIsNull(String txt) {
			if (txt == null) return "";
			else return txt;
		}
		
		//LIST TABLE
				@SuppressWarnings("unchecked")
				public List listTableRujukanV3(HttpSession session, String tableRujukan, String ID_NEGERI, String ID_DAERAH )throws Exception {
					Db db = null;
					ResultSet rs = null;
					Statement stmt = null;
					List listTableRujukanV3 = null;
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
							sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN FROM TBLRUJDAERAH " +
									" ORDER BY NAMA_DAERAH, KOD_DAERAH ";
						}
						else if(tableRujukan.equals("TBLRUJMUKIM"))
						{
							sql = "  SELECT ID_MUKIM AS ID, KOD_MUKIM AS KOD, UPPER(NAMA_MUKIM) AS KETERANGAN FROM TBLRUJMUKIM " +
									" ORDER BY NAMA_MUKIM, KOD_MUKIM ";
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
						else if(tableRujukan.equals("TBLRUJMUKIMBYDAERAH"))
						{
							sql = "  SELECT ID_MUKIM AS ID, KOD_MUKIM AS KOD, UPPER(NAMA_MUKIM) AS KETERANGAN FROM TBLRUJMUKIM " ;
							sql += " WHERE ID_DAERAH IS NOT NULL";
							sql += " AND ID_DAERAH = "+ID_DAERAH ;
							sql += " ORDER BY NAMA_MUKIM, KOD_MUKIM ";
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
						listTableRujukanV3 = Collections.synchronizedList(new ArrayList());
						Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							h.put("BIL",bil);
							h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
							h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
							h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
							listTableRujukanV3.add(h);
						}

					} finally {
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db != null)
							db.close();
					}

					return listTableRujukanV3;

				}
	}
