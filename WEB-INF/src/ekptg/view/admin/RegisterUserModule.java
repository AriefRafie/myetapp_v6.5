package ekptg.view.admin;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.app.RoleProcessor;
import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.portal.db.RegisterUser;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;


public class RegisterUserModule extends AjaxBasedModule
{
  private Hashtable<?, ?> conProp = new Hashtable<Object, Object>();
  static Logger myLogger = Logger.getLogger(RegisterUserModule.class);
  public String doTemplate2()
    throws Exception
  {
    HttpSession session = this.request.getSession();
    doJob(session);
    String template = "vtl/admin/registeruser.vm";
    //Template template = this.engine.getTemplate("vtl/admin/registeruser.vm");
    return template;
  }

  private void doJob(HttpSession session) throws Exception {
    String submit = getParam("command");

    String user_login = getParam("user_login");
    String user_name = getParam("user_name");
    String user_password = getParam("user_password");
    String user_role = getParam("user_role");
    String page_style = getParam("page_style");

    // Ekptg Added
    String id_seksyen = getParam("id_seksyen");
    String id_negeri = getParam("id_negeri");
    String id_daerah = getParam("id_daerah");
    String user_type = getParam("user_type");
    String id_pejabatjkptg = getParam("id_pejabatjkptg");
    String id_jawatan = getParam("id_jawatan");
    String emel = getParam("user_emel");
    
    this.context.put("submit", submit);
    this.context.put("user_login", user_login);
    this.context.put("user_name", user_name);
    this.context.put("user_password", user_password);
    this.context.put("user_role", user_role);
    this.context.put("page_style", page_style);

    this.context.put("id_seksyen", id_seksyen);
    this.context.put("id_negeri", id_negeri);
    this.context.put("id_daerah", id_daerah);

    this.context.put("user_type", user_type);
    this.context.put("id_pejabatjkptg", id_pejabatjkptg);
    this.context.put("id_jawatan", id_jawatan);

    String id_masuk = (String)session.getAttribute("_ekptg_user_id");
    String id_user_negeri = (String)session.getAttribute("_ekptg_user_negeri");
    this.context.put("id_negeri_user",id_user_negeri);

    myLogger.debug("id_negeri_user:"+id_user_negeri);


    Hashtable pejabatJKPTGInfo = new Hashtable();

    String doPost = (String) session.getAttribute("doPost");

    if (doPost == "false") {
           myLogger.debug("browser refresh **********");
           submit = ""; //do what ever approriate action
    }
    this.context.put("daerahJagaan","");

    if (!("".equals(submit))) {
        this.context.put("selectSeksyen",HTML.SelectSeksyen("id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
        this.context.put("selectJawatan",HTML.SelectJawatan("id_jawatan",Utils.parseLong(id_jawatan)));
        //New

        if ("16".equals(id_user_negeri)) { //HQ
            this.context.put("selectNegeri",HTML.SelectNegeri("id_negeri",id_negeri==""?null:Utils.parseLong(id_negeri),null,"onChange=\"doChanges()\" "));
            this.context.put("selectPejabatJKPTG",SelectPejabatJKPTG("id_pejabatjkptg",id_negeri,id_daerah,id_seksyen,id_pejabatjkptg,null,"onChange=\"doChanges()\" "));
        } else {
        	this.context.put("selectNegeri",getNegeri(id_user_negeri));
            this.context.put("selectPejabatJKPTG",SelectPejabatJKPTG("id_pejabatjkptg",id_user_negeri,id_daerah,id_seksyen,id_pejabatjkptg,null,"onChange=\"doChanges()\" "));
        }
        this.context.put("pejabatJKPTGInfo",(Hashtable)doGetPejabatJKPTG(id_pejabatjkptg));
        this.context.put("daerahJagaan",getDaerahJagaan(id_pejabatjkptg));


	     if ("add".equals(submit)) {
	        if (RegisterUser.add(user_name, user_login, user_password, user_role, page_style,
	        					 id_seksyen,id_negeri,id_daerah,user_type,id_pejabatjkptg,id_jawatan,
	        					 id_masuk,emel)) {
	          AuditTrail.logActivity(this,session,"INS","USER ["+user_name+"] Added");
	          this.context.put("registerUserStatus", "success");
	          
	        }
	        else
	          this.context.put("registerUserStatus", "failed");
	      } else if ("update".equals(submit)) {
	    	  RegisterUser.update(user_name, user_login, user_password, user_role, user_role, 
	    			  page_style);
	    	  AuditTrail.logActivity(this,session,"UPD","USER ["+user_name+"] Updated");
	    	  this.context.put("registerUserStatus", "success");
	      } else if ("doChanges".equals(submit)) {
	    	  //System.out.println("id_seksyen:"+id_seksyen);
	          this.context.put("registerUserStatus", "none");
	       }
     }
    else {
      this.context.put("registerUserStatus", "none");
      this.context.put("selectSeksyen",HTML.SelectSeksyen("id_seksyen",Utils.parseLong(id_seksyen),null,"onChange=\"doChanges()\" "));
      this.context.put("selectJawatan",HTML.Selectjawatan("id_jawatan"));

      if ("16".equals(id_user_negeri)) { //HQ
    	  this.context.put("selectNegeri",HTML.SelectNegeri("id_negeri","onChange=\"doChanges()\" "));
          //this.context.put("selectDaerah",SelectDaerahJKPTG("id_daerah","onChange=\"doChanges()\" "));
          this.context.put("selectPejabatJKPTG",SelectPejabatJKPTG("id_pejabatjkptg"));
          this.context.put("pejabatJKPTGInfo",new Hashtable());
      } else {
    	  this.context.put("selectNegeri",getNegeri(id_user_negeri));
          this.context.put("selectPejabatJKPTG",SelectPejabatJKPTG("id_pejabatjkptg",id_user_negeri,id_daerah,id_seksyen,id_pejabatjkptg,null,"onChange=\"doChanges()\" "));

      }


    }

    RoleProcessor processor = new RoleProcessor(this.conProp);
    Vector<?> userRoles = processor.getRoles();
    this.context.put("userRoles", userRoles);

    Vector<?> pageStyleList = RegisterUser.getPageStyles();
    this.context.put("pageStyleList", pageStyleList);
  }

    private Hashtable doGetPejabatJKPTG(String ID_PEJABATJKPTG) throws Exception{

    	Db db = null;
    	Hashtable result = new Hashtable();
		try {
			db = new Db();
			String sql = "select A.ID_PEJABATJKPTG,A.KOD_JKPTG," +
					"A.NAMA_PEJABAT,ALAMAT1,ALAMAT2,ALAMAT3,A.ID_DAERAH from tblRujPejabatJKPTG A " ;
			sql = sql + " WHERE A.ID_PEJABATJKPTG='"+ID_PEJABATJKPTG+"'";
//			sql = sql + " WHERE id_negeri='"+id_negeri+"'";
//			sql = sql + " AND id_daerah='"+id_daerah+"'";
//			sql = sql + " AND id_seksyen='"+id_seksyen+"'";

			Statement stmt = db.getStatement();
			//
			ResultSet rs = stmt.executeQuery(sql);
			String address = null;
			while (rs.next()) {
					result.put("id",getValue(rs.getString(1)));
					result.put("kod_jkptg",getValue(rs.getString(2)));
					result.put("nama_pejabat",getValue(rs.getString(3)));
					result.put("alamat1",getValue(rs.getString(4)));
					result.put("alamat2",getValue(rs.getString(5)));
					result.put("alamat3",getValue(rs.getString(6)));
					result.put("id_daerah",getValue(rs.getString(7)));
					}
					address = checkIsNull(result.get("alamat1"))+"\n"+checkIsNull(result.get("alamat2"))+"\n"+checkIsNull(result.get("alamat3"));
					result.put("address", address);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally {
			if (db != null) db.close();
		}

	return result;
}

    public String checkIsNull(Object txt) {
    	if (txt == null) return "";
    	else return (String)txt;
    }

    ////////////////////////// Select Pejabat


//////////////////////////
  public String SelectDaerahJKPTG(String selectName,String jsFunction) throws Exception{
	  return SelectDaerahJKPTG(selectName,null,null,null,null,jsFunction);
  }
  public String SelectDaerahJKPTG(String selectName,String idNegeri,String idSeksyen,String selectedValue)
  throws Exception{
	  return SelectDaerahJKPTG(selectName,idNegeri,idSeksyen,selectedValue,null,null);
  }
  public String SelectDaerahJKPTG(String selectName,String idNegeri,String idSeksyen,
			String selectedValue, String disability,String jsFunction)
  			throws Exception {

	 if (idNegeri == null) idNegeri="";


	  StringBuffer sb = new StringBuffer();
		sb.append("<select name='" + selectName + "'");
		if (disability != null)
			sb.append(disability);
		if (jsFunction != null)
			sb.append(jsFunction);
		sb.append(" > ");
		sb.append("<option value=>Sila Pilih Daerah</option>\n");

		Db db = null;
		String selected="";
		String sqlWhere="";
		try {
			db = new Db();
			String sql = "select A.ID_DAERAH,A.KOD_DAERAH,A.NAMA_DAERAH from tblRujDaerah A " +
						 "where A.id_daerah in ( "+
						 "select id_daerah from tblrujpejabatjkptg ";
			sql = sql + " WHERE id_negeri='"+idNegeri+"'";
			if (idSeksyen != null && !"".equals(idSeksyen) && "0".equals(idSeksyen)) {
				sql = sql + " AND id_seksyen='"+idSeksyen+"'";
			}
			sql = sql + " ) ";
			sql = sql + " ORDER BY A.KOD_DAERAH";
			Statement stmt = db.getStatement();
			//
			ResultSet rs = stmt.executeQuery(sql);
			String id="";
			String nama = "";
			String kod="";
			while (rs.next()) {
				id = rs.getString("id_daerah");
				kod = rs.getString("kod_daerah");
				nama = rs.getString("nama_daerah");
				if (id.equals(selectedValue)) selected = "selected";
				else selected = "";
				sb.append("<option " + selected + " value=" + id + ">"
						+ kod + " - " + nama + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally {
			if (db != null) db.close();
		}
		return sb.toString();

	}

   /////////////////
  public String SelectPejabatJKPTG(String selectName) throws Exception{
	  return SelectPejabatJKPTG(selectName,null,null,null,null,null,null);
  }
  public String SelectPejabatJKPTG(String selectName,String idNegeri,String idDaerah,String idSeksyen,String selectedValue)
  throws Exception{
	  return SelectPejabatJKPTG(selectName,idNegeri,idDaerah,idSeksyen,selectedValue,null,null);
  }

  public String SelectPejabatJKPTG(String selectName,String idNegeri,String idDaerah,String idSeksyen,
			String selectedValue, String disability,String jsFunction)
			throws Exception {

		if (idNegeri == null) idNegeri = "";
		//System.out.println("idSeksyen:"+idSeksyen);
		StringBuffer sb = new StringBuffer();
		sb.append("<select name='" + selectName + "'");
		if (disability != null)
			sb.append(disability);
		if (jsFunction != null)
			sb.append(jsFunction);
		sb.append(" > ");
		sb.append("<option value=>Sila Pilih Pejabat</option>\n");

		Db db = null;
		String selected="";
		try {
			db = new Db();
			String sql = "select A.ID_PEJABATJKPTG,A.KOD_JKPTG," +
					"A.NAMA_PEJABAT " +
					//"|| ' ' || A.ALAMAT1
					"AS NAMA_PEJABAT from tblRujPejabatJKPTG A " ;
			sql = sql + " WHERE id_negeri='"+idNegeri+"'";

//			if (idDaerah != null && !"".equals(idDaerah) && !"0".equals(idDaerah)) {
//				sql = sql + " AND id_daerah='"+idDaerah+"'";
//			}

			if (idSeksyen != null && !"".equals(idSeksyen) && !"0".equals(idSeksyen)) {
				sql = sql + " AND id_seksyen='"+idSeksyen+"'";
			}
			sql = sql + " ORDER BY A.KOD_JKPTG";
			Statement stmt = db.getStatement();
			//myLogger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			String id="";
			String nama = "";
			String kod="";
			while (rs.next()) {
				id = rs.getString("ID_PEJABATJKPTG");
				kod = rs.getString("KOD_JKPTG");
				nama = rs.getString("NAMA_PEJABAT");
				if (id.equals(selectedValue)) selected = "selected";
				else selected = "";
				sb.append("<option " + selected + " value=" + id + ">"
						+ kod + " - " + nama + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally {
			if (db != null) db.close();
		}
		return sb.toString();

	}

  public String getDaerahJagaan(String id_pejabatjkptg) throws Exception {
	  Db db = null;
	  String sql = "";
	  String output="";
	  try {
		  db = new Db();
		  Statement stmt = db.getStatement();
		  sql = "Select nama_daerah from tblRujDaerah where "+
		  "id_daerah in (select distinct id_daerahurus " +
		  "from TBLRUJPEJABATURUSAN where id_pejabatjkptg='"+id_pejabatjkptg+"') "+
		  "order by kod_daerah";
		  ResultSet rs = stmt.executeQuery(sql);
		  while (rs.next()) {
			  output = output + rs.getString("nama_daerah") + ",";
		  }
		  if (output.length() > 0) output=output.substring(0,output.length()-1);
	  } catch (Exception e) {
			e.printStackTrace();
			throw e;
	  }finally {
			if (db != null) db.close();
		}
	  return output;
  }

  public String getNegeri(String id_negeri) throws Exception {
	  Db db = null;
	  String sql = "";
	  String output="";
	  try {
		  db = new Db();
		  Statement stmt = db.getStatement();
		  sql = "Select upper(nama_negeri) as nama_negeri from tblRujNegeri where id_negeri='"+id_negeri+"'";
		  //
		  ResultSet rs = stmt.executeQuery(sql);
		  if (rs.next()) {
			output = rs.getString("nama_negeri");
		  }
	  } catch (Exception e) {
		  throw e;
	  } finally {
		  if (db != null) db.close();
	  }
	  return "<input type=hidden name='id_negeri' value='"+id_negeri+"'>"+output;
  }

	public String getValue(String txt) throws Exception {
		if (txt == null) return "";
		else return txt;
	}
}
