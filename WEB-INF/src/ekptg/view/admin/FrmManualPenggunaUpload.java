/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public class FrmManualPenggunaUpload extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger myLogger = Logger.getLogger(ekptg.view.admin.FrmManualPenggunaUpload.class);
	String skrin_name = "app/admin/SenaraiUserManual/senaraiManualMengikutPeranan.jsp";	
 
    @Override
    public String doTemplate2() throws Exception {
    	
    	 List listDokumen = null;
    	 List list_TBLRUJSEKSYEN = null;
    	 List listGroup = null;
    	 List listRole = null;
    	 
      	HttpSession session = this.request.getSession();
		String command = getParam("command");
		
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
		String USER_LOGIN_ROLE =  (String) session.getAttribute("_portal_role");
		//String role = (String) session.getAttribute("myrole");
		String portal_role = (String)session.getAttribute("myrole");
		context.put("portal_role",portal_role);
		myLogger.info("portal_role -- "+portal_role);
		String user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		
		String cssUser = getCss(portal_role);
		myLogger.info("cssUser -- "+cssUser);
		
		
		//this.context.put("CSS_ID","");
		this.context.put("KETERANGAN", "");	
		this.context.put("NAMA_DOKUMEN", "");
		this.context.put("tambahDocUser", "");
		
		String action = getParam("action");
		
		listGroup = listGroupRole(session, "","","");
		this.context.put("listGroup",listGroup);
		
		myLogger.info("command -- "+command);
		
		if(command.equals("showSenaraiRoleByGroup"))
		{
			this.context.put("SuccessMesejDeleteModule", "");
			String DELETE = getParam("DELETE");			
			if(DELETE.equals("Y"))
			{
				String ID = getParam("ID");
				//deleteRole(session,ID);
			}
			
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			
			myLogger.info("CSS_TITLE : "+ CSS_TITLE);
			
		listRole = listRole(session, "",CSS_TITLE,null);
		this.context.put("listRole",listRole);	
		
		this.context.put("KETERANGAN", "");	
		this.context.put("NAMA_DOKUMEN", "");
		this.context.put("tambahDocUser", "");
		
		skrin_name ="app/admin/SenaraiUserManual/listPerananbyGroup.jsp";
		}
		
		else if(command.equals("showSenaraiDokumen"))
		{
			this.context.put("SuccessMesejDeleteModule", "");
			String DELETE = getParam("DELETE");			
			if(DELETE.equals("Y"))
			{
				String ID = getParam("ID");
				//deleteRole(session,ID);
			}
			
			String CSS_TITLE = getParam("CSS_TITLE");
			this.context.put("CSS_TITLE",CSS_TITLE);
			
			myLogger.info("CSS_TITLE : "+ CSS_TITLE);
			
		listRole = listRole(session, "",CSS_TITLE,null);
		this.context.put("listRole",listRole);	
		
		listDokumen = getListDokumen(session, CSS_TITLE, "manual");
		this.context.put("listDokumen",listDokumen);
		
		listGroup = listGroupRole(session, "",CSS_TITLE,"");
		this.context.put("listGroup",listGroup);
		
		skrin_name ="app/admin/SenaraiUserManual/listDokumenUM.jsp";
		
		}else if(command.equals("simpanDokumen")){
			
			myLogger.info("masuk simpan doc >>>> ");
			
			String CSS_ID = getParam("CSS_ID");
			myLogger.info("CSS_ID >>>> "+ CSS_ID);
			
			String KETERANGAN = getParam("KETERANGAN");
			myLogger.info("KETERANGAN >>>> "+ KETERANGAN);
			String FLAG_ADMIN = getParam("FLAG_ADMIN");
			this.context.put("FLAG_ADMIN", FLAG_ADMIN);
			
			this.context.put("CSS_ID", CSS_ID);	
			
			this.context.put("commandDoc", "showDokumen");
			this.context.put("after_saveDOC", "Y");
			
	       /*	this.context.put("isComplete", true);
	       	this.context.put("COOR_UPLOAD",getParam("getPageCoor"));*/
			
	       	String returnDivUpload = getParam("returnDivUpload");
			this.context.put("returnDivUpload",returnDivUpload);
			
			
			uploadFiles(CSS_ID,FLAG_ADMIN);
			
			listDokumen = getListDokumen(session, CSS_ID, "manual");
			this.context.put("listDokumen",listDokumen);
			
			/*listGroup = listGroupRole(session, "",CSS_ID,"");
			this.context.put("listGroup",listGroup);*/
			
			this.context.put("KETERANGAN", "");	
			this.context.put("NAMA_DOKUMEN", "");
			this.context.put("tambahDocUser", "");
			
			skrin_name = "app/admin/SenaraiUserManual/senaraiManualMengikutPeranan.jsp";
			//skrin_name = "app/admin/SenaraiUserManual/listDokumenUM.jsp";
			
		}
		
		else if(command.equals("showDokumen")){
			
			myLogger.info("masuk showDokumen >>>> " );	
			
			this.context.put("KETERANGAN", "");	
			this.context.put("NAMA_DOKUMEN", "");
			this.context.put("tambahDocUser", "");
			
			String CSS_ID = getParam("css_id");
			myLogger.info("masuk CSS_ID show >>>> " + CSS_ID);	
			String FLAG_DELETE = getParam("FLAG_DELETE");
			
			if (FLAG_DELETE.equals("Y")){
				//delete dokumen
				String ID_DOKUMENADMIN = getParam("ID_DOKUMENADMIN");
				deleteDokumen(session, CSS_ID, ID_DOKUMENADMIN);	
				this.context.put("SuccessMesejDeleteUser", "Dokumen Berjaya Dihapus");
			}
			
			listDokumen = getListDokumen(session, CSS_ID, "manual");
			this.context.put("listDokumen",listDokumen);
			
			this.context.put("KETERANGAN", "");	
			this.context.put("NAMA_DOKUMEN", "");
			this.context.put("tambahDocUser", "");
			
			skrin_name = "app/admin/SenaraiUserManual/listDokumenUM.jsp";
		}
		else if(command.equals("simpanDetails")){
			
			myLogger.info("masuk simpan detailssss >>>> ");
			
			String CSS_ID = getParam("CSS_ID");
			//this.context.put("ruj.CSS_TITLE",CSS_ID);
			
			String ID_DOKUMENADMIN = getParam("ID_DOKUMENADMIN");
			myLogger.info("get ID_DOKUMENADMIN -- "+ID_DOKUMENADMIN);
			
			//function update keterangan
			String saveInfo = saveDetailsDoc(session,ID_DOKUMENADMIN);
			
			listDokumen = getListDokumen(session, CSS_ID, "manual");
			this.context.put("listDokumen",listDokumen);
			
			skrin_name = "app/admin/SenaraiUserManual/listDokumenUM.jsp";
			
		}
		
		return skrin_name;
	}

    
    public String saveDetailsDoc(HttpSession session, String ID_DOKUMENADMIN) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		
		String KETERANGAN = getParam("KETERANGAN"+ID_DOKUMENADMIN);
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if (!ID_DOKUMENADMIN.equals("")){
				
				
				r.update("ID_DOKUMENADMIN", ID_DOKUMENADMIN);
				r.add("KETERANGAN", KETERANGAN);
				
				sql = r.getSQLUpdate("TBLRUJDOKUMENADMIN");
				
				//myLogger.info("sql update -- "+ sql);
				AuditTrail.logActivity(this,session,"UPD","DOKUMEN ["+ID_DOKUMENADMIN+"] Updated");
			
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
	    	throw new Exception("Ralat Update DOKUMEN :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return ID_DOKUMENADMIN;
	}
    
    public void deleteDokumen(HttpSession session, String CSS_ID, String ID_DOKUMENADMIN) throws Exception {
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
    
    private void uploadFiles(String CSS_ID, String FLAG_ADMIN) throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    if (isMultipart) {
		    List items = upload.parseRequest(this.request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	 saveData(item, CSS_ID, FLAG_ADMIN);
		      }
		    }
	    }
	  }
    
    private void saveData(FileItem item, String CSS_ID, String FLAG_ADMIN) throws Exception {
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
        	ps.setString(2, CSS_ID);
        	ps.setString(3, FLAG_ADMIN);
        	ps.setString(4,item.getName());
        	ps.setString(5,item.getContentType());
        	ps.setBinaryStream(6,item.getInputStream(),(int)item.getSize());
        	ps.setString(7,USER_ID_SYSTEM);
        	ps.setString(8,getParam("KETERANGAN"));//
        	
        	/*String NAMA_DOC = getParam("NAMA_DOC");
			myLogger.info("NAMA_DOC >>>> "+NAMA_DOC);
			
			String KETERANGAN = getParam("KETERANGAN");
			myLogger.info("KETERANGAN >>>> "+KETERANGAN);*/
			
        	ps.executeUpdate();
        	
        	
            con.commit();
	    } finally {
		      if (db != null) db.close();
	    }
    }
    
    private String getCss(String portal_role) throws SQLException, DbException {
		
		Db db = null;
		String sql = "";
		String css = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
				sql = 	" SELECT THEME FROM ROLE WHERE NAME = '"+portal_role+"'" ;
						
				myLogger.info("GET CSS : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("THEME") != null)
				{
					css = rs.getString("THEME");
				}
			}
			
			//myLog.info("css xx -- "+css);
			return css;
		} finally {
			if (db != null)
				db.close();
		}
	}
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List getListDokumen(HttpSession session, String CSS_TITLE, String flag)throws Exception {
				
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
								" AND ID_REF = '"+ CSS_TITLE +"'";
					
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
						//h.put("ID_REF",rs.getString("ID_REF") == null ? "" : rs.getString("ID_REF"));
						
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
	public List listRole(HttpSession session, String carianTerperinci,String CSS_TITLE,Db db)throws Exception {
		
		myLogger.info(" ----------------------- CSS_TITLE : "+CSS_TITLE);
		
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRole = null;
		String sql = "";
		String condition = "";
		try {
			
			if(db==null)
			{
				db1 = new Db();
			}
			else
			{
				db1 = db;
			}
			stmt = db1.getStatement();
			
			
			if(!carianTerperinci.equals(""))
			{
				condition += " AND (UPPER(R.NAME) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(R.DESCRIPTION) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(R.THEME) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
						" OR UPPER(P.CSS_TITLE) LIKE '%"+carianTerperinci.toUpperCase()+"%' " +
								" )  "; 		
			}	
			
			sql = " SELECT ID,LAYER, NAME, DESCRIPTION, CSS_TITLE, TOTAL_ROLE "+
	          " FROM ( "+
	          " SELECT DISTINCT  R.ROWID AS ID,2 AS LAYER, R.NAME, R.DESCRIPTION,P.CSS_TITLE, "+
	          " 0 AS TOTAL_ROLE  "+
	          " FROM ROLE R, PAGE_CSS P  "+
	          " WHERE R.NAME IS NOT NULL ";
			  if(!CSS_TITLE.equals(""))
			  {
				  sql += " AND R.THEME = P.CSS_NAME  "+condition+
		          " AND P.CSS_TITLE = '"+CSS_TITLE+"' ";
			  }
			  else
			  {
				  sql += " AND R.THEME = P.CSS_NAME(+)  "+condition+
				          " AND P.CSS_NAME IS NULL ";
			  }
			  sql += " ORDER BY P.CSS_TITLE,R.NAME) RRR ";
			  
			
			myLogger.info(" mm: SQL listRole :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listRole = Collections.synchronizedList(new ArrayList());
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
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME"));
				h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
				h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE").toUpperCase());
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "NONE" : rs.getString("TOTAL_ROLE"));
				h.put("ROLEUNIK",rs.getString("NAME") == null ? "" : replaceString(rs.getString("NAME").toUpperCase()));
				listRole.add(h);
			}

		} finally {
			if(db==null)
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (db1 != null)
					db1.close();
			}
		}

		return listRole;

	}
    
    @SuppressWarnings("unchecked")
	public List listGroupRole(HttpSession session, String carianTerperinci,String CSS_NAME,String DROPDOW)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listRole = null;
		String sql = "";
		String condition = "";
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			sql = " SELECT ROWNUM AS ID_TEMP,JENIS_MIME,NAMA_DOC,LAYER, NAME, DESCRIPTION, CSS_TITLE, TITLE, CSS_NAME, TOTAL_ROLE "+
	          " FROM ( "+
	          " SELECT DISTINCT P.JENIS_MIME, P.NAMA_DOC,1 AS LAYER, '' AS NAME, '' AS DESCRIPTION,P.CSS_TITLE, P.CSS_TITLE AS TITLE,P.CSS_NAME, "+
	          " (SELECT COUNT(*) FROM ROLE RR,PAGE_CSS PP " +
	          " WHERE RR.THEME = R.THEME " + condition + 
	          " AND RR.THEME = PP.CSS_NAME) AS TOTAL_ROLE  "+
	          " FROM ROLE R, PAGE_CSS P  "+
	          " WHERE P.CSS_NAME = R.THEME(+) ";
			if(DROPDOW.equals(""))
			{
			sql+=" UNION "+     
					" SELECT DISTINCT '' AS JENIS_MIME,'' AS NAMA_DOC,1 AS LAYER, '' AS NAME, '' AS DESCRIPTION, "+ 
					" P.CSS_TITLE, '' AS TITLE, "+ 
					" P.CSS_NAME, "+ 
					" (SELECT COUNT (*) "+ 
					" FROM ROLE RR, PAGE_CSS PP "+ 
					" WHERE RR.THEME = R.THEME "  + condition + 
					" AND RR.THEME = PP.CSS_NAME(+) AND PP.CSS_NAME IS NULL ) AS TOTAL_ROLE "+ 
					" FROM ROLE R, PAGE_CSS P "+ 
					" WHERE P.CSS_NAME(+) = R.THEME AND P.CSS_NAME IS NULL  ";
			}
	          sql+=") RRR ";
			
			sql += " WHERE LAYER IS NOT NULL ";
			if(!carianTerperinci.equals(""))
			{
	          sql += " AND RRR.TOTAL_ROLE > 0 ";
			}
			if(!CSS_NAME.equals(""))
			{
			  sql += " AND RRR.CSS_NAME = '"+CSS_NAME+"' ";
			}
			 sql+=" ORDER BY TITLE";
			
			
			myLogger.info(" mm: SQL listGroupRole :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listRole = Collections.synchronizedList(new ArrayList());
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
				h.put("ID",rs.getString("ID_TEMP") == null ? "" : rs.getString("ID_TEMP"));
				h.put("LAYER",rs.getString("LAYER") == null ? "" : rs.getString("LAYER").toUpperCase());	
				h.put("NAME",rs.getString("NAME") == null ? "" : rs.getString("NAME").toUpperCase());
				h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME").toUpperCase());
				h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC").toUpperCase());
				h.put("DESCRIPTION",rs.getString("DESCRIPTION") == null ? "" : rs.getString("DESCRIPTION").toUpperCase());
				h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE"));
				h.put("TITLE",rs.getString("TITLE") == null ? "" : rs.getString("TITLE").toUpperCase());
				h.put("CSS_NAME",rs.getString("CSS_NAME") == null ? "" : rs.getString("CSS_NAME"));
				h.put("TOTAL_ROLE",rs.getString("TOTAL_ROLE") == null ? "NONE" : rs.getString("TOTAL_ROLE"));
				h.put("GROUPUNIK",rs.getString("TITLE") == null ? "" : replaceString(rs.getString("TITLE").toUpperCase()));
				listRole.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listRole;

	}
    
    public String replaceString(String asal)
	{		
		return asal.replace(".","_").replace(" ","").replace("/","X_X");
	}
}
