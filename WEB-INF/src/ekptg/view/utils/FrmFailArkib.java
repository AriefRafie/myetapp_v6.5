package ekptg.view.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmFailArkib extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmFailArkib.class);
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		String submit = getParam("command"); // First Level - default by
		String action = getParam("action");// Second Level
		
		//app/utils/frmFailArkib.jsp
		String template = "";
		String id_urusan = getParam("Form_id_urusan");
		String id_urusan_add = getParam("Form_id_urusan_add");
		String id_suburusan= getParam("Form_id_suburusan");
		String id_suburusan_add= getParam("Form_id_suburusan_add");
		String id_subsuburusan= getParam("Form_id_subsuburusan");
		String id_subsuburusan_add= getParam("Form_id_subsuburusan_add");
		String id_subsubsuburusan= getParam("Form_id_subsubsuburusan");
		String id_subsubsuburusan_add= getParam("Form_id_subsubsuburusan_add");
		String id_failarkib = getParam("idFailArkib");
		
		context.put("mode","");
		this.context.put("result", "");
		this.context.put("SenaraiFail", "");
		
		
		
		if ("doChangesUrusan".equals(submit)) {
			template = "app/utils/frmFailArkib.jsp";
			
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,null));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			List lists = getSenarai(id_urusan,null,null,null);
			setupPage(session, action, lists);
			
				
		}
		else if ("doChangesSuburusan".equals(submit)) {
	    	this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			List lists = getSenarai(id_urusan,id_suburusan,null,null);
			setupPage(session, action, lists);
			template = "app/utils/frmFailArkib.jsp";
		}
		else if ("doChangesSubSuburusan".equals(submit)) {
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(id_urusan,id_suburusan,id_subsuburusan,null);
			setupPage(session, action, lists);
			template = "app/utils/frmFailArkib.jsp";
		}
		else if ("doChangesSubSubSuburusan".equals(submit)) {
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", Utils.parseLong(id_urusan), null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(id_urusan, "Form_id_suburusan", Utils.parseLong(id_suburusan), null, "onChange=\"doChangesSuburusan()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan, "Form_id_subsuburusan", Utils.parseLong(id_subsuburusan), null, "onChange=\"doChangesSubSuburusan()\" "));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan, "Form_id_subsubsuburusan", Utils.parseLong(id_subsubsuburusan), null, "onChange=\"doChangesSubSubSuburusan()\" "));
			List lists = getSenarai(id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan);
			setupPage(session, action, lists);
			template = "app/utils/frmFailArkib.jsp";
		}
		else if ("doChangesUrusanAdd".equals(submit)) {
			
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", Utils.parseLong(id_urusan_add), null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSuburusanByIdUrusan(id_urusan_add, "Form_id_suburusan_add", Utils.parseLong(id_suburusan_add), null, "onChange=\"doChangesSuburusanAdd()\" "));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSubUrusan("Form_id_subsuburusan_add",null,null,null));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan_add",null,null,null));
			template = "app/utils/frmEditFailArkib.jsp";	
		}
		else if ("doChangesSuburusanAdd".equals(submit)) {
			template = "app/utils/frmEditFailArkib.jsp";
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", Utils.parseLong(id_urusan_add), null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSuburusanByIdUrusan(id_urusan_add, "Form_id_suburusan_add", Utils.parseLong(id_suburusan_add), null, "onChange=\"doChangesSuburusanAdd()\" "));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan_add, "Form_id_subsuburusan_add", Utils.parseLong(id_subsuburusan_add), null, "onChange=\"doChangesSubSuburusanAdd()\" "));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan_add",null,null,null));
			
		}
		else if ("doChangesSubSuburusanAdd".equals(submit)) {
			template = "app/utils/frmEditFailArkib.jsp";
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", Utils.parseLong(id_urusan_add), null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSuburusanByIdUrusan(id_urusan_add, "Form_id_suburusan_add", Utils.parseLong(id_suburusan_add), null, "onChange=\"doChangesSuburusanAdd()\" "));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan_add, "Form_id_subsuburusan_add", Utils.parseLong(id_subsuburusan_add), null, "onChange=\"doChangesSubSuburusanAdd()\" "));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan_add, "Form_id_subsubsuburusan_add", Utils.parseLong(id_subsubsuburusan_add), null, "onChange=\"doChangesSubSubSuburusanAdd()\" "));
			
		}
		else if ("doChangesSubSubSuburusanAdd".equals(submit)) {
			template = "app/utils/frmEditFailArkib.jsp";
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", Utils.parseLong(id_urusan_add), null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSuburusanByIdUrusan(id_urusan_add, "Form_id_suburusan_add", Utils.parseLong(id_suburusan_add), null, "onChange=\"doChangesSuburusanAdd()\" "));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSuburusanByIdSubUrusan(id_suburusan_add, "Form_id_subsuburusan_add", Utils.parseLong(id_subsuburusan_add), null, "onChange=\"doChangesSubSuburusanAdd()\" "));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusanByIdSubSubUrusan(id_subsuburusan_add, "Form_id_subsubsuburusan_add", Utils.parseLong(id_subsubsuburusan_add), null, "onChange=\"doChangesSubSubSuburusanAdd()\" "));
				
		}
			
		else if ("addNewFailArkib".equals(submit)) {
			template = "app/utils/frmEditFailArkib.jsp";
			context.put("mode","add");
			context.put("details","");
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", null, null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSubUrusan1("Form_id_suburusan_add",null,null,null));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSubUrusan("Form_id_subsuburusan_add",null,null,null));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan_add",null,null,null));
	
		}
		else if ("doAddNew".equals(submit)) {
			insert(id_urusan_add,id_suburusan_add,id_subsuburusan_add,id_subsubsuburusan_add);
			template = "app/utils/frmEditFailArkib.jsp";
		}
		else if ("editFailArkib".equals(submit)) {
				
			context.put("mode","edit");
			template = "app/utils/frmEditFailArkib.jsp";
			Hashtable h = getFailArkib(id_failarkib);
			this.context.put("idFailArkib",id_failarkib);
			this.context.put("details",h);
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", Utils.parseLong(h.get("ID_URUSAN").toString()), null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSubUrusan1("Form_id_suburusan_add",Utils.parseLong(h.get("ID_SUBURUSAN").toString()),null,"onChange=\"doChangesSuburusanAdd()\" "));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSubUrusan("Form_id_subsuburusan_add",Utils.parseLong(h.get("ID_SUBSUBURUSAN").toString()),null,"onChange=\"doChangesSubSuburusanAdd()\" "));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan_add",Utils.parseLong(h.get("ID_SUBSUBSUBURUSAN").toString()),null,"onChange=\"doChangesSubSubSuburusanAdd()\" "));
		
		} else if ("doUpdate".equals(submit)) {
			this.context.put("selectUrusanAdd", HTML.SelectUrusanArkib("Form_id_urusan_add", Utils.parseLong(id_urusan_add), null, "onChange=\"doChangesUrusanAdd()\" "));
			this.context.put("selectSuburusanAdd", HTML.SelectSubUrusan1("Form_id_suburusan_add",Utils.parseLong(id_suburusan_add),null,"onChange=\"doChangesSuburusanAdd()\" "));
			this.context.put("selectSubSuburusanAdd", HTML.SelectSubSubUrusan("Form_id_subsuburusan_add",Utils.parseLong(id_subsuburusan_add),null,"onChange=\"doChangesSubSuburusanAdd()\" "));
			this.context.put("selectSubSubSuburusanAdd",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan_add",Utils.parseLong(id_subsubsuburusan_add),null,"onChange=\"doChangesSubSubSuburusanAdd()\" "));
			
			template = "app/utils/frmEditFailArkib.jsp";
			if (update(id_failarkib,id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan)) {
				this.context.put("result", "success");
				Hashtable h = getFailArkib(id_failarkib);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
			
		}
		else if ("delete".equals(submit)) {
			delete(id_failarkib);
			List lists = getSenarai(id_urusan,id_suburusan,id_subsuburusan,id_subsubsuburusan);
			setupPage(session, action, lists);
			template = "app/utils/frmFailArkib.jsp";
		}
		else if ("goBack".equals(submit)){
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", null, null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",null,null,null));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,null));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			template = "app/utils/frmFailArkib.jsp";
		}
		else{
			this.context.put("selectUrusan", HTML.SelectUrusanArkib("Form_id_urusan", null, null, "onChange=\"doChangesUrusan()\" "));
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",null,null,null));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,null));
			this.context.put("selectSubSubSuburusan",HTML.SelectSubSubSuburusan("Form_id_subsubsuburusan",null,null,null));
			template = "app/utils/frmFailArkib.jsp";
		}
		return template;
	}
	
	public List getSenarai(String id_urusan, String id_suburusan, String id_subsuburusan, String id_subsubsuburusan)
	throws Exception {
		
		Db db = null;
		List result = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			result = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAILARKIB, A.NO_FAIL_ARKIB, B.NAMA_URUSAN, C.NAMA_SUBURUSAN, D.NAMA_SUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN FROM TBLRUJFAILARKIB A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E WHERE A.ID_URUSAN = B.ID_URUSAN AND A.ID_SUBURUSAN = C.ID_SUBURUSAN AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN AND A.ID_URUSAN = "+id_urusan ;
			
			if (id_suburusan != null){
				sql += " AND A.ID_SUBURUSAN = "+id_suburusan;
			}
			if (id_subsuburusan != null){
				sql += " AND A.ID_SUBSUBURUSAN = "+id_subsuburusan;
			}
			if (id_subsubsuburusan != null){
				sql += " AND A.ID_SUBSUBSUBURUSAN = "+id_subsubsuburusan;
			}
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				h = new Hashtable();
				h.put("ID_FAILARKIB",checkIsNull(rs.getString("ID_FAILARKIB")));
				h.put("NO_FAIL_ARKIB",checkIsNull(rs.getString("NO_FAIL_ARKIB")));
				h.put("NAMA_URUSAN",checkIsNull(rs.getString("NAMA_URUSAN")));
				h.put("NAMA_SUBURUSAN",checkIsNull(rs.getString("NAMA_SUBURUSAN")));
				h.put("NAMA_SUBSUBURUSAN",checkIsNull(rs.getString("NAMA_SUBSUBURUSAN")));
				h.put("NAMA_SUBSUBSUBURUSAN",checkIsNull(rs.getString("NAMA_SUBSUBSUBURUSAN")));
				
				result.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
	}
	
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
	
	public void insert(String id_urusan,String id_suburusan,String id_subsuburusan,String id_subsubsuburusan) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_urusan",id_urusan);
			r.add("id_suburusan",id_suburusan);
			r.add("id_subsuburusan",id_subsuburusan);
			r.add("id_subsubsuburusan",id_subsubsuburusan);
			r.add("no_fail_arkib","JKPTG/"+getKodSubUrusan(id_suburusan)+"/"+getKodSubSubUrusan(id_subsuburusan)+"/"+getKodSubSubSubUrusan(id_subsubsuburusan));
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_masuk",user_id);
			sql = r.getSQLInsert("tblRujFailarkib");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
	}
	
	public String getKodUrusan (String id_urusan)throws Exception{
		Db db = null;
		String sql = "";
		String result = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT KOD_URUSAN FROM TBLRUJURUSAN WHERE ID_URUSAN = "+id_urusan ;
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				result = rs.getString("KOD_URUSAN");
	
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
		
	}
	
	public String getKodSubUrusan (String id_suburusan)throws Exception{
		Db db = null;
		String sql = "";
		String result = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT KOD_SUBURUSAN FROM TBLRUJSUBURUSAN WHERE ID_SUBURUSAN = "+id_suburusan ;
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				result = rs.getString("KOD_SUBURUSAN");
	
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
		
	}
	
	public String getKodSubSubUrusan (String id_subsuburusan)throws Exception{
		Db db = null;
		String sql = "";
		String result = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT KOD_SUBSUBURUSAN FROM TBLRUJSUBSUBURUSAN WHERE ID_SUBSUBURUSAN = "+id_subsuburusan ;
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				result = rs.getString("KOD_SUBSUBURUSAN");
	
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
		
	}
	
	public String getKodSubSubSubUrusan (String id_subsubsuburusan)throws Exception{
		Db db = null;
		String sql = "";
		String result = "";
		Hashtable h = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT KOD_SUBSUBSUBURUSAN FROM TBLRUJSUBSUBSUBURUSAN WHERE ID_SUBSUBSUBURUSAN = "+id_subsubsuburusan ;
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				result = rs.getString("KOD_SUBSUBSUBURUSAN");
	
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
		
	}
	
	public Hashtable getFailArkib(String id) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "SELECT ID_FAILARKIB, ID_URUSAN, ID_SUBURUSAN, ID_SUBSUBURUSAN, ID_SUBSUBSUBURUSAN FROM TBLRUJFAILARKIB WHERE ID_FAILARKIB='"+id+"' ";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("ID_FAILARKIB",checkIsNull(rs.getString("ID_FAILARKIB")));
				h.put("ID_URUSAN",checkIsNull(rs.getString("ID_URUSAN")));
				h.put("ID_SUBURUSAN",checkIsNull(rs.getString("ID_SUBURUSAN")));
				h.put("ID_SUBSUBURUSAN",checkIsNull(rs.getString("ID_SUBSUBURUSAN")));
				h.put("ID_SUBSUBSUBURUSAN",checkIsNull(rs.getString("ID_SUBSUBSUBURUSAN")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return h;
	}
	
	public boolean update(String id_failarkib,String id_urusan,String id_suburusan,String id_subsuburusan,String id_subsubsuburusan) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_failarkib", id_failarkib);
			r.add("id_urusan",id_urusan);
			r.add("id_suburusan",id_suburusan);
			r.add("id_subsuburusan",id_subsuburusan);
			r.add("id_subsubsuburusan",id_subsubsuburusan);
			r.add("id_kemaskini",user_id);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("tblRujFailarkib");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if (db != null) db.close();
		}
		return true;
	}
	
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM TBLRUJFAILARKIB WHERE ID_FAILARKIB='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	
}
