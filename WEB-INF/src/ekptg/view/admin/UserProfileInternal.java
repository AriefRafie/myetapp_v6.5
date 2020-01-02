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
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

public class UserProfileInternal extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(UserProfileInternal.class);
	String skrin_name = "app/admin/MyProfile/index.jsp";
	
	@Override
	public String doTemplate2() throws Exception {
		
		List listPenggunaInternalHQ = null;
		List listPenggunaInternalNegeri = null;
		List listPenggunaOnline = null;
		Hashtable viewPengguna = null;
		Hashtable viewPenggunaExist = null;
		Hashtable viewPejabatJKPTG= null;
		Hashtable viewAlamatKJP= null;
		List listAdditionalRoleByUserLogin = null;
		List listDaerahJagaanByIdPejabat = null;
		Hashtable viewPenggunaKemaskini = null;
		
		
		List list_TBLRUJJAWATAN = null;
		List list_TBLRUJBANGSA = null;
		List list_TBLRUJSEKSYEN = null;
		List list_TBLRUJAGAMA = null;
		List list_TBLRUJNEGERI = null;
		List list_TBLRUJBANDAR = null;
		List list_TBLRUJKEMENTERIAN = null;
		List list_TBLRUJAGENSI = null;
		List listPejabatJKPTG = null;
		List listRole = null;
		List listRoleByUserLogin = null;
		List listPenggunaKJP = null;
		List listPengunaAT = null;
		List listPengunaPLA = null;
		List list_TBLINTRUJJENISPEJABAT = null;
		List list_TBLRUJDAERAH = null;
		List listPejabat = null;
		Hashtable viewPejabat = null;
		Hashtable viewPengunaPLA = null;
		List listPenggunaINT = null;
		
		/*Pengumuman logic = new Pengumuman();
		Vector list_memo_aktif = null;*/
		
		HttpSession session = this.request.getSession();
		
		String command = getParam("command");
		
		String USER_ID = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID", USER_ID);
		
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
		//String USER_LOGIN_ROLE =  (String) session.getAttribute("_portal_role");
		
		String USER_LOGIN_ROLE =  (String) session.getAttribute("myrole");
		this.context.put("USER_LOGIN_ROLE" , USER_LOGIN_ROLE);
		
		//String USER_ID = "";
		
		System.out.println("USER_LOGIN_ROLE -- "+USER_LOGIN_ROLE);
		
		System.out.println("command : "+command);
		
		this.context.put("internalType", "");
		
		 if(command.equals("viewPenggunaInternal"))
		{			
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN_SYSTEM);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);
			
			if (USER_LOGIN_ROLE.equals("online_kjp")){
				viewPengguna = viewDataPenggunaKJP(session,USER_ID,"");
				//this.context.put("viewPengguna", viewPengguna);
			
				skrin_name = "app/admin/MyProfile/viewPenggunaKJP.jsp";
			} 
			
			else if (USER_LOGIN_ROLE.equals("online")){
				viewPengguna = viewDataPenggunaOnline(session, USER_ID, "");
					//this.context.put("viewPengguna", viewPengguna);
				
					skrin_name = "app/admin/MyProfile/viewPenggunaOnline.jsp";
					
			} else if (USER_LOGIN_ROLE.equals("(INTEGRASI)UsersMACGDI")){
				viewPengguna = viewDataPenggunaINT(session, USER_ID, "");
				//this.context.put("viewPengguna", viewPengguna);
			
				skrin_name = "app/admin/MyProfile/viewPenggunaINT.jsp";
				
			} else if (USER_LOGIN_ROLE.equals("(INTEGRASI)UsersLHDN")){
				viewPengguna = viewDataPenggunaINT(session, USER_ID, "");
				//this.context.put("viewPengguna", viewPengguna);
			
				skrin_name = "app/admin/MyProfile/viewPenggunaINT.jsp";
				
			} else if (USER_LOGIN_ROLE.equals("(INTEGRASI)UsersAgensi")){
				viewPengguna = viewDataPenggunaINT(session, USER_ID, "");
				//this.context.put("viewPengguna", viewPengguna);
			
				skrin_name = "app/admin/MyProfile/viewPenggunaINT.jsp";
			
			} else if (USER_LOGIN_ROLE.equals("(MOF)Pengguna")){
				viewPengguna = viewDataPenggunaINT(session, USER_ID, "");
				//this.context.put("viewPengguna", viewPengguna);
			
				skrin_name = "app/admin/MyProfile/viewPenggunaINT.jsp";
				
			} else {
				
				viewPengguna = viewDataPenggunaInternal(session,USER_ID,"");
				skrin_name = "app/admin/MyProfile/UserProfileInternal.jsp";
				}
			
			this.context.put("viewPengguna", viewPengguna);
			
			if (!viewPengguna.get("USER_ID").equals("")) {
				
				System.out.println("masuk user id not null ");
				 USER_ID = (String) viewPengguna.get("USER_ID");
				 this.context.put("USER_ID" , USER_ID);
				 
			}else {
				
				System.out.println("masuk user id null ");
				USER_ID = USER_ID;
				this.context.put("USER_ID" , USER_ID);
				
			}
			
			
		}
		
		else if(command.equals("showDisplayDaerahJagaan"))
		{
			String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
			
			if(!ID_PEJABATJKPTG.equals(""))
			{
				listDaerahJagaanByIdPejabat = listDaerahJagaanByIdPejabat(session, ID_PEJABATJKPTG, ID_JENISPEJABAT);
				this.context.put("listDaerahJagaanByIdPejabat", listDaerahJagaanByIdPejabat);
			}
			else
			{
				this.context.put("listDaerahJagaanByIdPejabat", "");
			}
			skrin_name = "app/admin/MyProfile/showDisplayDaerahJagaan.jsp";
		}	
		//kemaskini
		else if(command.equals("edit_PenggunaInternal"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			
			//viewPengguna = viewDataPenggunaInternal(session,USER_ID,"");	
			viewPengguna = viewDataPenggunaInternal(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);
			
			list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","","",internalType);
			this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
			list_TBLRUJBANGSA = listTableRujukanV3(session,"TBLRUJBANGSA","","",internalType);
			this.context.put("list_TBLRUJBANGSA",list_TBLRUJBANGSA);
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","","",internalType);
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
			list_TBLRUJAGAMA = listTableRujukanV3(session,"TBLRUJAGAMA","","",internalType);
			this.context.put("list_TBLRUJAGAMA",list_TBLRUJAGAMA);
			
			String filterNegeriHQ = "";
			if(internalType.equals("HQ"))
			{
				filterNegeriHQ = "16";
			}
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",filterNegeriHQ,USER_ID,internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = listPejabatJKPTG(session,(String) viewPengguna.get("ID_SEKSYEN"),(String) viewPengguna.get("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);			
			if(!viewPengguna.get("ID_PEJABATJKPTG").equals(""))
			{
			viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
			this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}
			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);
			this.context.put("SuccessMesejDeleteUser","");
			
			skrin_name = "app/admin/MyProfile/editPenggunaInternal.jsp";
		}
		else if(command.equals("showDisplayPejabat"))
		{
			 USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			if(!ID_PEJABATJKPTG.equals(""))
			{
				viewPejabatJKPTG = viewPejabatJKPTG(session,ID_PEJABATJKPTG);
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}
			else
			{
				this.context.put("viewPejabatJKPTG","");
			}
			skrin_name = "app/admin/MyProfile/showDisplayPejabat.jsp";
		}
		
		//batal
		
		else if(command.equals("batalPaparMyProfile"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			
			//viewPengguna = viewDataPenggunaInternal(session,USER_ID,"");	
			viewPengguna = viewDataPenggunaInternal(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);
			
			list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","","",internalType);
			this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
			list_TBLRUJBANGSA = listTableRujukanV3(session,"TBLRUJBANGSA","","",internalType);
			this.context.put("list_TBLRUJBANGSA",list_TBLRUJBANGSA);
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","","",internalType);
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
			list_TBLRUJAGAMA = listTableRujukanV3(session,"TBLRUJAGAMA","","",internalType);
			this.context.put("list_TBLRUJAGAMA",list_TBLRUJAGAMA);
			
			String filterNegeriHQ = "";
			if(internalType.equals("HQ"))
			{
				filterNegeriHQ = "16";
			}
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",filterNegeriHQ,USER_ID,internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
			listPejabatJKPTG = listPejabatJKPTG(session,(String) viewPengguna.get("ID_SEKSYEN"),(String) viewPengguna.get("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);			
			if(!viewPengguna.get("ID_PEJABATJKPTG").equals(""))
			{
			viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
			this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}
			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);
			this.context.put("SuccessMesejDeleteUser","");
			
			skrin_name = "app/admin/MyProfile/editPenggunaInternal.jsp";
			
		}	
		//simpan
		else if(command.equals("savePenggunaInternal"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			
			USER_ID = simpanPenggunaInternal(session,USER_ID, internalType);
			
			viewPengguna = viewDataPenggunaInternal(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);
			
			
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, (String) viewPengguna.get("USER_LOGIN"));
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);
			
			
			skrin_name = "app/admin/MyProfile/UserProfileInternal.jsp";
		}	
		 
		else if(command.equals("showListPejabat"))
		{
			 USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_SEKSYEN = getParam("ID_SEKSYEN");
			String ID_NEGERI = getParam("ID_NEGERI");
			myLogger.info(" ID_SEKSYEN : "+ID_SEKSYEN+",  ID_NEGERI : "+ID_NEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,ID_SEKSYEN,ID_NEGERI);
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);	
			skrin_name = "app/admin/MyProfile/showListPejabat.jsp";
		}
		
		//audit trail
		else if(command.equals("carianUtamaAT"))
		{			
			 USER_ID = getParam("USER_ID");
			Integer totalAT = getATCount(session, USER_ID);
			this.context.put("USER_ID", USER_ID);
			this.context.put("adaAT", "");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);	
			
			if(totalAT>0)
			{
				this.context.put("totalAT", totalAT);
				this.context.put("adaAT", "Y");			
				
				
				String carianTerperinciAT = getParam("carianTerperinciAT_"+internalType+USER_ID);
				String TARIKH_MULA_AT = getParam("TARIKH_MULA_AT_"+internalType+USER_ID);
				String TARIKH_AKHIR_AT = getParam("TARIKH_AKHIR_AT_"+internalType+USER_ID);
				
				Date today_date = new Date();
				String today_date_str= new SimpleDateFormat("dd/MM/yyyy").format(today_date);
				/*
				if(TARIKH_MULA_AT.equals("") && TARIKH_AKHIR_AT.equals(""))
				{				
				TARIKH_AKHIR_AT = today_date_str;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -1);
				Date date_before = cal.getTime();
				SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
				TARIKH_MULA_AT = ft.format(date_before);
				}
				*/
				String action = getParam("action");
				listPengunaAT = listPengunaAT(session,carianTerperinciAT,TARIKH_MULA_AT, TARIKH_AKHIR_AT,USER_ID);
				setupPageList(session, action, listPengunaAT, "listPengunaAT",command,"div_ListAT"+internalType+USER_ID);				
				this.context.put("listPengunaATforPrint",listPengunaAT);
				this.context.put("internalType",internalType);
				this.context.put("USER_ID",USER_ID);
				this.context.put("carianTerperinciAT",carianTerperinciAT);
				this.context.put("TARIKH_MULA_AT",TARIKH_MULA_AT);
				this.context.put("TARIKH_AKHIR_AT",TARIKH_AKHIR_AT);
				this.context.put("TARIKH_CURRENT",today_date_str);
			
			}			
			skrin_name = "app/admin/MyProfile/SenaraiPenggunaAT.jsp";
		}
		
		//PLA
		else if(command.equals("carianUtamaPLA"))
		{			
			 USER_ID = getParam("USER_ID");
			myLogger.info("user id for pla -- "+ USER_ID);
			this.context.put("USER_ID", USER_ID);
			Integer totalPLA = getPLACount(session, USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);	
			
			//this.context.put("adaPLA", "");
			///if(totalPLA>0)
			//{
				this.context.put("totalPLA", totalPLA);
				this.context.put("adaPLA", "Y");			
				
				
				String carianTerperinciPLA = getParam("carianTerperinciPLA_"+internalType+USER_ID);
				String TARIKH_MULA_PLA = getParam("TARIKH_MULA_PLA_"+internalType+USER_ID);
				String TARIKH_AKHIR_PLA = getParam("TARIKH_AKHIR_PLA_"+internalType+USER_ID);
				
				Date today_date = new Date();
				String today_date_str= new SimpleDateFormat("dd/MM/yyyy").format(today_date);
				
				
				String action = getParam("action");
				listPengunaPLA = listPengunaPLA(session,carianTerperinciPLA,TARIKH_MULA_PLA, TARIKH_AKHIR_PLA,USER_ID);
				setupPageList(session, action, listPengunaPLA, "listPengunaPLA",command,"div_ListPLA"+internalType+USER_ID);				
				this.context.put("listPengunaPLAforPrint",listPengunaPLA);
				this.context.put("internalType",internalType);
				this.context.put("USER_ID",USER_ID);
				this.context.put("carianTerperinciPLA",carianTerperinciPLA);
				this.context.put("TARIKH_MULA_PLA",TARIKH_MULA_PLA);
				this.context.put("TARIKH_AKHIR_PLA",TARIKH_AKHIR_PLA);
				this.context.put("TARIKH_CURRENT",today_date_str);
			
			//}			
			skrin_name = "app/admin/MyProfile/SenaraiPenggunaPLA.jsp";
		}
		//pla
		else if(command.equals("viewPLA"))
		{
			String ID_ESADUAN = getParam("ID_ESADUAN");
			this.context.put("ID_ESADUAN", ID_ESADUAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengunaPLA = viewPengunaPLA(session,ID_ESADUAN);
			this.context.put("viewPengunaPLA", viewPengunaPLA);
			skrin_name = "app/admin/MyProfile/viewPLA.jsp";
		}
		else if(command.equals("close_viewPla"))
		{
			String ID_ESADUAN = getParam("ID_ESADUAN");
			this.context.put("ID_ESADUAN", ID_ESADUAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			skrin_name = "app/admin/MyProfile/blank_viewPLA.jsp";
		}
		 
		else if(command.equals("edit_PenggunaKJP"))
		{
			
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			
			//viewPengguna = viewDataPenggunaKJP(session,USER_ID,"");	
			//this.context.put("viewPengguna", viewPengguna);		
			
			viewPengguna = viewDataPenggunaKJP(session,USER_ID,"");			
			this.context.put("viewPengguna", viewPengguna);		
			
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","","X",internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
			list_TBLRUJKEMENTERIAN = listTableRujukanV3(session,"TBLRUJKEMENTERIAN","","",internalType);
			this.context.put("list_TBLRUJKEMENTERIAN",list_TBLRUJKEMENTERIAN);
			list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSI",(String)viewPengguna.get("ID_KEMENTERIAN"),"",internalType);
			this.context.put("list_TBLRUJAGENSI",list_TBLRUJAGENSI);
			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);
			String current_role_kjp = "";
			if(!USER_ID.equals(""))
			{
				current_role_kjp = getCurrentRoleKJP(session, USER_ID);
			}
			this.context.put("current_role_kjp",current_role_kjp);
			
			if(!viewPengguna.get("ID_KEMENTERIAN").equals(""))
			{
				viewAlamatKJP = viewAlamatKJP(session, (String)viewPengguna.get("ID_KEMENTERIAN"));
				this.context.put("viewAlamatKJP",viewAlamatKJP);
			}
			else
			{
				this.context.put("viewAlamatKJP","");
			}
			
			this.context.put("SuccessMesejDeleteUser","");
			
			skrin_name = "app/admin/MyProfile/editPenggunaKJP.jsp";
		}
		 
		else if(command.equals("edit_PenggunaOnline"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			
			viewPengguna = viewDataPenggunaOnline(session, USER_ID, "");
			this.context.put("viewPengguna", viewPengguna);
			
			this.context.put("list_TBLRUJPOSKOD", "");			
			
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","","",internalType);
			this.context.put("list_TBLRUJNEGERI", list_TBLRUJNEGERI);
			
			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);
			
			if(!viewPengguna.get("ID_NEGERI").equals(""))
			{
				list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDAR",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJBANDAR", list_TBLRUJBANDAR);
			}
			else
			{
				this.context.put("list_TBLRUJBANDAR", "");
			}
			this.context.put("SuccessMesejDeleteUser","");			
			skrin_name = "app/admin/MyProfile/editPenggunaOnline.jsp";
		}
		 
		else if(command.equals("edit_PenggunaINT"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengguna = viewDataPenggunaINT(session,USER_ID,"");			
			this.context.put("viewPengguna", viewPengguna);
			
			String ID_JENISPEJABAT = (String) viewPengguna.get("ID_JENISPEJABAT");
			
			list_TBLINTRUJJENISPEJABAT = listPejabatIntegrasi(session,"","");
			this.context.put("list_TBLINTRUJJENISPEJABAT",list_TBLINTRUJJENISPEJABAT);	
			
			if(ID_JENISPEJABAT.equals("16111"))//JPPH
			{				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*",USER_ID,internalType);
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);			
				
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				listPejabat = listPejabat(session, (String) viewPengguna.get("ID_NEGERI"), "", "3","2");//JPPH + PPK	
				this.context.put("listPejabat",listPejabat);
				
				viewPejabat = viewPejabat(session,(String) viewPengguna.get("ID_PEJABAT"));
				this.context.put("viewPejabat",viewPejabat);
				
				
			}
			
			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);			
			this.context.put("listRole",listRole);
			this.context.put("SuccessMesejDeleteUser","");
			
			skrin_name = "app/admin/MyProfile/editPenggunaINT.jsp";
		}
		 
		else if(command.equals("showListBandar"))
		{
			 USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_NEGERI = getParam("ID_NEGERI");
			list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDAR",ID_NEGERI,"",internalType);
			this.context.put("list_TBLRUJBANDAR", list_TBLRUJBANDAR);
			skrin_name = "app/admin/MyProfile/showListBandar.jsp";
		}
		 
		else if(command.equals("savePenggunaOnline"))
		{
			
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			
			
			
			USER_ID = simpanPenggunaOnline(session,USER_ID, internalType);
			
			viewPengguna = viewDataPenggunaOnline(session, USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);
			
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_ID);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);
			
			skrin_name = "app/admin/MyProfile/viewPenggunaOnline.jsp";
		}
		else if(command.equals("savePenggunaKJP"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String current_role_kjp = "";
			USER_ID = simpanPenggunaKJP(session,USER_ID, internalType);
			if(!USER_ID.equals(""))
			{
				current_role_kjp = getCurrentRoleKJP(session, USER_ID);
			}
			this.context.put("current_role_kjp",current_role_kjp);			
			viewPengguna = viewDataPenggunaKJP(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);
			
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, (String) viewPengguna.get("USER_LOGIN"));
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);
			
			skrin_name = "app/admin/MyProfile/viewPenggunaKJP.jsp";
		}	
		else if(command.equals("selectJenisPejabatINT"))
		{
			 USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengguna = viewDataPenggunaINT(session,USER_ID,"");			
			//this.context.put("viewPengguna", viewPengguna);
			
			String ID_JENISPEJABAT2 = getParam("ID_JENISPEJABAT");
			myLogger.info("ID_JENISPEJABAT2 onchange -- "+ID_JENISPEJABAT2);
			
			/*String seperator = "/";
			
			String ID_JENISPEJABAT = ID_JENISPEJABAT2.substring(0, ID_JENISPEJABAT2.indexOf(seperator)).trim();
			this.context.put("ID_JENISPEJABAT", ID_JENISPEJABAT);
			myLogger.info("ID_JENISPEJABAT  -- "+ID_JENISPEJABAT);
			
			String ID_NEGERIPEJABAT = ID_JENISPEJABAT2.substring(ID_JENISPEJABAT2.indexOf(seperator)+1).trim();
			this.context.put("ID_NEGERIPEJABAT", ID_NEGERIPEJABAT);
			myLogger.info("ID_NEGERIPEJABAT  -- "+ID_NEGERIPEJABAT);*/
			
			//if(ID_JENISPEJABAT.equals("16111"))//JPPH
			//if(ID_JENISPEJABAT.equals("3"))//JPPh drpd jenis pejabat
			//{
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);		
				/*
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				listPejabat = listPejabat(session, (String) viewPengguna.get("ID_NEGERI"), (String) viewPengguna.get("ID_DAERAH"), "3");	
				this.context.put("listPejabat",listPejabat);				
				*/
				listPejabat = listPejabatIntegrasi(session,(String) viewPengguna.get("ID_NEGERI"),ID_JENISPEJABAT2);//JPPH + PPK	
				this.context.put("listPejabat",listPejabat);
				
				skrin_name = "app/admin/UV3/edit_INT_JPPH.jsp";
			//}
			//else
			//{
			//	skrin_name = "app/admin/UV3/edit_INT_blank.jsp";
			//}
		}
		else if(command.equals("savePenggunaINT"))
		{
			 USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			
			USER_ID = simpanPenggunaINT(session,USER_ID, internalType);
			
			viewPengguna = viewDataPenggunaINT(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);
			
			String ID_JENISPEJABAT = (String) viewPengguna.get("ID_JENISPEJABAT");
			if(ID_JENISPEJABAT.equals("16111"))//JPPH
			{	
				viewPejabat = viewPejabat(session,(String) viewPengguna.get("ID_PEJABAT"));
				this.context.put("viewPejabat",viewPejabat);
			}			
			
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN_SYSTEM);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);
			
			skrin_name = "app/admin/MyProfile/viewPenggunaINT.jsp";
		}	
		 
		return skrin_name;
		}
	//PLA
	@SuppressWarnings("unchecked")
	public Hashtable viewPengunaPLA(HttpSession session,String id_esaduan)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		//List listPengunaPLA = null;
		String sql = "";
		Hashtable h;
		h = new Hashtable();
		try {
			db = new Db();
			stmt = db.getStatement();		
		    sql = queryPLA(session,"", "",id_esaduan,"","");
			myLogger.info(" V3: SQL viewPengunaPLA :"+ sql);			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
				h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
				h.put("PENGADU",rs.getString("PENGADU") == null ? "" : rs.getString("PENGADU"));
				h.put("ID_ESADUAN",rs.getString("ID_ESADUAN") == null ? "" : rs.getString("ID_ESADUAN"));
				h.put("NAMA_SUMBER",rs.getString("NAMA_SUMBER") == null ? "" : rs.getString("NAMA_SUMBER"));
				h.put("JENIS_ADUAN",rs.getString("JENIS_ADUAN") == null ? "" : rs.getString("JENIS_ADUAN"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("LOG_ADUAN",rs.getString("LOG_ADUAN") == null ? "" : rs.getString("LOG_ADUAN"));
				h.put("NAMA_SKRIN",rs.getString("NAMA_SKRIN") == null ? "" : rs.getString("NAMA_SKRIN"));
				h.put("TARIKH_ADUAN",rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN"));
				h.put("TARIKH_TERIMA_ADUAN",rs.getString("TARIKH_TERIMA_ADUAN") == null ? "" : rs.getString("TARIKH_TERIMA_ADUAN"));
				h.put("TARIKH_SELESAI_ADUAN",rs.getString("TARIKH_SELESAI_ADUAN") == null ? "" : rs.getString("TARIKH_SELESAI_ADUAN"));
				h.put("ADUAN",rs.getString("ADUAN") == null ? "" : rs.getString("ADUAN"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("MODUL",rs.getString("MODUL") == null ? "" : rs.getString("MODUL"));
				h.put("ULASAN_TEKNIKAL",rs.getString("ULASAN_TEKNIKAL") == null ? "" : rs.getString("ULASAN_TEKNIKAL"));
				h.put("NEGERI_PENGADU",rs.getString("NEGERI_PENGADU") == null ? "" : rs.getString("NEGERI_PENGADU"));
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("FLAG_MASALAH_DB",rs.getString("FLAG_MASALAH_DB") == null ? "" : rs.getString("FLAG_MASALAH_DB"));
				h.put("FLAG_MASALAH_FLOW",rs.getString("FLAG_MASALAH_FLOW") == null ? "" : rs.getString("FLAG_MASALAH_FLOW"));
				h.put("FLAG_MASALAH_HW",rs.getString("FLAG_MASALAH_HW") == null ? "" : rs.getString("FLAG_MASALAH_HW"));
				h.put("FLAG_MASALAH_PENAMBAHAN",rs.getString("FLAG_MASALAH_PENAMBAHAN") == null ? "" : rs.getString("FLAG_MASALAH_PENAMBAHAN"));
				h.put("FLAG_MASALAH_PERTANYAAN",rs.getString("FLAG_MASALAH_PERTANYAAN") == null ? "" : rs.getString("FLAG_MASALAH_PERTANYAAN"));
				h.put("FLAG_MASALAH_REPORT",rs.getString("FLAG_MASALAH_REPORT") == null ? "" : rs.getString("FLAG_MASALAH_REPORT"));
				h.put("FLAG_MASALAH_SKRIN",rs.getString("FLAG_MASALAH_SKRIN") == null ? "" : rs.getString("FLAG_MASALAH_SKRIN"));
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return h;

	}
	
	
	//PLA
	
public String queryPLA(HttpSession session,String user_id, String carianTerperinci,String ID_ESADUAN,String tarikh_mula,String tarikh_akhir) throws Exception {	
		
			String sql = " SELECT V.USER_ID, V.USER_LOGIN, V.PENGADU,V.ID_ESADUAN,V.NAMA_SUMBER,V.JENIS_ADUAN, "+
			" V.STATUS,V.LOG_ADUAN,V.NAMA_SKRIN,V.TARIKH_ADUAN,V.TARIKH_TERIMA_ADUAN,V.TARIKH_SELESAI_ADUAN, "+
			" V.ADUAN,V.NO_FAIL,V.MODUL, V.ULASAN_TEKNIKAL,V.NEGERI_PENGADU,V.NAMA_PEJABAT, "+
			" V.FLAG_MASALAH_DB, V.FLAG_MASALAH_FLOW,V.FLAG_MASALAH_HW,V.FLAG_MASALAH_PENAMBAHAN, "+
			" V.FLAG_MASALAH_PERTANYAAN,V.FLAG_MASALAH_REPORT,V.FLAG_MASALAH_SKRIN "+
			" FROM "+
			" (SELECT   ES.USER_ID, U.USER_LOGIN, UPPER (U.USER_NAME) AS PENGADU, "+
			" ES.ID_ESADUAN, UPPER (TRSA.NAMA_SUMBER) AS NAMA_SUMBER, "+
			" UPPER (TRJA.JENIS_ADUAN) AS JENIS_ADUAN, "+
			" UPPER (TRST.KETERANGAN) AS STATUS, ES.LOG_ADUAN, "+
			" UPPER (ES.NAMA_SKRIN) AS NAMA_SKRIN, "+
			" TO_CHAR (ES.TARIKH_ADUAN_HANTAR, 'DD/MM/YYYY') AS TARIKH_ADUAN, "+
			" TO_CHAR (ES.TARIKH_ADUAN_TERIMA, 'DD/MM/YYYY') AS TARIKH_TERIMA_ADUAN, "+
			" TO_CHAR (ES.TARIKH_SELESAI, 'DD/MM/YYYY') AS TARIKH_SELESAI_ADUAN, "+
			" UPPER (ES.ADUAN) AS ADUAN, ES.NO_FAIL, "+
			" UPPER (TRM.KETERANGAN) AS MODUL, "+
			" UPPER (ES.ULASAN_TEKNIKAL) AS ULASAN_TEKNIKAL, "+
			" UPPER(RN.NAMA_NEGERI) AS NEGERI_PENGADU, "+
			" UPPER(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, "+
			" ES.FLAG_MASALAH_DB, ES.FLAG_MASALAH_FLOW,ES.FLAG_MASALAH_HW,ES.FLAG_MASALAH_PENAMBAHAN, "+
			" ES.FLAG_MASALAH_PERTANYAAN,ES.FLAG_MASALAH_REPORT,ES.FLAG_MASALAH_SKRIN "+
			" FROM TBLESADUAN ES, "+
			" TBLRUJSUMBERESADUAN TRSA, "+
			" TBLRUJJENISESADUAN TRJA, "+
			" USERS U, "+
			" TBLRUJSTATUSESADUAN TRST, "+
			" TBLRUJJENISMODULESADUAN TRM, "+
			" TBLRUJNEGERI RN, TBLRUJPEJABATJKPTG PEJ "+
			" WHERE ES.ID_SUMBERADUAN = TRSA.ID_SUMBERADUAN(+) "+
			" AND ES.ID_JENISADUAN = TRJA.ID_JENISADUAN(+) "+
			" AND ES.USER_ID = U.USER_ID "+
			" AND ES.ID_STATUS = TRST.ID_STATUSESADUAN "+
			" AND ES.ID_STATUS NOT IN (16125) "+
			" AND ES.ID_JENISMODULESADUAN = TRM.ID_JENISMODULESADUAN(+)  "+  
			" AND ES.ID_NEGERI_PENGADU = RN.ID_NEGERI(+)  "+
			" AND ES.ID_PEJABAT_PENGADU = PEJ.ID_PEJABATJKPTG(+) ";
			if(!carianTerperinci.equals(""))
			{
				sql += " AND ( " +
						" UPPER(ES.NO_FAIL) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
						" OR UPPER(ES.NAMA_SKRIN) LIKE UPPER ('%"+carianTerperinci.toUpperCase()+"%') "+
						" OR UPPER(ES.LOG_ADUAN) LIKE UPPER ('%"+carianTerperinci.toUpperCase()+"%') "+
						" OR UPPER(TRST.KETERANGAN) LIKE UPPER ('%"+carianTerperinci.toUpperCase()+"%') "+
						" OR UPPER(TRJA.JENIS_ADUAN) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
						" OR UPPER(TRSA.NAMA_SUMBER) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
						" OR UPPER (TRM.KETERANGAN) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+ 
						"  )         ";
			}
		
		
			if(!tarikh_mula.equals("") && !tarikh_akhir.equals(""))
			{
			sql += " AND ES.TARIKH_ADUAN_HANTAR BETWEEN TO_DATE('"+tarikh_mula+"','DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"','DD/MM/YYYY') ";
			}
			if(!tarikh_mula.equals("") && tarikh_akhir.equals(""))
			{
			sql += " AND ES.TARIKH_ADUAN_HANTAR > TO_DATE('"+tarikh_mula+"','DD/MM/YYYY') ";
			}
			if(tarikh_mula.equals("") && !tarikh_akhir.equals(""))
			{
			sql += " AND ES.TARIKH_ADUAN_HANTAR < TO_DATE('"+tarikh_akhir+"','DD/MM/YYYY') ";
			}
			
			if(!user_id.equals(""))
			{
				sql += " AND ES.USER_ID = "+user_id+" ";
			}
			
			if(!ID_ESADUAN.equals(""))
			{
				sql += " AND ES.ID_ESADUAN = "+ID_ESADUAN+" ";
			}
			
			sql+= " ORDER BY ES.TARIKH_ADUAN_HANTAR DESC) V ";

		
		
		return sql;
	}
	
//PLA
	@SuppressWarnings("unchecked")
	public List listPengunaPLA(HttpSession session,String carianTerperinci,String tarikh_mula, String tarikh_akhir,String user_id)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPengunaPLA = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();			
			
		    sql = queryPLA(session,user_id, carianTerperinci,"",tarikh_mula,tarikh_akhir);				
			
			myLogger.info(" V3: SQL listPengunaPLA :"+ sql);			
			rs = stmt.executeQuery(sql);
			listPengunaPLA = Collections.synchronizedList(new ArrayList());
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
				h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
				h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
				h.put("PENGADU",rs.getString("PENGADU") == null ? "" : rs.getString("PENGADU"));
				h.put("ID_ESADUAN",rs.getString("ID_ESADUAN") == null ? "" : rs.getString("ID_ESADUAN"));
				h.put("NAMA_SUMBER",rs.getString("NAMA_SUMBER") == null ? "" : rs.getString("NAMA_SUMBER"));
				h.put("JENIS_ADUAN",rs.getString("JENIS_ADUAN") == null ? "" : rs.getString("JENIS_ADUAN"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("LOG_ADUAN",rs.getString("LOG_ADUAN") == null ? "" : rs.getString("LOG_ADUAN"));
				h.put("NAMA_SKRIN",rs.getString("NAMA_SKRIN") == null ? "" : rs.getString("NAMA_SKRIN"));
				h.put("TARIKH_ADUAN",rs.getString("TARIKH_ADUAN") == null ? "" : rs.getString("TARIKH_ADUAN"));
				h.put("ADUAN",rs.getString("ADUAN") == null ? "" : rs.getString("ADUAN"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("MODUL",rs.getString("MODUL") == null ? "" : rs.getString("MODUL"));
				h.put("ULASAN_TEKNIKAL",rs.getString("ULASAN_TEKNIKAL") == null ? "" : rs.getString("ULASAN_TEKNIKAL"));
				listPengunaPLA.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPengunaPLA;

	}
		//PLA
		
		public int getPLACount(HttpSession session, String user_id) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				int cnt = 0;
				
				sql = " SELECT COUNT(*) AS CNT FROM TBLESADUAN A, USERS U WHERE A.USER_ID = U.USER_ID AND A.ID_STATUS NOT IN (16125) AND A.USER_ID = "+user_id+" ";
					rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						cnt = rs.getString("CNT") == null ? 0 : rs.getInt("CNT");
					}
				
				return cnt;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
	//AT
	
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
	
	
	//PAGING AT
	
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
	
	
	
	//AT
	
	@SuppressWarnings("unchecked")
	public List listPengunaAT(HttpSession session,String carianTerperinci,String tarikh_mula, String tarikh_akhir,String user_id)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPengunaAT = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT ID_AUDITTRAIL, NO_FAIL,KETERANGAN_AKTIVITI, JENIS_AKTIVITI, MODULE_ID, NAMA_MODUL, MODULE_GROUP, (TARIKH_AKTIVITI || ' ' ||  WAKTU_AKTIVITI) AS MASA_AKTIVITI, "+
					" IP_ADDRESS, BAHAGIAN,STATUS_AKTIVITI "+
					" FROM "+
					" (SELECT TO_CHAR(AT.ID_AUDITTRAIL) AS ID_AUDITTRAIL, "+
					" REPLACE(NVL( "+
					" (CASE WHEN (AT.KETERANGAN LIKE '%FAIL [%' OR AT.KETERANGAN LIKE '%FAIL PERMOHONAN [%' OR AT.KETERANGAN LIKE '% [%') THEN  SUBSTR(SUBSTR(AT.KETERANGAN, 1, INSTR(AT.KETERANGAN, ']') - 1) ,  "+
					" INSTR(SUBSTR(AT.KETERANGAN, 1, INSTR(AT.KETERANGAN, ']') - 1) ,	 '[') + 1) "+
					" ELSE '' END) "+
					" ,''),'null','') AS NO_FAIL, AT.KETERANGAN AS KETERANGAN_AKTIVITI, "+
					" (CASE WHEN AT.JENIS_AKTIVITI = 'INS' THEN 'INSERT' "+
					" WHEN AT.JENIS_AKTIVITI = 'UPD' THEN 'UPDATE' "+
					" WHEN AT.JENIS_AKTIVITI = 'DEL' THEN 'DELETE' "+
					" ELSE '' END) AS JENIS_AKTIVITI, AT.MODULE_NAME AS MODULE_ID, M.MODULE_TITLE AS NAMA_MODUL, "+
					" M.MODULE_GROUP, "+
					" TO_CHAR(AT.TARIKH_AKTIVITI,'DD/MM/YYYY') AS TARIKH_AKTIVITI, "+
					" TO_CHAR(AT.TARIKH_AKTIVITI,'HH:MM:SS AM') AS WAKTU_AKTIVITI, "+
					" REPLACE(NVL(AT.IP_ADDRESS,''),'null','') AS IP_ADDRESS, "+
					" S.KOD_SEKSYEN AS BAHAGIAN, ST.KETERANGAN AS STATUS_AKTIVITI "+
					" FROM TBLAUDITTRAIL AT, MODULE M, TBLRUJSEKSYEN S, TBLRUJSTATUS ST "+
					" WHERE AT.MODULE_NAME = M.MODULE_ID(+) "+
					" AND AT.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
					" AND AT.ID_STATUS = ST.ID_STATUS(+) ";
			
					
					if(!tarikh_mula.equals("") && !tarikh_akhir.equals(""))
					{
					sql += " AND AT.TARIKH_AKTIVITI BETWEEN TO_DATE('"+tarikh_mula+"','DD/MM/YYYY') AND TO_DATE('"+tarikh_akhir+"','DD/MM/YYYY') ";
					}
					if(!tarikh_mula.equals("") && tarikh_akhir.equals(""))
					{
					sql += " AND AT.TARIKH_AKTIVITI > TO_DATE('"+tarikh_mula+"','DD/MM/YYYY') ";
					}
					if(tarikh_mula.equals("") && !tarikh_akhir.equals(""))
					{
					sql += " AND AT.TARIKH_AKTIVITI < TO_DATE('"+tarikh_akhir+"','DD/MM/YYYY') ";
					}
					sql += " AND (AT.ID_MASUK = "+user_id+" OR AT.ID_KEMASKINI = "+user_id+") ";
					
					sql += " ORDER BY AT.TARIKH_AKTIVITI DESC "+
					" ) ";
					
					if(!carianTerperinci.equals(""))
					{
						sql += " WHERE ( " +
								" UPPER(NO_FAIL) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
								" OR UPPER (KETERANGAN_AKTIVITI) LIKE UPPER ('%"+carianTerperinci.toUpperCase()+"%') "+
								" OR UPPER (JENIS_AKTIVITI) LIKE UPPER ('%"+carianTerperinci.toUpperCase()+"%') "+
								" OR UPPER (NAMA_MODUL) LIKE UPPER ('%"+carianTerperinci.toUpperCase()+"%') "+
								//" OR UPPER(MODULE_GROUP) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
								//" OR UPPER(STATUS_AKTIVITI) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
								" OR UPPER(IP_ADDRESS) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
								" OR UPPER(BAHAGIAN) LIKE '%"+carianTerperinci.toUpperCase()+"%' "+
								"  )         ";
					}
			
			myLogger.info(" V3: SQL listPengunaAT :"+ sql);			
			rs = stmt.executeQuery(sql);
			listPengunaAT = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_AUDITTRAIL",rs.getString("ID_AUDITTRAIL") == null ? "" : rs.getString("ID_AUDITTRAIL"));				
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("KETERANGAN_AKTIVITI",rs.getString("KETERANGAN_AKTIVITI") == null ? "" : rs.getString("KETERANGAN_AKTIVITI"));
				h.put("JENIS_AKTIVITI",rs.getString("JENIS_AKTIVITI") == null ? "" : rs.getString("JENIS_AKTIVITI"));
				h.put("MODULE_ID",rs.getString("MODULE_ID") == null ? "" : rs.getString("MODULE_ID"));
				h.put("NAMA_MODUL",rs.getString("NAMA_MODUL") == null ? "" : rs.getString("NAMA_MODUL"));
				h.put("MODULE_GROUP",rs.getString("MODULE_GROUP") == null ? "" : rs.getString("MODULE_GROUP"));
				h.put("MASA_AKTIVITI",rs.getString("MASA_AKTIVITI") == null ? "" : rs.getString("MASA_AKTIVITI"));
				h.put("IP_ADDRESS",rs.getString("IP_ADDRESS") == null ? "" : rs.getString("IP_ADDRESS"));
				h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
				h.put("STATUS_AKTIVITI",rs.getString("STATUS_AKTIVITI") == null ? "" : rs.getString("STATUS_AKTIVITI"));
				listPengunaAT.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPengunaAT;

	}
	//AT
	
	public int getATCount(HttpSession session, String user_id) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			int cnt = 0;
			
			sql = " SELECT COUNT(*) AS CNT FROM TBLAUDITTRAIL A WHERE A.ID_MASUK = "+user_id+" OR A.ID_KEMASKINI = "+user_id+" ";
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					cnt = rs.getString("CNT") == null ? 0 : rs.getInt("CNT");
				}
			
			return cnt;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	

	public String checkUsersInternal(HttpSession session, String user_id) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String user_id_internal = "";
			
			sql = " SELECT USER_ID FROM USERS_INTERNAL WHERE USER_ID = '"+user_id+"'  ";
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					user_id_internal = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");
				}
			
			return user_id_internal;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public String simpanPenggunaInternal(HttpSession session,String USER_ID, String internalType) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		
		String sendEmel = "";
		
		String flag_operasi = "INSERT";
		if(!USER_ID.equals(""))
		{
			flag_operasi = "UPDATE";
		}
		long USER_ID_INSERT = 0;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			String GET_USER_ID_EXIST = getParam("GET_USER_ID_EXIST_"+internalType+USER_ID);
			String USER_LOGIN = getParam("USER_LOGIN_"+internalType+USER_ID);
			String PASSWORD = getParam("PASSWORD_"+internalType+USER_ID);
			String USER_NAME = getParam("USER_NAME_"+internalType+USER_ID);
			String EMEL = getParam("EMEL_"+internalType+USER_ID);
			String ID_JAWATAN = getParam("ID_JAWATAN_"+internalType+USER_ID);
			String ID_AGAMA = getParam("ID_AGAMA_"+internalType+USER_ID);
			String ID_BANGSA = getParam("ID_BANGSA_"+internalType+USER_ID);
			String ID_SEKSYEN = getParam("ID_SEKSYEN_"+internalType+USER_ID);
			String ID_NEGERI = getParam("ID_NEGERI_"+internalType+USER_ID);
			String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG_"+internalType+USER_ID);
			//String ROLE_MAIN = getParam("ROLE_MAIN_"+internalType+USER_ID);
			String FLAG_AKTIF = getParam("FLAG_AKTIF_"+internalType+USER_ID);
			//tambah admin 19/1/2017 - no tel
			String NO_TEL_PEJ = getParam("NO_TEL_PEJ_"+internalType+USER_ID);
			
			String USER_TYPE = "";
			if(internalType.equals("HQ") || internalType.equals("Negeri"))
			{
				USER_TYPE = "internal";
			}
			
			
			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					USER_ID_INSERT = DB.getNextID(db,"USERS_SEQ");
					returnUSERID = USER_ID_INSERT+"";
				}
				else
				{
					USER_ID_INSERT = Long.parseLong(GET_USER_ID_EXIST);
					returnUSERID = USER_ID_INSERT+"";
				}
			}
			else
			{
				returnUSERID = USER_ID;
			}
			
			/*
			myLogger.info("USER_LOGIN : "+USER_LOGIN);
			myLogger.info("USER_NAME : "+USER_NAME);
			myLogger.info("EMEL : "+EMEL);
			myLogger.info("ID_JAWATAN : "+ID_JAWATAN);
			myLogger.info("ID_AGAMA : "+ID_AGAMA);
			myLogger.info("ID_BANGSA : "+ID_BANGSA);
			myLogger.info("ID_SEKSYEN : "+ID_SEKSYEN);
			myLogger.info("ID_NEGERI : "+ID_NEGERI);
			myLogger.info("ID_PEJABATJKPTG : "+ID_PEJABATJKPTG);
			myLogger.info("ROLE_MAIN : "+ROLE_MAIN);
			myLogger.info("FLAG_AKTIF : "+FLAG_AKTIF);
			*/
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("USER_ID", USER_ID_INSERT);
					//r.add("USER_ROLE", ROLE_MAIN);
				}
				else
				{
					r.update("USER_ID", GET_USER_ID_EXIST);
					
				}
			}
			else
			{
				r.update("USER_ID", USER_ID);
				//r.add("USER_ROLE", ROLE_MAIN);
			}
			
			r.add("USER_LOGIN", USER_LOGIN);
			if(!PASSWORD.equals(""))
			{
				r.add("USER_PASSWORD", PasswordService.encrypt(PASSWORD));
				r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
				sendEmel = "Y";
			}			
			r.add("USER_NAME", USER_NAME.toUpperCase());
			
			r.add("DATE_REGISTERED", r.unquote("sysdate"));
			
			r.add("USER_TYPE", USER_TYPE);
			
			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("USERS");		
					myLogger.info("V3 : INSERT USERS : "+sql);
					this.context.put("SuccessMesej", "Insert");
					AuditTrail.logActivity(this,session,"INS","USER ["+USER_NAME+"] Added");
				}
				else
				{
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("USERS");		
					myLogger.info("V3 : UPDATE USERS : "+sql);
					this.context.put("SuccessMesej", "Update");
					AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
				}
			}
			else
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("USERS");		
				myLogger.debug("V3 : UPDATE USERS : "+sql);
				this.context.put("SuccessMesej", "Update");
				AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
			}		
			stmt.executeUpdate(sql);
			
			
			r.clear();
			sql = "";
			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("USER_ID", USER_ID_INSERT);
				}
				else
				{
					if(!checkUsersInternal(session,GET_USER_ID_EXIST).equals(""))
					{
						r.update("USER_ID", GET_USER_ID_EXIST);
					}
				}
				
			}
			else
			{
				r.update("USER_ID", USER_ID);
			}
			r.add("ID_SEKSYEN", ID_SEKSYEN);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_PEJABATJKPTG", ID_PEJABATJKPTG);
			r.add("ID_AGAMA", ID_AGAMA);
			r.add("ID_BANGSA", ID_BANGSA);
			r.add("ID_JAWATAN", ID_JAWATAN);
			r.add("EMEL", EMEL);
			
			
			if(flag_operasi.equals("INSERT"))
			{

				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("USERS_INTERNAL");		
					myLogger.info("V3 : INSERT USERS INTERNAL : "+sql);
				}
				else
				{
					if(!checkUsersInternal(session,GET_USER_ID_EXIST).equals(""))
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("USERS_INTERNAL");		
						myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
					}
				}				
				
			}
			else
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("USERS_INTERNAL");		
				myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
			}		
			stmt.executeUpdate(sql);
			
			if(flag_operasi.equals("INSERT"))
			{
				if(!GET_USER_ID_EXIST.equals(""))
				{
				//add user role online	
					r.clear();
					r.add("USER_ID", USER_LOGIN);
					//r.add("ROLE_ID", ROLE_MAIN);
					sql = r.getSQLInsert("USER_ROLE");		
					myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
					stmt.executeUpdate(sql);
				}
			}

			conn.commit();
			
			/*if(sendEmel.equals("Y"))
			{
			//tambah hantar emel kpd bpict (sebab ada perubahan)
			hantarEmelStaff(session,USER_ID+"",1);
			}*/
			
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
		return returnUSERID;
	}
	
	
	public Hashtable viewPejabatJKPTG(HttpSession session, String ID_PEJABATJKPTG) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, "+
					" UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, UPPER(PEJ.ALAMAT3) AS ALAMAT3, "+ 
					" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, UPPER(BAN.KETERANGAN) AS BANDAR, UPPER(NEG.NAMA_NEGERI) AS NEGERI,  "+
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX  "+
					" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
					" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
					" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
					sql += " AND PEJ.ID_PEJABATJKPTG = "+ID_PEJABATJKPTG+" ";					
					sql += " ORDER BY PEJ.KOD_JKPTG ";
			myLogger.info(" viewPejabatJKPTG :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();
			
			while (rs.next()) {
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("KOD_JKPTG",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
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
	public List listPejabatJKPTG(HttpSession session, String ID_SEKSYEN, String ID_NEGERI)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPejabatJKPTG = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT PEJ.ID_PEJABATJKPTG, PEJ.KOD_JKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT, "+
					" UPPER(PEJ.ALAMAT1) AS ALAMAT1, UPPER(PEJ.ALAMAT2) AS ALAMAT2, UPPER(PEJ.ALAMAT3) AS ALAMAT3, "+ 
					" PEJ.POSKOD, PEJ.ID_NEGERI, PEJ.ID_BANDAR, UPPER(BAN.KETERANGAN) AS BANDAR, UPPER(NEG.NAMA_NEGERI) AS NEGERI,  "+
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX  "+
					" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
					" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
					" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
					if(!ID_NEGERI.equals(""))
					{
						sql += " AND PEJ.ID_NEGERI = "+ID_NEGERI+" ";
					}
					if(!ID_SEKSYEN.equals(""))
					{
						sql += " AND PEJ.ID_SEKSYEN = "+ID_SEKSYEN+" ";
					}
					sql += " ORDER BY PEJ.KOD_JKPTG ";
			myLogger.info(" V3: SQL listPejabatJKPTG :"+ sql);
			rs = stmt.executeQuery(sql);
			listPejabatJKPTG = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("KOD_JKPTG",rs.getString("KOD_JKPTG") == null ? "" : rs.getString("KOD_JKPTG"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				listPejabatJKPTG.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listPejabatJKPTG;

	}
	
	public String getRoleUtamaUsers(HttpSession session, String user_id) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String role = "";
			
			sql = " SELECT USER_ROLE FROM USERS WHERE USER_ID = "+user_id+"  ";
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					role = rs.getString("USER_ROLE") == null ? "" : rs.getString("USER_ROLE");
				}
			
			return role;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	//kemaskini
	
	@SuppressWarnings("unchecked")
	public List listTableRujukanV3(HttpSession session, String tableRujukan, String id_filter,String USER_ID,String internalType)throws Exception {
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
			else if(tableRujukan.equals("TBLRUJAGENSI"))
			{
				sql = " SELECT ID_AGENSI AS ID, KOD_AGENSI AS KOD, UPPER(NAMA_AGENSI) AS KETERANGAN FROM TBLRUJAGENSI WHERE ID_AGENSI IS NOT NULL ";
				sql += " AND ID_KEMENTERIAN = '"+id_filter+"' ";
				sql +=" ORDER BY KOD_AGENSI ";
			}
			else if(tableRujukan.equals("TBLRUJNEGERI"))
			{
				sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
				sql += " WHERE ID_NEGERI IS NOT NULL";
				
				if(USER_ID.equals(""))
				{
					if(!id_filter.equals(""))
					{
						sql += " AND ID_NEGERI = "+id_filter+" ";
					}
					else
					{
						sql += " AND ID_NEGERI != 16 ";
					}
				}
				
			}
			else if(tableRujukan.equals("TBLRUJBANDAR"))
			{
				sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR WHERE ID_BANDAR IS NOT NULL " +
						" AND ID_NEGERI = '"+id_filter+"' " +
								" ORDER BY KETERANGAN";
			}
			else if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
			{
				sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
			}
			else if(tableRujukan.equals("ROLE"))
			{
				String current_role_utama = "";
				myLogger.info(" --------- USER_ID : "+USER_ID);
				if(!USER_ID.equals(""))
				{
					current_role_utama = getRoleUtamaUsers(session,USER_ID);
					myLogger.info(" --------- atas current_role_utama : "+current_role_utama);
				}
				sql = " SELECT NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(DESCRIPTION) AS KETERANGAN, 2 AS LAYER  " +
						" FROM ROLE WHERE NAME IS NOT NULL ";
						if(internalType.equals("HQ") || internalType.equals("Negeri"))
						{
							String sql_Internal = " AND NAME NOT IN ('XXXXXX','online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user','XXXXXX')";
							if(!current_role_utama.equals(""))
							{
								if(sql_Internal.contains("'"+current_role_utama+"'")==true)
								{
									sql_Internal = sql_Internal.replace("'"+current_role_utama+"',", "");
								}
							}
							sql+=sql_Internal;
							
						}
						else if(internalType.equals("Online"))
						{
							String sql_Online = " AND NAME IN ('XXXXXX','online','ppt-online-user','php-online-user','ppk-online-user')";
							if(!current_role_utama.equals(""))
							{
								if(sql_Online.contains("'"+current_role_utama+"'")==false)
								{
									sql_Online = sql_Online.replace("'XXXXXX'", "'"+current_role_utama+"'");
								}
							}
							sql+=sql_Online;
						}
						else if(internalType.equals("KJP"))
						{
							String sql_KJP = " AND NAME IN ('XXXXXX','online_kjpagensi','online_kjp')";
							if(!current_role_utama.equals(""))
							{
								if(sql_KJP.contains("'"+current_role_utama+"'")==false)
								{
									sql_KJP = sql_KJP.replace("'XXXXXX'", "'"+current_role_utama+"'");									
								}
							}
							sql+=sql_KJP;
						}
						sql +=" UNION "+
						" SELECT DISTINCT '' AS ID,   REGEXP_REPLACE(REGEXP_REPLACE(THEME, 'eTapp_', ''),'.css','') AS KOD, '' AS KETERANGAN, 1 AS LAYER FROM ROLE " +
						" WHERE NAME IS NOT NULL ";
						if(internalType.equals("HQ") || internalType.equals("Negeri"))
						{
							String sql_Internal = " AND NAME NOT IN ('XXXXXX','online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user','XXXXXX')";
							if(!current_role_utama.equals(""))
							{
								if(sql_Internal.contains("'"+current_role_utama+"'")==true)
								{
									sql_Internal = sql_Internal.replace("'"+current_role_utama+"',", "");
								}
							}
							sql+=sql_Internal;
							
						}
						else if(internalType.equals("Online"))
						{
							String sql_Online = " AND NAME IN ('XXXXXX','online','ppt-online-user','php-online-user','ppk-online-user')";
							if(!current_role_utama.equals(""))
							{
								if(sql_Online.contains("'"+current_role_utama+"'")==false)
								{
									sql_Online = sql_Online.replace("'XXXXXX'", "'"+current_role_utama+"'");
								}
							}
							sql+=sql_Online;
						}
						else if(internalType.equals("KJP"))
						{
							String sql_KJP = " AND NAME IN ('XXXXXX','online_kjpagensi','online_kjp')";
							if(!current_role_utama.equals(""))
							{
								if(sql_KJP.contains("'"+current_role_utama+"'")==false)
								{
									sql_KJP = sql_KJP.replace("'XXXXXX'", "'"+current_role_utama+"'");									
								}
							}
							sql+=sql_KJP;
						}
						sql += " ORDER BY KOD, LAYER ";
			}
			
			myLogger.info(" V3: SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
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
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
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
	
	@SuppressWarnings("unchecked")
	public List listDaerahJagaanByIdPejabat(HttpSession session, String ID_PEJABATJKPTG, String ID_JENISPEJABAT)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listDaerahJagaanByIdPejabat = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT DISTINCT KOD_DAERAH, UPPER(NAMA_DAERAH) AS NAMA_DAERAH FROM TBLRUJDAERAH WHERE "+
					" ID_DAERAH IN (SELECT DISTINCT ID_DAERAHURUS  "+
					" FROM TBLRUJPEJABATURUSAN WHERE ID_PEJABATJKPTG='"+ID_PEJABATJKPTG+"' AND ID_JENISPEJABAT = '"+ID_JENISPEJABAT+"' ) "+
					" ORDER BY KOD_DAERAH ";
			myLogger.info(" V3: SQL listDaerahJagaanByIdPejabat :"+ sql);
			rs = stmt.executeQuery(sql);
			listDaerahJagaanByIdPejabat = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("KOD_DAERAH",rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH"));
				h.put("NAMA_DAERAH",rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));				
				listDaerahJagaanByIdPejabat.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listDaerahJagaanByIdPejabat;

	}
		@SuppressWarnings("unchecked")
		public List listRoleByUserLogin_Selected(HttpSession session, String USER_LOGIN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listRoleByUserLogin_Selected = null;
			String sql = "";		
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT R.NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(R.DESCRIPTION) AS KETERANGAN, 2 AS LAYER, 'Y' AS TICK, 0 AS TOTAL  "+
						" FROM ROLE R, USER_ROLE UR WHERE UR.ROLE_ID = R.NAME AND UR.USER_ID = '"+USER_LOGIN+"' "+
						" UNION "+
						" SELECT DISTINCT '' AS ID,   REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') AS KOD, '' AS KETERANGAN, 1 AS LAYER, '' AS TICK,  "+
						" (SELECT count(*)   "+
						" FROM ROLE R, USER_ROLE UR WHERE UR.ROLE_ID = R.NAME AND UR.USER_ID = '"+USER_LOGIN+"' AND REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') = REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''))  "+
						" AS TOTAL FROM ROLE R3, USER_ROLE UR3 WHERE  R3.NAME = UR3.ROLE_ID AND UR3.USER_ID = '"+USER_LOGIN+"'  "+
						" ORDER BY KOD, LAYER ";
				myLogger.info(" V3: SQL listRoleByUserLogin_Selected :"+ sql);
				rs = stmt.executeQuery(sql);
				listRoleByUserLogin_Selected = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				int new_bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					
					if(rs.getString("LAYER").equals("1"))
					{
						new_bil = 0;
					}
					if(rs.getString("LAYER").equals("2"))
					{
						new_bil++;
					}
					
					h.put("BIL",bil);
					h.put("NEWBIL",new_bil);
					
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
					h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));
					h.put("TOTAL",rs.getString("TOTAL") == null ? "" : rs.getInt("TOTAL"));				
					listRoleByUserLogin_Selected.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listRoleByUserLogin_Selected;

		}
		
		public Hashtable viewDataPenggunaInternal(HttpSession session, String user_id, String USER_LOGIN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!user_id.equals("") || !USER_LOGIN.equals(""))
				{
					sql = " SELECT U.FLAG_AKTIF, UI.ID_SEKSYEN, U.USER_ID, U.USER_LOGIN, UPPER(U.USER_NAME) AS USER_NAME, UI.EMEL, "+					
							" UI.ID_JAWATAN, UPPER(J.KETERANGAN) AS JAWATAN, UI.ID_AGAMA, UPPER(AG.KETERANGAN) AS AGAMA, "+
							" UI.ID_BANGSA, UPPER(BG.KETERANGAN) AS BANGSA, UPPER(SY.NAMA_SEKSYEN) AS NAMA_BAHAGIAN, "+
							" UI.ALAMAT1, UI.ALAMAT2, UI.ALAMAT3, UI.POSKOD, UI.ID_NEGERI, UI.ID_BANDAR,  "+
							" UPPER(NEG.NAMA_NEGERI) AS NAMA_NEGERI, UPPER(BAN.KETERANGAN) AS NAMA_BANDAR, "+
							" UI.ID_PEJABATJKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, UPPER(PEJ.ALAMAT1) AS ALAMAT1_PEJ, "+
							" UPPER(PEJ.ALAMAT2) AS ALAMAT2_PEJ,UPPER(PEJ.ALAMAT3) AS ALAMAT3_PEJ,PEJ.POSKOD AS POSKOD_PEJ,  "+
							" PEJ.ID_BANDAR AS ID_BANDAR_PEJ, PEJ.ID_NEGERI AS ID_NEGERI_PEJ, "+
							" UPPER(BAN_PEJ.KETERANGAN) AS BANDAR_PEJ, UPPER(NEG_PEJ.NAMA_NEGERI) AS NEGERI_PEJ, "+
							" PEJ.NO_TEL AS NO_TEL_PEJ,PEJ.NO_FAX AS NO_FAX_PEJ, RL.NAME AS ROLE_MAIN, UPPER(RL.DESCRIPTION) AS ROLE_MAIN_DETAILS, "+
							" TO_CHAR(MAX(WL.LOG_DATE),'DD/MM/YYYY') AS LAST_LOGIN, TO_CHAR(TO_DATE(U.LAST_CHANGEPASSWORD + 90),'DD/MM/YYYY') AS DAYS_AFTERCHANGEPASS, (90 - TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD))) AS TEMPOH_SAH, " +
							" UPPER(U.USER_TYPE) AS JENIS_PENGGUNA, U.USER_TYPE AS ID_JENIS_PENGGUNA, TO_CHAR(U.TARIKH_MASUK, 'DD/MM/YYYY')AS TARIKH_MASUK, TO_CHAR(U.TARIKH_KEMASKINI, 'DD/MM/YYYY')AS TARIKH_KEMASKINI  "+
							" FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J, TBLRUJAGAMA AG, TBLRUJBANGSA BG,  "+
							" TBLRUJSEKSYEN SY, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN, TBLRUJPEJABATJKPTG PEJ, "+
							" TBLRUJNEGERI NEG_PEJ, TBLRUJBANDAR BAN_PEJ, ROLE RL, WEB_LOGGER WL "+
							" WHERE U.USER_ID = UI.USER_ID "+
							" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "+
							" AND UI.ID_AGAMA = AG.ID_AGAMA(+) "+
							" AND UI.ID_BANGSA = BG.ID_BANGSA(+) "+
							" AND UI.ID_SEKSYEN = SY.ID_SEKSYEN(+) "+
							" AND UI.ID_NEGERI = NEG.ID_NEGERI(+) "+
							" AND UI.ID_BANDAR = BAN.ID_BANDAR(+) "+
							" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "+
							" AND PEJ.ID_BANDAR = BAN_PEJ.ID_BANDAR(+) "+
							" AND PEJ.ID_NEGERI = NEG_PEJ.ID_NEGERI(+) " +
							" AND U.USER_LOGIN = WL.USER_NAME " +
							" AND U.USER_ROLE = RL.NAME ";	
					
					if(!USER_LOGIN.equals(""))
					{
					//sql += " AND UPPER(U.USER_LOGIN) = '"+USER_LOGIN.toUpperCase()+"' ";
						sql += " AND U.USER_LOGIN = '"+USER_LOGIN+"' ";
					}
					if(!user_id.equals(""))
					{
					sql += " AND U.USER_ID = '"+user_id+"' ";
					}
					
					sql += " GROUP BY U.USER_ID,UI.ID_SEKSYEN,UI.EMEL, " +
						   " U.USER_LOGIN,SY.NAMA_SEKSYEN,U.USER_NAME,UI.ID_JAWATAN, " +
						   "  J.KETERANGAN,RL.DESCRIPTION,U.FLAG_AKTIF,UI.ID_AGAMA, AG.KETERANGAN, " +
						   "  UI.ID_BANGSA,BG.KETERANGAN,UI.ALAMAT1, UI.ALAMAT2, UI.ALAMAT3, " +
                           " UI.POSKOD, UI.ID_NEGERI, UI.ID_BANDAR, BAN.KETERANGAN, " +
                           " UI.ID_PEJABATJKPTG, " +
                           " PEJ.ALAMAT1, " +
                           " PEJ.ALAMAT2,PEJ.ALAMAT3, " +
                           " PEJ.POSKOD, " +
                           " PEJ.ID_BANDAR, " +
                           " PEJ.ID_NEGERI, " +
                           " BAN_PEJ.KETERANGAN, " +
                           " NEG_PEJ.NAMA_NEGERI,PEJ.NO_TEL,PEJ.NO_FAX, RL.NAME, " +
                           " U.USER_TYPE, " +
                           " U.LAST_CHANGEPASSWORD,U.TARIKH_MASUK,U.TARIKH_KEMASKINI, " +
                           " PEJ.NAMA_PEJABAT, NEG.NAMA_NEGERI" ;
					myLogger.info(" viewDataPenggunaInternal :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					
					
					while (rs.next()) {
						h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
						h.put("PASSWORD","");
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
						h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
						h.put("ID_AGAMA",rs.getString("ID_AGAMA") == null ? "" : rs.getString("ID_AGAMA"));
						h.put("AGAMA",rs.getString("AGAMA") == null ? "" : rs.getString("AGAMA"));
						h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
						h.put("ID_BANGSA",rs.getString("ID_BANGSA") == null ? "" : rs.getString("ID_BANGSA"));
						h.put("BANGSA",rs.getString("BANGSA") == null ? "" : rs.getString("BANGSA"));
						h.put("NAMA_BAHAGIAN",rs.getString("NAMA_BAHAGIAN") == null ? "" : rs.getString("NAMA_BAHAGIAN"));
						h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
						h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
						h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
						h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
						h.put("NAMA_BANDAR",rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR"));
						h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
						h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
						h.put("ALAMAT1_PEJ",rs.getString("ALAMAT1_PEJ") == null ? "" : rs.getString("ALAMAT1_PEJ"));
						h.put("ALAMAT2_PEJ",rs.getString("ALAMAT2_PEJ") == null ? "" : rs.getString("ALAMAT2_PEJ"));
						h.put("ALAMAT3_PEJ",rs.getString("ALAMAT3_PEJ") == null ? "" : rs.getString("ALAMAT3_PEJ"));				
						h.put("POSKOD_PEJ",rs.getString("POSKOD_PEJ") == null ? "" : rs.getString("POSKOD_PEJ"));
						h.put("ID_BANDAR_PEJ",rs.getString("ID_BANDAR_PEJ") == null ? "" : rs.getString("ID_BANDAR_PEJ"));
						h.put("ID_NEGERI_PEJ",rs.getString("ID_NEGERI_PEJ") == null ? "" : rs.getString("ID_NEGERI_PEJ"));
						h.put("BANDAR_PEJ",rs.getString("BANDAR_PEJ") == null ? "" : rs.getString("BANDAR_PEJ"));
						h.put("NEGERI_PEJ",rs.getString("NEGERI_PEJ") == null ? "" : rs.getString("NEGERI_PEJ"));
						h.put("NO_TEL_PEJ",rs.getString("NO_TEL_PEJ") == null ? "" : rs.getString("NO_TEL_PEJ"));
						h.put("NO_FAX_PEJ",rs.getString("NO_FAX_PEJ") == null ? "" : rs.getString("NO_FAX_PEJ"));
						h.put("ROLE_MAIN",rs.getString("ROLE_MAIN") == null ? "" : rs.getString("ROLE_MAIN"));
						h.put("ROLE_MAIN_DETAILS",rs.getString("ROLE_MAIN_DETAILS") == null ? "" : rs.getString("ROLE_MAIN_DETAILS"));
						h.put("JENIS_PENGGUNA",rs.getString("JENIS_PENGGUNA") == null ? "" : rs.getString("JENIS_PENGGUNA"));
						h.put("ID_JENIS_PENGGUNA",rs.getString("ID_JENIS_PENGGUNA") == null ? "" : rs.getString("ID_JENIS_PENGGUNA"));
						//tambah admin 20/1/2017
						h.put("LAST_LOGIN",rs.getString("LAST_LOGIN") == null ? "" : rs.getString("LAST_LOGIN"));
						h.put("DAYS_AFTERCHANGEPASS",rs.getString("DAYS_AFTERCHANGEPASS") == null ? "" : rs.getString("DAYS_AFTERCHANGEPASS"));
						h.put("TEMPOH_SAH",rs.getString("TEMPOH_SAH") == null ? "" : rs.getString("TEMPOH_SAH"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						//h.put("NAMA_DAFTAR",rs.getString("NAMA_DAFTAR") == null ? "" : rs.getString("NAMA_DAFTAR"));
						//h.put("KEMASKINI",rs.getString("KEMASKINI") == null ? "" : rs.getString("KEMASKINI"));
						
						
					}
				}
				else
				{
					h.put("FLAG_AKTIF","");
					h.put("USER_ID","");
					h.put("USER_LOGIN","");
					h.put("PASSWORD","");
					h.put("USER_NAME","");
					h.put("EMEL","");
					h.put("ID_JAWATAN","");
					h.put("JAWATAN","");
					h.put("ID_AGAMA","");
					h.put("AGAMA","");
					h.put("ID_SEKSYEN","");
					h.put("ID_BANGSA","");
					h.put("BANGSA","");
					h.put("NAMA_BAHAGIAN","");
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("ID_NEGERI","");
					h.put("ID_BANDAR","");
					h.put("NAMA_NEGERI","");
					h.put("NAMA_BANDAR","");
					h.put("ID_PEJABATJKPTG","");
					h.put("NAMA_PEJABAT","");
					h.put("ALAMAT1_PEJ","");
					h.put("ALAMAT2_PEJ","");
					h.put("ALAMAT3_PEJ","");				
					h.put("POSKOD_PEJ","");
					h.put("ID_BANDAR_PEJ","");
					h.put("ID_NEGERI_PEJ","");
					h.put("BANDAR_PEJ","");
					h.put("NEGERI_PEJ","");
					h.put("NO_TEL_PEJ","");
					h.put("NO_FAX_PEJ","");
					h.put("ROLE_MAIN","");
					h.put("ROLE_MAIN_DETAILS","");
					h.put("JENIS_PENGGUNA","");
					h.put("ID_JENIS_PENGGUNA","");
					//tambah admin 20/1/2017
					h.put("LAST_LOGIN","");	
					h.put("DAYS_AFTERCHANGEPASS","");	
					h.put("TEMPOH_SAH","");
					h.put("TARIKH_KEMASKINI","");	
					h.put("TARIKH_MASUK","");
					//h.put("NAMA_DAFTAR","");
					//h.put("KEMASKINI","");
					
		
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
		
		public Hashtable ViewKemaskini(HttpSession session, String user_id, String USER_LOGIN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!user_id.equals("") )
				{
					sql = " SELECT UPPER (U.USER_NAME) AS NAMA_DAFTAR, U.ID_KEMASKINI, 'KEMASKINI', " +
						  " TO_CHAR(MAX(AT.TARIKH_KEMASKINI),'DD/MM/YYYY') AS TARIKH_KEMASKINI " +
						  " FROM USERS U, TBLAUDITTRAIL AT " +
						  " WHERE  U.ID_KEMASKINI= AT.ID_KEMASKINI " +
						  " GROUP BY U.USER_NAME, U.ID_KEMASKINI " +
						  " UNION" +
						  " SELECT UPPER (U.USER_NAME) AS NAMA_DAFTAR, U.ID_MASUK,'MASUK', " +
						  " TO_CHAR(AT.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK " +
						  " FROM USERS U, TBLAUDITTRAIL AT " +
						  " WHERE  U.ID_MASUK= AT.ID_MASUK ";
;	

					myLogger.info(" ViewKemaskini :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);
					
					
					while (rs.next()) {
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("NAMA_DAFTAR",rs.getString("NAMA_DAFTAR") == null ? "" : rs.getString("NAMA_DAFTAR"));
					//	h.put("KEMASKINI",rs.getString("KEMASKINI") == null ? "" : rs.getString("KEMASKINI"));
						

					}
				}
				else
				{
					h.put("TARIKH_KEMASKINI","");
					h.put("NAMA_DAFTAR","");
				//	h.put("KEMASKINI","");
					
					
		
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
	//INTEGRASI
	

		@SuppressWarnings("unchecked")
		public List listPejabat(HttpSession session, String ID_NEGERI, String ID_DAERAH, String ID_JENISPEJABAT, String ID_SEKSYEN)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPejabat = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT P.ID_PEJABAT, P.ID_NEGERI, P.ID_DAERAH, P.ID_JENISPEJABAT, P.KOD_PEJABAT, UPPER(P.NAMA_PEJABAT) AS NAMA_PEJABAT, "+
						" UPPER(ALAMAT1) AS ALAMAT1, UPPER(ALAMAT2) AS ALAMAT2, UPPER(ALAMAT3) AS ALAMAT3, P.POSKOD, P.NO_TEL,P.NO_FAX, P.JAWATAN, "+ 
						" UPPER(N.NAMA_NEGERI) AS NEGERI, UPPER(D.NAMA_DAERAH) AS DAERAH, UPPER(B.KETERANGAN) AS BANDAR "+
						" FROM TBLRUJPEJABAT P, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B "+
						" WHERE P.ID_NEGERI = N.ID_NEGERI(+) AND P.ID_DAERAH = D.ID_DAERAH(+) AND P.ID_BANDAR = B.ID_BANDAR(+) ";
				if(!ID_SEKSYEN.equals(""))
				{
					sql += " AND P.ID_SEKSYEN = "+ID_SEKSYEN+" ";
				}
				if(!ID_NEGERI.equals(""))
				{
					sql += " AND P.ID_NEGERI = "+ID_NEGERI+" ";
				}
				if(!ID_DAERAH.equals(""))
				{
					sql += " AND P.ID_DAERAH = "+ID_DAERAH+" ";
				}
				if(!ID_JENISPEJABAT.equals(""))
				{
					sql += " AND P.ID_JENISPEJABAT = "+ID_JENISPEJABAT+" ";
				}
				
				sql += " ORDER BY N.KOD_NEGERI, D.KOD_DAERAH ";					
						
				myLogger.info(" V3: SQL listPejabat :"+ sql);
				rs = stmt.executeQuery(sql);
				listPejabat = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					h.put("BIL",bil);
					h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
					h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
					h.put("KOD_PEJABAT",rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT"));
					h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
					h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
					h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
					h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
					h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
					h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
					h.put("DAERAH",rs.getString("DAERAH") == null ? "" : rs.getString("DAERAH"));
					h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
					listPejabat.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listPejabat;

		}
		
		
		
		public String queryUserINT_ViewEdit(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {	
			
			String query = "";
			
					query += " SELECT   U.USER_ID, U.USER_LOGIN,  UPPER (U.USER_NAME) AS USER_NAME, "+
							" U.USER_TYPE, TO_CHAR(U.DATE_REGISTERED,'DD/MM/YYYY') AS TARIKH_DAFTAR, U.LAST_CHANGEPASSWORD, "+
					" UPPER (R.DESCRIPTION) AS ROLE_UTAMA, R.NAME AS ROLE_ID,TO_CHAR (MAX (WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN, "+
					" TRUNC (SYSDATE - TO_DATE (MAX (WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN, "+
					" TRUNC (SYSDATE - TO_DATE (U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, " +
					" TO_CHAR(TO_DATE(U.LAST_CHANGEPASSWORD + 90),'DD/MM/YYYY') AS DATE_DAYS_AFTERCHANGEPASS, "+
					" (90 - TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD))) AS TEMPOH_SAH, U.FLAG_AKTIF,"+
					" (CASE WHEN U.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN U.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
					" ELSE 'AKTIF' END ) AS KEAKTIFAN,UPPER (PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT,UPPER (N.NAMA_NEGERI) AS NEGERI_UI, "+
					" UPPER (D.NAMA_DAERAH) AS DAERAH_UI,UIN.EMEL,UIN.ID_JENISPEJABAT,UPPER(JP.KETERANGAN) AS JENIS_PEJABAT, " +
					" UPPER(UIN.JAWATAN) AS JAWATAN,UIN.ID_PEJABAT, UIN.ID_DAERAH, UIN.ID_NEGERI, UIN.ID_MUKIM, UIN.ID_AGENSI "+
					" FROM USERS U, USERS_INTERNAL UI, ROLE R, USERS_INTEGRASI UIN,WEB_LOGGER WL, "+
					" TBLRUJPEJABAT PEJ, TBLRUJNEGERI N,TBLRUJDAERAH D, TBLRUJJENISPEJABAT JP "+
					" WHERE U.USER_ID = UI.USER_ID AND UI.USER_ID = UIN.USER_ID "+
					" AND UIN.ID_NEGERI = N.ID_NEGERI(+) AND UIN.ID_DAERAH = D.ID_DAERAH(+)   "+  
					" AND U.USER_ROLE = R.NAME AND U.USER_LOGIN = WL.USER_NAME(+) "+
					" AND UIN.ID_PEJABAT = PEJ.ID_PEJABAT(+) AND UIN.ID_JENISPEJABAT = JP.ID_JENISPEJABAT(+) ";
					if(!USER_ID.trim().equals(""))
					{
						query += " AND U.USER_ID = '"+USER_ID.trim()+"' ";
					}
					if(!USER_LOGIN.trim().equals(""))
					{
						query += " AND U.USER_LOGIN = '"+USER_LOGIN+"' ";
					}
									
					query += " GROUP BY U.USER_ID, U.USER_LOGIN, U.USER_NAME,R.NAME,R.DESCRIPTION,U.FLAG_AKTIF," +
							" U.USER_TYPE,U.DATE_REGISTERED,U.LAST_CHANGEPASSWORD, "+
					" U.LAST_CHANGEPASSWORD,PEJ.NAMA_PEJABAT,N.NAMA_NEGERI,D.NAMA_DAERAH,UIN.EMEL, UIN.ID_JENISPEJABAT," +
					" JP.KETERANGAN,UIN.JAWATAN,UIN.ID_PEJABAT, UIN.ID_DAERAH, UIN.ID_NEGERI, UIN.ID_MUKIM, UIN.ID_AGENSI ";
					
					query += " ORDER BY USER_LOGIN ";
			
			return query;
		}
		
		@SuppressWarnings("unchecked")
		public Hashtable viewPejabat(HttpSession session,String ID_PEJABAT)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			String sql = "";
			Hashtable h;
			h = new Hashtable();
			try {
				db = new Db();
				stmt = db.getStatement();
				sql = " SELECT P.ID_PEJABAT, P.ID_NEGERI, P.ID_DAERAH, P.ID_JENISPEJABAT, P.KOD_PEJABAT, UPPER(P.NAMA_PEJABAT) AS NAMA_PEJABAT, "+
						" UPPER(ALAMAT1) AS ALAMAT1, UPPER(ALAMAT2) AS ALAMAT2, UPPER(ALAMAT3) AS ALAMAT3, P.POSKOD, P.NO_TEL,P.NO_FAX, P.JAWATAN, "+ 
						" UPPER(N.NAMA_NEGERI) AS NEGERI, UPPER(D.NAMA_DAERAH) AS DAERAH, UPPER(B.KETERANGAN) AS BANDAR "+
						" FROM TBLRUJPEJABAT P, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B "+
						" WHERE P.ID_NEGERI = N.ID_NEGERI(+) AND P.ID_DAERAH = D.ID_DAERAH(+) AND P.ID_BANDAR = B.ID_BANDAR(+) ";
				
					sql += " AND P.ID_PEJABAT= '"+ID_PEJABAT+"' ";
				
							
						
				myLogger.info(" V3: SQL viewPejabat :"+ sql);
				rs = stmt.executeQuery(sql);
				
				while (rs.next()) {
					h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
					h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
					h.put("KOD_PEJABAT",rs.getString("KOD_PEJABAT") == null ? "" : rs.getString("KOD_PEJABAT"));
					h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
					h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
					h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
					h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
					h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
					h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
					h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
					h.put("DAERAH",rs.getString("DAERAH") == null ? "" : rs.getString("DAERAH"));
					h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
					
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return h;

		}
		
		
		public Hashtable viewDataPenggunaINT(HttpSession session, String USER_ID, String USER_LOGIN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!USER_ID.equals("") || !USER_LOGIN.equals(""))
				{
					sql = queryUserINT_ViewEdit(session,USER_ID, "",USER_LOGIN);
					myLogger.info(" viewDataPenggunaINT :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
						h.put("NAMA_ROLE",rs.getString("ROLE_UTAMA") == null ? "" : rs.getString("ROLE_UTAMA"));
						h.put("ROLE_ID",rs.getString("ROLE_ID") == null ? "" : rs.getString("ROLE_ID"));
						h.put("USER_TYPE",rs.getString("USER_TYPE") == null ? "" : rs.getString("USER_TYPE"));
						h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR") == null ? "" : rs.getString("TARIKH_DAFTAR"));
						h.put("LAST_CHANGEPASSWORD",rs.getString("LAST_CHANGEPASSWORD") == null ? "" : rs.getString("LAST_CHANGEPASSWORD"));
						h.put("DAYS_AFTERLASTLOGIN",rs.getString("DAYS_AFTERLASTLOGIN") == null ? "" : rs.getString("DAYS_AFTERLASTLOGIN"));
						h.put("WAKTU_AKHIR_LOGIN",rs.getString("WAKTU_AKHIR_LOGIN") == null ? "" : rs.getString("WAKTU_AKHIR_LOGIN"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
						h.put("KEAKTIFAN",rs.getString("KEAKTIFAN") == null ? "" : rs.getString("KEAKTIFAN"));				
						h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
						h.put("NEGERI_UI",rs.getString("NEGERI_UI") == null ? "" : rs.getString("NEGERI_UI"));
						h.put("DAERAH_UI",rs.getString("DAERAH_UI") == null ? "" : rs.getString("DAERAH_UI"));
						h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
						h.put("JENIS_PEJABAT",rs.getString("JENIS_PEJABAT") == null ? "" : rs.getString("JENIS_PEJABAT"));
						h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
						h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
						h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
						h.put("ID_AGENSI",rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
						h.put("DATE_DAYS_AFTERCHANGEPASS",rs.getString("DATE_DAYS_AFTERCHANGEPASS") == null ? "" : rs.getString("DATE_DAYS_AFTERCHANGEPASS"));
						h.put("TEMPOH_SAH",rs.getString("TEMPOH_SAH") == null ? "" : rs.getString("TEMPOH_SAH"));
						h.put("ROLE_UTAMA",rs.getString("ROLE_UTAMA") == null ? "" : rs.getString("ROLE_UTAMA"));
					}
				}
				else
				{
					h.put("USER_ID","");
					h.put("USER_LOGIN","");
					h.put("USER_NAME","");
					h.put("NAMA_ROLE","");
					h.put("ROLE_ID","");
					h.put("TARIKH_DAFTAR","");
					h.put("LAST_CHANGEPASSWORD","");
					h.put("DAYS_AFTERLASTLOGIN","");
					h.put("WAKTU_AKHIR_LOGIN","");
					h.put("EMEL","");
					h.put("FLAG_AKTIF","");
					h.put("KEAKTIFAN","");				
					h.put("NAMA_PEJABAT","");
					h.put("NEGERI_UI","");
					h.put("DAERAH_UI","");
					h.put("ID_JENISPEJABAT","");
					h.put("JENIS_PEJABAT","");
					h.put("JAWATAN","");
					h.put("ID_PEJABAT","");
					h.put("ID_DAERAH","");
					h.put("ID_NEGERI","");
					h.put("ID_MUKIM","");
					h.put("ID_AGENSI","");
					h.put("DATE_DAYS_AFTERCHANGEPASS","");
					h.put("TEMPOH_SAH","");
					h.put("ROLE_UTAMA","");
					
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
		
	//KJP
		
		public String queryUserKJP(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {	
			
			String query = " SELECT   U.USER_ID, U.USER_LOGIN, UPPER (U.USER_NAME) AS NAMA_PENUH, "+
					" U.USER_ROLE, UPPER (R.DESCRIPTION) AS NAMA_ROLE, U.USER_TYPE, "+
					" TO_CHAR (U.DATE_REGISTERED, 'DD/MM/YYYY') AS TARIKH_DAFTAR, "+
					" U.LAST_CHANGEPASSWORD, "+
					" TRUNC (SYSDATE - TO_DATE (U.LAST_CHANGEPASSWORD) "+
					"       ) AS DAYS_AFTERCHANGEPASS, "+
					" TRUNC (SYSDATE - TO_DATE (MAX (WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN, "+
					" TO_CHAR (MAX (WL.LOG_DATE), "+
					"          'DD/MM/YYYY hh24:mi:ss' "+
					"         ) AS WAKTU_AKHIR_LOGIN, "+
					" (90 - TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD))) AS TEMPOH_SAH, TO_CHAR(TO_DATE(U.LAST_CHANGEPASSWORD + 90),'DD/MM/YYYY') AS DATE_DAYS_AFTERCHANGEPASS, " +
					//" UK.NO_KP_BARU, " +
					" UK.ID_KEMENTERIAN, UK.ID_AGENSI, UI.EMEL, "+
					" UPPER(TRK.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN,UPPER(TRA.NAMA_AGENSI) AS NAMA_AGENSI, "+
					" UPPER(TRK.ALAMAT1) AS ALAMAT1,UPPER(TRK.ALAMAT2) AS ALAMAT2,UPPER(TRK.ALAMAT3) AS ALAMAT3, "+
					" TRK.POSKOD,UPPER(NEG.NAMA_NEGERI) AS NEGERI_KEMENTERIAN, "+
					" U.FLAG_AKTIF, "+
					" (CASE WHEN U.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN U.FLAG_AKTIF = 2 "+
					"   THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS KEAKTIFAN, UI.ID_JAWATAN, " +
					" (CASE WHEN UI.ID_JAWATAN = 4 THEN 'PELULUS'" +
					" WHEN UI.ID_JAWATAN = 9 THEN 'PENYEMAK'" +
					" WHEN UI.ID_JAWATAN = 24 THEN 'PENYEDIA' ELSE '' END) AS NAMA_JAWATAN "+
					" FROM USERS U, USERS_INTERNAL UI, USERS_KEMENTERIAN UK, ROLE R,WEB_LOGGER WL, "+
					" TBLRUJKEMENTERIAN TRK, TBLRUJAGENSI TRA, TBLRUJNEGERI NEG "+
					" WHERE U.USER_ID = UK.USER_ID AND UI.USER_ID = UK.USER_ID "+
					" AND U.USER_ROLE = R.NAME(+)  "+
					" AND U.USER_LOGIN = WL.USER_NAME(+) "+
					" AND UK.ID_KEMENTERIAN = TRK.ID_KEMENTERIAN "+
					" AND UK.ID_AGENSI = TRA.ID_AGENSI "+
					" AND TRK.ID_NEGERI = NEG.ID_NEGERI(+) ";
					
					
					if(!USER_ID.trim().equals(""))
					{
						query += " AND U.USER_ID = '"+USER_ID.trim()+"' ";
					}
					if(!USER_LOGIN.trim().equals(""))
					{
						//query += " AND UPPER(U.USER_LOGIN) = '"+USER_LOGIN.toUpperCase()+"' ";
						query += " AND U.USER_LOGIN = '"+USER_LOGIN+"' ";
					}
					
					if(!carianTerperinci.equals(""))
					{
						query += " AND (U.USER_LOGIN LIKE '%"+carianTerperinci+"%' "+
								//" OR UK.NO_KP_BARU LIKE '%"+carianTerperinci+"%' "+
								" OR UPPER (U.USER_NAME) LIKE UPPER ('%"+carianTerperinci+"%') "+
								" OR UPPER (TRK.NAMA_KEMENTERIAN) LIKE UPPER ('%"+carianTerperinci+"%') "+
								" OR UPPER (TRA.NAMA_AGENSI) LIKE UPPER ('%"+carianTerperinci+"%') "+
								" OR UI.EMEL LIKE '%"+carianTerperinci+"%' "+
								"  )         ";
					}
					
					
					query += " GROUP BY U.USER_ID,U.USER_LOGIN,U.USER_NAME,U.USER_ROLE,R.DESCRIPTION, "+
					" U.USER_TYPE,U.DATE_REGISTERED,U.LAST_CHANGEPASSWORD, "+
					" U.LAST_CHANGEPASSWORD," +
					//" UK.NO_KP_BARU," +
					" UK.ID_KEMENTERIAN, "+
					" UK.ID_AGENSI,UI.EMEL,TRK.NAMA_KEMENTERIAN,TRA.NAMA_AGENSI, "+
					" TRK.ALAMAT1,TRK.ALAMAT2,TRK.ALAMAT3,TRK.POSKOD,NEG.NAMA_NEGERI, "+
					" U.FLAG_AKTIF,UI.ID_JAWATAN "+
					
					" ORDER BY U.USER_NAME ";
			
			
			return query;
		}
		
		public String getCurrentRoleKJP(HttpSession session, String user_id) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				String role = "";
				
				sql = " SELECT ROLE FROM "+
						" (SELECT U.USER_ROLE AS ROLE FROM USERS U WHERE U.USER_ID = '"+user_id+"' AND U.USER_ROLE IN ('online_kjp','online_kjpagensi') "+
						" UNION "+
						" SELECT UR.ROLE_ID AS ROLE FROM USERS U, USER_ROLE UR "+
						" WHERE U.USER_LOGIN = UR.USER_ID AND U.USER_ID = '"+user_id+"' AND UR.ROLE_ID IN ('online_kjp','online_kjpagensi') ) R ";
					rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						role = rs.getString("ROLE") == null ? "" : rs.getString("ROLE");
					}
				
				return role;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
		
		
		public Hashtable viewDataPenggunaKJP(HttpSession session, String USER_ID, String USER_LOGIN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!USER_ID.equals("") || !USER_LOGIN.equals(""))
				{
					sql = queryUserKJP(session,USER_ID, "",USER_LOGIN);
					myLogger.info(" viewDataPenggunaKJP :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
						h.put("NAMA_PENUH",rs.getString("NAMA_PENUH") == null ? "" : rs.getString("NAMA_PENUH"));
						h.put("USER_NAME",rs.getString("NAMA_PENUH") == null ? "" : rs.getString("NAMA_PENUH"));
						h.put("USER_ROLE",rs.getString("USER_ROLE") == null ? "" : rs.getString("USER_ROLE"));
						h.put("NAMA_ROLE",rs.getString("NAMA_ROLE") == null ? "" : rs.getString("NAMA_ROLE"));
						h.put("USER_TYPE",rs.getString("USER_TYPE") == null ? "" : rs.getString("USER_TYPE"));
						h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR") == null ? "" : rs.getString("TARIKH_DAFTAR"));
						h.put("LAST_CHANGEPASSWORD",rs.getString("LAST_CHANGEPASSWORD") == null ? "" : rs.getString("LAST_CHANGEPASSWORD"));
						h.put("DAYS_AFTERCHANGEPASS",rs.getString("DAYS_AFTERCHANGEPASS") == null ? "" : rs.getString("DAYS_AFTERCHANGEPASS"));
						h.put("DAYS_AFTERLASTLOGIN",rs.getString("DAYS_AFTERLASTLOGIN") == null ? "" : rs.getString("DAYS_AFTERLASTLOGIN"));
						h.put("WAKTU_AKHIR_LOGIN",rs.getString("WAKTU_AKHIR_LOGIN") == null ? "" : rs.getString("WAKTU_AKHIR_LOGIN"));
						//h.put("NO_KP_BARU",rs.getString("NO_KP_BARU") == null ? "" : rs.getString("NO_KP_BARU"));
						h.put("ID_KEMENTERIAN",rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
						h.put("ID_AGENSI",rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						h.put("NAMA_KEMENTERIAN",rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
						h.put("NAMA_AGENSI",rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI"));
						h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
						h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
						h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));				
						h.put("NEGERI_KEMENTERIAN",rs.getString("NEGERI_KEMENTERIAN") == null ? "" : rs.getString("NEGERI_KEMENTERIAN"));
						h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
						h.put("KEAKTIFAN",rs.getString("KEAKTIFAN") == null ? "" : rs.getString("KEAKTIFAN"));
						h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
						h.put("NAMA_JAWATAN",rs.getString("NAMA_JAWATAN") == null ? "" : rs.getString("NAMA_JAWATAN"));
						h.put("TEMPOH_SAH",rs.getString("TEMPOH_SAH") == null ? "" : rs.getString("TEMPOH_SAH"));
						
						
					}
				}
				else
				{
					h.put("USER_ID","");
					h.put("USER_LOGIN","");
					h.put("NAMA_PENUH","");
					h.put("USER_NAME","");
					h.put("USER_ROLE","");
					h.put("NAMA_ROLE","");
					h.put("USER_TYPE","");
					h.put("TARIKH_DAFTAR","");
					h.put("LAST_CHANGEPASSWORD","");
					h.put("DAYS_AFTERCHANGEPASS","");
					h.put("DAYS_AFTERLASTLOGIN","");
					h.put("WAKTU_AKHIR_LOGIN","");
					//h.put("NO_KP_BARU","");
					h.put("ID_KEMENTERIAN","");
					h.put("ID_AGENSI","");
					h.put("EMEL","");
					h.put("NAMA_KEMENTERIAN","");
					h.put("NAMA_AGENSI","");
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");				
					h.put("NEGERI_KEMENTERIAN","");/*
					h.put("NO_KP_BARU1","");
					h.put("NO_KP_BARU2","");
					h.put("NO_KP_BARU3","");*/
					h.put("FLAG_AKTIF","");
					h.put("KEAKTIFAN","");
					h.put("ID_JAWATAN","");
					h.put("NAMA_JAWATAN","");			
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
		
		
		public Hashtable viewAlamatKJP(HttpSession session, String ID_KEMENTERIAN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = " SELECT TRK.ID_KEMENTERIAN AS ID, TRK.KOD_KEMENTERIAN AS KOD, UPPER(TRK.NAMA_KEMENTERIAN) AS KETERANGAN,  "+
						" UPPER(TRK.ALAMAT1) AS ALAMAT1, UPPER(TRK.ALAMAT2) AS ALAMAT2, UPPER(TRK.ALAMAT3) AS ALAMAT3, TRK.POSKOD, "+ 
						" TRK.ID_NEGERI, UPPER(TRN.NAMA_NEGERI) AS NAMA_NEGERI "+
						" FROM TBLRUJKEMENTERIAN TRK, TBLRUJNEGERI TRN "+
						" WHERE TRK.ID_KEMENTERIAN IS NOT NULL AND TRK.ID_KEMENTERIAN = '"+ID_KEMENTERIAN+"'  AND TRK.ID_NEGERI = TRN.ID_NEGERI(+) ";
				myLogger.info(" viewAlamatKJP :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				Hashtable h;
				h = new Hashtable();
				
				while (rs.next()) {
					h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
					h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
					h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
					h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
					h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
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
		
		public Hashtable viewDataPenggunaOnline(HttpSession session, String USER_ID, String USER_LOGIN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!USER_ID.equals("") || !USER_LOGIN.equals(""))
				{
					sql = queryUserOnline(session,USER_ID, "",USER_LOGIN);
					myLogger.info(" viewDataPenggunaOnline :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("PASSWORD","");
						h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
						h.put("NAMA_PENUH",rs.getString("NAMA_PENUH") == null ? "" : rs.getString("NAMA_PENUH"));
						h.put("USER_NAME",rs.getString("NAMA_PENUH") == null ? "" : rs.getString("NAMA_PENUH"));
						h.put("USER_ROLE",rs.getString("USER_ROLE") == null ? "" : rs.getString("USER_ROLE"));
						h.put("NAMA_ROLE",rs.getString("NAMA_ROLE") == null ? "" : rs.getString("NAMA_ROLE"));
						h.put("USER_TYPE",rs.getString("USER_TYPE") == null ? "" : rs.getString("USER_TYPE"));
						h.put("TARIKH_DAFTAR",rs.getString("TARIKH_DAFTAR") == null ? "" : rs.getString("TARIKH_DAFTAR"));
						h.put("LAST_CHANGEPASSWORD",rs.getString("LAST_CHANGEPASSWORD") == null ? "" : rs.getString("LAST_CHANGEPASSWORD"));
						h.put("DAYS_AFTERCHANGEPASS",rs.getString("DAYS_AFTERCHANGEPASS") == null ? "" : rs.getString("DAYS_AFTERCHANGEPASS"));
						h.put("DAYS_AFTERLASTLOGIN",rs.getString("DAYS_AFTERLASTLOGIN") == null ? "" : rs.getString("DAYS_AFTERLASTLOGIN"));
						h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
						h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
						h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
						h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
						h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
						h.put("NO_PENGENALAN",rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
						h.put("UMUR",rs.getString("UMUR") == null ? "" : rs.getString("UMUR"));
						h.put("JANTINA",rs.getString("JANTINA") == null ? "" : rs.getString("JANTINA"));
						h.put("JANTINA_KETERANGAN",rs.getString("JANTINA_KETERANGAN") == null ? "" : rs.getString("JANTINA_KETERANGAN"));
						h.put("TARAF_PERKAHWINAN_KETERANGAN",rs.getString("TARAF_PERKAHWINAN_KETERANGAN") == null ? "" : rs.getString("TARAF_PERKAHWINAN_KETERANGAN"));
						h.put("TARAF_PERKAHWINAN",rs.getString("TARAF_PERKAHWINAN") == null ? "" : rs.getString("TARAF_PERKAHWINAN"));
						h.put("TARIKH_LAHIR",rs.getString("TARIKH_LAHIR") == null ? "" : rs.getString("TARIKH_LAHIR"));
						h.put("KATEGORI",rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());		
						h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP").toUpperCase().trim());
						h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL").toUpperCase().trim());
						h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase().trim());	
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						h.put("WAKTU_AKHIR_LOGIN",rs.getString("WAKTU_AKHIR_LOGIN") == null ? "" : rs.getString("WAKTU_AKHIR_LOGIN"));
						h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
						h.put("KEAKTIFAN",rs.getString("KEAKTIFAN") == null ? "" : rs.getString("KEAKTIFAN"));
						h.put("WAKTU_AKHIR_LOGIN",rs.getString("WAKTU_AKHIR_LOGIN") == null ? "" : rs.getString("WAKTU_AKHIR_LOGIN"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						
						
						if((rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI")).equals("Individu") 
								|| (rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI")).equals(""))
						{
							if(rs.getString("NO_PENGENALAN")!=null && !rs.getString("NO_PENGENALAN").equals(""))
							{
								h.put("NO_PENGENALAN1", rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN").substring(0, 6));
								h.put("NO_PENGENALAN2", rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN").substring(6, 8));
								h.put("NO_PENGENALAN3", rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN").substring(8, 12));
								h.put("NO_PENGENALAN",rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN"));
							}
							else
							{
								h.put("NO_PENGENALAN1","");
								h.put("NO_PENGENALAN2","");
								h.put("NO_PENGENALAN3","");
								h.put("NO_PENGENALAN","");
							}
						}
						else if(rs.getString("KATEGORI").equals("Syarikat"))
						{
							h.put("NO_PENGENALAN",rs.getString("NO_PENGENALAN") == null ? "" : rs.getString("NO_PENGENALAN"));
							h.put("NO_PENGENALAN1","");
							h.put("NO_PENGENALAN2","");
							h.put("NO_PENGENALAN3","");
						}
					}
				}
				else
				{
					h.put("USER_ID","");
					h.put("PASSWORD","");
					h.put("USER_LOGIN","");
					h.put("NAMA_PENUH","");
					h.put("USER_NAME","");
					h.put("USER_ROLE","");
					h.put("NAMA_ROLE","");
					h.put("USER_TYPE","");
					h.put("TARIKH_DAFTAR","");
					h.put("LAST_CHANGEPASSWORD","");
					h.put("DAYS_AFTERCHANGEPASS","");
					h.put("DAYS_AFTERLASTLOGIN","");
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("NEGERI","");
					h.put("ID_NEGERI","");
					h.put("BANDAR","");
					h.put("ID_BANDAR","");
					h.put("NO_PENGENALAN","");
					h.put("UMUR","");
					h.put("JANTINA","");
					h.put("JANTINA_KETERANGAN","");
					h.put("TARAF_PERKAHWINAN_KETERANGAN","");
					h.put("TARAF_PERKAHWINAN","");
					h.put("TARIKH_LAHIR","");
					h.put("KATEGORI","INDIVIDU");
					h.put("NO_HP","");
					h.put("NO_TEL","");
					h.put("NO_FAX","");
					h.put("EMEL","");
					h.put("WAKTU_AKHIR_LOGIN","");
					h.put("FLAG_AKTIF","");
					h.put("KEAKTIFAN","");
					h.put("NO_PENGENALAN1","");
					h.put("NO_PENGENALAN2","");
					h.put("NO_PENGENALAN3","");
					h.put("TARIKH_KEMASKINI","");
					

					
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
		
		public String queryUserOnline(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {	
			String query = " SELECT "+
			" U.USER_ID, U.USER_LOGIN, UPPER(U.USER_NAME) AS NAMA_PENUH, U.USER_ROLE, UPPER(R.DESCRIPTION) AS NAMA_ROLE, "+
			" U.USER_TYPE, TO_CHAR(U.DATE_REGISTERED,'DD/MM/YYYY') AS TARIKH_DAFTAR, U.LAST_CHANGEPASSWORD, "+
			" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS,  "+
			" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN, "+
			" TO_CHAR(MAX(WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN, "+
			" UPPER(UO.ALAMAT1) AS ALAMAT1,UPPER(UO.ALAMAT2) AS ALAMAT2,UPPER(UO.ALAMAT3) AS ALAMAT3,UO.POSKOD,  "+
			" UPPER(NEG.NAMA_NEGERI) AS NEGERI, NEG.ID_NEGERI, "+
			" UPPER(BAN.KETERANGAN) AS BANDAR, BAN.ID_BANDAR, "+
			" UPPER(NVL(UO.NO_KP_BARU,'')) AS NO_PENGENALAN,  "+
			" UO.UMUR, UO.JANTINA,  "+
			" (CASE WHEN UO.JANTINA = 'L' THEN 'LELAKI' "+
			" WHEN UO.JANTINA = 'P' THEN 'PEREMPUAN' ELSE '' END) AS JANTINA_KETERANGAN, "+
			" (CASE WHEN UO.TARAF_PERKAHWINAN = 'B' THEN 'BUJANG' "+
			" WHEN UO.TARAF_PERKAHWINAN = 'K' THEN 'BERKAHWIN' ELSE '' END) AS TARAF_PERKAHWINAN_KETERANGAN, "+
			" UO.TARAF_PERKAHWINAN, TO_CHAR(UO.TARIKH_LAHIR,'DD/MM/YYYY') AS TARIKH_LAHIR, "+
			" UO.KATEGORI, UO.NO_HP,UO.NO_TEL,UO.NO_FAX,UO.EMEL,U.FLAG_AKTIF,TO_CHAR(U.TARIKH_KEMASKINI,'DD/MM/YYYY')AS TARIKH_KEMASKINI, "+
			" (CASE WHEN U.FLAG_AKTIF = 1 THEN 'AKTIF' "+
			" WHEN U.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
			" ELSE 'AKTIF' END) AS KEAKTIFAN " +
			" FROM USERS U, USERS_ONLINE UO, ROLE R, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN "+
			" WHERE U.USER_ID = UO.USER_ID "+
			" AND U.USER_ROLE = R.NAME(+) "+
			" AND U.USER_LOGIN = WL.USER_NAME(+) "+
			" AND UO.ID_NEGERI = NEG.ID_NEGERI(+) "+
			" AND UO.ID_BANDAR = BAN.ID_BANDAR(+) ";
			
			if(!USER_ID.trim().equals(""))
			{
				query += " AND U.USER_ID = '"+USER_ID.trim()+"' ";
			}
			if(!USER_LOGIN.trim().equals(""))
			{
				//query += " AND UPPER(U.USER_LOGIN) = '"+USER_LOGIN.toUpperCase()+"' ";
				query += " AND U.USER_LOGIN = '"+USER_LOGIN+"' ";
			}
			
			if(!carianTerperinci.equals(""))
			{
				query += " AND (" +
						" U.USER_LOGIN LIKE '%"+carianTerperinci+"%' " +
						" OR UO.NO_KP_BARU LIKE '%"+carianTerperinci+"%' " +
						" OR UPPER(U.USER_NAME) LIKE UPPER('%"+carianTerperinci+"%') " +
						" OR UPPER(NEG.NAMA_NEGERI) LIKE UPPER('%"+carianTerperinci+"%') " +
						" OR UO.EMEL LIKE '%"+carianTerperinci+"%' " +
										") ";
			}
			
			query += " GROUP BY U.USER_ID, U.USER_LOGIN, U.USER_NAME, U.USER_ROLE, R.DESCRIPTION, U.USER_TYPE, U.DATE_REGISTERED, "+
			" U.LAST_CHANGEPASSWORD, U.LAST_CHANGEPASSWORD, UO.ALAMAT1, UO.ALAMAT2, UO.ALAMAT3, UO.POSKOD, NEG.NAMA_NEGERI, NEG.ID_NEGERI, "+
			" BAN.KETERANGAN, BAN.ID_BANDAR,UO.NO_KP_BARU,UO.UMUR,UO.JANTINA,UO.TARAF_PERKAHWINAN,UO.TARIKH_LAHIR,UO.KATEGORI, " +
			" UO.NO_HP,UO.NO_TEL,UO.NO_FAX, UO.EMEL,U.FLAG_AKTIF,U.TARIKH_KEMASKINI " +
			" ORDER BY U.USER_NAME ";
			
			return query;
		}
		
		public String simpanPenggunaOnline(HttpSession session,String USER_ID, String internalType) throws Exception {
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			String flag_operasi = "INSERT";
			if(!USER_ID.equals(""))
			{
				flag_operasi = "UPDATE";
			}
			long USER_ID_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String GET_USER_ID_EXIST = getParam("GET_USER_ID_EXIST_"+internalType+USER_ID);
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");			
				String USER_LOGIN = getParam("USER_LOGIN_"+internalType+USER_ID);
				String PASSWORD = getParam("PASSWORD_"+internalType+USER_ID);
				String USER_NAME = getParam("USER_NAME_"+internalType+USER_ID);
				
				String KATEGORI = getParam("KATEGORI_"+internalType+USER_ID);			
				String NO_PENGENALAN = "";
				if(KATEGORI.equals("") || KATEGORI.equals("Individu"))
				{
					NO_PENGENALAN = getParam("NO_PENGENALAN1_"+internalType+USER_ID)+getParam("NO_PENGENALAN2_"+internalType+USER_ID)+getParam("NO_PENGENALAN3_"+internalType+USER_ID);
				}
				else
				{
					NO_PENGENALAN = getParam("NO_PENGENALAN_"+internalType+USER_ID);
				}
							
				String EMEL = getParam("EMEL_"+internalType+USER_ID);
				String ALAMAT1 = getParam("ALAMAT1_"+internalType+USER_ID);
				String ALAMAT2 = getParam("ALAMAT2_"+internalType+USER_ID);
				String ALAMAT3 = getParam("ALAMAT3_"+internalType+USER_ID);
				String POSKOD = getParam("POSKOD_"+internalType+USER_ID);
				String ID_NEGERI = getParam("ID_NEGERI_"+internalType+USER_ID);
				String ID_BANDAR = getParam("ID_BANDAR_"+internalType+USER_ID);
				String UMUR = getParam("UMUR_"+internalType+USER_ID);
				String JANTINA = getParam("JANTINA_"+internalType+USER_ID);
				String TARAF_PERKAHWINAN = getParam("TARAF_PERKAHWINAN_"+internalType+USER_ID);			
				String TARIKH_LAHIR = getParam("TARIKH_LAHIR_"+internalType+USER_ID);
				String ROLE_MAIN = getParam("ROLE_MAIN_"+internalType+USER_ID);
				
				if(!TARIKH_LAHIR.equals(""))
				{
				 TARIKH_LAHIR = "to_date('" +TARIKH_LAHIR+ "','dd/MM/yyyy')";
				}
				String NO_HP = getParam("NO_HP_"+internalType+USER_ID);
				String NO_TEL = getParam("NO_TEL_"+internalType+USER_ID);
				String NO_FAX = getParam("NO_FAX_"+internalType+USER_ID);
				
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+internalType+USER_ID);
				
				
					if(flag_operasi.equals("INSERT"))
					{
						if(GET_USER_ID_EXIST.equals(""))
						{
							USER_ID_INSERT = DB.getNextID(db,"USERS_SEQ");
							returnUSERID = USER_ID_INSERT+"";
						}
						else
						{
							USER_ID_INSERT = Long.parseLong(GET_USER_ID_EXIST);
							returnUSERID = USER_ID_INSERT+"";
						}
					}
					else
					{
						returnUSERID = USER_ID;
					}
				
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
					if(flag_operasi.equals("INSERT"))
					{
						if(GET_USER_ID_EXIST.equals(""))
						{
							r.add("USER_ID", USER_ID_INSERT);	
							r.add("USER_ROLE", ROLE_MAIN);
							r.add("USER_TYPE", "online");
						}
						else
						{
							r.update("USER_ID", GET_USER_ID_EXIST);
						}
					}
					else
					{
						r.update("USER_ID", USER_ID);
						r.add("USER_ROLE", ROLE_MAIN);
					}
				
				
				r.add("USER_LOGIN", USER_LOGIN);
				if(!PASSWORD.equals(""))
				{
					r.add("USER_PASSWORD", PasswordService.encrypt(PASSWORD));
					r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
				}			
				r.add("USER_NAME", USER_NAME.toUpperCase());
				r.add("DATE_REGISTERED", r.unquote("sysdate"));
				//r.add("USER_TYPE", USER_TYPE);
				 r.add("FLAG_AKTIF", FLAG_AKTIF);
				if(flag_operasi.equals("INSERT"))
				{
					if(GET_USER_ID_EXIST.equals(""))
					{
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						 r.add("FLAG_AKTIF", FLAG_AKTIF);
						sql = r.getSQLInsert("USERS");		
						myLogger.info("V3 : INSERT USERS : "+sql);
						this.context.put("SuccessMesej", "Insert");
						AuditTrail.logActivity(this,session,"INS","USER ["+USER_NAME+"] Added");	
					}
					else
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("USERS");		
						myLogger.info("V3 : UPDATE USERS : "+sql);
						this.context.put("SuccessMesej", "Update");
						AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
					}
				}
				else
				{
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("USERS");		
					myLogger.info("V3 : UPDATE USERS : "+sql);
					this.context.put("SuccessMesej", "Update");
					AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
				}		
				stmt.executeUpdate(sql);
				
				
				r.clear();
				sql = "";
				if(flag_operasi.equals("INSERT"))
				{
					r.add("USER_ID", USER_ID_INSERT);
				}
				else
				{
					r.update("USER_ID", USER_ID);
				}
				
				r.add("ALAMAT1", ALAMAT1.toUpperCase());
				r.add("ALAMAT2", ALAMAT2.toUpperCase());
				r.add("ALAMAT3", ALAMAT3.toUpperCase());
				r.add("POSKOD", POSKOD);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_BANDAR", ID_BANDAR);
				r.add("NO_HP", NO_HP);
				r.add("NO_TEL", NO_TEL);
				r.add("NO_FAX", NO_FAX);
				r.add("NO_KP_BARU", NO_PENGENALAN);
				r.add("UMUR", UMUR);			
				r.add("JANTINA", JANTINA);
				r.add("TARAF_PERKAHWINAN", TARAF_PERKAHWINAN);
				r.add("KATEGORI", KATEGORI);
				r.add("EMEL", EMEL);
			    r.add("FLAG_AKTIF", FLAG_AKTIF);
				if (!TARIKH_LAHIR.equals("")){
				r.add("TARIKH_LAHIR", r.unquote(TARIKH_LAHIR));
				} else {
					r.add("TARIKH_LAHIR", "");	
				}
				if(flag_operasi.equals("INSERT"))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("USERS_ONLINE");		
					myLogger.info("V3 : INSERT USERS_ONLINE : "+sql);
				}
				else
				{
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("USERS_ONLINE");		
					myLogger.info("V3 : UPDATE USERS_ONLINE : "+sql);
				}		
				stmt.executeUpdate(sql);
				
				
				if(flag_operasi.equals("INSERT"))
				{
					if(!GET_USER_ID_EXIST.equals(""))
					{
					//add user role online	
						r.clear();
						r.add("USER_ID", USER_LOGIN);
						r.add("ROLE_ID", "online");
						sql = r.getSQLInsert("USER_ROLE");		
						myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
						stmt.executeUpdate(sql);
					}
					
					
						r.clear();
						r.add("USER_ID", USER_LOGIN);
						r.add("ROLE_ID", "ppt-online-user");
						sql = r.getSQLInsert("USER_ROLE");		
						myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
						stmt.executeUpdate(sql);
						
						r.clear();
						r.add("USER_ID", USER_LOGIN);
						r.add("ROLE_ID", "php-online-user");
						sql = r.getSQLInsert("USER_ROLE");		
						myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
						stmt.executeUpdate(sql);
						
						r.clear();
						r.add("USER_ID", USER_LOGIN);
						r.add("ROLE_ID", "ppk-online-user");
						sql = r.getSQLInsert("USER_ROLE");		
						myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
						stmt.executeUpdate(sql);
					
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
		    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return returnUSERID;
		}
		
		public String simpanPenggunaKJP(HttpSession session,String USER_ID, String internalType) throws Exception {
			
			
			Connection conn = null;
			Db db = null;
			String returnUSERID = "";
			String sql = "";
			
			String sendEmel = "";
			
			String flag_operasi = "INSERT";
			if(!USER_ID.equals(""))
			{
				flag_operasi = "UPDATE";
			}
			long USER_ID_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
				String GET_USER_ID_EXIST = getParam("GET_USER_ID_EXIST_"+internalType+USER_ID);
				String USER_LOGIN = getParam("USER_LOGIN_"+internalType+USER_ID);
				String PASSWORD = getParam("PASSWORD_"+internalType+USER_ID);
				String USER_NAME = getParam("USER_NAME_"+internalType+USER_ID);
				String EMEL = getParam("EMEL_"+internalType+USER_ID);			
				String JENIS_PENGGUNA = getParam("JENIS_PENGGUNA_"+internalType+USER_ID);
				String ID_JAWATAN = getParam("ID_JAWATAN_"+internalType+USER_ID);			
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN_"+internalType+USER_ID);
				String ID_AGENSI = getParam("ID_AGENSI_"+internalType+USER_ID);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+internalType+USER_ID);
				//String ROLE_MAIN = getParam("ROLE_MAIN_"+internalType+USER_ID);
				
				/*TAMBAH ADMIN*/
				String TEMPOH_SAH = getParam("TEMPOH_SAH"+internalType+USER_ID);
				String DATE_DAYS_AFTERCHANGEPASS = getParam("DATE_DAYS_AFTERCHANGEPASS"+internalType+USER_ID);
				String WAKTU_AKHIR_LOGIN = getParam("WAKTU_AKHIR_LOGIN"+internalType+USER_ID);
				
				/*TAMAT*/
				
				String USER_TYPE = "";
				if(internalType.equals("HQ") || internalType.equals("Negeri") || internalType.equals("KJP"))
				{
					USER_TYPE = "internal";
				}
				
				
				if(flag_operasi.equals("INSERT"))
				{
					if(GET_USER_ID_EXIST.equals(""))
					{
						USER_ID_INSERT = DB.getNextID(db,"USERS_SEQ");
						returnUSERID = USER_ID_INSERT+"";
					}
					else
					{
						USER_ID_INSERT = Long.parseLong(GET_USER_ID_EXIST);
						returnUSERID = USER_ID_INSERT+"";
					}
					
				}
				else
				{
					returnUSERID = USER_ID;
				}
				
				
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				if(flag_operasi.equals("INSERT"))
				{
					if(GET_USER_ID_EXIST.equals(""))
					{
						r.add("USER_ID", USER_ID_INSERT);
						r.add("USER_ROLE", JENIS_PENGGUNA);
						r.add("USER_TYPE", USER_TYPE);
					}
					else
					{
						r.update("USER_ID", GET_USER_ID_EXIST);
						
					}
				}
				else
				{
					r.update("USER_ID", USER_ID);
				}
				
				r.add("USER_LOGIN", USER_LOGIN);
				if(!PASSWORD.equals(""))
				{
					r.add("USER_PASSWORD", PasswordService.encrypt(PASSWORD));
					
					sendEmel = "Y";
				}			
				r.add("USER_NAME", USER_NAME.toUpperCase());
				
				r.add("DATE_REGISTERED", r.unquote("sysdate"));
				r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
				 r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				if(flag_operasi.equals("INSERT"))
				{
					if(GET_USER_ID_EXIST.equals(""))
					{
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						sql = r.getSQLInsert("USERS");		
						myLogger.info("V3 : INSERT USERS : "+sql);
						this.context.put("SuccessMesej", "Insert");
						AuditTrail.logActivity(this,session,"INS","USER ["+USER_NAME+"] Added");
					}
					else
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("USERS");		
						myLogger.info("V3 : UPDATE USERS : "+sql);
						this.context.put("SuccessMesej", "Update");
						AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
					}
				}
				else
				{
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("USERS");		
					myLogger.info("V3 : UPDATE USERS : "+sql);
					this.context.put("SuccessMesej", "Update");
					AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
				}		
				stmt.executeUpdate(sql);
				
				
				r.clear();
				sql = "";
				if(flag_operasi.equals("INSERT"))
				{
					
					if(GET_USER_ID_EXIST.equals(""))
					{
						r.add("USER_ID", USER_ID_INSERT);
					}
					else
					{
						if(!checkUsersInternal(session,GET_USER_ID_EXIST).equals(""))
						{
							r.update("USER_ID", GET_USER_ID_EXIST);
						}
					}
					
				}
				else
				{
					r.update("USER_ID", USER_ID);
				}
				r.add("ID_JAWATAN", ID_JAWATAN);
				r.add("EMEL", EMEL);
				 r.add("FLAG_AKTIF", FLAG_AKTIF);
				if(flag_operasi.equals("INSERT"))
				{
					if(GET_USER_ID_EXIST.equals(""))
					{
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						sql = r.getSQLInsert("USERS_INTERNAL");		
						myLogger.info("V3 : INSERT USERS INTERNAL : "+sql);
					}
					else
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
						sql = r.getSQLUpdate("USERS_INTERNAL");		
						myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
					}
				}
				else
				{
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("USERS_INTERNAL");		
					myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
				}		
				stmt.executeUpdate(sql);
				
				
				
				
				
				
				
				r.clear();
				sql = "";
				if(flag_operasi.equals("INSERT"))
				{
					r.add("ID_USERSKEMENTERIAN", DB.getNextID(db,"USERS_KEMENTERIAN_SEQ"));
					r.add("USER_ID", USER_ID_INSERT);				
				}
				else
				{
					r.update("USER_ID", USER_ID);
				}
				r.add("ID_KEMENTERIAN", ID_KEMENTERIAN);
				r.add("ID_AGENSI", ID_AGENSI);
				
				
				if(flag_operasi.equals("INSERT"))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("USERS_KEMENTERIAN");		
					myLogger.info("V3 : INSERT USERS KEMENTERIAN : "+sql);
					
				}
				else
				{
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					sql = r.getSQLUpdate("USERS_KEMENTERIAN");		
					myLogger.info("V3 : UPDATE USERS KEMENTERIAN : "+sql);
				}		
				stmt.executeUpdate(sql);
				
				
				
				
				
				
				if(flag_operasi.equals("INSERT"))
				{
					if(!GET_USER_ID_EXIST.equals(""))
					{
					//add user role KJP	
						r.clear();
						r.add("USER_ID", USER_LOGIN);
						r.add("ROLE_ID", JENIS_PENGGUNA);
						sql = r.getSQLInsert("USER_ROLE");		
						myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
						stmt.executeUpdate(sql);
					}
				}
				else
				{
					r.clear();				
					r.update("USER_ID", USER_LOGIN);
					if(JENIS_PENGGUNA.equals("online_kjp"))
					{
						r.update("ROLE_ID", "online_kjpagensi");
						r.add("ROLE_ID", "online_kjp");
					}				
					else if(JENIS_PENGGUNA.equals("online_kjpagensi"))
					{
						r.update("ROLE_ID", "online_kjp");
						r.add("ROLE_ID", "online_kjpagensi");
					}				
					sql = r.getSQLUpdate("USER_ROLE");		
					myLogger.info("V3 : INSERT/UPDATE USERS_ROLE : "+sql);
					stmt.executeUpdate(sql);
					
					
					r.clear();				
					r.update("USER_ID", USER_ID);
					if(JENIS_PENGGUNA.equals("online_kjp"))
					{
						r.update("USER_ROLE", "online_kjpagensi");
						r.add("USER_ROLE", "online_kjp");
					}
					else if(JENIS_PENGGUNA.equals("online_kjpagensi"))
					{
						r.update("USER_ROLE", "online_kjp");
						r.add("USER_ROLE", "online_kjpagensi");
					}				
					sql = r.getSQLUpdate("USERS");		
					myLogger.info("V3 : INSERT/UPDATE USERS : "+sql);
					stmt.executeUpdate(sql);
					
					
					
				}

				conn.commit();
				
				/*if(sendEmel.equals("Y"))
				{
				//tambah hantar emel kpd bpict (sebab ada perubahan)
				hantarEmelStaff(session,USER_ID+"",1);
				}*/
				
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
			return returnUSERID;
		}
		
//tambah function emel untuk user KJP
public void hantarEmelStaff(HttpSession session,String USER_ID,Integer STATUS) throws Exception {
			
			myLogger.info("MASUK FUNCTION EMEL STAFF PERUBAHAN : "+ USER_ID);	
			
			EmailProperty pro = EmailProperty.getInstance();
			EmailSender email = EmailSender.getInstance();
			email.FROM = pro.getFrom();
			Hashtable viewPengguna = viewDataPenggunaInternal(session, USER_ID, "");
			
			
			String subject = "";
			String content = "";

				
				subject = " Sistem MyeTaPP - Perubahan Maklumat Diri Pengguna di dalam sistem. ";
				content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
				content+= " Untuk makluman, pengguna ini telah membuat perubahan pada profil pengguna beliau. <br>";
				content+= " Maklumat lanjut adalah seperti berikut : <br><br>";
				content+= " Maklumat Pengguna : <br>";
				
				content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama Kementerian</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("USER_LOGIN")+"</td></tr>" ;
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Alamat Kementerian</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("ALAMAT1")+"</td></tr>" +
						" <tr><td valign='top'></td><td valign='top'></td>" +
						" <td valign='top' ></td><td valign='top'>"+viewPengguna.get("ALAMAT2")+"</td></tr>" +
						" <tr><td valign='top'></td><td valign='top'></td>" +
						" <td valign='top' ></td><td valign='top'>"+viewPengguna.get("ALAMAT3")+"</td></tr>" +
						" <tr><td valign='top'></td><td valign='top'></td>" +
						" <td valign='top' ></td><td valign='top'>"+viewPengguna.get("POSKOD")+" "+viewPengguna.get("NEGERI_KEMENTERIAN")+"</td></tr>" ;
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama Agensi</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_AGENSI")+"</td></tr>" ;
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>ID Pengguna</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("USER_LOGIN")+"</td></tr>" ;
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("USER_NAME")+"</td></tr>" ;
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Jawatan</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_JAWATAN")+"</td></tr>" ;
				
				/*content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>No Tel</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NO_HP")+"</td></tr>" ;*/
				
				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Emel</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("EMEL")+"</td></tr>" ;
				
				content+= " </table> <br>";
				
				content+= " <strong>Sila buat tindakan selanjutnya. </strong>  <br><br>";
				
			//	content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";
				
				content+= " Sekian, terima kasih.<br><br><br>";
				
				content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";
			
			email.SUBJECT = subject;
			email.MESSAGE = content;		
			
			 //diba tambah untuk emel KJP
			 List listPenggunaMengikutRole = null;
			 
			//edit role adminekptg?? --KJP? admin apa?
			 //hq adminekptg, negeri admin_negeri, admin->contoh je
			 String idNegeriUser = (String) viewPengguna.get("ID_NEGERI");
			 
			 if (idNegeriUser.equals("16")){
			listPenggunaMengikutRole = listPenggunaMengikutRole(session,"admin",idNegeriUser);
			 } else {
			listPenggunaMengikutRole = listPenggunaMengikutRole(session,"admin_negeri",idNegeriUser);	 
			 }
			
			//GET EMEL PEGAWAI ADMINEKPTG MENGIKUT NEGERI
			email.MULTIPLE_RECIEPIENT = new String[listPenggunaMengikutRole.size()];
			for(int i = 0; i < listPenggunaMengikutRole.size();i++)
			   {			   
				   Map m = (Map) listPenggunaMengikutRole.get(i);
				   myLogger.info(" EMEL PENGGUNA :"+(String) m.get("EMEL"));
				   //EMEL UNTUK PENGGUNA
				   email.MULTIPLE_RECIEPIENT[i] = (String) m.get("EMEL");		
						   //"simple1001plan@gmail.com";//	  
			   }
			
			/* SELECT   DISTINCT U.USER_ID,
		   U.USER_LOGIN,
		   U.USER_NAME,
		   UI.EMEL,
		   UI.ID_NEGERI
		FROM   USERS U, USERS_INTERNAL UI, USER_ROLE UR
		WHERE       U.USER_LOGIN = UR.USER_ID
		AND U.USER_ID = UI.USER_ID
		AND UR.ROLE_ID = 'admin' --userid=900706145580
		AND UI.ID_NEGERI = '10'
		AND UI.EMEL IS NOT NULL
		ORDER BY   U.USER_NAME*/
			
			//email.TO_CC = new String[1];		
			//email.TO_CC[0] = "razman.zainal@gmail.com";
			email.sendEmail();	
			
			
		}

@SuppressWarnings("unchecked")
public List listPenggunaMengikutRole(HttpSession session,String ROLE_ID,String ID_NEGERI)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listPengunaByRoleNegeri = null;
	String sql = "";
	
	try {
		db = new Db();
		stmt = db.getStatement();			
		
	    sql = " SELECT DISTINCT U.USER_ID,U.USER_LOGIN,U.USER_NAME, UI.EMEL, UI.ID_NEGERI " +
	    		" FROM USERS U,USERS_INTERNAL UI, USER_ROLE UR "+
	    		" WHERE U.USER_LOGIN = UR.USER_ID AND U.USER_ID = UI.USER_ID "+
	    		" AND UR.ROLE_ID = '"+ROLE_ID+"' "+
	    		" AND UI.ID_NEGERI = '"+ID_NEGERI+"' "+
	    		" AND UI.EMEL IS NOT NULL" +
	    		" ORDER BY U.USER_NAME ";				
		
		myLogger.info(" SQL listPenggunaMengikutRole :"+ sql);			
		rs = stmt.executeQuery(sql);
		listPengunaByRoleNegeri = Collections.synchronizedList(new ArrayList());
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
			//U.USER_ID,U.USER_LOGIN,U.USER_NAME, UI.EMEL, UI.ID_NEGERI
			h.put("rowCss",rowCss);
			h.put("BIL",bil);
			h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));	
			h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
			h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
			h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
			h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
			listPengunaByRoleNegeri.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
	return listPengunaByRoleNegeri;

}
@SuppressWarnings("unchecked")
public List listPejabatIntegrasi(HttpSession session, String ID_NEGERI, String ID_JENISPEJABAT)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listPejabatIntegrasi = null;
	String sql = "";		
	try {
		db = new Db();
		stmt = db.getStatement();
		
		if (ID_JENISPEJABAT.equals("")){
		
		sql = 	" SELECT DISTINCT JP.KETERANGAN AS JENISPEJABAT, JP.ID_JENISPEJABAT, " +
				" '' AS ID_PEJABAT, '' AS NAMA_PEJABAT " +
				" FROM TBLRUJPEJABAT P, TBLRUJINTEGRASI I, TBLRUJJENISPEJABAT JP " +
				" WHERE P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT " +
				" AND I.ID_JENISPEJABAT = JP.ID_JENISPEJABAT AND P.FLAG_INT = 1 ";
		}
		else {
			
		sql = 	" SELECT DISTINCT JP.KETERANGAN AS JENISPEJABAT, P.ID_PEJABAT, " +
				" P.NAMA_PEJABAT, JP.ID_JENISPEJABAT " +
				" FROM TBLRUJPEJABAT P, TBLRUJINTEGRASI I, TBLRUJJENISPEJABAT JP " +
				" WHERE P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT " +
				" AND I.ID_JENISPEJABAT = JP.ID_JENISPEJABAT AND P.FLAG_INT = 1 ";
		}
			
			
			
		if (!ID_JENISPEJABAT.equals("")){
			
			sql += " AND I.ID_JENISPEJABAT = "+ID_JENISPEJABAT;
		}
		
		if (!ID_NEGERI.equals("")){
			
			sql += " AND P.ID_NEGERI = "+ID_NEGERI;
		}
		
		myLogger.info(" SQL LIST PEJABAT URUSAN :"+ sql);
		rs = stmt.executeQuery(sql);
		listPejabatIntegrasi = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		int new_bil = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			bil++;
			
			/*if(rs.getString("LAYER").equals("1"))
			{
				new_bil = 0;
			}
			if(rs.getString("LAYER").equals("2"))
			{
				new_bil++;
			}
			
			h.put("BIL",bil);
			h.put("NEWBIL",new_bil);
			
			h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
			h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
			h.put("JENIS_PEJ",rs.getString("JENIS_PEJ") == null ? "" : rs.getString("JENIS_PEJ").toUpperCase());
			h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
			h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
			h.put("KOD",rs.getString("KOD") == null ? "" : rs.getInt("KOD"));
			h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
			h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));*/
			
			h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
			h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
			h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
			h.put("JENISPEJABAT",rs.getString("JENISPEJABAT") == null ? "" : rs.getString("JENISPEJABAT").toUpperCase());
			
			listPejabatIntegrasi.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}

	return listPejabatIntegrasi;

}

public String simpanPenggunaINT(HttpSession session,String USER_ID, String internalType) throws Exception {
	Connection conn = null;
	Db db = null;
	String returnUSERID = "";
	String sql = "";
	
	String flag_operasi = "INSERT";
	if(!USER_ID.equals(""))
	{
		flag_operasi = "UPDATE";
	}
	long USER_ID_INSERT = 0;
	
	try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		String GET_USER_ID_EXIST = getParam("GET_USER_ID_EXIST_"+internalType+USER_ID);
		String USER_LOGIN = getParam("USER_LOGIN_"+internalType+USER_ID);
		String PASSWORD = getParam("PASSWORD_"+internalType+USER_ID);
		String USER_NAME = getParam("USER_NAME_"+internalType+USER_ID);
		String EMEL = getParam("EMEL_"+internalType+USER_ID);
		String JAWATAN = getParam("JAWATAN_"+internalType+USER_ID);
		
		
		String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT_"+internalType+USER_ID);
		String ID_NEGERI = getParam("ID_NEGERI_"+internalType+USER_ID);
		String ID_DAERAH = getParam("ID_DAERAH_"+internalType+USER_ID);
		String ID_PEJABAT = getParam("ID_PEJABAT_"+internalType+USER_ID);
		
		String ROLE_MAIN = getParam("ROLE_MAIN_"+internalType+USER_ID);
		
		String USER_TYPE = "";
		if(internalType.equals("HQ") || internalType.equals("Negeri") || internalType.equals("INT") || internalType.equals("KJP"))
		{
			USER_TYPE = "internal";
		}
		
		
		if(flag_operasi.equals("INSERT"))
		{
			if(GET_USER_ID_EXIST.equals(""))
			{
				USER_ID_INSERT = DB.getNextID(db,"USERS_SEQ");
				returnUSERID = USER_ID_INSERT+"";
			}
			else
			{
				USER_ID_INSERT = Long.parseLong(GET_USER_ID_EXIST);
				returnUSERID = USER_ID_INSERT+"";
			}
			
		}
		else
		{
			returnUSERID = USER_ID;
		}
		
		
		
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();
		
		if(flag_operasi.equals("INSERT"))
		{
			if(GET_USER_ID_EXIST.equals(""))
			{
				r.add("USER_ID", USER_ID_INSERT);
				r.add("USER_ROLE", ROLE_MAIN);
				r.add("USER_TYPE", "internal");
			}
			else
			{
				r.update("USER_ID", GET_USER_ID_EXIST);
				
			}
		}
		else
		{
			r.update("USER_ID", USER_ID);
			r.add("USER_ROLE", ROLE_MAIN);
		}
		
		r.add("USER_LOGIN", USER_LOGIN);
		if(!PASSWORD.equals(""))
		{
			r.add("USER_PASSWORD", PasswordService.encrypt(PASSWORD));
			r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
		}			
		r.add("USER_NAME", USER_NAME.toUpperCase());
		r.add("DATE_REGISTERED", r.unquote("sysdate"));
		r.add("USER_TYPE", "internal");
		
		
		if(flag_operasi.equals("INSERT"))
		{
			if(GET_USER_ID_EXIST.equals(""))
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("USERS");		
				myLogger.info("V3 : INSERT USERS : "+sql);
				this.context.put("SuccessMesej", "Insert");
				AuditTrail.logActivity(this,session,"INS","USER ["+USER_NAME+"] Added");
			}
			else
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("USERS");		
				myLogger.info("V3 : UPDATE USERS : "+sql);
				this.context.put("SuccessMesej", "Update");
				AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
			}
		}
		else
		{
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("USERS");		
			myLogger.info("V3 : UPDATE USERS : "+sql);
			this.context.put("SuccessMesej", "Update");
			AuditTrail.logActivity(this,session,"UPD","USER ["+USER_NAME+"] Updated");
		}		
		stmt.executeUpdate(sql);
		
		
		r.clear();
		sql = "";
		if(flag_operasi.equals("INSERT"))
		{
			
			if(GET_USER_ID_EXIST.equals(""))
			{
				r.add("USER_ID", USER_ID_INSERT);
			}
			else
			{
				if(!checkUsersInternal(session,GET_USER_ID_EXIST).equals(""))
				{
					r.update("USER_ID", GET_USER_ID_EXIST);
				}
			}
			
		}
		else
		{
			r.update("USER_ID", USER_ID);
		}
		
		if(flag_operasi.equals("INSERT"))
		{
			if(GET_USER_ID_EXIST.equals(""))
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("USERS_INTERNAL");		
				myLogger.info("V3 : INSERT USERS INTERNAL : "+sql);
			}
			else
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				sql = r.getSQLUpdate("USERS_INTERNAL");		
				myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
			}
		}
		else
		{
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("USERS_INTERNAL");		
			myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
		}		
		stmt.executeUpdate(sql);
		
		
		
		
		
		
		
		r.clear();
		sql = "";
		if(flag_operasi.equals("INSERT"))
		{
			r.add("ID_USERSINTEGRASI", DB.getNextID(db,"USERS_INTEGRASI_SEQ"));
			r.add("USER_ID", USER_ID_INSERT);				
		}
		else
		{
			r.update("USER_ID", USER_ID);
		}
		
		r.add("ID_JENISPEJABAT", ID_JENISPEJABAT);
		r.add("ID_NEGERI", ID_NEGERI);
		r.add("ID_DAERAH", ID_DAERAH);
		r.add("ID_PEJABAT", ID_PEJABAT);
		r.add("EMEL", EMEL);
		r.add("JAWATAN", JAWATAN);
		
		
		if(flag_operasi.equals("INSERT"))
		{
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("USERS_INTEGRASI");		
			myLogger.info("V3 : INSERT USERS_INTEGRASI : "+sql);
			
		}
		else
		{
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("USERS_INTEGRASI");		
			myLogger.info("V3 : UPDATE USERS_INTEGRASI : "+sql);
		}		
		stmt.executeUpdate(sql);
		
		if(flag_operasi.equals("INSERT"))
		{
			if(!GET_USER_ID_EXIST.equals(""))
			{
			//add user role online	
				r.clear();
				r.add("USER_ID", USER_LOGIN);
				r.add("ROLE_ID", ROLE_MAIN);
				sql = r.getSQLInsert("USER_ROLE");		
				myLogger.info("V3 : INSERT USERS_ROLE : "+sql);
				stmt.executeUpdate(sql);
			}
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
    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	}
	catch (Exception re) {
		throw re;
	}finally {
		if (db != null)
			db.close();
	}
	return returnUSERID;
}

}
