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

public class FrmSubSuburusan extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmSuburusan.class);
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		String submit = getParam("command"); // First Level - default by
		String action = getParam("action"); // Second Level
		
		
		String template = "app/utils/frmSubSuburusan.jsp";
		String id_suburusan= getParam("Form_id_suburusan");
		String id_subsuburusan = getParam("idSubSuburusan");
		String kod_subsuburusan= getParam("Form_kod_subsuburusan");
		String nama_subsuburusan = getParam("Form_nama_subsuburusan");
		context.put("mode","");
		this.context.put("result", "");
		this.context.put("SenaraiFail", "");
	
		
		if ("doChanges".equals(submit) || "goBack".equals(submit)) {
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",Utils.parseLong(id_suburusan),null,"onChange=\"doChanges()\" "));
			List lists = getSenarai(id_suburusan);
			setupPage(session, action, lists);
		}else if ("addNewSubSuburusan".equals(submit)) {
			context.put("mode","add");
			template = "app/utils/frmEditSubSuburusan.jsp";
			context.put("details","");
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",Utils.parseLong(id_suburusan),null,null));
		}else if ("doAddNew".equals(submit)) {
			insert(id_subsuburusan,kod_subsuburusan,nama_subsuburusan,id_suburusan);
			List lists = getSenarai(id_suburusan);
			setupPage(session, action, lists);
		} else if ("editSubSuburusan".equals(submit)) {
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",Utils.parseLong(id_suburusan),null,"onChange=\"doChanges()\" "));
			
			context.put("mode","edit");
			template = "app/utils/frmEditSubSuburusan.jsp";
			Hashtable h = getSubSuburusanDetail(id_subsuburusan);
			this.context.put("idSubSuburusan",id_subsuburusan);
			this.context.put("details",h);
		} else if ("doUpdate".equals(submit)) {
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",Utils.parseLong(id_suburusan),null,"onChange=\"doChanges()\" "));
			this.context.put("id_suburusan","<input type=hidden name=Form_id_suburusan value="+id_suburusan+">");
			
			template = "app/utils/frmEditSubSuburusan.jsp";
			if (update(id_subsuburusan,kod_subsuburusan,nama_subsuburusan,id_suburusan)) {
				this.context.put("result", "success");
				Hashtable h = getSubSuburusanDetail(id_suburusan);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
			
		}
		else if ("delete".equals(submit)) {
			delete(id_subsuburusan);
			List lists = getSenarai(id_suburusan);
			setupPage(session, action, lists);
		}
		else {
//			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
			this.context.put("selectSuburusan", HTML.SelectSubUrusan1("Form_id_suburusan",null,null,"onChange=\"doChanges()\" "));
		}
		return template;
	}
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM tblRujSubSuburusan WHERE id_subsuburusan='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	public boolean update(String id_subsuburusan,String kod_subsuburusan,String nama_subsuburusan,String id_suburusan) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_subsuburusan", id_subsuburusan);
			r.add("kod_subsuburusan",kod_subsuburusan);
			r.add("nama_subsuburusan",nama_subsuburusan);
			r.add("id_suburusan",id_suburusan);
			r.add("id_kemaskini",user_id);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("tblRujSubSuburusan");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if (db != null) db.close();
		}
		return true;
	}
	public Hashtable getSubSuburusanDetail(String id) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "Select id_subsuburusan,kod_subsuburusan,nama_subsuburusan,id_suburusan from tblRujSubSuburusan where id_subsuburusan='"+id+"' ";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("id_subsuburusan",checkIsNull(rs.getString("id_subsuburusan")));
				h.put("kod_subsuburusan",checkIsNull(rs.getString("kod_subsuburusan")));
				h.put("nama_subsuburusan",checkIsNull(rs.getString("nama_subsuburusan")));
				h.put("id_suburusan",checkIsNull(rs.getString("id_suburusan")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return h;
	}	
	public void insert(String id_subsuburusan,String kod_subsuburusan,String nama_subsuburusan,String id_suburusan) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_subsuburusan",id_subsuburusan);
			r.add("kod_subsuburusan",kod_subsuburusan);
			r.add("nama_subsuburusan",nama_subsuburusan);
			r.add("id_suburusan",id_suburusan);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_masuk",user_id);
			sql = r.getSQLInsert("tblRujSubSuburusan");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
	}

public List getSenarai(String id_suburusan)
throws Exception {
	
	Db db = null;
	List result = null;
	String sql = "";
	Hashtable h = null;
	try {
		db = new Db();
		result = new ArrayList();
		Statement stmt = db.getStatement();
		sql = "select a.id_subsuburusan,a.kod_subsuburusan,a.nama_subsuburusan, b.nama_suburusan from tblRujSubSuburusan a, tblRujSuburusan b where b.id_suburusan='"+id_suburusan+"' and kod_subsuburusan not in ('00') and a.id_suburusan = b.id_suburusan order by a.kod_subsuburusan" ;
		myLogger.debug(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			h = new Hashtable();
			h.put("id_subsuburusan",checkIsNull(rs.getString("id_subsuburusan")));
			h.put("kod_subsuburusan",checkIsNull(rs.getString("kod_subsuburusan")));
			h.put("nama_subsuburusan",checkIsNull(rs.getString("nama_subsuburusan")));
			h.put("nama_suburusan",checkIsNull(rs.getString("nama_suburusan")));
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
