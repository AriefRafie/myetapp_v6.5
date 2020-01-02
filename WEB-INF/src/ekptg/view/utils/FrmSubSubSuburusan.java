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

public class FrmSubSubSuburusan  extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmSuburusan.class);
	public String doTemplate2() throws Exception {
		
HttpSession session = this.request.getSession();
		
		String submit = getParam("command"); // First Level - default by
		String action = getParam("action"); // Second Level
		
		myLogger.debug("submit"+submit);
		String template = "app/utils/frmSubSubSuburusan.jsp";
		String id_subsuburusan= getParam("Form_id_subsuburusan");
		String id_subsubsuburusan = getParam("idSubSubSuburusan");
		String kod_subsubsuburusan= getParam("Form_kod_subsubsuburusan");
		String nama_subsubsuburusan = getParam("Form_nama_subsubsuburusan");
		context.put("mode","");
		this.context.put("result", "");
		this.context.put("SenaraiFail", "");
	
		if ("doChanges".equals(submit) || "goBack".equals(submit)) {
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",Utils.parseLong(id_subsuburusan),null,"onChange=\"doChanges()\" "));
			List lists = getSenarai(id_subsuburusan);
			setupPage(session, action, lists);
		}else if ("addNewSubSubSuburusan".equals(submit)) {
			context.put("mode","add");
			template = "app/utils/frmEditSubSubSuburusan.jsp";
			context.put("details","");
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",Utils.parseLong(id_subsuburusan),null,null));
		}else if ("doAddNew".equals(submit)) {
			insert(id_subsubsuburusan,kod_subsubsuburusan,nama_subsubsuburusan,id_subsuburusan);
			List lists = getSenarai(id_subsuburusan);
			setupPage(session, action, lists);
			template = "app/utils/frmEditSubSubSuburusan.jsp";
		} else if ("editSubSubSuburusan".equals(submit)) {
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",Utils.parseLong(id_subsuburusan),null,null));
			
			context.put("mode","edit");
			template = "app/utils/frmEditSubSubSuburusan.jsp";
			Hashtable h = getSubSubSuburusanDetail(id_subsubsuburusan);
			this.context.put("idSubSubSuburusan",id_subsubsuburusan);
			this.context.put("details",h);
		} else if ("doUpdate".equals(submit)) {
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",Utils.parseLong(id_subsuburusan),null,"onChange=\"doChanges()\" "));
			this.context.put("id_subsuburusan","<input type=hidden name=Form_id_subsuburusan value="+id_subsuburusan+">");
			
			template = "app/utils/frmEditSubSubSuburusan.jsp";
			if (update(id_subsubsuburusan,kod_subsubsuburusan,nama_subsubsuburusan,id_subsuburusan)) {
				this.context.put("result", "success");
				Hashtable h = getSubSubSuburusanDetail(id_subsubsuburusan);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
			
		}
		else if ("delete".equals(submit)) {
			delete(id_subsubsuburusan);
			List lists = getSenarai(id_subsuburusan);
			setupPage(session, action, lists);
		}
		else {
//			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
			this.context.put("selectSubSuburusan", HTML.SelectSubSubUrusan("Form_id_subsuburusan",null,null,"onChange=\"doChanges()\" "));
		}
		return template;
	}
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM tblRujSubSubSuburusan WHERE id_subsubsuburusan='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	public boolean update(String id_subsubsuburusan,String kod_subsubsuburusan,String nama_subsubsuburusan,String id_subsuburusan) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_subsubsuburusan", id_subsubsuburusan);
			r.add("kod_subsubsuburusan",kod_subsubsuburusan);
			r.add("nama_subsubsuburusan",nama_subsubsuburusan);
			r.add("id_subsuburusan",id_subsuburusan);
			r.add("id_kemaskini",user_id);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("tblRujSubSubSuburusan");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if (db != null) db.close();
		}
		return true;
	}
	public Hashtable getSubSubSuburusanDetail(String id) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "Select id_subsubsuburusan,kod_subsubsuburusan,nama_subsubsuburusan,id_subsuburusan from tblRujSubSubSuburusan where id_subsubsuburusan='"+id+"' ";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("id_subsubsuburusan",checkIsNull(rs.getString("id_subsubsuburusan")));
				h.put("kod_subsubsuburusan",checkIsNull(rs.getString("kod_subsubsuburusan")));
				h.put("nama_subsubsuburusan",checkIsNull(rs.getString("nama_subsubsuburusan")));
				h.put("id_subsuburusan",checkIsNull(rs.getString("id_subsuburusan")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return h;
	}	
	public void insert(String id_subsubsuburusan,String kod_subsubsuburusan,String nama_subsubsuburusan,String id_subsuburusan) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_subsubsuburusan",id_subsubsuburusan);
			r.add("kod_subsubsuburusan",kod_subsubsuburusan);
			r.add("nama_subsubsuburusan",nama_subsubsuburusan);
			r.add("id_subsuburusan",id_subsuburusan);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_masuk",user_id);
			sql = r.getSQLInsert("tblRujSubSubSuburusan");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
	}

public List getSenarai(String id_subsuburusan)
throws Exception {
	
	Db db = null;
	List result = null;
	String sql = "";
	Hashtable h = null;
	try {
		db = new Db();
		result = new ArrayList();
		Statement stmt = db.getStatement();
		sql = "select a.id_subsubsuburusan,a.kod_subsubsuburusan,a.nama_subsubsuburusan, b.nama_subsuburusan from tblRujSubSubSuburusan a, tblRujSubSuburusan b where b.id_subsuburusan='"+id_subsuburusan+"' and kod_subsubsuburusan not in ('00') and a.id_subsuburusan = b.id_subsuburusan order by a.kod_subsubsuburusan" ;
		myLogger.debug(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			h = new Hashtable();
			h.put("id_subsubsuburusan",checkIsNull(rs.getString("id_subsubsuburusan")));
			h.put("kod_subsubsuburusan",checkIsNull(rs.getString("kod_subsubsuburusan")));
			h.put("nama_subsubsuburusan",checkIsNull(rs.getString("nama_subsubsuburusan")));
			h.put("nama_subsuburusan",checkIsNull(rs.getString("nama_subsuburusan")));
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
}
