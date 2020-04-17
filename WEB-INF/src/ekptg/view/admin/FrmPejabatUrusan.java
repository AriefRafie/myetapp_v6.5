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
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.view.admin.utils.UsersListConnectionToDB;


	@SuppressWarnings("serial")
	public class FrmPejabatUrusan extends AjaxBasedModule {
		
		static Logger myLog = Logger.getLogger(ekptg.view.admin.FrmPejabatUrusan.class);
		String skrin_name = "app/admin/Utilities/index.jsp";
		
		@Override
		public String doTemplate2() throws Exception {	

			List listPejabatUrusan = null;
			List listPejabat = null;
			Hashtable viewPejabat = null;
			List list_TBLRUJNEGERI = null;
			List list_TBLRUJSEKSYEN = null;
			List list_TBLRUJBANDAR = null;
			List list_TBLRUJDAERAH = null;
			List list_TBLRUJJENISPEJABAT = null;
			List list_TBLRUJJAWATAN = null;

			List ListDaerahJagaanDisplay= null;
			List listDaerahJagaanByIdPejabat= null;
			
			List listPegawaiPPK = null;
			List listPegawaiPPKDisp = null;
			
			Hashtable viewPejabatUrusan = null;
			Hashtable viewDetailsJenisPejabat = null;
			
			Hashtable dataPegawai = null;
			Hashtable viewPegawaiPPK = null;
			
			List statsJKPTGNegeri = null;
			
			List listUserPejabat = null;
			List listStatsJawatan = null;
			
			HttpSession session = this.request.getSession();
			String command = getParam("command");
			
			this.context.put("ID_PEJABAT", "");
			
			this.context.put("carianTerperinci", "");
			
			String cetakReport = "";
			this.context.put("cetakReport", cetakReport);
			this.context.put("selectNegeri", HTML.SelectNegeri("",""));
			/*this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("0","Form_id_daerah", null, null, "onChange=\"doChanges()\" "));*/
			this.context.put("selectSeksyen", HTML.SelectSeksyen("",null, null, ""));
			this.context.put("selectPejabat",getPejabatUrusanLists(""));
			
			myLog.info("Pejabat Urusan command : "+command );
			
			if(command.equals("batalCarianUtama"))
			{
				
				skrin_name = "app/admin/Utilities/index.jsp";
			}
			else if(command.equals("carianTerperinci"))
			{
				
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","0","0");
				this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
				
				list_TBLRUJJENISPEJABAT = listTableRujukanV3(session,"TBLRUJJENISPEJABAT","0","0");
				this.context.put("list_TBLRUJJENISPEJABAT",list_TBLRUJJENISPEJABAT);
				
				String carianTerperinci = getParam("carianTerperinci");
				this.context.put("carianTerperinci",carianTerperinci);
				
				if (mode.equals("1")){
				skrin_name = "app/admin/Utilities/frmPejabat.jsp";
				} else {
					skrin_name = "app/admin/Utilities/frmPejabatCTurus.jsp";	
				}
			}
			else if(command.equals("carianUmum"))
			{
				String action = getParam("action");
				
				String carianTerperinci = getParam("carianTerperinci");
				this.context.put("carianTerperinci",carianTerperinci);
				
				myLog.info("carianTerperinci - "+carianTerperinci);
				
				
				//PEJABAT JKPTG
				listPejabat = getSenaraiJKPTG(session, "1","","","",carianTerperinci);
				setupPageList(session, action, listPejabat, "listPejabat",command,"div_senaraiUtama");
				//this.context.put("PrintlistPejabat",listPejabatUrusan);
				
				 //PEJABAT URUSAN
				listPejabatUrusan = getSenarai(session, "","","","",carianTerperinci);
				setupPageList(session, action, listPejabatUrusan, "listPejabatUrusan",command,"div_senaraiUtama");
				//this.context.put("PrintlistPejabat",listPejabatUrusan);
				
				skrin_name = "app/admin/Utilities/SenaraiUtama.jsp";
			}
			else if(command.equals("showStatsJKPTG"))
			{
				statsJKPTGNegeri = getStatsJKPTG(session);
				this.context.put("statsJKPTGNegeri", statsJKPTGNegeri);
				
				skrin_name = "app/admin/Utilities/statsJKPTG.jsp";
			}
			else if(command.equals("showSenaraiPejabat1"))
			{
				String carianTerperinci = getParam("carianTerperinci");
				this.context.put("carianTerperinci",carianTerperinci);
				
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				String jenisPejabat = getParam("jenisPejabat");
				this.context.put("jenisPejabat",jenisPejabat);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				
				String FlagCari = getParam("FlagCari");
				this.context.put("FlagCari", FlagCari);
				 
				this.context.put("SuccessMesejDeleteUser", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				String JENISPEJ = getParam("JENISPEJ");
				
				if(FLAG_DELETE.equals("Y"))
				{
					String ID_PEJABAT = getParam("ID_PEJABAT");
					deletePengguna(session,ID_PEJABAT,JENISPEJ);
					this.context.put("SuccessMesejDeleteUser", "Maklumat Pejabat Berjaya Dihapus");
				}

				String id_jenispejabat = getParam("ID_JENISPEJABAT_"+mode);

				String id_negeri = getParam("ID_NEGERI_"+mode);
				if (id_negeri.equals("-1")){
					id_negeri = "";
				}
				String id_daerah = getParam("Form_id_daerah"+mode);
				if (id_daerah.equals("-1")){
					id_daerah = "";
				}
				String id_seksyen = getParam("ID_SEKSYEN_"+mode);
				if (id_seksyen.equals("-1")){
					id_seksyen = "";
				}
				
				myLog.info("jenisPejabat cari ---- : "+jenisPejabat );
				
					
				 String action = getParam("action");
				 
				 if (getParam("cetakReport").equals("Y")){ //PEJABAT JKPTG
				
				 listPejabat = getSenaraiJKPTG(session, "1",id_negeri,id_daerah,id_seksyen,carianTerperinci);
				 setupPageList(session, action, listPejabat, "listPejabat",command,"div_JKPTG");
				 this.context.put("PrintlistPejabat",listPejabat);
				 } 
				 else if (FlagCari.equals("Y")){
				listPejabat = getSenaraiJKPTG(session, "1",id_negeri,id_daerah,id_seksyen,carianTerperinci);
				setupPageList(session, action, listPejabat, "listPejabat",command,"div_JKPTG");
				//this.context.put("PrintlistPejabat",listPejabat);	 
				 }else{
				listPejabat = getSenaraiJKPTG(session, "1","","","","");
				setupPageList(session, action, listPejabat, "listPejabat",command,"div_JKPTG");
							 
				 }
				 
				 if(getParam("FlagCetak").equals("Y"))
					{
					// myLog.info("FlagCetak 1 -- "+getParam("FlagCetak"));
				skrin_name = "app/admin/Utilities/SenaraiPejabat_Print.jsp";
					
					}else{
						//myLog.info("FlagCetak 2 -- "+getParam("FlagCetak"));
				skrin_name = "app/admin/Utilities/SenaraiPejabat.jsp";
					}
				 
				
				
			}
			else if(command.equals("showSenaraiPejabat2"))
			{
				String carianTerperinci = getParam("carianTerperinci");
				this.context.put("carianTerperinci",carianTerperinci);
				
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				String jenisPejabat = getParam("jenisPejabat");
				this.context.put("jenisPejabat",jenisPejabat);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				 
				this.context.put("SuccessMesejDeleteUser", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				String JENISPEJ = getParam("JENISPEJ");
				
				if(FLAG_DELETE.equals("Y"))
				{
					String ID_PEJABAT = getParam("ID_PEJABAT");
					deletePengguna(session,ID_PEJABAT,JENISPEJ);
					this.context.put("SuccessMesejDeleteUser", "Maklumat Pejabat Berjaya Dihapus");
				}

				String id_jenispejabat = getParam("ID_JENISPEJABAT_"+mode);

				String id_negeri = getParam("ID_NEGERI_"+mode);
				if (id_negeri.equals("-1")){
					id_negeri = "";
				}
				String id_daerah = getParam("Form_id_daerah"+mode);
				if (id_daerah.equals("-1")){
					id_daerah = "";
				}
				String id_seksyen = getParam("ID_SEKSYEN_"+mode);
				if (id_seksyen.equals("-1")){
					id_seksyen = "";
				}
				
				myLog.info("jenisPejabat cari ---- : "+jenisPejabat );
				
					
				 String action = getParam("action");
				
				 if (getParam("cetakReport").equals("Y")){ //PEJABAT URUSAN

				 listPejabatUrusan = getSenarai(session,id_jenispejabat,id_negeri,id_daerah,id_seksyen,carianTerperinci);
				 setupPageList(session, action, listPejabatUrusan, "listPejabatUrusan",command,"div_URUSAN");
				 this.context.put("PrintlistPejabat",listPejabatUrusan);
				 }else{
				listPejabatUrusan = getSenarai(session,id_jenispejabat,id_negeri,id_daerah,id_seksyen,carianTerperinci);
				setupPageList(session, action, listPejabatUrusan, "listPejabatUrusan",command,"div_URUSAN"); 
				 }
				 
				 if(getParam("FlagCetak").equals("Y"))
					{
					// myLog.info("FlagCetak 1 -- "+getParam("FlagCetak"));
				skrin_name = "app/admin/Utilities/SenaraiPejabat_Print.jsp";
					
					}else{
						//myLog.info("FlagCetak 2 -- "+getParam("FlagCetak"));
				skrin_name = "app/admin/Utilities/SenaraiPejabatUrusan.jsp";
					}
				 
				 
			
			}else if(command.equals("tambahJenisPejabat")){
				
				String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
				//myLog.info("ID_JENISPEJABAT ---- : "+ID_JENISPEJABAT );
				
				viewDetailsJenisPejabat = viewDetailsJenisPejabat(session, ID_JENISPEJABAT);
				this.context.put("viewDetailsJenisPejabat", viewDetailsJenisPejabat);
				
				skrin_name = "app/admin/Utilities/frmAddJenisPejabat.jsp";
			}
			else if(command.equals("simpanJenisPejabat")){
				
				String ID_PEJABAT = getParam("ID_PEJABAT");
				//myLog.info("ID_PEJABAT ---- : "+ID_PEJABAT );
				
				String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
				//myLog.info("ID_JENISPEJABAT ---- : "+ID_JENISPEJABAT );
				
				if(ID_JENISPEJABAT==null || ID_JENISPEJABAT.equals("") || ID_JENISPEJABAT.equals("null")){
					System.out.println("ID_JENISPEJABAT insert? -- "+ID_JENISPEJABAT);
					ID_JENISPEJABAT = saveJenisPejabat(session, ID_JENISPEJABAT,ID_PEJABAT);
					System.out.println("ID_JENISPEJABAT after insert? -- "+ID_JENISPEJABAT);
					
					} else{
						
					System.out.println("ID_JENISPEJABAT update? -- "+ID_JENISPEJABAT);
					ID_JENISPEJABAT = saveJenisPejabat(session, ID_JENISPEJABAT,ID_PEJABAT);
					System.out.println("ID_JENISPEJABAT after update? -- "+ID_JENISPEJABAT);

					}
				
				viewDetailsJenisPejabat = viewDetailsJenisPejabat(session, ID_JENISPEJABAT);
				this.context.put("viewDetailsJenisPejabat", viewDetailsJenisPejabat);
				
				skrin_name = "app/admin/Utilities/frmViewJenisPejabat.jsp";
			}
			else if(command.equals("checkKodJenisPejabat"))
			{			
				String ID_PEJABAT = getParam("ID_PEJABAT");
				//myLog.info("ID_PEJABAT ---- : "+ID_PEJABAT );
				
				String KOD_JENIS_PEJABAT = getParam("KOD_JENIS_PEJABAT_"+ID_PEJABAT);
				//myLog.info("KOD_JENIS_PEJABAT ---- : "+KOD_JENIS_PEJABAT );
				
				boolean checkKod = checkKodJenisPjbt(session, KOD_JENIS_PEJABAT);

				String mesej = "";
					if(checkKod==false)
					{
						mesej = "<font color='red' class='blink' >Kod Jenis Pejabat Telah Wujud!Sila Pilih Kod Lain.</font>";
					}else {
						mesej = "<font color='black' class='blink' >Kod Jenis Pejabat Belum Wujud!</font>";	
					}
					
					this.context.put("mesej", mesej);
					this.context.put("checkKod", checkKod);
					
					skrin_name = "app/admin/Utilities/checkKodJenisPejabat.jsp";
			}
			
			else if(command.equals("showListJenisPejabat"))
			{
				String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
				this.context.put("ID_JENISPEJABAT",ID_JENISPEJABAT);
				
				list_TBLRUJJENISPEJABAT = listTableRujukanV3(session,"TBLRUJJENISPEJABAT",ID_JENISPEJABAT,"0");
				this.context.put("list_TBLRUJJENISPEJABAT",list_TBLRUJJENISPEJABAT);
				
				skrin_name = "app/admin/Utilities/showListJenisPejabat.jsp";
			}
			else if(command.equals("showViewJenisPejabat"))
			{
				String ID_JENISPEJABAT = getParam("ID_JENISPEJABAT");
				this.context.put("ID_JENISPEJABAT",ID_JENISPEJABAT);
				
				viewDetailsJenisPejabat = viewDetailsJenisPejabat(session, ID_JENISPEJABAT);
				this.context.put("viewDetailsJenisPejabat", viewDetailsJenisPejabat);
				
				skrin_name = "app/admin/Utilities/frmViewJenisPejabat.jsp";
			}
			else if(command.equals("tambahPegawaiSPPK"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				String JENISPEJ = getParam("JENISPEJ");
				this.context.put("JENISPEJ",JENISPEJ);
				
				listPegawaiPPK = listPegawaiPPKDropdown(session, ID_PEJABAT);
				this.context.put("listPegawaiPPK", listPegawaiPPK);	
				
				/*viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);*/
				
				skrin_name = "app/admin/Utilities/tambahPegawaiSPPK.jsp";
			}
			else if(command.equals("editPegawaiSPPK"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				String JENISPEJ = getParam("JENISPEJ");
				this.context.put("JENISPEJ",JENISPEJ);
				
				String ID_UNITPSK = getParam("ID_UNITPSK");
				this.context.put("ID_UNITPSK",ID_UNITPSK);
				
				listPegawaiPPK = listPegawaiPPKDropdown(session, ID_PEJABAT);
				this.context.put("listPegawaiPPK", listPegawaiPPK);	
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);
				
				viewPegawaiPPK = getPegawaiPPK(session, ID_UNITPSK, ID_PEJABAT);
				this.context.put("viewPegawaiPPK", viewPegawaiPPK);
				
				UsersListConnectionToDB ULCD = new UsersListConnectionToDB();
				String idJawatanPegawai = ULCD.getIdJawatanByDesc((String)viewPegawaiPPK.get("JAWATAN"));
				this.context.put("idJawatanPegawai", idJawatanPegawai);
				
				list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","0","0");
				this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
				
				skrin_name = "app/admin/Utilities/editPegawaiSPPK.jsp";
			}
			else if(command.equals("viewPejabatUrusan"))
			{
				
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				String ID_PEJABAT = getParam("ID_PEJABAT");
				String ID_NEGERI = getParam("ID_NEGERI");
				String JENISPEJ = getParam("JENISPEJ");
				this.context.put("JENISPEJ", JENISPEJ);
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);

				//myLog.info("ID_PEJABAT cari ---- : "+ID_PEJABAT );
				//myLog.info("ID_NEGERI cari ---- : "+ID_NEGERI );
				
				ListDaerahJagaanDisplay = listDaerahJagaanDisplay(session, ID_PEJABAT,ID_NEGERI,JENISPEJ);
				this.context.put("ListDaerahJagaanDisplay", ListDaerahJagaanDisplay);
				
				listPegawaiPPKDisp = listPegawaiPPKDisp(session, ID_PEJABAT);
				this.context.put("listPegawaiPPKDisp", listPegawaiPPKDisp);	
				
				if (mode.equals("1")){
				skrin_name = "app/admin/Utilities/frmViewPejabat.jsp";
				}else {
					skrin_name = "app/admin/Utilities/frmViewPejabatUrusan.jsp";	
				}
			}
			else if(command.equals("editPejabat"))
			{
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				String JENISPEJ = getParam("JENISPEJ");
				this.context.put("JENISPEJ",JENISPEJ);
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","0","0");
				this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
				
				list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDAR","0","0");
				this.context.put("list_TBLRUJBANDAR",list_TBLRUJBANDAR);
				
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH","0","0");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				list_TBLRUJJENISPEJABAT = listTableRujukanV3(session,"TBLRUJJENISPEJABAT","0","0");
				this.context.put("list_TBLRUJJENISPEJABAT",list_TBLRUJJENISPEJABAT);
				
				
				skrin_name = "app/admin/Utilities/frmEditPejabat.jsp";
			}
			else if(command.equals("showListPejabat"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				myLog.info("ID_PEJABAT : "+ID_PEJABAT);
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				//myLog.info("ID_NEGERI : "+ID_NEGERI);
				
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAHBYNEGERI",ID_NEGERI,"0");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				skrin_name = "app/admin/Utilities/showListDaerah.jsp";
			}
			else if(command.equals("showListBandar"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT",ID_PEJABAT);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				myLog.info("ID_NEGERI : "+ID_NEGERI);
				
				String ID_DAERAH = getParam("ID_DAERAH");
				myLog.info("ID_DAERAH : "+ID_DAERAH);
				
				list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDARBYNEGERI",ID_NEGERI,ID_DAERAH);
				this.context.put("list_TBLRUJBANDAR",list_TBLRUJBANDAR);
				
				skrin_name = "app/admin/Utilities/showListBandar.jsp";
			}
			
			else if(command.equals("savePejabat"))
			{
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				
				String JENISPEJ = "";
						
				if (ID_PEJABAT.equals("")){
				 JENISPEJ = getParam("JENISPEJ");
				 this.context.put("JENISPEJ", JENISPEJ);
				 
				}
				else{
				
					JENISPEJ = getParam("jenisPejabatIns_"+ID_PEJABAT);
					this.context.put("JENISPEJ", JENISPEJ);
				}
				
				
				System.out.println("JENISPEJ? -- "+JENISPEJ);
				//System.out.println("ID_PEJABAT? -- "+ID_PEJABAT);
				
				if(ID_PEJABAT==null || ID_PEJABAT.equals("") || ID_PEJABAT.equals("null")){
				System.out.println("ID_PEJABAT insert? -- "+ID_PEJABAT);
				
				ID_PEJABAT = insertDataPejabat(session, ID_PEJABAT,JENISPEJ);
				System.out.println("ID_PEJABAT after insert? -- "+ID_PEJABAT);
				
				} else{
					
				System.out.println("ID_PEJABAT update? -- "+ID_PEJABAT);
				ID_PEJABAT = saveDataPejabat(session, ID_PEJABAT,JENISPEJ);
				System.out.println("ID_PEJABAT after update? -- "+ID_PEJABAT);

				}
				System.out.println("JENISPEJ? -- "+JENISPEJ);
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);		
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				list_TBLRUJSEKSYEN = listTableRujukanV3(session,"TBLRUJSEKSYEN","0","0");
				this.context.put("list_TBLRUJSEKSYEN",list_TBLRUJSEKSYEN);
				
				list_TBLRUJBANDAR = listTableRujukanV3(session,"TBLRUJBANDAR","0","0");
				this.context.put("list_TBLRUJBANDAR",list_TBLRUJBANDAR);
				
				list_TBLRUJDAERAH = listTableRujukanV3(session,"TBLRUJDAERAH","0","0");
				this.context.put("list_TBLRUJDAERAH",list_TBLRUJDAERAH);
				
				list_TBLRUJJENISPEJABAT = listTableRujukanV3(session,"TBLRUJDAERAH","0","0");
				this.context.put("list_TBLRUJJENISPEJABAT",list_TBLRUJJENISPEJABAT);
				
				ListDaerahJagaanDisplay = listDaerahJagaanDisplay(session, ID_PEJABAT,(String) viewPejabat.get("ID_NEGERI"),JENISPEJ);
				this.context.put("ListDaerahJagaanDisplay", ListDaerahJagaanDisplay);
				
				listPegawaiPPKDisp = listPegawaiPPKDisp(session, ID_PEJABAT);
				this.context.put("listPegawaiPPKDisp", listPegawaiPPKDisp);	
				
				if (mode.equals("1")){
				
				skrin_name = "app/admin/Utilities/frmViewPejabat.jsp";
				} else {
					
					skrin_name = "app/admin/Utilities/frmViewPejabatUrusan.jsp";	
				}
			}	
			
			else if(command.equals("close_Pejabat")){
			
				skrin_name = "app/admin/Utilities/blank_viewPejabat.jsp";
			}
			
			else if(command.equals("showDisplayDaerahJagaan"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				String JENISPEJ = getParam("JENISPEJ");
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);
				
			//	myLog.info("ID_PEJABAT cari ---- : "+ID_PEJABAT );
				
				ListDaerahJagaanDisplay = listDaerahJagaanDisplay(session, ID_PEJABAT, ID_NEGERI,JENISPEJ);
				this.context.put("ListDaerahJagaanDisplay", ListDaerahJagaanDisplay);	
				
			//	myLog.info("ListDaerahJagaanDisplay cari ---- : "+ListDaerahJagaanDisplay );
				
				skrin_name = "app/admin/Utilities/showDisplayDaerahJagaan.jsp";
			}
			
			else if(command.equals("editAddDaerahJagaan"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				
				String ID_NEGERI = getParam("ID_NEGERI");
				this.context.put("ID_NEGERI", ID_NEGERI);
				
				String JENISPEJ = getParam("JENISPEJ");
				this.context.put("JENISPEJ", JENISPEJ);
				
				String ID_SEKSYEN = getParam("ID_SEKSYEN");
				this.context.put("ID_SEKSYEN", ID_SEKSYEN);
				
				myLog.info("ID_SEKSYEN edit daerah jagaan -- "+ ID_SEKSYEN);
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);
				
				viewPejabatUrusan = viewDataPejabatUrusan(session, ID_PEJABAT);
				this.context.put("viewPejabatUrusan", viewPejabatUrusan);

				if (ID_SEKSYEN.equals("2")){
				listDaerahJagaanByIdPejabat = listDaerahJagaanByIdPejabat2(session, ID_PEJABAT, ID_NEGERI, JENISPEJ);
				this.context.put("listDaerahJagaanByIdPejabat", listDaerahJagaanByIdPejabat);
				
				skrin_name = "app/admin/Utilities/editAddDaerahJagaanPusakaHQ.jsp";
				
				} else {
				listDaerahJagaanByIdPejabat = listDaerahJagaanByIdPejabat(session, ID_PEJABAT, ID_NEGERI, JENISPEJ);
				this.context.put("listDaerahJagaanByIdPejabat", listDaerahJagaanByIdPejabat);	
				
				skrin_name = "app/admin/Utilities/editAddDaerahJagaan.jsp";
				}
				
			}
			
			else if(command.equals("saveDaerahJagaan"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				myLog.info("ID_PEJABAT -- "+ ID_PEJABAT);
				String ID_NEGERI = getParam("ID_NEGERI"+ID_PEJABAT);
				//myLog.info("ID_NEGERI -- "+ ID_NEGERI);
				String JENIS_PEJABAT = request.getParameter("JENIS_PEJABAT"+ID_PEJABAT);
				myLog.info("JENIS_PEJABAT -- "+ JENIS_PEJABAT);
				String ID_DAERAH = request.getParameter("ID_DAERAH"+ID_PEJABAT);
				//myLog.info("ID_DAERAH -- "+ ID_DAERAH);
				String ID_SEKSYEN = request.getParameter("ID_SEKSYEN"+ID_PEJABAT);
				//myLog.info("ID_SEKSYEN -- "+ ID_SEKSYEN);
				
				String[] CHECKLIST_ = request.getParameterValues("CHECKLIST_"+ID_PEJABAT);
				
				int total_daerah_update = 0;
				total_daerah_update += saveDaerahJagaanbyPejabat(session, ID_PEJABAT, ID_NEGERI, JENIS_PEJABAT, ID_DAERAH, ID_SEKSYEN, CHECKLIST_);
				
				ListDaerahJagaanDisplay = listDaerahJagaanDisplay(session, ID_PEJABAT, ID_NEGERI,JENIS_PEJABAT);
				this.context.put("ListDaerahJagaanDisplay", ListDaerahJagaanDisplay);	
				
				skrin_name = "app/admin/Utilities/showDisplayDaerahJagaan.jsp";
			
			}	
			else if(command.equals("cetakListPejabat"))
			{
				String mode = getParam("mode");
				this.context.put("mode", mode);
				
				String carianTerperinci = getParam("carianTerperinci");
				this.context.put("carianTerperinci",carianTerperinci);
				
				String jenisPejabat = getParam("jenisPejabat");
				this.context.put("jenisPejabat",jenisPejabat);
				
				cetakReport = getParam("cetakReport");
				this.context.put("cetakReport", cetakReport);
				 
				this.context.put("SuccessMesejDeleteUser", "");
				String FLAG_DELETE = getParam("FLAG_DELETE");
				String JENISPEJ = getParam("JENISPEJ");
				
				if(FLAG_DELETE.equals("Y"))
				{
					String ID_PEJABAT = getParam("ID_PEJABAT");
					deletePengguna(session,ID_PEJABAT,JENISPEJ);
					this.context.put("SuccessMesejDeleteUser", "Maklumat Pejabat Berjaya Dihapus");
				}

				String id_jenispejabat = getParam("ID_JENISPEJABAT_"+mode);

				String id_negeri = getParam("ID_NEGERI_"+mode);
				if (id_negeri.equals("-1")){
					id_negeri = "";
				}
				String id_daerah = getParam("Form_id_daerah"+mode);
				if (id_daerah.equals("-1")){
					id_daerah = "";
				}
				String id_seksyen = getParam("ID_SEKSYEN_"+mode);
				if (id_seksyen.equals("-1")){
					id_seksyen = "";
				}
				
				myLog.info("jenisPejabat cari ---- : "+jenisPejabat );
				
					
				 String action = getParam("action");
				 
				 if (mode.equals("1")){
					 
					 myLog.info("sini 1 - ");
				
				listPejabat = getSenaraiJKPTG(session, "1",id_negeri,id_daerah,id_seksyen,carianTerperinci);
				//setupPageList(session, action, listPejabat, "listPejabat",command,"div_JKPTG");
				this.context.put("PrintlistPejabat",listPejabat);
				
				this.context.put("jenisPejabatReport","JKPTG");
				
				skrin_name = "app/admin/Utilities/SenaraiPejabat_Print.jsp";
				
				} else {
					
					myLog.info("sini 2 - ");
					 
				listPejabatUrusan = getSenarai(session,id_jenispejabat,id_negeri,id_daerah,id_seksyen,carianTerperinci);
				//setupPageList(session, action, listPejabatUrusan, "listPejabatUrusan",command,"div_URUSAN");
				this.context.put("PrintlistPejabat",listPejabatUrusan);
				
				this.context.put("jenisPejabatReport","Urusan");
				
				skrin_name = "app/admin/Utilities/SenaraiPejabat2_Print.jsp";
				
				 }
				
				
			}
			else if(command.equals("getPegawaiData"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				myLog.info("ID_PEJABAT - "+ID_PEJABAT);
				String jenisPejabat = getParam("JENISPEJ");
				this.context.put("jenisPejabat",jenisPejabat);
				myLog.info("jenisPejabat - "+jenisPejabat);
				
				String USER_ID = getParam("USER_ID");
				myLog.info("USER_ID - "+USER_ID);
				
				String seperator = "/";
				
				/*String USER_NAME = USER_ID2.substring(0, USER_ID2.indexOf(seperator)).trim();
				this.context.put("USER_NAME", USER_NAME);
				
				String USER_ID = USER_ID2.substring(USER_ID2.indexOf(seperator)+1).trim();
				this.context.put("USER_ID", USER_ID);*/
				
				String JENISPEJ = getParam("JENISPEJ");
				this.context.put("JENISPEJ", JENISPEJ);
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,JENISPEJ);
				this.context.put("viewPejabat", viewPejabat);
				
				dataPegawai = getUserData(session,USER_ID);
				this.context.put("dataPegawai", dataPegawai);
				
				list_TBLRUJJAWATAN = listTableRujukanV3(session,"TBLRUJJAWATAN","0","0");
				this.context.put("list_TBLRUJJAWATAN",list_TBLRUJJAWATAN);
				
				skrin_name = "app/admin/Utilities/hiddenPaparUser.jsp";
			
			}
			
			else if(command.equals("simpanPegawaiSPPK"))
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				myLog.info("ID_PEJABAT -- "+ ID_PEJABAT);
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				
				
				String ID_RUJPPK = getParam("ID_RUJPPK");
				this.context.put("ID_RUJPPK", ID_RUJPPK);
				
				if(ID_RUJPPK==null || ID_RUJPPK.equals("") || ID_RUJPPK.equals("null")){
					System.out.println("ID_RUJPPK insert? -- "+ID_RUJPPK);
					ID_RUJPPK = savePegawaiPPK(session, ID_PEJABAT,ID_RUJPPK);
					System.out.println("ID_RUJPPK after insert? -- "+ID_RUJPPK);
					
					} else{
						
					System.out.println("ID_RUJPPK update? -- "+ID_RUJPPK);
					ID_RUJPPK = savePegawaiPPK(session, ID_PEJABAT,ID_RUJPPK);
					System.out.println("ID_RUJPPK after update? -- "+ID_RUJPPK);

					}
				
				listPegawaiPPKDisp = listPegawaiPPKDisp(session, ID_PEJABAT);
				this.context.put("listPegawaiPPKDisp", listPegawaiPPKDisp);	
				
				skrin_name = "app/admin/Utilities/showDisplayPegawaiPPK.jsp";
			
			}
			else if(command.equals("showDisplayPegawaiSPPK")) 
			{
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				
				if (getParam("FLAG_DELETE").equals("Y")){
					
					String ID_UNITPSK = getParam("ID_UNITPSK");
					deletePegawaiSPPK(session,ID_UNITPSK);
				}
				
				listPegawaiPPKDisp = listPegawaiPPKDisp(session, ID_PEJABAT);
				this.context.put("listPegawaiPPKDisp", listPegawaiPPKDisp);	
				
				skrin_name = "app/admin/Utilities/showDisplayPegawaiPPK.jsp";
			}
			else if(command.equals("cetakPengguna")) 
			{
				this.context.put("viewPejabat", "");
				
				String action = getParam("action");
				
				String mode = getParam("mode");
				this.context.put("mode",mode);
				
				String ID_PEJABAT = getParam("ID_PEJABAT");
				this.context.put("ID_PEJABAT", ID_PEJABAT);
				
				viewPejabat = viewDataPejabat(session, ID_PEJABAT,mode);
				this.context.put("viewPejabat", viewPejabat);
				
				listStatsJawatan = getStatsJawatan(session,ID_PEJABAT,mode);
				//setupPageList(session, action, listUserPejabat, "listStatsJawatan",command,"senaraiUser");
				this.context.put("listStatsJawatan",listStatsJawatan);
								
				listUserPejabat = getSenaraiUser(session,ID_PEJABAT,mode);
				setupPageList(session, action, listUserPejabat, "PrintlistUserPejabat",command,"senaraiUser");
				this.context.put("PrintlistUserPejabat",listUserPejabat);
				
				if (mode.equals("1")){
				this.context.put("jenisPejabatReport","JKPTG");
				}else {
				this.context.put("jenisPejabatReport","Urusan");
				}

				skrin_name = "app/admin/Utilities/SenaraiPenggunaPejabat.jsp";
			}
			
			return skrin_name;
		}
		
		
		public void deletePegawaiSPPK(HttpSession session, String ID_UNITPSK) throws Exception{
						
			Connection conn = null;
			Db db = null;
			String sql = "";
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "DELETE FROM TBLPPKRUJUNIT WHERE ID_UNITPSK = "+ ID_UNITPSK;
				
				myLog.info(sql);
				stmt.executeUpdate(sql);
				
			} catch (Exception e) {
				e.printStackTrace();
					}finally  {
						if (db != null) db.close();
					}
						
				}
		
		
		@SuppressWarnings("unchecked")
		public Hashtable getUserData (HttpSession session, String USER_ID) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!USER_ID.equals(""))
				{
					sql = 	" SELECT U.USER_ID, U.USER_NAME, UI.ID_JAWATAN, UI.EMEL " +
							" FROM USERS U, TBLRUJPEJABATJKPTG P, USERS_INTERNAL UI " +
							" WHERE U.USER_ID = UI.USER_ID " +
							" AND UI.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG " +
							" AND U.USER_ID = "+ USER_ID;
					
					myLog.info(" viewDataPegawai :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
						h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						
					}
				}
				else
				{
					h.put("USER_ID","");
					h.put("USER_NAME","");
					h.put("ID_JAWATAN","");
					h.put("EMEL","");
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
		
		public void deletePengguna(HttpSession session,String ID_PEJABAT, String JENISPEJ) throws Exception {
			Connection conn = null;
			Db db = null;
			String sql = "";
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				boolean deleteTableUtama = false;
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				myLog.info("Masuk delete JENISPEJ == "+ JENISPEJ);
				
				if (JENISPEJ.equals("1")){
					
					/*r.add("ID_PEJABATJKPTG", ID_PEJABAT);
					r.add("ID_JENISPEJABAT", 22);
					
					sql = r.getSQLDelete("TBLRUJPEJABATJKPTG");
					deleteTableUtama = true;*/
					
					r.add("ID_PEJABATJKPTG", ID_PEJABAT);
					sql = r.getSQLDelete("TBLRUJPEJABATJKPTG");
					AuditTrail.logActivity(this,session,"DEL","PEJABATJKPTG ["+ID_PEJABAT+"] Deleted");
				
				}else if (JENISPEJ.equals("2")){
					
					/*r.add("ID_PEJABAT", ID_PEJABAT);
					//r.add("ID_JENISPEJABAT", 22);
					
					sql = r.getSQLDelete("TBLRUJPEJABAT");
					deleteTableUtama = false;*/
					r.add("ID_PEJABAT", ID_PEJABAT);
					sql = r.getSQLDelete("TBLRUJPEJABAT");
					AuditTrail.logActivity(this,session,"DEL","PEJABAT ["+ID_PEJABAT+"] Deleted");
					
				}
				
				/*if (deleteTableUtama == true){
				
				r.add("ID_PEJABATJKPTG", ID_PEJABAT);
				sql = r.getSQLDelete("TBLRUJPEJABATJKPTG");
				AuditTrail.logActivity(this,session,"DEL","PEJABATJKPTG ["+ID_PEJABAT+"] Deleted");
				
				} else {
				r.add("ID_PEJABAT", ID_PEJABAT);
				sql = r.getSQLDelete("TBLRUJPEJABAT");
				AuditTrail.logActivity(this,session,"DEL","PEJABAT ["+ID_PEJABAT+"] Deleted");
					
				}*/
				
				stmt.executeUpdate(sql);
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLog.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Delete Pejabat :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
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
		
		@SuppressWarnings("unchecked")
		public Hashtable viewDataPejabatUrusan (HttpSession session, String ID_PEJABAT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_PEJABAT.equals(""))
				{
					sql = 	" SELECT B.ID_PEJABAT, A.ID_PEJABATURUS, B.ID_JENISPEJABAT, B.NAMA_PEJABAT" +
							" FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B" +
							" WHERE A.ID_PEJABATJKPTG = " +ID_PEJABAT+
							" AND A.ID_PEJABATURUS = B.ID_PEJABAT" +
							" AND A.ID_DAERAHURUS = B.ID_DAERAH" +
							" AND A.ID_NEGERIURUS = B.ID_NEGERI" +
							" AND A.ID_JENISPEJABATURUS = B.ID_JENISPEJABAT";
					
					myLog.info(" viewDataPejabatUrusan :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						
						h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
						h.put("ID_PEJABATURUS",rs.getString("ID_PEJABATURUS") == null ? "" : rs.getString("ID_PEJABATURUS"));
						h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
						h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
						
					}
				}
				else
				{
					h.put("ID_PEJABAT","");
					h.put("ID_PEJABATURUS","");
					h.put("NAMA_PEJABAT","");
					h.put("ID_JENISPEJABAT","");
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
		public Hashtable viewDetailsJenisPejabat (HttpSession session, String ID_JENISPEJABAT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_JENISPEJABAT.equals(""))
				{
					
					sql = " SELECT ID_JENISPEJABAT, KOD_JENIS_PEJABAT, NAMA_LAIN_JENIS_PEJABAT, " +
							" KETERANGAN, PENERANGAN_LANJUT FROM TBLRUJJENISPEJABAT " +
						  " WHERE ID_JENISPEJABAT = "+ID_JENISPEJABAT;
					
					rs = stmt.executeQuery(sql);				
									
					while (rs.next()) {
						
						h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
						h.put("KOD_JENIS_PEJABAT",rs.getString("KOD_JENIS_PEJABAT") == null ? "" : rs.getString("KOD_JENIS_PEJABAT").toUpperCase());
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
						h.put("PENERANGAN_LANJUT",rs.getString("PENERANGAN_LANJUT") == null ? "" : rs.getString("PENERANGAN_LANJUT").toUpperCase());
						h.put("NAMA_LAIN_JENIS_PEJABAT",rs.getString("NAMA_LAIN_JENIS_PEJABAT") == null ? "" : rs.getString("NAMA_LAIN_JENIS_PEJABAT").toUpperCase());
					}
				}
				else
				{
					h.put("ID_JENISPEJABAT","");
					h.put("KOD_JENIS_PEJABAT","");
					h.put("KETERANGAN","");
					h.put("PENERANGAN_LANJUT","");
					h.put("NAMA_LAIN_JENIS_PEJABAT","");
				}
				
				myLog.info(" viewDetailsJenisPejabat :" + sql.toUpperCase());
				
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
		
		public boolean checkKodJenisPjbt(HttpSession session, String KOD_JENIS_PEJABAT) throws Exception {
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
				
					sql = " SELECT KOD_JENIS_PEJABAT FROM TBLRUJJENISPEJABAT " +
							" WHERE KOD_JENIS_PEJABAT = '"+KOD_JENIS_PEJABAT+"'" ;
					
					myLog.info(" checkKodJenisPjbt :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);

					while (rs.next()) {
						
						KOD_JENIS_PEJABAT = rs.getString("KOD_JENIS_PEJABAT") == null ? "" : rs.getString("KOD_JENIS_PEJABAT");
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
		public Hashtable getPegawaiPPK (HttpSession session, String ID_UNITPSK, String ID_PEJABAT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_UNITPSK.equals(""))
				{
					sql = 	" SELECT U.USER_ID, PR.USER_ID AS USER_PPK, PR.ID_UNITPSK, PR.ID_JKPTG, PR.NAMA_PEGAWAI, PR.KOD, " +
							" PR.JAWATAN, PR.KETERANGAN_UNIT_PSK, PR.NAMA_PEJABAT, PR.ALAMAT1, PR.ALAMAT2, " +
							" PR.ALAMAT3, PR.POSKOD, PR.ID_BANDAR, PR.ID_NEGERI, PR.NO_TEL, PR.NO_TEL_SAMBUNGAN, PR.EMEL " +
							" FROM TBLPPKRUJUNIT PR, USERS U " +
							" WHERE PR.ID_UNITPSK = "+ ID_UNITPSK +
							" AND PR.USER_ID = U.USER_ID(+) ";
					
					myLog.info(" viewDataPegawai :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						
						h.put("ID_UNITPSK",rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
						h.put("ID_JKPTG",rs.getString("ID_JKPTG") == null ? "" : rs.getString("ID_JKPTG"));
						h.put("NAMA_PEGAWAI",rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
						h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD"));
						//h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
						h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
						h.put("KETERANGAN_UNIT_PSK",rs.getString("KETERANGAN_UNIT_PSK") == null ? "" : rs.getString("KETERANGAN_UNIT_PSK"));
						
						h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT"));
						h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
						h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
						h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
						h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						
						h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
						h.put("NO_TEL_SAMBUNGAN",rs.getString("NO_TEL_SAMBUNGAN") == null ? "" : rs.getString("NO_TEL_SAMBUNGAN"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
						h.put("USER_PPK",rs.getString("USER_PPK") == null ? "" : rs.getString("USER_PPK"));
					}
				}
				else
				{
					h.put("ID_UNITPSK","");
					h.put("ID_JKPTG","");
					h.put("NAMA_PEGAWAI","");
					h.put("KOD","");
					//h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
					h.put("JAWATAN","");
					h.put("KETERANGAN_UNIT_PSK","");
					
					h.put("NAMA_PEJABAT","");
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("ID_BANDAR","");
					h.put("ID_NEGERI","");
					
					h.put("NO_TEL","");
					h.put("NO_TEL_SAMBUNGAN","");
					h.put("EMEL","");
					h.put("USER_ID","");
					h.put("USER_PPK",""); 
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
		public Hashtable viewDataPejabat (HttpSession session, String ID_PEJABAT, String JENISPEJ) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				myLog.info("JENISPEJ -- "+ JENISPEJ);
				
				if(!ID_PEJABAT.equals(""))
				{
					if (JENISPEJ.equals("1")){
						sql = queryPejabatJKPTG(session,ID_PEJABAT);
					} else if (JENISPEJ.equals("2")){
						sql = queryPejabat(session,ID_PEJABAT);
					}
					myLog.info(" viewDataPejabat :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {
						
						h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						h.put("NAMA_PEJABAT",rs.getString("NAMA_PEJABAT") == null ? "" : rs.getString("NAMA_PEJABAT").toUpperCase());
						h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
						h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
						h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
						h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN") == null ? "" : rs.getString("NAMA_SEKSYEN").toUpperCase());
						h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
						h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR").toUpperCase());
						h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
						h.put("NO_FAX",rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
						h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
						h.put("ID_BANDAR",rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));
						h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
						h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
						h.put("JENIS_PEJ",rs.getString("JENIS_PEJ") == null ? "" : rs.getString("JENIS_PEJ").toUpperCase());
						h.put("ID_JAWATAN",rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
						h.put("FLAG_INT",rs.getString("FLAG_INT") == null ? "" : rs.getString("FLAG_INT"));
						h.put("PEJ_URUSAN",rs.getString("PEJ_URUSAN") == null ? "" : rs.getString("PEJ_URUSAN"));
						h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
						
					}
				}
				else
				{
					h.put("ID_PEJABAT","");
					h.put("KETERANGAN","");
					h.put("NAMA_PEJABAT","");
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("NAMA_SEKSYEN","");
					h.put("NAMA_NEGERI","");
					h.put("BANDAR","");
					h.put("FLAG_AKTIF","");
					h.put("NO_TEL","");
					h.put("NO_FAX","");
					h.put("ID_BANDAR","");
					h.put("ID_NEGERI","");
					h.put("ID_SEKSYEN","");
					h.put("ID_DAERAH","");
					h.put("ID_JENISPEJABAT","");
					h.put("JENIS_PEJ","");
					h.put("ID_JAWATAN","");
					h.put("FLAG_INT","");
					h.put("PEJ_URUSAN","");
					
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
		
		public String queryPejabatJKPTG(HttpSession session,String ID_PEJABAT) throws Exception {	
			
			String query = "";
			
					query += " SELECT P.ID_PEJABATJKPTG AS ID_PEJABAT, JP.KETERANGAN, P.NAMA_PEJABAT, P.ALAMAT1, P.ALAMAT2, " +
							 " P.ALAMAT3, P.POSKOD, P.ID_SEKSYEN, S.NAMA_SEKSYEN, N.ID_NEGERI, N.NAMA_NEGERI, B.ID_BANDAR, " +
							 " B.KETERANGAN AS BANDAR, P.NO_TEL, P.NO_FAX, P.EMEL, " +
							 " (CASE WHEN P.FLAG_AKTIF = 'Y' THEN 'AKTIF' " +
							 " WHEN P.FLAG_AKTIF = 'N' THEN 'TIDAK AKTIF' " +
							 " ELSE 'AKTIF' END) AS FLAG_AKTIF, D.ID_DAERAH, P.ID_JENISPEJABAT, JP.KETERANGAN AS PEJ_URUSAN, P.ID_JAWATAN, " +
							 "(CASE WHEN P.ID_JENISPEJABAT IN (21,22,23,24) THEN '1' ELSE '2' END) AS JENIS_PEJ,  P.FLAG_INT " +
							 " FROM  TBLRUJPEJABATJKPTG P, " +
							 " TBLRUJJENISPEJABAT JP, " +
							 " TBLRUJSEKSYEN S, " +
							 " TBLRUJDAERAH D, " +
							 " TBLRUJNEGERI N, " +
							 " TBLRUJBANDAR B " +
							 " WHERE " +
							 " P.ID_DAERAH = D.ID_DAERAH(+) " +
							 " AND P.ID_NEGERI = N.ID_NEGERI(+) " +
							 " AND P.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
							 " AND P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT(+) " +
							 " AND P.ID_BANDAR = B.ID_BANDAR(+) " ;
	

					if(!ID_PEJABAT.trim().equals(""))
					{
						query += " AND P.ID_PEJABATJKPTG = '"+ID_PEJABAT.trim()+"' ";
					}

			return query;
		}
		
		public String queryPejabat(HttpSession session,String ID_PEJABAT) throws Exception {	
			
			String query = "";
			
					query += " SELECT P.ID_PEJABAT, JP.KETERANGAN, P.NAMA_PEJABAT, P.ALAMAT1, P.ALAMAT2, " +
							 " P.ALAMAT3, P.POSKOD, P.ID_SEKSYEN, S.NAMA_SEKSYEN, N.ID_NEGERI, N.NAMA_NEGERI, " +
							 "B.ID_BANDAR, B.KETERANGAN AS BANDAR, P.NO_TEL, P.NO_FAX, P.EMEL, " +
							 " (CASE WHEN P.FLAG_AKTIF = 'Y' THEN 'AKTIF' " +
							 " WHEN P.FLAG_AKTIF = 'N' THEN 'TIDAK AKTIF' " +
							 " ELSE 'AKTIF' END) AS FLAG_AKTIF, D.ID_DAERAH, P.ID_JENISPEJABAT , JP.KETERANGAN AS PEJ_URUSAN ," +
							 " 0 as ID_JAWATAN,  " +
							 "(CASE WHEN P.ID_JENISPEJABAT IN (21,22,23,24) THEN '1' ELSE '2' END) AS JENIS_PEJ,  P.FLAG_INT " +
							 " FROM  TBLRUJPEJABAT P, " +
							 " TBLRUJJENISPEJABAT JP, " +
							 " TBLRUJSEKSYEN S, " +
							 " TBLRUJDAERAH D, " +
							 " TBLRUJNEGERI N, " +
							 " TBLRUJBANDAR B " +
							 " WHERE " +
							 " P.ID_DAERAH = D.ID_DAERAH " +
							 " AND P.ID_NEGERI = N.ID_NEGERI " +
							 " AND P.ID_SEKSYEN = S.ID_SEKSYEN " +
							 " AND P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT " +
							 " AND P.ID_BANDAR = B.ID_BANDAR " ;
	

					if(!ID_PEJABAT.trim().equals(""))
					{
						query += " AND P.ID_PEJABAT = '"+ID_PEJABAT.trim()+"' ";
					}

			return query;
		}
		
		//dropdown carian seksyen
		
		public String getSeksyen(String idseksyen) throws Exception {
			StringBuffer sb = new StringBuffer("");
			String sql="";
			Db db = null;
			sb.append("<option value=>SILA PILIH SEKSYEN</option>");
			try {
				db = new Db();
				sql = "Select id_seksyen, kod_seksyen, nama_seksyen " +
						"from tblrujseksyen where "+  
						" id_seksyen not in (0)";
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				String rs_idseksyen = "";
				while (rs.next()) {
					rs_idseksyen = rs.getString("id_seksyen");
					if (rs_idseksyen.equals(idseksyen)) {
						sb.append("<option selected value="+rs_idseksyen+">");
					} else {
						sb.append("<option value="+rs_idseksyen+">");
					}
					sb.append(rs.getString(2)+" - "+rs.getString(3));
					sb.append("</option>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) db.close();
			}
			return sb.toString();
		}	
		//dropdown carian pejabat urusan
		
		public String getPejabatUrusanLists(String idjenispejabat) throws Exception {
			StringBuffer sb = new StringBuffer("");
			String sql="";
			Db db = null;
			sb.append("<option value=>SILA PILIH PEJABAT URUSAN</option>");
			try {
				db = new Db();
				sql = "Select id_jenispejabat,kod_jenis_pejabat,keterangan " +
						"from tblRujJenisPejabat where "+ //kod_jenis_pejabat not like 'S%' " +
						//"and 
						" id_jenispejabat not in (0) "+
						" ORDER BY KOD_JENIS_PEJABAT ASC ";
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				String rs_idjenispejabat = "";
				while (rs.next()) {
					rs_idjenispejabat = rs.getString("id_jenispejabat");
					if (rs_idjenispejabat.equals(idjenispejabat)) {
						sb.append("<option selected value="+rs_idjenispejabat+">");
					} else {
						sb.append("<option value="+rs_idjenispejabat+">");
					}
					sb.append(rs.getString(2)+" - "+rs.getString(3));
					sb.append("</option>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null) db.close();
			}
			return sb.toString();
		}	
		
		//senarai pejabat
		@SuppressWarnings("unchecked")
		public List getStatsJKPTG(HttpSession session) throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listStats = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT V.ID_NEGERI, V.NAMA_NEGERI, SUM (V.JUMLAHUNIT) JUMLAHUNIT, " +
									" SUM (V.JUMLAHSTAFF) JUMLAHSTAFF FROM (  " +
									" SELECT   COUNT (P.ID_PEJABATJKPTG) AS JUMLAHUNIT, P.ID_NEGERI, N.NAMA_NEGERI, " +
									" 0 AS JUMLAHSTAFF FROM   TBLRUJPEJABATJKPTG P, TBLRUJSEKSYEN S, TBLRUJNEGERI N " +
									" WHERE P.ID_SEKSYEN = S.ID_SEKSYEN AND P.ID_NEGERI = N.ID_NEGERI AND N.ID_NEGERI != 0 " +
									" AND N.ID_NEGERI != 17 GROUP BY   P.ID_NEGERI, N.NAMA_NEGERI" ;
							
							sql+=	" UNION ";
							
							sql+= 	" SELECT 0 AS JUMLAHUNIT, P.ID_NEGERI, N.NAMA_NEGERI, COUNT (UI.USER_ID) AS JUMLAHSTAFF " +
									" FROM TBLRUJPEJABATJKPTG P, TBLRUJSEKSYEN S, TBLRUJNEGERI N, USERS_INTERNAL UI " +
									" WHERE P.ID_SEKSYEN = S.ID_SEKSYEN AND UI.ID_PEJABATJKPTG = P.ID_PEJABATJKPTG " +
									" AND P.ID_NEGERI = N.ID_NEGERI AND N.ID_NEGERI != 0 AND N.ID_NEGERI != 17 " +
									" GROUP BY P.ID_NEGERI, N.NAMA_NEGERI) V " +
									" GROUP BY   ID_NEGERI, NAMA_NEGERI ORDER BY   ID_NEGERI ASC ";
								
					myLog.debug("SQL Laporan statistik JKPTG -- "+sql);
						 rs = stmt.executeQuery(sql);
						
						 listStats = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("JUMLAHUNIT",checkIsNull(rs.getString("JUMLAHUNIT")));
							h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
							h.put("JUMLAHSTAFF",checkIsNull(rs.getString("JUMLAHSTAFF")));
							
						listStats.add(h);
						
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listStats;
					
				}
		
		@SuppressWarnings("unchecked")
		public List getSenaraiJKPTG(HttpSession session, String id_jenispejabat,String id_negeri,
				String id_daerah, String id_seksyen, String carianTerperinci)
				throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			SQLRenderer r = new SQLRenderer();
			List listPejabatUrusan = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = " SELECT P.ID_PEJABATJKPTG AS ID_PEJABAT, P.ID_JENISPEJABAT, " +
								  " P.NAMA_PEJABAT, UPPER(P.ALAMAT1) AS ALAMAT1, UPPER(P.ALAMAT2) AS ALAMAT2, UPPER(P.ALAMAT3) AS ALAMAT3, " +
								  " P.POSKOD, S.NAMA_SEKSYEN, P.ID_NEGERI, N.NAMA_NEGERI, B.KETERANGAN AS BANDAR, " +
								  " (CASE WHEN P.FLAG_AKTIF = 'Y' THEN 'AKTIF' " +
								  " WHEN P.FLAG_AKTIF = 'N' THEN 'TIDAK AKTIF' " +
								  " ELSE 'AKTIF' END) AS FLAG_AKTIF " +
								  " FROM TBLRUJPEJABATJKPTG P, " +
								  " TBLRUJSEKSYEN S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B " +
								  " WHERE P.ID_DAERAH = D.ID_DAERAH " +
								  " AND P.ID_NEGERI = N.ID_NEGERI " +
								  " AND P.ID_BANDAR = B.ID_BANDAR(+) "+
								  " AND P.ID_SEKSYEN = S.ID_SEKSYEN ";
			
					if(!carianTerperinci.equals("") && carianTerperinci != null)
							{
						sql += " AND (";
						
						sql += " UPPER(P.NAMA_PEJABAT) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						sql += " OR UPPER(P.ALAMAT1) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";					
						sql += " OR UPPER(P.ALAMAT2) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						sql += " OR UPPER(P.ALAMAT3) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";					
						sql += " OR UPPER(S.NAMA_SEKSYEN) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						sql += " OR UPPER(N.NAMA_NEGERI) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";					
						sql += " OR UPPER(B.KETERANGAN) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						
						sql += ") ";
						
					}
			
							
						if (id_negeri != null && !"".equals(id_negeri)) {
							sql += " AND P.id_negeri='"+id_negeri+"' ";
						}
						if (id_daerah != null && !"".equals(id_daerah)) {
							sql += " AND P.id_daerah='"+id_daerah+"' ";
						}
						if (id_seksyen != null && !"".equals(id_seksyen)) {
							sql += " AND P.id_seksyen='"+id_seksyen+"' ";
						}
						
						
						sql += "ORDER BY P.id_negeri,P.id_daerah";
						
						myLog.debug("SQL SELECT JKPTG -- "+sql);
						 rs = stmt.executeQuery(sql);
						
						listPejabatUrusan = Collections.synchronizedList(new ArrayList());
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
							
							//String countStaff = getCountStaff(session, rs.getString("ID_PEJABAT"));
					
							h.put("rowCss",rowCss);
							h.put("BIL",bil);
							h.put("ID_PEJABAT",checkIsNull(rs.getString("ID_PEJABAT")));
							h.put("NAMA_PEJABAT",checkIsNull(rs.getString("NAMA_PEJABAT").toUpperCase()));
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI").toUpperCase()));
						//	h.put("daerah",checkIsNull(rs.getString("daerah")));
							h.put("ID_JENISPEJABAT",checkIsNull(rs.getString("ID_JENISPEJABAT")));
							
							h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT2"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT3"))+"\n<br>"+
									checkIsNull(rs.getString("POSKOD"))+"\n<br>"+
									checkIsNull(rs.getString("BANDAR")));
							
							h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYEN").toUpperCase()));
							h.put("KAKI_TANGAN","");
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							
						
							
							listPejabatUrusan.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listPejabatUrusan;
					
				}
		
		@SuppressWarnings("unchecked")
		public List getSenarai(HttpSession session, String id_jenispejabat,String id_negeri,String id_daerah, String id_seksyen, String carianTerperinci)
				throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPejabatUrusan = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
							sql = 	" SELECT P.ID_PEJABAT, UPPER(P.NAMA_PEJABAT) AS NAMA_PEJABAT, UPPER(P.ALAMAT1) AS ALAMAT1, " +
									" UPPER(P.ALAMAT2) AS ALAMAT2, UPPER(P.ALAMAT3) AS ALAMAT3, " +
									" P.POSKOD, S.NAMA_SEKSYEN, P.ID_NEGERI, N.NAMA_NEGERI, B.KETERANGAN AS BANDAR, " +
									" (CASE WHEN P.FLAG_AKTIF = 'Y' THEN 'AKTIF' " +
									" WHEN P.FLAG_AKTIF = 'N' THEN 'TIDAK AKTIF' " +
									" ELSE 'AKTIF' END) AS FLAG_AKTIF, P.ID_JENISPEJABAT " +
									" FROM TBLRUJPEJABAT P,TBLRUJJENISPEJABAT JP, " +
									" TBLRUJSEKSYEN S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B " +
									" WHERE P.ID_DAERAH = D.ID_DAERAH " +
									" AND P.ID_NEGERI = N.ID_NEGERI " +
									" AND P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT "+
									" AND P.ID_BANDAR = B.ID_BANDAR "+
									" AND P.ID_SEKSYEN = S.ID_SEKSYEN ";
						
					if(!carianTerperinci.equals("") && carianTerperinci != null)
							{
						sql += " AND (";
						
						sql += " UPPER(P.NAMA_PEJABAT) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						sql += " OR UPPER(P.ALAMAT1) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";					
						sql += " OR UPPER(P.ALAMAT2) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						sql += " OR UPPER(P.ALAMAT3) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";					
						sql += " OR UPPER(S.NAMA_SEKSYEN) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						sql += " OR UPPER(N.NAMA_NEGERI) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";					
						sql += " OR UPPER(B.KETERANGAN) LIKE UPPER('%"+carianTerperinci.trim().toUpperCase()+"%') ";
						
						sql += ") ";
						
					}
							
						if (id_jenispejabat != null && !"".equals(id_jenispejabat)) {
							sql += " AND P.id_jenispejabat = '"+id_jenispejabat+"' " ;
						}
						if (id_negeri != null && !"".equals(id_negeri)) {
							sql += " AND P.id_negeri='"+id_negeri+"' ";
						}
						if (id_daerah != null && !"".equals(id_daerah)) {
							sql += " AND P.id_daerah='"+id_daerah+"' ";
						}
						if (id_seksyen != null && !"".equals(id_seksyen)) {
							sql += " AND P.id_seksyen='"+id_seksyen+"' ";
						}
						sql += "ORDER BY P.id_negeri,P.id_daerah";
						
						myLog.debug(sql);
						 rs = stmt.executeQuery(sql);
						listPejabatUrusan = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_PEJABAT",checkIsNull(rs.getString("ID_PEJABAT")));
							h.put("NAMA_PEJABAT",checkIsNull(rs.getString("NAMA_PEJABAT")));
							h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							h.put("ID_JENISPEJABAT",checkIsNull(rs.getString("ID_JENISPEJABAT")));
							
							h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT2"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT3"))+"\n<br>"+
									checkIsNull(rs.getString("POSKOD"))+"\n<br>"+
									checkIsNull(rs.getString("BANDAR").toUpperCase()));
							
							h.put("NAMA_SEKSYEN",checkIsNull(rs.getString("NAMA_SEKSYEN").toUpperCase()));
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							
						
							
							listPejabatUrusan.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listPejabatUrusan;
					
				}
		
		public String checkIsNull(String txt) {
			if (txt == null) return "";
			else return txt;
		}
		
		public String saveJenisPejabat(HttpSession session,String ID_JENISPEJABAT, String ID_PEJABAT) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			//String returnID_PEJABAT = "";
			String sql = "";

			long ID_JENISPEJABAT_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String KOD_JENIS_PEJABAT = getParam("KOD_JENIS_PEJABAT_"+ID_PEJABAT);
				String KETERANGAN = getParam("KETERANGAN_"+ID_PEJABAT); //NAMA JENIS PEJABAT
				String NAMA_LAIN_JENIS_PEJABAT = getParam("NAMA_LAIN_JENIS_PEJABAT_"+ID_PEJABAT);
				String PENERANGAN_LANJUT = getParam("PENERANGAN_LANJUT_"+ID_PEJABAT);
				
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("KOD_JENIS_PEJABAT", KOD_JENIS_PEJABAT.toUpperCase());
				r.add("KETERANGAN", KETERANGAN.toUpperCase());
				r.add("NAMA_LAIN_JENIS_PEJABAT", NAMA_LAIN_JENIS_PEJABAT.toUpperCase());
				r.add("PENERANGAN_LANJUT", PENERANGAN_LANJUT.toUpperCase());
				
				if(ID_JENISPEJABAT.equals("")){
					
				ID_JENISPEJABAT_INSERT = DB.getNextID(db,"TBLRUJJENISPEJABAT_SEQ");
				ID_JENISPEJABAT = ID_JENISPEJABAT_INSERT+"";
				myLog.info("ID_JENISPEJABAT from seq - " + ID_JENISPEJABAT);
				r.add("ID_JENISPEJABAT", ID_JENISPEJABAT);
				sql = r.getSQLInsert("TBLRUJJENISPEJABAT");	
				
				}else {
				r.update("ID_JENISPEJABAT", ID_JENISPEJABAT);
				sql = r.getSQLUpdate("TBLRUJJENISPEJABAT");	
				}
				
					
				System.out.println("ACTION TBLRUJJENISPEJABAT : "+sql);
				stmt.executeUpdate(sql);	
				
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLog.error(se);
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
			return ID_JENISPEJABAT;
		}
		
		
		public String savePegawaiPPK(HttpSession session,String ID_PEJABAT, String ID_RUJPPK) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			//String returnID_PEJABAT = "";
			String sql = "";
			
			String USER_ID2 = "";
			String USER_NAME = "";
			String USER_ID = "";

			long ID_UNITPSK_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				if (ID_RUJPPK.equals("")){
					
					USER_ID2 = getParam("USER_NAME");
					
					myLog.info("USER_ID2 : "+USER_ID2);
					
					String seperator = "/";
					
					USER_NAME = USER_ID2.substring(0, USER_ID2.indexOf(seperator)).trim();
					
					myLog.info("USER_NAME : "+USER_NAME);
					
					
				}
				else {
					
					
					USER_ID = getParam("USER_ID_"+ID_PEJABAT);
					
					USER_NAME = getParam("NAMA_PEGAWAI_"+ID_PEJABAT);
					
					myLog.info("USER_NAME : "+USER_NAME);
					
				}
				
				
				
				System.out.println("KOD PEGAWAI PARAM :: "+getParam("KOD_PEGAWAI_"+ID_PEJABAT));
				
				String KOD_PEGAWAI = getParam("KOD_PEGAWAI_"+ID_PEJABAT);
				myLog.info("KOD_PEGAWAI : "+KOD_PEGAWAI);
				
				String JAWATAN = getParam("JAWATAN_"+ID_PEJABAT);
				String KETERANGAN_UNIT = getParam("KETERANGAN_UNIT_"+ID_PEJABAT);
				
				String NAMA_PEJABAT = getParam("NAMA_PEJABAT_"+ID_PEJABAT);
				String ALAMAT1 = getParam("ALAMAT1_"+ID_PEJABAT);
				String ALAMAT2 = getParam("ALAMAT2_"+ID_PEJABAT);
				String ALAMAT3 = getParam("ALAMAT3_"+ID_PEJABAT);
				String POSKOD = getParam("POSKOD_"+ID_PEJABAT);
				String ID_NEGERI = getParam("ID_NEGERI_"+ID_PEJABAT);
				String ID_BANDAR = getParam("ID_BANDAR_"+ID_PEJABAT);
				
				String NO_TEL = getParam("NO_TEL_"+ID_PEJABAT);
				String EXTENSION = getParam("EXTENSION_"+ID_PEJABAT);
				String EMEL = getParam("EMEL_"+ID_PEJABAT);
				String USER_LOGIN = getParam("USER_LOGIN_"+ID_PEJABAT);
				long USER_IDU = 0;
				String USER_IDP = "";
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				if (USER_ID.equals("")) { 
				//FIND USER_ID IF NULL
			     USER_IDP = getUserIdFromName (session, USER_NAME);
			   
			     if (USER_IDP.equals("")) {
				    //tambah kat table users
					USER_IDU = DB.getNextID(db,"USERS_SEQ");
					USER_IDP = USER_IDU+"";
					
					r.add("USER_ID", USER_IDU);	
				    r.add("USER_LOGIN", USER_LOGIN);
					r.add("USER_PASSWORD", PasswordService.encrypt("P@$$w0rd123"));
					r.add("LAST_CHANGEPASSWORD", r.unquote("sysdate"));
					r.add("USER_NAME", USER_NAME.toUpperCase());
					r.add("DATE_REGISTERED", r.unquote("sysdate"));
					r.add("USER_TYPE", "internal");
					r.add("USER_ROLE", "adminppk");
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					sql = r.getSQLInsert("USERS");		
					myLog.info("V3 : INSERT USERS : "+sql);
					this.context.put("SuccessMesej", "Insert");
					AuditTrail.logActivity(this,session,"INS","USER ["+USER_NAME+"] Added");	
					
					stmt.executeUpdate(sql);
					r.clear();
				     
			  }
			     r.add("USER_ID",USER_IDP);
				 } 
				else {
					r.add("USER_ID",USER_ID);	
				}
				
				r.add("NAMA_PEGAWAI", USER_NAME);
				r.add("KOD", KOD_PEGAWAI);
				r.add("JAWATAN", JAWATAN);
				r.add("STATUS_PEG","1");
				
				r.add("KETERANGAN_UNIT_PSK", KETERANGAN_UNIT);
				
				r.add("ID_JKPTG",ID_PEJABAT);
				r.add("NAMA_PEJABAT", NAMA_PEJABAT.toUpperCase());
				r.add("ALAMAT1", ALAMAT1.toUpperCase());
				r.add("ALAMAT2", ALAMAT2.toUpperCase());
				r.add("ALAMAT3", ALAMAT3.toUpperCase());
				r.add("POSKOD", POSKOD);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_BANDAR",ID_BANDAR);
				
				
				r.add("NO_TEL",NO_TEL);
				r.add("NO_TEL_SAMBUNGAN",EXTENSION);
				r.add("EMEL",EMEL);
				
				//INSERT
				if (ID_RUJPPK.equals("")) {
				
				ID_UNITPSK_INSERT = DB.getNextID(db,"TBLPPKRUJUNIT_SEQ");
				ID_RUJPPK = ID_UNITPSK_INSERT+"";
				r.add("ID_UNITPSK",ID_RUJPPK);
				
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLPPKRUJUNIT");		
				System.out.println("INSERT TBLPPKRUJUNIT : "+sql);
				
				stmt.executeUpdate(sql);	
				r.clear();
				}
				else {
					
				r.update("ID_UNITPSK",ID_RUJPPK);
				
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLPPKRUJUNIT");		
				System.out.println("UPDATE TBLPPKRUJUNIT : "+sql);
				
				stmt.executeUpdate(sql);	
				r.clear();	
				}

				
				
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLog.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Pendaftaran Pegawai PPK :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_RUJPPK;
		}
		
		//INSERT
		
		public String insertDataPejabat(HttpSession session,String ID_PEJABAT, String JENISPEJ) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			//String returnID_PEJABAT = "";
			String sql = "";

			long ID_PEJABAT_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				
				String KETERANGAN = getParam("KETERANGAN_"+ID_PEJABAT);
				String NAMA_PEJABAT = getParam("NAMA_PEJABAT_"+ID_PEJABAT);
				String ALAMAT1 = getParam("ALAMAT1_"+ID_PEJABAT);
				String ALAMAT2 = getParam("ALAMAT2_"+ID_PEJABAT);
				String ALAMAT3 = getParam("ALAMAT3_"+ID_PEJABAT);
				String POSKOD = getParam("POSKOD_"+ID_PEJABAT);
				String NO_TEL = getParam("NO_TEL_"+ID_PEJABAT);
				String ID_SEKSYEN = getParam("ID_SEKSYEN_"+ID_PEJABAT);
				String ID_NEGERI = getParam("ID_NEGERI_"+ID_PEJABAT);
				String NO_FAX = getParam("NO_FAX_"+ID_PEJABAT);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_PEJABAT);
				String ID_BANDAR = getParam("ID_BANDAR_"+ID_PEJABAT);
				String ID_DAERAH = getParam("ID_DAERAH_"+ID_PEJABAT);
				
				String jenisPejabatIns = getParam("jenisPejabatIns_"+ID_PEJABAT);
				myLog.info("jenisPejabatIns == "+jenisPejabatIns);
				
				String ID_JENISPEJABAT = getParam("JENIS_PEJABAT_URUSAN_"+ID_PEJABAT);
				
				
				
				String FLAG_INT = getParam("FLAG_INT_"+ID_PEJABAT);
				
				String EMEL = getParam("EMEL_"+ID_PEJABAT);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_BANDAR", ID_BANDAR);
				
				r.add("NAMA_PEJABAT", NAMA_PEJABAT.toUpperCase());	
				r.add("ALAMAT1", ALAMAT1.toUpperCase());
				r.add("ALAMAT2", ALAMAT2.toUpperCase());
				r.add("ALAMAT3", ALAMAT3.toUpperCase());
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				r.add("NO_FAX", NO_FAX);
				r.add("NO_TEL",NO_TEL);
				r.add("POSKOD",POSKOD);
				r.add("FLAG_INT",FLAG_INT);
				
				r.add("EMEL",EMEL);
				
				//INSERT
				if (jenisPejabatIns.equals("1")) {
					
					myLog.info("masuk pejabat jkptg");
					
					ID_PEJABAT_INSERT = DB.getNextID(db,"TBLRUJPEJABATJKPTG_SEQ");
					ID_PEJABAT = ID_PEJABAT_INSERT+"";
					
					r.add("ID_PEJABATJKPTG", ID_PEJABAT);
					
					if (ID_SEKSYEN.equals("2")){
					//	ID_JENISPEJABAT = "22";
					myLog.info("ID_JENISPEJABAT == "+ID_JENISPEJABAT);
					r.add("ID_JENISPEJABAT", ID_JENISPEJABAT);
					}
					
					sql = r.getSQLInsert("TBLRUJPEJABATJKPTG");		
					System.out.println("INSERT TBLRUJPEJABAT JKPTG: "+sql);
					
				}else {
					myLog.info("masuk pejabat urusan");
				
				ID_PEJABAT_INSERT = DB.getNextID(db,"TBLRUJPEJABAT_SEQ");
				ID_PEJABAT = ID_PEJABAT_INSERT+"";
				
				r.add("ID_PEJABAT", ID_PEJABAT);
				r.add("ID_JENISPEJABAT", ID_JENISPEJABAT);
				
				sql = r.getSQLInsert("TBLRUJPEJABAT");		
				System.out.println("INSERT TBLRUJPEJABAT : "+sql);
				
				}
				
				stmt.executeUpdate(sql);	
				conn.commit();
				
				myLog.info("FLAG_INT :: "+FLAG_INT);
				
				if (FLAG_INT.equals("1")){
					//insert tbltujintegrasi
					saveDetailIntegrasi(session, "1", "", false);
				}
				
			} 
			catch (SQLException se) { 
				myLog.error(se);
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
			return ID_PEJABAT;
		}
	
		//UPDATE

		public String saveDataPejabat(HttpSession session,String ID_PEJABAT, String JENISPEJ) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
			//String returnID_PEJABAT = "";
			String sql = "";
			long ID_PEJABAT_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String KETERANGAN = getParam("KETERANGAN_"+ID_PEJABAT);
				
				String NAMA_PEJABAT = getParam("NAMA_PEJABAT_"+ID_PEJABAT);
				String ALAMAT1 = getParam("ALAMAT1_"+ID_PEJABAT);
				String ALAMAT2 = getParam("ALAMAT2_"+ID_PEJABAT);
				String ALAMAT3 = getParam("ALAMAT3_"+ID_PEJABAT);
				String POSKOD = getParam("POSKOD_"+ID_PEJABAT);
				String NO_TEL = getParam("NO_TEL_"+ID_PEJABAT);
				String ID_SEKSYEN = getParam("ID_SEKSYEN_"+ID_PEJABAT);
				String ID_NEGERI = getParam("ID_NEGERI_"+ID_PEJABAT);
				String NO_FAX = getParam("NO_FAX_"+ID_PEJABAT);
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_PEJABAT);
				String ID_BANDAR = getParam("ID_BANDAR_"+ID_PEJABAT);
				String ID_DAERAH = getParam("ID_DAERAH_"+ID_PEJABAT);
				String ID_JENISPEJABAT = getParam("JENIS_PEJABAT_URUSAN_"+ID_PEJABAT);
				
				//for id jenis pejabat pusaka
				/*if (ID_SEKSYEN.equals("2")){
					ID_JENISPEJABAT = "22";
				}*/
				
				String FLAG_INT = getParam("FLAG_INT_"+ID_PEJABAT);
				
				String EMEL = getParam("EMEL_"+ID_PEJABAT);
				
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
	
				//UPDATE
				if(JENISPEJ.equals("1")){
				r.update("ID_PEJABATJKPTG", ID_PEJABAT);
				} else if(JENISPEJ.equals("2")){
				r.update("ID_PEJABAT", ID_PEJABAT);	
				}
				r.add("ID_SEKSYEN", ID_SEKSYEN);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("ID_DAERAH", ID_DAERAH);
				r.add("ID_BANDAR", ID_BANDAR);
				r.add("ID_JENISPEJABAT", ID_JENISPEJABAT);
				
				r.add("NAMA_PEJABAT", NAMA_PEJABAT);
				r.add("ALAMAT1", ALAMAT1);
				r.add("ALAMAT2", ALAMAT2);
				r.add("ALAMAT3", ALAMAT3);
				r.add("POSKOD", POSKOD);
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				r.add("NO_FAX", NO_FAX);
				r.add("NO_TEL",NO_TEL);
				r.add("FLAG_INT",FLAG_INT);
				
				r.add("EMEL",EMEL);
				
				if(JENISPEJ.equals("1")){
				sql = r.getSQLUpdate("TBLRUJPEJABATJKPTG");	
				} else if(JENISPEJ.equals("2")){
				sql = r.getSQLUpdate("TBLRUJPEJABAT");	
				}
					
				myLog.info("UPDATE TBLRUJPEJABAT : "+sql);
				
				stmt.executeUpdate(sql);
				conn.commit();
				
				myLog.info("FLAG_INT :: "+FLAG_INT);
				
				if (FLAG_INT.equals("1")){
					//insert tbltujintegrasi
					saveDetailIntegrasi(session, "1", ID_PEJABAT, false);
				}
				
			} 
			catch (SQLException se) { 
				myLog.error(se);
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
			return ID_PEJABAT;
		}

		//SETUP
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
					sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN " +
							" FROM TBLRUJJAWATAN WHERE  ID_JAWATAN != 0 ORDER BY KOD_JAWATAN";
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
				
		public int saveDaerahJagaanbyPejabat(HttpSession session, String ID_PEJABAT, String ID_NEGERI, String JENIS_PEJABAT, 
				String ID_DAERAH, String ID_SEKSYEN, String[] CHECKLIST_) throws Exception {
			
			Connection conn = null;
			Db db = null;
			String sql = "";
			int total_role_update = 0;
			long ID_PEJABATURUSAN = 0;
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = "DELETE FROM TBLRUJPEJABATURUSAN WHERE ID_PEJABATJKPTG = "+ ID_PEJABAT;
				
				myLog.info("DELETE ALL DAERAH JAGAAN : "+sql);
				stmt.executeUpdate(sql);			
				
				for (String DAERAH_ID : CHECKLIST_) {
					
					String seperator = "/";
					
					String ID_PEJABATURUS = DAERAH_ID.substring(0, DAERAH_ID.indexOf(seperator)).trim();
					String DAERAH_ID2 = DAERAH_ID.substring(DAERAH_ID.indexOf(seperator)+1).trim();
					String ID_PEJABATURUSPEJ = "";
					myLog.info(" DAERAH_KOD : "+DAERAH_ID);			
					if(DAERAH_ID!=null)
					{
						
						sql = "";
						r.clear();
						
						if (!ID_PEJABATURUS.equals("")){
						 ID_PEJABATURUSPEJ = getIDPejabatUrus(session,DAERAH_ID2,"");
						}else{
							ID_PEJABATURUSPEJ = "";
						}
						myLog.info("ID_PEJABATURUSPEJ -- "+ID_PEJABATURUSPEJ);
						
						if (ID_PEJABATURUSPEJ!= ""){
						
						r.add("ID_PEJABATURUS", ID_PEJABATURUSPEJ);
						
						String ID_JENISPEJABATURUS = getIdJenisPejUrus(session,ID_PEJABATURUSPEJ);
						
						r.add("ID_JENISPEJABATURUS", ID_JENISPEJABATURUS);
						
						} else {
							
						r.add("ID_PEJABATURUS", "");
						r.add("ID_JENISPEJABATURUS", "");
						}	
						
						ID_PEJABATURUSAN = DB.getNextID(db,"TBLRUJPEJABATURUSAN_SEQ");
						
						r.add("ID_PEJABATURUSAN", ID_PEJABATURUSAN);
						r.add("ID_JENISPEJABAT", JENIS_PEJABAT);
						r.add("ID_PEJABATJKPTG", ID_PEJABAT);
						r.add("ID_NEGERI", ID_NEGERI);
						r.add("ID_DAERAH", ID_DAERAH);
						r.add("ID_NEGERIURUS", ID_NEGERI);
						r.add("ID_DAERAHURUS", DAERAH_ID2);
						r.add("ID_SEKSYEN", ID_SEKSYEN);
						r.add("ID_MASUK", USER_ID_SYSTEM);
						r.add("TARIKH_MASUK", r.unquote("sysdate"));
						sql = r.getSQLInsert("TBLRUJPEJABATURUSAN");		
						
						myLog.info("V3 : INSERT TBLRUJPEJABATURUSAN : "+sql);
						
						stmt.executeUpdate(sql);
						total_role_update++;
							
					}
					DAERAH_ID = "";
				}
				
				conn.commit();
				
			} 
			catch (SQLException se) { 
				myLog.error(se);
		    	try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat insert daerah jagaan : "+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return total_role_update;
		}
		
		public String getIdPejUrus(HttpSession session, String ID_PEJABAT, String ID_NEGERI, String DAERAH_ID) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				String idPejUrus = "";
				
				sql = 	" SELECT ID_PEJABAT FROM TBLRUJPEJABAT " +
						" WHERE ID_NEGERI = " +ID_NEGERI +
						" AND ID_DAERAH = " +DAERAH_ID +
						" AND ID_PEJABAT = "+ID_PEJABAT ;
				
						
				myLog.info("GET ID PEJABAT URUSAN : "+sql);
				rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						idPejUrus = rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT");
					}
				
				return idPejUrus;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
		
		
		public String getIdJenisPejUrus(HttpSession session, String ID_PEJABAT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				String idPejUrus = "";
				
				sql = 	" SELECT ID_JENISPEJABAT FROM TBLRUJPEJABAT " +
						" WHERE ID_PEJABAT = "+ID_PEJABAT ;
				
						
				myLog.info(" getIdJenisPejUrus : "+sql);
				rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						idPejUrus = rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT");
					}
				
				return idPejUrus;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
		
		
		public String getCountStaff(HttpSession session, String ID_PEJABAT) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				String staffCount = "";
				
				sql = 	" SELECT COUNT(U.USER_ID) AS KAKITANGAN FROM USERS U, USERS_INTERNAL UI" +
						" WHERE U.USER_ID = UI.USER_ID " +
						" AND (UI.FLAG_AKTIF IS NULL OR UI.FLAG_AKTIF = 'Y')" +
						" AND UI.ID_PEJABATJKPTG =  "+ID_PEJABAT ;
				
						
				myLog.info(" staffCount : "+sql);
				rs = stmt.executeQuery(sql);				
					while (rs.next()) {
						staffCount = rs.getString("KAKITANGAN") == null ? "" : rs.getString("KAKITANGAN");
					}
				
				return staffCount;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
		
		public String getIDPejabatUrus(HttpSession session,  String ID_DAERAH, String test) throws Exception {
			
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				/*if(db==null)
				{
					db1 = new Db();
				}
				else
				{
					db1 = db;
				}*/
				db = new Db();
				stmt = db.getStatement();			
				String idPejabaturus = "";
				
				sql = 	" SELECT ID_PEJABAT FROM TBLRUJPEJABAT " +
						" WHERE ID_JENISPEJABAT = 2 AND ID_DAERAH = "+ ID_DAERAH;
				
				rs = stmt.executeQuery(sql);				
					
				while (rs.next()) {
						idPejabaturus = rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT");
					}
			
				myLog.info(" sql idPejabaturus : "+sql);
				myLog.info(" idPejabaturus : "+idPejabaturus);
				
				return idPejabaturus;
				
			} /*finally {
				if(db==null)
				{
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db1 != null)
						db1.close();
				}
			}*/
			
			finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}
		}
		
		
		@SuppressWarnings("unchecked")
		public List listPegawaiPPKDisp(HttpSession session, String ID_PEJABAT) throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPegawai = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = 	" SELECT U.USER_ID, PR.ID_UNITPSK, PR.ID_JKPTG, " +
						" PR.NAMA_PEGAWAI, PR.JAWATAN " +
						" FROM   TBLPPKRUJUNIT PR, TBLRUJPEJABATJKPTG PE, USERS U " +
						" WHERE   PR.ID_JKPTG = PE.ID_PEJABATJKPTG " +
						" AND PR.USER_ID = U.USER_ID(+) " +
						" AND PR.ID_JKPTG = " + ID_PEJABAT +
						" ORDER BY   PR.ID_UNITPSK ";

				myLog.info(" SQL listPegawai display:"+ sql);
				
				rs = stmt.executeQuery(sql);
				listPegawai = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					h.put("BIL",bil);
					h.put("ID_UNITPSK",rs.getString("ID_UNITPSK") == null ? "" : rs.getString("ID_UNITPSK"));
					h.put("ID_JKPTG",rs.getString("ID_JKPTG") == null ? "" : rs.getString("ID_JKPTG"));
					h.put("NAMA_PEGAWAI",rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));		
					h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));	
					h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					
					listPegawai.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listPegawai;

		}
		
		@SuppressWarnings("unchecked")
		public List listPegawaiPPKDropdown(HttpSession session, String ID_PEJABAT) throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPegawai = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				/*String sqlPlus = "  AND U.USER_ID NOT IN " +
								 " (SELECT   PR.USER_ID " +
								 " FROM   TBLPPKRUJUNIT PR, USERS U " +
								 " WHERE   PR.USER_ID = U.USER_ID " +
								 " AND PR.ID_JKPTG = "+ID_PEJABAT+")"; 
				
				sql = 	" SELECT U.USER_ID, U.USER_NAME, UR.ROLE_ID " +
						" FROM USER_ROLE UR, USERS U, TBLRUJSEKSYEN SY, USERS_INTERNAL UI " +
						" WHERE U.USER_LOGIN = UR.USER_ID AND UR.ROLE_ID = 'adminppk'  " +
						" AND U.USER_ID = UI.USER_ID AND UI.ID_SEKSYEN = SY.ID_SEKSYEN(+) AND UI.ID_SEKSYEN = 2 "+ sqlPlus;
				
				sql+= 	" UNION ";
				
				sql+=	" SELECT U.USER_ID, U.USER_NAME, U.USER_ROLE AS ROLE_ID " +
						" FROM USERS U, TBLRUJSEKSYEN SY, USERS_INTERNAL UI " +
						" WHERE U.USER_ROLE = 'adminppk' AND U.USER_ID = UI.USER_ID " +
						" AND UI.ID_SEKSYEN = SY.ID_SEKSYEN(+) AND UI.ID_SEKSYEN = 2 "+ sqlPlus;
				
				sql+= 	" ORDER BY USER_NAME ASC ";*/
				
				sql = 	" SELECT DISTINCT U.USER_ID, PR.USER_ID AS USER_ID_PPKUNIT, U.USER_LOGIN, U.USER_NAME, " +
						" U.USER_LOGIN || ' - ' || U.USER_NAME || ' (' || " +
						" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF'  WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
						" ELSE 'AKTIF' END) || ')' AS DETAILS_PEGAWAI, "+
						" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
						" ELSE 'AKTIF' END) AS KEAKTIFAN " +
						" FROM USERS U, USERS_INTERNAL UI, USER_ROLE UR, TBLPPKRUJUNIT PR " +
						" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR.USER_ID(+) AND UI.ID_SEKSYEN = 2 " +
						" AND LENGTH (U.USER_LOGIN) = 12 AND (U.USER_ROLE = 'adminppk' OR UR.ROLE_ID = 'adminppk') " +
						" AND U.USER_ID = PR.USER_ID(+) " +
						" AND NVL (LENGTH(REPLACE (TRANSLATE (U.USER_LOGIN, '0123456789', '0000000000'),'0','')),0 ) = 0 " +
						" ORDER BY (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
						" ELSE 'AKTIF' END),U.USER_NAME ";

				myLog.info(" SQL listPegawai:"+ sql);
				
				rs = stmt.executeQuery(sql);
				listPegawai = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
				//	h.put("BIL",bil);
					h.put("USER_ID",rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
					h.put("USER_ID_PPKUNIT",rs.getString("USER_ID_PPKUNIT") == null ? "" : rs.getString("USER_ID_PPKUNIT"));
					h.put("USER_LOGIN",rs.getString("USER_LOGIN") == null ? "" : rs.getString("USER_LOGIN"));
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
					h.put("KEAKTIFAN",rs.getString("KEAKTIFAN") == null ? "" : rs.getString("KEAKTIFAN"));		
					h.put("DETAILS_PEGAWAI",rs.getString("DETAILS_PEGAWAI") == null ? "" : rs.getString("DETAILS_PEGAWAI"));		
					
					listPegawai.add(h);
				}

			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db != null)
					db.close();
			}

			return listPegawai;

		}
				
		//display list daerah jagaan

				@SuppressWarnings("unchecked")
				public List listDaerahJagaanDisplay(HttpSession session,  String ID_PEJABAT, String idNegeri, String JENIS_PEJABAT)throws Exception {
					Db db = null;
					ResultSet rs = null;
					Statement stmt = null;
					List listDaerahJagaan = null;
					String sql = "";
					
					String ID_JENISPEJABAT = "";
					
					if (JENIS_PEJABAT.equals("1") || JENIS_PEJABAT.equals("22")){
						ID_JENISPEJABAT = "21,22,23,24";
					}else{
						ID_JENISPEJABAT = "3";
					}
					
					try {
						db = new Db();
						stmt = db.getStatement();
						
						if ( JENIS_PEJABAT.equals("1") ){
						sql = 	" SELECT B.ID_DAERAH AS ID, D.NAMA_DAERAH AS KETERANGAN, D.KOD_DAERAH AS KOD " +
								" FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJDAERAH D, TBLRUJPEJABATJKPTG PJ " +
								" WHERE A.ID_PEJABATJKPTG = "+ ID_PEJABAT +
								" AND A.ID_JENISPEJABAT IN ( "+ID_JENISPEJABAT+ ")"+
								" AND A.ID_PEJABATURUS = B.ID_PEJABAT(+) " +
								" AND B.ID_DAERAH = D.ID_DAERAH "+
								" AND A.ID_PEJABATJKPTG = PJ.ID_PEJABATJKPTG ";

						} else {
							
						sql = 	" SELECT D.KOD_DAERAH AS KOD, " +
								" F.ID_DAERAH AS ID, F.NAMA_DAERAH AS KETERANGAN " +
								" FROM TBLRUJPEJABATURUSAN A, TBLRUJPEJABAT B, TBLRUJDAERAH D, " +
								" TBLRUJPEJABAT E, TBLRUJDAERAH F " +
								" WHERE A.ID_PEJABATJKPTG = B.ID_PEJABAT AND A.ID_JENISPEJABAT IN ( "+ID_JENISPEJABAT+ ") "+
								" AND A.ID_DAERAH= D.ID_DAERAH AND A.ID_PEJABATURUS=E.ID_PEJABAT " +
								" AND A.ID_DAERAHURUS = F.ID_DAERAH AND B.ID_PEJABAT = "+ ID_PEJABAT +
								" ORDER BY A.ID_NEGERI,B.NAMA_PEJABAT ";	
							
							
						}
						myLog.info(" SQL listDaerahJagaanDisplay:"+ sql);
						rs = stmt.executeQuery(sql);
						listDaerahJagaan = Collections.synchronizedList(new ArrayList());
						Map h = null;
						int bil = 0;
						while (rs.next()) {
							h = Collections.synchronizedMap(new HashMap());
							bil++;
							h.put("BIL",bil);
							h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
							h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD"));
							h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));		
							
							listDaerahJagaan.add(h);
						}

					} finally {
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (db != null)
							db.close();
					}

					return listDaerahJagaan;

				}
		
				@SuppressWarnings("unchecked")
		public List listDaerahJagaanByIdPejabat(HttpSession session, String ID_PEJABAT, String idNegeri, String ID_JENISPEJ)throws Exception {
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listDaerahJagaanByIdPejabat = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				/*sql = 	" SELECT DISTINCT A.ID_DAERAH AS ID, A.KOD_DAERAH AS KOD, UPPER(A.NAMA_DAERAH) AS KETERANGAN, " +
						" 'Y' AS TICK , B.ID_PEJABATURUS " +
						" FROM TBLRUJDAERAH A, TBLRUJPEJABATURUSAN B" +
						" WHERE A.ID_NEGERI = '"+idNegeri+"' " +
						" AND B.ID_PEJABATJKPTG= "+ID_PEJABAT +
						" AND A.ID_DAERAH = B.ID_DAERAHURUS " +
						" UNION " +
						" SELECT DISTINCT A.ID_DAERAH AS ID, A.KOD_DAERAH AS KOD, UPPER (A.NAMA_DAERAH) AS KETERANGAN," +
						" '' AS TICK, B.ID_PEJABATURUS        " +
						" FROM   TBLRUJDAERAH A, TBLRUJPEJABATURUSAN B" +
						" WHERE   A.ID_NEGERI = '"+idNegeri+"' " +
						" AND A.ID_DAERAH NOT IN (SELECT DISTINCT B.ID_DAERAHURUS  FROM TBLRUJPEJABATURUSAN B WHERE B.ID_PEJABATJKPTG= "+ID_PEJABAT+" )" +
						" AND A.ID_DAERAH = B.ID_DAERAHURUS ";*/
				
				if (ID_JENISPEJ.equals("1")){
					ID_JENISPEJ = "22";
				}else {
					ID_JENISPEJ = "3";
				}
				
				sql = 	" SELECT DISTINCT D.ID_DAERAH, D.KOD_DAERAH, UPPER(D.NAMA_DAERAH) AS KETERANGAN, " +
						" CASE WHEN PEJ.ID_DAERAHURUS IS NOT NULL THEN 'Y' ELSE '' END AS TICK " +
						" FROM TBLRUJDAERAH D, TBLRUJNEGERI N, " +
						" ( SELECT P.ID_PEJABATURUS, P.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN P " +
						" WHERE P.ID_PEJABATJKPTG= "+ ID_PEJABAT + " AND P.ID_JENISPEJABAT = "+ID_JENISPEJ+ ") PEJ " +
						" WHERE D.ID_NEGERI = N.ID_NEGERI AND D.KOD_DAERAH != 0 " +
						" AND N.ID_NEGERI = " + idNegeri +
						" AND D.ID_DAERAH = PEJ.ID_DAERAHURUS(+)";
		
				myLog.info(" SQL listDaerahJagaanByIdPejabat :"+ sql);
				rs = stmt.executeQuery(sql);
				listDaerahJagaanByIdPejabat = Collections.synchronizedList(new ArrayList());
				Map h = null;
				int bil = 0;
				while (rs.next()) {
					h = Collections.synchronizedMap(new HashMap());
					bil++;
					
					String ID_PEJABATURUS = getIDPejabatUrus(session,  rs.getString("ID_DAERAH"),"");
					
					h.put("BIL",bil);
					h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
					h.put("KOD_DAERAH",rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH"));
					h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));	
					h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));	
					h.put("ID_PEJABATURUS",ID_PEJABATURUS);
					
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
				
	//save integ
	public String saveDetailIntegrasi(HttpSession session, String Type, String ID_INTEGRASI, boolean checkIdInt) throws Exception {
		Connection conn = null;
		Db db = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String sql = "";

		long ID_INTEGRASI_INSERT = 0;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String NAMA = getParam("NAMA_PEJABAT_"+ID_INTEGRASI);
			
			String ID_JENISPEJABAT = "";
			
			if (!ID_INTEGRASI.equals("")){
				ID_JENISPEJABAT = getParam("JENIS_PEJABAT_URUSAN_"+ID_INTEGRASI);
			} else{
				ID_JENISPEJABAT = getParam("ID_JENISPEJABAT_"+ID_INTEGRASI);
			}
			
			String NAMA_LAIN = getParam("KETERANGAN_"+ID_INTEGRASI);
//			String KOD_AGENSI = getParam("KOD_AGENSI_"+ID_INTEGRASI);
			
			String ALAMAT1 = getParam("ALAMAT1_"+ID_INTEGRASI);
			String ALAMAT2 = getParam("ALAMAT2_"+ID_INTEGRASI);
			String ALAMAT3 = getParam("ALAMAT3_"+ID_INTEGRASI);
			String POSKOD = getParam("POSKOD_"+ID_INTEGRASI);
			String ID_NEGERI = getParam("ID_NEGERI_"+ID_INTEGRASI);
			
			String NO_TEL = getParam("NO_TEL_"+ID_INTEGRASI);
			String NO_FAX = getParam("NO_FAX_"+ID_INTEGRASI);
			
			//String EMEL = getParam("EMEL_"+ID_INTEGRASI);
			//String CATATAN = getParam("CATATAN_"+ID_INTEGRASI);
			
			String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_INTEGRASI);
			
			//String JENIS_INTEGRASI = getParam("JENIS_INTEGRASI_"+ID_INTEGRASI);
			//String KATEGORI = getParam("KATEGORI_"+ID_INTEGRASI);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("NAMA", NAMA.toUpperCase());
			r.add("NAMA_LAIN", NAMA_LAIN.toUpperCase());
			//r.add("KOD_AGENSI", KOD_AGENSI.toUpperCase());
			r.add("ALAMAT1", ALAMAT1.toUpperCase());
			r.add("ALAMAT2", ALAMAT2.toUpperCase());
			r.add("ALAMAT3", ALAMAT3.toUpperCase());
			r.add("POSKOD", POSKOD);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("FLAG_AKTIF", FLAG_AKTIF);
			r.add("ID_JENISINTEG", "1");
			r.add("ID_KATEGORI", "1");
			
			//TAMBAH
			
			r.add("ID_JENISPEJABAT", ID_JENISPEJABAT);
			r.add("NO_TEL", NO_TEL);
			r.add("NO_FAKS", NO_FAX);
			
			/*r.add("EMEL", EMEL);
			r.add("CATATAN", CATATAN);*/
			
			/*if (Type.equals("2")){
				
				String NAMA_PEMILIK = getParam("NAMA_PEMILIK_"+ID_INTEGRASI);
				String NAMA_LAIN_PEMILIK = getParam("NAMA_LAIN_PEMILIK_"+ID_INTEGRASI);
				String KATEG_PEMILIK = getParam("KATEG_PEMILIK_"+ID_INTEGRASI);
				
				r.add("NAMA_PEMILIK", NAMA_PEMILIK);
				r.add("NAMA_LAIN_PEMILIK", NAMA_LAIN_PEMILIK);
				r.add("KATEG_PEMILIK", KATEG_PEMILIK);
				
			}*/
			
			
			if(ID_INTEGRASI.equals("")){
				
			myLog.info("MASUK INSERT INTEGRASI : "+ID_INTEGRASI);
				
			ID_INTEGRASI_INSERT = DB.getNextID(db,"TBLRUJINTEGRASI_SEQ");
			ID_INTEGRASI = ID_INTEGRASI_INSERT+"";
		
			r.add("ID_INTEGRASI", ID_INTEGRASI);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLRUJINTEGRASI");	
			
			}else if (!ID_INTEGRASI.equals("") && checkIdInt == false ){
				
			myLog.info("MASUK INSERT INTEGRASI 2 : "+ID_INTEGRASI);
			
			ID_INTEGRASI_INSERT = DB.getNextID(db,"TBLRUJINTEGRASI_SEQ");
			ID_INTEGRASI = ID_INTEGRASI_INSERT+"";
		
			r.add("ID_INTEGRASI", ID_INTEGRASI);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLRUJINTEGRASI");
			}
				
			else{
			r.update("ID_INTEGRASI", ID_INTEGRASI);	
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			sql = r.getSQLUpdate("TBLRUJINTEGRASI");	
			
			}
			
			myLog.info("ACTION INTEGRASI : "+sql.toUpperCase());
			
			stmt.executeUpdate(sql);
			conn.commit();
			
		} 
		catch (SQLException se) { 
			myLog.error(se);
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
		return ID_INTEGRASI;
	}
	
	@SuppressWarnings("unchecked")
	public List getStatsJawatan(HttpSession session, String ID_PEJABAT,String mode) throws Exception {
				
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listPejabatUrusan = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
						sql = 	" SELECT COUNT (USER_ID) AS JUMLAH_PENGGUNA, JAWATAN FROM " +
								" (SELECT U.USER_ID, UPPER (U.USER_NAME) AS FULLNAME, J.ID_JAWATAN, UPPER (J.KETERANGAN) AS JAWATAN" +
								" FROM USERS U, USERS_INTERNAL UI, TBLRUJSEKSYEN S, TBLRUJJAWATAN J, ROLE R, WEB_LOGGER WL, " +
								" TBLRUJPEJABATJKPTG PEJ, TBLRUJNEGERI N, " +
								" ( SELECT UR.USER_ID, COUNT (UR.ROLE_ID) AS TOTAL_ROLE FROM USER_ROLE UR GROUP BY UR.USER_ID) UR_TEMP " +
								" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR_TEMP.USER_ID(+) AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
								" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND PEJ.ID_NEGERI = N.ID_NEGERI(+) " +
								" AND (UI.ID_PEJABATJKPTG = '"+ID_PEJABAT+"') AND (UPPER (UI.FLAG_AKTIF) IS NULL OR UPPER (UI.FLAG_AKTIF) = '1' " +
								" OR UPPER (UI.FLAG_AKTIF) = '') AND U.USER_ROLE = R.NAME(+) " +
								" AND U.USER_LOGIN = WL.USER_NAME(+) AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG ";
						
						sql +=	" GROUP BY   U.USER_ID, U.USER_NAME, J.ID_JAWATAN, J.KETERANGAN " +
								" ORDER BY   USER_ID) " +
								" GROUP BY ID_JAWATAN, JAWATAN ";
					
					myLog.debug("SQL STATS JAWATAN JKPTG -- "+sql);
					 rs = stmt.executeQuery(sql);
					
					listPejabatUrusan = Collections.synchronizedList(new ArrayList());
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
						
						//String countStaff = getCountStaff(session, rs.getString("ID_PEJABAT"));
				
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("JUMLAH_PENGGUNA",checkIsNull(rs.getString("JUMLAH_PENGGUNA")));
						h.put("JAWATAN",checkIsNull(rs.getString("JAWATAN")));
						
						listPejabatUrusan.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listPejabatUrusan;
				
			}
	
	@SuppressWarnings("unchecked")
	public List getSenaraiUser(HttpSession session, String ID_PEJABAT,String mode) throws Exception {
				
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listPejabatUrusan = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
						sql = 	" SELECT U.USER_ID, U.USER_LOGIN, S.NAMA_SEKSYEN AS BAHAGIAN, " +
								" UPPER (U.USER_NAME) AS FULLNAME, UPPER (J.KETERANGAN) AS JAWATAN, " +
								" UPPER (R.DESCRIPTION) AS ROLE_UTAMA,  " +
								" (CASE WHEN UI.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN UI.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
								" ELSE 'AKTIF' END) AS KEAKTIFAN, UPPER (PEJ.NAMA_PEJABAT) AS NAMA_PEJABAT, " +
								" UPPER (N.NAMA_NEGERI) AS NEGERI_PEJABAT, UR_TEMP.TOTAL_ROLE, UI.NO_KP, " +
								" TO_CHAR (MAX (WL.LOG_DATE), 'DD/MM/YYYY hh24:mi:ss') AS WAKTU_AKHIR_LOGIN "+
								" FROM USERS U, USERS_INTERNAL UI, TBLRUJSEKSYEN S, TBLRUJJAWATAN J, ROLE R, WEB_LOGGER WL, " +
								" TBLRUJPEJABATJKPTG PEJ, TBLRUJNEGERI N, " +
								" ( SELECT UR.USER_ID, COUNT (UR.ROLE_ID) AS TOTAL_ROLE FROM USER_ROLE UR GROUP BY UR.USER_ID) UR_TEMP " +
								" WHERE U.USER_ID = UI.USER_ID AND U.USER_LOGIN = UR_TEMP.USER_ID(+) AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
								" AND UI.ID_JAWATAN = J.ID_JAWATAN(+) AND PEJ.ID_NEGERI = N.ID_NEGERI(+) " +
								" AND (UI.ID_PEJABATJKPTG = '"+ID_PEJABAT+"') AND (UPPER (UI.FLAG_AKTIF) IS NULL OR UPPER (UI.FLAG_AKTIF) = '1' " +
								" OR UPPER (UI.FLAG_AKTIF) = '')  AND U.USER_ROLE = R.NAME(+) " +
								" AND U.USER_LOGIN = WL.USER_NAME(+) AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG ";
						
						sql +=	" GROUP BY U.USER_ID, UR_TEMP.TOTAL_ROLE, U.USER_LOGIN, S.NAMA_SEKSYEN, U.USER_NAME, " +
								" J.KETERANGAN, R.DESCRIPTION, UI.FLAG_AKTIF, U.LAST_CHANGEPASSWORD, PEJ.NAMA_PEJABAT, " +
								" N.NAMA_NEGERI, UI.NO_KP ";
						
						sql +=	" ORDER BY USER_LOGIN ";
					
					myLog.debug("SQL USER JKPTG -- "+sql);
					 rs = stmt.executeQuery(sql);
					
					listPejabatUrusan = Collections.synchronizedList(new ArrayList());
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
						
						//String countStaff = getCountStaff(session, rs.getString("ID_PEJABAT"));
				
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("USER_ID",checkIsNull(rs.getString("USER_ID")));
						h.put("USER_LOGIN",checkIsNull(rs.getString("USER_LOGIN")));
						h.put("BAHAGIAN",checkIsNull(rs.getString("BAHAGIAN")));
						h.put("FULLNAME",checkIsNull(rs.getString("FULLNAME")));
						h.put("JAWATAN",checkIsNull(rs.getString("JAWATAN")));
						
						h.put("ROLE_UTAMA",checkIsNull(rs.getString("ROLE_UTAMA")));
						
						h.put("KEAKTIFAN",checkIsNull(rs.getString("KEAKTIFAN")));
						h.put("NAMA_PEJABAT",checkIsNull(rs.getString("NAMA_PEJABAT")));
						h.put("NEGERI_PEJABAT",checkIsNull(rs.getString("NEGERI_PEJABAT")));
						h.put("TOTAL_ROLE",checkIsNull(rs.getString("TOTAL_ROLE")));
						h.put("NO_KP",checkIsNull(rs.getString("NO_KP")));
						h.put("WAKTU_AKHIR_LOGIN",checkIsNull(rs.getString("WAKTU_AKHIR_LOGIN")));
						
						listPejabatUrusan.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listPejabatUrusan;
				
			}
	
	
	public String getUserIdFromName (HttpSession session, String USER_NAME) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String USER_ID = "";
			
			sql = 	" SELECT USER_ID FROM USERS WHERE USER_NAME = '"+USER_NAME+"'";
			
					
			myLog.info(" staffCount : "+sql);
			rs = stmt.executeQuery(sql);				
				while (rs.next()) {
					USER_ID = rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID");
				}
			
			return USER_ID;
			
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
	public List listDaerahJagaanByIdPejabat2(HttpSession session, String ID_PEJABAT, String idNegeri, String ID_JENISPEJ)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listDaerahJagaanByIdPejabat = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			if (ID_JENISPEJ.equals("1")){
				ID_JENISPEJ = "22";
			}else {
				ID_JENISPEJ = "3";
			}
			
			sql = 	" SELECT   1 AS LAYER, NAMA_NEGERI AS LAYER_1, '' AS LAYER_2, " +
					" ID_NEGERI, 0 AS ID_DAERAH, KOD_NEGERI AS KOD, '' AS TICK " +
					" FROM   TBLRUJNEGERI WHERE ID_NEGERI IS NOT NULL " +
					" AND KOD_NEGERI != '00' AND KOD_NEGERI != '0' AND ID_NEGERI != 17 " +
					" UNION " +
					" SELECT   2 AS LAYER, '' AS LAYER_1, UPPER (D.NAMA_DAERAH) AS LAYER_2, " +
					" N.ID_NEGERI, D.ID_DAERAH, D.KOD_DAERAH AS KOD, " +
					" CASE WHEN PEJ.ID_DAERAHURUS IS NOT NULL THEN 'Y' ELSE '' END AS TICK " +
					" FROM   TBLRUJDAERAH D, TBLRUJNEGERI N, (SELECT   P.ID_PEJABATURUS, P.ID_DAERAHURUS " +
					" FROM   TBLRUJPEJABATURUSAN P WHERE P.ID_PEJABATJKPTG = "+ ID_PEJABAT +
					" AND P.ID_JENISPEJABAT = "+ID_JENISPEJ +") PEJ " +
				    " WHERE D.ID_NEGERI = N.ID_NEGERI AND D.KOD_DAERAH != 0 " +
				    " AND D.ID_DAERAH = PEJ.ID_DAERAHURUS(+) " +
				    " ORDER BY ID_NEGERI, LAYER_1, ID_DAERAH, LAYER_2 ASC ";
	
			myLog.info(" SQL listDaerahJagaanByIdPejabat :"+ sql);
			rs = stmt.executeQuery(sql);
			listDaerahJagaanByIdPejabat = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String ID_PEJABATURUS = getIDPejabatUrus(session,  rs.getString("ID_DAERAH"), "");
				myLog.info(" ID_PEJABATURUS 1 :"+ ID_PEJABATURUS);
				//h.put("BIL",bil);
				h.put("ID_PEJABATURUS",ID_PEJABATURUS);
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER"));	
				h.put("LAYER_1",rs.getString("LAYER_1") == null ? "" : rs.getString("LAYER_1").toUpperCase());
				h.put("LAYER_2",rs.getString("LAYER_2") == null ? "" : rs.getString("LAYER_2").toUpperCase());	
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD"));
				h.put("TICK",rs.getString("TICK") == null ? "" : rs.getString("TICK"));
				
				
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
}
	
