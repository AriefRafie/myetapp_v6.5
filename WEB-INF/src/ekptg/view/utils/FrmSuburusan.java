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

public class FrmSuburusan extends AjaxBasedModule{
	
	

	static Logger myLogger = Logger.getLogger(FrmSuburusan.class);
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		
		String submit = getParam("command"); // First Level - default by
		String action = getParam("action"); // Second Level
		
		
		String template = "app/utils/frmSuburusan.jsp";
		String id_urusan= getParam("Form_id_urusan");
		String id_seksyen= getParam("Form_id_seksyen");
		String id_suburusan = getParam("idSuburusan");
		String kod_suburusan= getParam("Form_kod_suburusan");
		String nama_suburusan = getParam("Form_nama_suburusan");
		context.put("mode","");
		this.context.put("result", "");
		this.context.put("SenaraiFail", "");
	
		
		if ("doChanges".equals(submit) || "goBack".equals(submit)) {
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan",Utils.parseLong(id_urusan),null,"onChange=\"doChanges()\" "));
			myLogger.info("Id_urusan:"+id_urusan);
			List lists = getSenarai(id_urusan,id_seksyen);
			setupPage(session, action, lists);
		}else if ("addNewSuburusan".equals(submit)) {
			context.put("mode","add");
			template = "app/utils/frmEditSuburusan.jsp";
			context.put("details","");
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen),null,null));
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan",Utils.parseLong(id_urusan),null,null));
		}else if ("doAddNew".equals(submit)) {
			insert(id_urusan,kod_suburusan,nama_suburusan,id_seksyen);
			List lists = getSenarai(id_urusan,id_seksyen);
			setupPage(session, action, lists);
		} else if ("editSuburusan".equals(submit)) {
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan",Utils.parseLong(id_urusan),null,"onChange=\"doChanges()\" "));
			
			context.put("mode","edit");
			template = "app/utils/frmEditSuburusan.jsp";
			Hashtable h = getSuburusanDetail(id_suburusan);
			this.context.put("idSuburusan",id_suburusan);
			this.context.put("details",h);
		} else if ("doUpdate".equals(submit)) {
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
			this.context.put("id_seksyen","<input type=hidden name=Form_id_seksyen value="+id_seksyen+">");
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan",Utils.parseLong(id_urusan),null,"onChange=\"doChanges()\" "));
			this.context.put("id_urusan","<input type=hidden name=Form_id_urusan value="+id_urusan+">");
			
			template = "app/utils/frmEditSuburusan.jsp";
			if (update(id_suburusan,kod_suburusan,nama_suburusan,id_seksyen)) {
				this.context.put("result", "success");
				Hashtable h = getSuburusanDetail(id_suburusan);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
			
		}
		else if ("delete".equals(submit)) {
			delete(id_suburusan);
			List lists = getSenarai(id_urusan,id_seksyen);
			setupPage(session, action, lists);
		}
		else {
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",null,null,null));
			this.context.put("selectUrusan", HTML.SelectUrusan("Form_id_urusan",null,null,"onChange=\"doChanges()\" "));
		}
		return template;
	}
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM tblRujSuburusan WHERE id_suburusan='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}
	public boolean update(String id_suburusan,String kod_suburusan,String nama_suburusan,String id_seksyen) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_suburusan", id_suburusan);
			r.add("kod_suburusan",kod_suburusan);
			r.add("nama_suburusan",nama_suburusan);
			r.add("id_seksyen",id_seksyen);
			r.add("id_kemaskini",user_id);
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("tblRujSuburusan");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if (db != null) db.close();
		}
		return true;
	}
	public Hashtable getSuburusanDetail(String id) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "Select id_suburusan,kod_suburusan,nama_suburusan,id_seksyen from tblRujSuburusan where id_suburusan='"+id+"' ";
			
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("id_suburusan",checkIsNull(rs.getString("id_suburusan")));
				h.put("kod_suburusan",checkIsNull(rs.getString("kod_suburusan")));
				h.put("nama_suburusan",checkIsNull(rs.getString("nama_suburusan")));
				h.put("id_seksyen",checkIsNull(rs.getString("id_seksyen")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return h;
	}	
	public void insert(String id_urusan,String kod_suburusan,String nama_suburusan,String id_seksyen) throws Exception {
		Db db = null;
		String sql = "";
		HttpSession session = this.request.getSession();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_urusan",id_urusan);
			r.add("kod_suburusan",kod_suburusan);
			r.add("nama_suburusan",nama_suburusan);
			r.add("id_seksyen",id_seksyen);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("id_masuk",user_id);
			sql = r.getSQLInsert("tblRujSuburusan");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
	}

public List getSenarai(String id_urusan, String id_seksyen)
throws Exception {
	
	Db db = null;
	List result = null;
	String sql = "";
	Hashtable h = null;
	try {
		db = new Db();
		result = new ArrayList();
		Statement stmt = db.getStatement();
		sql = "select a.id_suburusan,a.kod_suburusan,a.nama_suburusan, b.nama_seksyen from tblRujSuburusan a, tblRujSeksyen b where id_urusan='"+id_urusan+"' and kod_suburusan not in ('00') and a.id_seksyen = b.id_seksyen and a.id_seksyen ='"+id_seksyen+"' order by a.kod_suburusan" ;
		myLogger.debug(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			h = new Hashtable();
			h.put("id_suburusan",checkIsNull(rs.getString("id_suburusan")));
			h.put("kod_suburusan",checkIsNull(rs.getString("kod_suburusan")));
			h.put("nama_suburusan",checkIsNull(rs.getString("nama_suburusan")));
			h.put("nama_seksyen",checkIsNull(rs.getString("nama_seksyen")));
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
