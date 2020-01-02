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

public class FrmDaerah extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmDaerah.class);
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String submit = getParam("command"); // First Level - default by
		String action = getParam("action"); // Second Level
		
		String template = "app/utils/frmDaerah.jsp";
		String id_negeri = getParam("Form_id_negeri");
		String id_daerah = getParam("idDaerah");
		String kod_daerah = getParam("Form_kod_daerah");
		String nama_daerah = getParam("Form_nama_daerah");
	
		context.put("mode","");
		this.context.put("result", "");
		this.context.put("SenaraiFail", "");
		
		if ("doChanges".equals(submit) || "goBack".equals(submit)) {
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
			List lists = getSenarai(id_negeri);
			setupPage(session, action, lists);
		}
		else if ("addNewDaerah".equals(submit)) {
			context.put("mode","add");
			template = "app/utils/frmEditDaerah.jsp";
			context.put("details","");
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,null));
		} else if ("doAddNew".equals(submit)) {
			insert(id_negeri,kod_daerah,nama_daerah);
			List lists = getSenarai(id_negeri);
			setupPage(session, action, lists);
		} else if ("editDaerah".equals(submit)) {
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
			context.put("mode","edit");
			template = "app/utils/frmEditDaerah.jsp";
			Hashtable h = getDaerahDetail(id_daerah);
			this.context.put("idDaerah",id_daerah);
			this.context.put("details",h);
		} else if ("doUpdate".equals(submit)) {
			this.context.put("id_negeri","<input type=hidden name=Form_id_negeri value="+id_negeri+">");
			template = "app/utils/frmEditDaerah.jsp";
			if (update(id_daerah,kod_daerah,nama_daerah)) {
				this.context.put("result", "success");
				Hashtable h = getDaerahDetail(id_daerah);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
			
		}
		else if ("delete".equals(submit)) {
			delete(id_daerah);
			List lists = getSenarai(id_negeri);
			setupPage(session, action, lists);
		}
		else {
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri","onChange=\"doChanges()\" "));
		}
		return template;
	}

	public void insert(String id_negeri,String kod_daerah,String nama_daerah) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_negeri",id_negeri);
			r.add("kod_daerah",kod_daerah);
			r.add("nama_daerah",nama_daerah);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLInsert("tblRujDaerah");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
	}

	
	public boolean update(String id_daerah,String kod_daerah,String nama_daerah) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_daerah", id_daerah);
			r.add("kod_daerah",kod_daerah);
			r.add("nama_daerah",nama_daerah);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("tblRujDaerah");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			if (db != null) db.close();
		}
		return true;
	}
//	public boolean update(String id_negeri,String kod_daerah,String nama_daerah) throws Exception {
//		Db db = null;
//		String sql = "";
//		try {
//			db = new Db();
//			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
//			r.update("id_negeri", id_negeri);
//			r.add("kod_daerah",kod_daerah);
//			r.add("nama_daerah",nama_daerah);
//			r.add("tarikh_masuk",r.unquote("SYSDATE"));
//			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
//			sql = r.getSQLUpdate("tblRujDaerah");
//			stmt.executeUpdate(sql);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}finally {
//			if (db != null) db.close();
//		}
//		return true;
//	}
	
	public List getSenarai(String id_negeri)
	throws Exception {
		
		Db db = null;
		List result = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			result = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "select id_daerah,kod_daerah,nama_daerah from tblRujDaerah where id_negeri='"+id_negeri+"' order by kod_daerah" ;
			//myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_daerah",checkIsNull(rs.getString("id_daerah")));
				h.put("kod_daerah",checkIsNull(rs.getString("kod_daerah")));
				h.put("nama_daerah",checkIsNull(rs.getString("nama_daerah")));
				result.add(h);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		
		return result;
		
	}
	
	public boolean delete(String id) {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = "DELETE FROM tblRujDaerah WHERE id_daerah='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}

	public Hashtable getDaerahDetail(String id) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "Select id_daerah,kod_daerah,nama_daerah from tblRujDaerah where id_daerah='"+id+"' ";
			myLogger.debug(sql);
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("id_daerah",checkIsNull(rs.getString("id_daerah")));
				h.put("kod_daerah",checkIsNull(rs.getString("kod_daerah")));
				h.put("nama_daerah",checkIsNull(rs.getString("nama_daerah")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return h;
	}	
	
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
}
