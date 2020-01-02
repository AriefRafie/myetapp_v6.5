package ekptg.view.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmPejabatUrusan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmPejabatUrusan.class);
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String submit = getParam("command"); // First Level - default by
		myLogger.debug("submit:"+submit);
		// AjaxBasedModule Call
		String action = getParam("action"); // Second Level
		
		String template = "app/utils/frmPejabatUrusan.jsp";
		String id_jenispejabat = getParam("Form_id_jenispejabat");
		String idPejabat = getParam("idPejabat");
		String id_negeri = getParam("Form_id_negeri");
		String id_daerah = getParam("Form_id_daerah");
		String id_seksyen = getParam("Form_id_seksyen");
		
		this.context.put("doCarian","false");
		this.context.put("result","");
		
		this.context.put("id_jenispejabat",id_jenispejabat);
		if ("doChanges".equals(submit) || "Carian".equals(submit)) {
			doContext(session,action,id_jenispejabat,id_negeri,id_daerah,id_seksyen);
		} else if ("goBack".equals(submit)) {
			doContext(session,action,id_jenispejabat,id_negeri,null,null);
		}
		else if ("editPejabatUrusan".equals(submit)) {
			template = "app/utils/frmEditPejabatUrusan.jsp";
			context.put("mode","edit");
			Hashtable h = getPejabatUrusanDetail(idPejabat);
			this.context.put("idPejabat",idPejabat);
			this.context.put("id_jenispejabat",id_jenispejabat);
			id_negeri = (String)h.get("id_negeri");
			id_daerah = (String)h.get("id_daerah");
			id_seksyen = (String)h.get("id_seksyen");
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen), null, "onChange=\"doChanges()\" "));
			this.context.put("selectDaerah", 
					HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
					.parseLong(id_daerah), null, null));
			context.put("details",h);
		}
		else if ("doUpdate".equals(submit)) {
			template = "app/utils/frmEditPejabatUrusan.jsp";
			Hashtable parameters = new Hashtable();
			setParameterValues(parameters);
			if (update(parameters,idPejabat)) {
				this.context.put("result", "success");
				Hashtable h = getPejabatUrusanDetail(idPejabat);
				this.context.put("details",h);
			} else
				this.context.put("result", "failed");
		}
		else if ("addNewPejabat".equals(submit)) {
			context.put("mode","add");
			template = "app/utils/frmEditPejabatUrusan.jsp";
			context.put("details","");
			this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,null));
			this.context.put("selectPejabat",getPejabatUrusanLists(id_jenispejabat));
			this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen), null, null));

			//initSelectList();
		}
		else if ("doAddNew".equals(submit)) {
			Hashtable parameters = new Hashtable();
			setParameterValues(parameters);
			insert(parameters);
			doContext(session,action,id_jenispejabat,id_negeri,id_daerah,id_seksyen);
		}
		else if ("deletePejabat".equals(submit)) {
			delete(idPejabat);
			doContext(session,action,id_jenispejabat,id_negeri,id_daerah,id_seksyen);
		}
		else {
			initSelectList();
		}
		return template;
	}
	
	public void initSelectList() throws Exception {
		this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",null, null, "onChange=\"doChanges()\" "));
		this.context.put("selectPejabat",getPejabatUrusanLists(""));
		this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri","onChange=\"doChanges()\" "));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri("0","Form_id_daerah", null, null, "onChange=\"doChanges()\" "));
		context.put("details","");
		context.put("SenaraiFail", "");
	}
	
	public void doContext(HttpSession session,String action,
			String id_jenispejabat,String id_negeri,String id_daerah,String id_seksyen) throws Exception {
		this.context.put("selectSeksyen", HTML.SelectSeksyen("Form_id_seksyen",Utils.parseLong(id_seksyen), null, "onChange=\"doChanges()\" "));
		this.context.put("selectNegeri", HTML.SelectNegeri("Form_id_negeri",Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
		this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri == "" ? "0" : id_negeri, "Form_id_daerah", Utils
						.parseLong(id_daerah), null, null));
		this.context.put("selectPejabat",getPejabatUrusanLists(id_jenispejabat));
		List lists = getSenarai(id_jenispejabat,id_negeri,id_daerah);
		//this.context.put("SenaraiFail", lists);
		setupPage(session, action, lists);
	}
	
	public String getPejabatUrusanLists(String idjenispejabat) throws Exception {
		StringBuffer sb = new StringBuffer("");
		String sql="";
		Db db = null;
		sb.append("<option value=>SILA PILIH PEJABAT URUSAN</option>");
		try {
			db = new Db();
			sql = "Select id_jenispejabat,kod_jenis_pejabat,keterangan " +
					"from tblRujJenisPejabat where kod_jenis_pejabat not like 'S%' " +
					"and id_jenispejabat not in (0)";
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String rs_idjenispejabat = "";
			while (rs.next()) {
				rs_idjenispejabat = rs.getString("id_jenispejabat");
				if (rs_idjenispejabat.equals(idjenispejabat)) {
					sb.append("<option selected value="+rs_idjenispejabat+">");
				} else {
					sb.append("<option value="+rs_idjenispejabat+">");
				}
				sb.append(rs.getString(2)+" - "+rs.getString(3));
				sb.append("</option>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return sb.toString();
	}	
	
	public Hashtable getPejabatUrusanDetail(String idpejabat) throws Exception {
		Hashtable h = null;
		String sql="";
		Db db = null;
		try {
			db = new Db();
			sql = "Select nama_pejabat,alamat1,alamat2,alamat3,poskod," +
					"no_tel,no_fax,id_negeri,id_daerah,id_seksyen " +
					"from tblRujPejabat where id_pejabat='"+idpejabat+"' ";
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			h = new Hashtable();
			if (rs.next()) {
				h.put("nama_pejabat",checkIsNull(rs.getString("nama_pejabat")));
				h.put("alamat1",checkIsNull(rs.getString("alamat1")));
				h.put("alamat2",checkIsNull(rs.getString("alamat2")));
				h.put("alamat3",checkIsNull(rs.getString("alamat3")));
				h.put("poskod",checkIsNull(rs.getString("poskod")));
				h.put("no_tel",checkIsNull(rs.getString("no_tel")));
				h.put("no_fax",checkIsNull(rs.getString("no_fax")));
				h.put("id_negeri",checkIsNull(rs.getString("id_negeri")));
				h.put("id_daerah",checkIsNull(rs.getString("id_daerah")));
				h.put("id_seksyen",checkIsNull(rs.getString("id_seksyen")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null) db.close();
		}
		return h;
	}	
	
	public List getSenarai(String id_jenispejabat,String id_negeri,String id_daerah)
	throws Exception {
		
		Db db = null;
		List result = null;
		String sql = "";
		Hashtable h = null;
		try {
			db = new Db();
			result = new ArrayList();
			Statement stmt = db.getStatement();
			sql = "select A.id_pejabat,A.nama_pejabat," +
					"(select nama_negeri from tblRujNegeri where id_negeri=A.id_negeri) negeri, " +
					"(select nama_daerah from tblRujDaerah where id_daerah=A.id_daerah) daerah "+
					"from tblRujPejabat A where A.id_jenispejabat = '"+id_jenispejabat+"' " ;
			if (id_negeri != null && !"".equals(id_negeri)) {
				sql += " AND A.id_negeri='"+id_negeri+"' ";
			}
			if (id_daerah != null && !"".equals(id_daerah)) {
				sql += " AND A.id_daerah='"+id_daerah+"' ";
			}
			sql += "ORDER BY A.id_negeri,A.id_daerah";
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				h = new Hashtable();
				h.put("id_pejabat",checkIsNull(rs.getString("id_pejabat")));
				h.put("nama_pejabat",checkIsNull(rs.getString("nama_pejabat")));
				h.put("negeri",checkIsNull(rs.getString("negeri")));
				h.put("daerah",checkIsNull(rs.getString("daerah")));
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
	
	public void setParameterValues(Hashtable parameters) {
		String name = "";
		String value = "";
		Enumeration allparam = request.getParameterNames();
		for (; allparam.hasMoreElements();) {
			name = (String) allparam.nextElement();
			if (name.indexOf("Form_") != -1) { // get only parameters with name
				// like Form_
				value = request.getParameter(name);
				parameters.put(name, value);
			}
		}
	}

	  public boolean update(Hashtable parameters,String id) {
		  String sql="";
		  String name="";
		  String value="";
		  if (id != null) {
			  sql = "Update tblRujPejabat SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE ID_PEJABAT = '" +id+ "' ";
			  myLogger.info(sql);
			  Db db = null;
			  try {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  stmt.executeUpdate(sql);
	
			  } catch (Exception e) { 
				  e.printStackTrace();
				  return false;
			  }
			  finally {
				  if (db != null) db.close();
			  }
		  }
		  return true;
	  }
	
	  public boolean insert(Hashtable parameters) {
		  String sql = "";
		  String name = "";
		  String value ="";
		  Db db = null;
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }
			  r.add("tarikh_masuk",r.unquote("SYSDATE"));
			  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			  sql = r.getSQLInsert("tblRujPejabat");
			  myLogger.info(sql);
			  stmt.executeUpdate(sql);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return false;
		  }
		  finally {
			  if (db != null) db.close();
		  }
		  
		  return true;
	}
	  
		public boolean delete(String id) {
			Db db = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				String sql = "DELETE FROM tblRujPejabat WHERE ID_PEJABAT='"+id+"' ";
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
