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

public class FrmMukim extends 
//AjaxModule {
AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmMukim.class);
	
//	public String doAction() throws Exception {
//		HttpSession session = this.request.getSession();
//		String submit = getParam("command"); // First Level - default by
//		String action = getParam("action"); // Second Level
//		
//		String template = "app/utils/frmMukim.jsp";
//		String id_negeri = getParam("Form_id_negeri");
//		String id_daerah = getParam("Form_id_daerah");
//		String id_mukim = getParam("idMukim");
//		String kod_mukim = getParam("Form_kod_mukim");
//		String nama_mukim = getParam("Form_nama_mukim");
//		
//		context.put("mode","");
//		this.context.put("result", "");
//		
//		if ("doChanges".equals(submit) || "goBack".equals(submit)
//			|| "Carian".equals(submit) ) {
//		this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
//		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
//				.parseLong(id_daerah), null,"onChange=\"doChanges()\" "));
//		List lists = getSenarai(id_daerah);
//		setupPage(session, action, lists);
//		}
//		else if ("addNewMukim".equals(submit)) {
//		context.put("mode","add");
//		template = "app/utils/frmEditMukim.jsp";
//		context.put("details","");
//		this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,null));
//		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
//				.parseLong(id_daerah), null,"onChange=\"doChanges()\" "));
//		} else if ("doAddNew".equals(submit)) {
//			insert(id_daerah,kod_mukim,nama_mukim);
//			List lists = getSenarai(id_daerah);
//			setupPage(session, action, lists);
//		} else if ("editMukim".equals(submit)) {
//		this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
//		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
//				.parseLong(id_daerah), null,"onChange=\"doChanges()\" "));
//		context.put("mode","edit");
//		template = "app/utils/frmEditMukim.jsp";
//		Hashtable h = getMukimDetail(id_mukim);
//		this.context.put("idMukim",id_mukim);
//		this.context.put("details",h);
//		} else if ("doUpdate".equals(submit)) {
//		template = "app/utils/frmEditMukim.jsp";
//		if (update(id_mukim,kod_mukim,nama_mukim)) {
//			this.context.put("result", "success");
//			Hashtable h = getMukimDetail(id_mukim);
//			this.context.put("details",h);
//		} else
//			this.context.put("result", "failed");
//	}
//	else if ("delete".equals(submit)) {
//		delete(id_mukim);
//		List lists = getSenarai(id_daerah);
//		setupPage(session, action, lists);
//	}
//	else {
//		this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri","onChange=\"doChanges()\" "));
//		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("0","Form_id_daerah", null, null, "onChange=\"doChanges()\" "));
//	
//	}		
//		return template;
//	}
	
//	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String submit = getParam("command"); // First Level - default by
		String action = getParam("action"); // Second Level
		
		String template = "app/utils/frmMukim.jsp";
		String id_negeri = getParam("Form_id_negeri");
		String id_daerah = getParam("Form_id_daerah");
		String id_mukim = getParam("idMukim");
		String kod_mukim = getParam("Form_kod_mukim");
		String nama_mukim = getParam("Form_nama_mukim");

		context.put("mode","");
		this.context.put("result", "");
		this.context.put("SenaraiFail", "");
		
		if ("doChanges".equals(submit) || "goBack".equals(submit)
				|| "Carian".equals(submit) ) {
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
					.parseLong(id_daerah), null,"onChange=\"doChanges()\" "));
			List lists = getSenarai(id_daerah);
			setupPage(session, action, lists);
		}
		else if ("addNewMukim".equals(submit)) {
			context.put("mode","add");
			template = "app/utils/frmEditMukim.jsp";
			context.put("details","");
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,null));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
					.parseLong(id_daerah), null,"onChange=\"doChanges()\" "));
		} else if ("doAddNew".equals(submit)) {
			insert(id_daerah,kod_mukim,nama_mukim);
			List lists = getSenarai(id_daerah);
			setupPage(session, action, lists);
		} else if ("editMukim".equals(submit)) {
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
			context.put("mode","edit");
			template = "app/utils/frmEditMukim.jsp";
			Hashtable h = getMukimDetail(id_mukim);
			this.context.put("idMukim",id_mukim);
			this.context.put("details",h);
		} else if ("doUpdate".equals(submit)) {
			template = "app/utils/frmEditMukim.jsp";
			if (update(id_mukim,kod_mukim,nama_mukim)) {
				this.context.put("result", "success");
				Hashtable h = getMukimDetail(id_mukim);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
			
		}
		else if ("delete".equals(submit)) {
			delete(id_mukim);
			List lists = getSenarai(id_daerah);
			setupPage(session, action, lists);
		}
		else {
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri","onChange=\"doChanges()\" "));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("0","Form_id_daerah", null, null, "onChange=\"doChanges()\" "));

		}
		return template;
	}

	public void insert(String id_daerah,String kod_mukim,String nama_mukim) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_daerah",id_daerah);
			r.add("kod_mukim",kod_mukim);
			r.add("nama_mukim",nama_mukim);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLInsert("tblRujMukim");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
	}
	
	public boolean update(String id_mukim,String kod_mukim,String nama_mukim) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("id_mukim", id_mukim);
			r.add("kod_mukim",kod_mukim);
			r.add("nama_mukim",nama_mukim);
			r.add("tarikh_masuk",r.unquote("SYSDATE"));
			r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("tblRujMukim");
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
		return true;
	}
	
	public List getSenarai(String id_daerah)
	throws Exception {
		
		Db db = null;
		List result = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			result = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "select id_mukim,kod_mukim,nama_mukim from tblRujMukim where id_daerah='"+id_daerah+"' order by kod_mukim" ;
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_mukim",checkIsNull(rs.getString("id_mukim")));
				h.put("kod_mukim",checkIsNull(rs.getString("kod_mukim")));
				h.put("nama_mukim",checkIsNull(rs.getString("nama_mukim")));
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
			String sql = "DELETE FROM tblRujMukim WHERE id_mukim='"+id+"' ";
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (db != null) db.close();
		}
	}

	public Hashtable getMukimDetail(String id) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "Select id_mukim,kod_mukim,nama_mukim from tblRujMukim where id_mukim='"+id+"' ";
			//myLogger.debug(sql);
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("id_mukim",checkIsNull(rs.getString("id_mukim")));
				h.put("kod_mukim",checkIsNull(rs.getString("kod_mukim")));
				h.put("nama_mukim",checkIsNull(rs.getString("nama_mukim")));
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
