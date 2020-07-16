package ekptg.view.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import lebah.util.PasswordService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;
//diba tambah

public class UserListModuleV3 extends AjaxBasedModule {
	/**
	 *
	 */
	private static final long serialVersionUID = 1710185038635698513L;
	static Logger myLogger = Logger.getLogger(UserListModuleV3.class);
	String skrin_name = "app/admin/UV3/index.jsp";


	@Override
	public String doTemplate2() throws Exception {

		List listPenggunaInternalHQ = null;
		List listPenggunaInternalNegeri = null;
		List listPenggunaOnline = null;
		Hashtable viewUserAsal = null;
		Hashtable viewUserBaru = null;
		Hashtable viewPengguna = null;
		Hashtable viewPenggunaExist = null;
		Hashtable viewPejabatJKPTG= null;
		Hashtable viewAlamatKJP= null;
		List listAdditionalRoleByUserLogin = null;
		List listDaerahJagaanByIdPejabat = null;

		List list_TBLRUJJAWATAN = null;
		List list_TBLRUJBANGSA = null;
		List list_TBLRUJSEKSYEN = null;
		List list_TBLRUJAGAMA = null;
		List list_TBLRUJNEGERI = null;
		List list_TBLRUJPEJABAT = null;
		List list_TBLRUJBANDAR = null;
		List list_TBLRUJKEMENTERIAN = null;
		List list_TBLRUJAGENSI = null;
		List listPejabatJKPTG = null;
		List listRole = null;
		List listRoleByUserLogin = null;
		List listPenggunaKJP = null;
		List listPengunaAT = null;
		List listPengunaPLA = null;
		Hashtable viewPengunaPLA = null;
		List listPenggunaINT = null;
		List listPengunaHISTORY = null;
		List listPengunaHISTORY_SUB = null;
		List listPengunaHISTORYforPrint = null;

		List list_TBLINTRUJJENISPEJABAT = null;
		List list_TBLRUJDAERAH = null;
		List listPejabat = null;
		Hashtable viewPejabat = null;

		List listPenggunaMOHON = null;
		Hashtable viewPenggunaMOHON = null;

		 List statsOnlineUmur = null;
		 List statsOnlineKategUser = null;
		 List statsOnlineHari = null;
		 List statsOnlineNegeri = null;
		 List statsInternalNegeri = null;
		 List statsKJP = null;
		 List statsIntegrasi = null;

		 List list_TBLRUJGRED = null;
		 List listStatsJawatan = null;
		 List listPenggunaInternalPrint = null;

		 List listDokumen = null;
		 //this.context.put("listDokumen", "");

		 /*Hashtable viewPejabatMajlisAgama = null;*/

		defaultPut();

		HttpSession session = this.request.getSession();
		String command = getParam("command");
		String FlagCari = getParam("FlagCari");
		String filter_negeri = "";
		this.context.put("FlagCari", FlagCari);
		myLogger.info("V3 command : "+command +" FlagCari : "+FlagCari);
		if(command.equals("carianUtama")){
			this.context.put("FlagCari", FlagCari);
			skrin_name = "app/admin/UV3/SenaraiUtama.jsp";

		}else if(command.equals("batalCarianUtama")){
			skrin_name = "app/admin/UV3/SenaraiUtama.jsp";
		}else if(command.equals("UploadDocPIP")){
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);

			this.context.put("commandPIP", "showDocPIP");
			this.context.put("after_saveDOCPIP", "Y");

			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String returnDIVDOCPIP = getParam("returnDIVDOCPIP");
			this.context.put("returnDIVDOCPIP",returnDIVDOCPIP);
			saveDocPIP(session,internalType,USER_ID,ID_PERMOHONAN);
			skrin_name = "app/admin/UV3/index.jsp";
		//open ct
		}else if(command.equals("showCT_HQ") || command.equals("showCT_Negeri") || command.equals("showCT_MOHON")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN");
			this.context.put("CT_OPENCLOSE_CARIAN",getParam("CT_OPENCLOSE_CARIAN"));
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);

			if(command.equals("showCT_HQ")){
				this.context.put("CT_ID_NEGERI", "16");
				filter_negeri = "16";

			}
			list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","","",internalType);
			this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","","",internalType);
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",filter_negeri,"",internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,getParam("CT_ID_SEKSYEN_"+internalType),getParam("CT_ID_NEGERI_"+internalType));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);

			if(command.equals("showCT_MOHON")){
				skrin_name = "app/admin/UV3/skrinCTMOHON.jsp";
			}else{
				listRole = listTableRujukanV3(session,"ROLE","","","HQ");
				this.context.put("listRole",listRole);
				skrin_name = "app/admin/UV3/skrinCTInternal.jsp";
			}

		//close ct ,//open ct
		}else if(command.equals("showCT_INT")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN");
			this.context.put("CT_OPENCLOSE_CARIAN",getParam("CT_OPENCLOSE_CARIAN"));
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);

			list_TBLINTRUJJENISPEJABAT = listPejabatIntegrasi(session,"","");
			this.context.put("list_TBLINTRUJJENISPEJABAT",list_TBLINTRUJJENISPEJABAT);

			listRole = listTableRujukanV3(session,"ROLE","","",internalType);
			this.context.put("listRole",listRole);

			String CT_ID_PEJABAT = getParam("CT_ID_PEJABAT_INT");

			if(CT_ID_PEJABAT.equals("16111")){	//JPPH
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);

				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);

				listPejabat = listPejabat(session, (String) viewPengguna.get("ID_NEGERI"), "", "3","2");//JPPH + PPK
				this.context.put("listPejabat",listPejabat);

			}

			skrin_name = "app/admin/UV3/skrinCTINT.jsp";

		//close ct
		}else if(command.equals("showCT_Online")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN");
			this.context.put("CT_OPENCLOSE_CARIAN",getParam("CT_OPENCLOSE_CARIAN"));
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);

			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
			this.context.put("list_TBLRUJNEGERI", list_TBLRUJNEGERI);
			listRole = listTableRujukanV3(session,"ROLE","","",internalType);
			this.context.put("listRole",listRole);

			skrin_name = "app/admin/UV3/skrinCTOnline.jsp";

		//close ct
		}else if(command.equals("showCT_KJP")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN");
			this.context.put("CT_OPENCLOSE_CARIAN",getParam("CT_OPENCLOSE_CARIAN"));
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);

			list_TBLRUJKEMENTERIAN = listTableRujukanV3(session,"TBLRUJKEMENTERIAN","","",internalType);
			this.context.put("list_TBLRUJKEMENTERIAN",list_TBLRUJKEMENTERIAN);
			list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSI",getParam("CT_ID_KEMENTERIAN_"+internalType),"",internalType);
			this.context.put("list_TBLRUJAGENSI",list_TBLRUJAGENSI);

			skrin_name = "app/admin/UV3/skrinCTKJP.jsp";

		//close ct
		}else if(command.equals("showSenaraiPenggunaInternalNegeri")){
//			myLogger.info("print negeri ");
			this.context.put("mode", "Negeri");

			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_Negeri");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_Negeri");
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y")){
				String USER_ID = getParam("USER_ID");
				String USER_NAME = getParam("USER_NAME");
				deletePengguna(session,USER_ID,"internal",USER_NAME);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Pengguna '"+USER_NAME+"' Berjaya Dihapus");

			}

			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			//open ct
