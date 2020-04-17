package ekptg.view.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;

public class FrmPopupSenaraiFailArkib extends AjaxBasedModule {

	static Logger myLogger = Logger.getLogger(PendaftaranFail.class);
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String  vm = "app/pfd/frmPopupSenaraiFailArkib.jsp";
        String submit = getParam("command");
		String action = getParam("action");
        String id_urusan = getParam("Form_id_urusan");
		String id_suburusan= getParam("Form_id_suburusan");
		String id_subsuburusan= getParam("Form_id_subsuburusan");
		String id_subsubsuburusan= getParam("Form_id_subsubsuburusan");
		String id_failarkib = getParam("idFailArkib");
		String noFail = "";
		String keyword = "";
		Vector lists = new Vector();
		Vector totalAll = new Vector();
		
        if ("doChangesUrusan".equals(submit)) {
        	
        	if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
        	if (!"".equals(getParam("keyword")))
        		keyword = getParam("keyword");
        	
        	this.context.put("txtNoFail", noFail);
        	this.context.put("keyword", keyword);
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,null));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			lists = getSenarai(noFail,id_urusan,null,null,null,keyword);
			setupPage(session,action,lists);
			totalAll = getTotalFailArkib();
			this.context.put("JumlahFail", totalAll.size());
				
		}
		else if ("doChangesSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			if (!"".equals(getParam("keyword")))
        		keyword = getParam("keyword");
        	
        	this.context.put("txtNoFail", noFail);
        	this.context.put("keyword", keyword);
	    	this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			lists = getSenarai(noFail,id_urusan,id_suburusan,null,null,keyword);
			setupPage(session,action,lists);
			totalAll = getTotalFailArkib();
			this.context.put("JumlahFail", totalAll.size());
		}
		else if ("doChangesSubSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			if (!"".equals(getParam("keyword")))
        		keyword = getParam("keyword");
        	
        	this.context.put("txtNoFail", noFail);
        	this.context.put("keyword", keyword);
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			lists = getSenarai(noFail,id_urusan,id_suburusan,id_subsuburusan,null,keyword);
			setupPage(session,action,lists);
			totalAll = getTotalFailArkib();
			this.context.put("JumlahFail", totalAll.size());
		}
		else if ("doChangesSubSubSuburusan".equals(submit)) {
			
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			if (!"".equals(getParam("keyword")))
        		keyword = getParam("keyword");
        	
        	this.context.put("txtNoFail", noFail);
        	this.context.put("keyword", keyword);
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			lists = getSenarai(noFail,id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan,keyword);
			setupPage(session,action,lists);
			totalAll = getTotalFailArkib();
			this.context.put("JumlahFail", totalAll.size());
		}
        
		else{
        
			if (!"".equals(getParam("txtNoFail")))
  	    		noFail = getParam("txtNoFail");
			if (!"".equals(getParam("keyword")))
        		keyword = getParam("keyword");
        	
        this.context.put("txtNoFail", noFail);
        this.context.put("keyword", keyword);
        this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", null, null, "onChange=\"doChangesUrusan()\" "));
		this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",null,null,"onChange=\"doChangesSuburusan()\" "));
		this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,"onChange=\"doChangesSubSuburusan()\" "));
		this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null, "onChange=\"doChangesSubSubSuburusan()\" "));
		lists = getSenaraiFailArkib(noFail,keyword);
		setupPage(session,action,lists);
		totalAll = getTotalFailArkib();
		this.context.put("JumlahFail",totalAll.size());
		
		
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
	
	public Vector getSenarai(String noFail,String id_urusan, String id_suburusan, String id_subsuburusan, String id_subsubsuburusan, String keyword)
	throws Exception {
		
		Db db = null;
		Vector result = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			result = new Vector();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAILARKIB, A.NO_FAIL_ARKIB, B.NAMA_URUSAN, C.NAMA_SUBURUSAN, D.NAMA_SUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN FROM TBLRUJFAILARKIB A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E WHERE A.ID_URUSAN = B.ID_URUSAN AND A.ID_SUBURUSAN = C.ID_SUBURUSAN AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN AND A.ID_URUSAN = "+id_urusan ;
			
			 if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(A.NO_FAIL_ARKIB) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
			
			 if (keyword != null) {
					if (!keyword.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND (UPPER(A.NO_FAIL_ARKIB) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(B.NAMA_URUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(C.NAMA_SUBURUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(D.NAMA_SUBSUBURUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(E.NAMA_SUBSUBSUBURUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%')";
					}
				}
		      
			
			
			if (id_suburusan != null){
				setLimit = false;
				sql += " AND A.ID_SUBURUSAN = "+id_suburusan;
			}
			if (id_subsuburusan != null){
				setLimit = false;
				sql += " AND A.ID_SUBSUBURUSAN = "+id_subsuburusan;
			}
			if (id_subsubsuburusan != null){
				setLimit = false;
				sql += " AND A.ID_SUBSUBSUBURUSAN = "+id_subsubsuburusan;
			}
			
			
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAILARKIB",checkIsNull(rs.getString("ID_FAILARKIB")));
				h.put("NO_FAIL_ARKIB",checkIsNull(rs.getString("NO_FAIL_ARKIB")));
				h.put("NAMA_URUSAN",checkIsNull(rs.getString("NAMA_URUSAN")));
				h.put("NAMA_SUBURUSAN",checkIsNull(rs.getString("NAMA_SUBURUSAN")));
				h.put("NAMA_SUBSUBURUSAN",checkIsNull(rs.getString("NAMA_SUBSUBURUSAN")));
				h.put("NAMA_SUBSUBSUBURUSAN",checkIsNull(rs.getString("NAMA_SUBSUBSUBURUSAN")));
				bil++;
				if ((setLimit && bil <= 50) || setLimit == false) {	
					result.addElement(h);		
				}
				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
	}
	public Vector getSenaraiFailArkib(String noFail, String keyword)
	throws Exception {
		
		Db db = null;
		Vector resultAll = null;
		String sql = "";
		Hashtable h = null;
		boolean setLimit = true;
		try {
			db = new Db();
			resultAll = new Vector();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAILARKIB, A.NO_FAIL_ARKIB, B.NAMA_URUSAN, C.NAMA_SUBURUSAN, D.NAMA_SUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN " +
				" FROM TBLRUJFAILARKIB A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E " +
				" WHERE A.ID_URUSAN = B.ID_URUSAN AND A.ID_SUBURUSAN = C.ID_SUBURUSAN " +
				" AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN" ;
			
			 if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(NO_FAIL_ARKIB) LIKE '%" + noFail.toUpperCase().trim() + "%'";
					}
				}
			
			 if (keyword != null) {
					if (!keyword.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND (UPPER(A.NO_FAIL_ARKIB) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(B.NAMA_URUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(C.NAMA_SUBURUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(D.NAMA_SUBSUBURUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%'";
						sql = sql + " OR UPPER(E.NAMA_SUBSUBSUBURUSAN) LIKE '%' ||'" + keyword.toUpperCase().trim() + "'|| '%')";
					}
				}
		      
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			while(rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_FAILARKIB",checkIsNull(rs.getString("ID_FAILARKIB")));
				h.put("NO_FAIL_ARKIB",checkIsNull(rs.getString("NO_FAIL_ARKIB")));
				h.put("NAMA_URUSAN",checkIsNull(rs.getString("NAMA_URUSAN")));
				h.put("NAMA_SUBURUSAN",checkIsNull(rs.getString("NAMA_SUBURUSAN")));
				h.put("NAMA_SUBSUBURUSAN",checkIsNull(rs.getString("NAMA_SUBSUBURUSAN")));
				h.put("NAMA_SUBSUBSUBURUSAN",checkIsNull(rs.getString("NAMA_SUBSUBSUBURUSAN")));
				bil++;
				if ((setLimit && bil <= 50) || setLimit == false) {	
					resultAll.addElement(h);		
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return resultAll;
		
	}
	
	public Vector getTotalFailArkib()
			throws Exception {
				
				Db db = null;
				Vector totalAll = null;
				String sql = "";
				Hashtable h = null;
				boolean setLimit = true;
				try {
					db = new Db();
					totalAll = new Vector();
					Statement stmt = db.getStatement();
					sql = "SELECT A.ID_FAILARKIB FROM TBLRUJFAILARKIB A" ;
					
					
					ResultSet rs = stmt.executeQuery(sql);
					int bil = 1;
					while(rs.next()) {
						h = new Hashtable();
						h.put("ID_FAILARKIB",checkIsNull(rs.getString("ID_FAILARKIB")));
						totalAll.addElement(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return totalAll;
				
			}
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	

}
