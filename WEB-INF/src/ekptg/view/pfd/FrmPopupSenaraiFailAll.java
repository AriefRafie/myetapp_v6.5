package ekptg.view.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;

public class FrmPopupSenaraiFailAll extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(PendaftaranFail.class);
	private static Vector paparSeksyen = null;
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String  vm = "app/pfd/frmPopupSenaraiFailAll.jsp";
        String submit = getParam("command");
		String action = getParam("action");
        String id_urusan = getParam("Form_id_urusan");
		String id_suburusan= getParam("Form_id_suburusan");
		String id_subsuburusan= getParam("Form_id_subsuburusan");
		String id_subsubsuburusan= getParam("Form_id_subsubsuburusan");
		String id_faillama = getParam("id_FailLama");
		String noFail = "";
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		Vector seksyen = setSeksyen(user_id);
		Hashtable hA = (Hashtable) seksyen.get(0);
     	String id_seksyen = String.valueOf(hA.get("id_seksyen"));
     	String user_negeri = (String) session.getAttribute("_ekptg_user_negeri");
     	
     	
        
        if ("doChangesUrusan".equals(submit)) {
        	
        	if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
        	this.context.put("txtNoFail", noFail);
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,null));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			List lists = getSenarai(noFail,id_urusan,null,null,null,id_seksyen,user_negeri);
			setupPage(session,action,lists);
			
				
		}
		else if ("doChangesSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			this.context.put("txtNoFail", noFail);
	    	this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			List lists = getSenarai(noFail,id_urusan,id_suburusan,null,null,id_seksyen,user_negeri);
			setupPage(session,action,lists);
			
		}
		else if ("doChangesSubSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			this.context.put("txtNoFail", noFail);
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(noFail,id_urusan,id_suburusan,id_subsuburusan,null,id_seksyen,user_negeri);
			setupPage(session,action,lists);
			
		}
		else if ("doChangesSubSubSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			this.context.put("txtNoFail", noFail);
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(noFail,id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan,id_seksyen,user_negeri);
			setupPage(session,action,lists);
			
		}
        
		else{
        
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
		this.context.put("txtNoFail", noFail);
        this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan", null, null, "onChange=\"doChangesUrusan()\" "));
		this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",null,null,"onChange=\"doChangesSuburusan()\" "));
		this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,"onChange=\"doChangesSubSuburusan()\" "));
		this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null, "onChange=\"doChangesSubSubSuburusan()\" "));
		List lists = getSenaraiFailAll(noFail,id_seksyen,user_negeri);
		setupPage(session,action,lists);
		
		}
        
		return vm;
	}
	
	public void setupPage(HttpSession session,String action1,Vector list) {
		try {
		
		this.context.remove("totalRecords");
		//this.context.put("totalRecords",FrmDaftarFailData.totalFail(session));
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action1)) {
	    	page++;
	    } else if ("getPrevious".equals(action1)) {
	    	page--;
	    } else if ("getPage".equals(action1)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action1)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
	  
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("SenaraiFail",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}	
	public List getSenarai(String noFail,String id_urusan, String id_suburusan, String id_subsuburusan, String id_subsubsuburusan, String seksyen, String negeri)
	throws Exception {
		
		Db db = null;
		List result = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			result = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL FROM TBLPFDFAIL A  WHERE A.ID_FAIL NOT IN (SELECT ID_FAILASAL FROM TBLPFDFAILMAPPING WHERE ID_FAILASAL IS NOT NULL) AND A.NO_FAIL IS NOT NULL "+
					"AND A.ID_URUSAN NOT IN (382, 6, 108, 3, 4, 2, 12, 7, 8, 9, 13, 17, 1, 309, 108, 5,10, 11) AND A.ID_NEGERI = '"+negeri+"' AND A.ID_SEKSYEN = '"+seksyen+"' AND A.ID_URUSAN = "+id_urusan ;
			
			 if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(NO_FAIL_ARKIB) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
			
			
			if (id_suburusan != null){
				sql += " AND ID_SUBURUSAN = "+id_suburusan;
			}
			if (id_subsuburusan != null){
				sql += " AND ID_SUBSUBURUSAN = "+id_subsuburusan;
			}
			if (id_subsubsuburusan != null){
				sql += " AND ID_SUBSUBSUBURUSAN = "+id_subsubsuburusan;
			}
			
			
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL",checkIsNull(rs.getString("ID_FAIL")));
				h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
				h.put("TAJUK_FAIL",checkIsNull(rs.getString("TAJUK_FAIL")));
				bil++;
				
				result.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
	}
	public List getSenaraiFailAll(String noFail,String seksyen,String negeri)
	throws Exception {
		
		Db db = null;
		List resultAll = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			resultAll = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, A.NO_FAIL, A.TAJUK_FAIL FROM TBLPFDFAIL A  WHERE A.ID_FAIL NOT IN (SELECT ID_FAILASAL FROM TBLPFDFAILMAPPING WHERE ID_FAILASAL IS NOT NULL) AND A.NO_FAIL IS NOT NULL AND A.ID_URUSAN NOT IN (382, 6, 108, 3, 4, 2, 12, 7, 8, 9, 13, 17, 1, 309, 108, 5,10, 11, 161042) AND A.ID_NEGERI = '"+negeri+"' AND A.ID_SEKSYEN = "+seksyen;
			
			 if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
			
			
			 
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAIL",checkIsNull(rs.getString("ID_FAIL")));
				h.put("NO_FAIL",checkIsNull(rs.getString("NO_FAIL")));
				h.put("TAJUK_FAIL",checkIsNull(rs.getString("TAJUK_FAIL")));
				bil++;
				resultAll.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return resultAll;
		
	}
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	public static Vector setSeksyen(String user_id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparSeksyen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select a.id_seksyen, a.id_negeri,b.kod_seksyen, b.nama_seksyen from users_internal a, tblrujseksyen b where a.id_seksyen = b.id_seksyen and user_id = '"+user_id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  h.put("kod_seksyen", rs.getString("kod_seksyen")==null?"":rs.getString("kod_seksyen"));
		    	  h.put("nama_seksyen", rs.getString("nama_seksyen")==null?"":rs.getString("nama_seksyen"));
		    	  paparSeksyen.addElement(h); 
		      }
		      
		      return paparSeksyen;
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
		
	}
	
	

}