//			myLogger.info(" ************ "+getParam("CT_FLAGTEPERINCI_CARIAN_Negeri"));
			if(!carianTerperinci.equals("") || getParam("CT_FLAGTEPERINCI_CARIAN_Negeri").equals("Y")){
				listPenggunaInternalNegeri = listPengunaInternal(session,"Negeri",carianTerperinci);
				setupPageList(session, action, listPenggunaInternalNegeri, "listPenggunaInternalNegeri",command,"div_PenggunaInternalNegeri");
				//open ct
				this.context.put("PrintlistPenggunaInternalNegeri",listPenggunaInternalNegeri);
				//close ct
			}
			if(getParam("FlagCetak").equals("Y")){
				skrin_name = "app/admin/UV3/SenaraiPenggunaInternalNegeri_Print.jsp";
			}else if (getParam("FlagCetakSemakanNegeri").equals("Y")){
				listPenggunaInternalPrint = listPengunaInternal(session,"Negeri",carianTerperinci);
				setupPageList(session, action, listPenggunaInternalPrint, "listPenggunaInternalPrint",command,"div_PenggunaInternalNegeri");
				//open ct
				this.context.put("PrintlistPengguna",listPenggunaInternalPrint);

				listStatsJawatan = getStatsJawatan(session,"Negeri",carianTerperinci);
				this.context.put("listStatsJawatan",listStatsJawatan);

				skrin_name = "app/admin/UV3/SenaraiPrintPenggunaAllNegeri.jsp";

			}else{
				skrin_name = "app/admin/UV3/SenaraiPenggunaInternalNegeri.jsp";
			}

		}else if(command.equals("showSenaraiPenggunaInternalHQ")){
			this.context.put("mode", "HQ");

			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_HQ");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_HQ");
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y")){
				String USER_ID = getParam("USER_ID");
				String USER_NAME = getParam("USER_NAME");
				deletePengguna(session,USER_ID,"internal",USER_NAME);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Pengguna '"+USER_NAME+"' Berjaya Dihapus");

			}

			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			//open ct
			if(!carianTerperinci.equals("") || getParam("CT_FLAGTEPERINCI_CARIAN_HQ").equals("Y")){
				listPenggunaInternalHQ = listPengunaInternal(session,"HQ",carianTerperinci);
				setupPageList(session, action, listPenggunaInternalHQ, "listPenggunaInternalHQ",command,"div_PenggunaInternalHQ");
				//open ct
				this.context.put("PrintlistPenggunaInternalHQ",listPenggunaInternalHQ);
				//close ct
			}

			if(getParam("FlagCetak").equals("Y")){
				skrin_name = "app/admin/UV3/SenaraiPenggunaInternalHQ_Print.jsp";
			}else if (getParam("FlagCetakSemakanHQ").equals("Y")){
				listPenggunaInternalPrint = listPengunaInternal(session,"HQ",carianTerperinci);
				setupPageList(session, action, listPenggunaInternalPrint, "listPenggunaInternalPrint",command,"div_PenggunaInternalHQ");
				//open ct
				this.context.put("PrintlistPengguna",listPenggunaInternalPrint);

				listStatsJawatan = getStatsJawatan(session,"HQ",carianTerperinci);
				this.context.put("listStatsJawatan",listStatsJawatan);

				skrin_name = "app/admin/UV3/SenaraiPrintPenggunaAll.jsp";

			}else{
				skrin_name = "app/admin/UV3/SenaraiPenggunaInternalHQ.jsp";
			}

		}else if(command.equals("edit_PenggunaInternal")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			viewPengguna = viewDataPenggunaInternal(session,USER_ID,"",ID_PERMOHONAN);
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
			if(internalType.equals("HQ")){
				filterNegeriHQ = "16";
			}
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI",filterNegeriHQ,USER_ID,internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,(String) viewPengguna.get("ID_SEKSYEN"),(String) viewPengguna.get("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			if(!viewPengguna.get("ID_PEJABATJKPTG").equals("")){
				viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}
			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);
			this.context.put("SuccessMesejDeleteUser","");

			skrin_name = "app/admin/UV3/editPenggunaInternal.jsp";

		}else if(command.equals("edit_PenggunaMOHON")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			viewPengguna = viewDataPenggunaMOHON(session,ID_PERMOHONAN);
			this.context.put("viewPengguna", viewPengguna);

			list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","","",internalType);
			this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","","",internalType);
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,(String) viewPengguna.get("ID_SEKSYEN"),(String) viewPengguna.get("ID_NEGERI"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			list_TBLRUJGRED = listTableRujukanV3(session,"TBLRUJGRED",(String) viewPengguna.get("ID_KLASIFIKASI"),"",internalType);
			this.context.put("list_TBLRUJGRED",list_TBLRUJGRED);

			if(!viewPengguna.get("ID_PEJABATJKPTG").equals("")){
				viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);

			}
			skrin_name = "app/admin/UV3/editPenggunaMOHON.jsp";

		}else if(command.equals("showAlamatPejabat")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PEJABAT = getParam("ID_PEJABAT");
			this.context.put("ID_PEJABAT", ID_PEJABAT);
			viewPejabat = viewPejabat(session,ID_PEJABAT);
			this.context.put("viewPejabat",viewPejabat);
			skrin_name = "app/admin/UV3/showAlamatPejabat.jsp";

		}else if(command.equals("selectPejabatByDaerahNegeri")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_NEGERI = getParam("ID_NEGERI");
			String ID_DAERAH = getParam("ID_DAERAH");
			String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
//			myLogger.info("ID_JENISPEJABAT selected for pejabat : "+ID_JENISPEJABAT);

			/*String seperator = "/";
			String ID_JENISPEJABAT = ID_JENISPEJABAT2.substring(0, ID_JENISPEJABAT2.indexOf(seperator)).trim();
			this.context.put("ID_JENISPEJABAT", ID_JENISPEJABAT);
			myLogger.info("ID_JENISPEJABAT  -- "+ID_JENISPEJABAT);*/

			listPejabat = listPejabatIntegrasi(session, ID_NEGERI, ID_JENISPEJABAT);	//JPPH + PPK
			this.context.put("listPejabat",listPejabat);

			/*listPejabat = listPejabat(session, ID_NEGERI, "", "3","2");	//JPPH + PPK
			this.context.put("listPejabat",listPejabat);*/

			skrin_name = "app/admin/UV3/showListPejabatINT.jsp";

		}else if(command.equals("selectPejabatByDaerahNegeri_CT")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_NEGERI = getParam("ID_NEGERI");
//			myLogger.info("ID_NEGERI selected for pejabat : "+ID_NEGERI);
			String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
//			myLogger.info("ID_JENISPEJABAT selected for pejabat : "+ID_JENISPEJABAT);

			/*String seperator = "/";
			String ID_JENISPEJABAT = ID_JENISPEJABAT2.substring(0, ID_JENISPEJABAT2.indexOf(seperator)).trim();
			this.context.put("ID_JENISPEJABAT", ID_JENISPEJABAT);
			myLogger.info("ID_JENISPEJABAT  -- "+ID_JENISPEJABAT);*/

			listPejabat = listPejabatIntegrasi(session, ID_NEGERI, ID_JENISPEJABAT);	//JPPH + PPK
			this.context.put("listPejabat",listPejabat);

			skrin_name = "app/admin/UV3/showListPejabatINT_CT.jsp";

		}else if(command.equals("selectDaerahByNegeri")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_NEGERI = getParam("ID_NEGERI");
			this.context.put("ID_NEGERI", ID_NEGERI);
			list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",ID_NEGERI,"",internalType);
			this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
			skrin_name = "app/admin/UV3/showListDaerah.jsp";

		}else if(command.equals("selectDaerahByNegeri_CT")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_NEGERI = getParam("ID_NEGERI");
			this.context.put("CT_ID_NEGERI", ID_NEGERI);
			list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",ID_NEGERI,"",internalType);
			this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
			skrin_name = "app/admin/UV3/showListDaerah_CT.jsp";

		}else if(command.equals("selectJenisPejabatINT")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengguna = viewDataPenggunaINT(session,USER_ID,"");
			//this.context.put("viewPengguna", viewPengguna);
			String ID_JENISPEJABAT2 = getParam("ID_JENISPEJABAT");
//			myLogger.info("ID_JENISPEJABAT2 onchange -- "+ID_JENISPEJABAT2);

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

			listPejabat = listPejabatIntegrasi(session,(String) viewPengguna.get("ID_NEGERI"),ID_JENISPEJABAT2);//JPPH + PPK
			this.context.put("listPejabat",listPejabat);

			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);

			list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
			this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				/*
				listPejabat = listPejabat(session, (String) viewPengguna.get("ID_NEGERI"), (String) viewPengguna.get("ID_DAERAH"), "3");
				this.context.put("listPejabat",listPejabat);
				*/
			skrin_name = "app/admin/UV3/edit_INT_JPPH.jsp";
			//}
			//else
			//{
			//	skrin_name = "app/admin/UV3/edit_INT_blank.jsp";
			//}

		}else if(command.equals("selectJenisPejabatINT_CT")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			String ID_NEGERI = getParam("ID_NEGERI");
//			myLogger.info("ID_NEGERI onchange -- "+ID_NEGERI);
			String ID_JENISPEJABAT2 = getParam("ID_JENISPEJABAT");
//			myLogger.info("ID_JENISPEJABAT2 onchange -- "+ID_JENISPEJABAT2);

			/*String seperator = "/";
			String ID_JENISPEJABAT = ID_JENISPEJABAT2.substring(0, ID_JENISPEJABAT2.indexOf(seperator)).trim();
			this.context.put("ID_JENISPEJABAT", ID_JENISPEJABAT);
			myLogger.info("ID_JENISPEJABAT  -- "+ID_JENISPEJABAT);*/

			/*String ID_NEGERIPEJABAT = ID_JENISPEJABAT2.substring(ID_JENISPEJABAT2.indexOf(seperator)+1).trim();
			this.context.put("ID_NEGERIPEJABAT", ID_NEGERIPEJABAT);
			myLogger.info("ID_NEGERIPEJABAT  -- "+ID_NEGERIPEJABAT);*/

			//if(ID_JENISPEJABAT.equals("16111"))//JPPH
			//if(ID_JENISPEJABAT.equals("3"))//JPPh drpd jenis pejabat
			//{
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);

				listPejabat = listPejabatIntegrasi(session,ID_NEGERI,ID_JENISPEJABAT2);//JPPH + PPK
				this.context.put("listPejabat",listPejabat);

				skrin_name = "app/admin/UV3/edit_INT_JPPH_CT.jsp";
			//}
			//else
			///{
				//skrin_name = "app/admin/UV3/edit_INT_blank.jsp";
			//}

		}else if(command.equals("edit_PenggunaINT")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengguna = viewDataPenggunaINT(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);

			String ID_JENISPEJABAT = (String) viewPengguna.get("ID_JENISPEJABAT");

			/*list_TBLINTRUJJENISPEJABAT = listTableRujukanV3(session,"TBLINTRUJJENISPEJABAT","","",internalType);
			this.context.put("list_TBLINTRUJJENISPEJABAT",list_TBLINTRUJJENISPEJABAT);*/

			list_TBLINTRUJJENISPEJABAT = listPejabatIntegrasi(session,"",ID_JENISPEJABAT);
			this.context.put("list_TBLINTRUJJENISPEJABAT",list_TBLINTRUJJENISPEJABAT);

			//if(ID_JENISPEJABAT.equals("16111"))//JPPH //sini
			//	if(ID_JENISPEJABAT.equals("3"))
			//{
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*",USER_ID,internalType);
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);

				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);

				listPejabat = listPejabat(session, "", "", (String) viewPengguna.get("ID_JENISPEJABAT"),(String) viewPengguna.get("ID_SEKSYEN"));//JPPH + PPK
				this.context.put("listPejabat",listPejabat);

				viewPejabat = viewPejabat(session,(String) viewPengguna.get("ID_PEJABAT"));
				this.context.put("viewPejabat",viewPejabat);


			//}

			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);
			this.context.put("SuccessMesejDeleteUser","");

			skrin_name = "app/admin/UV3/editPenggunaINT.jsp";

		}else if(command.equals("showDocPIP")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			viewPenggunaMOHON = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
			this.context.put("viewPenggunaMOHON", viewPenggunaMOHON);
			skrin_name = "app/admin/UV3/displayDocPIP.jsp";

		}else if(command.equals("edit_PenggunaKJP")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

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
			if(!USER_ID.equals("")){
				current_role_kjp = getCurrentRoleKJP(session, USER_ID);
			}
			this.context.put("current_role_kjp",current_role_kjp);

			if(!viewPengguna.get("ID_KEMENTERIAN").equals("")){
				viewAlamatKJP = viewAlamatKJP(session, (String)viewPengguna.get("ID_KEMENTERIAN"));
				this.context.put("viewAlamatKJP",viewAlamatKJP);

			}else{
				this.context.put("viewAlamatKJP","");
			}

			this.context.put("SuccessMesejDeleteUser","");
			skrin_name = "app/admin/UV3/editPenggunaKJP.jsp";

		}else if(command.equals("edit_PenggunaOnline")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			viewPengguna = viewDataPenggunaOnline(session, USER_ID, "");
			this.context.put("viewPengguna", viewPengguna);

			this.context.put("list_TBLRUJPOSKOD", "");

			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*","",internalType);
			this.context.put("list_TBLRUJNEGERI", list_TBLRUJNEGERI);

			listRole = listTableRujukanV3(session,"ROLE","",USER_ID,internalType);
			this.context.put("listRole",listRole);

			if(!viewPengguna.get("ID_NEGERI").equals("")){
				list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDAR",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJBANDAR", list_TBLRUJBANDAR);

			}else{
				this.context.put("list_TBLRUJBANDAR", "");
			}

			myLogger.info("ros : "+viewPengguna.get("ID_PEJABAT"));

			list_TBLRUJPEJABAT = listTableRujukanV3(session,"TBLRUJPEJABAT","*","",internalType);
			this.context.put("list_TBLRUJPEJABAT", list_TBLRUJPEJABAT);

			this.context.put("SuccessMesejDeleteUser","");
			skrin_name = "app/admin/UV3/editPenggunaOnline.jsp";

		}else if(command.equals("showFieldPengenalan")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_KATEGORI = getParam("ID_KATEGORI");
			if(ID_KATEGORI.equals("Individu")){
				String NO_PENGENALAN1 = getParam("NO_PENGENALAN1");
				this.context.put("NO_PENGENALAN1", NO_PENGENALAN1);
				String NO_PENGENALAN2 = getParam("NO_PENGENALAN2");
				this.context.put("NO_PENGENALAN2", NO_PENGENALAN2);
				String NO_PENGENALAN3 = getParam("NO_PENGENALAN3");
				this.context.put("NO_PENGENALAN3", NO_PENGENALAN3);
				this.context.put("NO_PENGENALAN", "");
				skrin_name = "app/admin/UV3/showFieldMyID.jsp";

			}else if(ID_KATEGORI.equals("Syarikat")){
				this.context.put("NO_PENGENALAN1", "");
				this.context.put("NO_PENGENALAN2", "");
				this.context.put("NO_PENGENALAN3", "");
				String NO_PENGENALAN = getParam("NO_PENGENALAN");
				this.context.put("NO_PENGENALAN", NO_PENGENALAN);
				skrin_name = "app/admin/UV3/showFieldMyCOID.jsp";

			}else{
				String NO_PENGENALAN1 = getParam("NO_PENGENALAN1");
				this.context.put("NO_PENGENALAN1", NO_PENGENALAN1);
				String NO_PENGENALAN2 = getParam("NO_PENGENALAN2");
				this.context.put("NO_PENGENALAN2", NO_PENGENALAN2);
				String NO_PENGENALAN3 = getParam("NO_PENGENALAN3");
				this.context.put("NO_PENGENALAN3", NO_PENGENALAN3);
				this.context.put("NO_PENGENALAN", "");
				skrin_name = "app/admin/UV3/showFieldMyID.jsp";

			}

		}else if(command.equals("showAlamatKJP")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
			if(!ID_KEMENTERIAN.equals("")){
				viewAlamatKJP = viewAlamatKJP(session, ID_KEMENTERIAN);
				this.context.put("viewAlamatKJP",viewAlamatKJP);

			}else{
				this.context.put("viewAlamatKJP","");
			}
			skrin_name = "app/admin/UV3/showAlamatKJP.jsp";

		}else if(command.equals("showListAgensi")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
			this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
			list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSI",ID_KEMENTERIAN,"",internalType);
			this.context.put("list_TBLRUJAGENSI", list_TBLRUJAGENSI);
			skrin_name = "app/admin/UV3/showListAgensi.jsp";

		}else if(command.equals("showListAgensi_CT")){
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
			this.context.put("CT_ID_KEMENTERIAN", ID_KEMENTERIAN);
			list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSI",ID_KEMENTERIAN,"",internalType);
			this.context.put("list_TBLRUJAGENSI", list_TBLRUJAGENSI);
			skrin_name = "app/admin/UV3/showListAgensi_CT.jsp";

		}else if(command.equals("showListBandar")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_NEGERI = getParam("ID_NEGERI");
			list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDAR",ID_NEGERI,"",internalType);
			this.context.put("list_TBLRUJBANDAR", list_TBLRUJBANDAR);
			skrin_name = "app/admin/UV3/showListBandar.jsp";

		}else if(command.equals("showListPejabat")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_SEKSYEN = getParam("ID_SEKSYEN");
			String ID_NEGERI = getParam("ID_NEGERI");
			myLogger.info(" ID_SEKSYEN : "+ID_SEKSYEN+",  ID_NEGERI : "+ID_NEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,ID_SEKSYEN,ID_NEGERI);
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			skrin_name = "app/admin/UV3/showListPejabat.jsp";

		}else if(command.equals("showListPejabat_CT")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_SEKSYEN = getParam("ID_SEKSYEN");
			String ID_NEGERI = getParam("ID_NEGERI");
			myLogger.info(" ID_SEKSYEN : "+ID_SEKSYEN+",  ID_NEGERI : "+ID_NEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,ID_SEKSYEN,ID_NEGERI);
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			skrin_name = "app/admin/UV3/showListPejabat_CT.jsp";

		}else if(command.equals("checkUSER_LOGIN")){
			this.context.put("FLAG_PIP", getParam("FLAG_PIP"));
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			this.context.put("GET_USER_ID_EXIST", "");
			boolean checkUSERLOGIN = checkUSERLOGIN(session, USER_ID, USER_LOGIN,internalType);

			if(USER_ID.equals("")){//waktu nak insert

				if(checkUSERLOGIN==false){
					this.context.put("checkUSERLOGIN", checkUSERLOGIN);
					//<font color="red" class="blink" >User Login Telah Wujud!</font>
					String msj = "";
					if(internalType.equals("HQ")){
						msj = "<font color='red' class='blink' >User Login Pengguna Telah Wujud!</font>";
					}else if(internalType.equals("Negeri")){
						msj = "<font color='red' class='blink' >User Login Pengguna Telah Wujud!</font>";
					}else if(internalType.equals("Online")){
						msj = "<font color='red' class='blink' >User Login Pengguna Jkptg Online Telah Wujud!</font>";
					}else if(internalType.equals("KJP")){
						msj = "<font color='red' class='blink' >User Login Pengguna KJP Online Telah Wujud!</font>";
					}else if(internalType.equals("INT")){
						msj = "<font color='red' class='blink' >User Login Pengguna Integrasi Telah Wujud!</font>";
					}
					this.context.put("display_info_check_id", msj);
					this.context.put("GET_USER_ID_EXIST", "");

				}else{
					this.context.put("GET_USER_ID_EXIST", "");
					this.context.put("checkUSERLOGIN", "");
					this.context.put("viewPenggunaExist", "");
					String msj = "";
					viewPenggunaExist = null;
					if(internalType.equals("HQ") || internalType.equals("Negeri")){
						//check user online dah wujud?
						viewPenggunaExist = viewDataPenggunaOnline(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)

						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("NO_PENGENALAN")+"'</b> (Pengguna Online)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaKJP(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("NAMA_KEMENTERIAN")+"'</b> (Pengguna KJP)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaINT(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("USER_NAME")+" - "+viewPenggunaExist.get("JENIS_PEJABAT")+"'</b> (Pengguna Integrasi)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);

						}

					}else if(internalType.equals("Online")){
						//check user internal dah wujud?
						viewPenggunaExist = viewDataPenggunaInternal(session,"",USER_LOGIN,"");
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//myLogger.info("++++++++++ viewPenggunaExist"+viewPenggunaExist);
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("USER_NAME")+" - "+viewPenggunaExist.get("NAMA_BAHAGIAN")+", "+viewPenggunaExist.get("NAMA_PEJABAT")+", "+viewPenggunaExist.get("NEGERI_PEJ")+"'</b> (Pengguna Dalaman)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaKJP(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("NAMA_KEMENTERIAN")+"'</b> (Pengguna KJP)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaINT(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("USER_NAME")+" - "+viewPenggunaExist.get("JENIS_PEJABAT")+"'</b> (Pengguna Integrasi)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);

						}

					}else if(internalType.equals("KJP")) {
						//check user internal dah wujud?
						viewPenggunaExist = viewDataPenggunaInternal(session,"",USER_LOGIN,"");
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("USER_NAME")+" - "+viewPenggunaExist.get("NAMA_BAHAGIAN")+", "+viewPenggunaExist.get("NAMA_PEJABAT")+", "+viewPenggunaExist.get("NEGERI_PEJ")+"'</b> (Pengguna Dalaman)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaOnline(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("NO_PENGENALAN")+"'</b> (Pengguna Online)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaINT(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("JENIS_PEJABAT")+"'</b> (Pengguna Integrasi)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);

						}

					}else if(internalType.equals("INT")) {
						//check user internal dah wujud?
						viewPenggunaExist = viewDataPenggunaInternal(session,"",USER_LOGIN,"");
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("USER_NAME")+" - "+viewPenggunaExist.get("NAMA_BAHAGIAN")+", "+viewPenggunaExist.get("NAMA_PEJABAT")+", "+viewPenggunaExist.get("NEGERI_PEJ")+"'</b> (Pengguna Dalaman)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaOnline(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("NO_PENGENALAN")+"'</b> (Pengguna Online)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);
							msj += "<br>";

						}

						viewPenggunaExist = viewDataPenggunaKJP(session, "", USER_LOGIN);
						//if(!viewPenggunaExist.get("USER_ID").toString().equals(""))
						//if(viewPenggunaExist!=null)
						if(viewPenggunaExist.get("USER_ID")!=null){
							msj += "<font color='blue' class='blink' >User Login Telah Wujud Sebagai <b>'"+viewPenggunaExist.get("NAMA_PENUH")+" - "+viewPenggunaExist.get("NAMA_KEMENTERIAN")+"'</b> (Pengguna KJP)</font>";
							this.context.put("GET_USER_ID_EXIST", viewPenggunaExist.get("USER_ID"));
							this.context.put("viewPenggunaExist", viewPenggunaExist);

						}

					}
					this.context.put("display_info_check_id", msj);

				}

			}else{ // waktu nak update

				if(checkUSERLOGIN==false){
					this.context.put("checkUSERLOGIN", checkUSERLOGIN);
					//<font color="red" class="blink" >User Login Telah Wujud!</font>
					String msj = "";
					if(internalType.equals("HQ")){
						msj += "<font color='red' class='blink' >User Login Pengguna Telah Wujud!</font>";
						msj += "<br>";

					}else if(internalType.equals("Negeri")){
						msj += "<font color='red' class='blink' >User Login Pengguna Telah Wujud!</font>";
						msj += "<br>";

					}else if(internalType.equals("Online")){
						msj += "<font color='red' class='blink' >User Login Pengguna Jkptg Online Telah Wujud!</font>";
						msj += "<br>";

					}else if(internalType.equals("KJP")){
						msj += "<font color='red' class='blink' >User Login Pengguna KJP Online Telah Wujud!</font>";
						msj += "<br>";

					}else if(internalType.equals("INT")){
						msj += "<font color='red' class='blink' >User Login Pengguna Integrasi Telah Wujud!</font>";
						msj += "<br>";

					}

					this.context.put("display_info_check_id", msj);
					this.context.put("GET_USER_ID_EXIST", "");

				}else{
					this.context.put("checkUSERLOGIN", checkUSERLOGIN);
					this.context.put("display_info_check_id", "");
					this.context.put("GET_USER_ID_EXIST", "");

				}

			}
			skrin_name = "app/admin/UV3/checkUSER_LOGIN.jsp";

		}else if(command.equals("showListRole")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String GET_USER_ID_EXIST = getParam("GET_USER_ID_EXIST");
			this.context.put("GET_USER_ID_EXIST", GET_USER_ID_EXIST);

			String current_role_utama = "";
//			myLogger.info(" --------- USER_ID : "+USER_ID);
			if(!GET_USER_ID_EXIST.equals("")){
				current_role_utama = getRoleUtamaUsers(session,GET_USER_ID_EXIST);
//				myLogger.info(" --------- atas current_role_utama : "+current_role_utama);
			}
			this.context.put("current_role_utama",current_role_utama);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			listRole = listTableRujukanV3(session,"ROLE","",GET_USER_ID_EXIST,internalType);
			this.context.put("listRole",listRole);
			skrin_name = "app/admin/UV3/listRole.jsp";

		}else if(command.equals("showDisplayPejabat")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			if(!ID_PEJABATJKPTG.equals("")){
				viewPejabatJKPTG = viewPejabatJKPTG(session,ID_PEJABATJKPTG);
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);

			}else{
				this.context.put("viewPejabatJKPTG","");
			}
			skrin_name = "app/admin/UV3/showDisplayPejabat.jsp";

		}else if(command.equals("edit_AddRole")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);
			listRoleByUserLogin = listRoleByUserLogin(session, USER_LOGIN,internalType,USER_ID);
			this.context.put("listRoleByUserLogin", listRoleByUserLogin);
			this.context.put("SuccessMesejRole", "");
			skrin_name = "app/admin/UV3/edit_AddRole.jsp";

		}else if(command.equals("save_AddRole")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);

			String[] TEMP_GROUP_CHECKLIST = request.getParameterValues("TEMP_GROUP_CHECKLIST_"+internalType+USER_ID);

			int total_role_update = 0;
			total_role_update += simpanAdditionalRoles(session,USER_ID, internalType, USER_LOGIN, TEMP_GROUP_CHECKLIST);

			if(total_role_update>=0){
				this.context.put("SuccessMesejRole", total_role_update+" Peranan/Role Telah Didaftarkan Kepada '"+USER_LOGIN+"'");
			}else{
				this.context.put("SuccessMesejRole", "");
			}

			listRoleByUserLogin = listRoleByUserLogin(session, USER_LOGIN,internalType,USER_ID);
			this.context.put("listRoleByUserLogin", listRoleByUserLogin);
			skrin_name = "app/admin/UV3/edit_AddRole.jsp";

		}else if(command.equals("showDisplayAddRole")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);
			//listAdditionalRoleByUserLogin = listAdditionalRoleByUserLogin(session, USER_LOGIN);
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);
			this.context.put("SuccessMesejRole", "");
			skrin_name = "app/admin/UV3/showDisplayAddRole.jsp";

		}else if(command.equals("showDisplayDaerahJagaan")){
			String ID_PEJABATJKPTG = getParam("ID_PEJABATJKPTG");
			String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
			if(!ID_PEJABATJKPTG.equals("")){
				listDaerahJagaanByIdPejabat = listDaerahJagaanByIdPejabat(session, ID_PEJABATJKPTG,ID_JENISPEJABAT);
				this.context.put("listDaerahJagaanByIdPejabat", listDaerahJagaanByIdPejabat);

			}else{
				this.context.put("listDaerahJagaanByIdPejabat", "");
			}
			skrin_name = "app/admin/UV3/showDisplayDaerahJagaan.jsp";
		}

		/*
		else if(command.equals("showSenaraiPenggunaInternalNegeri"))
		{
			//open ct

			myLogger.info("*****TEST_Negeri******** : "+getParam("TEST_Negeri"));
			//String CT_OPENCLOSE_CARIAN_Negeri = getParam("CT_OPENCLOSE_CARIAN_Negeri");
			String CT_FLAGTEPERINCI_CARIAN_Negeri = getParam("CT_FLAGTEPERINCI_CARIAN_Negeri");
			myLogger.info("************************* : "+getParam("CT_OPENCLOSE_CARIAN_Negeri"));
			this.context.put("CT_OPENCLOSE_CARIAN_Negeri",getParam("CT_OPENCLOSE_CARIAN_Negeri"));
			this.context.put("CT_FLAGTEPERINCI_CARIAN_Negeri", CT_FLAGTEPERINCI_CARIAN_Negeri);
			//this.context.put("CT_ID_NEGERI_Negeri", "16");

			list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","","","Negeri");
			this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
			list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","","","Negeri");
			this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
			list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","16","","Negeri");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
			listPejabatJKPTG = listPejabatJKPTG(session,getParam("CT_ID_SEKSYEN_Negeri"),getParam("CT_ID_NEGERI_Negeri"));
			this.context.put("listPejabatJKPTG",listPejabatJKPTG);
			listRole = listTableRujukanV3(session,"ROLE","","","Negeri");
			this.context.put("listRole",listRole);

			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y"))
			{
				String USER_ID = getParam("USER_ID");
				String USER_NAME = getParam("USER_NAME");
				deletePengguna(session,USER_ID,"internal",USER_NAME);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Pengguna '"+USER_NAME+"' Berjaya Dihapus");
			}
			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			if(!carianTerperinci.equals(""))
			{
				listPenggunaInternalNegeri = listPengunaInternal(session,"Negeri",carianTerperinci);
				setupPageList(session, action, listPenggunaInternalNegeri, "listPenggunaInternalNegeri",command,"div_PenggunaInternalNegeri");
				//open ct
				this.context.put("PrintlistPenggunaInternalNegeri",listPenggunaInternalNegeri);
				//close ct
			}
			skrin_name = "app/admin/UV3/SenaraiPenggunaInternalNegeri.jsp";
		}
		*/

		/*
		else if(command.equals("close_PenggunaOnline"))
		{
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			skrin_name = "app/admin/UV3/blank_viewPenggunaOnline.jsp";
		}*/
		else if(command.equals("close_PenggunaInternal")
			|| command.equals("close_PenggunaOnline")
			|| command.equals("close_PenggunaKJP")
			|| command.equals("close_PenggunaINT")
			|| command.equals("close_PenggunaMOHON")
			){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			skrin_name = "app/admin/UV3/blank_viewPenggunaInternal.jsp";

		}else if(command.equals("viewPenggunaInternal")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			viewPengguna = viewDataPenggunaInternal(session,USER_ID,"","");
			this.context.put("viewPengguna", viewPengguna);
			this.context.put("SuccessMesej", "");
			skrin_name = "app/admin/UV3/viewPenggunaInternal.jsp";

		}else if(command.equals("viewPLA")){
			String ID_ESADUAN = getParam("ID_ESADUAN");
			this.context.put("ID_ESADUAN", ID_ESADUAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengunaPLA = viewPengunaPLA(session,ID_ESADUAN);
			this.context.put("viewPengunaPLA", viewPengunaPLA);
			skrin_name = "app/admin/UV3/viewPLA.jsp";

		}else if(command.equals("close_viewPla")){
			String ID_ESADUAN = getParam("ID_ESADUAN");
			this.context.put("ID_ESADUAN", ID_ESADUAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			skrin_name = "app/admin/UV3/blank_viewPLA.jsp";

		}else if(command.equals("close_viewHistory")){
			skrin_name = "app/admin/UV3/blank_viewHISTORY.jsp";
		}else if(command.equals("carianUtamaPLA")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			Integer totalPLA = getPLACount(session, USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			this.context.put("adaPLA", "");
			if(totalPLA>0){
				this.context.put("totalPLA", totalPLA);
				this.context.put("adaPLA", "Y");

				String carianTerperinciPLA = getParam("carianTerperinciPLA_"+internalType+USER_ID);
				String TARIKH_MULA_PLA = getParam("TARIKH_MULA_PLA_"+internalType+USER_ID);
				String TARIKH_AKHIR_PLA = getParam("TARIKH_AKHIR_PLA_"+internalType+USER_ID);

				Date today_date = new Date();
				String today_date_str= new SimpleDateFormat("dd/MM/yyyy").format(today_date);
				/*
				if(TARIKH_MULA_PLA.equals("") && TARIKH_AKHIR_PLA.equals(""))
				{
				TARIKH_AKHIR_PLA = today_date_str;
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.YEAR, -1);
				Date date_before = cal.getTime();
				SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
				TARIKH_MULA_PLA = ft.format(date_before);
				}
				*/

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

			}
			skrin_name = "app/admin/UV3/SenaraiPenggunaPLA.jsp";

		}else if(command.equals("carianUtamaHISTORY")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			Integer totalHISTORY = getHISTORYCount(session, USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			this.context.put("adaHISTORY", "");
			if(totalHISTORY>0){
				this.context.put("totalHISTORY", totalHISTORY);
				this.context.put("adaHISTORY", "Y");

				String carianTerperinciHISTORY = getParam("carianTerperinciHISTORY_"+internalType+USER_ID);
				String TARIKH_MULA_HISTORY = getParam("TARIKH_MULA_HISTORY_"+internalType+USER_ID);
				String TARIKH_AKHIR_HISTORY = getParam("TARIKH_AKHIR_HISTORY_"+internalType+USER_ID);

				Date today_date = new Date();
				String today_date_str= new SimpleDateFormat("dd/MM/yyyy").format(today_date);

				String action = getParam("action");
				listPengunaHISTORY = listPengunaHISTORY(session,USER_ID,"","UTAMA",
						carianTerperinciHISTORY,TARIKH_MULA_HISTORY, TARIKH_AKHIR_HISTORY);
				setupPageList(session, action, listPengunaHISTORY, "listPengunaHISTORY",command,"div_ListHISTORY"+internalType+USER_ID);

				listPengunaHISTORYforPrint = listPengunaHISTORY(session,USER_ID,"","LAPORAN",
						carianTerperinciHISTORY,TARIKH_MULA_HISTORY, TARIKH_AKHIR_HISTORY);
				this.context.put("listPengunaHISTORYforPrint",listPengunaHISTORYforPrint);
				this.context.put("internalType",internalType);
				this.context.put("USER_ID",USER_ID);
				this.context.put("carianTerperinciHISTORY",carianTerperinciHISTORY);
				this.context.put("TARIKH_MULA_HISTORY",TARIKH_MULA_HISTORY);
				this.context.put("TARIKH_AKHIR_HISTORY",TARIKH_AKHIR_HISTORY);
				this.context.put("TARIKH_CURRENT",today_date_str);

			}
			skrin_name = "app/admin/UV3/SenaraiPenggunaHISTORY.jsp";

		}else if(command.equals("viewHISTORY_SUB")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			this.context.put("AKTIVITI", getParam("AKTIVITI"));
			this.context.put("TARIKH_MASUK", getParam("TARIKH_MASUK"));
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_SEJARAHPENGGUNAUTAMA = getParam("ID_SEJARAHPENGGUNAUTAMA");
//			myLogger.info("ID_SEJARAHPENGGUNAUTAMA :::::: "+ID_SEJARAHPENGGUNAUTAMA);
			this.context.put("ID_SEJARAHPENGGUNAUTAMA", ID_SEJARAHPENGGUNAUTAMA);
			listPengunaHISTORY_SUB = listPengunaHISTORY(session,USER_ID,ID_SEJARAHPENGGUNAUTAMA,"SUB",
					"","", "");
			this.context.put("listPengunaHISTORY_SUB", listPengunaHISTORY_SUB);
			skrin_name = "app/admin/UV3/SenaraiPenggunaHISTORY_SUB.jsp";

		}else if(command.equals("carianUtamaAT")){
			String USER_ID = getParam("USER_ID");
			Integer totalAT = getATCount(session, USER_ID);
			this.context.put("USER_ID", USER_ID);
			this.context.put("adaAT", "");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			if(totalAT>0){
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
			skrin_name = "app/admin/UV3/SenaraiPenggunaAT.jsp";

		}else if(command.equals("savePenggunaMOHON")){
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			savePenggunaMOHON(session,ID_PERMOHONAN, internalType, "");
			viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);

			this.context.put("viewPengguna", viewPengguna);
			if(!viewPengguna.get("ID_PEJABATJKPTG").equals("")){
				viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);

			}
			skrin_name = "app/admin/UV3/viewPenggunaMOHON.jsp";

		}else if(command.equals("viewPenggunaMOHON")){
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
			this.context.put("viewPengguna", viewPengguna);

			if(!viewPengguna.get("ID_PEJABATJKPTG").equals("")){
				viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}

			//System.out.println("test 123 :: "+InetAddress.getLocalHost());

			/*InetAddress addr = InetAddress.getLocalHost();
			String ipAddress = addr.getHostAddress();
			System.out.println("test 1234 :: "+ipAddress);

			String clintHost = request.getRemoteHost();
			System.out.println("test 12345 :: "+clintHost);

			String ipUser  = "";

			if (request != null) {
			 ipUser  = request.getHeader("x-real-ip");
			if(ipUser == null || "".equals(ipUser))
			{
				ipUser = request.getRemoteAddr(); //ini yg betul dah
				System.out.println("ipUser: null");

			}else {
				System.out.println("ipUser:"+ipUser);
			}

			}
			this.context.put("ipUser", ipUser);*/

			/*String ip = request.getHeader("x-forwarded-for");
			 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("Proxy-Client-IP");
			    }
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("WL-Proxy-Client-IP");
			    }
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getRemoteAddr();
			    }
			System.out.println("ipUser2:"+ip);*/
			skrin_name = "app/admin/UV3/viewPenggunaMOHON.jsp";

		}else if(command.equals("viewPenggunaKJP")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			viewPengguna = viewDataPenggunaKJP(session, USER_ID, "");
			list_TBLRUJKEMENTERIAN = listTableRujukanV3(session,"TBLRUJKEMENTERIAN","","",internalType);
			this.context.put("list_TBLRUJKEMENTERIAN",list_TBLRUJKEMENTERIAN);
			list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSI",(String) viewPengguna.get("ID_KEMENTERIAN"),"",internalType);
			this.context.put("list_TBLRUJAGENSI",list_TBLRUJAGENSI);
			String current_role_kjp = "";
			if(!USER_ID.equals("")){
				current_role_kjp = getCurrentRoleKJP(session, USER_ID);
			}
			this.context.put("current_role_kjp",current_role_kjp);
			this.context.put("viewPengguna", viewPengguna);
			this.context.put("SuccessMesej", "");
			skrin_name = "app/admin/UV3/viewPenggunaKJP.jsp";

		}else if(command.equals("viewPenggunaINT")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);
			viewPengguna = viewDataPenggunaINT(session, USER_ID, "");
			this.context.put("viewPengguna", viewPengguna);
			String ID_JENISPEJABAT = (String) viewPengguna.get("ID_JENISPEJABAT");
			//if(ID_JENISPEJABAT.equals("16111"))//JPPH
			if(ID_JENISPEJABAT.equals("3")){
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","*",USER_ID,internalType);
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);

				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH",(String) viewPengguna.get("ID_NEGERI"),"",internalType);
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);

				listPejabat = listPejabat(session, (String) viewPengguna.get("ID_NEGERI"), "", "3","2");//JPPH + PPK
				this.context.put("listPejabat",listPejabat);

				viewPejabat = viewPejabat(session,(String) viewPengguna.get("ID_PEJABAT"));
				this.context.put("viewPejabat",viewPejabat);

			}
			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			this.context.put("SuccessMesej", "");
			skrin_name = "app/admin/UV3/viewPenggunaINT.jsp";

		}else if(command.equals("viewPenggunaOnline")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);
			viewPengguna = viewDataPenggunaOnline(session, USER_ID, "");
			this.context.put("viewPengguna", viewPengguna);

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			this.context.put("SuccessMesej", "");
			skrin_name = "app/admin/UV3/viewPenggunaOnline.jsp";

		}else if(command.equals("savePenggunaInternal")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);

			//open history
			viewUserBaru = null;
			viewUserAsal = null;
			String aktiviti = "UPDATE";
			if(USER_ID.equals("") && USER_ID!=null){
				aktiviti = "INSERT";
			}
			viewUserAsal = viewDataPenggunaInternal(session,USER_ID,"",ID_PERMOHONAN);
			//close history

			boolean insert_update_user = false;
			boolean update_PIP = false;
			if(!ID_PERMOHONAN.equals("") && USER_ID.equals("")){
				String STATUS_PID = getParam("STATUS_PID_"+internalType+USER_ID);
				if(STATUS_PID.equals("2")){	//no user
					insert_update_user = false;
					update_PIP = true;

				}else if(STATUS_PID.equals("3")){	//add user
					insert_update_user = true;
					update_PIP = true;

				}

			}else{	//add user
				insert_update_user = true;
				update_PIP = false;

			}

			if(insert_update_user==true){
				USER_ID = simpanPenggunaInternal(session,USER_ID, internalType);
			}

			if(update_PIP==true){
				savePenggunaMOHON_internal(session,ID_PERMOHONAN, getParam("STATUS_PID_"+internalType),getParam("CATATAN_PIP_"+internalType), USER_ID);
			}

			viewPengguna = viewDataPenggunaInternal(session,USER_ID,"",ID_PERMOHONAN);
			this.context.put("viewPengguna", viewPengguna);

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, (String) viewPengguna.get("USER_LOGIN"));
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			//open history
			viewUserBaru = viewDataPenggunaInternal(session,USER_ID,"",ID_PERMOHONAN);
			checkForHistory(session,USER_ID, viewUserAsal, viewUserBaru,aktiviti);
			//close history

			skrin_name = "app/admin/UV3/viewPenggunaInternal.jsp";

		}else if(command.equals("savePenggunaINT")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			//open history
			viewUserBaru = null;
			viewUserAsal = null;
			String aktiviti = "UPDATE";
			if(USER_ID.equals("") && USER_ID!=null){
				aktiviti = "INSERT";
			}
			viewUserAsal = viewDataPenggunaINT(session,USER_ID,"");
			//close history

			USER_ID = simpanPenggunaINT(session,USER_ID, internalType);

			viewPengguna = viewDataPenggunaINT(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);

			String ID_JENISPEJABAT = (String) viewPengguna.get("ID_JENISPEJABAT");
			if(ID_JENISPEJABAT.equals("3")){
				viewPejabat = viewPejabat(session,(String) viewPengguna.get("ID_PEJABAT"));
				this.context.put("viewPejabat",viewPejabat);

			}

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, (String) viewPengguna.get("USER_LOGIN"));
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			//open history
			viewUserBaru = viewDataPenggunaINT(session,USER_ID,"");
			checkForHistory(session,USER_ID, viewUserAsal, viewUserBaru,aktiviti);
			//close history

			skrin_name = "app/admin/UV3/viewPenggunaINT.jsp";

		}else if(command.equals("savePenggunaKJP")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			//open history
			viewUserBaru = null;
			viewUserAsal = null;
			String aktiviti = "UPDATE";
			if(USER_ID.equals("") && USER_ID!=null){
				aktiviti = "INSERT";
			}
			viewUserAsal = viewDataPenggunaKJP(session,USER_ID,"");
			//close history

			String current_role_kjp = "";
			USER_ID = simpanPenggunaKJP(session,USER_ID, internalType);
			if(!USER_ID.equals("")){
				current_role_kjp = getCurrentRoleKJP(session, USER_ID);
			}
			this.context.put("current_role_kjp",current_role_kjp);
			viewPengguna = viewDataPenggunaKJP(session,USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, (String) viewPengguna.get("USER_LOGIN"));
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			//open history
			viewUserBaru = viewDataPenggunaKJP(session,USER_ID,"");
			this.context.put("viewUserBaru", viewUserBaru);
			checkForHistory(session,USER_ID, viewUserAsal, viewUserBaru,aktiviti);
			//close history

			skrin_name = "app/admin/UV3/viewPenggunaKJP.jsp";

		}else if(command.equals("savePenggunaOnline")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			//open history
			viewUserBaru = null;
			viewUserAsal = null;
			String aktiviti = "UPDATE";
			if(USER_ID.equals("") && USER_ID!=null){
				aktiviti = "INSERT";
			}
			viewUserAsal = viewDataPenggunaOnline(session, USER_ID,"");
			//close history

			USER_ID = simpanPenggunaOnline(session,USER_ID, internalType);

			viewPengguna = viewDataPenggunaOnline(session, USER_ID,"");
			this.context.put("viewPengguna", viewPengguna);

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, (String) viewPengguna.get("USER_LOGIN"));
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			//open history
			viewUserBaru = viewDataPenggunaOnline(session, USER_ID,"");
			checkForHistory(session,USER_ID, viewUserAsal, viewUserBaru,aktiviti);
			//close history

			skrin_name = "app/admin/UV3/viewPenggunaOnline.jsp";

		}else if(command.equals("showSenaraiPenggunaKJP")){
			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_KJP");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_KJP");
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y")){
				String USER_ID = getParam("USER_ID");
				String USER_NAME = getParam("USER_NAME");
				deletePengguna(session,USER_ID,"KJP",USER_NAME);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Pengguna '"+USER_NAME+"' Berjaya Dihapus");

			}

			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			if(!carianTerperinci.equals("")  || getParam("CT_FLAGTEPERINCI_CARIAN_KJP").equals("Y")){
				listPenggunaKJP = listPengunaKJP(session,carianTerperinci);
				setupPageList(session, action, listPenggunaKJP, "listPenggunaKJP",command,"div_PenggunaKJP");
				//open ct
				this.context.put("PrintlistPenggunaKJP",listPenggunaKJP);
				//close ct
			}
			if(getParam("FlagCetak").equals("Y")){
				skrin_name = "app/admin/UV3/SenaraiPenggunaKJP_Print.jsp";
			}else if (getParam("FlagCetakSemakanKJP").equals("Y")){
				listPenggunaInternalPrint = listPengunaKJP(session,carianTerperinci);
				setupPageList(session, action, listPenggunaInternalPrint, "listPenggunaKJP",command,"div_PenggunaKJP");
				//open ct
				this.context.put("PrintlistPengguna",listPenggunaInternalPrint);

				/*String jumPelulus = getCountTugasan (session,"4",carianTerperinci); //pelulus
				String jumPenyemak = getCountTugasan (session,"9",carianTerperinci); //penyemak
				String jumPenyedia = getCountTugasan (session,"24",carianTerperinci); //penyedia

				this.context.put("jumPelulus",jumPelulus);
				this.context.put("jumPenyemak",jumPenyemak);
				this.context.put("jumPenyedia",jumPenyedia);*/

				skrin_name = "app/admin/UV3/SenaraiPrintPenggunaAllKJP.jsp";

			}else{
				skrin_name = "app/admin/UV3/SenaraiPenggunaKJP.jsp";
			}

		}else if(command.equals("showSenaraiPenggunaINT")){
			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_INT");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_INT");
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y")){
				String USER_ID = getParam("USER_ID");
				String USER_NAME = getParam("USER_NAME");
				deletePengguna(session,USER_ID,"INT",USER_NAME);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Pengguna '"+USER_NAME+"' Berjaya Dihapus");

			}

			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			if(!carianTerperinci.equals("")  || getParam("CT_FLAGTEPERINCI_CARIAN_INT").equals("Y")){
				listPenggunaINT = listPenggunaINT(session,carianTerperinci);
				setupPageList(session, action, listPenggunaINT, "listPenggunaINT",command,"div_PenggunaINT");
				//open ct
				this.context.put("PrintlistPenggunaINT",listPenggunaINT);
				//close ct
			}

			if(getParam("FlagCetak").equals("Y")){
				skrin_name = "app/admin/UV3/SenaraiPenggunaIntegrasiDalaman_Print.jsp";
			}else if (getParam("FlagCetakSemakanINT").equals("Y")){
				listPenggunaINT = listPenggunaINT(session,carianTerperinci);
				setupPageList(session, action, listPenggunaINT, "listPenggunaINT",command,"div_PenggunaINT");
				this.context.put("PrintlistPengguna",listPenggunaINT);

				//listStatsJawatan = getStatsJawatan(session,"Negeri",carianTerperinci);
				//this.context.put("listStatsJawatan",listStatsJawatan);
				skrin_name = "app/admin/UV3/SenaraiPrintPenggunaAllIntegrasi.jsp";

			}else{
				skrin_name = "app/admin/UV3/SenaraiPenggunaIntegrasiDalaman.jsp";
			}

		}else if(command.equals("showSenaraiPenggunaOnline")){
			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_Online");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_Online");
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y")){
				String USER_ID = getParam("USER_ID");
				String USER_NAME = getParam("USER_NAME");
				deletePengguna(session,USER_ID,"online",USER_NAME);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Pengguna '"+USER_NAME+"' Berjaya Dihapus");

			}

			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			if(!carianTerperinci.equals("")  || getParam("CT_FLAGTEPERINCI_CARIAN_Online").equals("Y")){
				listPenggunaOnline = listPengunaOnline(session,carianTerperinci);
				setupPageList(session, action, listPenggunaOnline, "listPenggunaOnline",command,"div_PenggunaOnline");
				//open ct
				this.context.put("PrintlistPenggunaOnline",listPenggunaOnline);
				//close ct
			}

			if(getParam("FlagCetak").equals("Y")){
				skrin_name = "app/admin/UV3/SenaraiPenggunaOnline_Print.jsp";
			}else if (getParam("FlagCetakSemakanOnline").equals("Y")){
				listPenggunaOnline = listPengunaOnline(session,carianTerperinci);
				setupPageList(session, action, listPenggunaOnline, "listPenggunaOnline",command,"div_PenggunaOnline");
				this.context.put("PrintlistPengguna",listPenggunaOnline);

				//listStatsJawatan = getStatsJawatanOnline(session,"Online",carianTerperinci);
				//this.context.put("listStatsJawatan",listStatsJawatan);
				skrin_name = "app/admin/UV3/SenaraiPrintPenggunaAllOnline.jsp";

			}else{
				skrin_name = "app/admin/UV3/SenaraiPenggunaOnline.jsp";
			}

		}else if(command.equals("showSenaraiPenggunaMemohon")){
			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_MOHON");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_MOHON");
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			//close ct

			this.context.put("SuccessMesejDeleteUser", "");
			String FLAG_DELETE = getParam("FLAG_DELETE");
			if(FLAG_DELETE.equals("Y")){
				String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
				String NAMA = getParam("NAMA");
				deletePenggunaMOHON(session,ID_PERMOHONAN,NAMA);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Permohonan ID Pengguna '"+NAMA+"' Berjaya Dihapus");

			}

			String action = getParam("action");
			String carianTerperinci = getParam("carianTerperinci");
			if(!carianTerperinci.equals("")){
				listPenggunaMOHON = listPengunaMOHON(session,carianTerperinci);
			}else{
				if(getParam("CT_FLAGTEPERINCI_CARIAN_MOHON").equals("Y")){
					listPenggunaMOHON = listPengunaMOHON(session,carianTerperinci);
				}else{
					listPenggunaMOHON = listPengunaMOHON(session,"");
				}

			}
			setupPageList(session, action, listPenggunaMOHON, "listPenggunaMOHON",command,"div_PenggunaMOHON");
			//open ct
			this.context.put("PrintlistPenggunaMOHON",listPenggunaMOHON);
			//close ct

			if(getParam("FlagCetak").equals("Y")){
				skrin_name = "app/admin/UV3/SenaraiPenggunaMemohon_Print.jsp";
			}else{
				skrin_name = "app/admin/UV3/SenaraiPenggunaMemohon.jsp";
			}

		//tambah untuk print borang pengesahan (with format)
		}else if(command.equals("cetakBorangPengesahan")){
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);

			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
			this.context.put("viewPengguna", viewPengguna);

			if(!viewPengguna.get("ID_PEJABATJKPTG").equals("")){
				viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
				this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}

			listRoleByUserLogin = listRoleByUserLogin(session, ID_PERMOHONAN,internalType,ID_PERMOHONAN);
			this.context.put("listRoleByUserLogin", listRoleByUserLogin);

			skrin_name = "app/admin/UV3/BorangPengesahanPengguna.jsp";

		}
		/*else if(command.equals("cetakLampiranA"))
		{
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			this.context.put("ID_PERMOHONAN", ID_PERMOHONAN);

			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			listRoleByUserLogin = listRoleByUserLogin(session, ID_PERMOHONAN,internalType,ID_PERMOHONAN);
			this.context.put("listRoleByUserLogin", listRoleByUserLogin);

			viewPengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
			this.context.put("viewPengguna", viewPengguna);

			if(!viewPengguna.get("ID_PEJABATJKPTG").equals(""))
			{
			viewPejabatJKPTG = viewPejabatJKPTG(session,(String) viewPengguna.get("ID_PEJABATJKPTG"));
			this.context.put("viewPejabatJKPTG",viewPejabatJKPTG);
			}


			skrin_name = "app/admin/UV3/PrintRolePemohon.jsp";
		}*/

		//Diba tambah untuk Laporan Statistik
		else if(command.equals("showStats")){
			String mode = getParam("mode");
//			myLogger.info("mode - "+mode);
			this.context.put("mode", mode);

			if(mode.equals("Online")){
				statsOnlineUmur = LaporanStatsAdmin.getStatsUmur();
				this.context.put("statsOnlineUmur", statsOnlineUmur);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogOnlineUmur.jsp";

			}else if(mode.equals("OnlineKateg")){
				statsOnlineKategUser = LaporanStatsAdmin.getStatsKategUser();
				this.context.put("statsOnlineKategUser", statsOnlineKategUser);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogOnlineKateg.jsp";

			}else if(mode.equals("OnlineHari")){
				statsOnlineHari = LaporanStatsAdmin.getStatsLogbyHari();
				this.context.put("statsOnlineHari", statsOnlineHari);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogOnlineHari.jsp";

			}else if(mode.equals("OnlineNegeri")){
				statsOnlineNegeri = LaporanStatsAdmin.getStatsLogbyNegeri(5);
				this.context.put("statsOnlineNegeri", statsOnlineNegeri);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogOnlineNegeri.jsp";

			}else if(mode.equals("InternalNegeri")){
				filter_negeri = getParam("filterNegeri");
				System.out.println("filter_negeri -- "+ filter_negeri);
				this.context.put("filter_negeri", filter_negeri);

				if (filter_negeri.equals("16")){
					statsInternalNegeri = LaporanStatsAdmin.getStatsLogbyNegeri(1);
				} else {
					statsInternalNegeri = LaporanStatsAdmin.getStatsLogbyNegeri(2);
				}
				this.context.put("statsInternalNegeri", statsInternalNegeri);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogInternalNegeri.jsp";

			}else if(mode.equals("KJP")){
				statsKJP = LaporanStatsAdmin.getStatsLogbyNegeri(3);
				this.context.put("statsKJP", statsKJP);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogKJP.jsp";

			}else if(mode.equals("Integrasi")){
				statsIntegrasi = LaporanStatsAdmin.getStatsLogbyNegeri(4);
				this.context.put("statsIntegrasi", statsIntegrasi);

				skrin_name = "app/admin/UV3/laporanStatistik/statsLogIntegrasi.jsp";

			}

		}else if(command.equals("showListGred")){
			String ID_JAWATAN = getParam("ID_JAWATAN");
			this.context.put("ID_JAWATAN", ID_JAWATAN);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);

			list_TBLRUJGRED = listTableRujukanV3(session,"TBLRUJGREDBYJAWATAN",ID_JAWATAN,"",internalType);
			this.context.put("list_TBLRUJGRED", list_TBLRUJGRED);

			skrin_name = "app/admin/UV3/showListGred.jsp";

		}else if(command.equals("cetakMaklumatPengguna")){
			String USER_ID = getParam("USER_ID");
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			String USER_LOGIN = getParam("USER_LOGIN");
			this.context.put("USER_LOGIN", USER_LOGIN);

			listAdditionalRoleByUserLogin = listRoleByUserLogin_Selected(session, USER_LOGIN);
			this.context.put("listAdditionalRoleByUserLogin", listAdditionalRoleByUserLogin);

			if(internalType.equals("HQ")){
				viewPengguna = viewDataPenggunaInternal(session,USER_ID,"","");
				this.context.put("viewPengguna", viewPengguna);

			}else if(internalType.equals("Negeri")){
				viewPengguna = viewDataPenggunaInternal(session,USER_ID,"","");
				this.context.put("viewPengguna", viewPengguna);

			}else if(internalType.equals("KJP")){
				viewPengguna = viewDataPenggunaKJP(session, USER_ID, "");
				this.context.put("viewPengguna", viewPengguna);

			}else if(internalType.equals("INT")){
				viewPengguna = viewDataPenggunaINT(session, USER_ID, "");
				this.context.put("viewPengguna", viewPengguna);

			}else if(internalType.equals("Online")){
				viewPengguna = viewDataPenggunaOnline(session, USER_ID, "");
				this.context.put("viewPengguna", viewPengguna);

			}else { }
			skrin_name = "app/admin/UV3/printBorangPengguna.jsp";

		}else if(command.equals("simpanDokumen")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			String action = getParam("action");

			String returnDivUpload = getParam("returnDivUpload");
			this.context.put("returnDivUpload",returnDivUpload);

			this.context.put("commandDoc", "showDokumen");
			this.context.put("after_saveDOC", "Y");

			uploadFiles(USER_ID);
	       	this.context.put("isComplete", true);
	       	this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");

			skrin_name = "app/admin/UV3/index.jsp";;

		}else if(command.equals("showDokumen")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);

			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);

			String action = getParam("action");
			String FLAG_DELETE = getParam("FLAG_DELETE");

			if (FLAG_DELETE.equals("Y")){
				//delete dokumen
				String ID_DOKUMENADMIN = getParam("ID_DOKUMENADMIN");
				deleteDokumen(session, USER_ID, ID_DOKUMENADMIN);
				this.context.put("SuccessMesejDeleteUser", "Dokumen Berjaya Dihapus");

			}

			listDokumen = getListDoc(session, USER_ID, "user"); //flag user untuk uv3
			setupPageList(session, action, listDokumen, "listDokumen",command,"Senarai_Doc"+USER_ID);

			/*listDokumen = getListDoc(session, ID_SEKSYEN);
			this.context.put("listDokumen",listDokumen);*/
			skrin_name = "app/admin/UV3/SenaraiLampiranPengguna.jsp";

		}/*else if(command.equals("showfieldPejabat")){
			String USER_ID = getParam("USER_ID");
			this.context.put("USER_ID", USER_ID);
			String internalType = getParam("internalType");
			this.context.put("internalType", internalType);
			String ID_PERMOHONAN = getParam("ID_PERMOHONAN");
			viewPengguna = viewDataPenggunaMOHON(session,ID_PERMOHONAN);
			this.context.put("viewPengguna", viewPengguna);
			String ID_PEJABAT = getParam("ID_PEJABAT");
			myLogger.info("ID_PEJABAT ros : "+ID_PEJABAT);
			viewPejabatMajlisAgama = viewPejabatMajlisAgama(session,ID_PEJABAT);
			this.context.put("viewPejabatMajlisAgama",viewPejabatMajlisAgama);
			myLogger.info("viewPejabatMajlisAgama ros : "+viewPejabatMajlisAgama);

			List list_TBLRUJPEJABAT2 = listTableRujukanV3(session,"TBLRUJPEJABAT2",ID_PEJABAT,"",internalType);
			this.context.put("list_TBLRUJPEJABAT", list_TBLRUJPEJABAT2);

			Hashtable a = (Hashtable) list_TBLRUJPEJABAT2.get(0);
			String keterangan = (String) a.get("KETERANGAN");

			myLogger.info("keterangan: " +keterangan);

			if(!ID_PEJABAT.equals("")){
				skrin_name = "app/admin/UV3/showFieldName.jsp";
			}else{

			}

		}*/else{
			this.context.put("FlagCari", "Y");
			skrin_name = "app/admin/UV3/index.jsp";

		}
		myLogger.info(" skrin_name : "+skrin_name);
		return skrin_name;

	}

	public void setupPageDefaultPut(){
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

	public void defaultPut(){
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

		this.context.put("internalType","");
		this.context.put("carianTerperinci","");
		this.context.put("listPenggunaInternalHQ","");
		this.context.put("listPenggunaInternalNegeri","");
		this.context.put("listPenggunaOnline","");
		this.context.put("viewPengguna","");
		this.context.put("listAdditionalRoleByUserLogin","");

		this.context.put("fieldName","");
		this.context.put("tableRujukan","");

		this.context.put("list_TBLRUJJAWATAN","");
		this.context.put("list_TBLRUJBANGSA","");
		this.context.put("list_TBLRUJSEKSYEN","");
		this.context.put("list_TBLRUJAGAMA","");
		this.context.put("list_TBLRUJNEGERI","");
		this.context.put("list_TBLRUJBANDAR","");
		this.context.put("listPejabatJKPTG","");
		this.context.put("listRole","");
		this.context.put("viewPejabatJKPTG","");
		this.context.put("listRoleByUserLogin","");

		this.context.put("checkUSERLOGIN", "");
		this.context.put("SuccessMesej", "");
		this.context.put("SuccessMesejDeleteUser", "");
		this.context.put("SuccessMesejRole", "");
		this.context.put("display_info_check_id", "");
		this.context.put("listPenggunaKJP", "");

		this.context.put("viewAlamatKJP", "");
		this.context.put("list_TBLRUJKEMENTERIAN", "");
		this.context.put("list_TBLRUJAGENSI", "");

		this.context.put("current_role_kjp","");
		this.context.put("listPengunaAT","");
		this.context.put("listPengunaPLA","");
		this.context.put("viewPengunaPLA","");
		this.context.put("listPenggunaINT","");

		this.context.put("list_TBLINTRUJJENISPEJABAT","");
		this.context.put("list_TBLRUJDAERAH","");
		this.context.put("listPejabat","");
		this.context.put("viewPejabat","");

		this.context.put("FlagCari", "");

		this.context.put("commandPIP", "");
		this.context.put("after_saveDOCPIP", "");
		this.context.put("ID_PERMOHONAN", "");
		this.context.put("returnDIVDOCPIP", "");

		//open ct
		this.context.put("CT_NAMA", "");
		this.context.put("CT_ID_SEKSYEN", "");
		this.context.put("CT_ID_NEGERI", "");
		this.context.put("CT_ID_PEJABATJKPTG", "");
		this.context.put("CT_ROLE_MAIN", "");
		this.context.put("CT_FLAG_AKTIF", "");
		this.context.put("CT_ID_JAWATAN", "");
		this.context.put("CT_LOGMASUK_MULA", "");
		this.context.put("CT_LOGMASUK_AKHIR", "");
		this.context.put("CT_STATUS_LOG", "");
		this.context.put("CT_OPENCLOSE_CARIAN", "");
		this.context.put("CT_FLAGTEPERINCI_CARIAN", "");
		this.context.put("PrintlistPenggunaInternal","");
		this.context.put("PrintlistPenggunaKJP","");
		this.context.put("CT_ID_KEMENTERIAN","");
		this.context.put("CT_ID_AGENSI","");
		this.context.put("CT_ID_TUGASAN","");
		this.context.put("CT_ID_PEJABAT", "");
		this.context.put("CT_ID_DAERAH", "");
		this.context.put("CT_ID_JENISPEJABAT", "");
		this.context.put("CT_JAWATAN", "");
		this.context.put("CT_ID_KATEGORI_PENGGUNA", "");
		this.context.put("CT_HAD_UMUR", "");
		this.context.put("CT_TARIKHPENDAFTARAN_MULA", "");
		this.context.put("CT_TARIKHPENDAFTARAN_AKHIR", "");
		this.context.put("CT_STATUSPENDAFTARAN", "");
		//close ct

		this.context.put("listPengunaHISTORY", "");
		this.context.put("listPengunaHISTORY_SUB", "");

		this.context.put("mode","");
		this.context.put("PrintlistPengguna","");


	}

	@SuppressWarnings("unchecked")
	private void saveDocPIP(HttpSession session,String internalType,String USER_ID,String ID_PERMOHONAN) throws Exception {

			    DiskFileItemFactory factory = new DiskFileItemFactory();
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			    if(!isMultipart)
			    {
			    myLogger.info("BUKAN MULTIPART");
			    }
			    else
			    {
			    myLogger.info("MULTIPART");
			    }
			    List items = upload.parseRequest(request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			      FileItem item = (FileItem)itr.next();
			      if ((!(item.isFormField()))  && (item.getName() != null) && (!("".equals(item.getName())))) {

			    	  saveDocPIP_DB(item,ID_PERMOHONAN);

			      }
			    }
			  }

	private void saveDocPIP_DB(FileItem item,String ID_PERMOHONAN) throws Exception {
		    myLogger.info("::: saveDocPIP_DB ------- "+item+" ID_PERMOHONAN : "+ID_PERMOHONAN);
		 	HttpSession session = request.getSession();

	  		Db db = null;
	        try {
	        	//long id_esdokumen = DB.getNextID("TBLESDOKUMEN_SEQ");
	        	db = new Db();
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	SQLRenderer r = new SQLRenderer();
	        	PreparedStatement ps = con.prepareStatement("UPDATE PERMOHONANIDPENGGUNA SET CONTENT = ?, NAMA_DOC = ?, JENIS_MIME = ? WHERE USER_ID = ?");
	        	ps.setBinaryStream(1, item.getInputStream(),(int)item.getSize());
	        	ps.setString(2, item.getName());
	        	ps.setString(3,item.getContentType());
	        	ps.setString(4,ID_PERMOHONAN);
	        	ps.executeUpdate();
	            con.commit();
		    } finally {
			      if (db != null) db.close();
		    }

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
	public List listAdditionalRoleByUserLogin(HttpSession session, String USER_LOGIN)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listAdditionalRoleByUserLogin = null;
		String sql = "";

		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT DISTINCT UPPER(R.DESCRIPTION) AS NAMA_ROLE, R.NAME AS ROLE FROM ROLE R, USER_ROLE UR "+
					" WHERE R.NAME = UR.ROLE_ID " +
					" AND UR.USER_ID = '"+USER_LOGIN+"' ";
			myLogger.info(" V3: SQL listAdditionalRoleByUserLogin :"+ sql);
			rs = stmt.executeQuery(sql);
			listAdditionalRoleByUserLogin = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("NAMA_ROLE",rs.getString("NAMA_ROLE") == null ? "" : rs.getString("NAMA_ROLE"));
				h.put("ROLE",rs.getString("ROLE") == null ? "" : rs.getString("ROLE"));
				listAdditionalRoleByUserLogin.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listAdditionalRoleByUserLogin;

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
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX, PEJ.ID_JENISPEJABAT  "+
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
				h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));

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


	@SuppressWarnings("unchecked")
	public List listRoleByUserLogin(HttpSession session, String USER_LOGIN, String internalType,String USER_ID)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRoleByUserLogin = null;
		String sql = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			/*
			String current_role_utama = "";
			myLogger.info(" --------- USER_ID : "+USER_ID);
			if(!USER_ID.equals(""))
			{
				current_role_utama = getRoleUtamaUsers(session,USER_ID);
				myLogger.info(" --------- atas current_role_utama : "+current_role_utama);
			}
			*/
			sql = " SELECT R.NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(R.DESCRIPTION) AS KETERANGAN, 2 AS LAYER, 'Y' AS TICK, 0 AS TOTAL  "+
					" FROM ROLE R, USER_ROLE UR " +
					" WHERE UR.ROLE_ID = R.NAME AND UR.USER_ID = '"+USER_LOGIN+"' ";

					if(internalType.equals("HQ") || internalType.equals("Negeri"))
					{
						sql += " AND R.NAME NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
					}
					else if(internalType.equals("Online"))
					{
						sql += " AND R.NAME IN ('online','ppt-online-user','php-online-user','ppk-online-user')";
					}
					else if(internalType.equals("KJP"))
					{
						sql += " AND R.NAME IN ('online_kjpagensi','online_kjp')";
					}
					sql += " UNION "+
					" SELECT R1.NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(R1.THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(R1.DESCRIPTION) AS KETERANGAN, 2 AS LAYER, '' AS TICK,  0 AS TOTAL "+
					" FROM ROLE R1 WHERE R1.NAME NOT IN (SELECT UR1.ROLE_ID FROM USER_ROLE UR1 WHERE UR1.USER_ID = '"+USER_LOGIN+"') ";
					if(internalType.equals("HQ") || internalType.equals("Negeri"))
					{
						sql += " AND R1.NAME NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
					}
					else if(internalType.equals("Online"))
					{
						sql += " AND R1.NAME IN ('online','ppt-online-user','php-online-user','ppk-online-user')";
					}
					else if(internalType.equals("KJP"))
					{
						sql += " AND R1.NAME IN ('online_kjpagensi','online_kjp')";
					}
					sql+=" UNION "+
					" SELECT DISTINCT '' AS ID,   REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') AS KOD, '' AS KETERANGAN, 1 AS LAYER, '' AS TICK,  "+
					" (SELECT count(*)   "+
					" FROM ROLE R, USER_ROLE UR " +
					" WHERE UR.ROLE_ID = R.NAME AND UR.USER_ID = '"+USER_LOGIN+"' ";
					if(internalType.equals("HQ") || internalType.equals("Negeri"))
					{
						sql += " AND R.NAME NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
					}
					else if(internalType.equals("Online"))
					{
						sql += " AND R.NAME IN ('online','ppt-online-user','php-online-user','ppk-online-user','htp-online-user')";
					}
					else if(internalType.equals("KJP"))
					{
						sql += " AND R.NAME IN ('online_kjpagensi','online_kjp')";
					}
					sql += " AND REGEXP_REPLACE(REGEXP_REPLACE(R3.THEME, 'eTapp_', ''),'.css','') = REGEXP_REPLACE(REGEXP_REPLACE(R.THEME, 'eTapp_', ''),'.css',''))  "+
					" AS TOTAL FROM ROLE R3 WHERE  R3.NAME IS NOT NULL ";
					if(internalType.equals("HQ") || internalType.equals("Negeri"))
					{
						sql += " AND R3.NAME NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
					}
					else if(internalType.equals("Online"))
					{
						sql += " AND R3.NAME IN ('online','ppt-online-user','php-online-user','ppk-online-user','htp-online-user')";
					}
					else if(internalType.equals("KJP"))
					{
						sql += " AND R3.NAME IN ('online_kjpagensi','online_kjp')";
					}
					sql+=" ORDER BY KOD, LAYER ";
			myLogger.info(" V3: SQL listRoleByUserLogin :"+ sql);
			rs = stmt.executeQuery(sql);
			listRoleByUserLogin = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
				h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));
				h.put("TOTAL",rs.getString("TOTAL") == null ? "" : rs.getInt("TOTAL"));
				listRoleByUserLogin.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listRoleByUserLogin;

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


			myLogger.info(" tableRujukan ros  :"+ tableRujukan);
			if(tableRujukan.equals("TBLRUJJAWATAN"))
			{
				//sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJAWATAN WHERE KOD_JAWATAN != '00' ORDER BY KOD_JAWATAN";
				//komen sbb tak semua jawatan keluar pada senarai pilihan
				sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJAWATAN ORDER BY KOD_JAWATAN";
			}

			else if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
			{
				sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
			}//diba edit table
			/*else if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
			{
				sql = " SELECT ID_INTEGRASI AS ID, UPPER(KOD_AGENSI) AS KOD, UPPER(NAMA) AS KETERANGAN " +
						" FROM TBLRUJINTEGRASI WHERE ID_JENISINTEG IN (1,2) ORDER BY KOD_AGENSI ";
				sql = 	" SELECT P.ID_PEJABAT AS ID, P.NAMA_PEJABAT AS KETERANGAN, P.KOD_PEJABAT AS KOD " +
						" FROM TBLRUJPEJABAT P, TBLRUJJENISPEJABAT JP, TBLRUJSEKSYEN S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B " +
						" WHERE P.ID_DAERAH = D.ID_DAERAH AND P.ID_NEGERI = N.ID_NEGERI AND P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT " +
						" AND P.ID_BANDAR = B.ID_BANDAR AND P.ID_SEKSYEN = S.ID_SEKSYEN " +
						" ORDER BY P.id_negeri, P.id_daerah " ;
			}*/
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
						if(!id_filter.equals("*"))
						{
							sql += " AND ID_NEGERI = "+id_filter+" ";
						}
					}
					else
					{
						sql += " AND ID_NEGERI != 16 ";
					}
				}

			}
			else if(tableRujukan.equals("TBLRUJDAERAH"))
			{
				sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN FROM TBLRUJDAERAH WHERE ID_NEGERI = '"+id_filter+"' ORDER BY KOD_DAERAH ";
			}
			else if(tableRujukan.equals("TBLRUJBANDAR"))
			{
				sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR WHERE ID_BANDAR IS NOT NULL " +
						" AND ID_NEGERI = '"+id_filter+"' " +
								" ORDER BY KETERANGAN";
			}
			else if(tableRujukan.equals("TBLRUJGRED"))
			{
				sql = " SELECT ID_GRED AS ID, NAMA_GRED AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJGRED WHERE ID_GRED IS NOT NULL " ;

				if (!id_filter.equals("")){
					sql+=	" AND ID_KLASIFIKASI = "+ id_filter;
				}
					sql+=	"  ORDER BY NAMA_GRED ";
			}
			else if(tableRujukan.equals("TBLRUJGREDBYJAWATAN"))
			{
				sql = 	" SELECT G.ID_GRED AS ID, G.NAMA_GRED AS KOD, UPPER(G.KETERANGAN) AS KETERANGAN " +
						" FROM TBLRUJGRED G, TBLRUJGREDJAWATAN GJ "+
						" WHERE G.ID_GRED = GJ.ID_GRED ";

				if (!id_filter.equals("")){
					sql+=	" AND GJ.ID_JAWATAN = "+ id_filter;
				}
					sql+=	"  ORDER BY NAMA_GRED ";
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
				sql = " SELECT NAME AS ID, REGEXP_REPLACE(REGEXP_REPLACE(THEME, 'eTapp_', ''),'.css','') AS KOD, UPPER(DESCRIPTION) AS KETERANGAN, 2 AS LAYER, UPPER(ROLE_DETAILS) AS ROLE_DETAILS  " +
						" FROM ROLE WHERE NAME IS NOT NULL ";
						if(internalType.equals("HQ") || internalType.equals("Negeri") || internalType.equals("INT"))
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
						" SELECT DISTINCT '' AS ID,   REGEXP_REPLACE(REGEXP_REPLACE(THEME, 'eTapp_', ''),'.css','') AS KOD, '' AS KETERANGAN, 1 AS LAYER, '' AS ROLE_DETAILS FROM ROLE " +
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
			else if(tableRujukan.equals("TBLRUJPEJABAT"))
			{
				sql = "  SELECT ID_PEJABAT AS ID, KOD_PEJABAT AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLRUJPEJABAT WHERE ID_JENISPEJABAT = 61 ORDER BY KOD_PEJABAT ";
			}
			else if(tableRujukan.equals("TBLRUJPEJABAT2"))
			{
				sql = " SELECT ID_PEJABAT AS ID, KOD_PEJABAT AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLRUJPEJABAT WHERE ID_PEJABAT IS NOT NULL " +
						" AND ID_PEJABAT = '"+id_filter+"' " +
								" ORDER BY KOD_PEJABAT";
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
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				if(tableRujukan.equals("ROLE"))
				{
					h.put("ROLE_DETAILS",rs.getString("ROLE_DETAILS") == null ? "" : rs.getString("ROLE_DETAILS").toUpperCase());
				}

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

	/*public Hashtable viewPejabatMajlisAgama(HttpSession session, String ID_PEJABAT) throws Exception {
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
					" PEJ.ID_SEKSYEN, PEJ.NO_TEL, PEJ.NO_FAX, PEJ.ID_JENISPEJABAT  "+
					" FROM TBLRUJPEJABATJKPTG PEJ,TBLRUJBANDAR BAN, TBLRUJNEGERI NEG  "+
					" WHERE PEJ.ID_NEGERI = NEG.ID_NEGERI(+)  "+
					" AND PEJ.ID_BANDAR = BAN.ID_BANDAR(+) ";
					sql += " AND PEJ.ID_PEJABATJKPTG = "+ID_PEJABAT+" ";
					sql += " ORDER BY PEJ.KOD_JKPTG ";

			sql = " SELECT ID_PEJABAT AS ID, KOD_PEJABAT AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLRUJPEJABAT WHERE ID_PEJABAT IS NOT NULL " +
					" AND ID_PEJABAT = '"+ID_PEJABAT+"' ";
			myLogger.info(" viewPejabatMajlisAgama :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable h;
			h = new Hashtable();

			while (rs.next()) {
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD"));
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
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
	}*/




	public List getStatsJawatan(HttpSession session, String flag_HQ, String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPenggunaInternal = null;
		String sql = "";



		//open ct
		String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_"+flag_HQ);
		String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_"+flag_HQ);
		myLogger.info(" CT_FLAGTEPERINCI_CARIAN ::: "+CT_FLAGTEPERINCI_CARIAN);
		String CT_NAMA = getParam("CT_NAMA_"+flag_HQ);
		String CT_ID_SEKSYEN = getParam("CT_ID_SEKSYEN_"+flag_HQ);
		String CT_ID_NEGERI = getParam("CT_ID_NEGERI_"+flag_HQ);
		String CT_ID_PEJABATJKPTG = getParam("CT_ID_PEJABATJKPTG_"+flag_HQ);
		String CT_ROLE_MAIN = getParam("CT_ROLE_MAIN_"+flag_HQ);
		String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_"+flag_HQ);
		String CT_ID_JAWATAN = getParam("CT_ID_JAWATAN_"+flag_HQ);
		String CT_LOGMASUK_MULA = getParam("CT_LOGMASUK_MULA_"+flag_HQ);
		String CT_LOGMASUK_AKHIR = getParam("CT_LOGMASUK_AKHIR_"+flag_HQ);
		String CT_STATUS_LOG = getParam("CT_STATUS_LOG_"+flag_HQ);


		this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
		this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
		this.context.put("CT_NAMA", CT_NAMA);
		this.context.put("CT_ID_SEKSYEN", CT_ID_SEKSYEN);
		this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
		this.context.put("CT_ID_PEJABATJKPTG", CT_ID_PEJABATJKPTG);
		this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
		this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
		this.context.put("CT_ID_JAWATAN", CT_ID_JAWATAN);
		this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
		this.context.put("CT_LOGMASUK_MULA", CT_LOGMASUK_MULA);
		this.context.put("CT_LOGMASUK_AKHIR", CT_LOGMASUK_AKHIR);
		this.context.put("CT_STATUS_LOG", CT_STATUS_LOG);
		//close ct


		try {
			db = new Db();
			stmt = db.getStatement();
			sql = 	" SELECT COUNT(USER_ID) AS JUMLAH_PENGGUNA, JAWATAN FROM ( "+
					" SELECT U.USER_ID,U.USER_LOGIN, S.NAMA_SEKSYEN AS BAHAGIAN, UPPER(U.USER_NAME) AS FULLNAME, "+
					" UPPER(J.KETERANGAN) AS JAWATAN, UPPER(R.DESCRIPTION) AS ROLE_UTAMA,  "+
					//" MAX(WL.LOG_DATE) AS WAKTU_AKHIR_LOGIN, "+
					" TO_CHAR(MAX(WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN, "+
					" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
					" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

					" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' "+
					" WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
					" ELSE 'AKTIF' END) AS KEAKTIFAN, " +
					" UPPER(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, "+
					" UPPER(N.NAMA_NEGERI) AS NEGERI_PEJABAT, "+
					" UR_TEMP.TOTAL_ROLE ";

			//open ct
			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
			{
				sql += "  ,CHECK_TR.TOTAL_ROLE_CARI ";
			}
			//close ct

			sql += " FROM USERS U, USERS_INTERNAL UI, TBLRUJSEKSYEN S, TBLRUJJAWATAN J, ROLE R,  "+
					" WEB_LOGGER WL, TBLRUJPEJABATJKPTG PEJ, TBLRUJNEGERI N," +
					" (SELECT UR.USER_ID,COUNT(UR.ROLE_ID) AS TOTAL_ROLE FROM USER_ROLE UR GROUP BY UR.USER_ID) UR_TEMP";

			//open ct
			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
			{
				sql += " ,(SELECT RX.USER_ID,COUNT (RX.ROLE_ID) AS TOTAL_ROLE_CARI FROM USER_ROLE RX WHERE UPPER(RX.ROLE_ID) = '"+CT_ROLE_MAIN.toUpperCase()+"' "+
	               " GROUP BY RX.USER_ID) CHECK_TR ";
			}
			//close ct

			sql+=	" WHERE U.USER_ID = UI.USER_ID "+
					" AND U.USER_LOGIN = UR_TEMP.USER_ID(+) ";

			//open ct
			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
			{
				sql += " AND U.USER_LOGIN =  CHECK_TR.USER_ID(+) ";
			}
			//close ct

			sql +=	" AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
					" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "+
					" AND PEJ.ID_NEGERI = N.ID_NEGERI(+) " +
					" ";


					//open ct
					if(!carianTerperinci.equals("") && carianTerperinci != null)
					{
					sql += " AND (";

					sql += " UPPER(U.USER_LOGIN) LIKE UPPER('%"+carianTerperinci.trim()+"%') ";
					sql += " OR UPPER(S.NAMA_SEKSYEN) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(U.USER_NAME) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(N.NAMA_NEGERI) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(PEJ.NAMA_PEJABAT) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(R.DESCRIPTION) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(J.KETERANGAN) LIKE UPPER('%"+carianTerperinci+"%') ";

					sql += ") ";

					}

					if(!CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
					{

						if(!CT_NAMA.equals("") && CT_NAMA!=null)
						{
							sql += " AND (";
							sql += " UPPER(U.USER_LOGIN) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
							sql += " OR UPPER(U.USER_NAME) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
							sql += ") ";
						}
						if(!CT_ID_SEKSYEN.equals("") && CT_ID_SEKSYEN!=null)
						{
							sql += " AND (";
							sql += " UI.ID_SEKSYEN = '"+CT_ID_SEKSYEN+"' ";
							sql += ") ";
						}
						if(!CT_ID_NEGERI.equals("") && CT_ID_NEGERI!=null)
						{
							sql += " AND (";
							sql += " UI.ID_NEGERI = '"+CT_ID_NEGERI+"' ";
							sql += ") ";
						}
						if(!CT_ID_PEJABATJKPTG.equals("") && CT_ID_PEJABATJKPTG!=null)
						{
							sql += " AND (";
							sql += " UI.ID_PEJABATJKPTG = '"+CT_ID_PEJABATJKPTG+"' ";
							sql += ") ";
						}
						if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null)
						{
							sql += " AND (";
							sql += " UPPER(U.USER_ROLE) = '"+CT_ROLE_MAIN.toUpperCase()+"' OR CHECK_TR.TOTAL_ROLE_CARI>0 ";
							sql += ") ";
						}
						if(!CT_FLAG_AKTIF.equals("") && CT_FLAG_AKTIF!=null)
						{
							if(CT_FLAG_AKTIF.equals("1"))
							{
								sql += " AND (";
								sql += " UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '' ";
								sql += ") ";
							}
							else if(CT_FLAG_AKTIF.equals("2"))
							{
								sql += " AND (";
								sql += " UPPER(UI.FLAG_AKTIF) = '2' ";
								sql += ") ";
							}
						}
						if(!CT_ID_JAWATAN.equals("") && CT_ID_JAWATAN!=null)
						{
							sql += " AND (";
							sql += " UI.ID_JAWATAN = '"+CT_ID_JAWATAN+"' ";
							sql += ") ";
						}
						if(!CT_LOGMASUK_MULA.equals("") && CT_LOGMASUK_MULA!=null)
						{
							sql += " AND (";
							sql += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') >=  TO_DATE('"+CT_LOGMASUK_MULA+"','DD/MM/YYYY') ";
							sql += ") ";
						}
						if(!CT_LOGMASUK_AKHIR.equals("") && CT_LOGMASUK_AKHIR!=null)
						{
							sql += " AND (";
							sql += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=  TO_DATE('"+CT_LOGMASUK_AKHIR+"','DD/MM/YYYY') ";
							sql += ") ";
						}
						if(!CT_STATUS_LOG.equals("") && CT_STATUS_LOG!=null)
						{
						//	" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
						//			" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

							if(CT_STATUS_LOG.equals("1"))
							{
								//SENYAP (60 Hari Dari Tarikh Terakhir Login)
								sql += " AND (";
								sql += " TRUNC(SYSDATE - TO_DATE(WL.LOG_DATE)) > 60 ";
								sql += ") ";
							}
							else if(CT_STATUS_LOG.equals("2"))
							{
								//KATALALUAN TELAH LUPUT (90 Hari Dari Tarikh Terakhir Menukar Katalaluan)
								sql += " AND (";
								sql += " TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) > 90 ";
								sql += ") ";
							}
						}
					}
					//close ct


					if(flag_HQ.equals("HQ"))
					{
						sql += " AND (PEJ.ID_NEGERI = 16 OR PEJ.ID_NEGERI IS NULL) ";
					}
					else if(flag_HQ.equals("Negeri"))
					{
						sql += " AND PEJ.ID_NEGERI != 16 ";
					}

				sql +=	" AND U.USER_ROLE = R.NAME(+) "+
					" AND U.USER_LOGIN = WL.USER_NAME(+) "+
					" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG "+
					" GROUP BY U.USER_ID,UR_TEMP.TOTAL_ROLE, ";

				//open ct
				if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{
					sql += " CHECK_TR.TOTAL_ROLE_CARI, ";
				}
				//close ct

				sql += "U.USER_LOGIN,S.NAMA_SEKSYEN,U.USER_NAME,J.KETERANGAN,R.DESCRIPTION,UI.FLAG_AKTIF,U.LAST_CHANGEPASSWORD, " +
					" PEJ.NAMA_PEJABAT, N.NAMA_NEGERI "+
					" ORDER BY USER_LOGIN ";

				sql += " ) GROUP BY JAWATAN ";

			myLogger.info(" V3: SQL listPengunaInternal :"+ sql);

			rs = stmt.executeQuery(sql);
			senaraiPenggunaInternal = Collections.synchronizedList(new ArrayList());
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
				h.put("JUMLAH_PENGGUNA",rs.getString("JUMLAH_PENGGUNA") == null ? "" : rs.getString("JUMLAH_PENGGUNA"));
				h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));

				//myLogger.info(" display_note bawah : "+display_note);
				//myLogger.info(" display_note password : "+rs.getString("DAYS_AFTERCHANGEPASS"));

				senaraiPenggunaInternal.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return senaraiPenggunaInternal;

		}


	@SuppressWarnings("unchecked")
	public List listPengunaInternal(HttpSession session, String flag_HQ, String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPenggunaInternal = null;
		String sql = "";



		//open ct
		String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_"+flag_HQ);
		String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_"+flag_HQ);
		myLogger.info(" CT_FLAGTEPERINCI_CARIAN ::: "+CT_FLAGTEPERINCI_CARIAN);
		String CT_NAMA = getParam("CT_NAMA_"+flag_HQ);
		String CT_ID_SEKSYEN = getParam("CT_ID_SEKSYEN_"+flag_HQ);
		String CT_ID_NEGERI = getParam("CT_ID_NEGERI_"+flag_HQ);
		String CT_ID_PEJABATJKPTG = getParam("CT_ID_PEJABATJKPTG_"+flag_HQ);
		String CT_ROLE_MAIN = getParam("CT_ROLE_MAIN_"+flag_HQ);
		String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_"+flag_HQ);
		String CT_ID_JAWATAN = getParam("CT_ID_JAWATAN_"+flag_HQ);
		String CT_LOGMASUK_MULA = getParam("CT_LOGMASUK_MULA_"+flag_HQ);
		String CT_LOGMASUK_AKHIR = getParam("CT_LOGMASUK_AKHIR_"+flag_HQ);
		String CT_STATUS_LOG = getParam("CT_STATUS_LOG_"+flag_HQ);


		this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
		this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
		this.context.put("CT_NAMA", CT_NAMA);
		this.context.put("CT_ID_SEKSYEN", CT_ID_SEKSYEN);
		this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
		this.context.put("CT_ID_PEJABATJKPTG", CT_ID_PEJABATJKPTG);
		this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
		this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
		this.context.put("CT_ID_JAWATAN", CT_ID_JAWATAN);
		this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
		this.context.put("CT_LOGMASUK_MULA", CT_LOGMASUK_MULA);
		this.context.put("CT_LOGMASUK_AKHIR", CT_LOGMASUK_AKHIR);
		this.context.put("CT_STATUS_LOG", CT_STATUS_LOG);
		//close ct


		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT U.USER_ID,U.USER_LOGIN, S.NAMA_SEKSYEN AS BAHAGIAN, UPPER(U.USER_NAME) AS FULLNAME, "+
					" UPPER(J.KETERANGAN) AS JAWATAN, UPPER(R.DESCRIPTION) AS ROLE_UTAMA,  "+
					//" MAX(WL.LOG_DATE) AS WAKTU_AKHIR_LOGIN, "+
					" TO_CHAR(MAX(WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN, "+
					" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
					" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

					" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' "+
					" WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
					" ELSE 'AKTIF' END) AS KEAKTIFAN, " +
					" UPPER(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, "+
					" UPPER(N.NAMA_NEGERI) AS NEGERI_PEJABAT, "+
					" UR_TEMP.TOTAL_ROLE ";

			//open ct
			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
			{
				sql += "  ,CHECK_TR.TOTAL_ROLE_CARI ";
			}
			//close ct

			sql += " FROM USERS U, USERS_INTERNAL UI, TBLRUJSEKSYEN S, TBLRUJJAWATAN J, ROLE R,  "+
					" WEB_LOGGER WL, TBLRUJPEJABATJKPTG PEJ, TBLRUJNEGERI N," +
					" (SELECT UR.USER_ID,COUNT(UR.ROLE_ID) AS TOTAL_ROLE, " +
					" COUNT(CASE WHEN UPPER(UR.ROLE_ID) NOT LIKE '%KJP%' THEN UR.ROLE_ID END) AS TOTAL_XKJP, "+
					" COUNT(CASE WHEN UPPER(UR.ROLE_ID) LIKE '%KJP%' THEN UR.ROLE_ID END) AS TOTAL_KJP  "+
					" FROM USER_ROLE UR GROUP BY UR.USER_ID) UR_TEMP";

			//open ct
			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
			{
				sql += " ,(SELECT RX.USER_ID,COUNT (RX.ROLE_ID) AS TOTAL_ROLE_CARI FROM USER_ROLE RX WHERE UPPER(RX.ROLE_ID) = '"+CT_ROLE_MAIN.toUpperCase()+"' "+
	               " GROUP BY RX.USER_ID) CHECK_TR ";
			}
			//close ct

			sql+=	" WHERE U.USER_ID = UI.USER_ID "+
					" AND U.USER_LOGIN = UR_TEMP.USER_ID(+) ";

			//open ct
			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
			{
				sql += " AND U.USER_LOGIN =  CHECK_TR.USER_ID(+) ";
			}
			//close ct

			sql +=	" AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
					" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "+
					" AND PEJ.ID_NEGERI = N.ID_NEGERI(+) " +
					" ";


					//open ct
					if(!carianTerperinci.equals("") && carianTerperinci != null)
					{
					sql += " AND (";

					sql += " UPPER(U.USER_LOGIN) LIKE UPPER('%"+carianTerperinci.trim()+"%') ";
					sql += " OR UPPER(S.NAMA_SEKSYEN) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(U.USER_NAME) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(N.NAMA_NEGERI) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(PEJ.NAMA_PEJABAT) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(R.DESCRIPTION) LIKE UPPER('%"+carianTerperinci+"%') ";
					sql += " OR UPPER(J.KETERANGAN) LIKE UPPER('%"+carianTerperinci+"%') ";

					sql += ") ";

					}

					if(!CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
					{

						if(!CT_NAMA.equals("") && CT_NAMA!=null)
						{
							sql += " AND (";
							sql += " UPPER(U.USER_LOGIN) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
							sql += " OR UPPER(U.USER_NAME) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
							sql += ") ";
						}
						if(!CT_ID_SEKSYEN.equals("") && CT_ID_SEKSYEN!=null)
						{
							sql += " AND (";
							sql += " UI.ID_SEKSYEN = '"+CT_ID_SEKSYEN+"' ";
							sql += ") ";
						}
						if(!CT_ID_NEGERI.equals("") && CT_ID_NEGERI!=null)
						{
							sql += " AND (";
							sql += " UI.ID_NEGERI = '"+CT_ID_NEGERI+"' ";
							sql += ") ";
						}
						if(!CT_ID_PEJABATJKPTG.equals("") && CT_ID_PEJABATJKPTG!=null)
						{
							sql += " AND (";
							sql += " UI.ID_PEJABATJKPTG = '"+CT_ID_PEJABATJKPTG+"' ";
							sql += ") ";
						}
						if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null)
						{
							sql += " AND (";
							sql += " UPPER(U.USER_ROLE) = '"+CT_ROLE_MAIN.toUpperCase()+"' OR CHECK_TR.TOTAL_ROLE_CARI>0 ";
							sql += ") ";
						}
						if(!CT_FLAG_AKTIF.equals("") && CT_FLAG_AKTIF!=null)
						{
							if(CT_FLAG_AKTIF.equals("1"))
							{
								sql += " AND (";
								sql += " UPPER(UI.FLAG_AKTIF) IS NULL OR UPPER(UI.FLAG_AKTIF) = '1' OR UPPER(UI.FLAG_AKTIF) = '' ";
								sql += ") ";
							}
							else if(CT_FLAG_AKTIF.equals("2"))
							{
								sql += " AND (";
								sql += " UPPER(UI.FLAG_AKTIF) = '2' ";
								sql += ") ";
							}
						}
						if(!CT_ID_JAWATAN.equals("") && CT_ID_JAWATAN!=null)
						{
							sql += " AND (";
							sql += " UI.ID_JAWATAN = '"+CT_ID_JAWATAN+"' ";
							sql += ") ";
						}
						if(!CT_LOGMASUK_MULA.equals("") && CT_LOGMASUK_MULA!=null)
						{
							sql += " AND (";
							sql += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') >=  TO_DATE('"+CT_LOGMASUK_MULA+"','DD/MM/YYYY') ";
							sql += ") ";
						}
						if(!CT_LOGMASUK_AKHIR.equals("") && CT_LOGMASUK_AKHIR!=null)
						{
							sql += " AND (";
							sql += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=  TO_DATE('"+CT_LOGMASUK_AKHIR+"','DD/MM/YYYY') ";
							sql += ") ";
						}
						if(!CT_STATUS_LOG.equals("") && CT_STATUS_LOG!=null)
						{
						//	" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
						//			" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

							if(CT_STATUS_LOG.equals("1"))
							{
								//SENYAP (60 Hari Dari Tarikh Terakhir Login)
								sql += " AND (";
								sql += " TRUNC(SYSDATE - TO_DATE(WL.LOG_DATE)) > 60 ";
								sql += ") ";
							}
							else if(CT_STATUS_LOG.equals("2"))
							{
								//KATALALUAN TELAH LUPUT (90 Hari Dari Tarikh Terakhir Menukar Katalaluan)
								sql += " AND (";
								sql += " TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) > 90 ";
								sql += ") ";
							}
						}
					}
					//close ct


					if(flag_HQ.equals("HQ"))
					{
						sql += " AND (PEJ.ID_NEGERI = 16 OR PEJ.ID_NEGERI IS NULL) ";
					}
					else if(flag_HQ.equals("Negeri"))
					{
						sql += " AND PEJ.ID_NEGERI != 16 ";
					}

				sql +=	" AND U.USER_ROLE = R.NAME(+) "+
					//" AND ((UPPER(U.USER_ROLE) LIKE '%KJP%' AND NVL(UR_TEMP.TOTAL_KJP,0) > 0) " +
					//" OR (UPPER(U.USER_ROLE) NOT LIKE '%KJP%' " +
					//" AND NVL(UR_TEMP.TOTAL_XKJP,0) > 0" +
					//" )) "+
					" AND U.USER_LOGIN = WL.USER_NAME(+) "+
					" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "+
					" GROUP BY U.USER_ID,UR_TEMP.TOTAL_ROLE, ";

				//open ct
				if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{
					sql += " CHECK_TR.TOTAL_ROLE_CARI, ";
				}
				//close ct

				sql += "U.USER_LOGIN,S.NAMA_SEKSYEN,U.USER_NAME,J.KETERANGAN,R.DESCRIPTION,UI.FLAG_AKTIF,U.LAST_CHANGEPASSWORD, " +
					" PEJ.NAMA_PEJABAT, N.NAMA_NEGERI "+
					" ORDER BY USER_LOGIN ";
			myLogger.info(" V3: SQL listPengunaInternal :"+ sql);

			rs = stmt.executeQuery(sql);
			senaraiPenggunaInternal = Collections.synchronizedList(new ArrayList());
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
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "" : rs.getString("TOTAL_ROLE"));
				h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
				h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
				h.put("FULLNAME",rs.getString("FULLNAME") == null ? "" : rs.getString("FULLNAME"));
				h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
				h.put("ROLE_UTAMA",rs.getString("ROLE_UTAMA") == null ? "" : rs.getString("ROLE_UTAMA"));
				h.put("WAKTU_AKHIR_LOGIN",rs.getString("WAKTU_AKHIR_LOGIN") == null ? "" : rs.getString("WAKTU_AKHIR_LOGIN"));
				h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
				h.put("NEGERI_PEJABAT",rs.getString("NEGERI_PEJABAT") == null ? "" : rs.getString("NEGERI_PEJABAT"));
				h.put("KEAKTIFAN",rs.getString("KEAKTIFAN") == null ? "" : rs.getString("KEAKTIFAN"));

				String display_note = "";
				//String last_login = rs.getString("LOG_DATE") == null ? "" : rs.getString("LOG_DATE");
				//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				//Date date_last = null;
				//DAYS_AFTERLASTLOGIN
				//DAYS_AFTERCHANGEPASS
				int DAYS_AFTERLASTLOGIN = 0;
				if(rs.getString("DAYS_AFTERLASTLOGIN")!=null)
				{
					//myLogger.info("111111111111111111111");
					DAYS_AFTERLASTLOGIN = rs.getInt("DAYS_AFTERLASTLOGIN");
					if(DAYS_AFTERLASTLOGIN>60)
					{
					display_note += "*Senyap<br>";
					}
				}
				//myLogger.info(" display_note last login : "+rs.getString("DAYS_AFTERLASTLOGIN"));
				//myLogger.info(" display_note atas : "+display_note);
				int DAYS_AFTERCHANGEPASS = 0;
				if(rs.getString("DAYS_AFTERCHANGEPASS")!=null)
				{
					//myLogger.info("22222222222222222222");
					DAYS_AFTERCHANGEPASS = rs.getInt("DAYS_AFTERCHANGEPASS");
					if(DAYS_AFTERCHANGEPASS>90)
					{
					display_note += "*Kata Laluan Luput<br>";
					}
				}
				//myLogger.info(" display_note bawah : "+display_note);
				//myLogger.info(" display_note password : "+rs.getString("DAYS_AFTERCHANGEPASS"));


				h.put("DISPLAY_NOTE",display_note);

				senaraiPenggunaInternal.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return senaraiPenggunaInternal;

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



	public boolean checkUSERLOGIN(HttpSession session, String USER_ID, String USER_LOGIN,String internalType) throws Exception {
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

				sql = " SELECT U.USER_ID, U.USER_LOGIN, U.USER_NAME FROM USERS U";
				if(USER_ID.equals(""))//check time nk insert
				{
					if(internalType.equals("HQ") || internalType.equals("Negeri"))
					{
						sql+=", USERS_INTERNAL UI " +
								" WHERE U.USER_ID = UI.USER_ID " +
								" AND UI.ID_SEKSYEN IS NOT NULL";
					}
					else if(internalType.equals("Online"))
					{
						sql+=", USERS_ONLINE UO WHERE U.USER_ID = UO.USER_ID ";
					}
					else if(internalType.equals("KJP"))
					{
						sql+=", USERS_INTERNAL UI , USERS_KEMENTERIAN UK " +
								" WHERE U.USER_ID = UI.USER_ID AND UI.USER_ID = UK.USER_ID ";
					}
					else if(internalType.equals("INT"))
					{
						sql+=", USERS_INTERNAL UI , USERS_INTEGRASI INT " +
								" WHERE U.USER_ID = UI.USER_ID AND UI.USER_ID = INT.USER_ID ";
					}
					else
					{
						sql += " WHERE U.USER_ID IS NOT NULL " ;
					}
				}
				else //time nak update check directly to table users
				{
					sql += " WHERE U.USER_ID IS NOT NULL " ;
				}




				if(!USER_ID.equals(""))
				{
					sql += " AND U.USER_ID != "+USER_ID+" " ;
				}
				if(!USER_LOGIN.trim().equals(""))
				{
					sql += " AND UPPER(U.USER_LOGIN) = '"+USER_LOGIN.toUpperCase()+"' " ;
				}

				myLogger.info("V3 - checkUSERLOGIN :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);

				String GET_USER_ID = "";
				while (rs.next()) {

					GET_USER_ID = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");
					if(!GET_USER_ID.equals(""))
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


	public Hashtable viewDataPenggunaInternal(HttpSession session, String user_id, String USER_LOGIN,String ID_PERMOHONAN) throws Exception {
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
				sql = " SELECT UI.FLAG_AKTIF, UI.ID_SEKSYEN, U.USER_ID, U.USER_LOGIN, UPPER(U.USER_NAME) AS USER_NAME, UI.EMEL, "+
						" UI.ID_JAWATAN, UPPER(J.KETERANGAN) AS JAWATAN, UI.ID_AGAMA, UPPER(AG.KETERANGAN) AS AGAMA, "+
						" UI.ID_BANGSA, UPPER(BG.KETERANGAN) AS BANGSA, UPPER(SY.NAMA_SEKSYEN) AS NAMA_BAHAGIAN, "+
						" UI.ALAMAT1, UI.ALAMAT2, UI.ALAMAT3, UI.POSKOD, UI.ID_NEGERI, UI.ID_BANDAR,  "+
						" UPPER(NEG.NAMA_NEGERI) AS NAMA_NEGERI, UPPER(BAN.KETERANGAN) AS NAMA_BANDAR, "+
						" UI.ID_PEJABATJKPTG, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, UPPER(PEJ.ALAMAT1) AS ALAMAT1_PEJ, "+
						" UPPER(PEJ.ALAMAT2) AS ALAMAT2_PEJ,UPPER(PEJ.ALAMAT3) AS ALAMAT3_PEJ,PEJ.POSKOD AS POSKOD_PEJ,  "+
						" PEJ.ID_BANDAR AS ID_BANDAR_PEJ, PEJ.ID_NEGERI AS ID_NEGERI_PEJ, "+
						" UPPER(BAN_PEJ.KETERANGAN) AS BANDAR_PEJ, UPPER(NEG_PEJ.NAMA_NEGERI) AS NEGERI_PEJ, "+
						" PEJ.NO_TEL AS NO_TEL_PEJ,PEJ.NO_FAX AS NO_FAX_PEJ, RL.NAME AS ROLE_MAIN, UPPER(RL.DESCRIPTION) AS ROLE_MAIN_DETAILS, "+
						" UPPER(U.USER_TYPE) AS JENIS_PENGGUNA, U.USER_TYPE AS ID_JENIS_PENGGUNA, UI.WAKTU_KERJA AS WAKTU_KERJA, "+
						" UI.FLAG_JAWATAN, TO_CHAR(UI.TARIKH_MULA_TEMPOH,'DD/MM/YYYY') AS TARIKH_MULA_TEMPOH, " +
						" TO_CHAR(UI.TARIKH_TAMAT_TEMPOH,'DD/MM/YYYY') AS TARIKH_TAMAT_TEMPOH "+
						" FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN J, TBLRUJAGAMA AG, TBLRUJBANGSA BG,  "+
						" TBLRUJSEKSYEN SY, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN, TBLRUJPEJABATJKPTG PEJ, "+
						" TBLRUJNEGERI NEG_PEJ, TBLRUJBANDAR BAN_PEJ, ROLE RL "+
						" WHERE U.USER_ID = UI.USER_ID "+
						" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) "+
						" AND UI.ID_AGAMA = AG.ID_AGAMA(+) "+
						" AND UI.ID_BANGSA = BG.ID_BANGSA(+) "+
						" AND UI.ID_SEKSYEN = SY.ID_SEKSYEN(+) "+
						" AND UI.ID_NEGERI = NEG.ID_NEGERI(+) "+
						" AND UI.ID_BANDAR = BAN.ID_BANDAR(+) "+
						" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "+
						" AND PEJ.ID_BANDAR = BAN_PEJ.ID_BANDAR(+) "+
						" AND PEJ.ID_NEGERI = NEG_PEJ.ID_NEGERI(+) "+
						" AND U.USER_ROLE = RL.NAME(+) ";

				if(!USER_LOGIN.equals(""))
				{
				//sql += " AND UPPER(U.USER_LOGIN) = '"+USER_LOGIN.toUpperCase()+"' ";
					sql += " AND U.USER_LOGIN = '"+USER_LOGIN+"' ";
				}
				if(!user_id.equals(""))
				{
				sql += " AND U.USER_ID = '"+user_id+"' ";
				}
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

					//TAMBAH COLUMN
					h.put("WAKTU_KERJA",rs.getString("WAKTU_KERJA") == null ? "" : rs.getString("WAKTU_KERJA"));

					//27/3/18 ADD COLUMN

					h.put("FLAG_JAWATAN",rs.getString("FLAG_JAWATAN") == null ? "" : rs.getString("FLAG_JAWATAN"));
					h.put("TARIKH_MULA_TEMPOH",rs.getString("TARIKH_MULA_TEMPOH") == null ? "" : rs.getString("TARIKH_MULA_TEMPOH"));
					h.put("TARIKH_TAMAT_TEMPOH",rs.getString("TARIKH_TAMAT_TEMPOH") == null ? "" : rs.getString("TARIKH_TAMAT_TEMPOH"));

					h.put("PEGAWAIPENYELIA","");
					h.put("ID_TABLEUSERS","");
					h.put("ID_PENYEMAK","");
					h.put("PENYEMAK","");
					h.put("FLAG_STATUS","");
					h.put("ID_PERMOHONAN","");
					h.put("NOKP1","");
					h.put("NOKP2","");
					h.put("NOKP3","");
					h.put("CATATAN_PIP","");
					h.put("TARIKH_PERMOHONAN_PIP","");
					h.put("TARIKH_KEPUTUSAN_PIP","");
					h.put("JAWATAN_PIP","");
					h.put("BAHAGIAN_PIP","");
					h.put("UNIT_PIP","");
					h.put("NEGERI_PIP","");
					h.put("NOKP1_PIP","");
					h.put("NOKP2_PIP","");
					h.put("NOKP3_PIP","");
					h.put("USER_NAME_PIP","");
					h.put("EMEL_PIP","");
					h.put("NAMA_DOC","");
					h.put("JENIS_MIME","");
				}
			}
			else
			{
				h.put("NAMA_DOC","");
				h.put("JENIS_MIME","");
				h.put("NOKP1_PIP","");
				h.put("NOKP2_PIP","");
				h.put("NOKP3_PIP","");
				h.put("USER_NAME_PIP","");
				h.put("EMEL_PIP","");
				h.put("TARIKH_PERMOHONAN_PIP","");
				h.put("TARIKH_KEPUTUSAN_PIP","");
				h.put("JAWATAN_PIP","");
				h.put("BAHAGIAN_PIP","");
				h.put("UNIT_PIP","");
				h.put("NEGERI_PIP","");
				h.put("CATATAN_PIP","");
				h.put("PEGAWAIPENYELIA","");
				h.put("ID_TABLEUSERS","");
				h.put("ID_PENYEMAK","");
				h.put("PENYEMAK","");
				h.put("FLAG_STATUS","");
				h.put("ID_PERMOHONAN","");
				h.put("NOKP1","");
				h.put("NOKP2","");
				h.put("NOKP3","");
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
				h.put("WAKTU_KERJA","");

				h.put("FLAG_JAWATAN","");
				h.put("TARIKH_MULA_TEMPOH","");
				h.put("TARIKH_TAMAT_TEMPOH","");

				myLogger.info(" ID_PERMOHONAN : "+ID_PERMOHONAN);
				Hashtable PID = null;
				if(!ID_PERMOHONAN.equals(""))
				{
					PID = viewDataPenggunaMOHON(session, ID_PERMOHONAN);
				}

				if(PID!=null)
				{
					h.put("PASSWORD","P@$$w0rd2018");
					h.put("USER_LOGIN",(String)PID.get("NOKP"));
					h.put("NOKP1",((String)PID.get("NOKP")).substring(0, 6));
					h.put("NOKP2",((String)PID.get("NOKP")).substring(6, 8));
					h.put("NOKP3",((String)PID.get("NOKP")).substring(8, 12));
					h.put("USER_NAME",(String)PID.get("NAMA"));
					h.put("EMEL",(String)PID.get("EMEL"));
					h.put("ID_JAWATAN",(String)PID.get("ID_JAWATAN"));
					h.put("ID_SEKSYEN",(String)PID.get("ID_SEKSYEN"));
					h.put("ID_PEJABATJKPTG",(String)PID.get("ID_PEJABATJKPTG"));
					h.put("ID_NEGERI",(String)PID.get("ID_NEGERI"));
					h.put("ID_PERMOHONAN",ID_PERMOHONAN);
					h.put("FLAG_STATUS",(String)PID.get("FLAG_STATUS"));
					h.put("PEGAWAIPENYELIA",(String)PID.get("PEGAWAIPENYELIA"));
					h.put("ID_TABLEUSERS",(String)PID.get("ID_TABLEUSERS"));
					h.put("ID_PENYEMAK",(String)PID.get("ID_PENYEMAK"));
					h.put("PENYEMAK",(String)PID.get("PENYEMAK"));
					h.put("CATATAN_PIP",(String)PID.get("CATATAN"));
					h.put("TARIKH_PERMOHONAN_PIP",(String)PID.get("TARIKH_PENDAFTARAN"));
					h.put("TARIKH_KEPUTUSAN_PIP",(String)PID.get("TARIKH_KEPUTUSAN"));
					h.put("JAWATAN_PIP",(String)PID.get("JAWATAN"));
					h.put("BAHAGIAN_PIP",(String)PID.get("BAHAGIAN"));
					h.put("UNIT_PIP",(String)PID.get("NAMA_UNIT"));
					h.put("NEGERI_PIP",(String)PID.get("NEGERI"));
					h.put("NOKP1_PIP",((String)PID.get("NOKP")).substring(0, 6));
					h.put("NOKP2_PIP",((String)PID.get("NOKP")).substring(6, 8));
					h.put("NOKP3_PIP",((String)PID.get("NOKP")).substring(8, 12));
					h.put("USER_NAME_PIP",(String)PID.get("NAMA"));
					h.put("EMEL_PIP",(String)PID.get("EMEL"));
					h.put("NAMA_DOC",(String)PID.get("NAMA_DOC"));
					h.put("JENIS_MIME",(String)PID.get("JENIS_MIME"));

				}

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
				myLogger.info("GET_COUNT : "+sql);
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

	public int getHISTORYCount(HttpSession session, String user_id) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			int cnt = 0;

			sql = " SELECT COUNT(*) AS CNT FROM TBLSEJARAHPENGGUNAUTAMA A, USERS U WHERE A.USER_ID = U.USER_ID AND A.USER_ID = "+user_id+" ";
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
			String ROLE_MAIN = getParam("ROLE_MAIN_"+internalType+USER_ID);
			String FLAG_AKTIF = getParam("FLAG_AKTIF_"+internalType+USER_ID);

			//tambah column
			String FLAG_JAWATAN = getParam("FLAG_JAWATAN_"+internalType+USER_ID);
			String TARIKH_MULA_TEMPOH = getParam("TARIKH_MULA_TEMPOH_"+internalType+USER_ID);
			String TARIKH_TAMAT_TEMPOH = getParam("TARIKH_TAMAT_TEMPOH_"+internalType+USER_ID);

			String USER_TYPE = "";
			if(internalType.equals("HQ") || internalType.equals("Negeri"))
			{
				USER_TYPE = "internal";
			}

			String WAKTU_KERJA = getParam("WAKTU_KERJA_"+internalType+USER_ID);

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
					r.add("USER_ROLE", ROLE_MAIN);
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

			//r.add("DATE_REGISTERED", r.unquote("sysdate"));
			//r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
			r.add("USER_TYPE", USER_TYPE);

			myLogger.info("V3 : flag_operasi : "+flag_operasi);
			if(flag_operasi.equals("INSERT"))
			{
				myLogger.info("V3 : GET_USER_ID_EXIST : "+GET_USER_ID_EXIST);
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					r.add("DATE_REGISTERED", r.unquote("sysdate"));
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
			myLogger.info("V3 : ********** sql : "+sql);

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
					else
					{
						r.add("USER_ID", USER_ID_INSERT);
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
			r.add("FLAG_AKTIF", FLAG_AKTIF);
			r.add("WAKTU_KERJA", WAKTU_KERJA);

			if(flag_operasi.equals("INSERT"))
			{

				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));

					//TAMBAH COLUMN
					r.add("FLAG_JAWATAN", FLAG_JAWATAN);
					r.add("TARIKH_MULA_TEMPOH", TARIKH_MULA_TEMPOH);
					r.add("TARIKH_TAMAT_TEMPOH", TARIKH_TAMAT_TEMPOH);

					sql = r.getSQLInsert("USERS_INTERNAL");
					myLogger.info("V3 : INSERT USERS INTERNAL : "+sql);
				}
				else
				{
					if(!checkUsersInternal(session,GET_USER_ID_EXIST).equals(""))
					{
						r.add("ID_KEMASKINI", USER_ID_SYSTEM);
						r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

						//TAMBAH COLUMN
						r.add("FLAG_JAWATAN", FLAG_JAWATAN);
						r.add("TARIKH_MULA_TEMPOH", TARIKH_MULA_TEMPOH);
						r.add("TARIKH_TAMAT_TEMPOH", TARIKH_TAMAT_TEMPOH);

						sql = r.getSQLUpdate("USERS_INTERNAL");
						myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
					}
					else
					{
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));

						//TAMBAH COLUMN
						r.add("FLAG_JAWATAN", FLAG_JAWATAN);
						r.add("TARIKH_MULA_TEMPOH", TARIKH_MULA_TEMPOH);
						r.add("TARIKH_TAMAT_TEMPOH", TARIKH_TAMAT_TEMPOH);

						sql = r.getSQLInsert("USERS_INTERNAL");
						myLogger.info("V3 : INSERT USERS INTERNAL : "+sql);
					}
				}

			}
			else
			{
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));

				//TAMBAH COLUMN
				r.add("FLAG_JAWATAN", FLAG_JAWATAN);
				r.add("TARIKH_MULA_TEMPOH", TARIKH_MULA_TEMPOH);
				r.add("TARIKH_TAMAT_TEMPOH", TARIKH_TAMAT_TEMPOH);

				sql = r.getSQLUpdate("USERS_INTERNAL");
				myLogger.info("V3 : UPDATE USERS INTERNAL : "+sql);
			}
			myLogger.info("V3 : sql ********** 2 : "+sql);
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


			//String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT_"+internalType+USER_ID);

			String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT_"+internalType+USER_ID);
			myLogger.info("ID_JENISPEJABAT -- "+ID_JENISPEJABAT);

			/*String seperator = "/";

			String ID_JENISPEJABAT = CT_ID_JENISPEJABAT2.substring(0, CT_ID_JENISPEJABAT2.indexOf(seperator)).trim();
			myLogger.info("ID_JENISPEJABAT  -- "+ID_JENISPEJABAT);*/

			String ID_NEGERI = getParam("ID_NEGERI_"+internalType+USER_ID);
			String ID_DAERAH = getParam("ID_DAERAH_"+internalType+USER_ID);
			String ID_PEJABAT = getParam("ID_PEJABAT_"+internalType+USER_ID);
			String FLAG_AKTIF = getParam("FLAG_AKTIF_"+internalType+USER_ID);

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
				r.add("USER_ROLE", ROLE_MAIN);
			}

			r.add("USER_LOGIN", USER_LOGIN);
			if(!PASSWORD.equals(""))
			{
				r.add("USER_PASSWORD", PasswordService.encrypt(PASSWORD));
				r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
			}
			r.add("USER_NAME", USER_NAME.toUpperCase());

			//r.add("DATE_REGISTERED", r.unquote("sysdate"));
			//r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));


			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					r.add("DATE_REGISTERED", r.unquote("sysdate"));
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
			r.add("FLAG_AKTIF", FLAG_AKTIF);


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


	public String simpanPenggunaKJP(HttpSession session,String USER_ID, String internalType) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		myLogger.info("KJP USER_ID : "+USER_ID);
		String flag_operasi = "INSERT";
		if(!USER_ID.equals(""))
		{
			flag_operasi = "UPDATE";
		}
		long USER_ID_INSERT = 0;
		String sendEmel = "";

		myLogger.info("KJP flag_operasi : "+flag_operasi);
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			String GET_USER_ID_EXIST = getParam("GET_USER_ID_EXIST_"+internalType+USER_ID);
			myLogger.info("KJP GET_USER_ID_EXIST : "+GET_USER_ID_EXIST);
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
				r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
			}
			r.add("USER_NAME", USER_NAME.toUpperCase());

			//r.add("DATE_REGISTERED", r.unquote("sysdate"));
			//r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));


			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					r.add("DATE_REGISTERED", r.unquote("sysdate"));
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
			r.add("FLAG_AKTIF", FLAG_AKTIF);


			if(flag_operasi.equals("INSERT"))
			{
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				sql = r.getSQLInsert("USERS_KEMENTERIAN");
				sendEmel = "Y";
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

			if(sendEmel.equals("Y"))
			{
			//tambah hantar emel kpd bpict (kjp)
			//hantarEmelStaff(session,USER_ID_INSERT+"",1);
			hantarEmelDaftarKJP(session,USER_ID_INSERT+"",1);
			}



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
			else
			{
				TARIKH_LAHIR = "''";
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
			//r.add("DATE_REGISTERED", r.unquote("sysdate"));
			//r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));

			if(flag_operasi.equals("INSERT"))
			{
				if(GET_USER_ID_EXIST.equals(""))
				{
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					r.add("DATE_REGISTERED", r.unquote("sysdate"));
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
			//r.add("TARIKH_LAHIR", r.unquote(TARIKH_LAHIR));




			//String TL = "to_date('" + TARIKH_LAHIR + "','dd/MM/yyyy')";
			r.add("TARIKH_LAHIR", r.unquote(TARIKH_LAHIR));


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

		}/*
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
		}*/finally {
			if (db != null)
				db.close();
		}
		return returnUSERID;
	}


	public int simpanAdditionalRoles(HttpSession session,String USER_ID, String internalType, String USER_LOGIN, String[] TEMP_GROUP_CHECKLIST) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		int total_role_update = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "DELETE FROM USER_ROLE WHERE USER_ID = '"+USER_LOGIN+"'";
			if(internalType.equals("HQ") || internalType.equals("Negeri"))
			{
				sql += " AND ROLE_ID NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
			}
			else if(internalType.equals("Online"))
			{
				sql += " AND ROLE_ID IN ('online','ppt-online-user','php-online-user','ppk-online-user')";
			}
			else if(internalType.equals("KJP"))
			{
				sql += " AND ROLE_ID IN ('online_kjpagensi','online_kjp')";
			}
			myLogger.info("V3 : SQL-CLEAR USER ROLE : "+sql);
			stmt.executeUpdate(sql);



			String[] CHECKLIST_ROLES = null;
			for (String GROUP_KOD : TEMP_GROUP_CHECKLIST) {
				myLogger.info(" GROUP_KOD : "+GROUP_KOD);
				CHECKLIST_ROLES = request.getParameterValues("CHECKLIST_"+internalType+USER_ID+GROUP_KOD);
				myLogger.info(" CHECKLIST_ROLES : "+CHECKLIST_ROLES);
				if(CHECKLIST_ROLES!=null)
				{
					for (String ROLE_ID : CHECKLIST_ROLES) {
						myLogger.info(GROUP_KOD+" --- ROLE_ID "+ROLE_ID);
						sql = "";
						r.clear();
						r.add("USER_ID", USER_LOGIN);
						r.add("ROLE_ID", ROLE_ID);
						sql = r.getSQLInsert("USER_ROLE");
						myLogger.info("V3 : INSERT USER_ROLE : "+sql);
						stmt.executeUpdate(sql);
						total_role_update++;
					}
				}
				CHECKLIST_ROLES = null;
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
		return total_role_update;
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

					if((rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI")).equals("Individu")
							|| (rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI")).equals(""))
					{
						if(rs.getString("NO_PENGENALAN")!=null && !rs.getString("NO_PENGENALAN").equals(""))
						{
							if(rs.getString("NO_PENGENALAN").length()==12)
							{
								h.put("NO_PENGENALAN1", rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN").substring(0, 6));
								h.put("NO_PENGENALAN2", rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN").substring(6, 8));
								h.put("NO_PENGENALAN3", rs.getString("NO_PENGENALAN") == null ? "" : rs
										.getString("NO_PENGENALAN").substring(8, 12));
							}
							else
							{
								h.put("NO_PENGENALAN1","");
								h.put("NO_PENGENALAN2","");
								h.put("NO_PENGENALAN3","");
							}
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
	public List listPengunaOnline(HttpSession session,String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPenggunaOnline = null;
		String sql = "";

		try {
			db = new Db();
			stmt = db.getStatement();
			sql = queryUserOnline(session,"", carianTerperinci, "");
			myLogger.info(" V3: SQL listPengunaOnline :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiPenggunaOnline = Collections.synchronizedList(new ArrayList());
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
				h.put("NAMA_PENUH",rs.getString("NAMA_PENUH") == null ? "" : rs.getString("NAMA_PENUH"));
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
				h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP").toUpperCase());
				h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL").toUpperCase());
				h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX").toUpperCase());
				h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("WAKTU_AKHIR_LOGIN",rs.getString("WAKTU_AKHIR_LOGIN") == null ? "" : rs.getString("WAKTU_AKHIR_LOGIN"));
				h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
				h.put("KEAKTIFAN",rs.getString("KEAKTIFAN") == null ? "" : rs.getString("KEAKTIFAN"));

				if((rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI")).equals("Individu")
						|| (rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI")).equals(""))
				{
					if(rs.getString("NO_PENGENALAN") != null && rs.getString("NO_PENGENALAN").trim().length()==12)
					{
						/*
						h.put("NO_PENGENALAN1", rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN").trim().substring(0, 6));
						h.put("NO_PENGENALAN2", rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN").trim().substring(6, 8));
						h.put("NO_PENGENALAN3", rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN").trim().substring(8, 12));
						*/

						if(rs.getString("NO_PENGENALAN").length()==12)
						{
							h.put("NO_PENGENALAN1", rs.getString("NO_PENGENALAN") == null ? "" : rs
									.getString("NO_PENGENALAN").substring(0, 6));
							h.put("NO_PENGENALAN2", rs.getString("NO_PENGENALAN") == null ? "" : rs
									.getString("NO_PENGENALAN").substring(6, 8));
							h.put("NO_PENGENALAN3", rs.getString("NO_PENGENALAN") == null ? "" : rs
									.getString("NO_PENGENALAN").substring(8, 12));
						}
						else
						{
							h.put("NO_PENGENALAN1","");
							h.put("NO_PENGENALAN2","");
							h.put("NO_PENGENALAN3","");
						}
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

				String display_note = "";
				int DAYS_AFTERLASTLOGIN = 0;
				if(rs.getString("DAYS_AFTERLASTLOGIN")!=null)
				{
					DAYS_AFTERLASTLOGIN = rs.getInt("DAYS_AFTERLASTLOGIN");
					if(DAYS_AFTERLASTLOGIN>60)
					{
					display_note += "*Senyap<br>";
					}
				}

				int DAYS_AFTERCHANGEPASS = 0;
				if(rs.getString("DAYS_AFTERCHANGEPASS")!=null)
				{
					DAYS_AFTERCHANGEPASS = rs.getInt("DAYS_AFTERCHANGEPASS");
					if(DAYS_AFTERCHANGEPASS>90)
					{
					display_note += "*Kata Laluan Luput<br>";
					}
				}



				h.put("DISPLAY_NOTE",display_note);

				senaraiPenggunaOnline.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return senaraiPenggunaOnline;

	}





	public String queryUserMOHON(HttpSession session,String ID_PERMOHONAN, String carianTerperinci) throws Exception {

		//open ct
				String flag_HQ = "MOHON";
				String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_"+flag_HQ);
				String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_"+flag_HQ);
				String CT_NAMA = getParam("CT_NAMA_"+flag_HQ);
				String CT_ID_SEKSYEN = getParam("CT_ID_SEKSYEN_"+flag_HQ);
				String CT_ID_NEGERI = getParam("CT_ID_NEGERI_"+flag_HQ);
				String CT_ID_PEJABATJKPTG = getParam("CT_ID_PEJABATJKPTG_"+flag_HQ);
				//String CT_ROLE_MAIN = getParam("CT_ROLE_MAIN_"+flag_HQ);
				//String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_"+flag_HQ);
				String CT_ID_JAWATAN = getParam("CT_ID_JAWATAN_"+flag_HQ);
				String CT_TARIKHPENDAFTARAN_MULA = getParam("CT_TARIKHPENDAFTARAN_MULA_"+flag_HQ);
				String CT_TARIKHPENDAFTARAN_AKHIR = getParam("CT_TARIKHPENDAFTARAN_AKHIR_"+flag_HQ);
				String CT_STATUSPENDAFTARAN = getParam("CT_STATUSPENDAFTARAN_"+flag_HQ);


				this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
				this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
				this.context.put("CT_NAMA", CT_NAMA);
				this.context.put("CT_ID_SEKSYEN", CT_ID_SEKSYEN);
				this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
				this.context.put("CT_ID_PEJABATJKPTG", CT_ID_PEJABATJKPTG);
				//this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
				//this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
				this.context.put("CT_ID_JAWATAN", CT_ID_JAWATAN);
				//this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
				this.context.put("CT_TARIKHPENDAFTARAN_MULA", CT_TARIKHPENDAFTARAN_MULA);
				this.context.put("CT_TARIKHPENDAFTARAN_AKHIR", CT_TARIKHPENDAFTARAN_AKHIR);
				this.context.put("CT_STATUSPENDAFTARAN", CT_STATUSPENDAFTARAN);
				//close ct

		String query = " SELECT PP.CONTENT, PP.NAMA_DOC, PP.JENIS_MIME, PP.PENYEMAK,PP.PEGAWAIPENYELIA,PP.ID_TABLEUSERS,PP.ID_PENYEMAK,PP.ID_PERMOHONAN, PP.NAMA, PP.TARIKH_PENDAFTARAN, PP.TARIKH_KEPUTUSAN, PP.ID_JAWATAN, PP.ID_PEJABATJKPTG, PP.ID_NEGERI, "+
				" PP.ID_SEKSYEN,PP.CATATAN,PP.NOKP,PP.EMEL,PP.NO_HP,PP.FLAG_STATUS,PP.NAMA_UNIT, PP.NEGERI,PP.JAWATAN,PP.BAHAGIAN,PP.STATUS, PP.ID_KLASIFIKASI, PP.GRED, PP.ID_GRED "+
				" FROM "+
				" ( "+
				" SELECT P.CONTENT, P.NAMA_DOC, P.JENIS_MIME, UPPER(PPTM.USER_NAME) AS PENYEMAK, UPPER(P.PEGAWAIPENYELIA) AS PEGAWAIPENYELIA, P.ID_TABLEUSERS,P.ID_PENYEMAK,P.USER_ID AS ID_PERMOHONAN, UPPER(P.USER_NAME) AS NAMA, TO_CHAR(P.TARIKH_PENDAFTARAN,'DD/MM/YYYY') AS TARIKH_PENDAFTARAN,  "+
				" TO_CHAR(P.TARIKH_KEPUTUSAN,'DD/MM/YYYY') AS TARIKH_KEPUTUSAN, P.ID_JAWATAN, "+
				" P.ID_PEJABATJKPTG, P.ID_NEGERI, P.ID_SEKSYEN, P.CATATAN, P.NOKP, P.EMEL, P.NO_HP, P.FLAG_STATUS, UPPER(PEJ.NAMA_PEJABAT) AS NAMA_UNIT,  "+
				" UPPER(NEG.NAMA_NEGERI) AS NEGERI,UPPER(J.KETERANGAN) AS JAWATAN, UPPER(S.NAMA_SEKSYEN) AS BAHAGIAN, "+
				" (CASE WHEN P.FLAG_STATUS = 1 THEN 'PERMOHONAN BARU' "+
				" WHEN P.FLAG_STATUS = 2 THEN 'DITOLAK' "+
				" WHEN P.FLAG_STATUS = 3 THEN 'DITERIMA' ELSE '' END) AS STATUS, P.ID_KLASIFIKASI, G.ID_GRED, G.NAMA_GRED AS GRED "+
				" FROM USERS PPTM,PERMOHONANIDPENGGUNA P, TBLRUJPEJABATJKPTG PEJ, TBLRUJNEGERI NEG, TBLRUJJAWATAN J, TBLRUJSEKSYEN S, TBLRUJGRED G "+
				" WHERE P.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) AND P.ID_NEGERI = NEG.ID_NEGERI "+
				" AND P.ID_PENYEMAK = PPTM.USER_ID(+) AND P.ID_JAWATAN = J.ID_JAWATAN(+) AND P.ID_SEKSYEN = S.ID_SEKSYEN(+) "+
				" AND P.ID_GRED = G.ID_GRED(+) "+
				" ORDER BY P.FLAG_STATUS ASC, P.TARIKH_PENDAFTARAN DESC, P.ID_JAWATAN ASC, P.USER_NAME ASC "+
				" ) PP WHERE PP.ID_PERMOHONAN IS NOT NULL ";

				if(!ID_PERMOHONAN.trim().equals(""))
				{
					query += " AND PP.ID_PERMOHONAN = '"+ID_PERMOHONAN.trim()+"' ";
				}



				if(!CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{

					if(!CT_NAMA.equals("") && CT_NAMA!=null)
					{
						query += " AND (";
						query += " UPPER(PP.NAMA) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
						query += " OR UPPER(PP.NOKP) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
						query += ") ";
					}
					if(!CT_ID_SEKSYEN.equals("") && CT_ID_SEKSYEN!=null)
					{
						query += " AND (";
						query += " PP.ID_SEKSYEN = '"+CT_ID_SEKSYEN+"' ";
						query += ") ";
					}
					if(!CT_ID_NEGERI.equals("") && CT_ID_NEGERI!=null)
					{
						query += " AND (";
						query += " PP.ID_NEGERI = '"+CT_ID_NEGERI+"' ";
						query += ") ";
					}
					if(!CT_ID_PEJABATJKPTG.equals("") && CT_ID_PEJABATJKPTG!=null)
					{
						query += " AND (";
						query += " PP.ID_PEJABATJKPTG = '"+CT_ID_PEJABATJKPTG+"' ";
						query += ") ";
					}
					if(!CT_ID_JAWATAN.equals("") && CT_ID_JAWATAN!=null)
					{
						query += " AND (";
						query += " PP.ID_JAWATAN = '"+CT_ID_JAWATAN+"' ";
						query += ") ";
					}
					if(!CT_TARIKHPENDAFTARAN_MULA.equals("") && CT_TARIKHPENDAFTARAN_MULA!=null)
					{
						query += " AND (";
						query += "  TO_DATE(PP.TARIKH_PENDAFTARAN, 'DD/MM/YYYY') >=  TO_DATE('"+CT_TARIKHPENDAFTARAN_MULA+"','DD/MM/YYYY') ";
						query += ") ";
					}
					if(!CT_TARIKHPENDAFTARAN_AKHIR.equals("") && CT_TARIKHPENDAFTARAN_AKHIR!=null)
					{
						query += " AND (";
						query += "  TO_DATE(PP.TARIKH_PENDAFTARAN, 'DD/MM/YYYY') <=  TO_DATE('"+CT_TARIKHPENDAFTARAN_AKHIR+"','DD/MM/YYYY') ";
						query += ") ";
					}
					if(!CT_STATUSPENDAFTARAN.equals("") && CT_STATUSPENDAFTARAN!=null)
					{
						query += " AND (";
						query += " PP.FLAG_STATUS = '"+CT_STATUSPENDAFTARAN+"' ";
						query += ") ";
					}

				}
				//close ct



				if(!carianTerperinci.equals(""))
				{
					query += " AND (" +
							" PP.NOKP LIKE '%"+carianTerperinci+"%' " +
							" OR PP.EMEL LIKE '%"+carianTerperinci+"%' " +
							" OR UPPER(PP.NAMA) LIKE UPPER('%"+carianTerperinci+"%') " +
							" OR UPPER(PP.NAMA_UNIT) LIKE UPPER('%"+carianTerperinci+"%') " +
							" OR UPPER(PP.NEGERI) LIKE UPPER('%"+carianTerperinci+"%') " +
							//" OR UPPER(PP.JAWATAN) LIKE UPPER('%"+carianTerperinci+"%') " +
							" OR UPPER(PP.BAHAGIAN) LIKE UPPER('%"+carianTerperinci+"%') " +
							" OR UPPER(PP.STATUS) LIKE UPPER('%"+carianTerperinci+"%') " +
											") ";
				}
				else if(carianTerperinci.equals("") && ID_PERMOHONAN.equals("") && (CT_FLAGTEPERINCI_CARIAN.equals("N") || CT_FLAGTEPERINCI_CARIAN.equals("") || CT_FLAGTEPERINCI_CARIAN == null))
				{
					query += " AND PP.FLAG_STATUS = 1 ";
				}


				return query;
	}



	public Hashtable viewDataPenggunaMOHON(HttpSession session, String ID_PERMOHONAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();

			if(!ID_PERMOHONAN.equals("") || !ID_PERMOHONAN.equals(""))
			{
				sql = queryUserMOHON(session,ID_PERMOHONAN, "");
				myLogger.info(" viewDataPenggunaMOHON :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					h.put("ID_PENYEMAK",rs.getString("ID_PENYEMAK") == null ? "" : rs.getString("ID_PENYEMAK"));
					h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
					h.put("TARIKH_PENDAFTARAN",rs.getString("TARIKH_PENDAFTARAN") == null ? "" : rs.getString("TARIKH_PENDAFTARAN"));
					h.put("TARIKH_KEPUTUSAN",rs.getString("TARIKH_KEPUTUSAN") == null ? "" : rs.getString("TARIKH_KEPUTUSAN"));
					h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
					h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
					h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
					h.put("NOKP",rs.getString("NOKP") == null ? "" : rs.getString("NOKP"));
					h.put("NOKP1",rs.getString("NOKP").substring(0, 6));
					h.put("NOKP2",rs.getString("NOKP").substring(6, 8));
					h.put("NOKP3",rs.getString("NOKP").substring(8, 12));
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
					h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
					h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
					h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
					h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
					h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
					h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					h.put("PEGAWAIPENYELIA",rs.getString("PEGAWAIPENYELIA") == null ? "" : rs.getString("PEGAWAIPENYELIA"));
					h.put("ID_TABLEUSERS",rs.getString("ID_TABLEUSERS") == null ? "" : rs.getString("ID_TABLEUSERS"));
					h.put("ID_PENYEMAK",rs.getString("ID_PENYEMAK") == null ? "" : rs.getString("ID_PENYEMAK"));
					h.put("PENYEMAK",rs.getString("PENYEMAK") == null ? "" : rs.getString("PENYEMAK"));
					h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));
					h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));
					h.put("GRED",rs.getString("GRED") == null ? "" : rs.getString("GRED"));
					h.put("ID_GRED",rs.getString("ID_GRED") == null ? "" : rs.getString("ID_GRED"));
					h.put("ID_KLASIFIKASI",rs.getString("ID_KLASIFIKASI") == null ? "" : rs.getString("ID_KLASIFIKASI"));

				}
			}
			else
			{
				h.put("NAMA_DOC","");
				h.put("JENIS_MIME","");
				h.put("PENYEMAK","");
				h.put("PEGAWAIPENYELIA","");
				h.put("ID_TABLEUSERS","");
				h.put("ID_PENYEMAK","");
				h.put("ID_PERMOHONAN","");
				h.put("NAMA","");
				h.put("TARIKH_PENDAFTARAN","");
				h.put("TARIKH_KEPUTUSAN","");
				h.put("ID_JAWATAN","");
				h.put("ID_PEJABATJKPTG","");
				h.put("ID_NEGERI","");
				h.put("ID_SEKSYEN","");
				h.put("CATATAN","");
				h.put("NOKP","");
				h.put("NOKP1","");
				h.put("NOKP2","");
				h.put("NOKP3","");
				h.put("EMEL","");
				h.put("NO_HP","");
				h.put("FLAG_STATUS","");
				h.put("NAMA_UNIT","");
				h.put("NEGERI","");
				h.put("JAWATAN","");
				h.put("BAHAGIAN","");
				h.put("STATUS","");
				h.put("GRED","");
				h.put("ID_GRED","");
				h.put("ID_KLASIFIKASI","");

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

	public void savePenggunaMOHON(HttpSession session,String ID_PERMOHONAN, String internalType, String USER_ID_CREATED) throws Exception {
		Connection conn = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		Db db = null;
		String sql = "";
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String NOKP1 = getParam("NOKP1_"+internalType+ID_PERMOHONAN);
			String NOKP2 = getParam("NOKP2_"+internalType+ID_PERMOHONAN);
			String NOKP3 = getParam("NOKP3_"+internalType+ID_PERMOHONAN);
			String NAMA = getParam("NAMA_"+internalType+ID_PERMOHONAN);
			String ID_JAWATAN = getParam("ID_JAWATAN_"+internalType+ID_PERMOHONAN);
			String ID_BAHAGIAN = getParam("ID_SEKSYEN_"+internalType+ID_PERMOHONAN);
			String ID_NEGERI = getParam("ID_NEGERI_"+internalType+ID_PERMOHONAN);
			String ID_PEJABAT = getParam("ID_PEJABATJKPTG_"+internalType+ID_PERMOHONAN);
			String EMELPEMOHON = getParam("EMELPEMOHON_"+internalType+ID_PERMOHONAN);
			String PENYELIA = getParam("PEGAWAIPENYELIA_"+internalType+ID_PERMOHONAN);
			String STATUS_PID = getParam("STATUS_PID_"+internalType+ID_PERMOHONAN);
			String NO_HP = getParam("NO_HP_"+internalType+ID_PERMOHONAN);
			String CATATAN = getParam("CATATAN_"+internalType+ID_PERMOHONAN);
			String ID_GRED = getParam("ID_GRED_"+internalType+ID_PERMOHONAN);

			boolean sendEmel = false;

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("USER_ID", ID_PERMOHONAN);
			r.add("NOKP", NOKP1+NOKP2+NOKP3);
			r.add("USER_NAME", NAMA.toUpperCase());
			r.add("ID_JAWATAN", ID_JAWATAN);
			r.add("ID_SEKSYEN", ID_BAHAGIAN);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("ID_PEJABATJKPTG", ID_PEJABAT);
			r.add("EMEL", EMELPEMOHON);
			r.add("NO_HP", NO_HP);
			r.add("PEGAWAIPENYELIA", PENYELIA.toUpperCase());
			r.add("TARIKH_PENDAFTARAN", r.unquote("sysdate"));
			//r.add("TARIKH_KEPUTUSAN", "");
			r.add("FLAG_STATUS", STATUS_PID);//1-PERMOHONAN BARU 2-DITOLAK 3-DITERIMA
			r.add("CATATAN", CATATAN.toUpperCase());//1-PERMOHONAN BARU 2-DITOLAK 3-DITERIMA
			r.add("ID_PENYEMAK",USER_ID_SYSTEM);

			r.add("ID_GRED", ID_GRED);

			if(STATUS_PID.equals("2") && USER_ID_CREATED.equals(""))
			{
				r.add("TARIKH_KEPUTUSAN", r.unquote("sysdate"));
				sendEmel = true;

			}
			else if(STATUS_PID.equals("3") && !USER_ID_CREATED.equals(""))
			{
				r.add("TARIKH_KEPUTUSAN", r.unquote("sysdate"));
				r.add("ID_TABLEUSERS", USER_ID_CREATED);
				sendEmel = true;

			}

			sql = r.getSQLUpdate("PERMOHONANIDPENGGUNA");
			myLogger.info("UPDATE PERMOHONANIDPENGGUNA : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();
			if(sendEmel==true)
			{
				hantarEmel(session,ID_PERMOHONAN);
			}


		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}

	public void savePenggunaMOHON_internal(HttpSession session,String ID_PERMOHONAN, String STATUS_PID,String CATATAN, String USER_ID_CREATED) throws Exception {
		Connection conn = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		Db db = null;
		String sql = "";
		boolean sendEmel = false;
		try {

			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("USER_ID", ID_PERMOHONAN);

			r.add("FLAG_STATUS", STATUS_PID);//1-PERMOHONAN BARU 2-DITOLAK 3-DITERIMA
			r.add("CATATAN", CATATAN.toUpperCase());//1-PERMOHONAN BARU 2-DITOLAK 3-DITERIMA
			r.add("ID_PENYEMAK",USER_ID_SYSTEM);
			if(STATUS_PID.equals("2") && USER_ID_CREATED.equals(""))
			{
				r.add("TARIKH_KEPUTUSAN", r.unquote("sysdate"));
				sendEmel = true;
			}
			else if(STATUS_PID.equals("3") && !USER_ID_CREATED.equals(""))
			{
				r.add("TARIKH_KEPUTUSAN", r.unquote("sysdate"));
				r.add("ID_TABLEUSERS", USER_ID_CREATED);
				sendEmel = true;
			}

			sql = r.getSQLUpdate("PERMOHONANIDPENGGUNA");
			myLogger.info("UPDATE PERMOHONANIDPENGGUNA : "+sql);
			stmt.executeUpdate(sql);

			conn.commit();

			if(sendEmel==true)
			{
				hantarEmel(session,ID_PERMOHONAN);
			}

		} catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List listPengunaMOHON(HttpSession session,String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPenggunaMOHON = null;
		String sql = "";

		try {
			db = new Db();
			stmt = db.getStatement();
			sql = queryUserMOHON(session,"", carianTerperinci);
			myLogger.info(" V3: SQL senaraiPenggunaMOHON :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiPenggunaMOHON = Collections.synchronizedList(new ArrayList());
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
				h.put("ID_PENYEMAK",rs.getString("ID_PENYEMAK") == null ? "" : rs.getString("ID_PENYEMAK"));
				h.put("ID_PERMOHONAN",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("TARIKH_PENDAFTARAN",rs.getString("TARIKH_PENDAFTARAN") == null ? "" : rs.getString("TARIKH_PENDAFTARAN"));
				h.put("TARIKH_KEPUTUSAN",rs.getString("TARIKH_KEPUTUSAN") == null ? "" : rs.getString("TARIKH_KEPUTUSAN"));
				h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
				h.put("CATATAN",rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("NOKP",rs.getString("NOKP") == null ? "" : rs.getString("NOKP"));
				h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
				h.put("NO_HP",rs.getString("NO_HP") == null ? "" : rs.getString("NO_HP"));
				h.put("FLAG_STATUS",rs.getString("FLAG_STATUS") == null ? "" : rs.getString("FLAG_STATUS"));
				h.put("NAMA_UNIT",rs.getString("NAMA_UNIT") == null ? "" : rs.getString("NAMA_UNIT"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
				h.put("BAHAGIAN",rs.getString("BAHAGIAN") == null ? "" : rs.getString("BAHAGIAN"));
				h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
				h.put("PEGAWAIPENYELIA",rs.getString("PEGAWAIPENYELIA") == null ? "" : rs.getString("PEGAWAIPENYELIA"));
				h.put("ID_TABLEUSERS",rs.getString("ID_TABLEUSERS") == null ? "" : rs.getString("ID_TABLEUSERS"));
				h.put("ID_PENYEMAK",rs.getString("ID_PENYEMAK") == null ? "" : rs.getString("ID_PENYEMAK"));
				h.put("PENYEMAK",rs.getString("PENYEMAK") == null ? "" : rs.getString("PENYEMAK"));
				h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));
				h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));
				senaraiPenggunaMOHON.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return senaraiPenggunaMOHON;

	}


	public String queryUserOnline(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {


		String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_Online");
		String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_Online");
		String CT_NAMA = getParam("CT_NAMA_Online");
		String CT_ID_NEGERI = getParam("CT_ID_NEGERI_Online");
		String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_Online");
		String CT_LOGMASUK_MULA = getParam("CT_LOGMASUK_MULA_Online");
		String CT_LOGMASUK_AKHIR = getParam("CT_LOGMASUK_AKHIR_Online");
		String CT_STATUS_LOG = getParam("CT_STATUS_LOG_Online");
		String CT_ROLE_MAIN = getParam("CT_ROLE_MAIN_Online");
		String CT_ID_KATEGORI_PENGGUNA = getParam("CT_ID_KATEGORI_PENGGUNA_Online");
		String CT_HAD_UMUR = getParam("CT_HAD_UMUR_Online");


		this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
		this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
		this.context.put("CT_NAMA", CT_NAMA);
		this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
		this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
		this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
		this.context.put("CT_LOGMASUK_MULA", CT_LOGMASUK_MULA);
		this.context.put("CT_LOGMASUK_AKHIR", CT_LOGMASUK_AKHIR);
		this.context.put("CT_STATUS_LOG", CT_STATUS_LOG);
		this.context.put("CT_ID_KATEGORI_PENGGUNA", CT_ID_KATEGORI_PENGGUNA);
		this.context.put("CT_HAD_UMUR", CT_HAD_UMUR);
		//close ct

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
		" UO.KATEGORI, UO.NO_HP,UO.NO_TEL,UO.NO_FAX,UO.EMEL,UO.FLAG_AKTIF, "+
		" (CASE WHEN UO.FLAG_AKTIF = 1 THEN 'AKTIF' "+
		" WHEN UO.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
		" ELSE 'AKTIF' END) AS KEAKTIFAN ";

		//open ct
		if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += "  ,CHECK_TR.TOTAL_ROLE_CARI ";
		}
		//close ct

		//open ct
		if(!CT_HAD_UMUR.equals("") && CT_HAD_UMUR!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
					query += "  ,HAD_UMUR.UMUR ";
		}
		//close ct

		query += " FROM USERS U, USERS_ONLINE UO, ROLE R, WEB_LOGGER WL, TBLRUJNEGERI NEG, TBLRUJBANDAR BAN ";

		//open ct
		if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += " ,(SELECT RX.USER_ID,COUNT (RX.ROLE_ID) AS TOTAL_ROLE_CARI FROM USER_ROLE RX WHERE UPPER(RX.ROLE_ID) = '"+CT_ROLE_MAIN.toUpperCase()+"' "+
               " GROUP BY RX.USER_ID) CHECK_TR ";
		}
		//close ct


		//open ct
		if(!CT_HAD_UMUR.equals("") && CT_HAD_UMUR!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += " ,(SELECT "+
					" UOTEMP.USER_ID, "+
					" TO_NUMBER(CASE  "+
					" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NOT NULL THEN (TO_NUMBER(TO_CHAR(SYSDATE,'YYYY') - TO_NUMBER(TO_CHAR(TARIKH_LAHIR,'YYYY')))) "+
					" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS  NULL AND LENGTH(UOTEMP.NO_KP_BARU)=12 AND UOTEMP.NO_KP_BARU IS NOT NULL AND  REGEXP_LIKE(UOTEMP.NO_KP_BARU, '^[[:digit:]]+$') AND TO_NUMBER(SUBSTR(UOTEMP.NO_KP_BARU,1,2)) >= 20  THEN (TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER('19' || substr(NO_KP_BARU,1,2))) "+
					" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH(UOTEMP.NO_KP_BARU)=12 AND UOTEMP.NO_KP_BARU IS NOT NULL AND  REGEXP_LIKE(UOTEMP.NO_KP_BARU, '^[[:digit:]]+$') AND TO_NUMBER(SUBSTR(UOTEMP.NO_KP_BARU,1,2)) < 20  THEN (TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER('20' || substr(NO_KP_BARU,1,2))) "+
					" ELSE 0 "+
					" END) AS UMUR "+
					" FROM USERS_ONLINE UOTEMP "+
					" WHERE (TO_NUMBER(CASE  "+
					" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NOT NULL THEN (TO_NUMBER(TO_CHAR(SYSDATE,'YYYY') - TO_NUMBER(TO_CHAR(TARIKH_LAHIR,'YYYY')))) "+
					" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH(UOTEMP.NO_KP_BARU)=12 AND UOTEMP.NO_KP_BARU IS NOT NULL AND  REGEXP_LIKE(UOTEMP.NO_KP_BARU, '^[[:digit:]]+$') AND TO_NUMBER(SUBSTR(UOTEMP.NO_KP_BARU,1,2)) >= 20  THEN (TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER('19' || substr(NO_KP_BARU,1,2))) "+
					" WHEN UOTEMP.KATEGORI = 'Individu' AND UOTEMP.TARIKH_LAHIR IS NULL AND LENGTH(UOTEMP.NO_KP_BARU)=12 AND UOTEMP.NO_KP_BARU IS NOT NULL AND  REGEXP_LIKE(UOTEMP.NO_KP_BARU, '^[[:digit:]]+$') AND TO_NUMBER(SUBSTR(UOTEMP.NO_KP_BARU,1,2)) < 20  THEN (TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) - TO_NUMBER('20' || substr(NO_KP_BARU,1,2))) "+
					" ELSE 0 "+
					" END)) > 0) HAD_UMUR ";
		}
		//close ct

		query += " WHERE U.USER_ID = UO.USER_ID "+
		" AND U.USER_ROLE = R.NAME(+) ";

		//open ct
		if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += " AND U.USER_LOGIN =  CHECK_TR.USER_ID(+) ";
		}
		//close ct
		//open ct
		if(!CT_HAD_UMUR.equals("") && CT_HAD_UMUR!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += " AND U.USER_ID =  HAD_UMUR.USER_ID(+) ";
		}
		//close ct

		query += " AND U.USER_LOGIN = WL.USER_NAME(+) "+
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


		if(!CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{

			if(!CT_NAMA.equals("") && CT_NAMA!=null)
			{
				query += " AND (";
				query += " UPPER(U.USER_LOGIN) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
				query += " OR UPPER(U.USER_NAME) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
				query += ") ";
			}

			if(!CT_HAD_UMUR.equals("") && CT_HAD_UMUR!=null)
			{
				query += " AND (";

				if(CT_HAD_UMUR.equals("A"))
				{
					query += " TO_NUMBER(HAD_UMUR.UMUR) <= 18 ";
				}
				else if(CT_HAD_UMUR.equals("B"))
				{
					query += " TO_NUMBER(HAD_UMUR.UMUR) >= 19 AND TO_NUMBER(HAD_UMUR.UMUR) <= 30 ";
				}
				else if(CT_HAD_UMUR.equals("C"))
				{
					query += " TO_NUMBER(HAD_UMUR.UMUR) >= 31 AND TO_NUMBER(HAD_UMUR.UMUR) <= 40 ";
				}
				else if(CT_HAD_UMUR.equals("D"))
				{
					query += " TO_NUMBER(HAD_UMUR.UMUR) >= 41 AND TO_NUMBER(HAD_UMUR.UMUR) <= 50 ";
				}
				else if(CT_HAD_UMUR.equals("E"))
				{
					query += " TO_NUMBER(HAD_UMUR.UMUR) >= 51  ";
				}

				query += ") ";
			}

			if(!CT_ID_KATEGORI_PENGGUNA.equals("") && CT_ID_KATEGORI_PENGGUNA!=null)
			{
				query += " AND (";
				query += " UPPER(UO.KATEGORI) = '"+CT_ID_KATEGORI_PENGGUNA.toUpperCase()+"' ";
				query += ") ";
			}

			if(!CT_ID_NEGERI.equals("") && CT_ID_NEGERI!=null)
			{
				query += " AND (";
				query += " UO.ID_NEGERI = '"+CT_ID_NEGERI+"' ";
				query += ") ";
			}

			if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null)
			{
				query += " AND (";
				query += " UPPER(U.USER_ROLE) = '"+CT_ROLE_MAIN.toUpperCase()+"' OR CHECK_TR.TOTAL_ROLE_CARI>0 ";
				query += ") ";
			}

			if(!CT_FLAG_AKTIF.equals("") && CT_FLAG_AKTIF!=null)
			{
				if(CT_FLAG_AKTIF.equals("1"))
				{
					query += " AND (";
					query += " UPPER(UO.FLAG_AKTIF) IS NULL OR UPPER(UO.FLAG_AKTIF) = '1' OR UPPER(UO.FLAG_AKTIF) = '' ";
					query += ") ";
				}
				else if(CT_FLAG_AKTIF.equals("2"))
				{
					query += " AND (";
					query += " UPPER(UO.FLAG_AKTIF) = '2' ";
					query += ") ";
				}
			}

			if(!CT_LOGMASUK_MULA.equals("") && CT_LOGMASUK_MULA!=null)
			{
				query += " AND (";
				query += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') >=  TO_DATE('"+CT_LOGMASUK_MULA+"','DD/MM/YYYY') ";
				query += ") ";
			}
			if(!CT_LOGMASUK_AKHIR.equals("") && CT_LOGMASUK_AKHIR!=null)
			{
				query += " AND (";
				query += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=  TO_DATE('"+CT_LOGMASUK_AKHIR+"','DD/MM/YYYY') ";
				query += ") ";
			}
			if(!CT_STATUS_LOG.equals("") && CT_STATUS_LOG!=null)
			{
			//	" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
			//			" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

				if(CT_STATUS_LOG.equals("1"))
				{
					//SENYAP (60 Hari Dari Tarikh Terakhir Login)
					query += " AND (";
					query += " TRUNC(SYSDATE - TO_DATE(WL.LOG_DATE)) > 60 ";
					query += ") ";
				}
				else if(CT_STATUS_LOG.equals("2"))
				{
					//KATALALUAN TELAH LUPUT (90 Hari Dari Tarikh Terakhir Menukar Katalaluan)
					query += " AND (";
					query += " TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) > 90 ";
					query += ") ";
				}
			}
		}
		//close ct

		query += " GROUP BY U.USER_ID, U.USER_LOGIN, U.USER_NAME, U.USER_ROLE, R.DESCRIPTION, U.USER_TYPE, U.DATE_REGISTERED, "+
		" U.LAST_CHANGEPASSWORD, U.LAST_CHANGEPASSWORD, UO.ALAMAT1, UO.ALAMAT2, UO.ALAMAT3, UO.POSKOD, NEG.NAMA_NEGERI, NEG.ID_NEGERI, "+
		" BAN.KETERANGAN, BAN.ID_BANDAR,UO.NO_KP_BARU,UO.UMUR,UO.JANTINA,UO.TARAF_PERKAHWINAN,UO.TARIKH_LAHIR,UO.KATEGORI, ";

		//open ct
		if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += " CHECK_TR.TOTAL_ROLE_CARI, ";
		}
		//close ct

		//open ct
		if(!CT_HAD_UMUR.equals("") && CT_HAD_UMUR!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{
			query += " HAD_UMUR.UMUR, ";
		}
		//close ct


		query += " UO.NO_HP,UO.NO_TEL,UO.NO_FAX, UO.EMEL,UO.FLAG_AKTIF " +
		" ORDER BY U.USER_NAME ";

		return query;
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

					//diba tambah
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					/*
					if(rs.getString("NO_KP_BARU") != null && rs.getString("NO_KP_BARU").trim().length()==12)
					{
							h.put("NO_KP_BARU1", rs.getString("NO_KP_BARU") == null ? "" : rs
									.getString("NO_KP_BARU").trim().substring(0, 6));
							h.put("NO_KP_BARU2", rs.getString("NO_KP_BARU") == null ? "" : rs
									.getString("NO_KP_BARU").trim().substring(6, 8));
							h.put("NO_KP_BARU3", rs.getString("NO_KP_BARU") == null ? "" : rs
									.getString("NO_KP_BARU").trim().substring(8, 12));
					}
					else
					{
							h.put("NO_KP_BARU1","");
							h.put("NO_KP_BARU2","");
							h.put("NO_KP_BARU3","");
					}
					*/

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
				//diba tambah
				h.put("ID_NEGERI","");
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
	public List listPengunaKJP(HttpSession session,String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List senaraiPenggunaKJP = null;
		String sql = "";
		String flag_HQ = "KJP";
		//open ct

		try {
			db = new Db();
			stmt = db.getStatement();
			sql = queryUserKJP(session,"", carianTerperinci, "");
			myLogger.info(" V3: SQL senaraiPenggunaKJP :"+ sql);
			rs = stmt.executeQuery(sql);
			senaraiPenggunaKJP = Collections.synchronizedList(new ArrayList());
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
				h.put("NAMA_PENUH",rs.getString("NAMA_PENUH") == null ? "" : rs.getString("NAMA_PENUH"));
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
				h.put("NO_KP",rs.getString("NO_KP") == null ? "" : rs.getString("NO_KP"));
				/*
				if(rs.getString("NO_KP_BARU") != null && rs.getString("NO_KP_BARU").trim().length()==12)
				{
						h.put("NO_KP_BARU1", rs.getString("NO_KP_BARU") == null ? "" : rs
								.getString("NO_KP_BARU").trim().substring(0, 6));
						h.put("NO_KP_BARU2", rs.getString("NO_KP_BARU") == null ? "" : rs
								.getString("NO_KP_BARU").trim().substring(6, 8));
						h.put("NO_KP_BARU3", rs.getString("NO_KP_BARU") == null ? "" : rs
								.getString("NO_KP_BARU").trim().substring(8, 12));
				}
				else
				{
						h.put("NO_KP_BARU1","");
						h.put("NO_KP_BARU2","");
						h.put("NO_KP_BARU3","");
				}
				*/
				String display_note = "";
				int DAYS_AFTERLASTLOGIN = 0;
				if(rs.getString("DAYS_AFTERLASTLOGIN")!=null)
				{
					DAYS_AFTERLASTLOGIN = rs.getInt("DAYS_AFTERLASTLOGIN");
					if(DAYS_AFTERLASTLOGIN>60)
					{
					display_note += "*Senyap<br>";
					}
				}

				int DAYS_AFTERCHANGEPASS = 0;
				if(rs.getString("DAYS_AFTERCHANGEPASS")!=null)
				{
					DAYS_AFTERCHANGEPASS = rs.getInt("DAYS_AFTERCHANGEPASS");
					if(DAYS_AFTERCHANGEPASS>90)
					{
					display_note += "*Kata Laluan Luput<br>";
					}
				}



				h.put("DISPLAY_NOTE",display_note);

				senaraiPenggunaKJP.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return senaraiPenggunaKJP;

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
					h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));


				}
			}
			else
			{
				h.put("USER_ID","");
				h.put("USER_LOGIN","");
				h.put("USER_NAME","");
				h.put("NAMA_ROLE","");
				h.put("ROLE_ID","");
				h.put("USER_TYPE","");
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
				h.put("ID_SEKSYEN","");

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
	public List listPenggunaINT(HttpSession session,String carianTerperinci)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listPenggunaINT = null;
		String sql = "";
		String flag_HQ = "INT";
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = queryUserINT(session,"", carianTerperinci, "");
			myLogger.info(" V3: SQL listPenggunaINT :"+ sql);
			rs = stmt.executeQuery(sql);
			listPenggunaINT = Collections.synchronizedList(new ArrayList());
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



				String display_note = "";
				int DAYS_AFTERLASTLOGIN = 0;
				if(rs.getString("DAYS_AFTERLASTLOGIN")!=null)
				{
					DAYS_AFTERLASTLOGIN = rs.getInt("DAYS_AFTERLASTLOGIN");
					if(DAYS_AFTERLASTLOGIN>60)
					{
					display_note += "*Senyap<br>";
					}
				}

				int DAYS_AFTERCHANGEPASS = 0;
				if(rs.getString("DAYS_AFTERCHANGEPASS")!=null)
				{
					DAYS_AFTERCHANGEPASS = rs.getInt("DAYS_AFTERCHANGEPASS");
					if(DAYS_AFTERCHANGEPASS>90)
					{
					display_note += "*Kata Laluan Luput<br>";
					}
				}



				h.put("DISPLAY_NOTE",display_note);

				listPenggunaINT.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listPenggunaINT;

	}

	public String queryUserINT_ViewEdit(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {

		String query = "";

				query += " SELECT   U.USER_ID, U.USER_LOGIN,  UPPER (U.USER_NAME) AS USER_NAME, "+
						" U.USER_TYPE, TO_CHAR(U.DATE_REGISTERED,'DD/MM/YYYY') AS TARIKH_DAFTAR, U.LAST_CHANGEPASSWORD, "+
				" UPPER (R.DESCRIPTION) AS ROLE_UTAMA, R.NAME AS ROLE_ID,TO_CHAR (MAX (WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN, "+
				" TRUNC (SYSDATE - TO_DATE (MAX (WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN, "+
				" TRUNC (SYSDATE - TO_DATE (U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, UIN.FLAG_AKTIF,"+
				" (CASE WHEN UIN.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UIN.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
				" ELSE 'AKTIF' END ) AS KEAKTIFAN,UPPER (PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT,UPPER (N.NAMA_NEGERI) AS NEGERI_UI, "+
				" UPPER (D.NAMA_DAERAH) AS DAERAH_UI,UIN.EMEL,UIN.ID_JENISPEJABAT,UPPER (JP.KETERANGAN) AS JENIS_PEJABAT, " +
				" UPPER(UIN.JAWATAN) AS JAWATAN,UIN.ID_PEJABAT, UIN.ID_DAERAH, UIN.ID_NEGERI, UIN.ID_MUKIM, UIN.ID_AGENSI, PEJ.ID_SEKSYEN "+
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

				query += " GROUP BY U.USER_ID, U.USER_LOGIN, U.USER_NAME,R.NAME,R.DESCRIPTION,UIN.FLAG_AKTIF," +
						" U.USER_TYPE,U.DATE_REGISTERED,U.LAST_CHANGEPASSWORD, "+
				" U.LAST_CHANGEPASSWORD,PEJ.NAMA_PEJABAT,N.NAMA_NEGERI,D.NAMA_DAERAH,UIN.EMEL, UIN.ID_JENISPEJABAT," +
				" JP.KETERANGAN,UIN.JAWATAN,UIN.ID_PEJABAT, UIN.ID_DAERAH, UIN.ID_NEGERI, UIN.ID_MUKIM, UIN.ID_AGENSI, PEJ.ID_SEKSYEN ";

				query += " ORDER BY USER_LOGIN ";

		return query;
	}


	public String queryUserINT(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {


		String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_INT");
		String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_INT");
		String CT_NAMA = getParam("CT_NAMA_INT");
		String CT_JAWATAN = getParam("CT_JAWATAN_INT");
		//sini
		String CT_ID_JENISPEJABAT = getParam("CT_ID_JENISPEJABAT_INT");
		//myLogger.info("CT_ID_JENISPEJABAT2 -- "+CT_ID_JENISPEJABAT2);

		/*String seperator = "/";
		String CT_ID_JENISPEJABAT = "";

		if (CT_ID_JENISPEJABAT2==null || CT_ID_JENISPEJABAT2==""){
			 CT_ID_JENISPEJABAT = "";
			 //myLogger.info("CT_ID_JENISPEJABAT  null -- "+CT_ID_JENISPEJABAT);
		}
		else {
			CT_ID_JENISPEJABAT = CT_ID_JENISPEJABAT2.substring(0, CT_ID_JENISPEJABAT2.indexOf(seperator)).trim();
			//myLogger.info("CT_ID_JENISPEJABAT not null -- "+CT_ID_JENISPEJABAT);
		}*/

		String CT_ID_PEJABAT = getParam("CT_ID_PEJABAT_INT");
		String CT_ID_NEGERI = getParam("CT_ID_NEGERI_INT");
		String CT_ID_DAERAH = getParam("CT_ID_DAERAH_INT");
		String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_INT");
		String CT_LOGMASUK_MULA = getParam("CT_LOGMASUK_MULA_INT");
		String CT_LOGMASUK_AKHIR = getParam("CT_LOGMASUK_AKHIR_INT");
		String CT_STATUS_LOG = getParam("CT_STATUS_LOG_INT");
		String CT_ROLE_MAIN = getParam("CT_ROLE_MAIN_INT");


		this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
		this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
		this.context.put("CT_NAMA", CT_NAMA);
		this.context.put("CT_JAWATAN", CT_JAWATAN);
		this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
		this.context.put("CT_ID_PEJABAT", CT_ID_PEJABAT);
		this.context.put("CT_ID_JENISPEJABAT", CT_ID_JENISPEJABAT);
		this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
		this.context.put("CT_ID_DAERAH", CT_ID_DAERAH);
		this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
		this.context.put("CT_LOGMASUK_MULA", CT_LOGMASUK_MULA);
		this.context.put("CT_LOGMASUK_AKHIR", CT_LOGMASUK_AKHIR);
		this.context.put("CT_STATUS_LOG", CT_STATUS_LOG);
		//close ct


		String query = "";

				query += " SELECT   U.USER_ID, U.USER_LOGIN,  UPPER (U.USER_NAME) AS USER_NAME, "+
						" U.USER_TYPE, TO_CHAR(U.DATE_REGISTERED,'DD/MM/YYYY') AS TARIKH_DAFTAR, U.LAST_CHANGEPASSWORD, "+
				" UPPER (R.DESCRIPTION) AS ROLE_UTAMA, R.NAME AS ROLE_ID,TO_CHAR (MAX (WL.LOG_DATE),'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN, "+
				" TRUNC (SYSDATE - TO_DATE (MAX (WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN, "+
				" TRUNC (SYSDATE - TO_DATE (U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, UIN.FLAG_AKTIF,"+
				" (CASE WHEN UIN.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UIN.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' "+
				" ELSE 'AKTIF' END ) AS KEAKTIFAN,UPPER (PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT,UPPER (N.NAMA_NEGERI) AS NEGERI_UI, "+
				" UPPER (D.NAMA_DAERAH) AS DAERAH_UI,UIN.EMEL,UIN.ID_JENISPEJABAT, JP.KETERANGAN AS JENIS_PEJABAT, "+ //UPPER(JP.NAMA_PEJABAT) AS JENIS_PEJABAT, " +
				" UPPER(UIN.JAWATAN) AS JAWATAN ";
				//open ct
				if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{
					query += "  ,CHECK_TR.TOTAL_ROLE_CARI ";
				}
				//close ct

				query += " FROM USERS U, USERS_INTERNAL UI, ROLE R, USERS_INTEGRASI UIN,WEB_LOGGER WL, "+
				" TBLRUJPEJABAT PEJ, TBLRUJNEGERI N,TBLRUJDAERAH D, TBLRUJJENISPEJABAT JP";//TBLINTRUJJENISPEJABAT JP ";

				//open ct
				if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{
					query += " ,(SELECT RX.USER_ID,COUNT (RX.ROLE_ID) AS TOTAL_ROLE_CARI FROM USER_ROLE RX WHERE UPPER(RX.ROLE_ID) = '"+CT_ROLE_MAIN.toUpperCase()+"' "+
		               " GROUP BY RX.USER_ID) CHECK_TR ";
				}
				//close ct

				query += " WHERE U.USER_ID = UI.USER_ID AND UI.USER_ID = UIN.USER_ID "+
				" AND UIN.ID_NEGERI = N.ID_NEGERI(+) AND UIN.ID_DAERAH = D.ID_DAERAH(+)   ";


				//open ct
				if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{
					query += " AND U.USER_LOGIN =  CHECK_TR.USER_ID(+) ";
				}
				//close ct

				query += " AND U.USER_ROLE = R.NAME AND U.USER_LOGIN = WL.USER_NAME(+) "+
				" AND UIN.ID_PEJABAT = PEJ.ID_PEJABAT(+) AND UIN.ID_JENISPEJABAT = JP.ID_JENISPEJABAT(+) ";
				if(!USER_ID.trim().equals(""))
				{
					query += " AND U.USER_ID = '"+USER_ID.trim()+"' ";
				}
				if(!USER_LOGIN.trim().equals(""))
				{
					query += " AND U.USER_LOGIN = '"+USER_LOGIN+"' ";
				}


				if(!carianTerperinci.equals(""))
				{
					query += " AND (U.USER_LOGIN LIKE '%"+carianTerperinci+"%' "+
							" OR UPPER (U.USER_NAME) LIKE UPPER ('%"+carianTerperinci+"%') "+
							" OR UIN.EMEL LIKE '%"+carianTerperinci+"%' "+
							" OR UPPER (JP.KETERANGAN) LIKE UPPER ('%"+carianTerperinci+"%') "+
							"  )         ";
				}


				if(!CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{

					if(!CT_NAMA.equals("") && CT_NAMA!=null)
					{
						query += " AND (";
						query += " UPPER(U.USER_LOGIN) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
						query += " OR UPPER(U.USER_NAME) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
						query += ") ";
					}
					if(!CT_JAWATAN.equals("") && CT_JAWATAN!=null)
					{
						query += " AND (";
						query += " UPPER(UIN.JAWATAN) LIKE '%"+CT_JAWATAN.toUpperCase()+"%' ";
						query += ") ";
					}
					if(!CT_ID_JENISPEJABAT.equals("") && CT_ID_JENISPEJABAT!=null)
					{
						query += " AND (";
						query += " UIN.ID_JENISPEJABAT = '"+CT_ID_JENISPEJABAT+"' ";
						query += ") ";
					}
					if(!CT_ID_NEGERI.equals("") && CT_ID_NEGERI!=null)
					{
						query += " AND (";
						query += " UIN.ID_NEGERI = '"+CT_ID_NEGERI+"' ";
						query += ") ";
					}
					if(!CT_ID_DAERAH.equals("") && CT_ID_DAERAH!=null)
					{
						query += " AND (";
						query += " UIN.ID_DAERAH = '"+CT_ID_DAERAH+"' ";
						query += ") ";
					}
					if(!CT_ID_PEJABAT.equals("") && CT_ID_PEJABAT!=null)
					{
						query += " AND (";
						query += " UIN.ID_PEJABAT = '"+CT_ID_PEJABAT+"' ";
						query += ") ";
					}

					if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null)
					{
						query += " AND (";
						query += " UPPER(U.USER_ROLE) = '"+CT_ROLE_MAIN.toUpperCase()+"' OR CHECK_TR.TOTAL_ROLE_CARI>0 ";
						query += ") ";
					}

					if(!CT_FLAG_AKTIF.equals("") && CT_FLAG_AKTIF!=null)
					{
						if(CT_FLAG_AKTIF.equals("1"))
						{
							query += " AND (";
							query += " UPPER(UIN.FLAG_AKTIF) IS NULL OR UPPER(UIN.FLAG_AKTIF) = '1' OR UPPER(UIN.FLAG_AKTIF) = '' ";
							query += ") ";
						}
						else if(CT_FLAG_AKTIF.equals("2"))
						{
							query += " AND (";
							query += " UPPER(UIN.FLAG_AKTIF) = '2' ";
							query += ") ";
						}
					}

					if(!CT_LOGMASUK_MULA.equals("") && CT_LOGMASUK_MULA!=null)
					{
						query += " AND (";
						query += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') >=  TO_DATE('"+CT_LOGMASUK_MULA+"','DD/MM/YYYY') ";
						query += ") ";
					}
					if(!CT_LOGMASUK_AKHIR.equals("") && CT_LOGMASUK_AKHIR!=null)
					{
						query += " AND (";
						query += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=  TO_DATE('"+CT_LOGMASUK_AKHIR+"','DD/MM/YYYY') ";
						query += ") ";
					}
					if(!CT_STATUS_LOG.equals("") && CT_STATUS_LOG!=null)
					{
					//	" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
					//			" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

						if(CT_STATUS_LOG.equals("1"))
						{
							//SENYAP (60 Hari Dari Tarikh Terakhir Login)
							query += " AND (";
							query += " TRUNC(SYSDATE - TO_DATE(WL.LOG_DATE)) > 60 ";
							query += ") ";
						}
						else if(CT_STATUS_LOG.equals("2"))
						{
							//KATALALUAN TELAH LUPUT (90 Hari Dari Tarikh Terakhir Menukar Katalaluan)
							query += " AND (";
							query += " TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) > 90 ";
							query += ") ";
						}
					}
				}
				//close ct


				query += " GROUP BY U.USER_ID, U.USER_LOGIN, U.USER_NAME,R.NAME,R.DESCRIPTION,UIN.FLAG_AKTIF," +
						" U.USER_TYPE,U.DATE_REGISTERED,U.LAST_CHANGEPASSWORD, ";
				//open ct
				if(!CT_ROLE_MAIN.equals("") && CT_ROLE_MAIN!=null && !CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
				{
					query += " CHECK_TR.TOTAL_ROLE_CARI, ";
				}
				//close ct
				query += " U.LAST_CHANGEPASSWORD,PEJ.NAMA_PEJABAT,N.NAMA_NEGERI,D.NAMA_DAERAH,UIN.EMEL, "+
						 " UIN.ID_JENISPEJABAT, UIN.JAWATAN, JP.KETERANGAN ";//JP.NAMA_PEJABAT," +



				query += " ORDER BY USER_LOGIN ";

		return query;
	}



	public String queryUserKJP(HttpSession session,String USER_ID, String carianTerperinci,String USER_LOGIN) throws Exception {


		String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_KJP");
		String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_KJP");
		myLogger.info(" CT_FLAGTEPERINCI_CARIAN ::: "+CT_FLAGTEPERINCI_CARIAN);
		String CT_NAMA = getParam("CT_NAMA_KJP");
		String CT_ID_KEMENTERIAN = getParam("CT_ID_KEMENTERIAN_KJP");
		String CT_ID_AGENSI = getParam("CT_ID_AGENSI_KJP");
		String CT_ID_TUGASAN = getParam("CT_ID_TUGASAN_KJP");
		String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_KJP");
		String CT_LOGMASUK_MULA = getParam("CT_LOGMASUK_MULA_KJP");
		String CT_LOGMASUK_AKHIR = getParam("CT_LOGMASUK_AKHIR_KJP");
		String CT_STATUS_LOG = getParam("CT_STATUS_LOG_KJP");


		this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
		this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
		this.context.put("CT_NAMA", CT_NAMA);
		this.context.put("CT_ID_KEMENTERIAN", CT_ID_KEMENTERIAN);
		this.context.put("CT_ID_AGENSI", CT_ID_AGENSI);
		this.context.put("CT_ID_TUGASAN", CT_ID_TUGASAN);
		this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
		this.context.put("CT_LOGMASUK_MULA", CT_LOGMASUK_MULA);
		this.context.put("CT_LOGMASUK_AKHIR", CT_LOGMASUK_AKHIR);
		this.context.put("CT_STATUS_LOG", CT_STATUS_LOG);
		//close ct


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
		//" UK.NO_KP_BARU, " +
		" UK.ID_KEMENTERIAN, UK.ID_AGENSI, UI.EMEL, "+
		" UPPER(TRK.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN,UPPER(TRA.NAMA_AGENSI) AS NAMA_AGENSI, "+
		" UPPER(TRK.ALAMAT1) AS ALAMAT1,UPPER(TRK.ALAMAT2) AS ALAMAT2,UPPER(TRK.ALAMAT3) AS ALAMAT3, "+
		" TRK.POSKOD,UPPER(NEG.NAMA_NEGERI) AS NEGERI_KEMENTERIAN, "+
		" UK.FLAG_AKTIF, "+
		" (CASE WHEN UK.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UK.FLAG_AKTIF = 2 "+
		"   THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS KEAKTIFAN, UI.ID_JAWATAN, " +
		" (CASE WHEN UI.ID_JAWATAN = 4 THEN 'PELULUS'" +
		" WHEN UI.ID_JAWATAN = 9 THEN 'PENYEMAK'" +
		" WHEN UI.ID_JAWATAN = 24 THEN 'PENYEDIA' ELSE '' END) AS NAMA_JAWATAN, "+
		" TRK.ID_NEGERI, UI.NO_KP "+ //DIBA TAMBAH SINI
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


		if(!CT_FLAGTEPERINCI_CARIAN.equals("") && CT_FLAGTEPERINCI_CARIAN != null)
		{

			if(!CT_NAMA.equals("") && CT_NAMA!=null)
			{
				query += " AND (";
				query += " UPPER(U.USER_LOGIN) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
				query += " OR UPPER(U.USER_NAME) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";
				query += ") ";
			}
			if(!CT_ID_KEMENTERIAN.equals("") && CT_ID_KEMENTERIAN!=null)
			{
				query += " AND (";
				query += " UK.ID_KEMENTERIAN = '"+CT_ID_KEMENTERIAN+"' ";
				query += ") ";
			}
			if(!CT_ID_AGENSI.equals("") && CT_ID_AGENSI!=null)
			{
				query += " AND (";
				query += " UK.ID_AGENSI = '"+CT_ID_AGENSI+"' ";
				query += ") ";
			}
			if(!CT_ID_TUGASAN.equals("") && CT_ID_TUGASAN!=null)
			{
				query += " AND (";
				query += " UI.ID_JAWATAN = '"+CT_ID_TUGASAN+"' ";
				query += ") ";
			}

			if(!CT_FLAG_AKTIF.equals("") && CT_FLAG_AKTIF!=null)
			{
				if(CT_FLAG_AKTIF.equals("1"))
				{
					query += " AND (";
					query += " UPPER(UK.FLAG_AKTIF) IS NULL OR UPPER(UK.FLAG_AKTIF) = '1' OR UPPER(UK.FLAG_AKTIF) = '' OR UPPER (UK.FLAG_AKTIF )= '2' ";
					query += ") ";
				}
				else if(CT_FLAG_AKTIF.equals("2"))
				{
					query += " AND (";
					query += " UPPER(UK.FLAG_AKTIF) = '2' ";
					query += ") ";
				}
			}

			if(!CT_LOGMASUK_MULA.equals("") && CT_LOGMASUK_MULA!=null)
			{
				query += " AND (";
				query += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') >=  TO_DATE('"+CT_LOGMASUK_MULA+"','DD/MM/YYYY') ";
				query += ") ";
			}
			if(!CT_LOGMASUK_AKHIR.equals("") && CT_LOGMASUK_AKHIR!=null)
			{
				query += " AND (";
				query += "  TO_DATE(TO_CHAR(WL.LOG_DATE, 'DD/MM/YYYY'), 'DD/MM/YYYY') <=  TO_DATE('"+CT_LOGMASUK_AKHIR+"','DD/MM/YYYY') ";
				query += ") ";
			}
			if(!CT_STATUS_LOG.equals("") && CT_STATUS_LOG!=null)
			{
			//	" TRUNC(SYSDATE - TO_DATE(MAX(WL.LOG_DATE))) AS DAYS_AFTERLASTLOGIN," +
			//			" TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) AS DAYS_AFTERCHANGEPASS, "+

				if(CT_STATUS_LOG.equals("1"))
				{
					//SENYAP (60 Hari Dari Tarikh Terakhir Login)
					query += " AND (";
					query += " TRUNC(SYSDATE - TO_DATE(WL.LOG_DATE)) > 60 ";
					query += ") ";
				}
				else if(CT_STATUS_LOG.equals("2"))
				{
					//KATALALUAN TELAH LUPUT (90 Hari Dari Tarikh Terakhir Menukar Katalaluan)
					query += " AND (";
					query += " TRUNC(SYSDATE - TO_DATE(U.LAST_CHANGEPASSWORD)) > 90 ";
					query += ") ";
				}
			}
		}
		//close ct


		query += " GROUP BY U.USER_ID,U.USER_LOGIN,U.USER_NAME,U.USER_ROLE,R.DESCRIPTION, "+
		" U.USER_TYPE,U.DATE_REGISTERED,U.LAST_CHANGEPASSWORD, "+
		" U.LAST_CHANGEPASSWORD," +
		//" UK.NO_KP_BARU," +
		" UK.ID_KEMENTERIAN, "+
		" UK.ID_AGENSI,UI.EMEL,TRK.NAMA_KEMENTERIAN,TRA.NAMA_AGENSI, "+
		" TRK.ALAMAT1,TRK.ALAMAT2,TRK.ALAMAT3,TRK.POSKOD,NEG.NAMA_NEGERI, "+
		" UK.FLAG_AKTIF,UI.ID_JAWATAN, TRK.ID_NEGERI, UI.NO_KP "+ //DIBA TAMBAH ID NEGERI

		" ORDER BY U.USER_NAME ";


		return query;
	}

	public void deletePenggunaMOHON(HttpSession session,String ID_PERMOHONAN,String NAMA) throws Exception {
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
			r.add("user_id",ID_PERMOHONAN);
			sql = r.getSQLDelete("PERMOHONANIDPENGGUNA");
			stmt.executeUpdate(sql);
			AuditTrail.logActivity(this,session,"DEL","PERMOHONANIDPENGGUNA ["+NAMA+"] Deleted");

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

	public void deletePengguna(HttpSession session,String USER_ID, String jenis_user,String USER_NAME) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();


			boolean deleteTableUtama = true;
			if(jenis_user.equals("internal"))
			{
				if(viewDataPenggunaOnline(session, USER_ID, "").size()>0 || viewDataPenggunaKJP(session, USER_ID, "").size()>0 || viewDataPenggunaINT(session, USER_ID, "").size()>0)
				{
					deleteTableUtama = false;
				}
			}
			else if(jenis_user.equals("online"))
			{
				if(viewDataPenggunaInternal(session, USER_ID, "","").size()>0 || viewDataPenggunaKJP(session, USER_ID, "").size()>0 || viewDataPenggunaINT(session, USER_ID, "").size()>0)
				{
					deleteTableUtama = false;
				}
			}
			else if(jenis_user.equals("KJP"))
			{
				if(viewDataPenggunaInternal(session, USER_ID, "","").size()>0 || viewDataPenggunaOnline(session, USER_ID, "").size()>0 || viewDataPenggunaINT(session, USER_ID, "").size()>0)
				{
					deleteTableUtama = false;
				}
			}
			else if(jenis_user.equals("INT"))
			{
				if(viewDataPenggunaInternal(session, USER_ID, "","").size()>0 || viewDataPenggunaOnline(session, USER_ID, "").size()>0 || viewDataPenggunaKJP(session, USER_ID, "").size()>0)
				{
					deleteTableUtama = false;
				}
			}


			if(deleteTableUtama==true)
			{
				r.add("user_id", USER_ID);
				sql = r.getSQLDelete("Users");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","USER ["+USER_NAME+"] Deleted");

				String sql_deleteUR = "DELETE FROM USER_ROLE W WHERE W.USER_ID = (SELECT USER_LOGIN FROM USERS WHERE USER_ID='"+USER_ID+"') ";
				stmt.executeUpdate(sql_deleteUR);
			}


			if(jenis_user.equals("internal"))
			{
				if(viewDataPenggunaKJP(session, USER_ID, "").size()==0 && viewDataPenggunaINT(session, USER_ID, "").size()==0)
				{
				r.clear();
				r.add("user_id", USER_ID);
				sql = r.getSQLDelete("Users_Internal");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","USER INTERNAL ["+USER_NAME+"] Deleted");
				}

				if(viewDataPenggunaINT(session, USER_ID, "").size()==0)
				{
				String sql_deleteUR = "DELETE FROM USER_ROLE W WHERE W.USER_ID = (SELECT USER_LOGIN FROM USERS WHERE USER_ID='"+USER_ID+"') " +
						" AND W.ROLE_ID NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
				stmt.executeUpdate(sql_deleteUR);
				}
			}
			else if(jenis_user.equals("INT"))
			{
				if(viewDataPenggunaKJP(session, USER_ID, "").size()==0 && viewDataPenggunaInternal(session, USER_ID, "","").size()==0)
				{
				r.clear();
				r.add("user_id", USER_ID);
				sql = r.getSQLDelete("Users_Internal");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","USER INTERNAL ["+USER_NAME+"] Deleted");
				}

				String sql_deleteUR = "DELETE FROM USER_ROLE W WHERE W.USER_ID = (SELECT USER_LOGIN FROM USERS WHERE USER_ID='"+USER_ID+"') " +
						" AND W.ROLE_ID NOT IN ('online_kjpagensi','online_kjp','online','ppt-online-user','php-online-user','ppk-online-user')";
				stmt.executeUpdate(sql_deleteUR);
			}
			else if(jenis_user.equals("online"))
			{
				r.clear();
				r.add("user_id", USER_ID);
				sql = r.getSQLDelete("Users_Online");
				stmt.executeUpdate(sql);

				String sql_deleteUR = "DELETE FROM USER_ROLE W WHERE W.USER_ID = (SELECT USER_LOGIN FROM USERS WHERE USER_ID='"+USER_ID+"') " +
						" AND W.ROLE_ID IN ('online','ppt-online-user','php-online-user','ppk-online-user')";
				stmt.executeUpdate(sql_deleteUR);

				AuditTrail.logActivity(this,session,"DEL","USER ONLINE ["+USER_NAME+"] Deleted");
			}
			else if(jenis_user.equals("KJP"))
			{
				if(viewDataPenggunaInternal(session, USER_ID, "","").size()==0 && viewDataPenggunaINT(session, USER_ID, "").size()==0)
				{
				r.clear();
				r.add("user_id", USER_ID);
				sql = r.getSQLDelete("Users_Internal");
				stmt.executeUpdate(sql);
				AuditTrail.logActivity(this,session,"DEL","USER INTERNAL ["+USER_NAME+"] Deleted");
				}

				r.clear();
				r.add("user_id", USER_ID);
				sql = r.getSQLDelete("Users_Kementerian");
				stmt.executeUpdate(sql);

				String sql_deleteUR = "DELETE FROM USER_ROLE W WHERE W.USER_ID = (SELECT USER_LOGIN FROM USERS WHERE USER_ID='"+USER_ID+"') " +
						" AND W.ROLE_ID IN ('online_kjpagensi','online_kjp')";
				stmt.executeUpdate(sql_deleteUR);
				AuditTrail.logActivity(this,session,"DEL","USER KJP ["+USER_NAME+"] Deleted");
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
	}


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

			myLogger.info(" V3: SQL listPenggunaMengikutRole :"+ sql);
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

	public void checkForHistory(HttpSession session,String USER_ID, Hashtable ASAL, Hashtable BARU, String AKTIVITI)
			throws Exception {

		//myLogger.info(" *****checkForHistory USER_ID : "+USER_ID+" ASAL : "+ASAL+" BARU : "+BARU +" AKTIVITI :"+AKTIVITI);

		//LAST_CHANGEPASSWORD
		//DAYS_AFTERLASTLOGIN


		Integer count_ada_changes = 0;
		Set<String> keys_check = BARU.keySet();
		for(String key_c: keys_check){
			String rekod_asal = (String) ASAL.get(key_c);
			String rekod_baru = (String) BARU.get(key_c);
			//myLogger.info(" rekod_asal : "+rekod_asal+" rekod_baru : "+rekod_baru);

			if(!rekod_asal.equals(rekod_baru)
					&& !key_c.toUpperCase().contains("ID_")
					&& !key_c.toUpperCase().contains("_ID")
					&& !key_c.toUpperCase().contains("LAST_")
					&& !key_c.toUpperCase().contains("DAYS_")
					&& !key_c.toUpperCase().equals("JANTINA")
					&& !key_c.toUpperCase().equals("FLAG_AKTIF")
					&& !key_c.toUpperCase().equals("TARAF_PERKAHWINAN")
					)
			{
				count_ada_changes++;
			}
		}


		if(count_ada_changes>0)
		{
			String id_history_utama = saveHistoryUtama(session,USER_ID,AKTIVITI);
			Set<String> keys = BARU.keySet();
			for(String key: keys){
				//myLogger.info(" key : "+key);
				String rekod_asal = (String) ASAL.get(key);
				String rekod_baru = (String) BARU.get(key);
				if(!rekod_asal.equals(rekod_baru)
						&& !key.toUpperCase().contains("ID_")
						&& !key.toUpperCase().contains("_ID")
						&& !key.toUpperCase().contains("LAST_")
						&& !key.toUpperCase().contains("DAYS_")
						&& !key.toUpperCase().equals("JANTINA")
						&& !key.toUpperCase().equals("FLAG_AKTIF")
						&& !key.toUpperCase().equals("TARAF_PERKAHWINAN")
						)
				{
					myLogger.info(" **** ADA PERUBAHAN -> USER_ID : "+USER_ID+" AKTIVITI :"+AKTIVITI+" key : "+key+" ASAL : "+rekod_asal+" BARU : "+rekod_baru);
					key = key.replace("_PEJ", "_PEJABAT");
					key = key.replace("ROLE_MAIN", "ROLE ID");
					key = key.replace("ROLE_MAIN_DETAILS", "ROLE NAME");
					key = key.replace("_", " ");
					saveHistorySub(session,id_history_utama,rekod_asal,rekod_baru,key);
				}
			}
		}

		deleteHISTORY(USER_ID);
	}


	public String saveHistoryUtama(HttpSession session,String USER_ID,String AKTIVITI) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		long id_historyutama = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);


			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			id_historyutama = DB.getNextID(db, "TBLSEJARAHPENGGUNAUTAMA_SEQ");
			returnUSERID = id_historyutama+"";
			r.add("ID_SEJARAHPENGGUNAUTAMA", id_historyutama);
			r.add("JENIS_AKTIVITI", AKTIVITI);
			r.add("USER_ID", USER_ID);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLSEJARAHPENGGUNAUTAMA");
			myLogger.info("V3 : INSERT TBLSEJARAHPENGGUNAUTAMA : "+sql);
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
	    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return returnUSERID;
	}


	public String saveHistorySub(HttpSession session,String ID_SEJARAHMAIN,String ASAL,String BARU,String LABEL) throws Exception {
		Connection conn = null;
		Db db = null;
		String returnUSERID = "";
		String sql = "";
		long id_historysub = 0;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);


			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			id_historysub = DB.getNextID(db, "TBLSEJARAHPENGGUNASUB_SEQ");
			returnUSERID = id_historysub+"";
			r.add("ID_SEJARAHPENGGUNASUB", id_historysub);
			r.add("ID_SEJARAHPENGGUNAUTAMA", ID_SEJARAHMAIN);
			r.add("REKOD_SEBELUM", ASAL);
			r.add("REKOD_SELEPAS", BARU);
			r.add("REKOD_LABEL", LABEL);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			sql = r.getSQLInsert("TBLSEJARAHPENGGUNASUB");
			myLogger.info("V3 : INSERT TBLSEJARAHPENGGUNASUB : "+sql);
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
	    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return returnUSERID;
	}


public void hantarEmel(HttpSession session,String ID_PERMOHONAN) throws Exception {

		myLogger.info("MASUK FUNCTION EMEL");

		EmailProperty pro = EmailProperty.getInstance();
		EmailSender email = EmailSender.getInstance();
		email.FROM = pro.getFrom();

		Hashtable viewpengguna = viewDataPenggunaMOHON(session, ID_PERMOHONAN);

		String subject = "";
		String content = "";
		String status = (String)viewpengguna.get("FLAG_STATUS");
		if(status.equals("2"))//permohonan didotak
		{
			//subject = " PERMOHONAN BARU ID PENGGUNA - DITOLAK ";
			//content = " Permohonan Ditolak";
			//#wanidiba
			//wani diba, buat emel punya content & subject cun cun mengikut samsul punya req..
			//maklumat permohonan boleh amik dari object hashtable viewPengguna


				subject = " Sistem MyeTaPP - Permohonan Akaun Pengguna Baru telah ditolak. ";

				content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
				content+= " Dukacita dimaklumkan bahawa permohonan anda untuk membuka akaun MyeTaPP telah ditolak. <br>";
				content+= " Maklumat lanjut adalah seperti berikut : <br><br>";

				content+= " <strong>Maklumat Permohonan :</strong> <br><br>";

				content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama Pejabat</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NAMA_UNIT")+"</td></tr>" ;

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama Bahagian</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("BAHAGIAN")+"</td></tr>" ;

				/*content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama Unit</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NAMA_UNIT")+"</td></tr>" ;*/

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>NO KP</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NOKP")+"</td></tr>" ;

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Nama</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NAMA")+"</td></tr>" ;

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Jawatan</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("JAWATAN")+"</td></tr>" ;

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>No Tel</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NO_HP")+"</td></tr>" ;

				content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
						" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
						" <tr><td valign='top'></td><td valign='top'>Emel</td>" +
						" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("EMEL")+"</td></tr>" ;

				content+= " </table> <br>";

				/*content+= " <strong>Perhatian : </strong><br>";
				content+= "1. Kata Laluan ini boleh ditukar setelah Log Masuk dilakukan melalui Menu : My Profile <br>";*/
				content+= "<br><br><br>";


				content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";

				content+= " Sekian, terima kasih.<br><br><br>";

				content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";

		}
		else if(status.equals("3"))//permohonan diterima
		{
			//subject = " PERMOHONAN BARU ID PENGGUNA - DITERIMA ";
			//content = " Permohonan Diterima";
			//#wanidiba
			//wani diba, buat emel punya content & subject cun cun mengikut samsul punya req..
			//maklumat permohonan boleh amik dari object hashtable viewPengguna

			subject = " Sistem MyeTaPP - Permohonan Akaun Pengguna Baru telah diterima. ";

			content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
			content+= " Untuk makluman Ybhg. Dato/Tuan/Puan/Cik, Akaun Pengguna MyeTaPP yang berikut telah diwujudkan dalam Sistem MyeTaPP. <br>";
			content+= " Maklumat lanjut adalah seperti berikut : <br><br>";

			content+= " <strong>Maklumat Permohonan :</strong> <br><br>";

			content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama Pejabat</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NAMA_UNIT")+"</td></tr>" ;

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama Bahagian</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("BAHAGIAN")+"</td></tr>" ;

			/*content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama Unit</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NAMA_UNIT")+"</td></tr>" ;*/

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>NO KP</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NOKP")+"</td></tr>" ;

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Nama</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NAMA")+"</td></tr>" ;

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Jawatan</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("JAWATAN")+"</td></tr>" ;

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>No Tel</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NO_HP")+"</td></tr>" ;

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Emel</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("EMEL")+"</td></tr>" ;

			content+= " </table> <br>";

			content+= " Akaun tersebut boleh diakses menggunakan maklumat berikut : <br>";


			content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>ID Pengguna</td>" +
					" <td valign='top' >:</td><td valign='top'>"+viewpengguna.get("NOKP")+"</td></tr>" ;

			content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='28%'></td>" +
					" <td valign='top'  width='1%'></td><td valign='top'  width='70%'></td></tr>" +
					" <tr><td valign='top'></td><td valign='top'>Kata Laluan</td>" +
					" <td valign='top' >:</td><td valign='top'>P@$$w0rd2018</td></tr>" ;

			content+= " </table> <br>";

			content+= " <strong>Perhatian : </strong><br>";
			content+= "1. Kata Laluan ini boleh ditukar setelah Log Masuk dilakukan melalui Menu : My Profile <br>";
			content+= "2. Rahsiakan kata laluan anda daripada orang lain. <br><br><br>";


			content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";

			content+= " Sekian, terima kasih.<br><br><br>";

			content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";

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
		email.MULTIPLE_RECIEPIENT = new String[1];
		email.MULTIPLE_RECIEPIENT[0] = (String)viewpengguna.get("EMEL");

		//email.TO_CC = new String[1];
		//email.TO_CC[0] = "razman.zainal@gmail.com";
		email.sendEmail();
	 }

/*

SELECT '' AS USER_NAME, '' AS USER_LOGIN,'' AS AKTIVITI, SUB.ID_SEJARAHPENGGUNASUB,SUB.ID_SEJARAHPENGGUNAUTAMA,SUB.REKOD_SELEPAS,
SUB.REKOD_SEBELUM,SUB.REKOD_LABEL, SUB.TARIKH_MASUK AS TARIKH_MASUK , SUB.ID_MASUK, 2 AS LAYER
FROM TBLSEJARAHPENGGUNASUB SUB
UNION
SELECT UPPER(U.USER_NAME) AS USERNAME, U.USER_LOGIN, UTAMA.JENIS_AKTIVITI, NULL AS ID_SEJARAHPENGGUNASUB, UTAMA.ID_SEJARAHPENGGUNAUTAMA, NULL AS REKOD_LEPAS,
NULL AS REKOD_SEBELUM, NULL AS REKOD_LABEL, UTAMA.TARIKH_MASUK AS TARIKH_MASUK , UTAMA.ID_MASUK, 1 AS LAYER
FROM TBLSEJARAHPENGGUNAUTAMA UTAMA, USERS U WHERE UTAMA.ID_MASUK = U.USER_ID
ORDER BY TARIKH_MASUK,ID_SEJARAHPENGGUNAUTAMA,LAYER, REKOD_LABEL

	*/

//listPengunaHISTORY(session,carianTerperinciHISTORY,TARIKH_MULA_HISTORY, TARIKH_AKHIR_HISTORY,USER_ID);
@SuppressWarnings("unchecked")
public List listPengunaHISTORY(HttpSession session,String USER_ID,String ID_UTAMA,String TYPE,
		String carianTerperinciHISTORY,String TARIKH_MULA_HISTORY, String TARIKH_AKHIR_HISTORY)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	List listHistoryRekodPengguna = null;
	String sql = "";
	String filter = "";


	if(!carianTerperinciHISTORY.equals(""))
	{
		filter += " AND ( " +
				" UPPER(UTAMA.JENIS_AKTIVITI) LIKE '%"+carianTerperinciHISTORY.toUpperCase()+"%' "+
				" OR UPPER(SUB.REKOD_SEBELUM) LIKE UPPER ('%"+carianTerperinciHISTORY.toUpperCase()+"%') "+
				" OR UPPER(SUB.REKOD_SELEPAS) LIKE UPPER ('%"+carianTerperinciHISTORY.toUpperCase()+"%') "+
				" OR UPPER(SUB.REKOD_LABEL) LIKE UPPER ('%"+carianTerperinciHISTORY.toUpperCase()+"%') "+
				"  )         ";
	}


	if(!TARIKH_MULA_HISTORY.equals("") && !TARIKH_AKHIR_HISTORY.equals(""))
	{
		filter += " AND UTAMA.TARIKH_MASUK BETWEEN TO_DATE('"+TARIKH_MULA_HISTORY+"','DD/MM/YYYY') AND TO_DATE('"+TARIKH_AKHIR_HISTORY+"','DD/MM/YYYY') ";
	}
	if(!TARIKH_MULA_HISTORY.equals("") && TARIKH_AKHIR_HISTORY.equals(""))
	{
		filter += " AND UTAMA.TARIKH_MASUK > TO_DATE('"+TARIKH_MULA_HISTORY+"','DD/MM/YYYY') ";
	}
	if(TARIKH_MULA_HISTORY.equals("") && !TARIKH_AKHIR_HISTORY.equals(""))
	{
		filter += " AND UTAMA.TARIKH_MASUK < TO_DATE('"+TARIKH_AKHIR_HISTORY+"','DD/MM/YYYY') ";
	}

	if(!USER_ID.equals(""))
	{
		filter += " AND UTAMA.USER_ID = '"+USER_ID+"' ";
	}



	try {
		db = new Db();
		stmt = db.getStatement();

		if(TYPE.equals("UTAMA"))
		{


			sql = " SELECT DISTINCT UPPER(U.USER_NAME) AS USER_NAME, U.USER_LOGIN, UTAMA.JENIS_AKTIVITI AS AKTIVITI, NULL AS ID_SEJARAHPENGGUNASUB, UTAMA.ID_SEJARAHPENGGUNAUTAMA, " +
					" NULL AS REKOD_SELEPAS, "+
					" NULL AS REKOD_SEBELUM, NULL AS REKOD_LABEL, TO_CHAR(UTAMA.TARIKH_MASUK,'DD/MM/YYYY hh24:mi:ss') AS TARIKH_MASUK , UTAMA.ID_MASUK, 1 AS LAYER  "+
					" FROM TBLSEJARAHPENGGUNAUTAMA UTAMA, USERS U, TBLSEJARAHPENGGUNASUB SUB  "+
					" WHERE UTAMA.ID_MASUK = U.USER_ID AND SUB.ID_SEJARAHPENGGUNAUTAMA(+) = UTAMA.ID_SEJARAHPENGGUNAUTAMA ";

			sql += filter;


			sql +=	" ORDER BY TARIKH_MASUK DESC,ID_SEJARAHPENGGUNAUTAMA ASC,LAYER ASC, REKOD_LABEL ASC  ";


		}
		else if(TYPE.equals("SUB"))
		{
			sql = " SELECT '' AS USER_NAME, '' AS USER_LOGIN,'' AS AKTIVITI, SUB.ID_SEJARAHPENGGUNASUB,SUB.ID_SEJARAHPENGGUNAUTAMA,SUB.REKOD_SELEPAS, "+
					" SUB.REKOD_SEBELUM,SUB.REKOD_LABEL, SUB.TARIKH_MASUK AS TARIKH_MASUK , SUB.ID_MASUK, 2 AS LAYER "+
					" FROM TBLSEJARAHPENGGUNASUB SUB WHERE SUB.ID_SEJARAHPENGGUNAUTAMA = '"+ID_UTAMA+"' "+
					" ORDER BY TARIKH_MASUK,ID_SEJARAHPENGGUNAUTAMA,LAYER, REKOD_LABEL ";
		}
		else if(TYPE.equals("LAPORAN"))
		{
			sql = " SELECT DISTINCT UPPER (U.USER_NAME) AS USER_NAME, U.USER_LOGIN, "+
					" UTAMA.JENIS_AKTIVITI AS AKTIVITI, "+
					" NULL AS ID_SEJARAHPENGGUNASUB, UTAMA.ID_SEJARAHPENGGUNAUTAMA, "+
					" NULL AS REKOD_SELEPAS, NULL AS REKOD_SEBELUM, "+
					" NULL AS REKOD_LABEL, "+
					" TO_CHAR (UTAMA.TARIKH_MASUK, "+
					" 'DD/MM/YYYY hh24:mi:ss' "+
					" ) AS TARIKH_MASUK, "+
					" UTAMA.ID_MASUK, 1 AS LAYER "+
					" FROM TBLSEJARAHPENGGUNAUTAMA UTAMA, "+
					" USERS U, "+
					" TBLSEJARAHPENGGUNASUB SUB "+
					" WHERE UTAMA.ID_MASUK = U.USER_ID ";
					sql += filter;
			sql += " AND SUB.ID_SEJARAHPENGGUNAUTAMA(+) = UTAMA.ID_SEJARAHPENGGUNAUTAMA "+
					" UNION                 "+
					" SELECT   '' AS USER_NAME, '' AS USER_LOGIN, '' AS AKTIVITI, "+
					" SUB.ID_SEJARAHPENGGUNASUB, SUB.ID_SEJARAHPENGGUNAUTAMA, "+
					" SUB.REKOD_SELEPAS, SUB.REKOD_SEBELUM, SUB.REKOD_LABEL, "+
					" TO_CHAR (SUB.TARIKH_MASUK, "+
					" 'DD/MM/YYYY hh24:mi:ss' "+
					" ) AS TARIKH_MASUK, SUB.ID_MASUK, 2 AS LAYER "+
					" FROM TBLSEJARAHPENGGUNASUB SUB, TBLSEJARAHPENGGUNAUTAMA UTAMA "+
					" WHERE SUB.ID_SEJARAHPENGGUNAUTAMA = UTAMA.ID_SEJARAHPENGGUNAUTAMA    ";
			sql += filter;
			sql +=	" ORDER BY TARIKH_MASUK DESC, "+
					" ID_SEJARAHPENGGUNAUTAMA ASC, "+
					" LAYER ASC, "+
					" REKOD_LABEL ASC ";
		}

		myLogger.info(" V3: SQL listHistoryRekodPengguna ["+TYPE+"] :"+ sql);
		rs = stmt.executeQuery(sql);
		listHistoryRekodPengguna = Collections.synchronizedList(new ArrayList());
		Map h = null;
		int bil = 0;
		int bil_sub = 0;
		while (rs.next()) {
			h = Collections.synchronizedMap(new HashMap());
			String bil_temp = "";
			if(TYPE.equals("LAPORAN"))
			{
				if(rs.getString("LAYER").equals("1"))
				{
					bil++;
					bil_temp = bil+"";
					bil_sub = 0;
				}
				else
				{
					bil_sub++;
					bil_temp = bil + "." +bil_sub+"";
				}
			}
			else
			{
				bil++;
				bil_temp = bil+"";
			}

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
			h.put("BIL",bil_temp);
			h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
			h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
			h.put("AKTIVITI",rs.getString("AKTIVITI") == null ? "" : rs.getString("AKTIVITI"));
			h.put("ID_SEJARAHPENGGUNASUB",rs.getString("ID_SEJARAHPENGGUNASUB") == null ? "" : rs.getString("ID_SEJARAHPENGGUNASUB"));
			h.put("ID_SEJARAHPENGGUNAUTAMA",rs.getString("ID_SEJARAHPENGGUNAUTAMA") == null ? "" : rs.getString("ID_SEJARAHPENGGUNAUTAMA"));
			h.put("REKOD_SELEPAS",rs.getString("REKOD_SELEPAS") == null ? "" : rs.getString("REKOD_SELEPAS"));
			h.put("REKOD_SEBELUM",rs.getString("REKOD_SEBELUM") == null ? "" : rs.getString("REKOD_SEBELUM"));
			h.put("REKOD_LABEL",rs.getString("REKOD_LABEL") == null ? "" : rs.getString("REKOD_LABEL"));
			h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
			h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
			h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));
			listHistoryRekodPengguna.add(h);
		}

	} finally {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (db != null)
			db.close();
	}
	return listHistoryRekodPengguna;

}


public void deleteHISTORY(String USER_ID) throws Exception {
	Connection conn = null;
	Db db = null;
	String sql = "";
	String sql_condition = "";
	try {
		db = new Db();
		conn = db.getConnection();
		conn.setAutoCommit(false);
		Statement stmt = db.getStatement();
		SQLRenderer r = new SQLRenderer();

		sql_condition = " SELECT ID_SEJARAHPENGGUNAUTAMA FROM "+
				" (SELECT ID_SEJARAHPENGGUNAUTAMA, TARIKH_MASUK, ROWNUM R "+
				" FROM ( "+
				" SELECT ID_SEJARAHPENGGUNAUTAMA, TARIKH_MASUK FROM TBLSEJARAHPENGGUNAUTAMA WHERE USER_ID = '"+USER_ID+"'  "+
				" ORDER BY TARIKH_MASUK DESC "+
				" ) K ) RW "+
				" WHERE RW.R > 50 ";



		sql = "DELETE FROM TBLSEJARAHPENGGUNASUB WHERE ID_SEJARAHPENGGUNAUTAMA IN ("+sql_condition+")";
		myLogger.info("DELETE  TBLSEJARAHPENGGUNASUB : "+sql);
		stmt.executeUpdate(sql);

		sql = "DELETE FROM TBLSEJARAHPENGGUNAUTAMA WHERE ID_SEJARAHPENGGUNAUTAMA IN ("+sql_condition+")";
		myLogger.info("DELETE  TBLSEJARAHPENGGUNASUB : "+sql);
		stmt.executeUpdate(sql);


		conn.commit();
	} catch (Exception re) {
		throw re;
	}finally {
		if (db != null)
			db.close();
	}
}


//tambah function emel lepas daftar KJP
public void hantarEmelDaftarKJP(HttpSession session,String USER_ID,Integer STATUS) throws Exception {

	myLogger.info("MASUK FUNCTION EMEL KJP user id : "+ USER_ID);

	EmailProperty pro = EmailProperty.getInstance();
	EmailSender email = EmailSender.getInstance();
	email.FROM = pro.getFrom();
	Hashtable viewPengguna = viewDataPenggunaKJP(session, USER_ID, "");


	String subject = "";
	String content = "";


		subject = " Sistem MyeTaPP - Pengguna Baru KJP telah didaftarkan di dalam sistem. ";
		content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
		content+= " Untuk makluman, sistem telah mendaftarkan satu pengguna baru KJP. <br>";
		content+= " Maklumat lanjut adalah seperti berikut : <br><br>";
		content+= " Maklumat Pendaftaran Pengguna KJP : <br>";

		content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";

		content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
				" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
				" <tr><td valign='top'></td><td valign='top'>Nama Kementerian</td>" +
				" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_KEMENTERIAN")+"</td></tr>" ;

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
				" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_PENUH")+"</td></tr>" ;

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

	//	content+= " <strong>Sila buat tindakan selanjutnya. </strong>  <br><br>";

		content+= " Sila hubungi Pegawai IT di Jabatan anda jika memerlukan sebarang bantuan berkaitan perkara diatas. <br><br>";

		content+= " Sekian, terima kasih.<br><br><br>";

		content+= " Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas. <br>";

	email.SUBJECT = subject;
	email.MESSAGE = content;

	String emelDaftar = (String) viewPengguna.get("EMEL");

	email.RECIEPIENT = emelDaftar;


	//email.TO_CC = new String[1];
	//email.TO_CC[0] = "razman.zainal@gmail.com";
	email.sendEmail();


}

//tambah function emel untuk user KJP
public void hantarEmelStaff(HttpSession session,String USER_ID,Integer STATUS) throws Exception {

	myLogger.info("MASUK FUNCTION EMEL STAFF KJP user id : "+ USER_ID);

	EmailProperty pro = EmailProperty.getInstance();
	EmailSender email = EmailSender.getInstance();
	email.FROM = pro.getFrom();
	Hashtable viewPengguna = viewDataPenggunaKJP(session, USER_ID, "");


	String subject = "";
	String content = "";


		subject = " Sistem MyeTaPP - Pengguna Baru KJP telah didaftarkan di dalam sistem. ";
		content = " Assalamualaikum dan Salam Sejahtera. <br><br>";
		content+= " Untuk makluman, sistem telah mendaftarkan satu pengguna baru KJP. <br>";
		content+= " Maklumat lanjut adalah seperti berikut : <br><br>";
		content+= " Maklumat Pendaftaran Pengguna KJP : <br>";

		content+= "<table width='100%' border='0' cellpadding='2' cellspacing='2'>";

		content+= "<tr><td valign='top'  width='1%'></td><td valign='top'  width='14%'></td>" +
				" <td valign='top'  width='1%'></td><td valign='top'  width='84%'></td></tr>" +
				" <tr><td valign='top'></td><td valign='top'>Nama Kementerian</td>" +
				" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_KEMENTERIAN")+"</td></tr>" ;

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
				" <td valign='top' >:</td><td valign='top'>"+viewPengguna.get("NAMA_PENUH")+"</td></tr>" ;

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

//DIBA TAMBAH
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



		/*if (!ID_JENISPEJABAT.equals("")){

			sql += " AND I.ID_JENISPEJABAT = "+ID_JENISPEJABAT;
		}*/

		if (!ID_NEGERI.equals("")){

			sql += " AND P.ID_NEGERI = "+ID_NEGERI;
		}

		if (!ID_JENISPEJABAT.equals("")){

			sql += " AND JP.ID_JENISPEJABAT = "+ID_JENISPEJABAT;
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

//diba tambah integrasi
@SuppressWarnings("unchecked")
public Hashtable viewPejabatIntegrasi(HttpSession session,String ID_PEJABAT)throws Exception {
	Db db = null;
	ResultSet rs = null;
	Statement stmt = null;
	String sql = "";
	Hashtable h;
	h = new Hashtable();
	try {
		db = new Db();
		stmt = db.getStatement();

		if (ID_PEJABAT!= null && !ID_PEJABAT.equals("")){

		sql = 	" SELECT I.ID_INTEGRASI, I.ID_JENISINTEG, I.ID_KATEGORI, UPPER(I.NAMA) AS NAMA, " +
				" UPPER(I.NAMA_LAIN) AS NAMA_LAIN, I.KOD_AGENSI, " +
				" I.PENERANGAN, I.STATUS, UPPER(I.NAMA_PEMILIK) AS NAMA_PEMILIK, UPPER(I.ALAMAT1) AS ALAMAT1, " +
				" UPPER(I.ALAMAT2) AS ALAMAT2, UPPER(I.ALAMAT3) AS ALAMAT3, I.POSKOD, I.ID_NEGERI, " +
				" I.NO_TEL, I.EMEL, I.CATATAN, I.FLAG_AKTIF, TO_CHAR(I.TARIKH_MASUK,'DD/MM/YYYY') TARIKH_MASUK," +
				" I.ID_MASUK, TO_CHAR(I.TARIKH_KEMASKINI,'DD/MM/YYYY') TARIKH_KEMASKINI, " +
				" I.ID_KEMASKINI, N.NAMA_NEGERI, U.USER_NAME, I.ID_JENISINTEG AS ID_JENISINTEG_INT, " +
				" UPPER(I.NAMA_LAIN_PEMILIK) AS NAMA_LAIN_PEMILIK, I.KATEG_PEMILIK "+
				" FROM TBLRUJINTEGRASI I, TBLRUJNEGERI N, USERS U " +
				" WHERE I.ID_NEGERI = N.ID_NEGERI " +
				" AND I.ID_MASUK = U.USER_ID " ;



			sql += " AND I.ID_INTEGRASI = "+ ID_PEJABAT;
		}


		myLogger.info(" V3: SQL viewPejabatIntegrasi :"+ sql);

		rs = stmt.executeQuery(sql);

		while (rs.next()) {

			h.put("ID_INTEGRASI",rs.getString("ID_INTEGRASI") == null ? "" : rs.getString("ID_INTEGRASI"));
			h.put("KOD_AGENSI",rs.getString("KOD_AGENSI") == null ? "-" : rs.getString("KOD_AGENSI"));
			h.put("NAMA",rs.getString("NAMA") == null ? "-" : rs.getString("NAMA"));
			h.put("NAMA_LAIN",rs.getString("NAMA_LAIN") == null ? "-" : rs.getString("NAMA_LAIN"));
			h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
			h.put("ID_KATEGORI",rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
			h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));

			h.put("ID_JENISINTEG",rs.getString("ID_JENISINTEG") == null ? "" : rs.getString("ID_JENISINTEG"));
			h.put("PENERANGAN",rs.getString("PENERANGAN") == null ? "-" : rs.getString("PENERANGAN"));
			h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
			h.put("NAMA_PEMILIK",rs.getString("NAMA_PEMILIK") == null ? "-" : rs.getString("NAMA_PEMILIK"));
			h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
			h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
			h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));

			h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
			h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
			h.put("NO_TEL",rs.getString("NO_TEL") == null ? "-" : rs.getString("NO_TEL"));
			h.put("EMEL",rs.getString("EMEL") == null ? "-" : rs.getString("EMEL"));
			h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
			h.put("CATATAN",rs.getString("CATATAN") == null ? "-" : rs.getString("CATATAN"));

			h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "-" : rs.getString("TARIKH_MASUK"));
			h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
			h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "-" : rs.getString("TARIKH_KEMASKINI"));
			h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));

			h.put("USER_NAME",rs.getString("USER_NAME") == null ? "-" : rs.getString("USER_NAME"));

			//h.put("NAMA_PEMILIK",rs.getString("NAMA_LAIN_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK"));
			h.put("NAMA_LAIN_PEMILIK",rs.getString("NAMA_LAIN_PEMILIK") == null ? "-" : rs.getString("NAMA_LAIN_PEMILIK"));
			h.put("KATEG_PEMILIK",rs.getString("KATEG_PEMILIK") == null ? "" : rs.getString("KATEG_PEMILIK"));
			h.put("ID_JENISINTEG_INT",rs.getString("ID_JENISINTEG_INT") == null ? "" : rs.getString("ID_JENISINTEG_INT"));
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


	 public String getCountTugasan(HttpSession session, String ID_JAWATAN, String carianTerperinci) throws Exception {

		 Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String role = "";

			sql = " SELECT COUNT (USER_ID) AS TOTALTUGASAN FROM ( ";

			sql += queryUserKJP(session,"", carianTerperinci, "");

			sql	+= " ) ";

			sql += " WHERE ID_JAWATAN =  "+ ID_JAWATAN;

			myLogger.info("sql -- "+sql);

			rs = stmt.executeQuery(sql);
				while (rs.next()) {
					role = rs.getString("TOTALTUGASAN") == null ? "" : rs.getString("TOTALTUGASAN");
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

		public List getStatsJawatanOnline(HttpSession session, String flag_HQ, String carianTerperinci)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List senaraiPenggunaInternal = null;
			String sql = "";



			//open ct
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_"+flag_HQ);
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_"+flag_HQ);
			myLogger.info(" CT_FLAGTEPERINCI_CARIAN ::: "+CT_FLAGTEPERINCI_CARIAN);
			String CT_NAMA = getParam("CT_NAMA_"+flag_HQ);
			String CT_ID_SEKSYEN = getParam("CT_ID_SEKSYEN_"+flag_HQ);
			String CT_ID_NEGERI = getParam("CT_ID_NEGERI_"+flag_HQ);
			String CT_ID_PEJABATJKPTG = getParam("CT_ID_PEJABATJKPTG_"+flag_HQ);
			String CT_ROLE_MAIN = getParam("CT_ROLE_MAIN_"+flag_HQ);
			String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_"+flag_HQ);
			String CT_ID_JAWATAN = getParam("CT_ID_JAWATAN_"+flag_HQ);
			String CT_LOGMASUK_MULA = getParam("CT_LOGMASUK_MULA_"+flag_HQ);
			String CT_LOGMASUK_AKHIR = getParam("CT_LOGMASUK_AKHIR_"+flag_HQ);
			String CT_STATUS_LOG = getParam("CT_STATUS_LOG_"+flag_HQ);


			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
			this.context.put("CT_NAMA", CT_NAMA);
			this.context.put("CT_ID_SEKSYEN", CT_ID_SEKSYEN);
			this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
			this.context.put("CT_ID_PEJABATJKPTG", CT_ID_PEJABATJKPTG);
			this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
			this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
			this.context.put("CT_ID_JAWATAN", CT_ID_JAWATAN);
			this.context.put("CT_ROLE_MAIN", CT_ROLE_MAIN);
			this.context.put("CT_LOGMASUK_MULA", CT_LOGMASUK_MULA);
			this.context.put("CT_LOGMASUK_AKHIR", CT_LOGMASUK_AKHIR);
			this.context.put("CT_STATUS_LOG", CT_STATUS_LOG);
			//close ct
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();

				sql = 	" SELECT COUNT(USER_ID) AS JUMLAH_PENGGUNA, JAWATAN FROM ( ";
				sql += queryUserOnline(session,"", "","");
				sql += " ) GROUP BY JAWATAN ";

				myLogger.info(" viewDataPenggunaOnline :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);

				senaraiPenggunaInternal = Collections.synchronizedList(new ArrayList());
				Map x = null;
				int bil = 0;
				while (rs.next()) {
					x = Collections.synchronizedMap(new HashMap());
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
					h.put("JUMLAH_PENGGUNA",rs.getString("JUMLAH_PENGGUNA") == null ? "" : rs.getString("JUMLAH_PENGGUNA"));
					h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));


					senaraiPenggunaInternal.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
			return senaraiPenggunaInternal;

			}


		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListDoc(HttpSession session, String USER_ID, String flag)throws Exception {

			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listDoc = null;
			String sql = "";

			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT ID_DOKUMENADMIN, ID_REF, FLAG_ADMIN, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
									" TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK , ID_MASUK, " +
									" TO_CHAR(TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, ID_KEMASKINI, KETERANGAN "+
									" FROM TBLRUJDOKUMENADMIN "+
									" WHERE FLAG_ADMIN = '"+ flag +"' "+
									" AND ID_REF = "+ USER_ID;

						myLogger.debug("SQL LIST DOC - "+sql);
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
							h.put("ID_DOKUMENADMIN", rs.getString("ID_DOKUMENADMIN") == null ? "" : rs.getString("ID_DOKUMENADMIN"));
							h.put("ID_REF",rs.getString("ID_REF") == null ? "" : rs.getString("ID_REF"));
							h.put("NAMA_DOKUMEN",rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
							h.put("JENIS_DOKUMEN",rs.getString("JENIS_DOKUMEN") == null ? "" : rs.getString("JENIS_DOKUMEN"));
							h.put("DOKUMEN",rs.getString("DOKUMEN") == null ? "" : rs.getString("DOKUMEN"));
							h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
							h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
							h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
							h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
							h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
							h.put("FLAG_ADMIN",rs.getString("FLAG_ADMIN") == null ? "" : rs.getString("FLAG_ADMIN"));

							listDoc.add(h);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}

					return listDoc;

				}


		private void uploadFiles(String USER_ID) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		    if (isMultipart) {
			    List items = upload.parseRequest(this.request);
			    Iterator itr = items.iterator();
			    while (itr.hasNext()) {
			      FileItem item = (FileItem)itr.next();
			      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
			    	 saveData(item, USER_ID);
			      }
			    }
		    }
		  }

		private void saveData(FileItem item, String USER_ID) throws Exception {
	  		Db db = null;

	  		HttpSession session = this.request.getSession();
	  		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");

	        try {
	        	db = new Db();
	        	long idLampiran = DB.getNextID("TBLRUJDOKUMENADMIN_SEQ");
	        	Connection con = db.getConnection();
	        	con.setAutoCommit(false);
	        	PreparedStatement ps = con.prepareStatement("insert into TBLRUJDOKUMENADMIN " +
	        			" (ID_DOKUMENADMIN, ID_REF, FLAG_ADMIN, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
						" TARIKH_MASUK , ID_MASUK, KETERANGAN) " +
	        			" values(?,?,?,?,?,?,SYSDATE,?,?) ");

	        	ps.setLong(1,idLampiran);
	        	ps.setString(2, USER_ID);
	        	ps.setString(3, "user");
	        	ps.setString(4,item.getName());
	        	ps.setString(5,item.getContentType());
	        	ps.setBinaryStream(6,item.getInputStream(),(int)item.getSize());
	        	ps.setString(7,USER_ID_SYSTEM);
	        	ps.setString(8,getParam("keteranganDokumen"));

	        	ps.executeUpdate();


	            con.commit();
		    } finally {
			      if (db != null) db.close();
		    }
	  }

		public void deleteDokumen(HttpSession session, String USER_ID, String ID_DOKUMENADMIN) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";

			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);

				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();


				if (!ID_DOKUMENADMIN.equals("")){
					r.add("ID_DOKUMENADMIN", ID_DOKUMENADMIN);
					sql = r.getSQLDelete("TBLRUJDOKUMENADMIN");
					AuditTrail.logActivity(this,session,"DEL","DOKUMEN ["+ID_DOKUMENADMIN+"] Deleted");
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


}

