package ekptg.view.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.app.RoleProcessor;
import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import lebah.portal.db.RegisterUser;
import lebah.util.PasswordService;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class RegisterUserIntegrasi extends AjaxBasedModule {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hashtable<?, ?> conProp = new Hashtable<Object, Object>();
	static Logger myLogger = Logger.getLogger(RegisterUserIntegrasi.class);
	
	
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		doJob(session);
		String template = "vtl/admin/registeruser_INT.jsp";
		return template;
	}

  	private void doJob(HttpSession session) throws Exception {
	  	String submit = getParam("command");

		String id_jenisjabatan = getParam("id_jenisjabatan");
		String user_login = getParam("user_login");
		String user_password = getParam("user_password");
		String user_name = getParam("user_name");
		String user_email = getParam("user_email");
		String id_jawatan = getParam("id_jawatan");
		String id_negeri = getParam("id_negeri");
		String id_daerah = getParam("id_daerah");
		String id_mukim = getParam("id_mukim");
		String id_pejabat = getParam("id_pejabat");
		String user_role = getParam("user_role");
		
		this.context.put("submit", submit);
		
		this.context.put("id_jenisjabatan", id_jenisjabatan);
		this.context.put("user_login", user_login);
		this.context.put("user_password", user_password);
		this.context.put("user_name", user_name);
		this.context.put("user_email", user_email);
		this.context.put("id_jawatan", id_jawatan);
		this.context.put("id_negeri", id_negeri);
		this.context.put("id_daerah", id_daerah);
		this.context.put("id_mukim", id_mukim);
		this.context.put("id_pejabat", id_pejabat);
		this.context.put("user_role", user_role);
		
	    String id_masuk = (String) session.getAttribute("_ekptg_user_id");
	    String doPost = (String) session.getAttribute("doPost");
	
	    if (doPost == "false") {
	    	myLogger.debug("browser refresh **********");
	    	submit = ""; //do what ever approriate action
	    }
	
	    if (!("".equals(submit))) {
		    if ("add".equals(submit)) {
		    	if (this.add(id_jenisjabatan, user_login, user_password, user_name, user_email, id_jawatan, id_negeri, id_daerah, id_mukim, id_pejabat, 
		    		user_role, id_masuk)) {
		    		AuditTrail.logActivity(this, session, "INS", "USER INTEGRASI [" + user_name + "] Added");
		    		this.context.put("registerUserStatus", "success");
		        } else {
		        	this.context.put("registerUserStatus", "failed");		        	
		        }
		    } else if ("doChanges".equals(submit)) {
		    	this.context.put("registerUserStatus", "none");
		    }
	    }
    	this.context.put("selectJenisJabatan", this.SelectJenisPejabat("id_jenisjabatan", id_jenisjabatan, "onChange=\"doChanges()\" "));
	    this.context.put("selectJawatan", this.SelectJawatan("id_jawatan", id_jenisjabatan, id_jawatan));
        this.context.put("selectNegeri", HTML.SelectNegeri("id_negeri", id_negeri == "" ? null : Utils.parseLong(id_negeri), null, "onChange=\"doChanges()\" "));
        this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(id_negeri, "id_daerah", id_daerah == null ? null : Utils.parseLong(id_daerah), null, "onChange=\"doChanges()\" "));
        this.context.put("selectMukim", HTML.SelectMukimByDaerah(id_daerah, "id_mukim", id_mukim == null ? null : Utils.parseLong(id_mukim), null, "onChange=\"doChanges()\" "));
	    this.context.put("selectPejabat", this.SelectPejabat("id_pejabat", id_jenisjabatan, id_negeri, id_daerah, id_mukim, id_pejabat, "onChange=\"doChanges()\" "));
    	this.context.put("namaPejabat", this.getNamaPejabat(id_pejabat));
    	this.context.put("alamatPejabat", this.getAlamatPejabat(id_pejabat));

	    RoleProcessor processor = new RoleProcessor(this.conProp);
	    Vector<?> userRoles = processor.getRoles();
	    this.context.put("userRoles", userRoles);

	    Vector<?> pageStyleList = RegisterUser.getPageStyles();
	    this.context.put("pageStyleList", pageStyleList);
  	}

    //****************************
	public boolean add(String idJenisJabatan, String user_login, String user_password, String user_name, String user_email, String idJawatan, 
		String idNegeri, String idDaerah, String idMukim, String idPejabat, String user_role, String idMasuk) throws Exception { 
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			SQLRenderer r = new SQLRenderer();
			r.add("USER_LOGIN");
			r.add("USER_LOGIN", user_login);
			sql = r.getSQLSelect("USERS");
			ResultSet rs = stmt.executeQuery(sql);
			if(!rs.next()) {
				long id = DB.getNextID("USERS_SEQ");
				r.clear();
				r.add("USER_ID", id);
				r.add("USER_LOGIN", user_login);
				r.add("USER_PASSWORD", PasswordService.encrypt(user_password));
				r.add("USER_NAME", user_name);
				r.add("USER_ROLE", user_role);
				r.add("DATE_REGISTERED",r.unquote("SYSDATE"));
				r.add("USER_TYPE", "internal");
				r.add("ID_MASUK", idMasuk);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", idMasuk);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("USERS");
				stmt.executeUpdate(sql);
				
				//masukkan dlm table user internal
				r.clear();
				r.add("USER_ID", id);
				r.add("ID_MASUK", idMasuk);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", idMasuk);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("USERS_INTERNAL");
				stmt.executeUpdate(sql);			  
			  
				//masukkan dlm table user integrasi
				r.clear();
				r.add("ID_MASUK", idMasuk);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", idMasuk);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("USER_ID", id);
				r.add("ID_JENISPEJABAT", idJenisJabatan);
				r.add("ID_PEJABAT", idPejabat);
				r.add("ID_NEGERI", idNegeri);
				r.add("ID_DAERAH", idDaerah);
				r.add("ID_MUKIM", idMukim);
				r.add("EMEL", user_email);
				//myLogger.info("SQL Insert user:"+sql);
				sql = r.getSQLInsert("USERS_INTEGRASI");
				stmt.executeUpdate(sql);
			  
				//MASUKKAN DLM TABLE USER_ROLE 
				r.clear();
				r.add("USER_ID", user_login);
				r.add("ROLE_ID", user_role);
				sql = r.getSQLInsert("USER_ROLE");
				stmt.executeUpdate(sql);
				  
				conn.commit();
			} else { 
				return false; 
			}
		} catch(SQLException ex) {
			try {
				conn.rollback();
			} catch(SQLException sqlexception) { 
				
			}
			throw new DbException((new StringBuilder(String.valueOf(ex.getMessage()))).append(": ").append(sql).toString());
		}
		return true;
	} 
	  
	@SuppressWarnings("unused")
	private String SelectJenisPejabat(String selectName) throws Exception {
		return SelectJenisPejabat(selectName, null, null);
    }
	@SuppressWarnings("unused")
	private String SelectJenisPejabat(String selectName,String selectedValue) throws Exception {
  	  	return SelectJenisPejabat(selectName, selectedValue, null);
    }
	private String SelectJenisPejabat(String selectName, String selectedValue, String jsFunction) throws Exception {
    	String returnValue = "";
  		StringBuffer sb = new StringBuffer();
  		sb.append("<select name='" + selectName + "'");
  		if (jsFunction != null) {
  			sb.append(jsFunction);  			
  		}
  		sb.append(" > ");
  		sb.append("<option value=\"\">SILA PILIH</option>\n");

  		Db db = null;
  		String selected="";
  		try {
  			db = new Db();
  			String sql = "";
			sql = "SELECT ID_JENISPEJABAT, KOD_PEJABAT, NAMA_PEJABAT " +
				"FROM TBLINTRUJJENISPEJABAT " +
				"ORDER BY KOD_PEJABAT, NAMA_PEJABAT";
  			Statement stmt = db.getStatement();
  			ResultSet rs = stmt.executeQuery(sql);
  			String id = "";
  			String nama = "";
  			String kod = "";
  			while (rs.next()) {
  				id = rs.getString(1) == null ? "" : rs.getString(1);
  				kod = rs.getString(2) == null ? "" : rs.getString(2);
  				nama = rs.getString(3) == null ? "" : rs.getString(3);
  				if (id.equalsIgnoreCase(selectedValue.trim())) {
  					selected = "selected=\"selected\"";
  				} else {
  					selected = "";
  				}
  				sb.append("<option " + selected + " value=\"" + id + "\">" + kod + " - " + nama + "</option>\n");
  			}
  			sb.append("</select>");
  		} catch (Exception ex) {
  			ex.printStackTrace();
  			throw ex;
  		}finally {
  			if (db != null) db.close();
  		}
  		returnValue = sb.toString();
      	
    	return returnValue;
  	}
	
	@SuppressWarnings("unused")
	private String SelectPejabat(String selectName) throws Exception {
		return SelectPejabat(selectName, null, null, null, null, null, null);
    }
	@SuppressWarnings("unused")
	private String SelectPejabat(String selectName, String idJenisPejabat, String idNegeri, String idDaerah, String idMukim, String selectedValue) throws Exception {
  	  	return SelectPejabat(selectName, idJenisPejabat, idNegeri, idDaerah, idMukim, selectedValue, null);
    }
	private String SelectPejabat(String selectName, String idJenisPejabat, String idNegeri, String idDaerah, String idMukim, String selectedValue, 
			String jsFunction) throws Exception {
    	String returnValue = "";
    	if (!"".equalsIgnoreCase(idJenisPejabat.trim())) {
    		if ("".equalsIgnoreCase(idNegeri.trim())) {
    			idNegeri = "";
    		}
      		StringBuffer sb = new StringBuffer();
      		sb.append("<select name='" + selectName + "'");
      		if (jsFunction != null)
      			sb.append(jsFunction);
      		sb.append(" > ");
      		sb.append("<option value=\"\">SILA PILIH</option>\n");

      		Db db = null;
      		String selected="";
      		try {
      			db = new Db();
      			String sql = "";
      			if (!"".equalsIgnoreCase(idNegeri.trim())) {
      				// query with id negeri (and possibly id daerah & id mukim)
      				String TEMP = "";
      				if (!"".equalsIgnoreCase(idDaerah.trim())) {
      					TEMP = "AND A.ID_DAERAH = '" + idDaerah + "' ";
      				}
      				if (!"".equalsIgnoreCase(idMukim.trim())) {
      					TEMP += "AND A.ID_MUKIM = '" + idMukim + "' ";
      				}
      				sql = "SELECT A.ID_PEJABAT, A.KOD_PEJABAT, A.NAMA_PEJABAT " +
      					"FROM TBLINTRUJPEJABAT A, TBLINTRUJJENISPEJABAT B, TBLRUJNEGERI NG " +
      					"WHERE A.ID_JENISPEJABAT = B.ID_JENISPEJABAT AND A.ID_NEGERI = NG.ID_NEGERI " +
      					TEMP + "AND A.ID_NEGERI = '" + idNegeri + "' ORDER BY A.ID_JENISPEJABAT, A.KOD_PEJABAT";
      			} else {
      				// return all
      				sql = "SELECT A.ID_PEJABAT, A.KOD_PEJABAT, A.NAMA_PEJABAT " +
  						"FROM TBLINTRUJPEJABAT A, TBLINTRUJJENISPEJABAT B " +
  						"WHERE A.ID_JENISPEJABAT = B.ID_JENISPEJABAT ORDER BY A.ID_JENISPEJABAT, A.KOD_PEJABAT";
      			}
      			Statement stmt = db.getStatement();
      			ResultSet rs = stmt.executeQuery(sql);
      			String id = "";
      			String nama = "";
      			String kod = "";
      			while (rs.next()) {
      				id = rs.getString(1) == null ? "" : rs.getString(1);
      				kod = rs.getString(2) == null ? "" : rs.getString(2);
      				nama = rs.getString(3) == null ? "" : rs.getString(3);
      				if (id.equalsIgnoreCase(selectedValue.trim())) {
      					selected = "selected=\"selected\"";
      				} else {
      					selected = "";
      				}
      				sb.append("<option " + selected + " value=\"" + id + "\">" + kod + " - " + nama + "</option>\n");
      			}
      			sb.append("</select>");
      		} catch (Exception ex) {
      			ex.printStackTrace();
      			throw ex;
      		}finally {
      			if (db != null) db.close();
      		}
      		returnValue = sb.toString();
      	}
    	return returnValue;
  	}

	@SuppressWarnings("unused")
	private String SelectJawatan(String selectName) throws Exception {
		return SelectJawatan(selectName, null, null, null);
    }
	private String SelectJawatan(String selectName, String idJenisPejabat, String selectedValue) throws Exception {
  	  	return SelectJawatan(selectName, idJenisPejabat, selectedValue, null);
    }
	private String SelectJawatan(String selectName, String idJenisPejabat, String selectedValue, String jsFunction) throws Exception {
    	String returnValue = "";
    	if (!"".equalsIgnoreCase(idJenisPejabat.trim())) {
      		StringBuffer sb = new StringBuffer();
      		sb.append("<select name='" + selectName + "'");
      		if (jsFunction != null)
      			sb.append(jsFunction);
      		sb.append(" > ");
      		sb.append("<option value=\"\">SILA PILIH</option>\n");

      		Db db = null;
      		String selected="";
      		try {
      			db = new Db();
      			String sql = "";
  				// return all
  				sql = "SELECT A.ID_JAWATAN, A.KOD_JAWATAN, A.NAMA_JAWATAN " +
					"FROM TBLINTRUJJAWATAN A, TBLINTRUJJENISPEJABAT B " +
					"WHERE A.ID_JENISPEJABAT = B.ID_JENISPEJABAT ORDER BY A.KOD_JAWATAN";
      			Statement stmt = db.getStatement();
      			ResultSet rs = stmt.executeQuery(sql);
      			String id = "";
      			String nama = "";
      			String kod = "";
      			while (rs.next()) {
      				id = rs.getString(1) == null ? "" : rs.getString(1);
      				kod = rs.getString(2) == null ? "" : rs.getString(2);
      				nama = rs.getString(3) == null ? "" : rs.getString(3);
      				if (id.equalsIgnoreCase(selectedValue.trim())) {
      					selected = "selected=\"selected\"";
      				} else {
      					selected = "";
      				}
      				sb.append("<option " + selected + " value=\"" + id + "\">" + kod + " - " + nama + "</option>\n");
      			}
      			sb.append("</select>");
      		} catch (Exception ex) {
      			ex.printStackTrace();
      			throw ex;
      		} finally {
      			if (db != null) db.close();
      		}
      		returnValue = sb.toString();
      	}
    	return returnValue;
  	}
	
	private String getNamaPejabat(String idPejabat) throws Exception {
		String returnValue = "";
		if (!"".equalsIgnoreCase(idPejabat.trim())) {
			Db db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String sql = "";
			
			try {
				sql = "SELECT NAMA_PEJABAT FROM TBLINTRUJPEJABAT WHERE ID_PEJABAT = " + idPejabat;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
				rs.close();
				stmt.close();
			} catch (Exception ex) {
				
			} finally {
				if (db != null) {
					db.close();
				}
			}
		}
		return returnValue;
	}
	
	private String getAlamatPejabat(String idPejabat) throws Exception {
		String returnValue = "";
		if (!"".equalsIgnoreCase(idPejabat.trim())) {
			Db db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String sql = "";
			
			try {
				String ALAMAT1 = "", ALAMAT2 = "", ALAMAT3 = "";
				sql = "SELECT ALAMAT1, ALAMAT2, ALAMAT3 FROM TBLINTRUJPEJABAT WHERE ID_PEJABAT = " + idPejabat;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					ALAMAT1 = rs.getString(1) == null ? "" : rs.getString(1);
					ALAMAT2 = rs.getString(2) == null ? "" : rs.getString(2);
					ALAMAT3 = rs.getString(3) == null ? "" : rs.getString(3);
				}
				if (!"".equalsIgnoreCase(ALAMAT1.trim())) {
					returnValue = ALAMAT1;
				}
				if (!"".equalsIgnoreCase(ALAMAT2.trim())) {
					if (!"".equalsIgnoreCase(returnValue.trim())) {
						returnValue += ", " + ALAMAT2;
					} else {
						returnValue = ALAMAT2;						
					}
				}
				if (!"".equalsIgnoreCase(ALAMAT3.trim())) {
					if (!"".equalsIgnoreCase(returnValue.trim())) {
						returnValue += ", " + ALAMAT3;
					} else {
						returnValue = ALAMAT3;						
					}
				}
				rs.close();
				stmt.close();
			} catch (Exception ex) {
				
			} finally {
				if (db != null) {
					db.close();
				}
			}
		}
		return returnValue;
	}
	//****************************
}