/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ekptg.view;

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
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class FrmManualPenggunaAll extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9012102407918820290L;
	private final String PATH="app/admin/ManualPengguna/";
    private static Logger myLog = Logger.getLogger(ekptg.view.FrmManualPenggunaAll.class);
      
    @Override
    public String doTemplate2() throws Exception {
    	
    	 List listDokumen = null;

      	HttpSession session = this.request.getSession();
		String command = getParam("command");
		
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String USER_LOGIN_SYSTEM = (String)session.getAttribute("_portal_login");	
		String USER_LOGIN_ROLE =  (String) session.getAttribute("_portal_role");
		//String role = (String) session.getAttribute("myrole");
		String portal_role = (String)session.getAttribute("myrole");
		context.put("portal_role",portal_role);
		myLog.info("portal_role -- "+portal_role);
		String user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		
		String cssUser = getCss(portal_role);
		//myLog.info("cssUser -- "+cssUser);
		
		String action = getParam("action");
		
		String CSS_TITLE = getCSS_TITLE(cssUser);
		this.context.put("CSS_TITLE",CSS_TITLE);
		
		//myLog.info("CSS_TITLE -- "+CSS_TITLE);
		
		listDokumen = getListDoc(session, CSS_TITLE); //flag user untuk uv3
		//setupPageList(session, action, listDokumen, "listDokumen",command,"Senarai_Doc"+USER_ID_SYSTEM);
		this.context.put("listDokumen", listDokumen);
		
		
		
		
      	
		String vm = PATH+"frmManualPenggunaAll.jsp";
        return vm;
        
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
						
			//myLog.info("GET CSS : "+sql);
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
    
    private String getCSS_TITLE(String cssUser) throws SQLException, DbException {
		
		Db db = null;
		String sql = "";
		String css = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
				sql = 	" SELECT   DISTINCT " +
						" P.CSS_TITLE  " +
						" FROM   ROLE R, PAGE_CSS P " +
						" WHERE   P.CSS_NAME = R.THEME(+) " +
						" AND P.CSS_NAME = '"+ cssUser +"'";
						
			//myLog.info("GET CSS_TITLE : "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				
				if(rs.getString("CSS_TITLE") != null)
				{
					css = rs.getString("CSS_TITLE");
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
	public List getListDoc(HttpSession session, String cssUser)throws Exception {
				
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
								" WHERE FLAG_ADMIN = 'manual' "+
								" AND ID_REF = '"+ cssUser +"'";
						
						/*" SELECT CONTENT, CSS_NAME, CSS_TITLE, JENIS_MIME, NAMA_DOC "+
						" FROM PAGE_CSS "+
						" WHERE CSS_NAME = '"+ cssUser +"'" ;*/
				
					
					myLog.info("SQL LIST DOC - "+sql);
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
						/*h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("CONTENT", rs.getString("CONTENT") == null ? "" : rs.getString("CONTENT"));
						h.put("CSS_NAME",rs.getString("CSS_NAME") == null ? "" : rs.getString("CSS_NAME"));
						h.put("CSS_TITLE",rs.getString("CSS_TITLE") == null ? "" : rs.getString("CSS_TITLE"));
						h.put("JENIS_MIME",rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));
						h.put("NAMA_DOC",rs.getString("NAMA_DOC") == null ? "" : rs.getString("NAMA_DOC"));*/
						
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("ID_DOKUMENADMIN", rs.getString("ID_DOKUMENADMIN") == null ? "" : rs.getString("ID_DOKUMENADMIN"));
						h.put("ID_REF",rs.getString("ID_REF") == null ? "" : rs.getString("ID_REF"));
						h.put("FLAG_ADMIN",rs.getString("FLAG_ADMIN") == null ? "" : rs.getString("FLAG_ADMIN"));
						h.put("NAMA_DOC",rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
						h.put("JENIS_DOKUMEN",rs.getString("JENIS_DOKUMEN") == null ? "" : rs.getString("JENIS_DOKUMEN"));
						
						h.put("DOKUMEN",rs.getString("DOKUMEN") == null ? "" : rs.getString("DOKUMEN"));
						h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
						h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
						
						h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
						h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
						h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
						
						listDoc.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listDoc;
				
			}
}
