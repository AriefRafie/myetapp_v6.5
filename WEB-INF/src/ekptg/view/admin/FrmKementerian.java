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

	@SuppressWarnings("serial")
	public class FrmKementerian extends AjaxBasedModule {
		
		static Logger myLogger = Logger.getLogger(ekptg.view.admin.FrmKementerian.class);
		String skrin_name = "app/admin/Kementerian/index.jsp";
		
		@Override
		public String doTemplate2() throws Exception {	
			
			List listKementerian = null;
			List listAgensi = null;
			List listAll = null;
			List listPenggunaKJP = null;
			
			List list_TBLRUJKEMENTERIAN = null;
			List list_TBLRUJAGENSI = null;
			List list_TBLRUJNEGERI = null;
			
			List listKJPAktif = null;
			List listAgensiAktif = null;
			List listPeranan = null;
			List listAduanAgensi = null;
			List listLogAgensi = null;
			
			Hashtable detailsKementerian = null;
			Hashtable detailsAgensi = null;
			
			HttpSession session = this.request.getSession();
			String command = getParam("command");
			String action = getParam("action");
			String carianUmum = "";
			String carianAgensi = "";
			
			list_TBLRUJKEMENTERIAN = listTableRujukanV3(session,"TBLRUJKEMENTERIAN","","0");
			this.context.put("list_TBLRUJKEMENTERIAN",list_TBLRUJKEMENTERIAN);
			
			list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSI","","0");
			this.context.put("list_TBLRUJAGENSI",list_TBLRUJAGENSI);
			
			//initialize
			this.context.put("carianUmum", "");
			
			myLogger.info(" command : "+command);
			
			if(command.equals("batalCarianUtama"))
			{
				skrin_name = "app/admin/Kementerian/index.jsp";
			}
			else if(command.equals("closeDiv"))
			{
				skrin_name = "app/admin/Kementerian/blank_Kementerian.jsp";
			}
			else if(command.equals("getStatsKJP"))
			{
				String STATS = getParam("STATS");
				this.context.put("STATS", STATS);
				myLogger.info("STATS - "+STATS);
				
				if (STATS.equals("1")){
				
				listKJPAktif = getKJPAktif(session);
				this.context.put("listKJPAktif", listKJPAktif);
				
				skrin_name = "app/admin/Kementerian/SenaraiKJPAktif.jsp";
				
				} else if (STATS.equals("2")){
					
					listAgensiAktif = getAgensiAktif(session);
					this.context.put("listAgensiAktif", listAgensiAktif);
					
				skrin_name = "app/admin/Kementerian/SenaraiAgensiAktif.jsp";
				
				} else if (STATS.equals("3")){
					
					listPeranan = getJumlahPeranan(session);
					this.context.put("listPeranan", listPeranan);
					
				skrin_name = "app/admin/Kementerian/SenaraiKJPPeranan.jsp";
				
				} else if (STATS.equals("4")){
					
					listAduanAgensi = getAduanAgensi(session);
					//this.context.put("listAduanAgensi", listAduanAgensi);
					setupPageListSmall(session, action, listAduanAgensi, "listAduanAgensi",command,"statKJPAll");
					
				skrin_name = "app/admin/Kementerian/SenaraiAduanAgensi.jsp";
				
				} else if (STATS.equals("5")){
					
					listLogAgensi = getLogAgensi(session);
					this.context.put("listLogAgensi", listLogAgensi);
					//setupPageListSmall(session, action, listAduanAgensi, "listAduanAgensi",command,"statKJPAll");
					
				skrin_name = "app/admin/Kementerian/SenaraiLogAgensiPrint.jsp";
				
				}
				

			}
			else if(command.equals("showListAgensi"))
			{
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				list_TBLRUJAGENSI = listTableRujukanV3(session,"TBLRUJAGENSIBYKEMENTERIAN",ID_KEMENTERIAN,"0");
				this.context.put("list_TBLRUJAGENSI",list_TBLRUJAGENSI);
				
				skrin_name = "app/admin/Kementerian/showListAgensiCarian.jsp";
			}
			
			else if(command.equals("showSenaraiKementerian"))
			{
				 action = getParam("action");
				 
				 this.context.put("SuccessMesejDeleteUser", "");
					String FLAG_DELETE = getParam("FLAG_DELETE");
					
					if(FLAG_DELETE.equals("Y"))
					{
						String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
						deleteKementerian(session,ID_KEMENTERIAN);
						this.context.put("SuccessMesejDeleteUser", "Maklumat Kementerian Berjaya Dihapus");
					}
				 
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				
				if (!carianUmum.equals("")){
				listKementerian = getListKementerian(session, carianUmum);
				setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				//this.context.put("listKementerian",listKementerian);
				}
				else {
				listKementerian = getListKementerian(session, "");
				setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
					//this.context.put("listKementerian",listKementerian);
				}
				
				skrin_name = "app/admin/Kementerian/SenaraiKementerian.jsp";

			}
			else if(command.equals("viewDetailsKementerian"))
			{
				 
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				detailsKementerian = getDetailsKementerian(session, ID_KEMENTERIAN);
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("detailsKementerian",detailsKementerian);
				
				skrin_name = "app/admin/Kementerian/viewKementerian.jsp";

			}
			else if(command.equals("addKementerian"))
			{
				 
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				detailsKementerian = getDetailsKementerian(session, ID_KEMENTERIAN);
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("detailsKementerian",detailsKementerian);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				skrin_name = "app/admin/Kementerian/AddKementerian.jsp";
			}
			else if(command.equals("saveKementerian"))
			{
				 
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				if(ID_KEMENTERIAN==null || ID_KEMENTERIAN.equals("") || ID_KEMENTERIAN.equals("null"))
				{
					System.out.println("ID_KEMENTERIAN insert? -- "+ID_KEMENTERIAN);
					ID_KEMENTERIAN = saveDetailsKementerian(session, ID_KEMENTERIAN);
					System.out.println("ID_KEMENTERIAN after insert? -- "+ID_KEMENTERIAN);
					
					carianUmum = getParam("carianUmum");
					
					listKementerian = getListKementerian(session, carianUmum);
					setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
					 //this.context.put("listBahagianHQ",listUnitHQ);
					 
					skrin_name = "app/admin/Kementerian/SenaraiKementerian.jsp";
					
					} else{
						
					System.out.println("ID_KEMENTERIAN update? -- "+ID_KEMENTERIAN);
					ID_KEMENTERIAN = saveDetailsKementerian(session, ID_KEMENTERIAN);
					System.out.println("ID_KEMENTERIAN after update? -- "+ID_KEMENTERIAN);

					detailsKementerian = getDetailsKementerian(session, ID_KEMENTERIAN);
					//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
					this.context.put("detailsKementerian",detailsKementerian);
					
					skrin_name = "app/admin/Kementerian/viewKementerian.jsp";
					}
			}
			else if(command.equals("carianAgensi"))
			{
				 action = getParam("action");
				 
				 String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
					
				 carianAgensi = getParam("carianAgensi");
				this.context.put("carianAgensi", carianAgensi);
				
				if (!carianAgensi.equals("")){
				listAgensi = getListAgensi(session, carianAgensi,ID_KEMENTERIAN);
				setupPageList(session, action, listAgensi, "listAgensi","showSenaraiAgensi","div_SenaraiAgensi"+ID_KEMENTERIAN);
				//this.context.put("listAgensi",listAgensi);
				}
				else {
				listAgensi = getListAgensi(session, "",ID_KEMENTERIAN);
				setupPageList(session, action, listAgensi, "listAgensi","showSenaraiAgensi","div_SenaraiAgensi"+ID_KEMENTERIAN);
				//this.context.put("listAgensi",listAgensi);
				}
				
				skrin_name = "app/admin/Kementerian/CTAgensi.jsp";

			}
			else if(command.equals("showSenaraiAgensi"))
			{
				 action = getParam("action");
				 
				 String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
					
				 carianAgensi = getParam("carianAgensi");
				this.context.put("carianAgensi", carianAgensi);
				
				if (!carianAgensi.equals("")){
				listAgensi = getListAgensi(session, carianAgensi,ID_KEMENTERIAN);
				setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
				//this.context.put("listAgensi",listAgensi);
				}
				
				skrin_name = "app/admin/Kementerian/SenaraiAgensi.jsp";

			}
			else if(command.equals("viewDetailsAgensi"))
			{
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				 
				String ID_AGENSI = getParam("ID_AGENSI");
				this.context.put("ID_AGENSI", ID_AGENSI);
				
				detailsAgensi = getDetailsAgensi(session, ID_AGENSI);
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("detailsAgensi",detailsAgensi);
				
				skrin_name = "app/admin/Kementerian/viewAgensi.jsp";

			}
			else if(command.equals("addAgensi"))
			{
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				String ID_AGENSI = getParam("ID_AGENSI");
				this.context.put("ID_AGENSI", ID_AGENSI);
				
				detailsAgensi = getDetailsAgensi(session, ID_AGENSI);
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("detailsAgensi",detailsAgensi);
				
				list_TBLRUJNEGERI = listTableRujukanV3(session,"TBLRUJNEGERI","0","0");
				this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);
				
				skrin_name = "app/admin/Kementerian/AddAgensi.jsp";
			}
			else if(command.equals("saveAgensi"))
			{
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				String ID_AGENSI = getParam("ID_AGENSI");
				this.context.put("ID_AGENSI", ID_AGENSI);
				
				if(ID_AGENSI==null || ID_AGENSI.equals("") || ID_AGENSI.equals("null"))
				{
					System.out.println("ID_AGENSI insert? -- "+ID_AGENSI);
					ID_AGENSI = saveDetailsAgensi(session, ID_AGENSI,ID_KEMENTERIAN);
					System.out.println("ID_AGENSI after insert? -- "+ID_AGENSI);
					
					carianAgensi = getParam("carianAgensi");
					this.context.put("carianAgensi", carianAgensi);
					
					if (!carianAgensi.equals("")){
					listAgensi = getListAgensi(session, carianAgensi,ID_KEMENTERIAN);
					//setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
					this.context.put("listAgensi",listAgensi);
					}
					else {
					listAgensi = getListAgensi(session, "",ID_KEMENTERIAN);
					//setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
					this.context.put("listAgensi",listAgensi);
					}
					
					skrin_name = "app/admin/Kementerian/CTAgensi.jsp";
					
					} else{
						
					System.out.println("ID_AGENSI update? -- "+ID_AGENSI);
					ID_AGENSI = saveDetailsAgensi(session, ID_AGENSI,ID_KEMENTERIAN);
					System.out.println("ID_AGENSI after update? -- "+ID_AGENSI);

					detailsAgensi = getDetailsAgensi(session, ID_AGENSI);
					//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
					this.context.put("detailsAgensi",detailsAgensi);
					
					skrin_name = "app/admin/Kementerian/viewAgensi.jsp";
					}
			}
			else if(command.equals("cetakAllKementerian"))
			{
				 //action = getParam("action");
				 
				listAll = getListAllKementerian(session);
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("listAll",listAll);
				
				skrin_name = "app/admin/Kementerian/PrintListAllKJP.jsp";

			}
			else if(command.equals("cetakListKementerian"))
			{
				 action = getParam("action");
				 
				carianUmum = getParam("carianUmum");
				this.context.put("carianUmum", carianUmum);
				
				if (!carianUmum.equals("")){
				listKementerian = getListKementerian(session, carianUmum);
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("listKementerian",listKementerian);
				}
				else {
				listKementerian = getListKementerian(session, "");
				//setupPageList(session, action, listKementerian, "listKementerian",command,"div_senaraiUtama");
				this.context.put("listKementerian",listKementerian);
				}
				
				skrin_name = "app/admin/Kementerian/PrintListKementerian.jsp";

			}
			else if(command.equals("cetakDetailsKementerian"))
			{
				 
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				detailsKementerian = getDetailsKementerian(session, ID_KEMENTERIAN);
				this.context.put("detailsKementerian",detailsKementerian);
				
				action = getParam("action");
				
				carianAgensi = getParam("carianAgensi");
				this.context.put("carianAgensi", carianAgensi);
				
				if (!carianAgensi.equals("")){
				listAgensi = getListAgensi(session, carianAgensi,ID_KEMENTERIAN);
				//setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
				this.context.put("listAgensi",listAgensi);
				}
				else{
				listAgensi = getListAgensi(session, "",ID_KEMENTERIAN);
				//setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
				this.context.put("listAgensi",listAgensi);	
				}
				
				skrin_name = "app/admin/Kementerian/PrintDetailKementerian.jsp";

			}
			else if(command.equals("cetakBorangSemakanKJP"))
			{
				 //print borang semakan kjp
				
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				myLogger.info("ID_KEMENTERIAN : "+ID_KEMENTERIAN);
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				detailsKementerian = getDetailsKementerian(session, ID_KEMENTERIAN);
				this.context.put("detailsKementerian",detailsKementerian);
				
				action = getParam("action");
				
				carianAgensi = getParam("carianAgensi");
				this.context.put("carianAgensi", carianAgensi);
				
				if (!carianAgensi.equals("")){
				listAgensi = getListAgensi(session, carianAgensi,ID_KEMENTERIAN);
				//setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
				this.context.put("listAgensi",listAgensi);
				}
				else{
				listAgensi = getListAgensi(session, "",ID_KEMENTERIAN);
				//setupPageList(session, action, listAgensi, "listAgensi",command,"div_SenaraiAgensi"+ID_KEMENTERIAN);
				this.context.put("listAgensi",listAgensi);	
				}
				
				skrin_name = "app/admin/Kementerian/PrintBorangKJP.jsp";

			}
			else if(command.equals("cetakBorangSemakanPenggunaKJP"))
			{
				 //print borang semakan kjp
				
				String ID_KEMENTERIAN = getParam("ID_KEMENTERIAN");
				myLogger.info("ID_KEMENTERIAN : "+ID_KEMENTERIAN);
				this.context.put("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
				action = getParam("action");
				
				detailsKementerian = getDetailsKementerian(session, ID_KEMENTERIAN);
				this.context.put("detailsKementerian",detailsKementerian);
				
				listPenggunaKJP = getListPenggunaKJP(session, ID_KEMENTERIAN);
				this.context.put("listPenggunaKJP",listPenggunaKJP);	

				skrin_name = "app/admin/Kementerian/PrintSenaraiPenggunaKJP.jsp";

			}
			myLogger.info("skrin_name -- "+skrin_name);
			return skrin_name;
			
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListPenggunaKJP(HttpSession session, String ID_KEMENTERIAN)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listPenggunaKJP = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = 	" SELECT U.USER_ID, U.USER_LOGIN, UPPER (U.USER_NAME) AS NAMA_PENUH, UI.NO_KP, UK.ID_KEMENTERIAN, " +
						" UK.ID_AGENSI, UK.FLAG_AKTIF, (CASE WHEN UK.FLAG_AKTIF = 1 THEN 'AKTIF' " +
						" WHEN UK.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS KEAKTIFAN, UI.ID_JAWATAN," +
						" (CASE WHEN UI.ID_JAWATAN = 4 THEN 'PELULUS' WHEN UI.ID_JAWATAN = 9 THEN 'PENYEMAK' " +
						" WHEN UI.ID_JAWATAN = 24 THEN 'PENYEDIA' ELSE '' END) AS NAMA_JAWATAN " +
						" FROM USERS U, USERS_INTERNAL UI, USERS_KEMENTERIAN UK, ROLE R, WEB_LOGGER WL, TBLRUJKEMENTERIAN TRK, " +
						" TBLRUJAGENSI TRA, TBLRUJNEGERI NEG " +
						" WHERE U.USER_ID = UK.USER_ID AND UI.USER_ID = UK.USER_ID AND U.USER_ROLE = R.NAME(+) " +
						" AND U.USER_LOGIN = WL.USER_NAME(+) AND UK.ID_KEMENTERIAN = TRK.ID_KEMENTERIAN " +
						" AND UK.ID_AGENSI = TRA.ID_AGENSI AND TRK.ID_NEGERI = NEG.ID_NEGERI(+) " +
						" AND (   UPPER (UK.FLAG_AKTIF) IS NULL OR UPPER (UK.FLAG_AKTIF) = '1' OR UPPER (UK.FLAG_AKTIF) = '') " +
						" AND UK.ID_KEMENTERIAN = "+ ID_KEMENTERIAN;
				
				sql+= 	" GROUP BY U.USER_ID, U.USER_LOGIN, U.USER_NAME, UI.NO_KP, UK.ID_KEMENTERIAN, UK.ID_AGENSI, " +
						" UK.FLAG_AKTIF, UI.ID_JAWATAN ORDER BY U.USER_NAME ";
					
					
					myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listPenggunaKJP = Collections.synchronizedList(new ArrayList());
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
							h.put("USER_LOGIN",checkIsNull(rs.getString("USER_LOGIN")));
							h.put("NAMA_PENUH",checkIsNull(rs.getString("NAMA_PENUH")));
							h.put("NO_KP",checkIsNull(rs.getString("NO_KP")));
							h.put("ID_KEMENTERIAN",checkIsNull(rs.getString("ID_KEMENTERIAN")));
							h.put("ID_AGENSI",checkIsNull(rs.getString("ID_AGENSI")));
							h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
							h.put("KEAKTIFAN",checkIsNull(rs.getString("KEAKTIFAN")));
							h.put("ID_JAWATAN",checkIsNull(rs.getString("ID_JAWATAN")));
							h.put("NAMA_JAWATAN",checkIsNull(rs.getString("NAMA_JAWATAN")));
							
							listPenggunaKJP.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listPenggunaKJP;
					
				}
		
		public String saveDetailsAgensi (HttpSession session,String ID_AGENSI,String ID_KEMENTERIAN) throws Exception {
			Connection conn = null;
			Db db = null;
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			String sql = "";

			long ID_AGENSI_INSERT = 0;
			
			try {
				db = new Db();
				conn = db.getConnection();
				conn.setAutoCommit(false);
				
				String NAMA_AGENSI = getParam("NAMA_AGENSI_"+ID_AGENSI);
				String KOD_AGENSI = getParam("KOD_AGENSI_"+ID_AGENSI);
				
				String ALAMAT1 = getParam("ALAMAT1_"+ID_AGENSI);
				String ALAMAT2 = getParam("ALAMAT2_"+ID_AGENSI);
				String ALAMAT3 = getParam("ALAMAT3_"+ID_AGENSI);
				String POSKOD = getParam("POSKOD_"+ID_AGENSI);
				String ID_NEGERI = getParam("ID_NEGERI_"+ID_AGENSI);
				
				String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_AGENSI);
				
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.add("ID_KEMENTERIAN", ID_KEMENTERIAN);
				r.add("NAMA_AGENSI", NAMA_AGENSI);
				r.add("KOD_AGENSI", KOD_AGENSI);
				r.add("ALAMAT1", ALAMAT1);
				r.add("ALAMAT2", ALAMAT2);
				r.add("ALAMAT3", ALAMAT3);
				r.add("POSKOD", POSKOD);
				r.add("ID_NEGERI", ID_NEGERI);
				r.add("FLAG_AKTIF", FLAG_AKTIF);
				
				if(ID_AGENSI.equals("")){
					ID_AGENSI_INSERT = DB.getNextID(db,"TBLRUJAGENSI_SEQ");
					ID_AGENSI = ID_AGENSI_INSERT+"";
				
				r.add("ID_AGENSI", ID_AGENSI);
				r.add("ID_MASUK", USER_ID_SYSTEM);
				r.add("TARIKH_MASUK", r.unquote("sysdate"));
				
				sql = r.getSQLInsert("TBLRUJAGENSI");	
				
				}
				else{
				r.update("ID_AGENSI", ID_AGENSI);	
				r.add("ID_KEMASKINI", USER_ID_SYSTEM);
				r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
				
				sql = r.getSQLUpdate("TBLRUJAGENSI");	
				
				}

				myLogger.info("ACTION TBLRUJAGENSI : "+sql.toUpperCase());
				
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
		    	throw new Exception("Ralat action save agensi :"+se.getMessage());
			}
			catch (Exception re) {
				throw re;
			}finally {
				if (db != null)
					db.close();
			}
			return ID_AGENSI;
		}
		
		@SuppressWarnings("unchecked")
		public Hashtable getDetailsAgensi (HttpSession session, String ID_AGENSI) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_AGENSI.equals(""))
				{
					
					sql = 	" SELECT A.ID_AGENSI, A.KOD_AGENSI, UPPER(A.NAMA_AGENSI) AS NAMA_AGENSI, " +
							" UPPER(A.ALAMAT1) AS ALAMAT1, UPPER(A.ALAMAT2) AS ALAMAT2, UPPER(A.ALAMAT3) AS ALAMAT3, " +
							" A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, " +
							" A.ID_MASUK, A.TARIKH_MASUK, A.ID_KEMASKINI, TO_CHAR(A.TARIKH_KEMASKINI,'DD/MM/YYYY') TARIKH_KEMASKINI , " +
							" A.NAMA_AGENSI_LAMA , A.KOD_DDSA, A.EMEL, " +
							" (CASE WHEN A.FLAG_AKTIF = 1 THEN 'AKTIF' " +
							" WHEN A.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
							" ELSE 'AKTIF' END) AS FLAG_AKTIF " +
							" FROM TBLRUJAGENSI A, TBLRUJNEGERI B " +
							" WHERE A.ID_NEGERI = B.ID_NEGERI " +
							" AND A.ID_AGENSI = "+ID_AGENSI ;
					
					rs = stmt.executeQuery(sql);				
									
					while (rs.next()) {
						
						h.put("ID_AGENSI",checkIsNull(rs.getString("ID_AGENSI")));
						h.put("KOD_AGENSI",checkIsNull(rs.getString("KOD_AGENSI")));
						h.put("NAMA_AGENSI",checkIsNull(rs.getString("NAMA_AGENSI")));
						
						h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1")));
						h.put("ALAMAT2",checkIsNull(rs.getString("ALAMAT2")));
						h.put("ALAMAT3",checkIsNull(rs.getString("ALAMAT3")));
						h.put("POSKOD",checkIsNull(rs.getString("POSKOD")));
						h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
						
						h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
						
						h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
						h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
						h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
						h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
					
						h.put("NAMA_KEMENTERIAN_LAMA",checkIsNull(rs.getString("NAMA_AGENSI_LAMA")));
						h.put("KOD_DDSA",checkIsNull(rs.getString("KOD_DDSA")));
						h.put("EMEL",checkIsNull(rs.getString("EMEL")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						}
				}
				else
				{
					h.put("ID_AGENSI","");
					h.put("KOD_AGENSI","");
					h.put("NAMA_AGENSI","");
					
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("NAMA_NEGERI","");
					
					h.put("ID_NEGERI","");
					
					h.put("ID_MASUK","");
					h.put("TARIKH_MASUK","");
					h.put("ID_KEMASKINI","");
					h.put("TARIKH_KEMASKINI","");
				
					h.put("NAMA_AGENSI_LAMA","");
					h.put("KOD_DDSA","");
					h.put("EMEL","");
					h.put("FLAG_AKTIF","");
				}
				
				myLogger.info(" SQL Details agensi :" + sql.toUpperCase());
				
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
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListAgensi(HttpSession session, String carianAgensi, String ID_KEMENTERIAN)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listAgensi = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				sql = 	" SELECT A.ID_AGENSI, A.KOD_AGENSI, UPPER(A.NAMA_AGENSI) AS NAMA_AGENSI, " +
						" A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, " +
						" A.ID_MASUK, A.TARIKH_MASUK, A.ID_KEMASKINI, A.TARIKH_KEMASKINI, " +
						" A.NAMA_AGENSI_LAMA , A.KOD_DDSA, A.EMEL, "+
						" (CASE WHEN A.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN A.FLAG_AKTIF = 2 " +
						" THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS KEAKTIFAN  "+
						" FROM TBLRUJAGENSI A, TBLRUJNEGERI B " +
						" WHERE A.ID_NEGERI = B.ID_NEGERI " ;
				
				sql+=	" AND A.ID_KEMENTERIAN = "+ ID_KEMENTERIAN ;
				
				if (!carianAgensi.equals("")){
				
						sql+=	" AND ( " ;
						
						sql+= 	" UPPER (A.NAMA_AGENSI) LIKE UPPER ('%"+carianAgensi.trim()+"%') " +
								" OR UPPER (A.KOD_AGENSI) LIKE UPPER ('%"+carianAgensi.trim()+"%') " +
								" OR UPPER (A.NAMA_AGENSI_LAMA) LIKE UPPER ('%"+carianAgensi.trim()+"%') " +
								" OR UPPER (B.NAMA_NEGERI) LIKE UPPER ('%"+carianAgensi.trim()+"%') )";
						
						sql+=	" AND A.ID_KEMENTERIAN = "+ ID_KEMENTERIAN ;
					}	
				
				sql += " ORDER BY A.KOD_AGENSI, A.ID_NEGERI ";
					
					
					myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listAgensi = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_AGENSI",checkIsNull(rs.getString("ID_AGENSI")));
							h.put("KOD_AGENSI",checkIsNull(rs.getString("KOD_AGENSI")));
							h.put("NAMA_AGENSI",checkIsNull(rs.getString("NAMA_AGENSI")));
							
							h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT2"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT3"))+"\n<br>"+
									checkIsNull(rs.getString("POSKOD"))+"\n<br>"+
									checkIsNull(rs.getString("NAMA_NEGERI")));
							
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							
							h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
							h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
							h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
							h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
						
							h.put("NAMA_AGENSI_LAMA",checkIsNull(rs.getString("NAMA_AGENSI_LAMA")));
							h.put("KOD_DDSA",checkIsNull(rs.getString("KOD_DDSA")));
							h.put("EMEL",checkIsNull(rs.getString("EMEL")));
							h.put("KEAKTIFAN",checkIsNull(rs.getString("KEAKTIFAN")));
							
							listAgensi.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listAgensi;
					
				}
		
			public String saveDetailsKementerian (HttpSession session,String ID_KEMENTERIAN) throws Exception {
				Connection conn = null;
				Db db = null;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				String sql = "";

				long ID_KEMENTERIAN_INSERT = 0;
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					String NAMA_KEMENTERIAN = getParam("NAMA_KEMENTERIAN_"+ID_KEMENTERIAN);
					String KOD_KEMENTERIAN = getParam("KOD_KEMENTERIAN_"+ID_KEMENTERIAN);
					
					String ALAMAT1 = getParam("ALAMAT1_"+ID_KEMENTERIAN);
					String ALAMAT2 = getParam("ALAMAT2_"+ID_KEMENTERIAN);
					String ALAMAT3 = getParam("ALAMAT3_"+ID_KEMENTERIAN);
					String POSKOD = getParam("POSKOD_"+ID_KEMENTERIAN);
					String ID_NEGERI = getParam("ID_NEGERI_"+ID_KEMENTERIAN);
					
					String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_KEMENTERIAN);
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					r.add("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					r.add("KOD_KEMENTERIAN", KOD_KEMENTERIAN);
					r.add("ALAMAT1", ALAMAT1);
					r.add("ALAMAT2", ALAMAT2);
					r.add("ALAMAT3", ALAMAT3);
					r.add("POSKOD", POSKOD);
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("FLAG_AKTIF", FLAG_AKTIF);
					
					if(ID_KEMENTERIAN.equals("")){
						ID_KEMENTERIAN_INSERT = DB.getNextID(db,"TBLRUJKEMENTERIAN_SEQ");
						ID_KEMENTERIAN = ID_KEMENTERIAN_INSERT+"";
					
					r.add("ID_KEMENTERIAN", ID_KEMENTERIAN);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_MASUK", r.unquote("sysdate"));
					
					sql = r.getSQLInsert("TBLRUJKEMENTERIAN");	
					
					}
					else{
					r.update("ID_KEMENTERIAN", ID_KEMENTERIAN);	
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					
					sql = r.getSQLUpdate("TBLRUJKEMENTERIAN");	
					
					}

					myLogger.info("ACTION TBLRUJKEMENTERIAN : "+sql.toUpperCase());
					
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
			    	throw new Exception("Ralat action save kementerian :"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
				return ID_KEMENTERIAN;
			}
		
		@SuppressWarnings("unchecked")
		public Hashtable getDetailsKementerian (HttpSession session, String ID_KEMENTERIAN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_KEMENTERIAN.equals(""))
				{
					
					sql = 	" SELECT A.ID_KEMENTERIAN, A.KOD_KEMENTERIAN, UPPER(A.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN, " +
							" UPPER(A.ALAMAT1) AS ALAMAT1, UPPER(A.ALAMAT2) AS ALAMAT2, UPPER(A.ALAMAT3) AS ALAMAT3, " +
							" A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, " +
							" A.ID_MASUK, A.TARIKH_MASUK, A.ID_KEMASKINI, TO_CHAR(A.TARIKH_KEMASKINI,'DD/MM/YYYY') TARIKH_KEMASKINI , " +
							" A.NAMA_KEMENTERIAN_LAMA , A.KOD_DDSA, A.EMEL, " +
							" (CASE WHEN A.FLAG_AKTIF = 1 THEN 'AKTIF' " +
							" WHEN A.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
							" ELSE 'AKTIF' END) AS FLAG_AKTIF, U.USER_NAME " +
							" FROM TBLRUJKEMENTERIAN A, TBLRUJNEGERI B, USERS U " +
							" WHERE A.ID_NEGERI = B.ID_NEGERI " +
							" AND A.ID_KEMASKINI = U.USER_ID(+) "+
							" AND A.ID_KEMENTERIAN = "+ID_KEMENTERIAN ;
					
					rs = stmt.executeQuery(sql);				
									
					while (rs.next()) {
						
						h.put("ID_KEMENTERIAN",checkIsNull(rs.getString("ID_KEMENTERIAN")));
						h.put("KOD_KEMENTERIAN",checkIsNull(rs.getString("KOD_KEMENTERIAN")));
						h.put("NAMA_KEMENTERIAN",checkIsNull(rs.getString("NAMA_KEMENTERIAN")));
						
						h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1")));
						h.put("ALAMAT2",checkIsNull(rs.getString("ALAMAT2")));
						h.put("ALAMAT3",checkIsNull(rs.getString("ALAMAT3")));
						h.put("POSKOD",checkIsNull(rs.getString("POSKOD")));
						h.put("NAMA_NEGERI",checkIsNull(rs.getString("NAMA_NEGERI")));
						
						h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
						
						h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
						h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
						h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
						h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
					
						h.put("NAMA_KEMENTERIAN_LAMA",checkIsNull(rs.getString("NAMA_KEMENTERIAN_LAMA")));
						h.put("KOD_DDSA",checkIsNull(rs.getString("KOD_DDSA")));
						h.put("EMEL",checkIsNull(rs.getString("EMEL")));
						h.put("FLAG_AKTIF",checkIsNull(rs.getString("FLAG_AKTIF")));
						h.put("USER_NAME",checkIsNull(rs.getString("USER_NAME")));
						
						}
				}
				else
				{
					h.put("ID_KEMENTERIAN","");
					h.put("KOD_KEMENTERIAN","");
					h.put("NAMA_KEMENTERIAN","");
					
					h.put("ALAMAT1","");
					h.put("ALAMAT2","");
					h.put("ALAMAT3","");
					h.put("POSKOD","");
					h.put("NAMA_NEGERI","");
					
					h.put("ID_NEGERI","");
					
					h.put("ID_MASUK","");
					h.put("TARIKH_MASUK","");
					h.put("ID_KEMASKINI","");
					h.put("TARIKH_KEMASKINI","");
				
					h.put("NAMA_KEMENTERIAN_LAMA","");
					h.put("KOD_DDSA","");
					h.put("EMEL","");
					h.put("FLAG_AKTIF","");
					h.put("USER_NAME","");
				}
				
				myLogger.info(" SQL Details kementerian :" + sql.toUpperCase());
				
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
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getLogAgensi(HttpSession session)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listLogAgensi = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				
						sql = 	" SELECT 1 AS LAYER, A.NAMA_KEMENTERIAN AS LAYER_1, '' AS LAYER_2,  " +
								" TO_CHAR (A.ID_KEMENTERIAN) AS ID_KEMENTERIAN, '' AS ID_AGENSI, 0 AS TOTALLOG " +
								" FROM TBLRUJKEMENTERIAN A WHERE A.ID_KEMENTERIAN IS NOT NULL " +
								" AND A.KOD_KEMENTERIAN != '00' AND A.KOD_KEMENTERIAN != '0' " ;
						
						sql+=	" UNION " ;
						
						sql+=	" SELECT 2 AS LAYER, '' AS LAYER_1, A.NAMA_AGENSI AS LAYER_2, " +
								" TO_CHAR (A.ID_KEMENTERIAN) AS ID_KEMENTERIAN, TO_CHAR (A.ID_AGENSI) AS ID_AGENSI, COUNT(*) AS TOTALLOG " +
								" FROM TBLRUJAGENSI A, TBLRUJKEMENTERIAN B, USERS_KEMENTERIAN UK, USERS U,WEB_LOGGER WL " +
								" WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND UK.USER_ID = U.USER_ID " +
								" AND UK.ID_AGENSI = A.ID_AGENSI AND U.USER_LOGIN = WL.USER_NAME " ;
						
						sql+=	" GROUP BY A.ID_KEMENTERIAN, NAMA_KEMENTERIAN, A.ID_AGENSI, NAMA_AGENSI " +
								" ORDER BY ID_KEMENTERIAN, LAYER_1, ID_AGENSI, LAYER_2 ASC ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listLogAgensi = Collections.synchronizedList(new ArrayList());
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
							
							h.put("LAYER",checkIsNull(rs.getString("LAYER")));
							h.put("LAYER_1",checkIsNull(rs.getString("LAYER_1")));
							
							h.put("LAYER_2",checkIsNull(rs.getString("LAYER_2")));
							h.put("ID_KEMENTERIAN",checkIsNull(rs.getString("ID_KEMENTERIAN")));
							
							h.put("ID_AGENSI",checkIsNull(rs.getString("ID_AGENSI")));
							h.put("TOTALLOG",checkIsNull(rs.getString("TOTALLOG")));
							
							listLogAgensi.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listLogAgensi;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getAduanAgensi(HttpSession session)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listAduanAgensi = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				
						sql = 	" SELECT A.NAMA_AGENSI , COUNT (*) TOTALADUAN FROM TBLRUJAGENSI A, USERS_KEMENTERIAN UK, USERS U," +
								" TBLRUJJENISADUAN JA, TBLESADUAN AD WHERE A.ID_AGENSI = UK.ID_AGENSI AND UK.USER_ID = U.USER_ID " +
								" AND AD.USER_ID = U.USER_ID AND AD.ID_JENISADUAN = JA.ID_JENISADUAN GROUP BY A.NAMA_AGENSI ";
						
						sql+= 	" UNION ";
						
						sql+=	" SELECT A.NAMA_AGENSI ,0 AS TOTALADUAN FROM TBLRUJAGENSI A WHERE A.ID_AGENSI != (SELECT B.ID_AGENSI " +
								" FROM TBLRUJAGENSI B, USERS_KEMENTERIAN UK, USERS U, TBLRUJJENISADUAN JA, TBLESADUAN AD " +
								" WHERE B.ID_AGENSI = UK.ID_AGENSI AND UK.USER_ID = U.USER_ID AND AD.USER_ID = U.USER_ID " +
								" AND AD.ID_JENISADUAN = JA.ID_JENISADUAN) ";
						
						sql+=	" ORDER BY TOTALADUAN DESC ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listAduanAgensi = Collections.synchronizedList(new ArrayList());
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
							h.put("NAMA_AGENSI",checkIsNull(rs.getString("NAMA_AGENSI")));
							h.put("TOTALADUAN",checkIsNull(rs.getString("TOTALADUAN")));
							
							listAduanAgensi.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listAduanAgensi;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getJumlahPeranan(HttpSession session)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listJumlahPeranan = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				
						sql = 	" SELECT COUNT (USER_ID) AS TOTALUSERKJP, NAMA_JAWATAN FROM ( " +
								" SELECT   U.USER_ID,UK.ID_KEMENTERIAN,UK.ID_AGENSI, " +
								" UPPER (TRK.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN, " +
								" UPPER (TRA.NAMA_AGENSI) AS NAMA_AGENSI, UI.ID_JAWATAN, (CASE WHEN UI.ID_JAWATAN = 4 THEN 'PELULUS' " +
								" WHEN UI.ID_JAWATAN = 9 THEN 'PENYEMAK' WHEN UI.ID_JAWATAN = 24 THEN 'PENYEDIA' " +
								" ELSE 'TIADA MAKLUMAT' END) AS NAMA_JAWATAN " +
								" FROM USERS U, USERS_INTERNAL UI, USERS_KEMENTERIAN UK, ROLE R, WEB_LOGGER WL, TBLRUJKEMENTERIAN TRK, " +
								" TBLRUJAGENSI TRA, TBLRUJNEGERI NEG WHERE U.USER_ID = UK.USER_ID AND UI.USER_ID = UK.USER_ID " +
								" AND U.USER_ROLE = R.NAME(+) AND U.USER_LOGIN = WL.USER_NAME(+) AND UK.ID_KEMENTERIAN = TRK.ID_KEMENTERIAN " +
								" AND UK.ID_AGENSI = TRA.ID_AGENSI AND TRK.ID_NEGERI = NEG.ID_NEGERI(+) "+ //AND (UK.ID_KEMENTERIAN = '1') " +
								" AND (UPPER (UK.FLAG_AKTIF) IS NULL OR UPPER (UK.FLAG_AKTIF) = '1' OR UPPER (UK.FLAG_AKTIF) = '') " +
								" GROUP BY U.USER_ID,UK.ID_KEMENTERIAN, UK.ID_AGENSI, TRK.NAMA_KEMENTERIAN, TRA.NAMA_AGENSI,UI.ID_JAWATAN " +
								" ORDER BY U.USER_NAME) GROUP BY NAMA_JAWATAN ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listJumlahPeranan = Collections.synchronizedList(new ArrayList());
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
							h.put("TOTALUSERKJP",checkIsNull(rs.getString("TOTALUSERKJP")));
							h.put("NAMA_JAWATAN",checkIsNull(rs.getString("NAMA_JAWATAN")));
							
							listJumlahPeranan.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listJumlahPeranan;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getAgensiAktif(HttpSession session)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listAgensiAktif = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				
						sql = 	" SELECT V.ID_AGENSI, V.NAMA_AGENSI, V.TOTALAGENSI " +
								" FROM (SELECT   A.ID_AGENSI, A.NAMA_AGENSI, COUNT (WL.USER_NAME) TOTALAGENSI " +
								" FROM WEB_LOGGER WL, USERS U, USERS_KEMENTERIAN UK, TBLRUJKEMENTERIAN K,  TBLRUJAGENSI A " +
								" WHERE WL.USER_NAME = U.USER_LOGIN AND U.USER_ID = UK.USER_ID AND UK.ID_AGENSI = A.ID_AGENSI " +
								" AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN GROUP BY   A.ID_AGENSI,A.NAMA_AGENSI " +
								" ORDER BY   TOTALAGENSI DESC) V WHERE   ROWNUM <= 5 ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listAgensiAktif = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_AGENSI",checkIsNull(rs.getString("ID_AGENSI")));
							h.put("NAMA_AGENSI",checkIsNull(rs.getString("NAMA_AGENSI")));
							h.put("TOTALAGENSI",checkIsNull(rs.getString("TOTALAGENSI")));
							
							listAgensiAktif.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listAgensiAktif;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getKJPAktif(HttpSession session)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listKJPAktif = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				
						sql = 	" SELECT V.ID_KEMENTERIAN, V.NAMA_KEMENTERIAN, V.TOTALKJP FROM  " +
								" (SELECT K.ID_KEMENTERIAN, K.NAMA_KEMENTERIAN, COUNT(WL.USER_NAME) TOTALKJP "+
								" FROM WEB_LOGGER WL, USERS U, USERS_KEMENTERIAN UK, TBLRUJKEMENTERIAN K "+
								" WHERE WL.USER_NAME = U.USER_LOGIN "+
								" AND U.USER_ID = UK.USER_ID "+ 
								" AND UK.ID_KEMENTERIAN = K.ID_KEMENTERIAN "+
								" GROUP BY K.ID_KEMENTERIAN, K.NAMA_KEMENTERIAN "+
								" ORDER BY TOTALKJP DESC ) V " +
								" WHERE ROWNUM <= 5 ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listKJPAktif = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_KEMENTERIAN",checkIsNull(rs.getString("ID_KEMENTERIAN")));
							h.put("NAMA_KEMENTERIAN",checkIsNull(rs.getString("NAMA_KEMENTERIAN")));
							h.put("TOTALKJP",checkIsNull(rs.getString("TOTALKJP")));
							
							listKJPAktif.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listKJPAktif;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListAllKementerian(HttpSession session)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listAllKJP = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
						sql = 	" SELECT 1 AS LAYER, UPPER(A.NAMA_KEMENTERIAN) AS LAYER_1, '' AS LAYER_2, A.KOD_KEMENTERIAN AS KOD_KJP, " +
								" TO_CHAR (A.ID_KEMENTERIAN) AS ID_KEMENTERIAN, '' AS ID_AGENSI " +
								" FROM TBLRUJKEMENTERIAN A " +
								" WHERE A.ID_KEMENTERIAN IS NOT NULL " +
								" AND A.KOD_KEMENTERIAN != '00' AND A.KOD_KEMENTERIAN != '0' ";
						
						sql+= 	" UNION ";
						
						sql+= 	" SELECT 2 AS LAYER, '' AS LAYER_1, UPPER(A.NAMA_AGENSI) AS LAYER_2, A.KOD_AGENSI AS KOD_KJP, " +
								" TO_CHAR (A.ID_KEMENTERIAN) AS ID_KEMENTERIAN, TO_CHAR (A.ID_AGENSI) AS ID_AGENSI " +
								" FROM TBLRUJAGENSI A, TBLRUJKEMENTERIAN B " +
								" WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND A.ID_AGENSI IS NOT NULL " +
								" AND A.KOD_AGENSI != '00' AND A.KOD_AGENSI != '0' " ;
						
						sql+= 	" ORDER BY ID_KEMENTERIAN, LAYER_1, ID_AGENSI, LAYER_2 ASC ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listAllKJP = Collections.synchronizedList(new ArrayList());
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
					
							h.put("BIL",new_bil);
							h.put("LAYER",checkIsNull(rs.getString("LAYER")));
							h.put("LAYER_1",checkIsNull(rs.getString("LAYER_1")));
							h.put("LAYER_2",checkIsNull(rs.getString("LAYER_2")));
							
							h.put("KOD_KJP",checkIsNull(rs.getString("KOD_KJP")));
							h.put("ID_KEMENTERIAN",checkIsNull(rs.getString("ID_KEMENTERIAN")));
							h.put("ID_AGENSI",checkIsNull(rs.getString("ID_AGENSI")));

							
							listAllKJP.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listAllKJP;
					
				}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List getListKementerian(HttpSession session, String carianUmum)	throws Exception {
					
			Db db = null;
			ResultSet rs = null;
			Statement stmt = null;
			List listKementerian = null;
			String sql = "";
			
			try {
				db = new Db();
				stmt = db.getStatement();
				
				
				
						sql = 	" SELECT A.ID_KEMENTERIAN, A.KOD_KEMENTERIAN, A.NAMA_KEMENTERIAN, " +
								" A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, " +
								" A.ID_MASUK, A.TARIKH_MASUK, A.ID_KEMASKINI, A.TARIKH_KEMASKINI, " +
								" A.NAMA_KEMENTERIAN_LAMA , A.KOD_DDSA, A.EMEL " +
								" FROM TBLRUJKEMENTERIAN A, TBLRUJNEGERI B " +
								" WHERE A.ID_NEGERI = B.ID_NEGERI " ;
				
				if (!carianUmum.equals("")){	
					
						sql+=	" AND ( " ;
						
						sql+= 	" UPPER (A.NAMA_KEMENTERIAN) LIKE UPPER ('%"+carianUmum.trim()+"%') " +
								" OR UPPER (A.KOD_KEMENTERIAN) LIKE UPPER ('%"+carianUmum.trim()+"%') " +
								" OR UPPER (A.NAMA_KEMENTERIAN_LAMA) LIKE UPPER ('%"+carianUmum.trim()+"%') " +
								" OR UPPER (B.NAMA_NEGERI) LIKE UPPER ('%"+carianUmum.trim()+"%') )";
				}		
				
				sql += " ORDER BY A.KOD_KEMENTERIAN, A.ID_NEGERI ";
				
						myLogger.debug(sql);
						
						 rs = stmt.executeQuery(sql);
						 listKementerian = Collections.synchronizedList(new ArrayList());
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
							h.put("ID_KEMENTERIAN",checkIsNull(rs.getString("ID_KEMENTERIAN")));
							h.put("KOD_KEMENTERIAN",checkIsNull(rs.getString("KOD_KEMENTERIAN")));
							h.put("NAMA_KEMENTERIAN",checkIsNull(rs.getString("NAMA_KEMENTERIAN")));
							
							h.put("ALAMAT1",checkIsNull(rs.getString("ALAMAT1"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT2"))+"\n<br>"+
									checkIsNull(rs.getString("ALAMAT3"))+"\n<br>"+
									checkIsNull(rs.getString("POSKOD"))+"\n<br>"+
									checkIsNull(rs.getString("NAMA_NEGERI")));
							
							h.put("ID_NEGERI",checkIsNull(rs.getString("ID_NEGERI")));
							
							h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
							h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
							h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
							h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
						
							h.put("NAMA_KEMENTERIAN_LAMA",checkIsNull(rs.getString("NAMA_KEMENTERIAN_LAMA")));
							h.put("KOD_DDSA",checkIsNull(rs.getString("KOD_DDSA")));
							h.put("EMEL",checkIsNull(rs.getString("EMEL")));
							
							listKementerian.add(h);
						}
							
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (db != null) db.close();
					}
					
					return listKementerian;
					
				}
		
		public String checkIsNull(String txt) {
			if (txt == null) return "";
			else return txt;
		}
		
		@SuppressWarnings("unchecked")
		public Hashtable viewDataKementerian(HttpSession session, String ID_KEMENTERIAN) throws Exception {
			Db db = null;
			String sql = "";
			ResultSet rs = null;
			Statement stmt = null;
			try {
				db = new Db();
				stmt = db.getStatement();
				Hashtable h;
				h = new Hashtable();
				
				if(!ID_KEMENTERIAN.equals(""))
				{
					sql = queryKementerian(session,ID_KEMENTERIAN);
					myLogger.info(" viewDataKementerian :" + sql.toUpperCase());
					rs = stmt.executeQuery(sql);				
					
					while (rs.next()) {		
						h.put("ID_KEMENTERIAN",rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
						h.put("KOD_KEMENTERIAN",rs.getString("KOD_KEMENTERIAN") == null ? "" : rs.getString("KOD_KEMENTERIAN"));
						h.put("NAMA_KEMENTERIAN",rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
						h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
						h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
						h.put("NAMA_KEMENTERIAN_LAMA",rs.getString("NAMA_KEMENTERIAN_LAMA") == null ? "" : rs.getString("NAMA_KEMENTERIAN_LAMA"));
						h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
						h.put("JAWATAN",rs.getString("JAWATAN") == null ? "" : rs.getString("JAWATAN"));
						}
					}
					else
					{
						h.put("ID_KEMENTERIAN","");
						h.put("KOD_KEMENTERIAN","");
						h.put("NAMA_KEMENTERIAN","");
						h.put("ID_KEMASKINI","");
						h.put("TARIKH_KEMASKINI","");
						h.put("ID_MASUK","");
						h.put("TARIKH_MASUK","");
						h.put("ALAMAT1","");
						h.put("ALAMAT2","");
						h.put("ALAMAT3","");
						h.put("POSKOD","");
						h.put("NAMA_KEMENTERIAN_LAMA","");
						h.put("ID_NEGERI","");
						h.put("JAWATAN","");
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
		//Query Kementerian
			
			public String queryKementerian(HttpSession session,String ID_KEMENTERIAN) throws Exception {	
				
				String query = "";
				
						query += "SELECT K.ID_KEMENTERIAN, "+ 
								   "K.KOD_KEMENTERIAN, "+
							       "K.NAMA_KEMENTERIAN, "+      
							       "K.ID_KEMASKINI, "+      
							       "K.ID_MASUK, "+        
							       "TO_CHAR(K.TARIKH_KEMASKINI,'DD/MM/YYYY') AS TARIKH_KEMASKINI, "+  
							       "TO_CHAR(K.TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK, "+      
							       "K.ALAMAT1, K.ALAMAT2, K.ALAMAT3, K.POSKOD, K.NAMA_KEMENTERIAN_LAMA, K.ID_NEGERI, K.JAWATAN "+
							       "FROM "+
							       "TBLRUJKEMENTERIAN K, TBLRUJNEGERI N "+
							       "WHERE K.ID_NEGERI = N.ID_NEGERI";

						if(!ID_KEMENTERIAN.trim().equals(""))
						{
							query += " AND K.ID_KEMENTERIAN = '"+ID_KEMENTERIAN.trim()+"' ";
						}

				return query;
			}

				//Update Gred

			public String updateDataKementerian(HttpSession session,String ID_KEMENTERIAN) throws Exception {
				Connection conn = null;
				Db db = null;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				//String returnID_PEJABAT = "";
				String sql = "";

				long ID_KEMENTERIAN_INSERT= 0;
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					String KOD_KEMENTERIAN = getParam("KOD_KEMENTERIAN_"+ID_KEMENTERIAN);
					String NAMA_KEMENTERIAN = getParam("NAMA_KEMENTERIAN_"+ID_KEMENTERIAN);
					String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_KEMENTERIAN);
					String ID_MASUK = getParam("ID_MASUK_"+ID_KEMENTERIAN);
					String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_KEMENTERIAN);
					String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_KEMENTERIAN);
					String ALAMAT1 = getParam("ALAMAT1_"+ID_KEMENTERIAN);
					String ALAMAT2 = getParam("ALAMAT2_"+ID_KEMENTERIAN);
					String ALAMAT3 = getParam("ALAMAT3_"+ID_KEMENTERIAN);
					String POSKOD = getParam("POSKOD_"+ID_KEMENTERIAN);
					String NAMA_KEMENTERIAN_LAMA = getParam("NAMA_KEMENTERIAN_LAMA_"+ID_KEMENTERIAN);
					String ID_NEGERI = getParam("ID_NEGERI_"+ID_KEMENTERIAN);
					String JAWATAN = getParam("JAWATAN_"+ID_KEMENTERIAN);
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
		
					//UPDATE
					r.update("ID_KEMENTERIAN", ID_KEMENTERIAN);
				
					r.add("KOD_KEMENTERIAN", KOD_KEMENTERIAN);
					r.add("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					r.add("TARIKH_MASUK", r.unquote("sysdate"));	
					r.add("ALAMAT1", ALAMAT1);
					r.add("ALAMAT2", ALAMAT2);
					r.add("ALAMAT3", ALAMAT3);
					r.add("POSKOD", POSKOD);
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("JAWATAN", JAWATAN);
					r.add("NAMA_KEMENTERIAN_LAMA", NAMA_KEMENTERIAN_LAMA);
					
					
					sql = r.getSQLUpdate("TBLRUJKEMENTERIAN");		
					System.out.println("UPDATE TBLRUJKEMENTERIAN : "+sql);
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
				return ID_KEMENTERIAN;
			}
			
			//Insert Kementerian
			
			public String insertDataKementerian(HttpSession session,String ID_KEMENTERIAN) throws Exception {
				Connection conn = null;
				Db db = null;
				String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
				
				String sql = "";

				long ID_KEMENTERIAN_INSERT = 0;
			
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					
					String KOD_KEMENTERIAN = getParam("KOD_KEMENTERIAN_"+ID_KEMENTERIAN);
					String NAMA_KEMENTERIAN = getParam("NAMA_KEMENTERIAN_"+ID_KEMENTERIAN);
					String ID_KEMASKINI = getParam("ID_KEMASKINI_"+ID_KEMENTERIAN);
					String ID_MASUK = getParam("ID_MASUK_"+ID_KEMENTERIAN);
					String TARIKH_KEMASKINI = getParam("TARIKH_KEMASKINI_"+ID_KEMENTERIAN);
					String TARIKH_MASUK = getParam("TARIKH_MASUK_"+ID_KEMENTERIAN);
					String ALAMAT1 = getParam("ALAMAT1_"+ID_KEMENTERIAN);
					String ALAMAT2 = getParam("ALAMAT2_"+ID_KEMENTERIAN);
					String ALAMAT3 = getParam("ALAMAT3_"+ID_KEMENTERIAN);
					String POSKOD = getParam("POSKOD_"+ID_KEMENTERIAN);
					String NAMA_KEMENTERIAN_LAMA = getParam("NAMA_KEMENTERIAN_LAMA_"+ID_KEMENTERIAN);
					String ID_NEGERI = getParam("ID_NEGERI_"+ID_KEMENTERIAN);
					String JAWATAN = getParam("JAWATAN_"+ID_KEMENTERIAN);
					
					
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					//INSERT
					
					ID_KEMENTERIAN_INSERT = DB.getNextID(db,"TBLRUJKEMENTERIAN_SEQ");
					ID_KEMENTERIAN = ID_KEMENTERIAN_INSERT+"";
					
					r.add("ID_KEMENTERIAN", ID_KEMENTERIAN);
					r.add("KOD_KEMENTERIAN", KOD_KEMENTERIAN);
					r.add("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					r.add("ID_KEMASKINI", USER_ID_SYSTEM);
					r.add("ID_MASUK", USER_ID_SYSTEM);
					r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
					r.add("TARIKH_MASUK", r.unquote("sysdate"));	
					r.add("ALAMAT1", ALAMAT1);
					r.add("ALAMAT2", ALAMAT2);
					r.add("ALAMAT3", ALAMAT3);
					r.add("POSKOD", POSKOD);
					r.add("NAMA_KEMENTERIAN_LAMA", NAMA_KEMENTERIAN_LAMA);
					r.add("ID_NEGERI", ID_NEGERI);
					r.add("JAWATAN", JAWATAN);
					
					sql = r.getSQLInsert("TBLRUJKEMENTERIAN");		
					System.out.println("INSERT TBLRUJKEMENTERIAN : "+sql);
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
				return ID_KEMENTERIAN;
			}
			
			//delete kementerian
			public void deleteKementerian(HttpSession session,String ID_KEMENTERIAN) throws Exception {
				Connection conn = null;
				Db db = null;
				String sql = "";
				
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					
					if (!ID_KEMENTERIAN.equals("")){
						r.add("ID_KEMENTERIAN", ID_KEMENTERIAN);
						sql = r.getSQLDelete("TBLRUJKEMENTERIAN");
						AuditTrail.logActivity(this,session,"DEL","SEKSYEN ["+ID_KEMENTERIAN+"] Deleted");
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
			    	throw new Exception("Ralat Delete KEMENTERIAN :"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
			}
			
		//LIST TABLE
			@SuppressWarnings("unchecked")
			public List listTableRujukanV3(HttpSession session, String tableRujukan, String ID_KEMENTERIAN, String ID_AGENSI )throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listTableRujukanV3 = null;
				String sql = "";
				
				try {
					db = new Db();
					stmt = db.getStatement();
					
					if(tableRujukan.equals("TBLRUJKEMENTERIAN"))
					{
						sql = 	" SELECT ID_KEMENTERIAN AS ID, KOD_KEMENTERIAN AS KOD, UPPER(NAMA_KEMENTERIAN) AS KETERANGAN " +
								" FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN IS NOT NULL " +
								" ORDER BY KOD_KEMENTERIAN ";
					}
					else if(tableRujukan.equals("TBLRUJAGENSI"))
					{
						sql = " SELECT ID_AGENSI AS ID, KOD_AGENSI AS KOD, UPPER(NAMA_AGENSI) AS KETERANGAN " +
								" FROM TBLRUJAGENSI WHERE ID_AGENSI IS NOT NULL ";
						//sql += " AND ID_KEMENTERIAN = '"+ID_KEMENTERIAN+"' ";
						sql +=" ORDER BY KOD_AGENSI ";
					}
					else if(tableRujukan.equals("TBLRUJAGENSIBYKEMENTERIAN"))
					{
						sql = " SELECT ID_AGENSI AS ID, KOD_AGENSI AS KOD, UPPER(NAMA_AGENSI) AS KETERANGAN " +
								" FROM TBLRUJAGENSI WHERE ID_AGENSI IS NOT NULL ";
						sql += " AND ID_KEMENTERIAN = '"+ID_KEMENTERIAN+"' ";
						sql +=" ORDER BY KOD_AGENSI ";
					}
					else if(tableRujukan.equals("TBLRUJNEGERI"))
					{
						sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
						sql += " WHERE ID_NEGERI IS NOT NULL ORDER BY KETERANGAN";
					
					}
				//	myLogger.info(" SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
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
			
			public void setupPageListSmall(HttpSession session, String action, List list, String namaList, String command, String div) {
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
		
