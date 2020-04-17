package ekptg.view.ppk;

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
import ekptg.helpers.Paging2;

public class FrmPNB extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmPNB.class);
	String skrin_name = "app/ppk/PNB/index.jsp";
	
	
	@Override
	public String doTemplate2() throws Exception {
		
		List listBorang = null;
		List listBorangPenerima = null;
		List listNegeri = null;
		List listUnit = null;
		List listJenisBorang = null;
		Hashtable viewBorang = null;
		Hashtable viewBorangPenerima = null;
		defaultPut();
		
		HttpSession session = this.request.getSession();
		String command = getParam("command");
		myLogger.info("OT command : "+command);
		this.context.put("command",command);
		String action = getParam("action");
		this.context.put("action",action);
		String USER_ROLE = (String) session.getAttribute("myrole");
		this.context.put("USER_ROLE",USER_ROLE);
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		this.context.put("USER_UNIT",USER_UNIT);	
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);
		
		
		
		
		if(command.equals("showSenaraiBorang_Print"))
		{			
			listBorang = listBorang(session);
			this.context.put("listBorang",listBorang);
			this.context.put("listBorang_forPrint",listBorang);			
			skrin_name = "app/ppk/PNB/SenaraiBorang_Print.jsp";
		}
		else if(command.equals("showBorangPenerima") || command.equals("simpanBorangPenerima") || command.equals("batalBorangPenerima"))
		{
			String ID_BORANGPNB = getParam("ID_BORANGPNB");
			
			String HID_OPENCLOSE_ROWBORANG = getParam("HID_OPENCLOSE_ROWBORANG");
			this.context.put("ID_BORANGPNB",ID_BORANGPNB);
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss",getParam("rowCss"));
			this.context.put("FLAG_PNB_CARIAN",getParam("FLAG_PNB_CARIAN"));
			
			
			if((HID_OPENCLOSE_ROWBORANG.equals("CLOSE") && command.equals("showBorangPenerima")) || command.equals("simpanBorangPenerima") || command.equals("batalBorangPenerima") )
			{
				
				if(command.equals("simpanBorangPenerima"))
				{
					String[] CHECK_HANTAROB = request.getParameterValues("CHECK_HANTAROB"+ID_BORANGPNB);
					String[] CHECK_RETURN = request.getParameterValues("CHECK_RETURN"+ID_BORANGPNB);
					updateResetPenerimaan(session,ID_BORANGPNB);
					
					if(CHECK_HANTAROB!=null || CHECK_RETURN!=null)
					{
						Connection conn = null;
						Db db = null;
						try {
							db = new Db();
							conn = db.getConnection();
							conn.setAutoCommit(false);
						
							
							if(CHECK_HANTAROB!=null)
							{
								for (String ID_BORANGPNBOB : CHECK_HANTAROB) {
									String TARIKH_HANTAROB = getParam("TARIKH_HANTAROB"+ID_BORANGPNBOB);
									String NOSURAT_HANTAROB = getParam("NOSURAT_HANTAROB"+ID_BORANGPNBOB);
									myLogger.info("ID_BORANGPNBOB : "+ID_BORANGPNBOB+" --- TARIKH_HANTAROB : "+TARIKH_HANTAROB+" NOSURAT_HANTAROB : "+NOSURAT_HANTAROB);						
									updateFlagHantarOB(session,ID_BORANGPNBOB, "Y",TARIKH_HANTAROB,NOSURAT_HANTAROB,db);							
								}
							}
							
							
							if(CHECK_RETURN!=null)
							{						
							for (String ID_BORANGPNBOB : CHECK_RETURN) {
								String TARIKH_RETURN = getParam("TARIKH_RETURN"+ID_BORANGPNBOB);
								String CATATAN_RETURN = getParam("CATATAN_RETURN"+ID_BORANGPNBOB);
								myLogger.info("ID_BORANGPNBOB : "+ID_BORANGPNBOB+" --- TARIKH_RETURN : "+TARIKH_RETURN+" CATATAN_RETURN : "+CATATAN_RETURN);							
								updateFlagReturn(session,ID_BORANGPNBOB, "Y",TARIKH_RETURN,CATATAN_RETURN.toUpperCase(),db);							
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
					    	throw new Exception("Ralat :"+se.getMessage());
						}
						catch (Exception re) {
							throw re;
						}finally {
							if (db != null)
								db.close();
						}
					}
				}
				
				listBorangPenerima = listBorangPenerima(session,ID_BORANGPNB);
				this.context.put("listBorangPenerima",listBorangPenerima);				
				HID_OPENCLOSE_ROWBORANG = "OPEN";
				this.context.put("HID_OPENCLOSE_ROWBORANG",HID_OPENCLOSE_ROWBORANG);
				
				
				skrin_name = "app/ppk/PNB/SenaraiBorangPenerima.jsp";
			}
			else
			{
				
				HID_OPENCLOSE_ROWBORANG = "CLOSE";
				this.context.put("HID_OPENCLOSE_ROWBORANG",HID_OPENCLOSE_ROWBORANG);	
				skrin_name = "app/ppk/PNB/SenaraiBorangPenerima_blank.jsp";
			}
			
		}		
		else if(command.equals("showViewBorang") || command.equals("downloadBorang"))
		{	
			this.context.put("BIL", getParam("BIL"));
			this.context.put("rowCss",getParam("rowCss"));
			this.context.put("HID_OPENCLOSE_ROWBORANG",getParam("HID_OPENCLOSE_ROWBORANG"));
			
			String ID_BORANGPNB = getParam("ID_BORANGPNB");
			if(command.equals("downloadBorang") && USER_ROLE.equals("user_pnb"))
			{
				updateFlagDownload(session,ID_BORANGPNB);
			}
			
			viewBorang = viewBorang(session,ID_BORANGPNB);
			this.context.put("viewBorang",viewBorang);		
			skrin_name = "app/ppk/PNB/rowBorang.jsp";
		}
		else if(command.equals("showSenaraiBorang"))
		{	
			this.context.put("FLAG_PNB_CARIAN", getParam("FLAG_PNB_CARIAN"));
			this.context.put("OPENCLOSE_CARIAN",getParam("OPENCLOSE_CARIAN"));
			listBorang = listBorang(session);
			setupPageList(session, action, listBorang, "listBorang",command,"div_SenaraiBorang");
			skrin_name = "app/ppk/PNB/SenaraiBorang.jsp";
		}
		else if(command.equals("showUnit"))
		{
			String CARIAN_NEGERI = getParam("CARIAN_NEGERI"); 
			listUnit = listUnit(session,CARIAN_NEGERI);
			this.context.put("listUnit",listUnit);
			skrin_name = "app/ppk/PNB/skrinUnit.jsp";
		}
		else if(command.equals("showCARIAN")) 
		{
			this.context.put("FLAG_PNB_CARIAN", getParam("FLAG_PNB_CARIAN"));
			this.context.put("OPENCLOSE_CARIAN",getParam("OPENCLOSE_CARIAN"));
			listNegeri = listNegeri(session);
			this.context.put("listNegeri",listNegeri);
			listUnit = listUnit(session,"");
			this.context.put("listUnit",listUnit);
			listJenisBorang = listJenisBorang(session);
			this.context.put("listJenisBorang",listJenisBorang);			
			skrin_name = "app/ppk/PNB/SkrinCarian.jsp";
		}
		
		myLogger.info("VM :"+skrin_name);
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
		this.context.put("listBorang", "");		
		this.context.put("OPENCLOSE_CARIAN","");
		this.context.put("FLAG_PNB_CARIAN","");
		this.context.put("listNegeri","");
		this.context.put("listUnit","");
		this.context.put("listJenisBorang","");
		this.context.put("listBorang_forPrint","");
		this.context.put("viewBorang","");
		this.context.put("listBorangPenerima","");
		this.context.put("viewBorangPenerima","");		
		this.context.put("ID_BORANGPNB","");
		this.context.put("BIL","");
		this.context.put("rowCss","");
		
		this.context.put("CARIAN_NO_PNB","");
		this.context.put("CARIAN_UNIT","");				
		this.context.put("FLAG_PNB_CARIAN","");
		this.context.put("CARIAN_NEGERI","");
		this.context.put("CARIAN_NO_FAIL","");
		this.context.put("CARIAN_NAMA_BORANG","");
		this.context.put("CARIAN_JENIS_BORANG","");
		this.context.put("CARIAN_TARIKH_MULA","");
		this.context.put("CARIAN_TARIKH_AKHIR","");
		this.context.put("CARIAN_STATUS","");
		this.context.put("CARIAN_ALAMAT_PENERIMA","");
		this.context.put("CARIAN_NAMA_PENERIMA","");
		this.context.put("HID_OPENCLOSE_ROWBORANG","");
		
	}
	
	public String QuerySenaraiBorangPenerima(String ID_BORANGPNB,String ID_BORANGPNBOB, String filterCarian)
	{
		String query = " SELECT T.ID_BORANGPNBOB, T.ID_BORANGPNB, T.ID_PK, T.TABLENAME, T.FLAG_HANTAROB, T.ID_HANTAROB, "+
				" TO_CHAR(T.TARIKH_HANTAROB,'DD/MM/YYYY') AS TARIKH_HANTAROB,T.NOSURAT_HANTAROB, T.FLAG_RETURN, T.ID_RETURN,  "+
				" TO_CHAR(T.TARIKH_RETURN,'DD/MM/YYYY') AS TARIKH_RETURN,T.CATATAN_RETURN, T.NAMA, T.TARAF, T.ALAMAT_1, T.ALAMAT_2,  "+
				" T.ALAMAT_3, T.POSKOD, T.NEGERI, T.BANDAR "+
				" FROM TBLPPKBORANGPNBOB T  WHERE T.ID_BORANGPNBOB IS NOT NULL ";
		
				if(!ID_BORANGPNB.equals(""))
				{
					query += " AND T.ID_BORANGPNB = '"+ID_BORANGPNB+"' ";
				}
				
				if(!ID_BORANGPNBOB.equals(""))
				{
					query += " AND T.ID_BORANGPNBOB = '"+ID_BORANGPNBOB+"' ";
				}
				
				if(!filterCarian.equals(""))
				{
					query += filterCarian;
				}
				
				query += " ORDER BY T.NAMA ";
				
				
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public List listBorangPenerima(HttpSession session,String ID_BORANGPNB)throws Exception {
		
		String FLAG_PNB_CARIAN = getParam("FLAG_PNB_CARIAN");
		myLogger.info("-------- FLAG_PNB_CARIAN : "+FLAG_PNB_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");		
		myLogger.info("-------- USER_ID_SYSTEM : "+USER_ID_SYSTEM+" USER_ROLE : "+USER_ROLE+" USER_NEGERI : "+USER_NEGERI);
		
		String CARIAN_NAMA_PENERIMA = getParam("CARIAN_NAMA_PENERIMA").toUpperCase();
		String CARIAN_ALAMAT_PENERIMA = getParam("CARIAN_ALAMAT_PENERIMA").toUpperCase();
		String CARIAN_STATUS = getParam("CARIAN_STATUS").toUpperCase();
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listBorangPenerima = null;
		String sql = "";
		String filterCarian = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
							
			
					
			if(FLAG_PNB_CARIAN.equals("Y"))
			{				
				if(!CARIAN_NAMA_PENERIMA.equals(""))
				{
					filterCarian += " AND UPPER(T.NAMA) LIKE '%"+CARIAN_NAMA_PENERIMA+"%'";
				}
				
				if(!CARIAN_STATUS.equals("") && CARIAN_STATUS.equals("5"))
				{
					filterCarian += " AND T.FLAG_RETURN = 'Y'  ";
				}
				
				if(!CARIAN_ALAMAT_PENERIMA.equals(""))
				{
					filterCarian += " AND (UPPER(T.ALAMAT_1) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%'" +
							" OR UPPER(T.ALAMAT_2) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(T.ALAMAT_3) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(T.POSKOD) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(T.BANDAR) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(T.NEGERI) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +							
							"  ) ";
				}
				
				
				sql = QuerySenaraiBorangPenerima(ID_BORANGPNB,"",filterCarian);
				
				this.context.put("CARIAN_ALAMAT_PENERIMA",CARIAN_ALAMAT_PENERIMA.toUpperCase());
				this.context.put("CARIAN_NAMA_PENERIMA",CARIAN_NAMA_PENERIMA.toUpperCase());
				
			}
			else
			{	
				
				
				sql = QuerySenaraiBorangPenerima(ID_BORANGPNB,"","");
				sql += "  "; 
				this.context.put("CARIAN_ALAMAT_PENERIMA","");
				this.context.put("CARIAN_NAMA_PENERIMA","");
				
				 
			}			
					
			myLogger.info(" SQL listBorang Penerima :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listBorangPenerima = Collections.synchronizedList(new ArrayList());
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
				
				h.put("rowCssSUB",rowCss);
				h.put("BILSUB",bil);
				h.put("ID_BORANGPNBOB",rs.getString("ID_BORANGPNBOB") == null ? "" : rs.getString("ID_BORANGPNBOB"));
				h.put("ID_BORANGPNB",rs.getString("ID_BORANGPNB") == null ? "" : rs.getString("ID_BORANGPNB"));
				h.put("ID_PK",rs.getString("ID_PK") == null ? "" : rs.getString("ID_PK"));
				h.put("TABLENAME",rs.getString("TABLENAME") == null ? "" : rs.getString("TABLENAME"));
				h.put("FLAG_HANTAROB",rs.getString("FLAG_HANTAROB") == null ? "" : rs.getString("FLAG_HANTAROB"));
				h.put("ID_HANTAROB",rs.getString("ID_HANTAROB") == null ? "" : rs.getString("ID_HANTAROB"));
				h.put("TARIKH_HANTAROB",rs.getString("TARIKH_HANTAROB") == null ? "" : rs.getString("TARIKH_HANTAROB"));
				h.put("NOSURAT_HANTAROB",rs.getString("NOSURAT_HANTAROB") == null ? "" : rs.getString("NOSURAT_HANTAROB"));
				h.put("FLAG_RETURN",rs.getString("FLAG_RETURN") == null ? "" : rs.getString("FLAG_RETURN"));
				h.put("ID_RETURN",rs.getString("ID_RETURN") == null ? "" : rs.getString("ID_RETURN"));
				h.put("TARIKH_RETURN",rs.getString("TARIKH_RETURN") == null ? "" : rs.getString("TARIKH_RETURN"));
				h.put("CATATAN_RETURN",rs.getString("CATATAN_RETURN") == null ? "" : rs.getString("CATATAN_RETURN"));
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("TARAF",rs.getString("TARAF") == null ? "" : rs.getString("TARAF"));
				h.put("ALAMAT_1",rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_2",rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("ALAMAT_3",rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				
				listBorangPenerima.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listBorangPenerima;

	}
	
	public Hashtable viewBorangPenerima(HttpSession session, String ID_BORANGPNBOB) throws Exception {
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
			
				sql = QuerySenaraiBorangPenerima("",ID_BORANGPNBOB, "");
				myLogger.info(" viewBorangPenerima :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					h.put("ID_BORANGPNBOB",rs.getString("ID_BORANGPNBOB") == null ? "" : rs.getString("ID_BORANGPNBOB"));
					h.put("ID_BORANGPNB",rs.getString("ID_BORANGPNB") == null ? "" : rs.getString("ID_BORANGPNB"));
					h.put("ID_PK",rs.getString("ID_PK") == null ? "" : rs.getString("ID_PK"));
					h.put("TABLENAME",rs.getString("TABLENAME") == null ? "" : rs.getString("TABLENAME"));
					h.put("FLAG_HANTAROB",rs.getString("FLAG_HANTAROB") == null ? "" : rs.getString("FLAG_HANTAROB"));
					h.put("ID_HANTAROB",rs.getString("ID_HANTAROB") == null ? "" : rs.getString("ID_HANTAROB"));
					h.put("TARIKH_HANTAROB",rs.getString("TARIKH_HANTAROB") == null ? "" : rs.getString("TARIKH_HANTAROB"));
					h.put("NOSURAT_HANTAROB",rs.getString("NOSURAT_HANTAROB") == null ? "" : rs.getString("NOSURAT_HANTAROB"));
					h.put("FLAG_RETURN",rs.getString("FLAG_RETURN") == null ? "" : rs.getString("FLAG_RETURN"));
					h.put("ID_RETURN",rs.getString("ID_RETURN") == null ? "" : rs.getString("ID_RETURN"));
					h.put("TARIKH_RETURN",rs.getString("TARIKH_RETURN") == null ? "" : rs.getString("TARIKH_RETURN"));
					h.put("CATATAN_RETURN",rs.getString("CATATAN_RETURN") == null ? "" : rs.getString("CATATAN_RETURN"));
					h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
					h.put("TARAF",rs.getString("TARAF") == null ? "" : rs.getString("TARAF"));
					h.put("ALAMAT_1",rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
					h.put("ALAMAT_2",rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
					h.put("ALAMAT_3",rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
					h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));			
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
	
	
	public String QuerySenaraiBorang(String ID_BORANGPNB, String filterCarian,HttpSession session)
	{
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
		
		String query = " SELECT T.COUNT_PAGES,T.JENIS_BORANG,T.ID_BORANGPNB, T.ID_FAIL, T.ID_PERBICARAAN, T.ID_NEGERIUNIT, T.ID_UNIT, T.NAMA_BORANG, "+
				" T.FLAG_HANTARPNB, T.ID_HANTARPNB,TO_CHAR(T.TARIKH_HANTARPNB,'DD/MM/YYYY') AS TARIKH_HANTARPNB,  "+
				" T.FLAG_DOWNLOAD, T.ID_DOWNLOAD, TO_CHAR(T.TARIKH_DOWNLOAD,'DD/MM/YYYY') AS TARIKH_DOWNLOAD,   "+
				" T.NO_PNB, F.NO_FAIL, UPPER(N.NAMA_NEGERI) AS NEGERIUNIT, UPPER(P.NAMA_PEJABAT) AS NAMAUNIT, UPPER(U.USER_NAME) AS PEGAWAI_HANTAR, "+
				" COUNT(POB.ID_BORANGPNBOB) AS JUMLAH_PENERIMA, COUNT(CASE WHEN POB.FLAG_HANTAROB = 'Y' THEN POB.ID_BORANGPNBOB END) AS JUMLAH_DIHANTAR, "+
				" COUNT(CASE WHEN POB.FLAG_RETURN = 'Y' THEN POB.ID_BORANGPNBOB END) AS JUMLAH_RETURN, " +
				" (CASE WHEN COUNT (POB.ID_BORANGPNBOB) = "+
				" COUNT (CASE "+
				" WHEN POB.FLAG_HANTAROB = 'Y' "+
				" THEN POB.ID_BORANGPNBOB "+
				" END "+
				" ) "+
				" AND COUNT (POB.ID_BORANGPNBOB) > 0 "+
				" THEN '4'  "+
				" ELSE  "+
				" CASE WHEN NVL(T.FLAG_HANTARPNB,' ') = 'Y' AND NVL(T.FLAG_DOWNLOAD,' ') != 'Y' THEN '1'  "+
				" WHEN NVL(T.FLAG_DOWNLOAD,' ') = 'Y' AND COUNT(CASE WHEN POB.FLAG_HANTAROB = 'Y' THEN POB.ID_BORANGPNBOB END) = 0 THEN '2'  "+
				" ELSE '3' END  "+
				" END "+
				" ) AS STATUS_BORANG "+
				" FROM TBLPPKBORANGPNB T,TBLPFDFAIL F, TBLRUJNEGERI N, TBLRUJPEJABATJKPTG P, USERS U, TBLPPKBORANGPNBOB POB "+
				" WHERE T.ID_FAIL = F.ID_FAIL AND T.ID_NEGERIUNIT = N.ID_NEGERI AND T.ID_UNIT = P.ID_PEJABATJKPTG AND T.ID_HANTARPNB = U.USER_ID "+
				" AND T.ID_BORANGPNB = POB.ID_BORANGPNB ";		
				
				if(!filterCarian.equals(""))
				{
					query += filterCarian;
				}				
				
				
				if(USER_ROLE.equals("adminppk"))
				{
					if(!USER_NEGERI.equals("16"))
					{
						query += " AND T.ID_NEGERIUNIT = '"+USER_NEGERI+"' ";
					}
					
				}
				else if(USER_ROLE.equals("user_ppk"))
				{
					query += " AND T.ID_UNIT = '"+USER_UNIT+"' ";
				}
				
				
				if(!ID_BORANGPNB.equals(""))
				{
					query += " AND T.ID_BORANGPNB = '"+ID_BORANGPNB+"' ";
				}
				
				query += " GROUP BY T.COUNT_PAGES,T.JENIS_BORANG,T.ID_BORANGPNB, T.ID_FAIL, T.ID_PERBICARAAN,T.ID_NEGERIUNIT, T.ID_UNIT, T.NAMA_BORANG, T.FLAG_HANTARPNB, T.ID_HANTARPNB,  "+
				" T.TARIKH_HANTARPNB, T.FLAG_DOWNLOAD, T.ID_DOWNLOAD,T.TARIKH_DOWNLOAD, T.NO_PNB,F.NO_FAIL,N.NAMA_NEGERI,P.NAMA_PEJABAT,U.USER_NAME ";
				
				if(filterCarian.equals("") && ID_BORANGPNB.equals("") && USER_ROLE.equals("user_pnb"))
				{
					//display senarai yg belum selesai sahaja utk default list
					query += " HAVING NVL((CASE "+
							" WHEN COUNT (POB.ID_BORANGPNBOB) = "+
							" COUNT (CASE "+
							" WHEN POB.FLAG_HANTAROB = 'Y' "+
							" THEN POB.ID_BORANGPNBOB "+
							" END "+
							" ) "+
							" AND COUNT (POB.ID_BORANGPNBOB) > 0 "+
							" THEN '4' "+
							" ELSE  "+
							" CASE WHEN NVL(T.FLAG_HANTARPNB,' ') = 'Y' AND NVL(T.FLAG_DOWNLOAD,' ') != 'Y' THEN '1'  "+
							" WHEN NVL(T.FLAG_DOWNLOAD,' ') = 'Y'  AND COUNT(CASE WHEN POB.FLAG_HANTAROB = 'Y' THEN POB.ID_BORANGPNBOB END) = 0 THEN '2' "+
							" ELSE '3' END  "+
							" END "+
							" ),0) != 4  ";
				}
				
				query += " ORDER BY (CASE  WHEN COUNT (POB.ID_BORANGPNBOB) = "+
						" COUNT (CASE WHEN POB.FLAG_HANTAROB = 'Y' THEN POB.ID_BORANGPNBOB END) "+
						" AND COUNT (POB.ID_BORANGPNBOB) > 0 THEN '4' "+
						" ELSE  "+
						" CASE WHEN NVL(T.FLAG_HANTARPNB,' ') = 'Y' AND NVL(T.FLAG_DOWNLOAD,' ') != 'Y' THEN '1'  "+
						" WHEN NVL(T.FLAG_DOWNLOAD,' ') = 'Y'  AND COUNT(CASE WHEN POB.FLAG_HANTAROB = 'Y' THEN POB.ID_BORANGPNBOB END) = 0 THEN '2'  "+
						" ELSE '3' END "+
						" END "+
						" ) ASC, "+
						" T.TARIKH_HANTARPNB ASC ";
				
				//STATUS BORANG
				//1-HANTAR KE PNB
				//2-DITERIMA PNB
				//3-DALAM PROSES
				//4-SELESAI
				
				
		return query;
	}
	
	
	@SuppressWarnings("unchecked")
	public List listBorang(HttpSession session)throws Exception {
		
		String FLAG_PNB_CARIAN = getParam("FLAG_PNB_CARIAN");
		myLogger.info("-------- FLAG_PNB_CARIAN : "+FLAG_PNB_CARIAN);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");		
		myLogger.info("-------- USER_ID_SYSTEM : "+USER_ID_SYSTEM+" USER_ROLE : "+USER_ROLE+" USER_NEGERI : "+USER_NEGERI);
		
		String CARIAN_NO_PNB = getParam("CARIAN_NO_PNB").toUpperCase();
		String CARIAN_UNIT = getParam("CARIAN_UNIT");
		String CARIAN_NEGERI = getParam("CARIAN_NEGERI");	
		String CARIAN_NO_FAIL = getParam("CARIAN_NO_FAIL").toUpperCase();	
		String CARIAN_NAMA_BORANG = getParam("CARIAN_NAMA_BORANG").toUpperCase();
		String CARIAN_JENIS_BORANG = getParam("CARIAN_JENIS_BORANG");
		String CARIAN_TARIKH_MULA = getParam("CARIAN_TARIKH_MULA");
		String CARIAN_TARIKH_AKHIR = getParam("CARIAN_TARIKH_AKHIR");
		String CARIAN_STATUS = getParam("CARIAN_STATUS");
		String CARIAN_NAMA_PENERIMA = getParam("CARIAN_NAMA_PENERIMA").toUpperCase();
		String CARIAN_ALAMAT_PENERIMA = getParam("CARIAN_ALAMAT_PENERIMA").toUpperCase();
		
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listBorang = null;
		String sql = "";
		String filterCarian = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
							
			
					
			if(FLAG_PNB_CARIAN.equals("Y"))
			{					
				if(!CARIAN_NO_PNB.equals(""))
				{
					filterCarian += " AND UPPER(T.NO_PNB) LIKE '%"+CARIAN_NO_PNB+"%'";
				}
				
				if(!CARIAN_NO_FAIL.equals(""))
				{
					filterCarian += " AND UPPER(F.NO_FAIL) LIKE '%"+CARIAN_NO_FAIL+"%'";
				}
				
				if(!CARIAN_JENIS_BORANG.equals(""))
				{
					filterCarian += " AND T.JENIS_BORANG = '"+CARIAN_JENIS_BORANG+"'";
				}
				
				if(!CARIAN_NAMA_BORANG.equals(""))
				{
					filterCarian += " AND UPPER(T.NAMA_BORANG) LIKE '%"+CARIAN_NAMA_BORANG.toUpperCase()+"%'";
				}
				
				if(!CARIAN_NEGERI.equals(""))
				{
					filterCarian += " AND T.ID_NEGERIUNIT = '"+CARIAN_NEGERI+"'";
				}
				
				if(!CARIAN_UNIT.equals(""))
				{
					filterCarian += " AND T.ID_UNIT = '"+CARIAN_UNIT+"'";
				}
				
				if(!CARIAN_STATUS.equals("") && CARIAN_STATUS.equals("5"))
				{
					filterCarian += " AND POB.FLAG_RETURN = 'Y'  ";
				}
				
				if(!CARIAN_TARIKH_MULA.equals(""))
				{
					filterCarian += " AND TO_DATE (TO_CHAR (T.TARIKH_HANTARPNB, 'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE ('"+CARIAN_TARIKH_MULA+"', 'DD/MM/YYYY')  ";
				}
				
				if(!CARIAN_TARIKH_AKHIR.equals(""))
				{
					filterCarian += " AND TO_DATE (TO_CHAR (T.TARIKH_HANTARPNB, 'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE ('"+CARIAN_TARIKH_AKHIR+"', 'DD/MM/YYYY')  ";
				}
				
				
				if(!CARIAN_NAMA_PENERIMA.equals(""))
				{
					filterCarian += " AND UPPER(POB.NAMA) LIKE '%"+CARIAN_NAMA_PENERIMA+"%'";
				}
				
				if(!CARIAN_ALAMAT_PENERIMA.equals(""))
				{
					filterCarian += " AND (UPPER(POB.ALAMAT_1) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%'" +
							" OR UPPER(POB.ALAMAT_2) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(POB.ALAMAT_3) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(POB.POSKOD) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(POB.BANDAR) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +
							" OR UPPER(POB.NEGERI) LIKE '%"+CARIAN_ALAMAT_PENERIMA+"%' " +							
							"  ) ";
				}
				
				
				
				if(!CARIAN_STATUS.equals("") && !CARIAN_STATUS.equals("5"))
				{
					filterCarian += " HAVING NVL((CASE "+
							" WHEN COUNT (POB.ID_BORANGPNBOB) = "+
							" COUNT (CASE "+
							" WHEN POB.FLAG_HANTAROB = 'Y' "+
							" THEN POB.ID_BORANGPNBOB "+
							" END "+
							" ) "+
							" AND COUNT (POB.ID_BORANGPNBOB) > 0 "+
							" THEN '4' "+
							" ELSE  "+
							" CASE WHEN NVL(T.FLAG_HANTARPNB,' ') = 'Y' AND NVL(T.FLAG_DOWNLOAD,' ') != 'Y' THEN '1'  "+
							" WHEN NVL(T.FLAG_DOWNLOAD,' ') = 'Y'  AND COUNT(CASE WHEN POB.FLAG_HANTAROB = 'Y' THEN POB.ID_BORANGPNBOB END) = 0 THEN '2' "+
							" ELSE '3' END  "+
							" END "+
							" ),0) = '"+CARIAN_STATUS+"'  ";
				}
				
				
				sql = QuerySenaraiBorang("",filterCarian,session);
				
				this.context.put("CARIAN_UNIT",CARIAN_UNIT.toUpperCase());	
				this.context.put("CARIAN_NO_PNB",CARIAN_NO_PNB.toUpperCase());	
				this.context.put("FLAG_PNB_CARIAN",FLAG_PNB_CARIAN);
				this.context.put("CARIAN_NEGERI",CARIAN_NEGERI.toUpperCase());
				this.context.put("CARIAN_NO_FAIL",CARIAN_NO_FAIL.toUpperCase());
				this.context.put("CARIAN_NAMA_BORANG",CARIAN_NAMA_BORANG.toUpperCase());
				this.context.put("CARIAN_JENIS_BORANG",CARIAN_JENIS_BORANG.toUpperCase());
				this.context.put("CARIAN_TARIKH_MULA",CARIAN_TARIKH_MULA);
				this.context.put("CARIAN_TARIKH_AKHIR",CARIAN_TARIKH_AKHIR);
				this.context.put("CARIAN_STATUS",CARIAN_STATUS);
				this.context.put("CARIAN_ALAMAT_PENERIMA",CARIAN_ALAMAT_PENERIMA.toUpperCase());
				this.context.put("CARIAN_NAMA_PENERIMA",CARIAN_NAMA_PENERIMA.toUpperCase());
				
			}
			else
			{	
				
				
				sql = QuerySenaraiBorang("","",session);
				sql += "  "; 
				this.context.put("CARIAN_NO_PNB","");
				this.context.put("CARIAN_UNIT","");				
				this.context.put("FLAG_PNB_CARIAN","");
				this.context.put("CARIAN_NEGERI","");
				this.context.put("CARIAN_NO_FAIL","");
				this.context.put("CARIAN_NAMA_BORANG","");
				this.context.put("CARIAN_JENIS_BORANG","");
				this.context.put("CARIAN_TARIKH_MULA","");
				this.context.put("CARIAN_TARIKH_AKHIR","");
				this.context.put("CARIAN_STATUS","");
				this.context.put("CARIAN_ALAMAT_PENERIMA","");
				this.context.put("CARIAN_NAMA_PENERIMA","");
				
				 
			}			
					
			myLogger.info(" OT : SQL listBorang :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listBorang = Collections.synchronizedList(new ArrayList());
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
				h.put("JENIS_BORANG",rs.getString("JENIS_BORANG") == null ? "" : rs.getString("JENIS_BORANG"));
				h.put("ID_BORANGPNB",rs.getString("ID_BORANGPNB") == null ? "" : rs.getString("ID_BORANGPNB"));
				h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("ID_PERBICARAAN",rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
				h.put("ID_NEGERIUNIT",rs.getString("ID_NEGERIUNIT") == null ? "" : rs.getString("ID_NEGERIUNIT"));
				h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
				h.put("NAMA_BORANG",rs.getString("NAMA_BORANG") == null ? "" : rs.getString("NAMA_BORANG"));
				h.put("FLAG_HANTARPNB",rs.getString("FLAG_HANTARPNB") == null ? "" : rs.getString("FLAG_HANTARPNB"));
				h.put("ID_HANTARPNB",rs.getString("ID_HANTARPNB") == null ? "" : rs.getString("ID_HANTARPNB"));
				h.put("TARIKH_HANTARPNB",rs.getString("TARIKH_HANTARPNB") == null ? "" : rs.getString("TARIKH_HANTARPNB"));
				h.put("FLAG_DOWNLOAD",rs.getString("FLAG_DOWNLOAD") == null ? "" : rs.getString("FLAG_DOWNLOAD"));
				h.put("ID_DOWNLOAD",rs.getString("ID_DOWNLOAD") == null ? "" : rs.getString("ID_DOWNLOAD"));
				h.put("TARIKH_DOWNLOAD",rs.getString("TARIKH_DOWNLOAD") == null ? "" : rs.getString("TARIKH_DOWNLOAD"));
				h.put("NO_PNB",rs.getString("NO_PNB") == null ? "" : rs.getString("NO_PNB"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("NEGERIUNIT",rs.getString("NEGERIUNIT") == null ? "" : rs.getString("NEGERIUNIT"));
				h.put("NAMAUNIT",rs.getString("NAMAUNIT") == null ? "" : rs.getString("NAMAUNIT"));
				h.put("PEGAWAI_HANTAR",rs.getString("PEGAWAI_HANTAR") == null ? "" : rs.getString("PEGAWAI_HANTAR"));
				h.put("JUMLAH_PENERIMA",rs.getString("JUMLAH_PENERIMA") == null ? 0 : rs.getInt("JUMLAH_PENERIMA"));
				h.put("JUMLAH_DIHANTAR",rs.getString("JUMLAH_DIHANTAR") == null ? 0 : rs.getInt("JUMLAH_DIHANTAR"));
				h.put("JUMLAH_RETURN",rs.getString("JUMLAH_RETURN") == null ? 0 : rs.getInt("JUMLAH_RETURN"));
				h.put("STATUS_BORANG",rs.getString("STATUS_BORANG") == null ? 0 : rs.getInt("STATUS_BORANG"));
				h.put("COUNT_PAGES",rs.getString("COUNT_PAGES") == null ? 0 : rs.getInt("COUNT_PAGES"));
				
				listBorang.add(h);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listBorang;

	}
	
	public Hashtable viewBorang(HttpSession session, String ID_BORANGPNB) throws Exception {
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
				myLogger.info(" QuerySenaraiBorang :" + sql.toUpperCase());
				sql = QuerySenaraiBorang(ID_BORANGPNB, "",session);
				myLogger.info(" OT : viewBorang :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {				

					h.put("COUNT_PAGES",rs.getString("COUNT_PAGES") == null ? 0 : rs.getInt("COUNT_PAGES"));
					h.put("JENIS_BORANG",rs.getString("JENIS_BORANG") == null ? "" : rs.getString("JENIS_BORANG"));
					h.put("ID_BORANGPNB",rs.getString("ID_BORANGPNB") == null ? "" : rs.getString("ID_BORANGPNB"));
					h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
					h.put("ID_PERBICARAAN",rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
					h.put("ID_NEGERIUNIT",rs.getString("ID_NEGERIUNIT") == null ? "" : rs.getString("ID_NEGERIUNIT"));
					h.put("ID_UNIT",rs.getString("ID_UNIT") == null ? "" : rs.getString("ID_UNIT"));
					h.put("NAMA_BORANG",rs.getString("NAMA_BORANG") == null ? "" : rs.getString("NAMA_BORANG"));
					h.put("FLAG_HANTARPNB",rs.getString("FLAG_HANTARPNB") == null ? "" : rs.getString("FLAG_HANTARPNB"));
					h.put("ID_HANTARPNB",rs.getString("ID_HANTARPNB") == null ? "" : rs.getString("ID_HANTARPNB"));
					h.put("TARIKH_HANTARPNB",rs.getString("TARIKH_HANTARPNB") == null ? "" : rs.getString("TARIKH_HANTARPNB"));
					h.put("FLAG_DOWNLOAD",rs.getString("FLAG_DOWNLOAD") == null ? "" : rs.getString("FLAG_DOWNLOAD"));
					h.put("ID_DOWNLOAD",rs.getString("ID_DOWNLOAD") == null ? "" : rs.getString("ID_DOWNLOAD"));
					h.put("TARIKH_DOWNLOAD",rs.getString("TARIKH_DOWNLOAD") == null ? "" : rs.getString("TARIKH_DOWNLOAD"));
					h.put("NO_PNB",rs.getString("NO_PNB") == null ? "" : rs.getString("NO_PNB"));
					h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
					h.put("NEGERIUNIT",rs.getString("NEGERIUNIT") == null ? "" : rs.getString("NEGERIUNIT"));
					h.put("NAMAUNIT",rs.getString("NAMAUNIT") == null ? "" : rs.getString("NAMAUNIT"));
					h.put("PEGAWAI_HANTAR",rs.getString("PEGAWAI_HANTAR") == null ? "" : rs.getString("PEGAWAI_HANTAR"));
					h.put("JUMLAH_PENERIMA",rs.getString("JUMLAH_PENERIMA") == null ? 0 : rs.getInt("JUMLAH_PENERIMA"));
					h.put("JUMLAH_DIHANTAR",rs.getString("JUMLAH_DIHANTAR") == null ? 0 : rs.getInt("JUMLAH_DIHANTAR"));
					h.put("JUMLAH_RETURN",rs.getString("JUMLAH_RETURN") == null ? 0 : rs.getInt("JUMLAH_RETURN"));
					h.put("STATUS_BORANG",rs.getString("STATUS_BORANG") == null ? 0 : rs.getInt("STATUS_BORANG"));
					
				
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
		String USER_UNIT = (String) session.getAttribute("_ekptg_user_unit");
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
			
						
						if(USER_ROLE.equals("adminppk"))
						{
							if(!USER_NEGERI.equals("16"))
							{
								sql += " AND ID_NEGERI = '"+USER_NEGERI+"' ";
							}
							
						}
						else if(USER_ROLE.equals("user_ppk"))
						{
							sql += " AND ID_NEGERI = '"+USER_NEGERI+"' ";
						}
			
			
			/*
			if(USER_ROLE.equals("user_ppk") || (USER_ROLE.equals("adminppk") && !USER_NEGERI.equals("16")))
			{
				//sql += " AND ID_NEGERI = '"+USER_NEGERI+"' ";
			}
			*/
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
	public List listJenisBorang(HttpSession session)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listJenisBorang = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_ROLE = (String) session.getAttribute("myrole");
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		try {
			db = new Db();
			stmt = db.getStatement();
			sql = " SELECT DISTINCT JENIS_BORANG FROM TBLPPKBORANGPNB ORDER BY JENIS_BORANG ";
			myLogger.info(" V3: SQL listNegeri :"+ sql);
			rs = stmt.executeQuery(sql);
			listJenisBorang = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("JENIS_BORANG",rs.getString("JENIS_BORANG") == null ? "" : rs.getString("JENIS_BORANG"));
				listJenisBorang.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
		return listJenisBorang;

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
					
					
					
					if(USER_ROLE.equals("user_ppk"))
					{
						sql += " AND ID_UNIT = '"+USER_UNIT+"' ";
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
	
	public void updateFlagHantarOB(HttpSession session,String ID_BORANGPNBOB, String FLAG_HANTAROB,
			String TARIKH_HANTAROB,String NOSURAT_HANTAROB,Db db) throws Exception {
			String sql = "";
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
				r.update("ID_BORANGPNBOB", ID_BORANGPNBOB);
				r.add("FLAG_HANTAROB", FLAG_HANTAROB);
				r.add("ID_HANTAROB", USER_ID_SYSTEM);
				r.add("NOSURAT_HANTAROB", NOSURAT_HANTAROB);
				TARIKH_HANTAROB = "to_date('" +TARIKH_HANTAROB+ "','dd/MM/yyyy')";
				r.add("TARIKH_HANTAROB", r.unquote(TARIKH_HANTAROB));
				sql = r.getSQLUpdate("TBLPPKBORANGPNBOB");			
			myLogger.info("updateFlagHantarOB :::::: "+sql);				
			stmt.executeUpdate(sql);
	}
	
	public void updateFlagReturn(HttpSession session,String ID_BORANGPNBOB, String FLAG_RETURN,
			String TARIKH_RETURN,String CATATAN_RETURN,Db db) throws Exception {
			String sql = "";
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
				r.update("ID_BORANGPNBOB", ID_BORANGPNBOB);
				r.add("FLAG_RETURN", FLAG_RETURN);
				r.add("ID_RETURN", USER_ID_SYSTEM);
				r.add("CATATAN_RETURN", CATATAN_RETURN);
				TARIKH_RETURN = "to_date('" +TARIKH_RETURN+ "','dd/MM/yyyy')";
				r.add("TARIKH_RETURN", r.unquote(TARIKH_RETURN));
				sql = r.getSQLUpdate("TBLPPKBORANGPNBOB");			
			myLogger.info("updateFlagReturnOB : "+sql);				
			stmt.executeUpdate(sql);
	}
	
	
	public void updateFlagDownload(HttpSession session,String ID_BORANGPNB) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
				r.update("ID_BORANGPNB", ID_BORANGPNB);
				r.add("FLAG_DOWNLOAD", "Y");
				r.add("ID_DOWNLOAD", USER_ID_SYSTEM);
				r.add("TARIKH_DOWNLOAD", r.unquote("sysdate"));
				sql = r.getSQLUpdate("TBLPPKBORANGPNB");
			
			myLogger.info("updateFlagDownload : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			AuditTrail.logActivity(this,session,"UPD","TBLPPKBORANGPNB [ID_BORANGPNB : "+ID_BORANGPNB+"] Updated");
				
			
			
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
	
	
	
	public void updateResetPenerimaan(HttpSession session,String ID_BORANGPNB) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
				r.update("ID_BORANGPNB", ID_BORANGPNB);
				r.add("FLAG_HANTAROB", "");
				r.add("ID_HANTAROB", "");
				r.add("TARIKH_HANTAROB", "");
				r.add("NOSURAT_HANTAROB", "");
				r.add("FLAG_RETURN", "");
				r.add("ID_RETURN", "");
				r.add("TARIKH_RETURN", "");
				r.add("CATATAN_RETURN", "");	
				sql = r.getSQLUpdate("TBLPPKBORANGPNBOB");			
			myLogger.info("updateResetPenerimaan : "+sql);				
			stmt.executeUpdate(sql);
			conn.commit();
			AuditTrail.logActivity(this,session,"UPD","TBLPPKBORANGPNBOB [ID_BORANGPNB : "+ID_BORANGPNB+"] Updated");
				
			
			
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


}